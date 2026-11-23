package com.example.ui.design

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.semantics.verticalScrollAxisRange
import androidx.compose.ui.semantics.scrollBy
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.material.icons.outlined.Close
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.systemGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// -----------------------------------------------------------------------------
// Lumen extensions requested by the area plans: chrome control, sheets, menus,
// grouped lazy rows, toggles, confirm dialog, segmented progress, crossfading
// art, reading settings. See docs/redesign/COMPONENTS.md.
// -----------------------------------------------------------------------------

private fun Modifier.tag(tag: String?): Modifier = if (tag == null) this else testTag(tag)

// ------------------------------------------------------------------ chrome

/**
 * Scaffold-level chrome a screen can adjust: hide the floating dock, replace
 * the inline (scrolled) title. Provided by the Lumen scaffold.
 */
@Stable
class ChromeState {
    internal var dockHiders by mutableIntStateOf(0)
    internal val titles = mutableStateListOf<String>()
    internal val statusArtwork = mutableStateListOf<HeaderStatusArtwork>()
    /** Bottom inset that clears the floating dock (or the nav bar when hidden). */
    var dockInset by mutableStateOf(0.dp)
    val dockHidden: Boolean get() = dockHiders > 0
    val titleOverride: String? get() = titles.lastOrNull()
}

val LocalChrome = staticCompositionLocalOf<ChromeState?> { null }

internal class HeaderStatusArtwork(val source: LiquidBackdrop) {
    var ready by mutableStateOf(false)
    val softwarePicture = android.graphics.Picture()
    var softwareReady by mutableStateOf(false)
}

/** Records only decorative art, so the scaffold can paint its status-bar strip independently of safe scrolling controls. */
@Composable
internal fun Modifier.appStatusArtwork(): Modifier {
    val chrome = LocalChrome.current ?: return this
    if (!appIsLandscapePhone()) return this
    val layer = rememberGraphicsLayer()
    val art = remember(layer) { HeaderStatusArtwork(LiquidBackdrop(layer)) }
    DisposableEffect(chrome, art) {
        chrome.statusArtwork.add(art)
        onDispose { chrome.statusArtwork.remove(art) }
    }
    return onGloballyPositioned { art.source.origin = it.positionInRoot() }.drawWithContent {
        if (drawContext.canvas.nativeCanvas.isHardwareAccelerated) {
            layer.record { this@drawWithContent.drawContent() }
            if (!art.ready) art.ready = true
            drawLayer(layer)
        } else {
            // Bitmap screenshots and software windows cannot draw a RenderNode.
            // Record the same decorative commands, without changing their content.
            val original = drawContext.canvas
            val recorded = art.softwarePicture.beginRecording(size.width.toInt().coerceAtLeast(1), size.height.toInt().coerceAtLeast(1))
            try {
                drawContext.canvas = androidx.compose.ui.graphics.Canvas(recorded)
                drawContent()
            } finally {
                drawContext.canvas = original
                art.softwarePicture.endRecording()
            }
            original.nativeCanvas.drawPicture(art.softwarePicture)
            if (!art.softwareReady) art.softwareReady = true
        }
    }
}

/** Hides the floating tab bar while composed (immersive readers). */
@Composable
fun AppHideTabBar(hidden: Boolean = true) {
    val chrome = LocalChrome.current ?: return
    DisposableEffect(chrome, hidden) {
        if (hidden) chrome.dockHiders++
        onDispose { if (hidden) chrome.dockHiders-- }
    }
}

/**
 * Replaces the scaffold's inline title (the one that fades in on scroll)
 * while composed — call it at the root of an in-screen detail.
 */
@Composable
fun AppInlineTitle(title: String) {
    val chrome = LocalChrome.current ?: return
    DisposableEffect(chrome, title) {
        chrome.titles.add(title)
        onDispose { chrome.titles.remove(title) }
    }
}

/** Bottom padding that clears the floating dock, for any screen. */
@Composable
fun appDockInset(): Dp = LocalChrome.current?.dockInset
    ?: WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

/**
 * Forces status-bar glyph colour while composed (e.g. light glyphs over a
 * dark painting in light mode). Restores the theme's choice on dispose.
 */
@Composable
fun AppStatusBarGlyphs(lightGlyphs: Boolean) {
    val view = LocalView.current
    val isDark = LocalAppDesign.current.isDark
    if (view.isInEditMode) return
    SideEffect {
        val window = (view.context as? android.app.Activity)?.window ?: return@SideEffect
        androidx.core.view.WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !lightGlyphs
    }
    DisposableEffect(lightGlyphs) {
        onDispose {
            val window = (view.context as? android.app.Activity)?.window ?: return@onDispose
            androidx.core.view.WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
        }
    }
}

// ------------------------------------------------------------------ glyphs

object LumenGlyphs {
    /** A slender Latin cross, outline weight to sit beside Material outline icons. */
    val cross: ImageVector by lazy {
        ImageVector.Builder("lumen.cross", 24.dp, 24.dp, 24f, 24f).apply {
            addPath(
                pathData = PathParser().parsePathString(
                    "M11.1,2.5 h1.8 v5.6 h5.2 v1.8 h-5.2 v11.6 h-1.8 v-11.6 h-5.2 v-1.8 h5.2 z"
                ).toNodes(),
                fill = SolidColor(Color.Black)
            )
        }.build()
    }
}

/**
 * The liturgical colour as Lumen's dot/ring accent, from a colour name or an
 * arbitrary hex the data carries.
 */
fun lumenLiturgicalColor(name: String?, hex: Long? = null): Color {
    val n = name?.lowercase().orEmpty()
    return when {
        "viol" in n || "purp" in n || "morad" in n || "roxo" in n || "roxa" in n -> Color(0xFF7B5EA7)
        "rose" in n || "rosa" in n -> Color(0xFFD48AA0)
        "red" in n || "roj" in n || "verm" in n -> Color(0xFFC0392B)
        "white" in n || "blanc" in n || "branc" in n || "gold" in n || "oro" in n || "ouro" in n -> Color(0xFFC8A24A)
        "green" in n || "verde" in n -> Color(0xFF3E9B4F)
        hex != null -> nearestLiturgical(Color(hex))
        else -> Color(0xFF3E9B4F)
    }
}

private fun nearestLiturgical(c: Color): Color {
    val options = listOf(0xFF3E9B4F, 0xFF7B5EA7, 0xFFC8A24A, 0xFFC0392B, 0xFFD48AA0).map { Color(it) }
    return options.minBy { o ->
        val dr = o.red - c.red; val dg = o.green - c.green; val db = o.blue - c.blue
        dr * dr + dg * dg + db * db
    }
}

// --------------------------------------------------------- grouped lazy rows

/** Where a row sits in a group drawn across separate LazyColumn items. */
enum class GroupPosition { First, Middle, Last, Single }

/**
 * Content glass for one row of a group split across lazy items: only the
 * outer corners are rounded and the rim runs unbroken down the sides, so
 * consecutive items read as one pane. No shadow (it would seam between
 * items). Applies the gutter.
 */
@Composable
fun Modifier.groupedItem(position: GroupPosition): Modifier {
    val design = LocalAppDesign.current
    val r = if (design.isSanctuary) 8.dp else 24.dp
    val shape = when (position) {
        GroupPosition.First -> RoundedCornerShape(topStart = r, topEnd = r)
        GroupPosition.Middle -> RoundedCornerShape(0.dp)
        GroupPosition.Last -> RoundedCornerShape(bottomStart = r, bottomEnd = r)
        GroupPosition.Single -> RoundedCornerShape(r)
    }
    val base = this.fillMaxWidth().padding(horizontal = design.spacing.gutter)
    if (design.opaqueMaterial()) return base.clip(shape).background(design.palette.groupedSurface)
    val spec = design.glassSpec(GlassRole.Content, lifted = false)
    return base.clip(shape).drawBehind {
        // The pane continues beyond this item wherever the group does.
        val radius = r.toPx()
        val top = if (position == GroupPosition.First || position == GroupPosition.Single) 0f else -radius * 2f
        val bottom = if (position == GroupPosition.Last || position == GroupPosition.Single) size.height else size.height + radius * 2f
        val stroke = spec.rimWidth.toPx()
        val corner = androidx.compose.ui.geometry.CornerRadius(radius, radius)
        drawRoundRect(spec.fillBottom, androidx.compose.ui.geometry.Offset(0f, top),
            androidx.compose.ui.geometry.Size(size.width, bottom - top), corner)
        drawRoundRect(
            Brush.horizontalGradient(listOf(spec.rimLight, spec.rimShade, spec.rimLight.copy(alpha = spec.rimLight.alpha * 0.6f))),
            androidx.compose.ui.geometry.Offset(stroke / 2f, top + stroke / 2f),
            androidx.compose.ui.geometry.Size(size.width - stroke, bottom - top - stroke), corner,
            style = androidx.compose.ui.graphics.drawscope.Stroke(stroke)
        )
    }
}

/** A list row usable as a lazy item; see [groupedItem]. */
@Composable
fun AppGroupedRow(
    title: String,
    position: GroupPosition,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingIcon: ImageVector? = null,
    trailingText: String? = null,
    onClick: (() -> Unit)? = null,
    selected: Boolean = false,
    tag: String? = null,
    trailing: (@Composable () -> Unit)? = null
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(modifier.groupedItem(position).tag(tag)) {
        Row(
            Modifier
                .fillMaxWidth()
                .lumenPress(onClick, 0.99f)
                .heightIn(min = design.spacing.rowMinHeight)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                if (design.isSanctuary) Icon(leadingIcon, null, tint = if (selected) p.accent else p.textPrimary, modifier = Modifier.size(22.dp))
                else AppIconWell(leadingIcon)
                Spacer(Modifier.width(if (design.isSanctuary) 16.dp else 14.dp))
            }
            // Large text: a trailing value would leave the title a sliver of width, so it joins the second line.
            val stacked = appLargeText() && !trailingText.isNullOrBlank()
            Column(Modifier.weight(1f)) {
                Text(title, style = styles.body, color = if (selected) p.accent else p.textPrimary)
                val second = if (stacked) listOfNotNull(subtitle, trailingText).joinToString(" · ") else subtitle
                if (second != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(second, style = styles.footnote, color = p.textSecondary)
                }
            }
            if (trailingText != null && !stacked) {
                Spacer(Modifier.width(10.dp))
                Text(trailingText, style = styles.subhead, color = p.textSecondary, maxLines = appMaxLines(1),
                    overflow = TextOverflow.Ellipsis, modifier = Modifier.widthIn(max = 140.dp))
            }
            if (trailing != null) {
                Spacer(Modifier.width(10.dp))
                trailing()
            } else if (onClick != null) {
                Spacer(Modifier.width(10.dp))
                AppChevron()
            }
        }
        if (position == GroupPosition.First || position == GroupPosition.Middle) {
            Box(
                Modifier.fillMaxWidth().padding(start = 18.dp + if (leadingIcon != null) (if (design.isSanctuary) 38.dp else 50.dp) else 0.dp)
                    .height(0.5.dp).background(p.separator)
            )
        }
    }
}

// ------------------------------------------------------------------ toggles

/** Glass switch: an accent-lit track with a bright lens that slides on a spring. */
@Composable
fun AppSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier, tag: String? = null) {
    val design = LocalAppDesign.current
    val p = design.palette
    val feedback = rememberGlassFeedback()
    val track by animateColorAsState(if (checked) p.success else p.textPrimary.copy(alpha = if (design.isDark) 0.20f else 0.14f),
        tween(design.motion.fastMillis), label = "switch")
    val knob by animateDpAsState(if (checked) 20.dp else 0.dp,
        if (design.reduceMotion) androidx.compose.animation.core.snap() else androidx.compose.animation.core.spring(dampingRatio = 0.7f, stiffness = 520f),
        label = "knob")
    val capsule = RoundedCornerShape(percent = 50)
    Box(modifier.size(width = 56.dp, height = 48.dp)
        .toggleable(value = checked, role = Role.Switch, onValueChange = { feedback.tick(); onCheckedChange(it) })
        .tag(tag), contentAlignment = Alignment.Center) {
        Box(Modifier.size(width = 51.dp, height = 31.dp)
            .clip(capsule).background(track)
            .border(if (design.highContrast) 1.5.dp else 1.dp,
                if (design.opaqueMaterial()) Brush.verticalGradient(listOf(p.hairline, p.hairline))
                else Brush.verticalGradient(listOf(Color.Black.copy(alpha = if (design.isDark) 0.30f else 0.10f), Color.White.copy(alpha = if (design.isDark) 0.14f else 0.55f))),
                capsule)
            .padding(2.dp)) {
            Box(Modifier.offset(x = knob).size(27.dp)
                .softShadow(CircleShape, Color.Black.copy(alpha = 0.20f), 6.dp, 2.dp)
                .background(Brush.verticalGradient(listOf(Color.White, Color(0xFFF1F3F8))), CircleShape))
        }
    }
}

/** A list row with a trailing [AppSwitch]; inside [AppListGroup]. */
@Composable
fun AppToggleRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingIcon: ImageVector? = null,
    showDivider: Boolean = true,
    tag: String? = null,
    switchTag: String? = null
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(modifier.fillMaxWidth().tag(tag)) {
        Row(
            Modifier.fillMaxWidth().heightIn(min = 56.dp).padding(horizontal = 18.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                if (design.isSanctuary) Icon(leadingIcon, null, tint = p.textPrimary, modifier = Modifier.size(22.dp))
                else AppIconWell(leadingIcon)
                Spacer(Modifier.width(if (design.isSanctuary) 16.dp else 14.dp))
            }
            Column(Modifier.weight(1f)) {
                Text(title, style = styles.body, color = p.textPrimary)
                if (subtitle != null) Text(subtitle, style = styles.footnote, color = p.textSecondary)
            }
            Spacer(Modifier.width(12.dp))
            AppSwitch(checked, onCheckedChange, modifier = Modifier.semantics { contentDescription = title }, tag = switchTag)
        }
        if (showDivider) {
            Box(Modifier.fillMaxWidth().padding(start = 18.dp + if (leadingIcon != null) (if (design.isSanctuary) 38.dp else 50.dp) else 0.dp)
                .height(0.5.dp).background(p.separator))
        }
    }
}

// ------------------------------------------------------------------ search

/** Glass search capsule with clear button and IME search action. */
@Composable
fun AppSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    onSubmit: ((String) -> Unit)? = null,
    tag: String? = null,
    /** Takes the keyboard focus when the bar first appears (the global search opens ready to type). */
    autoFocus: Boolean = false
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val shape = design.shapes.field
    val focusRequester = remember { FocusRequester() }
    if (autoFocus) LaunchedEffect(Unit) { runCatching { focusRequester.requestFocus() } }
    Row(
        modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter).heightIn(min = 48.dp)
            .lumenGlass(shape, lifted = false).semantics { contentDescription = placeholder }
            .padding(start = 16.dp, end = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Outlined.Search, null, tint = p.textSecondary, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(10.dp))
        Box(Modifier.weight(1f)) {
            if (value.isEmpty()) Text(placeholder, style = styles.body, color = p.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = styles.body.copy(color = p.textPrimary),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSubmit?.invoke(value) }),
                modifier = Modifier.fillMaxWidth().tag(tag).focusRequester(focusRequester)
            )
        }
        if (value.isNotEmpty()) {
            val clearSource = remember { MutableInteractionSource() }
            Box(
                Modifier.size(48.dp).clickable(clearSource, appFocusRing(clearSource, androidx.compose.foundation.shape.CircleShape), role = Role.Button) { onValueChange("") }
                    .semantics { contentDescription = "Clear" },
                contentAlignment = Alignment.Center
            ) { Icon(Icons.Filled.Cancel, null, tint = p.inkQuiet, modifier = Modifier.size(18.dp)) }
        } else Spacer(Modifier.width(10.dp))
    }
}

// ------------------------------------------------------------- sheet/menus

/**
 * Glass bottom sheet hosted in its own Dialog window (so tests using
 * ShadowDialog find it): scrim, drag handle, optional [title], scrollable
 * [content]. Tap the scrim or press back to dismiss.
 */
@Composable
fun AppSheet(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    tag: String? = null,
    wide: Boolean = false,
    footer: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val compact = appHasCompactHeight()
    val window = appWindowSize()
    val safe = appSafeInsets()
    val density = androidx.compose.ui.platform.LocalDensity.current
    val keyboard = with(density) { WindowInsets.ime.getBottom(this).toDp() }
    val keyboardOpen = keyboard > 0.dp
    val width = (window.width * if (wide) 0.66f else 0.5f).coerceIn(if (wide) 480.dp else 360.dp, if (wide) 640.dp else 480.dp)
        .coerceAtMost(window.width - safe.start - safe.end - 16.dp)
    val top = if (compact && !design.isSanctuary) safe.top + 8.dp else 0.dp
    val bottom = if (compact && (keyboardOpen || !design.isSanctuary)) maxOf(safe.bottom, keyboard) + 8.dp else 0.dp
    val height = (window.height - top - bottom).coerceAtLeast(56.dp)
    val shape = if (!compact) design.shapes.sheet else RoundedCornerShape(if (design.isSanctuary) 0.dp else if (design.opaqueMaterial()) 16.dp else 28.dp)
    val stored = LocalPreferencesStore.current?.preferences?.collectAsState(initial = null)
    val language = stored?.value?.language ?: com.example.data.models.AppLanguage.fromCode(LocalConfiguration.current.locales[0].language)
    val close = when (language) { com.example.data.models.AppLanguage.ES -> "Cerrar"; com.example.data.models.AppLanguage.PT -> "Fechar"; else -> "Close" }
    var shown by remember { mutableStateOf(false) }
    var drag by remember { androidx.compose.runtime.mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) { shown = true }
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)) {
        if (compact) {
            val host = androidx.compose.ui.platform.LocalView.current
            androidx.compose.runtime.DisposableEffect(host) {
                val dialogWindow = (host.parent as? androidx.compose.ui.window.DialogWindowProvider)?.window
                val previousDim = dialogWindow?.attributes?.dimAmount
                dialogWindow?.setDimAmount(0f)
                onDispose { if (previousDim != null) dialogWindow?.setDimAmount(previousDim) }
            }
        }
        androidx.compose.runtime.CompositionLocalProvider(LocalLiquidBackdrop provides null) {
            Box(Modifier.fillMaxSize()) {
                Box(Modifier.fillMaxSize().background(p.scrim).focusProperties { canFocus = false }
                    .clearAndSetSemantics { this[androidx.compose.ui.semantics.SemanticsProperties.TestTag] = "sheet_scrim" }
                    .clickable(remember { MutableInteractionSource() }, null) { onDismiss() })
                AnimatedVisibility(shown,
                    enter = if (design.reduceMotion) androidx.compose.animation.EnterTransition.None else if (compact)
                        androidx.compose.animation.slideInHorizontally(tween(design.motion.mediumMillis)) { it }
                        else slideInVertically(tween(design.motion.mediumMillis)) { it / 3 } + fadeIn(tween(design.motion.fastMillis)),
                    exit = if (design.reduceMotion) androidx.compose.animation.ExitTransition.None else if (compact)
                        androidx.compose.animation.slideOutHorizontally(tween(design.motion.mediumMillis)) { it }
                        else slideOutVertically(tween(design.motion.mediumMillis)) { it / 3 } + fadeOut(tween(design.motion.fastMillis)),
                    modifier = Modifier.align(if (compact) Alignment.TopEnd else Alignment.BottomCenter)
                        .padding(top = top, end = if (!compact) 0.dp else if (design.isSanctuary) safe.end else maxOf(8.dp, safe.end + 8.dp))) {
                    val navInset = maxOf(WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
                        WindowInsets.systemGestures.asPaddingValues().calculateBottomPadding(), 24.dp)
                    // One content node and one footer node in both orientations.
                    androidx.compose.ui.layout.Layout(content = {
                        Box(Modifier.fillMaxWidth().padding(top = 10.dp), contentAlignment = Alignment.Center) {
                            Box(Modifier.size(40.dp, 5.dp).clip(RoundedCornerShape(50)).background(p.inkQuiet.copy(alpha = 0.55f)))
                        }
                        Box(Modifier.then(if (compact) Modifier.fillMaxWidth().height(44.dp) else Modifier)
                            .pointerInput(compact, onDismiss) {
                                if (compact) detectHorizontalDragGestures(
                                    onHorizontalDrag = { change, delta -> change.consume(); drag = (drag + delta).coerceAtLeast(0f) },
                                    onDragEnd = { if (drag > 48.dp.toPx()) onDismiss(); drag = 0f }, onDragCancel = { drag = 0f })
                            }, contentAlignment = if (compact) Alignment.BottomStart else Alignment.CenterStart) {
                            Text(title.orEmpty(), style = styles.headline, color = p.textPrimary,
                                textAlign = if (compact) TextAlign.Start else TextAlign.Center,
                                modifier = (if (compact) Modifier else Modifier.fillMaxWidth().padding(top = 14.dp, bottom = 8.dp)).semantics { heading() })
                        }
                        AppGlassIconButton(Icons.Outlined.Close, close, onDismiss, tag = "sheet_close")
                        Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(top = if (compact) 0.dp else 8.dp)) {
                            content()
                            Spacer(Modifier.height(if (compact || footer != null) 16.dp else navInset + 16.dp))
                        }
                        Row(Modifier.fillMaxWidth().then(if (compact) Modifier.padding(horizontal = 16.dp) else Modifier),
                            horizontalArrangement = if (compact) Arrangement.End else Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically) { footer?.invoke(this) }
                    }, modifier = modifier.then(if (compact) Modifier.width(width).height(height).offset { androidx.compose.ui.unit.IntOffset(drag.toInt(), 0) }
                        else Modifier.fillMaxWidth().heightIn(max = (LocalConfiguration.current.screenHeightDp * 0.9f).dp))
                        .appFloatingPanel(shape).tag(tag ?: if (compact) "side_sheet" else null)
                        .semantics { paneTitle = title ?: close; isTraversalGroup = true }
                        .then(if (compact && design.isSanctuary) Modifier.padding(top = safe.top + 8.dp, bottom = if (keyboardOpen) 0.dp else safe.bottom) else Modifier)) { nodes, constraints ->
                        val w = constraints.maxWidth
                        val loose = constraints.copy(minWidth = 0, minHeight = 0)
                        val header = if (compact) 56.dp.roundToPx().coerceAtMost(constraints.maxHeight) else 0
                        val inHeader = compact && keyboardOpen && footer != null
                        val foot = nodes[4].measure(loose.copy(maxWidth = if (inHeader) (w * 0.62f).toInt() else w,
                            maxHeight = if (footer == null) 0 else if (compact) header else constraints.maxHeight))
                        val closeButton = nodes[2].measure(loose.copy(maxWidth = 44.dp.roundToPx(), maxHeight = 44.dp.roundToPx()))
                        val handle = nodes[0].measure(loose.copy(minWidth = w, maxHeight = if (compact) 0 else constraints.maxHeight))
                        val trailing = if (inHeader) foot.width else closeButton.width
                        val heading = nodes[1].measure(loose.copy(maxWidth = if (compact) (w - trailing - 40.dp.roundToPx()).coerceAtLeast(0) else w,
                            maxHeight = if (compact) header else if (title == null) 0 else constraints.maxHeight))
                        val headingHeight = if (compact) header else handle.height + heading.height
                        val footerHeight = if (footer != null && !inHeader) if (compact) header else foot.height else 0
                        val body = nodes[3].measure(loose.copy(minWidth = w, maxHeight = (constraints.maxHeight - headingHeight - footerHeight).coerceAtLeast(0)))
                        val h = if (compact) constraints.maxHeight else constraints.constrainHeight(headingHeight + body.height + footerHeight)
                        layout(w, h) {
                            if (compact) {
                                heading.placeRelative(20.dp.roundToPx(), header - 12.dp.roundToPx() - heading.height)
                                if (!inHeader) closeButton.placeRelative(w - closeButton.width - 12.dp.roundToPx(), (header - closeButton.height) / 2)
                            } else { handle.placeRelative(0, 0); if (title != null) heading.placeRelative(0, handle.height) }
                            body.placeRelative(0, headingHeight)
                            if (footer != null) foot.placeRelative(if (inHeader) w - foot.width else 0, if (inHeader) (header - foot.height) / 2 else h - footerHeight)
                        }
                    }
                }
            }
        }
    }
}

/**
 * The material of anything that floats in its own window (sheets, dialogs):
 * a pane lit by the same ambient light as the screens, with a specular rim.
 */
@Composable
fun Modifier.appFloatingPanel(shape: androidx.compose.ui.graphics.Shape): Modifier {
    val p = LocalAppDesign.current.palette
    return this.clip(shape).background(p.canvasTop).lumenCanvas()
        .border(1.dp, Brush.verticalGradient(
            0f to p.glassEdgeTop, 0.3f to p.glassEdgeBottom.copy(alpha = p.glassEdgeBottom.alpha * 0.5f), 1f to p.glassEdgeBottom.copy(alpha = p.glassEdgeBottom.alpha * 0.3f)), shape)
}

/**
 * A centred glass dialog for a short decision or a first-run choice. Content
 * scrolls when it is taller than the window.
 */
@Composable
fun AppDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    tag: String? = null,
    aside: (@Composable () -> Unit)? = null,
    footer: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val compact = appHasCompactHeight()
    val safe = appSafeInsets()
    val window = appWindowSize()
    val density = androidx.compose.ui.platform.LocalDensity.current
    val ime = with(density) { WindowInsets.ime.getBottom(this).toDp() }
    val width = minOf(720.dp, window.width - safe.start - safe.end - 48.dp)
    val height = (window.height - safe.top - maxOf(safe.bottom, ime) - 32.dp).coerceAtLeast(56.dp)
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = !compact)) {
        if (compact) {
            val host = androidx.compose.ui.platform.LocalView.current
            androidx.compose.runtime.DisposableEffect(host) {
                val dialogWindow = (host.parent as? androidx.compose.ui.window.DialogWindowProvider)?.window
                val previousDim = dialogWindow?.attributes?.dimAmount
                dialogWindow?.setDimAmount(0f)
                onDispose { if (previousDim != null) dialogWindow?.setDimAmount(previousDim) }
            }
        }
        androidx.compose.runtime.CompositionLocalProvider(LocalLiquidBackdrop provides null) {
            Box(Modifier.fillMaxSize()) {
                if (compact) Box(Modifier.fillMaxSize().background(p.scrim).focusProperties { canFocus = false }
                    .clearAndSetSemantics { this[androidx.compose.ui.semantics.SemanticsProperties.TestTag] = "dialog_scrim" }
                    .clickable(remember { MutableInteractionSource() }, null) { onDismiss() })
            Box(Modifier.fillMaxSize().then(if (compact) Modifier.padding(start = safe.start + 24.dp, end = safe.end + 24.dp,
                top = safe.top + 16.dp, bottom = maxOf(safe.bottom, ime) + 16.dp) else Modifier), contentAlignment = Alignment.Center) {
                androidx.compose.ui.layout.Layout(content = {
                    Column(Modifier.then(if (compact) Modifier.verticalScroll(rememberScrollState()) else Modifier)
                        .then(if (aside != null) Modifier.padding(20.dp) else Modifier)
                        .semantics { isTraversalGroup = true; traversalIndex = 0f }) { aside?.invoke() }
                    Column(Modifier.semantics { isTraversalGroup = true; traversalIndex = 1f }) {
                        AppPinnedDialogContent(pinLast = compact && footer == null, scrollEnabled = compact || footer != null) {
                            if (title != null) {
                                Text(title, style = styles.title2, color = p.textPrimary,
                                    modifier = Modifier.padding(horizontal = 24.dp).semantics { heading() })
                                Spacer(Modifier.height(8.dp))
                            }
                            content()
                        }
                    }
                    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) { footer?.invoke(this) }
                }, modifier = modifier.then(if (compact) Modifier.width(width).heightIn(max = height)
                    else Modifier.padding(horizontal = 20.dp, vertical = 24.dp).widthIn(max = 520.dp).fillMaxWidth())
                    .appFloatingPanel(design.shapes.dialog).tag(tag ?: if (compact) "compact_dialog" else null)
                    .semantics { paneTitle = title.orEmpty(); isTraversalGroup = true }
                    .then(if (!compact && footer == null) Modifier.verticalScroll(rememberScrollState()) else Modifier)
                    .padding(vertical = if (compact) 12.dp else 24.dp)) { nodes, constraints ->
                    val w = constraints.maxWidth
                    val asideWidth = if (compact && aside != null) (w * 0.42f).toInt() else if (aside != null) w else 0
                    val bodyWidth = if (compact) w - asideWidth else w
                    val loose = constraints.copy(minWidth = 0, minHeight = 0)
                    val foot = nodes[2].measure(loose.copy(minWidth = bodyWidth, maxWidth = bodyWidth,
                        maxHeight = if (footer == null) 0 else 56.dp.roundToPx()))
                    val asidePlace = nodes[0].measure(loose.copy(minWidth = asideWidth, maxWidth = asideWidth))
                    val body = nodes[1].measure(loose.copy(minWidth = bodyWidth, maxWidth = bodyWidth,
                        maxHeight = if (compact) (constraints.maxHeight - foot.height).coerceAtLeast(0) else constraints.maxHeight))
                    val h = constraints.constrainHeight(if (compact) maxOf(asidePlace.height, body.height + foot.height) else asidePlace.height + body.height + foot.height)
                    layout(w, h) {
                        if (aside != null) asidePlace.placeRelative(0, 0)
                        body.placeRelative(if (compact) asideWidth else 0, if (compact) 0 else asidePlace.height)
                        if (footer != null) foot.placeRelative(if (compact) asideWidth else 0, h - foot.height)
                    }
                }
            }
            }
        }
    }
}

/** One entry of [AppMenu]. */
data class AppMenuItem(val label: String, val icon: ImageVector? = null, val selected: Boolean = false, val tag: String? = null, val onClick: () -> Unit)

/** Glass dropdown menu anchored to its parent Box. */
@Composable
fun AppMenu(expanded: Boolean, onDismiss: () -> Unit, items: List<AppMenuItem>, modifier: Modifier = Modifier) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(surface = p.raised, surfaceContainer = p.raised),
        shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(if (design.isSanctuary) 10.dp else 22.dp))
    ) {
        DropdownMenu(expanded = expanded, onDismissRequest = onDismiss, modifier = modifier.widthIn(min = 220.dp)) {
            items.forEachIndexed { i, item ->
                Row(
                    Modifier.fillMaxWidth().heightIn(min = 48.dp)
                        .clickable { item.onClick(); onDismiss() }
                        .tag(item.tag).semantics { selected = item.selected }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(item.label, style = styles.body, color = p.textPrimary, modifier = Modifier.weight(1f))
                    if (item.icon != null) {
                        Spacer(Modifier.width(12.dp))
                        Icon(item.icon, null, tint = if (item.selected) p.accent else p.textSecondary, modifier = Modifier.size(20.dp))
                    }
                }
                if (i < items.lastIndex) Box(Modifier.fillMaxWidth().height(0.5.dp).background(p.separator))
            }
        }
    }
}

/** Chip that opens an [AppMenu] (label + chevron down), e.g. "Misterios Luminosos ⌄". */
@Composable
fun AppMenuChip(label: String, items: List<AppMenuItem>, modifier: Modifier = Modifier, icon: ImageVector? = null, tag: String? = null) {
    val p = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    var open by remember { mutableStateOf(false) }
    Box(modifier) {
        Row(
            Modifier.heightIn(min = 44.dp).lumenPress({ open = true }, 0.96f)
                .lumenGlass(LocalAppDesign.current.shapes.button, strong = true)
                .tag(tag).padding(horizontal = 16.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(icon, null, tint = p.textPrimary, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(label, style = styles.subhead.copy(fontWeight = FontWeight.Medium), color = p.textPrimary, maxLines = appMaxLines(1))
            Spacer(Modifier.width(6.dp))
            Icon(Icons.Outlined.KeyboardArrowDown, null, tint = p.textSecondary, modifier = Modifier.size(18.dp))
        }
        AppMenu(open, { open = false }, items)
    }
}

/**
 * "Región: Todas ⌄" style single-chip dropdown replacing a long horizontally scrolling chip row.
 * [prefix] is the label before the selected option (e.g. "Región"); gutter margins included.
 */
@Composable
fun AppFilterMenu(
    prefix: String,
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tag: String? = null
) {
    val gutter = LocalAppDesign.current.spacing.gutter
    val current = options.getOrElse(selectedIndex) { options.firstOrNull().orEmpty() }
    Box(modifier.fillMaxWidth().padding(horizontal = gutter, vertical = 4.dp)) {
        AppMenuChip(
            label = "$prefix: $current",
            items = options.mapIndexed { i, o -> AppMenuItem(o, selected = i == selectedIndex) { onSelect(i) } },
            tag = tag
        )
    }
}

/** Glass confirmation dialog; [destructive] tints the confirm label red. */
@Composable
fun AppConfirmDialog(
    title: String,
    message: String?,
    confirmLabel: String,
    dismissLabel: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    destructive: Boolean = false,
    tag: String? = null
) {
    if (appHasCompactHeight()) {
        AppDialog(onDismiss = onDismiss, modifier = Modifier.widthIn(max = 420.dp), title = title, tag = tag, footer = {
            AppSecondaryButton(dismissLabel, onDismiss, modifier = Modifier.weight(1f).tag(tag?.let { "${it}_dismiss" }))
            Box(Modifier.weight(1f).heightIn(min = 48.dp).clickable(role = Role.Button, onClick = onConfirm)
                .tag(tag?.let { "${it}_confirm" }), contentAlignment = Alignment.Center) {
                Text(confirmLabel, style = LocalAppTextStyles.current.headline,
                    color = if (destructive) Color(0xFFD93A2B) else LocalAppDesign.current.palette.textPrimary)
            }
        }) {
            if (message != null) Text(message, style = LocalAppTextStyles.current.subhead,
                color = LocalAppDesign.current.palette.textSecondary, modifier = Modifier.padding(horizontal = 24.dp))
        }
        return
    }
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier.fillMaxWidth().appFloatingPanel(design.shapes.dialog).tag(tag).padding(top = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = styles.headline, color = p.textPrimary, textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 24.dp))
            if (message != null) {
                Spacer(Modifier.height(8.dp))
                Text(message, style = styles.subhead, color = p.textSecondary, textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 24.dp))
            }
            Spacer(Modifier.height(20.dp))
            Box(Modifier.fillMaxWidth().height(0.5.dp).background(p.separator))
            Row(Modifier.fillMaxWidth().height(52.dp)) {
                Box(Modifier.weight(1f).fillMaxSize().clickable(onClick = onDismiss).tag(tag?.let { "${it}_dismiss" }), contentAlignment = Alignment.Center) {
                    Text(dismissLabel, style = styles.body, color = p.textPrimary)
                }
                Box(Modifier.width(0.5.dp).fillMaxSize().background(p.separator))
                Box(Modifier.weight(1f).fillMaxSize().clickable(onClick = onConfirm).tag(tag?.let { "${it}_confirm" }), contentAlignment = Alignment.Center) {
                    Text(confirmLabel, style = styles.headline, color = if (destructive) Color(0xFFD93A2B) else p.textPrimary)
                }
            }
        }
    }
}

// ------------------------------------------------------- progress & art

/** Segmented progress (e.g. rosary decades): [current] segment partly filled by [fraction]. */
@Composable
fun AppSegmentedProgress(count: Int, current: Int, fraction: Float, modifier: Modifier = Modifier, height: Dp = 4.dp, gap: Dp = 4.dp) {
    val p = LocalAppDesign.current.palette
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(gap)) {
        repeat(count.coerceAtLeast(1)) { i ->
            val fill = when {
                i < current -> 1f
                i == current -> fraction.coerceIn(0f, 1f)
                else -> 0f
            }
            AppProgressBar(fill, Modifier.weight(1f), height = height)
        }
    }
}

/** Hero art that crossfades when [assetPath] changes (mystery paintings). */
@Composable
fun AppCrossfadeArt(assetPath: String?, modifier: Modifier = Modifier, fadeToCanvas: Boolean = true, fadeStart: Float = 0.45f) {
    Crossfade(targetState = assetPath, animationSpec = tween(if (LocalAppDesign.current.reduceMotion) 0 else 450), label = "art", modifier = modifier) { path ->
        AppHeroImage(path, Modifier.fillMaxSize(), fadeToCanvas = fadeToCanvas, fadeStart = fadeStart)
    }
}

/** Remote art with a bundled fallback while loading or on failure. */
@Composable
fun AppRemoteImage(imageUrl: String?, fallbackAssetPath: String?, modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.Crop, alignment: Alignment = Alignment.TopCenter) {
    val fallback = rememberAssetBitmap(fallbackAssetPath, 720)
    val painter = fallback?.let { BitmapPainter(it) }
    if (imageUrl.isNullOrBlank()) {
        if (fallback != null) androidx.compose.foundation.Image(fallback, null, modifier, contentScale = contentScale, alignment = rememberHeroMeta(fallbackAssetPath).alignment)
        return
    }
    AsyncImage(model = imageUrl, contentDescription = null, placeholder = painter, error = painter, fallback = painter,
        contentScale = contentScale, alignment = alignment, modifier = modifier)
}

// ------------------------------------------------------ reading settings

/** The preferences store, for shared settings sheets. Provided by MainApp. */
val LocalPreferencesStore = staticCompositionLocalOf<UserPreferencesStore?> { null }

/**
 * The shared "Aa" reading sheet: text size and reader font, persisted in
 * [UserPreferencesStore] and applied app-wide (Bible, Catechism, prayers).
 */
@Composable
fun AppReadingSettingsSheet(
    currentScale: Float,
    currentFontId: String,
    onDismiss: () -> Unit,
    labels: ReadingSheetLabels = ReadingSheetLabels()
) {
    val store = LocalPreferencesStore.current
    val scope = rememberCoroutineScope()
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    AppSheet(onDismiss = onDismiss, title = labels.title, tag = "reading_settings_sheet") {
        Text(labels.size, style = styles.footnote, color = p.textSecondary, modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp))
        Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(0.9f, 1.0f, 1.15f, 1.3f).forEachIndexed { i, s ->
                val sel = kotlin.math.abs(currentScale - s) < 0.03f
                Box(
                    Modifier.weight(1f).height(52.dp).lumenPress({ scope.launch2 { store?.setFontSizeScale(s) } }, 0.95f)
                        .then(if (sel) Modifier.background(p.action, RoundedCornerShape(16.dp)) else Modifier.lumenGlass(RoundedCornerShape(16.dp), lifted = false))
                        .testTag("reading_size_$i"),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Aa", style = styles.body.copy(fontSize = styles.body.fontSize * (0.8f + i * 0.17f)), color = if (sel) p.onAction else p.textPrimary)
                }
            }
        }
        Spacer(Modifier.height(18.dp))
        Text(labels.font, style = styles.footnote, color = p.textSecondary, modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp))
        AppListGroup {
            val fonts = com.example.data.models.FontChoice.entries
            fonts.forEachIndexed { i, f ->
                AppListRow(
                    title = if (f == com.example.data.models.FontChoice.IDENTITY_DEFAULT) labels.defaultFont else f.familyName,
                    selected = f.id == currentFontId,
                    trailingText = if (f.id == currentFontId) "✓" else null,
                    showsNavigationAffordance = false,
                    onClick = { scope.launch2 { store?.setFontFamilyChoice(f.id) } },
                    modifier = Modifier.testTag("reading_font_${f.id}")
                )
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}

data class ReadingSheetLabels(
    val title: String = "Texto",
    val size: String = "Tamaño",
    val font: String = "Tipografía",
    val defaultFont: String = "Predeterminada"
)

private fun kotlinx.coroutines.CoroutineScope.launch2(block: suspend () -> Unit) {
    launch { block() }
}

/** Small delay helper for staged entrances. */
suspend fun lumenSettle(ms: Long = 16) = delay(ms)

/**
 * Legacy AppDialog callers already end in an action Row/Column. In compact
 * height that final direct child stays fixed; preceding choices scroll, without
 * moving their composition or hiding their semantics under the action group.
 */
@Composable
private fun AppPinnedDialogContent(modifier: Modifier = Modifier, pinLast: Boolean = true, scrollEnabled: Boolean = true, content: @Composable () -> Unit) {
    var offset by remember { androidx.compose.runtime.mutableFloatStateOf(0f) }
    var limit by remember { androidx.compose.runtime.mutableFloatStateOf(0f) }
    val scroll = androidx.compose.foundation.gestures.rememberScrollableState { delta ->
        val before = offset
        offset = (offset - delta).coerceIn(0f, limit)
        before - offset
    }
    androidx.compose.ui.layout.Layout(content = content, modifier = modifier.clipToBounds()
        .then(if (scrollEnabled) Modifier.scrollable(scroll, androidx.compose.foundation.gestures.Orientation.Vertical)
        .semantics {
            verticalScrollAxisRange = androidx.compose.ui.semantics.ScrollAxisRange({ offset }, { limit })
            scrollBy { _, y -> offset = (offset + y).coerceIn(0f, limit); true }
        } else Modifier)) { nodes, constraints ->
        val loose = constraints.copy(minHeight = 0, maxHeight = androidx.compose.ui.unit.Constraints.Infinity)
        val places = nodes.map { it.measure(loose) }
        val footer = if (pinLast) places.lastOrNull() else null
        val body = if (pinLast) places.dropLast(1) else places
        val natural = places.sumOf { it.height }
        val h = constraints.constrainHeight(natural)
        val bodyHeight = (h - (footer?.height ?: 0)).coerceAtLeast(0)
        limit = (body.sumOf { it.height } - bodyHeight).coerceAtLeast(0).toFloat()
        layout(constraints.maxWidth, h) {
            var y = if (scrollEnabled) -offset.coerceIn(0f, limit).toInt() else 0
            body.forEach { item ->
                val position = y
                item.placeRelativeWithLayer(0, position) {
                    clip = true
                    shape = object : androidx.compose.ui.graphics.Shape {
                        override fun createOutline(size: androidx.compose.ui.geometry.Size, layoutDirection: androidx.compose.ui.unit.LayoutDirection,
                            density: androidx.compose.ui.unit.Density): androidx.compose.ui.graphics.Outline =
                            androidx.compose.ui.graphics.Outline.Rectangle(androidx.compose.ui.geometry.Rect(0f, (-position).coerceIn(0, item.height).toFloat(),
                                size.width, (bodyHeight - position).coerceIn(0, item.height).toFloat().coerceAtLeast((-position).coerceIn(0, item.height).toFloat())))
                    }
                }
                y += item.height
            }
            footer?.placeRelative(0, h - footer.height)
        }
    }
}

/** The caller places this in the active column's overlay, above its fixed footer. */
@Composable
fun AppNotice(text: String, modifier: Modifier = Modifier, footerHeight: Dp = 0.dp) {
    val safe = appSafeInsets()
    val density = androidx.compose.ui.platform.LocalDensity.current
    val ime = with(density) { WindowInsets.ime.getBottom(this).toDp() }
    val window = appWindowSize()
    var hostBottomGap by remember { mutableStateOf(0.dp) }
    var hostEndGap by remember { mutableStateOf(0.dp) }
    val p = LocalAppDesign.current.palette
    Box(modifier.fillMaxSize().onGloballyPositioned {
        val bounds = it.boundsInWindow()
        with(density) {
            hostBottomGap = (window.height - bounds.bottom.toDp()).coerceAtLeast(0.dp)
            hostEndGap = (window.width - bounds.right.toDp()).coerceAtLeast(0.dp)
        }
    }, contentAlignment = Alignment.BottomEnd) {
        Text(text, style = com.example.ui.theme.LocalAppTextStyles.current.subhead, color = p.textPrimary,
            modifier = Modifier.padding(end = maxOf(12.dp, safe.end - hostEndGap + 8.dp),
                bottom = (maxOf(ime, safe.bottom + footerHeight) - hostBottomGap).coerceAtLeast(0.dp) + 8.dp)
                .widthIn(max = 480.dp).appContentSurface(LocalAppDesign.current.shapes.buttonSmall)
                .semantics { liveRegion = androidx.compose.ui.semantics.LiveRegionMode.Polite }.padding(16.dp))
    }
}
