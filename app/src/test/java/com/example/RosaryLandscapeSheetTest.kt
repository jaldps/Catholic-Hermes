package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.audio.RecitationVoiceMode
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.RosaryScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class RosaryLandscapeSheetTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var voice: LiturgicalTtsManager? = null
    @After fun close() { voice?.release() }
    private fun launch() {
        val repo = CatholicDataRepository(compose.activity)
        val audio = LiturgicalTtsManager(compose.activity).also { voice = it }
        compose.activity.getSharedPreferences("unfinished_prayer_session", 0).edit().clear().putInt("rosary_step", 10).apply()
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { RosaryScreen(repo, audio, AppLanguage.ES) }
        } }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 411) }; compose.waitForIdle()
    }
    @Test fun optionsAreWideAndVoicesAreThreeAccessibleVerticalRows() {
        launch()
        compose.onNodeWithTag("rosary_options_button").performClick()
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 411) }; compose.waitForIdle()
        val box = compose.onNodeWithTag("rosary_options_sheet").getUnclippedBoundsInRoot()
        assertEquals(891f * .66f, (box.right - box.left).value, 1f)
        compose.onNodeWithTag("rosary_voice_mode_picker").assertIsDisplayed()
        val rows = compose.onAllNodes(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.RadioButton)).fetchSemanticsNodes()
        assertEquals(3, rows.size)
        rows.forEach { assertTrue(it.size.height / compose.density.density >= 44f) }
        compose.onNodeWithText("Respuesta").performClick()
        compose.runOnIdle { assertEquals(RecitationVoiceMode.FEMALE, voice!!.voiceMode.value) }
        compose.onNodeWithTag("sheet_close").performClick()
        compose.onNodeWithTag("rosary_options_sheet").assertDoesNotExist()
        compose.onNodeWithTag("next_bead_button").assertIsDisplayed()
    }
    @Test fun mysteriesStayNormalAndTheFullMysteryUsesTheWideSheet() {
        launch()
        compose.onNodeWithTag("rosary_mystery_picker").performScrollTo().performClick()
        val normal = compose.onNodeWithTag("rosary_mysteries_sheet").getUnclippedBoundsInRoot()
        assertEquals(445.5f, (normal.right - normal.left).value, 1f)
        compose.onNodeWithTag("sheet_close").performClick()
        compose.onNodeWithTag("rosary_view_mystery").performScrollTo().performClick()
        val wide = compose.onNodeWithTag("rosary_mystery_sheet").getUnclippedBoundsInRoot()
        assertEquals(588.06f, (wide.right - wide.left).value, 1f)
        compose.onNodeWithTag("sheet_close").assertIsDisplayed().performClick()
        compose.onNodeWithTag("next_bead_button").assertIsDisplayed()
    }
}
