package com.example.data.reminders

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.CancellationException
import java.util.Calendar

const val REMINDER_CHANNEL_ID = "devotion_reminders"

object ReminderScheduler {
    const val ACTION_OPEN_REMINDER = "com.example.action.OPEN_REMINDER"
    const val ACTION_FIRE = "com.example.action.REMINDER_FIRE"
    const val EXTRA_ID = "reminder_id"
    const val EXTRA_TITLE = "reminder_title"
    const val EXTRA_BODY = "reminder_body"
    const val EXTRA_LANG = "reminder_lang"

    /** Explicit immutable activity intent; the ID is resolved against ReminderCatalog in the UI. */
    fun contentIntent(context: Context, reminderId: String? = null): PendingIntent = PendingIntent.getActivity(
        context,
        reminderId?.hashCode() ?: 0,
        Intent(context, com.example.MainActivity::class.java).apply {
            action = ACTION_OPEN_REMINDER
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            reminderId?.let { putExtra(EXTRA_ID, it) }
        },
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
    )

    private fun intentFor(context: Context, reminder: DevotionReminder, language: String): Intent =
        Intent(context, ReminderReceiver::class.java).apply {
            action = ACTION_FIRE
            putExtra(EXTRA_ID, reminder.id)
            putExtra(EXTRA_TITLE, reminder.title(language))
            putExtra(EXTRA_BODY, reminder.body(language))
            putExtra(EXTRA_LANG, language)
        }

    private fun pendingFor(context: Context, reminder: DevotionReminder, language: String): PendingIntent =
        PendingIntent.getBroadcast(
            context,
            reminder.id.hashCode(),
            intentFor(context, reminder, language),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

    /** Next local trigger strictly after [now] for [hour]:[minute]. */
    fun nextTriggerMillis(hour: Int, minute: Int, now: Calendar = Calendar.getInstance()): Long {
        require(hour in 0..23 && minute in 0..59) { "Invalid reminder time" }
        val c = (now.clone() as Calendar).apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (timeInMillis <= now.timeInMillis) add(Calendar.DAY_OF_YEAR, 1)
        }
        return c.timeInMillis
    }

    fun schedule(context: Context, reminder: DevotionReminder, hour: Int, minute: Int, language: String) {
        val alarms = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val trigger = nextTriggerMillis(hour, minute)
        val pi = pendingFor(context, reminder, language)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarms.canScheduleExactAlarms()) {
                alarms.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger, pi)
            } else {
                alarms.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger, pi)
            }
        } catch (_: SecurityException) {
            alarms.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger, pi)
        }
    }

    fun cancel(context: Context, reminder: DevotionReminder, language: String) {
        val alarms = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarms.cancel(pendingFor(context, reminder, language))
    }

    /**
     * Reschedules every enabled reminder from the store (app start, boot,
     * language change…). Called at app start too, so the reminders that are
     * ON by default (midday Angelus, Chaplet) really ring on a fresh install
     * instead of only after a reboot or a manual toggle. Idempotent: each
     * reminder owns one PendingIntent, which FLAG_UPDATE_CURRENT replaces.
     */
    fun rescheduleAll(context: Context, language: String) {
        val app = context.applicationContext
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            runCatching { scheduleEnabled(app, language) }
        }
    }

    /** Schedules every enabled reminder now; returns how many were scheduled. */
    suspend fun scheduleEnabled(context: Context, language: String, store: ReminderStore = ReminderStore(context.applicationContext)): Int {
        val app = context.applicationContext
        var scheduled = 0
        for ((id, pref) in store.reminders.first()) {
            val reminder = ReminderCatalog.byId(id) ?: continue
            if (pref.enabled) {
                schedule(app, reminder, pref.hour, pref.minute, language)
                scheduled++
            } else {
                cancel(app, reminder, language)
            }
        }
        return scheduled
    }

    fun ensureChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (manager.getNotificationChannel(REMINDER_CHANNEL_ID) == null) {
                manager.createNotificationChannel(
                    NotificationChannel(
                        REMINDER_CHANNEL_ID,
                        "Recordatorios de oración",
                        NotificationManager.IMPORTANCE_DEFAULT,
                    ).apply { description = "Ángelus, coronilla, oraciones y lecturas" },
                )
            }
        }
    }

    /**
     * Fires an immediate notification on the reminder channel so the user can
     * verify the channel and POST_NOTIFICATIONS on device without waiting for
     * the next exact alarm. Returns false when the system refuses the post
     * (permission not granted on Android 13+).
     */
    fun sendTestNotification(context: Context, title: String, body: String): Boolean {
        ensureChannel(context)
        val notification = NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setContentIntent(contentIntent(context))
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        return try {
            NotificationManagerCompat.from(context).notify("test".hashCode(), notification)
            true
        } catch (_: SecurityException) {
            false
        }
    }
}

/** Broadcast work stays within Android's receiver window, including DataStore reads. */
private fun BroadcastReceiver.finishAsync(work: suspend () -> Unit) {
    val pending = goAsync()
    CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
        try {
            withTimeout(8_000) { work() }
        } catch (cancelled: CancellationException) {
            throw cancelled
        } catch (_: Exception) {
            // A failed alarm is retried by the next app start/system reschedule.
        } finally {
            pending.finish()
        }
    }
}

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != ReminderScheduler.ACTION_FIRE) return
        val id = intent.getStringExtra(ReminderScheduler.EXTRA_ID) ?: return
        val reminder = ReminderCatalog.byId(id) ?: return
        val app = context.applicationContext
        val language = intent.getStringExtra(ReminderScheduler.EXTRA_LANG) ?: "es"
        finishAsync {
            val pref = ReminderStore(app).reminders.first()[id] ?: return@finishAsync
            if (!pref.enabled) return@finishAsync
            // Chain first, so a refused notification cannot interrupt the next occurrence.
            ReminderScheduler.schedule(app, reminder, pref.hour, pref.minute, language)
            ReminderScheduler.ensureChannel(app)
            val notification = NotificationCompat.Builder(app, REMINDER_CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(reminder.title(language))
                .setContentText(reminder.body(language))
                .setStyle(NotificationCompat.BigTextStyle().bigText(reminder.body(language)))
                .setContentIntent(ReminderScheduler.contentIntent(app, id))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            try {
                NotificationManagerCompat.from(app).notify(id.hashCode(), notification)
            } catch (_: SecurityException) {
                // Notification permission can be declined independently of reminders.
            }
        }
    }
}

class ReminderBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action !in setOf(Intent.ACTION_BOOT_COMPLETED, Intent.ACTION_TIME_CHANGED,
                Intent.ACTION_TIMEZONE_CHANGED, Intent.ACTION_MY_PACKAGE_REPLACED)) return
        val app = context.applicationContext
        finishAsync {
            val language = com.example.data.prefs.UserPreferencesStore(app).preferences.first().language.code
            // Await the actual scheduling; a nested fire-and-forget job would outlive goAsync.
            ReminderScheduler.scheduleEnabled(app, language)
        }
    }
}
