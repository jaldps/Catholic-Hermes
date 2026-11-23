package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.ui.AppDestination
import com.example.ui.theme.DesignLanguage
import org.junit.After
import org.junit.Assert.assertTrue
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

@OptIn(androidx.compose.ui.ExperimentalComposeUiApi::class)
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class DockKeyboardFocusTest(private val identity: DesignLanguage) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var fixture: LandscapeRealAppFixture? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle(); fixture?.close()
    }
    @Test fun focusingTheLastExploreRowClearsTheFloatingDockWithoutATestNudge() {
        val resources = LandscapeRealAppFixture(ApplicationProvider.getApplicationContext<Application>(), AppLanguage.ES).also { fixture = it }
        lateinit var input: androidx.compose.ui.input.InputModeManager
        compose.setContent { input = LocalInputModeManager.current; resources.Content(AppDestination.MORE, identity) }
        compose.runOnIdle { input.requestInputMode(InputMode.Keyboard) }
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("image_credits_button"))
        compose.onNodeWithTag("image_credits_button").performSemanticsAction(SemanticsActions.RequestFocus) { assertTrue(it()) }
        compose.onNodeWithTag("image_credits_button").assertIsFocused()
        compose.waitUntil(10_000) {
            compose.onNodeWithTag("image_credits_button").fetchSemanticsNode().boundsInRoot.bottom <=
                compose.onNodeWithTag("bottom_nav_bar").fetchSemanticsNode().boundsInRoot.top + 1f
        }
        val row = compose.onNodeWithTag("image_credits_button").fetchSemanticsNode().boundsInRoot
        val dock = compose.onNodeWithTag("bottom_nav_bar").fetchSemanticsNode().boundsInRoot
        assertTrue("A focused row must not be obscured by the dock", row.bottom <= dock.top + 1f)
        val capture = File("build/screenshots/dock/r2_dock_focus_${identity.id}.png")
        landscapeCapture(compose, compose.activity, 411, 891, capture)
        val actual = compose.onNodeWithTag("image_credits_button").fetchSemanticsNode().boundsInRoot
        val pixels = BitmapFactory.decodeFile(capture.path)
        val x = actual.center.x.toInt(); val y = actual.top.toInt()
        val ring = Color(pixels.getPixel(x, y + with(compose.density) { 2.dp.roundToPx() }))
        val halo = Color(pixels.getPixel(x, y + with(compose.density) { 3.5.dp.roundToPx() }))
        val a = ring.luminance(); val b = halo.luminance()
        val ratio = (maxOf(a,b)+.05f)/(minOf(a,b)+.05f)
        assertTrue("The actual focused Explore row must paint a visible ring, not just move above the dock: $ratio", ratio >= 3f)
        File("build/audit/dock_focus_${identity.id}.json").apply {
            parentFile!!.mkdirs(); writeText("""{"identity":"${identity.id}","row_bottom_px":${actual.bottom},"dock_top_px":${dock.top},"focus_contrast":$ratio}""")
        }
        pixels.recycle()
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun matrix(): List<Array<Any>> = listOf(DesignLanguage.APPLE, DesignLanguage.CLASSIC, DesignLanguage.GLASS).map { arrayOf<Any>(it) }
    }
}
