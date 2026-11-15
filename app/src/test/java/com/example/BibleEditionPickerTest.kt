package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.repository.BibleEditionCatalog
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.BibleEditionChooser
import com.example.ui.screens.BibleScreen
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** The Bible chooser lists every installed edition by language, switches the reader and sets per-language defaults. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class BibleEditionPickerTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val context: Application = ApplicationProvider.getApplicationContext()
    private var repository: CatholicDataRepository? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        repository?.close()
    }

    private fun await(condition: () -> Boolean) = compose.waitUntil(20_000) {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        runBlocking { PersonalDataCommands.awaitIdle() }
        condition()
    }

    @Test fun chooserShowsAllTenEditionsInFourLanguageGroups() {
        val catalog = BibleEditionCatalog(context)
        val picked = mutableListOf<String>()
        val defaults = mutableListOf<Pair<AppLanguage, String>>()
        compose.setContent {
            SanctaFidesTheme {
                androidx.compose.foundation.lazy.LazyColumn {
                    item {
                        BibleEditionChooser(catalog, AppLanguage.ES, "es.lbl", emptyMap(), { picked += it }, { l, id -> defaults += l to id })
                    }
                }
            }
        }
        for (lang in listOf("es", "en", "pt", "la")) compose.onNodeWithTag("bible_edition_group_$lang").assertExists()
        for (edition in catalog.editions) compose.onNodeWithTag("bible_edition_${edition.id}").assertExists()
        assertEquals(10, catalog.editions.size)
        compose.onNodeWithTag("bible_edition_la.nvbse").performScrollTo().performClick()
        assertEquals(listOf("la.nvbse"), picked)
        // Latin (or any edition) can be the default of any language.
        compose.onNodeWithTag("bible_edition_default_pt").performScrollTo().performClick()
        compose.onNodeWithTag("bible_edition_default_en").performScrollTo().performClick()
        assertEquals(listOf(AppLanguage.PT to "es.lbl", AppLanguage.EN to "es.lbl"), defaults)
    }

    @Test fun pickingLatinInTheReaderShowsItsText() {
        val repo = CatholicDataRepository(context).apply { applyPersistedLanguage(AppLanguage.ES) }.also { repository = it }
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    BibleScreen(repo, AppLanguage.ES, initialBookId = "joh", initialChapter = 1, initialVerseStart = 1)
                }
            }
        }
        await { compose.onAllNodesWithTag("bible_reader").fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("bible_reader").performScrollToIndex(0)
        compose.onNodeWithTag("bible_active_edition").assertTextEquals("Biblia Platense · Español").performClick()
        await { compose.onAllNodesWithTag("bible_edition_picker").fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("bible_edition_la.nvbse").performScrollTo().performClick()
        await { repo.activeBibleEditionId.value == "la.nvbse" }
        await { compose.onAllNodesWithTag("bible_edition_picker").fetchSemanticsNodes().isEmpty() }
        await { compose.onAllNodesWithTag("bible_reader").fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("bible_reader").performScrollToIndex(0)
        compose.onNodeWithTag("bible_active_edition").assertTextEquals("Nova Vulgata · Latín")
        await { compose.onAllNodes(hasText("In principio erat Verbum", substring = true)).fetchSemanticsNodes().isNotEmpty() }
        assertTrue(repo.getChapter("joh", 1, AppLanguage.ES, "la.nvbse").verses.first().text.es.contains("In principio erat Verbum"))
    }
}
