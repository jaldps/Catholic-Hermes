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
import java.io.File

/**
 * The Catechism's navigable index (top level, unfolded to articles), the reader with its breadcrumb and the
 * plan day with its division subtitle, per identity, at phone width and at 320 dp / 130 %.
 * PNGs go to docs/tasks/T-038-shots.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class CatechismIndexScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }; narrator?.release(); repository?.close(); database?.close()
    }

    private fun render(language: AppLanguage, scale: Float, destination: AppDestination, beforeStart: suspend (UserPreferencesStore, Long) -> Unit = { _, _ -> }) {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val store = UserPreferencesStore(app)
        val repo = CatholicDataRepository(app).apply { setLanguage(language) }.also { repository = it }
        val today = repo.currentDate.value.toEpochDay()
        runBlocking { store.restartPlan("bible_year"); store.restartPlan("catechism_year"); beforeStart(store, today) }
        val prefs = runBlocking { store.preferences.first() }.copy(language = language, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT", fontSizeScale = scale)
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs, initialDestination = destination) }
        } }
    }

    private fun waitFor(tag: String) {
        compose.waitUntil(40_000) { compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() }
        compose.waitForIdle()
    }

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag, useUnmergedTree = true).performScrollTo().performClick()
        compose.waitForIdle()
    }

    private fun capture(name: String) {
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full))
            val small = Bitmap.createScaledBitmap(full, full.width / 2, full.height / 2, true)
            val file = File("../docs/tasks/T-038-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name.png")
            file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
            full.recycle(); small.recycle()
        }
    }

    private fun catechismFlow(prefix: String) {
        waitFor("ccc_index")
        compose.onNode(hasScrollToNodeAction()).performScrollToNode(hasTestTag("ccc_index"))
        capture("${prefix}index_top")
        tap("catechism_part_2"); waitFor("ccc_tree")
        capture("${prefix}part")
        tap("ccc_node_p2s2"); tap("ccc_node_p2s2c1")
        compose.onNode(hasScrollToNodeAction()).performScrollToNode(hasTestTag("ccc_node_p2s2"))
        capture("${prefix}index_articles")
        tap("ccc_node_p2s2c1a1"); waitFor("catechism_article_1213")
        waitFor("ccc_breadcrumb")
        capture("${prefix}reader")
        compose.onNodeWithTag("catechism_reader_scroll").performScrollToNode(hasTestTag("catechism_article_1250"))
        capture("${prefix}reader_scrolled")
    }

    /** Day 161 (§§ 1281–1288) crosses from Baptism into Confirmation: the longest subtitle. */
    private fun planFlow(prefix: String) {
        waitFor("plans_screen")
        // The plans load asynchronously, and at large text the card starts below the fold.
        compose.waitUntil(40_000) { runCatching { compose.onNodeWithTag("plans_screen").performScrollToNode(hasTestTag("plan_card_catechism_year")) }.isSuccess }
        tap("plan_card_catechism_year"); waitFor("plan_detail_screen")
        compose.waitUntil(40_000) { runCatching { compose.onNodeWithTag("plan_detail_screen").performScrollToNode(hasTestTag("plan_reading_0")) }.isSuccess }
        compose.waitForIdle()
        capture("${prefix}plan_day")
    }

    private val startAtDay161: suspend (UserPreferencesStore, Long) -> Unit = { s, today -> s.setPlanStart("catechism_year", today - 160) }

    @Test fun phone() {
        render(AppLanguage.ES, 1f, AppDestination.CATECHISM)
        catechismFlow("")
    }

    @Test fun phonePlan() {
        render(AppLanguage.ES, 1f, AppDestination.PLANS, startAtDay161)
        planFlow("")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeText() {
        render(AppLanguage.PT, 1.3f, AppDestination.CATECHISM)
        catechismFlow("320_130_")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeTextPlan() {
        render(AppLanguage.PT, 1.3f, AppDestination.PLANS, startAtDay161)
        planFlow("320_130_")
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
