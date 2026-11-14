package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.ui.components.CalendarPickerSheet
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk=[34],qualifiers="w411dp-h891dp-xhdpi")
class CalendarPickerInteractionTest {
    @get:Rule val compose=createAndroidComposeRule<ComponentActivity>()
    private var provider:FakeCalendarProvider?=null
    @After fun close(){compose.runOnUiThread{compose.activity.setContent{}};compose.waitForIdle();provider?.database?.close()}
    @Test fun calendarSelectionRequiresAnExplicitConfirmation() {
        provider=FakeCalendarProvider.install(ApplicationProvider.getApplicationContext<Application>())
        var chosen:Long?=null
        compose.setContent {SanctaFidesTheme(designLanguage="LUMEN",darkModeSetting="LIGHT",reduceMotion=true) {
            CalendarPickerSheet(AppLanguage.ES,null,{chosen=it},{})
        }}
        try {
            compose.waitUntil(10_000) {compose.runOnUiThread { androidx.compose.runtime.snapshots.Snapshot.sendApplyNotifications() }; compose.onAllNodesWithTag("calendar_option_2").fetchSemanticsNodes().isNotEmpty()}
        } catch (error: Throwable) {
            compose.onNodeWithTag("calendar_picker_sheet", useUnmergedTree = true).printToLog("CalendarPicker")
            println(compose.onNodeWithTag("calendar_picker_sheet", useUnmergedTree = true).printToString())
            throw error
        }
        compose.onNodeWithTag("confirm_calendar_choice").performScrollTo().assertIsNotEnabled()
        compose.onNodeWithTag("calendar_option_2").performScrollTo().performClick()
        assertNull(chosen)
        compose.onNodeWithTag("confirm_calendar_choice").performScrollTo().performClick()
        assertEquals(2L,chosen)
        assertEquals(0,provider!!.count("events"))
    }
}
