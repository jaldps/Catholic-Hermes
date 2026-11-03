# Final layout QA

Read-only visual review of **all 30** current root screenshots at 320 × 760 with Roboto and 130% font scale: Home, Bible, Rosary, Prayers, and More × Apple/Material/Vatican × light/dark. Also inspected 14 representative Spanish/Portuguese screenshots at the regular viewport. No application edits or builds.

## Confirmed defect

**P2 — Vatican bottom navigation cuts off destination names.** In all ten narrow Vatican captures, ROSARY is rendered as **ROSAR** and PRAYERS as **PRAYE**, without an ellipsis. This affects both selected and unselected destinations. These are fixed navigation labels, so scrolling cannot recover the missing letters.

Affected narrow files are all five root names (`home`, `bible`, `rosary`, `prayers`, `more`) with each of these suffixes, under `app/build/screenshots/app/`:

- `_vatican_light_font_roboto_scale_130_w320_h760.png`
- `_vatican_dark_font_roboto_scale_130_w320_h760.png`

The same issue is confirmed at regular size in `home_vatican_light_es.png`, `prayers_vatican_light_es.png`, and `more_vatican_light_es.png`: **ORACIONES** loses its final S and reads **ORACIONE**. The Portuguese Vatican labels fit in the three representative captures inspected. Ensure the complete label fits its actual measured navigation slot or give it an intentional multiline layout; clipping a word is not an adequate fallback.

## Other results

- **Apple and Material navigation:** all five labels fit in every narrow capture. No overlap found.
- **Home:** long November 3 celebration titles reflow inside their cards in all identities. Date/art/title do not collide. Read Mass labels remain complete where visible. Several captures show a loading skeleton; Material light shows the offline message. Those are captured content states, not clipping findings.
- **Bible:** the shortened Search placeholder fits in all six narrow captures. Tabs, canon selector, Versions, book names, and chapter counts remain separate and readable. The earlier Material search-hint loss is resolved visually.
- **Rosary:** mystery names wrap to two lines inside the bead ring without collision. The opening prayer fits the width. Primary/secondary actions continue below the visible viewport, with only part of Next visible in Apple/Material and no progression control visible in Vatican. These static captures cannot establish that the actions are unreachable or that a scrolled Back label is clipped. Ordinary vertical scrolling is not being reported as a defect.
- **Prayers:** long rows such as Morning Offering of Daily Life wrap cleanly, without touching chevrons. Apple's Night prayer tab wraps inside its segmented control; Material/Vatican tabs remain readable. No confirmed action-label clipping.
- **More:** all visible destination names fit; Settings begins near the lower viewport edge and continues below it. This is ordinary scrolling, not proof of inaccessible settings.

## Localized samples inspected

For each identity, inspected Home and Prayers in Spanish light mode and Portuguese dark mode, plus Vatican More in those two language/mode combinations: `home_{apple,material,vatican}_light_es.png`, `home_{apple,material,vatican}_dark_pt.png`, `prayers_{apple,material,vatican}_light_es.png`, `prayers_{apple,material,vatican}_dark_pt.png`, `more_vatican_light_es.png`, and `more_vatican_dark_pt.png`.

The long seasonal titles, Spanish Acto de Contrición (Examen de Conciencia), and settings values fit or wrap. No additional clipping or overlap was confirmed in this sample.

**Disposition:** fix and re-render the Vatican navigation labels. The remaining inspected layouts show no confirmed clipping/overlap beyond ordinary viewport scrolling. This review does not certify unshown scroll states or screen-reader behavior.
