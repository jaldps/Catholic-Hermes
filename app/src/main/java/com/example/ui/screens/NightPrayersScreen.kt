package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.data.models.ComplineOffice
import com.example.data.models.IgnatianExamenStep
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppMetricTile
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.design.AppVerseRow
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip

/** Minimum touch target for every control this screen owns (AAA). */
private val MinTouchTarget = 48.dp

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** The sigla the office prints before the two voices of a versicle. */
private const val VersicleSigil = "V/."
private const val ResponseSigil = "R/."
private val Sigla = listOf(VersicleSigil, ResponseSigil)

/** The Nunc Dimittis is Luke 2, 29-32, so its stichs carry the Gospel's figures. */
private const val NuncDimittisFirstVerse = 29

/**
 * Latin the office prints but the bundled office does not carry as data: the
 * Confiteor, the opening of Psalm 91 and the final blessing.
 */
private const val ConfiteorLatin =
    "Confíteor Deo omnipoténti, et vobis, fratres, quia peccávi nimis cogitatióne, " +
        "verbo, ópere et omissióne: mea culpa, mea culpa, mea máxima culpa..."
private const val PsalmLatin =
    "Qui hábitat in protectióne Altíssimi, in umbra Omnipoténtis commorábitur. " +
        "Dicet Dómino: Refúgium meum et fortitúdo mea, Deus meus, in quo confídam..."
private const val ConcludingLatin =
    "Noctem quiétam et finem perféctum concédat nobis Dóminus omnípotens. Amen."

/**
 * Compline — the Church's own night prayer — and the Ignatian examen.
 *
 * Both are read rather than operated, so the office is set as a continuous page
 * held to the identity's reading measure: a rubric heading opens each part, the
 * text follows in the reading face, and the controls are single full-width
 * actions rather than a bank of buttons. Nothing here is a dashboard.
 */
@Composable
fun NightPrayersScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    /** The host screen's title and view switcher, set as the first rows. */
    header: LazyListScope.() -> Unit = {}
) {
    var selectedNightOfficeTab by remember { mutableIntStateOf(0) } // 0 = Roman Compline, 1 = Ignatian Examen

    val compline = repository.complineOffice
    val examenSteps = repository.ignatianExamenSteps
    val isPlayingAudio by ttsManager.isPlaying.collectAsState()

    val tabsInset = segmentedTabsInset()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("night_prayers_screen")
    ) {
        // Compline or the examen is a choice inside the night's prayer, not a
        // second strip of views: two chips under the screen's own control.
        val officeHeader: LazyListScope.() -> Unit = {
            header()

            // Photographic hero (sunrise) with fade between the host title
            // and the office picker: the night office opens on the same
            // sunrise the day begins from, clipped to the 20dp card radius.
            item(key = "night_sunrise_hero") {
                val sunriseBitmap = rememberHeroBitmap("heroes/sunrise_mountains.jpg")
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IveGutter)
                        .height(220.dp)
                        .clip(LocalAppDesign.current.shapes.card)
                        .background(LocalAppDesign.current.palette.selectionTint)
                        .testTag("night_sunrise_hero")
                ) {
                    if (sunriseBitmap != null) {
                        Image(
                            bitmap = sunriseBitmap,
                            contentDescription = currentLanguage.pick(
                                "Amanecer entre montañas",
                                "Sunrise over mountains",
                                "Nascer do sol sobre montanhas"
                            ),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(LocalAppDesign.current.shapes.card)
                        )
                    }
                    // Fade: clear over the upper photo, canvas by the foot
                    // of the band, so the hero dissolves into the page.
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        LocalAppDesign.current.palette.canvas
                                    )
                                )
                            )
                    )
                    // Light scrim behind the title zone: the photo is
                    // lightened to the paper canvas under it rather than
                    // darkened.
                    Box(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(160.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        LocalAppDesign.current.palette.canvas.copy(alpha = 0.55f),
                                        LocalAppDesign.current.palette.canvas.copy(alpha = 0.92f),
                                        LocalAppDesign.current.palette.canvas
                                    )
                                )
                            )
                    )
                }
                Spacer(Modifier.height(LocalAppDesign.current.spacing.cardGap))
            }

            item(key = "night_office_choice") {
                OfficeChoice(
                    labels = listOf(
                        currentLanguage.pick("Completas", "Compline", "Completas"),
                        currentLanguage.pick("Examen ignaciano", "Ignatian examen", "Exame inaciano")
                    ),
                    selectedIndex = selectedNightOfficeTab,
                    onSelect = { selectedNightOfficeTab = it },
                    title = currentLanguage.pick("Oficio", "Office", "Ofício")
                )
            }
        }

        when (selectedNightOfficeTab) {
            0 -> ComplineView(
                header = officeHeader,
                compline = compline,
                ttsManager = ttsManager,
                isNarrating = isPlayingAudio,
                currentLanguage = currentLanguage
            )
            1 -> IgnatianExamenView(
                header = officeHeader,
                steps = examenSteps,
                ttsManager = ttsManager,
                isNarrating = isPlayingAudio,
                currentLanguage = currentLanguage
            )
        }
    }
}

// ------------------------------------------------------------------- compline

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ComplineView(
    header: LazyListScope.() -> Unit,
    compline: ComplineOffice,
    ttsManager: LiturgicalTtsManager,
    isNarrating: Boolean,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    var selectedMarianAntiphonIndex by remember { mutableIntStateOf(0) }
    // Held per part rather than inside each part, so scrolling the office out of
    // the lazy window no longer closes the Latin a reader had opened.
    var latinParts by remember { mutableStateOf(emptySet<Int>()) }
    val antiphonScroll = rememberScrollState()

    val currentAntiphon = compline.marianAntiphons.getOrNull(selectedMarianAntiphonIndex)
        ?: compline.marianAntiphons.firstOrNull()

    fun toggleLatin(part: Int) {
        latinParts = if (part in latinParts) latinParts - part else latinParts + part
    }

    val officeText = buildString {
        append(compline.openingVerseTranslation.get(currentLanguage))
        append("\n\n")
        append(compline.confiteorText.get(currentLanguage))
        append("\n\n")
        append(compline.hymnTranslation.get(currentLanguage))
        append("\n\n")
        append(compline.psalmText.get(currentLanguage))
        append("\n\n")
        append(compline.canticleOfSimeonTranslation.get(currentLanguage))
        append("\n\n")
        append(compline.concludingPrayer.get(currentLanguage))
    }

    LazyColumn(Modifier.fillMaxSize()) {
        header()
        item {
            AppSectionHeader(
                // The chip above already names the office, so the rubric names
                // the hour instead of repeating it.
                title = currentLanguage.pick("Oración de la noche", "Night prayer", "Oração da noite"),
                subtitle = currentLanguage.pick(
                    "Liturgia oficial de la Iglesia antes del reposo de la noche",
                    "The Church's official liturgy before the night's rest",
                    "Liturgia oficial da Igreja antes do repouso da noite"
                )
            )
        }

        item {
            ReadingColumn {
                // While the voice is running the same 48dp button offers Stop,
                // so recitation can be ended without leaving the page.
                AppPrimaryButton(
                    label = if (isNarrating) {
                        currentLanguage.pick("Detener", "Stop", "Parar")
                    } else {
                        currentLanguage.pick("Rezar", "Pray", "Rezar")
                    },
                    onClick = {
                        if (isNarrating) ttsManager.stop() else ttsManager.speakSingle(officeText)
                    },
                    leadingIcon = if (isNarrating) Icons.Default.Stop else Icons.Default.VolumeUp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = MinTouchTarget)
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("compline_tts_button")
                        .semantics {
                            stateDescription = if (isNarrating) {
                                currentLanguage.pick("Narrando", "Narrating", "Narrando")
                            } else {
                                currentLanguage.pick("Detenido", "Stopped", "Parado")
                            }
                        }
                )
                if (isNarrating) {
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = currentLanguage.pick(
                            "Narrando el oficio…",
                            "Reading the office aloud…",
                            "Narrando o ofício…"
                        ),
                        style = styles.rowSubtitle,
                        color = design.palette.textSecondary,
                        modifier = Modifier
                            .align(design.decor.titleAlignment)
                            .padding(horizontal = design.spacing.gutter)
                    )
                }
            }
        }

        item { AppOrnament() }

        item {
            OfficePart(
                caption = currentLanguage.pick(
                    "1. Invocación inicial",
                    "1. Opening versicle",
                    "1. Invocação inicial"
                ),
                reference = "Deus in adiutórium",
                latinText = compline.openingVerseLatin,
                latinShown = 1 in latinParts,
                onToggleLatin = { toggleLatin(1) },
                currentLanguage = currentLanguage
            ) {
                LiturgicalText(compline.openingVerseTranslation.get(currentLanguage))
            }
        }

        item {
            OfficePart(
                caption = currentLanguage.pick(
                    "2. Acto penitencial",
                    "2. Penitential act",
                    "2. Ato penitencial"
                ),
                reference = "Confiteor Deo omnipotenti",
                latinText = ConfiteorLatin,
                latinShown = 2 in latinParts,
                onToggleLatin = { toggleLatin(2) },
                currentLanguage = currentLanguage
            ) {
                LiturgicalText(compline.confiteorText.get(currentLanguage))
            }
        }

        item {
            OfficePart(
                caption = currentLanguage.pick(
                    "3. Himno de Completas",
                    "3. Hymn at Compline",
                    "3. Hino de Completas"
                ),
                reference = compline.hymnLatinTitle,
                latinText = compline.hymnLatin,
                latinShown = 3 in latinParts,
                onToggleLatin = { toggleLatin(3) },
                currentLanguage = currentLanguage
            ) {
                LiturgicalText(compline.hymnTranslation.get(currentLanguage))
            }
        }

        item {
            OfficePart(
                caption = currentLanguage.pick(
                    "4. Salmodia de la noche",
                    "4. Psalmody of the night",
                    "4. Salmodia da noite"
                ),
                reference = compline.psalmReference,
                latinText = PsalmLatin,
                latinShown = 4 in latinParts,
                onToggleLatin = { toggleLatin(4) },
                currentLanguage = currentLanguage
            ) {
                LiturgicalText(
                    text = compline.psalmAntiphon.get(currentLanguage),
                    muted = true,
                    quoted = true
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                // The bundled psalm is one continuous paragraph, so it is set
                // stich by stich at its sentence ends; the canonical range is
                // the reference printed above it.
                val psalmStichs = stichs(compline.psalmText.get(currentLanguage))
                psalmStichs.forEachIndexed { index, stich ->
                    AppVerseRow(
                        number = index + 1,
                        text = stich,
                        isFirstOfChapter = index == 0
                    )
                }
            }
        }

        item {
            OfficePart(
                caption = currentLanguage.pick(
                    "5. Lectura breve y responsorio",
                    "5. Short reading and responsory",
                    "5. Leitura breve e responsório"
                ),
                reference = compline.readingReference,
                latinText = compline.responsoryLatin,
                latinShown = 5 in latinParts,
                onToggleLatin = { toggleLatin(5) },
                currentLanguage = currentLanguage
            ) {
                LiturgicalText(text = compline.readingText.get(currentLanguage), quoted = true)
                Spacer(Modifier.height(design.spacing.sectionGap / 2))
                PartLabel(
                    currentLanguage.pick("Responsorio breve", "Short responsory", "Responsório breve")
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                LiturgicalText(compline.responsoryTranslation.get(currentLanguage))
            }
        }

        // The gospel canticle is the summit of the office, and the one part the
        // old screen framed; here the ornament above it carries that weight.
        item { AppOrnament() }

        item {
            OfficePart(
                caption = currentLanguage.pick(
                    "6. Cántico evangélico (Nunc Dimittis)",
                    "6. Gospel canticle (Nunc Dimittis)",
                    "6. Cântico evangélico (Nunc Dimittis)"
                ),
                reference = "Canticum Simeonis (Lc 2, 29-32)",
                latinText = "${compline.canticleAntiphon.get(currentLanguage)}\n\n" +
                    compline.canticleOfSimeonLatin,
                latinShown = 6 in latinParts,
                onToggleLatin = { toggleLatin(6) },
                currentLanguage = currentLanguage
            ) {
                LiturgicalText(
                    text = compline.canticleAntiphon.get(currentLanguage),
                    muted = true,
                    quoted = true
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                val canticle = compline.canticleOfSimeonTranslation.get(currentLanguage)
                    .split("\n\n")
                canticle.first()
                    .split("\n")
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                    .forEachIndexed { index, stich ->
                        AppVerseRow(
                            number = NuncDimittisFirstVerse + index,
                            text = stich,
                            isFirstOfChapter = index == 0
                        )
                    }
                val doxology = canticle.drop(1).joinToString("\n\n").trim()
                if (doxology.isNotEmpty()) {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    LiturgicalText(doxology)
                }
            }
        }

        item {
            OfficePart(
                caption = currentLanguage.pick(
                    "7. Oración conclusiva y bendición",
                    "7. Concluding prayer and blessing",
                    "7. Oração conclusiva e bênção"
                ),
                reference = "Oratio et Benedictio",
                latinText = ConcludingLatin,
                latinShown = 7 in latinParts,
                onToggleLatin = { toggleLatin(7) },
                currentLanguage = currentLanguage
            ) {
                LiturgicalText(compline.concludingPrayer.get(currentLanguage))
                Spacer(Modifier.height(design.spacing.cardGap))
                LiturgicalText(text = compline.blessing.get(currentLanguage), quoted = true)
            }
        }

        if (currentAntiphon != null) {
            item { AppOrnament() }

            item {
                AppSectionHeader(
                    title = currentLanguage.pick(
                        "8. Antífona mariana final",
                        "8. Final Marian antiphon",
                        "8. Antífona mariana final"
                    ),
                    subtitle = currentAntiphon.seasonTag.get(currentLanguage)
                )

                // The antiphons as wrapping chips instead of a menu: every
                // Latin title is visible at once (no clipped label behind a
                // dropdown at 320dp / 130% text), each is a 48dp target, and
                // the kit draws the selected state from tokens.
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("marian_antiphon_picker"),
                    horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap),
                    verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
                ) {
                    compline.marianAntiphons.forEachIndexed { index, antiphon ->
                        AppChip(
                            label = antiphon.titleLatin,
                            selected = index == selectedMarianAntiphonIndex,
                            onClick = { selectedMarianAntiphonIndex = index },
                            modifier = Modifier.heightIn(min = MinTouchTarget)
                        )
                    }
                }

                Spacer(Modifier.height(design.spacing.cardGap))

                ReadingColumn {
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        Text(
                            text = caps(currentAntiphon.titleTranslation.get(currentLanguage)),
                            style = styles.sectionTitle,
                            color = design.palette.textPrimary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        LiturgicalText(
                            text = currentAntiphon.latinText,
                            muted = true,
                            inCard = true,
                            latin = true
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        LiturgicalText(
                            text = currentAntiphon.translatedText.get(currentLanguage),
                            inCard = true
                        )
                        Spacer(Modifier.height(design.spacing.cardPadding))
                        // A label this long cannot be defended in a half-width
                        // button, so it gets the full measure to itself.
                        AppPrimaryButton(
                            label = if (isNarrating) {
                                currentLanguage.pick("Detener", "Stop", "Parar")
                            } else {
                                currentLanguage.pick(
                                    "Escuchar ${currentAntiphon.titleLatin}",
                                    "Listen to ${currentAntiphon.titleLatin}",
                                    "Ouvir ${currentAntiphon.titleLatin}"
                                )
                            },
                            onClick = {
                                if (isNarrating) {
                                    ttsManager.stop()
                                } else {
                                    ttsManager.speakSingle(
                                        currentAntiphon.translatedText.get(currentLanguage)
                                    )
                                }
                            },
                            leadingIcon = if (isNarrating) Icons.Default.Stop else Icons.Default.VolumeUp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = MinTouchTarget)
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/**
 * One part of the office: its rubric heading and reference, the toggle that
 * reveals the Latin of that part, and the vernacular text itself.
 */
@Composable
private fun OfficePart(
    caption: String,
    reference: String?,
    latinText: String,
    latinShown: Boolean,
    onToggleLatin: () -> Unit,
    currentLanguage: AppLanguage,
    body: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current

    AppSectionHeader(
        title = caption,
        subtitle = reference,
        modifier = Modifier.semantics { heading() }
    )
    ReadingColumn {
        LatinToggle(
            showing = latinShown,
            currentLanguage = currentLanguage,
            onToggle = onToggleLatin,
            modifier = Modifier.align(design.decor.titleAlignment)
        )
        AnimatedVisibility(visible = latinShown) {
            Column {
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                LiturgicalText(text = latinText, muted = true)
            }
        }
        Spacer(Modifier.height(design.spacing.cardGap))
        body()
    }
    Spacer(Modifier.height(design.spacing.sectionGap / 2))
}

// --------------------------------------------------------------------- examen

@Composable
private fun IgnatianExamenView(
    header: LazyListScope.() -> Unit,
    steps: List<IgnatianExamenStep>,
    ttsManager: LiturgicalTtsManager,
    isNarrating: Boolean,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    var completedSteps by remember { mutableStateOf(emptySet<Int>()) }
    var selectedStepIndex by remember { mutableIntStateOf(0) }

    val step = steps.getOrNull(selectedStepIndex) ?: steps.firstOrNull()
    val percentDone = if (steps.isEmpty()) 0 else completedSteps.size * 100 / steps.size

    LazyColumn(Modifier.fillMaxSize()) {
        header()
        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Examen de conciencia",
                    "Examination of conscience",
                    "Exame de consciência"
                ),
                subtitle = currentLanguage.pick(
                    "Cinco pasos de San Ignacio para revisar el día ante Dios.",
                    "St. Ignatius's five steps to review the day before God.",
                    "Cinco passos de Santo Inácio para rever o dia diante de Deus."
                )
            )
        }

        item {
            ReadingColumn {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter),
                    horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
                ) {
                    AppMetricTile(
                        value = "${completedSteps.size}/${steps.size}",
                        caption = currentLanguage.pick(
                            "Pasos del examen",
                            "Steps of the examen",
                            "Passos do exame"
                        ),
                        icon = Icons.Default.Check,
                        modifier = Modifier.weight(1f)
                    )
                    AppMetricTile(
                        value = "$percentDone%",
                        caption = currentLanguage.pick("Completado", "Completed", "Concluído"),
                        icon = Icons.Default.NightsStay,
                        modifier = Modifier.weight(1f)
                    )
                }
                // Progress as a rule with a text equivalent, not only the
                // tiles' numbers: the bar gives the fraction at a glance in
                // the identity's accent on its selection tint, and
                // stateDescription carries the same $completed/$total text,
                // so dark mode and screen readers get it without colour.
                val progressFraction = if (steps.isEmpty()) 0f else
                    completedSteps.size.toFloat() / steps.size.toFloat()
                val progressLabel = currentLanguage.pick(
                    "${completedSteps.size} de ${steps.size} pasos completados",
                    "${completedSteps.size} of ${steps.size} steps completed",
                    "${completedSteps.size} de ${steps.size} passos concluídos"
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                LinearProgressIndicator(
                    progress = { progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .padding(horizontal = design.spacing.gutter)
                        .semantics {
                            contentDescription = currentLanguage.pick(
                                "Progreso del examen", "Examen progress", "Progresso do exame"
                            )
                            stateDescription = progressLabel
                        },
                    color = design.palette.accent,
                    trackColor = design.palette.selectionTint
                )
            }
        }

        item {
            AppSectionHeader(
                title = currentLanguage.pick("Los cinco pasos", "The five steps", "Os cinco passos"),
                subtitle = currentLanguage.pick(
                    "Toca un paso para rezarlo",
                    "Tap a step to pray it",
                    "Toque num passo para rezá-lo"
                )
            )
            AppListGroup {
                steps.forEachIndexed { index, examenStep ->
                    AppListRow(
                        title = examenStep.title.get(currentLanguage),
                        trailingText = if (examenStep.stepNumber in completedSteps) {
                            currentLanguage.pick("Hecho", "Done", "Feito")
                        } else {
                            null
                        },
                        showsNavigationAffordance = false,
                        selected = index == selectedStepIndex,
                        onClick = { selectedStepIndex = index },
                        modifier = Modifier
                            .heightIn(min = MinTouchTarget)
                            .semantics {
                                stateDescription = if (examenStep.stepNumber in completedSteps) {
                                    currentLanguage.pick("Hecho", "Done", "Feito")
                                } else {
                                    currentLanguage.pick("Pendiente", "Pending", "Pendente")
                                }
                            }
                    )
                }
            }
        }

        if (step != null) {
            item {
                val isStepDone = step.stepNumber in completedSteps
                val guidedPrayer = step.guidedPrayer.get(currentLanguage)

                Spacer(Modifier.height(design.spacing.sectionGap / 2))
                ReadingColumn {
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        Text(
                            text = caps(
                                currentLanguage.pick(
                                    "Paso ${step.stepNumber} de ${steps.size}",
                                    "Step ${step.stepNumber} of ${steps.size}",
                                    "Passo ${step.stepNumber} de ${steps.size}"
                                )
                            ),
                            style = styles.sectionEyebrow,
                            color = design.palette.textSecondary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap / 2))
                        Text(
                            text = caps(step.title.get(currentLanguage)),
                            style = styles.sectionTitle,
                            color = design.palette.textPrimary,
                            modifier = Modifier.semantics { heading() }
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        LiturgicalText(
                            text = step.description.get(currentLanguage),
                            muted = true,
                            inCard = true
                        )
                        Spacer(Modifier.height(design.spacing.sectionGap / 2))
                        PartLabel(
                            text = currentLanguage.pick(
                                "Oración sugerida",
                                "Suggested prayer",
                                "Oração sugerida"
                            ),
                            inCard = true
                        )
                        Spacer(Modifier.height(design.spacing.cardGap / 2))
                        LiturgicalText(text = guidedPrayer, quoted = true, inCard = true)

                        Spacer(Modifier.height(design.spacing.cardPadding))
                        // Full-width rows, not two abreast: inside a card the
                        // half measure is some 120dp, and "Escuchar" set as
                        // tracked Roman capitals does not fit it. While the
                        // voice runs the same 48dp button offers Stop.
                        AppSecondaryButton(
                            label = if (isNarrating) {
                                currentLanguage.pick("Detener", "Stop", "Parar")
                            } else {
                                currentLanguage.pick("Escuchar", "Listen", "Ouvir")
                            },
                            onClick = {
                                if (isNarrating) ttsManager.stop() else ttsManager.speakSingle(guidedPrayer)
                            },
                            leadingIcon = if (isNarrating) Icons.Default.Stop else Icons.Default.VolumeUp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = MinTouchTarget)
                                .testTag("examen_tts_button")
                                .semantics {
                                    stateDescription = if (isNarrating) {
                                        currentLanguage.pick("Narrando", "Narrating", "Narrando")
                                    } else {
                                        currentLanguage.pick("Detenido", "Stopped", "Parado")
                                    }
                                }
                        )
                        Spacer(Modifier.height(design.spacing.cardGap / 2))
                        AppPrimaryButton(
                            label = if (isStepDone) {
                                currentLanguage.pick("Deshacer", "Undo", "Desfazer")
                            } else {
                                currentLanguage.pick("Hecho", "Done", "Feito")
                            },
                            onClick = {
                                completedSteps = if (isStepDone) {
                                    completedSteps - step.stepNumber
                                } else {
                                    completedSteps + step.stepNumber
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = MinTouchTarget)
                                .semantics {
                                    stateDescription = if (isStepDone) {
                                        currentLanguage.pick("Hecho", "Done", "Feito")
                                    } else {
                                        currentLanguage.pick("Pendiente", "Pending", "Pendente")
                                    }
                                }
                        )
                        if (isNarrating) {
                            Spacer(Modifier.height(design.spacing.cardGap / 2))
                            Text(
                                // The flag is the manager's, not this step's, so
                                // the line says only that a narration runs.
                                text = currentLanguage.pick(
                                    "Narración en curso…",
                                    "Narration in progress…",
                                    "Narração em curso…"
                                ),
                                style = styles.rowSubtitle,
                                color = design.palette.textSecondary
                            )
                        }
                    }
                }
            }
        }

        if (steps.isNotEmpty() && completedSteps.size == steps.size) {
            item { AppOrnament() }
            item {
                ReadingColumn {
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Monochrome: plain ink glyph, not gold-washed.
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = design.palette.textPrimary,
                                modifier = Modifier.size(design.decor.rowIconSize + 8.dp)
                            )
                            Spacer(Modifier.height(design.spacing.cardGap))
                            Text(
                                text = caps(
                                    currentLanguage.pick(
                                        "¡Examen Completado!",
                                        "Examen complete",
                                        "Exame concluído!"
                                    )
                                ),
                                style = styles.sectionTitle,
                                color = design.palette.textPrimary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.semantics { heading() }
                            )
                            Spacer(Modifier.height(design.spacing.cardGap))
                            Text(
                                text = currentLanguage.pick(
                                    "Descansa en la paz y el perdón de Nuestro Señor Jesucristo. Que la Virgen María y los santos Ángeles custodien tu sueño.",
                                    "Rest in the peace and the forgiveness of Our Lord Jesus Christ. May the Virgin Mary and the holy Angels keep watch over your sleep.",
                                    "Descansa na paz e no perdão de Nosso Senhor Jesus Cristo. Que a Virgem Maria e os santos Anjos guardem o teu sono."
                                ),
                                style = styles.quote,
                                color = design.palette.textSecondary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// -------------------------------------------------------------------- setting

/**
 * Liturgical text as the office prints it.
 *
 * A line opening with a versicle or response siglum is set as one voice of a
 * dialogue — the siglum rubricated, the response set in from the versicle it
 * answers — and everything else as a paragraph of the reading face. Adjacent
 * lines are stacked without added leading so a hymn keeps the reading face's
 * own baseline grid; the blank lines the office text carries are the stanza
 * breaks.
 */
@Composable
private fun LiturgicalText(
    text: String,
    modifier: Modifier = Modifier,
    muted: Boolean = false,
    quoted: Boolean = false,
    inCard: Boolean = false,
    /** Latin, which the Vatican identity sets in its true italic. */
    latin: Boolean = false
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    val body = if (quoted) styles.quote else styles.scripture
    val ink = if (muted) design.palette.textSecondary else design.palette.textPrimary
    val rubric = rubricSigil()

    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = if (inCard) 0.dp else design.spacing.gutter)
    ) {
        text.split("\n").forEach { rawLine ->
            val line = rawLine.trim()
            if (line.isEmpty()) {
                Spacer(Modifier.height(design.spacing.cardGap))
                return@forEach
            }
            val siglum = Sigla.firstOrNull { line.startsWith(it) }
            Text(
                // ℣ and ℟ in rubric, set from the typed sigla at render time.
                text = if (siglum == null) {
                    withLiturgicalSigla(line, rubric)
                } else {
                    buildAnnotatedString {
                        withStyle(rubric) {
                            append(if (siglum == VersicleSigil) VersicleSign else ResponseSign)
                        }
                        append("\u2002")
                        append(withLiturgicalSigla(line.removePrefix(siglum).trim(), rubric))
                    }
                },
                style = body,
                color = ink,
                modifier = Modifier
                    .widthIn(max = design.spacing.readingMaxWidth)
                    .padding(
                        start = if (siglum == ResponseSigil) design.spacing.gutter else 0.dp
                    )
            )
        }
    }
}

/** A rubric naming one element inside a part of the office. */
@Composable
private fun PartLabel(
    text: String,
    modifier: Modifier = Modifier,
    inCard: Boolean = false
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Text(
        text = caps(text),
        style = styles.sectionEyebrow,
        color = design.palette.textTertiary,
        modifier = modifier.padding(
            horizontal = if (inCard) 0.dp else design.spacing.gutter
        )
    )
}

/** The disclosure that opens the Latin of one part of the office. */
@Composable
private fun LatinToggle(
    showing: Boolean,
    currentLanguage: AppLanguage,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val label = if (showing) {
        currentLanguage.pick("Ocultar Latín", "Hide Latin", "Ocultar Latim")
    } else {
        currentLanguage.pick("Ver Latín", "Show Latin", "Ver Latim")
    }
    Text(
        text = caps(label),
        style = styles.sectionEyebrow,
        color = design.palette.accent,
        modifier = modifier
            .heightIn(min = MinTouchTarget)
            .padding(horizontal = design.spacing.gutter)
            .clickable(role = Role.Button, onClick = onToggle)
            // A rubric is a few millimetres tall; the minimum height is what
            // makes it a 48dp target rather than a hairline of type.
            .padding(horizontal = 4.dp, vertical = 12.dp)
            .semantics {
                contentDescription = label
                stateDescription = if (showing) {
                    currentLanguage.pick("Expandido", "Expanded", "Expandido")
                } else {
                    currentLanguage.pick("Contraído", "Collapsed", "Recolhido")
                }
            }
    )
}

/**
 * Divides continuous prose into the stichs a psalter sets one to a line, at the
 * sentence ends — including those that close inside a quotation.
 */
private val StichBreak = Regex("(?<=[.!?»”’'])\\s+")

private fun stichs(text: String): List<String> =
    text.split(StichBreak).map { it.trim() }.filter { it.isNotEmpty() }

/**
 * The two offices of the night as a segmented control, so the choice is
 * visible without opening a menu: at 320dp and 130% text the long ES/PT
 * labels ("Examen ignaciano") can overflow a dropdown, and the kit's
 * segmented control falls back to tabs when its segments cannot hold
 * both labels.
 */
@Composable
private fun OfficeChoice(
    labels: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    title: String
) {
    val design = LocalAppDesign.current
    Spacer(Modifier.height(design.spacing.cardGap / 2))
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .testTag("night_office_picker")
    ) {
        AppSegmentedTabs(
            options = labels,
            selectedIndex = selectedIndex,
            onSelect = onSelect,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = title }
        )
    }
}

/**
 * Holds long-form text to the identity's reading measure and centres it, so a
 * wide screen reads as the column of a book instead of a wall of type. The two
 * gutters are added back because what sits inside carries them itself.
 */
@Composable
private fun ReadingColumn(content: @Composable ColumnScope.() -> Unit) {
    val design = LocalAppDesign.current
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(
            Modifier
                .widthIn(max = design.spacing.readingMaxWidth + design.spacing.gutter * 2)
                .fillMaxWidth(),
            content = content
        )
    }
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

/** Vatican sets its labels as inscriptional capitals; the other two do not. */
@Composable
private fun caps(text: String): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) text.uppercase() else text

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
