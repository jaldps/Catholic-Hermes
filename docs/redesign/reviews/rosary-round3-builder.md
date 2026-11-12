# Rosary / Divine Mercy: round 3 (builder)

Input: `rosary-round2-critic.md` (light 8.6, dark 8.8, Mercy 8.8). No phone (`adb devices` lists none), so evidence is
renders from `RosaryStatesShotTest` (`docs/redesign/after/rosary_r3_*`). One build cycle; it passed.

| # | Fix | What the render shows |
|---|---|---|
| 1 | Fruit is never cut: footnote 13/18, up to 3 lines, no ellipsis (the one-line word-cutter is deleted). The data is untouched | Dark Hail Mary: "Fruto: el dolor por nuestros pecados y la conformidad con / la voluntad de Dios", whole, 2 lines. The painting is still clear down to the picker capsule (about y 150dp) |
| 2 | Title + text + "Después" are one block: title row 32dp, 12dp to the text, "Después" 24dp below it. The block lives in one custom layout inside the prayer area; a short prayer holds it at 40/60 in the spare room, a long one starts at the top. The separate title slot is gone (it is a 0dp spacer), `current_prayer_title` is the same node inside the block | Opening: about 35dp of air above the title and about 44dp below "Después" (was 50dp inside the block and 109dp under it). Mercy: "5 de 10", text and "Después" read as one unit |
| 3 | V. / R. at 0.72em, right-aligned in a 12dp hang, so they start 8dp from the screen edge (the gutter is 20dp) | Crop: the marks start at about 8.5dp. **Residual:** the mark sits about 2dp from the text ("V.Dios"). A mark wide enough to be readable at 0.72em fills the 12dp hang; a wider hang would break the 8dp edge clearance. A 14dp hang (6dp edge) or an 8dp edge with a 6dp gap are the two ways out; tell me which you prefer |
| 4 | "Después" is its own child of the prayer layout, outside the step crossfade and outside the scroll mist, in `textSecondary` | Sampled in the render: ink `#6B6661` on `#EFECE8` = **4.82:1** (light Mercy decade and opening), 7.58:1 in dark. It is no longer faint after clicks |
| 5 | When the first line already names the prayer, the count is the title: ink `title2` (22/28) weight 500 at the top of the block. I preferred this to moving it to the reel row because it keeps the reel row clean and gives the block a head | Mercy decade: "5 de 10" in ink, 12dp above "V. Por su dolorosa Pasión," |

Also:
- Book glyph: a drawn open book (`DevotionalGlyphs.bookOutlined` 1.5 stroke when off, `bookFilled` solid pages when on). Material's "outlined" menu_book renders as a solid slab at 20dp, which is why it read as on. The off state is now plainly an outline (crop in `rosary_r3_zoom_marks_fruit_book.png`). The on state was not rendered (the toggle is not clicked in the states test).
- Dark painting: -0.3 EV (RGB x 0.81 through the colour matrix, saturation kept; the flat 8% black is gone). The angel's robe no longer outshines the play disc.
- Prayer area budget: the title row moved inside it, so `PrayerChrome` (104dp) replaces the old title slot, gaps and "then" slot; the stage is about 4dp taller, not shorter.

## Tests

`RosaryInteractionTest` 4, `RosarySessionTest` 25, `RosaryNarrationTest` 17, `HomeTextTest` 7, `RosaryTextTest` 3: all pass. `:app:assembleDebug` passes.

## Known gaps

- No device: crossfade and scroll behaviour of the new prayer layout are checked in previews only (the interaction tests, including the 320dp / 1.3x one, pass).
- The mark-to-text gap (see 3).
- The light-glass segmented variant and the filled book state have not been rendered.
- `divine_mercy.jpg` is still 830px wide (no larger source in the repo).
- Nothing committed.
