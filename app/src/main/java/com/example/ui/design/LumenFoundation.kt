package com.example.ui.design

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.snap
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.key
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ObserverModifierNode
import androidx.compose.ui.node.observeReads
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalAppDesign
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
 * Control glass: clear floating material with a specular rim and a soft lift
 * shadow. [strong] is the denser glass of floating controls that sit over
 * scrolling content. [lifted] = false drops the shadow for glass nested in
 * glass. See [liquidPane].
 */
fun Modifier.lumenGlass(shape: Shape, strong: Boolean = false, lifted: Boolean = true): Modifier = composed {
    val design = LocalAppDesign.current
    liquidPane(shape, design.glassSpec(if (strong) GlassRole.Floating else GlassRole.Control, lifted))
}

/** Content glass: the dense, calm pane reading text sits on. */
fun Modifier.appContentSurface(shape: Shape): Modifier = composed {
    val design = LocalAppDesign.current
    if (design.opaqueMaterial()) {
        val p = design.palette
        background(p.groupedSurface, shape)
            .border(if (design.highContrast) 1.5.dp else if (design.isSanctuary) 0.75.dp else 0.5.dp,
                if (design.highContrast || design.isSanctuary) p.hairline else p.separator, shape)
    } else liquidPane(shape, design.glassSpec(GlassRole.Content))
}

/** Fixed pane footer: its own material and an explicit scroll edge, never a bare cut through text. */
internal fun Modifier.appPaneFooter(reader: Boolean = false): Modifier = composed {
    val design = LocalAppDesign.current
    val p = design.palette
    val material = if (reader || design.opaqueMaterial()) Modifier.background(p.canvasTop)
        else Modifier.appContentSurface(androidx.compose.ui.graphics.RectangleShape)
    drawBehind {
        if (!design.opaqueMaterial()) {
            val fade = 16.dp.toPx()
            drawRect(Brush.verticalGradient(listOf(Color.Transparent, p.canvasTop), startY = -fade, endY = 0f),
                topLeft = androidx.compose.ui.geometry.Offset(0f, -fade), size = androidx.compose.ui.geometry.Size(size.width, fade))
        }
    }.then(material).drawWithContent {
        drawContent()
        if (design.opaqueMaterial()) drawLine(p.separator, androidx.compose.ui.geometry.Offset.Zero,
            androidx.compose.ui.geometry.Offset(size.width, 0f), 0.5.dp.toPx())
    }
}

/** For a supporting slot that owns its own scroller (for example the Bible's study panel). */
fun Modifier.appPanelScrollHint(scroll: androidx.compose.foundation.ScrollState): Modifier = composed {
    val color = LocalAppDesign.current.palette.canvasTop
    drawWithContent {
        drawContent()
        if (scroll.canScrollForward) {
            val height = 24.dp.toPx()
            drawRect(Brush.verticalGradient(listOf(Color.Transparent, color), startY = size.height - height, endY = size.height),
                topLeft = androidx.compose.ui.geometry.Offset(0f, size.height - height), size = androidx.compose.ui.geometry.Size(size.width, height))
        }
    }
}

/**
 * A ripple-free target that gives under the finger on a spring, as glass
 * does, and answers with the system's quiet click (and a tick for
 * selections). Null [onClick] leaves the modifier inert.
 */
fun Modifier.lumenPress(
    onClick: (() -> Unit)?,
    scale: Float = 0.97f,
    role: Role = Role.Button,
    enabled: Boolean = true,
    shape: Shape? = null
): Modifier = if (onClick == null) this else composed {
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val design = LocalAppDesign.current
    val reduceMotion = design.reduceMotion
    val feedback = rememberGlassFeedback()
    val latest by rememberUpdatedState(onClick)
    val s by animateFloatAsState(
        if (pressed && enabled && !reduceMotion) scale else 1f,
        if (reduceMotion) snap() else spring(dampingRatio = 0.62f, stiffness = 520f),
        label = "lumenPress"
    )
    graphicsLayer { scaleX = s; scaleY = s }
        .clickable(source, indication = appFocusRing(source, shape ?: design.shapes.button), enabled = enabled, role = role) {
            if (role == Role.Tab || role == Role.Switch || role == Role.Checkbox || role == Role.RadioButton) feedback.tick()
            else feedback.click()
            latest()
        }
}

/** Keep both the source's state and the factory stable while focus, theme or shape changes. */
@Composable
internal fun appFocusRing(source: InteractionSource, shape: Shape): IndicationNodeFactory {
    val focused = source.collectIsFocusedAsState()
    val p = LocalAppDesign.current.palette
    val color = rememberUpdatedState(p.textPrimary)
    val halo = rememberUpdatedState(p.canvasTop)
    val outline = rememberUpdatedState(shape)
    return remember(source, focused) { AppFocusRing(color, halo, outline, focused) }
}

/** No ripple. A one-dp canvas halo makes the two-dp ring readable on filled controls. */
internal data class AppFocusRing(
    val color: androidx.compose.runtime.State<Color>, val halo: androidx.compose.runtime.State<Color>,
    val shape: androidx.compose.runtime.State<Shape>, val focused: androidx.compose.runtime.State<Boolean>
) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): androidx.compose.ui.node.DelegatableNode = FocusRingNode(color, halo, shape, focused)
}

private class FocusRingNode(
    val color: androidx.compose.runtime.State<Color>, val halo: androidx.compose.runtime.State<Color>,
    val shape: androidx.compose.runtime.State<Shape>, val focused: androidx.compose.runtime.State<Boolean>
) : Modifier.Node(), DrawModifierNode, ObserverModifierNode {
    override fun onObservedReadsChanged() { invalidateDraw() }
    override fun ContentDrawScope.draw() {
        drawContent()
        observeReads {
            if (focused.value) inset(2.dp.toPx()) {
                val outline = shape.value.createOutline(size, layoutDirection, this)
                drawOutline(outline, halo.value, style = Stroke(4.dp.toPx()))
                drawOutline(outline, color.value, style = Stroke(2.dp.toPx()))
            }
        }
    }
}

/** Re-read the system setting on resume, including changes made while this app was paused. */
@Composable
fun appReduceMotion(preference: Boolean): Boolean {
    val lifecycle = androidx.lifecycle.compose.LocalLifecycleOwner.current.lifecycle
    fun systemDisabled() = Build.VERSION.SDK_INT >= 26 && !android.animation.ValueAnimator.areAnimatorsEnabled()
    var disabled by remember { mutableStateOf(systemDisabled()) }
    DisposableEffect(lifecycle) {
        val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
            if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) disabled = systemDisabled()
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
    return preference || disabled
}

/** Full-screen canvas: ambient light for Lumen, paper for Sanctuary. */
@Composable
fun Modifier.lumenCanvas(): Modifier {
    val design = LocalAppDesign.current
    val p = design.palette
    return liquidAmbient()
        .then(if (design.isSanctuary && !design.highContrast) Modifier.drawBehind {
            // Static, barely visible paper fibres. No bitmap allocation or animation.
            val step = 5.dp.toPx()
            var row = 0
            var y = 0f
            while (y < size.height) {
                val start = ((row * 37) % 113) / 113f * size.width
                val length = 10.dp.toPx() + ((row * 17) % 29).dp.toPx()
                drawLine(p.textPrimary.copy(alpha = 0.018f),
                    androidx.compose.ui.geometry.Offset(start, y),
                    androidx.compose.ui.geometry.Offset((start + length).coerceAtMost(size.width), y),
                    strokeWidth = 0.35.dp.toPx())
                row++
                y += step
            }
        } else Modifier)
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
    /** Insets an edge-to-edge screen must respect: status/back row, dock and safe start/end edges. */
    var insets by mutableStateOf(PaddingValues(0.dp))
    /** How many parts of the screen currently claim the full width (see [AppFullWidth]). */
    internal var fullWidthClaims by mutableIntStateOf(0)
    /** True while the screen lays out its own panes: on wide windows it is not centred in a reading column. */
    val fullWidth: Boolean get() = fullWidthClaims > 0
}

/** Holds one full-width claim for as long as it is remembered. */
private class FullWidthClaim(private val frame: ScreenFrame) : androidx.compose.runtime.RememberObserver {
    init { frame.fullWidthClaims++ }
    override fun onRemembered() {}
    override fun onForgotten() { frame.fullWidthClaims-- }
    override fun onAbandoned() { frame.fullWidthClaims-- }
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
 * Declares that the current screen uses the whole width of a wide window
 * (a two-pane list/detail, a study split). Every other screen is centred in a
 * reading column there. Read in the layout phase; the claim ends when the
 * caller leaves the composition.
 */
@Composable
fun AppFullWidth() {
    val frame = LocalScreenFrame.current ?: return
    // Claimed while composed, so the first frame is already right and leaving the pane gives the width back.
    remember(frame) { FullWidthClaim(frame) }
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

/** Safe drawing edges (physical bars/cutout, not the much wider gesture zones). */
data class AppSafeInsets(val start: Dp, val top: Dp, val end: Dp, val bottom: Dp) {
    fun asPaddingValues() = PaddingValues(start = start, top = top, end = end, bottom = bottom)
}

@Composable
fun appSafeInsets(): AppSafeInsets {
    val direction = androidx.compose.ui.platform.LocalLayoutDirection.current
    val bars = WindowInsets.statusBars.union(WindowInsets.navigationBars).union(WindowInsets.displayCutout).asPaddingValues()
    return AppSafeInsets(bars.calculateStartPadding(direction), bars.calculateTopPadding(),
        bars.calculateEndPadding(direction), bars.calculateBottomPadding())
}

// ------------------------------------------------------------ back override

/**
 * Lets a screen take over the single glass back button in the top-left
 * corner (and system back) while it shows an in-screen detail, so there is
 * never a second back control. Innermost registration wins.
 */
@Stable
class BackOverrides {
    internal val stack = mutableStateListOf<() -> Unit>()
    internal var baseCallback by mutableStateOf<(() -> Unit)?>(null)
    internal var baseLabel by mutableStateOf<String?>(null)
    internal var panelClaims by mutableIntStateOf(0)
    val effective: (() -> Unit)? get() = current ?: baseCallback
    val current: (() -> Unit)? get() = stack.lastOrNull()
}

private class PanelBackClaim(private val owner: BackOverrides) : androidx.compose.runtime.RememberObserver {
    init { owner.panelClaims++ }
    override fun onRemembered() {}
    override fun onForgotten() { owner.panelClaims-- }
    override fun onAbandoned() { owner.panelClaims-- }
}

@Composable
internal fun rememberPanelBack(): Pair<String, () -> Unit>? {
    val owner = LocalBackOverrides.current ?: return null
    val action = owner.effective ?: return null
    remember(owner) { PanelBackClaim(owner) }
    return (owner.baseLabel ?: "Back") to action
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
data class HeroMeta(
    val focusX: Float = 0.5f,
    val focusY: Float = 0.4f,
    val tone: String = "dark",
    /** > 1 crops tighter than "cover" around the focus point (art with a small subject). */
    val zoom: Float = 1f,
    /** Where, in the hero box (0..1), the focus point lands when [zoom] > 1. */
    val anchorX: Float = 0.5f,
    val anchorY: Float = 0.5f
) {
    val alignment: Alignment get() = BiasAlignment(focusX * 2f - 1f, focusY * 2f - 1f)
}

/**
 * Bundled images decode two at a time: a long list (e.g. the 55 chaplets) must not start dozens of
 * decoders at once on a phone with a small heap.
 */
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
private val AssetDecoding = Dispatchers.IO.limitedParallelism(2)

private object HeroCache {
    private val bitmaps = object : android.util.LruCache<String, ImageBitmap>(48 * 1024 * 1024) {
        override fun sizeOf(key: String, value: ImageBitmap) = value.width * value.height * 4
    }
    private val manifests = java.util.concurrent.ConcurrentHashMap<String, JSONObject>()

    /** A bitmap already decoded and cached, or null (never decodes). Safe on the main thread. */
    fun peek(path: String, maxWidthPx: Int): ImageBitmap? = bitmaps.get("$path@$maxWidthPx")

    /** Decodes (and caches) a bitmap. Blocking: call it off the main thread. */
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
            tone = entry.optString("tone", "dark"),
            zoom = entry.optDouble("zoom", 1.0).toFloat(),
            anchorX = entry.optDouble("anchorX", 0.5).toFloat(),
            anchorY = entry.optDouble("anchorY", 0.5).toFloat()
        )
    }

    fun exists(context: Context, path: String): Boolean = runCatching {
        context.assets.open(path).close(); true
    }.getOrDefault(false)
}

/**
 * Decodes a bundled `assets/` image once (down-sampled, LRU-cached). The decode
 * runs on [Dispatchers.IO], never on the main thread: the result is null until
 * it lands (a cache hit is returned on the first frame).
 */
@Composable
fun rememberAssetBitmap(assetPath: String?, maxWidthPx: Int = 1080): ImageBitmap? {
    val context = LocalContext.current
    val request = assetPath to maxWidthPx
    val state = produceState(
        initialValue = request to assetPath?.let { HeroCache.peek(it, maxWidthPx) },
        assetPath, maxWidthPx
    ) {
        val cached = assetPath?.let { HeroCache.peek(it, maxWidthPx) }
        value = request to cached
        if (assetPath != null && cached == null) {
            val decoded = withContext(AssetDecoding) { HeroCache.bitmap(context, assetPath, maxWidthPx) }
            value = request to decoded
        }
    }
    // The producer restarts after composition; never expose a previous request's
    // image during that frame, even when the previous bitmap was non-null.
    return state.value.takeIf { it.first == request }?.second
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
