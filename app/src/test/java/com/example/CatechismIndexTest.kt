package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatechismStructureCatalog
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.LocalPreferencesStore
import com.example.ui.screens.CatechismScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.FileNotFoundException

/**
 * The Catechism's navigable index: Part → Section → Chapter → Article unfold in place, a leaf (or "Read" on any
 * node) opens the reader on that node's range, and the reader names the place of the paragraph it is on.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class CatechismIndexTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val context get() = ApplicationProvider.getApplicationContext<Application>()
    private val repositories = mutableListOf<CatholicDataRepository>()

    @After fun cleanUp() {
        CatechismStructureCatalog.override = null
        repositories.forEach { it.close() }
    }

    private fun show(language: AppLanguage = AppLanguage.ES, initialParagraph: Int? = null) {
        val repository = CatholicDataRepository(context).apply { setLanguage(language); repositories.add(this) }
        val store = UserPreferencesStore(context)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "APPLE", darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true, LocalPreferencesStore provides store) {
                    CatechismScreen(repository = repository, currentLanguage = language, initialParagraph = initialParagraph)
                }
            }
        }
        compose.waitForIdle()
    }

    private fun tag(t: String) = compose.onNodeWithTag(t, useUnmergedTree = true)
    private fun exists(t: String) = compose.onAllNodesWithTag(t, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    private fun waitForTag(t: String) = compose.waitUntil(10_000) { exists(t) }
    private fun click(t: String) {
        // Pinned pieces (the breadcrumb) sit outside the scrolling list.
        runCatching { tag(t).performScrollTo() }
        tag(t).performClick(); compose.waitForIdle()
    }
    private fun crumb(): String =
        tag("ccc_breadcrumb").fetchSemanticsNode().config[SemanticsProperties.ContentDescription].joinToString(" ")
    private fun scrollReaderTo(paragraph: Int) {
        tag("catechism_reader_scroll").performScrollToNode(hasTestTag("catechism_article_$paragraph"))
        compose.waitForIdle()
    }

    @Test fun theIndexUnfoldsToTheBaptismArticleAndTheReaderStartsAtItsFirstParagraph() {
        show()
        tag("ccc_index").assertIsDisplayed()
        // Prologue and the four parts, each with its range.
        listOf("prologue", "p1", "p2", "p3", "p4").forEach { tag("ccc_node_$it").assertExists() }
        click("catechism_part_2")
        waitForTag("ccc_tree")
        click("ccc_node_p2s2")
        click("ccc_node_p2s2c1")
        click("ccc_node_p2s2c1a1")
        waitForTag("catechism_article_1213")
        // The range is the article's: nothing of the chapter's opening before it.
        assertTrue(!exists("catechism_article_1212"))
        tag("ccc_breadcrumb").assertIsDisplayed()
        assertEquals(
            "Ubicación: Parte 2, Sección 2, Capítulo 1, Artículo 1 · El sacramento del bautismo",
            crumb()
        )
    }

    @Test fun theBreadcrumbFollowsTheScrollAcrossArticles() {
        show()
        click("catechism_part_2")
        click("ccc_node_p2s2")
        click("ccc_node_p2s2c1")
        // "Read" on the chapter covers its three articles.
        click("ccc_read_p2s2c1")
        waitForTag("catechism_article_1212")
        assertTrue(crumb().contains("Capítulo 1"))
        scrollReaderTo(1300)
        compose.waitUntil(5_000) { crumb().contains("Artículo 2") }
        assertTrue(crumb().contains("confirmación"))
        scrollReaderTo(1400)
        compose.waitUntil(5_000) { crumb().contains("Artículo 3") }
    }

    @Test fun nextReadingFollowsTheTreeAndTheParagraphJumpStillWorks() {
        show()
        click("catechism_part_2"); click("ccc_node_p2s2"); click("ccc_node_p2s2c1"); click("ccc_node_p2s2c1a1")
        waitForTag("catechism_article_1213")
        compose.onNode(hasScrollAction()).performScrollToNode(hasTestTag("paragraph_next_button"))
        // Past the floating action bar, as a thumb would.
        tag("catechism_reader_scroll").performTouchInput { swipeUp() }
        compose.waitForIdle()
        tag("paragraph_next_button").performClick()
        waitForTag("catechism_article_1285")
        assertTrue(crumb().contains("Artículo 2"))
        // The contents sheet's "go to §" is unchanged and the breadcrumb follows it into another part.
        click("catechism_contents_button")
        waitForTag("catechism_goto_input")
        tag("catechism_goto_input").performTextInput("2084")
        tag("catechism_goto_button").performClick()
        waitForTag("catechism_article_2084")
        compose.waitUntil(5_000) { crumb().contains("Parte 3") }
        assertTrue(crumb().contains("Artículo 1"))
    }

    @Test fun aDeepLinkStillOpensTheReaderAndTheBreadcrumbNamesItsPlace() {
        show(initialParagraph = 2746)
        waitForTag("catechism_article_2746")
        compose.waitUntil(5_000) { exists("ccc_breadcrumb") }
        assertTrue(crumb().contains("La oración de la Hora de Jesús"))
        assertTrue(crumb().contains("Parte 4"))
    }

    @Test fun theBreadcrumbReturnsToThePartIndexUnfoldedAtThisPlace() {
        show(initialParagraph = 1324)
        waitForTag("catechism_article_1324")
        click("back_to_catechism_outline")
        waitForTag("ccc_tree")
        // Part 2 › Section 2 › Chapter 1 are open, the article (a leaf) is on the page.
        assertTrue(exists("ccc_node_p2s2c1a3"))
    }

    @Test fun labelsAndRangesAreLocalised() {
        show(AppLanguage.EN)
        click("catechism_part_2")
        waitForTag("ccc_tree")
        tag("ccc_node_p2s2").assertExists()
        click("ccc_node_p2s2")
        waitForTag("ccc_read_p2s2")
        compose.onNodeWithText("Section Two · CCC\u00A01210–1690", useUnmergedTree = true).assertExists()
        compose.onNodeWithText("Part Two · CCC\u00A01066–1690", useUnmergedTree = true).assertExists()
    }

    @Test fun withoutTheAssetTheOutlineStillBrowsesAndOpens() {
        CatechismStructureCatalog.override = CatechismStructureCatalog { throw FileNotFoundException("no asset") }
        show()
        tag("ccc_index").assertIsDisplayed()
        click("catechism_part_1")
        waitForTag("ccc_tree")
        click("ccc_node_outline_26")
        waitForTag("catechism_article_26")
        tag("catechism_cross_references").assertExists()
    }
}
