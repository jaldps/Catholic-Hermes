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

    /** A chaplet of beads hanging to one side, a pendant and a Latin cross. */
    private fun rosary(name: String, filled: Boolean, sharp: Boolean): ImageVector =
        ImageVector.Builder(name, 24.dp, 24.dp, 24f, 24f).apply {
            val cx = 9.6f
            val cy = 8.6f
            val r = 6.2f
            val open = Math.PI / 4
            val beadR = if (filled) 1.45f else 1.25f
            val ink = SolidColor(Color.Black)
            val beads: PathBuilder.() -> Unit = {
                for (k in 1 until 12) {
                    val t = open + Math.PI * 2 * k / 12
                    circle(cx + r * cos(t).toFloat(), cy + r * sin(t).toFloat(), beadR)
                }
            }
            if (filled) path(fill = ink, pathBuilder = beads)
            else path(
                stroke = ink, strokeLineWidth = 1.5f,
                pathBuilder = beads
            )
            val mx = cx + r * cos(open).toFloat()
            val my = cy + r * sin(open).toFloat()
            path(fill = ink) {
                // The centre medal and two pendant beads.
                moveTo(mx, my - 1.5f); lineTo(mx + 1.5f, my); lineTo(mx, my + 1.5f); lineTo(mx - 1.5f, my); close()
                circle(mx + 1.85f, my + 1.85f, 0.95f)
            }
            // The cross, stroked so its arms keep the identity's joins.
            path(
                stroke = ink,
                strokeLineWidth = if (filled) 2.2f else 1.9f,
                strokeLineCap = if (sharp) StrokeCap.Butt else StrokeCap.Round,
                strokeLineJoin = if (sharp) StrokeJoin.Miter else StrokeJoin.Round
            ) {
                val x = mx + 3.5f
                moveTo(x, my + 3.1f); lineTo(x, 23f)
                moveTo(x - 2.7f, my + 5.9f); lineTo(x + 2.7f, my + 5.9f)
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
    val candleOutlined by lazy { candle("candle.outlined", filled = false, sharp = false) }
    val candleFilled by lazy { candle("candle.filled", filled = true, sharp = false) }
    val candleSharp by lazy { candle("candle.sharp", filled = true, sharp = true) }
}
