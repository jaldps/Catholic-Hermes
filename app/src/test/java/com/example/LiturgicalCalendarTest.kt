package com.example

import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.CelebrationRank
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.LiturgicalColor
import com.example.data.liturgy.LiturgicalSeason
import com.example.data.liturgy.SundayCycle
import com.example.data.liturgy.WeekdayCycle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The calendar is computed rather than tabulated, so these are checks against
 * published dates across a spread of years — including years where Easter sits
 * at either extreme of its range.
 */
class LiturgicalCalendarTest {

    private fun date(year: Int, month: Int, day: Int) = CalendarDate(year, month, day)

    @Test
    fun epochDayRoundTrips() {
        var day = CalendarDate(1900, 1, 1).toEpochDay()
        val end = CalendarDate(2100, 1, 1).toEpochDay()
        while (day < end) {
            val roundTripped = CalendarDate.fromEpochDay(day)
            assertEquals(day, roundTripped.toEpochDay())
            day += 97 // a prime-ish stride, to cross month and leap boundaries
        }
    }

    @Test
    fun dayOfWeekMatchesKnownDates() {
        assertEquals(4, date(2026, 10, 29).isoDayOfWeek) // Thursday
        assertEquals(7, date(2026, 10, 25).isoDayOfWeek) // Sunday
        assertEquals(1, date(2000, 1, 3).isoDayOfWeek)   // Monday
        assertTrue(date(2026, 4, 5).isSunday)            // Easter 2026
    }

    @Test
    fun easterMatchesPublishedDates() {
        assertEquals(date(2024, 3, 31), LiturgicalCalendar.easter(2024))
        assertEquals(date(2025, 4, 20), LiturgicalCalendar.easter(2025))
        assertEquals(date(2026, 4, 5), LiturgicalCalendar.easter(2026))
        assertEquals(date(2027, 3, 28), LiturgicalCalendar.easter(2027))
        assertEquals(date(2030, 4, 21), LiturgicalCalendar.easter(2030))
        assertEquals(date(2038, 4, 25), LiturgicalCalendar.easter(2038)) // latest possible
        assertEquals(date(2285, 3, 22), LiturgicalCalendar.easter(2285)) // earliest possible
    }

    @Test
    fun movableFeastsMatchPublishedDates() {
        assertEquals(date(2026, 2, 18), LiturgicalCalendar.ashWednesday(2026))
        assertEquals(date(2025, 3, 5), LiturgicalCalendar.ashWednesday(2025))
        assertEquals(date(2026, 3, 29), LiturgicalCalendar.palmSunday(2026))
        assertEquals(date(2026, 5, 24), LiturgicalCalendar.pentecost(2026))
        assertEquals(date(2026, 5, 14), LiturgicalCalendar.ascension(2026))
        assertEquals(date(2026, 1, 11), LiturgicalCalendar.baptismOfTheLord(2026))
        assertEquals(date(2025, 12, 28), LiturgicalCalendar.holyFamily(2025))
    }

    @Test
    fun adventBeginsFourSundaysBeforeChristmas() {
        assertEquals(date(2024, 12, 1), LiturgicalCalendar.firstSundayOfAdvent(2024))
        assertEquals(date(2025, 11, 30), LiturgicalCalendar.firstSundayOfAdvent(2025))
        assertEquals(date(2026, 11, 29), LiturgicalCalendar.firstSundayOfAdvent(2026))
        assertEquals(date(2027, 11, 28), LiturgicalCalendar.firstSundayOfAdvent(2027))

        // Advent always opens on a Sunday, in every year.
        for (year in 2000..2100) {
            assertTrue(
                "Advent $year does not begin on a Sunday",
                LiturgicalCalendar.firstSundayOfAdvent(year).isSunday
            )
        }
    }

    @Test
    fun christTheKingIsTheSundayBeforeAdvent() {
        assertEquals(date(2026, 11, 22), LiturgicalCalendar.christTheKing(2026))
        val info = LiturgicalCalendar.dayInfo(date(2026, 11, 22))
        assertEquals(CelebrationRank.SOLEMNITY, info.rank)
        assertEquals("christ_the_king", info.lectionaryKey)
    }

    @Test
    fun cyclesTurnOverAtAdvent() {
        // Liturgical year 2026 runs from Advent 2025 to November 2026.
        assertEquals(2026, LiturgicalCalendar.liturgicalYearOf(date(2025, 12, 1)))
        assertEquals(2026, LiturgicalCalendar.liturgicalYearOf(date(2026, 10, 29)))
        assertEquals(2027, LiturgicalCalendar.liturgicalYearOf(date(2026, 11, 29)))

        assertEquals(SundayCycle.C, LiturgicalCalendar.sundayCycleOf(2025))
        assertEquals(SundayCycle.A, LiturgicalCalendar.sundayCycleOf(2026))
        assertEquals(SundayCycle.B, LiturgicalCalendar.sundayCycleOf(2027))

        assertEquals(WeekdayCycle.I, LiturgicalCalendar.weekdayCycleOf(2025))
        assertEquals(WeekdayCycle.II, LiturgicalCalendar.weekdayCycleOf(2026))
    }

    @Test
    fun seasonsCoverTheWholeYearWithoutGaps() {
        // Walking a full liturgical year must never produce a season that
        // contradicts the date's position relative to the season's own bounds.
        var day = date(2026, 1, 1)
        val end = date(2026, 12, 31)
        var sawAdvent = false
        var sawLent = false
        var sawEaster = false
        var sawOrdinary = false
        var sawTriduum = false
        while (day <= end) {
            when (LiturgicalCalendar.seasonOf(day)) {
                LiturgicalSeason.ADVENT -> sawAdvent = true
                LiturgicalSeason.LENT -> sawLent = true
                LiturgicalSeason.EASTER -> sawEaster = true
                LiturgicalSeason.ORDINARY_TIME -> sawOrdinary = true
                LiturgicalSeason.TRIDUUM -> sawTriduum = true
                else -> Unit
            }
            day = day.plusDays(1)
        }
        assertTrue(sawAdvent && sawLent && sawEaster && sawOrdinary && sawTriduum)
    }

    @Test
    fun easterSeasonIsNotMisreportedAsHolyWeek() {
        // Easter Monday through Pentecost must all read as Easter.
        val easter = LiturgicalCalendar.easter(2026)
        var day = easter.plusDays(1)
        val pentecost = LiturgicalCalendar.pentecost(2026)
        while (day <= pentecost) {
            assertEquals(
                "wrong season on ${day.year}-${day.month}-${day.day}",
                LiturgicalSeason.EASTER,
                LiturgicalCalendar.seasonOf(day)
            )
            day = day.plusDays(1)
        }
    }

    @Test
    fun ordinaryTimeIsNumberedToThirtyFour() {
        assertEquals(30, LiturgicalCalendar.dayInfo(date(2026, 10, 25)).weekOfSeason)
        assertEquals(34, LiturgicalCalendar.dayInfo(date(2026, 11, 22)).weekOfSeason)
        // Every Sunday of Ordinary Time stays inside 1..34.
        for (year in 2024..2032) {
            var day = date(year, 1, 1)
            while (day <= date(year, 12, 31)) {
                val info = LiturgicalCalendar.dayInfo(day)
                if (info.season == LiturgicalSeason.ORDINARY_TIME) {
                    assertTrue(
                        "OT week ${info.weekOfSeason} out of range on ${day.year}-${day.month}-${day.day}",
                        info.weekOfSeason in 1..34
                    )
                }
                day = day.plusDays(1)
            }
        }
    }

    @Test
    fun coloursFollowTheSeason() {
        assertEquals(LiturgicalColor.VIOLET, LiturgicalCalendar.dayInfo(date(2026, 2, 18)).color)
        assertEquals(LiturgicalColor.RED, LiturgicalCalendar.dayInfo(date(2026, 5, 24)).color)
        assertEquals(LiturgicalColor.WHITE, LiturgicalCalendar.dayInfo(date(2026, 12, 25)).color)
        assertEquals(LiturgicalColor.GREEN, LiturgicalCalendar.dayInfo(date(2026, 10, 25)).color)
        // Gaudete and Laetare are rose.
        assertEquals(LiturgicalColor.ROSE, LiturgicalCalendar.dayInfo(date(2025, 12, 14)).color)
    }

    @Test
    fun fastAndAbstinenceFallOnTheRightDays() {
        val ashWednesday = LiturgicalCalendar.dayInfo(date(2026, 2, 18))
        assertTrue(ashWednesday.isFastDay && ashWednesday.isAbstinenceDay)

        val goodFriday = LiturgicalCalendar.dayInfo(date(2026, 4, 3))
        assertTrue(goodFriday.isFastDay && goodFriday.isAbstinenceDay)

        // A Friday in Lent is abstinence but not a day of fast.
        val lentFriday = LiturgicalCalendar.dayInfo(date(2026, 3, 6))
        assertTrue(lentFriday.isAbstinenceDay)
        assertTrue(!lentFriday.isFastDay)

        // A Friday in Ordinary Time carries neither.
        val ordinaryFriday = LiturgicalCalendar.dayInfo(date(2026, 10, 30))
        assertTrue(!ordinaryFriday.isFastDay)
    }

    @Test
    fun christmasAndEasterAreSolemnities() {
        assertEquals(CelebrationRank.SOLEMNITY, LiturgicalCalendar.dayInfo(date(2026, 12, 25)).rank)
        assertEquals("christmas_day", LiturgicalCalendar.dayInfo(date(2026, 12, 25)).lectionaryKey)
        assertEquals("easter_sunday", LiturgicalCalendar.dayInfo(date(2026, 4, 5)).lectionaryKey)
        assertEquals("good_friday", LiturgicalCalendar.dayInfo(date(2026, 4, 3)).lectionaryKey)
        assertEquals("pentecost", LiturgicalCalendar.dayInfo(date(2026, 5, 24)).lectionaryKey)
    }
}
