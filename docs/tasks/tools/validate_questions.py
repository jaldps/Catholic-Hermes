#!/usr/bin/env python3
"""Validator for M4 tasks (T-005: questions 1-51, T-006: 52-101).

Usage (repository root):
  python3 docs/tasks/tools/validate_questions.py app/src/main/assets/questions/deeper-a.json 1 51
  python3 docs/tasks/tools/validate_questions.py app/src/main/assets/questions/deeper-b.json 52 101
"""
import json, re, sys, pathlib

LANGS = ("es", "en", "pt")
FIELDS = {"explanation": (90, 160), "scripture": (50, 110), "tradition": (40, 100), "magisterium": (40, 100),
          "objection": (60, 120), "analogy": (25, 70), "synthesis": (20, 50)}
KEYS = ["number"] + list(FIELDS) + ["sources"]
CCC = {"es": r"\bCIC\s?\d", "en": r"\bCCC\s?\d", "pt": r"\bCIC\s?\d"}
BANNED = [r"https?://", r"\bcomo modelo de lenguaje\b", r"\bas an AI\b", r"\bTODO\b", r"\blorem\b"]
ROOT = pathlib.Path(__file__).resolve().parents[3]
errors, warns = [], []

def words(s): return len(re.findall(r"\w+", s))

def main():
    if len(sys.argv) != 4: print(__doc__); sys.exit(2)
    path, lo, hi = ROOT / sys.argv[1], int(sys.argv[2]), int(sys.argv[3])
    data = json.loads(path.read_text(encoding="utf-8"))
    nums = [e.get("number") for e in data]
    if nums != list(range(lo, hi + 1)):
        errors.append(f"numbers must be exactly {lo}..{hi} in order; missing={sorted(set(range(lo,hi+1))-set(nums))} extra={sorted(set(nums)-set(range(lo,hi+1)))}")
    for e in data:
        n = e.get("number")
        if list(e.keys()) != KEYS: errors.append(f"q{n}: keys must be exactly {KEYS} in that order")
        totals = {l: 0 for l in LANGS}
        for f, (a, b) in FIELDS.items():
            v = e.get(f)
            if not isinstance(v, dict) or set(v) != set(LANGS): errors.append(f"q{n}.{f}: must have exactly es/en/pt"); continue
            for l in LANGS:
                t = v[l] if isinstance(v[l], str) else ""
                w = words(t); totals[l] += w
                if not t.strip(): errors.append(f"q{n}.{f}.{l}: empty")
                elif w < a * 0.8 or w > b * 1.25: warns.append(f"q{n}.{f}.{l}: {w} words (target {a}-{b})")
                for p in BANNED:
                    if re.search(p, t, re.I): errors.append(f"q{n}.{f}.{l}: banned pattern /{p}/")
            if len({v[l].strip() for l in LANGS if isinstance(v[l], str)}) < 3: warns.append(f"q{n}.{f}: identical text in two languages")
        for l in LANGS:
            if not 350 <= totals[l] <= 650: warns.append(f"q{n}.{l}: total {totals[l]} words (target 350-650)")
            if not re.search(CCC[l], e.get("magisterium", {}).get(l, "")): errors.append(f"q{n}.magisterium.{l}: no Catechism reference in '{CCC[l]}' format")
        s = e.get("sources")
        if not isinstance(s, list) or not 3 <= len(s) <= 8: errors.append(f"q{n}.sources: 3-8 strings")
    for w in warns: print("WARN ", w)
    for x in errors: print("ERROR", x)
    print(f"{len(data)} entries · {len(errors)} errors · {len(warns)} warnings")
    sys.exit(1 if errors else 0)

if __name__ == "__main__":
    main()
