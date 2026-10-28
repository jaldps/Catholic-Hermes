package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.GuiCustomizerModal
import com.example.ui.components.LanguageSelectorDialog
import com.example.ui.screens.BibleScreen
import com.example.ui.screens.CatechismScreen
import com.example.ui.screens.DoctrineAndHistoryScreen
import com.example.ui.screens.HabitsAndVirtuesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PilgrimageAndHabitsScreen
import com.example.ui.screens.PrayersAndNovenasScreen
import com.example.ui.screens.RosaryScreen
import com.example.ui.screens.SpiritualLifeScreen
import com.example.ui.theme.AppThemePreset

enum class AppDestination(
    val titleEs: String,
    val titleEn: String,
    val titlePt: String,
    val icon: ImageVector,
    val tag: String
) {
    HOME("Inicio", "Home", "Início", Icons.Default.Home, "nav_home"),
    HABITS_VIRTUES("Hábitos y virtudes", "Habits & virtues", "Hábitos e virtudes", Icons.Default.Shield, "nav_habits"),
    BIBLE("Biblia", "Bible", "Bíblia", Icons.Default.AutoStories, "nav_bible"),
    CATECHISM("Catecismo", "Catechism", "Catecismo", Icons.Default.MenuBook, "nav_catechism"),
    ROSARY("Rosario", "Rosary", "Terço", Icons.Default.SelfImprovement, "nav_rosary"),
    PRAYERS("Oraciones", "Prayers", "Orações", Icons.Default.VolunteerActivism, "nav_prayers"),
    PILGRIMAGE_HABITS("Santuarios", "Shrines", "Santuários", Icons.Default.Place, "nav_pilgrimage"),
    DOCTRINE("Doctrina e Iglesia", "Doctrine & Church", "Doutrina e Igreja", Icons.Default.Church, "nav_doctrine"),
    CHURCHES_NEWS("Iglesias y noticias", "Churches & news", "Igrejas e notícias", Icons.Default.Place, "nav_churches_news"),
    SPIRITUAL_LIFE("Vida espiritual", "Spiritual life", "Vida espiritual", Icons.Default.Star, "nav_spiritual"),
    MORE("Más", "Explore", "Mais", Icons.Default.MoreHoriz, "nav_more")
}

private data class PrimaryTab(
    val destination: AppDestination,
    val labelEs: String,
    val labelEn: String,
    val labelPt: String,
    val appleIcon: ImageVector
) {
    fun label(language: AppLanguage): String = when (language) {
        AppLanguage.ES -> labelEs
        AppLanguage.EN -> labelEn
        AppLanguage.PT -> labelPt
    }
}

private val primaryTabs = listOf(
    PrimaryTab(AppDestination.HOME, "Inicio", "Home", "Início", Icons.Default.Home),
    PrimaryTab(AppDestination.BIBLE, "Biblia", "Bible", "Bíblia", Icons.Default.AutoStories),
    PrimaryTab(AppDestination.ROSARY, "Orar", "Pray", "Rezar", Icons.Default.SelfImprovement),
    PrimaryTab(AppDestination.CATECHISM, "Aprender", "Study", "Estudar", Icons.Default.MenuBook),
    PrimaryTab(AppDestination.MORE, "Más", "More", "Mais", Icons.Default.MoreHoriz)
)

private fun primaryIndexFor(destinationIndex: Int): Int = when (destinationIndex) {
    0 -> 0
    2 -> 1
    4, 5 -> 2
    3, 7 -> 3
    else -> 4
}

private fun destinationLabel(destination: AppDestination, language: AppLanguage): String = when (language) {
    AppLanguage.ES -> destination.titleEs
    AppLanguage.EN -> destination.titleEn
    AppLanguage.PT -> destination.titlePt
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    repository: CatholicDataRepository,
    dao: SanctaFidesDao,
    ttsManager: LiturgicalTtsManager
) {
    val currentLanguage by repository.currentLanguage.collectAsState()
    val preferences by dao.getAppPreferences().collectAsState(initial = null)
    val themePreset = AppThemePreset.normalizeId(preferences?.themePreset)
    val isApple = themePreset == AppThemePreset.APPLE.id
    val isMaterial = themePreset == AppThemePreset.MATERIAL.id
    val liturgicalDay = repository.getCurrentLiturgicalDay()
    val destinations = AppDestination.values()

    var selectedDestinationIndex by remember { mutableIntStateOf(0) }
    var spiritualLifeInitialTab by remember { mutableIntStateOf(0) }
    var showLanguageSelector by remember { mutableStateOf(false) }
    var showCustomizerModal by remember { mutableStateOf(false) }

    val activeDestination = destinations.getOrElse(selectedDestinationIndex) { AppDestination.HOME }
    val currentTitle = destinationLabel(activeDestination, currentLanguage)
    val primary = MaterialTheme.colorScheme.primary
    val surface = MaterialTheme.colorScheme.surface
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            when {
                isApple -> TopAppBar(
                    title = {
                        Text(
                            text = currentTitle,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        AppBarActions(
                            primary = primary,
                            currentLanguage = currentLanguage,
                            onOpenCustomizer = { showCustomizerModal = true },
                            onOpenLanguage = { showLanguageSelector = true }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = surface.copy(alpha = 0.94f),
                        scrolledContainerColor = surface.copy(alpha = 0.98f)
                    )
                )
                isMaterial -> TopAppBar(
                    title = {
                        Text(
                            text = currentTitle,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        AppBarActions(
                            primary = primary,
                            currentLanguage = currentLanguage,
                            onOpenCustomizer = { showCustomizerModal = true },
                            onOpenLanguage = { showLanguageSelector = true }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = surface)
                )
                else -> Column {
                    CenterAlignedTopAppBar(
                        title = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "SANCTA FIDES",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.5.sp,
                                    maxLines = 1
                                )
                                Text(
                                    text = liturgicalDay.seasonName.get(currentLanguage).uppercase(),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    letterSpacing = 1.1.sp,
                                    maxLines = 1
                                )
                            }
                        },
                        actions = {
                            AppBarActions(
                                primary = primary,
                                currentLanguage = currentLanguage,
                                onOpenCustomizer = { showCustomizerModal = true },
                                onOpenLanguage = { showLanguageSelector = true }
                            )
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = surface)
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.55f), thickness = 1.dp)
                }
            }
        },
        bottomBar = {
            when {
                isMaterial -> NavigationBar(
                    containerColor = surface,
                    tonalElevation = 3.dp,
                    modifier = Modifier.testTag("bottom_nav_bar")
                ) {
                    primaryTabs.forEachIndexed { index, tab ->
                        val selected = primaryIndexFor(selectedDestinationIndex) == index
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                selectedDestinationIndex = destinations.indexOf(tab.destination)
                            },
                            icon = {
                                Icon(
                                    imageVector = when (tab.destination) {
                                        AppDestination.HOME -> Icons.Default.Home
                                        AppDestination.BIBLE -> Icons.Default.AutoStories
                                        AppDestination.ROSARY -> Icons.Default.SelfImprovement
                                        AppDestination.CATECHISM -> Icons.Default.MenuBook
                                        else -> Icons.Default.MoreHoriz
                                    },
                                    contentDescription = tab.label(currentLanguage)
                                )
                            },
                            label = { Text(tab.label(currentLanguage), maxLines = 1) },
                            alwaysShowLabel = true,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = primary,
                                selectedTextColor = primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = onSurfaceVariant,
                                unselectedTextColor = onSurfaceVariant
                            )
                        )
                    }
                }
                else -> Surface(
                    modifier = Modifier.navigationBarsPadding().testTag("bottom_nav_bar"),
                    color = if (isApple) surface.copy(alpha = 0.94f) else surface,
                    shadowElevation = 0.dp,
                    tonalElevation = 0.dp
                ) {
                    Column {
                        HorizontalDivider(
                            color = if (isApple) MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                            else MaterialTheme.colorScheme.secondary.copy(alpha = 0.45f),
                            thickness = if (isApple) 0.5.dp else 1.dp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth().height(64.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            primaryTabs.forEachIndexed { index, tab ->
                                val selected = primaryIndexFor(selectedDestinationIndex) == index
                                val icon = if (isApple) tab.appleIcon else when (tab.destination) {
                                    AppDestination.HOME -> Icons.Default.Home
                                    AppDestination.BIBLE -> Icons.Default.AutoStories
                                    AppDestination.ROSARY -> Icons.Default.SelfImprovement
                                    AppDestination.CATECHISM -> Icons.Default.MenuBook
                                    else -> Icons.Default.MoreHoriz
                                }
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(64.dp)
                                        .selectable(
                                            selected = selected,
                                            role = Role.Tab,
                                            onClick = { selectedDestinationIndex = destinations.indexOf(tab.destination) }
                                        )
                                        .padding(horizontal = 2.dp, vertical = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    if (!isApple) {
                                        Box(
                                            modifier = Modifier
                                                .width(18.dp)
                                                .height(2.dp)
                                                .background(
                                                    if (selected) MaterialTheme.colorScheme.secondary else Color.Transparent,
                                                    MaterialTheme.shapes.extraSmall
                                                )
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                    }
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = tab.label(currentLanguage),
                                        tint = if (selected) primary else onSurfaceVariant,
                                        modifier = Modifier.size(if (isApple) 22.dp else 20.dp)
                                    )
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(
                                        text = tab.label(currentLanguage),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                                        color = if (selected) primary else onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        BackHandler(enabled = selectedDestinationIndex != 0) {
            selectedDestinationIndex = 0
        }

        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
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
                        liturgicalDay = liturgicalDay,
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
                    AppDestination.HABITS_VIRTUES -> HabitsAndVirtuesScreen(repository, dao, ttsManager)
                    AppDestination.BIBLE -> BibleScreen(repository, currentLanguage, dao, ttsManager)
                    AppDestination.CATECHISM -> CatechismScreen(repository, currentLanguage)
                    AppDestination.ROSARY -> RosaryScreen(repository, ttsManager, currentLanguage)
                    AppDestination.PRAYERS -> PrayersAndNovenasScreen(repository, ttsManager, currentLanguage)
                    AppDestination.PILGRIMAGE_HABITS -> PilgrimageAndHabitsScreen(repository, currentLanguage)
                    AppDestination.DOCTRINE -> DoctrineAndHistoryScreen(repository, currentLanguage)
                    AppDestination.CHURCHES_NEWS -> DoctrineAndHistoryScreen(repository, currentLanguage, initialTopTab = 3)
                    AppDestination.SPIRITUAL_LIFE -> SpiritualLifeScreen(
                        repository = repository,
                        dao = dao,
                        ttsManager = ttsManager,
                        currentLanguage = currentLanguage,
                        initialTab = spiritualLifeInitialTab
                    )
                    AppDestination.MORE -> MoreScreen(
                        currentLanguage = currentLanguage,
                        themePreset = themePreset,
                        onNavigate = { destination -> selectedDestinationIndex = destinations.indexOf(destination) }
                    )
                }
            }
        }
    }

    if (showLanguageSelector) {
        LanguageSelectorDialog(
            currentLanguage = currentLanguage,
            onLanguageSelected = { newLanguage ->
                repository.setLanguage(newLanguage)
                ttsManager.setLanguage(newLanguage)
            },
            onDismissRequest = { showLanguageSelector = false }
        )
    }
    if (showCustomizerModal) {
        GuiCustomizerModal(
            dao = dao,
            currentLanguage = currentLanguage,
            onDismiss = { showCustomizerModal = false }
        )
    }
}

@Composable
private fun AppBarActions(
    primary: Color,
    currentLanguage: AppLanguage,
    onOpenCustomizer: () -> Unit,
    onOpenLanguage: () -> Unit
) {
    IconButton(onClick = onOpenCustomizer, modifier = Modifier.testTag("app_bar_theme_button")) {
        Icon(Icons.Default.Palette, contentDescription = "Customize visual identity", tint = primary, modifier = Modifier.size(22.dp))
    }
    IconButton(onClick = onOpenLanguage, modifier = Modifier.testTag("app_bar_lang_button")) {
        Text(text = currentLanguage.flag, fontSize = 18.sp)
    }
}
