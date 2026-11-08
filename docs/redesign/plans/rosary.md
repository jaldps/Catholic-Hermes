# Rosary & Divine Mercy: redesign plan (Lumen)

Owner: rosary area agent. Files: `ui/screens/RosaryScreen.kt`, `ui/components/PrayerBeadCounter.kt`.
Status: STEP 1, audit and plan only. No code has been edited.

Direction in one line: a **"Now Praying" stage**. Think Apple Music Now Playing, but for prayer.
The current mystery's painting is the album art. The bead reel is the scrubber. The prayer text is the lyrics.
A glass transport sits in the thumb zone. All the owner's mechanics stay; the empty, boxy UI goes.

---

## a. Inventory: the "nothing lost" checklist

### Data and sources
| Item | Source | Notes |
|---|---|---|
| Today's mystery set | `repository.getTodayRosaryMystery()` (weekday: Mon/Sat Joyful, Tue/Fri Sorrowful, Wed/Sun Glorious, Thu Luminous) | Initial `selectedMysteryType`; menu marks it "· hoy / Today / Hoje" |
| Mystery set data | `repository.getRosaryMystery(type)` → `RosaryDecade(number, title, scriptureRef, fruitOfMystery, meditation, …)` | `fruitOfMystery` already starts with "Fruto:/Fruit:" |
| Set name and days | `RosaryMysteryType.displayName`, `.recommendedDays` | ES/EN/PT |
| Creed | `repository.vaticanPrayers["creed"]` (leader + response); fallback `divineMercySteps[stepIndex 2]` | |
| Chaplet opening prayer and "O Blood and Water" | `repository.divineMercySteps[stepIndex 1]` | Optional steps |
| Litany of Loreto | `LitanyOfLoreto.lines(lang)`, `.closingPrayer(lang)` | Split into Trinity, Marian invocations, Lamb of God |
| All other prayer texts, rubrics, names, section titles/subtitles | Private builders in `RosaryScreen.kt` (`signOfCross`, `ourFather`, … `buildRosaryGuide`, `buildMercyGuide`) | Kept **verbatim**. The data model (`GuideStep`, `PrayerGuide`, `BeadSpec`, `GuideSection`) is kept as is |
| Narration | `LiturgicalTtsManager`: `speakSingle`, `speakDialogue`, `stop`, `voiceMode`, `setVoiceMode`, `currentlySpeaking` | |

### Step model (must not change)
- **Rosary: 85 steps on 61 beads, 7 sections.**
  - Opening (7 steps): Sign of the Cross and Creed (crucifix), Our Father (large), 3 Hail Marys (faith/hope/charity intentions, "n of 3"), Glory Be on the 3rd small bead.
  - Each decade (14 steps): Announcement (leader, meditation, detail "ref · fruit") and Our Father on the large bead; 10 Hail Marys ("n of 10"); Glory Be and Fatima prayer on the 10th bead.
  - Closing (8 steps, all on the medal): Hail Holy Queen; Litany part 1, 2 and 3 (optional); "Pray for us" (V/R); litany prayer (optional); collect; Sign of the Cross.
- **Chaplet: 68 steps on 60 beads.** Sign of the Cross; optional opening prayer and 3× "O Blood and Water" (crucifix); Our Father; Hail Mary; Creed (2 small beads); 5 × (Eternal Father + 10 × "For the sake of His sorrowful Passion"); medal: Holy God ×3, optional closing prayer, Sign of the Cross.
- Prayers with a response render as ℣ leader / ℟ response. Single-voice prayers are plain text (the test matches the exact string).
- `whereLine`: "Inicio" / "Década n de 5" / "Conclusión". `countLine`: "4 de 10". `optional` flag: "Oración opcional".

### State
| State | Today | Plan |
|---|---|---|
| `selectedTab` (0 Rosary, 1 Mercy) | `remember`, seeded by `initialTab` (Habits deep links `onOpenRosary(0/1)`; nav-bar tap resets to 0) | Keep. Use `rememberSaveable` |
| `selectedMysteryType` | `remember`, today's set; changing the set resets the index to 0 | Keep the behaviour. Use `rememberSaveable` |
| `rosaryStepIndex`, `mercyStepIndex` | Independent `remember` (test: switching devotions keeps each position) | Keep independent. Use `rememberSaveable` so position survives rotation and process death |
| `narrationActive` | Persistent pray-aloud flag. A `LaunchedEffect(narrationActive, tab, set, lang, rosaryIndex, mercyIndex)` speaks every step change; only Pause stops it | Keep exactly. Add `onDispose { stop }` (today, leaving the tab orphans the utterance) |
| `voiceMode` | In-memory `StateFlow` in the TTS manager | Keep |
| Instructions open | `remember(step.name, step.rubric)`, so it collapses on every step | Change to one sticky "guide mode" for the session (beginners turn it on once) |
| Persisted to disk | Nothing | Unchanged. "Resume where you left off" is an optional follow-up and needs a prefs key from foundation/data |

### Actions
Previous / Next step; play/pause narration; tap a bead to go to its first step; rail accessibility custom actions (Anterior/Siguiente); tap a section dot to jump; tap a section row ("Partes del Rosario" / "Pasos de la coronilla") to jump; switch devotion; pick a mystery set; pick a voice mode; toggle instructions. All are kept (see section c for where each one moves).

### testTags (all preserved)
`rosary_screen` (AppNavigationTest) · `rosary_hero_photo` · `rosary_hero_title` / `mercy_hero_title` · `rosary_hero_step_counter` / `mercy_hero_step_counter` · `prayer_bead_counter` (rosary rail) / `mercy_step_counter` (mercy rail) · `prev_bead_button`, `next_bead_button`, `toggle_prayer_audio_button` · `prev_mercy_step_button`, `next_mercy_step_button`, `toggle_mercy_audio_button` · `page_dots` · `prayer_text_art` · `current_prayer_title` (heading) · `prayer_instructions_toggle` (stateDescription Expandido/Contraído) · `rosary_mystery_picker` · `rosary_voice_mode_picker` · `mystery_hero_photo`.

### What RosaryInteractionTest requires
1. EN at 411×891: `current_prayer_title` equals "Sign of the Cross" and is displayed. The exact text node "In the name of the Father, … Amen." is displayed and **unique**. `prev_bead_button` is disabled at step 0. `next_bead_button.performScrollTo()`: the **transport must sit inside a vertically scrollable ancestor**, or the call throws. After the click, the title is "Apostles' Creed" and "I believe in God" is a substring.
2. Clicking the text "Divine Mercy" (exact, ignoreCase, must be a **single** node, reached by `performScrollTo`) shows the chaplet at "Sign of the Cross". Mercy prev/next tags work; the next step is "Opening prayer". Clicking "The Holy Rosary" brings the Rosary back to "Apostles' Creed". **EN segment labels must stay exactly "The Holy Rosary" / "Divine Mercy", and that exact string must appear nowhere else.**
3. ES at 320×760, font scale 1.3: prev/next are displayed and enabled after scrolling, and a **text node "Anterior" / "Siguiente"** must be one line, not ellipsized. ⚠ The current transport (commit 817673d) is icon-only with `contentDescription = null`, so this test is **probably failing today**. Confirm in step 2. Proposal: an icon-only transport with contentDescriptions "Anterior/Siguiente" plus a test change to `onNodeWithContentDescription` with a ≥44dp bounds assertion (needs orchestrator approval). Fallback if refused: 12sp caption labels under prev/next.

### Content descriptions and accessibility today
- Rail: contentDescription "where, prayer, n of m, Paso N de M" plus custom actions prev/next. Keep.
- Page dots: Role.Button with contentDescription = section title. Hit area is about 18dp, which is a failure.
- Transport IconButtons: **no label at all** (TalkBack says "Button"). Must fix.
- Hero photos are decorative (null). `current_prayer_title` is a heading.

### TTS behaviour
- `narrate(step)`: a blank response calls `speakSingle(leader + meditation)`, otherwise `speakDialogue(leader, response)` (chime, male leader, female response after 1.2s).
- `currentlySpeaking` drives highlighting (the spoken half is drawn in accent).
- **Bugs:** (1) `voiceMode` is never read by the TTS manager, so the "Voces" picker does nothing. (2) The TTS language is set only in `MainActivity`/`onInit`, so EN/PT users may hear the Spanish voice. (3) `stop()` does not cancel the delayed `postDelayed` callbacks in `speakDialogue`, so a fast Next can overlap voices. Fixes 1 and 2 go in my file: `narrate()` honours the voice mode (leader only → `speakSingle(leader)`; response only → `speakSingle(response, female)`; steps without a response are always read in full) and calls `ttsManager.setLanguage(lang)` before speaking. Fix 3 is in `LiturgicalTtsManager` (not mine): request `mainHandler.removeCallbacksAndMessages(null)` in `stop()`.
- There is no auto-advance. Keep it that way. A "hands-free" mode is an owner decision and is out of scope.

### Divine Mercy differences
- No mysteries picker. Painting `divine_mercy.jpg`. Title "Coronilla / Chaplet / Coroinha". The rail has 2 small pendant beads, not 3. Steps are 68.
- 50 of the 68 steps are the 2-line "Por su dolorosa Pasión", so the layout must not look empty.
- Faustina quote card ("A las tres de la tarde…", Diario de Santa Faustina). Section list "Pasos de la coronilla".

### Other screen content (kept, moved)
Mystery card (n.º Misterio, scripture ref, title, meditation, "Fruto del misterio" + fruit), "Partes del Rosario" list with "Rezado" trailing, votive-candles band, pearl hero.

### `PrayerBeadCounter.kt`
**Dead code.** It is not called anywhere. It hard-codes Spanish ("MISTERIO / DÉCADA", "Rezar Audio"), uses raw Material buttons and colours, and **duplicates the tags** `prayer_bead_counter`, `prev_bead_button`, `next_bead_button`, `toggle_prayer_audio_button`. Plan: turn this file into the home of the new public, reusable bead visuals (`BeadReel` and the pearl drawing) and delete the legacy composable, after a grep confirms no callers.

---

## b. What is wrong today (specific)
1. **Black band** of about 90dp above a 180dp hero strip. The painting does not start at the top. Content is not edge-to-edge.
2. **Generic art.** The same pearl photo is used for both devotions and every mystery, and a stock votive-candle band sits below the fold. The bundled Divine Mercy image is unused. Nothing changes as you pray, so it never feels like "contemplating the mystery".
3. **Duplicate titles.** The scaffold title, the hero "Santo Rosario" and the segment "El Santo Rosario" all say the same thing. "Coronilla" in the hero contradicts "Divina Misericordia" in the segment.
4. **The rail reads as texture.** The loop radius is 64dp and small beads are 6dp across with about 7dp pitch. The current bead is a 6dp green dot. Tapping a bead is a guess (nearest within 24dp). The Fitness-style ring in liturgical green is a second accent system around a mostly empty dark card.
5. **Card in a card.** A green `accentMuted` prayer box sits inside a bordered dark card. Hard hairlines, grey flat boxes and green everywhere violate "one accent" and "no flat boxes".
6. **Transport is weak.** Three identical grey circles with generic back/forward *arrows*, not prayer/skip glyphs. No labels for TalkBack. The play button is not distinguished. The page dots duplicate progress with sub-44dp targets. `LongPress` haptic fires on every Next (too heavy for 84 taps).
7. **The primary context is below the fold.** Which mysteries you are praying (the picker), the current mystery's meditation and fruit, voices and instructions all need scrolling. During the 10 Hail Marys the mystery card is off-screen, which is exactly when you meditate on it.
8. **Redundancies.** "Fruto del misterio" eyebrow followed by "Fruto: …" (the data already has the prefix). The meditation shows in the StepCard on the announcement step while the decade card hides. "Paso N de 85" exists only in the hero and scrolls away.
9. **Instructions** collapse on every step, so a beginner re-opens them 85 times.
10. **Broken behaviour:** the Voces picker has no effect; the TTS language can be wrong; narration keeps talking after leaving the tab; state is lost on rotation.
11. **Hard-coded values:** `Color.White` highlight, `MaterialTheme.typography.bodyLarge`, magic dp values, per-screen `AssetPhoto` decoder (full-size decode on the main thread, no sampling).
12. **Dark mode:** a murky photo, a grey card on black and a green glow. It is an inversion, not a composition.

---

## c. The new design

### Grammar
**Stage → label → progress → bead reel → prayer → transport.** On a 411×891 phone at font scale 1.0 everything fits with no page scroll. Only long prayers scroll, inside the prayer area.

### Layout at 411×891 (viewport ≈ 799dp above the floating tab bar; content draws under the status bar)
```
y0   ┌───────────────────────────────────────┐  painting, full-bleed, focus-cropped
     │ status bar (icon tone from manifest)  │
y40  │     ( Santo Rosario │ Divina Miseric. ) │  glass segmented, centred, hugging
     │                                       │
     │          [ mystery painting ]         │
     │  ░░░░░░░░ mist + progressive blur ░░░ │
y~186│  (◐ Misterios Dolorosos ⌄)            │  glass picker capsule 32/44dp
     │  La Agonía en el Huerto de Getsemaní  │  headline 17/22, ≤2 lines (3 at ≥1.3×)
y~272│  ━━━━━━━━━━┃━━━━━━━━━━━━━━━━━━━━━━━━  │  7-segment progress bar, 4dp
     │  Paso 18 de 85          Década 1 de 5 │  footnote, row is a 44dp button → sheet
y~316│   ·  ∘  ○  ●  ●  ◉  ○  ○  ∘  ·        │  BEAD REEL 64dp, current centred
y~392│  Ave María                   4 de 10  │  title2 22/28 + subhead count
     │  ℣ Dios te salve, María, llena eres de│  reading 19/30 serif (or 18/28 sans)
     │  gracia; el Señor es contigo…         │  bounded area, inner scroll,
     │  ℟ Santa María, Madre de Dios, …      │  16dp mist fades top/bottom
     │                                       │
y~719│  (ⓘ)   (  ⏮     ( ⏯ )     ⏭  )   (⋯)  │  transport row 64dp
y799 └───────────────────────────────────────┘
         [ owner's floating 6-tab bar ]
```

### Layout mechanics
- A private `PracticeLayout` (custom `Layout`) inside `BoxWithConstraints` and an outer `verticalScroll` (needed for `performScrollTo`, small screens and large type).
- Fixed blocks are measured first. The **prayer area gets a stable target height**: the measured height of the devotion's dominant prayer (Rosary: Our Father; Chaplet: Eternal Father), via `rememberTextMeasurer`, cached per guide, width and font scale.
- The **stage absorbs the remainder**, clamped to [painting ≥ 96dp visible, ≤ 52% of the viewport]. Result: the Chaplet (short prayers) gets a taller Divine Mercy painting and never looks empty; the Rosary keeps the Our Father unscrolled.
- The layout changes only with devotion or font size, never between steps, so the title, reel and transport never jump.
- When the remainder drops below 160dp (320×760, 1.3×), the prayer area holds at 160dp and the page scrolls a little. The transport stays inside the scroll (tests).

### Stage (mystery painting)
- **Asset resolution:** list `heroes/mysteries/` and match `<set>_<n>_*.jpg` (`joyful|sorrowful|glorious|luminous`, n = 1..5). Opening uses the set's mystery 1; decade n uses mystery n; closing keeps mystery 5. The Chaplet uses `divine_mercy.jpg` (mysteries folder, else `heroes/divine_mercy.jpg`).
- **Crop:** `ContentScale.Crop` with `BiasAlignment(focus.x·2−1, focus.y·2−1)` from `manifest.json`. Missing focus defaults to (0.5, 0.3).
- **Fallback chain:** set painting 1 → `heroes/mary_statue.jpg` (Rosary) / `heroes/divine_mercy.jpg` → canvas gradient. Never a grey box.
- **Decoding:** sampled to viewport width on `Dispatchers.IO`; a small LRU of 3 bitmaps. The next decade's painting is prefetched during the Fatima prayer.
- **Mist treatment, light:** colour matrix (saturation 0.85, lift 10% toward canvasTop). A **progressive blur** (a 1/16-size copy drawn over with a DstIn mask from 40% to 85% of the height; works on all APIs). An eased canvas fade with 8 stops from 38% to 100% (no grey banding over dark paintings).
- **Mist treatment, dark:** saturation 0.9 and a 12% darken; fade to the dark canvasTop. No glow.
- **Dark paintings in light mode** (Agony, Crowning, Divine Mercy): the manifest `tone: dark` switches status bar icons to light, and the glass top control uses the dark-glass variant.
- **Transitions:** only on section or set change. 600ms crossfade plus a one-time settle (scale 1.04 → 1.0 over 1.2s, ease-out). No continuous Ken Burns. Everything is frozen under `LocalInspectionMode`.
- **Semantics:** the stage has contentDescription "‹painting title›, ‹artist›" when the manifest supplies them, otherwise it is decorative. Tag `rosary_hero_photo` (and `mystery_hero_photo` on the same node for decades).

### Top control
- A glass `SegmentedControl` (compact, content-hugging, centred, 36dp visual / 44dp hit).
- Labels: ES "Santo Rosario | Divina Misericordia", EN "The Holy Rosary | Divine Mercy" (test), PT "Santo Terço | Divina Misericórdia".
- If the measured width exceeds the space (320dp, 1.3×), it falls back to "Rosario | Coronilla" / "Terço | Coroinha". EN keeps the full labels at the test widths.
- No other button at the top. A tab root has no back button.

### Label group (in the mist, bottom-start, 20dp gutter)
- **Rosary:** a glass picker capsule `(◐ Misterios Dolorosos ⌄)` (subhead 15, outline set icon, chevron; tag `rosary_mystery_picker`), then a **headline** = `section.subtitle`. That is the mystery title during decades, and the plan of the part at the opening and closing ("Señal de la cruz, Credo, Padre Nuestro, tres Avemarías y Gloria"). Tag `rosary_hero_title` / `mercy_hero_title`.
- Tapping the headline during a decade opens the **Mystery sheet**: painting image card, title1, scripture ref, meditation (reading), fruit (shown once, data prefix as is), painting credit.
- **Chaplet:** no capsule. Headline = section subtitle ("Padre Eterno y diez «Por su dolorosa Pasión»").

### Progress
- A thin **7-segment bar** (4dp, capsule ends, 3dp gaps, segment widths proportional to the steps in each section). Fill is the action/ink colour, the track is the separator colour.
- Below it: `Paso 18 de 85` (left, tag `…_hero_step_counter`) and `Década 1 de 5` / `Inicio` / `Conclusión` (right), footnote, inkSecondary.
- Semantics: `progressBarRangeInfo`. Tag `page_dots`. The whole row is a 44dp button that opens the Parts sheet. This replaces the tiny dots.

### Bead reel (the bead rail, redesigned, tag `prayer_bead_counter` / `mercy_step_counter`)
- The **whole rosary is laid on one gently sagging strand** (crucifix → pendant → 5 decades → medal). The **current bead is always at the centre**, so the eye never searches.
- Pitch: 30dp small to small, 36dp around large beads, 40dp around the crucifix. About 12 beads are visible, fading out into the gutters (40dp edge mask). Scale falls off away from the centre (1.0 → 0.75).
- **Beads are drawn as pearls:** radial sheen, a 1dp lit edge, a crisp 6% ink contact shadow. Sizes: small 14dp, large 20dp, medal a 22dp rounded lozenge with a struck cross, crucifix 30dp (the existing budded crucifix drawing; the corpus is cut out in the canvas colour).
- **Light mode:** to come = white glass pearls; prayed = warm grey (inkSecondary) "told" pearls; current = ink pearl at 1.45× with a **2dp liturgical-accent ring** at a 3dp gap. That ring is the only accent on the screen.
- **Dark mode:** to come = glass 10%; prayed = ivory 55%; current = ivory and the accent ring. No halo.
- The thread is a 1dp separator colour, inked at 35% over the prayed run.
- **Sub-step pips:** 4dp dots under the current bead when it carries several steps (crucifix 2, large bead 2, 10th bead 3, medal 8), so "you're on 3 of 8 here" is visible.
- **Motion:** a spring (0.8 damping, medium-low stiffness) slides the strand one bead. Jumps of more than 12 beads crossfade instead of scrolling 40 beads.
- **Interaction:** tap a bead → its first step (existing). **Horizontal drag scrubs** bead by bead with a tick haptic and snaps on release. Drag left = forward, like pulling beads through the fingers.
- **Accessibility:** one node with the existing contentDescription ("Década 1 de 5, Ave María, 4 de 10") and custom actions Anterior/Siguiente, plus `stateDescription` "Paso 18 de 85".
- The **whole-rosary loop drawing** (`RosaryGeometry`) is kept, enlarged to 280dp, as the map at the top of the Parts sheet. Its beads become legible and tappable there.

### Prayer block (tag `prayer_text_art` on the area)
- Title row: `title2` prayer name (tag `current_prayer_title`, heading, ≤2 lines) with a trailing count "4 de 10" (subhead, inkSecondary).
- Optional steps get an "Opcional" glass tag and a trailing **"Saltar ›"** text button that jumps to the next non-optional step (litany ×3 + prayer, Chaplet opening ×4, closing). This is new and small; flag it to the owner.
- **Text:** reading style (the user's reader font), ink.
  - ℣ and ℟ glyphs in inkSecondary at weight 600. No red rubric: one accent rule.
  - Single-voice prayers stay one plain `Text` with the exact string (test).
  - **While narrating, the "lyrics" behaviour:** the half being spoken is ink and the other half fades to inkSecondary (still ≥4.5:1). When the response starts, the area scrolls it into view.
- **Announcement step:** the announcement, then the meditation (reading, inkSecondary), then a footnote with "Lucas 22, 39-46 · Fruto: …".
- **Guide mode (ⓘ on):** a glass hint card (radius 20) at the top of the area holds the rubric, plus the ℣/℟ legend when the step has a response. It stays on across steps.
- **Step change:** 220ms crossfade with a 6dp rise. Long texts (Creed, Salve, litany, collect) scroll inside the area behind 16dp mist edges. No page scroll.

### Transport row
- `(ⓘ)` glass circle 44dp (tag `prayer_instructions_toggle`, stateDescription Expandido/Contraído, contentDescription "Instrucciones", filled glyph when on).
- The **glass TransportBar capsule:**
  - ⏮ previous (contentDescription "Anterior"; disabled tint inkTertiary; enabled=false at 0).
  - ⏯ **play/pause in a 60dp glassStrong circle** (contentDescription "Rezar en voz alta" / "Pausar"; stateDescription).
  - ⏭ next ("Siguiente").
  - Tags per devotion as today. Media glyphs are filled (media convention).
- `(⋯)` glass circle opens the **Options sheet**.
- **Haptics:** a `View` haptic `KEYBOARD_TAP` for prev/next, `CLOCK_TICK` while scrubbing, `CONFIRM` (API 30+, else `LONG_PRESS`) when a decade ends. Compose 1.7 has no fine-grained types.
- At 320dp the capsule compresses (44/56/44).

### Sheets (glass, 28 radius)
- **Mysteries sheet** (from the capsule): 4 rows (≥64dp), each with a 48dp painting thumbnail of mystery 1, the set name (body), days (footnote), "Hoy" for today's set, and a check on the selected set. Selecting a set resets to step 1 (existing mechanic).
- **Options sheet** (⋯ or the progress row):
  - **Voces:** a 3-segment glass control "Diálogo / Solo quien guía / Solo la respuesta" (tag `rosary_voice_mode_picker`).
  - **Partes del Rosario / Pasos de la coronilla:** the loop map, then a grouped list (title, subtitle, "Rezado" trailing, current row checked) that jumps to the section.
  - Chaplet footer: the Faustina quote (reading, plus "Diario de Santa Faustina" footnote).

### Adaptation
- **Small or large type:** the layout above. Headline up to 3 lines, segment short labels, the prayer area holds at 160dp minimum and the page scrolls.
- **≥600dp width or landscape:** two panes. The stage fills the left 45% at full height (label group at its foot). The right column (max 560dp, centred) holds progress, reel, prayer and transport.
- **Language lengths checked:**
  - Longest headline: PT "A Coroação de Nossa Senhora como Rainha do Céu e da Terra" (56 chars, 2 lines at 17sp/371dp).
  - Segments at 1.3×: ES 364dp ≤ 371.
  - Longest title2: "Ladainha de Nossa Senhora".
  - Counters "Passo 18 de 85" / "Dezena 2 de 5" fit on one row at 280dp and 1.3×.
- **Dark ("night chapel"):** paintings fade into #171614, glass at 7/12%, ivory pearls, light status icons. Dark paintings look their best here.
- **Loading:** the canvas with a soft warm gradient, and the painting crossfades in (≤300ms). Text never waits for art.

---

## d. Component needs

### From the foundation owner (shared)
1. **Scaffold contract:** the Rosary tab root has **no top bar and no title** and draws under the status bar. Also a bottom inset value (or padding) for the floating tab bar, and a **status-bar icon tone API** per screen (set from the painting's tone).
2. **Tokens:** canvas/glass/glassStrong/glassEdge/shadow/separator/ink×3/action; **liturgical accent provider**; type styles title2, headline, subhead, footnote, caption, **reading** (reader font); radii (sheet 28, card 24, group 20, image 22, capsule; plus a **thumbnail** radius, proposed 12); spacing gutter 20; motion spring/press.
3. **GlassSurface** with a dark-glass variant for use over dark art; **GlassCircleButton** (40/44, selected = filled glyph); **TransportBar** (prev / large play-pause / next slots with tags and contentDescriptions); **SegmentedControl** (glass, *compact hugging* variant, short-label fallback hook, glass-over-image style); **PickerCapsule** (chip with leading icon and chevron); **ProgressBar** with a **segmented** variant; **GlassSheet** (modal bottom sheet, 28 radius); **GroupedList/Row** (selected check, trailing value).
4. **Image pipeline:** `rememberAssetBitmap(path, targetWidthPx)` (sampled IO decode, shared LRU); a **`MistImage`/HeroHeader core** that accepts a focus point, light/dark mist, progressive blur, crossfade on asset change, and *no built-in title* (I overlay my own label group); a `HeroManifest` reader.
5. **Data/audio (not mine):** `LiturgicalTtsManager.stop()` must cancel pending `postDelayed` callbacks. Optionally a prefs key for resume.
6. **Test change approval** for `RosaryInteractionTest.translatedControlsStayWholeAndReachableAtLargeType` (see section a).

### Requested from the art agent: `heroes/mysteries/manifest.json` entries
`{ "file": "sorrowful_1_agony.jpg", "focus": [0.5, 0.32], "tone": "dark|light", "title": "…", "artist": "…", "year": "…" }`, one per file, including `divine_mercy.jpg`.

### Private, built by me in my files
- In `RosaryScreen.kt`: `PracticeLayout`, `MysteryStage` (built on the shared MistImage), `StageLabel`, `SegmentedProgress` glue, `PrayerLyrics` (℣/℟ and spoken-half emphasis), `GuideHint`, `MysteriesSheet`, `OptionsSheet`, `MysterySheet`, and `narrate()` with voice-mode and language handling.
- In `PrayerBeadCounter.kt` (rewritten): `BeadReel`, `drawPearl`, `drawCrucifix`, `drawMedal`, and the restyled `RosaryLoopMap`. The legacy unused `PrayerBeadCounter()` is deleted to remove the duplicate tags.

### Verification (step 2)
1. Run `RosaryInteractionTest` and `AppNavigationTest`.
2. Render `-Pshots.only=rosary` in light and dark for ES/EN/PT, plus `-Pshots.height=1600` and 320dp.
3. Check on the phone: step 1, a Hail Mary in decade 3, the litany, the Chaplet decade, and the Chaplet in dark mode.
