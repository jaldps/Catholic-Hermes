package com.example

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
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

/** L5 4/7: 101 preguntas in landscape keeps the list beside the answer. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class QuestionsLandscapeL5Test {
    @get:Rule val compose = createComposeRule()

    private fun setContent() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext())
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                DoctrineAndHistoryScreen(repository, AppLanguage.ES, initialTopTab = 3)
            }
        }
        compose.waitForIdle()
    }

    @Test fun questionsUseTwoPanesWithStickySearchAndTopicFilter() {
        setContent()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("list_sticky_bar").assertIsDisplayed()
        compose.onNodeWithTag("questions_search").assertIsDisplayed()
        compose.onNodeWithTag("list_filter_button").performClick()
        compose.onNodeWithTag("questions_filter_sheet").assertIsDisplayed()
        compose.onNodeWithTag("questions_section_picker").assertIsDisplayed()
        compose.onNodeWithTag("sheet_close").performClick()
    }

    @Test fun selectingAQuestionDoesNotPushAnotherPane() {
        setContent()
        compose.onAllNodes(SemanticsMatcher("editable search") { node ->
            node.config.contains(SemanticsProperties.EditableText)
        })[0].performTextInput("Dios")
        compose.waitForIdle()
        compose.onAllNodes(SemanticsMatcher("a question row") { node ->
            node.config.contains(SemanticsProperties.TestTag) &&
                node.config[SemanticsProperties.TestTag].startsWith("catholic_question_")
        })[0].performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("two_pane_detail").assertIsDisplayed()
    }
}
