#!/usr/bin/env python3
"""Validator for M12 notices (assets/notices/notices.json).

Usage (repository root):
  python docs/tasks/tools/validate_notices.py \
      app/src/main/assets/notices/notices.json
Exit code 0 = valid.
"""
import json
import re
import sys
import pathlib

LANGS = ("es", "en", "pt")
ACCIONES = {"HOME", "BIBLE", "CATECHISM", "ROSARY", "PRAYERS", "SAINTS",
            "SPIRITUAL_LIFE", "CONFESSION", "PLANS", "HABITS_VIRTUES"}
SEASONES = {"ADVENT", "CHRISTMAS", "ORDINARY_TIME", "LENT", "HOLY_WEEK", "TRIDUUM", "EASTER"}
CELEBRACIONES = {
    "ash_wednesday", "palm_sunday", "holy_thursday_evening", "good_friday", "easter_sunday",
    "divine_mercy_sunday", "ascension", "pentecost", "trinity", "corpus_christi",
    "sacred_heart", "christ_the_king", "holy_family", "epiphany", "baptism_of_the_lord",
    "christmas_day", "mary_mother_of_god", "conversion_of_saint_paul_the_apostle",
    "presentation_of_the_lord", "chair_of_saint_peter_the_apostle", "joseph_spouse_of_mary",
    "annunciation_of_the_lord", "mark_evangelist", "philip_and_james_apostles", "matthias_apostle",
    "visitation", "nativity_of_john_the_baptist", "peter_and_paul_apostles", "thomas_apostle",
    "mary_magdalene", "james_apostle", "transfiguration_of_the_lord", "lawrence_of_rome_deacon",
    "bartholomew_apostle", "nativity_of_the_blessed_virgin_mary", "exaltation_of_the_holy_cross",
    "matthew_apostle", "michael_gabriel_and_raphael_archangels", "luke_evangelist",
    "simon_and_jude_apostles", "all_saints", "commemoration_of_all_the_faithful_departed",
    "dedication_lateran", "andrew_apostle", "immaculate_conception_of_the_blessed_virgin_mary",
    "stephen_the_first_martyr", "john_apostle", "holy_innocents_martyrs",
}
RECORDATORIOS = {"sunday_mass", "holy_day_of_obligation", "novena_day", "plan_day",
                 "rosary_evening", "examen_night", "confession_gentle"}
MARCADORES = {"novena_day": {"{{novena}}", "{{day}}"}, "plan_day": {"{{plan}}", "{{day}}"}}
PROHIBIDO = re.compile(r"https?://|www\.|\bTODO\b|\blorem\b|¡{2,}|!{2,}|[A-Z]{4,}")
EMOJI = re.compile("[\U0001F300-\U0001FAFF\u2600-\u27BF]")
SNAKE = re.compile(r"^[a-z][a-z0-9_]*$")
errors, warns = [], []


def palabras(texto):
    return len([t for t in texto.split() if t])


def lt_ok(obj, where, maximo=None):
    if not isinstance(obj, dict) or set(obj) != set(LANGS):
        errors.append(f"{where}: debe tener exactamente es/en/pt")
        return None
    for lang in LANGS:
        v = obj.get(lang)
        if not isinstance(v, str) or not v.strip():
            errors.append(f"{where}.{lang}: vacío")
            continue
        if PROHIBIDO.search(v):
            errors.append(f"{where}.{lang}: patrón prohibido")
        if EMOJI.search(v):
            errors.append(f"{where}.{lang}: emoji")
        if maximo and palabras(v) > maximo:
            errors.append(f"{where}.{lang}: {palabras(v)} palabras (máximo {maximo})")
    vals = [obj.get(l, "") for l in LANGS]
    if len(set(vals)) != len(LANGS):
        errors.append(f"{where}: los tres idiomas deben ser distintos entre sí")
    return obj


def aviso_ok(aviso, where, max_titulo=6, max_cuerpo=25, marcadores=()):
    if not isinstance(aviso, dict) or not set(aviso) <= {"title", "body", "action"} or \
            "title" not in aviso or "body" not in aviso:
        errors.append(f"{where}: claves deben ser title, body y (opcional) action")
        return
    if "action" in aviso and aviso["action"] not in ACCIONES:
        errors.append(f"{where}.action: {aviso['action']!r} no está en la lista")
    lt_ok(aviso["title"], f"{where}.title", maximo=max_titulo)
    lt_ok(aviso["body"], f"{where}.body", maximo=max_cuerpo)
    if marcadores:
        for lang in LANGS:
            texto = aviso["body"].get(lang, "")
            faltan = [m for m in marcadores if m not in texto]
            if faltan:
                errors.append(f"{where}.body.{lang}: faltan los marcadores {faltan}")


def ids_unicos(ids, donde):
    if len(ids) != len(set(ids)):
        errors.append(f"{donde}: ids repetidos")


def main():
    if len(sys.argv) != 2:
        print(__doc__)
        sys.exit(2)
    path = pathlib.Path(__file__).resolve().parents[3] / sys.argv[1]
    if not path.exists():
        print(f"ERROR {sys.argv[1]}: no existe")
        sys.exit(1)
    datos = json.loads(path.read_text(encoding="utf-8"))
    if set(datos) != {"seasons", "celebrations", "reminders"}:
        errors.append(f"notices.json: claves de nivel superior deben ser "
                      f"['seasons','celebrations','reminders'] (son {sorted(datos)})")

    seasons = datos.get("seasons", {})
    if set(seasons) != SEASONES:
        errors.append(f"seasons: faltan o sobran claves "
                      f"(faltan {sorted(SEASONES - set(seasons))}, "
                      f"sobran {sorted(set(seasons) - SEASONES)})")
    for clave, s in seasons.items():
        if not isinstance(s, dict) or set(s) != {"start", "weekly"}:
            errors.append(f"seasons.{clave}: claves deben ser start y weekly")
            continue
        aviso_ok(s["start"], f"seasons.{clave}.start")
        semanales = s["weekly"]
        if not isinstance(semanales, list) or not (4 <= len(semanales) <= 6):
            errors.append(f"seasons.{clave}: {len(semanales) if isinstance(semanales, list) else '?'} "
                          f"avisos semanales (4-6 necesarios)")
        ids = []
        for i, w in enumerate(semanales or []):
            if not isinstance(w, dict) or set(w) != {"id", "title", "body", "action"}:
                errors.append(f"seasons.{clave}.weekly[{i}]: claves id, title, body, action")
                continue
            if not SNAKE.match(str(w.get("id", ""))):
                errors.append(f"seasons.{clave}.weekly[{i}].id: no es snake_case")
            ids.append(w["id"])
            if w.get("action") not in ACCIONES:
                errors.append(f"seasons.{clave}.weekly[{i}].action: no está en la lista")
            lt_ok(w["title"], f"seasons.{clave}.weekly[{i}].title", maximo=6)
            lt_ok(w["body"], f"seasons.{clave}.weekly[{i}].body", maximo=25)
        ids_unicos(ids, f"seasons.{clave}")

    celebrations = datos.get("celebrations", {})
    if set(celebrations) != CELEBRACIONES:
        errors.append(f"celebrations: faltan o sobran claves "
                      f"(faltan {sorted(CELEBRACIONES - set(celebrations))}, "
                      f"sobran {sorted(set(celebrations) - CELEBRACIONES)})")
    for clave, c in celebrations.items():
        if not isinstance(c, dict) or set(c) != {"eve", "day"}:
            errors.append(f"celebrations.{clave}: claves deben ser eve y day")
            continue
        aviso_ok(c["eve"], f"celebrations.{clave}.eve")
        aviso_ok(c["day"], f"celebrations.{clave}.day")

    reminders = datos.get("reminders", {})
    if set(reminders) != RECORDATORIOS:
        errors.append(f"reminders: faltan o sobran claves "
                      f"(faltan {sorted(RECORDATORIOS - set(reminders))}, "
                      f"sobran {sorted(set(reminders) - RECORDATORIOS)})")
    for clave, r in reminders.items():
        aviso_ok(r, f"reminders.{clave}", marcadores=MARCADORES.get(clave, ()))

    for w in warns:
        print("WARN ", w)
    for x in errors:
        print("ERROR", x)
    print(f"{len(errors)} errores · {len(warns)} avisos")
    sys.exit(1 if errors else 0)


if __name__ == "__main__":
    main()
