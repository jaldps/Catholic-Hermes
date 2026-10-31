package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Quiz
import com.example.ui.components.MenuPickerRow
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
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
import com.example.ui.components.SacredHero
import com.example.ui.components.SacredSubject
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.JournalEntryEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.models.GregorianChantItem
import com.example.data.models.TriviaQuestion
import com.example.data.models.ViaCrucisStation
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppMetricTile
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/**
 * A journal category as it is stored and as it is read.
 *
 * [key] is what Room holds and has always held — Spanish — so entries written
 * before the app spoke three languages keep matching their category. Only the
 * label is translated.
 */
private data class JournalCategory(
    val key: String,
    val es: String,
    val en: String,
    val pt: String
)

private val JournalCategories = listOf(
    JournalCategory("Intención", "Intención", "Intention", "Intenção"),
    JournalCategory("Agradecimiento", "Agradecimiento", "Thanksgiving", "Agradecimento"),
    JournalCategory("Examen", "Examen", "Examen", "Exame"),
    JournalCategory("Gracia", "Gracia", "Grace", "Graça")
)

/** Falls back to the stored value, so a category from an older build still reads. */
private fun journalCategoryLabel(key: String, language: AppLanguage): String =
    JournalCategories.firstOrNull { it.key == key }
        ?.let { language.pick(it.es, it.en, it.pt) }
        ?: key

/**
 * No kit insets its own segmented control, so the gutter is the caller's to
 * give. Vatican is the exception: its strip is a ruled band whose hairline runs
 * the full width of the leaf, and inset it stops meeting the edges.
 */
@Composable
private fun tabStripInset(): Dp {
    val design = LocalAppDesign.current
    return if (design.language == DesignLanguage.VATICAN) 0.dp else design.spacing.gutter
}

/**
 * Holds long-form text to the identity's reading measure and centres it, so a
 * wide screen reads as a column of a book instead of a wall of type. The two
 * gutters are added back because the block inside carries them itself.
 */
@Composable
private fun ReadingMeasure(content: @Composable ColumnScope.() -> Unit) {
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
 * The journal's own input.
 *
 * The kit's search field is single-line and wears a magnifier, and neither
 * suits a title line or a page of writing, so this is cut from the identity's
 * own field shape, rule weight and type instead.
 */
@Composable
private fun JournalField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    minLines: Int = 1,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    Column(modifier.fillMaxWidth()) {
        Text(text = label, style = styles.sectionEyebrow, color = palette.textSecondary)
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = styles.rowTitle.copy(color = palette.textPrimary),
            minLines = minLines,
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
}

/**
 * Journal, trivia, the Via Crucis and the chant player.
 *
 * [initialTab] lets Home open the quiz directly rather than landing on the
 * journal first.
 */
@Composable
fun SpiritualLifeScreen(
    repository: CatholicDataRepository,
    dao: SanctaFidesDao,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    initialTab: Int = 0,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val coroutineScope = rememberCoroutineScope()
    val journalEntries by dao.getAllJournalEntries().collectAsState(initial = emptyList())

    var selectedTopTab by remember(initialTab) { mutableIntStateOf(initialTab) }

    var showAddJournalDialog by remember { mutableStateOf(false) }
    var newJournalTitle by remember { mutableStateOf("") }
    var newJournalCategory by remember { mutableStateOf(JournalCategories.first().key) }
    var newJournalContent by remember { mutableStateOf("") }

    val triviaList = repository.triviaQuestions
    var triviaIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var triviaScore by remember { mutableIntStateOf(0) }
    var hasAnswered by remember { mutableStateOf(false) }

    var currentStationIndex by remember { mutableIntStateOf(0) }

    var selectedChant by remember {
        mutableStateOf<GregorianChantItem?>(repository.gregorianChants.firstOrNull())
    }
    val isPlayingAudio by ttsManager.isPlaying.collectAsState()

    // One title for the screen, then the control that picks the view, both
    // the first rows of whichever list is showing so they scroll away.
    val tabsInset = tabStripInset()
    val header: LazyListScope.() -> Unit = {
        item(key = "spiritual_life_title") {
            AppScreenTitle(
                title = currentLanguage.pick("Vida espiritual", "Spiritual Life", "Vida espiritual"),
                subtitle = null
            )
        }
        item(key = "hero_art") { SacredHero(SacredSubject.DOVE) }
        item(key = "spiritual_life_tabs") {
            // Three destinations: the quiz and the chant share "Learn", chosen
            // by one menu row beneath, so the control never carries four.
            AppSegmentedTabs(
                options = listOf(
                    currentLanguage.pick("Diario", "Journal", "Diário"),
                    currentLanguage.pick("Vía Crucis", "Stations", "Via-Sacra"),
                    currentLanguage.pick("Aprender", "Learn", "Aprender")
                ),
                selectedIndex = when (selectedTopTab) {
                    2 -> 1
                    1, 3 -> 2
                    else -> 0
                },
                onSelect = {
                    selectedTopTab = when (it) {
                        1 -> 2
                        2 -> if (selectedTopTab == 3) 3 else 1
                        else -> 0
                    }
                },
                modifier = Modifier
                    .padding(horizontal = tabsInset)
                    .padding(bottom = design.spacing.cardGap / 2)
            )
        }
        if (selectedTopTab == 1 || selectedTopTab == 3) {
            item(key = "spiritual_life_learn_picker") {
                AppListGroup {
                    MenuPickerRow(
                        title = currentLanguage.pick("Actividad", "Activity", "Atividade"),
                        leadingIcon = if (selectedTopTab == 3) Icons.Default.MusicNote else Icons.Default.Quiz,
                        options = listOf(
                            1 to currentLanguage.pick("Preguntas de fe", "Faith quiz", "Quiz da fé"),
                            3 to currentLanguage.pick("Canto gregoriano", "Gregorian chant", "Canto gregoriano")
                        ),
                        selected = selectedTopTab,
                        onSelect = { selectedTopTab = it },
                        testTag = "spiritual_life_learn_picker"
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("spiritual_life_screen")
    ) {
        when (selectedTopTab) {
            0 -> JournalTab(
                header = header,
                entries = journalEntries,
                currentLanguage = currentLanguage,
                onToggleAnswered = { entry ->
                    coroutineScope.launch {
                        dao.updateJournalEntry(entry.copy(isAnswered = !entry.isAnswered))
                    }
                },
                onDelete = { entry ->
                    coroutineScope.launch { dao.deleteJournalEntry(entry) }
                },
                onAdd = { prompt ->
                    if (prompt != null) newJournalTitle = prompt
                    showAddJournalDialog = true
                }
            )

            1 -> TriviaTab(
                header = header,
                questions = triviaList,
                index = triviaIndex,
                score = triviaScore,
                selectedOptionIndex = selectedOptionIndex,
                hasAnswered = hasAnswered,
                currentLanguage = currentLanguage,
                onAnswer = { optionIndex ->
                    selectedOptionIndex = optionIndex
                    hasAnswered = true
                    val correct = triviaList[triviaIndex.coerceIn(triviaList.indices)].correctIndex
                    if (optionIndex == correct) {
                        triviaScore += 10
                    }
                },
                onNext = {
                    selectedOptionIndex = null
                    hasAnswered = false
                    if (triviaList.isNotEmpty()) {
                        triviaIndex = (triviaIndex + 1) % triviaList.size
                    }
                }
            )

            2 -> {
                val stations = repository.viaCrucisStations
                ViaCrucisTab(
                    header = header,
                    stations = stations,
                    currentIndex = currentStationIndex,
                    isPlayingAudio = isPlayingAudio,
                    currentLanguage = currentLanguage,
                    onPrevious = { if (currentStationIndex > 0) currentStationIndex-- },
                    onNext = {
                        if (currentStationIndex < stations.size - 1) currentStationIndex++
                    },
                    onNarrate = { station ->
                        ttsManager.speakDialogue(
                            station.meditation.get(currentLanguage),
                            station.prayer.get(currentLanguage)
                        )
                    }
                )
            }

            3 -> ChantTab(
                header = header,
                chants = repository.gregorianChants,
                selectedChantId = selectedChant?.id,
                isPlayingAudio = isPlayingAudio,
                currentLanguage = currentLanguage,
                onSelect = { selectedChant = it },
                onTogglePlayback = { chant ->
                    selectedChant = chant
                    if (isPlayingAudio) {
                        ttsManager.stop()
                    } else {
                        ttsManager.playSacredChime()
                        ttsManager.speakSingle(chant.latinLyrics)
                    }
                }
            )
        }
    }

    if (showAddJournalDialog) {
        AddJournalEntryDialog(
            currentLanguage = currentLanguage,
            title = newJournalTitle,
            onTitleChange = { newJournalTitle = it },
            category = newJournalCategory,
            onCategoryChange = { newJournalCategory = it },
            content = newJournalContent,
            onContentChange = { newJournalContent = it },
            onSave = {
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
            },
            onDismiss = { showAddJournalDialog = false }
        )
    }
}

// ------------------------------------------------------------------- journal

@Composable
private fun JournalTab(
    header: LazyListScope.() -> Unit,
    entries: List<JournalEntryEntity>,
    currentLanguage: AppLanguage,
    onToggleAnswered: (JournalEntryEntity) -> Unit,
    onDelete: (JournalEntryEntity) -> Unit,
    onAdd: (prompt: String?) -> Unit
) {
    val design = LocalAppDesign.current

    val centred = design.decor.titleAlignment == Alignment.CenterHorizontally

    Box(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxSize()) {
            header()

            if (entries.isEmpty()) {
                item {
                    AppEmptyState(
                        title = currentLanguage.pick(
                            "Tu diario está vacío",
                            "Your journal is empty",
                            "Seu diário está vazio"
                        ),
                        body = currentLanguage.pick(
                            "Anota intenciones, agradecimientos y gracias recibidas.",
                            "Keep your intentions, thanksgivings and the graces you receive.",
                            "Anote intenções, agradecimentos e graças recebidas."
                        ),
                        icon = Icons.Default.EditNote
                    )
                    // The empty page's one action sits with its explanation,
                    // not floating over a void at the foot of the screen.
                    // Sized to its label and centred: only a pinned bottom
                    // action spans the width.
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        AppPrimaryButton(
                            label = currentLanguage.pick("Escribir la primera entrada", "Write your first entry", "Escrever a primeira entrada"),
                            onClick = { onAdd(null) },
                            leadingIcon = Icons.Default.Add,
                            modifier = Modifier
                                .padding(horizontal = design.spacing.gutter)
                                .testTag("add_first_journal_entry")
                        )
                    }
                }
                // A few ways in, for the reader who does not know where to start.
                item {
                    AppSectionHeader(
                        title = currentLanguage.pick("Para empezar", "Ways to begin", "Para começar")
                    )
                    AppListGroup {
                        listOf(
                            currentLanguage.pick("Una gracia recibida hoy", "A grace received today", "Uma graça recebida hoje"),
                            currentLanguage.pick("Una intención para rezar", "An intention to pray for", "Uma intenção para rezar"),
                            currentLanguage.pick("Algo por lo que dar gracias", "Something to give thanks for", "Algo por que agradecer")
                        ).zip(
                            listOf(Icons.Default.AutoAwesome, Icons.Default.VolunteerActivism, Icons.Default.Favorite)
                        ).forEach { (prompt, glyph) ->
                            AppListRow(
                                title = prompt,
                                leadingIcon = glyph,
                                onClick = { onAdd(prompt) }
                            )
                        }
                    }
                }
            } else {
                item {
                    AppSectionHeader(
                        title = currentLanguage.pick("Entradas", "Entries", "Entradas"),
                        subtitle = currentLanguage.pick(
                            "${entries.size} escritas · ${entries.count { it.isAnswered }} concedidas",
                            "${entries.size} written · ${entries.count { it.isAnswered }} granted",
                            "${entries.size} escritas · ${entries.count { it.isAnswered }} concedidas"
                        )
                    )
                }
                itemsIndexed(entries, key = { _, entry -> entry.id }) { index, entry ->
                    JournalEntry(
                        entry = entry,
                        currentLanguage = currentLanguage,
                        onToggleAnswered = { onToggleAnswered(entry) },
                        onDelete = { onDelete(entry) }
                    )
                    if (index < entries.lastIndex) AppOrnament()
                }
            }

            // Room under the last entry for the floating action to clear it.
            item {
                Spacer(
                    Modifier.height(
                        design.spacing.contentBottomGap + design.spacing.rowMinHeight + design.spacing.gutter
                    )
                )
            }
        }

        // The add action floats over the foot of the page, with no rule above
        // it: the bottom-end extended action in Material and Apple, centred
        // under a centred masthead in Vatican.
        if (entries.isNotEmpty()) AppPrimaryButton(
            label = currentLanguage.pick("Nueva entrada", "New entry", "Nova entrada"),
            onClick = { onAdd(null) },
            leadingIcon = Icons.Default.Add,
            modifier = Modifier
                .align(if (centred) Alignment.BottomCenter else Alignment.BottomEnd)
                .padding(design.spacing.gutter)
                .testTag("add_journal_entry_fab")
        )
    }
}

@Composable
private fun JournalEntry(
    entry: JournalEntryEntity,
    currentLanguage: AppLanguage,
    onToggleAnswered: () -> Unit,
    onDelete: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    AppListGroup {
        AppListRow(
            title = entry.title,
            subtitle = if (entry.isAnswered) {
                currentLanguage.pick(
                    "✓ Gracia concedida",
                    "✓ Grace granted",
                    "✓ Graça concedida"
                )
            } else {
                journalCategoryLabel(entry.category, currentLanguage)
            },
            selected = entry.isAnswered,
            showsNavigationAffordance = false
        )
    }

    // The entry's own words are the most valuable text in the app, so they are
    // set in the reading face at the reading measure rather than squeezed into
    // a row subtitle.
    if (entry.content.isNotBlank()) {
        ReadingMeasure {
            Text(
                text = entry.content,
                style = styles.quote,
                color = palette.textPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = design.spacing.cardGap)
            )
        }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(top = design.spacing.cardGap),
        horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppSecondaryButton(
            label = if (entry.isAnswered) {
                currentLanguage.pick(
                    "Desmarcar concedida",
                    "Unmark as granted",
                    "Desmarcar concedida"
                )
            } else {
                currentLanguage.pick(
                    "Marcar como Gracia Concedida ✓",
                    "Mark as grace granted ✓",
                    "Marcar como graça concedida ✓"
                )
            },
            onClick = onToggleAnswered,
            modifier = Modifier.weight(1f)
        )
        Box(
            Modifier
                .size(48.dp)
                .clip(design.shapes.buttonSmall)
                .clickable(onClick = onDelete),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = currentLanguage.pick(
                    "Eliminar entrada",
                    "Delete entry",
                    "Excluir entrada"
                ),
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AddJournalEntryDialog(
    currentLanguage: AppLanguage,
    title: String,
    onTitleChange: (String) -> Unit,
    category: String,
    onCategoryChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
    onSave: () -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    AlertDialog(
        onDismissRequest = onDismiss,
        shape = design.shapes.dialog,
        containerColor = palette.groupedSurface,
        title = {
            Text(
                text = currentLanguage.pick(
                    "Nueva Intención / Reflexión",
                    "New Prayer / Intention",
                    "Nova Intenção / Oração"
                ),
                style = styles.sectionTitle,
                color = palette.textPrimary
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap)) {
                JournalField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = currentLanguage.pick("Título", "Title", "Título"),
                    placeholder = currentLanguage.pick(
                        "Título de la intención o gracia",
                        "Title of the intention or grace",
                        "Título da intenção ou graça"
                    )
                )

                Column(verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)) {
                    Text(
                        text = currentLanguage.pick("Categoría", "Category", "Categoria"),
                        style = styles.sectionEyebrow,
                        color = palette.textSecondary
                    )
                    // Four categories do not fit a dialog's measure in any of
                    // the three languages, so the run wraps instead of being
                    // clipped at the edge.
                    FlowRow(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        JournalCategories.forEach { option ->
                            AppChip(
                                label = currentLanguage.pick(option.es, option.en, option.pt),
                                selected = category == option.key,
                                onClick = { onCategoryChange(option.key) }
                            )
                        }
                    }
                }

                JournalField(
                    value = content,
                    onValueChange = onContentChange,
                    label = currentLanguage.pick("Tu escrito", "Your writing", "Seu texto"),
                    placeholder = currentLanguage.pick(
                        "Escribe tus palabras al Señor...",
                        "Write your words to the Lord...",
                        "Escreva suas palavras ao Senhor..."
                    ),
                    minLines = 5
                )
            }
        },
        confirmButton = {
            AppPrimaryButton(
                label = currentLanguage.pick(
                    "Guardar en el Diario",
                    "Save to journal",
                    "Salvar no Diário"
                ),
                onClick = onSave,
                enabled = title.isNotBlank()
            )
        },
        dismissButton = {
            AppSecondaryButton(
                label = currentLanguage.pick("Cancelar", "Cancel", "Cancelar"),
                onClick = onDismiss
            )
        }
    )
}

// -------------------------------------------------------------------- trivia

@Composable
private fun TriviaTab(
    header: LazyListScope.() -> Unit,
    questions: List<TriviaQuestion>,
    index: Int,
    score: Int,
    selectedOptionIndex: Int?,
    hasAnswered: Boolean,
    currentLanguage: AppLanguage,
    onAnswer: (Int) -> Unit,
    onNext: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    if (questions.isEmpty()) {
        LazyColumn(Modifier.fillMaxSize()) {
            header()
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Sin preguntas", "No questions", "Sem perguntas"),
                    body = currentLanguage.pick(
                        "El cuestionario no tiene preguntas disponibles.",
                        "The quiz has no questions available.",
                        "O questionário não tem perguntas disponíveis."
                    ),
                    icon = Icons.Default.Lightbulb
                )
            }
        }
        return
    }

    val question = questions[index.coerceIn(questions.indices)]
    val answeredCorrectly = selectedOptionIndex == question.correctIndex

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = design.spacing.sectionGap / 2),
                horizontalArrangement = Arrangement.spacedBy(design.spacing.gutter)
            ) {
                AppMetricTile(
                    value = "${index + 1}/${questions.size}",
                    caption = currentLanguage.pick("Pregunta", "Question", "Pergunta"),
                    modifier = Modifier.weight(1f)
                )
                AppMetricTile(
                    value = score.toString(),
                    caption = currentLanguage.pick("Puntos", "Points", "Pontos"),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item { AppSectionHeader(title = question.category.get(currentLanguage)) }

        item {
            ReadingMeasure {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        text = question.question.get(currentLanguage),
                        style = styles.quote,
                        color = palette.textPrimary
                    )
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.cardGap)) }

        item {
            AppListGroup {
                question.options.forEachIndexed { optionIndex, option ->
                    val chosen = selectedOptionIndex == optionIndex
                    val isCorrect = optionIndex == question.correctIndex
                    AppListRow(
                        title = option.get(currentLanguage),
                        // Once answered the kit marks the right answer as the
                        // selected row, so the mark only has to say which of
                        // the two it is.
                        trailingText = when {
                            !hasAnswered -> null
                            isCorrect -> "✓"
                            chosen -> "✗"
                            else -> null
                        },
                        showsNavigationAffordance = false,
                        selected = if (hasAnswered) isCorrect else chosen,
                        onClick = if (hasAnswered) null else ({ onAnswer(optionIndex) })
                    )
                }
            }
        }

        if (hasAnswered) {
            item {
                ReadingMeasure {
                    AppCard(
                        modifier = Modifier
                            .padding(horizontal = design.spacing.gutter)
                            .padding(top = design.spacing.sectionGap / 2)
                    ) {
                        Text(
                            text = if (answeredCorrectly) {
                                currentLanguage.pick("¡Correcto! ✦", "Correct! ✦", "Correto! ✦")
                            } else {
                                currentLanguage.pick(
                                    "Doctrina Católica:",
                                    "Catholic doctrine:",
                                    "Doutrina Católica:"
                                )
                            },
                            style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                            color = if (answeredCorrectly) palette.gold else palette.accent
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = question.doctrinalExplanation.get(currentLanguage),
                            style = styles.quote,
                            color = palette.textSecondary
                        )
                    }
                }
            }

            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .padding(top = design.spacing.cardGap)
                ) {
                    AppPrimaryButton(
                        label = currentLanguage.pick(
                            "Siguiente Pregunta →",
                            "Next question →",
                            "Próxima pergunta →"
                        ),
                        onClick = onNext,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("next_trivia_question_button")
                    )
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// --------------------------------------------------------------- via crucis

/**
 * Fourteen stations as a run of marks. No kit component counts a devotion, so
 * it is drawn — but from the identity's own badge shape, so Cupertino and
 * Material get discs and Vatican gets the squared marks of a printed leaf.
 */
@Composable
private fun StationProgress(
    count: Int,
    currentIndex: Int,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { index ->
            val isCurrent = index == currentIndex
            Box(
                Modifier
                    .size(if (isCurrent) 9.dp else 5.dp)
                    .clip(design.shapes.badge)
                    .background(
                        if (isCurrent) design.palette.accent else design.palette.hairline
                    )
            )
        }
    }
}

@Composable
private fun ViaCrucisTab(
    header: LazyListScope.() -> Unit,
    stations: List<ViaCrucisStation>,
    currentIndex: Int,
    isPlayingAudio: Boolean,
    currentLanguage: AppLanguage,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onNarrate: (ViaCrucisStation) -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val centred = design.decor.titleAlignment == Alignment.CenterHorizontally

    if (stations.isEmpty()) {
        LazyColumn(Modifier.fillMaxSize()) {
            header()
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Sin estaciones", "No stations", "Sem estações"),
                    body = currentLanguage.pick(
                        "El Vía Crucis no está disponible.",
                        "The Stations of the Cross are not available.",
                        "A Via-Sacra não está disponível."
                    )
                )
            }
        }
        return
    }

    val index = currentIndex.coerceIn(stations.indices)
    val station = stations[index]

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Estación ${station.stationNumber} de ${stations.size}",
                    "Station ${station.stationNumber} of ${stations.size}",
                    "Estação ${station.stationNumber} de ${stations.size}"
                )
            )
        }

        item {
            StationProgress(
                count = stations.size,
                currentIndex = index,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
            )
        }

        item {
            ReadingMeasure {
                AppCard(
                    modifier = Modifier
                        .padding(horizontal = design.spacing.gutter)
                        .padding(top = design.spacing.sectionGap / 2)
                ) {
                    Text(
                        text = station.title.get(currentLanguage),
                        style = styles.sectionTitle,
                        color = palette.textPrimary,
                        textAlign = if (centred) TextAlign.Center else TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                    // Lesson, meditation and prayer are the three movements of
                    // a station, so each is ruled off from the next.
                    Text(
                        text = station.scripture.get(currentLanguage),
                        style = styles.scripture,
                        color = palette.textSecondary
                    )
                    Spacer(Modifier.height(design.spacing.cardPadding))
                    AppDivider()
                    Spacer(Modifier.height(design.spacing.cardPadding))
                    Text(
                        text = station.meditation.get(currentLanguage),
                        style = styles.quote,
                        color = palette.textPrimary
                    )
                    Spacer(Modifier.height(design.spacing.cardPadding))
                    AppDivider()
                    Spacer(Modifier.height(design.spacing.cardPadding))
                    Text(
                        text = station.prayer.get(currentLanguage),
                        style = styles.quote,
                        color = palette.accent
                    )
                }
            }
        }

        item {
            // Three labelled buttons abreast left no room for their words: at
            // 360dp, in tracked Roman capitals and in Portuguese, each label
            // broke to one character per line. Previous and next share a row
            // and narration keeps a row of its own.
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = design.spacing.sectionGap / 2),
                verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
                ) {
                    AppSecondaryButton(
                        label = currentLanguage.pick("← Anterior", "← Previous", "← Anterior"),
                        onClick = onPrevious,
                        enabled = index > 0,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("via_crucis_previous_button")
                    )
                    AppSecondaryButton(
                        label = currentLanguage.pick("Siguiente →", "Next →", "Seguinte →"),
                        onClick = onNext,
                        enabled = index < stations.size - 1,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("via_crucis_next_button")
                    )
                }
                AppPrimaryButton(
                    label = currentLanguage.pick(
                        "Rezar estación",
                        "Pray this station",
                        "Rezar estação"
                    ),
                    onClick = { onNarrate(station) },
                    leadingIcon = if (isPlayingAudio) Icons.Default.Stop else Icons.Default.PlayArrow,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("via_crucis_narrate_button")
                )
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// --------------------------------------------------------------------- chant

@Composable
private fun ChantTab(
    header: LazyListScope.() -> Unit,
    chants: List<GregorianChantItem>,
    selectedChantId: String?,
    isPlayingAudio: Boolean,
    currentLanguage: AppLanguage,
    onSelect: (GregorianChantItem) -> Unit,
    onTogglePlayback: (GregorianChantItem) -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Canto gregoriano",
                    "Gregorian chant",
                    "Canto gregoriano"
                ),
                subtitle = currentLanguage.pick(
                    "La música sacra milenaria de la Iglesia.",
                    "The Church's thousand-year sacred music.",
                    "A música sacra milenar da Igreja."
                )
            )
        }

        if (chants.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Sin cantos", "No chants", "Sem cantos"),
                    body = currentLanguage.pick(
                        "El repertorio no está disponible.",
                        "The repertoire is not available.",
                        "O repertório não está disponível."
                    ),
                    icon = Icons.Default.MusicNote
                )
            }
        } else {
            itemsIndexed(chants, key = { _, chant -> chant.id }) { index, chant ->
                val isSelected = chant.id == selectedChantId
                val isPlayingThis = isPlayingAudio && isSelected

                ReadingMeasure {
                    AppCard(
                        modifier = Modifier.padding(horizontal = design.spacing.gutter),
                        onClick = { onSelect(chant) }
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.MusicNote,
                                contentDescription = null,
                                tint = if (isSelected) palette.accent else palette.textTertiary,
                                modifier = Modifier.size(design.decor.rowIconSize)
                            )
                            Spacer(Modifier.width(design.spacing.iconLabelGap))
                            Text(
                                text = chant.title,
                                style = styles.sectionTitle,
                                color = if (isSelected) palette.accent else palette.textPrimary,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = chant.liturgicalSeason.get(currentLanguage),
                            style = styles.sectionEyebrow,
                            color = palette.textSecondary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap / 2))
                        Text(
                            text = chant.description.get(currentLanguage),
                            style = styles.rowSubtitle,
                            color = palette.textSecondary
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))
                        AppDivider()
                        Spacer(Modifier.height(design.spacing.cardPadding))

                        Text(
                            text = currentLanguage.pick(
                                "Letra en Latín:",
                                "Latin text:",
                                "Letra em latim:"
                            ),
                            style = styles.sectionEyebrow,
                            color = palette.textSecondary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap / 2))
                        Text(
                            text = chant.latinLyrics,
                            style = if (design.language == DesignLanguage.VATICAN) {
                                styles.scripture.copy(fontStyle = FontStyle.Italic)
                            } else styles.scripture,
                            color = palette.textPrimary
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))
                        Text(
                            text = currentLanguage.pick("Traducción:", "Translation:", "Tradução:"),
                            style = styles.sectionEyebrow,
                            color = palette.textSecondary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap / 2))
                        Text(
                            text = chant.translatedLyrics.get(currentLanguage),
                            style = styles.quote,
                            color = palette.textSecondary
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))
                        AppSecondaryButton(
                            label = if (isPlayingThis) {
                                currentLanguage.pick("Detener", "Stop", "Parar")
                            } else {
                                currentLanguage.pick("Escuchar", "Listen", "Ouvir")
                            },
                            onClick = { onTogglePlayback(chant) },
                            leadingIcon = if (isPlayingThis) {
                                Icons.Default.Stop
                            } else {
                                Icons.Default.PlayArrow
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                if (index < chants.lastIndex) AppOrnament()
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}
