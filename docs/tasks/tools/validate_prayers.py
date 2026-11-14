#!/usr/bin/env python3
"""Validator for the M3 prayer tasks (T-001, T-002, T-003).

Usage (from the repository root):
  python3 docs/tasks/tools/validate_prayers.py details app/src/main/assets/prayers/details-a.json docs/tasks/T-001.ids
  python3 docs/tasks/tools/validate_prayers.py details app/src/main/assets/prayers/details-b.json docs/tasks/T-002.ids
  python3 docs/tasks/tools/validate_prayers.py extra   app/src/main/assets/prayers/extra.json

Exit code 0 = valid. Fix every ERROR before pushing; WARN lines need a look.
"""
import json, re, sys, pathlib

LANGS = ("es", "en", "pt")
KINDS = {"SOURCED", "ECCLESIAL_GRANT", "TRADITIONAL", "NONE"}
CATEGORIES = {"BASIC", "MORNING_NIGHT", "MARIAN", "EUCHARISTIC", "HOLY_SPIRIT", "PROTECTION", "INTERCESSION", "ANGELUS"}
DETAIL_KEYS = ["history", "instructions", "source", "approval", "promise", "textNote"]
EXTRA_KEYS = ["id", "title", "latinTitle", "category", "leaderText", "responseText", "whenToPray", "indulgenceNote", "thematicTags"]
BANNED = [r"nunca falla", r"never fails", r"nunca falha", r"publ[ií]cala", r"publish (this|it)", r"comp[aá]rtela", r"share (this|it) with",
          r"garantizad", r"guaranteed", r"garantid", r"https?://", r"conc\.\s*\d+\s*§"]
ROOT = pathlib.Path(__file__).resolve().parents[3]
errors, warns = [], []

def err(m): errors.append(m)
def warn(m): warns.append(m)

def words(s): return len(re.findall(r"\w+", s))

def lt(obj, where, required=True, lo=None, hi=None):
    if not isinstance(obj, dict) or set(obj.keys()) != set(LANGS):
        err(f"{where}: must be an object with exactly es/en/pt"); return
    for l in LANGS:
        v = obj[l]
        if not isinstance(v, str): err(f"{where}.{l}: not a string"); continue
        if required and not v.strip(): err(f"{where}.{l}: empty")
        if v and lo and words(v) < lo: warn(f"{where}.{l}: {words(v)} words (< {lo})")
        if v and hi and words(v) > hi: warn(f"{where}.{l}: {words(v)} words (> {hi})")
        for b in BANNED:
            if re.search(b, v, re.I): err(f"{where}.{l}: banned pattern /{b}/")
    filled = [l for l in LANGS if obj[l].strip()]
    if 0 < len(filled) < 3: err(f"{where}: some languages filled, others empty ({filled})")
    if len(filled) == 3 and len({obj[l].strip() for l in LANGS}) < 3: warn(f"{where}: identical text in two languages (untranslated?)")

def details(e, where):
    for k in DETAIL_KEYS:
        if k not in e: err(f"{where}: missing key {k}")
    lt(e.get("history"), f"{where}.history", lo=80, hi=190)
    lt(e.get("instructions"), f"{where}.instructions", lo=35, hi=130)
    lt(e.get("source"), f"{where}.source")
    lt(e.get("approval"), f"{where}.approval", required=False)
    lt(e.get("textNote"), f"{where}.textNote", required=False)
    p = e.get("promise")
    if p is not None:
        if not isinstance(p, dict): err(f"{where}.promise: null or object"); return
        if p.get("kind") not in KINDS: err(f"{where}.promise.kind invalid: {p.get('kind')}")
        for k in ("text", "attribution", "source", "conditions"):
            lt(p.get(k), f"{where}.promise.{k}", required=False)
        if p.get("kind") == "NONE":
            err(f"{where}.promise: use null instead of kind NONE")
        if p.get("kind") in ("SOURCED", "ECCLESIAL_GRANT"):
            for k in ("text", "attribution", "source"):
                lt(p.get(k), f"{where}.promise.{k}", required=True)

def existing_ids():
    src = (ROOT / "app/src/main/java/com/example/data/repository/PrayerAndNovenaData.kt").read_text(encoding="utf-8")
    return re.findall(r'id = "([^"]+)"', src)

def main():
    if len(sys.argv) < 3: print(__doc__); sys.exit(2)
    mode, path = sys.argv[1], ROOT / sys.argv[2]
    data = json.loads(path.read_text(encoding="utf-8"))
    if not isinstance(data, list): err("top level must be a JSON array")
    if mode == "details":
        want = [l.strip() for l in (ROOT / sys.argv[3]).read_text().splitlines() if l.strip()]
        got = [e.get("prayerId") for e in data]
        if got != want: err(f"prayerId list must equal {sys.argv[3]} in the same order.\n  missing={sorted(set(want)-set(got))}\n  extra={sorted(set(got)-set(want))}")
        for e in data:
            if list(e.keys()) != ["prayerId"] + DETAIL_KEYS: err(f"{e.get('prayerId')}: keys must be exactly {['prayerId']+DETAIL_KEYS} in that order")
            details(e, e.get("prayerId"))
    elif mode == "extra":
        ids = set(existing_ids()); seen = set()
        if not 20 <= len(data) <= 40: warn(f"{len(data)} new prayers (expected ~30)")
        for e in data:
            pid = e.get("id", "?")
            if list(e.keys()) != EXTRA_KEYS + DETAIL_KEYS: err(f"{pid}: keys must be exactly {EXTRA_KEYS + DETAIL_KEYS} in that order")
            if not re.fullmatch(r"[a-z][a-z0-9_]+", pid): err(f"{pid}: id must be snake_case")
            if pid in ids: err(f"{pid}: collides with an existing prayer id")
            if pid in seen: err(f"{pid}: duplicated")
            seen.add(pid)
            lt(e.get("title"), f"{pid}.title")
            lt(e.get("leaderText"), f"{pid}.leaderText", lo=15)
            if e.get("responseText") is not None: lt(e["responseText"], f"{pid}.responseText")
            lt(e.get("whenToPray"), f"{pid}.whenToPray")
            if e.get("indulgenceNote") is not None: lt(e["indulgenceNote"], f"{pid}.indulgenceNote")
            if e.get("category") not in CATEGORIES: err(f"{pid}: category invalid")
            if not isinstance(e.get("thematicTags"), list): err(f"{pid}: thematicTags must be a list")
            details(e, pid)
    else:
        print(__doc__); sys.exit(2)
    for w in warns: print("WARN ", w)
    for x in errors: print("ERROR", x)
    print(f"{len(data)} entries · {len(errors)} errors · {len(warns)} warnings")
    sys.exit(1 if errors else 0)

if __name__ == "__main__":
    main()
