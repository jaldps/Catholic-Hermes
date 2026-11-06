package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.temptation.TemptationStore
import com.example.ui.design.AppCard
import com.example.ui.design.AppDivider
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

/** Minimum touch target for the month arrows (AAA). */
private val CalendarTouchTarget = 48.dp

/**
 * Monthly calendar of temptations overcome.
 *
 * Each day cell shows that day's count (the daily counter, which resets
 * every new day) over the cumulative total for the month up to that day
 * (which resets after the month). Days with victories are highlighted with
 * the identity's tinted surface; today carries a gold-ring badge. Plain
 * grid reading (no interaction on the days themselves) so the cell testTags
 * `temptation_day_<d>` stay stable for Compose tests.
 */
@Composable
fun TemptationCalendar(
    counts: Map<String, Int>,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    initialMonth: YearMonth = YearMonth.now(),
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    var month by remember(initialMonth) { mutableStateOf(initialMonth) }
    val today = LocalDate.now()
    val cumulative = remember(counts, month) { TemptationStore.cumulativeForMonth(counts, month) }
    val monthTotal = cumulative.values.lastOrNull() ?: 0
    val daysInMonth = month.lengthOfMonth()
    val firstDow = month.atDay(1).dayOfWeek.value // 1 = Monday .. 7 = Sunday

    fun pick(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    AppCard(
        modifier = modifier
            .padding(horizontal = design.spacing.gutter)
            .testTag("temptation_calendar")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { month = month.minusMonths(1) },
                    modifier = Modifier
                        .size(CalendarTouchTarget)
                        .testTag("temptation_prev_month")
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = pick("Mes anterior", "Previous month", "Mês anterior"),
                        tint = palette.textPrimary
                    )
                }
                Text(
                    text = month.format(DateTimeFormatter.ofPattern("MMMM yyyy", localeFor(currentLanguage))),
                    style = styles.rowTitle.copy(fontWeight = FontWeight.SemiBold),
                    color = palette.textPrimary,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                IconButton(
                    onClick = { month = month.plusMonths(1) },
                    modifier = Modifier
                        .size(CalendarTouchTarget)
                        .testTag("temptation_next_month")
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = pick("Mes siguiente", "Next month", "Mês seguinte"),
                        tint = palette.textPrimary
                    )
                }
            }

            Spacer(Modifier.height(design.spacing.cardGap / 2))

            // Weekday header (Monday-first, localized short names).
            val weekdayNames = remember(currentLanguage) {
                (0..6).map { offset ->
                    DayOfWeek.MONDAY.plus(offset.toLong()).getDisplayName(
                        java.time.format.TextStyle.SHORT, localeFor(currentLanguage)
                    ).let { raw ->
                        // Some locales return longer short names than a cell
                        // can carry; the first two letters read best in a grid.
                        raw.take(2).uppercase(localeFor(currentLanguage))
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                weekdayNames.forEach { name ->
                    Text(
                        text = name,
                        style = styles.rowSubtitle,
                        color = palette.textSecondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(Modifier.height(design.spacing.cardGap / 2))

            // Day grid: leading blanks so day 1 lands under its weekday.
            val cells: List<Int?> = (List(firstDow - 1) { null } + (1..daysInMonth).toList())
            cells.chunked(7).forEach { week ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    week.forEach { day ->
                        if (day == null) {
                            Spacer(Modifier.weight(1f))
                        } else {
                            val dayCount = counts[TemptationStore.dateKey(month.atDay(day))] ?: 0
                            val cum = cumulative[day] ?: 0
                            val isToday = day == today.dayOfMonth && month == YearMonth.now()
                            TemptationDayCell(
                                day = day,
                                dayCount = dayCount,
                                cumulative = cum,
                                isToday = isToday,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    // Pad the final week so every row holds seven cells.
                    repeat(7 - week.size) { Spacer(Modifier.weight(1f)) }
                }
                Spacer(Modifier.height(4.dp))
            }

            Spacer(Modifier.height(design.spacing.cardGap / 2))
            AppDivider()
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = pick(
                    "$monthTotal vencidas este mes",
                    "$monthTotal overcome this month",
                    "$monthTotal vencidas este mês"
                ),
                style = styles.rowSubtitle.copy(fontWeight = FontWeight.SemiBold),
                color = palette.textPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("temptation_month_total"),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun TemptationDayCell(
    day: Int,
    dayCount: Int,
    cumulative: Int,
    isToday: Boolean,
    modifier: Modifier = Modifier,
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    Box(
        modifier = modifier
            .clip(design.shapes.card)
            .then(
                if (dayCount > 0) Modifier.background(palette.accentMuted)
                else Modifier
            )
            .padding(vertical = 6.dp)
            .testTag("temptation_day_$day"),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = day.toString(),
                style = styles.rowSubtitle.copy(
                    fontWeight = if (isToday || dayCount > 0) FontWeight.Bold else FontWeight.Normal,
                    color = if (isToday) palette.gold else palette.textPrimary
                ),
                textAlign = TextAlign.Center
            )
            if (dayCount > 0 || cumulative > 0) {
                Text(
                    text = "$dayCount·$cumulative",
                    style = styles.rowSubtitle,
                    color = palette.textSecondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

private fun localeFor(language: AppLanguage): Locale = when (language) {
    AppLanguage.ES -> Locale("es", "ES")
    AppLanguage.EN -> Locale("en", "US")
    AppLanguage.PT -> Locale("pt", "BR")
}
