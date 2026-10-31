# Material identity review

Benchmark: m3.material.io and Google's first-party apps (Play Store, Calendar,
Gmail, Photos, Android 16 Settings). Renders: `FullAppScreenshotTest.materialLight`
and `.materialDark`. Scores are out of 10; an 8 still has visible problems.

## Scores

| Screen | It. 1 (start) | It. 2 | It. 3 |
|---|---|---|---|
| home | 7.0 | 7.5 | 7.5 |
| bible | 6.5 | 8.0 | 8.5 |
| rosary | 6.5 | 8.0 | 8.5 |
| prayers | 7.0 | 8.0 | 8.5 |
| more | 7.5 | 8.5 | 8.5 |
| catechism | 6.0 | 7.5 | 8.0 |
| doctrine | 5.5 | 7.5 | 8.0 |
| churches_news | 5.5 | 6.5 | 7.0 |
| habits_virtues | 6.5 | 7.5 | 7.5 |
| spiritual_life | 6.5 | 7.0 | 7.0 |
| parishes | 7.0 | 7.5 | 7.5 |
| pilgrimage_habits | 6.5 | 7.5 | 7.5 |

## Kit changes (MaterialKit.kt)

### Iteration 1 → 2
- Pushed screens: the app bar no longer repeats "Sancta Fides" beside the back
  arrow; it shows the arrow alone and the screen's own headline does the naming.
- View switchers that do not fit a segmented button were filter chips with
  checkmarks: wrong component (chips filter a list; they do not switch views),
  and on Doctrine and Churches they stacked a chip row on top of the real
  filter chips. They are now M3 primary tabs: fixed when every label fits an
  equal share of the width, scrollable otherwise, glass to glass, 3dp
  content-width indicator with rounded top, divider under the whole width.
- Segmented buttons now find their own gutter when a caller passes the full
  width (Rosary drew its control edge to edge).
- Lists are Expressive segmented groups: 4dp inner corners, 2dp gaps, 20dp
  group ends (Android 16 Settings), instead of free 16dp cards 8dp apart.
  A divider inside a group draws nothing; the gap is the separator.
- Motion: rows and clickable cards morph their corners on press on the M3
  Expressive fast-spatial spring (0.6 / 800); buttons square off from a
  stadium on press; row selection crossfades its container on the
  default-effects spring.

### Iteration 2 → 3
- Scrollable tabs are drawn by hand: the stock `PrimaryScrollableTabRow`
  animates to the selected tab after the first frame, so screens opening on a
  later tab slid the strip on every entry (and the light capture caught the
  selection off the glass with no indicator). The strip is now laid out at
  composition from the measured labels, opens centred on its selection, and
  only moves on a change: indicator on the default spatial spring (0.8/380),
  scroll on a critically damped spring.
- Fixed tabs when all labels fit an equal share (Bible); divider spans the
  whole width instead of stopping at the last tab.
- `MaterialSpacing.sectionGap` 24 → 16dp: with the header's own 20dp top it
  left 44dp of dead canvas between groups; now 36dp, Settings' rhythm.

### Why nothing reaches 9.5 yet
What still holds every screen under 9 is in shared screens (list below): two
titles stacked on root tabs, a primary-tinted palette icon and a flag emoji as
app-bar actions, forced all-caps eyebrows on Home, M2-style outlined
dropdowns on Churches, a pinned full-width button under a rule instead of an
extended FAB on the Journal, a clipped radius chip row on Parishes.

### Iteration 3 → 4 (coordinator request)
- Root tabs: the app-bar title is empty at rest and fades in (effects spring)
  only once content scrolls under the bar, so there is one title per screen.
  Pushed screens never show it. Re-rendered: Home and Bible now open on their
  own headline under a bar that holds only the actions.

## Shared-screen requests

- `ui/screens/BibleScreen.kt`: the testament chips now wrap, leaving
  "Deuterocanon" alone on a second line. Keep filter chips on one
  `horizontalScroll` row that bleeds to the screen edge, with the gutter as
  content padding.
- `ui/screens/CatechismScreen.kt`, `DoctrineAndHistoryScreen.kt`,
  `SpiritualLifeScreen.kt`, `PilgrimageAndHabitsScreen.kt`: tabs still sit above
  the headline. Move them under it, as Bible now does.
- `ui/MainApp.kt`: the palette action uses a filled glyph next to an outlined
  Translate glyph. Use one family, `Icons.Outlined.Palette`.

- `ui/MainApp.kt`: the palette action is tinted `colorScheme.primary` and the
  language action is a flag emoji. M3 app-bar actions are onSurfaceVariant
  icons; drop the explicit tint (or use `LocalContentColor`) and use a
  `Icons.Rounded.Translate`/`Language` glyph instead of the flag.
- `ui/MainApp.kt`: root tabs show "Sancta Fides" in the bar and a 32sp headline
  right under it, two titles stacked. Either pass the destination's title and
  let the screen drop its ScreenTitle, or pass an empty title on root tabs.
- `ui/screens/ParishFinderScreen.kt` ~L284: the radius row of five chips is a
  plain `Row`; the last chip is cut to "50". Make it `horizontalScroll` or use
  `AppSegmentedTabs` (five short labels fit a segmented button).
- `ui/screens/RosaryScreen.kt` ~L990 (and the other screens' private
  `segmentedTabsInset()` / `tabStripInset()`): callers disagree on who insets
  `AppSegmentedTabs`. Pick one rule (caller insets by the gutter) and apply it
  everywhere. The kit now copes with both, but the inconsistency will bite the
  other identities.
- `ui/screens/SpiritualLifeScreen.kt` ~L443: "New entry" is a full-width button
  pinned under a full-width divider. In M3 that is an extended FAB
  (`ExtendedFloatingActionButton`, bottom-end, 16dp margins) with no divider;
  if a FAB is not wanted, drop the divider and let the button float on the
  canvas.
- `ui/screens/HomeScreen.kt` ~L273, L306, L447, L700: eyebrow labels are forced
  to `.uppercase()` ("HABITS & VIRTUES (RULE OF LIFE)", "DASHBOARD", "TODAY'S
  MASS READINGS"). M3 uses sentence case everywhere; gate the uppercase on
  `design.decor.usesAllCapsLabels`.
- `ui/screens/HomeScreen.kt`: the liturgical chips (Green / Weekday / Cycle A /
  Cycle II) sit in their own card under the banner and look like tappable
  filters. They are metadata; fold them into the banner's supporting line
  ("Green · Weekday · Cycle A · Year II") or render them as non-interactive
  assist chips without the card.
- `ui/screens/DoctrineAndHistoryScreen.kt` ~L762: the country/region/city
  pickers are outlined text fields on a white container inside a tonal card —
  the M2 look. Use `ExposedDropdownMenuBox` with filled text-field colours on
  the card's surface, or a row of three filter chips that open menus (the
  Google Maps / Play filters idiom).
- `ui/screens/PilgrimageAndHabitsScreen.kt`: the first "Gospel" row for today
  has no reference (empty subtitle). Hide the row when the reference is blank.
- `ui/screens/CatechismScreen.kt` (Vatican links): two full-width tonal buttons
  stacked per entry make the page a wall of pills. Use one filled-tonal button
  plus a text button side by side, or make the whole entry a clickable list row
  with an `OpenInNew` trailing icon.
