# Home (Hoy) — Round 4 critic review (blind pairs)

Files: `docs/redesign/blind/home_r4_light.png` and `docs/redesign/blind/home_r4_dark.png`.

**Identification:** the concept (an unchanged iPhone mock) is light B and
dark B. The shipping app is light A and dark A.

**How I checked:** every item below was zoomed with a PIL crop at 2–4×
before I called it. Measurements assume about 0.69 px/dp, ±2dp.

## Verdicts

| Pair | Winner, margin | Shipping | Concept |
|---|---|---|---|
| Light | **A (shipping)**, about 2.2 | **9.2** | 7.0 |
| Dark | **A (shipping)**, about 2.1 | **9.1** | 7.0 (light mock) |

The screen is now composed as a whole:

- The hero fades from y = 0, and the season card rides over the fade.
- The Gospel is in serif, with one charcoal or ivory call-to-action.
- The verse card is clamped, and every card follows one header grammar.
- The prayer group is a grouped list. Fidelidad is a slim summary.
- The new Santo del día image card gives the scroll a visual end.
- The dark theme reads as a night chapel rather than an inversion.

## Round-3 items: status (verified by zoom)

| Item | Status |
|---|---|
| Fidelidad overloaded | **Fixed.** Ring, copy and one capsule. The calendar and "Vencí una tentación" are gone. |
| Bell glyph | **Fixed.** About 19dp visible in a 39dp circle; the builder's figure is confirmed. |
| Row icons | **Fixed.** About 22dp. |
| Rosary glyph | **Improved, not solved.** At 22dp it reads as "Y over a cross" (⚳-like), not as a rosary. |
| Ellipsis | **Fixed.** "rebaños…»" and "serafines…" are a true, open U+2026. |
| Verse hint | **Fixed.** One line, no truncation. |
| Secondary capsules | **Fixed.** Flat fills with no shadow: about `#ECEBEA` in light and `#3B3B3A` in dark. |
| Light ring track | **Fixed.** Clearly visible. The builder's choice of 13% over my 8% is accepted. |
| Light glass edge | **Partial.** Cards are still near-opaque `#FCFBFA`, and the edge is a hairline, not a lit edge. On Android this is acceptable. |
| Dark horizon | **Fixed.** About `#5B5645` behind the cross; the top is `#3A362D`. |
| Season card and hero | **Fixed.** The card now sits over the residual art. |

## Remaining defects (shipping), by visual impact

1. **Saint title casing.** It reads "s. León Magno, papa y doctor": a
   lowercase abbreviation at the start of a display title.
   - Fix: title "San León Magno".
   - Meta: "Papa y doctor de la Iglesia · 10 de noviembre", footnote 13, white at 80%.
   - Apply the same rule to every saint (San, Santa, Santo, Beato). Never "s." or "sta.".
2. **The rosary glyph is still not a rosary.** The open drooping strands
   collapse into a "Y" at 22dp.
   - Fix: a *closed* oval loop of 7–8 bead dots (about 11dp wide, 2dp dots), a 2-bead stem, and a 7dp Latin cross.
   - Use the same glyph for the Coronilla row and the Rosario tab.
3. **Light saint card scrim.** The art's dark ground and the scrim make a
   heavy brown slab among light cards.
   - Fix: apply the scrim to the lower 50% only, `#1D1C1A` 0→55%.
   - Lift the art by +6% exposure in light mode only.
4. **Light glass** (low impact). The cards could drop to 72% fill with a
   visible 1dp top highlight. Not blocking.
5. **Bell inset is unverified on a device.** Confirm status inset + 8dp
   once a phone is available.

---

**SHIP.** Shipping wins light (9.2 vs 7.0) and scores 9.1 in dark (≥ 9).
Follow-ups for the next pass: 1, the saint title casing (a content bug, so
fix it first); 2, the closed-loop rosary glyph; 3, the light saint-card
scrim.
