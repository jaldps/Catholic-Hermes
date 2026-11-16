package com.example.ui.design

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
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
import androidx.compose.ui.unit.sp
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * The shared Lumen / Sanctuary kit (docs/quality/DESIGN.md).
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

        val backdrop = rememberLiquidBackdrop()
        val live = backdrop != null

        CompositionLocalProvider(
            LocalBackOverrides provides overrides,
            LocalChrome provides chrome,
            LocalLiquidBackdrop provides backdrop
        ) {
            val effectiveBack = overrides.current ?: onBack
            Box(Modifier.fillMaxSize()) {
                androidx.compose.material3.Scaffold(
                    containerColor = Color.Transparent,
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    bottomBar = {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !chrome.dockHidden,
                            enter = if (design.reduceMotion) androidx.compose.animation.EnterTransition.None else androidx.compose.animation.slideInVertically(tween(design.motion.mediumMillis)) { it } + androidx.compose.animation.fadeIn(tween(design.motion.fastMillis)),
                            exit = if (design.reduceMotion) androidx.compose.animation.ExitTransition.None else androidx.compose.animation.slideOutVertically(tween(design.motion.mediumMillis)) { it } + androidx.compose.animation.fadeOut(tween(design.motion.fastMillis))
                        ) { Dock(navItems, selectedNavIndex, onNavSelected) }
                    }
                ) { padding ->
                    val bottom = if (chrome.dockHidden) navInset else padding.calculateBottomPadding().coerceAtLeast(navInset)
                    if (chrome.dockInset != bottom) chrome.dockInset = bottom
                    val inner = PaddingValues(
                        top = statusTop + if (effectiveBack != null) BarRow else 8.dp,
                        bottom = bottom
                    )
                    Box(Modifier.fillMaxSize()) {
                        // The ambient light and everything that scrolls are one
                        // recorded layer: the floating glass above samples it.
                        // Glass inside the content must never sample itself.
                        val opaque = design.opaqueMaterial()
                        val dockShown = !chrome.dockHidden
                        Box(Modifier.fillMaxSize().liquidBackdropSource(backdrop)) {
                            Box(Modifier.fillMaxSize().lumenCanvas())
                            // Scroll edges: content dissolves into the ambient light
                            // above the dock (and, without live blur, under the bar),
                            // so nothing ghosts through the glass or hides a band.
                            Box(
                                Modifier.fillMaxSize()
                                    .then(if (opaque) Modifier else Modifier.alphaMask {
                                        val h = size.height
                                        val fadeBottom = if (dockShown) (bottom + if (live) 0.dp else 26.dp).toPx() else 0f
                                        val floor = if (live) 0.72f else 0.02f
                                        val topSpan = (statusTop + BarRow + 28.dp).toPx()
                                        val topAlpha = if (live) 1f else 1f - edge * 0.97f
                                        val bottomStops = if (!dockShown) arrayOf(1f to Color.Black) else if (live) arrayOf(
                                            ((h - fadeBottom) / h).coerceIn(0.5f, 1f) to Color.Black,
                                            1f to Color.Black.copy(alpha = floor)
                                        ) else if (design.isGlass) arrayOf(
                                            // Clear glass hides nothing: the content must be gone
                                            // before it reaches the dock's top edge.
                                            ((h - fadeBottom - 40.dp.toPx()) / h).coerceIn(0.5f, 1f) to Color.Black,
                                            ((h - fadeBottom) / h).coerceIn(0.5f, 1f) to Color.Black.copy(alpha = 0.34f),
                                            ((h - fadeBottom + 22.dp.toPx()) / h).coerceIn(0.5f, 1f) to Color.Black.copy(alpha = 0.03f),
                                            1f to Color.Black.copy(alpha = 0f)
                                        ) else arrayOf(
                                            ((h - fadeBottom) / h).coerceIn(0.5f, 1f) to Color.Black,
                                            ((h - fadeBottom * 0.80f) / h).coerceIn(0.5f, 1f) to Color.Black.copy(alpha = 0.42f),
                                            ((h - fadeBottom * 0.56f) / h).coerceIn(0.5f, 1f) to Color.Black.copy(alpha = 0.06f),
                                            1f to Color.Black.copy(alpha = floor)
                                        )
                                        Brush.verticalGradient(
                                            0f to Color.Black.copy(alpha = topAlpha),
                                            (topSpan * 0.55f / h) to Color.Black.copy(alpha = topAlpha + (1f - topAlpha) * 0.12f),
                                            (topSpan / h) to Color.Black,
                                            *bottomStops
                                        )
                                    })
                                    .nestedScroll(watcher)
                            ) {
                                CompositionLocalProvider(LocalLiquidBackdrop provides null) { content(inner) }
                            }
                        }

                        // Opaque identities keep the solid fade under the dock.
                        if (dockShown && opaque) {
                            val total = bottom + 24.dp
                            val lift = 24.dp / total
                            Box(
                                Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .height(total)
                                    .background(
                                        Brush.verticalGradient(
                                            0f to Color.Transparent,
                                            lift to palette.canvasBottom.copy(alpha = 0.55f),
                                            (lift + 0.14f).coerceAtMost(0.98f) to palette.canvasBottom.copy(alpha = 0.92f),
                                            1f to palette.canvasBottom.copy(alpha = 0.97f)
                                        )
                                    )
                            )
                        }

                        // The bar material, only once content has scrolled under it.
                        if (design.opaqueMaterial()) {
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
                        } else {
                            // Progressive glass: with a live backdrop the content blurs
                            // under the status bar and clears below the title; without
                            // one it has already dissolved into the light (the mask above).
                            val veil = statusTop + BarRow + 28.dp
                            Box(Modifier.fillMaxWidth().height(veil).graphicsLayer { alpha = edge }) {
                                if (live) {
                                    Box(
                                        Modifier.matchParentSize().alphaMask {
                                            Brush.verticalGradient(
                                                0f to Color.Black,
                                                0.62f to Color.Black,
                                                0.84f to Color.Black.copy(alpha = 0.45f),
                                                1f to Color.Transparent
                                            )
                                        }
                                    ) {
                                        LiquidBackdropFill(androidx.compose.ui.graphics.RectangleShape, Modifier.matchParentSize(), blur = 18.dp, lens = false)
                                        Box(Modifier.matchParentSize().background(palette.canvasTop.copy(alpha = 0.50f)))
                                    }
                                }
                                Text(
                                    text = chrome.titleOverride ?: title,
                                    style = styles.headline,
                                    color = palette.textPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = statusTop)
                                        .padding(horizontal = 72.dp)
                                        .height(BarRow)
                                        .padding(top = 15.dp)
                                )
                            }
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

    /** Four primary destinations. Captions reflow and always retain their type size. */
    @Composable
    private fun Dock(navItems: List<AppNavItem>, selectedNavIndex: Int, onNavSelected: (Int) -> Unit) {
        if (LocalAppDesign.current.isSanctuary) FolioDock(navItems, selectedNavIndex, onNavSelected)
        else GlassDock(navItems, selectedNavIndex, onNavSelected)
    }

    /**
     * Lumen's dock: one floating capsule of glass. The selected destination
     * sits under a bright lens that slides between tabs on a spring.
     */
    @Composable
    private fun GlassDock(navItems: List<AppNavItem>, selectedNavIndex: Int, onNavSelected: (Int) -> Unit) {
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        val radius = 32.dp
        val shape = RoundedCornerShape(radius)
        val live = LocalLiquidBackdrop.current != null
        val still = design.reduceMotion || androidx.compose.ui.platform.LocalInspectionMode.current
        val position by animateFloatAsState(
            selectedNavIndex.coerceIn(0, (navItems.size - 1).coerceAtLeast(0)).toFloat(),
            if (still) androidx.compose.animation.core.snap()
            else androidx.compose.animation.core.spring(dampingRatio = 0.74f, stiffness = 380f),
            label = "dockLens"
        )
        Box(
            Modifier.fillMaxWidth().navigationBarsPadding()
                .padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(Modifier.widthIn(max = 560.dp).fillMaxWidth().testTag("bottom_nav_bar")) {
                LiquidBackdropFill(shape, Modifier.matchParentSize(), blur = 22.dp, cornerRadius = radius)
                Box(Modifier.matchParentSize().liquidPane(shape, design.chromeGlassSpec(live)))
                val inset = 5.dp
                Box(Modifier.fillMaxWidth().padding(inset)) {
                    // The lens. Drawn behind the row, sized to one slot.
                    if (navItems.isNotEmpty()) {
                        val lensShape = RoundedCornerShape(radius - inset)
                        Box(
                            Modifier.matchParentSize().layout { measurable, constraints ->
                                val slot = constraints.maxWidth / navItems.size
                                val placeable = measurable.measure(
                                    constraints.copy(minWidth = slot, maxWidth = slot)
                                )
                                layout(constraints.maxWidth, placeable.height) {
                                    placeable.place((position * slot).toInt(), 0)
                                }
                            }
                        ) {
                            Box(Modifier.fillMaxSize().liquidPane(lensShape, design.glassSpec(GlassRole.Thumb)))
                        }
                    }
                    Row(
                        Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        navItems.forEachIndexed { index, item ->
                            val active = index == selectedNavIndex
                            val tint by animateColorAsState(
                                if (active) (if (design.opaqueMaterial()) p.onAction else p.accent) else p.textSecondary,
                                tween(design.motion.fastMillis), label = "tabTint")
                            Column(
                                Modifier.weight(1f).heightIn(min = 56.dp)
                                    .clip(RoundedCornerShape(radius - inset))
                                    .lumenPress({ onNavSelected(index) }, 0.94f, Role.Tab)
                                    .semantics { selected = active }
                                    .testTag(item.tag)
                                    .padding(horizontal = 4.dp, vertical = 6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(if (active) solidGlyph(item.selectedIcon) else item.icon, null,
                                    tint = tint, modifier = Modifier.size(24.dp))
                                Spacer(Modifier.height(3.dp))
                                Text(item.label, style = styles.navLabel.copy(
                                    fontWeight = if (active) FontWeight.SemiBold else FontWeight.Medium),
                                    color = tint, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        }
    }

    /** Sanctuary's ruled folio rail. */
    @Composable
    private fun FolioDock(navItems: List<AppNavItem>, selectedNavIndex: Int, onNavSelected: (Int) -> Unit) {
        val design = LocalAppDesign.current
        val p = design.palette
        val styles = LocalAppTextStyles.current
        Box(Modifier.fillMaxWidth().navigationBarsPadding(), contentAlignment = Alignment.Center) {
            Column(
                Modifier.widthIn(max = 680.dp).fillMaxWidth().background(p.glassStrong).testTag("bottom_nav_bar")
            ) {
                Box(Modifier.fillMaxWidth().height(1.dp).background(p.hairline))
                Row(
                    Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navItems.forEachIndexed { index, item ->
                        val active = index == selectedNavIndex
                        val tint by animateColorAsState(
                            if (active) p.accent else p.textSecondary,
                            tween(design.motion.fastMillis), label = "tabTint")
                        Column(
                            Modifier.weight(1f).heightIn(min = 64.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .lumenPress({ onNavSelected(index) }, 0.97f, Role.Tab)
                                .semantics { selected = active }
                                .testTag(item.tag)
                                .padding(horizontal = 4.dp, vertical = 6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(if (active) solidGlyph(item.selectedIcon) else item.icon, null,
                                tint = tint, modifier = Modifier.size(24.dp))
                            Spacer(Modifier.height(4.dp))
                            Text(item.label, style = styles.navLabel.copy(
                                fontWeight = if (active) FontWeight.SemiBold else FontWeight.Medium),
                                color = tint, textAlign = TextAlign.Center)
                            Spacer(Modifier.height(5.dp))
                            Box(Modifier.width(20.dp).height(2.dp)
                                .background(if (active) p.accent else Color.Transparent))
                        }
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
                .appContentSurface(shape)
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
                .appContentSurface(shape)
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
                    if (design.isSanctuary) Icon(leadingIcon, null, tint = if (selected) scheme.primary else p.textPrimary, modifier = Modifier.size(26.dp))
                    else AppIconWell(leadingIcon)
                    Spacer(Modifier.width(14.dp))
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
                        .padding(start = inset + if (leadingIcon != null) (if (design.isSanctuary) 40.dp else 50.dp) else 0.dp)
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
        val design = LocalAppDesign.current
        if (design.isSanctuary) Row(modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(Modifier.weight(1f).height(0.5.dp).background(design.palette.hairline))
            Text("✦", style = LocalAppTextStyles.current.caption, color = design.palette.gold,
                modifier = Modifier.semantics { contentDescription = "" })
            Box(Modifier.weight(1f).height(0.5.dp).background(design.palette.hairline))
        } else Spacer(modifier.height(8.dp))
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
        val shape = design.shapes.field
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
        val shape = design.shapes.buttonSmall
        if (options.isEmpty()) return
        val opaque = design.opaqueMaterial()
        val thumb = design.glassSpec(GlassRole.Thumb)
        val onThumb = if (opaque && !design.isSanctuary) p.onAction else if (design.isSanctuary) p.onAction else p.textPrimary
        BoxWithConstraints(modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter)) {
            val density = LocalDensity.current
            val measure = rememberTextMeasurer()
            val widths = options.map { label ->
                (measure.measure(AnnotatedString(label), styles.subhead.copy(fontWeight = FontWeight.SemiBold), maxLines = 1).size.width / density.density + 24f).coerceAtLeast(48f)
            }
            val slotWidth = (maxWidth.value - 8f - (options.size - 1) * 4f) / options.size
            val scrolling = widths.any { it > slotWidth }
            val initialOffset = if (scrolling) ((widths.take(selectedIndex.coerceAtLeast(0)).sum() + selectedIndex * 4f) * density.density).toInt() else 0
            val scrollState = rememberScrollState(initial = initialOffset)
            val still = design.reduceMotion || androidx.compose.ui.platform.LocalInspectionMode.current
            val position by animateFloatAsState(
                selectedIndex.coerceIn(0, options.lastIndex).toFloat(),
                if (still) androidx.compose.animation.core.snap()
                else androidx.compose.animation.core.spring(dampingRatio = 0.76f, stiffness = 420f),
                label = "segmentThumb"
            )
            Box(Modifier.fillMaxWidth().lumenGlass(shape, lifted = false).clip(shape)) {
                // One thumb slides between equal slots; a scrolling track
                // (labels wider than their slot) lights the selected item instead.
                if (!scrolling && !design.isSanctuary) {
                    Box(
                        Modifier.matchParentSize().padding(4.dp).layout { measurable, constraints ->
                            val gap = 4.dp.roundToPx()
                            val slot = (constraints.maxWidth - gap * (options.size - 1)) / options.size
                            val placeable = measurable.measure(constraints.copy(minWidth = slot, maxWidth = slot))
                            layout(constraints.maxWidth, placeable.height) {
                                placeable.place((position * (slot + gap)).toInt(), 0)
                            }
                        }
                    ) { Box(Modifier.fillMaxSize().liquidPane(shape, thumb)) }
                }
                Row(
                    Modifier.fillMaxWidth().height(IntrinsicSize.Min)
                        .then(if (scrolling) Modifier.horizontalScroll(scrollState) else Modifier).padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    options.forEachIndexed { index, label ->
                        val active = index == selectedIndex
                        val ink by animateColorAsState(if (active) onThumb else p.textSecondary,
                            tween(design.motion.fastMillis), label = "segmentInk")
                        Box(
                            (if (scrolling) Modifier.widthIn(min = 48.dp) else Modifier.weight(1f)).heightIn(min = 48.dp)
                                .then(
                                    if (active && design.isSanctuary) Modifier.clip(shape).background(p.action)
                                    else if (active && scrolling) Modifier.liquidPane(shape, thumb)
                                    else Modifier
                                )
                                .clip(shape)
                                .lumenPress({ onSelect(index) }, role = Role.Tab)
                                .semantics { selected = active }
                                .padding(horizontal = 12.dp, vertical = 11.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(label, style = styles.subhead.copy(fontWeight = if (active) FontWeight.SemiBold else FontWeight.Medium),
                                color = ink,
                                maxLines = 1, textAlign = TextAlign.Center)
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
            // `scripture`, not `reading`: readers override this slot with the
            // user's reader font (BibleScreen's Aa choice).
            Text(annotated, style = styles.scripture, color = p.textPrimary, modifier = Modifier.weight(1f))
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
                .appContentSurface(design.shapes.listGroup)
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
