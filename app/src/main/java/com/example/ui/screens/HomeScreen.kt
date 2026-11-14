package com.example.ui.screens

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.fidelity.FidelityStore
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.ScriptureReference
import com.example.data.local.SanctaFidesDao
import com.example.data.local.SinOvercomeLogEntity
import com.example.data.models.ApologeticsQuestion
import com.example.data.models.AppLanguage
import com.example.data.models.DailyMassReading
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.models.SaintOfTheDay
import com.example.data.models.VerseOfTheDay
import com.example.data.news.ReadingsResult
import com.example.data.news.VaticanGospelFetcher
import com.example.data.prefs.ReadingPosition
import com.example.data.repository.BibleData
import com.example.data.repository.PilgrimageAndHabitsData
import com.example.data.temptation.TemptationStore
import com.example.data.verse.DailyVerseRotation
import com.example.ui.components.TextFormat
import com.example.ui.design.AppCard
import com.example.ui.design.AppCategoryTile
import com.example.ui.design.AppChevron
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppGrid
import com.example.ui.design.AppHeroHeader
import com.example.ui.design.AppHeroImage
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppProgressRing
import com.example.ui.design.AppRoundPlayButton
import com.example.ui.design.AppSeasonCard
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.DevotionalGlyphs
import com.example.ui.design.lumenLiturgicalColor
import com.example.ui.design.rememberFirstAsset
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

/** Space before a section header; the header adds 10dp more, so sections sit 32dp apart. */
private val SectionLead = 22.dp

/** Gap between sibling cards in one cluster. */
private val ClusterGap = 12.dp

/**
 * The app's front door (Hoy): a misted sacred-art hero with the date and
 * "Hoy", the liturgical day riding over its fade, the Mass of the day built
 * around the Word (Gospel in reading type, one primary action), the verse, the
 * prayer of the hour, today's fidelity, the saint, the question and the
 * gateways to the rest of the app.
 */
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
    onNavigateToLaudes: () -> Unit = onNavigateToPrayers,
    onNavigateToAngelus: () -> Unit = onNavigateToPrayers,
    onNavigateToMorningPrayers: () -> Unit = {},
    onNavigateToChaplet: () -> Unit = {},
    onNavigateToNightPrayers: () -> Unit = {},
    onNavigateToDoctrine: () -> Unit,
    onNavigateToSpiritualLife: () -> Unit,
    onNavigateToQuiz: () -> Unit = onNavigateToSpiritualLife,
    onNavigateToCatechism: () -> Unit = {},
    onNavigateToPilgrimage: () -> Unit = {},
    onNavigateToHabits: () -> Unit = {},
    onOpenTemptations: () -> Unit = onNavigateToHabits,
    /** Receives the canonical daily rotation target, never the localized display citation. */
    onOpenDailyVerse: (String, Int, Int) -> Unit = { _, _, _ -> onNavigateToBible() },
    /** Supplied with the same date as [verseOfTheDay] by MainApp. */
    dailyVerseCanonicalReference: String? = null,
    onNavigateToMore: () -> Unit = {},
    onOpenAppearance: () -> Unit = {},
    onOpenFontPicker: () -> Unit = {},
    // Pregunta del día data + entry point: "Leer respuesta" opens the Questions
    // tab at the SPECIFIC question's answer (DoctrineAndHistoryScreen
    // initialQuestionIndex). The card renders only when the list is non-empty.
    catholicQuestions: List<ApologeticsQuestion> = emptyList(),
    onOpenQuestions: (Int) -> Unit = {},
    // Nullable so previews/screenshot callers that pass no database keep
    // compiling; the fidelity card is the only thing gated on it.
    dao: SanctaFidesDao? = null,
    liturgicalDay: LiturgicalCalendarDay = PilgrimageAndHabitsData.upcomingLiturgicalDays.first(),
    onOpenReminders: () -> Unit = {},
    onNavigateToChurches: () -> Unit = onNavigateToDoctrine,
    ttsManager: com.example.data.audio.LiturgicalTtsManager? = null,
    /** "Santuarios" tile; defaults to the calendar destination (MainApp opens the shrines tab). */
    onNavigateToShrines: () -> Unit = onNavigateToPilgrimage,
    onOpenBeginner: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val now = rememberLifecycleClock()
    val journeyStore = remember { BeginnerJourneyStore(context) }
    var welcomed by rememberSaveable { mutableStateOf(journeyStore.welcomed) }
    var showWelcome by rememberSaveable { mutableStateOf(false) }
    if (showWelcome) WelcomeChoices(currentLanguage, onChooseLanguage = onOpenLanguageSelector) { showWelcome = false; welcomed = true }

    fun strings(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    var meditationExpanded by rememberSaveable { mutableStateOf(false) }
    var showMoreToday by rememberSaveable { mutableStateOf(false) }

    val meditation = liturgicalDay.missalMeditation?.get(currentLanguage)?.takeIf { it.isNotBlank() }
    val resumeReading = remember(currentLanguage, now) {
        ReadingPosition.load(context)?.let { position ->
            BibleData.catholicCanonBooks.firstOrNull { it.id == position.bookId }
                ?.let { "${it.name.get(currentLanguage)} ${position.chapter}" }
        }
    }

    // One lectionary source, the same in every theme and offline: the bundled
    // lectionary for the day. Only a day it does not carry (some ferial days)
    // falls back to the Vatican's own feed, the source the Mass tab opens.
    val bundledGospel = dailyReading.gospelText.get(currentLanguage).takeIf { it.isNotBlank() }
    val bundledFirst = dailyReading.firstReadingRef.takeIf { it.isNotBlank() }
    val needsFeed = bundledGospel == null || bundledFirst == null
    val isoToday = now.toLocalDate().toString()
    val vaticanResult by produceState<ReadingsResult?>(null, currentLanguage, isoToday, needsFeed) {
        value = if (needsFeed) VaticanGospelFetcher().today(currentLanguage, isoToday) else null
    }
    val vatican = (vaticanResult as? ReadingsResult.Success)?.readings
    val feedGospel = vatican?.sections?.lastOrNull()
    val feedFirst = vatican?.sections?.firstOrNull()

    val gospelReference = scriptureRef(
        if (bundledGospel != null) dailyReading.gospelRef.takeIf { it.isNotBlank() } ?: feedGospel?.citation.orEmpty()
        else feedGospel?.citation?.takeIf { it.isNotBlank() } ?: dailyReading.gospelRef,
        currentLanguage
    )
    val gospelExcerpt = bundledGospel ?: feedGospel?.text?.takeIf { it.isNotBlank() }
    val gospelPreview = remember(gospelExcerpt, currentLanguage) {
        gospelExcerpt?.let { firstSentenceOf(cleanScripture(it, currentLanguage)) }?.takeIf { it.isNotBlank() }
    }
    val firstReadingReference = scriptureRef(
        bundledFirst ?: feedFirst?.citation?.takeIf { it.isNotBlank() }.orEmpty(),
        currentLanguage
    )

    // Pregunta del día: deterministic by date, so every reader sees the same
    // question on the same day; its index deep-links to THIS answer.
    val dayOfYear = now.dayOfYear
    val dailyQuestionIndex = remember(catholicQuestions, dayOfYear) {
        if (catholicQuestions.isEmpty()) -1 else dayOfYear % catholicQuestions.size
    }
    val dailyQuestion = remember(catholicQuestions, dailyQuestionIndex) {
        if (dailyQuestionIndex < 0) null else catholicQuestions[dailyQuestionIndex]
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

    // Display and reader use the same bundled edition. An online replacement
    // could silently change translations between the card and its deep link.
    val verseToday = now.toLocalDate()
    val verseReferenceId = remember(verseToday, dailyVerseCanonicalReference) {
        dailyVerseCanonicalReference ?: DailyVerseRotation.referenceFor(verseToday)
    }
    val dailyVerseTarget = remember(verseReferenceId) { ScriptureReference.parse(verseReferenceId) }
    val openDailyVerse: () -> Unit = {
        dailyVerseTarget?.let { onOpenDailyVerse(it.bookId, it.chapter, it.verseStart ?: 1) }
    }

    // Time-based daily prayer: the hour decides which prayer is "now" and which
    // is next. 5am is Morning Prayers (before the 6am Laudes); the small hours
    // stay on the traditional night prayers.
    val prayerHour = now.hour
    val currentPrayerSlot = dailyPrayerSlotForHour(prayerHour)
    val nextPrayerSlot = nextDailyPrayerSlot(currentPrayerSlot)

    @Composable
    fun DailyPrayerRow(slot: DailyPrayerSlot, caption: String) {
        val title = when (slot) {
            DailyPrayerSlot.MORNING_PRAYERS ->
                strings("Oraciones de la mañana", "Morning Prayers", "Orações da manhã")
            DailyPrayerSlot.LAUDES ->
                strings("Laudes", "Morning Prayer", "Laudes")
            DailyPrayerSlot.ANGELUS, DailyPrayerSlot.ANGELUS_EVENING ->
                com.example.data.liturgy.SeasonalMarianPrayer.forDate(
                    com.example.data.liturgy.CalendarDate(now.year, now.monthValue, now.dayOfMonth)
                ).title.get(currentLanguage)
            DailyPrayerSlot.CORONILLA ->
                strings("Coronilla de la Misericordia", "Divine Mercy Chaplet", "Coroa da Misericórdia")
            DailyPrayerSlot.NIGHT_PRAYERS ->
                strings("Oraciones de la noche", "Night prayers", "Orações da noite")
        }
        val (icon, onClick, clockHour, testTag) = when (slot) {
            DailyPrayerSlot.MORNING_PRAYERS ->
                Quad(Icons.Outlined.LightMode, onNavigateToMorningPrayers, 5, "home_morning_prayers_tile")
            DailyPrayerSlot.LAUDES ->
                Quad(Icons.Outlined.LightMode, onNavigateToLaudes, 6, "home_laudes_tile")
            DailyPrayerSlot.ANGELUS ->
                Quad(DevotionalGlyphs.sunriseOutlined, onNavigateToAngelus, 12, "home_angelus_tile")
            DailyPrayerSlot.CORONILLA ->
                Quad(DevotionalGlyphs.rosaryOutlined, onNavigateToChaplet, 15, "home_chaplet_tile")
            DailyPrayerSlot.ANGELUS_EVENING ->
                Quad(DevotionalGlyphs.sunriseOutlined, onNavigateToAngelus, 18, "home_angelus_tile")
            DailyPrayerSlot.NIGHT_PRAYERS ->
                Quad(Icons.Outlined.NightsStay, onNavigateToNightPrayers, 20, "home_night_prayers_tile")
        }
        AppListRow(
            title = title,
            subtitle = "$caption · ${TextFormat.clock(clockHour, 0, currentLanguage)}",
            leadingIcon = icon,
            onClick = onClick,
            modifier = Modifier.testTag(testTag)
        )
    }

    val insets = AppEdgeToEdge()
    val heroAsset = rememberFirstAsset("heroes/lumen/home.jpg", "heroes/church_window_light.jpg", "heroes/cathedral_dome.jpg")
    val saintAsset = rememberFirstAsset("heroes/church_window_light.jpg", "heroes/cathedral_dome.jpg")
    val ttsPlaying by (ttsManager?.isPlaying ?: kotlinx.coroutines.flow.MutableStateFlow(false)).collectAsState()
    var listeningToGospel by remember { mutableStateOf(false) }
    val dateEyebrow = remember(currentLanguage, now.toLocalDate()) {
        val locale = when (currentLanguage) {
            AppLanguage.ES -> Locale("es", "ES")
            AppLanguage.EN -> Locale.US
            AppLanguage.PT -> Locale("pt", "BR")
        }
        val pattern = when (currentLanguage) {
            AppLanguage.EN -> "EEEE, MMMM d"
            else -> "EEEE, d 'de' MMMM"
        }
        SimpleDateFormat(pattern, locale).format(Date.from(now.toInstant())).replaceFirstChar { it.titlecase(locale) }
    }
    val gutter = design.spacing.gutter

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            // The hero still reaches the top edge; scroll-to and keyboard
            // focus stop above the floating dock, where actions can be tapped.
            .padding(bottom = insets.calculateBottomPadding())
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Hero: misted art from the top of the display; the season card rides
        // 24dp up over its fade.
        item {
            AppHeroHeader(
                assetPath = heroAsset,
                title = strings("Hoy", "Today", "Hoje"),
                eyebrow = dateEyebrow,
                height = 212.dp,
                overlap = 24.dp,
                modifier = Modifier.testTag("screen_header_illustration")
            ) {
                AppGlassIconButton(
                    icon = Icons.Outlined.NotificationsNone,
                    contentDescription = strings("Recordatorios", "Reminders", "Lembretes"),
                    onClick = onOpenReminders,
                    onImage = true,
                    tag = "home_reminders_button"
                )
            }
        }

        // The liturgical day: the season once, the week and obligation on one line.
        item {
            val seasonName = liturgicalDay.seasonName.get(currentLanguage)
            val detail = seasonDetail(
                season = seasonName,
                celebration = liturgicalDay.celebrationTitle.get(currentLanguage),
                rule = liturgicalDay.fastingRule?.get(currentLanguage)
            )
            AppSeasonCard(
                season = seasonName,
                detail = detail,
                seasonColor = lumenLiturgicalColor(seasonName, liturgicalDay.liturgicalColorHex),
                onClick = onNavigateToPilgrimage,
                tag = "liturgical_calendar_widget",
                modifier = Modifier.padding(horizontal = gutter)
            )
            Spacer(Modifier.height(ClusterGap))
        }

        // The hour's prayer and the next one.
        item(key = "prayer_of_the_moment") {
            AppSectionHeader(title = strings("Oración del momento", "Pray now", "Oração do momento"), modifier = Modifier.testTag("home_prayer_moment_heading"))
            AppListGroup {
                DailyPrayerRow(currentPrayerSlot, strings("Ahora", "Now", "Agora"))
                DailyPrayerRow(nextPrayerSlot, strings("Después", "Next", "Depois"))
            }
            Spacer(Modifier.height(ClusterGap))
        }

        if (dao != null) item(key = "my_practices") {
            FidelityCard(dao = dao, currentLanguage = currentLanguage, onOpenHabits = onNavigateToHabits, onOpenTemptations = onOpenTemptations)
            Spacer(Modifier.height(ClusterGap))
        }

        // Mass of the day, built around the Word: the Gospel in reading type,
        // the first reading and the reflection as quiet rows, one primary action.
        item {
            AppGlassSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = gutter)
                    .testTag("daily_mass_card"),
                onClick = onNavigateToMissal,
                contentPadding = PaddingValues(design.spacing.cardPadding)
            ) {
                Column {
                    val homilyFirstLine = if (gospelPreview == null) {
                        dailyReading.homilyReflection.get(currentLanguage)
                            .takeIf { it.isNotBlank() }
                            ?.let { firstSentenceOf(cleanScripture(it, currentLanguage)) }
                    } else null
                    val headerRef = when {
                        gospelPreview != null ->
                            TextFormat.ranges(gospelReference)
                        else -> listOf(firstReadingReference, gospelReference).filter { it.isNotBlank() }
                            .joinToString(" · ") { TextFormat.ranges(it) }
                            .ifBlank { if (!homilyFirstLine.isNullOrBlank()) strings("Reflexión", "Reflection", "Reflexão") else "" }
                    }
                    CardHeader(strings("Liturgia de hoy", "Today's liturgy", "Liturgia de hoje"), headerRef)
                    val sourceLabel = when {
                        bundledGospel != null -> strings("Escritura para la reflexión", "Scripture for reflection", "Escritura para reflexão")
                        feedGospel?.text?.isNotBlank() == true -> "Vatican News"
                        else -> ""
                    }
                    if (sourceLabel.isNotBlank()) {
                        Spacer(Modifier.height(6.dp))
                        Text(sourceLabel, style = styles.footnote, color = palette.textSecondary)
                    }
                    Spacer(Modifier.height(12.dp))
                    if (gospelPreview != null) {
                        ExcerptText(
                            text = gospelPreview,
                            style = styles.reading,
                            color = palette.textPrimary,
                            maxLines = 3,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                    } else if (!homilyFirstLine.isNullOrBlank()) {
                        ExcerptText(homilyFirstLine, styles.reading, palette.textPrimary, 3)
                    }

                    // Quiet rows: first reading (information), reflection (disclosure), resume.
                    val showFirstRow = gospelPreview != null && firstReadingReference.isNotBlank()
                    if (showFirstRow || meditation != null || resumeReading != null) Spacer(Modifier.height(14.dp))
                    if (showFirstRow) {
                        CardHairline()
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    role = Role.Button,
                                    onClick = onNavigateToMissal
                                )
                                .testTag("home_first_reading_row"),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = listOf(strings("Primera lectura", "First reading", "Primeira leitura"), TextFormat.ranges(firstReadingReference))
                                    .joinToString(" · "),
                                style = styles.callout,
                                color = palette.textPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )
                            AppChevron(tint = palette.inkQuiet)
                        }
                    }
                    if (meditation != null) {
                        CardHairline()
                        DisclosureRow(
                            label = strings("Meditación del día", "Today's reflection", "Meditação do dia"),
                            expanded = meditationExpanded,
                            onClick = { meditationExpanded = !meditationExpanded },
                            modifier = Modifier.testTag("home_continue_row").semantics {
                                stateDescription = if (meditationExpanded) strings("Expandida", "Expanded", "Expandida")
                                else strings("Contraída", "Collapsed", "Recolhida")
                            }
                        )
                        AnimatedVisibility(
                            visible = meditationExpanded,
                            enter = expandVertically(tween(design.motion.mediumMillis, easing = design.motion.standardEasing)) +
                                fadeIn(tween(design.motion.fastMillis)),
                            exit = shrinkVertically(tween(design.motion.fastMillis, easing = design.motion.standardEasing)) +
                                fadeOut(tween(design.motion.fastMillis))
                        ) {
                            Text(
                                text = meditation,
                                style = styles.reading,
                                color = palette.textPrimary,
                                modifier = Modifier.padding(top = 4.dp, bottom = 12.dp).widthIn(max = design.spacing.readingMaxWidth)
                            )
                        }
                    }
                    if (resumeReading != null) {
                        CardHairline()
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .heightIn(min = 52.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    role = Role.Button,
                                    onClick = onNavigateToBible
                                )
                                .testTag("home_continue_reading"),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Outlined.Bookmark, null, tint = palette.textPrimary, modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(
                                    strings("Seguir leyendo", "Continue reading", "Continuar a ler"),
                                    style = styles.callout,
                                    color = palette.textPrimary
                                )
                                Text(resumeReading, style = styles.footnote, color = palette.textSecondary)
                            }
                            AppChevron(tint = palette.inkQuiet)
                        }
                    }

                    // One primary action, with a glass play circle beside it.
                    Spacer(Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AppPrimaryCapsule(
                            label = strings("Ver la liturgia de hoy", "Open today’s liturgy", "Ver a liturgia de hoje"),
                            onClick = onNavigateToMissal,
                            fillWidth = false,
                            tag = "read_mass_full_button"
                        )
                        if (ttsManager != null && gospelExcerpt != null) {
                            Spacer(Modifier.width(12.dp))
                            AppRoundPlayButton(
                                playing = listeningToGospel && ttsPlaying,
                                onClick = {
                                    if (listeningToGospel && ttsPlaying) {
                                        ttsManager.stop(); listeningToGospel = false
                                    } else {
                                        ttsManager.speakSingle(cleanScripture(gospelExcerpt, currentLanguage)); listeningToGospel = true
                                    }
                                },
                                contentDescription = strings("Escuchar el Evangelio", "Listen to the Gospel", "Ouvir o Evangelho"),
                                tag = "home_gospel_listen"
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(ClusterGap))
        }

        item(key = "today_more_toggle") {
            AppListGroup {
                AppListRow(strings("Más para hoy", "More for today", "Mais para hoje"), subtitle = strings("Santo, otras lecturas y oraciones", "Saint, more Scripture and prayers", "Santo, mais leituras e orações"), onClick = { showMoreToday = !showMoreToday }, modifier = Modifier.testTag("today_more_toggle").semantics { stateDescription = if (showMoreToday) strings("Expandido", "Expanded", "Expandido") else strings("Contraído", "Collapsed", "Recolhido") })
            }
        }
        if (showMoreToday) {
        // Verse of the day: a quiet pull quote in the reader face.
        item {
            val verseText = remember(verseOfTheDay, currentLanguage) {
                cleanScripture(verseOfTheDay.text.get(currentLanguage), currentLanguage)
            }
            AppGlassSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = gutter)
                    .testTag("verse_of_the_day_card"),
                onClick = openDailyVerse,
                contentPadding = PaddingValues(design.spacing.cardPadding)
            ) {
                Column {
                    CardHeader(
                        strings("Versículo del día", "Verse of the day", "Versículo do dia"),
                        TextFormat.ranges(scriptureRef(verseOfTheDay.reference, currentLanguage))
                    )
                    Spacer(Modifier.height(10.dp))
                    ExcerptText(
                        text = verseText,
                        style = styles.reading,
                        color = palette.textPrimary,
                        maxLines = 4,
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )
                    val challenge = verseOfTheDay.spiritualChallenge.get(currentLanguage)
                    if (challenge.isNotBlank()) {
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = hintClause(challenge),
                            style = styles.footnote,
                            color = palette.textSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Clip
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    AppSecondaryCapsule(
                        label = strings("Leer este pasaje", "Read this passage", "Ler esta passagem"),
                        onClick = openDailyVerse,
                        fillWidth = true,
                        tag = "home_read_daily_verse"
                    )
                }
            }
        }

        item(key = "today_invitation") {
            AppCard(Modifier.padding(horizontal = gutter).testTag("today_next_action")) {
                Text(strings("Un momento con Dios", "A moment with God", "Um momento com Deus"), style = styles.title2, color = palette.textPrimary)
                Spacer(Modifier.height(6.dp))
                Text(strings("${journeyStore.minutes} min · Un paso sencillo, a tu ritmo", "${journeyStore.minutes} min · One simple step, at your pace", "${journeyStore.minutes} min · Um passo simples, no seu ritmo"), style = styles.callout, color = palette.textSecondary)
                Spacer(Modifier.height(16.dp))
                AppSecondaryCapsule(strings("Comenzar", "Begin", "Começar"), onClick = onOpenBeginner, tag = "today_begin")
                if (!welcomed) {
                    Spacer(Modifier.height(8.dp))
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material3.TextButton(onClick = { showWelcome = true }, modifier = Modifier.weight(1f).heightIn(min = 48.dp).testTag("welcome_personalize")) { Text(strings("Elige tu ritmo", "Choose your pace", "Escolha seu ritmo"), style = styles.footnote) }
                        androidx.compose.material3.TextButton(onClick = { journeyStore.welcome(0, 5); welcomed = true }, modifier = Modifier.weight(1f).heightIn(min = 48.dp).testTag("welcome_skip")) { Text(strings("Ahora no", "Not now", "Agora não"), style = styles.footnote) }
                    }
                }
            }
            Spacer(Modifier.height(ClusterGap))
        }
        item(key = "today_continue_rosary") {
            val session = remember(now) { RosarySessionStore.load(context) }
            if (session != null && (session.rosaryStep > 0 || session.mercyStep > 0)) {
                AppListGroup {
                    AppListRow(strings("Continuar mi oración", "Continue my prayer", "Continuar minha oração"), strings("Tu lugar en el Rosario o la Coronilla está guardado", "Your place in the Rosary or Chaplet is saved", "Seu lugar no Terço ou na Coroa está salvo"), onClick = if (session.devotion == Devotion.MERCY) onNavigateToChaplet else onNavigateToRosary, modifier = Modifier.testTag("today_continue_prayer"))
                }
                Spacer(Modifier.height(ClusterGap))
            }
        }

        // Saint of the day.
        item {
            val rawSaintName = saintOfTheDay.name.get(currentLanguage).trim()
            val saintName = stripInlineFeastDate(rawSaintName, saintOfTheDay.feastDate)
            val feastDateLocalized = localizedFeastDateLabel(saintOfTheDay.feastDate, currentLanguage)
            val saintTitle = saintOfTheDay.title.get(currentLanguage).trim()
            val saintBiography = saintOfTheDay.biography.get(currentLanguage).trim()
            val saintQuote = saintOfTheDay.iconicQuote.get(currentLanguage).trim()
            // "San León Magno" as the title; "Papa y doctor de la Iglesia · 10 de noviembre" under it.
            val heading = saintHeading(saintName, currentLanguage)
            val saintMeta = listOfNotNull(
                heading.role,
                feastDateLocalized?.takeIf { it.isNotBlank() && !saintName.contains(it, ignoreCase = true) }
            ).joinToString(" · ").takeIf { it.isNotBlank() }
            val showTitle = saintTitle.isNotBlank() &&
                !saintTitle.equals(saintName, ignoreCase = true) &&
                !saintTitle.equals(heading.title, ignoreCase = true) &&
                !saintTitle.equals(heading.role, ignoreCase = true)
            val showQuote = saintQuote.isNotBlank() &&
                !saintQuote.equals(saintBiography, ignoreCase = true) &&
                !saintQuote.equals(saintName, ignoreCase = true) &&
                !saintQuote.equals(saintTitle, ignoreCase = true)
            Spacer(Modifier.height(SectionLead))
            AppSectionHeader(title = strings("Santo del día", "Saint of the day", "Santo do dia"))
            AppGlassSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = gutter)
                    .testTag("saint_of_the_day_card")
            ) {
                Column {
                    Box(Modifier.fillMaxWidth().height(150.dp)) {
                        AppHeroImage(saintAsset, Modifier.fillMaxSize(), fadeStart = 0.15f, topVeil = false, maxWidthPx = 900)
                        // Architecture is a generic sacred setting, never a portrait of this saint.
                        Column(Modifier.align(Alignment.BottomStart).padding(horizontal = 20.dp, vertical = 4.dp)) {
                            Text(
                                heading.title,
                                style = styles.title2,
                                color = palette.textPrimary,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            if (saintMeta != null) {
                                Spacer(Modifier.height(2.dp))
                                Text(saintMeta, style = styles.footnote, color = palette.textSecondary, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            }
                        }
                    }
                    Column(Modifier.padding(start = 20.dp, end = 20.dp, top = 6.dp, bottom = 20.dp)) {
                        if (showTitle) {
                            Text(saintTitle, style = styles.subhead, color = palette.textSecondary, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            Spacer(Modifier.height(10.dp))
                        }
                        ExcerptText(
                            text = saintBiography,
                            style = styles.callout,
                            color = palette.textPrimary,
                            maxLines = 5,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                        if (showQuote) {
                            Spacer(Modifier.height(16.dp))
                            MarginalQuote(text = saintQuote)
                        }
                    }
                }
            }
        }

        // Question of the day.
        if (dailyQuestion != null && dailyAnswerFirstLine != null) item {
            Spacer(Modifier.height(SectionLead))
            AppSectionHeader(title = strings("Pregunta del día", "Question of the day", "Pergunta do dia"))
            AppCard(modifier = Modifier.padding(horizontal = gutter).testTag("home_daily_question")) {
                Text(dailyQuestion.question.get(currentLanguage), style = styles.title2, color = palette.textPrimary)
                Spacer(Modifier.height(10.dp))
                ExcerptText(
                    text = dailyAnswerFirstLine,
                    style = styles.callout,
                    color = palette.textSecondary,
                    maxLines = 3,
                    modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                )
                Spacer(Modifier.height(18.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppPrimaryCapsule(
                        label = strings("Leer respuesta", "Read answer", "Ler resposta"),
                        onClick = { onOpenQuestions(dailyQuestionIndex) },
                        fillWidth = false,
                        tag = "home_daily_question_read"
                    )
                    Spacer(Modifier.weight(1f))
                    AppGlassIconButton(
                        icon = Icons.Outlined.IosShare,
                        contentDescription = strings("Compartir", "Share", "Partilhar"),
                        onClick = shareDailyQuestion,
                        tag = "home_daily_question_share"
                    )
                }
            }
        }

        // Gateways to the rest of the faith.
        item {
            data class Gate(val label: String, val icon: ImageVector, val onClick: () -> Unit, val tag: String)
            val gates = listOf(
                Gate(strings("Catecismo", "Catechism", "Catecismo"), Icons.Outlined.MenuBook, onNavigateToCatechism, "home_gate_catechism"),
                Gate(strings("Iglesias", "Churches", "Igrejas"), Icons.Outlined.Church, onNavigateToChurches, "home_gate_churches"),
                Gate(strings("Santuarios", "Shrines", "Santuários"), Icons.Outlined.Place, onNavigateToShrines, "home_gate_shrines"),
                Gate(strings("Trivia", "Trivia", "Quiz"), Icons.Outlined.StarOutline, onNavigateToQuiz, "home_gate_trivia"),
                Gate(strings("Vida interior", "Inner life", "Vida interior"), Icons.Outlined.Spa, onNavigateToSpiritualLife, "home_gate_spiritual"),
                Gate(strings("Explorar", "Explore", "Explorar"), Icons.Outlined.GridView, onNavigateToMore, "home_more_settings")
            )
            Spacer(Modifier.height(SectionLead))
            AppSectionHeader(title = strings("Explorar la fe", "Explore the faith", "Explorar a fé"))
            AppGrid(gates, columns = 3) { gate, m ->
                AppCategoryTile(label = gate.label, icon = gate.icon, onClick = gate.onClick, modifier = m, tag = gate.tag)
            }
        }
        } // optional Today discovery
    }
}

// ============================================================ small pieces

/**
 * The one card-header pattern: the title (headline) on the left, its reference
 * (footnote, secondary ink) on the right, on one baseline. No icon.
 */
@Composable
private fun CardHeader(title: String, reference: String) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
        Text(
            title,
            style = styles.headline,
            color = design.palette.textPrimary,
            modifier = Modifier.semantics { heading() }
        )
        if (reference.isNotBlank()) {
            Spacer(Modifier.width(12.dp))
            Text(
                reference,
                style = styles.footnote,
                color = design.palette.textSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f).padding(bottom = 1.dp)
            )
        }
    }
}

/** A hairline inside a glass card (the card's own padding supplies the inset). */
@Composable
private fun CardHairline() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(0.5.dp)
            .background(LocalAppDesign.current.palette.separator)
    )
}

/** A tappable row inside a card that shows a rotating chevron. */
@Composable
private fun DisclosureRow(
    label: String,
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    Row(
        modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.Button,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, style = styles.callout, color = palette.textPrimary, modifier = Modifier.weight(1f))
        Icon(
            Icons.Outlined.ExpandMore,
            contentDescription = null,
            tint = palette.inkQuiet,
            modifier = Modifier
                .size(22.dp)
                .graphicsLayer { rotationZ = if (expanded) 180f else 0f }
        )
    }
}

/**
 * Text that never ends in the middle of a word: when it would not fit in
 * [maxLines] it is cut back to the last clause boundary (comma, semicolon,
 * colon) or word, and closed with an ellipsis. Open guillemets are re-closed.
 */
@Composable
private fun ExcerptText(
    text: String,
    style: TextStyle,
    color: Color,
    maxLines: Int,
    modifier: Modifier = Modifier
) {
    var budget by remember(text) { mutableIntStateOf((maxLines * 46).coerceAtLeast(60)) }
    val shown = remember(text, budget) { balanceQuotes(clauseCut(text, budget)) }
    val uiFace = LocalAppTextStyles.current.footnote.fontFamily
    val annotated = remember(shown, uiFace) {
        buildAnnotatedString {
            val i = shown.indexOf('…')
            if (i < 0) append(shown) else {
                append(shown.substring(0, i))
                // U+2026, set open in the UI face so it never reads as "...".
                withStyle(SpanStyle(fontFamily = uiFace, letterSpacing = 1.sp)) { append("…") }
                append(shown.substring(i + 1))
            }
        }
    }
    Text(
        text = annotated,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Clip,
        modifier = modifier,
        onTextLayout = { layout ->
            if (layout.hasVisualOverflow && budget > 24) {
                budget = (minOf(budget, shown.length) * 0.88f).toInt()
            }
        }
    )
}

/** A quote hung off a hairline rule in the margin. One ink: no accent colour. */
@Composable
private fun MarginalQuote(text: String) {
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
                .background(design.palette.inkQuiet)
        )
        Column(
            Modifier
                .weight(1f)
                .padding(start = 14.dp)
                .widthIn(max = design.spacing.readingMaxWidth)
        ) {
            Text(text = text, style = styles.quote, color = design.palette.textPrimary)
        }
    }
}

// ============================================================== fidelity

/**
 * Today's spiritual fidelity from the fixed rule of twelve ([DailyRule], the
 * same ids and goal as the Habits screen): a ring "N / 12", one line of copy
 * and ONE capsule that opens Habits (where the temptation log and the month
 * calendar live).
 */
@Composable
private fun FidelityCard(
    dao: SanctaFidesDao,
    currentLanguage: AppLanguage,
    onOpenHabits: () -> Unit,
    onOpenTemptations: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val largeLabels = styles.body.fontSize.value * LocalDensity.current.fontScale > 22f

    fun strings(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    val today = rememberLifecycleClock().toLocalDate()
    val todayKey = FidelityStore.dateKey(today)
    val completions by dao.getAllCompletions().collectAsState(initial = emptyList())
    val points = remember(completions, todayKey) { DailyRule.countsFrom(completions)[todayKey] ?: 0 }
    val total = DailyRule.habits.size
    AppSectionHeader(title = strings("Mis prácticas", "My practices", "Minhas práticas"), modifier = Modifier.testTag("home_practices_heading"))
    AppCard(Modifier.padding(horizontal = design.spacing.gutter).testTag("home_fidelity_card")) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.testTag("home_habit_tracker_card")) {
            AppProgressRing(progress = points.toFloat() / total, size = 76.dp, stroke = 5.dp) {
                Text("$points", style = styles.title2, color = palette.textPrimary, modifier = Modifier.testTag("home_fidelity_count"))
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(if (largeLabels) strings("de $total", "of $total", "de $total") else strings("de $total hoy", "of $total today", "de $total hoje"), style = styles.headline, color = palette.textPrimary)
                Spacer(Modifier.height(4.dp))
                Text(if (largeLabels) strings("Hoy", "Today", "Hoje") else strings("Oración y caridad, a tu ritmo", "Prayer and charity, at your pace", "Oração e caridade, no seu ritmo"), style = styles.callout, color = palette.textSecondary)
            }
        }
        Spacer(Modifier.height(12.dp))
        AppPrimaryCapsule(strings("Ver mis prácticas", "View my practices", "Ver minhas práticas"), onClick = onOpenHabits, tag = "home_fidelity_open_habits")
        Spacer(Modifier.height(4.dp))
        AppListRow(
            title = if (largeLabels) strings("Caídas y victorias", "Falls and victories", "Quedas e vitórias") else strings("Tentaciones y victorias", "Temptations and victories", "Tentações e vitórias"),
            subtitle = if (largeLabels) strings("Ayuda y oración", "Help and prayer", "Ajuda e oração") else strings("Registra una caída o una victoria", "Record a fall or a victory", "Registre uma queda ou uma vitória"),
            leadingIcon = if (largeLabels) null else Icons.Outlined.Spa,
            onClick = onOpenTemptations,
            modifier = Modifier.testTag("home_open_temptations")
        )
    }
}

// ============================================================ text helpers

/**
 * Cleans scripture text for display: one pair of guillemets at most (no
 * doubled «, no outer wrap), no space before punctuation or inside quotes.
 */
internal fun cleanScripture(raw: String, language: AppLanguage): String {
    var t = normalizeExcerptQuotes(raw, language).replace("...", "…").replace(" …", "…")
    t = t.replace(Regex("\\s+"), " ").trim()
    // The outer wrap of the passage is not part of the text.
    t = t.trimStart('«', '"', '“', ' ')
    t = t.replace(Regex("«\\s*«+"), "«")
    t = t.replace(Regex("«\\s+"), "«").replace(Regex("\\s+»"), "»")
    t = t.replace(Regex("\\s+([.,;:!?…])"), "$1")
    // A closer left without its opener (the stripped wrap) goes with it.
    val opens = t.count { it == '«' }
    var closes = t.count { it == '»' }
    while (closes > opens && t.endsWith("»")) {
        t = t.dropLast(1).trimEnd()
        closes--
    }
    return t
}

/**
 * A hint cut to its first clause so it fits one line with no ellipsis:
 * "Hoy relee este versículo despacio tres veces, deteniéndote…" becomes
 * "Hoy relee este versículo despacio tres veces."
 */
internal fun hintClause(text: String): String {
    val t = text.trim()
    val end = t.indexOfFirst { it == ',' || it == ';' || it == ':' || it == '.' || it == '!' || it == '?' }
    val clause = (if (end > 8) t.take(end) else t).trimEnd(',', ';', ':', ' ')
    return if (clause.endsWith(".") || clause.endsWith("!") || clause.endsWith("?")) clause else "$clause."
}

/** Appends the closers an excerpt left open. */
internal fun balanceQuotes(text: String): String {
    val missing = text.count { it == '«' } - text.count { it == '»' }
    return if (missing > 0) text + "»".repeat(missing) else text
}

/**
 * [text] cut back to [budget] characters at the last clause boundary in its
 * second half, else at a word, plus an ellipsis; unchanged when it fits.
 */
internal fun clauseCut(text: String, budget: Int): String {
    if (text.length <= budget) return text
    val head = text.take(budget)
    val clause = head.indexOfLast { it == ',' || it == ';' || it == ':' || it == '—' }
    val end = when {
        clause >= budget / 2 -> clause
        else -> head.lastIndexOf(' ').takeIf { it > 0 } ?: head.length
    }
    return head.take(end).trimEnd(',', ';', ':', ' ', '—', '-', '«') + "…"
}

/** First sentence of [text], keeping a closing guillemet that follows its full stop. */
internal fun firstSentenceOf(text: String): String {
    val trimmed = text.trim()
    val end = Regex("""[.!?]+[»”"’]*(?=\s|$)""").find(trimmed)?.range?.last
    return if (end != null) trimmed.take(end + 1) else trimmed
}

/** First line of a multi-paragraph answer: its first physical line, cut at the first sentence end. */
internal fun firstLineOf(text: String): String {
    val line = text.trim().substringBefore('\n').trim()
    val end = Regex("""[.!?](?=\s|$)""").find(line)?.range?.last
    return if (end != null) line.take(end + 1) else line
}

/**
 * Scripture reference polish: "Salmos 51, 10-12" names one psalm, so it reads
 * "Salmo 51, 10–12". Ranges take an en dash.
 */
internal fun scriptureRef(reference: String, language: AppLanguage): String {
    var ref = TextFormat.ranges(reference.trim())
    if (language != AppLanguage.EN) {
        ref = ref.replace(Regex("^Salmos\\s+(\\d+)(?=\\s*,)"), "Salmo $1")
    }
    return ref
}

/**
 * Season card detail on one line: the celebration without repeating the
 * season ("Domingo 32 del Tiempo Ordinario" → "Domingo XXXII") then the rule.
 */
internal fun seasonDetail(season: String, celebration: String, rule: String?): String? {
    val cel = celebration.trim().takeUnless { it.isBlank() || it.equals(season, ignoreCase = true) }
        ?.let { c ->
            val stripped = Regex(
                """\s*(?:del|de la|de|of the|of|do|da|in)?\s*${Regex.escape(season)}""",
                RegexOption.IGNORE_CASE
            ).replace(c, "").trim()
            romanSunday(stripped.ifBlank { c })
        }
    return listOfNotNull(cel?.takeIf { it.isNotBlank() }, rule?.trim()?.takeIf { it.isNotBlank() })
        .joinToString(" · ").ifBlank { null }
}

/** "Domingo 32" → "Domingo XXXII" (Sundays only; other titles are left as written). */
private fun romanSunday(text: String): String {
    val m = Regex("""^(Domingo|Sunday)\s+(\d{1,2})$""", RegexOption.IGNORE_CASE).find(text) ?: return text
    val n = m.groupValues[2].toInt()
    return if (n in 1..60) "${m.groupValues[1]} ${TextFormat.roman(n)}" else text
}

/**
 * Normalizes quote marks inside a scripture excerpt for display. Spanish and
 * Portuguese use « » exclusively, so straight (" ") and curly (“ ”) quotes
 * are folded to « », alternating open/close for straight quotes. Other
 * languages keep the source marks.
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

// ================================================================ prayers

/** Time-based daily-prayer slot: which prayer the hour calls for. */
private enum class DailyPrayerSlot {
    MORNING_PRAYERS,
    LAUDES,
    ANGELUS,
    CORONILLA,
    ANGELUS_EVENING,
    NIGHT_PRAYERS
}

/** The schedule's next prayer after [slot]; the cycle wraps from night back to morning. */
private fun nextDailyPrayerSlot(slot: DailyPrayerSlot): DailyPrayerSlot = when (slot) {
    DailyPrayerSlot.MORNING_PRAYERS -> DailyPrayerSlot.LAUDES
    DailyPrayerSlot.LAUDES -> DailyPrayerSlot.ANGELUS
    DailyPrayerSlot.ANGELUS -> DailyPrayerSlot.CORONILLA
    DailyPrayerSlot.CORONILLA -> DailyPrayerSlot.ANGELUS_EVENING
    DailyPrayerSlot.ANGELUS_EVENING -> DailyPrayerSlot.NIGHT_PRAYERS
    DailyPrayerSlot.NIGHT_PRAYERS -> DailyPrayerSlot.MORNING_PRAYERS
}

/**
 * The slot for [hour]: `morning -> Morning Prayers` (5am, before the 6am
 * Laudes), 06:00 Laudes, 12:00 Angelus, 15:00 Coronilla, 18:00 Angelus,
 * 20:00+ traditional night prayers. The small hours (0–4) stay on the
 * night prayers.
 */
private fun dailyPrayerSlotForHour(hour: Int): DailyPrayerSlot = when (hour) {
    in 0..4 -> DailyPrayerSlot.NIGHT_PRAYERS
    5 -> DailyPrayerSlot.MORNING_PRAYERS
    in 6..11 -> DailyPrayerSlot.LAUDES
    in 12..14 -> DailyPrayerSlot.ANGELUS
    in 15..17 -> DailyPrayerSlot.CORONILLA
    in 18..19 -> DailyPrayerSlot.ANGELUS_EVENING
    else -> DailyPrayerSlot.NIGHT_PRAYERS
}

/** One daily-prayer row payload: icon, tap, clock hour and test tag. */
private data class Quad(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val clockHour: Int,
    val testTag: String
)
