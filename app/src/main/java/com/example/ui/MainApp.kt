package com.example.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.LanguageSelectorDialog
import com.example.ui.components.GuiCustomizerModal
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.filled.Shield
import com.example.ui.screens.HabitsAndVirtuesScreen
import com.example.ui.screens.BibleScreen
import com.example.ui.screens.CatechismScreen
import com.example.ui.screens.DoctrineAndHistoryScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PilgrimageAndHabitsScreen
import com.example.ui.screens.PrayersAndNovenasScreen
import com.example.ui.screens.RosaryScreen
import com.example.ui.screens.SpiritualLifeScreen
import com.example.ui.theme.LiturgicalGold
import com.example.ui.theme.SacredBurgundy

enum class AppDestination(
    val titleEs: String,
    val titleEn: String,
    val titlePt: String,
    val icon: ImageVector,
    val tag: String
) {
    HOME("Inicio", "Home", "Início", Icons.Default.Home, "nav_home"),
    HABITS_VIRTUES("Hábitos y Virtudes", "Habits & Virtues", "Hábitos e Virtudes", Icons.Default.Shield, "nav_habits"),
    BIBLE("Biblia", "Bible", "Bíblia", Icons.Default.AutoStories, "nav_bible"),
    CATECHISM("Catecismo", "Catechism", "Catecismo", Icons.Default.MenuBook, "nav_catechism"),
    ROSARY("Rosario", "Rosary", "Terço", Icons.Default.SelfImprovement, "nav_rosary"),
    PRAYERS("Oraciones", "Prayers", "Orações", Icons.Default.VolunteerActivism, "nav_prayers"),
    PILGRIMAGE_HABITS("Santuarios", "Sanctuaries", "Santuários", Icons.Default.Place, "nav_pilgrimage"),
    DOCTRINE("Doctrina e Iglesias", "Doctrine & Parishes", "Doutrina e Paróquias", Icons.Default.Church, "nav_doctrine"),
    SPIRITUAL_LIFE("Diario y Quiz", "Journal & Quiz", "Diário e Quiz", Icons.Default.Star, "nav_spiritual")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    repository: CatholicDataRepository,
    dao: SanctaFidesDao,
    ttsManager: LiturgicalTtsManager
) {
    val currentLanguage by repository.currentLanguage.collectAsState()
    var selectedDestinationIndex by remember { mutableIntStateOf(0) }
    var spiritualLifeInitialTab by remember { mutableIntStateOf(0) }
    var showLanguageSelector by remember { mutableStateOf(false) }
    var showCustomizerModal by remember { mutableStateOf(false) }

    val destinations = AppDestination.values()

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "SANCTA FIDES",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        fontSize = 18.sp,
                        color = SacredBurgundy
                    )
                },
                actions = {
                    IconButton(
                        onClick = { showCustomizerModal = true },
                        modifier = Modifier.testTag("app_bar_theme_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = "Personalizar Tema y Apariencia",
                            tint = SacredBurgundy,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    IconButton(
                        onClick = { showLanguageSelector = true },
                        modifier = Modifier.testTag("app_bar_lang_button")
                    ) {
                        Text(text = currentLanguage.flag, fontSize = 18.sp)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .navigationBarsPadding()
                    .testTag("bottom_nav_bar"),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                ScrollableTabRow(
                    selectedTabIndex = selectedDestinationIndex,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = SacredBurgundy,
                    edgePadding = 8.dp
                ) {
                    destinations.forEachIndexed { index, dest ->
                        val isSelected = selectedDestinationIndex == index
                        val label = when (currentLanguage) {
                            AppLanguage.ES -> dest.titleEs
                            AppLanguage.EN -> dest.titleEn
                            AppLanguage.PT -> dest.titlePt
                        }

                        Tab(
                            selected = isSelected,
                            onClick = { selectedDestinationIndex = index },
                            modifier = Modifier.testTag(dest.tag),
                            text = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = dest.icon,
                                        contentDescription = label,
                                        tint = if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = label,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        )
                    }
                }
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
                transitionSpec = { fadeIn() togetherWith fadeOut() },
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
                        onNavigateToBible = { selectedDestinationIndex = destinations.indexOf(AppDestination.BIBLE) },
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
                        ttsManager = ttsManager
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
                        currentLanguage = currentLanguage
                    )

                    AppDestination.PILGRIMAGE_HABITS -> PilgrimageAndHabitsScreen(
                        repository = repository,
                        currentLanguage = currentLanguage
                    )

                    AppDestination.DOCTRINE -> DoctrineAndHistoryScreen(
                        repository = repository,
                        currentLanguage = currentLanguage
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
            dao = dao,
            onDismiss = { showCustomizerModal = false }
        )
    }
}
