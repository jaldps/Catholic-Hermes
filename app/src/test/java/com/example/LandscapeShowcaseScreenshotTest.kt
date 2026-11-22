package com.example

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.data.models.AppLanguage
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** Native Canvas renders of the four L0 examples; no production routes are changed. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class LandscapeShowcaseScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val language = AppLanguage.fromCode(System.getProperty("shots.lang", "es"))
    private val scale = System.getProperty("shots.scale")?.toFloatOrNull() ?: 1f
    private val opaque = System.getProperty("shots.opaque") == "true"
    private val output = File("build/screenshots/landscape-fixtures").also { it.mkdirs() }

    @After fun dispose() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
    }

    private fun render(identity: DesignLanguage, dark: Boolean) {
        val filter = System.getProperty("shots.identity", "all")
        val mode = System.getProperty("shots.mode")
        if (filter != "all" && filter != identity.id && !(filter == "apple" && identity == DesignLanguage.APPLE)) return
        if (mode != null && mode != if (dark) "dark" else "light") return
        val width = System.getProperty("shots.width")?.toIntOrNull()
        val height = System.getProperty("shots.height")?.toIntOrNull()
        val viewports = if (width != null && height != null) listOf(width to height) else listOf(891 to 411, 800 to 360)
        for ((w, h) in viewports) {
            RuntimeEnvironment.setQualifiers("w${w}dp-h${h}dp-${if (w > h) "land-" else ""}xhdpi")
            compose.runOnUiThread { compose.activity.onConfigurationChanged(compose.activity.resources.configuration) }
            var visible by mutableStateOf(false)
            var showcase by mutableStateOf(LandscapeShowcase.Hub)
            compose.runOnUiThread {
                compose.activity.setContent {
                    SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT",
                        fontScale = scale, reduceTransparency = opaque, reduceMotion = true) {
                        CompositionLocalProvider(LocalInspectionMode provides true) {
                            if (visible) key(showcase) { LandscapeShowcaseScreen(showcase, language) }
                        }
                    }
                }
            }
            for (kind in LandscapeShowcase.entries) {
                compose.runOnUiThread { visible = false }; compose.waitForIdle()
                compose.runOnUiThread { showcase = kind; visible = true }; compose.waitForIdle()
                compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, h) }
                compose.waitForIdle()
                Thread.sleep(1200)
                compose.runOnIdle {
                    val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView
                    view.layoutParams = view.layoutParams.apply { this.width = w * 2; this.height = h * 2 }
                    view.measure(View.MeasureSpec.makeMeasureSpec(w * 2, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(h * 2, View.MeasureSpec.EXACTLY))
                    view.layout(0, 0, w * 2, h * 2)
                }
                compose.waitForIdle()
                compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, h) }; compose.waitForIdle()
                val bitmap = compose.runOnIdle {
                    val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView
                    check(view.width == w * 2 && view.height == h * 2)
                    Bitmap.createBitmap(w * 2, h * 2, Bitmap.Config.ARGB_8888).also {
                        view.draw(Canvas(it)); androidx.compose.runtime.snapshots.Snapshot.sendApplyNotifications(); view.draw(Canvas(it))
                    }
                }
                val suffix = (if (scale == 1f) "" else "_s${(scale * 100).toInt()}") +
                    (if (language == AppLanguage.PT) "_pt" else "") + (if (opaque) "_opaque" else "")
                File(output, "l0_${kind.name.lowercase()}_${identity.id}_${if (dark) "oscuro" else "claro"}_${w}x${h}$suffix.png")
                    .outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
                bitmap.recycle()
            }
            compose.runOnUiThread { visible = false }; compose.waitForIdle()
        }
    }

    @Test fun lumenLight() = render(DesignLanguage.APPLE, false)
    @Test fun lumenDark() = render(DesignLanguage.APPLE, true)
    @Test fun classicLight() = render(DesignLanguage.CLASSIC, false)
    @Test fun classicDark() = render(DesignLanguage.CLASSIC, true)
    @Test fun glassLight() = render(DesignLanguage.GLASS, false)
    @Test fun glassDark() = render(DesignLanguage.GLASS, true)
    @Test fun sanctuaryLight() = render(DesignLanguage.SANCTUARY, false)
    @Test fun sanctuaryDark() = render(DesignLanguage.SANCTUARY, true)
}
