# Design review brief

Sancta Fides ships three visual identities. Each one has to change everything:
type, shape, color, iconography, chrome, layout, spacing and motion. A recolor
does not count.

| Identity | Benchmark |
|---|---|
| **Apple** (`AppleKit.kt`) | Apple Design Award apps and the best of dribbble.com/tags/ios-app-design. UIKit/SwiftUI idiom: large titles, inset grouped lists, SF-like type, translucent bars, 44pt targets, a single tint. |
| **Material** (`MaterialKit.kt`) | m3.material.io as applied in Google's own first-party apps (Calendar, Gmail, Photos, Play): tonal surfaces, the M3 type scale, shape scale, state layers, navigation bar with pill indicator. |
| **Vatican** (`VaticanKit.kt`) | Vatican publications and liturgical books: the Roman Missal, Libreria Editrice Vaticana, vatican.va. Trajan/Cinzel capitals, Garamond body, gold leaf used sparingly, rubrics in red, folio rules, centred mastheads. |

## Bar

AAA quality, the level of the most professionally designed apps. Every
reviewer should come away impressed by every tab. Score each screen from 1 to
10 and be harsh: an 8 still has visible problems. Anything a design lead at
Apple or Google would send back counts as a defect.

## Renders

```bash
export ANDROID_HOME=/home/user/android-sdk ANDROID_SDK_ROOT=/home/user/android-sdk
```

```bash
cd /home/user/catholic-hermes && flock /tmp/gradle.lock /opt/gradle-9.3.1/bin/gradle -I /home/user/init-mirror.gradle --no-configuration-cache -q :app:testDebugUnitTest --tests 'com.example.screenshots.FullAppScreenshotTest' -Pshots.only=home,bible
```

The run writes to `app/build/screenshots/app/<destination>_<apple|material|vatican>_<light|dark>.png`.
Destinations: home, bible, rosary, prayers, more, catechism, doctrine,
churches_news, habits_virtues, spiritual_life, parishes, pilgrimage_habits.
Each PNG is the real 411×891dp viewport at 2x, rendered by Robolectric with
native graphics. Blur and real-device font hinting are approximate in these
renders.

**Always take `/tmp/gradle.lock` with flock.** Several agents build at the same
time, and builds that run without the lock corrupt each other's test results.

## Code map

- `ui/design/AppKit.kt`: the contract every identity implements.
- `ui/design/{Apple,Material,Vatican}Kit.kt`: the identities.
- `ui/design/AppComponents.kt`: the wrappers that screens call.
- `ui/theme/DesignSystem.kt`: tokens (spacing, shapes, palette, motion, decor).
- `ui/theme/AppTypography.kt`, `ui/theme/AppFonts.kt`: type.
- `ui/screens/*.kt`: the screens, shared by all identities.
- `ui/MainApp.kt`: navigation. Four tabs plus More, which pushes the
  secondary screens.
