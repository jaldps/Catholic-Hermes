package com.example

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.test.core.app.ApplicationProvider
import com.example.data.reminders.*
import com.example.data.liturgy.CalendarDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(sdk=[34])
class ReminderReliabilityRegressionTest {
    private val app=ApplicationProvider.getApplicationContext<Application>()
    private class FailingStore: DataStore<Preferences> {
        var failed=true
        var preferences: Preferences=emptyPreferences()
        override val data: Flow<Preferences> get()=flow {if(failed)throw IOException("injected");emit(preferences)}
        override suspend fun updateData(transform:suspend (Preferences)->Preferences):Preferences {
            if(failed)throw IOException("injected")
            preferences=transform(preferences);return preferences
        }
    }
    @Test fun failedReadDoesNotEnableDefaultRemindersAndRetryRestoresActualChoices()=runBlocking {
        val data=FailingStore()
        val store=ReminderStore(app,data)
        val unavailable=store.reminderSnapshot.first()
        assertFalse(unavailable.available)
        assertTrue(unavailable.reminders.values.none {it.enabled})
        assertTrue(store.readFailure.value)
        data.failed=false
        data.preferences=emptyPreferences().toMutablePreferences().apply {
            this[booleanPreferencesKey("rem_enabled_angelus_midday")]=false
            this[booleanPreferencesKey("rem_enabled_coronilla")]=false
        }
        store.retryRead()
        val recovered=store.reminderSnapshot.first()
        assertTrue(recovered.available)
        assertTrue(recovered.reminders.values.none {it.enabled})
        assertFalse(store.readFailure.value)
    }
    @Test fun failedReconciliationPreservesExistingAlarms()=runBlocking {
        val reminder=ReminderCatalog.DEFAULTS.first()
        ReminderScheduler.schedule(app,reminder,12,0,"es")
        val alarms=shadowOf(app.getSystemService(Context.ALARM_SERVICE) as AlarmManager)
        val count=alarms.scheduledAlarms.size
        try { ReminderScheduler.scheduleEnabled(app,"es",ReminderStore(app,FailingStore()));fail("Must expose failure") }
        catch(_:IOException) {}
        assertEquals(count,alarms.scheduledAlarms.size)
    }
    @Test fun blockedNotificationChannelDoesNotReportTestSuccess() {
        val manager=app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        shadowOf(manager).setNotificationsEnabled(true)
        manager.createNotificationChannel(NotificationChannel(REMINDER_CHANNEL_ID,"Prayer",NotificationManager.IMPORTANCE_NONE))
        assertFalse(ReminderScheduler.notificationsAvailable(app))
        assertFalse(ReminderScheduler.sendTestNotification(app,"Test","Body"))
    }
    @Test fun notificationAndCalendarTitlesHandleEasterWithoutFreezingARecurrence() {
        val reminder=ReminderCatalog.byId("angelus_midday")!!
        assertEquals("Regina Caeli",reminder.title("es",CalendarDate(2026,4,5)))
        assertTrue(reminder.body("en",CalendarDate(2026,4,5)).contains("Regina Caeli"))
        assertEquals("El Ángelus",reminder.title("es",CalendarDate(2026,4,4)))
        assertTrue(reminder.calendarTitle("es").contains("Ángelus"))
        assertTrue(reminder.calendarTitle("es").contains("Regina Caeli"))
    }
}
