package com.example

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.ReadingPlansScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L5 6/7: Reading plans list and detail side by side in landscape. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class PlansLandscapeL5Test {
    @get:Rule val compose = createComposeRule()

    private fun setContent() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext())
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                ReadingPlansScreen(
                    language = AppLanguage.ES,
                    progress = emptyMap(),
                    todayEpochDay = 0L,
                    bibleLabel = { it },
                    onStart = {},
                    onToggleDone = { _, _, _ -> },
                    onRebase = { _, _ -> },
                    onRestart = {},
                    onOpenBibleReading = {},
                    onOpenCatechism = {},
                    repository = repository
                )
            }
        }
        compose.waitUntil(timeoutMillis = 15_000) {
            compose.onAllNodesWithTag("plans_screen").fetchSemanticsNodes().isNotEmpty()
        }
    }

    @Test fun plansUseTwoPanes() {
        setContent()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("two_pane_detail").assertIsDisplayed()
    }

    @Test fun selectingAPlanKeepsTheDetailBesideTheList() {
        setContent()
        compose.onAllNodesWithTag("plan_card_bible_year")[0].performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("two_pane").assertIsDisplayed()
        compose.onNodeWithTag("plan_start").assertIsDisplayed()
    }
}
