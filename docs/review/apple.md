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

## Shared-screen requests

- **Large title before segmented control** — `BibleScreen.kt`, `PrayersAndNovenasScreen.kt`, `CatechismScreen.kt`, `DoctrineAndHistoryScreen.kt`, `SpiritualLifeScreen.kt`, `PilgrimageAndHabitsScreen.kt`: the `AppSegmentedTabs` is placed above `AppScreenTitle`. iOS order is large title → (search) → segmented control. Move the segmented control below the title/subtitle (as `RosaryScreen` and `HabitsAndVirtuesScreen` already do).
- **Doctrine has no large title** — `DoctrineAndHistoryScreen.kt`: the first tab opens straight into chips and a section header. Add an `AppScreenTitle` (e.g. "Doctrine & History") above the segmented control.
- **Segment labels too long** — `DoctrineAndHistoryScreen.kt` ("Catechism & Doctrine", "Mary & Jesus Christ"…) and `SpiritualLifeScreen.kt` ("Stations of the Cross") truncate at four segments on a phone. Use short labels: "Doctrine", "Mary & Jesus", "History", "Churches"; "Journal", "Trivia", "Stations", "Chant".
- **Hard-coded `.uppercase()`** — `HomeScreen.kt` lines ~273, 306, 447, 700, 708, 747 (and similar in other screens): uppercase only when `design.decor.usesAllCapsLabels`. Apple apps do not set tinted all-caps actions like "DASHBOARD"/"MEDITATION"; sentence case in the tint.
- **Material dropdowns** — `DoctrineAndHistoryScreen.kt` (~line 760–800, churches & news filter): the Country/Region/City `OutlinedTextField` dropdowns are Material. Render them as `AppListRow`s with `trailingText` = current value and a chevron inside an `AppListGroup` (iOS pop-up menu button rows).
- **Over-long large titles** — "Catholic Parish & Church Finder", "Catholic Spiritual Journal", "Catholic Liturgical Calendar" wrap to two lines at 34pt. Shorten to "Church Finder", "Journal", "Calendar"; put the qualifier in the subtitle.
- **More screen gap** — `MoreScreen.kt:73` adds `Spacer(sectionGap)` after each group although `AppSectionHeader` already carries the inter-section gap; drop it (keep one for the last group only) or use `cardGap`.
- **Disabled secondary button under a search field** — `ParishFinderScreen.kt`: "Search this place" disabled sits directly under the search field and reads as a second field. Hide it until the field has text, or make it the field's submit action.
