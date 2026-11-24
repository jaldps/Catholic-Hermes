#!/usr/bin/env python3
"""Fidelity checks for the 55 corrected chaplets (T-063).

Run: python3 tools/chaplets/validate_chaplets_fidelity.py
"""
import glob
import json
import pathlib
import re
import unicodedata

ROOT = pathlib.Path(__file__).resolve().parents[2]
ASSETS = ROOT / "app/src/main/assets/chaplets"
SOURCES = ROOT / "docs/content/coronillas/sources"
CANONICAL = set(re.findall(r'id = "([a-z_0-9]+)"', (ROOT / "app/src/main/java/com/example/data/repository/PrayerAndNovenaData.kt").read_text(encoding="utf-8")))
GROUP = {
    "es": "Se reza en conjunto",
    "en": "Said together",
    "pt": "Rezado em conjunto",
}


def norm(s: str) -> str:
    s = unicodedata.normalize("NFKD", s).encode("ascii", "ignore").decode().lower()
    s = s.replace("“", '"').replace("”", '"').replace("’", "'")
    return re.sub(r"\s+", " ", s).strip()


def source_lang(source_id: str) -> str:
    return "es" if source_id.startswith(("IVE-", "HZ-")) else "en"


def main() -> int:
    errors = []
    totals = {"files": 0, "steps": 0, "prayerRefs": 0, "vr": 0, "group": 0}
    for path in sorted(ASSETS.glob("*.json")):
        totals["files"] += 1
        d = json.loads(path.read_text(encoding="utf-8"))
        sid = d.get("sourceId", "")
        lang = source_lang(sid)
        src = SOURCES / (d["id"] + ".txt")
        src_text = norm(src.read_text(encoding="utf-8")) if src.exists() else ""
        for i, step in enumerate(d.get("steps", [])):
            totals["steps"] += 1
            tag = f"{d['id']}.steps[{i}]"
            ref = step.get("prayerRef")
            if ref:
                totals["prayerRefs"] += 1
                if ref not in CANONICAL:
                    errors.append(f"{tag}: unknown prayerRef {ref}")
                continue
            vr = step.get("vr")
            if vr:
                totals["vr"] += 1
                for role in ("v", "r"):
                    text = (vr.get(role) or {}).get(lang, "")
                    if re.match(r"^\s*[VRvRr][./]", text):
                        errors.append(f"{tag}.{role}: voice prefix must not be in the text")
                    if src_text and len(norm(text)) >= 25 and norm(text)[:40] not in src_text:
                        errors.append(f"{tag}.{role}: source text not found verbatim")
            text = step.get("text") or {}
            literal = norm(text.get(lang, ""))
            if literal:
                if re.search(r"(que estás en el cielo|full of grace,? the lord is with|llena de gracia; el señor|hallowed be thy name|santificado sea tu nombre|glory be to the father|gloria al padre, y al hijo)", literal):
                    errors.append(f"{tag}: canonical prayer written as literal text; use prayerRef")
                if src_text and len(literal) >= 25 and literal[:40] not in src_text:
                    errors.append(f"{tag}: source text not found verbatim")
            rubric = norm((step.get("rubric") or {}).get(lang, ""))
            if rubric == norm(GROUP[lang]):
                totals["group"] += 1
        if not d.get("steps"):
            errors.append(f"{d['id']}: no steps")
    print(f"{totals['files']} files · {totals['steps']} steps · "
          f"{totals['prayerRefs']} prayerRefs · {totals['vr']} V/R · "
          f"{totals['group']} group recitations")
    for e in errors:
        print("ERROR", e)
    print(f"{len(errors)} errors")
    return 1 if errors else 0


if __name__ == "__main__":
    raise SystemExit(main())
