package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.data.audio.LiturgicalTtsManager
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
class RosaryLandscapeGeometryTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var tts: LiturgicalTtsManager? = null
    @After fun close() { tts?.release() }
    private fun launch(width: Int, height: Int, cut: String? = null, navbar: String? = null) {
        landscapeViewport(compose, compose.activity, width, height)
        val repo = CatholicDataRepository(compose.activity)
        val voice = LiturgicalTtsManager(compose.activity).also { tts = it }
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { RosaryScreen(repo, voice, AppLanguage.ES) }
        } }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, height, cutout = cut, navbar = navbar) }
        compose.waitForIdle()
    }
    private fun b(tag: String) = compose.onNodeWithTag(tag).getUnclippedBoundsInRoot().let { androidx.compose.ui.geometry.Rect(it.left.value, it.top.value, it.right.value, it.bottom.value) }
    private fun check(width: Int, height: Int, right: Int = 0, left: Int = 0) {
        assertEquals((.32f * width).coerceIn(256f, 340f), b("app_supporting_panel").width, 1f)
        val next = b("next_bead_button")
        assertEquals(56f, next.width, .5f); assertEquals(56f, next.height, .5f)
        assertEquals(height * .70f, next.center.y, 1f)
        assertTrue(width - right - next.center.x <= 120f)
        assertTrue(next.right <= width - right)
        assertTrue(b("prayer_bead_counter").left >= maxOf(32, left) + 8f)
        assertEquals(44f, b("prev_bead_button").height, 1f)
        // The fixed 32 dp title row starts at S + 4; its 28 dp text is vertically centered.
        assertEquals(30f, b("current_prayer_title").top, 1f)
        compose.onNodeWithTag("next_bead_button").assertIsDisplayed().performClick()
        compose.onNodeWithTag("current_prayer_title").assertTextEquals("Credo de los Apóstoles")
        compose.onNodeWithTag("prev_bead_button").performClick()
    }
    @Test fun largeGeometry() { launch(891, 411); check(891, 411) }
    @Test fun smallGeometry() { launch(800, 360); check(800, 360) }
    @Test fun leftCutout() { launch(800, 360, "left"); check(800, 360, left = 32) }
    @Test fun rightCutout() { launch(800, 360, "right"); check(800, 360, right = 32) }
    @Test fun lateralThreeButtonNavigation() { launch(800, 360, navbar = "right"); check(800, 360, right = 48) }
}
