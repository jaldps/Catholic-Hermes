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
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * T-043: tablets and large screens. From 600 dp the tab bar is a rail on the
 * leading edge; from 840 dp Saints, Orders and Devotions show list and detail
 * side by side. Phones keep the bottom bar.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w840dp-h1200dp-xhdpi")
class TabletLayoutTest(private val identity: DesignLanguage) {
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

    private fun launch() {
        SaintsCatalog.override = saintsFixtureCatalog()
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repo = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES) }.also { repository = it }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val prefs = UserPreferences(AppLanguage.ES, identity.id, "LITURGICAL_DYNAMIC", "LIGHT", "IDENTITY_DEFAULT", 1f, 1f, "bundled", false, true)
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, db.dao(), tts, UserPreferencesStore(context), prefs) }
            }
        }
        compose.onNodeWithTag("home_screen").assertIsDisplayed()
    }

    private fun bounds(tag: String) = compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot

    private fun openFromMore(destinationTag: String, screenTag: String) {
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag(destinationTag))
        compose.onNodeWithTag(destinationTag).performClick()
        compose.onNodeWithTag(screenTag).assertIsDisplayed()
    }

    private fun assertRail() {
        compose.onNodeWithTag("navigation_rail").assertIsDisplayed()
        compose.onNodeWithTag("bottom_nav_bar").assertDoesNotExist()
        val rail = bounds("navigation_rail")
        // The rail stands on the leading edge, inside the window, and the content never sits under it.
        val dp = compose.density.density
        assertTrue("rail starts at the leading edge: $rail", rail.left < 24f * dp)
        assertTrue("rail is a narrow strip: $rail", rail.width < 110f * dp)
        val screen = bounds("home_screen")
        assertTrue("content ($screen) starts after the rail ($rail)", screen.left >= rail.right - 1f)
    }

    @Test fun railReplacesTheBottomBarAndEveryDestinationOpens() {
        launch()
        assertRail()
        listOf(
            "nav_home" to "home_screen",
            "nav_prayers" to "prayers_and_novenas_screen",
            "nav_bible" to "bible_screen",
            "nav_more" to "more_screen"
        ).forEach { (tab, screen) ->
            compose.onNodeWithTag(tab).assertHasClickAction().performClick()
            compose.onNodeWithTag(screen).assertIsDisplayed()
            compose.onNodeWithTag(tab).assertIsSelected()
            // Reselecting the current tab keeps it open and selected.
            compose.onNodeWithTag(tab).performClick()
            compose.onNodeWithTag(screen).assertIsDisplayed()
            compose.onNodeWithTag(tab).assertIsSelected()
        }
        // Four destinations, stacked top to bottom, all inside the rail.
        val tops = listOf("nav_home", "nav_prayers", "nav_bible", "nav_my_path", "nav_more").map { bounds(it).top }
        assertEquals(tops.sorted(), tops)
        val rail = bounds("navigation_rail")
        listOf("nav_home", "nav_prayers", "nav_bible", "nav_my_path", "nav_more").forEach {
            val b = bounds(it)
            assertTrue("$it inside the rail", b.left >= rail.left && b.right <= rail.right + 1f && b.top >= rail.top - 1f && b.bottom <= rail.bottom + 1f)
        }
    }

    @Test @Config(qualifiers = "w1280dp-h800dp-xhdpi") fun railOnALandscapeTablet() {
        launch()
        assertRail()
        compose.onNodeWithTag("nav_bible").performClick()
        compose.onNodeWithTag("bible_screen").assertIsDisplayed()
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.onNodeWithTag("prayers_and_novenas_screen").assertIsDisplayed()
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").assertIsDisplayed()
        compose.onNodeWithTag("nav_home").performClick()
        compose.onNodeWithTag("home_screen").assertIsDisplayed()
    }

    @Test @Config(qualifiers = "w411dp-h891dp-xhdpi") fun phonesKeepTheBottomBar() {
        launch()
        compose.onNodeWithTag("bottom_nav_bar").assertIsDisplayed()
        compose.onNodeWithTag("navigation_rail").assertDoesNotExist()
    }

    @Test @Config(qualifiers = "w599dp-h900dp-xhdpi") fun justBelowTheRailBreakpointIsStillAPhone() {
        launch()
        compose.onNodeWithTag("bottom_nav_bar").assertIsDisplayed()
        compose.onNodeWithTag("navigation_rail").assertDoesNotExist()
    }

    @Test @Config(qualifiers = "w600dp-h900dp-xhdpi") fun theRailStartsAt600() {
        launch()
        compose.onNodeWithTag("navigation_rail").assertIsDisplayed()
        compose.onNodeWithTag("bottom_nav_bar").assertDoesNotExist()
        // Below 840 dp lists still push their details, as on a phone.
        openFromMore("nav_saints", "saints_screen")
        compose.onNodeWithTag("two_pane").assertDoesNotExist()
    }

    @Test @Config(qualifiers = "w839dp-h1200dp-xhdpi") fun justBelowTheTwoPaneBreakpointPushesDetails() {
        launch()
        openFromMore("nav_saints", "saints_screen")
        compose.onNodeWithTag("two_pane").assertDoesNotExist()
        compose.onNodeWithTag("saint_row_francis_de_sales_bishop").performClick()
        compose.onNodeWithTag("saint_detail").assertIsDisplayed()
        compose.onNodeWithTag("saint_detail_back").assertIsDisplayed()
        compose.onNodeWithTag("saints_screen").assertDoesNotExist()
    }

    private fun assertSideBySide() {
        compose.onNodeWithTag("two_pane_list").assertIsDisplayed()
        compose.onNodeWithTag("two_pane_detail").assertIsDisplayed()
        val list = bounds("two_pane_list")
        val detail = bounds("two_pane_detail")
        val dp = compose.density.density
        assertTrue("list is on the left of the detail", list.right <= detail.left + 1f)
        assertEquals("list pane is 360 dp", 360f * dp, list.width, 1.5f)
        assertTrue("detail pane has room to read: $detail", detail.width >= 370f * dp)
    }

    @Test fun saintsShowListAndDetailSideBySideAndSelectionUpdatesTheDetail() {
        launch()
        openFromMore("nav_saints", "saints_screen")
        assertSideBySide()
        // The first profile is open without choosing anything; there is no page to go back from.
        compose.onNodeWithTag("saint_detail").assertIsDisplayed()
        compose.onNodeWithText("Obispo de Ginebra.").assertExists()
        compose.onNodeWithTag("saint_detail_back").assertDoesNotExist()
        compose.onNodeWithTag("saint_row_francis_de_sales_bishop").assertIsSelected()
        val detail = bounds("saint_detail")
        assertTrue("the detail is on the right pane", detail.left >= bounds("two_pane_list").right - 1f)

        compose.onNodeWithTag("saints_screen").performScrollToNode(hasTestTag("saint_row_agatha_of_sicily_virgin"))
        compose.onNodeWithTag("saint_row_agatha_of_sicily_virgin").performClick()
        compose.onNodeWithText("Virgen y mártir.").assertExists()
        compose.onNodeWithText("Obispo de Ginebra.").assertDoesNotExist()
        compose.onNodeWithTag("saint_row_agatha_of_sicily_virgin").assertIsSelected()
        // The list is still there: nothing was pushed.
        compose.onNodeWithTag("saints_screen").assertIsDisplayed()
        compose.onNodeWithTag("saint_detail").assertIsDisplayed()
    }

    @Test @Config(qualifiers = "w1280dp-h800dp-xhdpi") fun saintsTwoPaneOnALandscapeTablet() {
        launch()
        openFromMore("nav_saints", "saints_screen")
        assertSideBySide()
        compose.onNodeWithText("Obispo de Ginebra.").assertExists()
        compose.onNodeWithTag("saints_screen").performScrollToNode(hasTestTag("saint_row_agatha_of_sicily_virgin"))
        compose.onNodeWithTag("saint_row_agatha_of_sicily_virgin").performClick()
        compose.onNodeWithText("Virgen y mártir.").assertExists()
        // The reading column in the detail pane is centred, not stretched across 800+ dp.
        val pane = bounds("two_pane_detail")
        val detail = bounds("saint_detail")
        assertTrue("detail is at most the reading column wide: $detail", detail.width <= 700f * compose.density.density)
        assertEquals("detail is centred in its pane", (pane.left + pane.right) / 2f, (detail.left + detail.right) / 2f, 2f)
    }

    @Test fun ordersShowListAndDetailAndSelectionUpdatesTheDetail() {
        launch()
        openFromMore("nav_study_orders", "doctrine_and_history_screen")
        assertSideBySide()
        compose.onNodeWithTag("orders_list").assertIsDisplayed()
        compose.onNodeWithTag("doctrine_entry_page").assertIsDisplayed()
        val firstId = repository!!.religiousOrders[0].id
        val other = repository!!.religiousOrders[3]
        compose.onNodeWithTag("order_$firstId").assertIsSelected()
        compose.onNodeWithTag("orders_list").performScrollToNode(hasTestTag("order_${other.id}"))
        compose.onNodeWithTag("order_${other.id}").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("order_${other.id}").assertIsSelected()
        compose.onNodeWithTag("orders_list").assertIsDisplayed()
        // The page beside the list is the fourth order.
        val total = repository!!.religiousOrders.size
        compose.onNodeWithTag("order_progress").assert(
            SemanticsMatcher.expectValue(androidx.compose.ui.semantics.SemanticsProperties.StateDescription, "Orden 4 de $total"))
    }

    @Test fun devotionsShowListAndDetailAndSelectionUpdatesTheDetail() {
        launch()
        openFromMore("nav_study_mary_jesus", "doctrine_and_history_screen")
        assertSideBySide()
        compose.onNodeWithTag("devotions_explorer_screen").assertIsDisplayed()
        val first = repository!!.marianApparitions[0].id
        val other = repository!!.marianApparitions[2].id
        compose.onNodeWithTag("devotion_detail_$first").assertIsDisplayed()
        compose.onNodeWithTag("devotion_card_$first").assertIsSelected()
        compose.onNodeWithTag("devotions_explorer_screen").performScrollToNode(hasTestTag("devotion_card_$other"))
        compose.onNodeWithTag("devotion_card_$other").performClick()
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag("devotion_detail_$other").assertExists()
        compose.onNodeWithTag("devotion_detail_$first").assertDoesNotExist()
        compose.onNodeWithTag("devotion_card_$other").assertIsSelected()
        compose.onNodeWithTag("devotions_explorer_screen").assertIsDisplayed()
    }

    @Test fun readingPagesAreCentredInAReadingColumn() {
        launch()
        // A wide window does not stretch a screen edge to edge: the page is a centred column.
        compose.onNodeWithTag("nav_prayers").performClick()
        val screen = bounds("prayers_and_novenas_screen")
        val rail = bounds("navigation_rail")
        val dp = compose.density.density
        assertTrue("column no wider than the reading width: $screen", screen.width <= (640f + 2 * 20f) * dp + 1f)
        // The rail's reserved width (about 90 dp) is on the left of the column, nothing on the right.
        val window = compose.activity.window.decorView.width
        val offset = (screen.left - (window - screen.right)) / dp
        assertTrue("centred in the space beside the rail (offset $offset dp)", offset in 80f..100f)
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun identities() = listOf(DesignLanguage.APPLE, DesignLanguage.CLASSIC, DesignLanguage.GLASS, DesignLanguage.SANCTUARY)
    }
}
