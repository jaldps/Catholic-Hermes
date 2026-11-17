package com.example.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.R
import com.example.data.liturgy.CalendarRules
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.notices.NoticeProgress
import com.example.data.notices.NoticeScheduler
import com.example.data.notices.PathProgress
import com.example.data.prefs.UserPreferencesStore
import com.example.data.reminders.ReminderScheduler
import com.example.data.reminders.ReminderStore
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.util.Calendar

/**
 * Home-screen widgets with classic [RemoteViews] (no extra dependency). They read the same engine and records as the
 * app, work offline, use the app's language and draw with day/night resources, so they follow the system theme.
 * They are redrawn just after midnight (an inexact alarm, held only while a widget is placed), on clock, zone and
 * locale changes, and whenever the app sees the underlying data change ([observe]).
 */
object AppWidgets {
    const val ACTION_MIDNIGHT = "com.example.action.WIDGETS_MIDNIGHT"

    private val providers = listOf(LiturgicalDayWidget::class.java, MyPathWidget::class.java)

    private fun ids(context: Context, provider: Class<*>): IntArray = runCatching {
        AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context, provider))
    }.getOrNull() ?: IntArray(0)

    fun hasAny(context: Context): Boolean = providers.any { ids(context, it).isNotEmpty() }

    /** Asks every placed widget to redraw; schedules (or drops) the midnight refresh accordingly. */
    fun refreshAll(context: Context) {
        val app = context.applicationContext
        var any = false
        for (provider in providers) {
            val ids = ids(app, provider)
            if (ids.isEmpty()) continue
            any = true
            app.sendBroadcast(Intent(app, provider).apply {
                action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            })
        }
        if (any) scheduleMidnight(app) else cancelMidnight(app)
    }

    private fun midnightIntent(context: Context) = PendingIntent.getBroadcast(context, ACTION_MIDNIGHT.hashCode(),
        Intent(context, WidgetRefreshReceiver::class.java).setAction(ACTION_MIDNIGHT),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

    fun nextMidnight(now: Calendar = Calendar.getInstance()): Long = (now.clone() as Calendar).apply {
        add(Calendar.DAY_OF_YEAR, 1)
        set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 30); set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    /** Inexact on purpose: a widget redraw does not need to wake the phone; it lands when the phone next wakes. */
    fun scheduleMidnight(context: Context) {
        val alarms = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarms.set(AlarmManager.RTC, nextMidnight(), midnightIntent(context))
    }

    fun cancelMidnight(context: Context) {
        val alarms = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarms.cancel(midnightIntent(context))
    }

    /** A tap opens the app on [destination] through MainActivity's reminder intent, like a notice. */
    fun openIntent(context: Context, source: String, destination: String): PendingIntent =
        ReminderScheduler.contentIntent(context, NoticeScheduler.openId(source, destination))

    data class Settings(val language: AppLanguage, val rules: CalendarRules)

    suspend fun settings(context: Context): Settings = runCatching {
        UserPreferencesStore(context.applicationContext).preferences.first().let { Settings(it.language, it.calendarRules) }
    }.getOrElse { Settings(AppLanguage.fromCode(""), CalendarRules()) }

    /**
     * While the app runs, redraws the widgets when what they show changes: habits kept, novena days, plan progress,
     * the free-text novena, the language or the transfer rules. Nothing happens when no widget is placed.
     */
    @OptIn(FlowPreview::class)
    fun observe(scope: CoroutineScope, context: Context, dao: SanctaFidesDao, store: UserPreferencesStore): Job {
        val app = context.applicationContext
        val signals = combine(
            dao.getAllCompletions().map { it.size to it.maxOfOrNull { c -> c.id } },
            dao.getAllNovenaProgress(),
            dao.novenaDaySnapshot(),
            store.planProgress,
            combine(store.preferences.map { it.language to it.calendarRules }, ReminderStore(app).novena) { a, b -> a to b }
        ) { a, b, c, d, e -> listOf(a, b, c, d, e) }
        return scope.launch(Dispatchers.IO) {
            signals.distinctUntilChanged().drop(1).debounce(1_000).collect { if (hasAny(app)) refreshAll(app) }
        }
    }

    internal fun runAsync(receiver: BroadcastReceiver, work: suspend () -> Unit) {
        val pending = receiver.goAsync()
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            try { withTimeout(8_000) { work() } }
            catch (cancelled: CancellationException) { throw cancelled }
            catch (_: Exception) { /* The next refresh redraws. */ }
            finally { pending?.finish() }
        }
    }
}

/** «Día litúrgico»: celebration, season with its colour, and the day's Gospel reference. Tap opens Today. */
class LiturgicalDayWidget : AppWidgetProvider() {
    companion object {
        fun views(context: Context, state: LiturgicalWidgetState): RemoteViews =
            RemoteViews(context.packageName, R.layout.widget_liturgical_day).apply {
                setTextViewText(R.id.widget_date, state.dateLabel)
                setTextViewText(R.id.widget_celebration, state.celebration)
                setTextViewText(R.id.widget_season, state.season)
                setInt(R.id.widget_color_bar, "setColorFilter", state.colorArgb)
                setInt(R.id.widget_color_dot, "setColorFilter", state.colorArgb)
                setContentDescription(R.id.widget_color_dot, state.colorName)
                if (state.gospel != null) {
                    setTextViewText(R.id.widget_gospel, state.gospel)
                    setViewVisibility(R.id.widget_gospel, android.view.View.VISIBLE)
                } else setViewVisibility(R.id.widget_gospel, android.view.View.GONE)
                setContentDescription(R.id.widget_root, state.description)
                setOnClickPendingIntent(R.id.widget_root, AppWidgets.openIntent(context, "widget_day", "HOME"))
            }
    }

    override fun onUpdate(context: Context, manager: AppWidgetManager, appWidgetIds: IntArray) {
        val app = context.applicationContext
        AppWidgets.runAsync(this) {
            val settings = AppWidgets.settings(app)
            val today = NoticeScheduler.dateOf(Calendar.getInstance())
            val views = views(app, WidgetContent.liturgical(today, settings.rules, settings.language))
            appWidgetIds.forEach { manager.updateAppWidget(it, views) }
            AppWidgets.scheduleMidnight(app)
        }
    }

    override fun onDisabled(context: Context) { if (!AppWidgets.hasAny(context)) AppWidgets.cancelMidnight(context) }
}

/** «Mi camino»: today's habits and the novena or plan in progress. Tap opens «Mi camino». */
class MyPathWidget : AppWidgetProvider() {
    companion object {
        fun views(context: Context, state: PathWidgetState): RemoteViews =
            RemoteViews(context.packageName, R.layout.widget_my_path).apply {
                setTextViewText(R.id.widget_path_title, state.title)
                setTextViewText(R.id.widget_habits, state.habits)
                fun line(id: Int, text: String?) {
                    if (text != null) { setTextViewText(id, text); setViewVisibility(id, android.view.View.VISIBLE) }
                    else setViewVisibility(id, android.view.View.GONE)
                }
                line(R.id.widget_novena, state.novena)
                line(R.id.widget_plan, state.plan)
                line(R.id.widget_path_empty, state.empty)
                setContentDescription(R.id.widget_root, state.description)
                setOnClickPendingIntent(R.id.widget_root, AppWidgets.openIntent(context, "widget_path", "MY_PATH"))
            }
    }

    override fun onUpdate(context: Context, manager: AppWidgetManager, appWidgetIds: IntArray) {
        val app = context.applicationContext
        AppWidgets.runAsync(this) {
            val settings = AppWidgets.settings(app)
            val progress: PathProgress = NoticeProgress.load(app)
            val views = views(app, WidgetContent.path(progress, settings.language))
            appWidgetIds.forEach { manager.updateAppWidget(it, views) }
            AppWidgets.scheduleMidnight(app)
        }
    }

    override fun onDisabled(context: Context) { if (!AppWidgets.hasAny(context)) AppWidgets.cancelMidnight(context) }
}

/** The midnight alarm (and any explicit refresh request) lands here. */
class WidgetRefreshReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == AppWidgets.ACTION_MIDNIGHT) AppWidgets.refreshAll(context)
    }
}
