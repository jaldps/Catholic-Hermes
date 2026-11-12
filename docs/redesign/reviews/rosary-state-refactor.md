# Rosary and Divine Mercy: state-machine refactor

Branch `ui-redesign-v2`. Scope: `RosaryScreen.kt`, `PrayerBeadCounter.kt`,
`LiturgicalTtsManager.kt` (public API unchanged, one flow added), tests.
Goal: fewer state bugs and clearer code, no visible change (every testTag,
contentDescription, string and visual is untouched).

## 1. Inventory of the implicit state machines (before)

| # | Machine | How it was encoded | Combinations it allowed that should not exist |
|---|---|---|---|
| 1 | Narration (pray aloud) | `var narrationActive` in `remember`, plus a `LaunchedEffect` keyed on 7 values (`narrationActive, tab, set, lang, rosaryIndex, mercyIndex, voiceMode`), plus `rememberUpdatedState` + `DisposableEffect` for "stop on leave", plus the engine's own `isPlaying/currentlySpeaking` | Flag on but engine never started (play pressed before the speech engine was ready: pause icon, no sound, never recovers). Effect re-fired on keys that cannot change (mystery set while on the chaplet; the other devotion's index). Flag and engine could disagree after stop. |
| 2 | Step position | `rosaryStepIndex`, `mercyStepIndex` as two `rememberSaveable` ints, clamped only when read | Out-of-range index stored and silently clamped later; the "current" index was a computed pair (`isRosary ? a : b`) repeated in several places. |
| 3 | Devotion (Rosary vs Divine Mercy) | `selectedTab` Int 0/1 | Any Int; `initialTab` coerced once, no type. |
| 4 | Mystery set | `selectedMysteryType` = a copy of "today's set" taken at first composition, then overwritten by the picker | Impossible to tell "follows the weekday" from "the person chose this one". The sheet's "reset to step 0 when the set changes" lived in a lambda in the UI. |
| 5 | Weekday rule | `Calendar` read inline in `CatholicDataRepository.getTodayRosaryMystery()` | Untestable without a repository. |
| 6 | Skip optional prayer | `onSkip` lambda in the UI: "first later step that is not optional", then `go(next)` | Button shown on an optional step with no required step after it (no-op), rule duplicated away from the data. |
| 7 | Decade-complete haptic | Inline in `go()` | Rule not testable. |
| 8 | Instructions open | `instructionsOn` boolean, separate state | Independent of position (intended: sticky), but saved separately from the rest of the session. |
| 9 | Overlay sheet | `sheet: RosarySheet?` in `remember`; the MYSTERY sheet writes `sheet = null` from inside composition when no decade is on screen | State written during composition; MYSTERY sheet open while on the chaplet is representable (guarded by that write). Not saved on rotation (acceptable). Left as is, noted. |
| 10 | TTS utterance queue | `generation` int, `partOfUtterance` map, `lastUtteranceId`, `_isPlaying`, `_currentlySpeaking`, callbacks checking `startsWith("g$generation:")` | `onError` of ANY utterance of the current generation (a silence, a middle chunk) cleared `isPlaying` and the highlight while the rest of the queue kept speaking, and cleared `lastUtteranceId`, so the real end was never seen. A synchronous `speak()` refusal (`ERROR`) left `isPlaying` true forever. A blank leader showed `currentlySpeaking = ""`. |
| 11 | Bead reel | Drag/tap decisions inline in `pointerInput` blocks; implicit phases resting / dragging / settling | Decision rules (which bead a tap hits, what the end of a drag means) not testable. |
| 12 | Rotation / process death | Position, set, tab, instructions: `rememberSaveable` (4 separate savers). Narration, sheet: `remember` | Nothing wrong, but spread over six declarations. Narration is deliberately not restored (audio is stopped when the composition goes away, so the flag must reset too). |

## 2. The new models

All pure Kotlin (no Android types), so they run as plain JVM unit tests.

```kotlin
// ui/screens/RosarySession.kt
enum class Devotion { ROSARY, MERCY }
internal sealed interface MysterySelection { object ByDay; data class Picked(val type) }
internal interface StepMap { val stepCount: Int; fun isOptional(i: Int): Boolean }   // PrayerGuide implements it

internal data class RosarySession(
    val devotion: Devotion, val mysterySelection: MysterySelection,
    val rosaryStep: Int, val mercyStep: Int, val instructionsOn: Boolean
) {
    fun reduce(event: RosaryEvent, steps: StepMap, today: RosaryMysteryType): RosarySession
    companion object { val Saver: Saver<RosarySession, Any> }          // one rememberSaveable
}
internal sealed interface RosaryEvent {
    SelectDevotion, PickMysterySet, ToggleInstructions
    sealed interface Move { GoTo(step), Next, Previous, SkipOptional }
}
// PrayerGuide.finishesDecade(from, to)   -> the firmer haptic tick

// ui/screens/RosaryNarration.kt
internal sealed interface NarrationState { object Idle; data class Speaking(val target: NarrationTarget) }
internal data class NarrationTarget(devotion, mysterySet?, step, lang, voice)   // equality == "restart" rule
internal sealed interface NarrationEvent { Toggle(target), TargetChanged(target), EngineReady, ScreenLeft }
internal sealed interface NarrationCommand { Speak(target), Stop }
internal fun reduceNarration(state, event): NarrationStep(state, command?)
internal class NarrationController(sink)   // holds state, runs commands

// data/audio/SpeechTracker.kt
internal class SpeechTracker { begin(initial); register(id, fullText); onStart/onDone/onError(id); stop() }
// LiturgicalTtsManager delegates to it and mirrors playing/speaking into the same public flows.

// data/repository/RosaryMysterySchedule.kt
object RosaryMysterySchedule { fun typeFor(dayOfWeek: Int); fun today() }   // repository delegates

// ui/components/PrayerBeadCounter.kt
internal object ReelGesture { landed(); onDragEnd() -> Select(bead) | SnapBack; hit() }
```

State diagram of pray-aloud mode:

```
Idle --Toggle--> Speaking(t) --Toggle--> Idle
Speaking(t) --TargetChanged(t2 != t)--> Speaking(t2)   [speak t2]
Speaking(t) --EngineReady-->            Speaking(t)    [speak t again]
Speaking(t) --ScreenLeft-->             Idle           [stop voice]
Idle ignores TargetChanged / EngineReady / ScreenLeft
```

"Paused" is not a separate state: Pause and "never played" behave identically
(resume re-reads the current prayer), so modelling it would add a state with no
behaviour. "Stopping" is not a state either: the engine's stop is synchronous
and `SpeechTracker.stop()` clears every registered utterance, which is what
makes stale callbacks harmless.

The screen is now thin: it holds `session` (one `rememberSaveable` with the
Saver) and a `NarrationController`, sends events through `dispatch(...)`, and
renders. Behaviour kept on purpose: pray-aloud stays on across beads, mystery
sets, devotions and language/voice changes; there is no auto-advance;
narration is not restored after rotation or process death (the voice is
stopped when the screen goes away).

## 3. Bugs fixed (exposed by the explicit models)

1. **Play before the speech engine is ready stays silent forever.** The flag
   was on, the icon showed Pause, nothing was said, and nothing retried until
   the next step change. `LiturgicalTtsManager` now exposes `isReady`
   (new, additive) and the narration machine handles `EngineReady` by
   speaking the current prayer.
2. **A failing or refused utterance cut the narration state while the queue
   kept speaking** (any `onError` of the current generation cleared
   `isPlaying`/`currentlySpeaking` and forgot the final utterance id, so the
   real end was never recognised). `SpeechTracker` only ends a recitation on
   its last text part; errors on silences or middle chunks are ignored.
3. **`speak()` returning `ERROR` left `isPlaying` true forever.** It is now
   routed through the same error path.
4. **Stale utterance callbacks.** Previously guarded by a generation-prefix
   check scattered over three callbacks; now a late `onStart/onDone/onError`
   from a stopped recitation finds nothing registered and is ignored (tested).
   Note: there is no auto-advance in this app, so a stale callback could never
   advance the wrong step; the real exposure was the highlight and the
   playing flag, which is what is fixed.
5. **Spurious narration restarts.** The old 7-key effect re-spoke when keys
   that cannot affect the screen changed (mystery set while on the chaplet).
   The target is now normalised (`mysterySet = null` on the chaplet).
6. **State written during composition / split state.** Position, devotion,
   mystery selection and instructions are one saved value changed only by a
   reducer; indices are clamped when written, not when read.
7. **`currentlySpeaking = ""`** for a dialogue whose leader half is blank is
   now `null` (no visible difference: nothing matches a blank half).

Not changed (noted for the owner): the MYSTERY sheet still clears itself from
composition (`sheet = null`); narration is not restored after process death
(by design); after the narrator finishes a prayer the button still shows
Pause until pressed, because pray-aloud is a mode (as before).

Also changed outside the listed files, minimally:
`CatholicDataRepository.getTodayRosaryMystery()` now delegates to
`RosaryMysterySchedule` (same table, including the `else -> Joyful` fallback),
and `ViaCrucisDataTest.kt` line 43 had `Regex("\d")`, an illegal escape that
broke compilation of the whole unit-test source set at HEAD; it is now
`Regex("[0-9]")` (same meaning).

## 4. Tests added (pure JVM, no Robolectric)

`RosarySessionTest` (25): real guides built from `PrayerAndNovenaData`:
Rosary is 85 steps / 61 beads / 7 sections with section starts
`0,7,21,35,49,63,77`, 10 Hail Marys counted 1..10, closing on the medal,
the same shape for all four sets; chaplet is 68 steps / 60 beads; optional
flags (litany block 78-80, litany prayer 82; chaplet opening block 1-4 and
closing prayer); skip lands on "Pray for us", the collect, "Our Father", and
is a no-op with nothing required after; Next/Previous/GoTo clamping;
independent positions per devotion; decade-complete rule; weekday table
(Mon/Sat joyful, Tue/Fri sorrowful, Wed/Sun glorious, Thu luminous, fallback
joyful); by-day vs picked selection; pick resets only the Rosary and only when
the set changes; sticky instructions; `initialTab` range; Saver round trip.

`RosaryNarrationTest` (17): the narration state machine (play, pause, every
step change, set/devotion/language/voice change, duplicate target, leave,
engine ready, controller command order); `SpeechTracker` (single part,
dialogue highlight leader to response, stale callbacks, stop then late done,
error in a middle chunk, refused request, blank text); `ReelGesture` (drag end
select vs snap back, tap hit and slop).

Honesty note: the tests were written from the pre-refactor logic (read, not
run) in the same pass as the refactor, because the old logic was embedded in
composables and could not be exercised on the JVM before being extracted. The
expectations (85/68 steps, skip targets, section starts) are the plan's
documented behaviour and were confirmed against the real guides. The Robolectric
`RosaryInteractionTest` was not modified and is the end-to-end guard.

## 5. Results

| Check | Result |
|---|---|
| `tools/device/gradle.sh -q :app:compileDebugKotlin` | pass |
| `RosarySessionTest` (25) | pass |
| `RosaryNarrationTest` (17) | pass |
| `RosaryInteractionTest` (4, parameterised by design identity, Robolectric) | pass, unchanged |
| `tools/device/gradle.sh -q :app:assembleDebug` | pass (`app/build/outputs/apk/debug/app-debug.apk`, 63.6 MB) |
| Phone smoke check (play/pause, switch devotion, leave tab while narrating) | **not run**: `adb devices` listed no device. Needs a manual pass: audio must stop on leaving the tab, play-before-ready must now speak once the engine is up. |

Nothing is committed.
