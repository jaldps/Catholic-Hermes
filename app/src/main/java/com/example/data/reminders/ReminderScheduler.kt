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
import java.util.Calendar

const val REMINDER_CHANNEL_ID = "devotion_reminders"

object ReminderScheduler {
    const val ACTION_FIRE = "com.example.action.REMINDER_FIRE"
    const val EXTRA_ID = "reminder_id"
    const val EXTRA_TITLE = "reminder_title"
    const val EXTRA_BODY = "reminder_body"
    const val EXTRA_LANG = "reminder_lang"

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

    /** Reschedules every enabled reminder from the store (boot, language change…). */
    fun rescheduleAll(context: Context, language: String) {
        val app = context.applicationContext
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            val prefs = ReminderStore(app).reminders.first()
            for ((id, pref) in prefs) {
                val reminder = ReminderCatalog.byId(id) ?: continue
                if (pref.enabled) schedule(app, reminder, pref.hour, pref.minute, language)
            }
        }
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

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != ReminderScheduler.ACTION_FIRE) return
        val id = intent.getStringExtra(ReminderScheduler.EXTRA_ID) ?: return
        val title = intent.getStringExtra(ReminderScheduler.EXTRA_TITLE) ?: "Oración"
        val body = intent.getStringExtra(ReminderScheduler.EXTRA_BODY) ?: ""
        ReminderScheduler.ensureChannel(context)
        val notification = NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        try {
            NotificationManagerCompat.from(context).notify(id.hashCode(), notification)
        } catch (_: SecurityException) {
            // POST_NOTIFICATIONS not granted — the exact alarm still fired; nothing to show.
        }
        // Chain the next daily occurrence.
        val reminder = ReminderCatalog.byId(id) ?: return
        val language = intent.getStringExtra(ReminderScheduler.EXTRA_LANG) ?: "es"
        val store = ReminderStore(context.applicationContext)
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            val pref = store.reminders.first()[id] ?: return@launch
            if (pref.enabled) {
                ReminderScheduler.schedule(
                    context.applicationContext, reminder, pref.hour, pref.minute, language,
                )
            }
        }
    }
}

class ReminderBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != Intent.ACTION_BOOT_COMPLETED) return
        val app = context.applicationContext
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            val language = runCatching {
                com.example.data.prefs.UserPreferencesStore(app).preferences.first().language.code
            }.getOrNull() ?: "es"
            ReminderScheduler.rescheduleAll(app, language)
        }
    }
}
