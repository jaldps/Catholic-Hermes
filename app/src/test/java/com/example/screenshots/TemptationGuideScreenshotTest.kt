package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performScrollToNode
import com.example.data.models.AppLanguage
import com.example.data.repository.TemptationGuidesData
import com.example.ui.screens.TemptationGuidePage
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** Renders the long-form temptation guide in each identity for visual review. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class TemptationGuideScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val lang = when (System.getProperty("shots.lang")) { "en" -> AppLanguage.EN; "pt" -> AppLanguage.PT; else -> AppLanguage.ES }
    private val scale = System.getProperty("shots.scale")?.toFloatOrNull() ?: 1f

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
    }

    private fun capture(name: String) {
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bitmap))
            val mode = if (dark) "dark" else "light"
            val file = File("build/screenshots/guide/${name}_${lang.name.lowercase()}_${identity.id.lowercase()}_${mode}_s${(scale * 100).toInt()}.png")
            file.parentFile!!.mkdirs()
            file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        }
    }

    @Test fun captureGuide() {
        val guide = requireNotNull(TemptationGuidesData.forKey("PRIDE"))
        compose.activity.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", reduceMotion = true, fontScale = scale) {
                Box(Modifier.fillMaxSize().background(LocalAppDesign.current.palette.canvas)) {
                    TemptationGuidePage(lang = lang, guide = guide, sinTitle = "Soberbia", virtue = "Humildad")
                }
            }
        }
        capture("pride_top")
        val list = hasScrollToNodeAction() and hasTestTag("temptation_guide_page")
        compose.onNode(list).performScrollToNode(hasTestTag("guide_section_plan"))
        capture("pride_plan")
        compose.onNode(list).performScrollToNode(hasTestTag("guide_section_saints"))
        capture("pride_saints")
        compose.onNode(list).performScrollToNode(hasTestTag("guide_section_sources"))
        capture("pride_sources")
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun modes(): List<Array<Any>> = DesignLanguage.selectable.flatMap { listOf(arrayOf<Any>(it, false), arrayOf<Any>(it, true)) }
    }
}
