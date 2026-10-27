package com.example.data.repository

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar
import java.util.Locale

class CatholicDataRepository {

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

    fun setLanguage(language: AppLanguage) {
        _currentLanguage.value = language
    }

    // Bible & Liturgical Mass
    val catholicBooks: List<BibleBook> get() = BibleData.catholicCanonBooks
    val bibleVersions: List<BibleVersionInfo> get() = BibleData.bibleVersions
    val dailyMassReading: DailyMassReading get() = BibleData.todayMassReading

    fun getChapter(bookId: String, chapterNumber: Int): BibleChapter {
        return BibleChapter(
            bookId = bookId,
            chapterNumber = chapterNumber,
            verses = BibleData.getVersesForBookAndChapter(bookId, chapterNumber)
        )
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
    val verseOfTheDay: VerseOfTheDay get() = DevotionsAndHistoryData.verseOfTheDay
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
    fun getCompendiumQA(number: Int) = CatechismCompendiumData.getCompendiumQA(number)
    fun searchCompendium(query: String) = CatechismCompendiumData.searchCompendium(query)

    // Catholic Canon Apologetics & History (73 vs 66 Books)
    val canonDeuterocanonicalDetails: List<BibleData.DeuterocanonicalBookDetail> get() = BibleData.deuterocanonicalBooksDetail
    val canonHistoricalMilestones: List<BibleData.CanonHistoricalMilestone> get() = BibleData.canonHistoricalMilestones

    // Flagship Additions: Pilgrimages, Habits, Calendar
    val globalPilgrimages: List<com.example.data.models.CatholicPilgrimageSanctuary> get() = PilgrimageAndHabitsData.globalPilgrimages
    val spiritualHabitsList: List<com.example.data.models.SpiritualHabitItem> get() = PilgrimageAndHabitsData.spiritualHabitsList
    val liturgicalCalendarDays: List<com.example.data.models.LiturgicalCalendarDay> get() = PilgrimageAndHabitsData.upcomingLiturgicalDays
    val defaultHabitsToSeed: List<com.example.data.local.SpiritualHabitEntity> get() = PilgrimageAndHabitsData.defaultHabitsToSeed
    val defaultLiturgicalDaysToSeed: List<com.example.data.local.LiturgicalDayEntity> get() = PilgrimageAndHabitsData.defaultLiturgicalDaysToSeed
    val sinAvoiderCatalog: List<com.example.data.models.SinAvoiderOption> get() = PilgrimageAndHabitsData.sinAvoiderCatalog

    fun getCurrentLiturgicalDay(): com.example.data.models.LiturgicalCalendarDay {
        return PilgrimageAndHabitsData.upcomingLiturgicalDays.first()
    }

    // Vatican Scripture Study Course
    val scriptureStudyTracks: List<com.example.data.models.ScriptureCourseTrack> get() = ScriptureStudyData.tracks

    // Official Vatican Night Prayer (Compline & Examen)
    val complineOffice: com.example.data.models.ComplineOffice get() = NightPrayerData.complineOffice
    val ignatianExamenSteps: List<com.example.data.models.IgnatianExamenStep> get() = NightPrayerData.ignatianExamenSteps
}

