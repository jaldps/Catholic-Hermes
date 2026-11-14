package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
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
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Temptation sheet -> "Más" -> long guide -> back returns to the temptation list. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class TemptationGuideInteractionTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val context get() = ApplicationProvider.getApplicationContext<Application>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narration: LiturgicalTtsManager? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        narration?.release()
        repository?.close()
        database?.close()
    }

    private fun tag(value: String) = compose.onNodeWithTag(value, useUnmergedTree = true)
    private fun exists(value: String) = compose.onAllNodesWithTag(value, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    private fun waitFor(value: String) {
        try {
            compose.waitUntil(10_000) { exists(value) }
        } catch (failure: androidx.compose.ui.test.ComposeTimeoutException) {
            throw AssertionError("Timed out waiting for tag '$value'", failure)
        }
    }

    @Test fun moreButtonOpensGuideAndBackReturnsToTemptations() {
        val repo = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES); repository = this }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val store = UserPreferencesStore(context)
        val prefs = UserPreferences(AppLanguage.ES, "SANCTUARY", "LITURGICAL_DYNAMIC", "LIGHT", "IDENTITY_DEFAULT", 1f, 1f, "bundled", false, true)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "SANCTUARY", darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs) }
            }
        }
        tag("home_screen").performScrollToNode(hasTestTag("home_open_temptations"))
        tag("home_open_temptations").performClick()
        waitFor("tab_sin_avoider")
        tag("tab_sin_avoider").performScrollToNode(hasTestTag("card_overcome_PRIDE"))
        tag("card_overcome_PRIDE").performClick()
        waitFor("root_sheet")
        tag("root_more_button").performScrollTo().performClick()
        waitFor("temptation_guide_page")
        tag("temptation_guide_page").assertIsDisplayed()
        tag("temptation_guide_page").performScrollToNode(hasTestTag("guide_section_plan"))
        tag("guide_section_plan").assertIsDisplayed()

        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        waitFor("tab_sin_avoider")
        tag("tab_sin_avoider").assertIsDisplayed()
        assert(!exists("temptation_guide_page"))
    }
}
