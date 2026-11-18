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
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Cross-references from a course lesson and from a plan day: the action, the related passages, the Bible reader. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class CrossReferencesUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var db: SanctaFidesDatabase? = null
    private var repo: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null
    private val app get() = ApplicationProvider.getApplicationContext<Application>()
    private lateinit var store: UserPreferencesStore

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle(); runBlocking { PersonalDataCommands.awaitIdle() }
        tts?.release(); repo?.close(); db?.close()
    }

    private fun launch(language: AppLanguage = AppLanguage.ES, beforeStart: suspend (UserPreferencesStore, Long) -> Unit = { _, _ -> }) {
        store = UserPreferencesStore(app)
        runBlocking { store.setLanguage(language); store.restartPlan("bible_year"); store.restartPlan("catechism_year") }
        val repository = CatholicDataRepository(app).apply { setLanguage(language) }.also { repo = it }
        runBlocking { beforeStart(store, repository.currentDate.value.toEpochDay()) }
        val prefs = runBlocking { store.preferences.first() }
        val database = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { db = it }
        val narrator = LiturgicalTtsManager(app).also { tts = it }
        compose.setContent { SanctaFidesTheme(reduceMotion = true) { CompositionLocalProvider(LocalInspectionMode provides true) {
            MainApp(repository, database.dao(), narrator, store, prefs)
        } } }
    }

    private fun waitFor(tag: String, timeout: Long = 40_000) =
        compose.waitUntil(timeout) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty() }

    /** The visible words of an action chip. */
    private fun label(tag: String, words: String) =
        compose.onNode(hasText(words) and hasAnyAncestor(hasTestTag(tag)), useUnmergedTree = true).assertExists()

    private fun openLesson(trackId: String, lessonId: String) {
        compose.onNodeWithTag("nav_bible").performClick()
        compose.onNodeWithText("Estudio", useUnmergedTree = true).performClick()
        waitFor("study_track_picker")
        compose.onNodeWithTag("study_track_picker").performScrollToNode(hasTestTag("track_row_$trackId"))
        compose.onNodeWithTag("track_row_$trackId").performClick()
        compose.onNodeWithTag("study_overview_page").performScrollToNode(hasTestTag("lesson_row_$lessonId"))
        compose.onNodeWithTag("lesson_row_$lessonId").performClick()
        waitFor("study_lesson_page")
    }

    @Test fun lessonReferenceOpensRelatedPassagesAndFollowingOneLandsInTheReader() {
        launch()
        openLesson("track_psalms_prayer", "psalms_1_israel_jesus")
        label("lesson_xref_psalms_1_israel_jesus_0", "Referencias")
        compose.onNodeWithTag("lesson_xref_psalms_1_israel_jesus_0").assertContentDescriptionContains("Salmos 23 (22)", substring = true)
        compose.onNodeWithTag("lesson_xref_psalms_1_israel_jesus_0").performScrollTo().performClick()
        // Psalm 23 is cited in Hebrew numbering; the sheet speaks the reader's Vulgate numbering.
        waitFor("passage_xref_sheet")
        waitFor("passage_xref_0_0")
        compose.onNodeWithTag("passage_xref_sheet").assertExists()
        compose.onAllNodesWithText("Salmos 22", substring = true, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty().let {
            assertEquals(true, it)
        }
        compose.onNodeWithTag("passage_xref_0_0").performClick()
        waitFor("bible_reader")
        compose.onNodeWithTag("passage_xref_sheet").assertDoesNotExist()
        compose.onNodeWithTag("bible_screen").assertExists()
        // Back leaves the reader for the lesson it came from.
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        waitFor("study_lesson_page")
        compose.onNodeWithTag("lesson_xref_psalms_1_israel_jesus_0").assertExists()
    }

    @Test fun everyCitedReferenceOfALessonHasItsOwnAction() {
        launch()
        openLesson("track_jesus_paschal", "jesus_3_supper_service")
        compose.onNodeWithTag("lesson_xref_jesus_3_supper_service_0").assertExists()
        compose.onNodeWithTag("lesson_xref_jesus_3_supper_service_1").assertExists()
        compose.onNodeWithTag("lesson_xref_jesus_3_supper_service_2").assertDoesNotExist()
        // The lesson's own text is untouched.
        compose.onNodeWithTag("lesson_listen_button").assertExists()
    }

    @Test fun englishLessonSaysCrossReferences() {
        launch(AppLanguage.EN)
        compose.onNodeWithTag("nav_bible").performClick()
        compose.onNodeWithText("Study", useUnmergedTree = true).performClick()
        waitFor("study_track_picker")
        compose.onNodeWithTag("study_track_picker").performScrollToNode(hasTestTag("track_row_track_paul_life_in_christ"))
        compose.onNodeWithTag("track_row_track_paul_life_in_christ").performClick()
        compose.onNodeWithTag("study_overview_page").performScrollToNode(hasTestTag("lesson_row_paul_2_justified_grace"))
        compose.onNodeWithTag("lesson_row_paul_2_justified_grace").performClick()
        waitFor("study_lesson_page")
        label("lesson_xref_paul_2_justified_grace_0", "Cross-references")
    }

    @Test fun planDayReadingOpensRelatedPassagesAndFollowingOneLandsInTheReader() {
        launch { s, now -> s.setPlanStart("bible_year", now) }
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_plans"))
        compose.onNodeWithTag("nav_plans").performClick()
        waitFor("plan_card_bible_year")
        compose.onNodeWithTag("plan_card_bible_year").performClick()
        waitFor("plan_reading_0")
        // Day 1: Genesis 1, Genesis 2, Matthew 1, Psalm 1; each one has its action.
        for (i in 0..3) compose.onNodeWithTag("plan_detail_screen").performScrollToNode(hasTestTag("plan_xref_$i"))
        label("plan_xref_0", "Referencias")
        compose.onNodeWithTag("plan_xref_0").performClick()
        waitFor("passage_xref_sheet")
        waitFor("passage_xref_0_0")
        compose.onNodeWithTag("passage_xref_0_0").performClick()
        waitFor("bible_reader")
        compose.onNodeWithTag("passage_xref_sheet").assertDoesNotExist()
        // Back returns to the plan day, where the reading is still to be marked.
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        waitFor("plan_mark_done")
    }

    @Test fun catechismDaysOfThePlanHaveNoBibleReferences() {
        launch { s, now -> s.setPlanStart("catechism_year", now) }
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_plans"))
        compose.onNodeWithTag("nav_plans").performClick()
        waitFor("plan_card_catechism_year")
        compose.onNodeWithTag("plan_card_catechism_year").performClick()
        waitFor("plan_reading_0")
        compose.onNodeWithTag("plan_xref_0").assertDoesNotExist()
    }
}
