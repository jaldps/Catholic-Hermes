@file:OptIn(androidx.compose.ui.ExperimentalComposeUiApi::class, androidx.compose.ui.InternalComposeUiApi::class)

package com.example

import android.view.View
import android.app.Application
import android.content.ComponentName
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.test.core.app.ApplicationProvider
import org.robolectric.Shadows
import org.junit.After
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.design.*
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppTextStyles
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowDialog
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** An actual recreatable activity: onCreate reattaches the test route and Android restores its Bundle. */
class LandscapeRotationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { testContent?.invoke() }
    }
    companion object { var testContent by mutableStateOf<(@Composable () -> Unit)?>(null) }
}

/** Changes real window metrics and recreates the activity, without movable content. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class LandscapeRotationTest(private val identity: DesignLanguage) {
    private val registration = ApplicationProvider.getApplicationContext<Application>().let { app ->
        Shadows.shadowOf(app.packageManager).addActivityIfNotPresent(ComponentName(app, LandscapeRotationActivity::class.java)).apply { exported = true }
    }
    @get:Rule val compose = createAndroidComposeRule<LandscapeRotationActivity>()
    @After fun close() { compose.runOnUiThread { LandscapeRotationActivity.testContent = null }; compose.waitForIdle() }

    private fun start(kind: LandscapeShowcase) {
        compose.runOnUiThread { LandscapeRotationActivity.testContent = { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT", reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { LandscapeShowcaseScreen(kind) }
        } } }; compose.waitForIdle()
        insets(411)
    }
    private fun insets(height: Int) {
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, height) }; compose.waitForIdle()
    }
    private fun rotate(width: Int, height: Int) {
        val before = compose.activity
        RuntimeEnvironment.setQualifiers("w${width}dp-h${height}dp-${if (width > height) "land-" else ""}xhdpi")
        compose.activityRule.scenario.onActivity { }
        if (compose.activity === before) compose.activityRule.scenario.recreate()
        compose.waitForIdle()
        assertNotSame("Activity must actually recreate", before, compose.activity)
        compose.runOnIdle {
            val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
            view.layoutParams = view.layoutParams.apply { this.width = width * 2; this.height = height * 2 }
            view.measure(View.MeasureSpec.makeMeasureSpec(width * 2, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(height * 2, View.MeasureSpec.EXACTLY))
            view.layout(0, 0, width * 2, height * 2)
        }
        insets(height)
    }
    @Test fun elevatedSegmentAndQuerySurviveBothDirections() {
        start(LandscapeShowcase.Hub)
        compose.onNodeWithText("Marianas").performScrollTo().performClick()
        compose.onNode(hasSetTextAction()).performScrollTo().performTextInput("Ave")
        rotate(411, 891)
        compose.onNodeWithText("Marianas").assertIsSelected()
        compose.onNode(hasSetTextAction()).assertTextContains("Ave")
        rotate(891, 411)
        compose.onNodeWithText("Marianas").assertIsSelected()
        compose.onNode(hasSetTextAction()).assertTextContains("Ave")
        compose.onNodeWithText("Ave María").assertExists()
    }
    @Test fun defaultSelectionReturnsToListInPortrait() {
        start(LandscapeShowcase.ListDetail)
        compose.onNodeWithTag("showcase_detail_title").assertTextEquals("Padre Nuestro")
        rotate(411, 891)
        compose.onNodeWithTag("showcase_row_hail_mary").assertIsDisplayed()
        assertFalse(runCatching { compose.onNodeWithTag("showcase_detail_title").assertIsDisplayed() }.isSuccess)
    }
    @Test fun explicitStackedDetailBecomesSelectionAndBackLeavesTheLandscapeScreen() {
        start(LandscapeShowcase.ListDetail)
        rotate(411, 891)
        compose.onNodeWithTag("showcase_row_hail_mary").performClick()
        compose.onNodeWithTag("showcase_detail_title").assertTextEquals("Ave María")
        rotate(891, 411)
        compose.onNodeWithTag("showcase_row_hail_mary").assertIsSelected()
        compose.onNodeWithTag("showcase_detail_title").assertTextEquals("Ave María")
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("showcase_exited").assertIsDisplayed()
    }
    @Test fun explicitSelectionReturnsToStackAndPortraitBackReturnsToList() {
        start(LandscapeShowcase.ListDetail)
        compose.onNodeWithTag("showcase_row_hail_mary").performClick()
        rotate(411, 891)
        compose.onNodeWithTag("showcase_detail_title").assertIsDisplayed().assertTextEquals("Ave María")
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("showcase_row_hail_mary").assertIsDisplayed()
        compose.onNodeWithTag("showcase_exited").assertDoesNotExist()
    }
    @Test fun openSheetRestoresAlongsideItsElevatedQuery() {
        start(LandscapeShowcase.Hub)
        compose.onNode(hasSetTextAction()).performScrollTo().performTextInput("Ave")
        compose.onNodeWithTag("showcase_primary").performClick()
        compose.onNodeWithTag("showcase_prayer_sheet").assertIsDisplayed()
        rotate(411, 891)
        compose.onNodeWithTag("showcase_prayer_sheet").assertIsDisplayed()
        compose.runOnUiThread { (ShadowDialog.getLatestDialog() as androidx.activity.ComponentDialog).onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("showcase_prayer_sheet").assertDoesNotExist()
        compose.onNode(hasSetTextAction()).assertTextContains("Ave")
        rotate(891, 411)
        compose.onNode(hasSetTextAction()).assertTextContains("Ave")
    }
    @Test fun readingBlockAndFractionSurviveRotationAndFontChange() {
        var anchor: AppReadingAnchor? = null
        var scope: CoroutineScope? = null
        var scale by mutableFloatStateOf(1f)
        compose.runOnUiThread { LandscapeRotationActivity.testContent = { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT", fontScale = scale, reduceMotion = true) {
            val current = rememberReadingAnchor("rotation_reading")
            val coroutineScope = rememberCoroutineScope()
            SideEffect { anchor = current; scope = coroutineScope }
            AppPageLayout(header = { AppDetailHeader("Padre Nuestro", assetPath = "heroes/lumen/prayers_lily.jpg") }, kind = AppPageKind.Reader) { header ->
                LazyColumn(Modifier.fillMaxSize().testTag("anchor_list"), state = current.listState) {
                    item(key = "header") { header() }
                    items(20, key = { "prayer_$it" }) { index ->
                        Text(showcasePrayers[index % showcasePrayers.size].body(AppLanguage.ES), style = LocalAppTextStyles.current.reading,
                            modifier = Modifier.fillMaxWidth().padding(24.dp))
                    }
                }
            }
        } } }; compose.waitForIdle()
        insets(411)
        compose.onNodeWithTag("anchor_list").performScrollToIndex(7)
        val itemHeight = compose.runOnIdle { anchor!!.listState.layoutInfo.visibleItemsInfo.first { it.index == 7 }.size }
        compose.runOnIdle { scope!!.launch { anchor!!.listState.scrollToItem(7, (itemHeight * 0.45f).toInt()) } }
        compose.waitForIdle()
        val before = compose.runOnIdle { anchor!!.index to anchor!!.fraction }
        assertEquals(7, before.first)
        assertEquals(0.45f, before.second, 0.02f)
        rotate(411, 891)
        assertEquals(before.first, compose.runOnIdle { anchor!!.index })
        assertEquals(before.second, compose.runOnIdle { anchor!!.fraction }, 0.06f)
        rotate(891, 411)
        compose.runOnIdle { scale = 1.3f }; compose.waitForIdle()
        assertEquals(before.first, compose.runOnIdle { anchor!!.index })
        assertEquals(before.second, compose.runOnIdle { anchor!!.fraction }, 0.06f)
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun identities() = listOf(arrayOf(DesignLanguage.APPLE), arrayOf(DesignLanguage.SANCTUARY))
    }
}
