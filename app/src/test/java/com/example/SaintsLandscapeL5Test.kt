package com.example

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.ui.screens.SaintsScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L5: in landscape, selecting a saint changes the detail pane without pushing a page. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class SaintsLandscapeL5Test {
    @get:Rule val compose = createComposeRule()

    @Test fun selectionStaysInTwoPanesAndBackLeavesTheScreen() {
        var exited = false
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                SaintsScreen(AppLanguage.ES, initialKey = "basil_the_great_and_gregory_nazianzen_bishops", onExitContext = { exited = true })
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("list_filter_button").assertIsDisplayed()
        compose.onNodeWithTag("saint_detail").assertIsDisplayed()
        compose.onNodeWithTag("saint_row_most_holy_name_of_jesus").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        assert(!exited)
    }

    @Test
    @Config(sdk = [34], qualifiers = "w411dp-h891dp")
    fun filtersOpenInSheetAndBackReturnsToTheList() {
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                SaintsScreen(AppLanguage.ES)
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("saints_month_picker").assertDoesNotExist()
        compose.onNodeWithTag("list_filter_button").performClick()
        compose.onNodeWithTag("saints_filter_sheet").assertIsDisplayed()
        compose.onNodeWithTag("saints_month_picker").performClick()
        compose.onNodeWithTag("saints_month_1").performClick()
        compose.onNodeWithTag("sheet_close").performClick()
        compose.onNodeWithTag("saint_row_most_holy_name_of_jesus").performClick()
        compose.onNodeWithTag("saint_detail").assertIsDisplayed()
        compose.onNodeWithTag("saint_detail_back").performClick()
        compose.onNodeWithTag("saints_screen").assertIsDisplayed()
    }
}
