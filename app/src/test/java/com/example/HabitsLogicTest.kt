package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.PilgrimageAndHabitsData
import com.example.ui.screens.DailyRule
import com.example.ui.screens.TemptationLog
import com.example.ui.screens.millisUntilNextDay
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime

/** Rule-of-life and temptation-log logic behind the Habits screen. */
class HabitsLogicTest {

    @Test
    fun ruleHasTwelvePracticesInThreeGroups() {
        assertEquals(12, DailyRule.size)
        assertEquals(listOf(4, 5, 3), DailyRule.groups.map { it.size })
        assertEquals(12, DailyRule.ids.size)
    }

    @Test
    fun nightGroupIsSpanishInSpanish() {
        assertEquals("Anochecer", DailyRule.groupLabel(2, AppLanguage.ES))
        assertEquals("Nightfall", DailyRule.groupLabel(2, AppLanguage.EN))
        assertEquals("Anoitecer", DailyRule.groupLabel(2, AppLanguage.PT))
    }

    @Test
    fun genericVictoryIsNotLoggedAsTheFirstRoot() {
        val log = TemptationLog.victory(null, AppLanguage.ES, "  ofrecida  ")
        assertEquals(TemptationLog.GENERIC_KEY, log.sinKey)
        assertEquals("", log.counterVirtue)
        assertEquals("ofrecida", log.note)
        assertTrue(log.sinKey != PilgrimageAndHabitsData.sinAvoiderCatalog.first().sinKey)
    }

    @Test
    fun rootVictoryKeepsItsRootAndVirtue() {
        val pride = PilgrimageAndHabitsData.sinAvoiderCatalog.first { it.sinKey == "PRIDE" }
        val log = TemptationLog.victory(pride, AppLanguage.EN, "")
        assertEquals("PRIDE", log.sinKey)
        assertEquals("Humility & Truth", log.counterVirtue)
    }

    @Test
    fun ruleCountsIgnoreLegacyHabits() {
        val rows = listOf(
            com.example.data.local.HabitCompletionLogEntity(habitId = "daily_rosary", completionDate = "2026-11-08"),
            com.example.data.local.HabitCompletionLogEntity(habitId = "daily_examen", completionDate = "2026-11-08"),
            com.example.data.local.HabitCompletionLogEntity(habitId = "habit_angelus", completionDate = "2026-11-08"),
            com.example.data.local.HabitCompletionLogEntity(habitId = "daily_rosary", completionDate = "2026-11-08")
        )
        assertEquals(mapOf("2026-11-08" to 2), DailyRule.countsFrom(rows))
    }

    @Test
    fun todayRollsOverAtMidnight() {
        assertEquals(60_000L, millisUntilNextDay(LocalDateTime.of(2026, 11, 8, 23, 59, 0)))
        assertEquals(24L * 3600 * 1000, millisUntilNextDay(LocalDateTime.of(2026, 11, 8, 0, 0, 0)))
    }
}
