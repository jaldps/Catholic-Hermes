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
    if (language == com.example.data.models.AppLanguage.ES) return time
    val words = listOf(
        "Cualquier hora" to ("Any time" to "Qualquer hora"),
        "Todo el día" to ("All day" to "O dia todo"),
        "Mañana" to ("Morning" to "Manhã"),
        "Tarde" to ("Afternoon" to "Tarde"),
        "Noche" to ("Evening" to "Noite")
    )
    return words.fold(time) { acc, (es, other) ->
        acc.replace(es, if (language == com.example.data.models.AppLanguage.EN) other.first else other.second)
    }
}
