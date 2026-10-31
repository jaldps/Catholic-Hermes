package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDao
import com.example.data.local.ScriptureStudyProgressEntity
import com.example.data.models.AppLanguage
import com.example.data.models.ScriptureCourseTrack
import com.example.data.models.ScriptureLesson
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.MenuPickerRow
import com.example.ui.design.AppBanner
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** Vatican sets its labels as inscriptional capitals; the other two do not. */
@Composable
private fun caps(text: String): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) text.uppercase() else text

/**
 * Holds long-form text to the identity's reading measure and centres it, so a
 * wide screen reads as a column of a book instead of a wall of type. The two
 * gutters are added back because the block inside carries them itself.
 */
@Composable
private fun ReadingColumn(content: @Composable ColumnScope.() -> Unit) {
    val design = LocalAppDesign.current
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(
            Modifier
                .widthIn(max = design.spacing.readingMaxWidth + design.spacing.gutter * 2)
                .fillMaxWidth(),
            content = content
        )
    }
}

/**
 * The label the track strip can actually hold: a segment is about one word
 * wide at 360dp, and the course data carries no short title, so the three
 * bundled itineraries get one here and anything added later falls back to its
 * own title. The full title opens the overview immediately below the strip.
 */
private fun trackStripLabel(
    track: ScriptureCourseTrack,
    index: Int,
    language: AppLanguage
): String {
    val short = when (track.id) {
        "track_sacraments" -> language.pick("Sacramentos", "Sacraments", "Sacramentos")
        "track_parables" -> language.pick("Parábolas", "Parables", "Parábolas")
        "track_apostolic" -> language.pick("Cartas", "Letters", "Cartas")
        else -> track.title.get(language)
    }
    return "${index + 1}. $short"
}

/**
 * The guided scripture course, shown as the study tab of [BibleScreen]: a strip
 * of itineraries, the lessons of the selected one, and one lesson read as a
 * lectio divina. Completion and the reader's own notes live in Room.
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
    var selectedTrackIndex by remember { mutableIntStateOf(0) }

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
        if (tracks.isEmpty()) {
            LazyColumn(Modifier.fillMaxSize()) {
                header()
                item {
                    AppEmptyState(
                        title = currentLanguage.pick(
                            "Sin itinerarios",
                            "No study tracks",
                            "Sem itinerários"
                        ),
                        body = currentLanguage.pick(
                            "El curso bíblico no está disponible en esta edición.",
                            "The scripture course is not available in this edition.",
                            "O curso bíblico não está disponível nesta edição."
                        ),
                        icon = Icons.AutoMirrored.Filled.MenuBook
                    )
                }
            }
        } else {
            val currentTrack = tracks.getOrElse(selectedTrackIndex) { tracks.first() }
            val stripLabels = remember(tracks, currentLanguage) {
                tracks.mapIndexed { index, track ->
                    trackStripLabel(track, index, currentLanguage)
                }
            }

            val lesson = activeLesson
            if (lesson == null) {
                TrackOverviewView(
                    header = header,
                    stripLabels = stripLabels,
                    tracks = tracks,
                    track = currentTrack,
                    selectedTrackIndex = selectedTrackIndex,
                    completedLessonIds = completedLessonIds,
                    currentLanguage = currentLanguage,
                    onSelectTrack = { index ->
                        selectedTrackIndex = index
                        activeLesson = null
                    },
                    onSelectLesson = { selected -> activeLesson = selected }
                )
            } else {
                val isLessonDone = completedLessonIds.contains(lesson.id)
                val currentEntity = allProgress.firstOrNull { it.lessonId == lesson.id }

                BackHandler { activeLesson = null }

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
                                    completedTimestamp =
                                        if (!isLessonDone) System.currentTimeMillis() else 0L,
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
}

// ------------------------------------------------------------------- overview

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TrackOverviewView(
    header: LazyListScope.() -> Unit,
    stripLabels: List<String>,
    tracks: List<ScriptureCourseTrack>,
    track: ScriptureCourseTrack,
    selectedTrackIndex: Int,
    completedLessonIds: Set<String>,
    currentLanguage: AppLanguage,
    onSelectTrack: (Int) -> Unit,
    onSelectLesson: (ScriptureLesson) -> Unit
) {
    val design = LocalAppDesign.current
    val scheme = MaterialTheme.colorScheme

    val total = track.lessons.size
    val done = track.lessons.count { completedLessonIds.contains(it.id) }
    val fraction = if (total > 0) done.toFloat() / total.toFloat() else 0f

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        // The itinerary is one row with a menu under the screen's own
        // segmented control, not a second row of chips.
        item {
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            AppListGroup {
                MenuPickerRow(
                    title = currentLanguage.pick("Itinerario", "Course", "Itinerário"),
                    options = stripLabels.mapIndexed { i, label -> i to label },
                    selected = selectedTrackIndex,
                    onSelect = onSelectTrack,
                    testTag = "study_track_picker"
                )
            }
        }

        item {
            AppSectionHeader(
                title = track.title.get(currentLanguage),
                subtitle = track.description.get(currentLanguage)
            )
        }

        item {
            AppBanner(
                title = currentLanguage.pick(
                    "Fundamento magisterial",
                    "Magisterial grounding",
                    "Fundamento magisterial"
                ),
                body = currentLanguage.pick(
                    "Alineado con el Catecismo y Dei Verbum del Vaticano",
                    "Aligned with the Catechism and the Vatican's Dei Verbum",
                    "Alinhado com o Catecismo e a Dei Verbum do Vaticano"
                )
            )
        }

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Lecciones del Curso",
                    "Course Lessons",
                    "Lições do Curso"
                ),
                subtitle = currentLanguage.pick(
                    "$done de $total lecciones · ${(fraction * 100).toInt()}% del curso",
                    "$done of $total lessons · ${(fraction * 100).toInt()}% of the course",
                    "$done de $total lições · ${(fraction * 100).toInt()}% do curso"
                )
            )
            // No kit component carries a progress bar, so the course rule is
            // the Material indicator dressed in the identity's own colours.
            LinearProgressIndicator(
                progress = { fraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .padding(bottom = design.spacing.cardGap),
                color = scheme.primary,
                trackColor = design.palette.selectionTint
            )
        }

        if (track.lessons.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick(
                        "Sin lecciones",
                        "No lessons",
                        "Sem lições"
                    ),
                    body = currentLanguage.pick(
                        "Este itinerario aún no tiene lecciones en la edición incluida.",
                        "This track has no lessons in the bundled edition yet.",
                        "Este itinerário ainda não tem lições na edição incluída."
                    ),
                    icon = Icons.AutoMirrored.Filled.MenuBook
                )
            }
        } else {
            item {
                AppListGroup {
                    track.lessons.forEach { lesson ->
                        val isDone = completedLessonIds.contains(lesson.id)
                        AppListRow(
                            title = "${lesson.lessonNumber}. ${lesson.title.get(currentLanguage)}",
                            subtitle = lesson.biblicalReference,
                            trailingText = if (isDone) {
                                currentLanguage.pick("Hecha", "Done", "Feita")
                            } else {
                                null
                            },
                            onClick = { onSelectLesson(lesson) },
                            modifier = Modifier.testTag("lesson_row_${lesson.id}")
                        )
                    }
                }
            }
        }

        // The strip above names the itineraries but cannot show how far each
        // one has been read; this index can, and switches between them.
        if (tracks.size > 1) {
            item { AppOrnament() }
            item {
                AppSectionHeader(
                    title = currentLanguage.pick(
                        "Itinerarios del curso",
                        "Course itineraries",
                        "Itinerários do curso"
                    )
                )
            }
            item {
                AppListGroup {
                    tracks.forEachIndexed { index, candidate ->
                        val read = candidate.lessons.count { completedLessonIds.contains(it.id) }
                        AppListRow(
                            title = candidate.title.get(currentLanguage),
                            trailingText = currentLanguage.pick(
                                "$read de ${candidate.lessons.size}",
                                "$read of ${candidate.lessons.size}",
                                "$read de ${candidate.lessons.size}"
                            ),
                            selected = index == selectedTrackIndex,
                            onClick = { onSelectTrack(index) },
                            modifier = Modifier.testTag("track_row_${candidate.id}")
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// --------------------------------------------------------------------- lesson

@Composable
private fun LessonDetailView(
    lesson: ScriptureLesson,
    isCompleted: Boolean,
    existingNotes: String,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    onBack: () -> Unit,
    onToggleComplete: (notes: String) -> Unit,
    onSaveNotes: (notes: String) -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    var notesText by remember(lesson.id) { mutableStateOf(existingNotes) }

    val passage = lesson.biblicalPassageText.get(currentLanguage)

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = design.spacing.cardGap),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable(onClick = onBack)
                        .padding(vertical = design.spacing.rowPaddingVertical)
                        .testTag("back_to_course_button"),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = currentLanguage.pick(
                            "Volver al Curso",
                            "Back to the Course",
                            "Voltar ao Curso"
                        ),
                        tint = palette.accent,
                        modifier = Modifier.size(design.decor.rowIconSize)
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap / 2))
                    // One word, not "Volver al Curso": beside the status chip
                    // the row has about 140dp left, and Vatican's tracked
                    // capitals spend all of it on the longer phrase.
                    Text(
                        text = caps(currentLanguage.pick("Volver", "Back", "Voltar")),
                        style = styles.buttonLabel,
                        color = palette.accent,
                        maxLines = 1
                    )
                }
                Spacer(Modifier.width(design.spacing.cardGap))
                AppChip(
                    label = if (isCompleted) {
                        currentLanguage.pick("Completada", "Completed", "Concluída")
                    } else {
                        currentLanguage.pick("Marcar Hecha", "Mark done", "Marcar feita")
                    },
                    selected = isCompleted,
                    onClick = { onToggleComplete(notesText) },
                    modifier = Modifier.testTag("toggle_lesson_complete_button")
                )
            }
        }

        item {
            AppScreenTitle(
                title = lesson.title.get(currentLanguage),
                subtitle = currentLanguage.pick(
                    "Lección ${lesson.lessonNumber} · ${lesson.biblicalReference}",
                    "Lesson ${lesson.lessonNumber} · ${lesson.biblicalReference}",
                    "Lição ${lesson.lessonNumber} · ${lesson.biblicalReference}"
                )
            )
        }

        // The passage is not boxed: a lectio is read, so the rubric names the
        // step and the text runs at the identity's reading measure under it.
        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Paso 1: Lectura de la Escritura (Lectio)",
                    "Step 1: Reading of Scripture (Lectio)",
                    "Passo 1: Leitura da Escritura (Lectio)"
                )
            )
        }

        item {
            ReadingColumn {
                Text(
                    text = passage,
                    style = styles.scripture,
                    color = palette.textPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                AppSecondaryButton(
                    label = currentLanguage.pick("Escuchar", "Listen", "Ouvir"),
                    onClick = { ttsManager.speakSingle(passage) },
                    leadingIcon = Icons.AutoMirrored.Filled.VolumeUp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("lesson_listen_button")
                )
            }
        }

        item { AppOrnament() }

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Magisterio del Vaticano y Catecismo",
                    "Vatican Magisterium & Catechism",
                    "Magistério do Vaticano e Catecismo"
                ),
                // The citations are apparatus, so they sit under the rubric the
                // way a reference does in a printed commentary.
                subtitle = lesson.catechismRefs
                    .joinToString(" · ")
                    .ifBlank { null }
            )
        }

        item {
            ReadingColumn {
                Text(
                    text = lesson.vaticanContext.get(currentLanguage),
                    style = styles.scripture,
                    color = palette.textPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                )
            }
        }

        item { AppOrnament() }

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Guía de Lectio Divina",
                    "Guide to Lectio Divina",
                    "Guia de Lectio Divina"
                )
            )
        }

        item {
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    LectioStep(
                        badge = "1. Lectio",
                        question = currentLanguage.pick(
                            "¿Qué dice el texto sagrado?",
                            "What does the sacred text say?",
                            "O que diz o texto sagrado?"
                        ),
                        content = lesson.lectioStep.get(currentLanguage)
                    )
                    LectioRule()
                    LectioStep(
                        badge = "2. Meditatio",
                        question = currentLanguage.pick(
                            "¿Qué me dice Dios hoy a mí a través de este pasaje?",
                            "What is God saying to me today through this passage?",
                            "O que Deus me diz hoje através desta passagem?"
                        ),
                        content = lesson.meditatioStep.get(currentLanguage)
                    )
                    LectioRule()
                    LectioStep(
                        badge = "3. Oratio",
                        question = currentLanguage.pick(
                            "¿Qué le respondo a Dios en mi corazón?",
                            "What do I answer God in my heart?",
                            "O que respondo a Deus no meu coração?"
                        ),
                        content = lesson.oratioStep.get(currentLanguage)
                    )
                    LectioRule()
                    LectioStep(
                        badge = "4. Contemplatio",
                        question = currentLanguage.pick(
                            "¿Cómo transforma mi vida esta verdad hoy?",
                            "How does this truth change my life today?",
                            "Como esta verdade transforma a minha vida hoje?"
                        ),
                        content = lesson.contemplatioStep.get(currentLanguage)
                    )
                }
            }
        }

        item { AppOrnament() }

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Mis Notas y Frutos Espirituales",
                    "My Notes & Spiritual Fruits",
                    "Minhas Notas e Frutos Espirituais"
                ),
                subtitle = currentLanguage.pick(
                    "Escribe una breve resolución o lo que Dios te ha inspirado en esta lección.",
                    "Write a short resolution, or whatever God has inspired in you in this lesson.",
                    "Escreva uma breve resolução ou o que Deus lhe inspirou nesta lição."
                )
            )
        }

        item {
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    NotesField(
                        value = notesText,
                        onValueChange = { notesText = it },
                        placeholder = currentLanguage.pick(
                            "Mi resolución de hoy...",
                            "My resolution for today...",
                            "A minha resolução de hoje..."
                        ),
                        modifier = Modifier.testTag("lesson_notes_field")
                    )
                    Spacer(Modifier.height(design.spacing.cardPadding))
                    // One label, one full-width row: "Guardar y Completar
                    // Lección" cannot be defended in half a row at 360dp.
                    AppPrimaryButton(
                        label = if (isCompleted) {
                            currentLanguage.pick(
                                "Guardar Cambios",
                                "Save changes",
                                "Guardar alterações"
                            )
                        } else {
                            currentLanguage.pick(
                                "Guardar y Completar Lección",
                                "Save & complete lesson",
                                "Guardar e concluir a lição"
                            )
                        },
                        onClick = {
                            if (isCompleted) onSaveNotes(notesText) else onToggleComplete(notesText)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("save_lesson_button")
                    )
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/** One of the four movements: its Latin name, its question, its guidance. */
@Composable
private fun LectioStep(
    badge: String,
    question: String,
    content: String
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = caps(badge),
            style = styles.sectionEyebrow,
            color = design.palette.gold
        )
        Spacer(Modifier.height(design.spacing.cardGap / 3))
        Text(
            text = question,
            style = styles.rowTitle,
            color = design.palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = design.palette.textSecondary
        )
    }
}

/**
 * The rule between two movements. `AppDivider` is inset to the gutter in
 * Cupertino, which reads as a mistake inside a card's own padding, so the rule
 * is drawn to the card's measure instead.
 */
@Composable
private fun LectioRule() {
    val design = LocalAppDesign.current
    Spacer(Modifier.height(design.spacing.cardPadding))
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(design.palette.hairline)
    )
    Spacer(Modifier.height(design.spacing.cardPadding))
}

/**
 * The reader's own page.
 *
 * The kit's search field is single-line and wears a magnifier, which suits
 * neither a resolution nor a page of writing, so this is cut from the
 * identity's own field shape, rule weight and type instead.
 */
@Composable
private fun NotesField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = styles.rowTitle.copy(color = palette.textPrimary),
        minLines = 4,
        cursorBrush = SolidColor(palette.accent),
        decorationBox = { inner ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(design.shapes.field)
                    .background(
                        palette.selectionTint.copy(alpha = if (design.isDark) 1f else 0.5f)
                    )
                    .border(
                        design.depth.cardBorderWidth.coerceAtLeast(0.75.dp),
                        palette.hairline,
                        design.shapes.field
                    )
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = styles.rowTitle,
                        color = palette.textTertiary
                    )
                }
                inner()
            }
        }
    )
}
