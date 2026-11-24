package com.example.screenshots

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.LandscapeInsetsHarness
import com.example.LandscapeRealAppFixture
import com.example.chapletFileCatalog
import com.example.data.models.AppLanguage
import com.example.data.repository.ChapletCatalog
import com.example.landscapeCapture
import com.example.landscapeViewport
import com.example.ui.AppDestination
import com.example.ui.screens.ChapletLaunch
import com.example.ui.screens.ChapletPlan
import com.example.ui.screens.ChapletVariablesMemory
import com.example.ui.theme.DesignLanguage
import org.junit.After
import org.junit.Assume
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * T-057 captures: the catalogue (with «Para hoy»), four chaplets mid-prayer on different strings and the
 * questions sheet. Opt in with `-Pshots.only=chaplets`; narrow with shots.identity / mode / width / height /
 * zone (scene list) / scale / lang. Output: app/build/screenshots/chaplets.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class ChapletShotsTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var fixture: LandscapeRealAppFixture? = null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle(); fixture?.close()
        ChapletCatalog.override = null; ChapletLaunch.isoTodayOverride = null; ChapletVariablesMemory.clear()
    }

    private val scenes = mapOf(
        "catalog" to null, "vars" to "coronilla-difuntos-45", "siete" to "siete-dolores-corona", "anne" to "santa-ana-coronilla",
        "lagrimas" to "coronilla-lagrimas", "jose" to "coronilla-san-jose-hozana"
    )

    @Test fun capture() {
        Assume.assumeTrue(System.getProperty("shots.only")?.contains("chaplets") == true)
        val lang = AppLanguage.fromCode(System.getProperty("shots.lang") ?: "es")
        val scale = System.getProperty("shots.scale")?.toFloat() ?: 1f
        val wanted = System.getProperty("shots.zone")?.split(',')?.toSet()
        val sizes = System.getProperty("shots.width")?.toInt()?.let { listOf(it to System.getProperty("shots.height").toInt()) }
            ?: listOf(411 to 891, 891 to 411, 800 to 360)
        val context = ApplicationProvider.getApplicationContext<Application>()
        ChapletCatalog.override = chapletFileCatalog
        fixture = LandscapeRealAppFixture(context, lang)
        for ((w, h) in sizes) for ((scene, id) in scenes) {
            if (wanted != null && scene !in wanted) continue
            compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
            landscapeViewport(compose, compose.activity, w, h)
            context.getSharedPreferences("unfinished_prayer_session", 0).edit().clear().commit()
            ChapletVariablesMemory.clear()
            ChapletLaunch.isoTodayOverride = if (scene == "catalog") 7 else null
            compose.runOnUiThread { compose.activity.setContent { fixture!!.Content(AppDestination.ROSARY, identity, scale, dark) } }
            compose.waitForIdle()
            compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, h) }; compose.waitForIdle()
            compose.onNodeWithTag("other_chaplets_segment").performClick(); compose.waitForIdle()
            if (id != null) {
                compose.onNodeWithTag("chaplet_row_$id").performScrollTo().performClick(); compose.waitForIdle()
                if (scene == "vars") {
                    // Leave the sheet open on the questions, with a name typed and a gender picked.
                    compose.onNodeWithTag("chaplet_answer_name").performTextInput(if (lang == AppLanguage.EN) "Mary Ellen" else "María Elena")
                    compose.onNodeWithText(if (lang == AppLanguage.EN) "Her" else if (lang == AppLanguage.PT) "Ela" else "Ella").performClick()
                } else {
                    if (compose.onAllNodesWithTag("chaplet_variables_sheet").fetchSemanticsNodes().isNotEmpty()) {
                        compose.onNodeWithTag("chaplet_variables_confirm").performClick(); compose.waitForIdle()
                    }
                    val plan = ChapletPlan(chapletFileCatalog.get(id)!!)
                    val target = plan.steps.indexOfFirst { it.section == minOf(2, plan.sectionStarts.size - 1) && it.repetition >= 2 }
                        .takeIf { it > 0 } ?: (plan.stepCount / 3)
                    val next = "next_chaplet_step_button"
                    repeat(target) { compose.onNodeWithTag(next).performClick() }
                }
            }
            compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, h) }; compose.waitForIdle()
            Thread.sleep(1800); compose.waitForIdle()
            val suffix = (if (scale == 1f) "" else "_s130") + (if (lang == AppLanguage.PT) "_pt" else if (lang == AppLanguage.EN) "_en" else "")
            landscapeCapture(compose, compose.activity, w, h,
                File("build/screenshots/chaplets/t057_${scene}_${identity.id.lowercase()}_${if (dark) "oscuro" else "claro"}_${w}x$h$suffix.png"))
        }
    }

    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-dark{1}")
        fun cases() = DesignLanguage.selectable
            .filter { System.getProperty("shots.identity") == null || System.getProperty("shots.identity") == "all" || it.id == System.getProperty("shots.identity") }
            .flatMap { id -> listOf(false, true).filter { System.getProperty("shots.mode") == null || System.getProperty("shots.mode") == if (it) "dark" else "light" }.map { arrayOf<Any>(id, it) } }
    }
}
