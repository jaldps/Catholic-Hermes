package com.example.ui.design

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * One navigation destination as the chrome needs to see it.
 */
data class AppNavItem(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon,
    val tag: String
)

/**
 * The contract every visual identity implements.
 *
 * Screens are written against this and never against Material components
 * directly, so switching identity restyles the whole app rather than
 * recolouring it. Each implementation is free to lay its components out
 * completely differently — an inset grouped row, an elevated card and a ruled
 * folio entry are the same call site.
 */
interface AppKit {

    // -------------------------------------------------------------- chrome

    /**
     * Screen frame: background treatment, top bar and bottom navigation.
     * [content] receives the insets it must respect.
     */
    @Composable
    fun Scaffold(
        title: String,
        navItems: List<AppNavItem>,
        selectedNavIndex: Int,
        onNavSelected: (Int) -> Unit,
        actions: @Composable RowScope.() -> Unit,
        content: @Composable (PaddingValues) -> Unit
    )

    /** A top bar for a nested screen, with an optional back affordance. */
    @Composable
    fun TopBar(
        title: String,
        subtitle: String?,
        onBack: (() -> Unit)?,
        actions: @Composable RowScope.() -> Unit
    )

    /** The oversized title that opens a screen's scroll content. */
    @Composable
    fun ScreenTitle(
        title: String,
        subtitle: String?,
        modifier: Modifier
    )

    // ------------------------------------------------------------ structure

    /** Heading above a group of related content. */
    @Composable
    fun SectionHeader(
        title: String,
        subtitle: String?,
        modifier: Modifier
    )

    /** A self-contained block of content. */
    @Composable
    fun Card(
        modifier: Modifier,
        onClick: (() -> Unit)?,
        content: @Composable ColumnScope.() -> Unit
    )

    /**
     * Container for a run of [Row]s. Cupertino draws one rounded inset block
     * with hairlines between rows; Material gives each row its own surface;
     * Vatican rules them like a printed folio.
     */
    @Composable
    fun ListGroup(
        modifier: Modifier,
        content: @Composable ColumnScope.() -> Unit
    )

    /** A single row inside a [ListGroup]. */
    @Composable
    fun Row(
        title: String,
        subtitle: String?,
        leadingIcon: ImageVector?,
        trailingText: String?,
        showsNavigationAffordance: Boolean,
        selected: Boolean,
        onClick: (() -> Unit)?,
        modifier: Modifier
    )

    /** Separator between rows, in whatever idiom the identity uses. */
    @Composable
    fun Divider(modifier: Modifier)

    /** Decorative rule or ornament between sections; may draw nothing. */
    @Composable
    fun Ornament(modifier: Modifier)

    // --------------------------------------------------------------- inputs

    @Composable
    fun PrimaryButton(
        label: String,
        onClick: () -> Unit,
        enabled: Boolean,
        leadingIcon: ImageVector?,
        modifier: Modifier
    )

    @Composable
    fun SecondaryButton(
        label: String,
        onClick: () -> Unit,
        enabled: Boolean,
        leadingIcon: ImageVector?,
        modifier: Modifier
    )

    @Composable
    fun SearchField(
        value: String,
        onValueChange: (String) -> Unit,
        placeholder: String,
        modifier: Modifier
    )

    /** Mutually exclusive choice across a small set of options. */
    @Composable
    fun SegmentedTabs(
        options: List<String>,
        selectedIndex: Int,
        onSelect: (Int) -> Unit,
        modifier: Modifier
    )

    /** Scrollable filter chips for larger option sets. */
    @Composable
    fun Chip(
        label: String,
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier
    )

    // -------------------------------------------------------------- content

    /**
     * One verse of scripture. [isFirstOfChapter] lets an identity set an
     * illuminated drop cap where that is part of its idiom.
     */
    @Composable
    fun VerseRow(
        number: Int,
        text: String,
        isFirstOfChapter: Boolean,
        hasCommentary: Boolean,
        onClick: (() -> Unit)?,
        modifier: Modifier
    )

    /** A single number with its caption. */
    @Composable
    fun MetricTile(
        value: String,
        caption: String,
        icon: ImageVector?,
        modifier: Modifier
    )

    /** Shown where a list has nothing in it. */
    @Composable
    fun EmptyState(
        title: String,
        body: String?,
        icon: ImageVector?,
        modifier: Modifier
    )

    /** Short attention-drawing strip, e.g. today's liturgical colour. */
    @Composable
    fun Banner(
        title: String,
        body: String?,
        accentColorHex: Long?,
        onClick: (() -> Unit)?,
        modifier: Modifier
    )
}
