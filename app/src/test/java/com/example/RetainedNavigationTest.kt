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
import com.example.data.liturgy.FoundationRef
import com.example.data.liturgy.parseFoundation
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Real cross-tab and contextual-reference journeys through the application scaffold. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class RetainedNavigationTest {
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

    private fun launch(): CatholicDataRepository {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val repository = CatholicDataRepository(context).apply { setLanguage(AppLanguage.EN) }.also { contentRepository = it }
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { database = it }
        val tts = LiturgicalTtsManager(context).also { narration = it }
        val store = UserPreferencesStore(context)
        val prefs = UserPreferences(AppLanguage.EN, "APPLE", "LITURGICAL_DYNAMIC", "LIGHT", "IDENTITY_DEFAULT", 1f, 1f, "bundled", false, true)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "APPLE", darkModeSetting = "LIGHT") {
                CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repository, db.dao(), tts, store, prefs) }
            }
        }
        return repository
    }

    @Test fun ordinaryPrayerReaderSurvivesReadTabRoundTrip() {
        launch()
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.onNodeWithTag("prayers_landing").performScrollToNode(hasTestTag("prayer_row_our_father"))
        compose.onNodeWithTag("prayer_row_our_father").performClick()
        compose.onNodeWithTag("prayer_detail_our_father").assertIsDisplayed()
        compose.onNodeWithTag("nav_bible").performClick()
        compose.onNodeWithTag("bible_screen").assertIsDisplayed()
        compose.onNodeWithTag("nav_prayers").performClick()
        compose.onNodeWithTag("prayer_detail_our_father").assertIsDisplayed()
    }

    @Test fun questionReferenceReturnsToSameExpandedExplanation() {
        val repository = launch()
        val question = repository.catholicQuestions.first { q -> parseFoundation(q.foundation.get(AppLanguage.EN), AppLanguage.EN, repository.catholicBooks).any { it is FoundationRef.BibleRef } }
        val ref = (parseFoundation(question.foundation.get(AppLanguage.EN), AppLanguage.EN, repository.catholicBooks).first { it is FoundationRef.BibleRef } as FoundationRef.BibleRef).reference
        val key = "bible_${ref.bookId}_${ref.chapter}_${ref.verseStart ?: "all"}_${ref.verseEnd ?: ""}"
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("more_screen").performScrollToNode(hasTestTag("nav_questions"))
        compose.onNodeWithTag("nav_questions").performClick()
        compose.onNodeWithTag("qa_search_input").performScrollTo().performTextInput(question.question.get(AppLanguage.EN))
        compose.onNodeWithTag("catholic_question_${question.number}").performScrollTo().performClick()
        compose.onNodeWithTag("doctrine_entry_page").performScrollToNode(hasTestTag("foundation_ref_$key"))
        compose.onNodeWithTag("foundation_ref_$key").performClick()
        compose.onNodeWithTag("doctrine_entry_page").performScrollToNode(hasTestTag("foundation_ref_open_$key"))
        compose.onNodeWithTag("foundation_ref_open_$key").performClick()
        compose.onNodeWithTag("bible_screen").assertIsDisplayed()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.onNodeWithTag("doctrine_entry_page").assertIsDisplayed()
        compose.onNodeWithTag("foundation_ref_open_$key").assertIsDisplayed()
    }
}
