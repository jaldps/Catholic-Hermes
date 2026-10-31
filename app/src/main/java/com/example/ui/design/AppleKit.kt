package com.example.ui.design

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * The Cupertino identity: inset grouped lists on a grey canvas, half-pixel
 * hairlines, large left-aligned titles and a fixed tab bar. Nothing is
 * elevated — separation comes from the grouped background and the hairline,
 * exactly as iOS does it. The single exception is the lift under the selected
 * segment of a segmented control, which Apple itself draws.
 *
 * Three rules do most of the work here and are worth stating because they are
 * what the eye reads as "iOS" before it reads anything else:
 *
 *  * **Nothing ripples.** Every target is `indication = null`. Cells and
 *    tappable cards answer a touch with UIKit's flat grey wash; buttons and
 *    capsules fade toward ~70% under the finger, as UIButton highlights.
 *  * **The group ends at the group.** A table view never draws a separator
 *    under its last row, so [ListGroup] measures itself one physical pixel
 *    short and lets the trailing hairline fall outside its own clip.
 *  * **A leading glyph is a tile, not a picture.** The 29pt rounded-square
 *    icon well is the single most recognisable mark in iOS Settings, and the
 *    separator inset is measured from the text it pushes across, never from
 *    the edge of the card.
 */
object AppleKit : AppKit {

    /** 1 physical pixel at 2x — UIKit's separator weight. */
    private val HairlineWeight = 0.5.dp

    /** The 29pt icon well of an iOS Settings row. */
    private val IconWellSize = 28.dp
    private val IconWellShape = RoundedCornerShape(7.dp)
    private val IconWellGlyph = 17.dp

    /** Leading text inset of a grouped row, measured from the card edge. */
    private val RowTextInset = 16.dp

    /**
     * Marks the subtree inside a [Card] so a [SectionHeader] drops the gutter
     * and the section gap it would otherwise contribute — inside a card it is
     * a card header, not a table-view section header, and must align with the
     * card's own padding rather than add a second inset to it.
     */
    private val LocalInsideCard = compositionLocalOf { false }

    /** A target with no ripple. iOS does not ripple. */
    @Composable
    private fun Modifier.tap(onClick: (() -> Unit)?, enabled: Boolean = true): Modifier {
        val source = remember { MutableInteractionSource() }
        return if (onClick == null) {
            this
        } else {
            clickable(
                interactionSource = source,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
        }
    }

    /**
     * A target that reports its own press, for the controls UIKit highlights:
     * a button's fill and label fade to ~70% under the finger, a cell takes
     * the flat grey wash. Still no ripple.
     */
    @Composable
    private fun Modifier.pressable(
        source: MutableInteractionSource,
        onClick: (() -> Unit)?,
        enabled: Boolean = true
    ): Modifier = if (onClick == null) {
        this
    } else {
        clickable(interactionSource = source, indication = null, enabled = enabled, onClick = onClick)
    }

    /**
     * The fill behind filled controls and icon wells. iOS sets white on its
     * tint in both appearances; the dark-mode liturgical accent is a pastel
     * meant for text and glyphs, and filling a button with it forces a black
     * label, which is Material's tonal idiom, not Apple's. In dark mode a pale
     * accent is taken back down to a saturated mid-tone that carries white.
     */
    @Composable
    private fun accentFill(): Color {
        val design = LocalAppDesign.current
        val primary = MaterialTheme.colorScheme.primary
        return if (design.isDark && primary.luminance() > 0.25f) {
            // Same hue, re-struck at a saturated mid value (S .66, V .56) —
            // the way systemGreen stays vivid under a white label — rather
            // than darkened toward black, which only greys it to olive.
            val hsv = FloatArray(3)
            android.graphics.Color.colorToHSV(primary.toArgb(), hsv)
            Color(android.graphics.Color.HSVToColor(floatArrayOf(hsv[0], 0.66f, 0.56f)))
        } else {
            primary
        }
    }

    @Composable
    private fun onAccentFill(): Color {
        val design = LocalAppDesign.current
        val scheme = MaterialTheme.colorScheme
        return if (design.isDark && scheme.primary.luminance() > 0.25f) Color.White else scheme.onPrimary
    }

    /** tertiarySystemFill — the grey of a disabled filled button. */
    @Composable
    private fun disabledFill(): Color {
        val design = LocalAppDesign.current
        return if (design.isDark) {
            design.palette.selectionTint.copy(alpha = 0.7f)
        } else {
            design.palette.selectionTint.copy(alpha = 0.55f)
        }
    }

    /** A full-bleed half-pixel separator. */
    @Composable
    private fun Hairline(modifier: Modifier = Modifier) {
        val palette = LocalAppDesign.current.palette
        Box(modifier.fillMaxWidth().height(HairlineWeight).background(palette.hairline))
    }

    /**
     * The table-view separator: a hairline that starts after the leading
     * content rather than at the edge of the group.
     */
    @Composable
    private fun InsetHairline(modifier: Modifier = Modifier, leadingInset: Dp = RowTextInset) {
        val palette = LocalAppDesign.current.palette
        Row(modifier.fillMaxWidth().height(HairlineWeight)) {
            Spacer(Modifier.width(leadingInset))
            Box(Modifier.weight(1f).height(HairlineWeight).background(palette.hairline))
        }
    }

    /**
     * The disclosure indicator, drawn rather than borrowed. The Material
     * chevron is a thin, wide, square-capped glyph; UIKit's is a short,
     * steep, round-capped stroke at roughly semibold weight, and at 13dp tall
     * the difference between the two is the difference between a list that
     * looks like iOS and one that does not.
     */
    @Composable
    private fun Chevron(tint: Color, modifier: Modifier = Modifier, height: Dp = 13.dp) {
        Canvas(modifier.size(height * 0.58f, height)) {
            val stroke = 2.dp.toPx()
            val left = stroke / 2f
            val right = size.width - stroke / 2f
            val top = stroke / 2f
            val bottom = size.height - stroke / 2f
            val middle = size.height / 2f
            drawLine(tint, Offset(left, top), Offset(right, middle), stroke, StrokeCap.Round)
            drawLine(tint, Offset(right, middle), Offset(left, bottom), stroke, StrokeCap.Round)
        }
    }

    /** Bars sit a shade off the canvas so they read as bars, as UIKit's materials do. */
    @Composable
    private fun barColor(): Color {
        val design = LocalAppDesign.current
        return lerp(
            design.palette.canvas,
            design.palette.groupedSurface,
            if (design.isDark) 0.55f else 0.80f
        )
    }

    // ------------------------------------------------------------------ chrome

    @Composable
    override fun Scaffold(
        title: String,
        navItems: List<AppNavItem>,
        selectedNavIndex: Int,
        onNavSelected: (Int) -> Unit,
        actions: @Composable RowScope.() -> Unit,
        content: @Composable (PaddingValues) -> Unit
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        val bar = barColor()

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            containerColor = palette.canvas,
            topBar = {
                // A tab root in iOS opens on a large title, and at the scroll
                // edge its navigation bar is transparent, hairline-free and
                // carries no inline title: only the bar buttons. Every tab
                // here opens on AppScreenTitle, so an inline app name above it
                // was a second title — and an app name in the bar is the
                // Android toolbar habit, not a UINavigationBar one.
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(palette.canvas)
                        .statusBarsPadding()
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .heightIn(min = 44.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        content = actions
                    )
                }
            },
            bottomBar = {
                Surface(color = bar) {
                    Column(Modifier.navigationBarsPadding().testTag("bottom_nav_bar")) {
                        Hairline()
                        Row(
                            Modifier
                                .fillMaxWidth()
                                // 6 + 25 + 2 + label ≈ the 49pt UITabBar.
                                .padding(top = 6.dp, bottom = 6.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.Top
                        ) {
                            navItems.forEachIndexed { index, item ->
                                val isSelected = index == selectedNavIndex
                                // Unselected tab items are UIKit's secondary
                                // label, not its tertiary: at 30% the glyphs
                                // wash out into the bar.
                                val tint =
                                    if (isSelected) scheme.primary else palette.textSecondary
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .tap({ onNavSelected(index) })
                                        .padding(horizontal = 2.dp)
                                        .testTag(item.tag),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) item.selectedIcon else item.icon,
                                        contentDescription = item.label,
                                        tint = tint,
                                        modifier = Modifier.size(25.dp)
                                    )
                                    Spacer(Modifier.height(2.dp))
                                    // Ten destinations will not sit on one line
                                    // at tab-bar width; wrapping is the honest
                                    // tab-bar answer, not a scrolling rail.
                                    Text(
                                        text = item.label,
                                        style = styles.navLabel,
                                        color = tint,
                                        textAlign = TextAlign.Center,
                                        maxLines = 2
                                    )
                                }
                            }
                        }
                    }
                }
            },
            content = content
        )
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
        val scheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth().background(barColor())) {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 4.dp).heightIn(min = 44.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.width(44.dp)) {
                    if (onBack != null) {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.AutoMirrored.Outlined.ArrowBackIos,
                                contentDescription = null,
                                tint = scheme.primary,
                                modifier = Modifier.size(19.dp)
                            )
                        }
                    }
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = palette.textPrimary,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                    if (subtitle != null) {
                        // UIKit's two-line navigation title: a 12pt
                        // secondary line in sentence case under the 17pt title.
                        Text(
                            text = subtitle,
                            style = styles.rowSubtitle.copy(fontSize = styles.navLabel.fontSize * 1.2f),
                            color = palette.textSecondary,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
                Row(
                    Modifier.widthIn(min = 44.dp).wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }
            Hairline()
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
                .padding(top = design.spacing.headerTopGap, bottom = 10.dp),
            horizontalAlignment = design.decor.titleAlignment
        ) {
            Text(
                text = title,
                // SF tightens by roughly 26/1000 em at display sizes; the
                // scale's -0.6 is short of that at 34pt.
                style = styles.screenTitle.copy(letterSpacing = (-0.9).sp),
                color = palette.textPrimary
            )
            if (subtitle != null) {
                Spacer(Modifier.height(3.dp))
                Text(
                    text = subtitle,
                    style = styles.screenSubtitle,
                    color = palette.textSecondary
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
        val scheme = MaterialTheme.colorScheme
        val insideCard = LocalInsideCard.current
        if (insideCard) {
            // Health's card header: the card's subject in the tint at
            // subheadline semibold, sentence case, then the card's own title.
            // An all-caps grey eyebrow on top of a bold line is the Android
            // "overline" pattern; Apple apps don't letter-space shouting caps
            // inside a card.
            Column(modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                Text(
                    text = title,
                    style = styles.screenSubtitle.copy(fontWeight = FontWeight.SemiBold),
                    color = scheme.primary
                )
                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                        color = palette.textPrimary
                    )
                }
            }
            return
        }
        // Outside a card: the prominent section header of Health, Fitness,
        // Books and Journal (`.headerProminence(.increased)`) — a 20pt bold
        // title in sentence case standing on the card edge, not the 13pt
        // grey capitals of a 2014 settings table. On a screen made of cards
        // rather than one long table, the small caps read as Android's
        // overline style and stack into a wall of shouting labels.
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter + 1.dp)
                .padding(top = design.spacing.insetGroupGap, bottom = 8.dp)
        ) {
            Text(
                text = title,
                style = styles.sectionTitle.copy(fontSize = styles.sectionTitle.fontSize * (20f / 22f)),
                color = palette.textPrimary
            )
            if (subtitle != null) {
                Spacer(Modifier.height(1.dp))
                Text(
                    text = subtitle,
                    style = styles.screenSubtitle,
                    color = palette.textSecondary
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
        val design = LocalAppDesign.current
        val palette = design.palette
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        // A tappable card highlights like a cell: the flat grey wash.
        val fill = if (pressed && onClick != null) {
            lerp(palette.groupedSurface, palette.selectionTint, if (design.isDark) 1f else 0.9f)
        } else {
            palette.groupedSurface
        }
        Column(
            modifier
                .fillMaxWidth()
                .clip(design.shapes.card)
                .background(fill)
                .pressable(source, onClick)
                .padding(design.spacing.cardPadding)
        ) {
            val scope = this
            CompositionLocalProvider(LocalInsideCard provides true) {
                with(scope) { content() }
            }
        }
    }

    @Composable
    override fun ListGroup(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) {
        val design = LocalAppDesign.current
        val palette = design.palette
        // Clipping rather than bordering is what lets each row paint its own
        // background and its own trailing hairline. A table view draws no
        // separator under its last row, so the inner column reports itself one
        // physical pixel short of its content and the final hairline lands
        // outside the clip instead of reading as a bottom border on the card.
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .clip(design.shapes.listGroup)
                .background(palette.groupedSurface)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        val trimmed =
                            (placeable.height - HairlineWeight.roundToPx()).coerceAtLeast(0)
                        layout(placeable.width, trimmed) { placeable.place(0, 0) }
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
        val scheme = MaterialTheme.colorScheme

        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()

        // Pressed is UIKit's flat grey wash. Selected is Books' current-chapter
        // treatment: a breath of tint behind the row and the title in the tint
        // colour, not the slab of systemGray4 a press leaves behind.
        val fill = when {
            pressed -> palette.selectionTint.copy(alpha = if (design.isDark) 1f else 0.9f)
            selected -> scheme.primary.copy(alpha = if (design.isDark) 0.16f else 0.08f)
            else -> Color.Transparent
        }
        val titleColor = if (selected) scheme.primary else palette.textPrimary

        Column(modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(fill)
                    .then(
                        if (onClick != null) {
                            Modifier.clickable(
                                interactionSource = source,
                                indication = null,
                                onClick = onClick
                            )
                        } else {
                            Modifier
                        }
                    )
                    .heightIn(min = design.spacing.rowMinHeight)
                    // 8dp around a 41dp two-line stack gives the 57-58pt
                    // subtitle cell UIKit ships; the token's 11dp gave 64.
                    .padding(horizontal = RowTextInset, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    // Settings' wells are saturated with a white glyph in
                    // both appearances; never a pastel tile with a dark glyph.
                    Box(
                        Modifier.size(IconWellSize).background(accentFill(), IconWellShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = onAccentFill(),
                            modifier = Modifier.size(IconWellGlyph)
                        )
                    }
                    Spacer(Modifier.width(design.spacing.iconLabelGap))
                }
                Column(Modifier.weight(1f)) {
                    Text(title, style = styles.rowTitle, color = titleColor)
                    if (subtitle != null) {
                        Spacer(Modifier.height(1.dp))
                        Text(subtitle, style = styles.rowSubtitle, color = palette.textSecondary)
                    }
                }
                if (trailingText != null) {
                    Spacer(Modifier.width(8.dp))
                    Text(trailingText, style = styles.rowTitle, color = palette.textSecondary)
                }
                if (showsNavigationAffordance && design.decor.usesChevrons) {
                    Spacer(Modifier.width(8.dp))
                    Chevron(tint = palette.textTertiary)
                }
            }
            InsetHairline(
                leadingInset = if (leadingIcon != null) {
                    RowTextInset + IconWellSize + design.spacing.iconLabelGap
                } else {
                    RowTextInset
                }
            )
        }
    }

    @Composable
    override fun Divider(modifier: Modifier) {
        val design = LocalAppDesign.current
        // Inside a card the rule spans the card's content measure, edge to
        // edge of the text it separates. On the canvas it is inset by the
        // gutter on *both* sides: a rule that starts at the gutter and runs
        // off the right edge belongs to no column and reads as a stray line.
        if (LocalInsideCard.current) {
            Hairline(modifier)
        } else {
            Hairline(modifier.padding(horizontal = design.spacing.gutter))
        }
    }

    /** Cupertino has no ornament; the gap between groups is the ornament. */
    @Composable
    override fun Ornament(modifier: Modifier) {
        // Half a section gap: the header that follows brings its own 22dp, and
        // the full 28 on top of it opened a 50dp hole no table view has.
        Spacer(modifier.height(LocalAppDesign.current.spacing.sectionGap / 2))
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
        val styles = LocalAppTextStyles.current
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        // UIButton.Configuration.filled at .large: 50pt, 12pt corners, white
        // semibold label. Disabled is UIKit's grey fill and tertiary label —
        // a ghost of the tint is Material's disabled state, not Apple's.
        val content = if (enabled) onAccentFill() else design.palette.textTertiary
        Row(
            modifier
                .graphicsLayer { alpha = if (pressed) 0.72f else 1f }
                .heightIn(min = 50.dp)
                .clip(design.shapes.button)
                .background(if (enabled) accentFill() else disabledFill())
                .pressable(source, onClick, enabled)
                .padding(horizontal = 20.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, null, tint = content, modifier = Modifier.size(17.dp))
                Spacer(Modifier.width(7.dp))
            }
            Text(
                text = label,
                style = styles.buttonLabel,
                color = content,
                textAlign = TextAlign.Center
            )
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
        val design = LocalAppDesign.current
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        // iOS's tinted button: the accent at low alpha, label in the accent.
        // Derived from the live accent rather than from palette.accentMuted,
        // which is keyed to the palette's own iOS blue and turns this button
        // blue in a green app.
        val wash = scheme.primary.copy(alpha = if (design.isDark) 0.20f else 0.12f)
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        val content = if (enabled) scheme.primary else design.palette.textTertiary
        Row(
            modifier
                .graphicsLayer { alpha = if (pressed) 0.6f else 1f }
                .heightIn(min = 50.dp)
                .clip(design.shapes.button)
                .background(if (enabled) wash else disabledFill())
                .pressable(source, onClick, enabled)
                .padding(horizontal = 20.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, null, tint = content, modifier = Modifier.size(17.dp))
                Spacer(Modifier.width(7.dp))
            }
            Text(
                text = label,
                style = styles.buttonLabel,
                color = content,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    override fun SearchField(
        value: String,
        onValueChange: (String) -> Unit,
        placeholder: String,
        modifier: Modifier
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        // UISearchBar's field is 36pt. Material's TextField enforces a 56pt
        // minimum no matter what padding it is given, so the field is built
        // out of a BasicTextField in a Box whose height we own outright.
        val fill = if (design.isDark) {
            palette.raised
        } else {
            palette.selectionTint.copy(alpha = 0.55f)
        }
        androidx.compose.foundation.text.BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.fillMaxWidth().height(36.dp),
            singleLine = true,
            textStyle = styles.rowTitle.copy(color = palette.textPrimary),
            cursorBrush = SolidColor(scheme.primary),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(design.shapes.field)
                        .background(fill)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.Search,
                        null,
                        tint = palette.textTertiary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Box(Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                        if (value.isEmpty()) {
                            Text(
                                placeholder,
                                style = styles.rowTitle,
                                color = palette.textTertiary,
                                maxLines = 1
                            )
                        }
                        innerTextField()
                    }
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
        val trackShape = design.shapes.buttonSmall
        val pillShape = RoundedCornerShape(7.dp)
        // In dark mode the selected segment is *lighter* than its track, not
        // darker: a grouped-surface pill on a selectionTint track inverts the
        // control and is the first thing that looks wrong about it.
        val track = if (design.isDark) {
            palette.groupedSurface
        } else {
            palette.selectionTint.copy(alpha = 0.45f)
        }
        val pill = if (design.isDark) palette.selectionTint else palette.groupedSurface

        Row(
            modifier
                .fillMaxWidth()
                // 32pt track, 2pt inset, 28pt pill — UISegmentedControl.
                .height(32.dp)
                .clip(trackShape)
                .background(track)
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEachIndexed { index, option ->
                if (index > 0) {
                    // Apple rules between two unselected segments and drops the
                    // rule on either side of the selected one.
                    val ruled = index != selectedIndex && index - 1 != selectedIndex
                    Box(
                        Modifier
                            .width(1.dp)
                            .height(16.dp)
                            .background(if (ruled) palette.hairline else Color.Transparent)
                    )
                }
                val isSelected = index == selectedIndex
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        // The only shadow in the whole identity: the selected
                        // segment is the one thing iOS genuinely lifts, and
                        // only where there is light to lift it into.
                        .then(
                            if (isSelected && !design.isDark) {
                                Modifier.shadow(1.5.dp, pillShape)
                            } else {
                                Modifier
                            }
                        )
                        .clip(pillShape)
                        .background(if (isSelected) pill else Color.Transparent)
                        .tap({ onSelect(index) }),
                    contentAlignment = Alignment.Center
                ) {
                    // UISegmentedControl shrinks a long title to fit its
                    // segment (adjustsFontSizeToFitWidth) before it truncates;
                    // a label crowding the rule beside it is the tell.
                    // Measured against the semibold weight whether selected or
                    // not, so a label does not change size as selection moves.
                    val base = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                    val measurer = rememberTextMeasurer()
                    BoxWithConstraints(
                        Modifier.padding(horizontal = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val available = constraints.maxWidth
                        val natural = measurer.measure(
                            option,
                            base.copy(fontWeight = FontWeight.SemiBold),
                            softWrap = false,
                            maxLines = 1
                        ).size.width
                        val scale = if (natural > available && natural > 0) {
                            (available.toFloat() / natural).coerceAtLeast(0.75f)
                        } else {
                            1f
                        }
                        Text(
                            text = option,
                            style = base.copy(fontSize = base.fontSize * scale),
                            color = if (isSelected) palette.textPrimary else palette.textSecondary,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            softWrap = false,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }

    @Composable
    override fun Chip(label: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        // The capsule filter button of Photos, Music and the App Store:
        // tertiary-fill capsule at rest, the tint with a white label when on.
        Box(
            modifier
                .graphicsLayer { alpha = if (pressed) 0.65f else 1f }
                .height(32.dp)
                .clip(design.shapes.chip)
                .background(
                    if (selected) accentFill()
                    else palette.selectionTint.copy(alpha = if (design.isDark) 0.8f else 0.55f)
                )
                .pressable(source, onClick)
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = LocalAppTextStyles.current.screenSubtitle.copy(
                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
                ),
                color = if (selected) onAccentFill() else palette.textPrimary,
                maxLines = 1
            )
        }
    }

    // ----------------------------------------------------------------- content

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
        val scheme = MaterialTheme.colorScheme

        // The serif is deliberate — Apple Books sets book text in a serif and
        // its chrome in SF — but a hanging number column in the chrome's sans
        // at a 48dp indent was what made it look accidental: the reading
        // column started 32dp inside every other element on the screen and in
        // a different voice. The number is now a true superscript in the
        // reading face, so the measure sits on the same 20dp text inset as a
        // grouped row's title and the serif reads as one typographic system.
        // A verse carrying commentary says so by tinting its own number, which
        // is quieter and more legible than the floating dot in the right
        // margin that it replaces.
        val body = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = styles.scripture.fontSize * 0.62f,
                    baselineShift = BaselineShift.Superscript,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.sp,
                    color = if (hasCommentary) scheme.primary else palette.textTertiary
                )
            ) {
                append(number.toString())
            }
            append(" ")
            append(text)
        }

        Box(
            modifier
                .fillMaxWidth()
                .widthIn(max = design.spacing.readingMaxWidth)
                .tap(onClick)
                .padding(horizontal = design.spacing.gutter + 4.dp)
                .padding(top = if (isFirstOfChapter) 12.dp else 5.dp, bottom = 5.dp)
        ) {
            Text(
                text = body,
                style = styles.scripture,
                color = palette.textPrimary
            )
        }
    }

    @Composable
    override fun MetricTile(value: String, caption: String, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        // Health's summary tile: a tinted glyph and its label on one line, the
        // figure underneath. Stacking glyph, figure and caption vertically ran
        // the tile to 130dp and left the three of them ragged at the bottom.
        Column(
            modifier
                .clip(design.shapes.card)
                .background(palette.groupedSurface)
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                if (icon != null) {
                    Icon(
                        icon,
                        null,
                        tint = scheme.primary,
                        // Nudged down so the glyph's optical centre sits on the
                        // label's x-height rather than above its cap line.
                        modifier = Modifier.padding(top = 1.dp).size(15.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                }
                Text(
                    text = caption,
                    style = styles.rowSubtitle.copy(fontWeight = FontWeight.SemiBold),
                    color = scheme.primary,
                    // Always two lines tall, so a caption that wraps does not
                    // push its figure below the figures beside it. Tiles in a
                    // row have to share a baseline to read as one group.
                    minLines = 2,
                    maxLines = 2
                )
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = value,
                style = styles.metric,
                color = palette.textPrimary,
                maxLines = 1,
                softWrap = false
            )
        }
    }

    @Composable
    override fun EmptyState(title: String, body: String?, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter * 2, vertical = design.spacing.sectionGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon ?: Icons.Outlined.Inbox,
                contentDescription = null,
                tint = palette.textTertiary,
                modifier = Modifier.size(46.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = title,
                // iOS sets an empty state at body weight, not at a headline:
                // headlineSmall's 20pt semibold shouted over the whole screen.
                style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                color = palette.textPrimary,
                textAlign = TextAlign.Center
            )
            if (body != null) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = body,
                    style = styles.screenSubtitle,
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
        val scheme = MaterialTheme.colorScheme
        val accent = accentColorHex?.let { Color(it) } ?: scheme.primary
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        val fill = if (pressed && onClick != null) {
            lerp(palette.groupedSurface, palette.selectionTint, if (design.isDark) 1f else 0.9f)
        } else {
            palette.groupedSurface
        }
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .clip(design.shapes.card)
                .background(fill)
                .pressable(source, onClick)
                .heightIn(min = 60.dp)
                .padding(horizontal = RowTextInset, vertical = 11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Same well as a row's leading glyph, so the banner and the groups
            // below it share one leading rhythm.
            Box(Modifier.size(IconWellSize).background(accent, IconWellShape))
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Column(Modifier.weight(1f)) {
                // A cell's caption line, sentence case — not an all-caps overline.
                Text(
                    text = title,
                    style = styles.rowSubtitle,
                    color = palette.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(1.dp))
                if (body != null) {
                    Text(body, style = styles.rowTitle, color = palette.textPrimary)
                }
            }
            if (onClick != null) {
                Spacer(Modifier.width(8.dp))
                Chevron(tint = palette.textTertiary)
            }
        }
    }
}
