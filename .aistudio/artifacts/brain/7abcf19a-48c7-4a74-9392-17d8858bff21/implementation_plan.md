# Sancta Fides: Precision Fixes & Exhaustive Content Engine

Comprehensive plan to resolve all reported content and navigation discrepancies: authentic verse-by-verse fidelity for all 73 Catholic books, real-time Saint of the Day matching the current calendar date, direct parish search button on the primary GUI with cascading Country-Region-City dropdowns, Trivia navigation route fix, complete sequential Compendium (1–598) without gaps, and full 4-pillar indexing for the Universal Catechism.

---

## User Review & Critical Decisions

> [!IMPORTANT]
> The following decisions were clarified and prioritized in consultation with the user:

- **Confirmed Decision 1 (Scripture Engine)**: Motor bíblico canónico íntegro con versículos únicos por libro y capítulo. Replace all template/boilerplate verse fallbacks in `CatholicBibleContentEngine` with authentic, chapter-specific narrative passages across all 73 books and chapters so each selected chapter displays its own distinct, authentic verses without generic repetition or chapter mismatch.
- **Confirmed Decision 2 (Parish Directory Scope & UI)**: Directorio global internacional con búsqueda interactiva en Google Maps y selector de países. Add a prominent "Buscar Iglesias Cerca de Mí" quick-action shortcut on `HomeScreen`, and upgrade the directory in `DoctrineAndHistoryScreen` to use cascading dropdown menus (`ExposedDropdownMenuBox`): Country -> Region/State -> City, covering major Catholic nations (España, México, Colombia, Argentina, Brasil, Estados Unidos, Italia, Perú, Venezuela, Chile, etc.) and their respective dioceses/cities.
- **Fix 3 (Saint of the Day Calendar Alignment)**: Replace the static Oct 1 feast with a dynamic real-time date resolver using `Calendar.getInstance()` so the Saint of the Day always reflects the current day (e.g., October 27th / 28th Saints).
- **Fix 4 (Trivia Route Target)**: Ensure the "Quiz Católico / Trivia" button on `HomeScreen` opens `SpiritualLifeScreen` with the Trivia tab active (`selectedTopTab = 1`), rather than defaulting to the spiritual journal.
- **Fix 5 (Compendium & Universal Catechism Complete Scope)**:
  - Compendium: Ensure continuous sequential question coverage from 1 to 598 without skipping numbers (fixing the jump from 3 to 6 to 14).
  - Universal Catechism (CCC 1–2865): Populate all canonical sections and chapters across all 4 Parts (Creed, Sacraments, Moral Life/Commandments, Christian Prayer) in the navigation tree, eliminating sparse single-article placeholders.

---

## 1. System Architecture & Component Mapping

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                             SANCTA FIDES ENGINE                             │
├─────────────────────────────────────────────────────────────────────────────┤
│ 1. CANONICAL BIBLE ENGINE (CatholicBibleContentEngine.kt)                    │
│    ├── Book/Chapter Resolver -> Specific Verses (1..N) per Book & Chapter   │
│    ├── Authentic Patristic & Magisterial Notes per Chapter                  │
│    └── Zero Boilerplate: Distinct Scripture narratives across 73 Books     │
├─────────────────────────────────────────────────────────────────────────────┤
│ 2. CALENDAR & SAINT RESOLVER (DevotionsAndHistoryData.kt)                   │
│    └── Dynamic getSaintForDate(month, day) -> Today's Feast Day & Patron    │
├─────────────────────────────────────────────────────────────────────────────┤
│ 3. PRIMARY HOME GUI (HomeScreen.kt)                                         │
│    ├── Direct Action: "Buscar Iglesias Cerca de Mí" (Google Maps Intent)    │
│    └── Direct Action: "Quiz Católico" -> SpiritualLifeScreen(initialTab = 1)│
├─────────────────────────────────────────────────────────────────────────────┤
│ 4. PARISH DIRECTORY WITH CASCADING DROPDOWNS (DoctrineAndHistoryScreen.kt)  │
│    ├── Dropdown 1: País (España, México, Colombia, Argentina, Brasil, etc.) │
│    ├── Dropdown 2: Región / Estado (Madrid, CDMX, Bogotá, SP, NY, Roma...)  │
│    └── Dropdown 3: Ciudad / Diócesis -> Parishes with Mass & Adoration Info │
├─────────────────────────────────────────────────────────────────────────────┤
│ 5. COMPLETE CATECHISM & COMPENDIUM (CatechismData.kt & CompendiumData.kt)   │
│    ├── Compendium: Sequential 1..598 Questions without number skips         │
│    └── CCC Universal: 4 Pillars x All Sections x All Chapters               │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 2. Detailed Implementation Streams

### Stream 1: Bible Content Engine & Chapter Fidelity
- **Problem**: Opening non-master chapters produced repetitive generic template verses ("Dijo el Señor a su pueblo en el libro de...").
- **Solution**:
  - Overhaul `CatholicBibleContentEngine.kt` to build an extensive narrative and thematic verse repository keyed to `(bookId, chapterNumber)`.
  - Provide distinct, substantive verses for Old Testament narratives, Historical battles/kings, Wisdom poetry, Prophetic oracles, Gospel miracles/parables, Pauline theological discourses, and Johannine visions.
  - Verify that navigating to Genesis 1, Genesis 2, Genesis 3, Exodus 20, Matthew 5, Luke 1, John 1, Romans 8, etc. displays distinct, authentic biblical verses with proper verse numbering and commentary.

### Stream 2: Dynamic Saint of the Day
- **Problem**: `DevotionsAndHistoryData.saintOfTheDay` was statically fixed to Santa Teresita del Niño Jesús (October 1).
- **Solution**:
  - Implement `getSaintForDate(calendar: Calendar)` in `DevotionsAndHistoryData.kt` and `CatholicDataRepository.kt`.
  - Provide calendar feast mappings for the year, including late October feasts (October 27th San Frumencio / Beatos Mártires, October 28th San Judas Tadeo y San Simón Apóstoles, October 29th San Narciso, October 31st Víspera de Todos los Santos, November 1st Todos los Santos, etc.).
  - Wire `HomeScreen` to display today's actual liturgical saint with their feast date and biography.

### Stream 3: Primary GUI Parish Shortcut & Cascading Dropdowns
- **Problem**:
  - No direct quick-launch for nearby churches on the primary home screen.
  - Parish filter in `DoctrineAndHistoryScreen` only had horizontal chips with very few countries and regions.
- **Solution**:
  - In `HomeScreen.kt`, add a prominent primary button/banner: "Buscar Iglesias Cerca de Mí" with Google Maps intent (`geo:0,0?q=parroquia+catolica`) right in the featured routines and gateway cards.
  - In `DoctrineAndSpiritualData.kt`, expand the parish database to include key archdioceses and parishes across major Catholic countries (España, México, Colombia, Argentina, Brasil, Estados Unidos, Italia, Perú, Venezuela, Chile).
  - In `DoctrineAndHistoryScreen.kt`, implement `ExposedDropdownMenuBox` for:
    1. Select Country (País)
    2. Select Region / State (Región / Estado)
    3. Select City / Diocese (Ciudad / Diócesis)
    4. Instant Google Maps location launcher with coordinate navigation.

### Stream 4: Quiz Católico Navigation Target
- **Problem**: Clicking "Trivia Católica / Quiz Católico" opened `SpiritualLifeScreen` on Tab 0 (Diario Espiritual y Examen) instead of the quiz.
- **Solution**:
  - Add `initialTab: Int = 0` to `SpiritualLifeScreen`.
  - In `MainApp.kt`, support state-driven target tab for `AppDestination.SPIRITUAL_LIFE`, and when navigated from "Trivia Católica", set `initialTab = 1` so the user directly sees the Catholic Trivia interactive game.

### Stream 5: Complete Sequential Compendium (1–598) & Full 4-Pillar CCC
- **Problem**:
  - Compendium questions skipped numbers (1, 2, 3, 6, 14...).
  - Universal Catechism navigation tree only contained 2 sections with 1-2 articles.
- **Solution**:
  - In `CatechismCompendiumData.kt`, generate the complete sequence of 598 questions and answers across all 4 parts without gaps, ensuring every question number from 1 to 598 is present and searchable.
  - In `CatechismData.kt`, flesh out the complete table of contents for the Universal Catechism across all 4 Pillars:
    - Part 1: Sections 1 & 2 (Articles 1–12 of the Creed).
    - Part 2: Sections 1 & 2 (The 7 Sacraments & Liturgical Economy).
    - Part 3: Sections 1 & 2 (The 10 Commandments & Moral Virtues).
    - Part 4: Sections 1 & 2 (Christian Prayer & The 7 Petitions of the Our Father).
  - Ensure every chapter in every section is populated with accessible articles.

---

## 3. Verification & Validation Plan
1. **Compilation Check**: Run `compile_applet` to verify clean compilation with all new data models and composables.
2. **Automated Unit Tests**: Execute `gradle :app:testDebugUnitTest` to test data consistency, question sequence coverage, and navigation handlers.
3. **Functional CUJ Verification**:
   - Verify Bible reading across different books and chapters to ensure unique verses.
   - Verify Saint of the Day displays the current calendar day's feast.
   - Test "Buscar Iglesias Cerca de Mí" on HomeScreen and verify cascading dropdowns in Doctrine.
   - Verify clicking "Trivia Católica" opens the quiz directly.
   - Test Compendium question sequence (1, 2, 3, 4, 5, 6...) with no missing numbers.
