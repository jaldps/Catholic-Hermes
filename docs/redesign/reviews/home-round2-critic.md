# Home (Hoy) — Round 2 critic review (blind pairs)

Reviewer stance: ex-Apple HI design lead, iOS 26 bar. I ignored the sharpness
of the upscaled side, and I did not penalise the bottom crop.

Files: `docs/redesign/blind/home_r2_light.png`, `docs/redesign/blind/home_r2_dark.png`.

Identification: the iPhone-framed mock is the concept (light **B**, dark
**A**). It is unchanged from round 1. The Android capture is the shipping app
(light **A**, dark **B**).

Measurements: the viewport is about 283 px wide, which is about 0.69 px/dp.
Every dp value below is ±2dp. Colours were sampled from the PNGs.

---

## Pair 1 — light

**Verdict: A wins by about 1.5.** A is now a calm, coherent system with one
gutter, one CTA, serif scripture and a quiet tab bar, and the Word is the
first real content. B is still a soft-UI poster: neumorphic halos, a 60pt
bold title, a glossy dot, 4 unlabelled tabs and nothing below the Gospel.

| Side | Score |
|---|---|
| A (shipping) | **8.4** |
| B (concept) | **7.0** |

## Pair 2 — dark

**Verdict: B wins by about 1.3.** B is a real night composition: a warm,
misted hero, glass at a low fill, an ivory CTA and no green. A is the light
concept, which has no dark mode.

| Side | Score |
|---|---|
| A (concept, light) | **7.0** |
| B (shipping, dark) | **8.3** |

---

## Shipping app — remaining defects (by visual impact)

1. **The verse card outweighs the task.** "Versículo del día" sets 9 lines of
   19/30 serif (Isaías 6, 1–3 is a passage, not a verse). It is about 405dp
   tall, against about 320dp for the Mass card. The secondary card is the
   largest object on the screen, and that dilutes the "one task" read.
   - Fix: show one verse, or clamp the text to 4 lines with a 28dp canvas-to-card fade on the last line.
   - Make the whole card a tap target that opens the full passage.
   - Target height: 220dp or less.
2. **A band of canvas sits above the hero.** The first about 12dp of the
   viewport is flat `#171614` (dark) or `#F6F4F1` (light), and the painting
   starts below it.
   - In dark this is a hard dark strip above a mid-brown sky, which is exactly the "black band" DESIGN.md forbids.
   - Fix: draw the hero from y = 0 under a transparent status bar.
   - Put the bell at status inset + 8dp.
3. **The dark sky is still the brightest area on the screen.** The sky samples
   about `#867B6A` (about 50% luminance) over a `#0E0D0C` canvas: a light slab
   at the top of a dark screen.
   - Fix: −0.5 EV more on the dark variant only.
   - Add a top scrim of `#000` going from 35% to 0% over the first 120dp.
   - Target sky value: `#4A4439` or darker.
   - Keep the fade to canvas starting at 45% of the hero height. The fade itself is now good.
4. **The rosary glyph reads as a light bulb, or as ♀.** It is used for both
   "Coronilla de la Misericordia" and the Rosario tab: a teardrop bead on a
   stem. Round 1 called it "search +"; it is still not a rosary.
   - Fix: draw a crucifix hanging from a short bead loop, 22dp, 1.5dp stroke.
   - Ángelus: the dashed-ray sunrise is fussy. Use a plain sunrise (3 rays) or a bell.
   - Leading icons in rows measure about 18dp. Set them to 22dp.
   - The bell glyph measures about 14dp. Set it to 20dp in its 40dp circle.
5. **The cards do not read as glass in light mode.** Cards are near-opaque
   white with no visible lit edge. The corners measure about 18–20dp, but the
   spec says card 24 and list group 20.
   - Fix: glass `#FFFFFF` @ 72%.
   - Add a 1dp edge of white going from 95% at the top to 35% at the bottom.
   - Shadow: `#3B2F22` @ 8%, y 10, blur 30.
   - Radii: season, Mass and verse cards 24; the Oración and Fidelidad groups 20.
   - Dark: confirm the fill is 7%, not 10–12%. The large cards read slightly grey and slabby against `#0E0D0C`.
6. **Card header grammar differs from card to card.**
   - The Mass card uses icon + headline title + a footnote reference.
   - The verse card uses a grey eyebrow on the left and a reference on the right, with no title.
   - The season card uses a dot + headline.
   - Fix: one pattern everywhere. Title as headline 17/600 on the left, reference as footnote 13 inkSecondary on the right, at the same baseline. The icon is either always present or never.
7. **"Primera lectura · Tito 2, 1–8. 11–14" is a dead line.** It is a grey
   footnote under a hairline, with no affordance. It reads as metadata, yet it
   implies navigation.
   - Fix: either drop it (the "Leer la Misa" CTA covers it) or make it a 44dp row with a chevron.
   - Also, the excerpt ends "rebaños...»". Use a real ellipsis character (…).
8. **The scroll is cut hard under the tab bar.** The Fidelidad card is sliced
   by the bar edge, with no fade.
   - Fix: a 24dp canvas fade above the bar: light `#ECE9E5` going from 0 to 100%, dark `#0E0D0C`.
   - Bottom content padding: bar height + 16dp + nav inset.
9. **The Fidelidad ring is invisible at 0.** The dark track is about
   `#3D3C3C` on the card, which is under 1.5:1. The light track is pale grey
   on white.
   - Fix: the track is white @ 14% in dark and black @ 8% in light, with a 6dp stroke.
   - The "0/12" denominator is fine.
   - Add a secondary capsule "Marcar práctica" (or "Configurar" when the rule is empty) under the copy. The empty state needs an action.
10. **The season card does not overlap the hero.** It sits 24dp below "Hoy",
    on a flat canvas.
    - Fix: pull the card up so that its top is 24dp *into* the fade.
    - Tighten Hoy→card to 16dp.
    - This is the one concept idea worth stealing that is still not taken.
11. **Bold count.** Six semibold strings are visible above the tab bar, and
    "Bold is rare" is the rule.
    - Fix: drop the weight of "Tu regla de vida" to 500.
    - Keep 600 only for the card titles and section headers.

## Round-1 defects: status

**Fixed:**
- Tab bar: "Oraciones" is no longer clipped, the bar is opaque so content no longer shows through, and the selected pill is a neutral glass pill with no greens.
- Mid-word truncation is gone.
- "Tiempo Ordinario" is no longer repeated. The subtitle is now "Martes de la semana XXXII".
- The Mass card is rebuilt around the Word: serif excerpt at about 19/30, one charcoal 52dp CTA with no icon, and a glass play circle beside it.
- "Meditación del día" is no longer an orphan.
- "Regla de vida" is no longer listed under Oración.
- "Coronilla de la Misericordia" fits on one line.
- The hint is 2 lines, and its icon is no longer green.
- Rhythm: 12dp between cards, about 32dp between sections, and 12dp from section header to group.
- The season dot is aligned to the title.
- Hero crop: the cross is about 60dp and sits on the right third.
- Dark hero: warm grade, mist, and eyebrow contrast all pass.
- The dark bell is now a glass circle.
- The dark CTA no longer has a shadow.
- The doubled «, the stray spaces and the mixed lectionary are fixed. Both themes show Lucas 17, 7–10.

**Still open:**
- Rosary glyph (#4).
- Icon size consistency (#4).
- Light glass material: lit edge and radius 24 (#5).
- 24dp fade under the bar (#8).
- Fidelidad empty state has no action (#9).
- Season card does not overlap the hero (#10).
- Bell glyph size (#4).

**New:**
- Verse card dominance (#1).
- Band above the hero (#2).
- Dark sky brightness (#3).
- Header grammar (#6).
- Dead "Primera lectura" line (#7).

---

**ITERATE.** Light passes: shipping wins, 8.4 vs 7.0. Dark is 8.3, below the
bar of 9. Top 5 fixes:

1. Clamp the verse card to one verse or 4 lines with a fade and tap-through, ≤ 220dp tall.
2. Draw the hero from y = 0 to remove the 12dp canvas band. Put the bell at status inset + 8dp.
3. Dark hero: −0.5 EV on the sky (to `#4A4439` or darker), plus a top scrim of `#000` 35%→0 over 120dp.
4. Draw a real rosary glyph (crucifix + bead loop). Set row icons to 22dp and the bell glyph to 20dp, all with a 1.5dp outline.
5. Glass material: light 72% fill, 1dp lit edge (95%→35%), radius 24 for cards and 20 for groups. Dark: fill exactly 7%. Add a 24dp canvas fade above the tab bar.
