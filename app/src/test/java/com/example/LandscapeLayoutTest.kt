package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.design.*
import com.example.data.models.FontChoice
import com.example.data.models.AppLanguage
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.TextLayoutResult
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class LandscapeLayoutTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private fun launch(kind: LandscapeShowcase, identity: DesignLanguage = DesignLanguage.APPLE) {
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT", reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) { LandscapeShowcaseScreen(kind) }
            }
        }
        insets()
    }
    private fun insets(ime: Boolean = false) {
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 411, ime) }
        compose.waitForIdle()
    }
    private fun bounds(tag: String) = compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot
    private fun dp(px: Float) = px / compose.density.density

    @Test fun hubPanelUsesApprovedWidthAndTitleBaseline() {
        launch(LandscapeShowcase.Hub)
        assertEquals(292f, dp(bounds("app_page_panel").width), 1f)
        val title = compose.onNodeWithTag("showcase_title").fetchSemanticsNode()
        val results = mutableListOf<TextLayoutResult>()
        title.config[SemanticsActions.GetTextLayoutResult].action!!.invoke(results)
        assertEquals(0.46f * 411f - 14f, dp(title.boundsInRoot.top + results.single().lastBaseline), 1f)
        compose.onNodeWithTag("showcase_segments").assertIsDisplayed()
        compose.onNodeWithTag("showcase_primary").assertIsDisplayed().assertHasClickAction()
    }

    @Test fun readerHidesRailAndKeepsNavigationAboveTheSafeBottom() {
        launch(LandscapeShowcase.Reader)
        compose.onNodeWithTag("navigation_rail").assertDoesNotExist()
        assertEquals(267.3f, dp(bounds("app_page_panel").width), 1f)
        val next = bounds("showcase_next")
        assertTrue(dp(next.bottom) <= 411f - 16f)
        assertTrue(891f - dp(next.center.x) <= 120f)
        assertTrue(dp(next.center.y) >= 0.6f * 411f)
        compose.onNodeWithTag("showcase_next").performClick()
        compose.onNodeWithTag("showcase_title").assertTextEquals("Ave María")
    }

    @Test fun keyboardOnlyResizesTheFocusedSupportingLayoutsMainColumn() {
        launch(LandscapeShowcase.Supporting)
        val before = bounds("app_supporting_panel")
        val main = bounds("app_supporting_main")
        compose.onNode(hasSetTextAction()).performTextInput("Padre")
        insets(ime = true)
        assertEquals(before, bounds("app_supporting_panel"))
        assertTrue(bounds("app_supporting_main").height < main.height)
        val field = compose.onNode(hasSetTextAction()).fetchSemanticsNode().boundsInRoot
        assertTrue(dp(field.bottom) <= 411f * 0.44f + 1f)
        assertTrue(dp(bounds("showcase_primary").bottom) <= 411f * 0.44f + 1f)
    }

    @Test fun listDetailKeepsSearchStickyAndOpensSelectedPrayerInFullReader() {
        launch(LandscapeShowcase.ListDetail)
        assertEquals(292f, dp(bounds("two_pane_list").width), 1f)
        assertEquals(96f, dp(bounds("list_art_strip").height), 1f)
        assertTrue(dp(bounds("list_sticky_bar").height) <= 56f)
        compose.onNodeWithTag("list_filter_button").assertIsDisplayed().performClick()
        compose.onNodeWithTag("showcase_filter_sheet").assertIsDisplayed()
        compose.onNodeWithTag("sheet_close").performClick()
        compose.onNodeWithTag("showcase_row_hail_mary").performClick()
        compose.onNodeWithTag("showcase_detail_title").assertTextEquals("Ave María")
        compose.onNodeWithTag("detail_open_reader").performClick()
        compose.onNodeWithTag("showcase_title").assertTextEquals("Ave María")
        compose.onNodeWithTag("showcase_next").assertIsDisplayed()
    }

    @Test @Config(qualifiers = "w800dp-h360dp-land-xhdpi") fun compactBandUsesExactlyThirtyPercentAtTheShortRequiredSize() {
        compose.setContent { SanctaFidesTheme(reduceMotion = true) { AppHeroHeader("heroes/lumen/prayers_lily.jpg", "Oraciones") } }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 360) }; compose.waitForIdle()
        val b = compose.onNodeWithTag("compact_header").getUnclippedBoundsInRoot()
        val h = (b.bottom - b.top).value
        assertEquals(108f, h, 1f); assertTrue(h / 360f <= .30f)
    }
    @Test @Config(qualifiers = "w891dp-h320dp-land-xhdpi") fun minimumSupportedHeightRecordsTheApprovedBandsNumericalException() {
        compose.setContent { SanctaFidesTheme(reduceMotion = true) { AppHeroHeader("heroes/lumen/prayers_lily.jpg", "Oraciones") } }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 320) }; compose.waitForIdle()
        val b = compose.onNodeWithTag("compact_header").getUnclippedBoundsInRoot()
        assertEquals(.3375f, (b.bottom - b.top).value / 320f, .001f)
    }
    @Test @Config(qualifiers = "w320dp-h360dp-xhdpi") fun compactPhoneClampsTheSheetToItsActualSafeWidth() {
        compose.setContent { SanctaFidesTheme(reduceMotion = true) { AppSheet({}, title = "Oraciones", tag = "narrow_sheet") { androidx.compose.material3.Text("Padre Nuestro") } } }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 360) }; compose.waitForIdle()
        val b = compose.onNodeWithTag("narrow_sheet").getUnclippedBoundsInRoot()
        assertEquals(304f, (b.right - b.left).value, 1f)
    }
    @Test @Config(qualifiers = "w411dp-h891dp-xhdpi") fun portraitHeaderScrollsAwayInsteadOfRemainingAsAnOverlay() {
        launch(LandscapeShowcase.Hub)
        compose.onNodeWithTag("showcase_title").assertIsDisplayed()
        compose.onNodeWithTag("showcase_hub_list").performScrollToIndex(1)
        assertFalse(runCatching { compose.onNodeWithTag("showcase_title").assertIsDisplayed() }.isSuccess)
        compose.onNodeWithTag("showcase_hub_list").performScrollToIndex(0)
        compose.onNodeWithTag("showcase_title").assertIsDisplayed()
    }
    @Test fun readingMeasureTracksTheUsersRealFontAndScale() {
        var font by mutableStateOf(FontChoice.EB_GARAMOND.id)
        var scale by mutableFloatStateOf(1f)
        var measure = 0f
        compose.setContent { SanctaFidesTheme(fontFamilyChoice = font, fontScale = scale, reduceMotion = true) {
            val actual = appReadingMeasure(AppLanguage.ES)
            SideEffect { measure = actual.value }
        } }
        insets()
        val serif = compose.runOnIdle { measure }
        compose.runOnIdle { font = FontChoice.INTER.id }; compose.waitForIdle()
        val sans = compose.runOnIdle { measure }
        assertTrue("Named typefaces must affect the reading measure", kotlin.math.abs(sans - serif) > 10f)
        compose.runOnIdle { scale = 1.3f }; compose.waitForIdle()
        assertEquals("The measure follows effective user size", sans * 1.3f, compose.runOnIdle { measure }, 2f)
    }
}
