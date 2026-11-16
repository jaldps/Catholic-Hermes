#!/usr/bin/env python3
"""Validator for M7 (devotions to Jesus and Mary).

Usage (repository root):
  python3 docs/tasks/tools/validate_devotions.py details marian app/src/main/assets/devotions/marian-details.json
  python3 docs/tasks/tools/validate_devotions.py new     marian app/src/main/assets/devotions/marian-new.json
  python3 docs/tasks/tools/validate_devotions.py details christ app/src/main/assets/devotions/christ-details.json
  python3 docs/tasks/tools/validate_devotions.py new     christ app/src/main/assets/devotions/christ-new.json
Add --partial to accept an ordered prefix in details mode. Exit code 0 = valid.
"""
import json, re, sys, pathlib

LANGS = ("es", "en", "pt")
REGIONS = {"Americas", "Europe", "Asia_Africa", "Universal"}
BANNED = [r"https?://", r"\bTODO\b", r"lorem", r"nunca falla", r"never fails", r"nunca falha",
          r"incontables milagros", r"countless miracles", r"incont[aá]veis milagres", r"publ[ií]cala", r"garantizad"]
ROOT = pathlib.Path(__file__).resolve().parents[3]
errors, warns = [], []

def words(s): return len(re.findall(r"\w+", s))

def lt(obj, where, required=True, lo=None, hi=None):
    if not isinstance(obj, dict) or set(obj) != set(LANGS):
        errors.append(f"{where}: object with exactly es/en/pt"); return
    filled = [l for l in LANGS if isinstance(obj[l], str) and obj[l].strip()]
    if required and len(filled) < 3: errors.append(f"{where}: es/en/pt required")
    if 0 < len(filled) < 3: errors.append(f"{where}: some languages empty")
    for l in filled:
        v = obj[l]
        if lo and words(v) < lo: warns.append(f"{where}.{l}: {words(v)} words (< {lo})")
        if hi and words(v) > hi: warns.append(f"{where}.{l}: {words(v)} words (> {hi})")
        for b in BANNED:
            if re.search(b, v, re.I): errors.append(f"{where}.{l}: banned pattern /{b}/")

def sources(s, where):
    if not isinstance(s, list) or not 2 <= len(s) <= 5 or not all(isinstance(x, str) and x.strip() for x in s):
        errors.append(f"{where}.sources: 2-5 non-empty strings")
    elif any(re.search(r"https?://", x) for x in s):
        errors.append(f"{where}.sources: no URLs (put them in the REPORT)")

def sourced(obj, where, kind):
    if obj is None: return
    if not isinstance(obj, dict): errors.append(f"{where}: null or object"); return
    if obj.get("remove") is True:
        if set(obj) != {"remove"}: errors.append(f"{where}: remove object must contain only remove")
        return
    if kind == "miracles":
        if set(obj) != {"text"}: errors.append(f"{where}: keys must be text")
        lt(obj.get("text"), f"{where}.text"); return
    want = {"text", "attribution", "source"} if kind == "promises" else {"text", "source"}
    if set(obj) != want: errors.append(f"{where}: keys must be {sorted(want)}")
    lt(obj.get("text"), f"{where}.text")
    if kind == "promises": lt(obj.get("attribution"), f"{where}.attribution")
    if not str(obj.get("source", "")).strip(): errors.append(f"{where}.source required")

def existing(kind):
    src = (ROOT / "app/src/main/java/com/example/data/repository/DevotionsAndHistoryData.kt").read_text(encoding="utf-8")
    cls = "MarianApparition(" if kind == "marian" else "ChristManifestation("
    return re.findall(re.escape(cls) + r'\s*id\s*=\s*"([^"]+)"', src)

def main():
    partial = "--partial" in sys.argv
    if partial: sys.argv.remove("--partial")
    if len(sys.argv) != 4 or sys.argv[1] not in ("details", "new") or sys.argv[2] not in ("marian", "christ"):
        print(__doc__); sys.exit(2)
    mode, kind, path = sys.argv[1], sys.argv[2], ROOT / sys.argv[3]
    data = json.loads(path.read_text(encoding="utf-8"))
    if not isinstance(data, list): print("ERROR top level must be an array"); sys.exit(1)
    have = existing(kind)
    ids = [e.get("id") for e in data]
    dup = {i for i in ids if ids.count(i) > 1}
    if dup: errors.append(f"duplicated ids: {sorted(dup)}")
    if mode == "details":
        if partial and ids and ids == have[:len(ids)]: warns.append(f"partial delivery: {len(ids)}/{len(have)}")
        elif ids != have: errors.append(f"ids must equal the {kind} ids of DevotionsAndHistoryData.kt in order")
        keys = ["id", "recognition", "promises", "miracles", "prayer", "practice", "sources"]
        for e in data:
            k = e.get("id")
            if list(e) != keys: errors.append(f"{k}: keys must be exactly {keys}")
            lt(e.get("recognition"), f"{k}.recognition", lo=8, hi=90)
            sourced(e.get("promises"), f"{k}.promises", "promises")
            sourced(e.get("miracles"), f"{k}.miracles", "miracles")
            sourced(e.get("prayer"), f"{k}.prayer", "prayer")
            lt(e.get("practice"), f"{k}.practice", lo=8, hi=90)
            sources(e.get("sources"), k)
    else:
        if kind == "marian":
            keys = ["id", "title", "feastDay", "year", "location", "primarySanctuary", "history", "promises", "miracles",
                    "prayer", "region", "country", "patronageOf", "recognition", "practice", "sources"]
        else:
            keys = ["id", "title", "feastDay", "year", "primarySanctuary", "history", "theologicalMeaning", "promises",
                    "miracles", "prayer", "region", "country", "recognition", "practice", "sources"]
        for e in data:
            k = e.get("id", "?")
            if list(e) != keys: errors.append(f"{k}: keys must be exactly {keys}")
            if not re.fullmatch(r"[a-z0-9]+(_[a-z0-9]+)*", k): errors.append(f"{k}: id must be snake_case")
            if k in have: errors.append(f"{k}: collides with an existing devotion")
            for f in ("title", "feastDay", "primarySanctuary", "country", "recognition", "practice"):
                lt(e.get(f), f"{k}.{f}")
            if kind == "marian":
                lt(e.get("location"), f"{k}.location"); lt(e.get("patronageOf"), f"{k}.patronageOf")
                if not re.search(r"\d{3,4}", str(e.get("year", ""))): errors.append(f"{k}.year needs a year")
            else:
                lt(e.get("theologicalMeaning"), f"{k}.theologicalMeaning", lo=40, hi=120)
            lt(e.get("history"), f"{k}.history", lo=100, hi=220)
            lt(e.get("miracles"), f"{k}.miracles")
            sourced(e.get("promises"), f"{k}.promises", "promises")
            sourced(e.get("prayer"), f"{k}.prayer", "prayer")
            if e.get("region") not in REGIONS: errors.append(f"{k}.region invalid")
            sources(e.get("sources"), k)
    for w in warns: print("WARN ", w)
    for x in errors: print("ERROR", x)
    print(f"{len(data)} entries · {len(errors)} errors · {len(warns)} warnings")
    sys.exit(1 if errors else 0)

if __name__ == "__main__":
    main()
