# Life area plan: Vida espiritual · Parroquias · Calendario y Santuarios

Owner: inner-life / parishes / pilgrimage builder. Status: **step 1 (audit + plan)**,
no code touched yet. Read with `DESIGN.md` and `PROCESS.md`.

Files I own:

- `app/src/main/java/com/example/ui/screens/SpiritualLifeScreen.kt` (1477 lines)
- `app/src/main/java/com/example/ui/screens/ParishFinderScreen.kt` (846 lines)
- `app/src/main/java/com/example/ui/screens/PilgrimageAndHabitsScreen.kt` (900 lines)

Art (being added): `assets/heroes/lumen/{spiritual,parishes,pilgrimage}.jpg`.
Until they land, fall back to the current `heroes/sunrise_mountains.jpg`,
`heroes/church_window_light.jpg` and `heroes/votive_candles.jpg`.

---

## 0. Decisions in one screen

| Screen | Today | Lumen |
|---|---|---|
| Vida espiritual | Photo band, 3-way segmented control, and a second picker inside "Learn" (4 activities behind 2 controls). | A **hub**: DetailHeader, then a 3-tile **Practices** row (Way of the Cross · Faith quiz · Gregorian chant), then the **Journal** as a calm card feed with a floating "New entry" capsule. Each practice is a pushed sub-page inside the screen: Way of the Cross = rosary-grade player with a TransportBar; Quiz = one question per screen; Chant = cards with play buttons and expandable lyrics. |
| Parroquias | Search field, a CTA whose label and job change while you type, a radius section, then a status card or list. | An Apple Maps-style search: DetailHeader, a glass **SearchField** that submits from the keyboard, one fixed **"Parishes near me"** capsule, inline radius chips. Results: a **map preview** with numbered pins, then numbered parish rows that expand to Directions / Call / Mass times. Designed idle, loading, empty, failure, permission-denied and no-fix states. |
| Calendario y Santuarios | Two tabs of heavy cards. The shrine cards are walls of text, each with its own full-width map button, and a second photo under the hero. | Segmented **Calendar / Shrines**. Calendar opens on a **Today card** (season accent, celebration, precept, Gospel), then a light grouped list of upcoming days with weekday + day numerals and liturgical-colour dots. Shrines: a clean list that pushes a **shrine detail** reading page. The detail adds the two fields the app has always had but never shown (spiritual significance, pilgrim advice) and ends in one "View on map" capsule. |

---

## 1. Inventory: the "nothing lost" checklist

### 1.1 Navigation into my screens (`ui/MainApp.kt`)

| Destination | Reached from | Params | Back |
|---|---|---|---|
| `SPIRITUAL_LIFE` ("Vida espiritual" / "Spiritual Life" / "Vida espiritual") | More, section "Vida interior", row tag `nav_spiritual` (icon `Outlined.AutoStories`), `initialTab = 0`. Home `onNavigateToSpiritualLife` → `initialTab = 0`. Home tile "Trivia Católica" (`onNavigateToQuiz`) → `initialTab = 1` | `repository, dao, ttsManager, currentLanguage, initialTab` | Scaffold back to origin (More or Home), system back via `MainApp.BackHandler` |
| `PARISHES` ("Parroquias") | More, section "Lugares", tag `nav_parishes` (AppNavigationTest clicks it and asserts `parish_finder_screen`, then back to More) | `repository, currentLanguage, onOpenDirectory = go(CHURCHES_NEWS)` | as above |
| `PILGRIMAGE_HABITS` ("Calendario y Santuarios") | More, "Lugares", tag `nav_pilgrimage`. Home tile "Santuarios y Fe" (`onNavigateToPilgrimage`) | `repository, currentLanguage` | as above |

`initialTab` semantics in `SpiritualLifeScreen` must keep working: 0 journal,
1 quiz, 2 Way of the Cross, 3 chant. `AppScreenshotTest` calls
`ParishFinderScreen(repository = repository, currentLanguage = AppLanguage.EN)`,
so every new parameter needs a default.

### 1.2 Vida espiritual (`SpiritualLifeScreen.kt`)

**Data sources**

- Journal: Room `dao.getAllJournalEntries()` (Flow, `ORDER BY timestamp DESC`).
  Entity `JournalEntryEntity(id, title, category, content, timestamp, isAnswered, answeredNotes)`.
  Writes: `insertJournalEntry`, `updateJournalEntry(copy(isAnswered = !isAnswered))`, `deleteJournalEntry`.
  Category keys are stored in Spanish ("Intención", "Agradecimiento", "Examen", "Gracia") and labels are translated; unknown keys pass through (`journalCategoryLabel`). **Must keep.**
- Quiz: `repository.triviaQuestions` (about 104 `TriviaQuestion`s: category, question, options, correctIndex, doctrinalExplanation).
- Way of the Cross: `repository.viaCrucisStations`. **Only 3 stations are bundled** (data gap, §4.4). Each has stationNumber, title, scripture, meditation and prayer.
- Chant: `repository.gregorianChants`, 2 items (Salve Regina, Tantum Ergo): title (not localized), season, description, latinLyrics, translatedLyrics.
- Audio: `ttsManager.isPlaying` (StateFlow), `speakDialogue(meditation, prayer)`, `playSacredChime()`, `speakSingle(latinLyrics)`, `stop()`.

**State.** Everything is in-memory `remember`: selected tab, add-dialog visibility,
draft title/category/content, quiz index/selection/score/answered, station
index, selected chant. Nothing is persisted except the journal rows.

**Elements, actions, tags, a11y**

| Element | Action | testTag / semantics |
|---|---|---|
| Root column | n/a | `spiritual_life_screen` |
| PhotoHeader "Vida espiritual" (220dp photo, `sunrise_mountains.jpg`) | n/a | image `screen_header_illustration` |
| Segmented: Journal / Stations / Learn | switch tab | none |
| Learn picker (2 rows: Faith quiz, Gregorian chant; icons Quiz, MusicNote) | pick 1 or 3 | `spiritual_life_learn_picker` |
| Journal empty: EmptyState ("Tu diario está vacío" + body, EditNote) + primary "Escribir la primera entrada" | open the composer | `add_first_journal_entry` |
| "Para empezar": 3 prompt rows (A grace received today / An intention to pray for / Something to give thanks for; icons AutoAwesome, VolunteerActivism, Favorite) | open the composer with the prompt as its title | none |
| "Entradas" header + subtitle "N escritas · M concedidas" | n/a | n/a |
| Entry: row (title; subtitle = category label, or "✓ Gracia concedida" when answered; selected fill if answered), content in quote style at reading measure, secondary "Marcar como Gracia Concedida ✓" / "Desmarcar concedida", 48dp red trash | toggle answered; **delete immediately, no confirm** | trash `contentDescription` "Eliminar entrada" / "Delete entry" / "Excluir entrada" |
| Floating primary "Nueva entrada" (only when there are entries) | open the composer | `add_journal_entry_fab` |
| Composer dialog: title "Nueva Intención / Reflexión", Title field, Category chips ×4 (FlowRow), "Tu escrito" field (min 6 lines), Cancel / "Guardar en el Diario" (enabled only when the title is non-blank) | save inserts and clears title/content (category kept) | none |
| Quiz: metric tiles "n/N Pregunta", "score Puntos"; progress bar | n/a | `trivia_progress` + stateDescription "Pregunta n de N" |
| Quiz: category header, question card, options group (✓/✗ trailing text, selected fill) | answer once (+10 if correct) | per-option stateDescription Selected / Correct answer / Incorrect answer |
| Quiz: verdict card ("¡Correcto! ✦" in gold, or "Doctrina Católica:") + explanation; primary "Siguiente Pregunta →" | next question (wraps around) | `next_trivia_question_button` |
| Quiz empty | n/a | EmptyState "Sin preguntas" (Lightbulb) |
| Stations: header "Estación n de N", 14-dot progress (`StationProgress`), card with title, scripture, divider, meditation, divider, prayer (accent colour) | n/a | progress stateDescription |
| Stations: "← Anterior", "Siguiente →", primary "Rezar estación" / "Detener" (play/stop icon) | prev/next (disabled at ends), narrate/stop | `via_crucis_previous_button`, `via_crucis_next_button`, `via_crucis_narrate_button` |
| Stations empty | n/a | EmptyState "Sin estaciones" |
| Chant: header, one card per chant (icon, title, season eyebrow, description, divider, "Letra en Latín:", Latin, "Traducción:", translation, secondary "Escuchar" / "Detener") | select (tap card), toggle playback (plays the chime, then speaks the Latin) | none |
| Chant empty | n/a | EmptyState "Sin cantos" (MusicNote) |

### 1.3 Parroquias (`ParishFinderScreen.kt`)

**Data sources.** `ParishFinder()` (OkHttp): `geocode(query)` via Nominatim →
`GeocodedPlace(displayName, lat, lon)`, and `findNearby(lat, lon, radiusKm)`
via 3 Overpass mirrors raced → `ParishLookupResult.Success(List<NearbyParish>) | Empty(radius) | Failure(reason)`,
with a 20 s bound inside and a 22 s UI bound (`UI_TIMEOUT_MS`). `NearbyParish`
gives: id, name, diocese, address, city, lat/lon, distanceDisplay, sundayMasses
(OSM `service_times`, often blank; never invented), phone. Mass times are shown
through `ParishScheduleText.masses` + `TextFormat.times` (reader's clock).
`DeviceLocationProvider`: `hasPermission()`, `lastKnownLocation()` (last fix only, no tracking), `PERMISSIONS` = COARSE + FINE.

**Permissions.** `RequestMultiplePermissions(COARSE, FINE)`, launched on
"near me" when not granted. Any grant → proceed. Denied → message "Permiso de
ubicación denegado. Puedes buscar un lugar por nombre." Manifest: INTERNET,
COARSE, FINE.

**State (in-memory).** placeQuery, radiusKm (5/10/25, default 10),
SearchState IDLE/LOADING/DONE, results, message, searchedPlace,
expandedParishId, lastLatitude/Longitude/RadiusKm, showOsmMap.

**Behaviours that must survive**

- Near me: last fix → lookup labelled "Tu ubicación". No fix → message + **auto-open Google Maps** (`geo:0,0?q=…`, then the web URL).
- Search place: geocode → lookup labelled with the display name. Geocode fails → open Maps with "parroquia católica <query>".
- Lookup Empty/Failure/timeout → message + **auto-open Google Maps at the coordinates** (owner decision: "hand over to Google Maps automatically instead of only offering it").
- The spinner always ends (the try/catch + CancellationException path).
- Call: `ACTION_DIAL tel:` → `ACTION_VIEW tel:` → message. Directions: `geo:lat,lon?q=name` → web search → message.
- OSM embed URL framing all results + radius, marker on the nearest, shown in `InAppBrowserDialog`.
- "Ver más en Google Maps" (near searchedPlace). "Directorio de iglesias" → `onOpenDirectory` (Churches & News).

**Elements and tags**

| Element | testTag / semantics |
|---|---|
| Root LazyColumn | `parish_finder_screen` |
| PhotoHeader "Parroquias" (`church_window_light.jpg`) | `screen_header_illustration` |
| Search field "Buscar una ciudad o un lugar" | `parish_place_field` |
| Primary "Buscar «q»" (only while the field has text) | `parish_search_place_button` |
| Primary "Parroquias cerca de mí" (only while the field is empty) | `gps_nearby_maps_button` |
| "Distancia" header + chips 5/10/25 km | `parish_radius_selector` |
| Spinner + "Buscando…" | `parish_loading`, contentDescription "Buscando…" |
| Message card + secondary "Abrir Google Maps" | `use_my_location_button` (sic) |
| EmptyState idle ("Encuentra parroquias cerca de ti") / done-empty ("Sin resultados"), icon Church | none |
| Row "Directorio de iglesias" (only when there are no results) | `parish_open_directory` |
| Results header "Cerca de ti" + "place · N parroquias" | none |
| Secondary "Ver en el mapa (OpenStreetMap)" | `parish_osm_map_button` |
| Group of parish rows | `parish_list` |
| Parish row: name; street (or city); "Misa del domingo · times"; trailing distance; tap expands | `parish_row_<id>`, stateDescription Expanded/Collapsed |
| Expanded: "Llamar" + phone (if any), "Cómo llegar" + address | `parish_call_<id>`, `parish_directions_<id>` |
| Row "Ver más en Google Maps" | none |
| OSM dialog | `InAppBrowserDialog` |

### 1.4 Calendario y Santuarios (`PilgrimageAndHabitsScreen.kt`)

**Data.** `repository.liturgicalCalendarDays`: computed each read; today plus,
over the next 28 days, Sundays, holy days, fast/abstinence days (non-Friday),
solemnities, feasts, commemorations. Today is always the first element.
`LiturgicalCalendarDay` fields: dayOfMonth, monthName (localized),
liturgicalColorHex, seasonName, celebrationTitle, rank, fastingRule?,
gospelVerseRef, sundayCycle?, weekdayCycle?, isFastOrAbstinence,
missalMeditation?. **Shown today:** day, rank, title, "d de mes", precept,
"Evangelio · ref". **Never shown:** season, cycles, weekday (there is no date
field, §4.4). `repository.globalPilgrimages`: 5 shrines (rome_vatican,
holy_sepulchre, fatima_sanctuary, lourdes_sanctuary, santiago_compostela) with name,
cityCountry, historicalOrigin, relicsAndPatron, plenaryIndulgenceNote,
coordinateLatLong. **Never shown:** `spiritualSignificance`,
`practicalPilgrimAdvice`. `repository.spiritualHabitsList` is read only by dead code.

**State.** Selected tab (in-memory, always starts on Calendar).
`habitCompletionMap` is built but never rendered.

| Element | Action | testTag / semantics |
|---|---|---|
| Root Box | n/a | `pilgrimage_and_habits_screen` |
| PhotoHeader "Calendario y Santuarios" (`votive_candles.jpg`) | n/a | `screen_header_illustration` |
| Segmented Calendario / Santuarios | switch | none |
| Calendar empty | n/a | EmptyState "Sin celebraciones" (CalendarMonth) |
| "Próximas celebraciones" + one card per day: tinted date badge, colour dot, CAPS rank, title, "d de mes", precept, "Evangelio · ref" | none | `calendar_day_<monthName>_<day>` (monthName is localized) |
| Shrines header + subtitle "Lugares santos, su historia y la indulgencia plenaria que ofrecen." | n/a | n/a |
| Candle plate photo (`candle_prayer.jpg`, 132dp) | n/a | `pilgrimage_shrine_plate`, contentDescription "Velas de oración" / "Prayer candles" / "Velas de oração" |
| Shrine card: Place icon, name, locality, region (trailing), history, "Reliquias", "Indulgencia Plenaria y Gracia", divider, secondary "Ver ubicación en Mapa" | `geo:` intent; on failure a Toast with cityCountry | `shrine_card_<id>`, `shrine_map_button_<id>` |
| Shrines empty | n/a | EmptyState "Sin santuarios" (Place) |
| **Dead code:** `HolyHabitsTab`, `HabitEntry` (never called) | n/a | `habit_progress_tile`, `habit_card_<id>` (unreachable) |

Tests touching my area: `AppNavigationTest` (only `nav_parishes` →
`parish_finder_screen` → back to More, plus `screen_header_illustration` on
Bible under IVE), `AppScreenshotTest.parishes*`, `FullAppScreenshotTest`
(destinations `spiritual_life`, `parishes`, `pilgrimage_habits`),
`ParishFinderTest` and `ParishScheduleTextTest` (data only; untouched by UI
work), `LiturgicalCalendarTest` (data only). No test references the
spiritual/pilgrimage tags, but I keep all of them anyway.

---

## 2. What is wrong today

### Shared (all three)

1. **Three private copies** of `rememberHeroBitmap` + `PhotoHeader` (+ dead `IveGutter`) in my three files (Spiritual 1346–1477, Parish 715–846, Pilgrimage 769–900). DESIGN.md asks for one shared header.
2. The header is a 220dp band **below** the scaffold's top bar ("‹ More" in tint). It is neither full-bleed from the top nor a DetailHeader with a glass back button. The title is `screenTitle` with `maxLines = 1` and no overflow, so "Calendário e Santuários" can clip on narrow phones.
3. Hard-coded sizes everywhere (`48.dp` floors, `9/5/8.dp` dots, `132.dp` plate, `144.dp` field, `560/640.dp` dialog, `6/10.dp` spacers, `24.dp` title padding). Colours that bypass tokens: `MaterialTheme.colorScheme.error` on delete, `Color(day.liturgicalColorHex)` raw data colour instead of the DESIGN liturgical accents, `palette.gold` / `palette.accent` text for verdicts and prayers. That makes multicolour text, which Principle 3 forbids.
4. Glyphs used as UI in strings: "✓ Gracia concedida", "Marcar como Gracia Concedida ✓", "¡Correcto! ✦", trailing "✓"/"✗", "← Anterior", "Siguiente →", "Siguiente Pregunta →". Title Case strings: "Guardar en el Diario", "Nueva Intención / Reflexión", "Ver ubicación en Mapa", "Indulgencia Plenaria y Gracia". `caps()` all-caps eyebrows.
5. Old renders (`app/build/screenshots/app/*_apple_*.png`) show green filled buttons, saturated icon wells (blue/red/orange prompt icons, blue globe), a pure-black dark mode and bold titles. Every one of those breaks DESIGN.md.

### Vida espiritual

6. **Two stacked selectors for four activities**: segmented Journal / Stations / Learn, then a second list-picker inside Learn for Quiz vs Chant. The index juggling (`1,3 -> 2`) is a smell. Nothing above the fold says what the screen is for.
7. The journal **flashes "Tu diario está vacío"** on open because the Flow starts at `initial = emptyList()`.
8. **Delete is a one-tap red trash with no confirmation and no undo.** The journal is the most personal data in the app.
9. Entry layout is three disconnected pieces (list row, loose quote text, button row) separated by `AppOrnament`, so it does not read as one entry. `timestamp` is never shown. Answered state is shown with the selected fill plus a "✓" in text.
10. The composer is a generic tonal dialog: boxed fields with borders, "Tu escrito" in a bordered box, a title that mixes "Intención / Reflexión", category chips that wrap to two rows. A prompt ("Algo por lo que dar gracias") fills the title but leaves the category on "Intención".
11. Quiz: two metric tiles plus a bar say the same progress twice. The question sits in a card in quote style. The verdict uses gold/accent text with ✦. Nothing scrolls the explanation into view after answering.
12. Way of the Cross: the station title repeats its ordinal ("I Estación: …") under a header that already says "Estación 1 de 3". The 14-dot rail (`StationProgress`) is drawn for **3** stations. The page is one long card with dividers. Prev/next/narrate are three stacked buttons. There is no art, so it is far from the "never empty-looking" rosary bar. You cannot swipe between stations.
13. Global `isPlaying` drives both the station and the chant buttons. Start a chant, open the Way of the Cross, and its button already says "Detener".
14. Chant: both cards always show their full Latin + translation (a wall of text), plus a full-width "Escuchar" secondary per card. Titles carry a Spanish suffix "(Canto Gregoriano Simple)" in EN/PT.

### Parroquias

15. **The primary CTA changes identity while you type** ("Parroquias cerca de mí" ⇄ "Buscar «…»"). "Near me" disappears as soon as there is text. The field has no keyboard Search action.
16. Radius is a full section ("Distancia" header + chips) above the content. Changing the radius after a search does nothing until you search again, even though the empty message says "Prueba un radio mayor".
17. Status messages are one long sentence in a generic card ("No se encontraron parroquias en 10 km. Prueba un radio mayor. Se abrió Google Maps…"), followed by a button tagged `use_my_location_button` that opens Maps. No designed states for denied (and permanently denied, where the system dialog no longer appears and the user is stuck), location off / no fix, offline, or Maps missing.
18. Loading is a 22dp spinner row. The list area jumps from nothing to everything.
19. Results: no map on screen, only a secondary "Ver en el mapa (OpenStreetMap)" button. Each row uses a two-line subtitle joined with `\n`. Expansion is shown with the "selected" fill. Call/Directions appear as extra list rows inside the group, which reads as more parishes. There is no honest "Mass times not published" note when OSM has none.
20. The OSM map opens in the shared `InAppBrowserDialog`, which has Spanish-only chrome ("Vista en la app", "Cerrar"), bold M3 title type, `textZoom = 150` and injected body padding/font CSS meant for articles. That CSS distorts the map embed.
21. `openMapsAt` hard-codes a Spanish query ("iglesia católica parroquia cerca de …") in every language.
22. The directory link shows only when there are no results.

### Calendario y Santuarios

23. Home's "Santuarios y Fe" tile lands on the **Calendar** tab (there is no `initialTab`).
24. Today is not distinguished from the rest of the list. There are no weekdays. The season is never named. Each day is a heavy card with a flat tinted date box (against "no flat grey boxes"). "Weekday/Feria" rank eyebrows add noise. Nothing is grouped by month.
25. The liturgical colour is the raw data hex (`0xFF2C6B3E`…), not the DESIGN accents, and has no text alternative.
26. Shrines: a **second photo** (candle plate) under the hero, then five dense cards, each with history + relics + indulgence + a full-width map button (5 identical buttons). Locality/region are split oddly ("Ciudad del Vaticano / Roma" | "Italia"). `spiritualSignificance` and `practicalPilgrimAdvice` have never been shown. A Toast is the only fallback.
27. ~170 lines of dead habit UI and an in-memory map built on every composition.

---

## 3. The new design

Common grammar for all three:

- **Canvas** gradient, edge to edge. **DetailHeader** from the very top (under the status bar): image height **248dp** (mist: light = lift + slight desaturation + fade to canvas over the lower 55%; dark = fade to `#0E0D0C`). Glass back circle 40dp (44 hit) top-left at status-bar + 8dp, gutter 20. Title `title1` 28/34 bottom-start at the gutter, sitting on the fade (≈ y 236). Optional `footnote` meta under it. Content starts 20dp below the title. The image keeps `testTag("screen_header_illustration")`, decorative (`contentDescription = null`).
- **TopBar on scroll**: once the title scrolls under the status bar, a blurred glass bar fades in with the inline 17sp title. The glass back button stays put.
- Gutter 20, section gap 28, card padding 20, rows ≥ 52dp (most ≥ 56), radii from tokens only. Icons: outline, 22–24dp, ink.
- Section headers: `headline` 17/22 600, sentence case, 28dp above and 12dp below.
- Long text uses the **reading** style (reader font) at the reading measure.
- Primary action: charcoal capsule 52dp (ivory in dark). Never green, never red.
- Bottom spacing clears the floating tab bar (foundation inset token, §4.1). Floating capsules and the TransportBar sit 12dp above it, on a 24dp canvas fade.

### 3.1 Vida espiritual: Hub (initialTab 0)

Top to bottom (viewport 411×891):

1. **DetailHeader**: `heroes/lumen/spiritual.jpg` (fallback `sunrise_mountains.jpg`). Title "Vida espiritual" / "Spiritual life" / "Vida espiritual" (matches the More row). No actions.
2. **Practices**: `CategoryTile` row, 3 columns, gap 12, tile ≈ 105×96dp. Glass, 26dp outline icon, `caption` label up to 2 lines, centred. Tag `spiritual_life_learn_picker` on the row container (the old picker's job, now the only picker).
   - Way of the Cross: thin Latin cross glyph. "Vía Crucis" / "Stations of the Cross" / "Via-Sacra". Opens §3.2.
   - Faith quiz: `Outlined.Quiz`. "Preguntas de fe" / "Faith quiz" / "Quiz da fé". Opens §3.3.
   - Gregorian chant: `Outlined.MusicNote`. "Canto gregoriano" / "Gregorian chant" / "Canto gregoriano". Opens §3.4.
3. **Journal header**: `headline` "Diario" / "Journal" / "Diário". `footnote` meta below it, "12 entradas · 3 gracias concedidas" (the existing count line, reworded; only when there are entries).
4. **Loading** (Flow not yet emitted, `initial = null`): two skeleton glass cards (static, no shimmer), so no empty-state flash.
5. **Empty**: one glass card (radius 24, padding 20): 26dp `Outlined.EditNote`, `headline` "Tu diario está vacío", `subhead` inkSecondary body (existing copy). Then the 3 prompts as rows inside the card, separated by inset hairlines: leading outline icons (`Outlined.AutoAwesome`, `Outlined.VolunteerActivism`, `Outlined.FavoriteBorder`, all ink), body label, chevron. Each opens the composer with the prompt as title **and the matching category** (grace → Gracia, intention → Intención, thanks → Agradecimiento). Last, a PrimaryCapsule "Escribir la primera entrada" (`add_first_journal_entry`), full card width.
6. **Entries**: optional `FilterChips`, only when there are ≥ 6 entries: Todas · Intenciones · Agradecimientos · Exámenes · Gracias · Concedidas (horizontal scroll, gutter-aligned). Then **JournalEntryCard** per entry, gap 12:
   - Glass card, radius 24, padding 20.
   - Meta row (`footnote`, inkSecondary): category label · relative date ("Hoy", "Ayer", "3 de noviembre"; locale-formatted from `timestamp`).
   - Title `headline`.
   - Content in **reading** style, clamped to 6 lines. Tap the card to expand or collapse (stateDescription "Expandida"/"Contraída"); nothing is lost.
   - Footer row: a **check capsule** on the left (24dp circle, success fill + white check when granted, ink outline when not), label "Gracia concedida" / "Marcar como concedida" (`subhead`, ≥ 44dp target, `Role.Checkbox`, stateDescription). On the right a 40dp glass circle **trash** (ink), `contentDescription` "Eliminar entrada" / "Delete entry" / "Excluir entrada". It opens a confirm alert: "¿Eliminar esta entrada?" with body "No se puede deshacer." and actions Cancelar / Eliminar.
7. **Floating "Nueva entrada"** PrimaryCapsule (`add_journal_entry_fab`), centred, `+` glyph, above the tab bar, only when there are entries (as today). The list gets enough bottom padding to clear it.

**Composer** (replaces `AddJournalEntryDialog`): a full-height **sheet** (radius 28 top, glass-strong over a dimmed canvas, `imePadding`).

- Top row: text button "Cancelar" (left), `headline` "Nueva entrada" / "New entry" / "Nova entrada" (centre), text button "Guardar" (right, disabled until the title is non-blank).
- Category chips in a single scrolling row.
- Title field: borderless, `title2` 22/28, placeholder "Título" in inkTertiary.
- Hairline.
- Body field: borderless, **reading** style, placeholder "Escribe tus palabras al Señor…", fills the sheet.

Behaviour is unchanged: save inserts and clears; cancel keeps the draft.
`JournalField` is replaced by these borderless fields.

**Dark mode.** Glass at 7% on the night canvas, ink `#F3F0EB`, success check
stays `#43A047`. The image fades into `#0E0D0C`; no glow.

### 3.2 Way of the Cross page (initialTab 2, or a tile tap)

The "rosary-grade" player.

1. **DetailHeader as stage**: `heroes/lumen/via_crucis.jpg` if supplied (§4.3), else `spiritual.jpg`. Height 300dp. Back circle. Eyebrow "Vía Crucis". Title `title1` = the station title **without** its ordinal prefix ("Jesús es condenado a muerte"), split on the first ": " with a full-title fallback.
2. `footnote` "Estación I de III" (roman numeral via `TextFormat.roman`, number in the stateDescription) plus a thin **ProgressBar** (2dp, ink on separator) with stateDescription "Estación 1 de 3". This replaces the 14-dot rail.
3. Content in a **HorizontalPager**, one page per station (swipe), at the reading measure:
   - Scripture in reading style, *italic*, inkSecondary.
   - Meditation in reading style, ink.
   - Glass card: eyebrow "Oración" + prayer in reading style, ink. No accent-coloured text.
4. **TransportBar**, pinned above the tab bar:
   - previous: `via_crucis_previous_button`, cd "Estación anterior", disabled at the first station;
   - play/pause, larger, glass-strong: `via_crucis_narrate_button`, cd "Rezar estación" / "Detener", stateDescription;
   - next: `via_crucis_next_button`, cd "Estación siguiente", disabled at the last station.

   Prev/next animate the pager.
5. Playback state is **scoped**: the button shows "Detener" only if this page started the narration (local `playingSource`). Pressing play while a chant plays stops it, then narrates.
6. Back: to the hub, or straight out to the origin when the page was deep-linked (§3.6).

### 3.3 Faith quiz page (initialTab 1, Home "Trivia Católica")

1. **LargeTitleHeader** with a back circle: `title1` "Preguntas de fe" / "Faith quiz" / "Quiz da fé". Trailing glass capsule with the score, "30 puntos" (`caption`, stateDescription).
2. `footnote` "Pregunta 3 de 104 · Sagrada Escritura" + thin ProgressBar (`trivia_progress`, stateDescription "Pregunta 3 de 104").
3. Question text in **title2** 22/28, ink, at the reading measure. No card.
4. Options in a glass group, rows ≥ 56dp (text wraps, never truncated), leading 24dp circle:
   - unanswered: an empty ink-tertiary ring; tap answers;
   - answered: the correct row shows a filled success circle with a check; a wrong chosen row shows an ink circle with an x-mark; other rows' text goes to inkSecondary.

   The per-row stateDescriptions are kept exactly. No ✓/✗ text.
5. After answering:
   - ContentCard: leading `Outlined.CheckCircle` (success tint) or `Outlined.MenuBook` (ink), headline "Correcto" / "Doctrina católica", explanation in reading style.
   - PrimaryCapsule "Siguiente pregunta" (`next_trivia_question_button`), full width.
   - The list auto-scrolls (`animateScrollToItem`) so both are visible.
6. Empty: Lumen EmptyState "Sin preguntas" (outline Lightbulb).
7. Quiz index and score use `rememberSaveable` (they survive rotation).

### 3.4 Gregorian chant page (initialTab 3)

1. DetailHeader: `heroes/lumen/chant.jpg` if supplied (a PD manuscript page), else `spiritual.jpg`. Title "Canto gregoriano". Meta "2 cantos".
2. One **ContentCard** per chant:
   - leading `Outlined.MusicNote`;
   - title `headline` (display trims a trailing parenthetical such as "(Canto Gregoriano Simple)"; the rest of the title is unchanged);
   - season `footnote`;
   - description `subhead` inkSecondary;
   - trailing **round glass play button** 44dp (cd "Escuchar Salve Regina" / "Detener"), scoped playback as in §3.2.
3. Tap the card to select it and expand the lyrics (the old `onSelect`): eyebrow "Latín", Latin in reading style; eyebrow "Traducción", translation in reading style, inkSecondary. The first chant starts expanded (as today, `selectedChant` defaults to first). Chevron rotates; stateDescription Expanded/Collapsed.
4. Empty: EmptyState "Sin cantos".

### 3.5 Parroquias

Top to bottom:

1. **DetailHeader**: `heroes/lumen/parishes.jpg` (fallback `church_window_light.jpg`). Title "Parroquias" / "Parishes" / "Paróquias".
2. **SearchField** (glass capsule 52dp, magnifier, `parish_place_field`):
   - placeholder "Ciudad, barrio o dirección" / "City, neighbourhood or address" / "Cidade, bairro ou endereço";
   - IME action **Search** → `searchPlace()`;
   - while it has text, a trailing clear "x" (cd "Borrar") and a trailing **submit** glass circle (arrow-forward, cd "Buscar «q»", `parish_search_place_button`).
3. **PrimaryCapsule "Parroquias cerca de mí"** (`gps_nearby_maps_button`, `Outlined.NearMe`), full width, **always visible**, disabled while loading. Under it, a `footnote` inkSecondary privacy line: "Tu ubicación solo se usa para esta búsqueda." / "Your location is only used for this search." / "Sua localização só é usada nesta busca." (This is true: `lastKnownLocation` is read once, never tracked.)
4. **Radius**: one row, with a `footnote` label "Radio" then FilterChips 5 km · 10 km · 25 km (`parish_radius_selector`, ≥ 44dp targets). **Changing it re-runs the last lookup** when coordinates exist.
5. **State area** (crossfade 250ms between states):
   - **Idle**: glass card with 26dp `Outlined.Church`, headline "Encuentra parroquias cerca de ti", subhead (existing body). Then a glass group **"Directorio de iglesias"** (`parish_open_directory`, `Outlined.Public`, subtitle "Catedrales y basílicas del mundo", chevron).
   - **Loading**: a glass status capsule with a small ink `CircularProgressIndicator` (`parish_loading`, cd "Buscando…") and "Buscando parroquias cerca de {place}…", then 3 skeleton rows in a glass group (static separator-tone bars). Searching takes 2–20 s, so the skeleton keeps the layout from jumping.
   - **Notice** (replaces the one-sentence message): a glass card with a leading outline icon, `headline` title and `subhead` body, then actions. One enum, `ParishNotice`:

     | Case | Title / body (ES) | Actions |
     |---|---|---|
     | Empty(r) | "No hay parroquias a {r} km" / "Abrimos Google Maps para buscar cerca de ti." | Primary "Ampliar a {next} km" (if r < 25; re-runs), Secondary "Abrir Google Maps" (`use_my_location_button`) |
     | Failure / timeout | "El directorio no respondió" / "Abrimos Google Maps para buscar por ti." | "Reintentar", "Abrir Google Maps" |
     | No fix / location off | "Aún no tenemos tu ubicación" / "Activa la ubicación o busca una ciudad. Abrimos Google Maps." | "Ajustes de ubicación" (`ACTION_LOCATION_SOURCE_SETTINGS`), "Abrir Google Maps" |
     | Denied | "Sin acceso a tu ubicación" / "Puedes buscar una ciudad por nombre." | "Intentar de nuevo", or "Abrir ajustes" (app details) when permanently denied (`!shouldShowRequestPermissionRationale` after a denial) |
     | Maps / phone unavailable | inline footnote under the relevant row | none |

     The **auto-open of Google Maps stays exactly as today** (owner decision). The copy says so, as today.
   - **Results**:
     1. Header `headline` "Cerca de ti" / "Cerca de {place short}". Meta `footnote` "12 parroquias · 10 km".
     2. **Map preview card** (image-card radius 22, 180dp tall), `parish_osm_map_button` on the card. OSM raster tiles that fit the result bbox, warm-misted (slight desaturation) in light and colour-matrix darkened in dark. Pins are 22dp ink circles numbered 1…N (first 9). Your position or the searched point is a small accent ring. Bottom-start glass capsule "Ver mapa". Attribution "© OpenStreetMap" in `caption`, bottom-end. Tap opens the **map sheet**. Fallback (offline, or no tiles yet): a glass card with `Outlined.Map` + "Ver en el mapa", same tag, same action.
     3. **Parish list** (`parish_list`), a glass group. Each row (`parish_row_<id>`, ≥ 64dp) has:
        - a leading 28dp numbered pin circle (matches the map);
        - title `body`, wrapping to 2 lines;
        - `footnote` street (or city);
        - `footnote` "Misa del domingo · 10:00, 12:00" when known;
        - trailing distance (`footnote`) and a chevron that rotates when expanded (stateDescription Expanded/Collapsed kept; no selected fill).

        Expanded (animated), inside the same row block:
        - the full address (`subhead`);
        - Mass times, either the formatted times or an honest `footnote`: "Horario de misas no publicado. Llama a la parroquia para confirmarlo.";
        - then a row of two SecondaryCapsules, "Cómo llegar" (`parish_directions_<id>`, `Outlined.Directions`) and "Llamar" (`parish_call_<id>`, `Outlined.Phone`, only with a phone; phone number as its subtitle/cd).
     4. Group "Más": row "Ver más en Google Maps" (`Outlined.Map`) and row "Directorio de iglesias". The directory now also shows after results, under a different container; `parish_open_directory` is kept on that row.
6. **Map sheet** (private, replaces `InAppBrowserDialog` for this screen): full-height sheet, a glass close circle (cd "Cerrar"), `headline` "Mapa", and a WebView with the same `osmEmbedUrl()` (JS on, **no** CSS injection, no textZoom). Same URL logic, same marker.
7. `openMapsAt` query localized like `openNearbyInMaps`, with coordinates unchanged.
8. `rememberSaveable` for query, radius and expanded id.

### 3.6 Calendario y Santuarios

1. **DetailHeader**: `heroes/lumen/pilgrimage.jpg` (fallback `votive_candles.jpg`). Title "Calendario y Santuarios", which may wrap to 2 balanced lines (no `maxLines = 1`).
2. **SegmentedControl** Calendario / Santuarios (glass track, glassStrong thumb), gutter-inset. New `initialTab` param (default 0). Home's shrines tile passes 1 (a MainApp change, §4.1).

**Calendar pane**

3. **Today card** (`calendar_day_<month>_<day>` for today), glass card radius 24, padding 20:
   - a 3dp **liturgical accent bar** on the leading edge (accent from the DESIGN table);
   - eyebrow "Hoy · domingo 8 de noviembre" (weekday derived, §4.4);
   - title2 celebration title;
   - `footnote` meta "Domingo · Tiempo Ordinario · Ciclo A" (rank, seasonName, sundayCycle; on weekdays `weekdayCycle`; Ferial rank omitted);
   - then up to two icon lines (`subhead`, outline 20dp icon): precept (`Outlined.Church` for Mass obligation, `Outlined.NoFood`/`Outlined.Restaurant` for fast/abstinence) and "Evangelio · Mateo 25, 1–13" (`Outlined.MenuBook`);
   - an accessible colour label in the merged cd: "Color litúrgico: verde".
4. **"Próximas celebraciones"** (headline), then **month subheads** (`footnote` inkSecondary, "Diciembre") when the 28-day window crosses a month. One glass **GroupedList** per month. Row (`calendar_day_<month>_<day>`, ≥ 64dp):
   - leading date block 44dp wide: weekday `caption` inkSecondary ("DOM" is not used: sentence-case "dom.") over the day numeral `title2`, plain type with no box;
   - title `body` (wraps, ≤ 2 lines);
   - `footnote` line: rank (omitted for Sunday/Ferial when redundant with the weekday) · precept · "Evangelio · ref";
   - trailing **8dp liturgical dot** with a text alternative in the merged cd.
5. Empty: EmptyState "Sin celebraciones".

**Shrines pane**

6. `subhead` inkSecondary intro (existing copy: "Lugares santos, su historia y la indulgencia plenaria que ofrecen.").
7. List:
   - **With per-shrine art** (§4.3): full-width **ImageCards** (radius 22, 184dp) with a bottom scrim, name (`headline`, white, ≤ 2 lines) and "Lourdes · Francia" (`footnote`, white 85%).
   - **Without art**: a glass GroupedList whose rows have a leading `Outlined.Place`, name (`body`, wraps), `footnote` place ("Ciudad del Vaticano / Roma · Italia") and a chevron.

   Each row/card is `shrine_card_<id>` and pushes the detail.
8. **Shrine detail** (in-screen push, own BackHandler):
   - DetailHeader with the shrine art, or `pilgrimage.jpg`; back circle; title1 name; `footnote` place with `Outlined.Place`.
   - Sections, each a headline + reading text:
     - "Historia" (historicalOrigin);
     - "Significado espiritual" (**spiritualSignificance**, newly shown);
     - "Reliquias y patronazgo" (relicsAndPatron);
     - "Indulgencia plenaria" (a ContentCard with `Outlined.Verified`, plenaryIndulgenceNote);
     - "Consejo para el peregrino" (a ContentCard with `Outlined.Lightbulb`, **practicalPilgrimAdvice**, newly shown).
   - `footnote` coordinates "41,9022° N · 12,4539° E" (locale decimal).
   - PrimaryCapsule "Ver en el mapa" (`shrine_map_button_<id>`, `Outlined.Directions`). Same `geo:` intent; on failure the same Toast with cityCountry.
9. Empty: EmptyState "Sin santuarios".
10. **Removed**: the candle plate (a decorative duplicate of the hero; `pilgrimage_shrine_plate` and its cd are not referenced by any test). If the orchestrator wants the tag kept, it moves to the Shrines intro block. Also removed: the dead `HolyHabitsTab` / `HabitEntry` / `habitCompletionMap` (habits live in Habits & Virtues; `habit_*` tags were never rendered).

### 3.7 In-screen navigation (Spiritual and Pilgrimage)

A local `page` state with an `AnimatedContent` push/pop (slide ⅓ + fade, the
same motion as MainApp), plus a `BackHandler` enabled only while a sub-page is
open **and** it was opened from the hub. A deep-linked sub-page
(`initialTab ≠ 0`) leaves the system back to MainApp, which returns to Home.
The header back circle calls the same logic. The root tags
(`spiritual_life_screen`, `pilgrimage_and_habits_screen`) wrap every page.

### 3.8 Copy and lengths (ES / EN / PT)

Longest strings and how they fit at 411dp (and 360dp):

| String | ES | EN | PT | Treatment |
|---|---|---|---|---|
| Pilgrimage title | Calendario y Santuarios (23) | Calendar & Shrines | Calendário e Santuários (23) | title1, wraps to 2 lines |
| Tile | Canto gregoriano | Stations of the Cross (21) | Canto gregoriano | caption, 2 lines, centred, fixed tile height |
| Near-me capsule | Parroquias cerca de mí | Parishes near me | Paróquias perto de mim | single line, full width |
| Composer save | Guardar | Save | Salvar | text button |
| Check capsule | Marcar como concedida | Mark as granted | Marcar como concedida | subhead, wraps under 360dp |
| Notice title | No hay parroquias a 10 km | No parishes within 10 km | Nenhuma paróquia a 10 km | headline, wraps |
| Filter chips | Agradecimientos | Thanksgivings | Agradecimentos | horizontal scroll |
| Shrine name | Basílica Papal de San Pedro y Tumba de los Apóstoles | n/a | n/a | title1 on 2–3 lines on the detail; body 2 lines in the list (no ellipsis needed at 411dp) |

All copy goes through `pick(es, en, pt)` and loses its glyphs ("→", "✓", "✦").
Sentence case throughout.

---

## 4. Component needs

### 4.1 From the foundation owner (shared `ui/design`, `ui/theme`, `MainApp.kt`)

1. **DetailHeader**(asset, fallbackAsset, title, eyebrow?, meta?, onBack, actions, imageHeight = 248dp) usable as a LazyColumn item. Full-bleed under the status bar, mist light/dark. The image carries `testTag("screen_header_illustration")`. A **collapsing glass top bar** with inline 17sp title and persistent back, driven by the list state (e.g. a `DetailScaffold(listState, title, onBack) { … }`).
2. **LargeTitleHeader**(title, onBack?, trailing slot), for the quiz page.
3. **Scaffold for pushed screens**: no reserved top bar when the screen draws a DetailHeader. Content draws under the status bar. **MainApp passes `onBack` to my three screens** (new param `onBack: (() -> Unit)? = null`). Plus a **bottom inset** (floating tab bar height + nav inset), e.g. `LocalBottomBarInset`, for my floating capsule, the TransportBar and the list padding.
4. **MainApp**: `PilgrimageAndHabitsScreen(initialTab = …)`, with 1 from Home's "Santuarios y Fe" and 0 from More. Keep `spiritualLifeInitialTab` as is.
5. Components (Lumen look; other identities keep their own):
   - GlassSurface;
   - **ContentCard** (leading icon, title, subtitle, body slot, trailing round play button with cd + playing state);
   - **GroupedList/Row** with a **leading composable slot** (date block, numbered pin) and a trailing slot (dot, distance), multi-line title/subtitle, and a chevron that can rotate for expansion;
   - **CategoryTile** (3-col, 2-line caption);
   - **SegmentedControl**;
   - **FilterChips** (single-select row, scrollable);
   - **SearchField** with `onSearch` (ImeAction.Search), clear button and a trailing slot;
   - **PrimaryCapsule / SecondaryCapsule** (leading icon, enabled, text-button variant);
   - **TransportBar**(onPrev, onPlayPause, onNext, isPlaying, prevEnabled, nextEnabled, per-button modifiers for tags/cd);
   - **ProgressBar** (thin, with stateDescription);
   - **check circle / CheckRow** (success fill);
   - **GlassCircleButton** 40/44;
   - Lumen **EmptyState** with an actions slot;
   - **Sheet** (full-height, radius 28, imePadding);
   - **confirm Alert** (glass, destructive action).
6. Tokens/styles: `title1`, `title2`, `headline`, `body`, `callout`, `subhead`, `footnote`, `eyebrow`, `caption`, `reading`; `success`; `separator`; **`liturgicalAccent(LiturgicalColor | hex)`** mapping data colours → DESIGN accents (GREEN→Ordinary `#3E9B4F`, VIOLET→`#7B5EA7`, WHITE→`#C8A24A`, RED→`#C0392B`, ROSE→`#D48AA0`).
7. Glyph: thin outline **Latin cross** (`DevotionalGlyphs.crossOutlined`) for the Way of the Cross tile/header. If none is provided, I draw a private ImageVector in my file.
8. One shared **`rememberAssetBitmap(path, maxPx)`**, so I delete my 3 private copies.
9. `InAppBrowserDialog` (not mine): Spanish-only chrome and article CSS. I stop using it for the map (private sheet). Whoever owns it should localize it.

### 4.2 Private to my files

`JournalEntryCard`, `JournalComposerSheet`, `JournalPromptRows`,
`StationPage` + `StationPager`, `QuizOptionRow`, `ChantCard` (lyrics
disclosure), `ParishSearchBar` glue, `ParishMapPreview` (OSM tiles + pins on
Canvas, in-memory tile cache, UA "SanctaFides/1.0", ≤ 6 tiles per search,
attribution), `ParishMapSheet` (WebView), `ParishRow` (numbered pin,
expand), `ParishNoticeCard` + `ParishNotice` enum, skeleton rows,
`CalendarTodayCard`, `CalendarDayRow`, `ShrineRow`/`ShrineImageCard`,
`ShrineDetail`, a small date helper (weekday/relative date per
`AppLanguage`), `findActivity()` for the permission rationale check.

### 4.3 Assets (optional, public domain; graceful fallback to the 3 heroes)

- `heroes/lumen/via_crucis.jpg`, a classical Way of the Cross painting (e.g. G.D. Tiepolo's Via Crucis, S. Polo, Venice, PD). Ideally one per station later: `heroes/lumen/via_crucis/<n>.jpg`.
- `heroes/lumen/chant.jpg`, a PD Gregorian manuscript / gradual page.
- `heroes/lumen/shrines/{rome_vatican,holy_sepulchre,fatima_sanctuary,lourdes_sanctuary,santiago_compostela}.jpg`, PD/CC0 photos. Without them the shrine list uses the glass-row variant.

### 4.4 Data asks (not my files; for the orchestrator/data owner)

1. **Way of the Cross has 3 of 14 stations** (`DoctrineAndSpiritualData.viaCrucisStations`). The player is designed for 14. Please complete stations IV–XIV in ES/EN/PT.
2. Chant titles are not localized and carry "(Canto Gregoriano Simple)". I trim the parenthetical at display; a localized title field would be better.
3. `LiturgicalCalendarDay` has no date or weekday. I derive it in-screen by matching `LiturgicalCalendar.today() + 0..27` on day and month. A `date: CalendarDate?` field on the model would be cleaner.
4. `DeviceLocationProvider` returns null without a cached fix. A `getCurrentLocation` (API 30+) fallback would turn many "no fix" cases into results.

---

## 5. Decisions for the orchestrator

1. **Keep Google Maps auto-launch** on empty/failure/no-fix (the owner's explicit choice). The plan keeps it and adds explicit actions. Say if it should become offer-only.
2. **Delete the dead habit UI** in `PilgrimageAndHabitsScreen.kt` (`habit_progress_tile`, `habit_card_*` were never rendered). Recommended.
3. **Drop the candle plate** (`pilgrimage_shrine_plate`), a decorative duplicate. Recommended. Otherwise the tag moves to the Shrines intro.
4. **OSM static-tile map preview** (network, OSM tile policy: UA + attribution + light use). Recommended, with a glass fallback card so renders never depend on the network.
5. Journal **delete now asks for confirmation**. Recommended.

## 6. Verification plan (once the foundation API is ready)

- Build: `tools/device/gradle.sh -q :app:assembleDebug` after each file.
- Behaviour: `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `ParishFinderTest`, `ParishScheduleTextTest`, `LiturgicalCalendarTest`.
- Renders: `-Pshots.only=spiritual_life,parishes,pilgrimage_habits -Pshots.lang=es` (then en, pt), light + dark, plus `-Pshots.height=1600` to see whole pages. Sub-pages and states (journal with entries, quiz answered, Way of the Cross, chant, parish results/notice, shrine detail) need either preview hooks or phone shots. I will ask whether to add capture cases to the screenshot test, which I don't own.
- Phone: More → Parroquias (permission flow, real search, map sheet, Directions/Call intents), Home → Trivia (deep link + back), Home → Santuarios (lands on Shrines).
- Grep after edits: every testTag in §1 is still present, or listed in §5 as deliberately removed.

## 7. Build order

1. Pilgrimage (smallest; proves DetailHeader + SegmentedControl + GroupedList).
2. Parishes (states + map).
3. Spiritual life (hub, composer sheet, three sub-pages).

Each step: build, render light/dark ES, self-review, then the builder note in
`docs/redesign/reviews/life-round1-builder.md`.
