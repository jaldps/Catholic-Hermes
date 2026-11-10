# Rosary / Divine Mercy player — Round 1 critic review (blind)

Files: `blind/rosary_r1_light.png`, `blind/rosary_r1_dark.png`, `blind/rosary_r1_mercy.png`.

**Identification:** the shipping app is light B, dark A and Mercy A. These
are the sharp captures at about 1.23 px/dp. The concept is the upscaled
iPhone mock.

**What I judged:** execution, hierarchy, use of space and polish. The
owner's mechanics are a given: the reel, the single transport, the picker
and the narration. Every item below was zoomed before I ruled on it.

## Verdicts

| Pair | Winner, margin | Shipping | Concept |
|---|---|---|---|
| Light (opening) | **B (shipping)**, narrowly, about 0.3 | **7.6** | 7.3 |
| Dark (Hail Mary 2/10) | **A (shipping)**, about 1.0 | **8.0** | 7.0 (light mock) |
| Mercy (decade, light) | **A (shipping)**, narrowly, about 0.2 | **7.7** | 7.5 (Rosary landing) |

**Where shipping wins:** it is a real practice screen. It shows where you
are (step, decade, reel) and what comes next ("Después · …"). The reading
serif is excellent, and the Hail Mary fits without scrolling.

**Where the concept wins:** its art is luminous and is clearly the subject.
The shipping stage is muddy, mostly covered by controls, and in the opening
and Mercy states a large dead zone sits mid-screen.

**What the concept does wrong:** a close X on a tab root, a generic AI
Madonna, neumorphic halos, and no orientation at all (no reel, no decade).

## Shipping defects, by visual impact

1. **Dead zone under short prayers.**
   - Opening, Sign of the Cross: the text ends about 160dp above "Después · Credo".
   - Mercy decade: about 88dp. And 50 of the 68 chaplet steps are this case.
   - The layout is stable, but the hole reads as a bug.
   - Fix (keep the stable layout): place the short-prayer block at 40/60 optical centring inside the fixed prayer area, so 40% of the slack sits above it and 60% below.
   - Pin "Después" 24dp under the last line rather than to the transport.
   - Raise short prayers from 1.2× to 1.35× (about 26/36 serif). That fills the area like a lyric screen without moving the title, the reel or the transport.
2. **The stage art is muddy and its subject is lost.**
   - **Agony (light):** the 30% lift toward the canvas turns the night painting into grey smog (`#5A5A5A` top band). The fade starts at about 40%, so Christ, the subject, dissolves and only the angel reads.
   - Fix: no lift in light mode; use a 35%→0 top scrim for the control instead. Start the fade at 62% of the stage height. Re-crop with focus Y about 0.55 so Christ's head clears the fade.
   - **Divine Mercy:** saturation 0.85 plus the mist erases the red and pale rays, which are the image's whole meaning. The source also shows JPEG softness.
   - Fix: saturation 1.0 and no lift for this asset. Start the fade below the hands, at about 70%. Use a source of 1080px or wider.
   - **Dark:** the label group (picker + 2-line title + 2-line fruit) leaves about 110dp of clear painting.
   - Fix: clamp the fruit to 1 line with a tap to open the Mystery sheet, and shrink the 2-line title to title2 22/28 at max 2 lines.
3. **Duplicate text.**
   - Opening: the title "Inicio" and the right-hand progress label "Inicio".
   - Mercy: the title "Década 1" and the label "Década 1 de 5".
   - Mercy: the prayer title "Por su dolorosa Pasión" repeats the ℣ line word for word.
   - Fix: drop the right-hand label whenever it equals the title (show it only when the title is a mystery name).
   - When the prayer title equals the ℣ text, hide the title row. Keep "5 de 10" right-aligned on the first text line, as footnote 13 inkSecondary.
4. **The ℣ / ℟ glyphs are a fallback sans.** They are slashed sans V/R, larger than the serif's x-height and on their own baseline. Next to EB Garamond they look broken.
   - Fix: set them in the reading face (or as small caps "V." / "R.") at 0.85em, in inkSecondary, on the text baseline.
   - Hang them 18dp into the left margin so the text column stays on the 20dp gutter.
5. **The segmented control is the heaviest object in light mode.** It is a black 62% track (`#22201F` sampled) with a white thumb, sitting over light mist.
   - Fix: choose the style by painting tone.
   - Light-tone paintings: light glass (white @ 72%, 1dp lit edge, ink labels).
   - Dark-tone paintings: dark glass at black @ 35% plus blur. Do not use 62%.
   - Keep 36dp visual height and a 44dp hit area.
6. **The reel crucifix does not match.** It is a heavy filled trefoil cross in
   a green ring. Two stray dots (black and grey) sit under it, which are
   pendant beads colliding with the ring.
   - Fix: a 1.5dp outline Latin cross matching the dock glyph, in ink.
   - Push the pendant beads 6dp clear of the ring, or hide them at step 1.
7. **The light transport is white on white.** The capsule samples
   `#F9F9F7` and the play disc `#FFFFFF`, on a canvas around `#FCFCFB`. They
   are separated by grey shadow only, which is the neumorphic look again.
   - Fix: glass at 72% with a 1dp lit edge, and shadow `#3B2F22` @ 8%, y 10, blur 30.
   - Make the play disc the action colour (charcoal `#2B2A28` with a white glyph), so the one primary control is unmistakable.
8. **The light-bulb button is ambiguous.** Tip? Idea? Meditation?
   - Fix: a book or quote glyph, with the content description "Meditación del misterio".
   - Or label it under the button, as in Apple Music's lyrics/queue buttons.
9. **Fruit casing.** "El Dolor por nuestros pecados y la Conformidad con la
   Voluntad de Dios" is Title Case, which Spanish does not use.
   - Fix: "Fruto: el dolor por nuestros pecados y la conformidad con la voluntad de Dios". Fix it in the data sanitiser for all mysteries and languages.
10. **Two stacked capsules.** The transport (about 70dp) sits 14dp above the
    dock and reads as a second tab bar.
    - Fix: 20dp gap.
    - Make the side buttons 44dp glass circles aligned to the transport's centre (they are already close).

---

**ITERATE.** Shipping wins all three pairs, light narrowly, but dark is
8.0 and Mercy 7.7, both below 9. Top 5 fixes:

1. Fill the short-prayer dead zone: 40/60 optical centring, 1.35× lyric size, and "Después" 24dp under the text.
2. Re-treat the stage art. No light lift; top scrim; fade from 62%. Agony focus Y about 0.55. Divine Mercy at saturation 1.0, no lift, fade from 70%, sharp source. Clamp the fruit to 1 line in dark.
3. Remove the duplicates: "Inicio" / "Década 1" right-hand labels, and the prayer title that equals the ℣ line.
4. ℣ / ℟ in the reading face at 0.85em, inkSecondary, baseline-aligned and hung in the margin.
5. Material pass: tone-aware glass segmented control (no 62% black in light); charcoal play disc with a glass transport; outline crucifix in the reel; pendant beads clear of the ring.
