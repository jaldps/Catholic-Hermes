# Explore / Settings — Lumen plan

Owner: Explore/Settings agent. Files owned:
`ui/screens/MoreScreen.kt`, `ui/components/FontPickerDialog.kt`,
`ui/components/LanguageSelectorDialog.kt`, `ui/components/InAppBrowser.kt`,
`ui/components/InlineBackLink.kt`.
Not owned (requests in §d): `ui/MainApp.kt`, `GuiCustomizerModal.kt`,
`ui/design/*`, `ui/theme/*`, `data/models/FontChoice.kt`.

Status: Step 1 (audit + plan). No code edited.

---

## a. Inventory: the "nothing lost" checklist

### a1. More screen (`MoreScreen(currentLanguage, sections)`)

Pushed by `MainApp` as tab `AppDestination.MORE` (tab label "Más/More/Mais",
tag `nav_more`). Also reached from Home's `home_more_settings` tile. Rows call
`go(destination)` in MainApp. The destination returns to More on back, except
Habits, which is a tab and returns to Home. Before `go`, MainApp resets
deep-link state: `spiritualLifeInitialTab = 0`, Bible book/chapter/verse to null,
and `catechismInitialParagraph` to null.

| Section (from `moreSections()`) | Entry title (ES / EN / PT) | testTag | Opens | Glyph today |
|---|---|---|---|---|
| Estudio / Study / Estudo | Catecismo / Catechism / Catecismo | `nav_catechism` | `CatechismScreen` | MenuBook |
| | Doctrina / Doctrine / Doutrina | `nav_study_doctrine` | DoctrineAndHistory tab 0 | School |
| | María y Jesús / Mary & Jesus / Maria e Jesus | `nav_study_mary_jesus` | tab 1 (DevotionsExplorer) | Favorite |
| | Historia / History / História | `nav_study_history` | tab 2 | History |
| | 101 preguntas sobre la fe católica / 101 Questions on the Catholic Faith / 101 Perguntas sobre a fé católica | `nav_questions` | tab 3 | Help |
| | Órdenes y oficios / Orders & Offices / Ordens e ofícios | `nav_study_orders` | tab 4 | Groups |
| | Iglesias y Noticias / Churches & News / Igrejas e Notícias | `nav_churches_news` | tab 5 (news only) | Newspaper |
| Vida interior / Inner life / Vida interior | Hábitos y Virtudes / Habits & Virtues / Hábitos e Virtudes | `nav_habits` (same tag as the tab-bar item) | Habits **tab** (back goes Home) | Checklist |
| | Vida espiritual / Spiritual Life / Vida espiritual | `nav_spiritual` | `SpiritualLifeScreen` tab 0 | AutoStories (MainApp override) |
| Lugares / Places / Lugares | Parroquias / Parishes / Paróquias | `nav_parishes` | `ParishFinderScreen` | Church |
| | Calendario y Santuarios / Calendar & Shrines / Calendário e Santuários | `nav_pilgrimage` | `PilgrimageAndHabitsScreen` | Place |
| Ajustes / Settings / Ajustes | Apariencia / Appearance / Aparência | `app_bar_theme_button` | `GuiCustomizerModal` | Palette |
| | Fuente / Font / Fonte (value = `FontChoice.label`) | `font_settings_button` | `FontPickerDialog` | TextFields |
| | Idioma / Language / Idioma (value = `AppLanguage.displayName`) | `app_bar_lang_button` | `LanguageSelectorDialog` | Language |

Other elements:
- Screen title "Explorar / Explore / Explorar", overlaid on the hero.
- Hero photo `heroes/sunrise_mountains.jpg`, tag `more_hero_photo`, with a gradient fallback.
- Section photo strips: Vida interior uses `votive_candles.jpg` and Lugares
  uses `cathedral_dome.jpg`. They are matched by title keyword and tagged
  `more_section_hero_{i}`.
- Appearance fallback value: `"<identity label> · Claro|Oscuro"`, taken from `design.isDark`.
- Empty sections are filtered out.
- Root tag `more_screen` sits on the scrollable LazyColumn.
- Content descriptions: none. Every icon is `null`, and rows have no role.
- Strings in the file: title, light/dark words (ES/EN/PT).
- Data source: none. Everything is passed in, plus `LocalAppDesign`.

### a2. Font picker (`FontPickerDialog` + stateless `FontPicker`)
- Entry points: More › Fuente (MainApp `showFontPicker`) and Appearance sheet ›
  `appearance_font_button`.
- Data: `preferencesStore.preferences.collectAsState(initial = preferences)`, then `FontChoice.fromId`.
- Action: each tap writes `setFontFamilyChoice(id)` right away. Writes are
  serialised (`pendingWrite.join()`). Dismiss waits for the last write. On
  IOException it shows a Toast ("No se pudo guardar la fuente / Couldn't save
  the font / Não foi possível salvar a fonte").
- Options: `FontChoice.entries`, which are IDENTITY_DEFAULT ("Según el estilo /
  Identity default / Padrão do estilo"), Inter, Roboto, EB Garamond. Each label
  is set in its own family.
- Preview: "En el principio era el Verbo. / In the beginning was the Word. / No
  princípio era o Verbo." This must be a single Text node in
  `AppFonts.family(choice)`.
- Button: "Listo / Done / Concluir".
- Tags: `font_picker`, `font_choice_{IDENTITY_DEFAULT|INTER|ROBOTO|EB_GARAMOND}`, `font_preview`.
- Persisted pref: `font_family_choice`. Legacy SERIF/SANS ids still normalise.
- Test contracts:
  - `FontPickerInteractionTest` calls `FontPicker(choice, AppLanguage.EN, onSelect=, onDismiss=)`.
    - Each choice must be selectable and at least 48dp tall.
    - The preview must have exactly one TextLayoutResult with the expected family.
    - It draws `ShadowDialog.getLatestDialog()`, so the picker **must live in an android Dialog window**.
  - `AppNavigationTest` clicks `font_settings_button` and selects a choice.
    - It then clicks the text **"Done"** (which must be unique).
    - After closing, the More screen must show exactly one node with the text **"Inter"**.

### a3. Language selector (`LanguageSelectorDialog(currentLanguage, onLanguageSelected, onDismissRequest)`)
- Opened from More › Idioma.
- MainApp applies a choice with `repository.setLanguage` (which persists
  `language` + `has_chosen_language` and refetches the saint) and
  `ttsManager.setLanguage`.
- A tap selects and closes.
- Rows show the flag emoji, `displayName` (Español/English/Português), the same
  subtitle on every row ("Traducciones católicas oficiales / Official Catholic
  translations / Traduções católicas oficiais"), and a check (cd "Selected") on
  the current row.
- Title: "Seleccionar Idioma / Select Language / Selecionar Idioma". Close button: "Cerrar / Close / Fechar".
- Tags: `lang_option_{es|en|pt}`, `close_language_dialog`.
- `AppNavigationTest` requires that the More screen shows the text **"English"**, as the Language row value.

### a4. In-app browser (`InAppBrowserDialog(url, onDismiss)`)
- Callers:
  - `CatechismScreen` (Vatican CCC pages, `webViewUrl`)
  - `ParishFinderScreen` (OSM embed map, `showOsmMap`)
- Full-screen Dialog with a header row: "Vista en la app" plus a "Cerrar" text button (both Spanish only).
- WebView settings: JS on, DOM storage on, `textZoom = 150`.
- On `onPageFinished` it injects CSS: body 18px, line-height 1.6, padding 12px, and images/tables at max-width 100%.
- Back press dismisses the dialog.
- No tags, no content descriptions.

### a5. Inline back link (`InlineBackLink(label, onClick, modifier, testTag)`)
- Callers:
  - Prayers: `back_to_prayers_button` and `back_from_chaplet_button`, both labelled "Oraciones".
  - Doctrine: `doctrine_entry_back` with a dynamic label, and `back_to_churches_button` labelled "Iglesias".
- Today it draws an accent-tinted ArrowBackIos/ArrowBack glyph and the label (uppercased if `usesAllCapsLabels`).
- The hit area is 48dp min. `onClickLabel = "Volver a $label"` (Spanish only).

### a6. Appearance (`GuiCustomizerModal`, foundation-owned, listed for completeness)
- Sheet tag `gui_customizer_sheet`.
- Identity rows: `design_language_{id}`.
- Palette cards: `theme_preset_{LITURGICAL_DYNAMIC|MONASTIC_MINIMALIST|SACRED_BURGUNDY|MARIAN_AZURE}`.
- Dark-mode chips: SYSTEM / LIGHT / DARK.
- Font button: `appearance_font_button`, which opens the font picker over the sheet.
- Reading size: 1.0 / 1.15 / 1.3. Narration speed: 0.8 / 1.0 / 1.2.
- Save button: `save_preferences_button`. It calls `saveAppearance(..., updateFontFamily = false)`, which `FontPreferencesTest` covers.
- `audio_narration_speed` is persisted, but **nothing reads it**. `LiturgicalTtsManager` never applies it.

---

## b. What is wrong today

**More screen**
1. **Black band** above the hero, about 190px on the phone in dark mode. The
   hero starts below the scaffold's top bar and `padding(innerPadding)`, so
   the content never reaches the status bar.
2. **Generic hero.** Sunrise mountains is a landscape stock photo, not
   devotional art. It is also reused as the 101 Questions hero, so the same
   picture appears twice in the app.
3. **Three names for one place**:
   - the tab says "Más";
   - the scaffold's inline (scrolled) title is `MORE.title` = "Más";
   - the large title says "Explorar".
4. **Flat hierarchy.** Eleven destinations get equal weight. The 7-row Estudio
   list takes the whole first screen, and "Vida interior" starts below the fold
   behind a decorative strip.
5. **Decorative photo strips** (88dp) carry a hard black 55% gradient, which
   makes a dark band even in light mode. The votive candles are saturated
   orange, which breaks "one accent". They are matched by **title string**, so
   they are fragile across languages.
6. **Icon weights are mixed** (see `before/maas.png`):
   - the heart for María y Jesús and the ? for 101 preguntas render filled;
   - the Groups glyph is dotted;
   - icons are 20dp in `textSecondary`, but DESIGN asks for 22–24dp in ink.
7. **Off-token code:**
   - `MaterialTheme.typography.bodyLarge/bodyMedium`;
   - hard-coded 12dp padding and gaps, 220/88/160dp heights, `alpha(0.92f)`;
   - a flat `palette.canvas` background instead of the canvas gradient;
   - a flat grey group in dark mode (`#1C1C1E`-style).
8. **Truncated values.** `widthIn(max = 120.dp)` clips "Según el estilo",
   "Padrão do estilo" and "Cupertino · Oscuro" at 17sp.
9. **The Appearance value is meaningless.** It shows the identity label
   ("Cupertino · Claro"), but both identities now render Lumen and the picker is
   hidden. It also reads `isDark`, not the user's setting (Automatic vs explicit).
10. **"Iglesias y Noticias" is mislabelled and misplaced.** It sits under Estudio
    but opens a news-only feed, and no churches appear. Separately,
    ParishFinder's "Directorio de iglesias" row opens the same news-only page
    (bug, owned by parish/foundation).
11. **Bitmaps decode synchronously** in composition, on the main thread, on
    every tab entry. The file has a private `decodeSampledBitmap` copy that
    DESIGN says to delete.
12. **Rows have no semantics.** There is no role or merged label (title + value).
    Section headers are not marked as headings.

**Font picker**
1. It is a Material `AlertDialog` with M3 radio buttons and a "Listo"
   TextButton, both in the accent green. That is a coloured control, not Lumen.
2. The "Identity default / Según el estilo" option exposes a concept that no
   longer exists.
3. The preview is a small UI-body line under the list. It does not show the
   **reading** style (19/30), which is what the font is mostly for.
4. Text size lives in another sheet behind a "Guardar" button, so the user
   gets two mental models for one job (how text looks).
5. Rows are only 48dp, with no hint of what each family is.

**Language selector**
1. AlertDialog with:
   - a bold title in accent colour;
   - 1dp outline borders;
   - a `primaryContainer` tint on the selected row;
   - hard-coded `22.sp/16.sp/12.sp` sizes and a 12dp radius.
2. **Flags as languages** (🇪🇸 🇺🇸 🇧🇷) are imprecise, look different on each
   device, and are decoration.
3. The same subtitle is repeated on all three rows.
4. Title-case "Seleccionar Idioma" is not normal Spanish usage, and the check's
   description "Selected" is English only.

**In-app browser**
1. "Vista en la app" and "Cerrar" are Spanish in every language.
2. Missing chrome: page title, host, loading progress, error state and
   "open in browser".
3. System back closes the browser instead of going back in the page history.
4. `textZoom = 150` *plus* 18px !important CSS double-enlarges text. The CSS
   (padding 12px) is also injected into the OSM map embed.
5. The WebView is never destroyed (leak).
6. Dark mode always shows a white page.
7. The bar is not edge-to-edge and has no glass.

**Inline back link**
1. It uses the liturgical **accent** for navigation, which breaks "one accent: dot/ring/bar only".
2. The glyph is chosen per identity (ArrowBackIos vs ArrowBack), and identities are gone.
3. There is an `ALL CAPS` branch left over from Vatican.
4. `onClickLabel` is Spanish only.

**Appearance sheet** (for the foundation owner):
1. It uses the M3 sheet, chips, Cards and RadioButtons, with primary-coloured headers.
2. The title is bold: "Personalizar Interfaz y Tema".
3. Some labels are not translated:
   - PT shows the Spanish "Oscuro";
   - the size labels ("Muy Grande") and speed labels ("Pausado") are Spanish only.
4. The identity rows show hidden identities.
5. Saving needs an explicit "Guardar".
6. The font dialog opens over the sheet.
7. The narration speed setting is dead (nothing reads it).

---

## c. The new design

### c1. Information architecture

Hierarchy: art for study, quiet lists for everything else, settings last.

1. **Hero**: "Explorar" with the `explore` art.
2. **Estudio**: six full-width *mist* image cards (the main sections).
3. **Vida interior**: a grouped list (Vida espiritual, Hábitos y virtudes).
4. **La Iglesia hoy**: a grouped list (Parroquias, Calendario y santuarios, Noticias).
5. **Ajustes**: a grouped list (Apariencia, Fuente, Idioma), then a version footer.

News moves out of Study: it is the Church today, not study. Nothing is removed.
All 14 entries and tags stay, including `nav_habits`, which the test needs.

### c2. Top to bottom

Measurements are on the 411dp render; the phone is about 434dp. Gutter is
20dp, so content is 371dp wide.

```
┌───────────────────────────────────────┐  0  (under status bar, no band)
│  explore art, mist (HeroHeader)       │
│                                       │
│  Explorar        ← display 44/48      │  ~224 title bottom
└───────────────────────────────────────┘  248 art fully dissolved into canvas
  Estudio                 ← headline 17/22, 12 below
 ╭──────────────────────────────────────╮
 │ Catecismo         ░░░▒▒▓▓ art ▓▓▓▓▓ │  112–116 min, radius 22
 │ El texto completo:░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓ │
 │ 2865 puntos       ░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓ │
 ╰──────────────────────────────────────╯  gap 12
   Doctrina · María y Jesús · Historia · 101 preguntas · Órdenes y oficios
  (6 cards; about 4 visible above the floating bar on first open)
                                            section gap 32
  Vida interior
 ╭──────────────────────────────────────╮
 │ ♡  Vida espiritual                  › │ 64 (title + footnote subtitle)
 │    Diario, Vía Crucis y canto…        │
 │ ─────────────────────────────────── │ inset hairline
 │ ⛉  Hábitos y virtudes               › │
 │    Tu regla de vida                   │
 ╰──────────────────────────────────────╯
  La Iglesia hoy
 ╭ ⛪ Parroquias — Misas y confesiones cerca de ti                  ›
 │ ⌖ Calendario y santuarios — Días santos y peregrinaciones        ›
 ╰ 📰 Noticias — Lo último de Vatican News                          ›
  Ajustes
 ╭ 🎨 Apariencia                                  Automático  ›  52
 │ Tт Fuente                                           Inter  ›  52
 ╰ 🌐 Idioma                                         English  ›  52
     Sancta Fides 1.4 · Arte sacro de dominio público   ← footnote, inkSecondary
  + bottom clearance for the floating tab bar
```

**Hero**
- Shared `HeroHeader(asset = "heroes/lumen/explore", title = "Explorar")`.
- Height about 248dp from the very top of the screen, under the status bar.
- Title `display` at bottom-start on the gutter. No eyebrow, no actions: a
  settings gear would duplicate the Settings group.
- Light mode: lift, slight desaturation, fade to `canvasTop` over the lower ~55%.
- Dark mode: fade to the dark canvas, art dimmed about 10%, nothing glows.
- Keep tag `more_hero_photo` and give the title heading semantics.
- If the art is missing (or fails to decode in a test host), fall back to the
  canvas gradient. The title stays ink, so legibility never depends on the art.

**Study mist cards**

The reference's novena card treatment ("Light is the material"):
- Art is anchored to the end side with Crop and a per-asset focal alignment.
- A horizontal mist runs over it:
  - fully opaque glass composite from the start edge to about 52% of the width;
  - fading to 0 by about 78%.
- Text column: max 56% of the card, padding 20.
  - Title `title2` 22/28, ink, one line (short titles are guaranteed).
  - Meta `subhead` 15/20, `inkSecondary`, up to 2 lines.
- Card shape and depth: radius 22, glass lit edge, soft shadow.
- Press: scale 0.97 spring.
- Height `heightIn(min = 112.dp)`, growing with font scale (130% is fine).
- Contrast: text always sits on ≥92% opaque mist.
  - Light: ink `#1D1C1A` and inkSecondary on `#FBFAF9`, about 5.3:1 for meta.
  - Dark: mist = `canvasTop #171614`, ink `#F3F0EB`.
- Semantics:
  - merged, `Role.Button`;
  - `contentDescription` = the full destination title (for example "101 preguntas sobre la fe católica") plus the meta;
  - the art is decorative (cd null);
  - `testTag(entry.tag)` on the clickable node.
- Each card is its own LazyColumn item, so `performScrollToNode` works.

| tag | art | Title ES / EN / PT | Meta ES / EN / PT |
|---|---|---|---|
| `nav_catechism` | catechism | Catecismo / Catechism / Catecismo | El texto completo: 2865 puntos / The full text: 2,865 paragraphs / O texto completo: 2865 parágrafos (count from `CatechismOutline.totalParagraphs`, never hard-coded) |
| `nav_study_doctrine` | doctrine | Doctrina / Doctrine / Doutrina | Mandamientos, virtudes y la Didaché / Commandments, virtues and the Didache / Mandamentos, virtudes e a Didaché |
| `nav_study_mary_jesus` | mary_jesus | María y Jesús / Mary & Jesus / Maria e Jesus | Devociones a Nuestra Señora y a Cristo / Devotions to Our Lady and to Christ / Devoções a Nossa Senhora e a Cristo |
| `nav_study_history` | history | Historia / History / História | Concilios, papas y los grandes cismas / Councils, popes and the great schisms / Concílios, papas e os grandes cismas |
| `nav_questions` | questions | 101 preguntas / 101 Questions / 101 perguntas | Sobre la fe católica / On the Catholic faith / Sobre a fé católica |
| `nav_study_orders` | orders | Órdenes y oficios / Orders & Offices / Ordens e ofícios | Órdenes religiosas y oficios de la Iglesia / Religious orders and Church offices / Ordens religiosas e ofícios da Igreja |

Every title is at most 17 characters, which fits one line in the ~210dp column
at 22sp. Every meta is at most 42 characters, which fits two lines at 15sp.
These cover the longest of ES, EN and PT.

**Grouped lists** (shared GroupedList/Row)
- Glass group, radius 20, lit edge.
- Inset hairlines start at the text, not the icon.
- Leading outline glyph 22dp in ink. No wells and no colour.
- Title `body` 17/24. Subtitle `footnote` 13/18 in inkSecondary (discovery rows only).
- Chevron 16dp in inkTertiary (decorative).
- Height min 52dp, or 64dp with a subtitle.
- Semantics: merged label, `Role.Button`, `testTag(entry.tag)` on the clickable node.

| tag | glyph | Title ES / EN / PT | Subtitle ES / EN / PT |
|---|---|---|---|
| `nav_spiritual` | Outlined.FavoriteBorder (heart) | Vida espiritual / Spiritual Life / Vida espiritual | Diario, Vía Crucis y canto gregoriano / Journal, Stations and Gregorian chant / Diário, Via-Sacra e canto gregoriano |
| `nav_habits` | the **tab's own** Habits glyph (`AppIcons.pair(HABITS).first`), so it reads as the same place | Hábitos y virtudes / Habits & Virtues / Hábitos e virtudes | Tu regla de vida / Your rule of life / Sua regra de vida |
| `nav_parishes` | Outlined.Church | Parroquias / Parishes / Paróquias | Misas y confesiones cerca de ti / Masses and confession near you / Missas e confissões perto de você |
| `nav_pilgrimage` | Outlined.Place | Calendario y santuarios / Calendar & Shrines / Calendário e santuários | Días santos y peregrinaciones / Feast days and pilgrimages / Dias santos e peregrinações |
| `nav_churches_news` | Outlined.Newspaper | Noticias / News / Notícias (matches that screen's own large title) | Lo último de Vatican News / The latest from Vatican News / O mais recente do Vatican News |

**Settings** (same row, single-line, with a trailing value)

| tag | glyph | Title | Value (inkSecondary, never truncated; wraps under the title only if wider than 50%) |
|---|---|---|---|
| `app_bar_theme_button` | Outlined.Palette | Apariencia / Appearance / Aparência | Automático/Automatic/Automático · Claro/Light/Claro · Oscuro/Dark/Escuro, from `darkModeSetting`. Fallback until MainApp passes it: from `isDark`. |
| `font_settings_button` | Outlined.TextFields | Fuente / Font / Fonte | `FontChoice.label`. Must equal exactly "Inter" after the test's choice. |
| `app_bar_lang_button` | Outlined.Language | Idioma / Language / Idioma | `displayName`. Must equal exactly "English". |

- Footer: `footnote` in inkSecondary, start-aligned under the group at gutter + 16.
  - ES: "Sancta Fides {BuildConfig.VERSION_NAME} · Arte sacro de dominio público"
  - EN: "… · Public-domain sacred art"
  - PT: "… · Arte sacra de domínio público"
- No other node on the screen may equal "English", "Inter" or "Done".

**Section headers**
- Estudio/Study/Estudo; Vida interior/Inner life/Vida interior; La Iglesia hoy/The Church today/A Igreja hoje; Ajustes/Settings/Ajustes.
- Style `headline` 17/22 600, heading semantics.
- 32dp above (section gap) and 12dp below.

**Implementation notes**
- `MoreScreen` keeps its signature, adding only optional parameters.
- A private tag-keyed registry in `MoreScreen.kt` holds art, focal alignment,
  short title, meta, subtitle and glyph.
- An entry renders as a mist card if its tag is a Study featured tag. Every
  other entry renders as a row in its own section. So the screen is correct
  whatever section membership MainApp sends: during the transition News simply
  shows as a row under the Study cards.
- `MoreSection` gets an optional `footer`. `MoreEntry` gets an optional `subtitle` (defaults keep MainApp compiling).
- Tag mapping:
  - `more_screen` stays on the LazyColumn;
  - `more_section_hero_0` goes on the Study card block, which is now the section's imagery;
  - `more_section_{i}` (new) goes on each section header.
- Images load asynchronously through the shared loader with a 200ms crossfade
  from the mist placeholder, so there is no pop and no main-thread decode.
- Empty sections stay filtered.

### c3. Sheets (font, language; same chrome as Appearance)

One sheet grammar, built on an android **Dialog window**. It cannot use M3 1.3's
`ModalBottomSheet`, which is a Popup and would break `ShadowDialog`.
- 28dp top radius.
- Surface: light `#FBFAF9` (glass composite); dark `#242321` with the glass edge.
- Grabber 36×5 capsule in inkTertiary.
- Scrim black: 30% light, 45% dark.
- Motion: slide up and fade in 250ms; tap the scrim or drag down to dismiss.
- Back closes the sheet. Nav-bar inset padding. Max height 92%, content scrolls.
- Header: `title2` title at start; trailing **"Listo / Done / Concluir"** glass capsule (40dp visible, 48dp hit, ink).

**Font sheet ("Fuente / Font / Fonte")**, tag `font_picker`
1. **Preview card** (glass, radius 24, padding 20):
   - Jn 1:1 in the `reading` style 19/30, set in the selected family: "En el
     principio existía el Verbo, y el Verbo estaba junto a Dios…" (ES / EN /
     PT, same sentence as today, lengthened).
   - Tag `font_preview`: one Text node only.
   - Reference "Juan 1, 1 / John 1:1 / João 1, 1" as a separate footnote node.
2. **Choices**: a GroupedList of 4 rows, 56dp.
   - Each name is set in its own family (`body` 17).
   - Under each name, a footnote descriptor in inkSecondary:
     - Default: "Recomendada / Recommended / Recomendada"
     - Inter: "Sans moderna, muy legible / Modern sans, very legible / Sans moderna, muito legível"
     - Roboto: "La clásica de Android / The Android classic / A clássica do Android"
     - EB Garamond: "Serif clásica para leer / Classic serif for reading / Serifa clássica para leitura"
   - The selected row shows a trailing ink checkmark (Rounded.Check 20dp). No radio buttons, no colour.
   - `selectableGroup` + `selectable(role = RadioButton)`. Tags `font_choice_{id}`.
   - Each tap persists immediately (keep the serialised `pendingWrite` and the Toast).
3. **Text size** (only if the foundation owner agrees to remove it from Appearance, to avoid a duplicate control):
   - SegmentedControl "A · A · A" at increasing size, for 100 / 115 / 130%, with the % as caption.
   - Writes `setFontSizeScale` immediately.
   - `FontPicker` gains optional `scale: Float? = null, onScaleChange: ((Float) -> Unit)? = null`. The section is hidden when null, so the test call is unchanged.
4. Footer footnote: "Se aplica a la lectura y a la interfaz. / Applies to reading and the interface. / Aplica-se à leitura e à interface."

**Language sheet ("Idioma / Language / Idioma")**
- GroupedList of 3 rows, 56dp each, tags `lang_option_{code}`.
  - Native name (`body`): Español / English / Português.
  - Under it, the name in the current UI language (`footnote`), for example in EN: "Spanish", "English", "Portuguese". The duplicate is omitted on the active language's own row.
  - Trailing ink check on the current row.
  - `selectable(role = RadioButton)`. Selection is announced by semantics (no English-only "Selected").
- Flags are dropped (decoration). `AppLanguage.flag` stays in the model.
- The repeated subtitle becomes one group footer: "Las lecturas, oraciones y el
  Catecismo usan traducciones católicas oficiales. / Readings, prayers and the
  Catechism use official Catholic translations. / As leituras, orações e o
  Catecismo usam traduções católicas oficiais."
- Behaviour is unchanged: a tap applies the language (`onLanguageSelected`) and
  closes. The check moves before the sheet slides away.
- `close_language_dialog` goes on the "Listo" capsule.

**Appearance sheet** (foundation-owned; see §d4)

### c4. In-app browser chrome

New signature: `InAppBrowserDialog(url, onDismiss, currentLanguage: AppLanguage = AppLanguage.ES, readerMode: Boolean = true)`.

**Top bar**
- Full-screen Dialog, edge-to-edge, canvas background.
- Top glass bar with status-bar padding:
  - leading glass circle **Close** (cd "Cerrar / Close / Fechar");
  - centre: page title (`headline`, 1 line, ellipsis only for the untrusted page title) over the host (`footnote` inkSecondary, small lock glyph on https);
  - trailing glass circle **Open in browser** (cd "Abrir en el navegador / Open in browser / Abrir no navegador"), an ACTION_VIEW intent for the current URL.

**Loading and errors**
- A 2dp thin ProgressBar under the bar while loading, fading out at 100%.
- Back steps through `canGoBack()` first, then dismisses.
- Main-frame load error shows a designed EmptyState:
  - "No se pudo abrir la página" / "Couldn't open the page" / "Não foi possível abrir a página"
  - "Comprueba tu conexión e inténtalo de nuevo." plus EN/PT equivalents
  - PrimaryCapsule "Reintentar / Try again / Tentar de novo"
  - SecondaryCapsule "Abrir en el navegador"

**Reader mode and lifecycle**
- `readerMode = true` keeps today's CSS + textZoom, so the Catechism looks the same.
- The parish map should pass `false`, so its embed is not re-styled (request to the parish owner).
- Dark mode: on API 33+ set `isAlgorithmicDarkeningAllowed`. Best effort; verify on the phone.
- The WebView is destroyed on dispose.
- Tags: `in_app_browser`, `in_app_browser_close`, `in_app_browser_external`, `in_app_browser_progress`.

### c5. Inline back link

New signature: `InlineBackLink(label, onClick, modifier, testTag, language: AppLanguage? = null)`.

- A glass capsule at the gutter, 8dp below the content top:
  - 40dp visible, 48dp hit;
  - chevron (Rounded.ChevronLeft 20dp) plus the label in `subhead`, both ink;
  - padding 10 start, 14 end.
- No accent, no identity branch, no caps.
- `onClickLabel` = "Volver a %s / Back to %s / Voltar a %s" when `language` is
  given. Otherwise it keeps today's "Volver a %s", so callers do not have to change.
- Caller tags are preserved.

### c6. Dark mode, motion, states
- **Dark mode** is composed, not inverted:
  - hero and mist fade into `#171614 → #0E0D0C`;
  - art dimmed about 10%;
  - glass at 7% with a 14%→4% edge;
  - separators at 8%;
  - ink `#F3F0EB`;
  - sheets on `#242321`.
- **Motion**: press scale 0.97 spring (0.8 damping, 600 stiffness); 200–300ms
  crossfades for art and sheets. Nothing else.
- **States**: an art placeholder is the mist gradient; empty sections are hidden;
  the browser has loading and error states; a failed font write shows a Toast.
- **Font scale 130%**: cards and rows grow; nothing clips or truncates. The
  value column wraps under the title when it is wider than 50%.

### c7. Verification

**Renders**
- `-Pshots.only=more` in ES, EN and PT, light and dark.
- `-Pshots.height=2400` to see the whole screen.
- `FontPickerInteractionTest` output goes to `build/screenshots/fonts/*`.

**Tests**
- `AppNavigationTest`, `FontPickerInteractionTest`, `FontPreferencesTest`,
  `DesignNavigationTest`, `DesignContrastTest`.

**Phone**
- Más tab, then the Fuente, Idioma and Apariencia sheets.
- In-app browser from Catecismo, light and dark.

**Request to the orchestrator**: a small screenshot test for `LanguageSelectorDialog`
and the `InAppBrowserDialog` chrome (the WebView body will be blank in
Robolectric). I do not own test files.

---

## d. Component needs

### d1. From the foundation (design system)
1. **Edge-to-edge for tab roots with a hero.** Today MainApp pads `innerPadding`
   for every screen, which is what makes the black band. I need the top inset
   exposed (or not applied) for More, and a bottom clearance value for the
   floating bar.
2. **`HeroHeader(asset, eyebrow?, title, height, actions, modifier)`**:
   - passes `modifier` and the tag through;
   - draws a gradient fallback when the art is null;
   - gives the title heading semantics.
3. **A shared async asset image loader**, for example `rememberAssetImage(path, maxWidthPx)`:
   - decodes on IO with an LRU cache;
   - returns null on failure;
   - replaces every per-screen `decodeSampledBitmap`.
4. **`ImageCard` with a `Mist` style** (full-width, art at the end, ink text on
   mist; see §c2), alongside the scrim style for Novenas. It needs:
   - `focalAlignment`, `meta`, an optional `contentDescription`;
   - `testTag` on the clickable node;
   - press scale;
   - min height that grows with content;
   - a glass + outline-icon fallback when there is no art.
5. **`GroupedList` / `Row`** (the existing `AppListRow` signature is enough if the Lumen implementation provides):
   - optional subtitle;
   - a trailing value that never truncates;
   - ink outline icon at 22dp, chevron in inkTertiary;
   - min height 52 / 64;
   - merged semantics with `Role.Button`;
   - `selected` drawn as a trailing ink check with `Role.RadioButton` and `selectable` semantics (for the sheets).
6. **`SectionHeader`** with heading semantics, plus a **`SectionFooter`** (footnote, inkSecondary).
7. **`AppSheet(title, onDismiss, modifier/tag, trailing = Done capsule, content)`**,
   built on `androidx.compose.ui.window.Dialog` (ShadowDialog must still see
   it). If it is not ready, I will build `internal LumenSheet` in
   `FontPickerDialog.kt` and share it with the language sheet. The Appearance
   sheet should then adopt it.
8. **Smaller components:**
   - `GlassCircleButton` (40dp, 48dp hit);
   - `SegmentedControl`;
   - thin `ProgressBar`;
   - `EmptyState`;
   - `Primary/SecondaryCapsule`, including a compact 40dp capsule for "Done";
   - `Modifier.pressScale()`.
9. **Text styles** by the DESIGN names: display, largeTitle, title2, headline,
   body, subhead, footnote, caption, and `reading` (the reader font, respecting
   `FontChoice`).

### d2. MainApp changes (foundation-owned; exact asks)
1. `AppDestination.MORE` title becomes "Explorar / Explore / Explorar". `tabLabel` stays "Más / More / Mais".
   This makes the scrolled inline title and pushed back labels ("‹ Explorar") match.
2. `CHURCHES_NEWS` title becomes "Noticias / News / Notícias" (the screen is news only).
3. Separately, flag that ParishFinder's "Directorio de iglesias" (`onOpenDirectory`)
   lands on that news-only page (bug for the parish/foundation owners).
4. `moreSections()`:
   - Study = catechism, study_doctrine, study_mary_jesus, study_history, questions, study_orders;
   - "Vida interior / Inner life / Vida interior" = spiritual, habits;
   - "La Iglesia hoy / The Church today / A Igreja hoje" = parishes, pilgrimage, churches_news;
   - drop the `AutoStories` icon override (More owns its glyphs).
5. Pass the Appearance `value` from `preferences.darkModeSetting`:
   - SYSTEM → Automático / Automatic / Automático;
   - LIGHT → Claro / Light / Claro;
   - DARK → Oscuro / Dark / Escuro.
   A shared `darkModeLabel()` helper would be ideal.
6. Sentence case for ES/PT destination titles: "Calendario y santuarios" and "Hábitos y virtudes" (optional).

### d3. Data model
- `FontChoice.IDENTITY_DEFAULT.label` becomes "Predeterminada / Default / Padrão",
  because identities are hidden. The ids are unchanged.

### d4. Appearance sheet (`GuiCustomizerModal`): how it should look

It should use the same `AppSheet` chrome, apply changes instantly (no "Guardar"),
and close with the "Listo" capsule.

1. **Modo / Mode / Modo**:
   - SegmentedControl Automático | Claro | Oscuro (PT Escuro), calling `setDarkModeSetting`;
   - optional light/dark mini previews.
2. **Color litúrgico / Accent / Cor litúrgica**:
   - four 28dp swatches with a glass ring on the selected one, labelled Litúrgico / Monástico / Borgoña / Mariano (EN/PT equivalents), calling `setThemePreset`;
   - footnote: "Litúrgico sigue el tiempo del año.";
   - keep the `theme_preset_*` tags.
3. **Remove from this sheet:**
   - the identity rows (already hidden; keep the pref data and the `design_language_*` tags only if anything still needs them);
   - the font button (More › Fuente is the one door; this avoids a sheet over a sheet);
   - reading size, if it moves to the Font sheet as in §c3.
4. **Narration speed**: either wire it into `LiturgicalTtsManager` or hide it. A
   persisted setting with no effect is a false control. Translate the labels if
   it stays.
5. **Tags**: keep `gui_customizer_sheet`. `save_preferences_button` can move to
   "Listo". `saveAppearance(updateFontFamily = false)` stays valid for `FontPreferencesTest`.

### d5. Other owners
- The Catechism and Parish screens should pass `currentLanguage` to `InAppBrowserDialog`.
- The Parish screen should also pass `readerMode = false`.
- The Prayers and Doctrine screens may pass `language` to `InlineBackLink`. All defaults keep today's call sites compiling.
