package com.example.ui.design

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign

/** The kit for the identity currently in force. */
@Composable
@ReadOnlyComposable
fun appKit(): AppKit = when (LocalAppDesign.current.language) {
    DesignLanguage.APPLE -> AppleKit
    DesignLanguage.MATERIAL -> MaterialKit
    DesignLanguage.VATICAN -> VaticanKit
}

// Screens call these. They carry the default arguments an interface cannot, and
// keep every call site free of any one identity's component names.

@Composable
fun AppScaffold(
    title: String,
    navItems: List<AppNavItem>,
    selectedNavIndex: Int,
    onNavSelected: (Int) -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) = appKit().Scaffold(title, navItems, selectedNavIndex, onNavSelected, actions, content)

@Composable
fun AppTopBar(
    title: String,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) = appKit().TopBar(title, subtitle, onBack, actions)

@Composable
fun AppScreenTitle(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) = appKit().ScreenTitle(title, subtitle, modifier)

@Composable
fun AppSectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) = appKit().SectionHeader(title, subtitle, modifier)

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) = appKit().Card(modifier, onClick, content)

@Composable
fun AppListGroup(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) = appKit().ListGroup(modifier, content)

@Composable
fun AppListRow(
    title: String,
    subtitle: String? = null,
    leadingIcon: ImageVector? = null,
    trailingText: String? = null,
    showsNavigationAffordance: Boolean = true,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) = appKit().Row(
    title, subtitle, leadingIcon, trailingText,
    showsNavigationAffordance, selected, onClick, modifier
)

@Composable
fun AppDivider(modifier: Modifier = Modifier) = appKit().Divider(modifier)

@Composable
fun AppOrnament(modifier: Modifier = Modifier) = appKit().Ornament(modifier)

@Composable
fun AppPrimaryButton(
    label: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier
) = appKit().PrimaryButton(label, onClick, enabled, leadingIcon, modifier)

@Composable
fun AppSecondaryButton(
    label: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier
) = appKit().SecondaryButton(label, onClick, enabled, leadingIcon, modifier)

@Composable
fun AppSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) = appKit().SearchField(value, onValueChange, placeholder, modifier)

@Composable
fun AppSegmentedTabs(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) = appKit().SegmentedTabs(options, selectedIndex, onSelect, modifier)

@Composable
fun AppChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = appKit().Chip(label, selected, onClick, modifier)

@Composable
fun AppVerseRow(
    number: Int,
    text: String,
    isFirstOfChapter: Boolean = false,
    hasCommentary: Boolean = false,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) = appKit().VerseRow(number, text, isFirstOfChapter, hasCommentary, onClick, modifier)

@Composable
fun AppMetricTile(
    value: String,
    caption: String,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) = appKit().MetricTile(value, caption, icon, modifier)

@Composable
fun AppEmptyState(
    title: String,
    body: String? = null,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) = appKit().EmptyState(title, body, icon, modifier)

@Composable
fun AppBanner(
    title: String,
    body: String? = null,
    accentColorHex: Long? = null,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) = appKit().Banner(title, body, accentColorHex, onClick, modifier)
