package com.example

import android.app.AlarmManager
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.reminders.ReminderScheduler
import com.example.data.reminders.ReminderStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ReminderSchedulingTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    /** The reminders that are ON by default really get an alarm at app start. */
    @Test
    fun defaultRemindersAreScheduledWithoutAReboot() = runBlocking {
        val store = ReminderStore(context)
        val scheduled = ReminderScheduler.scheduleEnabled(context, "es", store)
        assertEquals(2, scheduled)
        val alarms = shadowOf(context.getSystemService(Context.ALARM_SERVICE) as AlarmManager)
        assertEquals(2, alarms.scheduledAlarms.size)
        // Idempotent: a second pass replaces, never duplicates.
        ReminderScheduler.scheduleEnabled(context, "es", store)
        assertEquals(2, alarms.scheduledAlarms.size)
    }

    /** The calendar event id is kept so a second sync updates instead of duplicating. */
    @Test
    fun calendarEventIdRoundTrips() = runBlocking {
        val store = ReminderStore(context)
        store.setCalendarEvent("coronilla", 42L)
        assertEquals(42L, store.calendarEvents.first()["coronilla"])
        store.setCalendarEvent("coronilla", null)
        assertNull(store.calendarEvents.first()["coronilla"])
    }
}
