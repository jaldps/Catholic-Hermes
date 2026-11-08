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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
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
    /** Bottom inset that clears the floating dock (or the nav bar when hidden). */
    var dockInset by mutableStateOf(0.dp)
    val dockHidden: Boolean get() = dockHiders > 0
    val titleOverride: String? get() = titles.lastOrNull()
}

val LocalChrome = staticCompositionLocalOf<ChromeState?> { null }

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
 * Glass background for one row of a group split across lazy items: only the
 * outer corners are rounded, and no shadow (it would seam between items).
 * Applies the gutter.
 */
@Composable
fun Modifier.groupedItem(position: GroupPosition): Modifier {
    val design = LocalAppDesign.current
    val r = 20.dp
    val shape = when (position) {
        GroupPosition.First -> RoundedCornerShape(topStart = r, topEnd = r)
        GroupPosition.Middle -> RoundedCornerShape(0.dp)
        GroupPosition.Last -> RoundedCornerShape(bottomStart = r, bottomEnd = r)
        GroupPosition.Single -> RoundedCornerShape(r)
    }
    return this
        .fillMaxWidth()
        .padding(horizontal = design.spacing.gutter)
        .clip(shape)
        .background(design.palette.glass)
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
                Icon(leadingIcon, null, tint = if (selected) p.accent else p.textPrimary, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(16.dp))
            }
            Column(Modifier.weight(1f)) {
                Text(title, style = styles.body, color = if (selected) p.accent else p.textPrimary)
                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(subtitle, style = styles.footnote, color = p.textSecondary)
                }
            }
            if (trailingText != null) {
                Spacer(Modifier.width(10.dp))
                Text(trailingText, style = styles.subhead, color = p.textSecondary, maxLines = 1,
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
                Modifier.fillMaxWidth().padding(start = 18.dp + if (leadingIcon != null) 38.dp else 0.dp)
                    .height(0.5.dp).background(p.separator)
            )
        }
    }
}

// ------------------------------------------------------------------ toggles

/** iOS-style switch: success green when on. */
@Composable
fun AppSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier, tag: String? = null) {
    val p = LocalAppDesign.current.palette
    val track by animateColorAsState(if (checked) p.success else p.textPrimary.copy(alpha = 0.14f), tween(180), label = "switch")
    val knob by animateDpAsState(if (checked) 20.dp else 0.dp, tween(180), label = "knob")
    Box(
        modifier
            .size(width = 51.dp, height = 31.dp)
            .clip(RoundedCornerShape(percent = 50))
            .background(track)
            .clickable(remember { MutableInteractionSource() }, null, role = Role.Switch) { onCheckedChange(!checked) }
            .semantics { this.selected = checked; stateDescription = if (checked) "On" else "Off" }
            .tag(tag)
            .padding(2.dp)
    ) {
        Box(
            Modifier.offset(x = knob).size(27.dp)
                .softShadow(CircleShape, Color.Black.copy(alpha = 0.18f), 6.dp, 2.dp)
                .background(Color.White, CircleShape)
        )
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
                Icon(leadingIcon, null, tint = p.textPrimary, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(16.dp))
            }
            Column(Modifier.weight(1f)) {
                Text(title, style = styles.body, color = p.textPrimary)
                if (subtitle != null) Text(subtitle, style = styles.footnote, color = p.textSecondary)
            }
            Spacer(Modifier.width(12.dp))
            AppSwitch(checked, onCheckedChange, tag = switchTag)
        }
        if (showDivider) {
            Box(Modifier.fillMaxWidth().padding(start = 18.dp + if (leadingIcon != null) 38.dp else 0.dp)
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
    tag: String? = null
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val shape = RoundedCornerShape(percent = 50)
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
                modifier = Modifier.fillMaxWidth().tag(tag)
            )
        }
        if (value.isNotEmpty()) {
            Box(
                Modifier.size(40.dp).clickable(remember { MutableInteractionSource() }, null, role = Role.Button) { onValueChange("") }
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
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    var shown by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { shown = true }
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)
    ) {
        Box(Modifier.fillMaxSize()) {
            Box(
                Modifier.fillMaxSize().background(Color.Black.copy(alpha = if (design.isDark) 0.5f else 0.28f))
                    .clickable(remember { MutableInteractionSource() }, null) { onDismiss() }
            )
            AnimatedVisibility(
                visible = shown,
                enter = slideInVertically(tween(320)) { it / 3 } + fadeIn(tween(200)),
                exit = slideOutVertically { it / 3 } + fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                val shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                Column(
                    modifier.fillMaxWidth().heightIn(max = 680.dp)
                        .clip(shape)
                        .background(Brush.verticalGradient(listOf(p.canvasTop, p.canvasBottom)))
                        .border(1.dp, Brush.verticalGradient(listOf(p.glassEdgeTop, Color.Transparent)), shape)
                        .tag(tag)
                        .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 16.dp)
                ) {
                    Box(Modifier.fillMaxWidth().padding(top = 10.dp), contentAlignment = Alignment.Center) {
                        Box(Modifier.size(width = 38.dp, height = 5.dp).clip(RoundedCornerShape(percent = 50)).background(p.inkQuiet.copy(alpha = 0.5f)))
                    }
                    if (title != null) {
                        Text(title, style = styles.headline, color = p.textPrimary, textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(top = 14.dp, bottom = 8.dp))
                    }
                    Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(top = 8.dp), content = content)
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
        shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(18.dp))
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
            Modifier.heightIn(min = 40.dp).lumenPress({ open = true }, 0.96f)
                .lumenGlass(RoundedCornerShape(percent = 50), strong = true)
                .tag(tag).padding(horizontal = 16.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(icon, null, tint = p.textPrimary, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(label, style = styles.subhead.copy(fontWeight = FontWeight.Medium), color = p.textPrimary, maxLines = 1)
            Spacer(Modifier.width(6.dp))
            Icon(Icons.Outlined.KeyboardArrowDown, null, tint = p.textSecondary, modifier = Modifier.size(18.dp))
        }
        AppMenu(open, { open = false }, items)
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
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(p.raised).tag(tag).padding(top = 24.dp),
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
    Crossfade(targetState = assetPath, animationSpec = tween(450), label = "art", modifier = modifier) { path ->
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
