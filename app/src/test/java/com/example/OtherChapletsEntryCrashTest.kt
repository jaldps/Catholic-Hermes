package com.example

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ChapletCatalog
import com.example.ui.screens.RosaryScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Orar → «Otras coronillas» on the real path: bundled assets, real images, no inspection mode
 * (the force close of 1.9.1 happened only there).
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class OtherChapletsEntryCrashTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var voice: LiturgicalTtsManager? = null

    @Before fun fresh() {
        ChapletCatalog.override = null
        ChapletCatalog.install(compose.activity)
        compose.activity.getSharedPreferences("unfinished_prayer_session", 0).edit().clear().commit()
    }
    @After fun close() { voice?.release() }

    @Test fun openingOtherChapletsFromPrayersShowsTheCatalogueAndAChaplet() {
        assertTrue("bundled chaplets load", ChapletCatalog.default.all.size == 55)
        val repo = CatholicDataRepository(compose.activity).apply { setLanguage(AppLanguage.ES) }
        val audio = LiturgicalTtsManager(compose.activity).also { voice = it }
        compose.setContent { SanctaFidesTheme(reduceMotion = true) { RosaryScreen(repo, audio, AppLanguage.ES, initialTab = 2) } }
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
        compose.onNodeWithTag("chaplet_catalog").assertExists()
        compose.onNodeWithTag("chaplet_row_siete-dolores-corona").performScrollTo().performClick()
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
        compose.onNodeWithTag("prayer_leader_text").assertExists()
    }

    @Test fun theOrarRowOpensOtherChapletsInsideTheWholeApp() {
        val context = compose.activity.application
        val repository = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES) }
        val db = androidx.room.Room.inMemoryDatabaseBuilder(context, com.example.data.local.SanctaFidesDatabase::class.java)
            .allowMainThreadQueries().build()
        val tts = LiturgicalTtsManager(context).also { voice = it }
        val store = com.example.data.prefs.UserPreferencesStore(context)
        val prefs = com.example.data.prefs.UserPreferences(
            language = AppLanguage.ES, designLanguage = "APPLE",
            themePreset = com.example.data.prefs.UserPreferences.DEFAULT_THEME_PRESET, darkModeSetting = "LIGHT",
            fontFamilyChoice = com.example.data.prefs.UserPreferences.DEFAULT_FONT_FAMILY, fontSizeScale = 1f,
            audioNarrationSpeed = 1f, activeBibleTranslationId = "", isFullCccDownloaded = false, hasChosenLanguage = true
        )
        compose.setContent {
            SanctaFidesTheme(designLanguage = "APPLE", darkModeSetting = "LIGHT") {
                com.example.ui.MainApp(repository, db.dao(), tts, store, prefs)
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
        compose.onNodeWithTag("chaplet_row_corona-san-miguel-raccolta").performScrollTo().performClick()
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(3_000)
        compose.waitForIdle()
        compose.onNodeWithTag("prayer_leader_text").assertExists()
        compose.runOnUiThread { compose.activity.setContent { } }
        compose.waitForIdle()
        db.close()
    }
}
