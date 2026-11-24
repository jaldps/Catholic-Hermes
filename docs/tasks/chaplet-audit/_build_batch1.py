import json, os, re, copy
ROOT = r"C:/Users/lanzj/Documents/Catholic-Hermes-work/repo"
SRC = ROOT + "/app/src/main/assets/chaplets"
OUT = ROOT + "/docs/tasks/chaplet-audit/batch1"
os.makedirs(OUT, exist_ok=True)
LANGS = ("es", "en", "pt")

def L(es, en, pt): return {"es": es, "en": en, "pt": pt}
TOGETHER = L("Se reza en conjunto", "Said together", "Rezado em conjunto")

def load(i): return json.load(open(f"{SRC}/{i}.json", encoding="utf-8"))
def save(i, d):
    with open(f"{OUT}/{i}.json", "w", encoding="utf-8") as f:
        json.dump(d, f, ensure_ascii=False, indent=2)
        f.write("\n")
def note(d, extra):
    d["notes"] = ((d.get("notes") or "").rstrip() + " " + extra).strip()
def together(d):
    for s in d["steps"]:
        if "text" in s and "rubric" not in s:
            s["rubric"] = dict(TOGETHER)
def clean_vr(d):
    for s in d["steps"]:
        if "vr" in s:
            for k in ("v", "r"):
                for lg in LANGS:
                    s["vr"][k][lg] = re.sub(r"^\s*[VR℣℟]\s*[./]\s*", "", s["vr"][k][lg]).strip()

# 1. alabanzas-maria: headings -> meditation; strip V/R marks; together rubrics
d = load("alabanzas-maria")
headings = {2: dict(d["steps"][2]["text"]), 18: dict(d["steps"][18]["text"]), 34: dict(d["steps"][34]["text"])}
clean_vr(d)
new = []
for i, s in enumerate(d["steps"]):
    if i in headings: continue
    if (i - 1) in headings and "prayerRef" in s:
        s["meditation"] = dict(headings[i - 1])
    new.append(s)
d["steps"] = new
together(d)
note(d, "Auditoría: los rótulos de las tres coronas pasan de text a meditation (abren sección) y se quitan los prefijos V/ y R/ del contenido de las jaculatorias; el motor los dibuja.")
save("alabanzas-maria", d)

# 2. almas-purgatorio-coronilla: instructions out of meditation; final 3 beads added
d = load("almas-purgatorio-coronilla")
for s in d["steps"]:
    if "meditation" in s and "text" in s:
        s["rubric"] = s.pop("meditation")
d["beads"].append({"kind": "small", "count": 3})
d["steps"][-1]["bead"] = len(d["beads"]) - 1
together(d)
note(d, "Auditoría: las indicaciones («tres veces», «antes de las cuentas pequeñas», «tres últimas cuentas antes de la Cruz») van en rubric, no en meditation; se añade el grupo de 3 cuentas finales que la fuente nombra antes de la Cruz.")
save("almas-purgatorio-coronilla", d)

# 3. cabrini: no invented cross or large beads; no invented Sign of the Cross
d = load("cabrini-coronilla")
prayer = dict(d["steps"][2]["text"])
d["beads"] = [{"kind": "medal"}] + [{"kind": "small", "count": 5} for _ in range(5)]
steps = []
for g in range(1, 6):
    steps.append({"bead": g, "prayerRef": "hail_mary", "repeat": 5,
                  "rubric": L("En cada una de las 5 cuentas", "On each of the 5 beads", "Em cada uma das 5 contas")})
    steps.append({"bead": None, "text": copy.deepcopy(prayer),
                  "rubric": L("Al final de cada grupo de cinco", "At the end of each set of five", "No fim de cada grupo de cinco")})
d["steps"] = steps
together(d) if False else None
note(d, "Auditoría: la fuente da 25 cuentas en 5 grupos de 5 y una medalla; se eliminan la Señal de la Cruz (no está en la fuente) y las cuentas grandes inventadas. La oración final se reza al terminar cada grupo, sobre la última cuenta.")
save("cabrini-coronilla", d)

# 4. carmen: already faithful; only explicit together rubric on its two opening prayers
d = load("carmen-coronilla")
together(d)
save("carmen-coronilla", d)

# 5. confianza-tribulaciones: faithful; explicit together rubric on the jaculatoria
d = load("confianza-tribulaciones")
together(d)
note(d, "Auditoría: la jaculatoria se reza en conjunto en cada cuenta; se declara en rubric. Estructura de 10 misterios x 9 + final confirmada (91 repeticiones).")
save("confianza-tribulaciones", d)

# 6. conversion: restore the Hail Mary on every large bead; one Hail Mary in the intro
d = load("conversion-coronilla")
d["beads"][2]["count"] = 1
d["steps"][2].pop("repeat", None)
hm = L("Avemaría.", "Hail Mary.", "Ave Maria.")
out = []
for s in d["steps"]:
    out.append(s)
    if "text" in s and s.get("bead") in (5, 7, 9, 11, 13):
        out.append({"bead": s["bead"], "text": dict(hm)})
d["steps"] = out
together(d)
note(d, "Auditoría: la fuente pide Avemaría en TODAS las cuentas grandes y después la jaculatoria «Santa Madre María»; se añade la Avemaría que faltaba en las cinco grandes. En la introducción se reza una sola Avemaría en la segunda cuenta, como dice la fuente.")
save("conversion-coronilla", d)

# 7. corona-san-miguel-raccolta: invocation on the medal; V/R stripped; final block split
d = load("corona-san-miguel-raccolta")
d["beads"][0] = {"kind": "medal", "label": L("Medalla", "Medal", "Medalha")}
clean_vr(d)
last = d["steps"][-1]

def split_final(t):
    mv = re.search(r"\nV/\s*", t); mr = re.search(r"\nR/\s*", t)
    mo = re.search(r"\n(?:Oremos\.|Let us pray\.)\n", t)
    return t[:mv.start()], t[mv.end():mr.start()], t[mr.end():mo.start()], t[mo.end():]
parts = {lg: split_final(last["text"][lg]) for lg in LANGS}
last["text"] = {lg: parts[lg][0] for lg in LANGS}
together(d)
vr = {"bead": None, "vr": {"v": {lg: parts[lg][1] for lg in LANGS}, "r": {lg: parts[lg][2] for lg in LANGS}}}
collect = {"bead": None, "text": {lg: parts[lg][3] for lg in LANGS}, "rubric": dict(TOGETHER)}
d["steps"].extend([vr, collect])
note(d, "Auditoría: la invocación inicial se dice en la medalla (no en la cruz); se quitan los prefijos V/ y R/ del contenido y el bloque final, que mezclaba antífona, versículo y oración en un solo text, se separa en antífona + vr + oración.")
save("corona-san-miguel-raccolta", d)

# 8. coronilla-difuntos-45: complete the concluding V/R sequence and the closing Our Father/Hail Mary
d = load("coronilla-difuntos-45")
clean_vr(d)
d["steps"].extend([
    {"bead": None, "vr": {"v": L("Que {{el|ella}} descanse en paz.", "May {{el|ella}} rest in peace.", "Que {{el|ella}} descanse em paz."),
                          "r": L("Amén.", "Amen.", "Amém.")}},
    {"bead": None, "vr": {"v": L("Que el alma de {{nombre}} y las almas de todos los fieles difuntos, por la misericordia de Dios, descansen en paz.", "May {{nombre}}'s soul and the souls of all the faithful departed, through the mercy of God, rest in peace.", "Que a alma de {{nombre}} e as almas de todos os fiéis defuntos, pela misericórdia de Deus, descansem em paz."),
                          "r": L("Amén.", "Amen.", "Amém.")}},
    {"bead": None, "vr": {"v": L("Que el amor de Dios y la paz del Señor Jesucristo nos bendigan y nos consuelen y sequen suavemente cada lágrima de nuestros ojos. En el nombre del Padre, y del Hijo, y del Espíritu Santo.", "May the love of God and the peace of the Lord Jesus Christ bless us and console us and gently wipe every tear from our eyes. In the name of the Father, and of the Son, and of the Holy Spirit.", "Que o amor de Deus e a paz do Senhor Jesus Cristo nos abençoe e nos console e enxugue suavemente cada lágrima dos nossos olhos. Em nome do Pai, e do Filho, e do Espírito Santo."),
                          "r": L("Amén.", "Amen.", "Amém.")}},
    {"bead": None, "prayerRef": "our_father"},
    {"bead": None, "prayerRef": "hail_mary"},
])
together(d)
note(d, "Auditoría: el final estaba incompleto; se añaden los versículos «May he/she rest in peace», «May his/her soul...», la bendición final y el Padrenuestro y Avemaría con que también se cierra la devoción. Se quitan los prefijos V. y R. del contenido de los vr.")
save("coronilla-difuntos-45", d)

# 9. coronilla-lagrimas: first meditation was invented; final prayer missing; set the VR on separators
d = load("coronilla-lagrimas")
first_med = L("Las lágrimas derramadas por nuestra Santísima Madre durante la profecía de Simeón, que una espada atravesaría su Corazón.",
              "The tears shed by our Most Holy Mother during Simeon's Prophecy that a sword would pierce Her Heart.",
              "As lágrimas derramadas pela nossa Santíssima Mãe durante a profecia de Simeão, que uma espada atravessaria o seu Coração.")
d["steps"][4]["meditation"] = first_med
for idx, bead in zip((4, 6, 8, 10, 12, 14, 16), (2, 5, 7, 9, 11, 13, 15)):
    d["steps"][idx]["bead"] = bead
d["steps"].append({"bead": None, "text": L(
    "Oh María, Madre de amor, dolor y misericordia, te rogamos que unas tus oraciones a las nuestras, para que Jesús, tu divino Hijo, a quien recurrimos, escuche nuestras peticiones en nombre de tus lágrimas maternas y nos conceda, además de los favores que pedimos, la corona de la vida eterna. Amén.",
    "O Mary, Mother of love, sorrow and mercy, we beg you to unite your prayers with ours so that Jesus, your Divine Son, to Whom we turn may hear our petitions in the name of your maternal tears and may give us in addition to the favors we ask, the crown of everlasting life. Amen.",
    "Ó Maria, Mãe de amor, dor e misericórdia, suplicamos-te que unas as tuas orações às nossas, para que Jesus, teu divino Filho, a quem recorremos, ouça as nossas petições em nome das tuas lágrimas maternais e nos conceda, além dos favores que pedimos, a coroa da vida eterna. Amém.")})
together(d)
d["notes"] = ("Texto literal de Catholic Doors; errata «holly Mothers tears» corregida a «holy Mother's tears». "
              "Auditoría: la primera meditación era inventada (nacimiento en Belén) y se sustituye por la de la profecía de Simeón que sí da la fuente; "
              "el V/R de cada cuenta separadora se apoya en su cuenta real; se añade la oración final «O Mary, Mother of love» que faltaba.")
save("coronilla-lagrimas", d)

# 10. coronilla-liberacion: the Word is proclaimed on every large bead, including the first three
d = load("coronilla-liberacion")
verse = dict(d["steps"][1]["text"])
inv = dict(d["steps"][2]["text"])
d["steps"][0]["bead"] = 0
out = [d["steps"][0],
       {"bead": 1, "text": copy.deepcopy(verse)},
       {"bead": 2, "text": copy.deepcopy(inv), "repeat": 3},
       {"bead": 3, "text": copy.deepcopy(verse)}]
out += d["steps"][1:]
d["steps"] = out
together(d)
note(d, "Auditoría: el Credo va en la cruz y la palabra de Juan 8:36 se proclama en TODAS las cuentas grandes, incluidas la primera y la que precede a la medalla; la jaculatoria se reza también en las tres primeras cuentas pequeñas, como manda el método.")
save("coronilla-liberacion", d)

# 11. coronilla-san-jose-hozana: the large-bead prayer on every large bead; medal and cross; rubric not meditation
d = load("coronilla-san-jose-hozana")
large_prayer = dict(d["steps"][0]["text"])
out = []
for s in d["steps"]:
    out.append(s)
    if s.get("bead") in [g + 1 for g in range(2, 41, 2)]:
        out.append({"bead": s["bead"] - 1, "text": copy.deepcopy(large_prayer)})
d["steps"] = out
d["beads"].extend([{"kind": "medal", "label": L("Medalla de San José", "St Joseph medal", "Medalha de São José")},
                   {"kind": "cross"}])
for s in d["steps"]:
    if "text" in s and "Jesús, José y María" in s["text"]["es"]:
        s["bead"] = len(d["beads"]) - 2
    if "meditation" in s and "Oración opcional final" in s["meditation"]["es"]:
        s["rubric"] = s.pop("meditation")
together(d)
note(d, "Auditoría: «San José, guardián...» se reza en cada cuenta grande (no solo en la primera); se añaden la medalla y la cruz que la fuente nombra al final; «Oración opcional final» es una instrucción y pasa de meditation a rubric.")
save("coronilla-san-jose-hozana", d)

print("written", sorted(os.listdir(OUT)))
