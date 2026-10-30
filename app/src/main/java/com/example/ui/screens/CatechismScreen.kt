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
import com.example.ui.theme.AppFonts
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
    var searchQuery by remember { mutableStateOf("") }
    var fontSizeMultiplier by remember { mutableFloatStateOf(1f) }
    var useSerifFont by remember { mutableStateOf(true) }

    val modeLabels = listOf(
        currentLanguage.pick("Compendio (598)", "Compendium (598)", "Compêndio (598)"),
        currentLanguage.pick("Catecismo (1-2865)", "Catechism (1-2865)", "Catecismo (1-2865)"),
        currentLanguage.pick("Fuentes Vaticano", "Vatican Links", "Fontes Vaticano")
    )
    val tabsInset = segmentedTabsInset()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("catechism_screen")
    ) {
        AppSegmentedTabs(
            options = modeLabels,
            selectedIndex = mainMode,
            onSelect = { mainMode = it },
            modifier = Modifier.padding(
                start = tabsInset,
                end = tabsInset,
                top = design.spacing.cardGap / 2
            )
        )

        when (mainMode) {
            0 -> CompendiumView(
                repository = repository,
                currentLanguage = currentLanguage
            )

            1 -> UniversalCatechismView(
                repository = repository,
                currentLanguage = currentLanguage,
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                selectedPartIndex = selectedPartIndex,
                onSelectPart = { selectedPartIndex = it },
                selectedChapter = selectedChapter,
                onSelectChapter = { selectedChapter = it },
                fontSizeMultiplier = fontSizeMultiplier,
                onFontSizeChange = { fontSizeMultiplier = it },
                useSerifFont = useSerifFont,
                onToggleSerif = { useSerifFont = !useSerifFont }
            )

            else -> VaticanOfficialLinksView(
                repository = repository,
                currentLanguage = currentLanguage
            )
        }
    }
}

// ----------------------------------------------------------- the Catechism

@Composable
private fun UniversalCatechismView(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedPartIndex: Int,
    onSelectPart: (Int) -> Unit,
    selectedChapter: CatechismChapter?,
    onSelectChapter: (CatechismChapter?) -> Unit,
    fontSizeMultiplier: Float,
    onFontSizeChange: (Float) -> Unit,
    useSerifFont: Boolean,
    onToggleSerif: () -> Unit
) {
    val parts = repository.catechismParts
    val currentPart = parts.getOrNull(selectedPartIndex) ?: parts.first()
    val chapter = selectedChapter

    Column(Modifier.fillMaxSize()) {
        SearchRow(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = currentLanguage.pick(
                "Buscar por n° de art. (ej. 1324) o tema...",
                "Search by CCC # (e.g. 1324) or topic...",
                "Buscar por nº art. (ex. 1324) ou tema..."
            ),
            clearLabel = currentLanguage.pick("Limpiar", "Clear", "Limpar"),
            fieldModifier = Modifier.testTag("ccc_search_input")
        )

        when {
            searchQuery.isNotBlank() -> CatechismSearchResults(
                repository = repository,
                parts = parts,
                currentLanguage = currentLanguage,
                searchQuery = searchQuery,
                fontSizeMultiplier = fontSizeMultiplier,
                useSerifFont = useSerifFont
            )

            chapter == null -> CatechismIndex(
                parts = parts,
                currentPart = currentPart,
                selectedPartIndex = selectedPartIndex,
                currentLanguage = currentLanguage,
                onSelectPart = onSelectPart,
                onSelectChapter = { onSelectChapter(it) }
            )

            else -> {
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
}

/**
 * The table of contents. The four parts are a selector rather than a further
 * level of navigation — the part in view is rubricated in the group, and its
 * own sections and chapters follow underneath.
 */
@Composable
private fun CatechismIndex(
    parts: List<CatechismPart>,
    currentPart: CatechismPart,
    selectedPartIndex: Int,
    currentLanguage: AppLanguage,
    onSelectPart: (Int) -> Unit,
    onSelectChapter: (CatechismChapter) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    LazyColumn(Modifier.fillMaxSize()) {
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

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
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
@Composable
private fun CatechismSearchResults(
    repository: CatholicDataRepository,
    parts: List<CatechismPart>,
    currentLanguage: AppLanguage,
    searchQuery: String,
    fontSizeMultiplier: Float,
    useSerifFont: Boolean
) {
    val design = LocalAppDesign.current

    val results = remember(searchQuery, currentLanguage) {
        val numQuery = searchQuery.trim().toIntOrNull()
        if (numQuery != null) {
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

    LazyColumn(Modifier.fillMaxSize()) {
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

    Column(Modifier.fillMaxSize()) {
        SearchRow(
            value = compendiumSearch,
            onValueChange = { compendiumSearch = it },
            placeholder = currentLanguage.pick(
                "Buscar en las 598 preguntas (ej. 1, eucaristía)...",
                "Search across 598 questions (e.g. 1, eucharist)...",
                "Buscar nas 598 perguntas (ex. 1, eucaristia)..."
            ),
            clearLabel = currentLanguage.pick("Limpiar", "Clear", "Limpar"),
            fieldModifier = Modifier.testTag("compendium_search_input")
        )

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

        AppDivider()

        LazyColumn(Modifier.fillMaxSize()) {
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
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    var webViewUrl by remember { mutableStateOf<String?>(null) }

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            AppScreenTitle(
                title = currentLanguage.pick(
                    "Fuentes Oficiales",
                    "Official Sources",
                    "Fontes Oficiais"
                ),
                subtitle = currentLanguage.pick(
                    "Fuentes Documentales Oficiales de la Santa Sede",
                    "Official documentary sources of the Holy See",
                    "Fontes Documentais Oficiais da Santa Sé"
                )
            )
        }

        item {
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        text = currentLanguage.pick(
                            "Accede a los portales oficiales de la Santa Sede (vatican.va) con los textos completos del Catecismo de la Iglesia Católica y del Compendio en Español, Inglés y Portugués.",
                            "Reach the Holy See's official portals (vatican.va) for the complete texts of the Catechism of the Catholic Church and of the Compendium in Spanish, English and Portuguese.",
                            "Acesse os portais oficiais da Santa Sé (vatican.va) com os textos completos do Catecismo da Igreja Católica e do Compêndio em Espanhol, Inglês e Português."
                        ),
                        style = styles.scripture,
                        color = design.palette.textSecondary
                    )
                }
            }
        }

        items(repository.officialVaticanLinks) { link ->
            AppSectionHeader(title = "Vatican.va (${link.language})")
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    DocumentLink(
                        title = currentLanguage.pick(
                            "Catecismo de la Iglesia Católica (Texto Completo 1-2865)",
                            "Catechism of the Catholic Church (complete text 1-2865)",
                            "Catecismo da Igreja Católica (Texto Completo 1-2865)"
                        ),
                        url = link.catechismUrl,
                        openLabel = currentLanguage.pick(
                            "Abrir Catecismo Oficial",
                            "Open official Catechism",
                            "Abrir Catecismo Oficial"
                        ),
                        onOpen = { context.openExternally(link.catechismUrl) },
                        inAppLabel = currentLanguage.pick(
                            "Ver en la app",
                            "Read in the app",
                            "Ver no app"
                        ),
                        onOpenInApp = { webViewUrl = link.catechismUrl }
                    )

                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppDivider()
                    Spacer(Modifier.height(design.spacing.cardGap))

                    DocumentLink(
                        title = currentLanguage.pick(
                            "Compendio del Catecismo (598 Preguntas y Respuestas)",
                            "Compendium of the Catechism (598 questions and answers)",
                            "Compêndio do Catecismo (598 Perguntas e Respostas)"
                        ),
                        url = link.compendiumUrl,
                        openLabel = currentLanguage.pick(
                            "Abrir Compendio Oficial",
                            "Open official Compendium",
                            "Abrir Compêndio Oficial"
                        ),
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

@Composable
private fun DocumentLink(
    title: String,
    url: String,
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
    Text(text = url, style = styles.rowSubtitle, color = design.palette.textTertiary)
    Spacer(Modifier.height(design.spacing.cardGap))
    AppSecondaryButton(
        label = openLabel,
        onClick = onOpen,
        leadingIcon = Icons.Default.OpenInNew,
        modifier = Modifier.fillMaxWidth()
    )
    if (inAppLabel != null && onOpenInApp != null) {
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        AppSecondaryButton(
            label = inAppLabel,
            onClick = onOpenInApp,
            leadingIcon = Icons.Default.MenuBook,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

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
