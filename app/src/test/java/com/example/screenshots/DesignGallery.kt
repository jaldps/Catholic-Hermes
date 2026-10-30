package com.example.screenshots

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.design.AppBanner
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppMetricTile
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.design.AppVerseRow
import com.example.ui.theme.LocalAppDesign

/**
 * One screen that exercises every component in [com.example.ui.design.AppKit],
 * with realistic content, so a rendered capture of it shows the whole identity
 * at once rather than a grid of isolated widgets.
 */
@Composable
fun DesignGallery() {
    val design = LocalAppDesign.current
    var tab by remember { mutableIntStateOf(0) }
    var chip by remember { mutableIntStateOf(1) }
    var query by remember { mutableStateOf("") }

    LazyColumn(Modifier.fillMaxWidth()) {
        item {
            AppScreenTitle(
                title = "Sacred Scripture",
                subtitle = "The Catholic canon in 73 books, Douay-Rheims"
            )
        }

        item {
            AppSearchField(
                value = query,
                onValueChange = { query = it },
                placeholder = "Search chapter or verse",
                modifier = Modifier.padding(horizontal = design.spacing.gutter)
            )
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        item {
            AppSegmentedTabs(
                options = listOf("Reader", "Missal", "Study"),
                selectedIndex = tab,
                onSelect = { tab = it },
                modifier = Modifier.padding(horizontal = design.spacing.gutter)
            )
        }

        item {
            AppBanner(
                title = "Thirty-First Week in Ordinary Time",
                body = "Green · Cycle A · Weekday Cycle II",
                accentColorHex = 0xFF2C6B3E,
                onClick = {},
                modifier = Modifier.padding(top = design.spacing.sectionGap / 2)
            )
        }

        item { AppSectionHeader(title = "Today", subtitle = "Thursday, 29 October") }

        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
            ) {
                val tile = Modifier.weight(1f).fillMaxHeight()
                AppMetricTile("31", "Ordinary week", Icons.Default.Church, tile)
                AppMetricTile("1,328", "Chapters", Icons.Default.MenuBook, tile)
                AppMetricTile("73", "Books", Icons.Default.AutoStories, tile)
            }
        }

        item { AppSectionHeader(title = "The Pentateuch") }

        item {
            AppListGroup {
                AppListRow(
                    title = "Genesis",
                    subtitle = "50 chapters · Pentateuch",
                    leadingIcon = Icons.Default.AutoStories,
                    trailingText = "Gn",
                    onClick = {}
                )
                AppListRow(
                    title = "Exodus",
                    subtitle = "40 chapters · Pentateuch",
                    leadingIcon = Icons.Default.AutoStories,
                    trailingText = "Ex",
                    onClick = {}
                )
                AppListRow(
                    title = "Leviticus",
                    subtitle = "27 chapters · Pentateuch",
                    leadingIcon = Icons.Default.AutoStories,
                    trailingText = "Lv",
                    selected = true,
                    onClick = {}
                )
            }
        }

        item { AppOrnament() }

        item { AppSectionHeader(title = "John 1", subtitle = "The Word made flesh") }

        item {
            AppVerseRow(
                number = 1,
                text = "In the beginning was the Word: and the Word was with God: and the Word was God.",
                isFirstOfChapter = true,
                hasCommentary = true,
                onClick = {}
            )
            AppVerseRow(
                number = 2,
                text = "The same was in the beginning with God.",
                onClick = {}
            )
            AppVerseRow(
                number = 3,
                text = "All things were made by him: and without him was made nothing that was made.",
                hasCommentary = true,
                onClick = {}
            )
        }

        item { AppDivider(Modifier.padding(vertical = 8.dp)) }

        item {
            AppCard(
                modifier = Modifier.padding(horizontal = design.spacing.gutter),
                onClick = {}
            ) {
                AppSectionHeader(title = "Commentary", subtitle = "St. John Chrysostom")
                Spacer(Modifier.height(8.dp))
                AppPrimaryButton(
                    label = "Read the homily",
                    onClick = {},
                    leadingIcon = Icons.Default.MenuBook,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                AppSecondaryButton(
                    label = "Memorise this verse",
                    onClick = {},
                    leadingIcon = Icons.Default.Favorite,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item { AppSectionHeader(title = "Filter") }

        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("All", "Old Testament", "New").forEachIndexed { index, label ->
                    AppChip(label = label, selected = chip == index, onClick = { chip = index })
                }
            }
        }

        item {
            AppEmptyState(
                title = "No memorised verses yet",
                body = "Verses you commit to memory will gather here.",
                icon = Icons.Default.SelfImprovement,
                modifier = Modifier.padding(top = design.spacing.sectionGap)
            )
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}
