package com.example.data.liturgy

import com.example.data.models.LocalizedText

/**
 * A calendar date, independent of time zone and of `java.time` (which needs
 * API 26 and this module targets minSdk 24).
 */
data class CalendarDate(val year: Int, val month: Int, val day: Int) {

    /** Days since 1970-01-01, via Howard Hinnant's `days_from_civil`. */
    fun toEpochDay(): Long {
        val y = if (month <= 2) year - 1 else year
        val era = (if (y >= 0) y else y - 399) / 400
        val yoe = y - era * 400
        val mp = (month + 9) % 12
        val doy = (153 * mp + 2) / 5 + day - 1
        val doe = yoe * 365 + yoe / 4 - yoe / 100 + doy
        return era.toLong() * 146097L + doe.toLong() - 719468L
    }

    /** 1 = Monday … 7 = Sunday, matching ISO-8601. */
    val isoDayOfWeek: Int
        get() = ((toEpochDay() + 3).mod(7L) + 1).toInt()

    val isSunday: Boolean get() = isoDayOfWeek == 7
    val isFriday: Boolean get() = isoDayOfWeek == 5

    fun plusDays(days: Int): CalendarDate = fromEpochDay(toEpochDay() + days)

    operator fun compareTo(other: CalendarDate): Int =
        toEpochDay().compareTo(other.toEpochDay())

    companion object {
        /** Inverse of [toEpochDay] (`civil_from_days`). */
        fun fromEpochDay(epochDay: Long): CalendarDate {
            val z = epochDay + 719468L
            val era = (if (z >= 0) z else z - 146096L) / 146097L
            val doe = z - era * 146097L
            val yoe = (doe - doe / 1460 + doe / 36524 - doe / 146096) / 365
            val y = yoe + era * 400
            val doy = doe - (365 * yoe + yoe / 4 - yoe / 100)
            val mp = (5 * doy + 2) / 153
            val d = doy - (153 * mp + 2) / 5 + 1
            val m = if (mp < 10) mp + 3 else mp - 9
            return CalendarDate((if (m <= 2) y + 1 else y).toInt(), m.toInt(), d.toInt())
        }

        fun of(year: Int, month: Int, day: Int) = CalendarDate(year, month, day)
    }
}

enum class LiturgicalSeason(val displayName: LocalizedText) {
    ADVENT(LocalizedText("Adviento", "Advent", "Advento")),
    CHRISTMAS(LocalizedText("Navidad", "Christmas", "Natal")),
    ORDINARY_TIME(LocalizedText("Tiempo Ordinario", "Ordinary Time", "Tempo Comum")),
    LENT(LocalizedText("Cuaresma", "Lent", "Quaresma")),
    HOLY_WEEK(LocalizedText("Semana Santa", "Holy Week", "Semana Santa")),
    TRIDUUM(LocalizedText("Triduo Pascual", "Paschal Triduum", "Tríduo Pascal")),
    EASTER(LocalizedText("Tiempo de Pascua", "Easter", "Tempo Pascal"))
}

enum class LiturgicalColor(val hex: Long, val displayName: LocalizedText) {
    GREEN(0xFF2C6B3E, LocalizedText("Verde", "Green", "Verde")),
    VIOLET(0xFF5E2777, LocalizedText("Violeta", "Violet", "Violeta")),
    WHITE(0xFFD4AF37, LocalizedText("Blanco", "White", "Branco")),
    RED(0xFF9E1F27, LocalizedText("Rojo", "Red", "Vermelho")),
    ROSE(0xFFB85977, LocalizedText("Rosa", "Rose", "Rosa"))
}

enum class CelebrationRank(val displayName: LocalizedText) {
    SOLEMNITY(LocalizedText("Solemnidad", "Solemnity", "Solenidade")),
    FEAST(LocalizedText("Fiesta", "Feast", "Festa")),
    MEMORIAL(LocalizedText("Memoria", "Memorial", "Memória")),
    SUNDAY(LocalizedText("Domingo", "Sunday", "Domingo")),
    FERIAL(LocalizedText("Feria", "Weekday", "Féria"))
}

enum class SundayCycle { A, B, C }

enum class WeekdayCycle(val displayName: LocalizedText) {
    I(LocalizedText("Año I", "Year I", "Ano I")),
    II(LocalizedText("Año II", "Year II", "Ano II"))
}

/** Everything the app needs to know about one day of the Church's year. */
data class LiturgicalDayInfo(
    val date: CalendarDate,
    val season: LiturgicalSeason,
    val color: LiturgicalColor,
    val rank: CelebrationRank,
    val title: LocalizedText,
    /** Week within the season; the 34-week count for Ordinary Time. */
    val weekOfSeason: Int,
    val liturgicalYear: Int,
    val sundayCycle: SundayCycle,
    val weekdayCycle: WeekdayCycle,
    val isHolyDayOfObligation: Boolean,
    val isFastDay: Boolean,
    val isAbstinenceDay: Boolean,
    /** Stable key into the lectionary, e.g. `ot_30_sunday` or `christmas_day`. */
    val lectionaryKey: String
)

/**
 * Computes the Roman Rite calendar (Ordinary Form, General Roman Calendar) for
 * any date, from the date of Easter outward. Nothing here is table-driven by
 * year, so it is correct past and future rather than only for a seeded range.
 *
 * Regional transfers are not applied: Ascension is given on its Thursday and
 * Epiphany on 6 January, as in the General Calendar.
 */
object LiturgicalCalendar {

    /** Gregorian Easter, by the Meeus/Jones/Butcher algorithm. */
    fun easter(year: Int): CalendarDate {
        val a = year % 19
        val b = year / 100
        val c = year % 100
        val d = b / 4
        val e = b % 4
        val f = (b + 8) / 25
        val g = (b - f + 1) / 3
        val h = (19 * a + b - d - g + 15) % 30
        val i = c / 4
        val k = c % 4
        val l = (32 + 2 * e + 2 * i - h - k) % 7
        val m = (a + 11 * h + 22 * l) / 451
        val month = (h + l - 7 * m + 114) / 31
        val day = ((h + l - 7 * m + 114) % 31) + 1
        return CalendarDate(year, month, day)
    }

    /**
     * First Sunday of Advent: four Sundays before Christmas. Derived from the
     * weekday of 25 December rather than from a fixed date, so it is right
     * every year.
     */
    fun firstSundayOfAdvent(year: Int): CalendarDate {
        val christmas = CalendarDate(year, 12, 25)
        val dow = christmas.isoDayOfWeek
        // Advent IV is the Sunday falling on or before 24 December.
        val adventFour = christmas.plusDays(if (dow == 7) -7 else -dow)
        return adventFour.plusDays(-21)
    }

    /** Baptism of the Lord: the Sunday after Epiphany, or 7 January's Monday. */
    fun baptismOfTheLord(year: Int): CalendarDate {
        val epiphany = CalendarDate(year, 1, 6)
        val dow = epiphany.isoDayOfWeek
        // Sunday after 6 January; when Epiphany itself is a Sunday the feast
        // falls on the following day.
        return if (dow == 7) epiphany.plusDays(1) else epiphany.plusDays(7 - dow)
    }

    fun ashWednesday(year: Int): CalendarDate = easter(year).plusDays(-46)
    fun palmSunday(year: Int): CalendarDate = easter(year).plusDays(-7)
    fun holyThursday(year: Int): CalendarDate = easter(year).plusDays(-3)
    fun goodFriday(year: Int): CalendarDate = easter(year).plusDays(-2)
    fun holySaturday(year: Int): CalendarDate = easter(year).plusDays(-1)
    fun divineMercySunday(year: Int): CalendarDate = easter(year).plusDays(7)
    fun ascension(year: Int): CalendarDate = easter(year).plusDays(39)
    fun pentecost(year: Int): CalendarDate = easter(year).plusDays(49)
    fun trinitySunday(year: Int): CalendarDate = easter(year).plusDays(56)
    fun corpusChristi(year: Int): CalendarDate = easter(year).plusDays(63)
    fun sacredHeart(year: Int): CalendarDate = easter(year).plusDays(68)
    fun immaculateHeart(year: Int): CalendarDate = easter(year).plusDays(69)

    /** Christ the King: the last Sunday of Ordinary Time, week 34. */
    fun christTheKing(year: Int): CalendarDate = firstSundayOfAdvent(year).plusDays(-7)

    /** Holy Family: the Sunday in the octave of Christmas, else 30 December. */
    fun holyFamily(year: Int): CalendarDate {
        val christmas = CalendarDate(year, 12, 25)
        val dow = christmas.isoDayOfWeek
        return if (dow == 7) CalendarDate(year, 12, 30) else christmas.plusDays(7 - dow)
    }

    /**
     * The liturgical year a date belongs to — it turns over at Advent, so
     * December dates belong to the following year's cycle.
     */
    fun liturgicalYearOf(date: CalendarDate): Int =
        if (date >= firstSundayOfAdvent(date.year)) date.year + 1 else date.year

    fun sundayCycleOf(liturgicalYear: Int): SundayCycle = when (liturgicalYear % 3) {
        1 -> SundayCycle.A
        2 -> SundayCycle.B
        else -> SundayCycle.C
    }

    /** Year I in odd liturgical years, Year II in even ones. */
    fun weekdayCycleOf(liturgicalYear: Int): WeekdayCycle =
        if (liturgicalYear % 2 == 1) WeekdayCycle.I else WeekdayCycle.II

    private fun weeksBetween(from: CalendarDate, to: CalendarDate): Int =
        ((to.toEpochDay() - from.toEpochDay()) / 7).toInt()

    // Fixed-date celebrations of solemnity or feast rank, keyed month/day.
    private data class Fixed(
        val title: LocalizedText,
        val rank: CelebrationRank,
        val color: LiturgicalColor,
        val obligation: Boolean = false,
        val key: String
    )

    private val fixedDays: Map<Pair<Int, Int>, Fixed> = mapOf(
        1 to 1 to Fixed(
            LocalizedText("Santa María, Madre de Dios", "Mary, the Holy Mother of God", "Santa Maria, Mãe de Deus"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, obligation = true, key = "mary_mother_of_god"
        ),
        1 to 6 to Fixed(
            LocalizedText("Epifanía del Señor", "The Epiphany of the Lord", "Epifania do Senhor"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, obligation = true, key = "epiphany"
        ),
        2 to 2 to Fixed(
            LocalizedText("Presentación del Señor", "The Presentation of the Lord", "Apresentação do Senhor"),
            CelebrationRank.FEAST, LiturgicalColor.WHITE, key = "presentation"
        ),
        3 to 19 to Fixed(
            LocalizedText("San José, Esposo de la Virgen María", "Saint Joseph, Spouse of the Blessed Virgin Mary", "São José, Esposo da Virgem Maria"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, key = "st_joseph"
        ),
        3 to 25 to Fixed(
            LocalizedText("Anunciación del Señor", "The Annunciation of the Lord", "Anunciação do Senhor"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, key = "annunciation"
        ),
        6 to 24 to Fixed(
            LocalizedText("Natividad de San Juan Bautista", "The Nativity of Saint John the Baptist", "Natividade de São João Batista"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, key = "john_baptist_nativity"
        ),
        6 to 29 to Fixed(
            LocalizedText("San Pedro y San Pablo, Apóstoles", "Saints Peter and Paul, Apostles", "São Pedro e São Paulo, Apóstolos"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.RED, key = "peter_and_paul"
        ),
        8 to 6 to Fixed(
            LocalizedText("Transfiguración del Señor", "The Transfiguration of the Lord", "Transfiguração do Senhor"),
            CelebrationRank.FEAST, LiturgicalColor.WHITE, key = "transfiguration"
        ),
        8 to 15 to Fixed(
            LocalizedText("Asunción de la Virgen María", "The Assumption of the Blessed Virgin Mary", "Assunção da Virgem Maria"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, obligation = true, key = "assumption"
        ),
        9 to 14 to Fixed(
            LocalizedText("Exaltación de la Santa Cruz", "The Exaltation of the Holy Cross", "Exaltação da Santa Cruz"),
            CelebrationRank.FEAST, LiturgicalColor.RED, key = "holy_cross"
        ),
        11 to 1 to Fixed(
            LocalizedText("Todos los Santos", "All Saints", "Todos os Santos"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, obligation = true, key = "all_saints"
        ),
        11 to 2 to Fixed(
            LocalizedText("Conmemoración de los Fieles Difuntos", "The Commemoration of All the Faithful Departed", "Comemoração de Todos os Fiéis Defuntos"),
            CelebrationRank.FEAST, LiturgicalColor.VIOLET, key = "all_souls"
        ),
        12 to 8 to Fixed(
            LocalizedText("Inmaculada Concepción de María", "The Immaculate Conception of the Blessed Virgin Mary", "Imaculada Conceição de Maria"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, obligation = true, key = "immaculate_conception"
        ),
        12 to 25 to Fixed(
            LocalizedText("Natividad del Señor", "The Nativity of the Lord", "Natividade do Senhor"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, obligation = true, key = "christmas_day"
        ),
        12 to 26 to Fixed(
            LocalizedText("San Esteban, Protomártir", "Saint Stephen, First Martyr", "Santo Estêvão, Protomártir"),
            CelebrationRank.FEAST, LiturgicalColor.RED, key = "st_stephen"
        ),
        12 to 27 to Fixed(
            LocalizedText("San Juan, Apóstol y Evangelista", "Saint John, Apostle and Evangelist", "São João, Apóstolo e Evangelista"),
            CelebrationRank.FEAST, LiturgicalColor.WHITE, key = "st_john"
        ),
        12 to 28 to Fixed(
            LocalizedText("Santos Inocentes, Mártires", "The Holy Innocents, Martyrs", "Santos Inocentes, Mártires"),
            CelebrationRank.FEAST, LiturgicalColor.RED, key = "holy_innocents"
        )
    )

    private val ordinals = mapOf(
        1 to Triple("I", "1st", "1º"), 2 to Triple("II", "2nd", "2º"),
        3 to Triple("III", "3rd", "3º"), 4 to Triple("IV", "4th", "4º"),
        5 to Triple("V", "5th", "5º"), 6 to Triple("VI", "6th", "6º"),
        7 to Triple("VII", "7th", "7º")
    )

    private fun seasonSundayTitle(season: LiturgicalSeason, week: Int): LocalizedText {
        val roman = ordinals[week]?.first ?: week.toString()
        val english = ordinals[week]?.second ?: "${week}th"
        val portuguese = ordinals[week]?.third ?: "${week}º"
        return when (season) {
            LiturgicalSeason.ADVENT -> LocalizedText(
                "Domingo $roman de Adviento", "$english Sunday of Advent", "$portuguese Domingo do Advento"
            )
            LiturgicalSeason.LENT -> LocalizedText(
                "Domingo $roman de Cuaresma", "$english Sunday of Lent", "$portuguese Domingo da Quaresma"
            )
            LiturgicalSeason.EASTER -> LocalizedText(
                "Domingo $roman de Pascua", "$english Sunday of Easter", "$portuguese Domingo da Páscoa"
            )
            else -> LocalizedText(
                "Domingo $week del Tiempo Ordinario",
                "${ordinalWordEnglish(week)} Sunday in Ordinary Time",
                "$week.º Domingo do Tempo Comum"
            )
        }
    }

    private fun ordinalEnglish(n: Int): String {
        val suffix = when {
            n % 100 in 11..13 -> "th"
            n % 10 == 1 -> "st"
            n % 10 == 2 -> "nd"
            n % 10 == 3 -> "rd"
            else -> "th"
        }
        return "$n$suffix"
    }

    private fun weekdayTitle(season: LiturgicalSeason, week: Int, date: CalendarDate): LocalizedText {
        val dayEs = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")[date.isoDayOfWeek - 1]
        val dayEn = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")[date.isoDayOfWeek - 1]
        val dayPt = listOf("Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado", "Domingo")[date.isoDayOfWeek - 1]
        val seasonEs = season.displayName.es
        val seasonEn = season.displayName.en
        val seasonPt = season.displayName.pt
        // The Missal's own diction: "Saturday of the Thirty-first Week in
        // Ordinary Time", "Sábado de la semana XXXI del Tiempo Ordinario".
        val ordinary = season == LiturgicalSeason.ORDINARY_TIME
        return LocalizedText(
            "$dayEs de la semana ${romanNumeral(week)} " +
                if (ordinary || season == LiturgicalSeason.EASTER) "del $seasonEs" else "de $seasonEs",
            "$dayEn of the ${ordinalWordEnglish(week)} Week " + if (ordinary) "in $seasonEn" else "of $seasonEn",
            "$dayPt da ${week}.ª Semana " + if (season == LiturgicalSeason.LENT) "da $seasonPt" else "do $seasonPt"
        )
    }

    private fun romanNumeral(n: Int): String {
        val table = listOf(10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I")
        var left = n
        return buildString {
            for ((value, glyph) in table) while (left >= value) { append(glyph); left -= value }
        }
    }

    private fun ordinalWordEnglish(n: Int): String {
        val units = listOf("", "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth")
        val teens = listOf("Tenth", "Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth",
            "Sixteenth", "Seventeenth", "Eighteenth", "Nineteenth")
        val tensOrdinal = mapOf(2 to "Twentieth", 3 to "Thirtieth")
        val tens = mapOf(2 to "Twenty", 3 to "Thirty")
        return when {
            n < 10 -> units[n.coerceAtLeast(1)]
            n < 20 -> teens[n - 10]
            n % 10 == 0 -> tensOrdinal[n / 10] ?: ordinalEnglish(n)
            else -> (tens[n / 10]?.let { "$it-${units[n % 10].lowercase()}" }) ?: ordinalEnglish(n)
        }
    }

    /** The full description of [date]. */
    fun dayInfo(date: CalendarDate): LiturgicalDayInfo {
        val year = date.year
        val liturgicalYear = liturgicalYearOf(date)
        val sundayCycle = sundayCycleOf(liturgicalYear)
        val weekdayCycle = weekdayCycleOf(liturgicalYear)

        val easter = easter(year)
        val ash = ashWednesday(year)
        val palm = palmSunday(year)
        val holyThu = holyThursday(year)
        val holySat = holySaturday(year)
        val pentecost = pentecost(year)
        val adventStart = firstSundayOfAdvent(year)
        val baptism = baptismOfTheLord(year)
        val lentFirstSunday = ash.plusDays(4)

        // Movable celebrations outrank the seasonal day they fall on.
        movableFor(date, year)?.let { movable ->
            return movable.toInfo(
                date, liturgicalYear, sundayCycle, weekdayCycle,
                weekOfSeason = weekOfSeason(date, year, movable.season)
            )
        }

        // Then fixed solemnities and feasts, except inside Holy Week and the
        // Triduum, which nothing in the General Calendar displaces.
        val inHolyWeekOrTriduum = date >= palm && date < easter
        if (!inHolyWeekOrTriduum) {
            fixedDays[date.month to date.day]?.let { fixed ->
                return LiturgicalDayInfo(
                    date = date,
                    season = seasonOf(date, year),
                    color = fixed.color,
                    rank = fixed.rank,
                    title = fixed.title,
                    weekOfSeason = weekOfSeason(date, year, seasonOf(date, year)),
                    liturgicalYear = liturgicalYear,
                    sundayCycle = sundayCycle,
                    weekdayCycle = weekdayCycle,
                    isHolyDayOfObligation = fixed.obligation || date.isSunday,
                    isFastDay = false,
                    isAbstinenceDay = false,
                    lectionaryKey = fixed.key
                )
            }
        }

        val season = seasonOf(date, year)
        val week = weekOfSeason(date, year, season)

        val isLentFriday = season == LiturgicalSeason.LENT && date.isFriday && date >= lentFirstSunday
        val color = when {
            season == LiturgicalSeason.ADVENT && week == 3 && date.isSunday -> LiturgicalColor.ROSE
            season == LiturgicalSeason.LENT && week == 4 && date.isSunday -> LiturgicalColor.ROSE
            season == LiturgicalSeason.ADVENT || season == LiturgicalSeason.LENT -> LiturgicalColor.VIOLET
            season == LiturgicalSeason.HOLY_WEEK -> LiturgicalColor.RED
            season == LiturgicalSeason.TRIDUUM -> if (date == holySat) LiturgicalColor.WHITE else LiturgicalColor.RED
            season == LiturgicalSeason.CHRISTMAS || season == LiturgicalSeason.EASTER -> LiturgicalColor.WHITE
            else -> LiturgicalColor.GREEN
        }

        val rank = when {
            season == LiturgicalSeason.TRIDUUM -> CelebrationRank.SOLEMNITY
            date.isSunday -> CelebrationRank.SUNDAY
            else -> CelebrationRank.FERIAL
        }

        val title = when {
            date == palm -> LocalizedText(
                "Domingo de Ramos en la Pasión del Señor",
                "Palm Sunday of the Passion of the Lord",
                "Domingo de Ramos na Paixão do Senhor"
            )
            date == holyThu -> LocalizedText(
                "Jueves Santo — Cena del Señor", "Holy Thursday — The Lord's Supper", "Quinta-feira Santa — Ceia do Senhor"
            )
            date == holySat -> LocalizedText(
                "Sábado Santo — Vigilia Pascual", "Holy Saturday — The Easter Vigil", "Sábado Santo — Vigília Pascal"
            )
            season == LiturgicalSeason.HOLY_WEEK -> weekdayTitle(season, 0, date)
            date.isSunday -> seasonSundayTitle(season, week)
            else -> weekdayTitle(season, week, date)
        }

        return LiturgicalDayInfo(
            date = date,
            season = season,
            color = color,
            rank = rank,
            title = title,
            weekOfSeason = week,
            liturgicalYear = liturgicalYear,
            sundayCycle = sundayCycle,
            weekdayCycle = weekdayCycle,
            isHolyDayOfObligation = date.isSunday,
            isFastDay = date == ash,
            isAbstinenceDay = date == ash || isLentFriday,
            lectionaryKey = lectionaryKey(season, week, date, easter, adventStart, baptism, pentecost)
        )
    }

    private data class Movable(
        val title: LocalizedText,
        val rank: CelebrationRank,
        val color: LiturgicalColor,
        val season: LiturgicalSeason,
        val key: String,
        val obligation: Boolean = false,
        val fast: Boolean = false,
        val abstinence: Boolean = false
    ) {
        fun toInfo(
            date: CalendarDate,
            liturgicalYear: Int,
            sundayCycle: SundayCycle,
            weekdayCycle: WeekdayCycle,
            weekOfSeason: Int
        ) = LiturgicalDayInfo(
            date = date,
            season = season,
            color = color,
            rank = rank,
            title = title,
            weekOfSeason = weekOfSeason,
            liturgicalYear = liturgicalYear,
            sundayCycle = sundayCycle,
            weekdayCycle = weekdayCycle,
            isHolyDayOfObligation = obligation || date.isSunday,
            isFastDay = fast,
            isAbstinenceDay = abstinence,
            lectionaryKey = key
        )
    }

    private fun movableFor(date: CalendarDate, year: Int): Movable? = when (date) {
        easter(year) -> Movable(
            LocalizedText("Domingo de Pascua de Resurrección", "Easter Sunday of the Resurrection of the Lord", "Domingo de Páscoa da Ressurreição"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, LiturgicalSeason.EASTER, "easter_sunday", obligation = true
        )
        goodFriday(year) -> Movable(
            LocalizedText("Viernes Santo de la Pasión del Señor", "Good Friday of the Passion of the Lord", "Sexta-feira Santa da Paixão do Senhor"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.RED, LiturgicalSeason.TRIDUUM, "good_friday",
            fast = true, abstinence = true
        )
        ashWednesday(year) -> Movable(
            LocalizedText("Miércoles de Ceniza", "Ash Wednesday", "Quarta-feira de Cinzas"),
            CelebrationRank.FERIAL, LiturgicalColor.VIOLET, LiturgicalSeason.LENT, "ash_wednesday",
            fast = true, abstinence = true
        )
        divineMercySunday(year) -> Movable(
            LocalizedText("Domingo de la Divina Misericordia", "Divine Mercy Sunday", "Domingo da Divina Misericórdia"),
            CelebrationRank.SUNDAY, LiturgicalColor.WHITE, LiturgicalSeason.EASTER, "divine_mercy", obligation = true
        )
        ascension(year) -> Movable(
            LocalizedText("Ascensión del Señor", "The Ascension of the Lord", "Ascensão do Senhor"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, LiturgicalSeason.EASTER, "ascension", obligation = true
        )
        pentecost(year) -> Movable(
            LocalizedText("Pentecostés", "Pentecost Sunday", "Pentecostes"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.RED, LiturgicalSeason.EASTER, "pentecost", obligation = true
        )
        trinitySunday(year) -> Movable(
            LocalizedText("Santísima Trinidad", "The Most Holy Trinity", "Santíssima Trindade"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, LiturgicalSeason.ORDINARY_TIME, "trinity", obligation = true
        )
        corpusChristi(year) -> Movable(
            LocalizedText("Santísimo Cuerpo y Sangre de Cristo", "The Most Holy Body and Blood of Christ", "Santíssimo Corpo e Sangue de Cristo"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, LiturgicalSeason.ORDINARY_TIME, "corpus_christi", obligation = true
        )
        sacredHeart(year) -> Movable(
            LocalizedText("Sagrado Corazón de Jesús", "The Most Sacred Heart of Jesus", "Sagrado Coração de Jesus"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, LiturgicalSeason.ORDINARY_TIME, "sacred_heart"
        )
        immaculateHeart(year) -> Movable(
            LocalizedText("Inmaculado Corazón de María", "The Immaculate Heart of the Blessed Virgin Mary", "Imaculado Coração de Maria"),
            CelebrationRank.MEMORIAL, LiturgicalColor.WHITE, LiturgicalSeason.ORDINARY_TIME, "immaculate_heart"
        )
        christTheKing(year) -> Movable(
            LocalizedText("Jesucristo, Rey del Universo", "Our Lord Jesus Christ, King of the Universe", "Nosso Senhor Jesus Cristo, Rei do Universo"),
            CelebrationRank.SOLEMNITY, LiturgicalColor.WHITE, LiturgicalSeason.ORDINARY_TIME, "christ_the_king", obligation = true
        )
        holyFamily(year) -> Movable(
            LocalizedText("Sagrada Familia de Jesús, María y José", "The Holy Family of Jesus, Mary and Joseph", "Sagrada Família de Jesus, Maria e José"),
            CelebrationRank.FEAST, LiturgicalColor.WHITE, LiturgicalSeason.CHRISTMAS, "holy_family"
        )
        baptismOfTheLord(year) -> Movable(
            LocalizedText("Bautismo del Señor", "The Baptism of the Lord", "Batismo do Senhor"),
            CelebrationRank.FEAST, LiturgicalColor.WHITE, LiturgicalSeason.CHRISTMAS, "baptism_of_the_lord"
        )
        else -> null
    }

    fun seasonOf(date: CalendarDate, year: Int = date.year): LiturgicalSeason {
        val easter = easter(year)
        val ash = ashWednesday(year)
        val palm = palmSunday(year)
        val holyThu = holyThursday(year)
        val pentecost = pentecost(year)
        val adventStart = firstSundayOfAdvent(year)
        val baptism = baptismOfTheLord(year)
        // Ordered earliest-to-latest through the year; each branch therefore
        // only has to rule out the one before it.
        return when {
            date >= adventStart -> LiturgicalSeason.ADVENT
            date >= CalendarDate(year, 12, 25) -> LiturgicalSeason.CHRISTMAS
            date <= baptism -> LiturgicalSeason.CHRISTMAS
            date < ash -> LiturgicalSeason.ORDINARY_TIME
            date < palm -> LiturgicalSeason.LENT
            date < holyThu -> LiturgicalSeason.HOLY_WEEK
            date < easter -> LiturgicalSeason.TRIDUUM
            date <= pentecost -> LiturgicalSeason.EASTER
            else -> LiturgicalSeason.ORDINARY_TIME
        }
    }

    private fun weekOfSeason(date: CalendarDate, year: Int, season: LiturgicalSeason): Int {
        return when (season) {
            LiturgicalSeason.ADVENT ->
                weeksBetween(firstSundayOfAdvent(year), date) + 1

            LiturgicalSeason.LENT -> {
                // Lent's weeks are counted from the First Sunday, not from Ash
                // Wednesday, which sits in a short preparatory week.
                val firstSunday = ashWednesday(year).plusDays(4)
                if (date < firstSunday) 0 else weeksBetween(firstSunday, date) + 1
            }

            LiturgicalSeason.EASTER ->
                weeksBetween(easter(year), date) + 1

            LiturgicalSeason.ORDINARY_TIME -> ordinaryTimeWeek(date, year)

            else -> 0
        }
    }

    /**
     * Ordinary Time runs in two stretches that share one 34-week count: the
     * first picks up after the Baptism of the Lord, the second is counted
     * backwards from week 34, which always ends at Christ the King.
     */
    private fun ordinaryTimeWeek(date: CalendarDate, year: Int): Int {
        val baptism = baptismOfTheLord(year)
        val ash = ashWednesday(year)
        return if (date < ash) {
            // Week 1 is the week the Baptism closes; the next Sunday opens week 2.
            val sundayAfterBaptism = baptism.plusDays(7)
            if (date < sundayAfterBaptism) 1 else weeksBetween(sundayAfterBaptism, date) + 2
        } else {
            val king = christTheKing(year)
            (34 - weeksBetween(date, king)).coerceIn(1, 34)
        }
    }

    private fun lectionaryKey(
        season: LiturgicalSeason,
        week: Int,
        date: CalendarDate,
        easter: CalendarDate,
        adventStart: CalendarDate,
        baptism: CalendarDate,
        pentecost: CalendarDate
    ): String {
        val dow = date.isoDayOfWeek
        val seasonSlug = when (season) {
            LiturgicalSeason.ADVENT -> "advent"
            LiturgicalSeason.CHRISTMAS -> "christmas"
            LiturgicalSeason.LENT -> "lent"
            LiturgicalSeason.HOLY_WEEK -> "holy_week"
            LiturgicalSeason.TRIDUUM -> "triduum"
            LiturgicalSeason.EASTER -> "easter"
            LiturgicalSeason.ORDINARY_TIME -> "ot"
        }
        return if (date.isSunday) "${seasonSlug}_${week}_sunday"
        else "${seasonSlug}_${week}_day$dow"
    }

    /** Today, in the device's own time zone. */
    fun today(): CalendarDate {
        val cal = java.util.Calendar.getInstance()
        return CalendarDate(
            cal.get(java.util.Calendar.YEAR),
            cal.get(java.util.Calendar.MONTH) + 1,
            cal.get(java.util.Calendar.DAY_OF_MONTH)
        )
    }
}
