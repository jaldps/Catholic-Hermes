package com.example.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Checklist
import com.example.ui.design.DevotionalGlyphs
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.HabitCompletionLogEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.local.SinOvercomeLogEntity
import com.example.data.local.SpiritualHabitEntity
import com.example.data.temptation.TemptationStore
import java.time.LocalDate
import com.example.data.models.AppLanguage
import com.example.data.models.SinAvoiderOption
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/**
 * A rule of life and the war on the capital sins, both kept in Room.
 *
 * Everything on this screen is the reader's own history — which practices they
 * keep, how long the streak has run, every temptation they have named and
 * overcome — so the persistence here is the point and the styling is the part
 * that moves between identities.
 */
@OptIn(ExperimentalLayoutApi::class)
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
    onOpenNight: (Int) -> Unit = { _ -> }
) {
    val currentLanguage by repository.currentLanguage.collectAsState()
    val design = LocalAppDesign.current
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Photographic hero, decoded once off the composition path and hoisted
    // unconditionally to the top of the screen — never inside a LazyColumn
    // item or a conditional branch (slot-table crash). Null on any decode
    // failure so the band falls back to the canvas gradient, like Home.
    val heroPhoto = remember(context) {
        decodeSampledBitmap(context, "heroes/candle_prayer.jpg", maxWidthPx = 1080)
    }

    val todayDateStr = remember {
        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }

    val habitsList by dao.getAllHabits().collectAsState(initial = emptyList())
    val todayCompletions by dao.getCompletionsForDate(todayDateStr).collectAsState(initial = emptyList())
    val allCompletions by dao.getAllCompletions().collectAsState(initial = emptyList())
    val sinOvercomeLogs by dao.getAllSinOvercomeLogs().collectAsState(initial = emptyList())
    val totalVictories by dao.getTotalVictoriesCount().collectAsState(initial = 0)

    // Per-day temptation counters for the monthly calendars (Victoria tab):
    // victories and falls, each resetting daily and monthly per date key.
    val temptationStore = remember(context) { TemptationStore(context.applicationContext) }
    val temptationCounts by temptationStore.counts.collectAsState(initial = emptyMap())
    val temptationFellCounts by temptationStore.fellCounts.collectAsState(initial = emptyMap())

    // The suggested rule is offered, not imposed: with nothing configured the
    // pane shows these practices and one action that adopts them.
    val suggestedHabits = repository.defaultHabitsToSeed

    var selectedTab by remember { mutableIntStateOf(0) }

    var showSinAvoiderModal by remember { mutableStateOf(false) }
    var selectedSinOption by remember { mutableStateOf<SinAvoiderOption?>(null) }
    var victoryNote by remember { mutableStateOf("") }

    val allDayLabel = currentLanguage.pick("Todo el día", "All day", "Todo o dia")

    var showAddHabitDialog by remember { mutableStateOf(false) }
    var newHabitTitle by remember { mutableStateOf("") }
    var newHabitDescription by remember { mutableStateOf("") }
    var newHabitCategory by remember { mutableStateOf("PRAYER") }
    var newHabitTime by remember { mutableStateOf(allDayLabel) }

    val completedHabitIds = remember(todayCompletions) {
        todayCompletions.map { it.habitId }.toSet()
    }

    val completionRate = if (DailyHabits.isNotEmpty()) {
        completedHabitIds.size.toFloat() / DailyHabits.size.toFloat()
    } else 0f

    // The full names of the two halves of the screen head their own pane; the
    // selector carries short labels, because a 23-character segment is
    // unreadable at 360dp in any of the three identities.
    val tabLabels = listOf(
        currentLanguage.pick("Regla de vida", "Rule of life", "Regra de vida"),
        currentLanguage.pick("En la tentación", "In temptation", "Na tentação")
    )
    val screenTitle = currentLanguage.pick(
        "Hábitos y Virtudes",
        "Habits & Virtues",
        "Hábitos e Virtudes"
    )

    fun openSinAvoider(option: SinAvoiderOption) {
        selectedSinOption = option
        showSinAvoiderModal = true
    }

    fun toggleHabit(habit: SpiritualHabitEntity, wasDone: Boolean) {
        scope.launch {
            if (wasDone) {
                dao.deleteCompletionForDate(habit.id, todayDateStr)
                dao.updateHabit(
                    habit.copy(
                        currentStreak = maxOf(0, habit.currentStreak - 1),
                        totalCompletions = maxOf(0, habit.totalCompletions - 1)
                    )
                )
            } else {
                dao.insertCompletion(
                    HabitCompletionLogEntity(
                        habitId = habit.id,
                        completionDate = todayDateStr
                    )
                )
                val newCurrent = habit.currentStreak + 1
                dao.updateHabit(
                    habit.copy(
                        currentStreak = newCurrent,
                        bestStreak = maxOf(habit.bestStreak, newCurrent),
                        lastCompletedDate = todayDateStr,
                        totalCompletions = habit.totalCompletions + 1
                    )
                )
            }
        }
    }

    fun deleteHabit(habit: SpiritualHabitEntity) {
        scope.launch {
            dao.deleteHabit(habit)
            dao.deleteCompletionForDate(habit.id, todayDateStr)
        }
    }

    /** One check/uncheck of a fixed daily habit for today (one log row). */
    fun toggleDailyHabit(habitId: String, wasDone: Boolean) {
        scope.launch {
            if (wasDone) {
                dao.deleteCompletionForDate(habitId, todayDateStr)
            } else {
                dao.insertCompletion(
                    HabitCompletionLogEntity(
                        habitId = habitId,
                        completionDate = todayDateStr
                    )
                )
            }
        }
    }

    /** Where tapping a habit's text goes; the circle itself only toggles. */
    fun openDailyHabit(action: DailyHabitAction) {
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

    // Prayer reminders live here as a full-screen overlay; the Habits screen
    // itself is untouched while they are open.
    var showReminders by remember { mutableStateOf(false) }

    // Reminders overlay as a Box/stack: the Habits Column stays composed and
    // RemindersScreen draws over it when showReminders is true. No early
    // return, so the composable hook order below stays stable.
    Box(modifier = modifier.fillMaxSize()) {
        Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("habits_and_virtues_screen")
    ) {
        // AppSegmentedTabs takes no tag per option, so the two tab tags name
        // the pane each option reveals. Each pane opens with the same
        // photographic hero band (Home standard) and carries the reminders
        // entry point as its first action below the tabs, so it stays
        // reachable from either tab without sitting above the hero.
        if (selectedTab == 0) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("tab_habits_rule"),
                // Bottom clearance beyond the trailing spacer: enough scroll
                // room for the "Prácticas diarias" heading and its first
                // checklist row to move fully clear of the dock instead of
                // stranding the heading alone at the bottom edge.
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    bottom = design.spacing.contentBottomGap + MinTouchTarget
                )
            ) {
                // Photographic hero band on top (Home standard): full-bleed
                // candle photo, 220dp, fading into the paper canvas, with
                // the serene screen title overlaid low in the faded zone.
                // This is the screen's single title; paneHeader no longer
                // repeats it below the band.
                item(key = "habits_hero") {
                    HabitsHeroBand(title = screenTitle, heroPhoto = heroPhoto)
                }

                paneHeader(
                    tabLabels = tabLabels,
                    selectedTab = selectedTab,
                    onSelectTab = { selectedTab = it }
                )

                item(key = "habits_reminders") {
                    // Entry point to the prayer-reminder overlay. Secondary
                    // (ghost) on purpose: the suggested-habits CTA below is
                    // the screen's one primary; two black pills side by side
                    // read as one duplicated act.
                    AppSecondaryButton(
                        label = currentLanguage.pick(
                            "Recordatorios de oración",
                            "Prayer reminders",
                            "Lembretes de oração"
                        ),
                        onClick = { showReminders = true },
                        leadingIcon = Icons.Outlined.Schedule,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .padding(horizontal = design.spacing.gutter)
                            .testTag("open_reminders")
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                }

                // Fixed daily rule (exactly twelve practices, no custom
                // rows): three groups — morning, day, nightfall — each
                // checkable per day; tapping the text opens the practice.
                item {
                    FidelityCard(
                        completedCount = completedHabitIds.size,
                        totalCount = DailyHabits.size,
                        completionRate = completionRate,
                        currentLanguage = currentLanguage
                    )
                }

                item {
                    DailyCompletionCard(
                        todayDone = completedHabitIds.size,
                        todayTotal = DailyHabits.size,
                        allCompletions = allCompletions,
                        todayDateStr = todayDateStr,
                        dailyIds = DailyHabits.map { it.id }.toSet(),
                        currentLanguage = currentLanguage
                    )
                }

                dailyHabitGroups.forEachIndexed { groupIndex, groupHabits ->
                    item {
                        AppSectionHeader(
                            title = dailyHabitGroupLabel(groupIndex, currentLanguage),
                            subtitle = currentLanguage.pick(
                                "${groupHabits.count { completedHabitIds.contains(it.id) }} de ${groupHabits.size} hoy",
                                "${groupHabits.count { completedHabitIds.contains(it.id) }} of ${groupHabits.size} today",
                                "${groupHabits.count { completedHabitIds.contains(it.id) }} de ${groupHabits.size} hoje"
                            )
                        )
                    }
                    item {
                        // Inset grouped list like Bible's book directory:
                        // AppListGroup insets by design.spacing.gutter (20dp
                        // in Ive), clips to shapes.listGroup and paints each
                        // row hairline itself, so no manual AppDivider.
                        AppListGroup(
                            modifier = Modifier.testTag(
                                if (groupIndex == 0) "habits_checklist_card"
                                else "habits_checklist_card_$groupIndex"
                            )
                        ) {
                            groupHabits.forEach { habit ->
                                DailyHabitRow(
                                    habit = habit,
                                    isCompletedToday = completedHabitIds.contains(habit.id),
                                    currentLanguage = currentLanguage,
                                    onToggleCompletion = {
                                        toggleDailyHabit(habit.id, completedHabitIds.contains(habit.id))
                                    },
                                    onOpen = { openDailyHabit(habit.action) }
                                )
                            }
                        }
                    }
                }

                item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("tab_sin_avoider")
            ) {
                // Same hero band on top as the other pane (Home standard),
                // so switching tabs never moves the screen's title.
                item(key = "habits_hero") {
                    HabitsHeroBand(title = screenTitle, heroPhoto = heroPhoto)
                }

                paneHeader(
                    tabLabels = tabLabels,
                    selectedTab = selectedTab,
                    onSelectTab = { selectedTab = it }
                )

                item(key = "habits_reminders") {
                    AppSecondaryButton(
                        label = currentLanguage.pick(
                            "Recordatorios de oración",
                            "Prayer reminders",
                            "Lembretes de oração"
                        ),
                        onClick = { showReminders = true },
                        leadingIcon = Icons.Outlined.Schedule,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .padding(horizontal = design.spacing.gutter)
                            .testTag("open_reminders")
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                }

                item {
                    GraceAntidoteCard(
                        totalVictories = totalVictories,
                        currentLanguage = currentLanguage,
                        onTriggerEmergency = { openSinAvoider(repository.sinAvoiderCatalog.first()) }
                    )
                }

                item {
                    AppSectionHeader(
                        title = currentLanguage.pick(
                            "Victoria mes a mes",
                            "Victory month by month",
                            "Vitória mês a mês"
                        )
                    )
                }

                item(key = "temptation_calendar") {
                    TemptationCalendar(
                        counts = temptationCounts,
                        currentLanguage = currentLanguage,
                        fellCounts = temptationFellCounts
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                }

                item {
                    AppSectionHeader(
                        title = currentLanguage.pick(
                            "Las siete raíces",
                            "The seven roots",
                            "As sete raízes"
                        )
                    )
                }

                item {
                    AppListGroup {
                        repository.sinAvoiderCatalog.forEach { option ->
                            val count = sinOvercomeLogs.count { it.sinKey == option.sinKey }
                            AppListRow(
                                title = option.title.get(currentLanguage),
                                subtitle = currentLanguage.pick(
                                    "Virtud contraria: ${option.counterVirtue.get(currentLanguage)}",
                                    "Remedy virtue: ${option.counterVirtue.get(currentLanguage)}",
                                    "Virtude contrária: ${option.counterVirtue.get(currentLanguage)}"
                                ) + "\n" + option.advice.get(currentLanguage),
                                trailingText = if (count > 0) count.toString() else null,
                                onClick = { openSinAvoider(option) },
                                modifier = Modifier
                                    .heightIn(
                                        min = maxOf(
                                            design.spacing.rowMinHeight,
                                            MinTouchTarget
                                        )
                                    )
                                    .testTag("card_overcome_${option.sinKey}")
                            )
                        }
                    }
                }

                if (sinOvercomeLogs.isNotEmpty()) {
                    item { AppOrnament() }

                    item {
                        AppSectionHeader(
                            title = currentLanguage.pick(
                                "Historial de victorias en Cristo",
                                "Victories record in Christ",
                                "Histórico de vitórias em Cristo"
                            ),
                            subtitle = currentLanguage.pick(
                                "$totalVictories victorias",
                                "$totalVictories victories",
                                "$totalVictories vitórias"
                            )
                        )
                    }

                    item {
                        AppListGroup {
                            sinOvercomeLogs.take(10).forEach { log ->
                                VictoryLogRow(log = log, currentLanguage = currentLanguage)
                            }
                        }
                    }
                }

                item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
            }
        }
    }

        if (showReminders) {
            RemindersScreen(
                repository = repository,
                onBack = { showReminders = false },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    if (showSinAvoiderModal && selectedSinOption != null) {
        val option = selectedSinOption!!
        val hailMaryPrayer = when (currentLanguage) {
            AppLanguage.ES -> "Dios te salve, María, llena eres de gracia; el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén."
            AppLanguage.EN -> "Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen."
            AppLanguage.PT -> "Ave Maria, cheia de graça, o Senhor é convosco. Bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora de nossa morte. Amém."
        }
        val styles = LocalAppTextStyles.current
        val palette = design.palette

        AlertDialog(
            onDismissRequest = {
                showSinAvoiderModal = false
                ttsManager.stop()
            },
            // Was the default narrow AlertDialog width: the advice and the
            // prayer wrapped into a thin column. Fill the window up to 520dp
            // with a 16dp side inset so the text has room to breathe.
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 520.dp)
                .padding(horizontal = 16.dp),
            shape = design.shapes.dialog,
            containerColor = palette.groupedSurface,
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = palette.gold,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap))
                    Text(
                        text = currentLanguage.pick(
                            "¡Victoria sobre la Tentación!",
                            "Victory Over Temptation!",
                            "Vitória sobre a Tentação!"
                        ),
                        style = styles.sectionTitle,
                        color = palette.textPrimary
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "${option.title.get(currentLanguage)} ➔ " +
                            option.counterVirtue.get(currentLanguage),
                        style = styles.rowTitle.copy(
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 26.sp
                        ),
                        color = palette.accent
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = option.advice.get(currentLanguage),
                        style = MaterialTheme.typography.bodyLarge,
                        color = palette.textSecondary
                    )

                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppDivider()
                    Spacer(Modifier.height(design.spacing.cardGap))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = caps(currentLanguage.pick("Reza un Ave María ahora", "Pray one Hail Mary now", "Reze uma Ave Maria agora")),
                            style = styles.sectionEyebrow,
                            color = palette.gold,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = {
                                ttsManager.setLanguage(currentLanguage)
                                ttsManager.speakSingle(hailMaryPrayer, isFemaleVoice = true)
                            },
                            // Was 32.dp: below the M3 48dp minimum, so the
                            // only TTS control on the screen was hard to hit.
                            modifier = Modifier.size(MinTouchTarget)
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = currentLanguage.pick(
                                    "Escuchar oración",
                                    "Listen to the prayer",
                                    "Ouvir a oração"
                                ),
                                tint = palette.accent,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    // Tonal container for the prayer itself: accentMuted is
                    // the identity's tinted surface in both light and dark,
                    // so the text keeps its contrast without a hard-coded tone.
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(design.shapes.card)
                            .background(palette.accentMuted)
                            .padding(design.spacing.cardGap)
                    ) {
                        Text(
                            text = hailMaryPrayer,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontStyle = FontStyle.Italic
                            ),
                            color = palette.textPrimary
                        )
                    }

                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppDivider()
                    Spacer(Modifier.height(design.spacing.cardGap))

                    // Running counts for victories and falls: today, this
                    // week, this month. All three windows reset on their own
                    // boundary because every entry is keyed by date.
                    TemptationStatsBlock(
                        label = currentLanguage.pick("Vencí", "I overcame", "Venci"),
                        counts = temptationCounts,
                        tagPrefix = "victory_stats",
                        currentLanguage = currentLanguage
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    TemptationStatsBlock(
                        label = currentLanguage.pick("Caí", "I fell", "Caí"),
                        counts = temptationFellCounts,
                        tagPrefix = "fall_stats",
                        currentLanguage = currentLanguage
                    )

                    OutlinedTextField(
                        value = victoryNote,
                        onValueChange = { victoryNote = it },
                        label = {
                            Text(
                                text = currentLanguage.pick(
                                    "Nota u ofrenda (opcional)",
                                    "Note or offering (optional)",
                                    "Nota ou oferta (opcional)"
                                ),
                                style = styles.rowSubtitle
                            )
                        },
                        shape = design.shapes.field,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2
                    )
                }
            },
            confirmButton = {
                // Stacked full-width instead of the side-by-side AlertDialog
                // button row: in the wider dialog the two labels no longer
                // need to fight for horizontal space.
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppPrimaryButton(
                        label = currentLanguage.pick(
                            "Registrar Victoria",
                            "Record Victory",
                            "Registar Vitória"
                        ),
                        onClick = {
                            scope.launch {
                                dao.insertSinOvercomeLog(
                                    SinOvercomeLogEntity(
                                        sinKey = option.sinKey,
                                        counterVirtue = option.counterVirtue.get(currentLanguage),
                                        prayerUsed = "HAIL_MARY",
                                        note = victoryNote
                                    )
                                )
                                // Same victory, per-day counter for the calendar.
                                temptationStore.recordVictory()
                                victoryNote = ""
                                showSinAvoiderModal = false
                                ttsManager.stop()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .testTag("confirm_victory_button")
                    )
                    AppSecondaryButton(
                        label = currentLanguage.pick(
                            "Caí en una tentación",
                            "I fell into temptation",
                            "Caí numa tentação"
                        ),
                        onClick = {
                            scope.launch {
                                // Separate counter, same calendar styling.
                                temptationStore.recordFall()
                                victoryNote = ""
                                showSinAvoiderModal = false
                                ttsManager.stop()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .testTag("record_fall_button")
                    )
                    AppSecondaryButton(
                        label = currentLanguage.pick("Cerrar", "Close", "Fechar"),
                        onClick = {
                            showSinAvoiderModal = false
                            ttsManager.stop()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                    )
                }
            }
        )
    }

    if (showAddHabitDialog) {
        val styles = LocalAppTextStyles.current
        val palette = design.palette

        AlertDialog(
            onDismissRequest = { showAddHabitDialog = false },
            shape = design.shapes.dialog,
            containerColor = palette.groupedSurface,
            title = {
                Text(
                    text = currentLanguage.pick(
                        "Nuevo Hábito Espiritual",
                        "New Spiritual Habit",
                        "Novo Hábito Espiritual"
                    ),
                    style = styles.sectionTitle,
                    color = palette.textPrimary
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    OutlinedTextField(
                        value = newHabitTitle,
                        onValueChange = { newHabitTitle = it },
                        label = {
                            Text(
                                text = currentLanguage.pick(
                                    "Nombre del Hábito",
                                    "Habit Name",
                                    "Nome do Hábito"
                                ),
                                style = styles.rowSubtitle
                            )
                        },
                        shape = design.shapes.field,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_habit_title"),
                        singleLine = true
                    )

                    Spacer(Modifier.height(design.spacing.cardGap))
                    OutlinedTextField(
                        value = newHabitDescription,
                        onValueChange = { newHabitDescription = it },
                        label = {
                            Text(
                                text = currentLanguage.pick(
                                    "Propósito o Descripción",
                                    "Purpose or Description",
                                    "Propósito ou Descrição"
                                ),
                                style = styles.rowSubtitle
                            )
                        },
                        shape = design.shapes.field,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2
                    )

                    Spacer(Modifier.height(design.spacing.cardGap))
                    OutlinedTextField(
                        value = newHabitTime,
                        onValueChange = { newHabitTime = it },
                        label = {
                            Text(
                                text = currentLanguage.pick(
                                    "Horario Sugerido",
                                    "Suggested Time",
                                    "Horário Sugerido"
                                ),
                                style = styles.rowSubtitle
                            )
                        },
                        shape = design.shapes.field,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(design.spacing.cardGap))
                    Text(
                        text = caps(currentLanguage.pick("Categoría", "Category", "Categoria")),
                        style = styles.sectionEyebrow,
                        color = palette.textSecondary
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("PRAYER", "FASTING", "SACRAMENT", "CHARITY", "VIRTUE").forEach { cat ->
                            AppChip(
                                label = categoryLabel(cat, currentLanguage),
                                selected = newHabitCategory == cat,
                                onClick = { newHabitCategory = cat },
                                modifier = Modifier.heightIn(min = MinTouchTarget)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                AppPrimaryButton(
                    label = currentLanguage.pick("Guardar", "Save", "Salvar"),
                    onClick = {
                        if (newHabitTitle.isNotBlank()) {
                            scope.launch {
                                dao.insertHabit(
                                    SpiritualHabitEntity(
                                        id = "custom_" + UUID.randomUUID().toString().take(8),
                                        titleEs = newHabitTitle,
                                        titleEn = newHabitTitle,
                                        titlePt = newHabitTitle,
                                        description = newHabitDescription.ifBlank {
                                            currentLanguage.pick(
                                                "Hábito espiritual personalizado",
                                                "Custom spiritual habit",
                                                "Hábito espiritual personalizado"
                                            )
                                        },
                                        category = newHabitCategory,
                                        timeSuggestion = newHabitTime.ifBlank { allDayLabel },
                                        iconName = "star",
                                        isCustom = true
                                    )
                                )
                                newHabitTitle = ""
                                newHabitDescription = ""
                                showAddHabitDialog = false
                            }
                        }
                    },
                    enabled = newHabitTitle.isNotBlank(),
                    modifier = Modifier.heightIn(min = MinTouchTarget).testTag("save_custom_habit_button")
                )
            },
            dismissButton = {
                AppSecondaryButton(
                    label = currentLanguage.pick("Cancelar", "Cancel", "Cancelar"),
                    onClick = { showAddHabitDialog = false }
                )
            }
        )
    }
}

/**
 * Photographic hero band on top, Home standard: the candle_prayer photo
 * fills a full-width 220dp band, melting into the paper canvas through a
 * vertical gradient, with the serene screen title overlaid low in the faded
 * zone. The title stays palette.textPrimary on a lightened scrim (never
 * white-on-black), and the band falls back to a canvas gradient when the
 * decode fails, so legibility never depends on the photo loading.
 */
@Composable
private fun HabitsHeroBand(title: String, heroPhoto: ImageBitmap?) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(palette.canvas)
            // Kept from the old in-card photo strip so the established tag
            // still names the screen's photograph.
            .testTag("fidelity_card_photo")
    ) {
        val hero = heroPhoto
        if (hero != null) {
            Image(
                bitmap = hero,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("screen_header_illustration"),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                palette.accentMuted,
                                palette.canvas
                            )
                        )
                    )
                    .testTag("screen_header_illustration")
            )
        }
        // Fade: clear over the upper photo, canvas by the foot of the band.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            palette.canvas
                        )
                    )
                )
        )
        // Light scrim behind the title zone, Home-style: the photo is
        // lightened to the paper canvas under the text rather than darkened.
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            palette.canvas.copy(alpha = 0.55f),
                            palette.canvas.copy(alpha = 0.92f),
                            palette.canvas
                        )
                    )
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(
                    horizontal = design.spacing.gutter,
                    vertical = 24.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = styles.screenTitle.copy(fontWeight = FontWeight.W400),
                color = palette.textPrimary,
                maxLines = 1
            )
        }
    }
}

/**
 * The opening of either pane below the photographic hero band: the selector
 * that swaps between the two. The screen title rides on the hero band above
 * (Home standard), so it is not repeated here.
 */
private fun LazyListScope.paneHeader(
    tabLabels: List<String>,
    selectedTab: Int,
    onSelectTab: (Int) -> Unit
) {
    item {
        val design = LocalAppDesign.current
        AppSegmentedTabs(
            options = tabLabels,
            selectedIndex = selectedTab,
            onSelect = onSelectTab,
            modifier = Modifier
                .heightIn(min = MinTouchTarget)
                .padding(horizontal = segmentedTabsInset())
        )
        Spacer(Modifier.height(design.spacing.sectionGap / 2))
    }
}

/**
 * No kit insets its own segmented control, so the gutter is the caller's to
 * give. Vatican is the exception: its strip is a ruled band whose hairline runs
 * the full width of the leaf.
 */
@Composable
private fun segmentedTabsInset(): Dp {
    val design = LocalAppDesign.current
    return design.spacing.gutter
}

/** Today's fidelity, calm and ring-only on plain paper: eyebrow, ring (count only), one subtitle. */
@Composable
private fun FidelityCard(
    completedCount: Int,
    totalCount: Int,
    completionRate: Float,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    // Frosted card like Home cards (AppCard + frostedCardChrome at
    // design.shapes.card, 20dp in Ive). The screen's candle photograph now
    // heads the screen as the 220dp hero band above (Home standard), so the
    // card carries only the ring and its count — no second photo strip.
    AppCard(
        modifier = modifier
            .padding(horizontal = design.spacing.gutter)
            .frostedCardChrome()
    ) {
        Column(Modifier.fillMaxWidth()) {
            // Serene, centred header: eyebrow, ring, count, one subtitle line.
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = caps(currentLanguage.pick("Fidelidad espiritual", "Spiritual fidelity", "Fidelidade espiritual")),
                    style = styles.sectionEyebrow,
                    color = palette.textPrimary
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                FidelityRing(
                    completionRate = completionRate,
                    completedCount = completedCount,
                    totalCount = totalCount,
                    currentLanguage = currentLanguage
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = when {
                        completionRate >= 1.0f -> currentLanguage.pick(
                            "¡Día consagrado en plenitud!",
                            "A day consecrated in full!",
                            "Dia consagrado em plenitude!"
                        )
                        completionRate >= 0.5f -> currentLanguage.pick(
                            "Buen camino, persevera hasta la noche.",
                            "Good progress — persevere until nightfall.",
                            "Bom caminho, persevere até à noite."
                        )
                        else -> currentLanguage.pick(
                            "Empieza con un ofrecimiento de obras.",
                            "Begin with an offering of the day's work.",
                            "Comece com um oferecimento de obras."
                        )
                    },
                    style = styles.rowSubtitle,
                    color = palette.textPrimary,
                    maxLines = 1
                )
            }
        }
    }
}

/**
 * The day's completion as a ring. No kit component draws one, so it is built
 * from the palette and closed with a square cap where the identity has no
 * rounded forms at all. The count (not a percentage) is the centre label
 * and the contentDescription, so the value does not live only in the arc.
 */
@Composable
private fun FidelityRing(
    completionRate: Float,
    completedCount: Int = 0,
    totalCount: Int = 0,
    currentLanguage: AppLanguage? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val fraction = completionRate.coerceIn(0f, 1f)
    val cap = StrokeCap.Round
    // Expert v3: single progress encoding — count only, no %. The arc
    // still shows the fraction visually, but the readable value is the
    // count, in the centre and in the description.
    val ringDescription = if (currentLanguage != null) {
        currentLanguage.pick(
            "$completedCount de $totalCount cumplidos hoy",
            "$completedCount of $totalCount kept today",
            "$completedCount de $totalCount cumpridos hoje"
        )
    } else {
        "$completedCount/$totalCount"
    }

    Box(
        modifier = modifier
            .size(RingSize)
            .semantics { contentDescription = ringDescription },
        contentAlignment = Alignment.Center
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = RingStroke.toPx()
            val box = Size(size.width - stroke, size.height - stroke)
            val origin = Offset(stroke / 2f, stroke / 2f)
            drawArc(
                color = palette.selectionTint,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = origin,
                size = box,
                style = Stroke(width = stroke, cap = cap)
            )
            if (fraction > 0f) {
                drawArc(
                    color = palette.accent,
                    startAngle = -90f,
                    sweepAngle = fraction * 360f,
                    useCenter = false,
                    topLeft = origin,
                    size = box,
                    style = Stroke(width = stroke, cap = cap)
                )
            }
        }
        Text(
            text = "$completedCount/$totalCount",
            style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
            color = palette.textPrimary,
            maxLines = 1
        )
    }
}

// ----------------------------------------------------- fixed daily rule

/** Where tapping a fixed daily habit's text opens its practice. */
private enum class DailyHabitAction {
    NONE, HOW_TO_PRAY, MISSAL, ANGELUS, CHAPLET, JACULATORY, EXAMEN, ROSARY, TRADITIONAL
}

/** One of the exactly twelve fixed daily practices. */
private data class DailyHabit(
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
    fun title(language: AppLanguage): String = when (language) {
        AppLanguage.ES -> titleEs
        AppLanguage.EN -> titleEn
        AppLanguage.PT -> titlePt
    }
    fun subtitle(language: AppLanguage): String = when (language) {
        AppLanguage.ES -> subtitleEs
        AppLanguage.EN -> subtitleEn
        AppLanguage.PT -> subtitlePt
    }
}

/**
 * Totally twelve, in rule order: 0 = morning (4), 1 = day (5),
 * 2 = nightfall (3). Titles in PT follow the requested rule; ES/EN
 * carry the same practices in the other two languages.
 */
private val DailyHabits: List<DailyHabit> = listOf(
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

private val dailyHabitGroups: List<List<DailyHabit>> = (0..2).map { group ->
    DailyHabits.filter { it.group == group }
}

private fun dailyHabitGroupLabel(group: Int, language: AppLanguage): String = when (group) {
    0 -> language.pick("Mañana", "Morning", "Manhã")
    1 -> language.pick("Día", "Day", "Dia")
    else -> language.pick("Anoitecer", "Nightfall", "Anoitecer")
}

/**
 * One fixed daily practice. The leading circle toggles today's check; the
 * text opens the practice where one is linked (a trailing › marks those).
 */
@Composable
private fun DailyHabitRow(
    habit: DailyHabit,
    isCompletedToday: Boolean,
    currentLanguage: AppLanguage,
    onToggleCompletion: () -> Unit,
    onOpen: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val rowMinHeight = maxOf(design.spacing.rowMinHeight, MinTouchTarget)

    Box(Modifier.testTag("habit_card_${habit.id}")) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = rowMinHeight)
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .semantics {
                    stateDescription = if (isCompletedToday) {
                        currentLanguage.pick("Cumplido hoy", "Kept today", "Cumprido hoje")
                    } else {
                        currentLanguage.pick("Pendiente hoy", "Pending today", "Pendente hoje")
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .then(
                        if (isCompletedToday) {
                            Modifier.background(palette.accent)
                        } else {
                            Modifier.border(1.5.dp, palette.textTertiary, CircleShape)
                        }
                    )
                    .clickable(role = Role.Button, onClick = onToggleCompletion)
                    .testTag("toggle_habit_${habit.id}"),
                contentAlignment = Alignment.Center
            ) {
                if (isCompletedToday) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = if (design.isDark) Color.Black else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .then(
                        if (habit.action != DailyHabitAction.NONE) {
                            Modifier.clickable(role = Role.Button, onClick = onOpen)
                        } else {
                            Modifier
                        }
                    )
            ) {
                Text(
                    text = habit.title(currentLanguage),
                    style = styles.rowTitle,
                    color = if (isCompletedToday) palette.accent else palette.textPrimary
                )
                Spacer(Modifier.height(1.dp))
                Text(
                    text = habit.subtitle(currentLanguage),
                    style = styles.rowSubtitle,
                    color = palette.textSecondary
                )
            }
            if (habit.action != DailyHabitAction.NONE) {
                Text(
                    text = "›",
                    style = styles.rowTitle,
                    color = palette.textTertiary
                )
            }
        }
    }
}

/**
 * Daily completion control: today's X/Y plus the current month — checked
 * practices so far over the practices possible up to today, the distinct
 * days with any check, and one dot per day elapsed this month.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DailyCompletionCard(
    todayDone: Int,
    todayTotal: Int,
    allCompletions: List<HabitCompletionLogEntity>,
    todayDateStr: String,
    dailyIds: Set<String>,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    val monthPrefix = todayDateStr.take(7)
    val dayOfMonth = todayDateStr.takeLast(2).toIntOrNull() ?: 1
    val monthLogs = remember(allCompletions, monthPrefix) {
        allCompletions.filter {
            it.completionDate.startsWith(monthPrefix) && it.habitId in dailyIds
        }
    }
    val monthCheckedPairs = remember(monthLogs) {
        monthLogs.map { it.habitId to it.completionDate }.distinct().size
    }
    val monthPossible = todayTotal * dayOfMonth
    val activeDays = remember(monthLogs) {
        monthLogs.map { it.completionDate }.distinct().toSet()
    }
    val monthRate = if (monthPossible > 0) monthCheckedPairs.toFloat() / monthPossible.toFloat() else 0f

    AppCard(
        modifier = modifier
            .padding(horizontal = design.spacing.gutter)
            .testTag("daily_completion_control")
    ) {
        Text(
            text = currentLanguage.pick(
                "Control de cumplimiento",
                "Completion control",
                "Controle de cumprimento"
            ),
            style = styles.sectionEyebrow,
            color = palette.textSecondary
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = currentLanguage.pick(
                "Hoy: $todayDone de $todayTotal",
                "Today: $todayDone of $todayTotal",
                "Hoje: $todayDone de $todayTotal"
            ),
            style = styles.rowTitle,
            color = palette.textPrimary,
            modifier = Modifier.semantics { stateDescription = "today $todayDone/$todayTotal" }
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = currentLanguage.pick(
                "Este mes: $monthCheckedPairs de $monthPossible · ${activeDays.size} ${if (activeDays.size == 1) "día activo" else "días activos"}",
                "This month: $monthCheckedPairs of $monthPossible · ${activeDays.size} ${if (activeDays.size == 1) "active day" else "active days"}",
                "Este mês: $monthCheckedPairs de $monthPossible · ${activeDays.size} ${if (activeDays.size == 1) "dia ativo" else "dias ativos"}"
            ),
            style = styles.rowSubtitle,
            color = palette.textPrimary,
            modifier = Modifier.testTag("habits_monthly_summary")
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        LinearProgressIndicator(
            progress = { monthRate.coerceIn(0f, 1f) },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .semantics {
                    stateDescription = "$monthCheckedPairs/$monthPossible"
                },
            color = palette.accent,
            trackColor = palette.selectionTint
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (day in 1..dayOfMonth) {
                val date = "$monthPrefix-${day.toString().padStart(2, '0')}"
                val active = date in activeDays
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(if (active) palette.accent else palette.selectionTint)
                        .semantics { contentDescription = date }
                        .testTag("habit_month_day_$day"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day.toString(),
                        style = styles.rowSubtitle,
                        color = if (active) Color.White else palette.textSecondary
                    )
                }
            }
        }
    }
}

/**
 * One practice of the rule. Tapping the row is what keeps or un-keeps it today.
 * Ive checklist: a green checked circle leads the row when kept today
 * (palette.accent — green #35751A under Ive), an open ring when pending.
 * The category glyph moves to a muted trailing mark so the check stays the
 * row's primary affordance. Toggle logic, tags and semantics are unchanged.
 */
@Composable
private fun HabitRow(
    habit: SpiritualHabitEntity,
    isCompletedToday: Boolean,
    currentLanguage: AppLanguage,
    onToggleCompletion: () -> Unit,
    onDeleteCustomHabit: (() -> Unit)?
) {
    val title = when (currentLanguage) {
        AppLanguage.ES -> habit.titleEs
        AppLanguage.EN -> habit.titleEn
        AppLanguage.PT -> habit.titlePt
    }
    val styles = LocalAppTextStyles.current

    // Singular/plural in all three languages: "1 día" not "1 días".
    val streakLine = if (habit.currentStreak > 0) {
        "\n" + if (habit.currentStreak == 1) {
            currentLanguage.pick(
                "1 día de fidelidad (Récord: ${habit.bestStreak})",
                "1 day of fidelity (best: ${habit.bestStreak})",
                "1 dia de fidelidade (recorde: ${habit.bestStreak})"
            )
        } else {
            currentLanguage.pick(
                "${habit.currentStreak} días de fidelidad (Récord: ${habit.bestStreak})",
                "${habit.currentStreak} days of fidelity (best: ${habit.bestStreak})",
                "${habit.currentStreak} dias de fidelidade (recorde: ${habit.bestStreak})"
            )
        }
    } else ""

    // Two tags on one row: the habit's card is now the habit's toggle, and a
    // click anywhere in it does what the toggle used to.
    val design = LocalAppDesign.current
    val palette = design.palette
    val rowMinHeight = maxOf(design.spacing.rowMinHeight, MinTouchTarget)
    Box(Modifier.testTag("habit_card_${habit.id}")) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(role = Role.Button, onClick = onToggleCompletion)
                .heightIn(min = rowMinHeight)
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .semantics {
                    stateDescription = if (isCompletedToday) {
                        currentLanguage.pick("Cumplido hoy", "Kept today", "Cumprido hoje")
                    } else {
                        currentLanguage.pick("Pendiente hoy", "Pending today", "Pendente hoje")
                    }
                }
                .testTag("toggle_habit_${habit.id}"),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Green checked circle when kept, open ring when pending.
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .then(
                        if (isCompletedToday) {
                            Modifier.background(palette.accent)
                        } else {
                            Modifier.border(1.5.dp, palette.textTertiary, CircleShape)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompletedToday) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = if (design.isDark) Color.Black else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Column(Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = styles.rowTitle,
                    color = if (isCompletedToday) palette.accent else palette.textPrimary
                )
                Spacer(Modifier.height(1.dp))
                Text(
                    text = habitSubtitleText(habit, currentLanguage) + streakLine,
                    style = styles.rowSubtitle,
                    color = palette.textSecondary
                )
            }
        }
    }

    if (onDeleteCustomHabit != null) {
        MonoHabitRow(
            title = currentLanguage.pick(
                "Eliminar hábito personalizado",
                "Delete custom habit",
                "Excluir hábito personalizado"
            ),
            icon = Icons.Outlined.Delete,
            subtitle = null,
            onClick = onDeleteCustomHabit,
            modifier = Modifier.heightIn(min = MinTouchTarget)
        )
    }
}

/** The way back: the count of temptations already overcome, and the prayer. */
@Composable
private fun GraceAntidoteCard(
    totalVictories: Int,
    currentLanguage: AppLanguage,
    onTriggerEmergency: () -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    AppCard(modifier = modifier.padding(horizontal = design.spacing.gutter).frostedCardChrome()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Tonal badge behind the shield: accentMuted carries the separation
            // in Material, and the dark variant of the same token does in dark mode.
            Box(
                modifier = Modifier
                    .size(MinTouchTarget)
                    .clip(design.shapes.badge)
                    .background(palette.accentMuted),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = palette.gold,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Column(Modifier.weight(1f)) {
                Text(
                    text = caps(currentLanguage.pick("Antídoto de gracia", "Grace antidote", "Antídoto de graça")),
                    style = styles.sectionEyebrow,
                    color = palette.textSecondary
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = currentLanguage.pick(
                        "$totalVictories tentaciones vencidas en Cristo",
                        "$totalVictories temptations overcome in Christ",
                        "$totalVictories tentações vencidas em Cristo"
                    ),
                    style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                    color = palette.textPrimary
                )
            }
        }

        Spacer(Modifier.height(design.spacing.cardGap))
        Text(
            text = when (currentLanguage) {
                AppLanguage.ES -> "«Si sientes la tentación de caer (ira, lujuria, gula, pereza), detente de inmediato, reza un Ave María y vence con la gracia de Dios»."
                AppLanguage.EN -> "“If you feel temptation striking (lust, anger, gluttony, sloth), stop immediately, pray one Hail Mary, and triumph with God's grace.”"
                AppLanguage.PT -> "“Se sentir a tentação bater (ira, luxúria, gula, preguiça), pare de imediato, reze uma Ave Maria e vença com a graça de Deus.”"
            },
            style = styles.quote.copy(fontStyle = FontStyle.Italic),
            color = palette.textSecondary
        )

        Spacer(Modifier.height(design.spacing.cardPadding))
        AppPrimaryButton(
            label = currentLanguage.pick(
                "Vencí una tentación",
                "I overcame a temptation",
                "Venci uma tentação"
            ),
            onClick = onTriggerEmergency,
            leadingIcon = Icons.Default.Bolt,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .testTag("emergency_overcome_button")
        )
    }
}

/**
 * A habit/category row in the liturgical monochrome set: the glyph is
 * always palette.textSecondary, never the kit row's saturated category
 * wells (orange/blue/purple/red). Green is reserved for completed checks
 * and the progress ring, so nothing here carries colour of its own.
 * Metrics are unchanged — 48dp minimum, role-button when tappable.
 */
@Composable
private fun MonoHabitRow(
    title: String,
    icon: ImageVector,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(role = Role.Button, onClick = onClick) else Modifier)
            .heightIn(min = MinTouchTarget)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = palette.textSecondary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(design.spacing.iconLabelGap))
        Column(Modifier.weight(1f)) {
            Text(title, style = styles.rowTitle, color = palette.textPrimary)
            if (subtitle != null) {
                Spacer(Modifier.height(1.dp))
                Text(subtitle, style = styles.rowSubtitle, color = palette.textSecondary)
            }
        }
    }
}

/** One entry of the victory record. */
@Composable
private fun VictoryLogRow(
    log: SinOvercomeLogEntity,
    currentLanguage: AppLanguage
) {
    val dateStr = remember(log.timestamp) {
        SimpleDateFormat("d MMM, HH:mm", Locale.getDefault()).format(Date(log.timestamp))
    }

    AppListRow(
        title = currentLanguage.pick(
            "Victoria en ${log.counterVirtue}",
            "Victory in ${log.counterVirtue}",
            "Vitória em ${log.counterVirtue}"
        ),
        subtitle = log.note.takeIf { it.isNotBlank() }?.let { "«$it»" },
        trailingText = dateStr,
        showsNavigationAffordance = false
    )
}

/** Labels are cut as capitals only where the identity sets them so. */
@Composable
private fun caps(text: String): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) text.uppercase() else text

private fun SpiritualHabitEntity.localizedTitle(language: AppLanguage): String = when (language) {
    AppLanguage.ES -> titleEs
    AppLanguage.EN -> titleEn
    AppLanguage.PT -> titlePt
}

/**
 * Single time rendering for a habit row: the localized timeSuggestion is
 * the only clock shown. If the stored description already restates that
 * same clock ("Pausa mariana a las 12:00 PM recordando…", timeSuggestion
 * "12:00 PM"), the clock — with its "a las/at" lead-in — is stripped
 * from the description, so the row never reads "12:00 · …12:00 PM".
 * Descriptions without that clock are shown unchanged.
 */
private fun habitSubtitleText(habit: SpiritualHabitEntity, language: AppLanguage): String {
    val localizedTime = com.example.data.models.localizedHabitTime(habit.timeSuggestion, language)
    val timeMinutes = habitClockMinutes(habit.timeSuggestion)
    val description = if (timeMinutes != null && habitClockMinutes(habit.description) == timeMinutes) {
        habit.description
            .replace(Regex("""(?i)\b(a las|las|à\s+as|as|at)\s+\d{1,2}:\d{2}\s*([AaPp][Mm])?\b"""), "")
            .replace(Regex("""\b\d{1,2}:\d{2}\s*([AaPp][Mm])?\b"""), "")
            .replace(Regex("""\s{2,}"""), " ")
            .trim()
            .trim('.', ' ', ',', ';')
            .replaceFirstChar { it.uppercase() }
    } else {
        habit.description
    }
    return if (description.isBlank()) localizedTime else "$localizedTime · $description"
}

/** Minutes since midnight for the first clock time in [text], or null. */
private fun habitClockMinutes(text: String): Int? {
    val match = Regex("""\b(\d{1,2}):(\d{2})\s*([AaPp][Mm])?\b""").find(text) ?: return null
    var hour = match.groupValues[1].toInt()
    val minute = match.groupValues[2].toInt()
    val meridiem = match.groupValues[3]
    if (meridiem.equals("p", ignoreCase = true) && hour < 12) hour += 12
    if (meridiem.equals("a", ignoreCase = true) && hour == 12) hour = 0
    return hour * 60 + minute
}

private fun habitIcon(iconName: String): ImageVector = when (iconName) {
    "wb_sunny" -> Icons.Outlined.WbSunny
    "schedule" -> Icons.Outlined.Schedule
    "self_improvement", "rosary" -> DevotionalGlyphs.rosaryOutlined
    "church" -> Icons.Outlined.Church
    "menu_book" -> Icons.Outlined.MenuBook
    "restaurant" -> Icons.Outlined.Restaurant
    "nights_stay" -> Icons.Outlined.NightsStay
    else -> Icons.Outlined.Star
}

private fun categoryLabel(category: String, language: AppLanguage): String = when (category) {
    "PRAYER" -> language.pick("Oración", "Prayer", "Oração")
    "FASTING" -> language.pick("Ayuno", "Fasting", "Jejum")
    "SACRAMENT" -> language.pick("Sacramento", "Sacrament", "Sacramento")
    "CHARITY" -> language.pick("Caridad", "Charity", "Caridade")
    else -> language.pick("Virtud", "Virtue", "Virtude")
}

private val RingSize = 112.dp
private val RingStroke = 10.dp

/** M3 minimum touch target; rows also honour the identity's rowMinHeight. */
private val MinTouchTarget = 48.dp

/** Frosted-card chrome matching Home cards (see HomeScreen): a soft shadow
 * plus a 1dp hairline stroke over AppCard frosted groupedSurface at
 * design.shapes.card (20dp in Ive). The same values Home uses.
 */
@Composable
private fun Modifier.frostedCardChrome(): Modifier {
    val design = LocalAppDesign.current
    val palette = design.palette
    val radius = if (design.language == com.example.ui.theme.DesignLanguage.IVE) 20.dp else 12.dp
    val width = design.depth.cardBorderWidth.coerceAtLeast(1.dp)
    return this
        .shadow(6.dp, design.shapes.card)
        .drawWithContent {
            drawContent()
            drawRoundRect(
                color = palette.hairline,
                topLeft = Offset.Zero,
                size = size,
                cornerRadius = CornerRadius(radius.toPx()),
                style = Stroke(width = width.toPx())
            )
        }
}

/**
 * One line of running counts for a per-date counter map: today, this week
 * (Mon–Sun), this month. Each window is a sum over date keys, so every one
 * resets on its own boundary (day / week / month rollover). Shared by the
 * victory and fall blocks of the temptation dialog.
 */
@Composable
private fun TemptationStatsBlock(
    label: String,
    counts: Map<String, Int>,
    tagPrefix: String,
    currentLanguage: AppLanguage
) {
    val palette = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    val today = LocalDate.now()
    val todayCount = counts[TemptationStore.dateKey(today)] ?: 0
    val weekCount = TemptationStore.weeklyCount(counts, today)
    val monthCount = TemptationStore.monthlyCount(counts, today)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = styles.sectionEyebrow,
            color = palette.textSecondary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = currentLanguage.pick("$todayCount hoy", "$todayCount today", "$todayCount hoje"),
            style = styles.rowSubtitle,
            color = palette.textPrimary,
            modifier = Modifier.testTag("${tagPrefix}_today")
        )
        Text(
            text = currentLanguage.pick("$weekCount esta semana", "$weekCount this week", "$weekCount esta semana"),
            style = styles.rowSubtitle,
            color = palette.textPrimary,
            modifier = Modifier.testTag("${tagPrefix}_week")
        )
        Text(
            text = currentLanguage.pick("$monthCount este mes", "$monthCount this month", "$monthCount este mês"),
            style = styles.rowSubtitle,
            color = palette.textPrimary,
            modifier = Modifier.testTag("${tagPrefix}_month")
        )
    }
}

/**
 * Decodes bundled photographic hero art (assets/heroes/) down-sampled so its
 * width does not exceed 1080px. Returns null on any failure (missing asset,
 * host without a real decoder in tests) so callers fall back to a gradient
 * band instead of crashing.
 */
private fun decodeSampledBitmap(
    context: Context,
    assetPath: String,
    maxWidthPx: Int = 1080
): ImageBitmap? = try {
    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    context.assets.open(assetPath).use { BitmapFactory.decodeStream(it, null, bounds) }
    if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
        null
    } else {
        var sampleSize = 1
        while (bounds.outWidth / sampleSize > maxWidthPx) sampleSize *= 2
        val options = BitmapFactory.Options().apply { inSampleSize = sampleSize }
        val bitmap: Bitmap? = context.assets.open(assetPath).use {
            BitmapFactory.decodeStream(it, null, options)
        }
        bitmap?.asImageBitmap()
    }
} catch (_: Exception) {
    null
}

