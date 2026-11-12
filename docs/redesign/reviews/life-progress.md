# Life area: progress note (paused)

Files: `SpiritualLifeScreen.kt`, `ParishFinderScreen.kt`, `PilgrimageAndHabitsScreen.kt`. All three are fully rewritten to the Lumen plan (docs/redesign/plans/life.md).

## Done (code written)
- **Pilgrimage**: `initialTab` param (default 0; consts `PILGRIMAGE_TAB_CALENDAR/SHRINES`, MainApp should pass 1 from Home's "Santuarios y Fe"). DetailHeader (`heroes/lumen/pilgrimage.jpg`), segmented Calendar/Shrines, Today card with liturgical accent bar, upcoming days grouped by month with weekday + numeral + colour dot (date derived by matching the next 28 days), shrine list rows, shrine detail page (history, spiritual significance, relics, indulgence, pilgrim advice, coordinates, "Ver en el mapa" capsule). Candle plate and dead habit code removed. Tags kept (`calendar_day_*`, `shrine_card_*`, `shrine_map_button_*`); `pilgrimage_shrine_plate` removed on purpose.
- **Parishes**: DetailHeader (`parishes.jpg`), one search field (IME Search, clear, submit circle), fixed "near me" capsule + privacy line, radius chips (changing radius re-runs the last lookup), states: idle / loading skeleton / notices (empty, failure, no fix, place not found, denied incl. permanently denied -> settings) / results with schematic numbered-pin map preview (no tiles, no new network use), numbered expandable rows, "more" group. Google Maps auto-open kept (searches and retry; not on radius/widen refinements). Real map opens via `InAppBrowserDialog(readerMode = false)`. All old tags kept.
- **Spiritual life**: hub with practice tiles (`spiritual_life_learn_picker`), journal feed (skeleton, empty card with prompts mapped to categories, filter chips at 6+ entries, entry cards, granted toggle, delete with confirm dialog), floating "Nueva entrada", composer sheet, quiz page, Way of the Cross pager + transport bar (original tags), chant page. `initialTab` 0..3 semantics kept; deep-linked pages exit via MainApp back.

## Not done / remaining
- Compile verification: DONE. `:app:compileDebugKotlin` finished with EXIT 0 on the final state of all three files (the whole tree compiled).
- Renders (light/dark, ES/EN/PT) and phone shots; `-Pshots.only=spiritual_life,parishes,pilgrimage_habits -Pshots.identity=apple`. Sub-pages/states need phone shots.
- Run AppNavigationTest, DesignNavigationTest, DesignContrastTest, ParishFinderTest.
- Builder note `docs/redesign/reviews/life-round1-builder.md` with inventory checklist and screenshots.
- Known gaps to record: Way of the Cross has only 3 of 14 stations (data gap; pager/progress adapt to any count, titles strip the ordinal); no `via_crucis.jpg`/`chant.jpg`/per-shrine art (fallbacks: `christ_thorns.jpg`, `spiritual.jpg`, `pilgrimage.jpg`); MainApp should pass `initialTab = 1` to `PilgrimageAndHabitsScreen` from Home's shrines tile.
