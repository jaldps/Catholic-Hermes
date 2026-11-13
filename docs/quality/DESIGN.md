# CatholicGPT — Lumen and Sanctuary

Current direction, 12 November 2026. This supersedes the older three-identity design direction. Two identities share AppKit, routes, content, semantics, test tags, session state and appearance controls. Light/Dark/System is independent of identity. Stored APPLE and IVE selections migrate to Lumen.

## Art direction

| | Lumen | Sanctuary |
|---|---|---|
| Character | Luminous contemporary chapel | Quiet personal missal |
| Ground | Porcelain / warm charcoal | Vellum / burgundy ink |
| Type | Inter UI; literary serif reading | EB Garamond editorial headings; Inter controls; literary serif reading |
| Geometry | 24dp cards, soft capsules, floating dock | 8–12dp frames, ruled groups, solid folio navigation |
| Material | Glass on navigation and transient tools; opaque text cards | Flat paper, fine rules, restrained antique gold |
| Art | Warm immersive crop | Framed and slightly inset |
| Accent | Liturgical green default | Oxblood default |

Primary action is the strongest shape. The first viewport offers one understandable next step. Opaque content surfaces support reading; glass is reserved for the control layer. Avoid glass card towers, simulated blur behind text and animated paper texture. Fine paper grain is static and decorative only.

## Shared contract

Screens call AppComponents/AppKit. LocalAppDesign.current.isSanctuary selects geometry/material within the complete shared kit; screens never fork navigation or content. Canonical preference IDs: LUMEN, SANCTUARY. APPLE/IVE remain source-compatible migration aliases. All controls have at least 48dp interactive targets. Dock labels remain at least 12sp and wrap; never shrink to fit. Responsive gutters 20dp and reading column max 640dp.

Reader font choice overrides identity fonts when explicitly selected; identity-default retains the sans/serif hierarchy. Text size, voice speed and all accessibility preferences persist immediately. Reduce motion removes spring-scale/slide motion. Reduce transparency makes controls solid. High contrast strengthens ink and borders. These variants have equal functionality.

## Source guidance

Official pages retrieved 12 November 2026. Apple’s current Materials guidance explicitly says “Don’t use Liquid Glass in the content layer” and “Use Liquid Glass effects sparingly.” The guidance also calls for reduced-transparency and increased-contrast variants. Google’s research identifies color, shape, size, motion and containment as ways to draw attention to key actions and group related elements. The implementation applies these hierarchy principles without claiming Android renders native Apple Liquid Glass.

- Apple Human Interface Guidelines, Materials: https://developer.apple.com/design/human-interface-guidelines/materials — separate the navigation/control layer from content; translucent controls must retain legibility.
- Google Design, M3 Expressive research: https://design.google/library/expressive-material-design-google-research — size, containment and shape establish action hierarchy.
- Android Compose blur: https://developer.android.com/develop/ui/compose/graphics/blur/overview — use bounded effects and supported-device fallbacks; this app uses a lightweight composited material rather than claiming native Apple glass.

## Acceptance

Render both identities in light/dark on Today, Pray, Read and Explore, appearance, guided prayer and long reading. Verify 320dp, landscape/tablet, 200% system text and EN/ES/PT. Check actual composited normal text 4.5:1, large text and UI boundaries 3:1; inspect TalkBack order and selected states. Verify preference/session preservation on identity change, first font-size/voice-speed choices, image A→B at the same composition site, reduced motion/transparency and high contrast. Source changes alone are not AAA validation. Fresh screenshots, behavior tests and device accessibility checks are evidence; historical renders are not current acceptance.
