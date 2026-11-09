# Doctrine & Study — progress note (paused)

Plan: `docs/redesign/plans/doctrine.md`. Status: **all code written, first full compile still unverified** (last attempt: `tools/device/gradle.sh :app:compileDebugKotlin`, started 13:02, still running at pause; the attempt before it failed only on a transient name clash — `foldText` — which is now renamed `doctrineFold`, plus errors in `CatechismScreen.kt`, another agent's file).

## Done

Data / tests
- `CatholicQuestionsData.kt`: stray leading ": " removed from all 60 clarifications (ES/EN/PT); PT theme "Salvação, purgatório, pecado e vida eterna" unified (3 questions) so every language has 11 themes.
- `VaticanNewsFetcher.kt`: `VaticanNewsItem.imageUrl: String? = null`, parsed from `media:content` / `media:thumbnail` (image medium only).
- Tests added: `DoctrineFormatTest.kt` (new), `CatholicQuestionsDataTest` (+2: no stray colon, 11 themes per language), `VaticanNewsTest` (+`readsEachStoryPhoto`). **Not run yet.**
- New render test `screenshots/DoctrineAreaScreenshotTest.kt` (26 states: roots, entries, news ok/loading/failed, churches, parish; output `app/build/screenshots/doctrine/<state>_<light|dark>_<lang>.png`; `-Pshots.lang`, `-Pshots.only`). **Not run yet.**

UI (all under `app/src/main/java/com/example/ui/screens/`, written, uncompiled-verified)
- `DoctrineAndHistoryScreen.kt` — shell: `AppEdgeToEdge`, saveable entry stack ("a|b"), `AppBackHandler` (one back), `AnimatedContent` push/pop (fade + 1/8 slide) with `SaveableStateHolder` so filters/scroll survive, one hoisted lazily-created `ReaderVoice` (TTS), per-destination `RootSpec` (art `heroes/lumen/*.jpg`, eyebrow, correct title), `AppInlineTitle`. New params: `initialChurchesSection: Int = 0`, `initialEntry: String? = null`, `newsLoader` (injectable); `showTabs` kept but ignored.
- `DoctrineParts.kt` — shared kit: ReaderVoice, async asset decode (off main thread, cached, sync in inspection mode), EmblemPlate (ivory plate), ReadingHeader/ReaderPage, ReadingParagraphs, PullQuote, GlassCard/NoteCard/FactsGroup, LeadRow, NumeralCircle, SectionHeader, RefChip, ReaderActionRow, SequenceFooter.
- `DoctrineFormat.kt` — pure rules (clarification clean, alias split, centuries/years localisation, epoch order, schism placement, news relative dates, themes, citation-only check, numbered blocks, office groups, order meta).
- `DoctrineMoral.kt` (commandments, precepts, vices, sin pages, Didache TOC + chapters), `DoctrineQuestions.kt` (search, question of the day, continue, itineraries with rings, quiz states, themes, answer page with reference chips + passage card + level pill, sources), `DoctrineHistory.kt` (timeline rail, epoch/schism pages incl. historicalSaints + catholicPerspective), `DoctrineOrders.kt` (segmented orders/offices, emblem plates, grouped offices, readers), `DevotionsExplorerScreen.kt` (segmented Our Lady/Christ, search, region chips incl. Asia & Africa, grouped list, new detail page with prayer Listen), `DoctrineNews.kt` (skeleton/retry/photo cards/relative dates, church directory now reachable, parish detail as a stack entry).

## Remaining
1. Get `:app:compileDebugKotlin` green (fix any errors in my `Doctrine*.kt` / `DevotionsExplorerScreen.kt`; errors in `CatechismScreen.kt` belong to another agent — wait/retry).
2. Run `DoctrineFormatTest`, `CatholicQuestionsDataTest`, `VaticanNewsTest`, `DidacheDataTest`, `ReligiousOrdersDataTest`, `FoundationRefParserTest`, `AppNavigationTest`, `DesignContrastTest`.
3. Render with `DoctrineAreaScreenshotTest` and `FullAppScreenshotTest -Pshots.only=doctrine,questions,study_doctrine,study_mary_jesus,study_history,study_orders,churches_news -Pshots.identity=apple` (light+dark, ES; EN/PT for questions, history, orders, mary_jesus; `-Pshots.height=1600`), look at them, fix visual defects (timeline node alignment, plate sizes, long titles, chip wraps, dark mode).
4. Phone check via `tools/device/adb.sh` (keep short; restore `night yes`).
5. Optional interaction test `DoctrineInteractionTest.kt` (deep link -> answer, chip expand/open, prev/next, quiz states, directory reachable).
6. Write `docs/redesign/reviews/doctrine-round1-builder.md` (renders, known gaps, inventory checklist).

## Requests / notes for the orchestrator
- **MainApp**: pass `initialChurchesSection = 1` for Home's "Directorio Iglesias" tile and Parishes' "Directorio de iglesias" (route to `CHURCHES_NEWS`); Home's tile currently opens DOCTRINE. `questionsInitialIndex` is already cleared from More.
- **Foundation**: `rememberAssetBitmap` (used by `AppDetailHeader`/`AppHeroImage`) decodes on the main thread; make it async.
- Old tags dropped with the retired inline back link: `doctrine_entry_back`, `back_to_churches_button` (replaced by the single glass back, tag `nav_back`). All other tags kept; new ones: `precept_row_n`, `vice_row_i`, `sin_row_i`, `didache_card`, `qa_daily`, `qa_search_input`, `qa_theme_n`, `orders_offices_tabs`, `epoch_row_<id>`, `schism_row_<id>`, `devotion_prayer_listen`, `news_retry`, `news_open_site`.
- New files in my area beyond the two owned ones: `DoctrineParts.kt`, `DoctrineFormat.kt`, `DoctrineMoral.kt`, `DoctrineQuestions.kt`, `DoctrineHistory.kt`, `DoctrineOrders.kt`, `DoctrineNews.kt` (split for size).
- Known deviations/risks: itinerary tap now resumes at first unread question (plan); epochs keep data order in the data file (UI orders them chronologically); Q31–40 and Q101 still outside itineraries (data decision).
