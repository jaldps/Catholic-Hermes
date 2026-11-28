# Working on CatholicGPT

- Work in the existing checkout; do not create worktrees unless requested.
- **Current UI direction (owner, November 28, 2026): one single identity, Catholic1.** The spec is `docs/design/CATHOLIC1.md`.
  - Look: navy, gold and ivory, opaque surfaces, and a brand banner with the owner's background, Virgin and logo.
  - Appearance: Day / Night / System only.
  - Lumen, Tradicional, Cristal and Sanctuary are retired. Every stored identity id reads as `CATHOLIC1`, so keep that compatibility.
  - The liturgical colour appears only on the dock's icons. The «Color de los iconos de la barra» setting switches between Litúrgico and Del tema.
  - The gold action fill carries navy text, never white.
  - Keep clear tasks and concise copy, and opaque reading surfaces.
- Single header: every screen uses `AppHeroHeader` / `AppDetailHeader` / `AppLargeTitleHeader`.
  - The Catholic1 banner is 176 dp, status bar included, on every screen.
  - Its wave edge is clipped, never patched.
  - The title sits below the banner: `largeTitle` for section roots and `title1` below them.
  - Do not add per-screen header heights or title styles.
- Keep the identity behind `ui/design/AppKit.kt`. Screens call the wrappers in `AppComponents.kt`; avoid parallel navigation, content or component systems.
- Preserve real scripture/prayer content, reading references, progress, current values, accessibility labels and stable test tags. Decorative or redundant UI can be removed without deleting the underlying data. Each destination appears once per screen.
- Illustrations must complement the task, remain clipped to their frame and never obscure text or controls. Font selection must be visible, persistent and effective.
- Titles use Plus Jakarta Sans (OFL); UI uses Inter; reading uses EB Garamond.

## Working rules (owner, November 28, 2026)

- **No long test suites.** Do not run `testDebugUnitTest` broadly or the full suite, which takes hours. Compiling (`assembleDebug` / `compileDebugKotlin`) is enough.
- **Screenshots are allowed and expected.** Use the screenshot tests only to produce images. Captures from the owner's phone over `adb`, when available, are better.
- **Critic always.** Every design phase, screen batch and release candidate goes through an independent critic (Opus, read-only) before integration. The critic applies the `.claude/skills` design skills (`better-*`, `impeccable-native`, `baseline-ui`, `apple-design`, `dxe-android-native-ui`) to the captures. Its findings are reported to the owner, even small ones.
- **Keep working.** The coordinator keeps an hourly review scheduled and works without waiting for the owner, except on design or product decisions.

## Verification

Java 21, Gradle 9.3.1, Android SDK 36.1. In the prepared cloud environment:

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache :app:assembleDebug
```

Always use the shared Gradle lock.

**Captures:** use `screenshots.FullAppScreenshotTest`, with renders in `app/build/screenshots/app`.
- Narrow a run with `-Pshots.only=home,bible,rosary,prayers,more` and `-Pshots.lang=es|pt`.
- `appleLight` and `appleDark` now render Catholic1 by day and by night.
- Restore regenerated tracked PNGs with `git checkout -- docs/tasks`.

Read `docs/review/catholicgpt/README.md` and `VALIDATION.md` for current evidence and limitations. Earlier review reports and the original briefing describe previous iterations; do not treat historical ratings or build results as current validation. Do not claim live website comparison or real-device verification without evidence.

Keep production signing and application IDs and the historical APK intact. Screenshot captures must await Compose and freeze decorative motion; native test workers are isolated per class to avoid shared runtime state.
