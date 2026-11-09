package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDao
import com.example.data.local.ScriptureStudyProgressEntity
import com.example.data.models.AppLanguage
import com.example.data.models.ScriptureCourseTrack
import com.example.data.models.ScriptureLesson
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppChevron
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppProgressRing
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.GroupPosition
import com.example.ui.design.appScreenInsets
import com.example.ui.design.groupedItem
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

private fun trackIcon(track: ScriptureCourseTrack): ImageVector = when (track.iconName) {
    "Church" -> Icons.Outlined.Church
    "AutoStories" -> Icons.Outlined.AutoStories
    else -> Icons.AutoMirrored.Outlined.MenuBook
}

/**
 * The guided scripture course, shown as the study tab of [BibleScreen]: the
 * itineraries as cards, the lessons of the selected one, and one lesson read
 * as a lectio divina. Completion and the reader's own notes live in Room.
 */
@Composable
fun ScriptureStudyScreen(
    repository: CatholicDataRepository,
    dao: SanctaFidesDao,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    /** The host screen's title and view switcher, set as the first rows. */
    header: LazyListScope.() -> Unit = {}
) {
    val tracks = repository.scriptureStudyTracks
    var selectedTrackIndex by rememberSaveable { mutableIntStateOf(0) }
    var activeLessonId by rememberSaveable { mutableStateOf<String?>(null) }

    val allProgress by dao.getAllScriptureProgress().collectAsState(initial = emptyList())
    val completedLessonIds = remember(allProgress) {
        allProgress.filter { it.isCompleted }.map { it.lessonId }.toSet()
    }
    val scope = rememberCoroutineScope()
    val bottom = appScreenInsets().calculateBottomPadding() + 32.dp

    Box(modifier.fillMaxSize().testTag("scripture_study_screen")) {
        if (tracks.isEmpty()) {
            LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = bottom)) {
                header()
                item {
                    AppEmptyState(
                        title = currentLanguage.pick("Sin itinerarios", "No study tracks", "Sem itinerários"),
                        body = currentLanguage.pick(
                            "El curso bíblico no está disponible en esta edición.",
                            "The scripture course is not available in this edition.",
                            "O curso bíblico não está disponível nesta edição."
                        ),
                        icon = Icons.AutoMirrored.Outlined.MenuBook
                    )
                }
            }
            return@Box
        }
        val currentTrack = tracks.getOrElse(selectedTrackIndex) { tracks.first() }
        val lesson = activeLessonId?.let { id -> tracks.flatMap { it.lessons }.firstOrNull { it.id == id } }
        if (lesson == null) {
            TrackOverviewView(
                header = header,
                tracks = tracks,
                track = currentTrack,
                selectedTrackIndex = selectedTrackIndex,
                completedLessonIds = completedLessonIds,
                currentLanguage = currentLanguage,
                bottom = bottom,
                onSelectTrack = { selectedTrackIndex = it },
                onSelectLesson = { activeLessonId = it.id }
            )
        } else {
            val isLessonDone = completedLessonIds.contains(lesson.id)
            val currentEntity = allProgress.firstOrNull { it.lessonId == lesson.id }
            AppBackHandler { activeLessonId = null }
            LessonDetailView(
                lesson = lesson,
                isCompleted = isLessonDone,
                existingNotes = currentEntity?.personalNotes ?: "",
                ttsManager = ttsManager,
                currentLanguage = currentLanguage,
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
                },
                // Saving the notes of a lesson already marked done must not
                // un-mark it, nor re-date when it was completed.
                onSaveNotes = { notes ->
                    scope.launch {
                        dao.saveScriptureProgress(
                            ScriptureStudyProgressEntity(
                                lessonId = lesson.id,
                                trackId = lesson.trackId,
                                isCompleted = isLessonDone,
                                completedTimestamp = currentEntity?.completedTimestamp ?: 0L,
                                personalNotes = notes
                            )
                        )
                    }
                }
            )
        }
    }
}

// ------------------------------------------------------------------- overview

@Composable
private fun TrackOverviewView(
    header: LazyListScope.() -> Unit,
    tracks: List<ScriptureCourseTrack>,
    track: ScriptureCourseTrack,
    selectedTrackIndex: Int,
    completedLessonIds: Set<String>,
    currentLanguage: AppLanguage,
    bottom: androidx.compose.ui.unit.Dp,
    onSelectTrack: (Int) -> Unit,
    onSelectLesson: (ScriptureLesson) -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current

    val total = track.lessons.size
    val done = track.lessons.count { completedLessonIds.contains(it.id) }
    val fraction = if (total > 0) done.toFloat() / total.toFloat() else 0f
    val progressSummary = currentLanguage.pick(
        "$done de $total lecciones · ${(fraction * 100).toInt()} % del curso",
        "$done of $total lessons · ${(fraction * 100).toInt()}% of the course",
        "$done de $total lições · ${(fraction * 100).toInt()} % do curso"
    )
    val nextLesson = track.lessons.firstOrNull { !completedLessonIds.contains(it.id) }

    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = bottom)) {
        header()

        // The itineraries as cards: each shows how far it has been read and
        // selects itself, so no second index of tracks is needed.
        item(key = "study_tracks") {
            LazyRow(
                Modifier.fillMaxWidth().testTag("study_track_picker"),
                contentPadding = PaddingValues(horizontal = design.spacing.gutter),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(tracks, key = { _, t -> t.id }) { index, candidate ->
                    val read = candidate.lessons.count { completedLessonIds.contains(it.id) }
                    TrackCard(
                        track = candidate,
                        read = read,
                        selected = index == selectedTrackIndex,
                        currentLanguage = currentLanguage,
                        onClick = { onSelectTrack(index) }
                    )
                }
            }
        }

        item(key = "study_track_head") {
            Column(Modifier.padding(horizontal = design.spacing.gutter).padding(top = 28.dp)) {
                Text(track.title.get(currentLanguage), style = styles.title2, color = p.textPrimary, modifier = Modifier.semantics { heading() })
                Spacer(Modifier.height(8.dp))
                Text(track.description.get(currentLanguage), style = styles.callout, color = p.textSecondary)
                Spacer(Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.Verified, null, tint = p.textSecondary, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(
                        currentLanguage.pick(
                            "Alineado con el Catecismo y Dei Verbum del Vaticano",
                            "Aligned with the Catechism and the Vatican's Dei Verbum",
                            "Alinhado com o Catecismo e a Dei Verbum do Vaticano"
                        ),
                        style = styles.footnote, color = p.textSecondary
                    )
                }
                Spacer(Modifier.height(20.dp))
                AppProgressBar(
                    progress = fraction,
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics {
                            contentDescription = track.title.get(currentLanguage)
                            stateDescription = progressSummary
                        }
                        .testTag("study_course_progress"),
                    height = 4.dp
                )
                Spacer(Modifier.height(8.dp))
                Text(progressSummary, style = styles.footnote, color = p.textSecondary)
                if (track.lessons.isNotEmpty()) {
                    Spacer(Modifier.height(18.dp))
                    AppPrimaryCapsule(
                        label = when {
                            done == 0 -> currentLanguage.pick("Empezar el itinerario", "Begin the course", "Começar o itinerário")
                            nextLesson != null -> currentLanguage.pick(
                                "Continuar · Lección ${nextLesson.lessonNumber}",
                                "Continue · Lesson ${nextLesson.lessonNumber}",
                                "Continuar · Lição ${nextLesson.lessonNumber}"
                            )
                            else -> currentLanguage.pick("Repasar el itinerario", "Review the course", "Rever o itinerário")
                        },
                        onClick = { onSelectLesson(nextLesson ?: track.lessons.first()) },
                        icon = Icons.Filled.PlayArrow,
                        tag = "study_continue_button"
                    )
                }
                Spacer(Modifier.height(28.dp))
                Text(
                    currentLanguage.pick("Lecciones", "Lessons", "Lições"),
                    style = styles.title3, color = p.textPrimary,
                    modifier = Modifier.padding(bottom = 10.dp).semantics { heading() }
                )
            }
        }

        if (track.lessons.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Sin lecciones", "No lessons", "Sem lições"),
                    body = currentLanguage.pick(
                        "Este itinerario aún no tiene lecciones en la edición incluida.",
                        "This track has no lessons in the bundled edition yet.",
                        "Este itinerário ainda não tem lições na edição incluída."
                    ),
                    icon = Icons.AutoMirrored.Outlined.MenuBook
                )
            }
        } else {
            itemsIndexed(track.lessons, key = { _, l -> "lesson_${l.id}" }) { index, lesson ->
                LessonRow(
                    lesson = lesson,
                    done = completedLessonIds.contains(lesson.id),
                    position = positionOf(index, track.lessons.size),
                    currentLanguage = currentLanguage,
                    onClick = { onSelectLesson(lesson) }
                )
            }
        }
    }
}

@Composable
private fun TrackCard(
    track: ScriptureCourseTrack,
    read: Int,
    selected: Boolean,
    currentLanguage: AppLanguage,
    onClick: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val shape = RoundedCornerShape(24.dp)
    val total = track.lessons.size.coerceAtLeast(1)
    Box(
        Modifier
            .width(252.dp)
            .height(184.dp)
            .lumenPress(onClick, 0.97f, Role.Tab)
            .lumenGlass(shape, strong = selected)
            .then(if (selected) Modifier.border(1.5.dp, p.textPrimary, shape) else Modifier)
            .semantics { this.selected = selected }
            .testTag("track_row_${track.id}")
            .padding(18.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            AppProgressRing(progress = read.toFloat() / total, size = 44.dp, stroke = 3.dp) {
                Icon(trackIcon(track), null, tint = p.textPrimary, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.weight(1f))
            Text(
                track.title.get(currentLanguage),
                style = styles.headline,
                color = p.textPrimary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                currentLanguage.pick("$read de ${track.lessons.size} lecciones", "$read of ${track.lessons.size} lessons", "$read de ${track.lessons.size} lições"),
                style = styles.footnote, color = p.textSecondary
            )
        }
    }
}

/** A lesson as a step: its number in a glass circle, a check once it is done. */
@Composable
private fun LessonRow(
    lesson: ScriptureLesson,
    done: Boolean,
    position: GroupPosition,
    currentLanguage: AppLanguage,
    onClick: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(
        Modifier.groupedItem(position)
            .semantics { if (done) stateDescription = currentLanguage.pick("Hecha", "Done", "Feita") }
            .testTag("lesson_row_${lesson.id}")
    ) {
        Row(
            Modifier.fillMaxWidth().lumenPress(onClick, 0.99f).heightIn(min = 64.dp).padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier.size(32.dp).then(
                    if (done) Modifier.clip(CircleShape).background(p.success)
                    else Modifier.lumenGlass(CircleShape, strong = true, lifted = false)
                ),
                contentAlignment = Alignment.Center
            ) {
                if (done) Icon(Icons.Filled.Check, null, tint = androidx.compose.ui.graphics.Color.White, modifier = Modifier.size(18.dp))
                else Text("${lesson.lessonNumber}", style = styles.subhead.copy(fontWeight = FontWeight.Medium), color = p.textPrimary)
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(lesson.title.get(currentLanguage), style = styles.body, color = p.textPrimary)
                Spacer(Modifier.height(2.dp))
                Text(lesson.biblicalReference, style = styles.footnote, color = p.textSecondary)
            }
            Spacer(Modifier.width(10.dp))
            AppChevron()
        }
        if (position == GroupPosition.First || position == GroupPosition.Middle) {
            Box(Modifier.fillMaxWidth().padding(start = 62.dp).height(0.5.dp).background(p.separator))
        }
    }
}

// --------------------------------------------------------------------- lesson

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LessonDetailView(
    lesson: ScriptureLesson,
    isCompleted: Boolean,
    existingNotes: String,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    onToggleComplete: (notes: String) -> Unit,
    onSaveNotes: (notes: String) -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val insets = appScreenInsets()
    var notesText by remember(lesson.id) { mutableStateOf(existingNotes) }
    var savedFlash by remember(lesson.id) { mutableIntStateOf(0) }
    val passage = lesson.biblicalPassageText.get(currentLanguage)
    val speaking by ttsManager.currentlySpeaking.collectAsState()
    val playing by ttsManager.isPlaying.collectAsState()
    val readingPassage = playing && speaking == passage
    val inset = design.spacing.gutter + 4.dp

    AppInlineTitle(lesson.title.get(currentLanguage))
    LaunchedEffect(savedFlash) { if (savedFlash > 0) { delay(1600); savedFlash = 0 } }

    LazyColumn(
        Modifier.fillMaxSize().imePadding(),
        contentPadding = PaddingValues(top = insets.calculateTopPadding(), bottom = insets.calculateBottomPadding() + 40.dp)
    ) {
        item(key = "lesson_head") {
            Column(Modifier.padding(horizontal = inset).padding(top = 8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        currentLanguage.pick("Lección ${lesson.lessonNumber}", "Lesson ${lesson.lessonNumber}", "Lição ${lesson.lessonNumber}"),
                        style = styles.eyebrow, color = p.textSecondary, modifier = Modifier.weight(1f)
                    )
                    // Done / not done, one tap either way.
                    Row(
                        Modifier
                            .heightIn(min = 40.dp)
                            .lumenPress({ onToggleComplete(notesText) }, 0.96f)
                            .then(
                                if (isCompleted) Modifier.clip(RoundedCornerShape(percent = 50)).background(p.success)
                                else Modifier.lumenGlass(RoundedCornerShape(percent = 50), strong = true)
                            )
                            .semantics { stateDescription = if (isCompleted) currentLanguage.pick("Completada", "Completed", "Concluída") else "" }
                            .testTag("toggle_lesson_complete_button")
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.Check, null,
                            tint = if (isCompleted) androidx.compose.ui.graphics.Color.White else p.textSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            if (isCompleted) currentLanguage.pick("Completada", "Completed", "Concluída")
                            else currentLanguage.pick("Marcar como hecha", "Mark as done", "Marcar como feita"),
                            style = styles.subhead.copy(fontWeight = FontWeight.Medium),
                            color = if (isCompleted) androidx.compose.ui.graphics.Color.White else p.textPrimary
                        )
                    }
                }
                Spacer(Modifier.height(6.dp))
                Text(lesson.title.get(currentLanguage), style = styles.title1, color = p.textPrimary, modifier = Modifier.semantics { heading() })
                Spacer(Modifier.height(6.dp))
                Text(lesson.biblicalReference, style = styles.subhead, color = p.textSecondary)
            }
        }

        // Step 1: the passage, read and heard.
        item(key = "lesson_lectio") {
            LessonSection(
                eyebrow = currentLanguage.pick("Paso 1 · Lectio", "Step 1 · Lectio", "Passo 1 · Lectio"),
                title = currentLanguage.pick("Lectura de la Escritura", "Reading of Scripture", "Leitura da Escritura")
            ) {
                Text(
                    passage, style = styles.reading, color = p.textPrimary,
                    modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                )
                Spacer(Modifier.height(16.dp))
                AppSecondaryCapsule(
                    label = if (readingPassage) currentLanguage.pick("Detener", "Stop", "Parar")
                    else currentLanguage.pick("Escuchar", "Listen", "Ouvir"),
                    onClick = { if (readingPassage) ttsManager.stop() else ttsManager.speakSingle(passage) },
                    icon = if (readingPassage) Icons.Filled.Stop else Icons.Filled.PlayArrow,
                    tag = "lesson_listen_button"
                )
            }
        }

        item(key = "lesson_magisterium") {
            LessonSection(
                eyebrow = currentLanguage.pick("Contexto", "Context", "Contexto"),
                title = currentLanguage.pick(
                    "Magisterio del Vaticano y Catecismo",
                    "Vatican Magisterium & Catechism",
                    "Magistério do Vaticano e Catecismo"
                )
            ) {
                if (lesson.catechismRefs.isNotEmpty()) {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        lesson.catechismRefs.forEach { ref ->
                            Box(
                                Modifier.lumenGlass(RoundedCornerShape(percent = 50), lifted = false)
                                    .padding(horizontal = 12.dp, vertical = 5.dp)
                            ) { Text(ref, style = styles.caption, color = p.textPrimary) }
                        }
                    }
                    Spacer(Modifier.height(14.dp))
                }
                Text(
                    lesson.vaticanContext.get(currentLanguage), style = styles.callout, color = p.textPrimary,
                    modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                )
            }
        }

        item(key = "lesson_guide") {
            LessonSection(
                eyebrow = currentLanguage.pick("Oración", "Prayer", "Oração"),
                title = currentLanguage.pick("Guía de lectio divina", "Guide to lectio divina", "Guia de lectio divina")
            ) {}
            AppGlassSurface(
                Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
                contentPadding = PaddingValues(vertical = 6.dp)
            ) {
                Column {
                    val steps = listOf(
                        Triple("Lectio", currentLanguage.pick("¿Qué dice el texto sagrado?", "What does the sacred text say?", "O que diz o texto sagrado?"), lesson.lectioStep),
                        Triple("Meditatio", currentLanguage.pick("¿Qué me dice Dios hoy a mí a través de este pasaje?", "What is God saying to me today through this passage?", "O que Deus me diz hoje através desta passagem?"), lesson.meditatioStep),
                        Triple("Oratio", currentLanguage.pick("¿Qué le respondo a Dios en mi corazón?", "What do I answer God in my heart?", "O que respondo a Deus no meu coração?"), lesson.oratioStep),
                        Triple("Contemplatio", currentLanguage.pick("¿Cómo transforma mi vida esta verdad hoy?", "How does this truth change my life today?", "Como esta verdade transforma a minha vida hoje?"), lesson.contemplatioStep)
                    )
                    steps.forEachIndexed { i, (name, question, content) ->
                        LectioStep(number = i + 1, name = name, question = question, content = content.get(currentLanguage))
                        if (i < steps.lastIndex) {
                            Box(Modifier.fillMaxWidth().padding(start = 64.dp).height(0.5.dp).background(p.separator))
                        }
                    }
                }
            }
        }

        item(key = "lesson_notes") {
            LessonSection(
                eyebrow = currentLanguage.pick("Fruto", "Fruit", "Fruto"),
                title = currentLanguage.pick("Mis notas y frutos espirituales", "My notes & spiritual fruits", "Minhas notas e frutos espirituais")
            ) {
                Text(
                    currentLanguage.pick(
                        "Escribe una breve resolución o lo que Dios te ha inspirado en esta lección.",
                        "Write a short resolution, or whatever God has inspired in you in this lesson.",
                        "Escreva uma breve resolução ou o que Deus lhe inspirou nesta lição."
                    ),
                    style = styles.footnote, color = p.textSecondary
                )
                Spacer(Modifier.height(12.dp))
                NotesField(
                    value = notesText,
                    onValueChange = { notesText = it },
                    placeholder = currentLanguage.pick("Mi resolución de hoy…", "My resolution for today…", "A minha resolução de hoje…"),
                    modifier = Modifier.testTag("lesson_notes_field")
                )
                Spacer(Modifier.height(16.dp))
                AppPrimaryCapsule(
                    label = if (isCompleted) currentLanguage.pick("Guardar cambios", "Save changes", "Guardar alterações")
                    else currentLanguage.pick("Guardar y completar lección", "Save & complete lesson", "Guardar e concluir a lição"),
                    onClick = {
                        if (isCompleted) onSaveNotes(notesText) else onToggleComplete(notesText)
                        savedFlash++
                    },
                    tag = "save_lesson_button"
                )
                AnimatedVisibility(savedFlash > 0, enter = fadeIn(), exit = fadeOut()) {
                    Row(Modifier.fillMaxWidth().padding(top = 10.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Check, null, tint = p.success, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(currentLanguage.pick("Guardado", "Saved", "Guardado"), style = styles.footnote, color = p.textSecondary)
                    }
                }
            }
        }
    }
}

/** A section of the lesson: a quiet eyebrow, a heading, then its content. */
@Composable
private fun LessonSection(eyebrow: String, title: String, content: @Composable () -> Unit) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter + 4.dp).padding(top = 36.dp, bottom = 12.dp)) {
        Text(eyebrow, style = styles.eyebrow, color = p.textSecondary)
        Spacer(Modifier.height(2.dp))
        Text(title, style = styles.title3, color = p.textPrimary, modifier = Modifier.semantics { heading() })
        Spacer(Modifier.height(14.dp))
        content()
    }
}

/** One of the four movements: its number, its Latin name, its question, its guidance. */
@Composable
private fun LectioStep(number: Int, name: String, question: String, content: String) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Row(Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 16.dp)) {
        Box(
            Modifier.size(30.dp).lumenGlass(CircleShape, strong = true, lifted = false),
            contentAlignment = Alignment.Center
        ) { Text("$number", style = styles.subhead.copy(fontWeight = FontWeight.Medium), color = p.textPrimary) }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(name, style = styles.headline, color = p.textPrimary)
            Spacer(Modifier.height(4.dp))
            Text(question, style = styles.callout.copy(fontStyle = FontStyle.Italic), color = p.textPrimary)
            Spacer(Modifier.height(6.dp))
            Text(content, style = styles.subhead, color = p.textSecondary)
        }
    }
}

/** The reader's own page: a glass field of several lines. */
@Composable
private fun NotesField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val shape = RoundedCornerShape(20.dp)
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = styles.body.copy(color = p.textPrimary),
        minLines = 4,
        cursorBrush = SolidColor(p.textPrimary),
        decorationBox = { inner ->
            Box(
                Modifier.fillMaxWidth().lumenGlass(shape, lifted = false).padding(horizontal = 18.dp, vertical = 14.dp)
            ) {
                if (value.isEmpty()) Text(placeholder, style = styles.body, color = p.textSecondary)
                inner()
            }
        }
    )
}
