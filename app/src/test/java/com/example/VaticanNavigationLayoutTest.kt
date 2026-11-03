package com.example

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.FontChoice
import com.example.ui.design.AppNavItem
import com.example.ui.design.AppScaffold
import com.example.ui.theme.AppFonts
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class VaticanNavigationLayoutTest(
    private val dark: Boolean,
    private val widthDp: Int,
    private val font: FontChoice,
    private val scale: Float,
    private val language: AppLanguage
) {
    @get:Rule val compose = createComposeRule()

    @Test fun completeCaptionsFitAndAllFiveDestinationsRemainSelectable() {
        val labels = if (language == AppLanguage.ES) {
            listOf("Inicio", "Biblia", "Rosario", "Oraciones", "Más")
        } else {
            listOf("Home", "Bible", "Rosary", "Prayers", "More")
        }
        val tags = listOf("nav_home", "nav_bible", "nav_rosary", "nav_prayers", "nav_more")
        compose.setContent {
            Box(Modifier.width(widthDp.dp).fillMaxHeight().testTag("navigation_viewport")) {
                SanctaFidesTheme(
                    designLanguage = DesignLanguage.VATICAN.id,
                    darkModeSetting = if (dark) "DARK" else "LIGHT",
                    fontFamilyChoice = font.id,
                    fontScale = scale
                ) {
                    var selected by remember { mutableIntStateOf(0) }
                    AppScaffold(
                        title = "Navigation",
                        navItems = labels.mapIndexed { index, label ->
                            AppNavItem(label, Icons.Default.Home, tag = tags[index])
                        },
                        selectedNavIndex = selected,
                        onNavSelected = { selected = it }
                    ) { padding -> Text("Content", Modifier.padding(padding)) }
                }
            }
        }
        compose.waitForIdle()

        fun assertCompleteCaptions() {
            val viewport = compose.onNodeWithTag("navigation_viewport").fetchSemanticsNode().boundsInRoot
            var sharedStyle: androidx.compose.ui.text.TextStyle? = null
            labels.forEachIndexed { index, label ->
                val item = compose.onNodeWithTag(tags[index]).assertIsDisplayed().assertHasClickAction()
                    .assertWidthIsAtLeast(48.dp).assertHeightIsAtLeast(48.dp)
                val itemBounds = item.fetchSemanticsNode().boundsInRoot
                assertTrue("${tags[index]} left outside viewport", itemBounds.left >= viewport.left - 1f)
                assertTrue("${tags[index]} right outside viewport", itemBounds.right <= viewport.right + 1f)
                val caption = label.uppercase()
                val results = mutableListOf<TextLayoutResult>()
                compose.onNodeWithText(caption, useUnmergedTree = true).assertIsDisplayed()
                    .performSemanticsAction(SemanticsActions.GetTextLayoutResult) { getResults ->
                        getResults(results)
                    }
                val result = results.single()
                assertEquals(caption, result.layoutInput.text.text)
                assertEquals(if (font == FontChoice.ROBOTO) AppFonts.Roboto else AppFonts.Cinzel,
                    result.layoutInput.style.fontFamily)
                sharedStyle?.let {
                    assertEquals("Shared caption size", it.fontSize, result.layoutInput.style.fontSize)
                    assertEquals("Shared caption tracking", it.letterSpacing, result.layoutInput.style.letterSpacing)
                    assertEquals("Shared caption family", it.fontFamily, result.layoutInput.style.fontFamily)
                    assertEquals("Shared caption weight", it.fontWeight, result.layoutInput.style.fontWeight)
                    assertEquals("Shared caption leading", it.lineHeight, result.layoutInput.style.lineHeight)
                }
                sharedStyle = result.layoutInput.style
                assertFalse("$caption overflows vertically", result.didOverflowHeight)
                for (line in 0 until result.lineCount) {
                    assertFalse("$caption is ellipsized", result.isLineEllipsized(line))
                    assertTrue("$caption line starts outside layout", result.getLineLeft(line) >= -1f)
                    assertTrue("$caption line ends outside layout: right=${result.getLineRight(line)}, width=${result.size.width}, constraints=${result.layoutInput.constraints}", result.getLineRight(line) <= result.size.width + 1f)
                }
                assertEquals("$caption is incomplete", caption.length,
                    result.getLineEnd(result.lineCount - 1, visibleEnd = true))
                for (offset in caption.indices) {
                    val bounds = result.getBoundingBox(offset)
                    assertTrue("$caption glyph $offset left clipped", bounds.left >= -1f)
                    assertTrue("$caption glyph $offset right clipped", bounds.right <= result.size.width + 1f)
                    assertTrue("$caption glyph $offset top clipped", bounds.top >= -1f)
                    assertTrue("$caption glyph $offset bottom clipped", bounds.bottom <= result.size.height + 1f)
                }
            }
        }

        assertCompleteCaptions()
        compose.onNodeWithTag(tags.first()).assertIsSelected()
        tags.forEach { tag -> compose.onNodeWithTag(tag).performClick().assertIsSelected() }
        compose.waitForIdle()
        assertCompleteCaptions()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "dark={0}, width={1}, font={2}, scale={3}, language={4}")
        fun cases(): List<Array<Any>> = listOf(false, true).flatMap { dark ->
            listOf(
                arrayOf<Any>(dark, 320, FontChoice.ROBOTO, 1.3f, AppLanguage.EN),
                arrayOf<Any>(dark, 411, FontChoice.IDENTITY_DEFAULT, 1f, AppLanguage.ES)
            )
        }
    }
}
