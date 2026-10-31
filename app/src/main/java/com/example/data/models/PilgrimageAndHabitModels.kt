package com.example.data.models

data class CatholicPilgrimageSanctuary(
    val id: String,
    val name: LocalizedText,
    val cityCountry: LocalizedText,
    val historicalOrigin: LocalizedText,
    val spiritualSignificance: LocalizedText,
    val relicsAndPatron: LocalizedText,
    val plenaryIndulgenceNote: LocalizedText,
    val practicalPilgrimAdvice: LocalizedText,
    val coordinateLatLong: Pair<Double, Double>
)

data class SpiritualHabitItem(
    val id: String,
    val title: LocalizedText,
    val category: LocalizedText,
    val timeSuggestion: String,
    val description: LocalizedText,
    val iconName: String
)

data class LiturgicalCalendarDay(
    val dayOfMonth: Int,
    val monthName: String,
    val liturgicalColorHex: Long,
    val seasonName: LocalizedText,
    val celebrationTitle: LocalizedText,
    val rank: LocalizedText, // Solemnity, Feast, Memorial, Ferial
    val fastingRule: LocalizedText? = null,
    val gospelVerseRef: String,
    val sundayCycle: LocalizedText? = null,
    val weekdayCycle: LocalizedText? = null,
    val isFastOrAbstinence: Boolean = false,
    val missalMeditation: LocalizedText? = null
)

data class SinAvoiderOption(
    val sinKey: String,
    val title: LocalizedText,
    val counterVirtue: LocalizedText,
    val advice: LocalizedText,
    val iconName: String
)


/**
 * Habit times were seeded, and are stored in Room, as Spanish words beside
 * clock times ("08:00 AM / Tarde"). They are translated where they are shown,
 * so existing databases read correctly without a migration.
 */
fun localizedHabitTime(time: String, language: com.example.data.models.AppLanguage): String {
    val words = listOf(
        "Cualquier hora" to ("Any time" to "Qualquer hora"),
        "Todo el día" to ("All day" to "O dia todo"),
        "Mañana" to ("Morning" to "Manhã"),
        "Tarde" to ("Afternoon" to "Tarde"),
        "Noche" to ("Evening" to "Noite")
    )
    val translated = if (language == com.example.data.models.AppLanguage.ES) time else
        words.fold(time) { acc, (es, other) ->
            acc.replace(es, if (language == com.example.data.models.AppLanguage.EN) other.first else other.second)
        }
    // Clock times in the reader's convention, never with a leading zero:
    // "7:00 AM" in English, "7:00" on the 24-hour clock in Spanish and Portuguese.
    val clock = Regex("""\b(\d{1,2}):(\d{2})\s*([AaPp])[Mm]\b|\b(\d{1,2}):(\d{2})\b""")
    val timed = clock.replace(translated) { m ->
        val (h12, mm, ap, h24, mm24) = m.destructured
        var hour = (h12.ifEmpty { h24 }).toInt()
        val minute = mm.ifEmpty { mm24 }
        if (ap.equals("p", true) && hour < 12) hour += 12
        if (ap.equals("a", true) && hour == 12) hour = 0
        if (language == com.example.data.models.AppLanguage.EN) {
            val h = if (hour % 12 == 0) 12 else hour % 12
            "$h:$minute ${if (hour < 12) "AM" else "PM"}"
        } else "$hour:$minute"
    }
    // Alternatives read as a phrase — "8:00 AM or afternoon" — not "A / B".
    val or = when (language) {
        com.example.data.models.AppLanguage.ES -> " o "
        com.example.data.models.AppLanguage.EN -> " or "
        com.example.data.models.AppLanguage.PT -> " ou "
    }
    val parts = timed.split(" / ")
    return parts.mapIndexed { i, part -> if (i == 0) part else part.replaceFirstChar { it.lowercase() } }
        .joinToString(or)
}
