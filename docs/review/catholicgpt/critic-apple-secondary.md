# Apple secondary destination visual check

Inspected all 14 current screenshots: Catechism, Doctrine & History, Churches & News, Habits & Virtues, Spiritual Life, Parishes, Calendar & Shrines, each light/dark. Images only; no source or behavior inspected. No live external reference access.

No definite blocking text collision, horizontal overflow, overlapping controls, or clipped button label is visible in these initial viewports. Long church names and commandments wrap cleanly. All major titles fit. Primary actions in Habits, Spiritual Life, and Parishes are fully visible and legible.

One important verification remains: Doctrine, Churches, Habits, and Calendar show a row cut at the common bottom content boundary immediately above the tab bar. This is normal if the list scrolls and every final row clears navigation when scrolled to the end; a static initial screenshot cannot establish that. If any of these screens cannot scroll fully clear, that is a blocker. This is a behavior verification request, not evidence of a confirmed overflow bug.

Calendar dark mode has the clearest visible legibility defect: the purple November 2 / November 29 labels and green November 8 / November 15 labels are muted against dark tinted card backgrounds. Important date/category metadata should be brighter. I would treat this as a contrast fix before visual signoff, although primary titles remain readable.

Catechism's short list, Spiritual Life's empty state, and Parishes' empty state have no blocking layout defects in either theme. Their large unused lower regions are a composition choice rather than overflow. Habits' long action label fits at this size, with only a modest remaining horizontal margin; recheck at larger accessibility text sizes. No such scaled screenshots were supplied.
