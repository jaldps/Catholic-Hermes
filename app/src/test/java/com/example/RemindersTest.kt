package com.example

import com.example.data.reminders.NovenaState
import com.example.data.reminders.ReminderCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class RemindersTest {

    @Test
    fun catalogHasSevenDailyDevotionsWithTrilingualText() {
        assertEquals(7, ReminderCatalog.DEFAULTS.size)
        for (r in ReminderCatalog.DEFAULTS) {
            assertTrue(r.id.isNotBlank())
            assertTrue(r.titleEs.isNotBlank())
            assertTrue(r.titleEn.isNotBlank())
            assertTrue(r.titlePt.isNotBlank())
            assertTrue(r.defaultHour in 0..23)
            assertTrue(r.defaultMinute in 0..59)
        }
        assertEquals(
            setOf("angelus_morning", "angelus_midday", "angelus_evening", "coronilla", "morning_prayer", "night_prayer", "daily_reading"),
            ReminderCatalog.DEFAULTS.map { it.id }.toSet(),
        )
    }

    @Test
    fun novenaStartsAtDayOne() {
        val today = LocalDate.now().toEpochDay()
        val novena = NovenaState("San Judas", today)
        assertEquals(1, novena.dayNumber(today))
        assertFalse(novena.isComplete(today))
    }

    @Test
    fun novenaCountsUpAndCompletesOnDayNine() {
        val today = LocalDate.now().toEpochDay()
        val novena = NovenaState("San Judas", today - 8)
        assertEquals(9, novena.dayNumber(today))
        assertTrue(novena.isComplete(today))
    }

    @Test
    fun novenaClampsAfterCompletion() {
        val today = LocalDate.now().toEpochDay()
        val novena = NovenaState("San Judas", today - 30)
        assertEquals(9, novena.dayNumber(today))
        assertTrue(novena.isComplete(today))
    }
}
