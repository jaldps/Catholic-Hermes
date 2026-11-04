package com.example.ui.screens

import androidx.compose.runtime.remember
import androidx.compose.runtime.produceState
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.news.VaticanGospelFetcher
import com.example.data.news.ReadingsResult
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.outlined.Checklist
import com.example.ui.design.DevotionalGlyphs
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.DailyMassReading
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.models.SaintOfTheDay
import com.example.data.models.VerseOfTheDay
import com.example.data.repository.PilgrimageAndHabitsData
import com.example.data.repository.BibleData
import com.example.data.prefs.ReadingPosition
import com.example.ui.design.AppCard
import com.example.ui.design.AppDivider
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryButton
import com.example.ui.components.TextFormat
import com.example.ui.components.IllustratedScreenHeader
import com.example.ui.components.SacredSubject
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * The app's front door: today's liturgical day, the Mass of the day, the verse
 * and saint of the day, the daily routine and the gateways to the rest of the
 * app — all of it set in whichever identity is in force.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    dailyReading: DailyMassReading,
    verseOfTheDay: VerseOfTheDay,
    saintOfTheDay: SaintOfTheDay,
    currentLanguage: AppLanguage,
    onOpenLanguageSelector: () -> Unit,
    onNavigateToBible: () -> Unit,
    onNavigateToMissal: () -> Unit = onNavigateToBible,
    onNavigateToRosary: () -> Unit,
    onNavigateToPrayers: () -> Unit,
    onNavigateToDoctrine: () -> Unit,
    onNavigateToSpiritualLife: () -> Unit,
    onNavigateToQuiz: () -> Unit = onNavigateToSpiritualLife,
    onNavigateToCatechism: () -> Unit = {},
    onNavigateToPilgrimage: () -> Unit = {},
    onNavigateToHabits: () -> Unit = {},
    liturgicalDay: LiturgicalCalendarDay = PilgrimageAndHabitsData.upcomingLiturgicalDays.first(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    // Hoisted out of the list so the disclosure survives being scrolled off.
    var meditationExpanded by rememberSaveable { mutableStateOf(false) }

    fun strings(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    // Both of these are empty on a ferial weekday the bundled lectionary does
    // not carry, so the day's gospel line and its disclosure appear only when
    // there is something behind them.
    val meditation = liturgicalDay.missalMeditation?.get(currentLanguage)
        ?.takeIf { it.isNotBlank() }
    val resumeReading = remember(currentLanguage) {
        ReadingPosition.load(context)?.let { position ->
            BibleData.catholicCanonBooks.firstOrNull { it.id == position.bookId }
                ?.let { "${it.name.get(currentLanguage)} ${position.chapter}" }
        }
    }

    // Today's readings from the Vatican's own feed, the same source the Missal
    // tab uses; the bundled lectionary is the offline fallback.
    val isoToday = remember {
        val d = LiturgicalCalendar.today()
        "%04d-%02d-%02d".format(d.year, d.month, d.day)
    }
    val vaticanResult by produceState<ReadingsResult?>(null, currentLanguage, isoToday) {
        value = VaticanGospelFetcher().today(currentLanguage, isoToday)
    }
    val vatican = (vaticanResult as? ReadingsResult.Success)?.readings

    val gospel = vatican?.sections?.lastOrNull()
    val gospelReference = gospel?.citation?.takeIf { it.isNotBlank() } ?: dailyReading.gospelRef
    val gospelExcerpt = gospel?.text?.takeIf { it.isNotBlank() }
        ?: dailyReading.gospelText.get(currentLanguage).takeIf { it.isNotBlank() }
    val gospelPreview = remember(gospelExcerpt) {
        gospelExcerpt?.trim()?.let { text ->
            val end = Regex("""[.!?](?=\s|$)""").find(text)?.range?.last
            if (end != null) text.take(end + 1) else text
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen")
    ) {
        item {
            // Extra end inset on the header so the decorative SacredArt
            // frame (and its glow) reads inside the screen edge instead of
            // clipped against it; title/subtitle and art are untouched.
            IllustratedScreenHeader(
                title = strings("Hoy", "Today", "Hoje"),
                subject = SacredSubject.DAY,
                subtitle = strings(
                    "${liturgicalDay.dayOfMonth} de ${liturgicalDay.monthName.lowercase()}",
                    "${liturgicalDay.monthName} ${liturgicalDay.dayOfMonth}",
                    "${liturgicalDay.dayOfMonth} de ${liturgicalDay.monthName.lowercase()}"
                ),
                modifier = Modifier.padding(end = design.spacing.cardGap)
            )
        }

        // The celebration and any obligation, with its liturgical color.
        item {
            LiturgicalSeasonBanner(
                title = liturgicalDay.seasonName.get(currentLanguage),
                body = listOfNotNull(
                    liturgicalDay.celebrationTitle.get(currentLanguage),
                    liturgicalDay.fastingRule?.get(currentLanguage)?.takeIf { it.isNotBlank() }
                ).joinToString("\n"),
                accentColorHex = liturgicalDay.liturgicalColorHex,
                modifier = Modifier.testTag("liturgical_calendar_widget")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // A complete reading preview and one route to the full Mass.
        item {
            AppCard(
                modifier = Modifier.padding(horizontal = design.spacing.gutter)
                    .testTag("daily_mass_card")
            ) {
                CardHeading(
                    label = strings("Misa de hoy", "Today's Mass", "Missa de hoje"),
                    icon = Icons.Default.AutoStories
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                if (gospelPreview != null) {
                    if (gospelReference.isNotBlank()) {
                        Text(
                            text = TextFormat.ranges(gospelReference),
                            style = styles.sectionEyebrow,
                            color = palette.accent
                        )
                        Spacer(Modifier.height(design.spacing.cardGap / 2))
                    }
                    Text(
                        text = "«$gospelPreview»",
                        style = styles.scripture,
                        color = palette.textPrimary,
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )
                } else if (vaticanResult == null) {
                    ReadingsPlaceholder(strings("Cargando lecturas", "Loading readings", "A carregar leituras"))
                } else {
                    Text(
                        text = strings("Lecturas no disponibles sin conexión.", "Readings unavailable offline.", "Leituras indisponíveis offline."),
                        style = styles.rowSubtitle,
                        color = palette.textSecondary
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
                // The hero action: one filled primary button to the full Mass.
                AppPrimaryButton(
                    label = strings("Leer Misa", "Read Mass", "Ler Missa"),
                    onClick = onNavigateToMissal,
                    leadingIcon = Icons.Default.AutoStories,
                    modifier = Modifier.testTag("read_mass_full_button")
                )
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // Resume the reader's place or open today's reflection.
        if (meditation != null || resumeReading != null) item {
            AppListGroup {
                if (resumeReading != null) {
                    AppListRow(
                        title = strings("Seguir leyendo", "Continue reading", "Continuar a ler"),
                        subtitle = resumeReading,
                        leadingIcon = Icons.Default.AutoStories,
                        onClick = onNavigateToBible,
                        modifier = Modifier.testTag("home_continue_reading")
                    )
                }
                if (meditation != null) {
                    AppListRow(
                        title = strings("Meditación", "Reflection", "Meditação"),
                        leadingIcon = Icons.Default.MenuBook,
                        onClick = { meditationExpanded = !meditationExpanded },
                        modifier = Modifier.testTag("home_continue_row").semantics {
                            stateDescription = if (meditationExpanded) strings("Expandida", "Expanded", "Expandida")
                                else strings("Contraída", "Collapsed", "Recolhida")
                        }
                    )
                }
            }
            AnimatedVisibility(
                visible = meditationExpanded && meditation != null,
                enter = expandVertically(
                    tween(design.motion.mediumMillis, easing = design.motion.standardEasing)
                ) + fadeIn(tween(design.motion.fastMillis)),
                exit = shrinkVertically(
                    tween(design.motion.fastMillis, easing = design.motion.standardEasing)
                ) + fadeOut(tween(design.motion.fastMillis))
            ) {
                AppCard(
                    modifier = Modifier
                        .padding(horizontal = design.spacing.gutter)
                        .padding(top = design.spacing.cardGap)
                ) {
                    Text(
                        text = meditation.orEmpty(),
                        style = styles.scripture,
                        color = palette.textPrimary,
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )
                }
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // Verse of the day: the screen's hero pull quote, directly after the
        // reading hero so the devotional core reads in one arc.
        item {
            AppCard(
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("verse_of_the_day_card")
            ) {
                CardHeading(
                    label = strings("Versículo del Día", "Verse of the Day", "Versículo do Dia"),
                    icon = Icons.Default.FormatQuote,
                    trailing = verseOfTheDay.reference
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = verseOfTheDay.text.get(currentLanguage),
                    style = styles.quote,
                    color = palette.textPrimary,
                    modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                AppDivider()
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = verseOfTheDay.spiritualChallenge.get(currentLanguage),
                    style = styles.rowSubtitle,
                    color = palette.textSecondary
                )
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // Saint of the day.
        item {
            AppCard(
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("saint_of_the_day_card")
            ) {
                CardHeading(
                    label = strings("Santo del Día", "Saint of the Day", "Santo do Dia"),
                    trailing = saintOfTheDay.feastDate
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = saintOfTheDay.name.get(currentLanguage),
                    style = styles.sectionTitle,
                    color = palette.textPrimary
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                Text(
                    text = saintOfTheDay.title.get(currentLanguage),
                    style = styles.rowSubtitle,
                    color = palette.textSecondary
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = saintOfTheDay.biography.get(currentLanguage),
                    style = styles.rowSubtitle,
                    color = palette.textPrimary,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                AppDivider()
                Spacer(Modifier.height(design.spacing.cardGap))
                MarginalQuote(
                    text = saintOfTheDay.iconicQuote.get(currentLanguage),
                    attribution = saintOfTheDay.name.get(currentLanguage)
                )
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // 4. The routine: the hours of the day, closed by the rule of life,
        // so the practices read as one group rather than a stray card.
        item {
            AppSectionHeader(
                title = strings(
                    "Oración diaria",
                    "Daily prayer",
                    "Oração diária"
                )
            )
        }

        item {
            AppListGroup {
                AppListRow(
                    title = strings("Laudes", "Morning Prayer", "Laudes"),
                    leadingIcon = Icons.Default.LightMode,
                    trailingText = TextFormat.clock(6, 0, currentLanguage),
                    onClick = onNavigateToPrayers
                )
                AppListRow(
                    title = strings("El Ángelus", "The Angelus", "O Angelus"),
                    leadingIcon = Icons.Default.WbTwilight,
                    trailingText = TextFormat.clock(12, 0, currentLanguage),
                    onClick = onNavigateToPrayers
                )
                AppListRow(
                    title = strings("Santo Rosario", "Holy Rosary", "Santo Terço"),
                    leadingIcon = DevotionalGlyphs.rosaryOutlined,
                    trailingText = TextFormat.clock(18, 0, currentLanguage),
                    onClick = onNavigateToRosary
                )
                AppListRow(
                    title = strings("Completas", "Night Prayer", "Completas"),
                    leadingIcon = Icons.Default.NightsStay,
                    trailingText = TextFormat.clock(21, 0, currentLanguage),
                    onClick = onNavigateToPrayers
                )
                AppListRow(
                    title = strings("Regla de vida", "Rule of life", "Regra de vida"),
                    leadingIcon = Icons.Outlined.Checklist,
                    onClick = onNavigateToHabits,
                    modifier = Modifier.testTag("home_habit_tracker_card")
                )
            }
            Spacer(Modifier.height(design.spacing.sectionGap / 2))
        }

        // Nearby parishes, straight out to the device's map app.
        item {
            val openNearbyChurches = {
                runCatching {
                    context.startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=parroquia+catolica"))
                    )
                }
                Unit
            }
            AppListGroup {
                AppListRow(
                    title = strings("Iglesias cercanas", "Nearby churches", "Igrejas próximas"),
                    leadingIcon = Icons.Default.NearMe,
                    onClick = openNearbyChurches,
                    modifier = Modifier.testTag("home_nearby_churches_banner")
                )
            }
        }

        item {
            AppSectionHeader(
                title = strings("Explorar la Fe", "Explore the Faith", "Explorar a Fé")
            )
        }

        // Shortcuts grid: tonal tiles with a 48dp+ target instead of a plain
        // list, so the gateways to the rest of the app read at a glance.
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter),
                verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
                ) {
                    ShortcutTile(
                        label = strings("Directorio Iglesias", "Church Directory", "Diretório Igrejas"),
                        icon = Icons.Default.Church,
                        onClick = onNavigateToDoctrine,
                        modifier = Modifier.weight(1f)
                    )
                    ShortcutTile(
                        label = strings("Trivia Católica", "Catholic Trivia", "Quiz Católico"),
                        icon = Icons.Default.Star,
                        onClick = onNavigateToQuiz,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
                ) {
                    ShortcutTile(
                        label = strings("Catecismo (CCC)", "Catechism (CCC)", "Catecismo (CIC)"),
                        icon = Icons.Default.MenuBook,
                        onClick = onNavigateToCatechism,
                        modifier = Modifier.weight(1f)
                    )
                    ShortcutTile(
                        label = strings("Santuarios y Fe", "Shrines & Pilgrims", "Santuários e Fé"),
                        icon = Icons.Default.Place,
                        onClick = onNavigateToPilgrimage,
                        modifier = Modifier.weight(1f)
                    )
                }
                ShortcutTile(
                    label = strings("Idioma", "Language", "Idioma"),
                    icon = Icons.Default.Language,
                    onClick = onOpenLanguageSelector,
                    trailing = "${currentLanguage.flag} ${currentLanguage.code.uppercase()}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("widget_lang_button")
                )
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/**
 * The readings card while the day's Mass is on its way: a fixed-height block in
 * the shape of what is coming (the Gospel's eyebrow and four lines of text, then
 * two citations), so the card keeps its size and the page does not jump. Every
 * bar is the same height and width and sits on a uniform gap, in the subtle
 * `surfaceContainerHighest` tone, so it reads as text lines rather than a
 * staircase of shimmering blocks.
 */
@Composable
private fun ReadingsPlaceholder(description: String) {
    val design = LocalAppDesign.current
    val barColor = MaterialTheme.colorScheme.surfaceContainerHighest
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(156.dp)
            .semantics { contentDescription = description }
            .testTag("home_readings_placeholder"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(7) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .clip(design.shapes.chip)
                    .background(barColor)
            )
        }
    }
}

/**
 * The liturgical season banner: the seasonal color stays on the container (a
 * low-alpha tint over `surfaceContainer`) and the dot, while the lettering is
 * set in `onSurface` / `onSurfaceVariant` ink, so "Ordinary Time" and the
 * celebration read at AAA contrast on the pale tint instead of muted seasonal
 * green. Inner padding is the `cardPadding` token, the same rhythm
 * `AppCard` gives the verse card.
 */
@Composable
private fun LiturgicalSeasonBanner(
    title: String,
    body: String?,
    accentColorHex: Long?,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val scheme = MaterialTheme.colorScheme
    val liturgicalColor = accentColorHex?.let { Color(it) } ?: scheme.primary
    val container = liturgicalColor.copy(alpha = 0.14f).compositeOver(scheme.surfaceContainer)
    val headline = body?.substringBefore('\n')?.takeIf { it.isNotBlank() }
    val supporting = body?.substringAfter('\n', "")?.takeIf { it.isNotBlank() }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter),
        shape = design.shapes.dialog,
        colors = CardDefaults.cardColors(containerColor = container),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(design.spacing.cardPadding),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .size(8.dp)
                    .clip(design.shapes.badge)
                    .background(liturgicalColor)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = if (headline == null) MaterialTheme.typography.titleMedium else MaterialTheme.typography.labelLarge,
                    color = scheme.onSurface
                )
                headline?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium),
                        color = scheme.onSurface
                    )
                }
                supporting?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = scheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

/**
 * Eyebrow line that opens a card, with an optional trailing value. Kept out of
 * [AppSectionHeader] because that component owns the screen gutter and would
 * inset itself a second time inside a card.
 */
@Composable
private fun CardHeading(
    label: String,
    icon: ImageVector? = null,
    trailing: String? = null,
    trailingColor: Color? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null && design.language != DesignLanguage.VATICAN) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = design.palette.accent,
                modifier = Modifier.size(design.decor.rowIconSize * 0.8f)
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = label.caps(),
            style = styles.sectionEyebrow,
            color = design.palette.textSecondary,
            modifier = Modifier.weight(1f)
        )
        if (trailing != null) {
            Spacer(Modifier.width(12.dp))
            Text(
                text = trailing.caps(),
                style = styles.sectionEyebrow,
                color = trailingColor ?: design.palette.textTertiary,
                maxLines = 1
            )
        }
    }
}

/**
 * One tonal shortcut tile in the Explore grid. The icon sits in a badge
 * shaped from identity tokens (a circle in Material, square in Vatican),
 * the label wraps to two lines, and the whole tile is a 72dp+ button so
 * the touch target never drops below M3's minimum.
 */
@Composable
private fun ShortcutTile(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailing: String? = null
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    AppCard(
        modifier = modifier.heightIn(min = 72.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(design.decor.rowIconSize * 1.6f)
                    .clip(design.shapes.badge)
                    .background(palette.accentMuted),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = palette.accent,
                    modifier = Modifier.size(design.decor.rowIconSize * 0.8f)
                )
            }
            Spacer(Modifier.width(design.spacing.cardGap))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    style = styles.rowTitle,
                    color = palette.textPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (trailing != null) {
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = trailing,
                        style = styles.rowSubtitle,
                        color = palette.textSecondary,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

/**
 * A quote hung off a rule in the margin. The kit has no pull-quote component,
 * so the rule is drawn from tokens and the voice comes from `styles.quote`,
 * which is a different face in every identity.
 */
@Composable
private fun MarginalQuote(text: String, attribution: String? = null) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Box(
            Modifier
                .width(2.dp)
                .fillMaxHeight()
                .background(design.palette.accent)
        )
        Column(
            Modifier
                .weight(1f)
                .padding(start = design.spacing.iconLabelGap)
                .widthIn(max = design.spacing.readingMaxWidth)
        ) {
            Text(text = text, style = styles.quote, color = design.palette.textPrimary)
            if (attribution != null) {
                Spacer(Modifier.height(10.dp))
                Text(
                    text = attribution.caps(),
                    style = styles.sectionEyebrow,
                    color = design.palette.textSecondary
                )
            }
        }
    }
}

/**
 * A badge stating one fact about the liturgical day. The kit's chip is a filter
 * control and demands a click; these are read-only, so they are built from the
 * identity's chip shape, its eyebrow style and a tint.
 */
/** Tracked capitals where the identity sets its labels that way; as written otherwise. */
@Composable
@ReadOnlyComposable
private fun String.caps(): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) uppercase() else this
