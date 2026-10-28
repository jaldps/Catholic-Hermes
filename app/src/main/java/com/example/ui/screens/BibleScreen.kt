package com.example.ui.screens

import com.example.ui.components.IdentityTabBar
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.data.models.BibleBook
import com.example.data.models.BibleVerse
import com.example.data.models.BibleVersionInfo
import com.example.data.models.DailyMassReading
import com.example.data.models.Testament
import com.example.data.repository.CatholicDataRepository
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDao
import kotlinx.coroutines.launch
import com.example.ui.components.InAppBrowserDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    dao: SanctaFidesDao? = null,
    ttsManager: LiturgicalTtsManager? = null,
    modifier: Modifier = Modifier
) {
    var selectedMainTab by remember { mutableIntStateOf(0) } // 0 = Bible Canon & Reader, 1 = Daily Mass Missal, 2 = Versions Comparator, 3 = Scripture Study
    var selectedTestamentTab by remember { mutableIntStateOf(0) } // 0 = All 73 books, 1 = OT (46), 2 = NT (27), 3 = Deuterocanonical (7)
    var selectedBook by remember { mutableStateOf<BibleBook?>(null) }
    var selectedChapterNumber by remember(selectedBook?.id) { mutableIntStateOf(1) }
    var showChapterSelectorDialog by remember { mutableStateOf(false) }
    var selectedVerseForCommentary by remember { mutableStateOf<BibleVerse?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var fontSizeMultiplier by remember { mutableFloatStateOf(1f) }
    var useSerifFont by remember { mutableStateOf(true) }

    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("bible_screen")
    ) {
        IdentityTabBar(
            labels = listOf(
                when (currentLanguage) { AppLanguage.ES -> "Biblia (73)"; AppLanguage.EN -> "Bible (73)"; AppLanguage.PT -> "Bíblia (73)" },
                when (currentLanguage) { AppLanguage.ES -> "Misal Diario"; AppLanguage.EN -> "Daily Missal"; AppLanguage.PT -> "Missal Diário" },
                when (currentLanguage) { AppLanguage.ES -> "Comparador"; AppLanguage.EN -> "Versions"; AppLanguage.PT -> "Versões" },
                when (currentLanguage) { AppLanguage.ES -> "Estudio Bíblico"; AppLanguage.EN -> "Scripture Study"; AppLanguage.PT -> "Estudo Bíblico" }
            ),
            selectedIndex = selectedMainTab,
            onTabSelected = { selectedMainTab = it },
            modifier = Modifier.testTag("bible_mode_tabs"),
            testTags = listOf("bible_tab_canon", "bible_tab_missal", "bible_tab_versions", "bible_tab_study")
        )

        when (selectedMainTab) {
            0 -> {
                // Bible Reader View
                if (selectedBook == null) {
                    // Books Directory
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                    ) {
                        // Search bar
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("bible_search_input"),
                            placeholder = {
                                Text(
                                    when (currentLanguage) {
                                        AppLanguage.ES -> "Buscar libro bíblico..."
                                        AppLanguage.EN -> "Search biblical book..."
                                        AppLanguage.PT -> "Buscar livro bíblico..."
                                    }
                                )
                            },
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Search, contentDescription = null)
                            },
                            singleLine = true,
                            shape = MaterialTheme.shapes.medium
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Filter Subtabs: All (73), OT (46), NT (27), Deuterocanonical (7)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            FilterChip(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "Todos (73)"
                                    AppLanguage.EN -> "All (73)"
                                    AppLanguage.PT -> "Todos (73)"
                                },
                                isSelected = selectedTestamentTab == 0,
                                onClick = { selectedTestamentTab = 0 }
                            )
                            FilterChip(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "A.T. (46)"
                                    AppLanguage.EN -> "O.T. (46)"
                                    AppLanguage.PT -> "A.T. (46)"
                                },
                                isSelected = selectedTestamentTab == 1,
                                onClick = { selectedTestamentTab = 1 }
                            )
                            FilterChip(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "N.T. (27)"
                                    AppLanguage.EN -> "N.T. (27)"
                                    AppLanguage.PT -> "N.T. (27)"
                                },
                                isSelected = selectedTestamentTab == 2,
                                onClick = { selectedTestamentTab = 2 }
                            )
                            FilterChip(
                                label = when (currentLanguage) {
                                    AppLanguage.ES -> "Deuterocanónicos"
                                    AppLanguage.EN -> "Deuterocanon"
                                    AppLanguage.PT -> "Deuterocanônicos"
                                },
                                isSelected = selectedTestamentTab == 3,
                                onClick = { selectedTestamentTab = 3 }
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val filteredBooks = repository.catholicBooks.filter { book ->
                            val matchesSearch = book.name.get(currentLanguage).contains(searchQuery, ignoreCase = true) ||
                                    book.abbreviation.contains(searchQuery, ignoreCase = true)
                            val matchesTab = when (selectedTestamentTab) {
                                1 -> book.testament == Testament.OLD
                                2 -> book.testament == Testament.NEW
                                3 -> book.isDeuterocanonical
                                else -> true
                            }
                            matchesSearch && matchesTab
                        }

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(filteredBooks) { book ->
                                BookListItem(
                                    book = book,
                                    currentLanguage = currentLanguage,
                                    onClick = { selectedBook = book }
                                )
                            }
                        }
                    }
                } else {
                    // Open Book Reading View
                    val currentBook = selectedBook!!
                    val chapter = repository.getChapter(currentBook.id, selectedChapterNumber)

                    BackHandler {
                        selectedBook = null
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Header with back button, chapter chooser & font controls
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = { selectedBook = null },
                                modifier = Modifier.testTag("back_to_books_button")
                            ) {
                                Text("← " + currentBook.name.get(currentLanguage), fontWeight = FontWeight.Bold)
                            }

                            FilledTonalButton(
                                onClick = { showChapterSelectorDialog = true },
                                modifier = Modifier.testTag("choose_chapter_header_button")
                            ) {
                                Text(
                                    text = when (currentLanguage) {
                                        AppLanguage.ES -> "Cap. $selectedChapterNumber / ${currentBook.chaptersCount} ▼"
                                        AppLanguage.EN -> "Ch. $selectedChapterNumber / ${currentBook.chaptersCount} ▼"
                                        AppLanguage.PT -> "Cap. $selectedChapterNumber / ${currentBook.chaptersCount} ▼"
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    if (fontSizeMultiplier > 0.85f) fontSizeMultiplier -= 0.15f
                                }) {
                                    Text("A-", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                                IconButton(onClick = {
                                    if (fontSizeMultiplier < 1.45f) fontSizeMultiplier += 0.15f
                                }) {
                                    Text("A+", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                }
                                IconButton(onClick = { useSerifFont = !useSerifFont }) {
                                    Text(if (useSerifFont) "Serif" else "Sans", fontSize = 11.sp)
                                }
                            }
                        }

                        // Deuterocanonical explanation tag if applicable
                        if (currentBook.isDeuterocanonical) {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                shape = MaterialTheme.shapes.small,
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
                                border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Info, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = when (currentLanguage) {
                                            AppLanguage.ES -> "Libro Deuterocanónico: Conservado en el canon católico desde los Concilios de Roma (382) e Hipona (393)."
                                            AppLanguage.EN -> "Deuterocanonical Book: Preserved in the Catholic Canon since the Councils of Rome (382) & Hippo (393)."
                                            AppLanguage.PT -> "Livro Deuterocanônico: Conservado no cânon católico desde os Concílios de Roma (382) e Hipona (393)."
                                        },
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Verses List with Tap to Commentary
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            item {
                                Text(
                                    text = "${currentBook.name.get(currentLanguage)} - ${when (currentLanguage) {
                                        AppLanguage.ES -> "Capítulo $selectedChapterNumber"
                                        AppLanguage.EN -> "Chapter $selectedChapterNumber"
                                        AppLanguage.PT -> "Capítulo $selectedChapterNumber"
                                    }}",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = currentBook.summary.get(currentLanguage),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }

                            items(chapter.verses) { verse ->
                                VerseItem(
                                    verse = verse,
                                    currentLanguage = currentLanguage,
                                    fontSizeMultiplier = fontSizeMultiplier,
                                    useSerif = useSerifFont,
                                    onCommentaryClick = {
                                        selectedVerseForCommentary = verse
                                    }
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }

                        // Bottom Chapter Navigation Bar
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FilledTonalButton(
                                enabled = selectedChapterNumber > 1,
                                onClick = { selectedChapterNumber-- },
                                modifier = Modifier.testTag("prev_chapter_button")
                            ) {
                                Text(
                                    text = when (currentLanguage) {
                                        AppLanguage.ES -> "← Cap. ${selectedChapterNumber - 1}"
                                        AppLanguage.EN -> "← Ch. ${selectedChapterNumber - 1}"
                                        AppLanguage.PT -> "← Cap. ${selectedChapterNumber - 1}"
                                    },
                                    fontSize = 12.sp
                                )
                            }

                            FilledTonalButton(
                                onClick = { showChapterSelectorDialog = true }
                            ) {
                                Text(
                                    text = "$selectedChapterNumber / ${currentBook.chaptersCount}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }

                            FilledTonalButton(
                                enabled = selectedChapterNumber < currentBook.chaptersCount,
                                onClick = { selectedChapterNumber++ },
                                modifier = Modifier.testTag("next_chapter_button")
                            ) {
                                Text(
                                    text = when (currentLanguage) {
                                        AppLanguage.ES -> "Cap. ${selectedChapterNumber + 1} →"
                                        AppLanguage.EN -> "Ch. ${selectedChapterNumber + 1} →"
                                        AppLanguage.PT -> "Cap. ${selectedChapterNumber + 1} →"
                                    },
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                    // Chapter Selection Dialog
                    if (showChapterSelectorDialog) {
                        AlertDialog(
                            onDismissRequest = { showChapterSelectorDialog = false },
                            title = {
                                Text(
                                    text = "${currentBook.name.get(currentLanguage)} - ${when (currentLanguage) {
                                        AppLanguage.ES -> "Seleccionar Capítulo"
                                        AppLanguage.EN -> "Select Chapter"
                                        AppLanguage.PT -> "Selecionar Capítulo"
                                    }}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            },
                            text = {
                                Box(modifier = Modifier.size(width = 300.dp, height = 340.dp)) {
                                    LazyVerticalGrid(
                                        columns = GridCells.Fixed(5),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        items((1..currentBook.chaptersCount).toList()) { chNum ->
                                            val isCurrent = chNum == selectedChapterNumber
                                            Box(
                                                modifier = Modifier
                                                    .size(44.dp)
                                                    .clip(MaterialTheme.shapes.small)
                                                    .background(if (isCurrent) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
                                                    .clickable {
                                                        selectedChapterNumber = chNum
                                                        showChapterSelectorDialog = false
                                                    },
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = chNum.toString(),
                                                    color = if (isCurrent) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                                                    fontSize = 13.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = { showChapterSelectorDialog = false }) {
                                    Text(
                                        when (currentLanguage) {
                                            AppLanguage.ES -> "Cerrar"
                                            AppLanguage.EN -> "Close"
                                            AppLanguage.PT -> "Fechar"
                                        }
                                    )
                                }
                            }
                        )
                    }
                }
            }

            1 -> {
                // Daily Mass Readings Full View
                DailyMassMissalView(
                    dailyReading = repository.dailyMassReading,
                    currentLanguage = currentLanguage
                )
            }

            2 -> {
                // Bible Versions & Canon Comparator View
                Column(modifier = Modifier.fillMaxSize()) {
                    BibleVersionsComparatorView(
                        versions = repository.bibleVersions,
                        currentLanguage = currentLanguage
                    )
                    // Offline downloads section
                    repository.bibleDownloadManager?.let { mgr ->
                        OfflineBibleDownloadsView(manager = mgr, currentLanguage = currentLanguage)
                    }
                }
            }

            3 -> {
                if (dao != null && ttsManager != null) {
                    ScriptureStudyScreen(
                        repository = repository,
                        dao = dao,
                        ttsManager = ttsManager,
                        currentLanguage = currentLanguage
                    )
                }
            }
        }

        // Patristic Commentary Bottom Sheet
        if (selectedVerseForCommentary != null) {
            val v = selectedVerseForCommentary!!
            ModalBottomSheet(
                onDismissRequest = { selectedVerseForCommentary = null },
                sheetState = bottomSheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Comment, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Comentario Patrístico y Litúrgico"
                                AppLanguage.EN -> "Patristic & Liturgical Commentary"
                                AppLanguage.PT -> "Comentário Patrístico e Litúrgico"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "«${v.text.get(currentLanguage)}» (v. ${v.number})",
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (v.commentary != null) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                            border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = v.commentaryAuthor ?: "Padres de la Iglesia",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = v.commentary.get(currentLanguage),
                                    style = MaterialTheme.typography.bodyMedium,
                                    lineHeight = 22.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))
                }
            }
        }
    }
}

@Composable
private fun FilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
            .border(
                1.dp,
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun BookListItem(
    book: BibleBook,
    currentLanguage: AppLanguage,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("book_item_${book.id}")
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
        border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(if (book.isDeuterocanonical) MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = book.abbreviation,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = if (book.isDeuterocanonical) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = book.name.get(currentLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (book.isDeuterocanonical) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.extraSmall)
                                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("Católico", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                    Text(
                        text = "${book.chaptersCount} capítulos • " + book.category.displayName.get(currentLanguage),
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Text("Leer →", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun VerseItem(
    verse: BibleVerse,
    currentLanguage: AppLanguage,
    fontSizeMultiplier: Float,
    useSerif: Boolean,
    onCommentaryClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onCommentaryClick),
        color = MaterialTheme.colorScheme.surface,
        border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "v. ${verse.number}",
                    fontWeight = FontWeight.Bold,
                    fontSize = (12 * fontSizeMultiplier).sp,
                    color = MaterialTheme.colorScheme.secondary
                )

                if (verse.commentary != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Comment, contentDescription = null, modifier = Modifier.size(12.dp), tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = verse.commentaryAuthor ?: "Comentario",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = verse.text.get(currentLanguage),
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                fontSize = (15 * fontSizeMultiplier).sp,
                lineHeight = (24 * fontSizeMultiplier).sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun DailyMassMissalView(
    dailyReading: DailyMassReading,
    currentLanguage: AppLanguage
) {
    var webViewUrl by remember { mutableStateOf<String?>(null) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = dailyReading.feastTitle.get(currentLanguage),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = dailyReading.liturgicalSeason.get(currentLanguage) + " • " + dailyReading.dateDisplay,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // First Reading
        item {
            ReadingBlock(
                title = when (currentLanguage) {
                    AppLanguage.ES -> "Primera Lectura"
                    AppLanguage.EN -> "First Reading"
                    AppLanguage.PT -> "Primeira Leitura"
                },
                citation = dailyReading.firstReadingRef,
                content = dailyReading.firstReadingText.get(currentLanguage)
            )
        }

        // Responsorial Psalm
        item {
            ReadingBlock(
                title = when (currentLanguage) {
                    AppLanguage.ES -> "Salmo Responsorial"
                    AppLanguage.EN -> "Responsorial Psalm"
                    AppLanguage.PT -> "Salmo Responsorial"
                },
                citation = dailyReading.responsorialPsalmRef,
                content = dailyReading.responsorialPsalmText.get(currentLanguage)
            )
        }

        // Second Reading
        if (dailyReading.secondReadingRef != null && dailyReading.secondReadingText != null) {
            item {
                ReadingBlock(
                    title = when (currentLanguage) {
                        AppLanguage.ES -> "Segunda Lectura"
                        AppLanguage.EN -> "Second Reading"
                        AppLanguage.PT -> "Segunda Leitura"
                    },
                    citation = dailyReading.secondReadingRef,
                    content = dailyReading.secondReadingText.get(currentLanguage)
                )
            }
        }

        // Holy Gospel
        item {
            ReadingBlock(
                title = when (currentLanguage) {
                    AppLanguage.ES -> "Santo Evangelio"
                    AppLanguage.EN -> "Holy Gospel"
                    AppLanguage.PT -> "Santo Evangelho"
                },
                citation = dailyReading.gospelRef,
                content = dailyReading.gospelText.get(currentLanguage),
                isGospel = true
            )
        }

        // Official source attribution (Vatican News)
        item {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Fuente oficial: ${dailyReading.sourceName}"
                            AppLanguage.EN -> "Official source: ${dailyReading.sourceName}"
                            AppLanguage.PT -> "Fonte oficial: ${dailyReading.sourceName}"
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = dailyReading.sourceUrl,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            FilledTonalButton(
                onClick = { webViewUrl = dailyReading.sourceUrl },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "Ver misal oficial en la app"
                        AppLanguage.EN -> "View official missal in app"
                        AppLanguage.PT -> "Ver missal oficial no app"
                    },
                    fontSize = 12.sp
                )
            }
        }

        // Reflection
        item {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Reflexión Homilética"
                            AppLanguage.EN -> "Homiletic Reflection"
                            AppLanguage.PT -> "Reflexão Homilética"
                        },
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = dailyReading.homilyReflection.get(currentLanguage),
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp
                    )
                }
            }
        }
    }

    webViewUrl?.let { url ->
        InAppBrowserDialog(url = url, onDismiss = { webViewUrl = null })
    }
}

@Composable
private fun ReadingBlock(
    title: String,
    citation: String,
    content: String,
    isGospel: Boolean = false
) {
    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (isGospel) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = if (isGospel) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = citation,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = content,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
private fun BibleVersionsComparatorView(
    versions: List<BibleVersionInfo>,
    currentLanguage: AppLanguage
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = when (currentLanguage) {
                    AppLanguage.ES -> "Comparador de Versiones y Cánones Bíblicos"
                    AppLanguage.EN -> "Bible Versions & Canon Comparator"
                    AppLanguage.PT -> "Comparador de Versões e Cânones Bíblicos"
                },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = when (currentLanguage) {
                    AppLanguage.ES -> "Conoce las diferencias esenciales entre las biblias con el canon apostólico católico íntegro (73 libros) y las versiones protestantes (66 libros)."
                    AppLanguage.EN -> "Learn the doctrinal and textual differences between the authentic 73-book Catholic Canon and 66-book Protestant translations."
                    AppLanguage.PT -> "Conheça a diferença entre as Bíblias católicas integrais (73 livros) e as versões protestantes (66 livros)."
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        items(versions) { ver ->
            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = if (ver.isCatholicCanon) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.15f)
                ),
                border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = ver.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Box(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.small)
                                .background(if (ver.isCatholicCanon) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.errorContainer)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = if (ver.isCatholicCanon) "CANON CATÓLICO (73)" else "NO CATÓLICA (66)",
                                color = if (ver.isCatholicCanon) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onErrorContainer,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = ver.description.get(currentLanguage),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = ver.canonicalDifferenceNote.get(currentLanguage),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (ver.isCatholicCanon) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun OfflineBibleDownloadsView(
    manager: com.example.data.repository.BibleDownloadManager,
    currentLanguage: com.example.data.models.AppLanguage
) {
    val scope = androidx.compose.runtime.rememberCoroutineScope()
    androidx.compose.foundation.layout.Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        androidx.compose.material3.Text(
            text = when (currentLanguage) {
                com.example.data.models.AppLanguage.ES -> "Descargas offline de Biblias"
                com.example.data.models.AppLanguage.EN -> "Offline Bible downloads"
                com.example.data.models.AppLanguage.PT -> "Downloads offline da Bíblia"
            },
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
        manager.specs.forEach { spec ->
            var downloading by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
            var progress by androidx.compose.runtime.remember { androidx.compose.runtime.mutableFloatStateOf(0f) }
            var status by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
            val downloaded = manager.isDownloaded(spec.id)
            androidx.compose.material3.Card(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                androidx.compose.foundation.layout.Column(modifier = androidx.compose.ui.Modifier.padding(12.dp)) {
                    androidx.compose.material3.Text(text = spec.name, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                    androidx.compose.material3.Text(
                        text = if (downloaded) {
                            when (currentLanguage) {
                                com.example.data.models.AppLanguage.ES -> "Descargado (offline disponible)"
                                com.example.data.models.AppLanguage.EN -> "Downloaded (available offline)"
                                com.example.data.models.AppLanguage.PT -> "Baixado (disponível offline)"
                            }
                        } else status.ifBlank {
                            when (currentLanguage) {
                                com.example.data.models.AppLanguage.ES -> "No descargado"
                                com.example.data.models.AppLanguage.EN -> "Not downloaded"
                                com.example.data.models.AppLanguage.PT -> "Não baixado"
                            }
                        },
                        fontSize = 12.sp
                    )
                    if (downloading) {
                        androidx.compose.material3.LinearProgressIndicator(
                            progress = { progress },
                            modifier = androidx.compose.ui.Modifier.fillMaxWidth().padding(vertical = 4.dp)
                        )
                    }
                    androidx.compose.material3.FilledTonalButton(
                        onClick = {
                            if (!downloading && !downloaded) {
                                downloading = true
                                scope.launch {
                                    val ok = manager.download(spec.id) { p, msg ->
                                        progress = p
                                        status = msg
                                    }
                                    downloading = false
                                    status = if (ok) {
                                        when (currentLanguage) {
                                            com.example.data.models.AppLanguage.ES -> "Completado"
                                            com.example.data.models.AppLanguage.EN -> "Completed"
                                            com.example.data.models.AppLanguage.PT -> "Concluído"
                                        }
                                    } else status
                                }
                            }
                        },
                        enabled = !downloading && !downloaded
                    ) {
                        androidx.compose.material3.Text(
                            text = when (currentLanguage) {
                                com.example.data.models.AppLanguage.ES -> if (downloaded) "Descargado" else "Descargar"
                                com.example.data.models.AppLanguage.EN -> if (downloaded) "Downloaded" else "Download"
                                com.example.data.models.AppLanguage.PT -> if (downloaded) "Baixado" else "Baixar"
                            }
                        )
                    }
                }
            }
        }
    }
}
