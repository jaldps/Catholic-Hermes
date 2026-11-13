# Milestone 1 — catechesis consistency corrections

Reviewed and edited 13 November 2026. Scope: editorial ES/EN/PT strings in `app/src/main/java/com/example/data/repository/DoctrineAndSpiritualData.kt`; no ecclesiastical approval is claimed.

## Changes and persistence

- **Fourth Church precept:** all three languages now give the same explicitly Latin framework: every Friday and Lent are penitential; Friday abstinence has a solemnity exception; Ash Wednesday and Good Friday have fasting and abstinence; conferences of bishops may specify observance and substitute other penances. Abstinence begins at 14; fasting applies at 18–59. Readers are directed to their own Church/conference's norms, without inferring territory or rite from app language. Health-sensitive advice directs readers to a priest and avoids putting health at risk; this is editorial pastoral guidance, not a claim that canon 1252 specifies medical exemptions.
- **Confession outline:** retains five steps, replacing exhaustive/detailed examination with sincere, reasonable preparation. Explains remembered grave sins in kind and number, priestly help and absolution, honest forgetfulness, assigned penance and possible reparation. Distinguishes sacramental preparation from the daily prayer examen and directs persistent scruples to steady guidance from a confessor. The adjacent three conditions for mortal sin, diminished responsibility, perfect contrition and the precise Communion exception were not edited.
- **Trivia `t51`:** question now explicitly concerns Latin use of reason; its existing correct option remains “about age seven,” at index `1`. The explanation distinguishes Eastern infant Communion after Baptism and Confirmation and cites CCC 1233/1244 and canon 97 §2. This avoids treating seven as a universal Catholic age or guaranteeing First Communion at that age.
- **Trivia `t52`:** existing question and options remain about sacred Chrism, correct index `1`. Explanation now distinguishes the ordinary Latin minister (bishop), priests with the appropriate faculty, and Eastern chrismation ordinarily conferred by the baptizing priest with Chrism consecrated by a patriarch or bishop.

No question IDs, option order, answer indices, record order, categories, models, persistence keys, courses, or unrelated content were changed. `t51` options continue to answer its narrowed question consistently; `t52`'s unchanged options continue to answer its unchanged material question.

## Primary sources actually consulted

The following official Vatican pages were retrieved successfully during this edit, rather than inferred solely from search results or an existing app reference:

1. [Catechism, Baptism — CCC 1233 and 1244 (Spanish)](https://www.vatican.va/archive/catechism_sp/p2s2c1a1_sp.html): Latin/Eastern initiation and Eastern Communion for infants.
2. [Catechism, Confirmation — CCC 1290 and 1312–1314 (Spanish)](https://www.vatican.va/archive/catechism_sp/p2s2c1a2_sp.html): Eastern baptizing presbyter and consecrated Chrism; Latin ordinary minister, priestly faculties, and danger of death.
3. [Code of Canon Law, canons 96–123 — canon 97 §§1–2 (English)](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib1-cann96-123_en.html): majority at 18; presumption of use of reason upon completing seven years.
4. [Code of Canon Law, canons 1244–1253 — specifically 1249–1253 (English)](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib4-cann1244-1253_en.html): penitential purpose, all Fridays/Lent, solemnity exception, Ash Wednesday/Good Friday, ages and conference adaptations.
5. [Catechism, Penance — CCC 1454–1460 (Spanish)](https://www.vatican.va/archive/catechism_sp/p2s2c2a4_sp.html): examination in the light of Scripture, confession of remembered mortal sins, forgiveness, penance and reparation.
6. [Code of Canon Law, canons 959–997 — particularly 960 and 988 (English)](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib4-cann959-997_en.html): ordinary individual confession/absolution and grave sins in kind and number after diligent examination.

The health and persistent-scruples sentences are editorial pastoral applications, not quotations of these canons. No particular episcopal conference's current discipline or Eastern particular law was researched or represented. ES/EN/PT copy is editorial explanation of the cited sources, not an authorized translation of official text.

## Verification and limitations

Reviewed the scoped diff and checked the question/option/answer relationships directly. No build, tests, new tests, screenshots, or device checks were run for this copy-only subtask; integration validation belongs to the parent milestone task. Independent catechetical/pastoral and native-language review remain valuable; this document does not claim those reviews occurred.
