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
import com.example.data.repository.OrdersCatalog
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
class OrdersScreenTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null
    private var repository: CatholicDataRepository? = null

    @After fun close() {
        OrdersCatalog.override = null
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { com.example.data.persistence.PersonalDataCommands.awaitIdle() }
        narration?.release(); repository?.close(); database?.close()
    }

    private fun launch(design: String = "LUMEN") {
        OrdersCatalog.override = ordersFixtureCatalog()
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
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_study_orders"))
        // The card can sit under the floating bar in Sanctuary, so invoke its click action directly.
        compose.onNodeWithTag("nav_study_orders").performSemanticsAction(SemanticsActions.OnClick)
        compose.waitUntil(5_000) { compose.onAllNodesWithTag("orders_list").fetchSemanticsNodes().isNotEmpty() }
    }

    private fun scroll(tag: String) = compose.onNodeWithTag("doctrine_entry_page").performScrollToNode(hasTestTag(tag))

    private fun scenario(design: String) {
        launch(design)
        compose.onNodeWithTag("orders_list").performScrollToNode(hasTestTag("order_franciscanos-ofm"))
        compose.onNodeWithTag("order_franciscanos-ofm").performClick()
        compose.onNodeWithTag("doctrine_entry_page").assertIsDisplayed()
        for (tag in listOf("order_kind", "order_motto", "order_approval", "order_saints", "order_presence", "order_sources")) {
            scroll(tag)
            compose.onNodeWithTag(tag).assertExists()
        }
        compose.onNodeWithText("Paz y bien").assertExists()
    }

    @Test fun orderReaderShowsDetailsInLumen() = scenario("LUMEN")
    @Test fun orderReaderShowsDetailsInSanctuary() = scenario("SANCTUARY")

    @Test fun newOrderWithoutPngOpens() {
        launch()
        compose.onNodeWithTag("orders_list").performScrollToNode(hasTestTag("order_mercedarios-odem"))
        compose.onNodeWithTag("order_mercedarios-odem").performClick()
        scroll("order_sources")
        compose.onNodeWithTag("order_sources").assertExists()
        compose.onNodeWithTag("order_motto").assertDoesNotExist()
    }

    @Test fun governanceOfficeOpensWithSources() {
        launch()
        compose.onNodeWithTag("orders_list").performScrollToNode(hasTestTag("orders_offices_tabs"))
        compose.onNodeWithText("Oficios").performClick()
        compose.onNodeWithTag("orders_list").performScrollToNode(hasText("Gobierno y Curia"))
        compose.onNodeWithText("Gobierno y Curia").assertExists()
        compose.onNodeWithTag("orders_list").performScrollToNode(hasTestTag("office_office_secretario_estado"))
        compose.onNodeWithTag("office_office_secretario_estado").performClick()
        scroll("office_sources")
        compose.onNodeWithTag("office_sources").assertExists()
    }
}
