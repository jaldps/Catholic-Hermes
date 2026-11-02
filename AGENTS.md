# Working on CatholicGPT

- Work in the existing checkout; do not create worktrees unless requested.
- Current UI direction: task-first simplicity. Use concise destination names and standard controls. Do not add taglines or explanatory subtitles to compensate for an unclear layout.
- Keep three identities behind `ui/design/AppKit.kt`: Apple, Material and Vatican. Screens call the wrappers in `AppComponents.kt`; avoid new parallel component systems.
- Preserve real scripture/prayer content, reading references, progress, current values, accessibility labels and stable test tags. Decorative or redundant UI can be removed without deleting the underlying data.
- Prefer deleting unused rendering code over keeping it for a gallery alone. App screenshots and behavior tests are the validation targets.

## Verification

Java 21, Gradle 9.3.1, Android SDK 36.1. In the prepared cloud environment:

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache :app:assembleDebug :app:testDebugUnitTest
```

Always use the shared Gradle lock. Focused UI checks: `AppNavigationTest`, `DesignNavigationTest`, `RosaryInteractionTest`, `DesignContrastTest`, and `screenshots.FullAppScreenshotTest`. Main renders are in `app/build/screenshots/app`; use `-Pshots.only=home,bible,rosary,prayers,more` and `-Pshots.lang=es|pt` for focused/localized runs.

Read `docs/review/catholicgpt/README.md` and `VALIDATION.md` for current evidence and limitations. Earlier review reports and the original briefing describe previous iterations; do not treat historical ratings or build results as current validation. Do not claim live website comparison or real-device verification without evidence.
