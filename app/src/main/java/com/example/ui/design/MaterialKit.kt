package com.example.ui.design

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Inbox
import androidx.compose.material.icons.rounded.Search
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Button
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card as M3Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * The Material 3 identity: tonal surfaces stepped through the `surfaceContainer`
 * ladder, the 16/28dp shape scale, stadium buttons and search field, and a pill
 * navigation indicator. Depth is tonal, never cast — every card sits at 0dp and
 * is separated from what is behind it by a tone step alone, so the hierarchy
 * survives dark mode where a shadow does not.
 *
 * ### Why the colour is read from the scheme
 *
 * The scheme built in `Theme.kt` is the single source of truth: its
 * `surfaceContainer` ladder is stepped from the palette canvas toward the
 * palette's deep surface tone, and its tonal containers are struck from the
 * accent itself, the way a seeded M3 scheme derives them. [roles] therefore
 * reads the scheme roles directly instead of re-deriving a parallel ladder
 * here — a second derivation is what produced surfaces that drifted from
 * the scheme (and, in the baseline palette, green glyphs sitting in lilac
 * circles). The `hsl`/`tone`/`readableOn` helpers below are retained only
 * for [Banner], which harmonizes a screen-supplied liturgical colour that
 * has no scheme role of its own; they no longer feed [roles].
 */
object MaterialKit : AppKit {

    // ------------------------------------------------------------------ colour

    /**
     * The roles this identity paints with, read straight from the
     * `MaterialTheme` colour scheme (see [roles]). The scheme — not this
     * file — owns the tonal derivation, so these can never drift from the
     * surfaces M3 components draw from the same scheme.
     */
    @Immutable
    private data class Roles(
        /** Background / top app bar, scheme `surface`. */
        val canvas: Color,
        /** `surfaceContainerLow`. */
        val low: Color,
        /** `surfaceContainer`: list rows, the navigation bar. */
        val container: Color,
        /** `surfaceContainerHigh`: the search field, metric tiles. */
        val high: Color,
        /** `surfaceContainerHighest`. */
        val highest: Color,
        /** `primaryContainer`: the tonal circle a metric or empty state wears. */
        val accentSoft: Color,
        val onAccentSoft: Color,
        /** `secondaryContainer`: nav pill, selection, verse number, active segment. */
        val accentSofter: Color,
        val onAccentSofter: Color,
        /** Scheme `outline`: dividers and the chip/segment hairline. */
        val outline: Color
    )

    // Retained for Banner (below), which harmonizes a screen-supplied
    // liturgical colour and solves legible text on it. Not used by roles().

    /** Relative luminance, for the WCAG ratio the on-colours are solved against. */
    private fun Color.relativeLuminance(): Float {
        fun ch(c: Float): Float {
            val v = c.toDouble()
            return (if (v <= 0.03928) v / 12.92 else Math.pow((v + 0.055) / 1.055, 2.4)).toFloat()
        }
        return 0.2126f * ch(red) + 0.7152f * ch(green) + 0.0722f * ch(blue)
    }

    private fun contrastOf(a: Color, b: Color): Float {
        val la = a.relativeLuminance()
        val lb = b.relativeLuminance()
        val hi = if (la > lb) la else lb
        val lo = if (la > lb) lb else la
        return (hi + 0.05f) / (lo + 0.05f)
    }

    private fun Color.lighten(f: Float) =
        Color(red + (1f - red) * f, green + (1f - green) * f, blue + (1f - blue) * f, alpha)

    private fun Color.darken(f: Float) =
        Color(red * (1f - f), green * (1f - f), blue * (1f - f), alpha)

    /** Walks a foreground toward the far end of the ramp until it clears 4.5:1. */
    private fun Color.readableOn(background: Color, target: Float = 4.5f): Color {
        var candidate = this
        val goLighter = background.relativeLuminance() < 0.5f
        repeat(14) {
            if (contrastOf(candidate, background) >= target) return candidate
            candidate = if (goLighter) candidate.lighten(0.11f) else candidate.darken(0.11f)
        }
        return candidate
    }

    /** Hue (0..360), saturation and lightness of an sRGB colour. */
    private fun Color.hsl(): FloatArray {
        val max = maxOf(red, green, blue)
        val min = minOf(red, green, blue)
        val l = (max + min) / 2f
        val d = max - min
        if (d < 1e-4f) return floatArrayOf(0f, 0f, l)
        val s = d / (1f - kotlin.math.abs(2f * l - 1f))
        val h = when (max) {
            red -> 60f * (((green - blue) / d).mod(6f))
            green -> 60f * (((blue - red) / d) + 2f)
            else -> 60f * (((red - green) / d) + 4f)
        }
        return floatArrayOf(h, s.coerceIn(0f, 1f), l)
    }

    /**
     * A tone of [this] colour's hue at lightness [l] and saturation [s] —
     * the stand-in for picking tone 90 / tone 30 out of the seed's tonal
     * palette. Saturation is scaled down for low-chroma seeds (slate, ivory),
     * so a sober theme gets sober containers instead of a forced pastel.
     */
    private fun Color.tone(s: Float, l: Float): Color {
        val (h, srcS, _) = hsl()
        val chromaScale = (srcS / 0.35f).coerceIn(0f, 1f)
        return Color.hsl(h, (s * chromaScale).coerceIn(0f, 1f), l.coerceIn(0f, 1f))
    }

    @Composable
    private fun roles(): Roles {
        val scheme = MaterialTheme.colorScheme
        return Roles(
            canvas = scheme.surface,
            low = scheme.surfaceContainerLow,
            container = scheme.surfaceContainer,
            high = scheme.surfaceContainerHigh,
            highest = scheme.surfaceContainerHighest,
            accentSoft = scheme.primaryContainer,
            onAccentSoft = scheme.onPrimaryContainer,
            accentSofter = scheme.secondaryContainer,
            onAccentSofter = scheme.onSecondaryContainer,
            outline = scheme.outline
        )
    }

    // ------------------------------------------------------------------ motion

    /**
     * M3 Expressive's *fast spatial* spring (damping 0.6, stiffness 800): the
     * scheme Google's own apps use for a shape answering a finger. It is
     * allowed to overshoot — the corner settles, it does not slide.
     */
    private fun <T> fastSpatial() = spring<T>(dampingRatio = 0.6f, stiffness = 800f)

    /** The *default effects* spring: no bounce, for colour and opacity. */
    private fun <T> defaultEffects() = spring<T>(dampingRatio = 1f, stiffness = Spring.StiffnessMediumLow)

    /** Inner corner of a row inside a segmented list group. */
    private val GroupInnerCorner = 4.dp

    /** Outer corner of a segmented list group, and the gap between its rows. */
    private val GroupOuterCorner = 16.dp
    private val GroupGap = 2.dp

    /**
     * Set by [ListGroup]: a row inside a group is one segment of it (4dp inner
     * corners, 2dp gaps, the group rounding the ends) — the Expressive list
     * Android 16 Settings and the Google apps moved to — rather than a free
     * 16dp card floating 8dp from its neighbour.
     */
    private val LocalInListGroup = staticCompositionLocalOf { false }
    private val LocalInsideCard = staticCompositionLocalOf { false }

    /**
     * A corner radius that morphs on press: [rest] at rest, [pressed] while a
     * finger is down, on the fast spatial spring.
     */
    @Composable
    private fun pressMorph(interaction: MutableInteractionSource, enabled: Boolean, rest: Dp, pressed: Dp): Dp {
        val isPressed by interaction.collectIsPressedAsState()
        val corner by animateDpAsState(
            targetValue = if (enabled && isPressed) pressed else rest,
            animationSpec = fastSpatial(),
            label = "press_morph"
        )
        return corner.coerceAtLeast(0.dp)
    }

    /**
     * A stadium whose ends square off as [progress] goes to 1 — the Expressive
     * button's press morph. Computed from the measured height so the resting
     * shape is a true stadium at any button height.
     */
    private data class MorphPill(val progress: Float, val pressedRadius: Dp) : Shape {
        override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
            val full = size.minDimension / 2f
            val target = with(density) { pressedRadius.toPx() }
            val r = (full + (target - full) * progress).coerceIn(0f, full)
            return Outline.Rounded(RoundRect(0f, 0f, size.width, size.height, CornerRadius(r)))
        }
    }

    @Composable
    private fun buttonShape(interaction: MutableInteractionSource, enabled: Boolean): Shape {
        val isPressed by interaction.collectIsPressedAsState()
        val progress by animateFloatAsState(
            targetValue = if (enabled && isPressed) 1f else 0f,
            animationSpec = fastSpatial(),
            label = "button_morph"
        )
        return MorphPill(progress, 10.dp)
    }

    // -------------------------------------------------------------- primitives

    /**
     * The signature M3 list idiom: a glyph centred in a tonal circle. [size] is
     * the container, [glyph] the icon drawn inside it. 40/24 is the dimension
     * the list-item leading avatar is specified at.
     */
    @Composable
    private fun TonalIconCircle(
        icon: ImageVector,
        container: Color,
        tint: Color,
        size: Dp = 40.dp,
        glyph: Dp = 24.dp,
        modifier: Modifier = Modifier,
        shape: Shape = RoundedCornerShape(percent = 50)
    ) {
        Box(
            modifier.size(size).background(container, shape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(glyph))
        }
    }

    /**
     * Compact Expressive navigation: 64dp content, a 56×32 indicator and a
     * persistent label. At medium width icons and labels share one line.
     * Dimensions follow the official MDC BottomNavigation guide; touch and
     * selected semantics remain on the entire destination, not just its icon.
     */
    @Composable
    private fun DestinationBar(
        items: List<AppNavItem>,
        selectedIndex: Int,
        onSelect: (Int) -> Unit
    ) {
        val c = roles()
        val palette = LocalAppDesign.current.palette
        val labelStyle = LocalAppTextStyles.current.navLabel
        val measurer = rememberTextMeasurer()
        val density = LocalDensity.current
        val labelHeight = with(density) {
            (items.maxOfOrNull { measurer.measure(it.label, labelStyle, maxLines = 1).size.height } ?: 0).toDp()
        }
        Surface(color = c.container, shadowElevation = 0.dp) {
            BoxWithConstraints(Modifier.fillMaxWidth().navigationBarsPadding().testTag("bottom_nav_bar")) {
                val expanded = maxWidth >= 600.dp
                val barHeight = maxOf(64.dp, labelHeight + if (expanded) 24.dp else 48.dp)
                Row(
                    Modifier.fillMaxWidth().height(barHeight).selectableGroup()
                        .then(if (items.size > 5) Modifier.horizontalScroll(rememberScrollState()) else Modifier),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEachIndexed { index, item ->
                        val isSelected = index == selectedIndex
                        val indicator by androidx.compose.animation.animateColorAsState(
                            if (isSelected) c.accentSofter else Color.Transparent,
                            animationSpec = defaultEffects(), label = "destination_indicator"
                        )
                        val tint = if (isSelected) c.onAccentSofter else palette.textSecondary
                        val itemModifier = (if (items.size > 5) Modifier.widthIn(min = 80.dp) else Modifier.weight(1f))
                            .fillMaxHeight().testTag(item.tag)
                            .selectable(isSelected, onClick = { onSelect(index) }, role = Role.Tab)
                        if (expanded) {
                            Row(
                                itemModifier.padding(horizontal = 4.dp, vertical = 8.dp)
                                    .background(indicator, RoundedCornerShape(50)),
                                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(if (isSelected) item.selectedIcon else item.icon, null, tint = tint, modifier = Modifier.size(24.dp))
                                Text(item.label, style = labelStyle, color = tint, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                        } else {
                            Column(
                                itemModifier.padding(vertical = 6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                            ) {
                                Box(
                                    Modifier.size(width = 56.dp, height = 32.dp)
                                        .background(indicator, RoundedCornerShape(50)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(if (isSelected) item.selectedIcon else item.icon, null, tint = tint, modifier = Modifier.size(24.dp))
                                }
                                Text(
                                    item.label, style = labelStyle, color = tint, maxLines = 1,
                                    overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * A filled card at the M3 shape scale. Filled cards are specified at 0dp —
     * the tone step is the separation — so [elevation] defaults to nothing and
     * only a surface that genuinely floats ever passes anything else.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun FilledCard(
        modifier: Modifier,
        container: Color,
        shape: Shape,
        onClick: (() -> Unit)?,
        elevation: Dp = 0.dp,
        interactionSource: MutableInteractionSource? = null,
        content: @Composable ColumnScope.() -> Unit
    ) {
        val colors = CardDefaults.cardColors(containerColor = container)
        val cardElevation = CardDefaults.cardElevation(defaultElevation = elevation)
        if (onClick != null) {
            M3Card(
                onClick = onClick,
                modifier = modifier,
                shape = shape,
                colors = colors,
                elevation = cardElevation,
                interactionSource = interactionSource,
                content = content
            )
        } else {
            M3Card(
                modifier = modifier,
                shape = shape,
                colors = colors,
                elevation = cardElevation,
                content = content
            )
        }
    }

    // ------------------------------------------------------------------ chrome

    /** One screen's headline, as [ScreenTitle] hands it to the app bar. */
    private class TitleEntry {
        var title by mutableStateOf("")
        var subtitle by mutableStateOf<String?>(null)
        var trailingContent by mutableStateOf<(@Composable () -> Unit)?>(null)
    }

    /**
     * The headlines the composed screen has offered the app bar, newest last.
     * Set by [Scaffold]; a [ScreenTitle] outside one draws its own headline.
     */
    private class BarTitles {
        val entries = mutableStateListOf<TitleEntry>()
    }

    private val LocalBarTitles = staticCompositionLocalOf<BarTitles?> { null }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Scaffold(
        title: String,
        navItems: List<AppNavItem>,
        selectedNavIndex: Int,
        onNavSelected: (Int) -> Unit,
        onBack: (() -> Unit)?,
        backLabel: String?,
        actions: @Composable RowScope.() -> Unit,
        content: @Composable (PaddingValues) -> Unit
    ) {
        val c = roles()

        // The large flexible top app bar: the screen's headline lives *in*
        // the bar, under a row that holds only navigation and actions, and
        // collapses into the bar's title as content scrolls (exitUntilCollapsed,
        // snapping on the Expressive default spatial spring). One title per
        // screen, in the place every Google app puts it — not an empty bar
        // stacked on a 32sp headline that scrolls away with the content.
        // A fresh state per destination, so every screen opens expanded.
        val titles = remember(title) { BarTitles() }
        val barState = remember(title) { androidx.compose.material3.TopAppBarState(-Float.MAX_VALUE, 0f, 0f) }
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
            state = barState,
            snapAnimationSpec = spring(dampingRatio = 0.9f, stiffness = 700f)
        )
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing,
            containerColor = c.canvas,
            topBar = {
                // ScreenTitle supplies the translated headline. Actual text
                // measurement keeps the flexible bar responsive to font choice.
                FlexibleTopBar(
                    title = { titles.entries.lastOrNull()?.title?.takeIf { it.isNotBlank() } ?: title },
                    subtitle = { titles.entries.lastOrNull()?.subtitle?.takeIf { it.isNotBlank() } },
                    trailingContent = titles.entries.lastOrNull()?.trailingContent,
                    onBack = onBack,
                    backLabel = backLabel,
                    actions = actions,
                    state = barState,
                    canvas = c.canvas,
                    scrolled = c.container
                )
            },
            bottomBar = { DestinationBar(navItems, selectedNavIndex, onNavSelected) },
            content = { padding ->
                CompositionLocalProvider(LocalBarTitles provides titles) { content(padding) }
            }
        )
    }

    /**
     * A flexible headline with real text layout, not text painted onto a fixed
     * spacer. The expanded height follows the actual type scale and translated
     * title; a larger accessibility font therefore gets space rather than
     * silently losing its second line. An actionless root screen starts with
     * the headline, while pushed screens keep a full navigation row.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun FlexibleTopBar(
        title: () -> String,
        subtitle: () -> String?,
        trailingContent: (@Composable () -> Unit)?,
        onBack: (() -> Unit)?,
        backLabel: String?,
        actions: @Composable RowScope.() -> Unit,
        state: androidx.compose.material3.TopAppBarState,
        canvas: Color,
        scrolled: Color
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val displayTitle = title()
        val supporting = subtitle()?.takeIf { it.isNotBlank() }
        val fraction = state.collapsedFraction.coerceIn(0f, 1f)
        val container = androidx.compose.ui.graphics.lerp(canvas, scrolled, fraction)
        Surface(color = container, contentColor = palette.textPrimary) {
            val actionsWidth = remember { intArrayOf(0) }
            Layout(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .clipToBounds(),
                content = {
                    Row(
                        Modifier.fillMaxWidth().height(64.dp).padding(horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (onBack != null) {
                            IconButton(onClick = onBack, modifier = Modifier.testTag("nav_back")) {
                                Icon(
                                    Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = backLabel ?: "Back",
                                    tint = palette.textPrimary
                                )
                            }
                        } else {
                            Spacer(Modifier.width(12.dp))
                        }
                        Text(
                            text = displayTitle,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f).padding(start = 4.dp)
                                .graphicsLayer { alpha = ((fraction - 0.55f) / 0.45f).coerceIn(0f, 1f) }
                                .then(if (fraction < 0.55f) Modifier.clearAndSetSemantics {} else Modifier)
                        )
                        CompositionLocalProvider(
                            androidx.compose.material3.LocalContentColor provides palette.textSecondary
                        ) {
                            Row(
                                Modifier.layout { measurable, constraints ->
                                    val placeable = measurable.measure(constraints)
                                    actionsWidth[0] = placeable.width
                                    layout(placeable.width, placeable.height) { placeable.place(0, 0) }
                                },
                                verticalAlignment = Alignment.CenterVertically,
                                content = actions
                            )
                        }
                    }
                    Row(
                        Modifier.fillMaxWidth()
                            .padding(horizontal = design.spacing.gutter)
                            .padding(top = 8.dp, bottom = if (trailingContent == null) 24.dp else 16.dp)
                            .graphicsLayer { alpha = (1f - fraction * 1.7f).coerceIn(0f, 1f) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = displayTitle,
                                style = styles.screenTitle,
                                color = palette.textPrimary,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.then(
                                    if (fraction < 0.6f) Modifier.semantics { heading() }
                                    else Modifier.clearAndSetSemantics {}
                                )
                            )
                            if (supporting != null) {
                                Text(
                                    text = supporting,
                                    style = styles.screenSubtitle,
                                    color = palette.textSecondary,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        trailingContent?.invoke()
                    }
                }
            ) { measurables, constraints ->
                val loose = constraints.copy(minHeight = 0, maxHeight = androidx.compose.ui.unit.Constraints.Infinity)
                val pinned = measurables[0].measure(loose)
                val expanded = measurables[1].measure(loose)
                // With no navigation or actions there is no empty toolbar to
                // display. Its headline rises into that space, as in Google's
                // first-party large-title surfaces, and collapses to 64dp.
                val overlap = if (onBack == null && actionsWidth[0] == 0) 44.dp.roundToPx() else 0
                val collapsible = (expanded.height - overlap).coerceAtLeast(1)
                val offsetLimit = -collapsible.toFloat()
                if (state.heightOffsetLimit != offsetLimit) state.heightOffsetLimit = offsetLimit
                val visible = (collapsible + state.heightOffset).toInt().coerceIn(0, collapsible)
                val height = pinned.height + visible
                layout(constraints.maxWidth, height) {
                    expanded.placeRelative(0, height - expanded.height)
                    pinned.placeRelative(0, 0)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun TopBar(
        title: String,
        subtitle: String?,
        onBack: (() -> Unit)?,
        actions: @Composable RowScope.() -> Unit
    ) {
        val palette = LocalAppDesign.current.palette
        val c = roles()
        TopAppBar(
            title = {
                // 1.3.0's app bars have no subtitle slot, so the two lines sit
                // in the title slot together, start-aligned like the bar.
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            },
            navigationIcon = {
                if (onBack != null) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            },
            actions = actions,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = c.canvas,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }

    @Composable
    override fun ScreenTitle(title: String, subtitle: String?, modifier: Modifier, trailingContent: (@Composable () -> Unit)?) {
        val holder = LocalBarTitles.current
        if (holder != null) {
            // Under the scaffold the headline is the app bar's: hand it up
            // and draw nothing here, so there is one title per screen. An
            // entry that scrolls out of a lazy list stays the bar's title
            // until another screen-level headline replaces it.
            val entry = remember { TitleEntry() }
            SideEffect {
                entry.title = title
                entry.subtitle = subtitle
                entry.trailingContent = trailingContent
            }
            DisposableEffect(holder, entry) {
                holder.entries.add(entry)
                onDispose { if (holder.entries.size > 1) holder.entries.remove(entry) }
            }
            // The app bar owns both the visible headline and its heading
            // semantics. Keeping another named spacer here would announce
            // the same screen twice to a screen reader.
            // Lazy lists may discard a zero-height header before its effects
            // register with the bar. Keep a real layout participant.
            Spacer(modifier.fillMaxWidth().height(1.dp))
            return
        }
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier.weight(1f)
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = design.spacing.headerTopGap, bottom = 16.dp)
            ) {
                Text(text = title, style = styles.screenTitle, color = palette.textPrimary)
                if (subtitle != null) {
                    Spacer(Modifier.height(4.dp))
                    Text(text = subtitle, style = styles.screenSubtitle, color = palette.textSecondary)
                }
            }
            trailingContent?.invoke()
        }
    }

    // --------------------------------------------------------------- structure

    @Composable
    override fun SectionHeader(title: String, subtitle: String?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        // The Settings / Calendar subheader: titleSmall in primary, set in
        // from the gutter onto the same edge as the text inside the group
        // beneath it, 24dp of air above and 8 below. A title-large header in
        // onSurface was a second headline competing with the app bar's; this
        // labels a group and gets out of the way. A short count ("5 books")
        // rides at the trailing end on the same line rather than costing a
        // line of its own; a longer description hangs under it in bodyMedium.
        val shortAside = subtitle != null && subtitle.length <= 18 && !subtitle.contains('.')
        val inCard = LocalInsideCard.current
        Column(
            modifier.fillMaxWidth().then(
                if (inCard) Modifier.padding(bottom = 8.dp)
                else Modifier.padding(horizontal = design.spacing.gutter)
                    .padding(top = 16.dp, bottom = 8.dp)
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                if (shortAside) {
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = subtitle!!,
                        style = MaterialTheme.typography.labelMedium,
                        color = palette.textSecondary,
                        maxLines = 1
                    )
                }
            }
            if (subtitle != null && !shortAside) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = palette.textSecondary
                )
            }
        }
    }

    @Composable
    override fun Card(
        modifier: Modifier,
        onClick: (() -> Unit)?,
        content: @Composable ColumnScope.() -> Unit
    ) {
        val design = LocalAppDesign.current
        val c = roles()
        val inGroup = LocalInListGroup.current
        val interaction = remember { MutableInteractionSource() }
        val rest = if (inGroup) GroupInnerCorner else 12.dp
        val corner = pressMorph(interaction, onClick != null, rest, rest + 12.dp)
        FilledCard(
            modifier = modifier.fillMaxWidth(),
            container = c.highest,
            shape = RoundedCornerShape(corner),
            onClick = onClick,
            interactionSource = interaction
        ) {
            Column(Modifier.padding(design.spacing.cardPadding)) {
                val scope = this
                CompositionLocalProvider(LocalInsideCard provides true) { scope.content() }
            }
        }
    }

    @Composable
    override fun ListGroup(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) {
        val spacing = LocalAppDesign.current.spacing
        // The Expressive segmented list: each row keeps its own surface, but
        // the rows sit 2dp apart with 4dp inner corners and the group rounds
        // its two ends, so a run of rows reads as one set and a section break
        // reads as a break. Free 16dp cards 8dp apart made every list on every
        // screen one undifferentiated stack.
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.gutter)
                .clip(RoundedCornerShape(GroupOuterCorner)),
            verticalArrangement = Arrangement.spacedBy(GroupGap)
        ) {
            val scope = this
            CompositionLocalProvider(LocalInListGroup provides true) { scope.content() }
        }
    }

    @Composable
    override fun Row(
        title: String,
        subtitle: String?,
        leadingIcon: ImageVector?,
        trailingText: String?,
        showsNavigationAffordance: Boolean,
        selected: Boolean,
        onClick: (() -> Unit)?,
        modifier: Modifier
    ) {
        val design = LocalAppDesign.current
        val styles = LocalAppTextStyles.current
        val c = roles()
        val container = if (selected) c.accentSofter else c.container
        val foreground = if (selected) c.onAccentSofter else design.palette.textPrimary
        val secondary = if (selected) c.onAccentSofter else design.palette.textSecondary
        FilledCard(
            modifier = modifier.fillMaxWidth().then(
                if (selected) Modifier.semantics { this.selected = true } else Modifier
            ),
            container = container,
            shape = if (LocalInListGroup.current) RoundedCornerShape(GroupInnerCorner) else design.shapes.card,
            onClick = onClick
        ) {
            androidx.compose.material3.ListItem(
                headlineContent = {
                    Text(title, style = styles.rowTitle, color = foreground, maxLines = 2, overflow = TextOverflow.Ellipsis)
                },
                supportingContent = if (!subtitle.isNullOrBlank()) {
                    { Text(subtitle, style = styles.rowSubtitle, color = secondary, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                } else null,
                leadingContent = if (leadingIcon != null) {
                    { Icon(leadingIcon, contentDescription = null, tint = foreground, modifier = Modifier.size(24.dp)) }
                } else null,
                trailingContent = if (trailingText != null || (showsNavigationAffordance && onClick != null)) {
                    {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            trailingText?.let {
                                Text(
                                    it, style = MaterialTheme.typography.bodyMedium, color = secondary,
                                    maxLines = 2, overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.End, modifier = Modifier.widthIn(max = 104.dp)
                                )
                            }
                            if (showsNavigationAffordance && onClick != null) {
                                Icon(
                                    Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                    contentDescription = null, tint = secondary, modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                } else null,
                colors = androidx.compose.material3.ListItemDefaults.colors(containerColor = container)
            )
        }
    }

    @Composable
    override fun Divider(modifier: Modifier) {
        // Inside a segmented group the 2dp gap already is the separator; a
        // rule on top of it would count the break twice.
        if (LocalInListGroup.current) return
        HorizontalDivider(
            modifier = modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }

    @Composable
    override fun Ornament(modifier: Modifier) {
        // Material has no ornament; the section break is pure space.
        Spacer(modifier.fillMaxWidth().height(LocalAppDesign.current.spacing.sectionGap))
    }

    // ------------------------------------------------------------------ inputs

    @Composable
    override fun PrimaryButton(
        label: String,
        onClick: () -> Unit,
        enabled: Boolean,
        leadingIcon: ImageVector?,
        modifier: Modifier
    ) {
        val scheme = MaterialTheme.colorScheme
        val interaction = remember { MutableInteractionSource() }
        Button(
            onClick = onClick,
            modifier = modifier.heightIn(min = 48.dp),
            enabled = enabled,
            shape = buttonShape(interaction, enabled),
            interactionSource = interaction,
            colors = ButtonDefaults.buttonColors(
                containerColor = scheme.primary,
                contentColor = scheme.onPrimary,
                disabledContainerColor = scheme.onSurface.copy(alpha = 0.12f),
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f)
            ),
            // 24dp ends, 16dp before the label when a button leads with an
            // icon: the spec's own button insets.
            contentPadding = PaddingValues(
                start = if (leadingIcon != null) 16.dp else 24.dp,
                end = 24.dp,
                top = 10.dp,
                bottom = 10.dp
            )
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text = label, style = MaterialTheme.typography.labelLarge, maxLines = 2, textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis)
        }
    }

    @Composable
    override fun SecondaryButton(
        label: String,
        onClick: () -> Unit,
        enabled: Boolean,
        leadingIcon: ImageVector?,
        modifier: Modifier
    ) {
        val c = roles()
        val scheme = MaterialTheme.colorScheme
        val interaction = remember { MutableInteractionSource() }
        FilledTonalButton(
            onClick = onClick,
            modifier = modifier.heightIn(min = 48.dp),
            enabled = enabled,
            shape = buttonShape(interaction, enabled),
            interactionSource = interaction,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = c.accentSofter,
                contentColor = c.onAccentSofter,
                disabledContainerColor = scheme.onSurface.copy(alpha = 0.12f),
                disabledContentColor = scheme.onSurface.copy(alpha = 0.38f)
            ),
            contentPadding = PaddingValues(
                start = if (leadingIcon != null) 16.dp else 24.dp,
                end = 24.dp,
                top = 10.dp,
                bottom = 10.dp
            )
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text = label, style = MaterialTheme.typography.labelLarge, maxLines = 2, textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis)
        }
    }

    @Composable
    override fun SearchField(
        value: String,
        onValueChange: (String) -> Unit,
        placeholder: String,
        modifier: Modifier
    ) {
        val palette = LocalAppDesign.current.palette
        val c = roles()
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                // The docked search field is 56dp. Nothing else in M3 is this
                // tall, which is exactly what makes it read as search.
                .heightIn(min = 56.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = palette.textSecondary,
                    maxLines = 1
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp).size(24.dp)
                )
            },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = "Clear",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            // Search is a stadium in M3, not a rounded rectangle — shapes.field
            // (16dp) is the text-field shape, and using it here is the single
            // loudest "not a Google app" signal the screen gives off.
            shape = RoundedCornerShape(percent = 50),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = c.high,
                unfocusedContainerColor = c.container,
                disabledContainerColor = c.container,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = c.outline,
                disabledIndicatorColor = c.outline,
                focusedTextColor = palette.textPrimary,
                unfocusedTextColor = palette.textPrimary,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun SegmentedTabs(
        options: List<String>,
        selectedIndex: Int,
        onSelect: (Int) -> Unit,
        modifier: Modifier
    ) {
        val c = roles()
        val design = LocalAppDesign.current
        val palette = design.palette
        val scheme = MaterialTheme.colorScheme
        // Segmented buttons are for short labels: the spec's own examples are
        // one or two words, and a segment never truncates. Measure each label
        // against the width its segment would actually get (minus the 12dp
        // side insets and, for the selected one, the 18+8dp checkmark). If any
        // would be cut, or there are more than five, the set is not a choice
        // but a set of peer views — and M3 draws peer views as scrollable
        // primary tabs (Play Store, Files, Clock), not as filter chips, which
        // say "narrow this list" and stacked a second chip row on top of the
        // real filters on half the screens.
        val measurer = rememberTextMeasurer()
        val labelStyle = MaterialTheme.typography.labelLarge
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        BoxWithConstraints(modifier.fillMaxWidth()) {
            // Callers disagree on whether they inset this control (Rosary
            // passes the full width, Bible passes the gutter). Read it off the
            // constraints instead of trusting either.
            val sideInset = ((screenWidth - maxWidth) / 2).coerceAtLeast(0.dp)
            val ownGutter = if (sideInset < 1.dp) design.spacing.gutter else 0.dp
            val segment = (maxWidth - ownGutter * 2) / options.size.coerceAtLeast(1)
            val room = segment - 24.dp - 26.dp
            val widest = with(LocalDensity.current) {
                options.maxOfOrNull { measurer.measure(it, labelStyle, maxLines = 1).size.width.toDp() } ?: 0.dp
            }
            // Short values (2 km … 50 km) that only fail for the checkmark's
            // sake stay a segmented button and drop the checkmark, as the
            // spec allows when space is tight: a radius is a choice, and
            // drawing it as navigation tabs was a component misuse.
            // Only for a dense set of four or five values: two or three
            // long-ish labels are peer views, and those stay tabs.
            // Round 5: a segmented button is a two-way value or view switch
            // (Rosary / Divine Mercy, Calendar / Shrines) and always carries
            // its checkmark. Three or more peers are destinations, and M3
            // draws those as primary tabs — a four-segment pill as section
            // navigation (Bible, Spiritual Life) was the single most-cited
            // component misuse. A run of numeric values (2 km … 50 km) is
            // neither: it is a single-select filter, drawn as the Maps / Play
            // chip row, checkmark on the chosen one.
            val valueSet = options.size >= 3 && options.all { o -> o.any { it.isDigit() } }
            val showCheck = widest <= room
            val fits = options.size <= 2 && widest <= segment - 24.dp
            if (valueSet) {
                androidx.compose.foundation.layout.Row(
                    Modifier
                        .fillMaxWidth()
                        .layout { measurable, constraints ->
                            val extra = (sideInset * 2).roundToPx()
                            val placeable = measurable.measure(
                                constraints.copy(
                                    minWidth = constraints.maxWidth + extra,
                                    maxWidth = constraints.maxWidth + extra
                                )
                            )
                            layout(constraints.maxWidth, placeable.height) {
                                placeable.placeRelative(-sideInset.roundToPx(), 0)
                            }
                        }
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = design.spacing.gutter),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    options.forEachIndexed { index, option ->
                        Chip(option, index == selectedIndex, { onSelect(index) }, Modifier)
                    }
                }
            } else if (!fits) {
                val bleed = sideInset
                val selected = selectedIndex.coerceIn(0, (options.size - 1).coerceAtLeast(0))
                // Fixed tabs when every label fits an equal share of the
                // glass (Bible's four), scrollable only when they cannot.
                val fixedTabs = options.size <= 4 && widest + 32.dp <= screenWidth / options.size
                val indicator: @Composable androidx.compose.material3.TabIndicatorScope.() -> Unit = {
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(selected, matchContentSize = true),
                        width = Dp.Unspecified,
                        height = 3.dp,
                        color = scheme.primary,
                        shape = RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp)
                    )
                }
                val tabs: @Composable () -> Unit = {
                    options.forEachIndexed { index, option ->
                        Tab(
                            selected = index == selected,
                            onClick = { onSelect(index) },
                            selectedContentColor = scheme.primary,
                            unselectedContentColor = palette.textSecondary,
                            text = {
                                Text(
                                    text = option,
                                    style = MaterialTheme.typography.titleSmall,
                                    maxLines = 1
                                )
                            }
                        )
                    }
                }
                // Tabs run glass to glass: bleed back out over whatever inset
                // the caller applied. The divider is drawn here, under the
                // whole width — the scrollable row's own divider stops where
                // its last tab does.
                Box(
                    Modifier.layout { measurable, constraints ->
                        val extra = (bleed * 2).roundToPx()
                        val placeable = measurable.measure(
                            constraints.copy(
                                minWidth = constraints.maxWidth + extra,
                                maxWidth = constraints.maxWidth + extra
                            )
                        )
                        layout(constraints.maxWidth, placeable.height) {
                            placeable.placeRelative(-bleed.roundToPx(), 0)
                        }
                    }
                ) {
                    // Under the indicator, so the indicator draws over it.
                    HorizontalDivider(
                        modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    if (fixedTabs) {
                        PrimaryTabRow(
                            selectedTabIndex = selected,
                            modifier = Modifier.fillMaxWidth(),
                            containerColor = Color.Transparent,
                            contentColor = scheme.primary,
                            indicator = indicator,
                            divider = {},
                            tabs = tabs
                        )
                    } else {
                        ScrollingTabs(options, selected, onSelect)
                    }
                }
            } else {
                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = ownGutter)
                ) {
                    options.forEachIndexed { index, option ->
                        val isSelected = index == selectedIndex
                        SegmentedButton(
                            selected = isSelected,
                            onClick = { onSelect(index) },
                            shape = SegmentedButtonDefaults.itemShape(index, options.size),
                            modifier = Modifier.weight(1f),
                            icon = { if (showCheck) SegmentedButtonDefaults.Icon(isSelected) },
                            // Dark: the selected segment steps up to the
                            // primary container — secondaryContainer at tone 25
                            // was a dull smudge barely off the canvas.
                            colors = SegmentedButtonDefaults.colors(
                                activeContainerColor = if (design.isDark) c.accentSoft else c.accentSofter,
                                activeContentColor = if (design.isDark) c.onAccentSoft else c.onAccentSofter,
                                activeBorderColor = c.outline,
                                inactiveContainerColor = Color.Transparent,
                                inactiveContentColor = palette.textSecondary,
                                inactiveBorderColor = c.outline
                            ),
                            label = {
                                Text(
                                    text = option,
                                    style = MaterialTheme.typography.labelLarge,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    /**
     * Scrollable primary tabs, drawn by hand. The stock row animates its
     * scroll to the selected tab on first composition, so every screen that
     * opens on a later tab slid its strip across on entry — and a capture
     * caught the selected tab off the glass. This one arrives already showing
     * its selection (centred, as the stock row centres it) and only moves on
     * a change, with the indicator travelling on the default spatial spring.
     */
    @Composable
    private fun ScrollingTabs(options: List<String>, selected: Int, onSelect: (Int) -> Unit) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val scheme = MaterialTheme.colorScheme
        val density = LocalDensity.current
        // Lay the strip out once at composition, from the measured labels
        // (a tab is its label plus 16dp each side), so the very first frame
        // already shows the selected tab centred with its indicator under it.
        // The layout pass below then corrects any sub-pixel difference.
        val measurer = rememberTextMeasurer()
        val labelStyle = MaterialTheme.typography.titleSmall
        val viewportPx = with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
        val initial = remember(options, selected, labelStyle) {
            val pad = with(density) { 16.dp.toPx() }
            val edge = with(density) { (design.spacing.gutter - 16.dp).coerceAtLeast(0.dp).toPx() }
            val textWidths = options.map { measurer.measure(it, labelStyle, maxLines = 1).size.width.toFloat() }
            var x = edge
            val tabLefts = textWidths.map { w -> x.also { x += w + pad * 2 } }
            val content = x + edge
            val i = selected.coerceIn(0, options.size - 1)
            val centre = (tabLefts[i] + (textWidths[i] + pad * 2) / 2f - viewportPx / 2f)
                .coerceIn(0f, (content - viewportPx).coerceAtLeast(0f))
            Triple(tabLefts[i] + pad, textWidths[i], centre.toInt())
        }
        val scroll = rememberScrollState(initial.third)
        val rowCoords = remember(options) { arrayOfNulls<androidx.compose.ui.layout.LayoutCoordinates>(1) }
        val tabCoords = remember(options) { arrayOfNulls<androidx.compose.ui.layout.LayoutCoordinates>(options.size) }
        val textCoords = remember(options) { arrayOfNulls<androidx.compose.ui.layout.LayoutCoordinates>(options.size) }
        val left = remember(options) { androidx.compose.animation.core.Animatable(initial.first) }
        val width = remember(options) { androidx.compose.animation.core.Animatable(initial.second) }
        val firstRun = remember(options) { booleanArrayOf(true) }

        LaunchedEffect(selected, options) {
            snapshotFlow { scroll.viewportSize }.first { it > 0 }
            val row = rowCoords[0] ?: return@LaunchedEffect
            val tab = tabCoords.getOrNull(selected) ?: return@LaunchedEffect
            val text = textCoords.getOrNull(selected) ?: return@LaunchedEffect
            val textLeft = row.localPositionOf(text, androidx.compose.ui.geometry.Offset.Zero).x
            val textWidth = text.size.width.toFloat()
            val tabLeft = row.localPositionOf(tab, androidx.compose.ui.geometry.Offset.Zero).x
            val centre = (tabLeft + tab.size.width / 2f - scroll.viewportSize / 2f)
                .toInt().coerceIn(0, scroll.maxValue)
            if (firstRun[0]) {
                left.snapTo(textLeft)
                width.snapTo(textWidth)
                scroll.scrollTo(centre)
                firstRun[0] = false
            } else {
                val spatial = spring<Float>(dampingRatio = 0.8f, stiffness = 380f)
                kotlinx.coroutines.coroutineScope {
                    launch { left.animateTo(textLeft, spatial) }
                    launch { width.animateTo(textWidth, spatial) }
                    launch { scroll.animateScrollTo(centre, spring(dampingRatio = 1f, stiffness = 380f)) }
                }
            }
        }

        Box(Modifier.fillMaxWidth().horizontalScroll(scroll)) {
            Row(
                Modifier
                    .onPlaced { rowCoords[0] = it }
                    .padding(horizontal = (design.spacing.gutter - 16.dp).coerceAtLeast(0.dp))
            ) {
                options.forEachIndexed { index, option ->
                    Tab(
                        selected = index == selected,
                        onClick = { onSelect(index) },
                        modifier = Modifier.onPlaced { tabCoords[index] = it },
                        selectedContentColor = scheme.primary,
                        unselectedContentColor = palette.textSecondary,
                        text = {
                            Text(
                                text = option,
                                style = MaterialTheme.typography.titleSmall,
                                maxLines = 1,
                                modifier = Modifier.onPlaced { textCoords[index] = it }
                            )
                        }
                    )
                }
            }
            Box(
                Modifier
                    .align(Alignment.BottomStart)
                    .layout { measurable, _ ->
                        val w = width.value.toInt().coerceAtLeast(0)
                        val placeable = measurable.measure(
                            androidx.compose.ui.unit.Constraints.fixed(w, with(density) { 3.dp.roundToPx() })
                        )
                        layout(w, placeable.height) { placeable.place(left.value.toInt(), 0) }
                    }
                    .background(scheme.primary, RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Chip(label: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val c = roles()
        FilterChip(
            selected = selected,
            onClick = onClick,
            label = { Text(text = label, style = MaterialTheme.typography.labelLarge, maxLines = 2, textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis) },
            modifier = modifier,
            // The selected filter chip carries its checkmark: without it a
            // selected chip is only a fill change, which is the input-chip
            // idiom, not the filter one.
            leadingIcon = if (selected) {
                {
                    Icon(
                        Icons.Rounded.Check,
                        contentDescription = null,
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
            shape = design.shapes.chip,
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color.Transparent,
                labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                selectedContainerColor = if (design.isDark) c.accentSoft else c.accentSofter,
                selectedLabelColor = if (design.isDark) c.onAccentSoft else c.onAccentSofter,
                selectedLeadingIconColor = if (design.isDark) c.onAccentSoft else c.onAccentSofter
            ),
            border = FilterChipDefaults.filterChipBorder(
                enabled = true,
                selected = selected,
                borderColor = c.outline,
                selectedBorderColor = c.outline
            )
        )
    }

    // ----------------------------------------------------------------- content

    @Composable
    override fun VerseRow(
        number: Int,
        text: String,
        isFirstOfChapter: Boolean,
        hasCommentary: Boolean,
        onClick: (() -> Unit)?,
        modifier: Modifier
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val c = roles()

        Row(
            modifier
                .fillMaxWidth()
                .widthIn(max = design.spacing.readingMaxWidth)
                .then(if (onClick != null) Modifier.clickable(role = Role.Button, onClick = onClick) else Modifier)
                // decor.usesDropCaps is false, so a chapter opens with air
                // rather than with an initial.
                .padding(top = if (isFirstOfChapter) 16.dp else 6.dp)
                .padding(start = design.spacing.gutter, end = design.spacing.gutter, bottom = 6.dp),
            verticalAlignment = Alignment.Top
        ) {
            // A quiet number column keeps the verse text easy to scan.
            Box(
                Modifier
                    .padding(top = 3.dp)
                    .size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$number",
                    style = styles.verseNumber,
                    color = palette.textSecondary,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = text,
                style = styles.scripture,
                color = palette.textPrimary,
                modifier = Modifier.weight(1f)
            )
            if (hasCommentary) {
                Spacer(Modifier.width(8.dp))
                Icon(
                    Icons.Rounded.AutoAwesome,
                    contentDescription = "Has commentary",
                    tint = MaterialTheme.colorScheme.primary,
                    // 6dp drops the 16dp glyph onto the optical centre of the
                    // 28sp first line rather than its ascender.
                    modifier = Modifier.padding(top = 6.dp).size(16.dp)
                )
            }
        }
    }

    @Composable
    override fun MetricTile(value: String, caption: String, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val c = roles()
        FilledCard(
            modifier = modifier,
            // Metrics step to surfaceContainerHigh while list segments remain
            // on surfaceContainer, giving values a useful tonal hierarchy.
            container = c.high,
            shape = design.shapes.card,
            onClick = null
        ) {
            // The tile runs on a fixed grid so three of them in a row share
            // every horizontal edge: 16 inset, 32dp disc, 12, the number, 4,
            // a caption held at two lines whether or not it wraps. Without
            // the two-line floor a one-word caption lifts its tile's baseline
            // out of line with its neighbours'.
            Column(Modifier.padding(16.dp)) {
                if (icon != null) {
                    TonalIconCircle(
                        icon = icon,
                        container = c.accentSoft,
                        tint = c.onAccentSoft,
                        size = 36.dp,
                        glyph = 18.dp
                    )
                    Spacer(Modifier.height(12.dp))
                }
                Text(
                    text = value,
                    // headlineMedium, not displaySmall: at a third of the width
                    // a 36sp figure cannot hold "1,328" and the number clips
                    // mid-digit. 28sp fits four figures and a separator with
                    // room to spare, and is what a stat tile is set in anyway.
                    style = MaterialTheme.typography.headlineMedium,
                    color = palette.textPrimary,
                    maxLines = 1,
                    softWrap = false
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = caption,
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.textSecondary,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    @Composable
    override fun EmptyState(title: String, body: String?, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val c = roles()
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter, vertical = design.spacing.sectionGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // The glyph sits in the same tonal disc the rest of the identity
            // uses, at hero size. A bare 48dp outline floating on the canvas is
            // the giveaway that an empty state was an afterthought.
            TonalIconCircle(
                icon = icon ?: Icons.Rounded.Inbox,
                container = c.accentSoft,
                tint = c.onAccentSoft,
                size = 56.dp,
                glyph = 28.dp
            )
            Spacer(Modifier.height(24.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = palette.textPrimary,
                textAlign = TextAlign.Center
            )
            if (body != null) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = palette.textSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.widthIn(max = 320.dp)
                )
            }
        }
    }

    /**
     * Material Color Utilities' `harmonize`: turns a fixed colour's hue
     * toward the scheme's primary by half the difference, at most 15°, so a
     * liturgical violet or gold sits in the same family as the seed instead
     * of arriving as an off-scheme lilac or a muddy ochre.
     */
    private fun Color.harmonizedWith(primary: Color): Color {
        val (h, sat, l) = hsl()
        val (ph, ps, _) = primary.hsl()
        if (sat < 0.05f || ps < 0.05f) return this
        // Gold is the exception. Any turn toward a green seed — and even its
        // own hue struck at a container or seal tone — reads as olive, so
        // it is pinned to amber, where its tones stay gold at every lightness.
        if (h in 35f..70f) return Color.hsl(40f, sat, l, alpha)
        val diff = ((ph - h + 540f) % 360f) - 180f
        // MCU allows 15°; at that a liturgical gold turned olive beside a
        // green seed, so the turn is held to 8°: enough to share the
        // scheme's temperature, not enough to change the colour's name.
        val shift = (diff * 0.5f).coerceIn(-8f, 8f)
        return Color.hsl(((h + shift) % 360f + 360f) % 360f, sat, l, alpha)
    }

    @Composable
    override fun Banner(
        title: String,
        body: String?,
        accentColorHex: Long?,
        onClick: (() -> Unit)?,
        modifier: Modifier
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val c = roles()
        val primary = MaterialTheme.colorScheme.primary
        // The celebration has a meaningful custom colour, harmonized into
        // the scheme. Give that information a tonal field; sacred artwork is
        // composed by the screen rather than repeated as decorative seals.
        val given = accentColorHex?.let { Color(it).harmonizedWith(primary) }
        val container = when {
            given == null -> c.accentSoft
            design.isDark -> given.tone(0.30f, 0.20f)
            else -> given.tone(0.34f, 0.92f)
        }
        val accent = (given ?: primary).readableOn(container)
        val headlineColor = palette.textPrimary.readableOn(container, target = 7f)
        val supportingColor = palette.textSecondary.readableOn(container)
        val headline = body?.substringBefore('\n')?.takeIf { it.isNotBlank() }
        val supporting = body?.substringAfter('\n', "")?.takeIf { it.isNotBlank() }
        FilledCard(
            modifier = modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
            container = container,
            shape = design.shapes.dialog,
            onClick = onClick
        ) {
            Row(
                Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                if (given != null) {
                    Box(Modifier.padding(top = 5.dp).size(8.dp).background(accent, RoundedCornerShape(50)))
                }
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = title,
                        style = if (headline == null) MaterialTheme.typography.titleMedium else MaterialTheme.typography.labelLarge,
                        color = if (headline == null) headlineColor else accent
                    )
                    headline?.let {
                        Text(
                            it,
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium),
                            color = headlineColor
                        )
                    }
                    supporting?.let {
                        Text(it, style = MaterialTheme.typography.bodyMedium, color = supportingColor)
                    }
                }
            }
        }
    }
}
