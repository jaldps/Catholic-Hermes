package com.example.data.audio

import java.util.Locale

/** A system TTS voice reduced to what the ranking needs (testable on the JVM). */
data class VoiceCandidate(
    val name: String,
    val language: String,
    val country: String,
    val quality: Int,
    val latency: Int,
    val networkRequired: Boolean = false,
    val notInstalled: Boolean = false
)

/** The voices to use for the leading and responding parts; null means "engine default". */
data class VoiceChoice(
    val leader: String?,
    val response: String?,
    val leaderPitch: Float,
    val responsePitch: Float
)

/**
 * Picks real system voices instead of pitch-shifting one. Gender is not
 * exposed reliably by Android, so the second voice is simply the best
 * *different* one.
 */
object VoiceSelector {
    /** Mild pitch difference used only when one voice has to play both roles. */
    const val SINGLE_VOICE_RESPONSE_PITCH = 1.04f

    /**
     * Offline voices of [language], best first: the app's [appCountry] before
     * other countries, then higher quality, then lower latency.
     */
    fun ranked(candidates: List<VoiceCandidate>, language: String, appCountry: String): List<VoiceCandidate> =
        candidates
            .filter { it.language.equals(language, ignoreCase = true) && !it.networkRequired && !it.notInstalled }
            .sortedWith(
                // Naturalness first: a higher-quality voice of another country of
                // the same language beats a lower-quality one of the app locale.
                compareByDescending<VoiceCandidate> { it.quality }
                    .thenByDescending { it.country.equals(appCountry, ignoreCase = true) }
                    .thenBy { it.latency }
                    .thenBy { it.name }
            )

    /** [storedLeader]/[storedResponse] of null, blank or unknown mean "auto". */
    fun choose(
        candidates: List<VoiceCandidate>,
        language: String,
        appCountry: String,
        storedLeader: String? = null,
        storedResponse: String? = null
    ): VoiceChoice {
        val ranked = ranked(candidates, language, appCountry)
        if (ranked.isEmpty()) return VoiceChoice(null, null, 1.0f, SINGLE_VOICE_RESPONSE_PITCH)
        val leader = ranked.firstOrNull { it.name == storedLeader } ?: ranked.first()
        val response = ranked.firstOrNull { it.name == storedResponse }
            ?: ranked.firstOrNull { it.name != leader.name }
            ?: leader
        val same = leader.name == response.name
        return VoiceChoice(leader.name, response.name, 1.0f, if (same) SINGLE_VOICE_RESPONSE_PITCH else 1.0f)
    }

    /** "Voz 2 · México · alta calidad" for the [index]th (0-based) ranked voice. */
    fun label(index: Int, voice: VoiceCandidate, language: String): String {
        val loc = Locale(language)
        val country = if (voice.country.isNotBlank()) Locale(voice.language, voice.country).getDisplayCountry(loc) else ""
        val quality = when {
            voice.quality >= 400 -> when (language) { "es" -> "alta calidad"; "pt" -> "alta qualidade"; else -> "high quality" }
            voice.quality in 1..200 -> when (language) { "es" -> "calidad básica"; "pt" -> "qualidade básica"; else -> "basic quality" }
            else -> ""
        }
        val word = when (language) { "es" -> "Voz"; "pt" -> "Voz"; else -> "Voice" }
        return listOf("$word ${index + 1}", country, quality).filter { it.isNotBlank() }.joinToString(" · ")
    }

    fun appCountry(language: String): String = when (language) { "es" -> "ES"; "pt" -> "BR"; else -> "US" }
}
