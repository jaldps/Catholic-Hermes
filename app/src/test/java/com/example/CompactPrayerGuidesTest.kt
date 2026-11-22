package com.example

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.TextLayoutResult
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.screens.Devotion
import com.example.ui.screens.RosarySessionStore
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** The two real entry cards must fit together and retain their distinct destinations. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w800dp-h360dp-land-xhdpi")
class CompactPrayerGuidesTest(private val identity: DesignLanguage, private val language: AppLanguage, private val scale: Float) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var db: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        tts?.release(); repository?.close(); db?.close()
    }

    @Test fun bothCompactGuidesFitWithoutScrollingAndOpenTheCorrectDevotion() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repo = CatholicDataRepository(context).apply { setLanguage(language) }.also { repository = it }
        val database = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { db = it }
        val narration = LiturgicalTtsManager(context).also { tts = it }
        val store = UserPreferencesStore(context)
        val preferences = UserPreferences(language = language, designLanguage = identity.id,
            themePreset = UserPreferences.DEFAULT_THEME_PRESET, darkModeSetting = "LIGHT",
            fontFamilyChoice = UserPreferences.DEFAULT_FONT_FAMILY, fontSizeScale = scale,
            audioNarrationSpeed = 1f, activeBibleTranslationId = "bundled", isFullCccDownloaded = false,
            hasChosenLanguage = true, reduceMotion = true)
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT", fontScale = scale, reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repo, database.dao(), narration, store, preferences) }
            }
        }
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 360) }; compose.waitForIdle()
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, 360) }; compose.waitForIdle()
        val cards = listOf("nav_rosary", "open_chaplet_row").map { tag ->
            val node = compose.onNodeWithTag(tag).assertIsDisplayed().assertHasClickAction().fetchSemanticsNode()
            assertTrue("$tag above the bottom inset", node.boundsInRoot.bottom / compose.density.density <= 344f)
            assertTrue("$tag compact at $scale", node.boundsInRoot.height / compose.density.density <= 140f)
            assertTrue(node.boundsInRoot.height / compose.density.density >= 44f)
            node
        }
        assertEquals("The pair has equal height", cards[0].boundsInRoot.height, cards[1].boundsInRoot.height, 1f)
        compose.onAllNodes((hasAnyAncestor(hasTestTag("nav_rosary")) or hasAnyAncestor(hasTestTag("open_chaplet_row"))) and SemanticsMatcher("text layout") { it.config.contains(SemanticsActions.GetTextLayoutResult) }, useUnmergedTree = true)
            .fetchSemanticsNodes().forEach { node ->
                val results = mutableListOf<TextLayoutResult>()
                node.config.getOrNull(SemanticsActions.GetTextLayoutResult)?.action?.invoke(results)
                results.forEach { result ->
                    // MultiParagraph keeps the maximum constraint width even
                    // when Text shrinks to its intrinsic width. Inspect ink
                    // lines instead of that false didOverflowWidth signal.
                    for (line in 0 until result.lineCount) {
                        assertFalse("No ellipsis: ${result.layoutInput.text}", result.isLineEllipsized(line))
                        assertTrue("Whole line fits: ${result.layoutInput.text}", result.getLineRight(line) - result.getLineLeft(line) <= result.size.width + 1f)
                        assertTrue("Whole line height fits", result.getLineBottom(line) <= result.size.height + 1f)
                    }
                    assertEquals(result.layoutInput.text.length, result.getLineEnd(result.lineCount - 1))
                }
            }
        Thread.sleep(700)
        compose.runOnIdle {
            val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bitmap))
            File("build/screenshots/compact-guides").apply { mkdirs() }
                .resolve("guides_${identity.id}_${language.code}_${(scale * 100).toInt()}.png")
                .outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
            bitmap.recycle()
        }
        listOf("nav_rosary" to Devotion.ROSARY, "open_chaplet_row" to Devotion.MERCY).forEach { (tag, devotion) ->
            compose.onNodeWithTag(tag).performClick()
            compose.onNodeWithTag("rosary_screen").assertExists()
            assertEquals(devotion, compose.runOnIdle { RosarySessionStore.load(context)?.devotion })
            compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
            compose.onNodeWithTag("prayers_and_novenas_screen").assertIsDisplayed()
        }
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-{1}-{2}")
        fun cases(): List<Array<Any>> = listOf(DesignLanguage.APPLE, DesignLanguage.CLASSIC, DesignLanguage.GLASS, DesignLanguage.SANCTUARY).flatMap { identity ->
            listOf(AppLanguage.ES, AppLanguage.PT).flatMap { language -> listOf(1f, 1.3f).map { arrayOf<Any>(identity, language, it) } }
        }
    }
}
