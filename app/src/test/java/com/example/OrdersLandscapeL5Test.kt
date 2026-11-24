package com.example

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.DoctrineAndHistoryScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L5 2/7: Órdenes in landscape keeps the list beside the detail and exposes search + filters. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class OrdersLandscapeL5Test {
    @get:Rule val compose = createComposeRule()

    @Test fun ordersUseTwoPanesWithStickySearchAndFilters() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext())
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                DoctrineAndHistoryScreen(repository, AppLanguage.ES, initialTopTab = 4)
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("list_sticky_bar").assertIsDisplayed()
        compose.onNodeWithTag("orders_search").assertIsDisplayed()
        compose.onNodeWithTag("list_filter_button").performClick()
        compose.onNodeWithTag("orders_filter_sheet").assertIsDisplayed()
        compose.onNodeWithTag("sheet_close").performClick()
    }

    @Test fun selectingAnOrderDoesNotPushAnotherPane() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext())
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                DoctrineAndHistoryScreen(repository, AppLanguage.ES, initialTopTab = 4)
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("order_jesuitas-sj").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("two_pane_detail").assertIsDisplayed()
    }
}
