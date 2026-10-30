package com.example.ui.design

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
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

/**
 * The Vatican identity, set as a printer would set it.
 *
 * Two rules govern everything here.
 *
 * **Gold is leaf, not paint.** It is spent in exactly four places: the single
 * hairline under the masthead, the rule under a title, the illuminated initial
 * that opens a chapter, and the fleuron that closes a section. Everything that
 * used to be gold — section eyebrows, book sigla, card borders, button edges,
 * metric rules, the search glass — is ink. Red is reserved for rubrication:
 * verse figures, the current entry, the liturgical colour.
 *
 * **Everything sits on one baseline grid.** Scripture is leaded at 30sp with no
 * space added between verses, exactly as a Bible column is set solid, and the
 * illuminated initial is cut into that grid: its cap line meets the first
 * line's cap line and its baseline sits on the second line's baseline, with the
 * remainder of the opening word running on beside it. Nothing floats on a
 * shadow; depth is carried by ruled lines, and corners are square because paper
 * has no radius.
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
        return design.palette.textPrimary.copy(alpha = if (design.isDark) 0.17f else 0.30f)
    }

    /**
     * Body ink. Candlelight does not print pure white on vellum, so the dark
     * variant is held back from full strength rather than being the light
     * theme with the lamp turned down.
     */
    @Composable
    private fun bodyInk(): Color {
        val design = LocalAppDesign.current
        return if (design.isDark) design.palette.textPrimary.copy(alpha = 0.90f)
        else design.palette.textPrimary
    }

    /** Lifts a liturgical colour until it reads as pigment on a dark ground. */
    @Composable
    private fun pigment(color: Color): Color {
        val design = LocalAppDesign.current
        return if (design.isDark) lerp(color, design.palette.textPrimary, 0.45f) else color
    }

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
    private fun Lozenge(size: Dp) {
        val gold = LocalAppDesign.current.palette.gold
        Box(Modifier.size(size).rotate(45f).background(gold))
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

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            containerColor = palette.canvas,
            topBar = {
                Surface(color = palette.canvas) {
                    Column {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .heightIn(min = 62.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // The masthead is centred, as an ecclesial document is
                            // headed rather than left-titled.
                            Box(Modifier.width(48.dp))
                            Column(
                                Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "✠",
                                    style = styles.sectionEyebrow.copy(
                                        fontSize = 11.sp,
                                        letterSpacing = 0.sp
                                    ),
                                    color = palette.gold
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = title.uppercase(),
                                    // Trajan capitals at masthead size want less
                                    // tracking than the eyebrows below them.
                                    style = styles.screenTitle
                                        .copy(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                        .tracked(0.115f),
                                    color = bodyInk(),
                                    textAlign = TextAlign.Center,
                                    maxLines = 1
                                )
                            }
                            Row(
                                Modifier.widthIn(min = 48.dp).wrapContentWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                content = actions
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        // The one hairline of leaf the chrome is allowed.
                        ScotchRule(thinColor = palette.gold.copy(alpha = 0.85f))
                    }
                }
            },
            bottomBar = {
                Surface(color = palette.canvas) {
                    Column(Modifier.navigationBarsPadding().testTag("bottom_nav_bar")) {
                        Hairline(color = inkRule())
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                                .padding(horizontal = 4.dp)
                                .padding(top = 10.dp, bottom = 6.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            navItems.forEachIndexed { index, item ->
                                val isSelected = index == selectedNavIndex
                                Column(
                                    modifier = Modifier
                                        .widthIn(min = 60.dp)
                                        .clickable { onNavSelected(index) }
                                        .padding(horizontal = 2.dp)
                                        .testTag(item.tag),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) item.selectedIcon else item.icon,
                                        contentDescription = item.label,
                                        tint = if (isSelected) palette.accent
                                        else palette.textTertiary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(Modifier.height(7.dp))
                                    Text(
                                        text = item.label.uppercase(),
                                        // 9sp caps: the smallest cut, so the
                                        // widest tracking on the scale.
                                        style = styles.navLabel.tracked(0.22f),
                                        color = if (isSelected) bodyInk() else palette.textTertiary,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1
                                    )
                                    Spacer(Modifier.height(6.dp))
                                    Box(
                                        Modifier
                                            .fillMaxWidth()
                                            .height(1.dp)
                                            .background(
                                                if (isSelected) palette.gold else Color.Transparent
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        ) { insets ->
            // Parchment is never one flat tint; vellum by candlelight even less
            // so, which is why the dark ground carries a warmer pool at the
            // centre of the leaf rather than a uniform brown.
            val wash = if (design.isDark) {
                Brush.verticalGradient(
                    0f to palette.canvas,
                    0.45f to palette.raised.copy(alpha = 0.85f),
                    1f to palette.canvas
                )
            } else {
                Brush.verticalGradient(
                    0f to palette.canvas,
                    0.45f to palette.groupedSurface.copy(alpha = 0.5f),
                    1f to palette.canvas
                )
            }
            Box(Modifier.fillMaxSize().background(wash)) {
                content(insets)
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
        Column(Modifier.fillMaxWidth().background(palette.canvas)) {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 4.dp).heightIn(min = 58.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.width(48.dp)) {
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
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
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
                Row(
                    Modifier.widthIn(min = 48.dp).wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }
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
            // Leaf, spent once: a single hairline the width of a short word.
            Box(Modifier.width(76.dp).height(1.dp).background(palette.gold))
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
            // A running head is centred between two rules — but the rules are
            // ink. A printer does not gild a section label five times a page.
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.width(24.dp).height(0.5.dp).background(inkHair()))
                Text(
                    text = title.uppercase(),
                    style = styles.sectionEyebrow.tracked(0.22f),
                    color = palette.textSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                Box(Modifier.width(24.dp).height(0.5.dp).background(inkHair()))
            }
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
        val design = LocalAppDesign.current
        // A box rule on the page: square, unfilled, one weight of ink.
        Column(
            modifier
                .fillMaxWidth()
                .border(0.75.dp, inkHair(), RectangleShape)
                .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
                .padding(design.spacing.cardPadding),
            content = content
        )
    }

    @Composable
    override fun ListGroup(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) {
        val design = LocalAppDesign.current
        // No panel, no border box, no corner radius: a ruled folio table. The
        // head rule opens it; each row closes itself with a hairline.
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
        ) {
            ScotchRule(gap = 2.dp)
            content()
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
                    .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
                    .background(
                        if (selected) palette.selectionTint.copy(alpha = 0.45f)
                        else Color.Transparent
                    )
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
        Row(
            modifier
                // Rubric red, square, unbordered. A gilded edge on a button is
                // leaf spent where no printer would spend it.
                .background(if (enabled) palette.accent else palette.hairline, RectangleShape)
                .clickable(enabled = enabled, onClick = onClick)
                .padding(horizontal = 22.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = label.uppercase(),
                style = styles.buttonLabel.copy(fontWeight = FontWeight.SemiBold).tracked(0.16f),
                color = if (enabled) palette.raised else palette.textTertiary,
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
        val styles = LocalAppTextStyles.current
        Row(
            modifier
                .border(0.75.dp, inkRule(), RectangleShape)
                .clickable(enabled = enabled, onClick = onClick)
                .padding(horizontal = 22.dp, vertical = 15.dp)
                .alpha(if (enabled) 1f else 0.45f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = label.uppercase(),
                style = styles.buttonLabel.copy(fontWeight = FontWeight.SemiBold).tracked(0.16f),
                color = bodyInk(),
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
        val palette = LocalAppDesign.current.palette
        val styles = LocalAppTextStyles.current
        Column(modifier.fillMaxWidth()) {
            Row(
                Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Center
            ) {
                options.forEachIndexed { index, option ->
                    val isSelected = index == selectedIndex
                    Column(
                        Modifier
                            .clickable { onSelect(index) }
                            .padding(horizontal = 18.dp)
                            .padding(top = 4.dp, bottom = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = option.uppercase(),
                            style = styles.navLabel.copy(fontSize = 11.sp).tracked(0.2f),
                            color = if (isSelected) bodyInk() else palette.textTertiary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(8.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(if (isSelected) palette.accent else Color.Transparent)
                        )
                    }
                }
            }
            Hairline()
        }
    }

    @Composable
    override fun Chip(label: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
        val palette = LocalAppDesign.current.palette
        val styles = LocalAppTextStyles.current
        Box(
            modifier
                .border(
                    0.75.dp,
                    if (selected) palette.accent else inkHair(),
                    RectangleShape
                )
                .clickable(onClick = onClick)
                .padding(horizontal = 14.dp, vertical = 9.dp)
        ) {
            Text(
                text = label.uppercase(),
                style = styles.navLabel.copy(fontSize = 10.sp).tracked(0.2f),
                color = if (selected) palette.accent else palette.textSecondary
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
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
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
                .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier),
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
