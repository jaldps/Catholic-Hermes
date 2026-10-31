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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
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

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** A decade is one Our Father bead followed by ten Hail Mary beads. */
private const val HailMarysPerDecade = 10

/** The Our Father bead is cut larger than the ten that follow it. */
private const val LeadBeadScale = 1.5f

/** The crucifix and the centre medal stand a little prouder than a lead bead. */
private const val CrucifixScale = 1.6f
private const val MedalScale = 1.5f

/** Ring weight where the identity rules its edges rather than filling them. */
private val BeadHairline = 0.5.dp

// ---------------------------------------------------------------------------
// The guide as data
// ---------------------------------------------------------------------------

private enum class BeadKind { CRUCIFIX, LARGE, SMALL, MEDAL }

/** One physical bead on the rail; [row] groups the pendant, each decade and the medal. */
private data class BeadSpec(val kind: BeadKind, val row: Int)

/**
 * One thing to pray, said once.
 *
 * A prayer said N times in a row (three Hail Marys, ten Hail Marys) is N steps,
 * each knowing it is [repetition] of [count], so every bead can be marked.
 */
private data class GuideStep(
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

private data class GuideSection(
    val index: Int,
    val title: String,
    val subtitle: String,
    val firstStep: Int
)

/** A whole devotion: the steps, the beads they sit on, and its parts. */
private class PrayerGuide(
    val steps: List<GuideStep>,
    val beads: List<BeadSpec>,
    val sections: List<GuideSection>,
    val decadeCount: Int
) {
    fun firstStepOn(bead: Int): Int = steps.indexOfFirst { it.bead == bead }
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
private data class Said(val leader: String, val response: String = "")

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
private fun buildRosaryGuide(mystery: RosaryMystery, creed: Said, lang: AppLanguage): PrayerGuide {
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
private fun buildMercyGuide(
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

/** "Decade 3 of 5 · Hail Mary 4 of 10". */
private fun PrayerGuide.progressLine(step: GuideStep, lang: AppLanguage): String {
    val where = when {
        step.section == 0 -> openingTitle(lang)
        step.section <= decadeCount -> lang.pick(
            "Década ${step.section} de $decadeCount",
            "Decade ${step.section} of $decadeCount",
            "Dezena ${step.section} de $decadeCount"
        )
        else -> closingTitle(lang)
    }
    val what = if (step.count > 1) {
        lang.pick(
            "${step.name} ${step.repetition} de ${step.count}",
            "${step.name} ${step.repetition} of ${step.count}",
            "${step.name} ${step.repetition} de ${step.count}"
        )
    } else {
        step.name
    }
    return "$where · $what"
}

// ---------------------------------------------------------------------------
// Screen
// ---------------------------------------------------------------------------

/**
 * The Holy Rosary and the Divine Mercy chaplet, guided one prayer at a time
 * from the first Sign of the Cross to the last, with the bead it is said on
 * marked on a rail of the whole rosary, and spoken recitation.
 *
 * Each devotion is a [PrayerGuide] built once: the page, the rail, the progress
 * line and the narration all read the same step, so they cannot disagree.
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
    val lang = currentLanguage

    var selectedTab by remember { mutableIntStateOf(0) } // 0 = Santo Rosario, 1 = Coronilla de la Divina Misericordia

    val todayMystery = remember { repository.getTodayRosaryMystery() }

    // Rosary State
    var selectedMysteryType by remember { mutableStateOf(todayMystery.type) }
    var rosaryStepIndex by remember { mutableIntStateOf(0) }

    // Divine Mercy State
    var mercyStepIndex by remember { mutableIntStateOf(0) }

    val isPlayingAudio by ttsManager.isPlaying.collectAsState()
    val voiceMode by ttsManager.voiceMode.collectAsState()
    val currentlySpeaking by ttsManager.currentlySpeaking.collectAsState()

    // The full Creed and the Faustina opening prayers already live in the
    // repository; the guide reads them from there rather than restating them.
    val creedSource = remember { repository.vaticanPrayers.firstOrNull { it.id == "creed" } }
    val mercySource = remember { repository.divineMercySteps }
    val creed = creedSource?.let { it.leaderText.said(lang, it.responseText) }
        ?: mercySource.first { it.stepIndex == 2 }.let { it.leaderText.said(lang, it.responseText) }
    val opening = mercySource.firstOrNull { it.stepIndex == 1 }?.let { it.leaderText.said(lang, it.responseText) }

    val currentMystery = repository.getRosaryMystery(selectedMysteryType)
    val rosaryGuide = remember(selectedMysteryType, lang) {
        buildRosaryGuide(currentMystery, creed, lang)
    }
    val mercyGuide = remember(lang) { buildMercyGuide(creed, opening, lang) }

    val rosaryIndex = rosaryStepIndex.coerceIn(0, rosaryGuide.steps.lastIndex)
    val rosaryStep = rosaryGuide.steps[rosaryIndex]
    val mercyIndex = mercyStepIndex.coerceIn(0, mercyGuide.steps.lastIndex)
    val mercyStep = mercyGuide.steps[mercyIndex]

    fun caps(text: String) = if (design.decor.usesAllCapsLabels) text.uppercase() else text

    // A prayer split between leader and people is spoken as a dialogue; one
    // said straight through is read by a single voice.
    fun narrate(step: GuideStep) {
        if (step.response.isBlank()) {
            ttsManager.speakSingle(step.narration)
        } else {
            ttsManager.speakDialogue(step.leader, step.response)
        }
    }

    fun goRosary(index: Int) {
        val target = index.coerceIn(0, rosaryGuide.steps.lastIndex)
        rosaryStepIndex = target
        if (isPlayingAudio) narrate(rosaryGuide.steps[target])
    }

    fun goMercy(index: Int) {
        val target = index.coerceIn(0, mercyGuide.steps.lastIndex)
        mercyStepIndex = target
        if (isPlayingAudio) narrate(mercyGuide.steps[target])
    }

    val playLabel = lang.pick("Rezar en voz alta", "Pray aloud", "Rezar em voz alta")
    val pauseLabel = lang.pick("Pausar", "Pause", "Pausar")
    val previousLabel = lang.pick("Anterior", "Back", "Anterior")
    val nextLabel = lang.pick("Siguiente", "Next", "Seguinte")
    val prayedLabel = lang.pick("Rezado", "Prayed", "Rezado")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("rosary_screen")
    ) {
        item {
            // The masthead names the screen and holds still; the segmented
            // control below it names the two devotions it carries.
            AppScreenTitle(
                title = lang.pick("Rosario y coronilla", "Rosary & chaplet", "Terço e coroinha"),
                subtitle = if (selectedTab == 0) {
                    lang.pick(
                        "Paso a paso, de la señal de la cruz a la Salve, con lo que se reza en cada cuenta.",
                        "Step by step, from the Sign of the Cross to the Hail, Holy Queen, with what to pray on every bead.",
                        "Passo a passo, do sinal da cruz à Salve-Rainha, com o que se reza em cada conta."
                    )
                } else {
                    lang.pick(
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
                    lang.pick("El Santo Rosario", "The Holy Rosary", "O Santo Terço"),
                    lang.pick("Divina Misericordia", "Divine Mercy", "Divina Misericórdia")
                ),
                selectedIndex = selectedTab,
                onSelect = {
                    if (it != selectedTab) ttsManager.stop()
                    selectedTab = it
                },
                modifier = tabsModifier
            )
        }

        when (selectedTab) {
            0 -> {
                // Holy Rosary
                item {
                    Spacer(Modifier.height(design.spacing.sectionGap / 2))
                    AppBanner(
                        title = lang.pick("Misterios de hoy", "Today's mysteries", "Mistérios de hoje"),
                        body = todayMystery.type.displayName.get(lang) +
                            " · " + todayMystery.type.recommendedDays.get(lang),
                        onClick = {
                            selectedMysteryType = todayMystery.type
                            rosaryStepIndex = 0
                        }
                    )
                }

                item {
                    AppSectionHeader(
                        title = lang.pick(
                            "Selecciona el misterio",
                            "Select the mystery",
                            "Selecione o mistério"
                        )
                    )
                    AppListGroup {
                        RosaryMysteryType.entries.forEach { mType ->
                            val isToday = mType == todayMystery.type
                            AppListRow(
                                title = mType.displayName.get(lang),
                                subtitle = mType.recommendedDays.get(lang),
                                leadingIcon = when (mType) {
                                    RosaryMysteryType.JOYFUL -> Icons.Default.LocalFlorist
                                    RosaryMysteryType.SORROWFUL -> Icons.Default.WaterDrop
                                    RosaryMysteryType.GLORIOUS -> Icons.Default.AutoAwesome
                                    RosaryMysteryType.LUMINOUS -> Icons.Default.LightMode
                                },
                                trailingText = if (isToday) lang.pick("Hoy", "Today", "Hoje") else null,
                                showsNavigationAffordance = false,
                                selected = mType == selectedMysteryType,
                                onClick = {
                                    if (mType != selectedMysteryType) {
                                        ttsManager.stop()
                                        selectedMysteryType = mType
                                        rosaryStepIndex = 0
                                    }
                                }
                            )
                        }
                    }
                }

                // Where you are on the beads, and the way forward
                item {
                    AppOrnament()
                    GuideControlsCard(
                        eyebrow = currentMystery.type.displayName.get(lang),
                        guide = rosaryGuide,
                        index = rosaryIndex,
                        lang = lang,
                        previousLabel = previousLabel,
                        nextLabel = nextLabel,
                        audioLabel = if (isPlayingAudio) pauseLabel else playLabel,
                        isPlayingAudio = isPlayingAudio,
                        onGo = { goRosary(it) },
                        onToggleAudio = {
                            if (isPlayingAudio) ttsManager.stop() else narrate(rosaryStep)
                        },
                        railTag = "prayer_bead_counter",
                        previousTag = "prev_bead_button",
                        nextTag = "next_bead_button",
                        audioTag = "toggle_prayer_audio_button"
                    ) {
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
                                text = caps(lang.pick("Modo de voz", "Voice mode", "Modo de voz")),
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
                                label = lang.pick("Diálogo dual", "Dual dialogue", "Diálogo duplo"),
                                selected = voiceMode == RecitationVoiceMode.DUAL_DIALOGUE,
                                onClick = { ttsManager.setVoiceMode(RecitationVoiceMode.DUAL_DIALOGUE) }
                            )
                            AppChip(
                                label = lang.pick("Voz guía", "Leader voice", "Voz guia"),
                                selected = voiceMode == RecitationVoiceMode.MALE,
                                onClick = { ttsManager.setVoiceMode(RecitationVoiceMode.MALE) }
                            )
                            AppChip(
                                label = lang.pick("Respuesta", "Response", "Resposta"),
                                selected = voiceMode == RecitationVoiceMode.FEMALE,
                                onClick = { ttsManager.setVoiceMode(RecitationVoiceMode.FEMALE) }
                            )
                        }
                    }
                }

                // What to pray now, in full
                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    StepCard(step = rosaryStep, lang = lang, spokenNow = currentlySpeaking)
                }

                // While the decade is prayed, its mystery stays in view
                val decadeIndex = rosaryStep.section - 1
                val inDecade = decadeIndex in currentMystery.decades.indices
                if (inDecade && rosaryStep.meditation == null) {
                    val currentDecade = currentMystery.decades[decadeIndex]
                    item {
                        Spacer(Modifier.height(design.spacing.cardGap))
                        AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                            CardHeading(
                                eyebrow = lang.pick(
                                    "${currentDecade.number}º Misterio",
                                    "Mystery ${currentDecade.number}",
                                    "${currentDecade.number}º Mistério"
                                ),
                                detail = currentDecade.scriptureRef
                            )

                            Spacer(Modifier.height(design.spacing.cardGap / 2))

                            Text(
                                text = currentDecade.title.get(lang),
                                style = styles.sectionTitle,
                                color = palette.textPrimary
                            )

                            Spacer(Modifier.height(design.spacing.cardGap))

                            Text(
                                text = currentDecade.meditation.get(lang),
                                style = styles.quote,
                                color = palette.textPrimary,
                                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                            )

                            Spacer(Modifier.height(design.spacing.cardPadding))
                            AppDivider()
                            Spacer(Modifier.height(design.spacing.cardGap))

                            Text(
                                text = caps(
                                    lang.pick(
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
                                text = currentDecade.fruitOfMystery.get(lang),
                                style = styles.rowTitle,
                                color = palette.accent
                            )
                        }
                    }
                }

                // The parts of the Rosary, to see the whole and jump to one
                item {
                    AppSectionHeader(
                        title = lang.pick("Partes del Rosario", "Parts of the Rosary", "Partes do Rosário")
                    )
                    AppListGroup {
                        rosaryGuide.sections.forEach { section ->
                            AppListRow(
                                title = section.title,
                                subtitle = section.subtitle,
                                trailingText = if (section.index < rosaryStep.section) prayedLabel else null,
                                showsNavigationAffordance = false,
                                selected = section.index == rosaryStep.section,
                                onClick = { goRosary(section.firstStep) }
                            )
                        }
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
                        body = lang.pick(
                            "La Hora de la Gran Misericordia",
                            "The Hour of Great Mercy",
                            "A Hora da Grande Misericórdia"
                        )
                    )
                }

                item {
                    AppOrnament()
                    GuideControlsCard(
                        eyebrow = lang.pick("Coronilla", "Chaplet", "Coroinha"),
                        guide = mercyGuide,
                        index = mercyIndex,
                        lang = lang,
                        previousLabel = previousLabel,
                        nextLabel = nextLabel,
                        audioLabel = if (isPlayingAudio) pauseLabel else playLabel,
                        isPlayingAudio = isPlayingAudio,
                        onGo = { goMercy(it) },
                        onToggleAudio = {
                            if (isPlayingAudio) ttsManager.stop() else narrate(mercyStep)
                        },
                        railTag = "mercy_step_counter",
                        previousTag = "prev_mercy_step_button",
                        nextTag = "next_mercy_step_button",
                        audioTag = "toggle_mercy_audio_button"
                    )
                }

                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    StepCard(step = mercyStep, lang = lang, spokenNow = currentlySpeaking)
                }

                item {
                    AppSectionHeader(
                        title = lang.pick(
                            "Pasos de la coronilla",
                            "Steps of the chaplet",
                            "Passos da coroinha"
                        )
                    )
                    AppListGroup {
                        mercyGuide.sections.forEach { section ->
                            AppListRow(
                                title = section.title,
                                subtitle = section.subtitle,
                                trailingText = if (section.index < mercyStep.section) prayedLabel else null,
                                showsNavigationAffordance = false,
                                selected = section.index == mercyStep.section,
                                onClick = { goMercy(section.firstStep) }
                            )
                        }
                    }
                }

                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        Text(
                            text = lang.pick(
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
                                lang.pick(
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

                item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
            }
        }
    }
}

/**
 * The rail of the whole rosary, the honest progress line and the controls.
 * [extra] closes the card (the Rosary puts its voice-mode chips there).
 */
@Composable
private fun GuideControlsCard(
    eyebrow: String,
    guide: PrayerGuide,
    index: Int,
    lang: AppLanguage,
    previousLabel: String,
    nextLabel: String,
    audioLabel: String,
    isPlayingAudio: Boolean,
    onGo: (Int) -> Unit,
    onToggleAudio: () -> Unit,
    railTag: String,
    previousTag: String,
    nextTag: String,
    audioTag: String,
    extra: @Composable ColumnScope.() -> Unit = {}
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val step = guide.steps[index]

    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
        CardHeading(
            eyebrow = eyebrow,
            detail = lang.pick(
                "Paso ${index + 1} de ${guide.steps.size}",
                "Step ${index + 1} of ${guide.steps.size}",
                "Passo ${index + 1} de ${guide.steps.size}"
            )
        )

        Spacer(Modifier.height(design.spacing.cardPadding))

        BeadRail(
            beads = guide.beads,
            activeIndex = step.bead,
            onSelectBead = { bead ->
                val target = guide.firstStepOn(bead)
                if (target >= 0) onGo(target)
            },
            modifier = Modifier.testTag(railTag)
        )

        Spacer(Modifier.height(design.spacing.cardGap))

        Text(
            text = guide.progressLine(step, lang),
            style = styles.rowTitle,
            color = design.palette.textPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(design.spacing.cardPadding))

        RecitationControls(
            previousLabel = previousLabel,
            nextLabel = nextLabel,
            audioLabel = audioLabel,
            isPlayingAudio = isPlayingAudio,
            canGoPrevious = index > 0,
            canGoNext = index < guide.steps.lastIndex,
            onPrevious = { onGo(index - 1) },
            onNext = { onGo(index + 1) },
            onToggleAudio = onToggleAudio,
            previousTag = previousTag,
            nextTag = nextTag,
            audioTag = audioTag
        )

        extra()
    }
}

/** The current step: its name, what to do, and the full text to pray. */
@Composable
private fun StepCard(step: GuideStep, lang: AppLanguage, spokenNow: String?) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
        if (step.optional) {
            Text(
                text = lang.pick("Opcional", "Optional", "Opcional").let {
                    if (design.decor.usesAllCapsLabels) it.uppercase() else it
                },
                style = styles.sectionEyebrow,
                color = palette.textTertiary
            )
            Spacer(Modifier.height(design.spacing.cardGap / 3))
        }

        Text(
            text = step.name,
            style = styles.sectionTitle,
            color = palette.textPrimary
        )

        if (step.intention != null) {
            Spacer(Modifier.height(design.spacing.cardGap / 3))
            Text(
                text = step.intention,
                style = styles.rowTitle,
                color = palette.accent
            )
        }

        Spacer(Modifier.height(design.spacing.cardGap / 2))

        Text(
            text = step.rubric,
            style = styles.rowSubtitle,
            color = palette.textSecondary,
            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
        )

        Spacer(Modifier.height(design.spacing.cardGap))

        val leaderSpoken = spokenNow != null &&
            (spokenNow == step.leader || spokenNow == step.narration)
        PrayerDialogue(
            leaderText = step.leader,
            responseText = step.response,
            leaderSpoken = leaderSpoken,
            responseSpoken = spokenNow != null && spokenNow == step.response
        )

        if (step.meditation != null) {
            Spacer(Modifier.height(design.spacing.cardGap))
            Text(
                text = step.meditation,
                style = styles.scripture,
                color = if (leaderSpoken) palette.accent else palette.textSecondary,
                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
            )
        }

        if (step.detail != null) {
            Spacer(Modifier.height(design.spacing.cardGap))
            Text(
                text = step.detail,
                style = styles.rowSubtitle,
                color = palette.textTertiary
            )
        }

        if (step.response.isNotBlank()) {
            Spacer(Modifier.height(design.spacing.cardPadding))
            AppDivider()
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = lang.pick(
                    "℣ dice quien guía · ℟ responden todos. Si rezas solo, di las dos partes.",
                    "℣ the leader says · ℟ everyone answers. Praying alone? Say both parts.",
                    "℣ diz quem conduz · ℟ todos respondem. Rezando sozinho? Diga as duas partes."
                ),
                style = styles.rowSubtitle,
                color = palette.textTertiary,
                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
            )
        }
    }
}

/**
 * The whole rosary: the pendant, each decade on its own line, and the medal.
 *
 * The idiom follows the tokens rather than a flag: where the identity rules its
 * edges (`cardBorderWidth`) the bead is an unfilled ring of leaf and only the
 * current one is inked solid; where it fills them, the bead is a clean circle
 * tinted by its state. Beads before the current one are marked as prayed.
 */
@Composable
private fun BeadRail(
    beads: List<BeadSpec>,
    activeIndex: Int,
    onSelectBead: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val haptics = LocalHapticFeedback.current

    val engraved = design.depth.cardBorderWidth > 0.dp
    val ringWidth = maxOf(
        design.depth.cardBorderWidth,
        if (design.depth.usesHairlines) BeadHairline else 0.dp
    )
    val rows = remember(beads) {
        beads.indices.groupBy { beads[it].row }.toSortedMap().values.toList()
    }

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val gap = design.spacing.cardGap / 3
        // One bead size for every line, set by the most crowded one.
        val unit = rows.minOf { row ->
            val slots = row.sumOf { beadScale(beads[it].kind).toDouble() }.toFloat()
            (maxWidth - gap * (row.size - 1)) / slots
        }.coerceIn(0.dp, design.spacing.rowMinHeight / 2)

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(gap * 2)
        ) {
            rows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(gap, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    row.forEach { index ->
                        Bead(
                            kind = beads[index].kind,
                            size = unit * beadScale(beads[index].kind),
                            isActive = index == activeIndex,
                            isTold = index < activeIndex,
                            engraved = engraved,
                            ringWidth = ringWidth,
                            onClick = {
                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                onSelectBead(index)
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun beadScale(kind: BeadKind): Float = when (kind) {
    BeadKind.CRUCIFIX -> CrucifixScale
    BeadKind.LARGE -> LeadBeadScale
    BeadKind.SMALL -> 1f
    BeadKind.MEDAL -> MedalScale
}

@Composable
private fun Bead(
    kind: BeadKind,
    size: Dp,
    isActive: Boolean,
    isTold: Boolean,
    engraved: Boolean,
    ringWidth: Dp,
    onClick: () -> Unit
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val spec = tween<Color>(
        durationMillis = design.motion.fastMillis,
        easing = design.motion.standardEasing
    )

    if (kind == BeadKind.CRUCIFIX) {
        // A cross drawn from two bars, inked by the same states as a bead.
        val ink by animateColorAsState(
            targetValue = when {
                isActive -> palette.accent
                isTold -> if (engraved) palette.gold else palette.accent.copy(alpha = 0.55f)
                engraved -> palette.gold.copy(alpha = 0.45f)
                else -> palette.textTertiary
            },
            animationSpec = spec,
            label = "crucifixInk"
        )
        val bar = RoundedCornerShape(percent = 50)
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .size(width = size * 0.22f, height = size * 0.92f)
                    .clip(bar)
                    .background(ink)
            )
            Box(
                Modifier
                    .offset(y = -(size * 0.16f))
                    .size(width = size * 0.64f, height = size * 0.22f)
                    .clip(bar)
                    .background(ink)
            )
        }
        return
    }

    val shape = if (kind == BeadKind.MEDAL) RoundedCornerShape(percent = 30) else CircleShape
    val fill by animateColorAsState(
        targetValue = when {
            isActive -> palette.accent
            engraved -> Color.Transparent
            isTold -> palette.accent.copy(alpha = 0.32f)
            else -> palette.selectionTint
        },
        animationSpec = spec,
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
            .size(size)
            .clip(shape)
            .background(fill)
            .border(ringWidth, ring, shape)
            .clickable(onClick = onClick)
    )
}

/**
 * Navigation across the steps and the narration toggle.
 *
 * Stacked rather than set three-across: "Siguiente" and "Seguinte" in Roman
 * capitals are wide enough to break a three-button row on a narrow screen.
 * Back and Next share a row; narration has a row of its own.
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

/**
 * The text to pray. With a response, leader and people are marked ℣ and ℟, the
 * signs set in rubric as a missal prints them;
 * a prayer said straight through is set plain. The line being spoken is in rubric.
 */
@Composable
private fun PrayerDialogue(
    leaderText: String,
    responseText: String,
    leaderSpoken: Boolean,
    responseSpoken: Boolean
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    Column(Modifier.widthIn(max = design.spacing.readingMaxWidth)) {
        if (responseText.isBlank()) {
            Text(
                text = leaderText,
                style = styles.quote,
                color = if (leaderSpoken) palette.accent else palette.textPrimary
            )
        } else {
            Text(
                text = rubricated("\u2123", leaderText, palette.accent),
                style = styles.quote,
                color = if (leaderSpoken) palette.accent else palette.textPrimary
            )
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = rubricated("\u211F", responseText, palette.accent),
                style = styles.quote,
                color = if (responseSpoken) palette.accent else palette.textSecondary
            )
        }
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

/** A run of litany lines set as call and answer, one pair to a paragraph. */
private fun litanyText(lines: List<LitanyLine>): String =
    lines.joinToString("\n\n") { "${it.call}\n    \u211F ${it.response}" }

/** A versicle or response sign in the rubric colour, then the words in ink. */
private fun rubricated(sign: String, text: String, rubric: Color): AnnotatedString =
    buildAnnotatedString {
        withStyle(SpanStyle(color = rubric, fontWeight = FontWeight.SemiBold)) { append(sign) }
        append("\u2002")
        append(text)
    }
