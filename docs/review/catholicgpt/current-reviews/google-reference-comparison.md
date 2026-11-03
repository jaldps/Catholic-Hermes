# Current Material screens compared with genuine Google references

**Verdict: Now in Android has stronger visual craft, hierarchy, and overall cohesion in the supplied references.** CatholicGPT's Material screens are readable, recognizably Material, and useful, but they do not yet match the reference's degree of composition. Consistent colors, rounded containers, and standard controls give the current app a foundation; they do not make every screen feel deliberately edited. Bible and Prayers most clearly expose that gap. Rosary has the strongest distinctive product idea.

This is one reviewer's qualitative comparison of identified images across different tasks and viewports. It is **not a controlled blind study**, a usability experiment, an official Google assessment, or evidence about Apple/Vatican quality. There were no app edits or builds for this review.

## Inputs and limits

CatholicGPT inputs: the English default `home_material_{light,dark}.png`, `bible_material_{light,dark}.png`, `prayers_material_{light,dark}.png`, and `rosary_material_{light,dark}.png` in `/workspace/Catholic-Hermes/app/build/screenshots/app`. Each is 822 × 1782 pixels. The screenshot harness specifies a default logical viewport of 411 × 891 dp, default font and scale, and inspection mode. These are app renders, not physical-device captures.

Google inputs: unmodified official [`android/nowinandroid`](https://github.com/android/nowinandroid/tree/2982e63cb691ad49cd009aab43dc206fee6699c2) files at commit `2982e63cb691ad49cd009aab43dc206fee6699c2`, preserved under `/workspace/.cloud-setup/references/whole-app/google-nowinandroid/`:

- `screenshots.png`: the official README montage, with two phone screens and an expanded Interests/detail screen inside device frames.
- `baseline-compact-expanded.png`: the checked-in NiaApp root test render, 600 × 1500 pixels, requested at 400 × 1000 dp.
- `baseline-compact-medium.png`: the same app at 600 × 750 pixels, requested at 400 × 500 dp.

The Google baselines use inspection mode and deterministic test data; their geometric illustration placeholders are not finished editorial artwork. The montage contains actual editorial imagery. The Google files reviewed here show light mode only. Comments on CatholicGPT dark mode therefore concern its own consistency and rendering, not a direct Google dark-mode comparison. Different content, capture density, viewport height, and device frames prevent a pixel-for-pixel ranking. The shorter Google baseline visibly truncates the first article above bottom navigation; it is not a perfect density exemplar.

## Where Google is stronger

| Dimension | Concrete comparison | Judgment |
| --- | --- | --- |
| Hierarchy | Google's compact app bar gives orientation and utility actions a small, stable area. Each article then has one clear image/headline/metadata/body sequence. CatholicGPT Bible and Prayers stack a large page title, a separate artwork panel, tabs, search, filters or categories, then destination rows. The parts are legible, but they compete for the opening viewport. | Google has the clearer relationship between navigation, content, and action. |
| Use of illustration | Google gives illustrations substantial space, but attaches them to a specific story or topic. The image and headline belong to one content object. CatholicGPT's Bible and candle banners primarily repeat the identity already given by the page title and selected destination. | Google integrates artwork more convincingly. The lesson is to give CatholicGPT's meaningful art a stronger content role, not remove it. |
| Surface composition | Google's white story surfaces, tinted canvas, selected accents, and editorial images establish distinct jobs. CatholicGPT repeats similarly pale green containers for banners, search, rows, navigation, and information; Home also adds a lavender celebration block and a heavier grey-green Mass block. | CatholicGPT is coordinated, but the hierarchy of its surface colors is less precise. |
| Typography | Google's story headline, small metadata, supporting text, and compact tags form an obvious editorial ladder. CatholicGPT's large page title, large illustration, green category heading, large row titles, pills, and filled panels each assert importance. | CatholicGPT needs a more intentional division between page identification, live context, and the next task. |
| Small-scale icon craft | Google's reference has relatively economical navigation silhouettes and repeatable topic-icon line work. CatholicGPT places a solid house, a visually heavy open book, a detailed rosary chain, a candle, and a thin grid together. | CatholicGPT's navigation is understandable but has less balanced visual weight. |

Google's light palette is more vivid; this alone does not make it better. Its stronger result comes from connecting those colors and images to content and state. Copying its pink, reducing all ornament, or replacing the current screens with generic lists would miss the useful part of the comparison.

## Screen-specific findings

### Home

The celebration name and Mass reading are clear, the scripture reference is visible, and Read Mass is easy to identify. This is a competent opening screen. The date/art panel, lavender celebration card, grey-green Mass card, Reflection capsule, and grouped daily-prayer rows nevertheless read as several component styles stacked together. The isolated date has a lot of horizontal and vertical space while the celebration immediately below becomes a second hero. The Mass icon appears in both its section label and button; the repeated symbol adds weight without adding information.

The concrete gap is composition: date, liturgical context, celebration, and artwork should read as one deliberate opening, with the Mass passage/action as the clear next block. Retain the religious illustration, but make its relationship to the day's celebration stronger than simply sharing a row with the date. Google is stronger at making the top image and the following text belong to one object.

### Bible

Search, book filter, versions, section labels, chapter counts, and chevrons are legible. The grouped book rows work as a directory. The craft problem is above them: the book picture occupies an isolated banner, followed by a second Bible label in the tab row, then a full-width search surface, a filter row, and a section heading. The first named book starts around 425 dp down the 891 dp viewport. The search starts around 268 dp. Those approximate positions are visible in this render; they are not universal layout measurements.

This is too much orientation before a lookup task. The reference does not prove that an app must avoid large pictures—it uses large pictures itself. It demonstrates that the picture should carry the content. Preserve the book artwork while composing it with useful reading context or a smaller task header; bring book lookup closer to the opening. The broad, nearly identical row backgrounds also make the whole lower screen feel like one muted block rather than a carefully paced catalog.

### Prayers

The candle artwork is coherent with the book art, the tab labels fit, and the named prayers remain readable. The opening repeats the same structure as Bible almost mechanically: title, large picture, tabs, search, a separate All intentions capsule, category heading, then prayer rows. The first named prayer begins around 440 dp; All intentions appears earlier, but adds another separate container between search and the actual prayer catalog.

The repeated template gives consistency at the expense of purpose. A prayer library should make searching, browsing by intention, and selecting a familiar prayer feel like one browsing system. Here they look like three stacked modules. The everyday and other-prayers sections are clear, but the large radius and pale fill receive more attention than the organization of the prayer choices. Google is stronger at nesting related information and keeping secondary controls subordinate.

### Rosary

This is the strongest screen in distinctiveness. The bead map communicates a real devotion and progress; the text, Next button, and independent devotion selector give the screen a clear job. Keep that meaningful illustration. It is more purposeful than the directory banners.

Its current balance still favors the diagram over praying. The large quiet bead circle fills the upper part of a very large grey-green card; the current prayer title begins around the middle of the viewport, and Next appears around 590 dp. At step 1, nearly all beads and outlines are subdued against a subdued surface, so the diagram has a large footprint but a weak current-state focal point. The three centered status lines inside the ring do not compensate for that visual silence. Give the active bead/current prayer a stronger focus and reconsider the map's height or placement while preserving the bead sequence and step data. In the opening state shown, the prayer and Next remain visible; this review does not establish how later, longer prayers behave.

## Cohesion and dark-mode observations

The book and candle illustrations share a consistent visual vocabulary and stay inside their frames. Their clipped decorative shapes are not evidence of an overflow defect. Light-mode body text and controls look clear at the default size. Dark mode preserves readable content and distinguishable controls, but its many similar dark green surfaces look heavy and muddy, especially the large Rosary card; this is a visual judgment, not a measured contrast failure.

The reviewed screenshot set also has inconsistent screen titles: Home light reads **Home**, while Home dark reads **Today**; Bible light reads **Bible**, while Bible dark reads **Holy Bible**. This may be a mixed capture set during copy revision. It must not be represented as proven theme-dependent runtime behavior. A final comparison needs a coherent capture set before presenting mode parity as evidence.

## Changes with the highest visual return

1. Recompose Bible and Prayers around their lookup/browsing tasks. Keep their art, but give it a useful role and reduce the independent title/banner/tab/control layers before the content.
2. Unify Home's date, liturgical context, celebration, and illustration into a clearer opening hierarchy; keep the Mass action prominent.
3. Retain the Rosary's bead map while improving the visual focus on the current prayer and active bead. Reduce the amount of low-information diagram area ahead of the task if needed.
4. Tune surface roles and icon weights across the four screens. Differentiate current content, controls, and grouped rows through deliberate emphasis, rather than making every region a similarly prominent rounded shape.
5. Refresh the default screenshots together after copy settles. Reassess longer prayer states and larger text separately; these references do not verify them.

The present Material implementation is serviceable and has some strong task-specific content. I would not call it equal to the genuine Google reference in visual polish on these images, and there is no basis here for calling it exceptional or validated against Apple/Vatican work.

## Input identification

SHA-256 prefixes recorded from the reviewed files, so later captures can be distinguished:

| Screen | Light | Dark |
| --- | --- | --- |
| Home | `7ccb9e24154f` | `32a1db877497` |
| Bible | `ced3c3576688` | `d8987ff4a3a3` |
| Prayers | `3e0009b368b0` | `d5714f15560c` |
| Rosary | `eb0c0604c0f9` | `829d0db4f35d` |

Full upstream Google provenance, pinned URLs, dimensions, and SHA-256 checksums are in `/workspace/.cloud-setup/references/whole-app/provenance.json`.
