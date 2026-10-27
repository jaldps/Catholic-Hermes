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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.JournalEntryEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.models.GregorianChantItem
import com.example.data.models.TriviaQuestion
import com.example.data.models.ViaCrucisStation
import com.example.data.repository.CatholicDataRepository
import com.example.ui.theme.LiturgicalGold
import com.example.ui.theme.SacredBurgundy
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
            contentColor = SacredBurgundy,
            edgePadding = 12.dp
        ) {
            Tab(
                selected = selectedTopTab == 0,
                onClick = { selectedTopTab = 0 },
                text = { Text("Diario Espiritual", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
            )
            Tab(
                selected = selectedTopTab == 1,
                onClick = { selectedTopTab = 1 },
                text = { Text("Trivia Católica", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
            )
            Tab(
                selected = selectedTopTab == 2,
                onClick = { selectedTopTab = 2 },
                text = { Text("Santo Vía Crucis", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
            )
            Tab(
                selected = selectedTopTab == 3,
                onClick = { selectedTopTab = 3 },
                text = { Text("Canto Gregoriano", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
            )
        }

        when (selectedTopTab) {
            0 -> {
                // Catholic Spiritual Diary & Examen
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        item {
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Diario Católico y Peticiones"
                                    AppLanguage.EN -> "Catholic Spiritual Journal"
                                    AppLanguage.PT -> "Diário Espiritual Católico"
                                },
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = SacredBurgundy
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
                                    shape = RoundedCornerShape(16.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(24.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = LiturgicalGold, modifier = Modifier.size(36.dp))
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = when (currentLanguage) {
                                                AppLanguage.ES -> "Tu diario espiritual está vacío"
                                                AppLanguage.EN -> "Your spiritual journal is empty"
                                                AppLanguage.PT -> "Seu diário espiritual está vazio"
                                            },
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp,
                                            color = SacredBurgundy
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = when (currentLanguage) {
                                                AppLanguage.ES -> "Toca el botón '+' abajo para registrar tu primera intención o agradecimiento."
                                                AppLanguage.EN -> "Tap '+' below to write your first prayer intention or thanksgiving."
                                                AppLanguage.PT -> "Toque no botão '+' abaixo para anotar sua intenção de oração."
                                            },
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        } else {
                            items(journalEntries) { entry ->
                                ElevatedCard(
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(6.dp))
                                                        .background(if (entry.isAnswered) LiturgicalGold.copy(alpha = 0.2f) else SacredBurgundy.copy(alpha = 0.15f))
                                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                                ) {
                                                    Text(
                                                        text = if (entry.isAnswered) "✓ GRACIA CONCEDIDA" else entry.category.uppercase(),
                                                        fontSize = 10.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = if (entry.isAnswered) LiturgicalGold else SacredBurgundy
                                                    )
                                                }
                                            }

                                            IconButton(onClick = {
                                                coroutineScope.launch { dao.deleteJournalEntry(entry) }
                                            }) {
                                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(18.dp))
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(6.dp))

                                        Text(
                                            text = entry.title,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )

                                        Spacer(modifier = Modifier.height(6.dp))

                                        Text(
                                            text = entry.content,
                                            style = MaterialTheme.typography.bodyMedium,
                                            lineHeight = 20.sp
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

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
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = LiturgicalGold
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
                            .padding(20.dp)
                            .testTag("add_journal_entry_fab"),
                        containerColor = SacredBurgundy,
                        contentColor = Color.White
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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PREGUNTA ${triviaIndex + 1} DE ${triviaList.size}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = LiturgicalGold
                        )
                        Text(
                            text = "Puntos: $triviaScore",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
                        )
                    }

                    ElevatedCard(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = currentQ.category.get(currentLanguage),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = LiturgicalGold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = currentQ.question.get(currentLanguage),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 26.sp
                            )
                        }
                    }

                    // 4 Options
                    currentQ.options.forEachIndexed { idx, opt ->
                        val isSelected = selectedOptionIndex == idx
                        val isCorrect = idx == currentQ.correctIndex

                        val borderCol = when {
                            hasAnswered && isCorrect -> Color(0xFF2E7D32)
                            hasAnswered && isSelected && !isCorrect -> Color(0xFFC62828)
                            isSelected -> SacredBurgundy
                            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                        }

                        val bgCol = when {
                            hasAnswered && isCorrect -> Color(0xFF2E7D32).copy(alpha = 0.15f)
                            hasAnswered && isSelected && !isCorrect -> Color(0xFFC62828).copy(alpha = 0.15f)
                            isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                            else -> MaterialTheme.colorScheme.surface
                        }

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .border(1.5.dp, borderCol, RoundedCornerShape(14.dp))
                                .background(bgCol)
                                .clickable(enabled = !hasAnswered) {
                                    selectedOptionIndex = idx
                                    hasAnswered = true
                                    if (idx == currentQ.correctIndex) {
                                        triviaScore += 10
                                    }
                                }
                                .padding(14.dp)
                        ) {
                            Text(
                                text = opt.get(currentLanguage),
                                fontSize = 14.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // Doctrinal Explanation when answered
                    if (hasAnswered) {
                        ElevatedCard(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f))
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = if (selectedOptionIndex == currentQ.correctIndex) "¡Correcto! ✦" else "Doctrina Católica:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = SacredBurgundy
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = currentQ.doctrinalExplanation.get(currentLanguage),
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp
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
                            colors = ButtonDefaults.filledTonalButtonColors(containerColor = SacredBurgundy, contentColor = Color.White)
                        ) {
                            Text("Siguiente Pregunta →", fontWeight = FontWeight.Bold)
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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "ESTACIÓN ${currentStation.stationNumber} DE 14",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = LiturgicalGold
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                stations.indices.forEach { idx ->
                                    Box(
                                        modifier = Modifier
                                            .size(if (idx == currentStationIndex) 10.dp else 6.dp)
                                            .clip(CircleShape)
                                            .background(if (idx == currentStationIndex) SacredBurgundy else MaterialTheme.colorScheme.outline)
                                    )
                                }
                            }
                        }
                    }

                    item {
                        ElevatedCard(
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Text(
                                    text = currentStation.title.get(currentLanguage),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = SacredBurgundy
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = currentStation.scripture.get(currentLanguage),
                                    fontFamily = FontFamily.Serif,
                                    fontSize = 12.sp,
                                    color = LiturgicalGold
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                Text(
                                    text = currentStation.meditation.get(currentLanguage),
                                    style = MaterialTheme.typography.bodyLarge,
                                    lineHeight = 24.sp
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(MaterialTheme.colorScheme.surface)
                                        .padding(12.dp)
                                ) {
                                    Text(
                                        text = currentStation.prayer.get(currentLanguage),
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 13.sp,
                                        color = SacredBurgundy
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
                                colors = ButtonDefaults.elevatedButtonColors(containerColor = SacredBurgundy, contentColor = Color.White)
                            ) {
                                Icon(if (isPlayingAudio) Icons.Default.Stop else Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Rezar Estación")
                            }

                            ElevatedButton(
                                onClick = {
                                    if (currentStationIndex < stations.size - 1) currentStationIndex++
                                },
                                enabled = currentStationIndex < stations.size - 1,
                                colors = ButtonDefaults.elevatedButtonColors(containerColor = LiturgicalGold, contentColor = Color.Black)
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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = "Reproductor de Canto Gregoriano y Música Sacra",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
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
                                .clip(RoundedCornerShape(16.dp))
                                .clickable { selectedChant = chant },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f) else MaterialTheme.colorScheme.surfaceVariant
                            ),
                            border = CardDefaults.outlinedCardBorder().copy(width = if (isSelected) 1.5.dp else 0.5.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.MusicNote, contentDescription = null, tint = SacredBurgundy)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(text = chant.title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = SacredBurgundy)
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
                                        Icon(if (isPlayingAudio && isSelected) Icons.Default.Stop else Icons.Default.PlayArrow, contentDescription = "Play Chant", tint = LiturgicalGold)
                                    }
                                }

                                Text(text = chant.liturgicalSeason.get(currentLanguage), fontSize = 11.sp, color = LiturgicalGold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = chant.description.get(currentLanguage), fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "Letra en Latín:", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = SacredBurgundy)
                                Text(text = chant.latinLyrics, fontFamily = FontFamily.Serif, fontSize = 12.sp, lineHeight = 18.sp)

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = "Traducción:", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = LiturgicalGold)
                                Text(text = chant.translatedLyrics.get(currentLanguage), fontSize = 12.sp, lineHeight = 18.sp)
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
                        fontWeight = FontWeight.Bold,
                        color = SacredBurgundy
                    )
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(
                            value = newJournalTitle,
                            onValueChange = { newJournalTitle = it },
                            label = { Text("Título de la intención o gracia") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            listOf("Intención", "Agradecimiento", "Examen", "Gracia").forEach { cat ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (newJournalCategory == cat) SacredBurgundy else MaterialTheme.colorScheme.surface)
                                        .border(1.dp, SacredBurgundy, RoundedCornerShape(8.dp))
                                        .clickable { newJournalCategory = cat }
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = cat,
                                        fontSize = 11.sp,
                                        color = if (newJournalCategory == cat) Color.White else MaterialTheme.colorScheme.onSurface
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
                        Text("Guardar en el Diario", fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddJournalDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}
