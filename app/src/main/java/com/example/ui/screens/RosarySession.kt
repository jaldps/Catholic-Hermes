package com.example.ui.screens

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import com.example.data.models.RosaryMysteryType

// ---------------------------------------------------------------------------
// The rosary screen's session, as one value and a reducer.
//
// Everything the person has "done" on the Rosary / Divine Mercy screen that
// must survive rotation and process death lives in [RosarySession]. It is a
// plain immutable value changed only by [RosarySession.reduce], so:
//  - the two devotions each keep their own position (they cannot share one);
//  - the mystery set is either "follow the weekday" or "the person's pick",
//    never a stale copy of one pretending to be the other;
//  - a step index can never be stored out of range;
//  - skipping an optional prayer is one rule, in one place.
// No Android or Compose-UI types here, so the whole file is JVM-testable.
// ---------------------------------------------------------------------------

/** The two devotions on the screen. [tab] is the legacy 0/1 tab number. */
enum class Devotion {
    ROSARY,
    MERCY;

    val tab: Int get() = ordinal

    companion object {
        /** Deep links pass 0 or 1; anything else is pulled into range. */
        fun fromTab(tab: Int): Devotion = values()[tab.coerceIn(0, 1)]
    }
}

/** How many steps a devotion has and which of them may be skipped. */
internal interface StepMap {
    val stepCount: Int
    fun isOptional(index: Int): Boolean
}

/** The first step after [index] that is not optional, or null if none. */
internal fun StepMap.firstRequiredAfter(index: Int): Int? =
    (index + 1 until stepCount).firstOrNull { !isOptional(it) }

/** Which set of mysteries is shown: the weekday's, or the person's choice. */
internal sealed interface MysterySelection {
    /** Follows the weekday's set. */
    object ByDay : MysterySelection {
        override fun toString() = "ByDay"
    }

    data class Picked(val type: RosaryMysteryType) : MysterySelection

    fun resolve(today: RosaryMysteryType): RosaryMysteryType = when (this) {
        ByDay -> today
        is Picked -> type
    }
}

/** What the person can do to the session. */
internal sealed interface RosaryEvent {
    /** Marker for events that move along the steps of the current devotion. */
    sealed interface Move : RosaryEvent

    data class SelectDevotion(val devotion: Devotion) : RosaryEvent
    data class PickMysterySet(val type: RosaryMysteryType) : RosaryEvent
    data class GoTo(val step: Int) : Move
    object Next : Move
    object Previous : Move
    /** Leave the optional prayer on screen for the next required one. */
    object SkipOptional : Move
    object ToggleInstructions : RosaryEvent
}

internal data class RosarySession(
    val devotion: Devotion = Devotion.ROSARY,
    val mysterySelection: MysterySelection = MysterySelection.ByDay,
    val rosaryStep: Int = 0,
    val mercyStep: Int = 0,
    /** Beginner guidance: sticky from step to step once turned on. */
    val instructionsOn: Boolean = false
) {
    /** Position in the devotion on screen. */
    val step: Int get() = stepIn(devotion)

    fun stepIn(devotion: Devotion): Int =
        if (devotion == Devotion.ROSARY) rosaryStep else mercyStep

    fun mysterySet(today: RosaryMysteryType): RosaryMysteryType = mysterySelection.resolve(today)

    /**
     * Applies [event]. [steps] describes the devotion currently on screen
     * (only moves read it); [today] is the weekday's set. Unchanged state is
     * returned as an equal value, so callers can compare before and after.
     */
    fun reduce(event: RosaryEvent, steps: StepMap, today: RosaryMysteryType): RosarySession = when (event) {
        is RosaryEvent.SelectDevotion -> copy(devotion = event.devotion)
        is RosaryEvent.PickMysterySet ->
            // A different set starts again at the first prayer; re-picking the
            // current set (from the sheet) changes nothing.
            if (event.type == mysterySet(today)) {
                copy(mysterySelection = MysterySelection.Picked(event.type))
            } else {
                copy(mysterySelection = MysterySelection.Picked(event.type), rosaryStep = 0)
            }
        is RosaryEvent.GoTo -> withStep(event.step, steps)
        RosaryEvent.Next -> withStep(step + 1, steps)
        RosaryEvent.Previous -> withStep(step - 1, steps)
        RosaryEvent.SkipOptional -> steps.firstRequiredAfter(step)?.let { withStep(it, steps) } ?: this
        RosaryEvent.ToggleInstructions -> copy(instructionsOn = !instructionsOn)
    }

    private fun withStep(target: Int, steps: StepMap): RosarySession {
        val clamped = target.coerceIn(0, (steps.stepCount - 1).coerceAtLeast(0))
        return if (devotion == Devotion.ROSARY) copy(rosaryStep = clamped) else copy(mercyStep = clamped)
    }

    companion object {
        /** A fresh session opened on [initialTab] (0 = Rosary, 1 = Divine Mercy). */
        fun opening(initialTab: Int) = RosarySession(devotion = Devotion.fromTab(initialTab))

        /** Survives rotation and process death. */
        val Saver: Saver<RosarySession, Any> = listSaver(
            save = {
                listOf(
                    it.devotion.ordinal,
                    (it.mysterySelection as? MysterySelection.Picked)?.type?.name ?: "",
                    it.rosaryStep,
                    it.mercyStep,
                    it.instructionsOn
                )
            },
            restore = {
                RosarySession(
                    devotion = Devotion.fromTab(it[0] as Int),
                    mysterySelection = (it[1] as String).takeIf(String::isNotEmpty)
                        ?.let { name -> MysterySelection.Picked(RosaryMysteryType.valueOf(name)) }
                        ?: MysterySelection.ByDay,
                    rosaryStep = (it[2] as Int).coerceAtLeast(0),
                    mercyStep = (it[3] as Int).coerceAtLeast(0),
                    instructionsOn = it[4] as Boolean
                )
            }
        )
    }
}
