package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.DevotionsCatalog
import com.example.data.repository.SaintsCatalog
import com.example.ui.screens.DoctrineAndHistoryScreen
import com.example.ui.screens.RosaryScreen
import com.example.ui.screens.SaintsScreen
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

/**
 * T-042: every narrating screen speaks through the one shared narrator, so
 * starting a recitation starts [com.example.data.audio.NarrationService] with
 * a title that names the content, and stopping stops it.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class NarrationEntryPointsTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val app = ApplicationProvider.getApplicationContext<Application>()
    private var narrator: LiturgicalTtsManager? = null
    private var repository: CatholicDataRepository? = null

    @After fun close() {
        SaintsCatalog.override = null
        DevotionsCatalog.override = null
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { com.example.data.persistence.PersonalDataCommands.awaitIdle() }
        narrator?.release(); repository?.close()
    }

    private fun show(language: AppLanguage, design: String = "LUMEN", content: @androidx.compose.runtime.Composable (CatholicDataRepository, LiturgicalTtsManager) -> Unit) {
        val repo = CatholicDataRepository(app).apply { setLanguage(language) }.also { repository = it }
        val tts = readyNarrator(app, language).also { narrator = it }
        compose.setContent {
            SanctaFidesTheme(designLanguage = design, darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) { content(repo, tts) }
            }
        }
    }

    /** The button reads «Listen», is pressed, reads «Stop» and starts the service; pressed again it stops it. */
    private fun assertTogglesAndNotifies(tag: String, expectedTitle: String, language: AppLanguage = AppLanguage.EN) {
        val listen = language.pick("Escuchar", "Listen", "Ouvir")
        val stop = language.pick("Detener", "Stop", "Parar")
        compose.onNode(hasTestTag(tag) and hasText(listen)).assertExists()
        app.narrationServiceStarted(); app.narrationServiceStopped()
        compose.onNodeWithTag(tag).performClick()
        compose.waitForIdle()
        compose.onNode(hasTestTag(tag) and hasText(stop)).assertExists()
        assertTrue("narration must start NarrationService", app.narrationServiceStarted())
        assertEquals(expectedTitle, notificationTitle(app))
        assertTrue(narrator!!.isPlaying.value)
        compose.onNodeWithTag(tag).performClick()
        compose.waitForIdle()
        compose.onNode(hasTestTag(tag) and hasText(listen)).assertExists()
        assertFalse(narrator!!.isPlaying.value)
        assertTrue("stop must stop NarrationService", app.narrationServiceStopped())
    }

    private fun AppLanguage.pick(es: String, en: String, pt: String) = when (this) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }

    private val francis get() = com.example.ui.screens.saintCelebrations().first { it.key == "francis_de_sales_bishop" }.title.get(AppLanguage.EN)

    @Test fun saintListenStartsServiceTitledWithTheSaintAndStops() {
        SaintsCatalog.override = saintsFixtureCatalog()
        show(AppLanguage.EN) { _, tts -> SaintsScreen(AppLanguage.EN, ttsManager = tts, initialKey = "francis_de_sales_bishop") }
        compose.onNodeWithTag("saint_detail").assertIsDisplayed()
        assertTogglesAndNotifies("saint_listen_button", francis)
        // It reads the name, then the biography.
        compose.onNodeWithTag("saint_listen_button").performClick()
        compose.waitForIdle()
        val spoken = narrator!!.currentlySpeaking.value.orEmpty()
        assertTrue(spoken, spoken.startsWith("$francis.") && spoken.endsWith("Bishop of Geneva."))
    }

    @Test fun leavingTheSaintStopsItsNarration() {
        SaintsCatalog.override = saintsFixtureCatalog()
        show(AppLanguage.EN) { _, tts -> SaintsScreen(AppLanguage.EN, ttsManager = tts, initialKey = "francis_de_sales_bishop") }
        compose.onNodeWithTag("saint_listen_button").performClick()
        compose.waitForIdle()
        assertTrue(narrator!!.isPlaying.value)
        compose.onNodeWithTag("saint_detail_back").performClick()
        compose.waitForIdle()
        assertFalse(narrator!!.isPlaying.value)
        assertTrue(app.narrationServiceStopped())
    }

    @Test fun questionAnswerListenUsesTheSharedNarrator() {
        show(AppLanguage.EN) { repo, tts -> DoctrineAndHistoryScreen(repo, AppLanguage.EN, initialEntry = "qa:0", ttsManager = tts) }
        compose.waitUntil(5_000) { compose.onAllNodesWithTag("catholic_qa_listen_button").fetchSemanticsNodes().isNotEmpty() }
        assertTogglesAndNotifies("catholic_qa_listen_button", "Question 1")
    }

    @Test fun orderListenUsesTheSharedNarrator() {
        show(AppLanguage.EN) { repo, tts -> DoctrineAndHistoryScreen(repo, AppLanguage.EN, initialEntry = "order:0", ttsManager = tts) }
        compose.waitUntil(5_000) { compose.onAllNodesWithTag("order_listen_button").fetchSemanticsNodes().isNotEmpty() }
        val name = repository!!.religiousOrders[0].name.get(AppLanguage.EN)
        assertTogglesAndNotifies("order_listen_button", name)
    }

    @Test fun devotionListenUsesTheSharedNarratorAndNeedsNoSecondEngine() {
        DevotionsCatalog.override = devotionsFixtureCatalog()
        show(AppLanguage.ES) { repo, tts -> DoctrineAndHistoryScreen(repo, AppLanguage.ES, initialEntry = "devotion:guadalupe", ttsManager = tts) }
        compose.waitUntil(5_000) { compose.onAllNodesWithTag("devotion_listen_button").fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("devotion_listen_button").performClick()
        compose.waitForIdle()
        assertTrue(app.narrationServiceStarted())
        assertTrue(narrator!!.isPlaying.value)
        val title = notificationTitle(app)
        assertFalse(title.isNullOrBlank())
        assertTrue(narrator!!.currentlySpeaking.value.orEmpty().startsWith(title!!))
        // The same narrator keeps the notification controls (no second player stole them).
        assertSame(narrator, LiturgicalTtsManager.active)
        compose.onNodeWithTag("devotion_listen_button").performClick()
        compose.waitForIdle()
        assertFalse(narrator!!.isPlaying.value)
        assertTrue(app.narrationServiceStopped())
    }

    @Test fun rosaryPrayAloudStartsServiceTitledWithTheDevotionAndStops() {
        show(AppLanguage.EN) { repo, tts -> RosaryScreen(repo, tts, AppLanguage.EN) }
        app.narrationServiceStarted(); app.narrationServiceStopped()
        compose.onNodeWithTag("toggle_prayer_audio_button").performClick()
        compose.waitForIdle()
        assertTrue(app.narrationServiceStarted())
        assertTrue(narrator!!.isPlaying.value)
        val title = notificationTitle(app).orEmpty()
        assertTrue(title, title.startsWith("Holy Rosary · "))
        compose.onNodeWithTag("toggle_prayer_audio_button").performClick()
        compose.waitForIdle()
        assertFalse(narrator!!.isPlaying.value)
        assertTrue(app.narrationServiceStopped())
    }
}
