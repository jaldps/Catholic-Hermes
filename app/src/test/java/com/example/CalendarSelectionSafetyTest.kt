package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.reminders.GoogleCalendarSync
import com.example.data.reminders.ReminderCatalog
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk=[34])
class CalendarSelectionSafetyTest {
    private val app=ApplicationProvider.getApplicationContext<Application>()
    private val provider=FakeCalendarProvider.install(app)
    @After fun close(){provider.database.close()}
    @Test fun listsWritableAccountsIncludingContributorWithoutChoosingOne() {
        val options=GoogleCalendarSync.writableCalendars(app)
        assertEquals(setOf(1L,2L),options.map{it.id}.toSet())
        assertEquals(0,provider.count("events"))
    }
    @Test fun chosenCalendarReceivesEventAndLostLocalLinkDoesNotDuplicateIt() {
        val reminder=ReminderCatalog.byId("coronilla")!!
        val first=GoogleCalendarSync.syncReminder(app,reminder,15,0,"es",calendarId=2L)!!
        assertEquals(2L,GoogleCalendarSync.calendarForEvent(app,first))
        val retry=GoogleCalendarSync.syncReminder(app,reminder,15,30,"es",calendarId=2L,existingEventId=null)
        assertEquals(first,retry)
        assertEquals(1,provider.count("events"))
        assertEquals(1,provider.count("reminders"))
    }
    @Test fun legacyEventInAnotherAccountIsNeverOverwritten() {
        provider.database.execSQL("INSERT INTO events (_id,calendar_id,title) VALUES (90,1,'Keep my work event')")
        val event=GoogleCalendarSync.syncReminder(app,ReminderCatalog.byId("coronilla")!!,15,0,"es",calendarId=2L,existingEventId=90L)
        assertNotNull(event);assertNotEquals(90L,event)
        provider.database.rawQuery("SELECT title FROM events WHERE _id=90",null).use {it.moveToFirst();assertEquals("Keep my work event",it.getString(0))}
    }
    @Test fun readOnlyCalendarCannotReceiveWrites() {
        assertNull(GoogleCalendarSync.syncReminder(app,ReminderCatalog.byId("coronilla")!!,15,0,"es",calendarId=3L))
        assertEquals(0,provider.count("events"))
    }
}
