# Catechism — Lumen redesign plan

Owner file: `app/src/main/java/com/example/ui/screens/CatechismScreen.kt` (1,791 lines today).
Status: step 1 (audit + plan). No code changed yet.
Reads with `DESIGN.md` and `PROCESS.md`.

The idea in one line: **open on the book, not on a menu.** Today the screen
opens on a list of vatican.va links (`mainMode = 2`). Lumen opens on the
Catechism itself: a book-cover header, one search field that also accepts a
§ number, "Continue reading", and the table of contents. You reach any
paragraph in two taps, or in one search. Reading should feel like Apple Books:
one calm column, § numbers set inline, citations in a quieter ink, and nothing
on the page that is not the text.

---

## a. Inventory: the "nothing lost" checklist

### Entry points and deep links

| Entry | Source | Today | Must keep |
|---|---|---|---|
| More → Study → "Catecismo" | `MainApp.moreSections` row `nav_catechism` | Opens on **Sources** | Yes (opens on the Index now) |
| Home → `onNavigateToCatechism` | `MainApp` sets `catechismInitialParagraph = null` | Same as above | Yes |
| 101 Questions / Doctrine screens → "Abrir en el Catecismo" | `DoctrineAndHistoryScreen` → `onOpenCatechism(ref.startParagraph)` → `CatechismScreen(initialParagraph = n)` | Opens the outline section that holds §n, scrolled to it (no highlight) | Yes, and add a highlight. `FoundationRef.CatechismRef` also has `endParagraph`, which is dropped today (see d.3). |
| Re-tapping the tab | `onNavSelected` clears `catechismInitialParagraph` | n/a (Catechism is pushed, not a tab) | Yes |
| System back | Inner `BackHandler`s pop the reader/chapter to the index; `MainApp.pop()` returns to `origin` | Back from a deep-linked reader lands on the **index**, and then on **More** (not on 101 Questions) | Fix (see c.0, d.3) |

Signature today: `CatechismScreen(repository, currentLanguage, initialParagraph: Int? = null, modifier)`.
The screenshot tests call it with `repository` and `currentLanguage` only (`AppScreenshotTest:152`), so the signature has to stay source-compatible. New parameters get defaults.

### Data and sources (all offline; nothing is downloaded)

| Data | API | Notes |
|---|---|---|
| Full text §§ 1–2865 × ES/EN/PT | `CatechismFullData.contentByNumber` via `repository.catechismParagraphText(n, lang)` (real text first) or `repository.findCatechismArticle(n)` (curated paraphrase first) | Guarded by `CatechismCoverageTest` (every § has real text in all 3 languages) |
| Outline: 31 sections that tile 1..2865 | `repository.catechismOutline`, `catechismSectionFor(n)`, `catechismParagraphCount` | Each has `partTitle`, `title`, `firstNumber`, `lastNumber`, and `biblicalReferences` at **section** level |
| Curated tree: 4 parts → 8 sections → 17 chapters → 26 "highlight" paragraphs | `repository.catechismParts` | Part `title`/`subtitle`/`description`/`cccRange`. Each curated article has a hand-written title, a **paraphrase** of the paragraph, refs and an authored `inBriefSummary` |
| Search (offline, trilingual, synonyms, ranked) | `CatechismData.searchCatechismHybrid(q, cap)` → `ScoredParagraph(number, score)` | A bare number such as "1324", "§1324" or "#1324" returns that paragraph alone |
| Compendium, numbers 1–598 | `repository.compendiumQAEntries` (`CompendiumQA`: number, part, partTitle, section, question, answer, cccReference) | Only 13 curated entries plus 24 range templates, so about **37 distinct Q&As spread over 598 numbers** (see b.5) |
| Doctrine formulas (Compendium appendix) | `repository.doctrineFormulas` (6 formulas) | Items are either "1. …" or "Label: …" |
| Official texts | `repository.officialVaticanLinks` (ES/EN/PT: `catechismUrl`, `compendiumUrl`) | CCC opens in `InAppBrowserDialog`; the Compendium opens **externally** (`ACTION_VIEW`) |

"Download of full CCC": **there is no download feature** in code. The whole
text ships in the APK, so the design states "available offline" and does not
add a download. If a PDF/export is wanted, that is a new feature (open question 1).

### Screens and states today

1. **Shell**: hero band 220dp (`heroes/church_window_light.jpg`, own bitmap decoder, two gradient scrims), title "Catecismo" over the photo, then a 3-way segmented control `Compendio | Catecismo | Fuentes`. The default is **Fuentes**; with a deep link it is **Catecismo**.
2. **Catechism / Index**: search row; "Las cuatro partes" (4 rows used as a radio selector); an ornament; a card repeating the selected part's subtitle, range, title and description; "Índice de Secciones y Capítulos" with sub-headings and curated chapter rows; another ornament; "Texto completo" listing all 31 outline sections grouped by part (this list ignores the part selector).
3. **Catechism / Search**: section header "Resultados" plus a count; a wall of up to 50 "§ n · § n …" numbers; up to 50 full `CatechismArticleCard`s (not tappable); an empty state.
4. **Catechism / Paragraph range reader** (outline section): a header row (back as the clickable text "§§ a–b", A−/A+/font chips); a progress bar with label; a divider; title + "part · refs" subtitle; each paragraph as "§ n" (heading) + text + "Ref: …"; prev/next paragraph buttons at the very end.
5. **Catechism / Chapter reader** (curated chapter): back text "← Índice del Catecismo", the same chips, progress bar, title "CCC: §§ a–b", then the curated paraphrase cards with an "En Resumen" note.
6. **Compendium**: search; a "Parte" menu row (Todas, I–IV, Fórmulas); a count; up to 598 full Q&A cards (eyebrow "Pregunta n" + CCC ref, question, answer); an empty state.
7. **Compendium / Fórmulas**: header plus 6 cards with gold bullets.
8. **Sources**: "En vatican.va" (CCC in-app, Compendium external), "En otros idiomas" (2 rows, in-app); an in-app browser dialog.

### Actions

Switch mode; type/clear search (CCC and Compendium); select a part; open a
curated chapter; open an outline section; deep-link scroll to §n; A− / A+
(×0.15, clamped 0.85–1.45); cycle reader font (Por defecto → Serif → Sans);
prev/next paragraph (scrolls the list); pick a Compendium part or the
formulas; open the CCC in the in-app browser (own language + others); open
the Compendium externally; close the browser; system back pops the reader.

### State (none is persisted to disk; all `remember`, lost on rotation and on leaving)

`mainMode`, `selectedPartIndex`, `selectedChapter`, `selectedOutline`,
`searchQuery`, `fontSizeMultiplier`, `readerFont`, `selectedFilterIndex`,
`compendiumSearch`, `webViewUrl`, and the reader `LazyListState`s.

### `testTag`s (all kept; c.9 maps each one to its new home)

`catechism_screen` (used by `AppNavigationTest`), `catechism_hero_photo`,
`ccc_search_input`, `search_clear_button`, `catechism_search_count`,
`catechism_search_paragraph_numbers`, `back_to_catechism_outline`,
`back_to_catechism_index`, `catechism_reading_progress`,
`catechism_chapter_progress`, `catechism_cross_references_<n>`,
`catechism_cross_references`, `catechism_article_<n>`, `reader_font_decrease`,
`reader_font_increase`, `reader_font_choice`, `paragraph_prev_button`,
`paragraph_next_button`, `compendium_search_input`, `compendium_part_picker`,
`compendium_count`, `catechism_source_ccc`, `catechism_source_compendium`.
Lazy keys: `catechism_hero`, `catechism_screen_tabs`, `ccc_search_row`,
`ccc_search_results_header`, `ccc_search_paragraph_numbers`,
`compendium_search_row`, `compendium_filters`, `paragraph_range_title`,
`paragraph_<i>`, `paragraph_navigation`, `chapter_title`, `chapter_article_<i>`.

### Semantics and content descriptions (all kept, all ES/EN/PT)

- Back texts: `Role.Button`, onClickLabel "Volver al índice del Catecismo / Back to the Catechism index / Voltar ao índice do Catecismo".
- Progress: contentDescription "Párrafo n de last · §§ a–b" (and "Artículo i de n · …"); stateDescription "Progreso de la lectura / Reading progress / Progresso da leitura".
- "§ n" labels and sub-headings: `heading()`.
- Current chapter article: contentDescription "Párrafo actual § n / Current paragraph § n / Parágrafo atual § n".
- Cross references: contentDescription "Referencias cruzadas: … / Cross-references: … / Referências cruzadas: …".
- A− / A+: "Reducir/Aumentar el tamaño del texto", "Decrease/Increase text size", "Diminuir/Aumentar o tamanho do texto".
- Prev/next: "Ir al párrafo anterior/siguiente § n", "Go to previous/next paragraph § n", "Ir para o parágrafo anterior/próximo § n".
- Clear: onClickLabel "Limpiar / Clear / Limpar". The hero image is decorative (`null`).

---

## b. What is wrong today

**1. Flow and hierarchy**
- It opens on **Sources**, two links to a website. The thing you came for (the Catechism) is one tap away, behind a segmented control.
- Three peers in a segmented control (`Compendio | Catecismo | Fuentes`) that are not peers: one book, one study aid, two hyperlinks. "Catecismo" also appears twice (the title and a segment).
- **Two tables of contents** on one page: the curated "Index of Sections and Chapters" (17 rows, ranges partly wrong, e.g. "Creo en el Espíritu Santo §§ 683–1065") and the "Complete text" (31 rows). The part "selector" filters only the first. The part title appears three times (row, card, sub-heading). There are about 60 rows and two ornaments.
- A part is a list row with `selected = true` (a radio pretending to be navigation).
- Search results **cannot be opened**. You find §1324 and cannot read it in context.
- The deep link drops you at the very top of a section list with no marker on the paragraph you asked for. Back goes to the index, then to More, never back to the 101 Questions screen you came from.
- Prev/next move by **one paragraph** and sit only at the end of a list that can be 237 paragraphs long. In practice they are dead.

**2. Visual (vs `DESIGN.md`)**
- A private `AssetPhoto` + `decodeSampledBitmap` copy, an ad-hoc 220dp band, two hand-made scrims, and a title painted over the photo. DESIGN asks for one shared `DetailHeader` and deleting these copies.
- Hard-coded sizes (220, 160, 48, 24, 14, 2dp, alpha 0.9), `MaterialTheme.typography.titleMedium`, and `palette.gold` (not a Lumen token). Gold bullets plus gold rules break "one accent".
- The cards-in-a-list reading layout makes a book look like a feed.
- Clear is an underlined "Limpiar" word next to the field. The back control is underlined/plain text ("§§ 26–49", "← Índice del Catecismo") that does not look tappable.
- The reader toolbar crams A−, A+ and a font chip into the title row.

**3. Reading quality**
- **English text is hard-wrapped**: 2,831 of the 2,865 EN paragraphs carry `\r\r\n` every ~72 characters. Kotlin turns that into `\n\n`, which most likely renders as blank lines mid-sentence. EN also has **1,823 dangling footnote digits** ("…disciples.'" 1 In response…"), and PT has "(1)", "(2)" markers. To be confirmed in the first render.
- ES has about 4,575 inline citations "( Mt 28,19-20)" set in full ink with a stray space after "(". These are the main source of visual noise in the column.
- In the range reader, **every paragraph** shows "Ref: Jn 17, 3; 1 Tim 2, 4". That is the section's key Scripture, not the paragraph's footnotes, so it is repeated 237 times and is misleading.
- The progress label "Párrafo 1324 de 1419" is wrong arithmetic: it is a paragraph number set against the section's last number.
- The 26 curated paragraphs open as their **paraphrase** (`findCatechismArticle` returns the curated object first). The real CCC text is bundled and ignored. The chapter reader presents 1–2 paraphrases as if they were "the chapter §§ 198–421".

**4. Honesty of displayed content (UI side, fixable in my file)**
- For the 2,839 non-curated paragraphs, `findArticleByNumber` invents a title ("Artículo CCC § 1324: El Sacramento…") and an **"En Resumen" filler** ("Doctrina perenne del Magisterio…"). Every search card shows both, as if they were Catechism content.

**5. Compendium**
- 598 full-height cards (about 150,000dp of scroll), yet only about 37 distinct Q&As. Questions 50–100 are 51 identical cards; anything not in a range falls through to the Our-Father answer.
- Eyebrow "Pregunta 50" plus the question text "Pregunta 50: ¿…?" duplicate each other.
- The CCC references ("CCC 198-421") are plain text. They should open the reader.
- "Fórmulas de la doctrina" sits inside the *Part* menu, though it is not a part. Formula items show both a gold "•" and their own "1." (double markers).
- The non-curated `section` text is Spanish in all three languages (it is only searched, never shown, but it must not leak into new UI).

**6. Sources**: the CCC link opens in-app but the Compendium link leaves the app (inconsistent). The other-language rows have no tags. `InAppBrowserDialog` has a hard-coded Spanish "Cerrar" (not my file).

**7. Engineering**: search runs on the main thread on every keystroke, and the first query builds the trilingual index (2,865 × 3 normalisations), so typing janks. There is no debounce. State is not saveable. Dead code: `DocumentLink`, `hostOf`, `caps`, the trivial `segmentedTabsInset`, and about 10 unused imports.

**8. Data defects outside my file (reported, not fixed here; see d.4)**: ~71 ES and ~112 PT paragraphs are English text; PT §1065 is 43,620 characters (Part One's whole footnote apparatus appended); §2349 ES/EN and §2357 PT are empty (filled by fallback); part titles are in Title Case ("La Profesión de la Fe").

---

## c. The new design

### c.0 Structure and navigation

One screen file with an internal page stack (`rememberSaveable`, survives
rotation). Pages cross-fade and slide over 250ms. System back and the glass
back button share one `pop()`. At the root, `pop()` calls the screen's
`onBack`.

```
Index ─┬─ Part (I–IV) ── Reader(section, focus§)
       ├─ Reader (Prologue, Continue, search hit, deep link)
       ├─ Compendium ── Answer(group) ── Reader(§ from its CCC ref)
       ├─ Formulas
       └─ (Official texts open the in-app browser)
```

- **Deep link** (`initialParagraph`): the stack is just `[Reader(section(n), focus = n)]`. Back leaves the screen and returns to the referrer (this needs MainApp, d.3). The reader's Contents button still reaches the whole book.
- From a search hit or Continue: `[Index, Reader]`. From the TOC: `[Index, Part, Reader]`.

### c.1 Index (root): "the book"

Top to bottom, 20dp gutters:

1. **DetailHeader**, art `heroes/lumen/catechism.jpg`, about 300dp from the very top (under the status bar), mist fading into the canvas over the lower 55%. A glass back circle at the top left. No top-right action. Below the fade, a **book-cover lockup**:
   - `title1` "Catecismo" / "Catechism" / "Catecismo"
   - `subhead` inkSecondary "de la Iglesia Católica" / "of the Catholic Church" / "da Igreja Católica"
   - tag `catechism_hero_photo` stays on the image.
2. 20dp, then **SearchField** (capsule): "Buscar un tema o un número" / "Search a topic or number" / "Pesquisar tema ou número". Tag `ccc_search_input`; its clear glyph is `search_clear_button`. When the field has text, the rest of the page is replaced by results (c.4). The field stays in the same LazyColumn slot, so it keeps focus.
3. 28dp, then **Continue reading** (only if a last position exists): a glass ContentCard. Leading outline book icon; `headline` section title ("El sacramento de la Eucaristía"); `footnote` "§ 1338 · Segunda parte"; a thin ProgressBar for the position within the section; a chevron. Tag `catechism_continue`. Tapping it opens the reader at that §. On first launch the card is simply absent (no placeholder).
4. 28–32dp, then section header `headline` "Contenido" / "Contents" / "Conteúdo".
   **GroupedList** with 5 rows (≥ 64dp, two lines, no truncation):
   - "Prólogo" · `footnote` "§§ 1–25" → opens the reader directly (it is one section)
   - "La profesión de la fe" · "Primera parte · §§ 26–1065"
   - "La celebración del misterio cristiano" · "Segunda parte · §§ 1066–1690"
   - "La vida en Cristo" · "Tercera parte · §§ 1691–2557"
   - "La oración cristiana" · "Cuarta parte · §§ 2558–2865"
   - Tags `catechism_part_0..4`. The row holding the last-read § shows a trailing season-accent **dot** (the one accent on the page).
5. 28–32dp, then section header "Compendio y fórmulas" / "Compendium and formulas" / "Compêndio e fórmulas", and a GroupedList with outline icons:
   - Compendio · "Preguntas y respuestas" → Compendium page (`catechism_open_compendium`)
   - Fórmulas de la doctrina católica · "Mandamientos, preceptos, dones, virtudes, obras de misericordia" → Formulas page (`catechism_open_formulas`)
6. 28–32dp, then section header "Texto oficial" / "Official text" / "Texto oficial", and a GroupedList, rows ending in an "open outward" glyph (not a chevron):
   - "Catecismo en vatican.va" (`catechism_source_ccc`)
   - "Compendio en vatican.va" (`catechism_source_compendium`; now **in-app**, the same as the CCC)
   - "Otros idiomas" with a disclosure arrow, expanding inline to the other two languages (`catechism_source_lang_es|en|pt`).
7. A footnote in inkSecondary, centred to the measure: "Texto íntegro de los 2865 párrafos, disponible sin conexión." This answers "download?" in one line. If the art is public domain with known metadata, add a second line: "Ilustración: *Title*, Artist (year)."
8. Bottom spacer clears the floating tab bar.

Above the fold on a 411×891dp phone you see the art, the title, the search field, Continue (or the first parts), and the opening "Contents" rows. The primary task, read or find, is obvious in one second.

### c.2 Part page

A transparent top bar with the glass back button. Once the title scrolls
away it becomes the blurred glass bar with an inline 17sp title.

1. `eyebrow` "Segunda parte · §§ 1066–1690"
2. `title1` "La celebración del misterio cristiano" (2 lines max at 28sp; no ellipsis)
3. `callout` inkSecondary: the part `description` from data, held to the reading measure.
4. For each of the part's 2 sections (the real CCC structure, c.10):
   - `footnote` inkSecondary "Sección primera" above a `headline` "La economía sacramental"
   - A GroupedList of its outline sections. Row title `body` ("El sacramento de la Eucaristía", wraps to 2 lines). Subtitle `footnote` "§§ 1322–1419 · 98 párrafos". The trailing value "§ 1338" in inkSecondary marks the section being read. Chevron. Tags `catechism_section_<first>`.
5. **"Párrafos clave"** (the 26 curated highlights, filtered to this part). Rows: title = curated title ("El deseo de Dios"), subtitle "§ 27 · " + the curated one-sentence summary, wrapped in full. Tapping opens the reader focused on that §, showing the real text. Nothing curated is lost; it becomes a guided way in.

### c.3 Reader: Apple Books quality

**Chrome.** At rest: a transparent bar with a glass back circle (left) and two glass circles on the right: **Contents** (outline list glyph, `catechism_contents_button`) and **Aa** (`catechism_appearance_button`). On scroll, the bar turns to blurred glass with the inline title (short section title, 1 line) and a **2dp progress line** along its bottom edge (`catechism_reading_progress`). The progress line keeps today's contentDescription and stateDescription semantics with corrected copy: "§ 1338 · 17 de 98 · §§ 1322–1419" / "§ 1338 · 17 of 98 · …" / "§ 1338 · 17 de 98 · …".

**Header (scrolls with the text).**
- `eyebrow` "Segunda parte · Los siete sacramentos"
- `title1` "El sacramento de la Eucaristía"
- `footnote` inkSecondary "§§ 1322–1419 · 98 párrafos"
- `footnote` inkSecondary "Escritura clave: 1 Co 11, 23-26; Jn 6, 51". This is the section-level reference, shown **once**, and it keeps the tag `catechism_cross_references` and its "Referencias cruzadas" description.
- Then a 32dp gap.

**Paragraphs** (LazyColumn, one item per §, key `paragraph_<n>`):
- Body in the **reading style**: the user's reader font at 19/30 for serif or 18/28 for sans, × the user's size step, in `ink`. The measure is capped at about 600dp and centred on tablets. 20dp gutters on a phone give about 42–46 characters per line.
- The **§ number is set inline as a lead-in**, the way the printed CCC does it: "1324" in 13sp, weight 600, tabular figures, inkSecondary, followed by an en space. It never takes its own line, so 237 paragraphs gain no 237 extra lines of chrome.
- **Inline citations dimmed**: parentheticals that contain a digit ("(cf. Hch 2,42)", "( LG 11)", "(1)") are drawn in inkSecondary at the same size, so the sentence reads first and the source second. "( " and " )" are normalised.
- Text clean-up at display time (private `cccDisplayText`, unit-tested over the corpus):
  1. Join hard wraps: a run of line breaks becomes one space, unless the line before it is under ~40 characters and the next line starts with a capital letter (keeps prayers and verse lines such as the Our Father).
  2. In EN, drop dangling footnote digits after closing punctuation (`[.,;:!?'"”’)]\s+\d{1,3}\s+(?=[A-Za-z])`).
  3. Collapse runs of spaces.
- The text source is `repository.catechismParagraphText(n, lang)` (the real text). The curated paraphrase is no longer shown as body text.
- Paragraph gap 20dp. Each paragraph is one merged accessibility node that reads "§ 1324. …". The heading semantics move to the section title (237 headings per page made heading navigation useless). Tag `catechism_article_<n>` sits on every paragraph (it was only on the curated ones).
- **Curated summary** (26 paragraphs only): 12dp below the paragraph, a block with a 2dp `separator`-ink rule at its leading edge, a `caption` label "Síntesis" / "Summary" / "Síntese", and the text in `callout` inkSecondary. Tag `catechism_in_brief_<n>`. It is labelled "Síntesis" rather than "En resumen" because it is the app's own summary, not the Catechism's official *In Brief* (open question 3). The synthetic filler is never shown.
- **Focus** (deep link, search hit, continue, key paragraph): scroll so the § sits about 24dp below the bar, never flush against it. Mark it with a 3dp rounded **season-accent bar** in the leading gutter, plus a 1.2s glass wash that fades out over 300ms. Its node gets the contentDescription "Párrafo actual § n" / "Current paragraph § n" / "Parágrafo atual § n" (the kept string).

**End of section.**
- A quiet row "‹ Anterior: <previous section title>" (`subhead`, inkSecondary, 44dp; tag `paragraph_prev_button`).
- A **glass ContentCard "Siguiente"** with `caption` "Siguiente" / "Next" / "Seguinte", `headline` for the next section title, `footnote` for its range, and a chevron. Tag `paragraph_next_button`. Both now move by **section**, like "next chapter" in Books, and their spoken names name their target.
- After §2865: "Fin del Catecismo" plus a SecondaryCapsule "Volver al índice".

**Contents sheet** (glass sheet, radius 28; tag `catechism_contents_sheet`):
- A FilterChips row: Prólogo · I · II · III · IV.
- The sections of the chosen part, with a check on the current one.
- A numeric "Ir al §" field with a "Ir" capsule (`catechism_goto_input`); out-of-range input shows an inline hint "Del 1 al 2865".
- The tags `back_to_catechism_index` / `back_to_catechism_outline` move to the sheet's "Índice completo" row and to the glass back button respectively, keeping their onClickLabels.

**Aa sheet** (`catechism_appearance_sheet`; shared with the Bible if the foundation adopts it, d.1):
- Size: a glass capsule stepper [A−] "100 %" [A+]. Tags `reader_font_decrease` / `reader_font_increase`, same descriptions, same 0.85–1.45 range in 0.15 steps.
- Font: a SegmentedControl "Por defecto | Serif | Sans" (`reader_font_choice`, plus `_follow|_serif|_sans` on the options).
- Changes apply live behind a ~40%-height sheet.

### c.4 Search (on the Index)

- **Debounced** 200ms and computed off the main thread (`produceState` + `Dispatchers.Default`). If it takes over 150ms, a "Buscando…" row with a small indeterminate bar appears.
- **Number query** (1–2865): the first row is "Ir al § 1324" as a ContentCard, with the section title and part as its subtitle and a 2-line snippet. Tap opens the reader focused on it. Out of range shows a hint row "Los párrafos van del 1 al 2865".
- **Text query**:
  - A `footnote` count line: "312 resultados · se muestran 50" / "312 results · showing 50" / "312 resultados · mostrando 50" (`catechism_search_count`).
  - Then a GroupedList (`catechism_search_paragraph_numbers` moves to this container, which now *is* the ranked list of numbers).
  - Each row (`catechism_search_result_<n>`) has a line 1 `caption` inkSecondary "§ 1324 · El sacramento de la Eucaristía", and lines 2–4 a `callout` snippet of about 160 characters around the first match, in the current language, with matched words in semibold ink and the rest in inkSecondary. Tap opens the reader focused on that §.
  - No synthetic titles and no filler "In Brief".
- **No results**: a designed empty state with an outline magnifier, `headline` "Sin resultados para «gracia santificante»", and a `subhead` "Prueba con otra palabra o con un número del 1 al 2865."

### c.5 Compendium page

- Top bar + back.
- `title1` "Compendio" with a `subhead` "Preguntas y respuestas del Catecismo".
- A SearchField "Buscar en el Compendio" (`compendium_search_input`). It keeps today's fixed semantics: all 598 numbers × 3 languages, an OR across number, CCC ref and text, and part chips never hide a match.
- A FilterChips row (horizontal scroll, gutter content padding; tag `compendium_part_picker`): Todas · Credo · Sacramentos · Vida en Cristo · Oración. EN: All · Creed · Sacraments · Life in Christ · Prayer. PT: Todas · Credo · Sacramentos · Vida em Cristo · Oração.
- A count `footnote` (`compendium_count`): "598 preguntas" or "23 preguntas coinciden".
- The list, grouped by part (headline header per part when "Todas" is selected). **Consecutive numbers with identical Q&A collapse into one row**:
  - leading column 56dp: "50–100" or "1" (`footnote` 600 tabular, inkSecondary)
  - title: the question with its "Pregunta n:" prefix stripped, `body`, wrapped
  - chevron; tag `compendium_q_<first>`
  - Search by number "75" still finds the 50–100 row.
  - This is the honest presentation of the data as it stands. If the data owner later bundles 598 distinct entries, the same code shows 598 rows.
- **Answer page**:
  - `eyebrow` "Compendio · Preguntas 50–100"
  - `title2` the question
  - the answer in the reading style
  - `footnote` "Primera parte"
  - a **SecondaryCapsule "Leer en el Catecismo · §§ 198–421"** that opens the reader focused on the first § parsed from `cccReference` (`compendium_open_ccc_<first>`)
  - a "Siguiente pregunta" card at the end.

### c.6 Formulas page

- `title1` "Fórmulas de la doctrina católica".
- Each of the 6 formulas: a `headline` header, then a glass card (radius 24, padding 20).
- "N. text" items get **hanging numerals** (`subhead` 600 tabular, inkSecondary, in a 28dp column), with the text in the reading font. "Label: text" items get the label in semibold followed by the text.
- No gold and no double markers.

### c.7 Dark mode ("night chapel")

- The art fades into `#171614`→`#0E0D0C`. Glass is at 7% with a 14%→4% edge. Text is `#F3F0EB`, and § numbers and citations are `#A8A29B` (≈ 7:1 on the canvas, so the dimmed citations stay readable).
- The focus bar uses the season accent. Nothing glows: the wash is glass at 12%, not a colour.
- The reader in dark mode takes no extra treatment beyond the tokens: no pure black and no pure white.
- Light mode: inkSecondary `#6B6661` on `#F2F0ED` ≈ 5.1:1 (passes 4.5:1).

### c.8 ES / EN / PT lengths (checked against the data)

| Element | Longest | Handling |
|---|---|---|
| Outline section title (row, reader `title1`) | EN "Seventh commandment: justice and the destination of goods" (58); ES "Séptimo mandamiento: justicia y destino de los bienes" (53); PT "O sacramento da Penitência e da Reconciliação" (45) | Row: 2 lines, no ellipsis. Reader `title1`: up to 3 lines (fine for a detail title). The inline bar title may ellipsise (iOS norm). |
| Part title (`title1`) | EN "The celebration of the Christian mystery" (40) | 2 lines |
| Lockup | "of the Catholic Church" | 1 line |
| Search placeholder | PT "Pesquisar tema ou número" | fits the capsule at 371dp |
| Compendium chips | ES "Vida en Cristo" | horizontal scroll; no clipping at the gutter |
| Count lines | ES "312 resultados · se muestran 50" | 1 line `footnote` |
| Next card | PT "Sétimo mandamento: justiça e destino dos bens" | `headline`, 2 lines |

All copy stays in a private `pick(es, en, pt)`. Sentence case is used
everywhere: the part and section titles come from my private structure
table (c.10), not from the Title-Case data titles.

### c.9 Tag and string mapping (old → new)

| Old | New home |
|---|---|
| `catechism_screen` | Root container of every page (unchanged, so `AppNavigationTest` passes) |
| `catechism_hero_photo` | DetailHeader image |
| `catechism_screen_tabs` (key) | Removed: no segmented control (Compendium and Sources became rows) |
| `ccc_search_input`, `search_clear_button` | SearchField and its clear glyph |
| `catechism_search_count`, `catechism_search_paragraph_numbers` | Count line; results group |
| `back_to_catechism_outline` | Reader glass back button |
| `back_to_catechism_index` | Contents sheet "Índice completo" row |
| `catechism_reading_progress`, `catechism_chapter_progress` | Reader bar progress line (the chapter tag goes on the same node through a wrapper Box, since the chapter reader no longer exists separately) |
| `catechism_cross_references_<n>`, `catechism_cross_references` | Section "Escritura clave" line (`catechism_cross_references`) and the `_<first>` of the section |
| `catechism_article_<n>` | Every paragraph |
| `reader_font_*` | Aa sheet controls |
| `paragraph_prev_button`, `paragraph_next_button` | Previous/next **section** controls |
| `compendium_*` | Compendium page (same roles) |
| `catechism_source_*` | Official-text rows |

The chapter reader (state 5) is folded into the reader. Its curated content
lives on as Part → "Párrafos clave" rows and as the "Síntesis" notes, and the
real text replaces the paraphrase.

### c.10 Book structure (private table in my file)

The real CCC division, which the outline lacks (it has only part plus
section titles). Each outline section maps to its CCC section by number range:

| | ES | EN | PT | §§ |
|---|---|---|---|---|
| Prologue | Prólogo | Prologue | Prólogo | 1–25 |
| I.1 | «Creo» — «Creemos» | "I believe" — "We believe" | «Eu creio» — «Nós cremos» | 26–184 |
| I.2 | La profesión de la fe cristiana | The profession of the Christian faith | A profissão da fé cristã | 185–1065 |
| II.1 | La economía sacramental | The sacramental economy | A economia sacramental | 1066–1209 |
| II.2 | Los siete sacramentos de la Iglesia | The seven sacraments of the Church | Os sete sacramentos da Igreja | 1210–1690 |
| III.1 | La vocación del hombre: la vida en el Espíritu | Man's vocation: life in the Spirit | A vocação do homem: a vida no Espírito | 1691–2051 |
| III.2 | Los diez mandamientos | The Ten Commandments | Os dez mandamentos | 2052–2557 |
| IV.1 | La oración en la vida cristiana | Prayer in the Christian life | A oração na vida cristã | 2558–2758 |
| IV.2 | La oración del Señor: «Padre nuestro» | The Lord's Prayer: "Our Father" | A oração do Senhor: «Pai nosso» | 2759–2865 |

A unit test (my new `CatechismReaderLogicTest`) asserts that every outline
section falls inside exactly one row, alongside the existing
`CatechismCoverageTest`.

### c.11 Persisted state

- `rememberSaveable`: the page stack, the queries, the chip selection, and per-reader scroll.
- Disk, through private SharedPreferences `catechism_reader` in my file (unless the foundation offers a store): the last-read § and its time, for Continue. Written when the reader's first visible § changes (debounced 1s).
- Reader size and font: the foundation's shared ReaderSettings if provided (d.1 #12); otherwise private prefs with the same keys.

### c.12 Loading, empty and error states

- Search warming: "Buscando…" row.
- No results: designed (c.4).
- Compendium with no match: the same pattern, with a hint "un número del 1 al 598".
- Art missing: the DetailHeader falls back to the canvas gradient, with the lockup unchanged.
- A paragraph without text (guarded by the test, never expected): a `footnote` row "Texto no disponible en este idioma".
- Browser failure is handled by `InAppBrowserDialog`.

---

## d. Component needs

### d.1 From the foundation owner (`AppKit` / `AppComponents`)

1. **DetailHeader**(asset, title, subtitle?, eyebrow?, onBack, actions, testTag), usable as a `LazyColumn` item, drawing under the status bar. It needs a **subtitle line** for the book-cover lockup.
2. **ScrollTopBar**(title, listState, onBack, actions, bottomSlot) for in-screen pushed pages: transparent at rest, glass with an inline 17sp title after scroll. `bottomSlot` carries the 2dp reading progress line.
3. **GlassCircleButton**(icon, contentDescription, onClick, modifier) at 40dp with a 44dp hit area.
4. **SearchField** with a built-in clear glyph (a `clearTestTag` param, or the tag `search_clear_button` by default), `KeyboardOptions` (number pad for "Ir al §"), IME Search, and a `FocusRequester` hook.
5. **GroupedList / Row** with: a multi-line title that never ellipsises, a subtitle, a `leadingContent` slot (for "50–100" numerals) or leading icon, a trailing value text, a trailing **dot** (season accent), and a trailing **external-link glyph** variant instead of the chevron. Rows take a `modifier` for tags.
6. **ContentCard**(icon?, eyebrow?, title, subtitle?, progress?, onClick) for Continue and Next.
7. **FilterChips** (horizontal scroll with gutter `contentPadding`), **SegmentedControl**, and a thin **ProgressBar**.
8. **Sheet** (glass, radius 28, grabber, scrim) for Contents and Aa.
9. **Section header** with an optional eyebrow line ("Sección primera" over "La economía sacramental").
10. **EmptyState** (outline icon, title, body) in Lumen style.
11. Type tokens: `title1`, `title2`, `headline`, `body`, `callout`, `subhead`, `footnote`, `eyebrow`, `caption`, plus **`reading(scale, fontChoice)`** returning the reader style. Also a tabular-figures helper (`fontFeatureSettings = "tnum"`).
12. **ReaderSettings**, persisted app-wide (size step + `ReaderFontChoice`), via a CompositionLocal, with a shared **ReaderAppearanceSheet** ("Aa"). The Bible and Prayer detail need the same control (the reference shows "Aa" on Prayer detail). If it is not shared, I build it privately with the tags above.
13. A **season accent** token reachable from screens (`LocalSeasonAccent` or similar) for the focus bar and the dot.
14. **Chrome for pushed screens**: a way for a destination to draw its own DetailHeader or ScrollTopBar, so the scaffold's own top bar is hidden for it, plus an `onBack` from the scaffold. Without this, "Catecismo" shows twice (bar and header).

### d.2 Private inside `CatechismScreen.kt`

- `CatechismPage` sealed class plus a Saver, and the stack with `pop()`.
- `CccStructure` (c.10).
- `cccDisplayText()`: cleans the text and builds the AnnotatedString (lead-in number, dimmed citations, search highlights).
- `ParagraphBlock`, `SummaryNote`, `SectionIntro`, `NextSectionCard`, `ContentsSheet`, `SearchResultRow` plus a snippet builder, `CompendiumGroups` (collapse identical consecutive entries), `AnswerPage`, `FormulaCard`, `ContinueStore`.
- Delete `AssetPhoto`, `decodeSampledBitmap`, `DocumentLink`, `hostOf`, `caps`, `segmentedTabsInset`, `SearchRow`, `EntryHeader`, `ReaderControls`, and the unused imports.

### d.3 From the MainApp owner (small and additive)

- `CatechismScreen(..., onBack: () -> Unit = {})`, with the scaffold chrome hidden for CATECHISM (d.1 #14).
- **Return to the referrer**: when `onOpenCatechism` fires from a pushed screen (QUESTIONS, DOCTRINE, …), back should return there, not to `origin` (More). This needs a one-level "previous" or a real stack in `go()`/`pop()`.
- Optional: pass `endParagraph` (`initialParagraphEnd: Int? = null`) so a range link can mark §a–b, and `onOpenBible` for tappable Scripture citations (phase 2, not needed for v1).

### d.4 For the data owner (reported; not blocking my v1, which cleans text at display time)

1. EN full text: hard wraps (`\r\r\n`, 2,831 paragraphs) and 1,823 footnote digits. PT: "(n)" markers. Fix at the source with a one-off script.
2. Language leaks: ~71 ES and ~112 PT paragraphs are English (e.g. ES §25, PT §33).
3. PT §1065 is 43,620 characters (footnotes appended).
4. `findArticleByNumber` synthesises a title, an "In Brief" and section-level refs for non-curated §§. I stop showing them, but other screens may still show them. An `isCurated` flag, or nulls, would be cleaner.
5. Compendium: about 37 distinct Q&As behind 598 numbers; non-curated `section` is untranslated; the "Pregunta n:" prefix is baked into the question. Bundling the real text is a licensing decision (© LEV), which is open question 2.
6. Curated chapter ranges are wrong in places; part titles use Title Case.
7. `InAppBrowserDialog` "Cerrar" is not localised (components owner).

---

## Verification (once the foundation API lands)

1. `tools/device/gradle.sh -q :app:assembleDebug` after each edit.
2. Renders: `FullAppScreenshotTest -Pshots.only=catechism -Pshots.lang=es` (then en and pt), light and dark, plus `-Pshots.height=1600`. These show the Index only, so I will add **my own** `CatechismScreensTest` (Robolectric) to render the Reader (`initialParagraph = 1324` and `= 1`, EN, to prove the text clean-up), Search ("gracia" and "1324"), Part II, the Compendium and an Answer, in light and dark.
3. Unit test `CatechismReaderLogicTest`: the structure table covers 1..2865; `cccDisplayText` leaves no `\n\n` mid-sentence across all 2,865 × 3 texts, no "( ", and no dangling EN digits; Compendium grouping keeps every number 1..598 findable.
4. Behaviour: `AppNavigationTest`, `CatechismCoverageTest`, `DesignContrastTest`, `FontPickerInteractionTest`.
5. Phone: More → Catecismo; search "1324" → open; Aa; Contents → Part IV; 101 Questions → "Abrir en el Catecismo" → back.

## Open questions for the orchestrator

1. Is an actual *download* (PDF/export) wanted? Today everything is offline, and the plan says so on screen instead.
2. The Compendium content is mostly templated. Is it acceptable to show it grouped by range (honest), or should the data owner bundle the real text (licensing)? Or should the Compendium be labelled a summary?
3. Should the curated summaries be labelled "Síntesis" (my recommendation, honest) or keep "En resumen" (the CCC's own term for its official *In Brief*)?
4. Will the foundation provide ReaderSettings + the Aa sheet shared with the Bible (preferred), or should I keep them private?
5. Metadata (title, artist, year) for `heroes/lumen/catechism.jpg`, for the credit line.
