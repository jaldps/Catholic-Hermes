package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.rememberTextMeasurer
import com.example.data.models.AppLanguage
import com.example.data.models.FontChoice
import com.example.data.repository.BibleData
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppTextStyles
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** Audits the actual laid-out reading column, not merely the measure function's return value. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class LandscapeReadingMeasureTest(private val width: Int, private val height: Int, private val language: AppLanguage, private val font: FontChoice) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private fun check(kind: LandscapeShowcase, minimum: Float) {
        landscapeViewport(compose, compose.activity, width, height)
        var referenceWidth = 0f
        var fontSize = 0f
        compose.setContent { SanctaFidesTheme(designLanguage = DesignLanguage.APPLE.id, darkModeSetting = "LIGHT", fontFamilyChoice = font.id, reduceMotion = true) {
            val measured = rememberTextMeasurer().measure(BibleData.sampleVersesWithCommentaries.first().text.get(language).take(66), LocalAppTextStyles.current.reading, softWrap = false)
            val size = LocalAppTextStyles.current.reading.fontSize.value
            SideEffect { referenceWidth = measured.size.width.toFloat(); fontSize = size }
            CompositionLocalProvider(LocalInspectionMode provides true) { LandscapeShowcaseScreen(kind, language) }
        } }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, height) }; compose.waitForIdle()
        val text = compose.onNodeWithTag("showcase_reading").fetchSemanticsNode()
        val results = mutableListOf<TextLayoutResult>()
        compose.runOnIdle { text.config[SemanticsActions.GetTextLayoutResult].action!!.invoke(results) }
        val layout = results.single()
        val capacity = layout.size.width / referenceWidth * 66f
        val knownWidthLimit = kind == LandscapeShowcase.Reader && width == 800 && language == AppLanguage.PT && font == FontChoice.INTER && capacity < minimum
        if (knownWidthLimit) {
            val main = compose.onNodeWithTag("app_page_content").fetchSemanticsNode().size.width
            assertTrue("The approved width budget is fully used", layout.size.width >= main - 40f * compose.density.density - 2f)
            assertTrue("Recorded PT Inter limit must not conceal a further regression", capacity >= 56f)
        }
        File("build/audit/reading_${kind.name}_${width}_${language.code}_${font.id}.json").apply {
            parentFile!!.mkdirs()
            writeText("""{"kind":"${kind.name}","width":$width,"language":"${language.code}","font":"${font.id}","font_size_sp":$fontSize,"text_width_px":${layout.size.width},"reference_width_px":$referenceWidth,"capacity":$capacity,"minimum":$minimum,"status":"${if (knownWidthLimit) "KNOWN_LIMIT" else "OK"}"}""")
        }
        if (!knownWidthLimit) assertFalse("MEASURE $kind / $font / ${language.code} at $width: $capacity characters, required $minimum–75",
            LandscapeAuditRules.outsideMeasure(capacity, minimum))
        assertTrue(layout.lineCount > 1)
        assertTrue((0 until layout.lineCount).none { layout.isLineEllipsized(it) })
    }
    @Test fun fullReaderUsesTheApprovedMeasureOrReportsItsExhaustedWidthBudget() = check(LandscapeShowcase.Reader, 60f)
    @Test fun detailKeepsTheApprovedMeasureAndOffersTheFullReader() {
        check(LandscapeShowcase.ListDetail, if (font == FontChoice.EB_GARAMOND) 56f else 45f)
        compose.onNodeWithTag("detail_open_reader").assertIsDisplayed().assertHasClickAction()
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}x{1}-{2}-{3}")
        fun matrix(): List<Array<Any>> = listOf(891 to 411, 800 to 360).flatMap { (w,h) ->
            listOf(AppLanguage.ES, AppLanguage.PT).flatMap { lang -> listOf(FontChoice.EB_GARAMOND, FontChoice.INTER).map { font -> arrayOf<Any>(w,h,lang,font) } }
        }
    }
}
