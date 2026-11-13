# Devotional priorities — version 1.5

Requested on November 13, 2026. Implementation, Catholic pastoral review and independent visual review used Extra High reasoning for their focused assignments. This is a refinement of the existing Lumen/Sanctuary app, not a new navigation or component system.

## User-visible changes

- Home presents the liturgical season, current prayer, practices counter and today's liturgy in that order. Beginner help remains available lower in the page.
- Pray presents Rosary and Divine Mercy Chaplet as its two principal guided entries. Help me pray is secondary; the Lord's Prayer stays in the prayer library.
- Verse of the day opens its canonical book/chapter/verse in the same bundled edition shown on Home, with contextual Back. Online text substitution was removed from Home so a different edition cannot silently replace the displayed passage.
- My practices has a compact progress ring and direct access to temptation support, falls, victories and existing vice/virtue guidance.
- The journal category picker is one localized dropdown. Existing Spanish storage keys and saved entries are preserved.
- Private recording remains optional. The interface distinguishes temptation from sin and links the fall sheet to the existing explanation of culpability and Confession. Reading that explanation and returning preserves the pending sheet and draft.

## Reliability refinements found during review

- Explicit prayer entries load the latest shared durable session, choose the requested devotion and preserve both guides' positions. Obsolete saved route snapshots are removed rather than accumulated.
- A contextual Doctrine reader delegates Back to its caller when at its initial entry, while nested readers still pop locally.
- Home's scrolling viewport clears the floating dock; scroll-to actions must place the actual liturgy button above the dock before a real touch is accepted.
- The content review refined knowledge/consent wording, health-sensitive temperance/acedia advice, and wording about God's grace and Mary's intercession. The authoritative Catechism and Scripture corpus was not rewritten in this iteration.
- Reading-source fallback and unavailable-state labels do not imply that a failed feed proves no Internet, or that missing local weekday content means no readings are appointed.

## Review records

- [Catholic pastoral review](PASTORAL-REVIEW-2.md), including primary sources and eight prioritized recommendations based on content already present.
- [Independent visual review](VISUAL-REVIEW-2.md).
- Interaction tests cover section order, count values, prior library visits followed by Mass entry, exact verse text/edition, independent guided-prayer progress, journal restoration/save, private-record history, contextual Back and nested reader Back.
- Native Compose captures cover the requested areas in both identities and modes, plus narrow Spanish large-text variants. Physical device, keyboard and TalkBack behavior must be distinguished from rendered/automated evidence.

Final run totals and preview checksum are recorded in [VALIDATION-1.5.json](VALIDATION-1.5.json). The full regression passed 273 tests; the final adaptive-label follow-up passed eight Home behavior cases and eight compact frames in 12 test executions. None of these checks constitutes a guarantee of perfection or ecclesiastical approval.

## Rendered examples

[Home · Lumen light](previews-1.5/home-lumen-light.png) · [Pray · Sanctuary dark](previews-1.5/pray-sanctuary-dark.png) · [Practices · 320dp / 200% text](previews-1.5/practices-sanctuary-large-text.png). These are native Compose test captures, not physical-phone screenshots.
