# Sequential audit remediation

Baseline: CatholicGPT 1.6, e30dc2a. Only confirmed defects and justified improvements are implemented; speculative findings stay distinct. Keep stable content IDs, personal data, package/signature and both visual identities.

1. Personal data: durable lesson notes, honest question progress, complete itinerary coverage, serialized recoverable writes.
2. Scripture and liturgy: complete human citation parsing, missing celebrations/cycle corrections, coherent date/season and explicit territory.
3. Persistence: atomic novena days, migration and unique habit/date completions.
4. Reliability: reminders/permissions, calendar target selection, narration states, HTTP/WebView policy and background I/O.
5. Editorial and usability: curate misleading metadata/promises, simplify routine hierarchy, improve search and source transparency.
6. Release: regression checks, visual review, upgrade-preserving APK and per-finding evidence.

Each stage records actual checks and remaining limits. No physical-device or ecclesiastical approval is inferred from unit tests or screenshots. Architectural and library expansion proposals are implemented only when their source, rights and behavior can be established, not by inventing content.

## Stage 1

Application-owned serial writes, transactional lesson notes/completion, automatic draft saving, per-lesson saved UI state and visible write status. Questions persist actual visited IDs and last-opened question separately. Legacy marks import only known endpoints; all 101 questions have exactly one itinerary.

Validation: StudyJourneyTest (3), LearningProgressReliabilityTest (4), DoctrineFormatTest (17): **24 passed**, zero failures/errors. Includes course back/reopen with unsaved notes, no accidental completion, out-of-order visits and non-destructive legacy import. Quiz alignment remains for the editorial stage.

## Stage 2a

Complete human citation ranges and common aliases, strict rejection of malformed/verse-part citations, full-range passage rendering; Easter octave rank/penitential exception, 219 fixed General Roman Calendar definitions plus movable Mother of Church, optional memorial collisions/seasonal precedence, rose/violet alternatives, corrected Gospel cycles and missing local selections. Vulgate Sirach numbering is preserved (elderly-parent passage 14–17). Holy Saturday no longer claims a daytime Vigil.

Validation: **41 passed**, zero failures/errors across five classes (counts in stage-2a/tests.json). The previous November 3 test expected a plain feria; it now verifies St Martin de Porres as optional while preserving Ordinary Time week 31 and the ferial reading key.

Calendar facts/localizations: Romcal MIT, pinned 0671305334e0a5ae7eec8f50a46c7a21e3e40347, https://github.com/romcal/romcal. The import script executes no external code; its license is bundled. Cross-check fixtures: USCCB 2025/2027 calendars and GNLY 24/CIC 1251. This is contemporary general calendar support, not historical calendars or complete diocesan propers. The devotional selections remain explicitly distinct from an approved complete lectionary; full ferial I/II and Vigil services remain expansion work.
