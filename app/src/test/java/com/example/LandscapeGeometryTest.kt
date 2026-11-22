package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.TextLayoutResult
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** Measures both type systems and both window sizes, including the paper frame's real scroll budget. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class LandscapeGeometryTest(private val identity: DesignLanguage, private val width: Int, private val height: Int) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private fun measure(kind: LandscapeShowcase) {
        landscapeViewport(compose, compose.activity, width, height)
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT", reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { LandscapeShowcaseScreen(kind) }
        } }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, height) }; compose.waitForIdle()
        val panel = compose.onNodeWithTag("app_page_panel").fetchSemanticsNode()
        val header = compose.onNodeWithTag("side_panel_header").fetchSemanticsNode()
        val title = compose.onNodeWithTag("showcase_title").fetchSemanticsNode()
        val layouts = mutableListOf<TextLayoutResult>()
        compose.runOnIdle { title.config[SemanticsActions.GetTextLayoutResult].action!!.invoke(layouts) }
        val baseline = (title.positionInRoot.y + layouts.single().lastBaseline) / 2f
        val scroll = panel.config.getOrNull(SemanticsProperties.VerticalScrollAxisRange)?.maxValue()?.div(2f) ?: 0f
        assertEquals(height * .46f - 14f, baseline, 1f)
        assertTrue(panel.size.width / 2f <= width * .4f)
        if (kind == LandscapeShowcase.Reader) assertEquals("The decorative frame must not be cut by its fixed footer at 100%", 0f, scroll, 1f)
        File("build/audit/geometry_${kind.name}_${identity.id}_${width}.json").apply {
            parentFile!!.mkdirs()
            writeText("""{"kind":"${kind.name}","identity":"${identity.id}","width":$width,"height":$height,"panel_width":${panel.size.width / 2f},"header_height":${header.size.height / 2f},"title_baseline":$baseline,"panel_scroll_max":$scroll}""")
        }
        if (kind == LandscapeShowcase.Hub) {
            compose.onNode(hasSetTextAction()).performScrollTo()
            assertTrue(compose.onNode(hasSetTextAction()).getUnclippedBoundsInRoot().bottom.value <= height - 16f + 1f)
        }
    }
    @Test fun hubKeepsTheSharedBaselineAndEverySupportingControlReachable() = measure(LandscapeShowcase.Hub)
    @Test fun readerKeepsItsArtworkAndFooterWithinTheApprovedPanel() = measure(LandscapeShowcase.Reader)
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-{1}x{2}")
        fun matrix(): List<Array<Any>> = listOf(DesignLanguage.APPLE, DesignLanguage.CLASSIC, DesignLanguage.GLASS, DesignLanguage.SANCTUARY).flatMap { id ->
            listOf(891 to 411, 800 to 360).map { (w,h) -> arrayOf<Any>(id,w,h) }
        }
    }
}
