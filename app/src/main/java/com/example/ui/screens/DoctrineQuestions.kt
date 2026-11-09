package com.example.ui.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.data.liturgy.FoundationRef
import com.example.data.liturgy.parseFoundation
import com.example.data.models.AppLanguage
import com.example.data.models.ApologeticsQuestion
import com.example.data.models.TriviaQuestion
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppChevron
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppProgressRing
import com.example.ui.design.AppSearchBar
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.groupedItem
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.text.Normalizer
import java.util.Calendar

// -----------------------------------------------------------------------------
// 101 Questions: search, question of the day, itineraries, themes, the answer
// reader with reference chips, the sources page and the end-of-itinerary quiz.
// -----------------------------------------------------------------------------

/** One guided itinerary: a contiguous question range (1-based numbers). */
internal data class LearningTrack(val index: Int, val startQuestion: Int, val endQuestion: Int) {
    val total: Int get() = endQuestion - startQuestion + 1
    fun completedCount(reachedQuestion: Int): Int = (reachedQuestion - startQuestion + 1).coerceIn(0, total)
}

internal val LEARNING_TRACKS = listOf(
    LearningTrack(0, 1, 30),
    LearningTrack(1, 41, 60),
    LearningTrack(2, 61, 100)
)

private fun trackTitle(track: LearningTrack, language: AppLanguage): String = when (track.index) {
    0 -> language.tr("Fundamentos", "Foundations", "Fundamentos")
    1 -> language.tr("Iglesia y sacramentos", "Church and sacraments", "Igreja e sacramentos")
    else -> language.tr("Vida y moral", "Life and morals", "Vida e moral")
}

/** Where tapping an itinerary resumes: the first unread question, or the start. */
internal fun trackResumeIndex(track: LearningTrack, reached: Int): Int {
    val done = track.completedCount(reached)
    return if (done in 1 until track.total) reached else track.startQuestion - 1
}

private fun trackQuizQuestions(trivia: List<TriviaQuestion>, trackIndex: Int): List<TriviaQuestion> {
    if (trivia.isEmpty()) return emptyList()
    val start = (trackIndex * 3) % trivia.size
    return (0 until 3).map { k -> trivia[(start + k) % trivia.size] }
}

private fun doctrineFold(s: String): String =
    Normalizer.normalize(s.lowercase(), Normalizer.Form.NFD).replace(Regex("\\p{M}+"), "")

private fun decodeAnswers(s: String): Map<String, Int> =
    s.split(';').filter { it.contains('=') }
        .associate { it.substringBefore('=') to (it.substringAfter('=').toIntOrNull() ?: -1) }

private fun encodeAnswers(m: Map<String, Int>): String = m.entries.joinToString(";") { "${it.key}=${it.value}" }

// -------------------------------------------------------------------- root

@Composable
internal fun QuestionsRoot(
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav,
    spec: RootSpec
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val context = LocalContext.current
    val questions = repository.catholicQuestions
    val state = rememberLazyListState()
    var query by rememberSaveable { mutableStateOf("") }
    var answersCode by rememberSaveable { mutableStateOf("") }
    val answers = decodeAnswers(answersCode)

    val store = remember { UserPreferencesStore(context) }
    val trackProgress by store.trackProgress.collectAsState(initial = emptyMap())

    val themes = remember(questions) { questionThemes(questions) }
    val dayIndex = remember(questions) {
        if (questions.isEmpty()) -1 else Calendar.getInstance().get(Calendar.DAY_OF_YEAR) % questions.size
    }

    val folded = remember(query) { doctrineFold(query.trim()) }
    val results = remember(folded, language, questions) {
        if (folded.isEmpty()) emptyList() else {
            val inQuestion = questions.indices.filter { doctrineFold(questions[it].question.get(language)).contains(folded) }
            val inAnswer = questions.indices.filter {
                it !in inQuestion && (doctrineFold(questions[it].answer.get(language)).contains(folded) ||
                    doctrineFold(questions[it].foundation.get(language)).contains(folded))
            }
            inQuestion + inAnswer
        }
    }

    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = LocalBottomPad.current)
    ) {
        item(key = "hero", contentType = "hero") { RootHeader(spec) }

        item(key = "search") {
            AppSearchBar(
                value = query,
                onValueChange = { query = it },
                placeholder = language.tr("Buscar entre las 101 preguntas", "Search the 101 questions", "Buscar nas 101 perguntas"),
                tag = "qa_search_input"
            )
        }

        if (folded.isNotEmpty()) {
            if (results.isEmpty()) {
                item(key = "no_results") {
                    Column(
                        Modifier.fillMaxWidth().padding(horizontal = 40.dp, vertical = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(language.tr("Sin resultados", "No results", "Sem resultados"), style = styles.headline, color = p.textPrimary)
                        Spacer(Modifier.height(6.dp))
                        Text(
                            language.tr(
                                "Prueba con otra palabra: «gracia», «Eucaristía», «purgatorio».",
                                "Try another word: \"grace\", \"Eucharist\", \"purgatory\".",
                                "Tente outra palavra: «graça», «Eucaristia», «purgatório»."
                            ),
                            style = styles.subhead,
                            color = p.textSecondary
                        )
                    }
                }
            } else {
                item(key = "results_header") {
                    SectionHeader(
                        language.tr("Resultados", "Results", "Resultados"),
                        trailing = "${results.size}",
                        topGap = 20.dp
                    )
                }
                items(results, key = { "r$it" }, contentType = { "row" }) { qi ->
                    val q = questions[qi]
                    val pos = results.indexOf(qi)
                    LeadRow(
                        title = q.question.get(language),
                        subtitle = q.section.get(language),
                        position = groupPosition(pos, results.size),
                        onClick = { nav.open("qa:$qi") },
                        tag = "catholic_question_${q.number}",
                        maxTitleLines = 4
                    )
                }
            }
            return@LazyColumn
        }

        // ---- question of the day
        if (dayIndex >= 0) item(key = "daily") {
            val q = questions[dayIndex]
            Spacer(Modifier.height(24.dp))
            GlassCard(onClick = { nav.open("qa:$dayIndex") }, modifier = Modifier.testTag("qa_daily")) {
                Text(
                    language.tr("Pregunta del día", "Question of the day", "Pergunta do dia") + " · " + q.section.get(language),
                    style = styles.eyebrow,
                    color = p.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                Text(q.question.get(language), style = styles.title2, color = p.textPrimary, modifier = Modifier.semantics { heading() })
                Spacer(Modifier.height(8.dp))
                Text(
                    firstSentence(splitParagraphs(q.answer.get(language)).firstOrNull().orEmpty()),
                    style = styles.callout,
                    color = p.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(16.dp))
                AppSecondaryCapsule(
                    label = language.tr("Leer respuesta", "Read the answer", "Ler resposta"),
                    onClick = { nav.open("qa:$dayIndex") }
                )
            }
        }

        // ---- continue where you left off
        val continueTarget = trackProgress.entries
            .mapNotNull { (trackIndex, reached) ->
                val track = LEARNING_TRACKS.firstOrNull { it.index == trackIndex } ?: return@mapNotNull null
                if (reached >= track.startQuestion) track to reached else null
            }
            .maxByOrNull { it.second }
        if (continueTarget != null && continueTarget.second - 1 in questions.indices) item(key = "continue") {
            val qi = continueTarget.second - 1
            val q = questions[qi]
            val total = continueTarget.first.total
            val done = continueTarget.first.completedCount(continueTarget.second)
            Spacer(Modifier.height(12.dp))
            GlassCard(onClick = { nav.open("qa:$qi") }, modifier = Modifier.testTag("qa_track_continue")) {
                Text(
                    language.tr("Continúa donde lo dejaste", "Pick up where you left off", "Continue de onde parou"),
                    style = styles.headline,
                    color = p.textPrimary
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    language.tr("Pregunta ${q.number}", "Question ${q.number}", "Pergunta ${q.number}") + " · " + q.question.get(language),
                    style = styles.callout,
                    color = p.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(12.dp))
                AppProgressBar(done.toFloat() / total, height = 3.dp)
            }
        }

        // ---- itineraries
        item(key = "tracks") {
            SectionHeader(language.tr("Itinerarios", "Itineraries", "Itinerários"))
            AppListGroup {
                LEARNING_TRACKS.forEach { track ->
                    val reached = trackProgress[track.index] ?: 0
                    val completed = track.completedCount(reached)
                    val fraction = if (track.total > 0) completed.toFloat() / track.total else 0f
                    val progressLabel = language.tr(
                        "$completed de ${track.total} preguntas",
                        "$completed of ${track.total} questions",
                        "$completed de ${track.total} perguntas"
                    )
                    LeadRow(
                        title = trackTitle(track, language),
                        subtitle = language.tr(
                            "Preguntas ${track.startQuestion}–${track.endQuestion} · $progressLabel",
                            "Questions ${track.startQuestion}–${track.endQuestion} · $progressLabel",
                            "Perguntas ${track.startQuestion}–${track.endQuestion} · $progressLabel"
                        ),
                        leading = {
                            AppProgressRing(
                                progress = fraction,
                                size = 44.dp,
                                stroke = 5.dp,
                                modifier = Modifier
                                    .semantics { stateDescription = progressLabel }
                                    .testTag("qa_track_ring_${track.index}")
                            ) {
                                Text(
                                    "${(fraction * 100).toInt()}%",
                                    style = styles.caption.copy(fontSize = styles.caption.fontSize * 0.85f, fontFeatureSettings = "tnum"),
                                    color = p.textPrimary,
                                    maxLines = 1
                                )
                            }
                        },
                        leadingWidth = 44.dp,
                        onClick = { nav.open("qa:${trackResumeIndex(track, reached)}") },
                        tag = "qa_track_${track.index}"
                    )
                }
            }
        }

        // ---- quiz for each finished itinerary
        LEARNING_TRACKS.forEach { track ->
            val reached = trackProgress[track.index] ?: 0
            if (track.completedCount(reached) >= track.total) {
                val quiz = trackQuizQuestions(repository.triviaQuestions, track.index)
                if (quiz.isNotEmpty()) item(key = "quiz_${track.index}") {
                    Spacer(Modifier.height(16.dp))
                    TrackQuiz(
                        track = track,
                        questions = quiz,
                        language = language,
                        answers = answers,
                        onAnswer = { key, option ->
                            if (key !in answers) answersCode = encodeAnswers(answers + (key to option))
                        }
                    )
                }
            }
        }

        // ---- themes
        item(key = "themes") {
            SectionHeader(
                language.tr("Temas", "Themes", "Temas"),
                trailing = "${themes.size}"
            )
            AppListGroup {
                themes.forEachIndexed { n, theme ->
                    LeadRow(
                        title = questions[theme.indices.first()].section.get(language),
                        leading = { NumeralCircle("${n + 1}") },
                        trailingText = "${theme.indices.size}",
                        onClick = { nav.open("theme:$n") },
                        tag = "qa_theme_$n"
                    )
                }
            }
        }

        // ---- sources
        item(key = "sources") {
            Spacer(Modifier.height(28.dp))
            AppListGroup {
                LeadRow(
                    title = language.tr("Fuentes y método", "Sources & method", "Fontes e método"),
                    subtitle = language.tr(
                        "Catecismo, Didaché y documentos del Vaticano",
                        "Catechism, Didache and Vatican documents",
                        "Catecismo, Didaqué e documentos do Vaticano"
                    ),
                    leading = {
                        Icon(Icons.Outlined.MenuBook, null, tint = p.textPrimary, modifier = Modifier.size(22.dp))
                    },
                    onClick = { nav.open("sources:0") },
                    tag = "sources_entry"
                )
            }
        }
    }
}

// -------------------------------------------------------------------- quiz

@Composable
private fun TrackQuiz(
    track: LearningTrack,
    questions: List<TriviaQuestion>,
    language: AppLanguage,
    answers: Map<String, Int>,
    onAnswer: (key: String, option: Int) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    GlassCard(modifier = Modifier.testTag("qa_track_quiz_${track.index}")) {
        Text(
            "Mini-quiz · " + trackTitle(track, language),
            style = styles.eyebrow,
            color = p.textSecondary,
            modifier = Modifier.semantics { heading() }
        )
        questions.forEachIndexed { qi, trivia ->
            val key = "${track.index}_$qi"
            val answered = answers[key]
            Spacer(Modifier.height(if (qi == 0) 12.dp else 24.dp))
            Text(trivia.question.get(language), style = styles.headline, color = p.textPrimary)
            Spacer(Modifier.height(12.dp))
            trivia.options.forEachIndexed { oi, option ->
                QuizOption(
                    label = option.get(language),
                    chosen = answered == oi,
                    isCorrect = oi == trivia.correctIndex,
                    revealed = answered != null,
                    language = language,
                    onClick = { if (answered == null) onAnswer(key, oi) },
                    modifier = Modifier.testTag("qa_track_quiz_${track.index}_${qi}_opt_$oi")
                )
                Spacer(Modifier.height(8.dp))
            }
            if (answered != null) {
                val correct = answered == trivia.correctIndex
                Spacer(Modifier.height(4.dp))
                Text(
                    language.tr(
                        if (correct) "Correcto" else "Incorrecto",
                        if (correct) "Correct" else "Incorrect",
                        if (correct) "Correto" else "Incorreto"
                    ),
                    style = styles.subhead.copy(fontWeight = FontWeight.SemiBold),
                    color = p.textPrimary,
                    modifier = Modifier.testTag("qa_track_quiz_${track.index}_${qi}_feedback")
                )
                Spacer(Modifier.height(4.dp))
                Text(trivia.doctrinalExplanation.get(language), style = styles.footnote, color = p.textSecondary)
            }
        }
    }
}

@Composable
private fun QuizOption(
    label: String,
    chosen: Boolean,
    isCorrect: Boolean,
    revealed: Boolean,
    language: AppLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val shape = design.shapes.listGroup
    val state = when {
        !revealed -> null
        isCorrect -> language.tr("Correcta", "Correct", "Correta")
        chosen -> language.tr("Incorrecta", "Incorrect", "Incorreta")
        else -> null
    }
    Row(
        modifier
            .fillMaxWidth()
            .heightIn(min = 52.dp)
            .lumenPress(onClick, 0.98f)
            .lumenGlass(shape, lifted = false)
            .clip(shape)
            .semantics {
                selected = chosen
                if (state != null) stateDescription = state
            }
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(26.dp)
                .clip(CircleShape)
                .then(
                    when {
                        revealed && isCorrect -> Modifier.background(p.success)
                        revealed && chosen -> Modifier.background(p.textPrimary.copy(alpha = 0.12f))
                        else -> Modifier.border(1.5.dp, p.inkQuiet, CircleShape)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (revealed && isCorrect) Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
            else if (revealed && chosen) Icon(Icons.Filled.Close, null, tint = p.textPrimary, modifier = Modifier.size(15.dp))
        }
        Spacer(Modifier.width(14.dp))
        Text(label, style = styles.callout, color = p.textPrimary, modifier = Modifier.weight(1f))
    }
}

// ----------------------------------------------------------------- entries

@Composable
internal fun QuestionsEntry(
    kind: String,
    index: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    when (kind) {
        "theme" -> ThemePage(index, repository, language, nav)
        "sources" -> SourcesPage(language)
        else -> QuestionPage(index, repository, language, nav)
    }
}

@Composable
private fun ThemePage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val questions = repository.catholicQuestions
    val themes = remember(questions) { questionThemes(questions) }
    if (themes.isEmpty()) return
    val n = rawIndex.coerceIn(0, themes.lastIndex)
    val theme = themes[n]
    val name = questions[theme.indices.first()].section.get(language)
    AppInlineTitle(name)
    ReaderPage(
        title = name,
        eyebrow = language.tr(
            "Tema ${n + 1} de ${themes.size} · ${theme.indices.size} preguntas",
            "Theme ${n + 1} of ${themes.size} · ${theme.indices.size} questions",
            "Tema ${n + 1} de ${themes.size} · ${theme.indices.size} perguntas"
        )
    ) {
        item(key = "questions") {
            AppListGroup {
                theme.indices.forEach { qi ->
                    val q = questions[qi]
                    LeadRow(
                        title = q.question.get(language),
                        leading = {
                            Text(
                                "${q.number}",
                                style = LocalAppTextStyles.current.footnote.copy(fontFeatureSettings = "tnum"),
                                color = LocalAppDesign.current.palette.textSecondary
                            )
                        },
                        leadingWidth = 28.dp,
                        onClick = { nav.open("qa:$qi") },
                        tag = "catholic_question_${q.number}",
                        maxTitleLines = 5
                    )
                }
            }
            BlockGap(28.dp)
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = themes.getOrNull(n + 1)?.let { questions[it.indices.first()].section.get(language) },
                prevTitle = themes.getOrNull(n - 1)?.let { questions[it.indices.first()].section.get(language) },
                onNext = { nav.replace("theme:${n + 1}") },
                onPrev = { nav.replace("theme:${n - 1}") },
                nextTag = "qa_theme_next",
                prevTag = "qa_theme_prev"
            )
        }
    }
}

// ----------------------------------------------------------- answer reader

private fun refKeyOf(ref: FoundationRef): String = when (ref) {
    is FoundationRef.BibleRef -> {
        val r = ref.reference
        "bible_${r.bookId}_${r.chapter}_${r.verseStart ?: "all"}_${r.verseEnd ?: ""}"
    }
    is FoundationRef.CatechismRef -> "ccc_${ref.startParagraph}_${ref.endParagraph ?: ""}"
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QuestionPage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val questions = repository.catholicQuestions
    if (questions.isEmpty()) return
    val index = rawIndex.coerceIn(0, questions.lastIndex)
    val question: ApologeticsQuestion = questions[index]
    val total = questions.size
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val context = LocalContext.current

    val questionText = question.question.get(language)
    AppInlineTitle(language.tr("Pregunta ${question.number}", "Question ${question.number}", "Pergunta ${question.number}"))

    // Guided itineraries: reading a question raises its itinerary's reached mark.
    val store = remember { UserPreferencesStore(context) }
    LaunchedEffect(index) { store.recordTrackProgress(question.number) }
    LaunchedEffect(Unit) { nav.voice.prepare() }

    val answerLabel = language.tr("Respuesta", "Answer", "Resposta")
    val foundationLabel = language.tr("Fundamento", "Foundation", "Fundamento")
    val clarificationLabel = language.tr("Aclaración", "Clarification", "Esclarecimento")
    val answerText = question.answer.get(language)
    val foundationText = question.foundation.get(language)
    val clarificationText = cleanClarification(question.clarification.get(language))

    val refs = remember(foundationText, language, repository.catholicBooks) {
        parseFoundation(foundationText, language, repository.catholicBooks)
    }
    val level = remember(refs, clarificationText) { foundationLevelFor(refs, clarificationText) }
    var expandedKey by remember(index, language) { mutableStateOf<String?>(null) }

    val speak = buildString {
        append(questionText); append("\n\n"); append(answerLabel); append(": "); append(answerText)
        if (foundationText.isNotBlank()) { append("\n\n"); append(foundationLabel); append(": "); append(foundationText) }
        if (clarificationText.isNotBlank()) { append("\n\n"); append(clarificationLabel); append(": "); append(clarificationText) }
    }
    val share = buildString {
        append(questionText); append("\n\n"); append(answerText)
        if (foundationText.isNotBlank()) { append("\n\n"); append(foundationText) }
    }
    val progressLabel = language.tr(
        "Pregunta ${index + 1} de $total", "Question ${index + 1} of $total", "Pergunta ${index + 1} de $total"
    )
    val prayerId = qaRelatedPrayerId(question.number)
    val prayerName = repository.vaticanPrayers.firstOrNull { it.id == prayerId }?.title?.get(language)

    ReaderPage(
        title = questionText,
        eyebrow = progressLabel + " · " + question.section.get(language),
        progress = (index + 1).toFloat() / total,
        progressLabel = progressLabel,
        progressTag = "catholic_qa_progress"
    ) {
        item(key = "actions") {
            ReaderActionRow(language, nav.voice, speak, share, "catholic_qa_listen_button", "catholic_qa_share")
            BlockGap(24.dp)
        }
        item(key = "answer") {
            ReadingParagraphs(answerText, lead = true)
        }

        if (foundationText.isNotBlank()) {
            item(key = "foundation") {
                SectionHeader(foundationLabel, topGap = 36.dp)
                if (refs.isEmpty() || !isCitationOnly(foundationText)) {
                    Text(
                        foundationText,
                        style = styles.callout,
                        color = p.textSecondary,
                        modifier = Modifier.padding(horizontal = design.spacing.gutter).padding(bottom = 12.dp)
                    )
                }
                if (refs.isNotEmpty()) {
                    FlowRow(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = design.spacing.gutter)
                            .semantics { contentDescription = foundationText },
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        refs.forEach { ref ->
                            val key = refKeyOf(ref)
                            val label = refLabel(ref, repository, language)
                            RefChip(
                                label = label,
                                icon = if (ref is FoundationRef.BibleRef) Icons.Outlined.MenuBook else Icons.Outlined.AutoStories,
                                selected = expandedKey == key,
                                onClick = { expandedKey = if (expandedKey == key) null else key },
                                tag = "foundation_ref_$key"
                            )
                        }
                    }
                    val open = refs.firstOrNull { refKeyOf(it) == expandedKey }
                    if (open != null) {
                        Spacer(Modifier.height(14.dp))
                        PassageCard(open, repository, language, nav)
                    }
                }
                Spacer(Modifier.height(16.dp))
                FoundationLevelPill(level, language)
            }
        } else {
            item(key = "level") {
                Spacer(Modifier.height(28.dp))
                FoundationLevelPill(level, language)
            }
        }

        if (clarificationText.isNotBlank()) {
            item(key = "clarification") {
                Spacer(Modifier.height(28.dp))
                NoteCard(clarificationLabel, clarificationText)
            }
        }

        item(key = "pray") {
            Spacer(Modifier.height(36.dp))
            PrayNow(language, prayerName) { nav.openPrayer(prayerId) }
            BlockGap(28.dp)
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = questions.getOrNull(index + 1)?.question?.get(language),
                prevTitle = questions.getOrNull(index - 1)?.question?.get(language),
                onNext = { nav.replace("qa:${index + 1}") },
                onPrev = { nav.replace("qa:${index - 1}") },
                nextTag = "catholic_qa_next",
                prevTag = "catholic_qa_prev"
            )
        }
    }
}

private fun refLabel(ref: FoundationRef, repository: CatholicDataRepository, language: AppLanguage): String = when (ref) {
    is FoundationRef.BibleRef -> {
        val r = ref.reference
        val book = repository.catholicBooks.firstOrNull { it.id == r.bookId }?.name?.get(language) ?: r.bookId
        val sep = if (language == AppLanguage.EN) ":" else ","
        book + " " + r.chapter +
            (r.verseStart?.let { "$sep$it" } ?: "") +
            (r.verseEnd?.let { "–$it" } ?: "")
    }
    is FoundationRef.CatechismRef ->
        language.tr("Catecismo", "Catechism", "Catecismo") + " " + ref.startParagraph +
            (ref.endParagraph?.let { "–$it" } ?: "")
}

/** The opened reference: its text, and a way into the full Bible or Catechism. */
@Composable
private fun PassageCard(
    ref: FoundationRef,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val key = refKeyOf(ref)
    GlassCard {
        Text(
            refLabel(ref, repository, language),
            style = styles.headline,
            color = p.textPrimary,
            modifier = Modifier.semantics { heading() }
        )
        val number = SpanStyle(
            fontSize = styles.reading.fontSize * 0.62f,
            color = p.textSecondary,
            baselineShift = BaselineShift.Superscript
        )
        when (ref) {
            is FoundationRef.BibleRef -> {
                val r = ref.reference
                val chapter = repository.getChapter(r.bookId, r.chapter, language)
                val verses = r.verseStart?.let { start ->
                    chapter.verses.filter { it.number in start..(r.verseEnd ?: start) }
                } ?: chapter.verses
                if (verses.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        buildAnnotatedString {
                            verses.forEachIndexed { i, v ->
                                if (i > 0) append(" ")
                                withStyle(number) { append("${v.number}") }
                                append(" ")
                                append(v.text.get(language))
                            }
                        },
                        style = styles.reading,
                        color = p.textPrimary,
                        modifier = Modifier.testTag("foundation_ref_text_$key")
                    )
                }
                Spacer(Modifier.height(16.dp))
                AppSecondaryCapsule(
                    label = language.tr("Abrir en la Biblia", "Open in the Bible", "Abrir na Bíblia"),
                    icon = Icons.Outlined.MenuBook,
                    onClick = { nav.openBible(r.bookId, r.chapter, r.verseStart) },
                    fillWidth = true,
                    tag = "foundation_ref_open_$key"
                )
            }
            is FoundationRef.CatechismRef -> {
                val paragraphs = (ref.startParagraph..(ref.endParagraph ?: ref.startParagraph)).mapNotNull { n ->
                    repository.catechismParagraphText(n, language)?.trim()?.takeIf { it.isNotBlank() }?.let { n to it }
                }
                if (paragraphs.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        buildAnnotatedString {
                            paragraphs.forEachIndexed { i, (n, text) ->
                                if (i > 0) append("\n\n")
                                withStyle(number) { append("$n") }
                                append(" ")
                                append(text)
                            }
                        },
                        style = styles.reading,
                        color = p.textPrimary,
                        modifier = Modifier.testTag("foundation_ref_text_$key")
                    )
                }
                Spacer(Modifier.height(16.dp))
                AppSecondaryCapsule(
                    label = language.tr("Abrir en el Catecismo", "Open in the Catechism", "Abrir no Catecismo"),
                    icon = Icons.Outlined.AutoStories,
                    onClick = { nav.openCatechism(ref.startParagraph) },
                    fillWidth = true,
                    tag = "foundation_ref_open_$key"
                )
            }
        }
    }
}

// --------------------------------------------------------- foundation level

/**
 * Foundation level, an honest heuristic from the refs the parser found plus a
 * word check on the clarification: A only Bible refs, B any Catechism ref,
 * C the clarification mentions Tradition, D everything else.
 */
internal fun foundationLevelFor(refs: List<FoundationRef>, clarificationText: String): Char {
    val hasBible = refs.any { it is FoundationRef.BibleRef }
    val hasCcc = refs.any { it is FoundationRef.CatechismRef }
    if (hasBible && !hasCcc) return 'A'
    if (hasCcc) return 'B'
    val folded = doctrineFold(clarificationText)
    if ("tradicion" in folded || "tradicao" in folded) return 'C'
    return 'D'
}

internal fun foundationLevelLabel(level: Char, language: AppLanguage): String = when (level) {
    'A' -> language.tr("Bíblico explícito", "Explicitly biblical", "Bíblico explícito")
    'B' -> language.tr("Interpretativo", "Interpretive", "Interpretativo")
    'C' -> language.tr("Tradicional", "Traditional", "Tradicional")
    else -> language.tr("Desarrollo/disciplina", "Development/discipline", "Desenvolvimento/disciplina")
}

internal fun foundationLevelExplanation(level: Char, language: AppLanguage): String = when (level) {
    'A' -> language.tr(
        "Cita solo referencias bíblicas; señal automática, puede no capturar latín u otras citas.",
        "Cites only Bible references; automatic signal, may miss Latin or other citations.",
        "Cita apenas referências bíblicas; sinal automático, pode não capturar latim ou outras citações."
    )
    'B' -> language.tr(
        "Cita el Catecismo (CCC); señal automática, no sustituye leer los párrafos citados.",
        "Cites the Catechism (CCC); automatic signal, does not replace reading the cited paragraphs.",
        "Cita o Catecismo (CCC); sinal automático, não substitui ler os parágrafos citados."
    )
    'C' -> language.tr(
        "La aclaración menciona la Tradición; señal automática y orientativa.",
        "The clarification mentions Tradition; automatic, indicative signal.",
        "O esclarecimento menciona a Tradição; sinal automático e indicativo."
    )
    else -> language.tr(
        "Sin cita bíblica o del CCC detectada; señal automática, no clasifica todo el contenido.",
        "No Bible or CCC citation detected; automatic signal, does not classify all content.",
        "Nenhuma citação bíblica ou do CCC detetada; sinal automático, não classifica todo o conteúdo."
    )
}

@Composable
private fun FoundationLevelPill(level: Char, language: AppLanguage) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val shape = RoundedCornerShape(percent = 50)
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .testTag("foundation_level_badge")
    ) {
        Box(Modifier.lumenGlass(shape, lifted = false).padding(horizontal = 14.dp, vertical = 7.dp)) {
            Text(
                language.tr("Fundamento", "Foundation", "Fundamento") + " $level · " + foundationLevelLabel(level, language),
                style = styles.footnote.copy(fontWeight = FontWeight.Medium),
                color = p.textPrimary
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(foundationLevelExplanation(level, language), style = styles.footnote, color = p.textSecondary)
    }
}

// ----------------------------------------------------------------- sources

@Composable
private fun SourcesPage(language: AppLanguage) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val title = language.tr("Fuentes y método", "Sources & method", "Fontes e método")
    AppInlineTitle(title)
    ReaderPage(
        title = title,
        meta = language.tr(
            "De dónde sale cada respuesta y cómo leer su señal",
            "Where each answer comes from and how to read its signal",
            "De onde vem cada resposta e como ler o seu sinal"
        )
    ) {
        item(key = "sources") {
            Column(Modifier.testTag("sources_page")) {
                SourceBlock(
                    language.tr("Catecismo — texto completo", "Catechism — full text", "Catecismo — texto completo"),
                    language.tr(
                        "Catechism of the Catholic Church (CCC), texto completo en vatican.va. Las referencias del tipo «Catecismo, 309-314» abren los párrafos citados en la app.",
                        "Catechism of the Catholic Church (CCC), full text at vatican.va. References such as \"Catechism, 309-314\" open the cited paragraphs in the app.",
                        "Catecismo da Igreja Católica (CCC), texto completo em vatican.va. As referências do tipo «Catecismo, 309-314» abrem os parágrafos citados na app."
                    )
                )
                SourceBlock(
                    language.tr("Didaché — clerus.org", "Didache — clerus.org", "Didaqué — clerus.org"),
                    language.tr(
                        "Texto de la Didaché según la fuente de clerus.org, reproducido en la sección de Doctrina. Se conserva la traducción y el orden de capítulos de esa fuente.",
                        "Didache text from the clerus.org source, reproduced in the Doctrine section. The translation and chapter order of that source are kept.",
                        "Texto da Didaqué segundo a fonte clerus.org, reproduzido na secção de Doutrina. Mantêm-se a tradução e a ordem dos capítulos dessa fonte."
                    )
                )
                SectionHeader(language.tr("Documentos del Vaticano", "Vatican documents", "Documentos do Vaticano"), topGap = 28.dp)
                AppListGroup {
                    LeadRow(title = "Mater Populi fidelis", subtitle = "2025")
                    LeadRow(title = "Fiducia supplicans", subtitle = "2023")
                    LeadRow(title = "Humanae vitae")
                    LeadRow(
                        title = "Dignitas infinita · Dignitas personae",
                        subtitle = language.tr("Sobre la dignidad humana", "On human dignity", "Sobre a dignidade humana")
                    )
                }
                SectionHeader(
                    language.tr("Niveles de fundamento", "Foundation levels", "Níveis de fundamento"),
                    topGap = 28.dp
                )
                AppListGroup {
                    listOf('A', 'B', 'C', 'D').forEach { level ->
                        LeadRow(
                            title = foundationLevelLabel(level, language),
                            subtitle = foundationLevelExplanation(level, language),
                            leading = { NumeralCircle("$level") }
                        )
                    }
                }
                FootnoteText(
                    language.tr(
                        "Señal automática: no sustituye leer la fuente original.",
                        "An automatic signal: it does not replace reading the original source.",
                        "Sinal automático: não substitui ler a fonte original."
                    )
                )
                Spacer(Modifier.height(12.dp))
                GlassCard {
                    Text(
                        language.tr("Uso honesto", "Honest use", "Uso honesto"),
                        style = styles.headline,
                        color = p.textPrimary,
                        modifier = Modifier.semantics { heading() }
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        language.tr(
                            "La señal de nivel es una heurística automática sobre el texto del Fundamento y la Aclaración: puede no detectar citas en latín, abreviaturas no reconocidas o referencias sin formato «Libro capítulo,versículo». No sustituye la lectura de la fuente original ni la orientación de un sacerdote o catequista.",
                            "The level signal is an automatic heuristic over the Foundation and Clarification text: it may miss Latin citations, unrecognized abbreviations, or references not in \"Book chapter,verse\" format. It does not replace reading the original source or guidance from a priest or catechist.",
                            "O sinal de nível é uma heurística automática sobre o texto do Fundamento e do Esclarecimento: pode não detetar citações em latim, abreviaturas não reconhecidas ou referências fora do formato «Livro capítulo,versículo». Não substitui a leitura da fonte original nem a orientação de um sacerdote ou catequista."
                        ),
                        style = styles.footnote,
                        color = p.textSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun SourceBlock(label: String, body: String) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Column(Modifier.padding(horizontal = design.spacing.gutter).padding(bottom = 20.dp)) {
        Text(label, style = styles.headline, color = design.palette.textPrimary, modifier = Modifier.semantics { heading() })
        Spacer(Modifier.height(6.dp))
        Text(body, style = styles.callout, color = design.palette.textSecondary)
    }
}
