#!/usr/bin/env python3
"""Validator for «Otras coronillas» (assets/chaplets/<id>.json), contract in docs/content/CORONILLAS-SPEC.md.

Usage (repository root):
  python3 docs/tasks/tools/validate_chaplets.py [--partial] [--images] app/src/main/assets/chaplets/*.json
Exit code 0 = valid. --partial accepts a subset of the curated list.
--images also requires assets/chaplets/images/<image>.jpg and its entry in credits.json (the owner made an own,
relevant image mandatory for every chaplet; there is no generic fallback).
"""
import json
import pathlib
import re
import sys

LANGS = ("es", "en", "pt")
ROOT = pathlib.Path(__file__).resolve().parents[3]
GROUPS = {"god_christ", "mary", "saints", "angels", "departed", "intention"}
BEAD_KINDS = {"cross", "medal", "large", "small", "separator", "knot"}
VARIABLES = {"deceased_name", "deceased_gender", "intention"}
ID_RE = re.compile(r"^[a-z0-9]+(?:-[a-z0-9]+)*$")
HEX_RE = re.compile(r"^#[0-9A-Fa-f]{6}$")
PLACEHOLDER_RE = re.compile(r"\{\{([^}]+)\}\}")
# Instructions that belong in `rubric`, not `meditation` (es text).
RUBRIC_RE = re.compile(r"^\s*(en (la|las|el|los|cada) |al (final|principio|inicio|comienzo)|cuenta (grande|peque)|se reza|rezar |repetir |tres veces|despu[eé]s de )", re.I)
CURATED = {
    # Dios y Cristo
    "IVE-07", "IVE-09", "IVE-10", "IVE-15", "HZ-03", "HZ-04", "HZ-05", "CD-37", "CD-40", "CD-44", "CD-45", "CD-54", "HZ-02",
    # María
    "IVE-08", "IVE-11", "IVE-12", "IVE-16", "HZ-01", "CD-10", "CD-11", "CD-12", "CD-13", "CD-15", "CD-34", "CD-42", "CD-53",
    "CD-47", "CD-57",
    # Santos
    "HZ-07", "HZ-08", "IVE-13", "IVE-14", "CD-17", "CD-19", "CD-20", "CD-22", "CD-24", "CD-25", "CD-26", "CD-28", "CD-29",
    "CD-30", "CD-32", "CD-58",
    # Ángeles (la Corona de San Miguel existente usa sourceId "APP-ST-MICHAEL")
    "APP-ST-MICHAEL", "CD-21", "CD-31",
    # Difuntos
    "IVE-06", "HZ-10", "CD-01",
    # Por una intención
    "IVE-17", "CD-05", "CD-08", "CD-09", "CD-55",
}
assert len(CURATED) == 55, len(CURATED)

errors, warns = [], []


def app_prayer_ids():
    kt = (ROOT / "app/src/main/java/com/example/data/repository/PrayerAndNovenaData.kt").read_text(encoding="utf-8")
    ids = set(re.findall(r'id = "([a-z_0-9]+)"', kt))
    extra = ROOT / "app/src/main/assets/prayers/extra.json"
    if extra.exists():
        ids |= {p["id"] for p in json.loads(extra.read_text(encoding="utf-8"))}
    return ids


# Rough language fingerprint: catches whole fields written in the wrong language (e.g. es/en swapped).
STOP = {
    "es": re.compile(r"\b(el|los|las|del|nuestro|nuestra|nosotros|señor|madre|con|y|ruega|santísima|vuestro)\b", re.I),
    "en": re.compile(r"\b(the|and|thy|thee|thou|you|your|pray|holy|mother|lord|who|with|for|our|us)\b", re.I),
    "pt": re.compile(r"\b(os|do|da|dos|das|nosso|nossa|nós|senhor|mãe|pelo|pela|com|e|rogai|vós|ó)\b", re.I),
}


def wrong_language(text, lang):
    """The other language whose stop-words clearly dominate this field, or None."""
    if len(text) < 40:
        return None
    own = len(STOP[lang].findall(text))
    for other in LANGS:
        if other != lang and len(STOP[other].findall(text)) >= max(4, 3 * (own + 1)):
            return other
    return None


def lt(obj, where, latin_ok=False):
    """A localized text: exactly es/en/pt, none empty; en/pt differ from es unless latin_ok."""
    if not isinstance(obj, dict) or set(obj) != set(LANGS):
        errors.append(f"{where}: must be an object with exactly es/en/pt")
        return False
    ok = True
    for lang in LANGS:
        if not isinstance(obj.get(lang), str) or not obj[lang].strip():
            errors.append(f"{where}.{lang}: empty")
            ok = False
    if ok:
        for lang in LANGS:
            other = wrong_language(obj[lang], lang)
            if other:
                errors.append(f"{where}.{lang}: reads as {other.upper()}, not {lang.upper()} (swapped or untranslated?): {obj[lang][:50]!r}")
    if ok and not latin_ok and len(obj["es"]) > 25 and (obj["en"] == obj["es"] or obj["pt"] == obj["es"]):
        warns.append(f"{where}: en/pt identical to es (only acceptable for Latin; check the translation)")
    return ok


def placeholders(obj):
    found = set()
    if isinstance(obj, dict):
        for v in obj.values():
            if isinstance(v, str):
                found |= set(PLACEHOLDER_RE.findall(v))
    return found


def image_credits():
    f = ROOT / "app/src/main/assets/chaplets/images/credits.json"
    if not f.exists():
        return None
    data = json.loads(f.read_text(encoding="utf-8"))
    # Same format as assets/devotions/images/credits.json: {"images": [{"id": ..., ...}]}.
    if isinstance(data, dict) and isinstance(data.get("images"), list):
        data = data["images"]
    if isinstance(data, dict):
        return set(data)
    return {e.get("id") or e.get("key") for e in data}


def check_image(where, key, credits):
    img = ROOT / f"app/src/main/assets/chaplets/images/{key}.jpg"
    if not img.exists():
        errors.append(f"{where}.image: missing assets/chaplets/images/{key}.jpg (an own, relevant image is mandatory)")
    if credits is None:
        errors.append(f"{where}.image: assets/chaplets/images/credits.json does not exist")
    elif key not in credits:
        errors.append(f"{where}.image: no entry for {key!r} in credits.json")


def check(path, prayer_ids, seen_ids, seen_sources, images=False, credits=None):
    where = path.name
    try:
        d = json.loads(path.read_text(encoding="utf-8"))
    except Exception as e:  # noqa: BLE001
        errors.append(f"{where}: invalid JSON ({e})")
        return
    cid = d.get("id")
    if not isinstance(cid, str) or not ID_RE.match(cid):
        errors.append(f"{where}: id must be lower-case words joined by '-'")
    elif path.stem != cid:
        errors.append(f"{where}: file name must be <id>.json ({cid}.json)")
    if cid in seen_ids:
        errors.append(f"{where}: duplicate id {cid}")
    seen_ids.add(cid)
    src = d.get("sourceId")
    if src not in CURATED:
        errors.append(f"{where}: sourceId {src!r} is not in the curated list (CORONILLAS-SPEC.md)")
    if src in seen_sources:
        errors.append(f"{where}: sourceId {src} used twice")
    seen_sources.add(src)
    if d.get("group") not in GROUPS:
        errors.append(f"{where}: group must be one of {sorted(GROUPS)}")
    for key in ("title", "summary", "instrument", "when"):
        lt(d.get(key), f"{where}.{key}")
    if not isinstance(d.get("image"), str) or not d["image"].strip():
        errors.append(f"{where}.image: required key (T-058 supplies the file)")
    elif images:
        check_image(where, d["image"], credits)
    wd = d.get("weekdays", [])
    if not isinstance(wd, list) or any(not isinstance(x, int) or not 1 <= x <= 7 for x in wd):
        errors.append(f"{where}.weekdays: list of ISO days 1-7")
    if d.get("season") not in (None, "lent", "advent", "easter"):
        errors.append(f"{where}.season: lent | advent | easter")
    variables = d.get("variables", [])
    if not isinstance(variables, list) or any(v not in VARIABLES for v in variables):
        errors.append(f"{where}.variables: subset of {sorted(VARIABLES)}")
        variables = []

    beads = d.get("beads")
    if not isinstance(beads, list):
        errors.append(f"{where}.beads: list (empty only for chaplets without an instrument)")
        beads = []
    for i, b in enumerate(beads):
        bw = f"{where}.beads[{i}]"
        if not isinstance(b, dict) or b.get("kind") not in BEAD_KINDS:
            errors.append(f"{bw}.kind: one of {sorted(BEAD_KINDS)}")
            continue
        c = b.get("count", 1)
        if not isinstance(c, int) or c < 1:
            errors.append(f"{bw}.count: integer >= 1")
        if "color" in b and not (isinstance(b["color"], str) and HEX_RE.match(b["color"])):
            errors.append(f"{bw}.color: #RRGGBB")
        if "label" in b:
            lt(b["label"], f"{bw}.label")

    steps = d.get("steps")
    if not isinstance(steps, list) or not steps:
        errors.append(f"{where}.steps: non-empty list")
        steps = []
    used = {}
    used_placeholders = set()
    for i, s in enumerate(steps):
        sw = f"{where}.steps[{i}]"
        if not isinstance(s, dict):
            errors.append(f"{sw}: object")
            continue
        contents = [k for k in ("prayerRef", "text", "vr") if k in s]
        if len(contents) != 1:
            errors.append(f"{sw}: exactly one of prayerRef / text / vr (found {contents})")
        if "prayerRef" in s and s["prayerRef"] not in prayer_ids:
            errors.append(f"{sw}.prayerRef: unknown prayer id {s['prayerRef']!r}")
        if "text" in s:
            lt(s["text"], f"{sw}.text")
            used_placeholders |= placeholders(s["text"])
        if "vr" in s:
            vr = s["vr"]
            if not isinstance(vr, dict) or set(vr) != {"v", "r"}:
                errors.append(f"{sw}.vr: object with exactly v and r")
            else:
                lt(vr["v"], f"{sw}.vr.v")
                lt(vr["r"], f"{sw}.vr.r")
                used_placeholders |= placeholders(vr["v"]) | placeholders(vr["r"])
        if "meditation" in s:
            lt(s["meditation"], f"{sw}.meditation")
            med = s["meditation"]
            if isinstance(med, dict) and RUBRIC_RE.match(med.get("es", "")):
                errors.append(f"{sw}.meditation: looks like an instruction ({med['es'][:40]!r}); put it in rubric")
        if "rubric" in s:
            lt(s["rubric"], f"{sw}.rubric")
        rep = s.get("repeat", 1)
        if not isinstance(rep, int) or rep < 1:
            errors.append(f"{sw}.repeat: integer >= 1")
            rep = 1
        b = s.get("bead")
        if b is not None:
            if not isinstance(b, int) or not 0 <= b < len(beads):
                errors.append(f"{sw}.bead: index into beads (0..{len(beads) - 1}) or null")
            else:
                used[b] = used.get(b, 0) + rep
    # Bead coverage: a group with count > 1 must receive exactly count prayers per pass.
    # A source inconsistency (rule 6) is accepted only when `notes` explains it (mentions «cuentas» or «beads»).
    notes = (d.get("notes") or "").lower()
    explained = "cuentas" in notes or "beads" in notes
    for i, b in enumerate(beads):
        c = b.get("count", 1) if isinstance(b, dict) else 1
        if c > 1 and i in used and used[i] % c != 0:
            msg = f"{where}.beads[{i}]: steps cover {used[i]} prayers for a group of {c}"
            if explained:
                warns.append(msg + " (explained in notes)")
            else:
                errors.append(msg + " — fix the steps or explain the source's count in notes")
    declared_ph = set()
    if "deceased_name" in variables:
        declared_ph.add("nombre")
    if "intention" in variables:
        declared_ph.add("intencion")
    for ph in used_placeholders:
        if "|" in ph:
            if "deceased_gender" not in variables:
                errors.append(f"{where}: gendered placeholder {{{{{ph}}}}} needs variable deceased_gender")
        elif ph not in declared_ph:
            errors.append(f"{where}: placeholder {{{{{ph}}}}} without its variable")

    for key in ("promise", "historicalNote"):
        v = d.get(key)
        if v is not None:
            if not isinstance(v, dict) or "text" not in v or "source" not in v:
                errors.append(f"{where}.{key}: null or {{text: {{es,en,pt}}, source: str}}")
            else:
                lt(v["text"], f"{where}.{key}.text")
    sources = d.get("sources")
    if not isinstance(sources, list) or not sources:
        errors.append(f"{where}.sources: at least one")
    else:
        for j, s in enumerate(sources):
            if not isinstance(s, dict) or not str(s.get("url", "")).startswith("https://") or not s.get("title"):
                errors.append(f"{where}.sources[{j}]: {{title, url https://}}")
    tr = d.get("translation")
    if not isinstance(tr, dict) or set(tr) != set(LANGS) or "original" not in tr.values():
        errors.append(f"{where}.translation: es/en/pt, one of them 'original', the others say who translated")
    r = d.get("rights")
    if r is not None and not (isinstance(r, str) and r.startswith("Derechos: ")):
        errors.append(f"{where}.rights: null or 'Derechos: <titular>'")


def main(argv):
    partial = "--partial" in argv
    images = "--images" in argv
    credits = image_credits() if images else None
    files = [pathlib.Path(a) for a in argv if not a.startswith("--")]
    if not files:
        print(__doc__)
        return 2
    prayer_ids = app_prayer_ids()
    seen_ids, seen_sources = set(), set()
    for f in files:
        check(f, prayer_ids, seen_ids, seen_sources, images, credits)
    excluded = set()
    ex = ROOT / "docs/tasks/T-056-data/excluded.json"
    if ex.exists():
        excluded = {e["sourceId"] for e in json.loads(ex.read_text(encoding="utf-8"))}
    missing = CURATED - seen_sources - excluded
    if missing and not partial:
        errors.append(f"missing {len(missing)} curated chaplets: {sorted(missing)}")
    elif missing:
        warns.append(f"partial delivery: {len(seen_sources)} of {len(CURATED) - len(excluded)}")
    for w in warns:
        print("WARN ", w)
    for e in errors:
        print("ERROR", e)
    print(f"{len(files)} files · {len(errors)} errors · {len(warns)} warnings")
    return 1 if errors else 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
