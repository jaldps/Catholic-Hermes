package com.example.ui.components

import com.example.data.models.AppLanguage

/**
 * Small typographic corrections applied where data meets the page, so every
 * screen sets times and references the same way in all three languages.
 */
object TextFormat {

    private val clock = Regex("""\b(\d{1,2}):(\d{2})(?:\s*([AaPp])\.?\s?[Mm]\.?)?(?![\d])""")
    private val numericRange = Regex("""(\d)\s*-\s*(\d)""")
    private val timeRange = Regex("""(\d(?:\s?[AP]M)?)\s+-\s+(\d)""")
    private val leadingOrdinal = Regex("""^\s*\d+\s*(?:°|º|ª|st|nd|rd|th)?\s*[:.]\s*""")

    /** One clock time: "7:00 AM" in English, "7:00" on the 24-hour clock elsewhere. */
    fun clock(hour: Int, minute: Int, language: AppLanguage): String {
        val mm = minute.toString().padStart(2, '0')
        return if (language == AppLanguage.EN) {
            val h12 = when (val h = hour % 12) { 0 -> 12; else -> h }
            "$h12:$mm ${if (hour < 12) "AM" else "PM"}"
        } else {
            "$hour:$mm"
        }
    }

    /**
     * Every clock time inside [text] restated in [language]'s convention, with
     * no leading zero, and spans between them closed up with an en dash.
     */
    fun times(text: String, language: AppLanguage): String {
        val restated = clock.replace(text) { m ->
            var hour = m.groupValues[1].toInt()
            val minute = m.groupValues[2].toInt()
            when (m.groupValues[3].lowercase()) {
                "p" -> if (hour < 12) hour += 12
                "a" -> if (hour == 12) hour = 0
            }
            if (hour > 23 || minute > 59) m.value else clock(hour, minute, language)
        }
        return timeRange.replace(restated) { "${it.groupValues[1]}–${it.groupValues[2]}" }
    }

    /** Scripture and number ranges with an en dash: "Mt 5:1–12", "1054–1517". */
    fun ranges(text: String): String =
        numericRange.replace(text) { "${it.groupValues[1]}–${it.groupValues[2]}" }

    /** "1st: You shall not kill" → "You shall not kill" (the list numbers it). */
    fun withoutOrdinal(text: String): String = text.replaceFirst(leadingOrdinal, "")

    /** Upper-case Roman numeral, for numbering commandments and precepts. */
    fun roman(n: Int): String {
        val table = listOf(10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I")
        var left = n
        return buildString {
            for ((value, glyph) in table) while (left >= value) { append(glyph); left -= value }
        }
    }

    /** "First", "Second"… through tenth, for a commandment's eyebrow. */
    fun ordinalWord(n: Int, language: AppLanguage): String {
        val en = listOf("First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth")
        val es = listOf("Primer", "Segundo", "Tercer", "Cuarto", "Quinto", "Sexto", "Séptimo", "Octavo", "Noveno", "Décimo")
        val pt = listOf("Primeiro", "Segundo", "Terceiro", "Quarto", "Quinto", "Sexto", "Sétimo", "Oitavo", "Nono", "Décimo")
        val i = (n - 1).coerceIn(0, 9)
        return when (language) {
            AppLanguage.EN -> en[i]
            AppLanguage.ES -> es[i]
            AppLanguage.PT -> pt[i]
        }
    }
}
