package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ui.components.SacredArt
import com.example.ui.components.SacredSubject
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * Gallery of every [SacredSubject] as a full-width hero banner, then as
 * squares, in each identity, light and dark.
 *
 * Output: build/screenshots/art/<identity>_<light|dark>.png
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class SacredArtScreenshotTest {

    private val outputDir = File("build/screenshots/art").also { it.mkdirs() }

    private fun idle() = shadowOf(Looper.getMainLooper()).idle()

    private fun render(identity: DesignLanguage, dark: Boolean) {
        val controller = Robolectric.buildActivity(ComponentActivity::class.java).setup()
        val activity = controller.get()
        val view = ComposeView(activity).apply {
            setContent {
                SanctaFidesTheme(
                    designLanguage = identity.id,
                    themePreset = "LITURGICAL_DYNAMIC",
                    darkModeSetting = if (dark) "DARK" else "LIGHT"
                ) {
                    val design = LocalAppDesign.current
                    val clip = if (identity == DesignLanguage.VATICAN) Modifier else Modifier.clip(design.shapes.cardLarge)
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        for (subject in SacredSubject.entries) {
                            SacredArt(subject, clip.fillMaxWidth().height(220.dp))
                        }
                        for (row in SacredSubject.entries.chunked(3)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                for (subject in row) {
                                    SacredArt(subject, clip.weight(1f).aspectRatio(1f))
                                }
                            }
                        }
                    }
                }
            }
        }
        activity.setContentView(
            view,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )
        idle()
        val w = 411 * 2
        val h = (16 * 2 + 6 * 232 + 2 * 133) * 2
        view.measure(
            View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY)
        )
        view.layout(0, 0, w, h)
        idle()
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        view.draw(Canvas(bitmap))
        val file = File(outputDir, "${identity.id.lowercase()}_${if (dark) "dark" else "light"}.png")
        file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        println("WROTE ${file.absolutePath}")
        controller.close()
    }

    @Test fun appleLight() = render(DesignLanguage.APPLE, false)
    @Test fun appleDark() = render(DesignLanguage.APPLE, true)
    @Test fun materialLight() = render(DesignLanguage.MATERIAL, false)
    @Test fun materialDark() = render(DesignLanguage.MATERIAL, true)
    @Test fun vaticanLight() = render(DesignLanguage.VATICAN, false)
    @Test fun vaticanDark() = render(DesignLanguage.VATICAN, true)
}
