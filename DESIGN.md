# Visual identity system — Sancta Fides

This project offers three deliberately different visual identities. They share functionality, language support and accessibility semantics, but should not look like recolors of one template.

## Identity selection

The selected identity is persisted in `app_preferences.themePreset`. `AppThemePreset.normalizeId` maps older saved preset IDs to the Vatican identity, so existing installs keep a valid appearance. Each identity supports explicit light, dark and system modes.

### Apple — Human Interface-inspired

**Intent:** quiet, content-led, familiar to an iOS user.

- System-gray surfaces, high-contrast text and carefully controlled blue accents.
- Rounded, generously padded reading surfaces; minimal elevation and hairline separators.
- Sans-serif typography with restrained semibold display roles.
- A compact five-destination bottom bar, uncluttered current-screen title and grouped row layouts.
- The Home screen leads with a single reading hero; secondary destinations use a clean, separated list.
- Section tabs use a compact segmented control with a distinct selected surface rather than a full-width Android indicator.

This is an Apple-inspired visual mode implemented in Android Jetpack Compose. It is not a native iOS app and does not use proprietary Apple assets.

### Material — Material 3-inspired

**Intent:** expressive, tactile and adaptable to Android.

- Tonal surfaces and clear primary/container relationships using a purple/lavender palette.
- Material NavigationBar with a selected indicator.
- Visible but restrained elevation, medium corner radii and icon containers.
- Dense, efficient spacing with two-column routine shortcuts, an adaptive Explore grid and Material-native tab indicators.
- Sans-serif type roles and a clear distinction between the surface, container and selected states.

### Vatican — Ecclesial editorial / liturgical

**Intent:** reverent, typographic, restrained and rooted in the liturgical calendar.

- Warm stone/ivory surfaces with Roman sky-blue institutional accents and gold details.
- A centered SANCTA FIDES masthead with the current liturgical season.
- Calendar-first Home hierarchy, editorial serif headings and more formal separators.
- Tighter corner geometry, low elevation and generous text-reading measure.
- Section tabs use editorial type, a restrained liturgical underline and formal rules rather than capsule controls.
- A seasonal accent changes with Advent/Lent, Christmas/Easter, Ordinary Time and other celebrations.

This is an original ecclesial direction inspired by Vatican editorial design and the Roman liturgical calendar. It is not an official Vatican product, and it does not imply Vatican endorsement.

## Theme contract

- **Palette:** screen components use `MaterialTheme.colorScheme` semantic roles rather than fixed brand colors. The liturgical identity uses `secondary` and `secondaryContainer` for the season-specific accent while preserving blue institutional chrome.
- **Typography:** screens inherit roles from `MaterialTheme.typography`. Vatican may use the user's serif preference; Apple and Material use the system sans family.
- **Geometry:** components inherit `MaterialTheme.shapes`. Do not add a fixed `RoundedCornerShape(N.dp)` for ordinary cards or controls.
- **Depth:** use tonal/elevation surfaces selectively: Material is the most layered, Apple the flattest, Vatican predominantly editorial and border-led.
- **Navigation:** the shell and shared `IdentityTabBar` are identity-aware; Apple uses segmented tabs, Material uses a native tab row, and Vatican uses an editorial strip. Secondary routes remain reachable from Explore/More.
- **Dark mode:** palettes are explicitly paired. Check every foreground against the surface it actually sits on, especially gold, muted labels, icons and selected states.
- **Language:** English, Spanish and Portuguese remain supported. New user-visible text should be localized to all three.
- **Functionality:** visual identity must not remove Bible, prayer, liturgical, habit, Catechism, pilgrimage or spiritual-life routes.

## Review checklist

Before calling a visual change complete:

1. Check all three identities in both light and dark mode.
2. Compare Home, Bible, Catechism, Rosary, Prayers, Explore and customization, not just the theme screen.
3. Check Spanish and Portuguese labels for clipping, especially bottom navigation and routine tiles.
4. Check small screens, text scaling, scroll behavior, selected-state clarity, contrast and touch targets.
5. Build the APK and visually inspect emulator screenshots. Source inspection alone cannot certify spacing, overflow, hit areas, transitions or a production-ready AAA finish.
