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
import androidx.compose.ui.platform.testTag
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

    @Test fun readerAlignsTitleWithPrayerAndUsesCompactButtons() {
        launch(LandscapeShowcase.Reader)
        assertEquals(76f, dp(bounds("showcase_title").top), 4f)
        assertEquals(dp(bounds("showcase_title").top), dp(bounds("showcase_reading").top), 4f)
        assertEquals(44f, dp(bounds("showcase_reader_home").height), 1f)
        assertEquals(44f, dp(bounds("showcase_previous").height), 1f)
        assertEquals(48f, dp(bounds("showcase_next").height), 1f)
        assertTrue("The whole prayer is visible", dp(compose.onNodeWithTag("showcase_reading").getUnclippedBoundsInRoot().bottom.value * compose.density.density) <= dp(bounds("showcase_next").top))
    }

    @Test fun landscapeReaderDoesNotRenderItsSuppliedArtwork() {
        var asset by mutableStateOf<String?>("heroes/lumen/prayers_lily.jpg")
        compose.setContent {
            SanctaFidesTheme(reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    AppPageLayout(kind = AppPageKind.Reader, header = {
                        AppDetailHeader("Padre Nuestro", assetPath = asset, meta = "Pater Noster")
                    }, supporting = {
                        androidx.compose.material3.Text("Oración ▾", modifier = Modifier.testTag("reader_test_outline"))
                    }) { header ->
                        androidx.compose.foundation.lazy.LazyColumn {
                            item { header() }
                            item { androidx.compose.material3.Text("Padre nuestro, que estás en el cielo") }
                        }
                    }
                }
            }
        }
        insets()
        val before = java.io.File("build/screenshots/reader-no-art/with_asset.png")
        landscapeCapture(compose, compose.activity, 891, 411, before)
        val withAsset = android.graphics.BitmapFactory.decodeFile(before.path)
        compose.runOnIdle { asset = null }
        compose.waitForIdle()
        val after = java.io.File("build/screenshots/reader-no-art/without_asset.png")
        landscapeCapture(compose, compose.activity, 891, 411, after)
        val withoutAsset = android.graphics.BitmapFactory.decodeFile(after.path)
        assertTrue("Reader artwork must not change any rendered pixels in landscape", withAsset.sameAs(withoutAsset))
        withAsset.recycle()
        withoutAsset.recycle()
        assertTrue(dp(bounds("reader_test_outline").top - bounds("side_panel_header").bottom) <= 24f)
    }

    @Test fun enlargedReaderCanReachTheEndWithoutMovingItsAdvanceButton() {
        landscapeViewport(compose, compose.activity, 800, 360)
        compose.setContent { SanctaFidesTheme(designLanguage = DesignLanguage.APPLE.id, darkModeSetting = "LIGHT", fontScale = 1.3f, reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { LandscapeShowcaseScreen(LandscapeShowcase.Reader) }
        } }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 360) }; compose.waitForIdle()
        val before = bounds("showcase_next")
        val end = compose.onNodeWithText(showcasePrayers.first().whenToPray.get(AppLanguage.ES), useUnmergedTree = true)
        end.performScrollTo().assertIsDisplayed()
        assertTrue(end.getUnclippedBoundsInRoot().bottom.value <= dp(bounds("showcase_next").top))
        assertEquals(before, bounds("showcase_next"))
        compose.onNodeWithTag("showcase_next").assertIsDisplayed().assertHasClickAction()
    }

    @Test fun readerUsesFullWindowProportionAndClearsBothLateralInsets() {
        launch(LandscapeShowcase.Reader)
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 411, cutout = "left", navbar = "right") }; compose.waitForIdle()
        assertEquals(891f * .30f, dp(bounds("app_page_panel").width), 1f)
        assertTrue(dp(bounds("app_page_panel").left) >= 32f)
        assertTrue(dp(bounds("showcase_next").right) <= 891f - 48f - 8f)
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
    @Test fun supportingContextWithoutFooterCanScrollItsLastLineAboveTheNavigationBar() {
        launch(LandscapeShowcase.Supporting, DesignLanguage.SANCTUARY)
        val context = compose.onNodeWithText(showcasePrayers.first().whenToPray.get(AppLanguage.ES)).performScrollTo()
        assertTrue(context.getUnclippedBoundsInRoot().bottom.value <= 411f - 16f + 1f)
    }
    @Test fun readerPanelFooterReturnsToItsParentRoute() {
        launch(LandscapeShowcase.Reader)
        compose.onNodeWithTag("showcase_next").performClick()
        compose.onNodeWithTag("showcase_reader_home").performClick()
        compose.onNodeWithTag("showcase_exited").assertIsDisplayed()
    }
    @Test @Config(qualifiers = "w1280dp-h800dp-xhdpi") fun statefulListDetailKeepsTheTabletReadingColumnAt680Dp() {
        launch(LandscapeShowcase.ListDetail)
        assertEquals(360f, dp(bounds("two_pane_list").width), 1f)
        val header = compose.onNodeWithTag("showcase_detail_header").getUnclippedBoundsInRoot()
        assertEquals(680f, (header.right - header.left).value, 1f)
    }
    @Test @Config(qualifiers = "w600dp-h360dp-land-xhdpi") fun tightestLandscapeBoundaryRecordsTheIncompatibleMinimumWidths() {
        launch(LandscapeShowcase.Hub)
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 360, cutout = "left", navbar = "right") }; compose.waitForIdle()
        assertEquals(248f, dp(bounds("app_page_panel").width), 1f)
        // 600 - rail80 - cutout32 - navbar48 - panel248 = 192.
        // The approved 248 panel and 220 main minima cannot both fit here.
        assertEquals(192f, dp(bounds("app_page_content").width), 1f)
    }
    @Test fun coverArtworkDrawsUnderTheStatusBarAtRest() {
        launch(LandscapeShowcase.Hub)
        val strip = bounds("status_header_artwork")
        assertEquals(0f, dp(strip.top), 1f)
        assertEquals(24f, dp(strip.height), 1f)
        assertTrue(dp(bounds("showcase_primary").bottom) <= 411f - 16f)
    }
}
