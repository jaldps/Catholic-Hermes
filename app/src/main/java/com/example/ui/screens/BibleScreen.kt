package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.view.accessibility.AccessibilityManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.sp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.models.BibleBook
import com.example.data.models.BibleVerse
import com.example.data.models.BibleVersionInfo
import com.example.data.models.DailyMassReading
import com.example.data.models.Testament
import com.example.data.news.ReadingsResult
import com.example.data.news.VaticanGospelFetcher
import com.example.data.prefs.ReadingPosition
import com.example.data.repository.BibleData
import com.example.data.repository.BibleDownloadManager
import com.example.data.repository.CatholicDataRepository
import com.example.data.xref.VerseRef
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppGroupedRow
import com.example.ui.design.AppHideTabBar
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppLargeTitleHeader
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppMenuChip
import com.example.ui.design.AppMenuItem
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppProgressRing
import com.example.ui.design.AppSearchBar
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.AppSeasonDot
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.design.AppSheet
import com.example.ui.design.AppKitChip
import com.example.ui.design.GroupPosition
import com.example.ui.design.appDockInset
import com.example.ui.design.appScreenInsets
import com.example.ui.design.groupedItem
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenLiturgicalColor
import com.example.ui.design.lumenPress
import com.example.ui.design.rememberAssetBitmap
import com.example.ui.design.rememberFirstAsset
import com.example.ui.design.rememberHeroMeta
import com.example.ui.design.softShadow
import com.example.ui.design.AppChevron
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import com.example.ui.theme.ReaderFontChoice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** Reader type size: the bounds and step the two size buttons move between. */
private const val MinFontScale = 0.85f
private const val MaxFontScale = 1.45f
private const val FontScaleStep = 0.15f

/** Verse search only runs once the query is long enough to mean something. */
private const val MinSearchLength = 3

/** The tab root's title and segmented control, the first rows of every list. */
internal const val BibleHeaderItems = 2

/** Text inset of the reading column; a little wider than the 20dp gutter, as a book's margin. */
private val ReadingInset = 24.dp

/** Spanish and Portuguese cite 3,16; English 3:16. */
internal fun citationOf(bookName: String, chapter: Int, verse: Int, language: AppLanguage): String =
    "$bookName $chapter${if (language == AppLanguage.EN) ":" else ","}$verse"

/** A book's name without its parenthetical alias ("Sirácida (Eclesiástico)" → "Sirácida"). */
private fun BibleBook.shortName(language: AppLanguage): String =
    name.get(language).substringBefore(" (").trim()

// ------------------------------------------------------------ reader settings

/**
 * The reader's own text settings: size on top of the app's text scale, the
 * reading face and the line spacing. Persisted on the device, so the Bible,
 * the Mass readings and the study passages always open the way they were left.
 */
internal data class ReaderSettings(
    val scale: Float = 1f,
    val font: ReaderFontChoice = ReaderFontChoice.FOLLOW_APP,
    /** 0 compact · 1 normal · 2 airy. */
    val spacing: Int = 1
) {
    val lineFactor: Float get() = when (spacing) { 0 -> 0.92f; 2 -> 1.14f; else -> 1f }
}

/** Device-local store for [ReaderSettings] and the exact verse the reader stopped at. */
private object BibleReaderStore {
    private const val FILE = "bible_reader"

    fun load(context: Context): ReaderSettings = runCatching {
        val p = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
        ReaderSettings(
            scale = p.getFloat("scale", 1f).coerceIn(MinFontScale, MaxFontScale),
            font = runCatching { ReaderFontChoice.valueOf(p.getString("font", null) ?: "") }
                .getOrDefault(ReaderFontChoice.FOLLOW_APP),
            spacing = p.getInt("spacing", 1).coerceIn(0, 2)
        )
    }.getOrDefault(ReaderSettings())

    fun save(context: Context, s: ReaderSettings) {
        runCatching {
            context.getSharedPreferences(FILE, Context.MODE_PRIVATE).edit()
                .putFloat("scale", s.scale).putString("font", s.font.name).putInt("spacing", s.spacing).apply()
        }
    }

    fun saveVerse(context: Context, bookId: String, chapter: Int, verse: Int) {
        runCatching {
            context.getSharedPreferences(FILE, Context.MODE_PRIVATE).edit()
                .putString("place", "$bookId:$chapter:$verse").apply()
        }
    }

    /** The verse last at the top of the page, if it belongs to [bookId] [chapter]. */
    fun loadVerse(context: Context, bookId: String, chapter: Int): Int? = runCatching {
        val parts = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
            .getString("place", null)?.split(':') ?: return null
        if (parts.size == 3 && parts[0] == bookId && parts[1].toIntOrNull() == chapter) parts[2].toIntOrNull() else null
    }.getOrNull()
}

private fun TextUnit.scaledBy(factor: Float): TextUnit = if (isSpecified) this * factor else this

private fun TextStyle.asReading(family: FontFamily?, scale: Float, line: Float): TextStyle = copy(
    fontFamily = family ?: fontFamily,
    fontSize = fontSize.scaledBy(scale),
    lineHeight = lineHeight.scaledBy(scale * line)
)

/**
 * Re-cuts the reading styles for the reader's own size, face and spacing, so
 * every passage in the Bible area (chapters, the Mass readings, study texts,
 * a verse in its sheet) follows the one "Aa" choice. The chrome is untouched.
 */
@Composable
internal fun ReadingTypography(settings: ReaderSettings, content: @Composable () -> Unit) {
    val language = LocalAppDesign.current.language
    val base = LocalAppTextStyles.current
    val styles = remember(base, settings, language) {
        val family = settings.font.family(base.reading.fontFamily, language)
        base.copy(
            reading = base.reading.asReading(family, settings.scale, settings.lineFactor),
            scripture = base.scripture.asReading(settings.font.family(base.scripture.fontFamily, language), settings.scale, settings.lineFactor),
            quote = base.quote.asReading(settings.font.family(base.quote.fontFamily, language), settings.scale, settings.lineFactor),
            verseNumber = base.verseNumber.asReading(null, settings.scale, 1f)
        )
    }
    CompositionLocalProvider(LocalAppTextStyles provides styles, content = content)
}

// ------------------------------------------------------------------ speech

/**
 * Reads a list of passages aloud one after another on the shared
 * [LiturgicalTtsManager], exposing which one is being read so the page can
 * follow it. Built on the manager's public API: each passage is spoken with
 * `speakSingle`, and the next starts when `isPlaying` falls back to false.
 */
@Stable
internal class SpeechQueue(val tts: LiturgicalTtsManager?) {
    var segments by mutableStateOf<List<String>>(emptyList())
    var index by mutableStateOf<Int?>(null)
    var active by mutableStateOf(false)
    /** Identifies what is queued (a chapter, today's Mass) so a page knows it is the one playing. */
    var owner by mutableStateOf<String?>(null)
    val available: Boolean get() = tts != null

    fun play(owner: String, list: List<String>, from: Int = 0) {
        if (tts == null || list.isEmpty()) return
        this.owner = owner
        segments = list
        index = from.coerceIn(0, list.lastIndex)
        active = true
    }

    fun pause() {
        active = false
        tts?.stop()
    }

    fun stop() {
        active = false
        index = null
        owner = null
        tts?.stop()
    }

    fun isPlaying(owner: String) = active && this.owner == owner
}

@Composable
internal fun rememberSpeechQueue(tts: LiturgicalTtsManager?): SpeechQueue {
    val queue = remember(tts) { SpeechQueue(tts) }
    LaunchedEffect(queue, queue.active, queue.index, queue.segments) {
        val engine = queue.tts ?: return@LaunchedEffect
        val i = queue.index ?: return@LaunchedEffect
        if (!queue.active) return@LaunchedEffect
        if (i !in queue.segments.indices) {
            queue.stop()
            return@LaunchedEffect
        }
        engine.speakSingle(queue.segments[i])
        // The engine was not ready (or the text was blank): stop rather than
        // racing through the queue in silence.
        if (!engine.isPlaying.value) {
            queue.active = false
            return@LaunchedEffect
        }
        engine.isPlaying.first { !it }
        delay(260)
        if (queue.active && queue.index == i) queue.index = i + 1
    }
    DisposableEffect(queue) { onDispose { if (queue.active) queue.tts?.stop() } }
    return queue
}

// ------------------------------------------------------------------ screen

internal fun verseIndexForVisibleItem(itemIndex: Int, headerCount: Int, verseCount: Int): Int? =
    if (verseCount <= 0) null else (itemIndex - headerCount).coerceIn(0, verseCount - 1)

private val LocalBibleEditionLabel = staticCompositionLocalOf { "" }

@Composable
fun BibleScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    dao: SanctaFidesDao? = null,
    ttsManager: LiturgicalTtsManager? = null,
    /** 0 reader · 1 missal · 2 versions · 3 study. */
    initialMainTab: Int = 0,
    /** Deep-link: preselected book id (e.g. "gen"), or null for the directory. */
    initialBookId: String? = null,
    /** Deep-link: preselected chapter (1-based), or null for chapter 1. */
    initialChapter: Int? = null,
    /** Deep-link: verse to highlight/scroll to on open, or null for none. */
    initialVerseStart: Int? = null,
    modifier: Modifier = Modifier,
    onExitContext: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val insets = AppEdgeToEdge()
    val activeEdition by repository.activeBibleEditionId.collectAsState()

    var selectedMainTab by rememberSaveable(initialMainTab) { mutableIntStateOf(initialMainTab) }
    // Deep-link state is keyed so a new link re-preselects, while ordinary
    // in-screen navigation is untouched; all of it survives rotation.
    var selectedBookId by rememberSaveable(initialBookId, initialChapter) {
        mutableStateOf(initialBookId?.takeIf { id -> BibleData.catholicCanonBooks.any { it.id == id } })
    }
    var selectedChapterNumber by rememberSaveable(initialBookId, initialChapter) {
        mutableIntStateOf((initialChapter ?: 1).coerceAtLeast(1))
    }
    var focusVerse by rememberSaveable(initialBookId, initialChapter, initialVerseStart) { mutableStateOf(initialVerseStart) }
    var studyVerse by rememberSaveable(initialBookId, initialChapter, initialVerseStart) { mutableStateOf(initialVerseStart) }
    var scrollVerse by rememberSaveable { mutableStateOf<Int?>(null) }
    // The trail of followed references, "book:chapter:verse;…", newest last.
    var trail by rememberSaveable { mutableStateOf("") }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedFilter by rememberSaveable { mutableIntStateOf(0) }
    var settings by remember { mutableStateOf(BibleReaderStore.load(context)) }
    val updateSettings: (ReaderSettings) -> Unit = {
        settings = it
        BibleReaderStore.save(context, it)
    }
    val speech = rememberSpeechQueue(ttsManager)

    val selectedBook = selectedBookId?.let { id -> BibleData.catholicCanonBooks.firstOrNull { it.id == id } }

    val openBook: (BibleBook, Int, Int?, Int?) -> Unit = { book, chapter, focus, scroll ->
        trail = ""
        selectedBookId = book.id
        selectedChapterNumber = chapter.coerceAtLeast(1)
        focusVerse = focus
        studyVerse = focus
        scrollVerse = scroll
        selectedMainTab = 0
    }

    val title = currentLanguage.pick("Sagrada Biblia", "Holy Bible", "Bíblia Sagrada")
    val header: LazyListScope.() -> Unit = {
        item(key = "bible_title") {
            AppLargeTitleHeader(title = title, titleTag = "bible_title") {
                AppGlassIconButton(
                    icon = Icons.Outlined.Translate,
                    contentDescription = currentLanguage.pick(
                        "Versiones, traducciones y descargas",
                        "Versions, translations and downloads",
                        "Versões, traduções e downloads"
                    ),
                    onClick = { selectedMainTab = 2 },
                    tag = "bible_versions_row"
                )
            }
        }
        item(key = "bible_screen_tabs") {
            AppSegmentedTabs(
                options = listOf(
                    currentLanguage.pick("Biblia", "Bible", "Bíblia"),
                    currentLanguage.pick("Misa", "Mass", "Missa"),
                    currentLanguage.pick("Estudio", "Study", "Estudo")
                ),
                selectedIndex = when (selectedMainTab) { 1 -> 1; 3 -> 2; else -> 0 },
                onSelect = { selectedMainTab = if (it == 2) 3 else it },
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
    val listBottom = PaddingValues(bottom = insets.calculateBottomPadding() + 32.dp)

    val editionLabel = repository.bibleDownloadManager?.specs?.firstOrNull { it.id == activeEdition }?.name
        ?: when(currentLanguage) { AppLanguage.ES -> "Biblia Platense · Español"; AppLanguage.EN -> "Douay-Rheims-Challoner · English"; AppLanguage.PT -> "Matos Soares · Português" }
    CompositionLocalProvider(LocalBibleEditionLabel provides editionLabel) {
    ReadingTypography(settings) {
        Box(modifier.fillMaxSize().testTag("bible_screen")) {
            val book = selectedBook
            when {
                selectedMainTab == 2 -> VersionsView(
                    versions = repository.bibleVersions,
                    downloadManager = repository.bibleDownloadManager,
                    currentLanguage = currentLanguage,
                    onBack = { selectedMainTab = 0 },
                    activeEditionId = activeEdition,
                    onSelectEdition = { repository.selectBibleEdition(it) }
                )

                selectedMainTab == 0 && book != null -> ReaderHost(
                    repository = repository,
                    book = book,
                    chapterRequest = selectedChapterNumber,
                    currentLanguage = currentLanguage,
                    settings = settings,
                    onSettingsChange = updateSettings,
                    speech = speech,
                    focusVerse = focusVerse,
                    studyVerse = studyVerse,
                    scrollVerse = scrollVerse,
                    onStudyVerse = { studyVerse = it },
                    onNavigate = { target, chapter ->
                        speech.stop()
                        selectedBookId = target.id
                        selectedChapterNumber = chapter
                        focusVerse = null
                        studyVerse = null
                        scrollVerse = null
                    },
                    onFollow = { from, target ->
                        trail = (trail.split(';').filter { it.isNotBlank() } +
                            "${from.bookId}:${from.chapter}:${from.verse}").joinToString(";")
                        speech.stop()
                        selectedBookId = target.bookId
                        selectedChapterNumber = target.chapter
                        focusVerse = target.verse
                        studyVerse = target.verse
                        scrollVerse = null
                    },
                    onBack = {
                        val steps = trail.split(';').filter { it.isNotBlank() }
                        val previous = steps.lastOrNull()?.split(':')
                        speech.stop()
                        if (previous != null && previous.size == 3) {
                            trail = steps.dropLast(1).joinToString(";")
                            selectedBookId = previous[0]
                            selectedChapterNumber = previous[1].toIntOrNull() ?: 1
                            focusVerse = previous[2].toIntOrNull()
                            studyVerse = focusVerse
                            scrollVerse = null
                        } else if (onExitContext != null) {
                            onExitContext()
                        } else {
                            trail = ""
                            selectedBookId = null
                        }
                    }
                )

                else -> Crossfade(
                    targetState = if (selectedMainTab == 3) 3 else if (selectedMainTab == 1) 1 else 0,
                    animationSpec = tween(220),
                    label = "bible_segment"
                ) { segment ->
                    when (segment) {
                        1 -> DailyMissalView(
                            header = header,
                            reading = repository.dailyMassReading,
                            currentLanguage = currentLanguage,
                            speech = speech,
                            contentPadding = listBottom
                        )

                        3 -> if (dao != null && ttsManager != null) {
                            ScriptureStudyScreen(
                                header = header,
                                repository = repository,
                                dao = dao,
                                ttsManager = ttsManager,
                                currentLanguage = currentLanguage
                            )
                        } else {
                            LazyColumn(Modifier.fillMaxSize(), contentPadding = listBottom) {
                                header()
                                item {
                                    AppEmptyState(
                                        title = currentLanguage.pick("Estudio Bíblico", "Scripture Study", "Estudo Bíblico"),
                                        body = currentLanguage.pick(
                                            "Los itinerarios de estudio necesitan el almacenamiento local del dispositivo.",
                                            "The study tracks need the device's local storage.",
                                            "Os itinerários de estudo precisam do armazenamento local do dispositivo."
                                        ),
                                        icon = Icons.AutoMirrored.Outlined.MenuBook
                                    )
                                }
                            }
                        }

                        else -> BookDirectory(
                            header = header,
                            repository = repository,
                            currentLanguage = currentLanguage,
                            searchQuery = searchQuery,
                            onSearchQueryChange = { searchQuery = it },
                            selectedFilter = selectedFilter,
                            onFilterChange = { selectedFilter = it },
                            contentPadding = listBottom,
                            onOpenBook = openBook,
                            onListen = { b, chapter, verse ->
                                openBook(b, chapter, null, verse)
                                val verses = repository.getChapter(b.id, chapter, currentLanguage).verses
                                val start = verse?.let { v -> verses.indexOfFirst { it.number == v } }?.coerceAtLeast(0) ?: 0
                                speech.play("${b.id}:$chapter", verses.map { it.text.get(currentLanguage) }, start)
                            }
                        )
                    }
                }
            }
        }
    }
    }
}

// ------------------------------------------------------------------ directory

@Composable
private fun BookDirectory(
    header: LazyListScope.() -> Unit,
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedFilter: Int,
    onFilterChange: (Int) -> Unit,
    contentPadding: PaddingValues,
    onOpenBook: (BibleBook, Int, Int?, Int?) -> Unit,
    onListen: (BibleBook, Int, Int?) -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current

    // The declared chapter count and the one actually present in the assets can
    // differ, so the directory reports what the repaired data really holds.
    val activeEdition by repository.activeBibleEditionId.collectAsState()
    val chapterCounts = remember(currentLanguage, activeEdition) {
        repository.catholicBooks.associate { it.id to repository.getChapterCount(it.id, currentLanguage) }
    }
    val booksById = remember { repository.catholicBooks.associateBy { it.id } }
    val lastPosition = remember { ReadingPosition.load(context) }
    val resume = lastPosition?.let { pos -> booksById[pos.bookId]?.let { it to pos.chapter } }
    val resumeVerse = remember(resume) { resume?.let { BibleReaderStore.loadVerse(context, it.first.id, it.second) } }

    val query = searchQuery.trim()
    val searching = query.isNotEmpty()
    val filteredBooks = remember(query, selectedFilter, currentLanguage, activeEdition) {
        repository.catholicBooks.filter { book ->
            val testamentName = if (book.testament == Testament.OLD) {
                currentLanguage.pick("Antiguo Testamento", "Old Testament", "Antigo Testamento")
            } else {
                currentLanguage.pick("Nuevo Testamento", "New Testament", "Novo Testamento")
            }
            val matchesSearch = query.isEmpty() ||
                book.name.get(currentLanguage).contains(query, ignoreCase = true) ||
                book.category.displayName.get(currentLanguage).contains(query, ignoreCase = true) ||
                testamentName.contains(query, ignoreCase = true) ||
                (book.isDeuterocanonical && query.length >= 4 &&
                    currentLanguage.pick("deuterocanónico", "deuterocanonical", "deuterocanônico")
                        .contains(query, ignoreCase = true))
            val matchesTab = when (selectedFilter) {
                1 -> book.testament == Testament.OLD
                2 -> book.testament == Testament.NEW
                3 -> book.isDeuterocanonical
                else -> true
            }
            matchesSearch && matchesTab && (chapterCounts[book.id] ?: 0) > 0
        }
    }

    // Full-text search runs off the composition thread: it walks the whole
    // bundled bible and would otherwise stall every keystroke.
    val verseMatchesState by produceState<List<Triple<String, Int, BibleVerse>>?>(null, query, currentLanguage, activeEdition) {
        value = null
        value = if (query.length < MinSearchLength) emptyList()
        else withContext(Dispatchers.Default) { repository.searchBible(query, 20, currentLanguage) }
    }
    val verseMatches = verseMatchesState ?: emptyList()
    val versesLoading = verseMatchesState == null && query.length >= MinSearchLength

    val chaptersLabel: (Int) -> String = { n -> currentLanguage.pick("$n capítulos", "$n chapters", "$n capítulos") }
    val deuteroLabel = currentLanguage.pick("Deuterocanónico", "Deuterocanonical", "Deuterocanônico")

    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        header()

        if (!searching) {
            item(key = "bible_continue") {
                ContinueReadingCard(
                    book = resume?.first ?: booksById["joh"],
                    chapter = resume?.second ?: 1,
                    chapterCount = resume?.first?.let { chapterCounts[it.id] } ?: chapterCounts["joh"] ?: 21,
                    firstRun = resume == null,
                    currentLanguage = currentLanguage,
                    onOpen = { b, ch -> onOpenBook(b, ch, null, if (resume != null) resumeVerse else null) },
                    onListen = { b, ch -> onListen(b, ch, if (resume != null) resumeVerse else null) }
                )
                Spacer(Modifier.height(20.dp))
            }
        }

        item(key = "bible_search") {
            AppSearchBar(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = currentLanguage.pick(
                    "Buscar libros y versículos",
                    "Search books and verses",
                    "Buscar livros e versículos"
                ),
                tag = "bible_search_input"
            )
            if (versesLoading) {
                Column(
                    Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter + 18.dp)
                        .padding(top = 12.dp).testTag("bible_verses_loading")
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth().height(2.dp).clip(RoundedCornerShape(percent = 50)),
                        color = p.textSecondary,
                        trackColor = p.separator
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        currentLanguage.pick("Buscando en la Biblia…", "Searching the Bible…", "Buscando na Bíblia…"),
                        style = styles.footnote, color = p.textSecondary
                    )
                }
            }
        }

        // Section row: what the list is, and the canon it shows.
        item(key = "bible_books_row") {
            val canonOptions = listOf(
                currentLanguage.pick("Todos los libros", "All books", "Todos os livros"),
                currentLanguage.pick("Antiguo Testamento", "Old Testament", "Antigo Testamento"),
                currentLanguage.pick("Nuevo Testamento", "New Testament", "Novo Testamento"),
                currentLanguage.pick("Deuterocanónicos", "Deuterocanon", "Deuterocanônicos")
            )
            val selectedCanon = canonOptions.getOrElse(selectedFilter) { canonOptions.first() }
            Row(
                Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter).padding(top = 28.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    currentLanguage.pick("Libros", "Books", "Livros"),
                    style = styles.title3,
                    color = p.textPrimary,
                    modifier = Modifier.weight(1f).semantics { heading() }
                )
                AppMenuChip(
                    label = selectedCanon,
                    items = canonOptions.mapIndexed { index, label ->
                        AppMenuItem(
                            label = label,
                            icon = if (index == selectedFilter) Icons.Outlined.CheckCircle else null,
                            selected = index == selectedFilter,
                            tag = "bible_canon_$index"
                        ) { onFilterChange(index) }
                    },
                    modifier = Modifier.semantics {
                        contentDescription = currentLanguage.pick("Filtrar libros", "Filter books", "Filtrar livros")
                        stateDescription = selectedCanon
                    },
                    tag = "bible_canon_filter"
                )
            }
        }

        if (filteredBooks.isEmpty() && verseMatches.isEmpty() && !versesLoading) {
            item(key = "bible_empty") {
                AppEmptyState(
                    title = currentLanguage.pick("Sin resultados", "Nothing found", "Sem resultados"),
                    body = currentLanguage.pick(
                        "Ningún libro ni versículo coincide con «$searchQuery».",
                        "No book or verse matches “$searchQuery”.",
                        "Nenhum livro ou versículo corresponde a “$searchQuery”."
                    ),
                    icon = Icons.Outlined.SearchOff
                )
                if (searching) {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        AppSecondaryCapsule(
                            label = currentLanguage.pick("Borrar la búsqueda", "Clear search", "Limpar a busca"),
                            onClick = { onSearchQueryChange("") },
                            tag = "bible_clear_search"
                        )
                    }
                }
            }
        }

        val bookRow: LazyListScope.(List<BibleBook>) -> Unit = { books ->
            itemsIndexed(books, key = { _, b -> "book_${b.id}" }) { index, book ->
                val chapters = chapterCounts[book.id] ?: book.chaptersCount
                AppGroupedRow(
                    title = book.name.get(currentLanguage),
                    position = positionOf(index, books.size),
                    subtitle = if (book.isDeuterocanonical) deuteroLabel else null,
                    trailingText = chaptersLabel(chapters),
                    onClick = { onOpenBook(book, 1, null, null) },
                    tag = "book_item_${book.id}"
                )
            }
        }

        if (searching) {
            if (filteredBooks.isNotEmpty()) bookRow(filteredBooks)
        } else {
            // The canon in its two Testaments, each in its traditional sections.
            filteredBooks.groupBy { it.testament }.forEach { (testament, books) ->
                item(key = "testament_${testament.name}") {
                    Row(
                        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter)
                            .padding(top = 12.dp, bottom = 2.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            if (testament == Testament.OLD) currentLanguage.pick("Antiguo Testamento", "Old Testament", "Antigo Testamento")
                            else currentLanguage.pick("Nuevo Testamento", "New Testament", "Novo Testamento"),
                            style = styles.title2,
                            color = p.textPrimary,
                            modifier = Modifier.weight(1f).semantics { heading() }
                        )
                        Text(
                            currentLanguage.pick("${books.size} libros", "${books.size} books", "${books.size} livros"),
                            style = styles.footnote,
                            color = p.textSecondary
                        )
                    }
                }
                books.groupBy { it.category }.forEach { (category, inCategory) ->
                    item(key = "category_${testament.name}_${category.name}") {
                        Text(
                            category.displayName.get(currentLanguage),
                            style = styles.footnote,
                            color = p.textSecondary,
                            modifier = Modifier.padding(start = design.spacing.gutter + 18.dp, top = 18.dp, bottom = 8.dp)
                        )
                    }
                    bookRow(inCategory)
                }
                item(key = "testament_gap_${testament.name}") { Spacer(Modifier.height(20.dp)) }
            }
        }

        if (verseMatches.isNotEmpty()) {
            item(key = "verses_header") {
                Text(
                    currentLanguage.pick("Versículos", "Verses", "Versículos"),
                    style = styles.title3,
                    color = p.textPrimary,
                    modifier = Modifier.padding(horizontal = design.spacing.gutter).padding(top = 28.dp, bottom = 10.dp)
                        .semantics { heading() }
                )
            }
            itemsIndexed(verseMatches, key = { _, m -> "verse_${m.first}_${m.second}_${m.third.number}" }) { index, (bookId, chapter, verse) ->
                val book = booksById[bookId] ?: return@itemsIndexed
                val text = verse.text.get(currentLanguage).let { if (it.length > 160) it.take(160).trimEnd() + "…" else it }
                VerseResultRow(
                    citation = citationOf(book.name.get(currentLanguage), chapter, verse.number, currentLanguage),
                    snippet = text,
                    query = query,
                    position = positionOf(index, verseMatches.size),
                    onClick = { onOpenBook(book, chapter, verse.number, null) },
                    tag = "bible_verse_result_$index"
                )
            }
        }
    }
}

internal fun positionOf(index: Int, size: Int): GroupPosition = when {
    size <= 1 -> GroupPosition.Single
    index == 0 -> GroupPosition.First
    index == size - 1 -> GroupPosition.Last
    else -> GroupPosition.Middle
}

/** One verse found by search: its citation, then the words, the match in ink. */
@Composable
private fun VerseResultRow(
    citation: String,
    snippet: String,
    query: String,
    position: GroupPosition,
    onClick: () -> Unit,
    tag: String
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val emphasised = remember(snippet, query, p) {
        buildAnnotatedString {
            append(snippet)
            if (query.isNotBlank()) {
                var from = 0
                while (true) {
                    val at = snippet.indexOf(query, from, ignoreCase = true)
                    if (at < 0) break
                    addStyle(SpanStyle(color = p.textPrimary, fontWeight = FontWeight.SemiBold), at, at + query.length)
                    from = at + query.length
                }
            }
        }
    }
    Column(Modifier.groupedItem(position).testTag(tag)) {
        Row(
            Modifier.fillMaxWidth().lumenPress(onClick, 0.99f).heightIn(min = design.spacing.rowMinHeight)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(citation, style = styles.body, color = p.textPrimary)
                Spacer(Modifier.height(3.dp))
                Text(emphasised, style = styles.subhead, color = p.textSecondary, maxLines = 3, overflow = TextOverflow.Ellipsis)
            }
            Spacer(Modifier.width(10.dp))
            AppChevron()
        }
        if (position == GroupPosition.First || position == GroupPosition.Middle) {
            Box(Modifier.fillMaxWidth().padding(start = 18.dp).height(0.5.dp).background(p.separator))
        }
    }
}

/**
 * The art-led invitation at the top of the Bible: where you stopped, with the
 * chapter's progress through its book, or — the first time — where to begin.
 */
@Composable
private fun ContinueReadingCard(
    book: BibleBook?,
    chapter: Int,
    chapterCount: Int,
    firstRun: Boolean,
    currentLanguage: AppLanguage,
    onOpen: (BibleBook, Int) -> Unit,
    onListen: (BibleBook, Int) -> Unit
) {
    book ?: return
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val art = rememberFirstAsset("heroes/lumen/bible.jpg", "heroes/church_window_light.jpg")
    val bitmap = rememberAssetBitmap(art, 900)
    val meta = rememberHeroMeta(art)
    val shape = design.shapes.card
    val title = if (firstRun) {
        currentLanguage.pick("Evangelio según San Juan", "The Gospel of John", "Evangelho segundo São João")
    } else "${book.shortName(currentLanguage)} $chapter"
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .heightIn(min = 196.dp)
            .lumenPress({ onOpen(book, chapter) }, 0.98f)
            .softShadow(shape, p.shadow)
            .clip(shape)
            .background(p.raised)
            .testTag("bible_continue_reading")
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = meta.alignment,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.86f) }),
                modifier = Modifier.matchParentSize().testTag("bible_hero_photo")
            )
        }
        Box(
            Modifier.matchParentSize().background(
                Brush.verticalGradient(
                    0f to Color.Black.copy(alpha = 0.05f),
                    0.38f to Color.Transparent,
                    1f to Color.Black.copy(alpha = if (design.isDark) 0.74f else 0.66f)
                )
            )
        )
        val configuration = LocalConfiguration.current
        val stacked = configuration.screenWidthDp < 360 || styles.subhead.fontSize.value * LocalDensity.current.fontScale > 22f
        val readingDetails: @Composable (Modifier) -> Unit = { detailsModifier ->
            Column(detailsModifier) {
                Text(
                    if (firstRun) currentLanguage.pick("Empieza aquí", "Start here", "Comece aqui")
                    else currentLanguage.pick("Seguir leyendo", "Continue reading", "Continuar a ler"),
                    style = styles.caption,
                    color = Color.White.copy(alpha = 0.86f)
                )
                Spacer(Modifier.height(2.dp))
                Text(title, style = styles.title2, color = Color.White, modifier = Modifier.testTag("bible_continue_title"))
                Spacer(Modifier.height(2.dp))
                Text(
                    if (firstRun) currentLanguage.pick("Toca para leer · Juan 1", "Tap to read · John 1", "Toque para ler · João 1")
                    else currentLanguage.pick(
                        "Capítulo $chapter de $chapterCount",
                        "Chapter $chapter of $chapterCount",
                        "Capítulo $chapter de $chapterCount"
                    ),
                    style = styles.footnote,
                    color = Color.White.copy(alpha = 0.86f)
                )
                if (!firstRun) {
                    Spacer(Modifier.height(10.dp))
                    AppProgressBar(
                        progress = chapter.toFloat() / chapterCount.coerceAtLeast(1),
                        modifier = Modifier.fillMaxWidth(0.62f),
                        color = Color.White,
                        track = Color.White.copy(alpha = 0.3f),
                        height = 3.dp
                    )
                }
            }
        }
        val listenControl: @Composable () -> Unit = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppGlassIconButton(
                    icon = Icons.Filled.PlayArrow,
                    contentDescription = currentLanguage.pick("Escuchar el capítulo", "Listen to the chapter", "Ouvir o capítulo"),
                    onClick = { onListen(book, chapter) },
                    onImage = true,
                    tag = "bible_continue_listen"
                )
                Spacer(Modifier.height(4.dp))
                Text(currentLanguage.pick("Escuchar", "Listen", "Ouvir"), style = styles.footnote, color = Color.White)
            }
        }
        if (stacked) {
            Column(Modifier.align(Alignment.BottomStart).fillMaxWidth().padding(18.dp)) {
                readingDetails(Modifier.fillMaxWidth())
                Spacer(Modifier.height(12.dp))
                listenControl()
            }
        } else {
            Row(
                Modifier.align(Alignment.BottomStart).fillMaxWidth().padding(start = 18.dp, end = 14.dp, bottom = 16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                readingDetails(Modifier.weight(1f))
                Spacer(Modifier.width(12.dp))
                listenControl()
            }
        }
    }
}

// --------------------------------------------------------------------- reader

/** Where the reader is and what it can move to: the whole canon, in order. */
private fun neighbour(book: BibleBook, chapter: Int, chapterCount: Int, step: Int, countOf: (String) -> Int): Pair<BibleBook, Int>? {
    val canon = BibleData.catholicCanonBooks
    val target = chapter + step
    if (target in 1..chapterCount) return book to target
    val i = canon.indexOfFirst { it.id == book.id }
    val other = canon.getOrNull(i + step) ?: return null
    return if (step > 0) other to 1 else other to countOf(other.id).coerceAtLeast(1)
}

@Composable
private fun ReaderHost(
    repository: CatholicDataRepository,
    book: BibleBook,
    chapterRequest: Int,
    currentLanguage: AppLanguage,
    settings: ReaderSettings,
    onSettingsChange: (ReaderSettings) -> Unit,
    speech: SpeechQueue,
    focusVerse: Int?,
    studyVerse: Int?,
    scrollVerse: Int?,
    onStudyVerse: (Int) -> Unit,
    onNavigate: (BibleBook, Int) -> Unit,
    onFollow: (VerseRef, VerseRef) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val landscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val countOf: (String) -> Int = { id -> repository.getChapterCount(id, currentLanguage) }
    val activeEdition by repository.activeBibleEditionId.collectAsState()
    val availableChapters = remember(book.id, currentLanguage, activeEdition) { countOf(book.id) }
    if (availableChapters == 0) {
        AppBackHandler(onBack = onBack)
        Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(currentLanguage.pick("Libro no disponible en esta edición", "Book unavailable in this edition", "Livro indisponível nesta edição"), style = LocalAppTextStyles.current.title2, color = LocalAppDesign.current.palette.textPrimary)
            Text(currentLanguage.pick("Esta edición no contiene este libro. Elige la Biblia católica incluida para leerlo.", "This edition does not contain this book. Choose the included Catholic Bible to read it.", "Esta edição não contém este livro. Escolha a Bíblia católica incluída para lê-lo."), style = LocalAppTextStyles.current.callout, color = LocalAppDesign.current.palette.textSecondary)
            AppPrimaryCapsule(currentLanguage.pick("Usar Biblia incluida", "Use included Bible", "Usar Bíblia incluída"), onClick = { repository.selectBibleEdition("bundled") }, tag = "bible_use_bundled")
            AppSecondaryCapsule(currentLanguage.pick("Volver", "Back", "Voltar"), onClick = onBack)
        }
        return
    }
    val chapterCount = availableChapters
    val chapterNumber = chapterRequest.coerceIn(1, chapterCount)
    val verses = remember(book.id, chapterNumber, currentLanguage, activeEdition) {
        repository.getChapter(book.id, chapterNumber, currentLanguage).verses
    }
    var verseForSheet by remember { mutableStateOf<BibleVerse?>(null) }
    var showChapters by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }

    AppHideTabBar(true)
    AppInlineTitle("${book.shortName(currentLanguage)} $chapterNumber")
    // Back first retraces followed references, then leaves the book.
    AppBackHandler(onBack = onBack)
    LaunchedEffect(book.id, chapterNumber) { ReadingPosition.save(context, book.id, chapterNumber) }

    val previous = remember(book.id, chapterNumber, chapterCount) { neighbour(book, chapterNumber, chapterCount, -1, countOf) }
    val next = remember(book.id, chapterNumber, chapterCount) { neighbour(book, chapterNumber, chapterCount, +1, countOf) }
    val follow: (VerseRef) -> Unit = { target ->
        verseForSheet = null
        onFollow(VerseRef(book.id, chapterNumber, studyVerse ?: focusVerse ?: 1), target)
    }

    val reader: @Composable (Modifier) -> Unit = { m ->
        key(book.id, chapterNumber, activeEdition) {
            ChapterReader(
                book = book,
                chapterNumber = chapterNumber,
                chapterCount = chapterCount,
                verses = verses,
                currentLanguage = currentLanguage,
                speech = speech,
                focusVerse = focusVerse,
                selectedVerse = if (landscape) studyVerse else null,
                scrollVerse = scrollVerse,
                previous = previous,
                next = next,
                onNavigate = onNavigate,
                onChooseChapter = { showChapters = true },
                onOpenText = { showText = true },
                onVerseClick = { if (landscape) onStudyVerse(it.number) else verseForSheet = it },
                modifier = m
            )
        }
    }

    if (landscape) {
        // Turned on its side, the reader becomes a study: the text on the
        // left, the whole Bible's web of references on the right.
        Row(Modifier.fillMaxSize()) {
            reader(Modifier.weight(0.46f))
            CrossReferenceStudy(
                repository = repository,
                book = book,
                chapterNumber = chapterNumber,
                verse = studyVerse,
                currentLanguage = currentLanguage,
                onFollow = follow,
                modifier = Modifier.weight(0.54f).fillMaxHeight()
            )
        }
    } else {
        reader(Modifier.fillMaxSize())
    }

    verseForSheet?.let { verse ->
        AppSheet(onDismiss = { verseForSheet = null }) {
            VerseSheet(
                repository = repository,
                book = book,
                chapterNumber = chapterNumber,
                verse = verse,
                currentLanguage = currentLanguage,
                onListen = if (speech.available) ({
                    speech.play("verse", listOf(verse.text.get(currentLanguage)))
                }) else null,
                onFollow = follow
            )
        }
    }
    if (showChapters) {
        ChapterSheet(
            book = book,
            chapterNumber = chapterNumber,
            currentLanguage = currentLanguage,
            countOf = countOf,
            onSelect = { b, ch ->
                showChapters = false
                onNavigate(b, ch)
            },
            onDismiss = { showChapters = false }
        )
    }
    if (showText) {
        TextSettingsSheet(
            settings = settings,
            currentLanguage = currentLanguage,
            onChange = onSettingsChange,
            onDismiss = { showText = false }
        )
    }
}

@Composable
private fun ChapterReader(
    book: BibleBook,
    chapterNumber: Int,
    chapterCount: Int,
    verses: List<BibleVerse>,
    currentLanguage: AppLanguage,
    speech: SpeechQueue,
    focusVerse: Int?,
    selectedVerse: Int?,
    scrollVerse: Int?,
    previous: Pair<BibleBook, Int>?,
    next: Pair<BibleBook, Int>?,
    onNavigate: (BibleBook, Int) -> Unit,
    onChooseChapter: () -> Unit,
    onOpenText: () -> Unit,
    onVerseClick: (BibleVerse) -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val density = LocalDensity.current
    val listState = rememberLazyListState()
    val top = appScreenInsets().calculateTopPadding()
    val bottomInset = appDockInset()
    var readerBarHeight by remember { mutableStateOf(bottomInset + 88.dp) }
    val owner = "${book.id}:$chapterNumber"
    val playingIndex = if (speech.owner == owner) speech.index else null
    val listening = speech.isPlaying(owner)
    // Opening block first, then one item per verse.
    val leading = 1

    LaunchedEffect(focusVerse, scrollVerse, verses) {
        val target = focusVerse ?: scrollVerse ?: return@LaunchedEffect
        val i = verses.indexOfFirst { it.number == target }
        if (i >= 0) listState.scrollToItem(leading + i, -with(density) { 96.dp.roundToPx() })
    }
    // The page follows the verse being read.
    LaunchedEffect(playingIndex) {
        val i = playingIndex ?: return@LaunchedEffect
        if (i in verses.indices) listState.animateScrollToItem(leading + i, -with(density) { 140.dp.roundToPx() })
    }
    // Remember the exact verse at the top of the page for "Continue reading".
    LaunchedEffect(listState, verses) {
        snapshotFlow { listState.firstVisibleItemIndex }.collect { index ->
            val verse = verses.getOrNull(verseIndexForVisibleItem(index, leading, verses.size) ?: return@collect) ?: return@collect
            BibleReaderStore.saveVerse(context, book.id, chapterNumber, verse.number)
        }
    }

    // Chrome steps aside while reading down and returns on the way up, at the
    // ends of the chapter, while listening, and always for TalkBack users.
    val touchExploration = remember {
        (context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager)?.isTouchExplorationEnabled == true
    }
    var chromeShown by remember { mutableStateOf(true) }
    val connection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -8f) chromeShown = false else if (available.y > 8f) chromeShown = true
                return Offset.Zero
            }
        }
    }
    val atEnd by remember { derivedStateOf { !listState.canScrollForward } }
    val atTop by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset < 40 } }
    val showChrome = chromeShown || atEnd || atTop || touchExploration || listening
    val progress by remember(chapterNumber, chapterCount) {
        derivedStateOf {
            val info = listState.layoutInfo
            val total = info.totalItemsCount.coerceAtLeast(1)
            val within = if (!listState.canScrollForward) 1f else {
                val first = info.visibleItemsInfo.firstOrNull()
                val partial = if (first != null && first.size > 0) listState.firstVisibleItemScrollOffset.toFloat() / first.size else 0f
                ((listState.firstVisibleItemIndex + partial) / total).coerceIn(0f, 1f)
            }
            ((chapterNumber - 1) + within) / chapterCount
        }
    }

    Box(modifier.fillMaxSize().nestedScroll(connection)) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize().testTag("bible_reader"),
            contentPadding = PaddingValues(top = top + 4.dp, bottom = readerBarHeight + 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item(key = "opening") {
                val edition = LocalBibleEditionLabel.current
                Text(edition, style = styles.footnote, color = p.textSecondary, modifier = Modifier.padding(horizontal = ReadingInset).testTag("bible_active_edition"))
                ChapterOpening(book, chapterNumber, chapterCount, currentLanguage)
            }
            if (verses.isEmpty()) {
                item(key = "empty") {
                    AppEmptyState(
                        title = currentLanguage.pick("Capítulo no disponible", "Chapter unavailable", "Capítulo não disponível"),
                        body = currentLanguage.pick(
                            "Este capítulo no está disponible en la edición seleccionada.",
                            "This chapter is not available in the selected edition.",
                            "Este capítulo não está disponível na edição selecionada."
                        ),
                        icon = Icons.Outlined.AutoStories
                    )
                    if (next != null) {
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            AppSecondaryCapsule(
                                label = currentLanguage.pick("Capítulo siguiente", "Next chapter", "Próximo capítulo"),
                                onClick = { onNavigate(next.first, next.second) }
                            )
                        }
                    }
                }
            } else {
                itemsIndexed(verses, key = { _, v -> v.number }) { index, verse ->
                    ReaderVerse(
                        verse = verse,
                        text = verse.text.get(currentLanguage),
                        marked = verse.number == focusVerse || verse.number == selectedVerse,
                        reading = index == playingIndex,
                        currentLanguage = currentLanguage,
                        onClick = { onVerseClick(verse) }
                    )
                }
                item(key = "end") {
                    Text(
                        currentLanguage.pick(
                            "${book.shortName(currentLanguage)} $chapterNumber · ${verses.size} versículos",
                            "${book.shortName(currentLanguage)} $chapterNumber · ${verses.size} verses",
                            "${book.shortName(currentLanguage)} $chapterNumber · ${verses.size} versículos"
                        ),
                        style = styles.footnote,
                        color = p.textSecondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(top = 28.dp, bottom = 8.dp)
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = showChrome,
            enter = slideInVertically(tween(260)) { it / 2 } + fadeIn(tween(220)),
            exit = slideOutVertically(tween(260)) { it / 2 } + fadeOut(tween(200)),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            ReaderBar(
                book = book,
                chapterNumber = chapterNumber,
                chapterCount = chapterCount,
                progress = progress,
                currentLanguage = currentLanguage,
                listening = listening,
                canListen = speech.available && verses.isNotEmpty(),
                previous = previous,
                next = next,
                pulseNext = atEnd && next != null && verses.isNotEmpty(),
                onPrevious = { previous?.let { onNavigate(it.first, it.second) } },
                onNext = { next?.let { onNavigate(it.first, it.second) } },
                onListen = {
                    if (listening) speech.pause()
                    else {
                        val firstVisible = (listState.firstVisibleItemIndex - leading).coerceAtLeast(0)
                        val from = if (speech.owner == owner) speech.index ?: firstVisible else firstVisible
                        speech.play(owner, verses.map { it.text.get(currentLanguage) }, from.coerceAtMost(verses.lastIndex))
                    }
                },
                onChooseChapter = onChooseChapter,
                onOpenText = onOpenText,
                modifier = Modifier
                    .onSizeChanged { readerBarHeight = with(density) { it.height.toDp() } }
                    .padding(bottom = bottomInset + 12.dp)
            )
        }
    }
}

/** The head of a chapter: book, chapter, and on the first chapter what the book is. */
@Composable
private fun ChapterOpening(book: BibleBook, chapterNumber: Int, chapterCount: Int, currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(
        Modifier.widthIn(max = design.spacing.readingMaxWidth).fillMaxWidth()
            .padding(horizontal = ReadingInset).padding(top = 12.dp, bottom = 22.dp)
    ) {
        Text(book.name.get(currentLanguage), style = styles.eyebrow, color = p.textSecondary)
        Spacer(Modifier.height(2.dp))
        Text(
            currentLanguage.pick("Capítulo $chapterNumber", "Chapter $chapterNumber", "Capítulo $chapterNumber"),
            style = styles.title1,
            color = p.textPrimary,
            modifier = Modifier.semantics { heading() }
        )
        if (chapterNumber == 1) {
            Spacer(Modifier.height(12.dp))
            Text(book.summary.get(currentLanguage), style = styles.callout, color = p.textSecondary)
            if (book.isDeuterocanonical) {
                Spacer(Modifier.height(12.dp))
                DeuteroNote(currentLanguage)
            }
        }
    }
}

@Composable
private fun DeuteroNote(currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column {
        Box(
            Modifier.lumenGlass(RoundedCornerShape(percent = 50), lifted = false)
                .padding(horizontal = 10.dp, vertical = 3.dp)
        ) {
            Text(
                currentLanguage.pick("Libro deuterocanónico", "Deuterocanonical book", "Livro deuterocanônico"),
                style = styles.caption, color = p.textPrimary
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            currentLanguage.pick(
                "Conservado en el canon católico desde los Concilios de Roma (382) e Hipona (393).",
                "Preserved in the Catholic Canon since the Councils of Rome (382) & Hippo (393).",
                "Conservado no cânon católico desde os Concílios de Roma (382) e Hipona (393)."
            ),
            style = styles.footnote, color = p.textSecondary
        )
    }
}

/**
 * One verse at reading measure: the number a quiet superscript, the text in
 * the reader's face. A verse with a patristic gloss carries a small season
 * dot in the margin; a marked verse (followed, searched, being read) a soft
 * wash behind it.
 */
@Composable
private fun ReaderVerse(
    verse: BibleVerse,
    text: String,
    marked: Boolean,
    reading: Boolean,
    currentLanguage: AppLanguage,
    onClick: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val density = LocalDensity.current
    val season = p.accent
    val base = styles.reading
    val body = remember(verse.number, text, base, p) {
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = base.fontSize * 0.6f,
                    baselineShift = BaselineShift.Superscript,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Default,
                    letterSpacing = 0.sp,
                    color = p.textSecondary
                )
            ) { append(verse.number.toString()) }
            append(" ")
            append(text)
        }
    }
    val wash = when {
        reading -> p.textPrimary.copy(alpha = if (design.isDark) 0.10f else 0.07f)
        marked -> p.textPrimary.copy(alpha = if (design.isDark) 0.08f else 0.055f)
        else -> Color.Transparent
    }
    val hasGloss = verse.commentary != null
    Box(
        Modifier
            .widthIn(max = design.spacing.readingMaxWidth)
            .fillMaxWidth()
            .padding(horizontal = ReadingInset - 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(wash)
            .clickable(
                onClickLabel = currentLanguage.pick("Referencias y comentario", "References and commentary", "Referências e comentário"),
                role = Role.Button,
                onClick = onClick
            )
            .semantics {
                if (hasGloss) stateDescription = currentLanguage.pick("Con comentario patrístico", "Has patristic commentary", "Com comentário patrístico")
            }
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .testTag("verse_${verse.number}")
    ) {
        Text(text = body, style = base, color = p.textPrimary)
        if (hasGloss) {
            val firstLine = with(density) { (base.lineHeight.takeIf { it.isSpecified } ?: base.fontSize).toDp() }
            AppSeasonDot(
                season,
                Modifier.offset(x = (-8).dp, y = firstLine / 2 - 2.5.dp),
                size = 5.dp
            )
        }
    }
}

/**
 * The reader's controls on one glass capsule within thumb reach: previous,
 * the chapter (with the book's progress under it), listen, text, next.
 */
@Composable
private fun ReaderBar(
    book: BibleBook,
    chapterNumber: Int,
    chapterCount: Int,
    progress: Float,
    currentLanguage: AppLanguage,
    listening: Boolean,
    canListen: Boolean,
    previous: Pair<BibleBook, Int>?,
    next: Pair<BibleBook, Int>?,
    pulseNext: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onListen: () -> Unit,
    onChooseChapter: () -> Unit,
    onOpenText: () -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val shape = RoundedCornerShape(percent = 50)
    val pulse = remember { Animatable(1f) }
    LaunchedEffect(pulseNext, design.reduceMotion) {
        pulse.snapTo(1f)
        if (pulseNext && !design.reduceMotion) {
            pulse.animateTo(1.14f, tween(160))
            pulse.animateTo(1f, spring(dampingRatio = 0.5f, stiffness = 500f))
        }
    }
    val chapterLabel = currentLanguage.pick("Cap. $chapterNumber / $chapterCount", "Ch. $chapterNumber / $chapterCount", "Cap. $chapterNumber / $chapterCount")
    fun place(target: Pair<BibleBook, Int>?) = target?.let { "${it.first.shortName(currentLanguage)} ${it.second}" } ?: ""

    BoxWithConstraints(modifier.padding(horizontal = design.spacing.gutter).widthIn(max = 560.dp).fillMaxWidth()) {
        val stacked = maxWidth < 320.dp || styles.subhead.fontSize.value * LocalDensity.current.fontScale > 22f
        val barShape = if (stacked) RoundedCornerShape(24.dp) else shape
        val chapterControl: @Composable (Modifier) -> Unit = { chapterModifier ->
            // The chapter, which opens the chapter sheet, over the book's progress.
            Column(
                Modifier
                    .then(chapterModifier)
                    .heightIn(min = 48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(role = Role.Button, onClick = onChooseChapter)
                    .semantics {
                        contentDescription = currentLanguage.pick(
                            "Elegir capítulo. ${book.name.get(currentLanguage)}, capítulo $chapterNumber de $chapterCount",
                            "Choose chapter. ${book.name.get(currentLanguage)}, chapter $chapterNumber of $chapterCount",
                            "Escolher capítulo. ${book.name.get(currentLanguage)}, capítulo $chapterNumber de $chapterCount"
                        )
                    }
                    .testTag("choose_chapter_header_button")
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = if (stacked) Modifier.fillMaxWidth() else Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (stacked) {
                        Text(
                            "${book.shortName(currentLanguage)} $chapterNumber",
                            style = styles.subhead.copy(fontWeight = FontWeight.SemiBold),
                            color = p.textPrimary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f).testTag("bible_reader_chapter_title")
                        )
                    } else {
                        FittedPlace(
                            full = "${book.shortName(currentLanguage)} $chapterNumber",
                            short = "${book.abbreviation} $chapterNumber",
                            style = styles.subhead.copy(fontWeight = FontWeight.SemiBold),
                            color = p.textPrimary,
                            modifier = Modifier.weight(1f, fill = false)
                        )
                    }
                    Spacer(Modifier.width(2.dp))
                    Icon(Icons.Outlined.KeyboardArrowDown, null, tint = p.textSecondary, modifier = Modifier.size(16.dp))
                }
                Spacer(Modifier.height(6.dp))
                AppProgressBar(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .semantics {
                            contentDescription = chapterLabel
                            stateDescription = currentLanguage.pick("Progreso en el libro", "Progress through the book", "Progresso no livro")
                        }
                        .testTag("bible_reader_progress"),
                    color = p.textPrimary.copy(alpha = 0.72f),
                    track = p.separator,
                    height = 3.dp
                )
            }
        }
        val previousControl: @Composable () -> Unit = {
            BarGlyph(
                onClick = onPrevious,
                enabled = previous != null,
                description = currentLanguage.pick("Capítulo anterior: ", "Previous chapter: ", "Capítulo anterior: ") + place(previous),
                tag = "prev_chapter_button"
            ) { tint -> Icon(Icons.AutoMirrored.Outlined.ArrowBackIos, null, tint = tint, modifier = Modifier.size(18.dp).offset(x = 3.dp)) }


        }
        val remainingControls: @Composable () -> Unit = {

            if (canListen) {
                Box(
                    Modifier
                        .size(48.dp)
                        .lumenPress(onListen, 0.9f)
                        .semantics {
                            contentDescription = if (listening) currentLanguage.pick("Pausar la lectura", "Pause reading", "Pausar a leitura")
                            else currentLanguage.pick("Escuchar el capítulo", "Listen to the chapter", "Ouvir o capítulo")
                        }
                        .testTag("bible_reader_listen"),
                    contentAlignment = Alignment.Center
                ) {
                    Box(Modifier.size(44.dp).clip(CircleShape).background(p.action), contentAlignment = Alignment.Center) {
                        Icon(
                            if (listening) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                            null, tint = p.onAction, modifier = Modifier.size(24.dp)
                        )
                    }
                }
        }
        BarGlyph(
            onClick = onOpenText,
            enabled = true,
            description = currentLanguage.pick("Texto: tamaño y tipo de letra", "Text: size and typeface", "Texto: tamanho e tipo de letra"),
            tag = "bible_reader_text_button"
        ) { tint -> Text("Aa", style = styles.headline.copy(fontWeight = FontWeight.Medium), color = tint) }
        Box(Modifier.graphicsLayer { scaleX = pulse.value; scaleY = pulse.value }) {
            BarGlyph(
                onClick = onNext,
                enabled = next != null,
                description = currentLanguage.pick("Capítulo siguiente: ", "Next chapter: ", "Próximo capítulo: ") + place(next),
                tag = "next_chapter_button"
            ) { tint -> Icon(Icons.AutoMirrored.Outlined.ArrowForwardIos, null, tint = tint, modifier = Modifier.size(18.dp)) }
        }
        }
        val surface = Modifier.fillMaxWidth()
            .lumenGlass(barShape, strong = true)
            .background(p.glassStrong.copy(alpha = (p.glassStrong.alpha + 0.12f).coerceAtMost(1f)), barShape)
            .padding(horizontal = 6.dp)
        if (stacked) {
            Column(surface.padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                chapterControl(Modifier.fillMaxWidth().padding(vertical = 4.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                    previousControl()
                    remainingControls()
                }
            }
        } else {
            Row(surface.height(64.dp), verticalAlignment = Alignment.CenterVertically) {
                previousControl()
                chapterControl(Modifier.weight(1f))
                remainingControls()
            }
        }
    }
}

@Composable
private fun BarGlyph(
    onClick: () -> Unit,
    enabled: Boolean,
    description: String,
    tag: String,
    content: @Composable (Color) -> Unit
) {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .size(48.dp)
            .lumenPress(onClick, 0.9f, enabled = enabled)
            .semantics { contentDescription = description }
            .testTag(tag),
        contentAlignment = Alignment.Center
    ) {
        content(if (enabled) p.textPrimary else p.inkQuiet.copy(alpha = 0.6f))
    }
}

/** [full] when it fits on one line, else [short]: never an ellipsis mid-name. */
@Composable
private fun FittedPlace(full: String, short: String, style: TextStyle, color: Color, modifier: Modifier = Modifier) {
    val measurer = rememberTextMeasurer()
    BoxWithConstraints(modifier) {
        val fits = remember(full, style, constraints.maxWidth) {
            measurer.measure(full, style, maxLines = 1).size.width <= constraints.maxWidth
        }
        Text(if (fits) full else short, style = style, color = color, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

/**
 * The chapter sheet: every chapter of the book one tap away, and the whole
 * canon under "Libros", so moving from Génesis 3 to Mateo 5 never leaves the
 * reader.
 */
@Composable
private fun ChapterSheet(
    book: BibleBook,
    chapterNumber: Int,
    currentLanguage: AppLanguage,
    countOf: (String) -> Int,
    onSelect: (BibleBook, Int) -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    var pickerBook by remember { mutableStateOf(book) }
    var tab by remember { mutableIntStateOf(0) }
    val count = remember(pickerBook.id) { countOf(pickerBook.id).coerceAtLeast(1) }
    val counts = remember {
        BibleData.catholicCanonBooks.mapNotNull { b -> countOf(b.id).takeIf { it > 0 }?.let { b.id to it } }.toMap()
    }

    AppSheet(onDismiss = onDismiss, tag = "bible_chapter_sheet") {
        Column(Modifier.padding(horizontal = 24.dp)) {
            Text(pickerBook.name.get(currentLanguage), style = styles.title2, color = p.textPrimary)
            Spacer(Modifier.height(2.dp))
            Text(
                currentLanguage.pick("$count capítulos", "$count chapters", "$count capítulos") +
                    if (pickerBook.isDeuterocanonical) " · " + currentLanguage.pick("Deuterocanónico", "Deuterocanonical", "Deuterocanônico") else "",
                style = styles.footnote, color = p.textSecondary
            )
            Spacer(Modifier.height(8.dp))
            Text(pickerBook.summary.get(currentLanguage), style = styles.subhead, color = p.textSecondary)
        }
        Spacer(Modifier.height(18.dp))
        AppSegmentedTabs(
            options = listOf(
                currentLanguage.pick("Capítulos", "Chapters", "Capítulos"),
                currentLanguage.pick("Libros", "Books", "Livros")
            ),
            selectedIndex = tab,
            onSelect = { tab = it }
        )
        Spacer(Modifier.height(18.dp))
        if (tab == 0) {
            val columns = 6
            val sheetBottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 24.dp
            Column(Modifier.padding(start = design.spacing.gutter, end = design.spacing.gutter, bottom = sheetBottom), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                (1..count).chunked(columns).forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        row.forEach { n ->
                            val current = pickerBook.id == book.id && n == chapterNumber
                            Box(
                                Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .then(
                                        if (current) Modifier.clip(CircleShape).background(p.action)
                                        else Modifier.lumenGlass(CircleShape, lifted = false)
                                    )
                                    .clip(CircleShape)
                                    .clickable(role = Role.Button) { onSelect(pickerBook, n) }
                                    .semantics {
                                        contentDescription = currentLanguage.pick("Capítulo $n", "Chapter $n", "Capítulo $n")
                                        selected = current
                                    }
                                    .testTag("chapter_cell_$n"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "$n",
                                    style = styles.subhead.copy(fontWeight = if (current) FontWeight.SemiBold else FontWeight.Medium),
                                    color = if (current) p.onAction else p.textPrimary
                                )
                            }
                        }
                        repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
            }
        } else {
            BibleData.catholicCanonBooks.groupBy { it.testament }.forEach { (testament, books) ->
                Text(
                    if (testament == Testament.OLD) currentLanguage.pick("Antiguo Testamento", "Old Testament", "Antigo Testamento")
                    else currentLanguage.pick("Nuevo Testamento", "New Testament", "Novo Testamento"),
                    style = styles.footnote, color = p.textSecondary,
                    modifier = Modifier.padding(start = design.spacing.gutter + 18.dp, top = 8.dp, bottom = 8.dp)
                )
                AppListGroup {
                    books.forEach { b ->
                        AppListRow(
                            title = b.name.get(currentLanguage),
                            trailingText = "${counts[b.id] ?: b.chaptersCount}",
                            selected = b.id == pickerBook.id,
                            onClick = {
                                pickerBook = b
                                tab = 0
                            },
                            modifier = Modifier.testTag("sheet_book_${b.id}")
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
            Spacer(Modifier.height(WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 24.dp))
        }
        Spacer(Modifier.height(12.dp))
    }
}

/** "Aa": the reader's size, face and line spacing, applied live behind the sheet. */
@Composable
private fun TextSettingsSheet(
    settings: ReaderSettings,
    currentLanguage: AppLanguage,
    onChange: (ReaderSettings) -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val base = LocalAppTextStyles.current.reading
    val capsule = RoundedCornerShape(percent = 50)
    val steps = ((MaxFontScale - MinFontScale) / FontScaleStep).toInt() + 1
    val step = ((settings.scale - MinFontScale) / FontScaleStep).let { kotlin.math.round(it).toInt() }.coerceIn(0, steps - 1)

    AppSheet(
        onDismiss = onDismiss,
        title = currentLanguage.pick("Texto", "Text", "Texto"),
        tag = "bible_reader_text_sheet"
    ) {
        SheetLabel(currentLanguage.pick("Tamaño", "Size", "Tamanho"))
        Row(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            val smaller = settings.scale > MinFontScale + 0.01f
            val larger = settings.scale < MaxFontScale - 0.01f
            Box(
                Modifier.weight(1f).height(52.dp)
                    .lumenGlass(capsule, lifted = false)
                    .lumenPress({ if (smaller) onChange(settings.copy(scale = (settings.scale - FontScaleStep).coerceAtLeast(MinFontScale))) }, 0.96f, enabled = smaller)
                    .semantics { contentDescription = currentLanguage.pick("Reducir el tamaño del texto", "Decrease text size", "Diminuir o tamanho do texto") }
                    .testTag("bible_reader_font_smaller"),
                contentAlignment = Alignment.Center
            ) { Text("A", style = styles.callout, color = if (smaller) p.textPrimary else p.inkQuiet) }
            Box(
                Modifier.weight(1f).height(52.dp)
                    .lumenGlass(capsule, lifted = false)
                    .lumenPress({ if (larger) onChange(settings.copy(scale = (settings.scale + FontScaleStep).coerceAtMost(MaxFontScale))) }, 0.96f, enabled = larger)
                    .semantics { contentDescription = currentLanguage.pick("Aumentar el tamaño del texto", "Increase text size", "Aumentar o tamanho do texto") }
                    .testTag("bible_reader_font_larger"),
                contentAlignment = Alignment.Center
            ) { Text("A", style = styles.title2, color = if (larger) p.textPrimary else p.inkQuiet) }
        }
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier.fillMaxWidth().semantics { contentDescription = "${(settings.scale * 100).toInt()} %" },
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            repeat(steps) { i ->
                Box(Modifier.size(6.dp).clip(CircleShape).background(if (i <= step) p.textPrimary else p.separator))
            }
        }

        Spacer(Modifier.height(22.dp))
        SheetLabel(currentLanguage.pick("Tipo de letra", "Typeface", "Tipo de letra"))
        Row(
            Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter).testTag("bible_reader_font_choice"),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ReaderFontChoice.entries.forEach { choice ->
                val selectedChoice = choice == settings.font
                val family = choice.family(base.fontFamily, design.language)
                val tile = RoundedCornerShape(20.dp)
                Column(
                    Modifier.weight(1f).height(88.dp)
                        .then(if (selectedChoice) Modifier.clip(tile).background(p.action) else Modifier.lumenGlass(tile, lifted = false))
                        .clip(tile)
                        .clickable(role = Role.RadioButton) { onChange(settings.copy(font = choice)) }
                        .semantics { selected = selectedChoice }
                        .testTag("bible_reader_font_${choice.name}"),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Aa", style = styles.title1.copy(fontFamily = family), color = if (selectedChoice) p.onAction else p.textPrimary)
                    Spacer(Modifier.height(2.dp))
                    Text(
                        choice.label(currentLanguage),
                        style = styles.caption,
                        color = if (selectedChoice) p.onAction else p.textSecondary,
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(Modifier.height(22.dp))
        SheetLabel(currentLanguage.pick("Interlineado", "Line spacing", "Entrelinha"))
        AppSegmentedTabs(
            options = listOf(
                currentLanguage.pick("Compacto", "Compact", "Compacto"),
                currentLanguage.pick("Normal", "Normal", "Normal"),
                currentLanguage.pick("Amplio", "Relaxed", "Amplo")
            ),
            selectedIndex = settings.spacing,
            onSelect = { onChange(settings.copy(spacing = it)) },
            modifier = Modifier.testTag("bible_reader_spacing")
        )
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun SheetLabel(text: String) {
    val design = LocalAppDesign.current
    Text(
        text,
        style = LocalAppTextStyles.current.footnote,
        color = design.palette.textSecondary,
        modifier = Modifier.padding(start = design.spacing.gutter + 18.dp, end = design.spacing.gutter, bottom = 8.dp)
    )
}

// --------------------------------------------------------------- daily missal

/** One lesson of the day's Mass, as the missal lays it out. */
private data class MassLesson(
    val label: String,
    val reference: String,
    val text: String
)

/** A short name for a lesson, for the jump chips; long Vatican headings stay in the body. */
private fun shortLessonLabel(heading: String, readingOrdinal: Int, language: AppLanguage): String {
    val h = heading.lowercase()
    return when {
        "evangel" in h || "gospel" in h -> language.pick("Evangelio", "Gospel", "Evangelho")
        "salmo" in h || "psalm" in h || "responsorial" in h -> language.pick("Salmo", "Psalm", "Salmo")
        "aleluya" in h || "alleluia" in h || "aleluia" in h || "aclamaci" in h || "acclamation" in h ->
            language.pick("Aleluya", "Alleluia", "Aleluia")
        "secuencia" in h || "sequence" in h || "sequência" in h -> language.pick("Secuencia", "Sequence", "Sequência")
        readingOrdinal == 1 -> language.pick("1.ª lectura", "1st reading", "1.ª leitura")
        readingOrdinal == 2 -> language.pick("2.ª lectura", "2nd reading", "2.ª leitura")
        else -> heading.split(' ').take(2).joinToString(" ")
    }
}

@Composable
private fun DailyMissalView(
    header: LazyListScope.() -> Unit,
    reading: DailyMassReading,
    currentLanguage: AppLanguage,
    speech: SpeechQueue,
    contentPadding: PaddingValues
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    // The day's readings come from the Vatican's own feed, read natively; the
    // bundled lectionary is the offline fallback.
    val isoToday = remember {
        val d = LiturgicalCalendar.today()
        "%04d-%02d-%02d".format(d.year, d.month, d.day)
    }
    val vaticanResult by produceState<ReadingsResult?>(null, currentLanguage, isoToday) {
        value = VaticanGospelFetcher().today(currentLanguage, isoToday)
    }
    val vatican = (vaticanResult as? ReadingsResult.Success)?.readings
    val loadingVatican = vaticanResult == null
    val offline = vaticanResult is ReadingsResult.Failure

    // On a ferial weekday the bundled lectionary legitimately has no entry;
    // such a lesson is dropped rather than drawn empty, and nothing is invented.
    val bundledLessons = listOf(
        MassLesson(currentLanguage.pick("Primera lectura", "First Reading", "Primeira Leitura"), reading.firstReadingRef, reading.firstReadingText.get(currentLanguage)),
        MassLesson(currentLanguage.pick("Salmo responsorial", "Responsorial Psalm", "Salmo Responsorial"), reading.responsorialPsalmRef, reading.responsorialPsalmText.get(currentLanguage)),
        MassLesson(currentLanguage.pick("Segunda lectura", "Second Reading", "Segunda Leitura"), reading.secondReadingRef.orEmpty(), reading.secondReadingText?.get(currentLanguage).orEmpty()),
        MassLesson(currentLanguage.pick("Santo Evangelio", "Holy Gospel", "Santo Evangelho"), reading.gospelRef, reading.gospelText.get(currentLanguage))
    ).filter { it.reference.isNotBlank() || it.text.isNotBlank() }
    val lessons = vatican?.sections?.map { MassLesson(it.heading, it.citation, it.text) } ?: bundledLessons
    val shortLabels = remember(lessons, currentLanguage) {
        var ordinal = 0
        lessons.map { l ->
            val h = l.label.lowercase()
            val isReading = listOf("evangel", "gospel", "salmo", "psalm", "responsorial", "aleluya", "alleluia", "aleluia", "secuencia", "sequence")
                .none { it in h }
            if (isReading) ordinal++
            shortLessonLabel(l.label, if (isReading) ordinal else 0, currentLanguage)
        }
    }

    val owner = "mass:$isoToday"
    val listening = speech.isPlaying(owner)
    val playingLesson = if (speech.owner == owner) speech.index else null
    val seasonColor = lumenLiturgicalColor(reading.liturgicalSeason.get(AppLanguage.EN), reading.liturgicalColorHex)
    val rubric = SpanStyle(color = seasonColor, fontWeight = FontWeight.SemiBold)

    // Item indices are fixed so the jump chips can scroll to a lesson.
    val lessonsStart = BibleHeaderItems + 2
    val currentLesson by remember(lessons) {
        derivedStateOf {
            val first = listState.firstVisibleItemIndex
            lessons.indices.lastOrNull { lessonsStart + it <= first + 1 } ?: 0
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize().testTag("daily_missal_screen"),
        contentPadding = contentPadding
    ) {
        header()
        item(key = "mass_card") {
            TodayMassCard(reading, currentLanguage, seasonColor)
            if (offline) {
                Text(
                    currentLanguage.pick(
                        "Sin conexión · lecturas del leccionario incluido",
                        "Offline · readings from the bundled lectionary",
                        "Sem conexão · leituras do lecionário incluído"
                    ),
                    style = styles.footnote, color = p.textSecondary,
                    modifier = Modifier.padding(horizontal = design.spacing.gutter + 4.dp).padding(top = 10.dp)
                )
            }
        }
        item(key = "mass_controls") {
            Column(Modifier.fillMaxWidth().padding(top = 18.dp, bottom = 8.dp)) {
                if (lessons.isNotEmpty() && !loadingVatican) {
                    if (speech.available) {
                        Box(Modifier.padding(horizontal = design.spacing.gutter)) {
                            AppSecondaryCapsule(
                                label = if (listening) currentLanguage.pick("Pausar", "Pause", "Pausar")
                                else currentLanguage.pick("Escuchar las lecturas", "Listen to the readings", "Ouvir as leituras"),
                                onClick = {
                                    if (listening) speech.pause()
                                    else speech.play(
                                        owner,
                                        lessons.map { l -> listOf(l.label, l.reference, l.text).filter { it.isNotBlank() }.joinToString(". ") },
                                        if (speech.owner == owner) speech.index ?: 0 else 0
                                    )
                                },
                                icon = if (listening) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                tag = "missal_listen_button"
                            )
                        }
                        Spacer(Modifier.height(14.dp))
                    }
                    if (lessons.size > 1) {
                        Row(
                            Modifier.fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                                .padding(horizontal = design.spacing.gutter),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            shortLabels.forEachIndexed { i, label ->
                                AppKitChip(
                                    label = label,
                                    selected = i == currentLesson,
                                    onClick = { scope.launch { listState.animateScrollToItem(lessonsStart + i) } },
                                    modifier = Modifier.testTag("missal_jump_$i")
                                )
                            }
                        }
                    }
                }
            }
        }

        if (loadingVatican) {
            item(key = "mass_loading") { MissalSkeleton(currentLanguage) }
        } else if (lessons.isEmpty()) {
            item(key = "mass_empty") {
                AppEmptyState(
                    title = currentLanguage.pick("Feria sin lecturas propias", "Ferial day without proper readings", "Féria sem leituras próprias"),
                    body = currentLanguage.pick(
                        "El leccionario incluido no trae las lecturas de esta feria. El día litúrgico es el de arriba; consulta el misal oficial para las lecturas de hoy.",
                        "The bundled lectionary carries no readings for this ferial day. The liturgical day stands above; see the official missal for today's readings.",
                        "O lecionário incluído não traz as leituras desta féria. O dia litúrgico é o de cima; consulte o missal oficial para as leituras de hoje."
                    ),
                    icon = Icons.AutoMirrored.Outlined.MenuBook
                )
            }
        } else {
            lessons.forEachIndexed { i, lesson ->
                item(key = "lesson_$i") {
                    MissalLesson(
                        shortLabel = shortLabels[i],
                        lesson = lesson,
                        rubric = rubric,
                        accent = seasonColor,
                        beingRead = playingLesson == i
                    )
                }
            }
        }

        if (vatican == null && !loadingVatican) item(key = "homily") {
            Column(Modifier.padding(top = 36.dp)) {
                Text(
                    currentLanguage.pick("Reflexión homilética", "Homiletic reflection", "Reflexão homilética"),
                    style = styles.eyebrow, color = p.textSecondary,
                    modifier = Modifier.padding(horizontal = design.spacing.gutter + 4.dp, vertical = 8.dp)
                )
                AppGlassSurface(
                    Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    Text(reading.homilyReflection.get(currentLanguage), style = styles.reading, color = p.textPrimary)
                }
            }
        }

        // Opens in the phone's own browser rather than an in-app WebView: the
        // site's cookie banner cannot be dismissed there.
        val pageUrl = vatican?.link ?: VaticanGospelFetcher.pageUrl(currentLanguage)
        item(key = "mass_source") {
            Column(
                Modifier.fillMaxWidth().padding(top = 40.dp, start = design.spacing.gutter, end = design.spacing.gutter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    currentLanguage.pick("Fuente: ", "Source: ", "Fonte: ") +
                        if (vatican != null) "Vatican News · ${vatican.title}" else reading.sourceName,
                    style = styles.footnote, color = p.textSecondary, textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))
                AppSecondaryCapsule(
                    label = currentLanguage.pick("Abrir en Vatican News", "Open on Vatican News", "Abrir no Vatican News"),
                    onClick = { runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))) } },
                    icon = Icons.AutoMirrored.Outlined.OpenInNew,
                    tag = "missal_open_vatican"
                )
            }
        }
    }
}

/**
 * Today's Mass at a glance: the Last Supper, then the day, its season as a
 * coloured dot, and the feast.
 */
@Composable
private fun TodayMassCard(reading: DailyMassReading, currentLanguage: AppLanguage, seasonColor: Color) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val art = rememberFirstAsset("heroes/lumen/mass.jpg", "heroes/cathedral_dome.jpg")
    val bitmap = rememberAssetBitmap(art, 1080)
    val meta = rememberHeroMeta(art)
    AppGlassSurface(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter).testTag("missal_today_card")) {
        Column(Modifier.fillMaxWidth()) {
            Box(Modifier.fillMaxWidth().height(156.dp).clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))) {
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = meta.alignment,
                        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.84f) }),
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Box(
                    Modifier.fillMaxSize().background(
                        Brush.verticalGradient(
                            0f to Color.Transparent,
                            0.5f to Color.Transparent,
                            1f to p.groupedSurface
                        )
                    )
                )
            }
            Column(Modifier.padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 20.dp)) {
                Text(reading.dateDisplay, style = styles.eyebrow, color = p.textSecondary)
                Spacer(Modifier.height(2.dp))
                Text(
                    currentLanguage.pick("Misa de hoy", "Today's Mass", "Missa de hoje"),
                    style = styles.title1, color = p.textPrimary,
                    modifier = Modifier.semantics { heading() }
                )
                Spacer(Modifier.height(12.dp))
                Row(
                    Modifier.lumenGlass(RoundedCornerShape(percent = 50), strong = true, lifted = false)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppSeasonDot(seasonColor, size = 9.dp)
                    Spacer(Modifier.width(8.dp))
                    Text(reading.liturgicalSeason.get(currentLanguage), style = styles.subhead.copy(fontWeight = FontWeight.Medium), color = p.textPrimary)
                }
                val feast = reading.feastTitle.get(currentLanguage)
                if (feast.isNotBlank()) {
                    Spacer(Modifier.height(10.dp))
                    Text(feast, style = styles.subhead, color = p.textSecondary)
                }
            }
        }
    }
}

/** One lesson: its rubric, its citation, then the text at reading measure. */
@Composable
private fun MissalLesson(
    shortLabel: String,
    lesson: MassLesson,
    rubric: SpanStyle,
    accent: Color,
    beingRead: Boolean
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Box(Modifier.fillMaxWidth().padding(top = 32.dp), contentAlignment = Alignment.TopCenter) {
        Column(
            Modifier.widthIn(max = design.spacing.readingMaxWidth).fillMaxWidth().padding(horizontal = ReadingInset)
        ) {
            Text(shortLabel, style = styles.eyebrow, color = p.textSecondary)
            Spacer(Modifier.height(2.dp))
            Text(
                lesson.reference.ifBlank { lesson.label },
                style = styles.title2, color = p.textPrimary,
                modifier = Modifier.semantics { heading() }
            )
            if (lesson.reference.isNotBlank() && !lesson.label.equals(shortLabel, ignoreCase = true)) {
                Spacer(Modifier.height(4.dp))
                Text(lesson.label, style = styles.footnote, color = p.textSecondary)
            }
            if (lesson.text.isNotBlank()) {
                Spacer(Modifier.height(14.dp))
                Row {
                    if (beingRead) {
                        Box(Modifier.padding(top = 4.dp).width(3.dp).height(48.dp).clip(RoundedCornerShape(percent = 50)).background(accent))
                        Spacer(Modifier.width(11.dp))
                    }
                    Text(
                        // The psalm's refrain is printed with ℟ in the season's ink.
                        text = withLiturgicalSigla(lesson.text, rubric),
                        style = styles.reading,
                        color = p.textPrimary
                    )
                }
            }
        }
    }
}

/** Calm placeholder lines while the Vatican feed answers. */
@Composable
private fun MissalSkeleton(currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val still = LocalInspectionMode.current
    val alpha = if (still) 0.65f else {
        val t = rememberInfiniteTransition(label = "skeleton")
        val a by t.animateFloat(0.45f, 0.85f, infiniteRepeatable(tween(1200), RepeatMode.Reverse), label = "skeletonAlpha")
        a
    }
    Column(
        Modifier.fillMaxWidth().padding(horizontal = ReadingInset).padding(top = 28.dp).testTag("daily_missal_loading")
    ) {
        repeat(2) { block ->
            Box(Modifier.fillMaxWidth(0.32f).height(12.dp).graphicsLayer { this.alpha = alpha }.clip(RoundedCornerShape(percent = 50)).background(p.separator))
            Spacer(Modifier.height(10.dp))
            Box(Modifier.fillMaxWidth(0.55f).height(20.dp).graphicsLayer { this.alpha = alpha }.clip(RoundedCornerShape(percent = 50)).background(p.separator))
            Spacer(Modifier.height(16.dp))
            listOf(1f, 0.94f, 0.97f, 0.6f).forEach { w ->
                Box(Modifier.fillMaxWidth(w).height(12.dp).graphicsLayer { this.alpha = alpha }.clip(RoundedCornerShape(percent = 50)).background(p.separator))
                Spacer(Modifier.height(12.dp))
            }
            if (block == 0) Spacer(Modifier.height(20.dp))
        }
        Text(
            currentLanguage.pick(
                "Cargando las lecturas de hoy desde Vatican News…",
                "Loading today's readings from Vatican News…",
                "A carregar as leituras de hoje do Vatican News…"
            ),
            style = styles.footnote, color = p.textSecondary
        )
    }
}

// ------------------------------------------------------------------- versions

@Composable
private fun VersionsView(
    versions: List<BibleVersionInfo>,
    downloadManager: BibleDownloadManager?,
    currentLanguage: AppLanguage,
    onBack: () -> Unit,
    activeEditionId: String = "bundled",
    onSelectEdition: (String) -> Unit = {}
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val insets = appScreenInsets()
    val title = currentLanguage.pick("Versiones y descargas", "Versions & downloads", "Versões e downloads")
    AppBackHandler(onBack = onBack)
    AppInlineTitle(title)

    LazyColumn(
        Modifier.fillMaxSize().testTag("bible_versions_screen"),
        contentPadding = PaddingValues(top = insets.calculateTopPadding(), bottom = insets.calculateBottomPadding() + 32.dp)
    ) {
        item(key = "versions_title") {
            Column(Modifier.padding(horizontal = design.spacing.gutter).padding(top = 8.dp, bottom = 24.dp)) {
                Text(title, style = styles.title1, color = p.textPrimary, modifier = Modifier.semantics { heading() })
                Spacer(Modifier.height(8.dp))
                Text(
                    currentLanguage.pick(
                        "El canon católico (73 libros) frente a las ediciones protestantes (66).",
                        "The Catholic canon (73 books) beside the Protestant editions (66).",
                        "O cânon católico (73 livros) diante das edições protestantes (66)."
                    ),
                    style = styles.callout, color = p.textSecondary
                )
            }
        }
        item(key = "select_included") {
            AppListGroup {
                AppListRow(currentLanguage.pick("Biblia católica incluida · 73 libros", "Included Catholic Bible · 73 books", "Bíblia católica incluída · 73 livros"), subtitle = when(currentLanguage) { AppLanguage.ES -> "Biblia Platense · Español"; AppLanguage.EN -> "Douay-Rheims-Challoner · English"; AppLanguage.PT -> "Matos Soares · Português" }, selected = activeEditionId == "bundled", onClick = { onSelectEdition("bundled") }, modifier = Modifier.testTag("bible_edition_bundled"))
            }
            Spacer(Modifier.height(16.dp))
        }
        item(key = "versions_label") {
            Text(
                currentLanguage.pick("Traducciones", "Translations", "Traduções"),
                style = styles.title3, color = p.textPrimary,
                modifier = Modifier.padding(horizontal = design.spacing.gutter).padding(bottom = 12.dp).semantics { heading() }
            )
        }
        versions.forEach { version ->
            item(key = "version_${version.id}") {
                VersionCard(version, currentLanguage)
                Spacer(Modifier.height(12.dp))
            }
        }
        if (downloadManager != null) {
            item(key = "downloads") {
                OfflineBibleDownloadsView(manager = downloadManager, currentLanguage = currentLanguage, activeEditionId = activeEditionId, onSelectEdition = onSelectEdition)
            }
        }
    }
}

@Composable
private fun VersionCard(version: BibleVersionInfo, currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    AppGlassSurface(
        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter).testTag("version_${version.id}"),
        contentPadding = PaddingValues(20.dp)
    ) {
        Column {
            Text(version.name, style = styles.headline, color = p.textPrimary)
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .then(
                            if (version.isCatholicCanon) Modifier.clip(RoundedCornerShape(percent = 50)).background(p.action)
                            else Modifier.lumenGlass(RoundedCornerShape(percent = 50), lifted = false)
                        )
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                ) {
                    Text(
                        if (version.isCatholicCanon) currentLanguage.pick("Canon católico · 73", "Catholic canon · 73", "Cânon católico · 73")
                        else currentLanguage.pick("No católica · 66", "Not Catholic · 66", "Não católica · 66"),
                        style = styles.caption,
                        color = if (version.isCatholicCanon) p.onAction else p.textSecondary
                    )
                }
                Spacer(Modifier.width(10.dp))
                Text("${version.abbreviation} · ${version.language.uppercase()}", style = styles.footnote, color = p.textSecondary)
            }
            Spacer(Modifier.height(12.dp))
            Text(version.description.get(currentLanguage), style = styles.callout, color = p.textPrimary)
            Spacer(Modifier.height(8.dp))
            Text(version.canonicalDifferenceNote.get(currentLanguage), style = styles.footnote, color = p.textSecondary)
        }
    }
}

/** Whole-Bible downloads for offline reading, one row each with its own control. */
@Composable
fun OfflineBibleDownloadsView(
    manager: BibleDownloadManager,
    currentLanguage: AppLanguage,
    activeEditionId: String = "bundled",
    onSelectEdition: (String) -> Unit = {}
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Text(
            currentLanguage.pick("Sin conexión", "Offline", "Offline"),
            style = styles.title3, color = p.textPrimary,
            modifier = Modifier.padding(horizontal = design.spacing.gutter).semantics { heading() }
        )
        Spacer(Modifier.height(4.dp))
        Text(
            currentLanguage.pick(
                "Guarda una versión completa en el dispositivo para leerla sin conexión.",
                "Keep a whole version on the device to read it without a connection.",
                "Guarde uma versão completa no dispositivo para ler sem conexão."
            ),
            style = styles.footnote, color = p.textSecondary,
            modifier = Modifier.padding(horizontal = design.spacing.gutter).padding(bottom = 12.dp)
        )
        AppListGroup {
            manager.specs.forEachIndexed { index, spec ->
                var downloading by remember(spec.id) { mutableStateOf(false) }
                var progress by remember(spec.id) { mutableFloatStateOf(0f) }
                var status by remember(spec.id) { mutableStateOf("") }
                var revision by remember(spec.id) { mutableIntStateOf(0) }
                var downloadJob by remember(spec.id) { mutableStateOf<kotlinx.coroutines.Job?>(null) }
                val downloadStatus = remember(spec.id, downloading, revision) { manager.status(spec.id) }
                val downloaded = downloadStatus.complete
                Column {
                    Row(
                        Modifier.fillMaxWidth().heightIn(min = 64.dp).padding(start = 18.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(spec.name, style = styles.body, color = p.textPrimary)
                            Spacer(Modifier.height(2.dp))
                            Text(
                                if (downloaded) currentLanguage.pick("Descargado · disponible sin conexión", "Downloaded · available offline", "Baixado · disponível offline")
                                else status.ifBlank { if (downloadStatus.partial) currentLanguage.pick("Parcial: ${downloadStatus.completedBooks}/${downloadStatus.totalBooks} libros", "Partial: ${downloadStatus.completedBooks}/${downloadStatus.totalBooks} books", "Parcial: ${downloadStatus.completedBooks}/${downloadStatus.totalBooks} livros") else currentLanguage.pick("No descargado", "Not downloaded", "Não baixado") },
                                style = styles.footnote, color = p.textSecondary
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        when {
                            downloaded -> Icon(
                                Icons.Outlined.CheckCircle,
                                contentDescription = currentLanguage.pick("Descargado", "Downloaded", "Baixado"),
                                tint = p.success,
                                modifier = Modifier.padding(10.dp).size(24.dp)
                            )
                            downloading -> Box(Modifier.size(44.dp), contentAlignment = Alignment.Center) {
                                AppProgressRing(progress = progress, size = 28.dp, stroke = 3.dp)
                            }
                            else -> AppGlassIconButton(
                                icon = Icons.Outlined.Download,
                                contentDescription = currentLanguage.pick("Descargar", "Download", "Baixar") + " " + spec.name,
                                onClick = {
                                    if (!downloading) {
                                        downloading = true
                                        downloadJob = scope.launch {
                                            try {
                                            val ok = manager.download(spec.id) { fraction, message ->
                                                progress = fraction
                                                status = message
                                            }
                                            status = if (ok) currentLanguage.pick("Completado", "Completed", "Concluído") else status
                                            } finally { downloading = false; revision++ }
                                        }
                                    }
                                },
                                tag = "bible_download_${spec.id}"
                            )
                        }
                    }
                    if (downloaded) AppListRow(currentLanguage.pick("Leer esta edición", "Read this edition", "Ler esta edição"), selected = activeEditionId == spec.id, onClick = { onSelectEdition(spec.id) }, modifier = Modifier.testTag("bible_edition_${spec.id}"))
                    Row(Modifier.padding(horizontal = 18.dp)) {
                        if (downloading) androidx.compose.material3.TextButton(onClick = { downloadJob?.cancel() }, modifier = Modifier.heightIn(min = 48.dp).testTag("bible_cancel_${spec.id}")) { Text(currentLanguage.pick("Cancelar", "Cancel", "Cancelar")) }
                        if (!downloading && downloadStatus.completedBooks > 0) androidx.compose.material3.TextButton(onClick = {
                            scope.launch { if (activeEditionId == spec.id) onSelectEdition("bundled"); manager.delete(spec.id); revision++; status = "" }
                        }, modifier = Modifier.heightIn(min = 48.dp).testTag("bible_delete_${spec.id}")) { Text(currentLanguage.pick("Eliminar descarga", "Delete download", "Excluir download")) }
                    }
                    if (index < manager.specs.lastIndex) {
                        Box(Modifier.fillMaxWidth().padding(start = 18.dp).height(0.5.dp).background(p.separator))
                    }
                }
            }
        }
    }
}
