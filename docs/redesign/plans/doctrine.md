# Doctrine & Study — Lumen plan

Owner: Doctrine & Study area. Files owned:
`app/src/main/java/com/example/ui/screens/DoctrineAndHistoryScreen.kt` (3218 lines) and
`app/src/main/java/com/example/ui/screens/DevotionsExplorerScreen.kt` (725 lines).
Read with `DESIGN.md` and `PROCESS.md`. Step 1 (audit + plan) only; no code touched yet.

---

## 0. How the area is entered (MainApp.kt)

Every destination below calls `DoctrineAndHistoryScreen(repository, currentLanguage,
initialTopTab, showTabs = false, onOpenBible, onOpenCatechism, onOpenPrayer[, initialQuestionIndex])`.
`showTabs` is always `false`, so the pane is fixed by `initialTopTab`.

| Destination (tag) | Scaffold title (ES) | initialTopTab → pane | Reached from | New hero asset |
|---|---|---|---|---|
| `DOCTRINE` (`nav_doctrine`) | Doctrina e Historia | 0 → MoralDoctrinePane | Home tile **"Directorio Iglesias"** (mislabelled, see b.27) | `heroes/lumen/doctrine.jpg` |
| `STUDY_DOCTRINE` (`nav_study_doctrine`) | Doctrina | 0 → MoralDoctrinePane | More → Estudio | `doctrine.jpg` |
| `STUDY_MARY_JESUS` (`nav_study_mary_jesus`) | María y Jesús | 1 → DevotionsExplorerScreen | More → Estudio | `mary_jesus.jpg` |
| `STUDY_HISTORY` (`nav_study_history`) | Historia | 2 → ChurchHistoryPane | More → Estudio | `history.jpg` |
| `QUESTIONS` (`nav_questions`) | 101 preguntas sobre la fe católica | 3 → CatholicQuestionsPane (+ `initialQuestionIndex` deep link) | More → Estudio; Home "Pregunta del día → Leer respuesta" | `questions.jpg` |
| `STUDY_ORDERS` (`nav_study_orders`) | Órdenes y oficios | 4 → OrdersOfficesPane | More → Estudio | `orders.jpg` |
| `CHURCHES_NEWS` (`nav_churches_news`) | Iglesias y Noticias | 5 → ParishesAndNewsPane(newsOnly = true) | More → Estudio; Parishes → "Directorio de iglesias" (`parish_open_directory`) | `news.jpg` |

Outgoing deep links: `onOpenBible(bookId, chapter, verseStart)` → BIBLE tab 0 at that verse;
`onOpenCatechism(paragraph)` → CATECHISM at §; `onOpenPrayer(prayerId)` → PRAYERS with that prayer
open; external intents: browser (Vatican News articles, vaticannews.va), maps (`geo:`, Google Maps
search), dialer (`tel:`), share sheet (`ACTION_SEND`).

---

## a. Inventory — the "nothing lost" checklist

### a.1 Shell (`DoctrineAndHistoryScreen`)
- Root tag `doctrine_and_history_screen`.
- Hero band 220dp, `doctrine_hero_photo` tag, per-pane photo (`heroes/cathedral_dome|mary_statue|church_window_light|sunrise_mountains|votive_candles.jpg`), decorative (`contentDescription = null`), title "Doctrina e Historia" (or "Noticias" when newsOnly).
- Dead: `AppSegmentedTabs` pane strip (only when `showTabs`), `topTabs` labels are used only as back labels.
- Internal navigation: `openEntry: String?` keyed `"kind:index"` (`remember`, not saveable); `key(entry)` recreates the page; `BackHandler { openEntry = null }`.
  Kinds: `cmd`, `prc`, `vice`, `grav`, `epoch`, `schism`, `didache`, `sources`, `qa`, `order`, `office`.
- Deep link: `LaunchedEffect(initialQuestionIndex)` → pane 3 + `qa:<index>` (out-of-range ignored).
- Entry page (`DoctrineEntryPage`, tag `doctrine_entry_page`) starts with `InlineBackLink` (`doctrine_entry_back`, label = pane name), scrolls to top on key change, ends with `AppOrnament`.

### a.2 Doctrine pane (0) — data `DoctrineAndSpiritualData`, `DidacheData`
| Section | Data | Row content | Action | Tags |
|---|---|---|---|---|
| Los Diez Mandamientos | `theTenCommandments` (10) | title without ordinal; subtitle "I · Éxodo 20:2–3" (roman + citation parsed from `biblicalWording`) | `cmd:i` | `commandment_row_<n>` |
| Los preceptos de la Iglesia | `churchPrecepts` (5) | title; subtitle roman | `prc:i` | — |
| Vicios capitales y virtudes | `capitalSinsVsVirtues` (7) | vice; "Se cura con: <virtud>" | `vice:i` | — |
| El pecado y la confesión | `sinTheology` | 3 fixed rows (venial/mortal, three conditions, preparing confession) | `grav:0..2` | — |
| Didaché | `didacheIntro` + `didacheChapters` (16) | intro paragraphs in a card, then chapter rows (heading; roman) | `didache:i` | `didache_chapter_<n>` |

Entry pages:
- **cmd**: title, eyebrow "<Ordinal> mandamiento"; scripture wording (citation removed) in scripture style + citation eyebrow; `moralExplanation`; notes "Lo que manda" (`virtuesCommanded`), "Lo que prohíbe" (`sinsForbidden`).
- **prc**: title, "Precepto <roman>"; `explanation`; "En la práctica" (`applicationInLife`).
- **vice**: `capitalSin`, "Vicio capital"; `sinMeaning`; "La virtud contraria" (`opposingVirtue`), "Remedio" (`virtueRemedy`), "En la práctica" (`practicalAdvice`).
- **grav 0**: venial / mortal explanations. **grav 1**: lead sentence + 3 numbered `mortalThreeConditions`. **grav 2**: `confessionExamenGuide` (heading line + "1. … 5." lines).
- **didache**: title heading, "Capítulo <roman>", progress "Capítulo i de 16" (`didache_progress`, stateDescription); ch.1 shows the intro card again; reader card (`didache_reader`) with paragraphs in scripture style on a paper box; **Rezar ahora** (`pray_now`, prayer id by chapter: 7 sign_of_the_cross, 8 our_father, 9–10 adoro_te_devote, 11 come_holy_spirit, 12–13 act_of_charity, 14 te_deum, 15–16 creed, else act_of_contrition); Listen/Stop TTS (`didache_listen_button`, speaks heading + intro on ch.1 + body); Share heading + body (`didache_share`); Anterior/Siguiente (`didache_prev_button`, `didache_next_button`).

### a.3 Mary & Jesus pane (1) — `DevotionsExplorerScreen`, data `DevotionsAndHistoryData`
- Root tag `devotions_explorer_screen`. State (`remember`): category (0 Marian / 1 Christ), search query, region (`ALL|Americas|Europe|Universal`), one expanded id.
- Search (`devotions_search_input`) over title, country, location, patronage, sanctuary, miracles (Marian) / title, country, sanctuary, origin, miracles (Christ).
- Pickers in one list group: "Devoción" (`devotions_category_picker`, icon Favorite/LightMode), "Región" (`devotions_region_picker`).
- Section header "Advocaciones Marianas" / "Devociones a Cristo" + "N resultados"; empty state "Sin resultados".
- Marian (30): card (`devotion_card_<id>`) header = title, `location`, trailing `year`, chevron, button role + stateDescription Abierta/Cerrada; expanded: Fiesta (`feastDay`) + País (`country`), `historyAndApparition` (scripture style), Patronazgo, Santuario, Promesas (quote style), Milagros destacados, folded prayer "Oración de Veneración e Intercesión" (`venerationPrayer`, quote style).
- Christ (20): header title + `country`; expanded: Fiesta, `originHistory`, Sentido teológico, Santuario principal, Promesas, Milagros, folded "Oración Oficial" (`officialPrayer`).
- Prayer disclosure has a Listen button only if `onSpeakText != null` — never passed, so never shown.
- Unused: private `FilterChips`.

### a.4 History pane (2)
- "Línea de tiempo": 10 `churchHistoryEpochs` rows (title, `centuryRange`) → `epoch:i`.
- "Los grandes cismas": 2 `churchSchisms` rows (title, `historicalYear`) → `schism:i`.
- epoch page: title, range; `narrative`, "Papas claves" (`keyPopes`), "Concilios" (`ecumenicalCouncils`). **`historicalSaints` is never shown.**
- schism page: title, year; `context`, "Diferencias doctrinales claves" (`theologicalDifferences`, "1. … 2. …"), "Perspectiva y diálogo" (`dialogueAndReconciliation`). **`catholicPerspective` is never shown.**

### a.5 101 Questions pane (3) — data `CatholicQuestionsData` (101, 11 sections), `triviaQuestions` (104)
- "Fuentes y método" row (`sources_entry`) → `sources:0`.
- Guided tracks (persisted, see a.9): tracks 0 "Fundamentos" Q1–30, 1 "Iglesia y sacramentos" Q41–60, 2 "Vida y moral" Q61–100. Card per track (`qa_track_<i>`): title, "Preguntas a–b", "x de n preguntas", ring with % (`qa_track_ring_<i>`, stateDescription). Tap → first question of the track.
- "Continuar" chip (`qa_track_continue`) → highest reached question of any track.
- Mini-quiz per completed track (`qa_track_quiz_<i>`): 3 trivia questions picked by track index; options (`qa_track_quiz_<i>_<q>_opt_<o>`), one answer each, feedback "Correcto/Incorrecto" (`qa_track_quiz_<i>_<q>_feedback`) + `doctrinalExplanation`. Answers not persisted.
- "101 preguntas sobre la fe católica" + description; questions grouped by **localized** section name; rows (`catholic_question_<n>`) → `qa:<index>`.
- **sources page** (`sources_page`): title + subtitle; notes: Catecismo (vatican.va, refs open paragraphs), Didaché (clerus.org), Vatican documents list (Mater Populi fidelis 2025, Fiducia supplicans 2023, Humanae vitae, Dignitas infinita / Dignitas personae), foundation levels A–D (label + explanation), "Uso honesto" disclaimer.
- **qa page**: title = question, subtitle = section; progress "Pregunta i de 101" (`catholic_qa_progress`); reader card:
  - Respuesta (`answer`, 2–14 paragraphs; 97/101 open "La respuesta directa…").
  - Fundamento (`foundation`) in tonal panel + "Referencias — toca para profundizar": one row per parsed ref (`parseFoundation`), Bible rows (book name + ch + verses, `MenuBook`), CCC rows ("Catecismo a-b", `AutoStories`), tag `foundation_ref_<refKey>` (`bible_<book>_<ch>_<vs|all>_<ve>` / `ccc_<a>_<b>`); expanding one inline shows verses (`repository.getChapter`) or CCC paragraphs (`catechismParagraphText`) (`foundation_ref_text_<refKey>`) and "Abrir en la Biblia/Catecismo" (`foundation_ref_open_<refKey>` → `onOpenBible(book, ch, vs)` / `onOpenCatechism(start)`). One expanded at a time, reset per question.
  - Aclaración (`clarification`) panel holding the foundation level badge (`foundation_level_badge`: "Fundamento X · label" + explanation; A Bible-only, B any CCC, C clarification mentions Tradición/Tradição, D else).
  - **Rezar ahora** (`pray_now`, by question number: 1–10 act_of_hope, 11–20 come_holy_spirit, 21–30 sign_of_the_cross, 31–40 memorare, 41–50 creed, 51–60 act_of_contrition, 61–70 eternal_rest, 71–80 st_joseph, 81–90 peace_prayer, 91–100 suscipe_prayer, else our_father); Listen/Stop (`catholic_qa_listen_button`, speaks question + labelled answer/foundation/clarification); Share question + answer + foundation (`catholic_qa_share`); Anterior/Siguiente (`catholic_qa_prev`, `catholic_qa_next`).
  - Opening/stepping to a question calls `recordTrackProgress(number)`.

### a.6 Orders & offices pane (4) — data `ReligiousOrdersData` (37 orders, 26 offices)
- "Órdenes religiosas": one list, data order; row = emblem 40dp (`assets/orders/<id>.png`, Fit, decorative) + name + "ABBR · year" (`order_<id>`) → `order:i`. Empty state "Sin órdenes por mostrar".
- "Oficios eclesiásticos": rows name (`office_<id>`) → `office:i`. Empty state.
- order page: name, meta "ABBR · year · founder", emblem 56dp (`order_emblem_detail`, contentDescription = name); progress (`order_progress`); `description`, "Carisma" (`charism`); Listen/Stop (`order_listen_button`); prev/next (`order_prev_button`, `order_next_button`).
- office page: name; progress (`office_progress`); `description`; Listen (`office_listen_button`); prev/next (`office_prev_button`, `office_next_button`).

### a.7 Churches & News pane (5)
- News: `VaticanNewsFetcher().latest(lang, 25)` via `produceState` keyed by language. States: loading (`news_loading`, spinner + "Cargando noticias del Vaticano…"), failure (empty state + "Abrir Vatican News" → vaticannews.va), success: card per item (contentDescription = title; eyebrow "Vatican News · <pubDate raw>", title heading, summary 3 lines, "Leer en Vatican News →") → browser.
- Church directory (only when not newsOnly — currently unreachable, b.26): segmented Iglesias/Noticias (`churches_news_tabs`); search (`parish_search_input`) over name/diocese/address/city/country; menu rows País (`parish_country_dropdown`), Región (`parish_region_dropdown`, only with a country), Ciudad (`parish_city_dropdown`), Mostrar (`parish_kind_picker`: all / confessions / adoration / cathedrals & basilicas); cascading resets; results (`parish_card_<id>`) or empty state + "Ver todas" reset; "Buscar en Google Maps" (`parish_open_maps`).
- Parish detail (`parish_detail_<id>`): back (`back_to_churches_button`), name, city/country; "Adoración perpetua" eyebrow; Misas domingo, entre semana (if any), Confesiones, Adoración, Diócesis (times localized via `TextFormat.times` + `ParishScheduleText`); "Cómo llegar" (`map_parish_<id>`, geo: intent, Toast fallback) and "Llamar" (`call_parish_<id>`, dialer, Toast fallback).

### a.8 Accessibility semantics to keep
Progress stateDescriptions (tracks, didache, qa, order, office); devotion header button role + Abierta/Cerrada state; heading() on news titles and devotion titles; news card contentDescription = title; emblem contentDescription on detail only; decorative hero/emblem in rows `null`; 48dp min targets everywhere.

### a.9 Persisted state
`UserPreferencesStore.trackProgress` (DataStore key `track_progress`, "track:highestQuestion,…"), written by `recordTrackProgress` on every `qa` page. Everything else is in-memory.

### a.10 Shared helpers in the file worth keeping
`scriptureCitation`/`withoutCitation`, `didacheParagraphs`, `sharePlainText`, `qaRelatedPrayerId`, `didacheRelatedPrayerId`, `foundationLevelFor/Label/Explanation`, `openMaps`, `pick`. To delete in favour of shared kit: `AssetPhoto`, `decodeSampledBitmap`, `frostedCardChrome`, `FrostedListGroup`, `GutterCard`, `StudySectionHeader`, `CardHeading` (unused), `Note`, `eyebrow()`, `tabStripInset`, `AppOrnament` usage.

---

## b. What is wrong today (specific)

Structure and navigation
1. **Wrong title on five of seven destinations**: the hero always says "Doctrina e Historia" (or "Noticias"); "101 preguntas", "Historia", "Órdenes y oficios"… never name themselves.
2. **Two back controls with different meanings** on entry pages: the scaffold's "‹ Más" pops the whole destination, the inline "‹ Preguntas" closes the entry, and system back does the latter. Plus a duplicate title (scaffold bar title + hero title once scrolled).
3. `openEntry`, segment, search and region state use `remember`: lost on rotation/process death and when coming back from Bible/Catechism deep links.
4. `DOCTRINE` and `STUDY_DOCTRINE` are the same screen; `showTabs`/`AppSegmentedTabs` code is dead.
5. `AppScreenTitle` (AppleKit) **silently drops subtitles** longer than 30 chars or containing commas and uppercases the rest: order meta "OFM · 1209 · San Francisco…", the question's theme, the sources subtitle are lost; eyebrows are ALL CAPS against DESIGN's sentence case.
6. Entry titles use `screenTitle` (34sp) for 2–9-line text (commandments, 101 questions; Q101 is 328 chars).

Hierarchy and visual
7. Card-in-card-in-card: answer card → tonal Fundamento panel → expanded passage panel; Didache paper box inside a frosted card; prayer card inside devotion card.
8. Button soup: each reader stacks full-width Rezar ahora (primary) + Escuchar + Compartir + a Prev (secondary)/Next (primary) pair: two primaries per page; Next outranks the real action.
9. Hairline borders and generic `shadow(6.dp)` (`frostedCardChrome`) and hard-coded 20/12dp radii — DESIGN forbids hard borders; radii must come from tokens.
10. Labels coloured with `accent`/`gold` (the scaffold maps both to the tint): rubric-coloured eyebrows everywhere ("Lo que manda", "Carisma", "Fiesta") — violates one-accent rule.
11. Direct `MaterialTheme.typography.*` (titleMedium, bodyLarge, labelSmall…) mixed with kit styles; long-form text in `bodyLarge` (Inter 16) instead of the reader font except scripture.
12. `AppOrnament` at the end of every list (decoration); spacing on `cardGap` 12 rather than the 28–32 section rhythm.
13. Tracks ring is a stock `CircularProgressIndicator` with "0%" in 11sp; three identical cards with no hint of what to do first; "Continuar" is a full-width selected chip.
14. Mini-quiz gives no visual answer state: the chosen and the correct option look identical after answering; only a word "Correcto/Incorrecto".
15. Orders: the emblem sits outside `AppListRow` (hairlines don't inset to text, double leading padding); emblems are heterogeneous (8 have opaque white boxes, 6 are wide logotypes 256×52–86, black line-art vanishes on the dark canvas, Opus Dei is pale yellow on ivory); 37 rows + 37 bitmap decodes inside **one** lazy item on the main thread.
16. Offices: 26 flat rows mixing altar servers, Pope and abbess with no hierarchy.
17. Devotions: 2-option category behind a dropdown; 50 accordion cards each opening into six notes and a nested "Oración" button; long scroll with no detail page.
18. History: plain list; no sense of time.
19. News: tiny spinner centred in a viewport-height box; failure has no retry; raw RFC-822 English dates ("Fri, 30 Oct 2026 13:55:00 +0100") in every language; fake link row "Leer en Vatican News →" with icon in accent; no images although the feed carries `media:content` 750×422 photos.

Content bugs ("nothing lost" violations already present)
20. `ChurchHistoryEpoch.historicalSaints` and `ChurchSchismHistory.catholicPerspective` are never displayed.
21. Marian region **`Asia_Africa`** (Akita, Kibeho) has no filter option.
22. Spanish-only strings shown in EN/PT: `centuryRange` ("Siglos I - IV (Años 33 - 313 d.C.)"), `historicalYear` ("1054 d.C."), Marian `year` ("Tradición medieval", "1854 (dogma)", "Siglo IX (tradición)").
23. Epochs are not chronological in data (`modern` XVI–XXI is 3rd) and are listed in data order.
24. 60/101 `clarification` texts start with a stray ": ".
25. PT section name differs for Q68–70 ("Salvação, purgatório…" vs "A salvação, o purgatório…"): grouping by localized name makes **12** groups in PT.
26. The church directory (40 parishes, filters, detail, directions, call) is **unreachable**: the only route to pane 5 is `CHURCHES_NEWS`, which forces `newsOnly`; Parishes' "Directorio de iglesias" lands on news.
27. Home's "Directorio Iglesias" tile opens `DOCTRINE` (Ten Commandments).
28. `questionsInitialIndex` is never cleared in MainApp: after one "Leer respuesta" from Home, More → 101 preguntas reopens that answer instead of the list.
29. Devotion prayers can't be listened to (`onSpeakText` never passed).
30. Opus Dei meta renders "— · 1928" (abbreviation "—").
31. Didache intro is shown twice (list and chapter 1); learning tracks skip Q31–40 (Mary) and Q101 (data decision, flagged, not changed).

Performance
32. Hero and emblems decoded synchronously in composition; whole 101-question list composed as one item; a new `LiturgicalTtsManager` engine per reader card, re-created on every Prev/Next.

---

## c. The new design

### c.1 Grammar shared by every destination
- **Root (list) page**: `DetailHeader` with the area's PD art (`heroes/lumen/*.jpg`), glass back circle top-left (pops the destination), eyebrow + `title1` destination title bottom-start over the mist fade, ~280dp art band so the first content row is above the fold. Top bar on scroll: blurred glass bar with 17sp inline title fades in when the title leaves.
- **Entry (detail) page**: art-less reading header (same glass back circle, optional trailing share circle), eyebrow (`eyebrow`, sentence case, inkSecondary) + title (`title1` ≤ 100 chars, else `title2`), optional meta line (`subhead`). Same scroll top bar. One back control only; system back = glass back.
- **Long text**: `reading` style (reader font, 19/30 serif or 18/28 sans), paragraphs 16dp apart, measure capped at `readingMaxWidth`, on the canvas itself (no paper box, no card around body text). Supporting facts live in glass groups; quotes/prayers in a single glass card.
- **Lists**: glass `GroupedList` rows ≥52dp, `body` title wrapping (never truncated), `footnote` subtitle, inset hairlines, chevron; section headers `headline`; section gap 28–32dp; gutter 20.
- **Actions**: one `PrimaryCapsule` per page at most ("Rezar ahora"); Listen = `SecondaryCapsule` with play/stop glyph; Share = glass circle. Reader action row (Listen + Share) sits directly under the title, so it is reachable without scrolling and never stacks above the floating tab dock.
- **Sequence footer** (all readers with prev/next): thin `ProgressBar` under the eyebrow ("Pregunta 12 de 101"); at the end a glass "Siguiente" row showing the next item's title (primary tap target, `…_next` tag) and a quieter "Anterior" row (`…_prev` tag); disabled ends are hidden, not greyed.
- **Colour**: labels ink/inkSecondary only; the liturgical accent appears once per screen at most (timeline event ring, selected state); primary capsule charcoal (ivory in dark).
- **State**: `rememberSaveable` for entry key, segments, region, queries, expanded reference. Push/pop between list and entry: 250ms cross-fade + 1/8-width slide.
- **TTS**: one lazily created `LiturgicalTtsManager` hoisted for the whole screen, released on dispose, stopped on entry change.

### c.2 Doctrine (DOCTRINE, STUDY_DOCTRINE)
Header: art `doctrine.jpg`; eyebrow "La enseñanza moral de la Iglesia" / "The Church's moral teaching" / "O ensino moral da Igreja"; title "Doctrina".
1. **Los Diez Mandamientos** — grouped list; leading 32dp glass numeral circle (I–X, `subhead` tabular); title = commandment; subtitle = citation ("Éxodo 20,2–3", language separators). `commandment_row_<n>`.
2. **Los preceptos de la Iglesia** — same pattern (I–V). New tags `precept_row_<n>`.
3. **Vicios y virtudes** — rows: vice (body) + "Virtud: Humildad" (footnote). New `vice_row_<i>`.
4. **El pecado y la confesión** — 3 rows. New `sin_row_<i>`.
5. **Didaché** — one glass ContentCard: eyebrow "Siglo I · 16 capítulos", title "Didaché", first sentence of the intro (2 lines), chevron → Didache contents page.

Entry pages:
- **Commandment**: eyebrow "Primer mandamiento", title; scripture wording as a pull quote (reading italic, 2dp inkTertiary bar at leading edge) + citation as a reference chip (opens Bible via `parseFoundation` when it parses; plain footnote otherwise — new deep link); explanation (reading); two glass cards "Lo que manda" / "Lo que prohíbe" (headline + callout). Sequence footer across the 10 (new).
- **Precept / Vice**: same; vice page highlights the opposing virtue in a glass card (`title2` virtue name) followed by "Remedio" and "En la práctica". Sequence footers (new).
- **Sin pages**: 0 → two glass cards (venial, mortal); 1 → lead sentence + `StepList` of 3 conditions; 2 → heading line + `StepList` parsed from the "1. … 5." lines (any non-numbered lines kept as paragraphs).
- **Didache contents** (new kind `didache_toc:0`): reading header "Didaché"; intro paragraphs in reading style (single place it appears; first paragraph as lead); "Capítulos" grouped list with numeral circles (`didache_chapter_<n>`).
- **Didache chapter**: eyebrow "Capítulo VII de XVI", title heading, `didache_progress`; reader action row (`didache_listen_button`, `didache_share`); text in reading style (`didache_reader` on the text column); chapter 1 shows the intro as a collapsed "Introducción" disclosure (TTS keeps including it on ch.1); "Rezar ahora" PrimaryCapsule with the prayer's name under it (`pray_now`, title from `repository.vaticanPrayers`); sequence footer (`didache_prev_button`, `didache_next_button`).

### c.3 101 Questions (QUESTIONS)
Header: `questions.jpg`; eyebrow "11 temas · respuestas con fuentes" / "11 themes · sourced answers" / "11 temas · respostas com fontes"; title "101 preguntas sobre la fe católica" (2 lines, balanced).
1. **Search field** "Buscar entre las 101 preguntas" (new). Non-empty query replaces the page body with matching rows (question text first, then answer matches), theme as subtitle; empty state "Sin resultados".
2. **Pregunta del día** — glass ContentCard; same index as Home (`DAY_OF_YEAR % size`); eyebrow "Pregunta del día · <tema>", question `title2`, first sentence of the answer (callout, 2 lines), SecondaryCapsule "Leer respuesta". New tag `qa_daily`.
3. **Continuar** (only with progress) — glass card "Continúa donde lo dejaste", "Pregunta 23 · <question 2 lines>", thin progress (`qa_track_continue`).
4. **Itinerarios** — glass group of 3 rows: leading 44dp gradient `ProgressRing` with % caption (`qa_track_ring_<i>`, stateDescription kept), title `headline`, "Preguntas 1–30 · 12 de 30" footnote (`qa_track_<i>`). Tap resumes at the first unread question of that track (start if new, start again if complete).
5. **Mini-quiz** card per completed track (`qa_track_quiz_<i>`): question `headline`; options as glass CheckRows with radio circle (`…_opt_<o>`); after answering, chosen option shows ✓ (success) or ✕ (ink), the correct option shows ✓; feedback line "Correcto/Incorrecto" (`…_feedback`) + explanation (footnote).
6. **Temas** — grouped list of 11 themes: leading numeral circle, theme name (wraps to 2 lines), trailing count "10". Grouping keyed on `section.es` + first-appearance order (fixes the PT 12-group bug). Tap → theme page (new kind `theme:<n>`): reading header eyebrow "Tema 3 de 11 · 10 preguntas", title1 theme name, rows "21  ¿…?" (`catholic_question_<n>`).
7. **Fuentes y método** row (`sources_entry`) at the bottom with outline book glyph.

**Answer page** (`qa:i`): eyebrow "Pregunta 12 de 101 · <tema>" + `catholic_qa_progress`; question as title (title1/title2 rule); action row Listen (`catholic_qa_listen_button`) + Share (`catholic_qa_share`).
- **Answer**: reading style; first paragraph is the lead (ink), the rest reading style; no card.
- **Fundamento** (`headline`): reference chips in a FlowRow — glass capsules "Romanos 8,18–23" / "Romans 8:18–23" / "Catecismo 309–314", leading book/catechism glyph, 44dp hit (`foundation_ref_<refKey>`); a selected chip fills with the action colour and opens one glass passage card under the row: chip label (`headline`), verses in reading style with small inkSecondary verse numbers / CCC paragraphs (`foundation_ref_text_<refKey>`), SecondaryCapsule "Abrir en la Biblia/Catecismo" (`foundation_ref_open_<refKey>`). The raw foundation text is shown as callout above the chips only when it contains prose or segments the parser did not turn into chips (e.g. Q101), so no citation is lost and pure citation lists are not printed twice; the chip group carries the full foundation text as its contentDescription.
- **Foundation level** pill under the chips: glass capsule "A · Bíblico explícito" + explanation (footnote) (`foundation_level_badge`).
- **Aclaración**: glass card, `headline` label + callout body; leading ": " stripped at display.
- **Rezar ahora** PrimaryCapsule + prayer name footnote (`pray_now`).
- Sequence footer: "Siguiente" shows the next question text (`catholic_qa_next`), "Anterior" (`catholic_qa_prev`).
- Deep link from Home opens here; back goes to the Questions root.

**Sources page** (`sources_page`): reading header "Fuentes y método" + subhead subtitle (now shown); sections (headline + callout): Catecismo, Didaché, Documentos del Vaticano (grouped list, one row each), Niveles de fundamento (grouped rows: letter circle A–D, label, footnote explanation), Uso honesto (footnote glass card).

### c.4 Mary & Jesus (STUDY_MARY_JESUS)
Header: `mary_jesus.jpg`; eyebrow computed "30 advocaciones · 20 devociones"; title "María y Jesús".
1. `SegmentedControl` "Nuestra Señora | Cristo" (`devotions_category_picker`).
2. Search field (`devotions_search_input`).
3. FilterChips, horizontally scrollable: Todas · Américas · Europa · **Asia y África** · Universales (`devotions_region_picker`; adds the missing region).
4. Results: with "Todas", grouped by region with section headers and counts; otherwise one group. Rows (`devotion_card_<id>`): main title (trailing parenthetical alias removed, e.g. "Nuestra Señora del Valle"), subtitle location (Marian) / country (Christ), trailing localized year for Marian (tabular footnote). Empty state with "Ver todas" reset.

**Devotion detail** (new kind `devotion:<id>`): eyebrow "Advocación mariana · 1531" / "Devoción a Cristo"; title1; alias line (subhead) from the parenthetical; facts glass group of stacked label/value rows: Fiesta, País, Lugar (Marian location), Santuario, Patronazgo (Marian); "Historia" (`historyAndApparition` / `originHistory`) in reading; "Sentido teológico" (Christ) in reading; "Promesas" glass quote card; "Milagros"; **Oración** glass card: title, prayer in reading style, Listen capsule (new, works now) (`devotion_prayer_listen`). Tags `devotion_detail_<id>`.
Localized year: keep digits ("1531", "40" + "d. C."/"AD"/"d.C."), map known phrases ("Tradición medieval" → "Medieval tradition"/"Tradição medieval", "(dogma)", "Siglo IX (tradición)" → "9th century (tradition)"/"Século IX (tradição)"; unknown phrases fall back to the raw value).

### c.5 History (STUDY_HISTORY)
Header: `history.jpg`; eyebrow "Veinte siglos de la Iglesia" / "Twenty centuries of the Church" / "Vinte séculos da Igreja"; title "Historia".
**Timeline**: a 1dp rail (separator token) at gutter+12dp with nodes; to its right one glass card per epoch: eyebrow years (tabular, "33–313"), title `headline`, century line (footnote, localized "Siglos I–IV" / "Centuries I–IV" / "Séculos I–IV"), 2-line narrative excerpt (callout, inkSecondary), chevron. New tag `epoch_row_<epochId>`.
Order: explicit chronological `epochId` list (apostolic, councils, papal_states_medieval, gregorian_reform, mendicant_orders, reformation_trent, baroque_missions, enlightenment_suppression, vatican_i_modern, modern as the closing overview), unknown ids after by parsed start year. Schisms sit on the same rail as **event nodes** (accent ring, no card): "1054 · El Gran Cisma de Oriente ›", placed before the first epoch starting at or after their year (`schism_row_<id>`). Keys stay `epoch:<dataIndex>` / `schism:<dataIndex>`.
**Epoch page**: eyebrow "Siglos I–IV · 33–313", title1; narrative in reading; facts group: Papas clave, Concilios, **Santos de la época** (`historicalSaints`, new); sequence footer in chronological order (new).
**Schism page**: eyebrow year (localized, "1054" + era suffix per language); context in reading; "Diferencias doctrinales" as `StepList` parsed from the numbered lines; **"Perspectiva católica"** (`catholicPerspective`, new); "Diálogo y reconciliación".

### c.6 Orders & offices (STUDY_ORDERS)
Header: `orders.jpg`; eyebrow computed "37 órdenes · 26 oficios"; title "Órdenes y oficios".
`SegmentedControl` "Órdenes | Oficios" (new tag `orders_offices_tabs`).
- **Orders**: lazy grouped list (data order) — leading **EmblemPlate** 44dp (rounded tile, radius = tile token, always-light ivory plate `#F6F4F1` in both modes with lit edge, emblem `Fit` inset 6dp; decoded off the main thread, cached), title name (wraps), footnote "OFM · 1209" ("—" abbreviations dropped). `order_<id>`.
- **Order detail**: EmblemPlate 104dp (emblem 80dp ≈ 225px, no upscaling of the ≤256px sources) left-aligned above eyebrow "OFM · Fundada en 1209" and title1 name (`order_emblem_detail`, contentDescription = name); founder line (subhead, now visible); progress (`order_progress`); action row Listen (`order_listen_button`); description in reading; "Carisma" glass quote card; sequence footer (`order_prev_button`, `order_next_button`).
- **Offices**: grouped by hierarchy with an explicit id map, data order within groups; unknown ids go to "Otros":
  "Servicio al altar" (monaguillo, lector, acólito) · "Clero" (diácono, subdiácono histórico, sacerdote, párroco, vicario parroquial, canónigo, obispo, arzobispo, metropolitano, patriarca, cardenal, papa) · "Vida consagrada" (novicio, monje, hermano religioso, monja, hermana religiosa, madre superiora, abadesa, madre general) · "Misión y laicado" (misionero, misionero laico, laico consagrado). Rows `office_<id>`.
- **Office detail**: eyebrow = group name, title1; progress (`office_progress`, position in display order); Listen (`office_listen_button`); description in reading; sequence footer in display order (`office_prev_button`, `office_next_button`).

### c.7 Churches & News (CHURCHES_NEWS)
Header: `news.jpg`; eyebrow "Vatican News · iglesias"; title "Iglesias y Noticias". `SegmentedControl` "Noticias | Iglesias" (`churches_news_tabs`) — makes the directory reachable again; new optional param `initialChurchesSection` (0 news, 1 churches).
- **News**: first story as a featured `ImageCard` (16:9 photo if `imageUrl`, bottom scrim, white title2 + meta) or, without a photo, a glass card (title2 + summary 3 lines). Then rows: 72dp rounded thumbnail (when available), title `headline` up to 3 lines, meta footnote. Whole row opens the article (contentDescription = title, role link); no fake link row. Date: RFC-822 parsed with `SimpleDateFormat(Locale.US)` (minSdk 24, no java.time) → "Hace 2 h" / "2 h ago" / "Há 2 h" under 24h, else "30 oct" / "Oct 30" / "30 out".
  Loading (`news_loading`): three skeleton glass blocks at reduced opacity (no shimmer) + footnote "Cargando noticias del Vaticano…". Failure: designed empty state + "Reintentar" (new; re-keys the fetch) + "Abrir Vatican News".
- **Churches**: search (`parish_search_input`); FilterChips for kind (`parish_kind_picker`: Todas · Con confesiones · Con adoración · Catedrales y basílicas); grouped picker rows País/Región/Ciudad (tags kept); results grouped list with count (`parish_card_<id>`); empty state + "Ver todas"; SecondaryCapsule "Buscar en Google Maps" (`parish_open_maps`).
- **Parish detail** (`parish_detail_<id>`): glass back (`back_to_churches_button`), title1 name, eyebrow city · country, "Adoración perpetua" pill with season dot; schedule glass group of label/value rows (Misas del domingo, Entre semana, Confesiones, Adoración, Diócesis); actions as grouped rows with outline icons: "Cómo llegar" + address (`map_parish_<id>`), "Llamar" + number (`call_parish_<id>`).

### c.8 Dark mode
Art fades into `#171614→#0E0D0C`, never glowing; glass at 7%, edges 14%→4%. EmblemPlates stay ivory (slightly dimmed to `#ECE9E5`) so black line emblems and white-boxed logos read as enamel medals, not stickers. Timeline rail 8% white; event ring accent. Reference chips selected = ivory fill with charcoal text. News thumbnails get a 6% dark veil. Quiz ✓ uses `success`; ✕ uses ink. Every screen rendered and judged in both modes.

### c.9 ES / EN / PT lengths (no truncation, no awkward wraps)
- Titles: "101 preguntas sobre la fe católica" (34) / "101 Questions on the Catholic Faith" (35) / "101 Perguntas sobre a fé católica" (33) → 2 lines title1. "Iglesias y Noticias" / "Churches & News" / "Igrejas e Notícias" fit one line.
- Segments: "Nuestra Señora | Cristo", "Our Lady | Christ", "Nossa Senhora | Cristo"; "Órdenes | Oficios", "Orders | Offices", "Ordens | Ofícios"; "Noticias | Iglesias", "News | Churches", "Notícias | Igrejas" — all < 16 chars per segment.
- Chip rows scroll horizontally ("Catedrales y basílicas", "Cathedrals & basilicas", "Asia y África").
- Theme names up to 57 chars ("La salvación, el purgatorio, el pecado y la vida eterna") wrap to 2 lines in rows and title1 on theme pages.
- Devotion titles up to 64 chars: alias split keeps rows ≤ 2 lines.
- Order names up to 66 chars ("Hijos del Corazón Inmaculado de la Bienaventurada Virgen María") wrap ≤ 2 lines beside a 44dp plate.
- Buttons: "Abrir en el Catecismo"/"Open in the Catechism"/"Abrir no Catecismo"; "Rezar ahora"/"Pray now"/"Rezar agora"; "Reintentar"/"Try again"/"Tentar de novo" — single line in a full-width capsule.
- Q101 (328 chars) uses title2; answers' 14 paragraphs fine in reading flow.
Renders checked in ES (all), EN and PT (questions, history, orders, mary_jesus) at 411dp.

### c.10 Accessibility
Headings on titles and section headers; progress `stateDescription` kept; chips expose "Romanos 8,18–23" + expanded/collapsed state; quiz options expose selected/correct; timeline cards merge "33–313, La Iglesia apostólica…"; event nodes "Cisma, 1054, …"; emblem decorative in rows, named on detail; all targets ≥44dp (rows ≥52, capsules 52).

### c.11 Performance
Proper `items(key, contentType)` everywhere (questions, orders, offices, devotions, news, parishes); async + cached asset decoding (shared loader); one TTS instance; `remember`ed parses (refs, epoch order, office groups).

---

## d. Component needs

### d.1 From the foundation (AppKit / AppComponents)
1. **DetailHeader**(asset, eyebrow, title, onBack, actions, height, modifier, imageTestTag) with async sampled decode, mist, title1 up to 3 lines balanced; and an **art-less variant** (asset = null) for reading pages. `imageTestTag` keeps `doctrine_hero_photo`.
2. **Back wiring**: the Scaffold must not draw its own back/title on screens that draw a DetailHeader, and must give the screen the destination pop (either an `onBack` param passed by MainApp or a `LocalScreenBack`). Inner entries override it.
3. **TopBar on scroll** usable with a `LazyListState` (inline title + glass back once the header scrolls away).
4. **GroupedList / Row** with a `leading` composable slot (numeral circle, emblem plate, radio), unlimited title/subtitle lines, trailing value, modifier/testTag passthrough.
5. **SegmentedControl** (2–3 options, testTag). **FilterChips** horizontal row (testTag on row). **SearchField**.
6. **ContentCard** (eyebrow, title, body, trailing action/chevron, onClick).
7. **PrimaryCapsule / SecondaryCapsule** (leading icon) and **GlassCircleButton** (40dp visual / 44dp hit, contentDescription).
8. **ProgressRing** small (44dp, centred caption, stateDescription) and thin **ProgressBar** (stateDescription, testTag).
9. **StepList** + a standalone **NumberBadge** (glass circle with numeral/letter).
10. **Chip** with leading icon and selected state (reference chips).
11. **ImageCard** accepting a remote model (Coil) as well as an asset.
12. **EmptyState** (icon, title, body, actions) and a **loading placeholder** block.
13. Type tokens `title1`, `title2`, `headline`, `callout`, `subhead`, `footnote`, `eyebrow`, `caption`, `reading` honouring the font picker; tokens for gutter 20, section gap, radii (card 24, group 20, tile 20, image 22).
14. Shared **async asset image** loader with cache (to replace the per-screen `decodeSampledBitmap`).

### d.2 Private to my files
EmblemPlate · TimelineRail/EpochNode/EventNode · ReferenceChips + PassageCard · FoundationLevelPill · QuizOptionRow · FactsGroup (stacked label/value rows) · ReaderActionRow · SequenceFooter · DevotionRow · NewsFeatureCard / NewsRow / NewsSkeleton · PrayerCard · formatters (`centuryLabel`, `eraYear`, `devotionYear`, `newsDate`, citation separators, `cleanClarification`, alias split) · office group map · epoch order list.

### d.3 Requests to other owners (orchestrator)
- **MainApp**: (a) clear `questionsInitialIndex` when QUESTIONS is opened from More (b.28); (b) route Home's "Directorio Iglesias" and Parishes' "Directorio de iglesias" to CHURCHES_NEWS with `initialChurchesSection = 1` (b.26–27); (c) pass `onBack` (d.1.2).
- **Data (news)**: add `imageUrl: String? = null` to `VaticanNewsItem`, parsed from `<media:content url=…>` (`isNamespaceAware = false`, tag `media:content`); the default keeps `VaticanNewsTest` green.
- **Data (optional)**: unify PT section label for Q68–70; strip leading ": " from clarifications; decide whether tracks should cover Q31–40 and Q101 (UI handles the first two regardless).

---

## e. Build order and verification (step 2, once the kit is ready)
1. Shell: header/back wiring, saveable entry state, hoisted TTS, delete local chrome helpers. Build.
2. Questions (root, theme, answer, sources, quiz) → Doctrine + Didache → History → Orders/Offices → Mary & Jesus → Churches & News. Build after each.
3. Render `doctrine, study_doctrine, questions, study_mary_jesus, study_history, study_orders, churches_news` light + dark ES (`-Pshots.height=1600` too), EN/PT spot checks.
4. Proposed new files I would own: `screenshots/DoctrineDetailScreenshotTest.kt` (entry pages via a test-only `initialEntry: String? = null` param) and `DoctrineInteractionTest.kt` (deep link → answer, chip expand/open, prev/next, quiz states, directory reachable).
5. Run `CatholicQuestionsDataTest`, `DidacheDataTest`, `ReligiousOrdersDataTest`, `VaticanNewsTest`, `FoundationRefParserTest`, `AppNavigationTest`, `DesignContrastTest`; phone check via `tools/device/adb.sh`; builder review in `docs/redesign/reviews/doctrine-round1-builder.md`.
