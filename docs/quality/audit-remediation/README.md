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
