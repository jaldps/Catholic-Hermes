package com.example.screenshots

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.saintsFixtureCatalog
import com.example.data.liturgy.CalendarDate
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.news.LocalReadingsLoader
import com.example.data.news.ReadingsLoader
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
import com.example.ui.design.lumenCanvas
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** T-055: the five empty/error states, per identity, plus novena filter at 320 dp / 130 %. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class EmptyStatesScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null
    private val app get() = ApplicationProvider.getApplicationContext<Application>()

    @After fun close() {
        SaintsCatalog.override = null
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }
        narrator?.release(); repository?.close(); database?.close()
    }

    private fun theme(scale: Float, content: @androidx.compose.runtime.Composable () -> Unit) {
        compose.activity.setContent {
            SanctaFidesTheme(
                designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT",
                fontScale = scale, reduceMotion = true
            ) {
                Box(Modifier.fillMaxSize().lumenCanvas()) {
                    CompositionLocalProvider(LocalInspectionMode provides true) { content() }
                }
            }
        }
    }

    private fun repo(): CatholicDataRepository =
        CatholicDataRepository(app).apply { setLanguage(AppLanguage.ES) }.also { repository = it }

    private fun db() = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java)
        .allowMainThreadQueries().build().also { database = it }

    private fun capture(name: String) {
        // Freeze decorative motion and let any entry animation finish before drawing.
        // reduceMotion = true is already set on both SanctaFidesTheme call sites; on
        // Lumen/Cristal light this was still captured mid-fade.
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(3_000L)
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full))
            val small = Bitmap.createScaledBitmap(full, full.width * 6 / 10, full.height * 6 / 10, true)
            val file = File("../docs/tasks/T-055-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name.png")
            file.parentFile!!.mkdirs()
            file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
            full.recycle(); small.recycle()
        }
    }

    private fun waitFor(tag: String) = compose.waitUntil(30_000) {
        compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    }

    @Test fun contentLoadNotice() {
        theme(1f) { ContentLoadNotice(AppLanguage.ES, failed = true, retry = {}) }
        waitFor("content_load_notice"); capture("content_load_notice")
    }

    @Test fun dailyMissalError() {
        val repo = repo()
        val failing: ReadingsLoader = { _, _ -> ReadingsResult.Failure("offline") }
        val date = CalendarDate(2026, 5, 17)
        val reading = repo.getMassReading(date).copy(firstReadingRef = "", gospelText = LocalizedText("", "", ""))
        theme(1f) {
            CompositionLocalProvider(LocalReadingsLoader provides failing) {
                HomeScreen(
                    dailyReading = reading, verseOfTheDay = repo.verseOfTheDay, saintOfTheDay = repo.saintOfTheDay,
                    currentLanguage = AppLanguage.ES, onOpenLanguageSelector = {}, onNavigateToBible = {},
                    onNavigateToRosary = {}, onNavigateToPrayers = {}, onNavigateToDoctrine = {},
                    onNavigateToSpiritualLife = {}
                )
            }
        }
        waitFor("daily_missal_error")
        compose.onNodeWithTag("daily_missal_error", useUnmergedTree = true)
            .performScrollToNode(hasText("No se pudo cargar", substring = true))
        capture("daily_missal_error")
    }

    private fun openNovenaFilter(scale: Float) {
        val store = UserPreferencesStore(app)
        runBlocking { store.setLanguage(AppLanguage.ES) }
        val repo = CatholicDataRepository(app, store).also { repository = it; it.applyPersistedLanguage(AppLanguage.ES) }
        val prefs = runBlocking { store.preferences.first() }.copy(
            language = AppLanguage.ES, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT", fontSizeScale = scale
        )
        val db = db()
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.activity.setContent {
            SanctaFidesTheme(
                designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT",
                fontScale = scale, reduceMotion = true
            ) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    MainApp(repo, db.dao(), tts, store, prefs)
                }
            }
        }
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.onNodeWithText("Novenas", useUnmergedTree = true).performClick()
        waitFor("novena_index")
        compose.onNodeWithTag("prayers_search_toggle").performClick()
        compose.onNodeWithTag("novena_search_input").performTextInput("zzzznoexiste")
        waitFor("novena_filter_empty")
    }

    @Test fun novenaFilterEmpty() {
        openNovenaFilter(1f)
        compose.waitForIdle()
        repeat(2) {
            compose.onNodeWithTag("novena_index").performScrollToNode(hasTestTag("novena_clear_filters"))
            compose.waitForIdle()
        }
        compose.onNodeWithTag("novena_clear_filters", useUnmergedTree = true).assertIsDisplayed()
        capture("novena_filter_empty")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi")
    fun novenaFilterEmptyNarrowLargeText() {
        openNovenaFilter(1.3f)
        compose.waitForIdle()
        repeat(2) {
            compose.onNodeWithTag("novena_index").performScrollToNode(hasTestTag("novena_clear_filters"))
            compose.waitForIdle()
        }
        compose.onNodeWithTag("novena_clear_filters", useUnmergedTree = true).assertIsDisplayed()
        capture("320_130_novena_filter_empty")
    }

    @Test fun listEmpty() {
        SaintsCatalog.override = saintsFixtureCatalog()
        val repo = repo()
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        theme(1f) { SaintsScreen(AppLanguage.ES, ttsManager = tts) }
        waitFor("saints_search")
        compose.onNodeWithTag("saints_search").onChildren().filter(hasSetTextAction())[0]
            .performTextInput("zzzznoexiste")
        waitFor("list_empty"); capture("list_empty")
    }

    @Test fun remindersEmpty() {
        val store = ReminderStore(app)
        runBlocking { ReminderCatalog.DEFAULTS.forEach { store.setEnabled(it.id, false) } }
        val repo = repo()
        theme(1f) { RemindersScreen(repo) }
        waitFor("reminders_empty"); capture("reminders_empty")
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false),
            arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false),
            arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
