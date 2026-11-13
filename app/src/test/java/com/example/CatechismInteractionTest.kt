package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.LocalPreferencesStore
import com.example.ui.screens.CatechismScreen
import com.example.ui.screens.compendiumGroups
import com.example.ui.screens.firstCccRange
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Assert.assertTrue
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The Catechism's flows, end to end on the screen itself: it opens on the book,
 * a § number or a topic finds tappable results, a deep link lands on (and marks)
 * its paragraph, the contents sheet reaches any paragraph, back pops one page
 * at a time, and the Compendium opens its answers.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class CatechismInteractionTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val context get() = ApplicationProvider.getApplicationContext<Application>()

    private val repositories = mutableListOf<CatholicDataRepository>()
    @After fun closeRepositories() { repositories.forEach { it.close() } }

    private fun show(initialParagraph: Int? = null, language: AppLanguage = AppLanguage.EN) {
        val repository = CatholicDataRepository(context).apply { setLanguage(language); repositories.add(this) }
        val store = UserPreferencesStore(context)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "APPLE", darkModeSetting = "LIGHT") {
                CompositionLocalProvider(
                    LocalInspectionMode provides true,
                    LocalPreferencesStore provides store
                ) {
                    CatechismScreen(
                        repository = repository,
                        currentLanguage = language,
                        initialParagraph = initialParagraph
                    )
                }
            }
        }
        compose.waitForIdle()
    }

    private fun tag(t: String) = compose.onNodeWithTag(t, useUnmergedTree = true)

    private fun exists(t: String) =
        compose.onAllNodesWithTag(t, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun waitForTag(t: String) {
        compose.waitUntil(10_000) { exists(t) }
    }

    private fun pressBack() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    @Test fun opensOnTheBookNotOnALinkList() {
        show()
        tag("catechism_screen").assertIsDisplayed()
        tag("catechism_hero_photo").assertIsDisplayed()
        tag("ccc_search_input").assertIsDisplayed()
        tag("catechism_part_1").assertIsDisplayed()
    }

    @Test fun aParagraphNumberOpensItsText() {
        show()
        tag("ccc_search_input").performTextInput("§1324")
        waitForTag("catechism_search_result_1324")
        tag("catechism_search_result_1324").performClick()
        waitForTag("catechism_article_1324")
        compose.onNodeWithContentDescription("Current paragraph § 1324").assertExists()
        tag("catechism_reader_scroll").performScrollToNode(hasTestTag("catechism_cross_references"))
        tag("catechism_cross_references").assertIsDisplayed()
    }

    @Test fun aTopicListsTappableResults() {
        show()
        tag("ccc_search_input").performTextInput("sanctifying grace")
        waitForTag("catechism_search_count")
        tag("catechism_search_paragraph_numbers").assertExists()
        // Result rows carry per-paragraph tags; open the first.
        val firstTag = (1..2865).firstOrNull { exists("catechism_search_result_$it") }
        assertTrue("a result row exists", firstTag != null)
        tag("catechism_search_result_$firstTag").performScrollTo().performClick()
        waitForTag("catechism_article_$firstTag")
    }

    @Test fun anUnknownTopicShowsADesignedEmptyState() {
        show()
        tag("ccc_search_input").performTextInput("zzqxv")
        compose.waitUntil(10_000) {
            compose.onAllNodesWithTag("catechism_search_count", useUnmergedTree = true).fetchSemanticsNodes().isEmpty() &&
                !exists("ccc_searching")
        }
        tag("search_clear_button").assertExists()
        tag("search_clear_button").performClick()
        compose.waitForIdle()
        tag("catechism_part_1").assertExists()
    }

    @Test fun aDeepLinkLandsOnAndMarksItsParagraph() {
        show(initialParagraph = 1324)
        waitForTag("catechism_article_1324")
        compose.onNodeWithContentDescription("Current paragraph § 1324").assertExists()
        tag("catechism_reading_progress").assertExists()
        // The section header names the real section, once.
        tag("catechism_reader_scroll").performScrollToNode(hasTestTag("catechism_cross_references"))
        tag("catechism_cross_references").assertIsDisplayed()
    }

    @Test fun contentsSheetReachesAnyParagraph() {
        show(initialParagraph = 1324)
        waitForTag("catechism_contents_button")
        tag("catechism_contents_button").performClick()
        waitForTag("catechism_contents_sheet")
        tag("catechism_goto_input").performTextInput("2000")
        tag("catechism_goto_button").performClick()
        waitForTag("catechism_article_2000")
        compose.onNodeWithContentDescription("Current paragraph § 2000").assertExists()
    }

    @Test fun outOfRangeGoToShowsAHint() {
        show(initialParagraph = 1324)
        tag("catechism_contents_button").performClick()
        waitForTag("catechism_goto_input")
        tag("catechism_goto_input").performTextInput("9999")
        tag("catechism_goto_button").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("catechism_contents_sheet", useUnmergedTree = true).assertExists()
    }

    @Test fun appearanceSheetOpensTheSharedReadingSettings() {
        show(initialParagraph = 1)
        tag("catechism_appearance_button").performClick()
        waitForTag("reading_settings_sheet")
    }

    @Test fun nextSectionMovesTheReaderOn() {
        show(initialParagraph = 1)
        compose.onNodeWithTag("catechism_screen").assertExists()
        // Scroll the lazy list to its last item (the section footer).
        compose.onNode(androidx.compose.ui.test.hasScrollAction())
            .performScrollToNode(hasTestTag("paragraph_next_button"))
        tag("paragraph_next_button").performClick()
        // The next outline section starts at § 26.
        waitForTag("catechism_article_26")
    }

    @Test fun backPopsOnePageAtATime() {
        show()
        tag("catechism_part_2").performClick()
        waitForTag("catechism_section_1066")
        tag("catechism_section_1210").performClick()
        waitForTag("catechism_article_1210")
        pressBack()
        waitForTag("catechism_section_1210")
        pressBack()
        waitForTag("catechism_part_2")
    }

    @Test fun compendiumRowsCollapseIdenticalAnswersAndOpen() {
        val repository = CatholicDataRepository(context)
        val big = compendiumGroups(repository.compendiumQAEntries).maxBy { it.size }
        show()
        compose.onNode(androidx.compose.ui.test.hasScrollAction())
            .performScrollToNode(hasTestTag("catechism_open_compendium"))
        tag("catechism_open_compendium").performClick()
        waitForTag("compendium_search_input")
        // Searching by a number inside a collapsed range finds its row.
        val inside = (big.first + big.last) / 2
        tag("compendium_search_input").performTextInput(inside.toString())
        waitForTag("compendium_q_${big.first}")
        tag("compendium_q_${big.first}").performClick()
        // The answer opens, with a way into the Catechism at its reference.
        val ref = firstCccRange(big.entry.cccReference)!!.first
        waitForTag("compendium_open_ccc_$ref")
        tag("compendium_open_ccc_$ref").performClick()
        waitForTag("catechism_article_$ref")
    }
}
