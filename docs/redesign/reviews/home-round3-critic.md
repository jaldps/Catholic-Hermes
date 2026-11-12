# Home (Hoy) — Round 3 critic review (blind pairs)

Files: `docs/redesign/blind/home_r3_light.png`, `docs/redesign/blind/home_r3_dark.png`.

**Identification:** the concept is light A and dark B. It is the same iPhone
mock as before, so it scores 7.0 for the same reasons as in rounds 1 and 2.
The shipping app is light B and dark A.

**Measurement basis:** about 0.69 px/dp, ±2dp. Colours are sampled from the
PNGs.

## Verdicts

| Pair | Winner, margin | Shipping | Concept |
|---|---|---|---|
| Light | **B (shipping)**, about 1.8 | **8.8** | 7.0 |
| Dark | **A (shipping)**, about 1.7 | **8.7** | 7.0 (light mock) |

The screen is now composed. It has one gutter, three cards that share one
header pattern, the Gospel above the fold, a calm dark hero and a correct
7% glass in dark. What keeps it from 9 is a handful of visible details.
Fidelidad has also become a second Hábitos screen.

## Round-2 items: status

| # | Item | Status |
|---|---|---|
| 1 | Verse card dominance | **Fixed.** Clamped to 4 serif lines, about 217dp. |
| 2 | Canvas band above hero | **Fixed.** The hero starts at y = 0 in both themes. |
| 3 | Dark sky too bright | **Mostly fixed.** The top samples `#464038`. The horizon band behind the cross is still about `#706955`. |
| 4 | Rosary glyph, icon sizes | **Open.** The teardrop/♀ glyph is still used for Coronilla and the Rosario tab. The bell glyph is about 14dp; row icons are about 18dp. |
| 5 | Glass material | **Dark fixed.** The card samples `#21201F`, which is 7%, and the edge is visible. **Light open:** cards are `#FBFBFA`, near-opaque, with no lit edge. |
| 6 | Card header grammar | **Fixed.** Title on the left, reference on the right, no icons, for both Misa and Versículo. |
| 7 | Dead "Primera lectura" line | **Fixed.** It is now a row with a chevron. |
| 8 | No fade above the tab bar | **Fixed.** The calendar row fades out under the bar. |
| 9 | Fidelidad ring and empty-state action | **Partial.** The dark track (`#41403F`) now reads. The light track (`#E4E4E3` on `#FBFBFA`) is still about 1.1:1. An action was added, along with too much else (see defect 1 below). |
| 10 | Season card overlap with the hero | **Partial.** The gap from "Hoy" to the card is about 16dp, and the card now sits on the fade, but it does not overlap it. |
| 11 | Bold count | **Open.** "Tu regla de vida" is still 600. |
| — | Real ellipsis character | **Open.** "rebaños...»" and "serafines..." still use three dots. |

## Remaining defects (shipping), by visual impact

1. **Fidelidad is overloaded.** The card holds a ring, copy, the "Empezar mi
   regla" capsule, a full-width "Vencí una tentación" capsule, "Este mes", and
   a month calendar. It is now the tallest block on Home (about 400dp or
   more), and it duplicates Hábitos.
   - The full-width secondary button outweighs the actual next step ("Empezar mi regla").
   - Fix: keep the ring, the copy and one capsule.
   - Move "Vencí una tentación" and the month calendar to Hábitos.
   - If a calendar must stay on Home, use a 7-day week strip of 56dp or less.
2. **Rosary glyph and icon sizes** (carried over from round 2).
   - Draw a crucifix on a bead loop, 22dp, 1.5dp stroke, for Coronilla and the Rosario tab.
   - Bell glyph 20dp. Row leading icons 22dp.
   - Ángelus: a plain 3-ray sunrise.
3. **The verse hint is truncated.** "Hoy relee este versículo despacio tres
   veces..." breaks the no-truncation rule.
   - Fix: rewrite it to fit one line, e.g. "Reléelo despacio, tres veces.", in footnote 13 inkSecondary.
   - Use U+2026 (…) for every excerpt ellipsis.
4. **Secondary capsules.** Light: white capsules on a white card are
   separated only by a drop shadow, which is the neumorphic look. Dark: grey
   capsules with a black drop shadow.
   - Light fix: fill black @ 5% (or glassStrong with the 1dp lit edge), and no shadow.
   - Dark fix: fill white @ 12% with a 14%→4% edge, and no shadow.
   - Height: 44dp.
5. **Light glass and the light ring.**
   - Cards: white @ 72%, with a 1dp lit edge going from 95% to 35%, and shadow `#3B2F22` @ 8%, y 10, blur 30.
   - Ring track: black @ 8%, 6dp stroke.
6. **Dark horizon.** Pull the band behind the cross down by 0.3 EV, to
   `#5A5446` or darker. The cross must stay legible.
7. **Season card and hero.** Pull the card up so its top sits 24dp into the
   fade.
8. **Bell position.** It is about 28dp from the capture top, and the capture
   has no status bar. Verify on a device that it sits at status inset + 8dp.
9. **Bold.** Set "Tu regla de vida" to 500.

---

**ITERATE.** Light passes: shipping wins, 8.8 vs 7.0. Dark is 8.7, below 9.
Top fixes:

1. Cut Fidelidad down to ring, copy and one capsule. Move "Vencí una tentación" and the calendar to Hábitos.
2. Draw a real rosary glyph. Bell glyph 20dp; row icons 22dp.
3. Rewrite the verse hint as one line with no ellipsis. Use U+2026 in excerpts.
4. Secondary capsules: no shadow. Light fill black @ 5%; dark fill white @ 12% with an edge.
5. Light glass edge and ring track at black @ 8%. Dark horizon −0.3 EV.
