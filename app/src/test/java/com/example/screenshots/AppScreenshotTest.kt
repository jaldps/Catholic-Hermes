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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppNavItem
import com.example.ui.design.AppScaffold
import com.example.ui.screens.CatechismScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ParishFinderScreen
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
 * Renders the app's real screens — not the component gallery — in each visual
 * identity, so the identities can be judged on the actual product.
 *
 * Screens that need the Room DAO or the TTS manager are left out; those are
 * covered by the gallery, which exercises the same components.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class AppScreenshotTest {

    private val outputDir = File("build/screenshots/screens").also { it.mkdirs() }

    private val repository: CatholicDataRepository by lazy {
        CatholicDataRepository(ApplicationProvider.getApplicationContext())
    }

    private val navItems = listOf(
        AppNavItem("Home", Icons.Default.Home, Icons.Default.Home, "nav_home"),
        AppNavItem("Bible", Icons.Default.AutoStories, Icons.Default.AutoStories, "nav_bible"),
        AppNavItem("Catechism", Icons.Default.MenuBook, Icons.Default.MenuBook, "nav_catechism"),
        AppNavItem("Rosary", Icons.Default.SelfImprovement, Icons.Default.SelfImprovement, "nav_rosary"),
        AppNavItem("Doctrine", Icons.Default.Church, Icons.Default.Church, "nav_doctrine")
    )

    private fun idle() = shadowOf(Looper.getMainLooper()).idle()

    private fun capture(
        name: String,
        identity: DesignLanguage,
        dark: Boolean,
        selectedNav: Int,
        heightPx: Int = 2600,
        screen: @Composable () -> Unit
    ) {
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
                            selectedNavIndex = selectedNav,
                            onNavSelected = {}
                        ) { insets ->
                            Box(Modifier.fillMaxSize().padding(insets)) { screen() }
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

        val widthPx = 411 * 2
        composeView.measure(
            View.MeasureSpec.makeMeasureSpec(widthPx, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(heightPx, View.MeasureSpec.EXACTLY)
        )
        composeView.layout(0, 0, widthPx, heightPx)
        idle()

        val bitmap = Bitmap.createBitmap(widthPx, heightPx, Bitmap.Config.ARGB_8888)
        composeView.draw(Canvas(bitmap))

        val file = File(outputDir, "${name}_${identity.id.lowercase()}_${if (dark) "dark" else "light"}.png")
        file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        println("WROTE ${file.absolutePath} ${bitmap.width}x${bitmap.height}")
        controller.close()
    }

    private fun home(identity: DesignLanguage, dark: Boolean) =
        capture("home", identity, dark, selectedNav = 0) {
            HomeScreen(
                dailyReading = repository.dailyMassReading,
                verseOfTheDay = repository.verseOfTheDay,
                saintOfTheDay = repository.saintOfTheDay,
                currentLanguage = AppLanguage.EN,
                liturgicalDay = repository.getCurrentLiturgicalDay(),
                onOpenLanguageSelector = {},
                onNavigateToBible = {},
                onNavigateToRosary = {},
                onNavigateToPrayers = {},
                onNavigateToDoctrine = {},
                onNavigateToSpiritualLife = {}
            )
        }

    private fun catechism(identity: DesignLanguage, dark: Boolean) =
        capture("catechism", identity, dark, selectedNav = 2) {
            CatechismScreen(repository = repository, currentLanguage = AppLanguage.EN)
        }

    private fun parishes(identity: DesignLanguage, dark: Boolean) =
        capture("parishes", identity, dark, selectedNav = 4) {
            ParishFinderScreen(repository = repository, currentLanguage = AppLanguage.EN)
        }

    @Test fun homeApple() = home(DesignLanguage.APPLE, false)
    @Test fun homeMaterial() = home(DesignLanguage.MATERIAL, false)
    @Test fun homeVatican() = home(DesignLanguage.VATICAN, false)
    @Test fun homeVaticanDark() = home(DesignLanguage.VATICAN, true)

    @Test fun catechismApple() = catechism(DesignLanguage.APPLE, false)
    @Test fun catechismMaterial() = catechism(DesignLanguage.MATERIAL, false)
    @Test fun catechismVatican() = catechism(DesignLanguage.VATICAN, false)

    @Test fun parishesApple() = parishes(DesignLanguage.APPLE, false)
    @Test fun parishesMaterial() = parishes(DesignLanguage.MATERIAL, false)
    @Test fun parishesVatican() = parishes(DesignLanguage.VATICAN, false)
}
