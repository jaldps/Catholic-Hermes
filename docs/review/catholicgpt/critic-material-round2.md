# Blind Material review, round 2

Inspected all ten newly randomized composites. No mapping or implementation inspected. No live web references were available; judgments use Material 3 expertise, not a claimed comparison to inaccessible reference sites. Static images establish visual size and hierarchy, not hit boxes or interaction behavior.

**Wowed: no.** The strongest variants have materially better information architecture and task priority, but remain competent rather than exceptional. Their useful changes deserve clear wins; they have not reached a 9 or 10.

| Pair | A score | B score | Winner | Reason |
|---|---:|---:|---|---|
| Home light | 6.5 | 7.5 | B | Removing the ornamental header puts the feast and Mass action first and reveals another useful destination. Action placement before the reference list is substantially better. |
| Home dark | 7.5 | 6.5 | A | Same strong improvement: the primary action is fully visible and useful content replaces the illustration. |
| Bible light | 7.5 | 6.5 | A | Compact banner, immediately available testament filters, and more books visible. Versions is appropriately demoted. |
| Bible dark | 6.5 | 7.5 | B | Same improvement. The library now behaves visually like a browsable collection rather than a settings menu above a collection. |
| Rosary light | 6.5 | 7.5 | B | Actual prayer and action finally share one visible composition; instructions are separated cleanly. Beads are still somewhat too prominent and faint. |
| Rosary dark | 7.5 | 6.5 | A | Same substantial functional hierarchy improvement. Prayer text is fully visible. |
| Prayers light | 7.0 | 7.5 | B | Useful categories and direct intent chips improve browsing. Smaller rounded banner is better integrated. Chips introduce some crowding and an awkward cutoff at the right edge. |
| Prayers dark | 7.5 | 7.0 | A | Same improved browsing structure and appropriate text-only rows. Dark tonal hierarchy is clear enough, but still visually flat. |
| More light | 7.0 | 7.0 | Tie | B is slightly more compact and exposes the start of Appearance, but an unusable sliver of a row is not enough to establish a meaningful quality increase. Both remain solid generic menus. |
| More dark | 7.0 | 7.0 | Tie | Tiny spacing compression; essentially the same hierarchy and sophistication. |

## Three biggest remaining flaws

1. **Weak differentiation between content roles.** Home's long reading container, its linked Gospel row, prayer lists, and navigation menus are all similar soft rounded green blocks. More's joined rounded sections still evoke generic inset grouped lists. Build a more intentional M3 surface hierarchy: distinguish reading content from actions and browsing rows through surface level, typography, and spacing. Preserve the excellent clear selected-navigation pill. Normalize the visual weight of the detailed rosary/candle navigation icons against the simpler symbols.
2. **Residual ornament and space allocation.** Even the better Bible and Prayers variants spend roughly 96px of height on an illustration that communicates little beyond the title. Their search still begins well below the top. Rosary's reduced bead image is better but remains large relative to its informational value; the dark beads are faint. Shrink decoration further or give it real utility; increase bead/current-progress contrast. The better Home still uses a long, ellipsized Gospel teaser and then repeats its reference in a second card: shorten that teaser deliberately and make the second action's purpose more distinct.
3. **Filter and secondary-action polish.** Bible's “Deutero.” abbreviation is cryptic and the chip row differs visually from the thinner Prayer chips; Prayer's right-edge partial chip is a harsh cutoff. Use clear language, consistent chip sizing, and an intentional scroll affordance/edge spacing. The small Versions text and painted Prayer chips warrant checking for at least 48dp touch regions. More exposes Settings/Appearance only as a bottom sliver: tune vertical rhythm or use a compact settings destination so this is a useful discovery rather than a clipped tease. Actual scroll clearance must be verified interactively.

These improvements solve substantial usability defects. The next step is a disciplined visual-system pass, not additional decoration: stronger role hierarchy, consistent interactive dimensions, and deliberate content density.
