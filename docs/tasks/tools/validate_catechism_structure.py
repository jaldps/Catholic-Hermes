#!/usr/bin/env python3
"""Validator for the Catechism structure index (assets/catechism/structure.json).

Usage (repository root):
  python docs/tasks/tools/validate_catechism_structure.py \
      app/src/main/assets/catechism/structure.json
Exit code 0 = valid.
"""
import json
import re
import sys
import pathlib

LANGS = ("es", "en", "pt")
KINDS = {"PROLOGUE", "PART", "SECTION", "CHAPTER", "ARTICLE", "PARAGRAPH", "HEADING"}
RANGO_PARTES = [(26, 1065), (1066, 1690), (1691, 2557), (2558, 2865)]
PROLOGO = (1, 25)
SNAKE = re.compile(r"^[a-z0-9]+([a-z0-9_]*[a-z0-9])?$")
errors, warns = [], []


def lt_ok(obj, where):
    if not isinstance(obj, dict) or set(obj) != set(LANGS):
        errors.append(f"{where}: title debe tener exactamente es/en/pt")
        return
    for lang in LANGS:
        v = obj.get(lang)
        if not isinstance(v, str) or not v.strip():
            errors.append(f"{where}.{lang}: vacío")


def recorre(nodos, funcion):
    for n in nodos:
        funcion(n)
        recorre(n.get("children", []), funcion)


def main():
    if len(sys.argv) != 2:
        print(__doc__)
        sys.exit(2)
    path = pathlib.Path(__file__).resolve().parents[3] / sys.argv[1]
    if not path.exists():
        print(f"ERROR {sys.argv[1]}: no existe")
        sys.exit(1)
    datos = json.loads(path.read_text(encoding="utf-8"))
    if set(datos) != {"nodes"}:
        errors.append(f"structure.json: la clave de nivel superior debe ser ['nodes'] "
                      f"(son {sorted(datos)})")
    raiz = datos.get("nodes", [])
    ids = []
    recorre(raiz, lambda n: ids.append(n.get("id")))
    if len(ids) != len(set(ids)):
        repetidos = sorted({i for i in ids if ids.count(i) > 1})
        errors.append(f"ids repetidos: {repetidos[:5]}")

    def comprueba(n, padre):
        donde = f"{n.get('id')!r}"
        if set(n) != {"id", "kind", "title", "range", "children"}:
            errors.append(f"{donde}: claves deben ser id, kind, title, range, children "
                          f"(son {sorted(n)})")
        if n.get("kind") not in KINDS:
            errors.append(f"{donde}: kind {n.get('kind')!r} no está en la lista")
        if not isinstance(n.get("id"), str) or not SNAKE.match(n["id"]):
            errors.append(f"{donde}: id no es snake_case")
        lt_ok(n.get("title"), f"{donde}.title")
        r = n.get("range")
        if not (isinstance(r, list) and len(r) == 2 and all(isinstance(x, int) for x in r)):
            errors.append(f"{donde}: range debe ser [a, b] de enteros")
            return
        if not (1 <= r[0] <= r[1] <= 2865):
            errors.append(f"{donde}: range {r} fuera de 1..2865")
            return
        if padre is not None:
            pr = padre["range"]
            if not (pr[0] <= r[0] and r[1] <= pr[1]):
                errors.append(f"{donde}: range {r} sale del padre {pr}")
        hermanos = [h for h in (padre["children"] if padre else raiz) if h is not n]
        for h in hermanos:
            hr = h.get("range", [0, 0])
            if r[0] <= hr[1] and hr[0] <= r[1]:
                errors.append(f"{donde}: range {r} se solapa con {h.get('id')} {hr}")
        hijos = n.get("children", [])
        if not isinstance(hijos, list):
            errors.append(f"{donde}: children debe ser una lista")
            return
        for h in hijos:
            comprueba(h, n)

    for n in raiz:
        comprueba(n, None)

    partes = [n for n in raiz if n.get("kind") == "PART"]
    if len(partes) != 4:
        errors.append(f"debe haber 4 partes (hay {len(partes)})")
    rangos = [tuple(n.get("range", [0, 0])) for n in partes]
    if rangos != RANGO_PARTES:
        errors.append(f"los rangos de las partes {rangos} no coinciden con {RANGO_PARTES}")
    prologos = [n for n in raiz if n.get("kind") == "PROLOGUE"]
    if len(prologos) != 1 or tuple(prologos[0].get("range", [0, 0])) != PROLOGO:
        errors.append("debe haber un prólogo con rango [1, 25]")
    cubierto = sorted([tuple(n["range"]) for n in prologos] + rangos)
    esperado, huecos = 1, []
    for a, b in cubierto:
        if a > esperado:
            huecos.append((esperado, a - 1))
        esperado = max(esperado, b + 1)
    if esperado != 2866:
        huecos.append((esperado, 2865))
    if huecos:
        errors.append(f"el prólogo y las partes no cubren 1..2865 sin huecos: {huecos}")

    for w in warns:
        print("WARN ", w)
    for x in errors:
        print("ERROR", x)
    print(f"{len(errors)} errores · {len(warns)} avisos")
    sys.exit(1 if errors else 0)


if __name__ == "__main__":
    main()
