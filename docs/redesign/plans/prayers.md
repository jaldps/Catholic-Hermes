# Prayers & Novenas & Night prayer: audit and plan (Lumen)

Owner: Prayers area agent. Files: `ui/screens/PrayersAndNovenasScreen.kt` (2844 lines),
`ui/screens/NightPrayersScreen.kt` (1467 lines). Read with `DESIGN.md` / `PROCESS.md`.
Status: STEP 1, plan only. No code changed.

---

## a. Inventory: the "nothing lost" checklist

### Entry point and navigation contract (MainApp, foundation-owned)

`PrayersAndNovenasScreen(repository, ttsManager, currentLanguage, modifier, dao, initialPrayerId, initialTab, initialNightOfficeTab, initialOpenChaplet)`.
Keep this signature. New optional params need the foundation owner's agreement (see d).

| Deep link | Source | Today | Keep as |
|---|---|---|---|
| `initialPrayerId` | Home: `laudes`, `angelus`, `morning_offering`; Habits: `angelus`, `jesus_prayer`; Doctrine: Didache / 101-questions related prayer | tab 0, opens prayer detail. Unknown ids are ignored. | same; back goes to the Prayers landing |
| `initialOpenChaplet` | Home chaplet tile | tab 0, chaplet | same |
| `initialTab=2`, `initialNightOfficeTab` 0/1/2 | Home night tile (2); Habits EXAMEN (1) and TRADITIONAL (2) | Night tab with that office selected | Night tab with **that office's reader open**; back goes to the Night overview |
| `initialTab=1` | (none today) | Novenas index | same |
| nav bar tap | MainApp resets all four | landing | same |

Internal APIs that other files use, which must keep the same signatures: `withLiturgicalSigla(text, SpanStyle)`, `rubricSigil()`, `VersicleSign`, `ResponseSign`, `SiglaPattern` (BibleScreen:1332 uses the first two).

Tests: `AppNavigationTest` asserts `prayers_and_novenas_screen` after `nav_prayers`. No other test touches this area. `FullAppScreenshotTest` renders only the first screen (the Prayers landing).

### Data sources
- `repository.vaticanPrayers`: 69 `VaticanPrayer` (id, title, latinTitle, category, leaderText, responseText?, whenToPray, indulgenceNote?, thematicTags).
- `repository.searchPrayers(query, category, tag)`: matches on title and leader text.
- `repository.officialNovenas`: 246 crawled novenas (`CrawledNovenasData`). Every one has a remote `imageUrl` on catholicnovenaapp.com, loaded with Coil. Every `category` is "Novenas". In all 3068 days, `theme` is just "Día N", `scripture` is empty, and `meditation` is the same as `prayer`. Every `promises`, `miraculousHistory` and `intentionGuidance` is empty. Tags: love, health, family, peace, money, comfort, blessings, impossible. 236 novenas have 9 days. The other 10 have 10, 26, 30, 46, 54 or 144 days, and two have 275 day entries.
- `LiturgicalCalendar.dayInfo(today())` gives the season prayer (`seasonPrayerId`: Marian feast → memorare; Lent, Holy Week, Triduum → act_of_contrition; Advent → advent_antiphons; Easter → easter_prayer; Christmas → christmas_prayer; otherwise our_father).
- `repository.complineOffice` (8 parts plus 3 Marian antiphons) and `repository.ignatianExamenSteps` (5). Traditional night prayer = prayer ids persignarse, our_father, hail_mary, glory_be, guardian_angel, bendita_sea_tu_pureza, signarse, immaculate_conception, plus an inline step "Gracias por el día" (ES/EN/PT). Guardian angel uses its leader text only.
- Inline content that must survive word for word: the chaplet (opening, lead bead, small bead, closing, Hour of Mercy, Diary 687 promise, "Revelada a santa Faustina en Vilna, 1935"); How to pray, 5 steps × ES/EN/PT; Compline Latin (Confiteor, Psalm 91, concluding blessing); the examen and traditional blessings at completion; all empty-state copy.

### Persisted state
- Room `NovenaProgressEntity` through `dao.getAllNovenaProgress()` (Flow), `getNovenaProgress`, `saveNovenaProgress`, `deleteNovenaProgress`. `currentDay` holds a **bitmask of the days prayed (days 1–31 only)**. `personalIntention` is kept but nothing in the UI edits it. `startDate` is set on the first mark. `isCompleted` is true when every day is prayed.
- There is a session optimistic echo (`sessionDaysPrayed`) that unions with the DB rows. When there is no DAO (previews, screenshots), it is the whole record.
- Everything else is session `remember` and is lost on rotation: the tab, queries, filters, open prayer, chaplet step/decade/bead, the selected novena and day, compline Latin parts, the antiphon index, and examen/traditional progress.

### Screens, states, elements, actions

**Shared header**: votive-candle photo band (220dp) with the title "Oraciones" (`screen_header_illustration`), then `AppSegmentedTabs` Oraciones / Novenas / Noche.

**Prayers tab (VaticanDevotionalView)**
- Search (`prayer_search_input`).
- Season prayer row (`season_prayer`), subtitle = season name. Shown only when nothing is filtered.
- How to pray, a collapsible card (`how_to_pray_section`, `how_to_pray_toggle`). Steps (`how_to_pray_step_0..4`).
- Category tiles 3×2 (`prayer_theme_picker`, `prayer_category_tile_{basic|all|family|protection|health|forgiveness}`). Basic filters on `PrayerCategory.BASIC`. "Intenciones" means all (no filter). The others filter on tags. Selected state is in semantics.
- Empty state: "Ninguna oración coincide" plus "Ver todas" (`prayer_clear_filters`).
- Groups: Fundamental (BASIC), "Cada día" (daily_routine), "Otras oraciones". When filtering, one "Resultados" group instead. The season prayer is never listed twice. Rows: `prayer_row_{id}`.
- Chaplet row (`open_chaplet_row`).

**Prayer detail**: `prayer_detail_{id}`, back link (`back_to_prayers_button`), title, leader text (℣) and response (℟), with the line currently being spoken highlighted. "Cuándo rezarla". Indulgence when present. Listen/Stop with `speakDialogue(leader, response)` / `stop()` (`play_prayer_audio_{id}`). System back closes it.

**Divine Mercy chaplet**: back (`back_from_chaplet_button`), title + subtitle, hero `heroes/divine_mercy.jpg` (`chaplet_hero`, cd = title), Diary 687 promise, Listen/Stop of the current step text (`chaplet_tts_button`), and part tabs Inicio / Decenas / Cierre / 15:00 (default Decenas).
- Inicio: text + "Comenzar la 1ª decena".
- Decenas: eyebrow lead/small bead with "Decena d de 5 · cuenta b de 10", a rail of 11 beads (tap to select), bead text, Listen, Atrás / Avanzar, and "Ir a la conclusión" on the last bead.
- Cierre: text + "Rezar la novena de 9 días". This should open the Divine Mercy novena but looks up `divine_mercy`, which is not in `officialNovenas`, so it falls back to the **first novena (Cristo Rey)**. Bug.
- 15:00: banner + text + Listen.

**Novenas index**
- Search (`novena_search_input`), which matches title, patron, description and every day's text.
- Intention dropdown (`novena_intention_picker`, icon per intention; only tags that exist in the data).
- "En curso" (`novenas_in_progress`), most recent start first. Cards add "Comenzada el {date}".
- "Completadas" (`novenas_completed`).
- Empty state + "Ver todas".
- "Todas las novenas", with "N de 246" when filtered.
- A lazy grid with 2 per row (`novena_card_{id}`): image (cd = title, Church icon when there is no URL), title (3 lines), patron, progress label ("9 días" / "Día n de N" / "✓ Completada"), and a bar with stateDescription.

**Novena day page**
- Back (`back_to_novenas_button`), category eyebrow, title + patron, image plate (bundled fallback under the remote image), description.
- Progress card: "Progreso: día X de N", "k rezados", a day rail (tap selects a day), a bar with stateDescription, and "Reiniciar novena" (`restart_novena_button`), which deletes the row with no confirmation.
- Day card: "Día N", scripture, theme, meditation (only when it differs from the prayer), "Tu intención" guidance.
- Prayer card: text, Listen/Stop (`novena_listen_button`), and Completar día / Deshacer día (`complete_novena_day_button`). It is a toggle and advances to the next day only on a fresh mark.
- Ornament. "Los nueve días" list (theme, scripture, "✓ n", selected). "Promesas y milagros" when present.

**Night (NightPrayersScreen, tag `night_prayers_screen`)**
- Office segmented control Completas / Examen ignaciano / Tradicional (`night_office_picker`, cd "Oficio").
- Compline: header + "Rezar"/"Detener" primary (`compline_tts_button`, stateDescription Narrando / Detenido; reads 6 parts), and the line "Narrando el oficio…". Parts 1–7 each have a caption, a reference, a Latin toggle (state per part, cd + Expandido / Contraído stateDescription, kept across scroll), and text with ℣/℟ sigla. The psalm is split into numbered stichs. The canticle verses are numbered 29–32, followed by the doxology. Part 8 is the Marian antiphon: chips (`marian_antiphon_picker`), a card with the translated title, Latin and vernacular texts, and "Escuchar {antiphon}".
- Examen: header; metric tiles "k/5" and "%"; a bar (cd + stateDescription); "Los cinco pasos" rows (Hecho / Pendiente state); a step card (eyebrow "Paso n de 5", title, description, "Oración sugerida", guided prayer, Escuchar (`examen_tts_button`), Hecho/Deshacer); and a completion card with a blessing.
- Traditional: the same structure with 9 steps, a guidance line for persignarse/signarse, Listen (`tradicional_tts_button`), Hecho/Deshacer, Anterior / Siguiente, and a completion card.

**TTS (complete list)**: `speakDialogue` (prayer detail); `speakSingle` (chaplet step / bead / hour, novena day, compline office, antiphon, examen step, traditional step); `stop()`. Prayers watch `currentlySpeaking`; Night watches `isPlaying`.

**Semantics to keep**: `selected` on tiles and chips; `heading()` on office parts, step titles and completion titles; every stateDescription listed above; image content descriptions; 48dp targets (these become ≥44dp per DESIGN; 48dp stays).

---

## b. What is wrong today

**Global**
- A black band under the status bar. The scaffold pads instead of drawing edge to edge.
- Hard-coded geometry and colours: `IveGutter`, radii of 18 and 16, `Color.Black` pill, 312dp card height, a 20/22-scaled section-title hack.
- `rememberHeroBitmap` is duplicated in both files.
- Vatican-era decor: ALL-CAPS `caps()`, `AppOrnament`, gold eyebrows, accent-red sigla and text links.
- Session state is lost on rotation.

**Prayers landing**
- A 220dp saturated orange candle photo: decorative, not misted. In dark mode it sits on black. The first content starts about 1/3 of the way down the screen.
- The order is wrong. Search, then season, then How to pray all come before the categories, so the tiles fall below the fold. The reference puts the tiles first.
- The "Intenciones" tile means "all" and is selected at rest, filled with green `accentMuted`. Icons mix filled and outlined.
- Tiles filter in place. Filtering hides cards above, so the page jumps.
- Rows are 69 separate floating rectangles (no grouped list, no hairlines) in three long groups. "Otras oraciones" is a junk drawer.
- Seasonal prayers (Advent, Christmas, Lent, Easter) appear under Fundamentales all year.
- The chaplet is buried as the last row.
- How to pray is a disclosure labelled "Toca para ver" with 5 heavy cards. DESIGN asks for a step-list screen with art.

**Prayer detail**
- No art. A second back control (text link) under the scaffold.
- The text sits inside a card. Half of every prayer, the response, is greyed out. Liturgical ℣/℟ appear on private prayers, in accent red.
- `latinTitle` is not shown. There is no share and no Aa.
- Notes are in ALL CAPS, and the indulgence is in accent colour.

**Chaplet**
- A text-link back, a cropped 176dp plate, two Listen buttons on the Decenas view, a stack of buttons inside a card, and a "15:00" banner.
- The novena link opens the wrong novena.

**Novenas index**
- A dropdown instead of chips. No Mary / Saints / Jesus / Popular filters.
- Tonal cards are 312dp tall with a 108dp thumbnail. The art is a sticker, not the card.
- A 0% progress bar is drawn on all 246 cards.
- The patron line shows poor data (for example "Santo patrón de el Reino de Jesús", and the PT text repeats the title).
- Fixed heights clip at large font sizes. Remote art has no mist treatment.
- Offline, and in Robolectric, the placeholder is a church icon on a flat tint.

**Novena day**
- One long page that mixes overview, progress, the rail, reset, day and prayer. There is no hero and no "Comenzar novena" call to action.
- The "NOVENAS" eyebrow is meaningless (every category is "Novenas").
- "DÍA 1" eyebrow plus a "Día 1" title is a duplicate, because the theme is always "Día N". The meditation never shows (it equals the prayer). "Los nueve días" repeats the rail.
- The heading is wrong for 30/54/275-day novenas. The rail breaks past about 11 days (54 beads are about 4dp; 275 are invisible).
- The page auto-advances to the next day right after "Completar" (novena days are prayed one per day).
- Reset deletes progress with no confirmation.
- The whole prayer turns accent colour while it is read aloud.
- **Days above 31 are never persisted** (bitmask): 10 novenas are affected.
- **257 day texts are longer than Android's TTS 4000-character limit**, so Listen fails silently on them.

**Night**
- Two stacked segmented controls (tab, then office).
- No nocturne character: the daytime canvas, no art, ornaments, ALL-CAPS Latin links.
- "Rezar" is ambiguous (it is text to speech) and reads only 6 of 8 parts.
- The Marian antiphon is always Salve Regina, even in Eastertide (Regina Cæli is the one for that season).
- Examen and Traditional show progress three times (two metric tiles and a bar). Step titles from the data keep their "1." prefix. Four buttons are stacked.
- "¡Examen Completado!" is shouty. The Traditional subtitle lists all 9 prayers in one sentence.

---

## c. The new design

Shared rules:
- Edge to edge (`AppEdgeToEdge`); scroll content padded with `appScreenInsets()` so it clears the dock.
- 20dp gutter, 28–32dp section gaps, everything from tokens.
- Inter for UI; `styles.reading` (the reader font, 19/30 serif) for every prayer text.
- In-screen detail screens use `AppBackHandler`, so the one glass back button sits top-left and there is never a text link.
- Navigation inside the tab is a small saveable route stack per segment: Prayers: Landing → Category | HowTo | Chaplet | Prayer(id); Novenas: Index → Overview(id) → Day(id, n); Night: Overview → Office(n). Cross-fades take 250ms.

### 1. Header (all three segments)
- `AppLargeTitleHeader("Oraciones")` with a glass search circle on the right. The circle shows on Prayers and Novenas only.
- Tapping it expands an inline glass `SearchField` under the title (autofocus, clear button). It stays open while a query is non-empty.
- Then the Lumen `SegmentedControl` Oraciones / Novenas / Noche. EN: "Prayers / Novenas / Night" (shortened from "Night prayer" to match the other two).
- The title does not change with the segment, so it never duplicates the control.
- On scroll, the glass top bar fades in with the inline title "Oraciones".

### 2. Prayers landing (top to bottom)
1. **Category tiles 3×2** (`AppCategoryTile`, outline icons in one ink). The grid keeps `prayer_theme_picker`.
   - Básicas: heart. Intenciones: leaf/spa. Familia: people. Protección: shield. Sanación: sparkle. Perdón: ∞ (AllInclusive).
   - Nothing is selected at rest. A tile **pushes a Category page**.
2. **Para este tiempo**: a glass `ContentCard` (`season_prayer`).
   - Header: liturgical-season dot plus the eyebrow "Tiempo Ordinario · Semana XXXII".
   - Title: the prayer name in title2. Then a 2-line excerpt (callout, inkSecondary).
   - A trailing round glass play button (listen, `season_prayer_listen`). Tapping the card opens the detail.
   - Optional: after 19:00 the card swaps to "Esta noche · Completas" with a moon dot and opens Night. Decide during review.
3. **Fundamentales** (headline): a glass GroupedList with inset hairlines and chevrons.
   - Order: Señal de la cruz, Padre Nuestro, Ave María, Gloria, Credo.
   - Last row: "Ver todas · 21", which opens the Básicas page.
   - Seasonal and chaplet-part prayers are excluded from this list (they are listed in 4 and 5).
4. **Guiadas**: two `ImageCard`s side by side, the same grammar as the novenas.
   - "Coronilla de la Divina Misericordia" (divine_mercy art, meta "5 decenas", `open_chaplet_row`).
   - "Cómo orar" (sunrise_mountains art, meta "5 pasos"; wrapper `how_to_pray_section`, card `how_to_pray_toggle`).
5. **Tiempos litúrgicos**: GroupedList rows with a season dot in each liturgical accent: Adviento, Navidad, Cuaresma, Pascua (plus Regina Caeli). The current season gets the trailing value "Ahora".
6. **Más devociones**: GroupedList by `PrayerCategory`, with a leading outline icon, a trailing count and a chevron. Each row pushes a Category page: Mañana y noche, María, Eucaristía, Espíritu Santo, Protección y sanación, Santos y peticiones, Ángelus.
   - Every one of the 69 prayers stays reachable within two taps.
7. Bottom inset.

**Search active**: the landing content cross-fades to a "Resultados · N" GroupedList. Rows (`prayer_row_{id}`) carry a category footnote. When empty: a glass empty state, a SearchOff outline icon and "Ver todas" (`prayer_clear_filters`).

**Category page**: title1 (tile label) with a count meta, then a GroupedList of `prayer_row_{id}`.
- **Intenciones** is "all prayers": FilterChips (Todas, Paz, Trabajo, Fe, Misericordia, Misiones, from the existing tags), and the list is grouped by category.
- The existing `selectedCategory` / `selectedThematicTag` filters feed this page unchanged.

### 3. Prayer detail (follows the reference panel)
- `DetailHeader` with art (map below), mist, the glass back button, title1 = prayer title.
- Meta footnote: `latinTitle` · category title (for example "Ave Maria · Oraciones fundamentales").
- Text in `reading`. The leader and response are paragraphs in the same ink.
- ℣/℟ appear only where the data types them itself (for example the Angelus). Sigla are inkSecondary semibold, not red.
- While the voice is speaking, the paragraph being spoken sits on a soft glass highlight (instead of a colour change).
- **A glass action row after the text**: Share (an `ACTION_SEND` intent with the title and text), Listen/Stop (`play_prayer_audio_{id}`, stateDescription), and Aa (opens the font picker; needs a hook).
- Then "Acerca de esta oración": a GroupedList with "Cuándo rezarla" and, when present, "Indulgencia" (footnote, ink).
- Tag `prayer_detail_{id}` on the root. `back_to_prayers_button` moves to the glass back button through the back override (the foundation must allow passing a tag).
- Art map (try the lumen asset first, then the legacy asset, through `rememberFirstAsset`):

| Prayer | lumen art | legacy fallback |
|---|---|---|
| Marian | lumen/mary | mary_statue |
| Angelus, Regina Caeli | lumen/angelus | — |
| Eucharistic, Sacred Heart, crucifix, Jesus prayer, Divine Mercy | lumen/christ_thorns | divine_mercy |
| Morning | — | sunrise_mountains |
| Evening and night | lumen/night | candle_prayer |
| Holy Spirit | — | church_window_light |
| Saints and protection | — | cathedral_dome or candle_prayer |
| Default | lumen/prayers_lily | — |

  The map is final once `manifest.json` lands. Crop focus comes from `rememberHeroMeta`.

### 4. Cómo orar (step-list screen)
- `DetailHeader` with sunrise_mountains, title1 "Cómo orar".
- Callout: "Cinco pasos para una oración más profunda." (EN "Five steps to a deeper prayer.", PT "Cinco passos para uma oração mais profunda.").
- `StepList`: numbered glass circles, step title (headline) and body (callout, inkSecondary). Tags `how_to_pray_step_0..4`. Copy unchanged.

### 5. Divine Mercy chaplet (player grammar, as the Rosary)
- Stage: the divine_mercy art (`chaplet_hero`, cd kept) as a misted hero, with the glass back button (`back_from_chaplet_button`).
- Title1. Footnote "Revelada a santa Faustina en Vilna, 1935".
- Part picker: a capsule SegmentedControl Inicio / Decenas / Cierre / 15:00.
- In Decenas: "Decena 2 de 5 · Cuenta 4 de 10" + a thin ProgressBar over all 55 beads; title2 (Padre Eterno / Dolorosa Pasión); the reading text.
- A private bead rail: 11 glass beads, lead bead 1.6×, told = success tint, current = action fill.
- Glass `TransportBar`: previous bead / listen (`chaplet_tts_button`) / next bead. At the end of the decades: PrimaryCapsule "Ir a la conclusión".
- Inicio: text + PrimaryCapsule "Comenzar la 1ª decena".
- Cierre: text + an ImageCard "Novena a la Divina Misericordia" that opens Overview(`novena-a-la-divina-misericordia`). This fixes the wrong-novena bug.
- 15:00: title2 "La Hora de la Misericordia", the Diary 1320 text, the listen control.
- The Diary 687 promise becomes a quiet pull quote (reading italic, inkSecondary) under the title.
- Option for the orchestrator: send the play flow to the Rosary screen's chaplet player so there is one implementation, and keep this page as the overview.

### 6. Novenas index (the owner's 2-up grid, made beautiful)
1. Header (search circle; field `novena_search_input`).
2. **Filter chips**, scrolling horizontally edge to edge with 20dp content padding:
   - Todas · Populares · María · Jesús · Santos · [Intención ▾].
   - The kind is derived from the id slug: María is 63 novenas, Jesús 22, Santos about 148. Populares is a curated list of about 20 ids (Divina Misericordia, San Judas, San José, Sagrado Corazón, Espíritu Santo, Guadalupe, Inmaculada, Medalla Milagrosa, Desatanudos, San Miguel, San Antonio, Santa Teresita, Santa Rita, Padre Pío, Cristo Rey, Perpetuo Socorro, Carmen, Lourdes, Fátima, San Charbel, Acordaos).
   - The intention chip keeps `novena_intention_picker` and opens a glass menu (Todas las intenciones + the 8 tags, with icons). Selected, it fills and shows its label.
   - Chip tags: `novena_filter_{key}`. Kind and intention combine.
3. **En curso** (`novenas_in_progress`, headline plus a count): 2-up ImageCards with white progress bars and meta "Día 4 de 9".
4. **Completadas** (`novenas_completed`): 2-up cards with a check badge and meta "Completada". After 4 cards, a "Ver todas" row.
5. **Todas las novenas** (count "N de 246" when filtered): the lazy 2-up grid. Each chunked row is one item; the key combines both ids.
6. Empty state: glass card + "Ver todas" (`novena_clear_filters`).

**ImageCard** (`novena_card_{id}`):
- Aspect 0.8; width (gutter-to-gutter − 12)/2 ≈ 180dp; radius 22.
- The novena's own `imageUrl`, cropped with a top-centre focus, with mist. Under it, a bundled fallback chosen by kind (Mary → mary; Jesus → christ_thorns; Divine Mercy → divine_mercy; other → prayers_lily / candles). So offline and in renders the card is never a flat tile.
- Bottom scrim (black 0→55% over the lower 55%) with white text:
  - The title split into a caption prefix ("Novena a la") and the subject (headline, up to 3 lines, ellipsis). EN keeps its trailing "Novena".
  - Meta "9 días" (no bar at 0%), "Día 4 de 9" plus a thin white bar, or a check + "Completada".
- cd = full title + progress. Press scale 0.97.

### 7. Novena overview (follows the reference panel)
- `DetailHeader`: the novena art (with the bundled fallback), glass back (`back_to_novenas_button`), top right a glass share button (and a bell if the reminder hook is approved).
- title1 = full title (up to 3 lines). Meta row: calendar outline icon, "9 días", "· Comenzada el 3 nov." when started. Patron footnote only when it is not empty and not the title.
- Description (callout, ink, reading measure).
- When begun: ProgressBar + "3 de 9 días rezados" (stateDescription).
- **PrimaryCapsule**, charcoal, with a play icon (`novena_start_button`): "Comenzar novena", "Continuar · Día 4", or "Rezar de nuevo". It opens the Day reader at `nextNovenaDay`.
- **Días**: GroupedList of `CheckRow`s, "Día N" (theme only when it is not the generic "Día N"; scripture as a subtitle when present). The next day gets the trailing value "Siguiente".
  - The circle toggles prayed, like Reminders, with stateDescription (`novena_day_check_{n}`). The row opens the reader (`novena_day_{n}`).
  - Rows are lazy items, so 275-day novenas scroll smoothly.
- "Mi intención" (P2): a glass text field that edits the existing `personalIntention`. Decide during review.
- "Promesas y milagros" when present.
- Separate group: "Reiniciar novena" (`restart_novena_button`), shown only when begun. A **confirm sheet** follows ("Se borrará el progreso de 3 días"). The delete is unchanged.

### 8. Novena day reader
- A compact `DetailHeader` (art at 200dp). Eyebrow = novena title (footnote, 2 lines). title1 "Día 4". Meta "de 9" plus "· Rezado" with a success check when prayed.
- Glass circles top right: Listen/Stop (`novena_listen_button`) and Aa.
- **Day strip**: a LazyRow of 40dp numbered glass circles (prayed = success fill and check; current = action fill), auto-scrolled to the current day. `novena_day_strip`. Works for 9 to 275 days.
- Theme (title2) when meaningful, the meditation when it differs, "Tu intención" when not empty, then the prayer in `reading`, split on blank lines.
- End of text: **PrimaryCapsule "Completar día 4"** with a check (`complete_novena_day_button`). Once prayed it becomes SecondaryCapsule "Día 4 rezado · Deshacer" with a success check.
- **The reader does not jump to the next day** (behaviour change). The overview call to action becomes "Continuar · Día 5". A share icon sits next to the capsule.
- The write path (toggle, whole-set save, startDate, isCompleted) is unchanged.

### 9. Night: a calm nocturne
**Mood**
- Night art (lumen/night, falling back to candle_prayer) misted into the canvas. Slower motion (300ms fades, no springs). Reading leading at its loosest. More space: 40dp between parts.
- No ornaments, no accent text. Sigla in inkSecondary. Latin in reading italic, inkSecondary.
- Dark mode is naturally a night chapel. Optional for the orchestrator: a scoped night-chapel palette for the three readers even when the system theme is light.

**Night overview**
- `night_office_picker` is on the column of choices.
- A full-width feature ImageCard, 16:10, art night: eyebrow "Liturgia de las Horas", title "Completas", meta "8 partes · Salve Regina" (the seasonal antiphon). Tag `night_office_compline`; `screen_header_illustration` moves to this art.
- Two glass ContentCards side by side: "Examen ignaciano" (moon icon, "5 pasos", progress "2 de 5" when started; `night_office_examen`) and "Oración tradicional" (bed/candle icon, "9 pasos"; `night_office_traditional`).

**Compline reader**
- `DetailHeader` (night art): eyebrow "Oración de la noche", title1 "Completas", meta "Liturgia oficial de la Iglesia antes del reposo de la noche".
- SecondaryCapsule with headphones, "Escuchar el oficio" ↔ "Detener" (`compline_tts_button`, stateDescription kept). The footnote "Narrando el oficio…" stays.
- TTS now reads all 8 parts in order (still under 4000 characters).
- Parts 1–8: a numbered glass circle, the part title (headline), the reference footnote, and a trailing quiet "Latín" capsule that toggles (state per part, cd + stateDescription kept). The text is in `reading`.
- Psalm stichs and Nunc Dimittis 29–32 keep verse numbers (a private small-number gutter, inkTertiary). Then the doxology.
- Part 8: FilterChips (`marian_antiphon_picker`), **preselected by season** (Regina Cæli in Eastertide, Salve Regina otherwise). A glass card with the title in title2, Latin (italic, inkSecondary), the vernacular, and SecondaryCapsule "Escuchar Salve Regina".

**Examen and Traditional (guided player, the same grammar as the Rosary)**
- DetailHeader without an image (LargeTitle style), eyebrow, title1.
- Examen: intro callout. Traditional: a short intro "Nueve oraciones sencillas antes de dormir." in place of the 9-item sentence.
- One thin ProgressBar with "2 de 5 pasos" (cd + stateDescription kept). The metric tiles go.
- A stage card: eyebrow "Paso 2 de 5", title2 (the data's "1." prefix stripped at render time), the description (callout, inkSecondary, Examen), "Oración sugerida", and the reading text. Traditional shows the guidance as a footnote.
- Glass `TransportBar`: previous step / listen (`examen_tts_button` / `tradicional_tts_button`, stateDescription kept) / next step.
- Below it, a check capsule "Marcar como hecho" ↔ "Hecho" (success check; tap again to undo). Done moves the selection to the next step not yet done.
- A `StepList` of all steps with done and current states ("Toca un paso" jump; stateDescription Hecho / Pendiente).
- Completion: a glass card with a success check, title2 "Examen completado" / "Oración completada", and the blessing in reading italic, centred.

### Dark mode
- Canvas #171614→#0E0D0C. Art fades into the dark canvas. Glass at 7% with a 14% edge. Capsules ivory with dark text.
- ImageCard scrims are unchanged. Season dots keep their hue. Nothing glows: no accent text, no glowing progress.

### ES / EN / PT length risks
| Element | Risk and fix |
|---|---|
| Segmented | "Night prayer" shortened to "Night" |
| Tiles (115dp) | "Intenciones" and "Forgiveness" fit at 13sp; 2 lines allowed at 1.3× font scale |
| Novena titles | median 38, max 79 characters, 90 over 40: prefix split + 3 lines + ellipsis, full title in cd and in the detail |
| Chaplet title | "Coronilla de la Divina Misericordia" in title1 wraps to 2 lines (fine) |
| Capsules | "Continuar · Día 275" and "Day 4 prayed · Undo" are within the 52dp capsule at 1.3× |
| Compline captions | "Cántico evangélico (Nunc Dimittis)" wraps under a 32dp number circle |
| Chips | scroll horizontally, so no truncation |
| Card meta | "Comenzada el" date moves off the card into the detail meta |

### Behaviour changes (need the orchestrator's OK)
1. Category tiles push a page instead of filtering in place.
2. The novena reader no longer auto-advances.
3. Reset asks for confirmation.
4. Compline TTS reads all parts.
5. The antiphon is preselected by season.
6. Chaplet → novena opens the right novena (bug fix).
7. A deep link into the night office opens its reader.
8. State moves to `rememberSaveable`.

---

## d. Component needs

**From the foundation owner** (in `LumenComponents` / `AppComponents`; most of these exist and need small extensions):
1. **Back override on tab roots.** `AppBackHandler` must show the top-left glass back button on a tab root while an in-screen detail is open, and accept a `testTag` so `back_to_prayers_button`, `back_to_novenas_button` and `back_from_chaplet_button` survive.
2. **AppImageCard**: `fallbackAssetPath` under `imageUrl`, image focus/alignment, `eyebrow` above the title, `titleMaxLines`, a done badge, `contentDescription`, and a white progress bar on the scrim.
3. **AppDetailHeader**: `imageUrl` + `fallbackAssetPath`, title up to 3 lines, `meta` with an icon (exists), and a compact height.
4. **Grouped rows across lazy items**: `AppGroupedRow(position = First | Middle | Last | Single)`, or `LazyListScope.appGroup {}`, with inset hairlines. Needed for 275-day lists and search results. Plus `AppCheckRow` working inside it.
5. **AppStepList v2**: items with title, body, done and current (the current signature takes `List<String>` only).
6. **AppFilterChips**: horizontal scroll, `tagFor`, and a **menu chip** (trailing ▾) with a glass `AppMenu`.
7. **AppSearchField (Lumen)**: clear button, `focusRequester`, testTag passthrough; and an expandable header search pattern.
8. **Lumen SegmentedControl** behind `AppSegmentedTabs`.
9. **AppActionBar**: a container for `AppActionBarItem` (share / listen / Aa).
10. **Liturgical accent tokens**: `palette.liturgical(season | color)` with the DESIGN hexes, for the dots.
11. **Scroll-aware glass top bar** with an inline title.
12. **AppConfirmDialog**, a glass sheet (for reset).
13. Optional: a **night-chapel palette scope** for the night readers.

**Wiring in MainApp (foundation owner)**
- `onOpenTextSettings: () -> Unit = {}` on `PrayersAndNovenasScreen`, which opens `FontPickerDialog` (the Aa button).
- Optional: `onOpenChapletPlayer` (Rosary tab 1) if the chaplet is consolidated.

**Other owners**
- **TTS manager**: chunk text over 4000 characters (257 novena days today). Fix `speakDialogue`: the leader's `onDone` clears `currentlySpeaking` while the response is playing.
- **Data / Room**: `NovenaProgressEntity.currentDay` holds days 1–31 only. Novenas of 54 to 275 days need a migration (for example a `daysPrayed: String` column).
- **Test infra**: permission for me to add `app/src/test/.../screenshots/PrayersScreenshotTest.kt`, which renders Novenas, Novena overview and day, Prayer detail, Chaplet, How to pray and the 3 night readers in light and dark. `FullAppScreenshotTest` renders only the landing.
- **Art**: lumen assets prayers_lily, mary, christ_thorns, angelus and night with manifest focus points. Note: the novena `imageUrl`s are remote images from catholicnovenaapp.com, not PD-cleared bundled art.

**Private to my files**
- Title splitter, novena kind classifier, popular list, art map.
- Day strip; chaplet bead rail (rebuilt from `DevotionRail`).
- Verse-number gutter; reading paragraphs with the speaking highlight; share intent.
- Season-antiphon picker; route stacks.
- The kept internal sigla helpers.
