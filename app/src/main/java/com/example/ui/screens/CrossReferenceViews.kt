package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.outlined.Hub
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import com.example.data.models.AppLanguage
import com.example.data.models.BibleBook
import com.example.data.models.BibleVerse
import com.example.data.repository.BibleData
import com.example.data.repository.CatholicDataRepository
import com.example.data.xref.VerseRef
import com.example.ui.components.BibleArcsPanel
import com.example.ui.components.CrossReferenceArcStrip
import com.example.ui.design.AppCard
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

private const val SheetPreviewCount = 12

private fun AppLanguage.say(es: String, en: String, pt: String) = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

private fun citation(book: BibleBook, chapter: Int, verse: Int, language: AppLanguage): String =
    "${book.name.get(language)} $chapter${if (language == AppLanguage.EN) ":" else ","}$verse"

/** Short book names for the arc baseline, in the reader's language. */
private fun shortBookName(id: String, language: AppLanguage): String =
    BibleData.catholicCanonBooks.firstOrNull { it.id == id }?.name?.get(language)
        ?.split(' ')?.lastOrNull() ?: id

/**
 * What a tap on a verse opens: the verse, its reach over the whole Bible as
 * arcs, the places it is cross-referenced (strongest first, each with its
 * opening words, each one tap from being read in place), and the patristic
 * gloss where the edition carries one.
 */
@Composable
fun VerseSheet(
    repository: CatholicDataRepository,
    book: BibleBook,
    chapterNumber: Int,
    verse: BibleVerse,
    currentLanguage: AppLanguage,
    onFollow: (VerseRef) -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val source = VerseRef(book.id, chapterNumber, verse.number)
    val references = remember(source, currentLanguage) {
        repository.crossReferences(book.id, chapterNumber, verse.number, currentLanguage)
    }
    val line = remember(currentLanguage) { repository.bibleLine(currentLanguage) }
    var shown by remember(source) { mutableIntStateOf(SheetPreviewCount) }
    val reading = Modifier.widthIn(max = design.spacing.readingMaxWidth)

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(bottom = design.spacing.contentBottomGap)
            .testTag("verse_sheet")
    ) {
        AppSectionHeader(title = citation(book, chapterNumber, verse.number, currentLanguage))
        Text(
            text = verse.text.get(currentLanguage),
            style = styles.quote,
            color = palette.textPrimary,
            modifier = reading.fillMaxWidth().padding(horizontal = design.spacing.gutter)
        )

        Spacer(Modifier.height(design.spacing.sectionGap / 2))

        if (references.isEmpty()) {
            AppEmptyState(
                title = currentLanguage.say("Sin referencias cruzadas", "No cross-references", "Sem referências cruzadas"),
                body = if (book.isDeuterocanonical) currentLanguage.say(
                    "La fuente abierta de referencias (OpenBible.info) no incluye los libros deuterocanónicos.",
                    "The open reference source (OpenBible.info) does not cover the deuterocanonical books.",
                    "A fonte aberta de referências (OpenBible.info) não inclui os livros deuterocanônicos."
                ) else currentLanguage.say(
                    "Este versículo no tiene referencias en la fuente incluida.",
                    "This verse has no references in the bundled source.",
                    "Este versículo não tem referências na fonte incluída."
                ),
                icon = Icons.Outlined.Hub
            )
        } else {
            line?.let {
                CrossReferenceArcStrip(
                    line = it,
                    source = source,
                    targets = references.map { r -> r.reference.target },
                    modifier = Modifier.padding(horizontal = design.spacing.gutter).testTag("verse_arc_strip")
                )
            }
            AppSectionHeader(
                title = currentLanguage.say("Referencias cruzadas", "Cross-references", "Referências cruzadas"),
                subtitle = currentLanguage.say(
                    "${references.size} lugares · gira el teléfono para el estudio completo",
                    "${references.size} places · turn your phone for the full study",
                    "${references.size} lugares · gire o telefone para o estudo completo"
                )
            )
            AppListGroup(Modifier.testTag("cross_reference_list")) {
                references.take(shown).forEach { ref ->
                    AppListRow(
                        title = ref.label,
                        subtitle = ref.preview.ifBlank { null },
                        onClick = { onFollow(ref.reference.target) }
                    )
                }
            }
            if (references.size > shown) {
                Spacer(Modifier.height(design.spacing.cardGap))
                AppSecondaryButton(
                    label = currentLanguage.say(
                        "Ver las ${references.size}",
                        "Show all ${references.size}",
                        "Ver as ${references.size}"
                    ),
                    onClick = { shown = references.size },
                    modifier = Modifier.padding(horizontal = design.spacing.gutter).fillMaxWidth()
                )
            }
        }

        verse.commentary?.let { commentary ->
            AppSectionHeader(
                title = currentLanguage.say("Comentario patrístico", "Patristic commentary", "Comentário patrístico")
            )
            AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(
                    text = verse.commentaryAuthor ?: currentLanguage.say("Padres de la Iglesia", "Church Fathers", "Padres da Igreja"),
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

        Spacer(Modifier.height(design.spacing.sectionGap / 2))
        Text(
            text = currentLanguage.say(
                "Referencias: OpenBible.info (CC BY 4.0), del Treasury of Scripture Knowledge, adaptadas a la numeración de esta Biblia.",
                "References: OpenBible.info (CC BY 4.0), from the Treasury of Scripture Knowledge, renumbered to this Bible.",
                "Referências: OpenBible.info (CC BY 4.0), do Treasury of Scripture Knowledge, adaptadas à numeração desta Bíblia."
            ),
            style = styles.rowSubtitle,
            color = palette.textTertiary,
            modifier = Modifier.padding(horizontal = design.spacing.gutter)
        )
    }
}

/**
 * The landscape study beside the text: every connection in the Bible, faint,
 * with the studied verse's (or, before one is chosen, the whole chapter's)
 * inked over it, and the list of where they lead.
 */
@Composable
fun CrossReferenceStudy(
    repository: CatholicDataRepository,
    book: BibleBook,
    chapterNumber: Int,
    verse: Int?,
    currentLanguage: AppLanguage,
    onFollow: (VerseRef) -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val line = remember(currentLanguage) { repository.bibleLine(currentLanguage) }
    val counts = remember(book.id, chapterNumber, currentLanguage) {
        repository.crossReferenceCounts(book.id, chapterNumber, currentLanguage)
    }
    val references = remember(book.id, chapterNumber, verse, currentLanguage) {
        if (verse == null) emptyList()
        else repository.crossReferences(book.id, chapterNumber, verse, currentLanguage)
    }
    // Before a verse is chosen, the chapter's own web: each verse's three
    // strongest links, so the panel is never empty on arrival.
    val focus = remember(book.id, chapterNumber, verse, currentLanguage) {
        if (verse != null) references.map { VerseRef(book.id, chapterNumber, verse) to it.reference.target }
        else counts.keys.sorted().flatMap { v ->
            repository.crossReferences(book.id, chapterNumber, v, currentLanguage, limit = 3)
                .map { VerseRef(book.id, chapterNumber, v) to it.reference.target }
        }
    }

    Column(modifier.padding(end = design.spacing.gutter).testTag("cross_reference_study")) {
        if (line == null) {
            AppEmptyState(
                title = currentLanguage.say("Estudio no disponible", "Study unavailable", "Estudo indisponível"),
                icon = Icons.Outlined.Hub
            )
            return@Column
        }
        BibleArcsPanel(
            line = line,
            allConnections = { repository.allCrossReferences(currentLanguage, minVotes = 15) },
            focus = focus,
            focusLabel = if (verse != null) citation(book, chapterNumber, verse, currentLanguage)
            else "${book.name.get(currentLanguage)} $chapterNumber · " + currentLanguage.say(
                "toca un versículo", "tap a verse", "toque um versículo"
            ),
            bookLabel = { shortBookName(it, currentLanguage) },
            modifier = Modifier.fillMaxWidth().weight(0.55f)
        )
        Box(Modifier.weight(0.45f).fillMaxWidth()) {
            if (verse == null) {
                Text(
                    text = currentLanguage.say(
                        "Cada arco une dos pasajes que se iluminan entre sí: en oro y tinta el Antiguo y el Nuevo Testamento. Toca un versículo a la izquierda para seguir sus conexiones.",
                        "Each arc joins two passages that shed light on each other, the Old and New Testaments in their own inks. Tap a verse on the left to follow its connections.",
                        "Cada arco une duas passagens que se iluminam mutuamente, o Antigo e o Novo Testamento em suas tintas. Toque um versículo à esquerda para seguir suas conexões."
                    ),
                    style = LocalAppTextStyles.current.rowSubtitle,
                    color = palette.textSecondary,
                    modifier = Modifier.padding(design.spacing.gutter)
                )
            } else if (references.isEmpty()) {
                AppEmptyState(
                    title = currentLanguage.say("Sin referencias", "No references", "Sem referências"),
                    icon = Icons.AutoMirrored.Filled.Comment
                )
            } else {
                LazyColumn(Modifier.fillMaxSize()) {
                    item {
                        AppListGroup {
                            references.forEach { ref ->
                                AppListRow(
                                    title = ref.label,
                                    subtitle = ref.preview.ifBlank { null },
                                    onClick = { onFollow(ref.reference.target) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
