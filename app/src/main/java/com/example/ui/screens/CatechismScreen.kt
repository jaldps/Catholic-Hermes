package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.FilledTonalButton
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.data.repository.CompendiumQA
import com.example.data.repository.DoctrineFormula
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.AppLanguage
import com.example.data.models.CatechismChapter
import com.example.data.models.CatechismNumberedArticle
import com.example.data.models.CatechismPart
import com.example.data.repository.CatholicDataRepository
import com.example.ui.theme.LiturgicalGold
import com.example.ui.theme.SacredBurgundy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatechismScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    var mainMode by remember { mutableIntStateOf(0) } // 0 = Compendio (598), 1 = Catecismo Universal (1-2865), 2 = Fuentes Oficiales
    var selectedPartIndex by remember { mutableIntStateOf(0) } // 0..3 (Parts 1 to 4)
    var selectedChapter by remember { mutableStateOf<CatechismChapter?>(null) }
    var selectedArticleDetail by remember { mutableStateOf<CatechismNumberedArticle?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var fontSizeMultiplier by remember { mutableFloatStateOf(1f) }
    var useSerifFont by remember { mutableStateOf(true) }

    val parts = repository.catechismParts
    val currentPart = parts.getOrNull(selectedPartIndex) ?: parts.first()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("catechism_screen")
    ) {
        // Top Main Mode Tabs
        PrimaryTabRow(
            selectedTabIndex = mainMode,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = SacredBurgundy
        ) {
            Tab(
                selected = mainMode == 0,
                onClick = { mainMode = 0 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Compendio (598)"
                            AppLanguage.EN -> "Compendium (598)"
                            AppLanguage.PT -> "Compêndio (598)"
                        },
                        fontWeight = if (mainMode == 0) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = mainMode == 1,
                onClick = { mainMode = 1 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Catecismo (1-2865)"
                            AppLanguage.EN -> "Catechism (1-2865)"
                            AppLanguage.PT -> "Catecismo (1-2865)"
                        },
                        fontWeight = if (mainMode == 1) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = mainMode == 2,
                onClick = { mainMode = 2 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Fuentes Vaticano"
                            AppLanguage.EN -> "Vatican Links"
                            AppLanguage.PT -> "Fontes Vaticano"
                        },
                        fontWeight = if (mainMode == 2) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
        }

        when (mainMode) {
            0 -> {
                CompendiumView(
                    repository = repository,
                    currentLanguage = currentLanguage
                )
            }
            1 -> {
                // Universal Catechism View
                Column(modifier = Modifier.fillMaxSize()) {
                    // Search bar with CCC number quick search
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ccc_search_input"),
                placeholder = {
                    Text(
                        when (currentLanguage) {
                            AppLanguage.ES -> "Buscar por n° de art. (ej. 1324) o tema..."
                            AppLanguage.EN -> "Search by CCC # (e.g. 1324) or topic..."
                            AppLanguage.PT -> "Buscar por nº art. (ex. 1324) ou tema..."
                        },
                        fontSize = 13.sp
                    )
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = SacredBurgundy)
                },
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                },
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )
        }

        if (searchQuery.isNotBlank()) {
            // Search Results Mode
            val numQuery = searchQuery.trim().toIntOrNull()
            val searchResults = remember(searchQuery, currentLanguage) {
                if (numQuery != null) {
                    val found = repository.findCatechismArticle(numQuery)
                    if (found != null) listOf(found) else emptyList()
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Resultados encontrados (${searchResults.size}):",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = SacredBurgundy
                    )
                }

                if (searchResults.isEmpty()) {
                    item {
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "No se encontraron artículos con ese criterio de búsqueda.",
                                modifier = Modifier.padding(18.dp),
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    items(searchResults) { article ->
                        ArticleItemCard(
                            article = article,
                            currentLanguage = currentLanguage,
                            fontSizeMultiplier = fontSizeMultiplier,
                            useSerif = useSerifFont
                        )
                    }
                }
            }
        } else if (selectedChapter == null) {
            // 4 Pillars Tabs
            ScrollableTabRow(
                selectedTabIndex = selectedPartIndex,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = SacredBurgundy,
                edgePadding = 12.dp
            ) {
                parts.forEachIndexed { idx, p ->
                    Tab(
                        selected = selectedPartIndex == idx,
                        onClick = { selectedPartIndex = idx },
                        text = {
                            Text(
                                text = "Parte ${p.partNumber} (${p.cccRange})",
                                fontWeight = if (selectedPartIndex == idx) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        }
                    )
                }
            }

            // Part Overview & Hierarchical Chapter Tree
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    ElevatedCard(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = currentPart.title.get(currentLanguage),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = SacredBurgundy
                            )
                            Text(
                                text = currentPart.subtitle.get(currentLanguage) + " • " + currentPart.cccRange,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = LiturgicalGold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = currentPart.description.get(currentLanguage),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "Índice de Secciones y Capítulos:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = SacredBurgundy
                    )
                }

                currentPart.sections.forEach { section ->
                    item {
                        Text(
                            text = section.title.get(currentLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = LiturgicalGold,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    items(section.chapters) { chapter ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .clickable { selectedChapter = chapter },
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                    Box(
                                        modifier = Modifier
                                            .size(34.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.primaryContainer),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "§",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Text(
                                            text = chapter.title.get(currentLanguage),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "Artículos: ${chapter.articleRangeDisplay} (${chapter.articles.size} destacados)",
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Text("Leer →", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }
        } else {
            // Chapter Reading View with full articles
            val chapter = selectedChapter!!

            BackHandler {
                selectedChapter = null
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header with back button & font scaling
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { selectedChapter = null },
                        modifier = Modifier.testTag("back_to_catechism_index")
                    ) {
                        Text("← Índice del Catecismo", fontWeight = FontWeight.Bold)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { if (fontSizeMultiplier > 0.85f) fontSizeMultiplier -= 0.15f }) {
                            Text("A-", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                        IconButton(onClick = { if (fontSizeMultiplier < 1.45f) fontSizeMultiplier += 0.15f }) {
                            Text("A+", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                        IconButton(onClick = { useSerifFont = !useSerifFont }) {
                            Text(if (useSerifFont) "Serif" else "Sans", fontSize = 11.sp)
                        }
                    }
                }

                Text(
                    text = chapter.title.get(currentLanguage),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = SacredBurgundy
                )
                Text(
                    text = "Catecismo de la Iglesia Católica: ${chapter.articleRangeDisplay}",
                    fontSize = 11.sp,
                    color = LiturgicalGold
                )

                Spacer(modifier = Modifier.height(14.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(chapter.articles) { article ->
                        ArticleItemCard(
                            article = article,
                            currentLanguage = currentLanguage,
                            fontSizeMultiplier = fontSizeMultiplier,
                            useSerif = useSerifFont
                        )
                    }
                }
            }
        }
    }
}
            2 -> {
                VaticanOfficialLinksView(
                    repository = repository,
                    currentLanguage = currentLanguage
                )
            }
        }
    }
}

@Composable
private fun ArticleItemCard(
    article: CatechismNumberedArticle,
    currentLanguage: AppLanguage,
    fontSizeMultiplier: Float,
    useSerif: Boolean
) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SacredBurgundy)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "CIC § ${article.cccNumber}",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 11.sp
                    )
                }

                if (article.biblicalReferences != null) {
                    Text(
                        text = "Ref: ${article.biblicalReferences}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = LiturgicalGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.title.get(currentLanguage),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = SacredBurgundy
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.content.get(currentLanguage),
                fontFamily = if (useSerif) FontFamily.Serif else FontFamily.Default,
                fontSize = (14.5f * fontSizeMultiplier).sp,
                lineHeight = (23 * fontSizeMultiplier).sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (article.inBriefSummary != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = SacredBurgundy, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "En Resumen: " + article.inBriefSummary.get(currentLanguage),
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CompendiumView(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage
) {
    var selectedFilterIndex by remember { mutableIntStateOf(0) } // 0 = Todas (598), 1 = Parte I, 2 = Parte II, 3 = Parte III, 4 = Parte IV, 5 = Fórmulas Doctrina
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Search
        OutlinedTextField(
            value = compendiumSearch,
            onValueChange = { compendiumSearch = it },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("compendium_search_input"),
            placeholder = {
                Text(
                    when (currentLanguage) {
                        AppLanguage.ES -> "Buscar en las 598 preguntas (ej. 1, eucaristía)..."
                        AppLanguage.EN -> "Search across 598 questions (e.g. 1, eucharist)..."
                        AppLanguage.PT -> "Buscar nas 598 perguntas (ex. 1, eucaristia)..."
                    },
                    fontSize = 12.sp
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = SacredBurgundy)
            },
            trailingIcon = {
                if (compendiumSearch.isNotBlank()) {
                    IconButton(onClick = { compendiumSearch = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            },
            shape = RoundedCornerShape(14.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Part filter chips
        ScrollableTabRow(
            selectedTabIndex = selectedFilterIndex,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = SacredBurgundy,
            edgePadding = 4.dp
        ) {
            val filterNames = when (currentLanguage) {
                AppLanguage.ES -> listOf("Todas (598)", "P. I: La Fe", "P. II: Sacramentos", "P. III: Vida en Cristo", "P. IV: Oración", "Fórmulas Doctrinales")
                AppLanguage.EN -> listOf("All (598)", "P. I: The Faith", "P. II: Sacraments", "P. III: Life in Christ", "P. IV: Prayer", "Doctrinal Formulas")
                AppLanguage.PT -> listOf("Todas (598)", "P. I: A Fé", "P. II: Sacramentos", "P. III: Vida em Cristo", "P. IV: Oração", "Fórmulas Doutrinais")
            }
            filterNames.forEachIndexed { idx, name ->
                Tab(
                    selected = selectedFilterIndex == idx,
                    onClick = { selectedFilterIndex = idx },
                    text = {
                        Text(
                            text = name,
                            fontSize = 11.sp,
                            fontWeight = if (selectedFilterIndex == idx) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (selectedFilterIndex == 5) {
            // Doctrinal formulas
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Fórmulas Principales de la Doctrina Católica",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = SacredBurgundy
                    )
                }

                items(repository.doctrineFormulas) { item ->
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = item.title.get(currentLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = SacredBurgundy
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = item.items.joinToString("\n• ", prefix = "• ") { it.get(currentLanguage) },
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }
            }
        } else {
            // Questions list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Mostrando ${filteredQuestions.size} preguntas del Compendio Oficial:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                items(filteredQuestions) { q ->
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(SacredBurgundy)
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = "Pregunta ${q.number}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        color = Color.White
                                    )
                                }

                                Text(
                                    text = q.cccReference,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = LiturgicalGold
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = q.question.get(currentLanguage),
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = SacredBurgundy
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = q.answer.get(currentLanguage),
                                fontFamily = FontFamily.Serif,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 22.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun VaticanOfficialLinksView(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            ElevatedCard(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Fuentes Documentales Oficiales de la Santa Sede",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = SacredBurgundy
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Accede a los portales oficiales de la Santa Sede (vatican.va) con los textos completos del Catecismo de la Iglesia Católica y del Compendio en Español, Inglés y Portugués.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        items(repository.officialVaticanLinks) { item ->
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Vatican.va (${item.language})",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = SacredBurgundy,
                            modifier = Modifier.weight(1f)
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(LiturgicalGold.copy(alpha = 0.25f))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = item.language,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SacredBurgundy
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Catecismo de la Iglesia Católica (Texto Completo 1-2865)",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = item.catechismUrl,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    FilledTonalButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.catechismUrl))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Abrir Catecismo Oficial", fontSize = 11.sp)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Compendio del Catecismo (598 Preguntas y Respuestas)",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = item.compendiumUrl,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    FilledTonalButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.compendiumUrl))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Abrir Compendio Oficial", fontSize = 11.sp)
                    }
                }
            }
        }
    }
}
