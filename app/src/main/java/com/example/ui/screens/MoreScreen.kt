package com.example.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.graphics.vector.ImageVector
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
    val subtitle: String,
    val iconSlot: AppIconSlot,
    val tag: String,
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
 */
@Composable
fun MoreScreen(
    currentLanguage: AppLanguage,
    sections: List<MoreSection>
) {
    val design = LocalAppDesign.current
    LazyColumn(Modifier.fillMaxSize().testTag("more_screen")) {
        item {
            AppScreenTitle(
                title = when (currentLanguage) {
                    AppLanguage.ES -> "Más"
                    AppLanguage.EN -> "More"
                    AppLanguage.PT -> "Mais"
                }
            )
        }
        sections.forEach { section ->
            item {
                AppSectionHeader(title = section.title)
                AppListGroup {
                    section.entries.forEach { entry ->
                        val icon: ImageVector = entry.icon ?: AppIcons.pair(entry.iconSlot).first
                        AppListRow(
                            title = entry.title,
                            subtitle = entry.subtitle,
                            leadingIcon = icon,
                            onClick = entry.onOpen,
                            modifier = Modifier.testTag(entry.tag)
                        )
                    }
                }
            }
        }
        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}
