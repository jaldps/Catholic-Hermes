package com.example.ui.design

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign

/**
 * Shared kit with complete identity-driven geometry, type, material and chrome.
 * Navigation, semantics and content remain identical when appearance changes.
 */
@Composable
@ReadOnlyComposable
fun appKit(): AppKit = when (LocalAppDesign.current.language) {
    DesignLanguage.APPLE, DesignLanguage.IVE, DesignLanguage.CLASSIC, DesignLanguage.GLASS, DesignLanguage.SANCTUARY -> LumenKit
}

// Screens call these. They carry the default arguments an interface cannot, and
// keep every call site free of any one identity's component names.

@Composable
fun AppScaffold(
    title: String,
    navItems: List<AppNavItem>,
    selectedNavIndex: Int,
    onNavSelected: (Int) -> Unit,
    onBack: (() -> Unit)? = null,
    backLabel: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) = appKit().Scaffold(title, navItems, selectedNavIndex, onNavSelected, onBack, backLabel, actions, content)

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
    modifier: Modifier = Modifier,
    trailingContent: (@Composable () -> Unit)? = null
) = appKit().ScreenTitle(title, subtitle, modifier, trailingContent)

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

/** Multiline notes using the shared card, type and colours. The caller owns the transient value. */
@Composable
fun AppNotesField(value: String, onValueChange: (String) -> Unit, label: String, modifier: Modifier = Modifier) {
    val p = LocalAppDesign.current.palette
    val styles = com.example.ui.theme.LocalAppTextStyles.current
    AppCard {
        androidx.compose.material3.Text(label, style = styles.footnote, color = p.textSecondary)
        androidx.compose.foundation.layout.Spacer(Modifier.height(8.dp))
        androidx.compose.foundation.text.BasicTextField(
            value = value, onValueChange = onValueChange,
            textStyle = styles.body.copy(color = p.textPrimary),
            cursorBrush = androidx.compose.ui.graphics.SolidColor(p.accent), minLines = 4, maxLines = 8,
            // Visible text, requesting a keyboard without spelling suggestions.
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Password, autoCorrectEnabled = false),
            modifier = modifier.fillMaxWidth().semantics { contentDescription = label }
        )
    }
}
