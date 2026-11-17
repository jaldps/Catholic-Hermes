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
class ReadingPlansUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var db: SanctaFidesDatabase? = null
    private var repo: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null
    private val app get() = ApplicationProvider.getApplicationContext<Application>()
    private lateinit var store: UserPreferencesStore
    private var today = 0L

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle(); runBlocking { PersonalDataCommands.awaitIdle() }
        tts?.release(); repo?.close(); db?.close()
    }

    private fun launch(beforeStart: suspend (UserPreferencesStore, Long) -> Unit = { _, _ -> }) {
        store = UserPreferencesStore(app)
        runBlocking { store.setLanguage(AppLanguage.ES); store.restartPlan("bible_year"); store.restartPlan("catechism_year") }
        val repository = CatholicDataRepository(app).apply { setLanguage(AppLanguage.ES) }.also { repo = it }
        today = repository.currentDate.value.toEpochDay()
        runBlocking { beforeStart(store, today) }
        val prefs = runBlocking { store.preferences.first() }
        val database = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { db = it }
        val narrator = LiturgicalTtsManager(app).also { tts = it }
        compose.setContent { SanctaFidesTheme(reduceMotion = true) { CompositionLocalProvider(LocalInspectionMode provides true) {
            MainApp(repository, database.dao(), narrator, store, prefs)
        } } }
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_plans"))
        compose.onNodeWithTag("nav_plans").performClick()
        compose.waitUntil(15_000) { compose.onAllNodesWithTag("plan_card_bible_year").fetchSemanticsNodes().isNotEmpty() }
    }

    private fun text(tag: String) = compose.onNode(hasTestTag(tag), useUnmergedTree = true)
    private fun progress(id: String) = runBlocking { store.planProgress.first()[id] }
    private fun waitFor(tag: String) = compose.waitUntil(10_000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty() }

    @Test fun startMarkDoneToggleAndOpenReadingFromExplore() {
        launch()
        text("plan_progress_bible_year").assertTextContains("Sin empezar", substring = true)
        compose.onNodeWithTag("plan_card_catechism_year").assertExists()
        compose.onNodeWithTag("plan_card_bible_year").performClick()
        compose.onNodeWithTag("plan_start").performClick()
        compose.waitUntil(10_000) { progress("bible_year")?.startEpochDay == today }
        waitFor("plan_mark_done")
        text("plan_progress").assertTextEquals("Día 1 de 365 · 0 %")
        compose.onNodeWithTag("plan_catch_up").assertDoesNotExist()
        compose.onNodeWithTag("plan_mark_done").performClick()
        compose.waitUntil(10_000) { progress("bible_year")?.done == setOf(1) }
        compose.onNodeWithText("Leído · Deshacer").assertExists()
        compose.onNodeWithTag("plan_mark_done").performClick()
        compose.waitUntil(10_000) { progress("bible_year")?.done.isNullOrEmpty() }
        compose.onNodeWithText("Marcar como leído").assertExists()
        compose.onNodeWithText("Salmos 1", useUnmergedTree = true).assertExists()
        compose.onNodeWithTag("plan_reading_0").performClick()
        compose.waitUntil(15_000) { compose.onAllNodesWithTag("bible_screen").fetchSemanticsNodes().isNotEmpty() }
    }

    @Test fun threeDaysBehindShowsGentleCatchUpAndRebaseKeepsCompletedDays() {
        launch { s, now -> s.setPlanStart("bible_year", now - 3); s.setPlanDayDone("bible_year", 2, true) }
        text("plan_progress_bible_year").assertTextEquals("Día 4 de 365 · 0 %")
        compose.onNodeWithTag("plan_card_bible_year").performClick()
        waitFor("plan_catch_up")
        compose.onNodeWithText("Llevas 2 días pendientes", substring = true, useUnmergedTree = true).assertExists()
        compose.onNodeWithTag("plan_view_pending").performClick()
        compose.onNodeWithText("Día 1", useUnmergedTree = true).assertExists()
        compose.onNodeWithTag("plan_rebase").performClick()
        compose.waitUntil(10_000) { progress("bible_year")?.startEpochDay == today }
        compose.onNodeWithTag("plan_catch_up").assertDoesNotExist()
        text("plan_progress").assertTextEquals("Día 1 de 365 · 0 %")
        assertEquals(setOf(2), progress("bible_year")!!.done)
    }

    @Test fun anyDayCanBeOpenedAndCatechismRangeOpensTheCatechism() {
        launch { s, now -> s.setPlanStart("catechism_year", now) }
        compose.onNodeWithTag("plan_card_catechism_year").performClick()
        waitFor("plan_reading_0")
        compose.onNodeWithText("CIC 1–8", useUnmergedTree = true).assertExists()
        compose.onNodeWithTag("plan_detail_screen").performScrollToNode(hasTestTag("plan_day_3"))
        compose.onNodeWithTag("plan_day_3").performClick()
        compose.onNodeWithText("Día 3", useUnmergedTree = true).assertExists()
        compose.onNodeWithTag("plan_reading_0").performClick()
        compose.waitUntil(15_000) { compose.onAllNodesWithTag("catechism_screen").fetchSemanticsNodes().isNotEmpty() }
    }

    @Test fun restartNeedsConfirmation() {
        launch { s, now -> s.setPlanStart("bible_year", now); s.setPlanDayDone("bible_year", 1, true) }
        compose.onNodeWithTag("plan_card_bible_year").performClick()
        waitFor("plan_detail_screen")
        compose.onNodeWithTag("plan_detail_screen").performScrollToNode(hasTestTag("plan_restart"))
        compose.onNodeWithTag("plan_restart").performClick()
        compose.onNodeWithTag("plan_restart_dialog_dismiss").performClick()
        assertEquals(setOf(1), progress("bible_year")!!.done)
        compose.onNodeWithTag("plan_restart").performClick()
        compose.onNodeWithTag("plan_restart_dialog_confirm").performClick()
        compose.waitUntil(10_000) { progress("bible_year") == null }
        waitFor("plan_start")
    }
}
