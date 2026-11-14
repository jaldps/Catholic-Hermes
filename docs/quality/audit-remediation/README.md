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

## Stage 2b

One date/language/transfer-policy key drives daily content and saint refresh. Requests and mapping use a captured date; old results cannot publish after rollover. Cancellable HTTP reads have bounded response bodies. Mass view keys its online state by date/language and avoids a mismatched general-calendar feed on transferred celebrations. Calendar transfers persist from Explore → Settings and are explicitly distinct from complete local propers/obligation law. Home/Habits/reminder deep links select Regina Caeli in Eastertide.

Validation: **20 passed**, zero failures/errors. Includes simulated rollover, dated saint requests, policy-specific reading cache, body-size limit, seasonal prayer boundaries and the actual settings journey with persistence across closing/reopening. A missing localToday reference was caught during compilation and corrected before these final checks. No physical alarm/audio verification is implied.

## Stage 3a

Moved 246 novenas / 3,068 days out of executable Kotlin into immutable assets. Every exported field was compared to original runtime objects before the move, then compared again after the move. Content IDs, all three languages, days, tags and image URLs match. The 328 KB index exposes day identifiers without reading bodies; selected readers preload a stable snapshot on IO. Full-text search runs on IO with cancellation/debounce and the body cache is evictable (8 MiB estimated budget, allowing one oversized body). No cold-start/frame/heap improvement is claimed without physical measurement.

Validation: **10 passed**, zero failures/errors, including actual catalog browsing with zero bodies loaded, opening one selected prayer, progress compatibility and whole-corpus runtime equivalence. The initial compile caught a missing AppEmptyState import; it was fixed before this run. The old novena Kotlin classes are removed only after this equivalence proof; hashes and reproducible legacy exporter are retained.

## Stage 3b

Room schema 5 unifies novena days with an atomic import marker. Legacy masks/extra days import once, preserving intentions and known dates; unknown completion times stay unknown. Reset cannot resurrect old preference days and preserves the intention. One SQL snapshot includes the marker and day rows. Habit/date uniqueness consolidates legacy duplicates to their earliest record; new writes are idempotent. Application-owned writes expose errors and retry the same command.

Validation: **30 passed, zero failures/errors** across eight classes. Covers real schema 4→5 and 3→5 upgrades, non-destructive failure for unknown versions, long novenas, reset/import, 40 concurrent habit requests, retry, notes, whole-corpus body hashes and actual navigation. The initial run exposed date-dependent test scrolling to an offscreen lazy Bible heading and the Home count showing zero before Room loaded. The test now scrolls the lazy parent while retaining the edition assertion; Home displays loading rather than a false zero. A Home online preview retained old request state too; it now uses the same date/language/calendar reset as Mass. These fixes preceded the final 30-case green run.

## Stage 4a

Reminder permission grants and foreground resume reconcile schedules through one application-owned scope and mutex. A failed preference read is explicitly unavailable, never a request to enable default reminders or cancel existing alarms. Retry rereads persisted choices. Test notifications check global/channel availability. UI no longer promises delivery before permission, and writes survive navigation with error/retry feedback. Seasonal Marian notification text is resolved at runtime; exported daily recurrences use a season-independent title.

Validation: **12 passed**, zero failures/errors across reminder regression/scheduling/intent/catalog classes. Includes read failure/recovery without opt-in, preservation of existing alarms on failed reconciliation and blocked-channel test behavior. Initial compilation caught an invalid extension import; fixed before this green run. Exact-alarm delivery/Doze still require Android device testing.

## Stage 4b

Calendar export requires an explicitly selected writable calendar and confirmation, including contributor access. A stable provider-side reminder marker prevents duplicate events when the local event-link write was interrupted. An event in another calendar is preserved, with a warning before creating a new selected-calendar copy. Account discovery and writes run on IO; failures are recoverable. Local app notifications and calendar-managed alerts are clearly distinguished.

Validation: **9 passed**, zero failures/errors, including a real Compose selection/confirmation journey and a SQLite-backed CalendarContract fake that executes actual projections/selections. The initial UI test stalled on the loading view; the screen now reads its explicit load state at the owning composition, and the native test drains paused Android/snapshot/frame scheduling while awaiting background work. No real account calendar was read or changed during testing.

## Stage 4c

Narration uses the resolved app language even before an explicit language choice. Engine readiness requires support for that language; initialization/language/playback failures expose an on-demand recovery sheet with Android voice settings and retry. The Bible queue and Rosary do not enter playback mode when voice is unavailable. Voice installation/resume rechecks availability without automatically starting prayer. Existing chunking, stop/generation tracking and Rosary positions remain intact.

The internal browser allows HTTPS navigation, disables local file/content access and mixed content, and limits page JavaScript/storage to the supported Google Maps origins. User-initiated top-level mail/telephone/map actions may open their Android handlers; executable/custom schemes are blocked. Main-frame HTTP errors use the existing recovery view.

Validation: **29 passed**, zero failures/errors across voice availability, browser policy and existing Rosary narration/interaction tests. Engine language failure/recovery and a rejected Bible playback request are exercised. These are simulated Android engine/Compose checks, not real audio or live WebView website compatibility measurements.
