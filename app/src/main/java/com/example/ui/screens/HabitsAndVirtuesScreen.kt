package com.example.ui.screens

import android.os.Build
import android.view.HapticFeedbackConstants
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.fidelity.FidelityStore
import com.example.data.local.HabitCompletionLogEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.local.SinOvercomeLogEntity
import com.example.data.models.AppLanguage
import com.example.data.models.SinAvoiderOption
import com.example.data.reminders.ReminderStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.temptation.TemptationStore
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppChevron
import com.example.ui.design.AppChip
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppHeroHeader
import com.example.ui.design.AppImageCard
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppLargeTitleHeader
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppRoundPlayButton
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.AppSheet
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date

// =============================================================== the rule

/** Where tapping a fixed daily habit opens its practice. */
enum class DailyHabitAction {
    NONE, HOW_TO_PRAY, MISSAL, ANGELUS, CHAPLET, JACULATORY, EXAMEN, ROSARY, TRADITIONAL
}

/** One of the exactly twelve fixed daily practices. */
data class DailyHabit(
    val id: String,
    val group: Int,
    val titleEs: String,
    val titleEn: String,
    val titlePt: String,
    val subtitleEs: String,
    val subtitleEn: String,
    val subtitlePt: String,
    val action: DailyHabitAction
) {
    fun title(language: AppLanguage): String = language.pick3(titleEs, titleEn, titlePt)
    fun subtitle(language: AppLanguage): String = language.pick3(subtitleEs, subtitleEn, subtitlePt)
}

/**
 * The fixed rule of life: exactly twelve practices in three groups
 * (0 = morning, 1 = day, 2 = nightfall). Public so Home reads the same goal
 * ("N de 12") and the same ids as the Habits screen.
 */
object DailyRule {
    val habits: List<DailyHabit> = listOf(
        DailyHabit(
            id = "daily_offering", group = 0,
            titleEs = "Ofrecimiento del día", titleEn = "Offering of the day", titlePt = "Oferecimento do dia",
            subtitleEs = "Consagrar el día a Dios", subtitleEn = "Offer the day to God", subtitlePt = "Ofereça o dia a Deus",
            action = DailyHabitAction.HOW_TO_PRAY
        ),
        DailyHabit(
            id = "daily_morning_prayers", group = 0,
            titleEs = "Oraciones matutinas", titleEn = "Morning prayers", titlePt = "Orações matutinas",
            subtitleEs = "Padre Nuestro · Ave María · Gloria", subtitleEn = "Our Father · Hail Mary · Glory Be", subtitlePt = "Pai Nosso · Ave Maria · Glória",
            action = DailyHabitAction.NONE
        ),
        DailyHabit(
            id = "daily_mental_prayer", group = 0,
            titleEs = "Oración mental y silencio", titleEn = "Mental prayer and silence", titlePt = "Oração mental e silêncio",
            subtitleEs = "15–30 min", subtitleEn = "15–30 min", subtitlePt = "15–30 min",
            action = DailyHabitAction.NONE
        ),
        DailyHabit(
            id = "daily_spiritual_reading", group = 0,
            titleEs = "Lectura espiritual", titleEn = "Spiritual reading", titlePt = "Leitura espiritual",
            subtitleEs = "Lecturas de hoy · Misal del día", subtitleEn = "Today's readings · Daily missal", subtitlePt = "Leituras do dia · Missal do dia",
            action = DailyHabitAction.MISSAL
        ),
        DailyHabit(
            id = "daily_angelus", group = 1,
            titleEs = "Ángelus 6h · 12h · 18h", titleEn = "Angelus 6am · 12pm · 6pm", titlePt = "Ângelus 6h · 12h · 18h",
            subtitleEs = "El Ángelus de la mañana, el mediodía y la tarde", subtitleEn = "The Angelus morning, midday and evening", subtitlePt = "O Angelus da manhã, do meio-dia e da tarde",
            action = DailyHabitAction.ANGELUS
        ),
        DailyHabit(
            id = "daily_chaplet", group = 1,
            titleEs = "Coronilla 15:00", titleEn = "Divine Mercy Chaplet 3:00 PM", titlePt = "Coronilha 15:00",
            subtitleEs = "Coronilla de la Divina Misericordia", subtitleEn = "Chaplet of Divine Mercy", subtitlePt = "Coronilha da Divina Misericórdia",
            action = DailyHabitAction.CHAPLET
        ),
        DailyHabit(
            id = "daily_jaculatorias", group = 1,
            titleEs = "Jaculatorias", titleEn = "Ejaculations", titlePt = "Jaculatórias",
            subtitleEs = "Invocaciones breves del corazón", subtitleEn = "Short prayers of the heart", subtitlePt = "Invocações breves do coração",
            action = DailyHabitAction.JACULATORY
        ),
        DailyHabit(
            id = "daily_mass", group = 1,
            titleEs = "Santa Misa / Misa del día", titleEn = "Holy Mass / Daily readings", titlePt = "Santa Missa / Missa do dia",
            subtitleEs = "Lecturas y Evangelio de hoy", subtitleEn = "Today's readings and Gospel", subtitlePt = "Leituras e Evangelho de hoje",
            action = DailyHabitAction.MISSAL
        ),
        DailyHabit(
            id = "daily_meal_blessing", group = 1,
            titleEs = "Bendición de las comidas", titleEn = "Blessing of meals", titlePt = "Bênção das refeições",
            subtitleEs = "Antes y después de comer", subtitleEn = "Before and after meals", subtitlePt = "Antes e depois das refeições",
            action = DailyHabitAction.NONE
        ),
        DailyHabit(
            id = "daily_examen", group = 2,
            titleEs = "Examen de conciencia", titleEn = "Examination of conscience", titlePt = "Exame de consciência",
            subtitleEs = "Revisar el día ante Dios", subtitleEn = "Review the day before God", subtitlePt = "Rever o dia diante de Deus",
            action = DailyHabitAction.EXAMEN
        ),
        DailyHabit(
            id = "daily_rosary", group = 2,
            titleEs = "Rosario", titleEn = "Rosary", titlePt = "Terço",
            subtitleEs = "Cinco misterios de la vida de Cristo", subtitleEn = "Five mysteries of the life of Christ", subtitlePt = "Cinco mistérios da vida de Cristo",
            action = DailyHabitAction.ROSARY
        ),
        DailyHabit(
            id = "daily_traditional_prayers", group = 2,
            titleEs = "Oraciones tradicionales", titleEn = "Traditional prayers", titlePt = "Orações tradicionais",
            subtitleEs = "Padre Nuestro, Ave María, Gloria, Ángel de la Guarda…", subtitleEn = "Our Father, Hail Mary, Glory Be, Guardian Angel…", subtitlePt = "Pai Nosso, Ave Maria, Glória, Anjo da Guarda…",
            action = DailyHabitAction.TRADITIONAL
        )
    )

    val ids: Set<String> = habits.map { it.id }.toSet()
    val size: Int = habits.size
    val groups: List<List<DailyHabit>> = (0..2).map { g -> habits.filter { it.group == g } }

    /** "Mañana / Día / Anochecer" (EN Morning / Day / Nightfall, PT Manhã / Dia / Anoitecer). */
    fun groupLabel(group: Int, language: AppLanguage): String = when (group) {
        0 -> language.pick3("Mañana", "Morning", "Manhã")
        1 -> language.pick3("Día", "Day", "Dia")
        else -> language.pick3("Anochecer", "Nightfall", "Anoitecer")
    }

    /** Per-date count of rule practices kept (legacy non-rule ids ignored). */
    fun countsFrom(rows: List<HabitCompletionLogEntity>): Map<String, Int> =
        FidelityStore.countsFrom(rows.filter { it.habitId in ids })
}

/** How a victory over temptation is written to the Room log. */
object TemptationLog {
    /** sinKey of a victory not attributed to any of the seven roots. */
    const val GENERIC_KEY = "TEMPTATION"

    /**
     * A victory for [option], or a generic one when no root was chosen —
     * never silently the first root of the catalog.
     */
    fun victory(option: SinAvoiderOption?, language: AppLanguage, note: String): SinOvercomeLogEntity =
        SinOvercomeLogEntity(
            sinKey = option?.sinKey ?: GENERIC_KEY,
            counterVirtue = option?.counterVirtue?.get(language).orEmpty(),
            prayerUsed = "HAIL_MARY",
            note = note.trim()
        )
}

/** Milliseconds from [now] to the next local midnight (at least 1s). */
fun millisUntilNextDay(now: LocalDateTime): Long =
    ChronoUnit.MILLIS.between(now, now.toLocalDate().plusDays(1).atStartOfDay()).coerceAtLeast(1_000L)

/**
 * Today's date, recomputed on every resume and at midnight, so checks made
 * after midnight are written to the new day and the ring resets.
 */
@Composable
private fun rememberToday(): LocalDate {
    var today by remember { mutableStateOf(LocalDate.now()) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                val now = LocalDate.now()
                if (now != today) today = now
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
    if (!LocalInspectionMode.current) {
        LaunchedEffect(today) {
            delay(millisUntilNextDay(LocalDateTime.now()))
            today = LocalDate.now()
        }
    }
    return today
}

private enum class HabitsPage { ROOT, TEMPTATION, SUMMARY, REMINDERS }

private sealed interface TemptationSheet {
    data class Victory(val option: SinAvoiderOption?) : TemptationSheet
    data class Root(val option: SinAvoiderOption) : TemptationSheet
    data object Fall : TemptationSheet
}

private fun hailMary(language: AppLanguage): String = language.pick3(
    "Dios te salve, María, llena eres de gracia; el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.",
    "Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.",
    "Ave Maria, cheia de graça, o Senhor é convosco. Bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora de nossa morte. Amém."
)

private const val TEMPTATION_ART = "heroes/mysteries/sorrowful_1_agony.jpg"

// =============================================================== screen

/**
 * Habits & Virtues: the twelve-practice rule of life with today's ring, the
 * month as Fitness-style rings, and — one tap away, never on the root — the
 * help in temptation, the remedy virtues and the record of victories.
 *
 * Everything here is the reader's own history (Room + DataStore); the
 * persistence is the point. Sub-pages (temptation, summary, reminders) are
 * pushed in place with the shared glass back button.
 */
@Composable
fun HabitsAndVirtuesScreen(
    repository: CatholicDataRepository,
    dao: SanctaFidesDao,
    ttsManager: LiturgicalTtsManager,
    modifier: Modifier = Modifier,
    /** Open a prayer by id on the Prayers screen (Ángelus, jaculatorias…). */
    onOpenPrayer: (String) -> Unit = { _ -> },
    /** Open the Prayers tab (the "How to pray" section lives there). */
    onOpenPrayers: () -> Unit = {},
    /** Open the Rosary screen; 0 = Rosary, 1 = Divine Mercy Chaplet. */
    onOpenRosary: (Int) -> Unit = { _ -> },
    /** Open the daily readings / missal (Bible screen, missal tab). */
    onOpenMissal: () -> Unit = {},
    /** Open the Night office; 0 = Compline, 1 = Examen, 2 = Traditional. */
    onOpenNight: (Int) -> Unit = { _ -> },
    /** Open the Reminders destination; null keeps a local pushed page. */
    onOpenReminders: (() -> Unit)? = null
) {
    val insets = AppEdgeToEdge()
    val design = LocalAppDesign.current
    val lang by repository.currentLanguage.collectAsState()
    val context = LocalContext.current
    val app = context.applicationContext
    val scope = rememberCoroutineScope()
    val today = rememberToday()
    val todayKey = today.toString()

    val todayFlow = remember(todayKey) { dao.getCompletionsForDate(todayKey) }
    val todayCompletions by todayFlow.collectAsState(initial = null)
    val allFlow = remember(dao) { dao.getAllCompletions() }
    val allCompletions by allFlow.collectAsState(initial = emptyList())
    val logsFlow = remember(dao) { dao.getAllSinOvercomeLogs() }
    val sinLogs by logsFlow.collectAsState(initial = emptyList())
    val totalFlow = remember(dao) { dao.getTotalVictoriesCount() }
    val totalVictories by totalFlow.collectAsState(initial = 0)

    val temptationStore = remember(app) { TemptationStore(app) }
    val victories by temptationStore.counts.collectAsState(initial = emptyMap())
    val falls by temptationStore.fellCounts.collectAsState(initial = emptyMap())
    val reminderStore = remember(app) { ReminderStore(app) }
    val reminderPrefs by reminderStore.reminders.collectAsState(initial = null)

    val keptIds = remember(todayCompletions) {
        todayCompletions?.map { it.habitId }?.filter { it in DailyRule.ids }?.toSet() ?: emptySet()
    }
    val ruleCounts = remember(allCompletions) { DailyRule.countsFrom(allCompletions) }
    val catalog = repository.sinAvoiderCatalog

    var page by rememberSaveable { mutableStateOf(HabitsPage.ROOT) }
    var sheet by remember { mutableStateOf<TemptationSheet?>(null) }
    var toast by remember { mutableStateOf<String?>(null) }
    val rootState = rememberLazyListState()

    fun closePage() {
        ttsManager.stop()
        page = HabitsPage.ROOT
    }
    AppBackHandler(enabled = page != HabitsPage.ROOT && page != HabitsPage.REMINDERS) { closePage() }

    // The scaffold's scroll-edge bar follows accumulated scroll; reset it to
    // the new page's own position when a page is pushed or popped.
    val dispatcher = remember { NestedScrollDispatcher() }
    val noScroll = remember { object : NestedScrollConnection {} }
    LaunchedEffect(page) {
        val scrolledRoot = page == HabitsPage.ROOT &&
            (rootState.firstVisibleItemIndex > 1 || rootState.firstVisibleItemScrollOffset > 600)
        dispatcher.dispatchPostScroll(
            consumed = Offset(0f, if (scrolledRoot) -1_000_000f else 1_000_000f),
            available = Offset.Zero,
            source = NestedScrollSource.UserInput
        )
    }

    fun toggle(habitId: String, wasDone: Boolean) {
        scope.launch {
            if (wasDone) {
                dao.deleteCompletionForDate(habitId, todayKey)
            } else {
                dao.insertCompletion(HabitCompletionLogEntity(habitId = habitId, completionDate = todayKey))
            }
        }
    }

    fun open(action: DailyHabitAction) {
        when (action) {
            DailyHabitAction.HOW_TO_PRAY -> onOpenPrayers()
            DailyHabitAction.MISSAL -> onOpenMissal()
            DailyHabitAction.ANGELUS -> onOpenPrayer("angelus")
            DailyHabitAction.CHAPLET -> onOpenRosary(1)
            DailyHabitAction.JACULATORY -> onOpenPrayer("jesus_prayer")
            DailyHabitAction.EXAMEN -> onOpenNight(1)
            DailyHabitAction.ROSARY -> onOpenRosary(0)
            DailyHabitAction.TRADITIONAL -> onOpenNight(2)
            DailyHabitAction.NONE -> {}
        }
    }

    fun recordVictory(option: SinAvoiderOption?, note: String) {
        scope.launch {
            dao.insertSinOvercomeLog(TemptationLog.victory(option, lang, note))
            temptationStore.recordVictory()
            ttsManager.stop()
            sheet = null
            toast = lang.pick3("Victoria registrada. Gracias a Dios.", "Victory recorded. Thanks be to God.", "Vitória registada. Graças a Deus.")
        }
    }

    fun recordFall() {
        scope.launch {
            temptationStore.recordFall()
            ttsManager.stop()
            sheet = null
            toast = lang.pick3("Anotado. Vuelve a empezar con confianza.", "Noted. Begin again with trust.", "Anotado. Recomece com confiança.")
        }
    }

    val activeReminders = reminderPrefs?.values?.count { it.enabled }

    Box(
        modifier
            .fillMaxSize()
            .nestedScroll(noScroll, dispatcher)
            .testTag("habits_and_virtues_screen")
    ) {
        AnimatedContent(
            targetState = page,
            transitionSpec = {
                if (targetState != HabitsPage.ROOT) {
                    (slideInHorizontally(tween(if (design.reduceMotion) 0 else 280)) { it / 4 } + fadeIn(tween(if (design.reduceMotion) 0 else 220)))
                        .togetherWith(slideOutHorizontally(tween(if (design.reduceMotion) 0 else 280)) { -it / 10 } + fadeOut(tween(if (design.reduceMotion) 0 else 180)))
                } else {
                    (slideInHorizontally(tween(if (design.reduceMotion) 0 else 280)) { -it / 10 } + fadeIn(tween(if (design.reduceMotion) 0 else 220)))
                        .togetherWith(slideOutHorizontally(tween(if (design.reduceMotion) 0 else 280)) { it / 4 } + fadeOut(tween(if (design.reduceMotion) 0 else 180)))
                }
            },
            label = "habitsPage"
        ) { shown ->
            when (shown) {
                HabitsPage.ROOT -> HabitsRoot(
                    state = rootState,
                    insets = insets,
                    lang = lang,
                    today = today,
                    loaded = todayCompletions != null,
                    keptIds = keptIds,
                    ruleCounts = ruleCounts,
                    activeReminders = activeReminders,
                    onToggle = { id -> toggle(id, id in keptIds) },
                    onOpen = { open(it) },
                    onOpenSummary = { page = HabitsPage.SUMMARY },
                    onOpenTemptation = { page = HabitsPage.TEMPTATION },
                    onOpenReminders = { onOpenReminders?.invoke() ?: run { page = HabitsPage.REMINDERS } }
                )
                HabitsPage.TEMPTATION -> TemptationPage(
                    insets = insets,
                    lang = lang,
                    ttsManager = ttsManager,
                    catalog = catalog,
                    sinLogs = sinLogs,
                    totalVictories = totalVictories,
                    victories = victories,
                    falls = falls,
                    onVictory = { sheet = TemptationSheet.Victory(null) },
                    onFall = { sheet = TemptationSheet.Fall },
                    onRoot = { sheet = TemptationSheet.Root(it) },
                    onOpenPrayer = onOpenPrayer
                )
                HabitsPage.SUMMARY -> SummaryPage(
                    insets = insets,
                    lang = lang,
                    today = today,
                    keptToday = keptIds.size,
                    allCompletions = allCompletions
                )
                HabitsPage.REMINDERS -> RemindersScreen(
                    repository = repository,
                    onBack = { page = HabitsPage.ROOT }
                )
            }
        }

        // Quiet confirmation toast above the dock.
        AnimatedVisibility(
            visible = toast != null,
            enter = fadeIn(tween(if (design.reduceMotion) 0 else 200)) + slideInVertically(tween(if (design.reduceMotion) 0 else 260)) { it / 2 },
            exit = fadeOut(tween(if (design.reduceMotion) 0 else 200)) + slideOutVertically(tween(if (design.reduceMotion) 0 else 260)) { it / 2 },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = insets.calculateBottomPadding() + 16.dp)
        ) {
            GlassToast(toast.orEmpty())
        }
        LaunchedEffect(toast) {
            if (toast != null) {
                delay(2600)
                toast = null
            }
        }
    }

    // Sheets of the temptation flow.
    when (val s = sheet) {
        null -> {}
        is TemptationSheet.Victory -> VictorySheet(
            lang = lang,
            catalog = catalog,
            preselected = s.option,
            onDismiss = { sheet = null },
            onRecord = { option, note -> recordVictory(option, note) }
        )
        is TemptationSheet.Root -> RootSheet(
            lang = lang,
            option = s.option,
            ttsManager = ttsManager,
            onDismiss = { ttsManager.stop(); sheet = null },
            onRecord = { note -> recordVictory(s.option, note) },
            onFell = { ttsManager.stop(); sheet = TemptationSheet.Fall }
        )
        TemptationSheet.Fall -> FallSheet(
            lang = lang,
            onDismiss = { sheet = null },
            onRecord = { recordFall() },
            onContrition = { sheet = null; onOpenPrayer("act_of_contrition") }
        )
    }
}

// =============================================================== root

@Composable
private fun HabitsRoot(
    state: LazyListState,
    insets: PaddingValues,
    lang: AppLanguage,
    today: LocalDate,
    loaded: Boolean,
    keptIds: Set<String>,
    ruleCounts: Map<String, Int>,
    activeReminders: Int?,
    onToggle: (String) -> Unit,
    onOpen: (DailyHabitAction) -> Unit,
    onOpenSummary: () -> Unit,
    onOpenTemptation: () -> Unit,
    onOpenReminders: () -> Unit,
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val title = lang.pick3("Hábitos", "Habits", "Hábitos")
    AppInlineTitle(title)

    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize().testTag("tab_habits_rule"),
        contentPadding = PaddingValues(bottom = insets.calculateBottomPadding() + 24.dp)
    ) {
        item(key = "header") {
            AppHeroHeader(
                assetPath = "heroes/lumen/habits.jpg",
                title = title,
                eyebrow = longDate(today, lang),
                height = 260.dp,
                titleTag = "habits_title"
            ) {
                AppGlassIconButton(
                    icon = Icons.Outlined.BarChart,
                    contentDescription = lang.pick3("Resumen", "Summary", "Resumo"),
                    onClick = onOpenSummary,
                    onImage = true,
                    tag = "habits_stats_button"
                )
            }
        }

        item(key = "ring") {
            TodayRing(done = keptIds.size, total = DailyRule.size, loaded = loaded, lang = lang)
        }

        DailyRule.groups.forEachIndexed { groupIndex, habits ->
            item(key = "group_header_$groupIndex") {
                val kept = habits.count { it.id in keptIds }
                GroupHeader(
                    title = DailyRule.groupLabel(groupIndex, lang),
                    trailing = lang.pick3("$kept de ${habits.size}", "$kept of ${habits.size}", "$kept de ${habits.size}"),
                    topGap = if (groupIndex == 0) 28.dp else design.spacing.sectionGap
                )
            }
            item(key = "group_$groupIndex") {
                AppListGroup(
                    modifier = Modifier.testTag(
                        if (groupIndex == 0) "habits_checklist_card" else "habits_checklist_card_$groupIndex"
                    )
                ) {
                    habits.forEachIndexed { i, habit ->
                        RuleRow(
                            habit = habit,
                            checked = habit.id in keptIds,
                            lang = lang,
                            onToggle = { onToggle(habit.id) },
                            onOpen = if (habit.action != DailyHabitAction.NONE) ({ onOpen(habit.action) }) else null,
                            showDivider = i < habits.lastIndex
                        )
                    }
                }
            }
        }

        item(key = "temptation_card") {
            Box(
                Modifier
                    .padding(top = design.spacing.sectionGap + 4.dp)
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("fidelity_card_photo")
            ) {
                AppImageCard(
                    title = lang.pick3("En la tentación", "In temptation", "Na tentação"),
                    onClick = onOpenTemptation,
                    assetPath = TEMPTATION_ART,
                    eyebrow = lang.pick3("Velad y orad · Mt 26,41", "Watch and pray · Mt 26:41", "Vigiai e orai · Mt 26,41"),
                    meta = lang.pick3(
                        "Reza un Ave María y vence con la gracia",
                        "Pray a Hail Mary and overcome with grace",
                        "Reze uma Ave Maria e vença com a graça"
                    ),
                    aspectRatio = 2.05f,
                    tag = "open_temptation",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item(key = "fidelity_header") {
            GroupHeader(
                title = lang.pick3("Fidelidad", "Fidelity", "Fidelidade"),
                trailing = null,
                topGap = design.spacing.sectionGap + 4.dp
            )
        }
        item(key = "fidelity_calendar") {
            FidelityCalendar(
                counts = ruleCounts,
                currentLanguage = lang,
                dailyGoal = DailyRule.size,
                legacyDayTagPrefix = "habit_month_day_"
            )
        }

        item(key = "reminders") {
            Spacer(Modifier.height(design.spacing.sectionGap + 4.dp))
            AppListGroup {
                AppListRow(
                    title = lang.pick3("Recordatorios", "Reminders", "Lembretes"),
                    leadingIcon = Icons.Outlined.Notifications,
                    trailingText = activeReminders?.let {
                        lang.pick3(
                            if (it == 1) "1 activo" else "$it activos",
                            "$it on",
                            if (it == 1) "1 ativo" else "$it ativos"
                        )
                    },
                    onClick = onOpenReminders,
                    modifier = Modifier.testTag("open_reminders")
                )
            }
            Text(
                text = lang.pick3(
                    "Ángelus, Coronilla, oración de la mañana y de la noche.",
                    "Angelus, Chaplet, morning and night prayer.",
                    "Angelus, Coronilha, oração da manhã e da noite."
                ),
                style = styles.footnote,
                color = p.textSecondary,
                modifier = Modifier.padding(horizontal = design.spacing.gutter + 18.dp, vertical = 8.dp)
            )
        }
    }
}

/** Today's ring: gradient arc, growing sprout, "3/12" and one line of encouragement. */
@Composable
private fun TodayRing(done: Int, total: Int, loaded: Boolean, lang: AppLanguage) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val fraction = if (total > 0) done / total.toFloat() else 0f
    val description = lang.pick3(
        "$done de $total cumplidos hoy",
        "$done of $total kept today",
        "$done de $total cumpridos hoje"
    )
    val sproutScale by animateFloatAsState(if (design.reduceMotion) 1f else 0.86f + 0.14f * fraction, if (design.reduceMotion) tween(0) else spring(0.7f, 200f), label = "sprout")
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .testTag("screen_header_illustration"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LumenGradientRing(
            progress = if (loaded) fraction else 0f,
            size = 216.dp,
            stroke = 20.dp,
            modifier = Modifier
                .clearAndSetSemantics { contentDescription = description }
                .testTag("habits_today_ring")
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Sprout(Modifier.size(30.dp).graphicsLayer { scaleX = sproutScale; scaleY = sproutScale })
                Spacer(Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    val numeral = styles.display.copy(
                        fontSize = styles.display.fontSize * 1.18f,
                        lineHeight = styles.display.fontSize * 1.24f,
                        fontWeight = FontWeight.Light,
                        fontFeatureSettings = "tnum"
                    )
                    Text("$done", style = numeral, color = if (done > 0) p.success else p.textPrimary)
                    Text("/$total", style = numeral, color = p.textSecondary)
                }
                Text(
                    text = lang.pick3("Hábitos de hoy", "Today's habits", "Hábitos de hoje"),
                    style = styles.subhead,
                    color = p.textSecondary
                )
            }
        }
        Spacer(Modifier.height(18.dp))
        val message = when {
            fraction >= 1f -> lang.pick3(
                "¡Día consagrado en plenitud!",
                "A day consecrated in full!",
                "Dia consagrado em plenitude!"
            )
            fraction >= 0.5f -> lang.pick3(
                "Buen camino, persevera hasta la noche.",
                "Good progress — persevere until nightfall.",
                "Bom caminho, persevere até à noite."
            )
            else -> lang.pick3(
                "Empieza con un ofrecimiento de obras.",
                "Begin with an offering of the day's work.",
                "Comece com um oferecimento de obras."
            )
        }
        Crossfade(targetState = message, animationSpec = tween(if (design.reduceMotion) 0 else 300), label = "message") { m ->
            Text(
                text = m,
                style = styles.subhead,
                color = p.textSecondary,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
            )
        }
    }
}

/** Two-leaf sprout above the count (the reference's seedling), in ring greens. */
@Composable
private fun Sprout(modifier: Modifier = Modifier) {
    val p = LocalAppDesign.current.palette
    Canvas(modifier.clearAndSetSemantics { }) {
        val w = size.width
        val h = size.height
        val stem = Path().apply {
            moveTo(w * 0.50f, h * 0.98f)
            cubicTo(w * 0.50f, h * 0.80f, w * 0.49f, h * 0.66f, w * 0.52f, h * 0.52f)
        }
        drawPath(stem, p.ringEnd, style = Stroke(width = w * 0.075f, cap = StrokeCap.Round))
        val left = Path().apply {
            moveTo(w * 0.50f, h * 0.66f)
            cubicTo(w * 0.30f, h * 0.70f, w * 0.08f, h * 0.58f, w * 0.04f, h * 0.30f)
            cubicTo(w * 0.28f, h * 0.24f, w * 0.48f, h * 0.38f, w * 0.50f, h * 0.66f)
            close()
        }
        drawPath(left, Brush.linearGradient(listOf(p.ringEnd, p.success), start = Offset(w * 0.04f, h * 0.3f), end = Offset(w * 0.5f, h * 0.66f)))
        val right = Path().apply {
            moveTo(w * 0.52f, h * 0.54f)
            cubicTo(w * 0.52f, h * 0.28f, w * 0.70f, h * 0.06f, w * 0.97f, h * 0.04f)
            cubicTo(w * 0.98f, h * 0.32f, w * 0.80f, h * 0.54f, w * 0.52f, h * 0.54f)
            close()
        }
        drawPath(right, Brush.linearGradient(listOf(p.success, p.ringEnd), start = Offset(w * 0.52f, h * 0.54f), end = Offset(w * 0.97f, h * 0.04f)))
    }
}

/** Section title (title3) with an optional trailing count. */
@Composable
private fun GroupHeader(title: String, trailing: String?, topGap: Dp) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = design.spacing.gutter, end = design.spacing.gutter + 2.dp, top = topGap, bottom = 10.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(title, style = styles.title3, color = p.textPrimary, modifier = Modifier.weight(1f).semantics { heading() })
        if (trailing != null) {
            Text(trailing, style = styles.footnote.copy(fontFeatureSettings = "tnum"), color = p.textSecondary)
        }
    }
}

/**
 * One practice of the rule. The circle toggles today's check (44dp, a
 * checkbox to TalkBack, spring + haptic); the rest of the row opens the
 * practice when it has one, and toggles when it has none, so no part of a
 * row is dead.
 */
@Composable
private fun RuleRow(
    habit: DailyHabit,
    checked: Boolean,
    lang: AppLanguage,
    onToggle: () -> Unit,
    onOpen: (() -> Unit)?,
    showDivider: Boolean
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val view = LocalView.current
    val state = lang.pick3(
        if (checked) "Cumplido hoy" else "Pendiente hoy",
        if (checked) "Kept today" else "Pending today",
        if (checked) "Cumprido hoje" else "Pendente hoje"
    )
    fun haptic(on: Boolean) {
        view.performHapticFeedback(
            if (on && Build.VERSION.SDK_INT >= 30) HapticFeedbackConstants.CONFIRM
            else HapticFeedbackConstants.CLOCK_TICK
        )
    }
    val rowSource = remember { MutableInteractionSource() }
    val rowPressed by rowSource.collectIsPressedAsState()
    val rowFill by animateColorAsState(
        if (rowPressed) p.textPrimary.copy(alpha = 0.06f) else Color.Transparent,
        if (rowPressed) tween(0) else tween(if (design.reduceMotion) 0 else 250),
        label = "rowFill"
    )
    Column(Modifier.fillMaxWidth().testTag("habit_card_${habit.id}")) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(rowFill)
                .heightIn(min = 60.dp)
                .padding(start = 8.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CheckCircle(
                checked = checked,
                stateLabel = state,
                tag = "toggle_habit_${habit.id}",
                onToggle = { haptic(!checked); onToggle() }
            )
            Spacer(Modifier.width(4.dp))
            Row(
                Modifier
                    .weight(1f)
                    .clickable(rowSource, indication = null, role = Role.Button, onClick = onOpen ?: { haptic(!checked); onToggle() })
                    .padding(vertical = 11.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(habit.title(lang), style = styles.body, color = p.textPrimary, maxLines = 2)
                    Spacer(Modifier.height(1.dp))
                    Text(habit.subtitle(lang), style = styles.footnote, color = p.textSecondary, maxLines = 2)
                }
                if (onOpen != null) {
                    Spacer(Modifier.width(10.dp))
                    AppChevron()
                }
            }
        }
        if (showDivider) {
            Box(Modifier.fillMaxWidth().padding(start = 56.dp).height(0.5.dp).background(p.separator))
        }
    }
}

/** The habit check: open ring when pending, success fill and a springing tick when kept. */
@Composable
private fun CheckCircle(checked: Boolean, stateLabel: String, tag: String, onToggle: () -> Unit) {
    val design = LocalAppDesign.current
    val p = design.palette
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val press by animateFloatAsState(if (pressed && !design.reduceMotion) 0.86f else 1f, if (design.reduceMotion) tween(0) else spring(0.8f, 600f), label = "checkPress")
    val fill by animateColorAsState(if (checked) p.action else Color.Transparent, tween(if (design.reduceMotion) 0 else 180), label = "checkFill")
    val tick by animateFloatAsState(if (checked) 1f else 0f, if (design.reduceMotion) tween(0) else spring(0.55f, 500f), label = "tick")
    Box(
        Modifier
            .size(48.dp)
            .graphicsLayer { scaleX = press; scaleY = press }
            .toggleable(
                value = checked,
                interactionSource = source,
                indication = null,
                role = Role.Checkbox,
                onValueChange = { onToggle() }
            )
            .semantics { stateDescription = stateLabel }
            .testTag(tag),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(fill)
                .border(1.5.dp, if (checked) p.action else p.textTertiary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (tick > 0.02f) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = null,
                    tint = p.onAction,
                    modifier = Modifier.size(17.dp).graphicsLayer {
                        scaleX = 0.4f + 0.6f * tick
                        scaleY = 0.4f + 0.6f * tick
                        alpha = tick.coerceIn(0f, 1f)
                    }
                )
            }
        }
    }
}

// =============================================================== summary

@Composable
private fun SummaryPage(
    insets: PaddingValues,
    lang: AppLanguage,
    today: LocalDate,
    keptToday: Int,
    allCompletions: List<HabitCompletionLogEntity>
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val title = lang.pick3("Resumen", "Summary", "Resumo")
    AppInlineTitle(title)

    val month = YearMonth.from(today)
    val monthPrefix = month.toString()
    val monthRows = remember(allCompletions, monthPrefix) {
        allCompletions.filter { it.completionDate.startsWith(monthPrefix) && it.habitId in DailyRule.ids }
    }
    val pairs = remember(monthRows) { monthRows.map { it.habitId to it.completionDate }.distinct() }
    val elapsed = today.dayOfMonth
    val possible = DailyRule.size * elapsed
    val activeDays = pairs.map { it.second }.distinct().size
    val daysPerHabit = remember(pairs) { pairs.groupingBy { it.first }.eachCount() }
    val monthRate = if (possible > 0) pairs.size / possible.toFloat() else 0f

    LazyColumn(
        modifier = Modifier.fillMaxSize().testTag("daily_completion_control"),
        contentPadding = PaddingValues(bottom = insets.calculateBottomPadding() + 24.dp)
    ) {
        item(key = "header") {
            AppDetailHeader(
                title = title,
                assetPath = "heroes/lumen/habits.jpg",
                eyebrow = monthTitle(month, calendarLocale(lang)),
                imageHeight = 230.dp,
                titleTag = "summary_title"
            )
        }
        item(key = "tiles") {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatTile(
                    value = "$keptToday/${DailyRule.size}",
                    label = lang.pick3("Hoy", "Today", "Hoje"),
                    modifier = Modifier.weight(1f).semantics { stateDescription = lang.pick3("Hoy: $keptToday de ${DailyRule.size} prácticas realizadas", "Today: $keptToday of ${DailyRule.size} practices completed", "Hoje: $keptToday de ${DailyRule.size} práticas realizadas") }
                )
                StatTile(
                    value = "${pairs.size}/$possible",
                    label = lang.pick3("Este mes", "This month", "Este mês"),
                    modifier = Modifier.weight(1f)
                        .semantics(mergeDescendants = true) {
                            contentDescription = lang.pick3(
                                "Este mes: ${pairs.size} de $possible · ${daysLabel(activeDays, lang)}",
                                "This month: ${pairs.size} of $possible · ${daysLabel(activeDays, lang)}",
                                "Este mês: ${pairs.size} de $possible · ${daysLabel(activeDays, lang)}"
                            )
                        }
                        .testTag("habits_monthly_summary")
                )
                StatTile(
                    value = "$activeDays",
                    label = lang.pick3("Días activos", "Active days", "Dias ativos"),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(16.dp))
            Column(Modifier.padding(horizontal = design.spacing.gutter + 4.dp)) {
                AppProgressBar(
                    progress = monthRate,
                    color = p.success,
                    modifier = Modifier.semantics { stateDescription = lang.pick3("${pairs.size} de $possible prácticas realizadas", "${pairs.size} of $possible practices completed", "${pairs.size} de $possible práticas realizadas") }
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = lang.pick3(
                        "${(monthRate * 100).toInt()} % de lo posible hasta hoy",
                        "${(monthRate * 100).toInt()}% of what was possible so far",
                        "${(monthRate * 100).toInt()} % do possível até hoje"
                    ),
                    style = styles.footnote,
                    color = p.textSecondary
                )
            }
        }
        DailyRule.groups.forEachIndexed { g, habits ->
            item(key = "summary_group_$g") {
                GroupHeader(
                    title = DailyRule.groupLabel(g, lang),
                    trailing = null,
                    topGap = design.spacing.sectionGap
                )
                AppListGroup {
                    habits.forEachIndexed { i, habit ->
                        val days = daysPerHabit[habit.id] ?: 0
                        PracticeMonthRow(
                            title = habit.title(lang),
                            days = days,
                            elapsed = elapsed,
                            lang = lang,
                            showDivider = i < habits.lastIndex
                        )
                    }
                }
            }
        }
        item(key = "summary_footer") {
            Text(
                text = if (pairs.isEmpty()) lang.pick3(
                    "Cada práctica cuenta desde hoy.",
                    "Every practice counts from today.",
                    "Cada prática conta a partir de hoje."
                ) else lang.pick3(
                    "Días de este mes en que cumpliste cada práctica.",
                    "Days this month on which you kept each practice.",
                    "Dias deste mês em que cumpriu cada prática."
                ),
                style = styles.footnote,
                color = p.textSecondary,
                modifier = Modifier.padding(horizontal = design.spacing.gutter + 18.dp, vertical = 10.dp)
            )
        }
    }
}

@Composable
private fun StatTile(value: String, label: String, modifier: Modifier = Modifier) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(
        modifier
            .lumenGlass(design.shapes.listGroup)
            .padding(horizontal = 14.dp, vertical = 14.dp)
    ) {
        Text(value, style = styles.title2.copy(fontFeatureSettings = "tnum"), color = p.textPrimary, maxLines = 1)
        Spacer(Modifier.height(2.dp))
        Text(label, style = styles.caption, color = p.textSecondary, maxLines = 1)
    }
}

@Composable
private fun PracticeMonthRow(title: String, days: Int, elapsed: Int, lang: AppLanguage, showDivider: Boolean) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 60.dp)
                .padding(horizontal = 18.dp, vertical = 12.dp)
                .semantics(mergeDescendants = true) { }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, style = styles.body, color = p.textPrimary, modifier = Modifier.weight(1f), maxLines = 2)
                Spacer(Modifier.width(10.dp))
                Text(
                    lang.pick3("$days de $elapsed días", "$days of $elapsed days", "$days de $elapsed dias"),
                    style = styles.footnote.copy(fontFeatureSettings = "tnum"),
                    color = p.textSecondary
                )
            }
            Spacer(Modifier.height(8.dp))
            AppProgressBar(
                progress = if (elapsed > 0) days / elapsed.toFloat() else 0f,
                color = p.success,
                height = 4.dp
            )
        }
        if (showDivider) Box(Modifier.fillMaxWidth().padding(start = 18.dp).height(0.5.dp).background(p.separator))
    }
}

// =============================================================== temptation

@Composable
private fun TemptationPage(
    insets: PaddingValues,
    lang: AppLanguage,
    ttsManager: LiturgicalTtsManager,
    catalog: List<SinAvoiderOption>,
    sinLogs: List<SinOvercomeLogEntity>,
    totalVictories: Int,
    victories: Map<String, Int>,
    falls: Map<String, Int>,
    onVictory: () -> Unit,
    onFall: () -> Unit,
    onRoot: (SinAvoiderOption) -> Unit,
    onOpenPrayer: (String) -> Unit,
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val title = lang.pick3("En la tentación", "In temptation", "Na tentação")
    AppInlineTitle(title)
    val playing by ttsManager.isPlaying.collectAsState()
    DisposableEffect(Unit) { onDispose { ttsManager.stop() } }

    LazyColumn(
        modifier = Modifier.fillMaxSize().testTag("tab_sin_avoider"),
        contentPadding = PaddingValues(bottom = insets.calculateBottomPadding() + 24.dp)
    ) {
        item(key = "header") {
            AppDetailHeader(
                title = title,
                assetPath = TEMPTATION_ART,
                meta = lang.pick3(
                    "«Velad y orad para no caer en tentación» · Mt 26,41",
                    "“Watch and pray that you may not enter into temptation” · Mt 26:41",
                    "«Vigiai e orai para não cairdes em tentação» · Mt 26,41"
                ),
                imageHeight = 200.dp,
                titleTag = "temptation_title"
            )
        }

        item(key = "pray_now") {
            Column(
                Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .lumenGlass(design.shapes.card)
                    .padding(20.dp)
                    .testTag("temptation_prayer_card")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            lang.pick3("Detente. Respira. Reza.", "Stop. Breathe. Pray.", "Pare. Respire. Reze."),
                            style = styles.eyebrow,
                            color = p.textSecondary
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(lang.pick3("Ave María", "Hail Mary", "Ave Maria"), style = styles.title2, color = p.textPrimary)
                    }
                    AppRoundPlayButton(
                        playing = playing,
                        onClick = {
                            if (playing) ttsManager.stop() else {
                                ttsManager.setLanguage(lang)
                                ttsManager.speakSingle(hailMary(lang), isFemaleVoice = true)
                            }
                        },
                        contentDescription = lang.pick3("Escuchar oración", "Listen to the prayer", "Ouvir a oração"),
                        tag = "temptation_listen"
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text(hailMary(lang), style = styles.reading, color = p.textPrimary)
            }
        }

        item(key = "actions") {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AppPrimaryCapsule(
                    label = lang.pick3("Vencí", "I overcame", "Venci"),
                    onClick = onVictory,
                    modifier = Modifier.weight(1f),
                    tag = "emergency_overcome_button"
                )
                AppSecondaryCapsule(
                    label = lang.pick3("Caí", "I fell", "Caí"),
                    onClick = onFall,
                    fillWidth = true,
                    modifier = Modifier.weight(1f),
                    tag = "temptation_fell_button"
                )
            }
            Text(
                text = lang.pick3(
                    "Anótalo solo para ti: queda en este dispositivo.",
                    "Noted only for you: it stays on this device.",
                    "Anotado só para si: fica neste dispositivo."
                ),
                style = styles.footnote,
                color = p.textSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter, vertical = 10.dp)
            )
        }

        item(key = "stats") {
            GroupHeader(lang.pick3("Tu camino", "Your path", "O seu caminho"), null, design.spacing.sectionGap - 8.dp)
            StatsGrid(lang = lang, victories = victories, falls = falls)
        }

        item(key = "calendar") {
            Spacer(Modifier.height(14.dp))
            TemptationCalendar(counts = victories, currentLanguage = lang, fellCounts = falls)
        }

        item(key = "virtues_header") {
            GroupHeader(
                lang.pick3("Virtudes contrarias", "Remedy virtues", "Virtudes contrárias"),
                null,
                design.spacing.sectionGap
            )
        }
        item(key = "virtues") {
            AppListGroup {
                catalog.forEach { option ->
                    val count = sinLogs.count { it.sinKey == option.sinKey }
                    AppListRow(
                        title = option.counterVirtue.get(lang),
                        subtitle = option.title.get(lang),
                        trailingText = if (count > 0) count.toString() else null,
                        onClick = { onRoot(option) },
                        modifier = Modifier
                            .semantics {
                                if (count > 0) stateDescription = lang.pick3("$count victorias", "$count victories", "$count vitórias")
                            }
                            .testTag("card_overcome_${option.sinKey}")
                    )
                }
            }
            Text(
                text = lang.pick3(
                    "Una para cada pecado capital. Toca para pedir su gracia.",
                    "One for each capital sin. Tap to ask for its grace.",
                    "Uma para cada pecado capital. Toque para pedir a sua graça."
                ),
                style = styles.footnote,
                color = p.textSecondary,
                modifier = Modifier.padding(horizontal = design.spacing.gutter + 18.dp, vertical = 8.dp)
            )
        }

        item(key = "st_michael") {
            Spacer(Modifier.height(12.dp))
            AppListGroup {
                AppListRow(
                    title = lang.pick3("Oración a San Miguel Arcángel", "Prayer to Saint Michael", "Oração a São Miguel Arcanjo"),
                    leadingIcon = Icons.Outlined.FavoriteBorder,
                    onClick = { onOpenPrayer("st_michael") },
                    modifier = Modifier.testTag("temptation_st_michael")
                )
                AppListRow(
                    title = lang.pick3("Acto de contrición", "Act of contrition", "Ato de contrição"),
                    leadingIcon = Icons.Outlined.FavoriteBorder,
                    onClick = { onOpenPrayer("act_of_contrition") },
                    modifier = Modifier.testTag("temptation_contrition")
                )
            }
        }

        item(key = "history_header") {
            GroupHeader(
                lang.pick3("Victorias en Cristo", "Victories in Christ", "Vitórias em Cristo"),
                if (totalVictories > 0) "$totalVictories" else null,
                design.spacing.sectionGap
            )
        }
        item(key = "history") {
            if (sinLogs.isEmpty()) {
                Text(
                    text = lang.pick3(
                        "Cuando venzas una tentación, quedará aquí como acción de gracias.",
                        "When you overcome a temptation, it will stay here as thanksgiving.",
                        "Quando vencer uma tentação, ficará aqui como ação de graças."
                    ),
                    style = styles.subhead,
                    color = p.textSecondary,
                    modifier = Modifier.padding(horizontal = design.spacing.gutter).testTag("victory_history")
                )
            } else {
                AppListGroup(Modifier.testTag("victory_history")) {
                    sinLogs.take(10).forEach { log ->
                        val option = catalog.firstOrNull { it.sinKey == log.sinKey }
                        val virtue = option?.counterVirtue?.get(lang) ?: log.counterVirtue.takeIf { it.isNotBlank() }
                        AppListRow(
                            title = if (virtue != null) {
                                lang.pick3("Victoria · $virtue", "Victory · $virtue", "Vitória · $virtue")
                            } else {
                                lang.pick3("Tentación vencida", "Temptation overcome", "Tentação vencida")
                            },
                            subtitle = log.note.takeIf { it.isNotBlank() }?.let { "«$it»" },
                            trailingText = logDate(log.timestamp, lang),
                            showsNavigationAffordance = false
                        )
                    }
                }
            }
        }
    }
}

/** Vencí / Caí × hoy / semana / mes, all six visible at once. */
@Composable
private fun StatsGrid(lang: AppLanguage, victories: Map<String, Int>, falls: Map<String, Int>) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val today = LocalDate.now()
    val columns = listOf(
        lang.pick3("Hoy", "Today", "Hoje"),
        lang.pick3("Semana", "Week", "Semana"),
        lang.pick3("Mes", "Month", "Mês")
    )
    Column(
        Modifier
            .padding(horizontal = design.spacing.gutter)
            .lumenGlass(design.shapes.card)
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Spacer(Modifier.weight(1.3f))
            columns.forEach {
                Text(it, style = styles.caption, color = p.textSecondary, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
            }
        }
        listOf(
            Triple(lang.pick3("Vencí", "Overcame", "Venci"), victories, "victory_stats"),
            Triple(lang.pick3("Caí", "Fell", "Caí"), falls, "fall_stats")
        ).forEachIndexed { index, (label, counts, prefix) ->
            if (index == 1) Box(Modifier.fillMaxWidth().padding(vertical = 2.dp).height(0.5.dp).background(p.separator))
            Row(Modifier.fillMaxWidth().heightIn(min = 48.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(label, style = styles.subhead, color = p.textPrimary, modifier = Modifier.weight(1.3f))
                val values = listOf(
                    (counts[TemptationStore.dateKey(today)] ?: 0) to "${prefix}_today",
                    TemptationStore.weeklyCount(counts, today) to "${prefix}_week",
                    TemptationStore.monthlyCount(counts, today) to "${prefix}_month"
                )
                values.forEachIndexed { i, (value, tag) ->
                    Text(
                        text = value.toString(),
                        style = styles.title2.copy(fontFeatureSettings = "tnum"),
                        color = p.textPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .semantics { contentDescription = "$label, ${columns[i]}: $value" }
                            .testTag(tag)
                    )
                }
            }
        }
    }
}

// =============================================================== sheets

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun VictorySheet(
    lang: AppLanguage,
    catalog: List<SinAvoiderOption>,
    preselected: SinAvoiderOption?,
    onDismiss: () -> Unit,
    onRecord: (SinAvoiderOption?, String) -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    var choice by remember { mutableStateOf(preselected) }
    var note by remember { mutableStateOf("") }
    AppSheet(onDismiss = onDismiss, tag = "victory_sheet") {
        Column(Modifier.padding(horizontal = design.spacing.gutter)) {
            Text(lang.pick3("Gracias a Dios", "Thanks be to God", "Graças a Deus"), style = styles.title2, color = p.textPrimary)
            Spacer(Modifier.height(6.dp))
            Text(
                lang.pick3(
                    "Anota esta victoria como acción de gracias.",
                    "Note this victory as an act of thanksgiving.",
                    "Anote esta vitória como ação de graças."
                ),
                style = styles.callout,
                color = p.textSecondary
            )
            Spacer(Modifier.height(20.dp))
            Text(
                lang.pick3("¿Frente a qué? (opcional)", "Against what? (optional)", "Contra quê? (opcional)"),
                style = styles.footnote,
                color = p.textSecondary
            )
            Spacer(Modifier.height(8.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AppChip(
                    label = lang.pick3("Sin especificar", "Unspecified", "Sem especificar"),
                    selected = choice == null,
                    onClick = { choice = null }
                )
                catalog.forEach { option ->
                    AppChip(
                        label = option.title.get(lang).substringBefore(" / "),
                        selected = choice?.sinKey == option.sinKey,
                        onClick = { choice = option }
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            GlassField(
                value = note,
                onValueChange = { note = it },
                placeholder = lang.pick3("Nota u ofrenda (opcional)", "Note or offering (optional)", "Nota ou oferta (opcional)"),
                tag = "victory_note"
            )
            Spacer(Modifier.height(20.dp))
            AppPrimaryCapsule(
                label = lang.pick3("Registrar victoria", "Record victory", "Registar vitória"),
                onClick = { onRecord(choice, note) },
                tag = "confirm_victory_button"
            )
            Spacer(Modifier.height(4.dp))
        }
    }
}

@Composable
private fun FallSheet(
    lang: AppLanguage,
    onDismiss: () -> Unit,
    onRecord: () -> Unit,
    onContrition: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    AppSheet(onDismiss = onDismiss, tag = "fall_sheet") {
        Column(Modifier.padding(horizontal = design.spacing.gutter)) {
            Text(lang.pick3("Levántate", "Rise again", "Levante-se"), style = styles.title2, color = p.textPrimary)
            Spacer(Modifier.height(10.dp))
            Text(
                lang.pick3(
                    "Dios no se cansa de perdonar. Pide perdón con un acto de contrición y vuelve a empezar; si la falta es grave, acude a la confesión.",
                    "God never tires of forgiving. Ask for pardon with an act of contrition and begin again; if the sin is grave, go to confession.",
                    "Deus não se cansa de perdoar. Peça perdão com um ato de contrição e recomece; se a falta for grave, procure a confissão."
                ),
                style = styles.reading,
                color = p.textPrimary
            )
            Spacer(Modifier.height(18.dp))
        }
        AppListGroup {
            AppListRow(
                title = lang.pick3("Acto de contrición", "Act of contrition", "Ato de contrição"),
                leadingIcon = Icons.Outlined.FavoriteBorder,
                onClick = onContrition,
                modifier = Modifier.testTag("fall_contrition")
            )
        }
        Column(Modifier.padding(horizontal = design.spacing.gutter)) {
            Spacer(Modifier.height(20.dp))
            AppPrimaryCapsule(
                label = lang.pick3("Anotar y empezar de nuevo", "Note it and begin again", "Anotar e recomeçar"),
                onClick = onRecord,
                tag = "record_fall_button"
            )
            Spacer(Modifier.height(10.dp))
            AppSecondaryCapsule(
                label = lang.pick3("Cancelar", "Cancel", "Cancelar"),
                onClick = onDismiss,
                fillWidth = true
            )
            Spacer(Modifier.height(4.dp))
        }
    }
}

@Composable
private fun RootSheet(
    lang: AppLanguage,
    option: SinAvoiderOption,
    ttsManager: LiturgicalTtsManager,
    onDismiss: () -> Unit,
    onRecord: (String) -> Unit,
    onFell: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    var note by remember { mutableStateOf("") }
    val playing by ttsManager.isPlaying.collectAsState()
    AppSheet(onDismiss = onDismiss, tag = "root_sheet") {
        Column(Modifier.padding(horizontal = design.spacing.gutter)) {
            Text(option.counterVirtue.get(lang), style = styles.title2, color = p.textPrimary)
            Spacer(Modifier.height(2.dp))
            Text(
                lang.pick3("Frente a: ", "Against: ", "Contra: ") + option.title.get(lang),
                style = styles.footnote,
                color = p.textSecondary
            )
            Spacer(Modifier.height(14.dp))
            Text(option.advice.get(lang), style = styles.reading, color = p.textPrimary)
            Spacer(Modifier.height(16.dp))
            Box(Modifier.fillMaxWidth().height(0.5.dp).background(p.separator))
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    lang.pick3("Reza un Ave María ahora", "Pray one Hail Mary now", "Reze uma Ave Maria agora"),
                    style = styles.headline,
                    color = p.textPrimary,
                    modifier = Modifier.weight(1f)
                )
                AppRoundPlayButton(
                    playing = playing,
                    onClick = {
                        if (playing) ttsManager.stop() else {
                            ttsManager.setLanguage(lang)
                            ttsManager.speakSingle(hailMary(lang), isFemaleVoice = true)
                        }
                    },
                    contentDescription = lang.pick3("Escuchar oración", "Listen to the prayer", "Ouvir a oração")
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(hailMary(lang), style = styles.callout, color = p.textSecondary)
            Spacer(Modifier.height(18.dp))
            GlassField(
                value = note,
                onValueChange = { note = it },
                placeholder = lang.pick3("Nota u ofrenda (opcional)", "Note or offering (optional)", "Nota ou oferta (opcional)"),
                tag = "root_note"
            )
            Spacer(Modifier.height(18.dp))
            AppPrimaryCapsule(
                label = lang.pick3("Registrar victoria", "Record victory", "Registar vitória"),
                onClick = { onRecord(note) },
                tag = "confirm_victory_button"
            )
            Spacer(Modifier.height(10.dp))
            AppSecondaryCapsule(
                label = lang.pick3("Caí en esta tentación", "I fell into this temptation", "Caí nesta tentação"),
                onClick = onFell,
                fillWidth = true,
                tag = "root_fell_button"
            )
            Spacer(Modifier.height(4.dp))
        }
    }
}

// =============================================================== small parts

/** Glass text field (radius 20) with a placeholder; multi-line up to 3. */
@Composable
internal fun GlassField(value: String, onValueChange: (String) -> Unit, placeholder: String, tag: String, singleLine: Boolean = false) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        maxLines = if (singleLine) 1 else 3,
        textStyle = styles.body.copy(color = p.textPrimary),
        cursorBrush = SolidColor(p.textPrimary),
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = placeholder }
            .testTag(tag),
        decorationBox = { inner ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 52.dp)
                    .lumenGlass(RoundedCornerShape(20.dp), lifted = false)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) Text(placeholder, style = styles.body, color = p.textSecondary)
                inner()
            }
        }
    )
}

/** Glass capsule toast for quiet confirmations. */
@Composable
internal fun GlassToast(text: String) {
    val p = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    Box(
        Modifier
            .padding(horizontal = 24.dp)
            .lumenGlass(RoundedCornerShape(percent = 50), strong = true)
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .testTag("glass_toast")
    ) {
        Text(text, style = styles.subhead, color = p.textPrimary, textAlign = TextAlign.Center)
    }
}

/** Height of the scaffold's glass back row on pushed pages. */
internal val BackRowHeight = 52.dp

private fun longDate(date: LocalDate, lang: AppLanguage): String {
    val locale = calendarLocale(lang)
    val pattern = lang.pick3("EEEE, d 'de' MMMM", "EEEE, MMMM d", "EEEE, d 'de' MMMM")
    return date.format(DateTimeFormatter.ofPattern(pattern, locale)).replaceFirstChar { it.titlecase(locale) }
}

private fun logDate(timestamp: Long, lang: AppLanguage): String =
    SimpleDateFormat(lang.pick3("d MMM, HH:mm", "MMM d, h:mm a", "d MMM, HH:mm"), calendarLocale(lang)).format(Date(timestamp))
