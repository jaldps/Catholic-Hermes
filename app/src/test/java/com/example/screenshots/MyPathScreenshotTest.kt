package com.example.screenshots

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.HabitCompletionLogEntity
import com.example.data.local.JournalEntryEntity
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.AppDestination
import com.example.ui.MainApp
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
import java.time.LocalDate

/**
 * «Mi camino» with a populated «Hoy en mi camino» (two habits kept, a plan three days in, a novena begun), per identity
 * and appearance, and at 320 dp / 130 %. PNGs go to docs/tasks/T-031-shots.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class MyPathScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }; narrator?.release(); repository?.close(); database?.close()
    }

    private fun render(language: AppLanguage, scale: Float) {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val store = UserPreferencesStore(app)
        val repo = CatholicDataRepository(app).apply { setLanguage(language) }.also { repository = it }
        val today = repo.currentDate.value.toEpochDay()
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val now = LocalDate.now()
        runBlocking {
            store.restartPlan("bible_year"); store.restartPlan("catechism_year")
            store.setPlanStart("bible_year", today - 3); store.setPlanDayDone("bible_year", 2, true)
            val dao = db.dao()
            dao.insertCompletion(HabitCompletionLogEntity(habitId = "daily_morning_prayers", completionDate = now.toString()))
            dao.insertCompletion(HabitCompletionLogEntity(habitId = "daily_angelus", completionDate = now.toString()))
            dao.insertCompletion(HabitCompletionLogEntity(habitId = "daily_rosary", completionDate = now.minusDays(1).toString()))
            dao.setNovenaDay("novena-a-la-divina-misericordia", 1, true, (1..9).toSet())
            dao.setNovenaDay("novena-a-la-divina-misericordia", 2, true, (1..9).toSet())
            dao.insertJournalEntry(JournalEntryEntity(title = "Por la salud de mi madre", category = "Intención", content = "Pido paz y fortaleza para toda la familia."))
            dao.insertJournalEntry(JournalEntryEntity(title = "Gracias por este día", category = "Agradecimiento", content = "Un día tranquilo en casa.", isAnswered = true))
        }
        val prefs = runBlocking { store.preferences.first() }.copy(language = language, hasChosenLanguage = true, designLanguage = identity.id,
            darkModeSetting = if (dark) "DARK" else "LIGHT", fontSizeScale = scale)
        val tts = LiturgicalTtsManager(app).also { narrator = it }
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, store, prefs, initialDestination = AppDestination.MY_PATH) }
        } }
        waitFor("my_path_today_novena_novena-a-la-divina-misericordia")
        waitFor("my_path_today_plan_bible_year")
        waitFor("my_path_today_habits")
    }

    private fun waitFor(tag: String) = compose.waitUntil(40_000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty() }

    private fun capture(name: String) {
        compose.waitForIdle()
        // Bundled art decodes on IO; let it land before the canvas is copied.
        Thread.sleep(1500)
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full))
            val small = Bitmap.createScaledBitmap(full, full.width * 6 / 10, full.height * 6 / 10, true)
            val file = File("../docs/tasks/T-031-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name.png")
            file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
            full.recycle(); small.recycle()
        }
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    private fun openSection(row: String, landing: String) {
        compose.onNodeWithTag("my_path_screen").performScrollToNode(hasTestTag(row))
        compose.onNodeWithTag(row).performClick()
        waitFor(landing)
    }

    @Test fun hubAndSections() {
        render(AppLanguage.ES, 1f)
        capture("hub")
        compose.onNodeWithTag("my_path_screen").performScrollToNode(hasTestTag("my_path_confession_entry"))
        capture("hub_bottom")
        openSection("my_path_section_practices", "spiritual_life_learn_picker")
        capture("practices")
        back(); waitFor("my_path_screen")
        openSection("my_path_section_diary", "spiritual_confession_entry")
        compose.waitUntil(20_000) { compose.onAllNodesWithText("Por la salud de mi madre").fetchSemanticsNodes().isNotEmpty() }
        capture("diary")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeText() {
        render(AppLanguage.PT, 1.3f)
        capture("320_130_hub")
        compose.onNodeWithTag("my_path_screen").performScrollToNode(hasTestTag("my_path_section_practices"))
        capture("320_130_hub_sections")
        compose.onNodeWithTag("my_path_screen").performScrollToNode(hasTestTag("my_path_confession_entry"))
        capture("320_130_hub_bottom")
        openSection("my_path_section_practices", "spiritual_life_learn_picker")
        capture("320_130_practices")
        back(); waitFor("my_path_screen")
        openSection("my_path_section_diary", "spiritual_confession_entry")
        compose.waitUntil(20_000) { compose.onAllNodesWithText("Por la salud de mi madre").fetchSemanticsNodes().isNotEmpty() }
        capture("320_130_diary")
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
