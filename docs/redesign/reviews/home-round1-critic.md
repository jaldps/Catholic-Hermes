# Home (Hoy) — Round 1 critic review (blind pairs)

Reviewer stance: ex-Apple HI design lead, iOS 26 bar (Music, Journal, Fitness,
Photos). "Fine" is a failure. Resolution/sharpness of the upscaled side was
ignored; everything below is about design.

Files: `docs/redesign/blind/home_r1_light.png`, `docs/redesign/blind/home_r1_dark.png`.

Identification (needed for item 4): the iPhone-framed mock (light **A**, dark
**B** — the same image both times) is the concept. The Android capture (light
**B**, dark **A**) is the shipping app. Note that the concept has **no dark
mode at all**: in the dark pair it is still the light mock, so that pair
compares a dark screen with a light one.

Measurement basis for the shipping capture: the viewport is about 283 px wide,
which is about 0.69 px/dp on a 411dp phone. Any dp value quoted below is
derived from that and is accurate to ±2dp.

---

## Pair 1 — light

### Verdict
**A wins by about 1 point.** A reads as one designed object: dominant hero, a
confident title, two calm glass cards and one obvious task (today's Gospel). B
is the more complete and more honest *product*. As a *design*, though, it is a
stack of near-identical white cards, too many type sizes and repeated text, and
its tab bar clips a label.

| Side | Score |
|---|---|
| A (concept) | **7.0** |
| B (shipping) | **6.0** |

### A (concept) — defects (7.0, below 9.5)

1. **Neumorphism, not Liquid Glass.** Every surface (season card, Gospel card,
   play disc, bell disc, tab bar, selected tab) has a grey omnidirectional halo
   about 30–40pt wide with almost no y-offset. That is the 2020 Dribbble
   soft-UI look. iOS 26 glass gets its lift from a directional shadow plus a
   lit edge. Fix: shadow `#3B2F22` @ 8%, y 10, blur 30, applied only below the
   surface. Add a 1dp edge of white going from 95% at the top to 35% at the
   bottom. No glow on the sides or the top.
2. **Three different left edges.** The date and "Hoy" start about 32pt in, the
   card edges about 19pt in, and the card content about 45pt in. Inside the
   cards, the season title starts at about 78pt and the Gospel title at about
   93pt, so the two text columns differ by about 15pt. Fix: put title, eyebrow
   and card edges on the 20pt gutter. Card content goes at 20 + 20 = 40pt. Put
   both card titles on one column at 40 + 24 (icon) + 12 = 76pt.
3. **Display title too heavy and too big.** "Hoy" is about 60pt at about 600
   weight. The spec is display 44/48, weight 400, tracking −1.0. Apple's large
   titles are big but never shouty. Fix: 44/400, −1.0.
4. **Eyebrow competes with the title.** "Jueves, 5 de noviembre" is about 20pt
   regular, which is nearly half the title size. Fix: eyebrow 13/18, weight
   500, inkSecondary, 4pt above the title's cap height.
5. **Season dot is a glossy 3D ball.** The dot is about 18pt with a radial
   gradient and a highlight: skeuomorphic, and the loudest pixel on the
   screen. Fix: a flat 8dp dot in the liturgical colour, centred on the cap
   height of the title.
6. **The Gospel card has no hierarchy.** Title (about 24 semibold), reference
   (about 22 grey) and quote (about 22 regular) are three nearly equal sizes.
   The scripture is set in UI sans. Fix: title as headline 17/600; reference
   as footnote 13 inkSecondary on the same line or directly below it; the
   excerpt in the reading face, serif 19/30, three lines maximum.
7. **Play disc and dead space.** The play disc is about 56pt and neumorphic,
   and it floats bottom-right over about 50pt of empty card. There is no
   labelled primary action ("Leer", "Escuchar"). Fix: a 52dp charcoal capsule
   "Leer el Evangelio" at the bottom-left of the card, and a 40dp glass play
   circle to its right. Cut the card's bottom padding to 20dp.
8. **Tab bar ignores the product.** It has 4 tabs, no labels, and mixed
   glyphs: a filled home, a heavy outline book, a hairline cross and an
   outline person. The selected state is a raised neumorphic disc. Unlabelled
   tabs fail clarity, and the owner's 6-tab labelled bar is mandatory. Fix:
   keep the 6 labelled tabs. Use one outline set with a 1.5dp stroke at 24dp,
   filled only when selected. The selected pill is glassStrong with no halo.
9. **Bell is oversized.** It is about 56pt. Fix: a 40dp glass circle (44dp hit
   area), 20dp glyph, top-right on the gutter at status-bar inset + 8dp.
10. **Hero art is stock, not devotional.** The image is a generic minimalist
    3D chapel with a lens-flare cross. Its roofline cuts hard against the sky
    at the left, with no mist on the edge. DESIGN.md asks for classical sacred
    art or soft photography treated with a mist. Fix: a real sacred interior
    or a classical painting, with the light-mode mist (lift +8%, −15%
    saturation, fade to canvas over the lower 55%).
11. **Too sparse for a Home.** Only the season card and a 3-line Gospel excerpt
    sit above the fold on a tall phone. Mass of the day, the verse, the prayer
    of the hour, fidelity and the saint are all pushed 2–4 scrolls down.
    Apple Fitness and Journal open on a summary, not a poster. Fix: cap the
    hero at about 42% of the viewport so the first rows of the next section
    show above the fold.
12. **No dark mode delivered.** The dark pair shows the same light mock, so
    "dark as composed as light" is unanswered.

### B (shipping) — defects (6.0)

See the full shipping audit in section 4. The items that decide this pair:

- **Cards are flat white Material cards.** The fill is about `#FCFCFB`, close
  to opaque. There is no visible lit edge, and a faint, diffuse elevation
  shadow. This reads "Android card", not glass.
- **No section rhythm.** Five cards are stacked at gaps of 20, 13 and 14dp,
  which reads as a feed of equal-weight boxes. Nothing says "this is the one
  thing".
- **Too many type sizes.** About eight are in play: display, 17 semibold, 15
  sans body, 12 eyebrows, serif about 18, 13 hint, 17 header, 10 tab label.
- **Text repeats.** "Tiempo Ordinario" appears three times in the first 380dp.
- **Mid-word truncation.** "encuen…" and "la am…".
- **Tab bar defects.** "Oraciones" is clipped, and content shows through a bar
  that is not frosted.

---

## Pair 2 — dark

### Verdict
**B (the concept, still in light mode) wins narrowly, by about 0.5.** Its
hierarchy and focus are better. But A is the only side that actually *is* a
dark screen, and its dark composition is more coherent than its own light
variant: the cards read as glass on near-black, and the ivory CTA is right.
If you judge strictly "dark-mode design", A wins by default. Either way,
neither side is shippable.

| Side | Score |
|---|---|
| A (shipping, dark) | **6.5** |
| B (concept, light) | **7.0** (same defects as Pair 1 A, plus no dark mode) |

### A (shipping, dark) — defects specific to dark (6.5)

1. **The hero glares.** The top of the painting is a bright, cold grey-blue sky
   (about `#ADAAAC`) above a warm near-black canvas (`#0F0E0D`). This makes a
   light band at the top of a dark screen, and a temperature clash. "Nothing
   glows" is violated. Fix: exposure −0.6 EV and a warm grade (+4 R / −4 B).
   Add a top scrim of `#000` going from 35% to 0 over the first 120dp, which
   also protects the status bar.
2. **Eyebrow contrast fails.** "Domingo, 8 de noviembre" is inkSecondary
   `#A8A29B` over brown rock (about `#4A4440`), which is about 3:1. Fix:
   extend the fade so the bottom 45% of the hero reaches at least 85% canvas
   *before* the eyebrow line, or set the eyebrow in ink at 80%.
3. **No mist.** The dark hero is the full painting darkened rather than
   dissolved, so the rock is a muddy brown mass right behind "Hoy". Fix: fade
   to `#171614` starting at 45% of the hero height. Lower the rock's
   saturation by 25%.
4. **Bell disc.** It is a light grey disc with a dark-looking drop shadow, the
   second-brightest object on screen. Fix: glassStrong (white @ 12%) with a
   14%→4% edge, glyph `#F3F0EB`, and no shadow.
5. **Data and typography bugs show more clearly in dark.**
   `««En aquel entonces` has a doubled guillemet. `del esposo .»` has a stray
   space before the period. The light and dark captures also show *different
   Gospel translations* ("Mateo" vs "San Mateo"). Fix the sanitiser: collapse
   repeated «, strip spaces before punctuation, and use one lectionary source.
6. **The ivory capsule shadow is wasted.** A black shadow on near-black
   achieves nothing and adds a smudge. Drop the shadow in dark mode; the
   ivory fill is enough.
7. **The tab bar is see-through.** "Tu regla de vida / Aún no hay prácticas…"
   and the "0/…" ring are fully legible through the bar. The selected pill is
   a murky green-grey. Fix: bar fill `#1E1C1A` @ 92% (or a real blur with
   radius ≥ 30dp), a 14%→4% edge, and a 24dp canvas fade under the bar.
8. **The green hint icon** (sun) beside the verse tip is a second accent. Use
   inkSecondary.

---

## 4. Shipping app vs DESIGN.md checklist

| Checklist item | Result | Evidence and fix |
|---|---|---|
| Gutter and alignment exact | **Mostly pass** | Cards and "Hoy" sit on about 20dp. Card content is about 20dp inside. The season dot hangs at the vertical centre of the whole 3-line block, so it sits beside the subtitle instead of the title: align the 8dp dot to the cap-height centre of "Tiempo Ordinario". The tab bar is inset about 12dp, not 20 (owner's bar; acceptable). |
| One radius per element type | **Partial** | Cards look about 20dp, *not 24*. The 52dp "Meditación del día" single-row card reads as a pill next to its taller siblings. Use card 24 and list group 20 everywhere, and put Meditación inside a group, not a lone card. |
| No truncation or awkward wraps | **Fail** | "salieron al encuen…" and "quienes la am…" are cut mid-word. "Oraciones" is clipped in the tab bar (light and dark). "Coronilla de la Divina / Misericordia" wraps, with the time centred against two lines. Fixes: end excerpts at a sentence or clause boundary, or use a 24dp gradient fade on the last line instead of an ellipsis. Tab labels: maxLines 1, 11sp with −0.2 tracking, equal-weight slots with 2dp side padding, auto-shrink to a 9.5sp floor. Shorten the row to "Coronilla de la Misericordia", or top-align the trailing time to the first line. |
| Body contrast ≥ 4.5:1 | **Fail in dark hero**, pass elsewhere | The dark eyebrow over the rock is about 3:1. In light, the 12sp grey eyebrows ("Evangelio · Mateo…", "Versículo del día") are borderline and too small (see Type). |
| Targets ≥ 44dp | **Pass** | The bell is about 38dp visible; confirm a 44dp hit area. Play is about 40dp, the CTA about 49dp (spec 52: fix), rows ≥ 52dp. |
| No clipped content | **Fail** | The tab-bar label is clipped. The "Fidelidad de hoy" card sits under the bar at rest with no bottom fade. Content bottom padding should be bar height + 16dp + nav inset. |
| No duplicate titles/controls | **Fail** | "Tiempo Ordinario" appears in the season title, the season subtitle and the Mass subtitle. The Mass card has a play button in its header *and* a "Leer la Misa" capsule: two primary actions. The book glyph appears in the header *and* inside the capsule. "Regla de vida" appears in "Oración del momento" (where it does not belong, since it is not a prayer) *and* again as "Tu regla de vida" in Fidelidad. |
| Consistent icon stroke and size | **Fail** | The set mixes the filled quote "99", the filled Ángelus sunrise, an outline lotus, an outline checklist, an outline book-with-bookmark, a green outline sun, and a rosary glyph that reads as **"search +"** (magnifier with a cross; used for both Coronilla and Rosario). Move to one outline family at 1.5dp stroke and 22dp, in ink. Draw a proper rosary/crucifix-bead glyph. |
| Images sharp, cropped, mist-treated | **Partial** | The art choice is right: Friedrich's cross on the summit is devotional and classical. The **crop is weak**: the cross, which is the devotional subject, is about 15dp tall and lost at the centre-right, and about 60% of the hero is empty sky with a bell in it. Scale the crop about 1.5× so the cross is about 40dp and sits on the right third, just above the title baseline. The light mist is good. The dark version is not misted (see Pair 2). |
| Vertical rhythm on 4dp grid | **Fail** | Gaps run: hero→season 20, season→Mass 20, Mass→Meditación 13, Meditación→Versículo 14, Versículo→header 42, header→group 14. Fix: 12dp between cards inside one cluster, 32dp between sections, 12dp from section header to group, and 24dp overlap of the season card into the hero fade. |
| Dark mode as composed as light | **Partial** | Cards, edge and ivory CTA are good. The hero, bell, tab bar and text bugs are not. |
| Loading/empty states designed | **Unknown/weak** | The "Fidelidad" empty state ("Aún no hay prácticas en tu regla de vida" plus an "0/…" ring) is only visible *through the tab bar*. It needs its own designed card: ring at 0, one line, and a "Configurar" secondary capsule. |
| Primary task obvious in one second | **Partial** | "Misa de hoy" is the biggest card, so the intent reads. But it carries a header, two eyebrowed readings, a play button and a CTA, and it opens with metadata, not the Word. The Gospel excerpt is not given reading typography, so it reads as a paragraph of UI text. |

Additional shipping defects not covered by the checklist:

- **Typography scale.** Eyebrows are about 12sp; the spec is 13/500. The
  season title and the Mass title use the same 17 semibold, while section
  headers ("Oración del momento") use a similar 17–18 semibold. Cards and
  sections are therefore not distinguished. The Gospel body is about 15sp
  sans with 22dp leading; it should be reading serif 19/30, or at least
  callout 16/22. The scripture reference "Salmos 51, 10-12" uses a hyphen,
  while the Gospel uses an en dash ("1–13"). Use en dashes everywhere, and
  for the Psalm write "Salmo 52 (51), 10–11".
- **The season card is a template row.** It has a bold title, a 2-line grey
  subtitle and a chevron: it is 96dp tall to say one fact. Fix: a 64–72dp
  row. Title "Tiempo Ordinario" (headline). Footnote "Domingo XXXII ·
  Precepto dominical" on one line. 8dp dot. Chevron 7×12 in inkTertiary.
- **The Mass subtitle repeats the season.** Replace it with something new
  ("Ciclo A · Verde") or drop it.
- **"Meditación del día" is an orphan.** It is a lone 52dp card wedged between
  two large cards. Fold it into a grouped list with the verse, or into the
  Mass card as a row.
- **The verse card** is the best block on the screen: the serif works and the
  hint below a hairline is good. But the green sun icon is a second accent,
  and the hint is 3 lines of grey 13sp. Cut it to 2 lines and use
  inkSecondary for the icon.
- **The CTA** "Leer la Misa" is about 49dp tall; the spec is 52. Its icon
  repeats the card's icon. Drop the icon or keep only the label. The capsule
  has a heavy black shadow in light mode as well; reduce it to 8% warm.
- **The tab-bar material** is not frosted, so content is legible through it.
  The selected state uses a green fill, a green label and a pale green pill:
  three greens. DESIGN allows only a material change on the bar, but the
  material change is exactly what is missing. Use glassStrong `#FFFFFF` @ 88%
  with a 30dp blur, or an Android fallback of a solid `#F2F0ED` @ 94%. Add a
  1dp edge and a 24dp canvas fade under the bar.
- **The bell sits within about 7dp of the capture top.** Confirm it clears the
  status bar: top = status inset + 8dp.

---

## Top 10 fixes (shipping app, priority order)

1. **Tab bar.** Stop "Oraciones" clipping (11sp, −0.2 tracking, equal-weight
   slots, maxLines 1, 9.5sp auto-shrink floor). Make the material opaque
   enough or blurred: light glassStrong 88% + blur, or fallback `#F2F0ED` @
   94%; dark `#1E1C1A` @ 92%. Add a 24dp canvas fade under the bar, and set
   bottom content padding to bar + 16dp + nav inset.
2. **Fix text-data bugs.** Remove the doubled «, the space before ".»", the
   mid-word ellipses (end at a clause boundary or use a last-line fade), and
   the hyphen in verse ranges (use –). Use one lectionary source for both
   themes.
3. **Kill the repetition.** The season subtitle becomes "Domingo XXXII ·
   Precepto dominical" on one line. Remove or replace the Mass subtitle. Move
   "Regla de vida" out of "Oración del momento".
4. **Rebuild the Mass card around the Word.** Gospel excerpt in reading serif
   19/30, 3 lines. First reading collapsed to a single footnote row. One
   primary action: a 52dp "Leer la Misa" capsule (no icon), with a 40dp glass
   play circle beside it, removed from the header.
5. **Vertical rhythm.** 12dp between cards in a cluster, 32dp between
   sections, 12dp from header to group. The season card overlaps the hero
   fade by 24dp. Fold "Meditación del día" into a group instead of a lone
   pill.
6. **Hero crop and dark treatment.** Crop the art about 1.5× so the cross is
   about 40dp on the right third. Hero height 340dp or more. Dark: −0.6 EV,
   warm grade, top scrim 35%→0 over 120dp, fade to `#171614` from 45% height,
   eyebrow contrast ≥ 4.5:1.
7. **One icon family.** Outline, 1.5dp stroke, 22dp, ink. Replace the filled
   quote and the filled sunrise. Draw a real rosary glyph instead of the
   magnifier-plus. Hint icons go inkSecondary, never green.
8. **Consolidate the type scale.** Eyebrows 13/500. Card titles headline
   17/600. Section headers distinct: either 20/600, or 17/600 with 32dp
   space above. Body copy callout 16/22. Fix the season dot's alignment to the
   title's cap height (8dp dot).
9. **Glass material.** Cards white @ 72% with a 1dp lit edge (95%→35%) and
   shadow `#3B2F22` @ 8%, y10, blur 30, radius 24. Bell as a 40dp glass circle
   (12% white in dark) with no grey disc or black shadow. The CTA shadow in
   light goes to 8% warm; no shadow in dark.
10. **Design the Fidelidad empty state and the bottom of the scroll.** Ring at
    0 with one line of copy and a secondary capsule. The last section must end
    at least 16dp above the bar, never under it at rest.

For the concept's direction, worth stealing: the larger hero, the season card
layered over the hero fade, and the "one task above the fold" restraint. Do
**not** adopt its neumorphic shadows, its 4-tab unlabelled bar, its glossy dot
or its 60pt bold title.
