package com.example.ui.screens

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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.ScrollableTabRow
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.AppLanguage
import com.example.data.models.CapitalSinVsVirtue
import com.example.data.models.CatholicNewsArticle
import com.example.data.models.ChristManifestation
import com.example.data.models.ChurchHistoryEpoch
import com.example.data.models.ChurchPrecept
import com.example.data.models.ChurchSchismHistory
import com.example.data.models.CommandmentExploration
import com.example.data.models.MarianApparition
import com.example.data.models.NearbyParish
import com.example.data.repository.CatholicDataRepository
import com.example.ui.theme.LiturgicalGold
import com.example.ui.theme.SacredBurgundy

@Composable
fun DoctrineAndHistoryScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    var selectedTopTab by remember { mutableIntStateOf(0) } // 0 = Catecismo y Moral, 1 = Devociones Marianas y Jesús, 2 = Historia y Cismas, 3 = Parroquias y Noticias
    var selectedSubDoctrineTab by remember { mutableIntStateOf(0) } // 0 = Mandamientos, 1 = Preceptos, 2 = Pecados vs Virtudes, 3 = Pecado Mortal vs Venial

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("doctrine_and_history_screen")
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTopTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = SacredBurgundy,
            edgePadding = 12.dp
        ) {
            Tab(
                selected = selectedTopTab == 0,
                onClick = { selectedTopTab = 0 },
                text = { Text("Catecismo y Doctrina", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
            )
            Tab(
                selected = selectedTopTab == 1,
                onClick = { selectedTopTab = 1 },
                text = { Text("María y Jesucristo", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
            )
            Tab(
                selected = selectedTopTab == 2,
                onClick = { selectedTopTab = 2 },
                text = { Text("Historia y Cismas", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
            )
            Tab(
                selected = selectedTopTab == 3,
                onClick = { selectedTopTab = 3 },
                text = { Text("Iglesias y Noticias", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
            )
        }

        when (selectedTopTab) {
            0 -> {
                // Catechism & Moral Theology
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Sub-filter tabs
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        DoctrineChip("10 Mandamientos", selectedSubDoctrineTab == 0) { selectedSubDoctrineTab = 0 }
                        DoctrineChip("5 Preceptos", selectedSubDoctrineTab == 1) { selectedSubDoctrineTab = 1 }
                        DoctrineChip("Pecados vs Virtudes", selectedSubDoctrineTab == 2) { selectedSubDoctrineTab = 2 }
                        DoctrineChip("Mortal vs Venial", selectedSubDoctrineTab == 3) { selectedSubDoctrineTab = 3 }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    when (selectedSubDoctrineTab) {
                        0 -> {
                            // 10 Commandments
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                                items(repository.theTenCommandments) { cmd ->
                                    CommandmentCard(cmd = cmd, currentLanguage = currentLanguage)
                                }
                            }
                        }
                        1 -> {
                            // 5 Precepts of the Church
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                                items(repository.churchPrecepts) { prc ->
                                    PreceptCard(prc = prc, currentLanguage = currentLanguage)
                                }
                            }
                        }
                        2 -> {
                            // 7 Capital Sins vs 7 Virtues
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                                items(repository.capitalSinsVsVirtues) { csv ->
                                    CapitalSinCard(item = csv, currentLanguage = currentLanguage)
                                }
                            }
                        }
                        3 -> {
                            // Mortal vs Venial Sins & Confession Guide
                            val st = repository.sinTheology
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                                item {
                                    ElevatedCard(
                                        shape = RoundedCornerShape(16.dp),
                                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text("Pecado Venial vs Pecado Mortal", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = SacredBurgundy)
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text("✦ Pecado Venial:", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = LiturgicalGold)
                                            Text(st.venialExplanation.get(currentLanguage), fontSize = 13.sp, lineHeight = 20.sp)
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text("✦ Pecado Mortal:", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFFB71C1C))
                                            Text(st.mortalExplanation.get(currentLanguage), fontSize = 13.sp, lineHeight = 20.sp)
                                        }
                                    }
                                }

                                item {
                                    ElevatedCard(
                                        shape = RoundedCornerShape(16.dp),
                                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text("Las 3 Condiciones del Pecado Mortal", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = SacredBurgundy)
                                            Text("Para que un pecado sea mortal se requieren simultáneamente tres condiciones:", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                            Spacer(modifier = Modifier.height(10.dp))
                                            st.mortalThreeConditions.forEach { cond ->
                                                Text("• " + cond.get(currentLanguage), fontSize = 12.sp, lineHeight = 18.sp, modifier = Modifier.padding(vertical = 3.dp))
                                            }
                                        }
                                    }
                                }

                                item {
                                    ElevatedCard(
                                        shape = RoundedCornerShape(16.dp),
                                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f))
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text("Guía para la Confesión y Examen de Conciencia", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = SacredBurgundy)
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(st.confessionExamenGuide.get(currentLanguage), fontSize = 13.sp, lineHeight = 22.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            1 -> {
                // Marian Advocations & Christ Manifestations Explorer
                DevotionsExplorerScreen(
                    repository = repository,
                    currentLanguage = currentLanguage
                )
            }

            2 -> {
                // Church History & Schisms
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = "Línea de Tiempo de la Iglesia Católica",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
                        )
                        Text(
                            text = "2.000 años de fidelidad al mandato de Cristo desde Pentecostés hasta hoy.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    items(repository.churchHistoryEpochs) { epoch ->
                        ChurchEpochCard(epoch = epoch, currentLanguage = currentLanguage)
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Los Cismas Históricos y Diferencias Doctrinales",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
                        )
                        Text(
                            text = "Explicación teológica católica respetuosa y clara sobre el Cisma de 1054 y la Reforma de 1517.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    items(repository.churchSchisms) { schism ->
                        SchismCard(schism = schism, currentLanguage = currentLanguage)
                    }
                }
            }

            3 -> {
                // Parishes & Catholic News
                val context = LocalContext.current
                var parishSearchQuery by remember { mutableStateOf("") }
                var selectedParishFilter by remember { mutableIntStateOf(0) } // 0: All, 1: Confessions, 2: Adoration, 3: Cathedrals
                var selectedCountry by remember { mutableStateOf("Todos") }
                var selectedRegion by remember { mutableStateOf("Todas") }
                var selectedCity by remember { mutableStateOf("Todas") }

                val allCountries = remember { listOf("Todos") + repository.nearbyParishes.map { it.country }.distinct() }
                val availableRegions = remember(selectedCountry) {
                    if (selectedCountry == "Todos") {
                        listOf("Todas") + repository.nearbyParishes.map { it.stateOrRegion }.distinct()
                    } else {
                        listOf("Todas") + repository.nearbyParishes.filter { it.country == selectedCountry }.map { it.stateOrRegion }.distinct()
                    }
                }
                val availableCities = remember(selectedCountry, selectedRegion) {
                    var list = repository.nearbyParishes
                    if (selectedCountry != "Todos") {
                        list = list.filter { it.country == selectedCountry }
                    }
                    if (selectedRegion != "Todas") {
                        list = list.filter { it.stateOrRegion == selectedRegion }
                    }
                    listOf("Todas") + list.map { it.city }.distinct()
                }

                val filteredParishes = repository.nearbyParishes.filter { parish ->
                    val matchesQuery = parish.name.contains(parishSearchQuery, ignoreCase = true) ||
                            parish.diocese.contains(parishSearchQuery, ignoreCase = true) ||
                            parish.address.contains(parishSearchQuery, ignoreCase = true) ||
                            parish.city.contains(parishSearchQuery, ignoreCase = true) ||
                            parish.country.contains(parishSearchQuery, ignoreCase = true)
                    val matchesCountry = selectedCountry == "Todos" || parish.country == selectedCountry
                    val matchesRegion = selectedRegion == "Todas" || parish.stateOrRegion == selectedRegion
                    val matchesCity = selectedCity == "Todas" || parish.city == selectedCity
                    val matchesFilter = when (selectedParishFilter) {
                        1 -> !parish.confessionTimes.contains("No disponible", ignoreCase = true)
                        2 -> parish.isPerpetualAdoration ||
                                parish.adorationTimes.contains("24", ignoreCase = true) ||
                                parish.adorationTimes.contains("Perpetua", ignoreCase = true) ||
                                parish.adorationTimes.contains("Jueves", ignoreCase = true)
                        3 -> parish.name.contains("Catedral", ignoreCase = true) ||
                                parish.name.contains("Basílica", ignoreCase = true)
                        else -> true
                    }
                    matchesQuery && matchesCountry && matchesRegion && matchesCity && matchesFilter
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Buscador de Parroquias y Templos Católicos"
                                AppLanguage.EN -> "Catholic Parish & Church Finder"
                                AppLanguage.PT -> "Buscador de Paróquias e Templos Católicos"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
                        )
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Localiza iglesias cerca de ti mediante Google Maps o explora el directorio internacional por país y ciudad."
                                AppLanguage.EN -> "Locate churches near you using Google Maps or browse the international directory by country and city."
                                AppLanguage.PT -> "Encontre igrejas próximas com o Google Maps ou explore o diretório internacional por país e cidade."
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // One-tap Near Me GPS Maps Launch Button
                        Button(
                            onClick = {
                                try {
                                    val uri = Uri.parse("geo:0,0?q=parroquia+catolica")
                                    val intent = Intent(Intent.ACTION_VIEW, uri)
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    try {
                                        val webUri = Uri.parse("https://www.google.com/maps/search/parroquia+catolica")
                                        val webIntent = Intent(Intent.ACTION_VIEW, webUri)
                                        context.startActivity(webIntent)
                                    } catch (e2: Exception) {
                                        Toast.makeText(context, "No se pudo abrir Google Maps", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("gps_nearby_maps_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SacredBurgundy
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Directions, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Buscar Parroquias Cerca de Mí (Google Maps)"
                                    AppLanguage.EN -> "Find Parishes Near Me (Google Maps)"
                                    AppLanguage.PT -> "Buscar Paróquias Perto de Mim (Google Maps)"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Search Field
                        OutlinedTextField(
                            value = parishSearchQuery,
                            onValueChange = { parishSearchQuery = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("parish_search_input"),
                            placeholder = {
                                Text(
                                    when (currentLanguage) {
                                        AppLanguage.ES -> "Buscar por nombre, dirección o diócesis..."
                                        AppLanguage.EN -> "Search by name, address, or diocese..."
                                        AppLanguage.PT -> "Buscar por nome, endereço ou diocese..."
                                    },
                                    fontSize = 12.sp
                                )
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Search, contentDescription = null, tint = SacredBurgundy)
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Cascading Dropdown Filters: País -> Región -> Ciudad
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Filtro Geográfico por Menú Desplegable:"
                                AppLanguage.EN -> "Geographic Filter Dropdown Menus:"
                                AppLanguage.PT -> "Filtro Geográfico por Menus Desdobráveis:"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SacredBurgundy
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        // Row 1: País dropdown
                        ParishDropdownSelector(
                            label = when (currentLanguage) {
                                AppLanguage.ES -> "País seleccionado:"
                                AppLanguage.EN -> "Selected Country:"
                                AppLanguage.PT -> "País selecionado:"
                            },
                            selectedValue = selectedCountry,
                            options = allCountries,
                            onOptionSelected = {
                                selectedCountry = it
                                selectedRegion = "Todas"
                                selectedCity = "Todas"
                            },
                            modifier = Modifier.fillMaxWidth().testTag("parish_country_dropdown")
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Row 2: Región / Estado & Ciudad dropdowns side-by-side or stacked
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ParishDropdownSelector(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "Región / Estado:"
                                    AppLanguage.EN -> "Region / State:"
                                    AppLanguage.PT -> "Região / Estado:"
                                },
                                selectedValue = selectedRegion,
                                options = availableRegions,
                                onOptionSelected = {
                                    selectedRegion = it
                                    selectedCity = "Todas"
                                },
                                modifier = Modifier.weight(1f).testTag("parish_region_dropdown")
                            )

                            ParishDropdownSelector(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "Ciudad:"
                                    AppLanguage.EN -> "City:"
                                    AppLanguage.PT -> "Cidade:"
                                },
                                selectedValue = selectedCity,
                                options = availableCities,
                                onOptionSelected = {
                                    selectedCity = it
                                },
                                modifier = Modifier.weight(1f).testTag("parish_city_dropdown")
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Parish Filter Chips
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            DoctrineChip(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "Todas (${filteredParishes.size})"
                                    AppLanguage.EN -> "All (${filteredParishes.size})"
                                    AppLanguage.PT -> "Todas (${filteredParishes.size})"
                                },
                                isSelected = selectedParishFilter == 0,
                                onClick = { selectedParishFilter = 0 }
                            )
                            DoctrineChip(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "Confesiones"
                                    AppLanguage.EN -> "Confessions"
                                    AppLanguage.PT -> "Confissões"
                                },
                                isSelected = selectedParishFilter == 1,
                                onClick = { selectedParishFilter = 1 }
                            )
                            DoctrineChip(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "Adoración 24h"
                                    AppLanguage.EN -> "24h Adoration"
                                    AppLanguage.PT -> "Adoração 24h"
                                },
                                isSelected = selectedParishFilter == 2,
                                onClick = { selectedParishFilter = 2 }
                            )
                            DoctrineChip(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "Catedrales"
                                    AppLanguage.EN -> "Cathedrals"
                                    AppLanguage.PT -> "Catedrais"
                                },
                                isSelected = selectedParishFilter == 3,
                                onClick = { selectedParishFilter = 3 }
                            )
                        }
                    }

                    if (filteredParishes.isEmpty()) {
                        item {
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            ) {
                                Text(
                                    text = when (currentLanguage) {
                                        AppLanguage.ES -> "No se encontraron parroquias con el criterio buscado."
                                        AppLanguage.EN -> "No parishes found matching your search."
                                        AppLanguage.PT -> "Nenhuma paróquia encontrada com os critérios pesquisados."
                                    },
                                    modifier = Modifier.padding(16.dp),
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    } else {
                        items(filteredParishes) { parish ->
                            ParishCard(parish = parish, currentLanguage = currentLanguage)
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Noticias y Boletines del Vaticano"
                                AppLanguage.EN -> "Vatican News & Catholic Bulletins"
                                AppLanguage.PT -> "Notícias e Boletins do Vaticano"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
                        )
                    }

                    items(repository.catholicNews) { news ->
                        NewsCard(news = news, currentLanguage = currentLanguage)
                    }
                }
            }
        }
    }
}

@Composable
private fun DoctrineChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.surface)
            .border(
                1.dp,
                if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CommandmentCard(cmd: CommandmentExploration, currentLanguage: AppLanguage) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = cmd.title.get(currentLanguage), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = SacredBurgundy)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = cmd.biblicalWording.get(currentLanguage), fontSize = 11.sp, fontFamily = FontFamily.Serif, color = LiturgicalGold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = cmd.moralExplanation.get(currentLanguage), fontSize = 13.sp, lineHeight = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "❌ Pecados prohibidos: " + cmd.sinsForbidden.get(currentLanguage), fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = "✓ Virtudes mandadas: " + cmd.virtuesCommanded.get(currentLanguage), fontSize = 11.sp, color = SacredBurgundy, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun PreceptCard(prc: ChurchPrecept, currentLanguage: AppLanguage) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = prc.title.get(currentLanguage), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = SacredBurgundy)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = prc.explanation.get(currentLanguage), fontSize = 13.sp, lineHeight = 20.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Obligación: " + prc.applicationInLife.get(currentLanguage), fontSize = 12.sp, color = LiturgicalGold, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun CapitalSinCard(item: CapitalSinVsVirtue, currentLanguage: AppLanguage) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Vicio: " + item.capitalSin.get(currentLanguage), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFFB71C1C))
                Text(text = "Virtud: " + item.opposingVirtue.get(currentLanguage), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = SacredBurgundy)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = item.sinMeaning.get(currentLanguage), fontSize = 12.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Remedio: " + item.virtueRemedy.get(currentLanguage), fontSize = 12.sp, fontWeight = FontWeight.Medium, color = LiturgicalGold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "💡 Consejo práctico: " + item.practicalAdvice.get(currentLanguage), fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun MarianApparitionCard(item: MarianApparition, currentLanguage: AppLanguage) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = item.title.get(currentLanguage), fontWeight = FontWeight.Bold, fontSize = 15.sp, color = SacredBurgundy)
                Text(text = item.feastDay, fontSize = 11.sp, color = LiturgicalGold, fontWeight = FontWeight.Bold)
            }
            Text(text = "${item.location.get(currentLanguage)} • Año ${item.year}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.historyAndApparition.get(currentLanguage), fontSize = 13.sp, lineHeight = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Promesas: " + item.promises.get(currentLanguage), fontSize = 12.sp, fontFamily = FontFamily.Serif, color = SacredBurgundy)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Santuario Principal: " + item.primarySanctuary.get(currentLanguage), fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ChristManifestationCard(item: ChristManifestation, currentLanguage: AppLanguage) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.title.get(currentLanguage), fontWeight = FontWeight.Bold, fontSize = 15.sp, color = SacredBurgundy)
            Text(text = "Fiesta: " + item.feastDay.get(currentLanguage), fontSize = 11.sp, color = LiturgicalGold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.originHistory.get(currentLanguage), fontSize = 13.sp, lineHeight = 20.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Promesas: " + item.promises.get(currentLanguage), fontSize = 12.sp, color = SacredBurgundy, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun ChurchEpochCard(epoch: ChurchHistoryEpoch, currentLanguage: AppLanguage) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = epoch.title.get(currentLanguage), fontWeight = FontWeight.Bold, fontSize = 15.sp, color = SacredBurgundy)
            Text(text = epoch.centuryRange, fontSize = 11.sp, color = LiturgicalGold, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = epoch.narrative.get(currentLanguage), fontSize = 13.sp, lineHeight = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Papas claves: " + epoch.keyPopes.get(currentLanguage), fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = "Concilios: " + epoch.ecumenicalCouncils.get(currentLanguage), fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun SchismCard(schism: ChurchSchismHistory, currentLanguage: AppLanguage) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = schism.title.get(currentLanguage), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = SacredBurgundy)
                Text(text = schism.historicalYear, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = LiturgicalGold)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = schism.context.get(currentLanguage), fontSize = 12.sp, lineHeight = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Diferencias doctrinales claves:", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = SacredBurgundy)
            Text(text = schism.theologicalDifferences.get(currentLanguage), fontSize = 12.sp, lineHeight = 18.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Perspectiva y Diálogo: " + schism.dialogueAndReconciliation.get(currentLanguage), fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ParishCard(parish: NearbyParish, currentLanguage: AppLanguage) {
    val context = LocalContext.current

    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("parish_card_${parish.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Church,
                        contentDescription = null,
                        tint = SacredBurgundy,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = parish.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = SacredBurgundy
                    )
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = LiturgicalGold.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = parish.distanceDisplay,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = SacredBurgundy,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${parish.address} • ${parish.city}, ${parish.country} • ${parish.diocese}",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (parish.isPerpetualAdoration) {
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = LiturgicalGold.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = "✨ Adoración Perpetua 24 Horas",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = SacredBurgundy,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Schedule Info Box
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "⛪ Misas Dominicales: " + parish.sundayMasses,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "🙏 Confesiones: " + parish.confessionTimes,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SacredBurgundy
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "✨ Adoración: " + parish.adorationTimes,
                        fontSize = 11.sp,
                        color = LiturgicalGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons: Call & Maps
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        try {
                            val cleanPhone = parish.contactPhone.replace(" ", "")
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$cleanPhone"))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, parish.contactPhone, Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("call_parish_${parish.id}"),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = SacredBurgundy
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Llamar"
                            AppLanguage.EN -> "Call"
                            AppLanguage.PT -> "Ligar"
                        },
                        fontSize = 12.sp,
                        color = SacredBurgundy,
                        fontWeight = FontWeight.Bold
                    )
                }

                FilledTonalButton(
                    onClick = {
                        try {
                            val uri = Uri.parse("geo:0,0?q=${Uri.encode("${parish.name}, ${parish.address}")}")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, parish.address, Toast.LENGTH_LONG).show()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("map_parish_${parish.id}"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = SacredBurgundy.copy(alpha = 0.12f),
                        contentColor = SacredBurgundy
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Directions,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = SacredBurgundy
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Cómo llegar"
                            AppLanguage.EN -> "Directions"
                            AppLanguage.PT -> "Como chegar"
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun NewsCard(news: CatholicNewsArticle, currentLanguage: AppLanguage) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = news.category.get(currentLanguage), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = LiturgicalGold)
                Text(text = news.date, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = news.title.get(currentLanguage), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = SacredBurgundy)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = news.summary.get(currentLanguage), fontSize = 12.sp, lineHeight = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Fuente: " + news.source + " • ${news.readTimeMinutes} min lectura", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ParishDropdownSelector(
    label: String,
    selectedValue: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedCard(
            onClick = { expanded = true },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = label,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SacredBurgundy
                    )
                    Text(
                        text = selectedValue,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = SacredBurgundy,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.heightIn(max = 280.dp)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            fontWeight = if (option == selectedValue) FontWeight.Bold else FontWeight.Normal,
                            color = if (option == selectedValue) SacredBurgundy else MaterialTheme.colorScheme.onSurface,
                            fontSize = 13.sp
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

