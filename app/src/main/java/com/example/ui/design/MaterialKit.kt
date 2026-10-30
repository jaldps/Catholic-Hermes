package com.example.ui.design

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Inbox
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card as M3Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * The Material 3 identity: tonal surfaces stepped through the `surfaceContainer`
 * ladder, the 16/28dp shape scale, stadium buttons and search field, and a pill
 * navigation indicator. Depth is tonal, never cast — every card sits at 0dp and
 * is separated from what is behind it by a tone step alone, so the hierarchy
 * survives dark mode where a shadow does not.
 *
 * ### Why the colour is computed here rather than read from the scheme
 *
 * The shared palette hands Material a *baseline-purple* neutral family
 * (`raised` #F3EDF7, `accentMuted` #EADDFF, `selectionTint` #E8DEF8) while
 * `primary` is the app's liturgical accent. That is not what M3 does: in a
 * seeded scheme every neutral is struck from the seed at low chroma and every
 * container is struck from the seed's tonal palette, so surfaces and accent are
 * always the same family. Reading `primaryContainer`/`secondaryContainer`
 * straight out of the scheme is what produced green glyphs sitting in lilac
 * circles. So the four roles this identity leans on hardest — the surface
 * ladder, the two tonal container tints and the outline — are derived from
 * `primary` here, the way a seeded scheme would derive them. The palette tokens
 * themselves want the same fix; see the report.
 */
object MaterialKit : AppKit {

    // ------------------------------------------------------------------ colour

    /**
     * The roles this identity actually paints with. Everything is derived from
     * the canvas lightness and the accent hue, so the surfaces, the tonal
     * circles and the accent all belong to one tonal family.
     */
    @Immutable
    private data class Roles(
        /** Background / top app bar, M3 `surface` at tone 98 (light) / 6 (dark). */
        val canvas: Color,
        /** `surfaceContainerLow`. */
        val low: Color,
        /** `surfaceContainer`: list rows, the navigation bar. */
        val container: Color,
        /** `surfaceContainerHigh`: the search field, metric tiles. */
        val high: Color,
        /** `surfaceContainerHighest`. */
        val highest: Color,
        /** `primaryContainer`: the tonal circle a metric or empty state wears. */
        val accentSoft: Color,
        val onAccentSoft: Color,
        /** `secondaryContainer`: nav pill, selection, verse number, active segment. */
        val accentSofter: Color,
        val onAccentSofter: Color,
        /** `outlineVariant`: dividers and the chip/segment hairline. */
        val outline: Color
    )

    private fun Color.luma(): Float = 0.2126f * red + 0.7152f * green + 0.0722f * blue

    /** Relative luminance, for the WCAG ratio the on-colours are solved against. */
    private fun Color.relativeLuminance(): Float {
        fun ch(c: Float): Float {
            val v = c.toDouble()
            return (if (v <= 0.03928) v / 12.92 else Math.pow((v + 0.055) / 1.055, 2.4)).toFloat()
        }
        return 0.2126f * ch(red) + 0.7152f * ch(green) + 0.0722f * ch(blue)
    }

    private fun contrastOf(a: Color, b: Color): Float {
        val la = a.relativeLuminance()
        val lb = b.relativeLuminance()
        val hi = if (la > lb) la else lb
        val lo = if (la > lb) lb else la
        return (hi + 0.05f) / (lo + 0.05f)
    }

    private fun Color.lighten(f: Float) =
        Color(red + (1f - red) * f, green + (1f - green) * f, blue + (1f - blue) * f, alpha)

    private fun Color.darken(f: Float) =
        Color(red * (1f - f), green * (1f - f), blue * (1f - f), alpha)

    /** Walks a foreground toward the far end of the ramp until it clears 4.5:1. */
    private fun Color.readableOn(background: Color, target: Float = 4.5f): Color {
        var candidate = this
        val goLighter = background.relativeLuminance() < 0.5f
        repeat(14) {
            if (contrastOf(candidate, background) >= target) return candidate
            candidate = if (goLighter) candidate.lighten(0.11f) else candidate.darken(0.11f)
        }
        return candidate
    }

    /** Strips a colour of its hue, keeping only its lightness. */
    private fun Color.neutralised(): Color = luma().let { Color(it, it, it, alpha) }

    /**
     * Pushes a colour along [hue]'s chroma direction without moving its
     * lightness — the cheap stand-in for "neutral palette at the seed's hue,
     * chroma 4" that a real M3 scheme generator produces.
     */
    private fun Color.tintedBy(hue: Color, amount: Float): Color {
        val l = hue.luma()
        return Color(
            (red + (hue.red - l) * amount).coerceIn(0f, 1f),
            (green + (hue.green - l) * amount).coerceIn(0f, 1f),
            (blue + (hue.blue - l) * amount).coerceIn(0f, 1f),
            alpha
        )
    }

    private fun Color.step(ink: Color, alpha: Float): Color =
        ink.copy(alpha = alpha).compositeOver(this)

    @Composable
    private fun roles(): Roles {
        val design = LocalAppDesign.current
        val dark = design.isDark
        val accent = MaterialTheme.colorScheme.primary
        val ink = design.palette.textPrimary

        // The canvas: the identity's own lightness, re-struck at the accent's
        // hue. Light sits at M3's tone 98 (above every container) rather than at
        // the token's tone 96, so cards can step *down* and still stay bright.
        val canvas = if (dark) {
            design.palette.canvas.neutralised().tintedBy(accent, 0.10f)
        } else {
            Color(0.976f, 0.976f, 0.976f).tintedBy(accent, 0.10f)
        }

        // The surfaceContainer ladder. The alphas reproduce M3's own tone steps
        // (98→96→94→92→90 light, 6→10→12→17→22 dark) measured off the baseline
        // scheme, so four distinct steps are visible in both modes with no
        // shadow doing any of the work.
        val a = if (dark) floatArrayOf(0.042f, 0.062f, 0.109f, 0.161f)
        else floatArrayOf(0.031f, 0.050f, 0.082f, 0.109f)

        val high = canvas.step(ink, a[2])
        val accentSoft = accent
            .copy(alpha = if (dark) 0.30f else 0.22f)
            .compositeOver(canvas)
            .tintedBy(accent, 0.18f)
        val accentSofter = accent
            .copy(alpha = if (dark) 0.20f else 0.13f)
            .compositeOver(canvas)

        return Roles(
            canvas = canvas,
            low = canvas.step(ink, a[0]),
            container = canvas.step(ink, a[1]),
            high = high,
            highest = canvas.step(ink, a[3]),
            accentSoft = accentSoft,
            onAccentSoft = accent.readableOn(accentSoft),
            accentSofter = accentSofter,
            onAccentSofter = design.palette.textPrimary.readableOn(accentSofter),
            outline = canvas.step(ink, if (dark) 0.30f else 0.24f)
        )
    }

    // -------------------------------------------------------------- primitives

    /**
     * The signature M3 list idiom: a glyph centred in a tonal circle. [size] is
     * the container, [glyph] the icon drawn inside it. 40/24 is the dimension
     * the list-item leading avatar is specified at.
     */
    @Composable
    private fun TonalIconCircle(
        icon: ImageVector,
        container: Color,
        tint: Color,
        size: Dp = 40.dp,
        glyph: Dp = 24.dp,
        modifier: Modifier = Modifier
    ) {
        Box(
            modifier.size(size).background(container, RoundedCornerShape(percent = 50)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(glyph))
        }
    }

    /**
     * The active-destination capsule, at the 64×32dp `NavigationBarItem` draws
     * its own indicator at. Hand-drawn because this app's ten destinations have
     * to scroll, and the real component does not.
     */
    @Composable
    private fun NavPill(selected: Boolean, container: Color, content: @Composable () -> Unit) {
        Box(
            Modifier
                .size(width = 64.dp, height = 32.dp)
                .background(
                    if (selected) container else Color.Transparent,
                    RoundedCornerShape(percent = 50)
                ),
            contentAlignment = Alignment.Center,
            content = { content() }
        )
    }

    /**
     * A filled card at the M3 shape scale. Filled cards are specified at 0dp —
     * the tone step is the separation — so [elevation] defaults to nothing and
     * only a surface that genuinely floats ever passes anything else.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun FilledCard(
        modifier: Modifier,
        container: Color,
        shape: Shape,
        onClick: (() -> Unit)?,
        elevation: Dp = 0.dp,
        content: @Composable ColumnScope.() -> Unit
    ) {
        val colors = CardDefaults.cardColors(containerColor = container)
        val cardElevation = CardDefaults.cardElevation(defaultElevation = elevation)
        if (onClick != null) {
            M3Card(
                onClick = onClick,
                modifier = modifier,
                shape = shape,
                colors = colors,
                elevation = cardElevation,
                content = content
            )
        } else {
            M3Card(
                modifier = modifier,
                shape = shape,
                colors = colors,
                elevation = cardElevation,
                content = content
            )
        }
    }

    // ------------------------------------------------------------------ chrome

    @OptIn(ExperimentalMaterial3Api::class)
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
        val c = roles()

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            containerColor = c.canvas,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = actions,
                    windowInsets = WindowInsets.statusBars,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        // At rest the small top app bar is `surface`, flush with
                        // the content behind it: no line, no shadow, no fill.
                        containerColor = c.canvas,
                        titleContentColor = palette.textPrimary,
                        actionIconContentColor = palette.textSecondary,
                        navigationIconContentColor = palette.textSecondary
                    )
                )
            },
            bottomBar = {
                if (navItems.size <= 5) {
                    // Within the component's specified range, so use it: the
                    // indicator, the icon crossfade and the insets come free.
                    NavigationBar(
                        modifier = Modifier.testTag("bottom_nav_bar"),
                        // surfaceContainer, and *no* tonal elevation on top of
                        // it: the bar is already a tone step above the canvas,
                        // and stacking an elevation overlay on a container
                        // colour counts the same step twice.
                        containerColor = c.container,
                        tonalElevation = 0.dp
                    ) {
                        navItems.forEachIndexed { index, item ->
                            val isSelected = index == selectedNavIndex
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = { onNavSelected(index) },
                                modifier = Modifier.testTag(item.tag),
                                alwaysShowLabel = true,
                                icon = {
                                    Icon(
                                        imageVector = if (isSelected) item.selectedIcon else item.icon,
                                        contentDescription = item.label,
                                        modifier = Modifier.size(design.decor.navIconSize)
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.label,
                                        style = styles.navLabel,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = c.onAccentSofter,
                                    selectedTextColor = palette.textPrimary,
                                    indicatorColor = c.accentSofter,
                                    unselectedIconColor = palette.textSecondary,
                                    unselectedTextColor = palette.textSecondary
                                )
                            )
                        }
                    }
                } else {
                    Surface(color = c.container) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .navigationBarsPadding()
                                .testTag("bottom_nav_bar")
                                .horizontalScroll(rememberScrollState())
                                .padding(horizontal = 8.dp)
                                // 12 above the pill, 16 below the label: the
                                // navigation bar's own 80dp vertical rhythm.
                                .padding(top = 12.dp, bottom = 16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            navItems.forEachIndexed { index, item ->
                                val isSelected = index == selectedNavIndex
                                Column(
                                    modifier = Modifier
                                        .widthIn(min = 72.dp)
                                        .clip(RoundedCornerShape(percent = 50))
                                        .clickable { onNavSelected(index) }
                                        .padding(horizontal = 4.dp)
                                        .testTag(item.tag),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    NavPill(isSelected, c.accentSofter) {
                                        Icon(
                                            imageVector = if (isSelected) item.selectedIcon else item.icon,
                                            contentDescription = item.label,
                                            tint = if (isSelected) {
                                                c.onAccentSofter
                                            } else {
                                                palette.textSecondary
                                            },
                                            modifier = Modifier.size(design.decor.navIconSize)
                                        )
                                    }
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = item.label,
                                        style = styles.navLabel,
                                        color = if (isSelected) palette.textPrimary else palette.textSecondary,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun TopBar(
        title: String,
        subtitle: String?,
        onBack: (() -> Unit)?,
        actions: @Composable RowScope.() -> Unit
    ) {
        val palette = LocalAppDesign.current.palette
        val c = roles()
        CenterAlignedTopAppBar(
            title = {
                // 1.3.0's app bars have no subtitle slot, so the two lines sit
                // in the title slot together and stay centred as a pair.
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = palette.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.labelMedium,
                            color = palette.textSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            },
            navigationIcon = {
                if (onBack != null) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            },
            actions = actions,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = c.canvas,
                titleContentColor = palette.textPrimary,
                actionIconContentColor = palette.textSecondary,
                navigationIconContentColor = palette.textSecondary
            )
        )
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
                // headerTopGap above, 16 below: the first control has to sit a
                // full step clear of the headline, not the 8 it had.
                .padding(top = design.spacing.headerTopGap, bottom = 16.dp)
        ) {
            Text(
                text = title,
                style = styles.screenTitle,
                color = palette.textPrimary
            )
            if (subtitle != null) {
                // Supporting text hangs 4dp off the headline, not 6: headline
                // and its support are one block, read together.
                Spacer(Modifier.height(4.dp))
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
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                // 20/8: a section break has to out-weigh the 8dp gap between the
                // cards inside a section, or the groups stop reading as groups.
                .padding(top = 20.dp, bottom = 8.dp)
        ) {
            Text(
                text = title,
                style = styles.sectionTitle,
                color = palette.textPrimary
            )
            if (subtitle != null) {
                Spacer(Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
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
        val c = roles()
        FilledCard(
            modifier = modifier.fillMaxWidth(),
            container = c.container,
            shape = design.shapes.card,
            onClick = onClick
        ) {
            Column(Modifier.padding(design.spacing.cardPadding), content = content)
        }
    }

    @Composable
    override fun ListGroup(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) {
        val spacing = LocalAppDesign.current.spacing
        // No grouped block: M3 gives each row its own card, so the group is
        // only a rhythm of gaps.
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.gutter),
            verticalArrangement = Arrangement.spacedBy(spacing.listItemGap),
            content = content
        )
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
        val c = roles()

        val container = if (selected) c.accentSofter else c.container
        val titleColor = if (selected) c.onAccentSofter else palette.textPrimary

        FilledCard(
            modifier = modifier.fillMaxWidth(),
            container = container,
            shape = design.shapes.card,
            onClick = onClick
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    // Two-line list item: 72dp, with the 16/12 insets the spec
                    // puts around a leading avatar.
                    .heightIn(min = 72.dp)
                    .padding(horizontal = 16.dp, vertical = design.spacing.rowPaddingVertical),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    TonalIconCircle(
                        icon = leadingIcon,
                        // Derived from the accent, not from the scheme's
                        // baseline containers: a green app has green circles.
                        container = if (selected) c.accentSoft else c.high,
                        tint = if (selected) c.onAccentSoft else MaterialTheme.colorScheme.primary,
                        glyph = design.decor.rowIconSize
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap))
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = styles.rowTitle,
                        color = titleColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (subtitle != null) {
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = subtitle,
                            style = styles.rowSubtitle,
                            color = palette.textSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                // decor.usesChevrons is false here: M3 states the destination
                // with a trailing value, not with a disclosure arrow, so
                // showsNavigationAffordance draws nothing.
                if (trailingText != null) {
                    Spacer(Modifier.width(16.dp))
                    Text(
                        text = trailingText,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (selected) c.onAccentSofter else palette.textSecondary,
                        maxLines = 1
                    )
                }
            }
        }
    }

    @Composable
    override fun Divider(modifier: Modifier) {
        HorizontalDivider(
            modifier = modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = roles().outline
        )
    }

    @Composable
    override fun Ornament(modifier: Modifier) {
        // Material has no ornament; the section break is pure space.
        Spacer(modifier.fillMaxWidth().height(LocalAppDesign.current.spacing.sectionGap))
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
        val scheme = MaterialTheme.colorScheme
        Button(
            onClick = onClick,
            modifier = modifier.heightIn(min = 40.dp),
            enabled = enabled,
            shape = design.shapes.button,
            colors = ButtonDefaults.buttonColors(
                containerColor = scheme.primary,
                contentColor = scheme.onPrimary
            ),
            // 24dp ends, 16dp before the label when a button leads with an
            // icon: the spec's own button insets.
            contentPadding = PaddingValues(
                start = if (leadingIcon != null) 16.dp else 24.dp,
                end = 24.dp,
                top = 10.dp,
                bottom = 10.dp
            )
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text = label, style = MaterialTheme.typography.labelLarge, maxLines = 1)
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
        val c = roles()
        FilledTonalButton(
            onClick = onClick,
            modifier = modifier.heightIn(min = 40.dp),
            enabled = enabled,
            shape = design.shapes.button,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = c.accentSofter,
                contentColor = c.onAccentSofter
            ),
            contentPadding = PaddingValues(
                start = if (leadingIcon != null) 16.dp else 24.dp,
                end = 24.dp,
                top = 10.dp,
                bottom = 10.dp
            )
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text = label, style = MaterialTheme.typography.labelLarge, maxLines = 1)
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
        val c = roles()
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                // The docked search field is 56dp. Nothing else in M3 is this
                // tall, which is exactly what makes it read as search.
                .heightIn(min = 56.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = palette.textSecondary,
                    maxLines = 1
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = null,
                    tint = palette.textSecondary,
                    modifier = Modifier.padding(start = 4.dp).size(24.dp)
                )
            },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = null,
                            tint = palette.textSecondary
                        )
                    }
                }
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            // Search is a stadium in M3, not a rounded rectangle — shapes.field
            // (16dp) is the text-field shape, and using it here is the single
            // loudest "not a Google app" signal the screen gives off.
            shape = RoundedCornerShape(percent = 50),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = c.high,
                unfocusedContainerColor = c.high,
                disabledContainerColor = c.high,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = palette.textPrimary,
                unfocusedTextColor = palette.textPrimary,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun SegmentedTabs(
        options: List<String>,
        selectedIndex: Int,
        onSelect: (Int) -> Unit,
        modifier: Modifier
    ) {
        val c = roles()
        val palette = LocalAppDesign.current.palette
        // No gutter of its own: every other component in this kit takes its
        // inset from the caller, and adding one here double-padded the control
        // to 32dp while the search field above it sat at 16dp.
        if (options.size > 5) {
            // Segmented buttons do not scroll, and past five segments the end
            // caps stop reading as one control — so the set becomes chips.
            Row(
                modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                options.forEachIndexed { index, option ->
                    Chip(
                        label = option,
                        selected = index == selectedIndex,
                        onClick = { onSelect(index) },
                        modifier = Modifier
                    )
                }
            }
        } else {
            SingleChoiceSegmentedButtonRow(modifier = modifier.fillMaxWidth()) {
                options.forEachIndexed { index, option ->
                    val isSelected = index == selectedIndex
                    SegmentedButton(
                        selected = isSelected,
                        onClick = { onSelect(index) },
                        shape = SegmentedButtonDefaults.itemShape(index, options.size),
                        modifier = Modifier.weight(1f),
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = c.accentSofter,
                            activeContentColor = c.onAccentSofter,
                            activeBorderColor = c.outline,
                            inactiveContainerColor = Color.Transparent,
                            inactiveContentColor = palette.textSecondary,
                            inactiveBorderColor = c.outline
                        ),
                        label = {
                            Text(
                                text = option,
                                style = MaterialTheme.typography.labelLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Chip(label: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val c = roles()
        FilterChip(
            selected = selected,
            onClick = onClick,
            label = { Text(text = label, style = MaterialTheme.typography.labelLarge, maxLines = 1) },
            modifier = modifier,
            leadingIcon = null,
            shape = design.shapes.chip,
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color.Transparent,
                labelColor = palette.textSecondary,
                selectedContainerColor = c.accentSofter,
                selectedLabelColor = c.onAccentSofter
            ),
            border = FilterChipDefaults.filterChipBorder(
                enabled = true,
                selected = selected,
                borderColor = c.outline,
                selectedBorderColor = Color.Transparent
            )
        )
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
        val c = roles()

        Row(
            modifier
                .fillMaxWidth()
                .widthIn(max = design.spacing.readingMaxWidth)
                .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
                // decor.usesDropCaps is false, so a chapter opens with air
                // rather than with an initial.
                .padding(top = if (isFirstOfChapter) 16.dp else 6.dp)
                .padding(start = design.spacing.gutter, end = design.spacing.gutter, bottom = 6.dp),
            verticalAlignment = Alignment.Top
        ) {
            // A 24dp tonal disc, not a rounded tag: it is the same circle the
            // list rows and the metric tiles use, one size down, so the page
            // has one shape language for "a glyph in a tint" instead of two.
            Box(
                Modifier
                    .padding(top = 3.dp)
                    .size(24.dp)
                    .background(c.accentSofter, RoundedCornerShape(percent = 50)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$number",
                    style = MaterialTheme.typography.labelSmall,
                    color = c.onAccentSofter,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = text,
                style = styles.scripture,
                color = palette.textPrimary,
                modifier = Modifier.weight(1f)
            )
            if (hasCommentary) {
                Spacer(Modifier.width(8.dp))
                Icon(
                    Icons.Rounded.AutoAwesome,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    // 6dp drops the 16dp glyph onto the optical centre of the
                    // 28sp first line rather than its ascender.
                    modifier = Modifier.padding(top = 6.dp).size(16.dp)
                )
            }
        }
    }

    @Composable
    override fun MetricTile(value: String, caption: String, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val c = roles()
        FilledCard(
            modifier = modifier,
            container = c.high,
            shape = design.shapes.card,
            onClick = null
        ) {
            // The tile runs on a fixed grid so three of them in a row share
            // every horizontal edge: 16 inset, 32dp disc, 12, the number, 4,
            // a caption held at two lines whether or not it wraps. Without
            // the two-line floor a one-word caption lifts its tile's baseline
            // out of line with its neighbours'.
            Column(Modifier.padding(16.dp)) {
                if (icon != null) {
                    TonalIconCircle(
                        icon = icon,
                        container = c.accentSoft,
                        tint = c.onAccentSoft,
                        size = 32.dp,
                        glyph = 18.dp
                    )
                    Spacer(Modifier.height(12.dp))
                }
                Text(
                    text = value,
                    // headlineMedium, not displaySmall: at a third of the width
                    // a 36sp figure cannot hold "1,328" and the number clips
                    // mid-digit. 28sp fits four figures and a separator with
                    // room to spare, and is what a stat tile is set in anyway.
                    style = MaterialTheme.typography.headlineMedium,
                    color = palette.textPrimary,
                    maxLines = 1,
                    softWrap = false
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = caption,
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.textSecondary,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    @Composable
    override fun EmptyState(title: String, body: String?, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val c = roles()
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter, vertical = design.spacing.sectionGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // The glyph sits in the same tonal disc the rest of the identity
            // uses, at hero size. A bare 48dp outline floating on the canvas is
            // the giveaway that an empty state was an afterthought.
            TonalIconCircle(
                icon = icon ?: Icons.Rounded.Inbox,
                container = c.accentSoft,
                tint = c.onAccentSoft,
                size = 72.dp,
                glyph = 36.dp
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = palette.textPrimary,
                textAlign = TextAlign.Center
            )
            if (body != null) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = palette.textSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.widthIn(max = 320.dp)
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
        val c = roles()
        val given = accentColorHex?.let { Color(it) }
        // M3 tints the whole container rather than hanging a colour bar off the
        // leading edge, so a liturgical colour arrives as a wash over the
        // surface role instead of as a stripe. In dark mode the wash has to be
        // carried by a *light* version of the colour — a dark green at 16% over
        // a dark surface is indistinguishable from the surface.
        val container = when {
            given == null -> c.accentSoft
            design.isDark -> given.lighten(0.12f).copy(alpha = 0.26f).compositeOver(c.high)
            else -> given.copy(alpha = 0.18f).compositeOver(c.high)
        }
        // The eyebrow was the liturgical colour at its literal hex, which on a
        // wash of itself in dark mode fell to roughly 2:1. Solve it instead.
        val eyebrowColor = (given ?: MaterialTheme.colorScheme.primary).readableOn(container)
        val bodyColor = palette.textPrimary.readableOn(container, target = 7f)

        FilledCard(
            modifier = modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
            container = container,
            // cardLarge, 28dp: the banner is the one hero surface on the page
            // and it has to out-shape the 16dp cards under it.
            shape = design.shapes.cardLarge,
            onClick = onClick
        ) {
            Column(Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = eyebrowColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                if (body != null) {
                    Text(
                        text = body,
                        style = MaterialTheme.typography.titleMedium,
                        color = bodyColor
                    )
                }
            }
        }
    }
}
