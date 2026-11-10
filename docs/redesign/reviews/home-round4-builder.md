# Home (Hoy) — round 4 (builder)

Input: `docs/redesign/reviews/home-round3-critic.md` (light 8.8, dark 8.7).
No phone (USB dropped), so evidence is ES renders at 411x1600dp:
`docs/redesign/after/home_r4_light_render.png`, `home_r4_dark_render.png`.
Each claim below was checked by cropping/zooming the render, not assumed.

## Zoom audit of items reported "fixed" last round

| Item | What the zoom showed |
|---|---|
| Bell | The glyph measures about 16 wide x 19.5 tall dp inside the 40dp circle (24dp Material frame). That is the 20dp visible height asked for; kept. |
| "Tu regla de vida" | It already rendered at weight 500 (Inter Medium) in the r3 render; kept at 500 in the slimmed card. |
| Ellipsis | The code already emitted U+2026 (`clauseCut`, `cleanScripture`), but EB Garamond draws it tight, so it still read as "...". It is now set open (1sp tracking) in the UI face, and `HomeTextTest` asserts U+2026 and no "...". |
| Row icons | They measured about 18dp visible. Row icon frame is now 26dp (text start unchanged), and the custom glyphs span the full 24 grid. Sunrise and rosary now read about 22dp. |
| Rosary | It still read as a venus sign (loop on a stem). Redrawn as a bead necklace drooping from the top corners, a stem bead and a Latin crucifix (1.5dp stroke); same glyph on the Coronilla row and the Rosario tab. Zoomed after the last edit (cross larger, shallower loop). |
| Light cards | White on a near-white canvas had no visible edge. `lumenGlass` now uses 72% fill without the extra sheen in light, a 95% -> 35% white lit edge and an outer warm 0.5dp hairline (light only). |

## Top 5

1. **Fidelidad:** ring (88dp, 7dp stroke), one line of copy and ONE secondary capsule ("Empezar mi regla" / "Ver mi regla"). "Vencí una tentación" and the month calendar are off Home; Habits already owns the temptation flow and the fidelity view. No tests referenced the removed Home tags.
2. **Icons:** see the table above; bell 20dp visible, row icons about 22dp visible.
3. **Verse hint:** cut to its first clause and set on one line, "Hoy relee este versículo despacio tres veces." (`hintClause`, EN/PT too), no ellipsis. Real "…" in all excerpts (see above).
4. **Secondary capsules (shared `AppSecondaryCapsule`):** 44dp, no shadow, black 5% fill in light; white 12% with a 14% -> 4% edge in dark.
5. **Light glass and ring:** see Light cards above. Ring track is black 13% in light (the critic asked for 8%, but at 8% it is even fainter than the 1.1:1 it called invisible; I chose legibility) and white 14% in dark. Dark horizon -0.3 EV more (gain 0.37/0.35/0.30).

Also: the hero fade now keeps about 18% art alpha at the card top, so the season card visibly rides over the fade; its 24dp overlap is unchanged.

## Files

`LumenComponents.kt` (secondary capsule, ring track, dark grade, hero fade), `LumenFoundation.kt` (glass), `LumenKit.kt` (row icon frame), `DesignSystem.kt` (light edge bottom), `DevotionalGlyphs.kt`, `HomeScreen.kt`, `HomeTextTest.kt` (new).

## Known gaps

- No phone verification; bell inset (status + 8dp) cannot be seen in a render (no status bar).
- The light glass edge is a hairline pair, not a literal white-on-white edge.
