package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.test.assertIsSelected
import org.robolectric.annotation.GraphicsMode
import java.io.File
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import com.example.data.models.AppLanguage
import com.example.data.models.FontChoice
import com.example.ui.components.FontPicker
import com.example.ui.theme.AppFonts
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config

@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class FontPickerInteractionTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun namedChoicesAreSelectableAndPreviewUsesActualBundledFamily() {
        var choice by mutableStateOf(FontChoice.IDENTITY_DEFAULT)
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, fontFamilyChoice = choice.id, darkModeSetting = if (dark) "DARK" else "LIGHT") {
                FontPicker(choice, AppLanguage.EN, onSelect = { choice = it }, onDismiss = {})
            }
        }
        for (selected in listOf(FontChoice.ROBOTO, FontChoice.EB_GARAMOND, FontChoice.INTER)) {
            compose.onNodeWithTag("font_choice_${selected.id}").performClick().assertIsSelected().assertHeightIsAtLeast(48.dp)
            compose.runOnIdle { assertEquals(selected, choice) }
            compose.onNodeWithTag("font_preview").performSemanticsAction(SemanticsActions.GetTextLayoutResult) { getResults ->
                val results = mutableListOf<androidx.compose.ui.text.TextLayoutResult>()
                getResults(results)
                assertEquals(AppFonts.family(selected), results.single().layoutInput.style.fontFamily)
            }
        }
        // Use the same native Canvas path as the app's screenshot harness:
        // Robolectric does not drive PixelCopy's frame-redraw callback.
        compose.runOnIdle {
            val dialog = requireNotNull(org.robolectric.shadows.ShadowDialog.getLatestDialog())
            val view = requireNotNull(dialog.window).decorView
            check(view.width > 0 && view.height > 0)
            val image = android.graphics.Bitmap.createBitmap(view.width, view.height, android.graphics.Bitmap.Config.ARGB_8888)
            view.draw(android.graphics.Canvas(image))
            val file = File("build/screenshots/fonts/${identity.id.lowercase()}_${if (dark) "dark" else "light"}.png")
            file.parentFile?.mkdirs()
            file.outputStream().use { image.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
        }
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun identities(): List<Array<Any>> = DesignLanguage.entries.flatMap { listOf(arrayOf<Any>(it, false), arrayOf<Any>(it, true)) }
    }
}
