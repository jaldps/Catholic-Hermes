# Independent Material 3 visual review, round 2

Reviewed all ten blind A/B pairs at `blind2/*_material_{light,dark}.png`, plus the two separately supplied current font-dialog images. I did not inspect application source, the A/B mapping, or builds. This review covers the Material identity only. Scores judge the rendered result against a demanding finished-product standard; they do not score the implementation effort.

I read the official Material Components Android `BottomNavigation.md`, `TopAppBar.md`, and `List.md` documentation and viewed their `topappbar-variants.png`, `bottomnav-expressive.png`, and `lists-expressive.png` references. These establish component anatomy and visual semantics. This is **not** a Google Play head-to-head comparison, and I did not access the blocked M3 website.

## Blind results

| Screen | A / 10 | B / 10 | Preference | Visible reason |
| --- | ---: | ---: | --- | --- |
| Home, light | 7.8 | 7.2 | **A** | A preserves November 2 inside a compact art-and-date band. B removes the visible date, gives the decorative art its own taller block, and pushes useful content down. |
| Home, dark | 7.3 | 7.8 | **B** | B integrates the date into the art band and brings the daily prayer list up. A spends more vertical space on separate date text and an art-only panel. |
| Bible, light | 7.6 | 7.6 | Tie | Both have a recognizable search field, selected tab, readable two-line book rows, and consistent expressive list grouping. The small banner-height difference is not a meaningful visual improvement. |
| Bible, dark | 7.5 | 7.5 | Tie | The same strong list and selection structure survives dark mode. Both still spend substantial space above the task content. |
| Rosary, light | 7.1 | 7.8 | **B** | B makes Next the clear primary action, labels Back, and gives audio a secondary play control. A promotes Pray aloud while forward progression is an unlabeled arrow. |
| Rosary, dark | 7.0 | 7.6 | **B** | B has the same stronger task hierarchy. Both versions have a large, low-contrast gray bead diagram and a relatively small reading passage. |
| Prayers, light | 7.5 | 7.5 | Tie | Search, tabs, category headings, and expressive grouped rows are coherent in both. B's slightly taller illustration does not earn a higher score. |
| Prayers, dark | 7.4 | 7.4 | Tie | Both scan cleanly; the candle art becomes rather muddy, and the broad decorative header still delays the actual prayer list. |
| More, light | 7.7 | 7.7 | Tie | Good information grouping, restrained leading icons, consistent trailing chevrons, and readable current-setting values. No material A/B advantage is visible. |
| More, dark | 7.6 | 7.6 | Tie | Good coherent settings surface. The pairs look effectively equivalent. |

**Overall: approximately 7.6/10 for the stronger visible choices. Wowed: no.** This is a credible, usable Material application. It has not reached the level where the composition, art integration, and reading experience feel exceptional. Most pairs contain small spacing differences rather than a transformation in quality.

## Fit to the actual Material references

The five-destination navigation bar has the right visible grammar: consistent icon-and-label destinations, a rounded selected indicator, and quiet inactive destinations. It fits the official expressive reference substantially better than a bespoke floating dock would. The icon/label hierarchy remains legible in both modes. A screenshot cannot certify exact dp sizing, touch targets, state announcements, or pressed/focus behavior.

The segmented lists are the strongest part. They use consistent leading content, headlines, supporting chapter counts where appropriate, and trailing navigation affordances. Tight internal gaps and differentiated outer corners closely resemble the official expressive-list example. More is especially disciplined.

The app headings and supporting type form a clear hierarchy. Serif scripture/prayer text helps distinguish reading from navigation without making the whole interface ornate. Search fields and selected tabs are recognizable. Green primary emphasis is generally restrained, and the Home feast card has a distinct tonal role. Exact contrast ratios and semantic color-token assignments cannot be established visually.

Official flexible app bars permit imagery, so the illustrations are not an M3 violation. Their current repeated, broad standalone containers do, however, make Bible and Prayers feel like the same template with a swapped picture. The approved cross, book, and candle motifs are coherent; they need more purposeful integration and less visual real estate to feel fully authored.

## Font dialog

The current light/dark dialogs are convincing option-selection UI: a clear selected radio, family names rendered as samples, and a readable sentence preview. EB Garamond is visibly distinct. Inter and Roboto are naturally subtler differences, so a live sentence preview is useful. The selected Inter preview and dark/light colors are visually coherent. **Dialog score: 7.7/10.** The supplied screenshots establish visible font choice; they do not by themselves prove application-wide family replacement or persistence. “Identity default” is also less immediately clear to a general user than “Theme default.”

## Three priority fixes

1. **Keep the compact, informative Home header and make copy consistent across modes.** The good choices are A in light and B in dark. Keep the date visible. The light screens say Home/Bible while dark says Today/Holy Bible; a color-mode change should retain the same page names.
2. **Refine the approved art into a more efficient header composition.** Make the Bible/Prayers bands approximately a quarter shallower, or integrate their motifs with the heading, while preserving the illustration and generous row readability. This should bring the first useful content closer without adding density inside the list itself. The small A/B banner growth does not improve quality.
3. **Give the Rosary's actual reading and progress a more balanced stage.** Retain B's primary Next and secondary audio hierarchy. Modestly reduce the large bead diagram, strengthen the current-stage/progress contrast, and give the prayer passage more presence. Its current pale ring looks decorative despite occupying most of the central card, especially in dark mode.

These are refinements to a sound Material foundation. I would not claim an AAA result or a reference-quality visual benchmark from these screenshots alone.
