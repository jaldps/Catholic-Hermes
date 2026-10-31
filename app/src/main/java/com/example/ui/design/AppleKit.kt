package com.example.ui.design

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.drawText
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * The Cupertino identity: inset grouped lists on a grey canvas, half-pixel
 * hairlines, large left-aligned titles and a fixed tab bar. Nothing is
 * elevated — separation comes from the grouped background and the hairline,
 * exactly as iOS does it. The single exception is the lift under the selected
 * segment of a segmented control, which Apple itself draws.
 *
 * Three rules do most of the work here and are worth stating because they are
 * what the eye reads as "iOS" before it reads anything else:
 *
 *  * **Nothing ripples.** Every target is `indication = null`. Cells and
 *    tappable cards answer a touch with UIKit's flat grey wash; buttons and
 *    capsules fade toward ~70% under the finger, as UIButton highlights.
 *  * **The group ends at the group.** A table view never draws a separator
 *    under its last row, so [ListGroup] measures itself one physical pixel
 *    short and lets the trailing hairline fall outside its own clip.
 *  * **A leading glyph is a tile, not a picture.** The 29pt rounded-square
 *    icon well is the single most recognisable mark in iOS Settings, and the
 *    separator inset is measured from the text it pushes across, never from
 *    the edge of the card.
 */
object AppleKit : AppKit {

    /** 1 physical pixel at 2x — UIKit's separator weight. */
    private val HairlineWeight = 0.5.dp

    /** The 29pt icon well of an iOS Settings row. */
    private val IconWellSize = 28.dp
    private val IconWellShape = RoundedCornerShape(7.dp)
    private val IconWellGlyph = 19.dp

    /** Leading text inset of a grouped row, measured from the card edge. */
    private val RowTextInset = 16.dp

    /**
     * Marks the subtree inside a [Card] so a [SectionHeader] drops the gutter
     * and the section gap it would otherwise contribute — inside a card it is
     * a card header, not a table-view section header, and must align with the
     * card's own padding rather than add a second inset to it.
     */
    private val LocalInsideCard = compositionLocalOf { false }

    /** A target with no ripple. iOS does not ripple. */
    @Composable
    private fun Modifier.tap(onClick: (() -> Unit)?, enabled: Boolean = true): Modifier {
        val source = remember { MutableInteractionSource() }
        return if (onClick == null) {
            this
        } else {
            clickable(
                interactionSource = source,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
        }
    }

    /**
     * A target that reports its own press, for the controls UIKit highlights:
     * a button's fill and label fade to ~70% under the finger, a cell takes
     * the flat grey wash. Still no ripple.
     */
    @Composable
    private fun Modifier.pressable(
        source: MutableInteractionSource,
        onClick: (() -> Unit)?,
        enabled: Boolean = true
    ): Modifier = if (onClick == null) {
        this
    } else {
        clickable(interactionSource = source, indication = null, enabled = enabled, onClick = onClick)
    }

    /** WCAG relative-luminance contrast between two opaque colours. */
    private fun contrast(a: Color, b: Color): Float {
        val la = a.luminance()
        val lb = b.luminance()
        return (maxOf(la, lb) + 0.05f) / (minOf(la, lb) + 0.05f)
    }

    /**
     * [color] moved toward black (on a light [background]) or white (on a
     * dark one) just far enough to read at [min]:1 — the same hue, never a
     * substitute grey.
     */
    private fun inkOn(color: Color, background: Color, min: Float = 4.5f): Color {
        val toward = if (background.luminance() > 0.4f) Color.Black else Color.White
        var t = 0f
        var c = color
        while (contrast(c, background) < min && t < 1f) {
            t += 0.04f
            c = lerp(color, toward, t)
        }
        return c
    }

    /**
     * The fill behind filled controls. iOS sets a white label on its tint in
     * both appearances, so the fill is whatever the tint must become for
     * white to read at 4.5:1. In dark mode the liturgical accent is a pastel
     * meant for text; it is re-struck at a saturated mid value first (the
     * way systemGreen stays vivid under white) and then taken down in value,
     * never toward grey, until the label passes.
     */
    @Composable
    private fun accentFill(): Color {
        val design = LocalAppDesign.current
        val primary = MaterialTheme.colorScheme.primary
        return remember(primary, design.isDark) {
            val hsv = FloatArray(3)
            android.graphics.Color.colorToHSV(primary.toArgb(), hsv)
            if (design.isDark && primary.luminance() > 0.25f) {
                hsv[1] = hsv[1].coerceAtLeast(0.62f)
                hsv[2] = 0.62f
            }
            var c = Color(android.graphics.Color.HSVToColor(hsv))
            while (contrast(c, Color.White) < 4.6f && hsv[2] > 0.18f) {
                hsv[2] -= 0.02f
                c = Color(android.graphics.Color.HSVToColor(hsv))
            }
            c
        }
    }

    /** White, always: [accentFill] is struck so that white passes on it. */
    @Suppress("FunctionOnlyReturningConstant")
    @Composable
    private fun onAccentFill(): Color = Color.White

    /**
     * The iOS system colours, light and dark, that Settings strikes its icon
     * wells in. One flat green on every tile is the tell of a themed Android
     * app; Settings, Health and Shortcuts colour-code each row by meaning.
     */
    private enum class SystemColor(val light: Long, val dark: Long) {
        RED(0xFFFF3B30, 0xFFFF453A),
        ORANGE(0xFFFF9500, 0xFFFF9F0A),
        YELLOW(0xFFFFCC00, 0xFFFFD60A),
        GREEN(0xFF34C759, 0xFF30D158),
        MINT(0xFF00C7BE, 0xFF63E6E2),
        TEAL(0xFF30B0C7, 0xFF40C8E0),
        CYAN(0xFF32ADE6, 0xFF64D2FF),
        BLUE(0xFF007AFF, 0xFF0A84FF),
        INDIGO(0xFF5856D6, 0xFF5E5CE6),
        PURPLE(0xFFAF52DE, 0xFFBF5AF2),
        PINK(0xFFFF2D55, 0xFFFF375F),
        BROWN(0xFFA2845E, 0xFFAC8E68),
        GRAY(0xFF8E8E93, 0xFF8E8E93);

        fun of(dark: Boolean) = Color(if (dark) this.dark else light)
    }

    /**
     * A row's well colour, chosen by what its glyph means — morning orange,
     * night indigo, Marian blue for the rosary, Books orange for a book,
     * Maps' red pin, Phone green, penitential purple for fasting — and, for a
     * glyph with no obvious meaning, a stable pick from the palette so the
     * same glyph is always the same colour.
     */
    private fun wellColor(icon: ImageVector, dark: Boolean): Color = wellHue(icon).of(dark)

    private fun wellHue(icon: ImageVector): SystemColor {
        val full = icon.name.lowercase()
        val n = full.substringAfterLast('.')
        return when {
            full.startsWith("rosary") -> SystemColor.BLUE
            full.startsWith("candle") -> SystemColor.ORANGE
            "twilight" in n || "sunrise" in n || "sunset" in n -> SystemColor.PINK
            "sunny" in n || "lightmode" in n || "sun" in n -> SystemColor.ORANGE
            "night" in n || "bedtime" in n || "darkmode" in n || "moon" in n -> SystemColor.INDIGO
            "rosary" in n || "selfimprovement" in n -> SystemColor.BLUE
            "candle" in n || "flame" in n || "fire" in n -> SystemColor.ORANGE
            "historyedu" in n || "history" in n -> SystemColor.BROWN
            "book" in n || "stories" in n || "library" in n -> SystemColor.ORANGE
            "newspaper" in n || "article" in n || "feed" in n -> SystemColor.RED
            "shield" in n || "verified" in n -> SystemColor.TEAL
            // systemYellow under a white glyph is ~1.6:1 in light mode;
            // Settings strikes its "star" rows in orange for that reason.
            "star" in n || "bolt" in n || "lightbulb" in n || "flash" in n -> SystemColor.ORANGE
            "church" in n || "temple" in n -> SystemColor.PURPLE
            "locationcity" in n || "apartment" in n || "domain" in n -> SystemColor.INDIGO
            "place" in n || "locationon" in n || "pushpin" in n || "pin" in n -> SystemColor.RED
            "mylocation" in n || "nearme" in n || "directions" in n || "navigation" in n -> SystemColor.BLUE
            "map" in n || "explore" in n -> SystemColor.GREEN
            "calendar" in n || "event" in n || "today" in n || "date" in n -> SystemColor.RED
            "schedule" in n || "alarm" in n || "clock" in n || "timer" in n || "access" in n -> SystemColor.ORANGE
            "volume" in n || "voice" in n || "music" in n || "headphone" in n || "mic" in n -> SystemColor.PINK
            "phone" in n || "call" in n -> SystemColor.GREEN
            "public" in n || "language" in n || "translate" in n || "globe" in n -> SystemColor.BLUE
            "search" in n || "openinnew" in n || "launch" in n || "link" in n -> SystemColor.GRAY
            "settings" in n || "tune" in n || "grid" in n -> SystemColor.GRAY
            "delete" in n || "remove" in n -> SystemColor.RED
            "download" in n || "cloud" in n || "upload" in n -> SystemColor.BLUE
            "check" in n || "done" in n || "task" in n -> SystemColor.GREEN
            "favorite" in n || "heart" in n || "volunteer" in n || "florist" in n || "spa" in n -> SystemColor.PINK
            "restaurant" in n || "food" in n || "dining" in n -> SystemColor.PURPLE
            "water" in n || "drop" in n || "waves" in n -> SystemColor.CYAN
            "quiz" in n || "psychology" in n || "help" in n || "school" in n -> SystemColor.INDIGO
            "edit" in n || "create" in n || "note" in n -> SystemColor.ORANGE
            "person" in n || "people" in n || "group" in n || "family" in n -> SystemColor.TEAL
            "home" in n -> SystemColor.ORANGE
            n == "add" || n.startsWith("add") -> SystemColor.BLUE
            else -> {
                val pool = arrayOf(
                    SystemColor.RED, SystemColor.ORANGE, SystemColor.GREEN, SystemColor.TEAL,
                    SystemColor.BLUE, SystemColor.INDIGO, SystemColor.PURPLE, SystemColor.PINK
                )
                pool[Math.floorMod(n.hashCode(), pool.size)]
            }
        }
    }

    /**
     * One colour per tile within a group. Settings never sets two red wells
     * one above the other: a group is colour-coded, and a repeat reads as
     * an accident. Each [ListGroup] owns a registry; a row claims its
     * semantic colour, and if a sibling already holds it, the next unused
     * colour in a fixed, well-separated order. Rows claim in composition
     * order, so the assignment is deterministic. Grey (utility links) may
     * repeat, as it does in Settings.
     */
    private class WellRegistry {
        val used = mutableSetOf<SystemColor>()
        fun claim(preferred: SystemColor): SystemColor {
            if (preferred == SystemColor.GRAY) return preferred
            val pick = if (preferred !in used) preferred else FallbackOrder.firstOrNull { it !in used } ?: preferred
            used += pick
            return pick
        }
    }

    private val FallbackOrder = listOf(
        SystemColor.BLUE, SystemColor.ORANGE, SystemColor.GREEN, SystemColor.PURPLE,
        SystemColor.RED, SystemColor.TEAL, SystemColor.INDIGO, SystemColor.PINK,
        SystemColor.BROWN, SystemColor.MINT, SystemColor.CYAN
    )

    private val LocalWellRegistry = compositionLocalOf<WellRegistry?> { null }

    /**
     * SF Symbols in a Settings well are the *filled* variant at a single
     * weight, white on colour. Material's outlined glyphs at 17dp are hairline
     * drawings that thin out against a saturated tile, and a mix of outlined
     * and filled in one column is the inconsistency an SF eye catches first.
     * So every well glyph is resolved to its filled cut, and the few Material
     * glyphs that carry the wrong meaning for a Catholic app are replaced:
     * the seated meditator becomes a rosary, the quill-on-scroll (illegible
     * at this size) becomes the columned portico of `building.columns`, the
     * note-with-lines becomes `square.and.pencil`.
     */
    private val solidCache = java.util.concurrent.ConcurrentHashMap<String, ImageVector>()

    private fun solidGlyph(icon: ImageVector): ImageVector {
        val full = icon.name
        return solidCache.getOrPut(full) {
            val n = full.substringAfterLast('.').lowercase()
            when {
                full.startsWith("rosary") || n == "selfimprovement" -> DevotionalGlyphs.rosaryFilled
                full.startsWith("candle") -> DevotionalGlyphs.candleFilled
                n == "historyedu" -> Icons.Filled.AccountBalance
                n == "editnote" -> Icons.Filled.Create
                else -> filledVariant(full) ?: icon
            }
        }
    }

    /** `Outlined.Shield` → `Icons.Filled.Shield`, by the icon library's own accessor. */
    private fun filledVariant(name: String): ImageVector? = runCatching {
        val parts = name.split('.')
        val simple = parts.last()
        val mirrored = parts.first() == "AutoMirrored"
        val style = if (mirrored) parts.getOrNull(1) else parts.first()
        if (style == "Filled") return@runCatching null
        if (style !in setOf("Outlined", "Rounded", "Sharp", "TwoTone")) return@runCatching null
        val pkg = if (mirrored) "androidx.compose.material.icons.automirrored.filled"
        else "androidx.compose.material.icons.filled"
        val owner: Any = if (mirrored) Icons.AutoMirrored.Filled else Icons.Filled
        Class.forName("$pkg.${simple}Kt")
            .getMethod("get$simple", owner.javaClass)
            .invoke(null, owner) as ImageVector
    }.getOrNull()

    /** A Latin cross, SF-weighted, for the places a seated meditator stood. */
    private val LatinCross: ImageVector by lazy {
        ImageVector.Builder("apple.cross", 24.dp, 24.dp, 24f, 24f).apply {
            addPath(
                pathData = androidx.compose.ui.graphics.vector.PathParser()
                    .parsePathString(
                        "M10.6,2.6 h2.8 v5.2 h4.9 v2.8 h-4.9 v10.8 h-2.8 v-10.8 h-4.9 v-2.8 h4.9 z"
                    ).toNodes(),
                fill = SolidColor(Color.Black)
            )
        }.build()
    }

    /** The disclosure chevron's grey: iOS's tertiary label, a touch firmer. */
    @Composable
    private fun chevronGrey(): Color =
        if (LocalAppDesign.current.isDark) Color(0xFF5E5E63) else Color(0xFFB8B8BE)

    /**
     * UIKit's fill colours are translucent greys, not opaque swatches: the
     * same `tertiarySystemFill` reads as a soft well on the grouped canvas
     * and still separates from a white or #1C1C1E card it sits on. An opaque
     * grey picked for one of those backgrounds vanishes on the other.
     */
    @Composable
    private fun systemFill(level: Int): Color {
        val dark = LocalAppDesign.current.isDark
        return when (level) {
            // secondarySystemFill: capsule buttons, segmented-control track.
            2 -> if (dark) Color(0x52787880) else Color(0x29787880)
            // tertiarySystemFill: search fields.
            3 -> if (dark) Color(0x3D767680) else Color(0x1F767680)
            // quaternarySystemFill.
            else -> if (dark) Color(0x2E767680) else Color(0x14747480)
        }
    }

    /**
     * The interactive spring UIKit uses for touch-down and touch-up
     * (`UISpringTimingParameters` at damping ≈ 0.8, response ≈ 0.3 s): fast,
     * barely overshooting, never bouncy.
     */
    private fun <T> touchSpring() = spring<T>(dampingRatio = 0.8f, stiffness = 600f)

    /**
     * A button or card gives under the finger: it shrinks a few percent on a
     * spring and springs back on release, the way SwiftUI's bordered buttons
     * and App Store cards do. Scale is drawn in a graphics layer, so the
     * layout never moves.
     */
    @Composable
    private fun Modifier.pressScale(pressed: Boolean, amount: Float = 0.97f): Modifier {
        val scale by animateFloatAsState(if (pressed) amount else 1f, touchSpring(), label = "press")
        return graphicsLayer { scaleX = scale; scaleY = scale }
    }

    /** tertiarySystemFill — the grey of a disabled filled button. */
    @Composable
    private fun disabledFill(): Color = systemFill(3)

    /** A full-bleed half-pixel separator. */
    @Composable
    private fun Hairline(modifier: Modifier = Modifier) {
        val palette = LocalAppDesign.current.palette
        Box(modifier.fillMaxWidth().height(HairlineWeight).background(palette.hairline))
    }

    /**
     * The table-view separator: a hairline that starts after the leading
     * content rather than at the edge of the group.
     */
    @Composable
    private fun InsetHairline(modifier: Modifier = Modifier, leadingInset: Dp = RowTextInset) {
        val palette = LocalAppDesign.current.palette
        Row(modifier.fillMaxWidth().height(HairlineWeight)) {
            Spacer(Modifier.width(leadingInset))
            Box(Modifier.weight(1f).height(HairlineWeight).background(palette.hairline))
        }
    }

    /**
     * The disclosure indicator, drawn rather than borrowed. The Material
     * chevron is a thin, wide, square-capped glyph; UIKit's is a short,
     * steep, round-capped stroke at roughly semibold weight, and at 13dp tall
     * the difference between the two is the difference between a list that
     * looks like iOS and one that does not.
     */
    @Composable
    private fun Chevron(tint: Color, modifier: Modifier = Modifier, height: Dp = 13.dp) {
        Canvas(modifier.size(height * 0.58f, height)) {
            val stroke = 2.dp.toPx()
            val left = stroke / 2f
            val right = size.width - stroke / 2f
            val top = stroke / 2f
            val bottom = size.height - stroke / 2f
            val middle = size.height / 2f
            drawLine(tint, Offset(left, top), Offset(right, middle), stroke, StrokeCap.Round)
            drawLine(tint, Offset(right, middle), Offset(left, bottom), stroke, StrokeCap.Round)
        }
    }

    /** Bars sit a shade off the canvas so they read as bars, as UIKit's materials do. */
    @Composable
    private fun barColor(): Color {
        val design = LocalAppDesign.current
        return lerp(
            design.palette.canvas,
            design.palette.groupedSurface,
            if (design.isDark) 0.55f else 0.80f
        )
    }

    /**
     * UILabel's `adjustsFontSizeToFitWidth`: the text steps its size down,
     * to no less than [minScale], until it sets in [maxLines] without a line
     * ending inside a word. If even the floor will not do it, it sets at the
     * floor and wraps.
     *
     * Built from a layout modifier and a drawn [TextLayoutResult] rather than
     * BoxWithConstraints, because these sit in rows sized by intrinsic
     * height, where a subcomposition throws.
     */
    @Composable
    private fun FittedText(
        text: String,
        style: androidx.compose.ui.text.TextStyle,
        maxLines: Int,
        minScale: Float,
        modifier: Modifier = Modifier,
        heading: Boolean = false
    ) {
        val measurer = rememberTextMeasurer()
        val laidOut = remember { arrayOfNulls<androidx.compose.ui.text.TextLayoutResult>(1) }
        Box(
            modifier
                .semantics {
                    contentDescription = text
                    if (heading) heading()
                }
                .layout { measurable, constraints ->
                    val width = if (constraints.hasBoundedWidth) {
                        constraints.maxWidth
                    } else {
                        androidx.compose.ui.unit.Constraints.Infinity
                    }
                    fun measureAt(f: Float, lines: Int) = measurer.measure(
                        text,
                        style.copy(fontSize = style.fontSize * f, lineHeight = style.lineHeight * f),
                        maxLines = lines,
                        overflow = TextOverflow.Ellipsis,
                        constraints = androidx.compose.ui.unit.Constraints(maxWidth = width)
                    )
                    fun breaksWord(r: androidx.compose.ui.text.TextLayoutResult) =
                        r.hasVisualOverflow || (0 until r.lineCount - 1).any { line ->
                            val end = r.getLineEnd(line)
                            end in 1 until text.length &&
                                !text[end - 1].isWhitespace() &&
                                !text[end].isWhitespace() &&
                                text[end - 1] != '-'
                        }
                    var f = 1f
                    var r = measureAt(f, maxLines)
                    while (breaksWord(r) && f > minScale + 0.001f) {
                        f = (f - 0.03f).coerceAtLeast(minScale)
                        r = measureAt(f, maxLines)
                    }
                    if (breaksWord(r)) r = measureAt(minScale, Int.MAX_VALUE)
                    laidOut[0] = r
                    val placeable = measurable.measure(androidx.compose.ui.unit.Constraints.fixed(0, 0))
                    val w = if (constraints.hasBoundedWidth) width else r.size.width
                    layout(w, r.size.height) { placeable.place(0, 0) }
                }
                .drawBehind { laidOut[0]?.let { drawText(it) } }
        )
    }

    // ------------------------------------------------------------------ chrome

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
        val scheme = MaterialTheme.colorScheme
        val bar = barColor()
        val density = androidx.compose.ui.platform.LocalDensity.current

        // UINavigationBar's scroll-edge behaviour: at rest the bar is the
        // canvas, with no hairline and no inline title, because the large
        // title in the content is the title. Once that title has scrolled
        // under the bar, the bar takes its material, the hairline fades in
        // and the title appears inline, centred, at 17pt semibold.
        val scrolled = remember(title) { androidx.compose.runtime.mutableFloatStateOf(0f) }
        val scrollWatcher = remember(title) {
            object : androidx.compose.ui.input.nestedscroll.NestedScrollConnection {
                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: androidx.compose.ui.input.nestedscroll.NestedScrollSource
                ): Offset {
                    scrolled.floatValue = (scrolled.floatValue - consumed.y).coerceAtLeast(0f)
                    return Offset.Zero
                }
            }
        }
        val edgeTarget = with(density) {
            ((scrolled.floatValue - 40.dp.toPx()) / 20.dp.toPx()).coerceIn(0f, 1f)
        }
        val edge by animateFloatAsState(edgeTarget, tween(design.motion.fastMillis), label = "barEdge")

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            containerColor = palette.canvas,
            topBar = {
                // A tab root in iOS opens on a large title, and at the scroll
                // edge its navigation bar is transparent, hairline-free and
                // carries no inline title: only the bar buttons. Every tab
                // here opens on AppScreenTitle, so an inline app name above it
                // was a second title — and an app name in the bar is the
                // Android toolbar habit, not a UINavigationBar one.
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(lerp(palette.canvas, bar, edge))
                        .statusBarsPadding()
                ) {
                  Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = title,
                        style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                        color = palette.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 112.dp)
                            .graphicsLayer { alpha = edge }
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 16.dp)
                            .heightIn(min = 52.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (onBack != null) {
                            // The UINavigationBar back item: chevron and the
                            // previous screen's title, both in the tint.
                            Row(
                                Modifier
                                    .tap(onBack)
                                    .heightIn(min = 44.dp)
                                    .padding(end = 8.dp)
                                    .testTag("nav_back"),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Outlined.ArrowBackIos,
                                    contentDescription = backLabel,
                                    tint = scheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                                if (backLabel != null) {
                                    Text(
                                        text = backLabel,
                                        style = styles.rowTitle,
                                        color = scheme.primary,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                        Spacer(Modifier.weight(1f))
                        // iOS 26 groups bar buttons on one Liquid Glass
                        // capsule: the glyphs sit on a surface that belongs
                        // to the bar instead of floating loose in the corner,
                        // and the capsule's edge lines up with the gutter
                        // the large title starts on.
                        val capsule = RoundedCornerShape(50)
                        CompositionLocalProvider(
                            androidx.compose.material3.LocalMinimumInteractiveComponentSize provides 0.dp
                        ) {
                            Row(
                                Modifier
                                    // No bar buttons, no capsule: an empty
                                    // glass pill is worse than none.
                                    .layout { measurable, constraints ->
                                        val placeable = measurable.measure(constraints)
                                        if (placeable.width <= 9.dp.roundToPx()) {
                                            layout(0, 0) {}
                                        } else {
                                            layout(placeable.width, placeable.height) { placeable.place(0, 0) }
                                        }
                                    }
                                    .height(40.dp)
                                    .then(
                                        if (!design.isDark) {
                                            Modifier.shadow(
                                                6.dp,
                                                capsule,
                                                ambientColor = Color.Black.copy(alpha = 0.06f),
                                                spotColor = Color.Black.copy(alpha = 0.10f)
                                            )
                                        } else {
                                            Modifier
                                        }
                                    )
                                    .clip(capsule)
                                    .background(
                                        if (design.isDark) Color(0xFF1C1C1E) else Color.White.copy(alpha = 0.92f)
                                    )
                                    .border(
                                        0.5.dp,
                                        if (design.isDark) Color.White.copy(alpha = 0.12f) else Color.Black.copy(alpha = 0.05f),
                                        capsule
                                    )
                                    .padding(horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                content = actions
                            )
                        }
                    }
                  }
                    Hairline(Modifier.graphicsLayer { alpha = edge })
                }
            },
            bottomBar = {
                Surface(color = bar) {
                    Column(Modifier.navigationBarsPadding().testTag("bottom_nav_bar")) {
                        Hairline()
                        Row(
                            Modifier
                                .fillMaxWidth()
                                // 6 + 25 + 2 + label ≈ the 49pt UITabBar.
                                .padding(top = 6.dp, bottom = 6.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.Top
                        ) {
                            navItems.forEachIndexed { index, item ->
                                val isSelected = index == selectedNavIndex
                                // Unselected tab items are UIKit's secondary
                                // label, not its tertiary: at 30% the glyphs
                                // wash out into the bar.
                                val tint by animateColorAsState(
                                    if (isSelected) scheme.primary else palette.textSecondary,
                                    tween(design.motion.fastMillis, easing = design.motion.standardEasing),
                                    label = "tabTint"
                                )
                                // iOS 17's tab item answers selection with a
                                // small symbol bounce: the glyph dips and
                                // springs back past rest. The spring is
                                // under-damped on purpose — this is the one
                                // place UIKit lets a control visibly bounce.
                                val bounce by animateFloatAsState(
                                    if (isSelected) 1f else 0.9f,
                                    spring(dampingRatio = 0.45f, stiffness = Spring.StiffnessMediumLow),
                                    label = "tabBounce"
                                )
                                val tabSource = remember { MutableInteractionSource() }
                                val tabPressed by tabSource.collectIsPressedAsState()
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .pressable(tabSource, { onNavSelected(index) })
                                        .padding(horizontal = 2.dp)
                                        .testTag(item.tag),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Unselected items rest a hair smaller so
                                    // the selected glyph lands on its spring;
                                    // at rest the difference is a single
                                    // pixel and reads only as emphasis.
                                    val rest = if (isSelected) bounce else 1f
                                    Icon(
                                        imageVector = if (isSelected) item.selectedIcon else item.icon,
                                        contentDescription = item.label,
                                        tint = tint,
                                        modifier = Modifier
                                            .size(25.dp)
                                            .graphicsLayer {
                                                val s = rest * if (tabPressed) 0.88f else 1f
                                                scaleX = s
                                                scaleY = s
                                                alpha = if (tabPressed) 0.6f else 1f
                                            }
                                    )
                                    Spacer(Modifier.height(2.dp))
                                    // Ten destinations will not sit on one line
                                    // at tab-bar width; wrapping is the honest
                                    // tab-bar answer, not a scrolling rail.
                                    Text(
                                        text = item.label,
                                        style = styles.navLabel,
                                        color = tint,
                                        textAlign = TextAlign.Center,
                                        maxLines = 2
                                    )
                                }
                            }
                        }
                    }
                }
            },
            content = { padding ->
                // One tint, everywhere. The palette's accent is iOS blue, and
                // screens that reach for `palette.accent` (rubric labels, the
                // rosary cross, the current weekday) were striking blue into an
                // app whose tint is the liturgical colour — two tints on one
                // screen is the first thing an iOS reviewer flags. Inside the
                // scaffold the palette's accent *is* the live tint.
                val tinted = remember(design, scheme.primary) {
                    design.copy(
                        palette = palette.copy(
                            accent = scheme.primary,
                            // Gold is the Vatican's second ink. Here it was a
                            // mustard second accent on catechism references
                            // and parish districts beside the green; iOS
                            // metadata takes the tint, so gold does too.
                            gold = scheme.primary,
                            accentMuted = lerp(
                                palette.groupedSurface,
                                scheme.primary,
                                if (design.isDark) 0.22f else 0.12f
                            )
                        )
                    )
                }
                CompositionLocalProvider(LocalAppDesign provides tinted) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollWatcher)
                    ) {
                        content(padding)
                        // The bars are materials, not walls: content passing
                        // under them dissolves into the bar over a short
                        // run instead of stopping dead at its edge — the
                        // nearest a frame-rendered surface gets to
                        // UIBarAppearance's blur. The top veil only exists
                        // once something has scrolled under the bar.
                        Box(
                            Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = padding.calculateTopPadding())
                                .fillMaxWidth()
                                .height(14.dp)
                                .graphicsLayer { alpha = edge }
                                .background(Brush.verticalGradient(listOf(bar.copy(alpha = 0.85f), bar.copy(alpha = 0f))))
                        )
                        Box(
                            Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = padding.calculateBottomPadding())
                                .fillMaxWidth()
                                .height(22.dp)
                                .background(Brush.verticalGradient(listOf(bar.copy(alpha = 0f), bar.copy(alpha = 0.92f))))
                        )
                    }
                }
            }
        )
    }

    @Composable
    override fun TopBar(
        title: String,
        subtitle: String?,
        onBack: (() -> Unit)?,
        actions: @Composable RowScope.() -> Unit
    ) {
        val palette = LocalAppDesign.current.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth().background(barColor())) {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 4.dp).heightIn(min = 44.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.width(44.dp)) {
                    if (onBack != null) {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.AutoMirrored.Outlined.ArrowBackIos,
                                contentDescription = null,
                                tint = scheme.primary,
                                modifier = Modifier.size(19.dp)
                            )
                        }
                    }
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = palette.textPrimary,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                    if (subtitle != null) {
                        // UIKit's two-line navigation title: a 12pt
                        // secondary line in sentence case under the 17pt title.
                        Text(
                            text = subtitle,
                            style = styles.rowSubtitle.copy(fontSize = styles.navLabel.fontSize * 1.2f),
                            color = palette.textSecondary,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
                Row(
                    Modifier.widthIn(min = 44.dp).wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }
            Hairline()
        }
    }

    @Composable
    override fun ScreenTitle(title: String, subtitle: String?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        // A UINavigationBar large title carries no subtitle. A short one —
        // a date, a Latin incipit, a patron — becomes the eyebrow the App
        // Store's Today tab and Fitness set *above* the title, in footnote
        // capitals; a tagline sentence is dropped, as iOS has nowhere for it.
        val eyebrow = subtitle?.takeIf { sub ->
            sub.length <= 30 && !sub.trimEnd().endsWith(".") &&
                (sub.any { it.isDigit() } || (sub.length <= 22 && ',' !in sub))
        }
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .padding(
                    top = if (eyebrow != null) design.spacing.headerTopGap - 6.dp else design.spacing.headerTopGap,
                    bottom = 10.dp
                ),
            horizontalAlignment = design.decor.titleAlignment
        ) {
            if (eyebrow != null) {
                Text(
                    text = eyebrow.uppercase(),
                    style = styles.rowSubtitle.copy(
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.2.sp
                    ),
                    color = palette.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(1.dp))
            }
            // A UINavigationBar large title is one line. A long one shrinks
            // toward title1 (28pt) before it is allowed to wrap; two lines of
            // 34pt bold is a poster, not a navigation title.
            FittedText(
                text = title,
                // SF tightens by roughly 26/1000 em at display sizes; the
                // scale's -0.6 is short of that at 34pt.
                style = styles.screenTitle.copy(color = palette.textPrimary),
                maxLines = 1,
                minScale = 30f / 38f,
                heading = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    // --------------------------------------------------------------- structure

    @Composable
    override fun SectionHeader(title: String, subtitle: String?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        val insideCard = LocalInsideCard.current
        if (insideCard) {
            // Health's card header: the card's subject in the tint at
            // subheadline semibold, sentence case, then the card's own title.
            // An all-caps grey eyebrow on top of a bold line is the Android
            // "overline" pattern; Apple apps don't letter-space shouting caps
            // inside a card.
            Column(modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                Text(
                    text = title,
                    style = styles.screenSubtitle.copy(fontWeight = FontWeight.SemiBold),
                    color = scheme.primary
                )
                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                        color = palette.textPrimary
                    )
                }
            }
            return
        }
        // Outside a card: the prominent section header of Health, Fitness,
        // Books and Journal (`.headerProminence(.increased)`) — a 20pt bold
        // title in sentence case standing on the card edge, not the 13pt
        // grey capitals of a 2014 settings table. On a screen made of cards
        // rather than one long table, the small caps read as Android's
        // overline style and stack into a wall of shouting labels.
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter + 1.dp)
                .padding(top = design.spacing.insetGroupGap, bottom = 8.dp)
        ) {
            // A count ("5 books") rides the header's baseline as trailing
            // secondary text, as Music and Files set it; a sentence of
            // explanation drops to a footnote under the title rather than a
            // second header line at body size.
            val trailing = subtitle != null && subtitle.length <= 18 && !subtitle.endsWith(".")
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = title,
                    style = styles.sectionTitle.copy(fontSize = styles.sectionTitle.fontSize * (20f / 22f)),
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

    @Composable
    override fun Card(
        modifier: Modifier,
        onClick: (() -> Unit)?,
        content: @Composable ColumnScope.() -> Unit
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        // A tappable card highlights like a cell — the flat grey wash — and
        // gives a little under the finger, as App Store and Fitness cards do.
        // The wash snaps on at touch-down and fades out on release, which is
        // UITableViewCell's own timing.
        val fill by animateColorAsState(
            if (pressed && onClick != null) {
                lerp(palette.groupedSurface, palette.selectionTint, if (design.isDark) 1f else 0.9f)
            } else {
                palette.groupedSurface
            },
            if (pressed) tween(0) else tween(design.motion.mediumMillis, easing = design.motion.standardEasing),
            label = "cardFill"
        )
        Column(
            modifier
                .fillMaxWidth()
                .then(if (onClick != null) Modifier.pressScale(pressed, 0.98f) else Modifier)
                .clip(design.shapes.card)
                .background(fill)
                .pressable(source, onClick)
                .padding(design.spacing.cardPadding)
        ) {
            val scope = this
            CompositionLocalProvider(LocalInsideCard provides true) {
                with(scope) { content() }
            }
        }
    }

    @Composable
    override fun ListGroup(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) {
        val design = LocalAppDesign.current
        val palette = design.palette
        // Clipping rather than bordering is what lets each row paint its own
        // background and its own trailing hairline. A table view draws no
        // separator under its last row, so the inner column reports itself one
        // physical pixel short of its content and the final hairline lands
        // outside the clip instead of reading as a bottom border on the card.
        val registry = remember { WellRegistry() }
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .clip(design.shapes.listGroup)
                .background(palette.groupedSurface)
        ) {
          CompositionLocalProvider(LocalWellRegistry provides registry) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        val trimmed =
                            (placeable.height - HairlineWeight.roundToPx()).coerceAtLeast(0)
                        layout(placeable.width, trimmed) { placeable.place(0, 0) }
                    },
                content = content
            )
          }
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
        val scheme = MaterialTheme.colorScheme

        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()

        // Pressed is UIKit's flat grey wash. Selected is Books' current-chapter
        // treatment: a breath of tint behind the row and the title in the tint
        // colour, not the slab of systemGray4 a press leaves behind.
        val fill by animateColorAsState(
            when {
                pressed -> palette.selectionTint.copy(alpha = if (design.isDark) 1f else 0.9f)
                selected -> scheme.primary.copy(alpha = if (design.isDark) 0.16f else 0.08f)
                else -> palette.selectionTint.copy(alpha = 0f)
            },
            // Highlight on touch-down is instant; the release fades over
            // UIKit's deselect duration rather than blinking off.
            if (pressed) tween(0) else tween(design.motion.mediumMillis, easing = design.motion.standardEasing),
            label = "rowFill"
        )
        val titleColor = if (selected) scheme.primary else palette.textPrimary

        // A row inside a card (an action under a parish's details, say) is
        // already inset by the card's padding: a second 16dp inset pushed
        // its glyph 32dp in, and its trailing separator drew a stray rule
        // across the bottom of the card. There the rule goes *above* the
        // row, dividing it from the content it acts on, as Contacts does,
        // and the row sits flush with the card's text column.
        val inCard = LocalInsideCard.current
        val rowInset = if (inCard) 0.dp else RowTextInset
        Column(modifier.fillMaxWidth()) {
            if (inCard) Hairline()
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(fill)
                    .then(
                        if (onClick != null) {
                            Modifier.clickable(
                                interactionSource = source,
                                indication = null,
                                onClick = onClick
                            )
                        } else {
                            Modifier
                        }
                    )
                    .heightIn(min = design.spacing.rowMinHeight)
                    // 8dp around a 41dp two-line stack gives the 57-58pt
                    // subtitle cell UIKit ships; the token's 11dp gave 64.
                    .padding(horizontal = rowInset, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    // Settings' wells are saturated with a white glyph in
                    // both appearances; never a pastel tile with a dark glyph.
                    // The tile is lit from above, as every Settings icon is
                    // since iOS 7: a few percent lighter at the top edge,
                    // never a visible gradient, and it stops the well from
                    // reading as a flat Android badge.
                    val registry = LocalWellRegistry.current
                    val hue = remember(leadingIcon.name, registry) {
                        registry?.claim(wellHue(leadingIcon)) ?: wellHue(leadingIcon)
                    }
                    val well = hue.of(design.isDark)
                    Box(
                        Modifier
                            .size(IconWellSize)
                            .clip(IconWellShape)
                            .background(
                                Brush.verticalGradient(listOf(lerp(well, Color.White, 0.14f), well))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = solidGlyph(leadingIcon),
                            contentDescription = null,
                            tint = onAccentFill(),
                            modifier = Modifier.size(IconWellGlyph)
                        )
                    }
                    Spacer(Modifier.width(design.spacing.iconLabelGap))
                }
                Column(Modifier.weight(1f)) {
                    Text(title, style = styles.rowTitle, color = titleColor)
                    if (subtitle != null) {
                        Spacer(Modifier.height(1.dp))
                        Text(subtitle, style = styles.rowSubtitle, color = palette.textSecondary)
                    }
                }
                if (trailingText != null) {
                    Spacer(Modifier.width(8.dp))
                    Text(trailingText, style = styles.rowTitle, color = palette.textSecondary)
                }
                if (showsNavigationAffordance && design.decor.usesChevrons) {
                    Spacer(Modifier.width(8.dp))
                    Chevron(tint = chevronGrey())
                }
            }
            if (!inCard) {
                InsetHairline(
                    leadingInset = if (leadingIcon != null) {
                        RowTextInset + IconWellSize + design.spacing.iconLabelGap
                    } else {
                        RowTextInset
                    }
                )
            }
        }
    }

    @Composable
    override fun Divider(modifier: Modifier) {
        val design = LocalAppDesign.current
        // Inside a card the rule spans the card's content measure, edge to
        // edge of the text it separates. On the canvas it is inset by the
        // gutter on *both* sides: a rule that starts at the gutter and runs
        // off the right edge belongs to no column and reads as a stray line.
        if (LocalInsideCard.current) {
            Hairline(modifier)
        } else {
            Hairline(modifier.padding(horizontal = design.spacing.gutter))
        }
    }

    /** Cupertino has no ornament; the gap between groups is the ornament. */
    @Composable
    override fun Ornament(modifier: Modifier) {
        // Half a section gap: the header that follows brings its own 22dp, and
        // the full 28 on top of it opened a 50dp hole no table view has.
        Spacer(modifier.height(LocalAppDesign.current.spacing.sectionGap / 2))
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
        val design = LocalAppDesign.current
        val styles = LocalAppTextStyles.current
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        // UIButton.Configuration.filled at .large: 50pt, 12pt corners, white
        // semibold label. Disabled is UIKit's grey fill and tertiary label —
        // a ghost of the tint is Material's disabled state, not Apple's.
        val content = if (enabled) onAccentFill() else design.palette.textTertiary
        Row(
            modifier
                .pressScale(pressed)
                .graphicsLayer { alpha = if (pressed) 0.78f else 1f }
                .heightIn(min = 50.dp)
                .clip(design.shapes.button)
                .background(if (enabled) accentFill() else disabledFill())
                .pressable(source, onClick, enabled)
                .padding(horizontal = 20.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, null, tint = content, modifier = Modifier.size(17.dp))
                Spacer(Modifier.width(7.dp))
            }
            Text(
                text = label,
                style = styles.buttonLabel,
                color = content,
                textAlign = TextAlign.Center
            )
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
        val design = LocalAppDesign.current
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        // iOS's tinted button: the accent at low alpha, label in the accent.
        // Derived from the live accent rather than from palette.accentMuted,
        // which is keyed to the palette's own iOS blue and turns this button
        // blue in a green app.
        val wash = scheme.primary.copy(alpha = if (design.isDark) 0.24f else 0.12f)
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        val content = if (enabled) scheme.primary else design.palette.textTertiary
        Row(
            modifier
                .pressScale(pressed)
                .graphicsLayer { alpha = if (pressed) 0.65f else 1f }
                .heightIn(min = 50.dp)
                .clip(design.shapes.button)
                .background(if (enabled) wash else disabledFill())
                .pressable(source, onClick, enabled)
                .padding(horizontal = 20.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, null, tint = content, modifier = Modifier.size(17.dp))
                Spacer(Modifier.width(7.dp))
            }
            Text(
                text = label,
                style = styles.buttonLabel,
                color = content,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    override fun SearchField(
        value: String,
        onValueChange: (String) -> Unit,
        placeholder: String,
        modifier: Modifier
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        // UISearchBar's field is 36pt. Material's TextField enforces a 56pt
        // minimum no matter what padding it is given, so the field is built
        // out of a BasicTextField in a Box whose height we own outright.
        val fill = systemFill(3)
        androidx.compose.foundation.text.BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.fillMaxWidth().height(36.dp),
            singleLine = true,
            textStyle = styles.rowTitle.copy(color = palette.textPrimary),
            cursorBrush = SolidColor(scheme.primary),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(design.shapes.field)
                        .background(fill)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.Search,
                        null,
                        tint = palette.textSecondary,
                        modifier = Modifier.size(17.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Box(Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                        if (value.isEmpty()) {
                            Text(
                                placeholder,
                                style = styles.rowTitle,
                                color = palette.textSecondary,
                                maxLines = 1
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
    }

    @Composable
    override fun SegmentedTabs(
        options: List<String>,
        selectedIndex: Int,
        onSelect: (Int) -> Unit,
        modifier: Modifier
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val trackShape = design.shapes.buttonSmall
        val pillShape = RoundedCornerShape(7.dp)
        // UISegmentedControl's track is secondarySystemFill — translucent, so
        // it reads the same on the canvas and inside a card. The selected
        // segment is white in light and a raised grey in dark, *lighter*
        // than its track in both: a pill darker than the track inverts the
        // control and is the first thing that looks wrong about it.
        val track = systemFill(2)
        val pill = if (design.isDark) Color(0xFF636366) else Color.White
        val count = options.size.coerceAtLeast(1)

        BoxWithConstraints(
            modifier
                .fillMaxWidth()
                // 32pt track, 2pt inset, 28pt pill — UISegmentedControl.
                .height(32.dp)
                .clip(trackShape)
                .background(track)
                .padding(2.dp)
        ) {
            val segment = maxWidth / count
            // The pill is one object that slides between segments on a
            // critically-damped spring, as UIKit's does — not a background
            // that blinks from one segment to the next.
            val pillOffset by animateDpAsState(
                segment * selectedIndex.coerceIn(0, count - 1),
                spring(dampingRatio = 0.86f, stiffness = 500f),
                label = "segmentPill"
            )
            Box(
                Modifier
                    .offset(x = pillOffset)
                    .width(segment)
                    .fillMaxHeight()
                    // The only shadow in the whole identity: the selected
                    // segment is the one thing iOS genuinely lifts, and only
                    // where there is light to lift it into.
                    .then(
                        if (!design.isDark) {
                            Modifier.shadow(2.dp, pillShape, ambientColor = Color.Black.copy(alpha = 0.10f), spotColor = Color.Black.copy(alpha = 0.14f))
                        } else {
                            Modifier
                        }
                    )
                    .clip(pillShape)
                    .background(pill)
                    .border(0.5.dp, Color.Black.copy(alpha = if (design.isDark) 0f else 0.04f), pillShape)
            )
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                options.forEachIndexed { index, option ->
                    val isSelected = index == selectedIndex
                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .tap({ onSelect(index) }),
                        contentAlignment = Alignment.Center
                    ) {
                        if (index > 0) {
                            // Apple rules between two unselected segments and
                            // drops the rule on either side of the selected one.
                            val ruled = index != selectedIndex && index - 1 != selectedIndex
                            val ruleAlpha by animateFloatAsState(
                                if (ruled) 1f else 0f,
                                tween(design.motion.fastMillis),
                                label = "segmentRule"
                            )
                            Box(
                                Modifier
                                    .align(Alignment.CenterStart)
                                    .width(1.dp)
                                    .height(16.dp)
                                    .graphicsLayer { alpha = ruleAlpha }
                                    .background(palette.hairline)
                            )
                        }
                        // UISegmentedControl shrinks a long title to fit its
                        // segment (adjustsFontSizeToFitWidth) before it
                        // truncates. Measured against the semibold weight
                        // whether selected or not, so a label does not change
                        // size as selection moves.
                        val base = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                        )
                        val measurer = rememberTextMeasurer()
                        BoxWithConstraints(
                            Modifier.padding(horizontal = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            val available = constraints.maxWidth
                            val natural = measurer.measure(
                                option,
                                base.copy(fontWeight = FontWeight.SemiBold),
                                softWrap = false,
                                maxLines = 1
                            ).size.width
                            val scale = if (natural > available && natural > 0) {
                                (available.toFloat() / natural).coerceAtLeast(0.7f)
                            } else {
                                1f
                            }
                            Text(
                                text = option,
                                style = base.copy(fontSize = base.fontSize * scale),
                                color = palette.textPrimary,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                softWrap = false,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    override fun Chip(label: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        // The capsule filter button of Photos, Music and the App Store:
        // tertiary-fill capsule at rest, the tint with a white label when on.
        Box(
            modifier
                .pressScale(pressed, 0.94f)
                .graphicsLayer { alpha = if (pressed) 0.7f else 1f }
                .height(32.dp)
                .clip(design.shapes.chip)
                .background(
                    if (selected) accentFill()
                    else systemFill(2)
                )
                .pressable(source, onClick)
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = LocalAppTextStyles.current.screenSubtitle.copy(
                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
                ),
                color = if (selected) onAccentFill() else palette.textPrimary,
                maxLines = 1
            )
        }
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
        val scheme = MaterialTheme.colorScheme

        // The serif is deliberate — Apple Books sets book text in a serif and
        // its chrome in SF — but a hanging number column in the chrome's sans
        // at a 48dp indent was what made it look accidental: the reading
        // column started 32dp inside every other element on the screen and in
        // a different voice. The number is now a true superscript in the
        // reading face, so the measure sits on the same 20dp text inset as a
        // grouped row's title and the serif reads as one typographic system.
        // A verse carrying commentary says so by tinting its own number, which
        // is quieter and more legible than the floating dot in the right
        // margin that it replaces.
        val body = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = styles.scripture.fontSize * 0.62f,
                    baselineShift = BaselineShift.Superscript,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.sp,
                    color = if (hasCommentary) scheme.primary else palette.textTertiary
                )
            ) {
                append(number.toString())
            }
            append(" ")
            append(text)
        }

        Box(
            modifier
                .fillMaxWidth()
                .widthIn(max = design.spacing.readingMaxWidth)
                .tap(onClick)
                .padding(horizontal = design.spacing.gutter + 4.dp)
                .padding(top = if (isFirstOfChapter) 12.dp else 5.dp, bottom = 5.dp)
        ) {
            Text(
                text = body,
                style = styles.scripture,
                color = palette.textPrimary
            )
        }
    }

    @Composable
    override fun MetricTile(value: String, caption: String, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        // Health's summary tile: a tinted glyph and its label on one line, the
        // figure underneath. Stacking glyph, figure and caption vertically ran
        // the tile to 130dp and left the three of them ragged at the bottom.
        Column(
            modifier
                .clip(design.shapes.card)
                .background(palette.groupedSurface)
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                if (icon != null) {
                    Icon(
                        icon,
                        null,
                        tint = scheme.primary,
                        // Nudged down so the glyph's optical centre sits on the
                        // label's x-height rather than above its cap line.
                        modifier = Modifier.padding(top = 1.dp).size(15.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                }
                // A word is never broken across lines ("Completio / ns" in a
                // three-up row is the tell of an Android layout). Like a
                // UILabel with adjustsFontSizeToFitWidth, the caption steps
                // its size down until no line ends inside a word. Tiles sit
                // in rows sized by intrinsic height, so the width cannot be
                // measured up front (BoxWithConstraints throws there).
                FittedText(
                    text = caption,
                    style = styles.rowSubtitle.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = scheme.primary
                    ),
                    maxLines = 2,
                    minScale = 0.72f,
                    modifier = Modifier.weight(1f)
                )
            }
            // The figure sits on the tile's floor, not under its caption:
            // tiles in a row stretch to the tallest one, and Health keeps
            // every figure on one baseline whether its label took one line
            // or two.
            Spacer(Modifier.height(6.dp))
            Spacer(Modifier.weight(1f))
            Text(
                text = value,
                style = styles.metric,
                color = palette.textPrimary,
                maxLines = 1,
                softWrap = false
            )
        }
    }

    @Composable
    override fun EmptyState(title: String, body: String?, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter * 2, vertical = design.spacing.sectionGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ContentUnavailableView's symbol, given the warmth Journal's
            // onboarding has: the glyph in the tint, standing in a soft
            // tinted halo lit from above, instead of a grey icon that reads
            // as "nothing here".
            val tint = MaterialTheme.colorScheme.primary
            Box(
                Modifier
                    .size(84.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                if (design.isDark) tint.copy(alpha = 0.30f) else lerp(Color.White, tint, 0.16f),
                                if (design.isDark) tint.copy(alpha = 0.14f) else lerp(Color.White, tint, 0.07f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when {
                        icon == null -> Icons.Outlined.Inbox
                        // A seated meditator says yoga, not a rule of life.
                        icon.name.endsWith("SelfImprovement") -> LatinCross
                        icon.name.endsWith("EditNote") -> Icons.Outlined.Create
                        else -> icon
                    },
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(42.dp)
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(
                text = title,
                // iOS sets an empty state at body weight, not at a headline:
                // headlineSmall's 20pt semibold shouted over the whole screen.
                // ContentUnavailableView's title: title3 semibold. Body
                // weight read as a stray line of copy, a headline shouted.
                style = styles.rowTitle.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = styles.rowTitle.fontSize * (20f / 17f),
                    lineHeight = styles.rowTitle.lineHeight * (25f / 22f),
                    letterSpacing = (-0.45).sp
                ),
                color = palette.textPrimary,
                textAlign = TextAlign.Center
            )
            if (body != null) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = body,
                    style = styles.screenSubtitle,
                    color = palette.textSecondary,
                    textAlign = TextAlign.Center
                )
            }
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
        if (accentColorHex != null) {
            LiturgicalHero(title, body, Color(accentColorHex), onClick, modifier)
            return
        }
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        val fill = if (pressed && onClick != null) {
            lerp(palette.groupedSurface, palette.selectionTint, if (design.isDark) 1f else 0.9f)
        } else {
            palette.groupedSurface
        }
        // A callout with no colour of its own: a tinted info glyph on the
        // leading edge, as Settings and Health set their explanatory notes —
        // not a swatch disc, which only means something when it shows a
        // liturgical colour.
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .then(if (onClick != null) Modifier.pressScale(pressed, 0.98f) else Modifier)
                .clip(design.shapes.card)
                .background(fill)
                .pressable(source, onClick)
                .heightIn(min = 60.dp)
                .padding(horizontal = RowTextInset, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.size(IconWellSize), contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Outlined.Info,
                    contentDescription = null,
                    tint = scheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Column(Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = styles.rowSubtitle.copy(fontWeight = FontWeight.SemiBold),
                    color = palette.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (body != null) {
                    Spacer(Modifier.height(1.dp))
                    Text(body, style = styles.rowTitle, color = palette.textPrimary)
                }
            }
            if (onClick != null) {
                Spacer(Modifier.width(8.dp))
                Chevron(tint = chevronGrey())
            }
        }
    }

    /**
     * The liturgical day as the app's signature object: the vestment itself.
     * The card is cut from the day's colour as a deep, saturated cloth — lit
     * from the top-leading corner, falling to shadow at the bottom-trailing
     * one, with the faint diagonal twill of a woven chasuble — and a tall
     * Latin cross is embroidered into it, tone on tone, running off the
     * bottom edge. The day is set large in the serif, white on the colour, as
     * Books and Journal set their covers. Nothing glows and nothing is a
     * decoration that repeats what the colour already says.
     *
     * A dated entry ("November 1 · Solemnity") is one of a run, as in the
     * calendar, and takes the restrained form: a white card with the
     * vestment colour as Calendar's leading event bar, so a list of them
     * reads as a colour-coded year rather than a stack of posters.
     */
    @Composable
    private fun LiturgicalHero(
        season: String,
        day: String?,
        vestment: Color,
        onClick: (() -> Unit)?,
        modifier: Modifier
    ) {
        if ('·' in season) {
            LiturgicalEntry(season, day, vestment, onClick, modifier)
            return
        }
        val design = LocalAppDesign.current
        val styles = LocalAppTextStyles.current
        val dark = design.isDark
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        // White vestments are shown in gold, as the rubrics allow.
        val isWhite = vestment.luminance() > 0.75f
        val base = remember(vestment, dark) {
            val hsv = FloatArray(3)
            android.graphics.Color.colorToHSV(
                (if (isWhite) Color(0xFFC49A2C) else vestment).toArgb(), hsv
            )
            if (hsv[1] > 0.12f) hsv[1] = hsv[1].coerceAtLeast(0.50f)
            var c = Color(android.graphics.Color.HSVToColor(hsv))
            // Deep enough that white type reads on the lightest corner.
            while (contrast(lerp(c, Color.White, 0.10f), Color.White) < 4.8f && hsv[2] > 0.12f) {
                hsv[2] -= 0.02f
                c = Color(android.graphics.Color.HSVToColor(hsv))
            }
            if (dark) lerp(c, Color.Black, 0.12f) else c
        }
        val lit = lerp(base, Color.White, 0.10f)
        val shade = lerp(base, Color.Black, if (dark) 0.40f else 0.32f)
        val shape = RoundedCornerShape(22.dp)
        Box(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .then(if (onClick != null) Modifier.pressScale(pressed, 0.98f) else Modifier)
                .then(
                    if (!dark) {
                        Modifier.shadow(10.dp, shape, ambientColor = base.copy(alpha = 0.35f), spotColor = base.copy(alpha = 0.45f))
                    } else {
                        Modifier
                    }
                )
                .clip(shape)
                .drawBehind {
                    val w = size.width
                    val h = size.height
                    // The cloth: lit top-leading, falling to shadow.
                    drawRect(
                        Brush.linearGradient(
                            listOf(lit, base, shade),
                            start = Offset(0f, 0f),
                            end = Offset(w, h)
                        )
                    )
                    // The light source, a soft sheen across the top.
                    drawRect(
                        Brush.radialGradient(
                            listOf(Color.White.copy(alpha = if (dark) 0.10f else 0.16f), Color.Transparent),
                            center = Offset(w * 0.18f, -h * 0.25f),
                            radius = w * 0.85f
                        )
                    )
                    // The weave: a fine diagonal twill, felt more than seen.
                    val step = 3.dp.toPx()
                    val thread = Color.White.copy(alpha = if (dark) 0.035f else 0.045f)
                    var x = -h
                    while (x < w) {
                        drawLine(thread, Offset(x, h), Offset(x + h, 0f), 0.5.dp.toPx())
                        x += step
                    }
                    // The embroidered cross, tone on tone, off the bottom edge.
                    val arm = 12.dp.toPx()
                    val cx = w - 62.dp.toPx()
                    val top = 22.dp.toPx()
                    val beamY = top + 34.dp.toPx()
                    val span = 33.dp.toPx()
                    val crossBrush = Brush.verticalGradient(
                        listOf(Color.White.copy(alpha = 0.17f), Color.White.copy(alpha = 0.05f)),
                        startY = top,
                        endY = h
                    )
                    drawRoundRect(
                        crossBrush,
                        topLeft = Offset(cx - arm / 2f, top),
                        size = androidx.compose.ui.geometry.Size(arm, h + arm),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(arm / 5f)
                    )
                    drawRoundRect(
                        crossBrush,
                        topLeft = Offset(cx - span, beamY),
                        size = androidx.compose.ui.geometry.Size(span * 2f, arm),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(arm / 5f)
                    )
                }
                .border(
                    0.5.dp,
                    Color.White.copy(alpha = if (dark) 0.10f else 0.14f),
                    shape
                )
                .then(
                    if (pressed && onClick != null) Modifier.background(Color.Black.copy(alpha = 0.12f)) else Modifier
                )
                .pressable(source, onClick)
                .heightIn(min = 150.dp)
                .padding(start = 20.dp, end = 20.dp, top = 18.dp, bottom = 20.dp)
        ) {
            Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.align(Alignment.BottomStart)) {
                Column(Modifier.weight(1f).padding(end = 56.dp)) {
                    Text(
                        text = season.uppercase(),
                        style = styles.rowSubtitle.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.6.sp
                        ),
                        color = Color.White.copy(alpha = 0.80f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    val headline = day?.substringBefore('\n')
                    val particulars = day?.substringAfter('\n', "")?.trim().orEmpty()
                    if (headline != null) {
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = headline,
                            style = styles.rowTitle.copy(
                                fontFamily = com.example.ui.theme.AppFonts.EbGaramond,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = styles.rowTitle.fontSize * (27f / 17f),
                                lineHeight = styles.rowTitle.lineHeight * (31f / 22f),
                                letterSpacing = (-0.1).sp
                            ),
                            color = Color.White,
                            maxLines = 4
                        )
                    }
                    if (particulars.isNotEmpty()) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = particulars,
                            style = styles.rowSubtitle,
                            color = Color.White.copy(alpha = 0.80f),
                            maxLines = 2
                        )
                    }
                }
                if (onClick != null) {
                    Chevron(tint = Color.White.copy(alpha = 0.7f), modifier = Modifier.padding(bottom = 4.dp))
                }
            }
        }
    }

    /** The calendar's dated entry: a white card with the vestment as its event bar. */
    @Composable
    private fun LiturgicalEntry(
        season: String,
        day: String?,
        vestment: Color,
        onClick: (() -> Unit)?,
        modifier: Modifier
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val dark = design.isDark
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        val isWhite = vestment.luminance() > 0.75f
        // Gold for white vestments, struck bright in dark mode: on #1C1C1E a
        // mid gold muddies to brown.
        val bar = when {
            isWhite -> if (dark) Color(0xFFE3BE4F) else Color(0xFFD1A21F)
            dark -> lerp(vestment, Color.White, 0.18f)
            else -> vestment
        }
        val surface = if (pressed && onClick != null) {
            lerp(palette.groupedSurface, palette.selectionTint, if (dark) 1f else 0.9f)
        } else {
            palette.groupedSurface
        }
        val ink = inkOn(bar, surface, 4.5f)
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .then(if (onClick != null) Modifier.pressScale(pressed, 0.98f) else Modifier)
                .clip(design.shapes.listGroup)
                .background(surface)
                .drawBehind {
                    // A breath of the colour behind the bar, gone by mid-card.
                    drawRect(
                        Brush.horizontalGradient(
                            listOf(bar.copy(alpha = if (dark) 0.16f else 0.09f), Color.Transparent),
                            startX = 0f,
                            endX = size.width * 0.6f
                        )
                    )
                }
                .pressable(source, onClick)
                .height(androidx.compose.foundation.layout.IntrinsicSize.Min)
                .padding(start = 12.dp, end = 16.dp, top = 11.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(2.dp))
                    .background(bar)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = season,
                    style = styles.rowSubtitle.copy(fontWeight = FontWeight.SemiBold),
                    color = ink,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                val headline = day?.substringBefore('\n')
                val particulars = day?.substringAfter('\n', "")?.trim().orEmpty()
                if (headline != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = headline,
                        style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                        color = palette.textPrimary,
                        maxLines = 3
                    )
                }
                if (particulars.isNotEmpty()) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = particulars,
                        style = styles.rowSubtitle,
                        color = palette.textSecondary,
                        maxLines = 2
                    )
                }
            }
            if (onClick != null) {
                Spacer(Modifier.width(8.dp))
                Chevron(tint = chevronGrey())
            }
        }
    }
}
