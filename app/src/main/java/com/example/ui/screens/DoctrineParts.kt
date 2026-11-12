package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.Stop
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.ui.design.AppChevron
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.GroupPosition
import com.example.ui.design.appScreenInsets
import com.example.ui.design.groupedItem
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.design.softShadow
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// -----------------------------------------------------------------------------
// Shared building blocks of the Doctrine & Study area (all destinations).
// -----------------------------------------------------------------------------

/** Bottom padding that clears the floating dock, provided once by the screen. */
internal val LocalBottomPad = compositionLocalOf { 24.dp }

/** Gutter shortcut. */
@Composable
internal fun gutter(): Dp = LocalAppDesign.current.spacing.gutter

// ---------------------------------------------------------------- navigation

/** What every page needs to move around and to leave the area. */
@Stable
internal class DoctrineNav(
    val open: (String) -> Unit,
    val replace: (String) -> Unit,
    val openBible: (bookId: String, chapter: Int, verseStart: Int?) -> Unit,
    val openCatechism: (paragraph: Int) -> Unit,
    val openPrayer: (prayerId: String) -> Unit,
    val voice: ReaderVoice
)

// --------------------------------------------------------------------- voice

/**
 * The area's one read-aloud engine. It is created the first time a reader
 * page is shown (so list pages never start a speech engine), survives
 * Previous/Next, and is released with the screen.
 */
@Stable
internal class ReaderVoice(private val context: Context) {
    private var manager: LiturgicalTtsManager? = null
    private var scope: CoroutineScope? = null
    var playing by mutableStateOf(false)
        private set
    var language: AppLanguage = AppLanguage.ES

    fun prepare() {
        if (manager == null) {
            val m = LiturgicalTtsManager(context)
            manager = m
            val s = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
            scope = s
            s.launch { m.isPlaying.collect { playing = it } }
        }
        manager?.setLanguage(language)
    }

    fun speak(text: String) {
        prepare()
        manager?.speakSingle(text)
    }

    fun stop() {
        manager?.stop()
    }

    fun release() {
        scope?.cancel()
        scope = null
        manager?.release()
        manager = null
        playing = false
    }
}

internal fun sharePlainText(context: Context, text: String) {
    runCatching {
        context.startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                },
                null
            )
        )
    }
}

/** Opens [uri] in whatever handles it, reporting whether anything did. */
internal fun Context.openUri(uri: String): Boolean =
    runCatching { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri))) }.isSuccess

// ------------------------------------------------------------ async bitmaps

private object AssetBitmaps {
    private val cache = object : android.util.LruCache<String, ImageBitmap>(8 * 1024 * 1024) {
        override fun sizeOf(key: String, value: ImageBitmap) = value.width * value.height * 4
    }

    fun peek(key: String): ImageBitmap? = cache.get(key)

    fun load(context: Context, path: String, maxWidthPx: Int): ImageBitmap? {
        val key = "$path@$maxWidthPx"
        cache.get(key)?.let { return it }
        val decoded = runCatching {
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            context.assets.open(path).use { BitmapFactory.decodeStream(it, null, bounds) }
            if (bounds.outWidth <= 0) return null
            var sample = 1
            while (bounds.outWidth / (sample * 2) >= maxWidthPx) sample *= 2
            val opts = BitmapFactory.Options().apply { inSampleSize = sample }
            context.assets.open(path).use { BitmapFactory.decodeStream(it, null, opts) }?.asImageBitmap()
        }.getOrNull() ?: return null
        cache.put(key, decoded)
        return decoded
    }
}

/**
 * Decodes a bundled image off the main thread and caches it. In inspection
 * mode (previews and the screenshot renders) it decodes inline so a frame is
 * never captured half-loaded.
 */
@Composable
internal fun rememberAsyncAsset(path: String, maxWidthPx: Int = 256): ImageBitmap? {
    val context = LocalContext.current.applicationContext
    val inspection = LocalInspectionMode.current
    val key = "$path@$maxWidthPx"
    val state = produceState<ImageBitmap?>(
        initialValue = AssetBitmaps.peek(key) ?: if (inspection) AssetBitmaps.load(context, path, maxWidthPx) else null,
        key1 = key
    ) {
        if (value == null) {
            value = withContext(Dispatchers.Default) { AssetBitmaps.load(context, path, maxWidthPx) }
        }
    }
    return state.value
}

/**
 * A religious order's emblem on an ivory plate. The plate stays light in both
 * modes so black line-art and white-boxed logos read as enamel medals.
 */
@Composable
internal fun EmblemPlate(
    orderId: String,
    size: Dp,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tag: String? = null
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val radius = minOf(20.dp, size * 0.28f)
    val shape = RoundedCornerShape(radius)
    val bitmap = rememberAsyncAsset("orders/$orderId.png", 256)
    Box(
        modifier
            .size(size)
            .softShadow(shape, p.shadow, 14.dp, 4.dp)
            .clip(shape)
            .background(if (design.isDark) Color(0xFFECE9E5) else Color(0xFFF6F4F1))
            .border(1.dp, Brush.verticalGradient(listOf(p.glassEdgeTop, p.glassEdgeBottom)), shape)
            .then(if (tag != null) Modifier.testTag(tag) else Modifier)
            .padding(size * 0.14f),
        contentAlignment = Alignment.Center
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = contentDescription,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

// ---------------------------------------------------------------------- text

/** Paragraphs are separated by blank lines throughout the data. */
internal fun splitParagraphs(text: String): List<String> =
    text.split(Regex("\\n\\s*\\n")).map { it.trim() }.filter { it.isNotEmpty() }

/** Long-form text in the reader font, on the canvas itself. */
@Composable
internal fun ReadingParagraphs(
    text: String,
    modifier: Modifier = Modifier,
    lead: Boolean = false
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val paragraphs = remember(text) { splitParagraphs(text) }
    Column(
        modifier
            .padding(horizontal = design.spacing.gutter)
            .widthIn(max = design.spacing.readingMaxWidth)
    ) {
        paragraphs.forEachIndexed { i, paragraph ->
            if (i > 0) Spacer(Modifier.height(16.dp))
            val style = if (lead && i == 0) {
                styles.reading.copy(
                    fontSize = styles.reading.fontSize * 1.06f,
                    lineHeight = styles.reading.lineHeight * 1.06f
                )
            } else styles.reading
            Text(paragraph, style = style, color = design.palette.textPrimary)
        }
    }
}

/** A scripture pull quote: reading italic with a quiet bar at the leading edge. */
@Composable
internal fun PullQuote(text: String, modifier: Modifier = Modifier) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Row(
        modifier
            .padding(horizontal = design.spacing.gutter)
            .widthIn(max = design.spacing.readingMaxWidth)
            .height(IntrinsicSize.Min)
    ) {
        Box(
            Modifier
                .width(2.dp)
                .fillMaxHeight()
                .background(design.palette.inkQuiet, RoundedCornerShape(1.dp))
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text,
            style = styles.reading.copy(fontStyle = FontStyle.Italic),
            color = design.palette.textPrimary,
            modifier = Modifier.weight(1f)
        )
    }
}

// --------------------------------------------------------------------- cards

@Composable
internal fun GlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    AppGlassSurface(
        modifier
            .padding(horizontal = design.spacing.gutter)
            .fillMaxWidth(),
        onClick = onClick,
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Column(content = content)
    }
}

/** A headline label over a callout paragraph, in one glass card. */
@Composable
internal fun NoteCard(label: String, body: String, modifier: Modifier = Modifier, quote: Boolean = false) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    GlassCard(modifier) {
        Text(label, style = styles.headline, color = design.palette.textPrimary, modifier = Modifier.semantics { heading() })
        Spacer(Modifier.height(8.dp))
        Text(
            body,
            style = if (quote) styles.reading.copy(fontStyle = FontStyle.Italic) else styles.callout,
            color = design.palette.textPrimary
        )
    }
}

/** Stacked label/value rows in one glass group. Rows with blank values are skipped. */
@Composable
internal fun FactsGroup(rows: List<Pair<String, String>>, modifier: Modifier = Modifier, tag: String? = null) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val visible = rows.filter { it.second.isNotBlank() }
    if (visible.isEmpty()) return
    val shape = design.shapes.listGroup
    Column(
        modifier
            .padding(horizontal = design.spacing.gutter)
            .fillMaxWidth()
            .lumenGlass(shape)
            .clip(shape)
            .then(if (tag != null) Modifier.testTag(tag) else Modifier)
    ) {
        visible.forEachIndexed { i, (label, value) ->
            Column(Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 14.dp)) {
                Text(label, style = styles.footnote, color = design.palette.textSecondary)
                Spacer(Modifier.height(3.dp))
                Text(value, style = styles.body, color = design.palette.textPrimary)
            }
            if (i < visible.lastIndex) {
                Box(Modifier.fillMaxWidth().padding(start = 18.dp).height(0.5.dp).background(design.palette.separator))
            }
        }
    }
}

// --------------------------------------------------------------------- rows

/** A numeral or letter in a small glass circle. */
@Composable
internal fun NumeralCircle(text: String, modifier: Modifier = Modifier, size: Dp = 32.dp) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Box(
        modifier.size(size).lumenGlass(CircleShape, lifted = false),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            style = styles.footnote.copy(fontFeatureSettings = "tnum"),
            color = design.palette.textPrimary,
            maxLines = 1
        )
    }
}

/**
 * A list row with an optional leading slot, usable inside an `AppListGroup`
 * ([position] null) or as a lazy item of a group split across items.
 */
@Composable
internal fun LeadRow(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leading: (@Composable () -> Unit)? = null,
    leadingWidth: Dp = 32.dp,
    trailingText: String? = null,
    onClick: (() -> Unit)? = null,
    position: GroupPosition? = null,
    tag: String? = null,
    maxTitleLines: Int = 3
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val base = if (position != null) Modifier.groupedItem(position) else Modifier.fillMaxWidth()
    Column(modifier.then(base).then(if (tag != null) Modifier.testTag(tag) else Modifier)) {
        Row(
            Modifier
                .fillMaxWidth()
                .lumenPress(onClick, 0.99f)
                .heightIn(min = design.spacing.rowMinHeight)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leading != null) {
                Box(Modifier.width(leadingWidth), contentAlignment = Alignment.Center) { leading() }
                Spacer(Modifier.width(14.dp))
            }
            Column(Modifier.weight(1f)) {
                Text(title, style = styles.body, color = p.textPrimary, maxLines = maxTitleLines, overflow = TextOverflow.Ellipsis)
                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(subtitle, style = styles.footnote, color = p.textSecondary, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
            if (trailingText != null) {
                Spacer(Modifier.width(10.dp))
                Text(
                    trailingText,
                    style = styles.footnote.copy(fontFeatureSettings = "tnum"),
                    color = p.textSecondary,
                    maxLines = 1
                )
            }
            if (onClick != null) {
                Spacer(Modifier.width(10.dp))
                AppChevron()
            }
        }
        if (position == null || position == GroupPosition.First || position == GroupPosition.Middle) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp + if (leading != null) leadingWidth + 14.dp else 0.dp)
                    .height(0.5.dp)
                    .background(p.separator)
            )
        }
    }
}

/** Position of item [i] of [n] in a group split across lazy items. */
internal fun groupPosition(i: Int, n: Int): GroupPosition = when {
    n <= 1 -> GroupPosition.Single
    i == 0 -> GroupPosition.First
    i == n - 1 -> GroupPosition.Last
    else -> GroupPosition.Middle
}

@Composable
internal fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    trailing: String? = null,
    topGap: Dp = 28.dp
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(top = topGap, bottom = 12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            title,
            style = styles.title3,
            color = design.palette.textPrimary,
            modifier = Modifier.weight(1f).semantics { heading() }
        )
        if (trailing != null) {
            Spacer(Modifier.width(12.dp))
            Text(trailing, style = styles.footnote, color = design.palette.textSecondary)
        }
    }
}

/** A footnote line under a group, in the gutter. */
@Composable
internal fun FootnoteText(text: String, modifier: Modifier = Modifier) {
    val design = LocalAppDesign.current
    Text(
        text,
        style = LocalAppTextStyles.current.footnote,
        color = design.palette.textSecondary,
        modifier = modifier.padding(horizontal = design.spacing.gutter + 4.dp, vertical = 8.dp)
    )
}

// ---------------------------------------------------------- reference chips

/** A glass capsule with a leading glyph. Selected = action fill. */
@Composable
internal fun RefChip(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tag: String? = null
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val shape = RoundedCornerShape(percent = 50)
    Row(
        modifier
            .heightIn(min = 44.dp)
            .lumenPress(onClick, 0.96f, Role.Button)
            .then(if (selected) Modifier.softShadow(shape, p.shadow, 16.dp, 4.dp) else Modifier.lumenGlass(shape, lifted = false))
            .clip(shape)
            .background(if (selected) p.action else Color.Transparent)
            .semantics { this.selected = selected }
            .then(if (tag != null) Modifier.testTag(tag) else Modifier)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(icon, null, tint = if (selected) p.onAction else p.textSecondary, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text(
            label,
            style = styles.subhead.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium),
            color = if (selected) p.onAction else p.textPrimary,
            maxLines = 1
        )
    }
}

// ----------------------------------------------------------------- headers

/**
 * The art-less header of every reading page: eyebrow, optional thin progress,
 * title (title1, or title2 for very long questions) and a meta line. Clears
 * the status bar and the single glass back button.
 */
@Composable
internal fun ReadingHeader(
    title: String,
    modifier: Modifier = Modifier,
    eyebrow: String? = null,
    meta: String? = null,
    progress: Float? = null,
    progressLabel: String? = null,
    progressTag: String? = null,
    titleTag: String? = null,
    leading: (@Composable () -> Unit)? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val top = appScreenInsets().calculateTopPadding()
    Column(
        modifier
            .fillMaxWidth()
            .padding(top = top + 8.dp)
            .padding(horizontal = design.spacing.gutter)
    ) {
        if (leading != null) {
            leading()
            Spacer(Modifier.height(16.dp))
        }
        if (eyebrow != null) {
            Text(eyebrow, style = styles.eyebrow, color = p.textSecondary)
            Spacer(Modifier.height(8.dp))
        }
        if (progress != null) {
            AppProgressBar(
                progress,
                modifier = Modifier
                    .semantics { if (progressLabel != null) stateDescription = progressLabel }
                    .then(if (progressTag != null) Modifier.testTag(progressTag) else Modifier),
                height = 3.dp
            )
            Spacer(Modifier.height(14.dp))
        }
        Text(
            title,
            style = if (title.length > 100) styles.title2 else styles.title1,
            color = p.textPrimary,
            modifier = Modifier.semantics { heading() }.then(if (titleTag != null) Modifier.testTag(titleTag) else Modifier)
        )
        if (meta != null) {
            Spacer(Modifier.height(8.dp))
            Text(meta, style = styles.subhead, color = p.textSecondary)
        }
    }
}

/** The Listen capsule and the Share circle, directly under a reading title. */
@Composable
internal fun ReaderActionRow(
    language: AppLanguage,
    voice: ReaderVoice,
    speakText: String,
    shareText: String?,
    listenTag: String,
    shareTag: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Row(
        modifier.padding(horizontal = gutter()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val playing = voice.playing
        AppSecondaryCapsule(
            label = if (playing) language.tr("Detener", "Stop", "Parar") else language.tr("Escuchar", "Listen", "Ouvir"),
            icon = if (playing) Icons.Outlined.Stop else Icons.Outlined.VolumeUp,
            onClick = { if (playing) voice.stop() else voice.speak(speakText) },
            tag = listenTag
        )
        if (shareText != null && shareTag != null) {
            AppGlassIconButton(
                icon = Icons.Outlined.IosShare,
                contentDescription = language.tr("Compartir", "Share", "Partilhar"),
                onClick = { sharePlainText(context, shareText) },
                tag = shareTag
            )
        }
    }
}

/**
 * End of a sequence reader: a glass row naming the next item (the primary
 * target) and a quieter row for the previous one. Missing ends are hidden.
 */
@Composable
internal fun SequenceFooter(
    language: AppLanguage,
    nextTitle: String?,
    prevTitle: String?,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    nextTag: String,
    prevTag: String,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(
        modifier.padding(horizontal = design.spacing.gutter),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (nextTitle != null) {
            AppGlassSurface(
                Modifier.fillMaxWidth().testTag(nextTag),
                shape = design.shapes.listGroup,
                onClick = onNext,
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(language.tr("Siguiente", "Next", "Seguinte"), style = styles.caption, color = p.textSecondary)
                        Spacer(Modifier.height(2.dp))
                        Text(nextTitle, style = styles.headline, color = p.textPrimary, maxLines = 3, overflow = TextOverflow.Ellipsis)
                    }
                    Spacer(Modifier.width(12.dp))
                    AppChevron()
                }
            }
        }
        if (prevTitle != null) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
                    .lumenPress(onPrev, 0.98f)
                    .semantics { contentDescription = language.tr("Anterior", "Previous", "Anterior") + ": " + prevTitle }
                    .testTag(prevTag)
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.AutoMirrored.Outlined.ArrowBackIos, null, tint = p.textSecondary, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(10.dp))
                Text(
                    language.tr("Anterior", "Previous", "Anterior") + " · " + prevTitle,
                    style = styles.callout,
                    color = p.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// -------------------------------------------------------------------- pages

/**
 * The scrolling frame of a reading page: [ReadingHeader] first, then
 * [content], the optional [footer], and the dock clearance.
 */
@Composable
internal fun ReaderPage(
    title: String,
    modifier: Modifier = Modifier,
    eyebrow: String? = null,
    meta: String? = null,
    progress: Float? = null,
    progressLabel: String? = null,
    progressTag: String? = null,
    pageTag: String = "doctrine_entry_page",
    leading: (@Composable () -> Unit)? = null,
    content: LazyListScope.() -> Unit
) {
    val state: LazyListState = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = modifier.fillMaxSize().testTag(pageTag),
        contentPadding = PaddingValues(bottom = LocalBottomPad.current)
    ) {
        item(key = "reader_header", contentType = "header") {
            ReadingHeader(
                title = title,
                eyebrow = eyebrow,
                meta = meta,
                progress = progress,
                progressLabel = progressLabel,
                progressTag = progressTag,
                leading = leading
            )
            Spacer(Modifier.height(18.dp))
        }
        content()
    }
}

/** Vertical gap between blocks of a reading page. */
@Composable
internal fun BlockGap(height: Dp = 24.dp) {
    Spacer(Modifier.height(height))
}
