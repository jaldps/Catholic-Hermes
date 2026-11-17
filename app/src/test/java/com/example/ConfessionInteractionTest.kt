package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.backup.BackupPreferences
import com.example.data.backup.BackupService
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.persistence.PersonalDataCommands
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ConfessionCatalog
import com.example.ui.AppDestination
import com.example.ui.MainApp
import com.example.ui.confession.ConfessionScreen
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk=[34],qualifiers="w411dp-h891dp-xhdpi")
class ConfessionInteractionTest {
    @get:Rule val compose=createAndroidComposeRule<ComponentActivity>()
    private var db: SanctaFidesDatabase? = null
    private var repo: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null
    private val app get()=ApplicationProvider.getApplicationContext<Application>()
    @After fun close() {
        compose.runOnUiThread {compose.activity.setContent {}}
        compose.waitForIdle();runBlocking {PersonalDataCommands.awaitIdle()}
        tts?.release();repo?.close();db?.close();ConfessionCatalog.override=null
    }
    private fun waitForContent() {
        compose.onNodeWithText("Entendido").performScrollTo().performClick()
        compose.waitUntil(15_000) {runCatching {
            compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_profile_button")); true
        }.getOrDefault(false)}
    }
    private fun tab(label: String) {
        compose.onNode(hasText(label) and hasAnyAncestor(hasTestTag("confession_tabs")), useUnmergedTree=true).performScrollTo().performClick()
    }
    private fun mark(id: String) {
        compose.onNodeWithTag("confession_section_c1").performScrollTo()
        if (compose.onAllNodesWithTag("confession_question_$id").fetchSemanticsNodes().isEmpty())
            compose.onNodeWithTag("confession_section_c1").performClick()
        compose.onNodeWithTag("confession_question_$id").performScrollTo().performClick()
    }
    @Test fun navigationProfilePrivateListNotesAndEraseNeverPersistTheDraft() {
        val store=UserPreferencesStore(app)
        runBlocking { store.setLanguage(AppLanguage.ES) }
        val prefs=runBlocking {store.preferences.first()}
        val database=Room.inMemoryDatabaseBuilder(app,SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also {db=it}
        val repository=CatholicDataRepository(app).apply {setLanguage(AppLanguage.ES)}.also {repo=it}
        val narrator=LiturgicalTtsManager(app).also {tts=it}
        compose.setContent {SanctaFidesTheme(reduceMotion=true) {CompositionLocalProvider(LocalInspectionMode provides true) {
            MainApp(repository,database.dao(),narrator,store,prefs)
        }}}
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_confession"))
        compose.onNodeWithTag("nav_confession").performClick()
        waitForContent()
        compose.onNodeWithTag("confession_profile_button").performScrollTo().performClick()
        compose.onNodeWithTag("confession_profile_married").performClick()
        compose.waitUntil(10_000) {runBlocking {store.preferences.first().confessionProfileId=="married"}}
        runBlocking {PersonalDataCommands.awaitIdle()}
        val beforePreferences=runBlocking {store.backupDataStore.data.first().asMap()}
        val backup=BackupService(app,database,BackupPreferences(app,store))
        val before=runBlocking {backup.snapshot()}.copy(createdAt=0)
        mark("married:c1:0")
        tab("Mi lista")
        compose.onNodeWithTag("confession_selected_married:c1:0").performScrollTo().assertExists()
        val secret="PRIVATE-NOTE-T028-sentinel"
        compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_notes"))
        compose.onNodeWithTag("confession_notes").performScrollTo().performTextInput(secret + "\n" + "Nota temporal larga.\n".repeat(40))
        val viewport = compose.onNodeWithTag("confession_screen").fetchSemanticsNode().boundsInRoot
        val erase = compose.onNodeWithTag("confession_finish_erase").fetchSemanticsNode().boundsInRoot
        assertTrue("The editor viewport ends above the erase action", viewport.bottom <= erase.top)
        runBlocking {PersonalDataCommands.awaitIdle()}
        assertEquals(beforePreferences,runBlocking {store.backupDataStore.data.first().asMap()})
        assertEquals(before,runBlocking {backup.snapshot()}.copy(createdAt=0)) // Reads every personal Room table and all exported preference stores.
        compose.onNodeWithTag("confession_finish_erase").performClick()
        compose.onNodeWithTag("confession_empty_list").performScrollTo().assertExists()
        compose.onNodeWithTag("confession_notes").assertTextEquals("")
        // Mark again, then leave via the dock and return. SaveableStateHolder must not restore private data.
        tab("Prepararme");mark("married:c1:0");tab("Mi lista")
        compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_notes"))
        compose.onNodeWithTag("confession_notes").performScrollTo().performTextInput(secret)
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_confession"))
        compose.onNodeWithTag("nav_confession").performClick()
        tab("Mi lista")
        compose.waitUntil(10_000) {compose.onAllNodesWithTag("confession_empty_list").fetchSemanticsNodes().isNotEmpty()}
        compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_notes"))
        compose.onNodeWithTag("confession_notes").performScrollTo().assertTextEquals("")
        assertEquals(before,runBlocking {backup.snapshot()}.copy(createdAt=0))
    }
    @Test fun androidStateRestorationDropsBothQuestionIdsAndNotes() {
        val restorer=StateRestorationTester(compose)
        var registry: androidx.compose.runtime.saveable.SaveableStateRegistry? = null
        restorer.setContent {
            val current = androidx.compose.runtime.saveable.LocalSaveableStateRegistry.current
            SideEffect { registry = current }
            SanctaFidesTheme(reduceMotion=true) {ConfessionScreen(AppLanguage.ES)}
        }
        waitForContent();mark("single:c1:0");tab("Mi lista")
        compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_notes"))
        compose.onNodeWithTag("confession_notes").performScrollTo().performTextInput("RESTORATION-SECRET")
        val saved = compose.runOnIdle { requireNotNull(registry).performSave().toString() }
        assertFalse(saved.contains("RESTORATION-SECRET")); assertFalse(saved.contains("single:c1:0"))
        restorer.emulateSavedInstanceStateRestore()
        compose.waitUntil(10_000) {compose.onAllNodesWithTag("confession_empty_list").fetchSemanticsNodes().isNotEmpty()}
        compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_notes"))
        compose.onNodeWithTag("confession_notes").performScrollTo().assertTextEquals("")
    }
    @Test fun spiritualLifeProvidesTheSameConfessionEntryAndGuideShowsRealPrayerSources() {
        val store=UserPreferencesStore(app);val prefs=runBlocking {store.preferences.first()}.copy(language=AppLanguage.ES,hasChosenLanguage=true)
        val database=Room.inMemoryDatabaseBuilder(app,SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also {db=it}
        val repository=CatholicDataRepository(app).apply {setLanguage(AppLanguage.ES)}.also {repo=it}
        val narrator=LiturgicalTtsManager(app).also {tts=it}
        compose.setContent {SanctaFidesTheme(reduceMotion=true) {CompositionLocalProvider(LocalInspectionMode provides true) {
            MainApp(repository,database.dao(),narrator,store,prefs,initialDestination=AppDestination.SPIRITUAL_LIFE)
        }}}
        compose.onNodeWithTag("spiritual_confession_entry").performScrollTo().performClick()
        waitForContent();tab("El sacramento")
        compose.onNodeWithTag("confession_guide").assertExists()
        compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_prayer_source_senor_mio_jesucristo"))
        compose.onNodeWithTag("confession_prayer_source_senor_mio_jesucristo").assertTextContains("Arquidiócesis de Monterrey",substring=true)
    }
}
