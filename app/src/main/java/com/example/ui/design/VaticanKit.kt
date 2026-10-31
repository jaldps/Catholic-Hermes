package com.example.ui.design

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max

/**
 * The Vatican identity, set as a printer would set it.
 *
 * Two rules govern everything here.
 *
 * **Gold is leaf, not paint.** It is spent in exactly four places: the single
 * hairline under the masthead, the fleuron under a title, the illuminated
 * initial that opens a chapter, and the fleuron that closes a section.
 * Everything else is ink. Red is reserved for rubrication: verse figures, the
 * current entry, the ribbon that marks the open section, the liturgical colour.
 *
 * **Everything sits on one baseline grid.** Scripture is leaded at 30sp with no
 * space added between verses, exactly as a Bible column is set solid, and the
 * illuminated initial is cut into that grid: its cap line meets the first
 * line's cap line and its baseline sits on the second line's baseline, with the
 * remainder of the opening word running on beside it. Nothing floats on a
 * shadow; depth is carried by ruled lines, and corners are square because paper
 * has no radius.
 *
 * The leaf itself is not a flat tint. A procedural vellum — fine grain, slow
 * mottle and a scatter of fibres, generated once per process and tiled — lies
 * over the whole page, ink included, because ink sits *in* paper, not on a
 * pane of glass above it. At night the page is lit by a single candle: a warm
 * pool high on the leaf, falling away to umber at the edges.
 */
object VaticanKit : AppKit {

    // ------------------------------------------------------------- metrics

    /** Leading of the scripture column; every vertical interval is a multiple. */
    private const val LeadingSp = 30f

    /** First baseline of a scripture block, measured from the block's top. */
    private const val FirstBaselineSp = 23.15f

    /** Illuminated initial: a two-line versal in Cinzel. */
    private const val DropCapLines = 2
    private const val DropCapSizeSp = 60f

    /** Cinzel's hhea ascent is 0.976 em, so the versal baseline falls here. */
    private const val DropCapAscentSp = DropCapSizeSp * 0.976f

    /**
     * Inscriptional caps need more tracking the smaller they are cut: a title
     * at 26sp wants about 0.085 em, a 9sp running label about 0.22 em. One
     * uniform value across the scale is the surest sign of a pastiche.
     */
    private fun TextStyle.tracked(em: Float): TextStyle =
        copy(letterSpacing = (fontSize.value * em).sp)

    // --------------------------------------------------------------- colour

    /** Ink at rule strength — darker on parchment, softer on vellum at night. */
    @Composable
    private fun inkRule(): Color {
        val design = LocalAppDesign.current
        return design.palette.textPrimary.copy(alpha = if (design.isDark) 0.38f else 0.72f)
    }

    /** The companion thin stroke of a scotch rule, and row separators. */
    @Composable
    private fun inkHair(): Color {
        val design = LocalAppDesign.current
        return design.palette.textPrimary.copy(alpha = if (design.isDark) 0.17f else 0.26f)
    }

    /**
     * Body ink. Candlelight does not print pure white on vellum, so the dark
     * variant is held back from full strength rather than being the light
     * theme with the lamp turned down.
     */
    @Composable
    private fun bodyInk(): Color {
        val design = LocalAppDesign.current
        return if (design.isDark) design.palette.textPrimary.copy(alpha = 0.92f)
        else design.palette.textPrimary
    }

    /** Rubric red as it should sit in running text on either ground. */
    @Composable
    private fun rubric(): Color = LocalAppDesign.current.palette.accent

    /**
     * A liturgical colour as pigment on this ground. At night it is lifted
     * toward vellum so violet and green still read; by day a pale pigment —
     * the white and gold of a solemnity — is deepened to a burnished ochre,
     * because white ink on parchment is invisible and a printer would use gold.
     */
    @Composable
    private fun pigment(color: Color): Color {
        val design = LocalAppDesign.current
        return when {
            design.isDark -> lerp(color, design.palette.textPrimary, 0.45f)
            color.luminance() > 0.45f -> lerp(design.palette.gold, design.palette.textPrimary, 0.28f)
            color.luminance() > 0.25f -> lerp(color, design.palette.textPrimary, 0.35f)
            else -> color
        }
    }

    // ---------------------------------------------------------------- vellum

    /**
     * Procedural vellum, generated once per process for each ground. Three
     * layers: a slow mottle (two octaves of tileable value noise), a fine
     * tooth of per-pixel grain, and a scatter of short fibres. Signed: below
     * zero it darkens with warm umber, above it lifts with a cream highlight, so
     * the average tone of the page is unchanged. Tileable in both axes.
     */
    private object Vellum {
        private const val Tile = 512
        private val cache = HashMap<Boolean, ImageBitmap>()

        fun texture(dark: Boolean): ImageBitmap = synchronized(cache) {
            cache.getOrPut(dark) { build(dark) }
        }

        private fun lattice(rnd: java.util.Random, g: Int) = FloatArray(g * g) { rnd.nextFloat() * 2f - 1f }

        private fun smooth(t: Float) = t * t * (3f - 2f * t)

        private fun noise(lat: FloatArray, g: Int, x: Float, y: Float): Float {
            val x0 = floor(x).toInt()
            val y0 = floor(y).toInt()
            val fx = smooth(x - x0)
            val fy = smooth(y - y0)
            fun at(i: Int, j: Int) = lat[((j % g + g) % g) * g + ((i % g + g) % g)]
            val a = at(x0, y0) + (at(x0 + 1, y0) - at(x0, y0)) * fx
            val b = at(x0, y0 + 1) + (at(x0 + 1, y0 + 1) - at(x0, y0 + 1)) * fx
            return a + (b - a) * fy
        }

        private fun build(dark: Boolean): ImageBitmap {
            val n = Tile
            val rnd = java.util.Random(if (dark) 0x5EEDDA2EL else 0x5EED11E7L)
            val g1 = 6
            val g2 = 22
            val l1 = lattice(rnd, g1)
            val l2 = lattice(rnd, g2)
            val mottleAmp = if (dark) 0.075f else 0.040f
            val grainAmp = if (dark) 0.050f else 0.040f
            // Umber shadow and cream lift; at night the lift is warmer and fainter.
            val shade = if (dark) intArrayOf(0, 0, 0) else intArrayOf(74, 52, 24)
            val lift = if (dark) intArrayOf(255, 214, 160) else intArrayOf(255, 252, 240)
            val liftScale = if (dark) 0.55f else 1f
            val px = IntArray(n * n)
            for (y in 0 until n) {
                for (x in 0 until n) {
                    val m = noise(l1, g1, x * g1 / n.toFloat(), y * g1 / n.toFloat()) * 0.6f +
                        noise(l2, g2, x * g2 / n.toFloat(), y * g2 / n.toFloat()) * 0.4f
                    // Triangular grain: two uniforms summed, softer than white noise.
                    val gr = (rnd.nextFloat() + rnd.nextFloat() - 1f)
                    val v = m * mottleAmp + gr * grainAmp
                    val c = if (v < 0f) shade else lift
                    val a = (abs(v) * (if (v < 0f) 1f else liftScale) * 255f).toInt().coerceIn(0, 255)
                    px[y * n + x] = (a shl 24) or (c[0] shl 16) or (c[1] shl 8) or c[2]
                }
            }
            val bmp = android.graphics.Bitmap.createBitmap(n, n, android.graphics.Bitmap.Config.ARGB_8888)
            bmp.setPixels(px, 0, n, 0, 0, n, n)
            // Fibres: short, faintly curved strokes, mostly lying with the grain.
            val canvas = android.graphics.Canvas(bmp)
            val paint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                style = android.graphics.Paint.Style.STROKE
                strokeCap = android.graphics.Paint.Cap.ROUND
            }
            repeat(if (dark) 110 else 90) {
                val x0 = rnd.nextFloat() * n
                val y0 = rnd.nextFloat() * n
                val len = 6f + rnd.nextFloat() * 22f
                val ang = (rnd.nextFloat() - 0.5f) * 1.4f + if (rnd.nextFloat() < 0.5f) 0f else Math.PI.toFloat()
                val dx = kotlin.math.cos(ang) * len
                val dy = kotlin.math.sin(ang) * len
                val bend = (rnd.nextFloat() - 0.5f) * len * 0.5f
                val light = rnd.nextFloat() < 0.4f
                val c = if (light) lift else shade
                val alpha = ((if (dark) 9 else 7) + rnd.nextInt(6)) * (if (light) liftScale else 1f)
                paint.color = android.graphics.Color.argb(alpha.toInt(), c[0], c[1], c[2])
                paint.strokeWidth = 0.5f + rnd.nextFloat() * 0.5f
                val path = android.graphics.Path().apply {
                    moveTo(x0, y0)
                    quadTo(x0 + dx / 2 - dy / len * bend, y0 + dy / 2 + dx / len * bend, x0 + dx, y0 + dy)
                }
                // Draw the wrapped copies so the fibre continues across the seam.
                for (ox in -1..1) for (oy in -1..1) {
                    canvas.save()
                    canvas.translate(ox * n.toFloat(), oy * n.toFloat())
                    canvas.drawPath(path, paint)
                    canvas.restore()
                }
            }
            return bmp.asImageBitmap()
        }
    }

    /** The leaf laid over the whole page: grain, then light. */
    private fun Modifier.vellum(dark: Boolean, texture: ImageBitmap): Modifier {
        val brush = ShaderBrush(ImageShader(texture, TileMode.Repeated, TileMode.Repeated))
        return drawWithContent {
            drawContent()
            drawRect(brush)
            val w = size.width
            val h = size.height
            if (dark) {
                // One candle, set above the text block: a warm pool that falls
                // away to umber at the edges of the leaf.
                drawRect(
                    Brush.radialGradient(
                        0f to Color(0xFFFFC27A).copy(alpha = 0.075f),
                        0.55f to Color(0xFFFFB060).copy(alpha = 0.025f),
                        1f to Color.Transparent,
                        center = Offset(w * 0.5f, h * 0.30f),
                        radius = h * 0.62f
                    )
                )
                drawRect(
                    Brush.radialGradient(
                        0f to Color.Transparent,
                        0.58f to Color.Transparent,
                        1f to Color.Black.copy(alpha = 0.42f),
                        center = Offset(w * 0.5f, h * 0.40f),
                        radius = max(w, h) * 0.78f
                    )
                )
            } else {
                // Age at the edges of the leaf, not a picture-frame vignette:
                // the fore-edge and foot tone faintly toward umber.
                drawRect(
                    Brush.radialGradient(
                        0f to Color.Transparent,
                        0.62f to Color.Transparent,
                        1f to Color(0xFF7A5424).copy(alpha = 0.11f),
                        center = Offset(w * 0.5f, h * 0.42f),
                        radius = max(w, h) * 0.80f
                    )
                )
            }
        }
    }

    // ---------------------------------------------------------------- motion

    /**
     * Press feedback as ink taking to paper: a soft wash that spreads slowly
     * from the finger and lifts more slowly still. No ripple ring, no state
     * layer. Umber on parchment; a breath of gold on vellum at night.
     */
    private object InkWash : IndicationNodeFactory {
        override fun create(interactionSource: InteractionSource): DelegatableNode =
            InkWashNode(interactionSource)

        override fun equals(other: Any?): Boolean = other === this
        override fun hashCode(): Int = 0x1A5E
    }

    private class InkWashNode(private val source: InteractionSource) :
        Modifier.Node(), DrawModifierNode, CompositionLocalConsumerModifierNode {

        private val strength = Animatable(0f)
        private val spread = Animatable(0f)
        private var origin = Offset.Unspecified

        override fun onAttach() {
            coroutineScope.launch {
                source.interactions.collect { interaction ->
                    when (interaction) {
                        is PressInteraction.Press -> {
                            origin = interaction.pressPosition
                            launch {
                                spread.snapTo(0f)
                                spread.animateTo(1f, tween(560, easing = SlowOut))
                            }
                            launch { strength.animateTo(1f, tween(120)) }
                        }
                        is PressInteraction.Release, is PressInteraction.Cancel -> launch {
                            strength.animateTo(0f, tween(640, easing = SlowOut))
                        }
                    }
                }
            }
        }

        override fun ContentDrawScope.draw() {
            drawContent()
            val s = strength.value
            if (s <= 0f) return
            val design = currentValueOf(LocalAppDesign)
            val tint = if (design.isDark) design.palette.gold else Color(0xFF3A2614)
            val peak = if (design.isDark) 0.10f else 0.075f
            val c = if (origin.isSpecified()) origin else center
            val r = size.maxDimension * (0.25f + 0.95f * spread.value)
            clipRect {
                drawRect(
                    Brush.radialGradient(
                        0f to tint.copy(alpha = peak * s),
                        0.7f to tint.copy(alpha = peak * 0.55f * s),
                        1f to Color.Transparent,
                        center = c,
                        radius = r
                    )
                )
            }
        }

        private fun Offset.isSpecified() = this != Offset.Unspecified
    }

    /** Dignified easing: a long, quiet settle, the way a leaf comes to rest. */
    private val SlowOut = androidx.compose.animation.core.CubicBezierEasing(0.22f, 0.61f, 0.36f, 1f)

    private fun Modifier.press(enabled: Boolean = true, onClick: () -> Unit): Modifier =
        clickable(interactionSource = null, indication = InkWash, enabled = enabled, onClick = onClick)

    // ---------------------------------------------------------------- rules

    /** A single hairline of ink. */
    @Composable
    private fun Hairline(modifier: Modifier = Modifier, color: Color? = null) {
        Box(modifier.fillMaxWidth().height(0.5.dp).background(color ?: inkHair()))
    }

    /**
     * A scotch rule: one firm stroke with a thin companion under it. This is
     * the separator of printed matter — not a bevel, not a shadow.
     */
    @Composable
    private fun ScotchRule(
        modifier: Modifier = Modifier,
        gap: Dp = 2.5.dp,
        thinColor: Color? = null
    ) {
        val hair = thinColor ?: inkHair()
        Column(modifier.fillMaxWidth()) {
            Box(Modifier.fillMaxWidth().height(1.dp).background(inkRule()))
            Spacer(Modifier.height(gap))
            Box(Modifier.fillMaxWidth().height(0.5.dp).background(hair))
        }
    }

    /** A lozenge of leaf — the printer's fleuron, drawn rather than set. */
    @Composable
    private fun Lozenge(size: Dp, color: Color? = null) {
        val gold = color ?: LocalAppDesign.current.palette.gold
        Box(Modifier.size(size).rotate(45f).background(gold))
    }

    /**
     * The fleuron under a title: two hairlines of leaf that taper to nothing
     * either side of a lozenge, as an engraver cuts a swelled rule.
     */
    @Composable
    private fun TitleFleuron() {
        val gold = LocalAppDesign.current.palette.gold
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.width(40.dp).height(0.75.dp)
                    .background(Brush.horizontalGradient(listOf(gold.copy(alpha = 0f), gold)))
            )
            Spacer(Modifier.width(9.dp))
            Lozenge(4.5.dp)
            Spacer(Modifier.width(9.dp))
            Box(
                Modifier.width(40.dp).height(0.75.dp)
                    .background(Brush.horizontalGradient(listOf(gold, gold.copy(alpha = 0f))))
            )
        }
    }

    /**
     * Fades the ends of a horizontally scrolled strip into the page, so a run
     * of capitals that continues off the measure dissolves rather than being
     * guillotined mid-letter.
     */
    private fun Modifier.fadedEdges(scroll: ScrollState, ground: Color, fade: Dp = 32.dp): Modifier =
        drawWithContent {
            drawContent()
            // Painted in the ground colour, not masked: the vellum is laid over
            // the whole page afterwards, so the fade takes the grain with it.
            val f = fade.toPx().coerceAtMost(size.width / 3f)
            if (scroll.value > 0) {
                drawRect(
                    Brush.horizontalGradient(listOf(ground, ground.copy(alpha = 0f)), startX = 0f, endX = f),
                    size = Size(f, size.height)
                )
            }
            if (scroll.value < scroll.maxValue) {
                drawRect(
                    Brush.horizontalGradient(
                        listOf(ground.copy(alpha = 0f), ground),
                        startX = size.width - f,
                        endX = size.width
                    ),
                    topLeft = Offset(size.width - f, 0f),
                    size = Size(f, size.height)
                )
            }
        }

    /**
     * Prints whatever the host puts in the masthead — a palette glyph, a flag —
     * as a duotone in this page's inks, so a stray green icon or a full-colour
     * emoji flag reads as an engraved device rather than a sticker.
     */
    private fun Modifier.duotone(shadow: Color, light: Color): Modifier {
        val dr = light.red - shadow.red
        val dg = light.green - shadow.green
        val db = light.blue - shadow.blue
        val matrix = ColorMatrix(
            floatArrayOf(
                dr * 0.299f, dr * 0.587f, dr * 0.114f, 0f, shadow.red * 255f,
                dg * 0.299f, dg * 0.587f, dg * 0.114f, 0f, shadow.green * 255f,
                db * 0.299f, db * 0.587f, db * 0.114f, 0f, shadow.blue * 255f,
                0f, 0f, 0f, 1f, 0f
            )
        )
        val paint = Paint().apply { colorFilter = ColorFilter.colorMatrix(matrix) }
        return drawWithContent {
            drawIntoCanvas { canvas ->
                canvas.saveLayer(Rect(Offset.Zero, size), paint)
                drawContent()
                canvas.restore()
            }
        }
    }

    /**
     * The Rosary as a printer's device: a chaplet of beads closed by a
     * pendant and a Latin cross, cut sharp to match the rest of the set. The
     * stock glyph for that slot is a seated meditator, which is not a Catholic
     * sign at all.
     */
    private val RosaryGlyph: ImageVector by lazy {
        ImageVector.Builder("vatican.rosary", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.Black)) {
                fun bead(x: Float, y: Float, r: Float) {
                    moveTo(x - r, y)
                    arcTo(r, r, 0f, true, false, x + r, y)
                    arcTo(r, r, 0f, true, false, x - r, y)
                    close()
                }
                // The chaplet hangs from one side, as a rosary does when it is
                // laid down, so the device never reads as a symbol of Venus.
                val cx = 9.6f
                val cy = 8.4f
                val r = 6.2f
                val open = Math.PI / 4
                val beads = 12
                for (k in 1 until beads) {
                    val t = open + Math.PI * 2 * k / beads
                    bead(cx + r * kotlin.math.cos(t).toFloat(), cy + r * kotlin.math.sin(t).toFloat(), 1.42f)
                }
                // The centre medal, a lozenge where the loop closes.
                val mx = cx + r * kotlin.math.cos(open).toFloat()
                val my = cy + r * kotlin.math.sin(open).toFloat()
                moveTo(mx, my - 1.55f)
                lineTo(mx + 1.55f, my)
                lineTo(mx, my + 1.55f)
                lineTo(mx - 1.55f, my)
                close()
                // The pendant: two beads, then the cross.
                bead(mx + 1.8f, my + 1.8f, 1.1f)
                bead(mx + 3.15f, my + 3.15f, 1.0f)
                val x = mx + 3.9f
                val w = 0.95f
                moveTo(x - w, my + 3.9f)
                lineTo(x + w, my + 3.9f)
                lineTo(x + w, my + 5.7f)
                lineTo(x + 2.4f, my + 5.7f)
                lineTo(x + 2.4f, my + 7.3f)
                lineTo(x + w, my + 7.3f)
                lineTo(x + w, 23.6f)
                lineTo(x - w, 23.6f)
                lineTo(x - w, my + 7.3f)
                lineTo(x - 2.4f, my + 7.3f)
                lineTo(x - 2.4f, my + 5.7f)
                lineTo(x - w, my + 5.7f)
                close()
            }
        }.build()
    }

    /** Substitutes this identity's own device where the stock glyph is wrong. */
    private fun glyphFor(item: AppNavItem, selected: Boolean): ImageVector =
        if (item.tag == "nav_rosary") RosaryGlyph
        else if (selected) item.selectedIcon else item.icon

    /** The ribbon marker of a bound missal, hanging from the head of the bar. */
    @Composable
    private fun Ribbon(color: Color, modifier: Modifier = Modifier) {
        Canvas(modifier.size(width = 9.dp, height = 15.dp)) {
            val notch = 4.dp.toPx()
            val p = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(size.width / 2f, size.height - notch)
                lineTo(0f, size.height)
                close()
            }
            drawPath(p, color)
            // The weave catches the light down one edge.
            drawRect(
                Color.White.copy(alpha = 0.16f),
                topLeft = Offset(size.width * 0.18f, 0f),
                size = Size(0.8.dp.toPx(), size.height - notch * 1.2f)
            )
        }
    }

    /**
     * A line of capitals set to one line, as a compositor would: when the
     * label is longer than the measure he takes out letterspacing first, then
     * drops a size, and only breaks the line as a last resort. A button label
     * broken over two lines of widely-tracked caps is a web tell.
     */
    @Composable
    private fun FittedCapitals(label: String, base: TextStyle, color: Color) {
        val text = label.uppercase()
        val measurer = rememberTextMeasurer()
        BoxWithConstraints(contentAlignment = Alignment.Center) {
            val limit = constraints.maxWidth
            val candidates = listOf(
                base.tracked(0.15f),
                base.tracked(0.11f),
                base.tracked(0.08f),
                base.copy(fontSize = base.fontSize * 0.92f).tracked(0.08f),
                base.copy(fontSize = base.fontSize * 0.85f).tracked(0.06f)
            )
            val style = if (limit == Constraints.Infinity) candidates.first() else
                candidates.firstOrNull {
                    measurer.measure(AnnotatedString(text), it, maxLines = 1, softWrap = false)
                        .size.width <= limit
                } ?: candidates.last()
            Text(
                text = text,
                style = style,
                color = color,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }

    // ------------------------------------------------------------------ chrome

    /**
     * The running head: back control left, actions right, and the title
     * centred on the *page*, not on whatever space the controls leave over. A
     * masthead that drifts left because the right side holds two glyphs is the
     * first thing a compositor would reset.
     */
    @Composable
    private fun Masthead(
        leading: @Composable () -> Unit,
        title: @Composable () -> Unit,
        actions: @Composable () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Layout(
            contents = listOf(leading, title, actions),
            modifier = modifier
        ) { (lead, head, act), c ->
            val loose = c.copy(minWidth = 0, minHeight = 0)
            val l = lead.map { it.measure(loose) }
            val a = act.map { it.measure(loose.copy(maxWidth = c.maxWidth / 3)) }
            val side = max(l.maxOfOrNull { it.width } ?: 0, a.maxOfOrNull { it.width } ?: 0)
            val t = head.map { it.measure(loose.copy(maxWidth = (c.maxWidth - 2 * side).coerceAtLeast(0))) }
            val h = maxOf(
                c.minHeight,
                l.maxOfOrNull { it.height } ?: 0,
                a.maxOfOrNull { it.height } ?: 0,
                t.maxOfOrNull { it.height } ?: 0
            )
            layout(c.maxWidth, h) {
                l.forEach { it.place(0, (h - it.height) / 2) }
                t.forEach { it.place((c.maxWidth - it.width) / 2, (h - it.height) / 2) }
                a.forEach { it.place(c.maxWidth - it.width, (h - it.height) / 2) }
            }
        }
    }

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
        val texture = remember(design.isDark) { Vellum.texture(design.isDark) }
        val deviceShadow = if (design.isDark) lerp(palette.gold, palette.canvas, 0.62f) else palette.textSecondary
        val deviceLight = if (design.isDark) palette.gold else lerp(palette.canvas, palette.textTertiary, 0.25f)

        Box(
            Modifier
                .fillMaxSize()
                .background(palette.canvas)
                .vellum(design.isDark, texture)
        ) {
            Scaffold(
                contentWindowInsets = WindowInsets.safeDrawing,
                containerColor = Color.Transparent,
                topBar = {
                    Column(Modifier.statusBarsPadding()) {
                        Masthead(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .padding(top = 6.dp)
                                .heightIn(min = 60.dp),
                            leading = {
                                Box(Modifier.width(48.dp), contentAlignment = Alignment.Center) {
                                    if (onBack != null) {
                                        IconButton(onClick = onBack, modifier = Modifier.testTag("nav_back")) {
                                            Icon(
                                                Icons.AutoMirrored.Sharp.ArrowBack,
                                                contentDescription = backLabel,
                                                tint = palette.textSecondary,
                                                modifier = Modifier.size(22.dp)
                                            )
                                        }
                                    }
                                }
                            },
                            title = {
                                // The masthead is centred, as an ecclesial
                                // document is headed rather than left-titled.
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "✠",
                                        style = styles.sectionEyebrow.copy(
                                            fontSize = 12.sp,
                                            lineHeight = 14.sp,
                                            letterSpacing = 0.sp
                                        ),
                                        color = palette.gold
                                    )
                                    Spacer(Modifier.height(5.dp))
                                    Text(
                                        text = title.uppercase(),
                                        // Trajan capitals at masthead size want
                                        // less tracking than the eyebrows below.
                                        style = styles.screenTitle
                                            .copy(fontSize = 17.sp, lineHeight = 22.sp, fontWeight = FontWeight.Normal)
                                            .tracked(0.16f),
                                        color = bodyInk(),
                                        textAlign = TextAlign.Center,
                                        maxLines = 1
                                    )
                                }
                            },
                            actions = {
                                Row(
                                    Modifier.widthIn(min = 48.dp).duotone(deviceShadow, deviceLight),
                                    verticalAlignment = Alignment.CenterVertically,
                                    content = actions
                                )
                            }
                        )
                        Spacer(Modifier.height(10.dp))
                        // The one hairline of leaf the chrome is allowed.
                        ScotchRule(thinColor = palette.gold.copy(alpha = 0.85f))
                    }
                },
                bottomBar = {
                    FolioRail(navItems, selectedNavIndex, onNavSelected)
                }
            ) { insets ->
                content(insets)
            }
        }
    }

    /**
     * The tab bar as a book's thumb index: five words in capitals under a
     * head rule, the open one marked by a ribbon of rubric red that hangs from
     * the rule and draws slowly across to the next section when it changes.
     */
    @Composable
    private fun FolioRail(
        navItems: List<AppNavItem>,
        selectedNavIndex: Int,
        onNavSelected: (Int) -> Unit
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        Column(Modifier.navigationBarsPadding().testTag("bottom_nav_bar")) {
            Hairline(color = inkRule())
            BoxWithConstraints(Modifier.fillMaxWidth()) {
                val count = navItems.size.coerceAtLeast(1)
                val itemWidth = maxOf((maxWidth - 8.dp) / count, 64.dp)
                val ribbonX by animateDpAsState(
                    targetValue = 4.dp + itemWidth * selectedNavIndex.coerceAtLeast(0) + itemWidth / 2,
                    animationSpec = tween(design.motion.slowMillis, easing = design.motion.emphasizedEasing),
                    label = "ribbon"
                )
                Box(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
                    Row(
                        Modifier.padding(horizontal = 4.dp).padding(top = 19.dp, bottom = 9.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        navItems.forEachIndexed { index, item ->
                            val isSelected = index == selectedNavIndex
                            val ink by animateColorAsState(
                                if (isSelected) bodyInk() else palette.textTertiary,
                                tween(design.motion.mediumMillis, easing = design.motion.standardEasing),
                                label = "nav_ink"
                            )
                            Column(
                                modifier = Modifier
                                    .width(itemWidth)
                                    .press { onNavSelected(index) }
                                    .testTag(item.tag),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = glyphFor(item, isSelected),
                                    contentDescription = item.label,
                                    tint = ink,
                                    modifier = Modifier.size(19.dp)
                                )
                                Spacer(Modifier.height(7.dp))
                                Text(
                                    text = item.label.uppercase(),
                                    // 9sp caps: the smallest cut, so the
                                    // widest tracking on the scale.
                                    style = styles.navLabel.tracked(0.2f),
                                    color = ink,
                                    textAlign = TextAlign.Center,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                    if (selectedNavIndex in navItems.indices) {
                        Ribbon(rubric(), Modifier.offset(x = ribbonX - 4.5.dp))
                    }
                }
            }
        }
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
        Column(Modifier.fillMaxWidth()) {
            Masthead(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp).heightIn(min = 58.dp),
                leading = {
                    Box(Modifier.width(48.dp), contentAlignment = Alignment.Center) {
                        if (onBack != null) {
                            IconButton(onClick = onBack) {
                                Icon(
                                    Icons.AutoMirrored.Sharp.ArrowBack,
                                    contentDescription = null,
                                    tint = palette.textSecondary
                                )
                            }
                        }
                    }
                },
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = title.uppercase(),
                            style = styles.sectionTitle
                                .copy(fontSize = 17.sp, fontWeight = FontWeight.Normal)
                                .tracked(0.12f),
                            color = bodyInk(),
                            textAlign = TextAlign.Center,
                            maxLines = 2
                        )
                        if (subtitle != null) {
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = subtitle,
                                style = styles.rowSubtitle,
                                color = palette.textSecondary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                },
                actions = {
                    Row(
                        Modifier.widthIn(min = 48.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        content = actions
                    )
                }
            )
            Spacer(Modifier.height(10.dp))
            ScotchRule(thinColor = palette.gold.copy(alpha = 0.85f))
        }
    }

    @Composable
    override fun ScreenTitle(title: String, subtitle: String?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                // A title page drops well below the head rule before it speaks.
                .padding(top = design.spacing.headerTopGap + 22.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title.uppercase(),
                // Cinzel regular, not bold: the Trajan column is incised, not
                // fattened. Bold caps at display size read as a luxury logotype.
                style = styles.screenTitle
                    .copy(fontWeight = FontWeight.Normal)
                    .tracked(0.085f),
                color = bodyInk(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(18.dp))
            // Leaf, spent once: a swelled rule with a lozenge at its heart.
            TitleFleuron()
            if (subtitle != null) {
                Spacer(Modifier.height(18.dp))
                Text(
                    text = subtitle,
                    style = styles.screenSubtitle.copy(lineHeight = 24.sp),
                    color = palette.textSecondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    // --------------------------------------------------------------- structure

    @Composable
    override fun SectionHeader(title: String, subtitle: String?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .padding(top = design.spacing.sectionGap / 2, bottom = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // A missal heads its sections with a rubric: centred capitals in
            // red ink, standing free. Dashes either side of a label are a web
            // idiom ("— TITLE —"), not a printer's; the rule belongs to the
            // table that follows, not to the heading.
            Text(
                text = title.uppercase(),
                style = styles.sectionEyebrow.tracked(0.2f),
                color = rubric(),
                textAlign = TextAlign.Center
            )
            if (subtitle != null) {
                Spacer(Modifier.height(10.dp))
                Text(
                    text = subtitle,
                    style = styles.rowSubtitle.copy(fontSize = 15.sp, lineHeight = 22.sp),
                    color = palette.textSecondary,
                    textAlign = TextAlign.Center
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
        // Not a box. A printed page does not frame each paragraph and indent
        // it a second time inside the frame: a new block is opened by a rule
        // across the full measure and its text sits on the same left edge as
        // everything else on the page. Boxed panels with inner padding are
        // the single strongest tell of an app imitating print.
        Column(
            modifier
                .fillMaxWidth()
                .then(if (onClick != null) Modifier.press(onClick = onClick) else Modifier)
        ) {
            Hairline(color = inkRule().copy(alpha = inkRule().alpha * 0.6f))
            Column(
                Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 6.dp),
                content = content
            )
        }
    }

    @Composable
    override fun ListGroup(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) {
        val design = LocalAppDesign.current
        // No panel, no border box, no corner radius: a ruled folio table. The
        // head rule opens it and each row closes itself with a hairline — all
        // but the last, which is trimmed off. A printed index ends in white
        // space; leaving the last hairline in sets two parallel rules a line
        // apart wherever the next block opens with its own rule.
        val trim = with(LocalDensity.current) { 0.5.dp.roundToPx().coerceAtLeast(1) }
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
        ) {
            ScotchRule(gap = 2.dp)
            Column(
                Modifier
                    .fillMaxWidth()
                    .clipToBounds()
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        val height = (placeable.height - trim).coerceAtLeast(0)
                        layout(placeable.width, height) { placeable.place(0, 0) }
                    },
                content = content
            )
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
        // The current entry is rubricated, not highlighted: red ink is how a
        // missal marks what is being read.
        val titleColor = if (selected) palette.accent else bodyInk()
        Column(modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .then(if (onClick != null) Modifier.press(onClick = onClick) else Modifier)
                    .heightIn(min = design.spacing.rowMinHeight)
                    .padding(vertical = design.spacing.rowPaddingVertical),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // No pictogram. A printed index of books carries the name, the
                // extent and the siglum — nothing else.
                Column(Modifier.weight(1f)) {
                    Text(title, style = styles.rowTitle, color = titleColor)
                    if (subtitle != null) {
                        Spacer(Modifier.height(4.dp))
                        Text(subtitle, style = styles.rowSubtitle, color = palette.textSecondary)
                    }
                }
                if (trailingText != null) {
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = trailingText.uppercase(),
                        style = styles.navLabel.copy(fontSize = 10.sp).tracked(0.2f),
                        color = if (selected) palette.accent else palette.textTertiary
                    )
                }
            }
            Hairline()
        }
    }

    @Composable
    override fun Divider(modifier: Modifier) {
        Hairline(modifier)
    }

    @Composable
    override fun Ornament(modifier: Modifier) {
        val design = LocalAppDesign.current
        Row(
            modifier
                .fillMaxWidth()
                .padding(
                    horizontal = design.spacing.gutter,
                    vertical = design.spacing.sectionGap / 2
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f).height(0.5.dp).background(inkHair()))
            Box(Modifier.padding(horizontal = 16.dp)) { Lozenge(5.dp) }
            Box(Modifier.weight(1f).height(0.5.dp).background(inkHair()))
        }
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
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        // Rubric red, square, unbordered. At night the red is laid down as
        // oxblood — cinnabar seen by candle — with vellum lettering; mixing
        // the bright rubric toward the ground gives a muddy mauve instead.
        val fill = when {
            !enabled -> palette.hairline
            design.isDark -> Color(0xFF7C2418)
            else -> palette.accent
        }
        val ink = when {
            !enabled -> palette.textTertiary
            design.isDark -> palette.textPrimary
            else -> palette.raised
        }
        // A pressed panel sinks a shade, like a blind-stamped label.
        Box(
            modifier
                .background(fill, RectangleShape)
                .border(0.5.dp, Color.Black.copy(alpha = if (enabled) 0.18f else 0f), RectangleShape)
                .press(enabled = enabled, onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 13.dp),
            contentAlignment = Alignment.Center
        ) {
            FittedCapitals(label, styles.buttonLabel.copy(fontWeight = FontWeight.SemiBold), ink)
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
        val styles = LocalAppTextStyles.current
        Box(
            modifier
                // The whole block fades when disabled — frame and label
                // together, or the frame reads as live and the label as broken.
                .alpha(if (enabled) 1f else 0.45f)
                .border(0.75.dp, inkRule(), RectangleShape)
                .press(enabled = enabled, onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            FittedCapitals(label, styles.buttonLabel.copy(fontWeight = FontWeight.SemiBold), bodyInk())
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
        val styles = LocalAppTextStyles.current
        // Not a filled capsule: a ruled blank, the way a form is printed.
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.fillMaxWidth(),
            textStyle = styles.rowTitle.copy(color = bodyInk()),
            singleLine = true,
            cursorBrush = SolidColor(palette.accent),
            decorationBox = { inner ->
                Column {
                    Row(
                        Modifier.fillMaxWidth().padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Sharp.Search,
                            contentDescription = null,
                            tint = palette.textTertiary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Box(Modifier.weight(1f)) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = styles.rowTitle,
                                    color = palette.textTertiary
                                )
                            }
                            inner()
                        }
                    }
                    Hairline(color = inkRule())
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
        val styles = LocalAppTextStyles.current
        val density = LocalDensity.current
        val measurer = rememberTextMeasurer()
        val looseStyle = styles.navLabel.copy(fontSize = 11.sp).tracked(0.2f)
        // Where each heading sits inside the scrolled strip, so the rule can
        // travel to it and the strip can bring an off-measure heading into view.
        val lefts = remember(options.size) { mutableStateListOf(*Array(options.size) { 0f }) }
        val widths = remember(options.size) { mutableStateListOf(*Array(options.size) { 0f }) }
        val markLeft = remember { Animatable(0f) }
        val markWidth = remember { Animatable(0f) }
        val rubricInk = rubric()
        val opened = remember { booleanArrayOf(false) }
        val ground = palette.canvas

        BoxWithConstraints(modifier.fillMaxWidth()) {
            val viewport = constraints.maxWidth
            // A strip of four headings at full tracking overruns a phone's
            // measure. Close it up, as a compositor tightens a running head,
            // before letting the strip scroll a heading out of sight.
            val fitsLoose = remember(options, viewport) {
                options.sumOf {
                    measurer.measure(AnnotatedString(it.uppercase()), looseStyle, maxLines = 1).size.width +
                        with(density) { 28.dp.roundToPx() }
                } <= viewport
            }
            val tabStyle = if (fitsLoose) looseStyle else styles.navLabel.copy(fontSize = 10.5.sp).tracked(0.1f)
            val tabPad = if (fitsLoose) 14.dp else 8.dp
            val inset = with(density) { tabPad.toPx() }
            val i = selectedIndex.coerceIn(0, (options.size - 1).coerceAtLeast(0))
            // Set the strip so the chosen heading opens in the middle of the
            // measure on the very first frame: the widths are known from the
            // type before anything is laid out.
            val scroll = remember {
                val pad = with(density) { (tabPad * 2).roundToPx() }
                val spans = options.map {
                    measurer.measure(AnnotatedString(it.uppercase()), tabStyle, maxLines = 1).size.width + pad
                }
                val before = spans.take(i).sum()
                val start = (before + (spans.getOrElse(i) { 0 }) / 2 - viewport / 2).coerceAtLeast(0)
                ScrollState(if (spans.sum() > viewport) start else 0)
            }
            val measured = options.isNotEmpty() && widths.getOrElse(i) { 0f } > 0f
            LaunchedEffect(i, measured, viewport) {
                if (!measured) return@LaunchedEffect
                val left = lefts[i] + inset
                val width = widths[i] - inset * 2
                val target = (lefts[i] + widths[i] / 2f - viewport / 2f).toInt().coerceIn(0, scroll.maxValue)
                if (markWidth.value == 0f) {
                    markLeft.snapTo(left)
                    markWidth.snapTo(width)
                    if (scroll.value != target) scroll.scrollTo(target)
                } else {
                    val spec = tween<Float>(design.motion.mediumMillis, easing = design.motion.emphasizedEasing)
                    launch { scroll.animateScrollTo(target, spec) }
                    launch { markLeft.animateTo(left, spec) }
                    launch { markWidth.animateTo(width, spec) }
                }
            }
            Column(Modifier.fillMaxWidth()) {
                Box(Modifier.fillMaxWidth().fadedEdges(scroll, ground)) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .horizontalScroll(scroll)
                            .drawBehind {
                                // Until the rule has first been laid, draw it
                                // straight under the measured heading.
                                val settled = markWidth.value > 0f
                                val ml = if (settled) markLeft.value else lefts.getOrElse(i) { 0f } + inset
                                val mw = if (settled) markWidth.value else widths.getOrElse(i) { 0f } - inset * 2
                                if (mw > 0f) {
                                    val h = 1.5.dp.toPx()
                                    drawRect(
                                        rubricInk,
                                        topLeft = Offset(ml, size.height - h),
                                        size = Size(mw, h)
                                    )
                                }
                            },
                        horizontalArrangement = Arrangement.Center
                    ) {
                        options.forEachIndexed { index, option ->
                            val isSelected = index == selectedIndex
                            val ink by animateColorAsState(
                                if (isSelected) bodyInk() else palette.textTertiary,
                                tween(design.motion.mediumMillis, easing = design.motion.standardEasing),
                                label = "tab_ink"
                            )
                            Box(
                                Modifier
                                    .onPlaced {
                                        if (index < lefts.size) {
                                            val x = it.positionInParent().x
                                            val w = it.size.width.toFloat()
                                            lefts[index] = x
                                            widths[index] = w
                                            // Open on the chosen heading: bring it to
                                            // the middle of the measure before the
                                            // first frame, not one effect later.
                                            if (index == i && !opened[0] && w > 0f) {
                                                opened[0] = true
                                                val target = (x + w / 2f - viewport / 2f)
                                                    .coerceIn(0f, scroll.maxValue.toFloat())
                                                if (abs(target - scroll.value) > 1f) {
                                                    scroll.dispatchRawDelta(target - scroll.value)
                                                }
                                            }
                                        }
                                    }
                                    .press { onSelect(index) }
                                    .padding(horizontal = tabPad)
                                    .padding(top = 6.dp, bottom = 13.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = option.uppercase(),
                                    style = tabStyle,
                                    color = ink,
                                    textAlign = TextAlign.Center,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
                Hairline()
            }
        }
    }

    @Composable
    override fun Chip(label: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val spec = tween<Color>(design.motion.mediumMillis, easing = design.motion.standardEasing)
        // A ruled box from a printed form. The chosen one is rubricated and
        // takes a faint wash of the same red, as a stamped entry would.
        val edge by animateColorAsState(if (selected) palette.accent else inkHair(), spec, label = "chip_edge")
        val wash by animateColorAsState(
            if (selected) palette.accent.copy(alpha = if (design.isDark) 0.10f else 0.06f)
            else Color.Transparent,
            spec,
            label = "chip_wash"
        )
        val ink by animateColorAsState(
            if (selected) palette.accent else palette.textSecondary, spec, label = "chip_ink"
        )
        Box(
            modifier
                .background(wash, RectangleShape)
                .border(if (selected) 1.dp else 0.75.dp, edge, RectangleShape)
                .press(onClick = onClick)
                .padding(horizontal = 14.dp, vertical = 9.dp)
        ) {
            Text(
                text = label.uppercase(),
                style = styles.navLabel.copy(fontSize = 10.sp).tracked(0.2f),
                color = ink,
                maxLines = 1
            )
        }
    }

    // ----------------------------------------------------------------- content

    /** The scripture column: one leading, one measure, one grid. */
    @Composable
    private fun scriptureStyle(): TextStyle {
        val styles = LocalAppTextStyles.current
        return styles.scripture.copy(
            lineHeight = LeadingSp.sp,
            letterSpacing = 0.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Proportional,
                trim = LineHeightStyle.Trim.None
            )
        )
    }

    /** The raised figure a Bible sets before a verse, and the apparatus mark. */
    @Composable
    private fun figureSpan(color: Color): SpanStyle {
        val styles = LocalAppTextStyles.current
        return styles.verseNumber.toSpanStyle().copy(
            color = color,
            fontSize = 10.sp,
            letterSpacing = 0.4.sp,
            baselineShift = BaselineShift(0.42f)
        )
    }

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
        val density = LocalDensity.current
        val body = scriptureStyle()
        val ink = bodyInk()
        val mark = figureSpan(palette.textTertiary).copy(baselineShift = BaselineShift(0.38f))

        val base = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.press(onClick = onClick) else Modifier)
            .padding(horizontal = design.spacing.gutter)

        if (!isFirstOfChapter) {
            // Verses are set solid: the figure is raised inline so every line in
            // the column — and every verse — keeps the same 30sp baseline grid,
            // and every verse starts on the same left edge.
            Text(
                text = buildAnnotatedString {
                    withStyle(figureSpan(palette.accent)) { append(number.toString()) }
                    append(" ")
                    append(text.trim())
                    if (hasCommentary) withStyle(mark) { append(" †") }
                },
                style = body,
                color = ink,
                modifier = base
            )
            return
        }

        // ---- the illuminated initial -------------------------------------
        //
        // The versal is cut into the text block, not stood beside it in a box.
        // Its cap line meets the first line's cap line and its baseline rests on
        // the second line's baseline; the remainder of the opening word runs on
        // from the versal, and the paragraph closes up to full measure below it.
        val trimmed = text.trimStart()
        val initial = trimmed.take(1).uppercase()
        val rest = trimmed.drop(1)

        val capStyle = styles.scriptureDropCap.copy(
            fontSize = DropCapSizeSp.sp,
            lineHeight = (DropCapSizeSp * 1.35f).sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight.Bold,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Top,
                trim = LineHeightStyle.Trim.None
            )
        )

        val measurer = rememberTextMeasurer()
        val capBlockHeight = with(density) { (LeadingSp * DropCapLines).sp.toDp() }
        // Versal baseline (second line) minus Cinzel's ascent at this size.
        val capBaselineOffset = with(density) {
            (FirstBaselineSp + LeadingSp * (DropCapLines - 1) - DropCapAscentSp).sp.toDp()
        }

        BoxWithConstraints(base) {
            val capInk = measurer.measure(text = AnnotatedString(initial), style = capStyle)
            // A narrow versal such as I still needs a proper indent, so the
            // block never falls below two ems of the text face.
            val capWidth = maxOf(
                with(density) { capInk.size.width.toDp() } + 2.dp,
                36.dp
            )
            val gap = 12.dp
            val measure = maxWidth - capWidth - gap
            val measurePx = with(density) { measure.roundToPx() }

            val layout = measurer.measure(
                text = AnnotatedString(rest),
                style = body,
                constraints = Constraints(maxWidth = measurePx)
            )
            val split = if (layout.lineCount > DropCapLines) {
                layout.getLineEnd(DropCapLines - 1, visibleEnd = true)
            } else {
                rest.length
            }
            val head = rest.substring(0, split)
            val tail = rest.substring(split).trimStart()

            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth()) {
                    Box(Modifier.width(capWidth).height(capBlockHeight)) {
                        Text(
                            text = initial,
                            style = capStyle,
                            color = palette.gold,
                            maxLines = 1,
                            modifier = Modifier.offset(y = capBaselineOffset)
                        )
                    }
                    Spacer(Modifier.width(gap))
                    Text(
                        text = buildAnnotatedString {
                            append(head)
                            if (tail.isEmpty() && hasCommentary) withStyle(mark) { append(" †") }
                        },
                        style = body,
                        color = ink,
                        modifier = Modifier.width(measure)
                    )
                }
                if (tail.isNotEmpty()) {
                    Text(
                        text = buildAnnotatedString {
                            append(tail)
                            if (hasCommentary) withStyle(mark) { append(" †") }
                        },
                        style = body,
                        color = ink,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

    @Composable
    override fun MetricTile(value: String, caption: String, icon: ImageVector?, modifier: Modifier) {
        val palette = LocalAppDesign.current.palette
        val styles = LocalAppTextStyles.current
        // Three ruled columns of a printed table, not three floating cards: no
        // fill, no border box, no pictogram, no gilt rule under the figure.
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            ScotchRule(gap = 2.dp)
            Spacer(Modifier.height(16.dp))
            Text(
                text = value,
                // Lining figures are already evenly set; tracking them apart is
                // what makes a number look like a logotype.
                style = styles.metric.copy(letterSpacing = 0.sp, fontWeight = FontWeight.Normal),
                color = bodyInk(),
                maxLines = 1,
                softWrap = false
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = caption.uppercase(),
                style = styles.sectionEyebrow.copy(lineHeight = 16.sp).tracked(0.22f),
                color = palette.textSecondary,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    override fun EmptyState(title: String, body: String?, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        Column(
            modifier.fillMaxWidth().padding(design.spacing.gutter * 1.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Lozenge(8.dp)
            Spacer(Modifier.height(22.dp))
            Text(
                text = title.uppercase(),
                style = styles.sectionTitle
                    .copy(fontSize = 15.sp, fontWeight = FontWeight.Normal)
                    .tracked(0.13f),
                color = bodyInk(),
                textAlign = TextAlign.Center
            )
            if (body != null) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = body,
                    style = styles.rowSubtitle.copy(fontSize = 15.sp, lineHeight = 23.sp),
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
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val accent = pigment(accentColorHex?.let { Color(it) } ?: palette.accent)
        // A marginal note in the liturgical colour: one rule of pigment in the
        // margin, left-ranged. Not a tinted alert with a rounded border.
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .then(if (onClick != null) Modifier.press(onClick = onClick) else Modifier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.width(2.dp).heightIn(min = 52.dp).background(accent))
            Column(Modifier.padding(start = 16.dp, top = 6.dp, bottom = 6.dp)) {
                Text(
                    text = title.uppercase(),
                    style = styles.sectionEyebrow.copy(lineHeight = 16.sp).tracked(0.22f),
                    color = accent
                )
                if (body != null) {
                    Spacer(Modifier.height(9.dp))
                    Text(body, style = styles.rowTitle, color = bodyInk())
                }
            }
        }
    }
}
