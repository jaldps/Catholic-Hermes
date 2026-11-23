package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.TextLayoutResult
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.*
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.*
import com.example.ui.theme.SanctaFidesTheme
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Real TextLayoutResult and scroll range of every routine prayer; no synthetic samples. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class RosaryFitTest(private val width: Int, private val height: Int, private val lang: AppLanguage, private val guidance: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var voice: LiturgicalTtsManager? = null
    @After fun close() { voice?.release() }
    @Test fun allRoutinePrayersFitWithoutScrollingAndTextIsComplete() {
        landscapeViewport(compose, compose.activity, width, height)
        val context = compose.activity
        val repo = CatholicDataRepository(context).apply { setLanguage(lang) }
        val tts = LiturgicalTtsManager(context).also { voice = it }
        val source = repo.vaticanPrayers.first { it.id == "creed" }
        val creed = Said(source.leaderText.get(lang), source.responseText?.get(lang).orEmpty())
        val rosary = buildRosaryGuide(repo.getRosaryMystery(RosaryMysteryType.JOYFUL), creed, lang)
        val opening = repo.divineMercySteps.firstOrNull { it.stepIndex == 1 }
        val mercy = buildMercyGuide(creed, opening?.let { Said(it.leaderText.get(lang), it.responseText?.get(lang).orEmpty()) }, lang)
        val routine = rosary.steps.withIndex().filter { it.value.section == 1 && it.value.meditation == null }
            .distinctBy { it.value.name }.take(4).map { Triple(0, it.index, it.value) } +
            mercy.steps.withIndex().filter { it.value.section == 1 }.distinctBy { it.value.name }.take(2).map { Triple(1, it.index, it.value) }
        assertEquals(6, routine.size)
        var generation by mutableIntStateOf(0)
        var tab by mutableIntStateOf(0)
        var visible by mutableStateOf(false)
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                if (visible) key(generation) { RosaryScreen(repo, tts, lang, initialTab = tab) }
            }
        } }
        routine.forEach { (devotion, index, step) ->
            compose.runOnIdle { visible = false }; compose.waitForIdle()
            compose.runOnIdle {
                RosarySessionStore.save(context, RosarySession.opening(devotion).copy(rosaryStep = index, mercyStep = index,
                    mysterySelection = MysterySelection.Picked(RosaryMysteryType.JOYFUL), instructionsOn = guidance), RosaryMysteryType.JOYFUL)
                tab = devotion; generation++; visible = true
            }
            compose.waitForIdle()
            compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, height) }; compose.waitForIdle()
            val pane = compose.onNodeWithTag("prayer_content_scroll")
            val range = pane.fetchSemanticsNode().config[SemanticsProperties.VerticalScrollAxisRange]
            assertEquals("$lang $width guide=$guidance ${step.name} must fit", 0f, range.maxValue(), .5f)
            val viewport = pane.getUnclippedBoundsInRoot()
            for (text in listOf(step.leader, step.response).filter { it.isNotBlank() }) {
                val node = compose.onNodeWithText(text, useUnmergedTree = true)
                val layouts = mutableListOf<TextLayoutResult>()
                node.performSemanticsAction(SemanticsActions.GetTextLayoutResult) { it(layouts) }
                assertEquals(1, layouts.size)
                assertFalse("Prayer text must not be ellipsized", layouts.single().hasVisualOverflow)
                val b = node.getUnclippedBoundsInRoot()
                assertTrue("${step.name}: full text fits above gestures", b.top >= viewport.top && b.bottom <= viewport.bottom)
            }
            compose.onNodeWithTag(if (devotion == 0) "next_bead_button" else "next_mercy_step_button").assertIsDisplayed()
        }
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}x{1}-{2}-guide{3}")
        fun cases() = listOf(891 to 411, 800 to 360).flatMap { (w,h) -> AppLanguage.entries.flatMap { l ->
            listOf(false,true).map { g -> arrayOf<Any>(w,h,l,g) }
        } }
    }
}
