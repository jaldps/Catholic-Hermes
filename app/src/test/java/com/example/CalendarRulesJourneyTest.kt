package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk=[34],qualifiers="w411dp-h891dp-xhdpi")
class CalendarRulesJourneyTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var db: SanctaFidesDatabase? = null
    private var repo: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null
    private val prefsScope = CoroutineScope(SupervisorJob()+Dispatchers.IO)
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }
        tts?.release();repo?.close();db?.close();prefsScope.cancel()
    }
    @Test fun transfersAreReachableAndSurviveClosingAndReopeningSettings() {
        val app=ApplicationProvider.getApplicationContext<Application>()
        val file=File.createTempFile("calendar-journey", ".preferences_pb",app.cacheDir).apply {delete()}
        val data=PreferenceDataStoreFactory.create(scope=prefsScope,produceFile={file})
        val store=UserPreferencesStore(app,data)
        runBlocking { store.setLanguage(AppLanguage.ES) }
        val repository=CatholicDataRepository(app,store).also {repo=it;it.applyPersistedLanguage(AppLanguage.ES)}
        val database=Room.inMemoryDatabaseBuilder(app,SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also {db=it}
        val narration=LiturgicalTtsManager(app).also {tts=it}
        val initial=runBlocking {store.preferences.first()}
        compose.setContent {
            val preferences by store.preferences.collectAsState(initial=initial)
            SanctaFidesTheme(designLanguage="LUMEN",darkModeSetting="LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) {MainApp(repository,database.dao(),narration,store,preferences)}
            }
        }
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("calendar_rules_settings"))
        compose.onNodeWithTag("calendar_rules_settings").performClick()
        compose.onNodeWithTag("transfer_epiphany").performScrollTo().performClick()
        compose.onNodeWithTag("transfer_ascension").performScrollTo().performClick()
        runBlocking { PersonalDataCommands.awaitIdle(); withTimeout(5_000) {repository.calendarRules.first {it.epiphanyOnSunday && it.ascensionOnSunday}} }
        compose.onNodeWithTag("close_calendar_rules").performClick()
        compose.onNodeWithTag("calendar_rules_settings").performClick()
        compose.onNodeWithTag("transfer_epiphany").performScrollTo().assertIsOn()
        compose.onNodeWithTag("transfer_ascension").performScrollTo().assertIsOn()
        val saved=runBlocking {store.preferences.first()}.calendarRules
        assertTrue(saved.epiphanyOnSunday);assertTrue(saved.ascensionOnSunday);assertFalse(saved.corpusChristiOnSunday)
    }
}
