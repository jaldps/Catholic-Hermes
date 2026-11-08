# How the redesign is built and judged

Read with `DESIGN.md`. Applies to every builder and critic agent.

## Machine limits (important)

The PC has 4 GB of RAM and 4 cores. Only **one** Gradle/Robolectric process
may run at a time, and only one agent may drive the phone at a time. Both
helpers below take a lock and wait their turn; never call `./gradlew` or
`adb` directly.

- Build / test / render: `tools/device/gradle.sh <gradle args>` (Java 17,
  no daemon, 2 workers, lock in `%TEMP%/catholic-hermes-gradle.lock`).
- Phone: `tools/device/adb.sh shot|tap|tapText|swipe|back|dump|launch|install`
  (Motorola Edge 60, 1220×2712 px, density 450; app id
  `com.aistudio.sanctafides.qvmrpx`).
- Images: `py -3 tools/device/montage.py sheet|pair ...`.

## Shared checkout rules

Several builders edit this checkout at the same time.

- Edit **only the files you own** (listed in your brief). Shared design-system
  files (`ui/design/*`, `ui/theme/*`, `MainApp.kt`) belong to the foundation
  owner; ask the orchestrator for a change instead of editing them.
- Keep the tree compiling: make an edit, build right away, fix your own
  errors immediately. Never leave a file half-edited while you go away.
- If the build fails only in files you do not own, another agent is
  mid-edit: wait ~60 s and retry (up to 10 times) before reporting.
- Do not commit, push, create branches or worktrees. The orchestrator
  commits.
- Preserve every `testTag`, content description, deep link and data source.

## Commands

Compile + APK:

```bash
tools/device/gradle.sh -q :app:assembleDebug
```

Render screens (Robolectric, 411×891 dp at 2×; first screen of each
destination, light and dark):

```bash
tools/device/gradle.sh -q :app:testDebugUnitTest --tests 'com.example.screenshots.FullAppScreenshotTest' -Pshots.only=home,prayers -Pshots.lang=es
```

Output: `app/build/screenshots/app/<destination>_<identity>_<light|dark>.png`.
Destinations: home, bible, rosary, prayers, habits_virtues, more, catechism,
doctrine, questions, study_doctrine, study_mary_jesus, study_history,
study_orders, spiritual_life, parishes, pilgrimage_habits, churches_news.
`-Pshots.height=1600` renders a taller viewport to see more of a screen.
Renders approximate blur and font hinting; the phone is the final truth.

Timing on this PC: a one-destination render took ~9.5 min cold (all
identities × light/dark); `assembleDebug` ~2 min incremental. Builds queue
behind other agents. So batch your changes, render only your destinations
(and only one identity once `-Pshots.identity` exists), and prefer the
phone (`assembleDebug` + install + `shot`) for fast iteration, including
scrolled states and sub-screens the renders cannot reach.

Behaviour tests worth running for your area: `AppNavigationTest`,
`DesignNavigationTest`, `RosaryInteractionTest`, `DesignContrastTest`,
`FontPickerInteractionTest`, `RemindersTest`.

Phone check (after `assembleDebug`):

```bash
tools/device/adb.sh install app/build/outputs/apk/debug/app-debug.apk
tools/device/adb.sh launch
tools/device/adb.sh tapText "Oraciones"; tools/device/adb.sh shot docs/redesign/after/prayers.png
```

The phone follows the system dark/light setting; to check light mode run
`MSYS_NO_PATHCONV=1 /c/Users/Edgli/AppData/Local/Android/Sdk/platform-tools/adb.exe shell cmd uimode night no`
and switch back with `night yes` when done (inside the lock: prefix it with
`tools/device/adb.sh` commands only; keep phone sessions short).

## The loop

1. Builder implements, renders its screens (light + dark, ES), looks at them
   itself and fixes the obvious first.
2. Builder writes `docs/redesign/reviews/<area>-roundN-builder.md`: what
   changed, render paths, known gaps.
3. The orchestrator gives a **separate critic** agent a blind pair (our
   screen vs a reference panel or a top Apple app screen, sides randomised)
   plus the checklist in `DESIGN.md`. The critic scores 1–10, says which side
   is better and lists every defect.
4. The builder fixes every defect. Repeat until the critic scores ≥ 9.5 on
   every screen in light and dark and prefers our side, or calls it a tie
   with the reference, in the blind comparison.
