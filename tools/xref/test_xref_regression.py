"""Regression tests for the bundled cross-reference assets (python3 -I -m unittest discover tools/xref -p 'test_*.py').
The bin checks need no downloads; the keyword audit runs when XREF_WORK/dl holds the OpenBible/KJV sources."""
import os, struct, sys, unittest
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
ROOT = os.path.abspath(os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", ".."))
CANON = "gen exo lev num deu jos jud rut 1sa 2sa 1ki 2ki 1ch 2ch ezr neh tob jdt est 1ma 2ma job psa pro ecc sng wis sir isa jer lam bar eze dan hos joe amo oba jon mic nah hab zep hag zec mal mat mar luk joh act rom 1co 2co gal eph phi col 1th 2th 1ti 2ti tit phm heb jam 1pe 2pe 1jo 2jo 3jo jde rev".split()
dec = lambda x: (CANON[x >> 16], (x >> 8) & 255, x & 255)

def refs_from(lang, ref):
    d = open(f"{ROOT}/app/src/main/assets/xref/xref_{lang}.bin", "rb").read(); n = struct.unpack(">i", d[4:8])[0]
    cols = [struct.unpack(f">{n}i", d[8 + 4 * n * i: 8 + 4 * n * (i + 1)]) for i in range(3)]
    return [dec(s) for f, s, e in zip(*cols) if dec(f) == ref]

class BundledXref(unittest.TestCase):
    def test_genesis_1_2_reaches_psalm_103_30(self):  # KJV Ps 104:30 "Thou sendest forth thy spirit"
        for lang in ("en", "es", "pt"):
            t = refs_from(lang, ("gen", 1, 2))
            self.assertIn(("psa", 103, 30), t, lang)
            self.assertNotIn(("psa", 103, 31), t, lang)  # es Ps 103:31 is "Sea eterna la gloria de Yahvé"

    def test_keyword_audit_bundled(self):
        os.environ.setdefault("XREF_WORK", os.path.join(ROOT, "build/xref"))
        if not os.path.exists(os.environ["XREF_WORK"] + "/dl/kjv.json"): self.skipTest("KJV/OpenBible sources not downloaded")
        import audit_xref; from common import kjv
        for lang, m in audit_xref.bundled_maps(kjv()).items():
            self.assertEqual([], audit_xref.run("bundled " + lang, lang, m, audit_xref.bundled_text(lang)))

if __name__ == "__main__": unittest.main()
