package com.example.data.reminders

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract

/** Android CalendarContract integration. The user explicitly chooses the account/calendar. */
object GoogleCalendarSync {
    data class WritableCalendar(val id: Long, val name: String, val account: String)

    fun writableCalendars(context: Context): List<WritableCalendar> {
        val fields=arrayOf(CalendarContract.Calendars._ID,CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
            CalendarContract.Calendars.ACCOUNT_NAME,CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL)
        return context.contentResolver.query(CalendarContract.Calendars.CONTENT_URI,fields,null,null,null)?.use { cursor ->
            buildList {
                while(cursor.moveToNext()) {
                    if(cursor.getInt(3)>=CalendarContract.Calendars.CAL_ACCESS_CONTRIBUTOR) {
                        add(WritableCalendar(cursor.getLong(0),cursor.getString(1).orEmpty(),cursor.getString(2).orEmpty()))
                    }
                }
            }.sortedWith(compareBy<WritableCalendar>{it.account}.thenBy{it.name}.thenBy{it.id})
        }.orEmpty()
    }

    fun calendarForEvent(context: Context, eventId: Long): Long? = context.contentResolver.query(
        ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI,eventId),
        arrayOf(CalendarContract.Events.CALENDAR_ID),"${CalendarContract.Events.DELETED} = 0",null,null
    )?.use { if(it.moveToFirst())it.getLong(0) else null }

    /** A stable provider-side marker makes retries safe even if saving the local event ID failed. */
    private fun existingManagedEvent(context: Context, calendarId: Long, marker: String): Long? =
        context.contentResolver.query(CalendarContract.Events.CONTENT_URI,arrayOf(CalendarContract.Events._ID),
            "${CalendarContract.Events.CALENDAR_ID} = ? AND ${CalendarContract.Events.CUSTOM_APP_PACKAGE} = ? AND ${CalendarContract.Events.CUSTOM_APP_URI} = ? AND ${CalendarContract.Events.DELETED} = 0",
            arrayOf(calendarId.toString(),context.packageName,marker),CalendarContract.Events._ID+" ASC")
            ?.use { if(it.moveToFirst())it.getLong(0) else null }

    fun syncReminder(context: Context, reminder: DevotionReminder, hour: Int, minute: Int, language: String,
        calendarId: Long, existingEventId: Long? = null): Long? {
        if(writableCalendars(context).none {it.id==calendarId})return null
        val marker="catholic-hermes://reminder/${reminder.id}"
        val values=ContentValues().apply {
            put(CalendarContract.Events.TITLE,reminder.calendarTitle(language))
            put(CalendarContract.Events.DESCRIPTION,reminder.calendarBody(language))
            put(CalendarContract.Events.DTSTART,ReminderScheduler.nextTriggerMillis(hour,minute))
            put(CalendarContract.Events.DURATION,"PT15M")
            put(CalendarContract.Events.EVENT_TIMEZONE,java.util.TimeZone.getDefault().id)
            put(CalendarContract.Events.RRULE,"FREQ=DAILY")
            put(CalendarContract.Events.CUSTOM_APP_PACKAGE,context.packageName)
            put(CalendarContract.Events.CUSTOM_APP_URI,marker)
        }
        val existing=existingEventId?.takeIf {calendarForEvent(context,it)==calendarId}
            ?: existingManagedEvent(context,calendarId,marker)
        val eventId=if(existing!=null) {
            val count=context.contentResolver.update(ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI,existing),
                values,"${CalendarContract.Events.CALENDAR_ID} = ? AND ${CalendarContract.Events.DELETED} = 0",arrayOf(calendarId.toString()))
            if(count<=0)return null
            existing
        } else {
            values.put(CalendarContract.Events.CALENDAR_ID,calendarId)
            context.contentResolver.insert(CalendarContract.Events.CONTENT_URI,values)?.lastPathSegment?.toLongOrNull() ?: return null
        }
        val hasAlarm=context.contentResolver.query(CalendarContract.Reminders.CONTENT_URI,
            arrayOf(CalendarContract.Reminders._ID),"${CalendarContract.Reminders.EVENT_ID} = ?",arrayOf(eventId.toString()),null)
            ?.use {it.moveToFirst()} ?: false
        if(!hasAlarm) {
            val alarm=ContentValues().apply {
                put(CalendarContract.Reminders.EVENT_ID,eventId)
                put(CalendarContract.Reminders.MINUTES,0)
                put(CalendarContract.Reminders.METHOD,CalendarContract.Reminders.METHOD_ALERT)
            }
            checkNotNull(context.contentResolver.insert(CalendarContract.Reminders.CONTENT_URI,alarm))
        }
        return eventId
    }
}
