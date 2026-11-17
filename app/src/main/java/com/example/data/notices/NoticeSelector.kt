package com.example.data.notices

import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.CalendarRules
import com.example.data.liturgy.CelebrationRank
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.LiturgicalSeason
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText

/** The three groups of the Reminders screen. */
enum class NoticeGroup { SEASON, FEASTS, REMINDERS }

/**
 * Every kind of notice the user can switch on, each with its own time. All are OFF until the person turns them on.
 * [id] is stable: it names the stored preference, the alarm and the notification, so it never changes.
 */
enum class NoticeType(val id: String, val group: NoticeGroup, val defaultHour: Int, val defaultMinute: Int) {
    SEASON_START("season_start", NoticeGroup.SEASON, 8, 0),
    SEASON_WEEKLY("season_weekly", NoticeGroup.SEASON, 9, 0),
    FEAST_EVE("feast_eve", NoticeGroup.FEASTS, 19, 0),
    FEAST_DAY("feast_day", NoticeGroup.FEASTS, 8, 0),
    SUNDAY_MASS("sunday_mass", NoticeGroup.REMINDERS, 18, 0),
    HOLY_DAY_OF_OBLIGATION("holy_day_of_obligation", NoticeGroup.REMINDERS, 19, 30),
    NOVENA_DAY("novena_day", NoticeGroup.REMINDERS, 9, 0),
    PLAN_DAY("plan_day", NoticeGroup.REMINDERS, 8, 30),
    ROSARY_EVENING("rosary_evening", NoticeGroup.REMINDERS, 20, 0),
    EXAMEN_NIGHT("examen_night", NoticeGroup.REMINDERS, 22, 0),
    CONFESSION_GENTLE("confession_gentle", NoticeGroup.REMINDERS, 10, 0);

    companion object {
        fun byId(id: String?): NoticeType? = entries.firstOrNull { it.id == id }
    }
}

/** The 48 celebrations with reviewed texts, and how the liturgical engine names the ones it keys differently. */
object NoticeCelebrations {
    /** Engine key ([com.example.data.liturgy.LiturgicalDayInfo.lectionaryKey]) → notice key, where they differ. */
    private val ENGINE_TO_NOTICE = mapOf(
        "divine_mercy" to "divine_mercy_sunday",
        "presentation" to "presentation_of_the_lord",
        "st_joseph" to "joseph_spouse_of_mary",
        "annunciation" to "annunciation_of_the_lord",
        "john_baptist_nativity" to "nativity_of_john_the_baptist",
        "peter_and_paul" to "peter_and_paul_apostles",
        "transfiguration" to "transfiguration_of_the_lord",
        "holy_cross" to "exaltation_of_the_holy_cross",
        "all_souls" to "commemoration_of_all_the_faithful_departed",
        "immaculate_conception" to "immaculate_conception_of_the_blessed_virgin_mary",
        "st_stephen" to "stephen_the_first_martyr",
        "st_john" to "john_apostle",
        "holy_innocents" to "holy_innocents_martyrs"
    )

    val ALL: Set<String> = setOf(
        "ash_wednesday", "palm_sunday", "holy_thursday_evening", "good_friday", "easter_sunday",
        "divine_mercy_sunday", "ascension", "pentecost", "trinity", "corpus_christi", "sacred_heart", "christ_the_king",
        "holy_family", "epiphany", "baptism_of_the_lord", "christmas_day", "mary_mother_of_god",
        "conversion_of_saint_paul_the_apostle", "presentation_of_the_lord", "chair_of_saint_peter_the_apostle",
        "joseph_spouse_of_mary", "annunciation_of_the_lord", "mark_evangelist", "philip_and_james_apostles",
        "matthias_apostle", "visitation", "nativity_of_john_the_baptist", "peter_and_paul_apostles", "thomas_apostle",
        "mary_magdalene", "james_apostle", "transfiguration_of_the_lord", "lawrence_of_rome_deacon",
        "bartholomew_apostle", "nativity_of_the_blessed_virgin_mary", "exaltation_of_the_holy_cross", "matthew_apostle",
        "michael_gabriel_and_raphael_archangels", "luke_evangelist", "simon_and_jude_apostles", "all_saints",
        "commemoration_of_all_the_faithful_departed", "dedication_lateran", "andrew_apostle",
        "immaculate_conception_of_the_blessed_virgin_mary", "stephen_the_first_martyr", "john_apostle",
        "holy_innocents_martyrs"
    )

    /**
     * The celebration the Church keeps on [date], as the engine resolves it with the person's transfer rules. A feast
     * that the liturgy impedes that day (a Sunday of Advent, Holy Week, the Easter octave…) is not returned, and a
     * transferred solemnity is returned on the day it is actually kept.
     */
    fun on(date: CalendarDate, rules: CalendarRules): String? {
        val year = date.year
        if (date == LiturgicalCalendar.palmSunday(year)) return "palm_sunday"
        if (date == LiturgicalCalendar.holyThursday(year)) return "holy_thursday_evening"
        val info = LiturgicalCalendar.dayInfo(date, rules)
        if (info.rank == CelebrationRank.MEMORIAL || info.rank == CelebrationRank.OPTIONAL_MEMORIAL) return null
        val key = ENGINE_TO_NOTICE[info.lectionaryKey] ?: info.lectionaryKey
        return key.takeIf { it in ALL }
    }
}

/**
 * The universal holy days of obligation of canon 1246 §1, offered as a checklist. The app never decides which of them
 * oblige: each bishops' conference does (§2), so the person ticks the ones that apply where they live.
 */
object ObligationDays {
    data class Day(val key: String, val title: LocalizedText, val month: Int? = null, val day: Int? = null)

    val ALL: List<Day> = listOf(
        Day("mary_mother_of_god", LocalizedText("Santa María, Madre de Dios (1 ene)", "Mary, Mother of God (1 Jan)", "Santa Maria, Mãe de Deus (1 jan)"), 1, 1),
        Day("epiphany", LocalizedText("Epifanía del Señor (6 ene)", "Epiphany of the Lord (6 Jan)", "Epifania do Senhor (6 jan)"), 1, 6),
        Day("st_joseph", LocalizedText("San José (19 mar)", "Saint Joseph (19 Mar)", "São José (19 mar)"), 3, 19),
        Day("ascension", LocalizedText("Ascensión del Señor (jueves)", "Ascension of the Lord (Thursday)", "Ascensão do Senhor (quinta-feira)")),
        Day("corpus_christi", LocalizedText("Corpus Christi (jueves)", "Corpus Christi (Thursday)", "Corpus Christi (quinta-feira)")),
        Day("peter_and_paul", LocalizedText("San Pedro y San Pablo (29 jun)", "Saints Peter and Paul (29 Jun)", "São Pedro e São Paulo (29 jun)"), 6, 29),
        Day("assumption", LocalizedText("Asunción de la Virgen (15 ago)", "Assumption of Mary (15 Aug)", "Assunção de Nossa Senhora (15 ago)"), 8, 15),
        Day("all_saints", LocalizedText("Todos los Santos (1 nov)", "All Saints (1 Nov)", "Todos os Santos (1 nov)"), 11, 1),
        Day("immaculate_conception", LocalizedText("Inmaculada Concepción (8 dic)", "Immaculate Conception (8 Dec)", "Imaculada Conceição (8 dez)"), 12, 8),
        Day("christmas_day", LocalizedText("Natividad del Señor (25 dic)", "Christmas (25 Dec)", "Natal do Senhor (25 dez)"), 12, 25)
    )
    val KEYS: Set<String> = ALL.map { it.key }.toSet()

    /**
     * The holy day kept on [date] among those the person selected, or null. Sundays are left to the Sunday notice. A
     * fixed solemnity counts only on its own date: whether the obligation follows a rubrical transfer is again a
     * conference decision, so a transferred day is not announced as one of precept.
     */
    fun on(date: CalendarDate, rules: CalendarRules, selected: Set<String>): String? {
        if (selected.isEmpty() || date.isSunday) return null
        val info = LiturgicalCalendar.dayInfo(date, rules)
        val day = ALL.firstOrNull { it.key == info.lectionaryKey && it.key in selected } ?: return null
        if (info.rank != CelebrationRank.SOLEMNITY) return null
        if (day.month != null && (date.month != day.month || date.day != day.day)) return null
        return day.key
    }
}

/** A novena in progress, already reduced to what a notice may say: the recipient's name (or none) and the day. */
data class NovenaNoticeData(val recipient: LocalizedText?, val day: Int)

/** A reading plan in progress: its catalog title and today's day; [doneToday] skips a day already read. */
data class PlanNoticeData(val title: LocalizedText, val day: Int, val doneToday: Boolean = false)

/** Everything a selection depends on besides the date. */
data class NoticeContext(
    val rules: CalendarRules = CalendarRules(),
    val obligationDays: Set<String> = emptySet(),
    val novena: NovenaNoticeData? = null,
    val plan: PlanNoticeData? = null
)

/** What a notification shows and where its tap leads ([action] is an AppDestination name). */
data class NoticeMessage(val type: NoticeType, val key: String, val title: String, val body: String, val action: String)

/**
 * Pure selection: which notice of [NoticeType] belongs to a given day. No clock, no storage, no Android: the
 * scheduler, the receiver and the tests all ask the same question.
 */
object NoticeSelector {

    /** First day of the season [date] belongs to (the engine's own season boundaries, with the user's rules). */
    fun seasonStart(date: CalendarDate, rules: CalendarRules): CalendarDate {
        val season = LiturgicalCalendar.seasonOf(date, date.year, rules)
        var start = date
        var guard = 0
        while (guard++ < 400) {
            val previous = start.plusDays(-1)
            if (LiturgicalCalendar.seasonOf(previous, previous.year, rules) != season) break
            start = previous
        }
        return start
    }

    /**
     * The weekly notice of the season, rotating week by week. It comes on Sundays, the first day of the liturgical
     * week; Holy Week and the Triduum are shorter than a week, so there it moves on daily to give each text its day.
     */
    fun weeklyIndex(date: CalendarDate, rules: CalendarRules): Int? {
        val season = LiturgicalCalendar.seasonOf(date, date.year, rules)
        val start = seasonStart(date, rules)
        val elapsed = (date.toEpochDay() - start.toEpochDay()).toInt()
        if (season == LiturgicalSeason.HOLY_WEEK || season == LiturgicalSeason.TRIDUUM) return elapsed
        if (!date.isSunday) return null
        val firstSunday = start.plusDays((7 - start.isoDayOfWeek % 7) % 7)
        return ((date.toEpochDay() - firstSunday.toEpochDay()) / 7).toInt()
    }

    private fun isFirstSaturdayOfMonth(date: CalendarDate) = date.isoDayOfWeek == 6 && date.day <= 7

    /** Does [type] have something to say on [date], judging by the calendar alone? (Progress is checked when it fires.) */
    fun occurs(type: NoticeType, date: CalendarDate, notices: Notices, context: NoticeContext): Boolean = when (type) {
        NoticeType.NOVENA_DAY, NoticeType.PLAN_DAY, NoticeType.ROSARY_EVENING, NoticeType.EXAMEN_NIGHT -> true
        NoticeType.SUNDAY_MASS -> date.isoDayOfWeek == 6
        NoticeType.CONFESSION_GENTLE -> isFirstSaturdayOfMonth(date)
        else -> select(type, date, notices, context, AppLanguage.ES) != null
    }

    fun select(type: NoticeType, date: CalendarDate, notices: Notices, context: NoticeContext, language: AppLanguage): NoticeMessage? {
        fun message(key: String, text: NoticeText, fallbackAction: String? = null, fill: (String) -> String = { it }) =
            NoticeMessage(type, key, fill(text.title.get(language)), fill(text.body.get(language)),
                text.action ?: fallbackAction ?: "HOME")
        val rules = context.rules
        return when (type) {
            NoticeType.SEASON_START -> {
                val season = LiturgicalCalendar.seasonOf(date, date.year, rules)
                val previous = date.plusDays(-1)
                if (LiturgicalCalendar.seasonOf(previous, previous.year, rules) == season) null
                else notices.seasons[season]?.let { message("${season.name}.start", it.start) }
            }
            NoticeType.SEASON_WEEKLY -> {
                val season = LiturgicalCalendar.seasonOf(date, date.year, rules)
                val weekly = notices.seasons[season]?.weekly.orEmpty()
                val index = weeklyIndex(date, rules) ?: return null
                val short = season == LiturgicalSeason.HOLY_WEEK || season == LiturgicalSeason.TRIDUUM
                if (weekly.isEmpty() || (short && index >= weekly.size)) null
                else weekly[index % weekly.size].let { message(it.id ?: "${season.name}.weekly", it) }
            }
            NoticeType.FEAST_DAY -> NoticeCelebrations.on(date, rules)?.let { key ->
                notices.celebrations[key]?.let { message("$key.day", it.day) }
            }
            NoticeType.FEAST_EVE -> NoticeCelebrations.on(date.plusDays(1), rules)?.let { key ->
                notices.celebrations[key]?.let { message("$key.eve", it.eve, it.day.action) }
            }
            NoticeType.SUNDAY_MASS -> if (date.isoDayOfWeek != 6) null
                else notices.reminders[type.id]?.let { message(type.id, it) }
            NoticeType.HOLY_DAY_OF_OBLIGATION -> ObligationDays.on(date.plusDays(1), rules, context.obligationDays)?.let { key ->
                notices.reminders[type.id]?.let { message("${type.id}.$key", it) }
            }
            NoticeType.CONFESSION_GENTLE -> if (!isFirstSaturdayOfMonth(date)) null
                else notices.reminders[type.id]?.let { message(type.id, it) }
            NoticeType.ROSARY_EVENING, NoticeType.EXAMEN_NIGHT -> notices.reminders[type.id]?.let { message(type.id, it) }
            NoticeType.NOVENA_DAY -> {
                val novena = context.novena ?: return null
                val text = notices.reminders[type.id] ?: return null
                val recipient = novena.recipient?.get(language)?.takeIf { it.isNotBlank() }
                message(type.id, text) { raw -> NovenaRecipient.fill(raw, recipient, novena.day, language) }
            }
            NoticeType.PLAN_DAY -> {
                val plan = context.plan?.takeUnless { it.doneToday } ?: return null
                val text = notices.reminders[type.id] ?: return null
                message(type.id, text) { raw -> raw.replace("{{plan}}", plan.title.get(language)).replace("{{day}}", plan.day.toString()) }
            }
        }
    }

    /** The first day from [from] (inclusive, up to [horizonDays] ahead) on which [type] occurs. */
    fun nextOccurrence(type: NoticeType, from: CalendarDate, notices: Notices, context: NoticeContext, horizonDays: Int = 400): CalendarDate? {
        for (offset in 0..horizonDays) {
            val date = from.plusDays(offset)
            if (occurs(type, date, notices, context)) return date
        }
        return null
    }
}
