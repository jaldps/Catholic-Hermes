# Validation — CatholicGPT

Final debug build and complete test suite: **107 passed, 0 failed, 0 errors, 0 skipped**.

- Command: `flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache :app:assembleDebug :app:testDebugUnitTest`
- The four new regression classes contribute 13 tests: full-app navigation (3), kit navigation/actions in both modes (6), Rosary/Divine Mercy state (3), and Vatican contrast (1).
- Full-app checks include all five tabs, canon selection, Home → Mass, secondary destinations and back behavior, and reachable settings/current language.
- Localized focused runs: 9/9 passed for Spanish and 9/9 for Portuguese; each includes the full-app navigation checks and the six screenshot methods.
- Main render coverage: 72 English images (12 destinations × 3 identities × 2 modes), plus 30 Spanish and 30 Portuguese images for the five root tabs.
- APK signature verified with SDK `apksigner`. Debug APK SHA-256: `9defdbfec7291f44638d0aeae1276034bc4a43e566129bcc1679ef05fa43dafe`.

## Test reliability

The inherited integration test asserted that today's celebration was green. On November 2 the app correctly returns violet, so the original suite failed. The test now checks that the current result reports today's day number, and asserts colors independently for fixed dates: October 29, 2026 (green) and November 2, 2026 (violet). No calendar production logic was changed. No tests were skipped to achieve a passing run.

The removed six-test illustration gallery covered the deleted decorative drawing engine. All content, app, navigation and remaining screenshot suites were retained. The older app screenshot harness was made deterministic by freezing decorative animations consistently with the main harness.

## Scope and limitations

Production UI diff versus the source branch: 734 lines added, 3130 removed (2396 net lines removed). Existing dependency declarations, database schema and historical APK are unchanged.

Reviewers preferred the simpler version in all 30 blind pairs. Final focused visual reviews found no critical defects at the rendered phone viewport. This is not proof of perfection, all font scales, all scroll states or real-device behavior. No emulator/device installation or live benchmark-site comparison was performed.

The downloadable preview is a separate, side-by-side package, `com.aistudio.sanctafides.qvmrpx.catholicgpt`, version `1.3-CatholicGPT`. It preserves the historical app and uses independent app data. Its build and APK signature were verified; no physical-phone installation was performed. Preview SHA-256: `ba7deab2a0cb13b2d7974a0156c3ef44e2d78d8e1fae855bd19c48ef3f527d87`.

Build this variant with `flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache -I tools/preview.gradle :app:assembleDebug`. The init script only affects explicitly requested debug preview builds; the normal application ID is unchanged.
