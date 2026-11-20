package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextReplacement
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.HabitCompletionLogEntity
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.AppDestination
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.time.LocalDate

/** «Mi camino»: the one Explore entry, the four sections, the old destinations as links, and «Hoy en mi camino». */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class MyPathUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val app get() = ApplicationProvider.getApplicationContext<Application>()
    private var db: SanctaFidesDatabase? = null
    private var repo: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null
    private lateinit var store: UserPreferencesStore
    private lateinit var database: SanctaFidesDatabase
    private var today = 0L

    @After fun close() = dispose()

    /** Stops the screens' collectors, lets queries already running on IO finish, then closes what they read. */
    private fun dispose() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle(); runBlocking { PersonalDataCommands.awaitIdle() }
        Thread.sleep(300)
        tts?.release(); repo?.close(); db?.close()
    }

    private fun launch(
        initial: AppDestination = AppDestination.HOME,
        language: AppLanguage = AppLanguage.ES,
        beforeStart: suspend (SanctaFidesDatabase, UserPreferencesStore, Long) -> Unit = { _, _, _ -> }
    ) {
        store = UserPreferencesStore(app)
        runBlocking { store.setLanguage(language); store.restartPlan("bible_year"); store.restartPlan("catechism_year") }
        val repository = CatholicDataRepository(app).apply { setLanguage(language) }.also { repo = it }
        today = repository.currentDate.value.toEpochDay()
        database = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { db = it }
        runBlocking { beforeStart(database, store, today) }
        val prefs = runBlocking { store.preferences.first() }.copy(language = language, hasChosenLanguage = true)
        val narrator = LiturgicalTtsManager(app).also { tts = it }
        compose.activity.setContent { SanctaFidesTheme(reduceMotion = true) { CompositionLocalProvider(LocalInspectionMode provides true) {
            MainApp(repository, database.dao(), narrator, store, prefs, initialDestination = initial)
        } } }
    }

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    private fun waitFor(tag: String, timeout: Long = 30_000) = compose.waitUntil(timeout) { exists(tag) }
    private fun gone(tag: String) = compose.waitUntil(15_000) { !exists(tag) }
    /** The row's texts live in its children; look for [text] among the descendants of [tag]. */
    private fun assertRowText(tag: String, text: String) =
        compose.onNode(hasText(text, substring = true) and hasAnyAncestor(hasTestTag(tag)), useUnmergedTree = true).assertExists()
    private fun tag(tag: String) = compose.onNodeWithTag(tag, useUnmergedTree = true)
    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    private fun openHubFromExplore() {
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_my_path"))
        compose.onNodeWithTag("nav_my_path").performClick()
        waitFor("my_path_screen")
    }

    private fun openRow(tag: String) {
        compose.onNodeWithTag("my_path_screen").performScrollToNode(hasTestTag(tag))
        // The scroll stops as soon as the row shows; finish it so the row clears the floating dock.
        val row = compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot
        val dock = compose.onNodeWithTag("bottom_nav_bar").fetchSemanticsNode().boundsInRoot
        if (row.bottom > dock.top) {
            compose.onNodeWithTag("my_path_screen").performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, row.bottom - dock.top + 24f) }
            compose.waitForIdle()
        }
        compose.onNodeWithTag(tag).performClick()
    }

    private fun hasTagPrefix(prefix: String) = SemanticsMatcher("tag starts with $prefix") {
        it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith(prefix) == true
    }

    @Test fun exploreHasOneEntryThatReplacesHabitsAndSpiritualLife() {
        launch()
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_my_path"))
        tag("nav_my_path").assertIsDisplayed()
        // The old rows are gone; Confession keeps its own row.
        assert(!exists("nav_habits")) { "nav_habits must no longer be listed in Explore" }
        assert(!exists("nav_spiritual")) { "nav_spiritual must no longer be listed in Explore" }
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_confession"))
        tag("nav_confession").assertExists()
    }

    @Test fun everySectionOpensTheScreenItAlwaysHadAndBackReturnsToTheHub() {
        launch()
        openHubFromExplore()
        compose.onNodeWithTag("my_path_title", useUnmergedTree = true).assertExists()
        listOf(
            "my_path_today_habits" to "tab_habits_rule",
            "my_path_section_diary" to "spiritual_confession_entry",
            "my_path_section_temptations" to "tab_sin_avoider",
            "my_path_section_practices" to "spiritual_life_learn_picker"
        ).forEach { (row, landing) ->
            openRow(row)
            waitFor(landing)
            gone("my_path_screen")
            back()
            waitFor("my_path_screen")
        }
        // The Journal section is the journal; Practices is the grid of practices without it.
        openRow("my_path_section_diary")
        waitFor("add_first_journal_entry")
        assert(!exists("spiritual_life_learn_picker"))
        back(); waitFor("my_path_screen")
        openRow("my_path_section_practices")
        waitFor("spiritual_life_learn_picker")
        assert(!exists("add_first_journal_entry"))
        back(); waitFor("my_path_screen")
        // Hub back leaves to Explore.
        back()
        waitFor("more_screen")
    }

    @Test fun temptationsSectionBacksStraightToTheHubAndConfessionIsOneTapFromIt() {
        launch()
        openHubFromExplore()
        openRow("my_path_section_temptations")
        waitFor("tab_sin_avoider")
        back()
        waitFor("my_path_screen")
        openRow("my_path_confession_entry")
        // Confession opens on its first-use notice, then on the screen.
        compose.waitUntil(30_000) { compose.onAllNodes(hasText("Entendido")).fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithText("Entendido").performScrollTo().performClick()
        waitFor("confession_screen")
        back()
        waitFor("my_path_screen")
    }

    @Test fun practiceOpenedFromTheListReturnsToTheListThenTheHub() {
        launch()
        openHubFromExplore()
        openRow("my_path_section_practices")
        waitFor("spiritual_life_learn_picker")
        compose.onNodeWithText("Preguntas de fe").performClick()
        waitFor("trivia_progress")
        back()
        waitFor("spiritual_life_learn_picker")
        back()
        waitFor("my_path_screen")
    }

    @Test fun todayShortcutsOpenTheJournalAndTheQuizAndBackLeavesToToday() {
        launch()
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("today_more_toggle"))
        compose.onNodeWithTag("today_more_toggle").performClick()
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("home_gate_spiritual"))
        compose.onNodeWithTag("home_gate_spiritual").performClick()
        waitFor("spiritual_confession_entry")
        back()
        waitFor("home_screen")
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("home_gate_trivia"))
        compose.onNodeWithTag("home_gate_trivia").performClick()
        waitFor("trivia_progress")
        back()
        waitFor("home_screen")
    }

    @Test fun oldDestinationsAreLinksIntoTheMatchingSection() {
        listOf(
            Triple(AppDestination.HABITS_VIRTUES, "tab_habits_rule", "my_path_screen"),
            Triple(AppDestination.SPIRITUAL_LIFE, "spiritual_confession_entry", "my_path_screen"),
            Triple(AppDestination.MY_PATH, "my_path_screen", "tab_habits_rule")
        ).forEach { (destination, landing, absent) ->
            launch(initial = destination)
            waitFor(landing)
            assert(!exists(absent)) { "$destination must not also show $absent" }
            // A link's back leaves to the caller, not to the hub.
            if (destination != AppDestination.MY_PATH) {
                back()
                waitFor("more_screen")
            }
            dispose()
        }
    }

    @Test fun searchSectionsHabitsAndSpiritualLifeLandInTheirSection() {
        launch()
        compose.onNodeWithTag("global_search_button").performClick()
        waitFor("search_field")
        tag("search_field").performTextReplacement("Vida espiritual")
        compose.waitUntil(40_000) {
            runCatching { compose.onNodeWithTag("search_screen").performScrollToNode(hasTestTag("search_group_section")) }
            exists("search_group_section")
        }
        compose.onNodeWithTag("search_result_section_spiritual_life").performScrollTo().performClick()
        waitFor("spiritual_confession_entry")
        back()
        waitFor("search_field")
        tag("search_field").performTextReplacement("Hábitos")
        compose.waitUntil(40_000) {
            runCatching { compose.onNodeWithTag("search_screen").performScrollToNode(hasTestTag("search_result_section_habits_virtues")) }
            exists("search_result_section_habits_virtues")
        }
        compose.onNodeWithTag("search_result_section_habits_virtues").performScrollTo().performClick()
        waitFor("tab_habits_rule")
    }

    @Test fun todaySummaryShowsHabitsFidelityPlanAndNovenaAndEachRowOpensItsPlace() {
        launch { dbase, prefs, now ->
            val todayDate = LocalDate.now()
            val dao = dbase.dao()
            dao.insertCompletion(HabitCompletionLogEntity(habitId = "daily_rosary", completionDate = todayDate.toString()))
            dao.insertCompletion(HabitCompletionLogEntity(habitId = "daily_rosary", completionDate = todayDate.minusDays(1).toString()))
            dao.insertCompletion(HabitCompletionLogEntity(habitId = "daily_examen", completionDate = todayDate.minusDays(1).toString()))
            prefs.setPlanStart("bible_year", now - 3); prefs.setPlanDayDone("bible_year", 2, true)
            dao.setNovenaDay("novena-a-la-divina-misericordia", 1, true, (1..9).toSet())
        }
        openHubFromExplore()
        waitFor("my_path_today_habits")
        assertRowText("my_path_today_habits", "1 hecho · 11 pendientes")
        // Today and yesterday kept: two days in a row, now in the single row's subtitle.
        assertRowText("my_path_today_habits", "2 días seguidos")
        assert(!exists("my_path_today_fidelity")) { "the duplicate fidelity row is gone" }
        assert(!exists("my_path_section_habits")) { "the duplicate section row is gone" }
        waitFor("my_path_today_plan_bible_year")
        assertRowText("my_path_today_plan_bible_year", "Día 4 de 365")
        assert(!exists("my_path_today_plan_catechism_year")) { "a plan that was never started is not 'in progress'" }
        waitFor("my_path_today_novena_novena-a-la-divina-misericordia")
        assertRowText("my_path_today_novena_novena-a-la-divina-misericordia", "día 2 de 9")

        openRow("my_path_today_habits"); waitFor("tab_habits_rule"); back(); waitFor("my_path_screen")
        assert(!exists("my_path_today_fidelity")) { "one habits row only" }
        openRow("my_path_today_plan_bible_year"); waitFor("plan_detail_screen"); back(); waitFor("my_path_screen")
        openRow("my_path_today_novena_novena-a-la-divina-misericordia")
        waitFor("novena_detail_novena-a-la-divina-misericordia")
        back(); waitFor("my_path_screen")
    }

    @Test fun summaryWithNothingStartedHasOnlyHabitsAndAGentleFidelityLine() {
        launch()
        openHubFromExplore()
        waitFor("my_path_today_habits")
        assertRowText("my_path_today_habits", "0 hechos · 12 pendientes")
        assertRowText("my_path_today_habits", "Empieza hoy, a tu ritmo.")
        assert(!exists("my_path_today_plan_bible_year"))
        assert(!hasAnyTagWithPrefix("my_path_today_novena_"))
    }

    private fun hasAnyTagWithPrefix(prefix: String) =
        compose.onAllNodes(hasTagPrefix(prefix), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    @Test fun summaryIsEnglishAndPortugueseToo() {
        listOf(AppLanguage.EN to Triple("My path", "Today on my path", "Begin today, at your own pace."),
            AppLanguage.PT to Triple("Meu caminho", "Hoje no meu caminho", "Comece hoje, no seu ritmo.")).forEach { (language, texts) ->
            launch(initial = AppDestination.MY_PATH, language = language)
            waitFor("my_path_today_habits")
            tag("my_path_title").assertTextContains(texts.first, substring = true)
            assertRowText("my_path_today_header", texts.second)
            assertRowText("my_path_today_habits", texts.third)
            dispose()
        }
    }

    @Test fun myPathIsAFifthTabAndTappingItAgainReturnsToTheHub() {
        launch()
        compose.onNodeWithTag("nav_my_path").assertHasClickAction().performClick()
        waitFor("my_path_screen")
        waitFor("my_path_today_habits")
        // Go into a section, then tap the same tab: back to the hub, not a no-op.
        openRow("my_path_today_habits")
        waitFor("tab_habits_rule")
        compose.onNodeWithTag("nav_my_path").performClick()
        waitFor("my_path_screen")
        waitFor("my_path_today_habits")
        gone("tab_habits_rule")
    }

    @Test fun theHubHasExactlyOneRowToHabits() {
        launch(initial = AppDestination.MY_PATH)
        waitFor("my_path_today_habits")
        assert(compose.onAllNodesWithTag("my_path_today_habits", useUnmergedTree = true)
            .fetchSemanticsNodes().size == 1) { "a single habits row" }
        assert(!exists("my_path_today_fidelity")) { "no separate fidelity row" }
        assert(!exists("my_path_section_habits")) { "no duplicate habits section row" }
    }

    @Test fun aHabitsDeepLinkSelectsTheMyPathTab() {
        launch(initial = AppDestination.HABITS_VIRTUES)
        waitFor("tab_habits_rule")
        runCatching { compose.onNodeWithTag("nav_my_path", useUnmergedTree = true).assertIsSelected() }
            .getOrElse { throw AssertionError("nav_my_path must be the selected tab for a habits deep link: ${it.message}") }
    }
}
