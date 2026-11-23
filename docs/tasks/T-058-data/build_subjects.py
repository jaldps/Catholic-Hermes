# -*- coding: utf-8 -*-
"""Curated subject map for T-058. Existing = key in saints/devotions credits."""
import json, os, pathlib
repo = pathlib.Path(__file__).resolve().parents[3]
base = repo/"app/src/main/assets"
out = pathlib.Path(__file__).resolve().parent/"subjects.json"

# id -> (existing_key or None, commons queries, aliases for filtering, note)
M = {
 "sagrado-corazon-jesus": ("sacred_heart_jesus", [], [], ""),
 "siete-dolores-corona": ("virgen_dolores", [], [], ""),
 "lourdes-rosario": ("lourdes", [], [], ""),
 "carmen-coronilla": ("virgen_del_carmen", [], [], ""),
 "guadalupe-coronilla": ("guadalupe", [], [], ""),
 "perpetuo-socorro-rosario": ("perpetuo_socorro", [], [], ""),
 "estrella-mar-coronilla": ("estrella_del_mar", [], [], ""),
 "inmaculada-concepcion-coronilla": ("inmaculada_concepcion", [], [], ""),
 "czestochowa-coronilla": ("virgen_czestochowa", [], [], ""),
 "llagas-cristo-coronilla": ("cinco_llagas", [], [], ""),
 "via-crucis-coronilla": ("via_crucis", [], [], ""),
 "sangre-preciosa-corona": ("preciosa_sangre", [], [], ""),
 "santisimo-meditar-adorar": ("santisimo_sacramento", [], [], ""),
 "sagrada-eucaristia-33": (None, ["Blessed Sacrament monstrance adoration", "Eucharist monstrance"], ["eucharist","sacrament","monstrance","ostensorio","santisimo"], ""),
 "santo-nombre-coronilla": ("santisimo_nombre_de_jesus", [], [], ""),
 "jesus-praga-coronilla": ("nino_jesus_de_praga", [], [], ""),
 "santa-faz-coronilla": ("santo_rostro", [], [], ""),
 "misericordia-perdon": ("divina_misericordia", [], [], ""),
 "conversion-coronilla": ("conversion_of_saint_paul_the_apostle", [], [], ""),
 "pasiones-cristo-rosario": ("cristo_yacente", [], [], ""),
 "teresita-24-glorias": ("therese_of_the_child_jesus_and_the_holy_face_of_lisieux_virgin", [], [], ""),
 "san-charbel": ("sharbel_makhluf_priest", [], [], ""),
 "san-antonio-padua-coronilla": ("anthony_of_padua_priest", [], [], ""),
 "maximiliano-coronilla": ("maximilian_mary_raymund_kolbe_priest", [], [], ""),
 "padre-pio-coronilla": ("pius_francesco_forgione_priest", [], [], ""),
 "santa-gertrudis-coronilla": ("gertrude_the_great_virgin", [], [], ""),
 "santa-rita-coronilla": ("rita_of_cascia_religious", [], [], ""),
 "san-patricio-coronilla": ("patrick_of_ireland_bishop", [], [], ""),
 "familia-franciscana-coronilla": ("francis_of_assisi", [], [], ""),
 "corona-san-miguel-raccolta": (None, ["Saint Michael the Archangel statue", "San Miguel Arcangel estatua"], ["michael","arcangel","archangel","miguel"], "No usar la imagen de los tres arcángeles: la coronilla es solo San Miguel."),
 "san-gabriel-coronilla": (None, ["Archangel Gabriel statue", "San Gabriel arcangel estatua"], ["gabriel","archangel","arcangel"], ""),
 "san-rafael-coronilla": (None, ["Archangel Raphael statue", "San Rafael arcangel estatua"], ["raphael","rafael","archangel","arcangel"], ""),
 "kateri-coronilla": (None, ["Kateri Tekakwitha", "Blessed Kateri Tekakwitha statue"], ["kateri","tekakwitha"], ""),
 "santa-dimpna-coronilla": (None, ["Saint Dymphna statue", "Sint Dymphna"], ["dymphna","dimpna"], ""),
 "cabrini-coronilla": (None, ["Frances Xavier Cabrini", "Mother Cabrini statue"], ["cabrini","frances xavier"], ""),
 "santa-filomena-coronilla": (None, ["Saint Philomena statue", "Santa Filomena"], ["philomena","filomena"], ""),
 "santa-ana-coronilla": (None, ["Saint Anne with Virgin Mary statue", "Santa Ana con la Virgen"], ["anne","anna","ana"], "Elegir Santa Ana, no San Joaquín."),
 "san-judas-coronilla": (None, ["Saint Jude Thaddeus statue", "San Judas Tadeo estatua"], ["jude","judas","thaddeus","tadeo"], ""),
 "coronilla-san-jose-hozana": (None, ["Saint Joseph with Child Jesus statue", "San Jose con el Nino"], ["joseph","jose"], "Elegir San José de Nazaret, no San José de Calasanz."),
 "inmaculado-corazon-maria": (None, ["Immaculate Heart of Mary", "Inmaculado Corazon de Maria"], ["immaculate heart","inmaculado corazon"], ""),
 "dos-corazones-coronilla": (None, ["United Hearts of Jesus and Mary", "Sacred Heart Immaculate Heart"], ["hearts","corazones","sacred heart"], ""),
 "reina-luz-coronilla": (None, ["Queen of Light Virgin Mary", "Maria Regina Lucis"], ["queen of light","regina lucis","reina de la luz"], "Origen no documentado; si no hay imagen exacta, usar imagen mariana referencial y anotarlo."),
 "desagravio-maria": (None, ["Immaculate Heart of Mary", "Virgen de los Dolores"], ["immaculate heart","inmaculado corazon","dolores"], "Si no hay imagen exacta, referencial mariana."),
 "alabanzas-maria": (None, ["Assumption of the Blessed Virgin Mary painting", "Our Lady Queen of Heaven"], ["assumption","asuncion","queen of heaven"], "Si no hay imagen exacta, referencial mariana."),
 "coronilla-lagrimas": (None, ["Our Lady of Tears", "Virgen de las Lagrimas"], ["tears","lagrimas","lacreme"], "Si no hay imagen exacta, referencial de la Virgen de los Dolores."),
 "madre-eucaristia-coronilla": (None, ["Blessed Virgin Mary Eucharist", "Madre de la Eucaristia"], ["eucharist","eucaristia","mary","maria"], "Si no hay imagen exacta, referencial eucarística."),
 "rosario-espiritu-santo": (None, ["Holy Spirit dove painting", "Espiritu Santo paloma"], ["holy spirit","espiritu santo","dove","paloma","pentecost"], ""),
 "santisima-trinidad-coronilla": (None, ["Holy Trinity painting", "Santisima Trinidad pintura"], ["trinity","trinidad"], ""),
 "confianza-tribulaciones": (None, ["Psalm 91 manuscript", "Trust in God painting"], ["psalm","salmo","trust","confianza"], "Si no hay imagen exacta, usar arte bíblico referencial."),
 "coronilla-liberacion": (None, ["Christ setting captives free painting", "Jesus liberacion"], ["liberation","liberacion","captive","cautivos","christ","jesus"], "Si no hay imagen exacta, usar Cristo referencial con anotación."),
 "fe-coronilla": (None, ["Faith allegory painting", "Virtue of Faith"], ["faith","fe"], "Si no hay imagen exacta, alegoría referencial."),
 "unidad-coronilla": (None, ["Unity of Christians", "Christian unity icon"], ["unity","unidad"], "Si no hay imagen exacta, referencial."),
 "almas-purgatorio-coronilla": (None, ["Souls in Purgatory painting", "Almas del Purgatorio"], ["purgatory","purgatorio","souls","almas"], "Si no hay imagen exacta, referencial de los fieles difuntos."),
 "rosario-difuntos-hozana": (None, ["Faithful departed souls painting", "Fieles difuntos"], ["departed","difuntos","souls","almas","requiem"], ""),
 "coronilla-difuntos-45": (None, ["All Souls Day painting", "Conmemoracion de los fieles difuntos"], ["departed","difuntos","souls","almas","requiem"], ""),
}

chaplets=[]
for f in sorted((base/"chaplets").glob("*.json")):
    d=json.loads(f.read_text(encoding="utf-8"))
    chaplets.append({"id":d["id"],"sourceId":d["sourceId"],"title":d["title"]["es"]})
assert len(chaplets)==55 and set(x["id"] for x in chaplets)==set(M), (len(chaplets), set(x["id"] for x in chaplets)^set(M))
subjects=[]
for c in chaplets:
    ex,q,al,note=M[c["id"]]
    subjects.append(dict(c, existing=ex, queries=q, aliases=al, note=note))
out.write_text(json.dumps(subjects,ensure_ascii=False,indent=2)+"\n",encoding="utf-8")
print(len(subjects),"existing",sum(1 for s in subjects if s["existing"]),"commons",sum(1 for s in subjects if not s["existing"]))
