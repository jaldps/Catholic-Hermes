# CatholicGPT UI

CatholicGPT keeps three visual identities behind one component contract: Apple, Material and Vatican. This iteration restores meaningful sacred illustrations, makes font selection persistent, and improves reading and navigation without changing the scripture or prayer data.

## Current behavior

- More → Font offers the identity default, Inter, Roboto and EB Garamond. Each named option previews its bundled family. The same selector is available under Appearance. Selection updates the interface immediately and survives reopening the app; appearance saves preserve the current selection.
- Bible and Catechism follow the app font by default. Their local reading control cycles Default → Serif → Sans → Default; local text size remains independent.
- Illustrations use luminous volume for Apple, flat tonal geometry for Material and engraved linework for Vatican. They clip to their frame and hold still for reduced motion. Apple header light fades before its frame rather than exposing a square crop.
- Material composes the title, date and illustration inside one flexible app bar. Apple places the illustration alongside its large title; Vatican retains an engraved page opening. Search and actual content keep priority.
- Rosary and Divine Mercy retain separate guide positions, prayer content, bead jumps and narration. Next is primary. Enlarged text and long translated labels move navigation into two rows when one cannot fit.
- Ordinary Time weekdays now retain the week of their preceding Sunday. November 3, 2026 correctly resolves to the Thirty-First Week and `ot_31_day2`; feasts and Advent boundaries retain their own rules.
- Five labeled destinations, canon selection, current values, accessible names and stable test tags remain available. Apple control targets and Material navigation use measured layouts that accommodate text sizing.

## Research and review

[DESIGN-SOURCES.md](DESIGN-SOURCES.md) distinguishes official material actually read from blocked websites and design interpretation. Official Apple sample source and Google Material component guides were inspected. Genuine Google Now in Android screenshots were obtained from its official repository; the comparison is recorded separately.

The current independent reviews are in [current-reviews](current-reviews/). Scores remain roughly 7–8/10: the critics do **not** consider the app exceptional or universally perfect. The Google comparison preferred the reference app's hierarchy and cohesion. The [Google comparison addendum](current-reviews/google-reference-comparison-final.md) confirms that integrated headers improve access by roughly 65–75dp; Google still leads overall. Later fixes address dark Apple compositing, Vatican font-selection color, reader font overrides and narrow prayer controls; reports name the earlier inputs they actually judged.

The [final navigation recheck](current-reviews/final-layout-qa-recheck.md) confirms that complete Vatican captions fit all reviewed narrow and Spanish root screens after the measured type adjustment. This resolves the clipping recorded in the preceding layout report.

The older `critic-*.md` files at this directory's root concern previous decorative or stripped-down versions. Their scores and wins are historical, not acceptance for the current implementation. Randomized A/B reviews compare our iterations; they are not a controlled blind test against Apple Notes or Google production apps.

Live Apple HIG, Material, Dribbble and Vatican sites remain blocked by the cloud proxy. Full execution access does not change that separate policy. No current Apple Notes/Vatican live comparison or physical-phone verification is claimed.

## Development

```bash
source /workspace/.cloud-setup/env.sh
cd /workspace/Catholic-Hermes
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache :app:assembleDebug :app:testDebugUnitTest
```

Always use the Gradle lock. Main screenshots are generated in `app/build/screenshots/app`; font dialogs in `app/build/screenshots/fonts`. Options include `-Pshots.only=home,bible,rosary,prayers,more`, `-Pshots.lang=es|pt`, `-Pshots.font=ROBOTO`, `-Pshots.scale=1.3`, and `-Pshots.width=320 -Pshots.height=760`.

Current validation and limitations are in [VALIDATION.md](VALIDATION.md). The [Android preview](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/CatholicGPT-preview.apk) uses a separate package and the CatholicGPT launcher name. Updates to this preview retain its data; the historical Sancta Fides installation has independent data.

## Rendered previews

Sheets show Apple, Material and Vatican in that order, rendered from the actual Compose tree:

- [Home, light](previews/home-light.png) · [dark](previews/home-dark.png)
- [Bible, light](previews/bible-light.png) · [dark](previews/bible-dark.png)
- [Rosary, light](previews/rosary-light.png) · [dark](previews/rosary-dark.png)

Rendered frames do not establish narration output, every scroll state, all accessibility settings or real-device behavior.
