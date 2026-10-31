package com.example.ui.components

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.RectF
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.xref.BibleLine
import com.example.data.xref.VerseRef
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.abs

/**
 * The inks the arcs are drawn in, per identity: the Old Testament and the New
 * each get their own, so a verse's reach across the two reads at a glance.
 * Apple and Material take the tint and a tonal partner; the Vatican draws in
 * gold leaf and rubric red, as a missal would.
 */
private data class ArcInks(val oldTestament: Color, val newTestament: Color, val baseline: Color, val focus: Color)

@Composable
private fun arcInks(): ArcInks {
    val design = LocalAppDesign.current
    val p = design.palette
    val scheme = androidx.compose.material3.MaterialTheme.colorScheme
    return when (design.language) {
        DesignLanguage.APPLE -> ArcInks(Color(0xFFFF9F0A), scheme.primary, p.hairline, p.textPrimary)
        DesignLanguage.MATERIAL -> ArcInks(scheme.tertiary, scheme.primary, scheme.outlineVariant, scheme.onSurface)
        DesignLanguage.VATICAN -> ArcInks(p.gold, p.accent, p.hairline, p.textPrimary)
    }
}

/** Canonical order puts Matthew first among the New Testament books. */
private fun BibleLine.newTestamentStart(): Int = books.firstOrNull { it.first == "mat" }?.second ?: total

private fun DrawScope.arc(x1: Float, x2: Float, baseline: Float, maxHeight: Float, color: Color, width: Float) {
    val left = minOf(x1, x2)
    val right = maxOf(x1, x2)
    val span = right - left
    if (span < 0.5f) return
    // Arc height grows with reach, so a link across the Bible towers over a
    // link to the next chapter, as on the printed arc diagrams.
    val h = (span / size.width).coerceIn(0.04f, 1f).let { kotlin.math.sqrt(it) } * maxHeight
    val path = Path().apply {
        moveTo(left, baseline)
        cubicTo(left, baseline - h * 1.33f, right, baseline - h * 1.33f, right, baseline)
    }
    drawPath(path, color, style = Stroke(width = width, cap = StrokeCap.Round))
}

private fun DrawScope.baselineBand(line: BibleLine, inks: ArcInks, y: Float, thickness: Float) {
    val nt = line.newTestamentStart().toFloat() / line.total * size.width
    drawRect(inks.oldTestament.copy(alpha = 0.55f), Offset(0f, y), Size(nt, thickness))
    drawRect(inks.newTestament.copy(alpha = 0.55f), Offset(nt, y), Size(size.width - nt, thickness))
    // A hairline tick at each book, so the line reads as the canon's shelf.
    line.books.forEach { (_, start, _) ->
        val x = start.toFloat() / line.total * size.width
        drawLine(inks.baseline, Offset(x, y - thickness), Offset(x, y + thickness * 2), strokeWidth = 1f)
    }
}

/**
 * One verse's reach over the whole Bible, for the top of the verse sheet:
 * the canon as a line, the verse as a point on it, and an arc to each place
 * it is cross-referenced, coloured by testament. The arcs draw themselves in.
 */
@Composable
fun CrossReferenceArcStrip(
    line: BibleLine,
    source: VerseRef,
    targets: List<VerseRef>,
    modifier: Modifier = Modifier,
    height: Dp = 96.dp
) {
    val inks = arcInks()
    val still = LocalInspectionMode.current
    val reveal = remember(source) { Animatable(if (still) 1f else 0f) }
    LaunchedEffect(source) { reveal.animateTo(1f, tween(900)) }
    val from = line.fraction(source)
    val nt = line.newTestamentStart()
    val points = remember(targets, line) {
        targets.mapNotNull { t -> line.position(t)?.let { it to (it >= nt) } }
    }
    Canvas(modifier.fillMaxWidth().height(height)) {
        val base = size.height - 6.dp.toPx()
        baselineBand(line, inks, base, 3.dp.toPx())
        if (from == null) return@Canvas
        val x0 = from * size.width
        val k = reveal.value
        points.forEach { (pos, isNt) ->
            val x = x0 + (pos.toFloat() / line.total * size.width - x0) * k
            arc(x0, x, base, size.height - 12.dp.toPx(), (if (isNt) inks.newTestament else inks.oldTestament).copy(alpha = 0.85f), 1.5.dp.toPx())
        }
        drawCircle(inks.focus, 4.dp.toPx(), Offset(x0, base + 1.5.dp.toPx()))
    }
}

/**
 * The whole Bible at once, for the landscape study: every connection drawn
 * faint (rendered once, off the main thread, into a bitmap) and the focused
 * verses' arcs inked over it. Testament and a few book names mark the line.
 */
@Composable
fun BibleArcsPanel(
    line: BibleLine,
    allConnections: () -> Sequence<Pair<VerseRef, VerseRef>>,
    focus: List<Pair<VerseRef, VerseRef>>,
    focusLabel: String?,
    modifier: Modifier = Modifier,
    bookLabel: (String) -> String
) {
    val inks = arcInks()
    val dark = LocalAppDesign.current.isDark
    val density = LocalDensity.current
    val measurer = rememberTextMeasurer()
    val styles = LocalAppTextStyles.current
    val nt = line.newTestamentStart()
    BoxWithConstraints(modifier) {
        val canvasSize = IntSize(constraints.maxWidth, constraints.maxHeight)
        val still = LocalInspectionMode.current
        val faintAlpha = if (dark) 0.028f else 0.022f
        val bottomInset = with(density) { 22.dp.toPx() }
        // Previews and screenshots draw in one pass; the app renders the web of
        // all connections off the main thread and fades the focus in.
        val faintNow = if (still && canvasSize.width > 0) remember(canvasSize, line, inks) {
            renderAllArcs(line, allConnections(), canvasSize, inks, nt, bottomInset, faintAlpha)
        } else null
        val faintLater: ImageBitmap? by produceState<ImageBitmap?>(null, canvasSize, line, inks) {
            if (still || canvasSize.width == 0 || canvasSize.height == 0) return@produceState
            value = withContext(Dispatchers.Default) {
                renderAllArcs(line, allConnections(), canvasSize, inks, nt, bottomInset, faintAlpha)
            }
        }
        val faint = faintNow ?: faintLater
        val reveal = remember(focus) { Animatable(if (still) 1f else 0f) }
        LaunchedEffect(focus) { reveal.animateTo(1f, tween(700)) }

        Canvas(Modifier.fillMaxSize()) {
            faint?.let { drawImage(it) }
            val base = size.height - 22.dp.toPx()
            baselineBand(line, inks, base, 3.dp.toPx())
            val w = size.width
            focus.forEach { (a, b) ->
                val pa = line.position(a) ?: return@forEach
                val pb = line.position(b) ?: return@forEach
                val xa = pa.toFloat() / line.total * w
                val xb = xa + (pb.toFloat() / line.total * w - xa) * reveal.value
                arc(xa, xb, base, base - 28.dp.toPx(), (if (pb >= nt) inks.newTestament else inks.oldTestament), 2.dp.toPx())
            }
            focus.firstOrNull()?.first?.let { line.position(it) }?.let {
                drawCircle(inks.focus, 4.5.dp.toPx(), Offset(it.toFloat() / line.total * w, base + 1.5.dp.toPx()))
            }
            drawBookLabels(line, measurer, styles.navLabel.copy(fontSize = 10.sp), inks, base + 8.dp.toPx(), bookLabel)
            if (focusLabel != null) {
                val text = measurer.measure(focusLabel, styles.rowTitle.copy(color = inks.focus))
                drawText(text, topLeft = Offset(12.dp.toPx(), 8.dp.toPx()))
            }
        }
    }
}

private val LabelledBooks = listOf("gen", "exo", "jos", "1sa", "1ch", "job", "psa", "isa", "jer", "eze", "dan", "mat", "joh", "act", "rom", "heb", "rev")

private fun DrawScope.drawBookLabels(
    line: BibleLine,
    measurer: TextMeasurer,
    style: androidx.compose.ui.text.TextStyle,
    inks: ArcInks,
    y: Float,
    bookLabel: (String) -> String
) {
    var lastRight = -1f
    line.books.filter { it.first in LabelledBooks }.forEach { (id, start, _) ->
        val x = start.toFloat() / line.total * size.width
        val layout = measurer.measure(bookLabel(id), style.copy(color = inks.focus.copy(alpha = 0.6f)))
        if (x > lastRight + 4f && x + layout.size.width < size.width) {
            drawText(layout, topLeft = Offset(x, y))
            lastRight = x + layout.size.width
        }
    }
}

private fun renderAllArcs(
    line: BibleLine,
    connections: Sequence<Pair<VerseRef, VerseRef>>,
    size: IntSize,
    inks: ArcInks,
    nt: Int,
    bottomInset: Float,
    alpha: Float
): ImageBitmap {
    val bitmap = Bitmap.createBitmap(size.width, size.height, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(bitmap)
    val base = size.height - bottomInset
    val maxH = base - 8f
    val paintOt = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE; strokeWidth = 1f; color = inks.focus.copy(alpha = alpha).toArgb()
    }
    val paintNt = Paint(paintOt).apply { color = inks.focus.copy(alpha = alpha).toArgb() }
    val oval = RectF()
    val w = size.width.toFloat()
    for ((a, b) in connections) {
        val pa = line.position(a) ?: continue
        val pb = line.position(b) ?: continue
        val x1 = pa.toFloat() / line.total * w
        val x2 = pb.toFloat() / line.total * w
        val span = abs(x2 - x1)
        if (span < 1f) continue
        val h = kotlin.math.sqrt((span / w).coerceIn(0.02f, 1f)) * maxH
        oval.set(minOf(x1, x2), base - h, maxOf(x1, x2), base + h)
        canvas.drawArc(oval, 180f, 180f, false, if (pb >= nt) paintNt else paintOt)
    }
    return bitmap.asImageBitmap()
}

/** Fills the parent and reports its pixel size, so the bitmap matches it. */
private fun Modifier.matchParentSizeCompat(onSize: (IntSize) -> Unit): Modifier =
    this.fillMaxSize().onSizeChanged(onSize)
