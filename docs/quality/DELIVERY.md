# Catholic Hermes Preview 1.5

Version `1.5-quality-preview`, versionCode `6`; package `com.aistudio.sanctafides.qvmrpx.qualitypreview`. The preview uses the same signing certificate as the previous quality preview, so it updates that installation and preserves its data. Historical CatholicGPT/Sancta Fides installations are separate. Production signing configuration/application identity and historical APK files remain unchanged.

SHA-256: `046af2180c5686533a309e0333ad93bfa9a1c250f686f53b02171b5271e2aa5f`.

## Changes

Home places the current prayer directly after the season, followed by the practices counter and Today's Liturgy opening Mass. Pray prioritizes Rosary and Divine Mercy; prayer help is secondary. The daily verse opens its exact passage and edition. Journal categories share one dropdown. Falls, victories and existing vice/virtue guidance are reachable from Home. Contextual Back preserves the pending recording sheet, and both guided prayer positions survive explicit switches.

Both Lumen/Sanctuary light/dark remain supported. Large-text journal actions reflow, and Home/temptation scroll viewports keep touch targets above the dock. See [the change record](DEVOTIONAL-PRIORITIES.md) and [pastoral review](PASTORAL-REVIEW-2.md).

## Validation

- Full regression: **273 tests across 51 suites, zero failures/errors/skips**.
- Focused behavior/compact-render batch: **16 tests, zero failures/errors/skips**, including 20 native Compose frames at 320dp/200% Spanish.
- Normal-size revalidation: **4 capture methods, zero failures/errors/skips**, generating 12 journal/menu/verse frames across both identities/modes. Earlier scope review inspected 40 normal frames.
- Corpus verification: all 2,865 Catechism paragraphs in three languages and all 246 devotion day sequences preserved.
- APK assembly and signature verification passed; same-preview-signer equality was checked against the previous installer.

Machine-readable totals are in [VALIDATION-1.5.json](VALIDATION-1.5.json). The initial 1.4 validation is retained separately in VALIDATION.json. Final context images and independent pixel-review conclusions are in [VISUAL-REVIEW-2.md](VISUAL-REVIEW-2.md). Historical captures/test runs are not presented as fresh tests.

## Limits

No physical-phone, keyboard, TalkBack, background-audio or full API24 device verification is claimed. The General Roman calendar is not a complete local diocesan calendar; bundled Scripture selections are explicitly not an approved liturgical lectionary. Source checking does not constitute ecclesiastical approval; inherited devotional provenance still needs curation. No guarantee of universal perfection is made.

Final narrow typography follow-up: eight Home behavior cases and eight Home/Pray frames passed in 12 test executions after adapting two large-text labels and the counter unit. These are separate from the full regression above; routes and stored data did not change.
