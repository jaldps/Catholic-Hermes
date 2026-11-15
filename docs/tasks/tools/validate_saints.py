#!/usr/bin/env python3
"""Validator for M5 saints tasks.

Usage (repository root):
  python3 docs/tasks/tools/validate_saints.py app/src/main/assets/saints/saints-q1.json docs/tasks/T-011.keys [--partial]
"""
import json, re, sys, pathlib

LANGS = ("es", "en", "pt")
KEYS = ["key", "month", "day", "bio", "patronages", "quote", "sources"]
BANNED = [r"https?://", r"\bTODO\b", r"\blorem\b", r"as an AI", r"modelo de lenguaje"]
ROOT = pathlib.Path(__file__).resolve().parents[3]
errors, warns = [], []

def words(s): return len(re.findall(r"\w+", s))

def sanctoral():
    out = {}
    for line in (ROOT / "app/src/main/java/com/example/data/liturgy/GeneralRomanSanctoral.kt").read_text(encoding="utf-8").splitlines():
        m = re.search(r'GeneralCelebration\((\d+), (\d+), "([^"]+)"', line)
        if m and "data class" not in line: out[m.group(3)] = (int(m.group(1)), int(m.group(2)))
    return out

def main():
    partial = "--partial" in sys.argv
    if partial: sys.argv.remove("--partial")
    if len(sys.argv) != 3: print(__doc__); sys.exit(2)
    data = json.loads((ROOT / sys.argv[1]).read_text(encoding="utf-8"))
    want = [l.strip() for l in (ROOT / sys.argv[2]).read_text().splitlines() if l.strip()]
    got = [e.get("key") for e in data]
    if partial and got and got == want[:len(got)]: warns.append(f"partial delivery: {len(got)}/{len(want)}")
    elif got != want: errors.append(f"keys must equal {sys.argv[2]} in order; missing={sorted(set(want)-set(got))[:10]} extra={sorted(set(got)-set(want))[:10]}")
    cal = sanctoral()
    for e in data:
        k = e.get("key")
        if list(e.keys()) != KEYS: errors.append(f"{k}: keys must be exactly {KEYS} in that order")
        if k in cal and (e.get("month"), e.get("day")) != cal[k]: errors.append(f"{k}: month/day must be {cal[k]}")
        bio = e.get("bio") or {}
        for l in LANGS:
            t = bio.get(l, "") if isinstance(bio, dict) else ""
            if not t.strip(): errors.append(f"{k}.bio.{l}: empty")
            elif not 100 <= words(t) <= 240: warns.append(f"{k}.bio.{l}: {words(t)} words (target 120-200)")
            for b in BANNED:
                if re.search(b, t, re.I): errors.append(f"{k}.bio.{l}: banned pattern /{b}/")
        if isinstance(bio, dict) and len({bio.get(l, '').strip() for l in LANGS}) < 3: warns.append(f"{k}.bio: identical text in two languages")
        p = e.get("patronages")
        if not isinstance(p, dict) or set(p) != set(LANGS) or not all(isinstance(p[l], list) for l in LANGS): errors.append(f"{k}.patronages: object with es/en/pt lists")
        elif len({len(p[l]) for l in LANGS}) > 1: errors.append(f"{k}.patronages: different number of items per language")
        q = e.get("quote")
        if q is not None:
            if not isinstance(q, dict) or set(q) != {"text", "source"}: errors.append(f"{k}.quote: null or {{text, source}}")
            else:
                if not all(q["text"].get(l, "").strip() for l in LANGS): errors.append(f"{k}.quote.text: es/en/pt required")
                if not re.search(r"\d|[IVXL]+", str(q.get("source", ""))): errors.append(f"{k}.quote.source: needs work and exact place (book/chapter/number)")
        s = e.get("sources")
        if not isinstance(s, list) or not 2 <= len(s) <= 5: errors.append(f"{k}.sources: 2-5 strings")
    for w in warns: print("WARN ", w)
    for x in errors: print("ERROR", x)
    print(f"{len(data)} entries · {len(errors)} errors · {len(warns)} warnings")
    sys.exit(1 if errors else 0)

if __name__ == "__main__":
    main()
