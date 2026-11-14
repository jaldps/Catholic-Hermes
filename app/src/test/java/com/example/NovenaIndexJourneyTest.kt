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
class NovenaIndexJourneyTest {
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
    @Test fun catalogBrowseDoesNotLoadBodiesAndSelectedPrayerOpens() {
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
        assertEquals(0,com.example.data.repository.NovenaCatalog.default.cachedBodyCount())
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.onNodeWithText("Novenas",useUnmergedTree=true).performClick()
        compose.waitUntil(10_000) {
            compose.onAllNodesWithText("Cargando novenas…").fetchSemanticsNodes().isEmpty()
        }
        val id="novena-a-cristo-rey"
        compose.onNodeWithTag("novena_index").performScrollToNode(hasTestTag("novena_card_$id"))
        assertEquals(0,com.example.data.repository.NovenaCatalog.default.cachedBodyCount())
        compose.onNodeWithTag("novena_card_$id").performClick()
        compose.waitUntil(10_000) { compose.onAllNodesWithTag("novena_detail_$id").fetchSemanticsNodes().isNotEmpty() }
        assertEquals(1,com.example.data.repository.NovenaCatalog.default.cachedBodyCount())
        compose.onNodeWithTag("novena_detail_$id").performScrollToNode(hasTestTag("novena_start_button"))
        compose.onNodeWithTag("novena_start_button").performClick()
        compose.waitUntil(10_000) { compose.onAllNodesWithTag("novena_day_reader").fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("novena_day_reader").assertExists()

    }
}
