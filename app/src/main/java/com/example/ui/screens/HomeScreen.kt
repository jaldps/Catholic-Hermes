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
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.liturgy.LiturgicalColor
import com.example.data.models.AppLanguage
import com.example.data.models.DailyMassReading
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.models.SaintOfTheDay
import com.example.data.models.VerseOfTheDay
import com.example.data.repository.PilgrimageAndHabitsData
import com.example.ui.design.AppBanner
import com.example.ui.design.AppCard
import com.example.ui.design.AppDivider
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
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

    val liturgicalPigment = pigment(Color(liturgicalDay.liturgicalColorHex), design.isDark, palette.textPrimary)
    val colorLabel = LiturgicalColor.entries
        .firstOrNull { it.hex == liturgicalDay.liturgicalColorHex }
        ?.displayName?.get(currentLanguage)
        ?: strings("Color Litúrgico", "Liturgical Color", "Cor Litúrgica")

    // Both of these are empty on a ferial weekday the bundled lectionary does
    // not carry, so the day's gospel line and its disclosure appear only when
    // there is something behind them.
    val meditation = liturgicalDay.missalMeditation?.get(currentLanguage)
        ?.takeIf { it.isNotBlank() }
    val gospelOfRecord = liturgicalDay.gospelVerseRef.takeIf { it.isNotBlank() }

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

    // A ferial weekday is not in the bundled lectionary, and the repository
    // leaves its citations empty rather than borrowing another day's readings.
    val bundledCitations = listOfNotNull(
        strings("Primera lectura", "First reading", "Primeira leitura") to dailyReading.firstReadingRef,
        strings("Salmo responsorial", "Responsorial psalm", "Salmo responsorial")
            to dailyReading.responsorialPsalmRef,
        dailyReading.secondReadingRef?.let {
            strings("Segunda lectura", "Second reading", "Segunda leitura") to it
        },
        strings("Evangelio", "Gospel", "Evangelho") to dailyReading.gospelRef
    ).filter { it.second.isNotBlank() }
    val massCitations = vatican?.sections
        ?.map { section -> section.heading to section.citation.ifBlank { "" } }
        ?.map { (heading, citation) -> if (citation.isBlank()) "" to heading else heading to citation }
        ?: bundledCitations
    val gospelExcerpt = vatican?.sections?.lastOrNull()?.text?.takeIf { it.isNotBlank() }
        ?: dailyReading.gospelText.get(currentLanguage).takeIf { it.isNotBlank() }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen")
    ) {
        item {
            AppScreenTitle(
                title = strings("Hoy", "Today", "Hoje"),
                subtitle = strings(
                    "${liturgicalDay.dayOfMonth} de ${liturgicalDay.monthName}",
                    "${liturgicalDay.monthName} ${liturgicalDay.dayOfMonth}",
                    "${liturgicalDay.dayOfMonth} de ${liturgicalDay.monthName}"
                )
            )
        }

        // The liturgical colour is the one literal colour the data owns, and
        // the banner is where each identity decides what to do with it.
        item {
            AppBanner(
                title = liturgicalDay.seasonName.get(currentLanguage),
                body = liturgicalDay.celebrationTitle.get(currentLanguage),
                accentColorHex = liturgicalDay.liturgicalColorHex,
                modifier = Modifier.testTag("liturgical_calendar_widget")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        item {
            AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DayBadge(label = colorLabel, tint = liturgicalPigment)
                    DayBadge(
                        label = liturgicalDay.rank.get(currentLanguage),
                        tint = palette.textSecondary
                    )
                    liturgicalDay.sundayCycle?.let {
                        DayBadge(label = it.get(currentLanguage), tint = palette.textSecondary)
                    }
                    liturgicalDay.weekdayCycle?.let {
                        DayBadge(label = it.get(currentLanguage), tint = palette.textSecondary)
                    }
                    liturgicalDay.fastingRule?.let {
                        DayBadge(
                            label = it.get(currentLanguage),
                            tint = palette.accent,
                            icon = if (liturgicalDay.isFastOrAbstinence) {
                                Icons.Default.Restaurant
                            } else {
                                Icons.Default.Star
                            }
                        )
                    }
                }

                if (gospelOfRecord != null) {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(design.shapes.buttonSmall)
                            .then(
                                if (meditation != null) {
                                    Modifier.clickable {
                                        meditationExpanded = !meditationExpanded
                                    }
                                } else {
                                    Modifier
                                }
                            )
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoStories,
                            contentDescription = null,
                            tint = palette.textTertiary,
                            modifier = Modifier.size(design.decor.rowIconSize * 0.8f)
                        )
                        Spacer(Modifier.width(design.spacing.iconLabelGap))
                        Text(
                            text = gospelOfRecord,
                            style = styles.rowSubtitle,
                            color = palette.textPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        if (meditation != null) {
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = if (meditationExpanded) {
                                    strings("Ocultar", "Hide", "Ocultar")
                                } else {
                                    strings("Meditación", "Meditation", "Meditação")
                                }.uppercase(),
                                style = styles.sectionEyebrow,
                                color = palette.accent,
                                maxLines = 1
                            )
                            Icon(
                                imageVector = if (meditationExpanded) {
                                    Icons.Default.KeyboardArrowUp
                                } else {
                                    Icons.Default.KeyboardArrowDown
                                },
                                contentDescription = null,
                                tint = palette.accent,
                                modifier = Modifier.size(18.dp)
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
                        Column {
                            Text(
                                text = strings(
                                    "Meditación litúrgica del día",
                                    "Liturgical meditation of the day",
                                    "Meditação litúrgica do dia"
                                ).uppercase(),
                                style = styles.sectionEyebrow,
                                color = palette.textSecondary
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = meditation.orEmpty(),
                                style = styles.scripture,
                                color = palette.textPrimary,
                                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                            )
                            Spacer(Modifier.height(4.dp))
                        }
                    }
                }
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // Habit & virtue tracker summary.
        item {
            AppCard(
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("home_habit_tracker_card"),
                onClick = onNavigateToHabits
            ) {
                CardHeading(
                    label = strings(
                        "Hábitos y Virtudes (Regla de Vida)",
                        "Habits & Virtues (Rule of Life)",
                        "Hábitos e Virtudes (Regra de Vida)"
                    ),
                    icon = Icons.Default.Star,
                    trailing = strings("Ver Panel", "Dashboard", "Painel"),
                    trailingColor = palette.accent
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = strings(
                        "Sigue tus devociones diarias, ayuno y el Antídoto contra la tentación.",
                        "Track your daily devotions, fasting, and Grace Antidote against sins.",
                        "Acompanhe suas práticas diárias, jejum e o Antídoto contra o pecado."
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = palette.textSecondary
                )
            }
        }

        // Daily sanctification routine. Four rows rather than four tiles: the
        // Spanish and Portuguese hour names do not survive a 4-across grid.
        item {
            AppSectionHeader(
                title = strings(
                    "Rutina espiritual diaria",
                    "Daily spiritual routine",
                    "Rotina espiritual diária"
                )
            )
        }

        item {
            AppListGroup {
                AppListRow(
                    title = strings("Laudes", "Morning", "Laudes"),
                    leadingIcon = Icons.Default.LightMode,
                    trailingText = "6:00 AM",
                    onClick = onNavigateToPrayers
                )
                AppListRow(
                    title = strings("El Ángelus", "The Angelus", "O Angelus"),
                    leadingIcon = Icons.Default.WbTwilight,
                    trailingText = "12:00 PM",
                    onClick = onNavigateToPrayers
                )
                AppListRow(
                    title = strings("Santo Rosario", "Holy Rosary", "Santo Terço"),
                    leadingIcon = Icons.Default.SelfImprovement,
                    trailingText = strings("Hoy", "Today", "Hoje"),
                    onClick = onNavigateToRosary
                )
                AppListRow(
                    title = strings("Completas", "Night Examen", "Completas"),
                    leadingIcon = Icons.Default.NightsStay,
                    trailingText = "9:00 PM",
                    onClick = onNavigateToPrayers
                )
            }
            Spacer(Modifier.height(design.spacing.sectionGap / 2))
        }

        // Mass of the day.
        item {
            AppCard(
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("daily_mass_card")
            ) {
                CardHeading(
                    label = strings(
                        "Lecturas de la Misa de Hoy",
                        "Today's Mass Readings",
                        "Leituras da Santa Missa"
                    ),
                    icon = Icons.Default.AutoStories
                )
                Spacer(Modifier.height(design.spacing.cardGap))

                if (massCitations.isEmpty() && vaticanResult == null) {
                    Text(
                        text = strings(
                            "Cargando las lecturas de hoy…",
                            "Loading today's readings…",
                            "A carregar as leituras de hoje…"
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textSecondary
                    )
                } else if (massCitations.isEmpty()) {
                    Text(
                        text = strings(
                            "El leccionario incluido no trae las lecturas de esta feria. " +
                                "La celebración del día queda indicada arriba.",
                            "The bundled lectionary has no readings for this weekday. " +
                                "The day's celebration is given above.",
                            "O lecionário incluído não traz as leituras desta féria. " +
                                "A celebração do dia está indicada acima."
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textSecondary,
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )
                } else {
                    // Stacked rather than two-column: a resolved citation
                    // carries the book's full name and would crush a label
                    // sharing its line.
                    massCitations.forEachIndexed { index, (label, reference) ->
                        if (index > 0) Spacer(Modifier.height(12.dp))
                        if (label.isNotBlank()) {
                            Text(
                                text = label.uppercase(),
                                style = styles.sectionEyebrow,
                                color = palette.textTertiary
                            )
                            Spacer(Modifier.height(3.dp))
                        }
                        Text(
                            text = reference,
                            style = styles.rowSubtitle,
                            color = palette.textPrimary
                        )
                    }

                    if (gospelExcerpt != null) {
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = "«$gospelExcerpt»",
                            style = styles.scripture,
                            color = palette.textPrimary,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                    }
                }

                Spacer(Modifier.height(design.spacing.cardGap))
                AppPrimaryButton(
                    label = strings(
                        "Leer la Misa de hoy",
                        "Read today's Mass",
                        "Ler a Missa de hoje"
                    ),
                    onClick = onNavigateToMissal,
                    leadingIcon = Icons.Default.AutoStories,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("read_mass_full_button")
                )
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // Verse of the day: the screen's hero pull quote.
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
                    text = "💡 " + verseOfTheDay.spiritualChallenge.get(currentLanguage),
                    style = MaterialTheme.typography.bodyMedium,
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
                Spacer(Modifier.height(6.dp))
                Text(
                    text = saintOfTheDay.title.get(currentLanguage),
                    style = styles.rowSubtitle,
                    color = palette.textSecondary
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                Text(
                    text = saintOfTheDay.biography.get(currentLanguage),
                    style = MaterialTheme.typography.bodyMedium,
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
            AppCard(
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("home_nearby_churches_banner"),
                onClick = openNearbyChurches
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.NearMe,
                        contentDescription = null,
                        tint = palette.accent,
                        modifier = Modifier.size(design.decor.rowIconSize)
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap))
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = strings(
                                "Buscar Iglesias Cerca de Mí",
                                "Find Churches Near Me",
                                "Buscar Igrejas Perto de Mim"
                            ),
                            style = styles.rowTitle,
                            color = palette.textPrimary
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = strings(
                                "Ubicación en Google Maps y horarios de misa",
                                "Google Maps nearby & mass times",
                                "Localização no Google Maps e horários"
                            ),
                            style = styles.rowSubtitle,
                            color = palette.textSecondary
                        )
                    }
                }
                Spacer(Modifier.height(design.spacing.cardGap))
                AppSecondaryButton(
                    label = strings("Buscar", "Locate", "Localizar"),
                    onClick = openNearbyChurches,
                    leadingIcon = Icons.Default.LocationOn,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item { AppOrnament() }

        item {
            AppSectionHeader(
                title = strings("Explorar la Fe", "Explore the Faith", "Explorar a Fé")
            )
        }

        item {
            AppListGroup {
                AppListRow(
                    title = strings("Directorio Iglesias", "Church Directory", "Diretório Igrejas"),
                    subtitle = strings("Por país y ciudad", "By Country & City", "Por país e cidade"),
                    leadingIcon = Icons.Default.Church,
                    onClick = onNavigateToDoctrine
                )
                AppListRow(
                    title = strings("Trivia Católica", "Catholic Trivia", "Quiz Católico"),
                    subtitle = strings("Aprende jugando", "Test Your Faith", "Aprenda a Fé"),
                    leadingIcon = Icons.Default.Star,
                    onClick = onNavigateToQuiz
                )
                AppListRow(
                    title = strings("Catecismo (CCC)", "Catechism (CCC)", "Catecismo (CIC)"),
                    subtitle = strings("4 Pilares de la Fe", "4 Pillars of Faith", "4 Pilares da Fé"),
                    leadingIcon = Icons.Default.MenuBook,
                    onClick = onNavigateToCatechism
                )
                AppListRow(
                    title = strings("Santuarios y Fe", "Shrines & Pilgrims", "Santuários e Fé"),
                    subtitle = strings("Indulgencias y Rutas", "Indulgences & Maps", "Indulgências e Rotas"),
                    leadingIcon = Icons.Default.Place,
                    onClick = onNavigateToPilgrimage
                )
                AppListRow(
                    title = strings("Idioma", "Language", "Idioma"),
                    subtitle = currentLanguage.displayName,
                    leadingIcon = Icons.Default.Language,
                    trailingText = "${currentLanguage.flag} ${currentLanguage.code.uppercase()}",
                    onClick = onOpenLanguageSelector,
                    modifier = Modifier.testTag("widget_lang_button")
                )
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
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
                tint = design.palette.textSecondary,
                modifier = Modifier.size(design.decor.rowIconSize * 0.8f)
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = label.uppercase(),
            style = styles.sectionEyebrow,
            color = design.palette.textSecondary,
            modifier = Modifier.weight(1f)
        )
        if (trailing != null) {
            Spacer(Modifier.width(12.dp))
            Text(
                text = trailing.uppercase(),
                style = styles.sectionEyebrow,
                color = trailingColor ?: design.palette.textTertiary,
                maxLines = 1
            )
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
                    text = attribution.uppercase(),
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
@Composable
private fun DayBadge(
    label: String,
    tint: Color,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Row(
        modifier = modifier
            .clip(design.shapes.chip)
            .background(tint.copy(alpha = if (design.isDark) 0.20f else 0.10f))
            .border(
                width = design.depth.cardBorderWidth.coerceAtLeast(0.75.dp),
                color = tint.copy(alpha = 0.40f),
                shape = design.shapes.chip
            )
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(12.dp)
            )
            Spacer(Modifier.width(6.dp))
        }
        Text(
            text = if (design.decor.usesAllCapsLabels) label.uppercase() else label,
            style = styles.sectionEyebrow,
            color = tint,
            // "Ayuno y abstinencia de carne" at Vatican's tracking is wider
            // than the card, so a badge is allowed to set on two lines.
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Lifts a liturgical colour until it reads as pigment on a dark ground; violet
 * and green at their printed values disappear into a night canvas.
 */
private fun pigment(color: Color, isDark: Boolean, ink: Color): Color =
    if (isDark) lerp(color, ink, 0.45f) else color
