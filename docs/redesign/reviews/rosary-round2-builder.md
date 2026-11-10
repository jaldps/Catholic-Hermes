# Rosary / Divine Mercy: round 2 (builder)

Input: `rosary-round1-critic.md` (light 7.6, dark 8.0, Mercy 7.7, ITERATE). No phone (`adb devices` lists none),
so evidence is Robolectric renders (411x891, ES) from `RosaryStatesShotTest`, files `docs/redesign/after/rosary_r2_*`.
Build cycles used: 2 (the first stopped on one compile error, `constraints` read inside `AnimatedContent`; the second passed).
Each fix below was checked on the renders or on a 2x crop (`rosary_r2_zoom_marks_reel.png`).

## Top 5

| # | Fix | Result seen in the render |
|---|---|---|
| 1 | Short prayers (<=150 chars) now 1.35x (about 26/36 serif) and held at 40/60 in the prayer area; "Despues · ..." sits 24dp under the last line instead of at the controls | Opening and Mercy: the text and its "then" line form one block in the area, the hole under the text is about 95dp (was about 160). It is one hole above the controls, not a gap inside the block. Long prayers (Creed, Hail Mary) keep the pinned line |
| 2 | Stage art: no light-mode lift; top scrim 35% to 0 over 130dp; the fade now starts at the label (about 36dp above it, Mercy 52dp) and is gone under the label's first line; progressive blur follows the same range; saturation 1.0 for Divine Mercy (0.92 others); Agony focus Y 0.38 to 0.55; fruit is one line cut at a word; the Mercy eyebrow ("Coronilla de la Divina Misericordia", which only repeated the segment) is gone so the Mercy label is 72dp, not 122 | Agony: Christ and the angel are clear and in colour; Divine Mercy: face below the control, red and pale rays intact, clear down to about y 270dp. Dark keeps the same clear painting (black 8% only) |
| 3 | Duplicates: the right-hand progress label shows only when the title is a mystery name; the stage title for non-decade parts is now "Decada 1 de 5" / "Inicio" (the richer line), so nothing repeats. When the prayer name equals the first line ("Por su dolorosa Pasion") the name is dropped and the count ("5 de 10") becomes the row's title | Mercy decade has no "Por su dolorosa Pasion" twice and no second "Decada 1" |
| 4 | V. / R. marks: reading face, 0.85em of the base size (not the enlarged size), secondary ink, Medium weight, on the first baseline (`alignByBaseline`), hung 18dp into the margin. `GuideHint` legend updated to "V. / R." | 2x crop: serif "V." and "R." with dots, level with the first line, text column stays on the 20dp gutter |
| 5 | Materials: segmented track by tone (dark art: black 38% + white labels; light art: white 72% glass, charcoal thumb, ink labels); play disc is the action colour (charcoal in light, ivory in dark); reel crucifix is an outline Latin cross (round caps, same proportions as the tab glyph) with a ring at +5dp; sub-step pips now sit about 6dp under the ring | Reel crop confirms the cross and the clear pips. The light-glass segmented variant is implemented but not seen: every painting rendered is tone "dark" (Annunciation and Resurrection would show it) |

## Lower items

- Book glyph replaces the bulb (outline `MenuBook`, filled when on). **I did not change its label to "Meditacion del misterio":** the button toggles the beginner's instructions (rubric + V./R. legend), tag and `contentDescription` "Instrucciones" are asserted by the accessibility spec, and the mystery's meditation already lives behind the tappable title and fruit (Mystery sheet). Decide whether to split these into two buttons.
- Fruit casing: `sentenceCaseFruit()` in `RosaryText.kt` lowers Title Case after the colon in ES and PT, keeping God's and the Virgin's names ("Espiritu Santo", "Dios", "Maria"); EN is left as written. Applied to the stage, the announcement detail line and the Mystery sheet. 3 tests in `RosaryTextTest`.
- Transport sits 20dp above the dock (was 14).
- Painting source: `heroes/mysteries/divine_mercy.jpg` is **830x1400**, below the 1080px asked for; there is no larger source in the repo, so it is softer than ideal on 3x phones. A sharper scan needs to be supplied.

## Tests

`RosaryInteractionTest` 4, `RosarySessionTest` 25, `RosaryNarrationTest` 17, `HomeTextTest` 7, `RosaryTextTest` 3: all pass. `:app:assembleDebug` passes.

## Known gaps

- In the Mercy decade render, "Despues · Padre Eterno" is faint. It happens only after 13 rapid test clicks and the same line is full ink right after a devotion switch, so I believe it is the capture catching the step crossfade, but I could not confirm it without a device.
- The fruit is cut at a word with an ellipsis on one line ("...y la…"); the whole text is one tap away.
- Light-glass segmented variant and the 320dp / 1.3x layout were not rendered this round.
- Nothing committed.
