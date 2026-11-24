package com.example

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ChapletCatalog
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Orar → «Otras coronillas» inside the whole app, in every identity and both modes, on Android 16. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [36], qualifiers = "w411dp-h891dp-xhdpi")
class OtherChapletsEntryMatrixTest(private val identity: String, private val dark: String, private val lang: AppLanguage, private val scale: Float, private val accessible: Boolean) {
    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-{1}-{2}-{3}-{4}")
        fun params() = listOf("APPLE", "CLASSIC", "GLASS", "SANCTUARY").flatMap { id ->
            listOf(arrayOf<Any>(id, "LIGHT", AppLanguage.ES, 1.3f, false), arrayOf<Any>(id, "DARK", AppLanguage.PT, 1.0f, true))
        }
    }

    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var voice: LiturgicalTtsManager? = null
    private var db: com.example.data.local.SanctaFidesDatabase? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent { } }
        compose.waitForIdle()
        voice?.release(); db?.close()
    }

    @Test fun orarRowOpensTheCatalogueAndAChaplet() {
        ChapletCatalog.override = null
        ChapletCatalog.install(compose.activity)
        val context = compose.activity.application
        val repository = CatholicDataRepository(context).apply { setLanguage(lang) }
        val database = androidx.room.Room.inMemoryDatabaseBuilder(context, com.example.data.local.SanctaFidesDatabase::class.java)
            .allowMainThreadQueries().build().also { db = it }
        val tts = LiturgicalTtsManager(context).also { voice = it }
        val store = com.example.data.prefs.UserPreferencesStore(context)
        val prefs = com.example.data.prefs.UserPreferences(
            language = lang, designLanguage = identity,
            themePreset = com.example.data.prefs.UserPreferences.DEFAULT_THEME_PRESET, darkModeSetting = dark,
            fontFamilyChoice = com.example.data.prefs.UserPreferences.DEFAULT_FONT_FAMILY, fontSizeScale = scale,
            audioNarrationSpeed = 1f, activeBibleTranslationId = "", isFullCccDownloaded = false, hasChosenLanguage = true
        )
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity, darkModeSetting = dark, fontScale = scale, reduceTransparency = accessible, highContrast = accessible, liturgicalColorHex = 0xFF6B3FA0L) {
                com.example.ui.MainApp(repository, database.dao(), tts, store, prefs)
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("nav_other_chaplets").performScrollTo().performClick()
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(3_000)
        compose.waitForIdle()
        compose.onNodeWithTag("chaplet_catalog").assertExists()
        compose.onNodeWithTag("chaplet_row_coronilla-difuntos-45").performScrollTo().performClick()
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(3_000)
        compose.waitForIdle()
        // Back to the Rosary screen's own «Otras ▾» segment, the other way in.
        compose.activity.onBackPressedDispatcher.let { d -> compose.runOnUiThread { d.onBackPressed() } }
        compose.waitForIdle()
        if (compose.onAllNodesWithTag("other_chaplets_segment").fetchSemanticsNodes().isNotEmpty()) {
            compose.onNodeWithTag("other_chaplets_segment").performClick()
            compose.waitForIdle()
            compose.mainClock.advanceTimeBy(3_000)
            compose.waitForIdle()
        }
    }
}
