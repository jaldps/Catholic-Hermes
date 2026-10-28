package com.example.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.AppLanguage
import com.example.data.models.ChristManifestation
import com.example.data.models.MarianApparition
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.IdentityTabBar
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.LocalAppThemePreset

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
    val identity = LocalAppThemePreset.current
    val isApple = identity == AppThemePreset.APPLE.id
    val isMaterial = identity == AppThemePreset.MATERIAL.id
    val screenGutter = if (isApple) 20.dp else if (isMaterial) 16.dp else 24.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("devotions_explorer_screen")
    ) {
        IdentityTabBar(
            labels = listOf(
                when (currentLanguage) {
                    AppLanguage.ES -> "Advocaciones Marianas (" + marianList.size + ")"
                    AppLanguage.EN -> "Marian Devotions (" + marianList.size + ")"
                    AppLanguage.PT -> "Devoções Marianas (" + marianList.size + ")"
                },
                when (currentLanguage) {
                    AppLanguage.ES -> "Devociones a Cristo (" + christList.size + ")"
                    AppLanguage.EN -> "Devotions to Christ (" + christList.size + ")"
                    AppLanguage.PT -> "Devoções a Cristo (" + christList.size + ")"
                }
            ),
            selectedIndex = selectedCategoryTab,
            onTabSelected = { selectedCategoryTab = it },
            modifier = Modifier.padding(top = if (isApple) 4.dp else if (isMaterial) 0.dp else 8.dp),
            testTags = listOf("devotions_tab_marian", "devotions_tab_christ")
        )

        // Search bar & Region filter chips
        Column(modifier = Modifier.padding(horizontal = screenGutter, vertical = if (isApple) 6.dp else 8.dp)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("devotions_search_input"),
                placeholder = {
                    Text(
                        text = when (currentLanguage) { AppLanguage.ES -> "Buscar por nombre, país, santuario o milagro..."; AppLanguage.EN -> "Search name, country, shrine or miracle..."; AppLanguage.PT -> "Buscar nome, país, santuário ou milagre..." },
                        fontSize = 12.sp
                    )
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                },
                singleLine = true,
                shape = if (isApple) MaterialTheme.shapes.large else if (isMaterial) MaterialTheme.shapes.medium else MaterialTheme.shapes.small
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    FilterChip(
                        selected = selectedRegionFilter == "ALL",
                        onClick = { selectedRegionFilter = "ALL" },
                        label = { Text(when (currentLanguage) { AppLanguage.ES -> "Todas"; AppLanguage.EN -> "All"; AppLanguage.PT -> "Todas" }, fontSize = 11.sp) }
                    )
                }
                item {
                    FilterChip(
                        selected = selectedRegionFilter == "Americas",
                        onClick = { selectedRegionFilter = "Americas" },
                        label = { Text(when (currentLanguage) { AppLanguage.ES -> "América Latina y el Caribe"; AppLanguage.EN -> "Americas & Caribbean"; AppLanguage.PT -> "Américas e Caribe" }, fontSize = 11.sp) }
                    )
                }
                item {
                    FilterChip(
                        selected = selectedRegionFilter == "Europe",
                        onClick = { selectedRegionFilter = "Europe" },
                        label = { Text(when (currentLanguage) { AppLanguage.ES -> "Europa y Santuarios Históricos"; AppLanguage.EN -> "Europe & historic shrines"; AppLanguage.PT -> "Europa e santuários históricos" }, fontSize = 11.sp) }
                    )
                }
                item {
                    FilterChip(
                        selected = selectedRegionFilter == "Universal",
                        onClick = { selectedRegionFilter = "Universal" },
                        label = { Text(when (currentLanguage) { AppLanguage.ES -> "Devociones Universales"; AppLanguage.EN -> "Universal devotions"; AppLanguage.PT -> "Devoções universais" }, fontSize = 11.sp) }
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
                    .padding(horizontal = screenGutter),
                verticalArrangement = Arrangement.spacedBy(if (isApple) 20.dp else if (isMaterial) 12.dp else 16.dp)
            ) {
                item {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Mostrando " + filteredMarian.size + " advocaciones marianas aprobadas por la Iglesia"
                            AppLanguage.EN -> "Showing " + filteredMarian.size + " Church-approved Marian devotions"
                            AppLanguage.PT -> "Exibindo " + filteredMarian.size + " devoções marianas aprovadas pela Igreja"
                        },
                        fontSize = 12.sp,
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

                item { Spacer(modifier = Modifier.height(24.dp)) }
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Mostrando " + filteredChrist.size + " manifestaciones sagradas de Jesucristo"
                            AppLanguage.EN -> "Showing " + filteredChrist.size + " sacred devotions to Jesus Christ"
                            AppLanguage.PT -> "Exibindo " + filteredChrist.size + " devoções sagradas a Jesus Cristo"
                        },
                        fontSize = 12.sp,
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

                item { Spacer(modifier = Modifier.height(24.dp)) }
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
    val identity = LocalAppThemePreset.current
    val isApple = identity == AppThemePreset.APPLE.id
    val isMaterial = identity == AppThemePreset.MATERIAL.id
    val cardShape = when (identity) {
        AppThemePreset.APPLE.id -> MaterialTheme.shapes.large
        AppThemePreset.MATERIAL.id -> MaterialTheme.shapes.extraLarge
        else -> MaterialTheme.shapes.small
    }

    ElevatedCard(
        shape = cardShape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = when {
                isApple -> MaterialTheme.colorScheme.surfaceVariant
                isMaterial -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.28f)
                else -> MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = if (isMaterial) 3.dp else 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "País: ${item.country.get(currentLanguage)} • Año ${item.year}",
                        fontSize = 11.sp,
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
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Patronage badge
            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = item.patronageOf.get(currentLanguage),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.historyAndApparition.get(currentLanguage),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Sanctuary
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Place, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Santuario: ${item.primarySanctuary.get(currentLanguage)}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Miracles
            Text(
                text = "Milagros destacados: " + item.recordedMiracles.get(currentLanguage),
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Traditional Prayer Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (showFullPrayer) "Ocultar Oración" else "Ver Oración Tradicional",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { showFullPrayer = !showFullPrayer }
                )

                if (onSpeakPrayer != null) {
                    IconButton(
                        onClick = { onSpeakPrayer(item.venerationPrayer.get(currentLanguage)) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Default.VolumeUp, contentDescription = "Listen", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
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
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Oración de Veneración e Intercesión:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.venerationPrayer.get(currentLanguage),
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
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
    val identity = LocalAppThemePreset.current
    val isApple = identity == AppThemePreset.APPLE.id
    val isMaterial = identity == AppThemePreset.MATERIAL.id
    val cardShape = when (identity) {
        AppThemePreset.APPLE.id -> MaterialTheme.shapes.large
        AppThemePreset.MATERIAL.id -> MaterialTheme.shapes.extraLarge
        else -> MaterialTheme.shapes.small
    }

    ElevatedCard(
        shape = cardShape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = when {
                isApple -> MaterialTheme.colorScheme.surfaceVariant
                isMaterial -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.28f)
                else -> MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = if (isMaterial) 3.dp else 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "País / Región: ${item.country.get(currentLanguage)}",
                        fontSize = 11.sp,
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
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.originHistory.get(currentLanguage),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Primary Sanctuary
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Church, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Santuario Principal: ${item.primarySanctuary.get(currentLanguage)}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Miracles and promises
            Text(
                text = "Milagros y Promesas: " + item.promises.get(currentLanguage),
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (showFullPrayer) "Ocultar Oración" else "Ver Oración Sagrada",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { showFullPrayer = !showFullPrayer }
                )

                if (onSpeakPrayer != null) {
                    IconButton(
                        onClick = { onSpeakPrayer(item.officialPrayer.get(currentLanguage)) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Default.VolumeUp, contentDescription = "Listen", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
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
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Oración Oficial:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.officialPrayer.get(currentLanguage),
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
