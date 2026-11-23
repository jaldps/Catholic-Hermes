package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.*
import com.example.data.repository.CatholicDataRepository
import com.example.ui.AppDestination
import com.example.ui.screens.*
import com.example.ui.theme.DesignLanguage
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class RosaryLandscapeScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var fixture: LandscapeRealAppFixture? = null
    @After fun close() { compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle(); fixture?.close() }
    @Test fun routinePrayerAndRequestedState() {
        val lang = AppLanguage.fromCode(System.getProperty("shots.lang") ?: "es")
        val scale = System.getProperty("shots.scale")?.toFloat() ?: 1f
        val cut = System.getProperty("shots.cutout")
        val nav = System.getProperty("shots.navbar")
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repo = CatholicDataRepository(context)
        val source = repo.vaticanPrayers.first { it.id == "creed" }
        val creed = Said(source.leaderText.get(lang), source.responseText?.get(lang).orEmpty())
        val rosary = buildRosaryGuide(repo.getRosaryMystery(RosaryMysteryType.JOYFUL), creed, lang)
        val opening = repo.divineMercySteps.firstOrNull { it.stepIndex == 1 }
        val mercy = buildMercyGuide(creed, opening?.let { Said(it.leaderText.get(lang), it.responseText?.get(lang).orEmpty()) }, lang)
        val rIndex = rosary.steps.indexOfFirst { it.section == 3 && it.count == 10 && it.repetition == 3 }
        val mIndex = mercy.steps.indexOfFirst { it.section == 3 && it.count == 10 && it.repetition == 3 }
        val resource = LandscapeRealAppFixture(context, lang).also { fixture = it }
        val widths = System.getProperty("shots.width")?.toInt()?.let { listOf(it to System.getProperty("shots.height").toInt()) } ?: listOf(891 to 411, 800 to 360)
        for ((w,h) in widths) for (devotion in if (System.getProperty("shots.ime") in listOf("mysteries", "mystery")) 0..0 else 0..1) {
            compose.runOnUiThread { compose.activity.setContent {} }; compose.waitForIdle()
            landscapeViewport(compose, compose.activity, w, h)
            RosarySessionStore.save(context, RosarySession.opening(0).copy(rosaryStep = rIndex, mercyStep = mIndex,
                instructionsOn = System.getProperty("shots.ime") == "guide", mysterySelection = MysterySelection.Picked(RosaryMysteryType.JOYFUL)), RosaryMysteryType.JOYFUL)
            compose.runOnUiThread { compose.activity.setContent { resource.Content(AppDestination.ROSARY, identity, scale, dark, System.getProperty("shots.opaque") == "true") } }; compose.waitForIdle()
            compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, h, cutout = cut, navbar = nav) }; compose.waitForIdle()
            if (devotion == 1) compose.onNodeWithText(when(lang) { AppLanguage.ES -> "Coronilla"; AppLanguage.EN -> "Chaplet"; AppLanguage.PT -> "Coroinha" }).performClick()
            val state = System.getProperty("shots.ime")
            when (state) {
                "options" -> compose.onNodeWithTag("rosary_options_button").performClick()
                "mysteries" -> compose.onNodeWithTag("rosary_mystery_picker").performScrollTo().performClick()
                "mystery" -> compose.onNodeWithTag("rosary_view_mystery").performScrollTo().performClick()
            }
            compose.runOnUiThread { LandscapeInsetsHarness.dispatch(compose.activity, h, cutout = cut, navbar = nav) }; compose.waitForIdle()
            Thread.sleep(2500); compose.waitForIdle()
            val suffix = (if (scale == 1f) "" else "_s130") + (if (lang == AppLanguage.PT) "_pt" else "") +
                (cut?.let { "_cut${it.first().uppercase()}" } ?: "") + (nav?.let { "_nav${it.first().uppercase()}" } ?: "") +
                (if (System.getProperty("shots.opaque") == "true") "_opaque" else "") + (state?.let { "_$it" } ?: "")
            landscapeCapture(compose, compose.activity, w,h, File("build/screenshots/l1/l1_${if(devotion==0) "rosary" else "chaplet"}_${identity.id.lowercase()}_${if(dark) "oscuro" else "claro"}_${w}x$h$suffix.png"))
        }
        repo.close()
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-dark{1}")
        fun cases() = DesignLanguage.selectable.filter { System.getProperty("shots.identity") == null || System.getProperty("shots.identity") == "all" || it.id == System.getProperty("shots.identity") }
            .flatMap { id -> listOf(false,true).filter { System.getProperty("shots.mode") == null || System.getProperty("shots.mode") == if(it) "dark" else "light" }.map { arrayOf<Any>(id,it) } }
    }
}
