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

## Round 4 shared-screen requests

Kit changes this round, which screens can now rely on: `AppSectionHeader` is
left-ranged and draws the **only** thick-thin rule under itself. A short subtitle
(≤22 chars, no full stop) is set opposite as a count ("PENTATEUCH ……… 5 books").
`AppListGroup` and `AppCard` no longer open with a rule (a card closes with a
hairline). `AppChip` is now a lozenge-led small-caps text filter with no box.
`AppRow` with a Roman-numeral `trailingText` ("I", "IV.") hangs it in the left
margin as a red figure. `AppEmptyState` draws an engraved vignette. `AppBanner`
is a rubricated calendar heading. `AppPrimaryButton` has a gilt inset.

1. **Do not add `AppDivider()` before or after `AppListGroup`, `AppSectionHeader` or `AppSearchField`.** The search field's underline followed by a group (Prayers, Churches & News) currently gives two rules about 20dp apart. Put the search field directly above the first row, or put a `AppSectionHeader` between them.
2. **Chip rows in `horizontalScroll`** (Churches & News filters, others): add `contentPadding`/end padding equal to `spacing.gutter`. The fourth filter's lozenge is cut at the screen edge. For six or fewer filters, prefer `FlowRow` with `horizontalArrangement = spacedBy(14.dp)`.
3. **HomeScreen**: drop the green ■ swatch row ("Green · Weekday · Cycle A · Cycle II") or fold it into the Banner body, because the Banner already shows the colour as a lozenge. Drop the ★ before "RULE OF LIFE". Rename "DASHBOARD" to "OPEN" or remove it. Change the copy "Grace Antidote against sins" to "the remedies against sin". Write the day name as "Saturday of the Thirty-first Week in Ordinary Time" (ordinal words).
4. **Doctrine (Commandments)**: pass the Roman numeral as `trailingText` ("I" … "X") and drop "1st:" from titles. The kit hangs it as a red marginal figure. The same applies to Precepts.
5. **Prayer detail**: the Latin incipit (`prayer.latinTitle`) should be the `AppScreenTitle` subtitle in the rubric colour. Better still, bundle **EB Garamond Italic** (`res/font/ebgaramond_italic.ttf`, plus the `FontStyle.Italic` entry in `AppFonts.EbGaramond`) so Latin incipits and scripture quotations can be true italic. Faux-oblique Garamond is worse than roman, so the kit does not synthesise it.
6. **Doctrine scripture quotation**: set it in `bodyInk` (`palette.textPrimary`) at body size, not grey at 22sp, so the quotation does not outrank the text.
7. **Spiritual Life (Journal empty)**: place the "New entry" button directly under the `AppEmptyState` (24dp gap), not pinned above the tab bar with a 300dp void.
8. **Times**: format consistently as "6:00 AM" everywhere (Habits uses "07:00 AM").
9. **Count blocks**: remove the centred "PRAYERS / 7 prayers" header, or pass the count as a short subtitle, which the kit now sets opposite the rubric.
10. **Calendar**: set references with an en dash ("Matthew 5:1–12"). Do not show Spanish dates in English ("25 OCTUBRE").

## Round 5 shared-screen requests

Kit changes this round, which screens can now rely on: a pushed screen
(`onBack != null`) gets a **one-line running head** naming the page in small
capitals. When the screen's first `AppScreenTitle` has the same title as the
scaffold, the kit does not set the title again: only the subtitle is kept, as
one italic argument line. Root tabs keep a tighter title page. An
`AppScreenTitle` with a different title inside a pushed screen (a commandment,
a prayer) is cut at 20sp. Row subtitles are now italic, which suits incipits,
references and extents. Trailing values are set in text-face figures, not
tracked caps. `AppPrimaryButton` is red capitals in a gilt double frame with
no fill. `AppSecondaryButton` is a ruled action line. `AppEmptyState` picks
its vignette from the icon: a book/comment/search/news icon gets the open
book, edit/note gets the dove, church/place gets the chalice and host, and
anything else gets the rayed cross. `AppBanner` prints the liturgical colour
as a bar.

1. **AppScreenTitle title must equal the scaffold title** on pushed screens
   (for example "Calendar & Shrines"), so the kit can fold it into the
   running head. Otherwise the page is set twice.
2. **Rosary**: the crucifix sits in a pink or red radial halo, which is a web
   glow. Draw the cross flat in rubric red, with no disc. Put the current
   prayer's text under the ring, above the action, so the text is the hero.
3. **Home**: the "Today's readings" skeleton shows mid-load in screenshots.
   Show the Gospel incipit, with the reference as a rubric, as soon as it is
   known. The "Green · Weekday" line under the Banner duplicates the colour
   bar: drop it, or pass the rank as the Banner title ("Weekday").
4. **Calendar**: set "Mass obligation" as a rubric (`AppSectionHeader`-style
   red caps, or the italic row subtitle), not as a second title line.
5. **Doctrine**: commandment rows wrap to three lines. Drop the chevron on
   rows whose detail only repeats the reference.
6. **Empty states**: pass a meaningful icon. Habits uses `Checklist` (gets
   the cross, which is correct). Journal uses `EditNote` (dove). Parishes and
   Churches use `Church` (chalice). Bible uses `MenuBook` (book).
7. **Parishes**: one primary action only. The kit now frames it in gilt, so
   two framed actions stacked look like a form.
