# Material simplicity blind review

Viewed all ten randomized pairs, no mapping/source inspection. Judged task clarity, scanning, clear actions, and Material coherence. No credit given for ornament. No live external reference comparison was available. Reported navigation-test results are useful context but were not independently executed by this visual reviewer.

**Wowed: no.** The simpler variants are substantially more useful and calmer, especially More and the two libraries. They are strong conventional layouts, not exceptional visual design. Removing decoration improves them without weakening their visible Material identity: tonal surfaces, navigation pills, search bars, tabs, and selected chips still establish it.

| Pair | A | B | Winner | Reason |
|---|---:|---:|---|---|
| Home light | 8.0 | 7.5 | A | Compact feast summary removes redundant color text and an oversized badge; reading/action starts earlier. |
| Home dark | 7.5 | 8.0 | B | Same improvement. Today is also the clearer contextual title for this daily screen. |
| Bible light | 7.5 | 8.0 | B | No decorative banner: search and filters are immediate and the next book category becomes visible. |
| Bible dark | 8.0 | 7.5 | A | Same task-first layout. All substantive filtering controls remain present. |
| Rosary light | 8.0 | 7.5 | A | Optional Instructions replaces always-visible guidance; mysteries selector becomes usable above navigation. Core prayer/action remains intact. |
| Rosary dark | 7.5 | 8.0 | B | Same appropriate progressive disclosure and clearer access to changing mysteries. |
| Prayers light | 7.5 | 8.0 | B | Removing banner and repeated Latin secondary titles greatly improves scanning; several more prayers are immediately visible. |
| Prayers dark | 7.5 | 8.0 | B | Same improvement. “Other prayers” is plainer, if less meaningful, than “Faith & refuge.” |
| More light | 8.0 | 7.0 | A | Strongest improvement: all sections plus Appearance and Language fit; plain icons replace arbitrary decorative badges and redundant summaries. |
| More dark | 8.0 | 7.0 | A | Same major gain in navigation density and reduced visual noise. |

## Blocking defects

**No confirmed blocking overflow or collisions in either set's captured states.** Core actions fit and text wraps without overlap. Partial lower rows in a scrolling viewport are not proof of inaccessible content. Screenshots cannot verify hit regions, font scaling, scroll reachability, or screen-reader labels.

## Remaining concrete issues

1. **Home is still more verbose than its task requires.** A long incomplete Gospel excerpt, reference list, Mass action, and separate Gospel meditation row duplicate related content. Use a deliberately short quotation rather than a five-line ellipsis, keeping Mass reading and meditation distinctly named. The Daily spiritual routine section appears only as an orphan heading at the viewport boundary in the simpler variants; reducing the excerpt would expose a useful item beneath it.
2. **Rosary's visual area still outweighs the prayer.** The faint bead circle consumes roughly half the main card and gives little visible current-step information. Shrink it or make current progress clearer, particularly in dark mode. The visual controls alone do not establish accessible Next/Previous names; validate these semantically, including the disabled first-step state. The Instructions trigger is a good simplification if its expanded content remains readily discoverable and dismissible.
3. **Filters and settings need final interaction polish.** Prayer chips are visibly shorter than Bible chips and end in an abrupt partial “Fam…” chip at the right edge. Standardize the interactive rhythm and ensure 48dp minimum touch regions around small painted chips and Versions. Plain More labels are calmer, although “Spiritual Life” and “Habits & Virtues” still require entering to understand scope; solve that through precise destination names where possible, rather than restoring every subtitle.

The simpler direction is preferred on every screen. This visual result supports the user's simplicity priority; it does not independently prove DX, AX, or interaction correctness, and it does not resolve the unavailable external-reference assessment.
