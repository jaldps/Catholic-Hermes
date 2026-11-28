---
name: impeccable-native
description: Code-level UI quality audit and cross-device adaptation for native Android (Jetpack Compose), from the Impeccable project. Use to audit a screen or the app for accessibility, text scaling, theming, layout, orientation and anti-patterns, or to adapt a layout across phone, landscape and tablet.
license: Apache-2.0
---

# Impeccable · native audit and adapt (Android)

Excerpt of the reference files of [pbakaus/impeccable](https://github.com/pbakaus/impeccable) (Apache-2.0; see `LICENSE` and `NOTICE.md`). Only the reference documents are bundled here, unmodified; the Impeccable CLI, plugins and scripts are not. This `SKILL.md` is a short wrapper written for this repository.

## How to use

1. Read `reference/android.md` first: the Android platform rules the audit scores against.
2. **Audit:** follow `reference/audit.native.md` (code-level audit from Compose source). `reference/audit.md` is the shared report skeleton it mirrors.
3. **Adapt:** follow `reference/adapt.native.md` for phone, landscape and tablet; `reference/adapt.md` is the general version.

The references mention Impeccable commands (`impeccable detect`, `polish`…). They are not installed here: report the findings and fix them with this project's tokens and wrappers (`ui/design/AppKit.kt`, `AppComponents.kt`), following `AGENTS.md`.
