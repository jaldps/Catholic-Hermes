#!/usr/bin/env python3
"""Validator for M6 (orders and offices).

Usage (repository root):
  python3 docs/tasks/tools/validate_orders.py details app/src/main/assets/orders/orders-details.json
  python3 docs/tasks/tools/validate_orders.py new     app/src/main/assets/orders/orders-new.json
  python3 docs/tasks/tools/validate_orders.py offices app/src/main/assets/orders/offices-new.json
Exit code 0 = valid.
"""
import json, re, sys, pathlib

LANGS = ("es", "en", "pt")
KINDS = {"order", "congregation", "society", "secular_institute", "prelature", "monastic_federation"}
GROUPS = {"altar", "clergy", "consecrated", "mission", "governance"}
BANNED = [r"https?://", r"\bTODO\b", r"lorem", r"as an AI", r"modelo de lenguaje"]
ROOT = pathlib.Path(__file__).resolve().parents[3]
errors, warns = [], []

def words(s): return len(re.findall(r"\w+", s))

def lt(obj, where, required=True, lo=None, hi=None, minchars=None):
    if not isinstance(obj, dict) or set(obj) != set(LANGS):
        errors.append(f"{where}: object with exactly es/en/pt"); return
    filled = [l for l in LANGS if isinstance(obj[l], str) and obj[l].strip()]
    if required and len(filled) < 3: errors.append(f"{where}: es/en/pt required")
    if 0 < len(filled) < 3: errors.append(f"{where}: some languages empty")
    for l in filled:
        v = obj[l]
        if lo and words(v) < lo: warns.append(f"{where}.{l}: {words(v)} words (< {lo})")
        if hi and words(v) > hi: warns.append(f"{where}.{l}: {words(v)} words (> {hi})")
        if minchars and len(v) <= minchars: errors.append(f"{where}.{l}: must exceed {minchars} characters")
        for b in BANNED:
            if re.search(b, v, re.I): errors.append(f"{where}.{l}: banned pattern /{b}/")
    if len(filled) == 3 and len({obj[l].strip() for l in LANGS}) < 3 and where.split(".")[-1] not in ("motto",):
        warns.append(f"{where}: identical text in two languages")

def sources(s, where):
    if not isinstance(s, list) or not 2 <= len(s) <= 5 or not all(isinstance(x, str) and x.strip() for x in s):
        errors.append(f"{where}.sources: 2-5 non-empty strings")
    elif any(re.search(r"https?://", x) for x in s):
        errors.append(f"{where}.sources: no URLs (put them in the REPORT)")

def existing():
    src = (ROOT / "app/src/main/java/com/example/data/repository/ReligiousOrdersData.kt").read_text(encoding="utf-8")
    ids = re.findall(r'id = "([^"]+)"', src)
    return [i for i in ids if not i.startswith("office_")], [i for i in ids if i.startswith("office_")]

def load_new_ids():
    p = ROOT / "app/src/main/assets/orders/orders-new.json"
    return [e.get("id") for e in json.loads(p.read_text(encoding="utf-8"))] if p.exists() else []

def main():
    if len(sys.argv) != 3: print(__doc__); sys.exit(2)
    mode, path = sys.argv[1], ROOT / sys.argv[2]
    data = json.loads(path.read_text(encoding="utf-8"))
    if not isinstance(data, list): print("ERROR top level must be an array"); sys.exit(1)
    orders, offices = existing()
    ids = [e.get("id") for e in data]
    dup = {i for i in ids if ids.count(i) > 1}
    if dup: errors.append(f"duplicated ids: {sorted(dup)}")
    if mode == "details":
        want = orders + load_new_ids()
        if ids != want[:len(ids)]: errors.append("ids must follow ReligiousOrdersData.kt order, then orders-new.json order")
        if len(ids) < len(want): warns.append(f"partial: {len(ids)}/{len(want)} orders")
        keys = ["id", "kind", "motto", "approval", "notableSaints", "presence", "sources"]
        for e in data:
            k = e.get("id")
            if list(e) != keys: errors.append(f"{k}: keys must be exactly {keys}")
            if e.get("kind") not in KINDS: errors.append(f"{k}.kind invalid: {e.get('kind')}")
            if e.get("motto") is not None: lt(e["motto"], f"{k}.motto")
            lt(e.get("approval"), f"{k}.approval", required=False)
            lt(e.get("presence"), f"{k}.presence", lo=10, hi=80)
            ns = e.get("notableSaints")
            if not isinstance(ns, dict) or set(ns) != set(LANGS) or not all(isinstance(ns[l], list) for l in LANGS):
                errors.append(f"{k}.notableSaints: object of es/en/pt lists")
            elif len({len(ns[l]) for l in LANGS}) > 1 or len(ns["es"]) > 5:
                errors.append(f"{k}.notableSaints: same count per language, max 5")
            sources(e.get("sources"), k)
    elif mode == "new":
        keys = ["id", "name", "abbreviation", "foundationYear", "founder", "description", "charism", "gender"]
        for e in data:
            k = e.get("id", "?")
            if list(e) != keys: errors.append(f"{k}: keys must be exactly {keys}")
            if not re.fullmatch(r"[a-z0-9]+(-[a-z0-9]+)*", k): errors.append(f"{k}: id must be kebab-case")
            if k in orders: errors.append(f"{k}: collides with an existing order")
            lt(e.get("name"), f"{k}.name"); lt(e.get("founder"), f"{k}.founder")
            lt(e.get("description"), f"{k}.description", lo=60, hi=120, minchars=150)
            lt(e.get("charism"), f"{k}.charism")
            if not str(e.get("abbreviation", "")).strip(): errors.append(f"{k}.abbreviation empty")
            if not re.search(r"\d{3,4}", str(e.get("foundationYear", ""))): errors.append(f"{k}.foundationYear needs a year")
            if e.get("gender") not in ("men", "women", "mixed"): errors.append(f"{k}.gender invalid")
    elif mode == "offices":
        keys = ["id", "name", "description", "group", "after", "sources"]
        known = set(offices) | set(ids)
        for e in data:
            k = e.get("id", "?")
            if list(e) != keys: errors.append(f"{k}: keys must be exactly {keys}")
            if not re.fullmatch(r"office_[a-z0-9_]+", k): errors.append(f"{k}: id must be office_snake_case")
            if k in offices: errors.append(f"{k}: collides with an existing office")
            lt(e.get("name"), f"{k}.name")
            lt(e.get("description"), f"{k}.description", lo=60, hi=120, minchars=150)
            if e.get("group") not in GROUPS: errors.append(f"{k}.group invalid")
            if e.get("after") is not None and e.get("after") not in known: errors.append(f"{k}.after unknown id {e.get('after')}")
            sources(e.get("sources"), k)
    else:
        print(__doc__); sys.exit(2)
    for w in warns: print("WARN ", w)
    for x in errors: print("ERROR", x)
    print(f"{len(data)} entries · {len(errors)} errors · {len(warns)} warnings")
    sys.exit(1 if errors else 0)

if __name__ == "__main__":
    main()
