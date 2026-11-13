package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.Directions
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.NoFood
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.LiturgicalColor
import com.example.data.models.AppLanguage
import com.example.data.models.CatholicPilgrimageSanctuary
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.TextFormat
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppContentCard
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.design.AppSeasonDot
import com.example.ui.design.appDockInset
import com.example.ui.design.lumenLiturgicalColor
import com.example.ui.design.rememberFirstAsset
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.util.Locale

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** Tab indexes of [PilgrimageAndHabitsScreen]'s `initialTab`. */
const val PILGRIMAGE_TAB_CALENDAR = 0
const val PILGRIMAGE_TAB_SHRINES = 1

/**
 * The Church's calendar and the world's shrines.
 *
 * Calendar opens on today's celebration, then the days worth knowing about in
 * the next four weeks. Shrines is a short list; each shrine is its own reading
 * page. The habits that once shared this screen live on Habits & Virtues.
 *
 * [initialTab] is 0 for the calendar and 1 for the shrines (Home's
 * "Santuarios y Fe" tile opens the latter).
 */
@Composable
fun PilgrimageAndHabitsScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    initialTab: Int = PILGRIMAGE_TAB_CALENDAR
) {
    AppEdgeToEdge()
    val design = LocalAppDesign.current
    var selectedTab by rememberSaveable(initialTab) {
        mutableStateOf(initialTab.coerceIn(PILGRIMAGE_TAB_CALENDAR, PILGRIMAGE_TAB_SHRINES))
    }
    var openShrineId by rememberSaveable { mutableStateOf<String?>(null) }
    AppBackHandler(enabled = openShrineId != null) { openShrineId = null }

    val sanctuaries = repository.globalPilgrimages
    val days = remember { repository.liturgicalCalendarDays }
    val hero = rememberFirstAsset("heroes/lumen/pilgrimage.jpg", "heroes/votive_candles.jpg")

    Box(
        modifier
            .fillMaxSize()
            .testTag("pilgrimage_and_habits_screen")
    ) {
        AnimatedContent(
            targetState = openShrineId,
            transitionSpec = {
                val forward = targetState != null
                val dir = if (forward) 1 else -1
                (slideInHorizontally(tween(if (design.reduceMotion) 0 else 300)) { dir * it / 3 } + fadeIn(tween(if (design.reduceMotion) 0 else 300))) togetherWith
                    (slideOutHorizontally(tween(if (design.reduceMotion) 0 else 300)) { -dir * it / 3 } + fadeOut(tween(if (design.reduceMotion) 0 else 200)))
            },
            label = "shrinePage"
        ) { shrineId ->
            val shrine = shrineId?.let { id -> sanctuaries.firstOrNull { it.id == id } }
            if (shrine != null) {
                ShrineDetail(shrine = shrine, currentLanguage = currentLanguage, hero = hero)
            } else {
                PilgrimageHome(
                    selectedTab = selectedTab,
                    onSelectTab = { selectedTab = it },
                    days = days,
                    sanctuaries = sanctuaries,
                    currentLanguage = currentLanguage,
                    hero = hero,
                    onOpenShrine = { openShrineId = it.id }
                )
            }
        }
    }
}

// ------------------------------------------------------------------ home page

@Composable
private fun PilgrimageHome(
    selectedTab: Int,
    onSelectTab: (Int) -> Unit,
    days: List<LiturgicalCalendarDay>,
    sanctuaries: List<CatholicPilgrimageSanctuary>,
    currentLanguage: AppLanguage,
    hero: String?,
    onOpenShrine: (CatholicPilgrimageSanctuary) -> Unit
) {
    val design = LocalAppDesign.current
    val dock = appDockInset()
    val title = currentLanguage.pick("Calendario y Santuarios", "Calendar & Shrines", "Calendário e Santuários")

    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = dock + 24.dp)) {
        item(key = "pilgrimage_header") {
            AppDetailHeader(title = title, assetPath = hero, imageHeight = 180.dp)
        }
        item(key = "pilgrimage_tabs") {
            AppSegmentedTabs(
                options = listOf(
                    currentLanguage.pick("Calendario", "Calendar", "Calendário"),
                    currentLanguage.pick("Santuarios", "Shrines", "Santuários")
                ),
                selectedIndex = selectedTab,
                onSelect = onSelectTab,
                modifier = Modifier.padding(horizontal = design.spacing.gutter)
            )
            Spacer(Modifier.height(design.spacing.sectionGap - 8.dp))
        }
        if (selectedTab == PILGRIMAGE_TAB_CALENDAR) {
            calendarItems(days, currentLanguage)
        } else {
            shrineItems(sanctuaries, currentLanguage, onOpenShrine)
        }
    }
}

// ------------------------------------------------------------------- calendar

private fun androidx.compose.foundation.lazy.LazyListScope.calendarItems(
    days: List<LiturgicalCalendarDay>,
    language: AppLanguage
) {
    if (days.isEmpty()) {
        item(key = "calendar_empty") {
            AppEmptyState(
                title = language.pick("Sin celebraciones", "No celebrations", "Sem celebrações"),
                body = language.pick(
                    "El calendario no tiene días próximos cargados.",
                    "The calendar has no upcoming days loaded.",
                    "O calendário não tem dias próximos carregados."
                ),
                icon = Icons.Outlined.CalendarMonth
            )
        }
        return
    }

    val dates = datesFor(days)
    val today = days.first()
    val upcoming = days.drop(1).mapIndexed { i, d -> d to dates.getOrNull(i + 1) }

    item(key = "calendar_today") {
        TodayCard(today, dates.firstOrNull(), language)
    }

    if (upcoming.isNotEmpty()) {
        item(key = "calendar_upcoming_title") {
            SectionHeadline(language.pick("Próximas celebraciones", "Upcoming celebrations", "Próximas celebrações"))
        }
        val byMonth = upcoming.groupBy { (_, date) -> date?.month ?: 0 }
        val showMonths = byMonth.size > 1
        byMonth.entries.forEachIndexed { groupIndex, (month, entries) ->
            item(key = "calendar_group_$groupIndex") {
                Column {
                    if (showMonths && month != 0) {
                        Text(
                            text = monthName(month, language),
                            style = LocalAppTextStyles.current.footnote,
                            color = LocalAppDesign.current.palette.textSecondary,
                            modifier = Modifier.padding(
                                start = LocalAppDesign.current.spacing.gutter + 4.dp,
                                bottom = 8.dp,
                                top = if (groupIndex == 0) 0.dp else 20.dp
                            )
                        )
                    } else if (groupIndex > 0) {
                        Spacer(Modifier.height(20.dp))
                    }
                    AppListGroup {
                        entries.forEach { (day, date) -> CalendarDayRow(day, date, language) }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeadline(text: String) {
    val design = LocalAppDesign.current
    Text(
        text = text,
        style = LocalAppTextStyles.current.headline,
        color = design.palette.textPrimary,
        modifier = Modifier
            .padding(horizontal = design.spacing.gutter)
            .padding(top = design.spacing.sectionGap, bottom = 12.dp)
    )
}

/** The accent a liturgical colour takes in Lumen, and its spoken name. */
private fun liturgicalAccent(hex: Long, language: AppLanguage): Pair<Color, String> {
    val named = LiturgicalColor.entries.firstOrNull { it.hex == hex }
    return lumenLiturgicalColor(named?.displayName?.en, hex) to
        (named?.displayName?.get(language) ?: "")
}

@Composable
private fun TodayCard(day: LiturgicalCalendarDay, date: CalendarDate?, language: AppLanguage) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val (accent, colourName) = liturgicalAccent(day.liturgicalColorHex, language)
    val dateLine = date?.let { longDate(it, language) }
        ?: language.pick(
            "${day.dayOfMonth} de ${day.monthName.lowercase()}",
            "${day.monthName} ${day.dayOfMonth}",
            "${day.dayOfMonth} de ${day.monthName.lowercase()}"
        )
    val eyebrow = language.pick("Hoy", "Today", "Hoje") + " · " + dateLine
    val isSunday = date?.isSunday == true
    val meta = listOfNotNull(
        day.rank.get(language).takeIf { day.rank != ferialRank },
        day.seasonName.get(language),
        (if (isSunday) day.sundayCycle else day.weekdayCycle)?.get(language)
    ).joinToString(" · ")
    val precept = day.fastingRule?.get(language)?.takeIf { it.isNotBlank() }
    val gospel = day.gospelVerseRef.takeIf { it.isNotBlank() }?.let {
        language.pick("Evangelio", "Gospel", "Evangelho") + " · " + TextFormat.ranges(it)
    }
    val colourLabel = language.pick("Color litúrgico", "Liturgical colour", "Cor litúrgica") + ": " + colourName.lowercase()
    val spoken = listOfNotNull(eyebrow, day.celebrationTitle.get(language), meta, precept, gospel, colourLabel)
        .joinToString(". ")

    AppGlassSurface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .testTag("calendar_day_${day.monthName}_${day.dayOfMonth}")
            .semantics(mergeDescendants = true) { contentDescription = spoken },
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Row(Modifier.height(IntrinsicSize.Min)) {
            Box(
                Modifier
                    .width(3.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(percent = 50))
                    .background(accent)
            )
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(eyebrow, style = styles.eyebrow, color = p.textSecondary)
                Spacer(Modifier.height(6.dp))
                Text(day.celebrationTitle.get(language), style = styles.title2, color = p.textPrimary)
                if (meta.isNotBlank()) {
                    Spacer(Modifier.height(4.dp))
                    Text(meta, style = styles.footnote, color = p.textSecondary)
                }
                if (precept != null) {
                    Spacer(Modifier.height(14.dp))
                    IconLine(
                        icon = if (day.isFastOrAbstinence) Icons.Outlined.NoFood else Icons.Outlined.Church,
                        text = precept
                    )
                }
                if (gospel != null) {
                    Spacer(Modifier.height(if (precept != null) 8.dp else 14.dp))
                    IconLine(icon = Icons.Outlined.MenuBook, text = gospel)
                }
            }
        }
    }
}

@Composable
private fun IconLine(icon: ImageVector, text: String) {
    val p = LocalAppDesign.current.palette
    Row(verticalAlignment = Alignment.Top) {
        Icon(icon, null, tint = p.textPrimary, modifier = Modifier.padding(top = 1.dp).size(20.dp))
        Spacer(Modifier.width(10.dp))
        Text(text, style = LocalAppTextStyles.current.subhead, color = p.textPrimary)
    }
}

@Composable
private fun CalendarDayRow(day: LiturgicalCalendarDay, date: CalendarDate?, language: AppLanguage) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val (accent, colourName) = liturgicalAccent(day.liturgicalColorHex, language)
    val gospel = day.gospelVerseRef.takeIf { it.isNotBlank() }?.let {
        language.pick("Evangelio", "Gospel", "Evangelho") + " · " + TextFormat.ranges(it)
    }
    val precept = day.fastingRule?.get(language)?.takeIf { it.isNotBlank() }
    val redundantRank = day.rank == ferialRank || day.rank == sundayRank
    val detail = listOfNotNull(
        day.rank.get(language).takeIf { !redundantRank },
        precept,
        gospel
    ).joinToString(" · ")
    val spoken = listOfNotNull(
        date?.let { longDate(it, language) } ?: "${day.dayOfMonth} ${day.monthName}",
        day.celebrationTitle.get(language),
        detail.takeIf { it.isNotBlank() },
        language.pick("Color litúrgico", "Liturgical colour", "Cor litúrgica") + ": " + colourName.lowercase()
    ).joinToString(". ")

    Column(
        Modifier
            .fillMaxWidth()
            .testTag("calendar_day_${day.monthName}_${day.dayOfMonth}")
            .semantics(mergeDescendants = true) { contentDescription = spoken }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.width(44.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                if (date != null) {
                    Text(shortWeekday(date, language), style = styles.caption, color = p.textSecondary, maxLines = 1)
                }
                Text(day.dayOfMonth.toString(), style = styles.title2, color = p.textPrimary, maxLines = 1)
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(day.celebrationTitle.get(language), style = styles.body, color = p.textPrimary, maxLines = 3)
                if (detail.isNotBlank()) {
                    Spacer(Modifier.height(2.dp))
                    Text(detail, style = styles.footnote, color = p.textSecondary)
                }
            }
            Spacer(Modifier.width(12.dp))
            AppSeasonDot(accent, size = 8.dp)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(start = 18.dp + 44.dp + 16.dp)
                .height(0.5.dp)
                .background(p.separator)
        )
    }
}

private val ferialRank = com.example.data.liturgy.CelebrationRank.FERIAL.displayName
private val sundayRank = com.example.data.liturgy.CelebrationRank.SUNDAY.displayName

/**
 * The list is today plus a subsequence of the next 28 days, in order, so each
 * entry is the first date from the cursor on with the same day of the month.
 * The model carries no date of its own.
 */
private fun datesFor(days: List<LiturgicalCalendarDay>): List<CalendarDate?> {
    val today = LiturgicalCalendar.today()
    var cursor = 0
    return days.map { day ->
        var found: CalendarDate? = null
        while (cursor < 29 && found == null) {
            val candidate = today.plusDays(cursor)
            cursor++
            if (candidate.day == day.dayOfMonth) found = candidate
        }
        found
    }
}

private val MonthsEs = listOf("enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre")
private val MonthsEn = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
private val MonthsPt = listOf("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro")
private val WeekdaysEs = listOf("lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo")
private val WeekdaysEn = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
private val WeekdaysPt = listOf("segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira", "sábado", "domingo")
private val ShortEs = listOf("lun.", "mar.", "mié.", "jue.", "vie.", "sáb.", "dom.")
private val ShortEn = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
private val ShortPt = listOf("seg.", "ter.", "qua.", "qui.", "sex.", "sáb.", "dom.")

private fun monthName(month: Int, language: AppLanguage): String {
    val i = (month - 1).coerceIn(0, 11)
    val name = language.pick(MonthsEs[i], MonthsEn[i], MonthsPt[i])
    return name.replaceFirstChar { it.uppercase() }
}

private fun shortWeekday(date: CalendarDate, language: AppLanguage): String {
    val i = date.isoDayOfWeek - 1
    return language.pick(ShortEs[i], ShortEn[i], ShortPt[i])
}

/** "domingo 8 de noviembre" · "Sunday, November 8" · "domingo, 8 de novembro". */
private fun longDate(date: CalendarDate, language: AppLanguage): String {
    val wd = date.isoDayOfWeek - 1
    val m = date.month - 1
    return language.pick(
        "${WeekdaysEs[wd]} ${date.day} de ${MonthsEs[m]}",
        "${WeekdaysEn[wd]}, ${MonthsEn[m]} ${date.day}",
        "${WeekdaysPt[wd]}, ${date.day} de ${MonthsPt[m]}"
    )
}

// -------------------------------------------------------------------- shrines

private fun androidx.compose.foundation.lazy.LazyListScope.shrineItems(
    sanctuaries: List<CatholicPilgrimageSanctuary>,
    language: AppLanguage,
    onOpen: (CatholicPilgrimageSanctuary) -> Unit
) {
    if (sanctuaries.isEmpty()) {
        item(key = "shrines_empty") {
            AppEmptyState(
                title = language.pick("Sin santuarios", "No shrines", "Sem santuários"),
                body = language.pick(
                    "La guía de peregrinaciones no tiene lugares cargados.",
                    "The pilgrimage guide has no places loaded.",
                    "O guia de peregrinações não tem lugares carregados."
                ),
                icon = Icons.Outlined.Place
            )
        }
        return
    }
    item(key = "shrines_intro") {
        val design = LocalAppDesign.current
        Text(
            text = language.pick(
                "Lugares santos, su historia y la indulgencia plenaria que ofrecen.",
                "Holy places, their history and the plenary indulgence they offer.",
                "Lugares santos, a sua história e a indulgência plenária que oferecem."
            ),
            style = LocalAppTextStyles.current.subhead,
            color = design.palette.textSecondary,
            modifier = Modifier
                .padding(horizontal = design.spacing.gutter + 4.dp)
                .padding(bottom = 16.dp)
        )
    }
    item(key = "shrines_list") {
        AppListGroup {
            sanctuaries.forEach { shrine ->
                val place = placeLine(shrine.cityCountry.get(language))
                AppListRow(
                    title = shrine.name.get(language),
                    subtitle = place,
                    leadingIcon = Icons.Outlined.Place,
                    onClick = { onOpen(shrine) },
                    modifier = Modifier
                        .heightIn(min = 64.dp)
                        .testTag("shrine_card_${shrine.id}")
                )
            }
        }
    }
}

/** "Ciudad del Vaticano / Roma, Italia" → "Ciudad del Vaticano / Roma · Italia". */
private fun placeLine(place: String): String {
    val cut = place.lastIndexOf(',')
    if (cut <= 0) return place.trim()
    return place.take(cut).trim() + " · " + place.substring(cut + 1).trim()
}

@Composable
private fun ShrineDetail(
    shrine: CatholicPilgrimageSanctuary,
    currentLanguage: AppLanguage,
    hero: String?
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val dock = appDockInset()
    val name = shrine.name.get(currentLanguage)
    AppInlineTitle(name)

    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = dock + 24.dp)) {
        item(key = "shrine_header") {
            AppDetailHeader(
                title = name,
                assetPath = hero,
                meta = placeLine(shrine.cityCountry.get(currentLanguage)),
                metaIcon = Icons.Outlined.Place,
                imageHeight = 180.dp
            )
        }
        item(key = "shrine_body") {
            Column(
                Modifier.padding(horizontal = design.spacing.gutter),
                verticalArrangement = Arrangement.spacedBy(design.spacing.sectionGap)
            ) {
                ReadingSection(
                    currentLanguage.pick("Historia", "History", "História"),
                    shrine.historicalOrigin.get(currentLanguage)
                )
                ReadingSection(
                    currentLanguage.pick("Significado espiritual", "Spiritual significance", "Significado espiritual"),
                    shrine.spiritualSignificance.get(currentLanguage)
                )
                ReadingSection(
                    currentLanguage.pick("Reliquias y patronazgo", "Relics and patronage", "Relíquias e patrocínio"),
                    shrine.relicsAndPatron.get(currentLanguage)
                )
                AppContentCard(
                    title = currentLanguage.pick("Indulgencia plenaria", "Plenary indulgence", "Indulgência plenária"),
                    icon = Icons.Outlined.Verified
                ) {
                    Text(
                        shrine.plenaryIndulgenceNote.get(currentLanguage),
                        style = styles.reading,
                        color = p.textPrimary
                    )
                }
                AppContentCard(
                    title = currentLanguage.pick("Consejo para el peregrino", "Advice for the pilgrim", "Conselho ao peregrino"),
                    icon = Icons.Outlined.Lightbulb
                ) {
                    Text(
                        shrine.practicalPilgrimAdvice.get(currentLanguage),
                        style = styles.reading,
                        color = p.textPrimary
                    )
                }
                Text(
                    text = coordinates(shrine.coordinateLatLong, currentLanguage),
                    style = styles.footnote,
                    color = p.textSecondary
                )
                AppPrimaryCapsule(
                    label = currentLanguage.pick("Ver en el mapa", "View on map", "Ver no mapa"),
                    onClick = { openOnMap(context, shrine, currentLanguage) },
                    icon = Icons.Outlined.Directions,
                    tag = "shrine_map_button_${shrine.id}"
                )
            }
        }
    }
}

@Composable
private fun ReadingSection(title: String, text: String) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Column(Modifier.widthIn(max = design.spacing.readingMaxWidth)) {
        Text(title, style = styles.headline, color = design.palette.textPrimary)
        Spacer(Modifier.height(10.dp))
        Text(text, style = styles.reading, color = design.palette.textPrimary)
    }
}

/** "41,9022° N · 12,4539° E" (decimal comma in Spanish and Portuguese). */
private fun coordinates(latLon: Pair<Double, Double>, language: AppLanguage): String {
    val locale = if (language == AppLanguage.EN) Locale.US else Locale("es")
    val (lat, lon) = latLon
    val ns = if (lat >= 0) "N" else "S"
    val ew = if (lon >= 0) "E" else if (language == AppLanguage.EN) "W" else "O"
    return String.format(locale, "%.4f° %s · %.4f° %s", kotlin.math.abs(lat), ns, kotlin.math.abs(lon), ew)
}

/**
 * Hands the shrine's coordinates to whatever maps application is installed.
 * A device that has none refuses the intent, so the place itself is read back
 * to the pilgrim instead of the screen dying on them.
 */
private fun openOnMap(
    context: Context,
    shrine: CatholicPilgrimageSanctuary,
    currentLanguage: AppLanguage
) {
    val (lat, lon) = shrine.coordinateLatLong
    val label = shrine.name.get(currentLanguage) + ", " + shrine.cityCountry.get(currentLanguage)
    val geoUri = Uri.parse("geo:$lat,$lon?q=${Uri.encode(label)}")
    runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, geoUri)) }
        .onFailure {
            Toast.makeText(
                context,
                shrine.cityCountry.get(currentLanguage),
                Toast.LENGTH_LONG
            ).show()
        }
}
