package com.example.data.audio

/**
 * What the speech engine is reading, as one explicit state machine.
 *
 *   Silent --begin--> Reading --(last text part done / failed)--> Silent
 *   Reading --stop--> Silent
 *
 * A recitation is a run of text parts (chunks of a leader and a response)
 * registered up front in playback order, with silent pauses between them
 * that the tracker never sees. The engine reports back asynchronously by
 * utterance id; every report is checked against the parts registered since
 * the last [stop] / [begin], so a late callback from an older recitation is
 * ignored instead of clearing or overwriting the current one.
 *
 * Main-thread only (the manager posts engine callbacks to the main looper).
 * No Android types, so it is unit-tested on the JVM.
 */
internal class SpeechTracker {
    /** True from [begin] until the last text part ends, fails or is stopped. */
    var playing: Boolean = false
        private set

    /** Full text of the part being read (never a chunk), or null. */
    var speaking: String? = null
        private set

    /** Full text of each registered utterance id. */
    private val partOf = HashMap<String, String>()
    private var lastId: String? = null

    /** A new recitation starts: forget everything older. */
    fun begin(initiallySpeaking: String?) {
        clear()
        playing = true
        speaking = initiallySpeaking?.takeIf { it.isNotBlank() }
    }

    /** Queues one chunk of [fullText] under [id]; the last one registered ends the recitation. */
    fun register(id: String, fullText: String) {
        partOf[id] = fullText
        lastId = id
    }

    /** The engine began [id]. Unknown (stale) ids are ignored. */
    fun onStart(id: String) {
        val text = partOf[id] ?: return
        playing = true
        speaking = text
    }

    /** [id] completed normally. */
    fun onDone(id: String) {
        if (id == lastId) clear()
    }

    /**
     * [id] failed. Only the last part's failure ends the recitation; a failed
     * middle chunk is skipped by the engine and the queue plays on.
     */
    fun onError(id: String) {
        if (id == lastId) clear()
    }

    /** Everything cancelled: late callbacks for it will find nothing. */
    fun stop() = clear()

    private fun clear() {
        playing = false
        speaking = null
        partOf.clear()
        lastId = null
    }
}
