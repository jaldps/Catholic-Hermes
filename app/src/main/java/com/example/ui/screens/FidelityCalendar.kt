package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.fidelity.FidelityStore
import com.example.data.models.AppLanguage
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.Locale

/**
 * Monthly calendar of the rule of life, Apple Fitness style: every day is a
 * small activity ring that closes as that day's practices are kept.
 *
 * Counts are per-date (`yyyy-MM-dd -> practices kept`), so the daily counter
 * resets every new day and the monthly cumulative resets every new month.
 * Tapping a day shows its detail line ("Martes 4 · 7 de 12 · 41 en el mes").
 *
 * [dailyGoal] turns the cells into rings (kept / goal); without it the cells
 * are soft discs whose density follows the count (legacy callers). [framed]
 * = false drops the glass card and the gutter so a caller that already sits
 * inside a card (Home) can nest it. [legacyDayTagPrefix] adds an outer
 * `<prefix><day>` tag to each cell for callers with older tests.
 */
@Composable
fun FidelityCalendar(
    counts: Map<String, Int>,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    initialMonth: YearMonth = YearMonth.now(),
    dailyGoal: Int? = null,
    framed: Boolean = true,
    legacyDayTagPrefix: String? = null,
) {
    val lang = currentLanguage
    CalendarFrame(framed = framed, modifier = modifier.testTag("fidelity_calendar")) {
        MonthCalendar(
            language = lang,
            initialMonth = initialMonth,
            tagPrefix = "fidelity",
            legacyDayTagPrefix = legacyDayTagPrefix,
            footerTag = "fidelity_month_total",
            footerText = { month ->
                val cumulative = FidelityStore.cumulativeForMonth(counts, month)
                val total = cumulative.values.lastOrNull() ?: 0
                val activeDays = (1..month.lengthOfMonth()).count {
                    (counts[FidelityStore.dateKey(month.atDay(it))] ?: 0) > 0
                }
                if (dailyGoal != null) {
                    val possible = dailyGoal * elapsedDays(month)
                    lang.pick3(
                        "$total de $possible prácticas · ${daysLabel(activeDays, lang)}",
                        "$total of $possible practices · ${daysLabel(activeDays, lang)}",
                        "$total de $possible práticas · ${daysLabel(activeDays, lang)}"
                    )
                } else {
                    lang.pick3(
                        "$total prácticas este mes",
                        "$total practices this month",
                        "$total práticas este mês"
                    )
                }
            },
            detailText = { date ->
                val count = counts[FidelityStore.dateKey(date)] ?: 0
                val cumulative = FidelityStore.cumulativeForMonth(counts, YearMonth.from(date))[date.dayOfMonth] ?: 0
                val kept = if (dailyGoal != null) {
                    lang.pick3("$count de $dailyGoal", "$count of $dailyGoal", "$count de $dailyGoal")
                } else {
                    lang.pick3("$count prácticas", "$count practices", "$count práticas")
                }
                "${dayName(date, lang)} · $kept · " + lang.pick3(
                    "$cumulative en el mes", "$cumulative this month", "$cumulative no mês"
                )
            },
            dayDescription = { date ->
                val count = counts[FidelityStore.dateKey(date)] ?: 0
                fullDate(date, lang) + ", " + if (dailyGoal != null) {
                    lang.pick3("$count de $dailyGoal prácticas", "$count of $dailyGoal practices", "$count de $dailyGoal práticas")
                } else {
                    lang.pick3("$count prácticas", "$count practices", "$count práticas")
                }
            }
        ) { date, isToday, isFuture, _ ->
            val count = counts[FidelityStore.dateKey(date)] ?: 0
            if (dailyGoal != null && dailyGoal > 0) {
                RingDayCell(
                    day = date.dayOfMonth,
                    progress = if (isFuture) 0f else count.coerceAtMost(dailyGoal) / dailyGoal.toFloat(),
                    isToday = isToday,
                    isFuture = isFuture
                )
            } else {
                DiscDayCell(day = date.dayOfMonth, count = count, isToday = isToday, isFuture = isFuture)
            }
        }
    }
}

// ------------------------------------------------------------ shared month

/** Glass card (radius 24, padding 20) around a calendar, or bare content. */
@Composable
internal fun CalendarFrame(framed: Boolean, modifier: Modifier, content: @Composable () -> Unit) {
    val design = LocalAppDesign.current
    if (framed) {
        Box(
            modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .lumenGlass(design.shapes.card)
                .padding(horizontal = 14.dp, vertical = 18.dp)
        ) { content() }
    } else {
        Box(modifier.fillMaxWidth()) { content() }
    }
}

/**
 * The month grid both trackers share: month title with glass arrows (next is
 * disabled on the current month, so there are no empty future months),
 * horizontal swipe between months, locale-first weekday initials, a 7-column
 * grid, a selected-day detail line and a footer.
 */
@Composable
internal fun MonthCalendar(
    language: AppLanguage,
    initialMonth: YearMonth,
    tagPrefix: String,
    footerTag: String,
    footerText: (YearMonth) -> String,
    detailText: (LocalDate) -> String,
    dayDescription: (LocalDate) -> String,
    legacyDayTagPrefix: String? = null,
    cell: @Composable BoxScope.(date: LocalDate, isToday: Boolean, isFuture: Boolean, selected: Boolean) -> Unit,
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val locale = calendarLocale(language)
    val today = LocalDate.now()
    val currentMonth = YearMonth.from(today)

    var month by remember(initialMonth) { mutableStateOf(initialMonth) }
    var selected by remember(month) {
        mutableStateOf(if (month == currentMonth) today else null)
    }
    val canGoNext = month < currentMonth
    val density = LocalDensity.current
    val swipeThreshold = with(density) { 56.dp.toPx() }

    Column(Modifier.fillMaxWidth()) {
        // Month title + arrows.
        Row(
            Modifier.fillMaxWidth().padding(start = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = monthTitle(month, locale),
                style = styles.headline,
                color = p.textPrimary,
                modifier = Modifier.weight(1f).semantics { heading() }
            )
            MonthArrow(
                forward = false,
                enabled = true,
                description = language.pick3("Mes anterior", "Previous month", "Mês anterior"),
                tag = "${tagPrefix}_prev_month",
                onClick = { month = month.minusMonths(1) }
            )
            Spacer(Modifier.width(4.dp))
            MonthArrow(
                forward = true,
                enabled = canGoNext,
                description = language.pick3("Mes siguiente", "Next month", "Mês seguinte"),
                tag = "${tagPrefix}_next_month",
                onClick = { if (canGoNext) month = month.plusMonths(1) }
            )
        }
        Spacer(Modifier.height(10.dp))

        // Weekday initials, in the locale's own week order.
        val firstDow = WeekFields.of(locale).firstDayOfWeek
        Row(Modifier.fillMaxWidth()) {
            (0 until 7).forEach { i ->
                Text(
                    text = firstDow.plus(i.toLong()).getDisplayName(TextStyle.NARROW, locale).uppercase(locale),
                    style = styles.caption,
                    color = p.textSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f).clearAndSetSemantics { }
                )
            }
        }
        Spacer(Modifier.height(6.dp))

        AnimatedContent(
            targetState = month,
            transitionSpec = {
                val dir = if (targetState > initialState) 1 else -1
                (slideInHorizontally(tween(260)) { it / 5 * dir } + fadeIn(tween(220)))
                    .togetherWith(slideOutHorizontally(tween(260)) { -it / 5 * dir } + fadeOut(tween(160)))
            },
            label = "month",
            modifier = Modifier.pointerInput(month, canGoNext) {
                var drag = 0f
                detectHorizontalDragGestures(
                    onDragStart = { drag = 0f },
                    onHorizontalDrag = { change, amount -> drag += amount; change.consume() },
                    onDragEnd = {
                        if (drag > swipeThreshold) month = month.minusMonths(1)
                        else if (drag < -swipeThreshold && canGoNext) month = month.plusMonths(1)
                    }
                )
            }
        ) { shown ->
            val lead = (shown.atDay(1).dayOfWeek.value - firstDow.value + 7) % 7
            val cells: List<Int?> = List(lead) { null } + (1..shown.lengthOfMonth()).toList()
            Column(Modifier.fillMaxWidth()) {
                cells.chunked(7).forEach { week ->
                    Row(Modifier.fillMaxWidth()) {
                        week.forEach { day ->
                            if (day == null) {
                                Spacer(Modifier.weight(1f).height(DayCellHeight))
                            } else {
                                val date = shown.atDay(day)
                                val isToday = date == today
                                val isFuture = date.isAfter(today)
                                val isSelected = date == selected
                                val description = dayDescription(date)
                                Box(
                                    Modifier
                                        .weight(1f)
                                        .height(DayCellHeight)
                                        .then(if (legacyDayTagPrefix != null) Modifier.testTag("$legacyDayTagPrefix$day") else Modifier)
                                ) {
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .lumenPress(
                                                onClick = if (isFuture) null else ({ selected = date }),
                                                scale = 0.9f
                                            )
                                            .semantics {
                                                contentDescription = description
                                                this.selected = isSelected
                                            }
                                            .testTag("${tagPrefix}_day_$day"),
                                        contentAlignment = Alignment.TopCenter
                                    ) {
                                        cell(date, isToday, isFuture, isSelected)
                                        // Selection mark under the cell.
                                        Box(
                                            Modifier
                                                .align(Alignment.BottomCenter)
                                                .padding(bottom = 1.dp)
                                                .size(4.dp)
                                                .clip(CircleShape)
                                                .background(if (isSelected) p.textPrimary else Color.Transparent)
                                        )
                                    }
                                }
                            }
                        }
                        repeat(7 - week.size) { Spacer(Modifier.weight(1f).height(DayCellHeight)) }
                    }
                }
            }
        }

        // Selected day detail.
        AnimatedVisibility(
            visible = selected != null,
            enter = fadeIn(tween(200)) + expandVertically(tween(220)),
            exit = fadeOut(tween(150)) + shrinkVertically(tween(200))
        ) {
            val sel = selected
            AnimatedContent(targetState = sel, label = "detail", transitionSpec = {
                fadeIn(tween(200)) togetherWith fadeOut(tween(120))
            }) { date ->
                Text(
                    text = if (date != null) detailText(date) else "",
                    style = styles.subhead,
                    color = p.textPrimary,
                    modifier = Modifier.fillMaxWidth().padding(start = 6.dp, end = 6.dp, top = 10.dp)
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        Box(Modifier.fillMaxWidth().padding(horizontal = 6.dp).height(0.5.dp).background(p.separator))
        Spacer(Modifier.height(10.dp))
        Text(
            text = footerText(month),
            style = styles.footnote,
            color = p.textSecondary,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp).testTag(footerTag)
        )
    }
}

/** 32dp glass circle arrow with a 44dp target; dimmed and inert when disabled. */
@Composable
private fun MonthArrow(forward: Boolean, enabled: Boolean, description: String, tag: String, onClick: () -> Unit) {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .size(44.dp)
            .lumenPress(onClick, 0.9f, enabled = enabled)
            .semantics { contentDescription = description }
            .testTag(tag),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .size(32.dp)
                .alpha(if (enabled) 1f else 0.4f)
                .lumenGlass(CircleShape, lifted = false),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (forward) Icons.AutoMirrored.Outlined.ArrowForwardIos else Icons.AutoMirrored.Outlined.ArrowBackIos,
                contentDescription = null,
                tint = p.textPrimary,
                modifier = Modifier.size(13.dp).then(if (forward) Modifier else Modifier.padding(start = 3.dp))
            )
        }
    }
}

/** One day as a small activity ring with the date inside. */
@Composable
private fun RingDayCell(day: Int, progress: Float, isToday: Boolean, isFuture: Boolean) {
    val p = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    Box(Modifier.padding(top = 2.dp)) {
        LumenGradientRing(
            progress = progress,
            size = 36.dp,
            stroke = 3.5.dp,
            showTrack = !isFuture,
            strongTrack = isToday,
            animate = false
        ) {
            Text(
                text = day.toString(),
                style = styles.caption.copy(fontWeight = if (isToday) FontWeight.Bold else FontWeight.Medium),
                color = when {
                    isFuture -> p.inkQuiet
                    isToday -> p.textPrimary
                    else -> p.textPrimary
                }
            )
        }
    }
}

/** One day as a soft ink disc whose density follows the count (no goal). */
@Composable
internal fun DiscDayCell(day: Int, count: Int, isToday: Boolean, isFuture: Boolean) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val alpha = when {
        count <= 0 -> 0f
        count == 1 -> if (design.isDark) 0.14f else 0.10f
        count == 2 -> if (design.isDark) 0.22f else 0.18f
        else -> if (design.isDark) 0.34f else 0.28f
    }
    Box(
        Modifier
            .padding(top = 2.dp)
            .size(36.dp)
            .clip(CircleShape)
            .background(p.textPrimary.copy(alpha = alpha))
            .then(
                if (isToday) Modifier.border(1.dp, p.textPrimary.copy(alpha = 0.55f), CircleShape)
                else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            style = styles.caption.copy(fontWeight = if (isToday || count > 0) FontWeight.Bold else FontWeight.Medium),
            color = if (isFuture) p.inkQuiet else p.textPrimary
        )
    }
}

// ------------------------------------------------------------- the ring

/**
 * Apple Fitness-style ring: neutral track, a gradient that runs along the
 * sweep from 12 o'clock (ringStart → ringEnd), round caps, and a soft shadow
 * under the end cap once the ring closes on itself.
 */
@Composable
internal fun LumenGradientRing(
    progress: Float,
    size: Dp,
    stroke: Dp,
    modifier: Modifier = Modifier,
    showTrack: Boolean = true,
    strongTrack: Boolean = false,
    animate: Boolean = true,
    center: @Composable BoxScope.() -> Unit = {}
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val target = progress.coerceIn(0f, 1f)
    val animated by animateFloatAsState(
        targetValue = target,
        animationSpec = if (animate) spring(dampingRatio = 0.85f, stiffness = 120f) else tween(0),
        label = "ring"
    )
    val trackAlpha = when {
        design.isDark && strongTrack -> 0.16f
        design.isDark -> 0.09f
        strongTrack -> 0.11f
        else -> 0.065f
    }
    val track = p.textPrimary.copy(alpha = trackAlpha)
    val start = p.ringStart
    val end = p.ringEnd
    val shadow = Color.Black.copy(alpha = if (design.isDark) 0.45f else 0.22f)
    Box(modifier.size(size), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val w = stroke.toPx()
            val inset = w / 2f
            val arc = Size(this.size.width - w, this.size.height - w)
            val tl = Offset(inset, inset)
            if (showTrack) {
                drawArc(track, 0f, 360f, false, tl, arc, style = Stroke(w))
            }
            val f = animated
            if (f <= 0.0005f) return@Canvas
            rotate(-90f) {
                if (f < 1f) {
                    // The start cap reaches behind 12 o'clock (fraction ≈ 1),
                    // so the gradient returns to `start` at 1 to keep it clean.
                    val brush = Brush.sweepGradient(0f to start, f to end, 1f to start)
                    drawArc(brush, 0f, 360f * f, false, tl, arc, style = Stroke(w, cap = StrokeCap.Round))
                } else {
                    val brush = Brush.sweepGradient(0f to start, 1f to end)
                    drawArc(brush, 0f, 360f, false, tl, arc, style = Stroke(w))
                    // End cap with its shadow over the start (the closed ring).
                    val r = (this.size.width - w) / 2f
                    val cx = this.size.width / 2f + r
                    val cy = this.size.height / 2f
                    drawCircle(shadow, radius = w * 0.62f, center = Offset(cx, cy + w * 0.18f))
                    drawCircle(end, radius = w / 2f, center = Offset(cx, cy))
                }
            }
        }
        center()
    }
}

// ------------------------------------------------------------- helpers

private val DayCellHeight = 46.dp

internal fun AppLanguage.pick3(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

internal fun calendarLocale(language: AppLanguage): Locale = when (language) {
    AppLanguage.ES -> Locale("es", "ES")
    AppLanguage.EN -> Locale("en", "US")
    AppLanguage.PT -> Locale("pt", "BR")
}

/** "Noviembre 2026": standalone month name, capitalised (ES/PT are lowercase). */
internal fun monthTitle(month: YearMonth, locale: Locale): String =
    month.format(DateTimeFormatter.ofPattern("LLLL yyyy", locale))
        .replaceFirstChar { it.titlecase(locale) }

/** Days of [month] that have started (all of them for a past month). */
internal fun elapsedDays(month: YearMonth, today: LocalDate = LocalDate.now()): Int {
    val current = YearMonth.from(today)
    return when {
        month < current -> month.lengthOfMonth()
        month == current -> today.dayOfMonth
        else -> 0
    }
}

internal fun daysLabel(days: Int, lang: AppLanguage): String = when (lang) {
    AppLanguage.ES -> if (days == 1) "1 día activo" else "$days días activos"
    AppLanguage.EN -> if (days == 1) "1 active day" else "$days active days"
    AppLanguage.PT -> if (days == 1) "1 dia ativo" else "$days dias ativos"
}

/** "Martes 4" / "Tuesday 4" / "Terça 4"; "Hoy" for today. */
internal fun dayName(date: LocalDate, lang: AppLanguage): String {
    if (date == LocalDate.now()) return lang.pick3("Hoy", "Today", "Hoje")
    val locale = calendarLocale(lang)
    val name = date.dayOfWeek.getDisplayName(TextStyle.FULL, locale)
        .substringBefore('-')
        .replaceFirstChar { it.titlecase(locale) }
    return "$name ${date.dayOfMonth}"
}

/** "martes, 4 de noviembre de 2026" for TalkBack. */
internal fun fullDate(date: LocalDate, lang: AppLanguage): String =
    date.format(DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.FULL).withLocale(calendarLocale(lang)))
