package com.example

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.components.GuiCustomizerModal
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class VoiceSettingsUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private fun show(design: String) {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val prefs = UserPreferences(AppLanguage.ES, design, "LITURGICAL_DYNAMIC", "LIGHT", "IDENTITY_DEFAULT", 1f, 1f, "bundled", false, true)
        compose.setContent {
            SanctaFidesTheme(designLanguage = design, darkModeSetting = "LIGHT", fontScale = 1f, reduceMotion = true) {
                GuiCustomizerModal(UserPreferencesStore(context), prefs, AppLanguage.ES) {}
            }
        }
    }

    private fun check() {
        compose.onNodeWithText("Voz de lectura").performScrollTo().assertIsDisplayed()
        listOf("voice_leader_picker", "voice_response_picker", "voice_leader_preview",
            "voice_response_preview", "voice_get_more").forEach {
            compose.onNodeWithTag(it).performScrollTo().assertIsDisplayed()
        }
        compose.onNodeWithTag("voice_leader_picker").assertIsDisplayed()
        compose.onNodeWithText("Automática (recomendada)", substring = true, useUnmergedTree = true)
    }

    @Test fun lumenShowsVoiceSection() { show("LUMEN"); check() }
    @Test fun sanctuaryShowsVoiceSection() { show("SANCTUARY"); check() }
}
