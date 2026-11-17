package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.OnboardingCatalog
import com.example.ui.MainApp
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** The welcome profile personalizes Home («Para ti hoy») and can be changed again from Settings → «Tu camino». */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class WelcomePersonalizationTest(private val identity: DesignLanguage, private val mode: String) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null
    private var contentRepository: CatholicDataRepository? = null
    private val context get() = ApplicationProvider.getApplicationContext<Application>()
    private val profiles = requireNotNull(OnboardingCatalog { javaClass.classLoader!!.getResourceAsStream(OnboardingCatalog.FILE)!! }.load())

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        narration?.release(); contentRepository?.close(); database?.close()
    }

    private fun journeyPrefs() = context.getSharedPreferences("beginner_journey", 0)

    private fun start(welcomedAs: Pair<Int, Int>? = null) {
        check(journeyPrefs().edit().clear().commit())
        welcomedAs?.let { (profile, minutes) -> check(journeyPrefs().edit().putBoolean("welcomed", true).putInt("profile", profile).putInt("minutes", minutes).commit()) }
        val repository = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES) }.also { contentRepository = it }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val preferences = UserPreferences(
            language = AppLanguage.ES, designLanguage = identity.id, themePreset = UserPreferences.DEFAULT_THEME_PRESET, darkModeSetting = mode,
            fontFamilyChoice = UserPreferences.DEFAULT_FONT_FAMILY, fontSizeScale = 1f, audioNarrationSpeed = 1f, activeBibleTranslationId = "bundled",
            isFullCccDownloaded = false, hasChosenLanguage = true, reduceMotion = true
        )
        val store = UserPreferencesStore(context)
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = mode, reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repository, db.dao(), tts, store, preferences) }
            }
        }
    }

    private fun waitForTag(tag: String) = compose.waitUntil(20_000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty() }

    private fun scrollHomeTo(tag: String) {
        compose.waitUntil(20_000) { runCatching { compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag(tag)) }.isSuccess }
    }

    private fun top(tag: String) = compose.onNodeWithTag(tag).getUnclippedBoundsInRoot().top

    @Test fun welcomeReturningTenMinutesFillsForYouInOrderAndATapNavigates() {
        start()
        compose.onAllNodesWithTag("home_for_you").assertCountEquals(0)
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("today_more_toggle"))
        compose.onNodeWithTag("today_more_toggle").performClick()
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("welcome_personalize"))
        compose.onNodeWithTag("welcome_personalize").performClick()
        waitForTag("welcome_dialog")
        // The profile line appears once a profile is picked, and it is the reviewed text of that profile.
        compose.onAllNodesWithTag("welcome_profile_line").assertCountEquals(0)
        compose.onNodeWithTag("welcome_profile_1").performClick().assertIsSelected()
        waitForTag("welcome_profile_line")
        compose.onNodeWithTag("welcome_profile_line").assertTextEquals(profiles.single { it.index == 1 }.welcome.es)
        compose.onNodeWithTag("welcome_profile_2").performClick()
        compose.onNodeWithTag("welcome_profile_line").assertTextEquals(profiles.single { it.index == 2 }.welcome.es)
        compose.onNodeWithTag("welcome_profile_1").performClick()
        compose.onNodeWithTag("welcome_minutes_10").performClick()
        compose.onNodeWithTag("welcome_language").assertExists()
        compose.onNodeWithTag("welcome_begin").performClick()
        compose.waitUntil(10_000) { compose.onAllNodesWithTag("welcome_dialog").fetchSemanticsNodes().isEmpty() }
        assertEquals(1, journeyPrefs().getInt("profile", -1)); assertEquals(10, journeyPrefs().getInt("minutes", -1))

        scrollHomeTo("for_you_all")
        compose.onNodeWithTag("home_for_you").assertIsDisplayed()
        // Ten minutes first, then the five-minute ones; the pointer to Home itself and the shortest two stay out of the three.
        val order = listOf("returning_saint", "returning_confession", "returning_rosary")
        val tops = order.map { top("for_you_$it") }
        assertEquals(tops.sorted(), tops)
        assertTrue(top("for_you_all") > tops.last())
        val saint = profiles.single { it.index == 1 }.suggestions.single { it.id == "returning_saint" }
        compose.onNode(hasText(saint.title.es) and hasAnyAncestor(hasTestTag("for_you_returning_saint")), useUnmergedTree = true).assertExists()
        compose.onNode(hasText(saint.why.es) and hasAnyAncestor(hasTestTag("for_you_returning_saint")), useUnmergedTree = true).assertExists()
        listOf("returning_home", "returning_gospel", "returning_our_father").forEach { compose.onAllNodesWithTag("for_you_$it").assertCountEquals(0) }

        compose.onNodeWithTag("for_you_returning_confession").performClick()
        waitForTag("confession_screen")
        compose.onNodeWithTag("confession_screen").assertIsDisplayed()
    }

    @Test fun settingsChangesTheProfileHomeUpdatesAndSeeAllOpensTheWholeList() {
        start(welcomedAs = 1 to 10)
        scrollHomeTo("for_you_returning_saint")
        compose.onNodeWithTag("for_you_returning_saint").assertIsDisplayed()

        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("settings_welcome_profile"))
        compose.onNode(hasText("Regresando") and hasAnyAncestor(hasTestTag("settings_welcome_profile")), useUnmergedTree = true).assertExists()
        compose.onNodeWithTag("settings_welcome_profile").performScrollTo().performClick()
        waitForTag("welcome_profile_sheet")
        compose.onNodeWithTag("welcome_profile_1").assertIsSelected()
        compose.onNodeWithTag("welcome_minutes_10").assertExists()
        compose.onNodeWithTag("welcome_profile_2").performClick().assertIsSelected()
        compose.onNodeWithTag("welcome_minutes_5").performClick()
        compose.onNodeWithTag("welcome_profile_line").assertTextEquals(profiles.single { it.index == 2 }.welcome.es)
        // Saved at once, before the sheet closes.
        assertEquals(2, journeyPrefs().getInt("profile", -1)); assertEquals(5, journeyPrefs().getInt("minutes", -1))
        compose.onNodeWithTag("welcome_profile_done").performClick()
        compose.waitUntil(10_000) { compose.onAllNodesWithTag("welcome_profile_sheet").fetchSemanticsNodes().isEmpty() }
        compose.onNode(hasText("Practicando") and hasAnyAncestor(hasTestTag("settings_welcome_profile")), useUnmergedTree = true).assertExists()

        compose.onNodeWithTag("nav_home").performClick()
        scrollHomeTo("for_you_all")
        // Practicing with five minutes: the three five-minute suggestions lead.
        val tops = listOf("practicing_rosary", "practicing_catechism", "practicing_habits").map { top("for_you_$it") }
        assertEquals(tops.sorted(), tops)
        listOf("returning_saint", "practicing_gospel", "practicing_saints").forEach { compose.onAllNodesWithTag("for_you_$it").assertCountEquals(0) }

        compose.onNodeWithTag("for_you_all").performClick()
        waitForTag("for_you_sheet")
        val practicing = profiles.single { it.index == 2 }
        practicing.suggestions.forEach { compose.onNodeWithTag("for_you_sheet_${it.id}").assertExists() }
        // «Vida espiritual» is a section of «Mi camino» now.
        compose.onNodeWithTag("for_you_sheet_practicing_spiritual").performScrollTo().performClick()
        waitForTag("spiritual_confession_entry")
        compose.onAllNodesWithTag("for_you_sheet").assertCountEquals(0)
    }

    @Test fun theDailyGospelSuggestionOpensTheDailyLiturgy() {
        start(welcomedAs = 2 to 2)
        scrollHomeTo("for_you_practicing_gospel")
        compose.onNodeWithTag("for_you_practicing_gospel").performClick()
        waitForTag("daily_missal_screen")
    }

    @Test fun anUnwelcomedReaderSeesNoForYouAndSkipStillPersonalizesWithTheDefaults() {
        start()
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("today_more_toggle"))
        compose.onNodeWithTag("today_more_toggle").performClick()
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("welcome_skip"))
        compose.onNodeWithTag("welcome_skip").performClick()
        scrollHomeTo("for_you_all")
        // «Ahora no»: exploring, five minutes.
        compose.onNodeWithTag("for_you_exploring_rosary").assertExists()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-{1}")
        fun cases(): List<Array<Any>> = listOf(arrayOf<Any>(DesignLanguage.APPLE, "LIGHT"), arrayOf<Any>(DesignLanguage.SANCTUARY, "DARK"),
            arrayOf<Any>(DesignLanguage.GLASS, "LIGHT"), arrayOf<Any>(DesignLanguage.CLASSIC, "DARK"))
    }
}
