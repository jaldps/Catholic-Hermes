package com.example.data.audio

import com.example.data.diagnostics.LogEvent
import com.example.data.diagnostics.AppLog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
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
import androidx.core.content.ContextCompat
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
enum class VoiceAvailability { INITIALIZING, READY, MISSING_DATA, UNSUPPORTED, ENGINE_ERROR, PLAYBACK_ERROR }

class LiturgicalTtsManager(context: Context) : TextToSpeech.OnInitListener {
    // A retained Rosary holder must never keep the destroyed Activity alive.
    private val context = context.applicationContext

    private var tts: TextToSpeech? = null
    private var isInitialized = false

    /** Ready only when the requested app language is supported by the engine. */
    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> = _isReady.asStateFlow()
    private val _availability = MutableStateFlow(VoiceAvailability.INITIALIZING)
    val availability = _availability.asStateFlow()
    private val _playbackIssue = MutableStateFlow<VoiceAvailability?>(null)
    val playbackIssue = _playbackIssue.asStateFlow()

    fun dismissPlaybackIssue() { _playbackIssue.value = null }

    fun requestPlayback(): Boolean {
        if (_isReady.value) return true
        _playbackIssue.value = _availability.value
        return false
    }

    fun refreshAvailability() {
        if (isInitialized) applyLanguage(requestedLanguage ?: deviceLanguage())
        else if (_availability.value == VoiceAvailability.ENGINE_ERROR) {
            _availability.value = VoiceAvailability.INITIALIZING
            tts?.shutdown()
            tts = TextToSpeech(context.applicationContext, this)
        }
    }

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentlySpeaking = MutableStateFlow<String?>(null)
    val currentlySpeaking: StateFlow<String?> = _currentlySpeaking.asStateFlow()

    private val _voiceMode = MutableStateFlow(RecitationVoiceMode.DUAL_DIALOGUE)
    val voiceMode: StateFlow<RecitationVoiceMode> = _voiceMode.asStateFlow()

    /** Offline voices of the current language, best first (labels are display-ready). */
    data class VoiceOption(val name: String, val label: String)
    private val _voiceOptions = MutableStateFlow<List<VoiceOption>>(emptyList())
    val voiceOptions: StateFlow<List<VoiceOption>> = _voiceOptions.asStateFlow()
    private var candidates: List<VoiceCandidate> = emptyList()
    private var voiceByName: Map<String, android.speech.tts.Voice> = emptyMap()
    /** Saved picks, keyed "es.leader" / "es.response"; absent means automatic. */
    @Volatile private var savedVoices: Map<String, String> = emptyMap()

    private val mainHandler = Handler(Looper.getMainLooper())
    private var toneGenerator: ToneGenerator? = null

    /** Language the app asked for; applied on init if it arrives early. */
    @Volatile private var requestedLanguage: AppLanguage? = null
    /** User narration speed multiplier (Settings › Narración). */
    @Volatile private var speedMultiplier: Float = 1.0f

    /** Bumped by [stop] so utterance ids are never reused across recitations. */
    private var generation = 0
    /**
     * What is being read, as one state machine; callbacks from an older
     * recitation find nothing registered and are ignored.
     */
    private val tracker = SpeechTracker()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    // ---- audio focus, foreground service and sleep timer -------------------

    /** True while narration was paused by the system or the user and can be continued with [resume]. */
    private val _interrupted = MutableStateFlow(false)
    val interrupted: StateFlow<Boolean> = _interrupted.asStateFlow()

    /** Bumped by every explicit [stop] (user, notification, sleep timer), never by starting the next part. */
    private val _stopCount = MutableStateFlow(0)
    val stopCount: StateFlow<Int> = _stopCount.asStateFlow()

    /**
     * Title of the notification and lock screen (the prayer being recited).
     * Set by every [speakSingle] / [speakDialogue] call from its `title`
     * argument and cleared when narration stops, so a title never outlives
     * the content it names.
     */
    @Volatile var narrationTitle: String? = null

    /** Sleep timer minutes (0 = off), followed from the saved preference. */
    @Volatile internal var sleepTimerMinutes: Int = 0

    private val audioManager = context.applicationContext.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
    private val sessionHandler = Handler(Looper.getMainLooper())
    private var sessionActive = false
    private var serviceRunning = false
    private var focusRequest: AudioFocusRequest? = null
    private var noisyReceiver: BroadcastReceiver? = null
    private var lastRequest: (() -> Unit)? = null
    private var resumeRequest: (() -> Unit)? = null
    private var lastText: String = ""
    private var lastServiceSignature: NarrationState? = null

    /**
     * Narration is spoken content the user chose to play, so it is media
     * ([AudioAttributes.USAGE_MEDIA], speech content type): it pauses music
     * like a podcast and is controlled by the media notification.
     * USAGE_ASSISTANCE_ACCESSIBILITY is meant for screen readers and would
     * wrongly bypass the user's media volume and muting.
     */
    private val focusAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_MEDIA)
        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
        .build()

    /** Focus changes from the system. Exposed internally so tests can drive it. */
    internal val focusListener = AudioManager.OnAudioFocusChangeListener { change -> handleFocusChange(change) }

    internal fun handleFocusChange(change: Int) {
        when (change) {
            AudioManager.AUDIOFOCUS_LOSS -> interrupt(giveUpFocus = true)
            // TTS cannot pause mid-utterance (or duck): stop now, continue on request.
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT,
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> interrupt(giveUpFocus = false)
        }
    }

    @Suppress("DEPRECATION")
    private fun requestFocus(): Boolean {
        val am = audioManager ?: return true
        val granted = if (Build.VERSION.SDK_INT >= 26) {
            val request = focusRequest ?: AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(focusAttributes)
                .setWillPauseWhenDucked(true)
                .setOnAudioFocusChangeListener(focusListener, sessionHandler)
                .build().also { focusRequest = it }
            am.requestAudioFocus(request)
        } else {
            am.requestAudioFocus(focusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
        }
        return granted == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
    }

    @Suppress("DEPRECATION")
    private fun abandonFocus() {
        val am = audioManager ?: return
        if (Build.VERSION.SDK_INT >= 26) {
            focusRequest?.let { am.abandonAudioFocusRequest(it) }
            focusRequest = null
        } else am.abandonAudioFocus(focusListener)
    }

    private fun registerNoisy() {
        if (noisyReceiver != null) return
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(c: Context?, intent: Intent?) {
                if (intent?.action == AudioManager.ACTION_AUDIO_BECOMING_NOISY) interrupt(giveUpFocus = false)
            }
        }
        runCatching {
            ContextCompat.registerReceiver(
                context.applicationContext, receiver, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY),
                ContextCompat.RECEIVER_NOT_EXPORTED
            )
            noisyReceiver = receiver
        }.onFailure {AppLog.failure(LogEvent.TTS_RECEIVER,it)}
    }

    private fun unregisterNoisy() {
        noisyReceiver?.let { runCatching { context.applicationContext.unregisterReceiver(it) } }
        noisyReceiver = null
    }

    /**
     * Gets focus, listens for headphone unplugging, starts the foreground
     * service (unless [withService] is false, as for short voice previews)
     * and arms the sleep timer. False when the system refuses focus.
     */
    private fun beginSession(withService: Boolean): Boolean {
        // The notification buttons talk to whichever manager is narrating.
        active = this
        sessionHandler.removeCallbacks(idleEnd)
        sleepHandler.removeCallbacks(sleepStale)
        if (!requestFocus()) { if (!sessionActive) abandonFocus(); return false }
        if (!sessionActive) {
            sessionActive = true
            registerNoisy()
        }
        // The timer counts from the first narration and survives the short
        // gaps between parts; a long silence (stale) disarms it.
        val minutes = sleepTimerMinutes
        if (!sleepArmed && minutes > 0) {
            sleepArmed = true
            sleepHandler.postDelayed(sleepStop, minutes * 60_000L)
        }
        if (withService && !serviceRunning) {
            serviceRunning = true
            lastServiceSignature = null
        }
        syncService()
        return true
    }

    private fun endSession() {
        sessionHandler.removeCallbacksAndMessages(null)
        if (sleepArmed) sleepHandler.postDelayed(sleepStale, SLEEP_STALE_MS)
        if (sessionActive) { sessionActive = false; abandonFocus(); unregisterNoisy() }
        if (serviceRunning) {
            serviceRunning = false
            lastServiceSignature = null
            runCatching { context.applicationContext.stopService(NarrationService.intent(context.applicationContext)) }.onFailure {AppLog.failure(LogEvent.TTS_SERVICE,it)}
        }
    }

    private val idleEnd = Runnable { if (!tracker.playing && !_interrupted.value) endSession() }
    private val sleepHandler = Handler(Looper.getMainLooper())
    private var sleepArmed = false
    private val sleepStop = Runnable { stop() }
    private val sleepStale = Runnable { sleepArmed = false; sleepHandler.removeCallbacksAndMessages(null) }

    internal fun narrationState(): NarrationState? {
        if (!sessionActive) return null
        val text = (_currentlySpeaking.value ?: lastText).replace(Regex("\\s+"), " ").trim()
        return NarrationState(
            narrationTitle.orEmpty(), if (text.length > 90) text.take(89) + "…" else text,
            paused = _interrupted.value, language = requestedLanguage ?: deviceLanguage()
        )
    }

    private fun syncService() {
        if (!serviceRunning) return
        val state = narrationState() ?: return
        if (state == lastServiceSignature) return
        lastServiceSignature = state
        runCatching {
            ContextCompat.startForegroundService(context.applicationContext, NarrationService.intent(context.applicationContext))
        }.onFailure {AppLog.failure(LogEvent.TTS_SERVICE,it)}
    }

    /** Stops speech now and remembers what was being said so [resume] can say it again. */
    private fun interrupt(giveUpFocus: Boolean) {
        if (!sessionActive || !tracker.playing) return
        resumeRequest = lastRequest
        _interrupted.value = true
        halt()
        if (giveUpFocus) abandonFocus()
        // A forgotten pause must not keep a notification forever.
        sessionHandler.removeCallbacks(idleEnd)
        sessionHandler.postDelayed({ if (_interrupted.value) stop() }, 10 * 60_000L)
        syncService()
    }

    /** Pause from the notification or lock screen. */
    fun pauseNarration() = interrupt(giveUpFocus = false)

    /** «Continuar»: says the interrupted part again from its start. No-op unless interrupted. */
    fun resume() {
        if (!_interrupted.value) return
        val again = resumeRequest
        _interrupted.value = false
        resumeRequest = null
        if (again != null) again() else endSession()
    }

    init {
        if (active?.sessionActive != true) active = this
        tts = TextToSpeech(context.applicationContext, this)
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 70)
        } catch (e: Exception) {
            AppLog.failure(LogEvent.TTS_TONE,e)
            toneGenerator = null
        }
        // Follow the saved narration speed (and the saved language, so the
        // voice is right even before any screen calls setLanguage).
        scope.launch {
            runCatching {
                UserPreferencesStore(context.applicationContext).preferences.collect { prefs ->
                    speedMultiplier = prefs.audioNarrationSpeed.coerceIn(0.5f, 2.0f)
                    sleepTimerMinutes = prefs.narrationSleepMinutes
                    if (savedVoices != prefs.narrationVoices) {
                        savedVoices = prefs.narrationVoices
                    }
                    if (prefs.hasChosenLanguage && requestedLanguage == null) {
                        mainHandler.post { setLanguage(prefs.language) }
                    }
                }
            }.onFailure {AppLog.failure(LogEvent.TTS_PREFERENCES,it)}
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
            applyLanguage(requestedLanguage ?: deviceLanguage())
            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    val id = utteranceId ?: return
                    mainHandler.post { tracker.onStart(id); publish() }
                }

                override fun onDone(utteranceId: String?) {
                    val id = utteranceId ?: return
                    mainHandler.post { tracker.onDone(id); publish() }
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {
                    val id = utteranceId ?: return
                    AppLog.failure(LogEvent.TTS_PLAYBACK)
                    mainHandler.post { tracker.onError(id); publish(); _playbackIssue.value = VoiceAvailability.PLAYBACK_ERROR }
                }
            })
        } else {
            AppLog.failure(LogEvent.TTS_ENGINE)
            isInitialized = false
            _isReady.value = false
            _availability.value = VoiceAvailability.ENGINE_ERROR
        }
    }

    /** Mirrors the tracker into the public flows. */
    private fun publish() {
        _isPlaying.value = tracker.playing
        _currentlySpeaking.value = tracker.speaking
        if (sessionActive) {
            if (tracker.playing || _interrupted.value) sessionHandler.removeCallbacks(idleEnd)
            else { sessionHandler.removeCallbacks(idleEnd); sessionHandler.postDelayed(idleEnd, IDLE_GRACE_MS) }
            syncService()
        }
    }

    fun setLanguage(lang: AppLanguage) {
        if (requestedLanguage != lang && _isPlaying.value) stop()
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
        val result = runCatching { tts?.setLanguage(targetLocale) }.onFailure {AppLog.failure(LogEvent.TTS_LANGUAGE,it)}.getOrNull()
        _availability.value = when {
            result == null -> VoiceAvailability.ENGINE_ERROR
            result >= TextToSpeech.LANG_AVAILABLE -> VoiceAvailability.READY
            result == TextToSpeech.LANG_MISSING_DATA -> VoiceAvailability.MISSING_DATA
            result == TextToSpeech.LANG_NOT_SUPPORTED -> VoiceAvailability.UNSUPPORTED
            else -> VoiceAvailability.ENGINE_ERROR
        }
        _isReady.value = _availability.value == VoiceAvailability.READY
        refreshVoices(lang)
    }

    private fun langCode(lang: AppLanguage) = when (lang) { AppLanguage.ES -> "es"; AppLanguage.EN -> "en"; AppLanguage.PT -> "pt" }

    private fun currentLangCode() = langCode(requestedLanguage ?: deviceLanguage())

    /** Reads the engine's voices; failures just mean "no choice of voice". */
    private fun refreshVoices(lang: AppLanguage) {
        val raw = runCatching { tts?.voices }.getOrNull().orEmpty()
        val map = LinkedHashMap<String, android.speech.tts.Voice>()
        val list = raw.mapNotNull { v ->
            runCatching {
                map[v.name] = v
                VoiceCandidate(
                    name = v.name, language = v.locale.language, country = v.locale.country,
                    quality = v.quality, latency = v.latency,
                    networkRequired = v.isNetworkConnectionRequired,
                    notInstalled = v.features?.contains(TextToSpeech.Engine.KEY_FEATURE_NOT_INSTALLED) == true
                )
            }.getOrNull()
        }
        voiceByName = map
        candidates = list
        val code = langCode(lang)
        _voiceOptions.value = VoiceSelector.ranked(list, code, VoiceSelector.appCountry(code))
            .mapIndexed { i, c -> VoiceOption(c.name, VoiceSelector.label(i, c, code)) }
    }

    private fun currentChoice(overrideLeader: String? = null, overrideResponse: String? = null): VoiceChoice {
        val code = currentLangCode()
        return VoiceSelector.choose(
            candidates, code, VoiceSelector.appCountry(code),
            overrideLeader ?: savedVoices["$code.leader"], overrideResponse ?: savedVoices["$code.response"]
        )
    }

    /** Speaks a short sample with [voiceName] (null = automatic) in the given role. */
    fun preview(response: Boolean, voiceName: String?, sample: String) {
        if (sample.isBlank() || !requestPlayback()) return
        halt()
        _interrupted.value = false
        narrationTitle = null
        if (!beginSession(withService = false)) return
        lastRequest = { preview(response, voiceName, sample) }
        lastText = sample
        val gen = generation
        tracker.begin(sample)
        publish()
        val code = currentLangCode()
        // Auto must preview the automatic pick, not the saved one.
        val choice = VoiceSelector.choose(
            candidates, code, VoiceSelector.appCountry(code),
            if (response) savedVoices["$code.leader"] else voiceName,
            if (response) voiceName else null
        )
        queuePart(gen, "preview", sample, female = response, flush = true, choice = choice)
    }

    fun setVoiceMode(mode: RecitationVoiceMode) {
        _voiceMode.value = mode
    }

    fun playSacredChime() {
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 300)
        } catch (_: Exception) {}
    }

    /**
     * The one way every screen narrates: reads [text] in one voice with audio
     * focus, the foreground notification (play/pause/stop, lock screen), the
     * sleep timer and interruption handling. [title] names the content in the
     * notification and lock screen (a prayer, a saint, a chapter).
     */
    fun speakSingle(text: String, isFemaleVoice: Boolean = false, title: String? = null) {
        if (text.isBlank() || !requestPlayback()) return
        halt()
        _interrupted.value = false
        narrationTitle = title?.trim()?.takeIf { it.isNotEmpty() }
        if (!beginSession(withService = true)) return
        lastRequest = { speakSingle(text, isFemaleVoice, title) }
        lastText = text
        val gen = generation
        tracker.begin(text)
        publish()
        queuePart(gen, "single", text, female = isFemaleVoice, flush = true)
    }

    /**
     * A prayer said as leader and response. In [RecitationVoiceMode.DUAL_DIALOGUE]
     * the leader is read in one voice, then, after a breath, the response
     * in another (a different system voice when the device has two); the other modes read only their half (a prayer with no
     * response is always read in full).
     */
    fun speakDialogue(leaderText: String, responseText: String, title: String? = null) {
        if (leaderText.isBlank() && responseText.isBlank()) return
        if (!requestPlayback()) return
        halt()
        _interrupted.value = false
        narrationTitle = title?.trim()?.takeIf { it.isNotEmpty() }
        if (!beginSession(withService = true)) return
        lastRequest = { speakDialogue(leaderText, responseText, title) }
        lastText = leaderText.ifBlank { responseText }
        val gen = generation
        val mode = _voiceMode.value
        val hasResponse = responseText.isNotBlank()

        val readLeader = !hasResponse || mode != RecitationVoiceMode.FEMALE
        val readResponse = hasResponse && mode != RecitationVoiceMode.MALE

        playSacredChime()
        tracker.begin(if (readLeader) leaderText else responseText)
        publish()

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
    private fun queuePart(
        gen: Int, part: String, text: String, female: Boolean, flush: Boolean,
        choice: VoiceChoice = currentChoice()
    ) {
        val engine = tts ?: return
        // Real voices, not pitch-shifting; a mild offset only if one voice plays both roles.
        val name = if (female) choice.response else choice.leader
        voiceByName[name]?.let { v -> runCatching { engine.setVoice(v) } }
        engine.setPitch(if (female) choice.responsePitch else choice.leaderPitch)
        engine.setSpeechRate((0.92f * speedMultiplier).coerceIn(0.4f, 2.0f))
        val chunks = chunk(text, maxInput())
        chunks.forEachIndexed { i, piece ->
            val id = "g$gen:$part:$i"
            tracker.register(id, text)
            val result = engine.speak(piece, if (flush && i == 0) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD, null, id)
            // A refused request never reports back; without this the narrator
            // would look busy forever.
            if (result == TextToSpeech.ERROR) {
                AppLog.failure(LogEvent.TTS_PLAYBACK)
                tracker.onError(id)
                publish()
                _playbackIssue.value = VoiceAvailability.PLAYBACK_ERROR
            }
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

    /** Cancels speech only; the session (focus, service) is left for the caller to keep or end. */
    private fun halt() {
        generation++
        mainHandler.removeCallbacksAndMessages(null)
        tracker.stop()
        tts?.stop()
        publish()
    }

    /** Stops narration for good: releases audio focus and the foreground service. */
    fun stop() {
        _interrupted.value = false
        resumeRequest = null
        sleepArmed = false
        sleepHandler.removeCallbacksAndMessages(null)
        _stopCount.value++
        halt()
        endSession()
        narrationTitle = null
    }

    private var rosaryHolders = 0
    private var releaseRequested = false

    /** Ownership bridge for the activity-scoped Rosary narration holder. Main-thread only. */
    internal fun retainForRosaryHolder(): AutoCloseable {
        rosaryHolders++
        var closed = false
        return AutoCloseable {
            if (!closed) {
                closed = true
                rosaryHolders--
                if (rosaryHolders == 0 && releaseRequested) release()
            }
        }
    }

    fun release() {
        if (rosaryHolders > 0) { releaseRequested = true; return }
        releaseRequested = false
        stop()
        if (active === this) active = null
        scope.cancel()
        tts?.shutdown()
        tts = null
        isInitialized = false
        _isReady.value = false
        toneGenerator?.release()
        toneGenerator = null
    }

    companion object {
        /** The manager the notification buttons talk to (the app has one). */
        @Volatile internal var active: LiturgicalTtsManager? = null
        /** Pause between parts that does not yet count as «narration ended». */
        internal const val IDLE_GRACE_MS = 4_000L
        /** Silence after which a running sleep timer is forgotten. */
        internal const val SLEEP_STALE_MS = 2 * 60_000L

        private fun deviceLanguage(): AppLanguage = when (Locale.getDefault().language) {
            "es" -> AppLanguage.ES
            "pt" -> AppLanguage.PT
            else -> AppLanguage.EN
        }
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
