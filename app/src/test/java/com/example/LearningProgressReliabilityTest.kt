package com.example

import android.app.Application
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.core.app.ApplicationProvider
import com.example.data.prefs.UserPreferencesStore
import com.example.data.persistence.PersonalDataCommands
import com.example.ui.screens.LEARNING_TRACKS
import com.example.ui.screens.trackResumeIndex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class LearningProgressReliabilityTest {
    @Test fun outOfOrderReadingCreditsOnlyVisitedQuestionsAndRemembersLatest() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val file = File.createTempFile("learning", ".preferences_pb", app.cacheDir).apply { delete() }
        val data = PreferenceDataStoreFactory.create(scope = scope, produceFile = { file })
        try {
            val store = UserPreferencesStore(app, data)
            store.recordTrackProgress(30)
            assertEquals(1, LEARNING_TRACKS[0].completedCount(store.visitedQuestions.first()))
            store.recordTrackProgress(2)
            store.recordTrackProgress(30)
            assertEquals(setOf(2,30), store.visitedQuestions.first())
            assertEquals(30, store.lastReadQuestion.first())
            assertEquals(0, trackResumeIndex(LEARNING_TRACKS[0], store.visitedQuestions.first()))
            store.recordTrackProgress(101)
            assertEquals(1, LEARNING_TRACKS[2].completedCount(store.visitedQuestions.first()))
            assertEquals(101, store.lastReadQuestion.first())
        } finally { scope.cancel() }
    }

    @Test fun legacyHighWaterMarkDoesNotInventCompletedQuestions() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val file = File.createTempFile("legacy-learning", ".preferences_pb", app.cacheDir).apply { delete() }
        val data = PreferenceDataStoreFactory.create(scope = scope, produceFile = { file })
        try {
            data.edit { it[stringPreferencesKey("track_progress")] = "0:30,1:50" }
            val store = UserPreferencesStore(app, data)
            assertEquals(setOf(30,50),store.visitedQuestions.first())
            store.recordTrackProgress(1)
            assertEquals(setOf(1,30,50),store.visitedQuestions.first())
            assertEquals(2, LEARNING_TRACKS[0].completedCount(store.visitedQuestions.first()))
        } finally { scope.cancel() }
    }

    @Test fun itinerariesCoverEveryQuestionExactlyOnce() {
        val numbers = LEARNING_TRACKS.flatMap { (it.startQuestion..it.endQuestion).toList() }
        assertEquals((1..101).toList(), numbers)
    }

    @Test fun writeFailureIsVisibleAndDoesNotStopFollowingWrites() = runBlocking {
        val accepted = mutableListOf<Int>()
        PersonalDataCommands.submit("failure-test") { throw IOException("injected") }
        for (i in 1..12) PersonalDataCommands.submit("ordered-test") { accepted += i }
        PersonalDataCommands.awaitIdle()
        assertEquals((1..12).toList(),accepted)
        assertEquals(PersonalDataCommands.Status.ERROR,PersonalDataCommands.states.value["failure-test"]?.status)
        assertEquals(PersonalDataCommands.Status.SAVED,PersonalDataCommands.states.value["ordered-test"]?.status)
    }
}
