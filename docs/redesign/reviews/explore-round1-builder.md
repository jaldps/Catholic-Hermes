# Explore / Settings — round 1 (builder)

Plan: `docs/redesign/plans/explore.md`. Owner files:
- `ui/screens/MoreScreen.kt`
- `ui/components/FontPickerDialog.kt`
- `ui/components/LanguageSelectorDialog.kt`
- `ui/components/InAppBrowser.kt`
- `ui/components/InlineBackLink.kt`
- new: `ui/components/ImageCredits.kt`

## What changed

### Explore (`MoreScreen`)
The screen is edge to edge (`AppEdgeToEdge`), so the black band is gone. It reads top to bottom:

1. **Hero.** `AppHeroHeader` with Friedrich's *Meadows near Greifswald* (`heroes/lumen/explore.jpg`, mist treatment) and the display title "Explorar". It keeps tag `more_hero_photo`; the title is tagged `more_title`.
2. **Estudio.** Six full-width *mist* art cards in the reference's novena style:
   - the painting fills the trailing 70%, cropped at the focus point in `manifest.json`;
   - it dissolves into opaque glass on the leading side, where the title (`title2`) and a one-line promise (`subhead`) sit in ink;
   - radius 22, lit glass edge, soft shadow, press scale 0.97;
   - the Catechism count comes from `CatechismOutline.totalParagraphs`;
   - each card's spoken label is the full destination title plus its promise.
3. **Vida interior** (Vida espiritual, Hábitos y virtudes) and **La Iglesia hoy** (Parroquias, Calendario y santuarios, Noticias) are glass grouped lists:
   - outline glyphs, 22dp, in ink;
   - a footnote line under each title;
   - inset hairlines, chevron.
   - News moved out of Study.
   - The Habits row uses the Habits tab's own glyph.
4. **Ajustes**: Apariencia, Fuente, Idioma, plus a new **Créditos de las imágenes** row (value = number of works).
   - Values sit in `body` in inkSecondary and are never truncated.
   - A centred version footer follows ("Sancta Fides · Versión 1.4", from `BuildConfig`).
5. **Layout rules.** The layout comes from entry tags, not from MainApp's section order. Unknown entries keep their caller's section, so nothing can be dropped. Photo strips, the private bitmap decoder and the identity-based Appearance value were removed.

### Font sheet (`FontPicker`)
- Shown in the foundation's `AppSheet`, which is a Dialog window, so `ShadowDialog` still sees it.
- Centred title with an ink "Listo" at the trailing edge.
- A glass preview card shows John 1:1 in the `reading` style, set in the chosen family, with the reference underneath. The `font_preview` text is a single node.
- Then a "Tipografía" group: each family set in itself, with a one-line description and an ink check.
  - Radio semantics; rows are 56dp.
  - "Identity default" is now called "Predeterminada" in this sheet.
- A footnote says the text size lives in Appearance, so there is no duplicate control.
- Tag signature and persistence are unchanged.

### Language sheet
- Same chrome as the font sheet.
- Each row shows the native name, with the name in the current UI language underneath. There are no flags.
- Ink check, radio semantics.
- The repeated "official translations" line now appears once, as a footnote.
- Tapping a language applies it and closes the sheet, as before.
- Tags `lang_option_*` and `close_language_dialog` are kept.

### Image credits sheet (new)
- The list is read from `heroes/lumen/manifest.json` and `heroes/mysteries/manifest.json`, the same record as `IMAGES.md` (42 works).
- Two groups: "Secciones de la app" and "Misterios del Rosario".
- Each row: a 44dp thumbnail decoded off the main thread, the title, and artist · year · licence (licence localised).
- Tapping a row opens the work's Commons page in the in-app browser.

### In-app browser
- Fully localised (ES/EN/PT).
- Glass bar:
  - close circle on the left;
  - the page title over its host, with a lock icon on https;
  - "Abrir en el navegador" circle on the right.
- A hairline progress bar shows while loading.
- Back steps through the page's history before closing.
- Designed error state: icon, title, hint, "Reintentar" capsule and "Abrir en el navegador".
- `readerMode` (default true) keeps the old legibility CSS; the map and Commons pages pass false.
- Dark mode uses algorithmic darkening on API 33+. The WebView is destroyed on release.

### Inline back link
- A glass capsule: chevron plus label, both in ink.
- No accent colour and no identity branch.
- Optional `language` parameter for a localised spoken action. Callers are unchanged.

## Renders / phone
(filled in below after the runs)

## Known gaps
(filled in below)
