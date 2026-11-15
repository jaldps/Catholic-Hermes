package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.activity.compose.setContent
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.core.app.ApplicationProvider
import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.CalendarRules
import com.example.data.models.AppLanguage
import com.example.data.news.LocalReadingsLoader
import com.example.data.news.ReadingsLoader
import com.example.data.news.VaticanGospelFetcher
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.DailyMissalView
import com.example.ui.screens.SpeechQueue
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class MassVaticanSourceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var repository: CatholicDataRepository? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        repository?.close()
    }

    private fun feed() = javaClass.classLoader!!.getResourceAsStream("vaticangospel_es.rss.xml")!!
        .bufferedReader().use { it.readText() }

    private fun launch(rules: CalendarRules, loader: ReadingsLoader) {
        val repo = CatholicDataRepository(ApplicationProvider.getApplicationContext<Application>())
            .apply { applyPersistedLanguage(AppLanguage.ES) }.also { repository = it }
        val date = CalendarDate(2026, 5, 17)
        repo.applyCalendarRules(rules)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true, LocalReadingsLoader provides loader) {
                    DailyMissalView(
                        header = {},
                        reading = repo.getMassReading(date),
                        date = date,
                        calendarMatches = repo.vaticanCalendarMatches(date, rules),
                        currentLanguage = AppLanguage.ES,
                        speech = SpeechQueue(null),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues()
                    )
                }
            }
        }
    }

    private fun await(c: () -> Boolean) = compose.waitUntil(15_000) {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        c()
    }

    private val ok: ReadingsLoader = { _, _ -> VaticanGospelFetcher().parse(feed(), "2026-10-31") }

    @Test fun mismatchingTransfersStillShowVaticanReadingsWithLocalNote() {
        launch(CalendarRules(ascensionOnSunday = true), ok)
        await { compose.onAllNodesWithTag("mass_local_note").fetchSemanticsNodes().isNotEmpty() }
        await { compose.onAllNodesWithText("Filipenses 1, 18-26", substring = true).fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithTag("mass_local_selection").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("daily_missal_screen").performScrollToNode(androidx.compose.ui.test.hasTestTag("mass_source_attribution"))
        compose.onNodeWithTag("mass_source_attribution").assertIsDisplayed()
    }

    @Test fun matchingCalendarShowsNoLocalNote() {
        launch(CalendarRules(), ok)
        await { compose.onAllNodesWithText("Filipenses 1, 18-26", substring = true).fetchSemanticsNodes().isNotEmpty() }
        assertTrue(compose.onAllNodesWithTag("mass_local_note").fetchSemanticsNodes().isEmpty())
    }
}
