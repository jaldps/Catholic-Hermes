@file:OptIn(androidx.compose.ui.ExperimentalComposeUiApi::class, androidx.compose.ui.InternalComposeUiApi::class)

package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.semantics.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.TextLayoutResult
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.ui.AppDestination
import com.example.ui.theme.DesignLanguage
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File
import kotlin.math.abs

internal enum class LandscapeIssue { HEADER_TOO_TALL, ESSENTIAL_BELOW_FOLD, MEASURE, UNDER_INSET, IME_HIDDEN, SHEET_GEOMETRY, OBSCURED, VERTICAL_GAP }

/** All geometry is in physical-window dp, independent of the content's constraints. */
internal object LandscapeAuditRules {
    fun headerTooTall(height: Float, windowHeight: Float) = height > windowHeight * 0.30f + 1f
    fun underInset(rect: Rect, safe: Rect) = rect.left < safe.left - 1f || rect.right > safe.right + 1f || rect.top < safe.top - 1f || rect.bottom > safe.bottom + 1f
    fun hiddenByIme(rect: Rect, height: Float, ime: Float) = ime > 0 && rect.bottom > height - ime + 1f
    fun outsideMeasure(characters: Float, minimum: Float = 60f) = characters < minimum - 1f || characters > 75f + 1f
    fun wrongSheet(rect: Rect, expected: Rect) = listOf(rect.left - expected.left, rect.right - expected.right, rect.top - expected.top, rect.bottom - expected.bottom).any { abs(it) > 1.1f }
    fun obscures(control: Rect, text: Rect) = control.overlaps(text) && control.intersect(text).let { it.width > 2f && it.height > 2f }
    fun verticalGap(headingBottom: Float, textTop: Float) = textTop - headingBottom > 24f + 1f
    fun belowFold(essential: List<String>, visible: Set<String>) = essential.filterNot { it in visible }
}

/** Root-level L0 audit. Area-owned essential targets deliberately remain empty until L1–L7. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class LandscapeAuditTest(private val identity: DesignLanguage, private val language: AppLanguage, private val width: Int, private val height: Int, private val scale: Float) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var fixture: LandscapeRealAppFixture? = null
    private val findings = linkedSetOf<String>()
    private val essentialByArea = landscapeRoots.associateWith { emptyList<String>() } +
        (AppDestination.ROSARY to listOf("prayer_bead_counter", "rosary_hero_title", "current_prayer_title",
            "prev_bead_button", "toggle_prayer_audio_button", "next_bead_button", "rosary_options_button",
            "prayer_instructions_toggle", "page_dots"))
    private fun all() = compose.onAllNodes(SemanticsMatcher("any node") { true }, useUnmergedTree = true).fetchSemanticsNodes(false)
    private fun name(node: SemanticsNode) = generateSequence(node) { it.parent }.firstNotNullOfOrNull { it.config.getOrNull(SemanticsProperties.TestTag) }
        ?: node.config.getOrNull(SemanticsProperties.Text)?.joinToString { it.text }?.take(70).orEmpty()
    private fun dp(rect: Rect) = rect.let { Rect(it.left / 2f, it.top / 2f, it.right / 2f, it.bottom / 2f) }
    private fun report(area: String, issue: LandscapeIssue, node: String, detail: String) { findings += "$area\t${issue.name}\t$node\t${detail.replace('\n', ' ')}" }
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle(); fixture?.close()
    }

    private fun sample(destination: AppDestination, cutout: String?, navbar: String?, at: String) {
        val area = "${destination.name.lowercase()}/$at/${cutout ?: "none"}/${navbar ?: "bottom"}"
        val safe = Rect(if (cutout == "left") 32f else if (navbar == "left") 48f else 0f, 24f,
            width - if (navbar == "right") 48f else if (cutout == "right") 32f else 0f,
            height - if (navbar == null) 16f else 0f)
        val nodes = all().filter { it.layoutInfo.isPlaced && it.boundsInRoot.width > 0 && it.boundsInRoot.height > 0 && !it.config.contains(SemanticsProperties.InvisibleToUser) }
        val controls = nodes.filter { it.config.contains(SemanticsActions.OnClick) && name(it) != "sheet_scrim" }
        compose.runOnIdle {
            nodes.filter { it.config.getOrNull(SemanticsProperties.TestTag) == "compact_header" }.forEach { n ->
                if (LandscapeAuditRules.headerTooTall(n.size.height / 2f, height.toFloat())) report(area, LandscapeIssue.HEADER_TOO_TALL, name(n), "height=${n.size.height / 2f}, H=$height")
            }
            // A lazy item crossing a scroll viewport edge is sampled when it
            // becomes fully visible. It is not an inset violation at that edge.
            controls.forEach { n ->
                val b = dp(n.boundsInRoot)
                val full = dp(Rect(n.positionInRoot, Size(n.size.width.toFloat(), n.size.height.toFloat())))
                val fullyVisible = abs(b.width - full.width) < 1f && abs(b.height - full.height) < 1f
                if (fullyVisible && LandscapeAuditRules.underInset(b, safe)) report(area, LandscapeIssue.UNDER_INSET, name(n), "$b vs $safe")
            }
            nodes.filter { it.config.getOrNull(SemanticsProperties.Focused) == true && it.config.contains(SemanticsActions.SetText) }.forEach { n ->
                // Root runs have no IME; focused/keyboard scenarios use the
                // same detector with real 0.56H dispatch in the modal tests.
                if (LandscapeAuditRules.hiddenByIme(dp(n.boundsInRoot), height.toFloat(), 0f)) report(area, LandscapeIssue.IME_HIDDEN, name(n), "focused field below keyboard")
            }
            val texts = nodes.filter { it.config.contains(SemanticsActions.GetTextLayoutResult) }
            texts.forEach { text ->
                val layouts = mutableListOf<TextLayoutResult>()
                text.config[SemanticsActions.GetTextLayoutResult].action?.invoke(layouts)
                layouts.forEach { layout ->
                    // Only dedicated reading surfaces have a 60–75 character
                    // measure; dashboard captions and excerpts do not.
                    if (name(text) == "showcase_reading" && layout.lineCount > 1) {
                        val longest = (0 until layout.lineCount).maxOf { layout.getLineEnd(it, true) - layout.getLineStart(it) }.toFloat()
                        if (LandscapeAuditRules.outsideMeasure(longest)) report(area, LandscapeIssue.MEASURE, name(text), "longest=$longest characters")
                    }
                }
                controls.filter { it.root === text.root && generateSequence(text) { it.parent }.none { parent -> parent.id == it.id } }
                    .filter { control -> generateSequence(control) { it.parent }.none { parent -> parent.id == text.id } }
                    .forEach { control -> if (LandscapeAuditRules.obscures(dp(control.boundsInRoot), dp(text.boundsInRoot)))
                        report(area, LandscapeIssue.OBSCURED, name(text), "overlap with ${name(control)}") }
            }
            fun panel(node: SemanticsNode): Int = generateSequence(node) { it.parent }.firstOrNull {
                it.config.getOrNull(SemanticsProperties.IsTraversalGroup) == true
            }?.id ?: -1
            texts.filter { it.config.getOrNull(SemanticsProperties.Heading) != null }.forEach { heading ->
                val next = texts.filter { it.id != heading.id && panel(it) == panel(heading) && it.boundsInRoot.top >= heading.boundsInRoot.bottom - 1f }
                    .minByOrNull { it.boundsInRoot.top }
                // A sacred painting is content between the context title and its fruit, not empty space.
                val paintingBetween = destination == AppDestination.ROSARY && next != null && nodes.any {
                    it.config.getOrNull(SemanticsProperties.TestTag) in listOf("rosary_hero_photo", "mercy_hero_photo") &&
                        it.boundsInRoot.top >= heading.boundsInRoot.bottom - 1f && it.boundsInRoot.bottom <= next.boundsInRoot.top + 1f
                }
                if (next != null && !paintingBetween && LandscapeAuditRules.verticalGap(heading.boundsInRoot.bottom / 2f, next.boundsInRoot.top / 2f))
                    report(area, LandscapeIssue.VERTICAL_GAP, name(heading), "gap=${(next.boundsInRoot.top - heading.boundsInRoot.bottom) / 2f}")
            }
            val visible = nodes.mapNotNull { it.config.getOrNull(SemanticsProperties.TestTag) }.toSet()
            val mercy = "next_mercy_step_button" in visible
            val required = essentialByArea.getValue(destination).map { tag -> if (!mercy) tag else when (tag) {
                "prayer_bead_counter" -> "mercy_step_counter"
                "rosary_hero_title" -> "mercy_hero_title"
                "prev_bead_button" -> "prev_mercy_step_button"
                "next_bead_button" -> "next_mercy_step_button"
                "toggle_prayer_audio_button" -> "toggle_mercy_audio_button"
                else -> tag
            } }
            val fullyVisible = if (destination != AppDestination.ROSARY) visible else nodes.filter { node ->
                val b = dp(node.boundsInRoot)
                val full = dp(Rect(node.positionInRoot, Size(node.size.width.toFloat(), node.size.height.toFloat())))
                b.top >= safe.top - .5f && b.bottom <= safe.bottom + .5f && b.left >= safe.left - .5f && b.right <= safe.right + .5f &&
                    abs(b.width - full.width) < 1f && abs(b.height - full.height) < 1f
            }.mapNotNull { it.config.getOrNull(SemanticsProperties.TestTag) }.toSet()
            LandscapeAuditRules.belowFold(required, fullyVisible).forEach { report(area, LandscapeIssue.ESSENTIAL_BELOW_FOLD, it, "area target not visible") }
        }
    }

    @Test fun rootsRespectCompactHeadersAndRealInsetsAcrossTheRequiredMatrix() {
        landscapeViewport(compose, compose.activity, width, height)
        val resources = LandscapeRealAppFixture(ApplicationProvider.getApplicationContext<Application>(), language).also { fixture = it }
        var visible by mutableStateOf(false)
        var destination by mutableStateOf(AppDestination.HOME)
        compose.setContent { if (visible) key(destination) { resources.Content(destination, identity, scale) } }
        val variants = mutableListOf<Pair<String?, String?>>(null to null)
        if (identity == DesignLanguage.APPLE && language == AppLanguage.ES && scale == 1f) {
            variants += listOf("left" to null, "right" to null, null to "right", null to "left")
        }
        for ((cutout, navbar) in variants) for (root in landscapeRoots) {
            compose.runOnUiThread { visible = false }; compose.waitForIdle()
            compose.runOnUiThread { destination = root; visible = true }; compose.waitForIdle()
            compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, height, cutout = cutout, navbar = navbar) }; compose.waitForIdle()
            sample(root, cutout, navbar, "top")
            if (System.getProperty("audit.capture") == "all") {
                // Match the full-app renderer's cold-cache allowance for bundled art on Dispatchers.IO.
                Thread.sleep(2500)
                compose.waitForIdle()
                val suffix = (if (scale == 1f) "" else "_s130") + (if (language == AppLanguage.PT) "_pt" else "") +
                    (cutout?.let { "_cut${it.first().uppercase()}" } ?: "") + (navbar?.let { "_nav${it.first().uppercase()}" } ?: "")
                landscapeCapture(compose, compose.activity, width, height,
                    File("build/screenshots/landscape-audit/l0_${root.name.lowercase()}_${identity.id.lowercase()}_claro_${width}x$height$suffix.png"))
            }
            val scroll = compose.onAllNodes(SemanticsMatcher("vertical scroll") { it.config.contains(SemanticsActions.ScrollBy) && it.config.contains(SemanticsProperties.VerticalScrollAxisRange) }, useUnmergedTree = true)
                .fetchSemanticsNodes(false).maxByOrNull { it.boundsInRoot.width * it.boundsInRoot.height }
            scroll?.let { compose.runOnUiThread { it.config[SemanticsActions.ScrollBy].action?.invoke(0f, 1_000_000f) }; compose.waitForIdle() }
            sample(root, cutout, navbar, "end")
        }
        val output = File("build/audit/landscape_${identity.id.lowercase()}_${language.code}_${width}x${height}_${(scale * 100).toInt()}.tsv")
        output.parentFile!!.mkdirs(); output.writeText(findings.joinToString("\n"))
        val hard = findings.filter { row -> listOf("HEADER_TOO_TALL", "UNDER_INSET", "SHEET_GEOMETRY").any { "\t$it\t" in row } }
        assertEquals("L0 hard findings:\n${hard.joinToString("\n")}", emptyList<String>(), hard)
        println("LANDSCAPE AUDIT ${identity.id} ${language.code} ${width}x$height $scale: ${findings.size} advisory, ${hard.size} hard findings")
    }
    @Test fun rosaryEssentialsRespectInsetsAndStayVisible() {
        landscapeViewport(compose, compose.activity, width, height)
        val resources = LandscapeRealAppFixture(ApplicationProvider.getApplicationContext<Application>(), language).also { fixture = it }
        compose.setContent { resources.Content(AppDestination.ROSARY, identity, scale) }
        val variants = mutableListOf<Pair<String?, String?>>(null to null)
        if (identity == DesignLanguage.APPLE && language == AppLanguage.ES && scale == 1f)
            variants += listOf("left" to null, "right" to null, null to "right", null to "left")
        for ((cutout, navbar) in variants) {
            compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, height, cutout = cutout, navbar = navbar) }; compose.waitForIdle()
            sample(AppDestination.ROSARY, cutout, navbar, "rosary")
            compose.onNodeWithText(if (language == AppLanguage.ES) "Coronilla" else "Coroinha").performClick()
            sample(AppDestination.ROSARY, cutout, navbar, "chaplet")
            compose.onNodeWithText(if (language == AppLanguage.ES) "Rosario" else "Terço").performClick()
        }
        val output = File("build/audit/l1_${identity.id.lowercase()}_${language.code}_${width}x${height}_${(scale * 100).toInt()}.tsv")
        output.parentFile!!.mkdirs(); output.writeText(findings.joinToString("\n"))
        assertEquals("L1 findings:\n${findings.joinToString("\n")}", emptyList<String>(), findings.toList())
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-{1}-{2}x{3}-{4}")
        fun matrix(): List<Array<Any>> = listOf(DesignLanguage.APPLE, DesignLanguage.CLASSIC, DesignLanguage.GLASS, DesignLanguage.SANCTUARY).flatMap { identity ->
            listOf(AppLanguage.ES, AppLanguage.PT).flatMap { language -> listOf(891 to 411, 800 to 360).flatMap { (w, h) -> listOf(1f, 1.3f).map { arrayOf<Any>(identity, language, w, h, it) } } }
        }
    }
}
