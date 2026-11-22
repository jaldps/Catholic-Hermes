package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.liturgy.CalendarDate
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.news.LocalReadingsLoader
import com.example.data.news.ReadingsResult
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.reminders.ReminderCatalog
import com.example.data.reminders.ReminderStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.SaintsCatalog
import com.example.ui.MainApp
import com.example.ui.components.ContentLoadNotice
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.RemindersScreen
import com.example.ui.screens.SaintsScreen
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** T-055: the five missing empty/error states, in portrait. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class EmptyStatesTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null
    private val app get() = ApplicationProvider.getApplicationContext<Application>()

    @After fun close() {
        SaintsCatalog.override = null
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }
        tts?.release(); repository?.close(); database?.close()
    }

    private fun waitFor(tag: String, timeout: Long = 25_000) =
        compose.waitUntil(timeout) { compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() }

    private fun gone(tag: String) =
        compose.waitUntil(20_000) { compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isEmpty() }

    @Test fun contentLoadNoticeShowsTheErrorAndRetries() {
        var retries = 0
        compose.activity.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                ContentLoadNotice(AppLanguage.ES, failed = true, retry = { retries++ })
            }
        }
        waitFor("content_load_notice")
        compose.onNodeWithText("Reintentar", useUnmergedTree = true).performClick()
        compose.waitForIdle()
        assert(retries == 1) { "retry must run once" }
    }

    @Test fun dailyMissalErrorAppearsAndRetriesTheLoader() {
        val repo = CatholicDataRepository(app).apply { setLanguage(AppLanguage.ES) }.also { repository = it }
        var calls = 0
        val failingLoader: com.example.data.news.ReadingsLoader = { _, _ ->
            calls++
            ReadingsResult.Failure("offline")
        }
        val date = CalendarDate(2026, 5, 17)
        val empty = LocalizedText("", "", "")
        val reading = repo.getMassReading(date).copy(firstReadingRef = "", gospelText = empty)
        compose.activity.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true, LocalReadingsLoader provides failingLoader) {
                    HomeScreen(
                        dailyReading = reading,
                        verseOfTheDay = repo.verseOfTheDay,
                        saintOfTheDay = repo.saintOfTheDay,
                        currentLanguage = AppLanguage.ES,
                        onOpenLanguageSelector = {},
                        onNavigateToBible = {},
                        onNavigateToRosary = {},
                        onNavigateToPrayers = {},
                        onNavigateToDoctrine = {},
                        onNavigateToSpiritualLife = {}
                    )
                }
            }
        }
        waitFor("daily_missal_error")
        // The outer daily_missal_error tag wins over the wrapper's content_load_notice.
        val before = calls
        compose.onNodeWithTag("daily_missal_error", useUnmergedTree = true)
            .performScrollToNode(hasText("Reintentar", substring = true))
        compose.onAllNodesWithText("Reintentar", useUnmergedTree = true)[0].performClick()
        compose.waitForIdle()
        assert(calls > before) { "retry must call the readings loader again" }
    }

    @Test fun novenaFilterEmptyShowsAndClearFiltersRestoresTheList() {
        val store = UserPreferencesStore(app)
        runBlocking { store.setLanguage(AppLanguage.ES) }
        val repo = CatholicDataRepository(app, store).also { repository = it; it.applyPersistedLanguage(AppLanguage.ES) }
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val narrator = LiturgicalTtsManager(app).also { tts = it }
        compose.activity.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    MainApp(repo, db.dao(), narrator, store, UserPreferences(AppLanguage.ES, "LUMEN", "LITURGICAL_DYNAMIC", "LIGHT", "IDENTITY_DEFAULT", 1f, 1f, "bundled", false, true))
                }
            }
        }
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.onNodeWithText("Novenas", useUnmergedTree = true).performClick()
        waitFor("novena_index")
        compose.onNodeWithTag("prayers_search_toggle", useUnmergedTree = true).performClick()
        compose.onNodeWithTag("novena_search_input", useUnmergedTree = true).performTextInput("zzzznoexiste")
        waitFor("novena_filter_empty")
        compose.onNodeWithTag("novena_clear_filters", useUnmergedTree = true).performClick()
        gone("novena_filter_empty")
        assert(compose.onAllNodes(hasTestTagPrefix("novena_card_"), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty())
    }

    @Test fun saintsListEmptyShowsAndClearFiltersRestoresRows() {
        SaintsCatalog.override = saintsFixtureCatalog()
        val repo = CatholicDataRepository(app).apply { setLanguage(AppLanguage.ES) }.also { repository = it }
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val narrator = LiturgicalTtsManager(app).also { tts = it }
        compose.activity.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    SaintsScreen(AppLanguage.ES, ttsManager = narrator)
                }
            }
        }
        waitFor("saints_search")
        // The tag sits on the wrapper Row; the editable node is its BasicTextField child.
        compose.onNodeWithTag("saints_search").onChildren().filter(hasSetTextAction())[0]
            .performTextInput("zzzznoexiste")
        waitFor("list_empty")
        compose.onNodeWithTag("saints_clear_filters", useUnmergedTree = true).performClick()
        gone("list_empty")
    }

    @Test fun remindersEmptyShowsAndAngelusButtonTurnsAReminderOn() {
        val store = ReminderStore(app)
        runBlocking { ReminderCatalog.DEFAULTS.forEach { store.setEnabled(it.id, false) } }
        val repo = CatholicDataRepository(app).apply { setLanguage(AppLanguage.ES) }.also { repository = it }
        compose.activity.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) { RemindersScreen(repo) }
            }
        }
        waitFor("reminders_empty")
        compose.onNodeWithTag("reminders_enable_angelus", useUnmergedTree = true).performClick()
        gone("reminders_empty")
    }

    private fun hasTestTagPrefix(prefix: String) = SemanticsMatcher("tag starts with $prefix") {
        it.config.getOrNull(androidx.compose.ui.semantics.SemanticsProperties.TestTag)?.startsWith(prefix) == true
    }
}
