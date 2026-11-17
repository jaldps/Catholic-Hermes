package com.example

import android.app.Application
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.CalendarRules
import com.example.data.liturgy.LiturgicalColor
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.notices.NoticeScheduler
import com.example.data.notices.PathProgress
import com.example.data.reminders.ReminderScheduler
import com.example.data.repository.ReadingPlan
import com.example.data.repository.ReadingPlanDay
import com.example.ui.screens.HabitsToday
import com.example.widget.LiturgicalDayWidget
import com.example.widget.MyPathWidget
import com.example.widget.WidgetContent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

/** The widgets' RemoteViews carry the celebration and its liturgical colour, and open the right screen. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class WidgetRemoteViewsTest {
    private val app = ApplicationProvider.getApplicationContext<Application>()

    private fun inflate(views: android.widget.RemoteViews): View = views.apply(app, FrameLayout(app))

    @Test fun liturgicalDayShowsCelebrationColourAndGospel() {
        // Christ the King 2026: Sunday 22 November, white (gold in the app's palette).
        val state = WidgetContent.liturgical(CalendarDate(2026, 11, 22), CalendarRules(), AppLanguage.ES)
        val view = inflate(LiturgicalDayWidget.views(app, state))
        val celebration = view.findViewById<TextView>(R.id.widget_celebration).text.toString()
        assertEquals("Jesucristo, Rey del Universo", celebration)
        assertTrue(view.findViewById<TextView>(R.id.widget_season).text.toString().contains("Blanco"))
        assertTrue(view.findViewById<TextView>(R.id.widget_gospel).text.toString().startsWith("Evangelio: "))
        val colour = LiturgicalColor.WHITE.hex.toInt()
        assertEquals(PorterDuffColorFilter(colour, PorterDuff.Mode.SRC_ATOP), view.findViewById<ImageView>(R.id.widget_color_bar).colorFilter)
        assertEquals("Blanco", view.findViewById<ImageView>(R.id.widget_color_dot).contentDescription)
        assertTrue(view.contentDescription.toString().contains("Jesucristo, Rey del Universo"))
        // A green weekday: the colour changes with the day.
        val green = WidgetContent.liturgical(CalendarDate(2026, 11, 18), CalendarRules(), AppLanguage.EN)
        val weekday = inflate(LiturgicalDayWidget.views(app, green))
        assertEquals(PorterDuffColorFilter(LiturgicalColor.GREEN.hex.toInt(), PorterDuff.Mode.SRC_ATOP),
            weekday.findViewById<ImageView>(R.id.widget_color_bar).colorFilter)
        assertTrue(weekday.findViewById<TextView>(R.id.widget_season).text.toString().contains("Green"))
        // Tap: Today.
        val tap = shadowOf(com.example.widget.AppWidgets.openIntent(app, "widget_day", "HOME")).savedIntent
        assertEquals(ReminderScheduler.ACTION_OPEN_REMINDER, tap.action)
        assertEquals("HOME", NoticeScheduler.actionOf(tap.getStringExtra(ReminderScheduler.EXTRA_ID)))
    }

    @Test fun myPathShowsHabitsNovenaAndPlanWithoutIntentions() {
        val novena = Novena("rafael", LocalizedText("Novena a San Rafael para solteros que buscan esposo o esposa", "St. Raphael Novena for Singles to Find a Spouse",
            "Novena a São Rafael para solteiros encontrarem um cônjuge"), LocalizedText("", "", ""), LocalizedText("", "", ""),
            LocalizedText("", "", ""), LocalizedText("", "", ""), LocalizedText("", "", ""), emptyList())
        val plan = ReadingPlan("bible_year", LocalizedText("La Biblia en un año", "The Bible in a year", "A Bíblia em um ano"),
            LocalizedText("", "", ""), listOf(ReadingPlanDay(1, listOf("gen 1"))))
        val progress = PathProgress(HabitsToday(3, 7, listOf("a", "b", "c", "d")), novena, 4, 9, plan, 12, false, null)
        val view = inflate(MyPathWidget.views(app, WidgetContent.path(progress, AppLanguage.ES)))
        assertEquals("Mi camino", view.findViewById<TextView>(R.id.widget_path_title).text.toString())
        assertEquals("Hábitos de hoy: 3 de 7", view.findViewById<TextView>(R.id.widget_habits).text.toString())
        val novenaLine = view.findViewById<TextView>(R.id.widget_novena).text.toString()
        assertEquals("Novena · San Rafael · día 4 de 9", novenaLine)
        assertFalse(novenaLine.contains("esposo"))
        assertEquals("Plan · La Biblia en un año · día 12", view.findViewById<TextView>(R.id.widget_plan).text.toString())
        assertEquals(View.GONE, view.findViewById<TextView>(R.id.widget_path_empty).visibility)
        // Nothing in progress: a calm line instead of empty rows.
        val idle = inflate(MyPathWidget.views(app, WidgetContent.path(PathProgress(HabitsToday(7, 7, emptyList()), null, null, null, null, null, false, null), AppLanguage.PT)))
        assertEquals("Hábitos de hoje: tudo feito", idle.findViewById<TextView>(R.id.widget_habits).text.toString())
        assertEquals(View.GONE, idle.findViewById<TextView>(R.id.widget_novena).visibility)
        assertEquals(View.VISIBLE, idle.findViewById<TextView>(R.id.widget_path_empty).visibility)
    }
}
