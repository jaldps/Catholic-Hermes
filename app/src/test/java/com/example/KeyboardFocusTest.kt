package com.example

import android.graphics.BitmapFactory
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.example.ui.design.*
import com.example.ui.theme.*
import org.junit.Assert.*
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
class KeyboardFocusTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private fun verify(kind: String) {
        val requester = FocusRequester()
        val elsewhere = FocusRequester()
        var activeDark by mutableStateOf(dark)
        var ringInk = Color.Unspecified
        var haloInk = Color.Unspecified
        lateinit var input: androidx.compose.ui.input.InputModeManager
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (activeDark) "DARK" else "LIGHT", reduceMotion = true) {
            input = LocalInputModeManager.current
            ringInk = LocalAppDesign.current.palette.textPrimary; haloInk = LocalAppDesign.current.palette.canvasTop
            CompositionLocalProvider(LocalInspectionMode provides true) {
                Box(Modifier.fillMaxSize().background(LocalAppDesign.current.palette.canvasTop).padding(24.dp)) {
                    val target = Modifier.width(280.dp).focusRequester(requester).testTag("focus_target")
                    when (kind) {
                        "row" -> AppListRow("Oraciones", onClick = {}, modifier = target)
                        "verse" -> AppVerseRow(1, com.example.data.repository.BibleData.sampleVersesWithCommentaries.first().text.get(com.example.data.models.AppLanguage.ES), onClick = {}, modifier = target)
                        "clear" -> AppSearchBar("María", {}, "Buscar", modifier = target)
                        else -> AppPrimaryButton("Rezar", {}, modifier = target)
                    }
                    // A real focus transfer avoids Android immediately refocusing the sole target in keyboard mode.
                    Box(Modifier.align(Alignment.BottomEnd).size(44.dp).focusRequester(elsewhere).focusable().testTag("other_focus"))
                }
            }
        } }
        val name = "${identity.id}_${if (dark) "dark" else "light"}_$kind"
        val before = File("build/screenshots/focus/${name}_before.png")
        landscapeCapture(compose, compose.activity, 411, 891, before)
        compose.runOnIdle { input.requestInputMode(InputMode.Keyboard) }
        if (kind == "clear") compose.onNodeWithContentDescription("Clear").performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.RequestFocus) { assertTrue(it()) }
        else compose.runOnIdle { requester.requestFocus() }
        compose.onNode(isFocused()).assertExists()
        val active = File("build/screenshots/focus/${name}_focused.png")
        landscapeCapture(compose, compose.activity, 411, 891, active)
        fun assertVisibleRing(bitmap: android.graphics.Bitmap) {
            val bounds = compose.onNode(isFocused()).fetchSemanticsNode().boundsInRoot
            val x = bounds.center.x.toInt()
            val y = bounds.top.toInt()
            val ring = bitmap.getPixel(x, y + with(compose.density) { 2.dp.roundToPx() })
            val halo = bitmap.getPixel(x, y + with(compose.density) { 0.5.dp.roundToPx() })
            assertEquals("The two-dp ring is drawn at the control's perimeter", ringInk.toArgb(), ring)
            assertEquals("The contrast halo is present next to the ring", haloInk.toArgb(), halo)
            val a = Color(ring).luminance(); val b = Color(halo).luminance()
            assertTrue("The actual ring/halo pixels must reach 3:1", (maxOf(a,b)+.05f)/(minOf(a,b)+.05f) >= 3f)
        }
        val a = BitmapFactory.decodeFile(before.path); val b = BitmapFactory.decodeFile(active.path)
        assertFalse("Requesting keyboard focus must paint a visible ring ($name)", a.sameAs(b))
        assertVisibleRing(b)
        compose.runOnIdle { activeDark = !dark }; compose.waitForIdle()
        compose.onNode(isFocused()).assertExists()
        val retinted = File("build/screenshots/focus/${name}_retinted.png")
        landscapeCapture(compose, compose.activity, 411, 891, retinted)
        val tinted = BitmapFactory.decodeFile(retinted.path)
        assertVisibleRing(tinted)
        compose.runOnIdle { elsewhere.requestFocus() }; compose.waitForIdle()
        compose.onNodeWithTag("other_focus").assertIsFocused()
        val unfocused = File("build/screenshots/focus/${name}_retinted_cleared.png")
        landscapeCapture(compose, compose.activity, 411, 891, unfocused)
        val plain = BitmapFactory.decodeFile(unfocused.path)
        assertFalse("Retinting a focused control must not lose the focus indicator", tinted.sameAs(plain))
        compose.runOnIdle { activeDark = dark }; compose.waitForIdle()
        val cleared = File("build/screenshots/focus/${name}_cleared.png")
        landscapeCapture(compose, compose.activity, 411, 891, cleared)
        val c = BitmapFactory.decodeFile(cleared.path)
        assertTrue("Clearing focus restores the original pixels ($name)", a.sameAs(c))
        a.recycle(); b.recycle(); c.recycle(); tinted.recycle(); plain.recycle()

    }
    @Test fun sharedPressControlPaintsItsFocusRing() = verify("button")
    @Test fun listRowPaintsItsFocusRingFromItsOwnInteractionSource() = verify("row")
    @Test fun clickableVersePaintsItsFocusRing() = verify("verse")
    @Test fun searchClearActionPaintsItsFocusRing() = verify("clear")
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-dark={1}")
        fun matrix(): List<Array<Any>> = DesignLanguage.selectable.flatMap { id -> listOf(false, true).map { arrayOf<Any>(id, it) } }
    }
}
