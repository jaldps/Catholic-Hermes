package com.example

import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.CalendarRules
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.LiturgicalColor
import com.example.data.liturgy.LiturgicalSeason
import org.junit.Assert.*
import org.junit.Test

/**
 * Independent published fixtures, not dates generated from the implementation.
 * USCCB calendar 2024, introductory notes 6(b–e), pp. 7–8 and daily entries:
 * https://www.usccb.org/resources/2024cal.pdf
 * USCCB calendar 2023, notes 6(b–c):
 * https://www.usccb.org/resources/2023cal.pdf
 * USCCB calendar 2019, January 6/13 and June 23 entries:
 * https://www.usccb.org/resources/2019cal.pdf
 * These US fixtures specify transferred Epiphany; they are not universal local law.
 */
class LiturgicalRegressionTest {
    private fun date(year: Int, month: Int, day: Int) = CalendarDate(year, month, day)
    private val sundayTransfers = CalendarRules(epiphanyOnSunday = true, ascensionOnSunday = true, corpusChristiOnSunday = true)

    @Test fun christmasOctaveAndBaptismBoundaries() {
        for (day in 25..31) {
            assertEquals("December $day", LiturgicalSeason.CHRISTMAS,
                LiturgicalCalendar.dayInfo(date(2024, 12, day)).season)
        }
        assertEquals(LiturgicalSeason.ADVENT, LiturgicalCalendar.seasonOf(date(2024, 12, 24)))
        assertEquals(date(2024, 1, 7), LiturgicalCalendar.baptismOfTheLord(2024))
        assertEquals(date(2024, 1, 8), LiturgicalCalendar.baptismOfTheLord(2024, sundayTransfers))
        assertEquals(LiturgicalSeason.CHRISTMAS, LiturgicalCalendar.dayInfo(date(2024, 1, 8), sundayTransfers).season)
        assertEquals(LiturgicalSeason.ORDINARY_TIME, LiturgicalCalendar.dayInfo(date(2024, 1, 9), sundayTransfers).season)
        assertEquals("ot_2_sunday", LiturgicalCalendar.dayInfo(date(2024, 1, 14), sundayTransfers).lectionaryKey)
        assertEquals(date(2019, 1, 13), LiturgicalCalendar.baptismOfTheLord(2019))
        assertEquals(date(2019, 1, 13), LiturgicalCalendar.baptismOfTheLord(2019, sundayTransfers))
    }

    @Test fun seasonalSundaysYieldNoPlaceToFixedSolemnities() {
        assertEquals("advent_2_sunday", LiturgicalCalendar.dayInfo(date(2024, 12, 8)).lectionaryKey)
        assertEquals("immaculate_conception", LiturgicalCalendar.dayInfo(date(2024, 12, 9)).lectionaryKey)
        assertEquals("lent_4_sunday", LiturgicalCalendar.dayInfo(date(2023, 3, 19)).lectionaryKey)
        assertEquals("st_joseph", LiturgicalCalendar.dayInfo(date(2023, 3, 20)).lectionaryKey)
    }

    @Test fun annunciationTransfersPastHolyWeekAndEntireEasterOctave() {
        assertNotEquals("annunciation", LiturgicalCalendar.dayInfo(date(2024, 3, 25)).lectionaryKey)
        assertEquals("annunciation", LiturgicalCalendar.dayInfo(date(2024, 4, 8)).lectionaryKey)
        // 2016's March 25 was Good Friday: the first free day is April 4.
        assertEquals("good_friday", LiturgicalCalendar.dayInfo(date(2016, 3, 25)).lectionaryKey)
        assertNotEquals("annunciation", LiturgicalCalendar.dayInfo(date(2016, 3, 28)).lectionaryKey)
        assertEquals("annunciation", LiturgicalCalendar.dayInfo(date(2016, 4, 4)).lectionaryKey)
    }

    @Test fun saintFeastsYieldToSundayAndLordFeastsReplaceOrdinarySunday() {
        assertEquals("holy_family", LiturgicalCalendar.dayInfo(date(2021, 12, 26)).lectionaryKey)
        assertEquals("presentation", LiturgicalCalendar.dayInfo(date(2020, 2, 2)).lectionaryKey)
        assertEquals("all_souls", LiturgicalCalendar.dayInfo(date(2025, 11, 2)).lectionaryKey)
    }

    @Test fun generalRomanAndExplicitSundayTransferPoliciesRemainDistinct() {
        assertEquals(date(2024, 5, 9), LiturgicalCalendar.ascension(2024))
        assertEquals(date(2024, 5, 12), LiturgicalCalendar.ascension(2024, sundayTransfers))
        assertEquals(date(2019, 6, 20), LiturgicalCalendar.corpusChristi(2019))
        assertEquals(date(2019, 6, 23), LiturgicalCalendar.corpusChristi(2019, sundayTransfers))
        assertEquals("easter_7_sunday", LiturgicalCalendar.dayInfo(date(2024, 5, 12)).lectionaryKey)
        assertEquals("ascension", LiturgicalCalendar.dayInfo(date(2024, 5, 12), sundayTransfers).lectionaryKey)
        val christmas = LiturgicalCalendar.dayInfo(date(2024, 12, 25))
        assertFalse("Unconfigured local obligation must not be asserted", christmas.isHolyDayOfObligation)
        assertTrue(christmas.isUniversalHolyDayCandidate)
        assertTrue(christmas.calendarNeedsLocalConfirmation)
    }

    @Test fun firstFridayAfterAshWednesdayAndHolyWeekColors() {
        val friday = LiturgicalCalendar.dayInfo(date(2024, 2, 16))
        assertTrue(friday.isAbstinenceDay)
        assertFalse(friday.isFastDay)
        assertEquals("Friday after Ash Wednesday", friday.title.en)
        assertEquals(LiturgicalColor.VIOLET, LiturgicalCalendar.dayInfo(date(2024, 3, 25)).color)
        assertEquals(LiturgicalColor.WHITE, LiturgicalCalendar.dayInfo(date(2024, 3, 28)).color)
        // A solemnity on a Friday is excluded from the general abstinence rule.
        assertFalse(LiturgicalCalendar.dayInfo(date(2022, 3, 25)).isAbstinenceDay)
        assertTrue(LiturgicalCalendar.dayInfo(date(2024, 2, 2)).isAbstinenceDay)
    }
}
