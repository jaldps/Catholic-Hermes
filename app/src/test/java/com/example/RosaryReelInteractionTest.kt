package com.example

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.example.ui.components.BeadKind
import com.example.ui.components.BeadReel
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
class RosaryReelInteractionTest {
    @get:Rule val compose = createComposeRule()
    private var bead by mutableIntStateOf(10)
    private fun launch() { compose.setContent { SanctaFidesTheme(reduceMotion = true) {
        BeadReel(List(30) { BeadKind.SMALL }, bead, { bead = it },
            Modifier.width(44.dp).height(203.dp).testTag("reel"), orientation = Orientation.Vertical)
    } } }
    @Test fun upwardDragAdvancesExactlyOneBeadPer24DpAndDownwardReverses() {
        launch()
        compose.onNodeWithTag("reel").performTouchInput { down(center); moveBy(Offset(0f, -24.dp.toPx())); up() }
        compose.runOnIdle { assertEquals(11, bead) }
        compose.onNodeWithTag("reel").performTouchInput { down(center); moveBy(Offset(0f, 72.dp.toPx())); up() }
        compose.runOnIdle { assertEquals(8, bead) }
    }
    @Test fun tapsOnlyMoveOneBeadAndTheCurrent44DpZoneDoesNothing() {
        launch()
        compose.onNodeWithTag("reel").performTouchInput { click(Offset(centerX, 1f)) }
        compose.runOnIdle { assertEquals(9, bead) }
        compose.onNodeWithTag("reel").performTouchInput { click(center) }
        compose.runOnIdle { assertEquals(9, bead) }
        compose.onNodeWithTag("reel").performTouchInput { click(Offset(centerX, height - 1f)) }
        compose.runOnIdle { assertEquals(10, bead) }
    }
    @Test fun adjustableSemanticsExposeTheFullRangeAndSupportLongJumps() {
        launch()
        compose.onNodeWithTag("reel").performSemanticsAction(SemanticsActions.SetProgress) { assertTrue(it(24f)) }
        compose.runOnIdle { assertEquals(24, bead) }
        val range = compose.onNodeWithTag("reel").fetchSemanticsNode().config[SemanticsProperties.ProgressBarRangeInfo]
        assertEquals(24f, range.current, .001f)
        assertEquals(0f..29f, range.range)
        compose.onNodeWithTag("reel").performSemanticsAction(SemanticsActions.SetProgress) { assertFalse(it(Float.NaN)) }
    }
}
