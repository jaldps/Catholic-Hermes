import sys,pickle; sys.path.insert(0, __file__.rsplit('/',1)[0])
from common import *
def lang_map(l, K, E, enmap):
    if l=='en': return enmap
    L=lang(l); out={}
    for b in OURS:
        kc=[len(c) for c in K[b]]; ec=[len(c) for c in E[b]]; lc=[len(c) for c in L[b]]
        fixed={}
        # runs of chapters where L differs from EN
        bad=[i for i in range(len(lc)) if i>=len(ec) or ec[i]!=lc[i]]
        handled=set()
        if b!='psa':
            i=0
            while i<len(bad):
                j=i
                while j+1<len(bad) and bad[j+1]==bad[j]+1: j+=1
                run=list(range(bad[i],bad[j]+1))
                ktot=sum(kc[c] for c in run if c<len(kc)); ltot=sum(lc[c] for c in run)
                if all(c<len(kc) and kc[c]==lc[c] for c in run):
                    for c in run:
                        for v in range(1,kc[c]+1): fixed[(b,c+1,v)]=(c+1,v)
                    handled.update(run)
                elif ktot==ltot and all(c<len(kc) for c in run):
                    flat=[(c+1,v) for c in run for v in range(1,lc[c]+1)]; k=0
                    for c in run:
                        for v in range(1,kc[c]+1): fixed[(b,c+1,v)]=flat[k]; k+=1
                    handled.update(run)
                i=j+1
        for key,(c,v) in enmap.items():
            if key[0]!=b: continue
            if key in fixed: continue
            if c-1 in handled: continue
            if b=='psa' and c<=len(lc) and c<=len(ec):
                d=lc[c-1]-ec[c-1]
                if d in (1,2): v=v+d
            if c<=len(lc) and 1<=v<=lc[c-1]: out[key]=(c,v)
        for key,t in fixed.items():
            if t[0]<=len(lc) and t[1]<=lc[t[0]-1]: out[key]=t
    return out
