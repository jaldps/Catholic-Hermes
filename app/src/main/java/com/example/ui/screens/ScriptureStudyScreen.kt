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
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.data.local.SanctaFidesDao
import com.example.data.local.ScriptureStudyProgressEntity
import com.example.data.models.AppLanguage
import com.example.data.models.ScriptureCourseTrack
import com.example.data.models.ScriptureLesson
import com.example.data.repository.CatholicDataRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScriptureStudyScreen(
    repository: CatholicDataRepository,
    dao: SanctaFidesDao,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val tracks = repository.scriptureStudyTracks
    var selectedTrackIndex by remember { mutableIntStateOf(0) }
    val currentTrack = tracks.getOrElse(selectedTrackIndex) { tracks.first() }

    val allProgress by dao.getAllScriptureProgress().collectAsState(initial = emptyList())
    val completedLessonIds = remember(allProgress) {
        allProgress.filter { it.isCompleted }.map { it.lessonId }.toSet()
    }

    var activeLesson by remember { mutableStateOf<ScriptureLesson?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("scripture_study_screen")
    ) {
        SecondaryTabRow(
            selectedTabIndex = selectedTrackIndex,
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            tracks.forEachIndexed { index, track ->
                Tab(
                    selected = selectedTrackIndex == index,
                    onClick = {
                        selectedTrackIndex = index
                        activeLesson = null
                    },
                    text = {
                        Text(
                            text = when (index) {
                                0 -> "1. Sacramentos"
                                1 -> "2. Parábolas"
                                else -> "3. Cartas Paulinas"
                            },
                            fontWeight = if (selectedTrackIndex == index) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    }
                )
            }
        }

        if (activeLesson == null) {
            TrackOverviewView(
                track = currentTrack,
                completedLessonIds = completedLessonIds,
                currentLanguage = currentLanguage,
                onSelectLesson = { lesson -> activeLesson = lesson }
            )
        } else {
            val lesson = activeLesson!!
            val isLessonDone = completedLessonIds.contains(lesson.id)
            val currentEntity = allProgress.firstOrNull { it.lessonId == lesson.id }

            LessonDetailView(
                lesson = lesson,
                isCompleted = isLessonDone,
                existingNotes = currentEntity?.personalNotes ?: "",
                ttsManager = ttsManager,
                currentLanguage = currentLanguage,
                onBack = { activeLesson = null },
                onToggleComplete = { notes ->
                    scope.launch {
                        dao.saveScriptureProgress(
                            ScriptureStudyProgressEntity(
                                lessonId = lesson.id,
                                trackId = lesson.trackId,
                                isCompleted = !isLessonDone,
                                completedTimestamp = if (!isLessonDone) System.currentTimeMillis() else 0L,
                                personalNotes = notes
                            )
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun TrackOverviewView(
    track: ScriptureCourseTrack,
    completedLessonIds: Set<String>,
    currentLanguage: AppLanguage,
    onSelectLesson: (ScriptureLesson) -> Unit
) {
    val completedInTrack = track.lessons.count { completedLessonIds.contains(it.id) }
    val progressPercent = if (track.lessons.isNotEmpty()) {
        (completedInTrack.toFloat() / track.lessons.size.toFloat()) * 100f
    } else 0f

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.MenuBook, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(20.dp))
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = track.title.get(currentLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Alineado con el Catecismo y Dei Verbum del Vaticano",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = track.description.get(currentLanguage),
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Progreso del curso: $completedInTrack de ${track.lessons.size} lecciones",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${progressPercent.toInt()}%",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "Lecciones del Curso",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }

        items(track.lessons) { lesson ->
            val isDone = completedLessonIds.contains(lesson.id)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectLesson(lesson) },
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = if (isDone) MaterialTheme.colorScheme.primary.copy(alpha = 0.05f) else MaterialTheme.colorScheme.surface
                ),
                border = if (isDone) CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.secondary)) else CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = if (isDone) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (isDone) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondary, modifier = Modifier.size(20.dp))
                            } else {
                                Text(
                                    text = "${lesson.lessonNumber}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = lesson.title.get(currentLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = lesson.biblicalReference,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                        )
                    }

                    Icon(
                        Icons.Default.AutoStories,
                        contentDescription = "Read lesson",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun LessonDetailView(
    lesson: ScriptureLesson,
    isCompleted: Boolean,
    existingNotes: String,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    onBack: () -> Unit,
    onToggleComplete: (notes: String) -> Unit
) {
    var notesText by remember(lesson.id) { mutableStateOf(existingNotes) }

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
                FilledTonalButton(onClick = onBack) {
                    Text("← Volver al Curso", fontSize = 12.sp)
                }

                ElevatedButton(
                    onClick = { onToggleComplete(notesText) },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = if (isCompleted) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                        contentColor = if (isCompleted) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(if (isCompleted) "Completada" else "Marcar Hecha", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Header
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "LECCIÓN ${lesson.lessonNumber}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = lesson.title.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = lesson.biblicalReference,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // 1. Biblical Passage Card
        item {
            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PASO 1: LECTURA DE LA ESCRITURA (LECTIO)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        IconButton(
                            onClick = {
                                ttsManager.speakSingle(lesson.biblicalPassageText.get(currentLanguage))
                            }
                        ) {
                            Icon(Icons.Default.VolumeUp, contentDescription = "Listen", tint = MaterialTheme.colorScheme.primary)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = lesson.biblicalPassageText.get(currentLanguage),
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // 2. Vatican Context & Catechism
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "MAGISTERIO DEL VATICANO Y CATECISMO",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = lesson.vaticanContext.get(currentLanguage),
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (lesson.catechismRefs.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            lesson.catechismRefs.forEach { ref ->
                                Surface(
                                    shape = MaterialTheme.shapes.small,
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                ) {
                                    Text(
                                        text = ref,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 3. Four Steps of Lectio Divina
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "GUÍA DE LECTIO DIVINA",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LectioStepItem(
                        badge = "1. LECTIO",
                        question = "¿Qué dice el texto sagrado?",
                        content = lesson.lectioStep.get(currentLanguage)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LectioStepItem(
                        badge = "2. MEDITATIO",
                        question = "¿Qué me dice Dios hoy a mí a través de este pasaje?",
                        content = lesson.meditatioStep.get(currentLanguage)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LectioStepItem(
                        badge = "3. ORATIO",
                        question = "¿Qué le respondo a Dios en mi corazón?",
                        content = lesson.oratioStep.get(currentLanguage)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LectioStepItem(
                        badge = "4. CONTEMPLATIO",
                        question = "¿Cómo transforma mi vida esta verdad hoy?",
                        content = lesson.contemplatioStep.get(currentLanguage)
                    )
                }
            }
        }

        // 4. Personal Reflection Notes
        item {
            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Mis Notas y Frutos Espirituales",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Escribe una breve resolución o lo que Dios te ha inspirado en esta lección.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = notesText,
                        onValueChange = { notesText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        placeholder = { Text("Mi resolución de hoy...", fontSize = 12.sp) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ElevatedButton(
                        onClick = { onToggleComplete(notesText) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(if (isCompleted) "Guardar Cambios" else "Guardar y Completar Lección", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun LectioStepItem(
    badge: String,
    question: String,
    content: String
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = badge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = question,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = content,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
