package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.SideEffect
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import com.example.ui.theme.SanctaFidesTheme
import org.robolectric.annotation.GraphicsMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.example.ui.design.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class LandscapeFoundationsTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun heightWinsAndEveryBoundaryHasOneClass() {
        val cases = listOf(
            Triple(411, 891, AppWindowClass.Phone), Triple(599, 480, AppWindowClass.Phone),
            Triple(599, 479, AppWindowClass.CompactPhone), Triple(600, 479, AppWindowClass.LandscapePhone),
            Triple(891, 411, AppWindowClass.LandscapePhone), Triple(800, 360, AppWindowClass.LandscapePhone),
            Triple(600, 480, AppWindowClass.TabletPortrait), Triple(839, 1200, AppWindowClass.TabletPortrait),
            Triple(840, 480, AppWindowClass.TabletLandscape), Triple(1280, 800, AppWindowClass.TabletLandscape)
        )
        cases.forEach { (w, h, expected) -> assertEquals("${w}x$h", expected, AppWindowSize(w.dp, h.dp).windowClass) }
    }

    @Test fun fullWindowMetricsAndDispatchedInsetsAreIndependent() {
        var size: AppWindowSize? = null
        var safe: AppSafeInsets? = null
        compose.setContent {
            val actual = appWindowSize()
            val insets = appSafeInsets()
            SideEffect { size = actual; safe = insets }
        }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 411, cutout = "left", navbar = "right") }
        compose.waitForIdle()
        assertEquals(AppWindowClass.LandscapePhone, size!!.windowClass)
        assertEquals(891f, size!!.width.value, 1f)
        assertEquals(411f, size!!.height.value, 1f)
        assertEquals(24f, safe!!.top.value, 0.5f)
        assertEquals(32f, safe!!.start.value, 0.5f)
        assertEquals(48f, safe!!.end.value, 0.5f)
    }

    private fun inset(ime: Boolean = false) {
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 411, ime = ime) }
        compose.waitForIdle()
    }

    @Test fun compactBandRetainsTheCallersTagAndUses108Dp() {
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            AppHeroHeader("heroes/lumen/prayers_lily.jpg", "Oraciones", modifier = Modifier.testTag("original_header"))
        } }
        inset()
        compose.onNodeWithTag("original_header").assertExists()
        val bounds = compose.onNodeWithTag("compact_header").getUnclippedBoundsInRoot()
        assertEquals(108f, (bounds.bottom - bounds.top).value, 1f)
    }

    @Test @Config(qualifiers = "w800dp-h360dp-land-xhdpi") fun fiveDestinationsFitTheCompactRail() {
        val nav = listOf("Hoy", "Orar", "Leer", "Mi camino", "Explorar").mapIndexed { i, label -> AppNavItem(label, Icons.Outlined.Home, tag = "destination_$i") }
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            AppScaffold("Hoy", nav, 0, {}) { Box(Modifier.fillMaxSize()) }
        } }
        inset()
        val rail = compose.onNodeWithTag("navigation_rail").fetchSemanticsNode().boundsInRoot
        assertTrue("five rows fit 320 dp: $rail", rail.height / compose.density.density <= 320f)
        nav.forEach { compose.onNodeWithTag(it.tag).assertIsDisplayed().assertHasClickAction() }
    }

    @Test fun sheetKeepsItsTagAndMovesTheFooterAboveTheKeyboard() {
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            AppSheet({}, title = "Apariencia", tag = "example_sheet", footer = {
                AppPrimaryButton("Guardar", {}, modifier = Modifier.testTag("sheet_save"))
            }) { Text("Una elección para tu lectura") }
        } }
        inset()
        compose.onNodeWithTag("example_sheet").assertIsDisplayed()
        compose.onNodeWithTag("sheet_close").assertIsDisplayed()
        val before = compose.onNodeWithTag("sheet_save").fetchSemanticsNode().boundsInRoot
        inset(ime = true)
        val after = compose.onNodeWithTag("sheet_save").fetchSemanticsNode().boundsInRoot
        assertTrue("footer moved into header", after.top < before.top)
        assertTrue("action above 0.56 H keyboard", after.bottom / compose.density.density <= 411 * 0.44f + 1f)
    }

    @Test fun legacyDialogActionsStayVisibleWhileItsBodyScrolls() {
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            AppDialog({}, title = "Elige tu camino", tag = "example_dialog") {
                repeat(20) { Text("Una opción para comenzar $it", modifier = Modifier.height(44.dp)) }
                AppPrimaryButton("Comenzar", {}, modifier = Modifier.testTag("dialog_begin"))
            }
        } }
        inset()
        compose.onNodeWithTag("dialog_begin").assertIsDisplayed().assertHasClickAction()
        val bounds = compose.onNodeWithTag("example_dialog").fetchSemanticsNode().boundsInRoot
        assertTrue(bounds.height / compose.density.density <= 339f)
    }

    @Test fun aHeaderWithoutArtKeepsItsNaturalTitleBlock() {
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            val frame = androidx.compose.runtime.remember { ScreenFrame().apply {
                edgeToEdge = true; insets = PaddingValues(top = 24.dp)
            } }
            androidx.compose.runtime.CompositionLocalProvider(LocalScreenFrame provides frame) {
                AppLargeTitleHeader("Orar", modifier = Modifier.testTag("plain_header"))
            }
        } }
        inset()
        val bounds = compose.onNodeWithTag("plain_header").fetchSemanticsNode().boundsInRoot
        assertTrue("no empty 108 dp art strip: $bounds", bounds.height / compose.density.density < 108f)
        compose.onNodeWithText("Orar").assertIsDisplayed()
    }
}
