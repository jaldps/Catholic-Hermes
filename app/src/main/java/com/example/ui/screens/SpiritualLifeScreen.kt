package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.JournalEntryEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.models.GregorianChantItem
import com.example.data.models.TriviaQuestion
import com.example.data.models.ViaCrucisStation
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.TextFormat
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppCategoryTile
import com.example.ui.design.AppChevron
import com.example.ui.design.AppConfirmDialog
import com.example.ui.design.AppContentCard
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppFilterChips
import com.example.ui.design.AppFilterMenu
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppGrid
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppKitChip
import com.example.ui.design.AppLargeTitleHeader
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppRoundPlayButton
import com.example.ui.design.LumenGlyphs
import com.example.ui.design.appDockInset
import com.example.ui.design.lumenCanvas
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.design.rememberFirstAsset
import com.example.ui.design.softShadow
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

// ------------------------------------------------------------------- journal

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
    val pt: String,
    val pluralEs: String,
    val pluralEn: String,
    val pluralPt: String
)

private val JournalCategories = listOf(
    JournalCategory("Intención", "Intención", "Intention", "Intenção", "Intenciones", "Intentions", "Intenções"),
    JournalCategory("Agradecimiento", "Agradecimiento", "Thanksgiving", "Agradecimento", "Agradecimientos", "Thanksgivings", "Agradecimentos"),
    JournalCategory("Examen", "Examen", "Examen", "Exame", "Exámenes", "Examens", "Exames"),
    JournalCategory("Gracia", "Gracia", "Grace", "Graça", "Gracias", "Graces", "Graças")
)

/** Falls back to the stored value, so a category from an older build still reads. */
private fun journalCategoryLabel(key: String, language: AppLanguage): String =
    JournalCategories.firstOrNull { it.key == key }
        ?.let { language.pick(it.es, it.en, it.pt) }
        ?: key

private val MonthsEs = listOf("enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre")
private val MonthsEn = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
private val MonthsPt = listOf("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro")

/** "Hoy", "Ayer", "3 de noviembre" (with the year when it is not this one). */
private fun relativeDate(timestamp: Long, language: AppLanguage): String {
    val then = Calendar.getInstance().apply { timeInMillis = timestamp }
    val now = Calendar.getInstance()
    fun dayStart(c: Calendar) = (c.clone() as Calendar).apply {
        set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
    }.timeInMillis
    val days = ((dayStart(now) - dayStart(then)) / 86_400_000L).toInt()
    if (days == 0) return language.pick("Hoy", "Today", "Hoje")
    if (days == 1) return language.pick("Ayer", "Yesterday", "Ontem")
    val m = then.get(Calendar.MONTH)
    val d = then.get(Calendar.DAY_OF_MONTH)
    val y = then.get(Calendar.YEAR)
    val sameYear = y == now.get(Calendar.YEAR)
    return language.pick(
        "$d de ${MonthsEs[m]}" + if (sameYear) "" else " de $y",
        "${MonthsEn[m]} $d" + if (sameYear) "" else ", $y",
        "$d de ${MonthsPt[m]}" + if (sameYear) "" else " de $y"
    )
}

/** The title without a trailing "(Canto Gregoriano Simple)" style parenthetical. */
private fun chantDisplayTitle(title: String): String =
    title.replace(Regex("""\s*\([^)]*\)\s*$"""), "").ifBlank { title }

/** "I Estación: Jesús es condenado" → "Jesús es condenado" (the page numbers it). */
private fun stationDisplayTitle(title: String): String =
    if (": " in title) title.substringAfter(": ").ifBlank { title } else title

private const val PAGE_HUB = 0
private const val PAGE_QUIZ = 1
private const val PAGE_STATIONS = 2
private const val PAGE_CHANT = 3

/**
 * The inner life: a journal, with the Way of the Cross, a faith quiz and
 * Gregorian chant one tap away.
 *
 * [initialTab] keeps its meaning for the deep links: 0 the hub (journal),
 * 1 the quiz (Home's "Trivia"), 2 the Way of the Cross, 3 the chant. A page
 * opened by a deep link returns to where the reader came from; one opened from
 * the hub returns to the hub.
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
    AppEdgeToEdge()
    val design = LocalAppDesign.current
    val scope = rememberCoroutineScope()
    val journal by remember(dao) { dao.getAllJournalEntries() }
        .collectAsState<List<JournalEntryEntity>, List<JournalEntryEntity>?>(initial = null)

    var page by rememberSaveable(initialTab) { mutableStateOf(initialTab.coerceIn(PAGE_HUB, PAGE_CHANT)) }
    AppBackHandler(enabled = page != PAGE_HUB && initialTab == PAGE_HUB) { page = PAGE_HUB }

    // Composer draft: kept when the sheet is cancelled, cleared once saved.
    var showComposer by rememberSaveable { mutableStateOf(false) }
    var draftTitle by rememberSaveable { mutableStateOf("") }
    var draftCategory by rememberSaveable { mutableStateOf(JournalCategories.first().key) }
    var draftContent by rememberSaveable { mutableStateOf("") }
    var entryToDelete by remember { mutableStateOf<JournalEntryEntity?>(null) }
    var filterIndex by rememberSaveable { mutableStateOf(0) }

    // Quiz progress.
    val triviaList = repository.triviaQuestions
    var triviaIndex by rememberSaveable { mutableStateOf(0) }
    var selectedOption by rememberSaveable { mutableStateOf(-1) }
    var triviaScore by rememberSaveable { mutableStateOf(0) }
    var hasAnswered by rememberSaveable { mutableStateOf(false) }

    // Audio: the voice is global, but each page shows "stop" only for what it started.
    val isPlayingAudio by ttsManager.isPlaying.collectAsState()
    var playingSource by remember { mutableStateOf<String?>(null) }
    var selectedChantId by rememberSaveable { mutableStateOf(repository.gregorianChants.firstOrNull()?.id) }

    val hubListState = rememberLazyListState()

    Box(
        modifier
            .fillMaxSize()
            .testTag("spiritual_life_screen")
    ) {
        AnimatedContent(
            targetState = page,
            transitionSpec = {
                val forward = targetState != PAGE_HUB
                val dir = if (forward) 1 else -1
                (slideInHorizontally(tween(if (design.reduceMotion) 0 else 300)) { dir * it / 3 } + fadeIn(tween(if (design.reduceMotion) 0 else 300))) togetherWith
                    (slideOutHorizontally(tween(if (design.reduceMotion) 0 else 300)) { -dir * it / 3 } + fadeOut(tween(if (design.reduceMotion) 0 else 200)))
            },
            label = "spiritualPage"
        ) { shown ->
            when (shown) {
                PAGE_QUIZ -> QuizPage(
                    questions = triviaList,
                    index = triviaIndex,
                    score = triviaScore,
                    selectedOption = selectedOption.takeIf { it >= 0 },
                    hasAnswered = hasAnswered,
                    language = currentLanguage,
                    onAnswer = { option ->
                        selectedOption = option
                        hasAnswered = true
                        val correct = triviaList[triviaIndex.coerceIn(triviaList.indices)].correctIndex
                        if (option == correct) triviaScore += 10
                    },
                    onNext = {
                        selectedOption = -1
                        hasAnswered = false
                        if (triviaList.isNotEmpty()) triviaIndex = (triviaIndex + 1) % triviaList.size
                    }
                )

                PAGE_STATIONS -> StationsPage(
                    stations = repository.viaCrucisStations,
                    language = currentLanguage,
                    isPlaying = isPlayingAudio && playingSource == "station",
                    onPlayPause = { station ->
                        if (isPlayingAudio && playingSource == "station") {
                            ttsManager.stop()
                        } else {
                            if (isPlayingAudio) ttsManager.stop()
                            playingSource = "station"
                            ttsManager.speakDialogue(
                                station.meditation.get(currentLanguage),
                                station.prayer.get(currentLanguage)
                            )
                        }
                    }
                )

                PAGE_CHANT -> ChantPage(
                    chants = repository.gregorianChants,
                    selectedChantId = selectedChantId,
                    playingChantId = if (isPlayingAudio) playingSource?.removePrefix("chant:")
                        ?.takeIf { playingSource?.startsWith("chant:") == true } else null,
                    language = currentLanguage,
                    onSelect = { chant ->
                        selectedChantId = if (selectedChantId == chant.id) null else chant.id
                    },
                    onTogglePlayback = { chant ->
                        selectedChantId = chant.id
                        if (isPlayingAudio && playingSource == "chant:${chant.id}") {
                            ttsManager.stop()
                        } else {
                            if (isPlayingAudio) ttsManager.stop()
                            playingSource = "chant:${chant.id}"
                            ttsManager.speakSingle(chant.latinLyrics)
                        }
                    }
                )

                else -> SpiritualHub(
                    listState = hubListState,
                    entries = journal,
                    filterIndex = filterIndex,
                    onFilter = { filterIndex = it },
                    language = currentLanguage,
                    onOpenPage = { page = it },
                    onToggleAnswered = { entry ->
                        scope.launch { dao.updateJournalEntry(entry.copy(isAnswered = !entry.isAnswered)) }
                    },
                    onRequestDelete = { entryToDelete = it },
                    onCompose = { prompt, category ->
                        if (prompt != null) draftTitle = prompt
                        if (category != null) draftCategory = category
                        showComposer = true
                    }
                )
            }
        }
    }

    if (showComposer) {
        JournalComposer(
            language = currentLanguage,
            title = draftTitle,
            onTitleChange = { draftTitle = it },
            category = draftCategory,
            onCategoryChange = { draftCategory = it },
            content = draftContent,
            onContentChange = { draftContent = it },
            onSave = {
                if (draftTitle.isNotBlank()) {
                    val entry = JournalEntryEntity(
                        title = draftTitle.trim(),
                        category = draftCategory,
                        content = draftContent
                    )
                    scope.launch {
                        dao.insertJournalEntry(entry)
                        draftTitle = ""
                        draftContent = ""
                        showComposer = false
                    }
                }
            },
            onDismiss = { showComposer = false }
        )
    }

    entryToDelete?.let { entry ->
        AppConfirmDialog(
            title = currentLanguage.pick("¿Eliminar esta entrada?", "Delete this entry?", "Excluir esta entrada?"),
            message = currentLanguage.pick("No se puede deshacer.", "This can't be undone.", "Isso não pode ser desfeito."),
            confirmLabel = currentLanguage.pick("Eliminar", "Delete", "Excluir"),
            dismissLabel = currentLanguage.pick("Cancelar", "Cancel", "Cancelar"),
            destructive = true,
            tag = "journal_delete_dialog",
            onConfirm = {
                scope.launch { dao.deleteJournalEntry(entry) }
                entryToDelete = null
            },
            onDismiss = { entryToDelete = null }
        )
    }
}

// ----------------------------------------------------------------------- hub

private data class Practice(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val page: Int)

@Composable
private fun SpiritualHub(
    listState: androidx.compose.foundation.lazy.LazyListState,
    entries: List<JournalEntryEntity>?,
    filterIndex: Int,
    onFilter: (Int) -> Unit,
    language: AppLanguage,
    onOpenPage: (Int) -> Unit,
    onToggleAnswered: (JournalEntryEntity) -> Unit,
    onRequestDelete: (JournalEntryEntity) -> Unit,
    onCompose: (prompt: String?, category: String?) -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val dock = appDockInset()
    val hero = rememberFirstAsset("heroes/lumen/spiritual.jpg", "heroes/sunrise_mountains.jpg")
    val practices = listOf(
        Practice(language.pick("Vía Crucis", "Stations of the Cross", "Via-Sacra"), LumenGlyphs.cross, PAGE_STATIONS),
        Practice(language.pick("Preguntas de fe", "Faith quiz", "Quiz da fé"), Icons.Outlined.Quiz, PAGE_QUIZ),
        Practice(language.pick("Canto gregoriano", "Gregorian chant", "Canto gregoriano"), Icons.Outlined.MusicNote, PAGE_CHANT)
    )

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxSize(),
            state = listState,
            contentPadding = PaddingValues(bottom = dock + 110.dp)
        ) {
            item(key = "life_header") {
                AppDetailHeader(
                    title = language.pick("Vida espiritual", "Spiritual life", "Vida espiritual"),
                    assetPath = hero,
                    imageHeight = 180.dp
                )
            }
            item(key = "life_practices") {
                Column {
                    SectionTitle(language.pick("Prácticas", "Practices", "Práticas"), top = 0.dp)
                    Box(Modifier.testTag("spiritual_life_learn_picker")) {
                        AppGrid(practices, columns = 3) { practice, cell ->
                            AppCategoryTile(
                                label = practice.label,
                                icon = practice.icon,
                                onClick = { onOpenPage(practice.page) },
                                modifier = cell
                            )
                        }
                    }
                }
            }

            item(key = "journal_title") {
                val granted = entries?.count { it.isAnswered } ?: 0
                Column(
                    Modifier
                        .padding(horizontal = design.spacing.gutter)
                        .padding(top = design.spacing.sectionGap)
                ) {
                    Text(language.pick("Diario", "Journal", "Diário"), style = styles.headline, color = p.textPrimary)
                    if (!entries.isNullOrEmpty()) {
                        Spacer(Modifier.height(2.dp))
                        Text(
                            journalCountLine(entries.size, granted, language),
                            style = styles.footnote,
                            color = p.textSecondary
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }

            when {
                entries == null -> {
                    items(2, key = { "journal_skeleton_$it" }) { JournalSkeleton() }
                }

                entries.isEmpty() -> item(key = "journal_empty") {
                    JournalEmptyCard(language, onCompose)
                }

                else -> {
                    val showFilters = entries.size >= 6
                    if (showFilters) {
                        item(key = "journal_filters") {
                            val labels = listOf(language.pick("Todas", "All", "Todas")) +
                                JournalCategories.map { language.pick(it.pluralEs, it.pluralEn, it.pluralPt) } +
                                language.pick("Concedidas", "Granted", "Concedidas")
                            AppFilterMenu(language.pick("Mostrar", "Show", "Mostrar"), labels, filterIndex, onFilter, tag = "journal_filter_menu")
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                    val effectiveFilter = if (showFilters) filterIndex else 0
                    val shown = entries.filter { entry ->
                        when (effectiveFilter) {
                            0 -> true
                            in 1..JournalCategories.size -> entry.category == JournalCategories[effectiveFilter - 1].key
                            else -> entry.isAnswered
                        }
                    }
                    if (shown.isEmpty()) {
                        item(key = "journal_filtered_empty") {
                            Text(
                                language.pick("Nada en esta categoría todavía.", "Nothing in this category yet.", "Nada nesta categoria ainda."),
                                style = styles.subhead,
                                color = p.textSecondary,
                                modifier = Modifier.padding(horizontal = design.spacing.gutter + 4.dp, vertical = 12.dp)
                            )
                        }
                    }
                    items(shown, key = { it.id }) { entry ->
                        JournalEntryCard(
                            entry = entry,
                            language = language,
                            onToggleAnswered = { onToggleAnswered(entry) },
                            onDelete = { onRequestDelete(entry) },
                            modifier = Modifier.padding(horizontal = design.spacing.gutter).padding(bottom = 12.dp)
                        )
                    }
                }
            }
        }

        if (!entries.isNullOrEmpty()) {
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Brush.verticalGradient(listOf(Color.Transparent, p.canvasBottom.copy(alpha = 0.92f))))
                    .padding(top = 28.dp, bottom = dock + 12.dp),
                contentAlignment = Alignment.Center
            ) {
                AppPrimaryCapsule(
                    label = language.pick("Nueva entrada", "New entry", "Nova entrada"),
                    onClick = { onCompose(null, null) },
                    icon = Icons.Outlined.Add,
                    fillWidth = false,
                    tag = "add_journal_entry_fab"
                )
            }
        }
    }
}

private fun journalCountLine(count: Int, granted: Int, language: AppLanguage): String = language.pick(
    "$count ${if (count == 1) "entrada" else "entradas"} · $granted ${if (granted == 1) "gracia concedida" else "gracias concedidas"}",
    "$count ${if (count == 1) "entry" else "entries"} · $granted ${if (granted == 1) "grace granted" else "graces granted"}",
    "$count ${if (count == 1) "entrada" else "entradas"} · $granted ${if (granted == 1) "graça concedida" else "graças concedidas"}"
)

@Composable
private fun SectionTitle(text: String, top: androidx.compose.ui.unit.Dp = LocalAppDesign.current.spacing.sectionGap) {
    val design = LocalAppDesign.current
    Text(
        text,
        style = LocalAppTextStyles.current.headline,
        color = design.palette.textPrimary,
        modifier = Modifier
            .padding(horizontal = design.spacing.gutter)
            .padding(top = top, bottom = 12.dp)
    )
}

@Composable
private fun JournalSkeleton() {
    val design = LocalAppDesign.current
    val p = design.palette
    AppGlassSurface(
        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter).padding(bottom = 12.dp),
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Column {
            Box(Modifier.width(110.dp).height(11.dp).clip(RoundedCornerShape(6.dp)).background(p.textPrimary.copy(alpha = 0.06f)))
            Spacer(Modifier.height(14.dp))
            Box(Modifier.fillMaxWidth(0.6f).height(16.dp).clip(RoundedCornerShape(8.dp)).background(p.textPrimary.copy(alpha = 0.08f)))
            Spacer(Modifier.height(14.dp))
            Box(Modifier.fillMaxWidth().height(12.dp).clip(RoundedCornerShape(6.dp)).background(p.textPrimary.copy(alpha = 0.05f)))
            Spacer(Modifier.height(8.dp))
            Box(Modifier.fillMaxWidth(0.8f).height(12.dp).clip(RoundedCornerShape(6.dp)).background(p.textPrimary.copy(alpha = 0.05f)))
        }
    }
}

@Composable
private fun JournalEmptyCard(language: AppLanguage, onCompose: (String?, String?) -> Unit) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    data class Prompt(val text: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val category: String)
    val prompts = listOf(
        Prompt(language.pick("Una gracia recibida hoy", "A grace received today", "Uma graça recebida hoje"), Icons.Outlined.AutoAwesome, "Gracia"),
        Prompt(language.pick("Una intención para rezar", "An intention to pray for", "Uma intenção para rezar"), Icons.Outlined.VolunteerActivism, "Intención"),
        Prompt(language.pick("Algo por lo que dar gracias", "Something to give thanks for", "Algo por que agradecer"), Icons.Outlined.FavoriteBorder, "Agradecimiento")
    )
    AppGlassSurface(
        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
        contentPadding = PaddingValues(top = design.spacing.cardPadding, bottom = design.spacing.cardPadding)
    ) {
        Column {
            Column(Modifier.padding(horizontal = design.spacing.cardPadding)) {
                Icon(Icons.Outlined.EditNote, null, tint = p.textPrimary, modifier = Modifier.size(26.dp))
                Spacer(Modifier.height(14.dp))
                Text(language.pick("Tu diario está vacío", "Your journal is empty", "Seu diário está vazio"), style = styles.headline, color = p.textPrimary)
                Spacer(Modifier.height(4.dp))
                Text(
                    language.pick(
                        "Anota intenciones, agradecimientos y gracias recibidas.",
                        "Keep your intentions, thanksgivings and the graces you receive.",
                        "Anote intenções, agradecimentos e graças recebidas."
                    ),
                    style = styles.subhead,
                    color = p.textSecondary
                )
            }
            Spacer(Modifier.height(10.dp))
            prompts.forEach { prompt ->
                Box(Modifier.fillMaxWidth().height(0.5.dp).padding(start = design.spacing.cardPadding).background(p.separator))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .lumenPress({ onCompose(prompt.text, prompt.category) }, 0.99f)
                        .heightIn(min = 52.dp)
                        .padding(horizontal = design.spacing.cardPadding, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(prompt.icon, null, tint = p.textPrimary, modifier = Modifier.size(22.dp))
                    Spacer(Modifier.width(16.dp))
                    Text(prompt.text, style = styles.body, color = p.textPrimary, modifier = Modifier.weight(1f))
                    Spacer(Modifier.width(10.dp))
                    AppChevron()
                }
            }
            Spacer(Modifier.height(16.dp))
            AppPrimaryCapsule(
                label = language.pick("Escribir la primera entrada", "Write your first entry", "Escrever a primeira entrada"),
                onClick = { onCompose(null, null) },
                modifier = Modifier.padding(horizontal = design.spacing.cardPadding),
                icon = Icons.Outlined.Add,
                tag = "add_first_journal_entry"
            )
        }
    }
}

@Composable
private fun JournalEntryCard(
    entry: JournalEntryEntity,
    language: AppLanguage,
    onToggleAnswered: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    var expanded by rememberSaveable(entry.id) { mutableStateOf(false) }
    var overflows by remember { mutableStateOf(false) }
    val canExpand = overflows || expanded
    val meta = journalCategoryLabel(entry.category, language) + " · " + relativeDate(entry.timestamp, language)
    val deleteLabel = language.pick("Eliminar entrada", "Delete entry", "Excluir entrada")

    AppGlassSurface(
        modifier = modifier.fillMaxWidth(),
        onClick = if (canExpand) ({ expanded = !expanded }) else null,
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Column(
            Modifier.semantics {
                if (canExpand) {
                    stateDescription = if (expanded) language.pick("Expandida", "Expanded", "Expandida")
                    else language.pick("Contraída", "Collapsed", "Recolhida")
                }
            }
        ) {
            Text(meta, style = styles.footnote, color = p.textSecondary)
            Spacer(Modifier.height(6.dp))
            Text(entry.title, style = styles.headline, color = p.textPrimary)
            if (entry.content.isNotBlank()) {
                Spacer(Modifier.height(10.dp))
                Text(
                    entry.content,
                    style = styles.reading,
                    color = p.textPrimary,
                    maxLines = if (expanded) Int.MAX_VALUE else 6,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { if (!expanded) overflows = it.hasVisualOverflow },
                    modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                )
                if (overflows && !expanded) {
                    Spacer(Modifier.height(6.dp))
                    Text(language.pick("Leer más", "Read more", "Ler mais"), style = styles.footnote, color = p.textSecondary)
                }
            }
            Spacer(Modifier.height(14.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                GrantedToggle(entry.isAnswered, language, onToggleAnswered, Modifier.weight(1f))
                AppGlassIconButton(
                    icon = Icons.Outlined.Delete,
                    contentDescription = deleteLabel,
                    onClick = onDelete
                )
            }
        }
    }
}

@Composable
private fun GrantedToggle(granted: Boolean, language: AppLanguage, onToggle: () -> Unit, modifier: Modifier = Modifier) {
    val p = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    val label = if (granted) language.pick("Gracia concedida", "Grace granted", "Graça concedida")
    else language.pick("Marcar como concedida", "Mark as granted", "Marcar como concedida")
    Row(
        modifier
            .heightIn(min = 44.dp)
            .lumenPress(onToggle, 0.98f, Role.Checkbox)
            .semantics {
                selected = granted
                stateDescription = if (granted) language.pick("Concedida", "Granted", "Concedida") else ""
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(if (granted) p.action else Color.Transparent)
                .border(1.5.dp, if (granted) p.success else p.textSecondary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (granted) Icon(Icons.Filled.Check, null, tint = p.onAction, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(10.dp))
        Text(label, style = styles.subhead, color = p.textPrimary)
    }
}

// ------------------------------------------------------------------ composer

@Composable
private fun JournalComposer(
    language: AppLanguage,
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
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val titleFocus = remember { FocusRequester() }
    val bodyFocus = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        delay(200)
        runCatching { if (title.isBlank()) titleFocus.requestFocus() else bodyFocus.requestFocus() }
    }
    val canSave = title.isNotBlank()
    val shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)
    ) {
        Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = if (design.isDark) 0.5f else 0.28f))
                .clickable(remember { MutableInteractionSource() }, null) { onDismiss() }
        )
        Box(Modifier.fillMaxSize().statusBarsPadding().imePadding(), contentAlignment = Alignment.BottomCenter) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.94f)
                    .clip(shape)
                    .background(Brush.verticalGradient(listOf(p.canvasTop, p.canvasBottom)))
                    .border(1.dp, Brush.verticalGradient(listOf(p.glassEdgeTop, Color.Transparent)), shape)
                    .clickable(remember { MutableInteractionSource() }, null) { }
                    .navigationBarsPadding()
                    .testTag("journal_composer")
            ) {
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 8.dp).heightIn(min = 56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .heightIn(min = 48.dp)
                            .lumenPress(onDismiss, 0.96f)
                            .padding(horizontal = 12.dp)
                            .testTag("journal_cancel_button"),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(language.pick("Cancelar", "Cancel", "Cancelar"), style = styles.body, color = p.textPrimary)
                    }
                    Text(
                        language.pick("Nueva entrada", "New entry", "Nova entrada"),
                        style = styles.headline,
                        color = p.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        Modifier
                            .heightIn(min = 48.dp)
                            .lumenPress(onSave, 0.96f, enabled = canSave)
                            .padding(horizontal = 12.dp)
                            .testTag("journal_save_button"),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            language.pick("Guardar", "Save", "Salvar"),
                            style = styles.headline,
                            color = if (canSave) p.textPrimary else p.inkQuiet
                        )
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = design.spacing.gutter, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    JournalCategories.forEach { option ->
                        AppKitChip(
                            label = language.pick(option.es, option.en, option.pt),
                            selected = category == option.key,
                            onClick = { onCategoryChange(option.key) },
                            modifier = Modifier.heightIn(min = 44.dp)
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
                Box(Modifier.padding(horizontal = design.spacing.gutter)) {
                    if (title.isEmpty()) {
                        Text(language.pick("Título", "Title", "Título"), style = styles.title2, color = p.textTertiary)
                    }
                    BasicTextField(
                        value = title,
                        onValueChange = onTitleChange,
                        textStyle = styles.title2.copy(color = p.textPrimary),
                        cursorBrush = SolidColor(p.textPrimary),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { runCatching { bodyFocus.requestFocus() } }),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(titleFocus)
                            .semantics { contentDescription = language.pick("Título", "Title", "Título") }
                            .testTag("journal_title_field")
                    )
                }
                Spacer(Modifier.height(12.dp))
                Box(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter).height(0.5.dp).background(p.separator))
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = design.spacing.gutter, vertical = 16.dp)
                ) {
                    if (content.isEmpty()) {
                        Text(
                            language.pick(
                                "Escribe tus palabras al Señor…",
                                "Write your words to the Lord…",
                                "Escreva suas palavras ao Senhor…"
                            ),
                            style = styles.reading,
                            color = p.textTertiary
                        )
                    }
                    BasicTextField(
                        value = content,
                        onValueChange = onContentChange,
                        textStyle = styles.reading.copy(color = p.textPrimary),
                        cursorBrush = SolidColor(p.textPrimary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 240.dp)
                            .focusRequester(bodyFocus)
                            .semantics { contentDescription = language.pick("Tu escrito", "Your writing", "Seu texto") }
                            .testTag("journal_body_field")
                    )
                }
            }
        }
        }
    }
}

// ---------------------------------------------------------------------- quiz

private enum class OptionVisual { Idle, Correct, Wrong, Dimmed }

@Composable
private fun QuizPage(
    questions: List<TriviaQuestion>,
    index: Int,
    score: Int,
    selectedOption: Int?,
    hasAnswered: Boolean,
    language: AppLanguage,
    onAnswer: (Int) -> Unit,
    onNext: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val dock = appDockInset()
    val listState = rememberLazyListState()
    val scoreText = language.pick("$score puntos", "$score points", "$score pontos")
    val title = language.pick("Preguntas de fe", "Faith quiz", "Quiz da fé")

    val question = questions.getOrNull(index.coerceIn(0, (questions.size - 1).coerceAtLeast(0)))
    // New question: back to the top. Answered: bring the verdict and the
    // button into view together with the options.
    LaunchedEffect(index) { listState.scrollToItem(0) }
    LaunchedEffect(hasAnswered) { if (hasAnswered) listState.animateScrollToItem(2) }

    LazyColumn(Modifier.fillMaxSize(), state = listState, contentPadding = PaddingValues(bottom = dock + 24.dp)) {
        item(key = "quiz_header") {
            AppLargeTitleHeader(title = title) {
                if (questions.isNotEmpty()) {
                    Box(
                        Modifier
                            .lumenGlass(RoundedCornerShape(percent = 50), strong = true)
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                            .semantics { contentDescription = scoreText }
                    ) {
                        Text(scoreText, style = styles.caption, color = p.textPrimary)
                    }
                }
            }
        }

        if (question == null) {
            item(key = "quiz_empty") {
                AppEmptyState(
                    title = language.pick("Sin preguntas", "No questions", "Sem perguntas"),
                    body = language.pick(
                        "El cuestionario no tiene preguntas disponibles.",
                        "The quiz has no questions available.",
                        "O questionário não tem perguntas disponíveis."
                    ),
                    icon = Icons.Outlined.Lightbulb
                )
            }
            return@LazyColumn
        }

        item(key = "quiz_meta") {
            val progressLabel = language.pick(
                "Pregunta ${index + 1} de ${questions.size}",
                "Question ${index + 1} of ${questions.size}",
                "Pergunta ${index + 1} de ${questions.size}"
            )
            Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(
                    progressLabel + " · " + question.category.get(language),
                    style = styles.footnote,
                    color = p.textSecondary
                )
                Spacer(Modifier.height(10.dp))
                Box(
                    Modifier
                        .semantics { stateDescription = progressLabel }
                        .testTag("trivia_progress")
                ) {
                    AppProgressBar((index + 1).toFloat() / questions.size, height = 3.dp)
                }
            }
        }

        item(key = "quiz_question") {
            Text(
                question.question.get(language),
                style = styles.title2,
                color = p.textPrimary,
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = 22.dp, bottom = 20.dp)
                    .widthIn(max = design.spacing.readingMaxWidth)
            )
        }

        item(key = "quiz_options") {
            AppListGroup {
                question.options.forEachIndexed { optionIndex, option ->
                    val chosen = selectedOption == optionIndex
                    val isCorrect = optionIndex == question.correctIndex
                    // The verdict as text, not only as the mark: a wrong
                    // choice is neither the selected row nor the right one.
                    val stateLabel = when {
                        !hasAnswered && chosen -> language.pick("Seleccionada", "Selected", "Selecionada")
                        hasAnswered && isCorrect -> language.pick("Respuesta correcta", "Correct answer", "Resposta correta")
                        hasAnswered && chosen -> language.pick("Respuesta incorrecta", "Incorrect answer", "Resposta incorreta")
                        else -> null
                    }
                    val visual = when {
                        !hasAnswered -> OptionVisual.Idle
                        isCorrect -> OptionVisual.Correct
                        chosen -> OptionVisual.Wrong
                        else -> OptionVisual.Dimmed
                    }
                    QuizOptionRow(
                        text = option.get(language),
                        visual = visual,
                        stateLabel = stateLabel,
                        onClick = if (hasAnswered) null else ({ onAnswer(optionIndex) })
                    )
                }
            }
        }

        if (hasAnswered) {
            val correct = selectedOption == question.correctIndex
            item(key = "quiz_verdict") {
                AppContentCard(
                    title = if (correct) language.pick("Correcto", "Correct", "Correto")
                    else language.pick("Doctrina católica", "Catholic doctrine", "Doutrina católica"),
                    icon = if (correct) Icons.Outlined.CheckCircle else Icons.Outlined.MenuBook,
                    modifier = Modifier
                        .padding(horizontal = design.spacing.gutter)
                        .padding(top = 20.dp)
                ) {
                    Text(
                        question.doctrinalExplanation.get(language),
                        style = styles.reading,
                        color = p.textPrimary
                    )
                }
            }
            item(key = "quiz_next") {
                AppPrimaryCapsule(
                    label = language.pick("Siguiente pregunta", "Next question", "Próxima pergunta"),
                    onClick = onNext,
                    modifier = Modifier
                        .padding(horizontal = design.spacing.gutter)
                        .padding(top = 16.dp),
                    tag = "next_trivia_question_button"
                )
            }
        }
    }
}

@Composable
private fun QuizOptionRow(text: String, visual: OptionVisual, stateLabel: String?, onClick: (() -> Unit)?) {
    val p = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .lumenPress(onClick, 0.99f)
                .heightIn(min = 56.dp)
                .semantics { if (stateLabel != null) stateDescription = stateLabel }
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (visual == OptionVisual.Correct) p.action else Color.Transparent)
                    .border(
                        1.5.dp,
                        when (visual) {
                            OptionVisual.Correct -> p.success
                            OptionVisual.Wrong -> p.textPrimary
                            OptionVisual.Dimmed -> p.inkQuiet.copy(alpha = 0.5f)
                            OptionVisual.Idle -> p.inkQuiet
                        },
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                when (visual) {
                    OptionVisual.Correct -> Icon(Icons.Filled.Check, null, tint = p.onAction, modifier = Modifier.size(16.dp))
                    OptionVisual.Wrong -> Icon(Icons.Outlined.Close, null, tint = p.textPrimary, modifier = Modifier.size(14.dp))
                    else -> Unit
                }
            }
            Spacer(Modifier.width(14.dp))
            Text(
                text,
                style = styles.body,
                color = if (visual == OptionVisual.Dimmed) p.textSecondary else p.textPrimary,
                modifier = Modifier.weight(1f)
            )
        }
        Box(Modifier.fillMaxWidth().padding(start = 56.dp).height(0.5.dp).background(p.separator))
    }
}

// ------------------------------------------------------------ way of the cross

@Composable
private fun StationsPage(
    stations: List<ViaCrucisStation>,
    language: AppLanguage,
    isPlaying: Boolean,
    onPlayPause: (ViaCrucisStation) -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val dock = appDockInset()
    val hero = rememberFirstAsset("heroes/lumen/via_crucis.jpg", "heroes/lumen/christ_thorns.jpg", "heroes/lumen/spiritual.jpg")
    val label = language.pick("Vía Crucis", "Stations of the Cross", "Via-Sacra")
    AppInlineTitle(label)

    if (stations.isEmpty()) {
        LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = dock + 24.dp)) {
            item {
                AppDetailHeader(title = label, assetPath = hero, imageHeight = 180.dp)
            }
            item {
                AppEmptyState(
                    title = language.pick("Sin estaciones", "No stations", "Sem estações"),
                    body = language.pick(
                        "El Vía Crucis no está disponible.",
                        "The Stations of the Cross are not available.",
                        "A Via-Sacra não está disponível."
                    )
                )
            }
        }
        return
    }

    val pager = rememberPagerState { stations.size }
    val scope = rememberCoroutineScope()
    val current = pager.currentPage.coerceIn(stations.indices)

    Box(Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pager,
            modifier = Modifier.fillMaxSize(),
            key = { stations[it].stationNumber }
        ) { pageIndex ->
            StationPage(
                station = stations[pageIndex],
                total = stations.size,
                label = label,
                hero = hero,
                language = language,
                bottomPadding = dock + 140.dp
            )
        }
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Brush.verticalGradient(listOf(Color.Transparent, p.canvasBottom.copy(alpha = 0.94f))))
                .padding(top = 36.dp, bottom = dock + 12.dp),
            contentAlignment = Alignment.Center
        ) {
            StationTransport(
                isPlaying = isPlaying,
                canPrevious = current > 0,
                canNext = current < stations.size - 1,
                onPrevious = { scope.launch { pager.animateScrollToPage(current - 1) } },
                onNext = { scope.launch { pager.animateScrollToPage(current + 1) } },
                onPlayPause = { onPlayPause(stations[current]) },
                previousLabel = language.pick("Estación anterior", "Previous station", "Estação anterior"),
                nextLabel = language.pick("Estación siguiente", "Next station", "Estação seguinte"),
                playLabel = language.pick("Rezar estación", "Pray this station", "Rezar estação"),
                stopLabel = language.pick("Detener", "Stop", "Parar")
            )
        }
    }
}

@Composable
private fun StationPage(
    station: ViaCrucisStation,
    total: Int,
    label: String,
    hero: String?,
    language: AppLanguage,
    bottomPadding: androidx.compose.ui.unit.Dp
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val progress = language.pick(
        "Estación ${station.stationNumber} de $total",
        "Station ${station.stationNumber} of $total",
        "Estação ${station.stationNumber} de $total"
    )
    val roman = language.pick(
        "Estación ${TextFormat.roman(station.stationNumber)} de ${TextFormat.roman(total)}",
        "Station ${TextFormat.roman(station.stationNumber)} of ${TextFormat.roman(total)}",
        "Estação ${TextFormat.roman(station.stationNumber)} de ${TextFormat.roman(total)}"
    )
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = bottomPadding)) {
        item {
            AppDetailHeader(
                title = stationDisplayTitle(station.title.get(language)),
                assetPath = rememberFirstAsset(
                    "heroes/viacrucis/station_%02d.jpg".format(station.stationNumber),
                    *listOfNotNull(hero).toTypedArray()
                ),
                eyebrow = label,
                imageHeight = 200.dp
            )
        }
        item {
            Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(roman, style = styles.footnote, color = p.textSecondary)
                Spacer(Modifier.height(8.dp))
                Box(Modifier.semantics { stateDescription = progress }) {
                    AppProgressBar(station.stationNumber.toFloat() / total, height = 2.dp)
                }
                Spacer(Modifier.height(design.spacing.sectionGap))
                Column(
                    Modifier.widthIn(max = design.spacing.readingMaxWidth),
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    Text(
                        station.scripture.get(language),
                        style = styles.reading.copy(fontStyle = FontStyle.Italic),
                        color = p.textSecondary
                    )
                    Text(station.meditation.get(language), style = styles.reading, color = p.textPrimary)
                }
                Spacer(Modifier.height(design.spacing.sectionGap - 4.dp))
                AppGlassSurface(
                    Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(design.spacing.cardPadding)
                ) {
                    Column {
                        Text(language.pick("Oración", "Prayer", "Oração"), style = styles.eyebrow, color = p.textSecondary)
                        Spacer(Modifier.height(8.dp))
                        Text(station.prayer.get(language), style = styles.reading, color = p.textPrimary)
                    }
                }
            }
        }
    }
}

/** The tags the old buttons carried, on a glass transport capsule. */
@Composable
private fun StationTransport(
    isPlaying: Boolean,
    canPrevious: Boolean,
    canNext: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onPlayPause: () -> Unit,
    previousLabel: String,
    nextLabel: String,
    playLabel: String,
    stopLabel: String
) {
    val p = LocalAppDesign.current.palette
    val shape = RoundedCornerShape(percent = 50)
    Row(
        Modifier.lumenGlass(shape).padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        TransportGlyph(Icons.Filled.SkipPrevious, previousLabel, if (canPrevious) onPrevious else null, "via_crucis_previous_button")
        Box(
            Modifier
                .size(60.dp)
                .lumenPress(onPlayPause, 0.92f)
                .semantics {
                    contentDescription = if (isPlaying) stopLabel else playLabel
                    stateDescription = if (isPlaying) stopLabel else ""
                }
                .testTag("via_crucis_narrate_button")
                .softShadow(CircleShape, p.shadow, 18.dp, 6.dp)
                .background(p.glassStrong, CircleShape)
                .border(1.dp, Brush.verticalGradient(listOf(p.glassEdgeTop, p.glassEdgeBottom)), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                null,
                tint = p.textPrimary,
                modifier = Modifier.size(30.dp)
            )
        }
        TransportGlyph(Icons.Filled.SkipNext, nextLabel, if (canNext) onNext else null, "via_crucis_next_button")
    }
}

@Composable
private fun TransportGlyph(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: (() -> Unit)?,
    tag: String
) {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .size(48.dp)
            .lumenPress(onClick ?: {}, 0.9f, enabled = onClick != null)
            .semantics { contentDescription = label }
            .testTag(tag),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, null, tint = if (onClick != null) p.textPrimary else p.inkQuiet, modifier = Modifier.size(26.dp))
    }
}

// --------------------------------------------------------------------- chant

@Composable
private fun ChantPage(
    chants: List<GregorianChantItem>,
    selectedChantId: String?,
    playingChantId: String?,
    language: AppLanguage,
    onSelect: (GregorianChantItem) -> Unit,
    onTogglePlayback: (GregorianChantItem) -> Unit
) {
    val design = LocalAppDesign.current
    val dock = appDockInset()
    val hero = rememberFirstAsset("heroes/lumen/chant.jpg", "heroes/lumen/spiritual.jpg")
    val title = language.pick("Canto gregoriano", "Gregorian chant", "Canto gregoriano")
    AppInlineTitle(title)

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = dock + 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(key = "chant_header") {
            AppDetailHeader(
                title = title,
                assetPath = hero,
                meta = if (chants.isEmpty()) null else language.pick(
                    "${chants.size} ${if (chants.size == 1) "canto" else "cantos"}",
                    "${chants.size} ${if (chants.size == 1) "chant" else "chants"}",
                    "${chants.size} ${if (chants.size == 1) "canto" else "cantos"}"
                ),
                imageHeight = 180.dp
            )
        }
        if (chants.isEmpty()) {
            item(key = "chant_empty") {
                AppEmptyState(
                    title = language.pick("Sin cantos", "No chants", "Sem cantos"),
                    body = language.pick(
                        "El repertorio no está disponible.",
                        "The repertoire is not available.",
                        "O repertório não está disponível."
                    ),
                    icon = Icons.Outlined.MusicNote
                )
            }
        } else {
            items(chants, key = { it.id }) { chant ->
                ChantCard(
                    chant = chant,
                    expanded = chant.id == selectedChantId,
                    playing = chant.id == playingChantId,
                    language = language,
                    onSelect = { onSelect(chant) },
                    onTogglePlayback = { onTogglePlayback(chant) },
                    modifier = Modifier.padding(horizontal = design.spacing.gutter)
                )
            }
        }
    }
}

@Composable
private fun ChantCard(
    chant: GregorianChantItem,
    expanded: Boolean,
    playing: Boolean,
    language: AppLanguage,
    onSelect: () -> Unit,
    onTogglePlayback: () -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val name = chantDisplayTitle(chant.title)
    val turn = if (expanded) 90f else 0f
    AppGlassSurface(
        modifier = modifier.fillMaxWidth(),
        onClick = onSelect,
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Column(
            Modifier.semantics {
                stateDescription = if (expanded) language.pick("Expandida", "Expanded", "Expandida")
                else language.pick("Contraída", "Collapsed", "Recolhida")
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.MusicNote, null, tint = p.textPrimary, modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(name, style = styles.headline, color = p.textPrimary)
                    Spacer(Modifier.height(2.dp))
                    Text(chant.liturgicalSeason.get(language), style = styles.footnote, color = p.textSecondary)
                }
                Spacer(Modifier.width(12.dp))
                AppRoundPlayButton(
                    playing = playing,
                    onClick = onTogglePlayback,
                    contentDescription = if (playing) language.pick("Detener", "Stop", "Parar")
                    else language.pick("Leer letra latina de $name · voz sintética", "Read Latin lyrics of $name · synthetic voice", "Ler letra latina de $name · voz sintética")
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(chant.description.get(language), style = styles.subhead, color = p.textSecondary)
            Spacer(Modifier.height(8.dp))
            Text(
                language.pick("Lectura de la letra latina con voz sintética; no es canto grabado.", "Latin lyrics read by a synthetic voice; this is not recorded singing.", "Leitura da letra latina com voz sintética; não é canto gravado."),
                style = styles.footnote,
                color = p.textSecondary
            )
            Spacer(Modifier.height(14.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    language.pick("Letra y traducción", "Lyrics and translation", "Letra e tradução"),
                    style = styles.footnote.copy(fontWeight = FontWeight.Medium),
                    color = p.textPrimary,
                    modifier = Modifier.weight(1f)
                )
                AppChevron(Modifier.graphicsLayer { rotationZ = turn })
            }
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(tween(design.motion.mediumMillis)) + fadeIn(tween(design.motion.mediumMillis)),
                exit = shrinkVertically(tween(design.motion.fastMillis)) + fadeOut(tween(design.motion.fastMillis))
            ) {
                Column(Modifier.padding(top = 16.dp)) {
                    Text(language.pick("Latín", "Latin", "Latim"), style = styles.eyebrow, color = p.textSecondary)
                    Spacer(Modifier.height(6.dp))
                    Text(chant.latinLyrics, style = styles.reading, color = p.textPrimary)
                    Spacer(Modifier.height(18.dp))
                    Text(language.pick("Traducción", "Translation", "Tradução"), style = styles.eyebrow, color = p.textSecondary)
                    Spacer(Modifier.height(6.dp))
                    Text(chant.translatedLyrics.get(language), style = styles.reading, color = p.textSecondary)
                }
            }
        }
    }
}
