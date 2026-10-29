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
import androidx.compose.ui.platform.testTag
import com.example.ui.theme.spacing
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
                            style = if (selectedTrackIndex == index) MaterialTheme.typography.labelLarge else MaterialTheme.typography.bodySmall
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
            .padding(MaterialTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg)
    ) {
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f))
            ) {
                Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(MaterialTheme.spacing.xl + MaterialTheme.spacing.md)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.MenuBook, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(MaterialTheme.spacing.md))
                            }
                        }

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm + MaterialTheme.spacing.xs))

                        Column {
                            Text(
                                text = track.title.get(currentLanguage),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Alineado con el Catecismo y Dei Verbum del Vaticano",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

                    Text(
                        text = track.description.get(currentLanguage),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Progreso del curso: $completedInTrack de ${track.lessons.size} lecciones",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${progressPercent.toInt()}%",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "Lecciones del Curso",
                style = MaterialTheme.typography.titleSmall,
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
                        .padding(MaterialTheme.spacing.lg),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = if (isDone) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.size(MaterialTheme.spacing.xl + MaterialTheme.spacing.md)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (isDone) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(MaterialTheme.spacing.md))
                            } else {
                                Text(
                                    text = "${lesson.lessonNumber}",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.md - MaterialTheme.spacing.xs))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = lesson.title.get(currentLanguage),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = lesson.biblicalReference,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Icon(
                        Icons.Default.AutoStories,
                        contentDescription = "Read lesson",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(MaterialTheme.spacing.md)
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
            .padding(MaterialTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(onClick = onBack) {
                    Text("← Volver al Curso", style = MaterialTheme.typography.bodySmall)
                }

                ElevatedButton(
                    onClick = { onToggleComplete(notesText) },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = if (isCompleted) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(MaterialTheme.spacing.md)
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
                    Text(if (isCompleted) "Completada" else "Marcar Hecha", style = MaterialTheme.typography.labelLarge)
                }
            }
        }

        // Header
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {
                    Text(
                        text = "LECCIÓN ${lesson.lessonNumber}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))
                    Text(
                        text = lesson.title.get(currentLanguage),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))
                    Text(
                        text = lesson.biblicalReference,
                        style = MaterialTheme.typography.titleSmall,
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
                Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PASO 1: LECTURA DE LA ESCRITURA (LECTIO)",
                            style = MaterialTheme.typography.labelSmall,
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

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

                    Text(
                        text = lesson.biblicalPassageText.get(currentLanguage),
                        style = MaterialTheme.typography.bodyLarge,
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
                Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {
                    Text(
                        text = "MAGISTERIO DEL VATICANO Y CATECISMO",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))
                    Text(
                        text = lesson.vaticanContext.get(currentLanguage),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (lesson.catechismRefs.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))
                        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                            lesson.catechismRefs.forEach { ref ->
                                Surface(
                                    shape = MaterialTheme.shapes.small,
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                ) {
                                    Text(
                                        text = ref,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.sm, vertical = MaterialTheme.spacing.xs)
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
                Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {
                    Text(
                        text = "GUÍA DE LECTIO DIVINA",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

                    LectioStepItem(
                        badge = "1. LECTIO",
                        question = "¿Qué dice el texto sagrado?",
                        content = lesson.lectioStep.get(currentLanguage)
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

                    LectioStepItem(
                        badge = "2. MEDITATIO",
                        question = "¿Qué me dice Dios hoy a mí a través de este pasaje?",
                        content = lesson.meditatioStep.get(currentLanguage)
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

                    LectioStepItem(
                        badge = "3. ORATIO",
                        question = "¿Qué le respondo a Dios en mi corazón?",
                        content = lesson.oratioStep.get(currentLanguage)
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

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
                Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {
                    Text(
                        text = "Mis Notas y Frutos Espirituales",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))
                    Text(
                        text = "Escribe una breve resolución o lo que Dios te ha inspirado en esta lección.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

                    OutlinedTextField(
                        value = notesText,
                        onValueChange = { notesText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(MaterialTheme.spacing.xxl * 3 + MaterialTheme.spacing.xs),
                        placeholder = { Text("Mi resolución de hoy...", style = MaterialTheme.typography.bodySmall) }
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

                    ElevatedButton(
                        onClick = { onToggleComplete(notesText) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(if (isCompleted) "Guardar Cambios" else "Guardar y Completar Lección", style = MaterialTheme.typography.bodyMedium)
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
        Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = badge,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))
                Text(
                    text = question,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))
            Text(
                text = content,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
