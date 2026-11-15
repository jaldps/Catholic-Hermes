"""Keyword audit of KJV -> edition verse maps: does the verse a KJV reference maps to actually say the same thing?
  XREF_WORK=<dir with dl/> python3 -I tools/xref/audit_xref.py [bundled|<converter out dir>]
Each case is a well-known KJV verse plus one keyword pattern per language (accents folded, case ignored)."""
import sys, re, unicodedata; sys.path.insert(0, __file__.rsplit('/', 1)[0])
from common import *

# (book, chapter, verse, {lang: regex}) in KJV numbering
CASES = [
 ("gen",1,1,{"en":"beginning","es":"principio","pt":"principio","la":"principio"}),
 ("gen",1,2,{"en":"spirit","es":"espiritu|soplo|viento","pt":"espirito|vento","la":"spiritus"}),
 ("gen",31,55,{"en":"kiss","es":"beso|besa|abrazo|abrazar","pt":"beij","la":"osculatus"}),
 ("exo",3,14,{"en":"i am","es":"soy el que soy|yo soy","pt":"eu sou","la":"sum qui sum|ego sum"}),
 ("exo",8,1,{"en":"let my people go","es":"mi pueblo|deja ir|enviar|deja libre|deja salir|despide|libera|deja partir|deja marchar","pt":"deixa|despede|solta|liberta","la":"dimitte"}),
 ("exo",8,32,{"en":"hardened|relentless","es":"endurec|obstin|empecin|porfiad","pt":"endurec|obstin","la":"ingravat|indurat|aggravat"}),
 ("exo",20,3,{"en":"other gods|strange gods","es":"otros dioses|otro dios|dioses ajenos","pt":"outros deuses|outro deus","la":"deos|dii alieni"}),
 ("exo",22,1,{"en":"ox","es":"buey|toro","pt":"boi","la":"bovem|bos"}),
 ("est",2,7,{"en":"esther","es":"ester","pt":"ester","la":"esther"}),
 ("est",4,14,{"en":"kingdom|relief|deliverance","es":"reino|realeza|alivio|ayuda|salvacion","pt":"reino|realeza|calares|salvar","la":"regnum"}),
 ("est",4,16,{"en":"perish|gather","es":"perezc|muero|morir|perecer","pt":"perec|morr","la":"peream|peri"}),
 ("job",19,25,{"en":"redeemer","es":"redentor|vengador|defensor","pt":"redentor|vingador|defensor","la":"redemptor|vindex"}),
 ("psa",1,1,{"en":"blessed","es":"dichoso|bienaventurado|feliz|bendito","pt":"bem-aventurado|feliz|bendito","la":"beatus"}),
 ("psa",3,2,{"en":"no help|help|salvation","es":"salvacion|ayuda|socorro|salvar|salva","pt":"salvacao|ajuda|socorro|salva","la":"salus|salvatio|salute"}),
 ("psa",18,2,{"en":"rock|firmament","es":"roca|pena|fortaleza","pt":"rocha|rochedo|penhasco","la":"firmamentum|petra"}),
 ("psa",22,1,{"en":"forsaken","es":"abandon|desampar","pt":"abandon|desampar","la":"dereliquisti|dereliquist"}),
 ("psa",23,1,{"en":"shepherd|ruleth","es":"pastor","pt":"pastor|apascenta","la":"pascit|regit|pastor"}),
 ("psa",46,10,{"en":"still|know","es":"reconoc|sabed|quieto|callen|aquiet|reconozc|sepan|conozc","pt":"quietos|aquiet|parai|reconhec|saibam|conhec","la":"vacate|cessate|state|scitote"}),
 ("psa",51,1,{"en":"mercy","es":"miseric|piedad","pt":"miseric|piedade","la":"miserere|misericordiam"}),
 ("psa",100,3,{"en":"sheep","es":"oveja|rebano","pt":"ovelha|rebanho","la":"oves|pascuae"}),
 ("psa",104,30,{"en":"spirit|breath","es":"soplo|espiritu|aliento","pt":"espirito|sopro|folego","la":"spiritum|spiritus"}),
 ("psa",110,1,{"en":"right hand","es":"derecha|diestra","pt":"direita|destra","la":"dextris"}),
 ("psa",114,1,{"en":"egypt","es":"egipto","pt":"egito|egipto","la":"aegypto|aegyptus"}),
 ("psa",116,13,{"en":"cup of salvation|chalice","es":"caliz|copa","pt":"calice|calix|copo|taca","la":"calicem"}),
 ("psa",119,105,{"en":"lamp","es":"lampara|antorcha","pt":"lampada|lanterna|farol","la":"lucerna"}),
 ("psa",137,1,{"en":"babylon","es":"babilonia","pt":"babil","la":"babylon"}),
 ("psa",139,14,{"en":"wonderfully|fearfully|wonders","es":"maravill|prodigio|admirable|portento","pt":"maravilh|prodigi|admir","la":"mirabiliter|mirabilia"}),
 ("psa",147,12,{"en":"jerusalem","es":"jerusal","pt":"jerusal","la":"ierusalem|hierusalem"}),
 ("psa",150,6,{"en":"breath|spirit","es":"aliento|respira|viviente|cuanto vive","pt":"folego|respira|vivente","la":"spiritus|spirat|laudate"}),
 ("isa",9,6,{"en":"child is born","es":"nino|hijo","pt":"menino|filho","la":"parvulus|puer|natus"}),
 ("isa",53,5,{"en":"wounded|crushed","es":"herid|traspas|llagas|castigo|sanados|curados","pt":"ferid|transgress|trespass|chaga|castigo|curados","la":"vulnerat|attritus|livore"}),
 ("jer",31,31,{"en":"new covenant","es":"alianza nueva|nueva alianza|nuevo pacto|alianza","pt":"nova alianca|novo pacto|alianca nova|alianca","la":"foedus novum|pactum novum|testamentum novum"}),
 ("dan",3,25,{"en":"four men","es":"cuatro","pt":"quatro","la":"quattuor"}),
 ("dan",3,28,{"en":"shadrach|blessed be the god","es":"sidrac|sadrac|bendito sea el dios","pt":"sidrac|sadrac","la":"sidrac|sedrac"}),
 ("dan",4,1,{"en":"nebuchadnezzar|peace be multiplied","es":"nabucodonosor|paz|pueblos","pt":"nabucodonosor|paz|povos","la":"nabuchodonosor|pax|populis"}),
 ("dan",5,31,{"en":"darius","es":"dario","pt":"dario","la":"darius"}),
 ("dan",6,16,{"en":"den of lions|lions","es":"fosa|foso|leones","pt":"cova|covas|leoes","la":"lacum|leon"}),
 ("dan",7,13,{"en":"son of man","es":"hijo del hombre|hijo de hombre|hijo de un hombre","pt":"filho do homem|filho de homem|filho de um homem","la":"filius hominis"}),
 ("hos",1,10,{"en":"living god","es":"dios vivo|dios viviente|arena|hijos de israel","pt":"deus vivo|deus vivente","la":"dei viventis|deus vivens|dei vivi"}),
 ("joe",2,28,{"en":"pour out","es":"derramare|derramo","pt":"derramarei|derramo","la":"effundam"}),
 ("joe",2,32,{"en":"call on the name|call","es":"invoque|invocar","pt":"invocar|invocado","la":"invocaverit"}),
 ("joe",3,1,{"en":"captivity|reestablish|restore","es":"cautiv|restaur|suerte","pt":"cativeiro|restaur|sorte","la":"captivitat|convertero"}),
 ("jon",1,17,{"en":"fish","es":"pez|pescado|ballena|monstruo","pt":"peixe|baleia","la":"piscis|pisci|belua"}),
 ("mic",5,2,{"en":"bethlehem","es":"belen","pt":"belem","la":"bethleem|bethlehem"}),
 ("mal",3,1,{"en":"messenger|angel","es":"mensajero|enviado|angel","pt":"mensageiro|anjo|enviado","la":"angelum|nuntium"}),
 ("mal",3,6,{"en":"change","es":"cambi|inmutable","pt":"mudo|mud","la":"mutor|mutatus"}),
 ("mal",3,10,{"en":"tithes|tenth","es":"diezmo|decima","pt":"dizimo","la":"decimas|decimationem|decimam"}),
 ("mal",4,2,{"en":"sun of (righteousness|justice)","es":"sol de justicia","pt":"sol de justica|sol da justica","la":"sol iustitiae"}),
 ("mal",4,5,{"en":"elijah|elias","es":"elias","pt":"elias","la":"eliam"}),
 ("mat",5,3,{"en":"poor in spirit|poor","es":"pobre","pt":"pobres.*esp","la":"pauperes spiritu"}),
 ("joh",3,16,{"en":"so loved","es":"tanto am|asi amo|amo tanto","pt":"tanto amou|de tal modo|de tal forma","la":"sic dilexit|dilexit"}),
 ("rom",5,8,{"en":"commend|manifest","es":"constancia|mostro|evidencia|demuestra|prueba|muestra|manifiesta","pt":"prova|demonstra|mostra|manifesta","la":"commendat"}),
]
# Verses the bundled editions merge or split, where the target may be the neighbouring verse (documented limitation):
NEIGHBOUR_OK = {("psa", 150, 6)}  # Douay-Rheims 150:5 carries 150:5-6; Platense/Matos Soares keep six verses
fold = lambda s: "".join(c for c in unicodedata.normalize("NFD", s.lower()) if unicodedata.category(c) != "Mn")

def run(label, lang_code, m, text_of):
    """m: {(book,c,v): (c,v)}; text_of(book,c,v) -> str or ''. Returns failures."""
    bad = []
    for b, c, v, pats in CASES:
        t = m.get((b, c, v)); txt = fold(text_of(b, *t)) if t else ""
        if t and (b, c, v) in NEIGHBOUR_OK: txt += " " + fold(text_of(b, t[0], t[1] + 1))
        if not re.search(fold(pats[lang_code]), txt): bad.append((f"{b} {c}:{v}", t, txt[:70]))
    print(f"{label}: {len(CASES) - len(bad)}/{len(CASES)} pass")
    for x in bad: print("   FAIL KJV", x[0], "->", x[1], repr(x[2]))
    return bad

def bundled_text(l):
    B = lang(l); return lambda b, c, v: B[b][c - 1][v - 1] if b in B and c <= len(B[b]) and v <= len(B[b][c - 1]) else ""

def bundled_maps(K):
    import pickle, os
    pk = os.path.join(D, "maps.pkl")
    if os.path.exists(pk): return {l: inherit_orphans(dict(m), K) for l, m in pickle.load(open(pk, "rb")).items()}
    from align import english_map; from langmap import lang_map
    en = english_map(); E = lang("en")
    maps = {"en": en, "es": lang_map("es", K, E, en), "pt": lang_map("pt", K, E, en)}
    pickle.dump(maps, open(pk, "wb")); return {l: inherit_orphans(dict(m), K) for l, m in maps.items()}

if __name__ == "__main__":
    K = kjv(); arg = sys.argv[1] if len(sys.argv) > 1 else "bundled"; fails = 0
    if arg == "bundled":
        for l, m in bundled_maps(K).items(): fails += len(run("bundled " + l, l, m, bundled_text(l)))
    else:
        import json; from editions import load_edition, edition_map
        for ed in json.load(open(arg + "/manifest_cbt.json"))["editions"]:
            texts, exists = load_edition(arg, ed); m, _ = edition_map(K, texts, exists, english=ed.startswith("en.")); m = inherit_orphans(m, K)
            m = {k: t for k, t in m.items() if t[0] <= len(exists[k[0]]) and t[1] in exists[k[0]][t[0] - 1]}
            fails += len(run(ed, ed[:2], m, lambda b, c, v: texts[b][c - 1][v - 1] if c <= len(texts[b]) and v <= len(texts[b][c - 1]) else ""))
    sys.exit(1 if fails else 0)
