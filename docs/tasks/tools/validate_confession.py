#!/usr/bin/env python3
"""Validator for M12 Confession content (assets/confession/*.json).

Usage (repository root):
  python docs/tasks/tools/validate_confession.py \
      app/src/main/assets/confession/examen.json \
      app/src/main/assets/confession/guide.json
Exit code 0 = valid.
"""
import json
import re
import sys
import pathlib

LANGS = ("es", "en", "pt")
PERFILES = {"children", "youth", "married", "single", "consecrated"}
MIN_SECTIONS = 12
BASIS = re.compile(r"^CIC (\d{1,4})(?:-(\d{1,4}))?$")
errors, warns = [], []


def words(s):
    return len(re.findall(r"\w+", s, re.UNICODE))


def lt(obj, where):
    if not isinstance(obj, dict) or set(obj) != set(LANGS):
        errors.append(f"{where}: object with exactly es/en/pt")
        return
    for lang in LANGS:
        v = obj.get(lang)
        if not isinstance(v, str) or not v.strip():
            errors.append(f"{where}.{lang}: empty")
        elif words(v) < 1:
            errors.append(f"{where}.{lang}: empty")


def check_basis(value, where):
    if not isinstance(value, str):
        errors.append(f"{where}: basis must be a string")
        return
    m = BASIS.match(value.strip())
    if not m:
        errors.append(f"{where}: bad basis {value!r} (expected 'CIC n' or 'CIC n-m')")
        return
    a = int(m.group(1))
    b = int(m.group(2)) if m.group(2) else a
    if not (1 <= a <= b <= 2865):
        errors.append(f"{where}: basis out of range 1..2865 ({value!r})")


def validar_examen(path):
    d = json.loads(path.read_text(encoding="utf-8"))
    if set(d) != {"profiles"}:
        errors.append("examen.json: top-level keys must be exactly ['profiles']")
    perfiles = d.get("profiles", [])
    ids = [p.get("id") for p in perfiles]
    if set(ids) != PERFILES or len(ids) != len(PERFILES):
        errors.append(f"examen.json: profiles must be exactly {sorted(PERFILES)}")
    for p in perfiles:
        pid = p.get("id", "?")
        lt(p.get("title"), f"examen.{pid}.title")
        lt(p.get("intro"), f"examen.{pid}.intro")
        secs = p.get("sections", [])
        if len(secs) < MIN_SECTIONS:
            errors.append(f"examen.{pid}: {len(secs)} sections (< {MIN_SECTIONS})")
        vistos = set()
        for s in secs:
            sid = s.get("id", "?")
            if sid in vistos:
                errors.append(f"examen.{pid}.{sid}: duplicated section id")
            vistos.add(sid)
            lt(s.get("title"), f"examen.{pid}.{sid}.title")
            check_basis(s.get("basis"), f"examen.{pid}.{sid}.basis")
            qs = s.get("questions", [])
            if not 3 <= len(qs) <= 8:
                errors.append(f"examen.{pid}.{sid}: {len(qs)} questions (3..8)")
            for i, q in enumerate(qs):
                lt(q, f"examen.{pid}.{sid}.q[{i}]")
    print(f"examen.json: {len(perfiles)} perfiles · "
          f"{sum(len(p.get('sections', [])) for p in perfiles)} secciones · "
          f"{sum(len(s.get('questions', [])) for p in perfiles for s in p.get('sections', []))} preguntas")


def validar_guide(path):
    d = json.loads(path.read_text(encoding="utf-8"))
    if set(d) != {"steps", "contrition", "faq"}:
        errors.append("guide.json: top-level keys must be exactly ['steps','contrition','faq']")
    steps = d.get("steps", [])
    if not 6 <= len(steps) <= 8:
        errors.append(f"guide.json: {len(steps)} steps (6..8)")
    for i, s in enumerate(steps):
        lt(s.get("title"), f"guide.steps[{i}].title")
        lt(s.get("body"), f"guide.steps[{i}].body")
        for lang in LANGS:
            n = words(s.get("body", {}).get(lang, ""))
            if not 40 <= n <= 90:
                warns.append(f"guide.steps[{i}].body.{lang}: {n} words (40..90)")
        check_basis(s.get("basis"), f"guide.steps[{i}].basis")
    contr = d.get("contrition", [])
    if not 2 <= len(contr) <= 3:
        errors.append(f"guide.json: {len(contr)} contrition acts (2..3)")
    for i, c in enumerate(contr):
        lt(c.get("title"), f"guide.contrition[{i}].title")
        lt(c.get("text"), f"guide.contrition[{i}].text")
        lt(c.get("source"), f"guide.contrition[{i}].source")
    faq = d.get("faq", [])
    if not 6 <= len(faq) <= 10:
        errors.append(f"guide.json: {len(faq)} faq entries (6..10)")
    for i, f in enumerate(faq):
        lt(f.get("question"), f"guide.faq[{i}].question")
        lt(f.get("answer"), f"guide.faq[{i}].answer")
        check_basis(f.get("basis"), f"guide.faq[{i}].basis")
    print(f"guide.json: {len(steps)} pasos · {len(contr)} actos de contrición · {len(faq)} preguntas")


def main():
    if len(sys.argv) < 2:
        print(__doc__)
        sys.exit(2)
    root = pathlib.Path(__file__).resolve().parents[3]
    for arg in sys.argv[1:]:
        path = root / arg
        if not path.exists():
            errors.append(f"{arg}: file not found")
            continue
        if path.name == "examen.json":
            validar_examen(path)
        elif path.name == "guide.json":
            validar_guide(path)
        else:
            errors.append(f"{arg}: unknown file")
    for w in warns:
        print("WARN ", w)
    for x in errors:
        print("ERROR", x)
    print(f"{len(errors)} errors · {len(warns)} warnings")
    sys.exit(1 if errors else 0)


if __name__ == "__main__":
    main()
