package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.CatechismChapter
import com.example.data.models.CatechismNumberedArticle
import com.example.data.models.CatechismPart
import com.example.data.repository.CatechismOutlineSection
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.CompendiumQA
import com.example.data.repository.DoctrineFormula
import com.example.ui.components.InAppBrowserDialog
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.AppDesign
import com.example.ui.theme.AppFonts
import com.example.ui.theme.AppTextStyles
import com.example.ui.theme.DesignLanguage
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
 * serif controls ride on top of whichever type scale is in force.
 */
@Composable
fun CatechismScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current

    // 0 = Compendio (598), 1 = Catecismo Universal (1-2865), 2 = Fuentes Oficiales
    var mainMode by remember { mutableIntStateOf(2) }
    var selectedPartIndex by remember { mutableIntStateOf(0) }
    var selectedChapter by remember { mutableStateOf<CatechismChapter?>(null) }
    var selectedOutline by remember { mutableStateOf<CatechismOutlineSection?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var fontSizeMultiplier by remember { mutableFloatStateOf(1f) }
    var useSerifFont by remember { mutableStateOf(true) }

    val modeLabels = listOf(
        currentLanguage.pick("Compendio", "Compendium", "Compêndio"),
        currentLanguage.pick("Catecismo", "Catechism", "Catecismo"),
        currentLanguage.pick("Fuentes", "Sources", "Fontes")
    )
    val tabsInset = segmentedTabsInset()

    // One title for the screen, then the control that picks the view: the
    // first rows of whichever list is showing, so they scroll away with it.
    val header: LazyListScope.() -> Unit = {
        item(key = "catechism_screen_title") {
            AppScreenTitle(
                title = currentLanguage.pick("Catecismo", "Catechism", "Catecismo"),
                subtitle = currentLanguage.pick(
                    "La fe de la Iglesia, íntegra",
                    "The faith of the Church, in full",
                    "A fé da Igreja, na íntegra"
                )
            )
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
                useSerifFont = useSerifFont,
                onToggleSerif = { useSerifFont = !useSerifFont }
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
    useSerifFont: Boolean,
    onToggleSerif: () -> Unit
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
        val results = remember(searchQuery, currentLanguage) {
            if (searchQuery.isBlank()) emptyList() else searchCatechism(repository, parts, searchQuery, currentLanguage)
        }
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
                    currentLanguage = currentLanguage,
                    fontSizeMultiplier = fontSizeMultiplier,
                    useSerifFont = useSerifFont,
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
                currentLanguage = currentLanguage,
                fontSizeMultiplier = fontSizeMultiplier,
                onFontSizeChange = onFontSizeChange,
                useSerifFont = useSerifFont,
                onToggleSerif = onToggleSerif,
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
                useSerifFont = useSerifFont,
                onToggleSerif = onToggleSerif,
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
                subtitle = currentLanguage.pick(
                    "Catecismo de la Iglesia Católica · §§ 1 – 2865",
                    "Catechism of the Catholic Church · §§ 1 – 2865",
                    "Catecismo da Igreja Católica · §§ 1 – 2865"
                )
            )
        }

        item {
            AppListGroup {
                parts.forEachIndexed { index, part ->
                    AppListRow(
                        title = part.title.get(currentLanguage),
                        subtitle = part.subtitle.get(currentLanguage),
                        trailingText = part.cccRange,
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
                        text = currentPart.subtitle.get(currentLanguage) + " • " + currentPart.cccRange,
                        style = styles.sectionEyebrow,
                        color = design.palette.gold
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
                            subtitle = currentLanguage.pick(
                                "${chapter.articles.size} artículos destacados",
                                "${chapter.articles.size} highlighted articles",
                                "${chapter.articles.size} artigos destacados"
                            ),
                            trailingText = chapter.articleRangeDisplay,
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
                subtitle = currentLanguage.pick(
                    "Los 2.865 numerales, íntegros",
                    "All 2,865 paragraphs, in full",
                    "Os 2.865 números, na íntegra"
                )
            )
        }

        outline.groupBy { it.partTitle.get(currentLanguage) }.forEach { (partTitle, sections) ->
            item { SubHeading(partTitle) }
            item {
                AppListGroup {
                    sections.forEach { section ->
                        AppListRow(
                            title = section.title.get(currentLanguage),
                            subtitle = currentLanguage.pick(
                                "${section.paragraphCount} numerales",
                                "${section.paragraphCount} paragraphs",
                                "${section.paragraphCount} números"
                            ),
                            trailingText = "§§ ${section.firstNumber}–${section.lastNumber}",
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
    currentLanguage: AppLanguage,
    fontSizeMultiplier: Float,
    onFontSizeChange: (Float) -> Unit,
    useSerifFont: Boolean,
    onToggleSerif: () -> Unit,
    onBack: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .padding(bottom = design.spacing.cardGap / 2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "§§ ${section.firstNumber}–${section.lastNumber}",
                style = styles.sectionEyebrow,
                color = design.palette.gold,
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onBack)
                    .testTag("back_to_catechism_outline")
            )
            AppChip(
                label = "A-",
                selected = false,
                onClick = {
                    if (fontSizeMultiplier > 0.85f) onFontSizeChange(fontSizeMultiplier - 0.15f)
                }
            )
            Spacer(Modifier.width(design.spacing.cardGap / 2))
            AppChip(
                label = "A+",
                selected = false,
                onClick = {
                    if (fontSizeMultiplier < 1.45f) onFontSizeChange(fontSizeMultiplier + 0.15f)
                }
            )
            Spacer(Modifier.width(design.spacing.cardGap / 2))
            AppChip(
                label = if (useSerifFont) "Serif" else "Sans",
                selected = useSerifFont,
                onClick = onToggleSerif
            )
        }

        AppDivider()

        LazyColumn(Modifier.fillMaxSize()) {
            item {
                AppScreenTitle(
                    title = section.title.get(currentLanguage),
                    subtitle = section.partTitle.get(currentLanguage) + " · " + section.biblicalReferences
                )
            }

            items(section.lastNumber - section.firstNumber + 1) { offset ->
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
                                color = design.palette.gold
                            )
                            Spacer(Modifier.height(design.spacing.cardGap / 3))
                            Text(
                                text = article.content.get(currentLanguage),
                                style = readingStyle(fontSizeMultiplier, useSerifFont),
                                color = design.palette.textPrimary
                            )
                        }
                    }
                }
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
    useSerifFont: Boolean,
    onToggleSerif: () -> Unit,
    onBack: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
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
                color = design.palette.accent,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onBack)
                    .padding(vertical = design.spacing.rowPaddingVertical)
                    .testTag("back_to_catechism_index")
            )
            AppChip(
                label = "A-",
                selected = false,
                onClick = {
                    if (fontSizeMultiplier > 0.85f) onFontSizeChange(fontSizeMultiplier - 0.15f)
                }
            )
            Spacer(Modifier.width(design.spacing.cardGap / 2))
            AppChip(
                label = "A+",
                selected = false,
                onClick = {
                    if (fontSizeMultiplier < 1.45f) onFontSizeChange(fontSizeMultiplier + 0.15f)
                }
            )
            Spacer(Modifier.width(design.spacing.cardGap / 2))
            AppChip(
                label = if (useSerifFont) "Serif" else "Sans",
                selected = useSerifFont,
                onClick = onToggleSerif
            )
        }

        AppDivider()

        LazyColumn(Modifier.fillMaxSize()) {
            item {
                AppScreenTitle(
                    title = chapter.title.get(currentLanguage),
                    subtitle = currentLanguage.pick(
                        "Catecismo de la Iglesia Católica: ${chapter.articleRangeDisplay}",
                        "Catechism of the Catholic Church: ${chapter.articleRangeDisplay}",
                        "Catecismo da Igreja Católica: ${chapter.articleRangeDisplay}"
                    )
                )
            }

            items(chapter.articles) { article ->
                ReadingColumn {
                    CatechismArticleCard(
                        article = article,
                        currentLanguage = currentLanguage,
                        fontSizeMultiplier = fontSizeMultiplier,
                        useSerif = useSerifFont,
                        modifier = Modifier.padding(horizontal = design.spacing.gutter)
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }

            item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
        }
    }
}

/**
 * Search by CCC number — which reaches any of the 2,865 articles — or by topic
 * across the titles and bodies of the indexed ones.
 */
private fun searchCatechism(
    repository: CatholicDataRepository,
    parts: List<CatechismPart>,
    searchQuery: String,
    currentLanguage: AppLanguage
): List<CatechismNumberedArticle> {
    val numQuery = searchQuery.trim().toIntOrNull()
    return if (numQuery != null) {
        listOfNotNull(repository.findCatechismArticle(numQuery))
    } else {
        parts.flatMap { it.sections }
            .flatMap { it.chapters }
            .flatMap { it.articles }
            .filter { art ->
                art.title.get(currentLanguage).contains(searchQuery, ignoreCase = true) ||
                    art.content.get(currentLanguage).contains(searchQuery, ignoreCase = true) ||
                    art.cccNumber.toString() == searchQuery.trim()
            }
    }
}

private fun LazyListScope.catechismSearchItems(
    results: List<CatechismNumberedArticle>,
    currentLanguage: AppLanguage,
    fontSizeMultiplier: Float,
    useSerifFont: Boolean,
    design: AppDesign
) {
    run {
        item {
            AppSectionHeader(
                title = currentLanguage.pick("Resultados", "Results", "Resultados"),
                subtitle = currentLanguage.pick(
                    "${results.size} artículos encontrados",
                    "${results.size} articles found",
                    "${results.size} artigos encontrados"
                )
            )
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
                        useSerif = useSerifFont,
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
    useSerif: Boolean,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current

    AppCard(modifier = modifier) {
        EntryHeader(
            label = "CIC § ${article.cccNumber}",
            reference = article.biblicalReferences?.let { "Ref: $it" }
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
            style = readingStyle(fontSizeMultiplier, useSerif),
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
    val allQuestions: List<CompendiumQA> = repository.compendiumQAEntries

    val filteredQuestions = remember(selectedFilterIndex, compendiumSearch, currentLanguage) {
        val byPart = when (selectedFilterIndex) {
            1 -> allQuestions.filter { it.part == 1 }
            2 -> allQuestions.filter { it.part == 2 }
            3 -> allQuestions.filter { it.part == 3 }
            4 -> allQuestions.filter { it.part == 4 }
            else -> allQuestions
        }

        if (compendiumSearch.isBlank()) {
            byPart
        } else {
            val query = compendiumSearch.trim()
            val num = query.toIntOrNull()
            if (num != null) {
                byPart.filter { it.number == num || it.cccReference.contains(query) }
            } else {
                byPart.filter {
                    it.question.get(currentLanguage).contains(query, ignoreCase = true) ||
                        it.answer.get(currentLanguage).contains(query, ignoreCase = true)
                }
            }
        }
    }

    val filterNames = when (currentLanguage) {
        AppLanguage.ES -> listOf(
            "Todas (598)", "P. I: La Fe", "P. II: Sacramentos",
            "P. III: Vida en Cristo", "P. IV: Oración", "Fórmulas Doctrinales"
        )
        AppLanguage.EN -> listOf(
            "All (598)", "P. I: The Faith", "P. II: Sacraments",
            "P. III: Life in Christ", "P. IV: Prayer", "Doctrinal Formulas"
        )
        AppLanguage.PT -> listOf(
            "Todas (598)", "P. I: A Fé", "P. II: Sacramentos",
            "P. III: Vida em Cristo", "P. IV: Oração", "Fórmulas Doutrinais"
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

            // The run scrolls, and its own padding carries the gutter at both
            // ends, so a scrolled row comes to rest on the margin.
            item(key = "compendium_filters") {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = design.spacing.gutter)
                        .padding(bottom = design.spacing.cardGap / 2),
                    horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
                ) {
                    filterNames.forEachIndexed { index, name ->
                        AppChip(
                            label = name,
                            selected = selectedFilterIndex == index,
                            onClick = { selectedFilterIndex = index }
                        )
                    }
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
                            "Mostrando ${filteredQuestions.size} preguntas",
                            "Showing ${filteredQuestions.size} questions",
                            "Mostrando ${filteredQuestions.size} perguntas"
                        )
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
                    items(filteredQuestions) { question ->
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
            reference = question.cccReference.ifBlank { null }
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
    val host = links.firstNotNullOfOrNull { hostOf(it.catechismUrl) } ?: "vatican.va"

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Fuentes oficiales",
                    "Official sources",
                    "Fontes oficiais"
                ),
                // The host is the only part of the addresses worth reading;
                // each action already says where it goes.
                subtitle = currentLanguage.pick(
                    "Los textos íntegros de la Santa Sede en ${host}.",
                    "The Holy See's complete texts on ${host}.",
                    "Os textos integrais da Santa Sé em ${host}."
                )
            )
        }

        items(links) { link ->
            AppSectionHeader(title = link.language)
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    DocumentLink(
                        title = currentLanguage.pick(
                            "Catecismo de la Iglesia Católica",
                            "Catechism of the Catholic Church",
                            "Catecismo da Igreja Católica"
                        ),
                        detail = currentLanguage.pick(
                            "Texto completo · §§ 1–2865",
                            "Complete text · §§ 1–2865",
                            "Texto completo · §§ 1–2865"
                        ),
                        openLabel = currentLanguage.pick("Abrir", "Open", "Abrir"),
                        onOpen = { context.openExternally(link.catechismUrl) },
                        inAppLabel = currentLanguage.pick(
                            "Leer en la app",
                            "Read in the app",
                            "Ler no app"
                        ),
                        onOpenInApp = { webViewUrl = link.catechismUrl }
                    )

                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppDivider()
                    Spacer(Modifier.height(design.spacing.cardGap))

                    DocumentLink(
                        title = currentLanguage.pick(
                            "Compendio del Catecismo",
                            "Compendium of the Catechism",
                            "Compêndio do Catecismo"
                        ),
                        detail = currentLanguage.pick(
                            "598 preguntas y respuestas",
                            "598 questions and answers",
                            "598 perguntas e respostas"
                        ),
                        openLabel = currentLanguage.pick("Abrir", "Open", "Abrir"),
                        onOpen = { context.openExternally(link.compendiumUrl) },
                        inAppLabel = null,
                        onOpenInApp = null
                    )
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
    Text(text = detail, style = styles.rowSubtitle, color = design.palette.textTertiary)
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
                color = design.palette.accent,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clickable(onClick = onOpenInApp)
                    .padding(horizontal = 8.dp, vertical = 12.dp)
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
                color = design.palette.accent,
                maxLines = 1,
                modifier = Modifier.clickable { onValueChange("") }
            )
        }
    }
}

/** The siglum that opens an entry, and the reference it cites. */
@Composable
private fun EntryHeader(label: String, reference: String?) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Text(
            text = label,
            style = styles.sectionEyebrow,
            color = design.palette.accent
        )
        if (reference != null) {
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Text(
                text = reference,
                style = styles.sectionEyebrow,
                color = design.palette.gold,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
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
                    tint = design.palette.gold,
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
            textAlign = if (centred) TextAlign.Center else TextAlign.Start
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
 * The body of an article. The identity supplies the reading face and the
 * leading; the reader's A-/A+ and serif controls ride on top of it, so the
 * type scale and the reader's preference stay independent of one another.
 */
@Composable
private fun readingStyle(fontSizeMultiplier: Float, useSerif: Boolean): TextStyle {
    val styles = LocalAppTextStyles.current
    val base = styles.scripture
    return base.copy(
        fontSize = base.fontSize * fontSizeMultiplier,
        lineHeight = base.lineHeight * fontSizeMultiplier,
        // Sans falls back to the identity's own text face: Vatican is a serif
        // identity throughout and has no sans to switch to.
        fontFamily = if (useSerif) AppFonts.EbGaramond else styles.rowTitle.fontFamily
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
    return if (design.language == DesignLanguage.VATICAN) 0.dp else design.spacing.gutter
}

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

private fun Context.openExternally(url: String) {
    runCatching { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
}
