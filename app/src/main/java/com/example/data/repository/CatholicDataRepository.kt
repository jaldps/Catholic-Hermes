package com.example.data.repository

import android.content.Context

import com.example.data.models.AppLanguage
import com.example.data.models.BibleBook
import com.example.data.models.BibleChapter
import com.example.data.models.BibleVersionInfo
import com.example.data.models.CapitalSinVsVirtue
import com.example.data.models.CatholicNewsArticle
import com.example.data.models.ChristManifestation
import com.example.data.models.ChurchHistoryEpoch
import com.example.data.models.ChurchPrecept
import com.example.data.models.ChurchSchismHistory
import com.example.data.models.CommandmentExploration
import com.example.data.models.DailyMassReading
import com.example.data.models.DivineMercyStep
import com.example.data.models.GregorianChantItem
import com.example.data.models.MarianApparition
import com.example.data.models.NearbyParish
import com.example.data.models.Novena
import com.example.data.models.PrayerCategory
import com.example.data.models.RosaryMystery
import com.example.data.models.RosaryMysteryType
import com.example.data.models.SaintOfTheDay
import com.example.data.models.SinTheologyExplanation
import com.example.data.models.TriviaQuestion
import com.example.data.models.VaticanPrayer
import com.example.data.models.VerseOfTheDay
import com.example.data.verse.DailyVerseMeditation
import com.example.data.verse.DailyVerseRotation
import com.example.data.models.ViaCrucisStation
import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.CalendarRules
import com.example.data.xref.BibleLine
import com.example.data.xref.CrossReference
import com.example.data.xref.CrossReferenceIndex
import com.example.data.xref.VerseRef
import com.example.data.liturgy.LectionaryData
import com.example.data.liturgy.CelebrationRank
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.ScriptureResolver
import com.example.data.models.LocalizedText
import com.example.data.news.DailySaint
import com.example.data.news.DailySaintsDay
import com.example.data.news.DailySaintsFetcher
import com.example.data.news.VaticanSaint
import com.example.data.prefs.UserPreferencesStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar
import java.util.Locale

class CatholicDataRepository(
    private val context: Context? = null,
    private val preferencesStore: UserPreferencesStore? = null,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    private val dateProvider: () -> CalendarDate = LiturgicalCalendar::today,
    private val dateChanges: kotlinx.coroutines.flow.Flow<CalendarDate>? = null,
    private val saintFetcher: DailySaintsFetcher = DailySaintsFetcher()
) {

    private val bibleProvider: BibleTextProvider? = context?.let { BibleTextProvider(it) }
    val bibleDownloadManager: BibleDownloadManager? = context?.let { BibleDownloadManager(it) }

    /** Resolves lectionary citations against the bundled Bible. */
    private val scriptureResolver by lazy { ScriptureResolver(
        bookName = { bookId, language ->
            BibleData.catholicCanonBooks.firstOrNull { it.id == bookId }?.name?.get(language)
        },
        versesOf = { bookId, chapter, language ->
            bibleProvider?.getVerses(bookId, chapter, language) ?: emptyList()
        }
    ) }

    private val canonIds: List<String> by lazy { BibleData.catholicCanonBooks.map { it.id } }
    private val crossReferenceIndex: CrossReferenceIndex? by lazy {
        context?.let { CrossReferenceIndex(it, canonIds) }
    }
    private val bibleLines = HashMap<AppLanguage, BibleLine>()

    private val _currentDate = MutableStateFlow(dateProvider())
    val currentDate: StateFlow<CalendarDate> = _currentDate.asStateFlow()
    private val _calendarRules = MutableStateFlow(CalendarRules())
    val calendarRules: StateFlow<CalendarRules> = _calendarRules.asStateFlow()
    fun applyCalendarRules(rules: CalendarRules) { _calendarRules.value = rules }
    fun vaticanCalendarMatches(date: CalendarDate, rules: CalendarRules = _calendarRules.value): Boolean =
        LiturgicalCalendar.dayInfo(date, rules).lectionaryKey == LiturgicalCalendar.dayInfo(date).lectionaryKey

    private val _currentLanguage = MutableStateFlow(detectInitialLanguage())
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    private val _activeBibleEditionId = MutableStateFlow("bundled")
    val activeBibleEditionId: StateFlow<String> = _activeBibleEditionId.asStateFlow()

    /** Reader selection never changes the edition used for liturgical selections. */
    fun selectBibleEdition(id: String) {
        val selected = id.takeIf { candidate ->
            bibleDownloadManager?.specs?.any { it.id == candidate } == true &&
                bibleDownloadManager.isDownloaded(candidate)
        } ?: "bundled"
        _activeBibleEditionId.value = selected
        preferencesStore?.let { store -> scope.launch { store.setActiveBibleTranslationId(selected) } }
    }

    data class DailyContent(
        val date: CalendarDate,
        val reading: DailyMassReading,
        val verse: VerseOfTheDay,
        val liturgicalDay: com.example.data.models.LiturgicalCalendarDay,
        val language: AppLanguage,
        val rules: CalendarRules
    )

    private val _dailyContent = MutableStateFlow<DailyContent?>(null)
    val dailyContent: StateFlow<DailyContent?> = _dailyContent.asStateFlow()
    private val readingCache = java.util.concurrent.ConcurrentHashMap<Triple<CalendarDate, AppLanguage, CalendarRules>, DailyMassReading>()
    private val verseCache = java.util.concurrent.ConcurrentHashMap<Pair<CalendarDate, AppLanguage>, VerseOfTheDay>()

    fun emptyVerseOfTheDay(): VerseOfTheDay = DevotionsAndHistoryData.verseOfTheDay.copy(
        reference = "", text = LocalizedText("", "", "")
    )

    /** Loading state contains no other day's text or invented reflection. */
    fun emptyMassReading(date: CalendarDate): DailyMassReading {
        val info = LiturgicalCalendar.dayInfo(date, _calendarRules.value)
        val empty = LocalizedText("", "", "")
        return BibleData.todayMassReading.copy(
            dateDisplay = date.toString(), liturgicalSeason = info.season.displayName,
            liturgicalColorHex = info.color.hex, feastTitle = info.title,
            firstReadingRef = "", firstReadingText = empty, responsorialPsalmRef = "", responsorialPsalmText = empty,
            secondReadingRef = null, secondReadingText = null, gospelRef = "", gospelText = empty,
            homilyReflection = empty, saintOfFeast = info.title, sourceName = "", sourceUrl = ""
        )
    }

    private fun detectInitialLanguage(): AppLanguage {
        val lang = Locale.getDefault().language.lowercase()
        return when {
            lang.startsWith("es") -> AppLanguage.ES
            lang.startsWith("pt") -> AppLanguage.PT
            else -> AppLanguage.EN
        }
    }

    /**
     * Sets the active language and persists it, so the choice survives process
     * death. Call [applyPersistedLanguage] on startup to restore it.
     */
    fun setLanguage(language: AppLanguage) {
        val changed = _currentLanguage.value != language
        _currentLanguage.value = language
        preferencesStore?.let { store ->
            scope.launch { store.setLanguage(language) }
        }
        // The saint page is language-specific: refetch in the new language.
        // The shared date/language flow owns saint refreshes.
    }

    /** Applies a language restored from storage without writing it back. */
    fun applyPersistedLanguage(language: AppLanguage) {
        val changed = _currentLanguage.value != language
        _currentLanguage.value = language
        // The shared date/language flow owns saint refreshes.
    }

    // Bible & Liturgical Mass
    val catholicBooks: List<BibleBook> get() = BibleData.catholicCanonBooks
    val bibleVersions: List<BibleVersionInfo> get() = BibleData.bibleVersions
    val dailyMassReading: DailyMassReading get() = getMassReading(dateProvider())

    /**
     * The Mass of [date], assembled from the computed liturgical day and the
     * lectionary, with the readings' text drawn from the bundled Bible in the
     * active language.
     *
     * Where the lectionary has no entry for the day — most ferial weekdays,
     * whose readings are not in the bundled table — the citation and text are
     * left empty rather than filled with another day's readings. The screen
     * shows the liturgical day without pretending to readings it does not have.
     */
    fun getMassReading(date: CalendarDate): DailyMassReading {
        val language = _currentLanguage.value
        val rules = _calendarRules.value
        val key = Triple(date, language, rules)
        readingCache[key]?.let { return it }
        val info = LiturgicalCalendar.dayInfo(date, rules)
        val entry = LectionaryData.entryFor(info.lectionaryKey, info.sundayCycle)
        val fallback = BibleData.todayMassReading

        val dateDisplay = java.text.SimpleDateFormat(
            "EEEE, d MMMM yyyy",
            java.util.Locale(language.code)
        ).format(
            java.util.GregorianCalendar(info.date.year, info.date.month - 1, info.date.day).time
        )

        fun citation(raw: String?): String =
            raw?.let { scriptureResolver.display(it, language) }.orEmpty()

        fun passage(raw: String?): LocalizedText? =
            raw?.let { scriptureResolver.passage(it, language) }?.let { LocalizedText(it, it, it) }

        val empty = LocalizedText("", "", "")

        return DailyMassReading(
            dateDisplay = dateDisplay,
            liturgicalSeason = info.season.displayName,
            liturgicalColorHex = info.color.hex,
            feastTitle = info.title,
            firstReadingRef = citation(entry?.firstReading),
            firstReadingText = passage(entry?.firstReading) ?: empty,
            responsorialPsalmRef = citation(entry?.psalmFor(language)),
            responsorialPsalmText = passage(entry?.psalmFor(language)) ?: empty,
            secondReadingRef = entry?.secondReading?.let { citation(it) },
            secondReadingText = passage(entry?.secondReading),
            gospelRef = citation(entry?.gospel),
            gospelText = passage(entry?.gospel) ?: empty,
            homilyReflection = empty,
            saintOfFeast = info.title,
            sourceName = when (language) {
                AppLanguage.ES -> "Selecciones bíblicas · edición de lectura, no leccionario litúrgico"
                AppLanguage.EN -> "Scripture selections · reading edition, not a liturgical lectionary"
                AppLanguage.PT -> "Seleções bíblicas · edição de leitura, não lecionário litúrgico"
            },
            sourceUrl = entry?.sourceUrl ?: fallback.sourceUrl
        ).also {
            if (readingCache.size > 6) readingCache.clear()
            readingCache[key] = it
        }
    }

    fun getChapter(
        bookId: String,
        chapterNumber: Int,
        language: AppLanguage = _currentLanguage.value
    ): BibleChapter {
        val verses = bibleProvider?.getVerses(bookId, chapterNumber, language, _activeBibleEditionId.value)
            ?: BibleData.getVersesForBookAndChapter(bookId, chapterNumber)
        return BibleChapter(
            bookId = bookId,
            chapterNumber = chapterNumber,
            verses = verses
        )
    }

    /** Chapters actually available for [bookId], falling back to the declared count. */
    fun getChapterCount(bookId: String, language: AppLanguage = _currentLanguage.value): Int {
        val editionId = _activeBibleEditionId.value
        val fromAssets = bibleProvider?.chapterCount(bookId, language, editionId) ?: 0
        if (editionId != "bundled") return fromAssets
        if (fromAssets > 0) return fromAssets
        return BibleData.catholicCanonBooks.firstOrNull { it.id == bookId }?.chaptersCount ?: 0
    }

    fun searchBible(
        query: String,
        limit: Int = 50,
        language: AppLanguage = _currentLanguage.value
    ): List<Triple<String, Int, com.example.data.models.BibleVerse>> {
        return bibleProvider?.search(query, language, limit, _activeBibleEditionId.value) ?: emptyList()
    }

    // Rosary & Divine Mercy
    fun getTodayRosaryMystery(): RosaryMystery = getRosaryMystery(RosaryMysterySchedule.today())

    fun getRosaryMystery(type: RosaryMysteryType): RosaryMystery {
        return when (type) {
            RosaryMysteryType.JOYFUL -> PrayerAndNovenaData.joyfulMysteries
            RosaryMysteryType.SORROWFUL -> PrayerAndNovenaData.sorrowfulMysteries
            RosaryMysteryType.GLORIOUS -> PrayerAndNovenaData.gloriousMysteries
            RosaryMysteryType.LUMINOUS -> PrayerAndNovenaData.luminousMysteries
        }
    }

    val divineMercySteps: List<DivineMercyStep> get() = PrayerAndNovenaData.divineMercySequence

    // Novenas & Vatican Prayers
    val officialNovenas: List<Novena> get() = PrayerAndNovenaData.officialNovenas
    val vaticanPrayers: List<VaticanPrayer> get() = PrayerAndNovenaData.vaticanOfficialPrayers

    fun searchPrayers(query: String, category: PrayerCategory? = null, tag: String? = null): List<VaticanPrayer> {
        val currentLang = _currentLanguage.value
        return vaticanPrayers.filter { prayer ->
            val matchesCategory = category == null || prayer.category == category
            val matchesTag = tag == null || prayer.thematicTags.contains(tag)
            val titleText = prayer.title.get(currentLang)
            val bodyText = prayer.leaderText.get(currentLang)
            val matchesQuery = query.isBlank() || titleText.contains(query, ignoreCase = true) || bodyText.contains(query, ignoreCase = true)
            matchesCategory && matchesTag && matchesQuery
        }
    }

    // Saints & Devotions
    val saintOfTheDay: SaintOfTheDay get() = DevotionsAndHistoryData.saintOfTheDay

    /**
     * Saint of the day as a flow: starts with the bundled entry (instant,
     * offline) and replaces it with today's entry once the network
     * answers. Fallback order:
     * 1. Vatican News saint page in the current app language
     *    ([DailySaintsFetcher.fetchVaticanSaint]) — trilingual, primary.
     * 2. Daily-saints API ([DailySaintsFetcher.fetchToday]) — English only.
     * 3. The bundled entry stays.
     * Refetched on every language change, so the flow always shows the
     * saint in the active language. [saintOfTheDay] above is kept for
     * sync callers.
     */
    private val _saintOfTheDay = MutableStateFlow(DevotionsAndHistoryData.getTodaySaint(calendarFor(dateProvider())))
    val saintOfTheDayFlow: StateFlow<SaintOfTheDay> = _saintOfTheDay.asStateFlow()

    private var saintFetchJob: kotlinx.coroutines.Job? = null

    init {
        preferencesStore?.let { store ->
            scope.launch {
                store.preferences.collectLatest { preferences ->
                    _calendarRules.value = preferences.calendarRules
                    val id = preferences.activeBibleTranslationId
                    _activeBibleEditionId.value = id.takeIf { candidate ->
                        bibleDownloadManager?.specs?.any { it.id == candidate } == true &&
                            bibleDownloadManager.isDownloaded(candidate)
                    } ?: "bundled"
                }
            }
        }
        scope.launch {
            val dates = dateChanges ?: flow {
                while (true) {
                    emit(dateProvider())
                    delay(30_000)
                }
            }.distinctUntilChanged()
            combine(currentLanguage, dates, calendarRules) { language, date, rules -> Triple(language, date, rules) }
                .collectLatest { (language, date, rules) ->
                    _currentDate.value = date
                    refreshSaintOfTheDay(language, date)
                    _dailyContent.value = null
                    val loaded = withContext(Dispatchers.IO) {
                        DailyContent(date, getMassReading(date), dailyVerseFor(date), getLiturgicalDay(date), language, rules)
                    }
                    if (_currentLanguage.value == language && _calendarRules.value == rules && _currentDate.value == date) _dailyContent.value = loaded
                }
        }
    }

    fun close() { scope.cancel() }

    /**
     * (Re)fetches today's saint in [language] and pushes it into
     * [_saintOfTheDay]. A still-running fetch is cancelled so an older
     * language cannot overwrite a newer one.
     */
    private fun calendarFor(date: CalendarDate): Calendar = java.util.GregorianCalendar(date.year, date.month - 1, date.day)

    private fun refreshSaintOfTheDay(language: AppLanguage, date: CalendarDate) {
        saintFetchJob?.cancel()
        val bundled = DevotionsAndHistoryData.getTodaySaint(calendarFor(date))
        _saintOfTheDay.value = bundled
        saintFetchJob = scope.launch {
            val monthDay = "%02d-%02d".format(java.util.Locale.ROOT, date.month, date.day)
            val vatican = saintFetcher.fetchVaticanSaint(language, monthDay)
            val saint = if (vatican != null) mapVaticanSaint(vatican, bundled, language, date)
                else saintFetcher.fetchToday(monthDay)?.let { mapDailySaints(it, bundled) } ?: bundled
            if (_currentLanguage.value == language && _currentDate.value == date && dateProvider() == date) {
                _saintOfTheDay.value = saint
            }
        }
    }

    /**
     * Maps the Vatican News entry (name + biography in [language]) to
     * [SaintOfTheDay]. The fetched slot holds the fresh page text; the
     * other slots keep the bundled text on a curated match, otherwise
     * they fall back to the fetched text verbatim (no machine translation
     * is invented here). [SaintOfTheDay.feastDate] is today's date
     * rendered for the active language.
     */
    internal fun mapVaticanSaint(vatican: VaticanSaint, bundled: SaintOfTheDay, language: AppLanguage, date: CalendarDate = dateProvider()): SaintOfTheDay {
        val curated = !bundled.id.startsWith("saint_day_")
        val bundledMatch = curated && (namesMatch(vatican.name, bundled.name.get(language)) || namesMatch(vatican.name, bundled.name.en))

        fun localized(current: String, bundledText: LocalizedText): LocalizedText {
            val base = if (bundledMatch) bundledText else LocalizedText(current, current, current)
            return when (language) {
                AppLanguage.ES -> base.copy(es = current)
                AppLanguage.EN -> base.copy(en = current)
                AppLanguage.PT -> base.copy(pt = current)
            }
        }

        val prayerEn = "Saint ${vatican.name}, pray for us."
        val today = calendarFor(date)
        return SaintOfTheDay(
            id = "vatican_${language.code}_${"%04d-%02d-%02d".format(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH))}_" +
                vatican.name.lowercase().replace(Regex("[^a-z0-9]+"), "_").trim('_'),
            name = localized(vatican.name, bundled.name),
            feastDate = feastDateForToday(today),
            title = if (bundledMatch) bundled.title else localized(vatican.name, LocalizedText("", "", "")),
            patronages = if (bundledMatch) bundled.patronages else LocalizedText("", "", ""),
            biography = localized(vatican.biography, bundled.biography),
            iconicQuote = if (bundledMatch) bundled.iconicQuote else localized(vatican.biography, LocalizedText("", "", "")),
            intercessionPrayer = if (bundledMatch) bundled.intercessionPrayer else localized(prayerEn, LocalizedText("", "", ""))
        )
    }

    /**
     * Maps the feed's English-only entry to [SaintOfTheDay].
     *
     * Which saint: the feed lists every commemoration of the day, so the
     * bundled entry's English name is matched against the list (significant
     * name tokens) — the first entry is used when nothing matches. A
     * "bundled match" is a curated bundled entry (not the generic
     * "Saint of Today" fallback, id `saint_day_*`) whose name matches the
     * selected saint.
     *
     * English text comes from the feed: name -> name, era -> title
     * (legacy when era is null), patronage -> patronages (legacy/era
     * when the list is empty), legacy -> iconicQuote, summary ->
     * biography. The feed has no prayer, so the EN intercession is the
     * short invocation "Saint <name>, pray for us." — never presented as
     * a sourced text.
     *
     * Spanish/Portuguese: taken from the bundled entry on a bundled
     * match, otherwise they fall back to the English text verbatim (no
     * machine translation is invented here). [SaintOfTheDay.feastDate]
     * is the feed's feast_day rendered for the active language.
     */
    internal fun mapDailySaints(day: DailySaintsDay, bundled: SaintOfTheDay): SaintOfTheDay {
        val curated = !bundled.id.startsWith("saint_day_")
        val selected = day.saints.firstOrNull { saint ->
            curated && namesMatch(saint.name, bundled.name.en)
        } ?: day.saints.first()
        val bundledMatch = curated && namesMatch(selected.name, bundled.name.en)

        // ES/PT text: bundled on a match, EN verbatim otherwise.
        fun localized(en: String, bundledText: LocalizedText) =
            if (bundledMatch) LocalizedText(bundledText.es, en, bundledText.pt)
            else LocalizedText(en, en, en)

        val titleEn = selected.era ?: selected.legacy ?: selected.feastDay
        val patronagesEn = selected.patronage.joinToString(", ").ifBlank {
            selected.legacy ?: selected.era ?: ""
        }
        val quoteEn = selected.legacy ?: selected.summary
        val prayerEn = "Saint ${selected.name}, pray for us."

        return SaintOfTheDay(
            id = "api_${day.date}_${selected.name.lowercase()
                .replace(Regex("[^a-z0-9]+"), "_").trim('_')}",
            name = localized(selected.name, bundled.name),
            feastDate = localizedFeastDate(day.feastDay),
            title = localized(titleEn, bundled.title),
            patronages = localized(patronagesEn, bundled.patronages),
            biography = localized(selected.summary, bundled.biography),
            iconicQuote = localized(quoteEn, bundled.iconicQuote),
            intercessionPrayer = localized(prayerEn, bundled.intercessionPrayer)
        )
    }

    /** Month names as (ES, EN, PT), indexed by [Calendar.MONTH]. */
    private val MONTH_NAMES = listOf(
        Triple("Enero", "January", "Janeiro"),
        Triple("Febrero", "February", "Fevereiro"),
        Triple("Marzo", "March", "Março"),
        Triple("Abril", "April", "Abril"),
        Triple("Mayo", "May", "Maio"),
        Triple("Junio", "June", "Junho"),
        Triple("Julio", "July", "Julho"),
        Triple("Agosto", "August", "Agosto"),
        Triple("Septiembre", "September", "Setembro"),
        Triple("Octubre", "October", "Outubro"),
        Triple("Noviembre", "November", "Novembro"),
        Triple("Diciembre", "December", "Dezembro")
    )

    /** Renders one month/day in the active language ("5 de Noviembre" / "November 5" / "5 de Novembro"). */
    private fun renderFeastDate(dayNum: Int, monthIndex: Int): String {
        val names = MONTH_NAMES.getOrNull(monthIndex) ?: return "$dayNum"
        return when (_currentLanguage.value) {
            AppLanguage.ES -> "$dayNum de ${names.first}"
            AppLanguage.EN -> "${names.second} $dayNum"
            AppLanguage.PT -> "$dayNum de ${names.third}"
        }
    }

    /**
     * Today's date rendered for the active language, for entries whose
     * source (Vatican News) carries no feast-day string of its own.
     */
    private fun feastDateForToday(cal: Calendar = Calendar.getInstance()): String =
        renderFeastDate(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH))

    /**
     * The feed's feast_day ("November 5") rendered for the active
     * language ("5 de Noviembre" / "November 5" / "5 de Novembro"), so
     * the Home card's trailing date reads natively. Falls back to the
     * raw English string when it cannot be parsed.
     */
    private fun localizedFeastDate(feastDay: String): String {
        val match = Regex("""^([A-Za-z]+)\s+(\d{1,2})$""").matchEntire(feastDay.trim())
            ?: return feastDay
        val monthIndex = MONTH_NAMES.indexOfFirst { it.second.equals(match.groupValues[1], ignoreCase = true) }
        if (monthIndex < 0) return feastDay
        val dayNum = match.groupValues[2].toIntOrNull() ?: return feastDay
        return renderFeastDate(dayNum, monthIndex)
    }

    /**
     * True when two English saint names share a significant token
     * (honorifics, "feast of", parentheticals and the like ignored) —
     * e.g. feed "Simon" matches bundled "Saints Simon and Jude".
     */
    private fun namesMatch(feedName: String, bundledName: String): Boolean {
        val ignored = setOf(
            "saint", "saints", "st", "the", "of", "and", "feast", "solemnity",
            "commemoration", "day", "blessed", "venerable"
        )
        fun tokens(text: String): Set<String> =
            text.lowercase()
                .replace(Regex("""\([^)]*\)"""), " ")
                .split(Regex("[^a-z0-9]+"))
                .filter { it.isNotEmpty() && it !in ignored }
                .toSet()
        val feedTokens = tokens(feedName)
        val bundledTokens = tokens(bundledName)
        if (feedTokens.isEmpty() || bundledTokens.isEmpty()) return false
        val shared = feedTokens.intersect(bundledTokens).size
        return shared * 2 >= minOf(feedTokens.size, bundledTokens.size)
    }
    /**
     * Today's verse: rotates by day-of-year over
     * [DailyVerseRotation.REFERENCES] so it *changes* daily without stored
     * state. Text is resolved from the bundled Bible (the same source as the
     * lectionary passages) in all three languages; a verse that cannot be
     * resolved falls back to the curated trilingual text so the card never
     * renders blank. Home displays this bundled text unchanged so the exact
     * verse link opens the same passage and edition in the reader.
     */
    val verseOfTheDay: VerseOfTheDay
        get() = dailyVerseFor(dateProvider())

    fun dailyVerseFor(date: CalendarDate): VerseOfTheDay {
        val key = date to _currentLanguage.value
        verseCache[key]?.let { return it }
        val referenceId = DailyVerseRotation.referenceFor(
            java.time.LocalDate.of(date.year, date.month, date.day)
        )
        val bundled = DevotionsAndHistoryData.verseOfTheDay
        val texts = AppLanguage.entries.associateWith { language ->
            scriptureResolver.passage(referenceId, language) ?: bundled.text.get(language)
        }
        val text = com.example.data.models.LocalizedText(
            texts.getValue(AppLanguage.ES),
            texts.getValue(AppLanguage.EN),
            texts.getValue(AppLanguage.PT)
        )
        return VerseOfTheDay(
            reference = scriptureResolver.display(referenceId, _currentLanguage.value),
            text = text,
            meditation = DailyVerseMeditation.meditation,
            spiritualChallenge = DailyVerseMeditation.challenge
        ).also {
            if (verseCache.size > 6) verseCache.clear()
            verseCache[key] = it
        }
    }
    val marianApparitions: List<MarianApparition> get() = DevotionsAndHistoryData.marianApparitions
    val christManifestations: List<ChristManifestation> get() = DevotionsAndHistoryData.christManifestations

    // Doctrine & Church History
    val churchHistoryEpochs: List<ChurchHistoryEpoch> get() = DevotionsAndHistoryData.churchHistoryEpochs
    val churchSchisms: List<ChurchSchismHistory> get() = DevotionsAndHistoryData.churchSchisms
    val theTenCommandments: List<CommandmentExploration> get() = DoctrineAndSpiritualData.theTenCommandments
    val churchPrecepts: List<ChurchPrecept> get() = DoctrineAndSpiritualData.churchPrecepts
    val capitalSinsVsVirtues: List<CapitalSinVsVirtue> get() = DoctrineAndSpiritualData.capitalSinsVsVirtues
    val sinTheology: SinTheologyExplanation get() = DoctrineAndSpiritualData.sinTheology

    // Spiritual Life & Additions
    val gregorianChants: List<GregorianChantItem> get() = DoctrineAndSpiritualData.gregorianChants
    val viaCrucisStations: List<ViaCrucisStation> get() = DoctrineAndSpiritualData.viaCrucisStations
    val triviaQuestions: List<TriviaQuestion> get() = DoctrineAndSpiritualData.triviaQuestions
    val nearbyParishes: List<NearbyParish> get() = DoctrineAndSpiritualData.nearbyParishes
    val catholicNews: List<CatholicNewsArticle> get() = DoctrineAndSpiritualData.catholicNews

    // Catechism of the Catholic Church (CCC) Full Index & Compendium
    val catechismParts: List<com.example.data.models.CatechismPart> get() = CatechismData.catechismParts
    fun findCatechismArticle(num: Int) = CatechismData.findArticleByNumber(num)
    fun searchCatechismArticles(query: String) = CatechismData.searchArticles(query)
    /**
     * Full text of CCC paragraph [number] in [language]; read directly from
     * [CatechismFullData], falling back to the article content (curated or
     * synthesized) so a blank localized field still resolves to filled text.
     * Returns null when no paragraph exists for [number].
     */
    fun catechismParagraphText(number: Int, language: AppLanguage): String? {
        val direct = CatechismFullData.contentByNumber[number]?.get(language)
        if (!direct.isNullOrBlank()) return direct
        return findCatechismArticle(number)?.content?.get(language)?.takeIf { it.isNotBlank() }
    }
    val compendiumQAEntries: List<CompendiumQA> get() = CatechismCompendiumData.keyCompendiumEntries
    val compendiumPartTitles: List<com.example.data.models.LocalizedText> get() = CatechismCompendiumData.compendiumPartTitles
    val doctrineFormulas: List<DoctrineFormula> get() = CatechismCompendiumData.doctrineFormulas
    /** The Didache (Doctrine of the Twelve Apostles), 16 chapters in ES/EN/PT. */
    val didacheChapters: List<com.example.data.models.DidacheChapter> get() = DidacheData.chapters
    val didacheIntro: com.example.data.models.LocalizedText get() = DidacheData.intro
    /** 101 Catholic Questions (apologetics Q&A) in ES/EN/PT. */
    val catholicQuestions: List<com.example.data.models.ApologeticsQuestion> get() = CatholicQuestionsData.questions
    /** Religious orders compendium + Church offices guide in ES/EN/PT. */
    val religiousOrders: List<com.example.data.models.ReligiousOrder> get() = ReligiousOrdersData.orders
    val churchOffices: List<com.example.data.models.ChurchOffice> get() = ReligiousOrdersData.offices
    val officialVaticanLinks: List<CatechismData.OfficialVaticanLink> get() = CatechismData.officialVaticanLinks

    /** A browsable index reaching all 2,865 paragraphs of the Catechism. */
    val catechismOutline: List<CatechismOutlineSection> get() = CatechismOutline.sections
    val catechismParagraphCount: Int get() = CatechismOutline.totalParagraphs
    fun catechismSectionFor(number: Int) = CatechismOutline.sectionFor(number)
    fun getCompendiumQA(number: Int) = CatechismCompendiumData.getCompendiumQA(number)
    fun searchCompendium(query: String) = CatechismCompendiumData.searchCompendium(query)

    // Flagship Additions: Pilgrimages, Habits, Calendar
    val globalPilgrimages: List<com.example.data.models.CatholicPilgrimageSanctuary> get() = PilgrimageAndHabitsData.globalPilgrimages
    val spiritualHabitsList: List<com.example.data.models.SpiritualHabitItem> get() = PilgrimageAndHabitsData.spiritualHabitsList
    /**
     * Today, then the next four weeks' Sundays, feasts, solemnities and days
     * of fast or abstinence, computed from the calendar rather than seeded.
     */
    val liturgicalCalendarDays: List<com.example.data.models.LiturgicalCalendarDay>
        get() {
            val today = dateProvider()
            return (0 until 28).map { today.plusDays(it) }.filter { date ->
                val info = LiturgicalCalendar.dayInfo(date, _calendarRules.value)
                date == today || date.isSunday || info.isHolyDayOfObligation ||
                    info.isFastDay || info.isAbstinenceDay && !date.isFriday ||
                    info.rank == CelebrationRank.SOLEMNITY || info.rank == CelebrationRank.FEAST ||
                    info.rank == CelebrationRank.COMMEMORATION
            }.map { getLiturgicalDay(it) }
        }
    val defaultHabitsToSeed: List<com.example.data.local.SpiritualHabitEntity> get() = PilgrimageAndHabitsData.defaultHabitsToSeed
    val defaultLiturgicalDaysToSeed: List<com.example.data.local.LiturgicalDayEntity> get() = PilgrimageAndHabitsData.defaultLiturgicalDaysToSeed
    val sinAvoiderCatalog: List<com.example.data.models.SinAvoiderOption> get() = PilgrimageAndHabitsData.sinAvoiderCatalog

    /**
     * Today's liturgical day, computed from the date rather than read from a
     * seeded list, so it stays correct in any year.
     */
    fun getCurrentLiturgicalDay(): com.example.data.models.LiturgicalCalendarDay =
        getLiturgicalDay(dateProvider())

    fun getLiturgicalDay(date: CalendarDate, includeText: Boolean = true): com.example.data.models.LiturgicalCalendarDay {
        val info = LiturgicalCalendar.dayInfo(date, _calendarRules.value)
        val monthNames = listOf(
            Triple("Enero", "January", "Janeiro"), Triple("Febrero", "February", "Fevereiro"),
            Triple("Marzo", "March", "Março"), Triple("Abril", "April", "Abril"),
            Triple("Mayo", "May", "Maio"), Triple("Junio", "June", "Junho"),
            Triple("Julio", "July", "Julho"), Triple("Agosto", "August", "Agosto"),
            Triple("Septiembre", "September", "Setembro"), Triple("Octubre", "October", "Outubro"),
            Triple("Noviembre", "November", "Novembro"), Triple("Diciembre", "December", "Dezembro")
        )
        val month = monthNames[info.date.month - 1]
        val reading = LectionaryData.entryFor(info.lectionaryKey, info.sundayCycle)

        val fasting = when {
            info.isFastDay && info.isAbstinenceDay -> LocalizedText(
                "Disciplina romana de ayuno y abstinencia: edad y normas locales aplicables", "Roman fasting and abstinence discipline: age and local rules apply", "Disciplina romana de jejum e abstinência: aplicam-se idade e normas locais"
            )
            info.isAbstinenceDay -> LocalizedText(
                "Abstinencia: consulta las normas locales", "Abstinence: check local norms", "Abstinência: consulte as normas locais"
            )
            info.isHolyDayOfObligation -> LocalizedText(
                "Domingo: día de la Eucaristía", "Sunday: the day of the Eucharist", "Domingo: dia da Eucaristia"
            )
            else -> null
        }

        return com.example.data.models.LiturgicalCalendarDay(
            dayOfMonth = info.date.day,
            monthName = when (_currentLanguage.value) {
                AppLanguage.ES -> month.first
                AppLanguage.EN -> month.second
                AppLanguage.PT -> month.third
            },
            liturgicalColorHex = info.color.hex,
            seasonName = info.season.displayName,
            celebrationTitle = info.title,
            rank = info.rank.displayName,
            fastingRule = fasting,
            gospelVerseRef = reading?.gospel?.let { scriptureResolver.display(it, _currentLanguage.value) } ?: "",
            sundayCycle = LocalizedText(
                "Ciclo ${info.sundayCycle.name}", "Year ${info.sundayCycle.name}", "Ano ${info.sundayCycle.name}"
            ),
            weekdayCycle = info.weekdayCycle.displayName,
            isFastOrAbstinence = info.isFastDay || info.isAbstinenceDay,
            missalMeditation = if (!includeText) null else reading?.gospel?.let { scriptureResolver.passage(it, _currentLanguage.value) }
                ?.let { LocalizedText(it, it, it) }
        )
    }



    // Vatican Scripture Study Course
    val scriptureStudyTracks: List<com.example.data.models.ScriptureCourseTrack>
        get() = listOf(CatholicBibleCourseData.track) + ScriptureStudyData.tracks

    /** Study citations always resolve against the included edition, independent of optional downloads. */
    fun studyScriptureText(lesson: com.example.data.models.ScriptureLesson, language: AppLanguage): String {
        if (lesson.scriptureReferences.isEmpty()) return lesson.biblicalPassageText.get(language)
        val passages = lesson.scriptureReferences.map { raw ->
            val text = scriptureResolver.passage(raw, language) ?: return ""
            "${scriptureResolver.display(raw, language)}\n$text"
        }
        return passages.joinToString("\n\n")
    }

    fun studyScriptureLabel(raw: String, language: AppLanguage): String = scriptureResolver.display(raw, language)

    fun referenceLabel(reference: com.example.data.liturgy.ScriptureReference, language: AppLanguage): String =
        scriptureResolver.display(reference, language)

    fun referenceVerses(reference: com.example.data.liturgy.ScriptureReference, language: AppLanguage) =
        scriptureResolver.verses(reference, language)


    fun studyBibleEdition(language: AppLanguage): String = when (language) {
        AppLanguage.ES -> "Biblia Platense · edición incluida"
        AppLanguage.EN -> "Douay-Rheims-Challoner · included edition"
        AppLanguage.PT -> "Matos Soares · edição incluída"
    }

    // Official Vatican Night Prayer (Compline & Examen)
    val complineOffice: com.example.data.models.ComplineOffice get() = NightPrayerData.complineOffice
    val ignatianExamenSteps: List<com.example.data.models.IgnatianExamenStep> get() = NightPrayerData.ignatianExamenSteps

    // ------------------------------------------------------- cross-references

    /** A cross-reference as the reader shows it: label and opening words. */
    data class ResolvedCrossReference(
        val reference: CrossReference,
        val label: String,
        val preview: String
    )

    /** References from one verse, strongest first, with their text in [language]. */
    fun crossReferences(
        bookId: String,
        chapter: Int,
        verse: Int,
        language: AppLanguage = _currentLanguage.value,
        limit: Int = 60
    ): List<ResolvedCrossReference> {
        val index = crossReferenceIndex ?: return emptyList()
        return index.referencesFrom(VerseRef(bookId, chapter, verse), language, limit).map { ref ->
            ResolvedCrossReference(ref, referenceLabel(ref, language), referenceText(ref, language))
        }
    }

    /** How many references leave each verse of a chapter. */
    fun crossReferenceCounts(bookId: String, chapter: Int, language: AppLanguage = _currentLanguage.value): Map<Int, Int> =
        crossReferenceIndex?.countsForChapter(bookId, chapter, language) ?: emptyMap()

    /** Every connection in the Bible, for the whole-Bible arc view. */
    fun allCrossReferences(language: AppLanguage = _currentLanguage.value, minVotes: Int = 0): Sequence<Pair<VerseRef, VerseRef>> =
        crossReferenceIndex?.allConnections(language, minVotes) ?: emptySequence()

    /** The whole canon laid on one line, in [language]'s own numbering. */
    fun bibleLine(language: AppLanguage = _currentLanguage.value): BibleLine? {
        bibleLines[language]?.let { return it }
        val counts = bibleProvider?.verseCounts(language)?.takeIf { it.isNotEmpty() } ?: return null
        return BibleLine(counts, canonIds).also { bibleLines[language] = it }
    }

    private fun referenceLabel(ref: CrossReference, language: AppLanguage): String {
        val book = BibleData.catholicCanonBooks.firstOrNull { it.id == ref.target.bookId }
            ?.name?.get(language) ?: ref.target.bookId
        val sep = if (language == AppLanguage.EN) ":" else ","
        val start = "${ref.target.chapter}$sep${ref.target.verse}"
        val end = ref.end?.let { e ->
            if (e.chapter == ref.target.chapter) "\u2013${e.verse}" else "\u2013${e.chapter}$sep${e.verse}"
        } ?: ""
        return "$book $start$end"
    }

    private fun referenceText(ref: CrossReference, language: AppLanguage): String {
        val t = ref.target
        val verses = bibleProvider?.getVerses(t.bookId, t.chapter, language) ?: return ""
        val last = ref.end?.takeIf { it.chapter == t.chapter }?.verse ?: t.verse
        return verses.filter { it.number in t.verse..last }
            .joinToString(" ") { it.text.get(language) }
    }
}
