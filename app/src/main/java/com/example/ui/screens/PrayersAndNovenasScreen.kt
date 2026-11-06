package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.Healing
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
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
import com.example.data.repository.CatholicDataRepository
import androidx.activity.compose.BackHandler
import com.example.ui.design.AppChip
import com.example.ui.components.InlineBackLink
import com.example.ui.design.AppBanner
import com.example.ui.design.AppCard
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.ListStyle
import com.example.ui.theme.LocalAppDesign
import coil.compose.AsyncImage
import com.example.ui.theme.LocalAppTextStyles
import android.graphics.BitmapFactory
import java.text.DateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

/** The chaplet: a lead bead and ten small ones, five times over. */
private const val ChapletDecades = 5
private const val ChapletBeadsPerDecade = 10

/** The lead bead of a decade is cut larger than the ten that follow it. */
private const val ChapletLeadBeadScale = 1.6f

/** Ring weight where the identity rules its bead edges rather than filling them. */
private val BeadRingHairline = 0.5.dp

/** The frontispiece plate at the head of a novena that ships with an image. */
private val NovenaPlateHeight = 176.dp

/**
 * Novena photo cards: full-bleed width minus the 20dp gutters, 20dp radius,
 * title overlaid on the bottom scrim. One card per row so the patron's
 * photograph, not a half-width thumbnail, carries the card; 200dp tall
 * leaves room for a two-line title, the patron line and the progress rule
 * above the scrim.
 */
private val NovenaCardHeight = 200.dp

/** Clipped plate across the top of a novena index card. */
private val NovenaCardImageHeight = 108.dp

/** Ive reference geometry: 20dp screen gutters. */
private val IveGutter = 20.dp

/** Category tiles: 18dp radius, frosted. */
private val CategoryTileRadius = RoundedCornerShape(18.dp)

/** Prayers header: full-bleed votive-candle photo band above the title. */
private val PrayersPhotoBandHeight = 168.dp

/** Bundled hero photos are decoded at most this many pixels on a side. */
private const val HeroMaxDimension = 1080

/** Novena image cards: full-bleed photo, 20dp radius. */
private val NovenaCardRadius = RoundedCornerShape(20.dp)

/** Prayer list rows: 16dp radius, frosted. */
private val PrayerRowRadius = RoundedCornerShape(16.dp)

/** Minimum touch target for every control this screen owns (AAA). */
private val MinTouchTarget = 48.dp

/** Vertical gap between stacked prayer cards/groups: 12-16dp, so the
 *  season card and the tile below it never touch as a 0dp seam. */
private val CardStackGap = 14.dp

/**
 * The Vatican devotional, the chaplet of Divine Mercy, the 246 official novenas
 * and the night office, behind one selector.
 *
 * Almost all of it is reading rather than operating: a prayer, a meditation and
 * a promise are set in the identity's reading face and held to its reading
 * measure, exactly as scripture is. The novenas are an index of 246 entries,
 * not a menu, so they are ruled rows carrying their own progress — and the day
 * of a novena opens as a page, with the nine days listed under it.
 */
@Composable
fun PrayersAndNovenasScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    dao: SanctaFidesDao? = null,
    initialPrayerId: String? = null
) {
    val design = LocalAppDesign.current
    val scope = rememberCoroutineScope()

    var selectedTab by remember { mutableIntStateOf(0) }

    // Devotional
    var prayerSearchQuery by remember { mutableStateOf("") }
    var selectedThematicTag by remember { mutableStateOf<String?>(null) }
    // A prayer opens as its own page; the list carries only a line of each.
    // A deep link (initialPrayerId, e.g. from Doctrine's "Rezar ahora") opens
    // its prayer on arrival; an unknown id is ignored, never a blank page.
    var openPrayerId by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(initialPrayerId) {
        if (initialPrayerId != null &&
            repository.vaticanPrayers.any { it.id == initialPrayerId }
        ) {
            selectedTab = 0
            openPrayerId = initialPrayerId
        }
    }
    var showChaplet by remember { mutableStateOf(false) }

    // Novenas
    var selectedNovena by remember { mutableStateOf<Novena?>(null) }
    var novenaSearchQuery by remember { mutableStateOf("") }
    var selectedNovenaTag by remember { mutableStateOf<String?>(null) }
    var activeNovenaDay by remember { mutableIntStateOf(1) }
    // Keyed by novena id: a single shared set of day numbers reported the same
    // progress for all 246 of them, so every row read "day 1 of 9".
    // This is the session's own record: the whole record when there is no DAO
    // (previews, the screenshot test), and the optimistic echo of a write
    // until the database flow hands the row back when there is one.
    var sessionDaysPrayed by remember { mutableStateOf(emptyMap<String, Set<Int>>()) }
    // The intention `NovenaProgressEntity` carries alongside the day count. No
    // control writes it yet; it is held here so that wiring has one home.
    var personalIntention by remember { mutableStateOf("") }

    val persistedProgress by remember(dao) {
        dao?.getAllNovenaProgress() ?: flowOf(emptyList<NovenaProgressEntity>())
    }.collectAsState(initial = emptyList())
    val startedOn = remember(persistedProgress) {
        persistedProgress.associate { it.novenaId to it.startDate }
    }
    // The session's marks are the optimistic echo of a write until the
    // database flow hands the row back when there is one. A toggle writes the
    // whole set back, so the union converges on the toggled set once the
    // flow lands.
    val daysPrayed = remember(persistedProgress, sessionDaysPrayed) {
        val stored = persistedProgress.associate { it.novenaId to decodeNovenaDays(it.currentDay) }
        (stored.keys + sessionDaysPrayed.keys).associateWith {
            stored[it].orEmpty() + sessionDaysPrayed[it].orEmpty()
        }
    }

    val currentlySpeaking by ttsManager.currentlySpeaking.collectAsState()

    val tabsInset = segmentedTabsInset()

    // One title for the screen, then the control that picks the view. Both
    // are the first rows of whichever list is showing, so they scroll away.
    val header: LazyListScope.() -> Unit = {
        item(key = "prayers_screen_title") {
            PrayersPhotoHeader(title = currentLanguage.pick("Oraciones", "Prayers", "Orações"))
        }
        item(key = "prayers_screen_tabs") {
            AppSegmentedTabs(
                // The chaplet is a devotion, opened from the Prayers list,
                // and Compline goes by the name a newcomer knows.
                options = listOf(
                    currentLanguage.pick("Oraciones", "Prayers", "Orações"),
                    currentLanguage.pick("Novenas", "Novenas", "Novenas"),
                    currentLanguage.pick("Noche", "Night prayer", "Noite")
                ),
                selectedIndex = selectedTab,
                onSelect = { selectedTab = it },
                modifier = Modifier
                    .padding(horizontal = tabsInset)
                    .padding(bottom = design.spacing.cardGap / 2)
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("prayers_and_novenas_screen")
    ) {
        val openPrayer = openPrayerId?.let { id -> repository.vaticanPrayers.firstOrNull { it.id == id } }
        when {
            selectedTab == 0 && openPrayer != null -> {
                BackHandler { openPrayerId = null }
                PrayerDetailView(
                    prayer = openPrayer,
                    currentLanguage = currentLanguage,
                    currentlySpeaking = currentlySpeaking,
                    onBack = { openPrayerId = null },
                    onPlayAudio = {
                        ttsManager.speakDialogue(
                            openPrayer.leaderText.get(currentLanguage),
                            openPrayer.responseText?.get(currentLanguage) ?: ""
                        )
                    },
                    onStopAudio = { ttsManager.stop() }
                )
            }

            selectedTab == 0 && showChaplet -> {
                BackHandler { showChaplet = false }
                DivineMercyChapletView(
                    ttsManager = ttsManager,
                    currentLanguage = currentLanguage,
                    onBack = { showChaplet = false },
                    onOpenNovena = {
                        showChaplet = false
                        selectedTab = 1
                        selectedNovena = repository.officialNovenas
                            .firstOrNull { it.id == "divine_mercy" }
                            ?: repository.officialNovenas.firstOrNull()
                        activeNovenaDay = selectedNovena
                            ?.let { nextNovenaDay(it, daysPrayed[it.id].orEmpty()) }
                            ?: 1
                    }
                )
            }

            selectedTab == 0 -> VaticanDevotionalView(
                header = header,
                repository = repository,
                currentLanguage = currentLanguage,
                searchQuery = prayerSearchQuery,
                onSearchQueryChange = { prayerSearchQuery = it },
                selectedTag = selectedThematicTag,
                onSelectTag = { selectedThematicTag = it },
                onOpenPrayer = { openPrayerId = it.id },
                onOpenChaplet = { showChaplet = true }
            )

            selectedTab == 1 -> {
                val novena = selectedNovena
                if (novena == null) {
                    NovenaIndexView(
                        header = header,
                        novenas = repository.officialNovenas,
                        currentLanguage = currentLanguage,
                        searchQuery = novenaSearchQuery,
                        onSearchQueryChange = { novenaSearchQuery = it },
                        selectedTag = selectedNovenaTag,
                        onSelectTag = { selectedNovenaTag = it },
                        daysPrayed = daysPrayed,
                        startedOn = startedOn,
                        onOpenNovena = {
                            selectedNovena = it
                            activeNovenaDay = nextNovenaDay(it, daysPrayed[it.id].orEmpty())
                        }
                    )
                } else {
                    NovenaDayView(
                        novena = novena,
                        currentLanguage = currentLanguage,
                        activeDay = activeNovenaDay,
                        onSelectDay = { activeNovenaDay = it },
                        daysPrayed = daysPrayed[novena.id].orEmpty(),
                        currentlySpeaking = currentlySpeaking,
                        onListen = { ttsManager.speakSingle(it) },
                        onStop = { ttsManager.stop() },
                        onMarkDayPrayed = {
                            val day = activeNovenaDay
                            val currently = daysPrayed[novena.id].orEmpty()
                            val wasPrayed = currently.contains(day)
                            // Toggle: a prayed day unmarks (undo) instead of
                            // the set only ever growing.
                            val prayed = if (wasPrayed) currently - day else currently + day
                            sessionDaysPrayed = sessionDaysPrayed + (novena.id to prayed)
                            if (dao != null) {
                                scope.launch {
                                    val existing = dao.getNovenaProgress(novena.id)
                                    val now = System.currentTimeMillis()
                                    // Write the toggled set whole: re-unioning
                                    // with the stored row would resurrect a day
                                    // the user just undid.
                                    dao.saveNovenaProgress(
                                        NovenaProgressEntity(
                                            novenaId = novena.id,
                                            currentDay = encodeNovenaDays(prayed),
                                            personalIntention = existing?.personalIntention
                                                ?: personalIntention,
                                            startDate = existing?.startDate ?: now,
                                            lastUpdated = now,
                                            isCompleted = isNovenaFinished(novena, prayed)
                                        )
                                    )
                                }
                            }
                            // Advance only on a fresh mark; undo stays on the day.
                            if (!wasPrayed && activeNovenaDay < novena.days.size) activeNovenaDay++
                        },
                        onResetNovena = {
                            sessionDaysPrayed = sessionDaysPrayed + (novena.id to emptySet())
                            activeNovenaDay = 1
                            if (dao != null) {
                                scope.launch { dao.deleteNovenaProgress(novena.id) }
                            }
                        },
                        onBack = { selectedNovena = null }
                    )
                }
            }

            else -> NightPrayersScreen(
                repository = repository,
                ttsManager = ttsManager,
                currentLanguage = currentLanguage,
                header = header
            )
        }
    }
}

// ----------------------------------------------------------------- devotional

@Composable
private fun VaticanDevotionalView(
    header: LazyListScope.() -> Unit,
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedTag: String?,
    onSelectTag: (String?) -> Unit,
    onOpenPrayer: (VaticanPrayer) -> Unit,
    onOpenChaplet: () -> Unit
) {
    val design = LocalAppDesign.current

    // Reference screen 2 grid: six tiles in three columns. "Básicas" filters
    // by PrayerCategory.BASIC (the Fundamental prayers, straight from
    // repository data); "Intenciones" clears the filter (all intentions);
    // the rest filter by the thematic tags the prayers already carry.
    // Tiles are mutually exclusive: picking one clears the other kind.
    var selectedCategory by remember { mutableStateOf<PrayerCategory?>(null) }
    val categoryTiles = listOf(
        PrayerCategoryTile(
            key = "basic",
            label = currentLanguage.pick("Básicas", "Basics", "Básicas"),
            icon = Icons.Outlined.MenuBook,
            tag = null,
            category = PrayerCategory.BASIC
        ),
        PrayerCategoryTile(
            key = "all",
            label = currentLanguage.pick("Intenciones", "Intentions", "Intenções"),
            icon = Icons.Outlined.FormatListBulleted,
            tag = null,
            category = null
        ),
        PrayerCategoryTile(
            key = "family",
            label = currentLanguage.pick("Familia", "Family", "Família"),
            icon = Icons.Outlined.Groups,
            tag = "family",
            category = null
        ),
        PrayerCategoryTile(
            key = "protection",
            label = currentLanguage.pick("Protección", "Protection", "Proteção"),
            icon = Icons.Outlined.Shield,
            tag = "protection",
            category = null
        ),
        PrayerCategoryTile(
            key = "health",
            label = currentLanguage.pick("Sanación", "Healing", "Cura"),
            icon = Icons.Outlined.Healing,
            tag = "health",
            category = null
        ),
        PrayerCategoryTile(
            key = "forgiveness",
            label = currentLanguage.pick("Perdón", "Forgiveness", "Perdão"),
            icon = Icons.Outlined.Handshake,
            tag = "forgiveness",
            category = null
        )
    )

    val prayers = repository.searchPrayers(
        query = searchQuery,
        category = selectedCategory,
        tag = selectedTag
    )
    val filtering = searchQuery.isNotBlank() || selectedTag != null || selectedCategory != null
    var howToPrayExpanded by remember { mutableStateOf(false) }
    // The season-relevant prayer, resolved once from the liturgical day.
    // Shown only when unfiltered, floated as the first content; it is then
    // excluded from the groups below so it appears exactly once.
    val seasonPrayer = remember(repository) {
        val info = LiturgicalCalendar.dayInfo(LiturgicalCalendar.today())
        val id = seasonPrayerId(info)
        repository.vaticanPrayers.firstOrNull { it.id == id }
            ?: repository.vaticanPrayers.firstOrNull { it.id == "our_father" }
            ?: repository.vaticanPrayers.firstOrNull()
    }

    // No top content padding or spacer above the header: the photo band
    // is the first item and sits flush at the top of the list.
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 0.dp)
    ) {
        header()

        // Reference order: title -> search -> tiles. Search sits directly
        // under the title/segmented tabs, above the featured liturgical
        // cards and the category tiles.
        item {
            AppSearchField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = currentLanguage.pick(
                    "Buscar una oración",
                    "Search prayers",
                    "Buscar uma oração"
                ),
                modifier = Modifier
                    .padding(horizontal = IveGutter)
                    .testTag("prayer_search_input")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // The season prayer floats first when the list is unfiltered;
        // searching or filtering hides it so results stay clean.
        if (!filtering && seasonPrayer != null) {
            item(key = "season_prayer") {
                PrayersSectionHeader(
                    title = currentLanguage.pick(
                        "Del tiempo litúrgico",
                        "For this season",
                        "Para este tempo"
                    )
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IveGutter),
                    verticalArrangement = Arrangement.spacedBy(CardStackGap)
                ) {
                    FrostedPrayerRow(
                        title = seasonPrayer.title.get(currentLanguage),
                        subtitle = LiturgicalCalendar.dayInfo(LiturgicalCalendar.today())
                            .season.displayName.get(currentLanguage),
                        onClick = { onOpenPrayer(seasonPrayer) },
                        modifier = Modifier.testTag("season_prayer")
                    )
                }
                // 12-16dp air before the next card ("How to pray"): without
                // it the season row and that tile met as a 0dp seam that
                // read as one overlapping surface.
                Spacer(Modifier.height(CardStackGap))
            }
        }

        // "How to pray" sits at the top of the Prayers tab when the list is
        // unfiltered; searching or filtering hides it so results stay clean.
        if (!filtering) {
            item(key = "how_to_pray") {
                HowToPraySection(
                    currentLanguage = currentLanguage,
                    expanded = howToPrayExpanded,
                    onToggle = { howToPrayExpanded = !howToPrayExpanded }
                )
            }
        }

        item {
            PrayersSectionHeader(
                title = currentLanguage.pick("Categorías", "Categories", "Categorias")
            )
            PrayerCategoryTiles(
                tiles = categoryTiles,
                selectedTag = selectedTag,
                selectedCategory = selectedCategory,
                onSelect = { tile ->
                    selectedCategory = tile.category
                    onSelectTag(tile.tag)
                },
                testTag = "prayer_theme_picker"
            )
        }

        if (prayers.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Ninguna oración coincide", "No matching prayers", "Nenhuma oração corresponde"),
                    body = currentLanguage.pick(
                        "Prueba otra palabra o elige otra intención.",
                        "Try another word or choose another intention.",
                        "Tente outra palavra ou escolha outra intenção."
                    ),
                    icon = Icons.Default.SearchOff
                )
                AppSecondaryButton(
                    label = currentLanguage.pick("Ver todas", "Show all prayers", "Ver todas"),
                    onClick = {
                        onSearchQueryChange("")
                        onSelectTag(null)
                        selectedCategory = null
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IveGutter)
                        .testTag("prayer_clear_filters")
                )
            }
        } else {
            // The daily rhythm is a useful first choice, not seven equally
            // weighted categories with one row each. Search results remain a
            // single index; grouping never conceals or changes filtering.
            // When unfiltered, Fundamental Prayers (PrayerCategory.BASIC,
            // straight from repository.vaticanPrayers — never a hard-coded
            // title list) lead, so newcomers start with the prayers the
            // Church teaches first. Each prayer appears once: a BASIC prayer
            // that is also daily_routine shows only under Fundamental, and
            // "Other" excludes both earlier groups.
            val fundamental = prayers.filter { it.category == PrayerCategory.BASIC }
            val daily = prayers.filter { "daily_routine" in it.thematicTags }
                .filterNot { it in fundamental }
            // The floated season prayer is excluded here (it is already shown
            // first above) so it is never listed twice when unfiltered.
            val withoutSeason = prayers.filterNot { it.id == seasonPrayer?.id }
            val groups = if (filtering) {
                listOf(currentLanguage.pick("Resultados", "Results", "Resultados") to prayers)
            } else {
                listOf(
                    PrayerCategory.BASIC.title.get(currentLanguage) to fundamental.filterNot { it.id == seasonPrayer?.id },
                    currentLanguage.pick("Cada día", "Every day", "Todos os dias") to daily.filterNot { it.id == seasonPrayer?.id },
                    currentLanguage.pick("Otras oraciones", "Other prayers", "Outras orações") to withoutSeason.filterNot { it in fundamental || it in daily }
                )
            }
            groups.filter { it.second.isNotEmpty() }.forEachIndexed { groupIndex, (label, entries) ->
                item(key = "prayer_group_$groupIndex") {
                    PrayersSectionHeader(title = label)
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = IveGutter),
                        verticalArrangement = Arrangement.spacedBy(CardStackGap)
                    ) {
                        entries.forEach { prayer ->
                            FrostedPrayerRow(
                                title = prayer.title.get(currentLanguage),
                                onClick = { onOpenPrayer(prayer) },
                                modifier = Modifier.testTag("prayer_row_${prayer.id}")
                            )
                        }
                    }
                }
            }
            if (!filtering) {
                item(key = "guided_chaplet") {
                    Spacer(Modifier.height(CardStackGap))
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = IveGutter),
                        verticalArrangement = Arrangement.spacedBy(CardStackGap)
                    ) {
                        FrostedPrayerRow(
                            title = currentLanguage.pick(
                                "Coronilla de la Divina Misericordia",
                                "Chaplet of Divine Mercy",
                                "Terço da Divina Misericórdia"
                            ),
                            onClick = onOpenChaplet,
                            modifier = Modifier.testTag("open_chaplet_row")
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/**
 * Lectionary keys of the Marian solemnities/feasts in the General Calendar
 * ([LiturgicalCalendar.fixedDays]): on these, the season prayer yields to a
 * Marian prayer instead of the season default.
 */
private val MarianSeasonKeys = setOf(
    "mary_mother_of_god",
    "annunciation",
    "assumption",
    "immaculate_conception"
)

/**
 * The `VaticanPrayer` id for [info]'s season, resolved by stable data ids —
 * never by matching a displayed title. Lent (and Holy Week/Triduum) yields the
 * Act of Contrition, Advent the Advent antiphons, Easter the Easter prayer,
 * Christmas its own prayer, Ordinary Time the Lord's Prayer, and Marian
 * feasts the Memorare.
 */
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

/** One prayer-category grid tile: its filter is either a thematic tag or
 * a [PrayerCategory]; the "all intentions" tile carries neither. */
private data class PrayerCategoryTile(
    val key: String,
    val label: String,
    val icon: ImageVector,
    val tag: String?,
    val category: PrayerCategory?
)

/** Prayer categories as a 3-column grid of ~104dp frosted tiles (18dp
 * radius, line icon over label), per the reference screen. A tile is
 * selected when its tag — or, for "Básicas", its category — matches; the
 * all-intentions tile is selected when nothing is filtered. Selected
 * tiles go black with white content, the same affordance the old chips
 * used. The list rows below the grid (Fundamental prayers with chevrons)
 * are untouched. */
@Composable
private fun PrayerCategoryTiles(
    tiles: List<PrayerCategoryTile>,
    selectedTag: String?,
    selectedCategory: PrayerCategory?,
    onSelect: (PrayerCategoryTile) -> Unit,
    testTag: String
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = IveGutter)
            .testTag(testTag),
        verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
    ) {
        tiles.chunked(3).forEach { rowTiles ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
            ) {
                rowTiles.forEach { tile ->
                    val isSelected = if (tile.category != null) {
                        selectedCategory == tile.category
                    } else if (tile.tag != null) {
                        selectedTag == tile.tag
                    } else {
                        selectedTag == null && selectedCategory == null
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .height(104.dp)
                            .clip(CategoryTileRadius)
                            .background(
                                if (isSelected) design.palette.accentMuted else design.palette.groupedSurface
                            )
                            .clickable(role = Role.Button) { onSelect(tile) }
                            .semantics { this.selected = isSelected }
                            .padding(horizontal = 8.dp, vertical = 12.dp)
                            .testTag("prayer_category_tile_${tile.key}"),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp,
                            Alignment.CenterVertically
                        )
                    ) {
                        Icon(
                            imageVector = tile.icon,
                            contentDescription = null,
                            tint = if (isSelected) design.palette.accent else design.palette.textPrimary,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = tile.label,
                            style = styles.rowTitle,
                            color = if (isSelected) design.palette.accent else design.palette.textPrimary,
                            maxLines = 2,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                }
                // Keep a short last row (e.g. a tile removed from the data)
                // on the same 3-column rhythm instead of stretching wide.
                repeat(3 - rowTiles.size) { Spacer(Modifier.weight(1f)) }
            }
        }
    }
}

/**
 * The intention filter as a wrapping row of chips instead of a collapsed
 * menu: every intention is visible at once (no clipped labels behind a
 * dropdown), each chip is a 48dp target, and the kit draws the selected
 * state — checkmark on Material, capsule fill on Apple, rubric rule on
 * Vatican — from tokens, so this screen carries no colour of its own.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PrayerIntentionFilters(
    options: List<Pair<String?, String>>,
    selected: String?,
    onSelect: (String?) -> Unit,
    testTag: String
) {
    val design = LocalAppDesign.current
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = IveGutter)
            .testTag(testTag),
        // Start-aligned on every line: a wrapped last line must sit under
        // the first chip's edge, not float centred as an orphan.
        horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap),
        verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
    ) {
        options.forEach { (value, label) ->
            val isSelected = selected == value
            Box(
                // Natural intrinsic width: no weight, so each chip sizes to
                // its full label and wraps to the next line instead of
                // shrinking/clipping (e.g. 'Health &', 'Peace of').
                Modifier
                    .heightIn(min = 48.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) design.palette.accentMuted else design.palette.groupedSurface)
                    .clickable { onSelect(value) }
                    .semantics { this.selected = isSelected }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    style = LocalAppTextStyles.current.screenSubtitle,
                    color = if (isSelected) Color.White else design.palette.textPrimary,
                    maxLines = 1
                )
            }
        }
    }
}

/** Black pill primary CTA for start/complete actions. */
@Composable
private fun IveBlackPillButton(
    label: String,
    onClick: () -> Unit,
    leadingIcon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    Row(
        modifier
            .heightIn(min = MinTouchTarget)
            .height(52.dp)
            .fillMaxWidth()
            .clip(CircleShape)
            .background(Color.Black)
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (leadingIcon != null) {
            Icon(leadingIcon, null, tint = Color.White, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = label,
            style = LocalAppTextStyles.current.buttonLabel,
            color = Color.White,
            maxLines = 1
        )
    }
}

/**
 * Section header for this screen only, demoted to SemiBold (600): the kit
 * header sets the section title bold (700), which outweighed the screen
 * title's large regular (400) line. The 20sp scaled section title at 600
 * still labels its group, while the 34sp screen title leads by size.
 * Layout mirrors the kit's outside-card header (gutter + 1dp, group gap on
 * top, 8dp below) so rhythm is unchanged; only the weight changes.
 */
@Composable
private fun PrayersSectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = IveGutter + 1.dp)
            .padding(top = design.spacing.insetGroupGap, bottom = 8.dp)
    ) {
        val trailing = subtitle != null && subtitle.length <= 18 && !subtitle.endsWith(".")
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = title,
                style = styles.sectionTitle.copy(
                    fontSize = styles.sectionTitle.fontSize * (20f / 22f),
                    fontWeight = FontWeight.SemiBold
                ),
                color = palette.textPrimary,
                modifier = Modifier.weight(1f).alignByBaseline()
            )
            if (trailing) {
                Spacer(Modifier.width(12.dp))
                Text(
                    text = subtitle!!,
                    style = styles.rowSubtitle,
                    color = palette.textSecondary,
                    maxLines = 1,
                    modifier = Modifier.alignByBaseline()
                )
            }
        }
        if (subtitle != null && !trailing) {
            Spacer(Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = styles.rowSubtitle,
                color = palette.textSecondary
            )
        }
    }
}

/**
 * 18dp frosted category tile. A tile with an [onClick] is a button: it is
 * exposed with button semantics (not a bare text link) and reaches the
 * 48dp minimum target through its content's heightIn.
 */
@Composable
private fun CategoryTile(
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    Column(
        modifier
            .fillMaxWidth()
            .clip(CategoryTileRadius)
            .background(design.palette.groupedSurface)
            .then(if (onClick != null) Modifier.clickable(role = Role.Button, onClick = onClick) else Modifier)
            .padding(design.spacing.cardPadding),
        content = content
    )
}

/** 16dp frosted row with chevron. */
@Composable
private fun FrostedPrayerRow(
    title: String,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Row(
        modifier
            .fillMaxWidth()
            .clip(PrayerRowRadius)
            .background(design.palette.groupedSurface)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = styles.rowTitle, color = design.palette.textPrimary)
            if (subtitle != null) {
                Spacer(Modifier.height(2.dp))
                Text(subtitle, style = styles.rowSubtitle, color = design.palette.textSecondary)
            }
        }
        if (onClick != null) {
            Spacer(Modifier.width(8.dp))
            Icon(
                Icons.Default.KeyboardArrowRight,
                null,
                tint = design.palette.textSecondary,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}


/**
 * "How to pray" at the top of the Prayers tab: a collapsible tonal card whose
 * five steps open as five more tonal cards. Tokens only (palette, spacing,
 * shapes come from the kit cards and the design system), so light and dark
 * appearances restyle together; the toggle is a full 48dp target.
 *
 * Guidance is short pastoral prose, not doctrine: preparation, the words
 * themselves, petition, thanksgiving, and silence at the end. Each step
 * carries its own ES/EN/PT text, picked by [currentLanguage].
 */
private data class HowToPrayStep(val title: String, val body: String)

@Composable
private fun HowToPraySection(
    currentLanguage: AppLanguage,
    expanded: Boolean,
    onToggle: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    val steps = listOf(
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

    Column(
        Modifier
            .fillMaxWidth()
            .testTag("how_to_pray_section")
    ) {
        CategoryTile(
            onClick = onToggle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = IveGutter)
                .heightIn(min = MinTouchTarget)
                .testTag("how_to_pray_toggle")
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = MinTouchTarget),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    // Unified card-title weight: rowTitle (regular), the
                    // same style FrostedPrayerRow uses for prayer titles
                    // such as "Padre Nuestro" — no bold/larger outlier.
                    Text(
                        text = currentLanguage.pick("Cómo orar", "How to pray", "Como orar"),
                        style = styles.rowTitle,
                        color = design.palette.textPrimary,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 3))
                    Text(
                        text = currentLanguage.pick(
                            if (expanded) "Cinco pasos · Toca para ocultar" else "Cinco pasos · Toca para ver",
                            if (expanded) "Five steps · Tap to hide" else "Five steps · Tap to see",
                            if (expanded) "Cinco passos · Toque para ocultar" else "Cinco passos · Toque para ver"
                        ),
                        style = styles.rowSubtitle,
                        color = design.palette.textSecondary
                    )
                }
                Spacer(Modifier.width(design.spacing.cardGap))
                // Chevron only, matching FrostedPrayerRow ("Padre Nuestro"):
                // one affordance for every tappable row on this list. The
                // tile itself carries the tap (button semantics, 48dp
                // target); no green text link.
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    null,
                    tint = design.palette.textSecondary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
        if (expanded) {
            steps.forEachIndexed { index, step ->
                Spacer(Modifier.height(design.spacing.cardGap))
                CategoryTile(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IveGutter)
                        .testTag("how_to_pray_step_$index")
                ) {
                    BlockHeading(eyebrow = step.title, detail = null)
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = step.body,
                        style = styles.quote,
                        color = design.palette.textPrimary,
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )
                }
            }
        }
        Spacer(Modifier.height(design.spacing.cardGap))
    }
}

/** A prayer as its own page: title, the text set to be prayed, and its notes. */
@Composable
private fun PrayerDetailView(
    prayer: VaticanPrayer,
    currentLanguage: AppLanguage,
    currentlySpeaking: String?,
    onBack: () -> Unit,
    onPlayAudio: () -> Unit,
    onStopAudio: () -> Unit
) {
    LazyColumn(Modifier.fillMaxSize().testTag("prayer_detail_${prayer.id}")) {
        item {
            InlineBackLink(
                label = currentLanguage.pick("Oraciones", "Prayers", "Orações"),
                onClick = onBack,
                testTag = "back_to_prayers_button"
            )
        }
        item {
            AppScreenTitle(title = prayer.title.get(currentLanguage))
        }
        item {
            ReadingColumn {
                VaticanPrayerCard(
                    prayer = prayer,
                    currentLanguage = currentLanguage,
                    currentlySpeaking = currentlySpeaking,
                    onPlayAudio = onPlayAudio,
                    onStopAudio = onStopAudio
                )
            }
        }
        item { Spacer(Modifier.height(LocalAppDesign.current.spacing.contentBottomGap)) }
    }
}

@Composable
private fun VaticanPrayerCard(
    prayer: VaticanPrayer,
    currentLanguage: AppLanguage,
    currentlySpeaking: String?,
    onPlayAudio: () -> Unit,
    onStopAudio: () -> Unit
) {
    val design = LocalAppDesign.current

    AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
        VersicleAndResponse(
            leaderText = prayer.leaderText.get(currentLanguage),
            responseText = prayer.responseText?.get(currentLanguage),
            spokenNow = currentlySpeaking
        )

        Spacer(Modifier.height(design.spacing.cardPadding))
        AppDivider()
        Spacer(Modifier.height(design.spacing.cardGap))

        NoteBlock(
            label = currentLanguage.pick("Cuándo rezarla", "When to pray it", "Quando rezá-la"),
            text = prayer.whenToPray.get(currentLanguage)
        )

        prayer.indulgenceNote?.let { note ->
            Spacer(Modifier.height(design.spacing.cardGap))
            NoteBlock(
                label = currentLanguage.pick("Indulgencia", "Indulgence", "Indulgência"),
                text = note.get(currentLanguage),
                textColor = design.palette.accent
            )
        }

        Spacer(Modifier.height(design.spacing.cardPadding))

        // While the voice is running the same 48dp button offers Stop, so
        // recitation can be ended without leaving the page.
        val prayerSpeaking = currentlySpeaking != null
        AppSecondaryButton(
            label = if (prayerSpeaking) {
                currentLanguage.pick("Detener", "Stop", "Parar")
            } else {
                currentLanguage.pick("Escuchar", "Listen", "Ouvir")
            },
            onClick = if (prayerSpeaking) onStopAudio else onPlayAudio,
            leadingIcon = if (prayerSpeaking) Icons.Default.Stop else Icons.Default.VolumeUp,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .testTag("play_prayer_audio_${prayer.id}")
        )
    }
}

// --------------------------------------------------------------- the chaplet

@Composable
private fun DivineMercyChapletView(
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    onBack: () -> Unit,
    onOpenNovena: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val currentlySpeaking by ttsManager.currentlySpeaking.collectAsState()

    // 0 = opening prayers, 1 = the five decades, 2 = conclusion, 3 = 3:00 PM
    var chapletStep by remember { mutableIntStateOf(1) }
    var currentDecade by remember { mutableIntStateOf(1) }
    // 0 = the lead bead (Eternal Father), 1..10 = the small ones (His Passion)
    var currentBead by remember { mutableIntStateOf(0) }


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

    val stepNames = when (currentLanguage) {
        AppLanguage.ES -> listOf("Inicio", "Decenas", "Cierre", "15:00")
        AppLanguage.EN -> listOf("Opening", "Decades", "Closing", "3:00 PM")
        AppLanguage.PT -> listOf("Início", "Dezenas", "Final", "15:00")
    }

    val beadPrayer = if (currentBead == 0) largeBeadPrayer else smallBeadPrayer
    val stepText = when (chapletStep) {
        0 -> openingPrayer
        1 -> beadPrayer
        2 -> closingPrayer
        else -> hourOfMercyText
    }
    val listenLabel = currentLanguage.pick("Escuchar", "Listen", "Ouvir")
    val heroBitmap = rememberHeroBitmap("heroes/divine_mercy.jpg")

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            InlineBackLink(
                label = currentLanguage.pick("Oraciones", "Prayers", "Orações"),
                onClick = onBack,
                testTag = "back_from_chaplet_button"
            )
        }
        item {
            AppScreenTitle(
                title = currentLanguage.pick(
                    "Coronilla de la Divina Misericordia",
                    "Chaplet of Divine Mercy",
                    "Terço da Divina Misericórdia"
                ),
                subtitle = currentLanguage.pick(
                    "Revelada a santa Faustina en Vilna, 1935",
                    "Revealed to St. Faustina in Vilnius, 1935",
                    "Revelado a santa Faustina em Vilna, 1935"
                )
            )
        }

        item {
            // Bundled Divine Mercy hero plate under the title.
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = IveGutter)
                    .height(NovenaPlateHeight)
                    .clip(design.shapes.cardLarge)
                    .background(design.palette.selectionTint)
                    .testTag("chaplet_hero")
            ) {
                if (heroBitmap != null) {
                    Image(
                        bitmap = heroBitmap,
                        contentDescription = currentLanguage.pick(
                            "Coronilla de la Divina Misericordia",
                            "Chaplet of Divine Mercy",
                            "Terço da Divina Misericórdia"
                        ),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(design.shapes.cardLarge)
                    )
                }
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        item {
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
                    Text(
                        text = currentLanguage.pick(
                            "Promesa de Nuestro Señor: «Quienquiera que la rece recibirá gran misericordia a la hora de la muerte... Defenderé como Mi propia gloria a cada alma que rece esta coronilla» (Diario, 687).",
                            "Our Lord's Promise: «Whoever will recite it will receive great mercy at the hour of death... I will defend as My own glory every soul that will say this chaplet» (Diary, 687).",
                            "Promessa de Nosso Senhor: «Quem quer que a recite receberá grande misericórdia na hora da morte... Defenderei como Minha própria glória cada alma que recitar este terço» (Diário, 687)."
                        ),
                        style = styles.quote,
                        color = design.palette.textPrimary,
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )
                    Spacer(Modifier.height(design.spacing.cardPadding))
                    // While the voice is running the same 48dp button offers Stop, so
                    // recitation can be ended without leaving the page.
                    val speaking = currentlySpeaking != null
                    AppSecondaryButton(
                        label = if (speaking) {
                            currentLanguage.pick("Detener", "Stop", "Parar")
                        } else {
                            listenLabel
                        },
                        onClick = if (speaking) {
                            { ttsManager.stop() }
                        } else {
                            { ttsManager.speakSingle(stepText) }
                        },
                        leadingIcon = if (speaking) Icons.Default.Stop else Icons.Default.VolumeUp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .testTag("chaplet_tts_button")
                    )
                }
            }
        }

        item {
            PrayersSectionHeader(
                title = currentLanguage.pick("Partes de la coronilla", "Parts of the chaplet", "Partes do terço")
            )
            AppSegmentedTabs(
                options = stepNames,
                selectedIndex = chapletStep,
                onSelect = { chapletStep = it },
                modifier = Modifier.padding(horizontal = segmentedTabsInset())
            )
        }

        when (chapletStep) {
            0 -> item {
                Spacer(Modifier.height(design.spacing.cardGap))
                ReadingColumn {
                    AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
                        BlockHeading(
                            eyebrow = currentLanguage.pick(
                                "Oraciones iniciales",
                                "Opening prayers",
                                "Orações iniciais"
                            ),
                            detail = null
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = openingPrayer,
                            style = styles.quote,
                            color = design.palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                        Spacer(Modifier.height(design.spacing.cardPadding))
                        IveBlackPillButton(
                            label = currentLanguage.pick(
                                "Comenzar la 1ª decena",
                                "Begin the 1st decade",
                                "Começar a 1ª dezena"
                            ),
                            onClick = {
                                chapletStep = 1
                                currentDecade = 1
                                currentBead = 0
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            1 -> {
                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
                        BlockHeading(
                            eyebrow = if (currentBead == 0) {
                                currentLanguage.pick(
                                    "Cuenta grande · Padre Eterno",
                                    "Lead bead · Eternal Father",
                                    "Conta grande · Eterno Pai"
                                )
                            } else {
                                currentLanguage.pick(
                                    "Cuenta pequeña · Dolorosa Pasión",
                                    "Small bead · His sorrowful Passion",
                                    "Conta pequena · Dolorosa Paixão"
                                )
                            },
                            detail = if (currentBead == 0) {
                                currentLanguage.pick(
                                    "Decena $currentDecade de $ChapletDecades",
                                    "Decade $currentDecade of $ChapletDecades",
                                    "Dezena $currentDecade de $ChapletDecades"
                                )
                            } else {
                                currentLanguage.pick(
                                    "Decena $currentDecade de $ChapletDecades · cuenta $currentBead de $ChapletBeadsPerDecade",
                                    "Decade $currentDecade of $ChapletDecades · bead $currentBead of $ChapletBeadsPerDecade",
                                    "Dezena $currentDecade de $ChapletDecades · conta $currentBead de $ChapletBeadsPerDecade"
                                )
                            }
                        )

                        Spacer(Modifier.height(design.spacing.cardGap))

                        DevotionRail(
                            count = ChapletBeadsPerDecade + 1,
                            activeIndex = currentBead,
                            isTold = { it < currentBead },
                            hasLeadBead = true,
                            onSelect = { currentBead = it }
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))

                        Text(
                            text = beadPrayer,
                            style = styles.quote,
                            color = design.palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))

                        AppSecondaryButton(
                            label = listenLabel,
                            onClick = { ttsManager.speakSingle(beadPrayer) },
                            leadingIcon = Icons.Default.VolumeUp,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(design.spacing.cardGap / 2))

                        val atLastBead = currentBead == ChapletBeadsPerDecade &&
                            currentDecade == ChapletDecades
                        val canGoBack = currentBead > 0 || currentDecade > 1

                        // Two abreast at most, and only words short enough to
                        // survive Roman capitals at 360dp in all three languages.
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
                        ) {
                            if (canGoBack) {
                                AppSecondaryButton(
                                    label = currentLanguage.pick("Atrás", "Back", "Voltar"),
                                    onClick = {
                                        if (currentBead > 0) {
                                            currentBead--
                                        } else {
                                            currentDecade--
                                            currentBead = ChapletBeadsPerDecade
                                        }
                                    },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            if (!atLastBead) {
                                IveBlackPillButton(
                                    label = currentLanguage.pick("Avanzar", "Next", "Avançar"),
                                    onClick = {
                                        if (currentBead < ChapletBeadsPerDecade) {
                                            currentBead++
                                        } else {
                                            currentDecade++
                                            currentBead = 0
                                        }
                                    },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        if (atLastBead) {
                            Spacer(Modifier.height(design.spacing.cardGap / 2))
                            IveBlackPillButton(
                                label = currentLanguage.pick(
                                    "Ir a la conclusión",
                                    "Go to the conclusion",
                                    "Ir à conclusão"
                                ),
                                onClick = { chapletStep = 2 },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            2 -> item {
                Spacer(Modifier.height(design.spacing.cardGap))
                ReadingColumn {
                    AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
                        BlockHeading(
                            eyebrow = currentLanguage.pick(
                                "Doxología trinitaria y conclusión",
                                "Trinitarian doxology and conclusion",
                                "Doxologia trinitária e conclusão"
                            ),
                            detail = null
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = closingPrayer,
                            style = styles.quote,
                            color = design.palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                        Spacer(Modifier.height(design.spacing.cardPadding))
                        IveBlackPillButton(
                            label = currentLanguage.pick(
                                "Rezar la novena de 9 días",
                                "Pray the 9-day novena",
                                "Rezar a novena de 9 dias"
                            ),
                            onClick = onOpenNovena,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            3 -> {
                item {
                    Spacer(Modifier.height(design.spacing.sectionGap / 2))
                    AppBanner(
                        title = currentLanguage.pick("15:00", "3:00 PM", "15:00"),
                        body = currentLanguage.pick(
                            "La Hora de la Misericordia",
                            "The Hour of Mercy",
                            "A Hora da Misericórdia"
                        )
                    )
                }
                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    ReadingColumn {
                        AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
                            Text(
                                text = hourOfMercyText,
                                style = styles.quote,
                                color = design.palette.textPrimary,
                                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                            )
                            Spacer(Modifier.height(design.spacing.cardPadding))
                            AppSecondaryButton(
                                label = listenLabel,
                                onClick = { ttsManager.speakSingle(hourOfMercyText) },
                                leadingIcon = Icons.Default.VolumeUp,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// ----------------------------------------------------------- novena index

@Composable
private fun NovenaIndexView(
    header: LazyListScope.() -> Unit,
    novenas: List<Novena>,
    currentLanguage: AppLanguage,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedTag: String?,
    onSelectTag: (String?) -> Unit,
    daysPrayed: Map<String, Set<Int>>,
    startedOn: Map<String, Long>,
    onOpenNovena: (Novena) -> Unit
) {
    val design = LocalAppDesign.current

    // Drawn from the whole catalogue, not the filtered index: what someone is
    // praying should not disappear because a search is still typed in.
    val begun = remember(novenas.size, daysPrayed) {
        novenas.filter { daysPrayed[it.id].orEmpty().isNotEmpty() }
    }
    val inProgress = begun
        .filterNot { isNovenaFinished(it, daysPrayed[it.id].orEmpty()) }
        .sortedByDescending { startedOn[it.id] ?: 0L }
    val finished = begun
        .filter { isNovenaFinished(it, daysPrayed[it.id].orEmpty()) }
        .sortedByDescending { startedOn[it.id] ?: 0L }
    val dateFormat = remember(currentLanguage) {
        DateFormat.getDateInstance(DateFormat.MEDIUM, Locale(currentLanguage.code))
    }

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

    // Keyed on the size, not on the list: the catalogue is a singleton, and
    // comparing 246 novenas by value costs more than the work being cached.
    val availableTags = remember(novenas.size) {
        listOf("impossible", "blessings", "love", "money", "comfort", "health", "family", "peace")
            .filter { tag -> novenas.any { it.tags.contains(tag) } }
    }

    // 246 novenas, each with nine days of prayer and meditation to scan: far
    // too much to redo on every recomposition of the list.
    val filtered = remember(novenas.size, searchQuery, selectedTag, currentLanguage) {
        novenas.filter { novena ->
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
            matchesSearch && matchesTag
        }
    }

    val blockGap = if (design.decor.listStyle == ListStyle.INSET_GROUPED) {
        design.spacing.insetGroupGap
    } else {
        design.spacing.listItemGap
    }

    // Bottom padding clears the floating nav bar so the last row of cards
    // is never clipped under it.
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 0.dp, bottom = 16.dp)
    ) {
        header()

        item {
            AppSearchField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = currentLanguage.pick(
                    "Buscar novena...",
                    "Search novena...",
                    "Buscar novena..."
                ),
                modifier = Modifier
                    .padding(horizontal = IveGutter)
                    .testTag("novena_search_input")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            PrayerIntentionFilters(
                options = listOf<Pair<String?, String>>(null to currentLanguage.pick("Todas las intenciones", "All intentions", "Todas as intenções")) +
                    availableTags.map { it to (tagLabels[it] ?: it) },
                selected = selectedTag,
                onSelect = onSelectTag,
                testTag = "novena_intention_picker"
            )
        }

        if (inProgress.isNotEmpty()) {
            item {
                PrayersSectionHeader(
                    title = currentLanguage.pick("En curso", "In progress", "Em andamento")
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IveGutter)
                        .testTag("novenas_in_progress"),
                    verticalArrangement = Arrangement.spacedBy(CardStackGap)
                ) {
                    inProgress.forEach { novena ->
                        val prayed = daysPrayed[novena.id].orEmpty()
                        val started = startedOn[novena.id]?.let { dateFormat.format(Date(it)) }
                        NovenaCard(
                            novena = novena,
                            currentLanguage = currentLanguage,
                            prayed = prayed,
                            extraLine = started?.let {
                                currentLanguage.pick(
                                    "Comenzada el $it",
                                    "Started $it",
                                    "Iniciada em $it"
                                )
                            },
                            onOpen = { onOpenNovena(novena) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        if (finished.isNotEmpty()) {
            item {
                PrayersSectionHeader(
                    title = currentLanguage.pick("Completadas", "Completed", "Concluídas")
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IveGutter)
                        .testTag("novenas_completed"),
                    verticalArrangement = Arrangement.spacedBy(CardStackGap)
                ) {
                    finished.forEach { novena ->
                        NovenaCard(
                            novena = novena,
                            currentLanguage = currentLanguage,
                            prayed = daysPrayed[novena.id].orEmpty(),
                            extraLine = null,
                            onOpen = { onOpenNovena(novena) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        if (filtered.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Ninguna novena coincide", "No matching novenas", "Nenhuma novena corresponde"),
                    body = currentLanguage.pick(
                        "Prueba otra palabra o elige otra intención.",
                        "Try another word or choose another intention.",
                        "Tente outra palavra ou escolha outra intenção."
                    ),
                    icon = Icons.Default.SearchOff
                )
                AppSecondaryButton(
                    label = currentLanguage.pick("Ver todas", "Show all novenas", "Ver todas"),
                    onClick = {
                        onSearchQueryChange("")
                        onSelectTag(null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IveGutter)
                )
            }
        } else {
            item {
                // The count is said only when a search has narrowed the index.
                PrayersSectionHeader(
                    title = currentLanguage.pick("Todas las novenas", "All novenas", "Todas as novenas"),
                    subtitle = if (filtered.size < novenas.size) currentLanguage.pick(
                        "${filtered.size} de ${novenas.size}",
                        "${filtered.size} of ${novenas.size}",
                        "${filtered.size} de ${novenas.size}"
                    ) else null
                )
            }

            // Full-bleed photo cards, one per row, lazy like the 246-row
            // index they replace: each novena is one lazy item, so
            // composition cost stays flat however long the filtered index
            // runs.
            items(
                items = filtered,
                key = { it.id }
            ) { novena ->
                NovenaCard(
                    novena = novena,
                    currentLanguage = currentLanguage,
                    prayed = daysPrayed[novena.id].orEmpty(),
                    extraLine = null,
                    onOpen = { onOpenNovena(novena) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IveGutter)
                )
                Spacer(Modifier.height(blockGap))
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// ------------------------------------------------------------- novena page

/**
 * One novena in the index: a full-bleed photo card (screen width minus
 * the 20dp gutters), 20dp radius. The photograph fills the card, a bottom
 * scrim darkens its foot, and title, patron and progress sit overlaid on
 * that scrim in white so they read on any photograph. The progress rule
 * is pinned to the card's foot. Colours come only from the palette
 * tokens, so the card is tonal in both appearances without this screen
 * knowing which one is in force.
 */
@Composable
private fun NovenaCard(
    novena: Novena,
    currentLanguage: AppLanguage,
    prayed: Set<Int>,
    extraLine: String?,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    val total = novena.days.size
    val fraction = if (total > 0) prayed.size.toFloat() / total.toFloat() else 0f
    val progressLabel = when {
        prayed.isEmpty() -> currentLanguage.pick(
            "$total días",
            "$total days",
            "$total dias"
        )
        isNovenaFinished(novena, prayed) -> novenaFinishedLabel(currentLanguage)
        else -> novenaDayOf(currentLanguage, nextNovenaDay(novena, prayed), total)
    }

    // Full-bleed photo, 20dp radius, bottom scrim with overlaid title.
    val heroBitmap = rememberHeroBitmap(novenaHeroAsset(novena))
    Box(
        modifier
            .height(NovenaCardHeight)
            .clip(NovenaCardRadius)
            .background(palette.selectionTint)
            .clickable(onClick = onOpen)
            .testTag("novena_card_${novena.id}")
    ) {
        if (heroBitmap != null) {
            Image(
                bitmap = heroBitmap,
                contentDescription = novena.title.get(currentLanguage),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(NovenaCardRadius)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Church,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.85f),
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center)
            )
        }
        // Bottom scrim, full bleed: transparent at the top so the
        // photograph shows, darkening through the overlaid text block so
        // white title/patron/progress stay legible on any image.
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(NovenaCardHeight)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.45f),
                            Color.Black.copy(alpha = 0.82f)
                        )
                    )
                )
        )
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                text = novena.title.get(currentLanguage),
                style = styles.rowTitle,
                color = Color.White,
                minLines = 1,
                maxLines = 2,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = listOfNotNull(
                    novena.patron.get(currentLanguage),
                    extraLine
                ).joinToString(" · "),
                style = styles.rowSubtitle,
                color = Color.White.copy(alpha = 0.88f),
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = progressLabel,
                style = styles.rowSubtitle,
                color = Color.White,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { fraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .semantics { stateDescription = progressLabel },
                color = Color.White,
                trackColor = Color.White.copy(alpha = 0.35f)
            )
        }
    }
}

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
    onMarkDayPrayed: () -> Unit,
    onResetNovena: () -> Unit = {},
    onBack: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    val dayData = novena.days.firstOrNull { it.dayNumber == activeDay } ?: novena.days.first()
    val prayerText = dayData.prayer.get(currentLanguage)
    // The source data stores each day's prayer in both fields, so the
    // meditation is shown only when it says something the prayer does not —
    // otherwise every day displayed its prayer twice.
    val meditation = dayData.meditation.get(currentLanguage)
        .takeUnless { it.trim() == prayerText.trim() }
        .orEmpty()
    val guidance = dayData.intentionGuidance.get(currentLanguage)
    val description = novena.description.get(currentLanguage)

    val heroBitmap = rememberHeroBitmap(novenaHeroAsset(novena))

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = IveGutter)
                    .padding(top = design.spacing.cardGap),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .heightIn(min = MinTouchTarget)
                        .clickable(onClick = onBack)
                        .padding(vertical = design.spacing.rowPaddingVertical)
                        .testTag("back_to_novenas_button"),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = design.palette.accent,
                        modifier = Modifier.size(design.decor.rowIconSize)
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap / 2))
                    Text(
                        text = caps(
                            currentLanguage.pick(
                                "Lista de Novenas",
                                "All Novenas",
                                "Todas as Novenas"
                            )
                        ),
                        style = styles.buttonLabel,
                        color = design.palette.accent,
                        maxLines = 1
                    )
                }
                Spacer(Modifier.width(design.spacing.cardGap))
                Text(
                    text = caps(novena.category.get(currentLanguage)),
                    style = styles.sectionEyebrow,
                    color = design.palette.gold,
                    maxLines = 1
                )
            }
        }

        item {
            AppScreenTitle(
                title = novena.title.get(currentLanguage),
                subtitle = novena.patron.get(currentLanguage)
            )
        }

        // Frontispiece plate: the novena's own illustration (AsyncImage over
        // imageUrl) covers the bundled fallback photo, so only a novena with
        // a blank/null imageUrl keeps the bundled plate. The bundled ground
        // also shows through if the remote image has not loaded yet.
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = IveGutter)
                    .height(NovenaPlateHeight)
                    .clip(design.shapes.cardLarge)
                    .background(design.palette.selectionTint)
            ) {
                if (heroBitmap != null) {
                    Image(
                        bitmap = heroBitmap,
                        contentDescription = novena.title.get(currentLanguage),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(design.shapes.cardLarge)
                    )
                }
                if (!novena.imageUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = novena.imageUrl,
                        contentDescription = novena.title.get(currentLanguage),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(design.shapes.cardLarge)
                    )
                }
            }
            Spacer(Modifier.height(design.spacing.sectionGap / 2))
        }

        if (description.isNotBlank()) {
            item {
                ReadingColumn {
                    Text(
                        text = description,
                        style = styles.quote,
                        color = design.palette.textSecondary,
                        modifier = Modifier
                            .padding(horizontal = IveGutter)
                            .widthIn(max = design.spacing.readingMaxWidth)
                    )
                }
                Spacer(Modifier.height(design.spacing.sectionGap / 2))
            }
        }

        item {
            AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
                BlockHeading(
                    eyebrow = currentLanguage.pick(
                        "Progreso: día $activeDay de ${novena.days.size}",
                        "Progress: day $activeDay of ${novena.days.size}",
                        "Progresso: dia $activeDay de ${novena.days.size}"
                    ),
                    detail = currentLanguage.pick(
                        "${daysPrayed.size} rezados",
                        "${daysPrayed.size} prayed",
                        "${daysPrayed.size} rezados"
                    )
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                DevotionRail(
                    count = novena.days.size,
                    activeIndex = activeDay - 1,
                    isTold = { daysPrayed.contains(it + 1) },
                    label = { "${it + 1}" },
                    onSelect = { onSelectDay(it + 1) }
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                // The rail shows which days; the rule gives the fraction at a
                // glance, in the identity's accent on its selection tint —
                // the same pair the index cards use.
                val progressFraction = if (novena.days.isEmpty()) {
                    0f
                } else {
                    daysPrayed.size.toFloat() / novena.days.size.toFloat()
                }
                val progressText = currentLanguage.pick(
                    "${daysPrayed.size} de ${novena.days.size} días completados",
                    "${daysPrayed.size} of ${novena.days.size} days completed",
                    "${daysPrayed.size} de ${novena.days.size} dias concluídos"
                )
                LinearProgressIndicator(
                    progress = { progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .semantics { stateDescription = progressText },
                    color = design.palette.accent,
                    trackColor = design.palette.selectionTint
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                TextButton(
                    onClick = onResetNovena,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = MinTouchTarget)
                        .testTag("restart_novena_button")
                ) {
                    Text(
                        text = currentLanguage.pick(
                            "Reiniciar novena",
                            "Restart novena",
                            "Reiniciar novena"
                        ),
                        style = styles.buttonLabel
                    )
                }
            }
        }

        item {
            Spacer(Modifier.height(design.spacing.cardGap))
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
                    BlockHeading(
                        eyebrow = currentLanguage.pick(
                            "Día $activeDay",
                            "Day $activeDay",
                            "Dia $activeDay"
                        ),
                        detail = dayData.scripture.ifBlank { null }
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = dayData.theme.get(currentLanguage),
                        style = styles.sectionTitle,
                        color = design.palette.textPrimary
                    )

                    if (meditation.isNotBlank()) {
                        Spacer(Modifier.height(design.spacing.cardGap))
                        // The meditation is read, not chrome: it takes the
                        // reading face at the reading size, inside the same
                        // clamped measure as the day's prayer below.
                        Text(
                            text = meditation,
                            style = styles.scripture,
                            color = design.palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                    }

                    if (guidance.isNotBlank()) {
                        Spacer(Modifier.height(design.spacing.cardPadding))
                        AppDivider()
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Row(Modifier.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = design.palette.textSecondary,
                                modifier = Modifier.size(design.decor.rowIconSize)
                            )
                            Spacer(Modifier.width(design.spacing.iconLabelGap))
                            NoteBlock(
                                label = currentLanguage.pick(
                                    "Tu intención",
                                    "Your intention",
                                    "Sua intenção"
                                ),
                                text = guidance,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(Modifier.height(design.spacing.cardGap))
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
                    BlockHeading(
                        eyebrow = currentLanguage.pick(
                            "Oración del Día $activeDay",
                            "Prayer for Day $activeDay",
                            "Oração do Dia $activeDay"
                        ),
                        detail = null
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                    Text(
                        text = prayerText,
                        style = styles.scripture,
                        color = if (currentlySpeaking == prayerText) {
                            design.palette.accent
                        } else {
                            design.palette.textPrimary
                        },
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )

                    Spacer(Modifier.height(design.spacing.cardPadding))

                    // Stacked rather than set two-across: "Completar día" in
                    // tracked Roman capitals needs the full measure at 360dp.
                    // While this day's prayer is being read the button offers
                    // Stop instead of Listen, on the same 48dp target.
                    val daySpeaking = currentlySpeaking == prayerText
                    AppSecondaryButton(
                        label = if (daySpeaking) {
                            currentLanguage.pick("Detener", "Stop", "Parar")
                        } else {
                            currentLanguage.pick("Escuchar", "Listen", "Ouvir")
                        },
                        onClick = { if (daySpeaking) onStop() else onListen(prayerText) },
                        leadingIcon = if (daySpeaking) Icons.Default.Stop else Icons.Default.VolumeUp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .testTag("novena_listen_button")
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    IveBlackPillButton(
                        label = if (daysPrayed.contains(activeDay)) {
                            currentLanguage.pick("Deshacer día", "Undo day", "Desfazer dia")
                        } else {
                            currentLanguage.pick("Completar día", "Complete day", "Completar dia")
                        },
                        onClick = onMarkDayPrayed,
                        leadingIcon = Icons.Default.Check,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("complete_novena_day_button")
                    )
                }
            }
        }

        item { AppOrnament() }

        item {
            PrayersSectionHeader(
                title = currentLanguage.pick("Los nueve días", "The nine days", "Os nove dias")
            )
            AppListGroup {
                novena.days.forEach { day ->
                    val prayed = daysPrayed.contains(day.dayNumber)
                    AppListRow(
                        title = day.theme.get(currentLanguage),
                        subtitle = day.scripture.ifBlank { null },
                        trailingText = if (prayed) "✓ ${day.dayNumber}" else "${day.dayNumber}",
                        showsNavigationAffordance = false,
                        selected = day.dayNumber == activeDay,
                        onClick = { onSelectDay(day.dayNumber) }
                    )
                }
            }
        }

        val promises = novena.promises.get(currentLanguage)
        val history = novena.miraculousHistory.get(currentLanguage)
        if (promises.isNotBlank() || history.isNotBlank()) {
            item {
                PrayersSectionHeader(
                    title = currentLanguage.pick(
                        "Promesas y milagros registrados",
                        "Recorded promises and miracles",
                        "Promessas e milagres registrados"
                    )
                )
                ReadingColumn {
                    AppCard(modifier = Modifier.padding(horizontal = IveGutter)) {
                        if (promises.isNotBlank()) {
                            Text(
                                text = promises,
                                style = styles.quote,
                                color = design.palette.textPrimary,
                                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                            )
                        }
                        if (promises.isNotBlank() && history.isNotBlank()) {
                            Spacer(Modifier.height(design.spacing.cardPadding))
                            AppDivider()
                            Spacer(Modifier.height(design.spacing.cardGap))
                        }
                        if (history.isNotBlank()) {
                            Text(
                                text = history,
                                style = styles.quote,
                                color = design.palette.textSecondary,
                                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// ------------------------------------------------------------------ pieces

/**
 * A run of beads or of days: the nine days of a novena, or the lead bead and
 * ten small ones of a decade.
 *
 * No kit component can express a decade, so this is drawn here — but from
 * tokens, so the bead is a filled circle on glass and an unfilled ring of leaf
 * on paper, and nothing has to know which identity is in force.
 */
@Composable
private fun DevotionRail(
    count: Int,
    activeIndex: Int,
    isTold: (Int) -> Boolean,
    onSelect: (Int) -> Unit,
    label: ((Int) -> String)? = null,
    hasLeadBead: Boolean = false,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    val engraved = design.depth.cardBorderWidth > 0.dp
    val ringWidth = maxOf(
        design.depth.cardBorderWidth,
        if (design.depth.usesHairlines) BeadRingHairline else 0.dp
    )

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val gap = design.spacing.cardGap / 3
        val slots = count + (if (hasLeadBead) ChapletLeadBeadScale - 1f else 0f)
        val bead = ((maxWidth - gap * (count - 1)) / slots)
            .coerceIn(0.dp, design.spacing.rowMinHeight / 2)

        Row(
            Modifier
                .fillMaxWidth()
                // The rail is a control, so its row is a full 48dp target
                // tall even though the bead glyphs are smaller.
                .heightIn(min = MinTouchTarget),
            horizontalArrangement = Arrangement.spacedBy(gap, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(count) { index ->
                val isActive = index == activeIndex
                val told = isTold(index)
                val fill by animateColorAsState(
                    targetValue = when {
                        isActive -> palette.accent
                        engraved -> Color.Transparent
                        told -> palette.accent.copy(alpha = 0.32f)
                        else -> palette.selectionTint
                    },
                    animationSpec = tween(
                        durationMillis = design.motion.fastMillis,
                        easing = design.motion.standardEasing
                    ),
                    label = "beadFill"
                )
                val ring = when {
                    engraved && (isActive || told) -> palette.gold
                    engraved -> palette.gold.copy(alpha = 0.45f)
                    design.depth.usesHairlines && !isActive -> palette.hairline
                    else -> Color.Transparent
                }
                Box(
                    Modifier
                        .size(if (hasLeadBead && index == 0) bead * ChapletLeadBeadScale else bead)
                        .clip(CircleShape)
                        .background(fill)
                        .border(ringWidth, ring, CircleShape)
                        .clickable { onSelect(index) },
                    contentAlignment = Alignment.Center
                ) {
                    if (label != null) {
                        Text(
                            text = label(index),
                            style = styles.verseNumber,
                            color = if (isActive) palette.raised else palette.textSecondary,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

/** Leader and response, with whichever line the voice is on set in rubric. */
@Composable
private fun VersicleAndResponse(
    leaderText: String,
    responseText: String?,
    spokenNow: String?
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val rubric = rubricSigil()

    // Some prayers (the Angelus) already carry their own sigla line by line;
    // those are set as they stand rather than given a second sign.
    fun signed(sign: String, text: String) =
        if (SiglaPattern.containsMatchIn(text)) withLiturgicalSigla(text, rubric)
        else buildAnnotatedString {
            withStyle(rubric) { append(sign) }
            append("\u2002")
            append(text)
        }

    Column(Modifier.widthIn(max = design.spacing.readingMaxWidth)) {
        Text(
            text = signed(VersicleSign, leaderText),
            style = styles.quote,
            color = if (spokenNow == leaderText) design.palette.accent else design.palette.textPrimary
        )
        if (responseText != null) {
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = signed(ResponseSign, responseText),
                style = styles.quote,
                color = if (spokenNow == responseText) design.palette.accent else design.palette.textSecondary
            )
        }
    }
}

/** ℣ — the versicle, as the liturgical books print it. */
internal const val VersicleSign = "\u2123"

/** ℟ — the response. */
internal const val ResponseSign = "\u211F"

/** The typed-out sigla ("V/." and "R/.") the bundled texts carry. */
internal val SiglaPattern = Regex("""(?<!\p{L})([VR])/\.""")

/** The sigla's rubric: the identity's accent, which is Vatican's missal red. */
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

/** An eyebrow over a card, with its reference or count on the line below. */
@Composable
private fun BlockHeading(eyebrow: String, detail: String?) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column {
        Text(
            text = caps(eyebrow),
            style = styles.sectionEyebrow,
            color = design.palette.textSecondary
        )
        if (detail != null) {
            Spacer(Modifier.height(design.spacing.cardGap / 3))
            Text(
                text = detail,
                style = styles.rowSubtitle,
                color = design.palette.textSecondary
            )
        }
    }
}

/** A small label with its line of apparatus under it. */
@Composable
private fun NoteBlock(
    label: String,
    text: String,
    textColor: Color? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(modifier.fillMaxWidth()) {
        Text(
            text = caps(label),
            style = styles.sectionEyebrow,
            color = design.palette.textSecondary
        )
        Spacer(Modifier.height(design.spacing.cardGap / 3))
        Text(
            text = text,
            style = styles.rowSubtitle,
            color = textColor ?: design.palette.textSecondary
        )
    }
}

/**
 * Holds long-form text to the identity's reading measure and centres it, so a
 * wide screen reads as the column of a book instead of a wall of type. The two
 * gutters are added back because the card inside carries them itself.
 */
@Composable
private fun ReadingColumn(content: @Composable ColumnScope.() -> Unit) {
    val design = LocalAppDesign.current
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(
            Modifier
                .widthIn(max = design.spacing.readingMaxWidth + design.spacing.gutter * 2)
                .fillMaxWidth(),
            content = content
        )
    }
}

/**
 * No kit insets its own segmented control, so the gutter is the caller's to
 * give. Vatican is the exception: its strip is a ruled band whose hairline runs
 * the full width of the leaf, and inset it stops meeting the edges.
 */
@Composable
private fun segmentedTabsInset(): Dp {
    val design = LocalAppDesign.current
    return design.spacing.gutter
}

/** Vatican sets its labels as inscriptional capitals; the other two do not. */
@Composable
private fun caps(text: String): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) text.uppercase() else text

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

// ------------------------------------------------------ bundled hero photos

/**
 * Decodes a bundled `assets/heroes/` photo once per [assetPath], sampled
 * down to at most [HeroMaxDimension]px on either side so a 246-card index
 * never holds full-resolution bitmaps. Returns null when the asset is
 * missing or unreadable; callers keep their tonal/placeholder ground.
 */
@Composable
private fun rememberHeroBitmap(assetPath: String): ImageBitmap? {
    val context = LocalContext.current
    return remember(assetPath) {
        try {
            val bytes = context.assets.open(assetPath).use { it.readBytes() }
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size, bounds)
            var sample = 1
            while (
                bounds.outWidth / sample > HeroMaxDimension ||
                bounds.outHeight / sample > HeroMaxDimension
            ) {
                sample *= 2
            }
            val options = BitmapFactory.Options().apply { inSampleSize = sample }
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)?.asImageBitmap()
        } catch (_: Exception) {
            null
        }
    }
}

/**
 * Bundled fallback photo for a novena: shown only when the novena has no
 * shipped photo URL (blank/null `imageUrl`), or while its own illustration
 * is still loading. The Divine Mercy novena (id or any-language title
 * containing "misericord") gets the Divine Mercy image; a novena without a
 * shipped photo URL falls back to the Marian statue default; every other
 * novena uses the votive candles. The novena's own `imageUrl` is fetched
 * with AsyncImage on top of this ground.
 */
private fun novenaHeroAsset(novena: Novena): String {
    val names = listOf(novena.id, novena.title.es, novena.title.en, novena.title.pt)
    return when {
        names.any { it.contains("misericord", ignoreCase = true) } -> "heroes/divine_mercy.jpg"
        novena.imageUrl.isNullOrBlank() -> "heroes/mary_statue.jpg"
        else -> "heroes/votive_candles.jpg"
    }
}

/**
 * Prayers header: a full-bleed votive-candle photo band that fades into
 * the canvas at its foot (64dp fade, Expert v3 — a longer fade ghosted
 * extra flames behind the title), with the screen title overlaid on the
 * fade so the photo and the word share one block. Replaces the vector
 * header art. The crop is centred on the centre candle flame.
 */
@Composable
private fun PrayersPhotoHeader(title: String) {
    val palette = LocalAppDesign.current.palette
    val bitmap = rememberHeroBitmap("heroes/votive_candles.jpg")
    Box(
        Modifier
            .fillMaxWidth()
            .height(PrayersPhotoBandHeight)
            .background(palette.selectionTint)
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                // Centre-aligned crop: the full candle row stays centred
                // in frame instead of a candle being cut mid-shape at the
                // right edge.
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("screen_header_illustration")
            )
        }
        // Light scrim behind the title zone: transparent over the photo
        // above, then paper canvas at 0.92 across the whole title zone so
        // the black title stays legible over busy candle flames without
        // the mid-toned step that ghosted extra flames behind it.
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(64.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            palette.canvas.copy(alpha = 0.92f),
                            palette.canvas
                        )
                    )
                )
        )
        AppScreenTitle(
            title = title,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

// ------------------------------------------------------- novena progress

/*
 * `NovenaProgressEntity.currentDay` is stored as a bitmask of the days prayed:
 * bit (n - 1) set means day n is done. A single "current day" cannot say which
 * days are done once someone prays them out of order, which the rail allows,
 * and a mask holds that in the existing column with no schema change. Nothing
 * else reads the column; the entity's own "1 to 9" note predates this.
 */
private fun encodeNovenaDays(days: Set<Int>): Int =
    days.filter { it in 1..31 }.fold(0) { mask, day -> mask or (1 shl (day - 1)) }

private fun decodeNovenaDays(mask: Int): Set<Int> =
    (1..31).filterTo(mutableSetOf()) { mask and (1 shl (it - 1)) != 0 }

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

private fun novenaFinishedLabel(language: AppLanguage): String = language.pick(
    "✓ Completada",
    "✓ Completed",
    "✓ Concluída"
)
