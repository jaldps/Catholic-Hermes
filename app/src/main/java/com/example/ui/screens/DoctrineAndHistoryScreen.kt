package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.data.liturgy.FoundationRef
import com.example.data.liturgy.parseFoundation
import com.example.data.models.AppLanguage
import com.example.data.models.ApologeticsQuestion
import com.example.data.models.CapitalSinVsVirtue
import com.example.data.models.ChurchHistoryEpoch
import com.example.data.models.ChurchSchismHistory
import com.example.data.models.DidacheChapter
import com.example.data.models.NearbyParish
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.news.NewsResult
import com.example.data.news.VaticanNewsFetcher
import com.example.data.news.VaticanNewsItem
import com.example.data.prefs.UserPreferencesStore
import com.example.data.models.TriviaQuestion
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ParishScheduleText
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import androidx.activity.compose.BackHandler
import com.example.ui.components.InlineBackLink
import com.example.ui.components.MenuPickerRow
import com.example.ui.components.TextFormat
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.AppDesign
import com.example.ui.theme.AppTextStyles
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * Sentinel for "do not narrow at this level". The three geographic menus are
 * compared against it, so the label the reader sees can be translated without
 * any filter silently ceasing to match.
 */
private const val ANY_PLACE = "__any__"

/** Minimum touch target for every control this screen owns (AAA). */
private val MinTouchTarget = 48.dp

/**
 * Doctrine, history and the directory, behind five panes: the moral teaching of
 * the Catechism (the Decalogue, the precepts, the capital vices against the
 * virtues that cure them, and the gravity of sin), the Marian and
 * Christological devotions, the Church's own timeline with the schisms that
 * mark it, the religious orders and Church offices, and the parish directory
 * beside the Vatican's live feed.
 */
@Composable
fun DoctrineAndHistoryScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    initialTopTab: Int = 0,
    showTabs: Boolean = false,
    modifier: Modifier = Modifier,
    onOpenBible: (bookId: String, chapter: Int, verseStart: Int?) -> Unit = { _, _, _ -> },
    onOpenCatechism: (paragraph: Int) -> Unit = {},
    onOpenPrayer: (prayerId: String) -> Unit = {}
) {
    val design = LocalAppDesign.current

    // 0 = Catechism & doctrine, 1 = Mary & Christ, 2 = history & schisms,
    // 3 = 101 Questions, 4 = Orders & Offices, 5 = news only (opened from
    // Churches & News).
    var selectedTopTab by remember { mutableIntStateOf(initialTopTab) }
    // A commandment, precept, vice or epoch opens as its own page, keyed
    // "kind:index"; the panes list one line of each.
    var openEntry by remember { mutableStateOf<String?>(null) }

    // Opened from "Churches & News" the destination is news only: no parish
    // directory or filters, just the Vatican feed under its own title.
    val directoryOnly = initialTopTab == 5

    // Short enough that all sit abreast on a 360dp phone in every identity;
    // the full names head each pane as its section header.
    val topTabs = listOf(
        currentLanguage.pick("Doctrina", "Doctrine", "Doutrina"),
        currentLanguage.pick("María y Jesús", "Mary & Jesus", "Maria e Jesus"),
        currentLanguage.pick("Historia", "History", "História"),
        currentLanguage.pick("Preguntas", "Questions", "Perguntas"),
        currentLanguage.pick("Órdenes y oficios", "Orders & Offices", "Ordens e ofícios")
    )
    val tabsInset = tabStripInset()

    // The large title, then the selector — the order every identity's own
    // platform uses. Both scroll away with the pane beneath them.
    val header: LazyListScope.() -> Unit = {
        item(key = "doctrine_title") {
            if (directoryOnly) {
                AppScreenTitle(
                    title = currentLanguage.pick(
                        "Noticias",
                        "News",
                        "Notícias"
                    ),
                    subtitle = null
                )
            } else {
                AppScreenTitle(
                    title = currentLanguage.pick(
                        "Doctrina e Historia",
                        "Doctrine & History",
                        "Doutrina e História"
                    ),
                    subtitle = null
                )
            }
        }
        if (!directoryOnly && showTabs) {
            item(key = "doctrine_tabs") {
                AppSegmentedTabs(
                    options = topTabs,
                    selectedIndex = selectedTopTab,
                    onSelect = { selectedTopTab = it },
                    modifier = Modifier
                        .padding(horizontal = tabsInset)
                        .heightIn(min = MinTouchTarget)
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("doctrine_and_history_screen")
    ) {
        val entry = openEntry
        if (entry != null && selectedTopTab in 0..4) {
            BackHandler { openEntry = null }
            // Keyed by entry so Prev/Next disposes and recreates the whole
            // page: title and body can never show different entries.
            key(entry) {
            DoctrineEntryPage(
                key = entry,
                repository = repository,
                currentLanguage = currentLanguage,
                backLabel = topTabs[selectedTopTab],
                onBack = { openEntry = null },
                onOpen = { openEntry = it },
                onOpenBible = onOpenBible,
                onOpenCatechism = onOpenCatechism,
                onOpenPrayer = onOpenPrayer
            )
            }
        } else when (selectedTopTab) {
            0 -> MoralDoctrinePane(
                repository = repository,
                currentLanguage = currentLanguage,
                onOpen = { openEntry = it },
                header = header
            )

            1 -> DevotionsExplorerScreen(
                repository = repository,
                currentLanguage = currentLanguage,
                header = header
            )

            2 -> ChurchHistoryPane(
                repository = repository,
                currentLanguage = currentLanguage,
                onOpen = { openEntry = it },
                header = header
            )

            3 -> CatholicQuestionsPane(
                repository = repository,
                currentLanguage = currentLanguage,
                onOpen = { openEntry = it },
                header = header
            )

            4 -> OrdersOfficesPane(
                repository = repository,
                currentLanguage = currentLanguage,
                onOpen = { openEntry = it },
                header = header
            )

            else -> ParishesAndNewsPane(
                repository = repository,
                currentLanguage = currentLanguage,
                header = header,
                newsOnly = directoryOnly
            )
        }
    }
}

// ------------------------------------------------------ 101 Catholic Questions

/** One guided learning track: a contiguous question range (1-based numbers). */
private data class LearningTrack(val index: Int, val startQuestion: Int, val endQuestion: Int) {
    val total: Int get() = endQuestion - startQuestion + 1
    /** Questions completed = those up to the highest reached question number. */
    fun completedCount(reachedQuestion: Int): Int =
        (reachedQuestion - startQuestion + 1).coerceIn(0, total)
}

private val LEARNING_TRACKS = listOf(
    LearningTrack(index = 0, startQuestion = 1, endQuestion = 30),
    LearningTrack(index = 1, startQuestion = 41, endQuestion = 60),
    LearningTrack(index = 2, startQuestion = 61, endQuestion = 100)
)

private fun learningTrackTitle(track: LearningTrack, language: AppLanguage): String = when (track.index) {
    0 -> language.pick("Fundamentos", "Foundations", "Fundamentos")
    1 -> language.pick("Iglesia y sacramentos", "Church and sacraments", "Igreja e sacramentos")
    else -> language.pick("Vida y moral", "Life and morals", "Vida e moral")
}

/** Deterministic mini-quiz sample: 3 trivia questions chosen by track index alone. */
private fun trackQuizQuestions(trivia: List<TriviaQuestion>, trackIndex: Int): List<TriviaQuestion> {
    if (trivia.isEmpty()) return emptyList()
    val start = (trackIndex * 3) % trivia.size
    return (0 until 3).map { k -> trivia[(start + k) % trivia.size] }
}

/**
 * End-of-track mini-quiz: three sampled trivia questions with immediate
 * correct/incorrect feedback and the doctrinal explanation. Answers live
 * only in composition state — no persistence beyond the reached index.
 */
@Composable
private fun TrackMiniQuiz(
    track: LearningTrack,
    questions: List<TriviaQuestion>,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current
    var answers by remember(track.index) { mutableStateOf<Map<Int, Int>>(emptyMap()) }
    AppCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .testTag("qa_track_quiz_${track.index}")
    ) {
        Text(
            text = currentLanguage.pick(
                "Mini-quiz: ${learningTrackTitle(track, currentLanguage)}",
                "Mini-quiz: ${learningTrackTitle(track, currentLanguage)}",
                "Mini-quiz: ${learningTrackTitle(track, currentLanguage)}"
            ),
            style = MaterialTheme.typography.titleMedium,
            color = design.palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        questions.forEachIndexed { qi, trivia ->
            if (qi > 0) {
                AppDivider()
                Spacer(Modifier.height(design.spacing.cardGap))
            }
            Text(
                text = trivia.question.get(currentLanguage),
                style = MaterialTheme.typography.bodyLarge,
                color = design.palette.textPrimary
            )
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            val answered = answers[qi]
            trivia.options.forEachIndexed { oi, option ->
                AppSecondaryButton(
                    label = option.get(currentLanguage),
                    onClick = { if (answered == null) answers = answers + (qi to oi) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = MinTouchTarget)
                        .testTag("qa_track_quiz_${track.index}_${qi}_opt_$oi")
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
            }
            if (answered != null) {
                val correct = answered == trivia.correctIndex
                Text(
                    text = currentLanguage.pick(
                        if (correct) "Correcto" else "Incorrecto",
                        if (correct) "Correct" else "Incorrect",
                        if (correct) "Correto" else "Incorreto"
                    ),
                    style = MaterialTheme.typography.titleSmall,
                    color = if (correct) design.palette.accent else design.palette.textPrimary,
                    modifier = Modifier.testTag("qa_track_quiz_${track.index}_${qi}_feedback")
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                Text(
                    text = trivia.doctrinalExplanation.get(currentLanguage),
                    style = MaterialTheme.typography.bodyMedium,
                    color = design.palette.textSecondary
                )
            }
        }
    }
}

@Composable
private fun CatholicQuestionsPane(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onOpen: (String) -> Unit,
    header: LazyListScope.() -> Unit
) {
    val design = LocalAppDesign.current
    LazyColumn(Modifier.fillMaxSize()) {
        header()
        item(key = "sources_entry") {
            AppListGroup {
                AppListRow(
                    title = currentLanguage.pick(
                        "Fuentes y método",
                        "Sources & method",
                        "Fontes e método"
                    ),
                    subtitle = currentLanguage.pick(
                        "Catecismo, Didaché y documentos del Vaticano",
                        "Catechism, Didache and Vatican documents",
                        "Catecismo, Didaché e documentos do Vaticano"
                    ),
                    leadingIcon = Icons.Default.MenuBook,
                    onClick = { onOpen("sources:0") },
                    modifier = Modifier
                        .heightIn(min = MinTouchTarget)
                        .testTag("sources_entry")
                )
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }
        // Guided learning tracks: three cards with a progress ring, one
        // Continuar chip, and an end-of-track mini-quiz per completed track.
        item(key = "learning_tracks") {
            val context = LocalContext.current
            val prefsStore = remember { UserPreferencesStore(context) }
            val trackProgress by prefsStore.trackProgress.collectAsState(initial = emptyMap())
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Itinerarios guiados",
                    "Guided learning tracks",
                    "Itinerários guiados"
                )
            )
            Text(
                text = currentLanguage.pick(
                    "Tres recorridos por las preguntas; tu avance se guarda solo.",
                    "Three routes through the questions; your progress is saved automatically.",
                    "Três percursos pelas perguntas; o seu progresso é guardado automaticamente."
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = design.palette.textSecondary,
                modifier = Modifier.padding(horizontal = design.spacing.gutter)
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            LEARNING_TRACKS.forEach { track ->
                val reached = trackProgress[track.index] ?: 0
                val completed = track.completedCount(reached)
                val fraction = if (track.total > 0) completed.toFloat() / track.total else 0f
                val progressLabel = currentLanguage.pick(
                    "$completed de ${track.total} preguntas",
                    "$completed of ${track.total} questions",
                    "$completed de ${track.total} perguntas"
                )
                AppCard(
                    onClick = { onOpen("qa:${track.startQuestion - 1}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .heightIn(min = MinTouchTarget)
                        .testTag("qa_track_${track.index}")
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = learningTrackTitle(track, currentLanguage),
                                style = MaterialTheme.typography.titleMedium,
                                color = design.palette.textPrimary
                            )
                            Text(
                                text = currentLanguage.pick(
                                    "Preguntas ${track.startQuestion}–${track.endQuestion}",
                                    "Questions ${track.startQuestion}–${track.endQuestion}",
                                    "Perguntas ${track.startQuestion}–${track.endQuestion}"
                                ),
                                style = MaterialTheme.typography.bodyMedium,
                                color = design.palette.textSecondary
                            )
                            Text(
                                text = progressLabel,
                                style = MaterialTheme.typography.bodySmall,
                                color = design.palette.textSecondary
                            )
                        }
                        Spacer(Modifier.width(design.spacing.cardGap))
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(
                                progress = { fraction },
                                modifier = Modifier
                                    .size(MinTouchTarget)
                                    .semantics { stateDescription = progressLabel }
                                    .testTag("qa_track_ring_${track.index}"),
                                color = design.palette.accent,
                                trackColor = design.palette.selectionTint
                            )
                            Text(
                                text = "${(fraction * 100).toInt()}%",
                                style = MaterialTheme.typography.labelSmall,
                                color = design.palette.textPrimary
                            )
                        }
                    }
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }
            // One Continuar chip: jumps to the last reached question of the
            // track with the highest reached question number.
            val continueTarget = trackProgress.entries
                .mapNotNull { (trackIndex, reached) ->
                    val track = LEARNING_TRACKS.firstOrNull { it.index == trackIndex } ?: return@mapNotNull null
                    if (reached >= track.startQuestion) track to reached else null
                }
                .maxByOrNull { it.second }
            if (continueTarget != null) {
                AppChip(
                    label = currentLanguage.pick("Continuar", "Continue", "Continuar"),
                    selected = true,
                    onClick = { onOpen("qa:${continueTarget.second - 1}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .heightIn(min = MinTouchTarget)
                        .testTag("qa_track_continue")
                )
                Spacer(Modifier.height(design.spacing.cardGap))
            }
            // End-of-track mini-quiz cards for completed tracks.
            LEARNING_TRACKS.forEach { track ->
                val reached = trackProgress[track.index] ?: 0
                if (track.completedCount(reached) >= track.total) {
                    val quizQuestions = trackQuizQuestions(repository.triviaQuestions, track.index)
                    if (quizQuestions.isNotEmpty()) {
                        TrackMiniQuiz(
                            track = track,
                            questions = quizQuestions,
                            currentLanguage = currentLanguage
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                    }
                }
            }
        }
        if (repository.catholicQuestions.isNotEmpty()) {
            item(key = "catholic_questions") {
                AppSectionHeader(
                    title = currentLanguage.pick(
                        "101 preguntas sobre la fe católica",
                        "101 Questions on the Catholic Faith",
                        "101 Perguntas sobre a fé católica"
                    )
                )
                Text(
                    text = currentLanguage.pick(
                        "101 preguntas y respuestas breves sobre la fe católica, agrupadas por temas.",
                        "101 short questions and answers on the Catholic faith, grouped by theme.",
                        "101 perguntas e respostas breves sobre a fé católica, agrupadas por tema."
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = design.palette.textSecondary,
                    modifier = Modifier.padding(horizontal = design.spacing.gutter)
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                // groupBy keeps the order in which each section first appears,
                // so the 11 themes read in the data's own sequence rather
                // than alphabetically.
                repository.catholicQuestions
                    .withIndex()
                    .groupBy { it.value.section.get(currentLanguage) }
                    .forEach { (sectionName, entries) ->
                        Text(
                            text = eyebrow(sectionName),
                            style = LocalAppTextStyles.current.sectionEyebrow,
                            color = design.palette.textSecondary,
                            modifier = Modifier.padding(horizontal = design.spacing.gutter)
                        )
                        AppListGroup {
                            entries.forEach { indexed ->
                                val question = indexed.value
                                AppListRow(
                                    title = question.question.get(currentLanguage),
                                    subtitle = question.section.get(currentLanguage),
                                    onClick = { onOpen("qa:${indexed.index}") },
                                    modifier = Modifier
                                        .heightIn(min = MinTouchTarget)
                                        .testTag("catholic_question_${question.number}")
                                )
                            }
                        }
                        Spacer(Modifier.height(design.spacing.cardGap))
                    }
            }
        }

        // The list ends under a small ornament, never an empty void.
        item { AppOrnament() }
        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// ------------------------------------------- religious orders and offices

@Composable
private fun OrdersOfficesPane(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onOpen: (String) -> Unit,
    header: LazyListScope.() -> Unit
) {
    val design = LocalAppDesign.current
    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item(key = "religious_orders") {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Órdenes religiosas",
                    "Religious orders",
                    "Ordens religiosas"
                )
            )
            if (repository.religiousOrders.isEmpty()) {
                AppEmptyState(
                    title = currentLanguage.pick(
                        "Sin órdenes por mostrar",
                        "No orders to show",
                        "Sem ordens para mostrar"
                    ),
                    icon = Icons.Default.Church
                )
            } else {
                // A single list in data order: the data may interleave men's
                // and women's orders, so no men/women split is inferred here.
                AppListGroup {
                    repository.religiousOrders.forEachIndexed { i, order ->
                        val subtitle = listOfNotNull(
                            order.abbreviation.takeIf { it.isNotBlank() },
                            order.foundationYear.takeIf { it.isNotBlank() }
                        ).joinToString(" · ").takeIf { it.isNotBlank() }
                        AppListRow(
                            title = order.name.get(currentLanguage),
                            subtitle = subtitle,
                            onClick = { onOpen("order:$i") },
                            modifier = Modifier
                                .heightIn(min = MinTouchTarget)
                                .testTag("order_${order.id}")
                        )
                    }
                }
            }
        }

        item(key = "church_offices") {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Oficios eclesiásticos",
                    "Church offices",
                    "Ofícios eclesiásticos"
                )
            )
            if (repository.churchOffices.isEmpty()) {
                AppEmptyState(
                    title = currentLanguage.pick(
                        "Sin oficios por mostrar",
                        "No offices to show",
                        "Sem ofícios para mostrar"
                    ),
                    icon = Icons.Default.LocationCity
                )
            } else {
                // Hierarchy order is the data order.
                AppListGroup {
                    repository.churchOffices.forEachIndexed { i, office ->
                        AppListRow(
                            title = office.name.get(currentLanguage),
                            onClick = { onOpen("office:$i") },
                            modifier = Modifier
                                .heightIn(min = MinTouchTarget)
                                .testTag("office_${office.id}")
                        )
                    }
                }
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // The list ends under a small ornament, never an empty void.
        item { AppOrnament() }
        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// ------------------------------------------------- the Catechism's moral part

@Composable
private fun MoralDoctrinePane(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onOpen: (String) -> Unit,
    header: LazyListScope.() -> Unit
) {
    val design = LocalAppDesign.current
    // Vatican hangs a Roman trailing numeral in the margin as a red figure;
    // elsewhere a numeral beside the chevron reads as a value, so it leads
    // the subtitle instead.
    val marginalNumerals = false
    fun numbered(n: Int, rest: String?) =
        if (marginalNumerals) rest else listOfNotNull(TextFormat.roman(n), rest).joinToString(" · ")

    // One selector on the page (the tabs above). The four parts of the moral
    // teaching follow one another as sections, each a list of one-line rows
    // that open a page — not four chips over walls of text.
    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item(key = "commandments") {
            AppSectionHeader(
                title = currentLanguage.pick("Los Diez Mandamientos", "The Ten Commandments", "Os Dez Mandamentos")
            )
            AppListGroup {
                repository.theTenCommandments.forEachIndexed { i, cmd ->
                    AppListRow(
                        title = TextFormat.withoutOrdinal(cmd.title.get(currentLanguage)),
                        subtitle = numbered(cmd.number, scriptureCitation(cmd.biblicalWording.get(currentLanguage))),
                        trailingText = if (marginalNumerals) TextFormat.roman(cmd.number) else null,
                        onClick = { onOpen("cmd:$i") },
                        modifier = Modifier.heightIn(min = MinTouchTarget).testTag("commandment_row_${cmd.number}")
                    )
                }
            }
        }

        item(key = "precepts") {
            AppSectionHeader(
                title = currentLanguage.pick("Los preceptos de la Iglesia", "The Precepts of the Church", "Os Mandamentos da Igreja")
            )
            AppListGroup {
                repository.churchPrecepts.forEachIndexed { i, prc ->
                    AppListRow(
                        title = TextFormat.withoutOrdinal(prc.title.get(currentLanguage)),
                        subtitle = numbered(prc.number, null),
                        trailingText = if (marginalNumerals) TextFormat.roman(prc.number) else null,
                        onClick = { onOpen("prc:$i") },
                        modifier = Modifier.heightIn(min = MinTouchTarget)
                    )
                }
            }
        }

        item(key = "vices") {
            AppSectionHeader(
                title = currentLanguage.pick("Vicios capitales y virtudes", "Capital Vices and Virtues", "Vícios capitais e virtudes")
            )
            AppListGroup {
                repository.capitalSinsVsVirtues.forEachIndexed { i, v ->
                    AppListRow(
                        title = v.capitalSin.get(currentLanguage),
                        subtitle = currentLanguage.pick(
                            "Se cura con: ${v.opposingVirtue.get(currentLanguage)}",
                            "Healed by ${v.opposingVirtue.get(currentLanguage).replaceFirstChar { it.lowercase() }}",
                            "Cura-se com: ${v.opposingVirtue.get(currentLanguage)}"
                        ),
                        onClick = { onOpen("vice:$i") },
                        modifier = Modifier.heightIn(min = MinTouchTarget)
                    )
                }
            }
        }

        item(key = "gravity") {
            AppSectionHeader(
                title = currentLanguage.pick("El pecado y la confesión", "Sin and Confession", "O pecado e a confissão")
            )
            AppListGroup {
                gravityTitles(currentLanguage).forEachIndexed { i, title ->
                    AppListRow(title = title, onClick = { onOpen("grav:$i") }, modifier = Modifier.heightIn(min = MinTouchTarget))
                }
            }
        }

        item(key = "didache") {
            AppSectionHeader(
                title = currentLanguage.pick("Didaché", "Didaché", "Didaché")
            )
            repository.didacheIntro.get(currentLanguage).takeIf { it.isNotBlank() }?.let { intro ->
                GutterCard {
                    didacheParagraphs(intro).forEachIndexed { i, paragraph ->
                        if (i > 0) Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = paragraph,
                            style = MaterialTheme.typography.bodyLarge,
                            color = design.palette.textPrimary
                        )
                    }
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }
            AppListGroup {
                repository.didacheChapters.forEachIndexed { i, chapter ->
                    AppListRow(
                        title = chapter.heading.get(currentLanguage),
                        subtitle = numbered(chapter.number, null),
                        trailingText = if (marginalNumerals) TextFormat.roman(chapter.number) else null,
                        onClick = { onOpen("didache:$i") },
                        modifier = Modifier.heightIn(min = MinTouchTarget).testTag("didache_chapter_${chapter.number}")
                    )
                }
            }
        }

        // The list ends under a small ornament, never an empty void.
        item { AppOrnament() }
        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

private fun gravityTitles(language: AppLanguage) = listOf(
    language.pick("Pecado venial y pecado mortal", "Venial and mortal sin", "Pecado venial e pecado mortal"),
    language.pick("Las tres condiciones del pecado mortal", "The three conditions of mortal sin", "As três condições do pecado mortal"),
    language.pick("Cómo prepararse para la confesión", "Preparing for confession", "Como preparar-se para a confissão")
)

/** The citation that closes a quotation — "(Exodus 20:2-3)" → "Exodus 20:2–3". */
private fun scriptureCitation(quoted: String): String? =
    Regex("""\(([^()]+)\)\s*$""").find(quoted.trim())?.groupValues?.get(1)?.let(TextFormat::ranges)

/** The quotation without its closing citation, which the page sets apart. */
private fun withoutCitation(quoted: String): String =
    quoted.trim().replace(Regex("""\s*\([^()]+\)\s*$"""), "")

/**
 * One entry of the moral teaching or of the Church's history, as a page: the
 * way back, an eyebrow, the title, and the full text in a single card.
 */
@Composable
private fun DoctrineEntryPage(
    key: String,
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    backLabel: String,
    onBack: () -> Unit,
    onOpen: (String) -> Unit = {},
    onOpenBible: (bookId: String, chapter: Int, verseStart: Int?) -> Unit = { _, _, _ -> },
    onOpenCatechism: (paragraph: Int) -> Unit = {},
    onOpenPrayer: (prayerId: String) -> Unit = {}
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val kind = key.substringBefore(':')
    val index = key.substringAfter(':').toIntOrNull() ?: 0
    val listState = rememberLazyListState()
    // A new entry is a new page: start at its top (matters when Prev/Next
    // moves between Didache chapters without leaving the page).
    LaunchedEffect(key) { listState.scrollToItem(0) }
    // Guided tracks: opening (or stepping to) a question raises its track's
    // persisted highest-reached number. Hoisted above the LazyColumn so the
    // remember/LaunchedEffect sit in a proper composable context.
    val trackPrefsContext = LocalContext.current
    val trackPrefsStore = remember { UserPreferencesStore(trackPrefsContext) }
    LaunchedEffect(key) {
        if (kind == "qa") {
            repository.catholicQuestions.getOrNull(index)?.let {
                trackPrefsStore.recordTrackProgress(it.number)
            }
        }
    }

    LazyColumn(state = listState, modifier = Modifier.fillMaxSize().testTag("doctrine_entry_page")) {
        item {
            InlineBackLink(label = backLabel, onClick = onBack, testTag = "doctrine_entry_back")
        }
        when (kind) {
            "cmd" -> {
                val cmd = repository.theTenCommandments[index.coerceIn(0, repository.theTenCommandments.lastIndex)]
                item {
                    AppScreenTitle(
                        title = TextFormat.withoutOrdinal(cmd.title.get(currentLanguage)),
                        subtitle = currentLanguage.pick(
                            "${TextFormat.ordinalWord(cmd.number, currentLanguage)} mandamiento",
                            "${TextFormat.ordinalWord(cmd.number, currentLanguage)} Commandment",
                            "${TextFormat.ordinalWord(cmd.number, currentLanguage)} mandamento"
                        )
                    )
                }
                item {
                    GutterCard {
                        Text(
                            text = withoutCitation(cmd.biblicalWording.get(currentLanguage)),
                            // Scripture in true italic where the identity prints it so.
                            style = styles.scripture,
                            color = design.palette.textPrimary
                        )
                        scriptureCitation(cmd.biblicalWording.get(currentLanguage))?.let {
                            Spacer(Modifier.height(design.spacing.cardGap / 3))
                            Text(text = eyebrow(it), style = styles.sectionEyebrow, color = design.palette.textSecondary)
                        }
                        Spacer(Modifier.height(design.spacing.cardGap))
                        AppDivider()
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = cmd.moralExplanation.get(currentLanguage),
                            style = MaterialTheme.typography.bodyLarge,
                            color = design.palette.textPrimary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Note(
                            label = currentLanguage.pick("Lo que manda", "What it asks", "O que pede"),
                            body = cmd.virtuesCommanded.get(currentLanguage),
                            labelColor = design.palette.accent
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Note(
                            label = currentLanguage.pick("Lo que prohíbe", "What it forbids", "O que proíbe"),
                            body = cmd.sinsForbidden.get(currentLanguage),
                            labelColor = design.palette.accent
                        )
                    }
                }
            }

            "prc" -> {
                val prc = repository.churchPrecepts[index.coerceIn(0, repository.churchPrecepts.lastIndex)]
                item {
                    AppScreenTitle(
                        title = TextFormat.withoutOrdinal(prc.title.get(currentLanguage)),
                        subtitle = currentLanguage.pick(
                            "Precepto ${TextFormat.roman(prc.number)}",
                            "Precept ${TextFormat.roman(prc.number)}",
                            "Preceito ${TextFormat.roman(prc.number)}"
                        )
                    )
                }
                item {
                    GutterCard {
                        Text(
                            text = prc.explanation.get(currentLanguage),
                            style = MaterialTheme.typography.bodyLarge,
                            color = design.palette.textPrimary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Note(
                            label = currentLanguage.pick("En la práctica", "In practice", "Na prática"),
                            body = prc.applicationInLife.get(currentLanguage),
                            labelColor = design.palette.accent
                        )
                    }
                }
            }

            "vice" -> {
                val v = repository.capitalSinsVsVirtues[index.coerceIn(0, repository.capitalSinsVsVirtues.lastIndex)]
                item {
                    AppScreenTitle(
                        title = v.capitalSin.get(currentLanguage),
                        subtitle = currentLanguage.pick("Vicio capital", "Capital vice", "Vício capital")
                    )
                }
                item {
                    GutterCard {
                        Text(
                            text = v.sinMeaning.get(currentLanguage),
                            style = MaterialTheme.typography.bodyLarge,
                            color = design.palette.textPrimary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        AppDivider()
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Note(
                            label = currentLanguage.pick("La virtud contraria", "The opposing virtue", "A virtude contrária"),
                            body = v.opposingVirtue.get(currentLanguage),
                            labelColor = design.palette.accent,
                            bodyStyle = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Note(
                            label = currentLanguage.pick("Remedio", "Remedy", "Remédio"),
                            body = v.virtueRemedy.get(currentLanguage),
                            labelColor = design.palette.accent
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Note(
                            label = currentLanguage.pick("En la práctica", "In practice", "Na prática"),
                            body = v.practicalAdvice.get(currentLanguage),
                            labelColor = design.palette.accent
                        )
                    }
                }
            }

            "grav" -> {
                val st = repository.sinTheology
                item { AppScreenTitle(title = gravityTitles(currentLanguage)[index.coerceIn(0, 2)]) }
                item {
                    GutterCard {
                        when (index) {
                            0 -> {
                                Note(
                                    label = currentLanguage.pick("Pecado venial", "Venial sin", "Pecado venial"),
                                    body = st.venialExplanation.get(currentLanguage),
                                    labelColor = design.palette.accent
                                )
                                Spacer(Modifier.height(design.spacing.cardGap))
                                Note(
                                    label = currentLanguage.pick("Pecado mortal", "Mortal sin", "Pecado mortal"),
                                    body = st.mortalExplanation.get(currentLanguage),
                                    labelColor = design.palette.accent
                                )
                            }
                            1 -> {
                                Text(
                                    text = currentLanguage.pick(
                                        "Un pecado es mortal solo cuando se dan a la vez estas tres condiciones:",
                                        "A sin is mortal only when all three conditions are met at once:",
                                        "Um pecado é mortal só quando se reúnem ao mesmo tempo estas três condições:"
                                    ),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = design.palette.textPrimary
                                )
                                Spacer(Modifier.height(design.spacing.cardGap))
                                st.mortalThreeConditions.forEachIndexed { i, cond ->
                                    if (i > 0) Spacer(Modifier.height(design.spacing.cardGap / 2))
                                    NumberedLine(i + 1, cond.get(currentLanguage))
                                }
                            }
                            else -> Text(
                                text = st.confessionExamenGuide.get(currentLanguage),
                                style = MaterialTheme.typography.bodyLarge,
                                color = design.palette.textPrimary
                            )
                        }
                    }
                }
            }

            "epoch" -> {
                val epoch = repository.churchHistoryEpochs[index.coerceIn(0, repository.churchHistoryEpochs.lastIndex)]
                item {
                    AppScreenTitle(
                        title = epoch.title.get(currentLanguage),
                        subtitle = TextFormat.ranges(epoch.centuryRange)
                    )
                }
                item { ChurchEpochCard(epoch = epoch, currentLanguage = currentLanguage) }
            }

            "schism" -> {
                val schism = repository.churchSchisms[index.coerceIn(0, repository.churchSchisms.lastIndex)]
                item {
                    AppScreenTitle(title = schism.title.get(currentLanguage), subtitle = schism.historicalYear)
                }
                item { SchismCard(schism = schism, currentLanguage = currentLanguage) }
            }

            "didache" -> {
                val chapters = repository.didacheChapters
                if (chapters.isNotEmpty()) {
                    val chapterIndex = index.coerceIn(0, chapters.lastIndex)
                    val chapter = chapters[chapterIndex]
                    val total = chapters.size
                    item {
                        AppScreenTitle(
                            title = chapter.heading.get(currentLanguage),
                            subtitle = currentLanguage.pick(
                                "Capítulo ${TextFormat.roman(chapter.number)}",
                                "Chapter ${TextFormat.roman(chapter.number)}",
                                "Capítulo ${TextFormat.roman(chapter.number)}"
                            )
                        )
                    }
                    item {
                        val progressLabel = currentLanguage.pick(
                            "Capítulo ${chapterIndex + 1} de $total",
                            "Chapter ${chapterIndex + 1} of $total",
                            "Capítulo ${chapterIndex + 1} de $total"
                        )
                        LinearProgressIndicator(
                            progress = { (chapterIndex + 1).toFloat() / total },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = design.spacing.gutter)
                                .padding(bottom = design.spacing.cardGap)
                                .semantics { stateDescription = progressLabel }
                                .testTag("didache_progress"),
                            color = design.palette.accent,
                            trackColor = design.palette.selectionTint
                        )
                    }
                    if (chapterIndex == 0) {
                        item {
                            GutterCard {
                                Text(
                                    text = eyebrow(currentLanguage.pick("Introducción", "Introduction", "Introdução")),
                                    style = styles.sectionEyebrow,
                                    color = design.palette.textSecondary
                                )
                                Spacer(Modifier.height(design.spacing.cardGap / 2))
                                didacheParagraphs(repository.didacheIntro.get(currentLanguage)).forEachIndexed { i, paragraph ->
                                    if (i > 0) Spacer(Modifier.height(design.spacing.cardGap))
                                    Text(
                                        text = paragraph,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = design.palette.textPrimary
                                    )
                                }
                            }
                        }
                    }
                    item {
                        DidacheReaderCard(
                            chapter = chapter,
                            currentLanguage = currentLanguage,
                            chapterIndex = chapterIndex,
                            includeIntro = chapterIndex == 0,
                            introText = repository.didacheIntro.get(currentLanguage),
                            onOpenPrayer = onOpenPrayer
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = design.spacing.gutter),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AppSecondaryButton(
                                label = currentLanguage.pick("Anterior", "Previous", "Anterior"),
                                onClick = { onOpen("didache:${chapterIndex - 1}") },
                                enabled = chapterIndex > 0,
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("didache_prev_button")
                            )
                            Spacer(Modifier.width(design.spacing.cardGap))
                            AppPrimaryButton(
                                label = currentLanguage.pick("Siguiente", "Next", "Seguinte"),
                                onClick = { onOpen("didache:${chapterIndex + 1}") },
                                enabled = chapterIndex < chapters.lastIndex,
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("didache_next_button")
                            )
                        }
                    }
                }
            }

            "sources" -> {
                item {
                    AppScreenTitle(
                        title = currentLanguage.pick(
                            "Fuentes y método",
                            "Sources & method",
                            "Fontes e método"
                        ),
                        subtitle = currentLanguage.pick(
                            "De dónde sale cada respuesta y cómo leer su señal",
                            "Where each answer comes from and how to read its signal",
                            "De onde vem cada resposta e como ler o seu sinal"
                        )
                    )
                }
                item {
                    Box(Modifier.testTag("sources_page")) {
                        GutterCard {
                            Note(
                                label = currentLanguage.pick(
                                    "Catecismo — texto completo",
                                    "Catechism — full text",
                                    "Catecismo — texto completo"
                                ),
                                body = currentLanguage.pick(
                                    "Catechism of the Catholic Church (CCC), texto completo en vatican.va. Las referencias del tipo «Catecismo, 309-314» enlazan a los párrafos citados en la app.",
                                    "Catechism of the Catholic Church (CCC), full text at vatican.va. References such as \"Catechism, 309-314\" open the cited paragraphs in the app.",
                                    "Catecismo da Igreja Católica (CCC), texto completo em vatican.va. As referências do tipo «Catecismo, 309-314» abrem os parágrafos citados na app."
                                ),
                                labelColor = design.palette.accent
                            )
                            Spacer(Modifier.height(design.spacing.cardGap))
                            Note(
                                label = currentLanguage.pick(
                                    "Didaché — clerus.org",
                                    "Didache — clerus.org",
                                    "Didaché — clerus.org"
                                ),
                                body = currentLanguage.pick(
                                    "Texto de la Didaché según la fuente de clerus.org, reproducido en la sección de Doctrina. Se conserva la traducción y el orden de capítulos de esa fuente.",
                                    "Didache text from the clerus.org source, reproduced in the Doctrine section. The translation and chapter order of that source are kept.",
                                    "Texto da Didaché segundo a fonte clerus.org, reproduzido na secção de Doutrina. Mantêm-se a tradução e a ordem dos capítulos dessa fonte."
                                ),
                                labelColor = design.palette.accent
                            )
                            Spacer(Modifier.height(design.spacing.cardGap))
                            Note(
                                label = currentLanguage.pick(
                                    "Documentos del Vaticano / Vaticano",
                                    "Vatican documents",
                                    "Documentos do Vaticano"
                                ),
                                body = currentLanguage.pick(
                                    "• Mater Populi fidelis (2025)\n• Fiducia supplicans (2023)\n• Humanae vitae\n• Dignitas infinita / Dignitas personae — sobre la dignidad humana",
                                    "• Mater Populi fidelis (2025)\n• Fiducia supplicans (2023)\n• Humanae vitae\n• Dignitas infinita / Dignitas personae — on human dignity",
                                    "• Mater Populi fidelis (2025)\n• Fiducia supplicans (2023)\n• Humanae vitae\n• Dignitas infinita / Dignitas personae — sobre a dignidade humana"
                                ),
                                labelColor = design.palette.accent
                            )
                            Spacer(Modifier.height(design.spacing.cardGap))
                            Note(
                                label = currentLanguage.pick(
                                    "Niveles de fundamento (señal automática)",
                                    "Foundation levels (automatic signal)",
                                    "Níveis de fundamento (sinal automático)"
                                ),
                                body = listOf('A', 'B', 'C', 'D').joinToString("\n") { level ->
                                    "$level · ${foundationLevelLabel(level, currentLanguage)}: ${foundationLevelExplanation(level, currentLanguage)}"
                                },
                                labelColor = design.palette.accent
                            )
                            Spacer(Modifier.height(design.spacing.cardGap))
                            Note(
                                label = currentLanguage.pick(
                                    "Uso honesto",
                                    "Honest use",
                                    "Uso honesto"
                                ),
                                body = currentLanguage.pick(
                                    "La señal de nivel es una heurística automática sobre el texto del Fundamento y la Aclaración: puede no detectar citas en latín, abreviaturas no reconocidas o referencias sin formato «Libro capítulo,versículo». No sustituye la lectura de la fuente original ni la orientación de un sacerdote o catequista.",
                                    "The level signal is an automatic heuristic over the Foundation and Clarification text: it may miss Latin citations, unrecognized abbreviations, or references not in \"Book chapter,verse\" format. It does not replace reading the original source or guidance from a priest or catechist.",
                                    "O sinal de nível é uma heurística automática sobre o texto do Fundamento e do Esclarecimento: pode não detetar citações em latim, abreviaturas não reconhecidas ou referências fora do formato «Livro capítulo,versículo». Não substitui a leitura da fonte original nem a orientação de um sacerdote ou catequista."
                                ),
                                labelColor = design.palette.textSecondary
                            )
                        }
                    }
                }
            }

            "qa" -> {
                val questions = repository.catholicQuestions
                if (questions.isNotEmpty()) {
                    val questionIndex = index.coerceIn(0, questions.lastIndex)
                    val question = questions[questionIndex]
                    val total = questions.size
                    item {
                        AppScreenTitle(
                            title = question.question.get(currentLanguage),
                            subtitle = question.section.get(currentLanguage)
                        )
                    }
                    item {
                        val progressLabel = currentLanguage.pick(
                            "Pregunta ${questionIndex + 1} de $total",
                            "Question ${questionIndex + 1} of $total",
                            "Pergunta ${questionIndex + 1} de $total"
                        )
                        LinearProgressIndicator(
                            progress = { (questionIndex + 1).toFloat() / total },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = design.spacing.gutter)
                                .padding(bottom = design.spacing.cardGap)
                                .semantics { stateDescription = progressLabel }
                                .testTag("catholic_qa_progress"),
                            color = design.palette.accent,
                            trackColor = design.palette.selectionTint
                        )
                    }
                    item {
                        CatholicQuestionReaderCard(
                            question = question,
                            currentLanguage = currentLanguage,
                            questionIndex = questionIndex,
                            repository = repository,
                            onOpenBible = onOpenBible,
                            onOpenCatechism = onOpenCatechism,
                            onOpenPrayer = onOpenPrayer
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = design.spacing.gutter),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AppSecondaryButton(
                                label = currentLanguage.pick("Anterior", "Previous", "Anterior"),
                                onClick = { onOpen("qa:${questionIndex - 1}") },
                                enabled = questionIndex > 0,
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("catholic_qa_prev")
                            )
                            Spacer(Modifier.width(design.spacing.cardGap))
                            AppPrimaryButton(
                                label = currentLanguage.pick("Siguiente", "Next", "Seguinte"),
                                onClick = { onOpen("qa:${questionIndex + 1}") },
                                enabled = questionIndex < questions.lastIndex,
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("catholic_qa_next")
                            )
                        }
                    }
                }
            }

            "order" -> {
                val orders = repository.religiousOrders
                if (orders.isNotEmpty()) {
                    val orderIndex = index.coerceIn(0, orders.lastIndex)
                    val order = orders[orderIndex]
                    val total = orders.size
                    val founderText = order.founder.get(currentLanguage)
                    val meta = listOfNotNull(
                        order.abbreviation.takeIf { it.isNotBlank() },
                        order.foundationYear.takeIf { it.isNotBlank() },
                        founderText.takeIf { it.isNotBlank() }
                    ).joinToString(" · ").takeIf { it.isNotBlank() }
                    item {
                        AppScreenTitle(
                            title = order.name.get(currentLanguage),
                            subtitle = meta
                        )
                    }
                    item {
                        val progressLabel = currentLanguage.pick(
                            "Orden ${orderIndex + 1} de $total",
                            "Order ${orderIndex + 1} of $total",
                            "Ordem ${orderIndex + 1} de $total"
                        )
                        LinearProgressIndicator(
                            progress = { (orderIndex + 1).toFloat() / total },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = design.spacing.gutter)
                                .padding(bottom = design.spacing.cardGap)
                                .semantics { stateDescription = progressLabel }
                                .testTag("order_progress"),
                            color = design.palette.accent,
                            trackColor = design.palette.selectionTint
                        )
                    }
                    item {
                        OrderOfficeReaderCard(
                            title = order.name.get(currentLanguage),
                            description = order.description.get(currentLanguage),
                            charism = order.charism.get(currentLanguage),
                            currentLanguage = currentLanguage,
                            readerIndex = orderIndex,
                            listenTestTag = "order_listen_button"
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = design.spacing.gutter),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AppSecondaryButton(
                                label = currentLanguage.pick("Anterior", "Previous", "Anterior"),
                                onClick = { onOpen("order:${orderIndex - 1}") },
                                enabled = orderIndex > 0,
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("order_prev_button")
                            )
                            Spacer(Modifier.width(design.spacing.cardGap))
                            AppPrimaryButton(
                                label = currentLanguage.pick("Siguiente", "Next", "Seguinte"),
                                onClick = { onOpen("order:${orderIndex + 1}") },
                                enabled = orderIndex < orders.lastIndex,
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("order_next_button")
                            )
                        }
                    }
                }
            }

            "office" -> {
                val offices = repository.churchOffices
                if (offices.isNotEmpty()) {
                    val officeIndex = index.coerceIn(0, offices.lastIndex)
                    val office = offices[officeIndex]
                    val total = offices.size
                    item {
                        AppScreenTitle(title = office.name.get(currentLanguage))
                    }
                    item {
                        val progressLabel = currentLanguage.pick(
                            "Oficio ${officeIndex + 1} de $total",
                            "Office ${officeIndex + 1} of $total",
                            "Ofício ${officeIndex + 1} de $total"
                        )
                        LinearProgressIndicator(
                            progress = { (officeIndex + 1).toFloat() / total },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = design.spacing.gutter)
                                .padding(bottom = design.spacing.cardGap)
                                .semantics { stateDescription = progressLabel }
                                .testTag("office_progress"),
                            color = design.palette.accent,
                            trackColor = design.palette.selectionTint
                        )
                    }
                    item {
                        OrderOfficeReaderCard(
                            title = office.name.get(currentLanguage),
                            description = office.description.get(currentLanguage),
                            charism = null,
                            currentLanguage = currentLanguage,
                            readerIndex = officeIndex,
                            listenTestTag = "office_listen_button"
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = design.spacing.gutter),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AppSecondaryButton(
                                label = currentLanguage.pick("Anterior", "Previous", "Anterior"),
                                onClick = { onOpen("office:${officeIndex - 1}") },
                                enabled = officeIndex > 0,
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("office_prev_button")
                            )
                            Spacer(Modifier.width(design.spacing.cardGap))
                            AppPrimaryButton(
                                label = currentLanguage.pick("Siguiente", "Next", "Seguinte"),
                                onClick = { onOpen("office:${officeIndex + 1}") },
                                enabled = officeIndex < offices.lastIndex,
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("office_next_button")
                            )
                        }
                    }
                }
            }
        }
        // The list ends under a small ornament, never an empty void.
        item { AppOrnament() }
        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// --------------------------------------------------- history and the schisms

/** Didache text arrives with paragraphs separated by blank lines. */
private fun didacheParagraphs(text: String): List<String> =
    text.split(Regex("\\n\\s*\\n")).map { it.trim() }.filter { it.isNotEmpty() }

/** Shares plain text through the system chooser; a missing handler is a no-op. */
private fun sharePlainText(context: Context, text: String) {
    runCatching {
        context.startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                },
                null
            )
        )
    }
}

/**
 * One religious order or Church office as a reading card: description, then
 * the charism where there is one, with a Listen/Stop read-aloud button.
 * Like the Didache reader, this screen receives no shared ttsManager, so
 * the card owns a repository-independent [LiturgicalTtsManager] for its
 * lifetime only.
 */
@Composable
private fun OrderOfficeReaderCard(
    title: String,
    description: String,
    charism: String?,
    currentLanguage: AppLanguage,
    readerIndex: Int,
    listenTestTag: String
) {
    val design = LocalAppDesign.current
    val context = LocalContext.current
    val ttsManager = remember { LiturgicalTtsManager(context) }
    val isPlaying by ttsManager.isPlaying.collectAsState()
    DisposableEffect(ttsManager) { onDispose { ttsManager.release() } }
    LaunchedEffect(currentLanguage) { ttsManager.setLanguage(currentLanguage) }
    LaunchedEffect(readerIndex) { ttsManager.stop() }

    val charismLabel = currentLanguage.pick("Carisma", "Charism", "Carisma")
    val hasCharism = !charism.isNullOrBlank()
    val speakText = buildString {
        append(title)
        append("\n\n")
        append(description)
        if (hasCharism) {
            append("\n\n")
            append(charismLabel)
            append(": ")
            append(charism)
        }
    }

    GutterCard {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = design.palette.textPrimary
        )
        if (hasCharism) {
            Spacer(Modifier.height(design.spacing.cardGap))
            AppDivider()
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = charismLabel,
                body = charism.orEmpty(),
                labelColor = design.palette.accent
            )
        }
        Spacer(Modifier.height(design.spacing.cardGap))
        AppDivider()
        Spacer(Modifier.height(design.spacing.cardGap))
        // While the voice is running the same 48dp button offers Stop, so
        // recitation can be ended without leaving the page.
        AppSecondaryButton(
            label = if (isPlaying) {
                currentLanguage.pick("Detener", "Stop", "Parar")
            } else {
                currentLanguage.pick("Escuchar", "Listen", "Ouvir")
            },
            onClick = if (isPlaying) {
                { ttsManager.stop() }
            } else {
                { ttsManager.speakSingle(speakText) }
            },
            leadingIcon = if (isPlaying) Icons.Default.Stop else Icons.Default.VolumeUp,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .testTag(listenTestTag)
        )
    }
}

/**
 * One Didache chapter as a reading card: paragraphs in the scripture style
 * inside the reading-width card, with a Listen/Stop read-aloud button.
 * This screen receives no shared ttsManager, so the card owns a
 * repository-independent [LiturgicalTtsManager] for its lifetime only.
 */
@Composable
private fun DidacheReaderCard(
    chapter: DidacheChapter,
    currentLanguage: AppLanguage,
    chapterIndex: Int,
    includeIntro: Boolean,
    introText: String,
    onOpenPrayer: (prayerId: String) -> Unit = {}
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val ttsManager = remember { LiturgicalTtsManager(context) }
    val isPlaying by ttsManager.isPlaying.collectAsState()
    DisposableEffect(ttsManager) { onDispose { ttsManager.release() } }
    LaunchedEffect(currentLanguage) { ttsManager.setLanguage(currentLanguage) }
    LaunchedEffect(chapterIndex) { ttsManager.stop() }

    val bodyText = chapter.body.get(currentLanguage)
    val speakText = buildString {
        append(chapter.heading.get(currentLanguage))
        if (includeIntro && introText.isNotBlank()) {
            append("\n\n")
            append(introText)
        }
        append("\n\n")
        append(bodyText)
    }
    // Shared text is the chapter itself (heading + body), not the read-aloud
    // script, so the intro is not duplicated into the shared message.
    val shareText = buildString {
        append(chapter.heading.get(currentLanguage))
        append("\n\n")
        append(bodyText)
    }

    GutterCard {
        didacheParagraphs(bodyText).forEachIndexed { i, paragraph ->
            if (i > 0) Spacer(Modifier.height(design.spacing.cardGap))
            Text(
                text = paragraph,
                style = styles.scripture,
                color = design.palette.textPrimary
            )
        }
        Spacer(Modifier.height(design.spacing.cardGap))
        AppDivider()
        Spacer(Modifier.height(design.spacing.cardGap))
        // Reading closes the loop: the chapter's related prayer opens in the
        // Prayers tab. Primary button, 48dp, above Listen/Share.
        AppPrimaryButton(
            label = currentLanguage.pick("Rezar ahora", "Pray now", "Rezar agora"),
            onClick = { onOpenPrayer(didacheRelatedPrayerId(chapter.number)) },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .testTag("pray_now")
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        // While the voice is running the same 48dp button offers Stop, so
        // recitation can be ended without leaving the page.
        AppSecondaryButton(
            label = if (isPlaying) {
                currentLanguage.pick("Detener", "Stop", "Parar")
            } else {
                currentLanguage.pick("Escuchar", "Listen", "Ouvir")
            },
            onClick = if (isPlaying) {
                { ttsManager.stop() }
            } else {
                { ttsManager.speakSingle(speakText) }
            },
            leadingIcon = if (isPlaying) Icons.Default.Stop else Icons.Default.VolumeUp,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .testTag("didache_listen_button")
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        AppSecondaryButton(
            label = currentLanguage.pick("Compartir", "Share", "Partilhar"),
            onClick = { sharePlainText(context, shareText) },
            leadingIcon = Icons.Default.Share,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .testTag("didache_share")
        )
    }
}

/**
 * One apologetics question as a reading card: the answer first in the
 * primary treatment, then the foundation and the clarification, each with
 * its own tonal panel. Like the Didache reader, this screen receives no
 * shared ttsManager, so the card owns a [LiturgicalTtsManager] for its
 * lifetime only.
 */
@Composable
private fun CatholicQuestionReaderCard(
    question: ApologeticsQuestion,
    currentLanguage: AppLanguage,
    questionIndex: Int,
    repository: CatholicDataRepository? = null,
    onOpenBible: (bookId: String, chapter: Int, verseStart: Int?) -> Unit = { _, _, _ -> },
    onOpenCatechism: (paragraph: Int) -> Unit = {},
    onOpenPrayer: (prayerId: String) -> Unit = {}
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val ttsManager = remember { LiturgicalTtsManager(context) }
    val isPlaying by ttsManager.isPlaying.collectAsState()
    DisposableEffect(ttsManager) { onDispose { ttsManager.release() } }
    LaunchedEffect(currentLanguage) { ttsManager.setLanguage(currentLanguage) }
    LaunchedEffect(questionIndex) { ttsManager.stop() }

    val answerLabel = currentLanguage.pick("Respuesta", "Answer", "Resposta")
    val foundationLabel = currentLanguage.pick("Fundamento", "Foundation", "Fundamento")
    val clarificationLabel = currentLanguage.pick("Aclaración", "Clarification", "Esclarecimento")

    val answerText = question.answer.get(currentLanguage)
    val foundationText = question.foundation.get(currentLanguage)
    val clarificationText = question.clarification.get(currentLanguage)
    // The foundation reference the reader has opened inline, if any; keyed to
    // the question so a new page starts with every reference collapsed.
    var expandedRef by remember(question.number, foundationText, currentLanguage) {
        mutableStateOf<FoundationRef?>(null)
    }
    // Parsed once here so the foundation-level badge and the reference rows
    // below read the same refs — the badge never disagrees with what is
    // actually linked.
    val foundationRefs = remember(foundationText, currentLanguage, repository?.catholicBooks) {
        repository?.let { parseFoundation(foundationText, currentLanguage, it.catholicBooks) }
            ?: emptyList()
    }
    val foundationLevel = remember(foundationRefs, clarificationText) {
        foundationLevelFor(foundationRefs, clarificationText)
    }

    val speakText = buildString {
        append(question.question.get(currentLanguage))
        append("\n\n")
        append(answerLabel)
        append(": ")
        append(answerText)
        if (foundationText.isNotBlank()) {
            append("\n\n")
            append(foundationLabel)
            append(": ")
            append(foundationText)
        }
        if (clarificationText.isNotBlank()) {
            append("\n\n")
            append(clarificationLabel)
            append(": ")
            append(clarificationText)
        }
    }
    // Shared text is question + answer + foundation (the clarification is left
    // for the in-app reader, keeping the shared message to the core teaching).
    val shareText = buildString {
        append(question.question.get(currentLanguage))
        append("\n\n")
        append(answerText)
        if (foundationText.isNotBlank()) {
            append("\n\n")
            append(foundationText)
        }
    }

    GutterCard {
        // Respuesta: the direct answer, primary type on the card itself.
        Note(
            label = answerLabel,
            body = answerText,
            labelColor = design.palette.accent
        )
        // Fundamento: the scriptural/doctrinal ground, scripture type on
        // the accentMuted tonal panel with a gold label.
        if (foundationText.isNotBlank()) {
            Spacer(Modifier.height(design.spacing.cardGap))
            AppDivider()
            Spacer(Modifier.height(design.spacing.cardGap))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(design.palette.accentMuted, design.shapes.card)
                    .padding(design.spacing.cardPadding)
            ) {
                Note(
                    label = foundationLabel,
                    body = foundationText,
                    labelColor = design.palette.gold,
                    bodyStyle = styles.scripture
                )
            }
            // Deep links as their own reference group below the panel (not
            // chips over the text): one tappable row per Bible/CCC passage.
            if (repository != null) {
                val refs = foundationRefs
                if (refs.isNotEmpty()) {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    Text(
                        text = currentLanguage.pick(
                            "Referencias — toca para profundizar",
                            "References — tap to go deeper",
                            "Referências — toque para aprofundar"
                        ),
                        style = styles.sectionEyebrow,
                        color = design.palette.textSecondary
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    val tapToReadHint = currentLanguage.pick(
                        "Toca para leer",
                        "Tap to read",
                        "Toque para ler"
                    )
                    AppListGroup {
                        refs.forEach { ref ->
                            val expanded = expandedRef == ref
                            val refKey = when (ref) {
                                is FoundationRef.BibleRef -> {
                                    val r = ref.reference
                                    "bible_${r.bookId}_${r.chapter}_${r.verseStart ?: "all"}_${r.verseEnd ?: ""}"
                                }
                                is FoundationRef.CatechismRef ->
                                    "ccc_${ref.startParagraph}_${ref.endParagraph ?: ""}"
                            }
                            Column(Modifier.fillMaxWidth()) {
                                when (ref) {
                                    is FoundationRef.BibleRef -> {
                                        val r = ref.reference
                                        val bookName = repository.catholicBooks
                                            .firstOrNull { it.id == r.bookId }
                                            ?.name?.get(currentLanguage) ?: r.bookId
                                        AppListRow(
                                            title = bookName + " " + r.chapter +
                                                (r.verseStart?.let { ", $it" } ?: "") +
                                                (r.verseEnd?.let { "-$it" } ?: ""),
                                            subtitle = if (expanded) null else tapToReadHint,
                                            leadingIcon = Icons.Default.MenuBook,
                                            showsNavigationAffordance = true,
                                            selected = expanded,
                                            onClick = {
                                                expandedRef = if (expanded) null else ref
                                            },
                                            modifier = Modifier
                                                .heightIn(min = MinTouchTarget)
                                                .testTag("foundation_ref_$refKey")
                                        )
                                        if (expanded) {
                                            val chapter = repository.getChapter(r.bookId, r.chapter, currentLanguage)
                                            val selectedVerses = run {
                                                val start = r.verseStart
                                                if (start == null) {
                                                    chapter.verses
                                                } else {
                                                    chapter.verses.filter {
                                                        it.number in start..(r.verseEnd ?: start)
                                                    }
                                                }
                                            }
                                            if (selectedVerses.isNotEmpty()) {
                                                Spacer(Modifier.height(design.spacing.cardGap / 2))
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .background(design.palette.accentMuted, design.shapes.card)
                                                        .padding(design.spacing.cardPadding)
                                                        .testTag("foundation_ref_text_$refKey")
                                                ) {
                                                    Text(
                                                        text = selectedVerses.joinToString("\n\n") { verse ->
                                                            "${verse.number}  ${verse.text.get(currentLanguage)}"
                                                        },
                                                        style = styles.scripture,
                                                        color = design.palette.textPrimary
                                                    )
                                                }
                                                Spacer(Modifier.height(design.spacing.cardGap / 2))
                                            }
                                            AppPrimaryButton(
                                                label = currentLanguage.pick(
                                                    "Abrir en la Biblia",
                                                    "Open in the Bible",
                                                    "Abrir na Bíblia"
                                                ),
                                                onClick = { onOpenBible(r.bookId, r.chapter, r.verseStart) },
                                                leadingIcon = Icons.Default.MenuBook,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .heightIn(min = MinTouchTarget)
                                                    .testTag("foundation_ref_open_$refKey")
                                            )
                                        }
                                    }

                                    is FoundationRef.CatechismRef -> {
                                        AppListRow(
                                            title = currentLanguage.pick(
                                                "Catecismo ${ref.startParagraph}" +
                                                    (ref.endParagraph?.let { "-$it" } ?: ""),
                                                "Catechism ${ref.startParagraph}" +
                                                    (ref.endParagraph?.let { "-$it" } ?: ""),
                                                "Catecismo ${ref.startParagraph}" +
                                                    (ref.endParagraph?.let { "-$it" } ?: "")
                                            ),
                                            subtitle = if (expanded) null else tapToReadHint,
                                            leadingIcon = Icons.Default.AutoStories,
                                            showsNavigationAffordance = true,
                                            selected = expanded,
                                            onClick = {
                                                expandedRef = if (expanded) null else ref
                                            },
                                            modifier = Modifier
                                                .heightIn(min = MinTouchTarget)
                                                .testTag("foundation_ref_$refKey")
                                        )
                                        if (expanded) {
                                            val paragraphText = (ref.startParagraph..(ref.endParagraph ?: ref.startParagraph))
                                                .mapNotNull { number ->
                                                    repository.catechismParagraphText(number, currentLanguage)
                                                        ?.trim()
                                                        ?.takeIf { it.isNotBlank() }
                                                }
                                                .joinToString("\n\n")
                                            if (paragraphText.isNotBlank()) {
                                                Spacer(Modifier.height(design.spacing.cardGap / 2))
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .background(design.palette.accentMuted, design.shapes.card)
                                                        .padding(design.spacing.cardPadding)
                                                        .testTag("foundation_ref_text_$refKey")
                                                ) {
                                                    Text(
                                                        text = paragraphText,
                                                        style = styles.scripture,
                                                        color = design.palette.textPrimary
                                                    )
                                                }
                                                Spacer(Modifier.height(design.spacing.cardGap / 2))
                                            }
                                            AppPrimaryButton(
                                                label = currentLanguage.pick(
                                                    "Abrir en el Catecismo",
                                                    "Open in the Catechism",
                                                    "Abrir no Catecismo"
                                                ),
                                                onClick = { onOpenCatechism(ref.startParagraph) },
                                                leadingIcon = Icons.Default.AutoStories,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .heightIn(min = MinTouchTarget)
                                                    .testTag("foundation_ref_open_$refKey")
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // Aclaración: the nuance, smaller type on the neutral selectionTint
        // panel with a secondary label, so it sits apart from the answer.
        // The foundation-level badge rides in this panel's header area: when
        // there is no clarification text the badge still shows on its own, in
        // the same place, so the signal is never silently absent.
        if (clarificationText.isNotBlank()) {
            Spacer(Modifier.height(design.spacing.cardGap))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(design.palette.selectionTint, design.shapes.card)
                    .padding(design.spacing.cardPadding)
            ) {
                Column(Modifier.fillMaxWidth()) {
                    FoundationLevelBadge(level = foundationLevel, currentLanguage = currentLanguage)
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Note(
                        label = clarificationLabel,
                        body = clarificationText,
                        labelColor = design.palette.textSecondary,
                        bodyStyle = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        } else {
            Spacer(Modifier.height(design.spacing.cardGap))
            FoundationLevelBadge(level = foundationLevel, currentLanguage = currentLanguage)
        }
        Spacer(Modifier.height(design.spacing.cardGap))
        AppDivider()
        Spacer(Modifier.height(design.spacing.cardGap))
        // Reading closes the loop: the question's related prayer opens in the
        // Prayers tab. Primary button, 48dp, above Listen/Share.
        AppPrimaryButton(
            label = currentLanguage.pick("Rezar ahora", "Pray now", "Rezar agora"),
            onClick = { onOpenPrayer(qaRelatedPrayerId(question.number)) },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .testTag("pray_now")
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        // While the voice is running the same 48dp button offers Stop, so
        // recitation can be ended without leaving the page.
        AppSecondaryButton(
            label = if (isPlaying) {
                currentLanguage.pick("Detener", "Stop", "Parar")
            } else {
                currentLanguage.pick("Escuchar", "Listen", "Ouvir")
            },
            onClick = if (isPlaying) {
                { ttsManager.stop() }
            } else {
                { ttsManager.speakSingle(speakText) }
            },
            leadingIcon = if (isPlaying) Icons.Default.Stop else Icons.Default.VolumeUp,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .testTag("catholic_qa_listen_button")
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        AppSecondaryButton(
            label = currentLanguage.pick("Compartir", "Share", "Partilhar"),
            onClick = { sharePlainText(context, shareText) },
            leadingIcon = Icons.Default.Share,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .testTag("catholic_qa_share")
        )
    }
}

/**
 * The `VaticanPrayer` id related to a 101-Questions entry, by question
 * number (the ten-per-section data order is stable, so this never depends on
 * matching displayed text in any language).
 */
private fun qaRelatedPrayerId(questionNumber: Int): String = when (questionNumber) {
    in 1..10 -> "act_of_hope"
    in 11..20 -> "come_holy_spirit"
    in 21..30 -> "sign_of_the_cross"
    in 31..40 -> "memorare"
    in 41..50 -> "creed"
    in 51..60 -> "act_of_contrition"
    in 61..70 -> "eternal_rest"
    in 71..80 -> "st_joseph"
    in 81..90 -> "peace_prayer"
    in 91..100 -> "suscipe_prayer"
    else -> "our_father"
}

/**
 * The `VaticanPrayer` id related to a Didache chapter, by chapter number.
 * Chapter 8 (fasting and prayer) carries the Lord's Prayer in the Didache
 * itself, so it opens the Our Father.
 */
private fun didacheRelatedPrayerId(chapterNumber: Int): String = when (chapterNumber) {
    7 -> "sign_of_the_cross"
    8 -> "our_father"
    9, 10 -> "adoro_te_devote"
    11 -> "come_holy_spirit"
    12, 13 -> "act_of_charity"
    14 -> "te_deum"
    15, 16 -> "creed"
    else -> "act_of_contrition"
}

@Composable
private fun ChurchHistoryPane(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onOpen: (String) -> Unit,
    header: LazyListScope.() -> Unit
) {
    val design = LocalAppDesign.current

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            AppSectionHeader(
                title = currentLanguage.pick("Línea de tiempo", "Timeline", "Linha do tempo")
            )
            AppListGroup {
                repository.churchHistoryEpochs.forEachIndexed { i, epoch ->
                    AppListRow(
                        title = epoch.title.get(currentLanguage),
                        subtitle = TextFormat.ranges(epoch.centuryRange),
                        onClick = { onOpen("epoch:$i") },
                        modifier = Modifier.heightIn(min = MinTouchTarget)
                    )
                }
            }
        }

        item {
            AppSectionHeader(
                title = currentLanguage.pick("Los grandes cismas", "The great schisms", "Os grandes cismas")
            )
            AppListGroup {
                repository.churchSchisms.forEachIndexed { i, schism ->
                    AppListRow(
                        title = schism.title.get(currentLanguage),
                        subtitle = schism.historicalYear,
                        onClick = { onOpen("schism:$i") },
                        modifier = Modifier.heightIn(min = MinTouchTarget)
                    )
                }
            }
        }

        // The list ends under a small ornament, never an empty void.
        item { AppOrnament() }
        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

@Composable
private fun ChurchEpochCard(epoch: ChurchHistoryEpoch, currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current

    GutterCard {
        Text(
            text = epoch.narrative.get(currentLanguage),
            style = MaterialTheme.typography.bodyLarge,
            color = design.palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick("Papas claves", "Key popes", "Papas principais"),
            body = epoch.keyPopes.get(currentLanguage)
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick("Concilios", "Councils", "Concílios"),
            body = epoch.ecumenicalCouncils.get(currentLanguage)
        )
    }
}

@Composable
private fun SchismCard(schism: ChurchSchismHistory, currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current

    GutterCard {
        Text(
            text = schism.context.get(currentLanguage),
            style = MaterialTheme.typography.bodyLarge,
            color = design.palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick(
                "Diferencias doctrinales claves",
                "Key doctrinal differences",
                "Diferenças doutrinais principais"
            ),
            body = schism.theologicalDifferences.get(currentLanguage)
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick(
                "Perspectiva y diálogo",
                "Perspective and dialogue",
                "Perspetiva e diálogo"
            ),
            body = schism.dialogueAndReconciliation.get(currentLanguage)
        )
    }
}

// ---------------------------------------------------- parishes and the feed

/** Vatican news as list items: loading, failure with a Vatican News link, or the feed. */
private fun LazyListScope.newsItems(news: NewsResult?, currentLanguage: AppLanguage) {
    when (news) {
        null -> item {
            val design = LocalAppDesign.current
            // Loading fills the viewport height via fillParentMaxHeight
            // (LazyItemScope): fillMaxSize inside a LazyColumn item only wraps
            // content and stays top-pinned. Content (spinner + label) is
            // unchanged and centered on both axes.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillParentMaxHeight()
                    .testTag("news_loading"),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(design.decor.rowIconSize),
                        color = design.palette.accent
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap))
                    Text(
                        text = currentLanguage.pick(
                            "Cargando noticias del Vaticano…",
                            "Loading news from the Vatican…",
                            "A carregar notícias do Vaticano…"
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = design.palette.textSecondary
                    )
                }
            }
        }

        is NewsResult.Failure -> item {
            val context = LocalContext.current
            val design = LocalAppDesign.current
            AppEmptyState(
                title = currentLanguage.pick(
                    "Noticias no disponibles",
                    "News unavailable",
                    "Notícias indisponíveis"
                ),
                body = currentLanguage.pick(
                    "Comprueba tu conexión y vuelve a intentarlo.",
                    "Check your connection and try again.",
                    "Verifique a ligação e tente de novo."
                ),
                icon = Icons.Default.Newspaper
            )
            AppSecondaryButton(
                label = currentLanguage.pick("Abrir Vatican News", "Open Vatican News", "Abrir o Vatican News"),
                onClick = {
                    runCatching {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vaticannews.va"))
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = MinTouchTarget)
                    .padding(horizontal = design.spacing.gutter)
            )
        }

        is NewsResult.Success -> items(news.items, key = { it.link }) { article ->
            val design = LocalAppDesign.current
            VaticanNewsCard(article = article, currentLanguage = currentLanguage)
            Spacer(Modifier.height(design.spacing.cardGap))
        }
    }
}



@Composable
private fun ParishesAndNewsPane(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    header: LazyListScope.() -> Unit,
    newsOnly: Boolean = false
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current

    // The screen's one selector: the directory or the news. In newsOnly
    // (directoryOnly) mode the selector and directory are gone and the
    // Vatican feed is the whole pane.
    var section by remember { mutableIntStateOf(if (newsOnly) 1 else 0) }
    var openParishId by remember { mutableStateOf<String?>(null) }
    var parishSearchQuery by remember { mutableStateOf("") }
    // 0: all, 1: confessions, 2: adoration, 3: cathedrals.
    var selectedParishFilter by remember { mutableIntStateOf(0) }
    var selectedCountry by remember { mutableStateOf(ANY_PLACE) }
    var selectedRegion by remember { mutableStateOf(ANY_PLACE) }
    var selectedCity by remember { mutableStateOf(ANY_PLACE) }

    // Every level of the cascade is drawn from the directory itself, so no
    // choice can lead to an empty region or city list.
    val allCountries = remember(repository) {
        listOf(ANY_PLACE) + repository.nearbyParishes
            .map { it.country }
            .filter { it.isNotBlank() }
            .distinct()
            .sorted()
    }
    val availableRegions = remember(selectedCountry) {
        val scoped =
            if (selectedCountry == ANY_PLACE) repository.nearbyParishes
            else repository.nearbyParishes.filter { it.country == selectedCountry }
        listOf(ANY_PLACE) + scoped
            .map { it.stateOrRegion }
            .filter { it.isNotBlank() }
            .distinct()
            .sorted()
    }
    val availableCities = remember(selectedCountry, selectedRegion) {
        var list = repository.nearbyParishes
        if (selectedCountry != ANY_PLACE) list = list.filter { it.country == selectedCountry }
        if (selectedRegion != ANY_PLACE) list = list.filter { it.stateOrRegion == selectedRegion }
        listOf(ANY_PLACE) + list
            .map { it.city }
            .filter { it.isNotBlank() }
            .distinct()
            .sorted()
    }

    // A narrower choice above must not leave a stale one below it.
    LaunchedEffect(selectedCountry) {
        if (selectedRegion !in availableRegions) selectedRegion = ANY_PLACE
        if (selectedCity !in availableCities) selectedCity = ANY_PLACE
    }
    LaunchedEffect(selectedRegion) {
        if (selectedCity !in availableCities) selectedCity = ANY_PLACE
    }

    val filteredParishes = repository.nearbyParishes.filter { parish ->
        val matchesQuery = parish.name.contains(parishSearchQuery, ignoreCase = true) ||
                parish.diocese.contains(parishSearchQuery, ignoreCase = true) ||
                parish.address.contains(parishSearchQuery, ignoreCase = true) ||
                parish.city.contains(parishSearchQuery, ignoreCase = true) ||
                parish.country.contains(parishSearchQuery, ignoreCase = true) ||
                ParishScheduleText.country(parish.country, currentLanguage)
                    .contains(parishSearchQuery, ignoreCase = true)
        val matchesCountry = selectedCountry == ANY_PLACE || parish.country == selectedCountry
        val matchesRegion = selectedRegion == ANY_PLACE || parish.stateOrRegion == selectedRegion
        val matchesCity = selectedCity == ANY_PLACE || parish.city == selectedCity
        // These read the bundled directory's own wording, not anything shown
        // to the reader, so they are not translated.
        val matchesFilter = when (selectedParishFilter) {
            1 -> !parish.confessionTimes.contains("No disponible", ignoreCase = true)
            2 -> parish.isPerpetualAdoration ||
                    parish.adorationTimes.contains("24", ignoreCase = true) ||
                    parish.adorationTimes.contains("Perpetua", ignoreCase = true) ||
                    parish.adorationTimes.contains("Jueves", ignoreCase = true)
            3 -> parish.name.contains("Catedral", ignoreCase = true) ||
                    parish.name.contains("Basílica", ignoreCase = true) ||
                    parish.name.contains("Cathedral", ignoreCase = true) ||
                    parish.name.contains("Cathédrale", ignoreCase = true) ||
                    parish.name.contains("Basilica", ignoreCase = true)
            else -> true
        }
        matchesQuery && matchesCountry && matchesRegion && matchesCity && matchesFilter
    }

    // Live from the Vatican's own feed, fetched once per language.
    val newsState by produceState<NewsResult?>(
        initialValue = null,
        key1 = currentLanguage
    ) {
        value = VaticanNewsFetcher().latest(currentLanguage, limit = 25)
    }

    val openParish = openParishId?.let { id -> repository.nearbyParishes.firstOrNull { it.id == id } }
    if (openParish != null) {
        BackHandler { openParishId = null }
        ParishDetail(
            parish = openParish,
            currentLanguage = currentLanguage,
            onBack = { openParishId = null }
        )
        return
    }

    val anyLabel = currentLanguage.pick("Todos", "All", "Todos")

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        if (!newsOnly) item(key = "churches_news_tabs") {
            AppSegmentedTabs(
                options = listOf(
                    currentLanguage.pick("Iglesias", "Churches", "Igrejas"),
                    currentLanguage.pick("Noticias", "News", "Notícias")
                ),
                selectedIndex = section,
                onSelect = { section = it },
                modifier = Modifier
                    .padding(horizontal = tabStripInset())
                    .heightIn(min = MinTouchTarget)
                    .testTag("churches_news_tabs")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        if (section == 0) {
            item {
                AppSearchField(
                    value = parishSearchQuery,
                    onValueChange = { parishSearchQuery = it },
                    placeholder = currentLanguage.pick(
                        "Nombre, ciudad o diócesis",
                        "Name, city or diocese",
                        "Nome, cidade ou diocese"
                    ),
                    modifier = Modifier
                        .padding(horizontal = design.spacing.gutter)
                        .heightIn(min = MinTouchTarget)
                        .testTag("parish_search_input")
                )
                Spacer(Modifier.height(design.spacing.cardGap))
            }

            // Where and what, as rows with menus: one group, no chip rows.
            item {
                AppListGroup {
                    MenuPickerRow(
                        title = currentLanguage.pick("País", "Country", "País"),
                        options = allCountries.map {
                            it to if (it == ANY_PLACE) anyLabel else ParishScheduleText.country(it, currentLanguage)
                        },
                        selected = selectedCountry,
                        onSelect = {
                            selectedCountry = it
                            selectedRegion = ANY_PLACE
                            selectedCity = ANY_PLACE
                        },
                        testTag = "parish_country_dropdown"
                    )
                    if (selectedCountry != ANY_PLACE) {
                        MenuPickerRow(
                            title = currentLanguage.pick("Región", "Region", "Região"),
                            options = availableRegions.map {
                                it to if (it == ANY_PLACE) currentLanguage.pick("Todas", "All", "Todas") else it
                            },
                            selected = selectedRegion,
                            onSelect = {
                                selectedRegion = it
                                selectedCity = ANY_PLACE
                            },
                            testTag = "parish_region_dropdown"
                        )
                    }
                    MenuPickerRow(
                        title = currentLanguage.pick("Ciudad", "City", "Cidade"),
                        options = availableCities.map {
                            it to if (it == ANY_PLACE) currentLanguage.pick("Todas", "All", "Todas") else it
                        },
                        selected = selectedCity,
                        onSelect = { selectedCity = it },
                        testTag = "parish_city_dropdown"
                    )
                    MenuPickerRow(
                        title = currentLanguage.pick("Mostrar", "Show", "Mostrar"),
                        options = listOf(
                            0 to currentLanguage.pick("Todas", "All churches", "Todas"),
                            1 to currentLanguage.pick("Con confesiones", "With confessions", "Com confissões"),
                            2 to currentLanguage.pick("Con adoración", "With adoration", "Com adoração"),
                            3 to currentLanguage.pick("Catedrales y basílicas", "Cathedrals & basilicas", "Catedrais e basílicas")
                        ),
                        selected = selectedParishFilter,
                        onSelect = { selectedParishFilter = it },
                        testTag = "parish_kind_picker"
                    )
                }
            }

            if (filteredParishes.isEmpty()) {
                item {
                    AppEmptyState(
                        title = currentLanguage.pick("Ninguna iglesia coincide", "No matching churches", "Nenhuma igreja corresponde"),
                        body = currentLanguage.pick(
                            "Prueba otro nombre o amplía el lugar.",
                            "Try another name or widen the place.",
                            "Tente outro nome ou amplie o lugar."
                        ),
                        icon = Icons.Default.Church
                    )
                    AppSecondaryButton(
                        label = currentLanguage.pick("Ver todas", "Show all churches", "Ver todas"),
                        onClick = {
                            parishSearchQuery = ""
                            selectedParishFilter = 0
                            selectedCountry = ANY_PLACE
                            selectedRegion = ANY_PLACE
                            selectedCity = ANY_PLACE
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .padding(horizontal = design.spacing.gutter)
                    )
                }
            } else {
                item(key = "parish_results") {
                    AppSectionHeader(
                        title = currentLanguage.pick("Iglesias", "Churches", "Igrejas")
                    )
                    AppListGroup {
                        filteredParishes.forEach { parish ->
                            AppListRow(
                                title = parish.name,
                                subtitle = "${parish.city}, ${ParishScheduleText.country(parish.country, currentLanguage)}",
                                onClick = { openParishId = parish.id },
                                modifier = Modifier.heightIn(min = MinTouchTarget).testTag("parish_card_${parish.id}")
                            )
                        }
                    }
                }
            }

            item {
                Spacer(Modifier.height(design.spacing.sectionGap / 2))
                AppSecondaryButton(
                    label = currentLanguage.pick("Buscar en Google Maps", "Search Google Maps", "Buscar no Google Maps"),
                    leadingIcon = Icons.Default.Map,
                    onClick = {
                        val query = listOf(selectedCity, selectedRegion, selectedCountry)
                            .filter { it != ANY_PLACE }
                            .joinToString(", ")
                        context.openMaps(
                            "https://www.google.com/maps/search/" +
                                Uri.encode("catholic church $query".trim())
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = MinTouchTarget)
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("parish_open_maps")
                )
            }
        } else {
            newsItems(newsState, currentLanguage)
        }

        // The list ends under a small ornament, never an empty void.
        item { AppOrnament() }
        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/** A church as its own page: where it is, its hours, and how to reach it. */
@Composable
private fun ParishDetail(
    parish: NearbyParish,
    currentLanguage: AppLanguage,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current

    fun hours(text: String) = TextFormat.times(text, currentLanguage)

    LazyColumn(Modifier.fillMaxSize().testTag("parish_detail_${parish.id}")) {
        item {
            InlineBackLink(
                label = currentLanguage.pick("Iglesias", "Churches", "Igrejas"),
                onClick = onBack,
                testTag = "back_to_churches_button"
            )
        }
        item {
            AppScreenTitle(
                title = parish.name,
                subtitle = "${parish.city}, ${ParishScheduleText.country(parish.country, currentLanguage)}"
            )
        }
        item {
            GutterCard {
                if (parish.isPerpetualAdoration) {
                    Text(
                        text = eyebrow(
                            currentLanguage.pick("Adoración perpetua", "Perpetual adoration", "Adoração perpétua")
                        ),
                        style = LocalAppTextStyles.current.sectionEyebrow,
                        color = design.palette.accent
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                }
                Note(
                    label = currentLanguage.pick("Misas del domingo", "Sunday Mass", "Missas de domingo"),
                    body = hours(ParishScheduleText.masses(parish.sundayMasses, currentLanguage))
                )
                if (parish.weekdayMasses.isNotBlank()) {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    Note(
                        label = currentLanguage.pick("Misas entre semana", "Weekday Mass", "Missas durante a semana"),
                        body = hours(ParishScheduleText.masses(parish.weekdayMasses, currentLanguage))
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
                Note(
                    label = currentLanguage.pick("Confesiones", "Confessions", "Confissões"),
                    body = hours(ParishScheduleText.note(parish.confessionTimes, currentLanguage))
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Note(
                    label = currentLanguage.pick("Adoración", "Adoration", "Adoração"),
                    body = hours(ParishScheduleText.note(parish.adorationTimes, currentLanguage))
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Note(
                    label = currentLanguage.pick("Diócesis", "Diocese", "Diocese"),
                    body = parish.diocese
                )
            }
        }
        item {
            Spacer(Modifier.height(design.spacing.cardGap))
            AppListGroup {
                AppListRow(
                    title = currentLanguage.pick("Cómo llegar", "Directions", "Como chegar"),
                    subtitle = parish.address.ifBlank { null },
                    leadingIcon = Icons.Default.Directions,
                    onClick = {
                        val target = Uri.encode("${parish.name}, ${parish.address}")
                        if (!context.openMaps("geo:0,0?q=$target")) {
                            Toast.makeText(context, parish.address, Toast.LENGTH_LONG).show()
                        }
                    },
                    modifier = Modifier.heightIn(min = MinTouchTarget).testTag("map_parish_${parish.id}")
                )
                if (parish.contactPhone.isNotBlank()) {
                    AppListRow(
                        title = currentLanguage.pick("Llamar", "Call", "Ligar"),
                        subtitle = parish.contactPhone,
                        leadingIcon = Icons.Default.Call,
                        onClick = {
                            val phone = parish.contactPhone.replace(" ", "")
                            val dialled = runCatching {
                                context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
                            }.isSuccess
                            if (!dialled) {
                                Toast.makeText(context, parish.contactPhone, Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.heightIn(min = MinTouchTarget).testTag("call_parish_${parish.id}")
                    )
                }
            }
        }
        // The list ends under a small ornament, never an empty void.
        item { AppOrnament() }
        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/**
 * One story from the Vatican's feed. Tapping it opens the article; the feed
 * carries only a summary.
 */
@Composable
private fun VaticanNewsCard(
    article: VaticanNewsItem,
    currentLanguage: AppLanguage
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    // Semantic palette only: source/date textSecondary, title textPrimary,
    // summary textSecondary, CTA accent -- every rank resolves per identity
    // and dark mode, so no hardcoded colour survives here.
    GutterCard(
        modifier = Modifier.semantics {
            contentDescription = article.title
        },
        onClick = {
            runCatching {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.link)))
            }
        }
    ) {
        Text(
            text = eyebrow(
                if (article.publishedAt.isNotBlank()) {
                    "Vatican News · ${article.publishedAt}"
                } else {
                    "Vatican News"
                }
            ),
            style = styles.sectionEyebrow,
            color = design.palette.textSecondary
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge,
            color = design.palette.textPrimary,
            modifier = Modifier.semantics { heading() }
        )
        if (article.summary.isNotBlank()) {
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = article.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = design.palette.textSecondary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(Modifier.height(design.spacing.cardGap))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.OpenInNew,
                contentDescription = null,
                tint = design.palette.accent,
                modifier = Modifier.size(design.decor.rowIconSize)
            )
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Text(
                text = currentLanguage.pick(
                    "Leer en Vatican News →",
                    "Read on Vatican News →",
                    "Ler no Vatican News →"
                ),
                style = styles.buttonLabel,
                color = design.palette.accent
            )
        }
    }
}




// ------------------------------------------------------------------ fittings

/** [AppCard] leaves the screen's inset to its caller; every card here wants it. */
@Composable
private fun GutterCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    AppCard(
        modifier = modifier
            .padding(horizontal = design.spacing.gutter)
            .widthIn(max = design.spacing.readingMaxWidth)
            .then(
                if (onClick != null) Modifier.heightIn(min = MinTouchTarget)
                else Modifier
            ),
        onClick = onClick,
        content = content
    )
}

/** A card's heading, with the century, year or reference that qualifies it. */
@Composable
private fun CardHeading(title: String, meta: String? = null) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = design.palette.textPrimary,
            modifier = Modifier.semantics { heading() }
        )
        if (meta != null && meta.isNotBlank()) {
            Spacer(Modifier.height(design.spacing.cardGap / 4))
            Text(
                text = eyebrow(meta),
                style = styles.sectionEyebrow,
                color = design.palette.gold
            )
        }
    }
}

/** An eyebrow label over the passage it introduces — a card's unit of content. */
@Composable
private fun Note(
    label: String,
    body: String,
    labelColor: Color? = null,
    bodyStyle: TextStyle? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = eyebrow(label),
            style = styles.sectionEyebrow,
            color = labelColor ?: design.palette.textSecondary
        )
        Spacer(Modifier.height(design.spacing.cardGap / 4))
        Text(
            text = body,
            style = bodyStyle ?: MaterialTheme.typography.bodyLarge,
            color = design.palette.textPrimary
        )
    }
}

/**
 * Foundation level, in the order the reader sees it — an honest heuristic,
 * computed from the refs [parseFoundation] actually found in the foundation
 * text plus a plain word check on the clarification:
 * A) only BibleRef, no CatechismRef; B) any CatechismRef (its loci are the
 * strong CCC paragraphs cited); C) the clarification mentions Tradition in
 * ES/EN/PT, accent-folded; D) everything else (development/discipline).
 * A blank or unparseable foundation therefore lands on D, never on A.
 */
private fun foundationLevelFor(refs: List<FoundationRef>, clarificationText: String): Char {
    val hasBible = refs.any { it is FoundationRef.BibleRef }
    val hasCcc = refs.any { it is FoundationRef.CatechismRef }
    if (hasBible && !hasCcc) return 'A'
    if (hasCcc) return 'B'
    val folded = clarificationText.lowercase()
        .replace("á", "a").replace("é", "e").replace("í", "i")
        .replace("ó", "o").replace("ú", "u").replace("ç", "c").replace("ã", "a")
    if ("tradicion" in folded || "tradicao" in folded) return 'C'
    return 'D'
}

private fun foundationLevelLabel(level: Char, language: AppLanguage): String = when (level) {
    'A' -> language.pick("Bíblico explícito", "Explicitly biblical", "Bíblico explícito")
    'B' -> language.pick("Interpretativo", "Interpretive", "Interpretativo")
    'C' -> language.pick("Tradicional", "Traditional", "Tradicional")
    else -> language.pick("Desarrollo/disciplina", "Development/discipline", "Desenvolvimento/disciplina")
}

private fun foundationLevelExplanation(level: Char, language: AppLanguage): String = when (level) {
    'A' -> language.pick(
        "Cita solo referencias bíblicas; señal automática, puede no capturar latín u otras citas.",
        "Cites only Bible references; automatic signal, may miss Latin or other citations.",
        "Cita apenas referências bíblicas; sinal automático, pode não capturar latim ou outras citações."
    )
    'B' -> language.pick(
        "Cita el Catecismo (CCC); señal automática, no sustituye leer los párrafos citados.",
        "Cites the Catechism (CCC); automatic signal, does not replace reading the cited paragraphs.",
        "Cita o Catecismo (CCC); sinal automático, não substitui ler os parágrafos citados."
    )
    'C' -> language.pick(
        "La aclaración menciona la Tradición; señal automática y orientativa.",
        "The clarification mentions Tradition; automatic, indicative signal.",
        "O esclarecimento menciona a Tradição; sinal automático e indicativo."
    )
    else -> language.pick(
        "Sin cita bíblica o del CCC detectada; señal automática, no clasifica todo el contenido.",
        "No Bible or CCC citation detected; automatic signal, does not classify all content.",
        "Nenhuma citação bíblica ou do CCC detetada; sinal automático, não classifica todo o conteúdo."
    )
}

/** The level badge: code + localized label on one line, one-line explanation under it. */
@Composable
private fun FoundationLevelBadge(level: Char, currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Column(
        Modifier
            .fillMaxWidth()
            .testTag("foundation_level_badge")
    ) {
        Text(
            text = eyebrow(
                currentLanguage.pick(
                    "Fundamento $level · ${foundationLevelLabel(level, currentLanguage)}",
                    "Foundation $level · ${foundationLevelLabel(level, currentLanguage)}",
                    "Fundamento $level · ${foundationLevelLabel(level, currentLanguage)}"
                )
            ),
            style = styles.sectionEyebrow,
            color = design.palette.gold
        )
        Spacer(Modifier.height(design.spacing.cardGap / 4))
        Text(
            text = foundationLevelExplanation(level, currentLanguage),
            style = MaterialTheme.typography.bodyMedium,
            color = design.palette.textSecondary
        )
    }
}

/** One condition of a numbered sequence, the figure hung in the margin. */
@Composable
private fun NumberedLine(number: Int, text: String) {
    val design = LocalAppDesign.current

    Row(Modifier.fillMaxWidth()) {
        Text(
            text = "$number",
            style = MaterialTheme.typography.bodyLarge,
            color = design.palette.accent,
            modifier = Modifier.width(design.spacing.iconLabelGap + design.spacing.cardGap)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = design.palette.textPrimary,
            modifier = Modifier.weight(1f)
        )
    }
}

/** Running labels are cut as capitals only where the identity sets them so. */
@Composable
private fun eyebrow(text: String): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) text.uppercase() else text

/**
 * No kit insets its own segmented control, so the gutter is the caller's to
 * give. Vatican is the exception: its strip is a ruled band whose hairline runs
 * the full width of the leaf, and inset it stops meeting the edges.
 */
@Composable
private fun tabStripInset(): Dp {
    val design = LocalAppDesign.current
    return design.spacing.gutter
}

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** Opens [uri] in whatever handles maps, reporting whether anything did. */
private fun Context.openMaps(uri: String): Boolean =
    runCatching { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri))) }.isSuccess
