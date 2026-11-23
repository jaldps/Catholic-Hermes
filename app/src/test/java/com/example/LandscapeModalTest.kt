package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.components.GuiCustomizerModal
import com.example.ui.design.*
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppTextStyles
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class LandscapeModalTest(private val identity: DesignLanguage, private val width: Int, private val height: Int) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var backdrop: LandscapeRealAppFixture? = null
    private fun prepare() {
        landscapeViewport(compose, compose.activity, width, height)
        backdrop = LandscapeRealAppFixture(ApplicationProvider.getApplicationContext<Application>(), AppLanguage.ES)
    }
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle(); backdrop?.close()
    }
    private fun bounds(tag: String) = compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot.let { androidx.compose.ui.geometry.Rect(it.left / 2, it.top / 2, it.right / 2, it.bottom / 2) }
    private fun inset(ime: Boolean = false) { compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, height, ime) }; compose.waitForIdle() }
    private fun auditSheetVerticalGap(tag: String) {
        val nodes = compose.onAllNodes(hasAnyAncestor(hasTestTag(tag)), useUnmergedTree = true).fetchSemanticsNodes()
            .filter { it.boundsInRoot.height > 0 && it.config.contains(SemanticsActions.GetTextLayoutResult) }
        val heading = nodes.first { it.config.getOrNull(SemanticsProperties.Heading) != null }
        val next = nodes.filter { it.id != heading.id && it.boundsInRoot.top >= heading.boundsInRoot.bottom - 1f }.minByOrNull { it.boundsInRoot.top }
        if (next != null) {
            val gap = (next.boundsInRoot.top - heading.boundsInRoot.bottom) / 2f
            File("build/audit/sheet_gap_${identity.id}_${width}.json").apply {
                parentFile!!.mkdirs(); writeText("""{"identity":"${identity.id}","width":$width,"gap_dp":$gap}""")
            }
            assertFalse("VERTICAL_GAP in sheet: $gap dp", LandscapeAuditRules.verticalGap(0f, gap))
        }
    }
    private fun capture(name: String, ime: Boolean) {
        Thread.sleep(700)
        val tag = when (name) { "appearance" -> "gui_customizer_sheet"; "dialog" -> "modal_dialog"; else -> "modal_sheet" }
        val surface = bounds(tag)
        val scrollHeights = compose.onAllNodes(hasAnyAncestor(hasTestTag(tag)) and SemanticsMatcher("vertical viewport") {
            it.config.contains(SemanticsActions.ScrollBy) && it.config.contains(SemanticsProperties.VerticalScrollAxisRange)
        }, useUnmergedTree = true).fetchSemanticsNodes().map { it.size.height / 2f }.filter { it > 0f }
        File("build/audit/modal_${name}_${identity.id}_${width}${if (ime) "_ime" else ""}.json").apply {
            parentFile!!.mkdirs()
            writeText("""{"scene":"$name","identity":"${identity.id}","width":$width,"height":$height,"ime":$ime,"surface_width":${surface.width},"surface_height":${surface.height},"scroll_viewport_heights":${scrollHeights.joinToString(prefix="[",postfix="]")}}""")
        }
        landscapeCapture(compose, compose.activity, width, height, File("build/screenshots/landscape-modals/l0_${name}_${identity.id.lowercase()}_claro_${width}x${height}${if (ime) "_ime" else ""}.png"))
    }
    @Test fun realAppearanceIsLateralWithAndWithoutKeyboard() {
        prepare()
        val store = UserPreferencesStore(ApplicationProvider.getApplicationContext<Application>())
        val prefs = runBlocking { store.preferences.first() }.copy(designLanguage = identity.id, language = AppLanguage.ES, darkModeSetting = "LIGHT", fontSizeScale = 1f)
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT", reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true, LocalPreferencesStore provides store) {
                Box(Modifier.fillMaxSize()) {
                    backdrop!!.Content(com.example.ui.AppDestination.PRAYERS, identity)
                    GuiCustomizerModal(store, prefs, AppLanguage.ES) {}
                }
            }
        } }
        for (ime in listOf(false, true)) {
            inset(ime)
            val b = bounds("gui_customizer_sheet")
            assertEquals((width * .5f).coerceIn(360f, 480f), b.width, 1f)
            assertEquals(width - if (identity == DesignLanguage.SANCTUARY) 0f else 8f, b.right, 1f)
            if (ime) assertTrue(b.bottom <= height * .44f)
            compose.onNodeWithTag("sheet_close").assertIsDisplayed().assertHasClickAction()
            if (!ime) auditSheetVerticalGap("gui_customizer_sheet")
            capture("appearance", ime)
        }
    }
    @Test fun sheetFooterMovesToTheHeaderAndFocusedFieldIsAboveTheKeyboard() {
        prepare()
        var query by mutableStateOf("")
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT", reduceMotion = true) {
            Box(Modifier.fillMaxSize()) {
                backdrop!!.Content(com.example.ui.AppDestination.PRAYERS, identity)
                AppSheet({}, title = "Buscar oraciones", tag = "modal_sheet", footer = {
                    AppPrimaryButton("Guardar", {}, modifier = Modifier.width(140.dp).testTag("modal_action"))
                }) {
                    AppSearchField(query, { query = it }, "Buscar", Modifier.testTag("modal_field"))
                    showcasePrayers.forEach { Text(it.title.get(AppLanguage.ES), modifier = Modifier.padding(16.dp)) }
                }
            }
        } }
        inset()
        val actionBefore = bounds("modal_action")
        val expected = if (identity == DesignLanguage.SANCTUARY) androidx.compose.ui.geometry.Rect(width * .5f, 0f, width.toFloat(), height.toFloat())
            else androidx.compose.ui.geometry.Rect(width * .5f - 8f, 32f, width - 8f, height - 24f)
        assertFalse("SHEET_GEOMETRY: ${bounds("modal_sheet")} vs $expected", LandscapeAuditRules.wrongSheet(bounds("modal_sheet"), expected))
        compose.onNode(hasSetTextAction()).performTextInput("Padre")
        inset(true)
        compose.onNodeWithTag("modal_field").performScrollTo()
        compose.onNodeWithTag("modal_action").assertIsDisplayed().assertHasClickAction()
        assertTrue(bounds("modal_action").top < actionBefore.top)
        assertFalse("IME_HIDDEN", LandscapeAuditRules.hiddenByIme(bounds("modal_field"), height.toFloat(), height * .56f))
        assertFalse("IME_HIDDEN action", LandscapeAuditRules.hiddenByIme(bounds("modal_action"), height.toFloat(), height * .56f))
        val label = compose.onNodeWithText("Guardar", useUnmergedTree = true).fetchSemanticsNode()
        val lines = mutableListOf<androidx.compose.ui.text.TextLayoutResult>()
        compose.runOnIdle { label.config[SemanticsActions.GetTextLayoutResult].action!!.invoke(lines) }
        assertEquals("The header action must retain its complete label", 1, lines.single().lineCount)
        assertEquals("Guardar".length, lines.single().getLineEnd(0, visibleEnd = true))
        assertTrue(bounds("modal_sheet").right - bounds("modal_action").center.x <= 120f)
        compose.onNodeWithTag("sheet_close").assertIsNotDisplayed()
        capture("sheet_footer", true)
    }
    @Test fun twoColumnDialogKeepsTheActionsVisibleWhenTheBodyHasToScroll() {
        prepare()
        compose.setContent { SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = "LIGHT", reduceMotion = true) {
            Box(Modifier.fillMaxSize()) {
                backdrop!!.Content(com.example.ui.AppDestination.PRAYERS, identity)
                AppDialog({}, title = "Oraciones", tag = "modal_dialog", aside = {
                    AppHeroImage("heroes/lumen/prayers_lily.jpg", Modifier.fillMaxWidth().height(100.dp), fadeToCanvas = false, topVeil = false)
                    Text(showcasePrayers.first().title.get(AppLanguage.ES), style = LocalAppTextStyles.current.title2)
                }, footer = { Spacer(Modifier.weight(1f)); AppPrimaryButton("Continuar", {}, modifier = Modifier.width(180.dp).testTag("modal_action")) }) {
                    showcasePrayers.forEach { Text(it.body(AppLanguage.ES), style = LocalAppTextStyles.current.reading, modifier = Modifier.padding(20.dp)) }
                }
            }
        } }
        for (ime in listOf(false, true)) {
            inset(ime)
            val b = bounds("modal_dialog")
            assertEquals(720f, b.width, 1f)
            assertEquals(width / 2f, b.center.x, 1f)
            assertTrue(b.height <= height - 24f - (if (ime) height * .56f else 16f) - 32f + 1f)
            compose.onNodeWithTag("modal_action").assertIsDisplayed().assertHasClickAction()
            assertTrue(bounds("modal_action").bottom <= height - if (ime) height * .56f else 16f)
            capture("dialog", ime)
        }
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-{1}x{2}")
        fun matrix(): List<Array<Any>> = listOf(DesignLanguage.APPLE, DesignLanguage.SANCTUARY).flatMap { identity -> listOf(891 to 411, 800 to 360).map { (w, h) -> arrayOf<Any>(identity, w, h) } }
    }
}
