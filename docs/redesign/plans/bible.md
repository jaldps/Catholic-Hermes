# Bible area — audit and plan (Lumen)

Owner files:
`ui/screens/BibleScreen.kt` (1653 lines), `ui/screens/ScriptureStudyScreen.kt` (810),
`ui/screens/CrossReferenceViews.kt` (280), `ui/components/CrossReferenceArcs.kt` (255).

Read with `DESIGN.md` / `PROCESS.md`. Status: step 1, plan only. No code changed.

---

## a. Inventory: the "nothing lost" checklist

### A0. Entry points, deep links and screen state (`BibleScreen`)

| Item | Source / behaviour | Keep |
|---|---|---|
| `BibleScreen(repository, currentLanguage, dao?, ttsManager?, initialMainTab, initialBookId, initialChapter, initialVerseStart)` | Called by `MainApp` (`AppDestination.BIBLE`) | Signature unchanged |
| `initialMainTab` 0 reader · 1 missal · 2 versions · 3 study | Home "Leer misa" (`read_mass_full_button` → 1), Habits `onOpenMissal` → 1, Home `onNavigateToBible` → 0, tab bar → 0 | Same codes |
| Deep link to book/chapter/verse | `onOpenBible(bookId, chapter, verse)` from Doctrine, Catechism, Mary & Jesus, History, Orders, Questions, … (7 call sites in `MainApp`) | Opens the reader, scrolls to and marks the verse |
| Keyed state | book/chapter/focus/study verse keyed on the deep-link params, so a new link re-preselects | Keep; move to `rememberSaveable` |
| Reference trail | `referenceTrail` (stack of `VerseRef`); system back retraces followed cross-references, then leaves the book | Keep, and route through `AppBackHandler` |
| Landscape | `Configuration.ORIENTATION_LANDSCAPE` turns the reader into a split: reader 46 % + `CrossReferenceStudy` 54 %; tapping a verse sets `studyVerse` instead of opening the sheet | Keep |
| Root tag | `bible_screen` on the root column | Keep |
| Storage fallback | when `dao` or `ttsManager` is null, Study shows `AppEmptyState` ("Estudio Bíblico" / "Los itinerarios de estudio necesitan el almacenamiento local…") | Keep, restyled |

### A1. Tab root header (shared by the Bible, Missal, Versions and Study views today)

- Hero band 220 dp, photo `heroes/church_window_light.jpg` decoded by the private `decodeSampledBitmap`, tag `bible_hero_photo`, with a fade and a light scrim.
- Title "Sagrada Biblia" / "Holy Bible" / "Bíblia Sagrada". On the Bible view it uses `IllustratedScreenHeader(SacredSubject.SCRIPTURE)`, which draws the cartoon open-book art. The IVE identity's art carries tag `screen_header_illustration` (asserted by `AppNavigationTest`).
- Segmented control: "Biblia | Misal | Estudio" / "Bible | Missal | Study" / "Bíblia | Missal | Estudo". Study maps to internal tab 3, and Versions (2) highlights segment 0. Height is at least 48 dp.

### A2. Bible view (`BookDirectory`)

| Element | Data / source | Action | Tag / a11y |
|---|---|---|---|
| Search field, placeholder "Buscar/Search/Buscar" | local `searchQuery` | filters books by name, category, testament and "deuterocanónico" (4+ chars); verse full-text search at 3+ chars via `repository.searchBible(q, 20, lang)` on `Dispatchers.Default` (`produceState`) | `bible_search_input` |
| Verse search loading | `verseMatchesState == null` | `LinearProgressIndicator` | `bible_verses_loading` |
| Continue reading row | `ReadingPosition.load(ctx)` (SharedPreferences `reading_position`: `book_id`, `chapter`); hidden while searching | opens that book at that chapter | `bible_continue_reading`; title "Seguir leyendo / Continue reading / Continuar a ler", subtitle "{book} {chapter}" |
| Canon filter (dropdown) | 0 Todos los libros · 1 Antiguo Testamento · 2 Nuevo Testamento · 3 Deuterocanónicos | filters the list | `bible_canon_filter` (cd "Filtrar libros", stateDescription = selected), items `bible_canon_0..3`, check on the selected item |
| Versions link "Versiones ›" | — | opens Versions (tab 2) | `bible_versions_row`, cd "Versiones, traducciones y descargas" |
| Book list | `repository.catholicBooks` grouped by `BibleCategory` (7 categories: Pentateuco, Históricos, Sapienciales, Proféticos, Evangelios y Hechos, Cartas, Profecía); chapter counts from `repository.getChapterCount(id, lang)` (the real asset count), with fallback to `chaptersCount` | open book at chapter 1 | `book_item_{id}`; subtitle "N capítulos" plus " · Deuterocanónico" |
| Verse results | `Triple(bookId, chapter, BibleVerse)`, snippet capped at 140 chars, citation "Juan 3,16" (EN "John 3:16") | open book at that chapter | — |
| Empty search | "Sin resultados" + "Ningún libro ni versículo coincide con «q»." + `AppPrimaryButton` "Borrar la búsqueda" | clears the query | icon `SearchOff` |

### A3. Reader (`ChapterReader`)

| Element | Data / behaviour | Tag / a11y |
|---|---|---|
| Back | `onBack`: clears the trail and returns to the directory | `back_to_books_button`, cd "Volver a los libros / Back to the books / Voltar aos livros" |
| Book name (row title) | `book.name` | — |
| Chapter chip "Cap. 2 / 50" | opens `ChapterSelectorDialog` | `choose_chapter_header_button` |
| A− / A+ chips | `fontSizeMultiplier` 0.85–1.45, step 0.15; **not persisted** | cd "Reducir el tamaño del texto" / "Aumentar el tamaño del texto" (×3 languages) |
| Font chip | `ReaderFontChoice` FOLLOW_APP → SERIF → SANS, labels "Por defecto/Default/Padrão", "Serif/Serifa", "Sans/Sem serifa"; **not persisted** | `bible_reader_font_choice` (test: one tap makes verse text EB Garamond) |
| Progress bar | chapter ÷ chapterCount | `bible_reader_progress`, cd "Cap. N / M", stateDescription "Progreso del capítulo" |
| Title block | `AppScreenTitle(book name, "Capítulo N")` | — |
| Book summary | `book.summary` (every chapter) | — |
| Deuterocanon banner | title "Libro Deuterocanónico", body "Conservado en el canon católico desde los Concilios de Roma (382) e Hipona (393)." (every chapter) | — |
| Verses | `repository.getChapter(id, ch, lang).verses`, `AppVerseRow` with a superscript number; a number tinted when `verse.commentary != null`; scaled by `ReadingTypography` (scripture, quote and verseNumber styles × scale, with the family override) | `verse_{n}`; tap → verse sheet (portrait) or study verse (landscape) |
| Focus / selected verse | `focusVerse` (deep link, followed reference) scrolls there (`leadingItems + index`) and marks it; `studyVerse` marks it in landscape | `selectionTint` fill |
| Empty chapter | "Capítulo no disponible" + "Este capítulo no está en la edición incluida en este idioma." | icon `AutoStories` |
| Prev / Next | "← Cap. N-1" / "Cap. N+1 →", disabled at the bounds | `prev_chapter_button`, `next_chapter_button` |
| Position save | `ReadingPosition.save(ctx, book, chapter)` on every chapter shown | — (Home also reads it, `home_continue_reading`) |
| Chapter dialog | `AlertDialog` "{Book} — Seleccionar Capítulo", adaptive grid (min 52 dp) of numbers, current = ink fill, stateDescription "Capítulo actual"/"Seleccionar", "Cerrar" button | — |
| TTS | **none today** (the `ttsManager` param is only passed to Study) | — |

### A4. Verse sheet (`VerseSheet`, `ModalBottomSheet`)

- Header: citation "Juan 3,16" / "John 3:16". Verse text in the `quote` style.
- Arc strip: `CrossReferenceArcStrip(line = repository.bibleLine(lang), source, targets)`, 96 dp, with a 900 ms draw-in. Tag `verse_arc_strip`.
- "Referencias cruzadas" header with subtitle "{n} lugares · gira el teléfono para el estudio completo".
- List data: `repository.crossReferences(book, ch, v, lang)` gives `label` and `preview`, strongest first. The first 12 are shown; "Ver las {n}" shows all. Tapping a row runs `onFollow(target)`, which pushes the trail, closes the sheet and jumps. Tag `cross_reference_list`.
- Empty state, with deuterocanon-specific copy: "La fuente abierta de referencias (OpenBible.info) no incluye los libros deuterocanónicos." The generic copy is "Este versículo no tiene referencias en la fuente incluida." Icon `Hub`.
- Patristic commentary: `verse.commentaryAuthor` (fallback "Padres de la Iglesia") and `verse.commentary`.
- Attribution: "Referencias: OpenBible.info (CC BY 4.0), del Treasury of Scripture Knowledge, adaptadas a la numeración de esta Biblia." (×3 languages).
- Sheet tag `verse_sheet`. `CrossReferenceScreenshotTest` calls `VerseSheet(repo, john, 3, verse, language) {}`, so the trailing lambda must stay `onFollow`.

### A5. Landscape study (`CrossReferenceStudy`)

- `BibleArcsPanel`: every connection with `minVotes = 15`, rendered faint off the main thread into a bitmap, with the focus arcs inked over it and a 700 ms reveal. Book labels are drawn for 17 books. The focus label is the citation, or "{book} {ch} · toca un versículo".
- Before a verse is chosen, the panel shows the chapter's web (each verse's top 3 links from `crossReferenceCounts`).
- Below the panel: either the hint "Cada arco une dos pasajes…" (×3 languages), the empty state "Sin referencias", or the reference list (tap → follow).
- Empty state when there is no `bibleLine`: "Estudio no disponible". Tag `cross_reference_study`.

### A6. Missal view (`DailyMissalView`)

- Data: `repository.dailyMassReading` (`DailyMassReading`: dateDisplay, liturgicalSeason, liturgicalColorHex, feastTitle, the refs and texts of the 1st reading, psalm, 2nd reading (optional) and gospel, homilyReflection, sourceName, sourceUrl).
- Live data: `VaticanGospelFetcher().today(lang, isoToday)` returns `ReadingsResult.Success(VaticanDailyReadings(title, publishedAt, link, sections[heading, citation, text]))` or `Failure`. Vatican sections take priority, then the bundled lessons, with blank lessons dropped.
- Header "Misa del día / Today's Mass / Missa do dia" with subtitle = date. `AppBanner(season, feast, accent = liturgicalColorHex)`.
- Loading: `LinearProgressIndicator` (tag `daily_missal_loading`) and "Cargando las lecturas de hoy desde Vatican News…" (×3 languages).
- Ferial empty state: "Feria sin lecturas propias" plus long body (×3 languages).
- Lessons: label as the section title, citation as the subtitle, text in the `scripture` style with ℣/℟ sigla via `withLiturgicalSigla(text, rubricSigil())` (from `PrayersAndNovenasScreen`, accepts any `SpanStyle`).
- Offline only: "Reflexión Homilética" card (`homilyReflection`).
- Source: "Fuente" + "Vatican News · {title}" or `sourceName`. "Abrir en Vatican News" opens the **system browser** (`ACTION_VIEW`, URL `vatican.link` or `VaticanGospelFetcher.pageUrl(lang)`), deliberately not a WebView (cookie banner).
- Root tag `daily_missal_screen`. Test: "Today's Mass" must be visible on arrival from Home.

### A7. Versions view (internal tab 2)

- `BackHandler` returns to tab 0. Header "Versiones y cánones" with subtitle "El canon católico (73 libros) frente a las ediciones protestantes (66)."
- Per `BibleVersionInfo`: name, canon badge "Canon católico (73)" / "No católica (66)", "{abbr} · {LANG}", description, `canonicalDifferenceNote`.
- `OfflineBibleDownloadsView(manager, lang)` (public): "Descargas offline de Biblias" with subtitle. Per `manager.specs`: name, status ("Descargado (offline disponible)" / progress message / "No descargado" / "Completado"), determinate progress, "Descargar" button, which calls `manager.download(id) { fraction, message -> }` and then `isDownloaded`.

### A8. Study view (`ScriptureStudyScreen`)

- Data: `repository.scriptureStudyTracks`: 3 tracks (`track_sacraments`, `track_parables`, `track_apostolic`) of 6 lessons each, with title, description and `iconName`. Progress comes from `dao.getAllScriptureProgress()` (Room `scripture_study_progress`: lessonId, trackId, isCompleted, completedTimestamp, personalNotes).
- Overview: `MenuPickerRow` "Itinerario" with "1. Sacramentos / 2. Parábolas / 3. Cartas" (tag `study_track_picker`), track title and description, a banner "Fundamento magisterial — Alineado con el Catecismo y Dei Verbum del Vaticano", "Lecciones del Curso" with "{done} de {total} lecciones · {pct}% del curso", and a progress bar (tag `study_course_progress`, cd = track title, stateDescription = summary).
- Lesson rows "{n}. {title}" with subtitle `biblicalReference`, trailing "Hecha" when done. Tags `lesson_row_{id}`.
- Bottom index "Itinerarios del curso": rows with "{read} de {n}" and the selected state (tags `track_row_{id}`).
- Empty states: "Sin itinerarios…" and "Sin lecciones…".
- Lesson detail: back (`back_to_course_button`, cd "Volver al Curso"), toggle chip "Marcar Hecha"/"Completada" (`toggle_lesson_complete_button`, toggles `isCompleted` and stamps or clears the timestamp, keeping notes). Title with "Lección N · ref".
  - "Paso 1: Lectura de la Escritura (Lectio)" + passage + "Escuchar" (`lesson_listen_button`, `ttsManager.speakSingle(passage)`, start only, no stop).
  - "Magisterio del Vaticano y Catecismo" (subtitle = `catechismRefs` joined) + `vaticanContext`.
  - "Guía de Lectio Divina": 4 steps (Lectio / Meditatio / Oratio / Contemplatio, each with a question and content).
  - "Mis Notas y Frutos Espirituales" + subtitle, notes field (`lesson_notes_field`, min 4 lines), save button (`save_lesson_button`): "Guardar y Completar Lección" if not done (marks done + saves notes), or "Guardar Cambios" if done (saves notes, keeps done state and timestamp).
  - `BackHandler` closes the lesson.

### A9. Arcs (`CrossReferenceArcs.kt`)

`CrossReferenceArcStrip`, `BibleArcsPanel`, the `arc()` geometry (height ∝ √span), the `baselineBand` (OT/NT bands and per-book ticks), `drawBookLabels` (collision-avoiding), `renderAllArcs` (bitmap) and the `LocalInspectionMode` still-render path for screenshots. Inks per identity: APPLE uses orange `#FF9F0A` and `scheme.primary`.

### A10. Tests touching this area

- `AppNavigationTest`: `nav_bible`→`bible_screen`; `bible_canon_filter` click then `bible_canon_2` click, then "Matthew" displayed. IVE: `screen_header_illustration` displayed. Home `read_mass_full_button` leads to `daily_missal_screen` + "Today's Mass". Clickable "Bible" in `bible_screen` (the segment), then `book_item_gen`, then `verse_1` text family == app font. `bible_reader_font_choice` **displayed** + click → EB Garamond.
- `CrossReferenceScreenshotTest` (sheet + landscape study, positional args).
- `FullAppScreenshotTest` `bible` (root only).
- `MissalTest` (data only).

---

## b. What is wrong today

1. **Header**: there is a black band above the hero (the scaffold's top bar sits on a black status area in dark mode). A dark stained-glass photo is fighting a cartoon open-book illustration and a heavy bold title. Two art styles share one header, and the title is centred on nothing.
2. **The segmented control** is a flat grey pill on grey, and it reads as a toolbar, not a material.
3. **"Seguir leyendo" is a flat grey row** with the same weight as a book row. It has no progress, no art and no invitation. When nothing has been read yet, the first screen offers no starting point at all.
4. **The filter row is an afterthought**: plain "Todos los libros ▾" text plus a "Versiones ›" text link at the opposite edge. Versions is a hidden fourth mode: it keeps the hero and the segmented control (with "Biblia" highlighted), has no visible back, and only system back leaves it.
5. **The book list is a 73-row wall** of two-line rows ("Génesis / 50 capítulos") under bold category headers. There is no Old/New Testament structure even though the canon filter is about testaments, and the same data appears in two places.
6. **Search** has a vague placeholder ("Buscar"). Verse hits render *below every book group*, so they sit off-screen. Opening a hit lands at the top of the chapter without marking the verse. The loading bar is green.
7. **Reader chrome eats the page.** Three rows of chrome (back/title/chip, A−/A+/font chips, progress + divider), then the book name repeated as a title, the summary repeated on every chapter, and the deuterocanon banner repeated on every chapter. At the bottom sit two big buttons plus the floating tab bar. On a phone the text gets roughly half the screen.
8. **The reader cannot read aloud** although `ttsManager` is right there (Study can). Type size and font are **not persisted**, so every visit starts at 100 %/Default. "Por defecto" is an opaque label. The progress bar measures the book but is announced as "Chapter progress". Prev/next stop dead at a book's edges instead of continuing to the next book.
9. **The chapter picker is a Material `AlertDialog`** with a cramped 320 dp grid, a "Cerrar" button and no way to change book.
10. **Rotation resets everything**: `MainApp.current` and all Bible state are `remember`, not saveable, and there is no `configChanges`. Turning the phone (the gesture the verse sheet *tells* you to make) recreates the activity and lands on Home, so the landscape study is effectively unreachable on a real device.
11. **Verse sheet**: arcs in iOS orange plus the theme primary (off-palette, multicolour). Commentary uses Material `bodyMedium`. The attribution is in `textTertiary` (fails 4.5:1). There is no copy, share or listen for a verse. The arc strip has no legend, so its colours mean nothing to a newcomer.
12. **Missal**: it lives under the *Bible* hero with no art of its own. Date, season banner and lessons are all section headers of equal weight, so the label ("Primera Lectura") and the citation fight. There is no listen and no way to jump to the Gospel. Loading is a bare green bar. "Misal" in the tab disagrees with "Misa de hoy" elsewhere.
13. **Study**: the track is chosen twice (a `MenuPickerRow` at the top *and* the "Itinerarios del curso" list at the bottom: duplicate controls). There is a decorative "Fundamento magisterial" banner, ornaments between every section, and "1. Title" rows with a trailing word "Hecha" instead of a check. Lesson detail uses a text "Volver" link in the accent and a chip, "Paso 1: …" headers, and gold eyebrows. Listen cannot be stopped, and there is no "continue where I left off".
14. **Hard-coded values** throughout: 220/160/96 dp bands, `Color(0xFFFF9F0A)`, `10.sp` labels, `MaterialTheme.typography.bodyMedium`, `scheme.primary` progress, and a private `decodeSampledBitmap` copy.

---

## c. The new design

Grammar for the whole area: edge-to-edge canvas gradient (`AppEdgeToEdge()` insets), 20 dp gutter, glass surfaces, Inter for the chrome, and the reader's font for every passage of scripture. One accent: the liturgical season, as a dot or bar only. Every passage (reader, Missal, Study, verse sheet) uses **one shared reading style**: `reading` × the persisted Aa settings. Change the size once and Scripture follows everywhere in this area.

### C1. Bible tab root (common top)

1. **LargeTitleHeader** "Sagrada Biblia" / "Holy Bible" / "Bíblia Sagrada" (`largeTitle` 34/40), 12 dp under the status bar. The trailing glass circle (40 dp, 44 dp hit) uses `Outlined.Translate`, carries tag `bible_versions_row` and cd "Versiones, traducciones y descargas". It replaces the "Versiones ›" link.
2. 16 dp → **SegmentedControl** "Biblia | Misa | Estudio", "Bible | Mass | Study", "Bíblia | Missa | Estudo" (glass track, glassStrong thumb, 36 dp visual / 44 dp target). The internal codes 0/1/3 are unchanged. The ES label changes from "Misal" to "Misa" to match Home.
3. 24 dp → the segment's content, cross-faded over 200 ms.
4. **TopBar on scroll**: when the large title passes under the status bar, a glass bar with an inline 17 sp "Sagrada Biblia" fades in.
5. No hero image on the root (DESIGN: tab roots without hero use a large title). Art lives inside the content cards instead (C2.1, C4.1).

### C2. Bible segment, top to bottom

1. **Continue card** (`ImageCard`, radius 22, full width, 184 dp). Art `heroes/lumen/bible.jpg` (fallback `heroes/church_window_light.jpg` via `rememberFirstAsset`). Tag `bible_hero_photo` moves to this image; tag `bible_continue_reading` goes on the card.
   - Bottom scrim. Bottom-start in white: caption "Seguir leyendo"; `title2` "{Book} {ch}" (2 lines max; a parenthetical alias is dropped when needed, e.g. "Sirácida 51"); footnote "Capítulo 2 de 21"; thin **ProgressBar** (chapter ÷ chapters, white on white@30 %).
   - Trailing round glass **play** button: opens the chapter *and starts listening*. Tapping the card opens the chapter at the last verse read (C3.6).
   - **First run** (no saved position): the same card reads "Empieza aquí / Start here / Comece aqui", "Evangelio según San Juan", "Una buena primera lectura" and opens John 1. The screen is never empty and has one clear task.
   - Hidden while a search is active.
2. 20 dp → **SearchField** (glass capsule, 48 dp, tag `bible_search_input`). Placeholder "Buscar libros y versículos / Search books and verses / Buscar livros e versículos". A clear (×) glass glyph appears with text.
3. 28 dp → **section row**: `headline` "Libros" at the left and, at the right, a glass **menu capsule** "Todos los libros ⌄" (tag `bible_canon_filter`, same semantics). It opens a glass menu (radius 20) with the 4 options (`bible_canon_0..3`, check on the selected one). A menu (not a chips row) keeps the long ES/PT labels whole and preserves the test flow.
4. **Book directory by testament.** `title2` "Antiguo Testamento" with footnote "46 libros", then one **GroupedList** per category with the iOS-style group label (footnote, inkSecondary, sentence case: "Pentateuco") above it. Then the same for "Nuevo Testamento · 27 libros".
   - Rows are single line, 52 dp: book name (`body`) and a trailing value "50 cap." / "50 ch." / "50 cap." (subhead, inkSecondary), with a chevron. Deuterocanonical books add one footnote line, "Deuterocanónico". Inset hairlines. Tag `book_item_{id}`; the row cd keeps the full "50 capítulos".
   - Filters hide empty testaments and categories.
5. **Search state** (query non-empty):
   - Books: matching books as above (no testament titles if only a few).
   - "Versículos": shown first when the query is 3+ characters and matches no book name, otherwise after the books. While searching, a thin ProgressBar sits under the field (tag `bible_verses_loading`) with the footnote "Buscando en la Biblia…".
   - Verse result rows: citation `body` ("Juan 3,16"), snippet `subhead` inkSecondary up to 3 lines with the matched words in ink medium. Tap → opens the chapter **with the verse focused**.
   - Empty: glass empty state (outline `SearchOff` in a 44 dp glass circle, `headline` "Sin resultados", footnote body) + SecondaryCapsule "Borrar la búsqueda".
6. Bottom padding = dock inset + 32 dp.

### C3. Reader (portrait): the showpiece

Target: Apple Books. Text owns the screen, chrome is glass and goes away while you read, and everything is one tap away.

1. **Immersive**: while a chapter is open the floating tab bar slides away (needs D1). The reader draws edge to edge on the flat `canvas` colour (not the gradient: a page should be even).
2. **Top glass bar** (floating, status inset + 56 dp):
   - Left: the glass back circle. This is the shared one via `AppBackHandler` (cd "Volver a los libros", tag `back_to_books_button`; needs D2). Back retraces the reference trail first.
   - Centre: a glass **capsule** "Génesis 3 ⌄" (`headline` 17/22, 1 line; parenthetical aliases dropped, then the abbreviation as a last resort). Tag `choose_chapter_header_button`, cd "Elegir capítulo. Génesis, capítulo 3 de 50". It opens the Chapter sheet (C3.7).
   - Under the bar: a 2 dp **ProgressBar** (ink on separator) showing *progress through the book*, `(chapter − 1 + scrollFraction) / chapters`. It moves smoothly as you scroll. Tag `bible_reader_progress`; cd "Cap. 3 / 50"; stateDescription "Progreso en el libro".
   - At rest the bar is transparent. Once text scrolls under it, it becomes glass.
3. **Page**: reading column max 640 dp, text inset 24 dp (reading margin token, D7), centred on wide screens.
   - **Opening**: eyebrow book name (inkSecondary), then `title1` "Capítulo 3".
   - **Chapter 1 only**: the book `summary` (callout, inkSecondary). For deuterocanonical books, a quiet footnote line with a small glass label "Deuterocanónico" followed by the councils note (the text is preserved). It is no longer repeated on every chapter; it also appears in the Chapter sheet header.
   - 28 dp, then the verses.
   - **Verses**: one row per verse (tags `verse_{n}` kept, so scroll-to, marking and tests keep working). The verse number is an inline superscript (0.62×, semibold, **inkSecondary** for contrast), followed by the text in the reading style: serif 19/30 or sans 18/28, × size and × spacing. The family sits on the `Text` style (the test reads `layoutInput.style.fontFamily`). 8 dp between verses.
   - A verse **with patristic commentary** gets a 5 dp season-accent **dot** in the left margin on its first line. It replaces the tinted number, follows the DESIGN accent rule, and adds the a11y suffix "con comentario".
   - Tap → Verse sheet. `onClickLabel` "Referencias y comentario".
   - **Marks**: the focused verse (deep link, search, followed reference) gets a 12 dp-radius `highlight` fill that bleeds 8 dp past the text. The verse being read aloud gets the same fill plus a 3 dp accent bar, cross-fading verse to verse in 200 ms and auto-scrolled to the upper third.
   - **End of chapter**: the footnote "Génesis 3 · 24 versículos" centred. The chrome reappears, and the *next* button pulses once (scale 1 → 1.06 → 1). There is no extra CTA, so there is no duplicate control.
   - **Empty chapter**: a glass empty-state card (copy preserved) with a "Capítulo siguiente" secondary capsule.
4. **Bottom glass TransportBar** (capsule 64 dp, centred, nav inset + 16 dp). Hit targets are 44 dp minimum.
   - `‹` previous: `prev_chapter_button`, cd "Capítulo anterior: Génesis 2". At chapter 1 it goes to the **previous book's last chapter**, and it is disabled only at Genesis 1.
   - **Listen**: 52 dp, glassStrong, play/pause. Tag `bible_reader_listen`, cd "Escuchar el capítulo" / "Pausar la lectura".
   - `Aa`: tag `bible_reader_text_button`, cd "Texto: tamaño y tipo de letra".
   - `›` next: `next_chapter_button`. It continues into the next book and is disabled only at Revelation 22.
   - A chapter change cross-fades (200 ms) to the top of the new chapter.
5. **Chrome behaviour**: scrolling down by more than 24 dp hides both bars (slide + fade, 250 ms). Scrolling up, reaching the end, or a chapter change shows them. While listening, the transport stays visible. With TalkBack on, the chrome never hides.
6. **Listen**: the chapter is read verse by verse in the app language via `LiturgicalTtsManager`. Highlight and auto-scroll follow the verse being read.
   - TTS stops on leaving the reader or changing chapter (Apple-style continuity across chapters is a stretch goal).
   - Uses D6 if provided. Otherwise it chains `speakSingle` privately by watching `isPlaying`. That also avoids the TTS ~4000-char limit on long chapters such as Psalm 119.
   - The last verse read is saved privately (`bible_reader` prefs) next to `ReadingPosition`, so Continue opens at that verse (scroll only, no mark).
7. **Chapter sheet** (glass sheet, radius 28, ~85 % height, tag `bible_chapter_sheet`):
   - Header: `title2` book name, footnote "50 capítulos", glass close circle.
   - A SegmentedControl "Capítulos | Libros".
   - **Capítulos**: a 6-column grid of 48 dp glass circles (numbers in subhead). The current chapter uses an action fill (charcoal; ivory in dark). Each cell has cd "Capítulo N" and a selected state; tag `chapter_cell_{n}`.
   - **Libros**: the compact testament/category list. Tapping a book swaps the sheet to its chapters, so Génesis 3 → Mateo 5 never leaves the reader.
   - Chapter 1 summary and deutero note: under the header, collapsed to 2 lines with "Más".
8. **Aa sheet** (glass sheet, ~50 % height so the live page shows behind it). Tag `bible_reader_text_sheet`.
   - **Size**: a two-button glass segmented pair, a small "A" and a large "A" (52 dp). The cds are preserved: "Reducir el tamaño del texto" / "Aumentar el tamaño del texto". Tags `bible_reader_font_smaller` / `_larger`. A 5-dot step indicator sits below. Range is 0.85–1.45, step 0.15, as now.
   - **Font**: three glass tiles (radius 20), each showing "Aa" in its own face over the caption label (`ReaderFontChoice.label`). Selected = action ring. The container has tag `bible_reader_font_choice` (see D12 for the test); options are tagged `bible_reader_font_{FOLLOW_APP|SERIF|SANS}`.
   - **Spacing** (new, small): a SegmentedControl "Compacto | Normal | Amplio" for line height ×0.92 / ×1 / ×1.12.
   - Optional toggle "Números de versículo" (on by default).
   - All of it is **persisted** (private SharedPreferences `bible_reader`, unless D5 gives an app-wide store). The Missal and Study passages read it too.

### C4. Missal segment ("Misa")

1. **Today card**: a mist-treated art frame (radius 24, 236 dp) with `heroes/lumen/mass.jpg` (fallback `heroes/cathedral_dome.jpg`). The art fades into glass in its lower half, as in the reference "Misa de hoy" panel. Ink text:
   - eyebrow `reading.dateDisplay`;
   - `title1` "Misa de hoy / Today's Mass / Missa de hoje" (the test string is preserved and visible above the fold);
   - a **season pill** (glass capsule: 8 dp dot in the season accent from `liturgicalColorHex` via D8, plus the season name);
   - the feast title (`subhead` inkSecondary, wraps).
2. 16 dp → **Listen capsule** (SecondaryCapsule with a play glyph): "Escuchar las lecturas / Listen to the readings / Ouvir as leituras", which becomes "Pausar" while playing. It reads every lesson in order. Tag `missal_listen_button`.
3. 20 dp → **jump chips** (FilterChips, horizontally scrollable): "1.ª lectura · Salmo · 2.ª lectura · Evangelio". The short labels are derived from the lesson's position and type; unknown Vatican headings fall back to their first two words. Tapping one scrolls to that lesson; scroll-spy marks the current one (action fill). The row is sticky under the top bar if D11 lands, inline otherwise. Tags `missal_jump_{i}`.
4. **Lessons**, 40 dp apart, with no dividers or boxes:
   - eyebrow label ("Primera lectura") in inkSecondary;
   - `title2` citation ("Lucas 15, 1–10");
   - 12 dp, then the text in the shared reading style;
   - ℣/℟ through `withLiturgicalSigla` with *our* rubric style (season accent, semibold);
   - the lesson being read aloud gets a 3 dp accent bar in the margin.
5. **Loading**: the today card renders at once (bundled data). The lessons area shows 3 calm skeleton blocks (glass lines, alpha pulse 0.5 ↔ 0.8 over 1.2 s, frozen in still mode) and the footnote "Cargando las lecturas de hoy desde Vatican News…". Tag `daily_missal_loading`.
6. **Offline** (`Failure`): the bundled lessons show, with a quiet footnote under the card, "Sin conexión · lecturas del leccionario incluido", and the homily reflection: eyebrow "Reflexión homilética" plus a glass card (radius 24, padding 20) in the reading style.
7. **Ferial empty**: a glass empty-state card (copy preserved) whose CTA is "Abrir en Vatican News".
8. **Source footer**: footnote "Fuente: Vatican News · {title}" or `sourceName`, plus a SecondaryCapsule "Abrir en Vatican News" with an `OpenInNew` glyph (system browser, unchanged). Tag `daily_missal_screen` stays on the list.

### C5. Study segment ("Estudio")

1. **Track carousel** (`LazyRow`, tag `study_track_picker`): cards 264 × 176 dp, radius 22, with 12 dp gaps; the next card peeks to signal scroll.
   - Each card is glass, or an `ImageCard` when `heroes/lumen/study_{sacraments|parables|letters}.jpg` exists.
   - Content: a 40 dp **ProgressRing** with the outline icon from `iconName` (Church / AutoStories / MenuBook) inside, the track title (`headline`, up to 3 lines), and the footnote "2 de 6 lecciones".
   - Selected card = glassStrong + 1.5 dp ink ring, with `selected` semantics. Tag `track_row_{id}`.
   - This replaces both the menu picker and the duplicate bottom index; per-track progress is preserved on the cards.
2. 24 dp → `title2` track title, `callout` inkSecondary description, and a footnote line with an outline `Verified` glyph: "Alineado con el Catecismo y Dei Verbum del Vaticano" (the banner text, kept, without the box).
3. **Progress**: a ProgressBar plus the footnote "2 de 6 lecciones · 33 % del curso". Tag `study_course_progress` with the same semantics.
4. **PrimaryCapsule** (52 dp, full width, tag `study_continue_button`):
   - "Continuar · Lección 3" (first lesson not done);
   - "Empezar el itinerario" (none done);
   - "Repasar el itinerario" (all done).
5. **Lessons**: a GroupedList in StepList grammar, with a leading 32 dp numbered glass circle that turns into a success-filled check when the lesson is done (stateDescription "Hecha"). Then the title (`body`, wraps, no truncation), the reference (footnote) and a chevron. Tag `lesson_row_{id}`.
6. **Lesson detail** (in-screen detail; root title and segments hidden; `AppBackHandler` → shared back, tag `back_to_course_button`, cd "Volver al Curso"):
   - top right: a glass capsule toggle with a check glyph, "Marcar como hecha" ↔ "Hecha" with a success dot (`toggle_lesson_complete_button`);
   - eyebrow "Lección 3", `title1` title, footnote reference;
   - **Lectio**: eyebrow "Paso 1 · Lectio" and `headline` "Lectura de la Escritura", the passage in the reading style, and a glass capsule "Escuchar" ↔ "Detener" that reflects `isPlaying` + `currentlySpeaking` (`lesson_listen_button`);
   - **Magisterio**: `headline` "Magisterio del Vaticano y Catecismo", the refs as small glass capsules (caption, FlowRow), then the context in `callout`;
   - **Lectio divina**: a glass card StepList of 4 movements: numbered circle, Latin name (`headline`), question (`callout` ink), guidance (`subhead` inkSecondary), with inset hairlines;
   - **Notes**: `headline` "Mis notas y frutos espirituales" plus a footnote prompt, a glass multi-line field (radius 20, min 4 lines, `body`, `imePadding`, `lesson_notes_field`), and a PrimaryCapsule "Guardar y completar lección" / "Guardar cambios" (`save_lesson_button`) that shows a brief inline "Guardado ✓" (footnote, 1.5 s);
   - all ornaments are removed.

### C6. Versions and downloads (internal tab 2, in-screen detail)

- `AppBackHandler` handles back to the Bible segment. The root title and segments are hidden. `title1` "Versiones y descargas" / "Versions & downloads" / "Versões e downloads", followed by a subhead intro (the 73/66 sentence, kept).
- Section "Traducciones": glass ContentCards. Each has the name (`headline`), a meta row with a capsule "Canon católico · 73" (ink on glassStrong) or "No católica · 66" (inkSecondary) and "JER · ES" (footnote), the description (`callout` inkSecondary) and the canon note (footnote).
- Section "Sin conexión" (`headline` + footnote description): a GroupedList with one row per spec.
  - Name and status footnote; the copy is kept.
  - Trailing glass circle: download (`Outlined.Download`, cd "Descargar {name}"). While downloading it becomes a 28 dp determinate ProgressRing; when done, a success check. Tag `bible_download_{id}`.
  - On failure the row shows the manager's message and re-enables.
- `OfflineBibleDownloadsView` stays public, with the same signature.

### C7. Verse sheet (`VerseSheet`)

- Glass sheet, radius 28. `title2` citation (full width, wraps), then the verse in the reading style.
- An **action row** of three glass circles, centred, as in the reference prayer detail: copy (`ContentCopy`), share (system share sheet, "«text» — Juan 3,16") and listen (when TTS is available).
- `VerseSheet` gains defaulted params *before* `onFollow`, so the trailing lambda still binds to `onFollow` and the screenshot test compiles.
- **Arc card**: a glass card (radius 24, padding 16) holding the strip, with a legend below: "● Antiguo Testamento ● Nuevo Testamento" (caption).
- `headline` "Referencias cruzadas" with the footnote "23 lugares · Gira el teléfono para el estudio completo". A GroupedList (`cross_reference_list`) of rows: citation `body`, preview `subhead` inkSecondary (2 lines), chevron. The first 12 rows show, then a SecondaryCapsule "Ver las 23".
- Commentary: eyebrow author (inkSecondary, not gold) and a glass card with the text in `callout`.
- Attribution in footnote **inkSecondary** (contrast fix).
- Empty state: a glass circle with an outline `Hub` glyph, a `headline`, and the footnote copy (kept).

### C8. Landscape study (`CrossReferenceStudy`, `BibleArcsPanel`)

- The reader column (46 %) keeps its glass top bar and transport, both confined to its column. The study is a glass card (radius 24) filling the right 54 % with a 20 dp inset.
- Top 55 %: the arcs. The focus label is `headline` ink at 16 dp. Book labels are `caption` inkSecondary (no 10 sp). The faint-web alpha is tuned per mode: light 0.022 on warm canvas, dark 0.035 on night canvas.
- Bottom: the hint in `callout` inkSecondary, or a GroupedList of references (`body` + `subhead` preview). Tag `cross_reference_study`.

### C9. Arcs inks (`CrossReferenceArcs.kt`)

Two inks only, which is data meaning, not decoration: **Old Testament = gold** (`palette.gold`, warm, lighter in dark) and **New Testament = ink** at 80 %. The baseline is separator-strength, with ticks at separator; the focus dot is ink. The `DesignLanguage` switch is dropped (or both map to these). The strip reveal goes from 900 → 600 ms ease-out, the panel reveal 700 → 500 ms. Still mode stays instant. The geometry, bitmap renderer and label collision logic are unchanged.

### C10. Dark mode ("night chapel")

- The reader page is flat `#121110`-ish canvas (canvas token), with ink `#F3F0EB` text. The highlight is white at 8 %, never a glow.
- Art cards fade into the dark canvas. The Continue card scrim deepens to 55 %.
- Glass is subtle (7 %). The action fill inverts to ivory with dark numerals (chapter grid, selected chips).
- Arcs use a lighter gold and ivory ink. The faint web uses a slightly higher alpha so it stays visible.
- Skeletons are white at 6 % and 10 %.

### C11. ES / EN / PT length risks and how they are handled

- **Reader capsule**: the longest case is "Sirácida (Eclesiástico) 51" (28 chars ≈ 260 dp against 267 dp available). The rule drops "(…)", then falls back to the abbreviation, so it is never ellipsised mid-word.
- **Continue card title**: `title2` with 2 lines allowed.
- **Canon menu**: "Antiguo Testamento" and "Deuterocanônicos" sit in a menu, not chips, so there are no width constraints.
- **Search placeholder**: the longest, "Buscar livros e versículos" (26 chars), is about 225 dp, so it fits.
- **Missal jump chips**: Vatican headings can be long, so the labels are derived short names.
- **Feast titles**: they wrap (2 lines).
- **Study cards**: titles run up to 42 chars ("Ensinamentos Apostólicos e Cartas Paulinas"), giving 3 lines at 17 sp in a 224 dp text width. The card height is fixed at 176 dp, which was tested against the longest title.
- **Lesson toggle**: "Marcar como hecha" / "Mark as done" / "Marcar como feita" in a capsule beside the back circle is fine. "Guardar e concluir a lição" uses a full-width capsule.
- **Verse sheet citation**: "1 Tessalonicenses 5,23" wraps under `title2`, and the actions live on their own row.
- **Aa font tile**: "Sem serifa" is a caption in a ~110 dp tile.
- **Versions title**: "Versões e downloads" in `title1` fits at 371 dp.

---

## d. Component needs beyond DESIGN.md

For the foundation owner unless marked "private".

1. **Immersive / dock control.** A way for a screen to hide the floating tab bar (animated) while the reader is open, e.g. `AppImmersive(enabled)` or a `ScreenFrame.dockHidden` flag, ideally also exposing a "chrome visible" toggle so the shared back circle can auto-hide with the reader's bars. *Fallback:* the transport capsule floats 12 dp above the dock.
2. **Shared back button parameters.** `AppBackHandler(tag, contentDescription)`, so the shared glass back keeps `back_to_books_button`, `back_to_course_button` and the "Volver a los libros" / "Volver al Curso" labels. Also its geometry (40 dp at gutter, top = status + 8 dp), so the reader's top bar and the lesson toggle align with it.
3. **AppSheet.** A glass `ModalBottomSheet` wrapper (radius 28, container, scrim token, drag handle, nav-bar insets, imePadding). The area uses four sheets: verse, chapter, Aa and the canon menu's equivalent.
4. **AppMenu.** A glass pull-down menu (radius 20, glassStrong, check on the selected item), for the canon filter. *Private fallback:* `DropdownMenu` restyled with tokens.
5. **Reading style + app-wide reader settings.** A `reading` type token (serif 19/30, sans 18/28) and ideally a shared persisted reader-settings store (size, family, spacing) so Prayers and the Catechism match. *Private fallback:* SharedPreferences `bible_reader` inside `BibleScreen.kt`.
6. **TTS sequence API** in `LiturgicalTtsManager`: `speakSequence(segments: List<String>)`, `currentSegment: StateFlow<Int?>`, and pause/resume (`QUEUE_ADD` with utterance ids). *Private fallback:* chain `speakSingle` on `isPlaying` transitions.
7. **Tokens**:
   - `palette.highlight` (ink 6 % / white 8 %), the verse mark;
   - `palette.gold` kept and tuned for both modes (OT arcs);
   - `spacing.readingInset` = 24 dp;
   - `spacing.readingMaxWidth` = 640 dp.
8. **`seasonAccent(colorHex | LiturgicalDay)`**, mapping to DESIGN's 5 liturgical accents, for the Missal pill dot, the ℟ rubric, the commentary dot and the listening bar.
9. **Skeleton block** (`AppSkeleton(lines)`, frozen in inspection mode) for the Missal and search loading.
10. **ImageCard with eyebrow, progress and trailing play**, plus an in-frame **mist card** variant of HeroHeader (art fading into glass inside a radius-24 frame) for the Missal today card.
11. **StickyGlassStrip** (optional) for the Missal jump chips; inline if not available.
12. **Test updates** (orchestrator-owned):
    - `AppNavigationTest` needs to tap `bible_reader_text_button` before `bible_reader_font_SERIF`, since the font choice now lives in the Aa sheet. Its IVE `screen_header_illustration` assertion on Bible goes away, because the cartoon header is removed.
    - Extra screenshot states for the reader, missal, study, lesson, versions, chapter sheet and Aa sheet, in light, dark and ES. Either new `FullAppScreenshotTest` destinations or a `BibleAreaScreenshotTest.kt` that I would own.
13. **MainApp state** (`MainApp.kt` is foundation-owned): make `current`, `origin` and the `bibleInitial*` values `rememberSaveable` (or add orientation `configChanges`), so rotating to the landscape study does not reset to Home. Inside my files I will move the Bible state to `rememberSaveable`.
14. **Home owner**: "Seguir leyendo" on Home should call `onOpenBible(book, chapter, null)` to land in the reader rather than the directory.
15. **Imagery agent**: `heroes/lumen/bible.jpg` (e.g. St Jerome in his study / an open codex, warm light) and `mass.jpg` (sanctuary light), with `manifest.json` focus and tone. Optionally `study_sacraments.jpg`, `study_parables.jpg` (Millet's *The Sower*) and `study_letters.jpg` (Valentin de Boulogne's *Saint Paul Writing His Epistles*).

Built privately in my files:
- `ReaderTopBar`, `ReaderTransport`, `ReaderVerse`, `ChapterSheet`, `TextSettingsSheet` and the reader prefs;
- the TTS chaining fallback;
- `MissalLesson`, `TodayMassCard`, the jump chips and scroll-spy;
- `TrackCard`, `LessonStepRow`, `LectioStepCard`, `NotesField` (glass);
- `DownloadRow`, `VersionCard`;
- the arc inks.

The private `decodeSampledBitmap` is deleted in favour of `rememberAssetBitmap`/`rememberFirstAsset`.
