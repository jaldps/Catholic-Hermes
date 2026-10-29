package com.example.ui.screens

import com.example.ui.theme.spacing
import com.example.ui.theme.identity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.AppLanguage
import com.example.data.models.ChristManifestation
import com.example.data.models.MarianApparition
import com.example.data.repository.CatholicDataRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevotionsExplorerScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onSpeakText: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var selectedCategoryTab by remember { mutableIntStateOf(0) } // 0 = Marian Advocations, 1 = Christ Devotions
    var searchQuery by remember { mutableStateOf("") }
    var selectedRegionFilter by remember { mutableStateOf("ALL") } // "ALL", "Americas", "Europe", "Universal"

    val marianList = repository.marianApparitions
    val christList = repository.christManifestations

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("devotions_explorer_screen")
    ) {
        SecondaryTabRow(
            selectedTabIndex = selectedCategoryTab,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedCategoryTab == 0,
                onClick = { selectedCategoryTab = 0 },
                text = {
                    Text(
                        text = "Advocaciones Marianas (${marianList.size})",
                        fontWeight = if (selectedCategoryTab == 0) FontWeight.Bold else FontWeight.Normal,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                }
            )
            Tab(
                selected = selectedCategoryTab == 1,
                onClick = { selectedCategoryTab = 1 },
                text = {
                    Text(
                        text = "Devociones a Cristo (${christList.size})",
                        fontWeight = if (selectedCategoryTab == 1) FontWeight.Bold else FontWeight.Normal,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                }
            )
        }

        // Search bar & Region filter chips
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.lg, vertical = MaterialTheme.spacing.sm)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("devotions_search_input"),
                placeholder = {
                    Text(
                        text = "Buscar por nombre, país, santuario o milagro...",
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                item {
                    FilterChip(
                        selected = selectedRegionFilter == "ALL",
                        onClick = { selectedRegionFilter = "ALL" },
                        label = { Text("Todas", fontSize = MaterialTheme.typography.labelSmall.fontSize) }
                    )
                }
                item {
                    FilterChip(
                        selected = selectedRegionFilter == "Americas",
                        onClick = { selectedRegionFilter = "Americas" },
                        label = { Text("América Latina y el Caribe", fontSize = MaterialTheme.typography.labelSmall.fontSize) }
                    )
                }
                item {
                    FilterChip(
                        selected = selectedRegionFilter == "Europe",
                        onClick = { selectedRegionFilter = "Europe" },
                        label = { Text("Europa y Santuarios Históricos", fontSize = MaterialTheme.typography.labelSmall.fontSize) }
                    )
                }
                item {
                    FilterChip(
                        selected = selectedRegionFilter == "Universal",
                        onClick = { selectedRegionFilter = "Universal" },
                        label = { Text("Devociones Universales", fontSize = MaterialTheme.typography.labelSmall.fontSize) }
                    )
                }
            }
        }

        if (selectedCategoryTab == 0) {
            val filteredMarian = marianList.filter { item ->
                val matchesRegion = when (selectedRegionFilter) {
                    "Americas" -> item.region == "Americas"
                    "Europe" -> item.region == "Europe"
                    "Universal" -> item.region == "Universal"
                    else -> true
                }
                val q = searchQuery.trim().lowercase()
                val matchesQuery = q.isEmpty() ||
                        item.title.get(currentLanguage).lowercase().contains(q) ||
                        item.country.get(currentLanguage).lowercase().contains(q) ||
                        item.patronageOf.get(currentLanguage).lowercase().contains(q) ||
                        item.primarySanctuary.get(currentLanguage).lowercase().contains(q) ||
                        item.recordedMiracles.get(currentLanguage).lowercase().contains(q)

                matchesRegion && matchesQuery
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg)
            ) {
                item {
                    Text(
                        text = "Mostrando ${filteredMarian.size} advocaciones marianas aprobadas por la Iglesia",
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                items(filteredMarian) { apparition ->
                    DetailedMarianCard(
                        item = apparition,
                        currentLanguage = currentLanguage,
                        onSpeakPrayer = onSpeakText
                    )
                }

                item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.xl)) }
            }
        } else {
            val filteredChrist = christList.filter { item ->
                val matchesRegion = when (selectedRegionFilter) {
                    "Americas" -> item.region == "Americas"
                    "Europe" -> item.region == "Europe"
                    "Universal" -> item.region == "Universal"
                    else -> true
                }
                val q = searchQuery.trim().lowercase()
                val matchesQuery = q.isEmpty() ||
                        item.title.get(currentLanguage).lowercase().contains(q) ||
                        item.country.get(currentLanguage).lowercase().contains(q) ||
                        item.primarySanctuary.get(currentLanguage).lowercase().contains(q) ||
                        item.originHistory.get(currentLanguage).lowercase().contains(q) ||
                        item.recordedMiracles.get(currentLanguage).lowercase().contains(q)

                matchesRegion && matchesQuery
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg)
            ) {
                item {
                    Text(
                        text = "Mostrando ${filteredChrist.size} manifestaciones sagradas de Jesucristo",
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                items(filteredChrist) { manifestation ->
                    DetailedChristCard(
                        item = manifestation,
                        currentLanguage = currentLanguage,
                        onSpeakPrayer = onSpeakText
                    )
                }

                item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.xl)) }
            }
        }
    }
}

@Composable
private fun DetailedMarianCard(
    item: MarianApparition,
    currentLanguage: AppLanguage,
    onSpeakPrayer: ((String) -> Unit)? = null
) {
    var showFullPrayer by remember { mutableStateOf(false) }

    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs / 2))
                    Text(
                        text = "País: ${item.country.get(currentLanguage)} • Año ${item.year}",
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = item.feastDay,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.sm, vertical = MaterialTheme.spacing.xs)
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            // Patronage badge
            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.sm, vertical = MaterialTheme.spacing.xs),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(MaterialTheme.spacing.md))
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
                    Text(
                        text = item.patronageOf.get(currentLanguage),
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            Text(
                text = item.historyAndApparition.get(currentLanguage),
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                lineHeight = MaterialTheme.typography.bodySmall.lineHeight,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            // Sanctuary
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Place, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(MaterialTheme.spacing.md))
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
                Text(
                    text = "Santuario: ${item.primarySanctuary.get(currentLanguage)}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))

            // Miracles
            Text(
                text = "Milagros destacados: " + item.recordedMiracles.get(currentLanguage),
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                lineHeight = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            // Traditional Prayer Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (showFullPrayer) "Ocultar Oración" else "Ver Oración Tradicional",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { showFullPrayer = !showFullPrayer }
                )

                if (onSpeakPrayer != null) {
                    IconButton(
                        onClick = { onSpeakPrayer(item.venerationPrayer.get(currentLanguage)) },
                        modifier = Modifier.size(MaterialTheme.spacing.xxl)
                    ) {
                        Icon(Icons.Default.VolumeUp, contentDescription = "Listen", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(MaterialTheme.identity.actionIconSize))
                    }
                }
            }

            AnimatedVisibility(visible = showFullPrayer) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {
                        Text(
                            text = "Oración de Veneración e Intercesión:",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.venerationPrayer.get(currentLanguage),
                            fontFamily = FontFamily.Serif,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            lineHeight = MaterialTheme.typography.bodySmall.lineHeight,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailedChristCard(
    item: ChristManifestation,
    currentLanguage: AppLanguage,
    onSpeakPrayer: ((String) -> Unit)? = null
) {
    var showFullPrayer by remember { mutableStateOf(false) }

    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs / 2))
                    Text(
                        text = "País / Región: ${item.country.get(currentLanguage)}",
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                ) {
                    Text(
                        text = item.feastDay.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.sm, vertical = MaterialTheme.spacing.xs)
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            Text(
                text = item.originHistory.get(currentLanguage),
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                lineHeight = MaterialTheme.typography.bodySmall.lineHeight,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            // Primary Sanctuary
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Church, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(MaterialTheme.spacing.md))
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
                Text(
                    text = "Santuario Principal: ${item.primarySanctuary.get(currentLanguage)}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))

            // Miracles and promises
            Text(
                text = "Milagros y Promesas: " + item.promises.get(currentLanguage),
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                lineHeight = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (showFullPrayer) "Ocultar Oración" else "Ver Oración Sagrada",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { showFullPrayer = !showFullPrayer }
                )

                if (onSpeakPrayer != null) {
                    IconButton(
                        onClick = { onSpeakPrayer(item.officialPrayer.get(currentLanguage)) },
                        modifier = Modifier.size(MaterialTheme.spacing.xxl)
                    ) {
                        Icon(Icons.Default.VolumeUp, contentDescription = "Listen", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(MaterialTheme.identity.actionIconSize))
                    }
                }
            }

            AnimatedVisibility(visible = showFullPrayer) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {
                        Text(
                            text = "Oración Oficial:",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.officialPrayer.get(currentLanguage),
                            fontFamily = FontFamily.Serif,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            lineHeight = MaterialTheme.typography.bodySmall.lineHeight,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
