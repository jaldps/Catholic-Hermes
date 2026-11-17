package com.example

import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.CalendarRules
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.notices.NoticeCelebrations
import com.example.data.notices.NoticeContext
import com.example.data.notices.NoticeSelector
import com.example.data.notices.NoticeType
import com.example.data.notices.Notices
import com.example.data.notices.NoticesCatalog
import com.example.data.notices.NovenaNoticeData
import com.example.data.notices.NovenaRecipient
import com.example.data.notices.PlanNoticeData
import com.example.data.repository.NovenaCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/** Pure selection of the notice for a given day (T-032), over the reviewed T-035 texts. */
class NoticeSelectorTest {
    private val notices: Notices = requireNotNull(NoticesCatalog { File("src/main/assets/notices/notices.json").inputStream() }.load())
    private val none = NoticeContext()
    private fun d(y: Int, m: Int, day: Int) = CalendarDate(y, m, day)
    private fun pick(type: NoticeType, date: CalendarDate, context: NoticeContext = none, lang: AppLanguage = AppLanguage.ES) =
        NoticeSelector.select(type, date, notices, context, lang)

    @Test fun catalogHasEverySeasonCelebrationAndReminder() {
        assertEquals(7, notices.seasons.size)
        assertEquals(32, notices.seasons.values.sumOf { it.weekly.size })
        assertEquals(48, notices.celebrations.size)
        assertEquals(NoticeCelebrations.ALL, notices.celebrations.keys)
        assertEquals(7, notices.reminders.size)
    }

    @Test fun firstSundayOfAdventIsAdventStartAndItsFirstWeek() {
        val advent = d(2026, 11, 29)
        val start = pick(NoticeType.SEASON_START, advent)!!
        assertEquals("ADVENT.start", start.key)
        assertEquals("Comienza el Adviento", start.title)
        assertEquals("ROSARY", start.action)
        assertNull(pick(NoticeType.SEASON_START, advent.plusDays(1)))
        assertNull(pick(NoticeType.SEASON_START, advent.plusDays(-1)))
        assertEquals("advent_w1", pick(NoticeType.SEASON_WEEKLY, advent)!!.key)
        assertEquals("advent_w2", pick(NoticeType.SEASON_WEEKLY, advent.plusDays(7))!!.key)
        assertEquals("advent_w4", pick(NoticeType.SEASON_WEEKLY, advent.plusDays(21))!!.key)
        assertNull("weekly notices come on Sundays", pick(NoticeType.SEASON_WEEKLY, advent.plusDays(3)))
        // Every season begins exactly once in its year.
        assertEquals("LENT.start", pick(NoticeType.SEASON_START, d(2027, 2, 10))!!.key) // Ash Wednesday 2027
        assertEquals("EASTER.start", pick(NoticeType.SEASON_START, d(2027, 3, 28), lang = AppLanguage.EN)!!.key)
    }

    @Test fun eveOfPentecostIsPentecostEve() {
        val eve = pick(NoticeType.FEAST_EVE, d(2026, 5, 23))!! // Pentecost 2026: 24 May
        assertEquals("pentecost.eve", eve.key)
        assertEquals("Víspera de Pentecostés", eve.title)
        assertEquals("SPIRITUAL_LIFE", eve.action) // The eve opens what the day opens.
        assertEquals("pentecost.day", pick(NoticeType.FEAST_DAY, d(2026, 5, 24))!!.key)
        assertEquals("Eve of Pentecost", pick(NoticeType.FEAST_EVE, d(2026, 5, 23), lang = AppLanguage.EN)!!.title)
    }

    @Test fun fixedFeastsFollowTransfersAndImpededFeastsAreSilent() {
        // Annunciation 2024 fell on Monday of Holy Week: kept on Monday 8 April.
        assertNull(pick(NoticeType.FEAST_DAY, d(2024, 3, 25)))
        assertEquals("annunciation_of_the_lord.day", pick(NoticeType.FEAST_DAY, d(2024, 4, 8))!!.key)
        assertEquals("annunciation_of_the_lord.eve", pick(NoticeType.FEAST_EVE, d(2024, 4, 7))!!.key)
        // Immaculate Conception 2024 fell on the Second Sunday of Advent: kept on Monday 9 December.
        assertNull(pick(NoticeType.FEAST_DAY, d(2024, 12, 8)))
        assertEquals("immaculate_conception_of_the_blessed_virgin_mary.day", pick(NoticeType.FEAST_DAY, d(2024, 12, 9))!!.key)
        // The person's own transfer rule: Epiphany on the Sunday between 2 and 8 January.
        val sunday = NoticeContext(CalendarRules(epiphanyOnSunday = true))
        assertEquals("epiphany.day", pick(NoticeType.FEAST_DAY, d(2027, 1, 3), sunday)!!.key)
        assertNull(pick(NoticeType.FEAST_DAY, d(2027, 1, 6), sunday))
        assertEquals("epiphany.day", pick(NoticeType.FEAST_DAY, d(2027, 1, 6))!!.key)
        // Saint Andrew on the First Sunday of Advent 2025 is impeded: no notice, neither eve nor day.
        assertNull(pick(NoticeType.FEAST_DAY, d(2025, 11, 30)))
        assertNull(pick(NoticeType.FEAST_EVE, d(2025, 11, 29)))
        // A memorial is not a feast notice.
        assertNull(pick(NoticeType.FEAST_DAY, d(2026, 11, 17))) // Saint Elizabeth of Hungary
    }

    @Test fun everyCelebrationIsAnnouncedOverFiveYears() {
        val seen = mutableSetOf<String>()
        var date = d(2026, 1, 1)
        while (date < d(2031, 1, 1)) {
            NoticeCelebrations.on(date, CalendarRules())?.let { seen += it }
            date = date.plusDays(1)
        }
        assertEquals(NoticeCelebrations.ALL - seen, emptySet<String>())
    }

    @Test fun novenaDayUsesTheRecipientNameNotTheTitle() {
        val joseph = NovenaRecipient.of(LocalizedText("Novena a San José", "St. Joseph Novena", "Novena a São José"))
        val context = NoticeContext(novena = NovenaNoticeData(joseph, 3))
        val es = pick(NoticeType.NOVENA_DAY, d(2026, 11, 17), context)!!
        assertEquals("Novena: día 3", es.title)
        assertEquals("Hoy es el día 3 de la novena de San José. Reza hoy con confianza.", es.body)
        assertEquals("Today is day 3 of the novena of St. Joseph. Pray today with trust.",
            pick(NoticeType.NOVENA_DAY, d(2026, 11, 17), context, AppLanguage.EN)!!.body)
        assertEquals("Hoje é o dia 3 da novena de São José. Reza hoje com confiança.",
            pick(NoticeType.NOVENA_DAY, d(2026, 11, 17), context, AppLanguage.PT)!!.body)
        // Articles contract: «del Sagrado Corazón», «da Imaculada Conceição».
        val heart = NovenaRecipient.of(LocalizedText("Novena al Sagrado Corazón de Jesús", "Sacred Heart Novena", "Novena ao Sagrado Coração de Jesus"))
        assertTrue(pick(NoticeType.NOVENA_DAY, d(2026, 11, 17), NoticeContext(novena = NovenaNoticeData(heart, 1)))!!.body
            .contains("de la novena del Sagrado Corazón de Jesús"))
        val immaculate = NovenaRecipient.of(LocalizedText("Novena a la Inmaculada", "Immaculate Conception Novena", "Novena à Imaculada Conceição"))
        assertTrue(pick(NoticeType.NOVENA_DAY, d(2026, 11, 17), NoticeContext(novena = NovenaNoticeData(immaculate, 1)), AppLanguage.PT)!!.body
            .contains("da novena da Imaculada Conceição"))
        // An intention never reaches the notification; a name that is an intention is left out.
        assertEquals("San Rafael", NovenaRecipient.spanish("Novena a San Rafael para solteros que buscan esposo o esposa"))
        assertNull(NovenaRecipient.of(LocalizedText("Novena para la fertilidad", "Fertility Novena to Conceive a Baby", "Novena para a fertilidade")))
        val nameless = pick(NoticeType.NOVENA_DAY, d(2026, 11, 17), NoticeContext(novena = NovenaNoticeData(null, 5)))!!
        assertEquals("Hoy es el día 5 de la novena. Reza hoy con confianza.", nameless.body)
        // Nothing in progress: nothing to say.
        assertNull(pick(NoticeType.NOVENA_DAY, d(2026, 11, 17)))
    }

    @Test fun everyCatalogNovenaGetsANameWithoutTheWordNovena() {
        val novenas = NovenaCatalog { File("src/main/assets/novenas/$it").inputStream() }.allNovenas
        assertTrue(novenas.size > 200)
        val names = novenas.mapNotNull { NovenaRecipient.of(it.title) }
        assertTrue("most novenas are named", names.size > novenas.size * 9 / 10)
        names.forEach { n -> listOf(n.es, n.en, n.pt).forEach {
            assertFalse(it, it.contains("novena", ignoreCase = true)); assertTrue(it.isNotBlank()) } }
    }

    @Test fun holyDaysOfObligationOnlyWhenChosen() {
        val eve = d(2026, 12, 7) // Immaculate Conception 2026 is a Tuesday.
        assertNull("off unless the person ticks it", pick(NoticeType.HOLY_DAY_OF_OBLIGATION, eve))
        assertFalse(NoticeSelector.occurs(NoticeType.HOLY_DAY_OF_OBLIGATION, eve, notices, none))
        val chosen = NoticeContext(obligationDays = setOf("immaculate_conception"))
        val message = pick(NoticeType.HOLY_DAY_OF_OBLIGATION, eve, chosen)!!
        assertEquals("holy_day_of_obligation.immaculate_conception", message.key)
        assertEquals("Víspera de precepto", message.title)
        assertNull("other days stay silent", pick(NoticeType.HOLY_DAY_OF_OBLIGATION, d(2026, 10, 31), chosen))
        // A rubrical transfer is not announced as a day of precept (2024: kept on Monday 9 December).
        assertNull(pick(NoticeType.HOLY_DAY_OF_OBLIGATION, d(2024, 12, 8), chosen))
        // Ascension moved to Sunday by the person's rules is left to the Sunday notice.
        val ascension = NoticeContext(CalendarRules(ascensionOnSunday = true), setOf("ascension"))
        assertNull(pick(NoticeType.HOLY_DAY_OF_OBLIGATION, d(2026, 5, 16), ascension))
        assertNotNull(pick(NoticeType.HOLY_DAY_OF_OBLIGATION, d(2026, 5, 13), NoticeContext(obligationDays = setOf("ascension"))))
    }

    @Test fun weeklyAndMonthlyRemindersAndPlans() {
        assertEquals("sunday_mass", pick(NoticeType.SUNDAY_MASS, d(2026, 11, 21))!!.key) // Saturday
        assertNull(pick(NoticeType.SUNDAY_MASS, d(2026, 11, 22)))
        assertEquals("Un gesto de vuelta", pick(NoticeType.CONFESSION_GENTLE, d(2026, 12, 5))!!.title) // first Saturday
        assertNull(pick(NoticeType.CONFESSION_GENTLE, d(2026, 12, 12)))
        assertEquals(d(2026, 12, 5), NoticeSelector.nextOccurrence(NoticeType.CONFESSION_GENTLE, d(2026, 11, 17), notices, none))
        val plan = NoticeContext(plan = PlanNoticeData(LocalizedText("La Biblia en un año", "The Bible in a year", "A Bíblia em um ano"), 12))
        assertEquals("Plan: La Biblia en un año, día 12", pick(NoticeType.PLAN_DAY, d(2026, 11, 17), plan)!!.title)
        assertNull("a day already read is not announced",
            pick(NoticeType.PLAN_DAY, d(2026, 11, 17), plan.copy(plan = plan.plan!!.copy(doneToday = true))))
        assertEquals("CONFESSION", pick(NoticeType.EXAMEN_NIGHT, d(2026, 11, 17))!!.action)
    }

    @Test fun holyWeekAndTriduumGiveEachDayItsText() {
        assertEquals("holyweek_w1", pick(NoticeType.SEASON_WEEKLY, d(2026, 3, 29))!!.key) // Palm Sunday 2026
        assertEquals("holyweek_w4", pick(NoticeType.SEASON_WEEKLY, d(2026, 4, 1))!!.key)
        assertEquals("triduum_w2", pick(NoticeType.SEASON_WEEKLY, d(2026, 4, 3))!!.key) // Good Friday
    }
}
