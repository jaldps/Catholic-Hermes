package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.design.AppIconSlot
import com.example.ui.design.AppIcons
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.LocalAppDesign

/** One secondary destination as the More screen lists it. */
data class MoreEntry(
    val title: String,
    val iconSlot: AppIconSlot,
    val tag: String,
    val value: String? = null,
    /** Overrides the slot's glyph, for rows that are settings, not places. */
    val icon: ImageVector? = null,
    val onOpen: () -> Unit
)

/** A titled run of [MoreEntry]s. */
data class MoreSection(val title: String, val entries: List<MoreEntry>)

/**
 * The fifth tab. Everything that does not earn a place in the tab bar is one
 * tap away here, grouped by what the reader is doing rather than listed flat:
 * a tab bar holds five items in iOS and Material alike, and the app has eleven.
 *
 * AAA M3 treatment, kept inside the shared kit on purpose: the screen paints
 * with nothing but [LocalAppDesign] tokens (canvas, spacing, shape via
 * [AppListGroup]/[AppListRow]) and lets the active identity — Material 3
 * included — resolve tonal surfaces, icon style, chevrons and the dark palette
 * (MaterialPaletteDark / ApplePaletteDark / VaticanPaletteDark). No hardcoded
 * colour or dimension, so dark mode is the token's job, not this file's.
 *
 * Rows are tappable at a 48dp floor on top of the identity's own row height
 * — `maxOf(rowMinHeight, 48.dp)`, the same floor the other redesigned screens
 * use — so a one-line setting row never shrinks below the AAA touch target
 * even under the Apple identity's 44dp token.
 */
@Composable
fun MoreScreen(
    currentLanguage: AppLanguage,
    sections: List<MoreSection>
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val rowMinHeight = maxOf(design.spacing.rowMinHeight, 48.dp)

    // Current values for the Settings rows, read off the tokens so they
    // follow a theme switch and a light/dark change with no caller rebuild.
    // e.g. "Material 3 · Oscuro" / "Material 3 · Dark" for Appearance.
    val appearanceValue = run {
        val name = design.language.label.get(currentLanguage)
        val mode = when (currentLanguage) {
            AppLanguage.ES -> if (design.isDark) "Oscuro" else "Claro"
            AppLanguage.EN -> if (design.isDark) "Dark" else "Light"
            AppLanguage.PT -> if (design.isDark) "Escuro" else "Claro"
        }
        "$name · $mode"
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(palette.canvas)
            .testTag("more_screen")
    ) {
        item(key = "more_title") {
            AppScreenTitle(
                title = when (currentLanguage) {
                    AppLanguage.ES -> "Más"
                    AppLanguage.EN -> "More"
                    AppLanguage.PT -> "Mais"
                }
            )
        }
        // Drop empty groups: a header over no rows reads as a broken section
        // and costs a full section-gap of dead canvas.
        val visibleSections = sections.filter { it.entries.isNotEmpty() }
        visibleSections.forEachIndexed { sectionIndex, section ->
            if (sectionIndex > 0) {
                item(key = "more_section_gap_$sectionIndex") {
                    Spacer(Modifier.height(design.spacing.sectionGap))
                }
            }
            item(key = "more_section_$sectionIndex") {
                AppSectionHeader(title = section.title)
                AppListGroup {
                    section.entries.forEach { entry ->
                        val icon: ImageVector = entry.icon ?: AppIcons.pair(entry.iconSlot).first
                        AppListRow(
                            title = entry.title,
                            // The trailing slot is the current value, not
                            // decoration: Font/Language arrive with one from
                            // the caller; Appearance/Language fall back to
                            // what this screen can know on its own (the
                            // identity in force + dark mode, the active
                            // language) so the row never shows a bare title.
                            trailingText = entry.value ?: settingsFallbackValue(
                                entry = entry,
                                appearanceValue = appearanceValue,
                                currentLanguage = currentLanguage
                            ),
                            leadingIcon = icon,
                            // Every entry navigates somewhere (a destination
                            // or a picker), so every row carries the
                            // navigation affordance — the chevron in the
                            // identities that use one.
                            showsNavigationAffordance = true,
                            onClick = entry.onOpen,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = rowMinHeight)
                                .testTag(entry.tag)
                        )
                    }
                }
            }
        }
        item(key = "more_bottom_gap") {
            Spacer(Modifier.height(design.spacing.contentBottomGap))
        }
    }
}

/**
 * Current-value text for the Settings rows whose caller does not supply one.
 * Only the three settings entries are recognised, by their preserved test
 * tags; destination rows always keep a null trailing slot.
 */
private fun settingsFallbackValue(
    entry: MoreEntry,
    appearanceValue: String,
    currentLanguage: AppLanguage
): String? = when (entry.tag) {
    "app_bar_theme_button" -> appearanceValue
    "app_bar_lang_button" -> currentLanguage.displayName
    // Font is caller-supplied (the stored FontChoice label); with no value
    // there is nothing honest to show, so leave the slot empty rather than
    // guessing a typeface.
    "font_settings_button" -> null
    else -> null
}
