# -*- coding: utf-8 -*-
"""Search Commons for the 27 subjects without an existing app image."""
import json, pathlib, re, time, urllib.parse, urllib.request, sys

HERE=pathlib.Path(__file__).resolve().parent
subj=[s for s in json.loads((HERE/"subjects.json").read_text(encoding="utf-8")) if not s["existing"]]
if len(sys.argv)>1: subj=[s for s in subj if s["id"] in set(sys.argv[1:])]
UA={"User-Agent":"HermesAgent/1.0 (CatholicGPT T-058 images; contact hermes@users.noreply.invalid)"}
API="https://commons.wikimedia.org/w/api.php"
NEG=("procession","procesion","church","iglesia","cathedral","catedral","basilica","facade",
     "fachada","street","calle","plaza","crowd","multitud","festival","fiesta","school",
     "hospital","tower","campanile","cemetery","cementerio","map","pdf","djvu","webm","svg","logo")
POS=("statue","estatua","sculpture","escultura","painting","pintura","oil","oleo","icon","icono",
     "retablo","altar","talla","portrait","retrato","drawing","dibujo","engraving","grabado","mosaic","vitral")

def get(params, tries=4):
    url=API+"?"+urllib.parse.urlencode(params)
    for i in range(tries):
        try:
            with urllib.request.urlopen(urllib.request.Request(url,headers=UA),timeout=45) as r:
                return json.load(r)
        except Exception as e:
            if i==tries-1: print("API FAIL",e); return {}
            time.sleep(8*(i+1))
    return {}

def clean(s): return re.sub(r"<[^>]+>","",s or "").strip()

def search(q):
    d=get({"action":"query","format":"json","generator":"search","gsrsearch":"File: "+q,
           "gsrnamespace":"6","gsrlimit":"20","prop":"imageinfo","iiprop":"url|size|mime|extmetadata",
           "iiurlwidth":"1000"})
    out=[]
    for p in d.get("query",{}).get("pages",{}).values():
        ii=(p.get("imageinfo") or [{}])[0]
        m=ii.get("extmetadata",{}) or {}
        lic=clean((m.get("LicenseShortName",{}) or {}).get("value",""))
        out.append({"title":p.get("title",""),"url":ii.get("thumburl") or ii.get("url"),
                    "full":ii.get("url"),"mime":ii.get("mime",""),
                    "license":lic,"author":clean((m.get("Artist",{}) or {}).get("value",""))[:120],
                    "desc":clean((m.get("ImageDescription",{}) or {}).get("value",""))[:300]})
    return out

results={}
for k,s in enumerate(subj):
    cands=[]
    for q in s["queries"]+[s["title"]]:
        cands += search(q); time.sleep(1.4)
    seen=set(); good=[]
    aliases=[a.lower() for a in s["aliases"]]
    for c in cands:
        if c["title"] in seen: continue
        seen.add(c["title"])
        blob=(c["title"]+" "+c["desc"]).lower()
        ext=c["title"].lower().rsplit(".",1)[-1]
        if ext not in ("jpg","jpeg","png","tif","tiff","webp"): continue
        if not any(a in blob for a in aliases): continue
        if any(n in blob for n in NEG): continue
        lic=c["license"].lower()
        if not (lic.startswith("cc by") or lic.startswith("cc0") or "public domain" in lic or lic.startswith("pd") or "cc by-sa" in lic): continue
        score=sum(3 for p in POS if p in blob)
        score+=sum(1 for a in aliases if a in blob)
        c["score"]=score
        good.append(c)
    good.sort(key=lambda x:-x["score"])
    results[s["id"]]={"title":s["title"],"picked":good[0] if good else None,
                      "alternatives":good[1:4],"n_candidates":len(good)}
    print(s["id"], "->", (good[0]["title"][:70] if good else "SIN CANDIDATO"), flush=True)
cf=HERE/"candidates.json"
merged=json.loads(cf.read_text(encoding="utf-8")) if cf.exists() else {}
merged.update(results)
cf.write_text(json.dumps(merged,ensure_ascii=False,indent=2)+"\n",encoding="utf-8")
print("saved",len(results))
