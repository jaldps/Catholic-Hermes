# Apple identity review

Reviewer bar: Apple's own apps (Health, Fitness, Books, Journal, Weather) and
Apple Design Award winners. Scores are 1–10, harsh; an 8 still has visible
defects.

## Iteration 1 (baseline render, before this round's fixes)

| Screen | Light | Dark | Main defects |
|---|---|---|---|
| home | 7.5 | 7.5 | Two tints (blue "DASHBOARD" in a green app); grey all-caps eyebrows inside cards; blank colour square reads as a missing icon |
| bible | 7.5 | 7.5 | Segmented control sits *above* the large title; search field opaque grey |
| rosary | 7.5 | 7.5 | Blue cross on the bead map (second tint); blank swatch tile |
| prayers | 7.5 | 7.5 | Segmented above large title |
| more | 8 | 8 | 50dp holes between groups |
| catechism | 7 | 7 | Segmented above large title |
| doctrine | 6.5 | 6.5 | No large title at all; four long segments truncate ("Catechism & Do…"); blue rubric labels |
| churches_news | 6.5 | 6.5 | Material outlined dropdown fields inside an iOS card; title wraps to two lines at 34pt |
| habits_virtues | 6.5 | 6.5 | Metric caption broken mid-word ("Completio / ns"); blue current weekday |
| spiritual_life | 7 | 7 | Truncated segments; empty state title too weak |
| parishes | 7.5 | 7.5 | Disabled "Search this place" button shares the field's grey and reads as a second field |
| pilgrimage_habits | 7 | 7 | Blank swatch tiles; 28dp gaps between celebration pairs; all-caps eyebrow floating on canvas |

### Kit fixes in iteration 1
- Scaffold re-provides `LocalAppDesign` with `palette.accent` = live tint: one tint app-wide.
- UIKit translucent system fills (secondary/tertiary) for chips, search field, segmented track.
- Segmented control: one pill sliding on a critically damped spring; dark pill lighter than track (#636366); rules fade.
- Spring press-scale on buttons, chips, tappable cards and banners; row/card highlight snaps on and fades out on release.
- Tab bar: animated tint cross-fade, symbol bounce on selection, press dim.
- Icon wells lit from above (subtle vertical gradient); banner colour drawn as a lit swatch disc, not an empty tile.
- Metric caption auto-shrinks so no word is split.
- Empty state title at title3 semibold.
- `sectionGap` 28 → 24.

## Iteration 2 (after tint, fills, springs)

| Screen | Light | Dark | Notes |
|---|---|---|---|
| home | 8 | 8 | One tint now; swatch disc reads as a colour, not a missing icon |
| bible | 8 | 8 | Search field now tertiarySystemFill; still segmented-above-title |
| rosary | 8.5 | 8.5 | Cross in tint |
| prayers | 8 | 8 | |
| more | 8.5 | 8.5 | |
| catechism | 7.5 | 7.5 | |
| doctrine | 7 | 7 | Segments truncate harder at 6dp padding (fixed in it. 3) |
| churches_news | 7 | 7 | Material dropdowns |
| habits_virtues | 8 | 8 | "Completions" fits on one line |
| spiritual_life | 7.5 | 7.5 | |
| parishes | 8 | 8 | |
| pilgrimage_habits | 7.5 | 7.5 | |

Kit fixes: `FittedText` (UILabel adjustsFontSizeToFitWidth, intrinsic-safe — BoxWithConstraints crashed in intrinsic-height tile rows); metric figures sit on the tile floor; segmented labels shrink to 0.7 before truncating.

## Iteration 3–4 (after screen agents' fixes landed + kit work)

| Screen | Light | Dark | Notes |
|---|---|---|---|
| home | 9 | 9 | |
| bible | 8.5 | 8.5 | Missal tab is sparse while loading |
| rosary | 8.5 | 8.5 | Banner title wraps; bead map heavy grey |
| prayers | 8.5 | 8.5 | "Compline" said three times (segment, chip, header) |
| more | 9.5 | 9.5 | |
| catechism | 9 | 9 | Long trailing "§§ 1066 - 1690" squeezes titles to 2–3 lines |
| doctrine | 9 | 9 | |
| churches_news | 8.5 | 8.5 | 8dp gaps between search, filter group and chips |
| habits_virtues | 8.5 | 8.5 | Trailing category repeated on every row |
| spiritual_life | 9 | 9 | |
| parishes | 8.5 | 8.5 | 3-line title + 2-line subtitle + trailing in one row |
| pilgrimage_habits | 8.5 | 8.5 | Floating "Fasting and precept" footer |

Kit fixes: large titles shrink to title1 (28pt) before wrapping ("Catholic Liturgical Calendar" now one line); a row inside a card sits flush with the card's text and draws its separator above, not a stray rule at the card bottom; disabled buttons use tertiarySystemFill; `palette.gold` also maps to the tint inside the Apple scaffold (no mustard second accent).

## Shared-screen requests

Resolved by screen agents since iteration 1: segmented control above large title (all six screens), Doctrine large title, long segment labels, hard-coded `.uppercase()` on Home, Material dropdowns in Churches & News, over-long large titles, More screen gaps, disabled "Search this place".

Open:
- **Rosary banner title** — `RosaryScreen.kt` "Today's mysteries" banner: body is "Joyful Mysteries · Mondays & Saturdays" and wraps. Body = "Joyful Mysteries" only; the days already appear in the list row below.
- **Catechism part rows** — `CatechismScreen.kt` "The four parts": the `trailingText` "§§ 1066 - 1690" is 17pt and squeezes the titles to 2–3 lines. Move the range into the subtitle ("§§ 1066–1690 · The Liturgy & the Seven Sacraments") and drop `trailingText`. Use an en dash, no spaces.
- **Compline redundancy** — `PrayersAndNovenasScreen.kt` Compline tab: segment "Compline", chip "Compline", header "Compline". Replace the chips with a two-segment control or drop the section header when the chip says the same thing.
- **Churches & News spacing** — `DoctrineAndHistoryScreen.kt` Church Finder: search field → filter group → chip row are separated by ~8dp. Use `cardGap` (12) between field and group and `sectionGap/2` above the chip row.
- **Habits trailing category** — `HabitsAndVirtuesScreen.kt` "Suggested practices": "Prayer" repeated as trailing text on five rows. Group the rows under per-category section headers, or drop `trailingText`.
- **Parish rows** — `ParishFinderScreen.kt` bundled directory: a 3-line title, 2-line subtitle and the trailing district in one row. Put the district in the subtitle ("Centro · Archidiócesis de Madrid") and drop `trailingText`, or cap the title at 2 lines.
- **Calendar footer** — `PilgrimageAndHabitsScreen.kt`: "Fasting and precept / Mass obligation" floats at the gutter between groups. Make it a row in the celebration's group (`AppListRow(title = "Fasting and precept", trailingText = "Mass obligation")`) so it aligns with the rows above.
- **Rosary bead map** — `RosaryScreen.kt`: all 59 beads in systemGray at rest make a heavy grey slab. Untold beads should be the quaternary fill (hollow ring or ~30% grey), so the told ones in the tint carry the picture.
