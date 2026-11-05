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
import com.example.data.models.ViaCrucisStation
import com.example.data.liturgy.CalendarDate
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar
import java.util.Locale

class CatholicDataRepository(
    private val context: Context? = null,
    private val preferencesStore: UserPreferencesStore? = null,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
) {

    private val bibleProvider: BibleTextProvider? = context?.let { BibleTextProvider(it) }
    val bibleDownloadManager: BibleDownloadManager? = context?.let { BibleDownloadManager(it) }

    private val canonIds: List<String> by lazy { BibleData.catholicCanonBooks.map { it.id } }
    private val crossReferenceIndex: CrossReferenceIndex? by lazy {
        context?.let { CrossReferenceIndex(it, canonIds) }
    }
    private val bibleLines = HashMap<AppLanguage, BibleLine>()

    private val _currentLanguage = MutableStateFlow(detectInitialLanguage())
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

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
        if (changed) refreshSaintOfTheDay(language)
    }

    /** Applies a language restored from storage without writing it back. */
    fun applyPersistedLanguage(language: AppLanguage) {
        val changed = _currentLanguage.value != language
        _currentLanguage.value = language
        if (changed) refreshSaintOfTheDay(language)
    }

    // Bible & Liturgical Mass
    val catholicBooks: List<BibleBook> get() = BibleData.catholicCanonBooks
    val bibleVersions: List<BibleVersionInfo> get() = BibleData.bibleVersions
    val dailyMassReading: DailyMassReading get() = getMassReading(LiturgicalCalendar.today())

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
        val info = LiturgicalCalendar.dayInfo(date)
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
            responsorialPsalmRef = citation(entry?.responsorialPsalm),
            responsorialPsalmText = passage(entry?.responsorialPsalm) ?: empty,
            secondReadingRef = entry?.secondReading?.let { citation(it) },
            secondReadingText = passage(entry?.secondReading),
            gospelRef = citation(entry?.gospel),
            gospelText = passage(entry?.gospel) ?: empty,
            homilyReflection = fallback.homilyReflection,
            saintOfFeast = info.title,
            sourceName = "Lectionary for Mass · Douay-Rheims",
            sourceUrl = fallback.sourceUrl
        )
    }

    fun getChapter(
        bookId: String,
        chapterNumber: Int,
        language: AppLanguage = _currentLanguage.value
    ): BibleChapter {
        val verses = bibleProvider?.getVerses(bookId, chapterNumber, language)
            ?: BibleData.getVersesForBookAndChapter(bookId, chapterNumber)
        return BibleChapter(
            bookId = bookId,
            chapterNumber = chapterNumber,
            verses = verses
        )
    }

    /** Chapters actually available for [bookId], falling back to the declared count. */
    fun getChapterCount(bookId: String, language: AppLanguage = _currentLanguage.value): Int {
        val fromAssets = bibleProvider?.chapterCount(bookId, language) ?: 0
        if (fromAssets > 0) return fromAssets
        return BibleData.catholicCanonBooks.firstOrNull { it.id == bookId }?.chaptersCount ?: 0
    }

    fun searchBible(
        query: String,
        limit: Int = 50,
        language: AppLanguage = _currentLanguage.value
    ): List<Triple<String, Int, com.example.data.models.BibleVerse>> {
        return bibleProvider?.search(query, language, limit) ?: emptyList()
    }

    // Rosary & Divine Mercy
    fun getTodayRosaryMystery(): RosaryMystery {
        val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        return when (dayOfWeek) {
            Calendar.MONDAY, Calendar.SATURDAY -> PrayerAndNovenaData.joyfulMysteries
            Calendar.TUESDAY, Calendar.FRIDAY -> PrayerAndNovenaData.sorrowfulMysteries
            Calendar.WEDNESDAY, Calendar.SUNDAY -> PrayerAndNovenaData.gloriousMysteries
            Calendar.THURSDAY -> PrayerAndNovenaData.luminousMysteries
            else -> PrayerAndNovenaData.joyfulMysteries
        }
    }

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
    private val _saintOfTheDay = MutableStateFlow(DevotionsAndHistoryData.getTodaySaint())
    val saintOfTheDayFlow: StateFlow<SaintOfTheDay> = _saintOfTheDay.asStateFlow()

    private var saintFetchJob: kotlinx.coroutines.Job? = null

    init {
        refreshSaintOfTheDay(_currentLanguage.value)
    }

    /**
     * (Re)fetches today's saint in [language] and pushes it into
     * [_saintOfTheDay]. A still-running fetch is cancelled so an older
     * language cannot overwrite a newer one.
     */
    private fun refreshSaintOfTheDay(language: AppLanguage) {
        saintFetchJob?.cancel()
        saintFetchJob = scope.launch {
            val bundled = DevotionsAndHistoryData.getTodaySaint()
            // 1. Vatican News (trilingual, primary).
            DailySaintsFetcher().fetchVaticanSaint(language)?.let { vatican ->
                _saintOfTheDay.value = mapVaticanSaint(vatican, bundled, language)
                return@launch
            }
            // 2. Daily-saints API (English fallback).
            val day = DailySaintsFetcher().fetchToday()
            if (day != null) {
                _saintOfTheDay.value = mapDailySaints(day, bundled)
            } else {
                // 3. Bundled entry as the last resort.
                _saintOfTheDay.value = bundled
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
    internal fun mapVaticanSaint(vatican: VaticanSaint, bundled: SaintOfTheDay, language: AppLanguage): SaintOfTheDay {
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
        val today = Calendar.getInstance()
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
    val verseOfTheDay: VerseOfTheDay
        get() = DevotionsAndHistoryData.verseOfTheDay.let {
            it.copy(reference = scriptureResolver.display(it.reference, _currentLanguage.value))
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
            val today = LiturgicalCalendar.today()
            return (0 until 28).map { today.plusDays(it) }.filter { date ->
                val info = LiturgicalCalendar.dayInfo(date)
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
        getLiturgicalDay(LiturgicalCalendar.today())

    fun getLiturgicalDay(date: CalendarDate): com.example.data.models.LiturgicalCalendarDay {
        val info = LiturgicalCalendar.dayInfo(date)
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
                "Ayuno y abstinencia de carne", "Fast and abstinence from meat", "Jejum e abstinência de carne"
            )
            info.isAbstinenceDay -> LocalizedText(
                "Abstinencia de carne", "Abstinence from meat", "Abstinência de carne"
            )
            info.isHolyDayOfObligation -> LocalizedText(
                "Precepto de Santa Misa", "Mass obligation", "Preceito da Santa Missa"
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
            missalMeditation = reading?.gospel?.let { scriptureResolver.passage(it, _currentLanguage.value) }
                ?.let { LocalizedText(it, it, it) }
        )
    }

    /** Resolves lectionary citations against the bundled Bible. */
    private val scriptureResolver = ScriptureResolver(
        bookName = { bookId, language ->
            BibleData.catholicCanonBooks.firstOrNull { it.id == bookId }?.name?.get(language)
        },
        versesOf = { bookId, chapter, language ->
            bibleProvider?.getVerses(bookId, chapter, language) ?: emptyList()
        }
    )

    // Vatican Scripture Study Course
    val scriptureStudyTracks: List<com.example.data.models.ScriptureCourseTrack> get() = ScriptureStudyData.tracks

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
