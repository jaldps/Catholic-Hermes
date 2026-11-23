# -*- coding: utf-8 -*-
"""Copy the app's already-available images for the 28 subjects that have one."""
import json, shutil, pathlib, sys
repo=pathlib.Path(__file__).resolve().parents[3]
base=repo/"app/src/main/assets"
data=pathlib.Path(__file__).resolve().parent
subjects=json.loads((data/"subjects.json").read_text(encoding="utf-8"))
out=base/"chaplets/images"; out.mkdir(parents=True, exist_ok=True)
crpath=out/"credits.json"
credits=json.loads(crpath.read_text(encoding="utf-8")) if crpath.exists() else {}
saints={e["id"]:e for e in json.loads((base/"saints/images/credits.json").read_text(encoding="utf-8"))["images"]}
devs={e["id"]:e for e in json.loads((base/"devotions/images/credits.json").read_text(encoding="utf-8"))["images"]}
only=set(sys.argv[1:])
n=0
for s in subjects:
    if not s["existing"]: continue
    if only and s["id"] not in only: continue
    e=saints.get(s["existing"]) or devs.get(s["existing"])
    area="saints" if s["existing"] in saints else "devotions"
    src=base/area/"images"/(s["existing"]+".jpg")
    if not src.exists():
        print("MISSING SOURCE",src); continue
    dst=out/(s["id"]+".jpg")
    shutil.copy2(src,dst)
    credits[s["id"]]={
      "id":s["id"],"title":e.get("title",""),"author":e.get("author",""),
      "license":e.get("license",""),"source":"Imagen ya presente en la app ("+area+")",
      "url":e.get("url",""),"referential":bool(e.get("referential",False)),
      "note":("Imagen reutilizada de "+area+". "+e.get("note","")+" "+s.get("note","")).strip(),
    }
    n+=1
crpath.write_text(json.dumps(credits,ensure_ascii=False,indent=2)+"\n",encoding="utf-8")
print(n,"images copied;",len(credits),"credit entries")
