package com.example.screenshots

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.readyNarrator
import com.example.ui.screens.SaintsScreen
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * A saint's page with its «Escuchar» button, idle and while narrating, per
 * identity; PNGs go to docs/tasks/T-042-shots.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class NarrationScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var narrator: LiturgicalTtsManager? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        narrator?.release()
    }

    private fun render(scale: Float, language: AppLanguage = AppLanguage.ES) {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val tts = readyNarrator(app, language).also { narrator = it }
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    Surface { SaintsScreen(language, Modifier.fillMaxSize(), ttsManager = tts, initialKey = "francis_de_sales_bishop") }
                }
            }
        }
        compose.waitUntil(20_000) { compose.onAllNodesWithTag("saint_listen_button").fetchSemanticsNodes().isNotEmpty() }
    }

    private fun capture(name: String) {
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            check(view.width > 0 && view.height > 0)
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(full))
            val small = Bitmap.createScaledBitmap(full, full.width * 6 / 10, full.height * 6 / 10, true)
            val file = File("../docs/tasks/T-042-shots/${identity.id.lowercase()}_${if (dark) "dark" else "light"}_$name.png")
            file.parentFile!!.mkdirs(); file.outputStream().use { small.compress(Bitmap.CompressFormat.PNG, 100, it) }
            full.recycle(); small.recycle()
        }
    }

    private fun idleAndPlaying(prefix: String) {
        capture("${prefix}idle")
        compose.onNodeWithTag("saint_listen_button").performClick()
        compose.waitForIdle()
        capture("${prefix}playing")
    }

    @Test fun saintListenIdleAndPlaying() {
        Assume.assumeTrue(identity == DesignLanguage.APPLE || identity == DesignLanguage.SANCTUARY)
        render(1f)
        idleAndPlaying("saint_")
    }

    @Test @Config(qualifiers = "w320dp-h891dp-xhdpi") fun narrowLargeText() {
        Assume.assumeTrue(identity == DesignLanguage.GLASS || identity == DesignLanguage.CLASSIC)
        render(1.3f, AppLanguage.PT)
        idleAndPlaying("saint_320_130_")
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes() = listOf(
            arrayOf<Any>(DesignLanguage.APPLE, false), arrayOf<Any>(DesignLanguage.SANCTUARY, true),
            arrayOf<Any>(DesignLanguage.GLASS, false), arrayOf<Any>(DesignLanguage.CLASSIC, true)
        )
    }
}
