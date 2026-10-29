# AAA Visual Identity — Harsh Critic Protocol

## Reference Standards
- **Apple HIG**: dribbble.com/tags/ios-app-design — look for frosted translucency, SF-style tight tracking (-0.3sp titles), flat bars with no drop shadow, 8dp icon grids, 20dp action icons, snappy 180ms motion, generous whitespace, system-gray secondary text.
- **Material Design 3**: m3.material.io — look for tonal elevation (no drop shadows on bars/cards), shape scale (extraSmall 4 → extraLarge 28dp), state layer ripples, medium tracking (0.1-0.6sp labels), 24dp nav icons, 300ms motion, opaque surfaces with tonal layering.
- **Vatican / Liturgical**: gold-on-burgundy or navy-silver with solemn pacing, deeper tracking (2-3sp), serif display type, heavier elevation (2-3dp top bar, 8-10dp nav), 22dp icons, 280-320ms motion, rich but restrained palette.

## Blind Comparison Protocol
1. Render the same screen in all 3 presets side-by-side (Apple / Material / Vatican).
2. For each preset, ask: "Does this look like it belongs in a real app from that ecosystem?"
3. If ANY of the following is true, the preset FAILS the AAA gate:
   - The screen looks identical across presets (only colors changed, or worse: nothing changed).
   - Typography is still the default Roboto/serif for all presets (Apple must switch to SF-like sans with tight tracking; Material must have proper label letter spacing; Vatican must have wide tracking).
   - Cards/buttons still use hardcoded `RoundedCornerShape(N.dp)` instead of `MaterialTheme.shapes.large` etc.
   - Padding is still hardcoded `16.dp` / `24.dp` instead of `MaterialTheme.spacing.lg/xl`.
   - Bottom nav icons are still all `20.dp` hardcoded (must be 20/22/24dp per preset).
   - Top bar still casts a hardcoded `shadowElevation = 8.dp` drop shadow in Apple/Material (should be flat/tonal).
   - Title text still uses `FontFamily.Serif` hardcoded (Apple/Material must switch to sans).
   - Icons don't dim with `unselectedIconAlpha` from the identity layer.
   - Motion is still instant or a single hardcoded duration (must be 180-320ms per preset).

## Screen-by-Screen Checklist
For each screen file, verify ALL of:
- [ ] All `Color(0xFF...)` replaced with `MaterialTheme.colorScheme.*`
- [ ] All `RoundedCornerShape(N.dp)` replaced with `MaterialTheme.shapes.*`
- [ ] All hardcoded `.dp` padding replaced with `MaterialTheme.spacing.*`
- [ ] All hardcoded `fontSize`, `fontFamily`, `letterSpacing`, `fontWeight` replaced with `MaterialTheme.typography.*`
- [ ] Icon sizes come from `identity.navIconSize` or `identity.actionIconSize` (not hardcoded)
- [ ] Icon alpha for unselected states uses `identity.unselectedIconAlpha`
- [ ] Card/elevation uses theme tokens, not hardcoded `shadowElevation` literals
- [ ] No `SacredBurgundy`, `LiturgicalGold`, `WarmAlabasterBg`, `ParchmentSurface`, `DarkSanctuaryBg` in screens (only in theme definitions)

## Pass/Fail Criteria
- **FAIL**: Any hardcoded color/shape/spacing/typography/icon-size remains in the screen file.
- **FAIL**: The screen looks the same across all 3 presets when rendered.
- **PASS**: Every visual parameter flows from the active preset. Switching presets transforms typography, shapes, spacing, iconography, transparency, and motion. A blind reviewer would say "this is clearly an Apple/Material/Vatican app."

## Output Format
```
SCREEN: <name>
STATUS: PASS / FAIL
HARSH CRITIC NOTES:
- <specific deficiency if FAIL>
- <praise if PASS>
REMAINING HARDCODED VALUES: <count>
```
