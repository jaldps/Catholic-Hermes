package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.text.font.FontFamily
import java.util.concurrent.atomic.AtomicReference
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.semantics.SemanticsActions
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
import com.example.data.models.FontChoice
import com.example.ui.theme.AppFonts
import com.example.ui.theme.LocalAppTextStyles
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

        val observedFamily = AtomicReference<FontFamily?>()
        compose.setContent {
            val stored by store.preferences.collectAsState(initial = prefs)
            val active = prefs.copy(fontFamilyChoice = stored.fontFamilyChoice, fontSizeScale = stored.fontSizeScale)
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT",
                fontFamilyChoice = active.fontFamilyChoice, fontScale = active.fontSizeScale) {
                val family = LocalAppTextStyles.current.rowTitle.fontFamily
                SideEffect { observedFamily.set(family) }
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    MainApp(repository, db.dao(), tts, store, active)
                }
            }
        }

        listOf(
            "nav_home" to "home_screen",
            "nav_bible" to "bible_screen",
            "nav_prayers" to "prayers_and_novenas_screen",
            "nav_more" to "more_screen"
        ).forEach { (tab, screen) ->
            compose.onNodeWithTag(tab).assertHasClickAction().performClick()
            compose.onNodeWithTag(screen).assertIsDisplayed()
            if (tab == "nav_bible") {
                // Lumen: the Bible opens on its large title and the
                // continue-reading card instead of the identity illustration.
                compose.onNodeWithTag("bible_continue_reading").assertIsDisplayed()
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
        // More is a tab (nav_more above) and is also reached via the Home tile.
        goToMoreFromHome()

        clickMoreRow("nav_catechism")
        compose.onNodeWithTag("catechism_screen").assertIsDisplayed()
        pressBackToMore()

        // Practices is a secondary screen and returns to its Explore origin.
        clickMoreRow("nav_habits")
        compose.onNodeWithTag("habits_and_virtues_screen").assertIsDisplayed()
        pressBackToMore()

        goToMoreFromHome()

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
        clickMoreRow("font_settings_button")
        compose.onNodeWithTag("font_picker").assertIsDisplayed()
        compose.onNodeWithTag("font_choice_ROBOTO").assertHasClickAction()
        compose.onNodeWithTag("font_choice_EB_GARAMOND").assertHasClickAction()
        val selectedFont = if (observedFamily.get() == AppFonts.Roboto) FontChoice.EB_GARAMOND else FontChoice.ROBOTO
        compose.onNodeWithTag("font_choice_${selectedFont.id}").performClick()
        compose.waitUntil(timeoutMillis = 10000) { observedFamily.get() == AppFonts.family(selectedFont) }
        compose.onNodeWithTag("font_choice_${selectedFont.id}").assertIsSelected()
        // Closing immediately after the final selection must wait for its
        // write instead of cancelling it with the dialog's composition.
        compose.onNodeWithTag("font_choice_INTER").performClick()
        compose.onNodeWithText("Done").performClick()
        compose.waitUntil(timeoutMillis = 10000) { observedFamily.get() == AppFonts.Inter }
        compose.onNodeWithText("Inter").assertIsDisplayed()

        // Verify actual reading content, not only the theme's style slots.
        compose.onNodeWithTag("nav_bible").performClick()
        compose.onNode(hasText("Bible", ignoreCase = true) and hasClickAction() and
            hasAnyAncestor(hasTestTag("bible_screen"))).performClick()
        // The earlier New Testament filter is intentionally retained across tabs.
        compose.onNodeWithTag("bible_canon_filter").performScrollTo().performClick()
        compose.onNodeWithTag("bible_canon_0").performClick()
        compose.onNodeWithTag("book_item_gen").performScrollTo().performClick()
        compose.onNodeWithTag("verse_1").performScrollTo()
        fun assertReadingFamily(expected: FontFamily) {
            compose.onNode(hasText("created", substring = true, ignoreCase = true) and
                hasAnyAncestor(hasTestTag("verse_1")), useUnmergedTree = true)
                .performSemanticsAction(SemanticsActions.GetTextLayoutResult) { getResults ->
                    val results = mutableListOf<androidx.compose.ui.text.TextLayoutResult>()
                    getResults(results)
                    org.junit.Assert.assertEquals(expected, results.single().layoutInput.style.fontFamily)
                }
        }
        assertReadingFamily(AppFonts.Inter)
        // The reader's face lives in its "Aa" sheet.
        compose.onNodeWithTag("bible_reader_text_button").assertIsDisplayed().performClick()
        compose.onNodeWithTag("bible_reader_font_choice").assertIsDisplayed()
        compose.onNodeWithTag("bible_reader_font_SERIF").performClick()
        compose.onNodeWithTag("verse_1").performScrollTo()
        assertReadingFamily(AppFonts.EbGaramond)
    }

    private fun clickableMoreRow(tag: String) = compose.onNode(
        hasClickAction() and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))) and
            hasAnyAncestor(hasTestTag("more_screen"))
    )

    private fun revealMoreRow(tag: String) {
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag(tag))
        compose.onNode(hasTestTag(tag) and hasAnyAncestor(hasTestTag("more_screen")))
            .assertIsDisplayed()
    }

    private fun goToMoreFromHome() {
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").assertIsDisplayed()
    }

    private fun clickMoreRow(tag: String) {
        revealMoreRow(tag)
        clickableMoreRow(tag).performClick()
    }

    private fun pressBackToMore() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("more_screen").assertIsDisplayed()
    }

    private fun pressBackToHome() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("home_screen").assertIsDisplayed()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun identities(): List<Array<Any>> = DesignLanguage.entries.map { arrayOf<Any>(it) }
    }
}
