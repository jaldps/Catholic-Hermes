package com.example

import android.Manifest
import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Looper
import androidx.test.core.app.ApplicationProvider
import com.example.data.notices.NoticeScheduler
import com.example.data.notices.NoticeStore
import com.example.data.notices.NoticeType
import com.example.data.persistence.PersonalDataCommands
import com.example.data.reminders.ReminderBootReceiver
import com.example.data.reminders.ReminderScheduler
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import java.util.Calendar

/** One alarm per notice type, never more; rescheduled after a reboot; the alarm posts today's text with its tap. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class NoticeSchedulingTest {
    private val app = ApplicationProvider.getApplicationContext<Application>()
    private val alarms get() = shadowOf(app.getSystemService(Context.ALARM_SERVICE) as AlarmManager)
    private val store = NoticeStore(app)

    @After fun idle() = runBlocking { PersonalDataCommands.awaitIdle() }

    /** The reminders DataStore outlives a single test in this JVM: start each one from the defaults. */
    @Before fun reset() = runBlocking {
        NoticeType.entries.forEach { store.setEnabled(it, false); store.setTime(it, it.defaultHour, it.defaultMinute) }
        store.setObligationDays(emptySet())
    }

    private fun noticeAlarms() = alarms.scheduledAlarms.filter { shadowOf(it.operation).savedIntent.action == NoticeScheduler.ACTION_NOTICE_FIRE }
    private fun typeOf(alarm: org.robolectric.shadows.ShadowAlarmManager.ScheduledAlarm) =
        shadowOf(alarm.operation).savedIntent.getStringExtra(NoticeScheduler.EXTRA_NOTICE_TYPE)

    /** Tuesday 17 November 2026, 10:00. */
    private fun tuesday(hour: Int = 10, minute: Int = 0) = Calendar.getInstance().apply { clear(); set(2026, Calendar.NOVEMBER, 17, hour, minute, 0) }

    @Test fun everythingIsOffByDefault() = runBlocking {
        val fresh = NoticeStore.parse(androidx.datastore.preferences.core.emptyPreferences())
        assertTrue(NoticeType.entries.none { fresh.pref(it).enabled })
        assertTrue(fresh.obligationDays.isEmpty())
        val settings = store.settings.first()
        assertTrue(settings.available)
        assertTrue(NoticeType.entries.none { settings.pref(it).enabled })
        assertTrue(settings.obligationDays.isEmpty())
        assertEquals(0, NoticeScheduler.scheduleEnabled(app, store, tuesday()))
        assertTrue(noticeAlarms().isEmpty())
    }

    @Test fun oneAlarmPerTypeAtItsNextOccurrence() = runBlocking {
        store.setEnabled(NoticeType.SUNDAY_MASS, true)
        store.setEnabled(NoticeType.ROSARY_EVENING, true)
        store.setEnabled(NoticeType.SEASON_START, true)
        store.setEnabled(NoticeType.HOLY_DAY_OF_OBLIGATION, true) // No days chosen: nothing to schedule.
        assertEquals(3, NoticeScheduler.scheduleEnabled(app, store, tuesday()))
        // Rescheduling replaces, never accumulates.
        repeat(3) { NoticeScheduler.scheduleEnabled(app, store, tuesday(11)) }
        val pending = noticeAlarms()
        assertEquals(3, pending.size)
        assertEquals(setOf("sunday_mass", "rosary_evening", "season_start"), pending.map { typeOf(it) }.toSet())
        fun at(type: String) = Calendar.getInstance().apply { timeInMillis = pending.single { typeOf(it) == type }.triggerAtTime }
        at("sunday_mass").let { assertEquals(21, it.get(Calendar.DAY_OF_MONTH)); assertEquals(18, it.get(Calendar.HOUR_OF_DAY)) } // Saturday 18:00
        at("rosary_evening").let { assertEquals(17, it.get(Calendar.DAY_OF_MONTH)); assertEquals(20, it.get(Calendar.HOUR_OF_DAY)) }
        at("season_start").let { assertEquals(29, it.get(Calendar.DAY_OF_MONTH)); assertEquals(Calendar.NOVEMBER, it.get(Calendar.MONTH)) } // Advent
        // Past today's time, the daily notice moves to tomorrow.
        NoticeScheduler.scheduleEnabled(app, store, tuesday(21))
        assertEquals(18, Calendar.getInstance().apply { timeInMillis = noticeAlarms().single { typeOf(it) == "rosary_evening" }.triggerAtTime }
            .get(Calendar.DAY_OF_MONTH))
        // Choosing a holy day adds its own single alarm, on the eve.
        store.setObligationDays(setOf("immaculate_conception"))
        assertEquals(4, NoticeScheduler.scheduleEnabled(app, store, tuesday()))
        assertEquals(7, Calendar.getInstance().apply { timeInMillis = noticeAlarms().single { typeOf(it) == "holy_day_of_obligation" }.triggerAtTime }
            .get(Calendar.DAY_OF_MONTH))
        // Turning one off cancels its alarm.
        store.setEnabled(NoticeType.ROSARY_EVENING, false)
        assertEquals(3, NoticeScheduler.scheduleEnabled(app, store, tuesday()))
        assertNull(noticeAlarms().firstOrNull { typeOf(it) == "rosary_evening" })
    }

    @Test fun bootReschedulesTheEnabledNotices() {
        runBlocking {
            store.setEnabled(NoticeType.EXAMEN_NIGHT, true)
            store.setEnabled(NoticeType.FEAST_DAY, true)
        }
        assertTrue(noticeAlarms().isEmpty()) // As after a reboot: the system dropped every alarm.
        app.sendBroadcast(Intent(Intent.ACTION_BOOT_COMPLETED).setClass(app, ReminderBootReceiver::class.java))
        shadowOf(Looper.getMainLooper()).idle()
        val deadline = System.currentTimeMillis() + 10_000
        while (noticeAlarms().size < 2 && System.currentTimeMillis() < deadline) Thread.sleep(50)
        assertEquals(setOf("examen_night", "feast_day"), noticeAlarms().map { typeOf(it) }.toSet())
    }

    @Test fun firingPostsTodaysTextAndItsTapOpensTheDestination() = runBlocking {
        shadowOf(app).grantPermissions(Manifest.permission.POST_NOTIFICATIONS)
        store.setEnabled(NoticeType.SUNDAY_MASS, true)
        val saturday = Calendar.getInstance().apply { clear(); set(2026, Calendar.NOVEMBER, 21, 18, 0, 0) }
        val day = NoticeScheduler.dateOf(saturday).toEpochDay()
        NoticeScheduler.onFire(app, "sunday_mass", day, saturday)
        val system = app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val manager = shadowOf(system)
        val posted = manager.allNotifications.single()
        val extras = posted.extras
        assertTrue(extras.getString("android.title")!!.isNotBlank())
        assertTrue(extras.getCharSequence("android.text")!!.isNotBlank())
        val tap = shadowOf(posted.contentIntent).savedIntent
        assertEquals(MainActivity::class.java.name, tap.component?.className)
        assertEquals(ReminderScheduler.ACTION_OPEN_REMINDER, tap.action)
        assertEquals("notice:sunday_mass:HOME", tap.getStringExtra(ReminderScheduler.EXTRA_ID))
        assertEquals("HOME", NoticeScheduler.actionOf(tap.getStringExtra(ReminderScheduler.EXTRA_ID)))
        // Chained: the next Saturday is already pending.
        val next = Calendar.getInstance().apply { timeInMillis = noticeAlarms().single().triggerAtTime }
        assertEquals(28, next.get(Calendar.DAY_OF_MONTH))
        // A late alarm (another day) only reschedules.
        system.cancelAll()
        NoticeScheduler.onFire(app, "sunday_mass", day - 7, saturday)
        assertTrue(manager.allNotifications.isEmpty())
        // A type switched off in the meantime stays silent.
        store.setEnabled(NoticeType.SUNDAY_MASS, false)
        NoticeScheduler.onFire(app, "sunday_mass", day, saturday)
        assertTrue(manager.allNotifications.isEmpty())
    }

    @Test fun feastNotificationNamesTheCelebration() = runBlocking {
        shadowOf(app).grantPermissions(Manifest.permission.POST_NOTIFICATIONS)
        store.setEnabled(NoticeType.FEAST_EVE, true)
        val eve = Calendar.getInstance().apply { clear(); set(2026, Calendar.MAY, 23, 19, 0, 0) }
        NoticeScheduler.onFire(app, "feast_eve", NoticeScheduler.dateOf(eve).toEpochDay(), eve)
        val posted = shadowOf(app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).allNotifications.single()
        assertNotNull(posted.extras.getString("android.title"))
        assertTrue(posted.extras.getString("android.title")!!.contains("Pentecost"))
        assertTrue(NoticeScheduler.actionOf(shadowOf(posted.contentIntent).savedIntent.getStringExtra(ReminderScheduler.EXTRA_ID)) == "SPIRITUAL_LIFE")
    }
}

