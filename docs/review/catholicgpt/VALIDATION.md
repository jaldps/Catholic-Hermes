# Validation — CatholicGPT 1.4

Full preview build and complete test suite: **134 passed, 0 failures, 0 errors, 0 skipped**. Machine-readable class counts are preserved in [test-results.json](test-results.json).

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache -I tools/preview.gradle :app:assembleDebug :app:testDebugUnitTest
```

## Behavior covered

- All five tabs, canon selection, Home → Mass, More → Catechism/Habits/Parishes and back, reachable settings, persisted family selection, and actual Bible text following the global family before an explicit reader override.
- Named font families across all theme roles and identities; real selector interactions in light/dark; reopening the preference store; legacy IDs; preserving a newer font when an older appearance draft is saved; closing the picker immediately after selection.
- Rosary and Divine Mercy retain independent positions. Next/Back follow the current prayer, and long Spanish labels remain whole and reachable at 320dp with enlarged text.
- Vatican navigation retains complete English captions at 320dp with Roboto at 130%, and Spanish captions at the regular viewport, in both modes. Actual text layout, visible destinations, selection and touch targets are checked.
- Ordinary Time weekday/week boundaries, November 3, 2026's Thirty-First Week title and `ot_31_day2`, feast and Advent keys, and weekday/Sunday consistency across 2000–2100.
- Existing scripture, references, lectionary, content and navigation tests remain in the suite. No database schema or scripture/prayer assets were changed.

## Render coverage

The full-app harness renders all 12 destinations × 3 identities × 2 modes: 72 default English PNGs. Focused Spanish and Portuguese runs each pass all 6 render methods and produce 30 root-screen images. The 320×760dp Roboto 130% run also passes all 6 methods and produces 30 images across the five root tabs. The illustration gallery and six font-dialog interaction captures are retained.

All app captures use native Canvas on the actual Compose tree. The harness waits for Compose, disposes each screen before the next, and freezes decorative motion for still images. Native Robolectric/Compose workers are isolated per test class; idleness checks and assertions remain enabled. Earlier failed runs exposed an outdated preview-name expectation, missing capture synchronization, lifecycle overlap and an animated gallery that never became idle; these were corrected rather than skipped.

Some Home frames show loading placeholders for online weekday Mass readings. The environment blocks the Vatican feed, so these frames do not verify live reading retrieval. Bundled Bible content and reading interactions are covered separately. Review reports identify their own dated inputs; earlier scores and images are not approval of every later change.

## Android installer

The [preview APK](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/CatholicGPT-preview.apk) is version `1.4-CatholicGPT`, versionCode `5`, launcher name **CatholicGPT**. Its separate package is `com.aistudio.sanctafides.qvmrpx.catholicgpt`. It updates the previous CatholicGPT preview with the same signing key and retains that installation's data. The historical Sancta Fides app has independent data.

APK structure, package/version/label and signature were verified with SDK tools. SHA-256: `c63068d73499bcef7353025b19474e9523e22e7d33c98e7440db3078cf59671d`. [APK.json](APK.json) contains the package and signing-certificate metadata. The historical `dist/sancta-fides-1.3.apk` is unchanged.

Physical-phone installation, narration output, every device/accessibility combination and live reference-site comparison have not been verified. Independent critics still find a gap to the requested visual quality; see the [review summary](README.md) and raw reports.
