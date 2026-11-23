package com.example.ui.design

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.focusGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.material.icons.outlined.FilterList
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.collect
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign

/**
 * Shared kit with complete identity-driven geometry, type, material and chrome.
 * Navigation, semantics and content remain identical when appearance changes.
 */
@Composable
@ReadOnlyComposable
fun appKit(): AppKit = when (LocalAppDesign.current.language) {
    DesignLanguage.APPLE, DesignLanguage.IVE, DesignLanguage.CLASSIC, DesignLanguage.GLASS, DesignLanguage.SANCTUARY -> LumenKit
}

/**
 * The text size the reader actually sees: the app's own preference times the system font scale.
 * At 130 % and above ([appLargeText]) rows stop capping their lines, stack trailing values under the
 * text and give grids a single column, so nothing is cut at a phone's narrowest width.
 */
@Composable
@ReadOnlyComposable
fun appTextScale(): Float = com.example.ui.theme.LocalAppFontScale.current * androidx.compose.ui.platform.LocalDensity.current.fontScale

@Composable
@ReadOnlyComposable
fun appLargeText(): Boolean = appTextScale() >= 1.25f

/** A line cap for a list row: [normal] at ordinary sizes, no cap when the text is large (the row grows instead). */
@Composable
@ReadOnlyComposable
fun appMaxLines(normal: Int): Int = if (appLargeText()) Int.MAX_VALUE else normal

// Screens call these. They carry the default arguments an interface cannot, and
// keep every call site free of any one identity's component names.

@Composable
fun AppScaffold(
    title: String,
    navItems: List<AppNavItem>,
    selectedNavIndex: Int,
    onNavSelected: (Int) -> Unit,
    onBack: (() -> Unit)? = null,
    backLabel: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) = appKit().Scaffold(title, navItems, selectedNavIndex, onNavSelected, onBack, backLabel, actions, content)

@Composable
fun AppTopBar(
    title: String,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) = appKit().TopBar(title, subtitle, onBack, actions)

@Composable
fun AppScreenTitle(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    trailingContent: (@Composable () -> Unit)? = null
) = appKit().ScreenTitle(title, subtitle, modifier, trailingContent)

@Composable
fun AppSectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) = appKit().SectionHeader(title, subtitle, modifier)

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) = appKit().Card(modifier, onClick, content)

@Composable
fun AppListGroup(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) = appKit().ListGroup(modifier, content)

@Composable
fun AppListRow(
    title: String,
    subtitle: String? = null,
    leadingIcon: ImageVector? = null,
    trailingText: String? = null,
    showsNavigationAffordance: Boolean = true,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) = appKit().Row(
    title, subtitle, leadingIcon, trailingText,
    showsNavigationAffordance, selected, onClick, modifier
)

@Composable
fun AppDivider(modifier: Modifier = Modifier) = appKit().Divider(modifier)

@Composable
fun AppOrnament(modifier: Modifier = Modifier) = appKit().Ornament(modifier)

@Composable
fun AppPrimaryButton(
    label: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier,
    advancing: Boolean = false
) = appKit().PrimaryButton(label, onClick, enabled, leadingIcon, modifier.then(
    if (advancing && appIsLandscapePhone()) Modifier.height(48.dp) else Modifier))

@Composable
fun AppSecondaryButton(
    label: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier
) = appKit().SecondaryButton(label, onClick, enabled, leadingIcon, modifier)

@Composable
fun AppSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) = appKit().SearchField(value, onValueChange, placeholder, modifier)

@Composable
fun AppSegmentedTabs(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) = appKit().SegmentedTabs(options, selectedIndex, onSelect, modifier)

@Composable
fun AppChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = appKit().Chip(label, selected, onClick, modifier)

@Composable
fun AppVerseRow(
    number: Int,
    text: String,
    isFirstOfChapter: Boolean = false,
    hasCommentary: Boolean = false,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) = appKit().VerseRow(number, text, isFirstOfChapter, hasCommentary, onClick, modifier)

@Composable
fun AppMetricTile(
    value: String,
    caption: String,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) = appKit().MetricTile(value, caption, icon, modifier)

@Composable
fun AppEmptyState(
    title: String,
    body: String? = null,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) = appKit().EmptyState(title, body, icon, modifier)

@Composable
fun AppBanner(
    title: String,
    body: String? = null,
    accentColorHex: Long? = null,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) = appKit().Banner(title, body, accentColorHex, onClick, modifier)

/** Multiline notes using the shared card, type and colours. The caller owns the transient value. */
@Composable
fun AppNotesField(value: String, onValueChange: (String) -> Unit, label: String, modifier: Modifier = Modifier) {
    val p = LocalAppDesign.current.palette
    val styles = com.example.ui.theme.LocalAppTextStyles.current
    AppCard {
        androidx.compose.material3.Text(label, style = styles.footnote, color = p.textSecondary)
        androidx.compose.foundation.layout.Spacer(Modifier.height(8.dp))
        androidx.compose.foundation.text.BasicTextField(
            value = value, onValueChange = onValueChange,
            textStyle = styles.body.copy(color = p.textPrimary),
            cursorBrush = androidx.compose.ui.graphics.SolidColor(p.accent), minLines = 4, maxLines = 8,
            // Visible text, requesting a keyboard without spelling suggestions.
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Password, autoCorrectEnabled = false),
            modifier = modifier.fillMaxWidth().semantics { contentDescription = label }
        )
    }
}

// ------------------------------------------------------------ window sizes

/**
 * Window-width breakpoints, following the Material window size classes. Below
 * [RailMinWidth] the app is the phone layout, untouched.
 */
object AppWindow {
    /** From this width the bottom tab bar becomes a navigation rail on the leading edge. */
    val RailMinWidth: Dp = 600.dp
    /** From this width a list/detail screen shows both panes side by side. */
    val TwoPaneMinWidth: Dp = 840.dp
    /** Width of the list pane of a two-pane screen. */
    val ListPaneWidth: Dp = 360.dp
}

/** Full-window classes. Height wins; system bars never change the class. */
enum class AppWindowClass { Phone, CompactPhone, LandscapePhone, TabletPortrait, TabletLandscape }

data class AppWindowSize(val width: Dp, val height: Dp) {
    val windowClass: AppWindowClass get() = when {
        height < 480.dp -> if (width < 600.dp) AppWindowClass.CompactPhone else AppWindowClass.LandscapePhone
        width < 600.dp -> AppWindowClass.Phone
        width < 840.dp -> AppWindowClass.TabletPortrait
        else -> AppWindowClass.TabletLandscape
    }
}

/**
 * Compose 1.7 has no WindowInfo.containerSize. WindowMetricsCalculator is the
 * approved fallback: its bounds include bars and cutouts, unlike Configuration.
 * Configuration is observed only to invalidate the measurement on recreation,
 * resize and density changes; none of its dimensions are used.
 */
@Composable
fun appWindowSize(): AppWindowSize {
    val configuration = LocalConfiguration.current
    val density = androidx.compose.ui.platform.LocalDensity.current
    val context = androidx.compose.ui.platform.LocalContext.current
    return androidx.compose.runtime.remember(configuration, context, density) {
        var base = context
        while (base is android.content.ContextWrapper && base !is android.app.Activity) base = base.baseContext
        val activity = requireNotNull(base as? android.app.Activity) { "AppWindow requires an Activity window" }
        val bounds = androidx.window.layout.WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity).bounds
        with(density) { AppWindowSize(bounds.width().toDp(), bounds.height().toDp()) }
    }
}

@Composable
fun appWindowClass(): AppWindowClass = appWindowSize().windowClass

@Composable
fun appIsLandscapePhone(): Boolean = appWindowClass() == AppWindowClass.LandscapePhone

@Composable
internal fun appHasCompactHeight(): Boolean = appWindowClass().let {
    it == AppWindowClass.LandscapePhone || it == AppWindowClass.CompactPhone
}

/** The full width, including system bars; do not substitute a content constraint. */
@Composable
fun appWindowWidth(): Dp = appWindowSize().width

@Composable
fun appUsesNavigationRail(): Boolean = appWindowClass().let {
    it == AppWindowClass.LandscapePhone || it == AppWindowClass.TabletPortrait || it == AppWindowClass.TabletLandscape
}

@Composable
fun appIsTwoPane(): Boolean = appWindowClass() == AppWindowClass.TabletLandscape

/**
 * The widest a page of content is allowed to be on a wide window: the reading
 * column plus its gutters. Wider windows centre it instead of stretching it.
 */
@Composable
@ReadOnlyComposable
fun appContentMaxWidth(): Dp = with(LocalAppDesign.current.spacing) { readingMaxWidth + gutter * 2 }

/**
 * List on the leading side, detail on the other ([AppWindow.ListPaneWidth] wide
 * list, a hairline between). Declares the screen full width, so the scaffold
 * does not centre it in a reading column; the detail pane centres itself in a
 * reading column instead. Call it only when [appIsTwoPane].
 */
@Composable
fun AppTwoPane(
    modifier: Modifier = Modifier,
    list: @Composable () -> Unit,
    detail: @Composable () -> Unit
) {
    AppFullWidth()
    val design = LocalAppDesign.current
    Row(modifier.fillMaxSize().testTag("two_pane")) {
        // The list's art fills its pane; the detail's art is only softened where a wide pane leaves it a centred column.
        Box(Modifier.width(AppWindow.ListPaneWidth).fillMaxHeight().testTag("two_pane_list")) {
            androidx.compose.runtime.CompositionLocalProvider(LocalHeroSideFade provides false) { list() }
        }
        Box(Modifier.width(0.5.dp).fillMaxHeight().background(design.palette.separator))
        BoxWithConstraints(Modifier.weight(1f).fillMaxHeight().testTag("two_pane_detail"), contentAlignment = Alignment.TopCenter) {
            val column = appContentMaxWidth()
            val constrained = maxWidth > column
            androidx.compose.runtime.CompositionLocalProvider(LocalHeroSideFade provides constrained) {
                Box(Modifier.widthIn(max = column).fillMaxHeight()) { detail() }
            }
        }
    }
}

// ------------------------------------------------------- compact-height pages

enum class AppPageKind { Hub, Today, Reader, Immersive }
enum class AppPaneSide { Start, End }
data class AppPaneWidth(val fraction: Float, val min: Dp, val max: Dp) {
    init { require(fraction > 0f && fraction < 1f && min > 0.dp && max >= min) }
    fun resolve(available: Dp): Dp = (available * fraction).coerceIn(min, max)
}

internal val LocalAppPageKind = androidx.compose.runtime.compositionLocalOf { AppPageKind.Hub }
internal val LocalHeaderPoster = androidx.compose.runtime.compositionLocalOf { false }
internal val LocalAppInteriorWidth = androidx.compose.runtime.compositionLocalOf { Dp.Unspecified }
internal val LocalCompactSearch = androidx.compose.runtime.compositionLocalOf { false }
internal val LocalHeaderPanelHeight = androidx.compose.runtime.compositionLocalOf { Dp.Unspecified }
internal val LocalPanelSupportingHeight = androidx.compose.runtime.compositionLocalOf { 0.dp }
internal val LocalListArtStrip = androidx.compose.runtime.compositionLocalOf { false }
internal val LocalPanelTitleOverflow = androidx.compose.runtime.compositionLocalOf<(Int) -> Unit> { {} }

internal fun appPanelWidth(kind: AppPageKind, available: Dp): Dp = when (kind) {
    AppPageKind.Hub -> (available * 0.36f).coerceIn(248.dp, 320.dp)
    AppPageKind.Today -> (available * 0.28f).coerceIn(208.dp, 280.dp)
    AppPageKind.Reader -> (available * 0.30f).coerceIn(224.dp, 280.dp)
    AppPageKind.Immersive -> (available * 0.28f).coerceIn(224.dp, 280.dp)
}

private enum class PageSlot { Panel, Main, PanelFooter, ContentFooter, Edge, Spine, ReadingSurface }

/**
 * One stable subcomposition per slot in both postures. The portrait list gets
 * a measured marker at its first item; the actual header remains in the same
 * slot and is placed at that marker (including its scroll offset). Landscape
 * changes only the measure/place policy. No movable-content reparenting.
 */
@Composable
fun AppPageLayout(
    header: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    kind: AppPageKind = AppPageKind.Hub,
    supporting: (@Composable ColumnScope.() -> Unit)? = null,
    supportingInPortrait: Boolean = true,
    panelFooter: (@Composable RowScope.() -> Unit)? = null,
    contentFooter: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable (headerItem: @Composable () -> Unit) -> Unit
) {
    val landscape = appIsLandscapePhone()
    if (landscape) {
        AppFullWidth()
        if (kind == AppPageKind.Reader || kind == AppPageKind.Immersive) AppHideTabBar()
    }
    AppEdgeToEdge()
    val safe = appSafeInsets()
    val fullWindowWidth = appWindowSize().width
    val design = LocalAppDesign.current
    val density = androidx.compose.ui.platform.LocalDensity.current
    val panelScroll = androidx.compose.foundation.rememberScrollState()
    var supportingHeight by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(0.dp) }
    var titleOverflow by androidx.compose.runtime.remember { androidx.compose.runtime.mutableIntStateOf(0) }
    androidx.compose.runtime.LaunchedEffect(landscape, titleOverflow) {
        if (landscape) panelScroll.scrollTo(titleOverflow)
    }
    var marker by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(androidx.compose.ui.geometry.Offset.Unspecified) }
    var origin by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(androidx.compose.ui.geometry.Offset.Zero) }
    var panelFocused by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    var mainFocused by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    val keyboard = with(density) { androidx.compose.foundation.layout.WindowInsets.ime.getBottom(this).toDp() }
    val screenBottom = appScreenInsets().calculateBottomPadding()
    androidx.compose.ui.layout.SubcomposeLayout(modifier.fillMaxSize().clipToBounds()
        .onGloballyPositioned { origin = it.positionInRoot() }.semantics { isTraversalGroup = true }) { constraints ->
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val panelWidth = if (landscape) appPanelWidth(kind, if (kind == AppPageKind.Reader || kind == AppPageKind.Immersive) fullWindowWidth else width.toDp()).roundToPx() else width
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        val panelBottom = if (landscape) maxOf(safe.bottom, if (panelFocused) keyboard else 0.dp).roundToPx() else 0
        val contentBottom = if (landscape) maxOf(safe.bottom, if (mainFocused) keyboard else 0.dp).roundToPx()
            else if (contentFooter != null) screenBottom.roundToPx() else 0
        val panelFoot = subcompose(PageSlot.PanelFooter) {
            Row(Modifier.fillMaxWidth().then(if (landscape && panelFooter != null) Modifier.appPaneFooter(kind == AppPageKind.Reader) else Modifier).padding(horizontal = design.spacing.gutter)
                .semantics { isTraversalGroup = true; traversalIndex = 0f }, horizontalArrangement = androidx.compose.foundation.layout.Arrangement.End,
                verticalAlignment = Alignment.CenterVertically) { panelFooter?.invoke(this) }
        }.single().measure(loose.copy(minWidth = panelWidth, maxWidth = panelWidth,
            minHeight = if (panelFooter != null) 56.dp.roundToPx() else 0,
            maxHeight = if (panelFooter != null) 56.dp.roundToPx() else 0))
        val contentWidth = if (landscape) width - panelWidth else width
        val contentFoot = subcompose(PageSlot.ContentFooter) {
            Row(Modifier.fillMaxWidth().then(if (landscape && contentFooter != null) Modifier.appPaneFooter(kind == AppPageKind.Reader) else Modifier).padding(horizontal = design.spacing.gutter)
                .semantics { isTraversalGroup = true; traversalIndex = 1f }, horizontalArrangement = androidx.compose.foundation.layout.Arrangement.End,
                verticalAlignment = Alignment.CenterVertically) { contentFooter?.invoke(this) }
        }.single().measure(loose.copy(minWidth = contentWidth, maxWidth = contentWidth,
            minHeight = if (contentFooter != null) 56.dp.roundToPx() else 0,
            maxHeight = if (contentFooter != null) 56.dp.roundToPx() else 0))
        val panel = subcompose(PageSlot.Panel) {
            androidx.compose.runtime.CompositionLocalProvider(
                LocalHeaderPlacement provides if (landscape) AppHeaderPlacement.SidePanel else AppHeaderPlacement.InList,
                LocalHeaderPoster provides (supporting == null), LocalAppPageKind provides kind,
                LocalPanelSupportingHeight provides supportingHeight,
                LocalPanelTitleOverflow provides { titleOverflow = it },
                LocalHeaderPanelHeight provides with(density) { (height - panelBottom - panelFoot.height).toDp() }
            ) {
                Box(Modifier.fillMaxWidth().then(if (landscape && kind == AppPageKind.Reader) Modifier.height(with(density) { (height - panelBottom - panelFoot.height).toDp() }) else Modifier)) {
                androidx.compose.foundation.layout.Column(Modifier.fillMaxWidth().then(if (landscape && kind == AppPageKind.Reader) Modifier.fillMaxHeight() else Modifier)
                    .onFocusChanged { panelFocused = it.hasFocus }.focusGroup().testTag("app_page_panel")
                    .then(if (landscape) Modifier.verticalScroll(panelScroll) else Modifier)
                    .semantics { isTraversalGroup = true; traversalIndex = 0f }) {
                    header()
                    // Slot is always composed from here; the layout hides it when not wanted in portrait.
                    androidx.compose.ui.layout.Layout(content = { androidx.compose.foundation.layout.Column { supporting?.invoke(this) } },
                        modifier = Modifier.onSizeChanged { supportingHeight = with(density) { it.height.toDp() } }) { children, c ->
                        val visible = landscape || supportingInPortrait
                        val child = children.single().measure(c.copy(minHeight = 0))
                        layout(c.maxWidth, if (visible) child.height else 0) { if (visible) child.placeRelative(0, 0) }
                    }
                }
                if (landscape && panelScroll.canScrollForward) Box(Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth().height(24.dp).background(androidx.compose.ui.graphics.Brush.verticalGradient(
                        listOf(androidx.compose.ui.graphics.Color.Transparent, design.palette.canvasTop)))
                    .testTag("panel_scroll_hint"))
                }
            }
        }.single().measure(loose.copy(minWidth = panelWidth, maxWidth = panelWidth,
            maxHeight = if (landscape) (height - panelBottom - panelFoot.height).coerceAtLeast(0) else androidx.compose.ui.unit.Constraints.Infinity))
        val top = if (landscape) (safe.top + if (kind == AppPageKind.Reader) 52.dp else 12.dp).roundToPx() else 0
        val bodyHeight = (height - top - contentBottom - contentFoot.height).coerceAtLeast(0)
        val main = subcompose(PageSlot.Main) {
            androidx.compose.runtime.CompositionLocalProvider(LocalHeaderPlacement provides AppHeaderPlacement.InList,
                LocalAppInteriorWidth provides with(density) { contentWidth.toDp() - design.spacing.gutter * 2 }) {
                Box(Modifier.fillMaxSize().onFocusChanged { mainFocused = it.hasFocus }.focusGroup()
                    .semantics { isTraversalGroup = true; traversalIndex = 1f }.testTag("app_page_content")
                    .then(if (landscape && kind == AppPageKind.Reader) Modifier.background(design.palette.canvasTop) else Modifier)) {
                    content {
                        androidx.compose.runtime.DisposableEffect(landscape) { onDispose { marker = androidx.compose.ui.geometry.Offset.Unspecified } }
                        androidx.compose.foundation.layout.Spacer(Modifier.fillMaxWidth().height(if (landscape) 0.dp else with(density) { (panel.height + panelFoot.height).toDp() })
                            .onGloballyPositioned { marker = it.positionInRoot() })
                    }
                }
            }
        }.single().measure(androidx.compose.ui.unit.Constraints.fixed(contentWidth, bodyHeight))
        val readingSurface = subcompose(PageSlot.ReadingSurface) {
            Box(Modifier.fillMaxSize().then(if (landscape && kind == AppPageKind.Reader) Modifier.background(design.palette.canvasTop) else Modifier))
        }.single().measure(androidx.compose.ui.unit.Constraints.fixed(contentWidth, (height - safe.top.roundToPx()).coerceAtLeast(0)))
        val edge = subcompose(PageSlot.Edge) {
            if (design.opaqueMaterial()) androidx.compose.foundation.layout.Column {
                Box(Modifier.fillMaxWidth().height(safe.top).background(design.palette.canvasTop.copy(alpha = 0.95f)))
                Box(Modifier.fillMaxWidth().height(0.5.dp).background(design.palette.separator))
            } else Box(Modifier.fillMaxWidth().height(safe.top + 24.dp).background(androidx.compose.ui.graphics.Brush.verticalGradient(
                0f to design.palette.canvasTop, 0.5f to design.palette.canvasTop.copy(alpha = 0.95f), 1f to androidx.compose.ui.graphics.Color.Transparent)))
        }.single().measure(loose.copy(minWidth = contentWidth, maxWidth = contentWidth))
        val spine = subcompose(PageSlot.Spine) {
            Row(Modifier.fillMaxHeight()) {
                Box(Modifier.width(0.5.dp).fillMaxHeight().background(design.palette.separator))
                if (design.isSanctuary) {
                    androidx.compose.foundation.layout.Spacer(Modifier.width(3.dp))
                    Box(Modifier.width(0.5.dp).fillMaxHeight().background(design.palette.separator))
                }
            }
        }.single().measure(loose.copy(maxWidth = 4.dp.roundToPx(), minHeight = height))
        layout(width, height) {
            if (landscape && kind == AppPageKind.Reader) readingSurface.placeRelative(panelWidth, safe.top.roundToPx())
            main.placeRelative(if (landscape) panelWidth else 0, top)
            if (landscape) {
                panel.placeRelative(0, 0)
                if (panelFooter != null) panelFoot.placeRelative(0, height - panelBottom - panelFoot.height)
            } else {
                if (marker.x.isFinite() && marker.y.isFinite()) {
                    val point = marker - origin
                    panel.placeRelative(point.x.toInt(), point.y.toInt())
                    if (panelFooter != null) panelFoot.placeRelative(point.x.toInt(), point.y.toInt() + panel.height)
                }
            }
            if (landscape) {
                edge.placeRelative(panelWidth, 0)
                if (design.opaqueMaterial()) spine.placeRelative(panelWidth - spine.width, 0)
            }
            if (contentFooter != null) contentFoot.placeRelative(if (landscape) panelWidth else 0, height - contentBottom - contentFoot.height)
        }
    }
}

/** Separate context/main slots keep their composition identity when their positions change. */
@Composable
fun AppSupportingPaneLayout(
    supporting: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    supportingSide: AppPaneSide = AppPaneSide.Start,
    supportingWidth: AppPaneWidth,
    showSupportingPane: Boolean = true,
    endActions: (@Composable ColumnScope.() -> Unit)? = null,
    supportingFooter: (@Composable RowScope.() -> Unit)? = null,
    mainFooter: (@Composable RowScope.() -> Unit)? = null,
    main: @Composable () -> Unit
) {
    val landscape = appIsLandscapePhone()
    if (landscape) { AppFullWidth(); AppHideTabBar(); AppEdgeToEdge() }
    val safe = appSafeInsets()
    val design = LocalAppDesign.current
    val density = androidx.compose.ui.platform.LocalDensity.current
    val keyboard = with(density) { WindowInsets.ime.getBottom(this).toDp() }
    var supportFocused by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    var mainFocused by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    androidx.compose.ui.layout.Layout(content = {
        androidx.compose.runtime.CompositionLocalProvider(LocalHeaderPlacement provides if (landscape && showSupportingPane) AppHeaderPlacement.SidePanel else AppHeaderPlacement.InList) {
        androidx.compose.foundation.layout.Column(Modifier.fillMaxSize().onFocusChanged { supportFocused = it.hasFocus }.focusGroup()
            .testTag("app_supporting_panel").semantics { isTraversalGroup = true; traversalIndex = if (supportingSide == AppPaneSide.Start) 0f else 1f }) { supporting() }
        }
        Box(Modifier.fillMaxSize().onFocusChanged { mainFocused = it.hasFocus }.focusGroup()
            .testTag("app_supporting_main").semantics { isTraversalGroup = true; traversalIndex = if (supportingSide == AppPaneSide.Start) 1f else 0f }
            .then(if (landscape) Modifier.padding(top = safe.top).clipToBounds().layout { measurable, c ->
                val top = safe.top.roundToPx()
                val child = measurable.measure(c.copy(minHeight = c.minHeight + top, maxHeight = c.maxHeight + top))
                layout(c.maxWidth, c.maxHeight) { child.placeRelative(0, -top) }
            } else Modifier)) { main() }
        androidx.compose.foundation.layout.Column { endActions?.invoke(this) }
        Row(Modifier.then(if (landscape && supportingFooter != null) Modifier.appPaneFooter() else Modifier).padding(start = 20.dp, end = 20.dp, bottom = if (landscape) safe.bottom else 0.dp), horizontalArrangement = androidx.compose.foundation.layout.Arrangement.End, verticalAlignment = Alignment.CenterVertically) { supportingFooter?.invoke(this) }
        Row(Modifier.then(if (landscape && mainFooter != null) Modifier.appPaneFooter() else Modifier)
            .padding(start = 20.dp, end = 20.dp, bottom = if (landscape) safe.bottom else 0.dp), horizontalArrangement = androidx.compose.foundation.layout.Arrangement.End, verticalAlignment = Alignment.CenterVertically) { mainFooter?.invoke(this) }
        Row(Modifier.fillMaxHeight()) {
            if (design.opaqueMaterial()) {
                Box(Modifier.width(0.5.dp).fillMaxHeight().background(design.palette.separator))
                if (design.isSanctuary) {
                    androidx.compose.foundation.layout.Spacer(Modifier.width(3.dp))
                    Box(Modifier.width(0.5.dp).fillMaxHeight().background(design.palette.separator))
                }
            }
        }
    }, modifier = modifier.fillMaxSize().clipToBounds().semantics { isTraversalGroup = true }) { children, constraints ->
        val split = landscape && showSupportingPane
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val panelWidth = if (split) supportingWidth.resolve(width.toDp()).roundToPx().coerceAtMost(width - 220.dp.roundToPx()) else 0
        val measurePanelWidth = if (split) panelWidth else supportingWidth.resolve(width.toDp()).roundToPx().coerceAtMost(width)
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        val actionsTop = if (landscape) (safe.top + 4.dp).roundToPx() else 0
        val actions = children[2].measure(loose.copy(maxWidth = if (split && endActions != null) (72.dp + maxOf(12.dp, safe.end + 8.dp)).roundToPx() else 0,
            maxHeight = if (landscape) (height - actionsTop - (safe.bottom + 4.dp).roundToPx()).coerceAtLeast(0) else height))
        val mainWidth = width - panelWidth - actions.width
        val footHeight = if (landscape) (56.dp + safe.bottom).roundToPx() else 0
        val supportKeyboard = if (landscape && supportFocused) (keyboard - safe.bottom).coerceAtLeast(0.dp).roundToPx() else 0
        val mainKeyboard = if (landscape && mainFocused) (keyboard - safe.bottom).coerceAtLeast(0.dp).roundToPx() else 0
        val panelFooterHeight = if (split && supportingFooter != null) footHeight else 0
        val mainFooterHeight = if (landscape && mainFooter != null) footHeight else 0
        val panelSafeBottom = if (landscape && supportingFooter == null) safe.bottom.roundToPx() else 0
        val mainSafeBottom = if (landscape && mainFooter == null) safe.bottom.roundToPx() else 0
        val panelFoot = children[3].measure(loose.copy(minWidth = measurePanelWidth, maxWidth = measurePanelWidth,
            minHeight = if (supportingFooter != null) maxOf(56.dp.roundToPx(), footHeight) else 0,
            maxHeight = if (supportingFooter != null) maxOf(56.dp.roundToPx(), footHeight) else 0))
        val mainFoot = children[4].measure(loose.copy(minWidth = mainWidth, maxWidth = mainWidth,
            minHeight = if (mainFooter != null) maxOf(56.dp.roundToPx(), footHeight) else 0,
            maxHeight = if (mainFooter != null) maxOf(56.dp.roundToPx(), footHeight) else 0))
        val panel = children[0].measure(androidx.compose.ui.unit.Constraints.fixed(measurePanelWidth,
            (height - panelFooterHeight - panelSafeBottom - supportKeyboard).coerceAtLeast(0)))
        val body = children[1].measure(androidx.compose.ui.unit.Constraints.fixed(mainWidth,
            (height - mainFooterHeight - mainSafeBottom - mainKeyboard).coerceAtLeast(0)))
        val spine = children[5].measure(loose.copy(maxWidth = 4.dp.roundToPx(), minHeight = height))
        layout(width, height) {
            val mainX = if (split && supportingSide == AppPaneSide.Start) panelWidth else 0
            val panelX = if (supportingSide == AppPaneSide.Start) 0 else mainWidth
            body.placeRelative(mainX, 0)
            if (split) {
                panel.placeRelative(panelX, 0)
                spine.placeRelative(if (supportingSide == AppPaneSide.Start) panelWidth - spine.width else mainWidth, 0)
                if (supportingFooter != null) panelFoot.placeRelative(panelX, height - panelFoot.height - supportKeyboard)
                if (endActions != null) actions.placeRelative(width - actions.width, actionsTop)
            }
            if (landscape && mainFooter != null) mainFoot.placeRelative(mainX, height - mainFoot.height - mainKeyboard)
        }
    }
}

@Composable
fun appGridColumns(minCell: Dp, max: Int, interior: Dp = Dp.Unspecified): Int {
    require(minCell > 0.dp && max > 0)
    val provided = LocalAppInteriorWidth.current
    val available = if (interior.value.isFinite()) interior else if (provided.value.isFinite()) provided
        else appWindowWidth() - LocalAppDesign.current.spacing.gutter * 2
    val k = if (appLargeText()) 1.25f else 1f
    return kotlin.math.floor((available.value + 12f) / (minCell.value * k + 12f)).toInt().coerceIn(1, max)
}

/** Explicit lane assignment; linear accessibility order is lane A, then lane B. */
@Composable
fun <T> AppFeedColumns(lanes: List<List<T>>, modifier: Modifier = Modifier, item: @Composable (T) -> Unit) {
    val gap = 16.dp
    androidx.compose.ui.layout.Layout(content = {
        lanes.forEachIndexed { index, lane ->
            androidx.compose.foundation.layout.Column(Modifier.semantics { isTraversalGroup = true; traversalIndex = index.toFloat() },
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(LocalAppDesign.current.spacing.sectionGap)) {
                lane.forEach { item(it) }
            }
        }
    }, modifier = modifier.fillMaxWidth()) { children, constraints ->
        val columns = children.size.coerceAtLeast(1)
        val parallel = constraints.maxWidth >= columns * 220.dp.roundToPx() + (columns - 1) * gap.roundToPx()
        val w = if (parallel) (constraints.maxWidth - (columns - 1) * gap.roundToPx()) / columns else constraints.maxWidth
        val places = children.map { it.measure(constraints.copy(minWidth = w, maxWidth = w, minHeight = 0)) }
        val h = if (parallel) places.maxOfOrNull { it.height } ?: 0 else places.sumOf { it.height } + ((columns - 1) * gap.roundToPx())
        layout(constraints.maxWidth, constraints.constrainHeight(h)) {
            var y = 0
            places.forEachIndexed { index, p ->
                p.placeRelative(if (parallel) index * (w + gap.roundToPx()) else 0, if (parallel) 0 else y)
                y += p.height + gap.roundToPx()
            }
        }
    }
}

/** 66 real characters from the app's John 1:1 sample in the effective reading face. */
@Composable
fun appReadingMeasure(language: com.example.data.models.AppLanguage? = null, style: androidx.compose.ui.text.TextStyle? = null): Dp {
    val stored = LocalPreferencesStore.current?.preferences?.collectAsState(initial = null)
    val active = language ?: stored?.value?.language ?: com.example.data.models.AppLanguage.fromCode(LocalConfiguration.current.locales[0].language)
    val text = com.example.data.repository.BibleData.sampleVersesWithCommentaries.first().text.get(active).take(66)
    val effectiveStyle = style ?: com.example.ui.theme.LocalAppTextStyles.current.reading
    val measured = androidx.compose.ui.text.rememberTextMeasurer().measure(text, effectiveStyle, softWrap = false, maxLines = 1)
    return with(androidx.compose.ui.platform.LocalDensity.current) { measured.size.width.toDp() }
}

/** Save only a block identity/index and a fraction; pixel offsets do not survive reflow. */
@androidx.compose.runtime.Stable
class AppReadingAnchor internal constructor(index: Int = 0, fraction: Float = 0f, blockKey: String? = null) {
    var index by androidx.compose.runtime.mutableIntStateOf(index)
        internal set
    var fraction by androidx.compose.runtime.mutableFloatStateOf(fraction)
        internal set
    var blockKey by androidx.compose.runtime.mutableStateOf(blockKey)
        internal set
    lateinit var listState: androidx.compose.foundation.lazy.LazyListState
        internal set

    internal companion object {
        val Saver = androidx.compose.runtime.saveable.listSaver<AppReadingAnchor, Any>(
            save = { listOf(it.index, it.fraction, it.blockKey.orEmpty()) },
            restore = { AppReadingAnchor(it[0] as Int, it[1] as Float, (it[2] as String).ifEmpty { null }) }
        )
    }
}

@Composable
fun rememberReadingAnchor(key: String = "reading_anchor"): AppReadingAnchor {
    val anchor = androidx.compose.runtime.saveable.rememberSaveable(key = key, saver = AppReadingAnchor.Saver) { AppReadingAnchor() }
    val state = androidx.compose.foundation.lazy.rememberLazyListState(initialFirstVisibleItemIndex = anchor.index)
    anchor.listState = state
    val window = appWindowSize()
    val style = com.example.ui.theme.LocalAppTextStyles.current.reading
    val scale = androidx.compose.ui.platform.LocalDensity.current.fontScale
    androidx.compose.runtime.LaunchedEffect(state, window, style, scale) {
        val index = anchor.index
        val fraction = anchor.fraction
        val block = anchor.blockKey
        state.scrollToItem(index)
        val visible = androidx.compose.runtime.snapshotFlow { state.layoutInfo.visibleItemsInfo.takeIf { it.isNotEmpty() } }.filterNotNull().first()
        val info = visible.firstOrNull { it.key.toString() == block } ?: visible.firstOrNull { it.index == index } ?: visible.first()
        state.scrollToItem(info.index, (info.size * fraction).toInt())
        androidx.compose.runtime.snapshotFlow {
            val first = state.layoutInfo.visibleItemsInfo.firstOrNull { it.index == state.firstVisibleItemIndex }
            if (first == null) null else Triple(first.index,
                state.firstVisibleItemScrollOffset.toFloat() / first.size.coerceAtLeast(1), first.key.toString())
        }.filterNotNull().collect { (i, part, identity) ->
            anchor.index = i; anchor.fraction = part.coerceIn(0f, 1f); anchor.blockKey = identity
        }
    }
    return anchor
}

/** A default selection never pushes a portrait page; a deliberate selection does. */
@androidx.compose.runtime.Stable
class AppListDetailState internal constructor(initialKey: String?, explicit: Boolean = false) {
    var selectedKey by androidx.compose.runtime.mutableStateOf(initialKey)
        private set
    var explicit by androidx.compose.runtime.mutableStateOf(explicit)
        private set
    fun select(key: String) { selectedKey = key; explicit = true }
    fun showList() { explicit = false }
    internal fun defaultTo(key: String?) { if (!explicit) selectedKey = key }
    internal companion object {
        val Saver = androidx.compose.runtime.saveable.listSaver<AppListDetailState, Any>(
            save = { listOf(it.selectedKey.orEmpty(), it.explicit) },
            restore = { AppListDetailState((it[0] as String).ifEmpty { null }, it[1] as Boolean) })
    }
}

@Composable
fun rememberAppListDetailState(keys: List<String>, initialKey: String? = null, key: String = "list_detail_selection"): AppListDetailState {
    val state = androidx.compose.runtime.saveable.rememberSaveable(key = key, saver = AppListDetailState.Saver) {
        AppListDetailState(initialKey ?: keys.firstOrNull(), initialKey != null)
    }
    androidx.compose.runtime.SideEffect { state.defaultTo(keys.firstOrNull()) }
    return state
}

/** Migrating lists expose their existing search/filters; their state stays above this wrapper. */
@Composable
fun AppTwoPane(
    state: AppListDetailState,
    modifier: Modifier = Modifier,
    listHeader: (@Composable () -> Unit)? = null,
    search: (@Composable () -> Unit)? = null,
    onOpenFilters: (() -> Unit)? = null,
    filterLabel: String = "Filters",
    activeFilters: (@Composable RowScope.() -> Unit)? = null,
    onOpenReader: (() -> Unit)? = null,
    readerLabel: String = "Read full screen",
    list: @Composable (headerItem: @Composable () -> Unit) -> Unit,
    detail: @Composable (String?) -> Unit
) {
    val landscape = appIsLandscapePhone()
    val twoPane = landscape || appWindowClass() == AppWindowClass.TabletLandscape
    val safe = appSafeInsets()
    val design = LocalAppDesign.current
    val density = androidx.compose.ui.platform.LocalDensity.current
    var artOffset by androidx.compose.runtime.remember { androidx.compose.runtime.mutableFloatStateOf(0f) }
    var artHeight by androidx.compose.runtime.remember { androidx.compose.runtime.mutableFloatStateOf(with(density) { 96.dp.toPx() }) }
    val connection = androidx.compose.runtime.remember {
        object : androidx.compose.ui.input.nestedscroll.NestedScrollConnection {
            override fun onPreScroll(available: androidx.compose.ui.geometry.Offset, source: androidx.compose.ui.input.nestedscroll.NestedScrollSource): androidx.compose.ui.geometry.Offset {
                if (available.y >= 0f) return androidx.compose.ui.geometry.Offset.Zero
                val before = artOffset
                artOffset = (artOffset + available.y).coerceIn(-artHeight, 0f)
                return androidx.compose.ui.geometry.Offset(0f, artOffset - before)
            }
            override fun onPostScroll(consumed: androidx.compose.ui.geometry.Offset, available: androidx.compose.ui.geometry.Offset,
                                      source: androidx.compose.ui.input.nestedscroll.NestedScrollSource): androidx.compose.ui.geometry.Offset {
                if (available.y <= 0f) return androidx.compose.ui.geometry.Offset.Zero
                val before = artOffset
                artOffset = (artOffset + available.y).coerceIn(-artHeight, 0f)
                return androidx.compose.ui.geometry.Offset(0f, artOffset - before)
            }
        }
    }
    if (twoPane) AppFullWidth()
    AppEdgeToEdge()
    AppBackHandler(enabled = !twoPane && state.explicit) { state.showList() }
    androidx.compose.ui.layout.Layout(content = {
        androidx.compose.foundation.layout.Column(Modifier.fillMaxSize().testTag("two_pane_list")
            .nestedScroll(connection).semantics { isTraversalGroup = true; traversalIndex = 0f }) {
            androidx.compose.runtime.CompositionLocalProvider(
                LocalHeaderPlacement provides if (landscape) AppHeaderPlacement.CompactBand else AppHeaderPlacement.InList,
                LocalListArtStrip provides landscape) {
                Box(Modifier.fillMaxWidth().layout { measurable, constraints ->
                    val child = measurable.measure(constraints.copy(minHeight = 0, maxHeight = androidx.compose.ui.unit.Constraints.Infinity))
                    artHeight = child.height.toFloat()
                    layout(child.width, (child.height + artOffset).toInt().coerceAtLeast(0)) { child.placeRelative(0, 0) }
                }.clipToBounds().testTag("list_art_strip")) { listHeader?.invoke() }
            }
            if (search != null || onOpenFilters != null) Row(Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 12.dp)
                .testTag("list_sticky_bar"), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
                androidx.compose.runtime.CompositionLocalProvider(LocalCompactSearch provides true) {
                    if (search != null) Box(Modifier.weight(1f)) { search() }
                }
                if (onOpenFilters != null) AppGlassIconButton(androidx.compose.material.icons.Icons.Outlined.FilterList,
                    filterLabel, onOpenFilters, tag = "list_filter_button", modifier = Modifier.size(44.dp))
            }
            if (activeFilters != null) Row(Modifier.fillMaxWidth().heightIn(min = 44.dp), content = activeFilters)
            Box(Modifier.weight(1f)) { list {} }
        }
        androidx.compose.runtime.CompositionLocalProvider(LocalHeaderPlacement provides if (landscape) AppHeaderPlacement.DetailPane else AppHeaderPlacement.InList) {
            BoxWithConstraints(Modifier.fillMaxSize().testTag("two_pane_detail")
                .semantics { isTraversalGroup = true; traversalIndex = 1f }, contentAlignment = Alignment.TopCenter) {
                val column = appContentMaxWidth()
                androidx.compose.runtime.CompositionLocalProvider(LocalHeroSideFade provides (maxWidth > column)) {
                    androidx.compose.foundation.layout.Column(Modifier.widthIn(max = column).fillMaxSize()) {
                        Box(Modifier.weight(1f)) { detail(state.selectedKey) }
                        if (onOpenReader != null) AppSecondaryButton(readerLabel, onOpenReader,
                            modifier = Modifier.fillMaxWidth().testTag("detail_open_reader"))
                    }
                }
            }
        }
        Row(Modifier.fillMaxHeight()) {
            Box(Modifier.width(0.5.dp).fillMaxHeight().background(design.palette.separator))
            if (design.isSanctuary) {
                androidx.compose.foundation.layout.Spacer(Modifier.width(3.dp))
                Box(Modifier.width(0.5.dp).fillMaxHeight().background(design.palette.separator))
            }
        }
    }, modifier = modifier.fillMaxSize().clipToBounds().testTag("two_pane").semantics { isTraversalGroup = true }) { children, constraints ->
        val w = constraints.maxWidth
        val h = constraints.maxHeight
        val listWidth = if (landscape) (w.toDp() * 0.36f).coerceIn(272.dp, 320.dp).roundToPx() else if (twoPane) 360.dp.roundToPx() else w
        val top = if (landscape) safe.top.roundToPx() else 0
        val bottom = if (landscape) safe.bottom.roundToPx() else 0
        val panel = children[0].measure(androidx.compose.ui.unit.Constraints.fixed(listWidth, (h - top - bottom).coerceAtLeast(0)))
        val body = children[1].measure(androidx.compose.ui.unit.Constraints.fixed(if (twoPane) w - listWidth else w, (h - top - bottom).coerceAtLeast(0)))
        val spine = children[2].measure(constraints.copy(minWidth = 0, maxWidth = 4.dp.roundToPx(), minHeight = h - top - bottom, maxHeight = h - top - bottom))
        layout(w, h) {
            if (twoPane || !state.explicit) panel.placeRelative(0, top)
            if (twoPane || state.explicit) body.placeRelative(if (twoPane) listWidth else 0, top)
            if (twoPane) spine.placeRelative(listWidth - spine.width, top)
        }
    }
}

/** Guided devotions retain their portrait cover; a short window gets a compact illustrated access. */
@Composable
fun AppGuidedPrayerCard(
    title: String,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    assetPath: String,
    meta: String? = null,
    aspectRatio: Float = 0.95f,
    tag: String? = null
) {
    if (appHasCompactHeight()) CompactGuidedPrayerCard(title, onClick, modifier, assetPath, meta, tag)
    else AppImageCard(title = title, onClick = onClick, modifier = modifier, assetPath = assetPath,
        meta = meta, aspectRatio = aspectRatio, tag = tag)
}
