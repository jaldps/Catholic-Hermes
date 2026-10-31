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
import com.example.data.liturgy.LectionaryData
import com.example.data.liturgy.CelebrationRank
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.ScriptureResolver
import com.example.data.models.LocalizedText
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
        _currentLanguage.value = language
        preferencesStore?.let { store ->
            scope.launch { store.setLanguage(language) }
        }
    }

    /** Applies a language restored from storage without writing it back. */
    fun applyPersistedLanguage(language: AppLanguage) {
        _currentLanguage.value = language
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
    val compendiumQAEntries: List<CompendiumQA> get() = CatechismCompendiumData.keyCompendiumEntries
    val compendiumPartTitles: List<com.example.data.models.LocalizedText> get() = CatechismCompendiumData.compendiumPartTitles
    val doctrineFormulas: List<DoctrineFormula> get() = CatechismCompendiumData.doctrineFormulas
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
                    info.rank == CelebrationRank.SOLEMNITY || info.rank == CelebrationRank.FEAST
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
                "Ciclo ${info.sundayCycle.name}", "Cycle ${info.sundayCycle.name}", "Ciclo ${info.sundayCycle.name}"
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
}

