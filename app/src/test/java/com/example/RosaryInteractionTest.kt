package com.example

import android.app.Application
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.unit.dp
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
import org.junit.Assert.assertTrue
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
        // A displayed Text node can still have its last lines clipped. Verify
        // its complete layout fits above the fixed transport on the first frame.
        val openingBounds = compose.onNodeWithTag("prayer_leader_text").getUnclippedBoundsInRoot()
        val transportTop = compose.onNodeWithTag("prayer_transport").getUnclippedBoundsInRoot().top
        assertTrue("The whole opening prayer must fit above transport", openingBounds.bottom <= transportTop)
        compose.onNodeWithTag("prev_bead_button").assertIsNotEnabled()
        compose.onNodeWithTag("next_bead_button")
            .assertIsEnabled().performClick()
        compose.onNodeWithTag("current_prayer_title").assertTextEquals("Apostles' Creed")
        compose.onNodeWithText("I believe in God", substring = true).assertExists()
        compose.onNodeWithTag("prayer_content_scroll").performSemanticsAction(SemanticsActions.ScrollBy) { scroll ->
            scroll(0f, 100000f)
        }
        val longPrayer = compose.onNodeWithText("I believe in God", substring = true).getUnclippedBoundsInRoot()
        assertTrue("The end of a long prayer must be reachable above transport", longPrayer.bottom <= transportTop)
        compose.onNodeWithTag("next_bead_button").assertIsDisplayed()

        // Entering the chaplet starts its own guide, rather than reusing the
        // Rosary's current index or leaving its previous prayer on screen.
        compose.onNodeWithText("Divine Mercy", ignoreCase = true)
            .performScrollTo().performClick()
        compose.onNodeWithTag("current_prayer_title").assertTextEquals("Sign of the Cross")
        compose.onNodeWithText(signOfCross).assertExists()
        compose.onNodeWithTag("prev_mercy_step_button").assertIsNotEnabled()
        compose.onNodeWithTag("next_mercy_step_button")
            .assertIsEnabled().performClick()
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
                fontFamilyChoice = "ROBOTO", fontScale = 2f) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    Surface { RosaryScreen(repository, tts, AppLanguage.ES) }
                }
            }
        }
        compose.onNodeWithTag("next_bead_button").assertIsDisplayed().assertIsEnabled()
        // Labeled transport controls stay visible with their spoken labels and
        // retain a >=48dp target even with 200% reading text.
        for (label in listOf("Anterior", "Siguiente")) {
            compose.onNodeWithTag(if (label == "Anterior") "prev_bead_button" else "next_bead_button")
                .assertIsDisplayed()
            compose.onNodeWithContentDescription(label)
                .assertIsDisplayed()
                .assertWidthIsAtLeast(48.dp)
                .assertHeightIsAtLeast(48.dp)
        }
        // The current prayer's title is never clipped away at large type.
        compose.onNodeWithTag("current_prayer_title").performScrollTo().assertIsDisplayed()
        compose.onNodeWithTag("next_bead_button").performClick()
        compose.onNodeWithTag("prev_bead_button").assertIsDisplayed().assertIsEnabled().performClick()
        compose.onNodeWithTag("prev_bead_button").assertIsNotEnabled()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun identities(): List<Array<Any>> = DesignLanguage.entries.map { arrayOf<Any>(it) }
    }
}
