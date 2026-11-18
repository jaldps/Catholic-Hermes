package com.example

import android.app.Application
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.speech.tts.TextToSpeech
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.audio.NarrationService
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowTextToSpeech
import java.time.Duration
import java.util.Locale

/** Audio focus, foreground service and sleep timer of spoken narration. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class NarrationPlaybackTest {
    private val app = ApplicationProvider.getApplicationContext<Application>()
    private lateinit var manager: LiturgicalTtsManager
    private val audio get() = shadowOf(app.getSystemService(Context.AUDIO_SERVICE) as AudioManager)

    @Before fun setUp() {
        ShadowTextToSpeech.addLanguageAvailability(Locale("en", "US"))
        manager = LiturgicalTtsManager(app)
        manager.setLanguage(AppLanguage.EN)
        manager.onInit(TextToSpeech.SUCCESS)
    }
    @After fun close() { manager.release() }

    @Test fun focusIsRequestedOnSpeakAndAbandonedOnStop() {
        assertNull(audio.lastAudioFocusRequest)
        manager.speakSingle("Our Father")
        assertNotNull(audio.lastAudioFocusRequest)
        assertEquals(AudioManager.AUDIOFOCUS_GAIN, audio.lastAudioFocusRequest.audioFocusRequest.focusGain)
        assertNull(audio.lastAbandonedAudioFocusRequest)
        manager.stop()
        assertNotNull(audio.lastAbandonedAudioFocusRequest)
    }

    @Test fun focusLossStopsNarrationAndContinueSpeaksTheSamePartAgain() {
        manager.speakSingle("Hail Mary")
        assertTrue(manager.isPlaying.value)
        manager.handleFocusChange(AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
        assertFalse(manager.isPlaying.value)
        assertTrue(manager.interrupted.value)
        manager.resume()
        assertTrue(manager.isPlaying.value)
        assertFalse(manager.interrupted.value)
        assertEquals("Hail Mary", shadowOf(ShadowTextToSpeech.getLastTextToSpeechInstance()).lastSpokenText)
        manager.handleFocusChange(AudioManager.AUDIOFOCUS_LOSS)
        assertFalse(manager.isPlaying.value)
        assertNotNull(audio.lastAbandonedAudioFocusRequest)
    }

    @Test fun becomingNoisyStopsNarration() {
        manager.speakSingle("Glory Be")
        assertTrue(manager.isPlaying.value)
        app.sendBroadcast(Intent(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
        shadowOf(android.os.Looper.getMainLooper()).idle()
        assertFalse(manager.isPlaying.value)
        assertTrue(manager.interrupted.value)
    }

    @Test fun serviceStartsWithRecitationAndStopsAfterStop() {
        manager.speakDialogue("Hail Mary", "Holy Mary")
        val started = shadowOf(app).nextStartedService
        assertNotNull(started)
        assertEquals(NarrationService::class.java.name, started.component?.className)
        manager.stop()
        val stopped = shadowOf(app).nextStoppedService
        assertEquals(NarrationService::class.java.name, stopped?.component?.className)
    }

    @Test fun serviceShowsMediaNotificationInForeground() {
        manager.speakSingle("The first joyful mystery", title = "Rosary")
        val controller = Robolectric.buildService(NarrationService::class.java, Intent(app, NarrationService::class.java).setAction(NarrationService.ACTION_UPDATE)).create()
        controller.startCommand(0, 1)
        val service = controller.get()
        val n = shadowOf(service).lastForegroundNotification
        assertNotNull(n)
        assertEquals(2, n.actions.size)
        assertEquals("Rosary", n.extras.getCharSequence(android.app.Notification.EXTRA_TITLE).toString())
        controller.destroy()
    }

    @Test fun titleNamesTheContentAndDoesNotOutliveIt() {
        manager.speakSingle("Hail Mary", title = "Hail Mary")
        assertEquals("Hail Mary", manager.narrationTitle)
        // The next recitation without a title must not inherit the previous one.
        manager.speakDialogue("Leader", "Response")
        assertNull(manager.narrationTitle)
        manager.speakDialogue("Our Father", "Amen", "Our Father")
        assertEquals("Our Father", manager.narrationTitle)
        manager.stop()
        assertNull(manager.narrationTitle)
    }

    @Test fun focusLossKeepsTitleAndMarksInterruptedForTheNotification() {
        manager.speakSingle("Glory Be", title = "Glory Be")
        manager.handleFocusChange(AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
        assertFalse(manager.isPlaying.value)
        assertTrue(manager.interrupted.value)
        // The notification stays, now offering «Continue», with the same title.
        val state = manager.narrationState()
        assertNotNull(state)
        assertEquals("Glory Be", state!!.title)
        assertTrue(state.paused)
        manager.pauseNarration() // already paused: no-op, still interrupted
        assertTrue(manager.interrupted.value)
        manager.resume()
        assertFalse(manager.interrupted.value)
        assertEquals("Glory Be", manager.narrationTitle)
    }

    @Test fun pauseFromNotificationOrLockScreenIsAnInterruption() {
        manager.speakSingle("Creed", title = "Creed")
        manager.pauseNarration()
        assertFalse(manager.isPlaying.value)
        assertTrue(manager.interrupted.value)
        // Stop from the lock screen ends everything and stops the service.
        manager.stop()
        assertFalse(manager.interrupted.value)
        assertTrue(app.narrationServiceStopped())
    }

    @Test fun lastNarratingManagerOwnsTheNotificationControls() {
        val other = LiturgicalTtsManager(app)
        try {
            other.onInit(TextToSpeech.SUCCESS)
            manager.speakSingle("Hail Mary")
            assertSame(manager, LiturgicalTtsManager.active)
        } finally { other.release() }
        // Releasing a manager that was not narrating must not detach the one that is.
        assertSame(manager, LiturgicalTtsManager.active)
    }

    @Test fun sleepTimerPersistsAndStopsNarration() = runBlocking {
        val store = UserPreferencesStore(app)
        store.setNarrationSleepMinutes(30)
        assertEquals(30, store.preferences.first().narrationSleepMinutes)
        store.setNarrationSleepMinutes(7) // not an offered value: falls back to off
        assertEquals(0, store.preferences.first().narrationSleepMinutes)

        store.setNarrationSleepMinutes(15)
        // The manager follows the saved preference.
        val deadline = System.currentTimeMillis() + 10_000
        while (manager.sleepTimerMinutes != 15 && System.currentTimeMillis() < deadline) Thread.sleep(50)
        assertEquals(15, manager.sleepTimerMinutes)
        // The test engine finishes each part at once, so keep reciting like a
        // queue would: one part a minute, for 14 minutes.
        val looper = shadowOf(android.os.Looper.getMainLooper())
        for (minute in 1..14) {
            manager.speakSingle("Litany $minute")
            looper.idleFor(Duration.ofMinutes(1))
        }
        assertEquals(0, manager.stopCount.value)
        manager.speakSingle("Litany 15")
        assertTrue(manager.isPlaying.value || manager.stopCount.value == 0)
        looper.idleFor(Duration.ofMinutes(2))
        assertEquals(1, manager.stopCount.value)
        assertFalse(manager.isPlaying.value)
        assertNotNull(audio.lastAbandonedAudioFocusRequest)
    }
}
