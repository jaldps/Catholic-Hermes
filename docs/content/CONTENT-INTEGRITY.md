# Content repair evidence — 12 November 2026

The Catechism was rebuilt from 508 HTML pages published by the Holy See, rather than filling missing translations with another language. All 2,865 paragraph IDs are present exactly once in Spanish, English and Portuguese. Every Spanish and Portuguese field differs from its corresponding English field. That last check detects fallback copies; it does not establish a formal linguistic or ecclesiastical review.

Primary source indexes:

- English: https://www.vatican.va/archive/ENG0015/_INDEX.HTM
- Spanish: https://www.vatican.va/archive/catechism_sp/index_sp.html
- Portuguese: https://www.vatican.va/archive/cathechism_po/index_new/prima-pagina-cic_po.html
- Independent official confirmation of the current English §2267: https://www.vatican.va/roman_curia/congregations/cfaith/documents/rc_con_cfaith_doc_20180801_catechismo-penadimorte_en.html

`catechism-provenance.json` records each retrieved URL, raw HTML SHA-256, extracted paragraph IDs, the retrieval date, and a digest of each complete language corpus. HTML caches remain outside git. Paragraph prose is transcribed from the source; HTML layout whitespace, headings and navigation are omitted. In-text source note markers remain. The source footnote bodies are not bundled in each paragraph and remain accessible in the official source. Source spelling, translation choices and occasional punctuation defects have not been silently rewritten.

The extraction handles bare bold number markers outside `<p>` elements, italic numbered summary paragraphs, numbered lists inside paragraphs, continuation quotations and the 2018 three-paragraph revision of §2267. Explicit source boundary repairs are documented in the script. The Portuguese source prints §2217 as 2117 and §2439 as 1439; the import assigns the IDs established by their sequential context and the parallel official English/Spanish paragraphs, without changing the Portuguese prose. English §2077 is embedded in the preceding paragraph and §2436 follows a line break inside §2435; both are split at the actual source numbers.

To verify the checked-in corpus offline, with the Python standard library:

```sh
python tools/content/catechism_corpus.py --verify
```

To reproduce ingestion with Python and `lxml`, preserving a cache outside the checkout:

```sh
python tools/content/catechism_corpus.py --rebuild --cache /tmp/catholicgpt-ccc-sources
```

The importer validates the entire proposed corpus before changing application files. It fails on missing/duplicate numbers, wrong-language copies, another numbered paragraph included in a field, short/truncated content, or the known broken boundary fixtures. Regression checks also preserve the three stages of Gospel formation (§126), the indulgence definition (§1471), §2077 and §2436, and the current death-penalty teaching in all three languages. Digests make later unreviewed changes visible. For a fresh upstream revision, review the changed source and update the retrieval date as well as the generated provenance.

## Novenas and editorial material

Exactly 2,000 occurrences of this two-line marketing prefix were removed from 200 of the 246 novena files:

> Continue esta novena no app
>
> Configure lembretes para não perder nenhum dia

A comparison against the previous git revision verified that all 246 files otherwise retained their exact prayer content. The sole additional novena change replaces the inaccurate “Patron saint of the Kingdom of Jesus” metadata with “Jesus Christ, King of the Universe” in all three languages. Each of the 246 devotions retains its original complete day sequence, including the 54-day Rosary novena. This is a precise contamination repair; it does not claim that every imported devotion has undergone a fresh theological, translation, licensing, historical or attribution review. Original novena source URLs and permissions remain an unresolved provenance limitation of the inherited dataset.

The former Lauds item is now named “Morning prayer · abridged selection” (localized) and explains that it is a devotional selection, not the complete office or today's appointed Liturgy of the Hours. Its prayer text is preserved. The study question dataset now supplies a localized editorial title and notice explaining that its abridged, sometimes repeated topic summaries do not reproduce the 598 official Compendium questions and answers. These changes do not turn editorial summaries into authoritative quotations.

No imprimatur, full ecclesiastical review, comprehensive translation certification or real-device verification is claimed by these static checks.
