package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.ChurchHistoryEpoch
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppChevron
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppStepList
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

// -----------------------------------------------------------------------------
// History: twenty centuries as a timeline, with the schisms as events on it.
// -----------------------------------------------------------------------------

private sealed interface TimelineItem {
    data class Epoch(val dataIndex: Int) : TimelineItem
    data class Schism(val dataIndex: Int) : TimelineItem
}

private fun timelineItems(repository: CatholicDataRepository): List<TimelineItem> {
    val epochs = repository.churchHistoryEpochs
    val order = chronologicalEpochIndices(epochs)
    val schisms = repository.churchSchisms
    val modernPos = order.indexOfFirst { epochs[it].epochId == "modern" }
    val before = HashMap<Int, MutableList<Int>>()
    schisms.forEachIndexed { i, s ->
        val pos = schismInsertPosition(s, epochs, order) ?: if (modernPos >= 0) modernPos else order.size
        before.getOrPut(pos) { mutableListOf() }.add(i)
    }
    val out = mutableListOf<TimelineItem>()
    order.forEachIndexed { pos, dataIndex ->
        before[pos]?.sortedBy { yearOf(schisms[it].historicalYear) ?: 0 }?.forEach { out += TimelineItem.Schism(it) }
        out += TimelineItem.Epoch(dataIndex)
    }
    before[order.size]?.forEach { out += TimelineItem.Schism(it) }
    return out
}

@Composable
internal fun HistoryRoot(
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav,
    spec: RootSpec
) {
    val state = rememberLazyListState()
    val items = remember(repository) { timelineItems(repository) }
    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = LocalBottomPad.current)
    ) {
        item(key = "hero", contentType = "hero") { RootHeader(spec) }
        item(key = "gap") { Spacer(Modifier.height(8.dp)) }
        itemsIndexed(
            items,
            key = { _, it -> if (it is TimelineItem.Epoch) "e${it.dataIndex}" else "s${(it as TimelineItem.Schism).dataIndex}" },
            contentType = { _, it -> if (it is TimelineItem.Epoch) "epoch" else "schism" }
        ) { i, item ->
            val first = i == 0
            val last = i == items.lastIndex
            when (item) {
                is TimelineItem.Epoch -> {
                    val epoch = repository.churchHistoryEpochs[item.dataIndex]
                    TimelineRow(first, last, nodeY = 30.dp, node = { EpochNode() }) {
                        EpochCard(epoch, language) { nav.open("epoch:${item.dataIndex}") }
                    }
                }
                is TimelineItem.Schism -> {
                    val schism = repository.churchSchisms[item.dataIndex]
                    TimelineRow(first, last, nodeY = 28.dp, node = { EventNode() }) {
                        EventRow(
                            year = eraYear(schism.historicalYear, language),
                            title = schism.title.get(language),
                            language = language,
                            tag = "schism_row_${schism.id}"
                        ) { nav.open("schism:${item.dataIndex}") }
                    }
                }
            }
        }
    }
}

/** One row of the timeline: a rail with a node on the left, content on the right. */
@Composable
private fun TimelineRow(
    first: Boolean,
    last: Boolean,
    nodeY: Dp,
    node: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val rail = p.separator.copy(alpha = (p.separator.alpha * 2.4f).coerceAtMost(1f))
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = design.spacing.gutter)
            .drawBehind {
                val x = 12.dp.toPx()
                val y = nodeY.toPx()
                val top = if (first) y else 0f
                val bottom = if (last) y else size.height
                drawLine(rail, Offset(x, top), Offset(x, bottom), strokeWidth = 1.dp.toPx())
            }
    ) {
        Box(Modifier.width(36.dp).height(nodeY * 2)) {
            Box(Modifier.offset(x = 12.dp, y = nodeY).align(Alignment.TopStart)) {
                // The node is centred on the rail.
                Box(Modifier.offset(x = (-7).dp, y = (-7).dp)) { node() }
            }
        }
        Box(
            Modifier
                .weight(1f)
                .padding(end = design.spacing.gutter, bottom = 12.dp)
        ) { content() }
    }
}

@Composable
private fun EpochNode() {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .size(14.dp)
            .background(p.canvasTop, CircleShape)
            .border(2.dp, p.textSecondary, CircleShape)
    )
}

@Composable
private fun EventNode() {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .size(14.dp)
            .background(p.canvasTop, CircleShape)
            .border(2.5.dp, p.accent, CircleShape)
    )
}

@Composable
private fun EpochCard(epoch: ChurchHistoryEpoch, language: AppLanguage, onClick: () -> Unit) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val span = remember(epoch.centuryRange, language) { centurySpan(epoch.centuryRange, language) }
    AppGlassSurface(
        Modifier.fillMaxWidth().testTag("epoch_row_${epoch.epochId}"),
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    span.years ?: span.centuries,
                    style = styles.eyebrow.copy(fontFeatureSettings = "tnum"),
                    color = p.textSecondary
                )
                Spacer(Modifier.height(4.dp))
                Text(epoch.title.get(language), style = styles.headline, color = p.textPrimary)
                if (span.years != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(span.centuries, style = styles.footnote, color = p.textSecondary)
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    firstSentence(epoch.narrative.get(language), 160),
                    style = styles.callout,
                    color = p.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.width(12.dp))
            AppChevron()
        }
    }
}

@Composable
private fun EventRow(year: String, title: String, language: AppLanguage, tag: String, onClick: () -> Unit) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .lumenPress(onClick, 0.98f)
            .semantics { contentDescription = language.tr("Cisma, $year, $title", "Schism, $year, $title", "Cisma, $year, $title") }
            .testTag(tag)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(year, style = styles.eyebrow.copy(fontFeatureSettings = "tnum"), color = p.textSecondary)
            Spacer(Modifier.height(2.dp))
            Text(title, style = styles.headline, color = p.textPrimary)
        }
        Spacer(Modifier.width(12.dp))
        AppChevron()
    }
}

// -------------------------------------------------------------------- pages

@Composable
internal fun HistoryEntry(
    kind: String,
    index: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    if (kind == "schism") SchismPage(index, repository, language) else EpochPage(index, repository, language, nav)
}

@Composable
private fun EpochPage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val epochs = repository.churchHistoryEpochs
    if (epochs.isEmpty()) return
    val index = rawIndex.coerceIn(0, epochs.lastIndex)
    val epoch = epochs[index]
    val order = remember(epochs) { chronologicalEpochIndices(epochs) }
    val pos = order.indexOf(index)
    val title = epoch.title.get(language)
    AppInlineTitle(title)
    val span = centurySpan(epoch.centuryRange, language)
    ReaderPage(
        title = title,
        eyebrow = listOfNotNull(span.centuries, span.years).joinToString(" · ")
    ) {
        item(key = "narrative") {
            ReadingParagraphs(epoch.narrative.get(language), lead = true)
            BlockGap(28.dp)
        }
        item(key = "facts") {
            FactsGroup(
                listOf(
                    language.tr("Papas clave", "Key popes", "Papas principais") to epoch.keyPopes.get(language),
                    language.tr("Concilios", "Councils", "Concílios") to epoch.ecumenicalCouncils.get(language),
                    language.tr("Santos de la época", "Saints of the age", "Santos da época") to epoch.historicalSaints.get(language)
                )
            )
            BlockGap(28.dp)
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = order.getOrNull(pos + 1)?.let { epochs[it].title.get(language) },
                prevTitle = order.getOrNull(pos - 1)?.let { epochs[it].title.get(language) },
                onNext = { order.getOrNull(pos + 1)?.let { nav.replace("epoch:$it") } },
                onPrev = { order.getOrNull(pos - 1)?.let { nav.replace("epoch:$it") } },
                nextTag = "epoch_next",
                prevTag = "epoch_prev"
            )
        }
    }
}

@Composable
private fun SchismPage(rawIndex: Int, repository: CatholicDataRepository, language: AppLanguage) {
    val schisms = repository.churchSchisms
    if (schisms.isEmpty()) return
    val schism = schisms[rawIndex.coerceIn(0, schisms.lastIndex)]
    val title = schism.title.get(language)
    AppInlineTitle(title)
    val styles = LocalAppTextStyles.current
    val p = LocalAppDesign.current.palette
    val differences = remember(schism, language) { parseNumbered(schism.theologicalDifferences.get(language)) }
    ReaderPage(title = title, eyebrow = eraYear(schism.historicalYear, language)) {
        item(key = "context") {
            ReadingParagraphs(schism.context.get(language), lead = true)
            BlockGap(28.dp)
        }
        item(key = "differences") {
            Text(
                language.tr("Diferencias doctrinales", "Key doctrinal differences", "Diferenças doutrinais"),
                style = styles.title3,
                color = p.textPrimary,
                modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 12.dp).semantics { heading() }
            )
            if (differences.steps.isNotEmpty()) {
                AppStepList(steps = differences.steps)
            } else {
                ReadingParagraphs(schism.theologicalDifferences.get(language))
            }
            BlockGap(28.dp)
        }
        item(key = "perspective") {
            NoteCard(
                language.tr("Perspectiva católica", "Catholic perspective", "Perspetiva católica"),
                schism.catholicPerspective.get(language)
            )
            Spacer(Modifier.height(12.dp))
            NoteCard(
                language.tr("Diálogo y reconciliación", "Dialogue and reconciliation", "Diálogo e reconciliação"),
                schism.dialogueAndReconciliation.get(language)
            )
        }
    }
}
