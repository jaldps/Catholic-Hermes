package com.example

import android.app.Application
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.semantics.SemanticsActions
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.RosaryScreen
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** The compact guide must keep prayer text and both independent devotions usable. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class RosaryInteractionTest(private val identity: DesignLanguage) {
    @get:Rule val compose = createComposeRule()

    private var narration: LiturgicalTtsManager? = null

    @After fun releaseNarration() {
        narration?.release()
    }

    @Test fun currentPrayerAndStepNavigationStayInSyncAcrossDevotions() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repository = CatholicDataRepository(context).apply { setLanguage(AppLanguage.EN) }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT") {
                // Match screenshot rendering: no decorative animation should
                // keep the Compose test clock waiting for an idle frame.
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    Surface {
                        RosaryScreen(repository, tts, AppLanguage.EN)
                    }
                }
            }
        }

        val signOfCross = "In the name of the Father, and of the Son, and of the Holy Spirit. Amen."
        compose.onNodeWithTag("current_prayer_title")
            .assertTextEquals("Sign of the Cross").assertIsDisplayed()
        compose.onNodeWithText(signOfCross).assertIsDisplayed()
        compose.onNodeWithTag("prev_bead_button").assertIsNotEnabled()
        compose.onNodeWithTag("next_bead_button")
            .performScrollTo().assertIsEnabled().performClick()
        compose.onNodeWithTag("current_prayer_title").assertTextEquals("Apostles' Creed")
        compose.onNodeWithText("I believe in God", substring = true).assertExists()

        // Entering the chaplet starts its own guide, rather than reusing the
        // Rosary's current index or leaving its previous prayer on screen.
        compose.onNodeWithText("Divine Mercy", ignoreCase = true)
            .performScrollTo().performClick()
        compose.onNodeWithTag("current_prayer_title").assertTextEquals("Sign of the Cross")
        compose.onNodeWithText(signOfCross).assertExists()
        compose.onNodeWithTag("prev_mercy_step_button").assertIsNotEnabled()
        compose.onNodeWithTag("next_mercy_step_button")
            .performScrollTo().assertIsEnabled().performClick()
        compose.onNodeWithTag("current_prayer_title").assertTextEquals("Opening prayer")
        compose.onNodeWithTag("prev_mercy_step_button").assertIsEnabled()

        // Switching devotions preserves the Rosary's position independently.
        compose.onNodeWithText("The Holy Rosary", ignoreCase = true)
            .performScrollTo().performClick()
        compose.onNodeWithTag("current_prayer_title").assertTextEquals("Apostles' Creed")
    }

    @Test
    @Config(sdk = [34], qualifiers = "w320dp-h760dp-xhdpi")
    fun translatedControlsStayWholeAndReachableAtLargeType() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repository = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES) }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT",
                fontFamilyChoice = "ROBOTO", fontScale = 1.3f) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    Surface { RosaryScreen(repository, tts, AppLanguage.ES) }
                }
            }
        }
        compose.onNodeWithTag("next_bead_button").performScrollTo().assertIsDisplayed().assertIsEnabled()
        for (label in listOf("Anterior", "Siguiente")) {
            compose.onNodeWithTag(if (label == "Anterior") "prev_bead_button" else "next_bead_button")
                .performScrollTo().assertIsDisplayed()
            compose.onNodeWithText(label, ignoreCase = true, useUnmergedTree = true)
                .performSemanticsAction(SemanticsActions.GetTextLayoutResult) { getResults ->
                    val results = mutableListOf<androidx.compose.ui.text.TextLayoutResult>()
                    getResults(results)
                    org.junit.Assert.assertEquals("$identity: $label must stay whole", 1, results.single().lineCount)
                    val result = results.single()
                    org.junit.Assert.assertFalse("$identity/$label must not be ellipsized", result.isLineEllipsized(0))
                    // Native glyph bounds can round up by a fraction of a pixel
                    // beyond TextLayoutResult's integer size.
                    org.junit.Assert.assertTrue("$identity/$label must fit its text bounds",
                        result.getLineRight(0) - result.getLineLeft(0) <= result.size.width + 1f)
                }
        }
        compose.onNodeWithTag("next_bead_button").performClick()
        compose.onNodeWithTag("prev_bead_button").performScrollTo().assertIsDisplayed().assertIsEnabled().performClick()
        compose.onNodeWithTag("prev_bead_button").assertIsNotEnabled()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun identities(): List<Array<Any>> = DesignLanguage.entries.map { arrayOf<Any>(it) }
    }
}
