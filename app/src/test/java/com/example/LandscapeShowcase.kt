package com.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.VaticanPrayer
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.PrayerAndNovenaData
import com.example.ui.AppDestination
import com.example.ui.design.*
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/** Test-only migration examples. No destinations or production screens are added. */
internal enum class LandscapeShowcase { Hub, Reader, Supporting, ListDetail }

internal fun AppLanguage.ui(es: String, en: String, pt: String) = when (this) {
    AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt
}

internal val showcasePrayers: List<VaticanPrayer> = listOf("our_father", "hail_mary", "glory_be", "angelus").map { id ->
    PrayerAndNovenaData.vaticanOfficialPrayers.first { it.id == id }
}
internal fun VaticanPrayer.body(language: AppLanguage) = listOfNotNull(leaderText.get(language), responseText?.get(language)).joinToString("\n\n")

@Composable
internal fun LandscapeShowcaseScreen(showcase: LandscapeShowcase, language: AppLanguage = AppLanguage.ES) {
    val context = LocalContext.current
    val store = remember { UserPreferencesStore(context) }
    LaunchedEffect(language) { store.setLanguage(language) }
    val frame = remember(showcase) { ScreenFrame() }
    var exited by rememberSaveable(key = "showcase_exited") { mutableIntStateOf(0) }
    androidx.activity.compose.BackHandler { exited++ }
    val large = appLargeText()
    val nav = AppDestination.tabs.map { dest ->
        val (icon, selected) = AppIcons.pair(dest.iconSlot)
        AppNavItem(if (large && dest == AppDestination.MY_PATH) language.ui("Ruta", "Path", "Rota")
            else if (large && dest == AppDestination.MORE) language.ui("Más", "More", "Mais") else dest.tabLabel(language), icon, selected, dest.tag)
    }
    CompositionLocalProvider(LocalPreferencesStore provides store, LocalInspectionMode provides true) {
        AppScaffold(language.ui("Orar", "Pray", "Orar"), nav, 1, {},
            onBack = if (showcase == LandscapeShowcase.Hub) null else ({ exited++ }), backLabel = language.ui("Orar", "Pray", "Orar")) { padding ->
            if (frame.insets != padding) frame.insets = padding
            CompositionLocalProvider(LocalScreenFrame provides frame) {
                Box(Modifier.fillMaxSize().testTag("showcase_${showcase.name.lowercase()}")) {
                    if (exited > 0) AppLargeTitleHeader(language.ui("Orar", "Pray", "Orar"), titleTag = "showcase_exited")
                    else when (showcase) {
                        LandscapeShowcase.Hub -> ShowcaseHub(language)
                        LandscapeShowcase.Reader -> ShowcaseReader(language, onExit = { exited++ })
                        LandscapeShowcase.Supporting -> ShowcaseSupporting(language)
                        LandscapeShowcase.ListDetail -> ShowcaseListDetail(language)
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowcaseHub(language: AppLanguage) {
    var selected by rememberSaveable(key = "showcase_segment") { mutableIntStateOf(0) }
    var query by rememberSaveable(key = "showcase_query") { mutableStateOf("") }
    var opened by rememberSaveable(key = "showcase_hub_opened") { mutableStateOf<String?>(null) }
    val styles = LocalAppTextStyles.current
    val palette = LocalAppDesign.current.palette
    val pool = when (selected) {
        1 -> showcasePrayers.filter { it.id != "angelus" }
        2 -> showcasePrayers.filter { it.id == "hail_mary" || it.id == "angelus" }
        else -> showcasePrayers
    }
    val prayers = pool.filter { it.title.get(language).contains(query, ignoreCase = true) }
    AppPageLayout(header = {
        AppHeroHeader("heroes/lumen/prayers_lily.jpg", language.ui("Oraciones", "Prayers", "Orações"),
            eyebrow = language.ui("Ora sin cesar", "Pray without ceasing", "Orai sem cessar"), titleTag = "showcase_title")
    }, supporting = {
        AppSegmentedTabs(listOf(language.ui("Todas", "All", "Todas"), language.ui("Básicas", "Basic", "Básicas"), language.ui("Marianas", "Marian", "Marianas")),
            selected, { selected = it }, Modifier.testTag("showcase_segments"))
        Spacer(Modifier.height(8.dp))
        AppSearchField(query, { query = it }, language.ui("Buscar", "Search", "Buscar"), Modifier.testTag("showcase_search"))
    }, contentFooter = {
        AppPrimaryButton(language.ui("Rezar", "Pray", "Rezar"), { opened = prayers.firstOrNull()?.id }, enabled = prayers.isNotEmpty(), modifier = Modifier.width(180.dp).testTag("showcase_primary"))
    }) { headerItem ->
        LazyColumn(Modifier.fillMaxSize().testTag("showcase_hub_list"), contentPadding = PaddingValues(bottom = 24.dp)) {
            item(key = "header") { headerItem() }
            item(key = "feed") {
                AppFeedColumns(lanes = listOf(prayers.filterIndexed { index, _ -> index % 2 == 0 }, prayers.filterIndexed { index, _ -> index % 2 == 1 }),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) { prayer ->
                    AppCard(onClick = { opened = prayer.id }) {
                        Text(prayer.title.get(language), style = styles.title2, color = palette.textPrimary)
                        Spacer(Modifier.height(8.dp))
                        Text(prayer.whenToPray.get(language), style = styles.subhead, color = palette.textSecondary)
                    }
                }
            }
            if (prayers.isEmpty()) item { AppEmptyState(language.ui("Sin resultados", "No results", "Nenhum resultado")) }
        }
    }
    val chosen = showcasePrayers.firstOrNull { it.id == opened }
    if (chosen != null) AppSheet({ opened = null }, title = chosen.title.get(language), tag = "showcase_prayer_sheet") {
        Text(chosen.body(language), style = styles.reading, color = palette.textPrimary, modifier = Modifier.padding(horizontal = 20.dp))
    }
}

@Composable
private fun ShowcaseReader(language: AppLanguage, initialId: String? = null, onExit: () -> Unit) {
    var selected by rememberSaveable(key = "showcase_reader_selection") { mutableIntStateOf(showcasePrayers.indexOfFirst { it.id == initialId }.coerceAtLeast(0)) }
    val prayer = showcasePrayers[selected]
    val styles = LocalAppTextStyles.current
    val palette = LocalAppDesign.current.palette
    val measure = appReadingMeasure(language)
    val anchor = rememberReadingAnchor("showcase_reader_anchor")
    AppHideTabBar()
    AppPageLayout(header = {
        AppDetailHeader(prayer.title.get(language), assetPath = "heroes/lumen/prayers_lily.jpg", meta = prayer.latinTitle, titleTag = "showcase_title")
    }, kind = AppPageKind.Reader, panelFooter = {
        AppSecondaryButton(language.ui("Volver a Orar", "Back to Pray", "Voltar a Orar"), onExit, modifier = Modifier.fillMaxWidth().testTag("showcase_reader_home"))
    }, contentFooter = {
        AppSecondaryButton(language.ui("Anterior", "Previous", "Anterior"), { selected = (selected - 1).coerceAtLeast(0) }, selected > 0,
            modifier = Modifier.width(120.dp).testTag("showcase_previous"))
        Spacer(Modifier.weight(1f))
        AppPrimaryButton(language.ui("Siguiente", "Next", "Seguinte"), { selected = (selected + 1).coerceAtMost(showcasePrayers.lastIndex) }, selected < showcasePrayers.lastIndex,
            modifier = Modifier.width(180.dp).testTag("showcase_next"), advancing = true)
    }) { headerItem ->
        LazyColumn(Modifier.fillMaxSize().testTag("showcase_reader_list"), state = anchor.listState, contentPadding = PaddingValues(bottom = 24.dp)) {
            item(key = "header") { headerItem() }
            item(key = prayer.id) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                    Column(Modifier.widthIn(max = measure + 40.dp).fillMaxWidth()
                        .then(if (appIsLandscapePhone()) Modifier else Modifier.background(palette.canvasTop, LocalAppDesign.current.shapes.card)).padding(horizontal = 20.dp, vertical = if (appIsLandscapePhone()) 0.dp else 20.dp)) {
                        Text(prayer.body(language), style = styles.reading, color = palette.textPrimary, modifier = Modifier.testTag("showcase_reading"))
                        Spacer(Modifier.height(24.dp))
                        Text(prayer.whenToPray.get(language), style = styles.subhead, color = palette.textSecondary)
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowcaseSupporting(language: AppLanguage) {
    var query by rememberSaveable(key = "showcase_supporting_query") { mutableStateOf("") }
    var selected by rememberSaveable(key = "showcase_supporting_selection") { mutableIntStateOf(0) }
    val prayer = showcasePrayers[selected]
    val styles = LocalAppTextStyles.current
    val palette = LocalAppDesign.current.palette
    val measure = appReadingMeasure(language)
    val safe = AppEdgeToEdge()
    val landscape = appIsLandscapePhone()
    val next: @Composable () -> Unit = {
        AppPrimaryButton(language.ui("Siguiente", "Next", "Seguinte"), { selected = (selected + 1) % showcasePrayers.size }, modifier = Modifier.width(180.dp).testTag("showcase_primary"), advancing = true)
    }
    val contextBlock: @Composable () -> Unit = {
        Column {
            AppDetailHeader(prayer.title.get(language), assetPath = "heroes/lumen/prayers_lily.jpg", meta = prayer.latinTitle, titleTag = "showcase_title")
            Text(prayer.whenToPray.get(language), style = styles.subhead, color = palette.textSecondary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp))
        }
    }
    AppHideTabBar()
    AppSupportingPaneLayout(supporting = {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) { contextBlock() }
    }, supportingWidth = AppPaneWidth(0.28f, 224.dp, 280.dp), mainFooter = {
        next()
    }) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(bottom = if (landscape) 24.dp else safe.calculateBottomPadding() + 24.dp)) {
            if (!landscape) contextBlock()
            Column(Modifier.fillMaxWidth().padding(top = if (landscape) statusBarHeight() + 12.dp else 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                AppSearchField(query, { query = it }, language.ui("Buscar", "Search", "Buscar"), Modifier.testTag("showcase_search"))
                Spacer(Modifier.height(16.dp))
                Column(Modifier.widthIn(max = measure + 40.dp).fillMaxWidth()
                    .background(palette.canvasTop, LocalAppDesign.current.shapes.card).padding(20.dp)) {
                    val body = prayer.body(language)
                    val marked = androidx.compose.ui.text.buildAnnotatedString {
                        append(body)
                        if (query.isNotBlank()) Regex(Regex.escape(query), RegexOption.IGNORE_CASE).findAll(body).forEach { match ->
                            addStyle(androidx.compose.ui.text.SpanStyle(background = palette.accent.copy(alpha = 0.18f)), match.range.first, match.range.last + 1)
                        }
                    }
                    Text(marked, style = styles.reading, color = palette.textPrimary, modifier = Modifier.testTag("showcase_reading"))
                    if (!landscape) { Spacer(Modifier.height(24.dp)); next() }
                }
            }
        }
    }
}

@Composable
private fun ShowcaseListDetail(language: AppLanguage) {
    var query by rememberSaveable(key = "showcase_list_query") { mutableStateOf("") }
    var filter by rememberSaveable(key = "showcase_filter") { mutableStateOf(false) }
    var filtersOpen by rememberSaveable(key = "showcase_filters_open") { mutableStateOf(false) }
    var readerOpen by rememberSaveable(key = "showcase_full_reader") { mutableStateOf(false) }
    val prayers = showcasePrayers.filter { it.title.get(language).contains(query, ignoreCase = true) && (!filter || it.id != "angelus") }
    val state = rememberAppListDetailState(prayers.map { it.id }, key = "showcase_list_selection")
    val styles = LocalAppTextStyles.current
    val palette = LocalAppDesign.current.palette
    if (readerOpen) {
        AppBackHandler { readerOpen = false }
        ShowcaseReader(language, state.selectedKey, onExit = { readerOpen = false })
        return
    }
    AppTwoPane(state, listHeader = {
        AppHeroHeader("heroes/lumen/prayers_lily.jpg", language.ui("Oraciones", "Prayers", "Orações"), titleTag = "showcase_title")
    }, search = { AppSearchField(query, { query = it }, language.ui("Buscar", "Search", "Buscar"), Modifier.testTag("showcase_search")) },
        onOpenFilters = { filtersOpen = true }, filterLabel = language.ui("Filtros", "Filters", "Filtros"),
        activeFilters = if (filter) ({ AppChip(language.ui("Básicas", "Basic", "Básicas"), true, { filter = false }) }) else null,
        onOpenReader = { readerOpen = true }, readerLabel = language.ui("Leer a pantalla completa", "Read full screen", "Ler em tela cheia"),
        list = { headerItem ->
            LazyColumn(Modifier.fillMaxSize().testTag("showcase_entries"), contentPadding = PaddingValues(bottom = 24.dp)) {
                item { headerItem() }
                items(prayers, key = { it.id }) { prayer -> AppListRow(prayer.title.get(language), subtitle = prayer.latinTitle,
                    selected = prayer.id == state.selectedKey, onClick = { state.select(prayer.id) }, modifier = Modifier.testTag("showcase_row_${prayer.id}")) }
            }
        }, detail = { key ->
            val prayer = prayers.firstOrNull { it.id == key }
            if (prayer == null) AppEmptyState(language.ui("Sin resultados", "No results", "Nenhum resultado"))
            else Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                AppDetailHeader(prayer.title.get(language), assetPath = "heroes/lumen/prayers_lily.jpg", meta = prayer.latinTitle,
                    modifier = Modifier.testTag("showcase_detail_header"), titleTag = "showcase_detail_title")
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                    Text(prayer.body(language), style = styles.reading, color = palette.textPrimary,
                        modifier = Modifier.widthIn(max = appReadingMeasure(language) + 40.dp).fillMaxWidth()
                            .background(palette.canvasTop, LocalAppDesign.current.shapes.card).padding(20.dp).testTag("showcase_reading"))
                }
            }
        })
    if (filtersOpen) AppSheet({ filtersOpen = false }, title = language.ui("Filtros", "Filters", "Filtros"), tag = "showcase_filter_sheet") {
        AppListRow(language.ui("Básicas", "Basic", "Básicas"), selected = filter, onClick = { filter = !filter }, modifier = Modifier.testTag("showcase_basic_filter"))
    }
}
