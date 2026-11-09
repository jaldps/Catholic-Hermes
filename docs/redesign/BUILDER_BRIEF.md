# Builder brief (read first)

You are an area builder in the **Lumen** redesign of the Catholic Hermes
Android app (Jetpack Compose), repo `C:\Users\Edgli\Catholic-Hermes`, branch
`ui-redesign-v2`, Windows + Git Bash. The goal is Apple first-party quality
(iOS 26 era: Apple Music, Journal, Fitness, Books). Not a reskin.

Read, in this order:
1. `docs/redesign/DESIGN.md` — the design target and quality checklist.
2. `docs/redesign/PROCESS.md` — machine limits, locks, commands, the loop.
3. `docs/redesign/COMPONENTS.md` — the Lumen component API you code against
   (`ui/design/Lumen*.kt`, wrappers in `AppComponents.kt`).
4. `docs/redesign/plans/<your area>.md` — your area's audit and approved plan.
5. `docs/redesign/reviews/<your area>-*.md` if present (builder notes,
   critic reviews).
Reference concept: `docs/redesign/reference.png` and crops in
`docs/redesign/reference/`. Before state: `docs/redesign/before/`.
PD art: `app/src/main/assets/heroes/lumen/` and `heroes/mysteries/` with
`manifest.json` (focus points, tone); credits in `docs/redesign/IMAGES.md`.

## Where things stand

A previous builder for your area may already have edited your files (see
`git diff 9606fca -- <your files>`). Continue from the current code and the
plan: finish what is half-done, don't restart from scratch, don't revert
other areas' work.

Foundation notes:
- Call `AppEdgeToEdge()` at the screen root and pad list bottoms with its
  dock inset.
- Use `AppBackHandler` (single glass back button, tag `nav_back`) instead of
  `InlineBackLink`.
- Use `styles.scripture` for text that must follow the reader-font
  override; the shared Aa reading sheet lives in `LumenExtras.kt`.
- Glass has no real blur (translucent fill + lit edge + soft shadow).

## Rules

- Edit only your owned files (listed in your task) plus any extra file the
  task explicitly grants. Shared design files (`ui/design/*`, `ui/theme/*`,
  `MainApp.kt`) are not yours: if you need a change there, build a private
  equivalent in your own file and list the request in your builder note.
- Build ONLY with `tools/device/gradle.sh` (one Gradle at a time on a 4 GB
  PC; it waits for the lock). Batch your edits; a compile is ~2–4 min, a
  render ~5–10 min. Use `-Pshots.only=<your destinations> -Pshots.identity=apple`.
- If the compile fails only in files you don't own, another agent is
  mid-edit: wait ~60 s and retry (up to 10×). Fix your own errors at once.
- The phone (Motorola Edge 60, dark mode) is reached only via
  `tools/device/adb.sh`; keep sessions short; restore `night yes` if you
  switch to light.
- Preserve every `testTag`, content description, deep link, data source and
  piece of real content. Update tests only where they encode the old design,
  keeping their intent, and say so.
- No commits, pushes, branches or worktrees.

## Done means

Your screens look like Apple shipped them, in light AND dark, in ES (with
EN/PT lengths checked), and your area's tests pass. Then write
`docs/redesign/reviews/<area>-roundN-builder.md` (what changed, render and
phone screenshot paths for every screen/state, known gaps, inventory
checklist ticked) and reply with a short report. A harsh blind critic
reviews afterwards and you may be asked to iterate.
