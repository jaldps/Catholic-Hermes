package com.example

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
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
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Drives the actual sheet, then closes the DataStore and reopens its file. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class AppearanceInteractionTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    @get:Rule val folder = TemporaryFolder()

    @Test fun smallestTextAndSlowestVoiceRemainSelectedAfterReopeningAppearance() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val file = folder.newFolder().resolve("appearance.preferences_pb")
        val firstJob = SupervisorJob()
        val firstData = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + firstJob)) { file }
        val firstStore = UserPreferencesStore(context, firstData)
        val defaults = runBlocking { firstStore.preferences.first() }
        var activeStore by mutableStateOf(firstStore)
        var visible by mutableStateOf(true)
        compose.setContent {
            val prefs by activeStore.preferences.collectAsState(initial = defaults)
            SanctaFidesTheme(designLanguage = prefs.designLanguage,
                darkModeSetting = prefs.darkModeSetting, fontScale = prefs.fontSizeScale,
                reduceMotion = true) {
                if (visible) GuiCustomizerModal(activeStore, prefs, AppLanguage.EN) { visible = false }
            }
        }
        fun selectAndAwait(label: String) {
            compose.onNodeWithText(label).performScrollTo().performClick()
            compose.waitUntil(timeoutMillis = 10000) {
                runCatching { compose.onNodeWithText(label).assertIsSelected(); true }.getOrDefault(false)
            }
        }
        selectAndAwait("Sanctuary")
        selectAndAwait("A−")
        selectAndAwait("0.75×")
        runBlocking {
            withTimeout(10000) {
                val saved = firstStore.preferences.first {
                    it.fontSizeScale == 0.9f && it.audioNarrationSpeed == 0.75f && it.designLanguage == "SANCTUARY"
                }
                assertEquals(0.9f, saved.fontSizeScale, 0f)
            }
        }
        compose.runOnIdle { visible = false }
        compose.waitForIdle()
        runBlocking { firstJob.cancelAndJoin() }

        val secondJob = SupervisorJob()
        val secondData = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + secondJob)) { file }
        try {
            val reopened = UserPreferencesStore(context, secondData)
            val saved = runBlocking { reopened.preferences.first() }
            assertEquals("SANCTUARY", saved.designLanguage)
            assertEquals(0.75f, saved.audioNarrationSpeed, 0f)
            compose.runOnIdle { activeStore = reopened; visible = true }
            compose.waitForIdle()
            compose.onNodeWithText("Sanctuary").performScrollTo().assertIsSelected()
            compose.onNodeWithText("A−").performScrollTo().assertIsSelected()
            compose.onNodeWithText("0.75×").performScrollTo().assertIsSelected()
            compose.runOnIdle { visible = false }
        } finally { runBlocking { secondJob.cancelAndJoin() } }
    }
}
