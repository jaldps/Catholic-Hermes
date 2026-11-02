package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Exercise real destinations and back handling, rather than a sample scaffold. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class AppNavigationTest(private val identity: DesignLanguage) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null

    @After fun releaseResources() {
        // Stop the real screens' collectors before closing their database.
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        narration?.release()
        database?.close()
    }

    @Test fun tabsSecondaryDestinationsAndSettingsRemainReachable() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repository = CatholicDataRepository(context).apply { setLanguage(AppLanguage.EN) }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java)
            .allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val store = UserPreferencesStore(context)
        val prefs = UserPreferences(
            language = AppLanguage.EN,
            designLanguage = identity.id,
            themePreset = UserPreferences.DEFAULT_THEME_PRESET,
            darkModeSetting = "LIGHT",
            fontFamilyChoice = UserPreferences.DEFAULT_FONT_FAMILY,
            fontSizeScale = 1f,
            audioNarrationSpeed = 1f,
            activeBibleTranslationId = "",
            isFullCccDownloaded = false,
            hasChosenLanguage = true
        )

        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    MainApp(repository, db.dao(), tts, store, prefs)
                }
            }
        }

        listOf(
            "nav_home" to "home_screen",
            "nav_bible" to "bible_screen",
            "nav_rosary" to "rosary_screen",
            "nav_prayers" to "prayers_and_novenas_screen",
            "nav_more" to "more_screen"
        ).forEach { (tab, screen) ->
            compose.onNodeWithTag(tab).assertHasClickAction().performClick()
            compose.onNodeWithTag(screen).assertIsDisplayed()
            if (tab == "nav_bible") {
                compose.onNodeWithTag("bible_canon_filter").performScrollTo().performClick()
                compose.onNodeWithTag("bible_canon_2").performClick()
                compose.onNodeWithText("Matthew").assertIsDisplayed()
            }
        }

        compose.onNodeWithTag("nav_home").performClick()
        compose.onNodeWithTag("read_mass_full_button").performScrollTo().performClick()
        compose.onNodeWithTag("bible_screen").assertIsDisplayed()
        compose.onNodeWithTag("daily_missal_screen").assertIsDisplayed()
        compose.onNodeWithText("Today's Mass", ignoreCase = true).assertIsDisplayed()
        compose.onNodeWithTag("nav_more").performClick()

        clickMoreRow("nav_catechism")
        compose.onNodeWithTag("catechism_screen").assertIsDisplayed()
        pressBackToMore()

        clickMoreRow("nav_parishes")
        compose.onNodeWithTag("parish_finder_screen").assertIsDisplayed()
        pressBackToMore()

        // More is a real scrollable destination; its final group must remain
        // reachable and show the active language, not just an unlabeled action.
        revealMoreRow("app_bar_theme_button")
        clickableMoreRow("app_bar_theme_button").assertHasClickAction()
        revealMoreRow("app_bar_lang_button")
        clickableMoreRow("app_bar_lang_button").assertHasClickAction()
        compose.onNodeWithText("English").assertIsDisplayed()
    }

    private fun clickableMoreRow(tag: String) = compose.onNode(
        hasClickAction() and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag)))
    )

    private fun revealMoreRow(tag: String) {
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag(tag))
        compose.onNodeWithTag(tag).assertIsDisplayed()
    }

    private fun clickMoreRow(tag: String) {
        revealMoreRow(tag)
        clickableMoreRow(tag).performClick()
    }

    private fun pressBackToMore() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("more_screen").assertIsDisplayed()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun identities(): List<Array<Any>> = DesignLanguage.entries.map { arrayOf<Any>(it) }
    }
}
