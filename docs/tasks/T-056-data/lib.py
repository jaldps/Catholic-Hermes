# -*- coding: utf-8 -*-
"""Helpers for T-056 chaplet content (data only; no app code)."""
import json, pathlib

REPO = pathlib.Path(__file__).resolve().parents[3]
SPEC = json.loads((REPO / "docs/content/coronillas/coronillas_spec.json").read_text(encoding="utf-8"))
BY_ID = {x["id"]: x for x in SPEC}

def L(es, en, pt):
    return {"es": es, "en": en, "pt": pt}

def B(kind, count=None, color=None, label=None):
    b = {"kind": kind}
    if count is not None: b["count"] = count
    if color is not None: b["color"] = color
    if label is not None: b["label"] = label
    return b

def P(bead, ref, rep=1, med=None):
    s = {"bead": bead, "prayerRef": ref}
    if rep != 1: s["repeat"] = rep
    if med: s["meditation"] = med
    return s

def T(bead, text, rep=1, med=None):
    s = {"bead": bead, "text": text}
    if rep != 1: s["repeat"] = rep
    if med: s["meditation"] = med
    return s

def V(bead, v, r, rep=1, med=None):
    s = {"bead": bead, "vr": {"v": v, "r": r}}
    if rep != 1: s["repeat"] = rep
    if med: s["meditation"] = med
    return s

def F(bead=None):
    """A step outside the thread (rosary said without an instrument)."""
    return {"bead": bead}

def rosary59():
    """cross, 1+3+1, medal, then 5×(large + 10 small)."""
    b = [B("cross"), B("large"), B("small", 3), B("large"), B("medal")]
    for _ in range(5):
        b += [B("large"), B("small", 10)]
    return b

# decade large beads: 5,7,9,11,13 ; decade small groups: 6,8,10,12,14
DL = [5, 7, 9, 11, 13]
DS = [6, 8, 10, 12, 14]

def triads(n, color=None, label=None):
    b = [B("medal", label=label)]
    for _ in range(n):
        b += [B("large"), B("small", 3, color=color)]
    return b

def finish(ch):
    """Fill the contract defaults from the curated source spec."""
    sid = ch["sourceId"]
    src = BY_ID[sid]
    d = dict(ch)
    d.setdefault("image", ch["id"])
    d.setdefault("weekdays", [])
    d.setdefault("variables", [])
    d.setdefault("promise", None)
    d.setdefault("historicalNote", None)
    d.setdefault("season", None)
    d.setdefault("notes", "")
    if sid.startswith("CD-"):
        d.setdefault("rights", "Derechos: Catholic Doors Ministry")
        d.setdefault("translation", L("traducción fiel de Hermes, 2026", "original", "traducción fiel de Hermes, 2026"))
    elif sid.startswith(("IVE-", "HZ-")):
        d.setdefault("rights", None)
        d.setdefault("translation", L("original", "traducción fiel de Hermes, 2026", "traducción fiel de Hermes, 2026"))
    else:
        d.setdefault("rights", None)
        d.setdefault("translation", L("traducción fiel de Hermes, 2026", "original", "traducción fiel de Hermes, 2026"))
    d.setdefault("sources", [{"title": title_for(sid), "url": src["fuente"]}])
    return d

def title_for(sid):
    if sid.startswith("CD-"): return "Catholic Doors · " + BY_ID[sid]["nombre"]
    if sid.startswith("IVE-"): return "IVE · Rosarios y coronas"
    if sid.startswith("HZ-"): return "Hozana · Santo Rosario"
    return "Raccolta 1910 · T-007"

def write(batch_modules, only=None):
    out = REPO / "app/src/main/assets/chaplets"
    out.mkdir(parents=True, exist_ok=True)
    written = []
    for mod in batch_modules:
        for ch in mod.CHAPLETS:
            if only and ch["sourceId"] not in only: continue
            d = finish(ch)
            p = out / (d["id"] + ".json")
            p.write_text(json.dumps(d, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
            written.append((d["sourceId"], d["id"], p.name))
    for sid, cid, fn in written:
        print(sid, "->", fn)
    print(len(written), "chaplets written")
