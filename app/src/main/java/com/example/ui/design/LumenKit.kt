package com.example.ui.design

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * Lumen — the redesign's single identity (docs/redesign/DESIGN.md).
 *
 * Warm canvas, frosted glass, regular-weight display type, one accent at a
 * time, charcoal capsules for primary actions. The owner's floating six-tab
 * dock is kept as designed; only its material follows the glass tokens.
 */
object LumenKit : AppKit {

    /** Height of the back/actions row under the status bar. */
    private val BarRow = 52.dp

    /** Rows inside a card align with the card's text column, not the gutter. */
    private val LocalInsideCard = staticCompositionLocalOf { false }

    // ------------------------------------------------------------- chrome

    @Composable
    override fun Scaffold(
        title: String,
        navItems: List<AppNavItem>,
        selectedNavIndex: Int,
        onNavSelected: (Int) -> Unit,
        onBack: (() -> Unit)?,
        backLabel: String?,
        actions: @Composable RowScope.() -> Unit,
        content: @Composable (PaddingValues) -> Unit
    ) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val density = LocalDensity.current
        val overrides = remember { BackOverrides() }
        val chrome = remember { ChromeState() }
        val navInset = androidx.compose.foundation.layout.WindowInsets.navigationBars
            .asPaddingValues().calculateBottomPadding()

        // Scroll-edge behaviour: at rest the bar is invisible and the large
        // title in the content is the title; once content scrolls under it,
        // a near-opaque canvas bar with a hairline and an inline 17sp title
        // fades in.
        val scrolled = remember(title) { mutableFloatStateOf(0f) }
        val watcher = remember(title) {
            object : NestedScrollConnection {
                override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                    scrolled.floatValue = (scrolled.floatValue - consumed.y).coerceAtLeast(0f)
                    return Offset.Zero
                }
            }
        }
        val edgeTarget = with(density) { ((scrolled.floatValue - 120.dp.toPx()) / 40.dp.toPx()).coerceIn(0f, 1f) }
        val edge by animateFloatAsState(edgeTarget, tween(design.motion.fastMillis), label = "barEdge")

        val statusTop = statusBarHeight()

        CompositionLocalProvider(LocalBackOverrides provides overrides, LocalChrome provides chrome) {
            val effectiveBack = overrides.current ?: onBack
            Box(Modifier.fillMaxSize().lumenCanvas()) {
                androidx.compose.material3.Scaffold(
                    containerColor = Color.Transparent,
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    bottomBar = {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !chrome.dockHidden,
                            enter = androidx.compose.animation.slideInVertically { it } + androidx.compose.animation.fadeIn(),
                            exit = androidx.compose.animation.slideOutVertically { it } + androidx.compose.animation.fadeOut()
                        ) { Dock(navItems, selectedNavIndex, onNavSelected) }
                    }
                ) { padding ->
                    val bottom = if (chrome.dockHidden) navInset else padding.calculateBottomPadding().coerceAtLeast(navInset)
                    if (chrome.dockInset != bottom) chrome.dockInset = bottom
                    val inner = PaddingValues(
                        top = statusTop + if (effectiveBack != null) BarRow else 8.dp,
                        bottom = bottom
                    )
                    Box(Modifier.fillMaxSize().nestedScroll(watcher)) {
                        content(inner)

                        // The bar material, only once content has scrolled under it.
                        Column(Modifier.fillMaxWidth().graphicsLayer { alpha = edge }) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(statusTop + BarRow)
                                    .background(palette.canvasTop.copy(alpha = 0.95f))
                            ) {
                                Text(
                                    text = chrome.titleOverride ?: title,
                                    style = styles.headline,
                                    color = palette.textPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                        .padding(horizontal = 72.dp)
                                        .height(BarRow)
                                        .padding(top = 15.dp)
                                )
                            }
                            Box(Modifier.fillMaxWidth().height(0.5.dp).background(palette.separator))
                        }

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = statusTop)
                                .height(BarRow)
                                .padding(horizontal = design.spacing.gutter - 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (effectiveBack != null) {
                                AppGlassIconButton(
                                    icon = Icons.AutoMirrored.Outlined.ArrowBackIos,
                                    contentDescription = backLabel ?: "Back",
                                    onClick = effectiveBack,
                                    tag = "nav_back",
                                    modifier = Modifier.offset(x = (-2).dp)
                                )
                            }
                            Spacer(Modifier.weight(1f))
                            Row(
                                Modifier.layout { measurable, constraints ->
                                    val placeable = measurable.measure(constraints)
                                    if (placeable.width <= 9.dp.roundToPx()) layout(0, 0) {}
                                    else layout(placeable.width, placeable.height) { placeable.place(0, 0) }
                                },
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                content = actions
                            )
                        }
                    }
                }
            }
        }
    }

    /**
     * The owner's floating dock: six labelled tabs on one capsule, the
     * selected tab on a tinted pill. Unchanged in layout; glass material.
     */
    @Composable
    private fun Dock(navItems: List<AppNavItem>, selectedNavIndex: Int, onNavSelected: (Int) -> Unit) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        val dockShape = RoundedCornerShape(32.dp)
        Box(
            Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .softShadow(dockShape, palette.shadow.copy(alpha = (palette.shadow.alpha * 1.5f).coerceAtMost(1f)), 32.dp, 10.dp)
                    .clip(dockShape)
                    .background(
                        Brush.verticalGradient(
                            if (design.isDark) listOf(Color(0xF22C2B29), Color(0xF2201F1D))
                            else listOf(Color(0xF5FFFFFF), Color(0xF2F8F6F3))
                        )
                    )
                    .border(1.dp, Brush.verticalGradient(listOf(palette.glassEdgeTop, palette.glassEdgeBottom)), dockShape)
                    .padding(5.dp)
                    .testTag("bottom_nav_bar"),
                verticalAlignment = Alignment.CenterVertically
            ) {
                navItems.forEachIndexed { index, item ->
                    val isSelected = index == selectedNavIndex
                    val tint by animateColorAsState(
                        if (isSelected) scheme.primary else palette.textSecondary,
                        tween(design.motion.fastMillis), label = "tabTint"
                    )
                    val tabFill by animateColorAsState(
                        if (isSelected) scheme.primary.copy(alpha = if (design.isDark) 0.18f else 0.10f) else Color.Transparent,
                        tween(design.motion.fastMillis), label = "tabFill"
                    )
                    val source = remember { MutableInteractionSource() }
                    val pressed by source.collectIsPressedAsState()
                    val scale by animateFloatAsState(if (pressed) 0.94f else 1f, label = "tabPress")
                    Column(
                        Modifier
                            .weight(1f)
                            .heightIn(min = 52.dp)
                            .graphicsLayer { scaleX = scale; scaleY = scale }
                            .clip(RoundedCornerShape(27.dp))
                            .background(tabFill)
                            .clickable(source, indication = null, role = Role.Tab, onClick = { onNavSelected(index) })
                            .semantics { selected = isSelected }
                            .padding(horizontal = 2.dp, vertical = 5.dp)
                            .testTag(item.tag),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = when {
                                item.tag == "nav_more" -> Icons.Filled.MoreHoriz
                                isSelected -> solidGlyph(item.selectedIcon)
                                else -> item.icon
                            },
                            contentDescription = null,
                            tint = tint,
                            modifier = Modifier.size(23.dp)
                        )
                        Spacer(Modifier.height(3.dp))
                        Text(
                            text = item.label,
                            style = styles.navLabel.copy(
                                fontSize = styles.navLabel.fontSize * 1.1f,
                                lineHeight = styles.navLabel.lineHeight * 1.17f,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                            ),
                            color = tint,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }

    private val solidCache = java.util.concurrent.ConcurrentHashMap<String, ImageVector>()

    /** The filled twin of an outline glyph for the selected tab. */
    private fun solidGlyph(icon: ImageVector): ImageVector {
        val full = icon.name
        return solidCache.getOrPut(full) {
            val n = full.substringAfterLast('.').lowercase()
            when {
                full.startsWith("rosary") || n == "selfimprovement" -> DevotionalGlyphs.rosaryFilled
                full.startsWith("candle") -> DevotionalGlyphs.candleFilled
                n == "historyedu" -> Icons.Filled.AccountBalance
                n == "editnote" -> Icons.Filled.Create
                else -> filledVariant(full) ?: icon
            }
        }
    }

    private fun filledVariant(name: String): ImageVector? = runCatching {
        val parts = name.split('.')
        val simple = parts.last()
        val mirrored = parts.first() == "AutoMirrored"
        val style = if (mirrored) parts.getOrNull(1) else parts.first()
        if (style == "Filled") return@runCatching null
        if (style !in setOf("Outlined", "Rounded", "Sharp", "TwoTone")) return@runCatching null
        val pkg = if (mirrored) "androidx.compose.material.icons.automirrored.filled" else "androidx.compose.material.icons.filled"
        val owner: Any = if (mirrored) Icons.AutoMirrored.Filled else Icons.Filled
        Class.forName("$pkg.${simple}Kt").getMethod("get$simple", owner.javaClass).invoke(null, owner) as ImageVector
    }.getOrNull()

    @Composable
    override fun TopBar(title: String, subtitle: String?, onBack: (() -> Unit)?, actions: @Composable RowScope.() -> Unit) {
        // The single back control is the scaffold's glass button; an inline
        // bar only takes it over.
        if (onBack != null) AppBackHandler(onBack = onBack)
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        Row(
            Modifier.fillMaxWidth().heightIn(min = BarRow).padding(horizontal = design.spacing.gutter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(title, style = styles.headline, color = p.textPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                if (subtitle != null) Text(subtitle, style = styles.footnote, color = p.textSecondary, maxLines = 1)
            }
            Row(verticalAlignment = Alignment.CenterVertically, content = actions)
        }
    }

    @Composable
    override fun ScreenTitle(title: String, subtitle: String?, modifier: Modifier, trailingContent: (@Composable () -> Unit)?) {
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        // A short subtitle (a date, a season) is an eyebrow above the title;
        // a sentence goes under it in subhead.
        val eyebrow = subtitle?.takeIf { it.length <= 32 && !it.trimEnd().endsWith(".") }
        val under = subtitle?.takeIf { eyebrow == null }
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .padding(top = design.spacing.headerTopGap, bottom = 14.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(Modifier.weight(1f)) {
                if (eyebrow != null) {
                    Text(eyebrow, style = styles.eyebrow, color = p.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(Modifier.height(2.dp))
                }
                Text(
                    title,
                    style = if (title.length > 18) styles.title1 else styles.largeTitle,
                    color = p.textPrimary,
                    modifier = Modifier.semantics { heading() }
                )
                if (under != null) {
                    Spacer(Modifier.height(6.dp))
                    Text(under, style = styles.subhead, color = p.textSecondary)
                }
            }
            if (trailingContent != null) {
                Spacer(Modifier.width(12.dp))
                trailingContent()
            }
        }
    }

    // ---------------------------------------------------------- structure

    @Composable
    override fun SectionHeader(title: String, subtitle: String?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        val inCard = LocalInsideCard.current
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = if (inCard) 0.dp else design.spacing.gutter)
                .padding(top = if (inCard) 0.dp else 10.dp, bottom = 12.dp)
        ) {
            Text(
                title,
                style = if (inCard) styles.headline else styles.title3,
                color = p.textPrimary,
                modifier = Modifier.semantics { heading() }
            )
            if (subtitle != null) {
                Spacer(Modifier.height(3.dp))
                Text(subtitle, style = styles.footnote, color = p.textSecondary)
            }
        }
    }

    @Composable
    override fun Card(modifier: Modifier, onClick: (() -> Unit)?, content: @Composable ColumnScope.() -> Unit) {
        val design = LocalAppDesign.current
        val shape = design.shapes.card
        Column(
            modifier
                .fillMaxWidth()
                .lumenPress(onClick, 0.98f)
                .lumenGlass(shape)
                .clip(shape)
                .padding(design.spacing.cardPadding)
        ) {
            val scope = this
            CompositionLocalProvider(LocalInsideCard provides true) { with(scope) { content() } }
        }
    }

    @Composable
    override fun ListGroup(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) {
        val design = LocalAppDesign.current
        val shape = design.shapes.listGroup
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .lumenGlass(shape)
                .clip(shape)
        ) {
            // The last row's separator falls outside the clip.
            Column(
                Modifier.fillMaxWidth().layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    val trimmed = (placeable.height - 1.dp.roundToPx()).coerceAtLeast(0)
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
        val p = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        val source = remember { MutableInteractionSource() }
        val pressed by source.collectIsPressedAsState()
        val fill by animateColorAsState(
            when {
                pressed -> p.textPrimary.copy(alpha = 0.06f)
                selected -> scheme.primary.copy(alpha = if (design.isDark) 0.16f else 0.08f)
                else -> Color.Transparent
            },
            if (pressed) tween(0) else tween(design.motion.mediumMillis),
            label = "rowFill"
        )
        val inCard = LocalInsideCard.current
        val inset = if (inCard) 0.dp else 18.dp
        Column(modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(fill)
                    .then(
                        if (onClick != null) Modifier.clickable(source, indication = null, role = Role.Button, onClick = onClick)
                        else Modifier
                    )
                    .heightIn(min = design.spacing.rowMinHeight)
                    .padding(horizontal = inset, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Icon(leadingIcon, null, tint = if (selected) scheme.primary else p.textPrimary, modifier = Modifier.size(22.dp))
                    Spacer(Modifier.width(16.dp))
                }
                Column(Modifier.weight(1f)) {
                    Text(title, style = styles.body, color = if (selected) scheme.primary else p.textPrimary)
                    if (subtitle != null) {
                        Spacer(Modifier.height(2.dp))
                        Text(subtitle, style = styles.footnote, color = p.textSecondary)
                    }
                }
                if (trailingText != null) {
                    Spacer(Modifier.width(10.dp))
                    Text(
                        trailingText,
                        style = styles.subhead,
                        color = p.textSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(max = 130.dp)
                    )
                }
                if (showsNavigationAffordance && onClick != null) {
                    Spacer(Modifier.width(10.dp))
                    AppChevron()
                }
            }
            if (!inCard) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = inset + if (leadingIcon != null) 38.dp else 0.dp)
                        .height(0.5.dp)
                        .background(p.separator)
                )
            }
        }
    }

    @Composable
    override fun Divider(modifier: Modifier) {
        val design = LocalAppDesign.current
        val inCard = LocalInsideCard.current
        Box(
            modifier
                .fillMaxWidth()
                .padding(horizontal = if (inCard) 0.dp else design.spacing.gutter)
                .height(0.5.dp)
                .background(design.palette.separator)
        )
    }

    /** Lumen's ornament is space. */
    @Composable
    override fun Ornament(modifier: Modifier) {
        Spacer(modifier.height(8.dp))
    }

    // -------------------------------------------------------------- inputs

    @Composable
    override fun PrimaryButton(label: String, onClick: () -> Unit, enabled: Boolean, leadingIcon: ImageVector?, modifier: Modifier) {
        AppPrimaryCapsule(label, onClick, modifier, leadingIcon, enabled, fillWidth = false)
    }

    @Composable
    override fun SecondaryButton(label: String, onClick: () -> Unit, enabled: Boolean, leadingIcon: ImageVector?, modifier: Modifier) {
        AppSecondaryCapsule(label, onClick, modifier, leadingIcon, enabled)
    }

    @Composable
    override fun SearchField(value: String, onValueChange: (String) -> Unit, placeholder: String, modifier: Modifier) {
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        val shape = RoundedCornerShape(percent = 50)
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .heightIn(min = 48.dp)
                .lumenGlass(shape, lifted = false)
                .semantics { contentDescription = placeholder }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.Search, null, tint = p.textSecondary, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            Box(Modifier.weight(1f)) {
                if (value.isEmpty()) {
                    Text(placeholder, style = styles.body, color = p.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    textStyle = styles.body.copy(color = p.textPrimary),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    @Composable
    override fun SegmentedTabs(options: List<String>, selectedIndex: Int, onSelect: (Int) -> Unit, modifier: Modifier) {
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        val track = RoundedCornerShape(percent = 50)
        BoxWithConstraints(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
        ) {
            val count = options.size.coerceAtLeast(1)
            val segment = (maxWidth - 8.dp) / count
            val offset by androidx.compose.animation.core.animateDpAsState(
                segment * selectedIndex.coerceIn(0, count - 1),
                androidx.compose.animation.core.spring(dampingRatio = 0.85f, stiffness = 500f),
                label = "segment"
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 44.dp)
                    .lumenGlass(track, lifted = false)
                    .padding(4.dp)
            ) {
                Box(
                    Modifier
                        .offset(x = offset)
                        .width(segment)
                        .height(36.dp)
                        .softShadow(track, p.shadow, 12.dp, 3.dp)
                        .background(if (design.isDark) Color.White.copy(alpha = 0.16f) else Color.White, track)
                )
                Row(Modifier.fillMaxWidth().height(36.dp)) {
                    options.forEachIndexed { i, label ->
                        val isSelected = i == selectedIndex
                        Box(
                            Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .clip(track)
                                .clickable(remember { MutableInteractionSource() }, indication = null, role = Role.Tab) { onSelect(i) }
                                .semantics { selected = isSelected },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                label,
                                style = styles.subhead.copy(fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium),
                                color = if (isSelected) p.textPrimary else p.textSecondary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    override fun Chip(label: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
        AppKitChip(label, selected, onClick, modifier)
    }

    // ------------------------------------------------------------- content

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
        val p = design.palette
        val styles = LocalAppTextStyles.current
        val annotated = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = styles.verseNumber.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = p.textSecondary,
                    baselineShift = BaselineShift(0.35f)
                )
            ) { append("$number ") }
            append(text)
        }
        Row(
            modifier
                .fillMaxWidth()
                .then(if (onClick != null) Modifier.clickable(remember { MutableInteractionSource() }, null, onClick = onClick) else Modifier)
                .padding(horizontal = design.spacing.gutter, vertical = 4.dp)
        ) {
            Text(annotated, style = styles.reading, color = p.textPrimary, modifier = Modifier.weight(1f))
            if (hasCommentary) {
                Spacer(Modifier.width(8.dp))
                Box(Modifier.padding(top = 12.dp).size(6.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary))
            }
        }
    }

    @Composable
    override fun MetricTile(value: String, caption: String, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        Column(
            modifier
                .lumenGlass(design.shapes.listGroup)
                .clip(design.shapes.listGroup)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            if (icon != null) {
                Icon(icon, null, tint = p.textSecondary, modifier = Modifier.size(20.dp))
                Spacer(Modifier.height(10.dp))
            }
            Text(value, style = styles.metric, color = p.textPrimary, maxLines = 1)
            Spacer(Modifier.height(2.dp))
            Text(caption, style = styles.footnote, color = p.textSecondary, maxLines = 2)
        }
    }

    @Composable
    override fun EmptyState(title: String, body: String?, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        Column(
            modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter * 2, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.size(64.dp).lumenGlass(CircleShape), contentAlignment = Alignment.Center) {
                Icon(icon ?: Icons.Outlined.Inbox, null, tint = p.textSecondary, modifier = Modifier.size(28.dp))
            }
            Spacer(Modifier.height(16.dp))
            Text(title, style = styles.headline, color = p.textPrimary, textAlign = TextAlign.Center)
            if (body != null) {
                Spacer(Modifier.height(6.dp))
                Text(body, style = styles.subhead, color = p.textSecondary, textAlign = TextAlign.Center)
            }
        }
    }

    @Composable
    override fun Banner(title: String, body: String?, accentColorHex: Long?, onClick: (() -> Unit)?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        val dot = accentColorHex?.let { Color(it) } ?: MaterialTheme.colorScheme.primary
        AppGlassSurface(
            modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
            shape = design.shapes.listGroup,
            onClick = onClick,
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AppSeasonDot(dot, size = 12.dp)
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(title, style = styles.headline, color = p.textPrimary)
                    if (body != null) {
                        Spacer(Modifier.height(2.dp))
                        Text(body, style = styles.subhead, color = p.textSecondary)
                    }
                }
                if (onClick != null) {
                    Spacer(Modifier.width(12.dp))
                    AppChevron()
                }
            }
        }
    }
}
