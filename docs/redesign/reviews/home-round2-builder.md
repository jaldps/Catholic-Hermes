# Home (Hoy) — round 2 (builder)

Starting point: the earlier round-2 pass had already landed most of the work in
`HomeScreen.kt`, `LumenKit.kt` (dock), `LumenComponents.kt` (hero) and
`DevotionalGlyphs.kt`. This pass audited it against the critic's top 10 with
fresh renders and fixed what was still off.

## Critic top 10 — status

| # | Critic item | State | Where |
|---|---|---|---|
| 1 | Tab bar: "Oraciones" clip, opaque/frosted material, fade under bar, bottom padding | Done. One shared label size (11sp, -0.2 tracking, maxLines 1, steps down to a 9.5sp floor), near-opaque warm sheet (light `#F2F0ED` ~94%, dark `#1E1C1A` ~92%) with lit edge, 20dp canvas fade above the dock, list bottom = dock inset + 32dp. Owner's 6 tabs, labels and order untouched. | `LumenKit.kt` `Dock`, scaffold fade |
| 2 | Text bugs: doubled «, space before `.»`, mid-word ellipsis, en dash ranges, one lectionary source | Done. `cleanScripture`, `balanceQuotes`, `clauseCut`/`ExcerptText` (cuts at a clause/word, never mid-word), `scriptureRef` ("Salmo 51, 10–12"). One source for both themes: the bundled lectionary, else the Vatican feed. | `HomeScreen.kt` |
| 3 | Repetition | Done. Season card = "Tiempo Ordinario" + one line "Martes de la semana XXXII" (`seasonDetail`); Mass card has no subtitle; "Regla de vida" removed from "Oración del momento" (now only Fidelity). | `HomeScreen.kt` |
| 4 | Mass card around the Word, one primary action | Done. Gospel excerpt in reading type (max 3 lines), first reading as one footnote row, reflection as a disclosure row, ONE charcoal/ivory "Leer la Misa" capsule with the glass play circle beside it (header play removed, no icon in the capsule). Reflection fallback now has an eyebrow. | `HomeScreen.kt` |
| 5 | Rhythm 12/32/12, season card overlaps the hero fade, Meditación not an orphan | Done. `ClusterGap` 12dp, `SectionLead` 22 + 10 header = 32dp, `AppHeroHeader(overlap = 24.dp)`; Meditación is a row inside the Mass card. | `HomeScreen.kt`, `LumenComponents.kt` |
| 6 | Hero crop and dark treatment | Done. Crop tightened this pass (manifest zoom 1.3 -> 1.65, anchor higher) so the cross reads ~1.3x larger on the right third. Dark: about -0.6 EV warm grade, 35% black top scrim over 120dp, long fade so the eyebrow sits on >= 85% canvas, eyebrow in ink @ 82%. | `AppHeroImage`, `heroes/lumen/manifest.json` |
| 7 | One icon family | Done. Outline 22dp ink everywhere on Home; new outline rosary and sunrise glyphs replace the "search+" and filled sunrise; no green hint icon, no filled quote. | `DevotionalGlyphs.kt` |
| 8 | Type roles, season dot alignment | Done. Eyebrow 13/500, card titles headline, section headers title3, reading type for scripture, callout for body. | `HomeScreen.kt` |
| 9 | Glass | Done. Cards glass + lit edge + soft directional shadow; bell is a 40dp glass circle (44dp target) at status inset + 6dp; CTA shadow softened, none in dark. | `LumenComponents.kt` |
| 10 | Fidelity empty state + end of scroll | Done. Ring 0/12, "Tu regla de vida", one line, "Empezar mi regla" secondary capsule; `FidelityCalendar(counts, dailyGoal = DailyRule.size, framed = false)`; scroll ends dock + 32dp clear. | `HomeScreen.kt` |

## Changed this pass

- `heroes/lumen/manifest.json`: `home.jpg` zoom 1.3 -> 1.65, anchorY 0.40 -> 0.36 (cross larger, still on the right third above "Hoy").
- `HomeScreen.kt`: eyebrow "Reflexión" above the homily fallback when no Gospel is available.
- `FullAppScreenshotTest.kt` (harness only): wait 2.5 s before capture. The first render in a JVM captured the dark canvas without the hero because bundled art decodes asynchronously; this was a harness race, not a product bug.

## Evidence

Phone was not connected (`adb.sh dump` empty), so renders (ES, 411x1600dp):
`docs/redesign/after/home_r2_light_render.png`, `docs/redesign/after/home_r2_dark_render.png`.

## Known gaps

- No phone verification (device offline).
- When the bundled lectionary lacks the day and the device is offline, the Mass card shows the reflection fallback instead of the Gospel (the dark render, which had no network, shows this).
- Fidelity at the bottom of the 1600dp render sits under the dock because the render crops the scroll; on a phone the scroll end clears the dock.
