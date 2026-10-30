package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ui.design.AppNavItem
import com.example.ui.design.AppScaffold
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * Renders each visual identity to a PNG so the result can actually be looked
 * at. There is no KVM in this environment, so no emulator is available.
 *
 * The Compose test rule's `captureToImage()` is not used: it goes through
 * `PixelCopy`, which never completes a redraw under Robolectric. Attaching a
 * [ComposeView] to a real activity and drawing it onto a software bitmap gives
 * the same pixels without that machinery.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class ScreenshotTest {

    private val outputDir = File("build/screenshots").also { it.mkdirs() }

    private val navItems = listOf(
        AppNavItem("Home", Icons.Default.Home, Icons.Default.Home, "nav_home"),
        AppNavItem("Bible", Icons.Default.AutoStories, Icons.Default.AutoStories, "nav_bible"),
        AppNavItem("Catechism", Icons.Default.MenuBook, Icons.Default.MenuBook, "nav_catechism"),
        AppNavItem("Rosary", Icons.Default.SelfImprovement, Icons.Default.SelfImprovement, "nav_rosary"),
        AppNavItem("Doctrine", Icons.Default.Church, Icons.Default.Church, "nav_doctrine")
    )

    private fun idle() = shadowOf(Looper.getMainLooper()).idle()

    private fun capture(identity: DesignLanguage, dark: Boolean, heightPx: Int = 2600) {
        val controller = Robolectric.buildActivity(ComponentActivity::class.java).setup()
        val activity = controller.get()

        val composeView = ComposeView(activity).apply {
            setContent {
                SanctaFidesTheme(
                    designLanguage = identity.id,
                    themePreset = "LITURGICAL_DYNAMIC",
                    darkModeSetting = if (dark) "DARK" else "LIGHT"
                ) {
                    Surface(Modifier.fillMaxSize()) {
                        AppScaffold(
                            title = "Sancta Fides",
                            navItems = navItems,
                            selectedNavIndex = 1,
                            onNavSelected = {}
                        ) { insets ->
                            Box(Modifier.fillMaxSize().padding(insets)) {
                                DesignGallery()
                            }
                        }
                    }
                }
            }
        }

        activity.setContentView(
            composeView,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        idle()

        // A tall viewport so the whole gallery is laid out in one pass instead
        // of being clipped to one phone screen.
        val widthPx = (411 * 2)
        composeView.measure(
            View.MeasureSpec.makeMeasureSpec(widthPx, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(heightPx, View.MeasureSpec.EXACTLY)
        )
        composeView.layout(0, 0, widthPx, heightPx)
        idle()

        val bitmap = Bitmap.createBitmap(widthPx, heightPx, Bitmap.Config.ARGB_8888)
        composeView.draw(Canvas(bitmap))

        val name = "${identity.id.lowercase()}_${if (dark) "dark" else "light"}.png"
        val file = File(outputDir, name)
        file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        println("WROTE ${file.absolutePath} ${bitmap.width}x${bitmap.height} ${file.length()} bytes")
        controller.close()
    }

    @Test fun appleLight() = capture(DesignLanguage.APPLE, dark = false)
    @Test fun appleDark() = capture(DesignLanguage.APPLE, dark = true)
    @Test fun materialLight() = capture(DesignLanguage.MATERIAL, dark = false)
    @Test fun materialDark() = capture(DesignLanguage.MATERIAL, dark = true)
    @Test fun vaticanLight() = capture(DesignLanguage.VATICAN, dark = false)
    @Test fun vaticanDark() = capture(DesignLanguage.VATICAN, dark = true)
}
