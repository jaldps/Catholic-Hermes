package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.models.BibleVerse
import com.example.data.models.LocalizedText
import com.example.data.repository.BibleData
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.CrossReferenceStudy
import com.example.ui.screens.VerseSheet
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** The verse sheet (portrait) and the landscape study, in every identity. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class CrossReferenceScreenshotTest {

    private val outputDir = File("build/screenshots/xref").also { it.mkdirs() }
    private val language: AppLanguage = System.getProperty("shots.lang")
        ?.let { AppLanguage.fromCode(it) } ?: AppLanguage.EN

    private fun render(name: String, wDp: Int, hDp: Int, identity: DesignLanguage, dark: Boolean, content: @Composable () -> Unit) {
        val controller = Robolectric.buildActivity(ComponentActivity::class.java).setup()
        val activity = controller.get()
        val view = ComposeView(activity).apply {
            setContent {
                SanctaFidesTheme(designLanguage = identity.id, themePreset = "LITURGICAL_DYNAMIC", darkModeSetting = if (dark) "DARK" else "LIGHT") {
                    Surface(Modifier.fillMaxSize()) {
                        androidx.compose.runtime.CompositionLocalProvider(androidx.compose.ui.platform.LocalInspectionMode provides true) { content() }
                    }
                }
            }
        }
        activity.setContentView(view, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        val idle = { shadowOf(Looper.getMainLooper()).idle() }
        idle()
        val w = wDp * 2; val h = hDp * 2
        view.measure(View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY))
        view.layout(0, 0, w, h)
        repeat(8) { Thread.sleep(700); idle(); shadowOf(Looper.getMainLooper()).idleFor(java.time.Duration.ofSeconds(2)) }
        view.measure(View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY))
        view.layout(0, 0, w, h)
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        view.draw(Canvas(bitmap))
        val suffix = if (language == AppLanguage.EN) "" else "_${language.code}"
        File(outputDir, "${name}_${identity.id.lowercase()}_${if (dark) "dark" else "light"}$suffix.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        controller.close()
    }

    private fun shots(identity: DesignLanguage, dark: Boolean) {
        val repo = CatholicDataRepository(ApplicationProvider.getApplicationContext()).apply { setLanguage(language) }
        val john = BibleData.catholicCanonBooks.first { it.id == "joh" }
        val text = repo.getChapter("joh", 3, language).verses.first { it.number == 16 }.text
        val verse = BibleVerse(number = 16, text = LocalizedText(text.es, text.en, text.pt))
        render("sheet", 411, 891, identity, dark) {
            VerseSheet(repo, john, 3, verse, language) {}
        }
        render("study", 891, 411, identity, dark) {
            Row(Modifier.fillMaxSize()) {
                CrossReferenceStudy(repo, john, 3, 16, language, {}, Modifier.fillMaxHeight())
            }
        }
    }

    @Test @Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi") fun appleLight() = shots(DesignLanguage.APPLE, false)
    @Test @Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi") fun materialDark() = shots(DesignLanguage.MATERIAL, true)
    @Test @Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi") fun vaticanLight() = shots(DesignLanguage.VATICAN, false)
    @Test @Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi") fun vaticanDark() = shots(DesignLanguage.VATICAN, true)
}
