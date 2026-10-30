package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.audio.RecitationVoiceMode
import com.example.data.models.AppLanguage
import com.example.data.models.RosaryMysteryType
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppBanner
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.ListStyle
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/** A decade is one Our Father bead followed by ten Hail Mary beads. */
private const val HailMarysPerDecade = 10

/** The Our Father bead is cut larger than the ten that follow it. */
private const val LeadBeadScale = 1.5f

/** Ring weight where the identity rules its edges rather than filling them. */
private val BeadHairline = 0.5.dp

/**
 * The Holy Rosary and the Divine Mercy chaplet, with a bead counter and spoken
 * recitation.
 *
 * Everything but the beads is the kit. The counter itself is drawn here because
 * no kit component can express a decade — but it is drawn from tokens, so the
 * bead is a filled circle on glass and an engraved, gold-ringed one on paper.
 */
@Composable
fun RosaryScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    var selectedTab by remember { mutableIntStateOf(0) } // 0 = Santo Rosario, 1 = Coronilla de la Divina Misericordia

    val todayMystery = remember { repository.getTodayRosaryMystery() }

    // Rosary State
    var selectedMysteryType by remember { mutableStateOf(todayMystery.type) }
    var currentDecadeIndex by remember { mutableIntStateOf(0) } // 0..4 (Decade 1..5)
    var currentBeadInDecade by remember { mutableIntStateOf(0) } // 0 = Our Father (Large bead), 1..10 = Hail Mary beads

    // Divine Mercy State
    var mercyStepIndex by remember { mutableIntStateOf(0) }

    val isPlayingAudio by ttsManager.isPlaying.collectAsState()
    val voiceMode by ttsManager.voiceMode.collectAsState()
    val currentlySpeaking by ttsManager.currentlySpeaking.collectAsState()

    val currentMystery = repository.getRosaryMystery(selectedMysteryType)
    val currentDecade = currentMystery.decades[currentDecadeIndex]
    val mercySteps = repository.divineMercySteps
    val activeStep = mercySteps[mercyStepIndex]

    fun strings(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    fun caps(text: String) = if (design.decor.usesAllCapsLabels) text.uppercase() else text

    // The Our Father is said on the large bead; the Hail Mary on the ten that
    // follow. One pair of texts serves both the page and the narration, so what
    // is read and what is spoken can never drift apart.
    val leaderText: String
    val responseText: String
    if (currentBeadInDecade == 0) {
        leaderText = when (currentLanguage) {
            AppLanguage.ES -> "Padre nuestro, que estás en el cielo, santificado sea tu Nombre; venga a nosotros tu Reino; hágase tu voluntad en la tierra como en el cielo."
            AppLanguage.EN -> "Our Father, who art in heaven, hallowed be thy name; thy kingdom come; thy will be done on earth as it is in heaven."
            AppLanguage.PT -> "Pai nosso, que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade assim na terra como no céu."
        }
        responseText = when (currentLanguage) {
            AppLanguage.ES -> "Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación, y líbranos del mal. Amén."
            AppLanguage.EN -> "Give us this day our daily bread; and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen."
            AppLanguage.PT -> "O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém."
        }
    } else {
        leaderText = currentDecade.leaderPrayer.get(currentLanguage)
        responseText = currentDecade.congregationResponse.get(currentLanguage)
    }

    fun triggerAudioForCurrentBead() {
        ttsManager.speakDialogue(leaderText, responseText)
    }

    val playLabel = strings("Rezar en voz alta", "Pray aloud", "Rezar em voz alta")
    val pauseLabel = strings("Pausar", "Pause", "Pausar")
    val previousLabel = strings("Anterior", "Back", "Anterior")
    val nextLabel = strings("Siguiente", "Next", "Próximo")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("rosary_screen")
    ) {
        item {
            // The masthead names the screen and holds still; the segmented
            // control below it names the two devotions it carries.
            AppScreenTitle(
                title = strings("Rosario y coronilla", "Rosary & chaplet", "Terço e coroinha"),
                subtitle = if (selectedTab == 0) {
                    strings(
                        "Cinco décadas, cinco misterios y una meditación para cada uno.",
                        "Five decades, five mysteries and a meditation for each one.",
                        "Cinco dezenas, cinco mistérios e uma meditação para cada um."
                    )
                } else {
                    strings(
                        "La coronilla que el Señor dictó a Santa Faustina, paso a paso.",
                        "The chaplet the Lord dictated to Saint Faustina, step by step.",
                        "A coroinha que o Senhor ditou a Santa Faustina, passo a passo."
                    )
                }
            )
        }

        item {
            // The inset-grouped identity expects a segmented control to sit in
            // the gutter; the other two inset it themselves.
            val tabsModifier = if (design.decor.listStyle == ListStyle.INSET_GROUPED) {
                Modifier.padding(horizontal = design.spacing.gutter)
            } else {
                Modifier
            }
            AppSegmentedTabs(
                options = listOf(
                    strings("El Santo Rosario", "The Holy Rosary", "O Santo Terço"),
                    strings("Divina Misericordia", "Divine Mercy", "Divina Misericórdia")
                ),
                selectedIndex = selectedTab,
                onSelect = { selectedTab = it },
                modifier = tabsModifier
            )
        }

        when (selectedTab) {
            0 -> {
                // Holy Rosary Recitation
                item {
                    Spacer(Modifier.height(design.spacing.sectionGap / 2))
                    AppBanner(
                        title = strings("Misterios de hoy", "Today's mysteries", "Mistérios de hoje"),
                        body = todayMystery.type.displayName.get(currentLanguage) +
                            " · " + todayMystery.type.recommendedDays.get(currentLanguage),
                        onClick = {
                            selectedMysteryType = todayMystery.type
                            currentDecadeIndex = 0
                            currentBeadInDecade = 0
                        }
                    )
                }

                item {
                    AppSectionHeader(
                        title = strings(
                            "Selecciona el misterio",
                            "Select the mystery",
                            "Selecione o mistério"
                        )
                    )
                    AppListGroup {
                        RosaryMysteryType.entries.forEach { mType ->
                            val isToday = mType == todayMystery.type
                            AppListRow(
                                title = mType.displayName.get(currentLanguage),
                                subtitle = mType.recommendedDays.get(currentLanguage),
                                leadingIcon = when (mType) {
                                    RosaryMysteryType.JOYFUL -> Icons.Default.LocalFlorist
                                    RosaryMysteryType.SORROWFUL -> Icons.Default.WaterDrop
                                    RosaryMysteryType.GLORIOUS -> Icons.Default.AutoAwesome
                                    RosaryMysteryType.LUMINOUS -> Icons.Default.LightMode
                                },
                                trailingText = if (isToday) {
                                    strings("Hoy", "Today", "Hoje")
                                } else {
                                    null
                                },
                                showsNavigationAffordance = false,
                                selected = mType == selectedMysteryType,
                                onClick = {
                                    selectedMysteryType = mType
                                    currentDecadeIndex = 0
                                    currentBeadInDecade = 0
                                }
                            )
                        }
                    }
                }

                // Interactive bead counter
                item {
                    AppOrnament()
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        CardHeading(
                            eyebrow = currentMystery.type.displayName.get(currentLanguage),
                            detail = strings(
                                "Década ${currentDecadeIndex + 1} de ${currentMystery.decades.size}",
                                "Decade ${currentDecadeIndex + 1} of ${currentMystery.decades.size}",
                                "Dezena ${currentDecadeIndex + 1} de ${currentMystery.decades.size}"
                            )
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))

                        BeadRail(
                            beadCount = HailMarysPerDecade + 1,
                            activeIndex = currentBeadInDecade,
                            hasLeadBead = true,
                            onSelectBead = { currentBeadInDecade = it },
                            modifier = Modifier.testTag("prayer_bead_counter")
                        )

                        Spacer(Modifier.height(design.spacing.cardGap))

                        Text(
                            text = if (currentBeadInDecade == 0) {
                                strings("Padre Nuestro", "Our Father", "Pai-Nosso")
                            } else {
                                strings(
                                    "Ave María $currentBeadInDecade de $HailMarysPerDecade",
                                    "Hail Mary $currentBeadInDecade of $HailMarysPerDecade",
                                    "Ave-Maria $currentBeadInDecade de $HailMarysPerDecade"
                                )
                            },
                            style = styles.rowSubtitle,
                            color = palette.textSecondary,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))

                        RecitationControls(
                            previousLabel = previousLabel,
                            nextLabel = nextLabel,
                            audioLabel = if (isPlayingAudio) pauseLabel else playLabel,
                            isPlayingAudio = isPlayingAudio,
                            canGoPrevious = currentBeadInDecade > 0 || currentDecadeIndex > 0,
                            canGoNext = currentBeadInDecade < HailMarysPerDecade ||
                                currentDecadeIndex < currentMystery.decades.lastIndex,
                            onPrevious = {
                                if (currentBeadInDecade > 0) {
                                    currentBeadInDecade--
                                } else if (currentDecadeIndex > 0) {
                                    currentDecadeIndex--
                                    currentBeadInDecade = HailMarysPerDecade
                                }
                            },
                            onNext = {
                                if (currentBeadInDecade < HailMarysPerDecade) {
                                    currentBeadInDecade++
                                } else if (currentDecadeIndex < currentMystery.decades.lastIndex) {
                                    currentDecadeIndex++
                                    currentBeadInDecade = 0
                                }
                            },
                            onToggleAudio = {
                                if (isPlayingAudio) {
                                    ttsManager.stop()
                                } else {
                                    triggerAudioForCurrentBead()
                                }
                            },
                            previousTag = "prev_bead_button",
                            nextTag = "next_bead_button",
                            audioTag = "toggle_prayer_audio_button"
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))
                        AppDivider()
                        Spacer(Modifier.height(design.spacing.cardGap))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.RecordVoiceOver,
                                contentDescription = null,
                                tint = palette.textTertiary,
                                modifier = Modifier.size(design.decor.rowIconSize * 0.7f)
                            )
                            Spacer(Modifier.width(design.spacing.cardGap / 2))
                            Text(
                                text = caps(strings("Modo de voz", "Voice mode", "Modo de voz")),
                                style = styles.sectionEyebrow,
                                color = palette.textSecondary
                            )
                        }

                        Spacer(Modifier.height(design.spacing.cardGap / 2))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
                        ) {
                            AppChip(
                                label = strings("Diálogo dual", "Dual dialogue", "Diálogo duplo"),
                                selected = voiceMode == RecitationVoiceMode.DUAL_DIALOGUE,
                                onClick = { ttsManager.setVoiceMode(RecitationVoiceMode.DUAL_DIALOGUE) }
                            )
                            AppChip(
                                label = strings("Voz guía", "Leader voice", "Voz guia"),
                                selected = voiceMode == RecitationVoiceMode.MALE,
                                onClick = { ttsManager.setVoiceMode(RecitationVoiceMode.MALE) }
                            )
                            AppChip(
                                label = strings("Respuesta", "Response", "Resposta"),
                                selected = voiceMode == RecitationVoiceMode.FEMALE,
                                onClick = { ttsManager.setVoiceMode(RecitationVoiceMode.FEMALE) }
                            )
                        }
                    }
                }

                // Current decade: meditation and fruit of the mystery
                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        CardHeading(
                            eyebrow = strings(
                                "${currentDecade.number}º Misterio",
                                "Mystery ${currentDecade.number}",
                                "${currentDecade.number}º Mistério"
                            ),
                            detail = currentDecade.scriptureRef
                        )

                        Spacer(Modifier.height(design.spacing.cardGap / 2))

                        Text(
                            text = currentDecade.title.get(currentLanguage),
                            style = styles.sectionTitle,
                            color = palette.textPrimary
                        )

                        Spacer(Modifier.height(design.spacing.cardGap))

                        Text(
                            text = currentDecade.meditation.get(currentLanguage),
                            style = styles.quote,
                            color = palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))
                        AppDivider()
                        Spacer(Modifier.height(design.spacing.cardGap))

                        Text(
                            text = caps(
                                strings(
                                    "Fruto del misterio",
                                    "Fruit of the mystery",
                                    "Fruto do mistério"
                                )
                            ),
                            style = styles.sectionEyebrow,
                            color = palette.textTertiary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap / 3))
                        Text(
                            text = currentDecade.fruitOfMystery.get(currentLanguage),
                            style = styles.rowTitle,
                            color = palette.accent
                        )
                    }
                }

                // The dialogue said on the current bead
                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        CardHeading(
                            eyebrow = if (currentBeadInDecade == 0) {
                                strings(
                                    "Oración del Padre Nuestro",
                                    "The Lord's Prayer",
                                    "Oração do Pai-Nosso"
                                )
                            } else {
                                strings(
                                    "Oración del Ave María",
                                    "The Hail Mary",
                                    "Oração da Ave-Maria"
                                )
                            },
                            detail = null
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        PrayerDialogue(
                            leaderText = leaderText,
                            responseText = responseText,
                            spokenNow = currentlySpeaking
                        )
                    }
                }

                item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
            }

            1 -> {
                // Divine Mercy Chaplet
                item {
                    Spacer(Modifier.height(design.spacing.sectionGap / 2))
                    AppBanner(
                        title = "3:00 PM",
                        body = strings(
                            "La Hora de la Gran Misericordia",
                            "The Hour of Great Mercy",
                            "A Hora da Grande Misericórdia"
                        )
                    )
                }

                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        Text(
                            text = strings(
                                "«A las tres de la tarde implora mi misericordia… En esta hora nada le será negado al alma que lo pida».",
                                "“At three o'clock, implore My mercy… In this hour nothing will be denied the soul that makes a request of Me.”",
                                "«Às três horas da tarde, implora a minha misericórdia… Nesta hora, nada será negado à alma que o pedir»."
                            ),
                            style = styles.quote,
                            color = palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = caps(
                                strings(
                                    "Diario de Santa Faustina",
                                    "Diary of Saint Faustina",
                                    "Diário de Santa Faustina"
                                )
                            ),
                            style = styles.sectionEyebrow,
                            color = palette.textTertiary
                        )
                    }
                }

                // Progress across the steps of the chaplet
                item {
                    AppOrnament()
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        CardHeading(
                            eyebrow = strings("Coronilla", "Chaplet", "Coroinha"),
                            detail = strings(
                                "Paso ${mercyStepIndex + 1} de ${mercySteps.size}",
                                "Step ${mercyStepIndex + 1} of ${mercySteps.size}",
                                "Passo ${mercyStepIndex + 1} de ${mercySteps.size}"
                            )
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))

                        BeadRail(
                            beadCount = mercySteps.size,
                            activeIndex = mercyStepIndex,
                            hasLeadBead = false,
                            onSelectBead = { mercyStepIndex = it },
                            modifier = Modifier.testTag("mercy_step_counter")
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))

                        RecitationControls(
                            previousLabel = previousLabel,
                            nextLabel = nextLabel,
                            audioLabel = if (isPlayingAudio) pauseLabel else playLabel,
                            isPlayingAudio = isPlayingAudio,
                            canGoPrevious = mercyStepIndex > 0,
                            canGoNext = mercyStepIndex < mercySteps.size - 1,
                            onPrevious = { if (mercyStepIndex > 0) mercyStepIndex-- },
                            onNext = {
                                if (mercyStepIndex < mercySteps.size - 1) mercyStepIndex++
                            },
                            onToggleAudio = {
                                if (isPlayingAudio) {
                                    ttsManager.stop()
                                } else {
                                    ttsManager.speakDialogue(
                                        activeStep.leaderText.get(currentLanguage),
                                        activeStep.responseText.get(currentLanguage)
                                    )
                                }
                            },
                            previousTag = "prev_mercy_step_button",
                            nextTag = "next_mercy_step_button",
                            audioTag = "toggle_mercy_audio_button"
                        )
                    }
                }

                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        Text(
                            text = activeStep.title.get(currentLanguage),
                            style = styles.sectionTitle,
                            color = palette.textPrimary
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        PrayerDialogue(
                            leaderText = activeStep.leaderText.get(currentLanguage),
                            responseText = activeStep.responseText.get(currentLanguage),
                            spokenNow = currentlySpeaking
                        )
                    }
                }

                item {
                    AppSectionHeader(
                        title = strings(
                            "Pasos de la coronilla",
                            "Steps of the chaplet",
                            "Passos da coroinha"
                        )
                    )
                    AppListGroup {
                        mercySteps.forEachIndexed { index, step ->
                            AppListRow(
                                title = step.title.get(currentLanguage),
                                trailingText = "${index + 1}",
                                showsNavigationAffordance = false,
                                selected = index == mercyStepIndex,
                                onClick = { mercyStepIndex = index }
                            )
                        }
                    }
                }

                item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
            }
        }
    }
}

/**
 * One decade of a rosary, or the run of steps of a chaplet.
 *
 * The idiom follows the tokens rather than a flag: where the identity rules its
 * edges (`cardBorderWidth`) the bead is an unfilled ring of leaf and only the
 * current one is inked solid; where it fills them, the bead is a clean circle
 * tinted by its state.
 */
@Composable
private fun BeadRail(
    beadCount: Int,
    activeIndex: Int,
    hasLeadBead: Boolean,
    onSelectBead: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val haptics = LocalHapticFeedback.current

    val engraved = design.depth.cardBorderWidth > 0.dp
    val ringWidth = maxOf(
        design.depth.cardBorderWidth,
        if (design.depth.usesHairlines) BeadHairline else 0.dp
    )

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val gap = design.spacing.cardGap / 3
        val slots = beadCount + (if (hasLeadBead) LeadBeadScale - 1f else 0f)
        val bead = ((maxWidth - gap * (beadCount - 1)) / slots)
            .coerceIn(0.dp, design.spacing.rowMinHeight / 2)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(gap, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(beadCount) { index ->
                val isActive = index == activeIndex
                val isTold = index < activeIndex
                val fill by animateColorAsState(
                    targetValue = when {
                        isActive -> palette.accent
                        engraved -> Color.Transparent
                        isTold -> palette.accent.copy(alpha = 0.32f)
                        else -> palette.selectionTint
                    },
                    animationSpec = tween(
                        durationMillis = design.motion.fastMillis,
                        easing = design.motion.standardEasing
                    ),
                    label = "beadFill"
                )
                val ring = when {
                    engraved && (isActive || isTold) -> palette.gold
                    engraved -> palette.gold.copy(alpha = 0.45f)
                    design.depth.usesHairlines && !isActive -> palette.hairline
                    else -> Color.Transparent
                }
                Box(
                    Modifier
                        .size(if (hasLeadBead && index == 0) bead * LeadBeadScale else bead)
                        .clip(CircleShape)
                        .background(fill)
                        .border(ringWidth, ring, CircleShape)
                        .clickable {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            onSelectBead(index)
                        }
                )
            }
        }
    }
}

/**
 * Navigation across the beads and the narration toggle.
 *
 * Stacked rather than set three-across: "Siguiente" and "Próximo" in Roman
 * capitals are wide enough to break a three-button row on a narrow screen.
 */
@Composable
private fun RecitationControls(
    previousLabel: String,
    nextLabel: String,
    audioLabel: String,
    isPlayingAudio: Boolean,
    canGoPrevious: Boolean,
    canGoNext: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onToggleAudio: () -> Unit,
    previousTag: String,
    nextTag: String,
    audioTag: String
) {
    val design = LocalAppDesign.current
    val haptics = LocalHapticFeedback.current

    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
        ) {
            AppSecondaryButton(
                label = previousLabel,
                onClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onPrevious()
                },
                enabled = canGoPrevious,
                leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
                modifier = Modifier
                    .weight(1f)
                    .testTag(previousTag)
            )
            AppSecondaryButton(
                label = nextLabel,
                onClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onNext()
                },
                enabled = canGoNext,
                leadingIcon = Icons.AutoMirrored.Filled.ArrowForward,
                modifier = Modifier
                    .weight(1f)
                    .testTag(nextTag)
            )
        }

        Spacer(Modifier.height(design.spacing.cardGap / 2))

        AppPrimaryButton(
            label = audioLabel,
            onClick = {
                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                onToggleAudio()
            },
            leadingIcon = if (isPlayingAudio) Icons.Default.Pause else Icons.Default.PlayArrow,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(audioTag)
        )
    }
}

/** Leader and response, with whichever line the voice is on set in rubric. */
@Composable
private fun PrayerDialogue(
    leaderText: String,
    responseText: String,
    spokenNow: String?
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    Column(Modifier.widthIn(max = design.spacing.readingMaxWidth)) {
        Text(
            text = "V/. $leaderText",
            style = styles.quote,
            color = if (spokenNow == leaderText) palette.accent else palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = "R/. $responseText",
            style = styles.quote,
            color = if (spokenNow == responseText) palette.accent else palette.textSecondary
        )
    }
}

/** An eyebrow over a card, with its reference or count on the line below. */
@Composable
private fun CardHeading(eyebrow: String, detail: String?) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column {
        Text(
            text = if (design.decor.usesAllCapsLabels) eyebrow.uppercase() else eyebrow,
            style = styles.sectionEyebrow,
            color = design.palette.textSecondary
        )
        if (detail != null) {
            Spacer(Modifier.height(design.spacing.cardGap / 3))
            Text(
                text = detail,
                style = styles.rowSubtitle,
                color = design.palette.textTertiary
            )
        }
    }
}
