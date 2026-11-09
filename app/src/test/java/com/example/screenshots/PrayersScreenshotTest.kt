package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
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
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File
import java.util.TimeZone

/**
 * Renders every state of the Prayers area inside the real app chrome
 * (MainApp, dock, glass back), reached by tapping through it the way a
 * person would: the landing, novenas grid, novena overview and day, prayer
 * detail, How to pray, the chaplet, the night overview and its three readers.
 *
 * Output: build/screenshots/prayers/<state>_<light|dark>[_<lang>].png
 * Narrow with -Pshots.only=novenas,novena_detail and -Pshots.mode=dark.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class PrayersScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val previousTimeZone = TimeZone.getDefault()
    private val heightDp = System.getProperty("shots.height")?.toIntOrNull()?.coerceIn(640, 1600) ?: 891

    @Before fun setUp() {
        if (heightDp != 891) org.robolectric.RuntimeEnvironment.setQualifiers("w411dp-h${heightDp}dp-xhdpi")
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("shots.zone", "America/Sao_Paulo")))
    }

    @After fun tearDown() { TimeZone.setDefault(previousTimeZone) }

    private val outputDir = File("build/screenshots/prayers").also { it.mkdirs() }
    private val context get() = ApplicationProvider.getApplicationContext<android.app.Application>()
    private val only: Set<String>? = System.getProperty("shots.only")
        ?.split(',')?.map { it.trim().lowercase() }?.filter { it.isNotEmpty() }?.toSet()
    private val language: AppLanguage = System.getProperty("shots.lang")
        ?.let { AppLanguage.fromCode(it.trim().lowercase()) } ?: AppLanguage.ES
    private val modeFilter = System.getProperty("shots.mode")?.trim()?.lowercase()

    private fun scrollTo(tag: String) {
        compose.onNode(hasScrollToNodeAction()).performScrollToNode(hasTestTag(tag))
        compose.waitForIdle()
    }

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag, useUnmergedTree = true).performClick()
        compose.waitForIdle()
    }

    private fun tapText(text: String) {
        compose.onAllNodesWithText(text).onFirst().performClick()
        compose.waitForIdle()
    }

    private val segNovenas get() = when (language) { AppLanguage.EN -> "Novenas"; else -> "Novenas" }
    private val segNight get() = when (language) { AppLanguage.ES -> "Noche"; AppLanguage.EN -> "Night"; AppLanguage.PT -> "Noite" }

    /** State name → how to get there from the Prayers tab. */
    private val scenarios: List<Pair<String, () -> Unit>> = listOf(
        "prayers" to {},
        "prayers_scrolled" to { scrollTo("prayer_devotion_angelus") },
        "prayer_detail" to { scrollTo("prayer_row_hail_mary"); tap("prayer_row_hail_mary") },
        "category" to { tap("prayer_category_tile_all") },
        "how_to_pray" to { scrollTo("how_to_pray_toggle"); tap("how_to_pray_toggle") },
        "chaplet" to { scrollTo("open_chaplet_row"); tap("open_chaplet_row") },
        "novenas" to { tapText(segNovenas) },
        "novena_detail" to {
            tapText(segNovenas)
            scrollTo("novena_card_novena-a-la-divina-misericordia")
            tap("novena_card_novena-a-la-divina-misericordia")
        },
        "novena_day" to {
            tapText(segNovenas)
            scrollTo("novena_card_novena-a-la-divina-misericordia")
            tap("novena_card_novena-a-la-divina-misericordia")
            tap("novena_start_button")
        },
        "night" to { tapText(segNight) },
        "compline" to { tapText(segNight); tap("night_office_compline") },
        "examen" to { tapText(segNight); tap("night_office_examen") },
        "traditional" to { tapText(segNight); tap("night_office_traditional") }
    )

    private fun appComposeView(): ComposeView =
        compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView

    private fun render(dark: Boolean) {
        if (modeFilter != null && modeFilter != (if (dark) "dark" else "light")) return
        val chosen = scenarios.filter { only == null || it.first in only }
        if (chosen.isEmpty()) return

        var renderApp by mutableStateOf(false)
        var generation by mutableIntStateOf(0)
        val repository = CatholicDataRepository(context).apply { setLanguage(language) }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        val tts = LiturgicalTtsManager(context)
        val store = UserPreferencesStore(context)
        val prefs = UserPreferences(
            language = language,
            designLanguage = DesignLanguage.APPLE.id,
            themePreset = UserPreferences.DEFAULT_THEME_PRESET,
            darkModeSetting = if (dark) "DARK" else "LIGHT",
            fontFamilyChoice = UserPreferences.DEFAULT_FONT_FAMILY,
            fontSizeScale = System.getProperty("shots.scale")?.toFloatOrNull()?.coerceIn(0.8f, 1.6f) ?: 1f,
            audioNarrationSpeed = 1f,
            activeBibleTranslationId = "",
            isFullCccDownloaded = false,
            hasChosenLanguage = true
        )
        try {
            compose.setContent {
                if (renderApp) {
                    SanctaFidesTheme(
                        designLanguage = prefs.designLanguage,
                        themePreset = prefs.themePreset,
                        darkModeSetting = prefs.darkModeSetting,
                        fontFamilyChoice = prefs.fontFamilyChoice,
                        fontScale = prefs.fontSizeScale
                    ) {
                        CompositionLocalProvider(LocalInspectionMode provides true) {
                            key(generation) {
                                MainApp(
                                    repository = repository,
                                    dao = db.dao(),
                                    ttsManager = tts,
                                    preferencesStore = store,
                                    preferences = prefs,
                                    initialDestination = AppDestination.PRAYERS
                                )
                            }
                        }
                    }
                }
            }
            val w = 411 * 2
            val h = heightDp * 2
            for ((name, go) in chosen) {
                compose.runOnUiThread { renderApp = false }
                compose.waitForIdle()
                compose.runOnUiThread {
                    generation++
                    renderApp = true
                }
                compose.waitForIdle()
                runCatching { go() }.onFailure { println("SCENARIO $name failed: ${it.message}") }
                compose.waitForIdle()
                compose.runOnIdle {
                    val view = appComposeView()
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
                compose.mainClock.advanceTimeBy(800)
                compose.waitForIdle()
                val bitmap = compose.runOnIdle {
                    val view = appComposeView()
                    Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888).also { view.draw(Canvas(it)) }
                }
                val suffix = (if (language == AppLanguage.ES) "" else "_${language.code}") +
                    (if (heightDp == 891) "" else "_h$heightDp")
                val file = File(outputDir, "${name}_${if (dark) "dark" else "light"}$suffix.png")
                file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
                println("WROTE ${file.absolutePath}")
            }
        } finally {
            try {
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

    @Test fun light() = render(false)
    @Test fun dark() = render(true)
}
