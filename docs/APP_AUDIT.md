# Catholic-Hermes (Sancta Fides) — App Audit

Repository: https://github.com/jaldps/Catholic-Hermes
Branch audited: `main` (commit `edeab98 feat: initialize Sancta Fides Android project`)
Audit date: 2026-10-25
Auditor task: `t_3d00a39a`
Scope: Android app only. No other repository was modified.

## 1. Stack and build configuration

- Platform: Android (single-module Gradle project, module `:app`).
- Language: Kotlin `2.2.10`, Jetpack Compose (BOM `2024.09.00`), Material3.
- Build: AGP `9.1.1`, Gradle wrapper, `compileSdk 36` (minorApiLevel 1), `minSdk 24`, `targetSdk 36`.
- Application id: `com.aistudio.sanctafides.qvmrpx`; namespace `com.example`; versionCode 1 / versionName 1.0.
- Root project name: `Sancta Fides` (`settings.gradle.kts`).
- Key dependencies in `app/build.gradle.kts`:
  - Navigation Compose `2.8.9`, lifecycle `2.8.7`, Room `2.7.0`, Retrofit `2.12.0` + Moshi `1.15.2`, OkHttp `4.10.0`, Coil `2.7.0`, coroutines `1.10.2`.
  - Firebase BOM `34.17.0` (Firebase AI, App Check Recaptcha/Debug); google-services plugin configured with `missingGoogleServicesStrategy = WARN`.
  - Secrets Gradle plugin `2.0.1` reading `.env` / `.env.example`.
- Commented-out (not available at runtime): Accompanist permissions, Play Services Location, CameraX, DataStore Preferences, Firestore, Firebase Auth/Credential Manager/Google ID.
- Manifest (`app/src/main/AndroidManifest.xml`): permissions only `INTERNET` and `VIBRATE`. No location, storage, or notification permissions. Single `MainActivity`.
- Theme entry: `Theme.MyApplication` style parent `android:Theme.DeviceDefault.NoActionBar`; Compose theme `SanctaFidesTheme`.
- AI Studio metadata: `metadata.json` (name "Sancta Fides", major capability `MAJOR_CAPABILITY_SERVER_SIDE_GEMINI_API`); `.env.example` has `GEMINI_API_KEY` commented out.
- Tests: `ExampleUnitTest`, `ExampleRobolectricTest` (Robolectric SDK 36). Tests assert counts/content but encode placeholder behavior (e.g. any non-empty chapter).

## 2. Architecture

- Single Activity: `app/src/main/java/com/example/MainActivity.kt` creates `CatholicDataRepository`, `LiturgicalTtsManager`, `SanctaFidesDatabase`, seeds default habits and liturgical days.
- UI entry: `app/src/main/java/com/example/ui/MainApp.kt` defines `AppDestination` enum (9 tabs) and bottom `ScrollableTabRow`; state is held in Compose `remember` and `MutableStateFlow` for language.
- Data layer: immutable singleton `object` repositories under `app/src/main/java/com/example/data/repository/` (`BibleData`, `CatechismData`, `PrayerAndNovenaData`, `CompleteNovenasData`, `DevotionsAndHistoryData`, `DoctrineAndSpiritualData`, `PilgrimageAndHabitsData`, `ScriptureStudyData`, `NightPrayerData`), aggregated by `CatholicDataRepository`.
- Models: `app/src/main/java/com/example/data/models/` (Bible, Catechism, Language, DoctrineAndHistory, Prayer, SaintAndDevotion, etc.). Localization via `LocalizedText(es,en,pt)`.
- Persistence: Room DB `SanctaFidesDatabase` v3 with `fallbackToDestructiveMigration()`, entities in `app/src/main/java/com/example/data/local/Entities.kt`, DAO in `SanctaFidesDao.kt`. Stores journal, novena progress, memorized verses, stats, habits, completions, sin logs, liturgical days, scripture study progress. No DataStore; language/theme/preferences are not persisted.
- Screens: `app/src/main/java/com/example/ui/screens/` (13 screens). Components: `app/src/main/java/com/example/ui/components/`.

## 3. Screen and feature inventory

Navigation destinations (`MainApp.kt` `AppDestination`):
1. `HOME` — `HomeScreen.kt`: liturgical calendar widget, habit tracker card, daily spiritual routine quick cards, daily mass card, verse of the day, saint of the day, quick gateways (parish finder, trivia, catechism, pilgrimages).
2. `HABITS_VIRTUES` — `HabitsAndVirtuesScreen.kt`.
3. `BIBLE` — `BibleScreen.kt`: subtabs Bible reader (73 books), Daily Missal, Versions comparator, Scripture Study.
4. `CATECHISM` — `CatechismScreen.kt`: 4 parts, sections/chapters, article cards, CCC number/text search, font controls.
5. `ROSARY` — `RosaryScreen.kt`: Rosary + Divine Mercy chaplet (coronilla).
6. `PRAYERS` — `PrayersAndNovenasScreen.kt`: Vatican prayers, novenas.
7. `PILGRIMAGE_HABITS` — `PilgrimageAndHabitsScreen.kt`.
8. `DOCTRINE` — `DoctrineAndHistoryScreen.kt`: doctrine, church history, schisms, commandments, precepts, sins/virtues, chants, via crucis, trivia, parish finder, catholic news.
9. `SPIRITUAL_LIFE` — `SpiritualLifeScreen.kt`: journal/quiz.

Additional screens reachable: `DevotionsExplorerScreen.kt` (Marian/Christ devotions), `NightPrayersScreen.kt` (Compline/Examen), `ScriptureStudyScreen.kt`.

Shared components: `LanguageSelectorDialog.kt`, `LiturgicalCalendarWidget.kt`, `LiturgicalHeader.kt`, `PrayerBeadCounter.kt`.

## 4. Data sources and content status

- All content is compiled into the APK as Kotlin `object` singletons; no remote API calls for Bible/Catechism/novenas/missal. Only declared external-ish sources are static strings.
- Bible metadata: 73 books with localized names/abbreviations/chapter counts (`BibleData.catholicCanonBooks`).
- Bible versions comparator: 4 entries (`BibleData.bibleVersions`), including a non-Catholic 66-book notice.
- Daily mass reading: one static `DailyMassReading` (`BibleData.todayMassReading`), not date-aware.
- Catechism: 4 parts with a few sections/chapters/articles (`CatechismData.catechismParts`).
- Novenas: `PrayerAndNovenaData.officialNovenas` = St. Jude, Our Lady Undoer of Knots, Sacred Heart, Divine Mercy. Only Sacred Heart and Divine Mercy have 9 distinct days; the other two repeat the same generic day 9 times.
- Rosary mysteries, Divine Mercy steps: static.
- Vatican prayers: static list.
- Marian/Christ devotions, church history, schisms: static lists.
- Doctrine: commandments, precepts, sins/virtues, chants, via crucis, trivia: static.
- Nearby parishes: 2 hardcoded entries (`DoctrineAndSpiritualData.nearbyParishes`).
- Catholic news: 2 hardcoded entries.
- Pilgrimages, habits, liturgical days, sin avoider: static.
- Scripture study: 3 tracks with lessons.
- Night prayers: Compline + Ignatian examen static.

## 5. Detailed implementation notes

### 5.1 Bible

Files/modules:
- Model: `app/src/main/java/com/example/data/models/BibleModels.kt` (`BibleBook`, `BibleChapter`, `BibleVerse`, `BibleVersionInfo`, `DailyMassReading`).
- Data: `app/src/main/java/com/example/data/repository/BibleData.kt` (`catholicCanonBooks`, `sampleVersesWithCommentaries`, `getVersesForBookAndChapter`, `bibleVersions`, `todayMassReading`).
- Repository facade: `CatholicDataRepository.catholicBooks`, `bibleVersions`, `dailyMassReading`, `getChapter`.
- UI: `app/src/main/java/com/example/ui/screens/BibleScreen.kt` (book list, search, testament/deuterocanonical filters, reader, chapter selector, font controls, commentary bottom sheet, daily missal, versions comparator, scripture study).
- Persistence hooks: `MemorizedVerseEntity` exists but reader does not appear to persist memorized verses.

Current behavior:
- Book directory and chapter navigation work.
- Verse text is real only for a few hardcoded chapters: John 1, Genesis 1, Psalm 23, Matthew 5, 1 Corinthians 13, Wisdom 3.
- For every other book/chapter, `getVersesForBookAndChapter` falls back to 12 synthetic verses with placeholder text ("Bless the Lord, all you works..."), so selecting most chapters shows generic content rather than scripture. This is the critical bug reported.
- Daily missal tab shows the single static `todayMassReading`, not today's date or official readings.
- Versions comparator is informational only; no actual text switching/downloading.
- "Scripture study" tab embeds `ScriptureStudyScreen`.

### 5.2 Catechism

Files/modules:
- Model: `app/src/main/java/com/example/data/models/CatechismModels.kt`.
- Data: `app/src/main/java/com/example/data/repository/CatechismData.kt` (`catechismParts`, `findArticleByNumber`).
- Repository facade: `CatholicDataRepository.catechismParts`, `findCatechismArticle`.
- UI: `app/src/main/java/com/example/ui/screens/CatechismScreen.kt`.

Current behavior:
- UI supports 4 parts, sections, chapters, article cards, CCC number/topic search, font controls.
- Content is only a handful of articles (e.g. CCC 27, 31, 199, 1324, 1374, 1857, 2559), not the full 1-2865. Ranges displayed are illustrative.
- No Compendium model/screen/data.
- No language-aware offline storage; all content in memory.

### 5.3 Novenas

Files/modules:
- Models: `app/src/main/java/com/example/data/models/PrayerModels.kt` (`Novena`, `NovenaDay`).
- Data: `app/src/main/java/com/example/data/repository/PrayerAndNovenaData.kt`, `CompleteNovenasData.kt`.
- UI: `app/src/main/java/com/example/ui/screens/PrayersAndNovenasScreen.kt`; progress entities `NovenaProgressEntity`.
- Divine Mercy chaplet/coronilla UI: `app/src/main/java/com/example/ui/screens/RosaryScreen.kt`.

Current behavior:
- 4 novenas listed, but only Sacred Heart and Divine Mercy have 9 distinct daily texts.
- St. Jude and Our Lady Undoer of Knots use `(1..9).map` with identical text each day (not day-specific).
- No coronilla dedicated section in Prayers screen, but Divine Mercy chaplet exists in Rosary screen tab. May need clearer entry/flow.
- Novena progress persistence exists in Room but UI wiring should be verified.

### 5.4 Daily missal

Files/modules:
- Model: `DailyMassReading` in `BibleModels.kt`.
- Data: `BibleData.todayMassReading`.
- UI: `DailyMassMissalView` in `BibleScreen.kt`; Home card uses `repository.dailyMassReading`.

Current behavior:
- Static single reading; not date-aware and not sourced from Vatican News.
- Home "Today's Mass Readings" shows gospel excerpt from the same static object.
- Liturgical calendar widget uses `repository.getCurrentLiturgicalDay()` which always returns `upcomingLiturgicalDays.first()` instead of today's date.

### 5.5 Parish search

Files/modules:
- Model: `NearbyParish` in `DoctrineAndHistoryModels.kt`.
- Data: `DoctrineAndSpiritualData.nearbyParishes` (2 entries).
- UI: `DoctrineAndHistoryScreen.kt` parish section; map intent via `geo:` URI.
- Build: Play Services Location and Accompanist permissions commented out; manifest lacks location permission.

Current behavior:
- Search filters only the 2 hardcoded parishes by name/diocese/address.
- No Google Maps/nearby location, no permission flow, no country/state/city selector.
- Map button opens a generic geo search intent.

### 5.6 Theming and customization

Files/modules:
- `app/src/main/java/com/example/ui/theme/Theme.kt`, `Color.kt`, `Type.kt`.
- `SanctaFidesTheme(darkTheme = isSystemInDarkTheme())` only.
- `MyApplicationTheme` delegates to `SanctaFidesTheme`.

Current behavior:
- Only light/dark system theme; no user-selectable themes, color choices, layout preferences, or persistence.
- DataStore dependency is commented out, so preferences cannot be persisted without adding dependency.
- `Theme.MyApplication` XML style and `colors.xml` are default template values, inconsistent with Compose palette.

## 6. Bug list (priority)

Critical:
1. Bible content is placeholder for most chapters (`BibleData.getVersesForBookAndChapter` fallback generates 12 identical synthetic verses). Affects all books except 6 hardcoded chapters. Files: `BibleData.kt`.
2. Catechism content is incomplete (only ~8 articles). Files: `CatechismData.kt`.
3. Daily missal is static and not official/date-aware. Files: `BibleData.todayMassReading`, `BibleScreen.kt`, `HomeScreen.kt`.
4. Liturgical day on Home is not date-aware (`getCurrentLiturgicalDay` returns first seeded day). Files: `CatholicDataRepository.kt`, `PilgrimageAndHabitsData.kt`.

High:
5. Novenas incomplete: St. Jude and Undoer of Knots repeat identical day text; only 2 complete 9-day novenas. Files: `PrayerAndNovenaData.kt`, `CompleteNovenasData.kt`.
6. No Compendium of the Catechism. Files: models/data/screens.
7. Parish search is hardcoded 2 entries, no location/manual selector. Files: `DoctrineAndSpiritualData.kt`, `DoctrineAndHistoryScreen.kt`, manifest/build.
8. Theming has no customization/persistence. Files: `Theme.kt`, `Color.kt`, `Type.kt`, build (DataStore commented).
9. Language selection is not persisted (`MutableStateFlow` resets on process death). Files: `CatholicDataRepository.kt`.
10. Tests encode placeholder behavior and need updating to assert real completeness. Files: `ExampleRobolectricTest.kt`.

Medium:
11. `fallbackToDestructiveMigration()` will wipe user data on schema changes; need proper migrations for production.
12. `google-services.json` absent; Firebase features run in passthrough/warn mode.
13. `GEMINI_API_KEY` not packaged by default (commented in `.env.example`).
14. Inconsistent XML theme/colors vs Compose palette.
15. No offline download mechanism for alternate Bible texts despite `BibleVersionInfo` metadata.

## 7. Constraints

- Min SDK 24: avoid APIs requiring higher SDK unless guarded; location permissions require manifest additions.
- Single-module app; all content compiled in increases APK size if full Bible/Catechism bundled.
- Room DB v3 with destructive migration; adding offline Bible storage requires schema migration or version bump.
- No DataStore dependency currently; persistence of preferences requires adding dependency or Room table.
- Network access only `INTERNET`; no location permission. Google Maps/location requires manifest + dependency changes.
- Static singleton data objects are simple but memory-heavy; full texts may require asset files or database seeding.
- Localization is fixed to ES/EN/PT via `LocalizedText`; new languages require model changes.
- Build environment expects Android SDK/Gradle; AI Studio signing configs must not be modified.

## 8. Recommended implementation order

1. Audit baseline (this file) — done.
2. Implement complete Bible text pipeline (data source + storage + navigation/search + offline downloads) — task `t_85357165`.
3. Implement complete Catechism + Compendium (ES/EN/PT) with navigation/search/language switching — task `t_82282bd8`.
4. Complete novenas (day-specific texts), add coronilla flow, replace static missal with official Vatican News readings — task `t_d5c1a3f5`.
5. Fix parish search (Google Maps/nearby with permission + manual country/state/city fallback) and add theme/customization menu with persistence — task `t_7b51050d`.
6. Build and push signed APK to repository (final integration) — root task `t_caae248f`.

Notes for downstream workers:
- Keep all work inside `https://github.com/jaldps/Catholic-Hermes`.
- Preserve existing navigation/test tags where possible.
- Update or replace tests in `ExampleRobolectricTest.kt` to assert real completeness, not non-empty placeholders.
- Prefer asset-backed or Room-backed storage for large texts instead of giant Kotlin objects.
- Do not modify other repositories.

## 9. Exact files/modules reference

Core:
- `settings.gradle.kts`, `build.gradle.kts`, `gradle/libs.versions.toml`, `app/build.gradle.kts`, `app/src/main/AndroidManifest.xml`, `metadata.json`.

Entry/UI:
- `app/src/main/java/com/example/MainActivity.kt`
- `app/src/main/java/com/example/ui/MainApp.kt`
- `app/src/main/java/com/example/ui/screens/*.kt`
- `app/src/main/java/com/example/ui/components/*.kt`
- `app/src/main/java/com/example/ui/theme/*.kt`

Data:
- `app/src/main/java/com/example/data/repository/*.kt`
- `app/src/main/java/com/example/data/models/*.kt`
- `app/src/main/java/com/example/data/local/*.kt`
- `app/src/main/java/com/example/data/audio/LiturgicalTtsManager.kt`

Tests:
- `app/src/test/java/com/example/ExampleUnitTest.kt`
- `app/src/test/java/com/example/ExampleRobolectricTest.kt`
- `app/src/androidTest/java/com/example/ExampleInstrumentedTest.kt`
