# Liturgical corrections — 12 November 2026

This records the scope of the corrected computation and the independent fixtures used. It is not an ecclesiastical approval or a complete national/diocesan calendar.

## Sources actually retrieved and read

- [USCCB 2024 calendar](https://www.usccb.org/resources/2024cal.pdf), introductory notes 6(b–e): transferred Epiphany gives Baptism Monday January 8; Annunciation April 8 after Holy Week/Easter octave; Ascension Thursday May 9 or Sunday May 12 by US ecclesiastical province; Immaculate Conception December 9 after the Second Sunday of Advent. The February 16 daily entry identifies Friday after Ash Wednesday. PDF SHA-256 `e07ff8238a63bff4cf59789a6ac0f9d906bf9a8b3746a9c54c5b2f79690f2660`.
- [USCCB 2023 calendar](https://www.usccb.org/resources/2023cal.pdf), notes 6(b–c): Baptism January 9 after transferred Epiphany; Saint Joseph March 20 after the Fourth Sunday of Lent. PDF SHA-256 `526549073ec05fb201f86e7f1329ff8764534e7c53aa580ecb633c3c17fde6ed`.
- [USCCB 2019 calendar](https://www.usccb.org/resources/2019cal.pdf), January 6/13 and June 23 entries: Epiphany on Sunday January 6 does **not** invoke the Baptism Monday exception; Baptism is January 13. Corpus Christi is Sunday June 23 under US transfer rules. PDF SHA-256 `a2a9e7794017f0d85197115396f3036dd8be5b7a65b9520633d0d4d4a7864211`.
- [General Norms for the Liturgical Year and Calendar](https://www.liturgyoffice.org.uk/Resources/GIRM/Documents/GNLY.pdf), published by the Catholic Bishops’ Conference of England and Wales: nos. 5–7, 37–38 and footnote 15, precedence table and no. 60. These distinguish the fixed January 6 calendar from the transferred Epiphany exception, seasonal Sunday precedence, and Sunday transfers. PDF SHA-256 `fb8496d841797d11f886bf50fc1b3b16f744695f2a11c030b2de6bb08afb35a6`.
- [USCCB Good Friday, March 29, 2024](https://bible.usccb.org/bible/readings/032924.cfm): Isaiah 52:13–53:12; Psalm 31:2,6,12–13,15–16,17,25; Hebrews 4:14–16;5:7–9; John 18:1–19:42. Retrieved HTML SHA-256 `effb29496ccc0c2f96f6c53381cfebe612127febf3c54b00ae20b6af8f52571c`.
- [CIC canons 1246–1253](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib4-cann1244-1253_en.html), the legal distinction referenced in the audit: national obligations, penitential discipline and conference adaptations must not be inferred from language.

The US calendars supply fixtures for explicitly transferred policies. They do not establish universal US-style transfers or obligations.

## Implemented contract

`CalendarRules()` retains General Roman dates: January 6 Epiphany, Thursday Ascension and Thursday Corpus Christi. Transfer booleans can be supplied explicitly and propagate through season, Baptism and Ordinary Time week calculation. They do not configure a country, diocese or local obligation law.

`isHolyDayOfObligation` asserts Sundays only; `isUniversalHolyDayCandidate` records candidates from canon 1246 without declaring their local obligation. `calendarNeedsLocalConfirmation` remains true. Fast/abstinence flags describe general Latin discipline, with age, health, conference substitution and local requirements requiring confirmation. In particular, general Friday abstinence includes the Friday after Ash Wednesday and ordinary Fridays; solemnities are excepted.

Christmas season begins on December 25 before the Advent branch is considered. Seasonal Sundays, Holy Week and the Easter octave protect their precedence. March solemnities and Immaculate Conception have the usual transfer rules; the Nativity of John the Baptist yields to the Sacred Heart on their concurrence, subject to patronal/local exceptions. Holy Week weekdays are violet; the Holy Thursday evening celebration is white.

The reference resolver preserves disjoint verse selections and cross-chapter ranges. It returns unavailable when any requested chapter or verse is absent instead of clipping the passage. It does not request an English substitute. Existing Bible-provider fallback behavior is a separate concern. The stale claim that the three bundled languages lack the Daniel/Esther additions was removed; Daniel 3 has 100 entries in each current asset.

The Good Friday Psalm uses Vulgate Psalm 30. Actual bundled text inspection established the title offset: English indices 1,5,11–12,14–16,24 and Spanish/Portuguese 2,6,12–13,15–17,25 yield the same selected lines. `LectionaryEntry.psalmFor(language)` must be used for both citation and passage. No global Psalm-number/verse-offset conversion is assumed. Christmas Day Psalm 97:1–6 likewise replaces the whole Psalm.

The Vatican feed succeeds only for an exact dated item path for the requested civil date. Missing dates, malformed feeds and invalid dates yield failure; publication timestamp alone is not used to relabel yesterday’s content.

## Remaining limits

- The calendar covers principal celebrations, not the full General Roman sanctoral calendar or all proper calendars, patronal ranks, historical changes, evening Mass/vigil precedence, or dispensation law.
- Transfer switches have no country/diocese chooser or persisted jurisdiction yet. A local parish/diocesan calendar remains necessary for local observance and obligations.
- Bundled entries are **Scripture selections**, not a complete approved Mass lectionary. Legacy entries still contain surrounding verses or whole psalms, and approved lectionaries can require verse parts, alternative forms, refrains, sequences and edition-specific numbering. Whole-verse source assets cannot supply exact half-verses safely. Every entry declares `isCompleteAppointedReadings = false`.
- The complete Good Friday narrative and other formerly dropped chapter segments are now supported, but this does not certify all historical entries. Neither a complete appointed weekday lectionary nor all vigil options is provided.
- Calendar/feed/edition tests are regression checks, not an imprimatur, live feed availability guarantee, or verification of every translated verse.

## Validation

Standalone Kotlin 2.2.21 compilation of the liturgy production files succeeded. JUnit 4.13.2 ran `LiturgicalCalendarTest`, `LiturgicalRegressionTest`, and `ScriptureRangeTest`: 26 tests passed. Android/Robolectric asset and feed parser tests are left to the shared Gradle validation. All reference syntaxes were statically checked against the bundled chapter/verse lengths; the Good Friday Psalm’s language-specific offsets are intentional.
