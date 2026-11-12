# Home (Hoy) — round 3 (builder)

Input: `docs/redesign/reviews/home-round2-critic.md` (light 8.4 vs 7.0, dark 8.3, ITERATE).
Builds and renders: `FullAppScreenshotTest` (ES, 411x1600dp). The phone did not
appear in `adb devices` at any point this round (daemon restarted, still empty),
so there are no phone screenshots; evidence is the renders below.

## Critic top 5

| # | Item | Change |
|---|---|---|
| 1 | Verse card dominates | `HomeScreen.kt`: the verse is clamped to 4 lines of reading type with a real "…" (clause/word boundary, never mid-word) and the challenge to one line; the card is now a tap target (`onNavigateToBible`). The render measures about 219dp (was ~405dp). |
| 2 | Band above the hero | Root cause found: `AppHeroHeader` applied its `overlap` trim INSIDE the fixed `height(...)`, so the parent centred the shorter box and left a 12dp (overlap/2) band of canvas above the art. The trim is now applied outside the fixed height (`LumenComponents.kt`); the art starts at y = 0. Bell circle = status inset + 8dp (`AppHeroHeader`). |
| 3 | Dark sky too bright | Dark grade for light-toned art about -0.5 EV further (gain 0.46/0.43/0.37, warm offsets); the top 35% black scrim over 120dp was already there. Sampled sky in the render: about `#474138` (target `#4A4439` or darker). |
| 4 | Glyphs | `DevotionalGlyphs.kt`: rosary redrawn as a bead loop with a stem bead and a crucifix (1.5 stroke, drawn in a 2dp margin like Material glyphs); sunrise lightened to 1.5 stroke with 3 rays. Row icons and the glass-button glyph now use a 24dp frame so the visible ink is about 20dp (Material glyphs carry 2dp padding, which is why they measured 18dp / 14dp). |
| 5 | Glass | Tokens already match: light glass `#FFFFFF` @ 72%, edge 95% top, shadow `#3B2F22` @ 8%; dark glass `0x12FFFFFF` = 7%; radii card 24 / listGroup 20 (`LumenDesign`). Not changed: the edge's bottom stop stays a faint warm line, because a white 35% edge on a white canvas disappears. Fade above the tab bar is now 24dp (`LumenKit.kt`). |

## Lower-impact items

- **One card-header pattern** (`CardHeader`): headline title left, footnote reference right, one baseline, no icon. Mass ("Misa de hoy" / "Evangelio · Lucas 17, 7–10") and verse ("Versículo del día" / "Isaías 6, 1–3") use it. The Mass card's book icon and the eyebrow rows are gone.
- **"Primera lectura"**: a 44dp row with a chevron that opens the Mass (`home_first_reading_row`).
- **Real ellipsis**: `cleanScripture` turns "..." into "…".
- **Fidelity empty state**: ring track visible (white 14% dark / black 9% light, shared `AppProgressRing`), stroke 7dp, "Empezar mi regla" capsule kept.
- **Season card overlap**: overlap stays 24dp; with the band bug fixed the card now truly rides over the hero, title 16dp above it; the hero fade eases (0.80 at 58%, 0.94 at 86%) so art is still faintly visible behind the card top.
- **Fewer bold strings**: "Tu regla de vida" is weight 500.

## Evidence

- `docs/redesign/after/home_r3_light_render.png`, `home_r3_dark_render.png` (final, after the band fix).

## Known gaps

- No phone verification (device not listed by adb).
- Not done: a white 35% bottom edge on light glass (see item 5).
- Offline day without bundled lectionary still falls back to the reflection (see round 2).
