package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.data.models.AppLanguage
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** The paper list header must not be painted underneath the scaffold's Back disc. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class LandscapeFolioListHeaderTest(
    private val width: Int, private val height: Int, private val scale: Float, private val language: AppLanguage
) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun completeTitleClearsBackAndRemainsInsideTheScrollingHeader() {
        landscapeViewport(compose, compose.activity, width, height)
        compose.setContent {
            SanctaFidesTheme(designLanguage = DesignLanguage.SANCTUARY.id, darkModeSetting = "LIGHT", fontScale = scale, reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    LandscapeShowcaseScreen(LandscapeShowcase.ListDetail, language)
                }
            }
        }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, height) }
        compose.waitForIdle()
        val back = compose.onNodeWithTag("nav_back").getUnclippedBoundsInRoot()
        val title = compose.onNodeWithTag("showcase_title").getUnclippedBoundsInRoot()
        val strip = compose.onNodeWithTag("list_art_strip").getUnclippedBoundsInRoot()
        val sticky = compose.onNodeWithTag("list_sticky_bar").getUnclippedBoundsInRoot()
        assertTrue("Back must not erase any title glyph: back=$back title=$title", title.top.value >= back.bottom.value + 4f - .5f)
        assertTrue("The scrolling strip must contain the complete title at the chosen size", title.bottom.value <= strip.bottom.value + .5f)
        assertTrue("No empty wrapper gap before filters", sticky.top.value - strip.bottom.value <= 1f)
        Thread.sleep(2500) // Bundled detail art decodes on Dispatchers.IO; keep the visual proof complete.
        compose.waitForIdle()
        val png = File("build/screenshots/folio-list/r2_folio_list_${width}_${language.code}_${(scale * 100).toInt()}.png")
        landscapeCapture(compose, compose.activity, width, height, png)
        File("build/audit/folio_list_${width}_${language.code}_${(scale * 100).toInt()}.json").apply {
            parentFile!!.mkdirs()
            writeText("""{"width":$width,"height":$height,"language":"${language.code}","scale":$scale,"back_bottom":${back.bottom.value},"title_top":${title.top.value},"title_bottom":${title.bottom.value},"strip_bottom":${strip.bottom.value}}""")
        }
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}x{1}-{2}-{3}")
        fun matrix(): List<Array<Any>> = listOf(891 to 411, 800 to 360).flatMap { (w,h) ->
            listOf(1f, 1.3f).flatMap { scale -> listOf(AppLanguage.ES, AppLanguage.PT).map { language -> arrayOf<Any>(w,h,scale,language) } }
        }
    }
}
