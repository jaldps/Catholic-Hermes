package com.example.data.audio

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

/**
 * Who the narrator voices in a prayer said as leader and response.
 * [MALE] voices only the leader's half (you answer), [FEMALE] only the
 * response (you lead), [DUAL_DIALOGUE] both, in two voices.
 */
enum class RecitationVoiceMode {
    MALE,
    FEMALE,
    DUAL_DIALOGUE // Leader in male, response in female (or vice versa)
}

/**
 * Spoken recitation for the whole app.
 *
 * - The app language is applied as soon as the engine is ready (a language
 *   requested before initialisation is remembered, not dropped).
 * - The saved narration speed preference scales the speech rate.
 * - [speakDialogue] honours [voiceMode].
 * - Text longer than the engine's input limit (4000 chars) is split at
 *   sentence boundaries and queued, so long novenas are read whole.
 * - [stop] cancels everything pending, including the queued response half
 *   of a dialogue, so a quick Next never overlaps two voices.
 *
 * [currentlySpeaking] always carries the full text of the part being read
 * (never a chunk), so callers can compare it with what they display.
 */
class LiturgicalTtsManager(private val context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var isInitialized = false

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentlySpeaking = MutableStateFlow<String?>(null)
    val currentlySpeaking: StateFlow<String?> = _currentlySpeaking.asStateFlow()

    private val _voiceMode = MutableStateFlow(RecitationVoiceMode.DUAL_DIALOGUE)
    val voiceMode: StateFlow<RecitationVoiceMode> = _voiceMode.asStateFlow()

    private val mainHandler = Handler(Looper.getMainLooper())
    private var toneGenerator: ToneGenerator? = null

    /** Language the app asked for; applied on init if it arrives early. */
    @Volatile private var requestedLanguage: AppLanguage? = null
    /** User narration speed multiplier (Settings › Narración). */
    @Volatile private var speedMultiplier: Float = 1.0f

    /** Bumped by [stop]: callbacks from an older recitation are ignored. */
    @Volatile private var generation = 0
    /** Full text of each queued utterance id, for [currentlySpeaking]. */
    private val partOfUtterance = java.util.concurrent.ConcurrentHashMap<String, String>()
    @Volatile private var lastUtteranceId: String? = null

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    init {
        tts = TextToSpeech(context.applicationContext, this)
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 70)
        } catch (_: Exception) {
            toneGenerator = null
        }
        // Follow the saved narration speed (and the saved language, so the
        // voice is right even before any screen calls setLanguage).
        scope.launch {
            runCatching {
                UserPreferencesStore(context.applicationContext).preferences.collect { prefs ->
                    speedMultiplier = prefs.audioNarrationSpeed.coerceIn(0.5f, 2.0f)
                    if (prefs.hasChosenLanguage && requestedLanguage == null) {
                        mainHandler.post { setLanguage(prefs.language) }
                    }
                }
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
            applyLanguage(requestedLanguage ?: AppLanguage.ES)
            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    val id = utteranceId ?: return
                    mainHandler.post {
                        if (!id.startsWith("g$generation:")) return@post
                        _isPlaying.value = true
                        partOfUtterance[id]?.let { _currentlySpeaking.value = it }
                    }
                }

                override fun onDone(utteranceId: String?) {
                    val id = utteranceId ?: return
                    mainHandler.post {
                        if (id == lastUtteranceId) finished()
                    }
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {
                    val id = utteranceId ?: return
                    mainHandler.post {
                        if (id == lastUtteranceId || id.startsWith("g$generation:")) finished()
                    }
                }
            })
        }
    }

    private fun finished() {
        _isPlaying.value = false
        _currentlySpeaking.value = null
        lastUtteranceId = null
    }

    fun setLanguage(lang: AppLanguage) {
        requestedLanguage = lang
        if (!isInitialized) return
        applyLanguage(lang)
    }

    private fun applyLanguage(lang: AppLanguage) {
        val targetLocale = when (lang) {
            AppLanguage.ES -> Locale("es", "ES")
            AppLanguage.EN -> Locale("en", "US")
            AppLanguage.PT -> Locale("pt", "BR")
        }
        runCatching { tts?.language = targetLocale }
    }

    fun setVoiceMode(mode: RecitationVoiceMode) {
        _voiceMode.value = mode
    }

    fun playSacredChime() {
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 300)
        } catch (_: Exception) {}
    }

    fun speakSingle(text: String, isFemaleVoice: Boolean = false) {
        if (!isInitialized || text.isBlank()) return
        stop()
        val gen = generation
        _currentlySpeaking.value = text
        _isPlaying.value = true
        queuePart(gen, "single", text, female = isFemaleVoice, flush = true)
    }

    /**
     * A prayer said as leader and response. In [RecitationVoiceMode.DUAL_DIALOGUE]
     * the leader is read in a lower voice, then, after a breath, the response
     * in a higher one; the other modes read only their half (a prayer with no
     * response is always read in full).
     */
    fun speakDialogue(leaderText: String, responseText: String) {
        if (!isInitialized) return
        if (leaderText.isBlank() && responseText.isBlank()) return
        stop()
        val gen = generation
        val mode = _voiceMode.value
        val hasResponse = responseText.isNotBlank()

        val readLeader = !hasResponse || mode != RecitationVoiceMode.FEMALE
        val readResponse = hasResponse && mode != RecitationVoiceMode.MALE

        playSacredChime()
        _isPlaying.value = true
        _currentlySpeaking.value = if (readLeader) leaderText else responseText

        // A short silence lets the chime ring before the first word.
        silence(gen, "pre", 350, flush = true)
        if (readLeader && leaderText.isNotBlank()) {
            queuePart(gen, "leader", leaderText, female = false, flush = false)
        }
        if (readLeader && readResponse) silence(gen, "breath", 900, flush = false)
        if (readResponse) {
            queuePart(gen, "response", responseText, female = true, flush = false)
        }
    }

    /** Queues [text] in engine-sized chunks under one logical part. */
    private fun queuePart(gen: Int, part: String, text: String, female: Boolean, flush: Boolean) {
        val engine = tts ?: return
        engine.setPitch(if (female) 1.22f else 0.85f)
        engine.setSpeechRate((0.92f * speedMultiplier).coerceIn(0.4f, 2.0f))
        val chunks = chunk(text, maxInput())
        chunks.forEachIndexed { i, piece ->
            val id = "g$gen:$part:$i"
            partOfUtterance[id] = text
            lastUtteranceId = id
            engine.speak(piece, if (flush && i == 0) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD, null, id)
        }
    }

    private fun silence(gen: Int, part: String, millis: Long, flush: Boolean) {
        val engine = tts ?: return
        val id = "g$gen:$part"
        runCatching {
            engine.playSilentUtterance(millis, if (flush) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD, id)
        }
    }

    private fun maxInput(): Int =
        runCatching { TextToSpeech.getMaxSpeechInputLength() }.getOrDefault(4000).coerceAtLeast(500) - 100

    fun stop() {
        generation++
        mainHandler.removeCallbacksAndMessages(null)
        partOfUtterance.clear()
        lastUtteranceId = null
        tts?.stop()
        _isPlaying.value = false
        _currentlySpeaking.value = null
    }

    fun release() {
        stop()
        scope.cancel()
        tts?.shutdown()
        tts = null
        toneGenerator?.release()
        toneGenerator = null
    }

    companion object {
        /**
         * Splits [text] into pieces no longer than [limit], preferring
         * paragraph, then sentence, then word boundaries.
         */
        internal fun chunk(text: String, limit: Int): List<String> {
            if (text.length <= limit) return listOf(text)
            val out = mutableListOf<String>()
            var rest = text.trim()
            while (rest.length > limit) {
                val window = rest.substring(0, limit)
                val cut = listOf("\n\n", ". ", "; ", ", ", " ")
                    .map { sep -> window.lastIndexOf(sep).let { if (it > limit / 3) it + sep.length else -1 } }
                    .firstOrNull { it > 0 } ?: limit
                out += rest.substring(0, cut).trim()
                rest = rest.substring(cut).trim()
            }
            if (rest.isNotEmpty()) out += rest
            return out
        }
    }
}
