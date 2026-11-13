package com.example.screenshots

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import android.view.inspector.WindowInspector
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
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
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowDialog
import java.io.File
import java.util.TimeZone

/**
 * Focused states from the actual MainApp, including its chrome and real sheets.
 * Output: build/screenshots/devotional_priorities/<state>_<identity>_<mode>_<lang>_<viewport>_scale<N>.png
 *
 * Defaults to Spanish and both modes of Lumen and Sanctuary (32 frames).
 * -Pshots.only=home_top,home_practices,home_liturgy,prayers_top,journal,journal_categories,temptations,fall
 * narrows states; home_daily_verse, fall_actions and victory are additional focused captures.
 * -Pshots.width=320 -Pshots.height=760 -Pshots.scale=2 exercises the compact layout.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class DevotionalPrioritiesScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val widthDp = System.getProperty("shots.width")?.toIntOrNull()?.coerceIn(320, 1280) ?: 411
    private val heightDp = System.getProperty("shots.height")?.toIntOrNull()?.coerceIn(640, 1600) ?: 891
    private val textScale = System.getProperty("shots.scale")?.toFloatOrNull()?.coerceIn(0.8f, 2f) ?: 1f
    private val language = System.getProperty("shots.lang")
        ?.let { AppLanguage.fromCode(it.trim().lowercase()) } ?: AppLanguage.ES
    private val only = System.getProperty("shots.only")?.split(',')
        ?.map { it.trim().lowercase() }?.filter { it.isNotEmpty() }?.toSet()
    private val identityFilter = System.getProperty("shots.identity")?.trim()?.lowercase() ?: "all"
    private val modeFilter = System.getProperty("shots.mode")?.trim()?.lowercase()
    private val previousTimeZone = TimeZone.getDefault()
    private val context get() = ApplicationProvider.getApplicationContext<android.app.Application>()
    private val outputDir = File("build/screenshots/devotional_priorities")

    @Before fun configureViewport() {
        RuntimeEnvironment.setQualifiers("w${widthDp}dp-h${heightDp}dp-xhdpi")
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("shots.zone", "America/Sao_Paulo")))
    }

    @After fun restoreTimeZone() { TimeZone.setDefault(previousTimeZone) }

    private enum class Window { ACTIVITY, DIALOG, DIALOG_WITH_MENU }

    private data class Scenario(
        val name: String,
        val destination: AppDestination,
        val window: Window = Window.ACTIVITY,
        val default: Boolean = true,
        val reach: () -> Unit = {}
    )

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag, useUnmergedTree = true).performClick()
        compose.waitForIdle()
    }

    private fun scrollTo(tag: String) {
        compose.onNode(hasScrollToNodeAction()).performScrollToNode(hasTestTag(tag))
        compose.waitForIdle()
    }

    private fun openJournal() {
        // Room's first result arrives on IO after Compose can already be idle.
        // The loading list has no compose action yet. Await the actual empty
        // state, allowing LazyColumn to compose it at every viewport/text size.
        compose.waitUntil(timeoutMillis = 5_000) {
            try {
                scrollTo("add_first_journal_entry")
                true
            } catch (failure: AssertionError) {
                if (!failure.message.orEmpty().startsWith(
                        "No node found that matches TestTag = 'add_first_journal_entry' in scrollable container"
                    )) throw failure
                false
            }
        }
        tap("add_first_journal_entry")
        compose.onNodeWithTag("journal_composer", useUnmergedTree = true).assertIsDisplayed()
    }

    private fun openTemptations() {
        scrollTo("home_open_temptations")
        tap("home_open_temptations")
        compose.onNodeWithTag("tab_sin_avoider").assertIsDisplayed()
    }

    private fun expandMoreToday() {
        scrollTo("today_more_toggle")
        tap("today_more_toggle")
    }

    private fun openFall() {
        openTemptations()
        scrollTo("temptation_fell_button")
        val action = compose.onNodeWithTag("temptation_fell_button", useUnmergedTree = true)
            .getUnclippedBoundsInRoot()
        val dock = compose.onNodeWithTag("bottom_nav_bar", useUnmergedTree = true)
            .getUnclippedBoundsInRoot()
        assertTrue(
            "The fall action must clear the dock after scroll-to: action=$action, dock=$dock",
            action.bottom <= dock.top
        )
        tap("temptation_fell_button")
        // AppSheet mounts its Dialog and then reveals the content in an effect.
        // Await that actual measured sheet before asserting or scrolling it.
        compose.waitUntil(timeoutMillis = 5_000) {
            runCatching {
                compose.onNodeWithTag("fall_sheet", useUnmergedTree = true).assertIsDisplayed()
            }.isSuccess
        }
    }

    private val scenarios get() = listOf(
        Scenario("home_top", AppDestination.HOME),
        Scenario("home_practices", AppDestination.HOME, reach = {
            scrollTo("home_practices_heading")
            compose.onNodeWithTag("home_fidelity_card", useUnmergedTree = true).performScrollTo()
            compose.onNodeWithTag("home_fidelity_count", useUnmergedTree = true).assertIsDisplayed()
        }),
        Scenario("home_liturgy", AppDestination.HOME, reach = {
            expandMoreToday()
            scrollTo("daily_mass_card")
        }),
        Scenario("prayers_top", AppDestination.PRAYERS),
        Scenario("journal", AppDestination.SPIRITUAL_LIFE, Window.DIALOG, reach = { openJournal() }),
        Scenario("journal_categories", AppDestination.SPIRITUAL_LIFE, Window.DIALOG_WITH_MENU, reach = {
            openJournal()
            tap("journal_category_dropdown")
            compose.onNodeWithTag("journal_category_Agradecimiento", useUnmergedTree = true).assertIsDisplayed()
        }),
        Scenario("temptations", AppDestination.HOME, reach = { openTemptations() }),
        Scenario("fall", AppDestination.HOME, Window.DIALOG, reach = {
            openFall()
        }),
        Scenario("fall_actions", AppDestination.HOME, Window.DIALOG, default = false, reach = {
            openFall()
            compose.onNodeWithTag("record_fall_button", useUnmergedTree = true).performScrollTo()
            compose.onNodeWithTag("fall_cancel_button", useUnmergedTree = true).performScrollTo()
            compose.waitForIdle()
            compose.onNodeWithTag("record_fall_button", useUnmergedTree = true)
                .assertIsDisplayed().assertHasClickAction()
            compose.onNodeWithTag("fall_cancel_button", useUnmergedTree = true)
                .assertIsDisplayed().assertHasClickAction()
        }),
        Scenario("home_daily_verse", AppDestination.HOME, default = false, reach = {
            expandMoreToday()
            scrollTo("verse_of_the_day_card")
            val action = compose.onNodeWithTag("home_read_daily_verse", useUnmergedTree = true)
                .performScrollTo().assertIsDisplayed().assertHasClickAction().getUnclippedBoundsInRoot()
            val viewport = compose.onNodeWithTag("home_screen", useUnmergedTree = true)
                .getUnclippedBoundsInRoot()
            assertTrue(
                "The verse reader action must be fully visible: action=$action, viewport=$viewport",
                action.top >= viewport.top && action.bottom <= viewport.bottom &&
                    action.left >= viewport.left && action.right <= viewport.right
            )
        }),
        Scenario("victory", AppDestination.HOME, Window.DIALOG, default = false, reach = {
            openTemptations()
            scrollTo("emergency_overcome_button")
            tap("emergency_overcome_button")
            compose.onNodeWithTag("victory_sheet", useUnmergedTree = true).assertIsDisplayed()
        })
    )

    private fun appComposeView(): ComposeView =
        compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView

    private fun sizeActivity() {
        compose.runOnIdle {
            val view = appComposeView()
            val w = widthDp * 2
            val h = heightDp * 2
            view.layoutParams = view.layoutParams.apply { width = w; height = h }
            view.measure(
                View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY)
            )
            view.layout(0, 0, w, h)
        }
        compose.waitForIdle()
    }

    /** Draw the real popup window over its real dialog at the window's screen coordinates. */
    private fun captureWindow(window: Window): Bitmap = compose.runOnIdle {
        val view = if (window == Window.ACTIVITY) appComposeView() else {
            val dialog = requireNotNull(ShadowDialog.getLatestDialog()) { "Expected an open app sheet" }
            check(dialog.isShowing) { "The latest app sheet is no longer showing" }
            requireNotNull(dialog.window).decorView
        }
        check(view.width > 0 && view.height > 0) { "Capture window has no measured area" }
        if (window == Window.ACTIVITY) {
            check(view.width == widthDp * 2 && view.height == heightDp * 2)
        }
        Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888).also { bitmap ->
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            if (window == Window.DIALOG_WITH_MENU) {
                // DropdownMenu uses a separate PopupLayout window. Drawing only
                // the dialog would silently omit the category choices.
                val popup = WindowInspector.getGlobalWindowViews().lastOrNull {
                    it.javaClass.name.contains("PopupLayout") && it.isAttachedToWindow && it.width > 0 && it.height > 0
                } ?: error("The category menu has no attached native popup window")
                val dialogPosition = IntArray(2).also { view.getLocationOnScreen(it) }
                val popupPosition = IntArray(2).also { popup.getLocationOnScreen(it) }
                canvas.save()
                canvas.translate(
                    (popupPosition[0] - dialogPosition[0]).toFloat(),
                    (popupPosition[1] - dialogPosition[1]).toFloat()
                )
                popup.draw(canvas)
                canvas.restore()
            }
        }
    }

    private fun resetSessionPreferences() {
        // These are the test application's own stores. Keep each composition
        // independent of the previously captured destination's session.
        listOf("beginner_journey", "reading_position", "unfinished_prayer_session").forEach { name ->
            check(context.getSharedPreferences(name, Context.MODE_PRIVATE).edit().clear().commit())
        }
    }

    private fun render(identity: String, dark: Boolean) {
        if (identityFilter != "all" && identityFilter != identity.lowercase() &&
            !(identity == "LUMEN" && identityFilter == "apple")) return
        val mode = if (dark) "dark" else "light"
        if (modeFilter != null && modeFilter != mode) return
        val wanted = scenarios.filter { if (only == null) it.default else it.name in only }
        if (wanted.isEmpty()) return

        var renderApp by mutableStateOf(false)
        var generation by mutableIntStateOf(0)
        var destination by mutableStateOf(AppDestination.HOME)
        val repository = CatholicDataRepository(context).apply { setLanguage(language) }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java)
            .allowMainThreadQueries().build()
        val tts = LiturgicalTtsManager(context)
        val store = UserPreferencesStore(context)
        val prefs = UserPreferences(
            language = language,
            designLanguage = identity,
            themePreset = UserPreferences.DEFAULT_THEME_PRESET,
            darkModeSetting = if (dark) "DARK" else "LIGHT",
            fontFamilyChoice = System.getProperty("shots.font") ?: UserPreferences.DEFAULT_FONT_FAMILY,
            fontSizeScale = textScale,
            audioNarrationSpeed = 1f,
            activeBibleTranslationId = "",
            isFullCccDownloaded = false,
            hasChosenLanguage = true,
            reduceMotion = true
        )
        try {
            compose.setContent {
                if (renderApp) {
                    SanctaFidesTheme(
                        designLanguage = prefs.designLanguage,
                        themePreset = prefs.themePreset,
                        darkModeSetting = prefs.darkModeSetting,
                        fontFamilyChoice = prefs.fontFamilyChoice,
                        fontScale = prefs.fontSizeScale,
                        reduceMotion = true
                    ) {
                        CompositionLocalProvider(LocalInspectionMode provides true) {
                            key(generation) {
                                MainApp(repository, db.dao(), tts, store, prefs, initialDestination = destination)
                            }
                        }
                    }
                }
            }
            for (scenario in wanted) {
                compose.runOnUiThread { renderApp = false }
                compose.waitForIdle()
                resetSessionPreferences()
                compose.runOnUiThread {
                    generation++
                    destination = scenario.destination
                    renderApp = true
                }
                compose.waitForIdle()
                sizeActivity()
                scenario.reach()
                compose.waitForIdle()
                // Bundled artwork decodes on IO, after Compose has dispatched
                // its effects. Await that work before taking a cold-cache frame.
                Thread.sleep(2200)
                compose.waitForIdle()
                val bitmap = captureWindow(scenario.window)
                outputDir.mkdirs()
                val font = if (prefs.fontFamilyChoice == UserPreferences.DEFAULT_FONT_FAMILY) "" else "_font_${prefs.fontFamilyChoice.lowercase()}"
                val file = File(outputDir,
                    "${scenario.name}_${identity.lowercase()}_${mode}_${language.code}_w${widthDp}_h${heightDp}_scale${(textScale * 100).toInt()}$font.png")
                file.outputStream().use { check(bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)) }
                check(file.length() > 0) { "Empty screenshot: ${file.absolutePath}" }
                bitmap.recycle()
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
                    repository.close()
                }
            }
        }
    }

    @Test fun lumenLight() = render("LUMEN", false)
    @Test fun lumenDark() = render("LUMEN", true)
    @Test fun sanctuaryLight() = render("SANCTUARY", false)
    @Test fun sanctuaryDark() = render("SANCTUARY", true)
}
