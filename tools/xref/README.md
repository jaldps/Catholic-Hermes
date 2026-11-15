# Cross-reference assets

`app/src/main/assets/xref/xref_{en,es,pt}.bin` are generated here from the
OpenBible.info cross-references (CC BY 4.0, largely from the public-domain
*Treasury of Scripture Knowledge*), re-keyed from King James versification to
each bundled Bible's own numbering (Douay-Rheims, Platense, Matos Soares).

How the re-keying works:

- **Psalms:** Hebrew-to-Vulgate numbering (9–10, 114–115, 116 and 147 merge or split), plus each edition's title verses.
- **Daniel 3–4:** follows the Vulgate, which includes the Song of the Three Young Men.
- **English:** aligned to the King James book by book with a word-similarity dynamic-programming alignment (98.7% of verses land on their best match).
- **Spanish and Portuguese:** follow the English wherever their chapters agree, and the Hebrew chapter breaks where they follow them.

```bash
mkdir -p build/xref/dl && cd build/xref/dl
```

```bash
curl -LO https://a.openbible.info/data/cross-references.zip && unzip cross-references.zip
```

```bash
curl -L -o kjv.json https://raw.githubusercontent.com/thiagobodruk/bible/master/json/en_kjv.json
```

```bash
cd ../../.. && python3 -c "import sys,pickle; sys.path.insert(0,'tools/xref'); from align import *; pickle.dump(english_map(), open('build/xref/en_map.pkl','wb'))"
```

```bash
python3 -c "import sys,pickle; sys.path.insert(0,'tools/xref'); from common import *; from langmap import *; K=kjv(); E=lang('en'); en=pickle.load(open(D+'/en_map.pkl','rb')); pickle.dump({l: lang_map(l,K,E,en) for l in ['en','es','pt']}, open(D+'/maps.pkl','wb'))"
```

```bash
python3 tools/xref/build_assets.py
```

## Audit and regression checks

`tools/xref/audit_xref.py` checks that well-known KJV verses (52 cases across Psalms, Daniel, Joel, Malachi, Exodus,
Esther, ...) land on a verse that says the same thing, per language, for the bundled editions and for every converted
edition. `tools/xref/test_xref_regression.py` pins the findings (Genesis 1:2 reaches Psalm 103:30, not 103:31, in
Spanish). Verse divisions that differ inside a chapter (the Platense Ps 103 has 36 verses against 35 in the Douay) are
placed with a Gale-Church length alignment (`langmap.gc_align`) against the bundled English chapter, with psalm titles
tried as a leading offset; a KJV verse an edition merges into its neighbour inherits the neighbour's target.

## Downloadable editions (Catholic Bible Tools)

```bash
python3 -I tools/bibles/convert_cbt.py --src <cbt checkout> --out <out>        # bibles-out/<edition>/, manifest_cbt.json
XREF_WORK=build/xref python3 -I tools/xref/build_assets.py --editions <out> <out>/xref-out
XREF_WORK=build/xref python3 -I tools/xref/validate_editions.py <out> <out>/xref-out
XREF_WORK=build/xref python3 -I tools/xref/audit_xref.py <out>
```

`editions.py` maps KJV references onto each edition's numbering (Vulgate or Hebrew psalms, Daniel 3/4, Hebrew chapter
shifts by position, Gale-Church within chapters whose verse counts differ, word-similarity alignment for English).
