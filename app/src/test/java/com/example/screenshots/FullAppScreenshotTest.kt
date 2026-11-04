package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Before
import org.junit.After
import org.junit.Rule
import java.util.TimeZone
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * Renders the whole app — chrome, tab bar and every destination — exactly as
 * MainActivity composes it, at a phone's real viewport, in each identity and
 * in light and dark. This is what the design reviews judge.
 *
 * Output: build/screenshots/app/<destination>_<identity>_<light|dark>.png
 * Narrow a run with -Pshots.only=home,bible (destination names, lower case).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class FullAppScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val previousTimeZone = TimeZone.getDefault()

    private val widthDp = System.getProperty("shots.width")?.toIntOrNull()?.coerceIn(320, 1280) ?: 411
    private val heightDp = System.getProperty("shots.height")?.toIntOrNull()?.coerceIn(640, 1600) ?: 891

    @Before fun useReviewTimeZone() {
        if (widthDp != 411 || heightDp != 891) {
            org.robolectric.RuntimeEnvironment.setQualifiers("w${widthDp}dp-h${heightDp}dp-xhdpi")
        }
        // Keep date-sensitive UI aligned with this review's client timezone,
        // rather than letting the UTC build host advance the liturgical day.
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("shots.zone", "America/Sao_Paulo")))
    }

    @After fun restoreTimeZone() { TimeZone.setDefault(previousTimeZone) }


    private val outputDir = File("build/screenshots/app").also { it.mkdirs() }
    private val context get() = ApplicationProvider.getApplicationContext<android.app.Application>()

    private val only: Set<String>? = System.getProperty("shots.only")
        ?.split(',')?.map { it.trim().lowercase() }?.filter { it.isNotEmpty() }?.toSet()

    /** -Pshots.lang=es|pt renders the app in that language; English by default. */
    private val language: AppLanguage = System.getProperty("shots.lang")
        ?.let { AppLanguage.fromCode(it.trim().lowercase()) } ?: AppLanguage.EN

    private fun appComposeView(): ComposeView =
        compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView

    private fun render(identity: DesignLanguage, dark: Boolean) {
        val destinations = AppDestination.entries.filter { only == null || it.name.lowercase() in only }
        if (destinations.isEmpty()) return

        var renderApp by mutableStateOf(false)
        var currentDestination by mutableStateOf(destinations.first())
        val repository = CatholicDataRepository(context).apply { setLanguage(language) }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        val tts = LiturgicalTtsManager(context)
        val store = UserPreferencesStore(context)
        val prefs = UserPreferences(
            language = language,
            designLanguage = identity.id,
            themePreset = UserPreferences.DEFAULT_THEME_PRESET,
            darkModeSetting = if (dark) "DARK" else "LIGHT",
            fontFamilyChoice = System.getProperty("shots.font") ?: UserPreferences.DEFAULT_FONT_FAMILY,
            fontSizeScale = System.getProperty("shots.scale")?.toFloatOrNull()?.coerceIn(0.8f, 1.6f) ?: 1f,
            audioNarrationSpeed = 1f,
            activeBibleTranslationId = "",
            isFullCccDownloaded = false,
            hasChosenLanguage = true
        )

        try {
            // One composition per test lets the Compose rule track recomposition,
            // effects, and header registration before the native Canvas capture.
            compose.setContent {
                if (renderApp) {
                    SanctaFidesTheme(
                        designLanguage = prefs.designLanguage,
                        themePreset = prefs.themePreset,
                        darkModeSetting = prefs.darkModeSetting,
                        fontFamilyChoice = prefs.fontFamilyChoice,
                        fontScale = prefs.fontSizeScale
                    ) {
                        // Holds looping ornament still so Compose can become idle.
                        CompositionLocalProvider(LocalInspectionMode provides true) {
                            key(currentDestination) {
                                MainApp(
                                    repository = repository,
                                    dao = db.dao(),
                                    ttsManager = tts,
                                    preferencesStore = store,
                                    preferences = prefs,
                                    initialDestination = currentDestination
                                )
                            }
                        }
                    }
                }
            }
            val w = widthDp * 2
            val h = heightDp * 2
            for (destination in destinations) {
                // Finish disposing the previous screen, including its effects
                // and collectors, before mounting the next destination.
                compose.runOnUiThread { renderApp = false }
                compose.waitForIdle()
                compose.runOnUiThread {
                    currentDestination = destination
                    renderApp = true
                }
                compose.waitForIdle()
                compose.runOnIdle {
                    val view = appComposeView()
                    // Keep custom viewport dimensions through any subsequent
                    // traversal while capturing the actual activity composition.
                    view.layoutParams = view.layoutParams.apply {
                        width = w
                        height = h
                    }
                    view.measure(
                        View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY)
                    )
                    view.layout(0, 0, w, h)
                }
                compose.waitForIdle()
                val bitmap = compose.runOnIdle {
                    val view = appComposeView()
                    check(view.width == w && view.height == h) {
                        "Unexpected capture viewport: ${view.width}x${view.height}, expected ${w}x$h"
                    }
                    Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888).also {
                        // PixelCopy's redraw callback is not driven by Robolectric.
                        view.draw(Canvas(it))
                    }
                }
                val name = destination.name.lowercase()
                val suffix = (if (language == AppLanguage.EN) "" else "_${language.code}") +
                    (if (prefs.fontFamilyChoice == UserPreferences.DEFAULT_FONT_FAMILY) "" else "_font_${prefs.fontFamilyChoice.lowercase()}") +
                    (if (prefs.fontSizeScale == 1f) "" else "_scale_${(prefs.fontSizeScale * 100).toInt()}") +
                    (if (widthDp == 411 && heightDp == 891) "" else "_w${widthDp}_h${heightDp}")
                val file = File(outputDir, "${name}_${identity.id.lowercase()}_${if (dark) "dark" else "light"}$suffix.png")
                file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
                println("WROTE ${file.absolutePath}")
            }
        } finally {
            try {
                // Dispose screen collectors and effects before their resources.
                // Keep the host's composition alive and empty until rule teardown.
                compose.runOnUiThread { renderApp = false }
                compose.waitForIdle()
            } finally {
                try {
                    tts.release()
                } finally {
                    db.close()
                }
            }
        }
    }

    @Test fun appleLight() = render(DesignLanguage.APPLE, false)
    @Test fun appleDark() = render(DesignLanguage.APPLE, true)
    @Test fun materialLight() = render(DesignLanguage.MATERIAL, false)
    @Test fun materialDark() = render(DesignLanguage.MATERIAL, true)
}
