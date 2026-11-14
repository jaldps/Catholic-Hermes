package com.example.data.reminders

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract

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
     * Writes one daily recurring event (15 min) starting at the next
     * [hour]:[minute], with a popup reminder at event time, and returns its
     * event id (null on failure).
     *
     * When [existingEventId] still names an event in the calendar, that event
     * is updated in place (new time, title, description) instead of inserting
     * a second copy, so tapping "Add to Google Calendar" twice never leaves
     * duplicate daily events behind.
     */
    fun syncReminder(
        context: Context,
        reminder: DevotionReminder,
        hour: Int,
        minute: Int,
        language: String,
        existingEventId: Long? = null,
    ): Long? {
        val start = ReminderScheduler.nextTriggerMillis(hour, minute)
        // Recurring events take a DURATION, not a DTEND (CalendarContract).
        val values = ContentValues().apply {
            put(CalendarContract.Events.TITLE, reminder.calendarTitle(language))
            put(CalendarContract.Events.DESCRIPTION, reminder.calendarBody(language))
            put(CalendarContract.Events.DTSTART, start)
            put(CalendarContract.Events.DURATION, "PT15M")
            put(CalendarContract.Events.EVENT_TIMEZONE, java.util.TimeZone.getDefault().id)
            put(CalendarContract.Events.RRULE, "FREQ=DAILY")
        }

        if (existingEventId != null) {
            val updated = runCatching {
                context.contentResolver.update(
                    ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, existingEventId),
                    values,
                    "${CalendarContract.Events.DELETED} = 0",
                    null
                )
            }.getOrDefault(0)
            if (updated > 0) return existingEventId
        }

        val calendarId = firstWritableCalendarId(context) ?: return null
        values.put(CalendarContract.Events.CALENDAR_ID, calendarId)
        val eventUri = context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
            ?: return null
        val eventId = eventUri.lastPathSegment?.toLongOrNull() ?: return null

        val reminderValues = ContentValues().apply {
            put(CalendarContract.Reminders.EVENT_ID, eventId)
            put(CalendarContract.Reminders.MINUTES, 0)
            put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
        }
        context.contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, reminderValues)
        return eventId
    }
}
