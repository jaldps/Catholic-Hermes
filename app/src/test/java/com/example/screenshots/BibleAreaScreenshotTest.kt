package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppNavItem
import com.example.ui.design.AppIcons
import com.example.ui.design.AppScaffold
import com.example.ui.design.LocalScreenFrame
import com.example.ui.design.ScreenFrame
import com.example.ui.screens.BibleScreen
import com.example.ui.AppDestination
import com.example.ui.theme.LocalAppTextStyles
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * The Bible area's states the full-app render cannot reach (reader, Mass,
 * Study, versions), each in the real Lumen scaffold.
 *
 * Output: build/screenshots/bible/<state>_<light|dark>[_<lang>].png
 * Run with -Pshots.lang=es|pt; -Pshots.only=reader,missal,… narrows it.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class BibleAreaScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private data class State(val name: String, val tab: Int, val book: String? = null, val chapter: Int? = null, val verse: Int? = null)

    private val states = listOf(
        State("root", 0),
        State("missal", 1),
        State("study", 3),
        State("study_lesson", 3),
        State("study_teaching", 3),
        State("study_review", 3),
        State("study_notes", 3),
        State("study_actions", 3),
        State("versions", 2),
        State("reader", 0, "gen", 1),
        State("reader_john3", 0, "joh", 3, 16),
        State("reader_deutero", 0, "tob", 1),
        State("reader_psalm", 0, "psa", 23)
    )

    private val outputDir = File("build/screenshots/bible").also { it.mkdirs() }
    private val language: AppLanguage = System.getProperty("shots.lang")
        ?.let { AppLanguage.fromCode(it.trim().lowercase()) } ?: AppLanguage.ES
    private val only: Set<String>? = System.getProperty("shots.only")
        ?.split(',')?.map { it.trim() }?.filter { it.isNotEmpty() }?.toSet()
    private val heightDp = System.getProperty("shots.height")?.toIntOrNull()?.coerceIn(640, 2400) ?: 891

    private val widthDp = System.getProperty("shots.width")?.toIntOrNull()?.coerceIn(320,1280) ?: 411
    private val textScale = System.getProperty("shots.scale")?.toFloatOrNull()?.coerceIn(0.8f,2f) ?: 1f
    @Before fun configureViewport() { org.robolectric.RuntimeEnvironment.setQualifiers("w${widthDp}dp-h${heightDp}dp-xhdpi") }

    private fun render(dark: Boolean, identity: String = "LUMEN") {
        val context = ApplicationProvider.getApplicationContext<android.app.Application>()
        val repository = CatholicDataRepository(context).apply { setLanguage(language) }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build()
        val tts = LiturgicalTtsManager(context)
        val wanted = states.filter { only == null || it.name in only!! }
        var current by mutableStateOf<State?>(null)
        try {
            compose.setContent {
                val state = current ?: return@setContent
                SanctaFidesTheme(designLanguage = identity, fontScale = textScale, darkModeSetting = if (dark) "DARK" else "LIGHT") {
                    val largeNav = LocalAppTextStyles.current.navLabel.fontSize.value * LocalDensity.current.fontScale > 18f
                    val nav = AppDestination.tabs.map { destination ->
                        val (resting, active) = AppIcons.pair(destination.iconSlot)
                        val label = if (destination == AppDestination.MORE && largeNav) when (language) {
                            AppLanguage.ES -> "Más"; AppLanguage.EN -> "More"; AppLanguage.PT -> "Mais"
                        } else destination.tabLabel(language)
                        AppNavItem(label, resting, selectedIcon = active, tag = destination.tag)
                    }
                    CompositionLocalProvider(LocalInspectionMode provides true) {
                        key(state) {
                            AppScaffold(title = AppDestination.BIBLE.title(language), navItems = nav, selectedNavIndex = AppDestination.tabs.indexOf(AppDestination.BIBLE), onNavSelected = {}) { inner ->
                                val frame = remember { ScreenFrame() }
                                if (frame.insets != inner) frame.insets = inner
                                CompositionLocalProvider(LocalScreenFrame provides frame) {
                                    Box(
                                        Modifier.fillMaxSize().layout { m, c ->
                                            val top = if (frame.edgeToEdge) 0 else inner.calculateTopPadding().roundToPx()
                                            val bottom = if (frame.edgeToEdge) 0 else inner.calculateBottomPadding().roundToPx()
                                            val p = m.measure(c.copy(minHeight = 0, maxHeight = (c.maxHeight - top - bottom).coerceAtLeast(0)))
                                            layout(c.maxWidth, c.maxHeight) { p.place(0, top) }
                                        }
                                    ) {
                                        BibleScreen(
                                            repository = repository,
                                            currentLanguage = language,
                                            dao = db.dao(),
                                            ttsManager = tts,
                                            initialMainTab = state.tab,
                                            initialBookId = state.book,
                                            initialChapter = state.chapter,
                                            initialVerseStart = state.verse,
                                            onOpenCatechism = {}
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            val w = widthDp * 2
            val h = heightDp * 2
            for (state in wanted) {
                compose.runOnUiThread { current = state }
                compose.waitForIdle()
                compose.runOnIdle {
                    val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView
                    view.layoutParams = view.layoutParams.apply { width = w; height = h }
                    view.measure(View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY))
                    view.layout(0, 0, w, h)
                }
                compose.waitForIdle()
                if (state.name.startsWith("study_")) {
                    compose.onNodeWithTag("study_overview_page").performScrollToNode(hasTestTag("study_continue_button"))
                    compose.onNodeWithTag("study_continue_button").performClick()
                    val anchor = when (state.name) {
                        "study_teaching" -> "study_teaching_0"
                        "study_review" -> "study_review_0"
                        "study_notes" -> "lesson_notes_field"
                        "study_actions" -> "study_next_lesson"
                        else -> null
                    }
                    if (anchor != null) compose.onNodeWithTag("study_lesson_page").performScrollToNode(hasTestTag(anchor))
                    if (state.name == "study_review") {
                        compose.onNodeWithTag("study_review_0").performClick()
                        compose.onNodeWithTag("study_review_answer_0").performScrollTo()
                    }
                    if (state.name == "study_actions") {
                        val action = compose.onNodeWithTag("study_next_lesson").getUnclippedBoundsInRoot()
                        val dockItem = compose.onNodeWithTag("nav_bible").getUnclippedBoundsInRoot()
                        check(action.bottom <= dockItem.top) { "Study action must remain above the floating dock: $action / $dockItem" }
                    }
                    compose.waitForIdle()
                }
                val bitmap = compose.runOnIdle {
                    val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView
                    Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888).also { view.draw(Canvas(it)) }
                }
                val suffix = "_${language.code}" + (if (textScale == 1f) "" else "_scale_${(textScale * 100).toInt()}") + (if (widthDp == 411) "" else "_w$widthDp")
                val file = File(outputDir, "${state.name}_${identity.lowercase()}_${if (dark) "dark" else "light"}$suffix${if (heightDp != 891) "_h$heightDp" else ""}.png")
                file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
                println("WROTE ${file.absolutePath}")
                if (state.name.startsWith("reader") && (widthDp <= 360 || textScale > 1.4f)) {
                    compose.onNodeWithTag("bible_reader_chapter_title", useUnmergedTree = true)
                        .performSemanticsAction(SemanticsActions.GetTextLayoutResult) { getResults ->
                            val layouts = mutableListOf<androidx.compose.ui.text.TextLayoutResult>()
                            check(getResults(layouts) && layouts.isNotEmpty()) { "Chapter label has no measured text" }
                            check(layouts.none { it.hasVisualOverflow }) { "Current chapter must remain readable: " + layouts.joinToString { "${it.layoutInput.text} size=${it.size} constraints=${it.layoutInput.constraints} lines=${it.lineCount} max=${it.layoutInput.maxLines}" } }
                        }
                }

            }
        } finally {
            compose.runOnUiThread { current = null }
            compose.waitForIdle()
            tts.release()
            db.close()
            repository.close()
        }
    }

    @Test fun light() = render(false)
    @Test fun dark() = render(true)
    @Test fun sanctuaryLight() = render(false, "SANCTUARY")
    @Test fun sanctuaryDark() = render(true, "SANCTUARY")
}
