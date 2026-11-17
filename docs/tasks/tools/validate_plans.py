#!/usr/bin/env python3
"""Validator for M12 reading plans (assets/plans/*.json).

Usage (repository root):
  python docs/tasks/tools/validate_plans.py \
      app/src/main/assets/plans/bible-year.json \
      app/src/main/assets/plans/catechism-year.json
Exit code 0 = valid.
"""
import json
import re
import sys
import pathlib

LANGS = ("es", "en", "pt")
ROOT = pathlib.Path(__file__).resolve().parents[3]
REF = re.compile(r"^([a-z0-9]{2,4}) (\d{1,3})(?:-(\d{1,3}))?$")
REF_VERS = re.compile(r"^([a-z0-9]{2,4}) (\d{1,3}):(\d{1,3})-(\d{1,3})$")
errors, warns = [], []


def libros_y_capitulos():
    kt = (ROOT / "app/src/main/java/com/example/data/repository/BibleData.kt").read_text(encoding="utf-8")
    ids = re.findall(r'BibleBook\("([a-z0-9]+)"', kt)
    biblia = json.loads((ROOT / "app/src/main/assets/bible/bible_es.json").read_text(encoding="utf-8"))
    return ids, {k: len(v) for k, v in biblia.items()}


def lt_ok(obj, where):
    if not isinstance(obj, dict) or set(obj) != set(LANGS):
        errors.append(f"{where}: object with exactly es/en/pt")
        return False
    ok = True
    for lang in LANGS:
        if not isinstance(obj.get(lang), str) or not obj[lang].strip():
            errors.append(f"{where}.{lang}: empty")
            ok = False
    return ok


def comprobar_dias(dias, donde):
    if not isinstance(dias, list) or len(dias) != 365:
        errors.append(f"{donde}: {len(dias) if isinstance(dias, list) else '?'} days (365 needed)")
        return False
    for i, d in enumerate(dias, 1):
        if d.get("day") != i:
            errors.append(f"{donde}: day {d.get('day')} out of order at position {i}")
    return True


def validar_biblia(path, ids, caps):
    d = json.loads(path.read_text(encoding="utf-8"))
    if set(d) != {"id", "title", "description", "days"}:
        errors.append("bible-year.json: top-level keys must be exactly "
                      "['id','title','description','days']")
    lt_ok(d.get("title"), "bible-year.title")
    lt_ok(d.get("description"), "bible-year.description")
    if d.get("id") != "bible_year":
        errors.append(f"bible-year.json: id must be 'bible_year' (got {d.get('id')!r})")
    dias = d.get("days", [])
    if not comprobar_dias(dias, "bible-year"):
        return
    cubiertos = {libro: set() for libro in ids}
    total = 0
    libros_usados = set()
    for dia in dias:
        lecturas = dia.get("readings")
        if not isinstance(lecturas, list) or not lecturas:
            errors.append(f"bible-year day {dia.get('day')}: readings missing")
            continue
        for r in lecturas:
            m = REF_VERS.match(str(r))
            if m:
                libro, cap, v1, v2 = m.group(1), int(m.group(2)), int(m.group(3)), int(m.group(4))
                if libro not in ids:
                    errors.append(f"bible-year day {dia['day']}: unknown book {libro!r}")
                    continue
                if not (1 <= cap <= caps[libro]):
                    errors.append(f"bible-year day {dia['day']}: {r} beyond {libro} ({caps[libro]} ch.)")
                    continue
                cubiertos[libro].add(cap)
                libros_usados.add(libro)
                total += 1
                continue
            m = REF.match(str(r))
            if not m:
                errors.append(f"bible-year day {dia['day']}: bad reference {r!r}")
                continue
            libro = m.group(1)
            c1 = int(m.group(2))
            c2 = int(m.group(3)) if m.group(3) else c1
            if libro not in ids:
                errors.append(f"bible-year day {dia['day']}: unknown book {libro!r}")
                continue
            if not (1 <= c1 <= c2 <= caps[libro]):
                errors.append(f"bible-year day {dia['day']}: {r} beyond {libro} ({caps[libro]} ch.)")
                continue
            for c in range(c1, c2 + 1):
                cubiertos[libro].add(c)
            libros_usados.add(libro)
            total += c2 - c1 + 1
    faltan = {libro: sorted(set(range(1, caps[libro] + 1)) - cubiertos[libro])
              for libro in ids if set(range(1, caps[libro] + 1)) - cubiertos[libro]}
    if faltan:
        for libro, caps_faltan in faltan.items():
            errors.append(f"bible-year: {libro} missing chapters {caps_faltan}")
    if len(libros_usados) != len(ids):
        errors.append(f"bible-year: only {len(libros_usados)} of {len(ids)} books used")
    cargas = [len(d.get("readings", [])) for d in dias]
    print(f"bible-year.json: {len(dias)} dias · {total} lecturas · "
          f"capitulos distintos cubiertos {sum(len(v) for v in cubiertos.values())}/{sum(caps.values())} · "
          f"carga {min(cargas)}-{max(cargas)} (media {sum(cargas)/len(cargas):.2f})")


def validar_catecismo(path):
    d = json.loads(path.read_text(encoding="utf-8"))
    if set(d) != {"id", "title", "description", "days"}:
        errors.append("catechism-year.json: top-level keys must be exactly "
                      "['id','title','description','days']")
    lt_ok(d.get("title"), "catechism-year.title")
    lt_ok(d.get("description"), "catechism-year.description")
    if d.get("id") != "catechism_year":
        errors.append(f"catechism-year.json: id must be 'catechism_year' (got {d.get('id')!r})")
    dias = d.get("days", [])
    if not comprobar_dias(dias, "catechism-year"):
        return
    esperado, prev = 1, 0
    cargas = []
    for dia in dias:
        p = dia.get("paragraphs")
        if not (isinstance(p, list) and len(p) == 2 and all(isinstance(x, int) for x in p)):
            errors.append(f"catechism-year day {dia.get('day')}: paragraphs must be [start, end]")
            continue
        a, b = p
        if not (1 <= a <= b <= 2865):
            errors.append(f"catechism-year day {dia['day']}: range {a}-{b} out of 1..2865")
            continue
        if a != esperado:
            errors.append(f"catechism-year day {dia['day']}: starts at {a}, expected {esperado} "
                          f"(gap or overlap)")
        if b <= prev:
            errors.append(f"catechism-year day {dia['day']}: overlaps previous day")
        esperado = b + 1
        prev = b
        cargas.append(b - a + 1)
    if esperado != 2866:
        errors.append(f"catechism-year: covers up to {prev}, expected 2865")
    print(f"catechism-year.json: {len(dias)} dias · carga {min(cargas)}-{max(cargas)} "
          f"(media {sum(cargas)/len(cargas):.2f}) · total {sum(cargas)}")


def main():
    if len(sys.argv) < 2:
        print(__doc__)
        sys.exit(2)
    ids, caps = libros_y_capitulos()
    for arg in sys.argv[1:]:
        path = ROOT / arg
        if not path.exists():
            errors.append(f"{arg}: file not found")
            continue
        if path.name == "bible-year.json":
            validar_biblia(path, ids, caps)
        elif path.name == "catechism-year.json":
            validar_catecismo(path)
        else:
            errors.append(f"{arg}: unknown plan file")
    for w in warns:
        print("WARN ", w)
    for x in errors:
        print("ERROR", x)
    print(f"{len(errors)} errors · {len(warns)} warnings")
    sys.exit(1 if errors else 0)


if __name__ == "__main__":
    main()
