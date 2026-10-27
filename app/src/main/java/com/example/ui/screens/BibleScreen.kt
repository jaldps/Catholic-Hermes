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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.collectAsState
import com.example.data.repository.BibleDownloadManager
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
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
import com.example.ui.theme.LiturgicalGold
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDao
import kotlinx.coroutines.launch
import com.example.ui.theme.SacredBurgundy

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
        // Main Screen Mode Tabs
        PrimaryTabRow(
            selectedTabIndex = selectedMainTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = SacredBurgundy
        ) {
            Tab(
                selected = selectedMainTab == 0,
                onClick = { selectedMainTab = 0 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Biblia (73)"
                            AppLanguage.EN -> "Bible (73)"
                            AppLanguage.PT -> "Bíblia (73)"
                        },
                        fontWeight = if (selectedMainTab == 0) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = selectedMainTab == 1,
                onClick = { selectedMainTab = 1 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Misal Diario"
                            AppLanguage.EN -> "Daily Missal"
                            AppLanguage.PT -> "Missal Diário"
                        },
                        fontWeight = if (selectedMainTab == 1) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = selectedMainTab == 2,
                onClick = { selectedMainTab = 2 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Comparador"
                            AppLanguage.EN -> "Versions"
                            AppLanguage.PT -> "Versões"
                        },
                        fontWeight = if (selectedMainTab == 2) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = selectedMainTab == 3,
                onClick = { selectedMainTab = 3 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Estudio Bíblico"
                            AppLanguage.EN -> "Scripture Study"
                            AppLanguage.PT -> "Estudo Bíblico"
                        },
                        fontWeight = if (selectedMainTab == 3) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
        }

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
                            shape = RoundedCornerShape(14.dp)
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
                                shape = RoundedCornerShape(10.dp),
                                color = LiturgicalGold.copy(alpha = 0.12f),
                                border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Info, contentDescription = null, tint = LiturgicalGold, modifier = Modifier.size(18.dp))
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
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .background(if (isCurrent) SacredBurgundy else MaterialTheme.colorScheme.surfaceVariant)
                                                    .clickable {
                                                        selectedChapterNumber = chNum
                                                        showChapterSelectorDialog = false
                                                    },
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = chNum.toString(),
                                                    color = if (isCurrent) Color.White else MaterialTheme.colorScheme.onSurface,
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
                BibleVersionsComparatorView(
                    versions = repository.bibleVersions,
                    repository = repository,
                    dao = dao,
                    currentLanguage = currentLanguage
                )
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
                        Icon(imageVector = Icons.Default.Comment, contentDescription = null, tint = SacredBurgundy)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Comentario Patrístico y Litúrgico"
                                AppLanguage.EN -> "Patristic & Liturgical Commentary"
                                AppLanguage.PT -> "Comentário Patrístico e Litúrgico"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "«${v.text.get(currentLanguage)}» (v. ${v.number})",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (v.commentary != null) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                            border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = v.commentaryAuthor ?: "Padres de la Iglesia",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = LiturgicalGold
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
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.surface)
            .border(
                1.dp,
                if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
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
            .clip(RoundedCornerShape(14.dp))
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
                        .background(if (book.isDeuterocanonical) LiturgicalGold.copy(alpha = 0.2f) else MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = book.abbreviation,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = if (book.isDeuterocanonical) LiturgicalGold else MaterialTheme.colorScheme.onPrimaryContainer
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
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(LiturgicalGold.copy(alpha = 0.2f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("Católico", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SacredBurgundy)
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
            .clip(RoundedCornerShape(12.dp))
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
                    color = LiturgicalGold
                )

                if (verse.commentary != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Comment, contentDescription = null, modifier = Modifier.size(12.dp), tint = SacredBurgundy)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = verse.commentaryAuthor ?: "Comentario",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = verse.text.get(currentLanguage),
                fontFamily = if (useSerif) FontFamily.Serif else FontFamily.Default,
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ElevatedCard(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = dailyReading.feastTitle.get(currentLanguage),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = SacredBurgundy
                    )
                    Text(
                        text = dailyReading.liturgicalSeason.get(currentLanguage) + " • " + dailyReading.dateDisplay,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Official Vatican News Verification Banner
        item {
            val context = LocalContext.current
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Fuente Oficial: Vatican News",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = SacredBurgundy
                        )
                        Text(
                            text = "vaticannews.va/es/evangelio-de-hoy.html",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    FilledTonalButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vaticannews.va/es/evangelio-de-hoy.html"))
                            context.startActivity(intent)
                        }
                    ) {
                        Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Ver en Web", fontSize = 11.sp)
                    }
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

        // Reflection
        item {
            Surface(
                shape = RoundedCornerShape(14.dp),
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
                        color = LiturgicalGold,
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
}

@Composable
private fun ReadingBlock(
    title: String,
    citation: String,
    content: String,
    isGospel: Boolean = false
) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
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
                    color = if (isGospel) SacredBurgundy else MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = citation,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = LiturgicalGold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = content,
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
private fun BibleVersionsComparatorView(
    versions: List<BibleVersionInfo>,
    repository: CatholicDataRepository,
    dao: SanctaFidesDao?,
    currentLanguage: AppLanguage
) {
    var subTab by remember { mutableIntStateOf(0) } // 0 = ¿Sabías qué...? (Canon 73 vs 66), 1 = Descargas Offline, 2 = Comparador Traducciones
    val downloadManager = remember(dao) { dao?.let { BibleDownloadManager(it) } }
    val downloads by (downloadManager?.getAllDownloads()?.collectAsState(initial = emptyList()) ?: remember { mutableStateOf(emptyList()) })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sub-tabs row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val tabs = when (currentLanguage) {
                AppLanguage.ES -> listOf("¿Sabías qué...? (73)", "Descargas Offline", "Comparador")
                AppLanguage.EN -> listOf("Did You Know? (73)", "Offline Downloads", "Comparator")
                AppLanguage.PT -> listOf("Você Sabia? (73)", "Downloads Offline", "Comparador")
            }
            tabs.forEachIndexed { idx, name ->
                val isSelected = subTab == idx
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) SacredBurgundy else MaterialTheme.colorScheme.surfaceVariant)
                        .clickable { subTab = idx }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        when (subTab) {
            0 -> {
                // ¿Sabías qué...? Section
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        ElevatedCard(
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Text(
                                    text = when (currentLanguage) {
                                        AppLanguage.ES -> "¿Por qué la Biblia Católica tiene 73 libros y la protestante 66?"
                                        AppLanguage.EN -> "Why Does the Catholic Bible Have 73 Books and the Protestant 66?"
                                        AppLanguage.PT -> "Por que a Bíblia Católica tem 73 livros e a protestante 66?"
                                    },
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = SacredBurgundy
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = when (currentLanguage) {
                                        AppLanguage.ES -> "La Iglesia Primitiva y los Apóstoles usaron la versión griega de los Setenta (Septuaginta o LXX, Alejandría c. 250 a.C.), que contenía los 46 libros del Antiguo Testamento. En el siglo XVI, Martín Lutero y los reformadores protestantes decidieron retirar 7 libros (llamados deuterocanónicos) y fragmentos de Ester y Daniel, adoptando el canon hebreo tardío fijado por rabinos en Jamnia (c. 90 d.C.) tras la destrucción de Jerusalén."
                                        AppLanguage.EN -> "The Early Church and the Apostles used the Greek Septuagint (LXX, Alexandria c. 250 BC), which contained all 46 Old Testament books. In the 16th century, Martin Luther and Protestant reformers removed 7 books (deuterocanonicals) and parts of Esther and Daniel, adopting the late rabbinic Jewish canon established in Jamnia (c. 90 AD) after the destruction of the Temple."
                                        AppLanguage.PT -> "A Igreja Primitiva e os Apóstolos utilizaram a tradução grega dos Setenta (Septuaginta ou LXX, Alexandria c. 250 a.C.), que continha 46 livros do Antigo Testamento. No século XVI, os reformadores protestantes removeram 7 livros (deuterocanônicos) e partes de Ester e Daniel, adotando o cânon hebraico tardio fixado em Jâmnia (c. 90 d.C.)."
                                    },
                                    style = MaterialTheme.typography.bodyMedium,
                                    lineHeight = 22.sp
                                )
                            }
                        }
                    }

                    // Historical Milestones
                    item {
                        Text(
                            text = "Hitos Históricos y Concilios de la Iglesia:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = SacredBurgundy
                        )
                    }

                    items(repository.canonHistoricalMilestones) { milestone ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = milestone.councilOrEvent.get(currentLanguage),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = SacredBurgundy
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(LiturgicalGold.copy(alpha = 0.2f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(text = milestone.year, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SacredBurgundy)
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = milestone.description.get(currentLanguage),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    // 7 Deuterocanonical Books Detail
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Los 7 Libros Deuterocanónicos y su Riqueza Doctrinal:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = SacredBurgundy
                        )
                    }

                    items(repository.canonDeuterocanonicalDetails) { book ->
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = "✦ " + book.name.get(currentLanguage) + " (${book.abbreviation})",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = SacredBurgundy
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = book.doctrinalSignificance.get(currentLanguage),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                                ) {
                                    Text(
                                        text = "Versículo clave: " + book.keyVerse.get(currentLanguage),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = SacredBurgundy,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            1 -> {
                // Offline Bible Downloads Section
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        ElevatedCard(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Gestor de Traducciones Católicas Offline",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = SacredBurgundy
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "La traducción oficial (Biblia de Jerusalén 73 libros) está completamente disponible e integrada. Puedes descargar textos católicos adicionales para lectura offline.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    items(downloads) { pkg ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = CardDefaults.outlinedCardBorder().copy(width = 0.5.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = pkg.name,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = SacredBurgundy
                                        )
                                        Text(
                                            text = "${pkg.language} • ${pkg.sizeDisplay}",
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(
                                                when {
                                                    pkg.isDownloaded -> SacredBurgundy
                                                    pkg.isDownloading -> LiturgicalGold
                                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                                }
                                            )
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = when {
                                                pkg.isDownloaded -> "INSTALADA"
                                                pkg.isDownloading -> "${pkg.downloadProgress}%"
                                                else -> "DISPONIBLE"
                                            },
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (pkg.isDownloaded) Color.White else Color.Black
                                        )
                                    }
                                }

                                if (pkg.isDownloading) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    LinearProgressIndicator(
                                        progress = { pkg.downloadProgress / 100f },
                                        modifier = Modifier.fillMaxWidth(),
                                        color = SacredBurgundy
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (pkg.isDownloaded && pkg.versionId != "jerusalem") {
                                        TextButton(
                                            onClick = {
                                                scope.launch { downloadManager?.deleteDownload(pkg.versionId) }
                                            }
                                        ) {
                                            Icon(Icons.Default.Delete, contentDescription = null, modifier = Modifier.size(16.dp), tint = MaterialTheme.colorScheme.error)
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("Eliminar Descarga", fontSize = 11.sp, color = MaterialTheme.colorScheme.error)
                                        }
                                    } else if (!pkg.isDownloaded && !pkg.isDownloading) {
                                        FilledTonalButton(
                                            onClick = {
                                                downloadManager?.startDownload(pkg.versionId)
                                            }
                                        ) {
                                            Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("Descargar para Offline", fontSize = 11.sp)
                                        }
                                    } else if (pkg.versionId == "jerusalem") {
                                        Text(
                                            text = "✓ Incluida en App (Offline Permanente)",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = SacredBurgundy
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            2 -> {
                // Translation Versions Comparator
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Comparador de Versiones y Cánones Bíblicos"
                                AppLanguage.EN -> "Bible Versions & Canon Comparator"
                                AppLanguage.PT -> "Comparador de Versões e Cânones Bíblicos"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SacredBurgundy
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
                            shape = RoundedCornerShape(16.dp),
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
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(if (ver.isCatholicCanon) SacredBurgundy else Color(0xFF8B2500))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = if (ver.isCatholicCanon) "CANON CATÓLICO (73)" else "NO CATÓLICA (66)",
                                            color = Color.White,
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
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(MaterialTheme.colorScheme.surface)
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = ver.canonicalDifferenceNote.get(currentLanguage),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (ver.isCatholicCanon) MaterialTheme.colorScheme.onSurface else Color(0xFFB71C1C)
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
