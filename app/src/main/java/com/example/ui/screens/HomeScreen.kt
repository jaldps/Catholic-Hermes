package com.example.ui.screens

import androidx.compose.runtime.remember
import androidx.compose.runtime.produceState
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.news.VaticanGospelFetcher
import com.example.data.news.ReadingsResult
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Checklist
import com.example.ui.design.DevotionalGlyphs
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.models.ApologeticsQuestion
import com.example.data.models.AppLanguage
import com.example.data.models.DailyMassReading
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.models.SaintOfTheDay
import com.example.data.models.VerseOfTheDay
import com.example.data.local.SanctaFidesDao
import com.example.data.local.SinOvercomeLogEntity
import com.example.data.repository.PilgrimageAndHabitsData
import com.example.data.repository.BibleData
import com.example.data.prefs.ReadingPosition
import com.example.ui.design.AppCard
import com.example.ui.design.AppDivider
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppSecondaryButton
import com.example.ui.components.TextFormat
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
    onNavigateToMore: () -> Unit = {},
    onOpenAppearance: () -> Unit = {},
    onOpenFontPicker: () -> Unit = {},
    // Pregunta del día data + entry point. MainApp wiring (follow-up, out of
    // scope for this change): pass repository.catholicQuestions and
    // onOpenQuestions = { go(AppDestination.QUESTIONS) } so "Leer respuesta"
    // opens the Questions tab (DoctrineAndHistoryScreen, initialTopTab = 3).
    // Defaults keep previews/screenshot callers compiling; the card renders
    // only when the list is non-empty.
    catholicQuestions: List<ApologeticsQuestion> = emptyList(),
    onOpenQuestions: () -> Unit = {},
    // Nullable so previews/screenshot callers that pass no database keep
    // compiling; the fidelity card is the only thing gated on it.
    dao: SanctaFidesDao? = null,
    liturgicalDay: LiturgicalCalendarDay = PilgrimageAndHabitsData.upcomingLiturgicalDays.first(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    // Photographic heroes (assets/heroes/), decoded once off the composition
    // path. Hoisted unconditionally to the top of HomeScreen — never inside
    // a LazyColumn item or a conditional branch (slot-table crash). Photo
    // for both identities: no vector gate, the same hero reads in Ive and
    // Apple. Null (asset missing / decode failure in a test host) falls
    // back to the canvas gradient band, keeping the header tag present.
    val heroPhoto = remember(context) {
        decodeSampledBitmap(context, "heroes/cathedral_dome.jpg", maxWidthPx = 1080)
    }
    val bannerPhoto = remember(context) {
        decodeSampledBitmap(context, "heroes/mary_statue.jpg", maxWidthPx = 1080)
    }

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
    val firstSection = vatican?.sections?.firstOrNull()
    val gospelReference = gospel?.citation?.takeIf { it.isNotBlank() } ?: dailyReading.gospelRef
    val gospelExcerpt = gospel?.text?.takeIf { it.isNotBlank() }
        ?: dailyReading.gospelText.get(currentLanguage).takeIf { it.isNotBlank() }
    val gospelPreview = remember(gospelExcerpt, currentLanguage) {
        gospelExcerpt?.let { normalizeExcerptQuotes(firstSentenceOf(it), currentLanguage) }
    }
    // First reading from the same missal data the "Read Mass" destination
    // opens (dailyReading, with the Vatican feed's first section preferred
    // when it has landed), so the card is populated offline and immediately
    // instead of showing a fixed-height skeleton.
    val firstReadingReference = firstSection?.citation?.takeIf { it.isNotBlank() }
        ?: dailyReading.firstReadingRef
    val firstReadingExcerpt = firstSection?.text?.takeIf { it.isNotBlank() }
        ?: dailyReading.firstReadingText.get(currentLanguage).takeIf { it.isNotBlank() }
    val firstReadingPreview = remember(firstReadingExcerpt, currentLanguage) {
        firstReadingExcerpt?.let { normalizeExcerptQuotes(firstSentenceOf(it), currentLanguage) }
    }
    val hasReadings = firstReadingPreview != null || gospelPreview != null

    // Pregunta del día: deterministic by date, no stored state — the index is
    // dayOfYear % question count (101 with the full bundled list), so every
    // reader sees the same question on the same day.
    val dailyQuestion = remember(catholicQuestions) {
        if (catholicQuestions.isEmpty()) null
        else catholicQuestions[Calendar.getInstance().get(Calendar.DAY_OF_YEAR) % catholicQuestions.size]
    }
    val dailyAnswerFirstLine = remember(dailyQuestion, currentLanguage) {
        dailyQuestion?.answer?.get(currentLanguage)?.let { firstLineOf(it) }?.takeIf { it.isNotBlank() }
    }
    val shareDailyQuestion: () -> Unit = {
        val q = dailyQuestion
        if (q != null && dailyAnswerFirstLine != null) {
            val text = "${q.question.get(currentLanguage)}\n\n$dailyAnswerFirstLine\n\nCatholic Hermes"
            runCatching {
                context.startActivity(
                    Intent.createChooser(
                        Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, text)
                        },
                        null
                    )
                )
            }
        }
        Unit
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 16.dp)
    ) {
        item {
            // Photographic hero: the cathedral dome fills the full-width
            // ~220dp band at the top of the header area, melting into the
            // paper canvas through a vertical gradient (photo -> canvas)
            // so there is no hard frame edge. Title/date ride overlaid
            // left, low in the faded zone where the canvas blend keeps the
            // ink legible. screen_header_illustration stays on the photo
            // (or its gradient fallback box when decode fails).
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(palette.canvas)
            ) {
                val hero = heroPhoto
                if (hero != null) {
                    Image(
                        bitmap = hero,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("screen_header_illustration"),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        palette.accentMuted,
                                        palette.canvas
                                    )
                                )
                            )
                            .testTag("screen_header_illustration")
                    )
                }
                // Fade: clear over the upper photo, canvas by the foot of
                // the band, so the hero dissolves into the page.
                Box(
                    modifier = Modifier
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
                // Light scrim behind the Hoy/date title zone: the title
                // stays palette.textPrimary, so the photo is lightened to
                // the paper canvas under it rather than darkened. The
                // gradient extends 160dp up the band so the date stays
                // legible over the busy dome photo.
                Box(
                    modifier = Modifier
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
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = strings("Hoy", "Today", "Hoje"),
                            style = styles.screenTitle.copy(fontWeight = FontWeight.W400),
                            color = palette.textPrimary,
                            maxLines = 1
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = strings(
                                "${liturgicalDay.dayOfMonth} de ${liturgicalDay.monthName.lowercase()}",
                                "${liturgicalDay.monthName} ${liturgicalDay.dayOfMonth}",
                                "${liturgicalDay.dayOfMonth} de ${liturgicalDay.monthName.lowercase()}"
                            ),
                            style = styles.screenSubtitle,
                            color = palette.textSecondary,
                            maxLines = 1
                        )
                    }
                }
            }
        }

        // The celebration and any obligation, with its liturgical color.
        // The season pill inside the banner is the only place the season
        // name appears: a celebration that merely repeats it (data feeds
        // do this on uncurated days) is dropped from the body.
        item {
            val seasonName = liturgicalDay.seasonName.get(currentLanguage)
            val celebrationTitle = liturgicalDay.celebrationTitle.get(currentLanguage)
            LiturgicalSeasonBanner(
                title = seasonName,
                body = listOfNotNull(
                    celebrationTitle.takeUnless {
                        it.isBlank() || it.equals(seasonName, ignoreCase = true)
                    },
                    liturgicalDay.fastingRule?.get(currentLanguage)?.takeIf { it.isNotBlank() }
                ).joinToString("\n"),
                accentColorHex = liturgicalDay.liturgicalColorHex,
                photo = bannerPhoto,
                modifier = Modifier.testTag("liturgical_calendar_widget")
            )
            Spacer(Modifier.height(16.dp))
        }

        // A complete reading preview and one route to the full Mass.
        // Frosted hero card: AppCard already paints the translucent
        // groupedSurface at design.shapes.card (20dp in Ive); the soft
        // shadow lifts it off the warm paper canvas.
        item {
            AppCard(
                modifier = Modifier.padding(horizontal = design.spacing.gutter)
                    .frostedCardChrome()
                    .testTag("daily_mass_card")
            ) {
                CardHeading(
                    label = strings("Misa de hoy", "Today's Mass", "Missa de hoje"),
                    icon = Icons.Default.AutoStories
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                if (hasReadings) {
                    if (firstReadingPreview != null) {
                        if (firstReadingReference.isNotBlank()) {
                            Text(
                                text = TextFormat.ranges(firstReadingReference),
                                style = styles.sectionEyebrow,
                                color = palette.accent
                            )
                            Spacer(Modifier.height(design.spacing.cardGap / 2))
                        }
                        Text(
                            text = "«$firstReadingPreview»",
                            style = styles.scripture,
                            color = palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                        if (gospelPreview != null) Spacer(Modifier.height(design.spacing.cardGap))
                    }
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
                    }
                } else {
                    // Offline fallback: the bundled lectionary carries no
                    // entry for most ferial days (the repository leaves those
                    // citations empty rather than inventing them). The
                    // celebration and season already headline the liturgical
                    // banner above and the saint has its own card below, so
                    // this card never repeats them — it shows only what is
                    // unique to the Mass: the citations, or the first line
                    // of today's homily reflection when no citation landed.
                    var showedFallback = false
                    if (firstReadingReference.isNotBlank()) {
                        Text(
                            text = TextFormat.ranges(firstReadingReference),
                            style = styles.sectionEyebrow,
                            color = palette.accent
                        )
                        showedFallback = true
                    }
                    if (gospelReference.isNotBlank()) {
                        if (showedFallback) Spacer(Modifier.height(design.spacing.cardGap / 2))
                        Text(
                            text = TextFormat.ranges(gospelReference),
                            style = styles.sectionEyebrow,
                            color = palette.accent
                        )
                        showedFallback = true
                    }
                    if (!showedFallback) {
                        val homilyFirstLine = dailyReading.homilyReflection.get(currentLanguage)
                            .takeIf { it.isNotBlank() }
                            ?.let { normalizeExcerptQuotes(firstSentenceOf(it), currentLanguage) }
                        if (!homilyFirstLine.isNullOrBlank()) {
                            Text(
                                text = "«$homilyFirstLine»",
                                style = styles.scripture,
                                color = palette.textPrimary,
                                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                            )
                        }
                    }
                }
                Spacer(Modifier.height(design.spacing.cardGap))
                // The hero action: one filled primary button to the full Mass.
                AppPrimaryButton(
                    label = strings("Leer Misa", "Read Mass", "Ler Missa"),
                    onClick = onNavigateToMissal,
                    modifier = Modifier.testTag("read_mass_full_button")
                )
            }
            Spacer(Modifier.height(16.dp))
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
                        .frostedCardChrome()
                ) {
                    Text(
                        text = meditation.orEmpty(),
                        style = styles.scripture,
                        color = palette.textPrimary,
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        // Verse of the day: the screen's hero pull quote, directly after the
        // reading hero so the devotional core reads in one arc.
        item {
            AppCard(
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .frostedCardChrome()
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
            Spacer(Modifier.height(16.dp))
        }

        // Pregunta del día: question + first line of its answer, a route to
        // the full answer in the Questions tab, and a share action. Renders
        // only when MainApp passes the question list (see onOpenQuestions).
        if (dailyQuestion != null && dailyAnswerFirstLine != null) item {
            AppCard(
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .frostedCardChrome()
                    .testTag("home_daily_question")
            ) {
                CardHeading(
                    label = strings("Pregunta del día", "Question of the day", "Pergunta do dia"),
                    icon = Icons.Default.MenuBook
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = dailyQuestion.question.get(currentLanguage),
                    style = styles.rowTitle,
                    color = palette.textPrimary
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                Text(
                    text = dailyAnswerFirstLine,
                    style = styles.rowSubtitle,
                    color = palette.textSecondary,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
                ) {
                    AppPrimaryButton(
                        label = strings("Leer respuesta", "Read answer", "Ler resposta"),
                        onClick = onOpenQuestions,
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .testTag("home_daily_question_read")
                    )
                    AppSecondaryButton(
                        label = strings("Compartir", "Share", "Partilhar"),
                        onClick = shareDailyQuestion,
                        leadingIcon = Icons.Default.Share,
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .testTag("home_daily_question_share")
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        // Saint of the day. Each piece renders exactly once: mapped feeds
        // duplicate fields on uncurated days (title == name, quote == bio),
        // so distinctness is checked before rendering.
        item {
            val rawSaintName = saintOfTheDay.name.get(currentLanguage).trim()
            // The bundled fallback embeds its Spanish date in the name
            // ("Saint of Today (5 de Noviembre)") and the raw feastDate is
            // stored in whatever locale its source used, so neither is
            // shown verbatim in a non-Spanish UI: the inline date is
            // stripped from the name and the trailing date is re-rendered
            // in the current UI language (or dropped when unparseable).
            val saintName = stripInlineFeastDate(rawSaintName, saintOfTheDay.feastDate)
            val feastDateLocalized = localizedFeastDateLabel(saintOfTheDay.feastDate, currentLanguage)
            val saintTitle = saintOfTheDay.title.get(currentLanguage).trim()
            val saintBiography = saintOfTheDay.biography.get(currentLanguage).trim()
            val saintQuote = saintOfTheDay.iconicQuote.get(currentLanguage).trim()
            val showTitle = saintTitle.isNotBlank() && !saintTitle.equals(saintName, ignoreCase = true)
            val showQuote = saintQuote.isNotBlank() &&
                !saintQuote.equals(saintBiography, ignoreCase = true) &&
                !saintQuote.equals(saintName, ignoreCase = true) &&
                !saintQuote.equals(saintTitle, ignoreCase = true)
            AppCard(
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .frostedCardChrome()
                    .testTag("saint_of_the_day_card")
            ) {
                CardHeading(
                    label = strings("Santo del Día", "Saint of the Day", "Santo do Dia"),
                    trailing = feastDateLocalized
                        ?.takeIf { it.isNotBlank() && !saintName.contains(it, ignoreCase = true) }
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = saintName,
                    style = styles.rowTitle,
                    color = palette.textPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (showTitle) {
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = saintTitle,
                        style = styles.rowSubtitle,
                        color = palette.textSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = saintBiography,
                    style = styles.rowSubtitle,
                    color = palette.textPrimary,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                )
                if (showQuote) {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppDivider()
                    Spacer(Modifier.height(design.spacing.cardGap))
                    MarginalQuote(text = saintQuote)
                }
            }
            Spacer(Modifier.height(16.dp))
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
            Spacer(Modifier.height(16.dp))
        }

        // Today's spiritual fidelity: one point per habit kept today, plus a
        // quick victory log. Gated on dao so callers without a database
        // (screenshots, previews) keep the plain Home.
        if (dao != null) item {
            FidelityCard(
                dao = dao,
                currentLanguage = currentLanguage,
                onOpenHabits = onNavigateToHabits
            )
            Spacer(Modifier.height(16.dp))
        }

        // Subtraction: the lone "Nearby churches" row duplicated the
        // Church Directory shortcut below (and left the device map app),
        // so it was cut — Directory is its one home.

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
                // Subtraction: Language / Appearance / Font tiles duplicated
                // More / Settings below (which keeps the same Settings), so
                // they were cut from Home — one settings door, not four.
                // More is no longer a tab: this tile is its entry point, and
                // More keeps every other destination plus the same Settings.
                ShortcutTile(
                    label = strings("Más / Ajustes", "More / Settings", "Mais / Ajustes"),
                    icon = Icons.Default.Settings,
                    onClick = onNavigateToMore,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("home_more_settings")
                )
            }
        }

        // Extra clearance so the last card never hides behind the floating tab bar.
        item { Spacer(Modifier.height(design.spacing.contentBottomGap + 72.dp)) }
    }
}

/**
 * Frosted-card chrome for [AppCard] on Home: a soft shadow plus a 1dp
 * hairline stroke. `AppCard` (AppleKit) already paints the frosted
 * `groupedSurface` at `design.shapes.card` (20dp in Ive), but its fill is
 * drawn *after* the caller's modifier — so a plain `.border()` passed in
 * would be painted over by that fill. `drawWithContent` strokes after the
 * card's own content instead, so the hairline sits on top at the card's
 * real (gutter-inset) bounds. Radius follows the identity's one card
 * system: 20dp in Ive, 12dp elsewhere.
 */
@Composable
private fun Modifier.frostedCardChrome(): Modifier {
    val design = LocalAppDesign.current
    val palette = design.palette
    val radius = if (design.language == DesignLanguage.IVE) 20.dp else 12.dp
    val width = design.depth.cardBorderWidth.coerceAtLeast(1.dp)
    return this
        .shadow(6.dp, design.shapes.card)
        .drawWithContent {
            drawContent()
            drawRoundRect(
                color = palette.hairline,
                topLeft = Offset.Zero,
                size = size,
                cornerRadius = CornerRadius(radius.toPx()),
                style = Stroke(width = width.toPx())
            )
        }
}

/**
 * Decodes an asset JPEG down-sampled so its width does not exceed
 * [maxWidthPx] (power-of-two [BitmapFactory.Options.inSampleSize]), so a
 * ~1.7MB photographic hero set never lands full-resolution on memory-
 * constrained devices. Returns null on any failure (missing asset, host
 * without a real decoder in tests) so callers fall back to a plain
 * gradient band instead of crashing.
 */
private fun decodeSampledBitmap(
    context: Context,
    assetPath: String,
    maxWidthPx: Int = 1080
): ImageBitmap? = try {
    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    context.assets.open(assetPath).use { BitmapFactory.decodeStream(it, null, bounds) }
    if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
        null
    } else {
        var sampleSize = 1
        while (bounds.outWidth / sampleSize > maxWidthPx) sampleSize *= 2
        val options = BitmapFactory.Options().apply { inSampleSize = sampleSize }
        val bitmap: Bitmap? = context.assets.open(assetPath).use {
            BitmapFactory.decodeStream(it, null, options)
        }
        bitmap?.asImageBitmap()
    }
} catch (_: Exception) {
    null
}

/** First sentence of [text]: up to the first sentence-ending punctuation. */
private fun firstSentenceOf(text: String): String {
    val trimmed = text.trim()
    val end = Regex("""[.!?](?=\s|$)""").find(trimmed)?.range?.last
    return if (end != null) trimmed.take(end + 1) else trimmed
}

/**
 * Normalizes quote marks inside a scripture excerpt for display. Spanish
 * (and Portuguese) renderings use « » exclusively, so straight (" ") and
 * curly (“ ”) quotes from the source — e.g. a Lucas gospel passage that
 * mixes them with the « » the card itself adds — are folded to « »,
 * alternating open/close for straight quotes. Other languages keep the
 * source marks. Already-« » passages pass through untouched.
 */
private fun normalizeExcerptQuotes(text: String, language: AppLanguage): String {
    if (language != AppLanguage.ES && language != AppLanguage.PT) return text
    val out = StringBuilder(text.length)
    var openNext = true
    for (ch in text) {
        when (ch) {
            '“' -> out.append('«')
            '”' -> out.append('»')
            '"' -> {
                out.append(if (openNext) '«' else '»')
                openNext = !openNext
            }
            else -> out.append(ch)
        }
    }
    return out.toString()
}

/** Month names as (ES, EN, PT), indexed by [Calendar.MONTH]. */
private val SAINT_MONTH_NAMES = listOf(
    Triple("Enero", "January", "Janeiro"),
    Triple("Febrero", "February", "Fevereiro"),
    Triple("Marzo", "March", "Março"),
    Triple("Abril", "April", "Abril"),
    Triple("Mayo", "May", "Maio"),
    Triple("Junio", "June", "Junho"),
    Triple("Julio", "July", "Julho"),
    Triple("Agosto", "August", "Agosto"),
    Triple("Septiembre", "September", "Setembro"),
    Triple("Octubre", "October", "Outubro"),
    Triple("Noviembre", "November", "Novembro"),
    Triple("Diciembre", "December", "Dezembro")
)

/**
 * Re-renders a stored feast-date string ("5 de Noviembre" / "November 5" /
 * "5 de Novembro") in [language]; returns null when the string carries no
 * day+month — the caller drops the meta instead of showing a mixed-locale
 * date inside a non-Spanish UI.
 */
private fun localizedFeastDateLabel(raw: String, language: AppLanguage): String? {
    val trimmed = raw.trim()
    if (trimmed.isBlank()) return null
    val day = Regex("""\d{1,2}""").find(trimmed)?.value?.toIntOrNull() ?: return null
    val monthIndex = SAINT_MONTH_NAMES.indexOfFirst { (es, en, pt) ->
        trimmed.contains(es, ignoreCase = true) ||
            trimmed.contains(en, ignoreCase = true) ||
            trimmed.contains(pt, ignoreCase = true)
    }
    if (monthIndex < 0) return null
    val (es, en, pt) = SAINT_MONTH_NAMES[monthIndex]
    return when (language) {
        AppLanguage.ES -> "$day de ${es.lowercase()}"
        AppLanguage.EN -> "$en $day"
        AppLanguage.PT -> "$day de ${pt.lowercase()}"
    }
}

/**
 * Removes a trailing parenthetical feast date from a saint name when it
 * embeds [feastDate] ("Saint of Today (5 de Noviembre)") or any digit-bearing
 * parenthetical; parentheticals without a date (roles, titles) are kept.
 */
private fun stripInlineFeastDate(name: String, feastDate: String): String {
    val trimmed = name.trim()
    if (trimmed.isBlank()) return trimmed
    if (feastDate.isNotBlank() && trimmed.contains(feastDate)) {
        val without = trimmed.replace(
            Regex("""\s*\([^)]*${Regex.escape(feastDate)}[^)]*\)"""),
            ""
        ).trim()
        if (without.isNotBlank()) return without
    }
    val dateParenthetical = Regex("""\s*\([^)]*\d[^)]*\)\s*$""")
    if (dateParenthetical.containsMatchIn(trimmed)) {
        val without = dateParenthetical.replace(trimmed, "").trim()
        if (without.isNotBlank()) return without
    }
    return trimmed
}

/**
 * Today's spiritual fidelity: one point per habit completion logged for today
 * (distinct habits kept), the total victories kept in Christ, and a one-tap
 * "I overcame a temptation" that writes to the same victory log the Habits
 * screen reads and confirms inline. The generic TEMPTATION key keeps the
 * quick log from inflating any one catalogued vice's count.
 */
@Composable
private fun FidelityCard(
    dao: SanctaFidesDao,
    currentLanguage: AppLanguage,
    onOpenHabits: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val scope = rememberCoroutineScope()

    fun strings(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    val todayDateStr = remember {
        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }
    val habits by dao.getAllHabits().collectAsState(initial = emptyList())
    val todayCompletions by dao.getCompletionsForDate(todayDateStr).collectAsState(initial = emptyList())
    val totalVictories by dao.getTotalVictoriesCount().collectAsState(initial = 0)
    var victoryLogged by rememberSaveable { mutableStateOf(false) }

    val keptIds = remember(todayCompletions) { todayCompletions.map { it.habitId }.toSet() }
    val points = keptIds.size

    AppCard(
        modifier = Modifier
            .padding(horizontal = design.spacing.gutter)
            .frostedCardChrome()
            .testTag("home_fidelity_card")
    ) {
        CardHeading(
            label = strings("Fidelidad de hoy", "Today's fidelity", "Fidelidade de hoje"),
            icon = Icons.Default.Star,
            trailing = "$points"
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Text(
            text = if (habits.isNotEmpty()) {
                strings(
                    "$points de ${habits.size} prácticas cumplidas hoy",
                    "$points of ${habits.size} practices kept today",
                    "$points de ${habits.size} práticas cumpridas hoje"
                )
            } else {
                strings(
                    "Aún no hay prácticas en tu regla de vida",
                    "No practices in your rule of life yet",
                    "Ainda não há práticas na sua regra de vida"
                )
            },
            style = styles.rowSubtitle,
            color = palette.textSecondary
        )
        if (totalVictories > 0) {
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = strings(
                    "$totalVictories tentaciones vencidas en Cristo",
                    "$totalVictories temptations overcome in Christ",
                    "$totalVictories tentações vencidas em Cristo"
                ),
                style = styles.rowSubtitle,
                color = palette.textSecondary
            )
        }
        Spacer(Modifier.height(design.spacing.cardGap))
        AppPrimaryButton(
            label = strings(
                "Vencí una tentación",
                "I overcame a temptation",
                "Venci uma tentação"
            ),
            onClick = {
                scope.launch {
                    dao.insertSinOvercomeLog(
                        SinOvercomeLogEntity(
                            sinKey = "TEMPTATION",
                            counterVirtue = "",
                            prayerUsed = "HAIL_MARY"
                        )
                    )
                    victoryLogged = true
                }
            },
            leadingIcon = Icons.Default.TaskAlt,
            modifier = Modifier.testTag("home_overcome_temptation_button")
        )
        if (victoryLogged) {
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = strings("¡Victoria registrada!", "Victory recorded!", "Vitória registada!"),
                style = styles.rowSubtitle,
                color = palette.accent,
                modifier = Modifier.testTag("home_victory_confirmation")
            )
        }
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = strings("Ver mi regla de vida", "View my rule of life", "Ver a minha regra de vida"),
            style = styles.sectionEyebrow,
            color = palette.accent,
            modifier = Modifier
                .clickable(onClick = onOpenHabits)
                .testTag("home_fidelity_open_habits")
        )
    }
}

/**
 * The liturgical season banner, in the Ive reference idiom: a frosted card
 * (translucent `raised` at `design.shapes.card`, 20dp in Ive, with a soft
 * shadow over the warm paper) carrying a pill for the liturgical time — a
 * green dot in the season's own color beside the season name — above the
 * celebration in a serene weight-400 title and any obligation below.
 * Lettering stays in opaque primary/secondary ink for AAA contrast.
 * When [photo] is present (mary_statue.jpg) it heads the card as a
 * cropped strip; when null the banner keeps its existing pill/dot art
 * alone — no layout or tag change either way.
 */
@Composable
private fun LiturgicalSeasonBanner(
    title: String,
    body: String?,
    accentColorHex: Long?,
    modifier: Modifier = Modifier,
    photo: ImageBitmap? = null
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val liturgicalColor = accentColorHex?.let { Color(it) } ?: palette.accent
    val headline = body?.substringBefore('\n')?.takeIf { it.isNotBlank() }
    val supporting = body?.substringAfter('\n', "")?.takeIf { it.isNotBlank() }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .shadow(6.dp, design.shapes.card),
        shape = design.shapes.card,
        colors = CardDefaults.cardColors(containerColor = palette.raised),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(
            design.depth.cardBorderWidth.coerceAtLeast(1.dp),
            palette.hairline
        )
    ) {
        Column(
            modifier = Modifier.padding(design.spacing.cardPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Photographic strip (mary statue) heading the banner; the
            // taller 128dp strip with a centre-aligned crop keeps the
            // focal point centred instead of the near-black side panels
            // filling ~60% of the strip; text below stays on the raised card.
            photo?.let { windowPhoto ->
                Image(
                    bitmap = windowPhoto,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .clip(design.shapes.card)
                        .testTag("liturgical_banner_photo"),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            }
            // Liturgical-time pill: dot + season name on a tinted capsule.
            Box(
                modifier = Modifier
                    .clip(design.shapes.chip)
                    .background(palette.accentMuted)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(design.shapes.badge)
                            .background(liturgicalColor)
                    )
                    Text(
                        text = title,
                        style = styles.sectionEyebrow,
                        color = palette.accent,
                        maxLines = 1
                    )
                }
            }
            headline?.let {
                Text(
                    text = it,
                    style = styles.rowTitle.copy(fontWeight = FontWeight.W400),
                    color = palette.textPrimary
                )
            }
            supporting?.let {
                Text(
                    text = it,
                    style = styles.rowSubtitle,
                    color = palette.textSecondary
                )
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
        if (icon != null) {
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
                color = trailingColor ?: design.palette.textSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                // Cap the trailing value (e.g. a saint's feast date) so a
                // long one ellipsizes on one line instead of wrapping or
                // squeezing the heading label out of the row.
                modifier = Modifier.widthIn(max = 144.dp)
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
        modifier = modifier.heightIn(min = 72.dp).frostedCardChrome(),
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

/** First line of a multi-paragraph answer: its first physical line, cut at the first sentence end. */
private fun firstLineOf(text: String): String {
    val line = text.trim().substringBefore('\n').trim()
    val end = Regex("""[.!?](?=\s|$)""").find(line)?.range?.last
    return if (end != null) line.take(end + 1) else line
}
