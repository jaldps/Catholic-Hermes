package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.LocalAppThemePreset

/**
 * Shared identity-specific tab treatment:
 * Apple = compact segmented surface; Material = native indicator row;
 * Vatican = editorial type with a restrained liturgical underline.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentityTabBar(
    labels: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    testTags: List<String> = emptyList()
) {
    val identity = LocalAppThemePreset.current
    val isApple = identity == AppThemePreset.APPLE.id
    val isMaterial = identity == AppThemePreset.MATERIAL.id

    when {
        isMaterial -> PrimaryTabRow(
            selectedTabIndex = selectedIndex,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = modifier
        ) {
            labels.forEachIndexed { index, label ->
                Tab(
                    selected = index == selectedIndex,
                    onClick = { onTabSelected(index) },
                    modifier = testTags.getOrNull(index)?.let { Modifier.testTag(it) } ?: Modifier,
                    text = {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = if (index == selectedIndex) FontWeight.SemiBold else FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }

        isApple -> Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 7.dp),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                labels.forEachIndexed { index, label ->
                    val selected = index == selectedIndex
                    Surface(
                        modifier = (testTags.getOrNull(index)?.let { Modifier.testTag(it) } ?: Modifier)
                            .selectable(
                                selected = selected,
                                role = Role.Tab,
                                onClick = { onTabSelected(index) }
                            ),
                        shape = MaterialTheme.shapes.medium,
                        color = if (selected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant,
                        shadowElevation = if (selected) 1.dp else 0.dp,
                    ) {
                        Text(
                            text = label,
                            modifier = Modifier.padding(horizontal = 13.dp, vertical = 10.dp),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                            color = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
            }
        }

        else -> {
            Column(modifier = modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 18.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    labels.forEachIndexed { index, label ->
                        val selected = index == selectedIndex
                        Column(
                            modifier = Modifier
                                .width(IntrinsicSize.Max)
                                .selectable(
                                    selected = selected,
                                    role = Role.Tab,
                                    onClick = { onTabSelected(index) }
                                )
                                .padding(top = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontFamily = MaterialTheme.typography.headlineSmall.fontFamily,
                                    fontSize = 13.sp
                                ),
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                softWrap = false
                            )
                            Spacer(modifier = Modifier.height(9.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(if (selected) 2.dp else 1.dp)
                                    .background(
                                        if (selected) MaterialTheme.colorScheme.secondary
                                        else MaterialTheme.colorScheme.outline.copy(alpha = 0.22f)
                                    )
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f))
                )
            }
        }
    }
}
