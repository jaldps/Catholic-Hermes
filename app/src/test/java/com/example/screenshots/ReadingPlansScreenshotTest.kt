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

/** Plan list and a plan three days behind (gentle catch-up), per identity; PNGs go to docs/tasks/PLANS-shots. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class ReadingPlansScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }; narrator?.release(); repository?.close(); database?.close()
    }

    private fun render(language: AppLanguage, scale: Float) {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val store = UserPreferencesStore(app)
        val repo = CatholicDataRepository(app).apply { setLanguage(language) }.also { repository = it }
        val today = repo.currentDate.value.toEpochDay()
        runBlocking {
            store.restartPlan("bible_year"); store.restartPlan("catechism_year")
            store.setPlanStart("bible_year", today - 3); store.setPlanDayDone("bible_year", 2, true)
            store.setPlanStart("catechism_year", today - 33); (1..30).forEach { store.setPlanDayDone("catechism_year", it, true) }
        }
        val prefs = runBlocking { store.preferences.first() }.copy(language = language, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT", fontSizeScale = scale)
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs, initialDestination = AppDestination.PLANS) }
        } }
        compose.waitUntil(40_000) { compose.onAllNodesWithTag("plan_card_bible_year").fetchSemanticsNodes().isNotEmpty() }
    }

    private fun capture(name: String) {
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full))
            val small = Bitmap.createScaledBitmap(full, full.width * 6 / 10, full.height * 6 / 10, true)
            val file = File("../docs/tasks/PLANS-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name.png")
            file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
            full.recycle(); small.recycle()
        }
    }

    private fun openBibleDetail() {
        compose.onNodeWithTag("plan_card_bible_year").performClick()
        compose.waitUntil(40_000) { compose.onAllNodesWithTag("plan_catch_up").fetchSemanticsNodes().isNotEmpty() }
    }

    @Test fun listAndCatchUp() {
        render(AppLanguage.ES, 1f)
        capture("list")
        openBibleDetail()
        capture("detail")
        compose.onNodeWithTag("plan_detail_screen").performScrollToNode(hasTestTag("plan_restart"))
        capture("detail_bottom")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeText() {
        render(AppLanguage.PT, 1.3f)
        capture("320_130_list")
        openBibleDetail()
        capture("320_130_detail")
        compose.onNodeWithTag("plan_detail_screen").performScrollToNode(hasTestTag("plan_next_day"))
        capture("320_130_detail_nav")
        compose.onNodeWithTag("plan_detail_screen").performScrollToNode(hasTestTag("plan_restart"))
        capture("320_130_detail_bottom")
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
