package com.example.ui.design

import androidx.compose.ui.composed

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import android.graphics.Shader
import android.os.Build
import android.view.SoundEffectConstants
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.AppDesign
import com.example.ui.theme.LocalAppDesign

// -----------------------------------------------------------------------------
// Liquid Glass — the material every Lumen surface is made of.
//
// Three layers, from the back:
//   1. Ambient light  — a still field of stained-glass light behind everything.
//   2. Content glass  — dense, calm panes that hold reading text.
//   3. Control glass  — clear floating glass (dock, bars, buttons, chips) that
//      blurs and refracts what scrolls beneath it where the device can.
//
// Every pane shares one construction: a soft coloured lift shadow, a tinted
// fill, a sheen across the upper half and a specular rim that is brightest
// where the light enters (top-left) and returns faintly where it leaves
// (bottom-right). Reduce-transparency, high contrast and Sanctuary get the
// same geometry in opaque material.
// -----------------------------------------------------------------------------

/** How a pane of glass is built. Resolved from the palette by [glassSpec]. */
@Immutable
data class GlassSpec(
    val fillTop: Color,
    val fillBottom: Color,
    /** Peak alpha of the white sheen across the upper part of the pane. */
    val sheen: Float,
    val rimLight: Color,
    val rimShade: Color,
    val rimWidth: Dp,
    val shadow: Color,
    val shadowBlur: Dp,
    val shadowY: Dp,
    /**
     * How strongly the pane bends the ambient light behind it (0 = not at
     * all): the light is redrawn magnified inside the pane and more sharply
     * along its rim, as a thick slab of clear glass would show it.
     */
    val lens: Float = 0f,
    /** Peak alpha of the specular glint in the pane's lit corner. */
    val glint: Float = 0f
)

/** The roles glass plays. One spec per role keeps every screen consistent. */
enum class GlassRole {
    /** Cards, groups, tiles: dense enough for body text. */
    Content,
    /** Fields, chips, segmented tracks: clearer, sits on the canvas. */
    Control,
    /** Dock, bars, floating buttons: densest unless a live backdrop is behind it. */
    Floating,
    /** The bright thumb that slides inside a control (segmented, dock, switch). */
    Thumb
}

internal fun AppDesign.opaqueMaterial(): Boolean = isCatholic1 || isSanctuary || isClassic || reduceTransparency || highContrast

/** The spec for [role] in the current identity and mode. */
fun AppDesign.glassSpec(role: GlassRole, lifted: Boolean = true): GlassSpec {
    val p = palette
    val opaque = opaqueMaterial()
    if (opaque) {
        val fill = when (role) {
            GlassRole.Content -> p.groupedSurface
            GlassRole.Thumb -> if (isSanctuary) p.raised else if (isCatholic1) p.brand else p.action
            else -> p.raised
        }
        return GlassSpec(fill, fill, 0f, p.hairline, p.hairline,
            if (highContrast) 1.5.dp else 0.75.dp,
            when {
                !lifted || isSanctuary || highContrast -> Color.Transparent
                // Catholic1: soft navy shadow by day, none at night (hairline only), CATHOLIC1.md P3.
                isCatholic1 -> if (isDark) Color.Transparent else p.shadow
                else -> p.shadow.copy(alpha = if (isDark) 0.22f else 0.07f)
            },
            if (isCatholic1) 16.dp else 20.dp, if (isCatholic1) 4.dp else 5.dp)
    }
    if (isGlass) return clearGlassSpec(role, lifted)
    return when (role) {
        GlassRole.Content -> GlassSpec(
            fillTop = p.pane.copy(alpha = (p.pane.alpha + 0.04f).coerceAtMost(1f)),
            fillBottom = p.pane,
            sheen = if (isDark) 0.045f else 0.30f,
            rimLight = p.glassEdgeTop,
            rimShade = p.glassEdgeBottom,
            rimWidth = 1.dp,
            shadow = if (lifted) p.shadow.copy(alpha = if (isDark) 0.30f else 0.085f) else Color.Transparent,
            shadowBlur = 28.dp, shadowY = 10.dp
        )
        GlassRole.Control -> GlassSpec(
            fillTop = p.glass.copy(alpha = (p.glass.alpha + 0.06f).coerceAtMost(1f)),
            fillBottom = p.glass,
            sheen = if (isDark) 0.05f else 0.34f,
            rimLight = p.glassEdgeTop,
            rimShade = p.glassEdgeBottom,
            rimWidth = 1.dp,
            shadow = if (lifted) p.shadow.copy(alpha = if (isDark) 0.26f else 0.075f) else Color.Transparent,
            shadowBlur = 18.dp, shadowY = 6.dp
        )
        GlassRole.Floating -> GlassSpec(
            fillTop = p.glassStrong.copy(alpha = (p.glassStrong.alpha + 0.04f).coerceAtMost(1f)),
            fillBottom = p.glassStrong,
            sheen = if (isDark) 0.07f else 0.42f,
            rimLight = p.glassEdgeTop,
            rimShade = p.glassEdgeBottom,
            rimWidth = 1.dp,
            shadow = if (lifted) p.shadow.copy(alpha = if (isDark) 0.42f else 0.13f) else Color.Transparent,
            shadowBlur = 30.dp, shadowY = 10.dp
        )
        GlassRole.Thumb -> GlassSpec(
            fillTop = if (isDark) Color.White.copy(alpha = 0.20f) else Color.White,
            fillBottom = if (isDark) Color.White.copy(alpha = 0.13f) else Color.White.copy(alpha = 0.94f),
            sheen = if (isDark) 0.10f else 0f,
            rimLight = if (isDark) Color.White.copy(alpha = 0.42f) else Color.White,
            rimShade = if (isDark) Color.White.copy(alpha = 0.10f) else Color.White.copy(alpha = 0.7f),
            rimWidth = 1.dp,
            shadow = p.shadow.copy(alpha = if (isDark) 0.34f else 0.16f),
            shadowBlur = 12.dp, shadowY = 3.dp
        )
    }
}

/**
 * The Glass identity's panes: a thin tint, the light behind bent through
 * them, a bright rim and a glint. Nothing here is dense enough to hide what
 * is behind it; legibility comes from the ambient light's own range.
 */
private fun AppDesign.clearGlassSpec(role: GlassRole, lifted: Boolean): GlassSpec {
    val p = palette
    val rimLight = p.glassEdgeTop
    val rimShade = p.glassEdgeBottom.copy(alpha = p.glassEdgeBottom.alpha * 0.35f)
    fun shadow(light: Float, dark: Float) = if (lifted) p.shadow.copy(alpha = if (isDark) dark else light) else Color.Transparent
    return when (role) {
        GlassRole.Content -> GlassSpec(
            fillTop = p.pane.copy(alpha = p.pane.alpha * 1.25f), fillBottom = p.pane.copy(alpha = p.pane.alpha * 0.8f),
            sheen = 0f, rimLight = rimLight, rimShade = rimShade, rimWidth = 1.25.dp,
            shadow = shadow(0.10f, 0.34f), shadowBlur = 30.dp, shadowY = 12.dp,
            lens = 1f, glint = if (isDark) 0.16f else 0.50f
        )
        GlassRole.Control -> GlassSpec(
            fillTop = p.glass.copy(alpha = p.glass.alpha * 1.3f), fillBottom = p.glass.copy(alpha = p.glass.alpha * 0.8f),
            sheen = 0f, rimLight = rimLight, rimShade = rimShade, rimWidth = 1.25.dp,
            shadow = shadow(0.09f, 0.30f), shadowBlur = 18.dp, shadowY = 6.dp,
            lens = 1f, glint = if (isDark) 0.14f else 0.45f
        )
        // Floats over scrolling text inside the content, where nothing can be
        // sampled: frosted enough to keep its own labels readable.
        GlassRole.Floating -> GlassSpec(
            fillTop = p.glassStrong.copy(alpha = (p.glassStrong.alpha + 0.05f).coerceAtMost(1f)), fillBottom = p.glassStrong,
            sheen = 0f, rimLight = rimLight, rimShade = rimShade, rimWidth = 1.25.dp,
            shadow = shadow(0.14f, 0.42f), shadowBlur = 30.dp, shadowY = 10.dp,
            lens = 0.7f, glint = if (isDark) 0.14f else 0.40f
        )
        GlassRole.Thumb -> GlassSpec(
            fillTop = Color.White.copy(alpha = if (isDark) 0.13f else 0.62f),
            fillBottom = Color.White.copy(alpha = if (isDark) 0.07f else 0.40f),
            sheen = 0f, rimLight = Color.White.copy(alpha = if (isDark) 0.55f else 1f),
            rimShade = Color.White.copy(alpha = if (isDark) 0.10f else 0.45f), rimWidth = 1.25.dp,
            shadow = p.shadow.copy(alpha = if (isDark) 0.36f else 0.16f), shadowBlur = 14.dp, shadowY = 4.dp,
            lens = 1.4f, glint = if (isDark) 0.20f else 0.55f
        )
    }
}

/**
 * Dock and bar glass. Over a [live] blurred backdrop the tint thins so the
 * content shows through; without one it is the dense floating glass.
 */
fun AppDesign.chromeGlassSpec(live: Boolean): GlassSpec {
    if (isGlass && !opaqueMaterial()) {
        // Clear either way: live, the content blurs and bends beneath it;
        // otherwise the content has already dissolved into the light there.
        val tint = if (isDark) Color.White.copy(alpha = if (live) 0.06f else 0.08f)
            else Color.White.copy(alpha = if (live) 0.14f else 0.22f)
        return clearGlassSpec(GlassRole.Control, lifted = true).copy(
            fillTop = tint.copy(alpha = tint.alpha * 1.3f), fillBottom = tint,
            shadow = palette.shadow.copy(alpha = if (isDark) 0.44f else 0.15f), shadowBlur = 30.dp, shadowY = 10.dp,
            // Live, the real backdrop is already blurred and bent beneath the
            // tint; redrawing the ambient light over it would hide it.
            lens = if (live) 0f else 1.2f
        )
    }
    val base = glassSpec(GlassRole.Floating)
    if (!live || opaqueMaterial()) return base
    val tint = if (isDark) Color(0xFF161D33) else Color.White
    return base.copy(
        fillTop = tint.copy(alpha = if (isDark) 0.56f else 0.62f),
        fillBottom = tint.copy(alpha = if (isDark) 0.50f else 0.54f)
    )
}

/** Accent-tinted glass: primary actions and selected chips. */
fun AppDesign.tintedGlassSpec(tint: Color, enabled: Boolean = true, glow: Boolean = true): GlassSpec {
    if (opaqueMaterial()) {
        val fill = if (enabled) tint else tint.copy(alpha = 0.3f)
        return GlassSpec(fill, fill, 0f, Color.Transparent, Color.Transparent, 0.dp,
            if (enabled && !isDark && !isSanctuary && !highContrast) palette.shadow else Color.Transparent, 24.dp, 8.dp)
    }
    val a = if (enabled) 1f else 0.36f
    return GlassSpec(
        fillTop = lerp(tint, Color.White, if (isDark) 0.16f else 0.14f).copy(alpha = a),
        fillBottom = lerp(tint, Color.Black, if (isDark) 0.14f else 0.10f).copy(alpha = a),
        sheen = if (enabled) (if (isDark) 0.22f else 0.20f) else 0f,
        rimLight = Color.White.copy(alpha = if (enabled) 0.62f else 0.2f),
        rimShade = Color.White.copy(alpha = if (enabled) 0.20f else 0.08f),
        rimWidth = 1.dp,
        shadow = if (enabled && glow) tint.copy(alpha = if (isDark) 0.30f else 0.34f) else Color.Transparent,
        shadowBlur = 22.dp, shadowY = 8.dp
    )
}

private fun Path.addOutline(outline: Outline) = when (outline) {
    is Outline.Rectangle -> addRect(outline.rect)
    is Outline.Rounded -> addRoundRect(outline.roundRect)
    is Outline.Generic -> addPath(outline.path)
}

/**
 * Draws one pane of glass behind the content: lift shadow, fill, sheen and
 * specular rim. Does not clip; follow it with `clip(shape)` where children
 * could overflow.
 */
fun Modifier.liquidPane(shape: Shape, spec: GlassSpec): Modifier = this
    .then(if (spec.shadow.alpha > 0f) Modifier.softShadow(shape, spec.shadow, spec.shadowBlur, spec.shadowY) else Modifier)
    .then(if (spec.lens > 0f) Modifier.ambientLens(shape, spec.lens) else Modifier)
    .drawWithCache {
        val body = Path().apply { addOutline(shape.createOutline(size, layoutDirection, this@drawWithCache)) }
        val fill = if (spec.fillTop == spec.fillBottom) null
        else Brush.verticalGradient(listOf(spec.fillTop, spec.fillBottom))
        val sheenDepth = minOf(size.height * 0.62f, 120.dp.toPx())
        val sheen = if (spec.sheen > 0f) Brush.verticalGradient(
            0f to Color.White.copy(alpha = spec.sheen),
            0.55f to Color.White.copy(alpha = spec.sheen * 0.22f),
            1f to Color.Transparent,
            startY = 0f, endY = sheenDepth
        ) else null
        val stroke = spec.rimWidth.toPx()
        val rimPath = if (stroke > 0f && size.width > stroke && size.height > stroke) Path().apply {
            addOutline(shape.createOutline(Size(size.width - stroke, size.height - stroke), layoutDirection, this@drawWithCache))
            translate(Offset(stroke / 2f, stroke / 2f))
        } else null
        // Light enters top-left, travels round the edge and returns bottom-right.
        val rim = if (spec.rimLight == spec.rimShade) null else Brush.linearGradient(
            0f to spec.rimLight,
            0.34f to lerp(spec.rimLight, spec.rimShade, 0.75f),
            0.66f to spec.rimShade,
            1f to lerp(spec.rimShade, spec.rimLight, 0.55f),
            start = Offset(size.width * 0.12f, 0f),
            end = Offset(size.width * 0.88f, size.height)
        )
        // The glint: light pooling in the corner it enters by, and a faint
        // shade along the far edge that gives the slab its thickness.
        val glint = if (spec.glint > 0f) Brush.radialGradient(
            0f to Color.White.copy(alpha = spec.glint),
            0.45f to Color.White.copy(alpha = spec.glint * 0.28f),
            1f to Color.Transparent,
            center = Offset(size.width * 0.10f, 0f),
            radius = maxOf(minOf(size.width, size.height) * 1.15f, 1f)
        ) else null
        val shade = if (spec.glint > 0f) Brush.verticalGradient(
            0f to Color.Transparent, 0.72f to Color.Transparent, 1f to Color.Black.copy(alpha = 0.045f)
        ) else null
        onDrawBehind {
            if (fill != null) drawPath(body, fill) else drawPath(body, spec.fillTop)
            if (sheen != null) drawPath(body, sheen)
            if (glint != null) drawPath(body, glint)
            if (shade != null) drawPath(body, shade)
            if (rimPath != null) {
                if (rim != null) drawPath(rimPath, rim, style = Stroke(stroke))
                else if (spec.rimLight.alpha > 0f) drawPath(rimPath, spec.rimLight, style = Stroke(stroke))
            }
        }
    }

// ------------------------------------------------------------------ ambient

/**
 * The still field of light behind every screen: the canvas gradient with four
 * soft pools of colour, like daylight through stained glass on stone. Static
 * by design: glass above it is what moves.
 */
fun Modifier.liquidAmbient(): Modifier = composed {
    val design = LocalAppDesign.current
    val p = design.palette
    val pools = p.ambient
    if (pools.size < 4 || design.isSanctuary || design.highContrast) {
        return@composed background(Brush.verticalGradient(listOf(p.canvasTop, p.canvasBottom)))
    }
    drawWithCache {
        val base = Brush.verticalGradient(listOf(p.canvasTop, p.canvasBottom))
        val brushes = ambientPools(pools, size.width, size.height, 1f)
        onDrawBehind {
            drawRect(base)
            brushes.forEach { drawRect(it) }
        }
    }
}

/** The four pools of ambient light for a [w] x [h] canvas, each alpha scaled by [gain]. */
private fun ambientPools(pools: List<Color>, w: Float, h: Float, gain: Float): List<Brush> {
    val unit = maxOf(w, h * 0.5f).coerceAtLeast(1f)
    fun pool(color: Color, cx: Float, cy: Float, radius: Float): Brush {
        val a = (color.alpha * gain).coerceAtMost(1f)
        return Brush.radialGradient(
            0f to color.copy(alpha = a),
            0.32f to color.copy(alpha = a * 0.70f),
            0.62f to color.copy(alpha = a * 0.26f),
            1f to color.copy(alpha = 0f),
            center = Offset(w * cx, h * cy), radius = unit * radius
        )
    }
    return listOf(
        pool(pools[0], 0.02f, 0.02f, 0.98f),
        pool(pools[1], 1.04f, 0.20f, 0.86f),
        pool(pools[2], -0.08f, 0.60f, 0.90f),
        pool(pools[3], 1.00f, 0.90f, 0.92f)
    )
}

/** How much brighter the ambient light reads through a lens of strength 1. */
const val LensGain = 1.25f

/**
 * Refraction without a shader: the ambient light behind the pane is redrawn
 * inside it magnified about the pane's centre, and again, more strongly,
 * along a rim band. As the pane moves across the light the image inside it
 * slides, the way it does through real glass. Works on every Android version
 * and on a software canvas.
 */
private fun Modifier.ambientLens(shape: Shape, strength: Float): Modifier = composed {
    val pools = LocalAppDesign.current.palette.ambient
    if (pools.size < 4) return@composed this
    var origin by remember { mutableStateOf(Offset.Zero) }
    var root by remember { mutableStateOf(androidx.compose.ui.unit.IntSize.Zero) }
    this
        .onGloballyPositioned { c ->
            origin = c.positionInRoot()
            root = c.findRootCoordinates().size
        }
        .drawWithCache {
            val body = Path().apply { addOutline(shape.createOutline(size, layoutDirection, this@drawWithCache)) }
            val band = 10.dp.toPx()
            val ring = if (size.width > band * 3f && size.height > band * 3f) {
                val inner = Path().apply {
                    addOutline(shape.createOutline(Size(size.width - band * 2f, size.height - band * 2f), layoutDirection, this@drawWithCache))
                    translate(Offset(band, band))
                }
                Path().apply { op(body, inner, androidx.compose.ui.graphics.PathOperation.Difference) }
            } else null
            val rootSize = Size(root.width.toFloat(), root.height.toFloat())
            val brushes = if (root.width > 0) ambientPools(pools, rootSize.width, rootSize.height, 1f + (LensGain - 1f) * strength.coerceAtMost(1f)) else emptyList()
            onDrawBehind {
                if (brushes.isEmpty()) return@onDrawBehind
                val o = origin
                val pivot = Offset(o.x + size.width / 2f, o.y + size.height / 2f)
                fun light(zoom: Float) = withTransform({
                    translate(-o.x, -o.y)
                    scale(zoom, zoom, pivot)
                }) { brushes.forEach { drawRect(it, size = rootSize) } }
                clipPath(body) { light(1f + 0.12f * strength) }
                if (ring != null) clipPath(ring) { light(1f + 0.38f * strength) }
            }
        }
}

/**
 * Dissolves the lower part of its content into whatever is behind it (the
 * ambient light), from [start] (fraction of height) to fully clear at the
 * bottom. Unlike painting the canvas colour over art, this never leaves a
 * band where the art meets the light.
 */
fun Modifier.fadeOutBottom(start: Float = 0.5f): Modifier = alphaMask {
    val span = 1f - start
    Brush.verticalGradient(
        0f to Color.Black,
        start to Color.Black,
        (start + span * 0.34f) to Color.Black.copy(alpha = 0.62f),
        (start + span * 0.66f) to Color.Black.copy(alpha = 0.24f),
        1f to Color.Transparent
    )
}

/** Softens the left and right edges of a picture that does not reach the window edges (a centred column on a wide window). */
fun Modifier.fadeSides(edge: androidx.compose.ui.unit.Dp = 32.dp): Modifier = alphaMask {
    val f = (edge.toPx() / size.width).coerceIn(0f, 0.5f)
    Brush.horizontalGradient(
        0f to Color.Transparent,
        f * 0.5f to Color.Black.copy(alpha = 0.35f),
        f to Color.Black,
        1f - f to Color.Black,
        1f - f * 0.5f to Color.Black.copy(alpha = 0.35f),
        1f to Color.Transparent
    )
}

/**
 * Multiplies this node's content by the alpha of [mask]. Uses an explicit
 * layer so it holds on every canvas, hardware or software.
 */
fun Modifier.alphaMask(mask: androidx.compose.ui.graphics.drawscope.DrawScope.() -> Brush): Modifier = drawWithContent {
    val paint = androidx.compose.ui.graphics.Paint()
    drawContext.canvas.saveLayer(androidx.compose.ui.geometry.Rect(Offset.Zero, size), paint)
    drawContent()
    drawRect(mask(), blendMode = BlendMode.DstIn)
    drawContext.canvas.restore()
}

// ------------------------------------------------------------ live backdrop

/** True where this device can blur a live backdrop (Android 12+, real GPU canvas). */
internal val liquidBlurAvailable: Boolean by lazy {
    Build.VERSION.SDK_INT >= 31 && !"robolectric".equals(Build.FINGERPRINT, ignoreCase = true)
}

/**
 * The scrolling content of a screen, recorded so floating glass can show it
 * blurred and refracted. Null wherever live blur is unavailable or turned off;
 * glass then falls back to its denser fill.
 */
@Stable
class LiquidBackdrop internal constructor(internal val layer: GraphicsLayer) {
    internal var origin by mutableStateOf(Offset.Zero)
}

val LocalLiquidBackdrop = staticCompositionLocalOf<LiquidBackdrop?> { null }

@Composable
fun rememberLiquidBackdrop(): LiquidBackdrop? {
    val design = LocalAppDesign.current
    if (!liquidBlurAvailable || design.opaqueMaterial()) return null
    val layer = rememberGraphicsLayer()
    return remember(layer) { LiquidBackdrop(layer) }
}

/** Records this node's content as the [backdrop] floating glass samples. */
fun Modifier.liquidBackdropSource(backdrop: LiquidBackdrop?): Modifier =
    if (backdrop == null) this else this
        .onGloballyPositioned { backdrop.origin = it.positionInRoot() }
        .drawWithContent {
            backdrop.layer.record { this@drawWithContent.drawContent() }
            drawLayer(backdrop.layer)
        }

/**
 * The live backdrop seen through a pane: blurred, and on Android 13+ bent at
 * the rim like thick glass. Fill its parent with it, behind the pane's tint.
 * Draws nothing when no backdrop is available. [cornerRadius] null = capsule.
 */
@Composable
fun LiquidBackdropFill(
    shape: Shape,
    modifier: Modifier = Modifier,
    blur: Dp = 20.dp,
    cornerRadius: Dp? = null,
    lens: Boolean = true
) {
    val backdrop = LocalLiquidBackdrop.current ?: return
    if (Build.VERSION.SDK_INT < 31) return
    var position by remember { mutableStateOf(Offset.Zero) }
    val lensShader = remember(lens) { if (lens && Build.VERSION.SDK_INT >= 33) LiquidLens.create() else null }
    // The rounded clip sits outside the effect so the blur never softens the edge.
    Box(modifier.clip(shape)) {
        Box(
            Modifier
                .fillMaxSize()
                .onGloballyPositioned { position = it.positionInRoot() - backdrop.origin }
                .graphicsLayer {
                    val radiusPx = blur.toPx()
                    renderEffect = runCatching {
                        val blurEffect = RenderEffect.createBlurEffect(radiusPx, radiusPx, Shader.TileMode.CLAMP)
                        val effect = if (lensShader != null && Build.VERSION.SDK_INT >= 33) {
                            val corner = cornerRadius?.toPx() ?: (minOf(size.width, size.height) / 2f)
                            LiquidLens.effect(lensShader, size.width, size.height, corner, density, blurEffect)
                        } else blurEffect
                        effect.asComposeRenderEffect()
                    }.getOrNull()
                }
                .drawBehind {
                    translate(-position.x, -position.y) { drawLayer(backdrop.layer) }
                }
        )
    }
}

/** Rounded-rectangle lens: bends the backdrop inward along the rim, with a hint of dispersion. */
internal object LiquidLens {
    const val SOURCE = """
        uniform shader content;
        uniform float2 size;
        uniform float corner;
        uniform float rim;
        uniform float bend;
        uniform float chroma;
        half4 main(float2 p) {
            float2 c = size * 0.5;
            float2 q = p - c;
            float2 d = abs(q) - (c - float2(corner));
            float2 o = max(d, float2(0.0));
            float dist = length(o) + min(max(d.x, d.y), 0.0) - corner;
            float t = 1.0 - clamp(-dist / rim, 0.0, 1.0);
            float k = t * t * t;
            float2 n = length(o) > 0.0001 ? normalize(o) : (d.x > d.y ? float2(1.0, 0.0) : float2(0.0, 1.0));
            n *= sign(q);
            float2 shift = n * bend * k;
            half4 base = content.eval(p - shift);
            half r = content.eval(p - shift - n * chroma * k).r;
            half b = content.eval(p - shift + n * chroma * k).b;
            return half4(r, base.g, b, base.a);
        }
    """

    fun create(): Any? = if (Build.VERSION.SDK_INT >= 33) runCatching { Api33.shader() }.getOrNull() else null

    @RequiresApi(33)
    fun effect(shader: Any, width: Float, height: Float, corner: Float, density: Float, inner: RenderEffect): RenderEffect =
        Api33.effect(shader as RuntimeShader, width, height, corner, density, inner)

    @RequiresApi(33)
    private object Api33 {
        // Android's AGSL spells child sampling `child.eval(p)`; some Skia builds keep `sample(child, p)`.
        fun shader(): RuntimeShader = runCatching { RuntimeShader(SOURCE) }
            .recoverCatching { RuntimeShader(SOURCE.replace("content.eval(", "sample(content, ")) }
            .getOrThrow()
        fun effect(shader: RuntimeShader, width: Float, height: Float, corner: Float, density: Float, inner: RenderEffect): RenderEffect {
            shader.setFloatUniform("size", width, height)
            shader.setFloatUniform("corner", corner.coerceAtMost(minOf(width, height) / 2f))
            shader.setFloatUniform("rim", minOf(18f * density, minOf(width, height) / 2f))
            shader.setFloatUniform("bend", 14f * density)
            shader.setFloatUniform("chroma", 1.1f * density)
            return RenderEffect.createChainEffect(RenderEffect.createRuntimeShaderEffect(shader, "content"), inner)
        }
    }
}

// ----------------------------------------------------------------- feedback

/** The quiet system tick and click a glass control answers a touch with. */
@Stable
class GlassFeedback internal constructor(private val view: android.view.View?) {
    /** A press that commits an action. Honours the system's touch-sound setting. */
    fun click() {
        view?.playSoundEffect(SoundEffectConstants.CLICK)
    }

    /** A selection that moved: tabs, segments, switches, checks. */
    fun tick() {
        val v = view ?: return
        v.playSoundEffect(SoundEffectConstants.CLICK)
        runCatching {
            v.performHapticFeedback(
                if (Build.VERSION.SDK_INT >= 34) android.view.HapticFeedbackConstants.SEGMENT_TICK
                else android.view.HapticFeedbackConstants.CLOCK_TICK
            )
        }
    }
}

@Composable
fun rememberGlassFeedback(): GlassFeedback {
    val view = LocalView.current
    return remember(view) { GlassFeedback(view) }
}

/** Whether this runtime would attempt a live blurred backdrop. */
internal fun liquidBlurSupportedForTests(): Boolean = liquidBlurAvailable

/** Softens only the trailing edge of a cover panel into the shared ambient canvas. */
fun Modifier.fadeEnd(edge: androidx.compose.ui.unit.Dp = 24.dp): Modifier = composed {
    val direction = androidx.compose.ui.platform.LocalLayoutDirection.current
    alphaMask {
        val span = (edge.toPx() / size.width.coerceAtLeast(1f)).coerceIn(0f, 1f)
        if (direction == androidx.compose.ui.unit.LayoutDirection.Ltr)
            Brush.horizontalGradient(0f to Color.Black, (1f - span) to Color.Black, 1f to Color.Transparent)
        else Brush.horizontalGradient(0f to Color.Transparent, span to Color.Black, 1f to Color.Black)
    }
}
