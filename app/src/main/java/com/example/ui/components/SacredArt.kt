package com.example.ui.components

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.clearAndSetSemantics
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sign
import kotlin.math.sin
import kotlin.math.sqrt
import androidx.compose.ui.graphics.Canvas as ComposeCanvas

/**
 * What a [SacredArt] vignette depicts. One per top-level tab, so every tab
 * opens on a moment of imagery rather than on a list.
 */
enum class SacredSubject {
    /** The liturgical day: a Latin cross in glory before a rose window. */
    DAY,

    /** The open Gospel book, with light falling on its pages. */
    SCRIPTURE,

    /** A five-decade rosary flowing down to its crucifix. */
    ROSARY,

    /** A rank of votive candles burning before the altar. */
    PRAYER,

    /** A domed basilica façade with its embracing colonnade. */
    CHURCH,

    /** The Holy Spirit as a dove descending, wings spread, in glory. */
    DOVE
}

/**
 * A procedurally drawn devotional vignette that fills [modifier], rendered in
 * the idiom of the active identity:
 *
 * - **Cupertino** — luminous multi-stop gradients, translucent layers, soft
 *   bloom and lift, on a tinted backdrop.
 * - **Material 3** — flat tonal shapes cut from the scheme's primary and
 *   tertiary tones, sitting on expressive cookie / burst forms.
 * - **Vatican** — a missal frontispiece: engraved hatching and cross-hatching
 *   in ink, gold-leaf highlights and rubric red, on a transparent ground so
 *   the parchment shows through.
 *
 * Composed around a central square stage, so it reads at 16:9 and 2:1 banner
 * proportions (wide subjects spread to the edges) and equally well square.
 *
 * All geometry is built once per size in [drawWithCache], and the subject
 * itself is rasterised once into a bitmap; the only per-frame work is a very
 * slow breathing of the glory and glow behind it and a candle-flame flicker.
 *
 * @param accent overrides the identity's key colour — the Cupertino tint, the
 *   Material primary, or the Vatican rubric.
 */
@Composable
fun SacredArt(
    subject: SacredSubject,
    modifier: Modifier = Modifier,
    accent: Color? = null
) {
    val design = LocalAppDesign.current
    val scheme = MaterialTheme.colorScheme
    val palette = design.palette
    val inks = ArtInks(
        language = design.language,
        dark = design.isDark,
        key = accent ?: when (design.language) {
            DesignLanguage.VATICAN -> palette.accent
            else -> scheme.primary
        },
        gold = palette.gold,
        ink = palette.textPrimary,
        surface = scheme.surface,
        tertiary = scheme.tertiary
    )

    // Previews and screenshots hold the glory still; the app lets it breathe.
    val still = androidx.compose.ui.platform.LocalInspectionMode.current
    val breath: androidx.compose.runtime.State<Float> = if (still) {
        androidx.compose.runtime.remember { androidx.compose.runtime.mutableFloatStateOf(0.5f) }
    } else {
        val transition = rememberInfiniteTransition(label = "sacredArt")
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 5600, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "breath"
        )
    }

    Spacer(
        modifier
            .clearAndSetSemantics { }
            .drawWithCache {
                if (size.width < 1f || size.height < 1f) {
                    return@drawWithCache onDrawBehind { }
                }
                val stage = Stage(size, density)
                val painter = when (inks.language) {
                    DesignLanguage.APPLE -> ApplePainter(stage, inks)
                    DesignLanguage.MATERIAL -> MaterialPainter(stage, inks)
                    DesignLanguage.VATICAN -> VaticanPainter(stage, inks)
                }
                when (subject) {
                    SacredSubject.DAY -> painter.day()
                    SacredSubject.SCRIPTURE -> painter.scripture()
                    SacredSubject.ROSARY -> painter.rosary()
                    SacredSubject.PRAYER -> painter.prayer()
                    SacredSubject.CHURCH -> painter.church()
                    SacredSubject.DOVE -> painter.dove()
                }
                // The subject never moves: rasterise it once per size.
                val bitmap = ImageBitmap(size.width.roundToInt(), size.height.roundToInt())
                CanvasDrawScope().draw(this, layoutDirection, ComposeCanvas(bitmap), size) {
                    val mid = painter.mid
                    for (i in mid.indices) mid[i](this, 0.5f)
                }
                val back = painter.back
                val front = painter.front
                onDrawBehind {
                    val t = breath.value
                    for (i in back.indices) back[i](this, t)
                    drawImage(bitmap)
                    for (i in front.indices) front[i](this, t)
                }
            }
    )
}

// ============================================================== foundations

private typealias Op = DrawScope.(Float) -> Unit

@Immutable
private data class ArtInks(
    val language: DesignLanguage,
    val dark: Boolean,
    val key: Color,
    val gold: Color,
    val ink: Color,
    val surface: Color,
    val tertiary: Color
)

/** Tonal roles a subject asks for; each idiom decides what they look like. */
private enum class Tone { MAIN, DEEP, PALE, GOLD, RED, GLASS_A, GLASS_B }

/**
 * The square stage every subject is composed on, centred in the canvas.
 * Units run −0.5…0.5 across the stage; wide banners expose [halfSpan] > 0.5
 * of horizontal room for scenery that spreads to the edges.
 */
private class Stage(val size: Size, val px: Float) {
    val w = size.width
    val h = size.height
    val s = min(w, h) * 0.92f
    val cx = w / 2f
    val cy = h / 2f
    val halfSpan = (w / 2f) / s
    val diagonal = hypot(w, h)
    fun x(u: Float) = cx + u * s
    fun y(v: Float) = cy + v * s
    fun p(u: Float, v: Float) = Offset(x(u), y(v))
    fun d(units: Float) = units * s
}

private fun polar(c: Offset, r: Float, deg: Float): Offset {
    val a = deg * PI.toFloat() / 180f
    return Offset(c.x + r * cos(a), c.y + r * sin(a))
}

private fun circle(c: Offset, r: Float) = Path().apply { addOval(Rect(c, r)) }

private fun oval(c: Offset, rx: Float, ry: Float) =
    Path().apply { addOval(Rect(c.x - rx, c.y - ry, c.x + rx, c.y + ry)) }

private fun rectPath(l: Float, t: Float, r: Float, b: Float) =
    Path().apply { addRect(Rect(l, t, r, b)) }

private fun polygon(points: List<Offset>, close: Boolean = true) = Path().apply {
    points.forEachIndexed { i, o -> if (i == 0) moveTo(o.x, o.y) else lineTo(o.x, o.y) }
    if (close) close()
}

private fun combine(a: Path, b: Path, operation: PathOperation) = Path().apply { op(a, b, operation) }

/** Parallel lines at [angleDeg] covering [b], for clipping into a shape. */
private fun hatchLines(b: Rect, angleDeg: Float, spacing: Float): Path {
    val path = Path()
    val a = angleDeg * PI.toFloat() / 180f
    val dx = cos(a)
    val dy = sin(a)
    val c = b.center
    val reach = hypot(b.width, b.height) / 2f + spacing
    var o = -reach
    while (o <= reach) {
        val ox = c.x - dy * o
        val oy = c.y + dx * o
        path.moveTo(ox - dx * reach, oy - dy * reach)
        path.lineTo(ox + dx * reach, oy + dy * reach)
        o += spacing
    }
    return path
}

/**
 * A radially modulated closed curve: a Material "cookie" (low depth, many
 * lobes), a "flower" (deep lobes), or a "burst" when [sharp] < 1 sharpens
 * the lobes into points.
 */
private fun softShape(
    c: Offset,
    r: Float,
    lobes: Int,
    depth: Float,
    rotationDeg: Float = 0f,
    sharp: Float = 1f,
    steps: Int = 360
): Path {
    val path = Path()
    val rot = rotationDeg * PI.toFloat() / 180f
    for (i in 0..steps) {
        val a = i / steps.toFloat() * 2f * PI.toFloat()
        val k = cos(lobes * (a - rot))
        val m = if (sharp == 1f) k else sign(k) * abs(k).pow(sharp)
        val rr = r * (1f + depth * m)
        val x = c.x + rr * cos(a)
        val y = c.y + rr * sin(a)
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    return path
}

/** A rotated, scaled local frame for drawing objects at an angle. */
private class Frame(val o: Offset, angleDeg: Float, val k: Float) {
    private val ca = cos(angleDeg * PI.toFloat() / 180f)
    private val sa = sin(angleDeg * PI.toFloat() / 180f)
    fun at(x: Float, y: Float) = Offset(o.x + (x * ca - y * sa) * k, o.y + (x * sa + y * ca) * k)
}

/**
 * Latin cross outline in a [Frame] whose origin is the crossing. The upright
 * runs [above] up and [below] down; the arms reach [arm] each side; [t] is
 * half the beam thickness.
 */
private fun latinCross(f: Frame, above: Float, below: Float, arm: Float, t: Float) = polygon(
    listOf(
        f.at(-t, -above), f.at(t, -above), f.at(t, -t), f.at(arm, -t), f.at(arm, t), f.at(t, t),
        f.at(t, below), f.at(-t, below), f.at(-t, t), f.at(-arm, t), f.at(-arm, -t), f.at(-t, -t)
    )
)

// ================================================================= painters

/**
 * The vocabulary subjects are composed in. Every call records draw ops into
 * one of three layers: [back] and [front] are redrawn each frame (they
 * breathe); [mid] — the subject — is rasterised once.
 */
private abstract class Painter(val st: Stage, val k: ArtInks) {
    val back = ArrayList<Op>(16)
    val mid = ArrayList<Op>(128)
    val front = ArrayList<Op>(16)
    val px get() = st.px

    /** Ground behind everything; [focus] is where the light gathers. */
    abstract fun backdrop(focus: Offset)

    /** Radiance: rays from [c] between [inner] and [outer], over [sweep]°. */
    abstract fun glory(c: Offset, inner: Float, outer: Float, rays: Int, from: Float = 0f, sweep: Float = 360f)

    /** Soft light gathered around [c]. */
    abstract fun halo(c: Offset, r: Float)

    /** A solid object. */
    abstract fun form(path: Path, tone: Tone)

    /** Shadow laid over part of a form. */
    abstract fun shade(path: Path, strength: Float = 1f)

    /** Linework within a form (ribs, text lines, feathers). */
    abstract fun detail(path: Path, tone: Tone, width: Float)

    /** Separates a form from what is behind it (soft shadow, or glow). */
    open fun lift(path: Path, glow: Boolean = false) {}

    /** A sphere — rosary beads. */
    abstract fun bead(c: Offset, r: Float, tone: Tone)

    /** A candle flame standing on [base]. */
    abstract fun flame(base: Offset, w: Float, h: Float)

    /** The surface objects rest on. */
    abstract fun ground(c: Offset, rx: Float, ry: Float)

    protected fun flamePath(base: Offset, w: Float, h: Float): Path = Path().apply {
        moveTo(base.x, base.y)
        cubicTo(base.x - w * 0.62f, base.y - h * 0.08f, base.x - w * 0.42f, base.y - h * 0.62f, base.x, base.y - h)
        cubicTo(base.x + w * 0.42f, base.y - h * 0.62f, base.x + w * 0.62f, base.y - h * 0.08f, base.x, base.y)
        close()
    }
}

// ---------------------------------------------------------------- Cupertino

private class ApplePainter(st: Stage, k: ArtInks) : Painter(st, k) {
    private val dark = k.dark
    private val tint = k.key
    private val goldLight = Color(0xFFFFF4D6)
    private val goldMid = Color(0xFFF5C96A)
    private val goldDeep = Color(0xFFD08F2E)
    private val warm = Color(0xFFFFB45E)
    private val rim = Stroke(width = 0.9f * px)

    private fun stops(t: Tone): List<Color> = when (t) {
        Tone.MAIN -> if (dark) listOf(lerp(tint, Color.White, 0.40f), tint, lerp(tint, Color.Black, 0.25f))
        else listOf(lerp(tint, Color.White, 0.50f), lerp(tint, Color.White, 0.12f), tint)
        Tone.DEEP -> listOf(lerp(tint, Color.Black, 0.05f), lerp(tint, Color.Black, 0.45f))
        Tone.PALE -> if (dark) listOf(Color.White, lerp(Color.White, tint, 0.22f), lerp(Color.White, tint, 0.42f))
        else listOf(Color.White, lerp(Color.White, tint, 0.10f), lerp(Color.White, tint, 0.26f))
        Tone.GOLD -> listOf(goldLight, goldMid, goldDeep)
        Tone.RED -> listOf(Color(0xFFFF8A7A), Color(0xFFE5483A), Color(0xFFB82A22))
        Tone.GLASS_A -> listOf(lerp(tint, Color.White, 0.55f).copy(alpha = 0.80f), tint.copy(alpha = 0.55f))
        Tone.GLASS_B -> listOf(goldLight.copy(alpha = 0.85f), goldMid.copy(alpha = 0.60f))
    }

    override fun backdrop(focus: Offset) {
        val base = if (dark) {
            Brush.verticalGradient(
                listOf(lerp(Color(0xFF06070B), tint, 0.42f), lerp(Color(0xFF06070B), tint, 0.16f), Color(0xFF05060A)),
                0f, st.h
            )
        } else {
            Brush.verticalGradient(
                listOf(lerp(Color.White, tint, 0.26f), lerp(Color.White, tint, 0.12f), lerp(Color(0xFFFFF8EC), tint, 0.04f)),
                0f, st.h
            )
        }
        val blobA = Brush.radialGradient(
            listOf(tint.copy(alpha = if (dark) 0.45f else 0.30f), tint.copy(alpha = 0f)),
            Offset(st.w * 0.06f, st.h * 1.0f), st.s * 1.0f
        )
        val blobB = Brush.radialGradient(
            listOf(warm.copy(alpha = if (dark) 0.22f else 0.26f), warm.copy(alpha = 0f)),
            Offset(st.w * 0.94f, st.h * 0.02f), st.s * 0.9f
        )
        val core = if (dark) lerp(goldLight, tint, 0.25f) else Color(0xFFFFFBF0)
        val bloom = Brush.radialGradient(
            0f to core.copy(alpha = if (dark) 0.55f else 0.95f),
            0.45f to core.copy(alpha = if (dark) 0.18f else 0.45f),
            1f to core.copy(alpha = 0f),
            center = focus, radius = st.s * 0.95f
        )
        back += { t ->
            drawRect(base)
            drawRect(blobA)
            drawRect(blobB)
            drawRect(bloom, alpha = 0.82f + 0.18f * t)
        }
    }

    override fun glory(c: Offset, inner: Float, outer: Float, rays: Int, from: Float, sweep: Float) {
        val path = Path()
        val step = sweep / rays
        for (i in 0 until rays) {
            val a = from + step * (i + 0.5f)
            val long = i % 2 == 0
            val half = step * if (long) 0.20f else 0.12f
            val r1 = if (long) outer else outer * 0.62f
            val p0 = polar(c, inner, a)
            path.moveTo(p0.x, p0.y)
            val p1 = polar(c, r1, a - half)
            val p2 = polar(c, r1, a + half)
            path.lineTo(p1.x, p1.y)
            path.lineTo(p2.x, p2.y)
            path.close()
        }
        val color = if (dark) lerp(goldLight, tint, 0.2f) else Color.White
        val brush = Brush.radialGradient(
            0f to color.copy(alpha = if (dark) 0.34f else 0.85f),
            0.55f to color.copy(alpha = if (dark) 0.10f else 0.28f),
            1f to color.copy(alpha = 0f),
            center = c, radius = outer
        )
        back += { t -> rotate((t - 0.5f) * 3f, c) { drawPath(path, brush, alpha = 0.75f + 0.25f * t) } }
    }

    override fun halo(c: Offset, r: Float) {
        val core = if (dark) goldLight else Color.White
        val brush = Brush.radialGradient(
            0f to core.copy(alpha = if (dark) 0.70f else 1f),
            0.35f to lerp(core, goldMid, 0.4f).copy(alpha = if (dark) 0.30f else 0.55f),
            1f to goldMid.copy(alpha = 0f),
            center = c, radius = r
        )
        back += { t -> drawCircle(brush, r, c, alpha = 0.80f + 0.20f * t) }
    }

    override fun lift(path: Path, glow: Boolean) {
        val radius = st.d(if (glow) 0.05f else 0.03f)
        val color = when {
            glow -> (if (dark) goldMid else Color.White).copy(alpha = if (dark) 0.55f else 0.95f)
            dark -> Color.Black.copy(alpha = 0.55f)
            else -> lerp(tint, Color.Black, 0.55f).copy(alpha = 0.20f)
        }
        val dy = if (glow) 0f else st.d(0.018f)
        val shifted = Path().apply { addPath(path, Offset(0f, dy)) }
        val paint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color.toArgb()
            maskFilter = BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL)
        }
        val native = shifted.asAndroidPath()
        mid += { drawContext.canvas.nativeCanvas.drawPath(native, paint) }
    }

    override fun form(path: Path, tone: Tone) {
        val b = path.getBounds()
        val brush = Brush.verticalGradient(stops(tone), b.top, b.bottom)
        val rimColor = when (tone) {
            Tone.PALE, Tone.GOLD -> if (dark) Color.White.copy(alpha = 0.55f) else lerp(tint, Color.Black, 0.2f).copy(alpha = 0.16f)
            else -> Color.White.copy(alpha = if (dark) 0.25f else 0.45f)
        }
        mid += {
            drawPath(path, brush)
            drawPath(path, rimColor, style = rim)
        }
    }

    override fun shade(path: Path, strength: Float) {
        val b = path.getBounds()
        val deep = lerp(tint, Color.Black, 0.6f)
        val brush = Brush.verticalGradient(
            listOf(deep.copy(alpha = 0.10f * strength), deep.copy(alpha = 0.26f * strength)),
            b.top, b.bottom
        )
        mid += { drawPath(path, brush) }
    }

    override fun detail(path: Path, tone: Tone, width: Float) {
        val color = when (tone) {
            Tone.GOLD -> goldDeep
            Tone.RED -> Color(0xFFE5483A)
            Tone.PALE -> Color.White.copy(alpha = 0.8f)
            Tone.MAIN -> lerp(tint, Color.White, if (dark) 0.2f else 0f).copy(alpha = 0.55f)
            else -> lerp(tint, Color.Black, 0.35f).copy(alpha = 0.55f)
        }
        val stroke = Stroke(width = width, cap = StrokeCap.Round, join = StrokeJoin.Round)
        mid += { drawPath(path, color, style = stroke) }
    }

    override fun bead(c: Offset, r: Float, tone: Tone) {
        val s = stops(tone)
        val brush = Brush.radialGradient(
            0f to Color.White,
            0.25f to s.first(),
            0.7f to s[s.size / 2],
            1f to s.last(),
            center = Offset(c.x - r * 0.38f, c.y - r * 0.42f), radius = r * 1.55f
        )
        val shadowC = Offset(c.x, c.y + r * 0.35f)
        val shadow = Color.Black.copy(alpha = if (dark) 0.35f else 0.12f)
        mid += {
            drawCircle(shadow, r * 1.02f, shadowC)
            drawCircle(brush, r, c)
        }
    }

    override fun flame(base: Offset, w: Float, h: Float) {
        val outer = flamePath(base, w, h)
        val inner = flamePath(Offset(base.x, base.y - h * 0.04f), w * 0.48f, h * 0.55f)
        val brush = Brush.verticalGradient(
            listOf(Color(0xFFFFF6D8), Color(0xFFFFC659), Color(0xFFFF8A3C)),
            base.y - h, base.y
        )
        val glowC = Offset(base.x, base.y - h * 0.45f)
        val glow = Brush.radialGradient(
            0f to Color(0xFFFFE3A3).copy(alpha = if (dark) 0.55f else 0.70f),
            1f to Color(0xFFFFB45E).copy(alpha = 0f),
            center = glowC, radius = h * 1.5f
        )
        front += { t ->
            drawCircle(glow, h * 1.5f, glowC, alpha = 0.8f + 0.2f * t)
            scale(1f, 0.96f + 0.08f * t, base) {
                drawPath(outer, brush)
                drawPath(inner, Color.White.copy(alpha = 0.85f))
            }
        }
    }

    override fun ground(c: Offset, rx: Float, ry: Float) {
        val brush = Brush.radialGradient(
            listOf(Color.Black.copy(alpha = if (dark) 0.55f else 0.16f), Color.Black.copy(alpha = 0f)),
            c, rx
        )
        mid += { scale(1f, ry / rx, c) { drawCircle(brush, rx, c) } }
    }
}

// ---------------------------------------------------------------- Material 3

private class MaterialPainter(st: Stage, k: ArtInks) : Painter(st, k) {
    private val dark = k.dark
    private val prim = k.key
    private val tert = k.tertiary
    private val surf = k.surface
    private val bg = lerp(surf, prim, if (dark) 0.14f else 0.12f)
    private val container = lerp(surf, prim, if (dark) 0.30f else 0.26f)
    private val tertContainer = lerp(surf, tert, if (dark) 0.34f else 0.36f)
    private val ink = if (dark) lerp(prim, Color.Black, 0.55f) else lerp(prim, Color.Black, 0.45f)

    fun color(t: Tone): Color = when (t) {
        Tone.MAIN -> prim
        Tone.DEEP -> ink
        Tone.PALE -> if (dark) lerp(Color.White, prim, 0.10f) else lerp(Color.White, prim, 0.04f)
        Tone.GOLD -> tert
        Tone.RED -> lerp(tert, Color(0xFFD2452F), 0.62f)
        Tone.GLASS_A -> lerp(container, prim, 0.45f)
        Tone.GLASS_B -> lerp(tertContainer, tert, 0.35f)
    }

    override fun backdrop(focus: Offset) {
        val cookie = softShape(focus, st.d(0.43f), 9, 0.055f, rotationDeg = 10f)
        val half = st.halfSpan
        // Expressive accents spread toward the edges: a sunny burst, a pill, a
        // scatter of dots — positioned in stage units so wide banners fill.
        val sunC = st.p(-min(half - 0.10f, 0.62f), -0.30f)
        val sunny = softShape(sunC, st.d(0.11f), 8, 0.16f, sharp = 0.6f)
        val pillC = st.p(min(half - 0.12f, 0.66f), 0.28f)
        val pill = Path().apply {
            val r = st.d(0.055f)
            addRoundRect(
                androidx.compose.ui.geometry.RoundRect(
                    Rect(pillC.x - st.d(0.15f), pillC.y - r, pillC.x + st.d(0.15f), pillC.y + r),
                    androidx.compose.ui.geometry.CornerRadius(r, r)
                )
            )
        }
        val clover = softShape(st.p(min(half - 0.08f, 0.70f), -0.28f), st.d(0.06f), 4, 0.22f, rotationDeg = 45f)
        val dots = Path().apply {
            addOval(Rect(st.p(-min(half - 0.18f, 0.55f), 0.30f), st.d(0.028f)))
            addOval(Rect(st.p(-min(half - 0.30f, 0.46f), 0.40f), st.d(0.016f)))
        }
        val bgC = bg
        val c1 = container
        val c2 = tertContainer
        val c3 = lerp(container, prim, 0.25f)
        back += { t ->
            drawRect(bgC)
            drawPath(cookie, c1)
            rotate((t - 0.5f) * 20f, sunC) { drawPath(sunny, c2) }
            rotate(-24f, pillC) { drawPath(pill, c3) }
            drawPath(clover, c2)
            drawPath(dots, c3)
        }
    }

    override fun glory(c: Offset, inner: Float, outer: Float, rays: Int, from: Float, sweep: Float) {
        // M3 radiance is a burst shape, not literal rays.
        val r = min(outer, st.d(0.40f))
        val burst = softShape(c, r * 0.86f, max(10, rays / 2), 0.13f, sharp = 0.55f)
        val color = lerp(tertContainer, tert, 0.18f)
        back += { t -> rotate((t - 0.5f) * 8f, c) { drawPath(burst, color) } }
    }

    override fun halo(c: Offset, r: Float) {
        val color = lerp(tertContainer, Color.White, if (dark) 0.05f else 0.35f)
        val rr = r * 0.62f
        back += { t -> drawCircle(color, rr * (0.97f + 0.03f * t), c) }
    }

    override fun form(path: Path, tone: Tone) {
        val c = color(tone)
        mid += { drawPath(path, c) }
    }

    override fun shade(path: Path, strength: Float) {
        val c = Color.Black.copy(alpha = (if (dark) 0.20f else 0.11f) * strength)
        mid += { drawPath(path, c) }
    }

    override fun detail(path: Path, tone: Tone, width: Float) {
        val c = when (tone) {
            Tone.PALE -> color(Tone.PALE)
            Tone.MAIN -> lerp(prim, if (dark) Color.Black else Color.White, 0.25f).copy(alpha = 0.55f)
            else -> color(tone)
        }
        val stroke = Stroke(width = width * 1.5f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        mid += { drawPath(path, c, style = stroke) }
    }

    override fun bead(c: Offset, r: Float, tone: Tone) {
        val body = color(tone)
        val hi = lerp(body, Color.White, 0.45f)
        val hc = Offset(c.x - r * 0.32f, c.y - r * 0.32f)
        mid += {
            drawCircle(body, r, c)
            drawCircle(hi, r * 0.30f, hc)
        }
    }

    override fun flame(base: Offset, w: Float, h: Float) {
        val outer = flamePath(base, w * 1.15f, h)
        val inner = flamePath(Offset(base.x, base.y - h * 0.02f), w * 0.55f, h * 0.55f)
        val glowC = Offset(base.x, base.y - h * 0.5f)
        val glow = lerp(tertContainer, Color.White, if (dark) 0.0f else 0.4f).copy(alpha = 0.75f)
        val o = lerp(tert, Color(0xFFFF8A3C), 0.35f)
        val i = lerp(tert, Color.White, 0.6f)
        front += { t ->
            drawCircle(glow, h * (0.95f + 0.05f * t), glowC)
            scale(1f, 0.96f + 0.08f * t, base) {
                drawPath(outer, o)
                drawPath(inner, i)
            }
        }
    }

    override fun ground(c: Offset, rx: Float, ry: Float) {
        val color = lerp(container, prim, 0.22f)
        val r = Rect(c.x - rx, c.y - ry, c.x + rx, c.y + ry)
        mid += {
            drawRoundRect(
                color, r.topLeft, r.size,
                androidx.compose.ui.geometry.CornerRadius(ry, ry)
            )
        }
    }
}

// ------------------------------------------------------------------ Vatican

private class VaticanPainter(st: Stage, k: ArtInks) : Painter(st, k) {
    private val ink = k.ink
    private val gold = k.gold
    private val red = k.key
    private val dark = k.dark
    private val hair = Stroke(width = 0.55f * px)
    private val fine = Stroke(width = 0.75f * px, cap = StrokeCap.Round)
    private val outline = Stroke(width = 1.15f * px, join = StrokeJoin.Round)
    private val spacing = 2.3f * px
    private val goldLeaf = listOf(lerp(gold, Color.White, 0.35f), gold, lerp(gold, Color.Black, 0.25f))

    override fun backdrop(focus: Offset) {
        // Transparent: the parchment is the ground.
    }

    override fun glory(c: Offset, inner: Float, outer: Float, rays: Int, from: Float, sweep: Float) {
        // Baroque glory: straight gold rays alternating with flamboyant
        // (wavy) ink rays, as engraved round a monstrance or the Cathedra.
        val straight = Path()
        val wavy = Path()
        val reach = min(outer, st.diagonal)
        val step = sweep / rays
        for (i in 0 until rays) {
            val a = from + step * (i + 0.5f)
            if (i % 2 == 0) {
                val r1 = if (i % 4 == 0) reach else reach * 0.78f
                for (j in -1..1) {
                    val p0 = polar(c, inner, a + j * step * 0.10f)
                    val p1 = polar(c, r1, a + j * step * 0.07f)
                    straight.moveTo(p0.x, p0.y)
                    straight.lineTo(p1.x, p1.y)
                }
            } else {
                val r1 = reach * 0.5f
                val n = 28
                for (j in 0..n) {
                    val f = j / n.toFloat()
                    val r = inner + (r1 - inner) * f
                    val wob = sin(f * 6f * PI.toFloat()) * step * 0.22f * (1f - f * 0.5f)
                    val p = polar(c, r, a + wob)
                    if (j == 0) wavy.moveTo(p.x, p.y) else wavy.lineTo(p.x, p.y)
                }
            }
        }
        val fade = Brush.radialGradient(
            0f to gold, 0.6f to gold.copy(alpha = 0.7f), 1f to gold.copy(alpha = 0f),
            center = c, radius = reach
        )
        val inkFade = Brush.radialGradient(
            0f to ink.copy(alpha = 0.55f), 1f to ink.copy(alpha = 0f),
            center = c, radius = reach * 0.5f
        )
        back += { t ->
            drawPath(straight, fade, alpha = 0.75f + 0.25f * t, style = fine)
            drawPath(wavy, inkFade, style = hair)
        }
    }

    override fun halo(c: Offset, r: Float) {
        val r0 = r * 0.55f
        val wash = Brush.radialGradient(
            0f to gold.copy(alpha = if (dark) 0.30f else 0.26f), 1f to gold.copy(alpha = 0f),
            center = c, radius = r
        )
        back += { t ->
            drawCircle(wash, r, c, alpha = 0.7f + 0.3f * t)
            drawCircle(gold, r0, c, style = fine)
            drawCircle(gold, r0 * 1.035f, c, style = hair)
        }
    }

    private fun hatchOp(path: Path, angle: Float, gap: Float, color: Color, alpha: Float) {
        val lines = hatchLines(path.getBounds(), angle, gap)
        mid += { clipPath(path) { drawPath(lines, color, alpha = alpha, style = hair) } }
    }

    override fun form(path: Path, tone: Tone) {
        when (tone) {
            Tone.PALE -> {}
            Tone.MAIN -> hatchOp(path, 60f, spacing, ink, 0.70f)
            Tone.DEEP -> {
                hatchOp(path, 60f, spacing * 0.9f, ink, 0.75f)
                hatchOp(path, -30f, spacing * 1.1f, ink, 0.6f)
            }
            Tone.GOLD -> {
                val b = path.getBounds()
                val brush = Brush.linearGradient(goldLeaf, Offset(b.left, b.top), Offset(b.right, b.bottom))
                mid += { drawPath(path, brush) }
                hatchOp(path, 90f, spacing * 1.3f, lerp(gold, Color.Black, 0.4f), 0.35f)
            }
            Tone.RED -> {
                mid += { drawPath(path, red) }
                hatchOp(path, 60f, spacing * 1.2f, lerp(red, Color.Black, 0.5f), 0.4f)
            }
            Tone.GLASS_A -> hatchOp(path, 0f, spacing * 1.25f, ink, 0.55f)
            Tone.GLASS_B -> {
                mid += { drawPath(path, gold.copy(alpha = 0.55f)) }
            }
        }
        mid += { drawPath(path, ink, style = outline) }
    }

    override fun shade(path: Path, strength: Float) {
        hatchOp(path, 45f, spacing * 1.05f, ink, 0.55f * strength)
        if (strength > 0.6f) hatchOp(path, -45f, spacing * 1.25f, ink, 0.45f * strength)
    }

    override fun detail(path: Path, tone: Tone, width: Float) {
        val c = when (tone) {
            Tone.GOLD, Tone.GLASS_B -> gold
            Tone.RED -> red
            else -> ink
        }
        val stroke = Stroke(width = max(0.55f * px, width * 0.6f), cap = StrokeCap.Round, join = StrokeJoin.Round)
        mid += { drawPath(path, c, style = stroke) }
    }

    override fun bead(c: Offset, r: Float, tone: Tone) {
        // Engraved sphere: outline plus nested crescent strokes in the shadow.
        val crescents = Path()
        for (i in 1..3) {
            val rr = r * (1f - i * 0.2f)
            val cc = Offset(c.x + r * 0.2f * i * 0.5f, c.y + r * 0.2f * i * 0.5f)
            crescents.addArc(Rect(cc, rr), 10f, 110f)
        }
        val fill = tone == Tone.GOLD
        val brush = Brush.radialGradient(goldLeaf, Offset(c.x - r * 0.3f, c.y - r * 0.3f), r * 1.4f)
        mid += {
            if (fill) drawCircle(brush, r, c)
            drawPath(crescents, ink, style = hair)
            drawCircle(ink, r, c, style = fine)
        }
    }

    override fun flame(base: Offset, w: Float, h: Float) {
        val outer = flamePath(base, w, h)
        val inner = flamePath(Offset(base.x, base.y - h * 0.03f), w * 0.5f, h * 0.55f)
        val aureole = Path()
        val c = Offset(base.x, base.y - h * 0.5f)
        for (i in 0 until 16) {
            val a = -90f + (i - 7.5f) * 14f
            val p0 = polar(c, h * 0.78f, a)
            val p1 = polar(c, h * (if (i % 2 == 0) 1.35f else 1.1f), a)
            aureole.moveTo(p0.x, p0.y)
            aureole.lineTo(p1.x, p1.y)
        }
        val brush = Brush.verticalGradient(listOf(lerp(gold, Color.White, 0.4f), gold), base.y - h, base.y)
        front += { t ->
            drawPath(aureole, gold, alpha = 0.6f + 0.4f * t, style = hair)
            scale(1f, 0.97f + 0.06f * t, base) {
                drawPath(outer, brush)
                drawPath(inner, red)
                drawPath(outer, ink, style = fine)
            }
        }
    }

    override fun ground(c: Offset, rx: Float, ry: Float) {
        // Engraved ground: a few horizontal strokes, shortening as they fall.
        val lines = Path()
        for (i in 0 until 5) {
            val y = c.y + ry * (i * 0.5f)
            val half = rx * (1f - i * 0.17f)
            lines.moveTo(c.x - half, y)
            lines.lineTo(c.x + half, y)
        }
        mid += { drawPath(lines, ink, alpha = 0.75f, style = fine) }
    }
}

// ================================================================= subjects

/** Liturgical day — a Latin cross in glory before a rose window. */
private fun Painter.day() {
    val c = st.p(0f, -0.07f)
    backdrop(c)
    glory(c, st.d(0.33f), st.diagonal * 0.6f, 40)
    halo(c, st.d(0.42f))

    val r = st.d(0.30f)
    lift(circle(c, r))
    form(circle(c, r), Tone.PALE)
    // Lancet petals radiating from the oculus.
    for (i in 0 until 12) {
        val a = -90f + i * 30f
        val petal = Path().apply {
            val p0 = polar(c, r * 0.27f, a)
            moveTo(p0.x, p0.y)
            val c1 = polar(c, r * 0.52f, a - 13f)
            val p1 = polar(c, r * 0.74f, a - 9.5f)
            quadraticBezierTo(c1.x, c1.y, p1.x, p1.y)
            val c2 = polar(c, r * 0.90f, a)
            val p2 = polar(c, r * 0.74f, a + 9.5f)
            quadraticBezierTo(c2.x, c2.y, p2.x, p2.y)
            val c3 = polar(c, r * 0.52f, a + 13f)
            quadraticBezierTo(c3.x, c3.y, p0.x, p0.y)
            close()
        }
        form(petal, if (i % 2 == 0) Tone.GLASS_A else Tone.GLASS_B)
    }
    // Ring of quatrefoil-like roundels between petals and rim.
    for (i in 0 until 12) {
        val a = -75f + i * 30f
        form(circle(polar(c, r * 0.80f, a), r * 0.075f), Tone.GLASS_B)
    }
    val rimRing = combine(circle(c, r), circle(c, r * 0.90f), PathOperation.Difference)
    form(rimRing, Tone.MAIN)
    form(circle(c, r * 0.22f), Tone.GLASS_B)

    // Latin cross: the crossing a little above the window's centre, upright
    // roughly 1.5× the span, crossbar at about a quarter of the height.
    val f = Frame(Offset(c.x, c.y - st.d(0.02f)), 0f, st.s)
    val cross = latinCross(f, above = 0.17f, below = 0.45f, arm = 0.205f, t = 0.034f)
    lift(cross, glow = true)
    lift(cross)
    form(cross, Tone.GOLD)
    val bevel = latinCross(f, above = 0.17f - 0.018f, below = 0.45f - 0.018f, arm = 0.205f - 0.018f, t = 0.012f)
    detail(bevel, Tone.PALE, 1.0f * px)
    shade(combine(cross, rectPath(f.o.x, st.y(-0.6f), st.w, st.h), PathOperation.Intersect), 0.35f)
}

/** Scripture — the open book, with light falling across it. */
private fun Painter.scripture() {
    val focus = st.p(0f, -0.10f)
    backdrop(focus)
    glory(st.p(0f, 0.0f), st.d(0.20f), st.diagonal * 0.6f, 22, from = 196f, sweep = 148f)
    halo(st.p(0f, -0.04f), st.d(0.40f))

    fun top(sign: Float, t: Float) = Offset(
        st.x(sign * 0.43f * t),
        st.y(-0.05f - 0.085f * (1f - (1f - t).pow(3)))
    )
    fun bottom(sign: Float, t: Float) = Offset(
        st.x(sign * 0.43f * t),
        st.y(0.25f - 0.055f * (1f - (1f - t).pow(3)))
    )
    fun page(sign: Float, dy: Float = 0f, grow: Float = 0f): Path {
        val pts = ArrayList<Offset>(52)
        for (i in 0..24) {
            val o = top(sign, i / 24f)
            pts += Offset(o.x + sign * grow * (i / 24f), o.y + dy)
        }
        for (i in 24 downTo 0) {
            val o = bottom(sign, i / 24f)
            pts += Offset(o.x + sign * grow * (i / 24f), o.y + dy + grow * 0.6f)
        }
        return polygon(pts)
    }

    // Binding, then page block, then the leaves.
    val coverL = page(-1f, st.d(0.040f), st.d(0.022f))
    val coverR = page(1f, st.d(0.040f), st.d(0.022f))
    val cover = combine(coverL, coverR, PathOperation.Union)
    lift(cover)
    form(cover, Tone.RED)
    for (sign in floatArrayOf(-1f, 1f)) {
        val block = page(sign, st.d(0.020f))
        form(block, Tone.PALE)
        shade(block, 0.45f)
    }
    ground(st.p(0f, 0.33f), st.d(0.50f), st.d(0.035f))
    for (sign in floatArrayOf(-1f, 1f)) {
        val leaf = page(sign)
        form(leaf, Tone.PALE)
        // Page-edge strata on the block.
        val strata = Path()
        for (j in 1..2) {
            val pts = (0..24).map { i ->
                val o = bottom(sign, i / 24f)
                Offset(o.x, o.y + st.d(0.0065f * j))
            }
            strata.addPath(polygon(pts, close = false))
        }
        detail(strata, Tone.DEEP, 0.6f * px)
        // Gutter shadow.
        val gutter = Path().apply {
            val a = top(sign, 0f)
            val b = bottom(sign, 0f)
            val g1 = top(sign, 0.16f)
            val g2 = bottom(sign, 0.16f)
            moveTo(a.x, a.y)
            quadraticBezierTo(g1.x, (a.y + g1.y) / 2f, g1.x, g1.y)
            lineTo(g2.x, g2.y)
            quadraticBezierTo(g2.x, (b.y + g2.y) / 2f, b.x, b.y)
            close()
        }
        shade(gutter, 0.55f)
        // Text: two columns' worth of lines following the page's curve,
        // the first line rubricated as in a missal.
        val text = Path()
        val rubric = Path()
        val lines = 9
        for (j in 0 until lines) {
            val fy = 0.16f + j * (0.74f / (lines - 1))
            val start = if (j == 0 && sign < 0) 0.30f else 0.20f
            val end = if (j == lines - 1) 0.62f else 0.88f
            val pts = (0..16).map { i ->
                val tt = start + (end - start) * i / 16f
                lerpOffset(top(sign, tt), bottom(sign, tt), fy)
            }
            (if (j == 0) rubric else text).addPath(polygon(pts, close = false))
        }
        detail(text, Tone.MAIN, 1.6f * px)
        detail(rubric, Tone.RED, 1.6f * px)
        if (sign < 0) {
            // Initial: a small rubric square at the head of the left page.
            val p0 = lerpOffset(top(sign, 0.205f), bottom(sign, 0.205f), 0.16f)
            val sq = st.d(0.034f)
            form(rectPath(p0.x - sq * 1.1f, p0.y - sq * 0.35f, p0.x - sq * 0.1f, p0.y + sq * 0.65f), Tone.RED)
        }
    }
    // Ribbon marker falling from the spine.
    val ribbon = Path().apply {
        val x0 = st.x(0.012f)
        moveTo(x0, st.y(0.20f))
        cubicTo(st.x(0.03f), st.y(0.26f), st.x(0.0f), st.y(0.32f), st.x(0.035f), st.y(0.40f))
        lineTo(st.x(0.05f), st.y(0.385f))
        lineTo(st.x(0.062f), st.y(0.405f))
        cubicTo(st.x(0.03f), st.y(0.32f), st.x(0.058f), st.y(0.26f), x0 + st.d(0.028f), st.y(0.21f))
        close()
    }
    form(ribbon, Tone.RED)
}

private fun lerpOffset(a: Offset, b: Offset, f: Float) = Offset(a.x + (b.x - a.x) * f, a.y + (b.y - a.y) * f)

/** Rosary — five decades in a loose loop, the pendant flowing to the crucifix. */
private fun Painter.rosary() {
    val lc = st.p(-0.02f, -0.15f)
    backdrop(lc)
    halo(lc, st.d(0.46f))

    val rx = st.d(min(0.40f + (st.halfSpan - 0.5f) * 0.35f, 0.62f))
    val ry = st.d(0.20f)
    fun loop(theta: Float): Offset {
        // θ = 90° is the bottom, where the centrepiece joins the loop.
        val a = theta * PI.toFloat() / 180f
        val drop = max(0f, sin(a)).pow(10) * st.d(0.075f)
        val wob = sin(2f * a + 0.7f) * st.d(0.025f)
        return Offset(lc.x + rx * cos(a) + wob * 0.6f, lc.y + ry * sin(a) + drop + wob * 0.4f)
    }
    // Arc-length table so the beads sit evenly.
    val samples = 720
    val pts = Array(samples + 1) { loop(90f + it * 360f / samples) }
    val len = FloatArray(samples + 1)
    for (i in 1..samples) len[i] = len[i - 1] + (pts[i] - pts[i - 1]).getDistance()
    fun at(f: Float): Offset {
        val target = f * len[samples]
        var i = 1
        while (i < samples && len[i] < target) i++
        val seg = (target - len[i - 1]) / max(1e-3f, len[i] - len[i - 1])
        return lerpOffset(pts[i - 1], pts[i], seg)
    }
    val chain = polygon(pts.toList(), close = true)

    val centre = pts[0]
    // Pendant: centrepiece → Our Father, three Hail Marys, Our Father → crucifix.
    val pendEnd = st.p(0.10f, 0.30f)
    val pendCtl = Offset(centre.x - st.d(0.01f), centre.y + st.d(0.10f))
    fun pend(f: Float): Offset {
        val a = lerpOffset(centre, pendCtl, f)
        val b = lerpOffset(pendCtl, pendEnd, f)
        return lerpOffset(a, b, f)
    }
    val pendant = polygon((0..20).map { pend(it / 20f) }, close = false)

    val small = st.d(0.019f)
    val large = st.d(0.026f)
    ground(st.p(0.05f, 0.44f), st.d(0.25f), st.d(0.03f))
    detail(chain, Tone.DEEP, 0.8f * px)
    detail(pendant, Tone.DEEP, 0.8f * px)

    // 5 decades of 10, Our Fathers between: 54 beads on the loop, with the
    // centrepiece occupying the gap at the bottom.
    val slots = 54 + 3
    var slot = 2
    for (decade in 0 until 5) {
        for (b in 0 until 10) {
            bead(at(slot / slots.toFloat()), small, Tone.MAIN)
            slot++
        }
        if (decade < 4) {
            slot++
        }
    }
    slot = 2 + 10
    for (decade in 0 until 4) {
        bead(at(slot / slots.toFloat()), large, Tone.GOLD)
        slot += 11
    }
    bead(pend(0.22f), large, Tone.GOLD)
    for (i in 0 until 3) bead(pend(0.38f + i * 0.13f), small, Tone.MAIN)
    bead(pend(0.80f), large, Tone.GOLD)

    // Centrepiece medal.
    val medal = oval(centre, st.d(0.030f), st.d(0.038f))
    form(medal, Tone.GOLD)
    detail(oval(centre, st.d(0.018f), st.d(0.025f)), Tone.DEEP, 0.7f * px)

    // Crucifix, hanging along the pendant's direction.
    val dir = pendEnd - pend(0.95f)
    val angle = (kotlin.math.atan2(dir.y, dir.x) * 180f / PI.toFloat()) - 90f
    val top = Offset(pendEnd.x, pendEnd.y)
    val f = Frame(top, angle, st.s)
    // Local frame: y runs down the crucifix from its top.
    val cf = Frame(f.at(0f, 0.05f), angle, st.s)
    val cross = latinCross(cf, above = 0.05f, below = 0.135f, arm = 0.062f, t = 0.012f)
    lift(cross)
    form(cross, Tone.GOLD)
    val corpus = Path().apply {
        addPath(circle(cf.at(0f, -0.022f), st.d(0.0085f)))
        addPath(
            polygon(
                listOf(
                    cf.at(-0.056f, -0.006f), cf.at(-0.009f, -0.008f), cf.at(0.009f, -0.008f), cf.at(0.056f, -0.006f),
                    cf.at(0.056f, 0.0f), cf.at(0.010f, 0.004f), cf.at(0.007f, 0.05f), cf.at(0.004f, 0.105f),
                    cf.at(-0.004f, 0.105f), cf.at(-0.007f, 0.05f), cf.at(-0.010f, 0.004f), cf.at(-0.056f, 0.0f)
                )
            )
        )
    }
    form(corpus, Tone.PALE)
    // Little ring joining the crucifix to the chain.
    detail(circle(f.at(0f, -0.004f), st.d(0.008f)), Tone.DEEP, 1f * px)
}

/** Prayer — votive candles burning on a ledge. */
private fun Painter.prayer() {
    val focus = st.p(0f, -0.12f)
    backdrop(focus)

    data class Candle(val u: Float, val top: Float, val w: Float)
    val base = 0.34f
    val candles = ArrayList<Candle>()
    candles += Candle(-0.20f, -0.02f, 0.11f)
    candles += Candle(0f, -0.14f, 0.135f)
    candles += Candle(0.20f, 0.04f, 0.11f)
    val half = st.halfSpan
    if (half > 0.80f) {
        candles += Candle(-0.42f, 0.11f, 0.095f)
        candles += Candle(0.42f, 0.08f, 0.095f)
    }
    if (half > 1.05f) {
        candles += Candle(-0.64f, 0.18f, 0.085f)
        candles += Candle(0.64f, 0.20f, 0.085f)
    }
    for (c in candles) halo(st.p(c.u, c.top - 0.09f), st.d(0.26f + c.w))
    val ledgeHalf = min(half - 0.04f, 0.78f)
    ground(st.p(0f, base + 0.015f), st.d(ledgeHalf), st.d(0.03f))

    for (c in candles) {
        val l = st.x(c.u - c.w / 2f)
        val r = st.x(c.u + c.w / 2f)
        val top = st.y(c.top)
        val bot = st.y(base)
        val rimH = st.d(c.w * 0.16f)
        val body = Path().apply {
            moveTo(l, top)
            lineTo(l, bot - rimH * 0.5f)
            quadraticBezierTo((l + r) / 2f, bot + rimH * 0.6f, r, bot - rimH * 0.5f)
            lineTo(r, top)
            // Melted rim: a soft dip across the top.
            quadraticBezierTo((l + r) / 2f, top + rimH * 0.9f, l, top)
            close()
        }
        lift(body)
        form(body, Tone.PALE)
        val shadow = combine(body, rectPath(l + (r - l) * 0.62f, top - rimH, r + 1f, bot + rimH), PathOperation.Intersect)
        shade(shadow, 0.7f)
        val pool = oval(Offset((l + r) / 2f, top + rimH * 0.15f), (r - l) / 2f * 0.86f, rimH * 0.42f)
        form(pool, Tone.PALE)
        shade(pool, 0.25f)
        val wick = Path().apply {
            moveTo((l + r) / 2f, top + rimH * 0.15f)
            lineTo((l + r) / 2f + st.d(0.003f), top - st.d(0.022f))
        }
        detail(wick, Tone.DEEP, 1.6f * px)
        if (c.u == 0f) {
            // The centre candle is marked like the Paschal candle.
            val cx = (l + r) / 2f
            val cyy = top + (bot - top) * 0.40f
            val f = Frame(Offset(cx, cyy), 0f, st.s)
            form(latinCross(f, 0.030f, 0.060f, 0.030f, 0.0075f), Tone.RED)
        }
        flame(Offset((l + r) / 2f + st.d(0.003f), top - st.d(0.018f)), st.d(c.w * 0.40f), st.d(0.075f + c.w * 0.25f))
    }
}

/** Church — a domed basilica, its colonnade opening its arms on wide banners. */
private fun Painter.church() {
    val kk = 0.86f
    val dy = 0.07f
    fun X(u: Float) = st.x(u * kk)
    fun Y(v: Float) = st.y(v * kk + dy)
    fun R(l: Float, t: Float, r: Float, b: Float) = rectPath(X(l), Y(t), X(r), Y(b))
    val domeC = Offset(X(0f), Y(-0.24f))
    backdrop(domeC)
    glory(Offset(X(0f), Y(-0.22f)), st.d(0.26f), st.diagonal * 0.65f, 36, from = 180f, sweep = 180f)
    halo(domeC, st.d(0.44f))

    val ground = 0.42f
    // Colonnade arms: low, receding slightly as they reach outwards.
    val reach = st.halfSpan / kk + 0.05f
    if (reach > 0.55f) {
        for (sign in floatArrayOf(-1f, 1f)) {
            val u0 = 0.44f
            val u1 = reach
            fun sag(u: Float) = 0.035f * ((u - u0) / max(0.01f, u1 - u0))
            val band = polygon(
                listOf(
                    Offset(X(sign * u0), Y(0.18f)), Offset(X(sign * u1), Y(0.18f + sag(u1))),
                    Offset(X(sign * u1), Y(0.225f + sag(u1))), Offset(X(sign * u0), Y(0.225f))
                )
            )
            val wall = polygon(
                listOf(
                    Offset(X(sign * u0), Y(0.225f)), Offset(X(sign * u1), Y(0.225f + sag(u1))),
                    Offset(X(sign * u1), Y(ground)), Offset(X(sign * u0), Y(ground))
                )
            )
            form(wall, Tone.DEEP)
            var u = u0 + 0.03f
            while (u < u1) {
                val col = R(sign * u - 0.011f, 0.225f + sag(u), sign * u + 0.011f, ground)
                form(col, Tone.PALE)
                shade(R(sign * u + 0.002f, 0.225f + sag(u), sign * u + 0.011f, ground), 0.6f)
                u += 0.055f
            }
            form(band, Tone.PALE)
            // Statues on the balustrade.
            var su = u0 + 0.05f
            while (su < u1) {
                val sy = 0.18f + sag(su)
                form(oval(Offset(X(sign * su), Y(sy - 0.022f)), st.d(0.007f), st.d(0.020f)), Tone.PALE)
                su += 0.11f
            }
        }
    }

    // Side cupolas.
    for (sign in floatArrayOf(-1f, 1f)) {
        val u = sign * 0.29f
        form(R(u - 0.04f, -0.06f, u + 0.04f, 0.0f), Tone.PALE)
        val cup = Path().apply {
            moveTo(X(u - 0.045f), Y(-0.06f))
            cubicTo(X(u - 0.045f), Y(-0.12f), X(u - 0.012f), Y(-0.145f), X(u), Y(-0.15f))
            cubicTo(X(u + 0.012f), Y(-0.145f), X(u + 0.045f), Y(-0.12f), X(u + 0.045f), Y(-0.06f))
            close()
        }
        form(cup, Tone.MAIN)
        form(R(u - 0.008f, -0.175f, u + 0.008f, -0.148f), Tone.PALE)
    }

    // Drum and dome.
    val drum = R(-0.18f, -0.17f, 0.18f, -0.02f)
    form(drum, Tone.PALE)
    for (i in -3..3) {
        val u = i * 0.05f
        form(R(u - 0.004f, -0.165f, u + 0.004f, -0.03f), Tone.PALE)
        if (i < 3) {
            val wu = u + 0.025f
            val win = Path().apply {
                moveTo(X(wu - 0.011f), Y(-0.05f))
                lineTo(X(wu - 0.011f), Y(-0.12f))
                quadraticBezierTo(X(wu), Y(-0.14f), X(wu + 0.011f), Y(-0.12f))
                lineTo(X(wu + 0.011f), Y(-0.05f))
                close()
            }
            form(win, Tone.DEEP)
        }
    }
    shade(R(0.08f, -0.17f, 0.18f, -0.02f), 0.6f)
    form(R(-0.20f, -0.19f, 0.20f, -0.165f), Tone.PALE)

    val dome = Path().apply {
        moveTo(X(-0.185f), Y(-0.19f))
        cubicTo(X(-0.185f), Y(-0.33f), X(-0.085f), Y(-0.415f), X(0f), Y(-0.425f))
        cubicTo(X(0.085f), Y(-0.415f), X(0.185f), Y(-0.33f), X(0.185f), Y(-0.19f))
        close()
    }
    lift(dome)
    form(dome, Tone.MAIN)
    val ribs = Path()
    for (i in -2..2) {
        if (i == 0) {
            ribs.moveTo(X(0f), Y(-0.19f)); ribs.lineTo(X(0f), Y(-0.425f))
            continue
        }
        val bu = i * 0.075f
        ribs.moveTo(X(bu), Y(-0.19f))
        ribs.cubicTo(X(bu), Y(-0.30f), X(bu * 0.45f), Y(-0.40f), X(0f), Y(-0.425f))
    }
    detail(ribs, Tone.PALE, 1.4f * px)
    shade(combine(dome, R(0.075f, -0.5f, 0.3f, -0.18f), PathOperation.Intersect), 0.8f)
    // Lantern and cross.
    form(R(-0.03f, -0.475f, 0.03f, -0.418f), Tone.PALE)
    shade(R(0.01f, -0.475f, 0.03f, -0.418f), 0.6f)
    val lanternCap = Path().apply {
        moveTo(X(-0.034f), Y(-0.475f))
        quadraticBezierTo(X(0f), Y(-0.51f), X(0.034f), Y(-0.475f))
        close()
    }
    form(lanternCap, Tone.MAIN)
    val crossF = Frame(Offset(X(0f), Y(-0.548f)), 0f, st.s * kk)
    form(latinCross(crossF, 0.016f, 0.042f, 0.018f, 0.0055f), Tone.GOLD)

    // Façade.
    val facade = R(-0.40f, 0.0f, 0.40f, 0.34f)
    lift(facade)
    form(facade, Tone.PALE)
    // Bays between columns: windows and doors.
    val bays = Path()
    val bayU = floatArrayOf(-0.30f, -0.17f, 0.17f, 0.30f)
    for (u in bayU) {
        bays.addPath(R(u - 0.026f, 0.085f, u + 0.026f, 0.135f))
        bays.addPath(Path().apply {
            moveTo(X(u - 0.03f), Y(0.335f)); lineTo(X(u - 0.03f), Y(0.235f))
            quadraticBezierTo(X(u), Y(0.20f), X(u + 0.03f), Y(0.235f))
            lineTo(X(u + 0.03f), Y(0.335f)); close()
        })
    }
    // Loggia of the Blessing and the central door.
    bays.addPath(Path().apply {
        moveTo(X(-0.032f), Y(0.15f)); lineTo(X(-0.032f), Y(0.09f))
        quadraticBezierTo(X(0f), Y(0.055f), X(0.032f), Y(0.09f))
        lineTo(X(0.032f), Y(0.15f)); close()
    })
    bays.addPath(Path().apply {
        moveTo(X(-0.04f), Y(0.335f)); lineTo(X(-0.04f), Y(0.225f))
        quadraticBezierTo(X(0f), Y(0.18f), X(0.04f), Y(0.225f))
        lineTo(X(0.04f), Y(0.335f)); close()
    })
    form(bays, Tone.DEEP)
    // Giant order of columns.
    val colU = floatArrayOf(-0.375f, -0.235f, -0.105f, -0.06f, 0.06f, 0.105f, 0.235f, 0.375f)
    for (u in colU) {
        val col = R(u - 0.013f, 0.05f, u + 0.013f, 0.335f)
        form(col, Tone.PALE)
        shade(R(u + 0.003f, 0.05f, u + 0.013f, 0.335f), 0.5f)
        form(R(u - 0.019f, 0.040f, u + 0.019f, 0.055f), Tone.PALE)
    }
    // Entablature, attic, pediment.
    form(R(-0.42f, 0.0f, 0.42f, 0.045f), Tone.PALE)
    shade(R(-0.40f, 0.045f, 0.40f, 0.056f), 0.6f)
    form(R(-0.41f, -0.045f, 0.41f, 0.0f), Tone.PALE)
    val pediment = polygon(listOf(Offset(X(-0.15f), Y(0.0f)), Offset(X(0f), Y(-0.085f)), Offset(X(0.15f), Y(0.0f))))
    form(pediment, Tone.PALE)
    shade(polygon(listOf(Offset(X(-0.11f), Y(-0.012f)), Offset(X(0f), Y(-0.07f)), Offset(X(0.11f), Y(-0.012f)))), 0.35f)
    // Christ and the apostles along the attic.
    for (i in -6..6) {
        val u = i * 0.064f
        val baseV = if (abs(u) < 0.15f) -0.085f + abs(u) * 0.085f / 0.15f else -0.045f
        form(oval(Offset(X(u), Y(baseV - 0.022f)), st.d(0.0075f), st.d(0.021f)), Tone.PALE)
        form(circle(Offset(X(u), Y(baseV - 0.047f)), st.d(0.0062f)), Tone.PALE)
    }
    // Steps.
    form(R(-0.44f, 0.335f, 0.44f, 0.375f), Tone.PALE)
    form(R(-0.48f, 0.375f, 0.48f, ground), Tone.PALE)
    shade(R(-0.48f, 0.375f, 0.48f, 0.385f), 0.5f)
    ground(Offset(X(0f), Y(ground + 0.01f)), st.d(min(st.halfSpan, 0.75f)), st.d(0.03f))
}

/** Dove — the Holy Spirit descending, head down, wings spread, in glory. */
private fun Painter.dove() {
    val c = st.p(0f, -0.02f)
    backdrop(c)
    glory(c, st.d(0.24f), st.diagonal * 0.6f, 44)
    halo(c, st.d(0.48f))

    fun P(u: Float, v: Float) = Offset(c.x + st.d(u), c.y + st.d(v))
    fun qb(a: Offset, ctl: Offset, b: Offset, t: Float) =
        lerpOffset(lerpOffset(a, ctl, t), lerpOffset(ctl, b, t), t)

    // Tail fan, above the body (the dove descends head first).
    val tailBase = P(0f, -0.085f)
    val tail = Path().apply {
        val l = P(-0.03f, -0.09f)
        moveTo(l.x, l.y)
        val tl = P(-0.085f, -0.285f)
        lineTo(tl.x, tl.y)
        val n = 5
        for (i in 0 until n) {
            val a = P(-0.085f + 0.17f * i / n, -0.285f - 0.012f * sin(PI.toFloat() * i / n))
            val b = P(-0.085f + 0.17f * (i + 1) / n, -0.285f - 0.012f * sin(PI.toFloat() * (i + 1) / n))
            val m = lerpOffset(a, b, 0.5f)
            quadraticBezierTo(m.x, m.y - st.d(0.03f), b.x, b.y)
        }
        val r = P(0.03f, -0.09f)
        lineTo(r.x, r.y)
        close()
    }
    val tailLines = Path()
    for (i in 1 until 5) {
        val e = P(-0.085f + 0.17f * i / 5f, -0.29f)
        tailLines.moveTo(tailBase.x, tailBase.y)
        tailLines.lineTo(e.x, e.y)
    }

    val wings = ArrayList<Path>()
    val wingDetails = Path()
    val wingShades = ArrayList<Path>()
    for (sign in floatArrayOf(-1f, 1f)) {
        fun W(u: Float, v: Float) = P(sign * u, v)
        val shoulder = W(0.035f, -0.055f)
        val wrist = W(0.22f, -0.20f)
        val tip = W(0.49f, -0.245f)
        val bodyJoin = W(0.045f, 0.035f)
        val trailCtl = W(0.33f, 0.02f)
        val n = 8
        val tips = (0..n).map { qb(tip, trailCtl, bodyJoin, it / n.toFloat()) }
        val wing = Path().apply {
            moveTo(shoulder.x, shoulder.y)
            val c1 = W(0.10f, -0.15f)
            quadraticBezierTo(c1.x, c1.y, wrist.x, wrist.y)
            val c2 = W(0.36f, -0.255f)
            quadraticBezierTo(c2.x, c2.y, tip.x, tip.y)
            for (i in 0 until n) {
                val a = tips[i]
                val b = tips[i + 1]
                val m = lerpOffset(a, b, 0.5f)
                // Each feather's tip bulges outward-and-down.
                val nx = (b.y - a.y)
                val ny = -(b.x - a.x)
                val nl = sqrt(nx * nx + ny * ny).coerceAtLeast(1e-3f)
                val bulge = st.d(0.032f) * (1f - i / (n * 1.6f))
                val outX = nx / nl * bulge * -sign
                val outY = ny / nl * bulge * -sign
                quadraticBezierTo(m.x + outX, m.y + abs(outY), b.x, b.y)
            }
            close()
        }
        wings += wing
        // Coverts line and feather shafts.
        val covA = W(0.06f, -0.03f)
        val covCtl = W(0.20f, -0.10f)
        val covB = W(0.34f, -0.205f)
        val cov = (0..n).map { qb(covB, covCtl, covA, it / n.toFloat()) }
        for (i in 1 until n) {
            wingDetails.moveTo(tips[i].x, tips[i].y)
            wingDetails.lineTo(cov[i].x, cov[i].y)
        }
        // Scalloped coverts edge.
        wingDetails.moveTo(cov[0].x, cov[0].y)
        for (i in 0 until n) {
            val a = cov[i]
            val b = cov[i + 1]
            val m = lerpOffset(a, b, 0.5f)
            wingDetails.quadraticBezierTo(m.x, m.y + st.d(0.02f), b.x, b.y)
        }
        // Shadow over the flight feathers.
        val shadePts = ArrayList<Offset>()
        shadePts += tips
        shadePts += cov.reversed().let { list -> list.subList(0, list.size) }
        wingShades += combine(polygon(shadePts), wing, PathOperation.Intersect)
    }

    val body = Path().apply {
        val top = P(0f, -0.12f)
        moveTo(top.x, top.y)
        val a1 = P(0.075f, -0.10f); val a2 = P(0.07f, 0.06f); val a3 = P(0.032f, 0.105f)
        cubicTo(a1.x, a1.y, a2.x, a2.y, a3.x, a3.y)
        val b1 = P(-0.032f, 0.105f)
        lineTo(b1.x, b1.y)
        val b2 = P(-0.07f, 0.06f); val b3 = P(-0.075f, -0.10f)
        cubicTo(b2.x, b2.y, b3.x, b3.y, top.x, top.y)
        close()
    }
    val headC = P(0f, 0.125f)
    val head = oval(headC, st.d(0.045f), st.d(0.05f))
    val beak = polygon(listOf(P(-0.012f, 0.168f), P(0.012f, 0.168f), P(0f, 0.198f)))

    val whole = Path().apply {
        addPath(tail); wings.forEach { addPath(it) }; addPath(body); addPath(head)
    }
    lift(whole, glow = true)
    lift(whole)
    form(tail, Tone.PALE)
    detail(tailLines, Tone.DEEP, 0.7f * px)
    for (i in wings.indices) {
        form(wings[i], Tone.PALE)
        shade(wingShades[i], 0.35f)
    }
    detail(wingDetails, Tone.DEEP, 0.7f * px)
    form(body, Tone.PALE)
    shade(combine(body, rectPath(c.x + st.d(0.02f), 0f, st.w, st.h), PathOperation.Intersect), 0.3f)
    form(head, Tone.PALE)
    form(beak, Tone.GOLD)
    val eyes = Path().apply {
        addOval(Rect(P(-0.022f, 0.118f), st.d(0.0065f)))
        addOval(Rect(P(0.022f, 0.118f), st.d(0.0065f)))
    }
    form(eyes, Tone.DEEP)
}

/**
 * A screen's one moment of imagery: the illustration as a hero under its
 * title, inset to the gutter and kept short so the content still starts above
 * the fold.
 */
@Composable
fun SacredHero(subject: SacredSubject, modifier: Modifier = Modifier) {
    val design = com.example.ui.theme.LocalAppDesign.current
    SacredArt(
        subject = subject,
        modifier = modifier
            .padding(horizontal = design.spacing.gutter)
            .padding(bottom = design.spacing.cardGap)
            .fillMaxWidth()
            .height(136.dp)
    )
}
