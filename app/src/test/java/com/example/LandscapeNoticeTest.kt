package com.example

import androidx.activity.ComponentActivity
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.example.ui.design.*
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
class LandscapeNoticeTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private fun insets(ime: Boolean = false) { compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 411, ime) }; compose.waitForIdle() }
    private fun notice() = compose.onNodeWithText("Guardado").assertIsDisplayed()
        .assert(SemanticsMatcher.expectValue(SemanticsProperties.LiveRegion, LiveRegionMode.Polite))
    @Test fun fullWindowNoticeClearsTheFooterAndTheKeyboard() {
        compose.setContent { SanctaFidesTheme(reduceMotion = true) { AppNotice("Guardado", footerHeight = 56.dp) } }
        insets()
        assertTrue(notice().getUnclippedBoundsInRoot().bottom.value <= 411f - 16f - 56f - 8f + 1f)
        insets(true)
        assertTrue(notice().getUnclippedBoundsInRoot().bottom.value <= 411f * .44f - 8f + 1f)
    }
    @Test fun anAlreadyResizedColumnDoesNotApplyTheKeyboardInsetTwiceOrCoverItsAction() {
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            val focus = remember { FocusRequester() }
            AppSupportingPaneLayout(supporting = { AppLargeTitleHeader("Oración") }, supportingWidth = AppPaneWidth(.28f, 224.dp, 280.dp), mainFooter = {
                AppPrimaryButton("Siguiente", {}, modifier = Modifier.width(180.dp).testTag("notice_next"))
            }) {
                Box(Modifier.fillMaxSize().focusRequester(focus).focusable()) { AppNotice("Guardado", footerHeight = 56.dp) }
            }
            LaunchedEffect(Unit) { focus.requestFocus() }
        } }
        insets(true)
        val n = notice().getUnclippedBoundsInRoot()
        val action = compose.onNodeWithTag("notice_next").assertIsDisplayed().getUnclippedBoundsInRoot()
        assertTrue("Notice uses the already resized host", n.bottom.value > 100f)
        assertTrue("Notice stays above the fixed action", n.bottom.value <= action.top.value - 4f)
        assertTrue(action.bottom.value <= 411f * .44f + 1f)
    }
}
