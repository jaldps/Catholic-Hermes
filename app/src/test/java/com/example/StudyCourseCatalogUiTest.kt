package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.StudyCoursesCatalog
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
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
class StudyCourseCatalogUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null
    private var repository: CatholicDataRepository? = null

    @After fun close() {
        StudyCoursesCatalog.override = null
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { com.example.data.persistence.PersonalDataCommands.awaitIdle() }
        narration?.release(); repository?.close(); database?.close()
    }

    @Test fun fixtureCourseIsAppendedAndItsLessonRenders() {
        StudyCoursesCatalog.override = coursesFixtureCatalog(mapOf("track_fixture_course.json" to courseFixtureJson()))
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repo = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES) }.also { repository = it }
        val ids = repo.scriptureStudyTracks.map { it.id }
        assertEquals("track_fixture_course", ids.last())
        assertEquals("track_catholic_bible", ids.first())
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val prefs = UserPreferences(AppLanguage.ES, "LUMEN", "LITURGICAL_DYNAMIC", "LIGHT", "IDENTITY_DEFAULT", 1f, 1f, "bundled", false, true)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, UserPreferencesStore(context), prefs) }
            }
        }
        compose.onNodeWithTag("nav_bible").performClick()
        compose.onNodeWithText("Estudio", useUnmergedTree = true).performClick()
        compose.onNodeWithTag("study_track_picker").performScrollToNode(hasTestTag("track_row_track_fixture_course"))
        compose.onNodeWithTag("track_row_track_fixture_course").performSemanticsAction(SemanticsActions.OnClick)
        compose.onNodeWithTag("study_overview_page").performScrollToNode(hasTestTag("lesson_row_fixture_2"))
        compose.onNodeWithTag("lesson_row_fixture_2").performClick()

        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("study_teaching_1"))
        compose.onNodeWithTag("study_teaching_1").assertTextContains("Cuerpo B2 es")
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("study_review_0"))
        compose.onNodeWithTag("study_review_0").performClick()
        compose.onNodeWithTag("study_review_answer_0").assertTextContains("Respuesta uno es")
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("study_source_ccc_456_460"))
        compose.onNodeWithTag("study_source_ccc_456_460").assertExists()
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("study_source_dv_11"))
        compose.onNodeWithTag("study_source_dv_11").assertExists()
    }
}
