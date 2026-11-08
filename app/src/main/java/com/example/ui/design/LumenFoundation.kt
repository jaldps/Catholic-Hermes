package com.example.ui.design

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalAppDesign
import org.json.JSONObject

// -----------------------------------------------------------------------------
// Lumen foundations: glass, press, soft shadow, screen frame, back override and
// hero bitmaps. Everything visual in the redesign is built from these.
// -----------------------------------------------------------------------------

/**
 * A soft, wide drop shadow the way the reference draws it (CSS-like
 * `0 10dp 30dp`), clipped *out* of the shape so translucent glass on top never
 * shows a grey wash through itself.
 */
fun Modifier.softShadow(shape: Shape, color: Color, blur: Dp = 30.dp, offsetY: Dp = 10.dp): Modifier =
    if (color.alpha <= 0f) this else drawBehind {
        val outline = shape.createOutline(size, layoutDirection, this)
        val path = Path().apply {
            when (outline) {
                is Outline.Rectangle -> addRect(outline.rect)
                is Outline.Rounded -> addRoundRect(outline.roundRect)
                is Outline.Generic -> addPath(outline.path)
            }
        }
        val androidPath = path.asAndroidPath()
        val paint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
            this.color = android.graphics.Color.TRANSPARENT
            setShadowLayer(blur.toPx() / 2f, 0f, offsetY.toPx(), color.toArgb())
        }
        drawIntoCanvas { canvas ->
            val native = canvas.nativeCanvas
            native.save()
            if (Build.VERSION.SDK_INT >= 26) {
                native.clipOutPath(androidPath)
            } else {
                @Suppress("DEPRECATION")
                native.clipPath(androidPath, android.graphics.Region.Op.DIFFERENCE)
            }
            // A transparent fill still casts its shadow layer; an opaque one
            // would paint the shape itself.
            paint.alpha = 1
            native.drawPath(androidPath, paint)
            native.restore()
        }
    }

/**
 * Frosted glass: translucent fill, a lit 1dp edge (bright at the top, fading
 * down) and a soft lift shadow. [strong] is the denser glass of floating
 * controls. [lifted] = false drops the shadow for glass nested in glass.
 */
fun Modifier.lumenGlass(shape: Shape, strong: Boolean = false, lifted: Boolean = true): Modifier = composed {
    val design = LocalAppDesign.current
    val p = design.palette
    this
        .then(
            if (lifted) softShadow(
                shape,
                p.shadow,
                blur = if (design.isDark) 24.dp else 30.dp,
                offsetY = if (design.isDark) 6.dp else 10.dp
            ) else Modifier
        )
        .background(if (strong) p.glassStrong else p.glass, shape)
        .border(1.dp, Brush.verticalGradient(listOf(p.glassEdgeTop, p.glassEdgeBottom)), shape)
}

/**
 * A ripple-free target that gives under the finger on a spring, as iOS
 * buttons and cards do. Null [onClick] leaves the modifier inert.
 */
fun Modifier.lumenPress(
    onClick: (() -> Unit)?,
    scale: Float = 0.97f,
    role: Role = Role.Button,
    enabled: Boolean = true
): Modifier = if (onClick == null) this else composed {
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val s by animateFloatAsState(
        if (pressed && enabled) scale else 1f,
        spring(dampingRatio = 0.8f, stiffness = 600f),
        label = "lumenPress"
    )
    graphicsLayer { scaleX = s; scaleY = s }
        .clickable(source, indication = null, enabled = enabled, role = role, onClick = onClick)
}

/** Full-screen canvas gradient. */
@Composable
fun Modifier.lumenCanvas(): Modifier {
    val p = LocalAppDesign.current.palette
    return background(Brush.verticalGradient(listOf(p.canvasTop, p.canvasBottom)))
}

// ------------------------------------------------------------- screen frame

/**
 * Per-destination frame state. A screen that calls [AppEdgeToEdge] draws from
 * the very top of the display to the very bottom: the scaffold stops padding
 * it, and the screen pads its own scroll content with [appScreenInsets].
 */
@Stable
class ScreenFrame {
    var edgeToEdge by mutableStateOf(false)
    /** Insets an edge-to-edge screen must respect: status bar (+ back row) and the floating dock. */
    var insets by mutableStateOf(PaddingValues(0.dp))
}

val LocalScreenFrame = staticCompositionLocalOf<ScreenFrame?> { null }

/**
 * Declares that the current screen draws edge to edge (under the status bar
 * and the floating tab bar). Call it once at the top of the screen
 * composable — not inside a lazy item. Returns the insets to pad scroll
 * content with: `top` clears the status bar (and the back button row on a
 * pushed screen), `bottom` clears the floating tab bar.
 */
@Composable
fun AppEdgeToEdge(): PaddingValues {
    val frame = LocalScreenFrame.current ?: return PaddingValues(0.dp)
    // Written during composition on purpose: the frame's padding is read in
    // the layout phase of the same frame, so the first frame is already right.
    if (!frame.edgeToEdge) frame.edgeToEdge = true
    return frame.insets
}

/**
 * The insets the current screen must pad itself with: the full frame insets
 * when it is edge to edge, nothing when the scaffold already pads it.
 */
@Composable
fun appScreenInsets(): PaddingValues {
    val frame = LocalScreenFrame.current ?: return PaddingValues(0.dp)
    return if (frame.edgeToEdge) frame.insets else PaddingValues(0.dp)
}

/** Height of the status bar, for content that draws under it. */
@Composable
fun statusBarHeight(): Dp = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

// ------------------------------------------------------------ back override

/**
 * Lets a screen take over the single glass back button in the top-left
 * corner (and system back) while it shows an in-screen detail, so there is
 * never a second back control. Innermost registration wins.
 */
@Stable
class BackOverrides {
    internal val stack = mutableStateListOf<() -> Unit>()
    val current: (() -> Unit)? get() = stack.lastOrNull()
}

val LocalBackOverrides = staticCompositionLocalOf<BackOverrides?> { null }

/**
 * While [enabled], the top-left glass back button and system back call
 * [onBack] instead of leaving the screen. Use it for in-screen details
 * (a novena, a prayer, a chapter) instead of drawing a back link.
 */
@Composable
fun AppBackHandler(enabled: Boolean = true, onBack: () -> Unit) {
    val overrides = LocalBackOverrides.current
    val latest by rememberUpdatedState(onBack)
    BackHandler(enabled = enabled) { latest() }
    if (overrides != null && enabled) {
        DisposableEffect(overrides) {
            val entry: () -> Unit = { latest() }
            overrides.stack.add(entry)
            onDispose { overrides.stack.remove(entry) }
        }
    }
}

// ------------------------------------------------------------------ heroes

/** Where to anchor a hero's crop, and whether the art is bright or dark. */
data class HeroMeta(val focusX: Float = 0.5f, val focusY: Float = 0.4f, val tone: String = "dark") {
    val alignment: Alignment get() = BiasAlignment(focusX * 2f - 1f, focusY * 2f - 1f)
}

private object HeroCache {
    private val bitmaps = object : android.util.LruCache<String, ImageBitmap>(48 * 1024 * 1024) {
        override fun sizeOf(key: String, value: ImageBitmap) = value.width * value.height * 4
    }
    private val manifests = java.util.concurrent.ConcurrentHashMap<String, JSONObject>()

    fun bitmap(context: Context, path: String, maxWidthPx: Int): ImageBitmap? {
        val key = "$path@$maxWidthPx"
        bitmaps.get(key)?.let { return it }
        val decoded = runCatching {
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            context.assets.open(path).use { BitmapFactory.decodeStream(it, null, bounds) }
            if (bounds.outWidth <= 0) return null
            var sample = 1
            while (bounds.outWidth / (sample * 2) >= maxWidthPx) sample *= 2
            val opts = BitmapFactory.Options().apply { inSampleSize = sample }
            context.assets.open(path).use { BitmapFactory.decodeStream(it, null, opts) }?.asImageBitmap()
        }.getOrNull() ?: return null
        bitmaps.put(key, decoded)
        return decoded
    }

    fun meta(context: Context, path: String): HeroMeta {
        val dir = path.substringBeforeLast('/', "")
        val file = path.substringAfterLast('/')
        val manifest = manifests.getOrPut(dir) {
            runCatching {
                JSONObject(context.assets.open("$dir/manifest.json").bufferedReader().use { it.readText() })
            }.getOrElse { JSONObject() }
        }
        val entry = manifest.optJSONObject(file) ?: return HeroMeta()
        return HeroMeta(
            focusX = entry.optDouble("focusX", 0.5).toFloat(),
            focusY = entry.optDouble("focusY", 0.4).toFloat(),
            tone = entry.optString("tone", "dark")
        )
    }

    fun exists(context: Context, path: String): Boolean = runCatching {
        context.assets.open(path).close(); true
    }.getOrDefault(false)
}

/** Decodes a bundled `assets/` image once (down-sampled, LRU-cached). */
@Composable
fun rememberAssetBitmap(assetPath: String?, maxWidthPx: Int = 1080): ImageBitmap? {
    val context = LocalContext.current
    return remember(assetPath, maxWidthPx) {
        assetPath?.let { HeroCache.bitmap(context, it, maxWidthPx) }
    }
}

/** Focus point and tone for a bundled hero, from its folder's `manifest.json`. */
@Composable
fun rememberHeroMeta(assetPath: String?): HeroMeta {
    val context = LocalContext.current
    return remember(assetPath) { assetPath?.let { HeroCache.meta(context, it) } ?: HeroMeta() }
}

/**
 * The first of [candidates] that exists in assets — lets a screen name the
 * new Lumen art and fall back to an older bundled photo.
 */
@Composable
fun rememberFirstAsset(vararg candidates: String): String? {
    val context = LocalContext.current
    val key = candidates.joinToString("|")
    return remember(key) { candidates.firstOrNull { HeroCache.exists(context, it) } }
}
