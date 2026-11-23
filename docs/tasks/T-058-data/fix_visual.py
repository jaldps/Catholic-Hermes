# -*- coding: utf-8 -*-
"""Targeted replacements after visual review."""
import json, pathlib, re, urllib.parse, urllib.request, io, time
from PIL import Image
HERE=pathlib.Path(__file__).resolve().parent
repo=HERE.parents[2]
out=repo/"app/src/main/assets/chaplets/images"
UA={"User-Agent":"HermesAgent/1.0 (T-058)"}
API="https://commons.wikimedia.org/w/api.php"

def meta(title):
    while title.startswith("File:"): title=title[5:]
    u=API+"?"+urllib.parse.urlencode({"action":"query","format":"json","titles":"File:"+title,
       "prop":"imageinfo","iiprop":"url|size|extmetadata","iiurlwidth":"1200"})
    d=json.load(urllib.request.urlopen(urllib.request.Request(u,headers=UA),timeout=45))
    for p in d.get("query",{}).get("pages",{}).values():
        ii=(p.get("imageinfo") or [{}])[0]
        m=ii.get("extmetadata",{})
        cl=lambda k: re.sub(r"<[^>]+>","",(m.get(k,{}) or {}).get("value","")).strip()
        return {"title":p.get("title",""),"url":ii.get("thumburl") or ii.get("url"),
                "license":cl("LicenseShortName"),"author":cl("Artist")[:120],
                "desc":cl("ImageDescription")[:200]}
    return None

cands=json.loads((HERE/"candidates.json").read_text(encoding="utf-8"))
picks={
 "estrella-mar-coronilla": meta("Netherlands-4946 - Our Lady - Star of the Sea (12568057675a) (cropped).jpg"),
 "rosario-espiritu-santo": cands["rosario-espiritu-santo"]["picked"],
 "san-rafael-coronilla": cands["san-rafael-coronilla"]["picked"],
}
# lourdes: search and take a close statue candidate
u=API+"?"+urllib.parse.urlencode({"action":"query","format":"json","generator":"search",
  "gsrsearch":"File: Our Lady of Lourdes statue close up","gsrnamespace":"6","gsrlimit":"15",
  "prop":"imageinfo","iiprop":"url|extmetadata","iiurlwidth":"1200"})
d=json.load(urllib.request.urlopen(urllib.request.Request(u,headers=UA),timeout=45))
for p in d.get("query",{}).get("pages",{}).values():
    t=p["title"]
    if "statue" in t.lower() or "estatua" in t.lower():
        m=meta(t)
        if m: picks["lourdes-rosario"]=m; break

cred=json.loads((out/"credits.json").read_text(encoding="utf-8"))
for cid,m in picks.items():
    if not m: print("SIN META",cid); continue
    raw=None
    for url in [m["url"],"https://commons.wikimedia.org/wiki/Special:FilePath/"+urllib.parse.quote(m["title"].replace("File:",""))+"?width=1280"]:
        try:
            raw=urllib.request.urlopen(urllib.request.Request(url,headers=UA),timeout=60).read(); break
        except Exception as e: print(" retry",cid,str(e)[:60]); time.sleep(2)
    if not raw: print("SIN BYTES",cid); continue
    im=Image.open(io.BytesIO(raw)).convert("RGB"); im.thumbnail((1200,1200),Image.LANCZOS)
    im.save(out/(cid+".jpg"),"JPEG",quality=88,optimize=True)
    cred[cid]={"id":cid,"title":m["title"].replace("File:",""),"author":m["author"],
      "license":m["license"],"source":"Wikimedia Commons",
      "url":"https://commons.wikimedia.org/wiki/"+urllib.parse.quote(m["title"].replace(" ","_")),
      "referential":False,"note":"Seleccionada tras revisión visual: primer plano del sujeto."}
    print("FIXED",cid,m["title"][:70],flush=True)
    time.sleep(1.5)
(out/"credits.json").write_text(json.dumps(cred,ensure_ascii=False,indent=2)+"\n",encoding="utf-8")
