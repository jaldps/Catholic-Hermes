package com.example.screenshots

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.CalendarRules
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.notices.NoticeStore
import com.example.data.notices.NoticeType
import com.example.data.notices.PathProgress
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ReadingPlan
import com.example.data.repository.ReadingPlanDay
import com.example.ui.AppDestination
import com.example.ui.MainApp
import com.example.ui.screens.HabitsToday
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import com.example.widget.LiturgicalDayWidget
import com.example.widget.MyPathWidget
import com.example.widget.WidgetContent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * T-032: the Reminders screen's notice groups per identity (and at 320 dp / 130 %), and both home-screen widgets in
 * light and dark, rendered from their RemoteViews. PNGs go to docs/tasks/T-032-shots.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class NoticesScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null
    private val app get() = ApplicationProvider.getApplicationContext<Application>()
    private val out = File("../docs/tasks/T-032-shots")

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }; narrator?.release(); repository?.close(); database?.close()
    }

    private fun render(language: AppLanguage, scale: Float) {
        val store = UserPreferencesStore(app)
        val notices = NoticeStore(app)
        runBlocking {
            NoticeType.entries.forEach { notices.setEnabled(it, false) }
            // A plausible choice: the season, both feast notices, Sunday Mass and the evening Rosary.
            listOf(NoticeType.SEASON_START, NoticeType.FEAST_EVE, NoticeType.FEAST_DAY, NoticeType.SUNDAY_MASS, NoticeType.ROSARY_EVENING)
                .forEach { notices.setEnabled(it, true) }
            notices.setObligationDays(emptySet())
        }
        val repo = CatholicDataRepository(app).apply { setLanguage(language) }.also { repository = it }
        val prefs = runBlocking { store.preferences.first() }.copy(language = language, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT", fontSizeScale = scale)
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs, initialDestination = AppDestination.REMINDERS) }
        } }
        compose.waitUntil(40_000) { compose.onAllNodesWithTag("reminders_screen").fetchSemanticsNodes().isNotEmpty() }
        // The notice groups sit below the fold of a lazy list: compose them by scrolling there.
        compose.onNodeWithTag("reminders_screen").performScrollToNode(hasTestTag("notice_settings"))
        compose.waitForIdle()
    }

    private fun save(bitmap: Bitmap, name: String, factor: Int = 5) {
        val small = Bitmap.createScaledBitmap(bitmap, bitmap.width * factor / 10, bitmap.height * factor / 10, true)
        val file = File(out, "$name.png")
        file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
        small.recycle()
    }

    private fun capture(name: String) {
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full))
            save(full, "${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name")
            full.recycle()
        }
    }

    /** Brings [tag] near the top of the list, below the inline title. */
    private fun bringToTop(tag: String) {
        compose.onNodeWithTag("reminders_screen").performScrollToNode(hasTestTag(tag))
        compose.waitForIdle()
        val top = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().boundsInRoot.top
        compose.onNodeWithTag("reminders_screen").performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, top - 260f) }
        compose.waitForIdle()
    }

    @Test fun remindersNoticeGroups() {
        render(AppLanguage.ES, 1f)
        bringToTop("notice_group_season")
        capture("notices_season_feasts")
        bringToTop("notice_group_reminders")
        capture("notices_reminders")
        if (identity == DesignLanguage.APPLE) {
            bringToTop("notice_group_feasts")
            compose.onNodeWithTag("notice_row_feast_eve", useUnmergedTree = true).performClick()
            compose.waitUntil(20_000) { compose.onAllNodesWithTag("notice_preview", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() }
            Thread.sleep(400); compose.waitForIdle()
            captureDialog("notice_sheet")
        }
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeText() {
        render(AppLanguage.PT, 1.3f)
        bringToTop("notice_group_season")
        capture("320_130_notices_season")
        bringToTop("notice_group_reminders")
        capture("320_130_notices_reminders")
        if (identity == DesignLanguage.CLASSIC) {
            compose.onNodeWithTag("notice_switch_holy_day_of_obligation", useUnmergedTree = true).performClick()
            compose.waitUntil(20_000) { compose.onAllNodesWithTag("obligation_note").fetchSemanticsNodes().isNotEmpty() }
            Thread.sleep(400); compose.waitForIdle()
            captureDialog("320_130_obligation_sheet")
            // Let the switch's write finish (it confirms on the main thread) before the rule tears down.
            compose.waitUntil(15_000) { runBlocking { NoticeStore(app).settings.first() }.pref(NoticeType.HOLY_DAY_OF_OBLIGATION).enabled }
            repeat(5) { Thread.sleep(100); compose.waitForIdle() }
        }
    }

    /** Sheets live in their own Dialog window; draw its decor view. */
    private fun captureDialog(name: String) {
        compose.runOnIdle {
            val view = requireNotNull(org.robolectric.shadows.ShadowDialog.getLatestDialog()?.window).decorView
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full))
            save(full, "${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name")
            full.recycle()
        }
    }

    /** Both widgets as a launcher would draw them, over a plain wallpaper, in the system's light and dark modes. */
    @Test fun widgets() {
        Assume.assumeTrue(identity == DesignLanguage.APPLE)
        for (night in listOf(false, true)) for (language in listOf(AppLanguage.ES, AppLanguage.EN)) {
            val config = Configuration(app.resources.configuration).apply {
                uiMode = (uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or
                    (if (night) Configuration.UI_MODE_NIGHT_YES else Configuration.UI_MODE_NIGHT_NO)
            }
            val context: Context = app.createConfigurationContext(config)
            val density = context.resources.displayMetrics.density
            fun px(dp: Int) = (dp * density).toInt()
            val joseph = Novena("joseph", LocalizedText("Novena a San José", "St. Joseph Novena", "Novena a São José"),
                LocalizedText("", "", ""), LocalizedText("", "", ""), LocalizedText("", "", ""), LocalizedText("", "", ""),
                LocalizedText("", "", ""), emptyList())
            val plan = ReadingPlan("bible_year", LocalizedText("La Biblia en un año", "The Bible in a year", "A Bíblia em um ano"),
                LocalizedText("", "", ""), listOf(ReadingPlanDay(1, listOf("gen 1"))))
            val day = LiturgicalDayWidget.views(context, WidgetContent.liturgical(CalendarDate(2026, 11, 22), CalendarRules(), language)).apply(context, FrameLayout(context))
            val weekday = LiturgicalDayWidget.views(context, WidgetContent.liturgical(CalendarDate(2026, 11, 18), CalendarRules(), language)).apply(context, FrameLayout(context))
            val path = MyPathWidget.views(context, WidgetContent.path(PathProgress(HabitsToday(3, 7, listOf("a", "b", "c", "d")), joseph, 4, 9,
                plan, 12, false, null), language)).apply(context, FrameLayout(context))
            val empty = MyPathWidget.views(context, WidgetContent.path(PathProgress(HabitsToday(0, 7, List(7) { "x" }), null, null, null,
                null, null, false, null), language)).apply(context, FrameLayout(context))
            val board = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(if (night) Color.rgb(16, 20, 28) else Color.rgb(196, 210, 226))
                setPadding(px(16), px(20), px(16), px(20))
                listOf(day to 150, weekday to 150, path to 140, empty to 120).forEach { (view, height) ->
                    addView(view, LinearLayout.LayoutParams(px(340), px(height)).apply { bottomMargin = px(14) })
                }
            }
            board.measure(View.MeasureSpec.makeMeasureSpec(px(372), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            board.layout(0, 0, board.measuredWidth, board.measuredHeight)
            val bitmap = Bitmap.createBitmap(board.measuredWidth, board.measuredHeight, Bitmap.Config.ARGB_8888)
            board.draw(Canvas(bitmap))
            save(bitmap, "widgets_${if (night) "dark" else "light"}_${language.code}", factor = 6)
            bitmap.recycle()
        }
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
