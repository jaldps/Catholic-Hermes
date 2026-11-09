# Lumen components — what screen agents code against

Package `com.example.ui.design`. Tokens: `LocalAppDesign.current` (`.palette`,
`.shapes`, `.spacing`) and `LocalAppTextStyles.current`. Never hard-code
colours or sizes. Lumen is the only identity (`appKit()` → `LumenKit`); both
`DesignLanguage` values render it and the identity picker is gone.

## Tokens added

`AppPalette` (light/dark per DESIGN.md): `canvasTop`, `canvasBottom`, `glass`,
`glassStrong`, `glassEdgeTop/Bottom`, `shadow`, `separator`, `action`,
`onAction`, `success`, `ringStart/End`, `inkQuiet` (decorative only). Existing
roles still work: `textPrimary/Secondary/Tertiary` (all ≥4.5:1), `accent`
(liturgical ink, ≥4.5:1, = `MaterialTheme.colorScheme.primary`), `canvas`,
`groupedSurface`/`raised` (= glass composited, opaque).

`AppTextStyles` adds: `display` 44, `largeTitle` 34, `title1` 28, `title2` 22
medium, `title3` 20 semibold (section headers), `headline` 17 semibold, `body`
17, `callout` 16, `subhead` 15, `footnote` 13, `eyebrow` 13 medium, `caption`
12 medium, `reading` (reader font 19/30 serif or 18/28 sans). Old names
(`screenTitle`, `rowTitle`, `scripture`…) map onto these.

Shapes: card 24, listGroup/tiles 20, image card 22, buttons/chips/fields
capsule. Spacing: gutter 20, sectionGap 28, cardPadding 20, rowMinHeight 52.

## Screen frame (edge-to-edge) — read this first

The scaffold draws the canvas gradient, the owner's floating dock and an
overlaid top bar (a glass back circle when pushed, plus an inline title
that fades in on scroll).

- **Legacy behaviour (default):** your screen is padded below the status bar
  (+52dp back row when pushed) and above the dock. Nothing to do.
- **Edge-to-edge (redesigned screens):** call `val insets = AppEdgeToEdge()`
  once at the top of the screen composable, not inside a lazy item. The
  screen then fills the whole display. Pad scroll content with
  `contentPadding = PaddingValues(bottom = insets.calculateBottomPadding() + 24.dp)`
  so the last item clears the dock and content scrolls under the glass dock.
  `AppHeroHeader` handles the top itself. Without a hero, use
  `AppLargeTitleHeader` / `AppDetailHeader`, which add `insets` top. Use
  `appScreenInsets()` when you need the insets inside a child composable.
- **Back:** never draw your own back link. For an in-screen detail (novena,
  prayer, chapter, question) call `AppBackHandler(enabled = detailOpen) { close() }`.
  The single glass back button top-left (tag `nav_back`) and system back
  both call it. The innermost registration wins. `InlineBackLink` still
  compiles; replace it with `AppBackHandler`.

## Headers

```kotlin
AppHeroHeader(assetPath = "heroes/lumen/home.jpg", title = "Hoy", eyebrow = "Domingo, 8 de noviembre",
    height = 340.dp, titleTag = "home_title") {
    AppGlassIconButton(Icons.Outlined.Notifications, "Recordatorios", onClick = …, onImage = true)
}
```
Tab-root hero from the top of the display, with mist treatment, the focus point
from `manifest.json`, `display` title bottom-start and glass actions top-right.
It declares edge-to-edge itself, but still call `AppEdgeToEdge()` at the screen root.

```kotlin
AppLargeTitleHeader(title = "Oraciones", eyebrow = null) { AppGlassIconButton(Icons.Outlined.Search, "Buscar", { … }) }
AppDetailHeader(title = "Novena a la Divina Misericordia", assetPath = "heroes/mysteries/divine_mercy.jpg",
    // or imageUrl = novena.imageUrl
    meta = "9 días", metaIcon = Icons.Outlined.CalendarMonth, imageHeight = 300.dp) { /* bookmark */ }
```
`AppScreenTitle(title, subtitle)` still exists (largeTitle, short subtitle →
eyebrow) for titles inside content. `AppSectionHeader(title, subtitle)` =
`title3`; inside an `AppCard` it becomes a `headline` card header.

Art helpers: `AppHeroImage(assetPath, modifier, fadeToCanvas, fadeStart, topVeil)`
(the treated image alone), `rememberAssetBitmap(path, maxWidthPx)`,
`rememberHeroMeta(path)` (`.alignment`, `.tone`) and
`rememberFirstAsset("heroes/lumen/x.jpg", "heroes/old.jpg")`, which falls back
while the art isn't bundled yet. These replace every per-screen
`decodeSampledBitmap` copy.

## Surfaces & cards

- `AppGlassSurface(modifier, shape = card, strong = false, lifted = true, onClick, contentPadding) { }`
  is the base glass. `Modifier.lumenGlass(shape, strong, lifted)` and
  `Modifier.lumenPress(onClick, scale)` are available for custom pieces, and
  `Modifier.softShadow(shape, color, blur, offsetY)` gives a CSS-like shadow.
- `AppCard(onClick) { }`: a glass card with 20dp padding.
- `AppSeasonCard(season, detail, seasonColor, onClick, tag)`: dot + headline + subhead + chevron.
- `AppContentCard(title, icon, subtitle, onClick, tag, trailing = { AppRoundPlayButton(...) }) { body }`.
- `AppImageCard(title, onClick, assetPath | imageUrl, eyebrow, meta, progress, aspectRatio = 0.8f, tag)`:
  art, bottom scrim and white type. Use 2-up for novenas:
  `AppGrid(novenas, columns = 2) { n, m -> AppImageCard(..., modifier = m) }`.
- `AppCategoryTile(label, icon, onClick, selected, tag)` in `AppGrid(items, columns = 3)`.
  `AppGrid` is non-lazy and applies the gutter itself.
- `AppBanner(title, body, accentColorHex, onClick)`: a season-dot glass row.
  `AppMetricTile`, `AppEmptyState`.

## Lists

- `AppListGroup { AppListRow(...) }`: a glass group with inset hairlines and a chevron when `onClick` is set. A leading icon is outline, ink colour, with no wells.
- `AppCheckRow(label, checked, onToggle, subtitle, trailingText, onOpen, showDivider, tag, checkTag)`:
  tapping the circle toggles; tapping the text calls `onOpen`. Put it inside `AppListGroup`, and pass `showDivider = false` on the last row.
- `AppStepList(steps, currentStep, onStepClick)`: numbered circles.

## Controls

- `AppPrimaryCapsule(label, onClick, icon, enabled, fillWidth = true, tag)`: 52dp, charcoal/ivory.
  `AppPrimaryButton` maps to it (not full-width).
- `AppSecondaryCapsule(...)`: glass capsule. `AppSecondaryButton` maps to it.
- `AppGlassIconButton(icon, contentDescription, onClick, onImage, tint, tag)`: 40dp circle with a 44dp target.
- `AppRoundPlayButton(playing, onClick, contentDescription, tag)`.
- `AppSegmentedTabs(options, selected, onSelect)`: a glass track with a sliding white thumb.
- `AppFilterChips(options, selectedIndex, onSelect, tagFor)` scrolls horizontally with gutter margins;
  `AppChip`/`AppKitChip` is a single capsule (selected = action fill).
- `AppSearchField(value, onValueChange, placeholder)`: a glass capsule with gutter padding.
- `AppTransportBar(isPlaying, onPlayPause, onPrevious, onNext, labels…, tagPrefix)`:
  tags are `<prefix>_previous|_play|_next`.
- `AppProgressRing(progress, size = 168.dp, stroke = 12.dp) { center }` and `AppProgressBar(progress, color, track, height)`.
- `AppActionBar { AppActionBarItem(icon, description, onClick, tag, active) }`: bottom glass capsule (share / listen / Aa).
- `AppSeasonDot(color)`, `AppChevron()`.

## Rules of thumb

The screen gutter is 20dp, and `AppListGroup`/`AppSearchField`/`AppSegmentedTabs`/`AppFilterChips`/`AppGrid`
apply it themselves. For cards, wrap with `Modifier.padding(horizontal = design.spacing.gutter)`.
Leave `sectionGap` (28dp) between sections. Use outline icons at 22–28dp in ink colour.
Keep every existing `testTag`.

## Phase A.2 — `ui/design/LumenExtras.kt`

- **Chrome:** `AppHideTabBar(hidden = true)` hides the floating dock while composed (use it in immersive readers);
  `appDockInset()` gives the bottom padding that clears the dock, or only the nav bar when the dock is hidden.
  `AppInlineTitle(title)` replaces the scaffold's scrolled inline title; call it at the root of an in-screen detail.
  `AppStatusBarGlyphs(lightGlyphs)` forces status-bar glyph colour over dark art.
  For a **pushed screen that draws its own DetailHeader**, call `AppEdgeToEdge()` and let `AppDetailHeader` handle the top.
  The single glass back button (tag `nav_back`) floats over your art.
- **Glyphs & colour:** `LumenGlyphs.cross`; `lumenLiturgicalColor(name, hex)` maps to the DESIGN.md accents.
- **Grouped rows across lazy items:** `AppGroupedRow(title, position = GroupPosition.First|Middle|Last|Single, subtitle,
  leadingIcon, trailingText, onClick, selected, tag, trailing)`, or `Modifier.groupedItem(position)` for custom content.
- **Toggles:** `AppSwitch(checked, onCheckedChange, tag)` (green when on); `AppToggleRow(title, checked, onCheckedChange,
  subtitle, leadingIcon, showDivider, tag, switchTag)` inside `AppListGroup`.
- **Search:** `AppSearchBar(value, onValueChange, placeholder, onSubmit, tag)` has a clear button and IME search.
- **Sheets & menus:** `AppSheet(onDismiss, title, tag) { … }` is a glass bottom sheet in its own **Dialog window**
  (works with ShadowDialog tests). `AppMenu(expanded, onDismiss, items = listOf(AppMenuItem(label, icon, selected, tag) { … }))`;
  `AppMenuChip(label, items, icon, tag)` is a capsule with ⌄ that opens the menu (e.g. a mysteries picker).
  `AppConfirmDialog(title, message, confirmLabel, dismissLabel, onConfirm, onDismiss, destructive, tag)`.
- **Progress & art:** `AppSegmentedProgress(count, current, fraction)`; `AppCrossfadeArt(assetPath)` (mystery paintings);
  `AppRemoteImage(imageUrl, fallbackAssetPath)` (remote art with a bundled fallback).
- **Reading settings:** `AppReadingSettingsSheet(currentScale = prefs.fontSizeScale, currentFontId = prefs.fontFamilyChoice,
  onDismiss, labels)` is the shared "Aa" sheet. It writes `UserPreferencesStore` (via `LocalPreferencesStore`), and the whole
  app re-themes (reader font + text scale). Pair it with `AppActionBarItem(Icons.Outlined.TextFields, "Aa", …)`.

### MainApp changes

- A real back stack, saved across rotation: pushing from a pushed screen pops back to it (101 Questions → Catechism → back),
  and the tab highlight follows the bottom of the stack.
- New `AppDestination.REMINDERS` (RemindersScreen, `onBack = pop`), opened from Home's bell.
- More is titled "Explorar"; its tab label stays "Más". CHURCHES_NEWS is titled "Noticias".
- Appearance shows the light/dark mode value. More → 101 preguntas clears the daily-question deep link.
- Home "Iglesias" → CHURCHES_NEWS. Doctrine owner: when you add `initialChurchesSection`, ask the foundation owner to pass it.
- Pushed screens don't need an `onBack` param: the scaffold's glass back pops the stack, and `AppBackHandler` overrides it
  for in-screen details. If you add an `onBack` param anyway, the foundation owner will wire it to `pop()`.
  `PilgrimageAndHabitsScreen(initialTab)` will be wired once the life owner adds the param.

**Reading slot note:** screens that let the reader pick a font override `AppTextStyles.scripture` (BibleScreen does).
`AppVerseRow` renders `scripture`, so use `styles.scripture` for anything that must follow a per-screen reader-font override.
`styles.reading` is the app-wide default and equals `scripture` unless a screen overrides it.

## Round 2 foundation changes (home + foundation owner)

All source-compatible: no signature lost, new parameters are defaulted and
placed so existing named/trailing-lambda calls compile unchanged.

### Chrome (the owner's 6-tab dock, layout/labels/order unchanged)

- **Material:** a near-opaque warm sheet, not see-through. Light
  `#F5F3F0 → #F0EEEA` @ 94%, dark `#252321 → #1E1C1A` @ 92%, 1dp lit edge,
  soft shadow. Content never ghosts through it.
- **Labels:** one line, 11sp / -0.2 tracking, one shared size for all six tabs
  that steps down by 0.25sp to a 9.5sp floor if any label would overflow its
  slot ("Oraciones" no longer clips at any font scale / language).
- **Icons:** one outline family at 22dp in ink; the selected tab shows its
  filled twin on a quiet ink pill (`ink @ 7%` light, `white @ 12%` dark).
  No accent colour in the bar any more (the green fill/label/pill is gone).
- **Content-under-bar fade:** the scaffold draws a canvas fade over the last
  20dp above the dock and a quiet canvas behind it (hidden with
  `AppHideTabBar`). Screens still pad list bottoms with the dock inset from
  `AppEdgeToEdge()` — **add 32dp** (not 24) so the last row ends clear of the
  fade.

### Glass and depth

- `lumenGlass` now has a faint sheen (fill 112% at the top → 90% at the
  bottom), and in light mode a tight contact shadow next to the wide lift
  shadow, so edges are defined instead of a blurry halo.
- Light `glassEdgeBottom` is now a warm keyline (`#3B2F22 @ 12%`); the edge
  reads white at the top and falls to a warm hairline at the bottom.
  Dark edge tokens are unchanged (14% → 4%).
- `AppGlassIconButton(onImage = true)` in dark: 12% white glass over a faint
  22% black backing, **no shadow**. Dark glass never casts a shadow.
- `AppPrimaryCapsule`: 8% warm shadow in light, none in dark.

### Heroes

- `AppHeroImage`: dark mode is graded about -0.6 EV with a warm cast (+R/-B)
  and less saturation (art whose manifest `tone` is `"dark"` gets a milder
  grade); top scrim is black 35% → 0 over 120dp; the fade to canvas is
  parametric in `fadeStart` and reaches 88% canvas at `fadeStart + 58%` of the
  remainder (so the eyebrow line sits on >= 85% canvas). Light: 15% less
  saturation, lifted blacks, 16% canvas mist.
- **Manifest zoom/anchor (new, optional):** a `manifest.json` entry may set
  `zoom` (> 1 crops tighter than "cover" around the focus point),
  `anchorX`/`anchorY` (where in the hero box the focus point lands, 0..1).
  `HeroMeta` carries them; entries without them behave exactly as before.
  `home.jpg` uses `zoom 1.3`, anchor (0.74, 0.40): the cross sits on the right
  third.
- `AppHeroHeader(..., overlap: Dp = 0.dp, actions)`: new `overlap` lets the next
  sibling (the season card) ride up over the hero's fade: the hero reports
  `overlap` less height and lifts its title clear. Eyebrow is 82% ink in dark.
  Actions are placed so a 40dp glass circle sits 8dp under the status bar on
  the 20dp gutter.

### Season card, dot, chevron

- `AppSeasonCard`: compact (~68dp) row: flat **8dp** dot centred on the cap
  height of the season name (headline), the detail on **one** footnote line
  under the name (aligned with the name, ellipsized, never wraps), chevron in
  `inkQuiet`. Callers should pass a short detail ("Domingo XXXII · Precepto de
  Santa Misa"): do not repeat the season name in it.
- `AppSeasonDot` default size is now 8dp (was 10dp); `AppChevron` unchanged.

### Glyphs

- `DevotionalGlyphs.rosary*` redrawn: a loop of beads open at the bottom with a
  medal and a Latin cross hanging from it (it used to read as a magnifier).
- New `DevotionalGlyphs.sunriseOutlined` / `sunriseFilled` (the Angelus hour),
  same stroke weight as Material outlined. Home uses it for the Ángelus rows.

### Type scale roles (unchanged tokens, now applied consistently on Home)

eyebrow 13/500 inkSecondary (date, card eyebrows) · card title headline 17/600
· section header title3 20/600 with **32dp above** (use `Spacer(22.dp)` then
`AppSectionHeader`, which adds 10dp) and 12dp to its group · reading text
`styles.reading` 19/30 serif for scripture/verse (3 lines max in cards) ·
callout 16/22 for card body · footnote 13 for meta/hints (inkSecondary, never
an accent colour).

### Spacing rhythm used on Home (recommended elsewhere)

12dp between cards of one cluster, 32dp between sections, 12dp section header
→ group, 24dp season-card overlap into the hero fade, 20dp card padding,
list-end clearance = dock inset + 32dp.

### MainApp wiring

- `HomeScreen(onNavigateToShrines)`: new, defaulted. Home's "Santuarios" tile
  opens `PilgrimageAndHabitsScreen(initialTab = 1)`; the season card opens
  tab 0. `PILGRIMAGE_HABITS` from More resets to tab 0.
- `DoctrineAndHistoryScreen(initialChurchesSection)` does **not exist yet**:
  Home's "Iglesias" tile still opens CHURCHES_NEWS. Wire it when the doctrine
  owner adds the parameter.
- Home reads the habits owner's `DailyRule` (ids, goal 12, `countsFrom`) and
  `FidelityCalendar(framed = false, dailyGoal = DailyRule.size)`.
