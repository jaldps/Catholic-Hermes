package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.reminders.ReminderScheduler
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import java.util.Calendar
import java.util.TimeZone

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ReminderIntentTest {
    @Test fun tappingReminderOpensExplicitActivityWithDevotionId() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val pending = ReminderScheduler.contentIntent(context, "coronilla")
        val intent = shadowOf(pending).savedIntent
        assertEquals(MainActivity::class.java.name, intent.component?.className)
        assertEquals(ReminderScheduler.ACTION_OPEN_REMINDER, intent.action)
        assertEquals("coronilla", intent.getStringExtra(ReminderScheduler.EXTRA_ID))
        assertTrue(pending.isImmutable)
    }

    @Test fun changedTimezoneUsesLocalPrayerHourAndNextOccurrence() {
        for (zone in listOf("America/New_York", "Europe/Rome", "Pacific/Auckland")) {
            val now = Calendar.getInstance(TimeZone.getTimeZone(zone)).apply {
                set(2026, Calendar.NOVEMBER, 12, 13, 10, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val next = Calendar.getInstance(now.timeZone).apply {
                timeInMillis = ReminderScheduler.nextTriggerMillis(12, 0, now)
            }
            assertEquals(13, next.get(Calendar.DAY_OF_MONTH))
            assertEquals(12, next.get(Calendar.HOUR_OF_DAY))
            assertEquals(0, next.get(Calendar.MINUTE))
        }
    }
}
