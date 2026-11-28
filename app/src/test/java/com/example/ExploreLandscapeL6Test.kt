package com.example

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.ui.screens.MoreScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L6 6/6: Explore uses a Hub panel, search/start here above and a responsive section grid. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class ExploreLandscapeL6Test {
    @get:Rule val compose = createComposeRule()

    @Test fun exploreUsesPanelAndSectionGrid() {
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                MoreScreen(AppLanguage.ES, emptyList())
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("app_page_panel").assertExists()
        compose.onNodeWithTag("more_title").assertExists()
        compose.onNodeWithTag("explore_start_here").assertExists()
        compose.onNodeWithTag("more_sections_grid").assertExists()
    }
}
