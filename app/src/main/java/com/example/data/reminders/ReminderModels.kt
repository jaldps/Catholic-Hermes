package com.example.data.reminders

import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.SeasonalMarianPrayer
import com.example.data.models.AppLanguage

/** One daily devotion reminder. Titles are trilingual (ES/EN/PT). */
data class DevotionReminder(
    val id: String,
    val titleEs: String,
    val titleEn: String,
    val titlePt: String,
    val bodyEs: String,
    val bodyEn: String,
    val bodyPt: String,
    val defaultHour: Int,
    val defaultMinute: Int,
) {
    fun title(language: String): String = title(language,LiturgicalCalendar.today())

    fun title(language: String, date: CalendarDate): String = if (id.startsWith("angelus_"))
        SeasonalMarianPrayer.forDate(date).title.get(AppLanguage.fromCode(language))
        else when (language) {
        "en" -> titleEn
        "pt" -> titlePt
        else -> titleEs
    }

    /** A daily calendar recurrence spans seasons; never freeze an Easter-only title. */
    fun calendarTitle(language: String): String = if (id.startsWith("angelus_")) {
        when(language) { "en" -> "Angelus / Regina Caeli"; "pt" -> "Angelus / Regina Caeli"; else -> "Ángelus / Regina Caeli" }
    } else title(language)
    fun calendarBody(language: String): String = if (id.startsWith("angelus_")) {
        when(language) { "en" -> "Daily Marian prayer: Regina Caeli during Eastertide; Angelus in other seasons.";
            "pt" -> "Oração mariana diária: Regina Caeli no Tempo Pascal; Angelus nos outros tempos.";
            else -> "Oración mariana diaria: Regina Caeli en Pascua; Ángelus en los demás tiempos." }
    } else body(language)

    fun body(language: String): String = body(language,LiturgicalCalendar.today())

    fun body(language: String, date: CalendarDate): String = if (id.startsWith("angelus_") && SeasonalMarianPrayer.forDate(date).id == "regina_caeli") {
        when (language) {
            "en" -> "Rejoice, Queen of Heaven. Pray the Regina Caeli during Eastertide."
            "pt" -> "Alegrai-vos, Rainha do Céu. Reze o Regina Caeli no Tempo Pascal."
            else -> "Alégrate, Reina del Cielo. Reza el Regina Caeli durante el tiempo de Pascua."
        }
    } else when (language) {
        "en" -> bodyEn
        "pt" -> bodyPt
        else -> bodyEs
    }
}

/** Persisted user choice for one reminder. */
data class ReminderPref(
    val enabled: Boolean,
    val hour: Int,
    val minute: Int,
)

/**
 * Active novena. [startEpochDay] is [java.time.LocalDate.toEpochDay] of day 1,
 * so the current day is derived from the calendar date — never stored — and
 * the novena can never drift out of sync with "today".
 */
data class NovenaState(
    val name: String,
    val startEpochDay: Long,
) {
    /** 1-based day number today, clamped to 1..9. */
    fun dayNumber(todayEpochDay: Long): Int =
        ((todayEpochDay - startEpochDay + 1).toInt()).coerceIn(1, 9)

    fun isComplete(todayEpochDay: Long): Boolean =
        todayEpochDay - startEpochDay >= 8
}

/** The devotional moments the app can remind about, with sane defaults. */
object ReminderCatalog {
    val DEFAULTS: List<DevotionReminder> = listOf(
        DevotionReminder(
            id = "angelus_morning",
            titleEs = "Ángelus de la mañana", titleEn = "Morning Angelus", titlePt = "Angelus da manhã",
            bodyEs = "Es hora del Ángelus de la mañana.", bodyEn = "Time for the Morning Angelus.", bodyPt = "Hora do Angelus da manhã.",
            defaultHour = 6, defaultMinute = 0,
        ),
        DevotionReminder(
            id = "angelus_midday",
            titleEs = "Ángelus del mediodía", titleEn = "Midday Angelus", titlePt = "Angelus do meio-dia",
            bodyEs = "Es hora del Ángelus del mediodía.", bodyEn = "Time for the Midday Angelus.", bodyPt = "Hora do Angelus do meio-dia.",
            defaultHour = 12, defaultMinute = 0,
        ),
        DevotionReminder(
            id = "angelus_evening",
            titleEs = "Ángelus de la tarde", titleEn = "Evening Angelus", titlePt = "Angelus da tarde",
            bodyEs = "Es hora del Ángelus de la tarde.", bodyEn = "Time for the Evening Angelus.", bodyPt = "Hora do Angelus da tarde.",
            defaultHour = 18, defaultMinute = 0,
        ),
        DevotionReminder(
            id = "coronilla",
            titleEs = "Coronilla de la Divina Misericordia", titleEn = "Divine Mercy Chaplet", titlePt = "Coronilha da Divina Misericórdia",
            bodyEs = "Es hora de la Coronilla (3 pm).", bodyEn = "Time for the Chaplet of Divine Mercy (3 pm).", bodyPt = "Hora da Coronilha (15h).",
            defaultHour = 15, defaultMinute = 0,
        ),
        DevotionReminder(
            id = "morning_prayer",
            titleEs = "Oración de la mañana", titleEn = "Morning prayer", titlePt = "Oração da manhã",
            bodyEs = "Comienza el día con la oración de la mañana.", bodyEn = "Start the day with morning prayer.", bodyPt = "Comece o dia com a oração da manhã.",
            defaultHour = 7, defaultMinute = 0,
        ),
        DevotionReminder(
            id = "night_prayer",
            titleEs = "Oración de la noche", titleEn = "Night prayer", titlePt = "Oração da noite",
            bodyEs = "Es hora de la oración de la noche.", bodyEn = "Time for night prayer.", bodyPt = "Hora da oração da noite.",
            defaultHour = 21, defaultMinute = 30,
        ),
        DevotionReminder(
            id = "daily_reading",
            titleEs = "Lectura diaria", titleEn = "Daily reading", titlePt = "Leitura diária",
            bodyEs = "Es hora de la lectura del día.", bodyEn = "Time for today's reading.", bodyPt = "Hora da leitura do dia.",
            defaultHour = 8, defaultMinute = 0,
        ),
    )

    fun byId(id: String): DevotionReminder? = DEFAULTS.firstOrNull { it.id == id }
}
