package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.CatholicPilgrimageSanctuary
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.models.SpiritualHabitItem
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppMetricTile
import com.example.ui.components.TextFormat
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** M3 minimum touch target (AAA): no interactive element on this screen is smaller. */
private val MinTouchTarget = 48.dp

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
        // The daily habits live in Habits & Virtues; here, only the year and
        // the places.
        currentLanguage.pick("Calendario", "Calendar", "Calendário"),
        currentLanguage.pick("Santuarios", "Shrines", "Santuários")
    )
    val tabsInset = segmentedTabsInset()

    // The screen's one large title, then the selector; both scroll away with
    // the pane. Each pane opens on a section header, never a second title.
    val header: LazyListScope.() -> Unit = {
        item(key = "pilgrimage_title") {
            // Photographic hero (votive candles) with fade; title rides the fade.
            PhotoHeader(
                title = currentLanguage.pick(
                    "Calendario y Santuarios",
                    "Calendar & Shrines",
                    "Calendário e Santuários"
                ),
                asset = "heroes/votive_candles.jpg"
            )
        }
        item(key = "pilgrimage_tabs") {
            AppSegmentedTabs(
                options = tabs,
                selectedIndex = selectedMainTab,
                onSelect = { selectedMainTab = it },
                modifier = Modifier
                    .heightIn(min = MinTouchTarget)
                    .padding(horizontal = tabsInset)
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
                    subtitle = null
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
    // One filled M3 card per celebration (not the kit's single hero banner):
    // a tonal date badge, the rank as eyebrow, then celebration, precept and
    // Gospel. All surfaces come from the palette, so the card inverts cleanly
    // in dark mode. The liturgical colour is data, so it is kept as a small
    // decorative dot beside the rank — never as a text ground, which would
    // need the banner kit's dark-mode harmonisation to stay legible.
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val gospel = day.gospelVerseRef.takeIf { it.isNotBlank() }?.let {
        currentLanguage.pick("Evangelio", "Gospel", "Evangelho") + " · " + TextFormat.ranges(it)
    }
    val precept = day.fastingRule?.get(currentLanguage)?.takeIf { it.isNotBlank() }
    AppCard(
        modifier = Modifier
            .padding(horizontal = design.spacing.gutter)
            .testTag("calendar_day_${day.monthName}_${day.dayOfMonth}")
    ) {
        Row(verticalAlignment = Alignment.Top) {
            // Date badge: accentMuted is the identity's tinted surface in
            // both modes, so the numeral keeps its contrast without a
            // hard-coded tone. Square in Vatican, rounded in M3 — the shape
            // token, not a literal.
            Box(
                modifier = Modifier
                    .size(MinTouchTarget)
                    .clip(design.shapes.thumbnail)
                    .background(palette.accentMuted),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day.dayOfMonth.toString(),
                    style = styles.rowTitle,
                    color = palette.textPrimary,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Column(Modifier.weight(1f).widthIn(max = design.spacing.readingMaxWidth)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(design.shapes.badge)
                            .background(Color(day.liturgicalColorHex))
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap / 2))
                    Text(
                        text = caps(day.rank.get(currentLanguage)),
                        style = styles.sectionEyebrow,
                        color = palette.textSecondary
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                Text(
                    text = day.celebrationTitle.get(currentLanguage),
                    style = styles.rowTitle,
                    color = palette.textPrimary
                )
                Text(
                    text = currentLanguage.pick(
                        "${day.dayOfMonth} de ${day.monthName.lowercase()}",
                        "${day.monthName} ${day.dayOfMonth}",
                        "${day.dayOfMonth} de ${day.monthName.lowercase()}"
                    ),
                    style = styles.rowSubtitle,
                    color = palette.textSecondary
                )
                precept?.let {
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = it,
                        style = styles.rowSubtitle,
                        color = palette.textSecondary
                    )
                }
                gospel?.let {
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = it,
                        style = styles.rowSubtitle,
                        color = palette.textSecondary
                    )
                }
            }
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
                    icon = Icons.Outlined.Checklist
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
                    icon = Icons.Outlined.TaskAlt,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("habit_progress_tile")
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                // Progress in bar form as well as the tile's count: colour and
                // numerals alone are invisible to a reader who cannot see the
                // tile, so the bar carries the same $completed/$total text as
                // its stateDescription. Track/indicator are palette tokens, so
                // the bar inverts with dark mode.
                val progressLabel = currentLanguage.pick(
                    "$completedCount de ${habits.size} cumplidos hoy",
                    "$completedCount of ${habits.size} kept today",
                    "$completedCount de ${habits.size} cumpridos hoje"
                )
                LinearProgressIndicator(
                    progress = {
                        if (habits.isEmpty()) 0f
                        else (completedCount.toFloat() / habits.size).coerceIn(0f, 1f)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .height(4.dp)
                        .semantics { stateDescription = progressLabel },
                    color = design.palette.accent,
                    trackColor = design.palette.selectionTint
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
            .heightIn(min = MinTouchTarget)
            .padding(horizontal = design.spacing.gutter)
            .semantics {
                stateDescription = if (completed) {
                    currentLanguage.pick("Cumplido hoy", "Kept today", "Cumprido hoje")
                } else {
                    currentLanguage.pick("Pendiente", "Not kept yet", "Pendente")
                }
            }
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
                tint = if (completed) design.palette.textPrimary else design.palette.textTertiary,
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

            // Candle-prayer plate: the shrine pilgrimage photo inside the
            // shrines pane, clipped to the 20dp card radius with a fade.
            item(key = "pilgrimage_shrine_plate") {
                val plateBitmap = rememberHeroBitmap("heroes/candle_prayer.jpg")
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IveGutter)
                        .height(132.dp)
                        .clip(design.shapes.card)
                        .background(design.palette.selectionTint)
                        .testTag("pilgrimage_shrine_plate")
                ) {
                    if (plateBitmap != null) {
                        Image(
                            bitmap = plateBitmap,
                            contentDescription = currentLanguage.pick("Velas de oración", "Prayer candles", "Velas de oração"),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(design.shapes.card)
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        design.palette.canvas.copy(alpha = 0.85f)
                                    )
                                )
                            )
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }

            itemsIndexed(sanctuaries, key = { _, shrine -> shrine.id }) { index, shrine ->
                if (index > 0) Spacer(Modifier.height(design.spacing.sectionGap))
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
    // One filled M3 card per shrine: header, origin, relics and indulgence
    // apparatus, then the map action — instead of a list row plus loose text
    // plus a button floating under it. Palette tokens throughout, so the card
    // inverts in dark mode; the header row and the button are both at least
    // the 48dp minimum.
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val place = placeParts(shrine.cityCountry.get(currentLanguage))

    AppCard(
        modifier = Modifier
            .padding(horizontal = design.spacing.gutter)
            .testTag("shrine_card_${shrine.id}")
    ) {
        Row(
            modifier = Modifier.heightIn(min = MinTouchTarget),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Monochrome: plain ink glyph, not accent-washed.
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = null,
                tint = palette.textPrimary,
                modifier = Modifier.size(design.decor.rowIconSize)
            )
            Spacer(Modifier.width(design.spacing.iconLabelGap))
            Column(Modifier.weight(1f)) {
                Text(
                    text = shrine.name.get(currentLanguage),
                    style = styles.rowTitle,
                    color = palette.textPrimary
                )
                place.locality?.let {
                    Text(
                        text = it,
                        style = styles.rowSubtitle,
                        color = palette.textSecondary
                    )
                }
            }
            place.region?.let {
                Spacer(Modifier.width(design.spacing.iconLabelGap))
                Text(
                    text = it,
                    style = styles.rowSubtitle,
                    color = palette.textSecondary,
                    textAlign = TextAlign.End
                )
            }
        }

        Spacer(Modifier.height(design.spacing.cardGap))
        Text(
            text = shrine.historicalOrigin.get(currentLanguage),
            style = styles.scripture,
            color = palette.textPrimary,
            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
        )

        Spacer(Modifier.height(design.spacing.cardGap))
        Apparatus(
            label = currentLanguage.pick("Reliquias", "Relics", "Relíquias"),
            text = shrine.relicsAndPatron.get(currentLanguage)
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Apparatus(
            label = currentLanguage.pick(
                "Indulgencia Plenaria y Gracia",
                "Plenary Indulgence & Grace",
                "Indulgência Plenária e Graça"
            ),
            text = shrine.plenaryIndulgenceNote.get(currentLanguage)
        )

        Spacer(Modifier.height(design.spacing.cardGap))
        AppDivider()
        Spacer(Modifier.height(design.spacing.cardGap))

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
                .heightIn(min = MinTouchTarget)
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
    return design.spacing.gutter
}

// ------------------------------------------------------ bundled hero photos

/** Photographic hero band height (Prayers/Doctrine idiom). */
private val PhotoBandHeight = 220.dp

/** Bundled hero photos are decoded at most this many pixels on a side. */
private const val HeroMaxDimension = 1080

/** Ive screen gutter: the identity token already resolves to 20dp. */
private val IveGutter = 20.dp

/**
 * Decodes a bundled assets/heroes photo once per assetPath, sampled down
 * to at most HeroMaxDimension px on either side. Returns null when the
 * asset is missing or unreadable; callers keep their tonal ground.
 */
@Composable
private fun rememberHeroBitmap(assetPath: String): ImageBitmap? {
    val context = LocalContext.current
    return remember(assetPath) {
        try {
            val bytes = context.assets.open(assetPath).use { it.readBytes() }
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size, bounds)
            if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
                null
            } else {
                var sample = 1
                while (
                    bounds.outWidth / sample > HeroMaxDimension ||
                    bounds.outHeight / sample > HeroMaxDimension
                ) {
                    sample *= 2
                }
                val options = BitmapFactory.Options().apply { inSampleSize = sample }
                BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)?.asImageBitmap()
            }
        } catch (_: Exception) {
            null
        }
    }
}

/**
 * Photographic hero with fade: a full-bleed bundled photo band that melts
 * into the canvas at its foot, with the serene screen title overlaid on the
 * fade so photo and word share one block (Prayers/Home idiom). Replaces
 * the vector header art for every identity: the photo reads in Ive and Apple.
 */
@Composable
private fun PhotoHeader(title: String, asset: String) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val bitmap = rememberHeroBitmap(asset)
    Box(
        Modifier
            .fillMaxWidth()
            .height(PhotoBandHeight)
            .background(palette.canvas)
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("screen_header_illustration")
            )
        } else {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(palette.selectionTint)
                    .testTag("screen_header_illustration")
            )
        }
        // Fade: clear over the upper photo, canvas by the foot of the
        // band, so the hero dissolves into the page.
        Box(
            Modifier
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
        // Light scrim behind the title zone: the title stays
        // palette.textPrimary, so the photo is lightened to the paper
        // canvas under it rather than darkened.
        Box(
            Modifier
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
        // Title overlaid at BottomStart with gutter + 24dp vertical
        // padding, Home-style.
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
