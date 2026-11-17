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
import com.example.data.search.SearchIndexHolder
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

/** The search with a query that fills several groups, per identity; PNGs go to docs/tasks/T-030-shots. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class SearchScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }; narrator?.release(); repository?.close(); database?.close()
        SearchIndexHolder.reset()
    }

    private fun render(language: AppLanguage, scale: Float, query: String, ready: String = "search_group_bible") {
        SearchIndexHolder.reset()
        val app = ApplicationProvider.getApplicationContext<Application>()
        val store = UserPreferencesStore(app)
        val repo = CatholicDataRepository(app).apply { setLanguage(language) }.also { repository = it }
        val prefs = runBlocking { store.preferences.first() }.copy(language = language, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT", fontSizeScale = scale)
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs, initialDestination = AppDestination.SEARCH) }
        } }
        compose.waitUntil(40_000) { compose.onAllNodesWithTag("search_field").fetchSemanticsNodes().isNotEmpty() }
        capture("empty")
        compose.onNodeWithTag("search_field").performTextReplacement(query)
        waitForGroup(ready)   // the Bible group arrives last
        compose.onNodeWithTag("search_screen").performScrollToIndex(0)
    }

    /** Results sit in a lazy list: scroll until the node is composed. */
    private fun waitForGroup(tag: String) = compose.waitUntil(60_000) {
        runCatching { compose.onNodeWithTag("search_screen").performScrollToNode(hasTestTag(tag)) }
        compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty()
    }

    private fun capture(name: String) {
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full))
            val small = Bitmap.createScaledBitmap(full, full.width * 6 / 10, full.height * 6 / 10, true)
            val file = File("../docs/tasks/T-030-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name.png")
            file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
            full.recycle(); small.recycle()
        }
    }

    @Test fun groupsForAQuery() {
        render(AppLanguage.ES, 1f, "María")
        capture("maria")
        compose.onNodeWithTag("search_screen").performScrollToNode(hasTestTag("search_group_catechism"))
        capture("maria_scrolled")
        compose.onNodeWithTag("search_screen").performScrollToNode(hasTestTag("search_more_catechism"))
        compose.onNodeWithTag("search_more_catechism").performClick()
        capture("maria_expanded")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeTextPortuguese() {
        render(AppLanguage.PT, 1.3f, "Maria")
        capture("320_130_maria")
        compose.onNodeWithTag("search_screen").performScrollToNode(hasTestTag("search_group_bible"))
        capture("320_130_maria_bible")
    }

    @Test fun englishReferenceAndEmptyState() {
        render(AppLanguage.EN, 1f, "John 3:16", ready = "search_group_reference")
        capture("en_reference")
        compose.onNodeWithTag("search_field").performTextReplacement("zzzxqv")
        // Only reads while the list swaps its items: no scrolling during the change.
        compose.waitUntil(60_000) { compose.onAllNodesWithTag("search_empty").fetchSemanticsNodes().isNotEmpty() }
        capture("en_empty")
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
