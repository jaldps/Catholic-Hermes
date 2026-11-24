package com.example

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.CatechismScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L5 5/7: Catechism index and reading pane side by side in landscape. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class CatechismLandscapeL5Test {
    @get:Rule val compose = createComposeRule()

    @Test fun catechismUsesTwoPanesWithStickySearch() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext())
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CatechismScreen(repository, AppLanguage.ES)
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("list_sticky_bar").assertIsDisplayed()
        compose.onNodeWithTag("ccc_search").assertIsDisplayed()
    }

    @Test fun openingASearchResultKeepsTheTwoPanes() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext())
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CatechismScreen(repository, AppLanguage.ES)
            }
        }
        compose.waitForIdle()
        compose.onAllNodes(SemanticsMatcher("editable search") { node ->
            node.config.contains(SemanticsProperties.EditableText)
        })[0].performTextInput("Dios")
        compose.waitUntil(timeoutMillis = 15_000) {
            compose.onAllNodes(SemanticsMatcher("a search result") { node ->
                node.config.contains(SemanticsProperties.TestTag) &&
                    node.config[SemanticsProperties.TestTag].startsWith("catechism_search_result_")
            }, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        compose.onAllNodes(SemanticsMatcher("a search result") { node ->
            node.config.contains(SemanticsProperties.TestTag) &&
                node.config[SemanticsProperties.TestTag].startsWith("catechism_search_result_")
        })[0].performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("two_pane_detail").assertIsDisplayed()
    }
}
