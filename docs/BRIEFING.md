> Current work is on `CatholicGPT`. For the current UI direction, implementation and checks, read [the CatholicGPT review](review/catholicgpt/README.md). The handover below describes the source branch and its historical APK; it is retained for content and product context.

# Sancta Fides — project briefing

Branch: `feat/bible-correctness-and-visual-identities`
Build: `dist/sancta-fides-1.3.apk` (versionCode 4, signed with the same key as 1.0, so it installs over it)
Tests: 100, all passing on the code before 1.3's last two Vatican fixes (see §0.6)

This document is the handover: what the app does, what was wrong with it, what
was changed, what is still open, and what is worth doing next.

## 0. Release 1.3: what changed since 1.0, and what is left

### 0.1 Navigation
- **Tabs.** The 11-destination bottom bar is now four tabs (Home, Bible, Rosary, Prayers) plus **More**, inside the 5-item limit of both an iOS tab bar and an M3 navigation bar. More groups the other screens under Study, Inner life, Places and Settings.
- **Back.** A screen opened from More, or from a Home shortcut, gets a back affordance in its identity's own chrome and returns to the tab it came from (`MainApp.kt`, `origin`). The scaffold receives the current screen's title (`AppKit.Scaffold(title, …, onBack, backLabel)`).
- **Settings.** Appearance and language moved from top-bar icons into Settings under More.

### 0.2 The three identities: design rounds 3–5
- **The loop.** One agent per identity reviewed real renders and fixed its own kit; shared-screen requests went to separate workers. Fresh, independent judges then compared before/after pairs blind.

  | Round | Apple | Material | Vatican | Blind pairs won by the newer build |
  |---|---|---|---|---|
  | 3 vs 2 | 6.9 | 6.8 | 7.1 | 72/72 |
  | 4 vs 3 | 7.1 | 7.25 | 6.85 | 71/72 |

  Every judge's verdict was "**not wowed**". The full reports are in the session scratchpad (`judge5_*.md`); their main findings are under §0.7.
- **Apple** (`AppleKit.kt`):
  - Colour: one liturgical tint, iOS system colours on icon tiles chosen by meaning, iOS-style translucent fills, and AA contrast on buttons and secondary labels.
  - Hero: the liturgical day is drawn as a vestment-colour card.
  - Navigation: the nav bar fades its inline title in under the large title.
  - Motion: sprung press and selection animations.
- **Material** (`MaterialKit.kt`):
  - Title: a large collapsing top app bar carries the screen title, so there is one title per screen.
  - Navigation: M3 primary tabs for view switching.
  - Lists: Android 16-style segmented list groups and Settings-style section headers.
  - Expressive: hand-built shapes (soft-burst seal on liturgical banners, cookie empty states, clover icons) and spring shape morphs on press.
  - Colour: liturgical colours harmonised toward the scheme.
- **Vatican** (`VaticanKit.kt`):
  - Paper: fine laid-paper grain; in dark mode, a candle-lit umber with one deep vermilion.
  - Navigation: a folded silk ribbon marks the selected tab over engraved tab glyphs; pushed screens get a compact running head.
  - Missal typesetting: small-caps text filters instead of chip boxes, missal rules, Roman numerals hung in the margin, a red drop initial, gilt-inset buttons, an engraved empty-state vignette, and true EB Garamond italics (bundled) for Latin.
- **Shared** (`ui/design/DevotionalGlyphs.kt`, `ui/components/SacredArt.kt`):
  - Glyphs: drawn rosary and votive-candle glyphs replace the stock meditator and charity hand.
  - Illustrations: procedural sacred illustrations (cross in glory, open book, rosary, candles, basilica, dove), each drawn in its identity's idiom. They sit as heroes on Home, Bible, Prayers, Spiritual life and Parishes.

### 0.3 Screens and content
- **Layout.** Every screen opens title → (search) → one control, never tabs above the title.
  - Filters: at most one filter layer per screen; secondary filters became menu rows (`MenuPickerRow`).
  - Lists: long text cards became concise rows that open their own page (`InlineBackLink`).
- **Rosary.** Drawn as a real chaplet: five decades, medal, pendant and crucifix. Prayed beads are filled and the current bead is haloed; the ring and its controls sit above the fold.
- **Calendar.** Computed from today's date: today plus the next four weeks' Sundays, feasts, solemnities and fast days. The rank sits in the hero, and each day's Gospel is attached to it.
- **Content.**
  - Home: a "Continue reading" row.
  - Habits: a real empty state with suggested practices.
  - Liturgy: November 2 is a Commemoration, with Gospel John 6:37–40.
  - Localisation and formatting:
    - Parish schedule notes are translated (`ParishScheduleText`).
    - Times are localised ("7:00 AM" vs "7:00").
    - Scripture ranges use en dashes.
    - Liturgical wording is used throughout ("Saturday of the Thirty-first Week in Ordinary Time").
    - Developer jargon is gone ("Dashboard", "Geographic Filter Dropdown Menus").

### 0.4 Bible cross-references (new in 1.1)
- **Portrait.** Tapping a verse opens a sheet with:
  - the verse;
  - an arc strip showing its reach over the whole canon, coloured by testament;
  - its references, strongest first, each with its text from the bundled Catholic Bible in the reader's language, and each tap opening the target highlighted (Back retraces the trail);
  - the patristic gloss, when there is one.
- **Landscape.** The reader becomes a study: the text beside the whole Bible's web of connections, with the chosen verse's (or the chapter's) arcs over it.
- **Data.**
  - Source: OpenBible.info (CC BY 4.0, mostly the *Treasury of Scripture Knowledge*), keeping the strongest 30 per verse; about 152k per language.
  - Numbering: re-keyed from King James numbering to each edition's Vulgate-tradition numbering — Psalm renumbering with title verses, Daniel's Greek additions, and a word-similarity alignment for the Douay-Rheims (98.7% of verses land on their best match).
  - How to rebuild the assets: `tools/xref/README.md`.
  - Coverage gap: the deuterocanonical books have no references in the source, and the sheet says so.
- **Code.** `data/xref/CrossReferenceIndex.kt`, `ui/screens/CrossReferenceViews.kt`, `ui/components/CrossReferenceArcs.kt`.

### 0.5 1.3 itself
- The Vatican identity's Roman numerals overlapped their text; they now hang in a wider margin column.
- The sacred illustrations spilled their rays over titles and tabs; they now clip to their frame.

### 0.6 Verification
- **Tests.** 100 unit tests, 0 failures, on the code of 1.2 (which is 1.3 minus the two Vatican fixes). 1.3 was built without a full re-run; its two fixes were checked on four Vatican screens in dark mode.
- **Screenshot tests.**
  - `FullAppScreenshotTest` renders MainApp at every destination × identity × light/dark: 72 PNGs at phone size in `app/build/screenshots/app`. `-Pshots.only=home,bible` narrows the destinations, and `-Pshots.lang=es|pt` renders in another language.
  - `CrossReferenceScreenshotTest` renders the verse sheet and the landscape study.
  - `SacredArtScreenshotTest` renders the illustration gallery.
- **Behaviour in screenshot runs.** Looping animations hold still under `LocalInspectionMode`. Without that, Robolectric never reaches idle and the run hangs.
- **Gradle.** Concurrent builds must share `flock /tmp/gradle.lock`.

### 0.7 What is left
**The design goal is not met.** The independent blind judges rate every identity about 7/10 and are "not wowed". They also have not judged the sacred illustrations or 1.3. The next step is another blind-judging round against the 1.3 renders, then fixes, repeated until each judge is wowed. Their open findings:

- **All identities**
  - Many screens are still "a Settings-app skin": the same stack of text rows. Each top-level tab needs a signature moment; the illustrations are a first step, still unjudged.
  - Parishes and Churches: a map with distances, one "near me" action, and the next Mass time per result.
  - The Bible's Old Testament / New Testament / Deuterocanonical canon filter should come back as a single compact control.
  - Home: a reading preview, and a lighter hierarchy below the hero.
- **Apple**
  - Icon tiles must never repeat a colour within one group.
  - Try a serif (EB Garamond) for large titles, as Books and Journal do.
  - Replace the floating "New entry" with a nav-bar button.
- **Material**
  - The large app bar reserves supporting-text space even when there is no subtitle, leaving a gap.
  - Use segmented buttons only for 2-option value switches, with checkmarks; screens with 3 or more options should use tabs.
  - Vary row containers: numeral badges and shaped icons per category.
  - Add a wavy progress indicator to the Rosary.
- **Vatican**
  - The root-tab title page is still tall.
  - Liturgical typesetting is not done throughout: ℣/℟ sized to the text and italic incipits everywhere.
  - Vary the empty-state vignettes by subject.
  - Check the dark-mode red and gold contrast again.
- **Cross-references**
  - The landscape study has not been seen on a real phone. The app's top and bottom bars take height from it, so consider hiding them in landscape.
  - The density of the background web needs a device check.
- **Coverage gaps**
  - The detail pages added in round 4 (prayer, commandment, church, chaplet) are not in the screenshot harness.
  - Spanish and Portuguese renders have only been spot-checked.

---

## 1. What the app is

An Android app (Kotlin, Jetpack Compose, Material 3, minSdk 24, targetSdk 36)
— a Catholic companion in Spanish, English and Portuguese. Ten destinations:
Home, Habits & Virtues, Bible, Catechism, Rosary, Prayers, Sanctuaries,
Doctrine, Parishes, and Journal & Quiz.

All content ships inside the APK; nothing is fetched to read scripture, pray a
novena or open the Catechism. The app works fully offline apart from the parish
finder and the in-app browser.

---

## 2. The defects that prompted this work

### 2.1 The Bible served the wrong books

The single most serious defect. The bundled Bible was keyed by the wrong book
order: a crawler had zipped the app's Catholic 73-book id list *positionally*
against a source served in Protestant 66-book order with the seven
deuterocanonical books appended at the end. Every book from Esther onward held
another book's text.

| You opened | You actually read |
|---|---|
| Esther | the Psalms |
| Judith | Job |
| 1 Maccabees | Proverbs |
| Isaiah | Joel |
| Zephaniah | John's Gospel |
| James | Revelation |
| 2 John | Sirach |
| Revelation | 2 Maccabees |

All 35,746 verses were present and correct, so this was a **lossless remap**,
not a re-crawl. Esther's declared chapter count was also wrong (10; the
Catholic book with the Greek additions has 16).

### 2.2 The three languages disagreed about what a Psalm is

Found while testing, and it would have been hard to spot by hand. The English
text is the Douay-Rheims, whose psalter is **Vulgate**-numbered. The Spanish and
Portuguese psalters were **Masoretic**-numbered. So `Psalm 22` was a different
psalm in each language — "The Lord ruleth me" in English, "¿por qué me has
dejado?" in Spanish — and every lectionary psalm citation would have resolved
to the wrong text in two languages out of three.

Both psalters were renumbered to Vulgate, verse-for-verse, using the Church's
own concordance (Masoretic 9+10 → Vulgate 9; 11–113 → 10–112; 114+115 → 113;
116 → 114+115; 117–146 → 116–145; 147 → 146+147). Every verse preserved; all
150 psalms now align across the three languages.

### 2.3 The theme system was dead code

`SanctaFidesTheme` was invoked with **no arguments** from `MainActivity`, so the
five colour presets, the dark-mode setting and the typography controls had no
effect whatsoever. Preferences were written to Room and never read back.

### 2.4 The liturgical day ignored the date

`getCurrentLiturgicalDay()` returned `upcomingLiturgicalDays.first()` — the
first entry of a hand-seeded list — regardless of what day it actually was. The
daily missal was a single hard-coded reading.

### 2.5 Tests asserted the placeholder behaviour

The Bible test built the repository with no `Context`, so it exercised a
synthetic-verse generator rather than the real assets, and asserted only that
chapters were non-empty — which the broken data satisfied.

---

## 3. What was changed

### Bible
- All three asset files remapped to the canonical book ids.
- Both non-English psalters renumbered to Vulgate.
- `BibleTextProvider` reads **language-first**: a chapter renders in the
  selected language's own versification instead of being forced onto the
  English one, and only one language is parsed at a time (each asset is ~4.7 MB
  of JSON, so holding all three was costly).
- The synthetic placeholder generator is gone. A missing chapter now returns
  nothing rather than inventing twelve verses.

### Liturgy
- `LiturgicalCalendar` computes the Roman Rite calendar from the date of Easter
  outward — seasons, colours, ranks, Sunday cycle (A/B/C), weekday cycle (I/II),
  movable and fixed celebrations, fast and abstinence days. Nothing is seeded
  per-year, so it is correct in any year.
- `LectionaryData` holds 191 celebrations (Sundays and solemnities), cited by
  canonical book id. `ScriptureResolver` turns a citation into readable text
  from the bundled Bible in the reader's language.
- The daily missal is assembled from the two.

### Settings
- `UserPreferencesStore` (DataStore) persists language, visual identity, theme
  preset, dark mode, typography and narration speed, and is read by the theme.

### Visual identities
Three complete identities, selectable at runtime under the palette icon:

| | Cupertino | Material 3 | Vatican |
|---|---|---|---|
| Type | Inter | Roboto | Cinzel + EB Garamond |
| Lists | inset grouped, hairlines | individual tonal cards | ruled folio |
| Shape | 10–16dp | 16/28dp, stadium buttons | 2–4dp |
| Depth | none, hairlines | tonal surfaces | ruled borders |
| Nav | fixed tab bar | pill indicator | gold-underlined rail |
| Scripture | clean reading column | tonal verse badges | illuminated initial, superscript verses |

`AppKit` is a single component contract; `AppleKit`, `MaterialKit` and
`VaticanKit` implement it. Screens call the kit, never Material directly, so
switching identity restyles structure and not merely colour. Fonts are bundled
(OFL, latin-ext, so Spanish and Portuguese accents are covered).

### Parishes
`ParishFinder` queries OpenStreetMap through Overpass and Nominatim — no API
key, no billing account. Location comes from the platform `LocationManager`, so
no Play Services dependency was added. The bundled directory remains the
offline fallback.

### Device-reported fixes
- **The top bar sat under the status bar**, so the language and appearance
  buttons could not be tapped. The app is edge-to-edge and `Scaffold` does not
  inset its `topBar` slot; all three kits now pad for it.
- Three-across button rows overflowed in Vatican capitals and in Portuguese, so
  their text wrapped one character per line. Controls are now stacked.

### Found and fixed in passing
- The Rosary displayed the **Hail Mary** text while narrating the **Our Father**.
- The offline-downloads view was a `Column` sibling of a `fillMaxSize`
  `LazyColumn`, so it rendered at zero height and was unreachable.
- Material's palette seeded M3's **baseline purple** containers under a green
  primary, so every tonal circle was a lilac disc holding a green glyph.
- Five M3 surface roles mapped onto two values, leaving dark mode with no
  visible hierarchy at all.

---

## 4. Verification

55 tests, all passing:

- **`BibleIntegrityTest`** — 73 books; every declared chapter present in all
  three languages; book ids checked against the *opening words* of 33 books, so
  a one-book shift fails; psalter alignment; Psalm 22 is the shepherd psalm in
  every language.
- **`LiturgicalCalendarTest`** — Easter and the movable feasts against published
  dates from 2024 to 2285, including the extremes of Easter's range; Advent
  always opens on a Sunday across 2000–2100; Ordinary Time stays within 1..34.
- **`MissalTest`** — every one of the lectionary's citations renders in all
  three languages, through the same resolver the UI uses.
- **`ScreenshotTest` / `AppScreenshotTest`** — render each identity to PNG. There
  is no KVM in the build container, so no emulator; Robolectric native graphics
  draws the real Compose tree instead.

---

## 5. What is still open

### Known content limitations
- The Spanish and Portuguese texts rest on **Reina-Valera** and **Almeida**,
  which are Protestant translations with the deuterocanonical books added
  separately. Consequences: the **Canticle of the Three Young Men**
  (Daniel 3:52–90) and part of the **Greek additions to Esther** are absent
  (the resolver falls back to English), and the Spanish renders the divine name
  as **"Jehová"**, which a Catholic edition would set as "el Señor".
- The lectionary covers **Sundays and solemnities**. A ferial weekday shows the
  liturgical day without readings rather than showing the wrong ones.

### Partly done
- ~~**The Catechism.**~~ **Done.** The browse tree named only 26 of the 2,865
  paragraphs, so the rest were reachable only by typing a number. A "Complete
  text" index of 31 sections now tiles 1..2865 without gap or overlap, and each
  section opens as a lazily-rendered reading view. Two paragraphs came out of
  the source incomplete (§2349 has no Spanish or English, §2357 no Portuguese);
  those fall back to a language that has the text rather than rendering empty.
  Guarded by `CatechismCoverageTest`.
- ~~**Screen migration: 9 of 13.**~~ **Done**: all 13 screens are on the kit.
- **The critic loop** has run five rounds; scores and open findings are in §0.2 and §0.7.

### Reported, not yet fixed
- ~~"Leer la misa y comentarios" on Home navigates to the Bible~~ **Fixed.** It
  now opens the Bible's Missal tab directly, which shows the app's own
  date-aware Mass. Since that Mass is computed offline from the bundled Bible,
  the Vatican News page — and its cookie banner — may not be needed at all.
- The versions comparator's buttons were reworked during migration but not
  checked against the original complaint.
- The button to read the official mass readings from the Vatican in-app shows an unformatted web view of the Vatican site, which means the cookie banner uses too much of the screen and tehre is no option to close that banner. There needs to be a workaround for this.  
- ~~The parish region filter does not list all options per country.~~ **Fixed.**
- ~~The Via Crucis screen has the same squeezed-button problem the Rosary had~~
  **Fixed.** It lives in `SpiritualLifeScreen`, not the doctrine screen. Three
  buttons abreast left no room for their labels; previous and next now share a
  row and the narration button takes its own. The labels were Spanish-only and
  are now translated.
  - ~~The button to use the GPS location doesn't produce results. It gets stuck in loading.~~
    **Fixed.** `findNearby` walked three Overpass mirrors at a 60-second read
    timeout each, so a failure spun for minutes. Each attempt is now short and
    the whole lookup is bounded at 35 seconds, after which it reports a
    timeout; the spinner also appears the moment the button is tapped rather
    than when the network call starts.
  - ~~The Geographical filter with menus does not show the proper options for each country/region/city.~~
    **Fixed.** The country menu was built from `Locale.getISOCountries()` — some
    250 countries — while the regions and cities below it could only come from
    the bundled directory's 12, so any other country gave empty menus and no
    results. Every level now comes from the directory itself, and a narrower
    choice above resets a stale one below. The Parishes tab remains the one that
    searches the whole world.
  - ~~Vatican News and Catholic Bulletins doesn't show what the Vatican updates.~~
    **Fixed by fetching it.** The Vatican publishes an RSS feed per language
    (`vaticannews.va/{es,en,pt}.rss.xml`, 50 stories, no API key), so the two
    hard-coded articles are replaced by the live feed, rendered natively and
    tapping through to the article. Rendering it natively also sidesteps the
    cookie banner entirely. Guarded by `VaticanNewsTest` against a real capture
    of the feed.

### Done in the latest round
- **Daily missal from Vatican News.** The readings page is script-driven and
  covered by a cookie banner, but Vatican News publishes the same readings as an
  RSS feed per language; the Missal tab reads it natively and falls back to the
  bundled lectionary offline. "Open on Vatican News" uses the phone's browser,
  which handles cookie consent, instead of an in-app WebView.
- **Parishes.** "Parishes near me" now hands the search to Google Maps, which
  already knows the phone's location; the in-app OpenStreetMap list is a
  secondary option and points back to Maps when its servers do not answer. The
  duplicate button is gone from the Iglesias section.
- **Novena progress is persisted** to the existing `NovenaProgressEntity`
  (completed days as a bitmask; no schema change), with "In progress" and
  "Completed" sections at the top of the novena list and "Day N of 9" on each.
- **Rosary and Divine Mercy Chaplet are fully guided**: 81 and 68 steps
  respectively, from the opening Sign of the Cross to the closing one, with the
  full text of every prayer in three languages, a plain instruction for each
  step, and a bead rail of the whole rosary that marks progress and jumps.
- **Novena text cleaned** of the source site's promo banner, analytics
  JavaScript, share chrome and readers' comments (304,499 lines removed).

### Design review round 2 — screen-level findings for next time
The kit reviewers scored the real screens at 8–9/10 in each identity after their
changes; what holds the rest back lives in screen files they could not edit:
- **Home**: the liturgical `DayBadge`s are hand-drawn grey outlined boxes (should be
  `AppChip`, or a line of small capitals in the Vatican identity); `CardHeading`
  upper-cases its labels and draws Material icons before them in every identity.
- ~~**Navigation**: eleven destinations do not fit a tab bar~~ **Done in 1.3**: four tabs plus More (§0.1).
- ~~**Catechism**: segmented control above the title; stacked buttons~~ **Done in 1.3** (§0.3).
- ~~**Parishes**: three rows per parish; clipped radius chips~~ **Done in 1.3**: one row each, opening in place; radius is a segmented control.
- **Cupertino top bar**: scrolling under it should show a blurred bar with the
  inline title, which needs scroll state the fixed `AppKit` signatures do not pass.

### Novenas — what the source actually contains
Of 325 novenas on catholicnovenaapp.com, 267 say the same prayer all nine days;
58 have distinct days. The 44 bundled novenas in that second group now carry
their real nine days. The other 202 repeat one prayer because the novena itself
does.

### Found during migration, worth knowing
- **Novena progress has never been persisted.** `NovenaProgressEntity`, its
  table and three DAO methods all exist, but nothing in the app reads or writes
  them — progress lived in a `remember` and died with the screen. It is now at
  least kept per novena rather than shared across all 246, but wiring it to Room
  is a real outstanding task.
- Journal categories are stored in Room **in Spanish**; the label is now
  translated while the stored key is left alone, so existing rows still match.
- The weekly fidelity strip indexed its weekday letters by position, so the
  letters were wrong on any day but Sunday.
- Two screens had a `FloatingActionButton` nested inside the kit's scaffold.
  No identity in the kit owns a FAB — a Material FAB is wrong in Cupertino and
  on a Vatican leaf — so both became pinned full-width buttons. Same action,
  same test tag, but it is a visible change to the add affordance.

### Dead code to delete
`LiturgicalCalendarWidget.kt`, `PrayerBeadCounter.kt` and `LiturgicalHeader.kt`
are now referenced by nothing — their content was absorbed into the migrated
screens and rebuilt from tokens.

---

## 6. Improvement ideas

**Kit gaps the migrating agents each independently asked for.** These recur
across screens and are worth adding to `AppKit`:
1. A **non-interactive badge** — `AppChip` demands an `onClick`, so every screen
   hand-rolls a static label.
2. **`AppQuote(text, attribution)`** — the pull-quote treatment should differ
   per identity, and three screens re-invented it.
3. A **card-safe section header** — only `AppleKit` honours `LocalInsideCard`,
   so `AppCard { AppSectionHeader(…) }` double-insets in the other two.
4. **`AppIconButton`** and `trailingIcon` on buttons — `VaticanKit` ignores
   `leadingIcon` entirely, so a "Next" button cannot carry its arrow.
5. A consistent **inset contract for `AppSegmentedTabs`** — the caller currently
   has to know which identity bleeds edge-to-edge.
6. **`AppProgressRail`** — every devotional screen re-invents the bead counter.

**Data and correctness**
- ~~Source a genuinely Catholic Spanish and Portuguese text.~~ **Done.** Spanish
  is now the Biblia Platense (Straubinger, 1948) and Portuguese the Matos Soares
  (1927/1932, Nihil Obstat and Imprimatur), both public domain and both
  Vulgate-numbered, so all 150 psalms align with the Douay-Rheims. This also
  closed the deuterocanonical gap: Daniel 3 carries the Canticle of the Three
  Young Men and Esther all sixteen chapters in every language. Straubinger uses
  "Yahvé" in the Old Testament; Matos Soares is pre-1990 European Portuguese.
- Extend the lectionary to ferial weekdays (Year I/II), which the calendar
  already distinguishes.
- Add proper **Room migrations**: the database still uses
  `fallbackToDestructiveMigration()`, so a schema change wipes journals, novena
  progress and habit history. Preferences are safe now — they moved to DataStore
  — but user-authored content is not.

**Build and release**
- The APK here is signed with a key generated for this build. It is **not** a
  Play Store upload key; generate one before publishing, and keep it safe — the
  same key is required for every future update.
- `google-services.json` is absent, so Firebase runs in warn/passthrough mode.
- Creating GitHub Releases is not permitted for the session that produced this
  build, so the APK is committed under `dist/` instead. A real release pipeline
  should publish it as a release artefact rather than keeping a 25 MB binary in
  git history.
