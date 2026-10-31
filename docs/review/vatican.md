# Vatican identity — design review log

Reviewer: Vatican art director (Libreria Editrice Vaticana bar). Scores 1–10, harsh; an 8 still has visible defects.

## Iteration 1 (baseline render, before fixes)

| Screen | Light | Dark | Notes |
|---|---|---|---|
| home | 7 | 6.5 | Green palette icon + US flag emoji in a centred masthead; title visibly off-centre; nav bunched left with dead space; flat beige ground; dark is a flat brown, pink rubrics |
| bible | 7.5 | 7 | Good folio index; chips row bleeds off the edge mid-word |
| rosary | 7.5 | 7 | Solid; rubricated current entry works |
| prayers | 7 | 6.5 | Tab row clipped mid-word with no fade; "V/." set as letters, not ℣ |
| more | 7.5 | 7 | Clean, but generic list page; nothing of the book |
| catechism | 7 | 6.5 | Selected tab scrolled off-screen (clipped "VATIC"); long URLs as body text |
| doctrine | 7 | 6.5 | Bold Garamond heads + grey 22sp quotes fight each other |
| churches_news | 6.5 | 6 | Selected tab not visible at all; Material dropdown boxes |
| habits_virtues | 6.5 | 6 | Empty "0/0", ring gauge is an app widget, not print |
| spiritual_life | 7 | 6.5 | Dark primary button is a muddy mauve slab |
| parishes | 7.5 | 7 | Good; dark primary button muddy |
| pilgrimage_habits | 6.5 | 6 | Empty "Gospel" row leaves a hole; white-vestment banner label is unreadable pale gold on parchment |

## Iteration 2 (vellum, candle light, ribbon rail, centred masthead, new rubric/gold)

Kit changes: procedural vellum (grain + mottle + fibres, generated once, tiled) laid over the whole page; candle pool + umber falloff at night; centred masthead layout independent of action widths; masthead devices printed as duotone; folio rail with equal columns and a sliding rubric ribbon marker; ink-wash press indication; tapered-rule fleuron under titles; oxblood primary at night; pale liturgical pigments burnished to ochre by day; rubric 951D1C / night cinnabar D66B50; burnished night gold.

| Screen | Light | Dark | Notes |
|---|---|---|---|
| home | 8.5 | 8.5 | Now reads as a page. Fibres too visible by day (hair-on-screen) |
| bible | 8.5 | 8.5 | Selected-tab rule missing in light render; fade edge smears |
| rosary | 8.5 | 8.5 | |
| prayers | 8 | 8 | Tab strip fade smear |
| more | 8.5 | 8.5 | |
| catechism | 7.5 | 8.5 | Light: selected tab not scrolled into view |
| doctrine | 8 | 8 | |
| churches_news | 7 | 8.5 | Light: selected tab off-screen |
| habits_virtues | 7.5 | 7.5 | Screen content (0/0) |
| spiritual_life | 8 | 8.5 | Oxblood button correct at night |
| parishes | 8.5 | 8.5 | |
| pilgrimage_habits | 7.5 | 7.5 | White solemnity now legible ochre; empty Gospel row remains |

## Iteration 3–4 (tab strip opens on the selected heading on first frame, sliding rubric rule, ground-colour edge fades, quieter fibres, rosary device on the rail)

| Screen | Light | Dark |
|---|---|---|
| home | 9 | 9 |
| bible | 9 | 9 |
| rosary | 9 | 9 |
| prayers | 8.5 | 8.5 |
| more | 9 | 9 |
| catechism | 8.5 | 8.5 |
| doctrine | 8.5 | 8.5 |
| churches_news | 8.5 | 8.5 |
| habits_virtues | 8 | 8 |
| spiritual_life | 9 | 9 |
| parishes | 9 | 9 |
| pilgrimage_habits | 8 | 8 |

What still keeps screens below 9.5 is screen content and layout (see the requests below), not the kit.

## Shared-screen requests

1. **ui/MainApp.kt (masthead actions)** — the palette glyph and the emoji flag sit in the running head of every screen. The Vatican kit now prints them as a duotone device, but a flag emoji is still a sticker. Request: when `design.language == VATICAN`, show the language as a two-letter Cinzel code (`EN` / `ES` / `PT`) text button, and the theme control as a single sharp glyph tinted `palette.textSecondary`, not the green Material tint.
2. **ui/screens/BibleScreen.kt:162, PilgrimageAndHabitsScreen.kt:99, PrayersAndNovenasScreen.kt:170, SpiritualLifeScreen.kt:242** — drop `.background(design.palette.canvas)` on the root column. The scaffold already lays the ground (and, for Vatican, the procedural vellum and candle light); a flat fill hides it on these four tabs only.
3. **Chip rows (BibleScreen.kt:380, PrayersAndNovenasScreen.kt:347/1001, DoctrineAndHistoryScreen chip row, ChurchesNews filter row)** — the rows are padded on the left only, so the last chip is guillotined mid-word at the right edge ("DEUTEROCAN"). Use `contentPadding`/end padding equal to the gutter so a scrolled row ends on the margin, or wrap with `FlowRow` for ≤6 chips.
4. **HomeScreen.kt:336** — "Habits & Virtues (Rule of Life)" wraps to two lines of tracked caps beside a star glyph. Shorten to "Rule of Life" (all three languages) and drop the star for Vatican (`decor.usesOrnaments`).
5. **RosaryScreen / PrayersAndNovenasScreen prayer texts** — set versicle and response as the liturgical signs ℣ and ℟ (U+2123, U+211F) in rubric red, not "V/." and "R/." in body ink.
6. **PilgrimageAndHabitsScreen (calendar)** — the "Gospel" row renders with an empty value, leaving a 90dp hole. Hide the row when the reference is blank.
7. **HabitsAndVirtues** — "0/0" under "Daily practices & devotions" and "0 of 0 kept today" read as a broken counter. With no practices configured show an EmptyState ("No practices yet" + one action) instead of the counters.
8. **CatechismScreen (official sources)** — raw `https://…` URLs set as body text. Show the host only (`vatican.va`) in small caps, or drop it: the button already says where it goes.
9. **AppIconSet.kt** — `PRAYERS` uses VolunteerActivism (a hand giving a heart: charity, not prayer) and `ROSARY` a seated meditator. The Vatican kit substitutes its own rosary device on the rail; please supply a proper sharp glyph for both slots (praying hands / rosary) so all identities benefit.
