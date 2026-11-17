package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.SaveAlt
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.sharp.Palette
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.layout.layout
import com.example.ui.design.LocalScreenFrame
import com.example.ui.design.ScreenFrame
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.GuiCustomizerModal
import com.example.ui.components.FontPickerDialog
import com.example.data.models.FontChoice
import com.example.ui.components.LanguageSelectorDialog
import androidx.compose.material.icons.outlined.DateRange
import com.example.ui.components.CalendarRulesSheet
import com.example.data.liturgy.SeasonalMarianPrayer
import com.example.data.search.GlobalSearch
import com.example.data.search.SearchSection
import com.example.data.search.SearchTarget
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppIconSlot
import com.example.ui.design.AppIcons
import com.example.ui.design.AppNavItem
import com.example.ui.design.AppScaffold
import com.example.ui.screens.BibleScreen
import com.example.ui.screens.CatechismScreen
import com.example.ui.screens.DoctrineAndHistoryScreen
import com.example.ui.screens.HabitsAndVirtuesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MoreEntry
import com.example.ui.screens.MyPathHub
import com.example.ui.screens.MyPathSection
import com.example.ui.screens.MyPathSwitcher
import com.example.ui.screens.SpiritualFocus
import com.example.ui.screens.MoreScreen
import com.example.ui.screens.MoreSection
import com.example.ui.screens.ParishFinderScreen
import com.example.ui.screens.PilgrimageAndHabitsScreen
import com.example.ui.screens.PrayersAndNovenasScreen
import com.example.ui.screens.RemindersScreen
import com.example.ui.screens.RosaryScreen
import com.example.ui.screens.SpiritualLifeScreen
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

enum class AppDestination(
    val titleEs: String,
    val titleEn: String,
    val titlePt: String,
    val iconSlot: AppIconSlot,
    val tag: String
) {
    HOME("Hoy", "Today", "Hoje", AppIconSlot.HOME, "nav_home"),
    HABITS_VIRTUES("Hábitos y Virtudes", "Habits & Virtues", "Hábitos e Virtudes", AppIconSlot.HABITS, "nav_habits"),
    MY_PATH("Mi camino", "My path", "Meu caminho", AppIconSlot.HABITS, "nav_my_path"),
    BIBLE("Leer", "Read", "Ler", AppIconSlot.BIBLE, "nav_bible"),
    CATECHISM("Catecismo", "Catechism", "Catecismo", AppIconSlot.CATECHISM, "nav_catechism"),
    ROSARY("Rosario", "Rosary", "Terço", AppIconSlot.ROSARY, "nav_rosary"),
    PRAYERS("Orar", "Pray", "Orar", AppIconSlot.PRAYERS, "nav_prayers"),
    PILGRIMAGE_HABITS("Calendario y Santuarios", "Calendar & Shrines", "Calendário e Santuários", AppIconSlot.PILGRIMAGE, "nav_pilgrimage"),
    DOCTRINE("Doctrina e Historia", "Doctrine & History", "Doutrina e História", AppIconSlot.DOCTRINE, "nav_doctrine"),
    PARISHES("Parroquias", "Parishes", "Paróquias", AppIconSlot.PARISH, "nav_parishes"),
    CHURCHES_NEWS("Noticias", "News", "Notícias", AppIconSlot.NEWS, "nav_churches_news"),
    QUESTIONS("101 preguntas sobre la fe católica", "101 Questions on the Catholic Faith", "101 Perguntas sobre a fé católica", AppIconSlot.DOCTRINE, "nav_questions"),
    STUDY_DOCTRINE("Doctrina", "Doctrine", "Doutrina", AppIconSlot.DOCTRINE, "nav_study_doctrine"),
    STUDY_MARY_JESUS("María y Jesús", "Mary & Jesus", "Maria e Jesus", AppIconSlot.DOCTRINE, "nav_study_mary_jesus"),
    STUDY_HISTORY("Historia", "History", "História", AppIconSlot.DOCTRINE, "nav_study_history"),
    SAINTS("Santos", "Saints", "Santos", AppIconSlot.DOCTRINE, "nav_saints"),
    STUDY_ORDERS("Órdenes y oficios", "Orders & Offices", "Ordens e ofícios", AppIconSlot.DOCTRINE, "nav_study_orders"),
    SPIRITUAL_LIFE("Vida espiritual", "Spiritual Life", "Vida espiritual", AppIconSlot.JOURNAL, "nav_spiritual"),
    CONFESSION("Confesión", "Confession", "Confissão", AppIconSlot.PRAYERS, "nav_confession"),
    PLANS("Planes de lectura", "Reading plans", "Planos de leitura", AppIconSlot.BIBLE, "nav_plans"),
    SEARCH("Buscar", "Search", "Buscar", AppIconSlot.MORE, "nav_search"),
    REMINDERS("Recordatorios", "Reminders", "Lembretes", AppIconSlot.HABITS, "nav_reminders"),
    BEGINNER("Primeros pasos", "Start here", "Primeiros passos", AppIconSlot.DOCTRINE, "nav_beginner"),
    MORE("Explorar", "Explore", "Explorar", AppIconSlot.MORE, "nav_more");

    companion object {
        /**
         * The destinations «Mi camino» absorbed, and the section each one opens. They keep existing as names for deep
         * links, the global search, welcome suggestions and notices, but never as a screen of their own.
         */
        fun myPathSectionFor(destination: AppDestination): MyPathSection? = when (destination) {
            MY_PATH -> MyPathSection.HUB
            HABITS_VIRTUES -> MyPathSection.HABITS
            SPIRITUAL_LIFE -> MyPathSection.DIARY
            else -> null
        }

        /**
         * What the tab bar carries. Habits sits next to Prayers as a
         * first-class tab, and [MORE] (with Settings) is the sixth tab;
         * everything else is reached by pushing from a tab.
         */
        val tabs = listOf(HOME, PRAYERS, BIBLE, MORE)
    }

    fun title(language: AppLanguage): String = when (language) {
        AppLanguage.ES -> titleEs
        AppLanguage.EN -> titleEn
        AppLanguage.PT -> titlePt
    }

    /**
     * Short label for the tab bar only. [title] is too long for a tab
     * ("Habits & Virtues" truncates), so tab destinations carry a compact
     * label; non-tab destinations fall back to [title] and never appear in
     * the tab bar. Screen titles and back labels keep using [title].
     */
    fun tabLabel(language: AppLanguage): String = when (this) {
        HOME -> when (language) {
            AppLanguage.ES -> "Hoy"
            AppLanguage.EN -> "Today"
            AppLanguage.PT -> "Hoje"
        }
        BIBLE -> when (language) {
            AppLanguage.ES -> "Leer"
            AppLanguage.EN -> "Read"
            AppLanguage.PT -> "Ler"
        }
        ROSARY -> when (language) {
            AppLanguage.ES -> "Rosario"
            AppLanguage.EN -> "Rosary"
            AppLanguage.PT -> "Terço"
        }
        PRAYERS -> when (language) {
            AppLanguage.ES -> "Orar"
            AppLanguage.EN -> "Pray"
            AppLanguage.PT -> "Orar"
        }
        HABITS_VIRTUES -> when (language) {
            AppLanguage.ES -> "Hábitos"
            AppLanguage.EN -> "Habits"
            AppLanguage.PT -> "Hábitos"
        }
        MORE -> when (language) {
            AppLanguage.ES -> "Explorar"
            AppLanguage.EN -> "Explore"
            AppLanguage.PT -> "Explorar"
        }
        else -> title(language)
    }
}

@Composable
fun MainApp(
    repository: CatholicDataRepository,
    dao: SanctaFidesDao,
    ttsManager: LiturgicalTtsManager,
    preferencesStore: UserPreferencesStore,
    preferences: UserPreferences,
    initialDestination: AppDestination = AppDestination.HOME,
    initialReminderId: String? = null,
    onReminderConsumed: () -> Unit = {},
    backupService: com.example.data.backup.BackupService? = null,
    onBackupRestored: () -> Unit = {}
) {
    val currentLanguage by repository.currentLanguage.collectAsState()
    val today by repository.currentDate.collectAsState()
    val activeCalendarRules by repository.calendarRules.collectAsState()
    val todayContent by repository.dailyContent.collectAsState()
    val todayReading = todayContent?.takeIf { it.date == today && it.language == currentLanguage && it.rules == activeCalendarRules }?.reading ?: repository.emptyMassReading(today)
    val todayLiturgy = todayContent?.takeIf { it.date == today && it.language == currentLanguage && it.rules == activeCalendarRules }?.liturgicalDay ?: repository.getLiturgicalDay(today, includeText = false)
    // Bundled saint while the feed loads, then the feed's saint for today.
    val saintOfTheDay by repository.saintOfTheDayFlow.collectAsState()
    // A real back stack, saved across rotation and process death: pushing
    // from a pushed screen (101 Questions → Catechism) pops back to it, and
    // the tab bar highlights the tab at the bottom of the stack.
    var currentName by rememberSaveable {
        mutableStateOf(if (AppDestination.myPathSectionFor(initialDestination) != null) AppDestination.MY_PATH.name else initialDestination.name)
    }
    var myPathSection by rememberSaveable {
        mutableStateOf((AppDestination.myPathSectionFor(initialDestination) ?: MyPathSection.HUB).name)
    }
    var stackNames by rememberSaveable {
        mutableStateOf(if (initialDestination in AppDestination.tabs) "" else AppDestination.MORE.name)
    }
    // A stored name of an absorbed destination is the hub: «Mi camino» is the only screen they have.
    fun screenOf(name: String) = AppDestination.valueOf(name).let { if (AppDestination.myPathSectionFor(it) != null) AppDestination.MY_PATH else it }
    val current = screenOf(currentName)
    val screenStateHolder = rememberSaveableStateHolder()
    val stack = stackNames.split(',').filter { it.isNotEmpty() }.map { screenOf(it) }
    val origin = stack.firstOrNull { it in AppDestination.tabs }
        ?: if (current in AppDestination.tabs) current else AppDestination.MORE
    // Deep links that only the global search sets. Every other way into a screen must start from none, so
    // they are cleared by each navigation helper before it (optionally) sets one.
    var searchEntryId by rememberSaveable { mutableIntStateOf(0) }
    var saintsInitialKey by rememberSaveable { mutableStateOf<String?>(null) }
    var studyInitialEntry by rememberSaveable { mutableStateOf<String?>(null) }
    var prayersInitialNovenaId by rememberSaveable { mutableStateOf<String?>(null) }
    var bibleInitialLesson by rememberSaveable { mutableStateOf<String?>(null) }
    var plansInitialId by rememberSaveable { mutableStateOf<String?>(null) }
    fun clearSearchLinks() {
        saintsInitialKey = null; studyInitialEntry = null; prayersInitialNovenaId = null
        bibleInitialLesson = null; plansInitialId = null
    }
    // «Mi camino»: the section on show, the Practices page a deep link opens (0 = the list, 1 quiz, 2 Stations, 3 chant),
    // whether the reader came down from the hub (back returns to it) or arrived by a link (back leaves to the caller),
    // and ids that give every arrival (entryId) and every hub choice (sectionId) a fresh first page.
    var spiritualLifeInitialTab by rememberSaveable { mutableIntStateOf(0) }
    var myPathFromHub by rememberSaveable { mutableStateOf(false) }
    var myPathEntryId by rememberSaveable { mutableIntStateOf(0) }
    var myPathSectionId by rememberSaveable { mutableIntStateOf(0) }
    fun enterMyPath(section: MyPathSection, practicesTab: Int = 0) {
        if (current != AppDestination.MY_PATH) screenStateHolder.removeState("${AppDestination.MY_PATH.name}:$myPathEntryId")
        myPathSection = section.name
        spiritualLifeInitialTab = practicesTab
        myPathFromHub = false
        myPathEntryId++
        myPathSectionId++
    }
    fun go(destination: AppDestination) {
        // Hábitos y Virtudes and Vida espiritual are sections of «Mi camino» now; opening either by name lands there.
        val absorbed = AppDestination.myPathSectionFor(destination)
        if (absorbed != null && destination != AppDestination.MY_PATH) enterMyPath(absorbed)
        val target = if (absorbed != null) AppDestination.MY_PATH else destination
        if (target == current) return
        clearSearchLinks()
        stackNames = if (target in AppDestination.tabs) "" else (stack + current).joinToString(",") { it.name }
        currentName = target.name
    }
    fun openHabits(temptations: Boolean = false) {
        enterMyPath(if (temptations) MyPathSection.TEMPTATIONS else MyPathSection.HABITS)
        go(AppDestination.MY_PATH)
    }
    /** Home's shortcuts: 0 the journal, 1 the faith quiz, 2 the Way of the Cross, 3 Gregorian chant. */
    fun openSpiritual(tab: Int) {
        enterMyPath(if (tab == 0) MyPathSection.DIARY else MyPathSection.PRACTICES, practicesTab = tab)
        go(AppDestination.MY_PATH)
    }
    var doctrineInitialEntry by rememberSaveable { mutableStateOf<String?>(null) }
    var bibleInitialTab by rememberSaveable { mutableIntStateOf(0) }
    // Deep-link state: set by onOpenBible/onOpenCatechism (Doctrine screen
    // calls these once it exposes them), consumed as the initial params of
    // BibleScreen/CatechismScreen.
    var bibleInitialBook by rememberSaveable { mutableStateOf<String?>(null) }
    var bibleInitialChapter by rememberSaveable { mutableStateOf<Int?>(null) }
    var bibleInitialVerse by rememberSaveable { mutableStateOf<Int?>(null) }
    var catechismInitialParagraph by rememberSaveable { mutableStateOf<Int?>(null) }
    val onOpenBible: (bookId: String, chapter: Int, verseStart: Int?) -> Unit =
        { bookId, chapter, verseStart ->
            clearSearchLinks()
            bibleInitialBook = bookId
            bibleInitialChapter = chapter
            bibleInitialVerse = verseStart
            bibleInitialTab = 0
            // References are contextual pushes, even though Read is also a tab.
            stackNames = (stack + current).joinToString(",") { it.name }
            currentName = AppDestination.BIBLE.name
        }
    val onOpenCatechism: (paragraph: Int) -> Unit = { paragraph ->
        catechismInitialParagraph = paragraph
        go(AppDestination.CATECHISM)
    }
    var prayersInitialPrayerId by rememberSaveable { mutableStateOf<String?>(null) }
    var prayersInitialTab by rememberSaveable { mutableIntStateOf(0) }
    var prayersInitialNightTab by rememberSaveable { mutableIntStateOf(0) }
    var prayersInitialOpenChaplet by rememberSaveable { mutableStateOf(false) }
    // Deep link from Home's "Pregunta del día": the 0-based index of the
    // question whose answer opens directly (DoctrineAndHistoryScreen
    // initialQuestionIndex). Cleared on the next direct nav.
    var questionsInitialIndex by rememberSaveable { mutableStateOf<Int?>(null) }
    var rosaryInitialTab by rememberSaveable { mutableIntStateOf(0) }
    var rosaryEntryId by rememberSaveable { mutableIntStateOf(0) }
    fun openRosary(tab: Int) {
        if (current != AppDestination.ROSARY) screenStateHolder.removeState("${AppDestination.ROSARY.name}:$rosaryEntryId")
        rosaryInitialTab = tab
        // A new entry loads both latest prayer positions from the shared
        // durable session, rather than restoring an older per-devotion copy.
        rosaryEntryId++
        go(AppDestination.ROSARY)
    }
    // Home's "Santuarios" tile opens Calendar & Shrines on its shrines tab
    // (PilgrimageAndHabitsScreen initialTab); every other entry opens the calendar.
    var pilgrimageInitialTab by rememberSaveable { mutableIntStateOf(0) }
    val onOpenPrayer: (prayerId: String) -> Unit = { prayerId ->
        prayersInitialPrayerId = prayerId
        prayersInitialTab = 0
        go(AppDestination.PRAYERS)
    }
    // Global search. It opens with an empty field every time (a new entry id drops the saved state of the last
    // visit); coming back from a result keeps the query. Results open the app's own screens; a screen that is
    // also a tab is pushed on top of the search, so back returns to the results.
    fun openSearch() {
        if (current != AppDestination.SEARCH) screenStateHolder.removeState("${AppDestination.SEARCH.name}:$searchEntryId")
        searchEntryId++
        go(AppDestination.SEARCH)
    }
    fun pushOnSearch(destination: AppDestination) {
        clearSearchLinks()
        stackNames = (stack + current).joinToString(",") { it.name }
        currentName = destination.name
    }
    fun openSection(destination: AppDestination) {
        when (destination) {
            AppDestination.ROSARY -> openRosary(0)
            AppDestination.HABITS_VIRTUES -> openHabits()
            AppDestination.SPIRITUAL_LIFE -> openSpiritual(0)
            AppDestination.MY_PATH -> { enterMyPath(MyPathSection.HUB); go(AppDestination.MY_PATH) }
            AppDestination.BIBLE -> {
                bibleInitialBook = null; bibleInitialChapter = null; bibleInitialVerse = null; bibleInitialTab = 0
                pushOnSearch(destination)
            }
            AppDestination.PRAYERS -> {
                prayersInitialPrayerId = null; prayersInitialTab = 0; prayersInitialNightTab = 0; prayersInitialOpenChaplet = false
                pushOnSearch(destination)
            }
            else -> {
                if (destination == AppDestination.QUESTIONS) questionsInitialIndex = null
                if (destination == AppDestination.CATECHISM) catechismInitialParagraph = null
                if (destination == AppDestination.PILGRIMAGE_HABITS) pilgrimageInitialTab = 0
                if (destination == AppDestination.DOCTRINE) doctrineInitialEntry = null
                go(destination)
            }
        }
    }
    /**
     * «Para ti hoy» and the welcome suggestions. Every reviewed BIBLE suggestion is the Gospel of the day, which lives on the
     * Read tab's daily-Mass page; the other destinations open as the search opens them («Mi camino» sections included).
     */
    fun openSuggestion(destination: AppDestination) {
        when (destination) {
            AppDestination.BIBLE -> {
                bibleInitialBook = null; bibleInitialChapter = null; bibleInitialVerse = null; bibleInitialTab = 1
                go(AppDestination.BIBLE)
            }
            AppDestination.PRAYERS -> {
                prayersInitialPrayerId = null; prayersInitialTab = 0; prayersInitialNightTab = 0; prayersInitialOpenChaplet = false
                go(AppDestination.PRAYERS)
            }
            else -> openSection(destination)
        }
    }
    fun openFromSearch(target: SearchTarget) {
        when (target) {
            is SearchTarget.Prayer -> {
                prayersInitialTab = 0; prayersInitialNightTab = 0; prayersInitialOpenChaplet = false
                pushOnSearch(AppDestination.PRAYERS)
                prayersInitialPrayerId = target.id
            }
            is SearchTarget.Novena -> {
                prayersInitialPrayerId = null; prayersInitialTab = 1; prayersInitialNightTab = 0; prayersInitialOpenChaplet = false
                pushOnSearch(AppDestination.PRAYERS)
                prayersInitialNovenaId = target.id
            }
            is SearchTarget.Saint -> { go(AppDestination.SAINTS); saintsInitialKey = target.key }
            is SearchTarget.Devotion -> { go(AppDestination.STUDY_MARY_JESUS); studyInitialEntry = "devotion:${target.id}" }
            is SearchTarget.Order -> { go(AppDestination.STUDY_ORDERS); studyInitialEntry = "order:${target.index}" }
            is SearchTarget.Office -> { go(AppDestination.STUDY_ORDERS); studyInitialEntry = "office:${target.index}" }
            is SearchTarget.Question -> {
                questionsInitialIndex = null
                go(AppDestination.QUESTIONS)
                studyInitialEntry = "qa:${target.index}"
            }
            is SearchTarget.Lesson -> {
                bibleInitialBook = null; bibleInitialChapter = null; bibleInitialVerse = null; bibleInitialTab = 3
                pushOnSearch(AppDestination.BIBLE)
                bibleInitialLesson = target.lessonId
            }
            is SearchTarget.Catechism -> onOpenCatechism(target.paragraph)
            is SearchTarget.Bible -> onOpenBible(target.bookId, target.chapter, target.verse)
            is SearchTarget.Plan -> { go(AppDestination.PLANS); plansInitialId = target.planId }
            is SearchTarget.Section -> openSection(AppDestination.valueOf(target.destination))
        }
    }
    var showLanguageSelector by remember { mutableStateOf(false) }
    var showCalendarRules by remember { mutableStateOf(false) }
    var showBackup by rememberSaveable { mutableStateOf(false) }
    var showErrorReport by rememberSaveable { mutableStateOf(false) }
    var showPreferenceRecovery by remember { mutableStateOf(false) }
    val preferenceWrites by com.example.data.persistence.PersonalDataCommands.states.collectAsState()
    val failedPreferenceKeys = preferenceWrites.filter { (key, state) -> state.status == com.example.data.persistence.PersonalDataCommands.Status.ERROR }.keys.toList()
    val preferenceFailureRevision = failedPreferenceKeys.maxOfOrNull { preferenceWrites.getValue(it).revision } ?: 0L
    LaunchedEffect(preferences.storageAvailable, preferenceFailureRevision) {
        if (!preferences.storageAvailable || failedPreferenceKeys.isNotEmpty()) showPreferenceRecovery = true
    }
    var showCustomizerModal by remember { mutableStateOf(false) }
    var showFontPicker by remember { mutableStateOf(false) }
    var showJourneyChoice by rememberSaveable { mutableStateOf(false) }
    val journey by com.example.ui.screens.rememberJourney()

    // Screens the search can open by name; the titles are the destinations' own.
    val searchSections = remember(currentLanguage) {
        listOf(
            AppDestination.PRAYERS, AppDestination.ROSARY, AppDestination.BIBLE, AppDestination.PLANS, AppDestination.CATECHISM,
            AppDestination.QUESTIONS, AppDestination.SAINTS, AppDestination.STUDY_DOCTRINE, AppDestination.STUDY_MARY_JESUS,
            AppDestination.STUDY_HISTORY, AppDestination.STUDY_ORDERS, AppDestination.MY_PATH, AppDestination.HABITS_VIRTUES, AppDestination.SPIRITUAL_LIFE,
            AppDestination.CONFESSION, AppDestination.PARISHES, AppDestination.PILGRIMAGE_HABITS, AppDestination.REMINDERS,
            AppDestination.BEGINNER
        ).map { SearchSection(it.name, it.title(currentLanguage)) }
    }
    val design = LocalAppDesign.current
    val tabs = AppDestination.tabs
    val isPushed = stack.isNotEmpty() || current !in tabs

    val compactNavigation = androidx.compose.ui.platform.LocalConfiguration.current.screenWidthDp <= 360
    val largeNavigationText = LocalAppTextStyles.current.navLabel.fontSize.value * androidx.compose.ui.platform.LocalDensity.current.fontScale > 18f
    val navItems = tabs.map { destination ->
        val (resting, active) = AppIcons.pair(destination.iconSlot)
        AppNavItem(
            label = if (destination == AppDestination.MORE && (largeNavigationText || compactNavigation)) when (currentLanguage) {
                AppLanguage.ES -> "Más"; AppLanguage.EN -> "More"; AppLanguage.PT -> "Mais"
            } else destination.tabLabel(currentLanguage),
            icon = resting,
            selectedIcon = active,
            tag = destination.tag
        )
    }

    // Popping a pushed screen returns to the tab it was opened from; popping
    // a non-Home tab returns to Home. System back and the top-bar back share
    // this so the two can never drift apart.
    fun pop() {
        val previous = stack.lastOrNull()
        if (previous != null) {
            stackNames = stack.dropLast(1).joinToString(",") { it.name }
            currentName = previous.name
        } else if (current != AppDestination.HOME) {
            go(AppDestination.HOME)
        }
    }

    // Hoisted above the scaffold so system back is honoured regardless of
    // which identity is in force or whether the scaffold content is composed.
    BackHandler(enabled = current != AppDestination.HOME) { pop() }

    CompositionLocalProvider(com.example.ui.design.LocalPreferencesStore provides preferencesStore) {
    AppScaffold(
        title = current.title(currentLanguage),
        navItems = navItems,
        selectedNavIndex = tabs.indexOf(if (isPushed) origin else current),
        onNavSelected = {
            val dest = tabs[it]
            if (dest == AppDestination.BIBLE) {
                bibleInitialBook = null; bibleInitialChapter = null; bibleInitialVerse = null; bibleInitialTab = 0
            }
            if (dest == AppDestination.PRAYERS) {
                prayersInitialPrayerId = null; prayersInitialTab = 0; prayersInitialNightTab = 0; prayersInitialOpenChaplet = false
            }
            clearSearchLinks()
            stackNames = ""
            currentName = dest.name
        },
        onBack = if (isPushed) ({ pop() }) else null,
        backLabel = if (isPushed) (stack.lastOrNull() ?: origin).title(currentLanguage) else null
        // Appearance and language are settings, listed under More, not two
        // glyphs floating over every screen.
    ) { innerPadding ->
        // Edge-to-edge: the scaffold draws the canvas, the floating dock and
        // an overlaid top bar; innerPadding is what clears the status bar
        // (plus the back row on a pushed screen) and the dock. Each screen
        // gets its own frame: a screen that calls AppEdgeToEdge() draws
        // from the top of the display to the bottom and pads itself with
        // those insets; any other screen is padded here.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
        ) {
            AnimatedContent(
                targetState = current,
                transitionSpec = {
                    val motion = design.motion
                    // Enters are emphasised, exits are standard: M3's
                    // direction-aware pattern. Slides use a fraction of the
                    // width rather than full width so heavy scrollable
                    // screens animate without jank on low-end devices.
                    val enterSlide = tween<IntOffset>(motion.mediumMillis, easing = motion.emphasizedEasing)
                    val exitFade = tween<Float>(motion.fastMillis, easing = motion.standardEasing)
                    when {
                        initialState !in tabs && targetState in tabs -> {
                            // Pop: pushed screen leaves to the right, the
                            // origin tab returns from the left.
                            (slideInHorizontally(
                                initialOffsetX = { -it / 3 },
                                animationSpec = enterSlide
                            ) + fadeIn(exitFade)) togetherWith
                                (slideOutHorizontally(
                                    targetOffsetX = { it / 3 },
                                    animationSpec = enterSlide
                                ) + fadeOut(exitFade))
                        }
                        targetState !in tabs -> {
                            // Push: new screen arrives from the right over a
                            // screen that recedes to the left.
                            (slideInHorizontally(
                                initialOffsetX = { it / 3 },
                                animationSpec = enterSlide
                            ) + fadeIn(exitFade)) togetherWith
                                (slideOutHorizontally(
                                    targetOffsetX = { -it / 3 },
                                    animationSpec = enterSlide
                                ) + fadeOut(exitFade))
                        }
                        else -> {
                            // Tab switch: short directional glide in the
                            // direction of travel, never a full push, so
                            // sibling tabs read as peers rather than a stack.
                            val direction = (
                                tabs.indexOf(targetState) - tabs.indexOf(initialState)
                                ).coerceIn(-1, 1)
                            (slideInHorizontally(
                                initialOffsetX = { direction * it / 8 },
                                animationSpec = tween(motion.fastMillis, easing = motion.standardEasing)
                            ) + fadeIn(exitFade)) togetherWith
                                (slideOutHorizontally(
                                    targetOffsetX = { -direction * it / 8 },
                                    animationSpec = tween(motion.fastMillis, easing = motion.standardEasing)
                                ) + fadeOut(exitFade))
                        }
                    }
                },
                label = "screen_transition"
            ) { target ->
              screenStateHolder.SaveableStateProvider(when (target) {
                  AppDestination.BIBLE -> "${target.name}:$bibleInitialTab:$bibleInitialBook:$bibleInitialChapter:$bibleInitialVerse:$bibleInitialLesson"
                  AppDestination.PRAYERS -> "${target.name}:$prayersInitialTab:$prayersInitialPrayerId:$prayersInitialNightTab:$prayersInitialOpenChaplet:$prayersInitialNovenaId"
                  AppDestination.SEARCH -> "${target.name}:$searchEntryId"
                  AppDestination.SAINTS -> "${target.name}:$saintsInitialKey"
                  AppDestination.STUDY_MARY_JESUS, AppDestination.STUDY_ORDERS -> "${target.name}:$studyInitialEntry"
                  AppDestination.QUESTIONS -> "${target.name}:$questionsInitialIndex:$studyInitialEntry"
                  AppDestination.PLANS -> "${target.name}:$plansInitialId"
                  AppDestination.ROSARY -> "${target.name}:$rosaryEntryId"
                  AppDestination.MY_PATH -> "${target.name}:$myPathEntryId"
                  AppDestination.DOCTRINE -> "${target.name}:$doctrineInitialEntry"
                  else -> target.name
              }) {
              val frame = remember(target) { ScreenFrame() }
              if (frame.insets != innerPadding) frame.insets = innerPadding
              CompositionLocalProvider(LocalScreenFrame provides frame) {
              Box(
                  Modifier
                      .fillMaxSize()
                      .layout { measurable, constraints ->
                          // Read in layout, so a screen that declares itself
                          // edge-to-edge while composing is right on its
                          // first frame.
                          val top = if (frame.edgeToEdge) 0 else innerPadding.calculateTopPadding().roundToPx()
                          val bottom = if (frame.edgeToEdge) 0 else innerPadding.calculateBottomPadding().roundToPx()
                          val inner = constraints.copy(
                              minHeight = (constraints.minHeight - top - bottom).coerceAtLeast(0),
                              maxHeight = (constraints.maxHeight - top - bottom).coerceAtLeast(0)
                          )
                          val placeable = measurable.measure(inner)
                          layout(constraints.maxWidth, constraints.maxHeight) { placeable.place(0, top) }
                      }
              ) {
                when (target) {
                    AppDestination.HOME -> HomeScreen(
                        dailyReading = todayReading,
                        verseOfTheDay = todayContent?.takeIf { it.date == today && it.language == currentLanguage && it.rules == activeCalendarRules }?.verse ?: repository.emptyVerseOfTheDay(),
                        saintOfTheDay = saintOfTheDay,
                        currentLanguage = currentLanguage,
                        liturgicalDay = todayLiturgy,
                        dao = dao,
                        catholicQuestions = repository.catholicQuestions,
                        onOpenQuestions = { index ->
                            questionsInitialIndex = index
                            go(AppDestination.QUESTIONS)
                        },
                        onOpenLanguageSelector = { showLanguageSelector = true },
                        onOpenAppearance = { showCustomizerModal = true },
                        onOpenFontPicker = { showFontPicker = true },
                        onNavigateToMore = { go(AppDestination.MORE) },
                        onOpenBeginner = { go(AppDestination.BEGINNER) },
                        onOpenSuggestion = { openSuggestion(it) },
                        onNavigateToHabits = { openHabits() },
                        onOpenTemptations = { openHabits(temptations = true) },
                        onOpenDailyVerse = { bookId, chapter, verseStart ->
                            repository.requestBibleEdition("bundled")
                            onOpenBible(bookId, chapter, verseStart)
                        },
                        dailyVerseCanonicalReference = com.example.data.verse.DailyVerseRotation.referenceFor(java.time.LocalDate.of(today.year, today.month, today.day)),
                        onNavigateToBible = {
                            bibleInitialBook = null
                            bibleInitialChapter = null
                            bibleInitialVerse = null
                            bibleInitialTab = 0
                            go(AppDestination.BIBLE)
                        },
                        onNavigateToMissal = {
                            bibleInitialBook = null
                            bibleInitialChapter = null
                            bibleInitialVerse = null
                            bibleInitialTab = 1
                            go(AppDestination.BIBLE)
                        },
                        onNavigateToCatechism = {
                            catechismInitialParagraph = null
                            go(AppDestination.CATECHISM)
                        },
                        onNavigateToRosary = { openRosary(0) },
                        onNavigateToPrayers = { go(AppDestination.PRAYERS) },
                        onNavigateToLaudes = { onOpenPrayer("laudes") },
                        onNavigateToAngelus = { onOpenPrayer(SeasonalMarianPrayer.forDate(today).id) },
                        onNavigateToMorningPrayers = { onOpenPrayer("morning_offering") },
                        onNavigateToChaplet = {
                            openRosary(1)
                        },
                        onNavigateToNightPrayers = {
                            prayersInitialPrayerId = null
                            prayersInitialTab = 2
                            prayersInitialNightTab = 2
                            prayersInitialOpenChaplet = false
                            go(AppDestination.PRAYERS)
                        },
                        onNavigateToPilgrimage = {
                            pilgrimageInitialTab = 0
                            go(AppDestination.PILGRIMAGE_HABITS)
                        },
                        onNavigateToShrines = {
                            pilgrimageInitialTab = 1
                            go(AppDestination.PILGRIMAGE_HABITS)
                        },
                        onNavigateToDoctrine = { doctrineInitialEntry = null; go(AppDestination.DOCTRINE) },
                        onNavigateToSpiritualLife = { openSpiritual(0) },
                        onNavigateToQuiz = { openSpiritual(1) },
                        onOpenReminders = { go(AppDestination.REMINDERS) },
                        onOpenSearch = { openSearch() },
                        onNavigateToChurches = { go(AppDestination.PARISHES) },
                        ttsManager = ttsManager
                    )

                    // «Mi camino» is the only screen of the three destinations; the sections reuse the screens
                    // Hábitos y Virtudes and Vida espiritual always had.
                    AppDestination.MY_PATH, AppDestination.HABITS_VIRTUES, AppDestination.SPIRITUAL_LIFE -> {
                        val section = MyPathSection.valueOf(myPathSection)
                        // Down from the hub, back returns to it; arriving by a link, back leaves to the caller.
                        AppBackHandler(enabled = myPathFromHub && section != MyPathSection.HUB) {
                            myPathSection = MyPathSection.HUB.name
                            myPathSectionId++
                        }
                        MyPathSwitcher(section, myPathSectionId) { shown ->
                            when (shown) {
                                MyPathSection.HUB -> MyPathHub(
                                    repository = repository,
                                    dao = dao,
                                    store = preferencesStore,
                                    language = currentLanguage,
                                    onOpenSection = { next ->
                                        myPathSection = next.name
                                        spiritualLifeInitialTab = 0
                                        myPathFromHub = true
                                        myPathSectionId++
                                    },
                                    onOpenConfession = { go(AppDestination.CONFESSION) },
                                    onOpenPlan = { id -> go(AppDestination.PLANS); plansInitialId = id },
                                    onOpenNovena = { id ->
                                        prayersInitialPrayerId = null; prayersInitialTab = 1; prayersInitialNightTab = 0; prayersInitialOpenChaplet = false
                                        pushOnSearch(AppDestination.PRAYERS)
                                        prayersInitialNovenaId = id
                                    }
                                )

                                MyPathSection.HABITS, MyPathSection.TEMPTATIONS -> HabitsAndVirtuesScreen(
                                    repository = repository,
                                    dao = dao,
                                    ttsManager = ttsManager,
                                    onOpenPrayer = onOpenPrayer,
                                    initialOpenTemptations = shown == MyPathSection.TEMPTATIONS,
                                    onExitTemptations = if (shown == MyPathSection.TEMPTATIONS && myPathFromHub) ({
                                        myPathSection = MyPathSection.HUB.name
                                        myPathSectionId++
                                    }) else null,
                                    onOpenSinAndConfession = {
                                        doctrineInitialEntry = "grav:0"
                                        go(AppDestination.DOCTRINE)
                                    },
                                    onOpenPrayers = {
                                        prayersInitialPrayerId = null
                                        prayersInitialTab = 0
                                        go(AppDestination.PRAYERS)
                                    },
                                    onOpenRosary = { tab ->
                                        openRosary(tab)
                                    },
                                    onOpenMissal = {
                                        bibleInitialBook = null
                                        bibleInitialChapter = null
                                        bibleInitialVerse = null
                                        bibleInitialTab = 1
                                        go(AppDestination.BIBLE)
                                    },
                                    onOpenNight = { office ->
                                        prayersInitialPrayerId = null
                                        prayersInitialTab = 2
                                        prayersInitialNightTab = office
                                        go(AppDestination.PRAYERS)
                                    },
                                    onOpenReminders = { go(AppDestination.REMINDERS) }
                                )

                                MyPathSection.DIARY, MyPathSection.PRACTICES -> SpiritualLifeScreen(
                                    repository = repository,
                                    dao = dao,
                                    ttsManager = ttsManager,
                                    currentLanguage = currentLanguage,
                                    initialTab = if (shown == MyPathSection.PRACTICES) spiritualLifeInitialTab else 0,
                                    focus = if (shown == MyPathSection.PRACTICES) SpiritualFocus.PRACTICES else SpiritualFocus.JOURNAL,
                                    onOpenConfession = { go(AppDestination.CONFESSION) }
                                )
                            }
                        }
                    }

                    AppDestination.BIBLE -> BibleScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        dao = dao,
                        ttsManager = ttsManager,
                        onExitContext = if (stack.isNotEmpty() && (bibleInitialBook != null || bibleInitialLesson != null)) ({ pop() }) else null,
                        initialMainTab = bibleInitialTab,
                        initialBookId = bibleInitialBook,
                        initialChapter = bibleInitialChapter,
                        initialVerseStart = bibleInitialVerse,
                        onOpenCatechism = onOpenCatechism,
                        initialLessonId = bibleInitialLesson
                    )

                    AppDestination.CATECHISM -> CatechismScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialParagraph = catechismInitialParagraph,
                        onExitContext = if ((stack.lastOrNull() == AppDestination.BIBLE || stack.lastOrNull() == AppDestination.SEARCH) && catechismInitialParagraph != null) ({ pop() }) else null
                    )

                    AppDestination.ROSARY -> RosaryScreen(
                        repository = repository,
                        ttsManager = ttsManager,
                        currentLanguage = currentLanguage,
                        initialTab = rosaryInitialTab
                    )

                    AppDestination.PRAYERS -> PrayersAndNovenasScreen(
                        repository = repository,
                        ttsManager = ttsManager,
                        currentLanguage = currentLanguage,
                        dao = dao,
                        initialPrayerId = prayersInitialPrayerId,
                        initialNovenaId = prayersInitialNovenaId,
                        initialTab = prayersInitialTab,
                        initialNightOfficeTab = prayersInitialNightTab,
                        onOpenRosary = { openRosary(0) },
                        onOpenGuidedChaplet = { openRosary(1) },
                        initialOpenChaplet = prayersInitialOpenChaplet,
                        onExitContext = if (stack.lastOrNull() == AppDestination.SEARCH || stack.lastOrNull() == AppDestination.MY_PATH) ({ pop() }) else null
                    )

                    AppDestination.PILGRIMAGE_HABITS -> PilgrimageAndHabitsScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTab = pilgrimageInitialTab
                    )

                    AppDestination.DOCTRINE -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTopTab = 0,
                        initialEntry = doctrineInitialEntry,
                        showTabs = false,
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        onOpenPrayer = onOpenPrayer
                    )

                    AppDestination.STUDY_DOCTRINE -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTopTab = 0,
                        showTabs = false,
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        onOpenPrayer = onOpenPrayer
                    )

                    AppDestination.STUDY_MARY_JESUS -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTopTab = 1,
                        initialEntry = studyInitialEntry,
                        showTabs = false,
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        onOpenPrayer = onOpenPrayer
                    )

                    AppDestination.STUDY_HISTORY -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTopTab = 2,
                        showTabs = false,
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        onOpenPrayer = onOpenPrayer
                    )

                    AppDestination.STUDY_ORDERS -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTopTab = 4,
                        initialEntry = studyInitialEntry,
                        showTabs = false,
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        onOpenPrayer = onOpenPrayer
                    )

                    AppDestination.SAINTS -> com.example.ui.screens.SaintsScreen(
                        currentLanguage, initialKey = saintsInitialKey,
                        onExitContext = if (stack.lastOrNull() == AppDestination.SEARCH && saintsInitialKey != null) ({ pop() }) else null
                    )

                    AppDestination.PARISHES -> ParishFinderScreen(
                        repository = repository,
                        currentLanguage = currentLanguage
                    )

                    AppDestination.CHURCHES_NEWS -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTopTab = 5,
                        showTabs = false,
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        onOpenPrayer = onOpenPrayer
                    )

                    AppDestination.QUESTIONS -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTopTab = 3,
                        showTabs = false,
                        initialQuestionIndex = questionsInitialIndex,
                        initialEntry = studyInitialEntry,
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        onOpenPrayer = onOpenPrayer
                    )

                    AppDestination.CONFESSION -> com.example.ui.confession.ConfessionRoute(
                        preferencesStore, currentLanguage, active = current == AppDestination.CONFESSION
                    )

                    AppDestination.PLANS -> com.example.ui.screens.ReadingPlansRoute(
                        store = preferencesStore,
                        repository = repository,
                        language = currentLanguage,
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        initialPlanId = plansInitialId,
                        onExitContext = if ((stack.lastOrNull() == AppDestination.SEARCH || stack.lastOrNull() == AppDestination.MY_PATH) && plansInitialId != null) ({ pop() }) else null
                    )

                    AppDestination.SEARCH -> {
                        val search = remember(repository, searchSections) { GlobalSearch(repository, searchSections) }
                        com.example.ui.screens.SearchScreen(currentLanguage, search, onOpen = { openFromSearch(it) })
                    }

                    AppDestination.REMINDERS -> RemindersScreen(
                        repository = repository,
                        onBack = { pop() }
                    )

                    AppDestination.BEGINNER -> com.example.ui.screens.BeginnerJourneyScreen(
                        currentLanguage = currentLanguage,
                        onOpenBible = { onOpenBible("joh", 1, 35) },
                        onOpenPrayer = { onOpenPrayer("our_father") },
                        onOpenParishes = { go(AppDestination.PARISHES) }
                    )

                    AppDestination.MORE -> MoreScreen(
                        currentLanguage = currentLanguage,
                        onOpenBeginner = { go(AppDestination.BEGINNER) },
                        sections = moreSections(currentLanguage) { destination ->
                            if (destination == AppDestination.SEARCH) { openSearch(); return@moreSections }
                            if (destination == AppDestination.MY_PATH) enterMyPath(MyPathSection.HUB)
                            if (destination == AppDestination.QUESTIONS) questionsInitialIndex = null
                            if (destination == AppDestination.PILGRIMAGE_HABITS) pilgrimageInitialTab = 0
                            if (destination == AppDestination.BIBLE) {
                                bibleInitialBook = null
                                bibleInitialChapter = null
                                bibleInitialVerse = null
                                bibleInitialTab = 3
                            } else if (destination == AppDestination.CATECHISM) {
                                catechismInitialParagraph = null
                            }
                            go(destination)
                        } + MoreSection(
                            when (currentLanguage) {
                                AppLanguage.ES -> "Ajustes"
                                AppLanguage.EN -> "Settings"
                                AppLanguage.PT -> "Ajustes"
                            },
                            listOfNotNull(
                                if (!preferences.storageAvailable || failedPreferenceKeys.isNotEmpty()) MoreEntry(
                                    title = when (currentLanguage) { AppLanguage.ES -> "Revisar cambios no guardados"; AppLanguage.EN -> "Review unsaved changes"; AppLanguage.PT -> "Revisar alterações não salvas" },
                                    iconSlot = AppIconSlot.MORE, tag = "preferences_recovery_settings",
                                    onOpen = { showPreferenceRecovery = true }
                                ) else null,
                                MoreEntry(
                                    title = when (currentLanguage) {
                                        AppLanguage.ES -> "Apariencia"
                                        AppLanguage.EN -> "Appearance"
                                        AppLanguage.PT -> "Aparência"
                                    },
                                    iconSlot = AppIconSlot.MORE,
                                    value = when (preferences.darkModeSetting) {
                                        "LIGHT" -> when (currentLanguage) { AppLanguage.ES -> "Claro"; AppLanguage.EN -> "Light"; AppLanguage.PT -> "Claro" }
                                        "DARK" -> when (currentLanguage) { AppLanguage.ES -> "Oscuro"; AppLanguage.EN -> "Dark"; AppLanguage.PT -> "Escuro" }
                                        else -> when (currentLanguage) { AppLanguage.ES -> "Automático"; AppLanguage.EN -> "Automatic"; AppLanguage.PT -> "Automático" }
                                    },
                                    tag = "app_bar_theme_button",
                                    onOpen = { showCustomizerModal = true },
                                    icon = Icons.Outlined.Palette
                                ),
                                MoreEntry(
                                    title = when (currentLanguage) {
                                        AppLanguage.ES -> "Fuente"
                                        AppLanguage.EN -> "Font"
                                        AppLanguage.PT -> "Fonte"
                                    },
                                    value = FontChoice.fromId(preferences.fontFamilyChoice).label(currentLanguage),
                                    iconSlot = AppIconSlot.MORE,
                                    tag = "font_settings_button",
                                    onOpen = { showFontPicker = true },
                                    icon = Icons.Outlined.TextFields
                                ),
                                MoreEntry(
                                    title = when (currentLanguage) { AppLanguage.ES -> "Tu camino"; AppLanguage.EN -> "Your path"; AppLanguage.PT -> "Seu caminho" },
                                    subtitle = when (currentLanguage) { AppLanguage.ES -> "Perfil y tiempo para hoy"; AppLanguage.EN -> "Profile and time for today"; AppLanguage.PT -> "Perfil e tempo para hoje" },
                                    value = if (journey.welcomed) com.example.ui.screens.journeyProfileShort(currentLanguage, journey.profile) else null,
                                    iconSlot = AppIconSlot.MORE, tag = "settings_welcome_profile",
                                    onOpen = { showJourneyChoice = true }, icon = Icons.Outlined.Tune
                                ),
                                MoreEntry(
                                    title = when (currentLanguage) { AppLanguage.ES -> "Enviar informe de errores"; AppLanguage.EN -> "Send error report"; AppLanguage.PT -> "Enviar relatório de erros" },
                                    iconSlot = AppIconSlot.MORE, tag = "error_report_entry", onOpen = { showErrorReport = true }, icon = Icons.Outlined.BugReport
                                ),
                                MoreEntry(
                                    title = when (currentLanguage) { AppLanguage.ES -> "Copia de seguridad"; AppLanguage.EN -> "Backup"; AppLanguage.PT -> "Cópia de segurança" },
                                    iconSlot = AppIconSlot.MORE, tag = "backup_entry", onOpen = { showBackup = true }, icon = Icons.Outlined.SaveAlt
                                ),
                                MoreEntry(
                                    title = when (currentLanguage) { AppLanguage.ES -> "Calendario litúrgico"; AppLanguage.EN -> "Liturgical calendar"; AppLanguage.PT -> "Calendário litúrgico" },
                                    value = when (currentLanguage) { AppLanguage.ES -> "Traslados"; AppLanguage.EN -> "Transfers"; AppLanguage.PT -> "Transferências" },
                                    iconSlot = AppIconSlot.MORE, tag = "calendar_rules_settings",
                                    onOpen = { showCalendarRules = true }, icon = Icons.Outlined.DateRange
                                ),
                                MoreEntry(
                                    title = when (currentLanguage) {
                                        AppLanguage.ES -> "Idioma"
                                        AppLanguage.EN -> "Language"
                                        AppLanguage.PT -> "Idioma"
                                    },
                                    value = currentLanguage.displayName,
                                    iconSlot = AppIconSlot.MORE,
                                    tag = "app_bar_lang_button",
                                    onOpen = { showLanguageSelector = true },
                                    icon = Icons.Outlined.Language
                                )
                            )
                        )
                    )
                }
              }
              }
              }
            }
        }
    }

    }

    LaunchedEffect(initialReminderId) {
        val id = initialReminderId ?: return@LaunchedEffect
        when {
            id.startsWith("angelus_") -> onOpenPrayer(SeasonalMarianPrayer.forDate(today).id)
            id == "morning_prayer" -> onOpenPrayer("morning_offering")
            id == "coronilla" -> openRosary(1)
            id == "night_prayer" -> { prayersInitialTab = 2; prayersInitialNightTab = 2; go(AppDestination.PRAYERS) }
            id == "daily_reading" -> { bibleInitialTab = 1; go(AppDestination.BIBLE) }
            // Liturgical notices and widgets: `notice:<source>:<destination>` (T-032).
            id.startsWith(com.example.data.notices.NoticeScheduler.OPEN_PREFIX) -> {
                val action = com.example.data.notices.NoticeScheduler.actionOf(id)
                when (val target = AppDestination.entries.firstOrNull { it.name == action } ?: AppDestination.HOME) {
                    AppDestination.HOME -> go(AppDestination.HOME)
                    // A notice's Gospel or reading opens the day's Mass readings.
                    AppDestination.BIBLE -> {
                        bibleInitialBook = null; bibleInitialChapter = null; bibleInitialVerse = null; bibleInitialTab = 1
                        go(AppDestination.BIBLE)
                    }
                    else -> openSection(target)
                }
            }
        }
        onReminderConsumed()
    }

    val narrationIssue by ttsManager.playbackIssue.collectAsState()
    narrationIssue?.let { issue ->
        com.example.ui.components.NarrationRecoverySheet(ttsManager, issue, currentLanguage)
    }

    if (showPreferenceRecovery) {
        com.example.ui.components.PreferencesRecoverySheet(preferencesStore, preferences.storageAvailable, failedPreferenceKeys, currentLanguage,
            onDismiss = { showPreferenceRecovery = false },
            saving = preferenceWrites.values.any { it.status == com.example.data.persistence.PersonalDataCommands.Status.SAVING })
    }

    if (showErrorReport) {
        com.example.ui.diagnostics.ErrorReportSheet(currentLanguage) { showErrorReport = false }
    }
    if (showBackup) {
        com.example.ui.backup.BackupSheet(preferencesStore, currentLanguage, backupService,
            onDismiss = { showBackup = false }, onRestored = onBackupRestored)
    }
    if (showCalendarRules) {
        CalendarRulesSheet(currentLanguage, activeCalendarRules, preferencesStore) { showCalendarRules = false }
    }

    if (showLanguageSelector) {
        LanguageSelectorDialog(
            currentLanguage = currentLanguage,
            onLanguageSelected = { newLang ->
                repository.setLanguage(newLang)
                ttsManager.setLanguage(newLang)
            },
            onDismissRequest = { showLanguageSelector = false }
        )
    }

    if (showJourneyChoice) {
        com.example.ui.screens.JourneyChoiceSheet(currentLanguage) { showJourneyChoice = false }
    }

    if (showFontPicker) {
        FontPickerDialog(preferencesStore, preferences, currentLanguage) { showFontPicker = false }
    }

    if (showCustomizerModal) {
        GuiCustomizerModal(
            preferencesStore = preferencesStore,
            preferences = preferences,
            currentLanguage = currentLanguage,
            ttsManager = ttsManager,
            onDismiss = { showCustomizerModal = false }
        )
    }
}

/** The More tab's contents, grouped by what the reader has come to do. */
private fun moreSections(
    language: AppLanguage,
    open: (AppDestination) -> Unit
): List<MoreSection> {
    fun pick(es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    fun entry(d: AppDestination) =
        MoreEntry(d.title(language), d.iconSlot, d.tag) { open(d) }
    return listOf(
        MoreSection(
            pick("Buscar", "Search", "Buscar"),
            listOf(entry(AppDestination.SEARCH).copy(
                icon = Icons.Outlined.Search,
                subtitle = pick("Oraciones, santos, Catecismo, Biblia…", "Prayers, saints, Catechism, Bible…", "Orações, santos, Catecismo, Bíblia…")
            ))
        ),
        MoreSection(
            pick("Estudio", "Study", "Estudo"),
            listOf(
                MoreEntry(pick("Cursos bíblicos", "Scripture courses", "Cursos bíblicos"), AppDestination.BIBLE.iconSlot, "nav_scripture_study") { open(AppDestination.BIBLE) },
                entry(AppDestination.PLANS),
                entry(AppDestination.CATECHISM),
                entry(AppDestination.STUDY_DOCTRINE),
                entry(AppDestination.STUDY_MARY_JESUS),
                entry(AppDestination.STUDY_HISTORY),
                entry(AppDestination.QUESTIONS),
                entry(AppDestination.STUDY_ORDERS),
                entry(AppDestination.SAINTS),
                entry(AppDestination.CHURCHES_NEWS)
            )
        ),
        MoreSection(
            pick("Vida interior", "Inner life", "Vida interior"),
            listOf(
                entry(AppDestination.MY_PATH),
                entry(AppDestination.CONFESSION)
            )
        ),
        MoreSection(
            pick("Lugares", "Places", "Lugares"),
            listOf(
                entry(AppDestination.PARISHES),
                entry(AppDestination.PILGRIMAGE_HABITS)
            )
        )
    )
}
