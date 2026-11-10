# Rosary / Divine Mercy: round 1 (builder)

Input: the approved plan (`plans/rosary.md`), the owner's phone screenshot and the Home round-4
critic bar. No phone was connected (`adb devices` lists none), so all evidence is Robolectric
renders at 411x891dp, ES, light and dark. Every claim below was checked on a crop or on the full
render, not assumed. State stays in `RosarySession` / `NarrationState`; no new scattered state.

## What the owner's screenshot got wrong, and what changed

| Report | Cause found in the render | Fix |
|---|---|---|
| Dead area between prayer and transport | The stage took only what the *Our Father* left (about 220dp), the prayer area was sized for it, and short prayers sat in 2 lines of empty space | The prayer area is now sized for the frequent prayer (Hail Mary / Eternal Father), so the stage grows to about 314dp. Short prayers (<=150 chars: Sign of the Cross, chaplet decades) are set 20% larger, like a lyric. A quiet "Despues · Gloria" line is pinned under the text (next *different* prayer), so the end of ten Hail Marys is never a surprise |
| Painting cropped awkwardly | A 411x220 strip showed 45% of a portrait painting. The Divine Mercy manifest focus (0.16) put the face behind the segmented control | Stage is about 314dp tall (about 70% of the painting is visible). Divine Mercy focus Y 0.16 -> 0.02 so the face sits below the control. Light mode lifts dark paintings toward the canvas (30%) so the top is mist, not a black slab |
| Heavy bold 2-line title under the picker | The headline was `headline` 17 semibold, ink, 2 lines | Title is regular 22/28 ink (the mystery during a decade, the part name at the opening/closing). Under it, one quiet `subhead` line: the mystery's **fruit** in a decade, the plan of the part otherwise. "Inicio" / "Decada 1" read as a calm title, the plan as a caption |
| Segmented control low contrast | Already darkened (black 62% track, white labels, white thumb) | Verified on a 2x crop: labels are white on a near-black track in light and dark, over both a bright and a dark painting. Kept |
| Refined bead reel | The edge fade used an offscreen DstIn mask, which draws as a white box in previews and is fragile | Edge fade is now per-bead and per-thread-run alpha (no offscreen layer). To-come pearl edge 12% -> 18% ink so white pearls hold on the light canvas. Reel 76 -> 68dp (stage gets the room) |
| Prayer text and measure | Fine, but the bottom of a long prayer was cut with a hard edge in previews | Reading style kept (user's reader font); mist at the scroll edges is now a canvas-coloured gradient that matches the screen gradient at that height (no offscreen mask). Verified on the Creed: the last line dissolves |
| Seam under the stage | Stage faded to flat `canvasTop`, the page gradient is darker lower down | The stage fades to the canvas gradient at its own y (`lerp(canvasTop, canvasBottom, y/screen)`), so no seam |
| Painting blur | Used an offscreen DstIn mask | Progressive blur is now 18 clipped bands of the tiny copy with a smoothstep alpha ramp: same look, no layer, same on every API |

Transport spacing is one rhythm now: 8 (stage to progress), 4 (reel to title), 4 (title to text), 8 (text to transport), 14 (to the dock). Both modes (Rosary and Divine Mercy) use the same layout; the Chaplet gets the label eyebrow "Coronilla de la Divina Misericordia" instead of the mysteries picker and its large Divine Mercy painting.

## Small extras

- **Rosary glyph** (`ui/design/DevotionalGlyphs.kt`): a closed oval loop of 8 beads, a 2-bead stem and a Latin cross about 7 units tall. Zoomed at the dock tab and at the Home Coronilla row (22dp): it reads as a rosary, not a "Y". Stem beads are separated (3.0 / 5.8 unit pitch) after the first crop showed them fusing; this last tweak is visible in the Home crop (`rosary_r1_home_glyph_row_light_render.png`) but the dock crop in the zoom sheet is from the render just before it.
- **Saint casing** (`ui/screens/SaintHeading.kt`, used by Home's saint card): `saintHeading()` splits "s. León Magno, papa y doctor de la Iglesia" into title **San León Magno** and role **Papa y doctor de la Iglesia**; the card shows the title in `title2` and "role · 10 de noviembre" (footnote) under it. General rules: s./sta./sto./b./bta./ss./sts. and full lowercase words are written out and capitalised per language (San before consonants, Santo before Domingo/Tomás/Tomé/Toribio; PT São/Santo; EN St.); the role's first letter is capitalised; parentheticals are never split. 3 new tests in `HomeTextTest`. Note: today (10 Nov) the bundled entry is the generic "Santo del Dia de Hoy", and the card sits under the dock in the 1600dp render, so the casing is verified by tests, not by a picture.

## Evidence (all in `docs/redesign/after/`)

- `rosary_r1_light_render.png`, `rosary_r1_dark_render.png`: Rosary opening (Sign of the Cross).
- `rosary_r1_{light,dark}_1_creed.png`: long prayer scrolling in its area, "Despues · Padre Nuestro".
- `rosary_r1_{light,dark}_2_hailmary_decade.png`: Hail Mary 2 of 10 in the first Sorrowful decade (title + fruit in the stage, reel mid-decade, Hail Mary fits without scrolling).
- `rosary_r1_{light,dark}_3_mercy_open.png`, `..._4_mercy_decade.png`: Divine Mercy, opening and a decade.
- `rosary_r1_zoom_segmented_glyph_fade.png`: crops of the segmented control, the dock glyph and the Creed fade.
- `rosary_r1_home_glyph_row_light_render.png`: Home, "Oracion del momento" with the new glyph.

`RosaryStatesShotTest` (new, runs only with `-Pshots.only=rosarystates`) renders these states through the real app chrome; output in `app/build/screenshots/rosary_states`.

## Tests

| Run | Result |
|---|---|
| `RosaryInteractionTest` (4) | pass |
| `RosarySessionTest` (25) | pass |
| `RosaryNarrationTest` (17) | pass |
| `HomeTextTest` (7, 3 new) | pass |
| `:app:assembleDebug` | pass (`app/build/outputs/apk/debug/app-debug.apk`) |

## Known gaps

- No device: gesture feel (reel drag, haptics), real-font rendering of the picker and TalkBack are unchecked. Robolectric draws blend modes unreliably, which is why the offscreen masks were removed rather than trusted.
- Short prayers still leave air between the text and the "then" line. That is deliberate (the layout never changes between steps), but a critic may want more.
- During a decade the label (picker + 2-line title + 2-line fruit) takes about 150dp of the 314dp stage, so only the top 165dp of the painting is clear. A longer title or fruit in PT at 1.3x type will take more.
- The 320dp / 1.3x layout was not re-rendered this round (the interaction test for it passes).
- Nothing committed.
