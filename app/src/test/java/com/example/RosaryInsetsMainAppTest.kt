package com.example

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.models.RosaryMysteryType
import com.example.ui.AppDestination
import com.example.ui.screens.RosarySession
import com.example.ui.screens.RosarySessionStore
import com.example.ui.theme.DesignLanguage
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Narrow regression for the shared-inset hypothesis: the real MainApp frame at 800x360,
 * Lumen light, with status 24 / bottom gestures 16 / lateral gestures 32. No isolated
 * RosaryScreen: the scaffold, LumenKit and AppSupportingPaneLayout must all participate.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], qualifiers = "w800dp-h360dp-land-xhdpi")
class RosaryInsetsMainAppTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var fixture: LandscapeRealAppFixture? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        fixture?.close()
    }

    private data class Measures(
        val cut: String,
        val panelWidth: Float,
        val beadLeft: Float,
        val textLeft: Float,
        val textWidth: Float,
        val nextLeft: Float,
        val nextRight: Float,
        val nextWidth: Float,
        val nextHeight: Float,
        val nextCenterY: Float
    )

    private fun measure(cut: String?): Measures {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val resource = fixture ?: LandscapeRealAppFixture(context, AppLanguage.ES).also { fixture = it }
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        landscapeViewport(compose, compose.activity, 800, 360)
        RosarySessionStore.save(context, RosarySession.opening(0), RosaryMysteryType.JOYFUL)
        compose.runOnUiThread {
            compose.activity.setContent { resource.Content(AppDestination.ROSARY, DesignLanguage.APPLE, dark = false) }
        }
        compose.waitForIdle()
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 360, cutout = cut) }
        compose.waitForIdle()
        fun box(tag: String): Rect = compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot
        val density = compose.activity.resources.displayMetrics.density
        val panel = box("app_supporting_panel")
        val bead = box("prayer_bead_counter")
        val text = box("prayer_content_scroll")
        val next = box("next_bead_button")
        return Measures(
            cut ?: "none", panel.width / density, bead.left / density, text.left / density, text.width / density,
            next.left / density, next.right / density, next.width / density, next.height / density, next.center.y / density
        )
    }

    @Test fun noDoubleLateralInsetInTheRealFrame() {
        val none = measure(null)
        val left = measure("left")
        val right = measure("right")
        val all = listOf(none, left, right)

        java.io.File("build/audit").mkdirs()
        java.io.File("build/audit/rosary_insets_mainapp.json").writeText(
            all.joinToString(prefix = "[\n", postfix = "\n]\n", separator = ",\n") { m ->
                """  {"cut":"${m.cut}","panel_width":${m.panelWidth},"bead_left":${m.beadLeft},"text_left":${m.textLeft},"text_width":${m.textWidth},"next_left":${m.nextLeft},"next_right":${m.nextRight},"next_size":[${m.nextWidth},${m.nextHeight}],"next_center_y":${m.nextCenterY}}"""
            }
        )

        all.forEach { m ->
            assertEquals("supporting panel stays 256 dp", 256f, m.panelWidth, 1f)
            assertEquals("thread x = max(safeStart, gestures) + 8 = 40", 40f, m.beadLeft, 1f)
            assertEquals("Siguiente is 56x56", 56f, m.nextWidth, .5f)
            assertEquals("Siguiente is 56x56", 56f, m.nextHeight, .5f)
            assertEquals("Siguiente centre at 0.70 H", 252f, m.nextCenterY, 1f)
        }
        // W_L + 20 + the 4 dp spine seam; one safeStart shift, never two.
        assertEquals(280f, none.textLeft, 1f)
        assertEquals(312f, left.textLeft, 1f)
        assertEquals(280f, right.textLeft, 1f)
        assertEquals(424f, none.textWidth, 1f)
        assertEquals("left cut shifts the column by safeStart once", 392f, left.textWidth, 1f)
        assertEquals("right cut reserves safeEnd + 8 once", 396f, right.textWidth, 1f)
        assertEquals("Siguiente keeps its baseline x with a left cut", 780f, left.nextRight, 1f)
        assertEquals("Siguiente moves only 28 dp with a right cut", 752f, right.nextRight, 1f)
        assertEquals(28f, none.textWidth - right.textWidth, 1f)
        assertEquals(28f, none.nextRight - right.nextRight, 1f)
        assertTrue("Siguiente inside the safe right edge", right.nextRight <= 768f + 1f)
    }
}
