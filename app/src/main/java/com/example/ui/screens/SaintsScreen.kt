package com.example.ui.screens

import com.example.data.repository.DevotionImageCredits
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Stop
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import com.example.data.audio.LiturgicalTtsManager
import com.example.ui.design.AppSecondaryCapsule
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.clearAndSetSemantics
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.appScreenInsets
import com.example.ui.design.appDockInset
import com.example.ui.design.AppHeroHeader
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.data.liturgy.GeneralCelebration
import com.example.data.liturgy.GeneralRomanSanctoral
import com.example.data.models.AppLanguage
import com.example.data.models.SaintProfile
import com.example.data.repository.SaintsCatalog
import com.example.ui.design.AppCard
import com.example.ui.design.AppTwoPane
import com.example.ui.design.rememberAppListDetailState
import com.example.ui.design.AppSheet
import com.example.ui.design.appIsTwoPane
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppMenuChip
import com.example.ui.design.AppMenuItem
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.text.Normalizer

private val monthsEs = listOf("enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre")
private val monthsEn = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
private val monthsPt = listOf("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro")

internal fun saintMonthName(month: Int, language: AppLanguage): String {
    val name = when (language) { AppLanguage.ES -> monthsEs; AppLanguage.EN -> monthsEn; AppLanguage.PT -> monthsPt }[month - 1]
    return name.replaceFirstChar { it.uppercase() }
}

internal fun saintDateLabel(month: Int, day: Int, language: AppLanguage): String = when (language) {
    AppLanguage.ES -> "$day de ${monthsEs[month - 1]}"
    AppLanguage.EN -> "${monthsEn[month - 1]} $day"
    AppLanguage.PT -> "$day de ${monthsPt[month - 1]}"
}

private fun fold(s: String): String =
    Normalizer.normalize(s, Normalizer.Form.NFD).replace(Regex("\\p{M}+"), "").lowercase()

/** Non-Lord celebrations of the General Roman Calendar, in calendar order. */
internal fun saintCelebrations(): List<GeneralCelebration> =
    GeneralRomanSanctoral.fixed.filter { !it.isLordFeast }.sortedWith(compareBy({ it.month }, { it.day }, { it.rank.ordinal }))

/**
 * Saints of the General Roman Calendar. Every celebration is listed; those
 * with an editorial profile open a detail page, the others show title and date.
 */
@Composable
fun SaintsScreen(
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    ttsManager: LiturgicalTtsManager? = null,
    initialKey: String? = null,
    onExitContext: (() -> Unit)? = null
) {
    val catalog = remember { SaintsCatalog.default }
    val profiles = catalog.byKey
    val all = remember { saintCelebrations() }
    val keys = remember(all, profiles) { all.filter { it.key in profiles }.map { it.key } }
    val state = rememberAppListDetailState(keys, initialKey, key = "saints_list_detail")
    var query by rememberSaveable(key = "saints_query") { mutableStateOf("") }
    var month by rememberSaveable(key = "saints_month") { mutableIntStateOf(0) }
    var profilesOnly by rememberSaveable(key = "saints_profiles_only") { mutableStateOf(false) }
    var filtersOpen by rememberSaveable(key = "saints_filters_open") { mutableStateOf(false) }
    fun tr(es: String, en: String, pt: String) = when (currentLanguage) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }

    if (onExitContext != null && state.explicit && state.selectedKey == initialKey) AppBackHandler { onExitContext() }
    AppTwoPane(
        state = state,
        modifier = modifier,
        listHeader = {
            AppHeroHeader(
                assetPath = "heroes/lumen/saints.jpg",
                title = tr("Santos", "Saints", "Santos"),
                eyebrow = tr("Calendario Romano General", "General Roman Calendar", "Calendário Romano Geral")
            )
        },
        search = {
            AppSearchField(
                value = query,
                onValueChange = { query = it },
                placeholder = tr("Buscar un santo", "Search for a saint", "Buscar um santo"),
                modifier = Modifier.fillMaxWidth().testTag("saints_search")
            )
        },
        onOpenFilters = { filtersOpen = true },
        filterLabel = tr("Filtros", "Filters", "Filtros"),
        activeFilters = {
            if (month != 0) AppChip(label = saintMonthName(month, currentLanguage), selected = true, onClick = { month = 0 }, modifier = Modifier.testTag("saints_month_chip"))
            if (profilesOnly) AppChip(
                label = tr("Solo con ficha", "With profile only", "Só com ficha"),
                selected = true,
                onClick = { profilesOnly = false },
                modifier = Modifier.testTag("saints_profiles_chip")
            )
        },
        list = { headerItem ->
            val folded = fold(query.trim())
            val visible = remember(folded, month, profilesOnly, currentLanguage, profiles, all) {
                all.filter { c ->
                    (month == 0 || c.month == month) &&
                        (!profilesOnly || c.key in profiles) &&
                        (folded.isEmpty() || fold(c.title.get(currentLanguage)).contains(folded) ||
                            profiles[c.key]?.let { fold(it.bio.get(currentLanguage)).contains(folded) } == true)
                }
            }
            val gutter = LocalAppDesign.current.spacing.gutter
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(bottom = appDockInset()).testTag("saints_screen"),
                contentPadding = PaddingValues(bottom = 32.dp)
            ) {
                item(key = "title") { headerItem(); Spacer(Modifier.height(6.dp)) }
                if (visible.isEmpty()) {
                    item(key = "empty") {
                        Column(
                            Modifier.fillMaxWidth().padding(horizontal = gutter, vertical = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            AppEmptyState(
                                title = tr("Nada coincide", "Nothing matches", "Nada corresponde"),
                                body = tr("Prueba con otra búsqueda o quita los filtros.", "Try another search or clear the filters.", "Tente outra busca ou limpe os filtros."),
                                modifier = Modifier.testTag("list_empty")
                            )
                            AppSecondaryCapsule(
                                label = tr("Quitar filtros", "Clear filters", "Limpar filtros"),
                                onClick = { query = ""; month = 0; profilesOnly = false },
                                tag = "saints_clear_filters"
                            )
                        }
                    }
                } else {
                    visible.groupBy { it.month }.forEach { (m, entries) ->
                        item(key = "month_$m") {
                            AppSectionHeader(title = saintMonthName(m, currentLanguage), modifier = Modifier.testTag("saints_month_header_$m"))
                        }
                        item(key = "group_$m") {
                            AppListGroup {
                                entries.forEach { c ->
                                    val has = c.key in profiles
                                    AppListRow(
                                        title = c.title.get(currentLanguage),
                                        subtitle = saintDateLabel(c.month, c.day, currentLanguage) + " · " + c.rank.displayName.get(currentLanguage),
                                        showsNavigationAffordance = has,
                                        selected = c.key == state.selectedKey,
                                        onClick = if (has) ({ state.select(c.key) }) else null,
                                        modifier = Modifier.testTag("saint_row_${c.key}")
                                            .then(if (state.selectedKey != null) Modifier.semantics { selected = c.key == state.selectedKey } else Modifier)
                                    )
                                }
                            }
                            Spacer(Modifier.height(LocalAppDesign.current.spacing.cardGap))
                        }
                    }
                }
            }
        },
        detail = { key ->
            val celebration = all.firstOrNull { it.key == key }
            if (celebration != null) {
                androidx.compose.runtime.key(celebration.key) {
                    SaintDetail(celebration, profiles[celebration.key], currentLanguage, { state.showList() }, Modifier, ttsManager, showBack = !appIsTwoPane())
                }
            } else {
                AppEmptyState(
                    title = tr("Selecciona un santo", "Select a saint", "Selecione um santo"),
                    body = tr("Elige una entrada de la lista para ver su ficha.", "Choose an entry from the list to see its profile.", "Escolha uma entrada da lista para ver o perfil."),
                    modifier = Modifier.testTag("list_empty").padding(16.dp)
                )
            }
        }
    )
    if (filtersOpen) {
        AppSheet(
            onDismiss = { filtersOpen = false },
            title = tr("Filtros", "Filters", "Filtros"),
            tag = "saints_filter_sheet"
        ) {
            AppMenuChip(
                label = if (month == 0) tr("Todos los meses", "All months", "Todos os meses") else saintMonthName(month, currentLanguage),
                tag = "saints_month_picker",
                items = listOf(AppMenuItem(tr("Todos los meses", "All months", "Todos os meses"), selected = month == 0, tag = "saints_month_0") { month = 0 }) +
                    (1..12).map { m -> AppMenuItem(saintMonthName(m, currentLanguage), selected = month == m, tag = "saints_month_$m") { month = m } }
            )
            if (profiles.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                AppChip(
                    label = tr("Solo con ficha", "With profile only", "Só com ficha"),
                    selected = profilesOnly,
                    onClick = { profilesOnly = !profilesOnly },
                    modifier = Modifier.testTag("saints_profiles_only")
                )
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun SaintDetail(
    celebration: GeneralCelebration,
    profile: SaintProfile?,
    language: AppLanguage,
    onBack: () -> Unit,
    modifier: Modifier,
    ttsManager: LiturgicalTtsManager?,
    /** False in the two-pane layout, where the list is always on screen and there is nothing to go back to. */
    showBack: Boolean = true
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val palette = design.palette
    fun tr(es: String, en: String, pt: String) = when (language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }
    val bio = profile?.bio?.get(language).orEmpty()
    val patronages = profile?.patronages?.get(language).orEmpty()
    val quote = profile?.quote
    val quoteText = quote?.text?.get(language).orEmpty()

    // Read aloud: the name, then the biography, through the shared narrator.
    val saintTitle = celebration.title.get(language)
    val speakText = if (bio.isBlank()) "" else "$saintTitle.\n\n$bio"
    val narrating by (ttsManager?.currentlySpeaking ?: remember { kotlinx.coroutines.flow.MutableStateFlow<String?>(null) }).collectAsState()
    val listening = speakText.isNotEmpty() && narrating == speakText
    DisposableEffect(celebration.key, ttsManager) { onDispose { if (ttsManager != null && narrating == speakText) ttsManager.stop() } }

    val gutter = design.spacing.gutter
    val card = Modifier.fillMaxWidth().padding(horizontal = gutter).widthIn(max = design.spacing.readingMaxWidth + gutter * 2)
    val context = LocalContext.current
    val images = remember(context) { DevotionImageCredits.install(context); DevotionImageCredits.saints }
    val imagePath = remember(images, celebration.key) { images.imagePath(celebration.key) }
    val credit = remember(images, imagePath) { imagePath?.let { images.credit(celebration.key) } }
    LazyColumn(
        modifier = modifier.fillMaxSize().testTag("saint_detail"),
        contentPadding = PaddingValues(bottom = appDockInset() + 32.dp),
        verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
    ) {
        item {
            AppDetailHeader(
                title = celebration.title.get(language),
                assetPath = imagePath ?: "heroes/lumen/saints.jpg",
                eyebrow = saintDateLabel(celebration.month, celebration.day, language) + " · " + celebration.rank.displayName.get(language)
            )
            if (credit != null) {
                val uriHandler = LocalUriHandler.current
                val url = credit.safeUrl
                Text(
                    imageCreditLine(credit, language), style = styles.footnote, color = palette.textSecondary,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = gutter).padding(top = 4.dp, bottom = 6.dp)
                        .then(if (url != null) Modifier.clickable(role = Role.Button) { runCatching { uriHandler.openUri(url) } } else Modifier)
                        .testTag("saint_image_credit")
                )
            }
            if (showBack) Row(Modifier.padding(horizontal = gutter).padding(top = 4.dp)) {
                AppSecondaryButton(tr("Todos los santos", "All saints", "Todos os santos"), onBack, leadingIcon = Icons.AutoMirrored.Outlined.ArrowBack, modifier = Modifier.testTag("saint_detail_back"))
            }
            if (ttsManager != null && speakText.isNotEmpty()) {
                Row(Modifier.padding(horizontal = gutter).padding(top = 10.dp)) {
                    AppSecondaryCapsule(
                        label = if (listening) tr("Detener", "Stop", "Parar") else tr("Escuchar", "Listen", "Ouvir"),
                        icon = if (listening) Icons.Outlined.Stop else Icons.Outlined.VolumeUp,
                        onClick = {
                            if (listening) ttsManager.stop()
                            else { ttsManager.setLanguage(language); ttsManager.speakSingle(speakText, title = saintTitle) }
                        },
                        tag = "saint_listen_button"
                    )
                }
            }
        }
        if (bio.isNotBlank()) item {
            AppCard(card) { Text(bio, style = styles.body, color = palette.textPrimary) }
        }
        if (patronages.isNotEmpty()) item {
            AppCard(card) {
                Text(tr("Patronazgos", "Patronage", "Padroados"), style = styles.headline, color = palette.textPrimary, modifier = Modifier.semantics { heading() })
                Spacer(Modifier.height(6.dp))
                Text(patronages.joinToString(" · "), style = styles.callout, color = palette.textSecondary, modifier = Modifier.testTag("saint_patronages"))
            }
        }
        if (quote != null && quoteText.isNotBlank()) item {
            AppCard(card.testTag("saint_quote")) {
                Text("\u201C", style = styles.display, color = palette.accent, modifier = Modifier.height(34.dp).clearAndSetSemantics { })
                Text(quoteText, style = styles.quote, color = palette.textPrimary)
                Spacer(Modifier.height(10.dp))
                Text(quote.source, style = styles.footnote, color = palette.textSecondary)
            }
        }
        val sources = profile?.sources.orEmpty()
        if (sources.isNotEmpty()) item {
            Column(Modifier.padding(horizontal = gutter + 4.dp).padding(top = 6.dp).widthIn(max = design.spacing.readingMaxWidth)) {
                Text(tr("Fuentes", "Sources", "Fontes"), style = styles.eyebrow, color = palette.textSecondary, modifier = Modifier.semantics { heading() })
                Spacer(Modifier.height(6.dp))
                Column(Modifier.testTag("saint_sources"), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    sources.forEach { Text(it, style = styles.footnote, color = palette.textSecondary) }
                }
            }
        }
    }
}
