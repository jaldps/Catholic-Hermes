# Apple identity — independent blind round 1

Reviewed every Apple pair in `restored-round/blind1`: Home, Bible, Rosary, Prayers, and More, each light and dark. I did not read the randomization mapping or application implementation. Scores judge the complete visible screen against the requested original, polished devotional identity; 10 means exceptional shipped-product quality, not a target to award automatically.

## Evidence and limits

I read the complete local Apple-authored Food Truck README and `official-design-research.md`. The sample documents default navigation, stacks, responsive composition, semantic typography, rich imagery, and gradients. It supports combining visual character with clarity; it does not prescribe bare lists. The `sample-food-truck` checkout contained only Git metadata when inspected, so I did not inspect its rendered UI or artwork. I did not read live HIG, the blocked Dribbble link, or compare this app with Apple Notes. No HIG compliance claim follows from this review.

These are screenshots. Visible labels, contrast, selected states, and composition can be judged. Touch hit bounds, dynamic type, scrolling behavior, assistive technology, actual font switching, and transition behavior cannot be verified.

## Blind results

| Pair | A | B | Winner | Reason |
|---|---:|---:|---|---|
| Home light | 7.1 | 7.5 | B | The cross illustration and compact date give Today a deliberate devotional identity without obscuring Mass. The extra header height is acceptable here. |
| Home dark | 7.5 | 7.2 | A | The lit cross provides a welcome focal point against the black ground. The bare counterpart is clear but emotionally flat. |
| Bible light | 7.3 | 7.1 | A, narrowly | The open-book art adds character to an otherwise conventional directory. It costs roughly one list row in the first viewport, so this is a modest win, not a categorical improvement. |
| Bible dark | 7.3 | 7.1 | A, narrowly | The book illustration is readable and appropriately luminous. Search and the selected segment remain evident, though the header consumes considerable space. |
| Rosary light | 7.3 | 7.5 | B | The slightly tighter top and main-card composition brings the current prayer and actions upward. The rosary itself is informative artwork rather than a detached decoration. |
| Rosary dark | 7.3 | 7.5 | B | Same hierarchy advantage. White prayer text, green progress/action, and muted beads remain clearly differentiated. |
| Prayers light | 7.5 | 7.1 | A | The candle art makes this feel like a devotional collection. All major groups remain available, so the additional height is tolerable. |
| Prayers dark | 7.1 | 7.5 | B | The candle glow is the strongest of the added header illustrations in dark mode; it offers atmosphere without becoming a backdrop behind text. |
| More light | 7.7 | 7.5 | A | Font is a discoverable, named setting with a current value. Grouping and row rhythm are otherwise nearly identical. This is a control-discoverability win, not proof that it changes real typefaces. |
| More dark | 7.5 | 7.7 | B | The Font row improves discoverability with no visible collision or lost group. The lower saturation in several icons is also calmer. |

## Three priorities

1. **Keep the illustrations, but integrate their composition into the identity.** The small rounded-square cross, book, and candle pictures look like app icons placed beside a heading. They add welcome warmth, yet the same square silhouette and detached top-right placement feel like a repeatable template. Compose a more deliberate title-and-art relationship: a quieter, less boxed crop, coordinated optical alignment, and art scaled to the amount of useful header information. Preserve the luminous green treatment in dark mode. Do not resolve this by removing the images again.
2. **Improve the Rosary action hierarchy.** At the active prayer step, “Pray aloud” is the dominant filled button, while advancing the actual prayer is a small right-arrow circle. A first-time user has to infer what the arrow changes. Give progress an explicit accessible visible cue such as “Next prayer,” and decide whether listening or progressing is the primary task. The large rosary and large card are justified only if the current prayer and onward path stay effortless to find.
3. **Give typography an authored role beyond bold headings and default-looking UI sans.** The serif Mass quotation and prayer prose are a good start. The directory pages still resemble a competent generic settings interface. Use the real font selection work to establish a clear, readable default UI voice and a deliberate devotional reading face; expose actual family names and a sample in the chooser. Merely changing a setting label or a few heading weights would not address this. These screenshots show the Font entry, not evidence that the selected family is applied.

## Whole-screen judgment

The strongest qualities are legible titles, consistent inset groups, restrained content surfaces, clear selected tabs and segments, and a sensible separation between reading text and utility labels. The bottom navigation is stable, with icon-plus-label destinations and a visible selected state. The rainbow row icons are a coherent familiar treatment, although some bright light-mode colors are stronger than their role needs. Secondary dark-mode chevrons and hairlines are very subdued; this is visually quiet, but actual contrast and touch access need measurement rather than a screenshot verdict.

The illustrations are an improvement over a fully bare approach. Their presence does not yet produce an original AAA identity: most of the app remains a conventional grouped-list shell with a decorative tile added above it. Rosary is the most meaningfully authored screen because the artwork expresses progress and the devotional activity itself.

**Was I actually wowed? No.** I see a clear, pleasant, substantially usable design around 7.5/10, with good potential. I would not describe it as exceptional Apple-level craft, claim a match to Apple Notes, or award perfection from these screens. The next gain should come from stronger composition and demonstrated font behavior, while preserving the restored sacred imagery.
