package com.example.data.notices

import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText

/**
 * Who a novena is prayed to, taken from its catalog title: «Novena a San José» → «San José», «Novena al Sagrado
 * Corazón» → «el Sagrado Corazón», "St. Jude Novena - Miraculous Prayer…" → "St. Jude". The notice reads «la novena de
 * {{novena}}», so the full title would say «la novena de Novena a San José» (T-035 review).
 *
 * Titles often carry the intention too («… para solteros que buscan esposo», "… for Expectant Mothers"). A
 * notification can be read by anyone near the phone, so the intention is always cut, and a novena whose very name is
 * an intention (fertility, curses, elections…) is announced without a name at all.
 */
object NovenaRecipient {
    private val SENSITIVE = Regex(
        "fertil|conceiv|conceb|baby|bebé|embaraz|gestant|pregnan|expectant|curse|maldi|elecci|eleiç|election|lapsed|alejad|afastad|" +
            "mental|cancer|cáncer|câncer|employ|empleo|emprego|spouse|esposo|cônjuge|divorc|addict|adicci|vício|vida humana|respect life|" +
            "respeito à vida|enferm|illness|doen|healing|sanaci|cura\\b|imposs|hopeless|desesper",
        RegexOption.IGNORE_CASE
    )

    private fun cut(text: String, intentions: String): String {
        var s = text.split(Regex("\\s+-\\s+|\\s*\\(|,\\s+|\\s+[–—]\\s+"), limit = 2)[0]
        Regex("\\s+(?:$intentions)\\s+", RegexOption.IGNORE_CASE).find(s)?.takeIf { it.range.first > 0 }?.let { s = s.substring(0, it.range.first) }
        return s.trim()
    }

    fun spanish(title: String): String {
        val t = title.trim()
        var s = t.replace(Regex("^(la\\s+)?novena\\s+", RegexOption.IGNORE_CASE), "")
        if (s != t) {
            s = s.replace(Regex("^de\\s+\\d+\\s+d[ií]as\\s+", RegexOption.IGNORE_CASE), "")
            Regex("^(a la|a las|a los|al|a|de la|de las|de los|del|de|por las|por los|por el|por la|por)\\s+(.*)$", RegexOption.IGNORE_CASE)
                .find(s)?.let { m ->
                    val article = when (m.groupValues[1].lowercase()) {
                        "a la", "de la", "por la" -> "la "
                        "a las", "de las", "por las" -> "las "
                        "a los", "de los", "por los" -> "los "
                        "al", "del", "por el" -> "el "
                        else -> ""
                    }
                    s = article + m.groupValues[2]
                }
        }
        return cut(s, "por|para|contra")
    }

    fun portuguese(title: String): String {
        val t = title.trim()
        var s = t.replace(Regex("^(a\\s+)?novena\\s+", RegexOption.IGNORE_CASE), "")
        if (s != t) {
            s = s.replace(Regex("^de\\s+\\d+\\s+dias\\s+", RegexOption.IGNORE_CASE), "")
            Regex("^(aos|às|ao|à|a|das|dos|da|do|de|pelas|pelos|pela|pelo|por)\\s+(.*)$", RegexOption.IGNORE_CASE)
                .find(s)?.let { m ->
                    val article = when (m.groupValues[1].lowercase()) {
                        "aos", "dos", "pelos" -> "os "
                        "às", "das", "pelas" -> "as "
                        "ao", "do", "pelo" -> "o "
                        "à", "da", "pela" -> "a "
                        else -> ""
                    }
                    s = article + m.groupValues[2]
                }
        }
        return cut(s, "pelo|pela|pelos|pelas|para|contra|por")
    }

    fun english(title: String): String {
        val s = title.trim()
        val m = Regex("^(.*?)\\s*\\bnovena\\b\\s*(.*)$", RegexOption.IGNORE_CASE).find(s) ?: return cut(s, "for|against")
        var before = m.groupValues[1].trim()
            .replace(Regex("^the\\s+", RegexOption.IGNORE_CASE), "")
            .replace(Regex("^\\d+[- ]days?$", RegexOption.IGNORE_CASE), "")
            .replace(Regex("\\s+rose$", RegexOption.IGNORE_CASE), "")
            .trim()
        if (before.isEmpty()) before = m.groupValues[2].trim().replace(Regex("^(to|of|for|al|a|de)\\s+", RegexOption.IGNORE_CASE), "")
        return cut(before, "for|against")
    }

    /** The recipient in the three languages, or null when the name itself would disclose an intention. */
    fun of(title: LocalizedText): LocalizedText? {
        val names = LocalizedText(spanish(title.es), english(title.en), portuguese(title.pt))
        if (listOf(names.es, names.en, names.pt).any { it.isBlank() || it.contains("novena", ignoreCase = true) }) return null
        if (listOf(names.es, names.en, names.pt).any { SENSITIVE.containsMatchIn(it) }) return null
        return names
    }

    /**
     * Fills a novena_day text. Spanish and Portuguese contract the preposition with the article («de el» → «del»,
     * «de a» → «da»). Without a name the phrase «de {{novena}}» is dropped: «Hoy es el día 3 de la novena».
     */
    fun fill(raw: String, recipient: String?, day: Int, language: AppLanguage): String {
        var text = raw.replace("{{day}}", day.toString())
        text = if (recipient == null) text.replace(Regex("\\s+(?:de|of)\\s+\\{\\{novena\\}\\}"), "")
            else text.replace("{{novena}}", recipient)
        text = when (language) {
            AppLanguage.ES -> text.replace(Regex("\\bde el\\b"), "del")
            AppLanguage.PT -> text.replace(Regex("\\bde o\\b"), "do").replace(Regex("\\bde a\\b"), "da")
                .replace(Regex("\\bde os\\b"), "dos").replace(Regex("\\bde as\\b"), "das")
            AppLanguage.EN -> text
        }
        return text.replace(Regex("\\s{2,}"), " ").trim()
    }
}
