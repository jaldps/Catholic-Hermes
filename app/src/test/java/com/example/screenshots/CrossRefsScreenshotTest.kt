package com.example.screenshots

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.AppDestination
import com.example.ui.MainApp
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowDialog
import java.io.File

/**
 * Cross-references of a lesson and of a plan day, per identity: the quiet action in the lesson and the
 * sheet it opens. PNGs go to docs/tasks/T-040-shots.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class CrossRefsScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }; narrator?.release(); repository?.close(); database?.close()
    }

    private fun render(language: AppLanguage, scale: Float, destination: AppDestination) {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val store = UserPreferencesStore(app)
        val repo = CatholicDataRepository(app).apply { setLanguage(language) }.also { repository = it }
        runBlocking { store.restartPlan("bible_year"); store.setPlanStart("bible_year", repo.currentDate.value.toEpochDay()) }
        val prefs = runBlocking { store.preferences.first() }.copy(language = language, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT", fontSizeScale = scale)
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs, initialDestination = destination) }
        } }
    }

    private fun waitFor(tag: String) = compose.waitUntil(40_000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty() }

    private fun save(full: Bitmap, name: String) {
        val small = Bitmap.createScaledBitmap(full, full.width * 6 / 10, full.height * 6 / 10, true)
        val file = File("../docs/tasks/T-040-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name.png")
        file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
        small.recycle()
    }

    private fun capture(name: String) {
        compose.waitForIdle()
        Thread.sleep(400) // images decode asynchronously
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full)); save(full, name); full.recycle()
        }
    }

    /** Sheets live in their own Dialog window; draw its decor view. */
    private fun captureSheet(name: String) {
        Thread.sleep(400); compose.waitForIdle()
        compose.runOnIdle {
            val view = requireNotNull(ShadowDialog.getLatestDialog()?.window).decorView
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full)); save(full, name); full.recycle()
        }
    }

    /** Brings [tag] a little below the top of [list]. */
    private fun bring(list: String, tag: String) {
        compose.onNodeWithTag(list).performScrollToNode(hasTestTag(tag))
        compose.waitForIdle()
    }

    private fun openLesson(study: String) {
        waitFor("nav_bible")
        compose.onNodeWithText(study, useUnmergedTree = true).performClick()
        waitFor("study_track_picker")
        compose.onNodeWithTag("study_track_picker").performScrollToNode(hasTestTag("track_row_track_psalms_prayer"))
        compose.onNodeWithTag("track_row_track_psalms_prayer").performClick()
        compose.onNodeWithTag("study_overview_page").performScrollToNode(hasTestTag("lesson_row_psalms_3_lament"))
        compose.onNodeWithTag("lesson_row_psalms_3_lament").performClick()
        waitFor("study_lesson_page")
    }

    private fun lessonShots(prefix: String, study: String) {
        openLesson(study)
        bring("study_lesson_page", "lesson_xref_psalms_3_lament_1")
        compose.onNodeWithTag("lesson_xref_psalms_3_lament_1").performScrollTo()
        capture("${prefix}lesson")
        compose.onNodeWithTag("lesson_xref_psalms_3_lament_0").performClick()
        waitFor("passage_xref_0_0")
        captureSheet("${prefix}lesson_sheet")
    }

    @Test fun lessonAndPlanDay() {
        render(AppLanguage.ES, 1f, AppDestination.BIBLE)
        lessonShots("", "Estudio")
    }

    @Test fun planDay() {
        render(AppLanguage.EN, 1f, AppDestination.PLANS)
        waitFor("plan_card_bible_year")
        compose.onNodeWithTag("plan_card_bible_year").performClick()
        waitFor("plan_reading_0")
        bring("plan_detail_screen", "plan_xref_3")
        capture("plan")
        compose.onNodeWithTag("plan_xref_0").performClick()
        waitFor("passage_xref_0_0")
        captureSheet("plan_sheet")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeText() {
        render(AppLanguage.PT, 1.3f, AppDestination.BIBLE)
        lessonShots("320_130_", "Estudo")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeTextPlan() {
        render(AppLanguage.EN, 1.3f, AppDestination.PLANS)
        waitFor("plan_card_bible_year")
        compose.onNodeWithTag("plan_card_bible_year").performClick()
        waitFor("plan_reading_0")
        bring("plan_detail_screen", "plan_xref_3")
        capture("320_130_plan")
        compose.onNodeWithTag("plan_xref_0").performClick()
        waitFor("passage_xref_0_0")
        captureSheet("320_130_plan_sheet")
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
