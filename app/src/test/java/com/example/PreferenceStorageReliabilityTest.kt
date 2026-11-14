package com.example

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class PreferenceStorageReliabilityTest {
    internal class FaultingStore : DataStore<Preferences> {
        val stored = MutableStateFlow(emptyPreferences())
        var readFails = false
        var writeFails = false
        var entered: CompletableDeferred<Unit>? = null
        var release: CompletableDeferred<Unit>? = null
        override val data: Flow<Preferences> get() = flow {
            if (readFails) throw IOException("injected read failure")
            emitAll(stored)
        }
        override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences {
            entered?.complete(Unit)
            release?.await()
            if (writeFails) throw IOException("injected write failure")
            return transform(stored.value).also { stored.value = it }
        }
    }
    private val app = ApplicationProvider.getApplicationContext<Application>()
    @Test fun nestedPreferenceCommandDoesNotDeadlockAndPreservesTheActualQuestion() = runBlocking {
        val fake = FaultingStore()
        val store = UserPreferencesStore(app, fake)
        PersonalDataCommands.submit("nested-preference-test") { store.recordTrackProgress(56) }
        withTimeout(5_000) { PersonalDataCommands.awaitIdle() }
        assertEquals(setOf(56), store.visitedQuestions.first())
        assertEquals(56, store.lastReadQuestion.first())
    }
    @Test fun cancellingTheCallerDoesNotCancelAnAcceptedPreferenceWrite() = runBlocking {
        val fake = FaultingStore().apply { entered = CompletableDeferred(); release = CompletableDeferred() }
        val store = UserPreferencesStore(app, fake)
        val caller = launch { store.setLanguage(AppLanguage.PT) }
        withTimeout(5_000) { fake.entered!!.await() }
        caller.cancelAndJoin()
        fake.release!!.complete(Unit)
        withTimeout(5_000) { PersonalDataCommands.awaitIdle() }
        assertEquals(AppLanguage.PT, store.preferences.first().language)
    }
    @Test fun aFailedSettingRemainsRetryableWithoutAnUncaughtCallerException() = runBlocking {
        val fake = FaultingStore().apply { writeFails = true }
        val store = UserPreferencesStore(app, fake)
        store.setLanguage(AppLanguage.ES)
        PersonalDataCommands.awaitIdle()
        assertEquals(PersonalDataCommands.Status.ERROR, PersonalDataCommands.states.value["preferences:setLanguage"]?.status)
        fake.writeFails = false
        assertTrue(PersonalDataCommands.retry("preferences:setLanguage"))
        PersonalDataCommands.awaitIdle()
        assertEquals(AppLanguage.ES, store.preferences.first().language)
    }
    @Test fun aReadFailureKeepsKnownValuesAndReportsUnavailabilityUntilRetry() = runBlocking {
        val fake = FaultingStore()
        val store = UserPreferencesStore(app, fake)
        store.setLanguage(AppLanguage.PT)
        store.recordTrackProgress(72)
        assertEquals(AppLanguage.PT, store.preferences.first().language)
        fake.readFails = true
        store.retryRead()
        val unavailable = store.preferences.first()
        assertFalse(unavailable.storageAvailable)
        assertEquals(AppLanguage.PT, unavailable.language)
        assertEquals(setOf(72), store.visitedQuestions.first())
        assertEquals(72, store.lastReadQuestion.first())
        fake.readFails = false
        store.retryRead()
        assertTrue(store.preferences.first().storageAvailable)
        assertEquals(setOf(72), store.visitedQuestions.first())
    }
}
