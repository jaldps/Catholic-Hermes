package com.example.ui.screens

import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.repository.Chaplet
import com.example.data.repository.ChapletBeadKind
import com.example.data.repository.ChapletContent
import com.example.data.repository.ChapletPrayers
import com.example.data.repository.ChapletVariable
import com.example.ui.components.BeadKind

// ---------------------------------------------------------------------------
// The chaplet engine: any chaplet of the catalogue as the same guided prayer
// the Rosary and the Divine Mercy chaplet use. Pure JVM (no Compose, no
// Android), so the sequence, the beads and the variables are unit-tested.
//
//  chaplet.steps  --ChapletPlan-->  one planned step per saying, each on a bead
//  ChapletPlan    --buildChapletGuide-->  PrayerGuide (what the screen reads)
// ---------------------------------------------------------------------------

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** One physical bead of the instrument, drawn from the chaplet's pattern. */
internal data class FlatBead(val kind: ChapletBeadKind, val group: Int, val color: Long?, val label: LocalizedText?)

/** One saying of a step: [repetition] of [repeatCount], resting on [bead] (-1 when the chaplet has no string). */
internal data class PlannedStep(
    val def: Int,
    val repetition: Int,
    val repeatCount: Int,
    val bead: Int,
    val section: Int
)

/**
 * The generic step map of a chaplet. Every repetition is a step; each step knows its bead. A group
 * with several beads hands out one bead per saying (and starts over if the steps pass it twice);
 * a step off the strand rests on the bead of the step before it. Beads no step rests on belong to
 * the instrument but not to this devotion: they are drawn dimmed and cannot be picked.
 */
internal class ChapletPlan(val chaplet: Chaplet) : StepMap {
    val beads: List<FlatBead>
    val steps: List<PlannedStep>
    /** First planned step of each section; a section opens at every step that carries a meditation. */
    val sectionStarts: List<Int>
    /** True when the steps before the first meditation form a section of their own («Inicio»). */
    val hasOpening: Boolean

    override val stepCount: Int get() = steps.size
    override fun isOptional(index: Int): Boolean = false

    /** Beads that no step rests on. */
    val dimmed: Set<Int>
    /** The strand is a rosary's (crucifix, pendant, medal, then the loop): the Options map draws it as one. */
    val rosaryShaped: Boolean
    private val firstOnBead: Map<Int, Int>

    init {
        val flat = ArrayList<FlatBead>()
        val groupStart = IntArray(chaplet.beads.size)
        chaplet.beads.forEachIndexed { g, group ->
            groupStart[g] = flat.size
            repeat(group.count) { flat += FlatBead(group.kind, g, group.color, group.label) }
        }
        beads = flat
        val used = IntArray(chaplet.beads.size)
        val raw = ArrayList<IntArray>() // def, repetition, repeatCount, bead(-2 = off the strand)
        chaplet.steps.forEachIndexed { d, def ->
            repeat(def.repeat) { r ->
                val g = def.bead?.takeIf { it in chaplet.beads.indices }
                val bead = if (g == null || flat.isEmpty()) -2 else {
                    val n = chaplet.beads[g].count
                    (groupStart[g] + used[g] % n).also { used[g]++ }
                }
                raw += intArrayOf(d, r + 1, def.repeat, bead)
            }
        }
        // Off-strand steps rest on the previous bead, or on the next one at the very start.
        var previous = -2
        raw.forEach { if (it[3] != -2) previous = it[3] else if (previous != -2) it[3] = previous }
        var following = -2
        for (i in raw.indices.reversed()) { if (raw[i][3] != -2) following = raw[i][3] else if (following != -2 && raw[i][3] == -2) raw[i][3] = following }
        val starts = ArrayList<Int>()
        var section = -1
        val planned = raw.mapIndexed { i, it ->
            val opens = it[1] == 1 && chaplet.steps[it[0]].meditation != null
            if (section < 0 || (opens && i > 0)) { section++; starts += i }
            PlannedStep(it[0], it[1], it[2], if (it[3] < 0) 0 else it[3], section)
        }
        steps = planned
        sectionStarts = starts
        hasOpening = chaplet.steps.firstOrNull()?.meditation == null && starts.size > 1
        val resting = planned.map { it.bead }.toSet()
        dimmed = if (flat.isEmpty()) emptySet() else flat.indices.filter { it !in resting }.toSet()
        val kinds = flat.map { it.kind }
        rosaryShaped = kinds.size >= 20 && kinds.take(7) == listOf(
            ChapletBeadKind.CROSS, ChapletBeadKind.LARGE, ChapletBeadKind.SMALL, ChapletBeadKind.SMALL, ChapletBeadKind.SMALL,
            ChapletBeadKind.LARGE, ChapletBeadKind.MEDAL
        )
        firstOnBead = planned.withIndex().groupBy({ it.value.bead }, { it.index }).mapValues { it.value.first() }
    }

    fun firstStepOn(bead: Int): Int = firstOnBead[bead] ?: -1
    fun stepsOn(bead: Int): List<Int> = steps.indices.filter { steps[it].bead == bead }
}

/** How a person's answers replace the chaplet's markers. They live in memory only. */
internal enum class DeceasedGender { MALE, FEMALE }

internal data class ChapletVariables(
    val deceasedName: String = "",
    val deceasedGender: DeceasedGender? = null,
    val intention: String = ""
) {
    /** Replaces `{{nombre}}`, `{{intencion}}` and the `{{él|ella}}` / `{{him|her}}` / `{{ele|ela}}` forms. */
    fun apply(text: String, lang: AppLanguage): String {
        if (!text.contains("{{")) return text
        val out = StringBuilder(text.length)
        var cursor = 0
        while (true) {
            val start = text.indexOf("{{", cursor)
            if (start < 0) break
            val end = text.indexOf("}}", start + 2)
            if (end < 0) break
            val key = text.substring(start + 2, end).trim()
            out.append(text, cursor, start)
            if (key.contains('}')) {
                out.append(text, start, end + 2)
            } else {
                out.append(
                    when {
                        key == "nombre" -> deceasedName.trim().ifEmpty { lang.pick("N.", "N.", "N.") }
                        key == "intencion" -> intention.trim().ifEmpty { lang.pick("esta intención", "this intention", "esta intenção") }
                        key.contains('|') -> {
                            val (masculine, feminine) = key.split('|', limit = 2).map { it.trim() }
                            when (deceasedGender) {
                                DeceasedGender.MALE -> masculine
                                DeceasedGender.FEMALE -> feminine
                                null -> "$masculine/$feminine"
                            }
                        }
                        else -> text.substring(start, end + 2)
                    }
                )
            }
            cursor = end + 2
        }
        out.append(text, cursor, text.length)
        return out.toString()
    }
}

/** In-memory answers, per chaplet. Never written to disk (like the confession draft). */
internal object ChapletVariablesMemory {
    private val answers = androidx.compose.runtime.mutableStateMapOf<String, ChapletVariables>()
    fun of(id: String): ChapletVariables? = answers[id]
    fun put(id: String, value: ChapletVariables) { answers[id] = value }
    fun forget(id: String) { answers.remove(id) }
    fun clear() = answers.clear()
}

/** Whether the chaplet needs the person's answers before the first prayer. */
internal fun Chaplet.needsAnswers(): Boolean = variables.isNotEmpty()

/** The words after «V.» / «R.» (the screen draws the marks). */
private fun String.withoutVoiceMark(): String =
    trimStart().let { s -> Regex("""^(?:[VR℣℟]\s*[./]\s*)""").replace(s, "") }.trim()

private fun beadName(kind: ChapletBeadKind, label: LocalizedText?, lang: AppLanguage): String =
    label?.get(lang)?.takeIf { it.isNotBlank() } ?: when (kind) {
        ChapletBeadKind.CROSS -> lang.pick("Cruz", "Cross", "Cruz")
        ChapletBeadKind.MEDAL -> lang.pick("Medalla", "Medal", "Medalha")
        ChapletBeadKind.LARGE -> lang.pick("Cuenta grande", "Large bead", "Conta grande")
        ChapletBeadKind.SMALL -> lang.pick("Cuenta pequeña", "Small bead", "Conta pequena")
        ChapletBeadKind.SEPARATOR -> lang.pick("Separador", "Spacer bead", "Separador")
        ChapletBeadKind.KNOT -> lang.pick("Nudo", "Knot", "Nó")
    }

/** What a beginner does on this bead, when the source gives no rubric of its own. */
private fun beadHint(kind: ChapletBeadKind, groupCount: Int, lang: AppLanguage): String = when (kind) {
    ChapletBeadKind.CROSS -> lang.pick("En la cruz.", "On the cross.", "Na cruz.")
    ChapletBeadKind.MEDAL -> lang.pick("En la medalla.", "On the medal.", "Na medalha.")
    ChapletBeadKind.LARGE -> lang.pick("En la cuenta grande.", "On the large bead.", "Na conta grande.")
    ChapletBeadKind.SMALL ->
        if (groupCount > 1) lang.pick("En cada una de las $groupCount cuentas pequeñas.", "On each of the $groupCount small beads.", "Em cada uma das $groupCount contas pequenas.")
        else lang.pick("En la cuenta pequeña.", "On the small bead.", "Na conta pequena.")
    ChapletBeadKind.SEPARATOR -> lang.pick("En la cuenta separadora.", "On the spacer bead.", "Na conta separadora.")
    ChapletBeadKind.KNOT -> lang.pick("En el nudo.", "On the knot.", "No nó.")
}

internal fun ChapletBeadKind.toBeadKind(): BeadKind = when (this) {
    ChapletBeadKind.CROSS -> BeadKind.CRUCIFIX
    ChapletBeadKind.MEDAL -> BeadKind.MEDAL
    ChapletBeadKind.LARGE -> BeadKind.LARGE
    ChapletBeadKind.SMALL -> BeadKind.SMALL
    ChapletBeadKind.SEPARATOR -> BeadKind.SEPARATOR
    ChapletBeadKind.KNOT -> BeadKind.KNOT
}

/** A chaplet as the screen reads it: the guide plus how its string is drawn. */
internal class ChapletGuide(val plan: ChapletPlan, val guide: PrayerGuide)

/**
 * Builds the [PrayerGuide] of [chaplet] in [lang]. `prayerRef`s are resolved with [prayers]; a
 * reference that cannot be resolved is a content error and throws (the catalogue test forbids it).
 */
internal fun buildChapletGuide(
    chaplet: Chaplet,
    lang: AppLanguage,
    prayers: ChapletPrayers,
    variables: ChapletVariables = ChapletVariables(),
    plan: ChapletPlan = ChapletPlan(chaplet)
): ChapletGuide {
    fun LocalizedText.text() = variables.apply(get(lang), lang)
    val steps = plan.steps.map { planned ->
        val def = chaplet.steps[planned.def]
        val flat = plan.beads.getOrNull(planned.bead)
        val group = flat?.let { chaplet.beads[it.group] }
        val rubricOwn = def.rubric?.text()
        val hint = rubricOwn ?: group?.let { beadHint(it.kind, it.count, lang) }
            ?: if (!chaplet.hasBeads) lang.pick("Sin cuentas: cuenta las oraciones.", "No beads: count the prayers.", "Sem contas: conte as orações.") else ""
        val meditation = def.meditation?.takeIf { planned.repetition == 1 }?.text()
        val name: String
        val leader: String
        val response: String
        when (val c = def.content) {
            is ChapletContent.PrayerRef -> {
                val prayer = requireNotNull(prayers.find(c.id)) { "Unknown prayerRef ${c.id} in ${chaplet.id}" }
                name = prayer.title.get(lang)
                leader = variables.apply(prayer.leaderText.get(lang), lang)
                response = prayer.responseText?.get(lang)?.let { variables.apply(it, lang) }.orEmpty()
            }
            is ChapletContent.Text -> {
                name = flat?.let { beadName(it.kind, it.label, lang) } ?: lang.pick("Oración", "Prayer", "Oração")
                leader = c.text.text()
                response = ""
            }
            is ChapletContent.Vr -> {
                name = flat?.let { beadName(it.kind, it.label, lang) } ?: lang.pick("Oración", "Prayer", "Oração")
                leader = c.v.text().withoutVoiceMark()
                response = c.r.text().withoutVoiceMark()
            }
        }
        GuideStep(
            name = name, rubric = hint, leader = leader, response = response, meditation = meditation,
            bead = planned.bead, section = planned.section,
            repetition = planned.repetition, count = planned.repeatCount,
            chapletRubric = rubricOwn, meditationFirst = true
        )
    }
    // Sections: «Inicio» when the first lines come before any meditation, then «Parte i de n».
    val parts = plan.sectionStarts.size - if (plan.hasOpening) 1 else 0
    val labels = plan.sectionStarts.indices.map { i ->
        val n = i + (if (plan.hasOpening) 0 else 1)
        when {
            plan.hasOpening && i == 0 -> lang.pick("Inicio", "Opening", "Início")
            parts <= 1 -> chaplet.title.get(lang)
            else -> lang.pick("Parte $n de $parts", "Part $n of $parts", "Parte $n de $parts")
        }
    }
    val sectionMeditations = plan.sectionStarts.map { first -> steps[first].meditation }
    val sections = plan.sectionStarts.mapIndexed { i, first ->
        GuideSection(i, labels[i], sectionMeditations[i]?.let(::firstSentence).orEmpty(), first)
    }
    val beadSpecs = plan.beads.mapIndexed { i, b -> BeadSpec(b.kind.toBeadKind(), b.group, b.color, i in plan.dimmed) }
    return ChapletGuide(
        plan,
        PrayerGuide(
            steps = steps, beads = beadSpecs, sections = sections, decadeCount = sections.lastIndex.coerceAtLeast(0),
            partLabels = labels, sectionMeditations = sectionMeditations
        )
    )
}

/** The first sentence of [text], at most about 110 characters, for a list row. */
internal fun firstSentence(text: String): String {
    val flat = text.replace('\n', ' ').trim()
    val end = Regex("""[.!?…»”](\s|$)""").find(flat)?.range?.last?.let { if (flat[it].isWhitespace()) it - 1 else it } ?: flat.lastIndex
    val sentence = flat.substring(0, (end + 1).coerceAtMost(flat.length))
    return if (sentence.length <= 110) sentence else sentence.take(107).trimEnd() + "…"
}

/** Which variables the sheet has to ask for, in order. */
internal fun Chaplet.questions(): List<ChapletVariable> = variables
