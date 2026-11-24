package com.example

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.search.GlobalSearch
import com.example.ui.screens.SearchScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L5 7/7: Search in landscape is single-pane with a 56 dp sticky bar. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class SearchLandscapeL5Test {
    @get:Rule val compose = createComposeRule()

    @Test fun searchUsesStickyBarWithoutASecondPane() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext())
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                SearchScreen(AppLanguage.ES, GlobalSearch(repository, emptyList())) {}
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("search_screen").assertIsDisplayed()
        compose.onNodeWithTag("list_sticky_bar").assertIsDisplayed()
        compose.onNodeWithTag("search_field").assertIsDisplayed()
        compose.onNodeWithTag("two_pane").assertDoesNotExist()
    }
}
