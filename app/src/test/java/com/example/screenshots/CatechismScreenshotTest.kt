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
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.onAllNodesWithTag
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
import com.example.ui.screens.compendiumGroups
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
 * Renders every state of the Catechism inside the real app chrome (MainApp,
 * dock, glass back), reached by tapping through it the way a person would:
 * the book's cover and contents, search by topic and by number, a part, the
 * reader (focused, at rest, scrolled, at the end of a section), the
 * Compendium, an answer and the formulas.
 *
 * Output: build/screenshots/catechism/<state>_<light|dark>[_<lang>].png
 * Narrow with -Pshots.only=reader,search_text and -Pshots.mode=dark.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class CatechismScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val previousTimeZone = TimeZone.getDefault()
    private val heightDp = System.getProperty("shots.height")?.toIntOrNull()?.coerceIn(640, 1600) ?: 891

    @Before fun setUp() {
        if (heightDp != 891) org.robolectric.RuntimeEnvironment.setQualifiers("w411dp-h${heightDp}dp-xhdpi")
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("shots.zone", "America/Sao_Paulo")))
    }

    @After fun tearDown() { TimeZone.setDefault(previousTimeZone) }

    private val outputDir = File("build/screenshots/catechism").also { it.mkdirs() }
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

    private fun type(tag: String, text: String) {
        compose.onNodeWithTag(tag, useUnmergedTree = true).performTextInput(text)
        compose.waitForIdle()
    }

    private fun waitFor(tag: String) {
        compose.waitUntil(15_000) {
            compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        compose.waitForIdle()
    }

    private val repo by lazy { CatholicDataRepository(context) }
    private val biggestGroup by lazy { compendiumGroups(repo.compendiumQAEntries).maxBy { it.size } }

    /** State name -> how to get there from the Catechism's cover. */
    private val scenarios: List<Pair<String, () -> Unit>> = listOf(
        "index" to {},
        "index_scrolled" to { scrollTo("catechism_source_ccc") },
        "search_text" to { type("ccc_search_input", searchWord); waitFor("catechism_search_count") },
        "search_number" to { type("ccc_search_input", "1324"); waitFor("catechism_search_result_1324") },
        "search_empty" to {
            type("ccc_search_input", "zzqxv")
            waitFor("ccc_search_empty")
        },
        "part" to { tap("catechism_part_2"); waitFor("catechism_section_1322") },
        "part_scrolled" to { tap("catechism_part_2"); waitFor("catechism_section_1322"); scrollTo("catechism_key_1324") },
        "reader_focus" to {
            type("ccc_search_input", "1324")
            waitFor("catechism_search_result_1324")
            tap("catechism_search_result_1324")
            waitFor("catechism_article_1324")
        },
        "reader" to {
            tap("catechism_part_2"); waitFor("catechism_section_1322")
            tap("catechism_section_1322"); waitFor("catechism_article_1322")
        },
        "reader_scrolled" to {
            tap("catechism_part_2"); waitFor("catechism_section_1322")
            tap("catechism_section_1322"); waitFor("catechism_article_1322")
            scrollTo("catechism_article_1340")
        },
        "reader_end" to {
            tap("catechism_part_2"); waitFor("catechism_section_1322")
            tap("catechism_section_1322"); waitFor("catechism_article_1322")
            scrollTo("paragraph_next_button")
        },
        "reader_prologue" to { tap("catechism_part_0"); waitFor("catechism_article_1") },
        "compendium" to { scrollTo("catechism_open_compendium"); tap("catechism_open_compendium"); waitFor("compendium_search_input") },
        "answer" to {
            scrollTo("catechism_open_compendium"); tap("catechism_open_compendium"); waitFor("compendium_search_input")
            type("compendium_search_input", ((biggestGroup.first + biggestGroup.last) / 2).toString())
            waitFor("compendium_q_${biggestGroup.first}")
            tap("compendium_q_${biggestGroup.first}")
            waitFor("compendium_next_question")
        },
        // After the readers above have saved a position, the cover offers it.
        "index_continue" to {},
        "formulas" to { scrollTo("catechism_open_formulas"); tap("catechism_open_formulas"); waitFor("catechism_formulas_title") }
    )

    private val searchWord get() = when (language) { AppLanguage.EN -> "grace"; AppLanguage.PT -> "graça"; else -> "gracia" }

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
                                    initialDestination = AppDestination.CATECHISM
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
