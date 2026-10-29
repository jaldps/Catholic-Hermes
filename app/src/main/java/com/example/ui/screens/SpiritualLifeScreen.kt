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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.spacing
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.JournalEntryEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.models.GregorianChantItem
import com.example.data.models.TriviaQuestion
import com.example.data.models.ViaCrucisStation
import com.example.data.repository.CatholicDataRepository
import kotlinx.coroutines.launch

@Composable
fun SpiritualLifeScreen(
    repository: CatholicDataRepository,
    dao: SanctaFidesDao,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    initialTab: Int = 0,
    modifier: Modifier = Modifier
) {
    var selectedTopTab by remember(initialTab) { mutableIntStateOf(initialTab) }

    val coroutineScope = rememberCoroutineScope()
    val journalEntries by dao.getAllJournalEntries().collectAsState(initial = emptyList())

    // Journal Dialog State
    var showAddJournalDialog by remember { mutableStateOf(false) }
    var newJournalTitle by remember { mutableStateOf("") }
    var newJournalCategory by remember { mutableStateOf("Intención") }
    var newJournalContent by remember { mutableStateOf("") }

    // Trivia State
    val triviaList = repository.triviaQuestions
    var triviaIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var triviaScore by remember { mutableIntStateOf(0) }
    var hasAnswered by remember { mutableStateOf(false) }

    // Via Crucis State
    var currentStationIndex by remember { mutableIntStateOf(0) }

    // Chant State
    var selectedChant by remember { mutableStateOf<GregorianChantItem?>(repository.gregorianChants.firstOrNull()) }
    val isPlayingAudio by ttsManager.isPlaying.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("spiritual_life_screen")
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTopTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            edgePadding = MaterialTheme.spacing.md
        ) {
            Tab(
                selected = selectedTopTab == 0,
                onClick = { selectedTopTab = 0 },
                text = { Text("Diario Espiritual", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTopTab == 1,
                onClick = { selectedTopTab = 1 },
                text = { Text("Trivia Católica", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTopTab == 2,
                onClick = { selectedTopTab = 2 },
                text = { Text("Santo Vía Crucis", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTopTab == 3,
                onClick = { selectedTopTab = 3 },
                text = { Text("Canto Gregoriano", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold) }
            )
        }

        when (selectedTopTab) {
            0 -> {
                // Catholic Spiritual Diary & Examen
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(MaterialTheme.spacing.lg),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)
                    ) {
                        item {
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Diario Católico y Peticiones"
                                    AppLanguage.EN -> "Catholic Spiritual Journal"
                                    AppLanguage.PT -> "Diário Espiritual Católico"
                                },
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Anota tus intenciones al Señor, tus agradecimientos, las gracias concedidas y el examen de conciencia."
                                    AppLanguage.EN -> "Record your prayers, graces received, answered petitions, and daily examination of conscience."
                                    AppLanguage.PT -> "Registre suas intenções de oração, graças alcançadas e exame diário de consciência."
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        if (journalEntries.isEmpty()) {
                            item {
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = MaterialTheme.shapes.large,
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(MaterialTheme.spacing.xl),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(36.dp))
                                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
                                        Text(
                                            text = when (currentLanguage) {
                                                AppLanguage.ES -> "Tu diario espiritual está vacío"
                                                AppLanguage.EN -> "Your spiritual journal is empty"
                                                AppLanguage.PT -> "Seu diário espiritual está vazio"
                                            },
                                            style = MaterialTheme.typography.titleSmall,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))
                                        Text(
                                            text = when (currentLanguage) {
                                                AppLanguage.ES -> "Toca el botón '+' abajo para registrar tu primera intención o agradecimiento."
                                                AppLanguage.EN -> "Tap '+' below to write your first prayer intention or thanksgiving."
                                                AppLanguage.PT -> "Toque no botão '+' abaixo para anotar sua intenção de oração."
                                            },
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        } else {
                            items(journalEntries) { entry ->
                                ElevatedCard(
                                    shape = MaterialTheme.shapes.large,
                                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
                                ) {
                                    Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Box(
                                                    modifier = Modifier
                                                        .clip(MaterialTheme.shapes.extraSmall)
                                                        .background(if (entry.isAnswered) MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                                                        .padding(horizontal = MaterialTheme.spacing.sm, vertical = MaterialTheme.spacing.xs)
                                                ) {
                                                    Text(
                                                        text = if (entry.isAnswered) "✓ GRACIA CONCEDIDA" else entry.category.uppercase(),
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = if (entry.isAnswered) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                                                    )
                                                }
                                            }

                                            IconButton(onClick = {
                                                coroutineScope.launch { dao.deleteJournalEntry(entry) }
                                            }) {
                                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(18.dp))
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

                                        Text(
                                            text = entry.title,
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )

                                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

                                        Text(
                                            text = entry.content,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )

                                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            TextButton(
                                                onClick = {
                                                    coroutineScope.launch {
                                                        dao.updateJournalEntry(entry.copy(isAnswered = !entry.isAnswered))
                                                    }
                                                }
                                            ) {
                                                Text(
                                                    text = if (entry.isAnswered) "Desmarcar concedida" else "Marcar como Gracia Concedida ✓",
                                                    style = MaterialTheme.typography.labelMedium,
                                                    color = MaterialTheme.colorScheme.secondary
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Floating Action Button to Add New Entry
                    FloatingActionButton(
                        onClick = { showAddJournalDialog = true },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(MaterialTheme.spacing.lg)
                            .testTag("add_journal_entry_fab"),
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Journal Entry")
                    }
                }
            }

            1 -> {
                // Catholic Trivia & Learning Game
                val currentQ = triviaList[triviaIndex]

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PREGUNTA ${triviaIndex + 1} DE ${triviaList.size}",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "Puntos: $triviaScore",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    ElevatedCard(
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {
                            Text(
                                text = currentQ.category.get(currentLanguage),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
                            Text(
                                text = currentQ.question.get(currentLanguage),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // 4 Options
                    currentQ.options.forEachIndexed { idx, opt ->
                        val isSelected = selectedOptionIndex == idx
                        val isCorrect = idx == currentQ.correctIndex

                        val borderCol = when {
                            hasAnswered && isCorrect -> MaterialTheme.colorScheme.tertiary
                            hasAnswered && isSelected && !isCorrect -> MaterialTheme.colorScheme.error
                            isSelected -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                        }

                        val bgCol = when {
                            hasAnswered && isCorrect -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.15f)
                            hasAnswered && isSelected && !isCorrect -> MaterialTheme.colorScheme.error.copy(alpha = 0.15f)
                            isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                            else -> MaterialTheme.colorScheme.surface
                        }

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium)
                                .border(1.5.dp, borderCol, MaterialTheme.shapes.medium)
                                .background(bgCol)
                                .clickable(enabled = !hasAnswered) {
                                    selectedOptionIndex = idx
                                    hasAnswered = true
                                    if (idx == currentQ.correctIndex) {
                                        triviaScore += 10
                                    }
                                }
                                .padding(MaterialTheme.spacing.md)
                        ) {
                            Text(
                                text = opt.get(currentLanguage),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // Doctrinal Explanation when answered
                    if (hasAnswered) {
                        ElevatedCard(
                            shape = MaterialTheme.shapes.medium,
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f))
                        ) {
                            Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {
                                Text(
                                    text = if (selectedOptionIndex == currentQ.correctIndex) "¡Correcto! ✦" else "Doctrina Católica:",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))
                                Text(
                                    text = currentQ.doctrinalExplanation.get(currentLanguage),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        FilledTonalButton(
                            onClick = {
                                selectedOptionIndex = null
                                hasAnswered = false
                                triviaIndex = (triviaIndex + 1) % triviaList.size
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary)
                        ) {
                            Text("Siguiente Pregunta →", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }

            2 -> {
                // Via Crucis (Stations of the Cross)
                val stations = repository.viaCrucisStations
                val currentStation = stations[currentStationIndex]

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "ESTACIÓN ${currentStation.stationNumber} DE 14",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                                stations.indices.forEach { idx ->
                                    Box(
                                        modifier = Modifier
                                            .size(if (idx == currentStationIndex) 10.dp else 6.dp)
                                            .clip(CircleShape)
                                            .background(if (idx == currentStationIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
                                    )
                                }
                            }
                        }
                    }

                    item {
                        ElevatedCard(
                            shape = MaterialTheme.shapes.large,
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {
                                Text(
                                    text = currentStation.title.get(currentLanguage),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )

                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

                                Text(
                                    text = currentStation.scripture.get(currentLanguage),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )

                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

                                Text(
                                    text = currentStation.meditation.get(currentLanguage),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.lg))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(MaterialTheme.shapes.medium)
                                        .background(MaterialTheme.colorScheme.surface)
                                        .padding(MaterialTheme.spacing.md)
                                ) {
                                    Text(
                                        text = currentStation.prayer.get(currentLanguage),
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ElevatedButton(
                                onClick = {
                                    if (currentStationIndex > 0) currentStationIndex--
                                },
                                enabled = currentStationIndex > 0
                            ) {
                                Text("← Anterior")
                            }

                            ElevatedButton(
                                onClick = {
                                    ttsManager.speakDialogue(
                                        currentStation.meditation.get(currentLanguage),
                                        currentStation.prayer.get(currentLanguage)
                                    )
                                },
                                colors = ButtonDefaults.elevatedButtonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary)
                            ) {
                                Icon(if (isPlayingAudio) Icons.Default.Stop else Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))
                                Text("Rezar Estación")
                            }

                            ElevatedButton(
                                onClick = {
                                    if (currentStationIndex < stations.size - 1) currentStationIndex++
                                },
                                enabled = currentStationIndex < stations.size - 1,
                                colors = ButtonDefaults.elevatedButtonColors(containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.onSecondary)
                            ) {
                                Text("Siguiente →")
                            }
                        }
                    }
                }
            }

            3 -> {
                // Gregorian Chant & Sacred Music Player
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg)
                ) {
                    item {
                        Text(
                            text = "Reproductor de Canto Gregoriano y Música Sacra",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Eleva tu alma a Dios con la rica tradición musical milenaria de la Iglesia Católica.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    items(repository.gregorianChants) { chant ->
                        val isSelected = selectedChant?.id == chant.id

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.large)
                                .clickable { selectedChant = chant },
                            shape = MaterialTheme.shapes.large,
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f) else MaterialTheme.colorScheme.surfaceVariant
                            ),
                            border = CardDefaults.outlinedCardBorder().copy(width = if (isSelected) 1.5.dp else 0.5.dp)
                        ) {
                            Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.MusicNote, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))
                                        Text(text = chant.title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                                    }

                                    IconButton(
                                        onClick = {
                                            selectedChant = chant
                                            if (isPlayingAudio) {
                                                ttsManager.stop()
                                            } else {
                                                ttsManager.playSacredChime()
                                                ttsManager.speakSingle(chant.latinLyrics)
                                            }
                                        }
                                    ) {
                                        Icon(if (isPlayingAudio && isSelected) Icons.Default.Stop else Icons.Default.PlayArrow, contentDescription = "Play Chant", tint = MaterialTheme.colorScheme.secondary)
                                    }
                                }

                                Text(text = chant.liturgicalSeason.get(currentLanguage), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary)
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))
                                Text(text = chant.description.get(currentLanguage), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)

                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

                                Text(text = "Letra en Latín:", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                Text(text = chant.latinLyrics, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)

                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

                                Text(text = "Traducción:", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
                                Text(text = chant.translatedLyrics.get(currentLanguage), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    }
                }
            }
        }

        // Add Journal Entry Modal Dialog
        if (showAddJournalDialog) {
            AlertDialog(
                onDismissRequest = { showAddJournalDialog = false },
                title = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Nueva Intención / Reflexión"
                            AppLanguage.EN -> "New Prayer / Intention"
                            AppLanguage.PT -> "Nova Intenção / Oração"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                        OutlinedTextField(
                            value = newJournalTitle,
                            onValueChange = { newJournalTitle = it },
                            label = { Text("Título de la intención o gracia") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                            listOf("Intención", "Agradecimiento", "Examen", "Gracia").forEach { cat ->
                                Box(
                                    modifier = Modifier
                                        .clip(MaterialTheme.shapes.small)
                                        .background(if (newJournalCategory == cat) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                                        .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
                                        .clickable { newJournalCategory = cat }
                                        .padding(horizontal = MaterialTheme.spacing.sm, vertical = MaterialTheme.spacing.xs)
                                ) {
                                    Text(
                                        text = cat,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = if (newJournalCategory == cat) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        OutlinedTextField(
                            value = newJournalContent,
                            onValueChange = { newJournalContent = it },
                            label = { Text("Escribe tus palabras al Señor...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            maxLines = 5
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (newJournalTitle.isNotBlank()) {
                                coroutineScope.launch {
                                    dao.insertJournalEntry(
                                        JournalEntryEntity(
                                            title = newJournalTitle,
                                            category = newJournalCategory,
                                            content = newJournalContent
                                        )
                                    )
                                    newJournalTitle = ""
                                    newJournalContent = ""
                                    showAddJournalDialog = false
                                }
                            }
                        }
                    ) {
                        Text("Guardar en el Diario", style = MaterialTheme.typography.labelLarge)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddJournalDialog = false }) {
                        Text("Cancelar", style = MaterialTheme.typography.labelLarge)
                    }
                }
            )
        }
    }
}
