package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.AppDestination
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * Rosary and Divine Mercy in several states (a long prayer, a Hail Mary inside
 * a decade, the chaplet), light and dark, through the real app chrome. Runs
 * only with -Pshots.only=rosarystates; output in build/screenshots/rosary_states.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class RosaryStatesShotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val outputDir = File("build/screenshots/rosary_states").also { it.mkdirs() }
    private val enabled = System.getProperty("shots.only")?.split(',')?.any { it.trim().lowercase() == "rosarystates" } == true
    private val language = System.getProperty("shots.lang")?.let { AppLanguage.fromCode(it.trim().lowercase()) } ?: AppLanguage.ES
    private val widthDp = System.getProperty("shots.width")?.toIntOrNull() ?: 411
    private val heightDp = System.getProperty("shots.height")?.toIntOrNull() ?: 891

    private val textScale = System.getProperty("shots.scale")?.toFloatOrNull()?.coerceIn(0.8f,2f) ?: 1f
    @Before fun configureViewport() { org.robolectric.RuntimeEnvironment.setQualifiers("w${widthDp}dp-h${heightDp}dp-xhdpi") }
    private var identity = "LUMEN"

    private fun capture(name: String, dark: Boolean) {
        val w = widthDp * 2
        val h = heightDp * 2
        compose.waitForIdle()
        Thread.sleep(1800)
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView
            view.layoutParams = view.layoutParams.apply { width = w; height = h }
            view.measure(View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY))
            view.layout(0, 0, w, h)
        }
        compose.waitForIdle()
        val bitmap = compose.runOnIdle {
            val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView
            Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888).also { view.draw(Canvas(it)) }
        }
        val file = File(outputDir, "${identity.lowercase()}_${if (dark) "dark" else "light"}_${name}_${language.code}_w${widthDp}_scale${(textScale * 100).toInt()}.png")
        file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        println("WROTE ${file.absolutePath}")
    }

    private fun next(times: Int, tag: String = "next_bead_button") {
        repeat(times) { compose.onNodeWithTag(tag).performClick() }
        compose.waitForIdle()
    }

    private fun run(dark: Boolean, selectedIdentity: String = "LUMEN") {
        if (!enabled) return
        identity = selectedIdentity
        val context = ApplicationProvider.getApplicationContext<android.app.Application>()
        val repository = CatholicDataRepository(context).apply { setLanguage(language) }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build()
        val tts = LiturgicalTtsManager(context)
        val store = UserPreferencesStore(context)
        val prefs = UserPreferences(
            language = language, designLanguage = identity, themePreset = UserPreferences.DEFAULT_THEME_PRESET,
            darkModeSetting = if (dark) "DARK" else "LIGHT",
            fontFamilyChoice = UserPreferences.DEFAULT_FONT_FAMILY, fontSizeScale = 1f, audioNarrationSpeed = 1f,
            activeBibleTranslationId = "", isFullCccDownloaded = false, hasChosenLanguage = true
        )
        try {
            compose.setContent {
                SanctaFidesTheme(
                    designLanguage = prefs.designLanguage, themePreset = prefs.themePreset,
                    darkModeSetting = prefs.darkModeSetting, fontFamilyChoice = prefs.fontFamilyChoice, fontScale = textScale
                ) {
                    CompositionLocalProvider(LocalInspectionMode provides true) {
                        MainApp(
                            repository = repository, dao = db.dao(), ttsManager = tts, preferencesStore = store,
                            preferences = prefs, initialDestination = AppDestination.ROSARY
                        )
                    }
                }
            }
            compose.waitForIdle()
            next(1)
            capture("1_creed", dark)
            next(9)
            capture("2_hailmary_decade", dark)
            val mercy = if (language == AppLanguage.ES) "Divina Misericordia" else if (language == AppLanguage.PT) "Divina Misericórdia" else "Divine Mercy"
            compose.onNodeWithText(mercy).performClick()
            compose.waitForIdle()
            capture("3_mercy_open", dark)
            next(13, "next_mercy_step_button")
            capture("4_mercy_decade", dark)
            // Back to the Rosary on a light painting (Annunciation): the light-glass
            // segmented control; then the guide toggle on (filled book).
            val rosary = if (language == AppLanguage.ES) "Santo Rosario" else if (language == AppLanguage.PT) "Santo Terço" else "The Holy Rosary"
            compose.onNodeWithText(rosary).performClick()
            compose.waitForIdle()
            compose.onNodeWithTag("rosary_mystery_picker").performClick()
            compose.waitForIdle()
            val joyful = if (language == AppLanguage.ES) "Misterios Gozosos" else if (language == AppLanguage.PT) "Mistérios Gozosos" else "Joyful Mysteries"
            compose.onNodeWithText(joyful, substring = true).performClick()
            compose.waitForIdle()
            capture("5_light_art", dark)
            compose.onNodeWithTag("prayer_instructions_toggle").performClick()
            compose.waitForIdle()
            capture("6_book_on", dark)
        } finally {
            tts.release()
            db.close()
            repository.close()
        }
    }

    @Test fun light() = run(false)
    @Test fun dark() = run(true)
    @Test fun sanctuaryLight() = run(false, "SANCTUARY")
    @Test fun sanctuaryDark() = run(true, "SANCTUARY")
}
