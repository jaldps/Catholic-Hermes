package com.example.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.data.audio.RecitationVoiceMode
import com.example.data.models.AppLanguage
import com.example.data.models.RosaryMysteryType

// ---------------------------------------------------------------------------
// Pray-aloud mode as an explicit state machine.
//
//   Idle --Toggle--> Speaking(target) --Toggle--> Idle
//   Speaking(t) --TargetChanged(t2)--> Speaking(t2)   (speaks t2)
//   Speaking(t) --EngineReady-------->  Speaking(t)   (speaks t again)
//   Speaking(t) --ScreenLeft--------->  Idle          (stops the voice)
//
// "Speaking" is a mode, not a moment: it stays on from the play press to the
// pause press and every step change is spoken, exactly like before. What the
// engine is saying right now stays in LiturgicalTtsManager.currentlySpeaking.
// Nothing can be speaking from this screen unless the state is Speaking, and
// leaving the screen always returns to Idle: the old pair "narrationActive
// flag + whatever the engine happens to be doing" can no longer disagree.
// ---------------------------------------------------------------------------

/**
 * Everything that, if it changes while praying aloud, must re-read the
 * screen. Equality is the restart rule: a new target is spoken, an equal one
 * is not spoken twice.
 */
internal data class NarrationTarget(
    val devotion: Devotion,
    /** Null for the chaplet, which has no mysteries to choose. */
    val mysterySet: RosaryMysteryType?,
    val step: Int,
    val lang: AppLanguage,
    val voice: RecitationVoiceMode
)

internal sealed interface NarrationState {
    /** Silent: the person has not pressed play, or has paused. */
    object Idle : NarrationState {
        override fun toString() = "Idle"
    }

    /** Pray-aloud mode is on; [target] is what is being (or was last) read. */
    data class Speaking(val target: NarrationTarget) : NarrationState
}

internal sealed interface NarrationEvent {
    /** The play / pause button, pressed while [target] is on screen. */
    data class Toggle(val target: NarrationTarget) : NarrationEvent
    /** The step, devotion, set, language or voice mode changed. */
    data class TargetChanged(val target: NarrationTarget) : NarrationEvent
    /** The speech engine finished starting up. */
    object EngineReady : NarrationEvent
    /** The screen is being left or disposed. */
    object ScreenLeft : NarrationEvent
}

internal sealed interface NarrationCommand {
    data class Speak(val target: NarrationTarget) : NarrationCommand
    object Stop : NarrationCommand
}

internal data class NarrationStep(val state: NarrationState, val command: NarrationCommand? = null)

internal fun reduceNarration(state: NarrationState, event: NarrationEvent): NarrationStep = when (state) {
    NarrationState.Idle -> when (event) {
        is NarrationEvent.Toggle -> NarrationStep(NarrationState.Speaking(event.target), NarrationCommand.Speak(event.target))
        is NarrationEvent.TargetChanged, NarrationEvent.EngineReady, NarrationEvent.ScreenLeft -> NarrationStep(state)
    }
    is NarrationState.Speaking -> when (event) {
        is NarrationEvent.Toggle -> NarrationStep(NarrationState.Idle, NarrationCommand.Stop)
        is NarrationEvent.TargetChanged ->
            if (event.target == state.target) NarrationStep(state)
            else NarrationStep(NarrationState.Speaking(event.target), NarrationCommand.Speak(event.target))
        NarrationEvent.EngineReady -> NarrationStep(state, NarrationCommand.Speak(state.target))
        NarrationEvent.ScreenLeft -> NarrationStep(NarrationState.Idle, NarrationCommand.Stop)
    }
}

/** Where commands go: the screen resolves the target to a prayer and speaks it. */
internal interface NarrationSink {
    fun speak(target: NarrationTarget)
    fun stop()
}

/** Holds the [NarrationState] (observable by Compose) and runs its commands. */
internal class NarrationController(private val sink: NarrationSink) {
    var state: NarrationState by mutableStateOf(NarrationState.Idle)
        private set

    val isSpeaking: Boolean get() = state is NarrationState.Speaking

    fun dispatch(event: NarrationEvent) {
        val step = reduceNarration(state, event)
        state = step.state
        when (val c = step.command) {
            is NarrationCommand.Speak -> sink.speak(c.target)
            NarrationCommand.Stop -> sink.stop()
            null -> Unit
        }
    }
}
