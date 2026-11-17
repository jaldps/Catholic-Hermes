package com.example.data.notices

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.data.diagnostics.AppLog
import com.example.data.diagnostics.LogEvent
import com.example.data.liturgy.CalendarDate
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.reminders.REMINDER_CHANNEL_ID
import com.example.data.reminders.ReminderReceiver
import com.example.data.reminders.ReminderScheduler
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.Calendar

/**
 * Liturgical notices on top of the reminder machinery: the same receiver, channel, exact-alarm fallback and activity
 * intent as the daily reminders. Each [NoticeType] owns exactly one PendingIntent, so there is never more than one
 * pending alarm per type; scheduling again replaces it. The alarm carries only the type and the day it was meant for:
 * the text is chosen when it fires, from the current language, transfer rules and progress.
 */
object NoticeScheduler {
    const val ACTION_NOTICE_FIRE = "com.example.action.NOTICE_FIRE"
    const val EXTRA_NOTICE_TYPE = "notice_type"
    const val EXTRA_NOTICE_DAY = "notice_epoch_day"
    /** Prefix of the reminder id a notice tap hands to MainActivity: `notice:<type>:<AppDestination>`. */
    const val OPEN_PREFIX = "notice:"

    private val reconciliation = Mutex()

    fun openId(source: String, action: String) = "$OPEN_PREFIX$source:$action"

    /** The destination named by an [openId], or null for any other reminder id. */
    fun actionOf(reminderId: String?): String? =
        reminderId?.takeIf { it.startsWith(OPEN_PREFIX) }?.substringAfterLast(':')?.takeIf { it.isNotBlank() }

    private fun requestCode(type: NoticeType) = "notice_${type.id}".hashCode()

    internal fun pendingFor(context: Context, type: NoticeType, epochDay: Long?): PendingIntent = PendingIntent.getBroadcast(
        context, requestCode(type),
        Intent(context, ReminderReceiver::class.java).apply {
            action = ACTION_NOTICE_FIRE
            putExtra(EXTRA_NOTICE_TYPE, type.id)
            epochDay?.let { putExtra(EXTRA_NOTICE_DAY, it) }
        },
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    fun dateOf(calendar: Calendar) = CalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))

    fun triggerMillis(date: CalendarDate, hour: Int, minute: Int, zone: java.util.TimeZone = java.util.TimeZone.getDefault()): Long =
        Calendar.getInstance(zone).apply {
            clear()
            set(date.year, date.month - 1, date.day, hour, minute, 0)
        }.timeInMillis

    /** The next moment strictly after [now] at which [type] has something to say, or null within the horizon. */
    fun nextTrigger(type: NoticeType, pref: NoticePref, notices: Notices, context: NoticeContext, now: Calendar): Pair<CalendarDate, Long>? {
        var from = dateOf(now)
        repeat(3) {
            val date = NoticeSelector.nextOccurrence(type, from, notices, context) ?: return null
            val millis = triggerMillis(date, pref.hour, pref.minute, now.timeZone)
            if (millis > now.timeInMillis) return date to millis
            from = date.plusDays(1)
        }
        return null
    }

    fun cancel(context: Context, type: NoticeType) {
        val alarms = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pending = pendingFor(context, type, null)
        alarms.cancel(pending)
        pending.cancel()
    }

    /**
     * Brings the alarms in line with the stored choices: one alarm per enabled type at its next occurrence, none for
     * the rest. Returns how many are pending. Unreadable choices leave existing alarms untouched.
     */
    suspend fun scheduleEnabled(
        context: Context,
        store: NoticeStore = NoticeStore(context.applicationContext),
        now: Calendar = Calendar.getInstance(),
        catalog: NoticesCatalog = NoticesCatalog.default
    ): Int = reconciliation.withLock {
        val app = context.applicationContext
        val settings = store.settings.first()
        if (!settings.available) throw java.io.IOException("Notice preferences unavailable")
        val notices = catalog.load()
        val rules = if (settings.anyEnabled) runCatching { UserPreferencesStore(app).preferences.first().calendarRules }
            .getOrDefault(com.example.data.liturgy.CalendarRules()) else com.example.data.liturgy.CalendarRules()
        val noticeContext = NoticeContext(rules, settings.obligationDays)
        var scheduled = 0
        for (type in NoticeType.entries) {
            val pref = settings.pref(type)
            val next = if (pref.enabled && notices != null) nextTrigger(type, pref, notices, noticeContext, now) else null
            if (next == null) cancel(app, type)
            else {
                ReminderScheduler.setAlarm(app, next.second, pendingFor(app, type, next.first.toEpochDay()))
                scheduled++
            }
        }
        scheduled
    }

    fun buildNotification(context: Context, message: NoticeMessage): Notification {
        ReminderScheduler.ensureChannel(context)
        return NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(message.title)
            .setContentText(message.body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message.body))
            .setContentIntent(ReminderScheduler.contentIntent(context, openId(message.type.id, message.action)))
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    /**
     * A notice alarm went off: chain the next one first, then post today's text if there is one. An alarm delivered on
     * another day than it was meant for (a long doze, a clock change) is stale and only reschedules.
     */
    suspend fun onFire(context: Context, typeId: String?, epochDay: Long, now: Calendar = Calendar.getInstance()) {
        val app = context.applicationContext
        val type = NoticeType.byId(typeId) ?: return
        val settings = NoticeStore(app).settings.first()
        if (!settings.available || !settings.pref(type).enabled) return
        try { scheduleEnabled(app, now = (now.clone() as Calendar).apply { add(Calendar.MINUTE, 1) }) }
        catch (e: Exception) { AppLog.failure(LogEvent.REMINDER_SCHEDULE, e) }
        val today = dateOf(now)
        if (epochDay != today.toEpochDay()) return
        val notices = NoticesCatalog.default.load() ?: return
        val prefs = runCatching { UserPreferencesStore(app).preferences.first() }.getOrNull()
        val language = prefs?.language ?: AppLanguage.fromCode("")
        val progress = if (type == NoticeType.NOVENA_DAY || type == NoticeType.PLAN_DAY) NoticeProgress.load(app) else null
        val noticeContext = NoticeContext(prefs?.calendarRules ?: com.example.data.liturgy.CalendarRules(), settings.obligationDays,
            progress?.novenaNotice, progress?.planNotice)
        val message = NoticeSelector.select(type, today, notices, noticeContext, language) ?: return
        try { NotificationManagerCompat.from(app).notify(requestCode(type), buildNotification(app, message)) }
        catch (_: SecurityException) { /* Notifications can be declined independently. */ }
    }
}
