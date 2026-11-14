package com.example

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.audio.VoiceAvailability
import com.example.data.models.AppLanguage
import com.example.ui.screens.SpeechQueue
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowTextToSpeech
import java.util.Locale

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class NarrationAvailabilityTest {
    private val manager = LiturgicalTtsManager(ApplicationProvider.getApplicationContext<Application>())
    @After fun close() { manager.release() }

    @Test fun missingLanguageCannotPretendToBePlayingAndCanRecoverWithoutAutoPlay() {
        manager.setLanguage(AppLanguage.PT)
        manager.onInit(TextToSpeech.SUCCESS)
        assertFalse(manager.isReady.value)
        val queue = SpeechQueue(manager)
        queue.play("test", listOf("Uma oração"))
        assertFalse(queue.active)
        assertNotNull(manager.playbackIssue.value)
        assertFalse(manager.isPlaying.value)
        ShadowTextToSpeech.addLanguageAvailability(Locale("pt", "BR"))
        manager.refreshAvailability()
        assertTrue(manager.isReady.value)
        assertFalse(manager.isPlaying.value)
        manager.dismissPlaybackIssue()
        assertNull(manager.playbackIssue.value)
    }

    @Test fun initializationFailureIsReportedOnlyAfterListeningIsRequested() {
        manager.onInit(TextToSpeech.ERROR)
        assertNull(manager.playbackIssue.value)
        manager.speakSingle("A prayer")
        assertEquals(VoiceAvailability.ENGINE_ERROR, manager.playbackIssue.value)
        assertFalse(manager.isPlaying.value)
    }

    @Test fun requestedLanguageBeforeInitializationIsActuallyApplied() {
        ShadowTextToSpeech.addLanguageAvailability(Locale("en", "US"))
        manager.setLanguage(AppLanguage.EN)
        assertFalse(manager.isReady.value)
        manager.onInit(TextToSpeech.SUCCESS)
        assertTrue(manager.isReady.value)
        assertEquals(VoiceAvailability.READY, manager.availability.value)
        manager.speakSingle("Our Father")
        assertEquals("Our Father", org.robolectric.Shadows.shadowOf(ShadowTextToSpeech.getLastTextToSpeechInstance()).lastSpokenText)
    }
}
