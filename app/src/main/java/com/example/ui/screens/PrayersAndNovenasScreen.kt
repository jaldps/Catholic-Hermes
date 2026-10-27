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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.data.models.Novena
import com.example.data.models.PrayerCategory
import com.example.data.models.VaticanPrayer
import com.example.data.repository.CatholicDataRepository
import com.example.ui.theme.LiturgicalGold
import com.example.ui.theme.SacredBurgundy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrayersAndNovenasScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0 = Devocionario Vaticano, 1 = Novenas Milagrosas

    // Devocionario State
    var prayerSearchQuery by remember { mutableStateOf("") }
    var selectedThematicTag by remember { mutableStateOf<String?>(null) }

    // Novenas State
    var selectedNovena by remember { mutableStateOf<Novena?>(null) }
    var activeNovenaDay by remember { mutableIntStateOf(1) } // 1..9
    var novenaCompletedDays by remember { mutableStateOf(setOf(1)) }
    var personalIntention by remember { mutableStateOf("") }

    val isPlayingAudio by ttsManager.isPlaying.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("prayers_and_novenas_screen")
    ) {
        PrimaryTabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = SacredBurgundy
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Devocionario"
                            AppLanguage.EN -> "Prayers"
                            AppLanguage.PT -> "Devocionário"
                        },
                        fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Coronilla Misericordia"
                            AppLanguage.EN -> "Divine Mercy Chaplet"
                            AppLanguage.PT -> "Terço Misericórdia"
                        },
                        fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Novenas"
                            AppLanguage.EN -> "Novenas"
                            AppLanguage.PT -> "Novenas"
                        },
                        fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = selectedTab == 3,
                onClick = { selectedTab = 3 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Completas"
                            AppLanguage.EN -> "Night Prayer"
                            AppLanguage.PT -> "Completas"
                        },
                        fontWeight = if (selectedTab == 3) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
        }

        when (selectedTab) {
            0 -> {
                // Vatican Devocionario View
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    // Search box
                    OutlinedTextField(
                        value = prayerSearchQuery,
                        onValueChange = { prayerSearchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("prayer_search_input"),
                        placeholder = {
                            Text(
                                when (currentLanguage) {
                                    AppLanguage.ES -> "Buscar oración o necesidad..."
                                    AppLanguage.EN -> "Search prayer or petition..."
                                    AppLanguage.PT -> "Buscar oração ou intenção..."
                                }
                            )
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        },
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Thematic Filter Chips
                    val tags = listOf(
                        null to when (currentLanguage) { AppLanguage.ES -> "Todas"; AppLanguage.EN -> "All"; AppLanguage.PT -> "Todas" },
                        "health" to when (currentLanguage) { AppLanguage.ES -> "Salud y Sanación"; AppLanguage.EN -> "Health & Healing"; AppLanguage.PT -> "Saúde e Cura" },
                        "peace" to when (currentLanguage) { AppLanguage.ES -> "Paz y Serenidad"; AppLanguage.EN -> "Peace of Mind"; AppLanguage.PT -> "Paz de Espírito" },
                        "family" to when (currentLanguage) { AppLanguage.ES -> "Familia"; AppLanguage.EN -> "Family"; AppLanguage.PT -> "Família" },
                        "protection" to when (currentLanguage) { AppLanguage.ES -> "Protección"; AppLanguage.EN -> "Protection"; AppLanguage.PT -> "Proteção" },
                        "forgiveness" to when (currentLanguage) { AppLanguage.ES -> "Perdón y Confesión"; AppLanguage.EN -> "Forgiveness"; AppLanguage.PT -> "Perdão" }
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(tags) { (tagKey, tagLabel) ->
                            val isSelected = selectedThematicTag == tagKey
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.surface)
                                    .border(
                                        1.dp,
                                        if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                        RoundedCornerShape(12.dp)
                                    )
                                    .clickable { selectedThematicTag = tagKey }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = tagLabel,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    val filteredPrayers = repository.searchPrayers(
                        query = prayerSearchQuery,
                        tag = selectedThematicTag
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(filteredPrayers) { prayer ->
                            VaticanPrayerCard(
                                prayer = prayer,
                                currentLanguage = currentLanguage,
                                onPlayAudio = {
                                    ttsManager.speakDialogue(
                                        prayer.leaderText.get(currentLanguage),
                                        prayer.responseText?.get(currentLanguage) ?: ""
                                    )
                                }
                            )
                        }
                    }
                }
            }

            1 -> {
                DivineMercyChapletView(
                    ttsManager = ttsManager,
                    currentLanguage = currentLanguage,
                    onOpenNovena = {
                        selectedTab = 2
                        selectedNovena = repository.officialNovenas.firstOrNull { it.id == "divine_mercy" } ?: repository.officialNovenas.firstOrNull()
                        activeNovenaDay = 1
                    }
                )
            }

            2 -> {
                // Miraculous Novenas View
                if (selectedNovena == null) {
                    // List of Novenas
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Novenas Oficiales con Control de 9 Días"
                                    AppLanguage.EN -> "Official Novenas with 9-Day Tracker"
                                    AppLanguage.PT -> "Novenas Oficiais com Controle de 9 Dias"
                                },
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = SacredBurgundy
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Lleva el seguimiento diario de tus oraciones, registra tus intenciones y recibe consuelo en la promesa de Dios."
                                    AppLanguage.EN -> "Track your 9-day prayer progress, record your deepest intentions, and receive divine consolation."
                                    AppLanguage.PT -> "Acompanhe diariamente suas novenas com registro de intenções e perseverança na oração."
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        items(repository.officialNovenas) { novena ->
                            NovenaOverviewCard(
                                novena = novena,
                                currentLanguage = currentLanguage,
                                onClick = {
                                    selectedNovena = novena
                                    activeNovenaDay = 1
                                }
                            )
                        }
                    }
                } else {
                    // Open Novena Day Tracker View
                    val novena = selectedNovena!!
                    val dayData = novena.days.firstOrNull { it.dayNumber == activeNovenaDay } ?: novena.days.first()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    onClick = { selectedNovena = null },
                                    modifier = Modifier.testTag("back_to_novenas_button")
                                ) {
                                    Text("← " + when (currentLanguage) {
                                        AppLanguage.ES -> "Lista de Novenas"
                                        AppLanguage.EN -> "All Novenas"
                                        AppLanguage.PT -> "Todas as Novenas"
                                    }, fontWeight = FontWeight.Bold)
                                }

                                Text(
                                    text = novena.category.get(currentLanguage),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = LiturgicalGold
                                )
                            }
                        }

                        item {
                            Text(
                                text = novena.title.get(currentLanguage),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = SacredBurgundy
                            )
                            Text(
                                text = novena.patron.get(currentLanguage),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Day Selector Circles (1 to 9)
                        item {
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                tonalElevation = 2.dp
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "PROGRESO: DÍA $activeNovenaDay DE 9",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = LiturgicalGold
                                        )
                                        Text(
                                            text = "${novenaCompletedDays.size} rezados",
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        (1..9).forEach { dayNum ->
                                            val isSelected = dayNum == activeNovenaDay
                                            val isCompleted = novenaCompletedDays.contains(dayNum)

                                            Box(
                                                modifier = Modifier
                                                    .size(32.dp)
                                                    .clip(CircleShape)
                                                    .background(
                                                        when {
                                                            isSelected -> SacredBurgundy
                                                            isCompleted -> LiturgicalGold
                                                            else -> MaterialTheme.colorScheme.surface
                                                        }
                                                    )
                                                    .border(
                                                        1.dp,
                                                        if (isSelected) LiturgicalGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                                        CircleShape
                                                    )
                                                    .clickable { activeNovenaDay = dayNum },
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "$dayNum",
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 12.sp,
                                                    color = when {
                                                        isSelected -> Color.White
                                                        isCompleted -> Color.Black
                                                        else -> MaterialTheme.colorScheme.onSurface
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Daily Scripture & Meditation
                        item {
                            ElevatedCard(
                                shape = RoundedCornerShape(18.dp),
                                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(modifier = Modifier.padding(18.dp)) {
                                    Text(
                                        text = dayData.theme.get(currentLanguage),
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = SacredBurgundy
                                    )
                                    Text(
                                        text = dayData.scripture,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = LiturgicalGold
                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(
                                        text = dayData.meditation.get(currentLanguage),
                                        style = MaterialTheme.typography.bodyMedium,
                                        lineHeight = 22.sp
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Intention guidance
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(Icons.Default.Info, contentDescription = null, tint = SacredBurgundy, modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = dayData.intentionGuidance.get(currentLanguage),
                                                fontSize = 11.sp,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Prayer of the Day & Action to Mark Completed
                        item {
                            ElevatedCard(
                                shape = RoundedCornerShape(18.dp),
                                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Column(modifier = Modifier.padding(18.dp)) {
                                    Text(
                                        text = when (currentLanguage) {
                                            AppLanguage.ES -> "Oración del Día $activeNovenaDay"
                                            AppLanguage.EN -> "Prayer for Day $activeNovenaDay"
                                            AppLanguage.PT -> "Oração do Dia $activeNovenaDay"
                                        },
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = SacredBurgundy
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = dayData.prayer.get(currentLanguage),
                                        fontFamily = FontFamily.Serif,
                                        style = MaterialTheme.typography.bodyLarge,
                                        lineHeight = 24.sp
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        ElevatedButton(
                                            onClick = {
                                                ttsManager.speakSingle(dayData.prayer.get(currentLanguage))
                                            },
                                            modifier = Modifier.weight(1f),
                                            colors = ButtonDefaults.elevatedButtonColors(
                                                containerColor = SacredBurgundy,
                                                contentColor = Color.White
                                            )
                                        ) {
                                            Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("Escuchar", fontSize = 12.sp)
                                        }

                                        FilledTonalButton(
                                            onClick = {
                                                novenaCompletedDays = novenaCompletedDays + activeNovenaDay
                                                if (activeNovenaDay < 9) activeNovenaDay++
                                            },
                                            modifier = Modifier.weight(1f),
                                            colors = ButtonDefaults.filledTonalButtonColors(
                                                containerColor = LiturgicalGold,
                                                contentColor = Color.Black
                                            )
                                        ) {
                                            Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("Completar Día", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            3 -> {
                NightPrayersScreen(
                    repository = repository,
                    ttsManager = ttsManager,
                    currentLanguage = currentLanguage
                )
            }
        }
    }
}

@Composable
private fun VaticanPrayerCard(
    prayer: VaticanPrayer,
    currentLanguage: AppLanguage,
    onPlayAudio: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = prayer.title.get(currentLanguage),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = SacredBurgundy
                    )
                    if (prayer.latinTitle != null) {
                        Text(
                            text = prayer.latinTitle,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Serif,
                            color = LiturgicalGold
                        )
                    }
                }

                IconButton(
                    onClick = onPlayAudio,
                    modifier = Modifier.testTag("play_prayer_audio_${prayer.id}")
                ) {
                    Icon(imageVector = Icons.Default.VolumeUp, contentDescription = "Listen to Prayer", tint = SacredBurgundy)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "V/. " + prayer.leaderText.get(currentLanguage),
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 22.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (prayer.responseText != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "R/. " + prayer.responseText.get(currentLanguage),
                    fontFamily = FontFamily.Serif,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = LiturgicalGold,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "⏱ " + prayer.whenToPray.get(currentLanguage),
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (prayer.indulgenceNote != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "✦ " + prayer.indulgenceNote.get(currentLanguage),
                    fontSize = 10.sp,
                    color = SacredBurgundy,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun NovenaOverviewCard(
    novena: Novena,
    currentLanguage: AppLanguage,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = novena.title.get(currentLanguage),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SacredBurgundy
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(LiturgicalGold.copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "9 DÍAS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = SacredBurgundy
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = novena.patron.get(currentLanguage),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = LiturgicalGold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = novena.description.get(currentLanguage),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Promesas y Milagros Registrados",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Comenzar / Continuar →",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun DivineMercyChapletView(
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    onOpenNovena: () -> Unit
) {
    var chapletStep by remember { mutableIntStateOf(1) } // 0 = Oraciones Iniciales, 1 = Decenas (1..5), 2 = Conclusión, 3 = Hora 3:00 PM
    var currentDecade by remember { mutableIntStateOf(1) } // 1..5
    var currentBead by remember { mutableIntStateOf(0) } // 0 = Cuenta Grande (Padre Eterno), 1..10 = Cuentas Pequeñas (Dolorosa Pasión)

    val openingPrayer = when (currentLanguage) {
        AppLanguage.ES -> "En el nombre del Padre, y del Hijo, y del Espíritu Santo. Amén.\n\n«Expiraste, Jesús, pero la fuente de vida brotó para las almas y el mar de misericordia se abrió para el mundo entero. ¡Oh fuente de vida, insondable Misericordia Divina!, abarca al mundo entero y derrámate sobre nosotros.\n\n¡Oh Sangre y Agua que brotaste del Corazón de Jesús como una fuente de misericordia para nosotros, en Ti confío! (repetir 3 veces)»\n\n[Padre Nuestro, Ave María, Credo de los Apóstoles]"
        AppLanguage.EN -> "In the name of the Father, and of the Son, and of the Holy Spirit. Amen.\n\n«You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.\n\nO Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You! (repeat 3 times)»\n\n[Our Father, Hail Mary, Apostles' Creed]"
        AppLanguage.PT -> "Em nome do Pai, e do Filho, e do Espírito Santo. Amém.\n\n«Expirastes, Jesus, mas a fonte da vida brotou para as almas e o mar de misericórdia abriu-se para o mundo inteiro. Ó fonte de vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-vos sobre nós.\n\nÓ Sangue e Água que brotastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós! (repetir 3 vezes)»\n\n[Pai Nosso, Ave Maria, Credo dos Apóstolos]"
    }

    val largeBeadPrayer = when (currentLanguage) {
        AppLanguage.ES -> "Padre Eterno, te ofrezco el Cuerpo y la Sangre, el Alma y la Divinidad de tu amadísimo Hijo, nuestro Señor Jesucristo, como propiciación de nuestros pecados y los del mundo entero."
        AppLanguage.EN -> "Eternal Father, I offer you the Body and Blood, Soul and Divinity of Your Dearly Beloved Son, Our Lord, Jesus Christ, in atonement for our sins and those of the whole world."
        AppLanguage.PT -> "Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade de Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro."
    }

    val smallBeadPrayer = when (currentLanguage) {
        AppLanguage.ES -> "Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero."
        AppLanguage.EN -> "For the sake of His sorrowful Passion, have mercy on us and on the whole world."
        AppLanguage.PT -> "Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro."
    }

    val closingPrayer = when (currentLanguage) {
        AppLanguage.ES -> "«Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.» (Repetir 3 veces)\n\nOh Dios Eterno, en quien la misericordia es infinita y el tesoro de compasión inagotable, vuelve a nosotros Tu mirada bondadosa y aumenta Tu misericordia en nosotros, para que en los momentos difíciles no desesperemos ni nos desalentemos, sino que con gran confianza nos sometamos a Tu santa voluntad, que es el Amor y la Misericordia mismos. Amén.\n\n¡Jesús, en Ti confío! (x3)"
        AppLanguage.EN -> "«Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.» (Repeat 3 times)\n\nEternal God, in whom mercy is endless and the treasury of compassion inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself. Amen.\n\nJesus, I trust in You! (x3)"
        AppLanguage.PT -> "«Santo Deus, Santo Forte, Santo Imortal, tende piedade de nós e do mundo inteiro.» (Repetir 3 vezes)\n\nÓ Deus Eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai-nos com bondade e aumentai a Vossa misericórdia em nós, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a Misericórdia. Amém.\n\nJesus, eu confio em Vós! (x3)"
    }

    val hourOfMercyText = when (currentLanguage) {
        AppLanguage.ES -> "«A las tres de la tarde implora Mi misericordia, especialmente para los pecadores; y aunque sea por un brevísimo momento, sumérgete en Mi Pasión, particularmente en Mi abandono en el momento de la agonía. Esta es la hora de la gran misericordia para todo el mundo. En esta hora no negaré nada al alma que Me pida por los méritos de Mi Pasión» (Diario de Santa Faustina Kowalska, 1320)."
        AppLanguage.EN -> "«At three o'clock, implore My mercy, especially for sinners; and, if only for a brief moment, immerse yourself in My Passion, particularly in My abandonment at the moment of agony. This is the hour of great mercy for the whole world. In this hour I will refuse nothing to the soul that makes a request of Me in virtue of My Passion» (Diary, 1320)."
        AppLanguage.PT -> "«Às três horas da tarde implora a Minha misericórdia, especialmente para os pecadores e, ao menos por um breve momento, aprofunda-te na Minha Paixão, particularmente no Meu desamparo no momento da agonia. Esta é a hora da grande misericórdia para o mundo inteiro. Nesta hora nada negarei à alma que Me pedir pelos méritos da Minha Paixão» (Diário, 1320)."
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ElevatedCard(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Coronilla de la Divina Misericordia"
                                AppLanguage.EN -> "Chaplet of Divine Mercy"
                                AppLanguage.PT -> "Terço da Divina Misericórdia"
                            },
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
                        )

                        IconButton(
                            onClick = {
                                val currentText = when (chapletStep) {
                                    0 -> openingPrayer
                                    1 -> if (currentBead == 0) largeBeadPrayer else smallBeadPrayer
                                    2 -> closingPrayer
                                    else -> hourOfMercyText
                                }
                                ttsManager.speakSingle(currentText)
                            },
                            modifier = Modifier.testTag("chaplet_tts_button")
                        ) {
                            Icon(Icons.Default.VolumeUp, contentDescription = "Escuchar con Voz", tint = SacredBurgundy)
                        }
                    }

                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Revelada a Santa Faustina Kowalska en Vilna (1935)"
                            AppLanguage.EN -> "Revealed to St. Maria Faustina Kowalska in Vilnius (1935)"
                            AppLanguage.PT -> "Revelada a Santa Faustina Kowalska em Vilna (1935)"
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = LiturgicalGold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Promesa de Nuestro Señor: «Quienquiera que la rece recibirá gran misericordia a la hora de la muerte... Defenderé como Mi propia gloria a cada alma que rece esta coronilla» (Diario, 687)."
                            AppLanguage.EN -> "Our Lord's Promise: «Whoever will recite it will receive great mercy at the hour of death... I will defend as My own glory every soul that will say this chaplet» (Diary, 687)."
                            AppLanguage.PT -> "Promessa de Nosso Senhor: «Quem quer que a recite receberá grande misericórdia na hora da morte... Defenderei como Minha própria glória cada alma que recitar este terço» (Diário, 687)."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Chaplet Step Selector
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val stepNames = when (currentLanguage) {
                    AppLanguage.ES -> listOf("Inicio", "5 Decenas", "Cierre", "Hora 3:00 PM")
                    AppLanguage.EN -> listOf("Opening", "5 Decades", "Closing", "3:00 PM Hour")
                    AppLanguage.PT -> listOf("Início", "5 Dezenas", "Fechamento", "Hora 15:00")
                }
                stepNames.forEachIndexed { idx, name ->
                    val isSelected = chapletStep == idx
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.surfaceVariant)
                            .clickable { chapletStep = idx }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = name,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        when (chapletStep) {
            0 -> {
                // Opening Prayers
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Oraciones Iniciales",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = SacredBurgundy
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = openingPrayer,
                                fontFamily = FontFamily.Serif,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 24.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { chapletStep = 1; currentDecade = 1; currentBead = 0 },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = SacredBurgundy)
                            ) {
                                Text("Comenzar 1ª Decena →", color = Color.White)
                            }
                        }
                    }
                }
            }

            1 -> {
                // 5 Decades Interactive Stepper
                item {
                    // Decade selector tabs (1..5)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (d in 1..5) {
                            val isCurrentD = currentDecade == d
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isCurrentD) LiturgicalGold else MaterialTheme.colorScheme.surface)
                                    .border(1.dp, if (isCurrentD) LiturgicalGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                                    .clickable {
                                        currentDecade = d
                                        currentBead = 0
                                    }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${d}ª Dec.",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = if (isCurrentD) Color.Black else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                // Bead Dots Progress (0 = Large, 1..10 = Small)
                item {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = if (currentBead == 0) "Cuenta Principal: Padre Eterno" else "Cuenta $currentBead de 10: Dolorosa Pasión",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = SacredBurgundy
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Large bead
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(if (currentBead == 0) SacredBurgundy else LiturgicalGold.copy(alpha = 0.35f))
                                        .clickable { currentBead = 0 },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("G", fontSize = 10.sp, color = if (currentBead == 0) Color.White else Color.Black, fontWeight = FontWeight.Bold)
                                }

                                for (b in 1..10) {
                                    val isSmallCurrent = currentBead == b
                                    Box(
                                        modifier = Modifier
                                            .size(18.dp)
                                            .clip(CircleShape)
                                            .background(if (isSmallCurrent) SacredBurgundy else if (currentBead > b) LiturgicalGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                                            .clickable { currentBead = b },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("$b", fontSize = 8.sp, color = if (isSmallCurrent) Color.White else MaterialTheme.colorScheme.onSurface)
                                    }
                                }
                            }
                        }
                    }
                }

                // Active Bead Prayer Card
                item {
                    ElevatedCard(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = if (currentBead == 0) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f) else MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (currentBead == 0) "Cuenta Grande (Decena $currentDecade)" else "Cuenta Pequeña ($currentBead/10)",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = SacredBurgundy
                                )

                                IconButton(
                                    onClick = {
                                        val text = if (currentBead == 0) largeBeadPrayer else smallBeadPrayer
                                        ttsManager.speakSingle(text)
                                    }
                                ) {
                                    Icon(Icons.Default.VolumeUp, contentDescription = "Escuchar", tint = SacredBurgundy)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = if (currentBead == 0) largeBeadPrayer else smallBeadPrayer,
                                fontFamily = FontFamily.Serif,
                                style = MaterialTheme.typography.bodyLarge,
                                lineHeight = 26.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                if (currentBead > 0 || currentDecade > 1) {
                                    FilledTonalButton(
                                        onClick = {
                                            if (currentBead > 0) {
                                                currentBead--
                                            } else if (currentDecade > 1) {
                                                currentDecade--
                                                currentBead = 10
                                            }
                                        },
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text("← Anterior")
                                    }
                                }

                                Button(
                                    onClick = {
                                        if (currentBead < 10) {
                                            currentBead++
                                        } else if (currentDecade < 5) {
                                            currentDecade++
                                            currentBead = 0
                                        } else {
                                            chapletStep = 2 // Done 5 decades, go to closing
                                        }
                                    },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(containerColor = SacredBurgundy)
                                ) {
                                    Text(
                                        text = if (currentBead == 10 && currentDecade == 5) "Ir a Conclusión →" else "Siguiente Cuenta →",
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            2 -> {
                // Closing Prayers
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Doxología Trinitaria y Conclusión",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = SacredBurgundy
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = closingPrayer,
                                fontFamily = FontFamily.Serif,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 24.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = onOpenNovena,
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = LiturgicalGold)
                            ) {
                                Text("Rezar la Novena a la Divina Misericordia (9 Días) →", color = Color.Black, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            3 -> {
                // Hour of Mercy
                item {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                        border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "La Hora de la Misericordia (3:00 PM)",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = SacredBurgundy
                                )

                                IconButton(onClick = { ttsManager.speakSingle(hourOfMercyText) }) {
                                    Icon(Icons.Default.VolumeUp, contentDescription = "Escuchar", tint = SacredBurgundy)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = hourOfMercyText,
                                fontFamily = FontFamily.Serif,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 24.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}
