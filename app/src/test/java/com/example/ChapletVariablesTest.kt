package com.example

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ChapletCatalog
import com.example.data.repository.ChapletPrayers
import com.example.data.repository.PrayerAndNovenaData
import com.example.data.repository.PrayerDetailsCatalog
import com.example.ui.screens.ChapletVariables
import com.example.ui.screens.ChapletVariablesMemory
import com.example.ui.screens.DeceasedGender
import com.example.ui.screens.RosaryScreen
import com.example.ui.screens.buildChapletGuide
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** The deceased's name, gender and intention replace their markers, and live in memory only. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class ChapletVariablesTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var voice: LiturgicalTtsManager? = null
    private val prayers = ChapletPrayers.of(PrayerDetailsCatalog.default.mergeInto(PrayerAndNovenaData.vaticanOfficialPrayers))

    @Before fun fresh() { ChapletCatalog.override = chapletFileCatalog; ChapletVariablesMemory.clear() }
    @After fun close() { ChapletCatalog.override = null; ChapletVariablesMemory.clear(); voice?.release() }

    @Test fun markersAreReplacedInEveryLanguage() {
        val v = ChapletVariables("Ana", DeceasedGender.FEMALE, "peace")
        assertEquals("Reza por Ana, ella", v.apply("Reza por {{nombre}}, {{el|ella}}", AppLanguage.ES))
        assertEquals("Pray for Ana, her", v.apply("Pray for {{nombre}}, {{him|her}}", AppLanguage.EN))
        assertEquals("Reza por Ana, ela", v.apply("Reza por {{nombre}}, {{ele|ela}}", AppLanguage.PT))
        assertEquals("Por peace", v.apply("Por {{intencion}}", AppLanguage.ES))
        assertEquals("él", ChapletVariables(deceasedGender = DeceasedGender.MALE).apply("{{él|ella}}", AppLanguage.ES))
        assertEquals("él/ella", ChapletVariables().apply("{{él|ella}}", AppLanguage.ES))
        assertEquals("N.", ChapletVariables().apply("{{nombre}}", AppLanguage.EN))
    }

    @Test fun theDeceasedChapletUsesTheNameAndNoMarkerSurvives() {
        val c = chapletFileCatalog.get("coronilla-difuntos-45")!!
        AppLanguage.entries.forEach { lang ->
            val g = buildChapletGuide(c, lang, prayers, ChapletVariables("Zacarías", DeceasedGender.MALE)).guide
            val text = g.steps.joinToString("\n") { it.leader + "\n" + it.response }
            assertTrue("$lang names the deceased", text.contains("Zacarías"))
            assertFalse("$lang leaves a marker", text.contains("{{"))
        }
        val blank = buildChapletGuide(c, AppLanguage.ES, prayers).guide.steps.joinToString { it.leader }
        assertFalse(blank.contains("{{"))
    }

    @Test fun answersAreAskedSubstitutedAndNeverWrittenToDisk() {
        val activity = compose.activity
        val repo = CatholicDataRepository(activity).apply { setLanguage(AppLanguage.ES) }
        val audio = LiturgicalTtsManager(activity).also { voice = it }
        compose.setContent { SanctaFidesTheme(reduceMotion = true) {
            CompositionLocalProvider(LocalInspectionMode provides true) { RosaryScreen(repo, audio, AppLanguage.ES, initialTab = 2) }
        } }
        compose.onNodeWithTag("chaplet_row_coronilla-difuntos-45").performScrollTo().performClick()
        compose.onNodeWithTag("chaplet_variables_sheet").assertExists()
        compose.onNodeWithTag("chaplet_answer_name").performTextInput("Zacarías Quimbaya")
        compose.onNodeWithText("Ella").performClick()
        compose.onNodeWithTag("chaplet_variables_confirm").performClick()
        compose.onNodeWithTag("chaplet_variables_sheet").assertDoesNotExist()
        var seen = false
        repeat(20) {
            if (compose.onAllNodesWithText("Zacarías Quimbaya", substring = true).fetchSemanticsNodes().isNotEmpty()) seen = true
            compose.onNodeWithTag("next_chaplet_step_button").performClick()
        }
        assertTrue("the name appears in the prayer", seen)
        compose.onAllNodesWithText("{{", substring = true).assertCountEquals(0)
        assertEquals("Zacarías Quimbaya", ChapletVariablesMemory.of("coronilla-difuntos-45")?.deceasedName)
        // Nothing of it reaches storage: the app's whole data directory is clean.
        val root = File(activity.applicationInfo.dataDir)
        val hits = root.walkTopDown().filter { it.isFile }.filter { f ->
            runCatching { String(f.readBytes(), Charsets.ISO_8859_1).let { "Zacar" in it || "Quimbaya" in it } }.getOrDefault(false)
        }.map { it.path }.toList()
        assertEquals(emptyList<String>(), hits)
    }
}
