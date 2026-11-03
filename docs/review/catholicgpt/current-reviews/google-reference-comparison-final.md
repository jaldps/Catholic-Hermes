# Refreshed Material comparison: final addendum

**Google's Now in Android still has stronger overall visual finish and content hierarchy, but CatholicGPT has improved materially.** The title/date/art now reads as one composed header. The former separate decorative banner no longer interrupts the task, and the illustration remains a visible part of the identity. Bible and Prayers feel more coherent and less like stacks of unrelated modules.

This review uses frozen copies of six English default 822 × 1782 PNGs in [google-final-inputs](google-final-inputs/), captured from `app/build/screenshots/app` while other rendering continued. Their default logical viewport is 411 × 891 dp. Google references remain the official README montage and compact checked-in NiaApp baselines at commit `2982e63cb691ad49cd009aab43dc206fee6699c2`, preserved under `/workspace/.cloud-setup/references/whole-app/google-nowinandroid/`. This is an identified qualitative comparison across different tasks and viewports, not a controlled blind study or Apple/Vatican validation. Google references reviewed here show light mode only.

## Task access

The gains are visible without removing the artwork. Approximate positions derived from the default images:

| Element | Earlier render | Refreshed render |
| --- | --- | --- |
| Bible search top | 268 dp | 195 dp |
| First book row top | 425 dp | 351 dp |
| Prayers search top | 268 dp | 195 dp |
| First named prayer row top | 437 dp | 363 dp |
| Home celebration card top | 206 dp | 141 dp |

Bible now shows the start of Historical Books and Joshua in the opening viewport. Prayers shows more actual prayer choices. The roughly 65–75 dp recovered near the top is a meaningful improvement. Home's integrated date/art header is also clearer. Its current November 3 Mass card says **Readings unavailable offline**; the earlier image showed a November 2 passage and Reflection. The additional visible Home content therefore cannot all be credited to layout changes, and the current image does not validate a populated reading state.

## Remaining gap and clipping check

Google still makes artwork, headline, metadata, and action feel more tightly related to a specific content object. CatholicGPT's header is improved, but its spacious opening, two navigation levels, search/filter layers, and repeated pale rounded surfaces retain a more assembled feel. Home still gives several large containers similar emphasis. The dark palette remains heavy in its large grey-green panels, although the shown text and controls are readable. Navigation icon weights remain less balanced than the Google reference. These are craft judgments, not component-compliance or measured contrast failures.

**No actual text/control/header clipping is visible in these six default images.** Titles, artwork, tabs, search controls, and visible row labels fit without overlapping. Both Prayers search fields contain the complete rounded background, magnifier, and **Search prayers** placeholder; direct inspection and OCR confirm the whole label. Partially visible content at the bottom is normal scroll-viewport continuation behind the persistent navigation area, rather than evidence of an overflowing header. These images do not establish scroll reachability, large-text behavior, narrower widths, or populated/offline state parity.

The prior objection to a separate title-plus-art-banner stack is resolved for these screens. The previous light/dark title mismatch is also resolved: both modes now show **Today**, **Holy Bible**, and **Prayers** respectively. I would describe this round as a substantial compositional improvement, while continuing to rank the genuine Google reference higher overall.

## Frozen input SHA-256

| File | SHA-256 |
| --- | --- |
| `home_material_light.png` | `98d1ae2d6e2bd18872304037de24381f15fe8f3ef6e841757eedf54b94d594ee` |
| `home_material_dark.png` | `312b159bbc6cc77f20afed33a01764d246fed5144dbe1534724f62b19b1be7c5` |
| `bible_material_light.png` | `d9b915b7982b7313b891aacdaad0bd392b157185c934c036b2a7331e68bb182a` |
| `bible_material_dark.png` | `40c0621b580b88247bd5bf5fc536d6d6ed87355c43c031959cc518cf502dae81` |
| `prayers_material_light.png` | `f32432f86c1c22021fcb64f8a3c6b999003f610b96e570a56939cfcff6a26516` |
| `prayers_material_dark.png` | `c8d0cec893838daadee7690b69b0d7b8851b0cee68d6f265db1ef9298138e7ff` |

Full frozen-input metadata is in [inputs.json](google-final-inputs/inputs.json). No app edits, builds, or tests were performed for this addendum.
