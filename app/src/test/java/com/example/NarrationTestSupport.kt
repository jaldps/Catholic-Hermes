package com.example

import android.app.Application
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.audio.NarrationService
import com.example.data.models.AppLanguage
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowTextToSpeech
import java.util.Locale

/**
 * A narrator whose engine is ready in [language] and whose utterances never
 * finish on their own, so a Listen button stays in its «playing» state until
 * the test (or the user) stops it.
 */
internal fun readyNarrator(app: Application, language: AppLanguage): LiturgicalTtsManager {
    ShadowTextToSpeech.addLanguageAvailability(
        when (language) {
            AppLanguage.ES -> Locale("es", "ES")
            AppLanguage.EN -> Locale("en", "US")
            AppLanguage.PT -> Locale("pt", "BR")
        }
    )
    val manager = LiturgicalTtsManager(app)
    manager.setLanguage(language)
    manager.onInit(TextToSpeech.SUCCESS)
    ShadowTextToSpeech.getLastTextToSpeechInstance().setOnUtteranceProgressListener(object : UtteranceProgressListener() {
        override fun onStart(utteranceId: String?) {}
        override fun onDone(utteranceId: String?) {}
        @Deprecated("Deprecated in Java") override fun onError(utteranceId: String?) {}
    })
    return manager
}

/** Runs the notification the foreground service would show right now and returns its title. */
internal fun notificationTitle(app: Application): String? {
    val controller = Robolectric.buildService(
        NarrationService::class.java, Intent(app, NarrationService::class.java).setAction(NarrationService.ACTION_UPDATE)
    ).create()
    controller.startCommand(0, 1)
    val n = shadowOf(controller.get()).lastForegroundNotification
    val title = n?.extras?.getCharSequence(android.app.Notification.EXTRA_TITLE)?.toString()
    controller.destroy()
    return title
}

/** True when the app started [NarrationService] since the last call (consumes the started-service queue). */
internal fun Application.narrationServiceStarted(): Boolean {
    var found = false
    while (true) {
        val next = shadowOf(this).nextStartedService ?: break
        if (next.component?.className == NarrationService::class.java.name) found = true
    }
    return found
}

internal fun Application.narrationServiceStopped(): Boolean {
    var found = false
    while (true) {
        val next = shadowOf(this).nextStoppedService ?: break
        if (next.component?.className == NarrationService::class.java.name) found = true
    }
    return found
}
