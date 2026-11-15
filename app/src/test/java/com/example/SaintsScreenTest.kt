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
import com.example.data.repository.SaintsCatalog
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
class SaintsScreenTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null
    private var repository: CatholicDataRepository? = null

    @After fun close() {
        SaintsCatalog.override = null
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { com.example.data.persistence.PersonalDataCommands.awaitIdle() }
        narration?.release(); repository?.close(); database?.close()
    }

    private fun launch(design: String = "LUMEN") {
        SaintsCatalog.override = saintsFixtureCatalog()
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
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_saints"))
        compose.onNodeWithTag("nav_saints").performClick()
        compose.onNodeWithTag("saints_screen").assertIsDisplayed()
    }

    private fun scenario() {
        launch()
        compose.onNodeWithTag("saints_search").assertIsDisplayed()
        compose.onNodeWithTag("saints_month_picker").assertIsDisplayed()
        compose.onNodeWithTag("saints_screen").performScrollToNode(hasTestTag("saint_row_agatha_of_sicily_virgin"))
        compose.onNodeWithTag("saints_screen").performScrollToNode(hasTestTag("saints_month_picker"))
        compose.onNodeWithTag("saints_month_picker").performClick()
        compose.onNodeWithTag("saints_month_1").performClick()
        compose.onNodeWithTag("saints_screen").performScrollToNode(hasTestTag("saint_row_francis_de_sales_bishop"))
        compose.onNodeWithTag("saint_row_agatha_of_sicily_virgin").assertDoesNotExist()
        compose.onNodeWithTag("saint_row_francis_de_sales_bishop").performClick()
        compose.onNodeWithTag("saint_detail").assertIsDisplayed()
        compose.onNodeWithText("Obispo de Ginebra.").assertExists()
        compose.onNodeWithText("Introducción a la vida devota, I, 3").assertExists()
        compose.onNodeWithTag("saint_detail_back").performClick()
        compose.onNodeWithTag("saints_screen").assertIsDisplayed()
    }

    @Test fun exploreFilterAndDetailInLumen() = scenario()

    @Test fun exploreFilterAndDetailInSanctuary() {
        launch("SANCTUARY")
        compose.onNodeWithTag("saints_month_picker").performClick()
        compose.onNodeWithTag("saints_month_2").performClick()
        compose.onNodeWithTag("saints_screen").performScrollToNode(hasTestTag("saint_row_agatha_of_sicily_virgin"))
        compose.onNodeWithTag("saint_row_agatha_of_sicily_virgin").performClick()
        compose.onNodeWithTag("saint_detail").assertIsDisplayed()
        compose.onNodeWithTag("saint_quote").assertDoesNotExist()
    }
}
