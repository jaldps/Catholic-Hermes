import sys,pickle; sys.path.insert(0, __file__.rsplit('/',1)[0])
from common import *
import math
def gc_align(a, b, titles=False):
    """Gale-Church length alignment of chapter verse lists a -> b: {a verse (1-based): b verse (1-based)}.
    titles: psalms - also try treating the first 1-2 surplus verses of b as titles missing from a, keep the cheaper.
    Verses of b shorter than 12 characters ("Dijo:") are merged into the next one for the alignment and map to it."""
    groups=[]
    for i,t in enumerate(b):
        if groups and len(b[groups[-1][-1]])<12: groups[-1].append(i)
        else: groups.append([i])
    merged=["".join(b[i] for i in g) for g in groups]
    def solve(m):
        best=(_gc(a,m),0)
        for d in ((len(b)-len(a),) if titles and 1<=len(b)-len(a)<=2 else ()):
            if d<len(m):
                alt=_gc(a,m[d:])
                if alt[1]+0.7*d<best[0][1]: best=(alt,d)
        return {k:v+best[1] for k,v in best[0][0].items()}
    return {k:groups[j-1][-1]+1 for k,j in solve(merged).items() if 1<=j<=len(groups)}
def _gc(a,b):
    """Gale-Church length alignment of two verse lists (texts). Returns {a verse (1-based): b verse} for one chapter.
    Beads 1-1, 1-0, 0-1, 2-1, 1-2, 2-2; verses lost to a 1-0 bead map to the preceding target verse."""
    la=[len(x) for x in a]; lb=[len(x) for x in b]; n,m=len(la),len(lb)
    c=max(sum(lb),1)/max(sum(la),1); s2=6.8
    prior={(1,1):0.89,(1,0):0.0099,(0,1):0.0099,(2,1):0.089,(1,2):0.089,(2,2):0.011}
    def cost(i,j,di,dj):
        l1=sum(la[i:i+di]); l2=sum(lb[j:j+dj])
        mean=max((l1+l2/c)/2,20.0); z=abs(l2-l1*c)/math.sqrt(mean*s2)
        p=prior[(di,dj)]
        return -math.log(max(math.erfc(z/math.sqrt(2)),1e-12))-math.log(p)
    INF=1e18; best=[[INF]*(m+1) for _ in range(n+1)]; back=[[None]*(m+1) for _ in range(n+1)]; best[0][0]=0.0
    for i in range(n+1):
        for j in range(m+1):
            if best[i][j]>=INF: continue
            for (di,dj) in prior:
                if i+di>n or j+dj>m: continue
                v=best[i][j]+cost(i,j,di,dj)
                if v<best[i+di][j+dj]: best[i+di][j+dj]=v; back[i+di][j+dj]=(di,dj)
    out={}; i,j=n,m
    while i>0 or j>0:
        di,dj=back[i][j]; i0,j0=i-di,j-dj
        for k in range(di): out[i0+k+1]=max(j0+1,1) if dj>0 else max(j0,1)
        i,j=i0,j0
    return out,best[n][m]
def lang_map(l, K, E, enmap):
    if l=='en': return enmap
    L=lang(l); out={}; galign={}
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
                if len(run)<2: pass  # one chapter: the English alignment plus Gale-Church places its verses
                elif all(c<len(kc) and kc[c]==lc[c] for c in run):
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
            if key[1]-1 in handled: continue
            if c<=len(lc) and c<=len(ec) and lc[c-1]!=ec[c-1]:
                ga=galign.get((b,c))
                if ga is None: ga=galign[(b,c)]=gc_align(E[b][c-1],L[b][c-1],titles=(b=='psa'))
                v=ga.get(v)
                if v is None: continue
            if c<=len(lc) and 1<=v<=lc[c-1]: out[key]=(c,v)
        for key,t in fixed.items():
            if t[0]<=len(lc) and t[1]<=lc[t[0]-1]: out[key]=t
    return out
