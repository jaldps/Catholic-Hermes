package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.AppLanguage
import com.example.data.models.CatholicPilgrimageSanctuary
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.models.SpiritualHabitItem
import com.example.data.repository.CatholicDataRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PilgrimageAndHabitsScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    var selectedMainTab by remember { mutableIntStateOf(0) } // 0 = Calendario Litúrgico, 1 = Hábitos y Virtudes, 2 = Santuarios de Peregrinación
    val context = LocalContext.current

    // Local in-memory habit check status
    val habitCompletionMap = remember {
        mutableStateMapOf<String, Boolean>().apply {
            repository.spiritualHabitsList.forEach { habit ->
                put(habit.id, false)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("pilgrimage_and_habits_screen")
    ) {
        PrimaryTabRow(
            selectedTabIndex = selectedMainTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedMainTab == 0,
                onClick = { selectedMainTab = 0 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Calendario"
                            AppLanguage.EN -> "Calendar"
                            AppLanguage.PT -> "Calendário"
                        },
                        fontWeight = if (selectedMainTab == 0) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = selectedMainTab == 1,
                onClick = { selectedMainTab = 1 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Hábitos Diarios"
                            AppLanguage.EN -> "Daily Habits"
                            AppLanguage.PT -> "Hábitos Diários"
                        },
                        fontWeight = if (selectedMainTab == 1) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
            Tab(
                selected = selectedMainTab == 2,
                onClick = { selectedMainTab = 2 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Santuarios"
                            AppLanguage.EN -> "Shrines"
                            AppLanguage.PT -> "Santuários"
                        },
                        fontWeight = if (selectedMainTab == 2) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
        }

        when (selectedMainTab) {
            0 -> {
                // Liturgical Calendar Explorer
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Calendario Litúrgico Católico"
                                AppLanguage.EN -> "Catholic Liturgical Calendar"
                                AppLanguage.PT -> "Calendário Litúrgico Católico"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Ciclo del Año Litúrgico, colores sagrados, solemnidades y normas de precepto."
                                AppLanguage.EN -> "Cycle of the Liturgical Year, sacred colors, solemnities, and fasting rules."
                                AppLanguage.PT -> "Ciclo do Ano Litúrgico, cores sagradas, solenidades e preceitos."
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    items(repository.liturgicalCalendarDays) { day ->
                        LiturgicalDayCard(day = day, currentLanguage = currentLanguage)
                    }
                }
            }

            1 -> {
                // Catholic Spiritual Habits & Virtues Checklist
                val completedCount = habitCompletionMap.values.count { it }
                val totalHabits = repository.spiritualHabitsList.size

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        ElevatedCard(
                            shape = MaterialTheme.shapes.large,
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = when (currentLanguage) {
                                                AppLanguage.ES -> "Regla de Vida y Hábitos Santos"
                                                AppLanguage.EN -> "Rule of Life & Holy Habits"
                                                AppLanguage.PT -> "Regra de Vida e Santos Hábitos"
                                            },
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Text(
                                            text = when (currentLanguage) {
                                                AppLanguage.ES -> "La santificación diaria paso a paso"
                                                AppLanguage.EN -> "Daily sanctification step by step"
                                                AppLanguage.PT -> "A santificação diária passo a passo"
                                            },
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Surface(
                                        shape = MaterialTheme.shapes.small,
                                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                                    ) {
                                        Text(
                                            text = "$completedCount / $totalHabits",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    items(repository.spiritualHabitsList) { habit ->
                        val isDone = habitCompletionMap[habit.id] ?: false
                        HabitItemCard(
                            habit = habit,
                            isCompleted = isDone,
                            currentLanguage = currentLanguage,
                            onToggle = {
                                habitCompletionMap[habit.id] = !isDone
                            }
                        )
                    }
                }
            }

            2 -> {
                // Global Catholic Pilgrimage Map & Shrines
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Guía Universal de Santuarios y Peregrinaciones"
                                AppLanguage.EN -> "Universal Guide to Shrines & Pilgrimages"
                                AppLanguage.PT -> "Guia Universal de Santuários e Peregrinações"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Lugares sagrados con gracias de indulgencia plenaria e historia milagrosa."
                                AppLanguage.EN -> "Sacred places with plenary indulgence graces and miraculous histories."
                                AppLanguage.PT -> "Lugares sagrados com graças de indulgência plenária e história milagrosa."
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    items(repository.globalPilgrimages) { shrine ->
                        ShrineCard(
                            shrine = shrine,
                            currentLanguage = currentLanguage,
                            onNavigateMap = {
                                try {
                                    val (lat, lon) = shrine.coordinateLatLong
                                    val geoUri = Uri.parse("geo:$lat,$lon?q=${Uri.encode(shrine.name.get(currentLanguage) + ", " + shrine.cityCountry.get(currentLanguage))}")
                                    val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
                                    context.startActivity(mapIntent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, shrine.cityCountry.get(currentLanguage), Toast.LENGTH_LONG).show()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LiturgicalDayCard(day: LiturgicalCalendarDay, currentLanguage: AppLanguage) {
    val liturgicalColor = Color(day.liturgicalColorHex)

    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(liturgicalColor)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = day.seasonName.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = liturgicalColor
                    )
                }
                Text(
                    text = "${day.dayOfMonth} ${day.monthName}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = day.celebrationTitle.get(currentLanguage),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Rango: " + day.rank.get(currentLanguage),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "📖 " + day.gospelVerseRef,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                if (day.fastingRule != null) {
                    Text(
                        text = "✦ " + day.fastingRule.get(currentLanguage),
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun HabitItemCard(
    habit: SpiritualHabitItem,
    isCompleted: Boolean,
    currentLanguage: AppLanguage,
    onToggle: () -> Unit
) {
    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (isCompleted) MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle)
            .testTag("habit_card_${habit.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onToggle) {
                Icon(
                    imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                    contentDescription = null,
                    tint = if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = habit.title.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = habit.category.get(currentLanguage),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = habit.description.get(currentLanguage),
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Sugerencia: " + habit.timeSuggestion,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun ShrineCard(
    shrine: CatholicPilgrimageSanctuary,
    currentLanguage: AppLanguage,
    onNavigateMap: () -> Unit
) {
    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("shrine_card_${shrine.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = shrine.name.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = shrine.cityCountry.get(currentLanguage),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = shrine.historicalOrigin.get(currentLanguage),
                fontSize = 12.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Reliquias: " + shrine.relicsAndPatron.get(currentLanguage),
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "✨ Indulgencia Plenaria y Gracia:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = shrine.plenaryIndulgenceNote.get(currentLanguage),
                        fontSize = 11.sp,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            FilledTonalButton(
                onClick = onNavigateMap,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Icon(
                    imageVector = Icons.Default.Directions,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "Ver ubicación en Mapa"
                        AppLanguage.EN -> "View location on Map"
                        AppLanguage.PT -> "Ver localização no Mapa"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}
