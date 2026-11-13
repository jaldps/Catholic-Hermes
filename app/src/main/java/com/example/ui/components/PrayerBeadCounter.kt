package com.example.ui.components

import android.view.HapticFeedbackConstants
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalAppDesign
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin

// -----------------------------------------------------------------------------
// The rosary drawn as the thing itself, for the Rosary and the Divine Mercy
// chaplet: the bead reel on the practice screen and the whole-rosary map in
// the parts sheet. Pearls are drawn, not iconised, in Lumen tokens.
// -----------------------------------------------------------------------------

/** One physical bead on a rosary. */
enum class BeadKind { CRUCIFIX, LARGE, SMALL, MEDAL }

/** The colours of a strand, from the Lumen palette. */
private class PearlInks(
    val todoFill: Color,
    val todoHighlight: Color,
    val todoEdge: Color,
    val toldFill: Color,
    val currentFill: Color,
    val ring: Color,
    val shadow: Color,
    val thread: Color,
    val toldThread: Color,
    val corpus: Color,
    val pip: Color,
    val pipOn: Color
)

@Composable
private fun pearlInks(): PearlInks {
    val design = LocalAppDesign.current
    val p = design.palette
    return if (design.isDark) {
        PearlInks(
            todoFill = p.textPrimary.copy(alpha = 0.10f),
            todoHighlight = p.textPrimary.copy(alpha = 0.22f),
            todoEdge = p.textPrimary.copy(alpha = 0.22f),
            toldFill = p.textPrimary.copy(alpha = 0.58f),
            currentFill = p.textPrimary,
            ring = p.accent,
            shadow = Color.Transparent,
            thread = p.textPrimary.copy(alpha = 0.16f),
            toldThread = p.textPrimary.copy(alpha = 0.42f),
            corpus = p.canvasTop,
            pip = p.textPrimary.copy(alpha = 0.22f),
            pipOn = p.textPrimary
        )
    } else {
        PearlInks(
            todoFill = lerp(p.glassStrong.copy(alpha = 1f), p.canvasBottom, 0.35f),
            todoHighlight = p.glassStrong.copy(alpha = 1f),
            todoEdge = p.textPrimary.copy(alpha = 0.18f),
            toldFill = p.textSecondary,
            currentFill = p.action,
            ring = p.accent,
            shadow = p.shadow.copy(alpha = (p.shadow.alpha * 2.2f).coerceAtMost(0.3f)),
            thread = p.textPrimary.copy(alpha = 0.16f),
            toldThread = p.textSecondary.copy(alpha = 0.7f),
            corpus = p.canvasTop,
            pip = p.textPrimary.copy(alpha = 0.16f),
            pipOn = p.textPrimary
        )
    }
}

/**
 * A pearl: soft contact shadow, body, lit edge (to-come beads) and a
 * highlight up and to the left, so it reads as a bead, not a dot.
 */
private fun DrawScope.drawPearl(centre: Offset, r: Float, fill: Color, inks: PearlInks, todo: Boolean, a: Float = 1f) {
    if (inks.shadow.alpha > 0f) {
        drawCircle(inks.shadow, radius = r * 1.02f, center = centre + Offset(0f, r * 0.22f), alpha = a)
    }
    drawCircle(fill, radius = r, center = centre, alpha = a)
    if (todo) {
        drawCircle(
            Brush.radialGradient(
                0f to inks.todoHighlight,
                1f to inks.todoHighlight.copy(alpha = 0f),
                center = centre + Offset(-r * 0.32f, -r * 0.38f),
                radius = r * 0.95f
            ),
            radius = r, center = centre, alpha = a
        )
        drawCircle(inks.todoEdge, radius = r - 0.5f, center = centre, style = Stroke(1f.coerceAtLeast(r * 0.07f)), alpha = a)
    } else {
        drawCircle(
            Brush.radialGradient(
                0f to Color.White.copy(alpha = 0.42f),
                1f to Color.White.copy(alpha = 0f),
                center = centre + Offset(-r * 0.32f, -r * 0.38f),
                radius = r * 0.7f
            ),
            radius = r, center = centre, alpha = a
        )
    }
}

/** The centre medal: a rounded lozenge with a small cross struck on it. */
private fun DrawScope.drawMedal(centre: Offset, r: Float, fill: Color, inks: PearlInks, todo: Boolean, a: Float = 1f) {
    val lozenge = Path().apply {
        moveTo(centre.x, centre.y - r)
        quadraticBezierTo(centre.x + r * 0.15f, centre.y - r * 0.15f, centre.x + r * 0.78f, centre.y)
        quadraticBezierTo(centre.x + r * 0.15f, centre.y + r * 0.15f, centre.x, centre.y + r)
        quadraticBezierTo(centre.x - r * 0.15f, centre.y + r * 0.15f, centre.x - r * 0.78f, centre.y)
        quadraticBezierTo(centre.x - r * 0.15f, centre.y - r * 0.15f, centre.x, centre.y - r)
        close()
    }
    if (inks.shadow.alpha > 0f) {
        drawPath(lozenge, inks.shadow, alpha = a)
    }
    drawPath(lozenge, fill, alpha = a)
    if (todo) drawPath(lozenge, inks.todoEdge, style = Stroke(r * 0.08f, join = StrokeJoin.Round), alpha = a)
    val c = r * 0.36f
    val ink = if (todo) inks.toldFill else inks.corpus
    drawLine(ink, Offset(centre.x, centre.y - c), Offset(centre.x, centre.y + c), r * 0.11f, StrokeCap.Round, alpha = a)
    drawLine(ink, Offset(centre.x - c * 0.62f, centre.y - c * 0.22f), Offset(centre.x + c * 0.62f, centre.y - c * 0.22f), r * 0.11f, StrokeCap.Round, alpha = a)
}

/**
 * The crucifix on the strand: a 1.5dp-class outline Latin cross in ink, drawn
 * like the Rosary tab's glyph (same proportions, round caps), not a budded
 * filled cross.
 */
private fun DrawScope.drawCrucifix(centre: Offset, height: Float, inkIn: Color, a: Float = 1f) {
    val ink = inkIn.copy(alpha = inkIn.alpha * a)
    val top = centre.y - height / 2
    val bottom = centre.y + height / 2
    val barY = top + height * 0.30f
    val half = height * 0.30f
    val stroke = (height * 0.075f).coerceAtLeast(1.5f * density)
    drawLine(ink, Offset(centre.x, top), Offset(centre.x, bottom), stroke, StrokeCap.Round)
    drawLine(ink, Offset(centre.x - half, barY), Offset(centre.x + half, barY), stroke, StrokeCap.Round)
}

/** Half the drawn extent of a bead along the strand, in dp. */
private fun halfExtent(kind: BeadKind): Float = when (kind) {
    BeadKind.CRUCIFIX -> 13f
    BeadKind.LARGE -> 10.5f
    BeadKind.SMALL -> 7f
    BeadKind.MEDAL -> 12f
}

/** Radius (or half height) of a bead at rest, in dp. */
private fun restRadius(kind: BeadKind): Float = when (kind) {
    BeadKind.CRUCIFIX -> 12f
    BeadKind.LARGE -> 10f
    BeadKind.SMALL -> 7f
    BeadKind.MEDAL -> 11f
}

/**
 * The reel's gesture decisions as pure functions (no Compose, no Android),
 * so what a tap or the end of a drag means can be tested on the JVM.
 *
 * The reel is always in one of three phases: resting on its bead, being
 * dragged (the strand is ahead of the bead in hand), or settling (animating
 * to it). A drag always ends in exactly one of two ways: the strand lands on
 * another bead and the parent is asked to go there ([DragEnd.Select]), or it
 * is pulled back to the bead in hand ([DragEnd.SnapBack]).
 */
internal object ReelGesture {
    sealed interface DragEnd {
        data class Select(val bead: Int) : DragEnd
        object SnapBack : DragEnd
    }

    /** The bead the strand is nearest to at [position]. */
    fun landed(position: Float, lastIndex: Int): Int = position.roundToInt().coerceIn(0, lastIndex)

    fun onDragEnd(position: Float, target: Int, lastIndex: Int): DragEnd {
        val landed = landed(position, lastIndex)
        return if (landed != target) DragEnd.Select(landed) else DragEnd.SnapBack
    }

    /**
     * The bead under a tap at [tapX] (px), or null when none is within
     * [slopDp]. [along] are bead offsets in dp along the strand; the strand
     * is centred on [centreAlong] at the middle of [widthPx].
     */
    fun hit(along: FloatArray, centreAlong: Float, density: Float, widthPx: Float, tapX: Float, slopDp: Float = 22f): Int? {
        if (along.isEmpty()) return null
        fun dx(i: Int) = abs((along[i] - centreAlong) * density + widthPx / 2f - tapX)
        val hit = along.indices.minByOrNull(::dx) ?: return null
        return hit.takeIf { dx(it) <= slopDp * density }
    }
}

/**
 * The bead reel: the whole rosary strung on one gently sagging thread, with
 * the bead in hand always at the centre, larger, and ringed in the liturgical
 * accent. Prayed beads (left) are "told" — filled; beads to come (right) are
 * pale pearls. It slides one bead per step, like beads passing through the
 * fingers; tap a bead to go to it, or drag the strand to scrub bead by bead.
 *
 * [subStepCount]/[subStepIndex] draw small pips under the current bead when
 * several prayers are said on it (the medal carries eight).
 */
@Composable
fun BeadReel(
    beads: List<BeadKind>,
    activeBead: Int,
    onSelectBead: (Int) -> Unit,
    modifier: Modifier = Modifier,
    subStepCount: Int = 1,
    subStepIndex: Int = 0,
    height: Dp = 70.dp
) {
    if (beads.isEmpty()) return
    val inks = pearlInks()
    val density = LocalDensity.current
    val view = LocalView.current
    val still = LocalInspectionMode.current || LocalAppDesign.current.reduceMotion
    val scope = rememberCoroutineScope()
    val select by rememberUpdatedState(onSelectBead)
    val target = activeBead.coerceIn(0, beads.lastIndex)

    // Bead positions along the strand (dp from the first bead's centre).
    val along = remember(beads) {
        val xs = FloatArray(beads.size)
        for (i in 1 until beads.size) {
            val a = beads[i - 1]
            val b = beads[i]
            var gap = 15f
            // The Our Father bead hangs apart on its own length of chain.
            if (a == BeadKind.LARGE || b == BeadKind.LARGE) gap += 7f
            if (a == BeadKind.CRUCIFIX || b == BeadKind.CRUCIFIX || a == BeadKind.MEDAL || b == BeadKind.MEDAL) gap += 8f
            xs[i] = xs[i - 1] + halfExtent(a) + gap + halfExtent(b)
        }
        xs
    }
    fun alongAt(pos: Float): Float {
        val lo = pos.toInt().coerceIn(0, beads.lastIndex)
        val hi = (lo + 1).coerceAtMost(beads.lastIndex)
        val t = (pos - lo).coerceIn(0f, 1f)
        return along[lo] + (along[hi] - along[lo]) * t
    }

    val position = remember { Animatable(target.toFloat()) }
    LaunchedEffect(target) {
        if (still || abs(position.value - target) > 14f) {
            position.snapTo(target.toFloat())
        } else {
            position.animateTo(target.toFloat(), spring(dampingRatio = 0.82f, stiffness = 260f))
        }
    }

    BoxWithConstraints(modifier.fillMaxWidth().height(height)) {
        val widthPx = with(density) { maxWidth.toPx() }
        Canvas(
            Modifier
                .fillMaxWidth()
                .height(height)
                .pointerInput(beads, widthPx) {
                    detectTapGestures { tap ->
                        val hit = ReelGesture.hit(along, alongAt(position.value), density.density, widthPx, tap.x)
                        if (hit != null) {
                            view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                            select(hit)
                        }
                    }
                }
                .pointerInput(beads, widthPx) {
                    var lastTick = target
                    detectHorizontalDragGestures(
                        onDragStart = { lastTick = position.value.roundToInt() },
                        onDragEnd = {
                            when (val end = ReelGesture.onDragEnd(position.value, target, beads.lastIndex)) {
                                is ReelGesture.DragEnd.Select -> select(end.bead)
                                ReelGesture.DragEnd.SnapBack ->
                                    scope.launch { position.animateTo(target.toFloat(), spring(0.82f, 300f)) }
                            }
                        },
                        onDragCancel = {
                            scope.launch { position.animateTo(target.toFloat(), spring(0.82f, 300f)) }
                        }
                    ) { change, dragAmount ->
                        change.consume()
                        val pitchPx = 30f * density.density
                        val next = (position.value - dragAmount / pitchPx).coerceIn(0f, beads.lastIndex.toFloat())
                        scope.launch { position.snapTo(next) }
                        val r = next.roundToInt()
                        if (r != lastTick) {
                            lastTick = r
                            view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                        }
                    }
                }
        ) {
            val dpx = density.density
            val w = size.width
            val half = w / 2f
            val pos = position.value
            val centreAlong = alongAt(pos)
            val baseY = size.height * 0.37f
            val sag = 9f * dpx

            fun xOf(i: Int) = (along[i] - centreAlong) * dpx + half
            fun yOf(x: Float): Float {
                val d = ((x - half) / half).coerceIn(-1.4f, 1.4f)
                return baseY + sag * (1f - d * d)
            }
            // The strand dissolves into the canvas at both gutters: each bead and
            // each run of thread fades by its distance from the edge, so no
            // offscreen mask is needed.
            val fadeW = 52f * dpx
            fun edge(x: Float): Float {
                val t = (minOf(x, w - x) / fadeW).coerceIn(0f, 1f)
                return t * t * (3f - 2f * t)
            }
            fun falloff(x: Float): Float {
                val d = (abs(x - half) / half).coerceIn(0f, 1.3f)
                return 1f - 0.26f * d.toDouble().pow(1.6).toFloat()
            }

            val visible = beads.indices.filter { val x = xOf(it); x > -40f * dpx && x < w + 40f * dpx }

            // The thread, as short runs between visible neighbours.
            for (k in 0 until visible.size - 1) {
                val i = visible[k]
                val j = visible[k + 1]
                if (j != i + 1) continue
                val x1 = xOf(i); val x2 = xOf(j)
                val r1 = restRadius(beads[i]) * dpx * falloff(x1)
                val r2 = restRadius(beads[j]) * dpx * falloff(x2)
                if (x2 - x1 <= r1 + r2) continue
                val prayed = j <= pos + 0.001f
                val steps = 6
                var prev = Offset(x1 + r1, yOf(x1 + r1))
                for (s in 1..steps) {
                    val x = x1 + r1 + (x2 - r2 - (x1 + r1)) * s / steps
                    val pt = Offset(x, yOf(x))
                    drawLine(
                        if (prayed) inks.toldThread else inks.thread, prev, pt,
                        strokeWidth = (if (prayed) 1.6f else 1.2f) * dpx, cap = StrokeCap.Round,
                        alpha = edge((prev.x + pt.x) / 2f)
                    )
                    prev = pt
                }
            }

            // The beads.
            visible.forEach { i ->
                val x = xOf(i)
                val emphasis = (1f - abs(i - pos)).coerceIn(0f, 1f)
                val told = (pos - i).coerceIn(0f, 1f)
                val scale = falloff(x) * (1f + 0.42f * emphasis)
                val r = restRadius(beads[i]) * dpx * scale
                val c = Offset(x, yOf(x))
                val a = edge(x)
                val todo = told < 0.5f && emphasis < 0.5f
                val fill = lerp(lerp(inks.todoFill, inks.toldFill, told), inks.currentFill, emphasis)
                when (beads[i]) {
                    BeadKind.CRUCIFIX -> drawCrucifix(
                        c, r * 2f,
                        lerp(inks.toldFill, inks.currentFill, emphasis),
                        a
                    )
                    BeadKind.MEDAL -> drawMedal(c, r, fill, inks, todo, a)
                    else -> drawPearl(c, r, fill, inks, todo, a)
                }
                if (emphasis > 0.02f) {
                    val ringR = r + (if (beads[i] == BeadKind.CRUCIFIX) 5f else 4.5f) * dpx
                    drawCircle(
                        inks.ring.copy(alpha = inks.ring.alpha * emphasis * a),
                        radius = ringR, center = c,
                        style = Stroke(2f * dpx)
                    )
                }
            }

            // Pips: how many prayers are said on the bead in hand.
            if (subStepCount > 1) {
                val pitch = 8f * dpx
                val total = pitch * (subStepCount - 1)
                val y = size.height - 4.5f * dpx
                for (k in 0 until subStepCount) {
                    val cx = half - total / 2f + k * pitch
                    drawCircle(
                        if (k == subStepIndex) inks.pipOn else inks.pip,
                        radius = (if (k == subStepIndex) 2.4f else 2f) * dpx,
                        center = Offset(cx, y)
                    )
                }
            }
        }
    }
}

// --------------------------------------------------------- whole-rosary map

/**
 * Where every bead of the whole rosary sits: the loop carries the decades
 * clockwise from beside the medal; the pendant hangs from the medal to the
 * crucifix.
 */
private class LoopGeometry(
    val centres: List<Offset>,
    val angles: Map<Int, Float>,
    val loopRuns: List<Pair<Int, Int>>,
    val pendantRuns: List<Pair<Int, Int>>,
    val kinds: List<BeadKind>,
    val centre: Offset,
    val radius: Float,
    val small: Float,
    val large: Float,
    val medal: Float,
    val crucifixHeight: Float,
    val height: Float
) {
    fun reach(index: Int): Float = when (kinds[index]) {
        BeadKind.CRUCIFIX -> crucifixHeight / 2
        BeadKind.LARGE -> large
        BeadKind.SMALL -> small
        BeadKind.MEDAL -> medal
    } + small * 0.4f

    companion object {
        fun layout(beads: List<BeadKind>, width: Float, density: Density): LoopGeometry = with(density) {
            val top = 10.dp.toPx()
            val radius = minOf(width / 2 - 14.dp.toPx(), 104.dp.toPx())
            val small = 4.4.dp.toPx()
            val large = 6.4.dp.toPx()
            val medal = 8.5.dp.toPx()
            val crucifixHeight = 30.dp.toPx()
            val centre = Offset(width / 2, top + radius)
            val medalIndex = beads.indexOfLast { it == BeadKind.MEDAL }
            // The pendant is everything before the first decade's large bead
            // after the opening (crucifix, its large bead, its small beads).
            val firstLoop = beads.indices.drop(2).firstOrNull { beads[it] == BeadKind.LARGE } ?: beads.size
            val pendant = (0 until firstLoop).toList()
            val loop = (firstLoop until beads.size).filter { it != medalIndex }

            fun r(i: Int) = when (beads[i]) {
                BeadKind.LARGE -> large
                BeadKind.MEDAL -> medal
                BeadKind.CRUCIFIX -> crucifixHeight / 2
                BeadKind.SMALL -> small
            }

            val centres = MutableList(beads.size) { Offset.Zero }
            val angles = mutableMapOf<Int, Float>()
            val bottom = (PI / 2).toFloat()
            val gap = (medal + small * 3.2f) / radius
            val start = bottom + gap
            val end = bottom + (2 * PI).toFloat() - gap
            val arc = (end - start) * radius
            val diameters = loop.sumOf { 2.0 * r(it) }.toFloat()
            val spacing = if (loop.size > 1) (arc - diameters) / (loop.size - 1) else 0f
            var run = 0f
            loop.forEach { i ->
                val angle = start + (run + r(i)) / radius
                angles[i] = angle
                centres[i] = Offset(centre.x + radius * cos(angle), centre.y + radius * sin(angle))
                run += 2 * r(i) + spacing
            }
            var y = centre.y + radius
            if (medalIndex >= 0) {
                angles[medalIndex] = bottom
                centres[medalIndex] = Offset(centre.x, y)
            }
            val pitch = 8.dp.toPx()
            var previous = if (medalIndex >= 0) medal else 0f
            val pendantRuns = mutableListOf<Pair<Int, Int>>()
            var upper: Int? = medalIndex.takeIf { it >= 0 }
            pendant.reversed().forEach { i ->
                y += previous + pitch + r(i)
                centres[i] = Offset(centre.x, y)
                previous = r(i)
                upper?.let { pendantRuns += it to i }
                upper = i
            }
            val ring = buildList { if (medalIndex >= 0) add(medalIndex); addAll(loop) }
            val loopRuns = ring.zipWithNext().toMutableList()
            if (medalIndex >= 0 && loop.isNotEmpty()) loopRuns += loop.last() to medalIndex
            LoopGeometry(
                centres, angles, loopRuns, pendantRuns, beads, centre, radius,
                small, large, medal, crucifixHeight, y + previous + 10.dp.toPx()
            )
        }
    }
}

/**
 * The whole rosary as a map: loop, medal, pendant and crucifix, prayed beads
 * told, the bead in hand ringed. Tap a bead to go to it.
 */
@Composable
fun RosaryLoopMap(
    beads: List<BeadKind>,
    activeBead: Int,
    onSelectBead: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (beads.isEmpty()) return
    val inks = pearlInks()
    val density = LocalDensity.current
    val view = LocalView.current
    val select by rememberUpdatedState(onSelectBead)
    BoxWithConstraints(modifier.fillMaxWidth()) {
        val widthPx = with(density) { maxWidth.toPx() }
        val g = remember(beads, widthPx) { LoopGeometry.layout(beads, widthPx, density) }
        Canvas(
            Modifier
                .fillMaxWidth()
                .height(with(density) { g.height.toDp() })
                .pointerInput(g) {
                    detectTapGestures { tap ->
                        val hit = g.centres.indices.minByOrNull { (g.centres[it] - tap).getDistance() }
                        if (hit != null && (g.centres[hit] - tap).getDistance() <= 20.dp.toPx()) {
                            view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                            select(hit)
                        }
                    }
                }
        ) {
            val stroke = 1.3.dp.toPx()
            g.loopRuns.forEach { (from, to) ->
                val fa = g.angles.getValue(from)
                var ta = g.angles.getValue(to)
                if (ta <= fa) ta += (2 * PI).toFloat()
                val a = fa + g.reach(from) / g.radius
                val b = ta - g.reach(to) / g.radius
                if (b > a) {
                    val prayed = maxOf(from, to) <= activeBead
                    drawArc(
                        if (prayed) inks.toldThread else inks.thread,
                        Math.toDegrees(a.toDouble()).toFloat(), Math.toDegrees((b - a).toDouble()).toFloat(),
                        false, Offset(g.centre.x - g.radius, g.centre.y - g.radius), Size(g.radius * 2, g.radius * 2),
                        style = Stroke(stroke, cap = StrokeCap.Round)
                    )
                }
            }
            g.pendantRuns.forEach { (upper, lower) ->
                val t = g.centres[upper].y + g.reach(upper)
                val b = g.centres[lower].y - g.reach(lower)
                if (b > t) {
                    val prayed = maxOf(upper, lower) <= activeBead
                    drawLine(if (prayed) inks.toldThread else inks.thread, Offset(g.centre.x, t), Offset(g.centre.x, b), stroke, StrokeCap.Round)
                }
            }
            beads.forEachIndexed { i, kind ->
                val c = g.centres[i]
                val current = i == activeBead
                val told = i < activeBead
                val fill = when { current -> inks.currentFill; told -> inks.toldFill; else -> inks.todoFill }
                val todo = !current && !told
                when (kind) {
                    BeadKind.CRUCIFIX -> drawCrucifix(c, g.crucifixHeight, if (current) inks.currentFill else inks.toldFill)
                    BeadKind.MEDAL -> drawMedal(c, g.medal, fill, inks, todo)
                    BeadKind.LARGE -> drawPearl(c, g.large, fill, inks, todo)
                    BeadKind.SMALL -> drawPearl(c, g.small, fill, inks, todo)
                }
                if (current) {
                    val rr = when (kind) {
                        BeadKind.CRUCIFIX -> g.crucifixHeight * 0.62f
                        BeadKind.LARGE -> g.large + 4.dp.toPx()
                        BeadKind.MEDAL -> g.medal + 4.dp.toPx()
                        BeadKind.SMALL -> g.small + 4.dp.toPx()
                    }
                    drawCircle(inks.ring, rr, c, style = Stroke(2.dp.toPx()))
                }
            }
        }
    }
}
