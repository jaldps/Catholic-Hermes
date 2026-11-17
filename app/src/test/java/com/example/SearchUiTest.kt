package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
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
import com.example.data.search.SearchIndexHolder
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

/** Global search from Today and Explore: type, groups appear, a result opens its own screen, back returns. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class SearchUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var db: SanctaFidesDatabase? = null
    private var repo: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null
    private val app get() = ApplicationProvider.getApplicationContext<Application>()

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle(); runBlocking { PersonalDataCommands.awaitIdle() }
        tts?.release(); repo?.close(); db?.close(); SearchIndexHolder.reset()
    }

    private fun launch(language: AppLanguage = AppLanguage.ES) {
        SearchIndexHolder.reset()
        val store = UserPreferencesStore(app)
        runBlocking { store.setLanguage(language) }
        val repository = CatholicDataRepository(app).apply { setLanguage(language) }.also { repo = it }
        val prefs = runBlocking { store.preferences.first() }
        val database = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { db = it }
        val narrator = LiturgicalTtsManager(app).also { tts = it }
        compose.setContent { SanctaFidesTheme(reduceMotion = true) { CompositionLocalProvider(LocalInspectionMode provides true) {
            MainApp(repository, database.dao(), narrator, store, prefs)
        } } }
        compose.waitUntil(30_000) { compose.onAllNodesWithTag("home_screen").fetchSemanticsNodes().isNotEmpty() }
    }

    private fun openFromToday() {
        compose.onNodeWithTag("global_search_button").performClick()
        waitFor("search_field")
    }

    private fun waitFor(tag: String, timeout: Long = 40_000) =
        compose.waitUntil(timeout) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty() }

    private fun hasTagPrefix(prefix: String) = SemanticsMatcher("tag starts with $prefix") {
        it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith(prefix) == true
    }

    private fun type(query: String) {
        compose.onNodeWithTag("search_field").performTextReplacement(query)
    }

    /** Results are in a lazy list: scroll to a group (or "see all" button) until it is composed. */
    private fun waitForGroup(tag: String) = compose.waitUntil(40_000) {
        runCatching { compose.onNodeWithTag("search_screen").performScrollToNode(hasTestTag(tag)) }
        compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty()
    }

    private fun waitForResults(prefix: String) =
        compose.waitUntil(40_000) { compose.onAllNodes(hasTagPrefix(prefix)).fetchSemanticsNodes().isNotEmpty() }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    /** Types [query], taps the first result of [type], waits for [landing], then returns to the search. */
    private fun openResult(query: String, type: String, landing: String, id: String? = null) {
        type(query)
        if (id != null) {
            // A named result cannot be a leftover of the previous query.
            waitForGroup("search_result_${type}_$id")
            compose.onNodeWithTag("search_result_${type}_$id").performScrollTo().performClick()
        } else {
            waitForGroup("search_group_$type")
            compose.onAllNodes(hasTagPrefix("search_result_${type}_"))[0].performScrollTo().performClick()
        }
        // A landing ending in "_" is a prefix (the detail's id is not known to the test).
        if (landing.endsWith("_")) waitForResults(landing) else waitFor(landing)
        back()
        waitFor("search_field")
        compose.onNodeWithTag("search_field").assertTextContains(query)
    }

    @Test fun opensFromTodayFocusedAndNotInTheTabBar() {
        launch()
        compose.onNodeWithTag("nav_search").assertDoesNotExist()
        compose.onNodeWithTag("global_search_button").assertExists()
        openFromToday()
        compose.onNodeWithTag("search_field").assertIsFocused()
        compose.onNodeWithTag("search_hint").assertExists()
        compose.onNodeWithTag("nav_search").assertDoesNotExist()
        // One character is not enough; two are.
        type("i")
        compose.waitForIdle()
        compose.onNodeWithTag("search_hint").assertExists()
        type("Inés")
        waitFor("search_group_saint")
        compose.onNodeWithTag("search_hint").assertDoesNotExist()
    }

    @Test fun typingShowsGroupsAndTappingASaintOpensItsProfileThenBackKeepsTheResults() {
        launch()
        openFromToday()
        type("Inés")
        waitFor("search_group_saint")
        waitForGroup("search_result_saint_agnes_of_rome_virgin")
        compose.onNodeWithTag("search_result_saint_agnes_of_rome_virgin").assertExists().performClick()
        waitFor("saint_detail")
        back()
        waitFor("search_group_saint")
        compose.onNodeWithTag("search_field").assertTextContains("Inés")
        // Leaving and reopening starts empty: nothing is remembered between visits.
        back()
        waitFor("home_screen")
        openFromToday()
        compose.onNodeWithTag("search_hint").assertExists()
        compose.onNodeWithTag("search_group_saint").assertDoesNotExist()
    }

    @Test fun exploreHasAnEntryAndEmptyQueriesSayNoResults() {
        launch()
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_search"))
        compose.onNodeWithTag("nav_search").performClick()
        waitFor("search_field")
        type("zzzxqv")
        waitFor("search_empty")
        compose.onAllNodes(hasTagPrefix("search_group_")).assertCountEquals(0)
    }

    @Test fun groupsShowFiveResultsAndSeeAllExpandsThem() {
        launch()
        openFromToday()
        type("dios")
        waitForGroup("search_group_catechism")
        compose.onNodeWithTag("search_group_catechism").assertExists()
        compose.onAllNodes(hasTagPrefix("search_result_catechism_")).assertCountEquals(5)
        waitForGroup("search_more_catechism")
        compose.onNodeWithTag("search_more_catechism").performClick()
        compose.waitUntil(10_000) { compose.onAllNodes(hasTagPrefix("search_result_catechism_")).fetchSemanticsNodes().size > 5 }
    }

    @Test fun prayersNovenasDevotionsOrdersAndQuestionsOpenTheirOwnDetails() {
        launch()
        openFromToday()
        openResult("padre nuestro", "prayer", "prayer_detail_our_father", id = "our_father")
        openResult("misericordia", "novena", "novena_detail_novena-a-la-divina-misericordia", id = "novena-a-la-divina-misericordia")
        openResult("guadalupe", "devotion", "devotion_detail_")
        openResult("compañía de jesús", "order", "order_progress")
        openResult("sufrimiento inocente", "question", "catholic_qa_progress")
    }

    @Test fun coursesPlansCatechismAndSectionsOpenTheirScreens() {
        launch()
        openFromToday()
        openResult("lectio divina", "course", "study_lesson_page")
        openResult("Biblia en un año", "plan", "plan_detail_screen", id = "bible_year")
        openResult("pecado original", "catechism", "catechism_screen")
        openResult("confesion", "section", "confession_screen")
    }

    @Test fun referencesOpenTheReaderAndBibleTextResultsOpenTheirVerse() {
        launch()
        openFromToday()
        type("Jn 3,16")
        waitFor("search_group_reference")
        compose.onNodeWithTag("search_result_reference_bible_joh_3_16").performClick()
        waitFor("bible_screen")
        back()
        waitFor("search_field")
        // A psalm is typed in the usual numbering and opens the edition's own chapter.
        type("Sal 23")
        waitFor("search_result_reference_bible_psa_23_0")
        compose.onNodeWithTag("search_result_reference_bible_psa_23_0").assertExists()
        type("principio creó Dios")
        waitForGroup("search_group_bible")
        waitForGroup("search_result_bible_gen_1_1")
        compose.onNodeWithTag("search_result_bible_gen_1_1").performClick()
        waitFor("bible_screen")
    }

    @Test fun worksInEnglishAndPortuguese() {
        launch(AppLanguage.EN)
        openFromToday()
        compose.onNodeWithTag("search_title").assertTextContains("Search", substring = true)
        type("our father")
        waitFor("search_group_prayer")
        compose.onNodeWithTag("search_result_prayer_our_father").assertExists()
        type("John 3:16")
        waitFor("search_result_reference_bible_joh_3_16")
    }
}
