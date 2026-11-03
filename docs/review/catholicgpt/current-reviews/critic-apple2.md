# Apple visual review — blind round 2

Independent screenshot review. No app source, mapping, edits, or builds inspected/performed. Viewed all ten matched A/B pairs in `blind2/` and the two separately identified current font dialogs. The matching date is November 2 in both Home candidates.

## Evidence boundary

Read `official-design-research.md` and the local official Apple Food Truck README. That sample documents semantic typography, responsive authored imagery/layout, and explicit default/navigation destinations; it includes imagery and gradients. This is official sample/code knowledge. Current Apple HIG and rendered first-party Apple apps, including Apple Notes, were unavailable for direct comparison. The judgments below are my screenshot judgments, not claims of measured HIG compliance or a live first-party comparison. Bare minimalism earns no automatic preference.

## Blind results

Scores are whole-screen visual judgments out of 10, not accessibility certifications. A/B letters refer only to the specific screenshot pair.

| Screen | A | B | Preference | Reason |
|---|---:|---:|---|---|
| Home light | 7.2 | 7.4 | B | Free-standing cross feels like illustration rather than another app icon. Slightly taller header costs useful content room. |
| Home dark | 7.1 | 7.2 | B, narrowly | A removes the tile but leaves a clearly bounded dark square/ray field. B contains that artifact more deliberately. |
| Bible light | 7.2 | 7.4 | B | Book art is better connected to the page background. Library hierarchy and search remain legible. |
| Bible dark | 7.1 | 7.2 | B, narrowly | A's rays visibly terminate at their image frame; the underlying composition is otherwise equivalent. |
| Rosary light | 7.0 | 7.5 | B | Explicit primary Next makes the main progression understandable. Audio is demoted, although the play-only icon loses the readable Pray aloud label. |
| Rosary dark | 7.0 | 7.5 | B | Same control-hierarchy improvement; prayer text remains readable against the container. |
| Prayers light | 7.1 | 7.4 | B | Open candle illustration is warmer and better integrated than a rounded square badge. |
| Prayers dark | 7.2 | 7.4 | B | Candle glow is more naturally atmospheric here than the boxed version. |
| More light | 7.4 | 7.4 | Tie | No meaningful visual difference. Clear semantic grouping and the Font setting are strengths. |
| More dark | 7.4 | 7.4 | Tie | Same result. |

No genuine wow. This is coherent, readable, competently styled work with welcome original art. It does not yet earn AAA: most screens remain a familiar grouped-list scaffold with a small illustration in a reserved header slot. The expressive craft is present but limited. I would retain the art, not remove it to manufacture a cleaner-looking score.

The five stable icon-and-label destinations are easy to scan. The large enclosing navigation capsule plus large selected capsule makes the chrome visually heavier than the content needs. Titles and section headings create a reliable reading order. The serif Mass excerpt and Rosary text add a useful contemplative register, while list typography is serviceable rather than distinctive. Some lower rows are partly outside the visible viewport; a screenshot alone cannot determine scroll/inset correctness or actionable touch bounds.

## Three priorities

1. Finish the free-art compositing in dark mode: fade rays/glow fully before image-frame edges, keep transparent backgrounds, and preserve a compact header budget. Do not replace the illustration with a generic icon. The visible square is an asset integration issue, not an argument against art.
2. Keep Next as Rosary's primary action, and restore a readable audio name near the play control, such as Listen or Pray aloud. A disabled Back button currently occupies considerably more width than the audio action; rebalance those controls without making audio primary again.
3. Refine the shared optical rhythm: align title/art as one composition, reclaim the added header height where it only pushes lists down, and reduce nested-pill emphasis in bottom navigation. Keep the strong scripture/list typography distinction; do not add more ornament to every row.

## Known-current font selector

Both light and dark dialog captures are visually clear: a descriptive Font title, one selected radio option, recognizable Inter/Roboto/EB Garamond choices, a preview sentence, and a readable Done action. EB Garamond is visibly differentiated. Visual score: 8/10 for this component. Add a small Preview label and clarify whether the setting controls reading text or all interface text if that scope is otherwise unclear. These screenshots cannot prove actual application or persistence; I did not independently test those behaviors.
