# Skills from ui-skills.com (curated, 28-nov-2026)

Selected by the coordinator for the single Catholic1 visual identity, at the owner's request. Each skill was cloned at a fixed commit, read in full and copied without changes, with its licence. Installed by copying; `npx skills add` was not used.

| Skill (folder) | Source | Commit | Licence | Use here |
|---|---|---|---|---|
| `dxe-android-native-ui` | github.com/kemiljk/skills | 32a9154 | MIT | Compose and Material: accessibility, adaptive layout and state |
| `build-design-system` | github.com/arla6ka/skills | 1ce5304 | MIT | Tokens, states and components of the single design system |
| `migrate-design-system` | github.com/arla6ka/skills | 1ce5304 | MIT | Move screens onto the new system (build-design-system routes here) |
| `design-system-boss` | github.com/arla6ka/skills | 1ce5304 | MIT | Entry point for broad design-system work (routes to the two above) |
| `brand-identity` | github.com/fatihaydost/brand-identity-skill | af7ba40 | MIT | Catholic1 palette, type and brand kit, measured for contrast |
| `apple-design` | github.com/emilkowalski/skills | 64eb3de | MIT | Polish: materials, typography, motion and restraint |
| `baseline-ui` | github.com/ibelick/ui-skills | 49289ef | MIT | Fast cleanup of spacing, hierarchy and typography |
| `impeccable-native` | github.com/pbakaus/impeccable | 2fc5e14 | Apache-2.0 | Native Android audit and adaptation (reference documents only, with a wrapper `SKILL.md`) |

**Notes from the review:**
- The scripts of `build-design-system` and `migrate-design-system` read git and take local screenshots with Playwright and Chromium. They write nothing to the repository by themselves.
- `brand-identity/scripts/site_palette.py` downloads a website only when it is asked to analyse that site's palette. None of the scripts send data anywhere.
- Most of these skills are written for the web. Translate them to Compose and to the tokens in `ui/design`, as with the `better-*` skills (see `docs/tasks/T-062.md`, "Traducción web → Compose").
- Left out: `apply-design-system` and `audit-design-system`, which only work in Figma; `apple-hig`, which is for iOS; `brandkit`, which no longer exists (404).
