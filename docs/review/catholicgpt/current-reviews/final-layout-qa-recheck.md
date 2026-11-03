# Final Vatican navigation recheck

**Pass: the previously reported ROSARY, PRAYERS, and ORACIONES clipping is fixed in every image inspected.**

Visually inspected all 20 regenerated Vatican root captures under `app/build/screenshots/app/`, using each root name (`home`, `bible`, `rosary`, `prayers`, `more`) with these four suffixes:

- `_vatican_light_font_roboto_scale_130_w320_h760.png`
- `_vatican_dark_font_roboto_scale_130_w320_h760.png`
- `_vatican_light_es.png`
- `_vatican_dark_es.png`

In all ten narrow Roboto/130% captures, **HOME, BIBLE, ROSARY, PRAYERS, MORE** are complete on one line. All five destinations remain visible within the viewport, with separate icons/captions and no overlap or ellipsis.

In all ten regular Spanish captures, **INICIO, BIBLIA, ROSARIO, ORACIONES, MÁS** are complete on one line, including the final S in ORACIONES. The selected caption and ribbon align with the current destination across all five roots in both modes.

No remaining navigation clipping, overlap, or unreadable caption was found. Lower page content continuing beyond the viewport is ordinary vertical scrolling and is not a navigation-caption defect.

This recheck clears the specific visual gate raised in `final-layout-qa.md`. It is a screenshot inspection; no application edits, builds, or test runs were performed during this recheck.
