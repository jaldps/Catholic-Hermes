package com.example.data.reminders

import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract
import java.util.Calendar

/**
 * Phase 2: writes a daily recurring event into the user's own Google
 * Calendar through Android's CalendarContract. No API key, no OAuth, no
 * server — it uses the Google account already signed in on the device and
 * the READ/WRITE_CALENDAR permissions the user grants at runtime.
 */
object GoogleCalendarSync {

    fun interface Callback {
        fun onResult(success: Boolean, message: String)
    }

    /** First calendar the user can write to (their Google calendar), or null. */
    fun firstWritableCalendarId(context: Context): Long? {
        val uri = CalendarContract.Calendars.CONTENT_URI
        val projection = arrayOf(
            CalendarContract.Calendars._ID,
            CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
        )
        return context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            val idCol = cursor.getColumnIndex(CalendarContract.Calendars._ID)
            val accessCol = cursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL)
            while (cursor.moveToNext()) {
                val access = cursor.getInt(accessCol)
                // CalendarContract.CalendarColumns.CAL_ACCESS_CALENDAR == 700
                if (access >= 700) {
                    return@use cursor.getLong(idCol)
                }
            }
            null
        }
    }

    /**
     * Inserts one daily recurring event (15 min) starting at the next
     * [hour]:[minute], with a popup reminder at event time.
     * Returns true when the event was created.
     */
    fun syncReminder(
        context: Context,
        reminder: DevotionReminder,
        hour: Int,
        minute: Int,
        language: String,
    ): Boolean {
        val calendarId = firstWritableCalendarId(context) ?: return false
        val start = ReminderScheduler.nextTriggerMillis(hour, minute)
        val cal = Calendar.getInstance().apply { timeInMillis = start }
        val end = (cal.clone() as Calendar).apply { add(Calendar.MINUTE, 15) }.timeInMillis

        val values = ContentValues().apply {
            put(CalendarContract.Events.CALENDAR_ID, calendarId)
            put(CalendarContract.Events.TITLE, reminder.title(language))
            put(CalendarContract.Events.DESCRIPTION, reminder.body(language))
            put(CalendarContract.Events.DTSTART, start)
            put(CalendarContract.Events.DTEND, end)
            put(CalendarContract.Events.EVENT_TIMEZONE, java.util.TimeZone.getDefault().id)
            put(CalendarContract.Events.RRULE, "FREQ=DAILY")
        }
        val eventUri = context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
            ?: return false
        val eventId = eventUri.lastPathSegment?.toLongOrNull() ?: return false

        val reminderValues = ContentValues().apply {
            put(CalendarContract.Reminders.EVENT_ID, eventId)
            put(CalendarContract.Reminders.MINUTES, 0)
            put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
        }
        context.contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, reminderValues)
        return true
    }
}
