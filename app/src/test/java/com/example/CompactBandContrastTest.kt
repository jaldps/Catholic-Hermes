package com.example

import android.graphics.BitmapFactory
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.ui.design.AppHeroHeader
import com.example.ui.theme.*
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** Contrast against actual painted pixels, with the glyphs removed from the comparison image. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class CompactBandContrastTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    @Test fun titleAndCaptionAreReadableOverTheFiveRootIllustrations() {
        var visible by mutableStateOf(true)
        var asset by mutableStateOf("heroes/lumen/home.jpg")
        var primary = Color.Unspecified; var secondary = Color.Unspecified
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", reduceMotion = true) {
            primary = LocalAppDesign.current.palette.textPrimary; secondary = LocalAppDesign.current.palette.textSecondary
            CompositionLocalProvider(LocalInspectionMode provides true) {
                Box(Modifier.fillMaxSize().background(LocalAppDesign.current.palette.canvasTop)) {
                    AppHeroHeader(asset, if (visible) "Oraciones" else "", eyebrow = if (visible) "Ora sin cesar" else null, titleTag = "contrast_title")
                }
            }
        } }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 411) }; compose.waitForIdle()
        val results = mutableListOf<String>()
        for (name in listOf("home", "prayers_lily", "bible", "habits", "explore")) {
            compose.runOnIdle { asset = "heroes/lumen/$name.jpg"; visible = true }; compose.waitForIdle(); Thread.sleep(250); compose.waitForIdle()
            val painted = File("build/screenshots/header-contrast/${identity.id}_${dark}_${name}_text.png")
            landscapeCapture(compose, compose.activity, 891, 411, painted)
            val title = compose.onNodeWithTag("contrast_title").fetchSemanticsNode().boundsInRoot
            val caption = compose.onNodeWithText("Ora sin cesar", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
            compose.runOnIdle { visible = false }; compose.waitForIdle()
            val bare = File("build/screenshots/header-contrast/${identity.id}_${dark}_${name}_background.png")
            landscapeCapture(compose, compose.activity, 891, 411, bare)
            val bitmap = BitmapFactory.decodeFile(bare.path)
            val glyphs = BitmapFactory.decodeFile(painted.path)
            fun minimum(ink: Color, bounds: androidx.compose.ui.geometry.Rect, localizedPlate: Boolean = false): Float {
                var ratio = Float.MAX_VALUE
                var samples = 0
                val inkArgb = ink.toArgb()
                for (y in bounds.top.toInt().coerceAtLeast(0) until bounds.bottom.toInt().coerceAtMost(bitmap.height))
                    for (x in bounds.left.toInt().coerceAtLeast(0) until bounds.right.toInt().coerceAtMost(bitmap.width)) {
                        // Fully covered glyph pixels avoid treating empty line-box corners as text.
                        val paintedPixel = glyphs.getPixel(x,y)
                        val distance = kotlin.math.abs(android.graphics.Color.red(paintedPixel) - android.graphics.Color.red(inkArgb)) +
                            kotlin.math.abs(android.graphics.Color.green(paintedPixel) - android.graphics.Color.green(inkArgb)) +
                            kotlin.math.abs(android.graphics.Color.blue(paintedPixel) - android.graphics.Color.blue(inkArgb))
                        if (distance > 6) continue
                        samples++
                        val background = if (localizedPlate) {
                            val above = glyphs.getPixel(x, bounds.top.toInt() - 1)
                            val below = glyphs.getPixel(x, bounds.bottom.toInt() + 1)
                            org.junit.Assert.assertEquals("The caption has an opaque local plate, not a full-band wash", above, below)
                            above
                        } else bitmap.getPixel(x,y)
                        val a = ink.luminance(); val b = Color(background).luminance()
                        ratio = minOf(ratio, (maxOf(a,b) + .05f) / (minOf(a,b) + .05f))
                    }
                assertTrue("The contrast mask must contain real glyph cores", samples > 5)
                return ratio
            }
            val titleRatio = minimum(primary, title); val captionRatio = minimum(secondary, caption, localizedPlate = true)
            results += "$name\ttitle=$titleRatio\tcaption=$captionRatio"
            bitmap.recycle(); glyphs.recycle()
        }
        val result = File("build/audit/compact_contrast_${identity.id}_${dark}.tsv").apply { parentFile!!.mkdirs(); writeText(results.joinToString("\n")) }
        val failures = results.filter { row -> row.substringAfter("title=").substringBefore('\t').toFloat() < 3f || row.substringAfter("caption=").toFloat() < 4.5f }
        assertTrue("Rendered contrast (${result.name}):\n${failures.joinToString("\n")}", failures.isEmpty())
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-dark={1}")
        fun matrix(): List<Array<Any>> = listOf(DesignLanguage.APPLE, DesignLanguage.CLASSIC, DesignLanguage.GLASS).flatMap { id -> listOf(false,true).map { arrayOf<Any>(id,it) } }
    }
}
