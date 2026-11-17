#!/usr/bin/env python3
"""Validator for M12 onboarding profiles (assets/onboarding/profiles.json).

Usage (repository root):
  python docs/tasks/tools/validate_onboarding.py \
      app/src/main/assets/onboarding/profiles.json
Exit code 0 = valid.
"""
import json
import re
import sys
import pathlib

LANGS = ("es", "en", "pt")
DESTINATIONS = {
    "HOME", "HABITS_VIRTUES", "BIBLE", "CATECHISM", "ROSARY", "PRAYERS", "DOCTRINE",
    "PARISHES", "QUESTIONS", "STUDY_DOCTRINE", "STUDY_MARY_JESUS", "STUDY_HISTORY", "SAINTS",
    "STUDY_ORDERS", "SPIRITUAL_LIFE", "CONFESSION", "REMINDERS", "BEGINNER",
}
MINUTES = {2, 5, 10}
PROHIBIDO = re.compile(r"\bTODO\b|\blorem\b|https?://|www\.")
SNAKE = re.compile(r"^[a-z][a-z0-9_]*$")
errors, warns = [], []


def palabras(texto):
    return len([t for t in texto.split() if t])


def lt_ok(obj, where, minimo=None, maximo=None):
    if not isinstance(obj, dict) or set(obj) != set(LANGS):
        errors.append(f"{where}: debe tener exactamente es/en/pt")
        return
    for lang in LANGS:
        v = obj.get(lang)
        if not isinstance(v, str) or not v.strip():
            errors.append(f"{where}.{lang}: vacío")
            continue
        n = palabras(v)
        if minimo is not None and n < minimo:
            errors.append(f"{where}.{lang}: {n} palabras (mínimo {minimo})")
        if maximo is not None and n > maximo:
            errors.append(f"{where}.{lang}: {n} palabras (máximo {maximo})")
        if PROHIBIDO.search(v):
            errors.append(f"{where}.{lang}: patrón prohibido")
    vals = [obj.get(l, "") for l in LANGS]
    if len(set(vals)) != len(LANGS):
        errors.append(f"{where}: los tres idiomas deben ser distintos entre sí")


def main():
    if len(sys.argv) != 2:
        print(__doc__)
        sys.exit(2)
    path = pathlib.Path(__file__).resolve().parents[3] / sys.argv[1]
    if not path.exists():
        print(f"ERROR {sys.argv[1]}: no existe")
        sys.exit(1)
    datos = json.loads(path.read_text(encoding="utf-8"))
    if set(datos) != {"profiles"}:
        errors.append(f"perfiles.json: claves de nivel superior deben ser ['profiles'] "
                      f"(son {sorted(datos)})")
    perfiles = datos.get("profiles", [])
    if len(perfiles) != 3:
        errors.append(f"perfiles.json: {len(perfiles)} perfiles (3 necesarios)")
    ids_vistos, sugerencias_globales = [], set()
    for indice, p in enumerate(perfiles):
        donde = f"perfil {indice}"
        if set(p) != {"id", "index", "welcome", "description", "suggestions"}:
            errors.append(f"{donde}: claves deben ser ['id','index','welcome','description','suggestions']")
        pid = p.get("id")
        ids_vistos.append(pid)
        if p.get("index") != indice:
            errors.append(f"{donde}: index {p.get('index')} no coincide con la posición {indice}")
        if not isinstance(pid, str) or not SNAKE.match(pid):
            errors.append(f"{donde}: id {pid!r} no es snake_case")
        lt_ok(p.get("welcome"), f"{donde}.welcome", maximo=35)
        lt_ok(p.get("description"), f"{donde}.description", minimo=25, maximo=60)
        sug = p.get("suggestions", [])
        if not isinstance(sug, list) or not (5 <= len(sug) <= 7):
            errors.append(f"{donde}: {len(sug) if isinstance(sug, list) else '?'} sugerencias "
                          f"(5-7 necesarias)")
        ids_sug, duraciones, carga = [], [], []
        for j, s in enumerate(sug):
            sd = f"{donde}.sugerencia {j}"
            if set(s) != {"id", "destination", "minutes", "title", "why"}:
                errors.append(f"{sd}: claves deben ser ['id','destination','minutes','title','why']")
                continue
            sid = s.get("id")
            if not isinstance(sid, str) or not SNAKE.match(sid):
                errors.append(f"{sd}: id {sid!r} no es snake_case")
            if sid in sugerencias_globales:
                errors.append(f"{sd}: id repetido")
            sugerencias_globales.add(sid)
            ids_sug.append(sid)
            if s.get("destination") not in DESTINATIONS:
                errors.append(f"{sd}: destination {s.get('destination')!r} no está en la lista")
            m = s.get("minutes")
            if m not in MINUTES:
                errors.append(f"{sd}: minutes {m!r} debe ser 2, 5 o 10")
            else:
                duraciones.append(m)
            lt_ok(s.get("title"), f"{sd}.title", maximo=8)
            lt_ok(s.get("why"), f"{sd}.why", maximo=30)
            carga.append((m if isinstance(m, int) else 0, sid))
        if duraciones and set(duraciones) != MINUTES:
            errors.append(f"{donde}: debe tener al menos una sugerencia de cada duración "
                          f"(tiene {sorted(set(duraciones))})")
        if any(carga[i][0] > carga[i + 1][0] for i in range(len(carga) - 1)):
            warns.append(f"{donde}: las sugerencias no están ordenadas de la más sencilla "
                         f"a la más exigente por minutes")
        if len(ids_sug) != len(set(ids_sug)):
            errors.append(f"{donde}: ids de sugerencia repetidos")
    if ids_vistos != ["exploring", "returning", "practicing"]:
        errors.append(f"perfiles.json: ids esperados ['exploring','returning','practicing'] "
                      f"(son {ids_vistos})")
    for w in warns:
        print("WARN ", w)
    for x in errors:
        print("ERROR", x)
    print(f"{len(errors)} errores · {len(warns)} avisos")
    sys.exit(1 if errors else 0)


if __name__ == "__main__":
    main()
