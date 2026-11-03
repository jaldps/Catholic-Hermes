# Vatican identity — current visual critique

Reviewed the current default Home, Bible, Rosary, Prayers, and More screenshots in light and dark modes under `app/build/screenshots/app`, plus the current Vatican font-dialog screenshots. No app source, builds, interaction tests, or A/B mapping were inspected. This is an independent screenshot assessment. It includes no live comparison with the current Vatican website.

## Verdict

**Light: 7.6/10. Dark: 7.0/10. Wowed: no.**

This is a convincing and coherent liturgical identity. Warm paper, ink, rubric red, engraved gold artwork, serif reading text, classical display capitals, and fine editorial rules reinforce each other. The artwork is the strongest element, but the surrounding interface has enough related choices that it does not feel like an illustration sitting in a generic template.

The current result is good, distinctive product design. I would not call it AAA or finished at an exceptional level. The principal gap is the precision and visibility of the small operational typography, particularly in dark mode. Some large vertical gaps also make the catalogue pages feel more loosely assembled than the beautifully controlled engraving.

## Current visible corrections

- **NEXT is the central primary Rosary action in both modes.** This resolves the earlier ambiguity between prayer progression and audio playback. The round play control is secondary and the disabled Back state reads correctly.
- **The font dialog now uses rubric red for selection and Done.** The former green mismatch is gone.
- **Inter is explicitly selected in the supplied font dialog.** Its sans-serif title, option labels, and preview are a valid selected-font presentation. I withdraw the suggestion that sans-serif appearance alone makes this dialog an identity failure. User font choice should remain real and respected.
- **Partly visible rows at the bottom are not proof of broken layout.** They can be normal scroll cues. These static root screenshots do not establish that any final row or control is unreachable, that the header does not scroll, or that content is permanently covered by navigation.

This report supersedes the remaining current-state claims in `critic-vatican2.md`; that file records the blind pairs and the older green-accented dialog as supplied at that time.

## Screen scores

| Screen | Light | Dark | Assessment |
| --- | --- | --- | --- |
| Home | 7.8 | 7.1 | Strongest editorial composition: art, liturgical title, and quotation form a clear reading sequence. |
| Bible | 7.7 | 7.1 | Clear index and elegant search; the gap before filters delays the catalogue unnecessarily. |
| Rosary | 7.7 | 7.3 | Relevant, memorable diagram and now-clear progression; small audio affordance still needs interpretation. |
| Prayers | 7.6 | 7.0 | Calm, legible catalogue with useful section rubrics; similar gap after search and subdued dark secondary details. |
| More | 7.2 | 6.7 | Useful quiet settings/index composition; dark setting values and small navigation are its weakest tier. |
| Font chooser | 7.3 | 7.0 | Clear real family names, obvious selection, readable preview; default family is unspecified. |

More does not need an illustration to earn its place in the identity. Its restraint is appropriate. Its lower score concerns typographic finish and secondary visibility, not lack of ornament.

## Three priorities

1. **Improve small-text and navigation visibility in dark mode.** Inactive bottom icons/labels, setting values, chapter counts, fine chevrons, and the Rosary's secondary labels feel subdued at phone scale. Increase the visible size or luminance of the operational elements selectively. Keep decorative engraving subordinate. The active bookmark supplies a useful non-color state cue, but inactive destinations still need comfortable visibility. Actual hit-box size and contrast ratios require separate validation; they cannot be certified from these images.
2. **Tighten the catalogue's vertical rhythm.** On Bible and Prayers, the blank interval between the search rule and the first filter/intention row is noticeably larger than the surrounding rhythm. Reduce that gap modestly, around 16–24 dp as a starting point, and evaluate at normal phone size. Preserve the artwork and readable body leading. This is an improvement to initial content visibility and composition, not a claim that scrolling is broken.
3. **Explain Identity default in the font picker.** Show the resolved family name beside or below that choice, so a person can understand what they are restoring. The live sentence and named Inter, Roboto, and EB Garamond options are useful. A short visible Play label for the Rosary audio control would be another worthwhile small clarity improvement after the default-font explanation.

## Reading and identity details

The Home quotation has a comfortable line measure and line spacing, and the serif shapes suit this subject. Red citations and rubrics establish a strong secondary reading layer. The purple seasonal marker adds meaning without disrupting the red/gold system. Headings, chapter counts, section labels, and body copy are visibly differentiated.

Dark mode has a coherent candlelit brown/gold atmosphere. Its body text remains readable in these screenshots. Its weakness is the collapse of secondary tiers toward the same muted brown-gold value: metadata, navigation, rules, and controls compete less clearly. Brightening everything would flatten the mood; strengthen things the user must read or operate.

The interface's tracked capitals work best in short headings and rubrics. On the longer tabs and bottom navigation they become small, widely spaced operational text. Those tiers should be judged at actual display size rather than at the enlarged screenshot scale.

The font dialog now belongs much more comfortably to the palette. EB Garamond's option label is visibly serif, while the selected Inter preview is sans serif. That is useful visible evidence of font distinction, but these screenshots alone cannot establish font loading, persistence, or application across every screen.

The result is worth keeping and refining. The evidence supports specific craft and clarity fixes, not a claim of perfection or a broad redesign.
