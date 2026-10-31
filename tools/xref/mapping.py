import sys; sys.path.insert(0, __file__.rsplit('/',1)[0])
from common import *

def psalm_map(K, L):
    """KJV (hebrew) psalm,verse -> target (vulgate) psalm,verse."""
    kp=[len(c) for c in K['psa']]; lp=[len(c) for c in L['psa']]
    H=lambda n: kp[n-1]; V=lambda n: lp[n-1]
    m={}
    def one(h, vg, start=0, first=1, last=None):
        # map hebrew psalm h verses first..last to vulgate psalm vg, beginning after `start` verses
        last = last or H(h)
        for v in range(first, last+1):
            m[(h, v)] = (vg, v - first + 1 + start)
    def whole(h, vg):
        t = V(vg) - H(h)
        one(h, vg, start=t if 0 <= t <= 2 else 0)
    for h in range(1, 151):
        if h <= 8 or h >= 148: whole(h, h)
        elif 11 <= h <= 113: whole(h, h-1)
        elif 117 <= h <= 146: whole(h, h-1)
    # 9+10 -> 9
    t = V(9) - (H(9)+H(10)); t = t if 0 <= t <= 2 else 0
    one(9, 9, start=t); one(10, 9, start=t+H(9))
    # 114+115 -> 113
    t = V(113) - (H(114)+H(115)); t = t if 0 <= t <= 2 else 0
    one(114, 113, start=t); one(115, 113, start=t+H(114))
    # 116 -> 114 (1-9), 115 (10-19)
    t1 = V(114) - 9; t1 = t1 if 0 <= t1 <= 2 else 0
    t2 = V(115) - 10; t2 = t2 if 0 <= t2 <= 2 else 0
    one(116, 114, start=t1, first=1, last=9); one(116, 115, start=t2, first=10, last=19)
    # 147 -> 146 (1-11), 147 (12-20)
    t1 = V(146) - 11; t1 = t1 if 0 <= t1 <= 2 else 0
    t2 = V(147) - 9; t2 = t2 if 0 <= t2 <= 2 else 0
    one(147, 146, start=t1, first=1, last=11); one(147, 147, start=t2, first=12, last=20)
    return m

def build(K, L):
    """Returns dict (book, kch, kv) -> (ch, v) in L's numbering, only for verses that exist in L."""
    out={}
    for b in OURS:
        kc=[len(c) for c in K[b]]; lc=[len(c) for c in L[b]]
        if b=='psa':
            for (h,v),(c,lv) in psalm_map(K,L).items():
                if c<=len(lc) and 1<=lv<=lc[c-1]: out[(b,h,v)]=(c,lv)
            continue
        if b=='dan':
            for c,n in enumerate(kc,1):
                for v in range(1,n+1):
                    if c==3 and v>=24: t=(3, v+67)
                    elif c==4 and v<=3: t=(3, 97+v)
                    elif c==4: t=(4, v-3)
                    else: t=(c,v)
                    if t[0]<=len(lc) and t[1]<=lc[t[0]-1]: out[(b,c,v)]=t
            continue
        if kc==lc or sum(kc)!=sum(lc):
            for c,n in enumerate(kc,1):
                for v in range(1,n+1):
                    if c<=len(lc) and v<=lc[c-1]: out[(b,c,v)]=(c,v)
        else:
            # same verses, different chapter breaks: align by position in the book
            flat=[(c,v) for c,n in enumerate(lc,1) for v in range(1,n+1)]
            i=0
            for c,n in enumerate(kc,1):
                for v in range(1,n+1):
                    out[(b,c,v)]=flat[i]; i+=1
    return out
