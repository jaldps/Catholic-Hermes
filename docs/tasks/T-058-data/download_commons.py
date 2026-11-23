# -*- coding: utf-8 -*-
"""Download the 24 Commons picks as JPG <=1200px and merge their credits."""
import json, pathlib, re, time, urllib.parse, urllib.request, io, sys
from PIL import Image

HERE=pathlib.Path(__file__).resolve().parent
repo=HERE.parents[2]
out=repo/"app/src/main/assets/chaplets/images"
out.mkdir(parents=True,exist_ok=True)
cands=json.loads((HERE/"candidates.json").read_text(encoding="utf-8"))
subj={s["id"]:s for s in json.loads((HERE/"subjects.json").read_text(encoding="utf-8"))}
crpath=out/"credits.json"
credits=json.loads(crpath.read_text(encoding="utf-8")) if crpath.exists() else {}
UA={"User-Agent":"HermesAgent/1.0 (CatholicGPT T-058 images; contact hermes@users.noreply.invalid)"}

def fetch(url):
    for u in [url]:
        try:
            with urllib.request.urlopen(urllib.request.Request(u,headers=UA),timeout=60) as r:
                return r.read()
        except Exception as e:
            print("  fail",str(e)[:80])
    return None

only=set(sys.argv[1:])
n=0
for cid,c in cands.items():
    if only and cid not in only: continue
    p=c.get("picked")
    if not p:
        print("SIN PICK",cid); continue
    dst=out/(cid+".jpg")
    if not dst.exists():
        raw=None
        title=p["title"].replace("File:","")
        urls=[p["url"]]
        urls.append("https://commons.wikimedia.org/wiki/Special:FilePath/"+urllib.parse.quote(title)+"?width=1280")
        for u in urls:
            raw=fetch(u)
            if raw: break
            time.sleep(2)
        if not raw:
            print("SIN BYTES",cid,title); continue
        try:
            im=Image.open(io.BytesIO(raw)).convert("RGB")
            im.thumbnail((1200,1200),Image.LANCZOS)
            im.save(dst,"JPEG",quality=88,optimize=True)
        except Exception as e:
            print("BAD IMAGE",cid,e); continue
    note=subj[cid].get("note","")
    credits[cid]={
      "id":cid,"title":p["title"].replace("File:",""),"author":p.get("author","?"),
      "license":p.get("license","?"),"source":"Wikimedia Commons",
      "url":"https://commons.wikimedia.org/wiki/"+urllib.parse.quote(p["title"].replace(" ","_")),
      "referential":"referencial" in note.lower(),
      "note":note or "Imagen del sujeto concreto.",
    }
    n+=1
    print("OK",cid,p["title"][:60],flush=True)
    time.sleep(1.3)
crpath.write_text(json.dumps(credits,ensure_ascii=False,indent=2)+"\n",encoding="utf-8")
print(n,"downloaded;",len(credits),"credit entries")
