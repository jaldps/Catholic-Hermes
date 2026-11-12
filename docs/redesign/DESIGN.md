# Lumen — the Catholic Hermes redesign

Branch `ui-redesign-v2`. This file is the single source of truth for every
agent working on the redesign. Read it fully before touching code.

North star: `docs/redesign/reference.png` (crops per screen in
`docs/redesign/reference/`). It is inspiration, not a spec to trace: we keep
the owner's bottom bar, the 2-column novena grid and the rosary mechanics,
and we cover every screen of the app, including the ones the reference never
drew (Bible, Catechism, Doctrine, Study, Explore, Spiritual life, Parishes,
Pilgrimage, News, Reminders, Settings, dialogs).

The bar is Apple first-party, iOS 26 era: Apple Music, Fitness, Journal,
Photos, Weather. A design lead at Apple would ship every screen as is. "Fine"
is a failure. A reskin is a failure: re-think hierarchy, layout and flow.

The "before" state is in `docs/redesign/before/` (phone, dark mode).

## Principles

1. **Light is the material.** A warm off-white canvas, misty photography
   that dissolves into it, frosted glass surfaces with a soft lit edge. No
   hard borders, no saturated icon wells, no heavy dividers, no flat grey
   boxes.
2. **Type carries hierarchy.** Large regular-weight titles with tight
   tracking, generous leading, few sizes. Bold is rare.
3. **One accent at a time.** The liturgical season colour appears as a dot,
   a ring or a thin bar. The primary action is a charcoal capsule (ivory in
   dark mode). Never green buttons, never multicolour.
4. **One clear task above the fold.** Each screen opens on what you came for.
   Secondary material is below or one tap away.
5. **Art is devotional, not decorative.** Classical sacred art and soft
   photography, treated with a warm mist so it belongs to the canvas.
   Illustrations stay clipped in their frame and never sit under text
   without a scrim.
6. **Nothing is lost.** Keep all real content, data, progress, values,
   accessibility labels, deep links and `testTag`s. Remove only redundant
   decoration and duplicate titles/controls.

## Tokens

Implement these in `ui/theme/DesignSystem.kt` (palette, shapes, spacing,
depth) and `ui/theme/AppTypography.kt`. Screens must read tokens, never
hard-code colours or sizes.

### Colour — light

| Token | Value | Use |
|---|---|---|
| canvasTop / canvasBottom | `#F6F4F1` → `#ECE9E5` | full-screen vertical gradient |
| canvas (flat) | `#F2F0ED` | fades, bars |
| glass | `#FFFFFF` @ 72% | cards, tiles, list groups, chips |
| glassStrong | `#FFFFFF` @ 88% | segmented thumb, floating controls |
| glassEdge | `#FFFFFF` @ 95% top → 35% bottom, 1dp | lit edge on every glass surface |
| shadow | `#3B2F22` @ 8%, y 10dp, blur ~30dp | glass lift (spot + ambient colour) |
| separator | `#000000` @ 6% | inset hairlines in lists |
| ink | `#1D1C1A` | titles, body |
| inkSecondary | `#6B6661` | subtitles, meta (≥4.5:1 on canvas) |
| inkTertiary | `#9C968F` | placeholders, disabled (decorative only) |
| action / onAction | `#2B2A28` / `#FFFFFF` | primary capsule, selected chip |
| success | `#43A047` | habit checks |
| ring | `#CFE6C6` → `#4E9F4A` | progress ring gradient |

Liturgical accents (dot/ring/bar only): Ordinary `#3E9B4F`, Advent/Lent
`#7B5EA7`, Christmas/Easter `#C8A24A`, Martyrs/Pentecost `#C0392B`,
Gaudete/Laetare `#D48AA0`.

### Colour — dark ("night chapel")

| Token | Value |
|---|---|
| canvasTop / canvasBottom | `#171614` → `#0E0D0C` |
| glass | `#FFFFFF` @ 7% |
| glassStrong | `#FFFFFF` @ 12% |
| glassEdge | `#FFFFFF` @ 14% top → 4% bottom |
| shadow | `#000000` @ 45% |
| separator | `#FFFFFF` @ 8% |
| ink / inkSecondary / inkTertiary | `#F3F0EB` / `#A8A29B` / `#77726C` |
| action / onAction | `#F3F0EB` / `#171614` |

Dark mode must be as carefully composed as light, not an inversion: images
fade into the dark canvas, glass is subtle, nothing glows.

### Type (Inter for UI; the user's reader font for long-form)

| Style | Size/line | Weight | Tracking | Use |
|---|---|---|---|---|
| display | 44/48 | 400 | -1.0 | hero title on tab roots ("Hoy") |
| largeTitle | 34/40 | 400 | -0.6 | tab root titles without hero |
| title1 | 28/34 | 400 | -0.4 | detail titles ("Misa de hoy") |
| title2 | 22/28 | 500 | -0.2 | step/prayer titles |
| headline | 17/22 | 600 | -0.2 | section headers ("Fundamentales") |
| body | 17/24 | 400 | -0.2 | rows, body copy |
| callout | 16/22 | 400 | -0.1 | card body |
| subhead | 15/20 | 400 | 0 | card subtitles |
| footnote | 13/18 | 400 | 0 | meta, secondary |
| eyebrow | 13/18 | 500 | 0 | date above a hero title (sentence case) |
| caption | 12/16 | 500 | 0 | tile labels, chip meta |
| reading | reader font 19/30 (serif) · 18/28 (sans) | 400 | 0 | scripture, prayers, meditations |

The font picker must keep working for reading text and UI.

### Shape, space, depth

- 4dp grid. Screen gutter **20dp**. Section gap 28–32dp. Card padding 20dp.
- Radii: card 24, list group 20, tile 20, image card 22, sheet 28,
  chips/CTA/search = capsule. One radius per element type, everywhere.
- Touch targets ≥ 44dp. Rows ≥ 52dp. CTA capsule 52dp tall.
- Glass circle buttons 40dp (44dp hit area) for back, close, search, bell,
  bookmark, more. Glyph 20dp, ink colour.
- Icons: thin outline (Material `Outlined`/`Rounded` at 22–24dp), one ink
  colour. Filled glyphs only for a selected state.
- Motion: press scale 0.97 with spring (0.8 damping, 600 stiffness),
  200–300ms cross-fades, no gratuitous animation.

## Components (in `AppleKit.kt` behind `AppKit`, wrapped in `AppComponents.kt`)

Extend the `AppKit` contract where needed instead of building parallel
systems. Required building blocks:

- **Canvas**: gradient background, edge-to-edge. Content draws under the
  status bar; status bar icons dark on light, light on dark. No black band.
- **GlassSurface**: fill + lit edge + soft shadow; the base of cards, tiles,
  groups, chips, bars.
- **HeroHeader(asset, eyebrow, title, height, actions)**: full-bleed image
  from the very top of the screen, mist treatment (light: lift + slight
  desaturation + fade to canvas over the lower ~55%; dark: fade to dark
  canvas), title `display` bottom-start at the gutter, eyebrow above.
  Glass circle actions top-right. One shared implementation; delete the
  per-screen `decodeSampledBitmap` copies in favour of it.
- **DetailHeader**: same as hero with a glass back button top-left and an
  optional bookmark/more top-right; title `title1` below the image fade.
- **LargeTitleHeader**: `largeTitle` + optional glass action at the right,
  for tab roots without art.
- **SeasonCard**: glass row, season dot, season name (headline) + week
  (footnote), chevron.
- **ContentCard**: glass card, leading outline icon, title/subtitle, body,
  optional trailing round glass play button.
- **CategoryTile** grid: 3 columns, square-ish glass tiles, 26dp outline
  icon over a caption label.
- **GroupedList / Row**: glass group, inset hairlines, chevron, optional
  leading icon and trailing value.
- **FilterChips**: capsules; selected = action fill.
- **ImageCard**: photo/art full-bleed, bottom scrim, title + meta bottom-start
  in white; optional progress bar. Used 2-up for novenas.
- **PrimaryCapsule** / **SecondaryCapsule** buttons.
- **TransportBar**: glass capsule with previous / play-pause (larger, glass
  strong) / next.
- **ProgressRing** (gradient) and thin **ProgressBar**.
- **CheckRow**: circle check (success fill when done) + label.
- **StepList**: numbered glass circles + step text.
- **SegmentedControl**: glass track, glassStrong thumb with shadow.
- **SearchField**: glass capsule with magnifier.
- **TopBar on scroll**: transparent at rest; when the title scrolls away a
  blurred glass bar with an inline 17sp title fades in.

**Bottom bar: keep the owner's current floating 6-tab bar exactly**
(labels, order, selected pill, floating position). The only change allowed
is matching its material to the new glass tokens.

## Screen blueprints

These set direction. Each screen agent owns the details and must make them
excellent.

- **Home (Hoy)**: hero (sacred interior/light image), eyebrow = full date,
  display "Hoy", bell → reminders. Season card overlapping the hero fade.
  Then Gospel of the day card (excerpt + play/listen), Mass of the day,
  verse of the day, prayer of the hour, fidelity ring summary, saint of the
  day (image card), question of the day. Keep every current feature and
  deep link; fix the order and hierarchy.
- **Prayers**: large title + search; segmented Prayers / Novenas / Night;
  category tiles 3×2; "Fundamentals" grouped list; seasonal prayer; How to
  pray (step list screen).
- **Novenas**: filter chips (All, Popular, Saints, Mary, …); "In progress"
  first; **2-column ImageCard grid** with each novena's own art.
- **Novena detail**: art hero, back/bookmark, title1, "9 días" meta,
  description, day list with progress, PrimaryCapsule "Comenzar/Continuar".
- **Prayer detail**: art hero, title1, reading text, bottom glass action row
  (share, listen, text size).
- **Rosary / Divine Mercy**: keep the owner's mechanics (bead rail, single
  transport, mysteries picker, persistent narration, step text) but make it
  rich: the current mystery's classical painting as the stage, "Paso N de 85"
  + thin progress bar, prayer title2 + reading text, glass TransportBar,
  mysteries picker capsule. Never empty-looking.
- **Habits**: large title + stats action; big ProgressRing "X/12 Hábitos de
  hoy" with sprout; CheckRows grouped Morning / Day / Night; monthly
  fidelity calendar; temptation card; reminders entry.
- **Bible**: large title; continue-reading card with progress; search;
  segmented Bible / Mass / Study; testament sections as grouped lists.
  Reader: calm reading page, glass top bar with book/chapter, glass bottom
  bar (prev, listen, Aa, next).
- **Explore (Más)**: large title; featured image cards for main sections;
  grouped lists with outline icons; settings entry.
- **Study / Catechism / Doctrine / Mary & Jesus / History / Orders /
  101 Questions / Didache / Spiritual life / Parishes / Pilgrimage /
  News / Reminders / Settings & dialogs**: same grammar — DetailHeader or
  LargeTitleHeader, glass groups, ImageCards where art exists, reading
  typography for long text.

## Quality checklist (critics score against this)

Gutter and alignment exact · one radius per element type · no truncation or
awkward wraps (ES/EN/PT) · body contrast ≥ 4.5:1 · targets ≥ 44dp · no
clipped content · no duplicate titles/controls · consistent icon stroke and
size · images sharp, well cropped, mist-treated · vertical rhythm on the
4dp grid · dark mode as composed as light · loading/empty states designed ·
the screen's primary task obvious in one second.
