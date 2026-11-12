package com.example.ui.screens

import com.example.data.models.AppLanguage
import com.example.data.models.ApologeticsQuestion
import com.example.data.models.ChurchHistoryEpoch
import com.example.data.models.ChurchSchismHistory
import com.example.ui.components.TextFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

// -----------------------------------------------------------------------------
// Pure helpers for the Doctrine & Study area: how data becomes display text.
// Nothing here touches Compose, so every rule has a plain unit test.
// -----------------------------------------------------------------------------

internal fun AppLanguage.tr(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

// ------------------------------------------------------------ clarifications

/** The data once carried a stray leading ": " on many clarifications; never show it. */
internal fun cleanClarification(text: String): String =
    text.trimStart().removePrefix(":").trimStart()

// ------------------------------------------------------------ titles/aliases

internal data class TitleAlias(val title: String, val alias: String?)

private val trailingParenthetical = Regex("""^(.*?)\s*\(([^()]+)\)\s*$""")

/** "Nuestra Señora del Valle (Virgen del Valle)" -> title + alias. */
internal fun splitAlias(raw: String): TitleAlias {
    val m = trailingParenthetical.find(raw.trim()) ?: return TitleAlias(raw.trim(), null)
    val main = m.groupValues[1].trim()
    val alias = m.groupValues[2].trim()
    if (main.isEmpty()) return TitleAlias(raw.trim(), null)
    return TitleAlias(main, alias)
}

// ----------------------------------------------------------------- numerals

private val romanValues = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100)

internal fun romanToInt(roman: String): Int? {
    if (roman.isEmpty()) return null
    var total = 0
    var prev = 0
    for (ch in roman.reversed()) {
        val v = romanValues[ch] ?: return null
        total += if (v < prev) -v else v
        prev = maxOf(prev, v)
    }
    return total
}

private fun ordinalEn(n: Int): String = when {
    n % 100 in 11..13 -> "${n}th"
    n % 10 == 1 -> "${n}st"
    n % 10 == 2 -> "${n}nd"
    n % 10 == 3 -> "${n}rd"
    else -> "${n}th"
}

// ----------------------------------------------------------------- centuries

internal data class CenturySpan(val centuries: String, val years: String?)

private val centuryPattern = Regex(
    """^Siglos?\s+([IVXLC]+)(?:\s*[-–]\s*([IVXLC]+))?\s*(?:\((?:Años\s+)?([^)]*)\))?\s*$"""
)

/**
 * "Siglos I - IV (Años 33 - 313 d.C.)" -> ("Siglos I–IV", "33–313"), localised.
 * Unknown shapes come back as the raw text with en dashes, so nothing is lost.
 */
internal fun centurySpan(range: String, language: AppLanguage): CenturySpan {
    val m = centuryPattern.find(range.trim())
        ?: return CenturySpan(range.trim().replace(Regex("""\s+-\s+"""), "–"), null)
    val from = m.groupValues[1]
    val to = m.groupValues[2]
    val single = to.isEmpty() || to == from
    val span = if (single) from else "$from–$to"
    val label = if (single) {
        language.tr("Siglo $span", "Century $span", "Século $span")
    } else {
        language.tr("Siglos $span", "Centuries $span", "Séculos $span")
    }
    val years = m.groupValues[3]
        .replace(Regex("""\s*d\.\s?C\.?""", RegexOption.IGNORE_CASE), "")
        .replace(Regex("""\s*[-–]\s*"""), "–")
        .trim()
        .ifEmpty { null }
    return CenturySpan(label, years)
}

/** First year a range starts in; falls back to the century number. */
internal fun epochStartYear(range: String): Int {
    val m = centuryPattern.find(range.trim()) ?: return Int.MAX_VALUE
    val years = m.groupValues[3]
    Regex("""\d{1,4}""").find(years)?.value?.toIntOrNull()?.let { return it }
    val century = romanToInt(m.groupValues[1]) ?: return Int.MAX_VALUE
    return (century - 1) * 100 + 1
}

/** The order the timeline reads in. `modern` is the closing overview. */
internal val EPOCH_ORDER = listOf(
    "apostolic", "councils", "papal_states_medieval", "gregorian_reform", "mendicant_orders",
    "reformation_trent", "baroque_missions", "enlightenment_suppression", "vatican_i_modern", "modern"
)

/** Data indices of the epochs in chronological order (unknown ids after, by start year). */
internal fun chronologicalEpochIndices(epochs: List<ChurchHistoryEpoch>): List<Int> {
    val known = EPOCH_ORDER.mapNotNull { id -> epochs.indexOfFirst { it.epochId == id }.takeIf { it >= 0 } }
    val unknown = epochs.indices
        .filter { it !in known }
        .sortedBy { epochStartYear(epochs[it].centuryRange) }
    return known + unknown
}

// -------------------------------------------------------------------- years

/** "1054 d.C." -> 1054 */
internal fun yearOf(raw: String): Int? = Regex("""\d{3,4}""").find(raw)?.value?.toIntOrNull()

/** A schism year with an era suffix only where one is needed. */
internal fun eraYear(raw: String, language: AppLanguage): String {
    val n = Regex("""\d{1,4}""").find(raw)?.value?.toIntOrNull() ?: return raw.trim()
    if (n >= 1000) return "$n"
    return language.tr("$n d. C.", "AD $n", "$n d.C.")
}

private val devotionYearPattern = Regex("""^(\d{1,4})(?:\s*d\.\s?C\.)?\s*(?:\(([^)]+)\))?$""")
private val devotionCentury = Regex("""^Siglo\s+([IVXL]+)\s*(?:\(([^)]+)\))?$""")

private val devotionNotes: Map<String, Triple<String, String, String>> = mapOf(
    "dogma" to Triple("dogma", "dogma", "dogma"),
    "tradición" to Triple("tradición", "tradition", "tradição"),
    "canarias" to Triple("Canarias", "Canary Islands", "Canárias"),
    "icono milenario" to Triple("icono milenario", "ancient icon", "ícone milenar"),
    "templo" to Triple("templo", "temple", "templo")
)

private fun devotionNote(note: String, language: AppLanguage): String {
    val t = devotionNotes[note.trim().lowercase()] ?: return note.trim()
    return language.tr(t.first, t.second, t.third)
}

/** Marian "year" is sometimes Spanish prose ("Tradición medieval", "1854 (dogma)"); localise it. */
internal fun devotionYear(raw: String, language: AppLanguage): String {
    val text = raw.trim()
    if (text.equals("Tradición medieval", ignoreCase = true)) {
        return language.tr("Tradición medieval", "Medieval tradition", "Tradição medieval")
    }
    devotionCentury.find(text)?.let { m ->
        val n = romanToInt(m.groupValues[1]) ?: return text
        val note = m.groupValues[2].takeIf { it.isNotEmpty() }?.let { devotionNote(it, language) }
        val base = language.tr("Siglo ${m.groupValues[1]}", "${ordinalEn(n)} century", "Século ${m.groupValues[1]}")
        return if (note == null) base else "$base ($note)"
    }
    devotionYearPattern.find(text)?.let { m ->
        val n = m.groupValues[1].toInt()
        val hadEra = Regex("""d\.\s?C\.""", RegexOption.IGNORE_CASE).containsMatchIn(text)
        val base = when {
            hadEra || n < 1000 -> language.tr("$n d. C.", "AD $n", "$n d.C.")
            else -> "$n"
        }
        val note = m.groupValues[2].takeIf { it.isNotEmpty() }?.let { devotionNote(it, language) }
        return if (note == null) base else "$base ($note)"
    }
    return text
}

// --------------------------------------------------------------------- news

private val rfc822 = "EEE, dd MMM yyyy HH:mm:ss Z"

private val monthsEs = listOf("ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic")
private val monthsEn = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
private val monthsPt = listOf("jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez")

/** RFC-822 feed date -> epoch millis, or null. */
internal fun parseFeedDate(raw: String): Long? = runCatching {
    SimpleDateFormat(rfc822, Locale.US).parse(raw.trim())?.time
}.getOrNull()

/**
 * "Hace 2 h" / "2 h ago" / "Há 2 h" inside a day, else "30 oct" / "Oct 30" /
 * "30 out" (with the year when it is not the current one).
 */
internal fun newsDate(
    raw: String,
    language: AppLanguage,
    now: Long = System.currentTimeMillis(),
    zone: TimeZone = TimeZone.getDefault()
): String {
    val millis = parseFeedDate(raw) ?: return ""
    val diff = now - millis
    if (diff in 0 until 24L * 3_600_000L) {
        val minutes = (diff / 60_000L).toInt()
        return if (minutes < 60) {
            val m = minutes.coerceAtLeast(1)
            language.tr("Hace $m min", "$m min ago", "Há $m min")
        } else {
            val h = minutes / 60
            language.tr("Hace $h h", "$h h ago", "Há $h h")
        }
    }
    val cal = java.util.Calendar.getInstance(zone).apply { timeInMillis = millis }
    val nowCal = java.util.Calendar.getInstance(zone).apply { timeInMillis = now }
    val day = cal.get(java.util.Calendar.DAY_OF_MONTH)
    val month = cal.get(java.util.Calendar.MONTH)
    val year = cal.get(java.util.Calendar.YEAR)
    val base = when (language) {
        AppLanguage.ES -> "$day ${monthsEs[month]}"
        AppLanguage.EN -> "${monthsEn[month]} $day"
        AppLanguage.PT -> "$day ${monthsPt[month]}"
    }
    return if (year != nowCal.get(java.util.Calendar.YEAR)) "$base $year" else base
}

// ---------------------------------------------------------------- questions

/** One of the 11 themes of the 101 Questions. Keyed on the Spanish name so languages cannot split it. */
internal data class QuestionTheme(val key: String, val indices: List<Int>)

internal fun questionThemes(questions: List<ApologeticsQuestion>): List<QuestionTheme> {
    val order = LinkedHashMap<String, MutableList<Int>>()
    questions.forEachIndexed { i, q -> order.getOrPut(q.section.es) { mutableListOf() }.add(i) }
    return order.map { (k, v) -> QuestionTheme(k, v) }
}

/** First sentence of an answer, for cards. The answer's own lead sentence reads whole. */
internal fun firstSentence(text: String, maxChars: Int = 220): String {
    val flat = text.trim().replace(Regex("""\s+"""), " ")
    val m = Regex("""^(.+?[.!?»”])(\s|$)""").find(flat)
    val s = m?.groupValues?.get(1) ?: flat
    return if (s.length <= maxChars) s else s.take(maxChars).trimEnd() + "…"
}

/**
 * True when a foundation text is nothing but a list of citations ("Romanos 8,18-23;
 * Juan 9,1-3. Catecismo, 309-314."), which the reference chips already state in full.
 */
internal fun isCitationOnly(text: String): Boolean {
    val segments = text.split(';', '.').map { it.trim() }.filter { it.isNotEmpty() }
    if (segments.isEmpty()) return false
    // Spanish/Portuguese use "8,18-23", English "8:18-23"; books may start with a digit.
    val bible = Regex("""^(?:[1-3]\s*)?\p{L}[\p{L}\s]*\s\d{1,3}\s*[,:]\s*\d{1,3}(?:\s*[-–]\s*\d{1,3})?$""")
    val ccc = Regex(
        """^(?:Catecismo|Catechism|CCC)\s*,?\s*\d{1,4}(?:\s*[-–]\s*\d{1,4})?(?:\s*(?:,|y|e|and)\s*\d{1,4}(?:\s*[-–]\s*\d{1,4})?)*$""",
        RegexOption.IGNORE_CASE
    )
    return segments.all { bible.matches(it) || ccc.matches(it) }
}

/** Splits a numbered block ("1. a\n2. b") into its item texts; non-numbered lines are returned as `lead`. */
internal data class NumberedBlock(val lead: List<String>, val steps: List<String>)

internal fun parseNumbered(text: String): NumberedBlock {
    val lead = mutableListOf<String>()
    val steps = mutableListOf<String>()
    val item = Regex("""^\s*\d{1,2}\s*[.)]\s+(.*\S)\s*$""")
    for (line in text.lines()) {
        val t = line.trim()
        if (t.isEmpty()) continue
        val m = item.find(t)
        if (m != null) {
            steps.add(m.groupValues[1])
        } else if (steps.isEmpty()) {
            lead.add(t)
        } else {
            steps[steps.lastIndex] = steps.last() + " " + t
        }
    }
    return NumberedBlock(lead, steps)
}

// ------------------------------------------------------------------- orders

/** Offices by hierarchy; ids from the data. Unknown ids fall into [Other]. */
internal enum class OfficeGroup { Altar, Clergy, Consecrated, Mission, Other }

private val officeGroupOf: Map<String, OfficeGroup> = buildMap {
    listOf("monaguillo", "lector", "acolito").forEach { put("office_$it", OfficeGroup.Altar) }
    listOf(
        "diacono", "subdiacono_historico", "sacerdote", "parroco", "vicario_parroquial", "canonigo",
        "obispo", "arzobispo", "metropolitano", "patriarca", "cardenal", "papa"
    ).forEach { put("office_$it", OfficeGroup.Clergy) }
    listOf(
        "novicio", "monje", "hermano_religioso", "monja", "hermana_religiosa", "madre_superiora",
        "abadesa", "madre_general"
    ).forEach { put("office_$it", OfficeGroup.Consecrated) }
    listOf("misionero", "misionero_laico", "laico_consagrado").forEach { put("office_$it", OfficeGroup.Mission) }
}

internal fun officeGroup(id: String): OfficeGroup = officeGroupOf[id] ?: OfficeGroup.Other

internal fun officeGroupTitle(group: OfficeGroup, language: AppLanguage): String = when (group) {
    OfficeGroup.Altar -> language.tr("Servicio al altar", "Serving at the altar", "Serviço ao altar")
    OfficeGroup.Clergy -> language.tr("Clero", "Clergy", "Clero")
    OfficeGroup.Consecrated -> language.tr("Vida consagrada", "Consecrated life", "Vida consagrada")
    OfficeGroup.Mission -> language.tr("Misión y laicado", "Mission and laity", "Missão e laicato")
    OfficeGroup.Other -> language.tr("Otros", "Others", "Outros")
}

/** Data indices of the offices in display order: by group, data order inside each group. */
internal fun officeDisplayOrder(ids: List<String>): List<Int> =
    OfficeGroup.entries.flatMap { g -> ids.indices.filter { officeGroup(ids[it]) == g } }

/** "OFM · 1209", dropping the placeholder dash some orders carry for an abbreviation. */
internal fun orderMeta(abbreviation: String, year: String): String? =
    listOf(abbreviation.trim(), year.trim())
        .filter { it.isNotEmpty() && it != "—" && it != "-" }
        .joinToString(" · ")
        .ifEmpty { null }

// ------------------------------------------------------------------ history

/**
 * Where a schism falls on the timeline: the data index of the epoch (in
 * chronological order position) it sits before, or null for "after the last".
 */
internal fun schismInsertPosition(
    schism: ChurchSchismHistory,
    epochs: List<ChurchHistoryEpoch>,
    chronological: List<Int>
): Int? {
    val year = yearOf(schism.historicalYear) ?: return null
    // The closing overview ("modern") spans centuries and is not a point in time.
    return chronological.indexOfFirst { pos ->
        epochs[pos].epochId != "modern" && epochStartYear(epochs[pos].centuryRange) >= year
    }.takeIf { it >= 0 }
}

// ---------------------------------------------------------------- citations

/** The citation that closes a quotation: "(Exodus 20:2-3)" -> "Exodus 20:2–3". */
internal fun scriptureCitation(quoted: String): String? =
    Regex("""\(([^()]+)\)\s*$""").find(quoted.trim())?.groupValues?.get(1)?.let(TextFormat::ranges)

/** The quotation without its closing citation. */
internal fun withoutCitation(quoted: String): String =
    quoted.trim().replace(Regex("""\s*\([^()]+\)\s*$"""), "")

/** Chapter/verse separators as the language writes them: "Éxodo 20,2–3" but "Exodus 20:2–3". */
internal fun localizedCitation(citation: String, language: AppLanguage): String {
    val sep = if (language == AppLanguage.EN) ":" else ","
    return citation.replace(Regex("""(\d)\s*[,:]\s*(\d)"""), "$1$sep$2")
}
