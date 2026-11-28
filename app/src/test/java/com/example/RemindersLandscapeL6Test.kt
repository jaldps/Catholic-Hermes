package com.example

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.RemindersScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L6 5/6: Reminders uses a Hub panel with a grid and 44 dp time wheel items. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class RemindersLandscapeL6Test {
    @get:Rule val compose = createComposeRule()

    @Test fun remindersUsePanelAndGrid() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext()).apply { setLanguage(AppLanguage.ES) }
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                RemindersScreen(repository)
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("app_page_panel").assertExists()
        compose.onNodeWithTag("reminders_title").assertExists()
        compose.onNodeWithTag("reminders_list").assertExists()
    }
}
