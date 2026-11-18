package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.models.NovenaStatus
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.NovenaCatalog
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** Orar -> Novenas: the origin badge on each card and the «Origen» filter, combined with search. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class NovenaOriginBadgeUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var db: SanctaFidesDatabase? = null
    private var repo: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null
    private val prefsScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    // Real ids from assets/novenas/provenance/*.json.
    private val historical = "novena-a-la-divina-misericordia"   // historical
    private val traditional = "novena-a-la-inmaculada-concepcion" // traditional
    private val modern = "novena-a-cristo-rey"                    // modern
    private val review = "novena-a-la-virgen-del-pilar"           // pastoral_review

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }
        tts?.release(); repo?.close(); db?.close(); prefsScope.cancel()
    }

    private fun openNovenas() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val file = File.createTempFile("novena-origin", ".preferences_pb", app.cacheDir).apply { delete() }
        val store = UserPreferencesStore(app, PreferenceDataStoreFactory.create(scope = prefsScope, produceFile = { file }))
        runBlocking { store.setLanguage(AppLanguage.ES) }
        val repository = CatholicDataRepository(app, store).also { repo = it; it.applyPersistedLanguage(AppLanguage.ES) }
        val database = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { db = it }
        val narration = LiturgicalTtsManager(app).also { tts = it }
        val initial = runBlocking { store.preferences.first() }
        compose.setContent {
            val preferences by store.preferences.collectAsState(initial = initial)
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repository, database.dao(), narration, store, preferences) }
            }
        }
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.onNodeWithText("Novenas", useUnmergedTree = true).performClick()
        compose.waitUntil(20_000) { compose.onAllNodesWithText("Cargando novenas…").fetchSemanticsNodes().isEmpty() }
    }

    private fun scrollToCard(id: String) = compose.onNodeWithTag("novena_index").performScrollToNode(hasTestTag("novena_card_$id"))

    private fun badge(id: String) = compose.onNodeWithTag("novena_badge_$id", useUnmergedTree = true)

    private fun pickOrigin(value: String) {
        compose.onNodeWithTag("novena_index").performScrollToNode(hasTestTag("novena_filter_chips"))
        compose.onNodeWithTag("novena_origin_picker", useUnmergedTree = true).performClick()
        compose.onNodeWithTag("novena_origin_$value").performClick()
        compose.waitForIdle()
    }

    private fun count(vararg statuses: NovenaStatus) =
        NovenaCatalog.default.allNovenas.count { it.provenance?.status in statuses }
    private val total get() = NovenaCatalog.default.allNovenas.size

    private fun waitForCounter(text: String) =
        compose.waitUntil(20_000) { compose.onAllNodesWithText(text).fetchSemanticsNodes().isNotEmpty() }

    @Test fun historicalAndModernCardsShowTheirBadge() {
        openNovenas()
        scrollToCard(historical)
        badge(historical).assertExists().assertTextEquals("Tradicional")
        scrollToCard(traditional)
        badge(traditional).assertTextEquals("Tradicional")
        scrollToCard(modern)
        badge(modern).assertTextEquals("Moderna")
        scrollToCard(review)
        badge(review).assertTextEquals("En revisión pastoral")
        // The label is text that belongs to the card's own semantics, not colour.
        compose.onNodeWithTag("novena_card_$modern").assert(
            SemanticsMatcher("description mentions the origin") {
                it.config.getOrNull(SemanticsProperties.ContentDescription)?.any { d -> d.endsWith(", Moderna") } == true
            }
        )
    }

    @Test fun everyCatalogNovenaWithProvenanceMapsToABadgeStatus() {
        openNovenas()
        // 262 of 262 carry provenance; the badge is what the status already says.
        assertEquals(total, count(NovenaStatus.HISTORICAL, NovenaStatus.TRADITIONAL, NovenaStatus.MODERN, NovenaStatus.PASTORAL_REVIEW))
    }

    @Test fun originFilterNarrowsTheList() {
        openNovenas()
        pickOrigin("pastoral_review")
        val n = count(NovenaStatus.PASTORAL_REVIEW)
        waitForCounter("$n de $total")
        scrollToCard(review)
        badge(review).assertTextEquals("En revisión pastoral")
        compose.onAllNodesWithTag("novena_card_$modern").assertCountEquals(0)
        compose.onAllNodesWithTag("novena_card_$historical").assertCountEquals(0)
        compose.onNodeWithTag("novena_origin_picker", useUnmergedTree = true).assertExists()
        compose.onAllNodesWithText("Origen: En revisión").assertCountEquals(1)

        pickOrigin("modern")
        waitForCounter("${count(NovenaStatus.MODERN)} de $total")
        scrollToCard(modern)
        compose.onAllNodesWithTag("novena_card_$review").assertCountEquals(0)

        pickOrigin("traditional")
        waitForCounter("${count(NovenaStatus.HISTORICAL, NovenaStatus.TRADITIONAL)} de $total")
        scrollToCard(historical)
        scrollToCard(traditional)
        compose.onAllNodesWithTag("novena_card_$modern").assertCountEquals(0)

        pickOrigin("all")
        waitForCounter("$total")
    }

    @Test fun originFilterCombinesWithSearchAndWithTheOtherFilters() {
        openNovenas()
        compose.onNodeWithTag("prayers_search_toggle", useUnmergedTree = true).performClick()
        compose.onNodeWithTag("novena_search_input", useUnmergedTree = true).performTextInput("Cristo Rey")
        scrollToCard(modern)

        // Same search, another origin: the modern novena is excluded and nothing else matches.
        pickOrigin("pastoral_review")
        compose.waitUntil(20_000) { compose.onAllNodesWithTag("novena_empty").fetchSemanticsNodes().isNotEmpty() }
        compose.onAllNodesWithTag("novena_card_$modern").assertCountEquals(0)

        // Back to the matching origin: the search result returns.
        pickOrigin("modern")
        compose.waitUntil(20_000) { compose.onAllNodesWithTag("novena_card_$modern").fetchSemanticsNodes().isNotEmpty() }
        badge(modern).assertTextEquals("Moderna")

        // «Ver todas» in the empty state clears the origin too.
        pickOrigin("traditional")
        compose.waitUntil(20_000) { compose.onAllNodesWithTag("novena_empty").fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("novena_clear_filters", useUnmergedTree = true).performClick()
        compose.waitUntil(20_000) { compose.onAllNodesWithText("Origen: Todas").fetchSemanticsNodes().isNotEmpty() }
        assertTrue(compose.onAllNodesWithTag("novena_search_input", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty())
    }

    @Test fun originFilterCombinesWithTheWhoFilter() {
        openNovenas()
        compose.onNodeWithTag("novena_index").performScrollToNode(hasTestTag("novena_filter_chips"))
        compose.onNodeWithTag("novena_kind_picker", useUnmergedTree = true).performClick()
        compose.onNodeWithTag("novena_filter_popular").performClick()
        compose.waitForIdle()
        scrollToCard(historical)               // popular and historical
        pickOrigin("modern")
        // Popular novenas are historical/traditional here: none is modern, so the list is empty.
        compose.waitUntil(20_000) { compose.onAllNodesWithTag("novena_card_$historical").fetchSemanticsNodes().isEmpty() }
        pickOrigin("traditional")
        compose.waitUntil(20_000) { compose.onAllNodesWithTag("novena_card_$historical").fetchSemanticsNodes().isNotEmpty() }
    }
}
