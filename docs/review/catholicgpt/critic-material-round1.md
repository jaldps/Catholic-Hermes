# Blind Material review, round 1

Reviewed all ten supplied A/B composites, visuals only. No source or mapping inspected. External reference websites were unavailable; this is judgment from Material 3 expertise, not a live comparison with Google or m3.material.io. Screenshots cannot establish actual hit boxes, scrolling, state layers, semantics, or motion.

**Wowed: no, by either side on any screen.** These are coherent, usable-looking Material-themed screens, roughly 6–7/10, not AAA work. Tonal containers, pills, segmented controls, and primary tabs establish recognizable M3 vocabulary. Repeated boxed illustrations, grouped rounded lists, repetitive green surfaces, and ornamental icon badges make it feel like a competent themed template rather than a distinctive first-party-quality product. Several pairs differ so little that a strong winner would be invented.

| Pair | A | B | Winner | Concrete reason |
|---|---:|---:|---|---|
| Home light | 6.5 | 6.5 | Tie | B's Today/date gives useful context, but pushes the primary Mass button partly behind navigation; A has more immediate usable content. Neither resolves the oversized ornamental banner. |
| Home dark | 6.5 | 6.5 | Tie | Virtually identical hierarchy; a tiny header vertical adjustment is not a meaningful improvement. Both obscure the primary Mass action at this viewport. |
| Bible light | 6.5 | 6.5 | Tie | A adds helpful canon context, but decorative initial circles spend horizontal space without distinguishing books meaningfully. B is faster to scan and shows more content; A's header is more informative. |
| Bible dark | 6.5 | 6.5 | Tie | B's text-only book rows are cleaner; A's initials increase recognition only marginally. The banner/search/tabs consume most of the upper screen on both. Difference is insufficient for a whole-screen winner. |
| Rosary light | 6.5 | 6.0 | A, slight | A's Pray aloud control is visibly taller and has stronger visual priority. B's compressed center action weakens the control cluster. Both underprioritize the actual prayer text. |
| Rosary dark | 6.5 | 6.0 | A, slight | Same control advantage for A. Low-contrast bead ring and oversized empty center remain unresolved on both. |
| Prayers light | 7.0 | 6.0 | A | Cleaner titles, consistent compact row rhythm, and no pointless initial badges. B forces the Saint Michael title into two lines and adds arbitrary badge colors and repeated A initials. |
| Prayers dark | 7.0 | 6.0 | A | A preserves scanning and density; B's initials add visual noise with no useful category information. |
| More light | 7.0 | 7.0 | Tie | Both clearly group content and provide useful subtitles. Small title placement differences do not materially change quality. |
| More dark | 7.0 | 7.0 | Tie | Essentially identical result. Strong basic readability; still a generic grouped menu rather than an exceptional composition. |

## Fixes in priority order

1. **Home: make the primary action visible without a scroll.** The decorative banner and large feast card consume too much height. Reduce the banner substantially, combine the date/feast context more economically, and bring Read today's Mass above the navigation region. The ellipsized Gospel is too long for a teaser yet incomplete as reading content; use a deliberate short excerpt and a clear continuation action.
2. **Bible and Prayers: prioritize the task over the header illustration.** The repeated 136-ish-pixel flat rectangular banner has hard corners against otherwise very rounded UI, and delays search and useful rows. Shrink it, integrate it into a considered header composition, or remove it. Reclaim enough height to show more of the library immediately.
3. **Prayers: keep text-only list rows.** Initials are neither informative icons nor useful alphabet indexing; repeated A markers fail to differentiate entries. They also create avoidable wrapping. If leading graphics return, they should communicate prayer type or an actual user-relevant state.
4. **Rosary: put the prayer, progress, and next action into one usable composition.** The bead visualization occupies most of the screen while the prayer body starts below it and immediately disappears below navigation. Reduce the visualization or reposition the prayer so the user can begin praying without scrolling. The empty ring center is excessive relative to its few short lines. Increase contrast between the bead path and tonal surface, especially dark mode. Keep the larger action treatment; ensure all three transport hit areas are at least 48dp, independently of painted shapes.
5. **Use a more deliberate M3 shape hierarchy.** Nearly every body surface is the same rounded rectangle. The joined list groups with tiny seams and outer-corner rounding read strongly as an inset grouped-list convention. M3 can support expressive connected groups, but this execution needs stronger intentional distinction between navigable lists, informational cards, filters, and hero content. Decorative scallops/circles/squares in More should encode categories consistently, not appear arbitrary.
6. **Improve navigation icon consistency.** The thin, detailed rosary and candle icons compete with simple home/book/grid symbols. Normalize visual weight and legibility at navigation size. The selected pill is appropriate M3; preserve that clear selection treatment.
7. **Tighten top-spacing consistency.** Several pairs change only the heading's vertical offset. Establish one coherent top-app-bar/header rhythm rather than letting individual screens float by a few pixels. More has a large gap before Study; a more compact app bar could make Settings content visible sooner.

The strongest visible choices are the clear search fields, legible body titles/subtitles, restrained tonal palette, and familiar selected-navigation pills. A screenshot alone does not demonstrate full M3 behavior: state layers, dynamic color behavior, scrolling app bars, accessibility sizing, and transitions still need independent validation.
