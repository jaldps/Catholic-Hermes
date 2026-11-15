"""Checks the xref_<edition>.bin files built by build_assets.py --editions against the converted edition text.
  XREF_WORK=<dir with dl/> python3 -I tools/xref/validate_editions.py <converter out dir> <xref out dir>
Reports per edition: references whose source or target verse is missing from the edition text (must be ~0), John 3:16
references, the text Psalm 23 / Psalm 22 (Vulgate) resolves to, and - for editions in a bundled language - how often the
mapped KJV verse and the bundled edition's verse for the same KJV verse share vocabulary (alignment sanity)."""
import sys, json, struct, re, os; sys.path.insert(0, __file__.rsplit('/', 1)[0])
from common import *
from build_assets import CANON
from editions import load_edition, edition_map
from align import words
root, xdir = sys.argv[1], sys.argv[2]

def read_bin(p):
    d = open(p, "rb").read(); assert d[:4] == b"XRF1"; n = struct.unpack(">i", d[4:8])[0]
    cols = [struct.unpack(f">{n}i", d[8 + 4 * n * i: 8 + 4 * n * (i + 1)]) for i in range(3)]
    return list(zip(*cols))
dec = lambda x: (CANON[x >> 16], (x >> 8) & 255, x & 255)
K = kjv()
bundled_maps = {}
def bundled_map(l):
    if l not in bundled_maps:
        from audit_xref import bundled_maps as load
        bundled_maps.update(load(K))
    return bundled_maps[l]
for ed in json.load(open(root + "/manifest_cbt.json"))["editions"]:
    texts, exists = load_edition(root, ed)
    ok = lambda r: r[1] <= len(exists[r[0]]) and r[2] in exists[r[0]][r[1] - 1]
    rows = read_bin(f"{xdir}/xref_{ed}.bin")
    miss_from = sum(1 for f, s, e in rows if not ok(dec(f))); miss_to = sum(1 for f, s, e in rows if not ok(dec(s)) or not ok(dec(e)))
    j316 = sum(1 for f, s, e in rows if dec(f) == ("joh", 3, 16))
    m, _ = edition_map(K, texts, exists, english=ed.startswith("en."))
    def snip(b, c, v):
        t = m.get((b, c, v)); return None if not t else f"{t[0]}:{t[1]} {texts[b][t[0]-1][t[1]-1][:60]!r}"
    print(f"{ed}: refs {len(rows)} missing-source {miss_from} missing-target {miss_to}; John 3:16 refs {j316}")
    print("   KJV Ps 23:1 ->", snip("psa", 23, 1)); print("   KJV Ps 22:1 ->", snip("psa", 22, 1)); print("   KJV Ps 51:1 ->", snip("psa", 51, 1)); print("   KJV Jon 1:17 ->", snip("jon", 1, 17), "| KJV Joel 2:28 ->", snip("joe", 2, 28))
    l = ed[:2]
    if l in ("en", "es", "pt"):
        bm = bundled_map(l); B = lang(l); sims = []
        for k, (c, v) in m.items():
            t = bm.get(k)
            if not t: continue
            a = words(texts[k[0]][c - 1][v - 1]); b = words(B[k[0]][t[0] - 1][t[1] - 1])
            if a and b: sims.append(len(a & b) / len(a | b))
        sims.sort()
        agree = sum(1 for k, t in m.items() if bm.get(k) == t) / max(1, len(m))
        print(f"   vs bundled {l}: same position {agree:.1%}; word overlap median {sims[len(sims)//2]:.2f}, <0.05: {sum(1 for s in sims if s < .05)/len(sims):.1%}")
