package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.PlanProgress
import com.example.data.repository.PlanProgressMath
import com.example.data.repository.ReadingPlan
import com.example.data.repository.ReadingPlanLinks
import com.example.data.repository.ReadingPlansCatalog
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppBanner
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppConfirmDialog
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppHeroHeader
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.appScreenInsets
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** Wires the screen to the stored progress, the Bible edition being read and the app's two reader destinations. */
@Composable
fun ReadingPlansRoute(
    store: UserPreferencesStore,
    repository: CatholicDataRepository,
    language: AppLanguage,
    onOpenBible: (bookId: String, chapter: Int, verseStart: Int?) -> Unit,
    onOpenCatechism: (paragraph: Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val progress by store.planProgress.collectAsState(initial = emptyMap())
    val date by repository.currentDate.collectAsState()
    val activeEdition by repository.activeBibleEditionId.collectAsState()
    val edition = repository.resolvedEditionId(activeEdition, language)
    fun save(block: suspend () -> Unit) { scope.launch {
        try { block() } catch (e: CancellationException) { throw e } catch (_: Exception) { /* the preferences recovery sheet reports failed writes */ }
    } }
    ReadingPlansScreen(
        language = language,
        progress = progress,
        todayEpochDay = date.toEpochDay(),
        bibleLabel = { raw -> repository.studyScriptureLabel(raw, language, edition) },
        onStart = { id -> save { store.setPlanStart(id, date.toEpochDay()) } },
        onToggleDone = { id, day, done -> save { store.setPlanDayDone(id, day, done) } },
        onRebase = { id, total -> save { store.rebasePlan(id, date.toEpochDay(), total) } },
        onRestart = { id -> save { store.restartPlan(id) } },
        onOpenBibleReading = { raw -> ReadingPlanLinks.bibleTarget(raw, edition)?.let { onOpenBible(it.bookId, it.chapter, it.verse) } },
        onOpenCatechism = onOpenCatechism
    )
}

@Composable
fun ReadingPlansScreen(
    language: AppLanguage,
    progress: Map<String, PlanProgress>,
    todayEpochDay: Long,
    bibleLabel: (String) -> String,
    onStart: (planId: String) -> Unit,
    onToggleDone: (planId: String, day: Int, done: Boolean) -> Unit,
    onRebase: (planId: String, totalDays: Int) -> Unit,
    onRestart: (planId: String) -> Unit,
    onOpenBibleReading: (raw: String) -> Unit,
    onOpenCatechism: (paragraph: Int) -> Unit
) {
    AppEdgeToEdge()
    fun tr(es: String, en: String, pt: String) = when (language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }
    val catalog = remember { ReadingPlansCatalog.default }
    var retry by remember { mutableIntStateOf(0) }
    var loading by remember { mutableStateOf(true) }
    var plans by remember { mutableStateOf<List<ReadingPlan>?>(null) }
    LaunchedEffect(catalog, retry) {
        loading = true
        plans = withContext(Dispatchers.IO) { catalog.load() }
        loading = false
    }
    var selectedId by rememberSaveable { mutableStateOf<String?>(null) }
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val gutter = Modifier.padding(horizontal = design.spacing.gutter)
    val loaded = plans
    val selected = loaded?.firstOrNull { it.id == selectedId }
    AppBackHandler(enabled = selected != null) { selectedId = null }
    val title = tr("Planes de lectura", "Reading plans", "Planos de leitura")

    fun progressLine(plan: ReadingPlan): String {
        val state = progress[plan.id]
        val start = state?.startEpochDay
            ?: return tr("Sin empezar · ${plan.totalDays} días", "Not started · ${plan.totalDays} days", "Não iniciado · ${plan.totalDays} dias")
        val day = PlanProgressMath.dayIndex(start, todayEpochDay, plan.totalDays)
        val percent = PlanProgressMath.percent(state.done, plan.totalDays)
        return tr("Día $day de ${plan.totalDays} · $percent %", "Day $day of ${plan.totalDays} · $percent%", "Dia $day de ${plan.totalDays} · $percent %")
    }

    Column(Modifier.fillMaxSize().padding(bottom = appScreenInsets().calculateBottomPadding())) {
        if (selected == null) {
            LazyColumn(
                Modifier.weight(1f).fillMaxWidth().testTag("plans_screen"),
                contentPadding = PaddingValues(bottom = 32.dp), verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item("header") { AppHeroHeader("heroes/lumen/bible.jpg", title,
                    eyebrow = tr("Un día a la vez", "One day at a time", "Um dia de cada vez")) }
                if (loading) item("loading") { AppBanner(tr("Cargando…", "Loading…", "Carregando…")) }
                else if (loaded == null) item("failure") {
                    Column {
                        AppEmptyState(tr("No se pudo abrir el contenido", "Content could not be opened", "Não foi possível abrir o conteúdo"))
                        AppSecondaryButton(tr("Reintentar", "Try again", "Tentar novamente"), { retry++ }, modifier = gutter.fillMaxWidth())
                    }
                } else items(loaded, key = { it.id }) { plan ->
                    val state = progress[plan.id]
                    AppCard(onClick = { selectedId = plan.id }, modifier = Modifier.padding(horizontal = design.spacing.gutter).testTag("plan_card_${plan.id}")) {
                        Text(plan.title.get(language), style = styles.headline, color = p.textPrimary, modifier = Modifier.semantics { heading() })
                        Spacer(Modifier.height(6.dp))
                        Text(plan.description.get(language), style = styles.body, color = p.textSecondary)
                        Spacer(Modifier.height(10.dp))
                        Text(progressLine(plan), style = styles.subhead, color = p.textPrimary, modifier = Modifier.testTag("plan_progress_${plan.id}"))
                        if (state?.started == true) {
                            Spacer(Modifier.height(8.dp))
                            AppProgressBar(PlanProgressMath.percent(state.done, plan.totalDays) / 100f)
                        }
                    }
                }
            }
        } else {
            PlanDetail(
                plan = selected, state = progress[selected.id] ?: PlanProgress(), todayEpochDay = todayEpochDay, language = language,
                progressLine = progressLine(selected), bibleLabel = bibleLabel, eyebrow = title,
                onStart = { onStart(selected.id) },
                onToggleDone = { day, done -> onToggleDone(selected.id, day, done) },
                onRebase = { onRebase(selected.id, selected.totalDays) },
                onRestart = { onRestart(selected.id) },
                onOpenBibleReading = onOpenBibleReading, onOpenCatechism = onOpenCatechism
            )
        }
    }
}

@Composable
private fun PlanDetail(
    plan: ReadingPlan,
    state: PlanProgress,
    todayEpochDay: Long,
    language: AppLanguage,
    progressLine: String,
    bibleLabel: (String) -> String,
    eyebrow: String,
    onStart: () -> Unit,
    onToggleDone: (day: Int, done: Boolean) -> Unit,
    onRebase: () -> Unit,
    onRestart: () -> Unit,
    onOpenBibleReading: (String) -> Unit,
    onOpenCatechism: (Int) -> Unit
) {
    fun tr(es: String, en: String, pt: String) = when (language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val gutter = Modifier.padding(horizontal = design.spacing.gutter)
    val total = plan.totalDays
    val start = state.startEpochDay
    val today = if (start == null) 1 else PlanProgressMath.dayIndex(start, todayEpochDay, total)
    val pending = if (start == null) emptyList() else PlanProgressMath.pending(state.done, today)
    // 0 = follow today; otherwise the day the reader picked.
    var picked by rememberSaveable(plan.id) { mutableIntStateOf(0) }
    val viewDay = if (picked in 1..total) picked else today
    var confirmRestart by rememberSaveable(plan.id) { mutableStateOf(false) }
    val day = plan.day(viewDay)
    val isDone = viewDay in state.done
    val chips = rememberLazyListState(initialFirstVisibleItemIndex = (viewDay - 2).coerceAtLeast(0))
    LaunchedEffect(viewDay) { chips.animateScrollToItem((viewDay - 2).coerceAtLeast(0)) }
    val english = language == AppLanguage.EN

    LazyColumn(
        Modifier.fillMaxSize().testTag("plan_detail_screen"),
        contentPadding = PaddingValues(bottom = 32.dp), verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item("header") { AppDetailHeader(plan.title.get(language),
            assetPath = if (plan.isCatechism) "heroes/lumen/catechism.jpg" else "heroes/lumen/bible.jpg", eyebrow = eyebrow) }
        if (start == null) {
            item("intro") { Column(gutter, verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text(plan.description.get(language), style = styles.body, color = p.textSecondary)
                AppPrimaryButton(tr("Empezar hoy", "Start today", "Começar hoje"), onStart, modifier = Modifier.fillMaxWidth().testTag("plan_start"))
            } }
        } else {
            item("progress") { Column(gutter, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(progressLine, style = styles.subhead, color = p.textPrimary, modifier = Modifier.testTag("plan_progress"))
                AppProgressBar(PlanProgressMath.percent(state.done, total) / 100f)
            } }
            if (pending.isNotEmpty()) item("catch_up") { Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                val n = pending.size
                AppBanner(
                    tr(if (n == 1) "Llevas 1 día pendiente" else "Llevas $n días pendientes",
                        if (n == 1) "You have 1 day to catch up on" else "You have $n days to catch up on",
                        if (n == 1) "Você tem 1 dia pendente" else "Você tem $n dias pendentes"),
                    tr("Puedes leerlos a tu ritmo o continuar desde hoy.", "Read them at your own pace, or carry on from today.",
                        "Você pode lê-los no seu ritmo ou continuar a partir de hoje."),
                    modifier = Modifier.testTag("plan_catch_up")
                )
                AppSecondaryButton(tr("Ver pendientes", "See pending days", "Ver pendentes"), { picked = pending.first() },
                    modifier = gutter.fillMaxWidth().testTag("plan_view_pending"))
                AppSecondaryButton(tr("Reajustar el plan", "Adjust the plan", "Reajustar o plano"), { picked = 0; onRebase() },
                    modifier = gutter.fillMaxWidth().testTag("plan_rebase"))
            } }
            else if (state.done.size >= total) item("complete") {
                AppBanner(tr("Plan completado", "Plan completed", "Plano concluído"),
                    tr("Has leído los ${total} días. Gracias a Dios.", "You have read all $total days. Thanks be to God.", "Você leu os $total dias. Graças a Deus."))
            }
            item("day_title") { AppSectionHeader(
                if (viewDay == today) tr("Hoy · Día $viewDay", "Today · Day $viewDay", "Hoje · Dia $viewDay") else tr("Día $viewDay", "Day $viewDay", "Dia $viewDay"),
                if (isDone) tr("Leído", "Read", "Lido") else null, Modifier.testTag("plan_day_title")) }
            if (day != null) item("readings") {
                AppListGroup {
                    if (day.paragraphs != null) {
                        AppListRow(ReadingPlanLinks.catechismLabel(day.paragraphs, english),
                            subtitle = tr("Catecismo de la Iglesia Católica", "Catechism of the Catholic Church", "Catecismo da Igreja Católica"),
                            onClick = { onOpenCatechism(day.paragraphs.first) }, modifier = Modifier.testTag("plan_reading_0"))
                    } else day.readings.forEachIndexed { index, raw ->
                        AppListRow(bibleLabel(raw), onClick = { onOpenBibleReading(raw) }, modifier = Modifier.testTag("plan_reading_$index"))
                    }
                }
            }
            item("mark") {
                if (isDone) AppSecondaryButton(tr("Leído · Deshacer", "Read · Undo", "Lido · Desfazer"), { onToggleDone(viewDay, false) },
                    modifier = gutter.fillMaxWidth().testTag("plan_mark_done"))
                else AppPrimaryButton(tr("Marcar como leído", "Mark as read", "Marcar como lido"), { onToggleDone(viewDay, true) },
                    modifier = gutter.fillMaxWidth().testTag("plan_mark_done"))
            }
            item("other_days") { AppSectionHeader(tr("Otros días", "Other days", "Outros dias")) }
            item("day_nav") { Row(gutter, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                AppSecondaryButton(tr("Anterior", "Previous", "Anterior"), { picked = viewDay - 1 }, enabled = viewDay > 1,
                    modifier = Modifier.weight(1f).testTag("plan_prev_day"))
                AppSecondaryButton(tr("Hoy", "Today", "Hoje"), { picked = 0 }, enabled = viewDay != today,
                    modifier = Modifier.weight(1f).testTag("plan_today"))
                AppSecondaryButton(tr("Siguiente", "Next", "Seguinte"), { picked = viewDay + 1 }, enabled = viewDay < total,
                    modifier = Modifier.weight(1f).testTag("plan_next_day"))
            } }
            item("day_picker") {
                LazyRow(state = chips, modifier = Modifier.testTag("plan_day_picker"),
                    contentPadding = PaddingValues(horizontal = design.spacing.gutter), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items((1..total).toList(), key = { it }) { n ->
                        AppChip(if (n in state.done) "$n ✓" else "$n", selected = n == viewDay, onClick = { picked = n },
                            modifier = Modifier.testTag("plan_day_$n"))
                    }
                }
            }
            item("restart") { AppSecondaryButton(tr("Reiniciar plan", "Restart plan", "Reiniciar plano"), { confirmRestart = true },
                modifier = gutter.fillMaxWidth().testTag("plan_restart")) }
        }
    }
    if (confirmRestart) AppConfirmDialog(
        tr("¿Reiniciar este plan?", "Restart this plan?", "Reiniciar este plano?"),
        tr("Se borrarán la fecha de inicio y los días marcados como leídos.", "The start date and the days marked as read will be cleared.",
            "A data de início e os dias marcados como lidos serão apagados."),
        confirmLabel = tr("Reiniciar", "Restart", "Reiniciar"), dismissLabel = tr("Cancelar", "Cancel", "Cancelar"),
        onConfirm = { confirmRestart = false; picked = 0; onRestart() }, onDismiss = { confirmRestart = false },
        destructive = true, tag = "plan_restart_dialog"
    )
}
