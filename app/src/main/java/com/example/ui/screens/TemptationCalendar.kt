package com.example.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.temptation.TemptationStore
import com.example.ui.design.lumenPress
import com.example.ui.design.softShadow
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.time.YearMonth

/**
 * Monthly calendar of temptations overcome and of falls: one grid, two
 * separate counters, switched by a glass segmented control ("Vencí | Caí",
 * shown only when [fellCounts] is passed).
 *
 * Days with entries carry a monochrome ink disc whose density follows the
 * count (1, 2, 3+). Falls use exactly the same quiet treatment as victories:
 * never red, never a badge. Tapping a day shows its line ("Martes 4 · 2
 * vencidas · 7 en el mes"): the daily counter (which resets every day) and
 * the cumulative for the month (which resets every month), from per-date keys.
 */
@Composable
fun TemptationCalendar(
    counts: Map<String, Int>,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    initialMonth: YearMonth = YearMonth.now(),
    fellCounts: Map<String, Int>? = null,
    framed: Boolean = true,
) {
    val lang = currentLanguage
    var showFalls by remember { mutableStateOf(false) }
    val active = if (showFalls && fellCounts != null) fellCounts else counts
    val falls = showFalls && fellCounts != null

    CalendarFrame(framed = framed, modifier = modifier.testTag("temptation_calendar")) {
        Column(Modifier.fillMaxWidth()) {
            if (fellCounts != null) {
                TwoSegments(
                    first = lang.pick3("Vencí", "I overcame", "Venci"),
                    second = lang.pick3("Caí", "I fell", "Caí"),
                    secondSelected = showFalls,
                    onSelect = { showFalls = it },
                    firstTag = "temptation_tab_victories",
                    secondTag = "temptation_tab_falls"
                )
                Spacer(Modifier.height(14.dp))
            }
            MonthCalendar(
                language = lang,
                initialMonth = initialMonth,
                tagPrefix = "temptation",
                footerTag = "temptation_month_total",
                footerText = { month ->
                    val total = TemptationStore.cumulativeForMonth(active, month).values.lastOrNull() ?: 0
                    if (falls) lang.pick3("$total caídas este mes", "$total falls this month", "$total quedas este mês")
                    else lang.pick3("$total vencidas este mes", "$total overcome this month", "$total vencidas este mês")
                },
                detailText = { date ->
                    val count = active[TemptationStore.dateKey(date)] ?: 0
                    val cumulative = TemptationStore.cumulativeForMonth(active, YearMonth.from(date))[date.dayOfMonth] ?: 0
                    val dayPart = if (falls) lang.pick3(
                        if (count == 1) "1 caída" else "$count caídas",
                        if (count == 1) "1 fall" else "$count falls",
                        if (count == 1) "1 queda" else "$count quedas"
                    ) else lang.pick3(
                        if (count == 1) "1 vencida" else "$count vencidas",
                        "$count overcome",
                        if (count == 1) "1 vencida" else "$count vencidas"
                    )
                    "${dayName(date, lang)} · $dayPart · " +
                        lang.pick3("$cumulative en el mes", "$cumulative this month", "$cumulative no mês")
                },
                dayDescription = { date ->
                    val count = active[TemptationStore.dateKey(date)] ?: 0
                    fullDate(date, lang) + ", " + if (falls) lang.pick3("$count caídas", "$count falls", "$count quedas")
                    else lang.pick3("$count vencidas", "$count overcome", "$count vencidas")
                }
            ) { date, isToday, isFuture, _ ->
                DiscDayCell(
                    day = date.dayOfMonth,
                    count = active[TemptationStore.dateKey(date)] ?: 0,
                    isToday = isToday,
                    isFuture = isFuture
                )
            }
        }
    }
}

/**
 * Two-option glass segmented control with one test tag per segment (the
 * kit's control takes none). Track = glass, thumb = white (dark: 16% white)
 * with a soft shadow, sliding on a spring.
 */
@Composable
internal fun TwoSegments(
    first: String,
    second: String,
    secondSelected: Boolean,
    onSelect: (Boolean) -> Unit,
    firstTag: String,
    secondTag: String,
    modifier: Modifier = Modifier,
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val track = RoundedCornerShape(percent = 50)
    BoxWithConstraints(
        modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(p.textPrimary.copy(alpha = if (design.isDark) 0.10f else 0.06f), track)
            .padding(3.dp)
    ) {
        val segment = maxWidth / 2
        val offset by animateDpAsState(
            if (secondSelected) segment else 0.dp,
            spring(dampingRatio = 0.85f, stiffness = 500f),
            label = "seg"
        )
        Box(
            Modifier
                .offset(x = offset)
                .width(segment)
                .fillMaxHeight()
                .softShadow(track, p.shadow, 10.dp, 2.dp)
                .background(if (design.isDark) Color.White.copy(alpha = 0.16f) else Color.White, track)
        )
        Row(Modifier.fillMaxWidth().fillMaxHeight()) {
            listOf(first to false, second to true).forEach { (label, isSecond) ->
                val isSel = isSecond == secondSelected
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .lumenPress({ onSelect(isSecond) }, 0.97f, Role.Tab)
                        .semantics { selected = isSel }
                        .testTag(if (isSecond) secondTag else firstTag),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        style = styles.subhead.copy(fontWeight = if (isSel) FontWeight.SemiBold else FontWeight.Medium),
                        color = p.textPrimary,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
