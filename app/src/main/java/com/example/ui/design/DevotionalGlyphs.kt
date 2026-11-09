package com.example.ui.design

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * Glyphs the stock icon sets lack. Material Symbols has no rosary and no
 * prayer sign: its nearest stand-ins are a seated meditator and a hand giving
 * a heart, which say yoga and charity. These are drawn on the same 24-unit
 * grid, in an outlined resting form and a filled selected form, with round
 * joins for the rounded identities and mitred ones for the sharp.
 */
internal object DevotionalGlyphs {

    private fun PathBuilder.circle(x: Float, y: Float, r: Float) {
        moveTo(x - r, y)
        arcTo(r, r, 0f, true, false, x + r, y)
        arcTo(r, r, 0f, true, false, x - r, y)
        close()
    }

    /**
     * A rosary: a loop of beads open at the bottom, a medal, and a Latin
     * cross hanging from it (the classic chaplet silhouette, so it never reads
     * as a magnifier). Strokes match Material's outlined weight.
     */
    private fun rosary(name: String, filled: Boolean, sharp: Boolean): ImageVector =
        ImageVector.Builder(name, 24.dp, 24.dp, 24f, 24f).apply {
            val cx = 12f
            val cy = 7.6f
            val r = 5.4f
            val beadR = if (filled) 1.5f else 1.25f
            val ink = SolidColor(Color.Black)
            val count = 9
            val gap = Math.toRadians(24.0)
            path(fill = ink) {
                for (k in 0 until count) {
                    val t = Math.PI / 2 + gap + (2 * Math.PI - 2 * gap) * k / (count - 1)
                    circle(cx + r * cos(t).toFloat(), cy + r * sin(t).toFloat(), beadR)
                }
            }
            val join = if (sharp) StrokeJoin.Miter else StrokeJoin.Round
            val cap = if (sharp) StrokeCap.Butt else StrokeCap.Round
            val w = if (filled) 2.2f else 1.8f
            // The medal that joins the loop to the cross.
            if (filled) path(fill = ink) { circle(cx, cy + r + 1.7f, 1.35f) }
            else path(stroke = ink, strokeLineWidth = 1.5f) { circle(cx, cy + r + 1.7f, 1.1f) }
            // The cross.
            path(stroke = ink, strokeLineWidth = w, strokeLineCap = cap, strokeLineJoin = join) {
                moveTo(cx, cy + r + 3.4f); lineTo(cx, 22.8f)
                moveTo(cx - 2.5f, 18.4f); lineTo(cx + 2.5f, 18.4f)
            }
        }.build()

    /** The Angelus hour: a sun at the horizon, in the same outline weight as the rest. */
    private fun sunrise(name: String, filled: Boolean, sharp: Boolean): ImageVector =
        ImageVector.Builder(name, 24.dp, 24.dp, 24f, 24f).apply {
            val ink = SolidColor(Color.Black)
            val cap = if (sharp) StrokeCap.Butt else StrokeCap.Round
            val join = if (sharp) StrokeJoin.Miter else StrokeJoin.Round
            val sun: PathBuilder.() -> Unit = {
                moveTo(6.5f, 16f)
                arcTo(5.5f, 5.5f, 0f, false, true, 17.5f, 16f)
                close()
            }
            if (filled) path(fill = ink, pathBuilder = sun)
            else path(stroke = ink, strokeLineWidth = 1.8f, strokeLineCap = cap, strokeLineJoin = join, pathBuilder = sun)
            path(stroke = ink, strokeLineWidth = 1.8f, strokeLineCap = cap) {
                moveTo(3f, 19.5f); lineTo(21f, 19.5f)
                moveTo(12f, 4f); lineTo(12f, 6.2f)
                moveTo(4.7f, 8.9f); lineTo(6.3f, 10.4f)
                moveTo(19.3f, 8.9f); lineTo(17.7f, 10.4f)
            }
        }.build()

    /** A votive candle: the sign of a prayer left burning. */
    private fun candle(name: String, filled: Boolean, sharp: Boolean): ImageVector =
        ImageVector.Builder(name, 24.dp, 24.dp, 24f, 24f).apply {
            val ink = SolidColor(Color.Black)
            val join = if (sharp) StrokeJoin.Miter else StrokeJoin.Round
            val cap = if (sharp) StrokeCap.Butt else StrokeCap.Round
            // Flame.
            path(fill = ink) {
                moveTo(12f, 1.6f)
                curveTo(14.6f, 4.4f, 15.2f, 6.2f, 15.2f, 7.4f)
                curveTo(15.2f, 9.2f, 13.8f, 10.5f, 12f, 10.5f)
                curveTo(10.2f, 10.5f, 8.8f, 9.2f, 8.8f, 7.4f)
                curveTo(8.8f, 6.0f, 9.6f, 4.4f, 12f, 1.6f)
                close()
            }
            // Wick.
            path(stroke = ink, strokeLineWidth = 1.2f, strokeLineCap = cap) {
                moveTo(12f, 10.5f); lineTo(12f, 12.2f)
            }
            // Body.
            val body: PathBuilder.() -> Unit = {
                if (sharp) {
                    moveTo(8.3f, 12.6f); lineTo(15.7f, 12.6f); lineTo(15.7f, 20.2f); lineTo(8.3f, 20.2f); close()
                } else {
                    moveTo(9.3f, 12.6f); lineTo(14.7f, 12.6f)
                    arcTo(1f, 1f, 0f, false, true, 15.7f, 13.6f)
                    lineTo(15.7f, 20.2f); lineTo(8.3f, 20.2f); lineTo(8.3f, 13.6f)
                    arcTo(1f, 1f, 0f, false, true, 9.3f, 12.6f)
                    close()
                }
            }
            if (filled) path(fill = ink, pathBuilder = body)
            else path(stroke = ink, strokeLineWidth = 1.6f, strokeLineJoin = join, pathBuilder = body)
            // Base.
            path(stroke = ink, strokeLineWidth = 1.8f, strokeLineCap = cap) {
                moveTo(5.6f, 21.9f); lineTo(18.4f, 21.9f)
            }
        }.build()

    val rosaryOutlined by lazy { rosary("rosary.outlined", filled = false, sharp = false) }
    val rosaryFilled by lazy { rosary("rosary.filled", filled = true, sharp = false) }
    val rosarySharp by lazy { rosary("rosary.sharp", filled = true, sharp = true) }
    val sunriseOutlined by lazy { sunrise("sunrise.outlined", filled = false, sharp = false) }
    val sunriseFilled by lazy { sunrise("sunrise.filled", filled = true, sharp = false) }
    val candleOutlined by lazy { candle("candle.outlined", filled = false, sharp = false) }
    val candleFilled by lazy { candle("candle.filled", filled = true, sharp = false) }
    val candleSharp by lazy { candle("candle.sharp", filled = true, sharp = true) }
}
