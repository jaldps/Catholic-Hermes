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
            todoEdge = p.textPrimary.copy(alpha = 0.12f),
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
private fun DrawScope.drawPearl(centre: Offset, r: Float, fill: Color, inks: PearlInks, todo: Boolean) {
    if (inks.shadow.alpha > 0f) {
        drawCircle(inks.shadow, radius = r * 1.02f, center = centre + Offset(0f, r * 0.22f))
    }
    drawCircle(fill, radius = r, center = centre)
    if (todo) {
        drawCircle(
            Brush.radialGradient(
                0f to inks.todoHighlight,
                1f to inks.todoHighlight.copy(alpha = 0f),
                center = centre + Offset(-r * 0.32f, -r * 0.38f),
                radius = r * 0.95f
            ),
            radius = r, center = centre
        )
        drawCircle(inks.todoEdge, radius = r - 0.5f, center = centre, style = Stroke(1f.coerceAtLeast(r * 0.07f)))
    } else {
        drawCircle(
            Brush.radialGradient(
                0f to Color.White.copy(alpha = 0.42f),
                1f to Color.White.copy(alpha = 0f),
                center = centre + Offset(-r * 0.32f, -r * 0.38f),
                radius = r * 0.7f
            ),
            radius = r, center = centre
        )
    }
}

/** The centre medal: a rounded lozenge with a small cross struck on it. */
private fun DrawScope.drawMedal(centre: Offset, r: Float, fill: Color, inks: PearlInks, todo: Boolean) {
    val lozenge = Path().apply {
        moveTo(centre.x, centre.y - r)
        quadraticBezierTo(centre.x + r * 0.15f, centre.y - r * 0.15f, centre.x + r * 0.78f, centre.y)
        quadraticBezierTo(centre.x + r * 0.15f, centre.y + r * 0.15f, centre.x, centre.y + r)
        quadraticBezierTo(centre.x - r * 0.15f, centre.y + r * 0.15f, centre.x - r * 0.78f, centre.y)
        quadraticBezierTo(centre.x - r * 0.15f, centre.y - r * 0.15f, centre.x, centre.y - r)
        close()
    }
    if (inks.shadow.alpha > 0f) {
        drawPath(lozenge, inks.shadow, alpha = 1f)
    }
    drawPath(lozenge, fill)
    if (todo) drawPath(lozenge, inks.todoEdge, style = Stroke(r * 0.08f, join = StrokeJoin.Round))
    val c = r * 0.36f
    val ink = if (todo) inks.toldFill else inks.corpus
    drawLine(ink, Offset(centre.x, centre.y - c), Offset(centre.x, centre.y + c), r * 0.11f, StrokeCap.Round)
    drawLine(ink, Offset(centre.x - c * 0.62f, centre.y - c * 0.22f), Offset(centre.x + c * 0.62f, centre.y - c * 0.22f), r * 0.11f, StrokeCap.Round)
}

/**
 * A Latin cross with budded (trefoil) ends and the corpus cut out of it in
 * the canvas colour, so it reads as a crucifix and never as a plus sign.
 */
private fun DrawScope.drawCrucifix(centre: Offset, height: Float, ink: Color, corpus: Color) {
    val top = centre.y - height / 2
    val bottom = centre.y + height / 2
    val stem = height * 0.13f
    val barY = top + height * 0.30f
    val half = height * 0.37f
    val bud = stem * 0.6f
    val corner = CornerRadius(stem * 0.25f)
    drawRoundRect(ink, Offset(centre.x - stem / 2, top + bud * 0.6f), Size(stem, bottom - top - bud * 0.6f), corner)
    drawRoundRect(ink, Offset(centre.x - half + bud * 0.6f, barY - stem / 2), Size(2 * half - bud * 1.2f, stem), corner)
    fun trefoil(at: Offset, out: Offset) {
        val side = Offset(-out.y, out.x)
        drawCircle(ink, bud, at + out * (bud * 0.5f))
        drawCircle(ink, bud * 0.62f, at + side * (bud * 1.05f) - out * (bud * 0.25f))
        drawCircle(ink, bud * 0.62f, at - side * (bud * 1.05f) - out * (bud * 0.25f))
    }
    trefoil(Offset(centre.x, top + bud * 1.1f), Offset(0f, -1f))
    trefoil(Offset(centre.x - half + bud * 1.1f, barY), Offset(-1f, 0f))
    trefoil(Offset(centre.x + half - bud * 1.1f, barY), Offset(1f, 0f))
    drawRoundRect(ink, Offset(centre.x - stem * 0.85f, bottom - stem * 0.7f), Size(stem * 1.7f, stem * 0.7f), corner)
    val thin = stem * 0.36f
    drawCircle(corpus, stem * 0.36f, Offset(centre.x, barY - stem * 0.95f))
    drawLine(corpus, Offset(centre.x - half + bud * 1.1f, barY - thin * 0.2f), Offset(centre.x, barY + thin * 0.5f), thin, StrokeCap.Round)
    drawLine(corpus, Offset(centre.x + half - bud * 1.1f, barY - thin * 0.2f), Offset(centre.x, barY + thin * 0.5f), thin, StrokeCap.Round)
    drawLine(corpus, Offset(centre.x, barY - stem * 0.5f), Offset(centre.x, barY + height * 0.30f), thin * 1.25f, StrokeCap.Round)
    drawLine(corpus, Offset(centre.x, barY + height * 0.30f), Offset(centre.x, bottom - stem * 1.1f), thin * 0.9f, StrokeCap.Round)
}

/** Half the drawn extent of a bead along the strand, in dp. */
private fun halfExtent(kind: BeadKind): Float = when (kind) {
    BeadKind.CRUCIFIX -> 15f
    BeadKind.LARGE -> 10.5f
    BeadKind.SMALL -> 7f
    BeadKind.MEDAL -> 12f
}

/** Radius (or half height) of a bead at rest, in dp. */
private fun restRadius(kind: BeadKind): Float = when (kind) {
    BeadKind.CRUCIFIX -> 15f
    BeadKind.LARGE -> 10f
    BeadKind.SMALL -> 7f
    BeadKind.MEDAL -> 11f
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
    height: Dp = 76.dp
) {
    if (beads.isEmpty()) return
    val inks = pearlInks()
    val density = LocalDensity.current
    val view = LocalView.current
    val still = LocalInspectionMode.current
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
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .pointerInput(beads, widthPx) {
                    detectTapGestures { tap ->
                        val dpx = density.density
                        val centreAlong = alongAt(position.value)
                        val hit = beads.indices.minByOrNull { i ->
                            abs((along[i] - centreAlong) * dpx + widthPx / 2f - tap.x)
                        } ?: return@detectTapGestures
                        val dx = abs((along[hit] - centreAlong) * dpx + widthPx / 2f - tap.x)
                        if (dx <= 22f * dpx) {
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
                            val landed = position.value.roundToInt().coerceIn(0, beads.lastIndex)
                            if (landed != target) select(landed)
                            else scope.launch { position.animateTo(target.toFloat(), spring(0.82f, 300f)) }
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
            val baseY = size.height * 0.42f
            val sag = 9f * dpx

            fun xOf(i: Int) = (along[i] - centreAlong) * dpx + half
            fun yOf(x: Float): Float {
                val d = ((x - half) / half).coerceIn(-1.4f, 1.4f)
                return baseY + sag * (1f - d * d)
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
                        strokeWidth = (if (prayed) 1.6f else 1.2f) * dpx, cap = StrokeCap.Round
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
                val todo = told < 0.5f && emphasis < 0.5f
                val fill = lerp(lerp(inks.todoFill, inks.toldFill, told), inks.currentFill, emphasis)
                when (beads[i]) {
                    BeadKind.CRUCIFIX -> drawCrucifix(
                        c, r * 2f,
                        lerp(lerp(inks.toldFill, inks.toldFill, told), inks.currentFill, emphasis),
                        inks.corpus
                    )
                    BeadKind.MEDAL -> drawMedal(c, r, fill, inks, todo)
                    else -> drawPearl(c, r, fill, inks, todo)
                }
                if (emphasis > 0.02f) {
                    val ringR = if (beads[i] == BeadKind.CRUCIFIX) r * 1.18f else r + 4.5f * dpx
                    drawCircle(
                        inks.ring.copy(alpha = inks.ring.alpha * emphasis),
                        radius = ringR, center = c,
                        style = Stroke(2f * dpx)
                    )
                }
            }

            // Pips: how many prayers are said on the bead in hand.
            if (subStepCount > 1) {
                val pitch = 8f * dpx
                val total = pitch * (subStepCount - 1)
                val y = size.height - 6f * dpx
                for (k in 0 until subStepCount) {
                    val cx = half - total / 2f + k * pitch
                    drawCircle(
                        if (k == subStepIndex) inks.pipOn else inks.pip,
                        radius = (if (k == subStepIndex) 2.4f else 2f) * dpx,
                        center = Offset(cx, y)
                    )
                }
            }

            // The strand dissolves into the canvas at both gutters.
            val fade = 44f * dpx
            drawRect(
                Brush.horizontalGradient(
                    0f to Color.Transparent,
                    (fade / w) to Color.Black,
                    (1f - fade / w) to Color.Black,
                    1f to Color.Transparent
                ),
                blendMode = BlendMode.DstIn
            )
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
                    BeadKind.CRUCIFIX -> drawCrucifix(c, g.crucifixHeight, if (current) inks.currentFill else inks.toldFill, inks.corpus)
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
