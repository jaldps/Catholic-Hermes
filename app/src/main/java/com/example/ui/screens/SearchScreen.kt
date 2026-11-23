package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.search.GlobalSearch
import com.example.data.search.SearchGroup
import com.example.data.search.SearchIndex
import com.example.data.search.SearchIndexHolder
import com.example.data.search.SearchResult
import com.example.data.search.SearchTarget
import com.example.data.search.SearchType
import com.example.ui.design.AppBanner
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppLargeTitleHeader
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppSearchBar
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.appDockInset
import com.example.ui.design.appScreenInsets
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/** Results shown per group before "see all". */
internal const val SearchGroupPreview = 5

/** Wait after the last keystroke before searching. */
internal const val SearchDebounceMillis = 250L

private sealed interface SearchUi {
    data object Idle : SearchUi
    data object Building : SearchUi
    /** [bibleBusy]: the Bible text search is still running, so "no results" is not final yet. */
    data class Ready(val reference: SearchResult?, val groups: List<SearchGroup>, val bibleBusy: Boolean) : SearchUi
}

/**
 * One box that finds prayers, novenas, saints, devotions, orders, the 101 questions, courses, the
 * Catechism and the Bible, grouped by kind. Typing a reference ("Jn 3,16", "Sal 23") offers it first.
 * The index is built once off the main thread; nothing typed is stored.
 */
@Composable
fun SearchScreen(
    language: AppLanguage,
    search: GlobalSearch,
    onOpen: (SearchTarget) -> Unit
) {
    AppEdgeToEdge()
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val focus = LocalFocusManager.current
    fun tr(es: String, en: String, pt: String) = when (language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }

    var query by rememberSaveable { mutableStateOf("") }
    // Groups the reader opened with "see all", as a comma-separated list of type tags.
    var expanded by rememberSaveable { mutableStateOf("") }
    var index by remember { mutableStateOf(SearchIndexHolder.peek(language)) }
    // Start building as soon as the screen opens, while the person is still typing.
    LaunchedEffect(language) { index = search.index(language) }

    val trimmed = query.trim()
    val ui by produceState<SearchUi>(SearchUi.Idle, trimmed, index, language) {
        if (trimmed.length < SearchIndex.MIN_QUERY_LENGTH) { value = SearchUi.Idle; return@produceState }
        delay(SearchDebounceMillis)
        val ready = index
        if (ready == null) { value = SearchUi.Building; return@produceState }
        val (reference, groups) = withContext(Dispatchers.Default) { search.reference(trimmed, language) to ready.search(trimmed) }
        value = SearchUi.Ready(reference, groups, bibleBusy = true)
        val bible = search.bible(trimmed, language)
        value = SearchUi.Ready(reference, groups + listOfNotNull(bible), bibleBusy = false)
    }

    fun groupTitle(type: SearchType) = when (type) {
        SearchType.REFERENCE -> tr("Ir directamente", "Go straight to", "Ir direto para")
        SearchType.PRAYER -> tr("Oraciones", "Prayers", "Orações")
        SearchType.NOVENA -> tr("Novenas", "Novenas", "Novenas")
        SearchType.SAINT -> tr("Santos", "Saints", "Santos")
        SearchType.DEVOTION -> tr("Devociones", "Devotions", "Devoções")
        SearchType.QUESTION -> tr("101 preguntas", "101 Questions", "101 perguntas")
        SearchType.COURSE -> tr("Cursos", "Courses", "Cursos")
        SearchType.CATECHISM -> tr("Catecismo", "Catechism", "Catecismo")
        SearchType.BIBLE -> tr("Biblia", "Bible", "Bíblia")
        SearchType.ORDER -> tr("Órdenes y oficios", "Orders & offices", "Ordens e ofícios")
        SearchType.PLAN -> tr("Planes de lectura", "Reading plans", "Planos de leitura")
        SearchType.SECTION -> tr("Secciones", "Sections", "Seções")
    }

    val gutter = Modifier.padding(horizontal = design.spacing.gutter)

    LazyColumn(
        Modifier.fillMaxSize().padding(bottom = appDockInset()).testTag("search_screen"),
        contentPadding = PaddingValues(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item("header") { AppLargeTitleHeader(tr("Buscar", "Search", "Buscar"), titleTag = "search_title") }
        item("field") {
            AppSearchBar(
                value = query,
                onValueChange = { query = it },
                placeholder = tr("Buscar en toda la app", "Search everything", "Buscar em todo o app"),
                tag = "search_field",
                autoFocus = query.isEmpty(),
                onSubmit = { focus.clearFocus() }
            )
        }
        when (val state = ui) {
            SearchUi.Idle -> item("hint") {
                Text(
                    tr("Oraciones, santos, el Catecismo, la Biblia… o una cita como «Jn 3,16».",
                        "Prayers, saints, the Catechism, the Bible… or a reference like “John 3:16”.",
                        "Orações, santos, o Catecismo, a Bíblia… ou uma citação como «Jo 3,16»."),
                    style = styles.callout, color = p.textSecondary,
                    modifier = gutter.testTag("search_hint")
                )
            }
            SearchUi.Building -> item("building") {
                AppBanner(tr("Preparando la búsqueda…", "Getting search ready…", "Preparando a busca…"), modifier = gutter.testTag("search_loading"))
            }
            is SearchUi.Ready -> {
                val groups = listOfNotNull(state.reference?.let { SearchGroup(SearchType.REFERENCE, 1, listOf(it)) }) + state.groups
                if (groups.isEmpty() && !state.bibleBusy) item("empty") {
                    AppEmptyState(
                        tr("Sin resultados", "No results", "Sem resultados"),
                        tr("Prueba con otra palabra.", "Try another word.", "Tente outra palavra."),
                        icon = Icons.Outlined.Search,
                        modifier = gutter.testTag("search_empty")
                    )
                }
                groups.forEach { group ->
                    item(key = "group_${group.type.tag}") {
                        val open = group.type.tag in expanded.split(',')
                        val shown = if (open) group.results else group.results.take(SearchGroupPreview)
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            val count = if (group.capped) "${group.total}+" else group.total.toString()
                            AppSectionHeader(
                                groupTitle(group.type),
                                if (group.type == SearchType.REFERENCE) null
                                else if (group.total == 1) tr("1 resultado", "1 result", "1 resultado")
                                else tr("$count resultados", "$count results", "$count resultados"),
                                Modifier.testTag("search_group_${group.type.tag}")
                            )
                            AppListGroup {
                                shown.forEach { r ->
                                    AppListRow(
                                        title = r.title,
                                        subtitle = r.snippet ?: r.subtitle,
                                        onClick = { onOpen(r.target) },
                                        modifier = Modifier.testTag("search_result_${group.type.tag}_${r.id}")
                                    )
                                }
                            }
                            if (group.total > SearchGroupPreview) {
                                AppSecondaryButton(
                                    if (open) tr("Ver menos", "Show less", "Ver menos")
                                    else tr("Ver todos (${group.total}${if (group.capped) "+" else ""})", "See all (${group.total}${if (group.capped) "+" else ""})", "Ver todos (${group.total}${if (group.capped) "+" else ""})"),
                                    onClick = {
                                        val tags = expanded.split(',').filter { it.isNotEmpty() }.toMutableList()
                                        if (open) tags.remove(group.type.tag) else tags.add(group.type.tag)
                                        expanded = tags.joinToString(",")
                                    },
                                    modifier = gutter.fillMaxWidth().testTag("search_more_${group.type.tag}")
                                )
                                if (open && group.total > group.results.size) Text(
                                    tr("Se muestran los ${group.results.size} primeros. Prueba con más palabras.",
                                        "Showing the first ${group.results.size}. Try more words.",
                                        "Mostrando os ${group.results.size} primeiros. Tente mais palavras."),
                                    style = styles.footnote, color = p.textSecondary, modifier = gutter
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
