package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.unit.dp
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.fidelity.FidelityStore
import com.example.data.liturgy.ScriptureReference
import com.example.data.local.HabitCompletionLogEntity
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.BibleData
import com.example.data.repository.CatholicDataRepository
import com.example.data.verse.DailyVerseRotation
import com.example.ui.MainApp
import com.example.ui.screens.DailyRule
import com.example.ui.screens.Devotion
import com.example.ui.screens.RosarySession
import com.example.ui.screens.RosarySessionStore
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
import java.time.LocalDate

/** Real Home and Pray intents, including the exact daily Scripture target. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class HomePrayerPrioritiesTest(private val identity: DesignLanguage, private val mode: String) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase? = null
    private var narration: LiturgicalTtsManager? = null
    private var contentRepository: CatholicDataRepository? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle()
        narration?.release()
        contentRepository?.close()
        database?.close()
    }

    private fun start(seedPrayer: Boolean = false): CatholicDataRepository {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repository = CatholicDataRepository(context).apply { setLanguage(AppLanguage.ES) }.also { contentRepository = it }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java)
            .allowMainThreadQueries().build().also { database = it }
        runBlocking {
            DailyRule.habits.take(3).forEach {
                db.dao().insertCompletion(HabitCompletionLogEntity(habitId = it.id, completionDate = FidelityStore.dateKey(LocalDate.now())))
            }
        }
        context.getSharedPreferences("unfinished_prayer_session", Application.MODE_PRIVATE).edit().clear().commit()
        if (seedPrayer) RosarySessionStore.save(context, RosarySession(devotion = Devotion.MERCY, rosaryStep = 1, mercyStep = 1), repository.getTodayRosaryMystery().type)
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val preferences = UserPreferences(
            language = AppLanguage.ES, designLanguage = identity.id,
            themePreset = UserPreferences.DEFAULT_THEME_PRESET, darkModeSetting = mode,
            fontFamilyChoice = UserPreferences.DEFAULT_FONT_FAMILY, fontSizeScale = 1f,
            audioNarrationSpeed = 1f, activeBibleTranslationId = "bundled",
            isFullCccDownloaded = false, hasChosenLanguage = true, reduceMotion = true
        )
        val store = UserPreferencesStore(context)
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = mode, reduceMotion = true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    MainApp(repository, db.dao(), tts, store, preferences)
                }
            }
        }
        return repository
    }

    @Test fun prayerFollowsSeasonAndCounterPrecedesLiturgyWithExactVerseReader() {
        val repository = start()
        val season = compose.onNodeWithTag("liturgical_calendar_widget").getUnclippedBoundsInRoot()
        val prayer = compose.onNodeWithTag("home_prayer_moment_heading").getUnclippedBoundsInRoot()
        val practices = compose.onNodeWithTag("home_practices_heading").getUnclippedBoundsInRoot()
        assertTrue("The current prayer is directly after the liturgical season", prayer.top >= season.bottom && prayer.top - season.bottom < 72.dp)
        assertTrue("Practices follow the current prayer near the start of Home", practices.top > prayer.bottom)
        compose.onNodeWithTag("home_fidelity_count").assertTextEquals("3").assertIsDisplayed()

        // An earlier ordinary Read visit keeps its own canon selection. The
        // explicit Home action must still open Mass, with a tappable target
        // after the smallest scroll needed to reveal it above the dock.
        compose.onNodeWithTag("nav_bible").performClick()
        compose.onNodeWithTag("bible_canon_filter").performScrollTo().performClick()
        compose.onNodeWithTag("bible_canon_2").performClick()
        compose.onNodeWithTag("nav_home").performClick()
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("read_mass_full_button"))
        compose.onNodeWithTag("read_mass_full_button").performScrollTo()
        val action = compose.onNodeWithTag("read_mass_full_button").getUnclippedBoundsInRoot()
        val dock = compose.onNodeWithTag("nav_home").getUnclippedBoundsInRoot()
        assertTrue("The liturgy action must be above the dock after scroll-to", action.bottom <= dock.top)
        compose.onNodeWithText("Liturgia de hoy").assertIsDisplayed()
        compose.onNodeWithTag("read_mass_full_button").assertHasClickAction().performClick()
        compose.onNodeWithTag("daily_missal_screen").assertIsDisplayed()
        compose.onNodeWithTag("nav_bible").performClick()
        compose.onNodeWithTag("bible_canon_filter").performScrollTo().performClick()
        compose.onNodeWithTag("bible_canon_2").assertIsSelected().performClick()
        compose.onNodeWithTag("nav_home").performClick()

        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("today_more_toggle"))
        compose.onNodeWithTag("today_more_toggle").performClick()
        compose.onNodeWithTag("home_screen").performScrollToNode(hasTestTag("verse_of_the_day_card"))
        compose.onNodeWithTag("verse_of_the_day_card").performClick()
        val reference = requireNotNull(ScriptureReference.parse(DailyVerseRotation.referenceFor(LocalDate.now())))
        val book = BibleData.catholicCanonBooks.single { it.id == reference.bookId }
        compose.onNodeWithTag("bible_reader").assertIsDisplayed()
        compose.onNodeWithContentDescription("Elegir capítulo. ${book.name.get(AppLanguage.ES)}, capítulo ${reference.chapter} de", substring = true).assertIsDisplayed()
        compose.onNodeWithTag("verse_${reference.verseStart}").assertIsDisplayed()
        assertEquals("bundled", repository.activeBibleEditionId.value)
        // Inspect the actual verse's text, rather than merely arriving at the Bible tab.
        val expected = repository.getChapter(book.id, reference.chapter, AppLanguage.ES).verses.single { it.number == reference.verseStart }
        compose.onNode(hasTestTag("verse_${reference.verseStart}") and hasText(expected.text.get(AppLanguage.ES), substring = true)).assertExists()
        compose.onNodeWithTag("bible_active_edition").performScrollTo().assertTextEquals("Biblia Platense · Español")
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("home_screen").assertIsDisplayed()
    }

    @Test fun explicitGuidesOverrideLastDevotionAndKeepBothSavedPositions() {
        start(seedPrayer = true)
        val context = ApplicationProvider.getApplicationContext<Application>()
        compose.onNodeWithTag("nav_prayers").performClick()
        val rosary = compose.onNodeWithTag("nav_rosary").assertIsDisplayed().getUnclippedBoundsInRoot()
        val chaplet = compose.onNodeWithTag("open_chaplet_row").assertIsDisplayed().getUnclippedBoundsInRoot()
        val help = compose.onNodeWithTag("how_to_pray_toggle").getUnclippedBoundsInRoot()
        assertTrue("Both guides precede the secondary help entry", rosary.top < help.top && chaplet.top < help.top)
        compose.onNodeWithTag("nav_rosary").assertWidthIsAtLeast(48.dp).assertHeightIsAtLeast(48.dp).performClick()
        compose.onNodeWithTag("next_bead_button").assertIsDisplayed().performClick()
        compose.onNodeWithTag("rosary_hero_step_counter", useUnmergedTree = true).assertTextContains("Paso 3 de", substring = true)
        val afterRosary = compose.runOnIdle { requireNotNull(RosarySessionStore.load(context)) }
        assertEquals(Devotion.ROSARY, afterRosary.devotion)
        assertEquals(2, afterRosary.rosaryStep)
        assertEquals(1, afterRosary.mercyStep)

        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("open_chaplet_row").performScrollTo().performClick()
        compose.onNodeWithTag("next_mercy_step_button").assertIsDisplayed().performClick()
        compose.onNodeWithTag("mercy_hero_step_counter", useUnmergedTree = true).assertTextContains("Paso 3 de", substring = true)
        val afterMercy = compose.runOnIdle { requireNotNull(RosarySessionStore.load(context)) }
        assertEquals(Devotion.MERCY, afterMercy.devotion)
        assertEquals(2, afterMercy.mercyStep)
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("nav_rosary").performScrollTo().performClick()
        compose.onNodeWithTag("rosary_hero_step_counter", useUnmergedTree = true).assertTextContains("Paso 3 de", substring = true)
        val reopened = compose.runOnIdle { requireNotNull(RosarySessionStore.load(context)) }
        assertEquals(Devotion.ROSARY, reopened.devotion)
        assertEquals(2, reopened.rosaryStep)
        assertEquals("The newer chaplet position survives another explicit Rosary entry", 2, reopened.mercyStep)
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("how_to_pray_toggle").performScrollTo().performClick()
        compose.onNodeWithTag("how_to_pray_page").assertIsDisplayed()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}-{1}")
        fun cases(): List<Array<Any>> = listOf(DesignLanguage.APPLE, DesignLanguage.SANCTUARY).flatMap { identity ->
            listOf("LIGHT", "DARK").map { mode -> arrayOf<Any>(identity, mode) }
        }
    }
}
