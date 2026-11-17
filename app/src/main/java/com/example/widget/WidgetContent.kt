package com.example.widget

import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.CalendarRules
import com.example.data.liturgy.LectionaryData
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.ScriptureResolver
import com.example.data.models.AppLanguage
import com.example.data.notices.NovenaRecipient
import com.example.data.notices.PathProgress
import com.example.data.repository.BibleData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/** What the «Día litúrgico» widget shows; built offline from the engine and the bundled lectionary. */
data class LiturgicalWidgetState(
    val dateLabel: String,
    val celebration: String,
    val season: String,
    val colorName: String,
    val colorArgb: Int,
    val gospel: String?,
    val description: String
)

/** What the «Mi camino» widget shows. Novena names follow the same privacy rule as the notices. */
data class PathWidgetState(val title: String, val habits: String, val novena: String?, val plan: String?, val empty: String?, val description: String)

object WidgetContent {
    private fun pick(language: AppLanguage, es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt
    }

    private val citations = ScriptureResolver(
        bookName = { id, language -> BibleData.catholicCanonBooks.firstOrNull { it.id == id }?.name?.get(language) },
        versesOf = { _, _, _ -> emptyList() }
    )

    fun dateLabel(date: CalendarDate, language: AppLanguage): String {
        val locale = Locale.forLanguageTag(language.code)
        val pattern = pick(language, "EEEE, d 'de' MMMM", "EEEE, MMMM d", "EEEE, d 'de' MMMM")
        return LocalDate.of(date.year, date.month, date.day).format(DateTimeFormatter.ofPattern(pattern, locale))
            .replaceFirstChar { it.titlecase(locale) }
    }

    fun liturgical(date: CalendarDate, rules: CalendarRules, language: AppLanguage): LiturgicalWidgetState {
        val info = LiturgicalCalendar.dayInfo(date, rules)
        val gospel = LectionaryData.entryFor(info.lectionaryKey, info.sundayCycle)?.gospel?.let { citations.display(it, language) }
        val celebration = info.title.get(language)
        val season = info.season.displayName.get(language)
        val color = info.color.displayName.get(language)
        val gospelLabel = gospel?.let { pick(language, "Evangelio: ", "Gospel: ", "Evangelho: ") + it }
        val description = listOfNotNull(
            pick(language, "Día litúrgico", "Liturgical day", "Dia litúrgico") + ". " + dateLabel(date, language),
            celebration,
            season + ", " + pick(language, "color ", "colour ", "cor ") + color.lowercase(Locale.forLanguageTag(language.code)),
            gospelLabel
        ).joinToString(". ")
        return LiturgicalWidgetState(dateLabel(date, language), celebration, "$season · $color", color, info.color.hex.toInt(), gospelLabel, description)
    }

    fun path(progress: PathProgress, language: AppLanguage): PathWidgetState {
        val title = pick(language, "Mi camino", "My path", "Meu caminho")
        val habits = progress.habits?.let { h ->
            if (h.pending == 0 && h.total > 0) pick(language, "Hábitos de hoy: todo hecho", "Today's habits: all done", "Hábitos de hoje: tudo feito")
            else pick(language, "Hábitos de hoy: ${h.done} de ${h.total}", "Today's habits: ${h.done} of ${h.total}", "Hábitos de hoje: ${h.done} de ${h.total}")
        } ?: pick(language, "Hábitos de hoy", "Today's habits", "Hábitos de hoje")
        val novena = progress.novena?.let { n ->
            val name = NovenaRecipient.of(n.title)?.get(language)
            val day = pick(language, "día ${progress.novenaDay} de ${progress.novenaTotal}", "day ${progress.novenaDay} of ${progress.novenaTotal}",
                "dia ${progress.novenaDay} de ${progress.novenaTotal}")
            "Novena" + (name?.let { " · $it" } ?: "") + " · " + day
        } ?: progress.namelessNovenaDay?.let { pick(language, "Novena · día $it de 9", "Novena · day $it of 9", "Novena · dia $it de 9") }
        val plan = progress.plan?.let { p ->
            pick(language, "Plan", "Plan", "Plano") + " · " + p.title.get(language) + " · " +
                pick(language, "día ${progress.planDay}", "day ${progress.planDay}", "dia ${progress.planDay}")
        }
        val empty = if (novena == null && plan == null) pick(language, "Sin novena ni plan en curso", "No novena or plan in progress",
            "Sem novena nem plano em curso") else null
        return PathWidgetState(title, habits, novena, plan, empty, listOfNotNull(title, habits, novena, plan, empty).joinToString(". "))
    }
}
