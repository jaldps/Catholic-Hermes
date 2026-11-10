package com.example.ui.screens

import android.os.Build
import android.view.HapticFeedbackConstants
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.audio.RecitationVoiceMode
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.models.RosaryMystery
import com.example.data.models.RosaryMysteryType
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.LitanyLine
import com.example.data.repository.LitanyOfLoreto
import com.example.ui.components.BeadKind
import com.example.ui.components.BeadReel
import com.example.ui.components.RosaryLoopMap
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.design.AppSheet
import com.example.ui.design.AppStatusBarGlyphs
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.design.rememberAssetBitmap
import com.example.ui.design.rememberFirstAsset
import com.example.ui.design.rememberHeroMeta
import com.example.ui.design.softShadow
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** A decade is one Our Father bead followed by ten Hail Mary beads. */
private const val HailMarysPerDecade = 10

// ---------------------------------------------------------------------------
// The guide as data
// ---------------------------------------------------------------------------


/** One physical bead on the rail; [row] groups the pendant, each decade and the medal. */
internal data class BeadSpec(val kind: BeadKind, val row: Int)

/**
 * One thing to pray, said once.
 *
 * A prayer said N times in a row (three Hail Marys, ten Hail Marys) is N steps,
 * each knowing it is [repetition] of [count], so every bead can be marked.
 */
internal data class GuideStep(
    /** Short name of the prayer: "Hail Mary". */
    val name: String,
    /** What this saying is offered for, or the mystery it announces. */
    val intention: String? = null,
    /** What a beginner does here, in plain words. */
    val rubric: String,
    /** The full text; with a [response] it is the leader's half. */
    val leader: String,
    /** The congregation's half, or empty for a prayer said straight through. */
    val response: String = "",
    /** A meditation read after the announcement of a mystery. */
    val meditation: String? = null,
    /** Scripture reference and fruit, beneath the text. */
    val detail: String? = null,
    /** Index into the guide's beads. */
    val bead: Int,
    /** 0 = opening, 1..decadeCount = decades, decadeCount + 1 = closing. */
    val section: Int,
    val repetition: Int = 1,
    val count: Int = 1,
    val optional: Boolean = false
) {
    /** What a single voice reads aloud when there is no leader/response split. */
    val narration: String get() = listOfNotNull(leader, meditation).joinToString("\n\n")
}

internal data class GuideSection(
    val index: Int,
    val title: String,
    val subtitle: String,
    val firstStep: Int
)

/** A whole devotion: the steps, the beads they sit on, and its parts. */
internal class PrayerGuide(
    val steps: List<GuideStep>,
    val beads: List<BeadSpec>,
    val sections: List<GuideSection>,
    val decadeCount: Int
) : StepMap {
    override val stepCount: Int get() = steps.size
    override fun isOptional(index: Int): Boolean = steps[index].optional

    fun firstStepOn(bead: Int): Int = steps.indexOfFirst { it.bead == bead }

    /** Moving one step forward from [from] to [to] completes a decade (a firmer haptic tick). */
    fun finishesDecade(from: Int, to: Int): Boolean =
        to == from + 1 && steps[to].section != steps[from].section && steps[from].section in 1..decadeCount
}

private class GuideBuilder {
    val beads = mutableListOf<BeadSpec>()
    val steps = mutableListOf<GuideStep>()
    private val sections = mutableListOf<GuideSection>()

    fun bead(kind: BeadKind, row: Int): Int {
        beads += BeadSpec(kind, row)
        return beads.lastIndex
    }

    fun section(index: Int, title: String, subtitle: String) {
        sections += GuideSection(index, title, subtitle, steps.size)
    }

    fun build(decadeCount: Int) = PrayerGuide(steps.toList(), beads.toList(), sections.toList(), decadeCount)
}

/** A prayer's two halves: what the leader says and what all answer. */
internal data class Said(val leader: String, val response: String = "")

// ---------------------------------------------------------------------------
// Prayer texts, in the traditional wording of each language
// ---------------------------------------------------------------------------

private fun signOfCross(lang: AppLanguage) = Said(
    lang.pick(
        "En el nombre del Padre, y del Hijo, y del Espíritu Santo. Amén.",
        "In the name of the Father, and of the Son, and of the Holy Spirit. Amen.",
        "Em nome do Pai, e do Filho, e do Espírito Santo. Amém."
    )
)

private fun ourFather(lang: AppLanguage) = Said(
    lang.pick(
        "Padre nuestro, que estás en el cielo, santificado sea tu Nombre; venga a nosotros tu Reino; hágase tu voluntad en la tierra como en el cielo.",
        "Our Father, who art in heaven, hallowed be thy name; thy kingdom come; thy will be done on earth as it is in heaven.",
        "Pai nosso, que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade assim na terra como no céu."
    ),
    lang.pick(
        "Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación, y líbranos del mal. Amén.",
        "Give us this day our daily bread; and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.",
        "O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém."
    )
)

private fun hailMary(lang: AppLanguage) = Said(
    lang.pick(
        "Dios te salve, María, llena eres de gracia; el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús.",
        "Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus.",
        "Ave Maria, cheia de graça, o Senhor é convosco. Bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus."
    ),
    lang.pick(
        "Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.",
        "Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.",
        "Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora de nossa morte. Amém."
    )
)

private fun gloryBe(lang: AppLanguage) = Said(
    lang.pick(
        "Gloria al Padre, y al Hijo, y al Espíritu Santo.",
        "Glory be to the Father, and to the Son, and to the Holy Spirit.",
        "Glória ao Pai, ao Filho e ao Espírito Santo."
    ),
    lang.pick(
        "Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.",
        "As it was in the beginning, is now, and ever shall be, world without end. Amen.",
        "Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém."
    )
)

private fun fatimaPrayer(lang: AppLanguage) = Said(
    lang.pick(
        "Oh Jesús mío, perdona nuestros pecados, líbranos del fuego del infierno, lleva al cielo a todas las almas, especialmente a las más necesitadas de tu misericordia.",
        "O my Jesus, forgive us our sins, save us from the fires of hell; lead all souls to heaven, especially those most in need of Thy mercy.",
        "Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem."
    )
)

private fun hailHolyQueen(lang: AppLanguage) = Said(
    lang.pick(
        "Dios te salve, Reina y Madre de misericordia, vida, dulzura y esperanza nuestra; Dios te salve. A ti llamamos los desterrados hijos de Eva; a ti suspiramos, gimiendo y llorando, en este valle de lágrimas.",
        "Hail, holy Queen, Mother of mercy, our life, our sweetness and our hope. To thee do we cry, poor banished children of Eve; to thee do we send up our sighs, mourning and weeping in this valley of tears.",
        "Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas."
    ),
    lang.pick(
        "Ea, pues, Señora, abogada nuestra, vuelve a nosotros esos tus ojos misericordiosos; y después de este destierro muéstranos a Jesús, fruto bendito de tu vientre. ¡Oh clementísima, oh piadosa, oh dulce Virgen María!",
        "Turn then, most gracious advocate, thine eyes of mercy toward us, and after this our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.",
        "Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria."
    )
)

private fun prayForUs(lang: AppLanguage) = Said(
    lang.pick(
        "Ruega por nosotros, santa Madre de Dios.",
        "Pray for us, O holy Mother of God.",
        "Rogai por nós, santa Mãe de Deus."
    ),
    lang.pick(
        "Para que seamos dignos de alcanzar las promesas de nuestro Señor Jesucristo.",
        "That we may be made worthy of the promises of Christ.",
        "Para que sejamos dignos das promessas de Cristo."
    )
)

private fun rosaryCollect(lang: AppLanguage) = Said(
    lang.pick(
        "Oremos. Oh Dios, cuyo Hijo unigénito, con su vida, muerte y resurrección, nos alcanzó el premio de la vida eterna; concédenos, te rogamos, que, meditando estos misterios del santísimo Rosario de la Bienaventurada Virgen María, imitemos lo que contienen y alcancemos lo que prometen. Por el mismo Cristo nuestro Señor.",
        "Let us pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that, meditating upon these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain and obtain what they promise. Through the same Christ our Lord.",
        "Oremos. Ó Deus, cujo Filho unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, nós Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que eles contêm e alcancemos o que prometem. Pelo mesmo Cristo, Nosso Senhor."
    ),
    lang.pick("Amén.", "Amen.", "Amém.")
)

private fun eternalFather(lang: AppLanguage) = Said(
    lang.pick(
        "Padre Eterno, te ofrezco el Cuerpo y la Sangre, el Alma y la Divinidad de tu amadísimo Hijo, nuestro Señor Jesucristo,",
        "Eternal Father, I offer You the Body and Blood, Soul and Divinity of Your Dearly Beloved Son, Our Lord, Jesus Christ,",
        "Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade de Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo,"
    ),
    lang.pick(
        "como propiciación de nuestros pecados y los del mundo entero.",
        "in atonement for our sins and those of the whole world.",
        "em expiação dos nossos pecados e dos do mundo inteiro."
    )
)

private fun sorrowfulPassion(lang: AppLanguage) = Said(
    lang.pick(
        "Por su dolorosa Pasión,",
        "For the sake of His sorrowful Passion,",
        "Pela Sua dolorosa Paixão,"
    ),
    lang.pick(
        "ten misericordia de nosotros y del mundo entero.",
        "have mercy on us and on the whole world.",
        "tende misericórdia de nós e do mundo inteiro."
    )
)

private fun holyGod(lang: AppLanguage) = Said(
    lang.pick(
        "Santo Dios, Santo Fuerte, Santo Inmortal,",
        "Holy God, Holy Mighty One, Holy Immortal One,",
        "Deus Santo, Deus Forte, Deus Imortal,"
    ),
    lang.pick(
        "ten piedad de nosotros y del mundo entero.",
        "have mercy on us and on the whole world.",
        "tende piedade de nós e do mundo inteiro."
    )
)

private fun chapletClosing(lang: AppLanguage) = Said(
    lang.pick(
        "Oh Dios Eterno, en quien la misericordia es infinita y el tesoro de compasión inagotable, vuelve a nosotros Tu mirada bondadosa y aumenta Tu misericordia en nosotros, para que en los momentos difíciles no desesperemos ni nos desalentemos, sino que con gran confianza nos sometamos a Tu santa voluntad, que es el Amor y la Misericordia mismos. Amén.\n\n¡Jesús, en Ti confío!",
        "Eternal God, in whom mercy is endless and the treasury of compassion inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself. Amen.\n\nJesus, I trust in You!",
        "Ó Deus Eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai-nos com bondade e aumentai a Vossa misericórdia em nós, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a Misericórdia. Amém.\n\nJesus, eu confio em Vós!"
    )
)

private fun LocalizedText.said(lang: AppLanguage, response: LocalizedText? = null) =
    Said(get(lang), response?.get(lang) ?: "")

// Names a beginner reads in the progress line.
private fun signName(lang: AppLanguage) = lang.pick("Señal de la cruz", "Sign of the Cross", "Sinal da cruz")
private fun creedName(lang: AppLanguage) = lang.pick("Credo de los Apóstoles", "Apostles' Creed", "Credo dos Apóstolos")
private fun ourFatherName(lang: AppLanguage) = lang.pick("Padre Nuestro", "Our Father", "Pai-Nosso")
private fun hailMaryName(lang: AppLanguage) = lang.pick("Ave María", "Hail Mary", "Ave-Maria")
private fun gloryBeName(lang: AppLanguage) = lang.pick("Gloria", "Glory Be", "Glória ao Pai")

private fun openingTitle(lang: AppLanguage) = lang.pick("Inicio", "Opening", "Início")
private fun closingTitle(lang: AppLanguage) = lang.pick("Conclusión", "Closing", "Conclusão")
private fun decadeTitle(lang: AppLanguage, n: Int) = lang.pick("Década $n", "Decade $n", "Dezena $n")

private fun signRubric(lang: AppLanguage) = lang.pick(
    "Toma el crucifijo y santíguate: toca la frente, el pecho, el hombro izquierdo y el derecho mientras dices:",
    "Hold the crucifix and bless yourself — forehead, chest, left shoulder, right shoulder — as you say:",
    "Segure o crucifixo e faça o sinal da cruz — testa, peito, ombro esquerdo e ombro direito — enquanto diz:"
)

private fun endSignRubric(lang: AppLanguage) = lang.pick(
    "Termina santiguándote, como al principio.",
    "End by making the Sign of the Cross, as at the start.",
    "Termine fazendo o sinal da cruz, como no início."
)

private fun ourFatherRubric(lang: AppLanguage) = lang.pick(
    "En la primera cuenta grande, reza el Padre Nuestro.",
    "On the first large bead, pray the Our Father.",
    "Na primeira conta grande, reze o Pai-Nosso."
)

private fun mysteryAnnouncement(
    lang: AppLanguage,
    type: RosaryMysteryType,
    position: Int,
    title: String
): String {
    val ordinal = when (position) {
        0 -> lang.pick("Primer", "First", "Primeiro")
        1 -> lang.pick("Segundo", "Second", "Segundo")
        2 -> lang.pick("Tercer", "Third", "Terceiro")
        3 -> lang.pick("Cuarto", "Fourth", "Quarto")
        4 -> lang.pick("Quinto", "Fifth", "Quinto")
        else -> "${position + 1}º"
    }
    val kind = when (type) {
        RosaryMysteryType.JOYFUL -> lang.pick("gozoso", "Joyful", "gozoso")
        RosaryMysteryType.SORROWFUL -> lang.pick("doloroso", "Sorrowful", "doloroso")
        RosaryMysteryType.GLORIOUS -> lang.pick("glorioso", "Glorious", "glorioso")
        RosaryMysteryType.LUMINOUS -> lang.pick("luminoso", "Luminous", "luminoso")
    }
    return lang.pick(
        "$ordinal misterio $kind: $title.",
        "The $ordinal $kind Mystery: $title.",
        "$ordinal mistério $kind: $title."
    )
}

// ---------------------------------------------------------------------------
// The two devotions, built once
// ---------------------------------------------------------------------------

/**
 * The Holy Rosary for one set of mysteries, crucifix to closing Sign of the Cross.
 *
 * Rail: crucifix, one large and three small beads on the pendant; then a large
 * bead and ten small for each decade; then the centre medal for the closing.
 * Each Glory Be and Fatima Prayer sits on the last bead of what it closes.
 */
internal fun buildRosaryGuide(mystery: RosaryMystery, creed: Said, lang: AppLanguage): PrayerGuide {
    val g = GuideBuilder()
    val decadeCount = mystery.decades.size

    g.section(
        0,
        openingTitle(lang),
        lang.pick(
            "Señal de la cruz, Credo, Padre Nuestro, tres Avemarías y Gloria",
            "Sign of the Cross, Creed, Our Father, three Hail Marys and Glory Be",
            "Sinal da cruz, Credo, Pai-Nosso, três Ave-Marias e Glória"
        )
    )
    val crucifix = g.bead(BeadKind.CRUCIFIX, 0)
    g.steps += GuideStep(
        name = signName(lang),
        rubric = signRubric(lang),
        leader = signOfCross(lang).leader,
        bead = crucifix,
        section = 0
    )
    g.steps += GuideStep(
        name = creedName(lang),
        rubric = lang.pick(
            "Todavía con el crucifijo en la mano, profesa la fe con el Credo de los Apóstoles.",
            "Still holding the crucifix, profess the faith with the Apostles' Creed.",
            "Ainda segurando o crucifixo, professe a fé com o Credo dos Apóstolos."
        ),
        leader = creed.leader,
        response = creed.response,
        bead = crucifix,
        section = 0
    )
    val firstLarge = g.bead(BeadKind.LARGE, 0)
    g.steps += GuideStep(
        name = ourFatherName(lang),
        rubric = ourFatherRubric(lang),
        leader = ourFather(lang).leader,
        response = ourFather(lang).response,
        bead = firstLarge,
        section = 0
    )
    val intentions = listOf(
        lang.pick("Por el aumento de la fe", "For an increase of faith", "Pelo aumento da fé"),
        lang.pick("Por el aumento de la esperanza", "For an increase of hope", "Pelo aumento da esperança"),
        lang.pick("Por el aumento de la caridad", "For an increase of charity", "Pelo aumento da caridade")
    )
    intentions.forEachIndexed { i, intention ->
        val bead = g.bead(BeadKind.SMALL, 0)
        g.steps += GuideStep(
            name = hailMaryName(lang),
            intention = intention,
            rubric = lang.pick(
                "En cada una de las tres cuentas pequeñas, un Ave María: por la fe, la esperanza y la caridad.",
                "On each of the three small beads, one Hail Mary: for faith, hope and charity.",
                "Em cada uma das três contas pequenas, uma Ave-Maria: pela fé, pela esperança e pela caridade."
            ),
            leader = hailMary(lang).leader,
            response = hailMary(lang).response,
            bead = bead,
            section = 0,
            repetition = i + 1,
            count = intentions.size
        )
    }
    g.steps += GuideStep(
        name = gloryBeName(lang),
        rubric = lang.pick(
            "Después de la tercera Ave María, antes de pasar a la siguiente cuenta, reza el Gloria.",
            "After the third Hail Mary, before moving to the next bead, pray the Glory Be.",
            "Depois da terceira Ave-Maria, antes de passar à próxima conta, reze o Glória."
        ),
        leader = gloryBe(lang).leader,
        response = gloryBe(lang).response,
        bead = g.beads.lastIndex,
        section = 0
    )

    mystery.decades.forEachIndexed { d, decade ->
        val section = d + 1
        val title = decade.title.get(lang)
        g.section(section, decadeTitle(lang, section), title)

        val large = g.bead(BeadKind.LARGE, section)
        g.steps += GuideStep(
            name = lang.pick("Anuncio del misterio", "Announce the mystery", "Anúncio do mistério"),
            intention = title,
            rubric = lang.pick(
                "En la cuenta grande, anuncia el misterio y medítalo unos instantes en silencio.",
                "On the large bead, announce the mystery and reflect on it for a moment in silence.",
                "Na conta grande, anuncie o mistério e medite-o por alguns instantes em silêncio."
            ),
            leader = mysteryAnnouncement(lang, mystery.type, d, title),
            meditation = decade.meditation.get(lang),
            detail = "${decade.scriptureRef} · ${decade.fruitOfMystery.get(lang)}",
            bead = large,
            section = section
        )
        g.steps += GuideStep(
            name = ourFatherName(lang),
            rubric = lang.pick(
                "En la misma cuenta grande, reza el Padre Nuestro.",
                "On the same large bead, pray the Our Father.",
                "Na mesma conta grande, reze o Pai-Nosso."
            ),
            leader = ourFather(lang).leader,
            response = ourFather(lang).response,
            bead = large,
            section = section
        )
        repeat(HailMarysPerDecade) { i ->
            val bead = g.bead(BeadKind.SMALL, section)
            g.steps += GuideStep(
                name = hailMaryName(lang),
                rubric = lang.pick(
                    "Un Ave María en cada una de las diez cuentas pequeñas, contemplando el misterio.",
                    "One Hail Mary on each of the ten small beads, while contemplating the mystery.",
                    "Uma Ave-Maria em cada uma das dez contas pequenas, contemplando o mistério."
                ),
                leader = hailMary(lang).leader,
                response = hailMary(lang).response,
                bead = bead,
                section = section,
                repetition = i + 1,
                count = HailMarysPerDecade
            )
        }
        val lastSmall = g.beads.lastIndex
        g.steps += GuideStep(
            name = gloryBeName(lang),
            rubric = lang.pick(
                "Después de la décima Ave María, reza el Gloria.",
                "After the tenth Hail Mary, pray the Glory Be.",
                "Depois da décima Ave-Maria, reze o Glória."
            ),
            leader = gloryBe(lang).leader,
            response = gloryBe(lang).response,
            bead = lastSmall,
            section = section
        )
        g.steps += GuideStep(
            name = lang.pick("Oración de Fátima", "Fatima Prayer", "Oração de Fátima"),
            rubric = lang.pick(
                "Para cerrar la década, la oración que la Virgen enseñó en Fátima.",
                "To close the decade, the prayer Our Lady taught at Fatima.",
                "Para encerrar a dezena, a oração que Nossa Senhora ensinou em Fátima."
            ),
            leader = fatimaPrayer(lang).leader,
            bead = lastSmall,
            section = section
        )
    }

    val closing = decadeCount + 1
    g.section(
        closing,
        closingTitle(lang),
        lang.pick(
            "Salve, letanías (opcional), oración final y señal de la cruz",
            "Hail, Holy Queen, the litany (optional), closing prayer and Sign of the Cross",
            "Salve-Rainha, ladainha (opcional), oração final e sinal da cruz"
        )
    )
    val medal = g.bead(BeadKind.MEDAL, closing)
    g.steps += GuideStep(
        name = lang.pick("Salve", "Hail, Holy Queen", "Salve-Rainha"),
        rubric = lang.pick(
            "Terminadas las cinco décadas, en la medalla, saluda a la Virgen como Reina.",
            "With the five decades finished, at the centre medal, greet Our Lady as Queen.",
            "Terminadas as cinco dezenas, na medalha, saúde Nossa Senhora como Rainha."
        ),
        leader = hailHolyQueen(lang).leader,
        response = hailHolyQueen(lang).response,
        bead = medal,
        section = closing
    )

    // The Litany of Loreto follows the Hail Holy Queen. It is optional, so each
    // of its parts is marked so and can be skipped with Next. Its closing
    // versicle is the "Pray for us" step that already follows.
    val litany = LitanyOfLoreto.lines(lang)
    val litanyRubric = lang.pick(
        "Opcional. Las letanías se rezan al terminar las cinco decenas y la Salve. " +
            "Quien guía dice cada invocación y los demás contestan lo que sigue a ℟ " +
            "Si rezas solo, di las dos partes.",
        "Optional. The litany is prayed after the five decades and the Hail, Holy Queen. " +
            "The leader says each invocation and everyone answers what follows ℟ " +
            "Praying alone, say both parts.",
        "Opcional. A ladainha reza-se depois das cinco dezenas e da Salve-Rainha. " +
            "Quem conduz diz cada invocação e todos respondem o que segue a ℟ " +
            "Se rezar sozinho, diga as duas partes."
    )
    val trinityEnd = litany.indexOfFirst { it.call.startsWith("Santa Mar") || it.call.startsWith("Holy Mary") }
    val lambStart = litany.indexOfFirst {
        it.call.startsWith("Cordero") || it.call.startsWith("Lamb") || it.call.startsWith("Cordeiro")
    }
    g.steps += GuideStep(
        name = lang.pick("Letanías Lauretanas", "Litany of Loreto", "Ladainha de Nossa Senhora"),
        intention = lang.pick("Señor y Santísima Trinidad", "The Lord and the Holy Trinity", "O Senhor e a Santíssima Trindade"),
        rubric = litanyRubric,
        leader = litanyText(litany.subList(0, trinityEnd)),
        bead = medal,
        section = closing,
        optional = true
    )
    g.steps += GuideStep(
        name = lang.pick("Letanías Lauretanas", "Litany of Loreto", "Ladainha de Nossa Senhora"),
        intention = lang.pick("Invocaciones a la Virgen", "Invocations of Our Lady", "Invocações a Nossa Senhora"),
        rubric = lang.pick(
            "Opcional. A cada invocación se contesta «ruega por nosotros».",
            "Optional. Each invocation is answered “pray for us”.",
            "Opcional. A cada invocação responde-se «rogai por nós»."
        ),
        leader = litanyText(litany.subList(trinityEnd, lambStart)),
        bead = medal,
        section = closing,
        optional = true
    )
    g.steps += GuideStep(
        name = lang.pick("Cordero de Dios", "Lamb of God", "Cordeiro de Deus"),
        intention = lang.pick("Conclusión de las letanías", "End of the litany", "Conclusão da ladainha"),
        rubric = lang.pick(
            "Opcional. Tres veces, cada una con su respuesta.",
            "Optional. Three times, each with its own response.",
            "Opcional. Três vezes, cada uma com a sua resposta."
        ),
        leader = litanyText(litany.subList(lambStart, litany.size)),
        bead = medal,
        section = closing,
        optional = true
    )

    g.steps += GuideStep(
        name = lang.pick("Ruega por nosotros", "Pray for us", "Rogai por nós"),
        rubric = lang.pick(
            "Quien guía dice la primera línea y todos responden.",
            "The leader says the first line and everyone answers.",
            "Quem conduz diz a primeira linha e todos respondem."
        ),
        leader = prayForUs(lang).leader,
        response = prayForUs(lang).response,
        bead = medal,
        section = closing
    )
    g.steps += GuideStep(
        name = lang.pick("Oración de las letanías", "Prayer of the litany", "Oração da ladainha"),
        rubric = lang.pick(
            "Opcional. Si rezaste las letanías, concluyen con esta oración.",
            "Optional. If you prayed the litany, it ends with this prayer.",
            "Opcional. Se rezou a ladainha, ela termina com esta oração."
        ),
        leader = LitanyOfLoreto.closingPrayer(lang),
        bead = medal,
        section = closing,
        optional = true
    )
    g.steps += GuideStep(
        name = lang.pick("Oración final", "Closing prayer", "Oração final"),
        rubric = lang.pick(
            "La oración con que la Iglesia concluye el Rosario.",
            "The prayer with which the Church concludes the Rosary.",
            "A oração com que a Igreja conclui o Rosário."
        ),
        leader = rosaryCollect(lang).leader,
        response = rosaryCollect(lang).response,
        bead = medal,
        section = closing
    )
    g.steps += GuideStep(
        name = signName(lang),
        rubric = endSignRubric(lang),
        leader = signOfCross(lang).leader,
        bead = medal,
        section = closing
    )
    return g.build(decadeCount)
}

/**
 * The Chaplet of Divine Mercy as Saint Faustina recorded it, said on rosary beads.
 *
 * Rail: crucifix (Sign of the Cross and the optional opening prayers), a large
 * bead (Our Father), two small (Hail Mary, Creed); five decades of one large and
 * ten small; then the medal for the Holy God and the close.
 */
internal fun buildMercyGuide(
    creed: Said,
    opening: Said?,
    lang: AppLanguage
): PrayerGuide {
    val g = GuideBuilder()
    val decadeCount = 5

    g.section(
        0,
        openingTitle(lang),
        lang.pick(
            "Señal de la cruz, oraciones iniciales, Padre Nuestro, Ave María y Credo",
            "Sign of the Cross, opening prayers, Our Father, Hail Mary and Creed",
            "Sinal da cruz, orações iniciais, Pai-Nosso, Ave-Maria e Credo"
        )
    )
    val crucifix = g.bead(BeadKind.CRUCIFIX, 0)
    g.steps += GuideStep(
        name = signName(lang),
        rubric = signRubric(lang),
        leader = signOfCross(lang).leader,
        bead = crucifix,
        section = 0
    )
    if (opening != null) {
        g.steps += GuideStep(
            name = lang.pick("Oración inicial", "Opening prayer", "Oração inicial"),
            rubric = lang.pick(
                "Opcional, al comenzar: la oración de la hora de la misericordia.",
                "Optional, at the start: the prayer of the hour of mercy.",
                "Opcional, ao começar: a oração da hora da misericórdia."
            ),
            leader = opening.leader,
            bead = crucifix,
            section = 0,
            optional = true
        )
        repeat(3) { i ->
            g.steps += GuideStep(
                name = lang.pick("Oh Sangre y Agua", "O Blood and Water", "Ó Sangue e Água"),
                rubric = lang.pick(
                    "Opcional: repite esta invocación tres veces.",
                    "Optional: repeat this invocation three times.",
                    "Opcional: repita esta invocação três vezes."
                ),
                leader = opening.response,
                bead = crucifix,
                section = 0,
                repetition = i + 1,
                count = 3,
                optional = true
            )
        }
    }
    val firstLarge = g.bead(BeadKind.LARGE, 0)
    g.steps += GuideStep(
        name = ourFatherName(lang),
        rubric = ourFatherRubric(lang),
        leader = ourFather(lang).leader,
        response = ourFather(lang).response,
        bead = firstLarge,
        section = 0
    )
    g.steps += GuideStep(
        name = hailMaryName(lang),
        rubric = lang.pick(
            "En la primera cuenta pequeña, un Ave María.",
            "On the first small bead, one Hail Mary.",
            "Na primeira conta pequena, uma Ave-Maria."
        ),
        leader = hailMary(lang).leader,
        response = hailMary(lang).response,
        bead = g.bead(BeadKind.SMALL, 0),
        section = 0
    )
    g.steps += GuideStep(
        name = creedName(lang),
        rubric = lang.pick(
            "En la cuenta siguiente, el Credo de los Apóstoles.",
            "On the next bead, the Apostles' Creed.",
            "Na conta seguinte, o Credo dos Apóstolos."
        ),
        leader = creed.leader,
        response = creed.response,
        bead = g.bead(BeadKind.SMALL, 0),
        section = 0
    )

    for (section in 1..decadeCount) {
        g.section(
            section,
            decadeTitle(lang, section),
            lang.pick(
                "Padre Eterno y diez «Por su dolorosa Pasión»",
                "Eternal Father and ten “For the sake of His sorrowful Passion”",
                "Eterno Pai e dez «Pela Sua dolorosa Paixão»"
            )
        )
        g.steps += GuideStep(
            name = lang.pick("Padre Eterno", "Eternal Father", "Eterno Pai"),
            rubric = lang.pick(
                "En la cuenta grande, ofrece al Padre a su Hijo:",
                "On the large bead, offer the Son to the Father:",
                "Na conta grande, ofereça o Filho ao Pai:"
            ),
            leader = eternalFather(lang).leader,
            response = eternalFather(lang).response,
            bead = g.bead(BeadKind.LARGE, section),
            section = section
        )
        repeat(HailMarysPerDecade) { i ->
            g.steps += GuideStep(
                name = lang.pick("Por su dolorosa Pasión", "For His sorrowful Passion", "Pela Sua dolorosa Paixão"),
                rubric = lang.pick(
                    "En cada una de las diez cuentas pequeñas:",
                    "On each of the ten small beads:",
                    "Em cada uma das dez contas pequenas:"
                ),
                leader = sorrowfulPassion(lang).leader,
                response = sorrowfulPassion(lang).response,
                bead = g.bead(BeadKind.SMALL, section),
                section = section,
                repetition = i + 1,
                count = HailMarysPerDecade
            )
        }
    }

    val closing = decadeCount + 1
    g.section(
        closing,
        closingTitle(lang),
        lang.pick(
            "Santo Dios tres veces, oración final y señal de la cruz",
            "Holy God three times, closing prayer and Sign of the Cross",
            "Deus Santo três vezes, oração final e sinal da cruz"
        )
    )
    val medal = g.bead(BeadKind.MEDAL, closing)
    repeat(3) { i ->
        g.steps += GuideStep(
            name = lang.pick("Santo Dios", "Holy God", "Deus Santo"),
            rubric = lang.pick(
                "Terminadas las cinco décadas, en la medalla, tres veces:",
                "With the five decades finished, at the medal, three times:",
                "Terminadas as cinco dezenas, na medalha, três vezes:"
            ),
            leader = holyGod(lang).leader,
            response = holyGod(lang).response,
            bead = medal,
            section = closing,
            repetition = i + 1,
            count = 3
        )
    }
    g.steps += GuideStep(
        name = lang.pick("Oración final", "Closing prayer", "Oração final"),
        rubric = lang.pick(
            "Opcional: la oración de conclusión.",
            "Optional: the concluding prayer.",
            "Opcional: a oração de encerramento."
        ),
        leader = chapletClosing(lang).leader,
        bead = medal,
        section = closing,
        optional = true
    )
    g.steps += GuideStep(
        name = signName(lang),
        rubric = endSignRubric(lang),
        leader = signOfCross(lang).leader,
        bead = medal,
        section = closing
    )
    return g.build(decadeCount)
}

/** "Decade 3 of 5": the part of the devotion a step belongs to. */
internal fun PrayerGuide.whereLine(step: GuideStep, lang: AppLanguage): String = when {
    step.section == 0 -> openingTitle(lang)
    step.section <= decadeCount -> lang.pick(
        "Década ${step.section} de $decadeCount",
        "Decade ${step.section} of $decadeCount",
        "Dezena ${step.section} de $decadeCount"
    )
    else -> closingTitle(lang)
}

/** "4 of 10" for a prayer said several times in a row, otherwise nothing. */
internal fun countLine(step: GuideStep, lang: AppLanguage): String? =
    if (step.count > 1) {
        lang.pick(
            "${step.repetition} de ${step.count}",
            "${step.repetition} of ${step.count}",
            "${step.repetition} de ${step.count}"
        )
    } else {
        null
    }

// ---------------------------------------------------------------------------
// Screen: "Now Praying"
// ---------------------------------------------------------------------------
//
// One screen, no page scroll on a phone: the current mystery's painting is
// the stage (it changes only when the decade or set changes), the bead reel
// is the scrubber, the prayer is the lyrics and the glass transport sits in
// the thumb zone. Everything reads the same GuideStep, so the painting, the
// rail, the counters and the narration can never disagree.

private enum class RosarySheet { MYSTERIES, OPTIONS, MYSTERY }

private val MysteryArtNames = mapOf(
    RosaryMysteryType.JOYFUL to listOf("annunciation", "visitation", "nativity", "presentation", "finding"),
    RosaryMysteryType.SORROWFUL to listOf("agony", "scourging", "crowning", "carrying", "crucifixion"),
    RosaryMysteryType.GLORIOUS to listOf("resurrection", "ascension", "pentecost", "assumption", "coronation"),
    RosaryMysteryType.LUMINOUS to listOf("baptism", "cana", "proclamation", "transfiguration", "eucharist")
)

/** The bundled painting for one mystery (0-based [decade]). */
private fun mysteryArt(type: RosaryMysteryType, decade: Int): String {
    val set = type.name.lowercase()
    val names = MysteryArtNames.getValue(type)
    val n = decade.coerceIn(0, names.lastIndex)
    return "heroes/mysteries/${set}_${n + 1}_${names[n]}.jpg"
}

private const val MercyArt = "heroes/mysteries/divine_mercy.jpg"

/** Title, artist and date of a bundled painting, from its folder's manifest. */
private data class ArtCredit(val title: String, val artist: String, val year: String)

private object ArtCredits {
    private val cache = java.util.concurrent.ConcurrentHashMap<String, org.json.JSONObject>()
    fun of(context: android.content.Context, path: String): ArtCredit? {
        val dir = path.substringBeforeLast('/', "")
        val manifest = cache.getOrPut(dir) {
            runCatching {
                org.json.JSONObject(context.assets.open("$dir/manifest.json").bufferedReader().use { it.readText() })
            }.getOrElse { org.json.JSONObject() }
        }
        val entry = manifest.optJSONObject(path.substringAfterLast('/')) ?: return null
        val title = entry.optString("title")
        if (title.isBlank()) return null
        return ArtCredit(title, entry.optString("artist"), entry.optString("year"))
    }
}

private fun stepLabel(lang: AppLanguage, index: Int, total: Int) = lang.pick(
    "Paso ${index + 1} de $total",
    "Step ${index + 1} of $total",
    "Passo ${index + 1} de $total"
)

/**
 * The Holy Rosary and the Divine Mercy chaplet, guided one prayer at a time
 * from the first Sign of the Cross to the last, with the bead it is said on
 * centred on a reel of the whole rosary, and spoken recitation.
 *
 * Each devotion is a [PrayerGuide] built once: the stage, the rail, the
 * progress and the narration all read the same step.
 */
@Composable
fun RosaryScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    /** Tab opened on arrival (0 = Rosary, 1 = Divine Mercy Chaplet). */
    initialTab: Int = 0
) {
    val insets = AppEdgeToEdge()
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val lang = currentLanguage
    val view = LocalView.current
    val density = LocalDensity.current

    // One saved value for everything the person has done (see RosarySession).
    var session by rememberSaveable(stateSaver = RosarySession.Saver) {
        mutableStateOf(RosarySession.opening(initialTab))
    }
    val todayMystery = remember { repository.getTodayRosaryMystery() }
    val selectedMysteryType = session.mysterySet(todayMystery.type)
    val selectedTab = session.devotion.tab
    // Beginner guidance stays on from step to step once turned on.
    val instructionsOn = session.instructionsOn
    var sheet by remember { mutableStateOf<RosarySheet?>(null) }

    val voiceMode by ttsManager.voiceMode.collectAsState()
    val currentlySpeaking by ttsManager.currentlySpeaking.collectAsState()
    val engineReady by ttsManager.isReady.collectAsState()

    val creedSource = remember { repository.vaticanPrayers.firstOrNull { it.id == "creed" } }
    val mercySource = remember { repository.divineMercySteps }
    val creed = creedSource?.let { it.leaderText.said(lang, it.responseText) }
        ?: mercySource.first { it.stepIndex == 2 }.let { it.leaderText.said(lang, it.responseText) }
    val opening = mercySource.firstOrNull { it.stepIndex == 1 }?.let { it.leaderText.said(lang, it.responseText) }

    val currentMystery = repository.getRosaryMystery(selectedMysteryType)
    val rosaryGuide = remember(selectedMysteryType, lang) { buildRosaryGuide(currentMystery, creed, lang) }
    val mercyGuide = remember(lang) { buildMercyGuide(creed, opening, lang) }

    val rosaryIndex = session.rosaryStep.coerceIn(0, rosaryGuide.steps.lastIndex)
    val mercyIndex = session.mercyStep.coerceIn(0, mercyGuide.steps.lastIndex)
    val isRosary = session.devotion == Devotion.ROSARY
    val guide = if (isRosary) rosaryGuide else mercyGuide
    val index = if (isRosary) rosaryIndex else mercyIndex
    val step = guide.steps[index]
    val section = guide.sections.lastOrNull { it.index == step.section } ?: guide.sections.first()

    fun narrate(s: GuideStep) {
        ttsManager.setLanguage(lang)
        if (s.response.isBlank()) ttsManager.speakSingle(s.narration)
        else ttsManager.speakDialogue(s.leader, s.response)
    }

    // Pray-aloud mode: an explicit state machine (see RosaryNarration.kt).
    // It stays on from the play press to the pause press, so every step change
    // speaks; leaving the screen ends it and stops the voice.
    val narrateCurrent by rememberUpdatedState({ narrate(step) })
    val narration = remember {
        NarrationController(object : NarrationSink {
            override fun speak(target: NarrationTarget) = narrateCurrent()
            override fun stop() = ttsManager.stop()
        })
    }
    val narrationTarget = NarrationTarget(
        devotion = session.devotion,
        mysterySet = selectedMysteryType.takeIf { isRosary },
        step = index,
        lang = lang,
        voice = voiceMode
    )
    LaunchedEffect(lang) { ttsManager.setLanguage(lang) }
    LaunchedEffect(narrationTarget) { narration.dispatch(NarrationEvent.TargetChanged(narrationTarget)) }
    LaunchedEffect(engineReady) { if (engineReady) narration.dispatch(NarrationEvent.EngineReady) }
    DisposableEffect(Unit) {
        onDispose { narration.dispatch(NarrationEvent.ScreenLeft) }
    }

    fun toggleNarration() = narration.dispatch(NarrationEvent.Toggle(narrationTarget))

    fun dispatch(event: RosaryEvent) {
        val before = session
        val after = before.reduce(event, guide, todayMystery.type)
        if (event is RosaryEvent.Move) {
            val to = after.step
            if (to == index) return
            // A decade completed: a firmer tick than a bead.
            view.performHapticFeedback(
                if (guide.finishesDecade(index, to)) {
                    if (Build.VERSION.SDK_INT >= 30) HapticFeedbackConstants.CONFIRM else HapticFeedbackConstants.LONG_PRESS
                } else HapticFeedbackConstants.KEYBOARD_TAP
            )
        }
        if (after != before) session = after
    }

    fun go(target: Int) = dispatch(RosaryEvent.GoTo(target))

    val previousLabel = lang.pick("Anterior", "Back", "Anterior")
    val nextLabel = lang.pick("Siguiente", "Next", "Seguinte")
    val where = guide.whereLine(step, lang)
    val count = countLine(step, lang)
    val stepText = stepLabel(lang, index, guide.steps.size)

    // The painting for this part of the devotion.
    val artCandidate = if (isRosary) mysteryArt(selectedMysteryType, (step.section - 1).coerceIn(0, guide.decadeCount - 1)) else MercyArt
    val artPath = rememberFirstAsset(artCandidate, if (isRosary) "heroes/lumen/rosary.jpg" else "heroes/divine_mercy.jpg", "heroes/mary_statue.jpg")
    val artMeta = rememberHeroMeta(artPath)
    AppStatusBarGlyphs(lightGlyphs = design.isDark || artMeta.tone == "dark")

    val stepsOnBead = remember(guide, step.bead) { guide.steps.indices.filter { guide.steps[it].bead == step.bead } }

    // Shared between the stage's mist and the layout: where the label begins.
    val labelTopPx = remember { mutableFloatStateOf(0f) }

    BoxWithConstraints(
        modifier
            .fillMaxSize()
            .testTag("rosary_screen")
    ) {
        val wide = maxWidth >= 600.dp
        val gutter = design.spacing.gutter
        val viewport = maxHeight
        val topInset = insets.calculateTopPadding()
        val bottomInset = insets.calculateBottomPadding()
        val columnWidth = if (wide) (maxWidth * 0.55f).coerceAtMost(600.dp) else maxWidth

        // The prayer area is sized for the devotion's characteristic prayer
        // (Our Father / Eternal Father), so the frequent prayers never scroll
        // and the layout never jumps between steps.
        val measurer = rememberTextMeasurer()
        val textWidthPx = with(density) { (columnWidth - gutter * 2).roundToPx().coerceAtLeast(1) }
        val readingStyle = styles.reading
        val textTarget = remember(isRosary, lang, textWidthPx, readingStyle, density) {
            val said = if (isRosary) ourFather(lang) else eternalFather(lang)
            val c = Constraints(maxWidth = textWidthPx)
            val a = measurer.measure(rubricated("℣", said.leader, Color.Unspecified), readingStyle, constraints = c).size.height
            val b = measurer.measure(rubricated("℟", said.response, Color.Unspecified), readingStyle, constraints = c).size.height
            with(density) { (a + b).toDp() } + 10.dp + 22.dp
        }

        val stage: @Composable () -> Unit = {
            MysteryStage(
                assetPath = artPath,
                labelTopPx = { labelTopPx.floatValue },
                credit = artPath?.let { ArtCredits.of(LocalContext.current, it) },
                inDecade = isRosary && step.section in 1..guide.decadeCount,
                modifier = Modifier.fillMaxSize()
            )
        }
        val topBar: @Composable () -> Unit = {
            DevotionSwitch(
                lang = lang,
                selected = selectedTab,
                onSelect = { dispatch(RosaryEvent.SelectDevotion(Devotion.fromTab(it))) }
            )
        }
        val label: @Composable () -> Unit = {
            StageLabel(
                lang = lang,
                isRosary = isRosary,
                setName = selectedMysteryType.displayName.get(lang),
                headline = section.subtitle,
                inDecade = isRosary && step.section in 1..guide.decadeCount,
                onPickMysteries = { sheet = RosarySheet.MYSTERIES },
                onOpenMystery = { sheet = RosarySheet.MYSTERY }
            )
        }
        val progress: @Composable () -> Unit = {
            SectionProgress(
                guide = guide,
                index = index,
                stepText = stepText,
                where = where,
                counterTag = if (isRosary) "rosary_hero_step_counter" else "mercy_hero_step_counter",
                onClick = { sheet = RosarySheet.OPTIONS }
            )
        }
        val reel: @Composable () -> Unit = {
            BeadReel(
                beads = guide.beads.map { it.kind },
                activeBead = step.bead,
                onSelectBead = { bead -> guide.firstStepOn(bead).takeIf { it >= 0 }?.let { go(it) } },
                subStepCount = stepsOnBead.size,
                subStepIndex = stepsOnBead.indexOf(index).coerceAtLeast(0),
                modifier = Modifier
                    .testTag(if (isRosary) "prayer_bead_counter" else "mercy_step_counter")
                    .semantics {
                        contentDescription = listOfNotNull(where, step.name, count).joinToString(", ")
                        stateDescription = stepText
                        customActions = listOf(
                            CustomAccessibilityAction(previousLabel) {
                                if (index > 0) { go(index - 1); true } else false
                            },
                            CustomAccessibilityAction(nextLabel) {
                                if (index < guide.steps.lastIndex) { go(index + 1); true } else false
                            }
                        )
                    }
            )
        }
        val title: @Composable () -> Unit = {
            PrayerTitleRow(
                step = step,
                count = count,
                lang = lang,
                onSkip = { dispatch(RosaryEvent.SkipOptional) }
            )
        }
        val prayer: @Composable () -> Unit = {
            PrayerArea(
                step = step,
                stepKey = "$selectedTab/$selectedMysteryType/$index",
                lang = lang,
                spokenNow = currentlySpeaking,
                instructionsOn = instructionsOn
            )
        }
        val transport: @Composable () -> Unit = {
            TransportRow(
                lang = lang,
                instructionsOn = instructionsOn,
                onToggleInstructions = { dispatch(RosaryEvent.ToggleInstructions) },
                canGoPrevious = index > 0,
                canGoNext = index < guide.steps.lastIndex,
                isPlaying = narration.isSpeaking,
                onPrevious = { go(index - 1) },
                onNext = { go(index + 1) },
                onToggleAudio = {
                    view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
                    toggleNarration()
                },
                onMore = { sheet = RosarySheet.OPTIONS },
                previousLabel = previousLabel,
                nextLabel = nextLabel,
                previousTag = if (isRosary) "prev_bead_button" else "prev_mercy_step_button",
                nextTag = if (isRosary) "next_bead_button" else "next_mercy_step_button",
                audioTag = if (isRosary) "toggle_prayer_audio_button" else "toggle_mercy_audio_button"
            )
        }

        if (!wide) {
            PracticeLayout(
                viewport = viewport,
                topInset = topInset,
                bottomInset = bottomInset,
                textTarget = textTarget,
                labelTopPx = labelTopPx,
                stage = stage, topBar = topBar, label = label, progress = progress,
                reel = reel, title = title, prayer = prayer, transport = transport,
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
            )
        } else {
            // Tablets, foldables and landscape: the painting holds the left,
            // the practice column the right.
            Row(Modifier.fillMaxSize()) {
                Box(Modifier.weight(1f).fillMaxHeight()) {
                    Layout(
                        contents = listOf(stage, topBar, label),
                        modifier = Modifier.fillMaxSize()
                    ) { (stageM, topM, labelM), c ->
                        val w = c.maxWidth
                        val h = c.maxHeight
                        val loose = Constraints(maxWidth = w)
                        val labelP = labelM.first().measure(loose)
                        val topP = topM.first().measure(loose)
                        val labelTop = h - labelP.height - bottomInset.roundToPx() - 8.dp.roundToPx()
                        labelTopPx.floatValue = labelTop.toFloat()
                        val stageP = stageM.first().measure(Constraints.fixed(w, h))
                        layout(w, h) {
                            stageP.place(0, 0)
                            topP.place(0, topInset.roundToPx() + 6.dp.roundToPx())
                            labelP.place(0, labelTop)
                        }
                    }
                }
                Column(
                    Modifier
                        .width(columnWidth)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                        .padding(top = topInset + 24.dp)
                ) {
                    progress()
                    Spacer(Modifier.height(8.dp))
                    reel()
                    Spacer(Modifier.height(6.dp))
                    title()
                    Spacer(Modifier.height(8.dp))
                    val fixed = 24.dp + 40.dp + 76.dp + 40.dp + 80.dp + topInset + bottomInset + 40.dp
                    Box(Modifier.fillMaxWidth().height((viewport - fixed).coerceAtLeast(180.dp))) { prayer() }
                    Spacer(Modifier.height(10.dp))
                    transport()
                    Spacer(Modifier.height(bottomInset + 12.dp))
                }
            }
        }
    }

    when (sheet) {
        RosarySheet.MYSTERIES -> MysteriesSheet(
            lang = lang,
            selected = selectedMysteryType,
            today = todayMystery.type,
            onSelect = { mType ->
                dispatch(RosaryEvent.PickMysterySet(mType))
                sheet = null
            },
            onDismiss = { sheet = null }
        )
        RosarySheet.OPTIONS -> OptionsSheet(
            lang = lang,
            isRosary = isRosary,
            guide = guide,
            step = step,
            voiceMode = voiceMode,
            onVoiceMode = { ttsManager.setVoiceMode(it) },
            onGo = { target -> go(target); sheet = null },
            onDismiss = { sheet = null }
        )
        RosarySheet.MYSTERY -> {
            val decade = currentMystery.decades.getOrNull(step.section - 1)
            if (decade == null || !isRosary) {
                sheet = null
            } else {
                MysterySheet(
                    lang = lang,
                    type = selectedMysteryType,
                    position = step.section - 1,
                    title = decade.title.get(lang),
                    scriptureRef = decade.scriptureRef,
                    meditation = decade.meditation.get(lang),
                    fruit = decade.fruitOfMystery.get(lang),
                    artPath = artPath,
                    onDismiss = { sheet = null }
                )
            }
        }
        null -> Unit
    }
}

/**
 * The practice screen's vertical composition. The stage absorbs whatever the
 * other blocks and the prayer's target height leave, within limits; on a
 * small screen or at large type the prayer area holds a minimum and the
 * whole page scrolls instead.
 */
@Composable
private fun PracticeLayout(
    viewport: Dp,
    topInset: Dp,
    bottomInset: Dp,
    textTarget: Dp,
    labelTopPx: androidx.compose.runtime.MutableFloatState,
    stage: @Composable () -> Unit,
    topBar: @Composable () -> Unit,
    label: @Composable () -> Unit,
    progress: @Composable () -> Unit,
    reel: @Composable () -> Unit,
    title: @Composable () -> Unit,
    prayer: @Composable () -> Unit,
    transport: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Layout(
        contents = listOf(stage, topBar, label, progress, reel, title, prayer, transport),
        modifier = modifier
    ) { slots, constraints ->
        val stageM = slots[0]; val topM = slots[1]; val labelM = slots[2]; val progressM = slots[3]
        val reelM = slots[4]; val titleM = slots[5]; val prayerM = slots[6]; val transportM = slots[7]
        val w = constraints.maxWidth
        val loose = Constraints(maxWidth = w)
        val topP = topM.first().measure(loose)
        val labelP = labelM.first().measure(loose)
        val progressP = progressM.first().measure(loose)
        val reelP = reelM.first().measure(loose)
        val titleP = titleM.first().measure(loose)
        val transportP = transportM.first().measure(loose)

        val gapStage = 14.dp.roundToPx()
        val gapReel = 2.dp.roundToPx()
        val gapTitle = 2.dp.roundToPx()
        val gapTitleText = 8.dp.roundToPx()
        val gapTransport = 8.dp.roundToPx()
        val gapBottom = 14.dp.roundToPx()
        val top = topInset.roundToPx()
        val bottom = bottomInset.roundToPx()

        val fixed = gapStage + progressP.height + gapReel + reelP.height + gapTitle + titleP.height +
            gapTitleText + gapTransport + transportP.height + gapBottom
        val avail = viewport.roundToPx() - bottom
        val stageMin = top + 6.dp.roundToPx() + topP.height + 92.dp.roundToPx() + labelP.height
        val stageMax = maxOf(stageMin, (avail * 0.56f).roundToInt())
        val stageH = (avail - fixed - textTarget.roundToPx()).coerceIn(stageMin, stageMax)
        val textH = maxOf(150.dp.roundToPx(), avail - fixed - stageH)
        val prayerP = prayerM.first().measure(Constraints.fixed(w, textH))
        val stageP = stageM.first().measure(Constraints.fixed(w, stageH))
        val labelTop = stageH - labelP.height
        labelTopPx.floatValue = labelTop.toFloat()

        val height = stageH + fixed + textH + bottom
        layout(w, height) {
            stageP.place(0, 0)
            topP.place(0, top + 6.dp.roundToPx())
            labelP.place(0, labelTop)
            var y = stageH + gapStage
            progressP.place(0, y); y += progressP.height + gapReel
            reelP.place(0, y); y += reelP.height + gapTitle
            titleP.place(0, y); y += titleP.height + gapTitleText
            prayerP.place(0, y); y += textH + gapTransport
            transportP.place(0, y)
        }
    }
}

// ---------------------------------------------------------------- the stage

/**
 * The mystery's painting, full-bleed from the top of the display: cropped on
 * its focus point, gently desaturated, then dissolving into the canvas —
 * growing soft (a progressive blur) as it fades — so the label below sits on
 * mist, never on paint. Crossfades and settles when the mystery changes.
 */
@Composable
private fun MysteryStage(
    assetPath: String?,
    labelTopPx: () -> Float,
    credit: ArtCredit?,
    inDecade: Boolean,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val still = LocalInspectionMode.current
    val canvas = p.canvasTop
    Box(
        modifier
            .background(canvas)
            .testTag("rosary_hero_photo")
            .semantics {
                if (credit != null) {
                    contentDescription = listOf(credit.title, credit.artist).filter { it.isNotBlank() }.joinToString(", ")
                }
            }
    ) {
        Crossfade(
            targetState = assetPath,
            animationSpec = tween(if (still) 0 else 650),
            label = "mystery",
            modifier = Modifier.fillMaxSize().then(if (inDecade) Modifier.testTag("mystery_hero_photo") else Modifier)
        ) { path ->
            val sharp = rememberAssetBitmap(path, 1080)
            val soft = rememberAssetBitmap(path, 40)
            val meta = rememberHeroMeta(path)
            val settle = remember(path) { Animatable(if (still) 1f else 1.045f) }
            LaunchedEffect(path) {
                if (!still) settle.animateTo(1f, tween(1400, easing = FastOutSlowInEasing))
            }
            val matrix = remember(design.isDark) {
                ColorMatrix().apply { setToSaturation(if (design.isDark) 0.82f else 0.86f) }
            }
            Box(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer { scaleX = settle.value; scaleY = settle.value }
            ) {
                if (sharp != null) {
                    Image(
                        bitmap = sharp,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = meta.alignment,
                        colorFilter = ColorFilter.colorMatrix(matrix),
                        modifier = Modifier.fillMaxSize()
                    )
                }
                if (soft != null) {
                    // The same painting, tiny and scaled up: a blur that
                    // strengthens toward the fade, on every API level.
                    Image(
                        bitmap = soft,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = meta.alignment,
                        filterQuality = FilterQuality.Low,
                        colorFilter = ColorFilter.colorMatrix(matrix),
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                            .drawWithContent {
                                drawContent()
                                val labelTop = labelTopPx().takeIf { it > 0f } ?: (size.height * 0.62f)
                                val start = (labelTop - 150.dp.toPx()).coerceAtLeast(0f)
                                drawRect(
                                    Brush.verticalGradient(
                                        0f to Color.Transparent,
                                        1f to Color.Black,
                                        startY = start,
                                        endY = labelTop + 10.dp.toPx()
                                    ),
                                    blendMode = BlendMode.DstIn
                                )
                            }
                    )
                }
            }
        }
        // Mist: lift (light) or settle (dark) the paint, veil the status
        // bar, and dissolve into the canvas above the label.
        Box(
            Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(if (design.isDark) Color.Black.copy(alpha = 0.20f) else canvas.copy(alpha = 0.10f))
                    val veil = 120.dp.toPx()
                    drawRect(
                        Brush.verticalGradient(
                            0f to (if (design.isDark) Color.Black.copy(alpha = 0.38f) else Color.Black.copy(alpha = 0.10f)),
                            1f to Color.Transparent,
                            startY = 0f, endY = veil
                        ),
                        size = androidx.compose.ui.geometry.Size(size.width, veil)
                    )
                    val labelTop = labelTopPx().takeIf { it > 0f } ?: (size.height * 0.62f)
                    val start = (labelTop - 170.dp.toPx()).coerceAtLeast(size.height * 0.18f)
                    val end = (labelTop + 34.dp.toPx()).coerceAtMost(size.height)
                    // Eased (smoothstep) so dark paintings never leave a grey band.
                    val stops = (0..10).map { k ->
                        val t = k / 10f
                        val a = t * t * (3f - 2f * t)
                        val y = start + (end - start) * t
                        (y / size.height).coerceIn(0f, 1f) to canvas.copy(alpha = a)
                    }
                    drawRect(
                        Brush.verticalGradient(
                            *(listOf(0f to canvas.copy(alpha = 0f)) + stops + listOf(1f to canvas)).toTypedArray()
                        )
                    )
                }
        )
    }
}

/** Rosary / Divine Mercy: a compact glass segmented control over the art. */
@Composable
private fun DevotionSwitch(lang: AppLanguage, selected: Int, onSelect: (Int) -> Unit) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val style = styles.subhead.copy(fontWeight = FontWeight.SemiBold)
    val full = listOf(
        lang.pick("Santo Rosario", "The Holy Rosary", "Santo Terço"),
        lang.pick("Divina Misericordia", "Divine Mercy", "Divina Misericórdia")
    )
    val short = listOf(
        lang.pick("Rosario", "Rosary", "Terço"),
        lang.pick("Coronilla", "Chaplet", "Coroinha")
    )
    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current
    BoxWithConstraints(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter), contentAlignment = Alignment.Center) {
        val room = with(density) { maxWidth.toPx() }
        val needed = remember(full, style, density) {
            full.sumOf { measurer.measure(it, style).size.width } + with(density) { (16.dp * 4 + 8.dp + 8.dp).toPx() }.toInt()
        }
        val labels = if (needed <= room) full else short
        val shape = RoundedCornerShape(percent = 50)
        Row(
            // Fixed dark track: this control always sits over the painting, in light and dark mode,
            // so white labels keep >= 4.5:1 whatever the art underneath.
            Modifier
                .clip(shape)
                .background(Color.Black.copy(alpha = 0.62f))
                .border(1.dp, Color.White.copy(alpha = 0.22f), shape)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            labels.forEachIndexed { i, text ->
                val isSelected = i == selected
                val thumb by animateColorAsState(
                    if (isSelected) Color.White.copy(alpha = 0.96f) else Color.Transparent,
                    tween(200), label = "devotion"
                )
                Box(
                    Modifier
                        .heightIn(min = 36.dp)
                        .then(if (isSelected) Modifier.softShadow(shape, p.shadow, 10.dp, 2.dp) else Modifier)
                        .clip(shape)
                        .background(thumb)
                        .clickable(remember { MutableInteractionSource() }, indication = null, role = Role.Tab) { onSelect(i) }
                        .semantics { this.selected = isSelected }
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text,
                        style = style.copy(fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium),
                        color = if (isSelected) Color(0xFF111111) else Color.White.copy(alpha = 0.94f),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

/**
 * What is being prayed, set in the mist at the foot of the painting: the
 * mysteries picker (Rosary) or the chaplet's name, then the part of the
 * devotion — the mystery's title during a decade.
 */
@Composable
private fun StageLabel(
    lang: AppLanguage,
    isRosary: Boolean,
    setName: String,
    headline: String,
    inDecade: Boolean,
    onPickMysteries: () -> Unit,
    onOpenMystery: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter)) {
        if (isRosary) {
            val shape = RoundedCornerShape(percent = 50)
            Row(
                Modifier
                    .heightIn(min = 44.dp)
                    .padding(vertical = 4.dp)
                    .lumenPress(onPickMysteries, 0.96f)
                    .lumenGlass(shape, strong = true)
                    .testTag("rosary_mystery_picker")
                    .semantics { contentDescription = lang.pick("Misterios: $setName", "Mysteries: $setName", "Mistérios: $setName") }
                    .padding(start = 14.dp, end = 10.dp, top = 6.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(setName, style = styles.subhead.copy(fontWeight = FontWeight.Medium), color = p.textPrimary, maxLines = 1)
                Spacer(Modifier.width(4.dp))
                Icon(Icons.Rounded.KeyboardArrowDown, null, tint = p.textSecondary, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.height(8.dp))
        } else {
            Text(
                lang.pick("Coronilla de la Divina Misericordia", "Chaplet of Divine Mercy", "Terço da Divina Misericórdia"),
                style = styles.eyebrow,
                color = p.textSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(6.dp))
        }
        Row(
            Modifier
                .then(if (inDecade) Modifier.lumenPress(onOpenMystery, 0.98f) else Modifier)
                .padding(bottom = 2.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                headline,
                style = styles.headline,
                color = p.textPrimary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f, fill = false)
                    .testTag(if (isRosary) "rosary_hero_title" else "mercy_hero_title")
            )
            if (inDecade) {
                Icon(
                    Icons.Rounded.ChevronRight,
                    lang.pick("Ver el misterio", "About this mystery", "Ver o mistério"),
                    tint = p.textSecondary,
                    modifier = Modifier.padding(start = 2.dp, top = 1.dp).size(20.dp)
                )
            }
        }
    }
}

// --------------------------------------------------------------- progress

/** A thin bar in the devotion's parts (proportional), with step and part. */
@Composable
private fun SectionProgress(
    guide: PrayerGuide,
    index: Int,
    stepText: String,
    where: String,
    counterTag: String,
    onClick: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val step = guide.steps[index]
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .lumenPress(onClick, 0.99f)
            .heightIn(min = 44.dp)
            .padding(vertical = 4.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .testTag("page_dots")
                .semantics {
                    progressBarRangeInfo = ProgressBarRangeInfo((index + 1).toFloat(), 1f..guide.steps.size.toFloat())
                },
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            guide.sections.forEachIndexed { i, s ->
                val end = guide.sections.getOrNull(i + 1)?.firstStep ?: guide.steps.size
                val n = (end - s.firstStep).coerceAtLeast(1)
                val target = when {
                    s.index < step.section -> 1f
                    s.index == step.section -> ((index - s.firstStep + 1).toFloat() / n).coerceIn(0f, 1f)
                    else -> 0f
                }
                val fill by animateFloatAsState(target, tween(320), label = "part")
                Box(
                    Modifier
                        .weight(n.toFloat())
                        .height(4.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(p.textPrimary.copy(alpha = if (design.isDark) 0.16f else 0.10f))
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth(fill)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(percent = 50))
                            .background(p.textPrimary)
                    )
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                stepText, style = styles.footnote, color = p.textSecondary, maxLines = 1,
                modifier = Modifier.testTag(counterTag)
            )
            Spacer(Modifier.weight(1f).widthIn(min = 12.dp))
            Text(where, style = styles.footnote, color = p.textSecondary, maxLines = 1, textAlign = TextAlign.End)
        }
    }
}

// ------------------------------------------------------------------ prayer

/** The prayer's name, title2, with its repetition count or a "skip". */
@Composable
private fun PrayerTitleRow(step: GuideStep, count: String?, lang: AppLanguage, onSkip: () -> Unit) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Row(
        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            step.name,
            style = styles.title2,
            color = p.textPrimary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .testTag("current_prayer_title")
                .semantics { heading() }
        )
        if (count != null) {
            Spacer(Modifier.width(12.dp))
            Text(count, style = styles.subhead, color = p.textSecondary, maxLines = 1)
        }
        if (step.optional) {
            Spacer(Modifier.width(8.dp))
            val shape = RoundedCornerShape(percent = 50)
            Box(
                Modifier
                    .heightIn(min = 44.dp)
                    .lumenPress(onSkip, 0.95f)
                    .semantics { contentDescription = lang.pick("Saltar la oración opcional", "Skip the optional prayer", "Pular a oração opcional") }
                    .testTag("skip_optional_button"),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    Modifier.lumenGlass(shape, lifted = false).padding(start = 12.dp, end = 6.dp, top = 5.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(lang.pick("Saltar", "Skip", "Pular"), style = styles.footnote.copy(fontWeight = FontWeight.Medium), color = p.textPrimary)
                    Icon(Icons.Rounded.ChevronRight, null, tint = p.textSecondary, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

/** One step's prayer as shown, so an outgoing frame keeps its own text. */
private data class PrayerFrame(val key: String, val step: GuideStep)

/**
 * The text to pray, in the reader's font, in a bounded area that scrolls on
 * its own (long prayers) behind soft fades. While narrating, the half being
 * spoken stays in ink and the other half steps back, as lyrics do.
 */
@Composable
private fun PrayerArea(
    step: GuideStep,
    stepKey: String,
    lang: AppLanguage,
    spokenNow: String?,
    instructionsOn: Boolean
) {
    val design = LocalAppDesign.current
    val still = LocalInspectionMode.current
    Box(Modifier.fillMaxSize().testTag("prayer_text_art")) {
        AnimatedContent(
            targetState = PrayerFrame(stepKey, step),
            contentKey = { it.key },
            transitionSpec = {
                if (still) (fadeIn(tween(0)) togetherWith fadeOut(tween(0)))
                else (fadeIn(tween(240, delayMillis = 60)) + slideInVertically(tween(300)) { it / 40 }) togetherWith fadeOut(tween(140))
            },
            label = "prayer",
            modifier = Modifier.fillMaxSize()
        ) { frame ->
            val key = frame.key
            val step = frame.step
            // Each step keeps its own scroll, starting at the top.
            val scroll = remember(key) { androidx.compose.foundation.ScrollState(0) }
            val scope = rememberCoroutineScope()
            val fadePx = with(LocalDensity.current) { 18.dp.toPx() }
            var responseTop by remember(key) { mutableFloatStateOf(-1f) }
            val responseSpoken = spokenNow != null && step.response.isNotBlank() && spokenNow == step.response
            LaunchedEffect(responseSpoken) {
                if (responseSpoken && responseTop > 0f && responseTop > scroll.value + scroll.viewportSize * 0.5f) {
                    scope.launch { scroll.animateScrollTo((responseTop - fadePx).roundToInt()) }
                }
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                    .drawWithContent {
                        drawContent()
                        val topA = if (scroll.value > 0) 0f else 1f
                        val bottomA = if (scroll.canScrollForward) 0f else 1f
                        drawRect(
                            Brush.verticalGradient(
                                0f to Color.Black.copy(alpha = topA),
                                (fadePx / size.height).coerceIn(0f, 0.5f) to Color.Black,
                                (1f - fadePx * 1.6f / size.height).coerceIn(0.5f, 1f) to Color.Black,
                                1f to Color.Black.copy(alpha = bottomA)
                            ),
                            blendMode = BlendMode.DstIn
                        )
                    }
                    .verticalScroll(scroll)
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = 2.dp, bottom = 22.dp)
            ) {
                PrayerBody(step, lang, spokenNow, instructionsOn, onResponsePlaced = { responseTop = it })
            }
        }
    }
}

@Composable
private fun PrayerBody(
    step: GuideStep,
    lang: AppLanguage,
    spokenNow: String?,
    instructionsOn: Boolean,
    onResponsePlaced: (Float) -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val reading = styles.reading
    val leaderSpoken = spokenNow != null && (spokenNow == step.leader || spokenNow == step.narration)
    val responseSpoken = spokenNow != null && step.response.isNotBlank() && spokenNow == step.response

    if (instructionsOn) {
        GuideHint(step, lang)
        Spacer(Modifier.height(14.dp))
    }
    if (step.optional) {
        Text(
            lang.pick("Oración opcional", "Optional prayer", "Oração opcional"),
            style = styles.footnote, color = p.textSecondary
        )
        Spacer(Modifier.height(4.dp))
    }
    step.intention?.takeIf { step.meditation == null }?.let {
        Text(it, style = styles.subhead.copy(fontWeight = FontWeight.Medium), color = p.textSecondary)
        Spacer(Modifier.height(6.dp))
    }
    Column(Modifier.widthIn(max = design.spacing.readingMaxWidth)) {
        if (step.response.isBlank()) {
            val ink by animateColorAsState(p.textPrimary, label = "leader")
            Text(text = step.leader, style = reading, color = ink)
        } else {
            val leaderInk by animateColorAsState(if (responseSpoken) p.textSecondary else p.textPrimary, tween(300), label = "leader")
            val responseInk by animateColorAsState(if (leaderSpoken) p.textSecondary else p.textPrimary, tween(300), label = "response")
            Text(text = rubricated("℣", step.leader, p.textSecondary), style = reading, color = leaderInk)
            Spacer(Modifier.height(10.dp))
            Text(
                text = rubricated("℟", step.response, p.textSecondary),
                style = reading,
                color = responseInk,
                modifier = Modifier.onPlaced { onResponsePlaced(it.positionInParent().y) }
            )
        }
        step.meditation?.let {
            Spacer(Modifier.height(14.dp))
            Text(it, style = reading.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic), color = p.textSecondary)
        }
        step.detail?.let {
            Spacer(Modifier.height(10.dp))
            Text(it, style = styles.footnote, color = p.textSecondary)
        }
    }
}

/** What a beginner does here: the rubric, and the ℣/℟ legend. */
@Composable
private fun GuideHint(step: GuideStep, lang: AppLanguage) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Row(
        Modifier
            .fillMaxWidth()
            .lumenGlass(RoundedCornerShape(20.dp), lifted = false)
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .testTag("prayer_instructions_text")
    ) {
        Icon(Icons.Outlined.Lightbulb, null, tint = p.textSecondary, modifier = Modifier.padding(top = 1.dp).size(18.dp))
        Spacer(Modifier.width(10.dp))
        Column {
            Text(step.rubric, style = styles.callout, color = p.textPrimary)
            if (step.response.isNotBlank()) {
                Spacer(Modifier.height(6.dp))
                Text(
                    lang.pick(
                        "℣ dice quien guía · ℟ responden todos. Si rezas solo, di las dos partes.",
                        "℣ the leader says · ℟ everyone answers. Praying alone? Say both parts.",
                        "℣ diz quem conduz · ℟ todos respondem. Rezando sozinho? Diga as duas partes."
                    ),
                    style = styles.footnote, color = p.textSecondary
                )
            }
        }
    }
}

// --------------------------------------------------------------- transport

/** Guide toggle · glass transport (previous / pray aloud / next) · more. */
@Composable
private fun TransportRow(
    lang: AppLanguage,
    instructionsOn: Boolean,
    onToggleInstructions: () -> Unit,
    canGoPrevious: Boolean,
    canGoNext: Boolean,
    isPlaying: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onToggleAudio: () -> Unit,
    onMore: () -> Unit,
    previousLabel: String,
    nextLabel: String,
    previousTag: String,
    nextTag: String,
    audioTag: String
) {
    val design = LocalAppDesign.current
    val p = design.palette
    BoxWithConstraints(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter)) {
        val compact = maxWidth < 330.dp
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AppGlassIconButton(
                icon = if (instructionsOn) Icons.Filled.Lightbulb else Icons.Outlined.Lightbulb,
                contentDescription = lang.pick("Instrucciones", "Instructions", "Instruções"),
                onClick = onToggleInstructions,
                tag = "prayer_instructions_toggle",
                modifier = Modifier.semantics {
                    selected = instructionsOn
                    stateDescription = if (instructionsOn) lang.pick("Expandido", "Expanded", "Expandido")
                    else lang.pick("Contraído", "Collapsed", "Recolhido")
                }
            )
            Spacer(Modifier.weight(1f))
            val shape = RoundedCornerShape(percent = 50)
            Row(
                Modifier
                    .lumenGlass(shape)
                    .padding(horizontal = if (compact) 4.dp else 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(if (compact) 4.dp else 14.dp)
            ) {
                TransportKey(Icons.Rounded.SkipPrevious, previousLabel, canGoPrevious, onPrevious, previousTag)
                Box(
                    Modifier
                        .size(60.dp)
                        .lumenPress(onToggleAudio, 0.92f)
                        .semantics {
                            contentDescription = if (isPlaying) lang.pick("Pausar la voz", "Pause narration", "Pausar a voz")
                            else lang.pick("Rezar en voz alta", "Pray aloud", "Rezar em voz alta")
                            stateDescription = if (isPlaying) lang.pick("Rezando en voz alta", "Praying aloud", "Rezando em voz alta")
                            else lang.pick("En silencio", "Silent", "Em silêncio")
                        }
                        .testTag(audioTag)
                        .softShadow(CircleShape, p.shadow, 16.dp, 5.dp)
                        .then(
                            if (isPlaying) Modifier.background(p.action, CircleShape)
                            else Modifier.lumenGlass(CircleShape, strong = true, lifted = false)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (isPlaying) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                        null,
                        tint = if (isPlaying) p.onAction else p.textPrimary,
                        modifier = Modifier.size(30.dp)
                    )
                }
                TransportKey(Icons.Rounded.SkipNext, nextLabel, canGoNext, onNext, nextTag)
            }
            Spacer(Modifier.weight(1f))
            AppGlassIconButton(
                icon = Icons.Outlined.MoreHoriz,
                contentDescription = lang.pick("Opciones del rosario", "Rosary options", "Opções do terço"),
                onClick = onMore,
                tag = "rosary_options_button"
            )
        }
    }
}

@Composable
private fun TransportKey(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, enabled: Boolean, onClick: () -> Unit, tag: String) {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .size(52.dp)
            .lumenPress(onClick, 0.88f, enabled = enabled)
            .semantics { contentDescription = label }
            .testTag(tag),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, null, tint = if (enabled) p.textPrimary else p.inkQuiet, modifier = Modifier.size(30.dp))
    }
}

// ------------------------------------------------------------------ sheets

@Composable
private fun SheetLabel(text: String) {
    val design = LocalAppDesign.current
    Text(
        text,
        style = LocalAppTextStyles.current.footnote.copy(fontWeight = FontWeight.Medium),
        color = design.palette.textSecondary,
        modifier = Modifier.padding(start = design.spacing.gutter + 16.dp, end = design.spacing.gutter, top = 8.dp, bottom = 8.dp)
    )
}

/** The four sets of mysteries, each with its first painting and its days. */
@Composable
private fun MysteriesSheet(
    lang: AppLanguage,
    selected: RosaryMysteryType,
    today: RosaryMysteryType,
    onSelect: (RosaryMysteryType) -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    AppSheet(onDismiss = onDismiss, title = lang.pick("Misterios", "Mysteries", "Mistérios"), tag = "rosary_mysteries_sheet") {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .lumenGlass(RoundedCornerShape(20.dp), lifted = false)
        ) {
            val types = RosaryMysteryType.entries
            types.forEachIndexed { i, type ->
                val isSelected = type == selected
                Row(
                    Modifier
                        .fillMaxWidth()
                        .lumenPress({ onSelect(type) }, 0.99f)
                        .semantics { this.selected = isSelected }
                        .testTag("rosary_mystery_option_${type.name.lowercase()}")
                        .heightIn(min = 76.dp)
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ArtThumb(mysteryArt(type, 0), Modifier.size(56.dp))
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Text(type.displayName.get(lang), style = styles.body, color = p.textPrimary)
                        Spacer(Modifier.height(2.dp))
                        val days = type.recommendedDays.get(lang)
                        Text(
                            if (type == today) lang.pick("Hoy", "Today", "Hoje") + " · " + days else days,
                            style = styles.footnote, color = p.textSecondary
                        )
                    }
                    if (isSelected) {
                        Spacer(Modifier.width(10.dp))
                        Icon(Icons.Rounded.Check, null, tint = p.accent, modifier = Modifier.size(22.dp))
                    }
                }
                if (i < types.lastIndex) {
                    Box(Modifier.fillMaxWidth().padding(start = 84.dp).height(0.5.dp).background(p.separator))
                }
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun ArtThumb(path: String, modifier: Modifier) {
    val p = LocalAppDesign.current.palette
    val bitmap = rememberAssetBitmap(path, 240)
    val meta = rememberHeroMeta(path)
    Box(modifier.clip(RoundedCornerShape(12.dp)).background(p.glass)) {
        if (bitmap != null) {
            Image(bitmap, null, contentScale = ContentScale.Crop, alignment = meta.alignment, modifier = Modifier.fillMaxSize())
        }
    }
}

/** Voices, and the parts of the devotion as a map and a list to jump to. */
@Composable
private fun OptionsSheet(
    lang: AppLanguage,
    isRosary: Boolean,
    guide: PrayerGuide,
    step: GuideStep,
    voiceMode: RecitationVoiceMode,
    onVoiceMode: (RecitationVoiceMode) -> Unit,
    onGo: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val modes = listOf(RecitationVoiceMode.DUAL_DIALOGUE, RecitationVoiceMode.MALE, RecitationVoiceMode.FEMALE)
    AppSheet(
        onDismiss = onDismiss,
        title = if (isRosary) lang.pick("Santo Rosario", "Holy Rosary", "Santo Terço")
        else lang.pick("Coronilla", "Chaplet", "Coroinha"),
        tag = "rosary_options_sheet"
    ) {
        SheetLabel(lang.pick("Voces", "Voices", "Vozes"))
        AppSegmentedTabs(
            options = listOf(
                lang.pick("Diálogo", "Dialogue", "Diálogo"),
                lang.pick("Quien guía", "Leader", "Quem conduz"),
                lang.pick("Respuesta", "Response", "Resposta")
            ),
            selectedIndex = modes.indexOf(voiceMode).coerceAtLeast(0),
            onSelect = { onVoiceMode(modes[it]) },
            modifier = Modifier.testTag("rosary_voice_mode_picker")
        )
        Text(
            when (voiceMode) {
                RecitationVoiceMode.DUAL_DIALOGUE -> lang.pick(
                    "La voz reza las dos partes, en dos voces.",
                    "The narrator prays both parts, in two voices.",
                    "A voz reza as duas partes, em duas vozes."
                )
                RecitationVoiceMode.MALE -> lang.pick(
                    "La voz guía la primera parte; tú respondes.",
                    "The narrator leads the first part; you answer.",
                    "A voz conduz a primeira parte; você responde."
                )
                RecitationVoiceMode.FEMALE -> lang.pick(
                    "Tú guías la primera parte; la voz responde.",
                    "You lead the first part; the narrator answers.",
                    "Você conduz a primeira parte; a voz responde."
                )
            },
            style = styles.footnote,
            color = p.textSecondary,
            modifier = Modifier.padding(horizontal = design.spacing.gutter + 16.dp, vertical = 8.dp)
        )
        Spacer(Modifier.height(16.dp))
        SheetLabel(
            if (isRosary) lang.pick("Partes del Rosario", "Parts of the Rosary", "Partes do Rosário")
            else lang.pick("Pasos de la coronilla", "Steps of the chaplet", "Passos da coroinha")
        )
        RosaryLoopMap(
            beads = guide.beads.map { it.kind },
            activeBead = step.bead,
            onSelectBead = { bead -> guide.firstStepOn(bead).takeIf { it >= 0 }?.let(onGo) },
            modifier = Modifier
                .padding(horizontal = 72.dp, vertical = 4.dp)
                .testTag("rosary_loop_map")
        )
        Spacer(Modifier.height(12.dp))
        val prayed = lang.pick("Rezado", "Prayed", "Rezado")
        val now = lang.pick("Ahora", "Now", "Agora")
        AppListGroup {
            guide.sections.forEach { s ->
                AppListRow(
                    title = s.title,
                    subtitle = s.subtitle,
                    trailingText = when {
                        s.index < step.section -> prayed
                        s.index == step.section -> now
                        else -> null
                    },
                    showsNavigationAffordance = false,
                    selected = s.index == step.section,
                    onClick = { onGo(s.firstStep) },
                    modifier = Modifier.heightIn(min = 52.dp)
                )
            }
        }
        if (!isRosary) {
            Spacer(Modifier.height(24.dp))
            Column(Modifier.padding(horizontal = design.spacing.gutter + 8.dp)) {
                Text(
                    lang.pick(
                        "«A las tres de la tarde implora mi misericordia… En esta hora nada le será negado al alma que lo pida».",
                        "“At three o'clock, implore My mercy… In this hour nothing will be denied the soul that makes a request of Me.”",
                        "«Às três horas da tarde, implora a minha misericórdia… Nesta hora, nada será negado à alma que o pedir»."
                    ),
                    style = styles.reading.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic),
                    color = p.textPrimary
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    lang.pick("Diario de Santa Faustina", "Diary of Saint Faustina", "Diário de Santa Faustina"),
                    style = styles.footnote, color = p.textSecondary
                )
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

/** The mystery in full: its painting, Scripture, meditation, fruit and credit. */
@Composable
private fun MysterySheet(
    lang: AppLanguage,
    type: RosaryMysteryType,
    position: Int,
    title: String,
    scriptureRef: String,
    meditation: String,
    fruit: String,
    artPath: String?,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val credit = remember(artPath) { artPath?.let { ArtCredits.of(context, it) } }
    AppSheet(onDismiss = onDismiss, tag = "rosary_mystery_sheet") {
        Column(Modifier.padding(horizontal = design.spacing.gutter)) {
            if (artPath != null) {
                val bitmap = rememberAssetBitmap(artPath, 1080)
                val meta = rememberHeroMeta(artPath)
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(22.dp))
                        .background(p.glass)
                ) {
                    if (bitmap != null) {
                        Image(bitmap, null, contentScale = ContentScale.Crop, alignment = meta.alignment, modifier = Modifier.fillMaxSize())
                    }
                }
                Spacer(Modifier.height(18.dp))
            }
            Text(
                mysteryAnnouncement(lang, type, position, "").substringBefore(":"),
                style = styles.eyebrow, color = p.textSecondary
            )
            Spacer(Modifier.height(4.dp))
            Text(title, style = styles.title1, color = p.textPrimary)
            Spacer(Modifier.height(6.dp))
            Text(scriptureRef, style = styles.subhead, color = p.textSecondary)
            Spacer(Modifier.height(16.dp))
            Text(meditation, style = styles.reading, color = p.textPrimary)
            Spacer(Modifier.height(14.dp))
            Text(fruit, style = styles.callout.copy(fontWeight = FontWeight.Medium), color = p.textPrimary)
            if (credit != null) {
                Spacer(Modifier.height(18.dp))
                Text(
                    listOf(credit.artist, credit.title, credit.year).filter { it.isNotBlank() }.joinToString(" · "),
                    style = styles.footnote, color = p.textSecondary
                )
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

/** A run of litany lines set as call and answer, one pair to a paragraph. */
private fun litanyText(lines: List<LitanyLine>): String =
    lines.joinToString("\n\n") { "${it.call}\n    ℟ ${it.response}" }

/** A versicle or response sign in a quieter ink, then the words. */
private fun rubricated(sign: String, text: String, rubric: Color): AnnotatedString =
    buildAnnotatedString {
        withStyle(SpanStyle(color = rubric, fontWeight = FontWeight.SemiBold)) { append(sign) }
        append(" ")
        append(text)
    }
