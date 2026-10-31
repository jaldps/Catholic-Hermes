package com.example.ui.design

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.text.drawText
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.contentDescription
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
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
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
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
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
 * ### Why the colour is computed here rather than read from the scheme
 *
 * The shared palette hands Material a *baseline-purple* neutral family
 * (`raised` #F3EDF7, `accentMuted` #EADDFF, `selectionTint` #E8DEF8) while
 * `primary` is the app's liturgical accent. That is not what M3 does: in a
 * seeded scheme every neutral is struck from the seed at low chroma and every
 * container is struck from the seed's tonal palette, so surfaces and accent are
 * always the same family. Reading `primaryContainer`/`secondaryContainer`
 * straight out of the scheme is what produced green glyphs sitting in lilac
 * circles. So the four roles this identity leans on hardest — the surface
 * ladder, the two tonal container tints and the outline — are derived from
 * `primary` here, the way a seeded scheme would derive them. The palette tokens
 * themselves want the same fix; see the report.
 */
object MaterialKit : AppKit {

    // ------------------------------------------------------------------ colour

    /**
     * The roles this identity actually paints with. Everything is derived from
     * the canvas lightness and the accent hue, so the surfaces, the tonal
     * circles and the accent all belong to one tonal family.
     */
    @Immutable
    private data class Roles(
        /** Background / top app bar, M3 `surface` at tone 98 (light) / 6 (dark). */
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
        /** `outlineVariant`: dividers and the chip/segment hairline. */
        val outline: Color
    )

    private fun Color.luma(): Float = 0.2126f * red + 0.7152f * green + 0.0722f * blue

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

    /** Strips a colour of its hue, keeping only its lightness. */
    private fun Color.neutralised(): Color = luma().let { Color(it, it, it, alpha) }

    /**
     * Pushes a colour along [hue]'s chroma direction without moving its
     * lightness — the cheap stand-in for "neutral palette at the seed's hue,
     * chroma 4" that a real M3 scheme generator produces.
     */
    private fun Color.tintedBy(hue: Color, amount: Float): Color {
        val l = hue.luma()
        return Color(
            (red + (hue.red - l) * amount).coerceIn(0f, 1f),
            (green + (hue.green - l) * amount).coerceIn(0f, 1f),
            (blue + (hue.blue - l) * amount).coerceIn(0f, 1f),
            alpha
        )
    }

    private fun Color.step(ink: Color, alpha: Float): Color =
        ink.copy(alpha = alpha).compositeOver(this)

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
        val design = LocalAppDesign.current
        val dark = design.isDark
        val accent = MaterialTheme.colorScheme.primary
        val ink = design.palette.textPrimary

        // The canvas: the identity's own lightness, re-struck at the accent's
        // hue. Light sits at M3's tone 98 (above every container) rather than at
        // the token's tone 96, so cards can step *down* and still stay bright.
        val canvas = if (dark) {
            design.palette.canvas.neutralised().tintedBy(accent, 0.10f)
        } else {
            Color(0.976f, 0.976f, 0.976f).tintedBy(accent, 0.10f)
        }

        // The surfaceContainer ladder. The alphas reproduce M3's own tone steps
        // (98→96→94→92→90 light, 6→10→12→17→22 dark) measured off the baseline
        // scheme, so four distinct steps are visible in both modes with no
        // shadow doing any of the work.
        // Dark runs a deeper ladder than the baseline (≈6→11→14→19→24): at
        // M3's own 10/12 steps a filled card on a phone at night was nearly
        // indistinguishable from the surface it sat on.
        val a = if (dark) floatArrayOf(0.055f, 0.088f, 0.135f, 0.185f)
        else floatArrayOf(0.031f, 0.050f, 0.082f, 0.109f)

        val high = canvas.step(ink, a[2])
        // The two containers are struck as tones of the accent's hue, the
        // way a seeded scheme picks tone 90 (light) / 30 (dark) out of the
        // primary and secondary palettes. Compositing the accent at low alpha
        // over a grey canvas — what this did before — lands on a desaturated
        // sage that reads as "disabled", not as "container".
        val accentSoft = if (dark) accent.tone(0.40f, 0.24f) else accent.tone(0.50f, 0.85f)
        val accentSofter = if (dark) accent.tone(0.16f, 0.25f) else accent.tone(0.30f, 0.875f)
        val onAccentSoft = if (dark) accent.tone(0.55f, 0.86f) else accent.tone(0.70f, 0.17f)
        val onAccentSofter = if (dark) accent.tone(0.25f, 0.89f) else accent.tone(0.35f, 0.12f)

        return Roles(
            canvas = canvas,
            low = canvas.step(ink, a[0]),
            container = canvas.step(ink, a[1]),
            high = high,
            highest = canvas.step(ink, a[3]),
            accentSoft = accentSoft,
            onAccentSoft = onAccentSoft.readableOn(accentSoft),
            accentSofter = accentSofter,
            onAccentSofter = onAccentSofter.readableOn(accentSofter),
            outline = canvas.step(ink, if (dark) 0.30f else 0.24f)
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
    private val GroupOuterCorner = 20.dp
    private val GroupGap = 2.dp

    /**
     * Set by [ListGroup]: a row inside a group is one segment of it (4dp inner
     * corners, 2dp gaps, the group rounding the ends) — the Expressive list
     * Android 16 Settings and the Google apps moved to — rather than a free
     * 16dp card floating 8dp from its neighbour.
     */
    private val LocalInListGroup = staticCompositionLocalOf { false }

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

    // ------------------------------------------------------------------ shapes

    /**
     * The M3 Expressive shape library's rounded polygons (Cookie, Soft burst,
     * Clover), built by hand because 1.3.0 ships no `MaterialShapes`: a
     * circle whose radius rises and falls [lobes] times around the centre,
     * [depth] deep. A depth of 0 is a circle, so animating [depth] morphs a
     * circle into a cookie, and [rotation] (degrees) lets the shape turn as it
     * morphs, as the Expressive shapes do.
     */
    private class ScallopShape(
        private val lobes: Int,
        private val depth: Float,
        private val rotation: Float = 0f
    ) : Shape {
        override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
            val cx = size.width / 2f
            val cy = size.height / 2f
            val radius = size.minDimension / 2f
            val path = androidx.compose.ui.graphics.Path()
            val steps = 240
            val rot = Math.toRadians(rotation.toDouble())
            for (i in 0..steps) {
                val t = 2.0 * Math.PI * i / steps
                // A raised cosine, sharpened a little, so lobes read as
                // rounded scallops rather than a sine wobble.
                val wave = (1.0 + kotlin.math.cos(lobes * t)) / 2.0
                val r = radius * (1.0 - depth * (1.0 - Math.pow(wave, 0.8)))
                val x = cx + (r * kotlin.math.cos(t + rot)).toFloat()
                val y = cy + (r * kotlin.math.sin(t + rot)).toFloat()
                if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
            }
            path.close()
            return Outline.Generic(path)
        }
    }

    /** M3 Expressive "Cookie 9": the hero shape for an empty state. */
    private val CookieShape: Shape = ScallopShape(lobes = 9, depth = 0.10f)

    /** "Soft burst": twelve shallow lobes, for the liturgical banner's seal. */
    private val SoftBurstShape: Shape = ScallopShape(lobes = 12, depth = 0.09f, rotation = 15f)

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
     * The active-destination capsule, at the 64×32dp `NavigationBarItem` draws
     * its own indicator at. Hand-drawn because this app's ten destinations have
     * to scroll, and the real component does not.
     */
    @Composable
    private fun NavPill(selected: Boolean, container: Color, content: @Composable () -> Unit) {
        Box(
            Modifier
                .size(width = 64.dp, height = 32.dp)
                .background(
                    if (selected) container else Color.Transparent,
                    RoundedCornerShape(percent = 50)
                ),
            contentAlignment = Alignment.Center,
            content = { content() }
        )
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
    }

    /**
     * The headlines the composed screen has offered the app bar, newest last.
     * Set by [Scaffold]; a [ScreenTitle] outside one draws its own headline.
     */
    private class BarTitles {
        val entries = mutableStateListOf<TitleEntry>()
    }

    private val LocalBarTitles = staticCompositionLocalOf<BarTitles?> { null }

    /** 4 + 38 (headline) + 2 + 20 (supporting line) + 20. */
    private val ExpandedBarHeight = 84.dp

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
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
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
                // The headline is read lazily, in the bar's draw phase: a
                // screen's ScreenTitle usually sits in a lazy list item that
                // only composes while the content is *measured*, after the
                // bar. Read in composition it would land a frame late — a
                // flash of the destination's name before the screen's own.
                FlexibleTopBar(
                    title = { titles.entries.lastOrNull()?.title?.takeIf { it.isNotBlank() } ?: title },
                    subtitle = { titles.entries.lastOrNull()?.subtitle?.takeIf { it.isNotBlank() } },
                    onBack = onBack,
                    backLabel = backLabel,
                    actions = actions,
                    state = barState,
                    canvas = c.canvas,
                    scrolled = c.container
                )
            },
            bottomBar = {
                if (navItems.size <= 5) {
                    // Within the component's specified range, so use it: the
                    // indicator, the icon crossfade and the insets come free.
                    NavigationBar(
                        modifier = Modifier.testTag("bottom_nav_bar"),
                        // surfaceContainer, and *no* tonal elevation on top of
                        // it: the bar is already a tone step above the canvas,
                        // and stacking an elevation overlay on a container
                        // colour counts the same step twice.
                        containerColor = c.container,
                        tonalElevation = 0.dp
                    ) {
                        navItems.forEachIndexed { index, item ->
                            val isSelected = index == selectedNavIndex
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = { onNavSelected(index) },
                                modifier = Modifier.testTag(item.tag),
                                alwaysShowLabel = true,
                                icon = {
                                    Icon(
                                        imageVector = if (isSelected) item.selectedIcon else item.icon,
                                        contentDescription = item.label,
                                        modifier = Modifier.size(design.decor.navIconSize)
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.label,
                                        style = styles.navLabel,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = c.onAccentSofter,
                                    selectedTextColor = palette.textPrimary,
                                    indicatorColor = c.accentSofter,
                                    unselectedIconColor = palette.textSecondary,
                                    unselectedTextColor = palette.textSecondary
                                )
                            )
                        }
                    }
                } else {
                    Surface(color = c.container) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .navigationBarsPadding()
                                .testTag("bottom_nav_bar")
                                .horizontalScroll(rememberScrollState())
                                .padding(horizontal = 8.dp)
                                // 12 above the pill, 16 below the label: the
                                // navigation bar's own 80dp vertical rhythm.
                                .padding(top = 12.dp, bottom = 16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            navItems.forEachIndexed { index, item ->
                                val isSelected = index == selectedNavIndex
                                Column(
                                    modifier = Modifier
                                        .widthIn(min = 72.dp)
                                        .clip(RoundedCornerShape(percent = 50))
                                        .clickable { onNavSelected(index) }
                                        .padding(horizontal = 4.dp)
                                        .testTag(item.tag),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    NavPill(isSelected, c.accentSofter) {
                                        Icon(
                                            imageVector = if (isSelected) item.selectedIcon else item.icon,
                                            contentDescription = item.label,
                                            tint = if (isSelected) {
                                                c.onAccentSofter
                                            } else {
                                                palette.textSecondary
                                            },
                                            modifier = Modifier.size(design.decor.navIconSize)
                                        )
                                    }
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = item.label,
                                        style = styles.navLabel,
                                        color = if (isSelected) palette.textPrimary else palette.textSecondary,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            },
            content = { padding ->
                CompositionLocalProvider(LocalBarTitles provides titles) { content(padding) }
            }
        )
    }

    /**
     * Two rows, as the M3 large top app bar draws them: a 64dp row with the
     * navigation icon, the collapsed title and the actions, over an expanded
     * region carrying the headline (emphasised headline type) and its
     * supporting line. The expanded region's own height is the scroll
     * behaviour's offset limit, so it collapses exactly as far as it is tall.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun FlexibleTopBar(
        title: () -> String,
        subtitle: () -> String?,
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
        val measurer = rememberTextMeasurer()
        val headStyle = styles.screenTitle.copy(color = palette.textPrimary)
        val headSmallStyle = MaterialTheme.typography.headlineSmall.copy(
            color = palette.textPrimary,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
        )
        val supportStyle = styles.screenSubtitle.copy(color = palette.textSecondary)
        val fraction = state.collapsedFraction
        val container = androidx.compose.ui.graphics.lerp(canvas, scrolled, fraction.coerceIn(0f, 1f))
        Surface(color = container, contentColor = palette.textPrimary) {
            val actionsWidth = remember { intArrayOf(0) }
            Layout(
                modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.statusBars),
                content = {
                Row(
                    Modifier.fillMaxWidth().height(64.dp).padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (onBack != null) {
                        IconButton(onClick = onBack, modifier = Modifier.testTag("nav_back")) {
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = backLabel,
                                tint = palette.textPrimary
                            )
                        }
                    } else {
                        Spacer(Modifier.width(12.dp))
                    }
                    Text(
                        text = title(),
                        style = MaterialTheme.typography.titleLarge,
                        color = palette.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                            .graphicsLayer {
                                alpha = ((state.collapsedFraction - 0.55f) / 0.45f).coerceIn(0f, 1f)
                            }
                    )
                    CompositionLocalProvider(androidx.compose.material3.LocalContentColor provides palette.textSecondary) {
                        Row(
                            Modifier.layout { m, c ->
                                val p = m.measure(c)
                                actionsWidth[0] = p.width
                                layout(p.width, p.height) { p.place(0, 0) }
                            },
                            verticalAlignment = Alignment.CenterVertically,
                            content = actions
                        )
                    }
                }
                // A fixed-height region, as the large top app bar's is, so
                // its height never waits on the headline: one line of
                // emphasised headline over one line of supporting text.
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(ExpandedBarHeight)
                        .graphicsLayer {
                            alpha = (1f - state.collapsedFraction * 1.6f).coerceIn(0f, 1f)
                        }
                        .drawWithCache {
                            val gutter = design.spacing.gutter.toPx()
                            val maxW = (size.width - gutter * 2).toInt().coerceAtLeast(0)
                            val box = androidx.compose.ui.unit.Constraints(maxWidth = maxW)
                            val t = title()
                            val sub = subtitle()
                            var head = measurer.measure(
                                t, headStyle, overflow = TextOverflow.Ellipsis, maxLines = 1, constraints = box
                            )
                            // A long headline steps down to headlineSmall
                            // before it ever ellipsises.
                            if (head.hasVisualOverflow) {
                                head = measurer.measure(
                                    t, headSmallStyle, overflow = TextOverflow.Ellipsis, maxLines = 1, constraints = box
                                )
                            }
                            val support = sub?.let {
                                measurer.measure(
                                    it, supportStyle, overflow = TextOverflow.Ellipsis, maxLines = 1, constraints = box
                                )
                            }
                            // Top-aligned on a fixed line, so the headline
                            // never jumps between tabs that have a
                            // supporting line and tabs that do not.
                            val headTop = 4.dp.toPx()
                            val supportTop = headTop + 38.dp.toPx() + 2.dp.toPx()
                            onDrawBehind {
                                drawText(head, topLeft = androidx.compose.ui.geometry.Offset(gutter, headTop))
                                support?.let {
                                    drawText(it, topLeft = androidx.compose.ui.geometry.Offset(gutter, supportTop))
                                }
                            }
                        }
                )
                }
            ) { measurables, constraints ->
                val loose = constraints.copy(minHeight = 0, maxHeight = androidx.compose.ui.unit.Constraints.Infinity)
                val pinned = measurables[0].measure(loose)
                val expanded = measurables[1].measure(loose)
                // A root screen with nothing in the pinned row lets the
                // headline rise into it at rest, so the bar does not open on
                // 64dp of empty canvas; anything in the row keeps it clear.
                val overlap = if (onBack == null && actionsWidth[0] == 0) 28.dp.roundToPx() else 0
                val full = expanded.height
                val collapsible = (full - overlap).coerceAtLeast(0)
                if (state.heightOffsetLimit != -collapsible.toFloat()) {
                    state.heightOffsetLimit = -collapsible.toFloat()
                }
                val visible = (collapsible + state.heightOffset).toInt().coerceIn(0, collapsible)
                val height = pinned.height + visible
                layout(constraints.maxWidth, height) {
                    pinned.placeRelative(0, 0)
                    expanded.placeRelative(0, height - full)
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
                        color = palette.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = palette.textSecondary,
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
                            contentDescription = null
                        )
                    }
                }
            },
            actions = actions,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = c.canvas,
                titleContentColor = palette.textPrimary,
                actionIconContentColor = palette.textSecondary,
                navigationIconContentColor = palette.textSecondary
            )
        )
    }

    @Composable
    override fun ScreenTitle(title: String, subtitle: String?, modifier: Modifier) {
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
            }
            DisposableEffect(holder, entry) {
                holder.entries.add(entry)
                onDispose { if (holder.entries.size > 1) holder.entries.remove(entry) }
            }
            // The headline is drawn by the bar, so the heading's semantics
            // stay here, where a screen reader expects the screen to open.
            Spacer(
                modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .semantics {
                        heading()
                        contentDescription = listOfNotNull(title, subtitle).joinToString(". ")
                    }
            )
            return
        }
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .padding(top = design.spacing.headerTopGap, bottom = 16.dp)
        ) {
            Text(text = title, style = styles.screenTitle, color = palette.textPrimary)
            if (subtitle != null) {
                Spacer(Modifier.height(4.dp))
                Text(text = subtitle, style = styles.screenSubtitle, color = palette.textSecondary)
            }
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
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 24.dp, bottom = 8.dp)
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
                Spacer(Modifier.height(2.dp))
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
        val rest = if (inGroup) GroupInnerCorner else 16.dp
        val corner = pressMorph(interaction, onClick != null, rest, rest + 12.dp)
        FilledCard(
            modifier = modifier.fillMaxWidth(),
            container = c.container,
            shape = RoundedCornerShape(corner),
            onClick = onClick,
            interactionSource = interaction
        ) {
            Column(Modifier.padding(design.spacing.cardPadding), content = content)
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
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val c = roles()

        val container = if (selected) c.accentSofter else c.container
        val titleColor = if (selected) c.onAccentSofter else palette.textPrimary

        // M3 list items come in one-, two- and three-line heights (56/72/88)
        // and the three-line item lets its supporting text run to a second
        // line. Clamping both lines to one cut every long Spanish parish name
        // to "Catedral Primada de S…" — which no Google list does; it wraps.
        // Once the text block runs past two lines the item is three-line, and
        // the spec then pins avatar and trailing text to the top instead of
        // centring them against a tall block. That is decided in the layout
        // pass from the measured text height, so it is right on the first
        // frame rather than one recomposition later.
        val hasLeading = leadingIcon != null
        val hasTrailing = trailingText != null || (showsNavigationAffordance && onClick != null)

        // Inside a group a row is a 4dp segment; pressed, its corners swell
        // on the fast spatial spring — the Expressive list's press answer.
        val inGroup = LocalInListGroup.current
        val interaction = remember { MutableInteractionSource() }
        val rest = if (inGroup) GroupInnerCorner else 16.dp
        val corner = pressMorph(interaction, onClick != null, rest, if (inGroup) 16.dp else 28.dp)
        val animatedContainer by androidx.compose.animation.animateColorAsState(
            targetValue = container,
            animationSpec = defaultEffects(),
            label = "row_container"
        )

        FilledCard(
            modifier = modifier.fillMaxWidth(),
            container = animatedContainer,
            shape = RoundedCornerShape(corner),
            onClick = onClick,
            interactionSource = interaction
        ) {
            Layout(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    if (leadingIcon != null) {
                        // Selection morphs the avatar from a circle into a
                        // nine-lobed cookie, turning as it goes, on the
                        // fast spatial spring: the Expressive selection cue,
                        // so a selected row differs by shape, not by fill alone.
                        val morph by animateFloatAsState(
                            targetValue = if (selected) 1f else 0f,
                            animationSpec = fastSpatial(),
                            label = "avatar_morph"
                        )
                        TonalIconCircle(
                            shape = if (morph < 0.01f) RoundedCornerShape(percent = 50)
                            else ScallopShape(9, 0.11f * morph, 40f * morph),
                            icon = leadingIcon,
                            // The list avatar: primaryContainer / onPrimaryContainer.
                            // A grey disc under a green glyph read as disabled.
                            // The selected row is already secondaryContainer,
                            // so its avatar steps up to primary to stay distinct.
                            container = if (selected) MaterialTheme.colorScheme.primary else c.accentSoft,
                            tint = if (selected) MaterialTheme.colorScheme.onPrimary else c.onAccentSoft,
                            glyph = design.decor.rowIconSize
                        )
                    }
                    Column {
                        Text(
                            text = title,
                            style = styles.rowTitle,
                            color = titleColor,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (subtitle != null) {
                            Text(
                                text = subtitle,
                                style = styles.rowSubtitle,
                                color = if (selected) c.onAccentSofter else palette.textSecondary,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    val chevron = showsNavigationAffordance && onClick != null
                    if (trailingText != null || chevron) {
                        // Trailing supporting text at bodyMedium: a time, a
                        // reference or a current value is data the reader
                        // scans for, and 11sp labelSmall read as a footnote.
                        // A row that opens something says so with a chevron,
                        // after its value when it has one ("Country  All ›").
                        val tint = if (selected) c.onAccentSofter else palette.textSecondary
                        androidx.compose.foundation.layout.Row(verticalAlignment = Alignment.CenterVertically) {
                            if (trailingText != null) {
                                Text(
                                    text = trailingText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = tint,
                                    textAlign = TextAlign.End,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.widthIn(max = 112.dp)
                                )
                            }
                            if (chevron) {
                                if (trailingText != null) Spacer(Modifier.width(4.dp))
                                Icon(
                                    Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = tint,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            ) { measurables, constraints ->
                val start = 16.dp.roundToPx()
                val end = 24.dp.roundToPx()
                val gap = design.spacing.iconLabelGap.roundToPx()
                val trailingGap = 16.dp.roundToPx()
                val loose = constraints.copy(minWidth = 0, minHeight = 0)
                var i = 0
                val leading = if (hasLeading) measurables[i++].measure(loose) else null
                val textMeasurable = measurables[i++]
                val trailing = if (hasTrailing) measurables[i].measure(loose) else null

                val width = constraints.maxWidth
                val used = start + end +
                    (leading?.let { it.width + gap } ?: 0) +
                    (trailing?.let { it.width + trailingGap } ?: 0)
                val text = textMeasurable.measure(
                    loose.copy(maxWidth = (width - used).coerceAtLeast(0))
                )

                // One line of rowTitle is 24dp, title + subtitle 44dp; anything
                // taller is a three-line item.
                val threeLine = text.height > 48.dp.roundToPx()
                val oneLine = subtitle == null && text.height <= 28.dp.roundToPx()
                val minHeight = when {
                    threeLine -> 88.dp
                    oneLine -> 56.dp
                    else -> 72.dp
                }.roundToPx()
                val padV = (if (threeLine) 12.dp else 8.dp).roundToPx()
                val height = maxOf(minHeight, text.height + padV * 2)

                fun sideY(h: Int) = if (threeLine) padV else (height - h) / 2

                layout(width, height) {
                    var x = start
                    leading?.let {
                        it.placeRelative(x, sideY(it.height))
                        x += it.width + gap
                    }
                    text.placeRelative(x, (height - text.height) / 2)
                    trailing?.let {
                        // Three-line: the trailing text shares the headline's
                        // first baseline, as the spec draws it, rather than
                        // sitting on the padding line 4dp above it.
                        val textBase = text[FirstBaseline]
                        val trailBase = it[FirstBaseline]
                        val y = if (threeLine && !(showsNavigationAffordance && onClick != null) && textBase != AlignmentLine.Unspecified &&
                            trailBase != AlignmentLine.Unspecified
                        ) {
                            (height - text.height) / 2 + textBase - trailBase
                        } else if (showsNavigationAffordance && onClick != null) {
                            // A chevron is centred on the item at any height.
                            (height - it.height) / 2
                        } else {
                            sideY(it.height)
                        }
                        it.placeRelative(width - end - it.width, y)
                    }
                }
            }
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
            color = roles().outline
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
            modifier = modifier.heightIn(min = 40.dp),
            enabled = enabled,
            shape = buttonShape(interaction, enabled),
            interactionSource = interaction,
            colors = ButtonDefaults.buttonColors(
                containerColor = scheme.primary,
                contentColor = scheme.onPrimary
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
            Text(text = label, style = MaterialTheme.typography.labelLarge, maxLines = 1)
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
        val interaction = remember { MutableInteractionSource() }
        FilledTonalButton(
            onClick = onClick,
            modifier = modifier.heightIn(min = 40.dp),
            enabled = enabled,
            shape = buttonShape(interaction, enabled),
            interactionSource = interaction,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = c.accentSofter,
                contentColor = c.onAccentSofter
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
            Text(text = label, style = MaterialTheme.typography.labelLarge, maxLines = 1)
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
                    tint = palette.textSecondary,
                    modifier = Modifier.padding(start = 4.dp).size(24.dp)
                )
            },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = null,
                            tint = palette.textSecondary
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
            colors = TextFieldDefaults.colors(
                focusedContainerColor = c.high,
                unfocusedContainerColor = c.high,
                disabledContainerColor = c.high,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
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
            val fitsBare = options.size <= 5 && widest <= segment - 24.dp
            val showCheck = widest <= room
            val fits = fitsBare
            if (!fits) {
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
                        color = c.highest
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
                            colors = SegmentedButtonDefaults.colors(
                                activeContainerColor = c.accentSofter,
                                activeContentColor = c.onAccentSofter,
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
            label = { Text(text = label, style = MaterialTheme.typography.labelLarge, maxLines = 1) },
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
                labelColor = palette.textSecondary,
                selectedContainerColor = c.accentSofter,
                selectedLabelColor = c.onAccentSofter,
                selectedLeadingIconColor = c.onAccentSofter
            ),
            border = FilterChipDefaults.filterChipBorder(
                enabled = true,
                selected = selected,
                borderColor = c.outline,
                selectedBorderColor = Color.Transparent
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
                .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
                // decor.usesDropCaps is false, so a chapter opens with air
                // rather than with an initial.
                .padding(top = if (isFirstOfChapter) 16.dp else 6.dp)
                .padding(start = design.spacing.gutter, end = design.spacing.gutter, bottom = 6.dp),
            verticalAlignment = Alignment.Top
        ) {
            // A 24dp tonal disc, not a rounded tag: it is the same circle the
            // list rows and the metric tiles use, one size down, so the page
            // has one shape language for "a glyph in a tint" instead of two.
            Box(
                Modifier
                    .padding(top = 3.dp)
                    .size(24.dp)
                    .background(c.accentSofter, RoundedCornerShape(percent = 50)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$number",
                    style = MaterialTheme.typography.labelSmall,
                    color = c.onAccentSofter,
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
                    contentDescription = null,
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
            // surfaceContainer, the same step as every other card: a tile one
            // tone darker than the list rows under it read as a second,
            // unrelated grey rather than as a sibling surface.
            container = c.container,
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
                        size = 32.dp,
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
                size = 96.dp,
                glyph = 40.dp,
                shape = CookieShape
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
        val diff = ((ph - h + 540f) % 360f) - 180f
        // MCU allows 15°; at that a liturgical gold turned olive beside a
        // green seed, so the turn is held to 8°: enough to share the
        // scheme's temperature, not enough to change the colour's name.
        val shift = (diff * 0.5f).coerceIn(-8f, 8f)
        return Color.hsl(((h + shift) % 360f + 360f) % 360f, sat, l, alpha)
    }

    /** A Latin cross, drawn rather than borrowed: the icon set has none. */
    @Composable
    private fun CrossGlyph(color: Color, modifier: Modifier) {
        androidx.compose.foundation.Canvas(modifier) {
            val w = size.width
            val h = size.height
            val bar = w * 0.17f
            val r = CornerRadius(bar / 2f)
            drawRoundRect(
                color = color,
                topLeft = androidx.compose.ui.geometry.Offset((w - bar) / 2f, h * 0.08f),
                size = Size(bar, h * 0.84f),
                cornerRadius = r
            )
            drawRoundRect(
                color = color,
                topLeft = androidx.compose.ui.geometry.Offset(w * 0.2f, h * 0.30f),
                size = Size(w * 0.6f, bar),
                cornerRadius = r
            )
        }
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
        // The liturgical colour is a fixed custom colour, so it is harmonised
        // with the scheme before any tone is struck from it.
        val given = accentColorHex?.let { Color(it).harmonizedWith(primary) }
        // The whole container is a tone of the liturgical colour — its hue at
        // container lightness (tone ≈90 light, ≈25 dark) — never a stripe.
        val container = when {
            given == null -> c.accentSoft
            design.isDark -> given.tone(0.36f, 0.22f)
            else -> given.tone(0.48f, 0.875f)
        }
        val eyebrowColor = (given ?: primary).readableOn(container)
        val bodyColor = palette.textPrimary.readableOn(container, target = 7f)
        // A short celebration name is the hero of the screen it opens, so it
        // gets emphasised headline type; a sentence of explanation stays at
        // titleMedium, where a paragraph can still be read.
        val bodyStyle = when {
            body == null -> MaterialTheme.typography.titleMedium
            body.length <= 28 -> MaterialTheme.typography.headlineSmall.copy(
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
            )
            body.length <= 64 -> MaterialTheme.typography.titleLarge.copy(
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
            )
            else -> MaterialTheme.typography.titleMedium
        }
        val heroBody = body != null && body.length <= 64
        val seal = given != null

        FilledCard(
            modifier = modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
            container = container,
            // The extra-large 28dp corner: the banner is the hero surface,
            // and with headline type it is tall enough to carry it.
            shape = RoundedCornerShape(28.dp),
            onClick = onClick
        ) {
            Row(
                Modifier.padding(start = 20.dp, end = if (seal) 16.dp else 20.dp, top = 18.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelLarge,
                        color = eyebrowColor,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (body != null) {
                        Spacer(Modifier.height(if (heroBody) 6.dp else 4.dp))
                        Text(
                            text = body,
                            style = bodyStyle,
                            color = bodyColor
                        )
                    }
                }
                if (seal && given != null) {
                    // The Expressive moment: the day's colour struck as a
                    // soft-burst seal at full container strength, carrying a
                    // cross — the one shape on the screen that is not a
                    // rounded rectangle, which is what makes it the hero.
                    val sealColor = if (design.isDark) given.tone(0.50f, 0.72f) else given.tone(0.55f, 0.36f)
                    val crossColor = if (design.isDark) given.tone(0.40f, 0.16f) else given.tone(0.30f, 0.96f)
                    Spacer(Modifier.width(16.dp))
                    Box(
                        Modifier.size(64.dp).background(sealColor, SoftBurstShape),
                        contentAlignment = Alignment.Center
                    ) {
                        CrossGlyph(crossColor, Modifier.size(width = 22.dp, height = 30.dp))
                    }
                }
            }
        }
    }
}
