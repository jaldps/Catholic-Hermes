import json, os, shutil
ROOT = r"C:/Users/lanzj/Documents/Catholic-Hermes-work/repo"
A = ROOT + "/app/src/main/assets/chaplets"
B = ROOT + "/docs/tasks/chaplet-audit/batch1"
TOGETHER = {"es": "Se reza en conjunto", "en": "Said together", "pt": "Rezado em conjunto"}
IDS = ["alabanzas-maria", "almas-purgatorio-coronilla", "cabrini-coronilla", "carmen-coronilla",
       "confianza-tribulaciones", "conversion-coronilla", "corona-san-miguel-raccolta",
       "coronilla-difuntos-45", "coronilla-lagrimas", "coronilla-liberacion", "coronilla-san-jose-hozana"]

def load(i): return json.load(open(f"{A}/{i}.json", encoding="utf-8"))
def save(i, d):
    for path in (f"{A}/{i}.json", f"{B}/{i}.json"):
        with open(path, "w", encoding="utf-8") as f:
            json.dump(d, f, ensure_ascii=False, indent=2); f.write("\n")

# conversion: 5 literal Hail Marys -> canonical prayerRef
d = load("conversion-coronilla")
for s in d["steps"]:
    if "text" in s and s["text"]["es"].startswith("Avemaría"):
        s.pop("text"); s["prayerRef"] = "hail_mary"
save("conversion-coronilla", d)

# corona-san-miguel: English source is Catholic Doors; restore its verbatim wording
d = load("corona-san-miguel-raccolta")
SALUT = [
 "By the intercession of St. Michael and the Celestial choir of Seraphim, may the Lord make us worthy to burn with the fire of perfect charity. Amen.",
 "By the intercession of St. Michael and the Celestial choir of Cherubim, may the Lord vouchsafe to grant us grace to leave the ways of wickedness and run in the paths of Christian perfection. Amen.",
 "By the intercession of St. Michael and the Celestial choir of Thrones, may the Lord infuse into our hearts a true and sincere spirit of humility. Amen.",
 "By the intercession of St. Michael and the Celestial choir of Dominions, may the Lord give us grace to govern our senses and subdue our unruly passions. Amen.",
 "By the intercession of St. Michael and the Celestial choir of Virtues, may the Lord preserve us from evil, an suffer us not to fall into temptation. Amen.",
 "By the intercession of St. Michael and the Celestial choir of Powers, may the Lord vouchsafe to protect our souls against the snares and temptations of the devil. Amen.",
 "By the intercession of St. Michael and the Celestial choir of Principalities, may God fill our souls with a true spirit of obedience. Amen.",
 "By the intercession of St. Michael and the Celestial choir of Archangels, may the Lord give us perseverance in faith and in good works, in order that we gain the glory of Paradise. Amen.",
 "By the intercession of St. Michael and the Celestial choir of Angels, may the Lord grant us to be protected by them in this mortal life and conducted hereafter to eternal glory. Amen.",
]
d["steps"][0]["vr"]["v"]["en"] = "O God, come to my assistance!"
d["steps"][0]["vr"]["r"]["en"] = "O Lord, make haste to help me!"
n = 0
for s in d["steps"]:
    if "text" in s and "meditation" in s and s["meditation"]["es"].startswith("Salutación"):
        s["text"]["en"] = SALUT[n]; n += 1
assert n == 9, n
d["steps"][33]["text"]["en"] = ("O glorious Prince St. Michael, chief and commander of the heavenly hosts, guardian of souls, "
 "vanquisher of rebel spirits, servant in the house of the Divine King, and our admirable conductor, thou who dost shine with "
 "excellence and superhuman virtue, vouchsafe to deliver us from all evil, who turn to thee with confidence, and enable us by "
 "thy gracious protection to serve God more and more faithfully every day. Amen.")
d["steps"][34]["vr"]["v"]["en"] = "Pray for us, O Glorious St. Michael, Prince of the Church of Jesus Christ."
d["steps"][34]["vr"]["r"]["en"] = "That we may be made worthy of His Promises."
d["steps"][35]["text"]["en"] = ("Almighty and everlasting God, who by a prodigy of Goodness and a merciful desire for the "
 "salvation of all men, has appointed the most glorious Archangel St. Michael, Prince of Thy Church, make us worthy, we beseech "
 "Thee, to be delivered by his powerful protection from all our enemies, that none of them may harass us at the hour of our death, "
 "but that we may be conducted by him into the august presence of Thy Divine Majesty. This we beg through the merits of Jesus Christ our Lord. Amen.")
d["notes"] += " Fidelidad: texto EN sustituido por el literal de Catholic Doors (chap08)."
save("corona-san-miguel-raccolta", d)

# difuntos-45: English source wording for the sufragios
d = load("coronilla-difuntos-45")
for s in d["steps"]:
    if "text" in s and s["text"]["en"].startswith("Eternal Rest Grant unto"):
        s["text"]["en"] = ("Eternal Rest Grant unto (him/her) and let the perpetual light shine upon (him/her). "
                           "May (he/she) rest in peace.")
for idx, key, val in [(13, "v", "Eternal rest grant unto (him/her), O Lord."),
                      (13, "r", "And let perpetual light shine upon (him/her)."),
                      (14, "v", "May (he/she) rest in peace."),
                      (15, "v", "May (his/her) soul and the souls of all the faithful departed, through the mercy of God, rest in peace.")]:
    d["steps"][idx]["vr"][key]["en"] = val
save("coronilla-difuntos-45", d)

# lagrimas: exact (faulty) source wording in the response
d = load("coronilla-lagrimas")
for s in d["steps"]:
    if "vr" in s and "Mother's tears" in s["vr"]["r"]["en"]:
        s["vr"]["r"]["en"] = "For the sake of your most holly Mother\ufffds tears."
save("coronilla-lagrimas", d)

# san-jose: rebuild the strand in order (large, triad x20), add missing large at bead 40,
# and drop the trailing period that is outside the quotation in the source
d = load("coronilla-san-jose-hozana")
large = d["steps"][0]["text"]
small = dict(d["steps"][1]["text"]); small["es"] = "San José, ruega por nosotros"
final = d["steps"][-2:]
steps = []
for bead in range(41):
    t = dict(large) if bead % 2 == 0 else dict(small)
    st = {"bead": bead, "text": t, "rubric": dict(TOGETHER)}
    if bead % 2 == 1: st["repeat"] = 3
    steps.append(st)
steps += final
d["steps"] = steps
d["notes"] += " Fidelidad: orden de cuentas reconstruido (grande, tríada) y texto ES literal sin el punto fuera de la cita."
save("coronilla-san-jose-hozana", d)

print("fixed")
