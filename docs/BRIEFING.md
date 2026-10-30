# Sancta Fides — project briefing

Branch: `feat/bible-correctness-and-visual-identities`
Build: `dist/sancta-fides-1.0.apk` (signed, sideload-ready)
Tests: 55, all passing

This document is the handover: what the app does, what was wrong with it, what
was changed, what is still open, and what is worth doing next.

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
- **The Catechism.** The text is complete and genuine — all 2,865 paragraphs,
  official text, three languages. But the browse tree names only **26** of them,
  so the rest are reachable only by typing a number. `CatechismOutline` now
  provides a 31-section index tiling 1..2865 without gap or overlap; the screen
  does not consume it yet. This is the single highest-value remaining fix.
- **Screen migration: 5 of 13.** Migrated: Home, Bible, Catechism, Rosary,
  Parishes. Not migrated: Habits & Virtues, Prayers & Novenas, Doctrine &
  History, Spiritual Life, Scripture Study, Night Prayers, Pilgrimage,
  Devotions Explorer. Unmigrated screens still inherit each identity's colour,
  type and shape, but not its structural idiom.
- **The critic loop ran one round.** Apple and Vatican were revised by it;
  Material's findings were applied to the shared tokens.

### Reported, not yet fixed
- "Leer la misa y comentarios" on Home navigates to the Bible rather than the
  Mass. Note the app now computes the real Mass of the day itself, so the
  Vatican News page — and its cookie banner — may not be needed at all.
- The versions comparator's buttons were reworked during migration but not
  checked against the original complaint.
- The parish region filter does not list all options per country.
- The Via Crucis screen has the same squeezed-button problem the Rosary had; it
  lives in `DoctrineAndHistoryScreen`, which is not yet migrated.

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
- Source a genuinely Catholic Spanish and Portuguese text, or at minimum
  normalise "Jehová" → "el Señor" and backfill the two missing deuterocanonical
  passages.
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
