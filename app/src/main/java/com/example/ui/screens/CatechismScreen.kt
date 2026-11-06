package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.CatechismChapter
import com.example.data.models.CatechismNumberedArticle
import com.example.data.models.CatechismPart
import com.example.data.repository.CatechismData
import com.example.data.repository.CatechismOutlineSection
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.CompendiumQA
import com.example.data.repository.DoctrineFormula
import com.example.ui.components.InAppBrowserDialog
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.components.MenuPickerRow
import com.example.ui.components.TextFormat
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.AppDesign
import com.example.ui.theme.ReaderFontChoice
import com.example.ui.theme.AppTextStyles
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * The Catechism: three documents behind one selector — the 598 questions of the
 * official Compendium, the Catechism itself (§§ 1 – 2865) browsed part by part,
 * and the Holy See's own portals.
 *
 * The browsing half is an index: four parts, their sections, their chapters.
 * The reading half is long-form, so every body text is set in the identity's
 * reading face and held to the reading measure, and the reader's own A-/A+ and
 * local font controls ride on top of whichever type scale is in force.
 */
@Composable
fun CatechismScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    /** Deep-link: CCC paragraph (§) to open on entry, or null for Sources. */
    initialParagraph: Int? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current

    // The outline section tiling 1..2865 that contains the deep-linked
    // paragraph, resolved once per link target.
    val initialOutlineSection = remember(initialParagraph, repository) {
        initialParagraph?.let { paragraph ->
            repository.catechismOutline.firstOrNull { paragraph in it.firstNumber..it.lastNumber }
        }
    }
    // 0 = Compendio (598), 1 = Catecismo Universal (1-2865), 2 = Fuentes Oficiales
    // A deep link lands on the Universal Catechism at its paragraph.
    var mainMode by remember(initialParagraph) {
        mutableIntStateOf(if (initialParagraph != null) 1 else 2)
    }
    var selectedPartIndex by remember { mutableIntStateOf(0) }
    var selectedChapter by remember { mutableStateOf<CatechismChapter?>(null) }
    var selectedOutline by remember(initialParagraph) {
        mutableStateOf(initialOutlineSection)
    }
    var searchQuery by remember { mutableStateOf("") }
    var fontSizeMultiplier by remember { mutableFloatStateOf(1f) }
    var readerFont by remember { mutableStateOf(ReaderFontChoice.FOLLOW_APP) }

    val modeLabels = listOf(
        currentLanguage.pick("Compendio", "Compendium", "Compêndio"),
        currentLanguage.pick("Catecismo", "Catechism", "Catecismo"),
        currentLanguage.pick("Fuentes", "Sources", "Fontes")
    )
    val tabsInset = segmentedTabsInset()

    // One title for the screen, then the control that picks the view: the
    // first rows of whichever list is showing, so they scroll away with it.
    val header: LazyListScope.() -> Unit = {
        // Photographic hero band at the top of the catechism directory:
        // full-bleed church-window photograph with a fade into the
        // surface at its foot, above the title. Decorative only.
        item(key = "catechism_hero") {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(design.palette.canvas)
                    .testTag("catechism_hero_photo")
            ) {
                AssetPhoto(
                    assetPath = "heroes/church_window_light.jpg",
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().alpha(0.9f)
                )
                // Fade: clear over the upper photo, canvas by the foot of
                // the band, so the hero dissolves into the page.
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    design.palette.canvas
                                )
                            )
                        )
                )
                // Light scrim behind the title zone: the title stays
                // palette.textPrimary, so the photo is lightened to the
                // paper canvas under it rather than darkened.
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    design.palette.canvas.copy(alpha = 0.55f),
                                    design.palette.canvas.copy(alpha = 0.92f),
                                    design.palette.canvas
                                )
                            )
                        )
                )
                // Title overlaid at BottomStart with gutter + 24dp vertical
                // padding, Home-style.
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .padding(
                            horizontal = design.spacing.gutter,
                            vertical = 24.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = currentLanguage.pick("Catecismo", "Catechism", "Catecismo"),
                        style = LocalAppTextStyles.current.screenTitle.copy(fontWeight = FontWeight.W400),
                        color = design.palette.textPrimary,
                        maxLines = 1
                    )
                }
            }
        }
        item(key = "catechism_screen_tabs") {
            AppSegmentedTabs(
                options = modeLabels,
                selectedIndex = mainMode,
                onSelect = { mainMode = it },
                modifier = Modifier
                    .padding(horizontal = tabsInset)
                    .padding(bottom = design.spacing.cardGap / 2)
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("catechism_screen")
    ) {
        when (mainMode) {
            0 -> CompendiumView(
                header = header,
                repository = repository,
                currentLanguage = currentLanguage
            )

            1 -> UniversalCatechismView(
                header = header,
                repository = repository,
                currentLanguage = currentLanguage,
                initialParagraph = initialParagraph,
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                selectedPartIndex = selectedPartIndex,
                onSelectPart = { selectedPartIndex = it },
                selectedChapter = selectedChapter,
                onSelectChapter = { selectedChapter = it },
                selectedOutline = selectedOutline,
                onSelectOutline = { selectedOutline = it },
                fontSizeMultiplier = fontSizeMultiplier,
                onFontSizeChange = { fontSizeMultiplier = it },
                readerFont = readerFont,
                onReaderFontChange = { readerFont = readerFont.next() }
            )

            else -> VaticanOfficialLinksView(
                header = header,
                repository = repository,
                currentLanguage = currentLanguage
            )
        }
    }
}

// ----------------------------------------------------------- the Catechism

@Composable
private fun UniversalCatechismView(
    header: LazyListScope.() -> Unit,
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    initialParagraph: Int? = null,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedPartIndex: Int,
    onSelectPart: (Int) -> Unit,
    selectedChapter: CatechismChapter?,
    onSelectChapter: (CatechismChapter?) -> Unit,
    selectedOutline: CatechismOutlineSection?,
    onSelectOutline: (CatechismOutlineSection?) -> Unit,
    fontSizeMultiplier: Float,
    onFontSizeChange: (Float) -> Unit,
    readerFont: ReaderFontChoice,
    onReaderFontChange: () -> Unit
) {
    val parts = repository.catechismParts
    val currentPart = parts.getOrNull(selectedPartIndex) ?: parts.first()
    val chapter = selectedChapter

    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    // The index and the search results are one list, so the field keeps its
    // focus while the first letter typed turns the one into the other.
    @Composable
    fun IndexAndSearch() {
        // Hybrid v2 ranking: uncapped, so the header can report the true
        // total and the list shows the top 50 paragraph numbers.
        val scored = remember(searchQuery) {
            if (searchQuery.isBlank()) emptyList() else searchCatechism(searchQuery)
        }
        val results = remember(scored, repository) {
            scored.take(50).mapNotNull { repository.findCatechismArticle(it.number) }
        }
        val paragraphNumbers = remember(scored) { scored.take(50).map { it.number } }
        LazyColumn(Modifier.fillMaxSize()) {
            header()
            item(key = "ccc_search_row") {
                SearchRow(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = currentLanguage.pick(
                        "Número (ej. 1324) o tema",
                        "Number (e.g. 1324) or topic",
                        "Número (ex. 1324) ou tema"
                    ),
                    clearLabel = currentLanguage.pick("Limpiar", "Clear", "Limpar"),
                    fieldModifier = Modifier.testTag("ccc_search_input")
                )
            }
            if (searchQuery.isNotBlank()) {
                catechismSearchItems(
                    results = results,
                    totalCount = scored.size,
                    paragraphNumbers = paragraphNumbers,
                    currentLanguage = currentLanguage,
                    fontSizeMultiplier = fontSizeMultiplier,
                    readerFont = readerFont,
                    design = design
                )
            } else {
                catechismIndexItems(
                    parts = parts,
                    currentPart = currentPart,
                    selectedPartIndex = selectedPartIndex,
                    currentLanguage = currentLanguage,
                    onSelectPart = onSelectPart,
                    onSelectChapter = { onSelectChapter(it) },
                    outline = repository.catechismOutline,
                    onSelectOutline = onSelectOutline,
                    design = design,
                    styles = styles
                )
            }
        }
    }

    // A single call site: the list is the same composition whether it shows
    // the index or the results, which is what keeps the field focused.
    val showsList = searchQuery.isNotBlank() || (selectedOutline == null && chapter == null)
    when {
        showsList -> IndexAndSearch()

        selectedOutline != null -> {
            BackHandler { onSelectOutline(null) }
            ParagraphRangeView(
                repository = repository,
                section = selectedOutline,
                focusParagraph = initialParagraph,
                currentLanguage = currentLanguage,
                fontSizeMultiplier = fontSizeMultiplier,
                onFontSizeChange = onFontSizeChange,
                readerFont = readerFont,
                onReaderFontChange = onReaderFontChange,
                onBack = { onSelectOutline(null) }
            )
        }

        chapter != null -> {
            BackHandler { onSelectChapter(null) }
            ChapterReadingView(
                chapter = chapter,
                currentLanguage = currentLanguage,
                fontSizeMultiplier = fontSizeMultiplier,
                onFontSizeChange = onFontSizeChange,
                readerFont = readerFont,
                onReaderFontChange = onReaderFontChange,
                onBack = { onSelectChapter(null) }
            )
        }
    }
}

/**
 * The table of contents. The four parts are a selector rather than a further
 * level of navigation — the part in view is rubricated in the group, and its
 * own sections and chapters follow underneath.
 */
private fun LazyListScope.catechismIndexItems(
    parts: List<CatechismPart>,
    currentPart: CatechismPart,
    selectedPartIndex: Int,
    currentLanguage: AppLanguage,
    onSelectPart: (Int) -> Unit,
    onSelectChapter: (CatechismChapter) -> Unit,
    outline: List<CatechismOutlineSection>,
    onSelectOutline: (CatechismOutlineSection?) -> Unit,
    design: AppDesign,
    styles: AppTextStyles
) {
    run {
        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Las cuatro partes",
                    "The four parts",
                    "As quatro partes"
                ),
                subtitle = "§§ 1–2865"
            )
        }

        item {
            AppListGroup {
                parts.forEachIndexed { index, part ->
                    AppListRow(
                        title = part.title.get(currentLanguage),
                        // The range rides in the subtitle: as trailing text it
                        // squeezed the titles onto three lines.
                        subtitle = TextFormat.ranges(part.cccRange.replace(" - ", "–")) +
                            " · " + part.subtitle.get(currentLanguage),
                        selected = index == selectedPartIndex,
                        onClick = { onSelectPart(index) }
                    )
                }
            }
        }

        item { AppOrnament() }

        item {
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        text = currentPart.subtitle.get(currentLanguage) + " · " +
                            TextFormat.ranges(currentPart.cccRange),
                        style = styles.sectionEyebrow,
                        color = design.palette.textSecondary
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = currentPart.title.get(currentLanguage),
                        style = MaterialTheme.typography.titleMedium,
                        color = design.palette.textPrimary
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                    Text(
                        text = currentPart.description.get(currentLanguage),
                        style = styles.scripture,
                        color = design.palette.textSecondary
                    )
                }
            }
        }

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Índice de Secciones y Capítulos",
                    "Index of Sections and Chapters",
                    "Índice de Seções e Capítulos"
                )
            )
        }

        currentPart.sections.forEach { section ->
            item { SubHeading(section.title.get(currentLanguage)) }
            item {
                AppListGroup {
                    section.chapters.forEach { chapter ->
                        AppListRow(
                            title = chapter.title.get(currentLanguage),
                            subtitle = TextFormat.ranges(chapter.articleRangeDisplay),
                            onClick = { onSelectChapter(chapter) }
                        )
                    }
                }
            }
        }

        // The highlighted chapters above name only a few dozen paragraphs.
        // These sections tile 1..2865 without gap or overlap, so the rest of
        // the Catechism is reachable too.
        item { AppOrnament() }

        item {
            AppSectionHeader(
                title = currentLanguage.pick("Texto completo", "Complete text", "Texto completo"),
                subtitle = null
            )
        }

        outline.groupBy { it.partTitle.get(currentLanguage) }.forEach { (partTitle, sections) ->
            item { SubHeading(partTitle) }
            item {
                AppListGroup {
                    sections.forEach { section ->
                        AppListRow(
                            title = section.title.get(currentLanguage),
                            subtitle = "§§ ${section.firstNumber}–${section.lastNumber}",
                            onClick = { onSelectOutline(section) }
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/**
 * Every paragraph of one outline section, set in full and lazily, so a
 * two-hundred-paragraph stretch costs no more to open than a short one.
 */
@Composable
private fun ParagraphRangeView(
    repository: CatholicDataRepository,
    section: CatechismOutlineSection,
    focusParagraph: Int? = null,
    currentLanguage: AppLanguage,
    fontSizeMultiplier: Float,
    onFontSizeChange: (Float) -> Unit,
    readerFont: ReaderFontChoice,
    onReaderFontChange: () -> Unit,
    onBack: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val scope = rememberCoroutineScope()

    val paragraphCount = section.lastNumber - section.firstNumber + 1
    val listState = rememberLazyListState()
    // A deep-linked paragraph is scrolled into view on open (item 0 is the
    // title, paragraphs start at item 1).
    LaunchedEffect(focusParagraph, section) {
        if (focusParagraph != null && focusParagraph in section.firstNumber..section.lastNumber) {
            listState.scrollToItem(focusParagraph - section.firstNumber + 1)
        }
    }
    // List item 0 is the title; paragraph offsets live at items 1..count, and
    // the final item is the bottom gap. The first visible paragraph is where
    // the reader actually is, which is what progress and prev/next follow.
    val currentOffset by remember {
        derivedStateOf {
            (listState.firstVisibleItemIndex - 1).coerceIn(0, (paragraphCount - 1).coerceAtLeast(0))
        }
    }
    val currentNumber = section.firstNumber + currentOffset
    val progressLabel = currentLanguage.pick(
        "Párrafo $currentNumber de ${section.lastNumber} · §§ ${section.firstNumber}–${section.lastNumber}",
        "Paragraph $currentNumber of ${section.lastNumber} · §§ ${section.firstNumber}–${section.lastNumber}",
        "Parágrafo $currentNumber de ${section.lastNumber} · §§ ${section.firstNumber}–${section.lastNumber}"
    )

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = maxOf(design.spacing.rowMinHeight, 48.dp))
                .padding(horizontal = design.spacing.gutter)
                .padding(bottom = design.spacing.cardGap / 2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "§§ ${section.firstNumber}–${section.lastNumber}",
                style = styles.rowTitle,
                color = palette.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 48.dp)
                    .clickable(
                        role = Role.Button,
                        onClickLabel = currentLanguage.pick(
                            "Volver al índice del Catecismo",
                            "Back to the Catechism index",
                            "Voltar ao índice do Catecismo"
                        ),
                        onClick = onBack
                    )
                    .testTag("back_to_catechism_outline")
            )
            ReaderControls(
                currentLanguage = currentLanguage,
                fontSizeMultiplier = fontSizeMultiplier,
                onFontSizeChange = onFontSizeChange,
                readerFont = readerFont,
                onReaderFontChange = onReaderFontChange
            )
        }

        ReadingProgress(
            progress = (currentOffset + 1f) / paragraphCount.coerceAtLeast(1),
            label = progressLabel,
            stateLabel = currentLanguage.pick(
                "Progreso de la lectura",
                "Reading progress",
                "Progresso da leitura"
            ),
            testTag = "catechism_reading_progress"
        )

        AppDivider()

        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            item(key = "paragraph_range_title") {
                AppScreenTitle(
                    title = section.title.get(currentLanguage),
                    subtitle = section.partTitle.get(currentLanguage) + " · " + section.biblicalReferences
                )
            }

            items(paragraphCount, key = { "paragraph_$it" }) { offset ->
                val number = section.firstNumber + offset
                val article = repository.findCatechismArticle(number)
                if (article != null) {
                    ReadingColumn {
                        Column(
                            Modifier.padding(
                                horizontal = design.spacing.gutter,
                                vertical = design.spacing.cardGap / 2
                            )
                        ) {
                            Text(
                                text = "§ $number",
                                style = styles.verseNumber,
                                color = palette.textPrimary,
                                modifier = Modifier.semantics { heading() }
                            )
                            Spacer(Modifier.height(design.spacing.cardGap / 3))
                            Text(
                                text = article.content.get(currentLanguage),
                                style = readingStyle(fontSizeMultiplier, readerFont),
                                color = palette.textPrimary
                            )
                            article.biblicalReferences
                                ?.takeIf { it.isNotBlank() }
                                ?.let { references ->
                                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                                    CrossReferenceText(
                                        references = references,
                                        currentLanguage = currentLanguage,
                                        modifier = Modifier.testTag("catechism_cross_references_$number")
                                    )
                                }
                        }
                    }
                }
            }

            item(key = "paragraph_navigation") {
                ParagraphNavigation(
                    currentNumber = currentNumber,
                    firstNumber = section.firstNumber,
                    lastNumber = section.lastNumber,
                    currentLanguage = currentLanguage,
                    onGoTo = { target ->
                        scope.launch { listState.scrollToItem(target - section.firstNumber + 1) }
                    }
                )
            }

            item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
        }
    }
}

/**
 * One chapter, read at length. The back affordance and the type controls stay
 * put; the chapter's title page and its articles scroll under them.
 */
@Composable
private fun ChapterReadingView(
    chapter: CatechismChapter,
    currentLanguage: AppLanguage,
    fontSizeMultiplier: Float,
    onFontSizeChange: (Float) -> Unit,
    readerFont: ReaderFontChoice,
    onReaderFontChange: () -> Unit,
    onBack: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    val articleCount = chapter.articles.size
    val listState = rememberLazyListState()
    // Item 0 is the title; articles live at items 1..count, the gap is last.
    val currentArticle by remember {
        derivedStateOf {
            (listState.firstVisibleItemIndex - 1).coerceIn(0, (articleCount - 1).coerceAtLeast(0))
        }
    }
    val currentArticleNumber = chapter.articles.getOrNull(currentArticle)?.cccNumber
    val chapterProgressLabel = currentLanguage.pick(
        "Artículo ${currentArticle + 1} de $articleCount · ${chapter.articleRangeDisplay}",
        "Article ${currentArticle + 1} of $articleCount · ${chapter.articleRangeDisplay}",
        "Artigo ${currentArticle + 1} de $articleCount · ${chapter.articleRangeDisplay}"
    )

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = maxOf(design.spacing.rowMinHeight, 48.dp))
                .padding(horizontal = design.spacing.gutter)
                .padding(bottom = design.spacing.cardGap / 2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Weighted, so a long translation ellipsises instead of pushing the
            // type controls off the edge.
            Text(
                text = currentLanguage.pick(
                    "← Índice del Catecismo",
                    "← Catechism index",
                    "← Índice do Catecismo"
                ),
                style = styles.rowTitle,
                color = palette.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 48.dp)
                    .clickable(
                        role = Role.Button,
                        onClickLabel = currentLanguage.pick(
                            "Volver al índice del Catecismo",
                            "Back to the Catechism index",
                            "Voltar ao índice do Catecismo"
                        ),
                        onClick = onBack
                    )
                    .testTag("back_to_catechism_index")
            )
            ReaderControls(
                currentLanguage = currentLanguage,
                fontSizeMultiplier = fontSizeMultiplier,
                onFontSizeChange = onFontSizeChange,
                readerFont = readerFont,
                onReaderFontChange = onReaderFontChange
            )
        }

        ReadingProgress(
            progress = (currentArticle + 1f) / articleCount.coerceAtLeast(1),
            label = chapterProgressLabel,
            stateLabel = currentLanguage.pick(
                "Progreso de la lectura",
                "Reading progress",
                "Progresso da leitura"
            ),
            testTag = "catechism_chapter_progress"
        )

        AppDivider()

        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            item(key = "chapter_title") {
                AppScreenTitle(
                    title = chapter.title.get(currentLanguage),
                    subtitle = currentLanguage.pick(
                        "Catecismo de la Iglesia Católica: ${chapter.articleRangeDisplay}",
                        "Catechism of the Catholic Church: ${chapter.articleRangeDisplay}",
                        "Catecismo da Igreja Católica: ${chapter.articleRangeDisplay}"
                    )
                )
            }

            items(articleCount, key = { "chapter_article_$it" }) { index ->
                val article = chapter.articles[index]
                ReadingColumn {
                    CatechismArticleCard(
                        article = article,
                        currentLanguage = currentLanguage,
                        fontSizeMultiplier = fontSizeMultiplier,
                        readerFont = readerFont,
                        modifier = Modifier
                            .padding(horizontal = design.spacing.gutter)
                            .semantics {
                                if (article.cccNumber == currentArticleNumber) {
                                    contentDescription = currentLanguage.pick(
                                        "Párrafo actual § ${article.cccNumber}",
                                        "Current paragraph § ${article.cccNumber}",
                                        "Parágrafo atual § ${article.cccNumber}"
                                    )
                                }
                            }
                            .testTag("catechism_article_${article.cccNumber}")
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }

            item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
        }
    }
}

/**
 * Offline hybrid search (v2): tokenised, accent/case-insensitive,
 * trilingual, with theological synonym expansion, ranked
 * title/heading > exact phrase > all-words AND > any-word OR and capped
 * at 50. Returns the uncapped [CatechismData.ScoredParagraph] list so the
 * UI can report the true total and show the top-50 paragraph numbers;
 * repository.searchCatechismArticles keeps the same ranking for other
 * callers.
 */
private fun searchCatechism(
    searchQuery: String
): List<CatechismData.ScoredParagraph> =
    CatechismData.searchCatechismHybrid(searchQuery, cap = Int.MAX_VALUE)

private fun LazyListScope.catechismSearchItems(
    results: List<CatechismNumberedArticle>,
    totalCount: Int,
    paragraphNumbers: List<Int>,
    currentLanguage: AppLanguage,
    fontSizeMultiplier: Float,
    readerFont: ReaderFontChoice,
    design: AppDesign
) {
    run {
        val shownCount = results.size
        val countLabel = when {
            totalCount == 0 -> currentLanguage.pick(
                "0 artículos encontrados",
                "0 articles found",
                "0 artigos encontrados"
            )
            totalCount > shownCount -> currentLanguage.pick(
                "Mostrando $shownCount de $totalCount artículos",
                "Showing $shownCount of $totalCount articles",
                "Mostrando $shownCount de $totalCount artigos"
            )
            else -> currentLanguage.pick(
                "$totalCount artículos encontrados",
                "$totalCount articles found",
                "$totalCount artigos encontrados"
            )
        }
        item(key = "ccc_search_results_header") {
            AppSectionHeader(
                title = currentLanguage.pick("Resultados", "Results", "Resultados"),
                subtitle = countLabel,
                modifier = Modifier.testTag("catechism_search_count")
            )
        }

        if (paragraphNumbers.isNotEmpty()) {
            item(key = "ccc_search_paragraph_numbers") {
                ReadingColumn {
                    Text(
                        text = paragraphNumbers.joinToString(" · ") { "§ $it" },
                        style = LocalAppTextStyles.current.rowSubtitle,
                        color = design.palette.textSecondary,
                        modifier = Modifier
                            .padding(horizontal = design.spacing.gutter)
                            .testTag("catechism_search_paragraph_numbers")
                    )
                }
            }
        }

        if (results.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Sin resultados", "No results", "Sem resultados"),
                    body = currentLanguage.pick(
                        "No se encontraron artículos con ese criterio de búsqueda.",
                        "No articles were found for that search.",
                        "Nenhum artigo encontrado com esse critério de busca."
                    ),
                    icon = Icons.Default.MenuBook
                )
            }
        } else {
            items(results) { article ->
                ReadingColumn {
                    CatechismArticleCard(
                        article = article,
                        currentLanguage = currentLanguage,
                        fontSizeMultiplier = fontSizeMultiplier,
                        readerFont = readerFont,
                        modifier = Modifier.padding(horizontal = design.spacing.gutter)
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

@Composable
private fun CatechismArticleCard(
    article: CatechismNumberedArticle,
    currentLanguage: AppLanguage,
    fontSizeMultiplier: Float,
    readerFont: ReaderFontChoice,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current

    AppCard(modifier = modifier) {
        EntryHeader(
            label = "CIC § ${article.cccNumber}",
            reference = article.biblicalReferences?.let { "Ref: $it" },
            currentLanguage = currentLanguage
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = article.title.get(currentLanguage),
            style = MaterialTheme.typography.titleMedium,
            color = design.palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Text(
            text = article.content.get(currentLanguage),
            style = readingStyle(fontSizeMultiplier, readerFont),
            color = design.palette.textPrimary
        )
        article.inBriefSummary?.let { summary ->
            Spacer(Modifier.height(design.spacing.cardGap))
            InBriefNote(
                label = currentLanguage.pick("En Resumen", "In Brief", "Em Resumo"),
                text = summary.get(currentLanguage)
            )
        }
    }
}

// ------------------------------------------------------------- the Compendium

@Composable
private fun CompendiumView(
    header: LazyListScope.() -> Unit,
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current

    // 0 = Todas (598), 1..4 = Partes I a IV, 5 = Fórmulas Doctrinales
    var selectedFilterIndex by remember { mutableIntStateOf(0) }
    var compendiumSearch by remember { mutableStateOf("") }
    // Full list sorted by question number and deduplicated by number, so all
    // 598 questions are present exactly once before any filter/search runs.
    // Root cause of "not all show": previously a text search only matched the
    // current UI language, a numeric query short-circuited to an exact-number
    // match (hiding every text/CCC-reference match), and the result was then
    // intersected with the selected Part — a stale Part choice could hide a
    // question the search had actually found. There was no hard take()/limit.
    val allQuestions: List<CompendiumQA> = remember(repository) {
        repository.compendiumQAEntries.distinctBy { it.number }.sortedBy { it.number }
    }
    val totalQuestions = allQuestions.size

    val filteredQuestions = remember(selectedFilterIndex, compendiumSearch, currentLanguage, allQuestions) {
        if (compendiumSearch.isBlank()) {
            // No search: the Part filter alone decides (0 = Todas / All).
            when (selectedFilterIndex) {
                1 -> allQuestions.filter { it.part == 1 }
                2 -> allQuestions.filter { it.part == 2 }
                3 -> allQuestions.filter { it.part == 3 }
                4 -> allQuestions.filter { it.part == 4 }
                else -> allQuestions
            }
        } else {
            // Search runs over ALL 598 questions in ALL three languages, so a
            // previously selected Part can never hide a match. A query is
            // matched as a unified OR — never short-circuited — against the
            // question number, the CCC reference, and question/answer/section
            // text in ES, EN and PT.
            val query = compendiumSearch.trim()
            val q = query.lowercase()
            val num = query.toIntOrNull()
            allQuestions.filter { entry ->
                (num != null && entry.number == num) ||
                    entry.number.toString() == query ||
                    entry.cccReference.lowercase().contains(q) ||
                    entry.question.es.lowercase().contains(q) ||
                    entry.question.en.lowercase().contains(q) ||
                    entry.question.pt.lowercase().contains(q) ||
                    entry.answer.es.lowercase().contains(q) ||
                    entry.answer.en.lowercase().contains(q) ||
                    entry.answer.pt.lowercase().contains(q) ||
                    entry.section.es.lowercase().contains(q) ||
                    entry.section.en.lowercase().contains(q) ||
                    entry.section.pt.lowercase().contains(q)
            }
        }
    }

    val filterNames = when (currentLanguage) {
        AppLanguage.ES -> listOf(
            "Todas", "I. La profesión de la fe", "II. Los sacramentos",
            "III. La vida en Cristo", "IV. La oración", "Fórmulas de la doctrina"
        )
        AppLanguage.EN -> listOf(
            "All parts", "I. The Profession of Faith", "II. The Sacraments",
            "III. Life in Christ", "IV. Christian Prayer", "Formulas of doctrine"
        )
        AppLanguage.PT -> listOf(
            "Todas", "I. A profissão da fé", "II. Os sacramentos",
            "III. A vida em Cristo", "IV. A oração", "Fórmulas da doutrina"
        )
    }

    run {
        LazyColumn(Modifier.fillMaxSize()) {
            header()

            item(key = "compendium_search_row") {
                SearchRow(
                    value = compendiumSearch,
                    onValueChange = { compendiumSearch = it },
                    placeholder = currentLanguage.pick(
                        "Buscar en 598 preguntas",
                        "Search 598 questions",
                        "Buscar em 598 perguntas"
                    ),
                    clearLabel = currentLanguage.pick("Limpiar", "Clear", "Limpar"),
                    fieldModifier = Modifier.testTag("compendium_search_input")
                )
            }

            // The part is one row with a menu, under the screen's own selector.
            item(key = "compendium_filters") {
                AppListGroup {
                    MenuPickerRow(
                        title = currentLanguage.pick("Parte", "Part", "Parte"),
                        options = filterNames.mapIndexed { i, name -> i to name },
                        selected = selectedFilterIndex,
                        onSelect = { selectedFilterIndex = it },
                        testTag = "compendium_part_picker"
                    )
                }
            }

            if (selectedFilterIndex == filterNames.lastIndex) {
                item {
                    AppSectionHeader(
                        title = currentLanguage.pick(
                            "Fórmulas Principales de la Doctrina Católica",
                            "Principal Formulas of Catholic Doctrine",
                            "Fórmulas Principais da Doutrina Católica"
                        )
                    )
                }
                items(repository.doctrineFormulas) { formula ->
                    ReadingColumn {
                        DoctrineFormulaCard(
                            formula = formula,
                            currentLanguage = currentLanguage,
                            modifier = Modifier.padding(horizontal = design.spacing.gutter)
                        )
                    }
                    Spacer(Modifier.height(design.spacing.cardGap))
                }
            } else {
                item {
                    AppSectionHeader(
                        title = currentLanguage.pick(
                            "Compendio Oficial",
                            "Official Compendium",
                            "Compêndio Oficial"
                        ),
                        subtitle = currentLanguage.pick(
                            "Mostrando ${filteredQuestions.size} de $totalQuestions preguntas",
                            "Showing ${filteredQuestions.size} of $totalQuestions questions",
                            "Mostrando ${filteredQuestions.size} de $totalQuestions perguntas"
                        ),
                        modifier = Modifier.testTag("compendium_count")
                    )
                }

                if (filteredQuestions.isEmpty()) {
                    item {
                        AppEmptyState(
                            title = currentLanguage.pick(
                                "Sin resultados",
                                "No results",
                                "Sem resultados"
                            ),
                            body = currentLanguage.pick(
                                "Ninguna pregunta coincide con esa búsqueda. Prueba un número del 1 al 598 o otra palabra.",
                                "No question matches that search. Try a number from 1 to 598, or another word.",
                                "Nenhuma pergunta corresponde a essa busca. Tente um número de 1 a 598 ou outra palavra."
                            ),
                            icon = Icons.Default.Search
                        )
                    }
                } else {
                    items(filteredQuestions, key = { it.number }) { question ->
                        ReadingColumn {
                            CompendiumQuestionCard(
                                question = question,
                                currentLanguage = currentLanguage,
                                modifier = Modifier.padding(horizontal = design.spacing.gutter)
                            )
                        }
                        Spacer(Modifier.height(design.spacing.cardGap))
                    }
                }
            }

            item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
        }
    }
}

@Composable
private fun CompendiumQuestionCard(
    question: CompendiumQA,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    AppCard(modifier = modifier) {
        EntryHeader(
            label = currentLanguage.pick(
                "Pregunta ${question.number}",
                "Question ${question.number}",
                "Pergunta ${question.number}"
            ),
            reference = question.cccReference.ifBlank { null },
            currentLanguage = currentLanguage
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = question.question.get(currentLanguage),
            style = MaterialTheme.typography.titleMedium,
            color = design.palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Text(
            text = question.answer.get(currentLanguage),
            style = styles.scripture,
            color = design.palette.textPrimary
        )
    }
}

@Composable
private fun DoctrineFormulaCard(
    formula: DoctrineFormula,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    AppCard(modifier = modifier) {
        Text(
            text = formula.title.get(currentLanguage),
            style = MaterialTheme.typography.titleMedium,
            color = design.palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        formula.items.forEachIndexed { index, item ->
            if (index > 0) Spacer(Modifier.height(design.spacing.cardGap / 2))
            Row(Modifier.fillMaxWidth()) {
                Text(text = "•", style = styles.scripture, color = design.palette.gold)
                Spacer(Modifier.width(design.spacing.iconLabelGap / 2))
                Text(
                    text = item.get(currentLanguage),
                    style = styles.scripture,
                    color = design.palette.textPrimary,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// ---------------------------------------------------------- official sources

@Composable
private fun VaticanOfficialLinksView(
    header: LazyListScope.() -> Unit,
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    var webViewUrl by remember { mutableStateOf<String?>(null) }

    // The reader's own language first; the other two follow.
    val ownLanguage = currentLanguage.pick("Español", "English", "Português")
    val links = remember(currentLanguage) {
        repository.officialVaticanLinks.sortedByDescending { it.language == ownLanguage }
    }

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        // The texts as one list in the reader's language, then the same in
        // the other two: rows that open, not cards of mismatched buttons.
        val own = links.firstOrNull()
        if (own != null) {
            item {
                AppSectionHeader(
                    title = currentLanguage.pick("En vatican.va", "On vatican.va", "Em vatican.va")
                )
                AppListGroup {
                    AppListRow(
                        title = currentLanguage.pick(
                            "Catecismo de la Iglesia Católica",
                            "Catechism of the Catholic Church",
                            "Catecismo da Igreja Católica"
                        ),
                        subtitle = currentLanguage.pick(
                            "Texto completo · §§ 1–2865",
                            "Complete text · §§ 1–2865",
                            "Texto completo · §§ 1–2865"
                        ),
                        onClick = { webViewUrl = own.catechismUrl },
                        modifier = Modifier.testTag("catechism_source_ccc")
                    )
                    AppListRow(
                        title = currentLanguage.pick(
                            "Compendio del Catecismo",
                            "Compendium of the Catechism",
                            "Compêndio do Catecismo"
                        ),
                        subtitle = currentLanguage.pick(
                            "598 preguntas y respuestas · vatican.va",
                            "598 questions and answers · vatican.va",
                            "598 perguntas e respostas · vatican.va"
                        ),
                        onClick = { context.openExternally(own.compendiumUrl) },
                        modifier = Modifier.testTag("catechism_source_compendium")
                    )
                }
            }
        }
        if (links.size > 1) {
            item {
                AppSectionHeader(
                    title = currentLanguage.pick("En otros idiomas", "In other languages", "Noutros idiomas")
                )
                AppListGroup {
                    links.drop(1).forEach { link ->
                        AppListRow(
                            title = link.language,
                            subtitle = currentLanguage.pick(
                                "Catecismo, texto completo",
                                "Catechism, complete text",
                                "Catecismo, texto completo"
                            ),
                            onClick = { webViewUrl = link.catechismUrl }
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }

    webViewUrl?.let { url ->
        InAppBrowserDialog(url = url, onDismiss = { webViewUrl = null })
    }
}

/**
 * One document: its name, one line of what it holds, and a single row of
 * actions — the primary one as a button, reading in the app as a text action
 * beside it — rather than two full-width pills stacked per entry.
 */
@Composable
private fun DocumentLink(
    title: String,
    detail: String,
    openLabel: String,
    onOpen: () -> Unit,
    inAppLabel: String?,
    onOpenInApp: (() -> Unit)?
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Text(
        text = title,
        style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
        color = design.palette.textPrimary
    )
    Spacer(Modifier.height(design.spacing.cardGap / 3))
    Text(text = detail, style = styles.rowSubtitle, color = design.palette.textSecondary)
    Spacer(Modifier.height(design.spacing.cardGap))
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
    ) {
        AppSecondaryButton(
            label = openLabel,
            onClick = onOpen,
            leadingIcon = Icons.Default.OpenInNew
        )
        if (inAppLabel != null && onOpenInApp != null) {
            Text(
                text = caps(inAppLabel),
                style = styles.buttonLabel,
                color = design.palette.textPrimary,
                textDecoration = TextDecoration.Underline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .heightIn(min = 48.dp)
                    .clickable(
                        role = Role.Button,
                        onClickLabel = inAppLabel,
                        onClick = onOpenInApp
                    )
                    .padding(horizontal = design.spacing.iconLabelGap, vertical = design.spacing.cardGap / 2)
            )
        }
    }
}

/** "https://www.vatican.va/archive/…" → "vatican.va". */
private fun hostOf(url: String): String? =
    runCatching { Uri.parse(url).host }.getOrNull()
        ?.removePrefix("www.")
        ?.takeIf { it.isNotBlank() }

/** Vatican sets its labels as inscriptional capitals; the other two do not. */
@Composable
private fun caps(text: String): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) text.uppercase() else text

// ------------------------------------------------------------------- pieces

/**
 * A search field with the affordance that clears it. Only the Material field
 * carries its own clear button, so the action is spelled out beside the field
 * and reads the same way in all three identities.
 */
@Composable
private fun SearchRow(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    clearLabel: String,
    fieldModifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(vertical = design.spacing.cardGap / 2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppSearchField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            modifier = fieldModifier.weight(1f)
        )
        if (value.isNotEmpty()) {
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Text(
                text = clearLabel,
                style = styles.buttonLabel,
                color = design.palette.textPrimary,
                textDecoration = TextDecoration.Underline,
                maxLines = 1,
                modifier = Modifier
                    .heightIn(min = 48.dp)
                    .clickable(
                        role = Role.Button,
                        onClickLabel = clearLabel,
                        onClick = { onValueChange("") }
                    )
                    .padding(horizontal = design.spacing.iconLabelGap)
                    .testTag("search_clear_button")
            )
        }
    }
}

/** The siglum that opens an entry, and the reference it cites. */
@Composable
private fun EntryHeader(
    label: String,
    reference: String?,
    currentLanguage: AppLanguage? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Text(
            text = label,
            style = styles.sectionEyebrow,
            color = design.palette.textPrimary
        )
        if (reference != null) {
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Text(
                text = reference,
                style = styles.sectionEyebrow,
                color = design.palette.textSecondary,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .semantics {
                        contentDescription = (currentLanguage?.pick(
                            "Referencias cruzadas",
                            "Cross-references",
                            "Referências cruzadas"
                        ) ?: "Cross-references") + ": " + reference
                    }
                    .testTag("catechism_cross_references")
            )
        }
    }
}

/** The Catechism's own "in brief", set as a marginal note rather than an alert. */
@Composable
private fun InBriefNote(label: String, text: String) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Box(
            Modifier
                .width(2.dp)
                .fillMaxHeight()
                .background(design.palette.gold)
        )
        Column(Modifier.padding(start = design.spacing.iconLabelGap)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = design.palette.textSecondary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(design.spacing.iconLabelGap / 2))
                Text(
                    text = label,
                    style = styles.sectionEyebrow,
                    color = design.palette.textSecondary
                )
            }
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = text,
                style = styles.rowSubtitle,
                color = design.palette.textSecondary
            )
        }
    }
}

/**
 * Second-level heading. The kit offers one heading rank and this index needs
 * two — the part's index, then each section within it — so this one is built
 * from tokens and keeps the identity's own title alignment.
 */
@Composable
private fun SubHeading(text: String) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val centred = design.decor.titleAlignment == Alignment.CenterHorizontally

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(top = design.spacing.sectionGap / 2, bottom = design.spacing.cardGap / 2),
        horizontalAlignment = design.decor.titleAlignment
    ) {
        Text(
            text = text,
            style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
            color = design.palette.textSecondary,
            textAlign = if (centred) TextAlign.Center else TextAlign.Start,
            modifier = Modifier.semantics { heading() }
        )
    }
}

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
 * The reader's type controls: A-/A+ scale the reading face, the third chip
 * cycles the local font. One call site per reading view, so both toolbars
 * name the same targets. Filter chips are 32dp by spec; the 48dp floor is
 * the caller's, and every chip here carries it, plus the spoken name a
 * screen reader needs for a two-character label.
 */
@Composable
private fun ReaderControls(
    currentLanguage: AppLanguage,
    fontSizeMultiplier: Float,
    onFontSizeChange: (Float) -> Unit,
    readerFont: ReaderFontChoice,
    onReaderFontChange: () -> Unit
) {
    val design = LocalAppDesign.current
    Row(verticalAlignment = Alignment.CenterVertically) {
        AppChip(
            label = "A-",
            selected = false,
            onClick = {
                if (fontSizeMultiplier > 0.85f) onFontSizeChange(fontSizeMultiplier - 0.15f)
            },
            modifier = Modifier
                .heightIn(min = 48.dp)
                .semantics {
                    contentDescription = currentLanguage.pick(
                        "Reducir el tamaño del texto",
                        "Decrease text size",
                        "Diminuir o tamanho do texto"
                    )
                }
                .testTag("reader_font_decrease")
        )
        Spacer(Modifier.width(design.spacing.cardGap / 2))
        AppChip(
            label = "A+",
            selected = false,
            onClick = {
                if (fontSizeMultiplier < 1.45f) onFontSizeChange(fontSizeMultiplier + 0.15f)
            },
            modifier = Modifier
                .heightIn(min = 48.dp)
                .semantics {
                    contentDescription = currentLanguage.pick(
                        "Aumentar el tamaño del texto",
                        "Increase text size",
                        "Aumentar o tamanho do texto"
                    )
                }
                .testTag("reader_font_increase")
        )
        Spacer(Modifier.width(design.spacing.cardGap / 2))
        AppChip(
            label = readerFont.label(currentLanguage),
            selected = readerFont != ReaderFontChoice.FOLLOW_APP,
            onClick = onReaderFontChange,
            modifier = Modifier
                .heightIn(min = 48.dp)
                .testTag("reader_font_choice")
        )
    }
}

/**
 * Where the reader stands in the stretch being read, as a bar and as
 * spoken text. The bar's colours are palette tokens, so it tracks dark
 * mode; the label names the current paragraph and the whole range, which
 * is the number a reader would say aloud.
 */
@Composable
private fun ReadingProgress(
    progress: Float,
    label: String,
    stateLabel: String,
    testTag: String
) {
    val design = LocalAppDesign.current
    LinearProgressIndicator(
        progress = { progress.coerceIn(0f, 1f) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .semantics {
                contentDescription = label
                stateDescription = stateLabel
            }
            .testTag(testTag),
        color = design.palette.accent,
        trackColor = design.palette.selectionTint
    )
}

/**
 * Previous / next paragraph, pinned at the foot of a paragraph range. The
 * buttons are the kit's 48dp buttons; each carries its target paragraph
 * number in its spoken name, and the boundary one is disabled rather than
 * a dead target.
 */
@Composable
private fun ParagraphNavigation(
    currentNumber: Int,
    firstNumber: Int,
    lastNumber: Int,
    currentLanguage: AppLanguage,
    onGoTo: (Int) -> Unit
) {
    val design = LocalAppDesign.current
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(top = design.spacing.cardGap),
        horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppSecondaryButton(
            label = currentLanguage.pick("← Anterior", "← Previous", "← Anterior"),
            onClick = { onGoTo(currentNumber - 1) },
            enabled = currentNumber > firstNumber,
            modifier = Modifier
                .weight(1f)
                .semantics {
                    contentDescription = currentLanguage.pick(
                        "Ir al párrafo anterior § ${currentNumber - 1}",
                        "Go to previous paragraph § ${currentNumber - 1}",
                        "Ir para o parágrafo anterior § ${currentNumber - 1}"
                    )
                }
                .testTag("paragraph_prev_button")
        )
        AppPrimaryButton(
            label = currentLanguage.pick("Siguiente →", "Next →", "Seguinte →"),
            onClick = { onGoTo(currentNumber + 1) },
            enabled = currentNumber < lastNumber,
            modifier = Modifier
                .weight(1f)
                .semantics {
                    contentDescription = currentLanguage.pick(
                        "Ir al párrafo siguiente § ${currentNumber + 1}",
                        "Go to next paragraph § ${currentNumber + 1}",
                        "Ir para o próximo parágrafo § ${currentNumber + 1}"
                    )
                }
                .testTag("paragraph_next_button")
        )
    }
}

/**
 * A paragraph's cross-references, verbatim. Set as a labelled marginal
 * line — ink from the palette's text ranks, never gold at 11sp — and named
 * as what it is for a screen reader.
 */
@Composable
private fun CrossReferenceText(
    references: String,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val label = currentLanguage.pick(
        "Referencias cruzadas",
        "Cross-references",
        "Referências cruzadas"
    )
    Text(
        text = "Ref: $references",
        style = styles.rowSubtitle,
        color = design.palette.textSecondary,
        modifier = modifier.semantics {
            contentDescription = "$label: $references"
        }
    )
}

/**
 * The body of an article. The identity supplies the reading face and the
 * leading; the reader's A-/A+ and optional font override ride on top of it, so the
 * type scale and the reader's preference stay independent of one another.
 */
@Composable
private fun readingStyle(fontSizeMultiplier: Float, readerFont: ReaderFontChoice): TextStyle {
    val styles = LocalAppTextStyles.current
    val base = styles.scripture
    return base.copy(
        fontSize = base.fontSize * fontSizeMultiplier,
        lineHeight = base.lineHeight * fontSizeMultiplier,
        fontFamily = readerFont.family(base.fontFamily, LocalAppDesign.current.language)
    )
}

/**
 * No kit insets its own segmented control, so the gutter is the caller's to
 * give. Vatican is the exception: its strip is a ruled band whose hairline runs
 * the full width of the leaf, and inset it stops meeting the edges.
 */
@Composable
private fun segmentedTabsInset(): Dp {
    val design = LocalAppDesign.current
    return design.spacing.gutter
}

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

private fun Context.openExternally(url: String) {
    runCatching { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
}

/**
 * Photographic hero art bundled under assets/heroes/. Decoded down-sampled
 * so its width does not exceed 1080px — a missing asset draws nothing so
 * the surface beneath shows through.
 */
@Composable
private fun AssetPhoto(
    assetPath: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val bitmap = remember(assetPath) { decodeSampledBitmap(context, assetPath) }
    if (bitmap != null) {
        Image(
            bitmap = bitmap,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}

private fun decodeSampledBitmap(
    context: Context,
    assetPath: String,
    maxWidthPx: Int = 1080
): ImageBitmap? = try {
    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    context.assets.open(assetPath).use { BitmapFactory.decodeStream(it, null, bounds) }
    if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
        null
    } else {
        var sampleSize = 1
        while (bounds.outWidth / sampleSize > maxWidthPx) sampleSize *= 2
        val options = BitmapFactory.Options().apply { inSampleSize = sampleSize }
        val bitmap: Bitmap? = context.assets.open(assetPath).use {
            BitmapFactory.decodeStream(it, null, options)
        }
        bitmap?.asImageBitmap()
    }
} catch (_: Exception) {
    null
}
