package com.example

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.Lifecycle
import com.example.ui.design.appReduceMotion
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowValueAnimator

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class SystemReduceMotionTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private fun durationScale(value: Float) {
        ShadowValueAnimator::class.java.getDeclaredMethod("setDurationScale", java.lang.Float.TYPE).apply { isAccessible = true }.invoke(null, value)
    }
    @After fun restore() { durationScale(1f) }
    private fun launch(preference: Boolean) {
        compose.setContent { SanctaFidesTheme(reduceMotion = appReduceMotion(preference)) {
            Text(LocalAppDesign.current.reduceMotion.toString(), modifier = Modifier.testTag("motion_effective"))
        } }
    }
    @Test fun systemDisabledAnimationsReachTheActualDesignLocal() {
        durationScale(0f); launch(false)
        compose.onNodeWithTag("motion_effective").assertTextEquals("true")
    }
    @Test fun returningToTheAppRefreshesTheSystemSettingInBothDirections() {
        durationScale(1f); launch(false)
        compose.onNodeWithTag("motion_effective").assertTextEquals("false")
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        durationScale(0f)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        compose.onNodeWithTag("motion_effective").assertTextEquals("true")
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        durationScale(1f)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        compose.onNodeWithTag("motion_effective").assertTextEquals("false")
    }
    @Test fun appPreferenceStillDisablesMotionWhenSystemAnimationsAreEnabled() {
        durationScale(1f); launch(true)
        compose.onNodeWithTag("motion_effective").assertTextEquals("true")
    }
}
