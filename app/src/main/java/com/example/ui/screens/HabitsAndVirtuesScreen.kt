package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.HabitCompletionLogEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.local.SinOvercomeLogEntity
import com.example.data.local.SpiritualHabitEntity
import com.example.data.models.AppLanguage
import com.example.data.models.SinAvoiderOption
import com.example.data.repository.CatholicDataRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HabitsAndVirtuesScreen(
    repository: CatholicDataRepository,
    dao: SanctaFidesDao,
    ttsManager: LiturgicalTtsManager,
    modifier: Modifier = Modifier
) {
    val currentLanguage by repository.currentLanguage.collectAsState()
    val scope = rememberCoroutineScope()

    val todayDateStr = remember {
        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }

    val habitsList by dao.getAllHabits().collectAsState(initial = emptyList())
    val todayCompletions by dao.getCompletionsForDate(todayDateStr).collectAsState(initial = emptyList())
    val allCompletions by dao.getAllCompletions().collectAsState(initial = emptyList())
    val sinOvercomeLogs by dao.getAllSinOvercomeLogs().collectAsState(initial = emptyList())
    val totalVictories by dao.getTotalVictoriesCount().collectAsState(initial = 0)

    LaunchedEffect(habitsList) {
        if (habitsList.isEmpty()) {
            dao.insertDefaultHabits(repository.defaultHabitsToSeed)
        }
    }

    var selectedTab by remember { mutableIntStateOf(0) }

    var showSinAvoiderModal by remember { mutableStateOf(false) }
    var selectedSinOption by remember { mutableStateOf<SinAvoiderOption?>(null) }
    var victoryNote by remember { mutableStateOf("") }

    var showAddHabitDialog by remember { mutableStateOf(false) }
    var newHabitTitle by remember { mutableStateOf("") }
    var newHabitDescription by remember { mutableStateOf("") }
    var newHabitCategory by remember { mutableStateOf("PRAYER") }
    var newHabitTime by remember { mutableStateOf("Todo el día") }

    val completedHabitIds = remember(todayCompletions) {
        todayCompletions.map { it.habitId }.toSet()
    }

    val completionRate = if (habitsList.isNotEmpty()) {
        completedHabitIds.size.toFloat() / habitsList.size.toFloat()
    } else 0f

    Scaffold(
        floatingActionButton = {
            if (selectedTab == 0) {
                FloatingActionButton(
                    onClick = { showAddHabitDialog = true },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.testTag("fab_add_habit")
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir Hábito")
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .testTag("habits_and_virtues_screen")
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Regla de Vida y Hábitos"
                                AppLanguage.EN -> "Rule of Life & Habits"
                                AppLanguage.PT -> "Regra de Vida e Hábitos"
                            },
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    modifier = Modifier.testTag("tab_habits_rule")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Antídoto de Gracia (S.O.S)"
                                    AppLanguage.EN -> "Grace Antidote (S.O.S)"
                                    AppLanguage.PT -> "Antídoto de Graça (S.O.S)"
                                },
                                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    },
                    modifier = Modifier.testTag("tab_sin_avoider")
                )
            }

            if (selectedTab == 0) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        HabitProgressCard(
                            completedCount = completedHabitIds.size,
                            totalCount = habitsList.size,
                            completionRate = completionRate,
                            totalVictories = totalVictories,
                            allCompletions = allCompletions,
                            currentLanguage = currentLanguage,
                            onOpenSinAvoider = {
                                selectedSinOption = repository.sinAvoiderCatalog.first()
                                showSinAvoiderModal = true
                            }
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "PRÁCTICAS Y DEVOCIONES DIARIAS"
                                    AppLanguage.EN -> "DAILY PRACTICES & DEVOTIONS"
                                    AppLanguage.PT -> "PRÁTICAS E DEVOÇÕES DIÁRIAS"
                                },
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                letterSpacing = 1.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "${completedHabitIds.size}/${habitsList.size}",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }

                    items(habitsList, key = { it.id }) { habit ->
                        val isDone = completedHabitIds.contains(habit.id)
                        HabitItemCard(
                            habit = habit,
                            isCompletedToday = isDone,
                            currentLanguage = currentLanguage,
                            onToggleCompletion = {
                                scope.launch {
                                    if (isDone) {
                                        dao.deleteCompletionForDate(habit.id, todayDateStr)
                                        val newCurrent = maxOf(0, habit.currentStreak - 1)
                                        dao.updateHabit(
                                            habit.copy(
                                                currentStreak = newCurrent,
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
                                        val newBest = maxOf(habit.bestStreak, newCurrent)
                                        dao.updateHabit(
                                            habit.copy(
                                                currentStreak = newCurrent,
                                                bestStreak = newBest,
                                                lastCompletedDate = todayDateStr,
                                                totalCompletions = habit.totalCompletions + 1
                                            )
                                        )
                                    }
                                }
                            },
                            onDeleteCustomHabit = if (habit.isCustom) {
                                {
                                    scope.launch {
                                        dao.deleteHabit(habit)
                                        dao.deleteCompletionForDate(habit.id, todayDateStr)
                                    }
                                }
                            } else null
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(72.dp))
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        GraceEmergencyBanner(
                            totalVictories = totalVictories,
                            currentLanguage = currentLanguage,
                            onTriggerEmergency = {
                                selectedSinOption = repository.sinAvoiderCatalog.first()
                                showSinAvoiderModal = true
                            }
                        )
                    }

                    item {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "LAS 7 RAÍCES DE PECADO Y SUS VIRTUDES"
                                AppLanguage.EN -> "THE 7 CAPITAL SINS & REMEDY VIRTUES"
                                AppLanguage.PT -> "AS 7 RAÍZES DO PECADO E SUAS VIRTUDES"
                            },
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    items(repository.sinAvoiderCatalog, key = { it.sinKey }) { option ->
                        val count = sinOvercomeLogs.count { it.sinKey == option.sinKey }
                        SinAvoiderCatalogCard(
                            option = option,
                            victoriesCount = count,
                            currentLanguage = currentLanguage,
                            onOvercomeClicked = {
                                selectedSinOption = option
                                showSinAvoiderModal = true
                            }
                        )
                    }

                    if (sinOvercomeLogs.isNotEmpty()) {
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "HISTORIAL DE VICTORIAS EN CRISTO"
                                    AppLanguage.EN -> "VICTORIES RECORD IN CHRIST"
                                    AppLanguage.PT -> "HISTÓRICO DE VITÓRIAS EM CRISTO"
                                },
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                letterSpacing = 1.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        items(sinOvercomeLogs.take(10), key = { it.id }) { log ->
                            SinVictoryLogItem(
                                log = log,
                                currentLanguage = currentLanguage
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(72.dp))
                    }
                }
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

        AlertDialog(
            onDismissRequest = {
                showSinAvoiderModal = false
                ttsManager.stop()
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "¡Victoria sobre la Tentación!"
                            AppLanguage.EN -> "Victory Over Temptation!"
                            AppLanguage.PT -> "Vitória sobre a Tentação!"
                        },
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "${option.title.get(currentLanguage)} ➔ ${option.counterVirtue.get(currentLanguage)}",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )

                    Text(
                        text = option.advice.get(currentLanguage),
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = when (currentLanguage) {
                                        AppLanguage.ES -> "REZA UN AVE MARÍA AHORA"
                                        AppLanguage.EN -> "PRAY ONE HAIL MARY NOW"
                                        AppLanguage.PT -> "REZE UMA AVE MARIA AGORA"
                                    },
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    letterSpacing = 1.sp
                                )
                                IconButton(
                                    onClick = {
                                        ttsManager.setLanguage(currentLanguage)
                                        ttsManager.speakSingle(hailMaryPrayer, isFemaleVoice = true)
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.VolumeUp,
                                        contentDescription = "Escuchar oración",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = hailMaryPrayer,
                                fontSize = 12.sp,
                                lineHeight = 17.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    OutlinedTextField(
                        value = victoryNote,
                        onValueChange = { victoryNote = it },
                        label = {
                            Text(
                                when (currentLanguage) {
                                    AppLanguage.ES -> "Nota u ofrenda (opcional)"
                                    AppLanguage.EN -> "Note or offering (optional)"
                                    AppLanguage.PT -> "Nota ou oferta (opcional)"
                                },
                                fontSize = 11.sp
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2
                    )
                }
            },
            confirmButton = {
                Button(
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
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.testTag("confirm_victory_button")
                ) {
                    Text(
                        when (currentLanguage) {
                            AppLanguage.ES -> "Registrar Victoria"
                            AppLanguage.EN -> "Record Victory"
                            AppLanguage.PT -> "Registar Vitória"
                        },
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showSinAvoiderModal = false
                    ttsManager.stop()
                }) {
                    Text(
                        when (currentLanguage) {
                            AppLanguage.ES -> "Cerrar"
                            AppLanguage.EN -> "Close"
                            AppLanguage.PT -> "Fechar"
                        }
                    )
                }
            }
        )
    }

    if (showAddHabitDialog) {
        AlertDialog(
            onDismissRequest = { showAddHabitDialog = false },
            title = {
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "Nuevo Hábito Espiritual"
                        AppLanguage.EN -> "New Spiritual Habit"
                        AppLanguage.PT -> "Novo Hábito Espiritual"
                    },
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = newHabitTitle,
                        onValueChange = { newHabitTitle = it },
                        label = {
                            Text(
                                when (currentLanguage) {
                                    AppLanguage.ES -> "Nombre del Hábito"
                                    AppLanguage.EN -> "Habit Name"
                                    AppLanguage.PT -> "Nome do Hábito"
                                }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_habit_title"),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = newHabitDescription,
                        onValueChange = { newHabitDescription = it },
                        label = {
                            Text(
                                when (currentLanguage) {
                                    AppLanguage.ES -> "Propósito o Descripción"
                                    AppLanguage.EN -> "Purpose or Description"
                                    AppLanguage.PT -> "Propósito ou Descrição"
                                }
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2
                    )

                    OutlinedTextField(
                        value = newHabitTime,
                        onValueChange = { newHabitTime = it },
                        label = {
                            Text(
                                when (currentLanguage) {
                                    AppLanguage.ES -> "Horario Sugerido"
                                    AppLanguage.EN -> "Suggested Time"
                                    AppLanguage.PT -> "Horário Sugerido"
                                }
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Categoría:"
                            AppLanguage.EN -> "Category:"
                            AppLanguage.PT -> "Categoria:"
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf("PRAYER", "FASTING", "SACRAMENT", "CHARITY", "VIRTUE").forEach { cat ->
                            val isSelected = newHabitCategory == cat
                            val label = when (cat) {
                                "PRAYER" -> "Oración"
                                "FASTING" -> "Ayuno"
                                "SACRAMENT" -> "Sacramento"
                                "CHARITY" -> "Caridad"
                                else -> "Virtud"
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier
                                    .clickable { newHabitCategory = cat }
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newHabitTitle.isNotBlank()) {
                            scope.launch {
                                dao.insertHabit(
                                    SpiritualHabitEntity(
                                        id = "custom_" + UUID.randomUUID().toString().take(8),
                                        titleEs = newHabitTitle,
                                        titleEn = newHabitTitle,
                                        titlePt = newHabitTitle,
                                        description = newHabitDescription.ifBlank { "Hábito espiritual personalizado" },
                                        category = newHabitCategory,
                                        timeSuggestion = newHabitTime.ifBlank { "Todo el día" },
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
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.testTag("save_custom_habit_button")
                ) {
                    Text(
                        when (currentLanguage) {
                            AppLanguage.ES -> "Guardar"
                            AppLanguage.EN -> "Save"
                            AppLanguage.PT -> "Salvar"
                        }
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddHabitDialog = false }) {
                    Text(
                        when (currentLanguage) {
                            AppLanguage.ES -> "Cancelar"
                            AppLanguage.EN -> "Cancel"
                            AppLanguage.PT -> "Cancelar"
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun HabitProgressCard(
    completedCount: Int,
    totalCount: Int,
    completionRate: Float,
    totalVictories: Int,
    allCompletions: List<HabitCompletionLogEntity>,
    currentLanguage: AppLanguage,
    onOpenSinAvoider: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.dp,
                Brush.linearGradient(listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f))),
                RoundedCornerShape(20.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "FIDELIDAD ESPIRITUAL"
                            AppLanguage.EN -> "SPIRITUAL FIDELITY"
                            AppLanguage.PT -> "FIDELIDADE ESPIRITUAL"
                        },
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 1.2.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$completedCount de $totalCount cumplidos hoy",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = when {
                            completionRate >= 1.0f -> "¡Día consagrado en plenitud!"
                            completionRate >= 0.5f -> "Buen camino, persevera hasta la noche."
                            else -> "Empieza con un ofrecimiento de obras."
                        },
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }

                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { completionRate },
                        modifier = Modifier.size(62.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeWidth = 6.dp
                    )
                    Text(
                        text = "${(completionRate * 100).toInt()}%",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            WeeklyHeatmapStrip(allCompletions = allCompletions, currentLanguage = currentLanguage)

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(onClick = onOpenSinAvoider)
                    .testTag("grace_sos_quick_button"),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "S.O.S. Tentación: Rezar Ave María"
                                AppLanguage.EN -> "S.O.S. Temptation: Pray Hail Mary"
                                AppLanguage.PT -> "S.O.S. Tentação: Rezar Ave Maria"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Text(
                        text = "$totalVictories victorias",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Composable
fun WeeklyHeatmapStrip(
    allCompletions: List<HabitCompletionLogEntity>,
    currentLanguage: AppLanguage
) {
    val daysLabels = when (currentLanguage) {
        AppLanguage.ES -> listOf("L", "M", "X", "J", "V", "S", "D")
        AppLanguage.EN -> listOf("M", "T", "W", "T", "F", "S", "S")
        AppLanguage.PT -> listOf("S", "T", "Q", "Q", "S", "S", "D")
    }

    val past7Dates = remember {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val cal = Calendar.getInstance()
        (6 downTo 0).map { offset ->
            val c = cal.clone() as Calendar
            c.add(Calendar.DAY_OF_YEAR, -offset)
            format.format(c.time)
        }
    }

    val completionDatesSet = remember(allCompletions) {
        allCompletions.map { it.completionDate }.toSet()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        past7Dates.forEachIndexed { index, dateStr ->
            val hasActivity = completionDatesSet.contains(dateStr)
            val isToday = index == 6

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = daysLabels[index % daysLabels.size],
                    fontSize = 10.sp,
                    fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                    color = if (isToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                hasActivity -> MaterialTheme.colorScheme.secondary
                                isToday -> MaterialTheme.colorScheme.surfaceVariant
                                else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            }
                        )
                        .border(
                            width = if (isToday) 1.5.dp else 0.dp,
                            color = if (isToday) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (hasActivity) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HabitItemCard(
    habit: SpiritualHabitEntity,
    isCompletedToday: Boolean,
    currentLanguage: AppLanguage,
    onToggleCompletion: () -> Unit,
    onDeleteCustomHabit: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    val title = when (currentLanguage) {
        AppLanguage.ES -> habit.titleEs
        AppLanguage.EN -> habit.titleEn
        AppLanguage.PT -> habit.titlePt
    }

    val icon = when (habit.iconName) {
        "wb_sunny" -> Icons.Default.WbSunny
        "schedule" -> Icons.Default.Schedule
        "self_improvement" -> Icons.Default.SelfImprovement
        "church" -> Icons.Default.Church
        "menu_book" -> Icons.Default.MenuBook
        "restaurant" -> Icons.Default.Restaurant
        "nights_stay" -> Icons.Default.NightsStay
        else -> Icons.Default.Star
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("habit_card_${habit.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompletedToday) {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = if (isCompletedToday) {
            androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f))
        } else {
            androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onToggleCompletion,
                modifier = Modifier
                    .size(36.dp)
                    .testTag("toggle_habit_${habit.id}")
            ) {
                Icon(
                    imageVector = if (isCompletedToday) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                    contentDescription = "Completar hábito",
                    tint = if (isCompletedToday) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "${habit.timeSuggestion} • ${habit.description}",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (habit.currentStreak > 0) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = "Racha",
                            tint = Color(0xFFE65100),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "${habit.currentStreak} días de fidelidad (Récord: ${habit.bestStreak})",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE65100)
                        )
                    }
                }
            }

            if (onDeleteCustomHabit != null) {
                IconButton(
                    onClick = onDeleteCustomHabit,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar hábito personalizado",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.6f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun GraceEmergencyBanner(
    totalVictories: Int,
    currentLanguage: AppLanguage,
    onTriggerEmergency: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.5.dp,
                Brush.linearGradient(listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)),
                RoundedCornerShape(20.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "ANTÍDOTO DE GRACIA"
                                AppLanguage.EN -> "GRACE ANTIDOTE"
                                AppLanguage.PT -> "ANTÍDOTO DE GRAÇA"
                            },
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "$totalVictories tentaciones vencidas en Cristo",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = when (currentLanguage) {
                    AppLanguage.ES -> "«Si sientes la tentación de caer (ira, lujuria, gula, pereza), detente de inmediato, reza un Ave María y vence con la gracia de Dios»."
                    AppLanguage.EN -> "“If you feel temptation striking (lust, anger, gluttony, sloth), stop immediately, pray one Hail Mary, and triumph with God's grace.”"
                    AppLanguage.PT -> "“Se sentir a tentação bater (ira, luxúria, gula, preguiça), pare de imediato, reze uma Ave Maria e vença com a graça de Deus.”"
                },
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onTriggerEmergency,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("emergency_overcome_button"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "¡Sentí tentación y la vencí! (Ave María)"
                        AppLanguage.EN -> "Felt temptation & overcame it! (Hail Mary)"
                        AppLanguage.PT -> "Senti tentação e venci! (Ave Maria)"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun SinAvoiderCatalogCard(
    option: SinAvoiderOption,
    victoriesCount: Int,
    currentLanguage: AppLanguage,
    onOvercomeClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = option.title.get(currentLanguage),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "$victoriesCount victorias",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Virtud contraria: ${option.counterVirtue.get(currentLanguage)}",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = option.advice.get(currentLanguage),
                fontSize = 11.sp,
                lineHeight = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onOvercomeClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("card_overcome_${option.sinKey}"),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "Registrar Victoria + Ave María"
                        AppLanguage.EN -> "Record Victory + Hail Mary"
                        AppLanguage.PT -> "Registar Vitória + Ave Maria"
                    },
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SinVictoryLogItem(
    log: SinOvercomeLogEntity,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val dateStr = remember(log.timestamp) {
        SimpleDateFormat("d MMM, HH:mm", Locale.getDefault()).format(Date(log.timestamp))
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Victoria en ${log.counterVirtue}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                if (log.note.isNotBlank()) {
                    Text(
                        text = "«${log.note}»",
                        fontSize = 11.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            Text(
                text = dateStr,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}
