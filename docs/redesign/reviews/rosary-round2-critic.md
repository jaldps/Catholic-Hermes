# Rosary / Divine Mercy player — Round 2 critic review (blind)

Files: `blind/rosary_r2_{light,dark,mercy}.png`.

**Identification:** the shipping app is light A, dark B and Mercy B. These
are the sharp captures at about 1.23 px/dp.

**How I judged:**
- Every item was zoomed before I ruled on it.
- Painting resolution was ignored (the source is 830px). The treatment is judged.
- "Instrucciones" is accepted as the book button's label.

## Verdicts

| Pair | Winner, margin | Shipping | Concept |
|---|---|---|---|
| Light (opening) | **A (shipping)**, about 1.3 | **8.6** | 7.3 |
| Dark (Hail Mary 2/10) | **B (shipping)**, about 1.8 | **8.8** | 7.0 (light mock) |
| Mercy (decade) | **B (shipping)**, about 1.3 | **8.8** | 7.5 |

**The big win this round is the art.** Agony and Divine Mercy are now in
full colour with their subjects clear: Christ and the angel, the face, and
the red and pale rays. The stage finally carries the screen, the way the
concept's portrait does, but with real sacred art.

## Round-1 items: status

| # | Item | Status |
|---|---|---|
| 1 | Dead zone under short prayers | **Partial.** Mercy is good: about 55dp to the controls. The opening moved the hole *inside* the block: about 50dp between "Señal de la cruz" and its text, plus about 109dp under "Después". |
| 2 | Stage art | **Fixed.** No smog, the subjects are clear, and the rays are intact. The dark fruit is one line, but now it truncates (see defect 1 below). |
| 3 | Duplicates | **Fixed.** No double "Inicio", no double "Década 1", and no prayer title repeating the ℣ line. |
| 4 | ℣ / ℟ marks | **Mostly fixed.** Serif "V." / "R." in inkSecondary, on the baseline. But they sit about 4dp from the screen edge (see defect 3 below). |
| 5 | Materials | **Fixed.** Dark-glass track over dark art, a charcoal play disc in light and ivory in dark, an outline cross in the reel, and the pips are clear of the ring. The light-glass track was not shown, so it is unverified. |
| — | Book glyph, fruit casing, 20dp gap to the dock | **Fixed.** |

## Remaining defects (shipping), by visual impact

1. **The dark fruit is truncated.** "Fruto: el dolor por nuestros pecados y
   la…" ends on an article. That is a checklist fail, and it is a worse read
   than the 2 lines it replaced.
   - Fix: add a short-fruit field for the stage, e.g. "Fruto: contrición y conformidad con la voluntad de Dios". Keep the full text in the Mystery sheet.
   - Or allow up to 2 lines in footnote 13/18 (not subhead). Never use an ellipsis.
2. **The opening's title is detached from its text.** About 50dp of air
   separates "Señal de la cruz" from the prayer, and about 109dp sit under
   "Después". The 40/60 offset is being applied to the text alone.
   - Fix: treat title + text + "Después" as one block.
   - Title 12dp above the text; "Después" 24dp under it.
   - Place the *whole block* at 40/60 in the area.
   - Result on the opening: about 40dp above and about 60dp below, with no internal gap.
3. **The ℣ / ℟ marks crowd the screen edge.** Hung 18dp into a 20dp gutter,
   the marks start about 4dp from the edge in both dark and Mercy.
   - Fix: set them at 0.72em, right-aligned in a 12dp hang (6dp clear of the text).
   - The mark should start no less than 8dp from the edge.
4. **"Después · Padre Eterno" is faint in Mercy.** It samples `#9D9A95` on
   `#EEECE7`, about 2.4:1. The builder attributes it to capture during the
   crossfade.
   - Fix: make the "then" line part of the static layout, not of the step crossfade (it is constant through ten repetitions).
   - Assert alpha 1 once the UI is idle, and confirm it reaches inkSecondary `#6B6661` (≥ 4.5:1).
5. **"5 de 10" reads as an orphan.** It is grey (`#635D58`), medium weight,
   at title size, a meta value posing as a title.
   - Fix: set it in ink, title2 22/28 weight 500.
   - Or drop the title row in Mercy decades: put "5 de 10" as footnote on the right of the reel row, and let the prayer rise 36dp.
6. **The book button looks "on" in all three states.** It is a filled glyph
   everywhere, but the builder says filled means on. Verify that the off
   state renders as outline, or the toggle is unreadable.
7. **The dark painting is bright in dark mode.** The angel's robe (about
   `#E0D8C8`) is the brightest thing on screen after the ivory play disc.
   - Fix: −0.3 EV in dark only (keep saturation), so the art sits under the UI rather than glowing.

---

**ITERATE.** Light passes: shipping wins, 8.6 vs 7.3. Dark is 8.8 and
Mercy 8.8, both just below 9. Top 5 fixes:

1. No truncated fruit: a short-fruit field, or 2 lines at footnote 13/18. Never "y la…".
2. Short-prayer block = title + text + "Después" as one unit (12/24dp internal), placed at 40/60.
3. ℣ / ℟ at 0.72em in a 12dp hang, at least 8dp from the screen edge.
4. "Después" excluded from the step crossfade; verify inkSecondary ≥ 4.5:1 at idle.
5. "5 de 10" in ink title2/500 (or moved to the reel row). Dark painting −0.3 EV.
