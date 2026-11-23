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
import com.example.data.repository.DevotionsCatalog
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class DevotionsScreenTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null
    private var repository: CatholicDataRepository? = null

    @After fun close() {
        DevotionsCatalog.override = null
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { com.example.data.persistence.PersonalDataCommands.awaitIdle() }
        narration?.release(); repository?.close(); database?.close()
    }

    private fun launch(design: String = "LUMEN") {
        DevotionsCatalog.override = devotionsFixtureCatalog()
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repo = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES) }.also { repository = it }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val prefs = UserPreferences(AppLanguage.ES, design, "LITURGICAL_DYNAMIC", "LIGHT", "IDENTITY_DEFAULT", 1f, 1f, "bundled", false, true)
        compose.setContent {
            SanctaFidesTheme(designLanguage = design, darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, UserPreferencesStore(context), prefs) }
            }
        }
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_study_mary_jesus"))
        compose.onNodeWithTag("nav_study_mary_jesus").performSemanticsAction(SemanticsActions.OnClick)
        compose.waitUntil(5_000) { compose.onAllNodesWithTag("devotions_explorer_screen").fetchSemanticsNodes().isNotEmpty() }
    }

    private fun open(id: String) {
        compose.onNodeWithTag("devotions_explorer_screen").performScrollToNode(hasTestTag("devotion_card_$id"))
        compose.revealAboveDock("devotion_card_$id").performClick()
        compose.onNodeWithTag("devotion_detail_$id").assertIsDisplayed()
    }

    private fun has(id: String, vararg tags: String) {
        for (tag in tags) {
            compose.onNodeWithTag("devotion_detail_$id").performScrollToNode(hasTestTag(tag))
            compose.onNodeWithTag(tag).assertExists()
        }
    }

    private fun marian(design: String) {
        launch(design)
        open("guadalupe")
        has("guadalupe", "devotion_recognition", "devotion_practice", "devotion_promise_source", "devotion_sources")
        compose.onNodeWithText("Nican Mopohua", substring = true).assertExists()
        compose.onNodeWithTag("devotion_prayer_source").assertDoesNotExist()
        compose.onNodeWithTag("devotion_prayer_listen").assertDoesNotExist()
    }

    @Test fun marianDevotionShowsDetailsInLumen() = marian("LUMEN")
    @Test fun marianDevotionShowsDetailsInSanctuary() = marian("SANCTUARY")

    @Test fun christDevotionShowsPrayerSourceAndHidesRemovedPromises() {
        launch()
        compose.onNodeWithText("Cristo").performClick()
        open("sacred_heart_jesus")
        has("sacred_heart_jesus", "devotion_recognition", "devotion_practice", "devotion_prayer_source", "devotion_sources")
        compose.onNodeWithTag("devotion_promise_source").assertDoesNotExist()
        compose.onNodeWithText("Promesas").assertDoesNotExist()
    }

    @Test fun newMarianDevotionWithoutPrayerOpens() {
        launch()
        open("virgen_nueva")
        has("virgen_nueva", "devotion_recognition", "devotion_sources")
        compose.onNodeWithTag("devotion_prayer_listen").assertDoesNotExist()
        compose.onNodeWithTag("devotion_promise_source").assertDoesNotExist()
    }

    @Test fun marianDevotionShowsImageCredit() {
        launch()
        open("guadalupe")
        compose.onNodeWithTag("devotion_image_credit").assertExists()
        compose.onNodeWithTag("devotion_image_credit").assertTextContains("Imagen:", substring = true)
    }

    @Test fun christDevotionShowsImageCredit() {
        launch()
        compose.onNodeWithText("Cristo").performClick()
        open("senor_milagros")
        compose.onNodeWithTag("devotion_image_credit").assertExists()
    }
}
