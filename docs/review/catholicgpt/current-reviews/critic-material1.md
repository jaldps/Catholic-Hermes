# Blind Material review, round 1

Reviewed all ten `*_material_{light,dark}.png` pairs at their supplied size. No app source, randomization mapping, or other critics' findings inspected. Scores are holistic visual/product scores out of 10; 9+ would require exceptional, convincing craft. A small numeric edge is not a claim of statistical significance.

## Verdict

The illustrated treatment is the better direction for this product and the user's stated taste. It restores warmth and a recognizable devotional family without abandoning Material roles. The bare treatment is easier to scan, but its generic utility alone does not make it better designed. The illustrated screens are around **7.9/10**, not AAA yet. **I am not genuinely wowed by either set.**

The strongest improvement is the candles illustration on Prayers: legible, specific to the destination, and tonally successful in both modes. The Rosary's brighter task surface also creates useful focus. The least resolved area is the relationship between large decorative banners and immediate daily tasks.

## Blind pair decisions

| Pair | A | B | Winner | Why |
| --- | ---: | ---: | --- | --- |
| Home light | 7.6 | 7.4 | A, narrow | Art and a distinct purple liturgical card create a more memorable opening. The missing date and downward movement of daily prayer prevent a larger lead. |
| Home dark | 7.4 | 8.0 | B | `Today` + date, devotional art, a distinguishable liturgical card, and a brighter Mass surface create a clearer hierarchy. |
| Bible light | 7.9 | 7.4 | A | Book illustration gives the destination identity, with consistent tabs, search, filters and grouped book rows. Search is pushed lower than ideal. |
| Bible dark | 7.9 | 7.4 | A | The illustrated `Holy Bible` treatment feels intentional and coherent. Same tradeoff in vertical space. |
| Rosary light | 7.9 | 7.8 | Tie | A's slightly stronger task surface helps focus, but the difference is too small to call a meaningful overall win. Both have the same weak bead-state legibility. |
| Rosary dark | 8.0 | 7.7 | A | The brighter main panel separates the active prayer from settings and the surrounding canvas. B is more uniformly dark. |
| Prayers light | 7.5 | 7.9 | B | Candles make the page feel like a devotional destination; search and grouped rows remain understandable. The banner is a little too tall for a frequently searched index. |
| Prayers dark | 7.5 | 7.9 | B | The candles retain their warmth and shape in dark mode. This is the most successful illustrative header. |
| More light | 8.0 | 7.8 | A | Both have clean, restrained grouping; the visible Font row makes personalization discoverable. Its behavior is not established by a screenshot. |
| More dark | 7.8 | 8.0 | B | Same clean grouping; B visibly exposes the Font setting. This is a small usability gain, not a major visual redesign. |

## Comparison with actual official Material references

Read `components/TopAppBar.md`, `components/BottomNavigation.md`, `components/List.md`, `theming/Typography.md`, and the adaptive/navigation-rail guidance in the local official Material Components Android checkout. Viewed the actual official image assets alongside this evaluation: `topappbar-variants.png`, `bottomnav-expressive.png`, and `lists-expressive.png`.

This is a **comparison against official component references**, not a direct comparison against Google Play, Gmail, or another Google app. No Google application screenshot was inspected; no such equivalence is claimed.

- **Navigation:** Both sets correctly show five top-level destinations, persistent icon-and-label pairs, and a single capsule active indicator. This corresponds to the official navigation-bar anatomy and its three-to-five-destination purpose. The selected destination is visible in every supplied screen. Small icon labels remain a legibility concern. A static image does not establish target sizes, semantics, focus or transition behavior. I would not call it an exact Expressive implementation: the component references distinguish active-label color/weight and indicator measurements, which I have not measured here.
- **Lists:** The segmented groups, rounded outer corners, small inter-row gaps, leading icons where useful, and trailing chevrons closely resemble the official Expressive segmented list image. The More screen uses the pattern especially well. There is no Material requirement to eliminate illustration or to turn every screen into the same dense list.
- **App bars and hierarchy:** The page titles have a clear heading role, but these shots alone cannot establish whether they implement small/flexible app-bar scrolling behavior. The official docs explicitly allow imagery, larger titles, subtitles and filled actions in flexible app bars. The restored art is therefore compatible with Material. It currently reads as a separate rounded banner beneath a title rather than a highly integrated flexible header; that is a craft opportunity, not a component violation.
- **Typography:** Material's official typography guidance explicitly supports brand font faces and themed scales. The sans UI text plus serif devotional reading is a sensible division of roles. A visible `Font — Identity default` setting is good discovery; it does not prove that a real alternative family works. Screens of the selector and resulting reading/UI text would be needed to evaluate that requirement.

Component-role confidence is roughly **8/10** for the stronger treatments. This does not elevate their total product craft score to 9/10.

## Three highest-priority flaws

1. **The artwork and immediate tasks still compete for height.** Keep the illustrations. Their repeated roughly 120-pixel full-width banner framing, however, makes Bible and Prayers search start about a third of the way down the screen and leaves fewer useful rows visible. Home adds both a banner and a separate large feast card before Mass and daily prayer. A more compact/integrated header or a demonstrated scroll-collapse behavior would retain identity while giving daily actions a stronger opening position. Home light's absent date is a real omission in the supplied image, already known to the root agent; the dark `Today` treatment is the stronger model.
2. **Rosary progress states are visually too faint for the space they occupy.** Most of the large bead ring is a low-contrast outline, while the current position is a tiny green mark. The textual `Step 1 of 85` helps, but the art should communicate current/completed/upcoming positions more decisively through size, fill or shape as well as hue. The next-step arrow is also much less prominent than the audio action, so progression relies on a relatively quiet control. The stronger raised panel is worth keeping.
3. **The identity is present but still feels templated, and accessibility evidence is incomplete.** The same rounded green banner, scalloped halo and small floating ornaments repeat across destinations. It is pleasant and coherent, but not yet distinctive enough to earn a wow response. Preserve the illustration family while giving each composition more specific purpose and tighter integration with its page heading. Keep functional text clear: small `Opening`, scripture-reference and bottom-nav labels deserve particular scrutiny under larger font settings. None of these phone-sized static captures establishes font scaling, contrast ratios, screen-reader behavior, reduced-motion behavior, wide-screen navigation or adaptation. Those remain unverified, rather than passed or failed.

## Practical recommendation

Proceed with the illustrated direction, the stronger Rosary task surface, and the visible Font setting. Restore the Home date, tighten the banners' relationship to search/daily actions, and make Rosary progress legible. Preserve the clean More screen; it does not need an ornamental hero merely for symmetry. Do not report this round as AAA or a Google-app match.
