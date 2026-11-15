#!/usr/bin/env python3
"""Converts Catholic Bible Tools (github.com/catholicbibletools/cbt, src/bibles/bible_<lang>_<id>.go)
editions into the app's downloadable per-book format and emits a manifest.

  python3 -I tools/bibles/convert_cbt.py --src <cbt checkout> --out <dir> [--editions en.ccb,...]

Output (never inside the APK assets):
  <out>/bibles-out/<edition>/<bookid>.json   {"abbreviation": <cbt code>, "chapters": [{"chapter": n, "verses": [{"verse": n, "text": s}]}]}
  <out>/manifest_cbt.json                    same schema as assets/bible/download_manifest.json (+ "cbtCommit")
  <out>/conversion_report.json               per-edition stats and anomalies
The converter fails loudly on unknown/missing codes, unparsable chapters or unparseable verse keys.
Source untidiness is normalised and reported (typo/padded keys, lettered sub-verses merged into their verse,
shuffled keys sorted, repeated keys relabelled or merged). Verse numbers are the edition's true numbers and may have
gaps (omitted verses); manifest verseCounts hold each chapter's LAST verse number.
"""
import argparse, hashlib, json, os, re, subprocess, sys

EDITIONS = {  # edition id -> (language, source file)
    "en.ccb": "en", "en.drbcr": "en", "es.eldpdd": "es", "es.lbl": "es", "es.lsb": "es", "pt.bs": "pt", "la.nvbse": "la",
}
# Verified table: cbt book code -> app book id (BibleData.catholicCanonBooks order).
CODE_TO_ID = {
    "VPGN": "gen", "VPEX": "exo", "VPLV": "lev", "VPNM": "num", "VPDT": "deu", "VHIE": "jos", "VHID": "jud", "VHRT": "rut",
    "VH1S": "1sa", "VH2S": "2sa", "VH1R": "1ki", "VH2R": "2ki", "VH1P": "1ch", "VH2P": "2ch", "VHED": "ezr", "VHNH": "neh",
    "VHTB": "tob", "VHIH": "jdt", "VHET": "est", "VH1M": "1ma", "VH2M": "2ma", "VSIB": "job", "VSPS": "psa", "VSPR": "pro",
    "VSEE": "ecc", "VSCC": "sng", "VSSP": "wis", "VSEI": "sir", "VMIS": "isa", "VMIE": "jer", "VMLM": "lam", "VMBR": "bar",
    "VMEZ": "eze", "VMDN": "dan", "VNOS": "hos", "VNIL": "joe", "VNAM": "amo", "VNAB": "oba", "VNIN": "jon", "VNMI": "mic",
    "VNNH": "nah", "VNHA": "hab", "VNSO": "zep", "VNAG": "hag", "VNZA": "zec", "VNML": "mal",
    "NEMT": "mat", "NEMC": "mar", "NELC": "luk", "NEIO": "joh", "NTAA": "act", "NPRM": "rom", "NP1C": "1co", "NP2C": "2co",
    "NPGA": "gal", "NPEP": "eph", "NPPP": "phi", "NPCL": "col", "NP1E": "1th", "NP2E": "2th", "NP1I": "1ti", "NP2I": "2ti",
    "NPTT": "tit", "NPPM": "phm", "NTHB": "heb", "NCIB": "jam", "NC1P": "1pe", "NC2P": "2pe", "NC1I": "1jo", "NC2I": "2jo",
    "NC3I": "3jo", "NCID": "jde", "NTAI": "rev",
}
CANON = list(CODE_TO_ID.values())
assert len(CANON) == 73 and len(set(CANON)) == 73
ID_TO_CODE = {v: k for k, v in CODE_TO_ID.items()}


def fail(msg):
    sys.exit("convert_cbt: " + msg)


def parse_go(text, edition):
    info = re.search(r'SetInfo\("([^"]+)",\s*"([^"]*)"\)', text)
    if not info or info.group(1) != edition:
        fail(f"{edition}: SetInfo id mismatch ({info.group(1) if info else None})")
    books = re.findall(r'AddBook\("(\w{4})",\s*"([^"]*)"\)', text)
    codes = [c for c, _ in books]
    unknown = [c for c in codes if c not in CODE_TO_ID]
    if unknown: fail(f"{edition}: unknown book codes {unknown}")
    if len(set(codes)) != len(codes): fail(f"{edition}: duplicate book codes")
    missing = [c for c in CODE_TO_ID if c not in codes]
    if missing: fail(f"{edition}: missing books {[CODE_TO_ID[c] for c in missing]}")
    # LoadBooks switch: code -> function; the function must name the same code.
    for code, fn in re.findall(r'case "(\w{4})":\s*\n\s*\w+_Load(\w{4})Chapters\(cb\)', text):
        if code != fn: fail(f"{edition}: switch maps {code} to {fn}")
    chapters = {}
    for m in re.finditer(r'func \w+?_Load(\w{4})Chapters\(CurrentBook \*Book\) \{(.*?)\n\}(?=\n|\Z)', text, re.S):
        code, body = m.group(1), m.group(2)
        out = []
        for n, raw in re.findall(r'AddChapter\((\d+),\s*`(.*?)`\)', body, re.S):
            try:
                obj = json.loads(raw, object_pairs_hook=lambda p: p)  # keep document order and duplicate keys
            except ValueError as e:
                fail(f"{edition} {code} ch {n}: bad JSON ({e})")
            if not isinstance(obj, list): fail(f"{edition} {code} ch {n}: chapter is not a JSON object")
            out.append((int(n), obj))
        if len(out) != len(re.findall(r'AddChapter\(', body)): fail(f"{edition} {code}: unparsed AddChapter")
        chapters[code] = out
    if set(chapters) != set(CODE_TO_ID): fail(f"{edition}: chapter functions differ from books: {set(CODE_TO_ID) ^ set(chapters)}")
    return dict(books), chapters


# Explicit, verified source repairs: (edition, book, chapter) -> action.
#  en.ccb John 5 carries a second run of keys 19-24 after verse 47; their text is John 7:19-24 (John 7 lacks them).
def apply_source_fixes(edition, chapters, notes):
    if edition == "en.drbcr":  # Psalms 115 and 147 keep Hebrew verse numbers (10-19, 12-20); Douay numbers them from 1
        for n, first in ((115, 10), (147, 12)):
            ch = dict(chapters["VSPS"])[n]
            if [int(k) for k, _ in ch][0] != first: fail(f"en.drbcr psalm {n} no longer starts at {first}")
            ch[:] = [(str(int(k) - first + 1), t) for k, t in ch]
            notes.append(f"en.drbcr psa {n}: verse keys renumbered from {first} to 1")
    if edition == "pt.bs":  # Esther 10:3a-3l: the last key reads "31" for "3l"
        ch = dict(chapters["VHET"])[10]
        if ch[-1][0] != "31" or ch[-2][0] != "3j": fail("pt.bs Esther 10 tail changed; review")
        ch[-1] = ("3l", ch[-1][1]); notes.append("pt.bs est 10: key '31' read as '3l'")
    if edition == "en.ccb":
        ch5 = dict(chapters["NEIO"])[5]
        tail = ch5[47:]
        if [k for k, _ in tail] == [str(i) for i in range(19, 25)] and tail[0][1].startswith("Moses gave you the Law"):
            ch5[47:] = []
            ch7 = dict(chapters["NEIO"])[7]
            have = {k for k, _ in ch7}
            if have & {k for k, _ in tail}: fail("en.ccb John 7 already has 19-24")
            ch7.extend(tail)
            notes.append("en.ccb joh 5: keys 19-24 after verse 47 moved to John 7:19-24")
        else:
            fail("en.ccb John 5 tail no longer matches the known defect; review the source")


def normalize(bid, n, pairs, notes):
    """Source chapters are untidy: padded/typo keys, lettered sub-verses, shuffled order, repeated keys.
    Returns [(verse number, text)] strictly increasing; gaps (verses a critical edition omits) are kept."""
    items = []
    for idx, (k, t) in enumerate(pairs):
        key = k.strip()
        if re.fullmatch(r"l\d+", key): key = "1" + key[1:]  # typo: letter l for digit 1 (en.ccb Judges 18 "l0")
        if key != k: notes.append(f"{bid} {n}: key {k!r} read as {key!r}")
        m = re.fullmatch(r"(\d+)([a-z]*)", key)
        if not m: fail(f"{bid} {n}: unparseable verse key {k!r}")
        items.append((int(m.group(1)), m.group(2), t, idx))
    base_nums = {v for v, s, _, _ in items if not s}
    pieces, seen = {}, set()  # verse number -> [(document index, text)]
    for v, suf, t, idx in items:
        if suf:
            notes.append(f"{bid} {n}: {v}{suf} merged into {v}")
        elif v in seen:
            if v + 1 not in base_nums and v + 1 not in seen:
                notes.append(f"{bid} {n}: repeated key {v} relabelled {v+1}"); v += 1
            else:
                notes.append(f"{bid} {n}: repeated key {v} merged into {v}")
        if not suf: seen.add(v)
        pieces.setdefault(v, []).append((idx, t))
    verses = {v: " ".join(t for _, t in sorted(ps)) for v, ps in pieces.items()}
    if any(not x.strip() for x in verses.values()): notes.append(f"{bid} {n}: empty verse text")
    return sorted(verses.items())


def convert(edition, text):
    names, chapters = parse_go(text, edition)
    books, anomalies, total = {}, [], 0
    apply_source_fixes(edition, chapters, anomalies)
    for code, chs in chapters.items():
        bid = CODE_TO_ID[code]
        nums = [n for n, _ in chs]
        if nums != list(range(1, len(nums) + 1)): fail(f"{edition} {bid}: chapter numbers {nums[:5]}... not 1..n")
        out = []
        for n, pairs in chs:
            if not all(isinstance(t, str) for _, t in pairs): fail(f"{edition} {bid} {n}: non-string verse")
            norm = normalize(bid, n, pairs, anomalies)
            if not norm: fail(f"{edition} {bid} {n}: empty chapter")
            if any(not t.strip() for _, t in norm): fail(f"{edition} {bid} {n}: empty verse text")
            verses = [{"verse": v, "text": t} for v, t in norm]
            gaps = sorted(set(range(1, verses[-1]["verse"] + 1)) - {v["verse"] for v in verses})
            if gaps: anomalies.append(f"{bid} {n}: omitted verse numbers {gaps}")
            out.append({"chapter": n, "verses": verses}); total += len(verses)
        books[bid] = {"abbreviation": code, "chapters": out}
    return books, names, total, anomalies


def commit_of(src):
    try:
        return subprocess.check_output(["git", "-C", src, "rev-parse", "HEAD"], text=True).strip()
    except Exception:
        return "unknown"


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--src", required=True); ap.add_argument("--out", required=True)
    ap.add_argument("--editions", default=",".join(EDITIONS))
    a = ap.parse_args()
    manifest, report = {}, {}
    for ed in a.editions.split(","):
        if ed not in EDITIONS: fail(f"unknown edition {ed}")
        path = os.path.join(a.src, "src/bibles", "bible_" + ed.replace(".", "_") + ".go")
        books, names, total, anomalies = convert(ed, open(path, encoding="utf-8").read())
        d = os.path.join(a.out, "bibles-out", ed); os.makedirs(d, exist_ok=True)
        entries = []
        for n, bid in enumerate(CANON, 1):
            data = json.dumps(books[bid], ensure_ascii=False, separators=(",", ":")).encode("utf-8")
            fname = f"{n:02d}-{bid}.json"
            open(os.path.join(d, fname), "wb").write(data)
            entries.append({"id": bid, "file": fname, "abbreviation": books[bid]["abbreviation"],
                            "sha256": hashlib.sha256(data).hexdigest(), "bytes": len(data),
                            "verseCounts": [c["verses"][-1]["verse"] for c in books[bid]["chapters"]]})
        manifest[ed] = entries
        report[ed] = {"books": len(books), "verses": total, "anomalies": anomalies,
                      "bytes": sum(e["bytes"] for e in entries), "verseCounts": {e["id"]: e["verseCounts"] for e in entries}}
        print(ed, "books", len(books), "verses", total, "bytes", report[ed]["bytes"], "anomalies", len(anomalies))
    json.dump({"cbtCommit": commit_of(a.src), "editions": manifest}, open(os.path.join(a.out, "manifest_cbt.json"), "w"), separators=(",", ":"))
    json.dump(report, open(os.path.join(a.out, "conversion_report.json"), "w"), ensure_ascii=False)


if __name__ == "__main__":
    main()
