package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
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
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
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

    private val outputDir = File("build/screenshots/app").also { it.mkdirs() }
    private val context get() = ApplicationProvider.getApplicationContext<android.app.Application>()

    private val only: Set<String>? = System.getProperty("shots.only")
        ?.split(',')?.map { it.trim().lowercase() }?.filter { it.isNotEmpty() }?.toSet()

    /** -Pshots.lang=es|pt renders the app in that language; English by default. */
    private val language: AppLanguage = System.getProperty("shots.lang")
        ?.let { AppLanguage.fromCode(it.trim().lowercase()) } ?: AppLanguage.EN

    private fun idle() = shadowOf(Looper.getMainLooper()).idle()

    private fun render(identity: DesignLanguage, dark: Boolean) {
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
            fontFamilyChoice = UserPreferences.DEFAULT_FONT_FAMILY,
            fontSizeScale = 1f,
            audioNarrationSpeed = 1f,
            activeBibleTranslationId = "",
            isFullCccDownloaded = false,
            hasChosenLanguage = true
        )

        for (destination in AppDestination.entries) {
            val name = destination.name.lowercase()
            if (only != null && name !in only) continue
            val controller = Robolectric.buildActivity(ComponentActivity::class.java).setup()
            val activity = controller.get()
            val view = ComposeView(activity).apply {
                setContent {
                    SanctaFidesTheme(
                        designLanguage = prefs.designLanguage,
                        themePreset = prefs.themePreset,
                        darkModeSetting = prefs.darkModeSetting
                    ) {
                        MainApp(
                            repository = repository,
                            dao = db.dao(),
                            ttsManager = tts,
                            preferencesStore = store,
                            preferences = prefs,
                            initialDestination = destination
                        )
                    }
                }
            }
            activity.setContentView(
                view,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            )
            idle()
            val w = 411 * 2
            val h = 891 * 2
            view.measure(
                View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY)
            )
            view.layout(0, 0, w, h)
            idle()
            val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bitmap))
            val suffix = if (language == AppLanguage.EN) "" else "_${language.code}"
            val file = File(outputDir, "${name}_${identity.id.lowercase()}_${if (dark) "dark" else "light"}$suffix.png")
            file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
            println("WROTE ${file.absolutePath}")
            controller.close()
        }
        db.close()
    }

    @Test fun appleLight() = render(DesignLanguage.APPLE, false)
    @Test fun appleDark() = render(DesignLanguage.APPLE, true)
    @Test fun materialLight() = render(DesignLanguage.MATERIAL, false)
    @Test fun materialDark() = render(DesignLanguage.MATERIAL, true)
    @Test fun vaticanLight() = render(DesignLanguage.VATICAN, false)
    @Test fun vaticanDark() = render(DesignLanguage.VATICAN, true)
}
