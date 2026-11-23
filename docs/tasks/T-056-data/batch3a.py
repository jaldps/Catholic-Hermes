# -*- coding: utf-8 -*-
"""Lote 3a: APP-ST-MICHAEL, desde text-overrides.json (Raccolta 1910 n.º 291, T-007)."""
import json, re, pathlib
from lib import L, B, P, T, V

REPO = pathlib.Path(__file__).resolve().parents[3]
OV = json.loads((REPO/"app/src/main/assets/prayers/text-overrides.json").read_text(encoding="utf-8"))
E = next(x for x in OV if x["id"] == "chaplet_of_st_michael")

def salutations(lang):
    parts = re.split(r"\n\s*\n", E["leaderText"][lang])
    segs = [q for q in parts if re.match(r"^(Salutation|Salutación|Saudação)\s+\d", q.strip())]
    assert len(segs) == 9, (lang, len(segs))
    return ["\n".join(q.strip().split("\n")[1:]).strip() for q in segs]

S = {lg: salutations(lg) for lg in ("es","en","pt")}
OPEN = {}
for lg in ("es","en","pt"):
    lines = [x.strip() for x in E["leaderText"][lg].split("\n")[:3]]
    OPEN[lg] = (lines[0], lines[1])

beads = [B("cross")]
for _ in range(9):
    beads += [B("large"), B("small",3)]
beads += [B("large") for _ in range(4)]

steps = [V(0, L(OPEN["es"][0],OPEN["en"][0],OPEN["pt"][0]),
              L(OPEN["es"][1],OPEN["en"][1],OPEN["pt"][1])), P(0,"glory_be")]
for i in range(9):
    steps += [P(1+2*i,"our_father"),
              P(2+2*i,"hail_mary",3),
              T(1+2*i, L(S["es"][i],S["en"][i],S["pt"][i]),
                med=L("Salutación %d"%(i+1),"Salutation %d"%(i+1),"Saudação %d"%(i+1)))]
for i in range(4):
    steps.append(P(19+i,"our_father",med=L("Padrenuestro final %d: san Miguel, san Gabriel, san Rafael y el Ángel de la Guarda"%(i+1),
      "Final Our Father %d: St Michael, St Gabriel, St Raphael and the Guardian Angel"%(i+1),
      "Pai-Nosso final %d: São Miguel, São Gabriel, São Rafael e o Anjo da Guarda"%(i+1))))
steps.append(T(None, L(E["responseText"]["es"],E["responseText"]["en"],E["responseText"]["pt"]),
               med=L("Antífona y oración final","Antiphon and final prayer","Antífona e oração final")))

CHAPLETS = [{
 "id":"corona-san-miguel-raccolta","sourceId":"APP-ST-MICHAEL","group":"angels",
 "title":L("Corona de San Miguel Arcángel","Chaplet of St Michael the Archangel","Coroa de São Miguel Arcanjo"),
 "summary":L("Nueve salutaciones a los coros angélicos, cuatro Padres Nuestros y antífona final.",
   "Nine salutations to the angelic choirs, four Our Fathers and a final antiphon.",
   "Nove saudações aos coros angélicos, quatro Pai-Nossos e antífona final."),
 "instrument":L("Corona con nueve grupos de tres cuentas y cuatro cuentas finales; también sirve el rosario tradicional.",
   "Chaplet with nine groups of three beads and four concluding beads; an ordinary rosary may also be used.",
   "Coroa com nove grupos de três contas e quatro contas finais; também serve o terço tradicional."),
 "when":L("Como devoción personal o comunitaria a los nueve coros angélicos, para pedir a Dios conversión y protección.",
   "As a personal or communal devotion honoring the nine angelic choirs, asking God for conversion and protection.",
   "Como devoção pessoal ou comunitária aos nove coros angélicos, pedindo a Deus conversão e proteção."),
 "weekdays":[],
 "beads":beads,
 "steps":steps,
 "sources":[{"title":"The Raccolta, 1910, n.º 291, pp. 261–263 (texto de T-007)","url":"https://www.catholicdoors.com/prayers/chaplets/chap08.htm"}],
 "translation":{"es":"traducción fiel de T-007","en":"original","pt":"traducción fiel de T-007"},
 "rights":None,
 "notes":"Texto inglés original de la Raccolta 1910 n.º 291, ya presente en text-overrides.json (T-007), tomado de los campos leaderText y responseText sin reescribirlo. No se usa la versión de ninguna otra web.",
}]
