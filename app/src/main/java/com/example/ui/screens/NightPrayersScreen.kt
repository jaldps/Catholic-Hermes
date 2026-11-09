package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.LiturgicalSeason
import com.example.data.models.AppLanguage
import com.example.data.models.ComplineOffice
import com.example.data.models.IgnatianExamenStep
import com.example.data.models.VaticanPrayer
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppHideTabBar
import com.example.ui.design.AppImageCard
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppKitChip
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.AppTransportBar
import com.example.ui.design.appScreenInsets
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.design.rememberFirstAsset
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/*
 * The night office — a calm nocturne.
 *
 * The Night segment opens on a choice of three offices (Compline, the
 * Ignatian examen, the traditional night prayer), each a reader of its own:
 * Friedrich's moonlit cross misted into the canvas, the reader font at its
 * loosest, no ornaments, no accent ink. Compline reads as one continuous
 * page; the examen and the traditional prayer are guided one step at a time
 * with the same transport as the Rosary.
 */

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

/** Friedrich's "Cross beside the Baltic" — the night's one image. */
private const val NightArt = "heroes/lumen/night.jpg"
private const val NightArtFallback = "heroes/candle_prayer.jpg"

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
 * Compline, the Ignatian examen and the traditional night prayer.
 *
 * [openOffice] is the office whose reader is open (0 = Compline, 1 = Examen,
 * 2 = Traditional, -1 = the overview); the host holds it so a deep link can
 * open a reader directly and the back button returns to the overview.
 */
@Composable
fun NightPrayersScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    /** The host screen's title and view switcher, set as the first rows. */
    header: LazyListScope.() -> Unit = {},
    openOffice: Int = -1,
    onOpenOffice: (Int) -> Unit = {}
) {
    val compline = repository.complineOffice
    val examenSteps = repository.ignatianExamenSteps
    val prayers = repository.vaticanPrayers
    val isPlayingAudio by ttsManager.isPlaying.collectAsState()

    // Progress through the guided offices survives a trip back to the overview.
    var examenDone by rememberSaveable { mutableStateOf(listOf<Int>()) }
    var examenIndex by rememberSaveable { mutableIntStateOf(0) }
    var traditionalDone by rememberSaveable { mutableStateOf(listOf<Int>()) }
    var traditionalIndex by rememberSaveable { mutableIntStateOf(0) }

    val traditionalSteps = traditionalSteps(prayers, currentLanguage)
    val seasonalAntiphon = remember(compline) { seasonalAntiphonIndex(compline) }

    Box(
        modifier
            .fillMaxSize()
            .testTag("night_prayers_screen")
    ) {
        Crossfade(targetState = openOffice, animationSpec = tween(300), label = "nightOffice") { office ->
            when (office) {
                0 -> {
                    AppBackHandler { onOpenOffice(-1) }
                    ComplineView(compline, ttsManager, isPlayingAudio, currentLanguage, seasonalAntiphon)
                }
                1 -> {
                    AppBackHandler { onOpenOffice(-1) }
                    GuidedOfficeView(
                        title = currentLanguage.pick("Examen ignaciano", "Ignatian examen", "Exame inaciano"),
                        eyebrow = currentLanguage.pick("Examen de conciencia", "Examination of conscience", "Exame de consciência"),
                        intro = currentLanguage.pick(
                            "Cinco pasos de San Ignacio para revisar el día ante Dios.",
                            "St. Ignatius's five steps to review the day before God.",
                            "Cinco passos de Santo Inácio para rever o dia diante de Deus."
                        ),
                        steps = examenSteps.map {
                            GuidedStep(
                                title = stripStepNumber(it.title.get(currentLanguage)),
                                description = it.description.get(currentLanguage),
                                prayer = it.guidedPrayer.get(currentLanguage),
                                guidance = null,
                                number = it.stepNumber
                            )
                        },
                        done = examenDone.toSet(),
                        onDone = { examenDone = it.toList() },
                        selectedIndex = examenIndex,
                        onSelect = { examenIndex = it },
                        progressLabel = currentLanguage.pick("Progreso del examen", "Examen progress", "Progresso do exame"),
                        completionTitle = currentLanguage.pick("Examen completado", "Examen complete", "Exame concluído"),
                        completionText = currentLanguage.pick(
                            "Descansa en la paz y el perdón de Nuestro Señor Jesucristo. Que la Virgen María y los santos Ángeles custodien tu sueño.",
                            "Rest in the peace and the forgiveness of Our Lord Jesus Christ. May the Virgin Mary and the holy Angels keep watch over your sleep.",
                            "Descansa na paz e no perdão de Nosso Senhor Jesus Cristo. Que a Virgem Maria e os santos Anjos guardem o teu sono."
                        ),
                        ttsTag = "examen_tts_button",
                        rootTag = "night_examen_reader",
                        ttsManager = ttsManager,
                        isNarrating = isPlayingAudio,
                        currentLanguage = currentLanguage
                    )
                }
                2 -> {
                    AppBackHandler { onOpenOffice(-1) }
                    GuidedOfficeView(
                        title = currentLanguage.pick("Oración tradicional", "Traditional prayer", "Oração tradicional"),
                        eyebrow = currentLanguage.pick("Oración de la noche", "Night prayer", "Oração da noite"),
                        intro = currentLanguage.pick(
                            "Nueve oraciones sencillas antes de dormir, de la señal de la cruz a la acción de gracias por el día.",
                            "Nine simple prayers before sleep, from the sign of the cross to thanks for the day.",
                            "Nove orações simples antes de dormir, do sinal da cruz à ação de graças pelo dia."
                        ),
                        steps = traditionalSteps,
                        done = traditionalDone.toSet(),
                        onDone = { traditionalDone = it.toList() },
                        selectedIndex = traditionalIndex,
                        onSelect = { traditionalIndex = it },
                        progressLabel = currentLanguage.pick("Progreso de la oración", "Prayer progress", "Progresso da oração"),
                        completionTitle = currentLanguage.pick("Oración completada", "Prayer complete", "Oração concluída"),
                        completionText = currentLanguage.pick(
                            "Descansa en la paz del Señor. Que la Virgen María y los santos Ángeles custodien tu sueño.",
                            "Rest in the peace of the Lord. May the Virgin Mary and the holy Angels keep watch over your sleep.",
                            "Descansa na paz do Senhor. Que a Virgem Maria e os santos Anjos guardem o teu sono."
                        ),
                        ttsTag = "tradicional_tts_button",
                        rootTag = "night_traditional_reader",
                        ttsManager = ttsManager,
                        isNarrating = isPlayingAudio,
                        currentLanguage = currentLanguage
                    )
                }
                else -> NightOverview(
                    header = header,
                    compline = compline,
                    seasonalAntiphon = seasonalAntiphon,
                    examenTotal = examenSteps.size,
                    examenDone = examenDone.size,
                    traditionalTotal = traditionalSteps.size,
                    traditionalDone = traditionalDone.size,
                    currentLanguage = currentLanguage,
                    onOpen = onOpenOffice
                )
            }
        }
    }
}

/** Regina Cæli in Eastertide, Salve Regina otherwise. */
private fun seasonalAntiphonIndex(compline: ComplineOffice): Int {
    val season = runCatching { LiturgicalCalendar.dayInfo(LiturgicalCalendar.today()).season }.getOrNull()
    val antiphons = compline.marianAntiphons
    val index = if (season == LiturgicalSeason.EASTER) {
        antiphons.indexOfFirst { it.titleLatin.startsWith("Regina", ignoreCase = true) }
    } else {
        antiphons.indexOfFirst { it.titleLatin.startsWith("Salve", ignoreCase = true) }
    }
    return index.coerceAtLeast(0)
}

private fun stripStepNumber(title: String): String = title.replace(Regex("^\\s*\\d+\\s*[.)]\\s*"), "")

// ------------------------------------------------------------------ overview

@Composable
private fun NightOverview(
    header: LazyListScope.() -> Unit,
    compline: ComplineOffice,
    seasonalAntiphon: Int,
    examenTotal: Int,
    examenDone: Int,
    traditionalTotal: Int,
    traditionalDone: Int,
    currentLanguage: AppLanguage,
    onOpen: (Int) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val antiphon = compline.marianAntiphons.getOrNull(seasonalAntiphon)
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = appScreenInsets().calculateBottomPadding() + 32.dp)
    ) {
        header()
        item(key = "night_offices") {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .testTag("night_office_picker")
                    .semantics { contentDescription = currentLanguage.pick("Oficio", "Office", "Ofício") }
            ) {
                Box(
                    Modifier
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("screen_header_illustration")
                ) {
                    AppImageCard(
                        title = currentLanguage.pick("Completas", "Compline", "Completas"),
                        eyebrow = currentLanguage.pick("Liturgia de las Horas", "Liturgy of the Hours", "Liturgia das Horas"),
                        meta = listOfNotNull(
                            currentLanguage.pick("8 partes", "8 parts", "8 partes"),
                            antiphon?.titleLatin
                        ).joinToString(" · "),
                        onClick = { onOpen(0) },
                        assetPath = rememberFirstAsset(NightArt, NightArtFallback),
                        aspectRatio = 1.3f,
                        tag = "night_office_compline"
                    )
                }
                Spacer(Modifier.height(12.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OfficeTile(
                        title = currentLanguage.pick("Examen ignaciano", "Ignatian examen", "Exame inaciano"),
                        meta = officeMeta(examenDone, examenTotal, currentLanguage),
                        icon = Icons.Outlined.NightsStay,
                        onClick = { onOpen(1) },
                        tag = "night_office_examen",
                        modifier = Modifier.weight(1f)
                    )
                    OfficeTile(
                        title = currentLanguage.pick("Oración tradicional", "Traditional prayer", "Oração tradicional"),
                        meta = officeMeta(traditionalDone, traditionalTotal, currentLanguage),
                        icon = Icons.Outlined.Bedtime,
                        onClick = { onOpen(2) },
                        tag = "night_office_traditional",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        if (antiphon != null) {
            item(key = "antiphon") {
                PrayerSectionHeader(currentLanguage.pick("Antífona a la Virgen", "Antiphon to Our Lady", "Antífona a Nossa Senhora"))
                AppGlassSurface(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("night_antiphon_card"),
                    onClick = { onOpen(0) },
                    contentPadding = PaddingValues(design.spacing.cardPadding)
                ) {
                    Column {
                        Text(antiphon.seasonTag.get(currentLanguage), style = styles.footnote, color = p.textSecondary)
                        Spacer(Modifier.height(6.dp))
                        Text(antiphon.titleLatin, style = styles.title2, color = p.textPrimary)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            antiphon.translatedText.get(currentLanguage),
                            style = styles.reading.copy(fontStyle = FontStyle.Italic),
                            color = p.textSecondary,
                            maxLines = 3,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

private fun officeMeta(done: Int, total: Int, language: AppLanguage): String =
    if (done == 0) language.pick("$total pasos", "$total steps", "$total passos")
    else language.pick("$done de $total", "$done of $total", "$done de $total")

@Composable
private fun OfficeTile(
    title: String,
    meta: String,
    icon: ImageVector,
    onClick: () -> Unit,
    tag: String,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    AppGlassSurface(
        modifier
            .heightIn(min = 136.dp)
            .testTag(tag),
        shape = design.shapes.listGroup,
        onClick = onClick,
        contentPadding = PaddingValues(18.dp)
    ) {
        Column(Modifier.fillMaxWidth().heightIn(min = 100.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Icon(icon, null, tint = p.textPrimary, modifier = Modifier.size(26.dp))
            Column {
                Text(title, style = styles.headline, color = p.textPrimary)
                Spacer(Modifier.height(2.dp))
                Text(meta, style = styles.footnote, color = p.textSecondary)
            }
        }
    }
}

// -------------------------------------------------------------------- compline

@Composable
private fun ComplineView(
    compline: ComplineOffice,
    ttsManager: LiturgicalTtsManager,
    isNarrating: Boolean,
    currentLanguage: AppLanguage,
    seasonalAntiphon: Int
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val title = currentLanguage.pick("Completas", "Compline", "Completas")
    AppInlineTitle(title)
    AppHideTabBar()
    var showReading by remember { mutableStateOf(false) }

    var selectedMarianAntiphonIndex by rememberSaveable { mutableIntStateOf(seasonalAntiphon) }
    // Held per part, so scrolling a part out of the lazy window keeps its Latin open.
    var latinParts by rememberSaveable { mutableStateOf(listOf<Int>()) }
    fun toggleLatin(part: Int) {
        latinParts = if (part in latinParts) latinParts - part else latinParts + part
    }
    val currentAntiphon = compline.marianAntiphons.getOrNull(selectedMarianAntiphonIndex)
        ?: compline.marianAntiphons.firstOrNull()

    // The whole office, in order, for the voice.
    val officeText = buildString {
        listOf(
            compline.openingVerseTranslation.get(currentLanguage),
            compline.confiteorText.get(currentLanguage),
            compline.hymnTranslation.get(currentLanguage),
            compline.psalmAntiphon.get(currentLanguage),
            compline.psalmText.get(currentLanguage),
            compline.readingText.get(currentLanguage),
            compline.responsoryTranslation.get(currentLanguage),
            compline.canticleAntiphon.get(currentLanguage),
            compline.canticleOfSimeonTranslation.get(currentLanguage),
            compline.concludingPrayer.get(currentLanguage),
            compline.blessing.get(currentLanguage),
            currentAntiphon?.translatedText?.get(currentLanguage).orEmpty()
        ).filter { it.isNotBlank() }.forEach {
            append(it.replace(Regex("[VR]/\\.\\s*"), ""))
            append("\n\n")
        }
    }.trim()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .testTag("night_compline_reader"),
        contentPadding = PaddingValues(bottom = appScreenInsets().calculateBottomPadding() + 48.dp)
    ) {
        item(key = "header") {
            AppDetailHeader(
                title = title,
                assetPath = rememberFirstAsset(NightArt, NightArtFallback),
                eyebrow = currentLanguage.pick("Oración de la noche", "Night prayer", "Oração da noite"),
                meta = currentLanguage.pick(
                    "Liturgia oficial de la Iglesia antes del reposo de la noche",
                    "The Church's official liturgy before the night's rest",
                    "Liturgia oficial da Igreja antes do repouso da noite"
                ),
                imageHeight = 340.dp
            ) {
                AppGlassIconButton(
                    icon = Icons.Outlined.TextFields,
                    contentDescription = currentLanguage.pick("Tamaño del texto", "Text size", "Tamanho do texto"),
                    onClick = { showReading = true },
                    onImage = true,
                    tag = "compline_text_settings"
                )
            }
        }
        item(key = "listen") {
            Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                AppSecondaryCapsule(
                    label = if (isNarrating) currentLanguage.pick("Detener", "Stop", "Parar")
                    else currentLanguage.pick("Escuchar el oficio", "Listen to the office", "Ouvir o ofício"),
                    onClick = { if (isNarrating) ttsManager.stop() else ttsManager.speakSingle(officeText) },
                    icon = if (isNarrating) Icons.Filled.Stop else Icons.Outlined.Headphones,
                    tag = "compline_tts_button",
                    modifier = Modifier.semantics {
                        stateDescription = if (isNarrating) currentLanguage.pick("Narrando", "Narrating", "Narrando")
                        else currentLanguage.pick("Detenido", "Stopped", "Parado")
                    }
                )
                if (isNarrating) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        currentLanguage.pick("Narrando el oficio…", "Reading the office aloud…", "Narrando o ofício…"),
                        style = styles.footnote,
                        color = p.textSecondary
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
        }

        item(key = "part1") {
            OfficePart(
                number = 1,
                caption = currentLanguage.pick("Invocación inicial", "Opening versicle", "Invocação inicial"),
                reference = "Deus in adiutórium",
                latinText = compline.openingVerseLatin,
                latinShown = 1 in latinParts,
                onToggleLatin = { toggleLatin(1) },
                currentLanguage = currentLanguage
            ) { LiturgicalText(compline.openingVerseTranslation.get(currentLanguage)) }
        }
        item(key = "part2") {
            OfficePart(
                number = 2,
                caption = currentLanguage.pick("Acto penitencial", "Penitential act", "Ato penitencial"),
                reference = "Confiteor Deo omnipotenti",
                latinText = ConfiteorLatin,
                latinShown = 2 in latinParts,
                onToggleLatin = { toggleLatin(2) },
                currentLanguage = currentLanguage
            ) { LiturgicalText(compline.confiteorText.get(currentLanguage)) }
        }
        item(key = "part3") {
            OfficePart(
                number = 3,
                caption = currentLanguage.pick("Himno de Completas", "Hymn at Compline", "Hino de Completas"),
                reference = compline.hymnLatinTitle,
                latinText = compline.hymnLatin,
                latinShown = 3 in latinParts,
                onToggleLatin = { toggleLatin(3) },
                currentLanguage = currentLanguage
            ) { LiturgicalText(compline.hymnTranslation.get(currentLanguage)) }
        }
        item(key = "part4") {
            OfficePart(
                number = 4,
                caption = currentLanguage.pick("Salmodia de la noche", "Psalmody of the night", "Salmodia da noite"),
                reference = compline.psalmReference,
                latinText = PsalmLatin,
                latinShown = 4 in latinParts,
                onToggleLatin = { toggleLatin(4) },
                currentLanguage = currentLanguage
            ) {
                AntiphonLine(compline.psalmAntiphon.get(currentLanguage))
                Spacer(Modifier.height(16.dp))
                // The bundled psalm is one paragraph: set stich by stich.
                stichs(compline.psalmText.get(currentLanguage)).forEachIndexed { index, stich ->
                    VerseLine(index + 1, stich)
                }
            }
        }
        item(key = "part5") {
            OfficePart(
                number = 5,
                caption = currentLanguage.pick("Lectura breve y responsorio", "Short reading and responsory", "Leitura breve e responsório"),
                reference = compline.readingReference,
                latinText = compline.responsoryLatin,
                latinShown = 5 in latinParts,
                onToggleLatin = { toggleLatin(5) },
                currentLanguage = currentLanguage
            ) {
                LiturgicalText(compline.readingText.get(currentLanguage))
                Spacer(Modifier.height(22.dp))
                Text(
                    currentLanguage.pick("Responsorio breve", "Short responsory", "Responsório breve"),
                    style = styles.footnote,
                    color = p.textSecondary
                )
                Spacer(Modifier.height(8.dp))
                LiturgicalText(compline.responsoryTranslation.get(currentLanguage))
            }
        }
        item(key = "part6") {
            OfficePart(
                number = 6,
                caption = currentLanguage.pick("Cántico evangélico (Nunc Dimittis)", "Gospel canticle (Nunc Dimittis)", "Cântico evangélico (Nunc Dimittis)"),
                reference = "Canticum Simeonis (Lc 2, 29-32)",
                latinText = "${compline.canticleAntiphon.get(currentLanguage)}\n\n" + compline.canticleOfSimeonLatin,
                latinShown = 6 in latinParts,
                onToggleLatin = { toggleLatin(6) },
                currentLanguage = currentLanguage
            ) {
                AntiphonLine(compline.canticleAntiphon.get(currentLanguage))
                Spacer(Modifier.height(16.dp))
                val canticle = compline.canticleOfSimeonTranslation.get(currentLanguage).split("\n\n")
                canticle.first().split("\n").map { it.trim() }.filter { it.isNotEmpty() }
                    .forEachIndexed { index, stich -> VerseLine(NuncDimittisFirstVerse + index, stich) }
                val doxology = canticle.drop(1).joinToString("\n\n").trim()
                if (doxology.isNotEmpty()) {
                    Spacer(Modifier.height(14.dp))
                    LiturgicalText(doxology)
                }
            }
        }
        item(key = "part7") {
            OfficePart(
                number = 7,
                caption = currentLanguage.pick("Oración conclusiva y bendición", "Concluding prayer and blessing", "Oração conclusiva e bênção"),
                reference = "Oratio et Benedictio",
                latinText = ConcludingLatin,
                latinShown = 7 in latinParts,
                onToggleLatin = { toggleLatin(7) },
                currentLanguage = currentLanguage
            ) {
                LiturgicalText(compline.concludingPrayer.get(currentLanguage))
                Spacer(Modifier.height(16.dp))
                LiturgicalText(compline.blessing.get(currentLanguage), muted = true)
            }
        }

        if (currentAntiphon != null) {
            item(key = "part8") {
                PartHeading(
                    number = 8,
                    caption = currentLanguage.pick("Antífona mariana final", "Final Marian antiphon", "Antífona mariana final"),
                    reference = currentAntiphon.seasonTag.get(currentLanguage),
                    trailing = null
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("marian_antiphon_picker"),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    compline.marianAntiphons.forEachIndexed { index, antiphon ->
                        AppKitChip(
                            label = antiphon.titleLatin,
                            selected = index == selectedMarianAntiphonIndex,
                            onClick = { selectedMarianAntiphonIndex = index },
                            modifier = Modifier.testTag("marian_antiphon_$index")
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                AppGlassSurface(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter),
                    contentPadding = PaddingValues(design.spacing.cardPadding)
                ) {
                    Column {
                        Text(currentAntiphon.titleTranslation.get(currentLanguage), style = styles.title2, color = p.textPrimary)
                        Spacer(Modifier.height(14.dp))
                        LiturgicalText(currentAntiphon.latinText, muted = true, latin = true)
                        Spacer(Modifier.height(14.dp))
                        LiturgicalText(currentAntiphon.translatedText.get(currentLanguage))
                        Spacer(Modifier.height(20.dp))
                        AppSecondaryCapsule(
                            label = if (isNarrating) currentLanguage.pick("Detener", "Stop", "Parar")
                            else currentLanguage.pick(
                                "Escuchar ${currentAntiphon.titleLatin}",
                                "Listen to ${currentAntiphon.titleLatin}",
                                "Ouvir ${currentAntiphon.titleLatin}"
                            ),
                            onClick = {
                                if (isNarrating) ttsManager.stop()
                                else ttsManager.speakSingle(currentAntiphon.translatedText.get(currentLanguage))
                            },
                            icon = if (isNarrating) Icons.Filled.Stop else Icons.Outlined.Headphones,
                            tag = "marian_antiphon_tts"
                        )
                    }
                }
            }
        }
    }

    if (showReading) PrayerReadingSheet(currentLanguage) { showReading = false }
}

/** A part's heading: numbered glass circle, caption, reference, optional trailing toggle. */
@Composable
private fun PartHeading(number: Int, caption: String, reference: String?, trailing: (@Composable () -> Unit)?) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(top = 36.dp, bottom = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(32.dp).lumenGlass(CircleShape, lifted = false), contentAlignment = Alignment.Center) {
            Text("$number", style = styles.footnote, color = p.textPrimary)
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(caption, style = styles.headline, color = p.textPrimary, modifier = Modifier.semantics { heading() })
            if (!reference.isNullOrBlank()) {
                Spacer(Modifier.height(2.dp))
                Text(reference, style = styles.footnote.copy(fontStyle = FontStyle.Italic), color = p.textSecondary)
            }
        }
        if (trailing != null) {
            Spacer(Modifier.width(8.dp))
            trailing()
        }
    }
}

/** One part of the office: heading, its Latin on demand, the vernacular text. */
@Composable
private fun OfficePart(
    number: Int,
    caption: String,
    reference: String?,
    latinText: String,
    latinShown: Boolean,
    onToggleLatin: () -> Unit,
    currentLanguage: AppLanguage,
    body: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    PartHeading(number, caption, reference) {
        LatinToggle(showing = latinShown, currentLanguage = currentLanguage, onToggle = onToggleLatin)
    }
    Column(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter)) {
        AnimatedVisibility(
            visible = latinShown,
            enter = expandVertically(tween(260)) + fadeIn(tween(260)),
            exit = shrinkVertically(tween(220)) + fadeOut(tween(160))
        ) {
            Column {
                LiturgicalText(text = latinText, muted = true, latin = true)
                Spacer(Modifier.height(16.dp))
            }
        }
        body()
    }
}

/** The quiet capsule that opens the Latin of one part. */
@Composable
private fun LatinToggle(showing: Boolean, currentLanguage: AppLanguage, onToggle: () -> Unit) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val label = if (showing) currentLanguage.pick("Ocultar Latín", "Hide Latin", "Ocultar Latim")
    else currentLanguage.pick("Ver Latín", "Show Latin", "Ver Latim")
    val shape = RoundedCornerShape(percent = 50)
    Box(
        Modifier
            .heightIn(min = 44.dp)
            .lumenPress(onToggle, 0.95f, Role.Button)
            .semantics {
                contentDescription = label
                stateDescription = if (showing) currentLanguage.pick("Expandido", "Expanded", "Expandido")
                else currentLanguage.pick("Contraído", "Collapsed", "Recolhido")
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .then(if (showing) Modifier.background(p.action, shape) else Modifier.lumenGlass(shape, lifted = false))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(currentLanguage.pick("Latín", "Latin", "Latim"),
                style = styles.caption, color = if (showing) p.onAction else p.textSecondary)
        }
    }
}

/** A psalm or canticle stich with its verse figure in the margin. */
@Composable
private fun VerseLine(number: Int, text: String) {
    val styles = LocalAppTextStyles.current
    val p = LocalAppDesign.current.palette
    Row(Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
        Text(
            "$number",
            style = styles.caption,
            color = p.textSecondary,
            textAlign = TextAlign.End,
            modifier = Modifier.width(24.dp).padding(top = 7.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(withLiturgicalSigla(text, prayerQuietSigil()), style = styles.reading, color = p.textPrimary, modifier = Modifier.weight(1f))
    }
}

/** An antiphon: italic, secondary, set off from the psalm it frames. */
@Composable
private fun AntiphonLine(text: String) {
    val styles = LocalAppTextStyles.current
    val p = LocalAppDesign.current.palette
    Text(
        withLiturgicalSigla(text, prayerQuietSigil()),
        style = styles.reading.copy(fontStyle = FontStyle.Italic),
        color = p.textSecondary
    )
}

/**
 * Liturgical text as the office prints it: a line opening with a versicle or
 * response siglum is one voice of a dialogue (siglum in quiet ink, the
 * response set in), everything else a line of the reading face; blank lines
 * are stanza breaks.
 */
@Composable
private fun LiturgicalText(text: String, muted: Boolean = false, latin: Boolean = false) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val body = if (latin) styles.reading.copy(fontStyle = FontStyle.Italic) else styles.reading
    val ink = if (muted) design.palette.textSecondary else design.palette.textPrimary
    val sigil = prayerQuietSigil()
    Column(Modifier.fillMaxWidth().widthIn(max = design.spacing.readingMaxWidth)) {
        text.split("\n").forEach { rawLine ->
            val line = rawLine.trim()
            if (line.isEmpty()) {
                Spacer(Modifier.height(14.dp))
                return@forEach
            }
            val siglum = Sigla.firstOrNull { line.startsWith(it) }
            Text(
                text = if (siglum == null) withLiturgicalSigla(line, sigil) else buildAnnotatedString {
                    withStyle(sigil) { append(if (siglum == VersicleSigil) VersicleSign else ResponseSign) }
                    append(" ")
                    append(withLiturgicalSigla(line.removePrefix(siglum).trim(), sigil))
                },
                style = body,
                color = ink,
                modifier = Modifier.padding(start = if (siglum == ResponseSigil) 20.dp else 0.dp)
            )
        }
    }
}

/** Continuous prose divided into the stichs a psalter sets one to a line. */
private val StichBreak = Regex("(?<=[.!?»”’'])\\s+")

private fun stichs(text: String): List<String> =
    text.split(StichBreak).map { it.trim() }.filter { it.isNotEmpty() }

// ------------------------------------------------------- guided offices

/** One step of a guided office. */
private data class GuidedStep(
    val title: String,
    val description: String?,
    val prayer: String,
    val guidance: String?,
    val number: Int
)

/**
 * Steps 1-8 are existing Vatican prayer entries by id; step 9, the good-night
 * thanksgiving, has no bundled entry and is carried inline.
 */
private fun traditionalSteps(prayers: List<VaticanPrayer>, language: AppLanguage): List<GuidedStep> {
    fun prayerById(id: String) = prayers.find { it.id == id }
    fun body(prayer: VaticanPrayer): String {
        val leader = prayer.leaderText.get(language)
        val response = prayer.responseText?.get(language)
        // The guardian angel entry's response is a stray fragment; its leader text is complete.
        return if (prayer.id == "guardian_angel" || response.isNullOrBlank()) leader else "$leader\n\n$response"
    }
    val ids = listOf(
        "persignarse" to true, "our_father" to false, "hail_mary" to false, "glory_be" to false,
        "guardian_angel" to false, "bendita_sea_tu_pureza" to false, "signarse" to true, "immaculate_conception" to false
    )
    val steps = ids.mapNotNull { (id, withGuidance) ->
        prayerById(id)?.let {
            GuidedStep(
                title = it.title.get(language),
                description = null,
                prayer = body(it),
                guidance = if (withGuidance) it.whenToPray.get(language) else null,
                number = 0
            )
        }
    } + GuidedStep(
        title = language.pick("Gracias por el día", "Thanks for the day", "Obrigado pelo dia"),
        description = null,
        prayer = language.pick(
            "Gracias te doy buen Señor y alabo tu gran poder, pues con el alma en el cuerpo me has dejado anochecer. Así te pido Señor por tu santísima madre y por el Eterno Padre me dejes amanecer, para poder alabar a Jesús, María y José, por los siglos de los siglos. Amén.",
            "I thank you, good Lord, and I praise your great power, for with my soul in my body you have allowed me to see the nightfall. So I ask you, Lord, through your most holy Mother and through the eternal Father, to let me see the dawn, so that I may praise Jesus, Mary and Joseph, for ever and ever. Amen.",
            "Dou-vos graças, bom Senhor, e louvo o vosso grande poder, pois com a alma no corpo me deixastes chegar ao anoitecer. Assim vos peço, Senhor, por vossa santíssima Mãe e pelo Pai eterno, que me deixeis amanhecer, para poder louvar Jesus, Maria e José, pelos séculos dos séculos. Amém."
        ),
        guidance = null,
        number = 0
    )
    return steps.mapIndexed { i, s -> s.copy(number = i + 1) }
}

/**
 * A guided office (the examen, the traditional prayer): one step on stage at
 * a time with the Rosary's transport, a check to mark it, and the steps below.
 */
@Composable
private fun GuidedOfficeView(
    title: String,
    eyebrow: String,
    intro: String,
    steps: List<GuidedStep>,
    done: Set<Int>,
    onDone: (Set<Int>) -> Unit,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    progressLabel: String,
    completionTitle: String,
    completionText: String,
    ttsTag: String,
    rootTag: String,
    ttsManager: LiturgicalTtsManager,
    isNarrating: Boolean,
    currentLanguage: AppLanguage
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    AppInlineTitle(title)
    AppHideTabBar()
    var showReading by remember { mutableStateOf(false) }
    val index = selectedIndex.coerceIn(0, (steps.size - 1).coerceAtLeast(0))
    val step = steps.getOrNull(index)
    val total = steps.size
    val stepsDoneText = currentLanguage.pick(
        "${done.size} de $total pasos completados",
        "${done.size} of $total steps completed",
        "${done.size} de $total passos concluídos"
    )
    val doneLabel = currentLanguage.pick("Hecho", "Done", "Feito")
    val pendingLabel = currentLanguage.pick("Pendiente", "Pending", "Pendente")

    LazyColumn(
        Modifier
            .fillMaxSize()
            .testTag(rootTag),
        contentPadding = PaddingValues(bottom = appScreenInsets().calculateBottomPadding() + 48.dp)
    ) {
        item(key = "header") {
            AppDetailHeader(
                title = title,
                eyebrow = eyebrow,
                assetPath = rememberFirstAsset(NightArt, NightArtFallback),
                imageHeight = 230.dp
            ) {
                AppGlassIconButton(
                    icon = Icons.Outlined.TextFields,
                    contentDescription = currentLanguage.pick("Tamaño del texto", "Text size", "Tamanho do texto"),
                    onClick = { showReading = true },
                    onImage = true
                )
            }
            Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(intro, style = styles.callout, color = p.textSecondary)
                Spacer(Modifier.height(20.dp))
                AppProgressBar(
                    if (total == 0) 0f else done.size.toFloat() / total,
                    color = if (total > 0 && done.size == total) p.success else null,
                    modifier = Modifier.semantics {
                        contentDescription = progressLabel
                        stateDescription = stepsDoneText
                    }
                )
                Spacer(Modifier.height(8.dp))
                Text(stepsDoneText, style = styles.footnote, color = p.textSecondary)
            }
        }

        if (step != null) {
            item(key = "stage") {
                val isDone = step.number in done
                Spacer(Modifier.height(24.dp))
                AppGlassSurface(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter),
                    contentPadding = PaddingValues(design.spacing.cardPadding)
                ) {
                    Column {
                        Text(
                            currentLanguage.pick("Paso ${step.number} de $total", "Step ${step.number} of $total", "Passo ${step.number} de $total"),
                            style = styles.footnote,
                            color = p.textSecondary
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(step.title, style = styles.title2, color = p.textPrimary, modifier = Modifier.semantics { heading() })
                        if (!step.description.isNullOrBlank()) {
                            Spacer(Modifier.height(10.dp))
                            Text(step.description, style = styles.callout, color = p.textSecondary)
                            Spacer(Modifier.height(18.dp))
                            Text(currentLanguage.pick("Oración sugerida", "Suggested prayer", "Oração sugerida"), style = styles.footnote, color = p.textSecondary)
                        }
                        Spacer(Modifier.height(10.dp))
                        LiturgicalText(step.prayer)
                        if (!step.guidance.isNullOrBlank()) {
                            Spacer(Modifier.height(14.dp))
                            Text(step.guidance, style = styles.footnote, color = p.textSecondary)
                        }
                    }
                }
                Spacer(Modifier.height(22.dp))
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    AppTransportBar(
                        isPlaying = isNarrating,
                        onPlayPause = { if (isNarrating) ttsManager.stop() else ttsManager.speakSingle(step.prayer) },
                        onPrevious = if (index > 0) ({ onSelect(index - 1) }) else null,
                        onNext = if (index < total - 1) ({ onSelect(index + 1) }) else null,
                        previousLabel = currentLanguage.pick("Anterior", "Previous", "Anterior"),
                        nextLabel = currentLanguage.pick("Siguiente", "Next", "Seguinte"),
                        playLabel = currentLanguage.pick("Escuchar", "Listen", "Ouvir"),
                        pauseLabel = currentLanguage.pick("Detener", "Stop", "Parar"),
                        tagPrefix = "${ttsTag}_transport",
                        modifier = Modifier
                            .testTag(ttsTag)
                            .semantics {
                                stateDescription = if (isNarrating) currentLanguage.pick("Narrando", "Narrating", "Narrando")
                                else currentLanguage.pick("Detenido", "Stopped", "Parado")
                            }
                    )
                }
                Spacer(Modifier.height(16.dp))
                Box(Modifier.padding(horizontal = design.spacing.gutter)) {
                    val toggle: () -> Unit = {
                        if (isDone) {
                            onDone(done - step.number)
                        } else {
                            val now = done + step.number
                            onDone(now)
                            // Move on to the next step still to pray.
                            val nextIndex = steps.indexOfFirst { it.number !in now && it.number > step.number }
                                .takeIf { it >= 0 } ?: steps.indexOfFirst { it.number !in now }
                            if (nextIndex >= 0) onSelect(nextIndex)
                        }
                    }
                    if (isDone) {
                        AppSecondaryCapsule(
                            label = currentLanguage.pick("Hecho · Deshacer", "Done · Undo", "Feito · Desfazer"),
                            onClick = toggle,
                            icon = Icons.Filled.Check,
                            fillWidth = true,
                            tag = "${rootTag}_done",
                            modifier = Modifier.semantics { stateDescription = doneLabel }
                        )
                    } else {
                        AppPrimaryCapsule(
                            label = currentLanguage.pick("Marcar como hecho", "Mark as done", "Marcar como feito"),
                            onClick = toggle,
                            icon = Icons.Filled.Check,
                            tag = "${rootTag}_done",
                            modifier = Modifier.semantics { stateDescription = pendingLabel }
                        )
                    }
                }
            }
        }

        if (total > 0 && done.size == total) {
            item(key = "complete") {
                Spacer(Modifier.height(28.dp))
                AppGlassSurface(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("${rootTag}_complete"),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 28.dp)
                ) {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(Modifier.size(40.dp).background(p.success, CircleShape), contentAlignment = Alignment.Center) {
                            Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(22.dp))
                        }
                        Spacer(Modifier.height(14.dp))
                        Text(completionTitle, style = styles.title2, color = p.textPrimary, textAlign = TextAlign.Center,
                            modifier = Modifier.semantics { heading() })
                        Spacer(Modifier.height(10.dp))
                        Text(
                            completionText,
                            style = styles.reading.copy(fontStyle = FontStyle.Italic),
                            color = p.textSecondary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        item(key = "steps_header") {
            PrayerSectionHeader(
                currentLanguage.pick("Los pasos", "The steps", "Os passos"),
                trailing = "${done.size}/$total"
            )
        }
        itemsIndexed(steps, key = { _, s -> "step_${s.number}" }) { i, s ->
            PrayerNumberedStep(
                number = s.number,
                title = s.title,
                body = null,
                done = s.number in done,
                current = i == index,
                onClick = { onSelect(i) },
                modifier = Modifier
                    .testTag("${rootTag}_step_${s.number}")
                    .semantics { stateDescription = if (s.number in done) doneLabel else pendingLabel }
            )
        }
    }

    if (showReading) PrayerReadingSheet(currentLanguage) { showReading = false }
}
