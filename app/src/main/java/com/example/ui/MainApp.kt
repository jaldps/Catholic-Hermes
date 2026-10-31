package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.GuiCustomizerModal
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
import com.example.ui.screens.RosaryScreen
import com.example.ui.screens.SpiritualLifeScreen
import com.example.ui.theme.LocalAppDesign

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
    CHURCHES_NEWS("Iglesias y Noticias", "Churches & News", "Igrejas e Notícias", AppIconSlot.NEWS, "nav_churches_news"),
    SPIRITUAL_LIFE("Diario y Quiz", "Journal & Quiz", "Diário e Quiz", AppIconSlot.JOURNAL, "nav_spiritual"),
    MORE("Más", "More", "Mais", AppIconSlot.MORE, "nav_more");

    companion object {
        /**
         * What the tab bar carries. iOS and Material both cap a bottom bar at
         * five; everything else is listed by [MORE] and pushed from it.
         */
        val tabs = listOf(HOME, BIBLE, ROSARY, PRAYERS, MORE)
    }

    fun title(language: AppLanguage): String = when (language) {
        AppLanguage.ES -> titleEs
        AppLanguage.EN -> titleEn
        AppLanguage.PT -> titlePt
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
    var current by remember { mutableStateOf(initialDestination) }
    // The tab a pushed screen was opened from, so back and the tab bar's
    // highlight return to it: Home's shortcuts push onto Home, not onto More.
    var origin by remember {
        mutableStateOf(if (initialDestination in AppDestination.tabs) initialDestination else AppDestination.MORE)
    }
    fun go(destination: AppDestination) {
        if (destination in AppDestination.tabs) origin = destination
        else if (current in AppDestination.tabs) origin = current
        current = destination
    }
    var spiritualLifeInitialTab by remember { mutableIntStateOf(0) }
    var bibleInitialTab by remember { mutableIntStateOf(0) }
    var showLanguageSelector by remember { mutableStateOf(false) }
    var showCustomizerModal by remember { mutableStateOf(false) }

    val design = LocalAppDesign.current
    val tabs = AppDestination.tabs
    val isPushed = current !in tabs

    val navItems = tabs.map { destination ->
        val (resting, active) = AppIcons.pair(destination.iconSlot)
        AppNavItem(
            label = destination.title(currentLanguage),
            icon = resting,
            selectedIcon = active,
            tag = destination.tag
        )
    }

    AppScaffold(
        title = "Sancta Fides",
        navItems = navItems,
        selectedNavIndex = tabs.indexOf(if (isPushed) origin else current),
        onNavSelected = { go(tabs[it]) },
        onBack = if (isPushed) ({ current = origin }) else null,
        backLabel = if (isPushed) origin.title(currentLanguage) else null,
        actions = {
            IconButton(
                onClick = { showCustomizerModal = true },
                modifier = Modifier.testTag("app_bar_theme_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Palette,
                    contentDescription = "Personalizar Tema y Apariencia",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(22.dp)
                )
            }
            IconButton(
                onClick = { showLanguageSelector = true },
                modifier = Modifier.testTag("app_bar_lang_button")
            ) {
                Text(text = currentLanguage.flag, fontSize = 18.sp)
            }
        }
    ) { innerPadding ->
        BackHandler(enabled = current != AppDestination.HOME) {
            if (isPushed) current = origin else go(AppDestination.HOME)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = current,
                transitionSpec = {
                    val spec = tween<Float>(
                        durationMillis = design.motion.mediumMillis,
                        easing = design.motion.standardEasing
                    )
                    fadeIn(spec) togetherWith fadeOut(spec)
                },
                label = "screen_transition"
            ) { target ->
                when (target) {
                    AppDestination.HOME -> HomeScreen(
                        dailyReading = repository.dailyMassReading,
                        verseOfTheDay = repository.verseOfTheDay,
                        saintOfTheDay = repository.saintOfTheDay,
                        currentLanguage = currentLanguage,
                        liturgicalDay = repository.getCurrentLiturgicalDay(),
                        onOpenLanguageSelector = { showLanguageSelector = true },
                        onNavigateToHabits = { go(AppDestination.HABITS_VIRTUES) },
                        onNavigateToBible = {
                            bibleInitialTab = 0
                            go(AppDestination.BIBLE)
                        },
                        onNavigateToMissal = {
                            bibleInitialTab = 1
                            go(AppDestination.BIBLE)
                        },
                        onNavigateToCatechism = { go(AppDestination.CATECHISM) },
                        onNavigateToRosary = { go(AppDestination.ROSARY) },
                        onNavigateToPrayers = { go(AppDestination.PRAYERS) },
                        onNavigateToPilgrimage = { go(AppDestination.PILGRIMAGE_HABITS) },
                        onNavigateToDoctrine = { go(AppDestination.DOCTRINE) },
                        onNavigateToSpiritualLife = {
                            spiritualLifeInitialTab = 0
                            go(AppDestination.SPIRITUAL_LIFE)
                        },
                        onNavigateToQuiz = {
                            spiritualLifeInitialTab = 1
                            go(AppDestination.SPIRITUAL_LIFE)
                        }
                    )

                    AppDestination.HABITS_VIRTUES -> HabitsAndVirtuesScreen(
                        repository = repository,
                        dao = dao,
                        ttsManager = ttsManager
                    )

                    AppDestination.BIBLE -> BibleScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        dao = dao,
                        ttsManager = ttsManager,
                        initialMainTab = bibleInitialTab
                    )

                    AppDestination.CATECHISM -> CatechismScreen(
                        repository = repository,
                        currentLanguage = currentLanguage
                    )

                    AppDestination.ROSARY -> RosaryScreen(
                        repository = repository,
                        ttsManager = ttsManager,
                        currentLanguage = currentLanguage
                    )

                    AppDestination.PRAYERS -> PrayersAndNovenasScreen(
                        repository = repository,
                        ttsManager = ttsManager,
                        currentLanguage = currentLanguage,
                        dao = dao
                    )

                    AppDestination.PILGRIMAGE_HABITS -> PilgrimageAndHabitsScreen(
                        repository = repository,
                        currentLanguage = currentLanguage
                    )

                    AppDestination.DOCTRINE -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage
                    )

                    AppDestination.PARISHES -> ParishFinderScreen(
                        repository = repository,
                        currentLanguage = currentLanguage
                    )

                    AppDestination.CHURCHES_NEWS -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage,
                        initialTopTab = 3
                    )

                    AppDestination.SPIRITUAL_LIFE -> SpiritualLifeScreen(
                        repository = repository,
                        dao = dao,
                        ttsManager = ttsManager,
                        currentLanguage = currentLanguage,
                        initialTab = spiritualLifeInitialTab
                    )

                    AppDestination.MORE -> MoreScreen(
                        currentLanguage = currentLanguage,
                        sections = moreSections(currentLanguage) { destination ->
                            if (destination == AppDestination.SPIRITUAL_LIFE) spiritualLifeInitialTab = 0
                            go(destination)
                        }
                    )
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
    fun entry(d: AppDestination, es: String, en: String, pt: String) =
        MoreEntry(d.title(language), pick(es, en, pt), d.iconSlot, d.tag) { open(d) }
    return listOf(
        MoreSection(
            pick("Estudio", "Study", "Estudo"),
            listOf(
                entry(AppDestination.CATECHISM, "Los 2865 artículos, por partes", "All 2,865 paragraphs, by part", "Os 2865 parágrafos, por partes"),
                entry(AppDestination.DOCTRINE, "Doctrina, María y Jesús, historia de la Iglesia", "Doctrine, Mary and Jesus, Church history", "Doutrina, Maria e Jesus, história da Igreja"),
                entry(AppDestination.CHURCHES_NEWS, "Noticias del Vaticano y directorio", "Vatican news and directory", "Notícias do Vaticano e diretório")
            )
        ),
        MoreSection(
            pick("Vida interior", "Inner life", "Vida interior"),
            listOf(
                entry(AppDestination.HABITS_VIRTUES, "Examen, virtudes y hábitos diarios", "Examen, virtues and daily habits", "Exame, virtudes e hábitos diários"),
                entry(AppDestination.SPIRITUAL_LIFE, "Diario espiritual y preguntas de fe", "Spiritual journal and faith quiz", "Diário espiritual e quiz de fé")
            )
        ),
        MoreSection(
            pick("Lugares", "Places", "Lugares"),
            listOf(
                entry(AppDestination.PARISHES, "Misa cerca de ti", "Mass near you", "Missa perto de você"),
                entry(AppDestination.PILGRIMAGE_HABITS, "Año litúrgico, hábitos y santuarios", "Church year, habits and shrines", "Ano litúrgico, hábitos e santuários")
            )
        )
    )
}
