package com.example.screenshots

import com.example.*
import android.graphics.Bitmap
import android.graphics.Canvas
import java.io.File

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.liturgy.parseFoundation
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
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
class GroupedPickersShotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null
    private var repository: CatholicDataRepository? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { com.example.data.persistence.PersonalDataCommands.awaitIdle() }
        narration?.release(); repository?.close(); database?.close()
    }

    private fun launch(fromExplore: Boolean = false) = launchApp().also { openLesson(fromExplore) }

    private fun launchApp(): CatholicDataRepository {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repo = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES) }.also { repository = it }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val prefs = UserPreferences(AppLanguage.ES, "LUMEN", "LITURGICAL_DYNAMIC", "LIGHT", "IDENTITY_DEFAULT", 1f, 1f, "bundled", false, true)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, UserPreferencesStore(context), prefs) }
            }
        }
        return repo
    }

    private fun openLesson(fromExplore: Boolean) {
        if (fromExplore) {
            compose.onNodeWithTag("nav_more").performClick()
            compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_scripture_study"))
            compose.onNodeWithTag("nav_scripture_study").performClick()
        } else {
            compose.onNodeWithTag("nav_bible").performClick()
            compose.onNodeWithText("Estudio", useUnmergedTree = true).performClick()
        }
        compose.onNodeWithTag("study_overview_page").performScrollToNode(hasTestTag("study_continue_button"))
        compose.onNodeWithTag("study_continue_button").performClick()
    }

    @Test fun foundationPickerShowsFirstReferenceAndSwitches() {
        val repo = launchApp()
        val question = repo.catholicQuestions.first { q -> parseFoundation(q.foundation.get(AppLanguage.ES), AppLanguage.ES, repo.catholicBooks).size >= 2 }
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_questions"))
        compose.onNodeWithTag("nav_questions").performClick()
        compose.onNodeWithTag("qa_search_input").performScrollTo().performTextInput(question.question.get(AppLanguage.ES))
        compose.onNodeWithTag("catholic_question_${question.number}").performScrollTo().performClick()
        compose.onNodeWithTag("doctrine_entry_page").performScrollToNode(hasTestTag("foundation_ref_picker"))
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            val bmp = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bmp))
            File("build/screenshots/m2b").mkdirs()
            File("build/screenshots/m2b/foundation_picker.png").outputStream().use { bmp.compress(Bitmap.CompressFormat.PNG, 100, it) }
        }
        compose.onNodeWithTag("foundation_ref_picker").performClick()
        compose.onAllNodes(hasTestTagPrefix("foundation_ref_") and !hasTestTagPrefix("foundation_ref_picker") and !hasTestTagPrefix("foundation_ref_text_") and !hasTestTagPrefix("foundation_ref_open_"), useUnmergedTree = true).assertCountEquals(
            parseFoundation(question.foundation.get(AppLanguage.ES), AppLanguage.ES, repo.catholicBooks).size
        )
    }

    private fun hasTestTagPrefix(prefix: String) = SemanticsMatcher("tag starts with $prefix") {
        ((if (androidx.compose.ui.semantics.SemanticsProperties.TestTag in it.config) it.config[androidx.compose.ui.semantics.SemanticsProperties.TestTag] else "")).startsWith(prefix)
    }
}
