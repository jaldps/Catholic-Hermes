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
 * roles, the 16/28dp shape scale, stadium buttons and a pill navigation
 * indicator. Depth is tonal rather than cast — nothing carries a drop shadow
 * beyond the 1dp a filled card is specified at.
 */
object MaterialKit : AppKit {

    /**
     * The signature M3 list idiom: a glyph centred in a tonal circle. [size] is
     * the container, [glyph] the icon drawn inside it.
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
     * The active-destination capsule, at the dimensions `NavigationBarItem`
     * draws its own indicator at. Hand-drawn because this app's ten
     * destinations have to scroll, and the real component does not.
     */
    @Composable
    private fun NavPill(selected: Boolean, content: @Composable () -> Unit) {
        val scheme = MaterialTheme.colorScheme
        Box(
            Modifier
                .size(width = 64.dp, height = 32.dp)
                .background(
                    if (selected) scheme.secondaryContainer else Color.Transparent,
                    RoundedCornerShape(percent = 50)
                ),
            contentAlignment = Alignment.Center,
            content = { content() }
        )
    }

    /** A filled card at the M3 shape scale, clickable only when it has to be. */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun FilledCard(
        modifier: Modifier,
        container: Color,
        shape: Shape,
        elevation: Dp,
        onClick: (() -> Unit)?,
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
        val scheme = MaterialTheme.colorScheme

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            containerColor = palette.canvas,
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
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = palette.canvas,
                        titleContentColor = palette.textPrimary,
                        actionIconContentColor = scheme.onSurfaceVariant,
                        navigationIconContentColor = scheme.onSurfaceVariant
                    )
                )
            },
            bottomBar = {
                if (navItems.size <= 5) {
                    // Within the component's specified range, so use it: the
                    // indicator, the icon crossfade and the insets come free.
                    NavigationBar(
                        modifier = Modifier.testTag("bottom_nav_bar"),
                        containerColor = palette.raised,
                        tonalElevation = design.depth.navElevation
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
                                    selectedIconColor = scheme.onSecondaryContainer,
                                    selectedTextColor = palette.textPrimary,
                                    indicatorColor = scheme.secondaryContainer,
                                    unselectedIconColor = palette.textTertiary,
                                    unselectedTextColor = palette.textTertiary
                                )
                            )
                        }
                    }
                } else {
                    Surface(color = palette.raised, tonalElevation = design.depth.navElevation) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .navigationBarsPadding()
                                .testTag("bottom_nav_bar")
                                .horizontalScroll(rememberScrollState())
                                .padding(horizontal = 8.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            navItems.forEachIndexed { index, item ->
                                val isSelected = index == selectedNavIndex
                                Column(
                                    modifier = Modifier
                                        .widthIn(min = 72.dp)
                                        .clip(design.shapes.card)
                                        .clickable { onNavSelected(index) }
                                        .padding(horizontal = 4.dp, vertical = 2.dp)
                                        .testTag(item.tag),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    NavPill(isSelected) {
                                        Icon(
                                            imageVector = if (isSelected) item.selectedIcon else item.icon,
                                            contentDescription = item.label,
                                            tint = if (isSelected) {
                                                scheme.onSecondaryContainer
                                            } else {
                                                palette.textTertiary
                                            },
                                            modifier = Modifier.size(design.decor.navIconSize)
                                        )
                                    }
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = item.label,
                                        style = styles.navLabel,
                                        color = if (isSelected) palette.textPrimary else palette.textTertiary,
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
        val scheme = MaterialTheme.colorScheme
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
                            style = MaterialTheme.typography.bodyMedium,
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
                containerColor = palette.canvas,
                titleContentColor = palette.textPrimary,
                actionIconContentColor = scheme.onSurfaceVariant,
                navigationIconContentColor = scheme.onSurfaceVariant
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
                .padding(top = design.spacing.headerTopGap, bottom = 8.dp)
        ) {
            Text(
                text = title,
                style = styles.screenTitle,
                color = palette.textPrimary
            )
            if (subtitle != null) {
                Spacer(Modifier.height(6.dp))
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
                .padding(top = design.spacing.sectionGap / 2, bottom = design.spacing.listItemGap)
        ) {
            Text(
                text = title,
                style = styles.sectionTitle,
                color = palette.textPrimary
            )
            if (subtitle != null) {
                Spacer(Modifier.height(4.dp))
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
        FilledCard(
            modifier = modifier.fillMaxWidth(),
            container = MaterialTheme.colorScheme.surfaceContainer,
            shape = design.shapes.card,
            elevation = design.depth.cardElevation,
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
        val scheme = MaterialTheme.colorScheme

        FilledCard(
            modifier = modifier.fillMaxWidth(),
            container = if (selected) scheme.secondaryContainer else palette.raised,
            shape = design.shapes.card,
            elevation = design.depth.cardElevation,
            onClick = onClick
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = design.spacing.rowMinHeight)
                    .padding(horizontal = 16.dp, vertical = design.spacing.rowPaddingVertical),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    TonalIconCircle(
                        icon = leadingIcon,
                        container = if (selected) scheme.primaryContainer else scheme.secondaryContainer,
                        tint = if (selected) scheme.onPrimaryContainer else scheme.onSecondaryContainer,
                        glyph = design.decor.rowIconSize
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap))
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = styles.rowTitle,
                        color = if (selected) scheme.onSecondaryContainer else palette.textPrimary
                    )
                    if (subtitle != null) {
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = subtitle,
                            style = styles.rowSubtitle,
                            color = palette.textSecondary
                        )
                    }
                }
                // decor.usesChevrons is false here: M3 states the destination
                // with a trailing value, not with a disclosure arrow, so
                // showsNavigationAffordance draws nothing.
                if (trailingText != null) {
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = trailingText,
                        style = MaterialTheme.typography.labelMedium,
                        color = scheme.onSurfaceVariant
                    )
                }
            }
        }
    }

    @Composable
    override fun Divider(modifier: Modifier) {
        val palette = LocalAppDesign.current.palette
        HorizontalDivider(
            modifier = modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = palette.hairline
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
            modifier = modifier,
            enabled = enabled,
            shape = design.shapes.button,
            colors = ButtonDefaults.buttonColors(
                containerColor = scheme.primary,
                contentColor = scheme.onPrimary
            ),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text = label, style = MaterialTheme.typography.labelLarge)
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
        val scheme = MaterialTheme.colorScheme
        FilledTonalButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = design.shapes.button,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = scheme.secondaryContainer,
                contentColor = scheme.onSecondaryContainer
            ),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text = label, style = MaterialTheme.typography.labelLarge)
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
        val scheme = MaterialTheme.colorScheme
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = palette.textTertiary
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = null,
                    tint = scheme.onSurfaceVariant,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = null,
                            tint = scheme.onSurfaceVariant
                        )
                    }
                }
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            shape = design.shapes.field,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = palette.raised,
                unfocusedContainerColor = palette.raised,
                disabledContainerColor = palette.raised,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = palette.textPrimary,
                unfocusedTextColor = palette.textPrimary,
                cursorColor = scheme.primary
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
        val spacing = LocalAppDesign.current.spacing
        if (options.size > 5) {
            // Segmented buttons do not scroll, and past five segments the end
            // caps stop reading as one control — so the set becomes chips.
            Row(
                modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = spacing.gutter),
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
            SingleChoiceSegmentedButtonRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.gutter)
            ) {
                options.forEachIndexed { index, option ->
                    SegmentedButton(
                        selected = index == selectedIndex,
                        onClick = { onSelect(index) },
                        shape = SegmentedButtonDefaults.itemShape(index, options.size),
                        modifier = Modifier.weight(1f),
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
        FilterChip(
            selected = selected,
            onClick = onClick,
            label = { Text(text = label, style = MaterialTheme.typography.labelLarge) },
            modifier = modifier,
            leadingIcon = null,
            shape = design.shapes.chip,
            colors = FilterChipDefaults.filterChipColors()
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
        val scheme = MaterialTheme.colorScheme

        Row(
            modifier
                .fillMaxWidth()
                .widthIn(max = design.spacing.readingMaxWidth)
                .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
                // decor.usesDropCaps is false, so a chapter opens with air
                // rather than with an initial.
                .padding(top = if (isFirstOfChapter) 20.dp else 6.dp)
                .padding(start = design.spacing.gutter, end = design.spacing.gutter, bottom = 6.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                Modifier
                    .widthIn(min = 26.dp)
                    .background(scheme.secondaryContainer, design.shapes.chip)
                    .padding(horizontal = 7.dp, vertical = 3.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$number",
                    style = styles.verseNumber,
                    color = scheme.onSecondaryContainer,
                    textAlign = TextAlign.Center
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
                    tint = scheme.primary,
                    modifier = Modifier.padding(top = 4.dp).size(16.dp)
                )
            }
        }
    }

    @Composable
    override fun MetricTile(value: String, caption: String, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val styles = LocalAppTextStyles.current
        val scheme = MaterialTheme.colorScheme
        FilledCard(
            modifier = modifier,
            container = scheme.surfaceContainerHigh,
            shape = design.shapes.card,
            elevation = design.depth.cardElevation,
            onClick = null
        ) {
            Column(Modifier.padding(design.spacing.cardPadding)) {
                if (icon != null) {
                    TonalIconCircle(
                        icon = icon,
                        container = scheme.primaryContainer,
                        tint = scheme.onPrimaryContainer,
                        size = 36.dp,
                        glyph = 20.dp
                    )
                    Spacer(Modifier.height(14.dp))
                }
                Text(
                    text = value,
                    style = styles.metric,
                    color = palette.textPrimary,
                    maxLines = 1,
                    softWrap = false
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = caption,
                    style = MaterialTheme.typography.labelMedium,
                    color = scheme.onSurfaceVariant
                )
            }
        }
    }

    @Composable
    override fun EmptyState(title: String, body: String?, icon: ImageVector?, modifier: Modifier) {
        val design = LocalAppDesign.current
        val palette = design.palette
        val scheme = MaterialTheme.colorScheme
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter, vertical = design.spacing.sectionGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon ?: Icons.Rounded.Inbox,
                contentDescription = null,
                tint = scheme.onSurfaceVariant,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
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
        val scheme = MaterialTheme.colorScheme
        val accent = accentColorHex?.let { Color(it) }
        // M3 tints the whole container rather than hanging a colour bar off the
        // leading edge, so a liturgical colour arrives as a wash over the
        // surface role instead of as a stripe.
        val container = accent
            ?.copy(alpha = 0.16f)
            ?.compositeOver(scheme.surfaceContainerHigh)
            ?: scheme.primaryContainer
        val eyebrowColor = accent ?: scheme.onPrimaryContainer
        val bodyColor = if (accent != null) design.palette.textPrimary else scheme.onPrimaryContainer

        FilledCard(
            modifier = modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
            container = container,
            shape = design.shapes.cardLarge,
            elevation = 0.dp,
            onClick = onClick
        ) {
            Column(Modifier.padding(design.spacing.cardPadding)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = eyebrowColor
                )
                if (body != null) {
                    Spacer(Modifier.height(6.dp))
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
