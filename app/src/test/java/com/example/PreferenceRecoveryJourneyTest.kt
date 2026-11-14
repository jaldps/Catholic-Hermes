package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class PreferenceRecoveryJourneyTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var repository: CatholicDataRepository? = null
    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }
        narration?.release(); repository?.close(); database?.close()
    }
    @Test fun aNestedQuestionWriteFailureCanBeRetriedFromTheGlobalRecoverySheet() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val fake = PreferenceStorageReliabilityTest.FaultingStore()
        val store = UserPreferencesStore(app, fake)
        runBlocking { store.setLanguage(AppLanguage.ES) }
        val initial = runBlocking { store.preferences.first() }
        val repo = CatholicDataRepository(app, store).also { repository = it; it.applyPersistedLanguage(AppLanguage.ES) }
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narration = it }
        compose.setContent {
            val preferences by store.preferences.collectAsState(initial = initial)
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, preferences) }
            }
        }
        fake.writeFails = true
        PersonalDataCommands.submit("question:72") { store.recordTrackProgress(72) }
        runBlocking { PersonalDataCommands.awaitIdle() }
        compose.waitUntil(15_000) {
            compose.runOnUiThread { Snapshot.sendApplyNotifications() }
            compose.onAllNodesWithTag("preferences_recovery_sheet").fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNodeWithTag("retry_preferences_write").assertIsDisplayed()
        fake.writeFails = false
        compose.onNodeWithTag("retry_preferences_write").performClick()
        runBlocking { PersonalDataCommands.awaitIdle() }
        assertEquals(setOf(72), runBlocking { store.visitedQuestions.first() })
        assertEquals(PersonalDataCommands.Status.SAVED, PersonalDataCommands.states.value["question:72"]?.status)
        compose.onNodeWithTag("close_preferences_recovery").performClick()
        compose.onNodeWithTag("home_screen").assertIsDisplayed()
    }
}
