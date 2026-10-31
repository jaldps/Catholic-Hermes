package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.CatholicPilgrimageSanctuary
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.models.SpiritualHabitItem
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppBanner
import com.example.ui.design.AppCard
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppMetricTile
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/**
 * The sanctuary destination: a catalogue of shrines, the liturgical days around
 * today, and the rule of life that shares the tab strip with them.
 *
 * Everything here is read-only bundled data except the habit ticks, which are
 * deliberately in-memory — this screen offers the day's checklist, while the
 * kept history lives on the habits screen.
 */
@Composable
fun PilgrimageAndHabitsScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    var selectedMainTab by remember { mutableIntStateOf(0) }
    val design = LocalAppDesign.current

    val habitCompletionMap = remember {
        mutableStateMapOf<String, Boolean>().apply {
            repository.spiritualHabitsList.forEach { habit ->
                put(habit.id, false)
            }
        }
    }

    val tabs = listOf(
        currentLanguage.pick("Calendario", "Calendar", "Calendário"),
        currentLanguage.pick("Hábitos", "Habits", "Hábitos"),
        currentLanguage.pick("Santuarios", "Shrines", "Santuários")
    )
    val tabsInset = segmentedTabsInset()

    // The screen's one large title, then the selector; both scroll away with
    // the pane. Each pane opens on a section header, never a second title.
    val header: LazyListScope.() -> Unit = {
        item(key = "pilgrimage_title") {
            AppScreenTitle(
                title = currentLanguage.pick(
                    "Calendario y Santuarios",
                    "Calendar & Shrines",
                    "Calendário e Santuários"
                ),
                subtitle = currentLanguage.pick(
                    "El año de la Iglesia, una regla de vida y los lugares santos.",
                    "The Church's year, a rule of life and the holy places.",
                    "O ano da Igreja, uma regra de vida e os lugares santos."
                )
            )
        }
        item(key = "pilgrimage_tabs") {
            AppSegmentedTabs(
                options = tabs,
                selectedIndex = selectedMainTab,
                onSelect = { selectedMainTab = it },
                modifier = Modifier.padding(horizontal = tabsInset)
            )
            Spacer(Modifier.height(design.spacing.cardGap / 2))
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("pilgrimage_and_habits_screen")
    ) {
        when (selectedMainTab) {
            0 -> LiturgicalCalendarTab(
                days = repository.liturgicalCalendarDays,
                currentLanguage = currentLanguage,
                header = header
            )

            1 -> HolyHabitsTab(
                habits = repository.spiritualHabitsList,
                isCompleted = { id -> habitCompletionMap[id] ?: false },
                onToggle = { id -> habitCompletionMap[id] = !(habitCompletionMap[id] ?: false) },
                currentLanguage = currentLanguage,
                header = header
            )

            else -> SanctuariesTab(
                sanctuaries = repository.globalPilgrimages,
                currentLanguage = currentLanguage,
                header = header
            )
        }
    }
}

// ------------------------------------------------------------------- calendar

@Composable
private fun LiturgicalCalendarTab(
    days: List<LiturgicalCalendarDay>,
    currentLanguage: AppLanguage,
    header: LazyListScope.() -> Unit
) {
    val design = LocalAppDesign.current

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        if (days.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick(
                        "Sin celebraciones",
                        "No celebrations",
                        "Sem celebrações"
                    ),
                    body = currentLanguage.pick(
                        "El calendario no tiene días próximos cargados.",
                        "The calendar has no upcoming days loaded.",
                        "O calendário não tem dias próximos carregados."
                    ),
                    icon = Icons.Default.CalendarMonth
                )
            }
        } else {
            item {
                AppSectionHeader(
                    title = currentLanguage.pick(
                        "Próximas celebraciones",
                        "Upcoming celebrations",
                        "Próximas celebrações"
                    ),
                    subtitle = currentLanguage.pick(
                        "Colores litúrgicos, solemnidades y días de precepto.",
                        "Liturgical colours, solemnities and days of precept.",
                        "Cores litúrgicas, solenidades e dias de preceito."
                    )
                )
            }

            itemsIndexed(
                items = days,
                key = { _, day -> "${day.monthName}_${day.dayOfMonth}" }
            ) { index, day ->
                if (index > 0) Spacer(Modifier.height(design.spacing.sectionGap))
                LiturgicalDayEntry(day = day, currentLanguage = currentLanguage)
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

@Composable
private fun LiturgicalDayEntry(day: LiturgicalCalendarDay, currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current

    Column {
        // The liturgical colour is data, so it travels as the banner's accent
        // rather than as a coloured disc the screen draws for itself.
        AppBanner(
            title = currentLanguage.pick(
                "${day.dayOfMonth} de ${day.monthName.lowercase()}",
                "${day.monthName} ${day.dayOfMonth}",
                "${day.dayOfMonth} de ${day.monthName.lowercase()}"
            ) + " · " + day.seasonName.get(currentLanguage),
            body = day.celebrationTitle.get(currentLanguage),
            accentColorHex = day.liturgicalColorHex
        )

        Spacer(Modifier.height(design.spacing.cardGap))

        AppListGroup {
            AppListRow(
                title = currentLanguage.pick("Rango", "Rank", "Grau"),
                subtitle = day.rank.get(currentLanguage),
                leadingIcon = Icons.Default.Star,
                showsNavigationAffordance = false
            )
            if (day.gospelVerseRef.isNotBlank()) {
                AppListRow(
                    title = currentLanguage.pick("Evangelio", "Gospel", "Evangelho"),
                    subtitle = day.gospelVerseRef,
                    leadingIcon = Icons.Default.MenuBook,
                    showsNavigationAffordance = false
                )
            }
        }

        day.fastingRule?.let { rule ->
            Spacer(Modifier.height(design.spacing.cardGap))
            Apparatus(
                label = currentLanguage.pick(
                    "Ayuno y precepto",
                    "Fasting and precept",
                    "Jejum e preceito"
                ),
                text = rule.get(currentLanguage),
                modifier = Modifier.padding(horizontal = design.spacing.gutter)
            )
        }
    }
}

// --------------------------------------------------------------------- habits

@Composable
private fun HolyHabitsTab(
    habits: List<SpiritualHabitItem>,
    isCompleted: (String) -> Boolean,
    onToggle: (String) -> Unit,
    currentLanguage: AppLanguage,
    header: LazyListScope.() -> Unit
) {
    val design = LocalAppDesign.current
    val completedCount = habits.count { isCompleted(it.id) }

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        if (habits.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Sin hábitos", "No habits", "Sem hábitos"),
                    body = currentLanguage.pick(
                        "La regla de vida no tiene prácticas cargadas.",
                        "The rule of life has no practices loaded.",
                        "A regra de vida não tem práticas carregadas."
                    ),
                    icon = Icons.Default.SelfImprovement
                )
            }
        } else {
            item {
                AppSectionHeader(
                    title = currentLanguage.pick(
                        "Regla de vida",
                        "Rule of life",
                        "Regra de vida"
                    ),
                    subtitle = currentLanguage.pick(
                        "La santificación diaria, paso a paso. Toca una práctica al cumplirla.",
                        "Daily sanctification, step by step. Tap a practice once it is kept.",
                        "A santificação diária, passo a passo. Toque numa prática ao cumpri-la."
                    )
                )
            }

            item {
                AppMetricTile(
                    value = currentLanguage.pick(
                        "$completedCount de ${habits.size}",
                        "$completedCount of ${habits.size}",
                        "$completedCount de ${habits.size}"
                    ),
                    caption = currentLanguage.pick(
                        "Cumplidos hoy",
                        "Kept today",
                        "Cumpridos hoje"
                    ),
                    icon = Icons.Default.SelfImprovement,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("habit_progress_tile")
                )
                Spacer(Modifier.height(design.spacing.cardGap))
            }

            items(habits, key = { it.id }) { habit ->
                HabitEntry(
                    habit = habit,
                    completed = isCompleted(habit.id),
                    currentLanguage = currentLanguage,
                    onToggle = { onToggle(habit.id) }
                )
                Spacer(Modifier.height(design.spacing.cardGap))
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/**
 * One practice of the rule of life. Kept as a card rather than a list row
 * because the row contract holds title and subtitle to a single line in
 * Material, and the description is the half of this entry worth reading.
 */
@Composable
private fun HabitEntry(
    habit: SpiritualHabitItem,
    completed: Boolean,
    currentLanguage: AppLanguage,
    onToggle: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    AppCard(
        modifier = Modifier
            .padding(horizontal = design.spacing.gutter)
            .testTag("habit_card_${habit.id}"),
        onClick = onToggle
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Icon(
                imageVector = if (completed) {
                    Icons.Default.CheckCircle
                } else {
                    Icons.Default.RadioButtonUnchecked
                },
                contentDescription = null,
                tint = if (completed) design.palette.accent else design.palette.textTertiary,
                modifier = Modifier
                    // Nudged onto the title's x-height instead of its cap line.
                    .padding(top = 2.dp)
                    .size(design.decor.rowIconSize)
            )
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Column(Modifier.weight(1f)) {
                Text(
                    text = habit.title.get(currentLanguage),
                    style = styles.rowTitle,
                    color = if (completed) design.palette.accent else design.palette.textPrimary
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = habit.description.get(currentLanguage),
                    style = styles.rowSubtitle,
                    color = design.palette.textSecondary,
                    modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = caps(
                        habit.category.get(currentLanguage) + " · " + com.example.data.models.localizedHabitTime(habit.timeSuggestion, currentLanguage)
                    ),
                    style = styles.sectionEyebrow,
                    color = design.palette.textSecondary
                )
            }
        }
    }
}

// ---------------------------------------------------------------- sanctuaries

@Composable
private fun SanctuariesTab(
    sanctuaries: List<CatholicPilgrimageSanctuary>,
    currentLanguage: AppLanguage,
    header: LazyListScope.() -> Unit
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        if (sanctuaries.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick(
                        "Sin santuarios",
                        "No shrines",
                        "Sem santuários"
                    ),
                    body = currentLanguage.pick(
                        "La guía de peregrinaciones no tiene lugares cargados.",
                        "The pilgrimage guide has no places loaded.",
                        "O guia de peregrinações não tem lugares carregados."
                    ),
                    icon = Icons.Default.Place
                )
            }
        } else {
            item {
                AppSectionHeader(
                    title = currentLanguage.pick(
                        "Santuarios del mundo",
                        "Shrines worldwide",
                        "Santuários do mundo"
                    ),
                    subtitle = currentLanguage.pick(
                        "Lugares santos, su historia y la indulgencia plenaria que ofrecen.",
                        "Holy places, their history and the plenary indulgence they offer.",
                        "Lugares santos, a sua história e a indulgência plenária que oferecem."
                    )
                )
            }

            itemsIndexed(sanctuaries, key = { _, shrine -> shrine.id }) { index, shrine ->
                if (index > 0) AppOrnament()
                SanctuaryEntry(
                    shrine = shrine,
                    currentLanguage = currentLanguage,
                    onNavigateMap = { openOnMap(context, shrine, currentLanguage) }
                )
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

@Composable
private fun SanctuaryEntry(
    shrine: CatholicPilgrimageSanctuary,
    currentLanguage: AppLanguage,
    onNavigateMap: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val place = placeParts(shrine.cityCountry.get(currentLanguage))

    Column(Modifier.testTag("shrine_card_${shrine.id}")) {
        AppListGroup {
            AppListRow(
                title = shrine.name.get(currentLanguage),
                subtitle = place.locality,
                leadingIcon = Icons.Default.Place,
                trailingText = place.region,
                onClick = onNavigateMap
            )
        }

        Spacer(Modifier.height(design.spacing.cardGap))

        Column(
            modifier = Modifier.padding(horizontal = design.spacing.gutter),
            verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
        ) {
            Text(
                text = shrine.historicalOrigin.get(currentLanguage),
                style = styles.scripture,
                color = design.palette.textPrimary,
                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
            )
            Apparatus(
                label = currentLanguage.pick("Reliquias", "Relics", "Relíquias"),
                text = shrine.relicsAndPatron.get(currentLanguage)
            )
            Apparatus(
                label = currentLanguage.pick(
                    "Indulgencia Plenaria y Gracia",
                    "Plenary Indulgence & Grace",
                    "Indulgência Plenária e Graça"
                ),
                text = shrine.plenaryIndulgenceNote.get(currentLanguage)
            )
        }

        Spacer(Modifier.height(design.spacing.sectionGap / 2))

        // Its own full-width row: "Ver ubicación en Mapa" set in tracked Roman
        // capitals has no business sharing a line with anything.
        AppSecondaryButton(
            label = currentLanguage.pick(
                "Ver ubicación en Mapa",
                "View location on Map",
                "Ver localização no Mapa"
            ),
            onClick = onNavigateMap,
            leadingIcon = Icons.Default.Directions,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .testTag("shrine_map_button_${shrine.id}")
        )
    }
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

// ---------------------------------------------------------------- scaffolding

/**
 * A labelled note of the kind printed under a catalogue entry. The kit's banner
 * is the one hero strip on a page, so hanging one under every shrine would
 * overstate it; this is an eyebrow over its text, built from tokens.
 */
@Composable
private fun Apparatus(label: String, text: String, modifier: Modifier = Modifier) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(modifier.fillMaxWidth()) {
        Text(
            text = caps(label),
            style = styles.sectionEyebrow,
            color = design.palette.textSecondary
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = text,
            style = styles.rowSubtitle,
            color = design.palette.textPrimary,
            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
        )
    }
}

/** Labels are cut as capitals only where the identity sets them so. */
@Composable
private fun caps(text: String): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) text.uppercase() else text

private data class PlaceParts(val locality: String?, val region: String?)

/**
 * "Lourdes, Altos Pirineos, Francia" is a locality plus the country that belongs
 * in a row's trailing slot. A place named in one part keeps the whole line, so
 * the country is never printed twice.
 */
private fun placeParts(place: String): PlaceParts {
    val cut = place.lastIndexOf(',')
    if (cut <= 0) return PlaceParts(place.trim().ifEmpty { null }, null)
    return PlaceParts(
        locality = place.take(cut).trim().ifEmpty { null },
        region = place.substring(cut + 1).trim().ifEmpty { null }
    )
}

/**
 * No kit insets its own segmented control, so the gutter is the caller's to
 * give. Vatican is the exception: its strip is a ruled band whose hairline runs
 * the full width of the leaf, and inset it stops meeting the edges.
 */
@Composable
private fun segmentedTabsInset(): Dp {
    val design = LocalAppDesign.current
    return if (design.language == DesignLanguage.VATICAN) 0.dp else design.spacing.gutter
}
