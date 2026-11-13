# Independent quality gate — CatholicGPT

Reviewer: independent critic, 12 November 2026. This is an evidence ledger, not a score or a claim of perfection. No app edits, Gradle invocation, commits, or device tests by this reviewer. The app is changing concurrently; source findings describe the inspected snapshot and need rechecking against final source.

## Evidence and status

Read `AGENTS.md`, the 12 November audit, historical review README/VALIDATION, MainApp, screen sources, and screenshot harness. Visually opened historical Home round-4 light/dark and Rosary round-4 Hail Mary light/dark. These are not new renders, and the historical Home is an extended scroll image rather than proof of a usable phone viewport. Fresh Lumen/Sanctuary frames are pending. No final visual acceptance is given.

The explicit current request for two identities overrides AGENTS' older three-identity requirement. Documentation must be reconciled so new contributors do not restore obsolete implementations. Historical test totals and high ratings are not current evidence.

## Measurable acceptance rubric

Every gate is required. Passing the gates establishes a usable baseline, not exceptional visual quality.

| Gate | Concrete acceptance | Evidence required |
|---|---|---|
| Clear next step | At 360×800dp and 320×760dp, normal text, initial Today viewport shows one complete primary action with a descriptive label, useful duration/context, and no scroll needed to begin. | Fresh phone viewport captures, plus click reaches actual experience. |
| Identity integrity | Exactly Lumen and Sanctuary selectable; each has light/dark/system independently. Every root and detail uses its identity; shared destinations, content, preferences, actions and saved session survive switching. Sanctuary must differ in typography, composition, shape, depth and control treatment, not merely accent color. | Four-variant matrix for Today, prayer session, reader, Explore, settings; spot checks all secondary destinations. |
| Readability | Normal body text ≥16sp, primary prayer/Scripture ≥18sp, metadata generally ≥12sp; no actionable captions shrunk to fit. 200% system text reflows; complete required labels/content reachable, without overlap/ellipsis hiding essential meaning. Intentional user-controlled reading reduction is explicit. | 320dp at 100% and 200%; long ES/PT labels; rendered text-layout bounds. |
| Contrast | Final composited normal text ≥4.5:1; large text ≥3:1; meaningful control boundaries/state indicators ≥3:1 against adjacent surface. Disabled elements assessed separately. Artwork behind labels has a tested scrim. | Sample final colors/composites; source palette checks alone insufficient. |
| Targets and interaction | Every independently actionable control has nonoverlapping ≥48×48dp hit/semantic bounds, including close, icon, segment, bead-jump, chapter navigation and text controls. Tiny visible glyphs may sit inside larger targets. | Bounds assertions or on-device inspection, not visual guessing. |
| Stable prayer | Complete prayer available; Next/Previous remain reachable without horizontal squeeze or unexpected motion; labels describe phase and state. Restart deliberate. Tab change, identity change, rotate, recreate process and relaunch resume exact session. | Functional journey and large-text frame; do not infer retention from `rememberSaveable` alone. |
| Stable reading | Opaque reading surface, bounded tablet column, exact reference/edition, correct first/middle/final verse resume; reference return restores originating explanation and position. | Reader frames and behavioral checks. |
| Truthful controls | Every CTA does what it promises; audio distinguishes recorded music/chant from synthetic narration. Downloads reflect actual edition/completeness. No placeholders presented as working actions. | Trace action→state/data; runtime where available. |
| Honest content | Authoritative source, edition/language/fallback and actual date visible where relevant; no unsupported universal obligation claims, saint-art mismatch or scraped marketing. | Content audit regressions and spot inspection of rendered pages. |
| State completeness | Loading, empty, filtered no results, permission denied, offline/stale, partial download, error/retry, completed and active states have useful localized copy and a next action. | State inventory plus representative captures/actions. |
| Assistive access | TalkBack order follows visual task; selected/checked/expanded/progress states spoken in current language; focus restored after sheet/navigation; no unlabeled icon controls or duplicate nested click traps. Keyboard/switch reachable. | Semantic checks and TalkBack device evidence where available; name unsupported evidence explicitly. |
| Calm alternatives | Reduced motion respected for all app-owned art/transition loops, reduced transparency has equally usable solid surfaces, no surprise audio/chime. | Preference toggle checks and corresponding render. |
| Safe areas/adaptation | Dock never obscures final actionable content; landscape/keyboard leave exit and primary actions reachable; inset handling works on pushed screens. | Final scroll and IME/landscape screenshots. |
| Delivery integrity | Build/test result corresponds to final source/APK; package/version/signature and SHA documented; historical APK preserved. | Final output and provenance manifest. |

## Reachable experience inventory

MainApp currently declares 18 destinations, including six root destinations. Proposed architecture may regroup these, but no content/action should become orphaned.

| Entry/current class | Nested experiences to retain and inspect |
|---|---|
| HOME / `HomeScreen` | Date/season; readings/Mass and listen; daily verse; scheduled prayer; habits progress; saint; question; shortcuts; appearance/language/font entry; loading/stale. |
| BIBLE / `BibleScreen` | Book directory/search/filter; chapter/verse reader; cross-reference trail and study; reading font/scale/spacing; speech; Mass; versions/downloads; `ScriptureStudyScreen` tracks and progress. |
| ROSARY / `RosaryScreen`, `RosarySession`, `RosaryNarration` | Rosary and Divine Mercy; mystery picker; opening/decade/closing phases; selected bead; narration/play/pause/previous/next; options; guide/book view; completion/restart. |
| PRAYERS / `PrayersAndNovenasScreen` | Prayer categories/search; ordinary prayer detail/audio/share/reading sheet; how-to-pray; chaplet; novena index/filter/search/intention; overview/day/prayer/progress/reset; Night (`NightPrayersScreen`) examen/traditional/office/antiphon. |
| HABITS_VIRTUES / `HabitsAndVirtuesScreen` | Rule/practice rows; checked state; summary/month; reminders; temptation root/victory/fall sheets; `FidelityCalendar` and `TemptationCalendar`; privacy and edit/delete. |
| MORE / `MoreScreen` | Study, inner life, church today; appearance customizer, fonts, language. Check settings discoverability and final-scroll dock clearance. |
| CATECHISM / `CatechismScreen` | Cover/index/part/section/paragraph reader; search; exact deep link; local reader settings; Compendium index/filter/summary; return history. |
| PILGRIMAGE_HABITS / `PilgrimageAndHabitsScreen` | Calendar/today/upcoming; shrine directory/detail; external maps; date parsing; calendar export/sync if exposed. |
| DOCTRINE and STUDY_DOCTRINE / `DoctrineAndHistoryScreen` + `DoctrineMoral`, `DoctrineParts`, `DoctrineFormat` | Moral teaching lists/detail; expandable sections; Scripture/CCC references and return. |
| STUDY_MARY_JESUS / same root + `DevotionsExplorerScreen` | Marian titles/apparitions and Christ devotions; category/region/search; detail/source/status/art. |
| STUDY_HISTORY / `DoctrineHistory` | Timeline/topic/detail; citations; long reading and return. |
| QUESTIONS / `DoctrineQuestions` | Topic/search; question answer; Home exact-index link; Bible/CCC references and originating context. |
| STUDY_ORDERS / `DoctrineOrders` | Orders/offices/category/detail; accurate terms and source. |
| CHURCHES_NEWS / `DoctrineNews` | Feed/loading/empty/error/stale; story; `InAppBrowser` and external fallback; share/source. |
| PARISHES / `ParishFinderScreen` | Manual query submit; near-me external Maps; Mass/confession/adoration quick searches; no handler/error. |
| SPIRITUAL_LIFE / `SpiritualLifeScreen` | Journal list/filter/empty; composer/draft/save/answered/delete; quiz/options/explanation/next; Stations directory/detail/transport/audio; Gregorian chant list/lyrics/translation/audio. |
| REMINDERS / `RemindersScreen` | Enable/times; notification and exact-alarm permissions; time-wheel 12/24h; active novena/end; preview; disabled/denied states. |
| Shared overlays/components | Language selector, `GuiCustomizerModal`, `FontPickerDialog`, reading sheets, confirmation dialogs, image credits, in-app browser loading/error/back/external action. All require four identity-mode variants or representative token/semantic coverage. |

This inventory is source-derived, not proof that each current link works. `SaintHeading`, `CrossReferenceViews`, `RosaryText`, `DoctrineFormat` are supporting UI classes rather than separate route roots.

## Priority defects and audit gaps

### New source-confirmed gap: chant playback misrepresents the experience

`SpiritualLifeScreen`'s chant toggle starts `playSacredChime()` followed by `speakSingle(chant.latinLyrics)` (around lines 339–342 in initial snapshot). The page says “Gregorian chant” and each control says “Listen to [chant]”; this promises sung music but produces a chime and synthetic spoken Latin. Provide licensed actual chant audio, or label the control “Read lyrics aloud · synthetic voice” and remove the surprise chime. Do not claim that this is recorded or sung chant. Runtime output has not been heard by this reviewer.

### Historical visual defects to prevent in the rebuild

1. **Home proof uses the wrong shape:** extended Home renders are 822×3200 images; they conceal the constrained first viewport problem. Hero consumes substantial initial space and the same-elevation cards have no clear pacing. Require realistic phone viewport captures before judging hierarchy.
2. **Competing navigation/control layers:** Rosary's six-item dock plus a second wide transport capsule and side tools occupy a large bottom region. Reader content looks calm, but controls dominate. Test quiet/focus mode with clear exit and stable labeled transport. Never hide navigation unexpectedly.
3. **Rosary information is redundant:** progress strokes, “Step 11 of 85,” “Decade 1 of 5,” full bead rail and “2 of 10” all compete with prayer. Give beginners a meaningful phase and optional detail. A small chevron beside the mystery title does not explain what will open.
4. **Round-4 art is attractive but can mislead:** Home labels Pope St Leo while displaying Mary. Correctness is essential to perceived craft; generic devotional art must be visibly generic.
5. **Mode is not identity:** historical light/dark screenshots are essentially identical layouts with inverted neutral surfaces. A new Sanctuary must establish its editorial composition rather than decorating the same glass stack.
6. **Eyeballing targets is insufficient:** accessible hit areas cannot be established from scaled PNGs. Need actual semantic/hit bounds for rail beads, segmented children, dock items and small tool glyphs.

### Source-specific concerns beyond the audit's main list

- Parish source describes Google Maps search as “Find Mass times,” but the implementation only launches generic query results (`ParishFinderScreen` around 105–111). Add a brief instruction to confirm current Mass/Confession times with the parish; search hits are not verified schedules. Manual search is present and should be preserved.
- Journal composer keeps drafts only inside the composed screen; this is covered broadly by tab-state audit E03 but deserves a concrete unsaved-journal journey. Create draft → dismiss/reopen → switch tab → return → rotate/relaunch. Do not imply draft durability without testing it.
- Each `PrayerDetailView` places text/audio/share/settings actions **after the complete prayer**, below a 300dp image. On long prayers, users must scroll through everything to find Listen or enlarge text. Put an accessible reader/audio action near the title or stable toolbar; inspect at 200% text.
- SpiritualLife's page transition explicitly uses 300ms slides/fades rather than design motion tokens. Audit reduced motion at nested page level, not only root transitions/art.
- Root `MainApp` deep-link arguments are several separate `remember` fields; saved destination names alone do not restore contextual route arguments after recreation. Validate restoration from Questions→CCC and Home→specific night office as well as ordinary tabs.
- Historical review documentation still states five tabs/three identities and 72 captures, while inspected MainApp has six tabs/18 destinations. Reconcile exact final inventory and evidence counts. Do not ship contradictory release evidence.

## Required next evidence

Fresh final-source captures: Today, prayer in progress, Bible reader, Pray/Explore landing, appearance/font sheet, one long detail and one failure/empty state in Lumen light/dark and Sanctuary light/dark. Prioritize Today/prayer/reader at 320×760dp normal and 200% text. Include EN and longest ES/PT labels; lower-scroll last action and keyboard composer state. Send actual file paths; reviewer will open the images independently and report concrete failures without an invented overall score.
