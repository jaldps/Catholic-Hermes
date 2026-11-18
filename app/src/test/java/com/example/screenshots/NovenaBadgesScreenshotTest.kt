package com.example.screenshots

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsProperties
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

/** Novena list with origin badges and the «Origen» menu open, per identity; PNGs go to docs/tasks/T-041-shots. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class NovenaBadgesScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
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
        val prefs = runBlocking { store.preferences.first() }.copy(language = language, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT", fontSizeScale = scale)
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs, initialDestination = AppDestination.PRAYERS) }
        } }
        compose.onNodeWithText("Novenas", useUnmergedTree = true).performClick()
        compose.waitUntil(40_000) { compose.onAllNodesWithTag("novena_filter_chips").fetchSemanticsNodes().isNotEmpty() }
        compose.waitUntil(40_000) { compose.onAllNodes(hasTestTagStartingWith("novena_badge_"), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() }
        // Cards that were composed before their bundled art finished decoding keep an empty painter
        // while offline; scrolling them out and back builds them again with the decoded art.
        repeat(5) { Thread.sleep(400); compose.waitForIdle() }
        compose.onNodeWithTag("novena_index").performScrollToIndex(24)
        compose.waitForIdle()
        compose.onNodeWithTag("novena_index").performScrollToNode(hasTestTag("novena_filter_chips"))
        compose.waitUntil(40_000) { compose.onAllNodes(hasTestTagStartingWith("novena_badge_"), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() }
    }

    private fun hasTestTagStartingWith(prefix: String) = SemanticsMatcher("tag starts with $prefix") {
        SemanticsProperties.TestTag in it.config && it.config[SemanticsProperties.TestTag].startsWith(prefix)
    }

    /** Every window, popups included: the open menu is a window of its own. */
    @Suppress("DiscouragedPrivateApi")
    private fun windowViews(): List<View> {
        val decor = compose.activity.window.decorView
        val global = Class.forName("android.view.WindowManagerGlobal")
        val instance = global.getMethod("getInstance").invoke(null)
        val names = global.getMethod("getViewRootNames").invoke(instance) as Array<*>
        val getRoot = global.getMethod("getRootView", String::class.java)
        return names.mapNotNull { getRoot.invoke(instance, it) as? View }.filter { it !== decor && it.width > 0 && it.height > 0 }
    }

    private fun capture(name: String, withPopups: Boolean = false) {
        // The card art decodes off the main thread; give it a moment so white titles never land on a blank card.
        repeat(6) { Thread.sleep(400); compose.waitForIdle() }
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(full)
            view.draw(canvas)
            if (withPopups) windowViews().forEach { popup ->
                val lp = popup.layoutParams as? WindowManager.LayoutParams
                canvas.save(); canvas.translate((lp?.x ?: 0).toFloat(), (lp?.y ?: 0).toFloat()); popup.draw(canvas); canvas.restore()
            }
            val small = Bitmap.createScaledBitmap(full, full.width * 6 / 10, full.height * 6 / 10, true)
            val file = File("../docs/tasks/T-041-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name.png")
            file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 90, it) }
            full.recycle(); small.recycle()
        }
    }

    private fun openOriginMenu() {
        compose.onNodeWithTag("novena_origin_picker", useUnmergedTree = true).performClick()
        compose.waitUntil(10_000) { compose.onAllNodesWithTag("novena_origin_modern").fetchSemanticsNodes().isNotEmpty() }
    }

    @Test fun listAndOriginMenu() {
        render(AppLanguage.ES, 1f)
        capture("list")
        openOriginMenu()
        capture("origin_menu", withPopups = true)
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeText() {
        render(AppLanguage.PT, 1.3f)
        capture("320_130_list")
        openOriginMenu()
        capture("320_130_origin_menu", withPopups = true)
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
