package com.example.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Stop
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.ChristManifestation
import com.example.data.models.MarianApparition
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppFilterChips
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppSearchBar
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

// -----------------------------------------------------------------------------
// Mary & Jesus: Marian advocations and devotions to Christ, a searchable list
// grouped by region, and a reading page for each devotion.
// -----------------------------------------------------------------------------

/** Sentinel for "every region". */
private const val EVERY_REGION = "ALL"

/** Regions in the order they appear; any other value in the data follows. */
private val REGION_ORDER = listOf("Americas", "Europe", "Asia_Africa", "Universal")

private fun regionLabel(region: String, language: AppLanguage): String = when (region) {
    EVERY_REGION -> language.tr("Todas", "All", "Todas")
    "Americas" -> language.tr("Américas", "Americas", "Américas")
    "Europe" -> language.tr("Europa", "Europe", "Europa")
    "Asia_Africa" -> language.tr("Asia y África", "Asia & Africa", "Ásia e África")
    "Universal" -> language.tr("Universales", "Universal", "Universais")
    else -> region.replace('_', ' ')
}

/** One row of the list, whichever category it comes from. */
private data class DevotionRowData(
    val id: String,
    val region: String,
    val title: String,
    val subtitle: String,
    val year: String?
)

@Composable
internal fun DevotionsExplorerScreen(
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav,
    spec: RootSpec
) {
    val state = rememberLazyListState()
    var category by rememberSaveable { mutableStateOf(0) }
    var query by rememberSaveable { mutableStateOf("") }
    var region by rememberSaveable { mutableStateOf(EVERY_REGION) }

    val marian = repository.marianApparitions
    val christ = repository.christManifestations
    val q = query.trim().lowercase()

    fun hit(vararg fields: String) = q.isEmpty() || fields.any { it.lowercase().contains(q) }

    val rows: List<DevotionRowData> = remember(category, q, language, marian, christ) {
        if (category == 0) {
            marian.filter {
                hit(
                    it.title.get(language), it.country.get(language), it.location.get(language),
                    it.patronageOf.get(language), it.primarySanctuary.get(language), it.recordedMiracles.get(language)
                )
            }.map {
                DevotionRowData(
                    it.id, it.region, splitAlias(it.title.get(language)).title,
                    it.location.get(language), devotionYear(it.year, language)
                )
            }
        } else {
            christ.filter {
                hit(
                    it.title.get(language), it.country.get(language), it.primarySanctuary.get(language),
                    it.originHistory.get(language), it.recordedMiracles.get(language)
                )
            }.map {
                DevotionRowData(it.id, it.region, splitAlias(it.title.get(language)).title, it.country.get(language), null)
            }
        }
    }
    val inRegion = if (region == EVERY_REGION) rows else rows.filter { it.region == region }

    val regionKeys = listOf(EVERY_REGION) + REGION_ORDER
    val regionIndex = regionKeys.indexOf(region).coerceAtLeast(0)
    val grouped: List<Pair<String, List<DevotionRowData>>> =
        if (region == EVERY_REGION) {
            val known = REGION_ORDER.map { r -> r to rows.filter { it.region == r } }
            val others = rows.filter { it.region !in REGION_ORDER }.groupBy { it.region }.toList()
            (known + others).filter { it.second.isNotEmpty() }
        } else {
            listOf(region to inRegion).filter { it.second.isNotEmpty() }
        }

    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize().testTag("devotions_explorer_screen"),
        contentPadding = PaddingValues(bottom = LocalBottomPad.current)
    ) {
        item(key = "hero", contentType = "hero") { RootHeader(spec) }
        item(key = "controls") {
            Spacer(Modifier.height(4.dp))
            AppSegmentedTabs(
                options = listOf(
                    language.tr("Nuestra Señora", "Our Lady", "Nossa Senhora"),
                    language.tr("Cristo", "Christ", "Cristo")
                ),
                selectedIndex = category,
                onSelect = { category = it },
                modifier = Modifier.testTag("devotions_category_picker")
            )
            Spacer(Modifier.height(14.dp))
            AppSearchBar(
                value = query,
                onValueChange = { query = it },
                placeholder = language.tr("Nombre, país o santuario", "Name, country or shrine", "Nome, país ou santuário"),
                tag = "devotions_search_input"
            )
            Spacer(Modifier.height(12.dp))
            AppFilterChips(
                options = regionKeys.map { regionLabel(it, language) },
                selectedIndex = regionIndex,
                onSelect = { region = regionKeys[it] },
                modifier = Modifier.testTag("devotions_region_picker")
            )
        }

        if (inRegion.isEmpty()) {
            item(key = "empty") {
                AppEmptyState(
                    title = language.tr("Sin resultados", "Nothing found", "Sem resultados"),
                    body = language.tr(
                        "Prueba otro nombre o elige otra región.",
                        "Try another name, or choose a different region.",
                        "Tente outro nome ou escolha outra região."
                    ),
                    icon = Icons.Outlined.Search
                )
                Column(Modifier.fillMaxWidth().padding(horizontal = 40.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    AppSecondaryCapsule(
                        label = language.tr("Ver todas", "Show all", "Ver todas"),
                        onClick = { query = ""; region = EVERY_REGION }
                    )
                }
            }
        } else {
            grouped.forEach { (key, members) ->
                item(key = "h_$key", contentType = "header") {
                    SectionHeader(
                        if (region == EVERY_REGION) regionLabel(key, language)
                        else if (category == 0) language.tr("Advocaciones marianas", "Marian titles", "Advocações marianas")
                        else language.tr("Devociones a Cristo", "Devotions to Christ", "Devoções a Cristo"),
                        trailing = "${members.size}",
                        topGap = 24.dp
                    )
                }
                itemsIndexed(members, key = { _, m -> "d_${m.id}" }, contentType = { _, _ -> "devotion" }) { i, m ->
                    LeadRow(
                        title = m.title,
                        subtitle = m.subtitle,
                        trailingText = m.year,
                        position = groupPosition(i, members.size),
                        onClick = { nav.open("devotion:${m.id}") },
                        tag = "devotion_card_${m.id}"
                    )
                }
            }
        }
    }
}

// ------------------------------------------------------------------ detail

@Composable
internal fun DevotionDetail(
    id: String,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val marian = repository.marianApparitions.firstOrNull { it.id == id }
    val christ = repository.christManifestations.firstOrNull { it.id == id }
    when {
        marian != null -> MarianPage(marian, language, nav)
        christ != null -> ChristPage(christ, language, nav)
    }
}

@Composable
private fun MarianPage(item: MarianApparition, language: AppLanguage, nav: DoctrineNav) {
    val ta = splitAlias(item.title.get(language))
    AppInlineTitle(ta.title)
    val year = devotionYear(item.year, language)
    DevotionPage(
        language = language,
        nav = nav,
        id = item.id,
        title = ta.title,
        eyebrow = language.tr("Advocación mariana", "Marian title", "Advocação mariana") + if (year.isNotBlank()) " · $year" else "",
        alias = ta.alias,
        facts = listOf(
            language.tr("Fiesta", "Feast day", "Festa") to item.feastDay.get(language),
            language.tr("País", "Country", "País") to item.country.get(language),
            language.tr("Lugar", "Place", "Lugar") to item.location.get(language),
            language.tr("Santuario", "Shrine", "Santuário") to item.primarySanctuary.get(language),
            language.tr("Patronazgo", "Patronage", "Padroado") to item.patronageOf.get(language)
        ),
        historyLabel = language.tr("Historia", "History", "História"),
        history = item.historyAndApparition.get(language),
        meaningLabel = null,
        meaning = null,
        promises = item.promises.get(language),
        miraclesLabel = language.tr("Milagros destacados", "Recorded miracles", "Milagres registados"),
        miracles = item.recordedMiracles.get(language),
        prayerTitle = language.tr(
            "Oración de veneración e intercesión",
            "Prayer of veneration and intercession",
            "Oração de veneração e intercessão"
        ),
        prayer = item.venerationPrayer.get(language)
    )
}

@Composable
private fun ChristPage(item: ChristManifestation, language: AppLanguage, nav: DoctrineNav) {
    val ta = splitAlias(item.title.get(language))
    AppInlineTitle(ta.title)
    DevotionPage(
        language = language,
        nav = nav,
        id = item.id,
        title = ta.title,
        eyebrow = language.tr("Devoción a Cristo", "Devotion to Christ", "Devoção a Cristo"),
        alias = ta.alias,
        facts = listOf(
            language.tr("Fiesta", "Feast day", "Festa") to item.feastDay.get(language),
            language.tr("País", "Country", "País") to item.country.get(language),
            language.tr("Santuario principal", "Principal shrine", "Santuário principal") to item.primarySanctuary.get(language)
        ),
        historyLabel = language.tr("Historia", "History", "História"),
        history = item.originHistory.get(language),
        meaningLabel = language.tr("Sentido teológico", "Theological meaning", "Sentido teológico"),
        meaning = item.theologicalMeaning.get(language),
        promises = item.promises.get(language),
        miraclesLabel = language.tr("Milagros", "Miracles", "Milagres"),
        miracles = item.recordedMiracles.get(language),
        prayerTitle = language.tr("Oración oficial", "Official prayer", "Oração oficial"),
        prayer = item.officialPrayer.get(language)
    )
}

@Composable
private fun DevotionPage(
    language: AppLanguage,
    nav: DoctrineNav,
    id: String,
    title: String,
    eyebrow: String,
    alias: String?,
    facts: List<Pair<String, String>>,
    historyLabel: String,
    history: String,
    meaningLabel: String?,
    meaning: String?,
    promises: String,
    miraclesLabel: String,
    miracles: String,
    prayerTitle: String,
    prayer: String
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    LaunchedEffect(Unit) { nav.voice.prepare() }
    ReaderPage(
        title = title,
        eyebrow = eyebrow,
        meta = alias,
        pageTag = "devotion_detail_$id"
    ) {
        item(key = "facts") {
            FactsGroup(facts)
            BlockGap(28.dp)
        }
        item(key = "history") {
            SectionHeader(historyLabel, topGap = 0.dp)
            ReadingParagraphs(history)
            if (meaningLabel != null && !meaning.isNullOrBlank()) {
                SectionHeader(meaningLabel, topGap = 28.dp)
                ReadingParagraphs(meaning)
            }
            BlockGap(28.dp)
        }
        if (promises.isNotBlank()) item(key = "promises") {
            NoteCard(language.tr("Promesas", "Promises", "Promessas"), promises, quote = true)
            Spacer(Modifier.height(12.dp))
        }
        if (miracles.isNotBlank()) item(key = "miracles") {
            NoteCard(miraclesLabel, miracles)
            BlockGap(28.dp)
        }
        if (prayer.isNotBlank()) item(key = "prayer") {
            GlassCard {
                Text(prayerTitle, style = styles.headline, color = p.textPrimary, modifier = Modifier.semantics { heading() })
                Spacer(Modifier.height(12.dp))
                Text(
                    prayer,
                    style = styles.reading.copy(fontStyle = FontStyle.Italic),
                    color = p.textPrimary
                )
                Spacer(Modifier.height(16.dp))
                val playing = nav.voice.playing
                AppSecondaryCapsule(
                    label = if (playing) language.tr("Detener", "Stop", "Parar") else language.tr("Escuchar", "Listen", "Ouvir"),
                    icon = if (playing) Icons.Outlined.Stop else Icons.Outlined.VolumeUp,
                    onClick = { if (playing) nav.voice.stop() else nav.voice.speak(prayer) },
                    tag = "devotion_prayer_listen"
                )
            }
        }
    }
}
