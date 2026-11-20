package com.example.ui.design

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.runtime.remember
import kotlin.math.roundToInt
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

// -----------------------------------------------------------------------------
// Lumen components. See docs/redesign/COMPONENTS.md for when to use each.
// -----------------------------------------------------------------------------

private fun Modifier.tagged(tag: String?): Modifier = if (tag == null) this else testTag(tag)

// ------------------------------------------------------------------ glass

/** A frosted glass container. Defaults to the card shape. */
@Composable
fun AppGlassSurface(
    modifier: Modifier = Modifier,
    shape: Shape? = null,
    strong: Boolean = false,
    lifted: Boolean = true,
    onClick: (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable BoxScope.() -> Unit
) {
    val s = shape ?: LocalAppDesign.current.shapes.card
    Box(
        modifier
            .lumenPress(onClick, 0.98f)
            .then(if (strong) Modifier.lumenGlass(s, strong = true, lifted = lifted) else Modifier.appContentSurface(s))
            .clip(s)
            .padding(contentPadding),
        content = content
    )
}

/**
 * The 44dp glass circle used for back, close, search, bell, bookmark and more
 * (48dp hit area). In the scaffold's chrome it blurs what scrolls beneath it;
 * [onImage] keeps the denser glass for legibility over art.
 */
@Composable
fun AppGlassIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onImage: Boolean = false,
    tint: Color? = null,
    tag: String? = null
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val live = LocalLiquidBackdrop.current != null
    Box(
        modifier
            .size(48.dp)
            .lumenPress(onClick, 0.9f)
            .semantics { if (contentDescription != null) this.contentDescription = contentDescription }
            .tagged(tag),
        contentAlignment = Alignment.Center
    ) {
        Box(Modifier.size(44.dp), contentAlignment = Alignment.Center) {
            LiquidBackdropFill(CircleShape, Modifier.matchParentSize(), blur = 16.dp)
            Box(
                Modifier.matchParentSize()
                    // Over art in the dark, a faint backing keeps the glass from
                    // disappearing into a bright painting.
                    .then(
                        if (design.isDark && onImage && !design.opaqueMaterial()) Modifier.background(Color.Black.copy(alpha = 0.20f), CircleShape)
                        else Modifier
                    )
                    // No ambient lens: these sit over paintings as often as over
                    // the light, and must show whatever is really behind them.
                    .liquidPane(CircleShape, design.chromeGlassSpec(live).copy(lens = 0f))
            )
            Icon(icon, contentDescription = null, tint = tint ?: p.textPrimary, modifier = Modifier.size(22.dp))
        }
    }
}

/**
 * A small bead of accent-tinted glass that carries a row's or card's glyph.
 * Sanctuary keeps the bare engraved glyph; Tradicional a plain neutral one.
 */
@Composable
fun AppIconWell(icon: ImageVector, modifier: Modifier = Modifier, tint: Color? = null, size: Dp = 36.dp) {
    val design = LocalAppDesign.current
    val p = design.palette
    if (design.isSanctuary) {
        Icon(icon, null, tint = tint ?: p.textPrimary, modifier = modifier.size(24.dp))
        return
    }
    if (design.isClassic) {
        // Tradicional: a plain neutral line glyph, no tinted bead.
        Box(modifier.size(size), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = tint ?: p.textSecondary, modifier = Modifier.size(24.dp))
        }
        return
    }
    val ink = tint ?: p.accent
    val shape = RoundedCornerShape(size * 0.32f)
    Box(
        modifier.size(size)
            .background(Brush.verticalGradient(listOf(ink.copy(alpha = if (design.isDark) 0.24f else 0.16f), ink.copy(alpha = if (design.isDark) 0.14f else 0.09f))), shape)
            .then(if (design.opaqueMaterial()) Modifier else Modifier.border(1.dp, Brush.verticalGradient(listOf(
                Color.White.copy(alpha = if (design.isDark) 0.20f else 0.80f),
                Color.White.copy(alpha = if (design.isDark) 0.03f else 0.20f))), shape)),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, null, tint = ink, modifier = Modifier.size(size * 0.56f))
    }
}

// ----------------------------------------------------------------- headers

/**
 * A 4x5 colour matrix: saturate by [saturation], then scale each channel by its
 * gain and add an offset (0..255 scale). Used to grade art toward the canvas.
 */
private fun gradeMatrix(
    saturation: Float,
    gainR: Float, gainG: Float, gainB: Float,
    offR: Float, offG: Float, offB: Float
): ColorMatrix {
    val lr = 0.2126f
    val lg = 0.7152f
    val lb = 0.0722f
    val s = saturation
    val i = 1f - s
    return ColorMatrix(
        floatArrayOf(
            (i * lr + s) * gainR, i * lg * gainR, i * lb * gainR, 0f, offR,
            i * lr * gainG, (i * lg + s) * gainG, i * lb * gainG, 0f, offG,
            i * lr * gainB, i * lg * gainB, (i * lb + s) * gainB, 0f, offB,
            0f, 0f, 0f, 1f, 0f
        )
    )
}

/**
 * Bundled art treated so it belongs to the canvas.
 *
 * Light: lifted toward the paper and 15% desaturated, then dissolved into the
 * canvas over the lower part. Dark ("night chapel"): about -0.6 EV on bright
 * art with a warm grade (+R / -B) and less saturation, a black scrim over the
 * top 120dp (status bar legibility) and a long fade so the lower 45% reaches
 * >= 85% canvas before any text line. Art whose manifest sets `zoom` is
 * cropped tighter around its focus point. Fills its box.
 */
@Composable
fun AppHeroImage(
    assetPath: String?,
    modifier: Modifier = Modifier,
    fadeToCanvas: Boolean = true,
    fadeStart: Float = 0.45f,
    topVeil: Boolean = true,
    maxWidthPx: Int = 1080
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val bitmap = rememberAssetBitmap(assetPath, maxWidthPx)
    val meta = rememberHeroMeta(assetPath)
    val filter = remember(design.isDark, design.isSanctuary, meta.tone) {
        val grade = if (design.isSanctuary) {
            gradeMatrix(0.94f, 0.96f, 0.93f, 0.88f, 3f, 2f, 0f)
        } else if (!design.isDark) {
            gradeMatrix(0.96f, 0.97f, 0.97f, 0.98f, 8f, 8f, 10f)
        } else if (meta.tone == "light") {
            // About -0.6 EV with a warm cast.
            gradeMatrix(0.78f, 0.52f, 0.50f, 0.50f, 2f, 2f, 4f)
        } else {
            gradeMatrix(0.88f, 0.84f, 0.82f, 0.84f, 1f, 1f, 3f)
        }
        ColorFilter.colorMatrix(grade)
    }
    val dissolve = fadeToCanvas && !design.opaqueMaterial()
    Box(modifier.clipToBounds()
        .then(if (dissolve) Modifier.fadeOutBottom(fadeStart) else Modifier.background(p.canvasTop))) {
        if (bitmap != null) {
            if (meta.zoom > 1.001f) {
                Canvas(Modifier.fillMaxSize()) {
                    val iw = bitmap.width.toFloat()
                    val ih = bitmap.height.toFloat()
                    val cover = maxOf(size.width / iw, size.height / ih)
                    val scale = cover * meta.zoom
                    val dw = iw * scale
                    val dh = ih * scale
                    val dx = (size.width * meta.anchorX - meta.focusX * dw).coerceIn(size.width - dw, 0f)
                    val dy = (size.height * meta.anchorY - meta.focusY * dh).coerceIn(size.height - dh, 0f)
                    drawImage(
                        image = bitmap,
                        srcOffset = IntOffset.Zero,
                        srcSize = IntSize(bitmap.width, bitmap.height),
                        dstOffset = IntOffset(dx.roundToInt(), dy.roundToInt()),
                        dstSize = IntSize(dw.roundToInt(), dh.roundToInt()),
                        colorFilter = filter,
                        filterQuality = FilterQuality.High
                    )
                }
            } else {
                Image(
                    bitmap = bitmap,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = meta.alignment,
                    colorFilter = filter,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        // Light mist: lift the art toward the paper so a saturated painting
        // never shouts. Dark needs none beyond the grade above.
        if (!design.isDark && !design.isSanctuary) {
            Box(Modifier.fillMaxSize().background(p.canvasTop.copy(alpha = 0.05f)))
        }
        if (topVeil) {
            Box(
                Modifier.fillMaxWidth().height(120.dp).background(
                    Brush.verticalGradient(
                        listOf(
                            if (design.isDark) Color.Black.copy(alpha = 0.35f) else p.canvasTop.copy(alpha = 0.6f),
                            Color.Transparent
                        )
                    )
                )
            )
        }
        if (fadeToCanvas && !dissolve) {
            val span = 1f - fadeStart
            Box(
                Modifier.fillMaxSize().background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        fadeStart to Color.Transparent,
                        (fadeStart + span * 0.30f) to p.canvasTop.copy(alpha = 0.50f),
                        (fadeStart + span * 0.58f) to p.canvasTop.copy(alpha = 0.72f),
                        (fadeStart + span * 0.86f) to p.canvasTop.copy(alpha = 0.82f),
                        1f to p.canvasTop
                    )
                )
            )
        }
    }
}

/**
 * The one header every screen opens with.
 *
 * Art runs from the very top of the display for exactly [AppHeaderArtHeight]
 * below the status bar and dissolves into the ambient light; the eyebrow,
 * title and meta sit in one block on the same bottom line, on every screen.
 * Only the title's rank changes: `largeTitle` on a section root,
 * `title1` on anything deeper. A long title grows the header upward, never
 * moves the line it sits on.
 */
val AppHeaderArtHeight: Dp = 236.dp

/**
 * Whether header art softens its side edges. Null: whenever the window is wide
 * (the page is a centred column). A two-pane screen sets it per pane.
 */
internal val LocalHeroSideFade = androidx.compose.runtime.compositionLocalOf<Boolean?> { null }

/** Bottom inset of the header's text block; with [AppHeaderArtHeight] it fixes the title line. */
private val HeaderTextBottom: Dp = 14.dp

@Composable
private fun AppHeaderFrame(
    title: String,
    root: Boolean,
    edgeToEdge: Boolean,
    modifier: Modifier = Modifier,
    assetPath: String? = null,
    imageUrl: String? = null,
    eyebrow: String? = null,
    meta: String? = null,
    metaIcon: ImageVector? = null,
    titleTag: String? = null,
    overlap: Dp = 0.dp,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val statusTop = statusBarHeight()
    // Where this screen's content starts: 0 when it draws edge to edge, else
    // below the status bar and back row the scaffold already cleared.
    val contentTop = if (edgeToEdge) 0.dp else (LocalScreenFrame.current?.insets?.calculateTopPadding() ?: 0.dp)
    val hasArt = assetPath != null || imageUrl != null
    val largeText = styles.eyebrow.fontSize.value * androidx.compose.ui.platform.LocalDensity.current.fontScale >= 20f
    // A section root takes `largeTitle`; when one word of it is wider than the page at this text size («Recordatorios» at 130 %),
    // it takes the next rank down (`title1`, still scaled) rather than being broken letter by letter.
    val measurer = androidx.compose.ui.text.rememberTextMeasurer()
    val titleDensity = androidx.compose.ui.platform.LocalDensity.current
    val pageWidthDp = androidx.compose.ui.platform.LocalConfiguration.current.screenWidthDp
    val titleStyle = if (!root) styles.title1 else {
        val gutter = design.spacing.gutter
        val widestWord = title.split(' ', '\n', '\u00A0').maxByOrNull { it.length }.orEmpty()
        val fits = remember(widestWord, styles.largeTitle, pageWidthDp) {
            widestWord.isEmpty() || measurer.measure(widestWord, styles.largeTitle, softWrap = false, maxLines = 1).size.width <=
                with(titleDensity) { (pageWidthDp.dp - gutter * 2 - 4.dp).toPx() }
        }
        if (fits) styles.largeTitle else styles.title1
    }

    val metaLine: @Composable (Modifier) -> Unit = { m ->
        if (meta != null) Row(m, verticalAlignment = Alignment.CenterVertically) {
            if (metaIcon != null) {
                Icon(metaIcon, null, tint = p.textSecondary, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(meta, style = styles.subhead, color = p.textSecondary)
        }
    }
    val density = androidx.compose.ui.platform.LocalDensity.current
    val text: @Composable (Modifier) -> Unit = { m ->
        Column(m) {
            // On paper (title at the top) the eyebrow's line is always reserved,
            // so titles share one line whether or not a screen has an eyebrow.
            if (eyebrow == null && design.isSanctuary && root) {
                Spacer(Modifier.height(with(density) { styles.eyebrow.lineHeight.toDp() } + 4.dp))
            }
            if (eyebrow != null) {
                Text(
                    eyebrow, style = styles.eyebrow,
                    color = if (design.isDark && hasArt && !design.isSanctuary) p.textPrimary.copy(alpha = 0.84f) else p.textSecondary,
                    maxLines = appMaxLines(2), overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
            }
            // Never truncated: a long title grows the header upward.
            Text(title, style = titleStyle, color = p.textPrimary,
                modifier = Modifier.semantics { heading() }.tagged(titleTag))
        }
    }

    // Sanctuary and very large type: the title on paper, the art in a ruled frame beneath it.
    if (design.isSanctuary || largeText || !hasArt) {
        Column(modifier.fillMaxWidth().padding(top = (if (edgeToEdge) maxOf(statusTop, LocalScreenFrame.current?.insets?.calculateTopPadding() ?: statusTop) else 0.dp) + 8.dp)) {
            Row(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
                verticalAlignment = Alignment.CenterVertically) {
                text(Modifier.weight(1f))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp), content = actions)
            }
            metaLine(Modifier.padding(horizontal = design.spacing.gutter).padding(top = 6.dp))
            if (hasArt) {
                Spacer(Modifier.height(14.dp))
                Box(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter)
                    .height(if (largeText) 132.dp else 168.dp)
                    .then(if (design.isSanctuary) Modifier.border(0.75.dp, p.hairline, design.shapes.thumbnail).padding(5.dp) else Modifier)
                    .clip(design.shapes.thumbnail)) {
                    if (imageUrl.isNullOrBlank() && assetPath != null) AppHeroImage(assetPath, Modifier.fillMaxSize(), fadeToCanvas = false, topVeil = false)
                    else AppRemoteImage(imageUrl, assetPath, Modifier.fillMaxSize(), alignment = Alignment.TopCenter)
                }
            }
            Spacer(Modifier.height(if (hasArt) 20.dp else 14.dp))
        }
        return
    }

    Column(modifier.fillMaxWidth()) {
    Box(
        Modifier
            .fillMaxWidth()
            // The overlap trim is applied OUTSIDE the height: inside it, the
            // parent would centre the shorter box and leave a band above the art.
            .then(
                if (overlap > 0.dp) Modifier.layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    layout(placeable.width, (placeable.height - overlap.roundToPx()).coerceAtLeast(0)) {
                        placeable.place(0, 0)
                    }
                } else Modifier
            )
            .heightIn(min = statusTop + AppHeaderArtHeight - contentTop)
    ) {
        // On a wide window the page is a centred column: its art melts into the canvas at the sides instead of ending in hard edges.
        val fadeSides = LocalHeroSideFade.current ?: appUsesNavigationRail()
        Box(Modifier.matchParentSize().then(if (fadeSides) Modifier.fadeSides() else Modifier)) {
            if (imageUrl.isNullOrBlank() && assetPath != null) {
                AppHeroImage(assetPath, Modifier.fillMaxSize(), fadeStart = 0.30f)
            } else {
                // Remote art, with the bundled painting while it loads or if it fails.
                Box(Modifier.fillMaxSize().fadeOutBottom(0.30f)) {
                    AppRemoteImage(imageUrl, assetPath, Modifier.fillMaxSize(), alignment = Alignment.TopCenter)
                    if (design.isDark) Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.18f)))
                    Box(Modifier.fillMaxWidth().height(120.dp).background(Brush.verticalGradient(listOf(
                        if (design.isDark) Color.Black.copy(alpha = 0.4f) else p.canvasTop.copy(alpha = 0.55f), Color.Transparent))))
                }
            }
        }
        // 44dp glass circles, level with the scaffold's back button.
        Row(
            Modifier.align(Alignment.TopEnd)
                .padding(top = (statusTop - contentTop).coerceAtLeast(0.dp) + 2.dp, end = design.spacing.gutter - 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = actions
        )
        text(
            Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = design.spacing.gutter)
                // Keeps a long title clear of the back button: the header grows instead.
                .padding(top = (statusTop - contentTop).coerceAtLeast(0.dp) + 60.dp, bottom = HeaderTextBottom + overlap)
        )
    }
    // The meta line hangs under the title line, so it never moves the title.
    // (Pulled 8dp up into the title's bottom inset, taking that much less room.)
    metaLine(Modifier.padding(horizontal = design.spacing.gutter).layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val lift = 8.dp.roundToPx()
        layout(placeable.width, (placeable.height - lift).coerceAtLeast(0)) { placeable.place(0, -lift) }
    })
    }
}

/**
 * Section-root header (a dock tab or a destination opened from Explore): the
 * shared art frame with the [title] in `largeTitle`. Declares the screen
 * edge-to-edge; put it first in the screen's scroll content. [height] is
 * ignored: every header has the same art height ([AppHeaderArtHeight]).
 */
@Composable
fun AppHeroHeader(
    assetPath: String?,
    title: String,
    modifier: Modifier = Modifier,
    eyebrow: String? = null,
    @Suppress("UNUSED_PARAMETER") height: Dp = AppHeaderArtHeight,
    titleTag: String? = null,
    /**
     * How far the next sibling (e.g. the season card) rides up over the hero's
     * fade. The hero reports that much less height and lifts its title clear.
     */
    overlap: Dp = 0.dp,
    meta: String? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    AppEdgeToEdge()
    AppHeaderFrame(title, root = true, edgeToEdge = true, modifier = modifier, assetPath = assetPath,
        eyebrow = eyebrow, meta = meta, titleTag = titleTag, overlap = overlap, actions = actions)
}

/**
 * Detail header (anything below a section root): the shared art frame with
 * the [title] in `title1`, an optional [eyebrow] and [meta] line. The back
 * button is the scaffold's single glass back control — register the in-screen
 * back with [AppBackHandler]. [actions] (bookmark, more) sit top-right.
 * With an [imageUrl], [assetPath] is the bundled fallback shown while it
 * loads. [imageHeight] is ignored: every header has the same art height.
 */
@Composable
fun AppDetailHeader(
    title: String,
    modifier: Modifier = Modifier,
    assetPath: String? = null,
    imageUrl: String? = null,
    eyebrow: String? = null,
    meta: String? = null,
    metaIcon: ImageVector? = null,
    @Suppress("UNUSED_PARAMETER") imageHeight: Dp = AppHeaderArtHeight,
    titleTag: String? = null,
    /** True on a section root that uses this entry point: the title takes `largeTitle`. */
    root: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val edgeToEdge = appScreenInsets().calculateTopPadding() > 0.dp
    AppHeaderFrame(title, root = root, edgeToEdge = edgeToEdge, modifier = modifier, assetPath = assetPath,
        imageUrl = imageUrl, eyebrow = eyebrow, meta = meta, metaIcon = metaIcon, titleTag = titleTag, actions = actions)
}

/**
 * Section-root title without art: the same block and rank as [AppHeroHeader],
 * for the rare root that has no picture. Clears the status bar itself on an
 * edge-to-edge screen.
 */
@Composable
fun AppLargeTitleHeader(
    title: String,
    modifier: Modifier = Modifier,
    eyebrow: String? = null,
    titleTag: String? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val edgeToEdge = appScreenInsets().calculateTopPadding() > 0.dp
    AppHeaderFrame(title, root = true, edgeToEdge = edgeToEdge, modifier = modifier,
        eyebrow = eyebrow, titleTag = titleTag, actions = actions)
}

// ------------------------------------------------------------------ cards

/** A small solid dot, e.g. the liturgical season colour (flat: no gradient, no highlight). */
@Composable
fun AppSeasonDot(color: Color, modifier: Modifier = Modifier, size: Dp = 8.dp) {
    Box(modifier.size(size).clip(CircleShape).background(color))
}

/** The thin disclosure chevron used across rows and cards (7x12 glyph, tertiary ink). */
@Composable
fun AppChevron(modifier: Modifier = Modifier, tint: Color? = null) {
    Icon(
        Icons.AutoMirrored.Outlined.ArrowForwardIos,
        contentDescription = null,
        tint = tint ?: LocalAppDesign.current.palette.textSecondary.copy(alpha = 0.75f),
        modifier = modifier.size(14.dp)
    )
}

/**
 * The liturgical season as a compact glass row (about 68dp): a flat 8dp dot
 * centred on the cap height of the season name (headline), the week or
 * feast on one footnote line beneath it, chevron.
 */
@Composable
fun AppSeasonCard(
    season: String,
    detail: String?,
    seasonColor: Color,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    tag: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    AppGlassSurface(
        modifier.fillMaxWidth().tagged(tag),
        shape = design.shapes.listGroup,
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppSeasonDot(seasonColor)
                    Spacer(Modifier.width(10.dp))
                    Text(season, style = styles.headline, color = p.textPrimary, maxLines = appMaxLines(1), overflow = TextOverflow.Ellipsis)
                }
                if (detail != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        detail,
                        style = styles.footnote,
                        color = p.textSecondary,
                        maxLines = appMaxLines(1),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 18.dp)
                    )
                }
            }
            if (onClick != null) {
                Spacer(Modifier.width(12.dp))
                AppChevron(tint = p.inkQuiet)
            }
        }
    }
}

/**
 * A glass card with a leading outline [icon], [title] / [subtitle] heading,
 * free [content] below and an optional [trailing] control (e.g.
 * [AppRoundPlayButton]) aligned with the heading.
 */
@Composable
fun AppContentCard(
    title: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null,
    tag: String? = null,
    trailing: (@Composable () -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    AppGlassSurface(
        modifier.fillMaxWidth().tagged(tag),
        onClick = onClick,
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) {
                    AppIconWell(icon)
                    Spacer(Modifier.width(14.dp))
                }
                Column(Modifier.weight(1f)) {
                    Text(title, style = styles.headline, color = p.textPrimary)
                    if (subtitle != null) {
                        Spacer(Modifier.height(2.dp))
                        Text(subtitle, style = styles.subhead, color = p.textSecondary)
                    }
                }
                if (trailing != null) {
                    Spacer(Modifier.width(12.dp))
                    trailing()
                }
            }
            if (content != null) {
                Spacer(Modifier.height(14.dp))
                content()
            }
        }
    }
}

/** Round glass play/pause button (40dp) for cards. */
@Composable
fun AppRoundPlayButton(
    playing: Boolean,
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tag: String? = null
) {
    AppGlassIconButton(
        icon = if (playing) Icons.Filled.Pause else Icons.Filled.PlayArrow,
        contentDescription = contentDescription,
        onClick = onClick,
        modifier = modifier,
        tag = tag
    )
}

/** A 3-up category tile: outline glyph over a caption, glass. */
@Composable
fun AppCategoryTile(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    tag: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val shape = design.shapes.listGroup
    Box(
        modifier
            .heightIn(min = 104.dp)
            .lumenPress(onClick, 0.96f)
            .then(
                if (selected) Modifier.liquidPane(shape, design.tintedGlassSpec(p.action))
                else Modifier.appContentSurface(shape)
            )
            .clip(shape)
            .semantics { this.selected = selected }
            .tagged(tag)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, null, tint = if (selected) p.onAction else p.textPrimary, modifier = Modifier.size(28.dp))
            Spacer(Modifier.height(12.dp))
            Text(
                label,
                style = styles.caption,
                color = if (selected) p.onAction else p.textPrimary,
                maxLines = appMaxLines(2),
                overflow = TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

/**
 * Lays [items] out in a non-lazy grid of [columns] equal cells with the
 * gutter around it — for tiles and image cards inside a scrolling column.
 */
@Composable
fun <T> AppGrid(
    items: List<T>,
    modifier: Modifier = Modifier,
    columns: Int = 3,
    spacing: Dp = 12.dp,
    cell: @Composable (item: T, modifier: Modifier) -> Unit
) {
    val gutter = LocalAppDesign.current.spacing.gutter
    // Large text: three narrow tiles would break their captions letter by letter, so the grid keeps two across.
    val cols = if (appLargeText() && columns > 2) 2 else columns
    Column(modifier.fillMaxWidth().padding(horizontal = gutter), verticalArrangement = Arrangement.spacedBy(spacing)) {
        items.chunked(cols).forEach { rowItems ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(spacing)) {
                rowItems.forEach { item -> cell(item, Modifier.weight(1f)) }
                repeat(cols - rowItems.size) { Spacer(Modifier.weight(1f)) }
            }
        }
    }
}

/**
 * Art-led card: an image (bundled [assetPath] or remote [imageUrl]) filling
 * the card, a bottom scrim, [title] and [meta] in white at the bottom-start,
 * optional [progress]. Use 2-up in [AppGrid] (novenas) or full width.
 */
@Composable
fun AppImageCard(
    title: String,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    assetPath: String? = null,
    imageUrl: String? = null,
    eyebrow: String? = null,
    meta: String? = null,
    progress: Float? = null,
    aspectRatio: Float = 0.8f,
    tag: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val shape = if (design.isSanctuary) design.shapes.card else RoundedCornerShape(24.dp)
    val fallback = rememberAssetBitmap(assetPath, 720)
    val meta2 = rememberHeroMeta(assetPath)
    Box(
        modifier
            // The art ratio is a minimum: with large text the card grows instead of cutting its title.
            .layout { measurable, c ->
                val minHeight = if (c.hasBoundedWidth) Math.round(c.maxWidth / aspectRatio).coerceAtMost(c.maxHeight) else 0
                val placeable = measurable.measure(c.copy(minHeight = maxOf(c.minHeight, minHeight)))
                layout(placeable.width, maxOf(placeable.height, minHeight)) { placeable.place(0, 0) }
            }
            .lumenPress(onClick, 0.97f)
            .softShadow(shape, p.shadow.copy(alpha = if (design.isDark) 0.45f else 0.16f), 26.dp, 10.dp)
            .clip(shape)
            // Before the art lands (or where there is none): deep glass in the accent's hue.
            .background(Brush.linearGradient(listOf(
                lerp(p.accent, Color(0xFF0B1022), if (design.isDark) 0.62f else 0.38f),
                lerp(p.accent, Color(0xFF0B1022), 0.86f))))
            .tagged(tag)
    ) {
        when {
            imageUrl != null -> AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier.matchParentSize()
            )
            fallback != null -> Image(
                bitmap = fallback,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = meta2.alignment,
                modifier = Modifier.matchParentSize()
            )
        }
        Box(
            Modifier.matchParentSize().background(
                Brush.verticalGradient(
                    0f to Color.Transparent,
                    0.42f to Color.Transparent,
                    1f to Color.Black.copy(alpha = 0.68f)
                )
            )
        )
        Box(Modifier.matchParentSize().border(1.dp, Brush.linearGradient(listOf(
            Color.White.copy(alpha = if (design.isDark) 0.30f else 0.70f),
            Color.White.copy(alpha = if (design.isDark) 0.04f else 0.12f),
            Color.White.copy(alpha = if (design.isDark) 0.12f else 0.32f))), shape))
        Column(Modifier.align(Alignment.BottomStart).padding(14.dp)) {
            if (eyebrow != null) {
                Text(eyebrow, style = styles.caption, color = Color.White.copy(alpha = 0.85f), maxLines = appMaxLines(1))
                Spacer(Modifier.height(2.dp))
            }
            Text(
                title,
                style = styles.headline.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium),
                color = Color.White,
                maxLines = appMaxLines(3),
                overflow = TextOverflow.Ellipsis
            )
            if (meta != null) {
                Spacer(Modifier.height(4.dp))
                Text(meta, style = styles.caption, color = Color.White.copy(alpha = 0.85f), maxLines = appMaxLines(1))
            }
            if (progress != null) {
                Spacer(Modifier.height(8.dp))
                AppProgressBar(progress, color = Color.White, track = Color.White.copy(alpha = 0.3f), height = 3.dp)
            }
        }
    }
}

// ---------------------------------------------------------------- controls

/** Horizontal capsule filter chips with the gutter as their margins. */
@Composable
fun AppFilterChips(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tagFor: ((Int) -> String)? = null
) {
    val design = LocalAppDesign.current
    Row(
        modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = design.spacing.gutter, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEachIndexed { i, label ->
            AppKitChip(label, i == selectedIndex, { onSelect(i) }, Modifier.tagged(tagFor?.invoke(i)))
        }
    }
}

/** One capsule chip: clear glass, accent-tinted glass when selected. */
@Composable
fun AppKitChip(label: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val shape = design.shapes.button
    val ink by animateColorAsState(if (selected) p.onAction else p.textPrimary, tween(design.motion.fastMillis), label = "chip")
    Box(
        modifier
            .heightIn(min = 48.dp)
            .lumenPress(onClick, 0.95f, Role.Tab)
            .then(
                if (selected) Modifier.liquidPane(shape, design.tintedGlassSpec(p.action).copy(shadowBlur = 16.dp, shadowY = 5.dp))
                else Modifier.lumenGlass(shape, lifted = false)
            )
            .clip(shape)
            .semantics { this.selected = selected }
            .padding(horizontal = 18.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            label,
            style = styles.subhead.copy(fontWeight = if (selected) androidx.compose.ui.text.font.FontWeight.SemiBold else androidx.compose.ui.text.font.FontWeight.Medium),
            color = ink,
            maxLines = appMaxLines(1)
        )
    }
}

/** The primary action: a 52dp capsule of accent-tinted glass. */
@Composable
fun AppPrimaryCapsule(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    fillWidth: Boolean = true,
    tag: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val shape = design.shapes.button
    Row(
        modifier
            .then(if (fillWidth) Modifier.fillMaxWidth() else Modifier)
            .heightIn(min = 52.dp)
            .lumenPress(onClick, 0.97f, enabled = enabled)
            .liquidPane(shape, design.tintedGlassSpec(p.action, enabled))
            .tagged(tag)
            .padding(horizontal = 26.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(icon, null, tint = p.onAction, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
        }
        Text(label, style = styles.buttonLabel, color = p.onAction, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}

/** Secondary action: a glass capsule with ink label. */
@Composable
fun AppSecondaryCapsule(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    fillWidth: Boolean = false,
    tag: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val shape = design.shapes.button
    Row(
        modifier
            .then(if (fillWidth) Modifier.fillMaxWidth() else Modifier)
            .heightIn(min = 50.dp)
            .lumenPress(onClick, 0.97f, enabled = enabled)
            .then(
                if (design.opaqueMaterial()) Modifier
                    .background(if (design.isDark) Color.White.copy(alpha = 0.12f) else Color.Black.copy(alpha = 0.05f), shape)
                    .then(if (design.isDark) Modifier.border(1.dp,
                        Brush.verticalGradient(listOf(Color.White.copy(alpha = 0.14f), Color.White.copy(alpha = 0.04f))), shape) else Modifier)
                else Modifier.liquidPane(shape, design.glassSpec(GlassRole.Control).copy(shadowBlur = 14.dp, shadowY = 4.dp))
            )
            .tagged(tag)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val ink = if (enabled) p.textPrimary else p.inkQuiet
        if (icon != null) {
            Icon(icon, null, tint = ink, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
        }
        Text(label, style = styles.buttonLabel.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium), color = ink, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}

/**
 * Glass transport capsule: previous / play-pause (larger, denser glass) /
 * next. Null [onPrevious]/[onNext] disables that side.
 */
@Composable
fun AppTransportBar(
    isPlaying: Boolean,
    onPlayPause: () -> Unit,
    modifier: Modifier = Modifier,
    onPrevious: (() -> Unit)? = null,
    onNext: (() -> Unit)? = null,
    previousLabel: String = "Previous",
    nextLabel: String = "Next",
    playLabel: String = "Play",
    pauseLabel: String = "Pause",
    tagPrefix: String = "transport"
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val shape = design.shapes.button
    Row(
        modifier
            .lumenGlass(shape, strong = true)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        TransportGlyph(Icons.Filled.SkipPrevious, previousLabel, onPrevious, "${tagPrefix}_previous")
        Box(
            Modifier
                .size(60.dp)
                .lumenPress(onPlayPause, 0.92f)
                .semantics { contentDescription = if (isPlaying) pauseLabel else playLabel }
                .testTag("${tagPrefix}_play")
                .liquidPane(CircleShape, design.tintedGlassSpec(p.action)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                null,
                tint = p.onAction,
                modifier = Modifier.size(30.dp)
            )
        }
        TransportGlyph(Icons.Filled.SkipNext, nextLabel, onNext, "${tagPrefix}_next")
    }
}

@Composable
private fun TransportGlyph(icon: ImageVector, label: String, onClick: (() -> Unit)?, tag: String) {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .size(48.dp)
            .lumenPress(onClick ?: {}, 0.9f, enabled = onClick != null)
            .semantics { contentDescription = label }
            .testTag(tag),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, null, tint = if (onClick != null) p.textPrimary else p.inkQuiet, modifier = Modifier.size(26.dp))
    }
}

// ---------------------------------------------------------------- progress

/** Gradient progress ring with free [center] content. */
@Composable
fun AppProgressRing(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = 168.dp,
    stroke: Dp = 12.dp,
    center: @Composable BoxScope.() -> Unit = {}
) {
    val p = LocalAppDesign.current.palette
    val animated by animateFloatAsState(progress.coerceIn(0f, 1f), tween(if (LocalAppDesign.current.reduceMotion) 0 else 600), label = "ring")
    val trackColor = if (LocalAppDesign.current.isDark) Color.White.copy(alpha = 0.14f) else Color.Black.copy(alpha = 0.13f)
    Box(modifier.size(size), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val w = stroke.toPx()
            val inset = w / 2f
            val arcSize = androidx.compose.ui.geometry.Size(this.size.width - w, this.size.height - w)
            drawArc(
                color = trackColor,
                startAngle = 0f, sweepAngle = 360f, useCenter = false,
                topLeft = androidx.compose.ui.geometry.Offset(inset, inset), size = arcSize,
                style = Stroke(w)
            )
            if (animated > 0f) {
                drawArc(
                    brush = Brush.sweepGradient(
                        0f to p.ringStart, animated to p.ringEnd, 1f to p.ringEnd,
                        center = androidx.compose.ui.geometry.Offset(this.size.width / 2, this.size.height / 2)
                    ),
                    startAngle = -90f, sweepAngle = 360f * animated, useCenter = false,
                    topLeft = androidx.compose.ui.geometry.Offset(inset, inset), size = arcSize,
                    style = Stroke(w, cap = StrokeCap.Round)
                )
            }
        }
        center()
    }
}

/** Thin rounded progress bar. */
@Composable
fun AppProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color? = null,
    track: Color? = null,
    height: Dp = 4.dp
) {
    val p = LocalAppDesign.current.palette
    val animated by animateFloatAsState(progress.coerceIn(0f, 1f), tween(if (LocalAppDesign.current.reduceMotion) 0 else 400), label = "bar")
    Box(
        modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(percent = 50))
            .background(track ?: p.separator.copy(alpha = (p.separator.alpha * 1.8f).coerceAtMost(1f)))
    ) {
        Box(
            Modifier
                .fillMaxWidth(animated)
                .height(height)
                .clip(RoundedCornerShape(percent = 50))
                .background(color ?: p.accent)
        )
    }
}

// ------------------------------------------------------------- list idioms

/**
 * A habit/checklist row: circle check (success fill when done) toggled by
 * the circle, the label opening [onOpen] when given. Put rows in an
 * [AppListGroup] (they draw their own inset separators).
 */
@Composable
fun AppCheckRow(
    label: String,
    checked: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    trailingText: String? = null,
    onOpen: (() -> Unit)? = null,
    showDivider: Boolean = true,
    tag: String? = null,
    checkTag: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val fill by animateColorAsState(if (checked) p.success else Color.Transparent, tween(design.motion.fastMillis), label = "check")
    Column(modifier.fillMaxWidth().tagged(tag)) {
        Row(
            Modifier.fillMaxWidth().heightIn(min = 56.dp).padding(start = 12.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(48.dp)
                    .lumenPress(onToggle, 0.88f, Role.Checkbox)
                    .semantics {
                        this.selected = checked
                        toggleableState = if (checked) ToggleableState.On else ToggleableState.Off
                        contentDescription = label
                    }
                    .tagged(checkTag),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(fill)
                        .border(1.5.dp, if (checked) p.success else p.inkQuiet, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (checked) Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(17.dp))
                }
            }
            Spacer(Modifier.width(6.dp))
            Row(
                Modifier.weight(1f).lumenPress(onOpen, 0.99f).padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(label, style = styles.body, color = p.textPrimary)
                    if (subtitle != null) {
                        Text(subtitle, style = styles.footnote, color = p.textSecondary)
                    }
                }
                if (trailingText != null) {
                    Spacer(Modifier.width(8.dp))
                    Text(trailingText, style = styles.footnote, color = p.textSecondary, maxLines = appMaxLines(1))
                }
                if (onOpen != null) {
                    Spacer(Modifier.width(8.dp))
                    AppChevron()
                }
            }
        }
        if (showDivider) {
            Box(Modifier.fillMaxWidth().padding(start = 62.dp).height(0.5.dp).background(p.separator))
        }
    }
}

/** Numbered steps (How to pray): glass number circles and step text. */
@Composable
fun AppStepList(
    steps: List<String>,
    modifier: Modifier = Modifier,
    currentStep: Int? = null,
    onStepClick: ((Int) -> Unit)? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    Column(modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        steps.forEachIndexed { i, step ->
            val active = currentStep == i
            Row(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 52.dp)
                    .lumenPress(onStepClick?.let { { it(i) } }, 0.98f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .size(34.dp)
                        .then(if (active) Modifier.liquidPane(CircleShape, design.tintedGlassSpec(p.action, glow = false)) else Modifier.lumenGlass(CircleShape, lifted = false)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("${i + 1}", style = styles.subhead, color = if (active) p.onAction else p.textPrimary)
                }
                Spacer(Modifier.width(16.dp))
                Text(step, style = styles.body, color = p.textPrimary, modifier = Modifier.weight(1f))
            }
        }
    }
}

/**
 * A glass capsule of icon actions (share, listen, text size) — the bottom
 * action row of a prayer or reading.
 */
@Composable
fun AppActionBar(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    val shape = LocalAppDesign.current.shapes.button
    Row(
        modifier.lumenGlass(shape, strong = true).padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

/** One icon action inside [AppActionBar] (48dp target). */
@Composable
fun AppActionBarItem(icon: ImageVector, contentDescription: String, onClick: () -> Unit, tag: String? = null, active: Boolean = false) {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .size(52.dp)
            .lumenPress(onClick, 0.9f)
            .semantics { this.contentDescription = contentDescription }
            .tagged(tag),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, null, tint = if (active) p.accent else p.textPrimary, modifier = Modifier.size(24.dp))
    }
}

/** Blend helper for screens: [a] toward [b] by [t]. */
fun lumenMix(a: Color, b: Color, t: Float): Color = lerp(a, b, t)
