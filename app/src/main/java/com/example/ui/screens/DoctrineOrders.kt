package com.example.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppSegmentedTabs

// -----------------------------------------------------------------------------
// Orders & offices: emblem plates, a segmented list, readers with sequence footers.
// -----------------------------------------------------------------------------

@Composable
internal fun OrdersRoot(
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav,
    spec: RootSpec
) {
    val state = rememberLazyListState()
    var segment by rememberSaveable { mutableStateOf(0) }
    val orders = repository.religiousOrders
    val offices = repository.churchOffices
    val officeOrder = remember(offices) { officeDisplayOrder(offices.map { it.id }) }

    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = LocalBottomPad.current)
    ) {
        item(key = "hero", contentType = "hero") { RootHeader(spec) }
        item(key = "segments") {
            Spacer(Modifier.height(4.dp))
            AppSegmentedTabs(
                options = listOf(
                    language.tr("Órdenes", "Orders", "Ordens"),
                    language.tr("Oficios", "Offices", "Ofícios")
                ),
                selectedIndex = segment,
                onSelect = { segment = it },
                modifier = Modifier.testTag("orders_offices_tabs")
            )
            Spacer(Modifier.height(20.dp))
        }

        if (segment == 0) {
            if (orders.isEmpty()) {
                item(key = "no_orders") {
                    AppEmptyState(
                        title = language.tr("Sin órdenes por mostrar", "No orders to show", "Sem ordens para mostrar"),
                        icon = Icons.Outlined.Church
                    )
                }
            } else {
                itemsIndexed(orders, key = { _, o -> "o_${o.id}" }, contentType = { _, _ -> "order" }) { i, order ->
                    LeadRow(
                        title = order.name.get(language),
                        subtitle = orderMeta(order.abbreviation, order.foundationYear),
                        leading = { EmblemPlate(order.id, 44.dp) },
                        leadingWidth = 44.dp,
                        position = groupPosition(i, orders.size),
                        onClick = { nav.open("order:$i") },
                        tag = "order_${order.id}"
                    )
                }
            }
        } else {
            if (offices.isEmpty()) {
                item(key = "no_offices") {
                    AppEmptyState(
                        title = language.tr("Sin oficios por mostrar", "No offices to show", "Sem ofícios para mostrar"),
                        icon = Icons.Outlined.LocationCity
                    )
                }
            } else {
                OfficeGroup.entries.forEach { group ->
                    val members = officeOrder.filter { officeGroup(offices[it].id) == group }
                    if (members.isNotEmpty()) {
                        item(key = "og_${group.name}", contentType = "header") {
                            SectionHeader(officeGroupTitle(group, language), topGap = 8.dp)
                        }
                        itemsIndexed(members, key = { _, m -> "f_${offices[m].id}" }, contentType = { _, _ -> "office" }) { i, m ->
                            val office = offices[m]
                            LeadRow(
                                title = office.name.get(language),
                                position = groupPosition(i, members.size),
                                onClick = { nav.open("office:$m") },
                                tag = "office_${office.id}"
                            )
                        }
                        item(key = "og_gap_${group.name}", contentType = "gap") { Spacer(Modifier.height(16.dp)) }
                    }
                }
            }
        }
    }
}

@Composable
internal fun OrdersEntry(
    kind: String,
    index: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    if (kind == "office") OfficePage(index, repository, language, nav) else OrderPage(index, repository, language, nav)
}

@Composable
private fun OrderPage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val orders = repository.religiousOrders
    if (orders.isEmpty()) return
    val index = rawIndex.coerceIn(0, orders.lastIndex)
    val order = orders[index]
    val total = orders.size
    val name = order.name.get(language)
    AppInlineTitle(name)
    LaunchedEffect(Unit) { nav.voice.prepare() }
    val charismLabel = language.tr("Carisma", "Charism", "Carisma")
    val description = order.description.get(language)
    val charism = order.charism.get(language)
    val speak = buildString {
        append(name); append("\n\n"); append(description)
        if (charism.isNotBlank()) { append("\n\n"); append(charismLabel); append(": "); append(charism) }
    }
    val abbreviation = order.abbreviation.takeIf { it.isNotBlank() && it != "—" && it != "-" }
    val founded = order.foundationYear.takeIf { it.isNotBlank() }?.let {
        language.tr("Fundada en $it", "Founded in $it", "Fundada em $it")
    }
    val founder = order.founder.get(language).takeIf { it.isNotBlank() }?.let {
        language.tr("Fundador: $it", "Founder: $it", "Fundador: $it")
    }
    val progressLabel = language.tr("Orden ${index + 1} de $total", "Order ${index + 1} of $total", "Ordem ${index + 1} de $total")
    ReaderPage(
        title = name,
        eyebrow = listOfNotNull(abbreviation, founded).joinToString(" · ").ifEmpty { null },
        meta = founder,
        progress = (index + 1).toFloat() / total,
        progressLabel = progressLabel,
        progressTag = "order_progress",
        leading = {
            EmblemPlate(order.id, 104.dp, contentDescription = name, tag = "order_emblem_detail")
        }
    ) {
        item(key = "actions") {
            Spacer(Modifier.height(4.dp))
            ReaderActionRow(language, nav.voice, speak, null, "order_listen_button", null)
            BlockGap(24.dp)
        }
        item(key = "description") {
            ReadingParagraphs(description, lead = true)
            BlockGap(28.dp)
        }
        if (charism.isNotBlank()) item(key = "charism") {
            NoteCard(charismLabel, charism, quote = true)
            BlockGap(28.dp)
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = orders.getOrNull(index + 1)?.name?.get(language),
                prevTitle = orders.getOrNull(index - 1)?.name?.get(language),
                onNext = { nav.replace("order:${index + 1}") },
                onPrev = { nav.replace("order:${index - 1}") },
                nextTag = "order_next_button",
                prevTag = "order_prev_button"
            )
        }
    }
}

@Composable
private fun OfficePage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val offices = repository.churchOffices
    if (offices.isEmpty()) return
    val index = rawIndex.coerceIn(0, offices.lastIndex)
    val office = offices[index]
    val display = remember(offices) { officeDisplayOrder(offices.map { it.id }) }
    val pos = display.indexOf(index).coerceAtLeast(0)
    val total = offices.size
    val name = office.name.get(language)
    AppInlineTitle(name)
    LaunchedEffect(Unit) { nav.voice.prepare() }
    val description = office.description.get(language)
    val progressLabel = language.tr("Oficio ${pos + 1} de $total", "Office ${pos + 1} of $total", "Ofício ${pos + 1} de $total")
    ReaderPage(
        title = name,
        eyebrow = officeGroupTitle(officeGroup(office.id), language),
        progress = (pos + 1).toFloat() / total,
        progressLabel = progressLabel,
        progressTag = "office_progress"
    ) {
        item(key = "actions") {
            ReaderActionRow(language, nav.voice, name + "\n\n" + description, null, "office_listen_button", null)
            BlockGap(24.dp)
        }
        item(key = "description") {
            ReadingParagraphs(description, lead = true)
            BlockGap(28.dp)
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = display.getOrNull(pos + 1)?.let { offices[it].name.get(language) },
                prevTitle = display.getOrNull(pos - 1)?.let { offices[it].name.get(language) },
                onNext = { display.getOrNull(pos + 1)?.let { nav.replace("office:$it") } },
                onPrev = { display.getOrNull(pos - 1)?.let { nav.replace("office:$it") } },
                nextTag = "office_next_button",
                prevTag = "office_prev_button"
            )
        }
    }
}
