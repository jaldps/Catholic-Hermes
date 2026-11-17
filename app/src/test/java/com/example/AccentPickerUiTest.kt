package com.example

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.click
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.onNodeWithText
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.components.GuiCustomizerModal
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** The appearance sheet: grouped accent colours, the colour wheel and the Tradicional identity. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w320dp-h891dp-xhdpi")
class AccentPickerUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    @get:Rule val folder = TemporaryFolder()

    private fun run(block: (UserPreferencesStore) -> Unit) {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val job = SupervisorJob()
        val data = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + job)) {
            folder.newFolder().resolve("accent-ui.preferences_pb")
        }
        val store = UserPreferencesStore(context, data)
        val defaults = runBlocking { store.preferences.first() }
        compose.setContent {
            val prefs by store.preferences.collectAsState(initial = defaults)
            SanctaFidesTheme(designLanguage = prefs.designLanguage, themePreset = prefs.themePreset,
                darkModeSetting = "LIGHT", reduceMotion = true) {
                GuiCustomizerModal(store, prefs, AppLanguage.ES) {}
            }
        }
        try { block(store) } finally { runBlocking { job.cancelAndJoin() } }
    }

    private fun await(store: UserPreferencesStore, what: String, test: (com.example.data.prefs.UserPreferences) -> Boolean) {
        runBlocking { withTimeout(10000) { assertTrue(what, test(store.preferences.first { test(it) })) } }
    }

    @Test fun accentColoursAreInTwoGroupsAndPersist() = run { store ->
        compose.onNodeWithTag("accent_group_ecclesial").performScrollTo().assertIsDisplayed()
        compose.onNodeWithTag("accent_group_other").performScrollTo().assertIsDisplayed()
        compose.onNodeWithTag("theme_preset_SACRED_BURGUNDY").performScrollTo().assertIsDisplayed()
        compose.onNodeWithTag("theme_preset_CUSTOM").performScrollTo().assertIsDisplayed()
        compose.onNodeWithTag("theme_preset_NE_TEAL").performScrollTo().performClick()
        await(store, "NE_TEAL stored") { it.themePreset == "NE_TEAL" }
        compose.waitForIdle()
        compose.onNodeWithTag("theme_preset_NE_TEAL").assertIsSelected()
    }

    @Test fun colourWheelSavesACustomColour() = run { store ->
        compose.onNodeWithTag("theme_preset_CUSTOM").performScrollTo().performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("accent_custom_wheel").assertIsDisplayed()
        compose.onNodeWithTag("accent_wheel_canvas").performTouchInput { click(Offset(width * 0.75f, height * 0.5f)) }
        compose.onNodeWithTag("accent_wheel_save").performClick()
        await(store, "CUSTOM_ stored") { Regex("CUSTOM_[0-9A-F]{6}").matches(it.themePreset) }
        compose.waitForIdle()
        compose.onNodeWithTag("theme_preset_CUSTOM").performScrollTo().assertIsSelected()
    }

    @Test fun cancellingTheWheelStoresNothing() = run { store ->
        compose.onNodeWithTag("theme_preset_CUSTOM").performScrollTo().performClick()
        compose.waitForIdle()
        compose.onNodeWithTag("accent_wheel_cancel").performClick()
        compose.waitForIdle()
        assertTrue(runBlocking { store.preferences.first().themePreset } == "LITURGICAL_DYNAMIC")
    }

    @Test fun traditionalIdentityCanBeSelectedAndPersists() = run { store ->
        compose.onNodeWithText("Tradicional").performScrollTo().performClick()
        await(store, "CLASSIC stored") { it.designLanguage == "CLASSIC" }
        compose.waitForIdle()
        compose.onNodeWithText("Tradicional").assertIsSelected()
    }
}
