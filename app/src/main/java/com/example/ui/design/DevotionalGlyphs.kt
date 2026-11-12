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
     * A rosary, drawn as the object: a closed oval loop of eight beads, a
     * two-bead stem and a 7-unit Latin cross hanging from it. The loop is
     * closed so it cannot collapse into a "Y" at 22dp, the way an open
     * necklace does; the cross is the only stroke and the only straight line.
     */
    private fun rosary(name: String, filled: Boolean, sharp: Boolean): ImageVector =
        ImageVector.Builder(name, 24.dp, 24.dp, 24f, 24f).apply {
            val cx = 12f
            val cy = 6.1f
            val rx = 5.4f
            val ry = 4.4f
            val beadR = if (filled) 1.28f else 1.12f
            val ink = SolidColor(Color.Black)
            val count = 8
            path(fill = ink) {
                for (k in 0 until count) {
                    // Starts at the bottom (where the stem hangs) and goes round.
                    val t = Math.PI / 2.0 + 2.0 * Math.PI * k / count
                    circle(cx + rx * cos(t).toFloat(), cy + ry * sin(t).toFloat(), beadR)
                }
                // The two stem beads.
                circle(cx, cy + ry + 3.0f, beadR * 0.85f)
                circle(cx, cy + ry + 5.8f, beadR * 0.85f)
            }
            val join = if (sharp) StrokeJoin.Miter else StrokeJoin.Round
            val cap = if (sharp) StrokeCap.Butt else StrokeCap.Round
            val w = if (filled) 1.9f else 1.5f
            // The Latin cross, 7 units tall.
            path(stroke = ink, strokeLineWidth = w, strokeLineCap = cap, strokeLineJoin = join) {
                moveTo(cx, 17.9f); lineTo(cx, 23.4f)
                moveTo(cx - 2.5f, 19.9f); lineTo(cx + 2.5f, 19.9f)
            }
        }.build()

    /** The Angelus hour: a sun at the horizon, in the same outline weight as the rest. */
    private fun sunrise(name: String, filled: Boolean, sharp: Boolean): ImageVector =
        ImageVector.Builder(name, 24.dp, 24.dp, 24f, 24f).apply {
            val ink = SolidColor(Color.Black)
            val cap = if (sharp) StrokeCap.Butt else StrokeCap.Round
            val join = if (sharp) StrokeJoin.Miter else StrokeJoin.Round
            val sun: PathBuilder.() -> Unit = {
                moveTo(4.8f, 17f)
                arcTo(7.2f, 7.2f, 0f, false, true, 19.2f, 17f)
                close()
            }
            if (filled) path(fill = ink, pathBuilder = sun)
            else path(stroke = ink, strokeLineWidth = 1.5f, strokeLineCap = cap, strokeLineJoin = join, pathBuilder = sun)
            path(stroke = ink, strokeLineWidth = 1.5f, strokeLineCap = cap) {
                moveTo(1.8f, 20.5f); lineTo(22.2f, 20.5f)
                moveTo(12f, 2.6f); lineTo(12f, 5.4f)
                moveTo(4.4f, 8.4f); lineTo(6.4f, 10.4f)
                moveTo(19.6f, 8.4f); lineTo(17.6f, 10.4f)
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

    /** An open book: a thin outline when off, solid pages when on (the guide toggle). */
    private fun book(name: String, filled: Boolean): ImageVector =
        ImageVector.Builder(name, 24.dp, 24.dp, 24f, 24f).apply {
            val ink = SolidColor(Color.Black)
            if (filled) {
                path(fill = ink) {
                    moveTo(11.2f, 7.7f); curveTo(9.7f, 6.5f, 6.9f, 6.1f, 3.6f, 6.4f); lineTo(3.6f, 17.6f)
                    curveTo(6.9f, 17.3f, 9.7f, 17.7f, 11.2f, 18.9f); close()
                    moveTo(12.8f, 7.7f); curveTo(14.3f, 6.5f, 17.1f, 6.1f, 20.4f, 6.4f); lineTo(20.4f, 17.6f)
                    curveTo(17.1f, 17.3f, 14.3f, 17.7f, 12.8f, 18.9f); close()
                }
            } else {
                path(stroke = ink, strokeLineWidth = 1.5f, strokeLineCap = StrokeCap.Round, strokeLineJoin = StrokeJoin.Round) {
                    moveTo(12f, 7.7f); curveTo(10.3f, 6.2f, 7.1f, 5.7f, 3.6f, 6.1f); lineTo(3.6f, 17.9f)
                    curveTo(7.1f, 17.5f, 10.3f, 18.1f, 12f, 19.6f)
                    curveTo(13.7f, 18.1f, 16.9f, 17.5f, 20.4f, 17.9f); lineTo(20.4f, 6.1f)
                    curveTo(16.9f, 5.7f, 13.7f, 6.2f, 12f, 7.7f); close()
                    moveTo(12f, 7.7f); lineTo(12f, 19.6f)
                }
            }
        }.build()

    val bookOutlined by lazy { book("book.outlined", filled = false) }
    val bookFilled by lazy { book("book.filled", filled = true) }
    val rosaryOutlined by lazy { rosary("rosary.outlined", filled = false, sharp = false) }
    val rosaryFilled by lazy { rosary("rosary.filled", filled = true, sharp = false) }
    val rosarySharp by lazy { rosary("rosary.sharp", filled = true, sharp = true) }
    val sunriseOutlined by lazy { sunrise("sunrise.outlined", filled = false, sharp = false) }
    val sunriseFilled by lazy { sunrise("sunrise.filled", filled = true, sharp = false) }
    val candleOutlined by lazy { candle("candle.outlined", filled = false, sharp = false) }
    val candleFilled by lazy { candle("candle.filled", filled = true, sharp = false) }
    val candleSharp by lazy { candle("candle.sharp", filled = true, sharp = true) }
}
