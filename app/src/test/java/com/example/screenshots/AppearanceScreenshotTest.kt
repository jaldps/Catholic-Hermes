package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.components.GuiCustomizerModal
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowDialog
import java.io.File

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class AppearanceScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private fun capture(identity: String, dark: Boolean) {
        val context = ApplicationProvider.getApplicationContext<android.app.Application>()
        val store = UserPreferencesStore(context)
        val preferences = runBlocking { store.preferences.first() }.copy(
            designLanguage = identity, darkModeSetting = if (dark) "DARK" else "LIGHT",
            fontSizeScale = 1f, language = AppLanguage.EN
        )
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity, darkModeSetting = preferences.darkModeSetting, reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    GuiCustomizerModal(store, preferences, AppLanguage.EN) {}
                }
            }
        }
        compose.waitForIdle()
        compose.runOnIdle {
            val view = requireNotNull(ShadowDialog.getLatestDialog()?.window).decorView
            check(view.width > 0 && view.height > 0)
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bitmap))
            val file = File("build/screenshots/appearance/${identity.lowercase()}_${if (dark) "dark" else "light"}.png")
            file.parentFile?.mkdirs()
            file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG,100,it) }
        }
    }
    @Test fun lumenLight() = capture("LUMEN", false)
    @Test fun lumenDark() = capture("LUMEN", true)
    @Test fun sanctuaryLight() = capture("SANCTUARY", false)
    @Test fun sanctuaryDark() = capture("SANCTUARY", true)
}
