#!/usr/bin/env python3
"""Validator for M8 course files (assets/courses/<id>.json).

Usage (repository root):
  python3 docs/tasks/tools/validate_courses.py app/src/main/assets/courses/track_jesus_paschal.json
Exit code 0 = valid.
"""
import json, re, sys, pathlib

LANGS = ("es", "en", "pt")
ROOT = pathlib.Path(__file__).resolve().parents[3]
LEVELS = {"FOUNDATION", "INTERMEDIATE"}
BANNED = [r"\bTODO\b", r"lorem", r"as an AI", r"modelo de lenguaje", r"imprimatur", r"aprobad[oa] por (el Vaticano|la Iglesia)"]
errors, warns = [], []

def words(s): return len(re.findall(r"\w+", s))

def lt(obj, where, lo=None, hi=None):
    if not isinstance(obj, dict) or set(obj) != set(LANGS):
        errors.append(f"{where}: object with exactly es/en/pt"); return
    for l in LANGS:
        v = obj[l]
        if not isinstance(v, str) or not v.strip(): errors.append(f"{where}.{l}: empty"); continue
        if lo and words(v) < lo: warns.append(f"{where}.{l}: {words(v)} words (< {lo})")
        if hi and words(v) > hi: warns.append(f"{where}.{l}: {words(v)} words (> {hi})")
        for b in BANNED:
            if re.search(b, v, re.I): errors.append(f"{where}.{l}: banned pattern /{b}/")
    if len({obj[l].strip() for l in LANGS if isinstance(obj[l], str)}) < 3:
        warns.append(f"{where}: identical text in two languages")

def books():
    src = (ROOT / "app/src/main/java/com/example/data/repository/BibleData.kt").read_text(encoding="utf-8")
    return set(re.findall(r'BibleBook\("([a-z0-9]+)"', src))

REF = re.compile(r"^([a-z0-9]{2,4}) (\d{1,3})(:\d{1,3}(-\d{1,3}(:\d{1,3})?)?)?(, ?\d{1,3}(:\d{1,3}(-\d{1,3})?)?)*$")

def main():
    if len(sys.argv) != 2: print(__doc__); sys.exit(2)
    path = ROOT / sys.argv[1]
    d = json.loads(path.read_text(encoding="utf-8"))
    known = books()
    keys = ["id", "level", "estimatedMinutes", "iconName", "title", "description", "objectives", "lessons"]
    if list(d) != keys: errors.append(f"course keys must be exactly {keys}")
    cid = d.get("id", "?")
    if path.stem != cid: errors.append(f"file name must be {cid}.json")
    if not re.fullmatch(r"track_[a-z0-9_]+", cid): errors.append("course id must be track_snake_case")
    if d.get("level") not in LEVELS: errors.append("level must be FOUNDATION or INTERMEDIATE")
    lt(d.get("title"), "title"); lt(d.get("description"), "description", lo=20, hi=90)
    obj = d.get("objectives", [])
    if not isinstance(obj, list) or len(obj) != 3: errors.append("course objectives: exactly 3")
    for i, o in enumerate(obj or []): lt(o, f"objectives[{i}]")
    lessons = d.get("lessons", [])
    if len(lessons) != 5: errors.append(f"lessons: exactly 5 (got {len(lessons)})")
    lkeys = ["id", "lessonNumber", "title", "scriptureReferences", "estimatedMinutes", "objectives", "summary", "historicalContext",
             "teachingSections", "lectio", "meditatio", "oratio", "contemplatio", "actionStep", "reviewQuestions", "sourceReferences"]
    seen = set()
    for n, L in enumerate(lessons, 1):
        k = L.get("id", f"#{n}")
        if list(L) != lkeys: errors.append(f"{k}: keys must be exactly {lkeys}")
        if k in seen: errors.append(f"{k}: duplicated id")
        seen.add(k)
        if not re.fullmatch(r"[a-z0-9]+(_[a-z0-9]+)+", k): errors.append(f"{k}: id must be snake_case")
        if L.get("lessonNumber") != n: errors.append(f"{k}: lessonNumber must be {n}")
        lt(L.get("title"), f"{k}.title")
        refs = L.get("scriptureReferences") or []
        if not refs: errors.append(f"{k}.scriptureReferences: at least one")
        for r in refs:
            m = REF.match(str(r))
            if not m: errors.append(f"{k}: bad reference format '{r}' (e.g. 'luk 24:13-35')")
            elif m.group(1) not in known: errors.append(f"{k}: unknown book id '{m.group(1)}'")
        lo = L.get("objectives", [])
        if not isinstance(lo, list) or not 2 <= len(lo) <= 3: errors.append(f"{k}.objectives: 2-3")
        for i, o in enumerate(lo or []): lt(o, f"{k}.objectives[{i}]")
        lt(L.get("summary"), f"{k}.summary", hi=60)
        lt(L.get("historicalContext"), f"{k}.historicalContext", lo=60, hi=125)
        ts = L.get("teachingSections", [])
        if not isinstance(ts, list) or len(ts) != 3: errors.append(f"{k}.teachingSections: exactly 3")
        for i, s in enumerate(ts or []):
            lt(s.get("title"), f"{k}.teaching[{i}].title"); lt(s.get("body"), f"{k}.teaching[{i}].body", lo=70, hi=150)
        for f in ("lectio", "meditatio", "oratio", "contemplatio", "actionStep"):
            lt(L.get(f), f"{k}.{f}", lo=12, hi=120)
        rq = L.get("reviewQuestions", [])
        if not isinstance(rq, list) or len(rq) != 3: errors.append(f"{k}.reviewQuestions: exactly 3")
        for i, q in enumerate(rq or []):
            lt(q.get("prompt"), f"{k}.q[{i}].prompt"); lt(q.get("answer"), f"{k}.q[{i}].answer", lo=25, hi=150)
        sr = L.get("sourceReferences", [])
        if not isinstance(sr, list) or not 2 <= len(sr) <= 6: errors.append(f"{k}.sourceReferences: 2-6")
        for s in sr or []:
            sid = s.get("id", "?")
            lt(s.get("title"), f"{k}.source[{sid}].title")
            a, b = s.get("catechismStart"), s.get("catechismEnd")
            if a is not None or b is not None:
                if not (isinstance(a, int) and isinstance(b, int) and 1 <= a <= b <= 2865): errors.append(f"{k}.source[{sid}]: catechism range 1..2865")
                elif sid != f"ccc_{a}_{b}": errors.append(f"{k}.source[{sid}]: id must be ccc_{a}_{b}")
            else:
                u = s.get("url")
                if not isinstance(u, dict) or not all(str(u.get(l, "")).startswith("https://www.vatican.va/") for l in LANGS):
                    warns.append(f"{k}.source[{sid}]: non-CCC source should carry official vatican.va URLs es/en/pt")
    for w in warns: print("WARN ", w)
    for x in errors: print("ERROR", x)
    print(f"{len(lessons)} lessons · {len(errors)} errors · {len(warns)} warnings")
    sys.exit(1 if errors else 0)

if __name__ == "__main__":
    main()
