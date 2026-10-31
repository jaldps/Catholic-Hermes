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
    PILGRIMAGE_HABITS("Santuarios", "Sanctuaries", "Santuários", AppIconSlot.PILGRIMAGE, "nav_pilgrimage"),
    DOCTRINE("Doctrina e Iglesias", "Doctrine & Parishes", "Doutrina e Paróquias", AppIconSlot.DOCTRINE, "nav_doctrine"),
    PARISHES("Parroquias", "Parishes", "Paróquias", AppIconSlot.PILGRIMAGE, "nav_parishes"),
    CHURCHES_NEWS("Iglesias y Noticias", "Churches & News", "Igrejas e Notícias", AppIconSlot.NEWS, "nav_churches_news"),
    SPIRITUAL_LIFE("Diario y Quiz", "Journal & Quiz", "Diário e Quiz", AppIconSlot.JOURNAL, "nav_spiritual");

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
    preferences: UserPreferences
) {
    val currentLanguage by repository.currentLanguage.collectAsState()
    var selectedDestinationIndex by remember { mutableIntStateOf(0) }
    var spiritualLifeInitialTab by remember { mutableIntStateOf(0) }
    var bibleInitialTab by remember { mutableIntStateOf(0) }
    var showLanguageSelector by remember { mutableStateOf(false) }
    var showCustomizerModal by remember { mutableStateOf(false) }

    val destinations = AppDestination.entries
    val design = LocalAppDesign.current

    val navItems = destinations.map { destination ->
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
        selectedNavIndex = selectedDestinationIndex,
        onNavSelected = { selectedDestinationIndex = it },
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
        BackHandler(enabled = selectedDestinationIndex != 0) {
            selectedDestinationIndex = 0
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = selectedDestinationIndex,
                transitionSpec = {
                    val spec = tween<Float>(
                        durationMillis = design.motion.mediumMillis,
                        easing = design.motion.standardEasing
                    )
                    fadeIn(spec) togetherWith fadeOut(spec)
                },
                label = "screen_transition"
            ) { targetIndex ->
                when (destinations[targetIndex]) {
                    AppDestination.HOME -> HomeScreen(
                        dailyReading = repository.dailyMassReading,
                        verseOfTheDay = repository.verseOfTheDay,
                        saintOfTheDay = repository.saintOfTheDay,
                        currentLanguage = currentLanguage,
                        liturgicalDay = repository.getCurrentLiturgicalDay(),
                        onOpenLanguageSelector = { showLanguageSelector = true },
                        onNavigateToHabits = { selectedDestinationIndex = destinations.indexOf(AppDestination.HABITS_VIRTUES) },
                        onNavigateToBible = {
                            bibleInitialTab = 0
                            selectedDestinationIndex = destinations.indexOf(AppDestination.BIBLE)
                        },
                        onNavigateToMissal = {
                            bibleInitialTab = 1
                            selectedDestinationIndex = destinations.indexOf(AppDestination.BIBLE)
                        },
                        onNavigateToCatechism = { selectedDestinationIndex = destinations.indexOf(AppDestination.CATECHISM) },
                        onNavigateToRosary = { selectedDestinationIndex = destinations.indexOf(AppDestination.ROSARY) },
                        onNavigateToPrayers = { selectedDestinationIndex = destinations.indexOf(AppDestination.PRAYERS) },
                        onNavigateToPilgrimage = { selectedDestinationIndex = destinations.indexOf(AppDestination.PILGRIMAGE_HABITS) },
                        onNavigateToDoctrine = { selectedDestinationIndex = destinations.indexOf(AppDestination.DOCTRINE) },
                        onNavigateToSpiritualLife = {
                            spiritualLifeInitialTab = 0
                            selectedDestinationIndex = destinations.indexOf(AppDestination.SPIRITUAL_LIFE)
                        },
                        onNavigateToQuiz = {
                            spiritualLifeInitialTab = 1
                            selectedDestinationIndex = destinations.indexOf(AppDestination.SPIRITUAL_LIFE)
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
