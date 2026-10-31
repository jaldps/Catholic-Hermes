package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.models.BibleBook
import com.example.data.models.BibleVerse
import com.example.data.models.BibleVersionInfo
import com.example.data.models.DailyMassReading
import com.example.data.models.Testament
import com.example.data.repository.BibleDownloadManager
import com.example.data.news.ReadingsResult
import com.example.data.news.VaticanGospelFetcher
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.InAppBrowserDialog
import com.example.ui.design.AppBanner
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.design.AppVerseRow
import com.example.ui.theme.AppFonts
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** Reader type size: the bounds and step the A-/A+ pair moves between. */
private const val MinFontScale = 0.85f
private const val MaxFontScale = 1.45f
private const val FontScaleStep = 0.15f

/** Verse search only runs once the query is long enough to mean something. */
private const val MinSearchLength = 3

/**
 * The caller insets the segmented control by the gutter. Vatican is the
 * exception: its strip is a ruled band whose hairline runs the full width of
 * the leaf, and inset it stops meeting the edges.
 */
@Composable
private fun segmentedTabsInset(): androidx.compose.ui.unit.Dp {
    val design = LocalAppDesign.current
    return if (design.language == DesignLanguage.VATICAN) 0.dp else design.spacing.gutter
}

private fun TextUnit.scaledBy(factor: Float): TextUnit =
    if (isSpecified) this * factor else this

/** A reading style re-cut for the reader's own size and face preference. */
private fun TextStyle.asReading(family: FontFamily?, factor: Float): TextStyle = copy(
    fontFamily = family ?: fontFamily,
    fontSize = fontSize.scaledBy(factor),
    lineHeight = lineHeight.scaledBy(factor)
)

/** One lesson of the day's Mass, as the missal lays it out. */
private data class MassLesson(
    val label: String,
    val reference: String,
    val text: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    dao: SanctaFidesDao? = null,
    ttsManager: LiturgicalTtsManager? = null,
    /** 0 reader · 1 missal · 2 versions · 3 study. */
    initialMainTab: Int = 0,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current

    var selectedMainTab by remember(initialMainTab) { mutableIntStateOf(initialMainTab) }
    var selectedTestamentTab by remember { mutableIntStateOf(0) }
    var selectedBook by remember { mutableStateOf<BibleBook?>(null) }
    var selectedChapterNumber by remember { mutableIntStateOf(1) }
    var showChapterSelectorDialog by remember { mutableStateOf(false) }
    var selectedVerseForCommentary by remember { mutableStateOf<BibleVerse?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var fontSizeMultiplier by remember { mutableFloatStateOf(1f) }
    var useSerifFont by remember { mutableStateOf(true) }

    val bottomSheetState = rememberModalBottomSheetState()

    // One title for the whole screen, then the control that chooses the view:
    // title → segmented control, never the other way round. It is the first
    // rows of whichever list is showing, so it scrolls away with the content.
    val tabsInset = segmentedTabsInset()
    val header: LazyListScope.() -> Unit = {
        item(key = "bible_screen_title") {
            AppScreenTitle(
                title = currentLanguage.pick("Sagrada Biblia", "Holy Bible", "Bíblia Sagrada"),
                subtitle = currentLanguage.pick(
                    "Los 73 libros del canon católico",
                    "The 73 books of the Catholic canon",
                    "Os 73 livros do cânon católico"
                )
            )
        }
        item(key = "bible_screen_tabs") {
            AppSegmentedTabs(
                options = listOf(
                    currentLanguage.pick("Biblia", "Bible", "Bíblia"),
                    currentLanguage.pick("Misal", "Missal", "Missal"),
                    currentLanguage.pick("Versiones", "Versions", "Versões"),
                    currentLanguage.pick("Estudio", "Study", "Estudo")
                ),
                selectedIndex = selectedMainTab,
                onSelect = { selectedMainTab = it },
                modifier = Modifier
                    .padding(horizontal = tabsInset)
                    .padding(bottom = design.spacing.cardGap / 2)
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("bible_screen")
    ) {
        when (selectedMainTab) {
            0 -> {
                val book = selectedBook
                if (book == null) {
                    BookDirectory(
                        header = header,
                        repository = repository,
                        currentLanguage = currentLanguage,
                        searchQuery = searchQuery,
                        onSearchQueryChange = { searchQuery = it },
                        selectedFilter = selectedTestamentTab,
                        onFilterChange = { selectedTestamentTab = it },
                        onOpenBook = { opened, chapter ->
                            selectedBook = opened
                            selectedChapterNumber = chapter
                        }
                    )
                } else {
                    val chapterCount = remember(book.id, currentLanguage) {
                        repository.getChapterCount(book.id, currentLanguage).coerceAtLeast(1)
                    }
                    val chapterNumber = selectedChapterNumber.coerceIn(1, chapterCount)
                    val verses = remember(book.id, chapterNumber, currentLanguage) {
                        repository.getChapter(book.id, chapterNumber, currentLanguage).verses
                    }

                    BackHandler { selectedBook = null }

                    ChapterReader(
                        book = book,
                        chapterNumber = chapterNumber,
                        chapterCount = chapterCount,
                        verses = verses,
                        currentLanguage = currentLanguage,
                        fontScale = fontSizeMultiplier,
                        serif = useSerifFont,
                        onBack = { selectedBook = null },
                        onChooseChapter = { showChapterSelectorDialog = true },
                        onFontScaleChange = { fontSizeMultiplier = it },
                        onSerifToggle = { useSerifFont = !useSerifFont },
                        onChapterChange = { selectedChapterNumber = it },
                        onVerseClick = { selectedVerseForCommentary = it }
                    )

                    if (showChapterSelectorDialog) {
                        ChapterSelectorDialog(
                            book = book,
                            chapterNumber = chapterNumber,
                            chapterCount = chapterCount,
                            currentLanguage = currentLanguage,
                            onSelect = {
                                selectedChapterNumber = it
                                showChapterSelectorDialog = false
                            },
                            onDismiss = { showChapterSelectorDialog = false }
                        )
                    }
                }
            }

            1 -> DailyMissalView(
                header = header,
                reading = repository.dailyMassReading,
                currentLanguage = currentLanguage
            )

            2 -> VersionsView(
                header = header,
                versions = repository.bibleVersions,
                downloadManager = repository.bibleDownloadManager,
                currentLanguage = currentLanguage
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
                LazyColumn(Modifier.fillMaxSize()) {
                    header()
                    item {
                        AppEmptyState(
                            title = currentLanguage.pick(
                                "Estudio Bíblico",
                                "Scripture Study",
                                "Estudo Bíblico"
                            ),
                            body = currentLanguage.pick(
                                "Los itinerarios de estudio necesitan el almacenamiento local del dispositivo.",
                                "The study tracks need the device's local storage.",
                                "Os itinerários de estudo precisam do armazenamento local do dispositivo."
                            ),
                            icon = Icons.AutoMirrored.Filled.MenuBook
                        )
                    }
                }
            }
        }
    }

    selectedVerseForCommentary?.let { verse ->
        ModalBottomSheet(
            onDismissRequest = { selectedVerseForCommentary = null },
            sheetState = bottomSheetState,
            shape = design.shapes.sheet,
            containerColor = design.palette.groupedSurface,
            scrimColor = design.palette.scrim
        ) {
            CommentarySheet(verse = verse, currentLanguage = currentLanguage)
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
    onOpenBook: (BibleBook, Int) -> Unit
) {
    val design = LocalAppDesign.current

    // The declared chapter count and the one actually present in the assets can
    // differ, so the directory reports what the repaired data really holds —
    // once per language rather than once per row.
    val chapterCounts = remember(currentLanguage) {
        repository.catholicBooks.associate { it.id to repository.getChapterCount(it.id, currentLanguage) }
    }
    val booksById = remember { repository.catholicBooks.associateBy { it.id } }

    // One filter layer only: the view selector above. The testaments and the
    // deuterocanon are reached by search ("deutero", "Gospels", "New
    // Testament") and the sections themselves, not a second row of chips.
    val filteredBooks = remember(searchQuery, selectedFilter, currentLanguage) {
        val query = searchQuery.trim()
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
            matchesSearch && matchesTab
        }
    }
    val grouped = remember(filteredBooks) { filteredBooks.groupBy { it.category } }

    // Full-text search runs off the composition thread: it walks the whole
    // bundled bible and would otherwise stall every keystroke.
    val verseMatches by produceState(
        emptyList<Triple<String, Int, BibleVerse>>(),
        searchQuery,
        currentLanguage
    ) {
        val query = searchQuery.trim()
        value = if (query.length < MinSearchLength) {
            emptyList()
        } else {
            withContext(Dispatchers.Default) { repository.searchBible(query, 20, currentLanguage) }
        }
    }

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            AppSearchField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = currentLanguage.pick(
                    "Buscar libros y versículos",
                    "Search books and verses",
                    "Buscar livros e versículos"
                ),
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("bible_search_input")
            )
        }

        if (filteredBooks.isEmpty() && verseMatches.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick(
                        "Sin resultados",
                        "Nothing found",
                        "Sem resultados"
                    ),
                    body = currentLanguage.pick(
                        "Ningún libro ni versículo coincide con «$searchQuery».",
                        "No book or verse matches “$searchQuery”.",
                        "Nenhum livro ou versículo corresponde a “$searchQuery”."
                    ),
                    icon = Icons.Default.SearchOff
                )
                AppSecondaryButton(
                    label = currentLanguage.pick("Borrar la búsqueda", "Clear search", "Limpar a busca"),
                    onClick = { onSearchQueryChange("") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                )
            }
        }

        grouped.forEach { (category, books) ->
            item(key = "header_${category.name}") {
                AppSectionHeader(title = category.displayName.get(currentLanguage))
            }
            item(key = "group_${category.name}") {
                AppListGroup {
                    books.forEach { book ->
                        val chapters = chapterCounts[book.id] ?: book.chaptersCount
                        AppListRow(
                            title = book.name.get(currentLanguage),
                            subtitle = buildString {
                                append(
                                    currentLanguage.pick(
                                        "$chapters capítulos",
                                        "$chapters chapters",
                                        "$chapters capítulos"
                                    )
                                )
                                if (book.isDeuterocanonical) {
                                    append(" · ")
                                    append(
                                        currentLanguage.pick(
                                            "Deuterocanónico",
                                            "Deuterocanonical",
                                            "Deuterocanônico"
                                        )
                                    )
                                }
                            },
                            onClick = { onOpenBook(book, 1) },
                            modifier = Modifier.testTag("book_item_${book.id}")
                        )
                    }
                }
            }
        }

        if (verseMatches.isNotEmpty()) {
            item { AppOrnament() }
            item {
                AppSectionHeader(title = currentLanguage.pick("Versículos", "Verses", "Versículos"))
            }
            item {
                AppListGroup {
                    verseMatches.forEach { (bookId, chapter, verse) ->
                        val book = booksById[bookId] ?: return@forEach
                        val snippet = verse.text.get(currentLanguage).let {
                            if (it.length > 140) it.take(140).trimEnd() + "…" else it
                        }
                        AppListRow(
                            // Spanish and Portuguese cite 3,16; English 3:16.
                            title = book.name.get(currentLanguage) + " " + chapter +
                                (if (currentLanguage == AppLanguage.EN) ":" else ",") + verse.number,
                            subtitle = snippet,
                            onClick = { onOpenBook(book, chapter) }
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// --------------------------------------------------------------------- reader

@Composable
private fun ChapterReader(
    book: BibleBook,
    chapterNumber: Int,
    chapterCount: Int,
    verses: List<BibleVerse>,
    currentLanguage: AppLanguage,
    fontScale: Float,
    serif: Boolean,
    onBack: () -> Unit,
    onChooseChapter: () -> Unit,
    onFontScaleChange: (Float) -> Unit,
    onSerifToggle: () -> Unit,
    onChapterChange: (Int) -> Unit,
    onVerseClick: (BibleVerse) -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val centred = design.decor.titleAlignment == Alignment.CenterHorizontally
    val reading = Modifier.widthIn(max = design.spacing.readingMaxWidth)

    val chapterLabel = currentLanguage.pick(
        "Cap. $chapterNumber / $chapterCount",
        "Ch. $chapterNumber / $chapterCount",
        "Cap. $chapterNumber / $chapterCount"
    )

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = design.spacing.rowMinHeight)
                .padding(start = 4.dp, end = design.spacing.gutter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.testTag("back_to_books_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = currentLanguage.pick(
                        "Volver a los libros",
                        "Back to the books",
                        "Voltar aos livros"
                    ),
                    tint = palette.textSecondary
                )
            }
            Text(
                text = book.name.get(currentLanguage),
                style = styles.rowTitle,
                color = palette.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(10.dp))
            AppChip(
                label = chapterLabel,
                selected = false,
                onClick = onChooseChapter,
                modifier = Modifier.testTag("choose_chapter_header_button")
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppChip(
                label = "A-",
                selected = false,
                onClick = {
                    if (fontScale > MinFontScale) onFontScaleChange(fontScale - FontScaleStep)
                }
            )
            AppChip(
                label = "A+",
                selected = false,
                onClick = {
                    if (fontScale < MaxFontScale) onFontScaleChange(fontScale + FontScaleStep)
                }
            )
            AppChip(
                label = if (serif) "Serif" else "Sans",
                selected = serif,
                onClick = onSerifToggle
            )
        }

        AppDivider()

        ReadingTypography(scale = fontScale, serif = serif) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    AppScreenTitle(
                        title = book.name.get(currentLanguage),
                        subtitle = currentLanguage.pick(
                            "Capítulo $chapterNumber",
                            "Chapter $chapterNumber",
                            "Capítulo $chapterNumber"
                        ),
                        modifier = reading
                    )
                }

                item {
                    Text(
                        text = book.summary.get(currentLanguage),
                        style = styles.rowSubtitle,
                        color = palette.textSecondary,
                        textAlign = if (centred) TextAlign.Center else TextAlign.Start,
                        modifier = reading
                            .fillMaxWidth()
                            .padding(horizontal = design.spacing.gutter)
                    )
                }

                if (book.isDeuterocanonical) {
                    item {
                        AppBanner(
                            title = currentLanguage.pick(
                                "Libro Deuterocanónico",
                                "Deuterocanonical Book",
                                "Livro Deuterocanônico"
                            ),
                            body = currentLanguage.pick(
                                "Conservado en el canon católico desde los Concilios de Roma (382) e Hipona (393).",
                                "Preserved in the Catholic Canon since the Councils of Rome (382) & Hippo (393).",
                                "Conservado no cânon católico desde os Concílios de Roma (382) e Hipona (393)."
                            ),
                            modifier = reading.padding(top = design.spacing.sectionGap / 2)
                        )
                    }
                }

                item { AppOrnament(reading) }

                if (verses.isEmpty()) {
                    item {
                        AppEmptyState(
                            title = currentLanguage.pick(
                                "Capítulo no disponible",
                                "Chapter unavailable",
                                "Capítulo não disponível"
                            ),
                            body = currentLanguage.pick(
                                "Este capítulo no está en la edición incluida en este idioma.",
                                "This chapter is not in the bundled edition for this language.",
                                "Este capítulo não está na edição incluída neste idioma."
                            ),
                            icon = Icons.Default.AutoStories,
                            modifier = reading
                        )
                    }
                } else {
                    itemsIndexed(verses, key = { _, verse -> verse.number }) { index, verse ->
                        AppVerseRow(
                            number = verse.number,
                            text = verse.text.get(currentLanguage),
                            isFirstOfChapter = index == 0,
                            hasCommentary = verse.commentary != null,
                            onClick = { onVerseClick(verse) },
                            modifier = reading
                        )
                    }
                }

                item { Spacer(Modifier.height(design.spacing.sectionGap)) }
            }
        }

        AppDivider()

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppSecondaryButton(
                label = currentLanguage.pick(
                    "← Cap. ${chapterNumber - 1}",
                    "← Ch. ${chapterNumber - 1}",
                    "← Cap. ${chapterNumber - 1}"
                ),
                onClick = { onChapterChange(chapterNumber - 1) },
                enabled = chapterNumber > 1,
                modifier = Modifier
                    .weight(1f)
                    .testTag("prev_chapter_button")
            )
            AppSecondaryButton(
                label = currentLanguage.pick(
                    "Cap. ${chapterNumber + 1} →",
                    "Ch. ${chapterNumber + 1} →",
                    "Cap. ${chapterNumber + 1} →"
                ),
                onClick = { onChapterChange(chapterNumber + 1) },
                enabled = chapterNumber < chapterCount,
                modifier = Modifier
                    .weight(1f)
                    .testTag("next_chapter_button")
            )
        }
    }
}

/**
 * Applies the reader's own type size and serif preference to the scripture
 * styles, so the kit's verse rows — drop cap, superscript figure, tonal badge —
 * all follow the A-/A+ pair instead of each identity being re-implemented here.
 * Only the reading faces move; the chrome keeps its identity's voice.
 */
@Composable
private fun ReadingTypography(
    scale: Float,
    serif: Boolean,
    content: @Composable () -> Unit
) {
    val language = LocalAppDesign.current.language
    val base = LocalAppTextStyles.current
    val styles = remember(base, scale, serif, language) {
        val readingFamily = when {
            serif -> AppFonts.EbGaramond
            language == DesignLanguage.MATERIAL -> AppFonts.Roboto
            else -> AppFonts.Inter
        }
        base.copy(
            scripture = base.scripture.asReading(readingFamily, scale),
            quote = base.quote.asReading(readingFamily, scale),
            verseNumber = base.verseNumber.asReading(null, scale)
        )
    }
    CompositionLocalProvider(LocalAppTextStyles provides styles, content = content)
}

@Composable
private fun ChapterSelectorDialog(
    book: BibleBook,
    chapterNumber: Int,
    chapterCount: Int,
    currentLanguage: AppLanguage,
    onSelect: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val scheme = MaterialTheme.colorScheme

    AlertDialog(
        onDismissRequest = onDismiss,
        shape = design.shapes.dialog,
        containerColor = palette.groupedSurface,
        title = {
            Text(
                text = book.name.get(currentLanguage) + " — " + currentLanguage.pick(
                    "Seleccionar Capítulo",
                    "Select Chapter",
                    "Selecionar Capítulo"
                ),
                style = styles.sectionTitle,
                color = palette.textPrimary
            )
        },
        text = {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 52.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 320.dp)
            ) {
                items(chapterCount) { index ->
                    val number = index + 1
                    val isCurrent = number == chapterNumber
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .clip(design.shapes.buttonSmall)
                            .background(
                                if (isCurrent) scheme.primary
                                else palette.selectionTint.copy(alpha = if (design.isDark) 1f else 0.55f)
                            )
                            .clickable { onSelect(number) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = number.toString(),
                            style = styles.rowSubtitle.copy(
                                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium
                            ),
                            color = if (isCurrent) scheme.onPrimary else palette.textPrimary
                        )
                    }
                }
            }
        },
        confirmButton = {
            AppSecondaryButton(
                label = currentLanguage.pick("Cerrar", "Close", "Fechar"),
                onClick = onDismiss
            )
        }
    )
}

@Composable
private fun CommentarySheet(
    verse: BibleVerse,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val reading = Modifier.widthIn(max = design.spacing.readingMaxWidth)

    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = design.spacing.contentBottomGap)
    ) {
        AppSectionHeader(
            title = currentLanguage.pick(
                "Comentario Patrístico y Litúrgico",
                "Patristic & Liturgical Commentary",
                "Comentário Patrístico e Litúrgico"
            ),
            subtitle = currentLanguage.pick(
                "v. ${verse.number}",
                "v. ${verse.number}",
                "v. ${verse.number}"
            )
        )

        Text(
            text = "«${verse.text.get(currentLanguage)}»",
            style = styles.quote,
            color = palette.textPrimary,
            modifier = reading
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
        )

        Spacer(Modifier.height(design.spacing.sectionGap / 2))

        val commentary = verse.commentary
        if (commentary == null) {
            AppEmptyState(
                title = currentLanguage.pick(
                    "Sin comentario",
                    "No commentary",
                    "Sem comentário"
                ),
                body = currentLanguage.pick(
                    "Este versículo aún no tiene glosa patrística en la edición incluida.",
                    "This verse has no patristic gloss in the bundled edition yet.",
                    "Este versículo ainda não tem glosa patrística na edição incluída."
                ),
                icon = Icons.AutoMirrored.Filled.Comment
            )
        } else {
            AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(
                    text = verse.commentaryAuthor ?: currentLanguage.pick(
                        "Padres de la Iglesia",
                        "Church Fathers",
                        "Padres da Igreja"
                    ),
                    style = styles.sectionEyebrow,
                    color = palette.gold
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                Text(
                    text = commentary.get(currentLanguage),
                    style = MaterialTheme.typography.bodyMedium,
                    color = palette.textPrimary
                )
            }
        }
    }
}

// --------------------------------------------------------------- daily missal

@Composable
private fun DailyMissalView(
    header: LazyListScope.() -> Unit,
    reading: DailyMassReading,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val readingClamp = Modifier.widthIn(max = design.spacing.readingMaxWidth)
    val context = LocalContext.current

    // The day's readings come from the Vatican's own feed, read natively — the
    // web page is driven by script and covered by a cookie banner. The bundled
    // lectionary is the offline fallback.
    val isoToday = remember {
        val d = LiturgicalCalendar.today()
        "%04d-%02d-%02d".format(d.year, d.month, d.day)
    }
    val vaticanResult by produceState<ReadingsResult?>(null, currentLanguage, isoToday) {
        value = VaticanGospelFetcher().today(currentLanguage, isoToday)
    }
    val vatican = (vaticanResult as? ReadingsResult.Success)?.readings
    val loadingVatican = vaticanResult == null

    // On a ferial weekday the bundled lectionary legitimately has no entry: the
    // citation and the passage both come back empty. Such a lesson is dropped
    // rather than drawn as an empty row, and nothing is invented in its place.
    val bundledLessons = listOf(
        MassLesson(
            currentLanguage.pick("Primera Lectura", "First Reading", "Primeira Leitura"),
            reading.firstReadingRef,
            reading.firstReadingText.get(currentLanguage)
        ),
        MassLesson(
            currentLanguage.pick("Salmo Responsorial", "Responsorial Psalm", "Salmo Responsorial"),
            reading.responsorialPsalmRef,
            reading.responsorialPsalmText.get(currentLanguage)
        ),
        MassLesson(
            currentLanguage.pick("Segunda Lectura", "Second Reading", "Segunda Leitura"),
            reading.secondReadingRef.orEmpty(),
            reading.secondReadingText?.get(currentLanguage).orEmpty()
        ),
        MassLesson(
            currentLanguage.pick("Santo Evangelio", "Holy Gospel", "Santo Evangelho"),
            reading.gospelRef,
            reading.gospelText.get(currentLanguage)
        )
    ).filter { it.reference.isNotBlank() || it.text.isNotBlank() }

    val lessons = vatican?.sections?.map { MassLesson(it.heading, it.citation, it.text) }
        ?: bundledLessons

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        header()
        item {
            AppSectionHeader(
                title = currentLanguage.pick("Misa del día", "Today's Mass", "Missa do dia"),
                subtitle = reading.dateDisplay
            )
        }

        item {
            AppBanner(
                title = reading.liturgicalSeason.get(currentLanguage),
                body = reading.feastTitle.get(currentLanguage),
                accentColorHex = reading.liturgicalColorHex
            )
        }

        if (loadingVatican) {
            item {
                Text(
                    text = currentLanguage.pick(
                        "Cargando las lecturas de hoy desde Vatican News…",
                        "Loading today's readings from Vatican News…",
                        "A carregar as leituras de hoje do Vatican News…"
                    ),
                    style = styles.rowSubtitle,
                    color = palette.textSecondary,
                    modifier = Modifier.padding(design.spacing.gutter)
                )
            }
        }

        if (lessons.isEmpty() && !loadingVatican) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick(
                        "Feria sin lecturas propias",
                        "Ferial day without proper readings",
                        "Féria sem leituras próprias"
                    ),
                    body = currentLanguage.pick(
                        "El leccionario incluido no trae las lecturas de esta feria. El día litúrgico es el de arriba; consulta el misal oficial para las lecturas de hoy.",
                        "The bundled lectionary carries no readings for this ferial day. The liturgical day stands above; see the official missal for today's readings.",
                        "O lecionário incluído não traz as leituras desta féria. O dia litúrgico é o de cima; consulte o missal oficial para as leituras de hoje."
                    ),
                    icon = Icons.AutoMirrored.Filled.MenuBook
                )
            }
        } else {
            // A missal does not box its lessons: the label is the rubric, the
            // citation sits under it, and the passage runs at reading measure.
            items(lessons, key = { "${it.label}|${it.reference}" }) { lesson ->
                AppSectionHeader(
                    title = lesson.label,
                    subtitle = lesson.reference.ifBlank { null }
                )
                if (lesson.text.isNotBlank()) {
                    Text(
                        // The psalm's refrain is printed with ℟ in rubric.
                        text = withLiturgicalSigla(lesson.text, rubricSigil()),
                        style = styles.scripture,
                        color = palette.textPrimary,
                        modifier = readingClamp
                            .fillMaxWidth()
                            .padding(horizontal = design.spacing.gutter)
                    )
                }
            }
        }

        item { AppOrnament() }

        if (vatican == null && !loadingVatican) item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Reflexión Homilética",
                    "Homiletic Reflection",
                    "Reflexão Homilética"
                )
            )
        }

        if (vatican == null && !loadingVatican) item {
            AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(
                    text = reading.homilyReflection.get(currentLanguage),
                    style = styles.quote,
                    color = palette.textPrimary,
                    modifier = readingClamp
                )
            }
        }

        item {
            AppSectionHeader(
                title = currentLanguage.pick("Fuente", "Source", "Fonte"),
                subtitle = if (vatican != null) {
                    "Vatican News · ${vatican.title}"
                } else {
                    reading.sourceName
                }
            )
        }

        // Opens in the phone's own browser rather than an in-app WebView: the
        // site's cookie banner covers half the screen there and cannot be
        // dismissed, while the system browser handles consent and remembers it.
        val pageUrl = vatican?.link ?: VaticanGospelFetcher.pageUrl(currentLanguage)
        item {
            AppSecondaryButton(
                label = currentLanguage.pick(
                    "Abrir en Vatican News",
                    "Open on Vatican News",
                    "Abrir no Vatican News"
                ),
                onClick = {
                    runCatching {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl)))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
            )
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }

}

// ------------------------------------------------------------------- versions

@Composable
private fun VersionsView(
    header: LazyListScope.() -> Unit,
    versions: List<BibleVersionInfo>,
    downloadManager: BibleDownloadManager?,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val scheme = MaterialTheme.colorScheme

    LazyColumn(Modifier.fillMaxSize()) {
        header()
        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Versiones y cánones",
                    "Versions & canons",
                    "Versões e cânones"
                ),
                subtitle = currentLanguage.pick(
                    "El canon católico (73 libros) frente a las ediciones protestantes (66).",
                    "The Catholic canon (73 books) beside the Protestant editions (66).",
                    "O cânon católico (73 livros) diante das edições protestantes (66)."
                )
            )
        }

        items(versions, key = { it.id }) { version ->
            val canonColor = if (version.isCatholicCanon) scheme.primary else scheme.error
            val canonLabel = if (version.isCatholicCanon) {
                currentLanguage.pick(
                    "Canon católico (73)",
                    "Catholic canon (73)",
                    "Cânon católico (73)"
                )
            } else {
                currentLanguage.pick(
                    "No católica (66)",
                    "Not Catholic (66)",
                    "Não católica (66)"
                )
            }

            AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(
                    text = version.name,
                    style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                    color = palette.textPrimary
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        Modifier
                            .background(canonColor.copy(alpha = 0.14f), design.shapes.badge)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = if (design.decor.usesAllCapsLabels) canonLabel.uppercase() else canonLabel,
                            style = styles.sectionEyebrow,
                            color = canonColor
                        )
                    }
                    Text(
                        text = "${version.abbreviation} · ${version.language.uppercase()}",
                        style = styles.rowSubtitle,
                        color = palette.textTertiary
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = version.description.get(currentLanguage),
                    style = MaterialTheme.typography.bodyMedium,
                    color = palette.textSecondary
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                // AppDivider is inset to the gutter in Cupertino, which reads
                // as a mistake inside a card's own padding — so the rule that
                // closes a card is drawn to the card's measure.
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(palette.hairline)
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = version.canonicalDifferenceNote.get(currentLanguage),
                    style = styles.rowSubtitle,
                    color = if (version.isCatholicCanon) palette.textSecondary else scheme.error
                )
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        if (downloadManager != null) {
            item { AppOrnament() }
            item {
                OfflineBibleDownloadsView(
                    manager = downloadManager,
                    currentLanguage = currentLanguage
                )
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/**
 * Whole-bible downloads for offline reading. The progress bar has no kit
 * equivalent, so it is built from tokens around the Material indicator.
 */
@Composable
fun OfflineBibleDownloadsView(
    manager: BibleDownloadManager,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val scheme = MaterialTheme.colorScheme
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxWidth()) {
        AppSectionHeader(
            title = currentLanguage.pick(
                "Descargas offline de Biblias",
                "Offline Bible downloads",
                "Downloads offline da Bíblia"
            ),
            subtitle = currentLanguage.pick(
                "Guarda una versión completa en el dispositivo para leerla sin conexión.",
                "Keep a whole version on the device to read it without a connection.",
                "Guarde uma versão completa no dispositivo para ler sem conexão."
            )
        )

        manager.specs.forEach { spec ->
            var downloading by remember(spec.id) { mutableStateOf(false) }
            var progress by remember(spec.id) { mutableFloatStateOf(0f) }
            var status by remember(spec.id) { mutableStateOf("") }
            val downloaded = remember(spec.id, downloading) { manager.isDownloaded(spec.id) }

            AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(
                    text = spec.name,
                    style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                    color = palette.textPrimary
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                Text(
                    text = if (downloaded) {
                        currentLanguage.pick(
                            "Descargado (offline disponible)",
                            "Downloaded (available offline)",
                            "Baixado (disponível offline)"
                        )
                    } else {
                        status.ifBlank {
                            currentLanguage.pick(
                                "No descargado",
                                "Not downloaded",
                                "Não baixado"
                            )
                        }
                    },
                    style = styles.rowSubtitle,
                    color = palette.textSecondary
                )
                if (downloading) {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxWidth(),
                        color = scheme.primary,
                        trackColor = palette.selectionTint
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
                if (downloaded) {
                    AppSecondaryButton(
                        label = currentLanguage.pick("Descargado", "Downloaded", "Baixado"),
                        onClick = {},
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    AppPrimaryButton(
                        label = currentLanguage.pick("Descargar", "Download", "Baixar"),
                        onClick = {
                            if (!downloading) {
                                downloading = true
                                scope.launch {
                                    val ok = manager.download(spec.id) { fraction, message ->
                                        progress = fraction
                                        status = message
                                    }
                                    downloading = false
                                    if (ok) {
                                        status = currentLanguage.pick(
                                            "Completado",
                                            "Completed",
                                            "Concluído"
                                        )
                                    }
                                }
                            }
                        },
                        enabled = !downloading,
                        leadingIcon = Icons.Default.Download,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }
    }
}
