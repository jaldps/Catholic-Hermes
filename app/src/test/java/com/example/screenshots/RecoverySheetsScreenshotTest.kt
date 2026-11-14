package com.example.screenshots

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.FakeCalendarProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.audio.VoiceAvailability
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.components.CalendarPickerSheet
import com.example.ui.components.NarrationRecoverySheet
import com.example.ui.components.PreferencesRecoverySheet
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Before
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowDialog
import java.io.File

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class RecoverySheetsScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val width = System.getProperty("shots.width")?.toIntOrNull() ?: 411
    private val height = System.getProperty("shots.height")?.toIntOrNull() ?: 891
    private val scale = System.getProperty("shots.scale")?.toFloatOrNull() ?: 1f
    private val suffix get() = if (width == 411 && height == 891 && scale == 1f) "" else "_w" + width + "_scale" + (scale * 100).toInt()
    @Before fun viewport() { org.robolectric.RuntimeEnvironment.setQualifiers("w" + width + "dp-h" + height + "dp-xhdpi") }
    private var provider: FakeCalendarProvider? = null
    private var narration: LiturgicalTtsManager? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        narration?.release()
        provider?.database?.close()
    }
    private fun capture(kind: String) {
        compose.waitForIdle()
        compose.runOnIdle {
            val view = requireNotNull(requireNotNull(ShadowDialog.getLatestDialog()).window).decorView
            check(view.width > 0 && view.height > 0)
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bitmap))
            val file = File("build/screenshots/recovery/${kind}_${identity.id.lowercase()}_${if (dark) "dark" else "light"}${suffix}.png")
            file.parentFile!!.mkdirs()
            file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        }
    }
    @Test fun captureCurrentRecoverySheetsInSpanish() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        provider = FakeCalendarProvider.install(app)
        val tts = LiturgicalTtsManager(app).also { narration = it }
        val store = UserPreferencesStore(app)
        var sheet by mutableIntStateOf(0)
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", reduceMotion = true, fontScale = scale) {
                when (sheet) {
                    0 -> CalendarPickerSheet(AppLanguage.ES, null, {}, {}, "Coronilla de la Divina Misericordia · 15:00")
                    1 -> NarrationRecoverySheet(tts, VoiceAvailability.MISSING_DATA, AppLanguage.ES)
                    else -> PreferencesRecoverySheet(store, false, listOf("preferences:fixture"), AppLanguage.ES, {})
                }
            }
        }
        compose.waitUntil(15_000) {
            compose.runOnUiThread { Snapshot.sendApplyNotifications() }
            compose.onAllNodesWithTag("calendar_option_2").fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNodeWithTag("calendar_option_2").performScrollTo().performClick()
        compose.onNodeWithTag("confirm_calendar_choice").assertIsEnabled()
        capture("calendar")
        compose.runOnUiThread { sheet = 1 }
        compose.onNodeWithTag("narration_recovery_sheet").assertIsDisplayed()
        capture("voice")
        compose.runOnUiThread { sheet = 2 }
        compose.onNodeWithTag("preferences_recovery_sheet").assertIsDisplayed()
        capture("preferences")
    }
    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes(): List<Array<Any>> = DesignLanguage.selectable.flatMap { listOf(arrayOf<Any>(it, false), arrayOf<Any>(it, true)) }
    }
}
