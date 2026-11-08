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
