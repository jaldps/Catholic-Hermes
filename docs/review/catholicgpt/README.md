# CatholicGPT UI

Current direction: Apple Notes / Linear restraint. Clear tasks, fewer words, standard components, less custom code. Apple, Material and Vatican retain their own typography, surfaces and navigation.

## Current implementation

- Removed the decorative introduction banners and their unused drawing engine. The former illustration-only screenshot gallery was removed with that component; app screenshots and content/interaction tests remain.
- Apple uses a familiar sans-serif large title and a flat liturgical information card instead of fabric, gradients, embroidery and bespoke drawing.
- Material uses standard `ListItem` rows instead of custom row measurement, inferred avatars and decorative shape code.
- Vatican keeps its serif/rubric language without repeated branding, title ornaments or ceremonial separators.
- Home opens on the date and celebration, followed by the reading and its action. Nearby churches is one action rather than a clickable card plus duplicate button.
- More has direct destination rows. Its data model no longer stores explanatory subtitles; the chosen language remains a useful current value.
- Removed introductory taglines, repeated prayer aliases and generic helper copy. Actual prayer text, scripture, references, progress, language state, accessible labels and actionable errors are retained.
- Bible exposes its canon filters; translated names wrap intact. Search, versions and reader controls retain their existing tags and behavior.
- Rosario puts prayer text and transport together; instructions remain available through a disclosure. The two devotions keep independent positions.

## Review evidence

The raw round 1 and 2 reports in this directory describe earlier, more decorative versions. They are retained as evidence, not as the specification for this version. Three independent critics compared randomized A/B screenshots and also reviewed all secondary destinations. None claimed the earlier versions were exceptional. Their feedback led to the current task-first simplification.

Live reference comparison remains unavailable: the environment proxy denied the reference sites, and no managed visual browser was available. No report should be read as a side-by-side comparison with the current Apple Notes, Linear, Material or Vatican websites.

Exact test results, the known date-dependent baseline assertion, and final screenshot coverage are recorded in `VALIDATION.md`.

## Development

```bash
source /workspace/.cloud-setup/env.sh
cd /workspace/Catholic-Hermes
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache :app:assembleDebug :app:testDebugUnitTest
```

Keep the shared Gradle lock. Main screenshot output: `app/build/screenshots/app`. Use `--tests com.example.screenshots.FullAppScreenshotTest -Pshots.only=home,bible,rosary,prayers,more` for a focused review, and `-Pshots.lang=es` or `pt` for translations.

New regression coverage includes kit-level navigation and touch targets, full-app navigation, independent Rosary/Divine Mercy steps, and Vatican text contrast. The older app screenshot harness now freezes decorative animations consistently with the main harness.

The generated APK uses the environment's debug key. The historical signed APK in `dist/sancta-fides-1.3.apk` is unchanged. Installation over that historical APK and real-device behavior have not been verified.

## Current previews

Each sheet shows Apple, Material and Vatican in that order, rendered from the actual Compose tree at the same phone size:

- [Home, light](previews/home-light.png) · [dark](previews/home-dark.png)
- [Bible, light](previews/bible-light.png) · [dark](previews/bible-dark.png)
- [Rosary, light](previews/rosary-light.png) · [dark](previews/rosary-dark.png)

The simplicity round's restrained variants won all 30 blind comparisons. Final focused reviews found no critical visual defects at the supplied viewport; the critics still did not claim universal perfection. Those image reviews do not establish all device sizes, accessibility scales or real-device behavior.
