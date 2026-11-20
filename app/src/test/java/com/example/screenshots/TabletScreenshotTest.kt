package com.example.screenshots

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.snapshots.Snapshot
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
 * T-043: Hoy, Orar, a Bible chapter and the two-pane Santos on a portrait tablet
 * (840 x 1200 dp) and a landscape one (1280 x 800 dp), per identity. PNGs go to docs/tasks/T-043-shots.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w840dp-h1200dp-xhdpi")
class TabletScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }; narrator?.release(); repository?.close(); database?.close()
    }

    private fun render() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val store = UserPreferencesStore(app)
        val repo = CatholicDataRepository(app).apply { setLanguage(AppLanguage.ES) }.also { repository = it }
        val prefs = runBlocking { store.preferences.first() }.copy(language = AppLanguage.ES, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT")
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs) }
        } }
        compose.waitUntil(40_000) { compose.onAllNodesWithTag("home_screen").fetchSemanticsNodes().isNotEmpty() }
    }

    private fun await(condition: () -> Boolean) = compose.waitUntil(40_000) {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        condition()
    }

    /** Pictures decode off the main thread: let them land before the frame is captured. */
    private fun settle() {
        repeat(10) {
            Thread.sleep(120)
            compose.runOnUiThread { Snapshot.sendApplyNotifications() }
            compose.waitForIdle()
        }
    }

    private fun capture(orientation: String, name: String) {
        settle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full))
            val small = Bitmap.createScaledBitmap(full, full.width * 4 / 10, full.height * 4 / 10, true)
            val file = File("../docs/tasks/T-043-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_${orientation}_$name.png")
            file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
            full.recycle(); small.recycle()
        }
    }

    private fun tour(orientation: String) {
        render()
        capture(orientation, "1_hoy")
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.onNodeWithTag("prayers_and_novenas_screen").assertIsDisplayed()
        capture(orientation, "2_orar")
        compose.onNodeWithTag("nav_bible").performClick()
        compose.onNodeWithTag("bible_continue_reading").performClick()
        await { compose.onAllNodesWithTag("bible_reader").fetchSemanticsNodes().isNotEmpty() }
        capture(orientation, "3_biblia")
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_saints"))
        compose.onNodeWithTag("nav_saints").performClick()
        compose.onNodeWithTag("saints_screen").assertIsDisplayed()
        capture(orientation, "4_santos")
    }

    @Test fun portrait() = tour("portrait")

    @Test @Config(qualifiers = "w1280dp-h800dp-xhdpi") fun landscape() = tour("landscape")

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
