package com.example

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.ui.confession.ConfessionScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L6 4/6: Confession uses an immersive panel; the draft stays in memory only. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class ConfessionLandscapeL6Test {
    @get:Rule val compose = createComposeRule()

    @Test fun immersivePanelShowsBothLanesAndPrivacyNotice() {
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                ConfessionScreen(AppLanguage.ES)
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("app_page_panel").assertExists()
        compose.onNodeWithTag("confession_tabs").assertExists()
        compose.onNodeWithTag("confession_examen").assertExists()
        compose.onNodeWithTag("confession_private_list").assertExists()
        compose.onNodeWithTag("confession_privacy_notice").assertExists()
        compose.onNodeWithTag("confession_notes").assertExists()
    }
}
