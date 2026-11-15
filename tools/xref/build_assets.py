"""Builds xref_<id>.bin from OpenBible.info cross-references (CC BY 4.0), re-keyed from KJV versification.

  python3 tools/xref/build_assets.py                      bundled xref_{en,es,pt}.bin into app/src/main/assets/xref
  python3 tools/xref/build_assets.py --editions DIR OUT   xref_<edition>.bin for each edition converted into DIR by
                                                          tools/bibles/convert_cbt.py (written to OUT, never to the APK)
"""
import sys,pickle,struct,os,collections; sys.path.insert(0, __file__.rsplit('/',1)[0])
from common import *
CANON="gen exo lev num deu jos jud rut 1sa 2sa 1ki 2ki 1ch 2ch ezr neh tob jdt est 1ma 2ma job psa pro ecc sng wis sir isa jer lam bar eze dan hos joe amo oba jon mic nah hab zep hag zec mal mat mar luk joh act rom 1co 2co gal eph phi col 1th 2th 1ti 2ti tit phm heb jam 1pe 2pe 1jo 2jo 3jo jde rev".split()
IDX={b:i for i,b in enumerate(CANON)}
def parse(r):
    b,c,v=r.split('.'); return (O2U[b],int(c),int(v))
def load_keep():
    rows=[]
    for i,line in enumerate(open(D+"/dl/cross_references.txt")):
        if i==0: continue
        f,t,votes=line.rstrip('\n').split('\t'); votes=int(votes)
        if votes<2: continue
        ts,_,te=t.partition('-')
        rows.append((parse(f),parse(ts),parse(te) if te else None,votes))
    by=collections.defaultdict(list)
    for r in rows: by[r[0]].append(r)
    keep=[]
    for f,rs in by.items():
        rs.sort(key=lambda r:-r[3])
        k=[r for r in rs if r[3]>=5][:30]
        if len(k)<3: k=rs[:3]
        keep+=k
    print('kept',len(keep),'verses',len(by))
    return keep
enc=lambda b,c,v:(IDX[b]<<16)|(c<<8)|v
def write_bin(m, keep, path):
    recs=[]
    for f,ts,te,votes in keep:
        mf=m.get(f); ms=m.get(ts)
        if not mf or not ms: continue
        me=m.get(te) if te else None
        if te and me and (me<ms if te[0]==ts[0] else True): me=None
        a=enc(f[0],*mf); s=enc(ts[0],*ms); e=enc(ts[0],*me) if me else s
        recs.append((a,-votes,s,e,votes))
    recs.sort()
    with open(path,"wb") as fh:
        fh.write(b"XRF1"); fh.write(struct.pack(">i",len(recs)))
        for col in (0,2,3): fh.write(struct.pack(f">{len(recs)}i",*[r[col] for r in recs]))
        fh.write(struct.pack(f">{len(recs)}h",*[min(r[4],32767) for r in recs]))
    print(os.path.basename(path),len(recs),os.path.getsize(path))
def bundled():
    keep=load_keep(); K=kjv(); maps={l:inherit_orphans(dict(m),K) for l,m in pickle.load(open(D+'/maps.pkl','rb')).items()}
    out=os.path.join(ROOT, "app/src/main/assets/xref"); os.makedirs(out,exist_ok=True)
    for l,m in maps.items(): write_bin(m,keep,f"{out}/xref_{l}.bin")
def editions(root,out):
    import json
    from editions import load_edition, edition_map
    keep=load_keep(); K=kjv(); os.makedirs(out,exist_ok=True)
    for ed in json.load(open(root+"/manifest_cbt.json"))["editions"]:
        texts,exists=load_edition(root,ed)
        m,dropped=edition_map(K,texts,exists,english=ed.startswith("en."))
        m=inherit_orphans(m,K)
        m={k:t for k,t in m.items() if t[0]<=len(exists[k[0]]) and t[1] in exists[k[0]][t[0]-1]}
        print(ed,"mapped",len(m),"KJV verses dropped (absent from edition)",dropped)
        write_bin(m,keep,f"{out}/xref_{ed}.bin")
if __name__=="__main__":
    if len(sys.argv)==4 and sys.argv[1]=="--editions": editions(sys.argv[2],sys.argv[3])
    else: bundled()
