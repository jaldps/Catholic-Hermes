@file:OptIn(androidx.compose.ui.ExperimentalComposeUiApi::class, androidx.compose.ui.InternalComposeUiApi::class)

package com.example

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.layout.boundsInRoot
import androidx.activity.compose.setContent
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.TextLayoutResult
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.search.SearchIndexHolder
import com.example.ui.AppDestination
import com.example.ui.MainApp
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowDialog
import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * T-045: the whole app at 320 dp width with the app's font size at 130 %, in every identity and in
 * Spanish and Portuguese (the longest strings).
 *
 * It walks the semantics tree of every destination and of the important sub-screens (scrolling each
 * scroll container top to bottom and sampling at every step) and flags:
 *  - OVERFLOW:   text whose layout overflows its box (clipped, not ellipsized);
 *  - ELLIPSIS:   text cut with an ellipsis by `maxLines` (only allowed through [allowList], with a reason);
 *  - MIDWORD:    a word broken in the middle (letter-by-letter wrapping in a column that is too narrow);
 *  - CLIPPED:    text or a button whose bounds are cut by a parent or by the window (hidden or unreachable);
 *  - SMALL_TARGET: a clickable smaller than 48 dp in either dimension;
 *  - BEHIND_DOCK: content still under the floating tab bar after scrolling to the very end;
 *  - UNREACHED:  the scenario could not reach the screen it is meant to audit (a coverage failure).
 *
 * Narrow a run with the already-forwarded -Pshots.only=<scenario,…>, -Pshots.identity=<id> and -Pshots.lang=es|pt.
 * -Paudit.capture=true writes a PNG of every step that produced findings to build/screenshots/audit.
 * The findings table is written to build/audit/narrow-large-text.tsv (and -allowed.tsv).
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w320dp-h640dp-xhdpi")
class NarrowLargeTextAuditTest(private val identity: DesignLanguage, private val language: AppLanguage) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private var database: SanctaFidesDatabase? = null
    private var repository: CatholicDataRepository? = null
    private var narrator: LiturgicalTtsManager? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
        runBlocking { PersonalDataCommands.awaitIdle() }
        narrator?.release(); repository?.close(); database?.close(); SearchIndexHolder.reset()
    }

    // ----------------------------------------------------------------------------------------- findings

    data class Finding(val screen: String, val identity: String, val lang: String, val where: String, val kind: String, val detail: String) {
        fun row() = listOf(screen, identity, lang, where, kind, detail).joinToString("\t") { it.replace('\t', ' ').replace('\n', ' ') }
    }

    /** An intentional ellipsis (or other deliberate finding): [reason] says why the content is not lost. */
    data class Allow(val kind: String, val where: Regex, val reason: String)

    private val unexpected = linkedMapOf<String, Finding>()
    private val allowed = linkedMapOf<String, Finding>()
    private var screenName = ""

    private fun report(kind: String, where: String, detail: String) {
        val f = Finding(screenName, identity.id, language.code, where, kind, detail)
        val key = listOf(screenName.substringBefore('@'), kind, where).joinToString("|")
        val allow = allowList.firstOrNull { it.kind == kind && it.where.containsMatchIn(where) }
        if (allow != null) allowed.putIfAbsent(key, f.copy(detail = f.detail + " — " + allow.reason))
        else if (unexpected.putIfAbsent(key, f) == null) newThisStep = true
    }
    private var newThisStep = false
    private val clipCandidates = linkedMapOf<String, String>()

    // ----------------------------------------------------------------------------------------- driving

    private fun settle(times: Int = 3) = repeat(times) {
        Thread.sleep(100)
        compose.runOnUiThread { androidx.compose.runtime.snapshots.Snapshot.sendApplyNotifications() }
        compose.waitForIdle()
    }

    private fun matchTag(tag: String) = SemanticsMatcher("tag=$tag") { it.config.getOrNull(SemanticsProperties.TestTag) == tag }
    private fun matchPrefix(prefix: String, vararg not: String) = SemanticsMatcher("tag^$prefix") {
        val t = it.config.getOrNull(SemanticsProperties.TestTag)
        t != null && t.startsWith(prefix) && not.none { n -> t.startsWith(n) }
    }
    private fun matchText(text: String) = SemanticsMatcher("text=$text") { n ->
        n.config.getOrNull(SemanticsProperties.Text)?.any { it.text == text } == true
    }
    private fun nodes(m: SemanticsMatcher, merged: Boolean = false) =
        compose.onAllNodes(m, useUnmergedTree = !merged).fetchSemanticsNodes(false)
    private fun exists(m: SemanticsMatcher) = nodes(m).isNotEmpty()

    /** Waits for [m], scrolling the lazy containers toward it while it is not yet composed (like SearchScreenshotTest). */
    private fun waitFor(m: SemanticsMatcher, ms: Long = 20_000) {
        try {
            compose.waitUntil(ms) {
                compose.runOnUiThread { androidx.compose.runtime.snapshots.Snapshot.sendApplyNotifications() }
                if (!exists(m)) {
                    val count = compose.onAllNodes(hasScrollToNodeAction()).fetchSemanticsNodes(false).size
                    for (i in 0 until count) {
                        runCatching { compose.onAllNodes(hasScrollToNodeAction())[i].performScrollToNode(m) }
                        if (exists(m)) break
                    }
                }
                exists(m)
            }
        } catch (e: androidx.compose.ui.test.ComposeTimeoutException) {
            throw IllegalStateException("node ${m.description} never appeared", e)
        }
        compose.waitForIdle()
    }

    private fun scrollTo(m: SemanticsMatcher) {
        runCatching { compose.onAllNodes(m, useUnmergedTree = true)[0].performScrollTo() }
        compose.waitForIdle()
    }

    private fun tap(m: SemanticsMatcher) {
        waitFor(m)
        scrollTo(m)
        // performScrollTo can leave the node under the floating tab bar (a click there would hit a tab): lift it clear.
        runCatching {
            val node = compose.onAllNodes(m, useUnmergedTree = true)[0].fetchSemanticsNode()
            val height = (node.root as? View)?.height ?: 0
            if (height > 0 && node.boundsInRoot.bottom > height * 0.78f) {
                biggestScroller(rootKey(node))?.let { scrollBy(it, node.boundsInRoot.bottom - height * 0.5f) }
            }
        }
        compose.onAllNodes(m, useUnmergedTree = true)[0].performClick()
        compose.waitForIdle(); settle(2)
    }
    private fun tap(tag: String) = tap(matchTag(tag))
    private fun tapFirst(prefix: String, vararg not: String) = tap(matchPrefix(prefix, *not))
    private fun tapText(text: String) = tap(matchText(text))

    private fun t(es: String, en: String, pt: String) = when (language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }

    // ----------------------------------------------------------------------------------------- scenarios

    private class Scenario(val name: String, val destination: AppDestination, val welcomed: Boolean = true, val steps: NarrowLargeTextAuditTest.() -> Unit)

    private fun scenarios(): List<Scenario> {
        val list = mutableListOf<Scenario>()
        // Every top-level destination, as the app composes it.
        AppDestination.entries.forEach { d -> list += Scenario("dest_${d.name.lowercase()}", d) { audit("root") } }
        // Sub-screens.
        list += Scenario("bible_chapter", AppDestination.BIBLE) {
            tag("bible_continue_reading"); waitFor(matchTag("bible_reader")); audit("chapter")
        }
        list += Scenario("bible_mass", AppDestination.BIBLE) {
            tapText(t("Misa", "Mass", "Missa")); audit("mass")
        }
        list += Scenario("bible_lesson", AppDestination.BIBLE) {
            tapText(t("Estudio", "Study", "Estudo")); waitFor(matchTag("study_overview_page")); audit("overview")
            tap("study_continue_button"); waitFor(matchTag("study_lesson_page")); audit("lesson")
        }
        list += Scenario("prayer_detail", AppDestination.PRAYERS) {
            tap("prayer_row_hail_mary"); audit("hail_mary")
        }
        list += Scenario("prayer_category", AppDestination.PRAYERS) {
            tap("prayer_category_tile_all"); audit("category")
        }
        list += Scenario("novena_day", AppDestination.PRAYERS) {
            tapText("Novenas"); audit("novenas")
            tap("novena_card_novena-a-la-divina-misericordia"); audit("novena")
            tap("novena_start_button"); audit("day")
        }
        list += Scenario("rosary_mysteries", AppDestination.ROSARY) {
            tap("rosary_mystery_picker"); waitFor(matchTag("rosary_mysteries_sheet")); audit("mysteries_sheet")
        }
        list += Scenario("rosary_options", AppDestination.ROSARY) {
            tap("rosary_options_button"); waitFor(matchTag("rosary_options_sheet")); audit("options_sheet")
        }
        list += Scenario("saint_detail", AppDestination.SAINTS) {
            tapFirst("saint_row_"); audit("saint")
        }
        list += Scenario("order_detail", AppDestination.STUDY_ORDERS) {
            tapFirst("order_", "order_emblem"); audit("order")
        }
        list += Scenario("office_detail", AppDestination.STUDY_ORDERS) {
            tapText(t("Oficios", "Offices", "Ofícios")); audit("offices")
            tapFirst("office_"); audit("office")
        }
        list += Scenario("devotion_detail", AppDestination.STUDY_MARY_JESUS) {
            tapFirst("devotion_card_"); audit("devotion")
        }
        list += Scenario("doctrine_detail", AppDestination.STUDY_DOCTRINE) {
            tapFirst("commandment_row_"); audit("commandment")
        }
        list += Scenario("question_detail", AppDestination.QUESTIONS) {
            tap("qa_daily"); audit("question")
        }
        list += Scenario("catechism_article", AppDestination.CATECHISM) {
            tap("catechism_part_2"); waitFor(matchTag("ccc_node_p2s2")); audit("part")
            tap("ccc_node_p2s2"); waitFor(matchTag("ccc_node_p2s2c1"))
            tap("ccc_node_p2s2c1"); waitFor(matchTag("ccc_node_p2s2c1a3"))
            tap("ccc_node_p2s2c1a3"); waitFor(matchTag("catechism_article_1322")); audit("article")
        }
        list += Scenario("catechism_compendium", AppDestination.CATECHISM) {
            tap("catechism_open_compendium"); waitFor(matchTag("compendium_search_input")); audit("compendium")
        }
        list += Scenario("plan_detail", AppDestination.PLANS) {
            tap("plan_card_bible_year"); waitFor(matchTag("plan_detail_screen")); audit("plan")
        }
        list += Scenario("confession_flow", AppDestination.CONFESSION) {
            audit("intro_dialog")
            tapText(t("Entendido", "Got it", "Entendi"))
            audit("overview")
            tapFirst("confession_section_"); audit("examen")
        }
        list += Scenario("search_results", AppDestination.SEARCH) {
            waitFor(matchTag("search_field"))
            audit("empty")
            compose.onNodeWithTag("search_field").performTextReplacement(t("María", "Mary", "Maria"))
            waitFor(matchTag("search_group_bible"), 60_000); settle(); audit("results")
        }
        list += Scenario("reminders_sheet", AppDestination.REMINDERS) {
            tapFirst("notice_row_"); settle(); audit("notice_sheet")
        }
        list += Scenario("settings_appearance", AppDestination.MORE) {
            tap("app_bar_theme_button"); waitFor(matchTag("gui_customizer_sheet")); audit("appearance")
        }
        list += Scenario("settings_font", AppDestination.MORE) {
            tap("font_settings_button"); waitFor(matchTag("font_picker")); audit("font")
        }
        list += Scenario("settings_path", AppDestination.MORE) {
            tap("settings_welcome_profile"); waitFor(matchTag("welcome_profile_sheet")); audit("your_path")
        }
        list += Scenario("settings_backup", AppDestination.MORE) {
            tap("backup_entry"); settle(); audit("backup")
        }
        list += Scenario("settings_calendar_rules", AppDestination.MORE) {
            tap("calendar_rules_settings"); waitFor(matchTag("calendar_rules_sheet")); audit("calendar_rules")
        }
        list += Scenario("welcome_dialog", AppDestination.HOME, welcomed = false) {
            tap("today_more_toggle")
            tap("welcome_personalize"); waitFor(matchTag("welcome_dialog")); audit("welcome")
            tap("welcome_profile_1"); audit("welcome_profile")
        }
        return list
    }

    private fun tag(t: String) = tap(t)
    private fun pressBack() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle(); settle(2)
    }

    // ----------------------------------------------------------------------------------------- audit

    private val dpf get() = compose.activity.resources.displayMetrics.density

    private fun rootKey(n: SemanticsNode) = System.identityHashCode(n.root)

    private fun isVerticalScroller(n: SemanticsNode) =
        n.config.contains(SemanticsActions.ScrollBy) && n.config.contains(SemanticsProperties.VerticalScrollAxisRange)

    private fun scrollValue(n: SemanticsNode) = n.config[SemanticsProperties.VerticalScrollAxisRange].value()

    private fun biggestScroller(key: Int): SemanticsNode? =
        nodes(SemanticsMatcher("vscroll") { isVerticalScroller(it) && it.config[SemanticsProperties.VerticalScrollAxisRange].maxValue() > 0f }, merged = false)
            .filter { rootKey(it) == key && it.size.height / dpf >= 120f }
            .maxByOrNull { it.size.height.toLong() * it.size.width }

    private fun scrollBy(n: SemanticsNode, dy: Float) {
        compose.runOnUiThread { n.config[SemanticsActions.ScrollBy].action?.invoke(0f, dy) }
        compose.waitForIdle()
    }

    /** Samples the current screen (and each open sheet or dialog), scrolling every tall container top to bottom. */
    private fun audit(label: String) {
        screenName = "$currentScenario/$label"
        compose.waitForIdle(); settle(2)
        val keys = nodes(SemanticsMatcher("any") { true }).map { rootKey(it) }.distinct()
        for (key in keys) {
            var scroller = biggestScroller(key)
            if (scroller == null) { sample("$label@0", atEnd = true); continue }
            val viewport = scroller.size.height.toFloat()
            repeat(3) { biggestScroller(key)?.let { scrollBy(it, -1_000_000f) } }
            var step = 0
            var atEnd = false
            while (step < MAX_STEPS) {
                sample("$label@$step", atEnd = false)
                scroller = biggestScroller(key) ?: break
                val before = scrollValue(scroller)
                scrollBy(scroller, viewport * 0.85f)
                val after = biggestScroller(key)?.let { scrollValue(it) } ?: before
                if (abs(after - before) < 1e-4f) { atEnd = true; break }
                step++
            }
            // Jump to the very end and check what is left under the tab bar.
            repeat(3) { biggestScroller(key)?.let { scrollBy(it, 1_000_000f) } }
            sample("$label@end", atEnd = true)
        }
    }

    private fun overlaps(a: Rect, b: Rect) = min(a.right, b.right) - max(a.left, b.left) > 2f && min(a.bottom, b.bottom) - max(a.top, b.top) > 2f

    private fun nearestTag(n: SemanticsNode): String? =
        generateSequence(n) { it.parent }.firstNotNullOfOrNull { it.config.getOrNull(SemanticsProperties.TestTag) }

    private fun textOf(n: SemanticsNode): String =
        n.config.getOrNull(SemanticsProperties.Text)?.joinToString(" ") { it.text }
            ?: n.config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString(" ") ?: ""

    private fun label(n: SemanticsNode): String {
        val text = textOf(n).replace('\n', ' ')
        return (nearestTag(n)?.let { "[$it] " } ?: "") + if (text.length > 70) text.take(70) + "…" else text
    }

    private fun scrollerOf(n: SemanticsNode, vertical: Boolean): SemanticsNode? {
        val key = if (vertical) SemanticsProperties.VerticalScrollAxisRange else SemanticsProperties.HorizontalScrollAxisRange
        return generateSequence(n.parent) { it.parent }.firstOrNull { it.config.contains(key) && it.config.contains(SemanticsActions.ScrollBy) }
    }

    /** The node is cut by an ancestor or by the window (a scroll viewport cutting a scrolled item is normal). */
    private fun clipProblem(n: SemanticsNode): String? {
        val full = Rect(n.positionInRoot, Size(n.size.width.toFloat(), n.size.height.toFloat()))
        if (full.width < 1f || full.height < 1f) return null
        val c = n.boundsInRoot
        val vs = scrollerOf(n, vertical = true)
        val hs = scrollerOf(n, vertical = false)
        if (c.width <= 0f || c.height <= 0f) return if (vs == null && hs == null) "completely outside its parent or the window" else null
        val eps = 3.5f
        val d = dpf
        // A node that is being scrolled across a viewport edge is cut by the viewport (its extents are checked when it is fully in view).
        if (vs != null) {
            val vb = vs.boundsInRoot
            if ((full.top < vb.top - eps && abs(c.top - vb.top) <= eps) || (full.bottom > vb.bottom + eps && abs(c.bottom - vb.bottom) <= eps)) return null
        }
        val parts = mutableListOf<String>()
        fun edge(loss: Float, name: String, scroller: SemanticsNode?, at: Float, scrollerEdge: (Rect) -> Float) {
            if (loss > eps && !(scroller != null && abs(at - scrollerEdge(scroller.boundsInRoot)) <= eps)) parts += "$name ${"%.0f".format(loss / d)} dp"
        }
        edge(c.top - full.top, "top", vs, c.top) { it.top }
        edge(full.bottom - c.bottom, "bottom", vs, c.bottom) { it.bottom }
        edge(c.left - full.left, "left", hs, c.left) { it.left }
        edge(full.right - c.right, "right", hs, c.right) { it.right }
        val culprit = if (parts.isEmpty()) "" else generateSequence(n.layoutInfo.parentInfo) { it.parentInfo }.firstOrNull { li ->
            val b = li.coordinates.boundsInRoot(); b.bottom < full.bottom - eps || b.top > full.top + eps || b.right < full.right - eps
        }?.let { li ->
            val b = li.coordinates.boundsInRoot()
            " ancestor ${"%.0f".format(b.top / d)}..${"%.0f".format(b.bottom / d)} mods=" + li.getModifierInfo().joinToString("|") { it.modifier.toString().take(60) }.take(300)
        }.orEmpty()
        return if (parts.isEmpty()) null else "cut: " + parts.joinToString(", ") + culprit +
            " (node y ${"%.0f".format(full.top / d)}..${"%.0f".format(full.bottom / d)}, visible ${"%.0f".format(c.top / d)}..${"%.0f".format(c.bottom / d)}, vertical scroller ${vs?.boundsInRoot?.let { "%.0f..%.0f".format(it.top / d, it.bottom / d) }})"
    }

    private fun sample(step: String, atEnd: Boolean) {
        newThisStep = false
        val d = dpf
        val texts = nodes(SemanticsMatcher("text layout") { it.config.contains(SemanticsActions.GetTextLayoutResult) })
        val clickables = nodes(SemanticsMatcher("clickable") { it.config.contains(SemanticsActions.OnClick) }, merged = true)
        val dockTags = setOf("nav_home", "nav_prayers", "nav_bible", "nav_more")
        val dockNodes = nodes(SemanticsMatcher("dock") { it.config.getOrNull(SemanticsProperties.TestTag) in dockTags })
            .filter { it.boundsInRoot.center.y > (it.root as? View)?.height?.times(0.65f) ?: Float.MAX_VALUE }
        val dockRoot = dockNodes.firstOrNull()?.root
        val dock = dockNodes.map { it.boundsInRoot }.reduceOrNull { a, b -> Rect(min(a.left, b.left), min(a.top, b.top), max(a.right, b.right), max(a.bottom, b.bottom)) }
        fun inDock(n: SemanticsNode) = generateSequence(n) { it.parent }.any { it.config.getOrNull(SemanticsProperties.TestTag) in dockTags }
        // Prefetched lazy items are composed but not placed: their coordinates are stale, and nothing of them is drawn.
        fun hidden(n: SemanticsNode) = n.config.contains(SemanticsProperties.InvisibleToUser) || !n.layoutInfo.isPlaced

        compose.runOnIdle {
            for (n in texts) {
                if (hidden(n)) continue
                val results = mutableListOf<TextLayoutResult>()
                n.config[SemanticsActions.GetTextLayoutResult].action?.invoke(results)
                val layout = results.firstOrNull() ?: continue
                val full = layout.layoutInput.text.text
                if (full.isBlank()) continue
                val where = label(n)
                val maxLines = layout.layoutInput.maxLines
                val lastLine = layout.lineCount - 1
                val ellipsized = (0..lastLine).any { layout.isLineEllipsized(it) }
                // Width of the widest line's glyphs (a centred paragraph is laid out wider than its box, so compare extents, not positions).
                var widest = 0f
                for (i in 0..lastLine) {
                    val st = layout.getLineStart(i)
                    val en = layout.getLineEnd(i, visibleEnd = true)
                    if (en > st && en <= full.length) widest = max(widest, layout.getBoundingBox(en - 1).right - layout.getBoundingBox(st).left)
                }
                val widthOverflow = layout.layoutInput.constraints.hasBoundedWidth && widest > layout.size.width + 1f
                val heightOverflow = layout.getLineBottom(lastLine) > layout.size.height + 1f
                if (ellipsized || layout.multiParagraph.didExceedMaxLines) {
                    report("ELLIPSIS", where, "maxLines=$maxLines, ${layout.lineCount} lines, width ${"%.0f".format(layout.size.width / d)} dp")
                } else if (widthOverflow || heightOverflow) {
                    report("OVERFLOW", where, "text ${"%.0f".format(widest / d)}x${"%.0f".format(layout.getLineBottom(lastLine) / d)} dp in a box of ${"%.0f".format(layout.size.width / d)}x${"%.0f".format(layout.size.height / d)} dp; lines=${layout.lineCount}/max=$maxLines; constraints=${layout.layoutInput.constraints}; align=${layout.layoutInput.style.textAlign}; line0 l=${layout.getLineLeft(0)} r=${layout.getLineRight(0)}")
                }
                for (i in 0 until layout.lineCount - 1) {
                    val end = layout.getLineEnd(i, visibleEnd = false)
                    if (end in 1 until full.length && full[end - 1].isLetterOrDigit() && full[end].isLetterOrDigit()) {
                        val start = full.lastIndexOf(' ', end - 1).let { if (it < 0) 0 else it + 1 }
                        val stop = full.indexOf(' ', end).let { if (it < 0) full.length else it }
                        report("MIDWORD", where, "«${full.substring(start, stop)}» broken in a ${"%.0f".format(layout.size.width / d)} dp column")
                        break
                    }
                }
                clipProblem(n)?.let { clipCandidates[where] = it }
                if (atEnd && dock != null && n.root === dockRoot && !inDock(n) && overlaps(n.boundsInRoot, dock)) report("BEHIND_DOCK", where, "under the tab bar after scrolling to the end")
            }
            for (n in clickables) {
                if (hidden(n)) continue
                val where = label(n)
                val wDp = n.size.width / d
                val hDp = n.size.height / d
                val window = (n.root as? View)
                val scrim = window != null && n.size.width >= window.width * 0.9f && n.size.height >= window.height * 0.9f
                if (!scrim && n.size.width > 0 && (wDp < MIN_TARGET_DP - 0.5f || hDp < MIN_TARGET_DP - 0.5f) && !n.config.contains(SemanticsActions.SetText)) report("SMALL_TARGET", where, "${"%.0f".format(wDp)}x${"%.0f".format(hDp)} dp")
                clipProblem(n)?.let { clipCandidates[where] = it }
                if (atEnd && dock != null && n.root === dockRoot && !inDock(n) && overlaps(n.boundsInRoot, dock)) report("BEHIND_DOCK", where, "under the tab bar after scrolling to the end")
            }
        }
        if (clipCandidates.isNotEmpty()) {
            // A cut seen in one frame may be a lazy item still settling: report it only if it is still cut after the layout rests.
            val seen = clipCandidates.toMap(); clipCandidates.clear()
            settle(2)
            val again = nodes(SemanticsMatcher("text layout") { it.config.contains(SemanticsActions.GetTextLayoutResult) }) +
                nodes(SemanticsMatcher("clickable") { it.config.contains(SemanticsActions.OnClick) }, merged = true)
            compose.runOnIdle {
                for (n in again) {
                    val where = label(n)
                    if (where in seen) clipProblem(n)?.let { report("CLIPPED", where, it) }
                }
            }
        }
        if ((newThisStep && System.getProperty("audit.capture") == "true") || System.getProperty("audit.capture") == "all") capture("${currentScenario}_${step.replace('@', '_')}")
    }

    private fun capture(name: String) {
        // Bundled pictures decode off the main thread: give them a moment before the frame is drawn.
        if (System.getProperty("audit.capture") == "all") settle(6)
        compose.waitForIdle()
        compose.runOnIdle {
            val view = compose.activity.window.decorView
            if (view.width <= 0 || view.height <= 0) return@runOnIdle
            val full = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(full)
            view.draw(canvas)
            ShadowDialog.getLatestDialog()?.window?.decorView?.takeIf { it.width > 0 }?.draw(canvas)
            val file = File("build/screenshots/audit/${identity.id.lowercase()}_${language.code}_$name.png")
            file.parentFile!!.mkdirs(); file.outputStream().use { full.compress(Bitmap.CompressFormat.PNG, 100, it) }
            full.recycle()
        }
    }

    // ----------------------------------------------------------------------------------------- run

    private var currentScenario = ""

    @Test fun nothingIsClippedOrUnreachableAt320dpWith130PercentText() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val store = UserPreferencesStore(app)
        val repo = CatholicDataRepository(app).apply { setLanguage(language) }.also { repository = it }
        val prefs = runBlocking { store.preferences.first() }.copy(
            language = language, hasChosenLanguage = true, designLanguage = identity.id, darkModeSetting = "LIGHT", fontSizeScale = FONT_SCALE
        )
        val db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(app).also { narrator = it }

        var render by mutableStateOf(false)
        var current by mutableStateOf(AppDestination.HOME)
        var generation by mutableStateOf(0)
        compose.setContent {
            if (render) SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT", fontScale = FONT_SCALE, reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    key(generation) { MainApp(repo, db.dao(), tts, store, prefs, initialDestination = current) }
                }
            }
        }

        val onlySet = System.getProperty("shots.only")?.split(',')?.map { it.trim() }?.filter { it.isNotEmpty() }?.toSet()
        for (s in scenarios().filter { sc -> onlySet == null || onlySet.any { o -> sc.name.startsWith(o) } }) {
            currentScenario = s.name
            screenName = "${s.name}/setup"
            app.getSharedPreferences("beginner_journey", 0).edit().clear().apply {
                if (s.welcomed) putBoolean("welcomed", true).putInt("profile", 1).putInt("minutes", 10)
            }.commit()
            SearchIndexHolder.reset()
            compose.runOnUiThread { render = false }; compose.waitForIdle()
            compose.runOnUiThread { current = s.destination; generation++; render = true }
            compose.waitForIdle(); settle(4)
            try {
                s.steps(this)
            } catch (e: Throwable) {
                if (System.getProperty("audit.capture") == "true") capture("${s.name}_UNREACHED")
                report("UNREACHED", s.name, e.javaClass.simpleName + ": " + (e.message ?: "").lineSequence().firstOrNull().orEmpty().take(160))
            }
            println("AUDIT ${identity.id} ${language.code} ${s.name}: ${unexpected.size} findings so far")
        }
        compose.runOnUiThread { render = false }; compose.waitForIdle()

        val dir = File("build/audit").also { it.mkdirs() }
        File(dir, "findings_${identity.id.lowercase()}_${language.code}.tsv").writeText(unexpected.values.joinToString("\n") { it.row() })
        File(dir, "allowed_${identity.id.lowercase()}_${language.code}.tsv").writeText(allowed.values.joinToString("\n") { it.row() })
        assertTrue(
            "Unexpected findings at 320 dp / 130 % (${identity.id}, ${language.code}):\n" +
                unexpected.values.joinToString("\n") { "  " + it.row() },
            unexpected.isEmpty()
        )
    }

    companion object {
        private const val FONT_SCALE = 1.3f
        private const val MAX_STEPS = 16

        /** The app's touch-target standard (also the platform's 44 dp HIG floor); 48 dp is the material minimum. */
        private const val MIN_TARGET_DP = 44f

        /** Intentional ellipses and other deliberate findings, each with the reason the content is not lost. */
        val allowList: List<Allow> = listOf(
            Allow("ELLIPSIS", Regex("""\[app_bar_title]"""),
                "The compact top-bar title only repeats the page's own large title, which is shown in full on the page (and read out as the heading)."),
            Allow("ELLIPSIS", Regex("""^(\[[a-z_]+] )?(Buscar|Pesquisar|Cidade, bairro|Nome, país|Search|City, |Ciudad, barrio|Name, country|Nombre, país)"""),
                "Placeholder (hint) of a one-line search field: it disappears as soon as the user types, and the field keeps its full accessible name."),
            Allow("SMALL_TARGET", Regex("""\[fidelity_day_\d+]"""),
                "Day cells of the month calendar: 36 x 46 dp because seven columns must fit 320 dp (the row is 46 dp tall; the 44 dp floor holds vertically)."),
            Allow("MIDWORD", Regex("""\[order_premonstratenses-opraem]"""),
                "«Premonstratenses» (16 letters) is wider than the 162 dp left beside the emblem and chevron at 130 %; every other word of the name wraps, and the page repeats it in full."),
            Allow("MIDWORD", Regex("""\[prayer_row_nicene_creed]"""),
                "«Constantinopolitano» (19 letters) is wider than the 220 dp row column at 130 %; a single word cannot wrap, and the full title is shown on the prayer's page."),
        )

        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0} {1}")
        fun cases(): List<Array<Any>> {
            val ids = System.getProperty("shots.identity")?.trim()?.lowercase()?.takeIf { it != "all" }
            val lang = System.getProperty("shots.lang")?.trim()?.lowercase()?.let { AppLanguage.fromCode(it) }
            return DesignLanguage.selectable
                .filter { ids == null || it.id.lowercase() == ids || (ids == "apple" && it == DesignLanguage.APPLE) }
                .flatMap { id -> listOf(AppLanguage.ES, AppLanguage.PT).filter { lang == null || it == lang }.map { arrayOf<Any>(id, it) } }
        }
    }
}
