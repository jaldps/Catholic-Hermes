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
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.sharp.Palette
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    HOME("Inicio", "Home", "Início", AppIconSlot.HOME, "nav_home"),
    HABITS_VIRTUES("Hábitos y Virtudes", "Habits & Virtues", "Hábitos e Virtudes", AppIconSlot.HABITS, "nav_habits"),
    BIBLE("Biblia", "Bible", "Bíblia", AppIconSlot.BIBLE, "nav_bible"),
    CATECHISM("Catecismo", "Catechism", "Catecismo", AppIconSlot.CATECHISM, "nav_catechism"),
    ROSARY("Rosario", "Rosary", "Terço", AppIconSlot.ROSARY, "nav_rosary"),
    PRAYERS("Oraciones", "Prayers", "Orações", AppIconSlot.PRAYERS, "nav_prayers"),
    PILGRIMAGE_HABITS("Calendario y Santuarios", "Calendar & Shrines", "Calendário e Santuários", AppIconSlot.PILGRIMAGE, "nav_pilgrimage"),
    DOCTRINE("Doctrina e Historia", "Doctrine & History", "Doutrina e História", AppIconSlot.DOCTRINE, "nav_doctrine"),
    PARISHES("Parroquias", "Parishes", "Paróquias", AppIconSlot.PARISH, "nav_parishes"),
    CHURCHES_NEWS("Noticias", "News", "Notícias", AppIconSlot.NEWS, "nav_churches_news"),
    QUESTIONS("101 preguntas sobre la fe católica", "101 Questions on the Catholic Faith", "101 Perguntas sobre a fé católica", AppIconSlot.DOCTRINE, "nav_questions"),
    STUDY_DOCTRINE("Doctrina", "Doctrine", "Doutrina", AppIconSlot.DOCTRINE, "nav_study_doctrine"),
    STUDY_MARY_JESUS("María y Jesús", "Mary & Jesus", "Maria e Jesus", AppIconSlot.DOCTRINE, "nav_study_mary_jesus"),
    STUDY_HISTORY("Historia", "History", "História", AppIconSlot.DOCTRINE, "nav_study_history"),
    STUDY_ORDERS("Órdenes y oficios", "Orders & Offices", "Ordens e ofícios", AppIconSlot.DOCTRINE, "nav_study_orders"),
    SPIRITUAL_LIFE("Vida espiritual", "Spiritual Life", "Vida espiritual", AppIconSlot.JOURNAL, "nav_spiritual"),
    REMINDERS("Recordatorios", "Reminders", "Lembretes", AppIconSlot.HABITS, "nav_reminders"),
    MORE("Explorar", "Explore", "Explorar", AppIconSlot.MORE, "nav_more");

    companion object {
        /**
         * What the tab bar carries. Habits sits next to Prayers as a
         * first-class tab, and [MORE] (with Settings) is the sixth tab;
         * everything else is reached by pushing from a tab.
         */
        val tabs = listOf(HOME, BIBLE, ROSARY, PRAYERS, HABITS_VIRTUES, MORE)
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
            AppLanguage.ES -> "Inicio"
            AppLanguage.EN -> "Home"
            AppLanguage.PT -> "Início"
        }
        BIBLE -> when (language) {
            AppLanguage.ES -> "Biblia"
            AppLanguage.EN -> "Bible"
            AppLanguage.PT -> "Bíblia"
        }
        ROSARY -> when (language) {
            AppLanguage.ES -> "Rosario"
            AppLanguage.EN -> "Rosary"
            AppLanguage.PT -> "Terço"
        }
        PRAYERS -> when (language) {
            AppLanguage.ES -> "Oraciones"
            AppLanguage.EN -> "Prayers"
            AppLanguage.PT -> "Orações"
        }
        HABITS_VIRTUES -> when (language) {
            AppLanguage.ES -> "Hábitos"
            AppLanguage.EN -> "Habits"
            AppLanguage.PT -> "Hábitos"
        }
        MORE -> when (language) {
            AppLanguage.ES -> "Más"
            AppLanguage.EN -> "More"
            AppLanguage.PT -> "Mais"
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
    initialDestination: AppDestination = AppDestination.HOME
) {
    val currentLanguage by repository.currentLanguage.collectAsState()
    // Bundled saint while the feed loads, then the feed's saint for today.
    val saintOfTheDay by repository.saintOfTheDayFlow.collectAsState()
    // A real back stack, saved across rotation and process death: pushing
    // from a pushed screen (101 Questions → Catechism) pops back to it, and
    // the tab bar highlights the tab at the bottom of the stack.
    var currentName by rememberSaveable { mutableStateOf(initialDestination.name) }
    var stackNames by rememberSaveable {
        mutableStateOf(if (initialDestination in AppDestination.tabs) "" else AppDestination.MORE.name)
    }
    val current = AppDestination.valueOf(currentName)
    val stack = stackNames.split(',').filter { it.isNotEmpty() }.map { AppDestination.valueOf(it) }
    val origin = stack.firstOrNull { it in AppDestination.tabs }
        ?: if (current in AppDestination.tabs) current else AppDestination.MORE
    fun go(destination: AppDestination) {
        if (destination == current) return
        stackNames = if (destination in AppDestination.tabs) "" else (stack + current).joinToString(",") { it.name }
        currentName = destination.name
    }
    var spiritualLifeInitialTab by remember { mutableIntStateOf(0) }
    var bibleInitialTab by remember { mutableIntStateOf(0) }
    // Deep-link state: set by onOpenBible/onOpenCatechism (Doctrine screen
    // calls these once it exposes them), consumed as the initial params of
    // BibleScreen/CatechismScreen.
    var bibleInitialBook by remember { mutableStateOf<String?>(null) }
    var bibleInitialChapter by remember { mutableStateOf<Int?>(null) }
    var bibleInitialVerse by remember { mutableStateOf<Int?>(null) }
    var catechismInitialParagraph by remember { mutableStateOf<Int?>(null) }
    val onOpenBible: (bookId: String, chapter: Int, verseStart: Int?) -> Unit =
        { bookId, chapter, verseStart ->
            bibleInitialBook = bookId
            bibleInitialChapter = chapter
            bibleInitialVerse = verseStart
            bibleInitialTab = 0
            go(AppDestination.BIBLE)
        }
    val onOpenCatechism: (paragraph: Int) -> Unit = { paragraph ->
        catechismInitialParagraph = paragraph
        go(AppDestination.CATECHISM)
    }
    var prayersInitialPrayerId by remember { mutableStateOf<String?>(null) }
    var prayersInitialTab by remember { mutableIntStateOf(0) }
    var prayersInitialNightTab by remember { mutableIntStateOf(0) }
    var prayersInitialOpenChaplet by remember { mutableStateOf(false) }
    // Deep link from Home's "Pregunta del día": the 0-based index of the
    // question whose answer opens directly (DoctrineAndHistoryScreen
    // initialQuestionIndex). Cleared on the next direct nav.
    var questionsInitialIndex by remember { mutableStateOf<Int?>(null) }
    var rosaryInitialTab by remember { mutableIntStateOf(0) }
    // Home's "Santuarios" tile opens Calendar & Shrines on its shrines tab
    // (PilgrimageAndHabitsScreen initialTab); every other entry opens the calendar.
    var pilgrimageInitialTab by remember { mutableIntStateOf(0) }
    // Churches & News opens on the church directory (1) from Home's "Iglesias"
    // tile and Parishes' "Directorio"; More opens it on the news (0).
    var churchesInitialSection by remember { mutableIntStateOf(0) }
    val onOpenPrayer: (prayerId: String) -> Unit = { prayerId ->
        prayersInitialPrayerId = prayerId
        prayersInitialTab = 0
        go(AppDestination.PRAYERS)
    }
    var showLanguageSelector by remember { mutableStateOf(false) }
    var showCustomizerModal by remember { mutableStateOf(false) }
    var showFontPicker by remember { mutableStateOf(false) }

    val design = LocalAppDesign.current
    val tabs = AppDestination.tabs
    val isPushed = current !in tabs

    val navItems = tabs.map { destination ->
        val (resting, active) = AppIcons.pair(destination.iconSlot)
        AppNavItem(
            label = destination.tabLabel(currentLanguage),
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
                bibleInitialBook = null
                bibleInitialChapter = null
                bibleInitialVerse = null
            } else if (dest == AppDestination.CATECHISM) {
                catechismInitialParagraph = null
            } else if (dest == AppDestination.PRAYERS) {
                prayersInitialPrayerId = null
                prayersInitialTab = 0
                prayersInitialNightTab = 0
                prayersInitialOpenChaplet = false
            } else if (dest == AppDestination.ROSARY) {
                rosaryInitialTab = 0
            }
            go(dest)
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
                        dailyReading = repository.dailyMassReading,
                        verseOfTheDay = repository.verseOfTheDay,
                        saintOfTheDay = saintOfTheDay,
                        currentLanguage = currentLanguage,
                        liturgicalDay = repository.getCurrentLiturgicalDay(),
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
                        onNavigateToHabits = { go(AppDestination.HABITS_VIRTUES) },
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
                        onNavigateToRosary = { go(AppDestination.ROSARY) },
                        onNavigateToPrayers = { go(AppDestination.PRAYERS) },
                        onNavigateToLaudes = { onOpenPrayer("laudes") },
                        onNavigateToAngelus = { onOpenPrayer("angelus") },
                        onNavigateToMorningPrayers = { onOpenPrayer("morning_offering") },
                        onNavigateToChaplet = {
                            prayersInitialPrayerId = null
                            prayersInitialTab = 0
                            prayersInitialOpenChaplet = true
                            go(AppDestination.PRAYERS)
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
                        onNavigateToDoctrine = { go(AppDestination.DOCTRINE) },
                        onNavigateToSpiritualLife = {
                            spiritualLifeInitialTab = 0
                            go(AppDestination.SPIRITUAL_LIFE)
                        },
                        onNavigateToQuiz = {
                            spiritualLifeInitialTab = 1
                            go(AppDestination.SPIRITUAL_LIFE)
                        },
                        onOpenReminders = { go(AppDestination.REMINDERS) },
                        onNavigateToChurches = {
                            churchesInitialSection = 1
                            go(AppDestination.CHURCHES_NEWS)
                        },
                        ttsManager = ttsManager
                    )

                    AppDestination.HABITS_VIRTUES -> HabitsAndVirtuesScreen(
                        repository = repository,
                        dao = dao,
                        ttsManager = ttsManager,
                        onOpenPrayer = onOpenPrayer,
                        onOpenPrayers = {
                            prayersInitialPrayerId = null
                            prayersInitialTab = 0
                            go(AppDestination.PRAYERS)
                        },
                        onOpenRosary = { tab ->
                            rosaryInitialTab = tab
                            go(AppDestination.ROSARY)
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

                    AppDestination.BIBLE -> BibleScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        dao = dao,
                        ttsManager = ttsManager,
                        initialMainTab = bibleInitialTab,
                        initialBookId = bibleInitialBook,
                        initialChapter = bibleInitialChapter,
                        initialVerseStart = bibleInitialVerse
                    )

                    AppDestination.CATECHISM -> CatechismScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialParagraph = catechismInitialParagraph
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
                        initialTab = prayersInitialTab,
                        initialNightOfficeTab = prayersInitialNightTab,
                        initialOpenChaplet = prayersInitialOpenChaplet
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
                        showTabs = false,
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        onOpenPrayer = onOpenPrayer
                    )

                    AppDestination.PARISHES -> ParishFinderScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        onOpenDirectory = {
                            churchesInitialSection = 1
                            go(AppDestination.CHURCHES_NEWS)
                        }
                    )

                    AppDestination.CHURCHES_NEWS -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTopTab = 5,
                        initialChurchesSection = churchesInitialSection,
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
                        onOpenBible = onOpenBible,
                        onOpenCatechism = onOpenCatechism,
                        onOpenPrayer = onOpenPrayer
                    )

                    AppDestination.SPIRITUAL_LIFE -> SpiritualLifeScreen(
                        repository = repository,
                        dao = dao,
                        ttsManager = ttsManager,
                        currentLanguage = currentLanguage,
                        initialTab = spiritualLifeInitialTab
                    )

                    AppDestination.REMINDERS -> RemindersScreen(
                        repository = repository,
                        onBack = { pop() }
                    )

                    AppDestination.MORE -> MoreScreen(
                        currentLanguage = currentLanguage,
                        sections = moreSections(currentLanguage) { destination ->
                            if (destination == AppDestination.SPIRITUAL_LIFE) spiritualLifeInitialTab = 0
                            if (destination == AppDestination.QUESTIONS) questionsInitialIndex = null
                            if (destination == AppDestination.PILGRIMAGE_HABITS) pilgrimageInitialTab = 0
                            if (destination == AppDestination.CHURCHES_NEWS) churchesInitialSection = 0
                            if (destination == AppDestination.BIBLE) {
                                bibleInitialBook = null
                                bibleInitialChapter = null
                                bibleInitialVerse = null
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
                            listOf(
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

    if (showFontPicker) {
        FontPickerDialog(preferencesStore, preferences, currentLanguage) { showFontPicker = false }
    }

    if (showCustomizerModal) {
        GuiCustomizerModal(
            preferencesStore = preferencesStore,
            preferences = preferences,
            currentLanguage = currentLanguage,
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
            pick("Estudio", "Study", "Estudo"),
            listOf(
                entry(AppDestination.CATECHISM),
                entry(AppDestination.STUDY_DOCTRINE),
                entry(AppDestination.STUDY_MARY_JESUS),
                entry(AppDestination.STUDY_HISTORY),
                entry(AppDestination.QUESTIONS),
                entry(AppDestination.STUDY_ORDERS),
                entry(AppDestination.CHURCHES_NEWS)
            )
        ),
        MoreSection(
            pick("Vida interior", "Inner life", "Vida interior"),
            listOf(
                entry(AppDestination.HABITS_VIRTUES),
                entry(AppDestination.SPIRITUAL_LIFE)
                    .copy(icon = Icons.Outlined.AutoStories)
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
