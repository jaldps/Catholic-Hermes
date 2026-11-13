# CatholicGPT quality program — November 12, 2026

Starting snapshot: `b227670` on `CatholicGPT`. User approved the audit roadmap, two selectable identities (Lumen/Sanctuary), functional repairs and independent visual review. Work stays in the existing checkout; historical APKs/signing/application identity are preserved.

## Delivery gates

1. Repair authoritative-content imports, calendar/lectionary rules, data preservation, downloads and reminder reliability.
2. Implement retained navigation and exact sessions, four primary destinations, a calm Today and optional beginner path.
3. Implement two coherent identities through the shared component contract, including accessible light/dark and reduced-effects variants.
4. Build and execute meaningful regression tests. Render every reachable destination and key reader/prayer/detail state; separately inspect normal phone, narrow/large-text, light/dark and both identities.
5. Independent critic identifies specific defects; implementation owner revises; repeat captures for changed states. Reviewers report remaining limitations honestly instead of promising subjective perfection.

## Parallel ownership

- content_repair (High): Catechism/novena/prayer source data and provenance checks.
- liturgy_repair (High): calendar/reference/lectionary and dated-feed correctness.
- reliability_repair (High): local storage, downloads/provider, backups and reminders.
- dual_design (High): design/theme/shared visuals and appearance controls.
- beginner_ux (High): MainApp, Today/Explore, Bible/Pray/Rosary and beginner navigation.
- quality_critic (Medium): independent visual/functional critique, no competing implementation edits.
- root: environment/toolchain, repository/Activity/build integration, secondary-screen fixes, checks and final delivery.

Medium effort is used for bounded critic inventory and later routine work; complex architecture/content work uses High. Extra High is reserved for a concrete unresolved problem. Builds use one shared Gradle lock; screenshot evidence must come from current Compose source. No agent may claim historical validation as new results.
