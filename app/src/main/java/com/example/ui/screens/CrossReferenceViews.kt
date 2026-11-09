package com.example.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.Hub
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.BibleBook
import com.example.data.models.BibleVerse
import com.example.data.repository.BibleData
import com.example.data.repository.CatholicDataRepository
import com.example.data.xref.VerseRef
import com.example.ui.components.BibleArcsPanel
import com.example.ui.components.CrossReferenceArcStrip
import com.example.ui.components.crossReferenceInks
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.appScreenInsets
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

private const val SheetPreviewCount = 12

private fun AppLanguage.say(es: String, en: String, pt: String) = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

private fun citation(book: BibleBook, chapter: Int, verse: Int, language: AppLanguage): String =
    citationOf(book.name.get(language), chapter, verse, language)

/** Short book names for the arc baseline, in the reader's language. */
private fun shortBookName(id: String, language: AppLanguage): String =
    BibleData.catholicCanonBooks.firstOrNull { it.id == id }?.name?.get(language)
        ?.substringBefore(" (")?.split(' ')?.lastOrNull() ?: id

/**
 * What a tap on a verse opens: the verse, actions to copy, share or hear it,
 * its reach over the whole Bible as arcs, the places it is cross-referenced
 * (strongest first, each one tap from being read in place), and the
 * patristic gloss where the edition carries one.
 *
 * The caller provides the scroll container (a sheet); [onFollow] stays last
 * so a trailing lambda still binds to it.
 */
@Composable
fun VerseSheet(
    repository: CatholicDataRepository,
    book: BibleBook,
    chapterNumber: Int,
    verse: BibleVerse,
    currentLanguage: AppLanguage,
    onListen: (() -> Unit)? = null,
    onFollow: (VerseRef) -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current
    val source = VerseRef(book.id, chapterNumber, verse.number)
    val references = remember(source, currentLanguage) {
        repository.crossReferences(book.id, chapterNumber, verse.number, currentLanguage)
    }
    val line = remember(currentLanguage) { repository.bibleLine(currentLanguage) }
    var shown by remember(source) { mutableIntStateOf(SheetPreviewCount) }
    var copied by remember(source) { mutableStateOf(false) }
    val cite = citation(book, chapterNumber, verse.number, currentLanguage)
    val text = verse.text.get(currentLanguage)
    val inset = design.spacing.gutter + 4.dp

    Column(Modifier.fillMaxWidth().testTag("verse_sheet")) {
        Text(
            cite, style = styles.title2, color = p.textPrimary,
            modifier = Modifier.padding(horizontal = inset).padding(top = 6.dp).semantics { heading() }
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = text,
            style = styles.reading,
            color = p.textPrimary,
            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth).fillMaxWidth().padding(horizontal = inset)
        )

        // Copy · share · listen, the reference's own action row.
        Spacer(Modifier.height(18.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SheetAction(
                icon = Icons.Outlined.ContentCopy,
                label = if (copied) currentLanguage.say("Copiado", "Copied", "Copiado") else currentLanguage.say("Copiar", "Copy", "Copiar"),
                tag = "verse_copy"
            ) {
                clipboard.setText(AnnotatedString("«$text» — $cite"))
                copied = true
            }
            SheetAction(Icons.Outlined.IosShare, currentLanguage.say("Compartir", "Share", "Partilhar"), "verse_share") {
                runCatching {
                    val send = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "«$text» — $cite")
                    }
                    context.startActivity(Intent.createChooser(send, cite).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            }
            if (onListen != null) {
                SheetAction(Icons.Outlined.Headphones, currentLanguage.say("Escuchar", "Listen", "Ouvir"), "verse_listen", onListen)
            }
        }

        Spacer(Modifier.height(24.dp))
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
            Text(
                currentLanguage.say("Referencias cruzadas", "Cross-references", "Referências cruzadas"),
                style = styles.title3, color = p.textPrimary,
                modifier = Modifier.padding(horizontal = inset).semantics { heading() }
            )
            Spacer(Modifier.height(2.dp))
            Text(
                currentLanguage.say(
                    "${references.size} lugares · gira el teléfono para el estudio completo",
                    "${references.size} places · turn your phone for the full study",
                    "${references.size} lugares · gire o telefone para o estudo completo"
                ),
                style = styles.footnote, color = p.textSecondary,
                modifier = Modifier.padding(horizontal = inset)
            )
            Spacer(Modifier.height(12.dp))
            line?.let {
                AppGlassSurface(
                    Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
                    lifted = false,
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                ) {
                    Column {
                        CrossReferenceArcStrip(
                            line = it,
                            source = source,
                            targets = references.map { r -> r.reference.target },
                            modifier = Modifier.testTag("verse_arc_strip")
                        )
                        Spacer(Modifier.height(10.dp))
                        ArcLegend(currentLanguage)
                    }
                }
                Spacer(Modifier.height(14.dp))
            }
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
                Spacer(Modifier.height(12.dp))
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    AppSecondaryCapsule(
                        label = currentLanguage.say("Ver las ${references.size}", "Show all ${references.size}", "Ver as ${references.size}"),
                        onClick = { shown = references.size },
                        tag = "cross_reference_show_all"
                    )
                }
            }
        }

        verse.commentary?.let { commentary ->
            Spacer(Modifier.height(28.dp))
            Text(
                currentLanguage.say("Comentario patrístico", "Patristic commentary", "Comentário patrístico"),
                style = styles.title3, color = p.textPrimary,
                modifier = Modifier.padding(horizontal = inset).semantics { heading() }
            )
            Spacer(Modifier.height(10.dp))
            AppGlassSurface(
                Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
                contentPadding = PaddingValues(20.dp)
            ) {
                Column {
                    Text(
                        verse.commentaryAuthor ?: currentLanguage.say("Padres de la Iglesia", "Church Fathers", "Padres da Igreja"),
                        style = styles.eyebrow, color = p.textSecondary
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(commentary.get(currentLanguage), style = styles.callout, color = p.textPrimary)
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Text(
            text = currentLanguage.say(
                "Referencias: OpenBible.info (CC BY 4.0), del Treasury of Scripture Knowledge, adaptadas a la numeración de esta Biblia.",
                "References: OpenBible.info (CC BY 4.0), from the Treasury of Scripture Knowledge, renumbered to this Bible.",
                "Referências: OpenBible.info (CC BY 4.0), do Treasury of Scripture Knowledge, adaptadas à numeração desta Bíblia."
            ),
            style = styles.footnote,
            color = p.textSecondary,
            modifier = Modifier.padding(horizontal = inset)
        )
        Spacer(Modifier.height(8.dp))
    }
}

/** A glass circle with its label under it, as in the reference's action rows. */
@Composable
private fun SheetAction(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, tag: String, onClick: () -> Unit) {
    val p = LocalAppDesign.current.palette
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(76.dp)) {
        AppGlassIconButton(icon = icon, contentDescription = label, onClick = onClick, tag = tag)
        Spacer(Modifier.height(4.dp))
        Text(label, style = LocalAppTextStyles.current.caption, color = p.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

/** What the two inks of the arcs mean. */
@Composable
private fun ArcLegend(currentLanguage: AppLanguage) {
    val p = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    val inks = crossReferenceInks()
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
        LegendItem(inks.first, currentLanguage.say("Antiguo Testamento", "Old Testament", "Antigo Testamento"))
        LegendItem(inks.second, currentLanguage.say("Nuevo Testamento", "New Testament", "Novo Testamento"))
    }
}

@Composable
private fun LegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(8.dp).clip(CircleShape).background(color))
        Spacer(Modifier.width(6.dp))
        Text(label, style = LocalAppTextStyles.current.caption, color = LocalAppDesign.current.palette.textSecondary)
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
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val insets = appScreenInsets()
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

    Column(
        modifier
            .padding(top = insets.calculateTopPadding(), end = design.spacing.gutter, bottom = 12.dp)
            .testTag("cross_reference_study")
    ) {
        if (line == null) {
            AppEmptyState(
                title = currentLanguage.say("Estudio no disponible", "Study unavailable", "Estudo indisponível"),
                icon = Icons.Outlined.Hub
            )
            return@Column
        }
        AppGlassSurface(Modifier.fillMaxWidth().weight(0.56f), lifted = false) {
            BibleArcsPanel(
                line = line,
                allConnections = { repository.allCrossReferences(currentLanguage, minVotes = 15) },
                focus = focus,
                focusLabel = if (verse != null) citation(book, chapterNumber, verse, currentLanguage)
                else "${book.name.get(currentLanguage)} $chapterNumber · " + currentLanguage.say(
                    "toca un versículo", "tap a verse", "toque um versículo"
                ),
                bookLabel = { shortBookName(it, currentLanguage) },
                modifier = Modifier.fillMaxSize().padding(4.dp)
            )
        }
        Spacer(Modifier.height(12.dp))
        Box(Modifier.weight(0.44f).fillMaxWidth()) {
            if (verse == null) {
                Text(
                    text = currentLanguage.say(
                        "Cada arco une dos pasajes que se iluminan entre sí: en oro y tinta el Antiguo y el Nuevo Testamento. Toca un versículo a la izquierda para seguir sus conexiones.",
                        "Each arc joins two passages that shed light on each other, the Old and New Testaments in their own inks. Tap a verse on the left to follow its connections.",
                        "Cada arco une duas passagens que se iluminam mutuamente, o Antigo e o Novo Testamento em suas tintas. Toque um versículo à esquerda para seguir suas conexões."
                    ),
                    style = styles.callout,
                    color = p.textSecondary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                )
            } else if (references.isEmpty()) {
                AppEmptyState(
                    title = currentLanguage.say("Sin referencias", "No references", "Sem referências"),
                    icon = Icons.Outlined.Hub
                )
            } else {
                LazyColumn(Modifier.fillMaxSize()) {
                    item {
                        AppListGroup(Modifier.padding(start = 0.dp)) {
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
