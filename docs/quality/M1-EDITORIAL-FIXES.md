# M1 editorial authority and devotional accuracy fixes

Reviewed 13 November 2026. Narrow changes in `ScriptureStudyData.kt`, `ScriptureStudyScreen.kt`, `DevotionsAndHistoryData.kt` and `DevotionsExplorerScreen.kt`, following [CONTENT-FORMATION-AUDIT.md](CONTENT-FORMATION-AUDIT.md).

- **Editorial authority (ES/EN/PT):** the sacrament course now identifies itself as an editorial course; removed the apostolic course's additional English “Vatican study” claim. The shared course caption explicitly identifies editorial authorship and references to the Catechism/Dei Verbum. Replaced its verification badge with a reading icon. Lesson commentary is labeled “Editorial commentary and references,” with existing document and paragraph references retained. These changes make no claim of ecclesiastical approval.
- **Christ devotion prayers (ES/EN/PT):** replaced the blanket “Official prayer” heading with the neutral “Devotional prayer.” The legacy `officialPrayer` model field and every prayer text remain intact; individual historical attribution is not established by that field name.
- **Lourdes (ES/EN/PT):** removed claims of inherently healing water, thousands of medically documented cures and guaranteed physical healing. Retained the apparition narrative with attribution and described prayer/comfort without a personal cure guarantee. Replaced claims that medical committees prove or declare miracles with the distinction between medical assessment under current knowledge and recognition as miraculous by the competent bishop. Omitted a volatile cure count: the shrine currently lists 72, recognized **by the Church**.
- **Chile chronology (ES/EN/PT):** removed both occurrences of the unverified account of an eighteenth-century canvas surviving the 1647 earthquake, including the associated wall/canvas and reconstruction claims. The entry now identifies the devotional subject, states that the image's date/origin require documentary verification, and includes no verified miracle narrative. No substitute historical date, image type or earthquake story was invented. Stable ID, title, country, feast, shrine metadata and prayer were preserved; their preservation is not historical verification.

## Primary sources actually consulted

All fetched successfully from the official Lourdes sanctuary on 13 November 2026:

1. [Miraculous healings](https://www.lourdes-france.com/en/miraculous-healings/): “72 cases of unexplained cures have been recognised as miraculous by the Church.”
2. [Recognition of a miracle](https://www.lourdes-france.com/en/recognition-of-a-miracle/): distinguishes stages of medical enquiry, CMIL's assessment according to present scientific knowledge, and transmission of the file to the bishop of the cured person's diocese.
3. [The Medical Bureau of the Sanctuary](https://www.lourdes-france.com/en/the-medical-bureau-of-the-sanctuary/): medical bodies assess whether a cure is unexplained under current medical knowledge; historical recognition of cures as miraculous is attributed to the bishop.
4. [The Water of Lourdes](https://www.lourdes-france.com/en/the-water-of-lourdes/): narrates Bernadette being directed to the spring and explicitly says the water has no therapeutic or specific properties.

## Verification and remaining limits

`git diff --check` passes. A static before/after comparison confirms unchanged IDs/track IDs, prayer fields, biblical texts/references and Catechism reference lists in the two data files. No builds, unit tests, screenshot captures or device checks were run for this delegated copy-only change. Both design identities and progress/navigation code remain unchanged.

No primary source for the Chile entry was established; conservative removal was chosen instead of replacing the contradicted account. This is not a full audit of that entry or other devotional claims, individual prayer provenance, source translations or permissions. Spanish/Portuguese wording was reviewed editorially; native-language and qualified pastoral review remain outside this change. Existing lesson source references were preserved, not individually revalidated.
