package com.example.ui.design

import androidx.compose.foundation.layout.fillMaxWidth
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
    modifier: Modifier = Modifier
) = appKit().PrimaryButton(label, onClick, enabled, leadingIcon, modifier)

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

/** The width of the window this composition lives in (it follows split-screen and foldable postures). */
@Composable
@ReadOnlyComposable
fun appWindowWidth(): Dp = LocalConfiguration.current.screenWidthDp.dp

/** True when the tab bar is a leading-edge rail (window width >= [AppWindow.RailMinWidth]). */
@Composable
@ReadOnlyComposable
fun appUsesNavigationRail(): Boolean = appWindowWidth() >= AppWindow.RailMinWidth

/** True when list/detail screens show both panes (window width >= [AppWindow.TwoPaneMinWidth]). */
@Composable
@ReadOnlyComposable
fun appIsTwoPane(): Boolean = appWindowWidth() >= AppWindow.TwoPaneMinWidth

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
