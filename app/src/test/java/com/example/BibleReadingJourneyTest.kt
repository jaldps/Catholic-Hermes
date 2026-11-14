package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.BibleScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowLooper

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class BibleReadingJourneyTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var repository: CatholicDataRepository? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        repository?.close()
    }
    private fun launch(book: String? = null, chapter: Int? = null, verse: Int? = null) {
        val repo = CatholicDataRepository(ApplicationProvider.getApplicationContext<Application>()).apply { applyPersistedLanguage(AppLanguage.ES) }.also { repository = it }
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    BibleScreen(repo, AppLanguage.ES, initialBookId = book, initialChapter = chapter, initialVerseStart = verse)
                }
            }
        }
    }
    private fun await(condition: () -> Boolean) = compose.waitUntil(15_000) {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        condition()
    }
    @Test fun aDeepLinkedVerseRemainsReachableAfterAsynchronousChapterLoading() {
        launch("joh", 3, 16)
        await { compose.onAllNodesWithTag("bible_reader").fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("verse_16").assertIsDisplayed()
        compose.onNodeWithTag("bible_reader").performScrollToIndex(0)
        compose.onNodeWithTag("bible_active_edition").assertTextEquals("Biblia Platense · Español")
    }
    @Test fun aSearchCanContinueBeyondItsFirstTwentyResults() {
        launch()
        compose.onNodeWithTag("bible_search_input").performTextInput("Dios")
        await { compose.onAllNodesWithTag("bible_verse_result_0").fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("bible_book_directory").performScrollToNode(hasTestTag("bible_load_more_results"))
        compose.onNodeWithTag("bible_load_more_results").performClick()
        await {
            runCatching {
                compose.onNodeWithTag("bible_book_directory").performScrollToNode(hasTestTag("bible_verse_result_20"))
                compose.onNodeWithTag("bible_verse_result_20").assertIsDisplayed()
            }.isSuccess
        }
        compose.onNodeWithTag("bible_verse_result_20").performClick()
        await { compose.onAllNodesWithTag("bible_reader").fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("bible_reader").performScrollToIndex(0)
        compose.onNodeWithTag("bible_active_edition").assertTextEquals("Biblia Platense · Español")
    }
}
