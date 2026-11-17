package com.example.screenshots

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
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
import org.robolectric.shadows.ShadowDialog
import java.io.File

/** Home «Para ti hoy», the welcome dialog and Settings → «Tu camino» per identity; PNGs go to docs/tasks/T-037-shots. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class WelcomePersonalizationScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }; narrator?.release(); repository?.close(); database?.close()
    }

    private fun render(language: AppLanguage, scale: Float, profile: Int, minutes: Int, welcomed: Boolean = true) {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val journey = app.getSharedPreferences("beginner_journey", 0).edit().clear()
        if (welcomed) journey.putBoolean("welcomed", true).putInt("profile", profile).putInt("minutes", minutes)
        check(journey.commit())
        val store = UserPreferencesStore(app)
        val repo = CatholicDataRepository(app).apply { setLanguage(language) }.also { repository = it }
        val prefs = runBlocking { store.preferences.first() }.copy(language = language, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT", fontSizeScale = scale)
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs) }
        } }
        compose.waitUntil(40_000) { compose.onAllNodesWithTag("home_screen").fetchSemanticsNodes().isNotEmpty() }
    }

    private fun waitForTag(tag: String) = compose.waitUntil(40_000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty() }

    private fun scrollHomeTo(tag: String) =
        compose.waitUntil(40_000) { runCatching { compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag(tag)) }.isSuccess }

    /** The window under any dialog is drawn first, so a sheet appears over the screen it opened from. */
    private fun capture(name: String) {
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(full)
            view.draw(canvas)
            ShadowDialog.getLatestDialog()?.window?.decorView?.takeIf { it.width > 0 }?.draw(canvas)
            val small = Bitmap.createScaledBitmap(full, full.width * 6 / 10, full.height * 6 / 10, true)
            val file = File("../docs/tasks/T-037-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name.png")
            file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
            full.recycle(); small.recycle()
        }
    }

    private fun homeBlock(name: String) {
        scrollHomeTo("for_you_all")
        compose.onNodeWithTag("home_for_you").assertIsDisplayed()
        capture("${name}_home")
    }

    private fun settingsSheet(name: String) {
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("settings_welcome_profile"))
        compose.onNodeWithTag("settings_welcome_profile").performScrollTo()
        // The row can sit behind the floating dock after the scroll; lift it into the clear for the picture.
        compose.onNodeWithTag("more_screen").performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, 260f) }
        capture("${name}_settings")
        compose.onNodeWithTag("settings_welcome_profile").performScrollTo().performClick()
        waitForTag("welcome_profile_sheet")
        capture("${name}_sheet")
    }

    private fun welcomeDialog(name: String, profile: Int) {
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("today_more_toggle"))
        compose.onNodeWithTag("today_more_toggle").performClick()
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("welcome_personalize"))
        compose.onNodeWithTag("welcome_personalize").performClick()
        waitForTag("welcome_dialog")
        compose.onNodeWithTag("welcome_profile_$profile").performClick()
        waitForTag("welcome_profile_line")
        capture("${name}_welcome")
    }

    @Test fun homeAndSettings() {
        render(AppLanguage.ES, 1f, profile = 1, minutes = 10)
        homeBlock("es")
        settingsSheet("es")
    }

    @Test fun welcomeDialog() {
        render(AppLanguage.ES, 1f, profile = 0, minutes = 5, welcomed = false)
        welcomeDialog("es", 1)
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeText() {
        render(AppLanguage.PT, 1.3f, profile = 2, minutes = 5)
        homeBlock("320_130")
        settingsSheet("320_130")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeTextWelcome() {
        render(AppLanguage.PT, 1.3f, profile = 0, minutes = 5, welcomed = false)
        welcomeDialog("320_130", 2)
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
