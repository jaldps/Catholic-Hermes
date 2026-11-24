package com.example

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.SemanticsMatcher
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

/** L5 3/7: Devociones in landscape keeps list and detail side by side. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class DevotionsLandscapeL5Test {
    @get:Rule val compose = createComposeRule()

    private fun setContent() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext())
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                DoctrineAndHistoryScreen(repository, AppLanguage.ES, initialTopTab = 1)
            }
        }
        compose.waitForIdle()
    }

    @Test fun devotionsUseTwoPanesWithStickySearchAndFilters() {
        setContent()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("list_sticky_bar").assertIsDisplayed()
        compose.onNodeWithTag("devotions_search").assertIsDisplayed()
        compose.onNodeWithTag("list_filter_button").performClick()
        compose.onNodeWithTag("devotions_filter_sheet").assertIsDisplayed()
        compose.onNodeWithTag("devotions_category_picker").assertIsDisplayed()
        compose.onNodeWithTag("sheet_close").performClick()
    }

    @Test fun selectingADevotionDoesNotPushAnotherPane() {
        setContent()
        compose.onAllNodes(SemanticsMatcher("a devotion card") { node ->
            node.config.contains(SemanticsProperties.TestTag) &&
                node.config[SemanticsProperties.TestTag].startsWith("devotion_card_")
        })[0].performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("two_pane_detail").assertIsDisplayed()
    }
}
