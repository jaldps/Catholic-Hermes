# Catechism — progress note (paused)

## Done (all written, not yet verified by a successful compile)

- `app/src/main/java/com/example/ui/screens/CatechismScreen.kt` fully rewritten (about 2,400 lines):
  - Opens on the book: `AppDetailHeader` with `heroes/lumen/catechism.jpg` (tag `catechism_hero_photo`), capsule search (`ccc_search_input`, `search_clear_button`), "Continue reading" card (`catechism_continue`, SharedPreferences `catechism_reader`), Contents (Prologue + 4 parts, tags `catechism_part_0..4`, accent dot on the last-read part), Compendium and formulas rows, official-text rows (CCC and Compendium both in-app, "Other languages" expands), offline footnote + art credit.
  - Search: debounced 200 ms, off main thread; a § number (`1324`, `§1324`, `#1324`) gives a "Go to §" card; topic search gives tappable ranked rows with snippets and highlighted words (`catechism_search_result_<n>`, `catechism_search_count`, `catechism_search_paragraph_numbers`); designed empty and out-of-range states.
  - Page stack kept in `rememberSaveable` (Index, Part, Reader, Compendium, Answer, Formulas). `AppBackHandler` pops it; at the root back leaves to whoever opened the Catechism (MainApp's back stack). Deep link `initialParagraph` opens `[Reader(section, focus=n)]`.
  - Part page (`catechism_section_<first>`, `catechism_key_<n>`), reader (real text only, § number inline, citations dimmed, clean-up in `cccDisplayText`, focus bar and wash on the target paragraph, progress line `catechism_reading_progress` and `catechism_chapter_progress`, prev/next **section** `paragraph_prev_button` / `paragraph_next_button`, end of book state), contents sheet (`catechism_contents_sheet`, "Go to §" `catechism_goto_input`), shared Aa sheet via `AppReadingSettingsSheet`.
  - Compendium grouped honestly (consecutive identical Q&As collapse into "50–100"; `compendium_*` tags), answer page with "Read in the Catechism · §§ a–b" (`compendium_open_ccc_<n>`), formulas page with hanging numerals.
  - No fabricated "En Resumen" notes, no synthetic titles. Curated titles appear only as "Key paragraphs" rows (title + real opening text).
- Tests written: `app/src/test/java/com/example/CatechismReaderLogicTest.kt` (pure logic), `CatechismInteractionTest.kt` (flows), `app/src/test/java/com/example/screenshots/CatechismScreenshotTest.kt` (renders into `build/screenshots/catechism/`).

## Compile state

First compile failed with 3 errors in my file only: duplicate `groupPosition` / `foldText` names elsewhere in the package, and a missing `scrollBy` import. Fixed (renamed to `catGroupPosition` / `cccFold`, added `androidx.compose.foundation.gestures.scrollBy`). A re-compile (`tools/device/gradle.sh -q :app:compileDebugKotlin`, task `boxuvc29b`) is queued behind other agents' Gradle jobs; its result is **not yet seen**. Note: DoctrineQuestions.kt (not mine) also errored in the first run because of the duplicate-name clash, which my rename should also resolve.

## Remaining

1. Run the compile; fix any further errors in `CatechismScreen.kt` (likely small: icon imports, lambda typing in `CatRow.trailing`, `Modifier` chains).
2. Run `CatechismReaderLogicTest` (check the text clean-up leaves 0 leftovers; adjust regexes if not) and `CatechismInteractionTest`, plus `AppNavigationTest`, `CatechismCoverageTest`, `DesignContrastTest`.
3. Renders: `-Pshots.only=... --tests 'com.example.screenshots.CatechismScreenshotTest'` in ES/EN/PT, light and dark; look at them and fix defects. Then the phone (More → Catecismo, search 1324, Aa, Contents, 101 Questions → Abrir en el Catecismo → back).
4. Write `docs/redesign/reviews/catechism-round1-builder.md` (changes, screenshot paths, gaps, inventory tick).

## Deviations / notes for the builder note

- The scaffold's top bar paints over content when scrolled, so **Contents** and **Aa** are a floating glass action bar at the bottom of the reader (the dock is hidden there), not top-right circles.
- `reader_font_decrease|increase|choice` tags are gone: Aa now uses the shared `AppReadingSettingsSheet` (tags `reading_size_*`, `reading_font_*`). No test referenced them.
- `back_to_catechism_outline` is on the part crumb in the reader header; `back_to_catechism_index` is on the sheet's "Full index" row.
- Hard-wrapped text is joined unconditionally (the corpus has no intentional line breaks), a simplification of the plan.
- Data defects to report (not fixed): EN footnote digits and hard wraps, PT "(n)" markers and footnote apparatus appended to section-final paragraphs (all cleaned at display time), ES/PT paragraphs that are English, §2349/§2357 empty, Compendium ~37 distinct Q&As behind 598 numbers.
- Deep-link `endParagraph` is not yet passed by MainApp.
