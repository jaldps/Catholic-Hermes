# Rosary / Divine Mercy player — Round 3 critic review (blind)

Files: `blind/rosary_r3_{light,dark,mercy}.png` and the zoom sheet
`after/rosary_r3_zoom_marks_fruit_book.png`.

**Identification:** the shipping app is light B, dark A and Mercy A.

**Method:** every item was zoomed before I ruled on it.

## Verdicts

| Pair | Winner, margin | Shipping | Concept |
|---|---|---|---|
| Light (opening) | **B (shipping)**, about 1.7 | **9.0** | 7.3 |
| Dark (Hail Mary 2/10) | **A (shipping)**, about 1.9 | **8.9** | 7.0 (light mock) |
| Mercy (decade) | **A (shipping)**, about 1.4 | **8.9** | 7.5 |

## Round-2 items: status

| # | Item | Status |
|---|---|---|
| 1 | Fruit truncated | **Fixed.** Footnote 13/18, 2 lines, whole. The painting is still clear down to the picker. |
| 2 | Opening block detached | **Fixed.** Title, text and "Después" read as one unit. The air above and below is now a breath, not a hole. |
| 3 | Marks at the screen edge | **Fixed, but a new collision.** The marks start about 8dp from the edge, and now sit about 2dp from the text: "V.Dios", "R.Santa", "V.Por", "R.ten". |
| 4 | "Después" faint | **Fixed.** 4.82:1 in light, 7.58:1 in dark, outside the crossfade. |
| 5 | "5 de 10" as an orphan | **Fixed.** Ink title2/500 heads the block. |
| — | Book glyph | **Fixed.** The off state is a true outline. The filled (on) state was not rendered. |
| — | Dark painting glow | **Fixed.** −0.3 EV; the angel no longer outshines the ivory play disc. |

## Marks decision: choose (a), the 14dp hang starting 6dp from the edge

**Why (a):**
- The 2dp gap makes "V.Dios" read as one word. That is a typographic error on every dialogue step: 50 Hail Marys and 50 chaplet responses.
- The gap matters more than edge clearance. The marks sit mid-screen, away from the rounded corners, so 6dp is safe.
- Liturgical books hang rubrics close to the edge for exactly this reason.

**Why not (b):** a mark smaller than 0.72em turns the period into a speck,
and "V" and "R" lose their legibility as rubrics.

**Spec:**
- 0.72em, right-aligned in a 14dp hang, with at least 4dp clear of the text.
- If the measured mark width is over 10dp (large font scale), drop to 0.66em before eating into the gap.

## Remaining defects (shipping)

1. **Mark-to-text collision** (above). This is the only item holding dark
   and Mercy below 9.
2. **Two states are unverified:** the light-glass segmented variant (needs a
   light-tone painting such as the Annunciation or the Resurrection) and the
   filled book state.
   - Render both once. Neither is scored here.
3. **Label group in dark decades** (minor, not blocking). Picker + 2-line
   title + 2-line fruit takes about 150dp.
   - Acceptable at 411dp. Check PT at 1.3× so the painting keeps at least 96dp clear, as the plan promises.

---

**ITERATE (one fix).** Light passes: shipping wins, 9.0 vs 7.3. Dark (8.9)
and Mercy (8.9) are held below 9 only by the "V.Dios" collision.

Apply marks option (a): a 14dp hang starting 6dp from the edge, with a
4dp minimum gap. Re-render the dark Hail Mary and the Mercy decade. If the
crops show the gap, I expect both at about 9.2 and the screen at **SHIP**,
with no further full round needed.
