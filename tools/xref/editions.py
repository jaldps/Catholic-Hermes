"""KJV -> edition versification maps for the downloadable editions (tools/bibles/convert_cbt.py output).

Editions keep their own numbering, with verse gaps where a critical edition omits a verse and with
"last verse" chapter lengths that include unnumbered-in-KJV psalm titles. The map is structural:
  * Psalms: Vulgate numbering (mapping.psalm_map) or Hebrew numbering with a title offset, detected from Psalm 10.
  * Daniel: 3:24-30 -> 3:91-97, 4:1-3 -> 3:98-100 when chapter 3 holds the Song of the Three (>=97 verses).
  * Every other book: chapters whose last verse differs from the KJV form runs; a run whose totals agree (or differ
    by <= 3 over >= 2 chapters, a verse omitted by the edition) is aligned by position (Joel, Malachi, Genesis 31-32 ...),
    anything else keeps chapter:verse where the edition has it.
English editions are then refined by the word-similarity alignment of align.py, as the bundled Douay-Rheims is.
Targets the edition does not contain are dropped (reported by the builder)."""
import sys, json, os; sys.path.insert(0, __file__.rsplit('/', 1)[0])
from common import *
from mapping import psalm_map


def load_edition(root, edition):
    """-> (texts {book: [[verse text per slot 1..last]]}, exists {book: [set of verse numbers]})."""
    manifest = json.load(open(os.path.join(root, "manifest_cbt.json")))["editions"][edition]
    texts, exists = {}, {}
    for e in manifest:
        data = json.load(open(os.path.join(root, "bibles-out", edition, e["file"]), encoding="utf-8"))
        chs, ex = [], []
        for ch in data["chapters"]:
            slots = [""] * ch["verses"][-1]["verse"]
            for v in ch["verses"]: slots[v["verse"] - 1] = v["text"]
            chs.append(slots); ex.append({v["verse"] for v in ch["verses"]})
        texts[e["id"]] = chs; exists[e["id"]] = ex
    return texts, exists


def _gc_chapter(kchap, slots, titles):
    """Gale-Church placement of one KJV chapter's verses into an edition chapter whose verse count differs.
    slots: edition verse texts by number ('' where the edition has no such verse). -> {kjv verse: edition verse}"""
    from langmap import gc_align
    have = [(i + 1, t) for i, t in enumerate(slots) if t]
    if not have: return {}
    a = gc_align(kchap, [t for _, t in have], titles=titles)
    return {v: have[j - 1][0] for v, j in a.items() if 1 <= j <= len(have)}


def structural_map(K, L):
    """K, L: {book: [chapter lists]} (only lengths matter). -> {(book, c, v): (c, v)}, not yet checked for existence."""
    out = {}
    kc_all = {b: [len(c) for c in K[b]] for b in OURS}
    for b in OURS:
        kc, lc = kc_all[b], [len(c) for c in L[b]]
        if b == "psa":
            V = lambda n: lc[n - 1]
            if len(lc) >= 150 and V(10) <= 12:
                out.update({(b, h, v): (c, w) for (h, v), (c, w) in psalm_map(K, L).items()})
            else:
                for h in range(1, 151):
                    if h > len(lc): continue
                    if lc[h - 1] == kc[h - 1] and all(L[b][h - 1]):
                        for v in range(1, kc[h - 1] + 1): out[(b, h, v)] = (h, v)
                    else:
                        for v, w in _gc_chapter(K[b][h - 1], L[b][h - 1], True).items(): out[(b, h, v)] = (h, w)
            continue
        first = 0
        if b == "dan" and len(lc) > 3 and lc[2] >= 97:
            for c in (1, 2, 3, 4):
                for v in range(1, kc[c - 1] + 1):
                    if c == 3 and v >= 24: out[(b, c, v)] = (3, v + 67)
                    elif c == 4 and v <= 3: out[(b, c, v)] = (3, 97 + v)
                    elif c == 4: out[(b, c, v)] = (4, v - 3)
                    else: out[(b, c, v)] = (c, v)
            first = 4
        kx = lambda i: kc[i] if i < len(kc) else 0
        bad = [i for i in range(first, max(len(kc), len(lc))) if i >= len(lc) or i >= len(kc) or kc[i] != lc[i]]
        handled, i = set(), 0
        while i < len(bad):
            j = i
            while j + 1 < len(bad) and bad[j + 1] == bad[j] + 1: j += 1
            run = list(range(bad[i], bad[j] + 1))
            ktot = sum(kx(c) for c in run); ltot = sum(lc[c] for c in run if c < len(lc))
            if ktot == ltot or (len(run) >= 2 and abs(ktot - ltot) <= 3):
                flat = [(c + 1, v) for c in run if c < len(lc) for v in range(1, lc[c] + 1)]; k = 0
                for c in run:
                    for v in range(1, kx(c) + 1):
                        if k < len(flat): out[(b, c + 1, v)] = flat[k]
                        k += 1
                handled.update(run)
            i = j + 1
        for c in range(first, len(kc)):
            if c in handled: continue
            if c < len(lc) and kc[c] != lc[c]:
                for v, w in _gc_chapter(K[b][c], L[b][c], False).items(): out[(b, c + 1, v)] = (c + 1, w)
                continue
            for v in range(1, kc[c] + 1): out[(b, c + 1, v)] = (c + 1, v)
    return out


def edition_map(K, texts, exists, english=False):
    """Final KJV -> edition map restricted to verses the edition has. Returns (map, dropped KJV verse count)."""
    from align import align_book
    prior = structural_map(K, texts)
    final = {}
    if english:
        for b in OURS:
            kc = [len(c) for c in K[b]]; lc = [len(c) for c in texts[b]]
            if kc == lc: final.update({k: v for k, v in prior.items() if k[0] == b})
            else: final.update(align_book(K, texts, b, prior))
    else:
        final = prior
    out, dropped = {}, 0
    for (b, c, v), (c2, v2) in final.items():
        if c2 <= len(exists[b]) and v2 in exists[b][c2 - 1]: out[(b, c, v)] = (c2, v2)
        else: dropped += 1
    return out, dropped + sum(1 for b in OURS for c, ch in enumerate(K[b], 1) for v in range(1, len(ch) + 1) if (b, c, v) not in final)
