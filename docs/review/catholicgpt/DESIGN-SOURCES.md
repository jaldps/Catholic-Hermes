# Design sources and evidence boundaries

Research performed November 2–3, 2026. Official public GitHub sources were accessed through the environment proxy with TLS verification. These are publishers' own materials; blocked websites were not relayed through mirrors.

## Apple

Pinned [Food Truck sample](https://github.com/apple/sample-food-truck/tree/3954a769e99f3cc53297d94f2b960ceb2665b3d6): README; `App/Navigation/Sidebar.swift`, `App/Navigation/ContentView.swift`, `App/Truck/TruckView.swift`, `App/Donut/DonutGallery.swift`, `FoodTruckKit/Sources/Brand/BrandHeader.swift`, `FoodTruckKit/Sources/Donut/DonutView.swift`.

Observed implementation choices include native split/stack navigation, an explicit default destination, semantic text styles, grouped backgrounds, responsive gutters/grids and bounded illustrated headers. The sample includes rich illustration, gradients and animation. It does not establish that an Apple interface must be bare or that ornament is forbidden.

Apple HIG's live website and Apple Notes were unavailable. The sample's source supports implementation comparisons, not a claim that current Apple HIG was fully studied or Apple Notes was visually beaten. Food Truck and [Backyard Birds](https://github.com/apple/sample-backyard-birds/tree/1843d5655bf884b501e2889ad9862ec58978fdbe) were inspected for tracked whole-app screenshots; only asset-catalog artwork was found. Artwork was not substituted for UI screenshots.

## Google Material

Pinned [Material Components Android](https://github.com/material-components/material-components-android/tree/66b0eeb53afeb6025ffbbd37734b08e5bce197ed): `docs/components/TopAppBar.md`, `BottomNavigation.md`, `List.md`, `Card.md`, `Tabs.md`, `Chip.md`, `CommonButton.md`; `docs/theming/Typography.md`, `Color.md`, `Shape.md`. Relevant introduction/anatomy, role, sizing and accessibility passages were read; not every line of every guide was read. The top-app-bar, expressive navigation and expressive-list component images were visually inspected.

The documents describe three to five labeled navigation destinations and an active indicator; semantic typography and color roles; tonal surface containers; 48dp chip touch targets independent of their smaller painted size; and differentiated emphasis for filled/tonal actions. Flexible app bars can contain imagery. Current documentation distinguishes baseline M3 from M3 Expressive: navigation height 80→64dp and indicator width 64→56dp. CatholicGPT deliberately uses the compact Expressive navigation dimensions and lets the bar grow with text.

These Android View component documents inform visual decisions; they are not Compose API documentation or proof of whole-app polish.

## Actual Google app comparison

Pinned [Now in Android](https://github.com/android/nowinandroid/tree/2982e63cb691ad49cd009aab43dc206fee6699c2), the official Android reference app. Its README identifies Material 3 and contains genuine app screenshots. The repository also publishes screenshot-test outputs for the whole `NiaApp` root at compact and expanded logical viewport sizes, and a 55-page Material 3 design PDF.

Original files used:

- [README screenshot montage](https://github.com/android/nowinandroid/blob/2982e63cb691ad49cd009aab43dc206fee6699c2/docs/images/screenshots.png).
- The whole-app compact/expanded checked-in screenshot baselines, traced to the repository's `NiaAppScreenSizesScreenshotTests` source. These use demo data and inspection-mode illustration placeholders; they are Android test renders, not website screenshots.
- [Official design PDF](https://github.com/android/nowinandroid/blob/2982e63cb691ad49cd009aab43dc206fee6699c2/docs/Now-In-Android-Design-File.pdf).

Exact baseline URLs, dimensions and checksums are preserved in [reference-provenance.json](reference-provenance.json). The original images and their source README/license remain in `/workspace/.cloud-setup/references/whole-app/`. The independent [comparison report](current-reviews/google-reference-comparison.md) records which inputs were actually viewed and preferred Google's visual hierarchy/cohesion. It compares different products and tasks, not a controlled blind usability study. Later Material header integration addresses one recorded gap; that does not imply the critic approved the later change.

## Vatican and live websites

Vatican's engraved, rubric and paper treatment is this app's editorial interpretation. No claim is made that it reproduces a verified current Vatican design system.

Attempts to access Apple HIG, `m3.material.io`, both supplied Dribbble URLs and `www.vatican.va` still returned proxy CONNECT 403 after full execution access was enabled. Runtime status continued to show restricted network policy with no custom hosts. The saved configuration draft includes the reference domains, but saving a draft does not apply the live policy. No live-site comparison is claimed.

## Acceptance

Practical checks cover persisted font selection, actual themed text families, independent prayer-guide positions, navigation/back behavior, translated large-text controls, contrast and rendered screens. Targets and ratios are checked by tests where available; screenshots alone do not prove touch bounds, persistence or audio behavior. Independent critics remain candid about gaps. Compliance with a component specification does not establish exceptional product quality.
