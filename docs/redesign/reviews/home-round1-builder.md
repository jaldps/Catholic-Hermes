# Home — round 1 (foundation builder)

## What changed

**Foundation (all screens inherit)**
- Lumen tokens (`DesignSystem.kt`): a warm canvas gradient, translucent glass with a lit edge, a soft CSS-like shadow (clipped out of the shape), charcoal/ivory action colour, success green, ring gradient, and calibrated inks that pass `DesignContrastTest`. The dark palette is a "night chapel", not an inversion.
- Type (`AppTypography.kt`): Lumen scale `display` 44 / `largeTitle` 34 / `title1` 28 / `title2` 22 / `title3` 20 / `headline` / `body` / `callout` / `subhead` / `footnote` / `eyebrow` / `caption` / `reading`. The font picker still applies to every slot.
- `LumenKit` replaces `AppleKit` for both stored identities, and the identity picker is gone.
- Chrome: an edge-to-edge canvas with an overlaid top bar (a single glass back circle, and an inline title that fades in on scroll). The owner's six-tab floating dock is kept as it was; only its material changed to glass.
- Per-screen frame (`AppEdgeToEdge()`), back override (`AppBackHandler`), shared hero loader with manifest focus points, plus the full component set in `LumenComponents.kt` / `LumenExtras.kt`. See `COMPONENTS.md`.
- MainApp: a saveable back stack, a REMINDERS destination, More titled "Explorar", CHURCHES_NEWS titled "Noticias", the Appearance value, and the questions deep link cleared from More.
- Appearance is an `AppSheet` (Dialog-hosted) that applies instantly: mode, accent, text size and narration speed.

**Home**
- Hero art (`heroes/lumen/home.jpg`, misty Friedrich landscape with a cross) from the top of the display, with the date eyebrow, `display` "Hoy" and a glass bell → Reminders.
- The season card rides the hero fade (season dot, celebration · obligation).
- The "Misa de hoy" card shows the Gospel and first-reading previews, a charcoal "Leer la Misa" capsule, and a round glass play button that reads the Gospel aloud (TTS).
- Continue reading / today's reflection (expandable).
- The verse of the day is a pull quote in the reader font, with the daily challenge underneath.
- "Oración del momento" (current hour, next hour, rule of life).
- "Fidelidad de hoy": progress ring X/N, "Ver mi regla de vida", "Vencí una tentación" glass capsule, and the month calendar.
- "Santo del día": Marian art band, name in `title2`, bio and quote.
- "Pregunta del día": `title2` question, answer preview, "Leer respuesta" capsule and share.
- "Explorar la fe": a 3×2 grid of glass tiles (Catecismo, Iglesias, Santuarios, Trivia, Vida interior, Explorar).
- All testTags kept (`home_screen`, `screen_header_illustration`, `liturgical_calendar_widget`, `daily_mass_card`, `read_mass_full_button`, `home_continue_reading`, `home_continue_row`, `verse_of_the_day_card`, `home_daily_question*`, `saint_of_the_day_card`, `home_habit_tracker_card`, `home_fidelity_*`, `home_overcome_temptation_button`, `home_victory_confirmation`, `home_more_settings`). New tags: `home_reminders_button`, `home_gospel_listen`, `home_gate_*`.

## Evidence

- Renders (ES, 411×1600dp): `docs/redesign/after/home_r1_light_render.png` and `home_r1_dark_render.png`.
- Pair against the reference: `docs/redesign/after/home_r1_vs_reference.png`.
- The phone dropped off ADB ("no devices") right after the round-1 APK built, so there are **no phone screenshots this round**.

## Fixed after self-review

- The Mass card subtitle repeated the season card (now "Lecturas del día").
- The Gospel preview showed a doubled «« (now normalised by `guillemets()`).
- Hero height went from 300 to 330dp to match the reference's proportion.

## Known gaps

- Phone verification is pending (device offline).
- Fidelity shows 0/0 in renders because the in-memory DB has no seeded habits.
- Coming from the habits owner: `DailyRule` and `FidelityCalendar(framed = false)`, so the calendar card isn't nested. Swap them in when they land.
- The bell opens the REMINDERS destination; its visual design belongs to the habits owner.
- Backdrop blur isn't available without a dependency, so glass is a translucent fill with a lit edge and shadow.
