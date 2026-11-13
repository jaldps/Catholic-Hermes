package com.example

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
class StudyJourneyTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null
    private var repository: CatholicDataRepository? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        narration?.release(); repository?.close(); database?.close()
    }

    private fun launch(fromExplore: Boolean = false) {
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

    @Test fun answersAndUnsavedNotesSurviveCatechismRoundTrip() {
        launch()
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("study_review_0"))
        compose.onNodeWithTag("study_review_0").performClick()
        compose.onNodeWithTag("study_review_answer_0").performScrollTo()
        compose.onNodeWithTag("study_review_answer_0").assertIsDisplayed()
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("lesson_notes_field"))
        compose.onNodeWithTag("lesson_notes_field").performTextInput("Mi pregunta sobre la Tradición")
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("study_source_ccc_80_87"))
        compose.onNodeWithTag("study_source_ccc_80_87").performClick()
        compose.onNodeWithTag("catechism_screen").assertExists()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("lesson_notes_field"))
        compose.onNodeWithTag("lesson_notes_field").assertTextContains("Mi pregunta sobre la Tradición")
    }

    @Test fun continuingSavesNotesWithoutFalselyCompletingLesson() {
        launch(fromExplore = true)
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("lesson_notes_field"))
        compose.onNodeWithTag("lesson_notes_field").performTextInput("Para consultar en mi parroquia")
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag("study_next_lesson"))
        compose.onNodeWithTag("study_next_lesson").performClick()
        compose.waitUntil(10_000) {
            runBlocking { database!!.dao().getAllScriptureProgress().first() }.any { it.lessonId == "bible_1_word_tradition" }
        }
        val saved = runBlocking { database!!.dao().getAllScriptureProgress().first() }.first { it.lessonId == "bible_1_word_tradition" }
        assertEquals("Para consultar en mi parroquia", saved.personalNotes)
        assertFalse(saved.isCompleted)
        compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasText("Una biblioteca y una historia de salvación"))
    }
}
