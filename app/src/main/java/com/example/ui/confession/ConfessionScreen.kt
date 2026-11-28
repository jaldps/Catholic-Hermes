package com.example.ui.confession

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.*
import com.example.ui.design.appDockInset
import com.example.ui.design.*
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ConfessionRoute(store: UserPreferencesStore, language: AppLanguage, active: Boolean) {
    val prefs by store.preferences.collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    var saveFailed by remember { mutableStateOf(false) }
    ConfessionScreen(language, prefs?.confessionProfileId ?: "single", active = active,
        profileSaveFailed = saveFailed, onProfileChoice = { id -> scope.launch {
            try { store.setConfessionProfile(id); saveFailed = false }
            catch (e: CancellationException) { throw e }
            catch (_: Exception) { saveFailed = true }
        } })
}

@Composable
fun ConfessionScreen(
    language: AppLanguage,
    initialProfileId: String = "single",
    onProfileChoice: (String) -> Unit = {},
    active: Boolean = true,
    profileSaveFailed: Boolean = false
) {
    AppEdgeToEdge()
    fun tr(es: String, en: String, pt: String) = when (language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }
    val catalog = remember { ConfessionCatalog.default }
    var retry by remember { mutableIntStateOf(0) }
    var loading by remember { mutableStateOf(true) }
    var content by remember { mutableStateOf<ConfessionContent?>(null) }
    LaunchedEffect(catalog, retry) {
        loading = true
        content = withContext(Dispatchers.IO) { catalog.load() }
        loading = false
    }
    val draft = rememberSaveable(saver = ConfessionDraft.Saver) { ConfessionDraft() }
    DisposableEffect(draft) { onDispose { draft.clear() } }
    LaunchedEffect(active) { if (!active) draft.clear() }
    var profileId by rememberSaveable { mutableStateOf(initialProfileId) }
    LaunchedEffect(initialProfileId) { profileId = initialProfileId }
    var section by rememberSaveable { mutableIntStateOf(0) }
    var expanded by rememberSaveable(profileId) { mutableStateOf<String?>(null) }
    var chooseProfile by remember { mutableStateOf(false) }
    var privacyAcknowledged by rememberSaveable { mutableStateOf(false) }
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val data = content
    val profile = data?.profiles?.firstOrNull { it.id == profileId } ?: data?.profiles?.firstOrNull()
    val questions = remember(data) { data?.questions.orEmpty() }
    val selected = questions.filter { it.id in draft.selected }
    val gutter = Modifier.padding(horizontal = design.spacing.gutter)
    val focus = LocalFocusManager.current
    fun basis(text: String) = if (language == AppLanguage.EN) text.replaceFirst("CIC", "CCC") else text

    if (appIsLandscapePhone()) {
        AppPageLayout(
            kind = AppPageKind.Immersive,
            header = { AppHeroHeader("heroes/lumen/spiritual.jpg", tr("Confesión", "Confession", "Confissão")) },
            supporting = {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Column(Modifier.weight(1f).testTag("confession_tabs")) {
                        listOf(
                            tr("Prepararme", "Prepare", "Preparar-me"),
                            tr("Mi lista", "My list", "Minha lista"),
                            tr("El sacramento", "The sacrament", "O sacramento")
                        ).forEachIndexed { i, label ->
                            AppSecondaryButton(label, { section = i }, modifier = Modifier.fillMaxWidth())
                        }
                    }
                    if (section == 0) {
                        Column(Modifier.weight(2f)) {
                            if (!privacyAcknowledged) AppBanner(
                                tr("Una lista temporal", "A temporary list", "Uma lista temporária"),
                                tr("Tu lista y tus notas se borran al salir; nunca se guardan en disco.",
                                    "Your list and notes are erased on exit; never written to disk.",
                                    "Sua lista e notas são apagadas ao sair; nunca gravadas em disco."),
                                modifier = Modifier.testTag("confession_privacy_notice")
                            )
                            Column(Modifier.testTag("confession_examen")) {
                                AppSecondaryButton(tr("Elegir perfil", "Choose profile", "Escolher perfil"),
                                    { chooseProfile = true }, modifier = gutter.fillMaxWidth().testTag("confession_profile_button"))
                                profile?.let { Text(it.title.get(language), style = styles.headline, color = p.textPrimary,
                                    modifier = gutter.testTag("confession_profile_title")) }
                            }
                        }
                        Column(Modifier.weight(2f).testTag("confession_private_list")) {
                            if (selected.isEmpty()) AppEmptyState(tr("Tu lista está vacía", "Your list is empty", "Sua lista está vazia"),
                                tr("Marca preguntas en Prepararme.", "Mark questions in Prepare.", "Marque perguntas em Preparar-me."),
                                modifier = Modifier.testTag("confession_empty_list"))
                            else selected.forEach { q -> AppCheckRow(q.text.get(language), true, { draft.toggle(q.id) }) }
                            AppNotesField(draft.notes, { draft.notes = it },
                                tr("Notas personales (opcionales)", "Personal notes (optional)", "Notas pessoais (opcionais)"),
                                Modifier.testTag("confession_notes"))
                        }
                    }
                }
            },
            content = { Box(Modifier.fillMaxSize()) }
        )
        return
    }

    Column(Modifier.fillMaxSize().padding(bottom = appDockInset()).imePadding()) {
    LazyColumn(
        Modifier.weight(1f).fillMaxWidth().testTag("confession_screen"),
        contentPadding = PaddingValues(bottom = 32.dp), verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item("header") { AppHeroHeader("heroes/lumen/spiritual.jpg", tr("Confesión", "Confession", "Confissão"),
            eyebrow = tr("Un encuentro con la misericordia", "An encounter with mercy", "Um encontro com a misericórdia")) }
        item("tabs") { AppSegmentedTabs(
            listOf(tr("Prepararme", "Prepare", "Preparar-me"), tr("Mi lista", "My list", "Minha lista"), tr("El sacramento", "The sacrament", "O sacramento")),
            section, { section = it }, Modifier.testTag("confession_tabs")) }
        if (!privacyAcknowledged) item("privacy") {
            Column {
                AppBanner(tr("Una lista temporal", "A temporary list", "Uma lista temporária"),
                    tr("Solo el perfil se recuerda. Tu lista y tus notas se borran al salir o reiniciar esta pantalla; no se guardan ni se incluyen en copias de seguridad.",
                        "Only your profile is remembered. Your list and notes are erased when you leave or restart this screen; they are never saved or included in backups.",
                        "Apenas o perfil é lembrado. Sua lista e suas notas são apagadas ao sair ou reiniciar esta tela; não são salvas nem incluídas em backups."), modifier = Modifier.testTag("confession_privacy_notice"))
                AppSecondaryButton(tr("Entendido", "Got it", "Entendi"), { privacyAcknowledged = true }, modifier = gutter.fillMaxWidth())
            }
        }
        if (loading) item("loading") { AppBanner(tr("Cargando…", "Loading…", "Carregando…")) }
        else if (data == null || profile == null) item("failure") {
            Column {
                AppEmptyState(tr("No se pudo abrir el contenido", "Content could not be opened", "Não foi possível abrir o conteúdo"))
                AppSecondaryButton(tr("Reintentar", "Try again", "Tentar novamente"), { retry++ }, modifier = gutter.fillMaxWidth())
            }
        } else when (section) {
            0 -> {
                item("profile") { Column(Modifier.testTag("confession_examen"), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    AppSecondaryButton(tr("Elegir perfil", "Choose profile", "Escolher perfil"), { chooseProfile = true }, modifier = gutter.fillMaxWidth().testTag("confession_profile_button"))
                    Text(profile.title.get(language), style = styles.headline, color = p.textPrimary, modifier = gutter.testTag("confession_profile_title"))
                    Text(profile.intro.get(language), style = styles.body, color = p.textSecondary, modifier = gutter)
                    if (profileSaveFailed) AppBanner(tr("No se pudo recordar el perfil", "Could not remember the profile", "Não foi possível lembrar o perfil"))
                } }
                items(profile.sections, key = { "examen_${profile.id}_${it.id}" }) { part ->
                    Column {
                        AppListGroup {
                            AppListRow(part.title.get(language), basis(part.basis), selected = expanded == part.id,
                                trailingText = if (expanded == part.id) "−" else "+",
                                showsNavigationAffordance = false, onClick = { expanded = if (expanded == part.id) null else part.id },
                                modifier = Modifier.testTag("confession_section_${part.id}"))
                            if (expanded == part.id) part.questions.forEachIndexed { index, question ->
                                val id = "${profile.id}:${part.id}:$index"
                                AppCheckRow(question.get(language), id in draft.selected, { draft.toggle(id) },
                                    checkTag = "confession_question_$id", showDivider = index != part.questions.lastIndex)
                            }
                        }
                    }
                }
                item("review") { AppPrimaryButton(tr("Ver mi lista", "View my list", "Ver minha lista"), { section = 1 },
                    modifier = gutter.fillMaxWidth().testTag("confession_review_list")) }
            }
            1 -> {
                item("list_title") { AppSectionHeader(tr("Mi lista", "My list", "Minha lista"),
                    tr("Para conversar con el sacerdote", "To discuss with the priest", "Para conversar com o sacerdote"), Modifier.testTag("confession_private_list")) }
                if (selected.isEmpty()) item("empty") { AppEmptyState(tr("Tu lista está vacía", "Your list is empty", "Sua lista está vazia"),
                    tr("Marca las preguntas que quieras recordar en Prepararme.", "Mark questions you want to remember in Prepare.", "Marque as perguntas que deseja lembrar em Preparar-me."), modifier = Modifier.testTag("confession_empty_list")) }
                items(selected, key = { "private_${it.id}" }) { question ->
                    Column { AppListGroup { AppCheckRow(question.text.get(language), true, { draft.toggle(question.id) },
                        subtitle = "${question.profile.get(language)} · ${basis(question.basis)}", tag = "confession_selected_${question.id}",
                        checkTag = "confession_remove_${question.id}", showDivider = false) } }
                }
                item("notes") { Column(gutter) { AppNotesField(draft.notes, { draft.notes = it },
                    tr("Notas personales (opcionales)", "Personal notes (optional)", "Notas pessoais (opcionais)"), Modifier.testTag("confession_notes")) } }
            }
            else -> {
                item("guide_title") { AppSectionHeader(tr("El sacramento", "The sacrament", "O sacramento"), modifier = Modifier.testTag("confession_guide")) }
                items(data.guide.steps, key = { "step_${it.id}" }) { step -> Column(gutter) { AppCard {
                    Text("${data.guide.steps.indexOf(step) + 1}. ${step.title.get(language)}", style = styles.headline, color = p.textPrimary, modifier = Modifier.semantics { heading() })
                    Text(step.body.get(language), style = styles.body, color = p.textPrimary)
                    Text(basis(step.basis), style = styles.footnote, color = p.textSecondary)
                } } }
                item("contrition_title") { AppSectionHeader(tr("Actos de contrición", "Acts of contrition", "Atos de contrição")) }
                items(data.guide.contrition, key = { "prayer_${it.id}" }) { prayer -> Column(gutter) { AppCard(Modifier.testTag("confession_prayer_${prayer.id}")) {
                    Text(prayer.title.get(language), style = styles.headline, color = p.textPrimary)
                    Text(prayer.text.get(language), style = styles.body, color = p.textPrimary)
                    Text(tr("Fuente: ", "Source: ", "Fonte: ") + prayer.source.get(language), style = styles.footnote, color = p.textSecondary, modifier = Modifier.testTag("confession_prayer_source_${prayer.id}"))
                } } }
                item("faq_title") { AppSectionHeader(tr("Preguntas frecuentes", "Frequently asked questions", "Perguntas frequentes")) }
                items(data.guide.faq, key = { "faq_${it.id}" }) { faq -> Column(gutter) { AppCard {
                    Text(faq.question.get(language), style = styles.headline, color = p.textPrimary)
                    Text(faq.answer.get(language), style = styles.body, color = p.textPrimary)
                    Text(basis(faq.basis), style = styles.footnote, color = p.textSecondary)
                } } }
            }
        }
    }
    if (section == 1 && data != null) AppPrimaryButton(
        tr("Terminar y borrar", "Finish and erase", "Terminar e apagar"),
        { focus.clearFocus(); draft.clear() },
        modifier = gutter.padding(vertical = 16.dp).fillMaxWidth().testTag("confession_finish_erase")
    )
    }
    if (chooseProfile && data != null) AppSheet({ chooseProfile = false }, title = tr("Elegir perfil", "Choose profile", "Escolher perfil"), tag = "confession_profile_sheet") {
        Column(Modifier.fillMaxWidth().padding(vertical = 8.dp)) { AppListGroup { data.profiles.forEach { choice ->
            AppListRow(choice.title.get(language), selected = choice.id == profile?.id, onClick = {
                profileId = choice.id; onProfileChoice(choice.id); chooseProfile = false
            }, modifier = Modifier.testTag("confession_profile_${choice.id}"))
        } } }
    }
}
