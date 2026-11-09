package com.example.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.AllInclusive
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material.icons.outlined.WbTwilight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.LiturgicalDayInfo
import com.example.data.liturgy.LiturgicalSeason
import com.example.data.local.NovenaProgressEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.models.Novena
import com.example.data.models.PrayerCategory
import com.example.data.models.VaticanPrayer
import com.example.data.prefs.UserPreferences
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppActionBar
import com.example.ui.design.AppActionBarItem
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppCategoryTile
import com.example.ui.design.AppCheckRow
import com.example.ui.design.AppConfirmDialog
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppGrid
import com.example.ui.design.AppGroupedRow
import com.example.ui.design.AppHideTabBar
import com.example.ui.design.AppImageCard
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppKitChip
import com.example.ui.design.AppLargeTitleHeader
import com.example.ui.design.AppMenuChip
import com.example.ui.design.AppMenuItem
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppReadingSettingsSheet
import com.example.ui.design.AppRemoteImage
import com.example.ui.design.AppRoundPlayButton
import com.example.ui.design.AppSearchBar
import com.example.ui.design.AppSeasonDot
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.design.AppTransportBar
import com.example.ui.design.GroupPosition
import com.example.ui.design.LocalPreferencesStore
import com.example.ui.design.ReadingSheetLabels
import com.example.ui.design.appScreenInsets
import com.example.ui.design.groupedItem
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenLiturgicalColor
import com.example.ui.design.lumenPress
import com.example.ui.design.rememberFirstAsset
import com.example.ui.design.statusBarHeight
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.text.DateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

/*
 * Prayers, novenas and the night office — Lumen.
 *
 * One tab, three segments. Each segment is a calm root (large title, the
 * segmented control, then what you came for) and every deeper page is an
 * in-screen detail whose back is the scaffold's single glass back button
 * ([AppBackHandler]). Long prayer text is set in the reader font
 * (`styles.reading`) and the "Aa" action opens the shared reading sheet.
 */

/** The chaplet: a lead bead and ten small ones, five times over. */
private const val ChapletDecades = 5
private const val ChapletBeadsPerDecade = 10

/** The lead bead of a decade is cut larger than the ten that follow it. */
private const val ChapletLeadBeadScale = 1.45f

/** Image cards (DESIGN.md: image card radius 22). */
private val ImageCardShape = RoundedCornerShape(22.dp)

/** Id of the Divine Mercy novena in the official (crawled) catalogue. */
private const val DivineMercyNovenaId = "novena-a-la-divina-misericordia"

/** Art used across the area: Lumen PD art first, older bundled photo second. */
private const val ArtLily = "heroes/lumen/prayers_lily.jpg"
private const val ArtMary = "heroes/lumen/mary.jpg"
private const val ArtMaryJesus = "heroes/lumen/mary_jesus.jpg"
private const val ArtChrist = "heroes/lumen/christ_thorns.jpg"
private const val ArtAngelus = "heroes/lumen/angelus.jpg"
private const val ArtMass = "heroes/lumen/mass.jpg"
private const val ArtNight = "heroes/lumen/night.jpg"
private const val ArtMorning = "heroes/lumen/habits.jpg"
private const val ArtMeadow = "heroes/lumen/explore.jpg"
private const val ArtSaints = "heroes/lumen/orders.jpg"
private const val ArtChurch = "heroes/lumen/doctrine.jpg"
private const val ArtSunrise = "heroes/lumen/questions.jpg"
private const val ArtDivineMercy = "heroes/mysteries/divine_mercy.jpg"
private const val ArtAnnunciation = "heroes/mysteries/joyful_1_annunciation.jpg"
private const val ArtNativity = "heroes/mysteries/joyful_3_nativity.jpg"
private const val ArtResurrection = "heroes/mysteries/glorious_1_resurrection.jpg"
private const val ArtPentecost = "heroes/mysteries/glorious_3_pentecost.jpg"
private const val ArtCarrying = "heroes/mysteries/sorrowful_4_carrying.jpg"

/** Routes inside the Prayers / Novenas segments. */
private sealed interface PrayersRoute {
    data object Landing : PrayersRoute
    data class Category(val key: String) : PrayersRoute
    data object HowTo : PrayersRoute
    data object Chaplet : PrayersRoute
    data class Prayer(val id: String) : PrayersRoute
    data object NovenaIndex : PrayersRoute
    data class NovenaOverview(val id: String) : PrayersRoute
    data class NovenaDay(val id: String, val day: Int) : PrayersRoute
    data object Night : PrayersRoute
}

/**
 * The Vatican devotional, the chaplet of Divine Mercy, the 246 official
 * novenas and the night office, behind one segmented control.
 */
@Composable
fun PrayersAndNovenasScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    dao: SanctaFidesDao? = null,
    initialPrayerId: String? = null,
    /** Tab opened on arrival (0 = Prayers, 1 = Novenas, 2 = Night). */
    initialTab: Int = 0,
    /** Night office opened on arrival when [initialTab] is 2. */
    initialNightOfficeTab: Int = 0,
    /** Opens the Divine Mercy chaplet on arrival instead of the prayers list. */
    initialOpenChaplet: Boolean = false
) {
    val insets = AppEdgeToEdge()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var selectedTab by rememberSaveable { mutableIntStateOf(initialTab.coerceIn(0, 2)) }

    // ---- Prayers segment
    var prayerSearchQuery by rememberSaveable { mutableStateOf("") }
    var prayerSearchOpen by rememberSaveable { mutableStateOf(false) }
    var openPrayerId by rememberSaveable { mutableStateOf<String?>(null) }
    var categoryKey by rememberSaveable { mutableStateOf<String?>(null) }
    var showHowTo by rememberSaveable { mutableStateOf(false) }
    var showChaplet by rememberSaveable { mutableStateOf(false) }

    // A deep link opens its prayer once per arrival; an unknown id is ignored.
    var consumedPrayerLink by rememberSaveable { mutableStateOf<String?>(null) }
    LaunchedEffect(initialPrayerId) {
        if (initialPrayerId != null && initialPrayerId != consumedPrayerLink &&
            repository.vaticanPrayers.any { it.id == initialPrayerId }
        ) {
            consumedPrayerLink = initialPrayerId
            selectedTab = 0
            openPrayerId = initialPrayerId
        }
    }
    var consumedChapletLink by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(initialOpenChaplet) {
        if (initialOpenChaplet && !consumedChapletLink) {
            consumedChapletLink = true
            selectedTab = 0
            openPrayerId = null
            showChaplet = true
        }
    }

    // ---- Novenas segment
    var novenaId by rememberSaveable { mutableStateOf<String?>(null) }
    var novenaReaderDay by rememberSaveable { mutableIntStateOf(0) } // 0 = overview
    var novenaSearchQuery by rememberSaveable { mutableStateOf("") }
    var novenaSearchOpen by rememberSaveable { mutableStateOf(false) }
    var novenaKind by rememberSaveable { mutableStateOf(NovenaKind.ALL.name) }
    var selectedNovenaTag by rememberSaveable { mutableStateOf<String?>(null) }

    // ---- Night segment: a deep link opens its office's reader directly.
    var nightOffice by rememberSaveable {
        mutableIntStateOf(if (initialTab == 2) initialNightOfficeTab.coerceIn(0, 2) else -1)
    }

    // ---- Novena progress (Room + a parallel store for days beyond 31)
    val extraStore = remember(context) { NovenaExtraDaysStore(context.applicationContext) }
    var extraDays by remember { mutableStateOf(extraStore.all()) }
    var sessionDaysPrayed by remember { mutableStateOf(emptyMap<String, Set<Int>>()) }
    val persistedProgress by remember(dao) {
        dao?.getAllNovenaProgress() ?: flowOf(emptyList<NovenaProgressEntity>())
    }.collectAsState(initial = emptyList())
    val startedOn = remember(persistedProgress) {
        persistedProgress.associate { it.novenaId to it.startDate }
    }
    val daysPrayed = remember(persistedProgress, sessionDaysPrayed, extraDays) {
        val stored = persistedProgress.associate { it.novenaId to decodeNovenaDays(it.currentDay) }
        (stored.keys + sessionDaysPrayed.keys + extraDays.keys).associateWith { id ->
            // The session's set is the optimistic echo of the last write; it
            // is authoritative for that novena once it exists.
            sessionDaysPrayed[id] ?: (stored[id].orEmpty() + extraDays[id].orEmpty())
        }
    }

    fun writeNovenaDays(novena: Novena, prayed: Set<Int>) {
        sessionDaysPrayed = sessionDaysPrayed + (novena.id to prayed)
        extraStore.put(novena.id, prayed.filter { it > 31 }.toSet())
        extraDays = extraStore.all()
        if (dao != null) {
            scope.launch {
                val existing = dao.getNovenaProgress(novena.id)
                val now = System.currentTimeMillis()
                dao.saveNovenaProgress(
                    NovenaProgressEntity(
                        novenaId = novena.id,
                        currentDay = encodeNovenaDays(prayed),
                        personalIntention = existing?.personalIntention ?: "",
                        startDate = existing?.startDate ?: now,
                        lastUpdated = now,
                        isCompleted = isNovenaFinished(novena, prayed)
                    )
                )
            }
        }
    }

    fun toggleNovenaDay(novena: Novena, day: Int) {
        val current = daysPrayed[novena.id].orEmpty()
        writeNovenaDays(novena, if (day in current) current - day else current + day)
    }

    fun resetNovena(novena: Novena) {
        sessionDaysPrayed = sessionDaysPrayed + (novena.id to emptySet())
        extraStore.put(novena.id, emptySet())
        extraDays = extraStore.all()
        if (dao != null) scope.launch { dao.deleteNovenaProgress(novena.id) }
    }

    val currentlySpeaking by ttsManager.currentlySpeaking.collectAsState()

    val route: PrayersRoute = when (selectedTab) {
        0 -> when {
            openPrayerId != null && repository.vaticanPrayers.any { it.id == openPrayerId } ->
                PrayersRoute.Prayer(openPrayerId!!)
            showChaplet -> PrayersRoute.Chaplet
            showHowTo -> PrayersRoute.HowTo
            categoryKey != null -> PrayersRoute.Category(categoryKey!!)
            else -> PrayersRoute.Landing
        }
        1 -> {
            val id = novenaId
            when {
                id == null || repository.officialNovenas.none { it.id == id } -> PrayersRoute.NovenaIndex
                novenaReaderDay > 0 -> PrayersRoute.NovenaDay(id, novenaReaderDay)
                else -> PrayersRoute.NovenaOverview(id)
            }
        }
        else -> PrayersRoute.Night
    }

    // The tab root's title and view switcher, as the first rows of each list.
    val header: LazyListScope.() -> Unit = {
        item(key = "prayers_screen_title") {
            AppLargeTitleHeader(title = currentLanguage.pick("Oraciones", "Prayers", "Orações")) {
                if (selectedTab != 2) {
                    val open = if (selectedTab == 0) prayerSearchOpen else novenaSearchOpen
                    AppGlassIconButton(
                        icon = Icons.Outlined.Search,
                        contentDescription = currentLanguage.pick("Buscar", "Search", "Buscar"),
                        onClick = {
                            if (selectedTab == 0) {
                                prayerSearchOpen = !open
                                if (open) prayerSearchQuery = ""
                            } else {
                                novenaSearchOpen = !open
                                if (open) novenaSearchQuery = ""
                            }
                        },
                        tag = "prayers_search_toggle"
                    )
                }
            }
        }
        item(key = "prayers_screen_tabs") {
            AppSegmentedTabs(
                options = listOf(
                    currentLanguage.pick("Oraciones", "Prayers", "Orações"),
                    currentLanguage.pick("Novenas", "Novenas", "Novenas"),
                    currentLanguage.pick("Noche", "Night", "Noite")
                ),
                selectedIndex = selectedTab,
                onSelect = { selectedTab = it },
                modifier = Modifier.testTag("prayers_segments")
            )
        }
        if (selectedTab == 0 && (prayerSearchOpen || prayerSearchQuery.isNotEmpty())) {
            item(key = "prayer_search") {
                Spacer(Modifier.height(14.dp))
                AppSearchBar(
                    value = prayerSearchQuery,
                    onValueChange = { prayerSearchQuery = it },
                    placeholder = currentLanguage.pick("Buscar una oración", "Search prayers", "Buscar uma oração"),
                    tag = "prayer_search_input"
                )
            }
        }
        if (selectedTab == 1 && (novenaSearchOpen || novenaSearchQuery.isNotEmpty())) {
            item(key = "novena_search") {
                Spacer(Modifier.height(14.dp))
                AppSearchBar(
                    value = novenaSearchQuery,
                    onValueChange = { novenaSearchQuery = it },
                    placeholder = currentLanguage.pick("Buscar novena", "Search novenas", "Buscar novena"),
                    tag = "novena_search_input"
                )
            }
        }
    }

    val bottomPadding = insets.calculateBottomPadding() + 32.dp

    Box(
        modifier
            .fillMaxSize()
            .testTag("prayers_and_novenas_screen")
    ) {
        Crossfade(targetState = route, animationSpec = tween(240), label = "prayersRoute") { r ->
            when (r) {
                PrayersRoute.Landing -> PrayersLanding(
                    header = header,
                    repository = repository,
                    currentLanguage = currentLanguage,
                    searchQuery = prayerSearchQuery,
                    onClearSearch = { prayerSearchQuery = "" },
                    currentlySpeaking = currentlySpeaking,
                    onListen = { p ->
                        if (currentlySpeaking != null) ttsManager.stop()
                        else ttsManager.speakDialogue(
                            p.leaderText.get(currentLanguage),
                            p.responseText?.get(currentLanguage) ?: ""
                        )
                    },
                    onOpenPrayer = { openPrayerId = it.id },
                    onOpenCategory = { categoryKey = it },
                    onOpenChaplet = { showChaplet = true },
                    onOpenHowTo = { showHowTo = true },
                    bottomPadding = bottomPadding
                )

                is PrayersRoute.Category -> {
                    AppBackHandler { categoryKey = null }
                    PrayerCategoryPage(
                        key = r.key,
                        repository = repository,
                        currentLanguage = currentLanguage,
                        onOpenPrayer = { openPrayerId = it.id },
                        bottomPadding = bottomPadding
                    )
                }

                PrayersRoute.HowTo -> {
                    AppBackHandler { showHowTo = false }
                    HowToPrayPage(currentLanguage = currentLanguage, bottomPadding = bottomPadding)
                }

                PrayersRoute.Chaplet -> {
                    AppBackHandler { showChaplet = false }
                    DivineMercyChapletView(
                        ttsManager = ttsManager,
                        currentLanguage = currentLanguage,
                        novenaArtUrl = repository.officialNovenas.firstOrNull { it.id == DivineMercyNovenaId }?.imageUrl,
                        onOpenNovena = {
                            showChaplet = false
                            selectedTab = 1
                            novenaId = repository.officialNovenas
                                .firstOrNull { it.id == DivineMercyNovenaId }?.id
                                ?: repository.officialNovenas.firstOrNull {
                                    it.title.es.contains("Misericordia", ignoreCase = true)
                                }?.id
                            novenaReaderDay = 0
                        },
                        bottomPadding = bottomPadding
                    )
                }

                is PrayersRoute.Prayer -> {
                    val prayer = repository.vaticanPrayers.first { it.id == r.id }
                    AppBackHandler { openPrayerId = null }
                    PrayerDetailView(
                        prayer = prayer,
                        currentLanguage = currentLanguage,
                        currentlySpeaking = currentlySpeaking,
                        onPlayAudio = {
                            ttsManager.speakDialogue(
                                prayer.leaderText.get(currentLanguage),
                                prayer.responseText?.get(currentLanguage) ?: ""
                            )
                        },
                        onStopAudio = { ttsManager.stop() },
                        bottomPadding = bottomPadding
                    )
                }

                PrayersRoute.NovenaIndex -> NovenaIndexView(
                    header = header,
                    novenas = repository.officialNovenas,
                    currentLanguage = currentLanguage,
                    searchQuery = novenaSearchQuery,
                    onSearchQueryChange = { novenaSearchQuery = it },
                    kind = NovenaKind.valueOf(novenaKind),
                    onKind = { novenaKind = it.name },
                    selectedTag = selectedNovenaTag,
                    onSelectTag = { selectedNovenaTag = it },
                    daysPrayed = daysPrayed,
                    startedOn = startedOn,
                    onOpenNovena = {
                        novenaId = it.id
                        novenaReaderDay = 0
                    },
                    bottomPadding = bottomPadding
                )

                is PrayersRoute.NovenaOverview -> {
                    val novena = repository.officialNovenas.first { it.id == r.id }
                    AppBackHandler { novenaId = null }
                    NovenaOverviewView(
                        novena = novena,
                        currentLanguage = currentLanguage,
                        daysPrayed = daysPrayed[novena.id].orEmpty(),
                        startedOn = startedOn[novena.id],
                        onOpenDay = { novenaReaderDay = it },
                        onToggleDay = { toggleNovenaDay(novena, it) },
                        onReset = { resetNovena(novena) },
                        bottomPadding = bottomPadding
                    )
                }

                is PrayersRoute.NovenaDay -> {
                    val novena = repository.officialNovenas.first { it.id == r.id }
                    AppBackHandler { novenaReaderDay = 0 }
                    NovenaDayView(
                        novena = novena,
                        currentLanguage = currentLanguage,
                        activeDay = r.day.coerceIn(1, novena.days.maxOfOrNull { it.dayNumber } ?: 1),
                        onSelectDay = { novenaReaderDay = it },
                        daysPrayed = daysPrayed[novena.id].orEmpty(),
                        currentlySpeaking = currentlySpeaking,
                        onListen = { ttsManager.speakSingle(it) },
                        onStop = { ttsManager.stop() },
                        onToggleDay = { toggleNovenaDay(novena, it) }
                    )
                }

                PrayersRoute.Night -> NightPrayersScreen(
                    repository = repository,
                    ttsManager = ttsManager,
                    currentLanguage = currentLanguage,
                    header = header,
                    openOffice = nightOffice,
                    onOpenOffice = { nightOffice = it }
                )
            }
        }
    }
}

// =================================================================== landing

/** One prayer-category tile: a thematic tag or a [PrayerCategory]; "all" carries neither. */
private data class PrayerCategoryTile(
    val key: String,
    val label: String,
    val icon: ImageVector,
    val tag: String?,
    val category: PrayerCategory?
)

private fun prayerTiles(language: AppLanguage): List<PrayerCategoryTile> = listOf(
    PrayerCategoryTile("basic", language.pick("Básicas", "Basics", "Básicas"), Icons.Outlined.FavoriteBorder, null, PrayerCategory.BASIC),
    PrayerCategoryTile("all", language.pick("Intenciones", "Intentions", "Intenções"), Icons.Outlined.Spa, null, null),
    PrayerCategoryTile("family", language.pick("Familia", "Family", "Família"), Icons.Outlined.People, "family", null),
    PrayerCategoryTile("protection", language.pick("Protección", "Protection", "Proteção"), Icons.Outlined.Shield, "protection", null),
    PrayerCategoryTile("health", language.pick("Sanación", "Healing", "Cura"), Icons.Outlined.AutoAwesome, "health", null),
    PrayerCategoryTile("forgiveness", language.pick("Perdón", "Forgiveness", "Perdão"), Icons.Outlined.AllInclusive, "forgiveness", null)
)

/** The Church's first prayers, in the order a child learns them. */
private val FundamentalOrder = listOf("sign_of_the_cross", "our_father", "hail_mary", "glory_be", "creed")

/** Seasonal prayers: id → season they belong to. */
private val SeasonalPrayers = listOf(
    "advent_antiphons" to LiturgicalSeason.ADVENT,
    "christmas_prayer" to LiturgicalSeason.CHRISTMAS,
    "lenten_prayer" to LiturgicalSeason.LENT,
    "easter_prayer" to LiturgicalSeason.EASTER,
    "regina_caeli" to LiturgicalSeason.EASTER
)

private fun seasonDot(season: LiturgicalSeason): Color = when (season) {
    LiturgicalSeason.ADVENT, LiturgicalSeason.LENT, LiturgicalSeason.HOLY_WEEK -> lumenLiturgicalColor("violet")
    LiturgicalSeason.CHRISTMAS, LiturgicalSeason.EASTER -> lumenLiturgicalColor("white")
    LiturgicalSeason.TRIDUUM -> lumenLiturgicalColor("red")
    LiturgicalSeason.ORDINARY_TIME -> lumenLiturgicalColor("green")
}

private fun categoryIcon(category: PrayerCategory): ImageVector = when (category) {
    PrayerCategory.BASIC -> Icons.Outlined.FavoriteBorder
    PrayerCategory.MORNING_NIGHT -> Icons.Outlined.WbTwilight
    PrayerCategory.MARIAN -> Icons.Outlined.LocalFlorist
    PrayerCategory.EUCHARISTIC -> Icons.Outlined.WbSunny
    PrayerCategory.HOLY_SPIRIT -> Icons.Outlined.LocalFireDepartment
    PrayerCategory.PROTECTION -> Icons.Outlined.Shield
    PrayerCategory.INTERCESSION -> Icons.Outlined.VolunteerActivism
    PrayerCategory.ANGELUS -> Icons.Outlined.NotificationsNone
}

private fun prayerGroupPosition(index: Int, size: Int): GroupPosition = when {
    size == 1 -> GroupPosition.Single
    index == 0 -> GroupPosition.First
    index == size - 1 -> GroupPosition.Last
    else -> GroupPosition.Middle
}

@Composable
private fun PrayersLanding(
    header: LazyListScope.() -> Unit,
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    searchQuery: String,
    onClearSearch: () -> Unit,
    currentlySpeaking: String?,
    onListen: (VaticanPrayer) -> Unit,
    onOpenPrayer: (VaticanPrayer) -> Unit,
    onOpenCategory: (String) -> Unit,
    onOpenChaplet: () -> Unit,
    onOpenHowTo: () -> Unit,
    bottomPadding: Dp
) {
    val design = LocalAppDesign.current
    val all = repository.vaticanPrayers
    val dayInfo = remember { LiturgicalCalendar.dayInfo(LiturgicalCalendar.today()) }
    val seasonPrayer = remember(all) {
        val id = seasonPrayerId(dayInfo)
        all.firstOrNull { it.id == id } ?: all.firstOrNull { it.id == "our_father" } ?: all.firstOrNull()
    }

    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = bottomPadding)) {
        header()

        if (searchQuery.isNotBlank()) {
            val results = repository.searchPrayers(query = searchQuery)
            item(key = "results_header") {
                PrayerSectionHeader(
                    title = currentLanguage.pick("Resultados", "Results", "Resultados"),
                    trailing = "${results.size}"
                )
            }
            if (results.isEmpty()) {
                item(key = "results_empty") {
                    EmptyCard(
                        title = currentLanguage.pick("Ninguna oración coincide", "No matching prayers", "Nenhuma oração corresponde"),
                        body = currentLanguage.pick(
                            "Prueba otra palabra o elige otra intención.",
                            "Try another word or choose another intention.",
                            "Tente outra palavra ou escolha outra intenção."
                        ),
                        actionLabel = currentLanguage.pick("Ver todas", "Show all prayers", "Ver todas"),
                        actionTag = "prayer_clear_filters",
                        onAction = onClearSearch
                    )
                }
            } else {
                itemsIndexed(results, key = { _, p -> "result_${p.id}" }) { index, prayer ->
                    AppGroupedRow(
                        title = prayer.title.get(currentLanguage),
                        subtitle = prayer.category.title.get(currentLanguage),
                        position = prayerGroupPosition(index, results.size),
                        onClick = { onOpenPrayer(prayer) },
                        tag = "prayer_row_${prayer.id}"
                    )
                }
            }
            return@LazyColumn
        }

        item(key = "tiles") {
            Spacer(Modifier.height(20.dp))
            AppGrid(
                items = prayerTiles(currentLanguage),
                columns = 3,
                modifier = Modifier.testTag("prayer_theme_picker")
            ) { tile, m ->
                AppCategoryTile(
                    label = tile.label,
                    icon = tile.icon,
                    onClick = { onOpenCategory(tile.key) },
                    modifier = m,
                    tag = "prayer_category_tile_${tile.key}"
                )
            }
        }

        if (seasonPrayer != null) {
            item(key = "season_prayer") {
                PrayerSectionHeader(currentLanguage.pick("Para este tiempo", "For this season", "Para este tempo"))
                SeasonPrayerCard(
                    prayer = seasonPrayer,
                    dayInfo = dayInfo,
                    currentLanguage = currentLanguage,
                    speaking = currentlySpeaking != null,
                    onOpen = { onOpenPrayer(seasonPrayer) },
                    onListen = { onListen(seasonPrayer) }
                )
            }
        }

        item(key = "fundamentals") {
            val fundamentals = FundamentalOrder.mapNotNull { id -> all.firstOrNull { it.id == id } }
            val basicCount = all.count { it.category == PrayerCategory.BASIC }
            PrayerSectionHeader(currentLanguage.pick("Fundamentales", "Fundamentals", "Fundamentais"))
            Column {
                val rows = fundamentals.size + 1
                fundamentals.forEachIndexed { i, prayer ->
                    AppGroupedRow(
                        title = prayer.title.get(currentLanguage),
                        position = prayerGroupPosition(i, rows),
                        onClick = { onOpenPrayer(prayer) },
                        tag = "prayer_row_${prayer.id}"
                    )
                }
                AppGroupedRow(
                    title = currentLanguage.pick("Ver todas", "See all", "Ver todas"),
                    trailingText = "$basicCount",
                    position = GroupPosition.Last,
                    onClick = { onOpenCategory("basic") },
                    tag = "prayer_fundamentals_all"
                )
            }
        }

        item(key = "guided") {
            PrayerSectionHeader(currentLanguage.pick("Guiadas", "Guided", "Guiadas"))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("how_to_pray_section"),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AppImageCard(
                    title = currentLanguage.pick(
                        "Coronilla de la Divina Misericordia",
                        "Chaplet of Divine Mercy",
                        "Terço da Divina Misericórdia"
                    ),
                    onClick = onOpenChaplet,
                    assetPath = rememberFirstAsset(ArtDivineMercy, "heroes/divine_mercy.jpg"),
                    meta = currentLanguage.pick("5 decenas", "5 decades", "5 dezenas"),
                    aspectRatio = 0.86f,
                    modifier = Modifier.weight(1f),
                    tag = "open_chaplet_row"
                )
                AppImageCard(
                    title = currentLanguage.pick("Cómo orar", "How to pray", "Como orar"),
                    onClick = onOpenHowTo,
                    assetPath = rememberFirstAsset(ArtSunrise, "heroes/sunrise_mountains.jpg"),
                    meta = currentLanguage.pick("5 pasos", "5 steps", "5 passos"),
                    aspectRatio = 0.86f,
                    modifier = Modifier.weight(1f),
                    tag = "how_to_pray_toggle"
                )
            }
        }

        item(key = "seasons") {
            val rows = SeasonalPrayers.mapNotNull { (id, season) -> all.firstOrNull { it.id == id }?.let { it to season } }
            if (rows.isNotEmpty()) {
                PrayerSectionHeader(currentLanguage.pick("Tiempos litúrgicos", "Liturgical seasons", "Tempos litúrgicos"))
                Column {
                    rows.forEachIndexed { i, (prayer, season) ->
                        val now = season == dayInfo.season ||
                            (season == LiturgicalSeason.LENT && dayInfo.season == LiturgicalSeason.HOLY_WEEK)
                        DotRow(
                            title = prayer.title.get(currentLanguage),
                            subtitle = season.displayName.get(currentLanguage),
                            dot = seasonDot(season),
                            trailingText = if (now) currentLanguage.pick("Ahora", "Now", "Agora") else null,
                            position = prayerGroupPosition(i, rows.size),
                            onClick = { onOpenPrayer(prayer) },
                            tag = "prayer_row_${prayer.id}"
                        )
                    }
                }
            }
        }

        item(key = "devotions") {
            val cats = PrayerCategory.entries.filter { it != PrayerCategory.BASIC }
                .map { c -> c to all.count { it.category == c } }
                .filter { it.second > 0 }
            PrayerSectionHeader(currentLanguage.pick("Más devociones", "More devotions", "Mais devoções"))
            Column {
                cats.forEachIndexed { i, (category, count) ->
                    AppGroupedRow(
                        title = categoryShortTitle(category, currentLanguage),
                        leadingIcon = categoryIcon(category),
                        trailingText = "$count",
                        position = prayerGroupPosition(i, cats.size),
                        onClick = { onOpenCategory("cat_${category.name}") },
                        tag = "prayer_devotion_${category.name.lowercase()}"
                    )
                }
            }
        }
    }
}

private fun categoryShortTitle(category: PrayerCategory, language: AppLanguage): String = when (category) {
    PrayerCategory.BASIC -> language.pick("Fundamentales", "Fundamentals", "Fundamentais")
    PrayerCategory.MORNING_NIGHT -> language.pick("Mañana y noche", "Morning and night", "Manhã e noite")
    PrayerCategory.MARIAN -> language.pick("Devoción mariana", "Marian devotion", "Devoção mariana")
    PrayerCategory.EUCHARISTIC -> language.pick("Eucaristía y adoración", "Eucharist and adoration", "Eucaristia e adoração")
    PrayerCategory.HOLY_SPIRIT -> language.pick("Al Espíritu Santo", "To the Holy Spirit", "Ao Espírito Santo")
    PrayerCategory.PROTECTION -> language.pick("Protección y sanación", "Protection and healing", "Proteção e cura")
    PrayerCategory.INTERCESSION -> language.pick("Santos y peticiones", "Saints and petitions", "Santos e pedidos")
    PrayerCategory.ANGELUS -> language.pick("Ángelus y Regina Caeli", "Angelus and Regina Caeli", "Angelus e Regina Caeli")
}

/** The season's prayer: dot + season eyebrow, title, a two-line excerpt, listen. */
@Composable
private fun SeasonPrayerCard(
    prayer: VaticanPrayer,
    dayInfo: LiturgicalDayInfo,
    currentLanguage: AppLanguage,
    speaking: Boolean,
    onOpen: () -> Unit,
    onListen: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val season = dayInfo.season.displayName.get(currentLanguage)
    val week = if (dayInfo.weekOfSeason > 0) {
        currentLanguage.pick("Semana ${dayInfo.weekOfSeason}", "Week ${dayInfo.weekOfSeason}", "Semana ${dayInfo.weekOfSeason}")
    } else null
    AppGlassSurface(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .testTag("season_prayer"),
        onClick = onOpen,
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppSeasonDot(lumenLiturgicalColor(dayInfo.color.name, dayInfo.color.hex), size = 9.dp)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        listOfNotNull(season, week).joinToString(" · "),
                        style = styles.footnote,
                        color = p.textSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(prayer.title.get(currentLanguage), style = styles.title2, color = p.textPrimary)
                Spacer(Modifier.height(6.dp))
                Text(
                    plainPrayerText(prayer.leaderText.get(currentLanguage)),
                    style = styles.callout,
                    color = p.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.width(12.dp))
            AppRoundPlayButton(
                playing = speaking,
                onClick = onListen,
                contentDescription = if (speaking) currentLanguage.pick("Detener", "Stop", "Parar")
                else currentLanguage.pick("Escuchar", "Listen", "Ouvir"),
                tag = "season_prayer_listen"
            )
        }
    }
}

/** A grouped row led by a liturgical colour dot. */
@Composable
private fun DotRow(
    title: String,
    subtitle: String?,
    dot: Color,
    trailingText: String?,
    position: GroupPosition,
    onClick: () -> Unit,
    tag: String
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    Column(Modifier.groupedItem(position).testTag(tag)) {
        Row(
            Modifier
                .fillMaxWidth()
                .lumenPress(onClick, 0.99f)
                .heightIn(min = design.spacing.rowMinHeight)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.width(22.dp), contentAlignment = Alignment.Center) { AppSeasonDot(dot, size = 10.dp) }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = styles.body, color = p.textPrimary)
                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(subtitle, style = styles.footnote, color = p.textSecondary)
                }
            }
            if (trailingText != null) {
                Spacer(Modifier.width(10.dp))
                Text(trailingText, style = styles.subhead, color = p.textSecondary, maxLines = 1)
            }
            Spacer(Modifier.width(10.dp))
            com.example.ui.design.AppChevron()
        }
        if (position == GroupPosition.First || position == GroupPosition.Middle) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 56.dp)
                    .height(0.5.dp)
                    .background(p.separator)
            )
        }
    }
}

/** Section title (title3) with optional trailing count, on the 28dp section rhythm. */
@Composable
internal fun PrayerSectionHeader(title: String, trailing: String? = null, modifier: Modifier = Modifier) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(top = design.spacing.sectionGap, bottom = 10.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            title,
            style = styles.title3,
            color = design.palette.textPrimary,
            modifier = Modifier
                .weight(1f)
                .semantics { heading() }
        )
        if (trailing != null) {
            Text(trailing, style = styles.subhead, color = design.palette.textSecondary)
        }
    }
}

/** A designed empty state: glass card, outline glyph, copy and a way back. */
@Composable
private fun EmptyCard(
    title: String,
    body: String,
    actionLabel: String,
    actionTag: String,
    onAction: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    AppGlassSurface(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 28.dp)
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Outlined.SearchOff, null, tint = p.textSecondary, modifier = Modifier.size(28.dp))
            Spacer(Modifier.height(12.dp))
            Text(title, style = styles.headline, color = p.textPrimary, textAlign = TextAlign.Center)
            Spacer(Modifier.height(4.dp))
            Text(body, style = styles.subhead, color = p.textSecondary, textAlign = TextAlign.Center)
            Spacer(Modifier.height(18.dp))
            AppSecondaryCapsule(label = actionLabel, onClick = onAction, tag = actionTag)
        }
    }
}

/**
 * Lectionary keys of the Marian solemnities/feasts in the General Calendar:
 * on these, the season prayer yields to a Marian prayer.
 */
private val MarianSeasonKeys = setOf(
    "mary_mother_of_god",
    "annunciation",
    "assumption",
    "immaculate_conception"
)

/** The season prayer's id, resolved by stable data ids. */
private fun seasonPrayerId(info: LiturgicalDayInfo): String = when {
    info.lectionaryKey in MarianSeasonKeys -> "memorare"
    info.season == LiturgicalSeason.LENT ||
        info.season == LiturgicalSeason.HOLY_WEEK ||
        info.season == LiturgicalSeason.TRIDUUM -> "act_of_contrition"
    info.season == LiturgicalSeason.ADVENT -> "advent_antiphons"
    info.season == LiturgicalSeason.EASTER -> "easter_prayer"
    info.season == LiturgicalSeason.CHRISTMAS -> "christmas_prayer"
    else -> "our_father"
}

// ============================================================ category page

private val IntentionChipTags = listOf("peace", "work", "faith", "mercy", "trust", "mission")

private fun intentionLabel(tag: String, language: AppLanguage): String = when (tag) {
    "peace" -> language.pick("Paz", "Peace", "Paz")
    "work" -> language.pick("Trabajo", "Work", "Trabalho")
    "faith" -> language.pick("Fe", "Faith", "Fé")
    "mercy" -> language.pick("Misericordia", "Mercy", "Misericórdia")
    "trust" -> language.pick("Confianza", "Trust", "Confiança")
    "mission" -> language.pick("Misiones", "Missions", "Missões")
    else -> tag
}

private fun categoryArt(key: String): String = when (key) {
    "basic" -> ArtLily
    "all" -> ArtMeadow
    "family" -> ArtMaryJesus
    "protection" -> ArtChurch
    "health" -> ArtChrist
    "forgiveness" -> ArtCarrying
    "cat_MORNING_NIGHT" -> ArtMorning
    "cat_MARIAN" -> ArtMary
    "cat_EUCHARISTIC" -> ArtMass
    "cat_HOLY_SPIRIT" -> ArtPentecost
    "cat_PROTECTION" -> ArtChurch
    "cat_INTERCESSION" -> ArtSaints
    "cat_ANGELUS" -> ArtAngelus
    else -> ArtLily
}

@Composable
private fun PrayerCategoryPage(
    key: String,
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onOpenPrayer: (VaticanPrayer) -> Unit,
    bottomPadding: Dp
) {
    val tile = prayerTiles(currentLanguage).firstOrNull { it.key == key }
    val devotion = PrayerCategory.entries.firstOrNull { "cat_${it.name}" == key }
    var intention by rememberSaveable(key) { mutableStateOf<String?>(null) }
    val title = tile?.label ?: devotion?.let { categoryShortTitle(it, currentLanguage) } ?: ""
    AppInlineTitle(title)

    val prayers = when {
        devotion != null -> repository.searchPrayers(query = "", category = devotion)
        tile != null && key == "all" -> repository.searchPrayers(query = "", tag = intention)
        tile != null -> repository.searchPrayers(query = "", category = tile.category, tag = tile.tag)
        else -> emptyList()
    }
    // "Intenciones" lists every prayer, grouped by its category.
    val groups: List<Pair<String?, List<VaticanPrayer>>> = if (key == "all") {
        PrayerCategory.entries.map { c -> categoryShortTitle(c, currentLanguage) to prayers.filter { it.category == c } }
            .filter { it.second.isNotEmpty() }
    } else listOf(null to prayers)

    LazyColumn(
        Modifier
            .fillMaxSize()
            .testTag("prayer_category_page_$key"),
        contentPadding = PaddingValues(bottom = bottomPadding)
    ) {
        item(key = "header") {
            AppDetailHeader(
                title = title,
                assetPath = rememberFirstAsset(categoryArt(key), ArtLily),
                meta = currentLanguage.pick("${prayers.size} oraciones", "${prayers.size} prayers", "${prayers.size} orações"),
                imageHeight = 230.dp
            )
        }
        if (key == "all") {
            item(key = "chips") {
                val options = listOf<String?>(null) + IntentionChipTags.filter { t ->
                    repository.vaticanPrayers.any { t in it.thematicTags }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = LocalAppDesign.current.spacing.gutter)
                        .testTag("prayer_intention_chips"),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    options.forEach { t ->
                        AppKitChip(
                            label = t?.let { intentionLabel(it, currentLanguage) }
                                ?: currentLanguage.pick("Todas", "All", "Todas"),
                            selected = intention == t,
                            onClick = { intention = t },
                            modifier = Modifier.testTag("prayer_intention_${t ?: "all"}")
                        )
                    }
                }
            }
        }
        groups.forEachIndexed { gi, (label, entries) ->
            item(key = "group_$gi") {
                if (label != null) PrayerSectionHeader(label) else Spacer(Modifier.height(8.dp))
            }
            itemsIndexed(entries, key = { _, p -> "g${gi}_${p.id}" }) { i, prayer ->
                AppGroupedRow(
                    title = prayer.title.get(currentLanguage),
                    position = prayerGroupPosition(i, entries.size),
                    onClick = { onOpenPrayer(prayer) },
                    tag = "prayer_row_${prayer.id}"
                )
            }
        }
    }
}

// ============================================================ prayer detail

/** Lumen art for a prayer, by stable id first, then by category. */
private fun prayerArt(prayer: VaticanPrayer): String = when {
    prayer.id == "advent_antiphons" -> ArtAnnunciation
    prayer.id == "christmas_prayer" -> ArtNativity
    prayer.id == "easter_prayer" || prayer.id == "regina_caeli" -> ArtResurrection
    prayer.id == "lenten_prayer" -> ArtCarrying
    prayer.id == "angelus" -> ArtAngelus
    prayer.id == "immaculate_conception" || prayer.id == "bendita_sea_tu_pureza" -> ArtMaryJesus
    prayer.id.startsWith("divine_mercy") || prayer.id == "eternal_father" || prayer.id == "holy_god" -> ArtDivineMercy
    prayer.category == PrayerCategory.HOLY_SPIRIT -> ArtPentecost
    prayer.category == PrayerCategory.MARIAN || prayer.id == "hail_mary" -> ArtMary
    prayer.category == PrayerCategory.EUCHARISTIC -> ArtMass
    prayer.id in setOf("prayer_before_crucifix", "jesus_prayer", "act_of_contrition", "confiteor") -> ArtChrist
    "morning" in prayer.thematicTags || prayer.id == "morning_offering" -> ArtMorning
    "evening" in prayer.thematicTags || prayer.id in setOf("evening_prayer", "stay_with_me_lord") -> ArtNight
    prayer.category == PrayerCategory.MORNING_NIGHT -> ArtMeadow
    prayer.category == PrayerCategory.PROTECTION -> ArtChurch
    prayer.category == PrayerCategory.INTERCESSION -> ArtSaints
    else -> ArtLily
}

/** A prayer as its own page: art, title, the text to be prayed, actions, notes. */
@Composable
private fun PrayerDetailView(
    prayer: VaticanPrayer,
    currentLanguage: AppLanguage,
    currentlySpeaking: String?,
    onPlayAudio: () -> Unit,
    onStopAudio: () -> Unit,
    bottomPadding: Dp
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val context = LocalContext.current
    var showReading by remember { mutableStateOf(false) }
    val title = prayer.title.get(currentLanguage)
    AppInlineTitle(title)
    val leader = prayer.leaderText.get(currentLanguage)
    val response = prayer.responseText?.get(currentLanguage)?.takeIf { it.isNotBlank() }
    val speaking = currentlySpeaking != null
    val meta = listOfNotNull(
        prayer.latinTitle?.takeIf { it.isNotBlank() && !it.equals(title, ignoreCase = true) },
        prayer.category.title.get(currentLanguage)
    ).joinToString(" · ")

    LazyColumn(
        Modifier
            .fillMaxSize()
            .testTag("prayer_detail_${prayer.id}"),
        contentPadding = PaddingValues(bottom = bottomPadding)
    ) {
        item(key = "header") {
            AppDetailHeader(
                title = title,
                assetPath = rememberFirstAsset(prayerArt(prayer), ArtLily),
                meta = meta,
                imageHeight = 300.dp,
                titleTag = "prayer_detail_title"
            )
        }
        item(key = "text") {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = 2.dp)
            ) {
                PrayerReadingText(leader, highlighted = currentlySpeaking == leader)
                if (response != null) {
                    Spacer(Modifier.height(16.dp))
                    PrayerReadingText(response, highlighted = currentlySpeaking == response)
                }
            }
        }
        item(key = "actions") {
            Box(Modifier.fillMaxWidth().padding(top = 32.dp), contentAlignment = Alignment.Center) {
                AppActionBar(Modifier.widthIn(min = 220.dp)) {
                    AppActionBarItem(
                        icon = Icons.Outlined.IosShare,
                        contentDescription = currentLanguage.pick("Compartir", "Share", "Compartilhar"),
                        onClick = {
                            sharePrayerText(context, title, listOfNotNull(leader, response).joinToString("\n\n"))
                        },
                        tag = "share_prayer_${prayer.id}"
                    )
                    AppActionBarItem(
                        icon = if (speaking) Icons.Filled.Stop else Icons.Outlined.Headphones,
                        contentDescription = if (speaking) currentLanguage.pick("Detener", "Stop", "Parar")
                        else currentLanguage.pick("Escuchar", "Listen", "Ouvir"),
                        onClick = if (speaking) onStopAudio else onPlayAudio,
                        tag = "play_prayer_audio_${prayer.id}",
                        active = speaking
                    )
                    AppActionBarItem(
                        icon = Icons.Outlined.TextFields,
                        contentDescription = currentLanguage.pick("Tamaño del texto", "Text size", "Tamanho do texto"),
                        onClick = { showReading = true },
                        tag = "prayer_text_settings"
                    )
                }
            }
        }
        item(key = "about") {
            PrayerSectionHeader(currentLanguage.pick("Acerca de esta oración", "About this prayer", "Sobre esta oração"))
            Column {
                val notes = listOfNotNull(
                    currentLanguage.pick("Cuándo rezarla", "When to pray it", "Quando rezá-la") to
                        prayer.whenToPray.get(currentLanguage),
                    prayer.indulgenceNote?.let {
                        currentLanguage.pick("Indulgencia", "Indulgence", "Indulgência") to it.get(currentLanguage)
                    }
                ).filter { it.second.isNotBlank() }
                notes.forEachIndexed { i, (label, text) ->
                    NoteRow(label, text, prayerGroupPosition(i, notes.size))
                }
            }
        }
    }

    if (showReading) PrayerReadingSheet(currentLanguage) { showReading = false }
}

/** A grouped note: footnote label over callout text. */
@Composable
private fun NoteRow(label: String, text: String, position: GroupPosition) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    Column(Modifier.groupedItem(position)) {
        Column(Modifier.padding(horizontal = 18.dp, vertical = 14.dp)) {
            Text(label, style = styles.footnote, color = p.textSecondary)
            Spacer(Modifier.height(4.dp))
            Text(text, style = styles.callout, color = p.textPrimary)
        }
        if (position == GroupPosition.First || position == GroupPosition.Middle) {
            Box(Modifier.fillMaxWidth().padding(start = 18.dp).height(0.5.dp).background(p.separator))
        }
    }
}

// ============================================================== how to pray

private data class HowToPrayStep(val title: String, val body: String)

private fun howToPraySteps(currentLanguage: AppLanguage) = listOf(
    HowToPrayStep(
        title = currentLanguage.pick("Qué hacer", "What to do", "O que fazer"),
        body = currentLanguage.pick(
            "Busca un lugar tranquilo, siéntate o arrodíllate con el cuerpo atento, y comienza con la señal de la cruz: en el nombre del Padre, y del Hijo, y del Espíritu Santo.",
            "Find a quiet place, sit or kneel with an attentive body, and begin with the sign of the cross: in the name of the Father, and of the Son, and of the Holy Spirit.",
            "Procura um lugar tranquilo, senta-te ou ajoelha-te com o corpo atento e começa com o sinal da cruz: em nome do Pai, e do Filho, e do Espírito Santo."
        )
    ),
    HowToPrayStep(
        title = currentLanguage.pick("Qué decir", "What to say", "O que dizer"),
        body = currentLanguage.pick(
            "Reza con el corazón: lee la Escritura en voz baja, usa las palabras de la Iglesia o habla con tus propias palabras, sin prisa.",
            "Pray from the heart: read Scripture quietly, use the words of the Church or speak in your own words, without haste.",
            "Reza com o coração: lê a Escritura em voz baixa, usa as palavras da Igreja ou fala com as tuas próprias palavras, sem pressa."
        )
    ),
    HowToPrayStep(
        title = currentLanguage.pick("Cómo pedir", "How to ask", "Como pedir"),
        body = currentLanguage.pick(
            "Pide con confianza y humildad: nombra tu intención, confía en la voluntad de Dios y pide también por los demás.",
            "Ask with trust and humility: name your intention, trust God's will, and pray for others too.",
            "Pede com confiança e humildade: nomeia a tua intenção, confia na vontade de Deus e pede também pelos outros."
        )
    ),
    HowToPrayStep(
        title = currentLanguage.pick("Dar gracias", "Give thanks", "Dar graças"),
        body = currentLanguage.pick(
            "Da gracias por lo recibido y por lo que aún esperas: nombra los dones concretos de hoy, aunque sean pequeños.",
            "Give thanks for what you have received and what you still await: name today's concrete gifts, however small.",
            "Dá graças pelo que recebeste e pelo que ainda esperas: nomeia os dons concretos de hoje, por menores que sejam."
        )
    ),
    HowToPrayStep(
        title = currentLanguage.pick("Silencio y escucha", "Silence and listening", "Silêncio e escuta"),
        body = currentLanguage.pick(
            "Termina quedándote en silencio: escucha sin exigir respuesta y deja que la Palabra resuene en tu corazón.",
            "End by remaining in silence: listen without demanding an answer, and let the Word echo in your heart.",
            "Termina ficando em silêncio: escuta sem exigir resposta e deixa que a Palavra ressoe no teu coração."
        )
    )
)

@Composable
private fun HowToPrayPage(currentLanguage: AppLanguage, bottomPadding: Dp) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val title = currentLanguage.pick("Cómo orar", "How to pray", "Como orar")
    AppInlineTitle(title)
    val steps = howToPraySteps(currentLanguage)
    LazyColumn(
        Modifier
            .fillMaxSize()
            .testTag("how_to_pray_page"),
        contentPadding = PaddingValues(bottom = bottomPadding)
    ) {
        item(key = "header") {
            AppDetailHeader(
                title = title,
                assetPath = rememberFirstAsset(ArtSunrise, "heroes/sunrise_mountains.jpg"),
                imageHeight = 300.dp
            )
            Text(
                currentLanguage.pick(
                    "Cinco pasos para una oración más profunda.",
                    "Five steps to a deeper prayer.",
                    "Cinco passos para uma oração mais profunda."
                ),
                style = styles.title3.copy(fontWeight = FontWeight.Normal),
                color = p.textPrimary,
                modifier = Modifier.padding(horizontal = design.spacing.gutter)
            )
            Spacer(Modifier.height(24.dp))
        }
        itemsIndexed(steps, key = { i, _ -> "step_$i" }) { index, step ->
            PrayerNumberedStep(
                number = index + 1,
                title = step.title,
                body = step.body,
                modifier = Modifier.testTag("how_to_pray_step_$index")
            )
        }
    }
}

/** A numbered glass circle beside a step title and its body. */
@Composable
internal fun PrayerNumberedStep(
    number: Int,
    title: String,
    body: String?,
    modifier: Modifier = Modifier,
    done: Boolean = false,
    current: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    Row(
        modifier
            .fillMaxWidth()
            .lumenPress(onClick, 0.99f)
            .padding(horizontal = design.spacing.gutter, vertical = 10.dp)
            .semantics(mergeDescendants = true) {},
        verticalAlignment = Alignment.Top
    ) {
        val circle = Modifier.size(36.dp)
        Box(
            when {
                done -> circle.background(p.success, CircleShape)
                current -> circle.background(p.action, CircleShape)
                else -> circle.lumenGlass(CircleShape, lifted = false)
            },
            contentAlignment = Alignment.Center
        ) {
            if (done) Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(18.dp))
            else Text("$number", style = styles.subhead, color = if (current) p.onAction else p.textPrimary)
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f).padding(top = 6.dp)) {
            Text(title, style = styles.headline, color = p.textPrimary)
            if (!body.isNullOrBlank()) {
                Spacer(Modifier.height(4.dp))
                Text(body, style = styles.callout, color = p.textSecondary)
            }
        }
    }
}

// ================================================================ the chaplet

@Composable
private fun DivineMercyChapletView(
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    novenaArtUrl: String?,
    onOpenNovena: () -> Unit,
    bottomPadding: Dp
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val currentlySpeaking by ttsManager.currentlySpeaking.collectAsState()
    val speaking = currentlySpeaking != null

    // 0 = opening prayers, 1 = the five decades, 2 = conclusion, 3 = 3:00 PM
    var chapletStep by rememberSaveable { mutableIntStateOf(1) }
    var currentDecade by rememberSaveable { mutableIntStateOf(1) }
    // 0 = the lead bead (Eternal Father), 1..10 = the small ones (His Passion)
    var currentBead by rememberSaveable { mutableIntStateOf(0) }

    val title = currentLanguage.pick(
        "Coronilla de la Divina Misericordia",
        "Chaplet of Divine Mercy",
        "Terço da Divina Misericórdia"
    )
    AppInlineTitle(title)
    AppHideTabBar()

    val openingPrayer = when (currentLanguage) {
        AppLanguage.ES -> "En el nombre del Padre, y del Hijo, y del Espíritu Santo. Amén.\n\n«Expiraste, Jesús, pero la fuente de vida brotó para las almas y el mar de misericordia se abrió para el mundo entero. ¡Oh fuente de vida, insondable Misericordia Divina!, abarca al mundo entero y derrámate sobre nosotros.\n\n¡Oh Sangre y Agua que brotaste del Corazón de Jesús como una fuente de misericordia para nosotros, en Ti confío! (repetir 3 veces)»\n\n[Padre Nuestro, Ave María, Credo de los Apóstoles]"
        AppLanguage.EN -> "In the name of the Father, and of the Son, and of the Holy Spirit. Amen.\n\n«You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.\n\nO Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You! (repeat 3 times)»\n\n[Our Father, Hail Mary, Apostles' Creed]"
        AppLanguage.PT -> "Em nome do Pai, e do Filho, e do Espírito Santo. Amém.\n\n«Expirastes, Jesus, mas a fonte da vida brotou para as almas e o mar de misericórdia abriu-se para o mundo inteiro. Ó fonte de vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-vos sobre nós.\n\nÓ Sangue e Água que brotastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós! (repetir 3 vezes)»\n\n[Pai Nosso, Ave Maria, Credo dos Apóstolos]"
    }
    val largeBeadPrayer = when (currentLanguage) {
        AppLanguage.ES -> "Padre Eterno, te ofrezco el Cuerpo y la Sangre, el Alma y la Divinidad de tu amadísimo Hijo, nuestro Señor Jesucristo, como propiciación de nuestros pecados y los del mundo entero."
        AppLanguage.EN -> "Eternal Father, I offer you the Body and Blood, Soul and Divinity of Your Dearly Beloved Son, Our Lord, Jesus Christ, in atonement for our sins and those of the whole world."
        AppLanguage.PT -> "Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade de Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro."
    }
    val smallBeadPrayer = when (currentLanguage) {
        AppLanguage.ES -> "Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero."
        AppLanguage.EN -> "For the sake of His sorrowful Passion, have mercy on us and on the whole world."
        AppLanguage.PT -> "Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro."
    }
    val closingPrayer = when (currentLanguage) {
        AppLanguage.ES -> "«Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.» (Repetir 3 veces)\n\nOh Dios Eterno, en quien la misericordia es infinita y el tesoro de compasión inagotable, vuelve a nosotros Tu mirada bondadosa y aumenta Tu misericordia en nosotros, para que en los momentos difíciles no desesperemos ni nos desalentemos, sino que con gran confianza nos sometamos a Tu santa voluntad, que es el Amor y la Misericordia mismos. Amén.\n\n¡Jesús, en Ti confío! (x3)"
        AppLanguage.EN -> "«Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.» (Repeat 3 times)\n\nEternal God, in whom mercy is endless and the treasury of compassion inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself. Amen.\n\nJesus, I trust in You! (x3)"
        AppLanguage.PT -> "«Santo Deus, Santo Forte, Santo Imortal, tende piedade de nós e do mundo inteiro.» (Repetir 3 vezes)\n\nÓ Deus Eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai-nos com bondade e aumentai a Vossa misericórdia em nós, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a Misericórdia. Amém.\n\nJesus, eu confio em Vós! (x3)"
    }
    val hourOfMercyText = when (currentLanguage) {
        AppLanguage.ES -> "«A las tres de la tarde implora Mi misericordia, especialmente para los pecadores; y aunque sea por un brevísimo momento, sumérgete en Mi Pasión, particularmente en Mi abandono en el momento de la agonía. Esta es la hora de la gran misericordia para todo el mundo. En esta hora no negaré nada al alma que Me pida por los méritos de Mi Pasión» (Diario de Santa Faustina Kowalska, 1320)."
        AppLanguage.EN -> "«At three o'clock, implore My mercy, especially for sinners; and, if only for a brief moment, immerse yourself in My Passion, particularly in My abandonment at the moment of agony. This is the hour of great mercy for the whole world. In this hour I will refuse nothing to the soul that makes a request of Me in virtue of My Passion» (Diary, 1320)."
        AppLanguage.PT -> "«Às três horas da tarde implora a Minha misericórdia, especialmente para os pecadores e, ao menos por um breve momento, aprofunda-te na Minha Paixão, particularmente no Meu desamparo no momento da agonia. Esta é a hora da grande misericórdia para o mundo inteiro. Nesta hora nada negarei à alma que Me pedir pelos méritos da Minha Paixão» (Diário, 1320)."
    }
    val promise = currentLanguage.pick(
        "Promesa de Nuestro Señor: «Quienquiera que la rece recibirá gran misericordia a la hora de la muerte... Defenderé como Mi propia gloria a cada alma que rece esta coronilla» (Diario, 687).",
        "Our Lord's Promise: «Whoever will recite it will receive great mercy at the hour of death... I will defend as My own glory every soul that will say this chaplet» (Diary, 687).",
        "Promessa de Nosso Senhor: «Quem quer que a recite receberá grande misericórdia na hora da morte... Defenderei como Minha própria glória cada alma que recitar este terço» (Diário, 687)."
    )
    val stepNames = when (currentLanguage) {
        AppLanguage.ES -> listOf("Inicio", "Decenas", "Cierre", "15:00")
        AppLanguage.EN -> listOf("Opening", "Decades", "Closing", "3:00 PM")
        AppLanguage.PT -> listOf("Início", "Dezenas", "Final", "15:00")
    }
    val beadPrayer = if (currentBead == 0) largeBeadPrayer else smallBeadPrayer
    val listenLabel = currentLanguage.pick("Escuchar", "Listen", "Ouvir")
    val stopLabel = currentLanguage.pick("Detener", "Stop", "Parar")

    @Composable
    fun ListenCapsule(text: String) {
        AppSecondaryCapsule(
            label = if (speaking) stopLabel else listenLabel,
            onClick = { if (speaking) ttsManager.stop() else ttsManager.speakSingle(text) },
            icon = if (speaking) Icons.Filled.Stop else Icons.Outlined.Headphones,
            tag = "chaplet_tts_button",
            modifier = Modifier.semantics {
                stateDescription = if (speaking) currentLanguage.pick("Narrando", "Narrating", "Narrando")
                else currentLanguage.pick("Detenido", "Stopped", "Parado")
            }
        )
    }

    LazyColumn(Modifier.fillMaxSize().testTag("chaplet_screen"), contentPadding = PaddingValues(bottom = bottomPadding)) {
        item(key = "hero") {
            AppDetailHeader(
                title = title,
                assetPath = rememberFirstAsset(ArtDivineMercy, "heroes/divine_mercy.jpg"),
                meta = currentLanguage.pick(
                    "Revelada a santa Faustina en Vilna, 1935",
                    "Revealed to St. Faustina in Vilnius, 1935",
                    "Revelado a santa Faustina em Vilna, 1935"
                ),
                imageHeight = 340.dp,
                modifier = Modifier
                    .testTag("chaplet_hero")
                    .semantics { contentDescription = title }
            )
        }
        item(key = "promise") {
            Text(
                promise,
                style = styles.reading.copy(fontStyle = FontStyle.Italic),
                color = p.textSecondary,
                modifier = Modifier.padding(horizontal = design.spacing.gutter)
            )
            Spacer(Modifier.height(24.dp))
            AppSegmentedTabs(
                options = stepNames,
                selectedIndex = chapletStep,
                onSelect = { chapletStep = it },
                modifier = Modifier.testTag("chaplet_parts")
            )
            Spacer(Modifier.height(28.dp))
        }

        when (chapletStep) {
            0 -> item(key = "opening") {
                Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        currentLanguage.pick("Oraciones iniciales", "Opening prayers", "Orações iniciais"),
                        style = styles.title2, color = p.textPrimary
                    )
                    Spacer(Modifier.height(14.dp))
                    PrayerReadingParagraphs(openingPrayer)
                    Spacer(Modifier.height(24.dp))
                    ListenCapsule(openingPrayer)
                    Spacer(Modifier.height(14.dp))
                    AppPrimaryCapsule(
                        label = currentLanguage.pick("Comenzar la 1ª decena", "Begin the 1st decade", "Começar a 1ª dezena"),
                        onClick = {
                            chapletStep = 1
                            currentDecade = 1
                            currentBead = 0
                        },
                        icon = Icons.Filled.PlayArrow,
                        tag = "chaplet_begin_decades"
                    )
                }
            }

            1 -> item(key = "decades") {
                val position = (currentDecade - 1) * (ChapletBeadsPerDecade + 1) + currentBead + 1
                val total = ChapletDecades * (ChapletBeadsPerDecade + 1)
                val atLastBead = currentBead == ChapletBeadsPerDecade && currentDecade == ChapletDecades
                val canGoBack = currentBead > 0 || currentDecade > 1
                Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        if (currentBead == 0) currentLanguage.pick(
                            "Decena $currentDecade de $ChapletDecades",
                            "Decade $currentDecade of $ChapletDecades",
                            "Dezena $currentDecade de $ChapletDecades"
                        ) else currentLanguage.pick(
                            "Decena $currentDecade de $ChapletDecades · cuenta $currentBead de $ChapletBeadsPerDecade",
                            "Decade $currentDecade of $ChapletDecades · bead $currentBead of $ChapletBeadsPerDecade",
                            "Dezena $currentDecade de $ChapletDecades · conta $currentBead de $ChapletBeadsPerDecade"
                        ),
                        style = styles.footnote,
                        color = p.textSecondary
                    )
                    Spacer(Modifier.height(8.dp))
                    AppProgressBar(
                        position.toFloat() / total,
                        modifier = Modifier.semantics {
                            stateDescription = currentLanguage.pick("Paso $position de $total", "Step $position of $total", "Passo $position de $total")
                        }
                    )
                    Spacer(Modifier.height(22.dp))
                    Text(
                        if (currentBead == 0) currentLanguage.pick("Cuenta grande · Padre Eterno", "Lead bead · Eternal Father", "Conta grande · Eterno Pai")
                        else currentLanguage.pick("Cuenta pequeña · Dolorosa Pasión", "Small bead · His sorrowful Passion", "Conta pequena · Dolorosa Paixão"),
                        style = styles.title2,
                        color = p.textPrimary
                    )
                    Spacer(Modifier.height(18.dp))
                    BeadRail(
                        activeIndex = currentBead,
                        onSelect = { currentBead = it },
                        currentLanguage = currentLanguage
                    )
                    Spacer(Modifier.height(20.dp))
                    PrayerReadingText(beadPrayer, highlighted = currentlySpeaking == beadPrayer)
                    Spacer(Modifier.height(28.dp))
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        AppTransportBar(
                            isPlaying = speaking,
                            onPlayPause = { if (speaking) ttsManager.stop() else ttsManager.speakSingle(beadPrayer) },
                            onPrevious = if (canGoBack) ({
                                if (currentBead > 0) currentBead-- else {
                                    currentDecade--
                                    currentBead = ChapletBeadsPerDecade
                                }
                            }) else null,
                            onNext = if (!atLastBead) ({
                                if (currentBead < ChapletBeadsPerDecade) currentBead++ else {
                                    currentDecade++
                                    currentBead = 0
                                }
                            }) else null,
                            previousLabel = currentLanguage.pick("Atrás", "Back", "Voltar"),
                            nextLabel = currentLanguage.pick("Avanzar", "Next", "Avançar"),
                            playLabel = listenLabel,
                            pauseLabel = stopLabel,
                            tagPrefix = "chaplet_transport",
                            modifier = Modifier.testTag("chaplet_tts_button")
                        )
                    }
                    if (atLastBead) {
                        Spacer(Modifier.height(18.dp))
                        AppPrimaryCapsule(
                            label = currentLanguage.pick("Ir a la conclusión", "Go to the conclusion", "Ir à conclusão"),
                            onClick = { chapletStep = 2 },
                            tag = "chaplet_to_conclusion"
                        )
                    }
                }
            }

            2 -> item(key = "closing") {
                Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        currentLanguage.pick(
                            "Doxología trinitaria y conclusión",
                            "Trinitarian doxology and conclusion",
                            "Doxologia trinitária e conclusão"
                        ),
                        style = styles.title2, color = p.textPrimary
                    )
                    Spacer(Modifier.height(14.dp))
                    PrayerReadingParagraphs(closingPrayer)
                    Spacer(Modifier.height(24.dp))
                    ListenCapsule(closingPrayer)
                }
                PrayerSectionHeader(currentLanguage.pick("Rezar la novena de 9 días", "Pray the 9-day novena", "Rezar a novena de 9 dias"))
                Box(Modifier.padding(horizontal = design.spacing.gutter)) {
                    RemoteArtCard(
                        title = currentLanguage.pick("Divina Misericordia", "Divine Mercy", "Divina Misericórdia"),
                        eyebrow = currentLanguage.pick("Novena a la", "Novena to the", "Novena à"),
                        meta = currentLanguage.pick("9 días", "9 days", "9 dias"),
                        imageUrl = novenaArtUrl,
                        fallbackAsset = rememberFirstAsset(ArtDivineMercy, ArtChrist),
                        aspectRatio = 2.1f,
                        onClick = onOpenNovena,
                        tag = "chaplet_open_novena"
                    )
                }
            }

            else -> item(key = "hour") {
                Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(currentLanguage.pick("15:00", "3:00 PM", "15:00"), style = styles.footnote, color = p.textSecondary)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        currentLanguage.pick("La Hora de la Misericordia", "The Hour of Mercy", "A Hora da Misericórdia"),
                        style = styles.title2, color = p.textPrimary
                    )
                    Spacer(Modifier.height(14.dp))
                    PrayerReadingParagraphs(hourOfMercyText)
                    Spacer(Modifier.height(24.dp))
                    ListenCapsule(hourOfMercyText)
                }
            }
        }
    }
}

/** The decade: a lead bead and ten small ones; told = filled, current = action. */
@Composable
private fun BeadRail(activeIndex: Int, onSelect: (Int) -> Unit, currentLanguage: AppLanguage) {
    val p = LocalAppDesign.current.palette
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 44.dp)
            .testTag("chaplet_bead_rail"),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(ChapletBeadsPerDecade + 1) { index ->
            val active = index == activeIndex
            val told = index < activeIndex
            val fill by animateColorAsState(
                when {
                    active -> p.action
                    told -> p.textSecondary.copy(alpha = 0.55f)
                    else -> Color.Transparent
                },
                tween(200),
                label = "bead"
            )
            val size = if (index == 0) 18.dp * ChapletLeadBeadScale else 18.dp
            Box(
                Modifier
                    .size(30.dp, 44.dp)
                    .lumenPress({ onSelect(index) }, 0.85f)
                    .semantics {
                        contentDescription = if (index == 0) currentLanguage.pick("Cuenta grande", "Lead bead", "Conta grande")
                        else currentLanguage.pick("Cuenta $index", "Bead $index", "Conta $index")
                        this.selected = active
                    },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .size(size)
                        .clip(CircleShape)
                        .then(if (fill == Color.Transparent) Modifier.lumenGlass(CircleShape, lifted = false) else Modifier)
                        .background(fill)
                        .border(1.dp, if (active) Color.Transparent else p.separator, CircleShape)
                )
            }
        }
    }
}

// ============================================================ novena index

/** Kinds the index filters by, derived from the stable id slug. */
private enum class NovenaKind { ALL, POPULAR, MARY, JESUS, SAINTS }

/** The most-prayed novenas, in the order they lead the "Populares" filter. */
private val PopularNovenaIds = listOf(
    "novena-a-la-divina-misericordia",
    "novena-a-san-judas-tadeo",
    "novena-a-san-jose",
    "novena-al-corazon-sagrado-de-jesus",
    "novena-al-espiritu-santo-pentecostes",
    "novena-a-la-virgen-de-guadalupe",
    "novena-a-la-inmaculada-concepcion",
    "novena-a-la-medalla-milagrosa",
    "novena-a-maria-desatanudos",
    "novena-a-san-miguel-arcangel",
    "novena-a-san-antonio-de-padua",
    "novena-a-santa-teresita-del-nino-jesus",
    "novena-a-santa-rita",
    "novena-a-padre-pio",
    "novena-a-cristo-rey",
    "novena-a-nuestra-senora-del-perpetuo-socorro",
    "novena-a-la-virgen-del-carmen",
    "novena-a-nuestra-senora-de-lourdes",
    "novena-a-la-virgen-de-fatima",
    "novena-a-san-charbel",
    "novena-del-acordaos",
    "novena-al-nino-jesus-de-praga",
    "novena-por-las-almas-del-purgatorio"
)

private val JesusSpecific = Regex("misericordia|santo-nombre|santo-rostro|sagrado|corazon|espiritu-santo|cristo|divino|nino-jesus-de-praga|preciosa-sangre")
private val SaintPattern = Regex("-san-|-santa-|-santo-|-santos-|-santas-|beato|beata|arcangel|padre-pio|-papa-|martires|apostol")
private val MaryPattern = Regex("maria|virgen|senora|inmaculada|guadalupe|fatima|lourdes|medalla|rosario|dolores|madre|asuncion|anunciacion|acordaos|desatanudos|carmen|socorro")
private val JesusGeneric = Regex("jesus|cruz|pasion|eucar|trinidad|navidad|adviento|nino")

private fun novenaKindOf(novena: Novena): NovenaKind {
    val id = novena.id.lowercase()
    return when {
        JesusSpecific.containsMatchIn(id) -> NovenaKind.JESUS
        SaintPattern.containsMatchIn(id) -> NovenaKind.SAINTS
        MaryPattern.containsMatchIn(id) -> NovenaKind.MARY
        JesusGeneric.containsMatchIn(id) -> NovenaKind.JESUS
        else -> NovenaKind.ALL
    }
}

/** Bundled art behind a novena's own image (offline, loading, renders). */
private fun novenaFallbackArt(novena: Novena): String = when {
    novena.id.contains("misericordia") -> ArtDivineMercy
    novena.id.contains("espiritu-santo") -> ArtPentecost
    novena.id.contains("navidad") || novena.id.contains("aguinaldo") || novena.id.contains("nino") -> ArtNativity
    else -> when (novenaKindOf(novena)) {
        NovenaKind.MARY -> ArtMary
        NovenaKind.JESUS -> ArtChrist
        NovenaKind.SAINTS -> ArtSaints
        else -> ArtLily
    }
}

private fun intentionIcon(tag: String?): ImageVector = when (tag) {
    "impossible" -> Icons.Outlined.Shield
    "blessings" -> Icons.Outlined.AutoAwesome
    "love" -> Icons.Outlined.FavoriteBorder
    "health" -> Icons.Outlined.Spa
    "family" -> Icons.Outlined.People
    "peace" -> Icons.Outlined.WbTwilight
    "comfort" -> Icons.Outlined.VolunteerActivism
    else -> Icons.Outlined.LocalFlorist
}

@Composable
private fun NovenaIndexView(
    header: LazyListScope.() -> Unit,
    novenas: List<Novena>,
    currentLanguage: AppLanguage,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    kind: NovenaKind,
    onKind: (NovenaKind) -> Unit,
    selectedTag: String?,
    onSelectTag: (String?) -> Unit,
    daysPrayed: Map<String, Set<Int>>,
    startedOn: Map<String, Long>,
    onOpenNovena: (Novena) -> Unit,
    bottomPadding: Dp
) {
    val design = LocalAppDesign.current

    // Drawn from the whole catalogue: what someone is praying never hides
    // behind a search that is still typed in.
    val begun = novenas.filter { daysPrayed[it.id].orEmpty().isNotEmpty() }
    val inProgress = begun.filterNot { isNovenaFinished(it, daysPrayed[it.id].orEmpty()) }
        .sortedByDescending { startedOn[it.id] ?: 0L }
    val finished = begun.filter { isNovenaFinished(it, daysPrayed[it.id].orEmpty()) }
        .sortedByDescending { startedOn[it.id] ?: 0L }
    var showAllFinished by rememberSaveable { mutableStateOf(false) }

    val tagLabels = mapOf(
        "impossible" to currentLanguage.pick("Casos imposibles/difíciles", "Impossible/difficult cases", "Casos impossíveis/difíceis"),
        "blessings" to currentLanguage.pick("Bendiciones", "Blessings", "Bênçãos"),
        "love" to currentLanguage.pick("Amor", "Love", "Amor"),
        "money" to currentLanguage.pick("Dinero/Trabajo", "Money/Work", "Dinheiro/Trabalho"),
        "comfort" to currentLanguage.pick("Consuelo", "Comfort", "Consolo"),
        "health" to currentLanguage.pick("Salud/Cura", "Health/Healing", "Saúde/Cura"),
        "family" to currentLanguage.pick("Familia", "Family", "Família"),
        "peace" to currentLanguage.pick("Paz", "Peace", "Paz")
    )
    val availableTags = remember(novenas.size) {
        listOf("impossible", "blessings", "love", "money", "comfort", "health", "family", "peace")
            .filter { tag -> novenas.any { it.tags.contains(tag) } }
    }
    val kinds = remember(novenas.size) { novenas.associate { it.id to novenaKindOf(it) } }

    val filtered = remember(novenas.size, searchQuery, selectedTag, kind, currentLanguage) {
        val base = novenas.filter { novena ->
            val matchesSearch = searchQuery.isBlank() || listOf(
                novena.title.get(currentLanguage),
                novena.patron.get(currentLanguage),
                novena.description.get(currentLanguage)
            ).any { it.contains(searchQuery, ignoreCase = true) } ||
                novena.days.any {
                    it.prayer.get(currentLanguage).contains(searchQuery, ignoreCase = true) ||
                        it.meditation.get(currentLanguage).contains(searchQuery, ignoreCase = true)
                }
            val matchesTag = selectedTag == null || novena.tags.contains(selectedTag)
            val matchesKind = when (kind) {
                NovenaKind.ALL -> true
                NovenaKind.POPULAR -> novena.id in PopularNovenaIds
                else -> kinds[novena.id] == kind
            }
            matchesSearch && matchesTag && matchesKind
        }
        if (kind == NovenaKind.POPULAR) base.sortedBy { PopularNovenaIds.indexOf(it.id) } else base
    }
    val filtering = searchQuery.isNotBlank() || selectedTag != null || kind != NovenaKind.ALL

    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = bottomPadding)) {
        header()

        item(key = "novena_filters") {
            Spacer(Modifier.height(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = design.spacing.gutter, vertical = 4.dp)
                    .testTag("novena_filter_chips"),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val kindLabels = listOf(
                    NovenaKind.ALL to currentLanguage.pick("Todas", "All", "Todas"),
                    NovenaKind.POPULAR to currentLanguage.pick("Populares", "Popular", "Populares"),
                    NovenaKind.MARY to currentLanguage.pick("María", "Mary", "Maria"),
                    NovenaKind.JESUS to currentLanguage.pick("Jesús", "Jesus", "Jesus"),
                    NovenaKind.SAINTS to currentLanguage.pick("Santos", "Saints", "Santos")
                )
                kindLabels.forEach { (k, label) ->
                    AppKitChip(
                        label = label,
                        selected = kind == k,
                        onClick = { onKind(k) },
                        modifier = Modifier.testTag("novena_filter_${k.name.lowercase()}")
                    )
                }
                AppMenuChip(
                    label = selectedTag?.let { tagLabels[it] } ?: currentLanguage.pick("Intención", "Intention", "Intenção"),
                    icon = if (selectedTag != null) intentionIcon(selectedTag) else null,
                    tag = "novena_intention_picker",
                    items = listOf(
                        AppMenuItem(
                            label = currentLanguage.pick("Todas las intenciones", "All intentions", "Todas as intenções"),
                            selected = selectedTag == null,
                            icon = if (selectedTag == null) Icons.Filled.Check else null,
                            tag = "novena_intention_all"
                        ) { onSelectTag(null) }
                    ) + availableTags.map { t ->
                        AppMenuItem(
                            label = tagLabels[t] ?: t,
                            icon = if (selectedTag == t) Icons.Filled.Check else intentionIcon(t),
                            selected = selectedTag == t,
                            tag = "novena_intention_$t"
                        ) { onSelectTag(t) }
                    }
                )
            }
        }

        if (inProgress.isNotEmpty()) {
            item(key = "in_progress_header") {
                PrayerSectionHeader(currentLanguage.pick("En curso", "In progress", "Em andamento"), "${inProgress.size}")
            }
            item(key = "in_progress") {
                NovenaGrid(
                    novenas = inProgress,
                    currentLanguage = currentLanguage,
                    daysPrayed = daysPrayed,
                    onOpen = onOpenNovena,
                    modifier = Modifier.testTag("novenas_in_progress")
                )
            }
        }

        if (finished.isNotEmpty()) {
            item(key = "finished_header") {
                PrayerSectionHeader(currentLanguage.pick("Completadas", "Completed", "Concluídas"), "${finished.size}")
            }
            item(key = "finished") {
                Column(Modifier.testTag("novenas_completed")) {
                    NovenaGrid(
                        novenas = if (showAllFinished) finished else finished.take(4),
                        currentLanguage = currentLanguage,
                        daysPrayed = daysPrayed,
                        onOpen = onOpenNovena
                    )
                    if (finished.size > 4) {
                        Spacer(Modifier.height(12.dp))
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            AppSecondaryCapsule(
                                label = if (showAllFinished) currentLanguage.pick("Ver menos", "Show less", "Ver menos")
                                else currentLanguage.pick("Ver todas", "Show all", "Ver todas"),
                                onClick = { showAllFinished = !showAllFinished }
                            )
                        }
                    }
                }
            }
        }

        item(key = "all_header") {
            PrayerSectionHeader(
                currentLanguage.pick("Todas las novenas", "All novenas", "Todas as novenas"),
                trailing = if (filtering) currentLanguage.pick(
                    "${filtered.size} de ${novenas.size}",
                    "${filtered.size} of ${novenas.size}",
                    "${filtered.size} de ${novenas.size}"
                ) else "${novenas.size}"
            )
        }

        if (filtered.isEmpty()) {
            item(key = "novena_empty") {
                EmptyCard(
                    title = currentLanguage.pick("Ninguna novena coincide", "No matching novenas", "Nenhuma novena corresponde"),
                    body = currentLanguage.pick(
                        "Prueba otra palabra o elige otra intención.",
                        "Try another word or choose another intention.",
                        "Tente outra palavra ou escolha outra intenção."
                    ),
                    actionLabel = currentLanguage.pick("Ver todas", "Show all novenas", "Ver todas"),
                    actionTag = "novena_clear_filters",
                    onAction = {
                        onSearchQueryChange("")
                        onSelectTag(null)
                        onKind(NovenaKind.ALL)
                    }
                )
            }
        } else {
            // Equal-size cards, two per row; each row is one lazy item.
            items(
                items = filtered.chunked(2),
                key = { pair -> "row_" + pair.first().id + "_" + pair.last().id }
            ) { pair ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    pair.forEach { novena ->
                        NovenaCard(
                            novena = novena,
                            currentLanguage = currentLanguage,
                            prayed = daysPrayed[novena.id].orEmpty(),
                            onOpen = { onOpenNovena(novena) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (pair.size == 1) Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun NovenaGrid(
    novenas: List<Novena>,
    currentLanguage: AppLanguage,
    daysPrayed: Map<String, Set<Int>>,
    onOpen: (Novena) -> Unit,
    modifier: Modifier = Modifier
) {
    AppGrid(items = novenas, columns = 2, modifier = modifier) { novena, m ->
        NovenaCard(
            novena = novena,
            currentLanguage = currentLanguage,
            prayed = daysPrayed[novena.id].orEmpty(),
            onOpen = { onOpen(novena) },
            modifier = m
        )
    }
}

/** "Novena a la" over "Divina Misericordia": the patron leads, the form whispers. */
private data class SplitTitle(val eyebrow: String?, val subject: String)

private val EsPtPrefix = Regex(
    "^(Novena (?:a la|a los|a las|a|al|de la|de los|de las|del|de|por la|por los|por las|por el|por|para|à|ao|aos|às|da|do|dos|das|pela|pelo|pelas|pelos|em honra de|em honra da|em honra do))\\s+(.+)$",
    RegexOption.IGNORE_CASE
)
private val EnPrefix = Regex("^(Novena (?:to the|to|for the|for|of the|of|in honor of))\\s+(.+)$", RegexOption.IGNORE_CASE)

private fun splitNovenaTitle(title: String, language: AppLanguage): SplitTitle {
    val t = title.trim()
    val m = (if (language == AppLanguage.EN) EnPrefix else EsPtPrefix).find(t)
    if (m != null) {
        return SplitTitle(m.groupValues[1], m.groupValues[2].replaceFirstChar { it.uppercaseChar() })
    }
    if (language == AppLanguage.EN) {
        val i = t.indexOf(" Novena")
        if (i > 0) {
            val subject = (t.substring(0, i) + t.substring(i + " Novena".length)).trim()
            if (subject.isNotBlank()) return SplitTitle("Novena", subject)
        }
    }
    return SplitTitle(null, t)
}

private fun novenaProgressLabel(novena: Novena, prayed: Set<Int>, language: AppLanguage): String {
    val total = novena.days.size
    return when {
        prayed.isEmpty() -> language.pick("$total días", "$total days", "$total dias")
        isNovenaFinished(novena, prayed) -> language.pick("Completada", "Completed", "Concluída")
        else -> novenaDayOf(language, nextNovenaDay(novena, prayed), total)
    }
}

/** One novena in the grid: its own art, full-bleed, with the title over a scrim. */
@Composable
private fun NovenaCard(
    novena: Novena,
    currentLanguage: AppLanguage,
    prayed: Set<Int>,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val split = splitNovenaTitle(novena.title.get(currentLanguage), currentLanguage)
    val finished = isNovenaFinished(novena, prayed)
    val label = novenaProgressLabel(novena, prayed, currentLanguage)
    RemoteArtCard(
        title = split.subject,
        eyebrow = split.eyebrow,
        meta = label,
        imageUrl = novena.imageUrl,
        fallbackAsset = rememberFirstAsset(novenaFallbackArt(novena), ArtLily),
        progress = if (prayed.isNotEmpty() && !finished) prayed.size.toFloat() / novena.days.size.coerceAtLeast(1) else null,
        done = finished,
        aspectRatio = 0.8f,
        onClick = onOpen,
        tag = "novena_card_${novena.id}",
        description = "${novena.title.get(currentLanguage)}, $label",
        modifier = modifier
    )
}

/**
 * Art-led card for remote art with a bundled fallback (the kit's
 * [AppImageCard] has no fallback under a remote image).
 */
@Composable
private fun RemoteArtCard(
    title: String,
    eyebrow: String?,
    meta: String?,
    imageUrl: String?,
    fallbackAsset: String?,
    aspectRatio: Float,
    onClick: () -> Unit,
    tag: String,
    modifier: Modifier = Modifier,
    progress: Float? = null,
    done: Boolean = false,
    description: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    Box(
        modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .lumenPress(onClick, 0.97f)
            .clip(ImageCardShape)
            .background(p.raised)
            .testTag(tag)
            .semantics(mergeDescendants = true) { if (description != null) contentDescription = description }
    ) {
        AppRemoteImage(
            imageUrl = imageUrl,
            fallbackAssetPath = fallbackAsset,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopCenter
        )
        // Mist: the art belongs to the canvas, then a scrim for white type.
        Box(Modifier.fillMaxSize().background(if (design.isDark) Color.Black.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.06f)))
        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    0f to Color.Transparent,
                    0.38f to Color.Transparent,
                    0.7f to Color.Black.copy(alpha = 0.38f),
                    1f to Color.Black.copy(alpha = 0.72f)
                )
            )
        )
        Box(Modifier.fillMaxSize().border(1.dp, Color.White.copy(alpha = if (design.isDark) 0.08f else 0.3f), ImageCardShape))
        Column(Modifier.align(Alignment.BottomStart).padding(14.dp)) {
            if (eyebrow != null) {
                Text(eyebrow, style = styles.caption, color = Color.White.copy(alpha = 0.86f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(1.dp))
            }
            Text(
                title,
                style = styles.headline.copy(fontWeight = FontWeight.Medium),
                color = Color.White,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            if (meta != null) {
                Spacer(Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (done) {
                        Box(
                            Modifier.size(15.dp).background(p.success, CircleShape),
                            contentAlignment = Alignment.Center
                        ) { Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(11.dp)) }
                        Spacer(Modifier.width(6.dp))
                    }
                    Text(meta, style = styles.caption, color = Color.White.copy(alpha = 0.9f), maxLines = 1)
                }
            }
            if (progress != null) {
                Spacer(Modifier.height(8.dp))
                AppProgressBar(progress, color = Color.White, track = Color.White.copy(alpha = 0.3f), height = 3.dp)
            }
        }
    }
}

// ========================================================== novena overview

/** Header for remote art (with bundled fallback): art, title1, meta row. */
@Composable
private fun RemoteArtHeader(
    title: String,
    imageUrl: String?,
    fallbackAsset: String?,
    imageHeight: Dp,
    eyebrow: String? = null,
    meta: String? = null,
    metaIcon: ImageVector? = null,
    titleTag: String? = null,
    titleStyleSmall: Boolean = false,
    actions: @Composable () -> Unit = {}
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val top = statusBarHeight()
    Column(Modifier.fillMaxWidth()) {
        Box(Modifier.fillMaxWidth().height(imageHeight + top)) {
            AppRemoteImage(
                imageUrl = imageUrl,
                fallbackAssetPath = fallbackAsset,
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.TopCenter
            )
            Box(Modifier.fillMaxSize().background(if (design.isDark) Color.Black.copy(alpha = 0.2f) else p.canvasTop.copy(alpha = 0.12f)))
            Box(
                Modifier.fillMaxSize().background(
                    Brush.verticalGradient(
                        0f to (if (design.isDark) Color.Black.copy(alpha = 0.45f) else p.canvasTop.copy(alpha = 0.6f)),
                        0.22f to Color.Transparent,
                        0.5f to Color.Transparent,
                        0.82f to p.canvasTop.copy(alpha = 0.82f),
                        1f to p.canvasTop
                    )
                )
            )
            Row(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = top + 4.dp, end = design.spacing.gutter - 2.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) { actions() }
        }
        Column(
            Modifier
                .padding(horizontal = design.spacing.gutter)
                .offset(y = (-28).dp)
        ) {
            if (eyebrow != null) {
                Text(eyebrow, style = styles.eyebrow, color = p.textSecondary, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(6.dp))
            }
            Text(
                title,
                style = if (titleStyleSmall) styles.title1 else styles.title1,
                color = p.textPrimary,
                modifier = Modifier
                    .semantics { heading() }
                    .then(if (titleTag != null) Modifier.testTag(titleTag) else Modifier)
            )
            if (meta != null) {
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (metaIcon != null) {
                        Icon(metaIcon, null, tint = p.textSecondary, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(meta, style = styles.subhead, color = p.textSecondary)
                }
            }
        }
    }
}

private val GenericDayTheme = Regex("^(D[ií]a|Day)\\s*\\d+$", RegexOption.IGNORE_CASE)

private fun dayTitle(language: AppLanguage, day: Int) = language.pick("Día $day", "Day $day", "Dia $day")

@Composable
private fun NovenaOverviewView(
    novena: Novena,
    currentLanguage: AppLanguage,
    daysPrayed: Set<Int>,
    startedOn: Long?,
    onOpenDay: (Int) -> Unit,
    onToggleDay: (Int) -> Unit,
    onReset: () -> Unit,
    bottomPadding: Dp
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val context = LocalContext.current
    var confirmReset by remember { mutableStateOf(false) }
    val title = novena.title.get(currentLanguage)
    AppInlineTitle(title)
    val total = novena.days.size
    val finished = isNovenaFinished(novena, daysPrayed)
    val next = nextNovenaDay(novena, daysPrayed)
    val dateFormat = remember(currentLanguage) { DateFormat.getDateInstance(DateFormat.MEDIUM, Locale(currentLanguage.code)) }
    val started = startedOn?.takeIf { daysPrayed.isNotEmpty() }?.let { dateFormat.format(Date(it)) }
    val description = novena.description.get(currentLanguage)
    val patron = novena.patron.get(currentLanguage).takeIf {
        it.isNotBlank() && !it.equals(title, ignoreCase = true) && !title.contains(it, ignoreCase = true)
    }
    val promises = novena.promises.get(currentLanguage)
    val history = novena.miraculousHistory.get(currentLanguage)

    LazyColumn(
        Modifier
            .fillMaxSize()
            .testTag("novena_detail_${novena.id}"),
        contentPadding = PaddingValues(bottom = bottomPadding)
    ) {
        item(key = "hero") {
            RemoteArtHeader(
                title = title,
                imageUrl = novena.imageUrl,
                fallbackAsset = rememberFirstAsset(novenaFallbackArt(novena), ArtLily),
                imageHeight = 320.dp,
                meta = listOfNotNull(
                    currentLanguage.pick("$total días", "$total days", "$total dias"),
                    started?.let { currentLanguage.pick("comenzada el $it", "started $it", "iniciada em $it") }
                ).joinToString(" · "),
                metaIcon = Icons.Outlined.CalendarMonth,
                titleTag = "novena_title"
            ) {
                AppGlassIconButton(
                    icon = Icons.Outlined.IosShare,
                    contentDescription = currentLanguage.pick("Compartir", "Share", "Compartilhar"),
                    onClick = { sharePrayerText(context, title, listOf(title, description).filter { it.isNotBlank() }.joinToString("\n\n")) },
                    onImage = true,
                    tag = "share_novena"
                )
            }
        }
        item(key = "intro") {
            Column(Modifier.padding(horizontal = design.spacing.gutter).offset(y = (-12).dp)) {
                if (patron != null) {
                    Text(patron, style = styles.footnote, color = p.textSecondary)
                    Spacer(Modifier.height(12.dp))
                }
                if (description.isNotBlank()) {
                    Text(description, style = styles.callout, color = p.textPrimary)
                    Spacer(Modifier.height(22.dp))
                }
                if (daysPrayed.isNotEmpty()) {
                    val progressText = currentLanguage.pick(
                        "${daysPrayed.size} de $total días rezados",
                        "${daysPrayed.size} of $total days prayed",
                        "${daysPrayed.size} de $total dias rezados"
                    )
                    AppProgressBar(
                        daysPrayed.size.toFloat() / total.coerceAtLeast(1),
                        color = if (finished) p.success else null,
                        modifier = Modifier.semantics { stateDescription = progressText }
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(progressText, style = styles.footnote, color = p.textSecondary)
                    Spacer(Modifier.height(20.dp))
                }
                AppPrimaryCapsule(
                    label = when {
                        daysPrayed.isEmpty() -> currentLanguage.pick("Comenzar novena", "Begin novena", "Começar novena")
                        finished -> currentLanguage.pick("Rezar de nuevo", "Pray again", "Rezar de novo")
                        else -> currentLanguage.pick("Continuar · Día $next", "Continue · Day $next", "Continuar · Dia $next")
                    },
                    onClick = { onOpenDay(if (finished) novena.days.first().dayNumber else next) },
                    icon = Icons.Filled.PlayArrow,
                    tag = "novena_start_button"
                )
            }
        }
        item(key = "days_header") {
            PrayerSectionHeader(
                currentLanguage.pick("Días", "Days", "Dias"),
                trailing = "${daysPrayed.size}/$total"
            )
        }
        itemsIndexed(novena.days, key = { _, d -> "day_${d.dayNumber}" }) { index, day ->
            val position = prayerGroupPosition(index, novena.days.size)
            val prayed = day.dayNumber in daysPrayed
            val theme = day.theme.get(currentLanguage).trim()
            val label = dayTitle(currentLanguage, day.dayNumber)
            val subtitle = listOfNotNull(
                theme.takeIf { it.isNotBlank() && !GenericDayTheme.matches(it) },
                day.scripture.takeIf { it.isNotBlank() }
            ).joinToString(" · ").ifBlank { null }
            Box(Modifier.groupedItem(position)) {
                AppCheckRow(
                    label = label,
                    checked = prayed,
                    onToggle = { onToggleDay(day.dayNumber) },
                    subtitle = subtitle,
                    trailingText = if (!finished && daysPrayed.isNotEmpty() && day.dayNumber == next)
                        currentLanguage.pick("Siguiente", "Next", "Próximo") else null,
                    onOpen = { onOpenDay(day.dayNumber) },
                    showDivider = position == GroupPosition.First || position == GroupPosition.Middle,
                    tag = "novena_day_${day.dayNumber}",
                    checkTag = "novena_day_check_${day.dayNumber}",
                    modifier = Modifier.semantics {
                        stateDescription = if (prayed) currentLanguage.pick("Rezado", "Prayed", "Rezado")
                        else currentLanguage.pick("Pendiente", "Pending", "Pendente")
                    }
                )
            }
        }
        if (promises.isNotBlank() || history.isNotBlank()) {
            item(key = "promises") {
                PrayerSectionHeader(
                    currentLanguage.pick("Promesas y milagros registrados", "Recorded promises and miracles", "Promessas e milagres registrados")
                )
                Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                    if (promises.isNotBlank()) PrayerReadingParagraphs(promises)
                    if (promises.isNotBlank() && history.isNotBlank()) Spacer(Modifier.height(16.dp))
                    if (history.isNotBlank()) Text(history, style = styles.callout, color = p.textSecondary)
                }
            }
        }
        if (daysPrayed.isNotEmpty()) {
            item(key = "reset") {
                Spacer(Modifier.height(design.spacing.sectionGap))
                AppGroupedRow(
                    title = currentLanguage.pick("Reiniciar novena", "Restart novena", "Reiniciar novena"),
                    position = GroupPosition.Single,
                    onClick = { confirmReset = true },
                    tag = "restart_novena_button",
                    trailing = {}
                )
            }
        }
    }

    if (confirmReset) {
        AppConfirmDialog(
            title = currentLanguage.pick("¿Reiniciar la novena?", "Restart this novena?", "Reiniciar a novena?"),
            message = currentLanguage.pick(
                "Se borrará el progreso de ${daysPrayed.size} días.",
                "Progress for ${daysPrayed.size} days will be cleared.",
                "O progresso de ${daysPrayed.size} dias será apagado."
            ),
            confirmLabel = currentLanguage.pick("Reiniciar", "Restart", "Reiniciar"),
            dismissLabel = currentLanguage.pick("Cancelar", "Cancel", "Cancelar"),
            onConfirm = {
                confirmReset = false
                onReset()
            },
            onDismiss = { confirmReset = false },
            destructive = true,
            tag = "restart_novena_dialog"
        )
    }
}

// ============================================================ novena day

@Composable
private fun NovenaDayView(
    novena: Novena,
    currentLanguage: AppLanguage,
    activeDay: Int,
    onSelectDay: (Int) -> Unit,
    daysPrayed: Set<Int>,
    currentlySpeaking: String?,
    onListen: (String) -> Unit,
    onStop: () -> Unit,
    onToggleDay: (Int) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val context = LocalContext.current
    var showReading by remember { mutableStateOf(false) }
    AppHideTabBar()

    val dayData = novena.days.firstOrNull { it.dayNumber == activeDay } ?: novena.days.first()
    val prayerText = dayData.prayer.get(currentLanguage)
    // The source stores each day's prayer in both fields: the meditation is
    // shown only when it says something the prayer does not.
    val meditation = dayData.meditation.get(currentLanguage).takeUnless { it.trim() == prayerText.trim() }.orEmpty()
    val guidance = dayData.intentionGuidance.get(currentLanguage)
    val theme = dayData.theme.get(currentLanguage).trim().takeIf { it.isNotBlank() && !GenericDayTheme.matches(it) }
    val prayed = dayData.dayNumber in daysPrayed
    val speaking = currentlySpeaking != null
    val total = novena.days.size
    val dayLabel = dayTitle(currentLanguage, dayData.dayNumber)
    AppInlineTitle("$dayLabel · ${splitNovenaTitle(novena.title.get(currentLanguage), currentLanguage).subject}")
    val paragraphs = remember(prayerText) { prayerText.split(Regex("\\n\\s*\\n")).map { it.trim() }.filter { it.isNotEmpty() } }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .testTag("novena_day_reader"),
        contentPadding = PaddingValues(bottom = appScreenInsets().calculateBottomPadding() + 40.dp)
    ) {
        item(key = "hero") {
            RemoteArtHeader(
                title = dayLabel,
                eyebrow = novena.title.get(currentLanguage),
                imageUrl = novena.imageUrl,
                fallbackAsset = rememberFirstAsset(novenaFallbackArt(novena), ArtLily),
                imageHeight = 210.dp,
                meta = if (prayed) currentLanguage.pick("de $total · Rezado", "of $total · Prayed", "de $total · Rezado")
                else currentLanguage.pick("de $total", "of $total", "de $total"),
                titleTag = "novena_day_title"
            ) {
                AppGlassIconButton(
                    icon = if (speaking) Icons.Filled.Stop else Icons.Outlined.Headphones,
                    contentDescription = if (speaking) currentLanguage.pick("Detener", "Stop", "Parar")
                    else currentLanguage.pick("Escuchar", "Listen", "Ouvir"),
                    onClick = { if (speaking) onStop() else onListen(prayerText) },
                    onImage = true,
                    tag = "novena_listen_button"
                )
                AppGlassIconButton(
                    icon = Icons.Outlined.TextFields,
                    contentDescription = currentLanguage.pick("Tamaño del texto", "Text size", "Tamanho do texto"),
                    onClick = { showReading = true },
                    onImage = true,
                    tag = "novena_text_settings"
                )
            }
        }
        item(key = "strip") {
            DayStrip(
                days = novena.days.map { it.dayNumber },
                activeDay = dayData.dayNumber,
                prayed = daysPrayed,
                onSelect = onSelectDay,
                currentLanguage = currentLanguage
            )
            Spacer(Modifier.height(22.dp))
        }
        if (theme != null) {
            item(key = "theme") {
                Text(theme, style = styles.title2, color = p.textPrimary, modifier = Modifier.padding(horizontal = design.spacing.gutter))
                Spacer(Modifier.height(14.dp))
            }
        }
        if (meditation.isNotBlank()) {
            item(key = "meditation") {
                Column(Modifier.padding(horizontal = design.spacing.gutter)) { PrayerReadingParagraphs(meditation) }
                Spacer(Modifier.height(20.dp))
            }
        }
        if (guidance.isNotBlank()) {
            item(key = "guidance") {
                Box(Modifier.padding(horizontal = design.spacing.gutter)) {
                    AppGlassSurface(Modifier.fillMaxWidth(), contentPadding = PaddingValues(18.dp)) {
                        Column {
                            Text(currentLanguage.pick("Tu intención", "Your intention", "Sua intenção"), style = styles.footnote, color = p.textSecondary)
                            Spacer(Modifier.height(4.dp))
                            Text(guidance, style = styles.callout, color = p.textPrimary)
                        }
                    }
                }
                Spacer(Modifier.height(22.dp))
            }
        }
        itemsIndexed(paragraphs, key = { i, _ -> "para_$i" }) { _, para ->
            Box(Modifier.padding(horizontal = design.spacing.gutter).padding(bottom = 14.dp)) {
                PrayerReadingText(para, highlighted = false)
            }
        }
        item(key = "complete") {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = 22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!prayed) {
                    AppPrimaryCapsule(
                        label = currentLanguage.pick("Completar día ${dayData.dayNumber}", "Complete day ${dayData.dayNumber}", "Completar dia ${dayData.dayNumber}"),
                        onClick = { onToggleDay(dayData.dayNumber) },
                        icon = Icons.Filled.Check,
                        tag = "complete_novena_day_button"
                    )
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(22.dp).background(p.success, CircleShape), contentAlignment = Alignment.Center) {
                            Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(15.dp))
                        }
                        Spacer(Modifier.width(10.dp))
                        Text(
                            currentLanguage.pick("Día ${dayData.dayNumber} rezado", "Day ${dayData.dayNumber} prayed", "Dia ${dayData.dayNumber} rezado"),
                            style = styles.headline,
                            color = p.textPrimary
                        )
                    }
                    Spacer(Modifier.height(14.dp))
                    AppSecondaryCapsule(
                        label = currentLanguage.pick("Deshacer día", "Undo day", "Desfazer dia"),
                        onClick = { onToggleDay(dayData.dayNumber) },
                        fillWidth = true,
                        tag = "complete_novena_day_button"
                    )
                }
                Spacer(Modifier.height(18.dp))
                AppSecondaryCapsule(
                    label = currentLanguage.pick("Compartir", "Share", "Compartilhar"),
                    icon = Icons.Outlined.IosShare,
                    onClick = { sharePrayerText(context, "${novena.title.get(currentLanguage)} · $dayLabel", prayerText) },
                    tag = "share_novena_day"
                )
            }
        }
    }

    if (showReading) PrayerReadingSheet(currentLanguage) { showReading = false }
}

/** Every day of the novena as a numbered circle; scrolls for 30–275-day novenas. */
@Composable
private fun DayStrip(
    days: List<Int>,
    activeDay: Int,
    prayed: Set<Int>,
    onSelect: (Int) -> Unit,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val state = rememberLazyListState()
    LaunchedEffect(activeDay) {
        val index = days.indexOf(activeDay).coerceAtLeast(0)
        state.animateScrollToItem((index - 3).coerceAtLeast(0))
    }
    LazyRow(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("novena_day_strip"),
        contentPadding = PaddingValues(horizontal = design.spacing.gutter),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(days, key = { it }) { day ->
            val active = day == activeDay
            val done = day in prayed
            Box(
                Modifier
                    .size(44.dp)
                    .lumenPress({ onSelect(day) }, 0.9f)
                    .semantics {
                        contentDescription = dayTitle(currentLanguage, day)
                        selected = active
                        stateDescription = if (done) currentLanguage.pick("Rezado", "Prayed", "Rezado")
                        else currentLanguage.pick("Pendiente", "Pending", "Pendente")
                    }
                    .testTag("novena_day_pill_$day"),
                contentAlignment = Alignment.Center
            ) {
                val circle = Modifier.size(38.dp)
                Box(
                    when {
                        active -> circle.background(p.action, CircleShape)
                        done -> circle.background(p.success, CircleShape)
                        else -> circle.lumenGlass(CircleShape, lifted = false)
                    },
                    contentAlignment = Alignment.Center
                ) {
                    if (done && !active) Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(17.dp))
                    else Text("$day", style = styles.subhead, color = if (active) p.onAction else p.textPrimary)
                }
            }
        }
    }
}

// ========================================================== shared pieces

/** Prayer text in the reader font; a soft glass wash while it is being read aloud. */
@Composable
internal fun PrayerReadingText(text: String, highlighted: Boolean, modifier: Modifier = Modifier) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val wash by animateColorAsState(if (highlighted) p.glassStrong else Color.Transparent, tween(300), label = "wash")
    Text(
        text = withLiturgicalSigla(text, prayerQuietSigil()),
        style = styles.reading,
        color = p.textPrimary,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(wash)
            .widthIn(max = design.spacing.readingMaxWidth)
    )
}

/** Paragraphs separated by blank lines, in the reader font. */
@Composable
internal fun PrayerReadingParagraphs(text: String) {
    val paragraphs = text.split(Regex("\\n\\s*\\n")).map { it.trim() }.filter { it.isNotEmpty() }
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        paragraphs.forEach { PrayerReadingText(it, highlighted = false) }
    }
}

/** The calm sigla style for Lumen pages: secondary ink, semibold. */
@Composable
internal fun prayerQuietSigil(): SpanStyle = SpanStyle(
    color = LocalAppDesign.current.palette.textSecondary,
    fontWeight = FontWeight.SemiBold
)

/** The shared "Aa" sheet, fed with the current preferences. */
@Composable
internal fun PrayerReadingSheet(currentLanguage: AppLanguage, onDismiss: () -> Unit) {
    val store = LocalPreferencesStore.current
    val prefs by remember(store) { store?.preferences ?: flowOf(null) }.collectAsState(initial = null as UserPreferences?)
    AppReadingSettingsSheet(
        currentScale = prefs?.fontSizeScale ?: 1f,
        currentFontId = prefs?.fontFamilyChoice ?: "",
        onDismiss = onDismiss,
        labels = ReadingSheetLabels(
            title = currentLanguage.pick("Texto", "Text", "Texto"),
            size = currentLanguage.pick("Tamaño", "Size", "Tamanho"),
            font = currentLanguage.pick("Tipografía", "Typeface", "Tipografia"),
            defaultFont = currentLanguage.pick("Predeterminada", "Default", "Padrão")
        )
    )
}

/** Shares plain text through the system share sheet. */
internal fun sharePrayerText(context: Context, subject: String, text: String) {
    runCatching {
        val send = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, "$subject\n\n$text")
        }
        context.startActivity(Intent.createChooser(send, subject).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}

/** Strips the typed sigla for one-line excerpts. */
private fun plainPrayerText(text: String): String =
    text.replace(SiglaPattern, "").replace(Regex("\\s+"), " ").trim()

/** ℣ — the versicle, as the liturgical books print it. */
internal const val VersicleSign = "℣"

/** ℟ — the response. */
internal const val ResponseSign = "℟"

/** The typed-out sigla ("V/." and "R/.") the bundled texts carry. */
internal val SiglaPattern = Regex("""(?<!\p{L})([VR])/\.""")

/** The sigla's rubric: the identity's accent (used by the Bible's lessons). */
@Composable
internal fun rubricSigil(): SpanStyle = SpanStyle(
    color = LocalAppDesign.current.palette.accent,
    fontWeight = FontWeight.SemiBold
)

/**
 * The text with each typed-out "V/." and "R/." set as ℣ and ℟ in [rubric], at
 * render time, so the bundled data and the text-to-speech feed stay as they are.
 */
internal fun withLiturgicalSigla(text: String, rubric: SpanStyle): AnnotatedString =
    buildAnnotatedString {
        var last = 0
        SiglaPattern.findAll(text).forEach { match ->
            append(text.substring(last, match.range.first))
            withStyle(rubric) {
                append(if (match.groupValues[1] == "V") VersicleSign else ResponseSign)
            }
            last = match.range.last + 1
        }
        append(text.substring(last))
    }

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

// ------------------------------------------------------- novena progress

/*
 * `NovenaProgressEntity.currentDay` is a bitmask of the days prayed: bit
 * (n - 1) set means day n is done, for days 1..31. Days beyond 31 (the 54-
 * to 275-day novenas) live in [NovenaExtraDaysStore], a parallel store, so
 * no schema change is needed and no existing progress is touched.
 */
internal fun encodeNovenaDays(days: Set<Int>): Int =
    days.filter { it in 1..31 }.fold(0) { mask, day -> mask or (1 shl (day - 1)) }

internal fun decodeNovenaDays(mask: Int): Set<Int> =
    (1..31).filterTo(mutableSetOf()) { mask and (1 shl (it - 1)) != 0 }

/** Days > 31 of long novenas, keyed by novena id ("32,33,40"). */
internal class NovenaExtraDaysStore(context: Context) {
    private val prefs = context.getSharedPreferences("novena_extra_days", Context.MODE_PRIVATE)

    fun all(): Map<String, Set<Int>> = prefs.all.mapNotNull { (key, value) ->
        val days = (value as? String).orEmpty().split(',').mapNotNull { it.trim().toIntOrNull() }.toSet()
        if (days.isEmpty()) null else key to days
    }.toMap()

    fun put(novenaId: String, days: Set<Int>) {
        val extra = days.filter { it > 31 }.sorted()
        prefs.edit().apply {
            if (extra.isEmpty()) remove(novenaId) else putString(novenaId, extra.joinToString(","))
        }.apply()
    }
}

private fun isNovenaFinished(novena: Novena, prayed: Set<Int>): Boolean =
    novena.days.isNotEmpty() && novena.days.all { prayed.contains(it.dayNumber) }

/** The first day not yet prayed, or the first day once the novena is finished. */
private fun nextNovenaDay(novena: Novena, prayed: Set<Int>): Int =
    novena.days.firstOrNull { !prayed.contains(it.dayNumber) }?.dayNumber
        ?: novena.days.firstOrNull()?.dayNumber
        ?: 1

private fun novenaDayOf(language: AppLanguage, day: Int, of: Int): String = language.pick(
    "Día $day de $of",
    "Day $day of $of",
    "Dia $day de $of"
)
