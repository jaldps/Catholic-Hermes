package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.HabitCompletionLogEntity
import com.example.data.local.JournalEntryEntity
import com.example.data.local.SanctaFidesDatabase
import com.example.data.local.SinOvercomeLogEntity
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.temptation.TemptationStore
import com.example.ui.MainApp
import com.example.ui.screens.SpiritualLifeScreen
import com.example.ui.screens.DoctrineAndHistoryScreen
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.time.LocalDate

/** Real category/save and Home → private-record journeys; existing history is retained. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class JournalTemptationInteractionTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val context get() = ApplicationProvider.getApplicationContext<Application>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narration: LiturgicalTtsManager? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        narration?.release()
        repository?.close()
        database?.close()
    }

    private fun tag(value: String) = compose.onNodeWithTag(value, useUnmergedTree = true)
    private fun exists(value: String) = compose.onAllNodesWithTag(value, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    private fun waitFor(value: String) {
        try {
            compose.waitUntil(10_000) { exists(value) }
        } catch (failure: androidx.compose.ui.test.ComposeTimeoutException) {
            throw AssertionError("Timed out waiting for tag '$value'", failure)
        }
    }

    private fun journalDropdownSave(identity: String, language: AppLanguage, storedKey: String, expectedLabel: String) {
        val repo = CatholicDataRepository(context).apply { setLanguage(language); repository = this }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val oldEntry = JournalEntryEntity(title = "Existing intention", category = "Intención", content = "Keep this record", isAnswered = true)
        runBlocking { db.dao().insertJournalEntry(oldEntry) }
        val restoration = StateRestorationTester(compose)
        restoration.setContent {
            SanctaFidesTheme(designLanguage = identity, darkModeSetting = "LIGHT", fontScale = 2f) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    SpiritualLifeScreen(repo, db.dao(), tts, language)
                }
            }
        }
        waitFor("add_journal_entry_fab")
        tag("add_journal_entry_fab").performClick()
        tag("journal_save_button").assertIsNotEnabled()
        assertComposerHeader(language)
        tag("journal_title_field").performTextInput("A new journal entry")
        tag("journal_save_button").assertIsEnabled()
        tag("journal_category_dropdown").performClick()
        tag("journal_category_$storedKey").performClick()
        tag("journal_category_value").assertTextContains(expectedLabel)
        // Restoration keeps the writer's choice and draft, including its stored category key.
        restoration.emulateSavedInstanceStateRestore()
        tag("journal_title_field").assertTextContains("A new journal entry")
        tag("journal_category_value").assertTextContains(expectedLabel)
        assertComposerHeader(language)
        tag("journal_save_button").performClick()
        compose.waitUntil(10_000) { runBlocking { db.dao().getAllJournalEntries().first().size == 2 } }
        val saved = runBlocking { db.dao().getAllJournalEntries().first() }
        assertEquals(storedKey, saved.single { it.title == "A new journal entry" }.category)
        assertEquals("Keep this record", saved.single { it.title == oldEntry.title }.content)
        assertTrue(saved.single { it.title == oldEntry.title }.isAnswered)
    }

    private fun assertComposerHeader(language: AppLanguage) {
        fun assertWholeLabel(tagName: String, expected: String, singleLine: Boolean) {
            tag(tagName).assertIsDisplayed().performSemanticsAction(SemanticsActions.GetTextLayoutResult) { getResults ->
                val results = mutableListOf<TextLayoutResult>()
                getResults(results)
                val layout = results.single()
                assertEquals(expected, layout.layoutInput.text.text)
                assertFalse(
                    "$tagName must fit at 320dp / 200%: size=${layout.size}, " +
                        "paragraph=${layout.multiParagraph.width}x${layout.multiParagraph.height}, " +
                        "widthOverflow=${layout.didOverflowWidth}, heightOverflow=${layout.didOverflowHeight}, " +
                        "constraints=${layout.layoutInput.constraints}, font=${layout.layoutInput.style.fontSize}, " +
                        "lineHeight=${layout.layoutInput.style.lineHeight}, lines=${layout.lineCount}, " +
                        "firstLine=${layout.getLineLeft(0)}..${layout.getLineRight(0)}, " +
                        "lineVertical=${layout.getLineTop(0)}..${layout.getLineBottom(0)}",
                    layout.hasVisualOverflow
                )
                if (singleLine) assertEquals("$tagName must not split an essential word", 1, layout.lineCount)
            }
        }
        assertWholeLabel("journal_composer_heading", if (language == AppLanguage.PT) "Nova entrada" else "Nueva entrada", false)
        assertWholeLabel("journal_cancel_label", "Cancelar", true)
        assertWholeLabel("journal_save_label", if (language == AppLanguage.PT) "Salvar" else "Guardar", true)
        tag("journal_cancel_button").assertHeightIsAtLeast(48.dp)
        tag("journal_save_button").assertHeightIsAtLeast(48.dp)
    }

    @Test @Config(sdk = [34], qualifiers = "w320dp-h760dp-xhdpi")
    fun lumenSpanishDropdownSavesOriginalCategoryAtTwoHundredPercent() {
        journalDropdownSave("LUMEN", AppLanguage.ES, "Gracia", "Gracia")
    }

    @Test @Config(sdk = [34], qualifiers = "w320dp-h760dp-xhdpi")
    fun sanctuaryPortugueseDropdownSavesOriginalCategoryAtTwoHundredPercent() {
        journalDropdownSave("SANCTUARY", AppLanguage.PT, "Agradecimiento", "Agradecimento")
    }

    @Test fun contextualDoctrinePopsNestedReadersBeforeReturningToItsCaller() {
        val repo = CatholicDataRepository(context).apply { setLanguage(AppLanguage.EN); repository = this }
        var exitsToCaller = 0
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    BackHandler { exitsToCaller++ }
                    DoctrineAndHistoryScreen(repo, AppLanguage.EN, initialEntry = "didache_toc:0")
                }
            }
        }
        tag("doctrine_entry_page").performScrollToNode(hasTestTag("didache_chapter_1"))
        tag("didache_chapter_1").performClick()
        waitFor("didache_progress")
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        waitFor("didache_chapter_1")
        assertEquals(0, exitsToCaller)
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
        assertEquals(1, exitsToCaller)
    }

    @Test fun homeSupportRecordsBothKindsAndKeepsHistoryAndPendingConfessionContext() {
        val repo = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES); repository = this }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val store = UserPreferencesStore(context)
        val temptation = TemptationStore(context)
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)
        val oldNote = "Earlier private record"
        runBlocking {
            temptation.recordVictory(yesterday)
            temptation.recordFall(yesterday)
            db.dao().insertSinOvercomeLog(SinOvercomeLogEntity(sinKey = "PRIDE", counterVirtue = "Humildad", note = oldNote))
            db.dao().insertCompletion(HabitCompletionLogEntity(habitId = "daily_rosary", completionDate = yesterday.toString()))
        }
        val beforeVictories = runBlocking { temptation.counts.first() }
        val beforeFalls = runBlocking { temptation.fellCounts.first() }
        val prefs = UserPreferences(AppLanguage.ES, "SANCTUARY", "LITURGICAL_DYNAMIC", "LIGHT", "IDENTITY_DEFAULT", 1f, 1f, "bundled", false, true)
        val restoration = StateRestorationTester(compose)
        restoration.setContent {
            SanctaFidesTheme(designLanguage = "SANCTUARY", darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs) }
            }
        }
        fun openHomeEntry(entryTag: String) {
            tag("home_screen").performScrollToNode(hasTestTag(entryTag))
            tag(entryTag).performClick()
        }
        fun back() {
            compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
            compose.waitForIdle()
        }
        fun assertClearsDock(actionTag: String) {
            val action = tag(actionTag).getUnclippedBoundsInRoot()
            val dock = tag("bottom_nav_bar").getUnclippedBoundsInRoot()
            assertTrue("$actionTag must clear the dock after scroll-to: action=$action, dock=$dock", action.bottom <= dock.top)
        }

        // Visit the ordinary overview first: a retained overview must not swallow Home's support shortcut.
        openHomeEntry("home_fidelity_open_habits")
        tag("tab_habits_rule").assertIsDisplayed()
        tag("nav_home").performClick()
        openHomeEntry("home_open_temptations")
        waitFor("tab_sin_avoider")
        tag("tab_sin_avoider").performScrollToNode(hasTestTag("temptation_fell_button"))
        assertClearsDock("temptation_fell_button")
        tag("temptation_fell_button").performClick()
        tag("fall_sin_and_confession").performScrollTo().performClick()
        waitFor("doctrine_and_history_screen")
        back()
        waitFor("fall_sheet")
        restoration.emulateSavedInstanceStateRestore()
        waitFor("fall_sheet")
        tag("record_fall_button").performScrollTo().performClick()
        compose.waitUntil(10_000) {
            runBlocking { temptation.fellCounts.first()[today.toString()] ?: 0 } == (beforeFalls[today.toString()] ?: 0) + 1
        }
        tag("tab_sin_avoider").performScrollToNode(hasTestTag("emergency_overcome_button"))
        assertClearsDock("emergency_overcome_button")
        tag("emergency_overcome_button").performClick()
        tag("victory_note").performScrollTo().performTextInput("Gracias de hoy")
        restoration.emulateSavedInstanceStateRestore()
        tag("victory_note").assertTextContains("Gracias de hoy")
        tag("confirm_victory_button").performScrollTo().performClick()
        compose.waitUntil(10_000) { runBlocking { db.dao().getAllSinOvercomeLogs().first().size == 2 } }
        compose.waitUntil(10_000) {
            runBlocking { temptation.counts.first()[today.toString()] ?: 0 } == (beforeVictories[today.toString()] ?: 0) + 1
        }
        val afterVictories = runBlocking { temptation.counts.first() }
        val afterFalls = runBlocking { temptation.fellCounts.first() }
        tag("tab_sin_avoider").performScrollToNode(hasTestTag("victory_stats_today"))
        tag("victory_stats_today").assertTextContains(((beforeVictories[today.toString()] ?: 0) + 1).toString())
        tag("fall_stats_today").assertTextContains(((beforeFalls[today.toString()] ?: 0) + 1).toString())
        assertEquals(beforeVictories[yesterday.toString()], afterVictories[yesterday.toString()])
        assertEquals(beforeFalls[yesterday.toString()], afterFalls[yesterday.toString()])
        assertEquals(0, temptation.countFor(afterVictories, today.plusDays(1)))
        assertEquals(0, temptation.fellCountFor(afterFalls, today.plusDays(1)))
        val logs = runBlocking { db.dao().getAllSinOvercomeLogs().first() }
        assertTrue(logs.any { it.note == oldNote && it.sinKey == "PRIDE" })
        assertTrue(logs.any { it.note == "Gracias de hoy" && it.sinKey == "TEMPTATION" })
        assertEquals(1, runBlocking { db.dao().getAllCompletions().first().size })

        // An explicit ordinary-practices entry returns to its overview without deleting either record.
        tag("nav_home").performClick()
        openHomeEntry("home_fidelity_open_habits")
        waitFor("tab_habits_rule")
        tag("tab_habits_rule").assertIsDisplayed()
        assertEquals(2, runBlocking { db.dao().getAllSinOvercomeLogs().first().size })
    }
}
