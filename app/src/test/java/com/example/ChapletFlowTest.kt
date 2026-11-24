package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ChapletCatalog
import com.example.data.repository.ChapletGroup
import com.example.data.repository.ChapletPrayers
import com.example.data.repository.PrayerAndNovenaData
import com.example.data.repository.PrayerDetailsCatalog
import com.example.ui.screens.buildChapletGuide
import com.example.ui.screens.searchChaplets
import com.example.ui.screens.ChapletPlan
import com.example.ui.screens.ChapletVariablesMemory
import com.example.ui.screens.RosaryScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** «Otras ▾» → the catalogue → a chaplet prayed from its first line to its last, on three different strings. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class ChapletFlowTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var voice: LiturgicalTtsManager? = null

    @Before fun fresh() {
        ChapletCatalog.override = chapletFileCatalog
        ChapletVariablesMemory.clear()
        compose.activity.getSharedPreferences("unfinished_prayer_session", 0).edit().clear().commit()
    }
    @After fun close() { ChapletCatalog.override = null; ChapletVariablesMemory.clear(); voice?.release() }

    private fun launch(tab: Int = 0) {
        val repo = CatholicDataRepository(compose.activity).apply { setLanguage(AppLanguage.EN) }
        val audio = LiturgicalTtsManager(compose.activity).also { voice = it }
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { RosaryScreen(repo, audio, AppLanguage.EN, initialTab = tab) }
        } }
        compose.waitForIdle()
    }

    private fun openCatalog() {
        compose.onNodeWithTag("other_chaplets_segment").performClick()
        compose.onNodeWithTag("chaplet_catalog").assertExists()
    }

    private fun pick(id: String) {
        compose.onNodeWithTag("chaplet_row_$id").performScrollTo().performClick()
        compose.waitForIdle()
    }

    /** Next on every step to the last one, checking each step shows its prayer and the counter follows. */
    private fun walk(id: String) {
        val plan = ChapletPlan(chapletFileCatalog.get(id)!!)
        val total = plan.stepCount
        for (i in 0 until total) {
            compose.onNodeWithTag("prayer_leader_text").assertExists()
            compose.onNodeWithTag("chaplet_step_counter", useUnmergedTree = true).assertTextEquals("Step ${i + 1} of $total")
            if (i < total - 1) compose.onNodeWithTag("next_chaplet_step_button").assertIsEnabled().performClick()
        }
        compose.onNodeWithTag("next_chaplet_step_button").assertIsNotEnabled()
        compose.onNodeWithTag("prev_chaplet_step_button").assertIsEnabled()
    }

    @Test fun catalogShowsSixGroupsAndSearches() {
        launch()
        openCatalog()
        ChapletGroup.entries.forEach { compose.onNodeWithTag("chaplet_group_${it.key}").performScrollTo().assertExists() }
        compose.onNodeWithTag("chaplet_row_siete-dolores-corona").performScrollTo().assertExists()
        compose.onNodeWithTag("chaplet_search").assertExists()
    }

    @Test fun searchFindsByTitleSummaryAndGroupIgnoringAccents() {
        val all = chapletFileCatalog.all
        assertEquals(listOf("coronilla-lagrimas"), searchChaplets(all, "tears", AppLanguage.EN).map { it.id }.filter { it == "coronilla-lagrimas" })
        assertTrue(searchChaplets(all, "lagrimas", AppLanguage.ES).any { it.id == "coronilla-lagrimas" })
        assertEquals(all.size, searchChaplets(all, "  ", AppLanguage.ES).size)
        assertTrue(searchChaplets(all, "zzzzqq", AppLanguage.EN).isEmpty())
    }

    @Test fun sevenSorrowsWalksFromFirstToLastPrayer() {
        launch(); openCatalog(); pick("siete-dolores-corona")
        compose.onNodeWithTag("chaplet_catalog").assertDoesNotExist()
        compose.onNodeWithTag("chaplet_bead_counter").assertExists()
        walk("siete-dolores-corona")
    }

    @Test fun saintAnnesChapletHasNoStringAndAsksForAnIntentionFirst() {
        launch(); openCatalog(); pick("santa-ana-coronilla")
        compose.onNodeWithTag("chaplet_variables_sheet").assertExists()
        compose.onNodeWithTag("chaplet_answer_intention").performTextInput("for my family")
        compose.onNodeWithTag("chaplet_variables_confirm").performClick()
        compose.onNodeWithTag("chaplet_variables_sheet").assertDoesNotExist()
        compose.onNodeWithTag("chaplet_bead_counter").assertDoesNotExist()
        walk("santa-ana-coronilla")
    }

    @Test fun tearsChapletPraysVersicleAndResponseToTheEnd() {
        launch(); openCatalog(); pick("coronilla-lagrimas")
        // The first V/R step shows both voices: the versicle and, below it, the response.
        val steps = buildChapletGuide(chapletFileCatalog.get("coronilla-lagrimas")!!, AppLanguage.EN,
            ChapletPrayers.of(PrayerDetailsCatalog.default.mergeInto(PrayerAndNovenaData.vaticanOfficialPrayers))).guide.steps
        val first = steps.indexOfFirst { it.response.isNotBlank() }
        assertTrue(first > 0)
        repeat(first) { compose.onNodeWithTag("next_chaplet_step_button").performClick() }
        compose.onNodeWithText(steps[first].leader, substring = true).assertExists()
        compose.onNodeWithText(steps[first].response, substring = true).assertExists()
        // Back to the first line and through to the last.
        repeat(first) { compose.onNodeWithTag("prev_chaplet_step_button").performClick() }
        walk("coronilla-lagrimas")
    }

    @Test fun theChapletInProgressSurvivesGoingBackToTheRosary() {
        launch(); openCatalog(); pick("san-charbel")
        repeat(4) { compose.onNodeWithTag("next_chaplet_step_button").performClick() }
        compose.onNodeWithTag("chaplet_step_counter", useUnmergedTree = true).assertTextEquals("Step 5 of ${ChapletPlan(chapletFileCatalog.get("san-charbel")!!).stepCount}")
        compose.onNodeWithText("Rosary").performClick()
        compose.onNodeWithTag("next_bead_button").assertExists()
        openCatalog(); pick("san-charbel")
        compose.onNodeWithTag("chaplet_step_counter", useUnmergedTree = true).assertTextEquals("Step 5 of ${ChapletPlan(chapletFileCatalog.get("san-charbel")!!).stepCount}")
    }

    @Test fun orarOpensTheCatalogueWhenNothingIsInProgress() {
        launch(tab = 2)
        compose.onNodeWithTag("chaplet_catalog").assertExists()
        assertTrue(chapletFileCatalog.all.isNotEmpty())
        assertEquals(55, chapletFileCatalog.all.size)
    }
}
