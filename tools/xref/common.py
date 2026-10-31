import json, os
ROOT=os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
A=os.path.join(ROOT, "app/src/main/assets/bible")
# Working dir holding dl/cross_references.txt (OpenBible.info) and dl/kjv.json.
D=os.environ.get("XREF_WORK", os.path.join(ROOT, "build/xref"))
OSIS="Gen Exod Lev Num Deut Josh Judg Ruth 1Sam 2Sam 1Kgs 2Kgs 1Chr 2Chr Ezra Neh Esth Job Ps Prov Eccl Song Isa Jer Lam Ezek Dan Hos Joel Amos Obad Jonah Mic Nah Hab Zeph Hag Zech Mal Matt Mark Luke John Acts Rom 1Cor 2Cor Gal Eph Phil Col 1Thess 2Thess 1Tim 2Tim Titus Phlm Heb Jas 1Pet 2Pet 1John 2John 3John Jude Rev".split()
OURS="gen exo lev num deu jos jud rut 1sa 2sa 1ki 2ki 1ch 2ch ezr neh est job psa pro ecc sng isa jer lam eze dan hos joe amo oba jon mic nah hab zep hag zec mal mat mar luk joh act rom 1co 2co gal eph phi col 1th 2th 1ti 2ti tit phm heb jam 1pe 2pe 1jo 2jo 3jo jde rev".split()
O2U=dict(zip(OSIS,OURS))
def kjv():
    k=json.load(open(D+"/dl/kjv.json",encoding="utf-8-sig"))
    return {OURS[i]:[ch for ch in b["chapters"]] for i,b in enumerate(k)}
def lang(l):
    d=json.load(open(f"{A}/bible_{l}.json"))
    return {b:[d[b][str(c)] for c in range(1,len(d[b])+1)] for b in d}
