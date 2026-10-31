package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.HabitCompletionLogEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.local.SinOvercomeLogEntity
import com.example.data.local.SpiritualHabitEntity
import com.example.data.models.AppLanguage
import com.example.data.models.SinAvoiderOption
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppMetricTile
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
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
    modifier: Modifier = Modifier
) {
    val currentLanguage by repository.currentLanguage.collectAsState()
    val design = LocalAppDesign.current
    val scope = rememberCoroutineScope()

    val todayDateStr = remember {
        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }

    val habitsList by dao.getAllHabits().collectAsState(initial = emptyList())
    val todayCompletions by dao.getCompletionsForDate(todayDateStr).collectAsState(initial = emptyList())
    val allCompletions by dao.getAllCompletions().collectAsState(initial = emptyList())
    val sinOvercomeLogs by dao.getAllSinOvercomeLogs().collectAsState(initial = emptyList())
    val totalVictories by dao.getTotalVictoriesCount().collectAsState(initial = 0)

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

    val completionRate = if (habitsList.isNotEmpty()) {
        completedHabitIds.size.toFloat() / habitsList.size.toFloat()
    } else 0f

    // The full names of the two halves of the screen head their own pane; the
    // selector carries short labels, because a 23-character segment is
    // unreadable at 360dp in any of the three identities.
    val tabLabels = listOf(
        currentLanguage.pick("Regla de vida", "Rule of life", "Regra de vida"),
        currentLanguage.pick("Gracia S.O.S.", "Grace S.O.S.", "Graça S.O.S.")
    )
    val screenTitle = currentLanguage.pick(
        "Hábitos y Virtudes",
        "Habits & Virtues",
        "Hábitos e Virtudes"
    )
    val screenSubtitle = currentLanguage.pick(
        "Las prácticas que sostienen tu vida en Cristo, y la gracia para cuando caes.",
        "The practices that sustain your life in Christ, and grace for when you fall.",
        "As práticas que sustentam a sua vida em Cristo, e a graça para quando cai."
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("habits_and_virtues_screen")
    ) {
        // AppSegmentedTabs takes no tag per option, so the two tab tags name
        // the pane each option reveals.
        if (selectedTab == 0) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("tab_habits_rule")
            ) {
                paneHeader(
                    title = screenTitle,
                    subtitle = screenSubtitle,
                    tabLabels = tabLabels,
                    selectedTab = selectedTab,
                    onSelectTab = { selectedTab = it }
                )

                if (habitsList.isEmpty()) {
                    // Nothing configured: no counters at all, since "0 of 0"
                    // only ever reads as broken. An invitation instead.
                    item {
                        AppEmptyState(
                            title = currentLanguage.pick(
                                "Empieza tu regla de vida",
                                "Begin your rule of life",
                                "Comece a sua regra de vida"
                            ),
                            body = currentLanguage.pick(
                                "Unas pocas prácticas, guardadas cada día, forman una vida en Cristo. Empieza con estas o escribe las tuyas.",
                                "A few practices, kept every day, shape a life in Christ. Start with these, or write your own.",
                                "Umas poucas práticas, guardadas cada dia, formam uma vida em Cristo. Comece com estas ou escreva as suas."
                            ),
                            icon = Icons.Default.SelfImprovement,
                            modifier = Modifier.testTag("habits_empty_state")
                        )
                    }

                    if (suggestedHabits.isNotEmpty()) {
                        item {
                            AppSectionHeader(
                                title = currentLanguage.pick(
                                    "Sugerencias",
                                    "Suggested practices",
                                    "Sugestões"
                                )
                            )
                        }
                        item {
                            AppListGroup {
                                suggestedHabits.forEach { habit ->
                                    AppListRow(
                                        title = habit.localizedTitle(currentLanguage),
                                        subtitle = habit.timeSuggestion,
                                        leadingIcon = habitIcon(habit.iconName),
                                        trailingText = categoryLabel(habit.category, currentLanguage),
                                        showsNavigationAffordance = false
                                    )
                                }
                            }
                        }
                        item {
                            Spacer(Modifier.height(design.spacing.sectionGap / 2))
                            AppPrimaryButton(
                                label = currentLanguage.pick(
                                    "Adoptar estas ${suggestedHabits.size} prácticas",
                                    "Adopt these ${suggestedHabits.size} practices",
                                    "Adotar estas ${suggestedHabits.size} práticas"
                                ),
                                onClick = {
                                    scope.launch { dao.insertDefaultHabits(suggestedHabits) }
                                },
                                leadingIcon = Icons.Default.CheckCircle,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = design.spacing.gutter)
                                    .testTag("adopt_suggested_habits")
                            )
                        }
                    }
                } else {
                    item {
                        FidelityCard(
                            completedCount = completedHabitIds.size,
                            totalCount = habitsList.size,
                            completionRate = completionRate,
                            allCompletions = allCompletions,
                            currentLanguage = currentLanguage,
                            onOpenSinAvoider = { openSinAvoider(repository.sinAvoiderCatalog.first()) }
                        )
                    }

                    item {
                        Spacer(Modifier.height(design.spacing.cardGap))
                        MetricRow(
                            bestStreak = habitsList.maxOfOrNull { it.bestStreak } ?: 0,
                            totalCompletions = allCompletions.size,
                            totalVictories = totalVictories,
                            currentLanguage = currentLanguage
                        )
                    }

                    item {
                        AppSectionHeader(
                            title = currentLanguage.pick(
                                "Prácticas diarias",
                                "Daily practices",
                                "Práticas diárias"
                            ),
                            subtitle = currentLanguage.pick(
                                "Toca una práctica al cumplirla hoy.",
                                "Tap a practice once you have kept it today.",
                                "Toque numa prática ao cumpri-la hoje."
                            )
                        )
                    }

                    item {
                        AppListGroup {
                            habitsList.forEach { habit ->
                                HabitRow(
                                    habit = habit,
                                    isCompletedToday = completedHabitIds.contains(habit.id),
                                    currentLanguage = currentLanguage,
                                    onToggleCompletion = {
                                        toggleHabit(habit, completedHabitIds.contains(habit.id))
                                    },
                                    onDeleteCustomHabit = if (habit.isCustom) {
                                        { deleteHabit(habit) }
                                    } else null
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(Modifier.height(design.spacing.sectionGap / 2))
                    AppSecondaryButton(
                        label = if (habitsList.isEmpty()) {
                            currentLanguage.pick(
                                "Escribir la mía",
                                "Write my own",
                                "Escrever a minha"
                            )
                        } else {
                            currentLanguage.pick(
                                "Añadir práctica",
                                "Add a practice",
                                "Adicionar prática"
                            )
                        },
                        onClick = { showAddHabitDialog = true },
                        leadingIcon = Icons.Default.Add,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = design.spacing.gutter)
                            .testTag("fab_add_habit")
                    )
                }

                item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("tab_sin_avoider")
            ) {
                paneHeader(
                    title = screenTitle,
                    subtitle = screenSubtitle,
                    tabLabels = tabLabels,
                    selectedTab = selectedTab,
                    onSelectTab = { selectedTab = it }
                )

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
                            "Las siete raíces",
                            "The seven roots",
                            "As sete raízes"
                        ),
                        subtitle = currentLanguage.pick(
                            "Siete raíces de pecado, siete virtudes que las vencen.",
                            "Seven roots of sin, seven virtues that overcome them.",
                            "Sete raízes do pecado, sete virtudes que as vencem."
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
                                modifier = Modifier.testTag("card_overcome_${option.sinKey}")
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
                        style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                        color = palette.accent
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = option.advice.get(currentLanguage),
                        style = styles.rowSubtitle,
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
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = currentLanguage.pick(
                                    "Escuchar oración",
                                    "Listen to the prayer",
                                    "Ouvir a oração"
                                ),
                                tint = palette.accent,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = hailMaryPrayer,
                        style = styles.rowSubtitle.copy(fontStyle = FontStyle.Italic),
                        color = palette.textPrimary
                    )

                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppDivider()
                    Spacer(Modifier.height(design.spacing.cardGap))

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
                            victoryNote = ""
                            showSinAvoiderModal = false
                            ttsManager.stop()
                        }
                    },
                    modifier = Modifier.testTag("confirm_victory_button")
                )
            },
            dismissButton = {
                AppSecondaryButton(
                    label = currentLanguage.pick("Cerrar", "Close", "Fechar"),
                    onClick = {
                        showSinAvoiderModal = false
                        ttsManager.stop()
                    }
                )
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
                                onClick = { newHabitCategory = cat }
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
                    modifier = Modifier.testTag("save_custom_habit_button")
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
 * The opening of either pane: the pane's own title, then the selector that
 * swaps between the two.
 */
private fun LazyListScope.paneHeader(
    title: String,
    subtitle: String,
    tabLabels: List<String>,
    selectedTab: Int,
    onSelectTab: (Int) -> Unit
) {
    item {
        AppScreenTitle(title = title, subtitle = subtitle)
    }
    item {
        val design = LocalAppDesign.current
        AppSegmentedTabs(
            options = tabLabels,
            selectedIndex = selectedTab,
            onSelect = onSelectTab,
            modifier = Modifier.padding(horizontal = segmentedTabsInset())
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
    return if (design.language == DesignLanguage.VATICAN) 0.dp else design.spacing.gutter
}

/** Today's fidelity: the ring, the week behind it, and the way out of a fall. */
@Composable
private fun FidelityCard(
    completedCount: Int,
    totalCount: Int,
    completionRate: Float,
    allCompletions: List<HabitCompletionLogEntity>,
    currentLanguage: AppLanguage,
    onOpenSinAvoider: () -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    AppCard(modifier = modifier.padding(horizontal = design.spacing.gutter)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = caps(currentLanguage.pick("Fidelidad espiritual", "Spiritual fidelity", "Fidelidade espiritual")),
                    style = styles.sectionEyebrow,
                    color = palette.textSecondary
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = currentLanguage.pick(
                        "$completedCount de $totalCount cumplidos hoy",
                        "$completedCount of $totalCount kept today",
                        "$completedCount de $totalCount cumpridos hoje"
                    ),
                    style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                    color = palette.textPrimary
                )
                Spacer(Modifier.height(4.dp))
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
                    color = palette.textSecondary
                )
            }
            Spacer(Modifier.width(design.spacing.cardGap))
            FidelityRing(completionRate = completionRate)
        }

        Spacer(Modifier.height(design.spacing.cardPadding))
        AppDivider()
        Spacer(Modifier.height(design.spacing.cardPadding))

        Text(
            text = caps(currentLanguage.pick("Últimos 7 días", "Last 7 days", "Últimos 7 dias")),
            style = styles.sectionEyebrow,
            color = palette.textSecondary
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        WeeklyFidelityStrip(allCompletions = allCompletions, currentLanguage = currentLanguage)

        Spacer(Modifier.height(design.spacing.cardPadding))
        AppSecondaryButton(
            label = currentLanguage.pick(
                "¿Tentado? Reza un Ave María",
                "Tempted? Pray a Hail Mary",
                "Tentado? Reze uma Ave Maria"
            ),
            onClick = onOpenSinAvoider,
            leadingIcon = Icons.Default.Shield,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("grace_sos_quick_button")
        )
    }
}

/**
 * The day's completion as a ring. No kit component draws one, so it is built
 * from the palette and closed with a square cap where the identity has no
 * rounded forms at all.
 */
@Composable
private fun FidelityRing(completionRate: Float, modifier: Modifier = Modifier) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val fraction = completionRate.coerceIn(0f, 1f)
    val cap = if (design.language == DesignLanguage.VATICAN) StrokeCap.Butt else StrokeCap.Round

    Box(modifier.size(RingSize), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = RingStroke.toPx()
            val box = Size(size.width - stroke, size.height - stroke)
            val origin = Offset(stroke / 2f, stroke / 2f)
            drawArc(
                color = palette.hairline,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = origin,
                size = box,
                style = Stroke(width = stroke, cap = StrokeCap.Butt)
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
            text = "${(fraction * 100).toInt()}%",
            style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
            color = palette.textPrimary,
            maxLines = 1
        )
    }
}

/**
 * Seven markers for the seven days behind today. Each one takes the identity's
 * badge shape, so the week reads as a row of discs under Cupertino and Material
 * and as a row of squares on the Vatican leaf.
 */
@Composable
private fun WeeklyFidelityStrip(
    allCompletions: List<HabitCompletionLogEntity>,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    val dayLabels = when (currentLanguage) {
        AppLanguage.ES -> listOf("L", "M", "X", "J", "V", "S", "D")
        AppLanguage.EN -> listOf("M", "T", "W", "T", "F", "S", "S")
        AppLanguage.PT -> listOf("S", "T", "Q", "Q", "S", "S", "D")
    }

    // Each date carries the index of its own weekday, so the letter over a
    // marker is the day that date actually fell on rather than its position.
    val week = remember {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val base = Calendar.getInstance()
        (6 downTo 0).map { offset ->
            val day = (base.clone() as Calendar).apply {
                add(Calendar.DAY_OF_YEAR, -offset)
            }
            format.format(day.time) to (day.get(Calendar.DAY_OF_WEEK) + 5) % 7
        }
    }

    val completionDatesSet = remember(allCompletions) {
        allCompletions.map { it.completionDate }.toSet()
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        week.forEachIndexed { index, (dateStr, weekdayIndex) ->
            val kept = completionDatesSet.contains(dateStr)
            val isToday = index == week.lastIndex
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = dayLabels[weekdayIndex],
                    style = styles.sectionEyebrow,
                    color = if (isToday) palette.accent else palette.textTertiary
                )
                Spacer(Modifier.height(6.dp))
                Box(
                    Modifier
                        .size(MarkerSize)
                        .clip(design.shapes.badge)
                        .background(
                            if (kept) palette.accent else palette.hairline.copy(alpha = 0.4f)
                        )
                        .then(
                            if (isToday) {
                                Modifier.border(1.5.dp, palette.accent, design.shapes.badge)
                            } else {
                                Modifier
                            }
                        )
                )
            }
        }
    }
}

/** The three standing totals, held to one height so their figures line up. */
@Composable
private fun MetricRow(
    bestStreak: Int,
    totalCompletions: Int,
    totalVictories: Int,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = design.spacing.gutter),
        horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
    ) {
        AppMetricTile(
            value = bestStreak.toString(),
            caption = currentLanguage.pick("Mejor racha", "Best streak", "Melhor sequência"),
            icon = Icons.Default.LocalFireDepartment,
            modifier = Modifier.weight(1f).fillMaxHeight()
        )
        AppMetricTile(
            value = totalCompletions.toString(),
            caption = currentLanguage.pick("Cumplimientos", "Completions", "Cumprimentos"),
            icon = Icons.Default.CheckCircle,
            modifier = Modifier.weight(1f).fillMaxHeight()
        )
        AppMetricTile(
            value = totalVictories.toString(),
            caption = currentLanguage.pick("Victorias", "Victories", "Vitórias"),
            icon = Icons.Default.Shield,
            modifier = Modifier.weight(1f).fillMaxHeight()
        )
    }
}

/**
 * One practice of the rule. Tapping the row is what keeps or un-keeps it today,
 * and a kept habit is the row's [AppListRow] selected state — rubricated on the
 * Vatican leaf, tinted under the other two.
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

    val streakLine = if (habit.currentStreak > 0) {
        "\n" + currentLanguage.pick(
            "${habit.currentStreak} días de fidelidad (Récord: ${habit.bestStreak})",
            "${habit.currentStreak} days of fidelity (best: ${habit.bestStreak})",
            "${habit.currentStreak} dias de fidelidade (recorde: ${habit.bestStreak})"
        )
    } else ""

    // Two tags on one row: the habit's card is now the habit's toggle, and a
    // click anywhere in it does what the toggle used to.
    Box(Modifier.testTag("habit_card_${habit.id}")) {
        AppListRow(
            title = title,
            subtitle = "${habit.timeSuggestion} · ${habit.description}" + streakLine,
            leadingIcon = habitIcon(habit.iconName),
            showsNavigationAffordance = false,
            selected = isCompletedToday,
            onClick = onToggleCompletion,
            modifier = Modifier.testTag("toggle_habit_${habit.id}")
        )
    }

    if (onDeleteCustomHabit != null) {
        AppListRow(
            title = currentLanguage.pick(
                "Eliminar hábito personalizado",
                "Delete custom habit",
                "Excluir hábito personalizado"
            ),
            leadingIcon = Icons.Default.Delete,
            showsNavigationAffordance = false,
            onClick = onDeleteCustomHabit
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

    AppCard(modifier = modifier.padding(horizontal = design.spacing.gutter)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Shield,
                contentDescription = null,
                tint = palette.gold,
                modifier = Modifier.size(22.dp)
            )
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
                .testTag("emergency_overcome_button")
        )
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

private fun habitIcon(iconName: String): ImageVector = when (iconName) {
    "wb_sunny" -> Icons.Default.WbSunny
    "schedule" -> Icons.Default.Schedule
    "self_improvement" -> Icons.Default.SelfImprovement
    "church" -> Icons.Default.Church
    "menu_book" -> Icons.Default.MenuBook
    "restaurant" -> Icons.Default.Restaurant
    "nights_stay" -> Icons.Default.NightsStay
    else -> Icons.Default.Star
}

private fun categoryLabel(category: String, language: AppLanguage): String = when (category) {
    "PRAYER" -> language.pick("Oración", "Prayer", "Oração")
    "FASTING" -> language.pick("Ayuno", "Fasting", "Jejum")
    "SACRAMENT" -> language.pick("Sacramento", "Sacrament", "Sacramento")
    "CHARITY" -> language.pick("Caridad", "Charity", "Caridade")
    else -> language.pick("Virtud", "Virtue", "Virtude")
}

private val RingSize = 68.dp
private val RingStroke = 6.dp
private val MarkerSize = 24.dp
