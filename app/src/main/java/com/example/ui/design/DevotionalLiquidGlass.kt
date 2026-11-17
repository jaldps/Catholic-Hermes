package com.example.ui.design

import android.graphics.BitmapShader
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RuntimeShader
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalAppDesign

/** Samples only the sacred illustration, never text, other controls or the screen itself. */
internal data class DevotionalBackdrop(
    val image: ImageBitmap?,
    val softImage: ImageBitmap?,
    val frame: Rect,
    val focusX: Float,
    val focusY: Float,
    val saturation: Float,
    val gain: Float
)

/** Glass material shared by the two existing visual identities, with an opaque accessibility mode. */
@Composable
internal fun LiquidGlassDevotionTrack(
    selected: Int,
    pressed: Boolean,
    darkArt: Boolean,
    backdrop: DevotionalBackdrop,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val still = design.reduceMotion || LocalInspectionMode.current
    val position by animateFloatAsState(
        selected.coerceIn(0, 1).toFloat(),
        if (still) snap() else spring(dampingRatio = 0.84f, stiffness = 420f),
        label = "devotionGlassPosition"
    )
    val pressure by animateFloatAsState(if (pressed) 1f else 0f,
        if (still) snap() else spring(dampingRatio = 0.9f, stiffness = 600f),
        label = "devotionGlassPressure")
    var origin by remember { mutableStateOf(Offset.Zero) }
    val renderer = remember { DevotionalGlassRenderer() }
    val opaque = design.isClassic || design.reduceTransparency || design.highContrast
    Box(modifier) {
        Canvas(Modifier.matchParentSize().onGloballyPositioned { origin = it.positionInRoot() }) {
            drawIntoCanvas { canvas ->
                renderer.draw(
                    canvas.nativeCanvas, size.width, size.height, density,
                    position, pressure, darkArt, design.isSanctuary, opaque, backdrop, origin,
                    palette.raised, palette.action, palette.hairline
                )
            }
        }
        Row(
            Modifier.fillMaxWidth().padding(4.dp).height(IntrinsicSize.Min).selectableGroup(),
            content = content
        )
    }
}

/** API33 uses an AGSL lens; older devices retain sampled frosted glass without a native dependency. */
internal class DevotionalGlassRenderer {
    private val lenses = if (Build.VERSION.SDK_INT >= 33) runCatching { Lens33() to Lens33() } else null
    val refractionAvailable: Boolean get() = lenses?.isSuccess == true
    val refractionFailure: String? get() = lenses?.exceptionOrNull()?.message
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    private val edge = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.STROKE }

    fun draw(
        canvas: android.graphics.Canvas, width: Float, height: Float, density: Float,
        position: Float, pressure: Float, darkArt: Boolean, sanctuary: Boolean, opaque: Boolean,
        backdrop: DevotionalBackdrop, origin: Offset, solid: Color, action: Color, hairline: Color
    ) {
        if (width <= 0 || height <= 0) return
        val inset = 4f * density
        val radius = if (sanctuary) 10f * density else height / 2f
        val neutral = when {
            darkArt && sanctuary -> Color(0xFF271B24)
            darkArt -> Color(0xFF151D26)
            sanctuary -> Color(0xFFFFF6E8)
            else -> Color(0xFFF5FAFF)
        }
        material(canvas, Rect(0f, 0f, width, height), radius,
            if (opaque) solid else neutral.copy(alpha = if (darkArt) 0.68f else 0.76f),
            opaque, backdrop, origin, density, 3.0f, false)
        val half = ((width - inset * 2f) / 2f).coerceAtLeast(1f)
        val left = inset + half * position.coerceIn(0f, 1f)
        val thumb = Rect(left, inset, left + half, height - inset)
        material(canvas, thumb, if (sanctuary) 7f * density else thumb.height / 2f,
            if (opaque) action else neutral.copy(alpha = if (darkArt) 0.66f else 0.64f),
            opaque, backdrop, origin, density, 6f + pressure * 2f, true)
        edge.strokeWidth = if (opaque) 1.5f * density else 0.9f * density
        edge.shader = null
        edge.color = if (opaque) hairline.toArgb() else Color.White.copy(alpha = if (darkArt) 0.29f else 0.72f).toArgb()
        canvas.drawRoundRect(0.5f * density, 0.5f * density, width - 0.5f * density, height - 0.5f * density, radius, radius, edge)
        if (!opaque) {
            edge.shader = android.graphics.LinearGradient(
                thumb.left, thumb.top, thumb.right, thumb.bottom,
                intArrayOf(Color.White.copy(alpha = 0.82f).toArgb(), Color.White.copy(alpha = 0.12f).toArgb(), Color.White.copy(alpha = 0.44f).toArgb()),
                floatArrayOf(0f, 0.52f, 1f), Shader.TileMode.CLAMP)
            val r = if (sanctuary) 7f * density else thumb.height / 2f
            canvas.drawRoundRect(thumb.left, thumb.top, thumb.right, thumb.bottom, r, r, edge)
            edge.shader = null
        }
    }

    private fun material(
        canvas: android.graphics.Canvas, bounds: Rect, radius: Float, tint: Color,
        opaque: Boolean, backdrop: DevotionalBackdrop, origin: Offset, density: Float,
        bend: Float, selected: Boolean
    ) {
        paint.shader = null
        paint.colorFilter = null
        paint.color = tint.copy(alpha = 1f).toArgb()
        val useRefraction = Build.VERSION.SDK_INT >= 33 && refractionAvailable && canvas.isHardwareAccelerated
        val image = if (useRefraction) backdrop.image else backdrop.softImage ?: backdrop.image
        if (!opaque && image != null && backdrop.frame.width > 0 && backdrop.frame.height > 0) {
            val bitmap = image.asAndroidBitmap()
            val scale = maxOf(backdrop.frame.width / bitmap.width, backdrop.frame.height / bitmap.height)
            val w = bitmap.width * scale
            val h = bitmap.height * scale
            val transform = Matrix().apply {
                setScale((w.toInt() + 1f) / bitmap.width, (h.toInt() + 1f) / bitmap.height)
                postTranslate(
                    backdrop.frame.left + ((backdrop.frame.width - w) * backdrop.focusX).toInt() - origin.x,
                    backdrop.frame.top + ((backdrop.frame.height - h) * backdrop.focusY).toInt() - origin.y)
            }
            val sample = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP).apply { setLocalMatrix(transform) }
            if (Build.VERSION.SDK_INT >= 33 && useRefraction) {
                val lens = if (selected) lenses!!.getOrThrow().second else lenses!!.getOrThrow().first
                paint.shader = lens.configure(sample, bounds, radius, tint, density, bend, backdrop.saturation, backdrop.gain)
                canvas.drawRoundRect(bounds.left, bounds.top, bounds.right, bounds.bottom, radius, radius, paint)
                paint.shader = null
                return
            }
            paint.shader = sample
            val colors = android.graphics.ColorMatrix().apply {
                setSaturation(backdrop.saturation)
                postConcat(android.graphics.ColorMatrix(floatArrayOf(
                    backdrop.gain,0f,0f,0f,0f, 0f,backdrop.gain,0f,0f,0f,
                    0f,0f,backdrop.gain,0f,0f, 0f,0f,0f,1f,0f)))
            }
            paint.colorFilter = ColorMatrixColorFilter(colors)
            canvas.drawRoundRect(bounds.left, bounds.top, bounds.right, bounds.bottom, radius, radius, paint)
            paint.shader = null
            paint.colorFilter = null
            paint.color = tint.toArgb()
        }
        canvas.drawRoundRect(bounds.left, bounds.top, bounds.right, bounds.bottom, radius, radius, paint)
    }
}

@RequiresApi(33)
private class Lens33 {
    // Android uses child.eval; some Skia backends retain the equivalent sample(child, p).
    private val shader = runCatching { RuntimeShader(SOURCE) }
        .recoverCatching { RuntimeShader(SOURCE.replace("scene.eval(", "sample(scene, ")) }
        .getOrThrow()
    fun configure(image: BitmapShader, bounds: Rect, radius: Float, tint: Color, density: Float, bend: Float, saturation: Float, gain: Float): RuntimeShader {
        shader.setInputShader("scene", image)
        shader.setFloatUniform("lens", bounds.left, bounds.top, bounds.right, bounds.bottom)
        shader.setFloatUniform("corner", radius)
        shader.setFloatUniform("edgeWidth", 9f * density)
        shader.setFloatUniform("bend", bend * density)
        shader.setFloatUniform("blur", 1.2f * density)
        shader.setFloatUniform("dispersion", 0.42f * density)
        shader.setFloatUniform("tint", tint.red, tint.green, tint.blue, tint.alpha)
        shader.setFloatUniform("saturation", saturation)
        shader.setFloatUniform("gain", gain)
        return shader
    }

    companion object {
        // Original implementation: rounded-rectangle distance, a refractive rim and a restrained specular edge.
        private const val SOURCE = """
            uniform shader scene;
            uniform float4 lens;
            uniform float corner;
            uniform float edgeWidth;
            uniform float bend;
            uniform float blur;
            uniform float dispersion;
            uniform float4 tint;
            uniform float saturation;
            uniform float gain;
            half4 main(float2 p) {
                float2 center = (lens.xy + lens.zw) * 0.5;
                float2 halfSize = (lens.zw - lens.xy) * 0.5;
                float2 q = p - center;
                float2 v = abs(q) - halfSize + corner;
                float2 outside = max(v, float2(0.0));
                float distance = length(outside) + min(max(v.x, v.y), 0.0) - corner;
                float2 normal = length(outside) > 0.001 ? normalize(outside) * sign(q) :
                    (v.x > v.y ? float2(sign(q.x), 0.0) : float2(0.0, sign(q.y)));
                float rim = 1.0 - smoothstep(0.0, edgeWidth, max(-distance, 0.0));
                float2 uv = p + normal * bend * rim * rim;
                half4 c = scene.eval(uv) * 0.40 +
                    (scene.eval(uv + float2(blur, 0.0)) + scene.eval(uv - float2(blur, 0.0)) +
                     scene.eval(uv + float2(0.0, blur)) + scene.eval(uv - float2(0.0, blur))) * 0.15;
                c.r = mix(c.r, scene.eval(uv + normal * dispersion).r, rim);
                c.b = mix(c.b, scene.eval(uv - normal * dispersion).b, rim);
                float luminance = dot(float3(c.rgb), float3(0.2126, 0.7152, 0.0722));
                float3 color = mix(float3(luminance), float3(c.rgb), saturation) * gain;
                color = mix(color, tint.rgb, tint.a);
                float light = max(dot(normal, normalize(float2(-0.6, -0.8))), 0.0);
                color += rim * rim * light * 0.11;
                return half4(clamp(color, 0.0, 1.0), 1.0);
            }
        """
    }
}
