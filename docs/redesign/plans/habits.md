# Habits area plan (Lumen)

Owner: Habits designer-engineer. Files owned:
`ui/screens/HabitsAndVirtuesScreen.kt`, `FidelityCalendar.kt` (also used by
Home), `TemptationCalendar.kt`, `RemindersScreen.kt`.
Read with `DESIGN.md` and `PROCESS.md`. Step 1 is the audit and plan. No code
has been changed yet.

---

## a. Inventory: the "nothing lost" checklist

### A1. `HabitsAndVirtuesScreen` (tab root, `AppDestination.HABITS_VIRTUES`)

Public signature (keep it; additions must be optional):
`HabitsAndVirtuesScreen(repository, dao, ttsManager, modifier, onOpenPrayer: (String)->Unit, onOpenPrayers: ()->Unit, onOpenRosary: (Int)->Unit, onOpenMissal: ()->Unit, onOpenNight: (Int)->Unit)`.
MainApp wires them as follows: Prayers tab 0, Rosary tab (0 = Rosary,
1 = Chaplet), Bible tab 1 (missal), Prayers tab 2 + night office (1 = Examen,
2 = Traditional).

**Data read**
| Data | Source |
|---|---|
| UI language | `repository.currentLanguage` (StateFlow) |
| Today's checks | `dao.getCompletionsForDate(todayDateStr)`; `todayDateStr` is `yyyy-MM-dd`, captured once in `remember {}` |
| All checks (month stats) | `dao.getAllCompletions()` |
| Victory log + total | `dao.getAllSinOvercomeLogs()`, `dao.getTotalVictoriesCount()` |
| Per-day victories / falls | `TemptationStore(ctx).counts` / `.fellCounts` (DataStore `temptation_counts`) |
| Seven roots | `repository.sinAvoiderCatalog` (7 `SinAvoiderOption`: sinKey, title, counterVirtue, advice, iconName) |
| Hero photo | `assets/heroes/candle_prayer.jpg` through a private `decodeSampledBitmap` copy |
| Unused (dead) | `dao.getAllHabits()`, `repository.defaultHabitsToSeed` |

**Data written**
- Toggling a daily habit inserts or deletes one `HabitCompletionLogEntity(habitId, completionDate)` for today.
- "Registrar victoria" inserts `SinOvercomeLogEntity(sinKey, counterVirtue = localized string, prayerUsed = "HAIL_MARY", note)` and calls `TemptationStore.recordVictory()`.
- "Caí" calls `TemptationStore.recordFall()` only. No Room row.
- TTS: `ttsManager.setLanguage(lang)`, `speakSingle(hailMary, isFemaleVoice = true)`, `stop()` on every dismiss. `ttsManager.isPlaying` exists but is not used yet.

**The fixed rule: exactly 12 practices, 3 groups.** Each row has a check
circle (toggles today) and a text area (opens the practice).
| Group | id | ES title | Opens |
|---|---|---|---|
| 0 Mañana (4) | `daily_offering` | Ofrecimiento del día | `onOpenPrayers()` |
| | `daily_morning_prayers` | Oraciones matutinas | nothing |
| | `daily_mental_prayer` | Oración mental y silencio | nothing |
| | `daily_spiritual_reading` | Lectura espiritual | `onOpenMissal()` |
| 1 Día (5) | `daily_angelus` | Ángelus 6h · 12h · 18h | `onOpenPrayer("angelus")` |
| | `daily_chaplet` | Coronilla 15:00 | `onOpenRosary(1)` |
| | `daily_jaculatorias` | Jaculatorias | `onOpenPrayer("jesus_prayer")` |
| | `daily_mass` | Santa Misa / Misa del día | `onOpenMissal()` |
| | `daily_meal_blessing` | Bendición de las comidas | nothing |
| 2 Noche (3) | `daily_examen` | Examen de conciencia | `onOpenNight(1)` |
| | `daily_rosary` | Rosario | `onOpenRosary(0)` |
| | `daily_traditional_prayers` | Oraciones tradicionales | `onOpenNight(2)` |

Every practice has a localized title and subtitle in ES, EN and PT. All of
them stay, word for word.

**States and surfaces today**
1. Tab "Regla de vida". Hero band (candle photo, 220dp) and the title "Hábitos y Virtudes". Segmented control "Regla de vida / En la tentación". "Recordatorios de oración" button. Fidelity card (ring 112dp, "N/12", eyebrow "Fidelidad espiritual", three messages chosen by rate: <50 %, ≥50 %, 100 %). "Control de cumplimiento" card ("Hoy: N de 12", "Este mes: X de Y · N días activos" with Y = 12 × day of month, thin bar, one dot per elapsed day). Three groups, each with a header ("N de M hoy") and a list of rows.
2. Tab "En la tentación". The same hero, segmented control and reminders button. "Antídoto de gracia" card (count "N tentaciones vencidas en Cristo", quote, primary button "Vencí una tentación"). Section "Victoria mes a mes" with the TemptationCalendar (victories and falls). "Las siete raíces" list: a row per root with the root's title, "Virtud contraria: …", the advice and a trailing count of victories for that `sinKey`. Ornament. "Historial de victorias en Cristo" ("N victorias", the last 10 logs, each showing "Victoria en {virtue}", the «note» and "d MMM, HH:mm").
3. Sin-avoider dialog, opened from a root row or from the emergency button. Title "¡Victoria sobre la Tentación!". "{sin} ➔ {virtue}", the advice, "Reza un Ave María ahora" with a TTS button, the Hail Mary in ES/EN/PT, two stats rows (Vencí and Caí, each with hoy, semana and mes), a note field "Nota u ofrenda (opcional)", and the buttons "Registrar Victoria", "Caí en una tentación" and "Cerrar".
4. Reminders overlay (`showReminders`): `RemindersScreen` drawn over the Habits column.
5. Add-custom-habit dialog: **dead**. `showAddHabitDialog` is never set to true.

**testTags (all preserved; the new location is in §c)**
`habits_and_virtues_screen`, `tab_habits_rule`, `tab_sin_avoider`,
`fidelity_card_photo`, `screen_header_illustration`, `open_reminders`,
`habits_checklist_card`, `habits_checklist_card_1`, `habits_checklist_card_2`,
`habit_card_<id>` ×12, `toggle_habit_<id>` ×12, `daily_completion_control`,
`habits_monthly_summary`, `habit_month_day_<d>`, `emergency_overcome_button`,
`card_overcome_<LUST|ANGER|GLUTTONY|SLOTH|ENVY|PRIDE|GREED>`,
`confirm_victory_button`, `record_fall_button`,
`victory_stats_today|week|month`, `fall_stats_today|week|month`.
These tags exist only in dead code: `input_habit_title` and
`save_custom_habit_button`. Removing that code needs approval (see §e).

**Content descriptions and semantics**
- Ring: "N de 12 cumplidos hoy" / "N of 12 kept today" / "N de 12 cumpridos hoje".
- Each row has the stateDescription "Cumplido hoy" / "Pendiente hoy" (EN "Kept today" / "Pending today", PT "Cumprido hoje" / "Pendente hoje").
- Completion card: stateDescription "today N/12"; the bar says "X/Y"; each day dot's contentDescription is its date.
- TTS button: "Escuchar oración" / "Listen to the prayer" / "Ouvir a oração".

**Tests touching the area.** `AppNavigationTest` asserts
`habits_and_virtues_screen` from the tab and from More (back goes to Home).
`RemindersTest` covers data only: the catalog of 7 and novena day math. The
screenshot destination `habits_virtues` renders the first screen.

### A2. `FidelityCalendar` (public, used by Home inside its `FidelityCard`)

- Signature: `FidelityCalendar(counts: Map<String,Int>, currentLanguage, modifier, initialMonth = YearMonth.now())`. Keep it.
- Data: `FidelityStore(dao).counts`, which counts distinct habit ids per date (all ids, including legacy `habit_*` rows), and `FidelityStore.cumulativeForMonth`.
- Content:
  - Month header with prev/next arrows.
  - Two-letter Monday-first weekday names.
  - Day cells showing the number and "count·cumulative". Days with checks are tinted; today is in gold.
  - Footer "N prácticas este mes".
- Tags: `fidelity_calendar`, `fidelity_prev_month`, `fidelity_next_month`, `fidelity_day_<d>`, `fidelity_month_total`.
- Content descriptions: "Mes anterior" / "Mes siguiente" (EN and PT too).

### A3. `TemptationCalendar` (public)

- Signature: `TemptationCalendar(counts, currentLanguage, modifier, initialMonth, fellCounts: Map<String,Int>? = null)`. Keep it.
- Content: a text toggle "Vencí / Caí" (shown only when `fellCounts` is passed), the same grid as above, and the footer "N vencidas este mes" or "N caídas este mes".
- Tags: `temptation_calendar`, `temptation_tab_victories`, `temptation_tab_falls`, `temptation_prev_month`, `temptation_next_month`, `temptation_day_<d>`, `temptation_month_total`.
- Content descriptions: the same month-arrow descriptions as A2.

### A4. `RemindersScreen` (public; today it is only an overlay inside Habits)

Signature: `RemindersScreen(repository, onBack = {}, modifier)`. Keep it.

**Data**
- `ReminderStore(ctx)`:
  - `.reminders` (7 prefs: enabled, hour, minute; `angelus_midday` and `coronilla` are ON by default).
  - `.novena` (name and start epoch day), `setEnabled`, `setTime`, `startNovena`, `clearNovena`.
- `ReminderCatalog.DEFAULTS` (7 reminders), each with a trilingual title and body:
  - `angelus_morning` 06:00, `angelus_midday` 12:00, `angelus_evening` 18:00
  - `coronilla` 15:00, `morning_prayer` 07:00, `daily_reading` 08:00, `night_prayer` 21:30
- `ReminderScheduler.schedule / cancel / sendTestNotification`, and `GoogleCalendarSync.syncReminder`.

**Permissions and intents**
- `POST_NOTIFICATIONS`: requested on the first toggle ON.
- Exact alarms: checked with `canScheduleExactAlarms`. The button opens `ACTION_REQUEST_SCHEDULE_EXACT_ALARM`.
- Notification settings: a button opens `ACTION_APP_NOTIFICATION_SETTINGS`.
- `READ_CALENDAR` and `WRITE_CALENDAR`: requested before a calendar sync.

**Surfaces**
1. Header: back icon button and the title "Recordatorios de oración".
2. Permission banner: "Permisos para recordatorios", shown only while something is missing, with one message and one button for each missing permission.
3. "Enviar notificación de prueba" button. It shows a snackbar for sent or failed.
4. "Novena" section:
   - No novena: a name field and "Iniciar novena".
   - Active novena: its name, "Día N de 9" (or "Completada"), a progress bar and "Terminar/archivar".
5. "Recordatorios diarios": 7 cards, each with:
   - the title and the body (notification text)
   - a time pill "HH:MM"
   - a switch
   - an "Añadir a Google Calendar" button
6. Time picker dialog: M3 clock, forced 24 h. "Aceptar" saves and reschedules the alarm if the reminder is enabled.
7. Calendar result dialog: shown only when the sync fails or permission is denied. Success shows a snackbar.
8. Snackbars:
   - "{title} sonará a las HH:MM"
   - "notifications off" warning
   - "added to calendar"
   - "test sent" and "test failed"

**testTags:** `reminders_screen`, `reminders_back`, `reminders_status`,
`test_notification`, `novena_section`, `novena_start`, `novena_finish`,
`reminder_time_<id>` ×7, `reminder_switch_<id>` ×7, `gcal_<id>` ×7.

**Content descriptions**
- Back: "Atrás" / "Back" / "Voltar". The title is also exposed in semantics.
- The novena section is described as "Novena". The day label has the stateDescription "Día N de 9".
- Time pill: "Cambiar la hora del recordatorio".
- Switch: "Activar recordatorio" / "Desactivar recordatorio".
All of these exist in EN and PT too.

---

## b. What is wrong today (specific)

**Habits root**
1. Today's progress appears twice, back to back: the ring card ("0/12") and "Control de cumplimiento" ("Hoy: 0 de 12").
2. The ring is small (112dp), flat, single-colour, and its count is set in the row-title style. It is not the hero that the reference makes it.
3. The hero is an unrelated, dark candle-in-a-bulb photo with palm trees. On the phone there is a **black band** above it (nothing draws under the status bar). The image is not mist-treated in dark mode, so it ends in a hard edge.
4. "Recordatorios de oración" is a full-width saturated green button above all content. This breaks "never green buttons", and it is repeated in both tabs.
5. In the before screenshot **no habit row is above the fold**: the first row is cut by the bottom bar. The primary task (check today) is not visible within one second.
6. **ES bug:** the night group is labelled "Anoitecer", a Portuguese word, in Spanish.
7. The check circle is 28dp with no larger hit area (below 44dp). The text beside it navigates, so the two targets have no visual separation. Rows that open nothing (3 of 12) are dead to taps.
8. A completed title turns accent green, which makes the text multicolour. The chevron is a "›" text glyph, not an icon.
9. **Midnight bug:** `todayDateStr` is remembered once. If the app stays open past midnight, new checks are written to yesterday and the ring never resets.
10. The ring has no animation and the check has no haptic.
11. The month view is a strip of 22dp numbered dots with 15sp numerals clipped inside them, plus a bar with an end-dot artifact. "0 de 96" (12 × the day of month) is unexplained. The far better `FidelityCalendar` is used only on Home.
12. The encouragement line has `maxLines = 1`, so EN can truncate.
13. Dead code:
    - `HabitRow`, `MonoHabitRow` and the custom-habit dialog.
    - The helpers `habitIcon`, `categoryLabel`, `habitSubtitleText` and `habitClockMinutes`.
    - Unused functions: `toggleHabit` and `deleteHabit`.
    - Unused state: `suggestedHabits`, `allDayLabel` and `newHabit*`.
    - A live `getAllHabits()` Room flow whose result is never used.
14. Values are hard-coded throughout: `Color.White`/`Color.Black` check tint, 220dp, 16/10dp padding, `MinTouchTarget = 48.dp`. `frostedCardChrome` has a per-identity radius hack, and the screen has a private `decodeSampledBitmap` copy.

**"En la tentación"**
15. **Data bug.** "Vencí una tentación" always opens the dialog preset to the *first* catalog entry, LUST. Every generic victory is therefore logged as Lujuria → Castidad, which inflates that root's count and the history. It is also presumptuous and indiscreet.
16. The temptation content (sin names with per-sin counts) sits on a tab one tap from the root, visible to anyone glancing at the phone.
17. The dialog is gamified and cramped:
    - The title "¡Victoria sobre la Tentación!" appears before anything has happened.
    - There is a gold shield and "Lujuria / Impureza ➔ Castidad".
    - The prayer is italic in a tinted box.
    - Each stats row packs a label and 3 counts into one `Row`, which overflows at 360dp.
    - There are three stacked buttons, and "Cerrar" duplicates dismissal.
18. "Caí" records a fall with no word of mercy and no path forward (contrition, confession).
19. Root rows concatenate "Virtud contraria: …" with the full advice quote. The result is 3–4-line rows (a wall of text) and an unlabeled trailing count.
20. History bugs:
    - Home's quick log writes `sinKey = "TEMPTATION"` with an empty `counterVirtue`, so its rows read "Victoria en " with nothing after it.
    - The virtue is the string stored in the language active at the time of logging.
    - Dates use `Locale.getDefault()`, not the app language.
21. Stats are visible only inside the dialog.

**Calendars (both)**
22. Cells show a cryptic "2·7" (daily·cumulative) under the number. Cells are clipped to the card radius, so they look like pills. The fill is `accentMuted` and today is gold.
23. Weekday names are 2-letter caps; the month title is lowercase in ES and PT ("noviembre 2026"). Monday-first is forced for EN-US users.
24. Navigation runs into empty future months forever. There is no swipe and no selected-day detail.
25. The temptation switch is two clickable `Text`s, not a control.
26. `FidelityCalendar` wraps itself in an `AppCard` with gutter padding. Home nests it inside another `AppCard`: card in card, double padding, a cramped grid. Day intensity ignores the goal of 12, and legacy `habit_*` ids can push a day above 12.

**Reminders**
27. Each reminder is its own card with three controls (time pill, switch, a full-width "Añadir a Google Calendar"). That makes 21 controls in 7 cards: heavy and repetitive, and nothing like iOS Settings.
28. `Switch` is forced to `size(48.dp)`, which squashes its 52×32 track.
29. The notification body ("Es hora del Ángelus…") repeats the title as a subtitle.
30. "Enviar notificación de prueba" is the first action on the screen; it is a debug tool given top priority.
31. Header: an M3 `ArrowBack` beside "Recordatorios de oración" in the screen-title style, which wraps at 360dp. `Scaffold` uses the default M3 container; snackbars are dark M3 bars.
32. Novena:
    - The name field is an M3 `OutlinedTextField`.
    - The label "Terminar/archivar" uses a slash.
    - "Completada" appears twice when the novena is complete.
    - The bar uses the default M3 colour.
33. The permission banner never re-checks on resume. `permissionTick` bumps on click, before the user comes back from Settings, so the banner stays stale.
34. The time picker is an M3 clock dial in an AlertDialog, forced to 24 h even in EN. Snackbars always format times as `%02d:%02d`.
35. **Functional bug:** the default-ON reminders (midday Angelus, Chaplet) show ON but are **not scheduled**. `rescheduleAll` runs only on `BOOT_COMPLETED`, so they never fire until a reboot or a manual toggle.
36. Each "Añadir a Google Calendar" tap inserts a **new** recurring event. Duplicates pile up and there is no "added" state.
37. System back while Reminders is open leaves the Habits tab. The overlay is local state with no `BackHandler`, so MainApp pops the destination.
38. Home cannot open Reminders (DESIGN asks for bell → reminders) because it is private overlay state.

---

## c. The new design

Grammar: canvas gradient, glass groups, one accent per screen (success green
for checks and rings on Habits; nothing coloured on the temptation page), no
icon wells, no ornaments. Every value comes from a token.

### C1. Navigation model

The Habits tab hosts three pushed sub-pages as local state:
- `Temptation`
- `Summary` (stats)
- `Reminders`, unless MainApp provides a route (see §e)

Implementation:
- State: `rememberSaveable` `HabitsPage` enum, shown through `AnimatedContent`. Push: slide 24dp + fade 250ms. Pop: the reverse.
- Each sub-page registers `BackHandler { pop }`.
- The bottom bar stays visible (iOS tab-stack behaviour).
- Every sub-page ends above the floating bar, using the foundation's bar-clearance inset.
- New optional parameter `onOpenReminders: (() -> Unit)? = null`. When MainApp passes a real destination, the root row uses it; otherwise the local page is used.

### C2. Habits root (`tab_habits_rule` on its LazyColumn; `habits_and_virtues_screen` on the outer Box)

Top to bottom, 20dp gutter:

1. **LargeTitleHeader**, as in the reference panel and the DESIGN blueprint.
   - Eyebrow (`eyebrow`, inkSecondary): today's full date, e.g. "Domingo, 8 de noviembre". The checklist is "today", so the date makes rollover explicit.
   - Title (`largeTitle` 34/40): "Hábitos" / "Habits" / "Hábitos". The tab label already says Hábitos, and "Hábitos y Virtudes" at 34sp is ≈ 336dp, which collides with the action.
   - Trailing glass circle 40dp (44 hit), outline bar-chart glyph (`habits_stats_button`, content description "Resumen" / "Summary" / "Resumo") → **Summary**.
   - TopBar on scroll: when the title leaves, a blurred glass bar with an inline 17sp "Hábitos" fades in.
2. **Ring block.** No card: the ring sits on the canvas, as in the reference. Tag `screen_header_illustration`, since it is the screen's header visual now.
   - **ProgressRing** 208dp, stroke 16dp, round caps.
   - Track: `ringTrack`.
   - Arc: sweep gradient `ring` `#CFE6C6 → #4E9F4A` running from 12 o'clock along the sweep. At 12/12 the ring closes, with a soft end-cap shadow (the Apple Fitness overlap).
   - Centre, stacked:
     - The **sprout** glyph, 28dp, filled `success` gradient (the only filled glyph, by reference).
     - The numerals `ringValue` (≈ 56/60, weight 300–400, tracking −1.5, tabular): "**3**" in `success`, then "/12" in inkSecondary.
     - The caption `subhead` inkSecondary "Hábitos de hoy" / "Today's habits" / "Hábitos de hoje".
   - Below the ring, 16dp gap: the encouragement line in `subhead` inkSecondary, centred, max 2 lines. The three existing messages are kept verbatim.
   - Semantics: the existing content description "N de 12 cumplidos hoy".
   - Motion: the sweep animates with a spring (≈ 600ms) on every change. The sprout scales 0.9→1.0 with progress (subtle growth, no confetti). At 12/12 the message cross-fades to "¡Día consagrado en plenitud!" and that is all.
3. **Three CheckRow groups.**
   - Section header (`headline`): "Mañana", "Día", "Anochecer" (ES fixed from "Anoitecer"; EN "Morning / Day / Nightfall"; PT "Manhã / Dia / Anoitecer"). The trailing `footnote` inkSecondary reads "2 de 4" (EN "2 of 4", PT "2 de 4").
   - Glass list group (radius 20), inset hairlines. Tags `habits_checklist_card`, `_1`, `_2`.
   - **CheckRow**, minimum 60dp (tag `habit_card_<id>` on the row):
     - Leading check circle, 26dp visual inside a 44dp hit area (tag `toggle_habit_<id>`). `toggleable(role = Checkbox)`, keeping the existing stateDescription.
     - Pending: 1.5dp inkTertiary ring.
     - Done: `success` fill with an `onSuccess` check that springs in (scale 0.6→1, damping 0.8) and a toggle haptic.
   - Title in `body` ink. It stays ink when done, as in the reference.
   - Subtitle in `footnote` inkSecondary, maximum 2 lines. All 12 subtitles are kept.
   - Trailing outline chevron (20dp, inkTertiary), only on the 9 rows that open something.
   - Tap targets:
     - Circle: toggles.
     - Rest of a linked row: opens the practice through the existing callbacks, unchanged.
     - Rest of an unlinked row: toggles as well, so no dead area remains.
   - The press state is a glass highlight, not a ripple.
4. **"En la tentación" ImageCard** (full width, 168dp, radius 22, tag `open_temptation`) → **Temptation** page.
   - Art: Hofmann's *Christ in Gethsemane* (1890, public domain), mist-treated, with a bottom scrim.
   - White title `title2` "En la tentación" / "In temptation" / "Na tentação".
   - Meta `footnote`: «Velad y orad» (Mt 26,41) / "Watch and pray" / "Vigiai e orai".
   - It shows no counts and no sin names, so the root stays discreet.
5. **Fidelidad** (section header `headline` "Fidelidad" / "Fidelity" / "Fidelidade") → **FidelityCalendar** in its glass card (C5). Called with:
   - `dailyGoal = 12`
   - counts from `getAllCompletions` filtered to the 12 rule ids
   - legacy day tags `habit_month_day_<d>`
   - Footer: "34 de 96 prácticas · 8 días activos" (`fidelity_month_total`).
6. **Recordatorios.** One glass group row:
   - leading outline bell (22dp ink)
   - "Recordatorios" (body)
   - trailing value "3 activos" (EN "3 on", PT "3 ativos"), read from `ReminderStore.reminders`
   - chevron
   - tag `open_reminders`
   It routes to `onOpenReminders` or to the local page.
7. Bottom clearance for the floating bar.

**Above the fold** (411×891dp render): header ≈ 0–150, ring block ≈ 160–440,
message ≈ 452–492, "Mañana" header ≈ 520, about 3 rows visible above the bar.
The primary task is obvious within one second.

### C3. Summary page ("Resumen" / "Summary" / "Resumo"; root tag `daily_completion_control`)

1. Glass back circle (`habits_stats_back`, "Atrás"). `largeTitle` "Resumen", with the eyebrow set to the month: "Noviembre de 2026".
2. Three glass stat tiles in one row (radius 20). Values in `title2` tabular; labels in `caption` inkSecondary:
   - "Hoy" "3 de 12"
   - "Este mes" "34 de 96" (`habits_monthly_summary`; its content description adds "8 días activos")
   - "Días activos" "8"
   A thin **ProgressBar** for the month rate sits below (stateDescription "34/96").
3. "Por práctica": three glass groups (Mañana, Día, Anochecer). Each of the 12 rows shows:
   - the title in `body`
   - a thin bar of days kept / days elapsed this month
   - a trailing `footnote` "12 de 30 días"
   Data: `getAllCompletions` (distinct date per habit). New insight from existing data; nothing invented.
4. Empty month: every bar at 0 and the footer "Cada práctica cuenta desde hoy." No blank screen.

### C4. Temptation page ("En la tentación"; `tab_sin_avoider` on its LazyColumn)

Tone: quiet, merciful, private. No exclamation marks, no gold, no red, no
badges, no streak flames. Success green is not used here; the only colour is
the art.

1. **DetailHeader**:
   - Gethsemane art, 280dp from the top, misted (light: lift and fade to canvas; dark: fade to night canvas).
   - Glass back top-left (`temptation_back`).
   - Title `title1` "En la tentación".
   - Epigraph in `callout` inkSecondary: «Velad y orad para no caer en tentación» (Mt 26,41), in EN and PT too.
2. **"Reza ahora" card** (glass card, radius 24, padding 20). This is the help, above the fold.
   - Eyebrow "Detente. Respira. Reza." (EN "Stop. Breathe. Pray.", PT "Pare. Respire. Reze.").
   - Title `title2` "Ave María".
   - The Hail Mary in `reading` typography (the user's reader font). It is upright, not italic, and has no tinted box.
   - Trailing round glass play/stop button (`temptation_listen`, content description "Escuchar oración"). It toggles on `ttsManager.isPlaying`; TTS stops on pop or dismiss.
3. **Two capsules**, side by side. They stack when the content is too wide for one line:
   - PrimaryCapsule "Vencí" (EN "I overcame", PT "Venci"), `emergency_overcome_button` → Victory sheet.
   - SecondaryCapsule "Caí" (EN "I fell", PT "Caí"), `temptation_fell_button` → Fall sheet.
4. **"Tu camino"** (`headline`): a glass card with a 2×3 stat grid. Rows are "Vencí" and "Caí"; columns are "Hoy", "Semana", "Mes". Values in `title2` tabular, labels in `caption`. All six tags are kept (`victory_stats_*`, `fall_stats_*`), and all six values are visible at once.
5. **TemptationCalendar** (C6).
6. **"Virtudes contrarias"** (EN "Remedy virtues", PT "Virtudes contrárias"), footer "Una para cada pecado capital".
   - Glass group of 7 rows (`card_overcome_<KEY>`). Title: the counter-virtue (`body`, ≤ 2 lines). Subtitle: the sin (`footnote`).
   - Trailing: the victory count in `footnote` tabular, with its content description "N victorias", then a chevron.
   - The advice moves into the sheet.
   - Tap → Root sheet.
7. **"Victorias en Cristo"** (trailing "N"): glass group, last 10 entries, each with:
   - Title: "Victoria · {virtue}". The virtue is resolved from `sinKey` in the *current* language; generic `TEMPTATION` entries read "Tentación vencida".
   - The «note» (if any) in `footnote`.
   - A trailing date formatted in the app language.
   - Tag `victory_history`.
   - Empty state: "Cuando venzas una tentación, quedará aquí como acción de gracias."
8. Optional link row "Oración a San Miguel Arcángel" → `onOpenPrayer("st_michael")`. This is the traditional prayer against temptation; it needs approval (§e).

**Sheets** (one host, glass sheet radius 28, drag handle, scrim; TTS stops on
dismiss):

**Victory sheet**
- Title `title2` "Gracias a Dios" / "Thanks be to God" / "Graças a Deus".
- Body `callout`: "Anótala como acción de gracias."
- Optional root chips (7 capsules plus "Sin especificar", which is the default). This fixes bug 15: generic victories log `TEMPTATION`.
- Glass note field "Nota u ofrenda (opcional)".
- PrimaryCapsule "Registrar victoria" (`confirm_victory_button`). It writes the Room log (selected root, or `TEMPTATION`) and `recordVictory()`.
- After saving: a glass toast "Victoria registrada".

**Fall sheet**
- Title `title2` "Levántate" / "Rise again" / "Levante-se".
- Body in `reading`: «Dios no se cansa de perdonar…». Pide perdón con un acto de contrición y vuelve a empezar; si es grave, acude a la confesión.
- Row "Acto de contrición" → `onOpenPrayer("act_of_contrition")` (the prayer exists).
- PrimaryCapsule "Anotar y empezar de nuevo" (`record_fall_button`) → `recordFall()`.
- Text button "Cancelar".

**Root sheet** (from a virtue row)
- Title `title2` showing the virtue; under it, the sin in `footnote`.
- The advice in `reading`.
- A compact Hail Mary with listen.
- The note field.
- PrimaryCapsule "Registrar victoria" (`confirm_victory_button`, with this root).
- SecondaryCapsule "Caí". It swaps the sheet to the Fall content.

### C5. FidelityCalendar (Apple Fitness monthly view)

- **Container.** A glass card (radius 24, padding 20), unless `framed = false`. With `framed = false` it renders only the content, with no card and no gutter, so Home can nest it without card-in-card.
- **Header.**
  - The month title in `headline`, left-aligned: "Noviembre 2026". Use the standalone `LLLL yyyy` format and capitalise the first letter.
  - Prev and next as 32dp glass circles with 44dp hit areas, at the right.
  - Next is disabled on the current month, so there are no empty future months.
  - Horizontal swipe between months (pager); the arrows remain.
- **Weekday row.** `TextStyle.NARROW` initials in `caption` inkTertiary: ES "L M X J V S D", EN "S M T W T F S", PT "D S T Q Q S S". The first day of the week follows the locale's `WeekFields`.
- **Day cells.**
  - The grid uses 7 equal columns. Each cell is about 47×48dp, and the whole cell is the tap target.
  - Each day is a **mini ProgressRing**: 36dp, 3.5dp stroke, `ring` gradient. The arc shows `min(count, goal) / goal`; a full ring means 12 of 12.
  - The day number sits inside the ring in `caption` tabular.
  - Today: the number is semibold ink and the track is slightly stronger.
  - Future days: the number is inkTertiary, with no track.
  - With `dailyGoal == null` (legacy mode): a soft filled disc whose opacity follows the count tiers.
- **Selected day.** Tapping a day shows a detail line under the grid, in `footnote`: "Martes 4 · 7 de 12 · 41 en el mes". This keeps the daily and cumulative information that "2·7" used to carry, now legible.
  - The detail line cross-fades on change.
  - Each cell's content description is the full sentence.
- **Footer.** `footnote` inkSecondary (`fidelity_month_total`). With a goal: "34 de 96 prácticas · 8 días activos". The possible total is 12 × the days elapsed in the current month, or the full month for past months. Without a goal: "34 prácticas este mes".
- **Tags.** All kept: `fidelity_calendar`, `fidelity_prev_month`, `fidelity_next_month`, `fidelity_day_<d>`, `fidelity_month_total`. Optionally `habit_month_day_<d>` on an outer wrapper of each cell (used by Habits).
- **Signature.** The existing parameters stay; additions are optional with defaults, so Home compiles unchanged:
  ```kotlin
  fun FidelityCalendar(
      counts: Map<String, Int>,
      currentLanguage: AppLanguage,
      modifier: Modifier = Modifier,
      initialMonth: YearMonth = YearMonth.now(),
      dailyGoal: Int? = null,
      framed: Boolean = true,
      legacyDayTagPrefix: String? = null,
  )
  ```
  Recommendation for Home: pass `dailyGoal = DailyRule.size, framed = false`.
- **Public `DailyRule`.** Expose `DailyRule` (the 12 ids, groups and size) from `HabitsAndVirtuesScreen.kt`. Home's card then says "N de 12" instead of the seeded-habit count of 7 it shows today, and the two screens agree.

### C6. TemptationCalendar

- It uses the same skeleton as C5.
- **Victory/fall switch.**
  - A glass SegmentedControl "Vencí | Caí" with tags `temptation_tab_victories` and `temptation_tab_falls`, shown only when `fellCounts != null`.
- **Day cells.**
  - Days with entries get a monochrome ink disc at 10, 18 or 28 % for 1, 2 and 3+ entries. There is no ring here because there is no goal.
  - The same style is used for victories and falls, so falls are never drawn in red.
- **Selected-day line.** "Martes 4 · 2 vencidas · 7 en el mes".
- **Footer** (`temptation_month_total`): "7 vencidas este mes" or "3 caídas este mes".
- **Tags** all kept. The signature is unchanged; `framed` is added the same way as in C5.

### C7. Reminders (iOS Settings-grade)

Root: a `Box` instead of `Scaffold`, drawn edge to edge. Tags: `reminders_screen` on
the LazyColumn and `BackHandler { onBack() }`.

1. **Header.**
   - Glass back circle (`reminders_back`, "Atrás").
   - Optional compact art header. Millet's *L'Angélus* (1857–59, public domain) fits well: the bell that calls workers to prayer was the first prayer reminder. If art is not approved, use a LargeTitleHeader.
   - `largeTitle` "Recordatorios" / "Reminders" / "Lembretes". The semantics keep the full "Recordatorios de oración".
   - Subhead inkSecondary: "3 activos · Próximo: Coronilla, 15:00", computed from the prefs with `nextTriggerMillis`.
2. **Permissions card** (`reminders_status`), shown only while something is missing. It re-checks on `ON_RESUME`.
   - Glass card with up to two rows, separated by a hairline. Each row has:
     - a leading outline icon (bell-off, or alarm-off for exact alarms)
     - a `headline` ("Notificaciones desactivadas")
     - a `footnote` (the existing text)
     - a trailing compact capsule ("Activar" / "Permitir") that opens the same Settings intents as today
3. **"Diarios"** (section header "Recordatorios diarios"). One glass group of 7 rows, sorted by effective time and reordered with `animateItem`. Each row (minimum 72dp) has:
   - **Time** in `title1` tabular with locale 12/24 h ("6:00 AM" in EN). Tag `reminder_time_<id>`; content description "Cambiar la hora…". It is ink when on and inkSecondary when off, as iOS Alarms dims inactive alarms.
   - **Title** below the time, in `subhead` inkSecondary, maximum 2 lines.
   - **Trailing switch** (`reminder_switch_<id>`) at its natural size, inside a 48dp touch box. The content descriptions are unchanged.
   - Tapping the row or the time opens the **Reminder sheet**.
   - The notification body moves into the sheet's preview.
4. **Novena** (`novena_section`, section header "Novena").
   - None active: a glass group with an inline glass text field "Nombre de la novena", then PrimaryCapsule "Iniciar novena" (`novena_start`), disabled while the field is blank.
   - Active, in a glass card:
     - Eyebrow "Novena en curso".
     - Title `title2`: the name.
     - Footnote "Día 4 de 9", keeping the stateDescription.
     - **Nine-segment progress:** 9 capsules with 6dp gaps. Done segments are `ring` green, today has a ring, and future segments are track.
     - When complete, "Completada" appears once with a check.
     - A glass group row "Terminar novena" in ink (`novena_finish`).
5. **"Comprobar".** A row "Enviar notificación de prueba" (`test_notification`) with an outline bell, and the footer "Así verás tus recordatorios".
6. **Footer note** in `footnote`: "Tus recordatorios se guardan solo en este dispositivo." This is true; the store is local DataStore.
7. **On open:** `LaunchedEffect` → `ReminderScheduler.rescheduleAll(ctx, lang)`. It is idempotent and makes the default-ON reminders real (fixes bug 35).

**Reminder sheet** (glass, radius 28)
- Title `title2`.
- **Time wheel:** hour and minute (and AM/PM when 12 h) with snapping. The centre band uses `glassStrong`.
  - TalkBack treats each column as an adjustable value (`setProgress`, increment and decrement), with the stateDescription "6 horas", "30 minutos" and so on.
  - The fallback is the M3 `TimeInput`, restyled.
- Footnote "Cada día".
- **Notification preview:** a small glass card with the app glyph, the title and the body. The body text is kept here.
- Group row "Añadir a Google Calendar" (`gcal_<id>`) with an outline calendar icon. The permission flow is unchanged.
- PrimaryCapsule "Guardar". It saves the time and reschedules when the reminder is enabled. The toast "Sonará a las 6:00" is localised and its format follows the locale.

**Messages**
- All snackbars become a glass toast capsule above the bar.
- The calendar failure dialog becomes a glass alert with "Cerrar".

### C8. Dark mode ("night chapel")

- Ring track: `#FFF` at 8 %. Ring gradient (dark): deep moss to a softer green, e.g. `#2F4F2C → #6DBE67`. Success in dark: `#5DB85F` with an ink-dark check.
- No glow. The 12/12 closure keeps only the end-cap shadow, at 45 % black.
- Glass is white at 7 %, with a 14→4 % edge.
- Art (Gethsemane, Angelus) fades into `#171614 → #0E0D0C`, and the ImageCard scrim deepens.
- Calendar discs use ink `#F3F0EB` at 12/20/32 %.
- The switch off-track is `#FFF` at 16 %.
- Status bar icons are light and there is no black band in either mode.
- Every screen is rendered in both modes and judged separately.

### C9. ES / EN / PT lengths (gutter 20, 411dp wide)

| String | Longest | Treatment |
|---|---|---|
| Large title | "Recordatorios" (13) | Fits at 34sp ≈ 240dp |
| Ring caption | "Hábitos de hoje" (15) | Inner diameter 176dp; fits at 15sp |
| Encouragement | EN "Good progress — persevere until nightfall." (42) | Fits in 2 lines max |
| Habit title | EN "Divine Mercy Chaplet 3:00 PM" (28) | One line at 17sp |
| Habit subtitle | ES "Padre Nuestro, Ave María, Gloria, Ángel de la Guarda…" (52) | 2 lines at 13sp |
| Virtue | "Generosidad y Desprendimiento" (29) | ≤ 2 lines |
| Reminder title | "Coronilla de la Divina Misericordia" (35) | Subhead 15sp ≈ 255dp; one line, 2 max |
| Capsules | PT "Anotar e recomeçar" / ES "Anotar y empezar de nuevo" (25) | Full width in the sheet |
| Stat labels | "Semana" / "Week" / "Semana" | Caption |
| Temptation card | "En la tentación" (15) | 168dp tall card, full width |

Every screen is rendered with `-Pshots.lang=es` and `pt`, plus EN.

### C10. Loading and empty states

- Room and DataStore flows start with `null` and show a placeholder state, so switches never flicker OFF→ON and the ring never flashes 0. A ring drawn with only its track is the loading state.
- The empty states for history, months and the per-practice view are listed above.
- When the hero art fails to decode (Robolectric), a canvas gradient is shown.

---

## d. Component needs (for the foundation owner)

Required from the shared kit. Each one needs a `modifier` and testTag
pass-through.
1. **Canvas**, **GlassSurface**, **LargeTitleHeader** (eyebrow plus one trailing glass action), **DetailHeader** (art, back, `title1`, subtitle slot, image testTag param), **TopBar-on-scroll**, and a **bottom-bar clearance** inset helper.
2. **GlassCircleButton**: 40dp visual, 44dp hit, content description, enabled state.
3. **ProgressRing**:
   - Parameters: `progress`, `size`, `stroke`, `animate`, a centre slot and `contentDescription`.
   - The gradient must run along the sweep, from 12 o'clock.
   - Round caps, plus an end-cap shadow when progress ≥ 1.
   - It must look right from 36dp (calendar) to 208dp (hero).
   - Tokens: `ringTrack`, `ringStart`/`ringEnd` (light and dark).
4. **ProgressBar**: thin capsule, 4dp, with a stateDescription. Also a **SegmentedProgress(n, done, current)** for the 9-day novena (or I build it privately).
5. **CheckRow**:
   - Parameters: `title`, `subtitle?`, `checked`, `onCheckedChange`, `onClick?`, `showsChevron`, `checkModifier` (for `toggle_habit_<id>`), and a stateDescription.
   - The check is a 44dp toggleable checkbox with a spring fill and haptic.
6. **GroupedList / Row** with these slots:
   - leading icon, title, subtitle
   - trailing value text, chevron, or a **trailing switch** slot
   - a custom leading block, needed for the large time in Reminders
   Rows are at least 52dp, with inset hairlines and a section header with trailing text plus a footer.
7. **AppSwitch** (glass grammar).
   - Decision needed: the on-track colour. I recommend `success` (iOS Settings); the alternative is `action`.
8. **SegmentedControl** with a per-segment testTag (`temptation_tab_*`).
9. **PrimaryCapsule / SecondaryCapsule / TextButton** with an optional leading icon, a `fillWidth` option and a disabled state.
10. **Sheet**: glass, radius 28, handle, scrim, `onDismiss`. Plus **GlassAlert** and **GlassToast** (snackbar host).
11. **GlassTextField**: placeholder or label, single and multi-line.
12. **ImageCard**:
    - Asset, height and testTag parameters.
    - A bottom scrim, with the title and meta in white.
    - Full width or 2-up.
13. **StatTile / StatGrid** cell: value in `title2` tabular, label in `caption`.
14. **Type tokens.** A `ringValue` numeral style (≈ 56/60, light, −1.5 tracking) and a tabular-figures variant (`tnum`) of `title1`, `title2`, `footnote` and `caption` for counts and times.
15. **Glyphs** (outline, ink): bar-chart, bell, bell-off, alarm-off, calendar, shield, chevron, play/stop. A filled two-leaf **sprout**, available as `DevotionalGlyphs.sprout`; otherwise I keep a private `ImageVector`.
16. **Press-scale modifier** (0.97, spring 0.8 / 600) and a **toggle haptic** helper.

Private to my files, unless the foundation wants them shared:
- `TimeWheelPicker` (accessible)
- the month-pager calendar skeleton shared by C5 and C6
- `DailyRule`
- the temptation sheet state machine

---

## e. Requests and open questions for the orchestrator

1. **Reminders route.** Add `AppDestination.REMINDERS` (or a MainApp-level overlay) so Home's bell and the Habits row both reach it and back works globally. Until then Habits keeps the local page with a `BackHandler`. `RemindersScreen` also installs its own `BackHandler`.
2. **Home owner.**
   - Adopt `FidelityCalendar(…, dailyGoal = DailyRule.size, framed = false)` and "N de 12" from `DailyRule`.
   - Home's quick "Vencí" logs `TEMPTATION` with an empty virtue. That is fine now that history resolves it.
3. **Data and app-start owners.**
   - Call `ReminderScheduler.rescheduleAll` at app start and on language change (MainActivity).
   - `ReminderStore`: persist the Google Calendar event id per reminder, so the sync updates the event or shows "Añadido" instead of duplicating.
   - `FidelityStore`: add a variant that takes the 12 ids (I filter locally meanwhile).
   - Weekly counts are Mon–Sun, while the calendar grid follows the locale. This is a minor mismatch.
4. **Art.**
   - What does `heroes/lumen/habits.jpg` depict?
   - My plan uses H. Hofmann, *Christ in Gethsemane* (1890, PD) for the temptation card and page (`heroes/lumen/temptation.jpg`).
   - It uses J.-F. Millet, *L'Angélus* (1857–59, PD) for Reminders.
   - If `habits.jpg` is one of these, I reuse it.
5. **Approvals.**
   - Delete the dead custom-habit code, including the unreachable tags `input_habit_title` and `save_custom_habit_button`.
   - Optional links:
     - meal blessing → `grace_before_meals`
     - offering → `morning_offering` (today it opens the Prayers tab)
     - a St Michael row on the temptation page
6. **Switch colour** (d7).

## f. Build order and verification (after the foundation API lands)

1. Calendars (C5, C6) with the signature-compatible parameters; Home still compiles.
2. Habits root (C2), with the midnight fix (today date ticks on `ON_RESUME` and at midnight) and `DailyRule`.
3. Temptation page and sheets (C4).
4. Summary (C3).
5. Reminders (C7).
6. Delete dead code (after approval).

After each step: `tools/device/gradle.sh -q :app:assembleDebug`. Then render
`-Pshots.only=habits_virtues` in light and dark, with ES and PT. Run
`AppNavigationTest`, `DesignNavigationTest`, `RemindersTest` and
`DesignContrastTest`. Then a short phone pass covering the check toggle, the
month swipe, the temptation flow, the reminder sheet and the permission
banner on resume. Write the review to `docs/redesign/reviews/habits-round1-builder.md`.
