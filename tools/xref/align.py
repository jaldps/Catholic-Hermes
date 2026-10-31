import sys,re; sys.path.insert(0, __file__.rsplit('/',1)[0])
from common import *
from mapping import build
STOP=set("that with unto they them their there have shall will which from this were your when then also upon said into thou thee thy hath",)
def words(s): return frozenset(w for w in re.findall(r"[a-z]+",s.lower()) if len(w)>3 and w not in STOP)
def align_book(K, L, b, prior, band=80):
    A=[(c,v,words(t)) for c,ch in enumerate(K[b],1) for v,t in enumerate(ch,1)]
    B=[(c,v,words(t)) for c,ch in enumerate(L[b],1) for v,t in enumerate(ch,1)]
    n,m=len(A),len(B)
    # expected diagonal from the prior mapping, for banding
    pos={ (c,v):i for i,(c,v,_) in enumerate(B)}
    exp=[pos.get(prior.get((b,c,v)), None) for c,v,_ in A]
    last=0
    for i in range(n):
        if exp[i] is None: exp[i]=last
        last=exp[i]
    NEG=-1e9
    score=[dict() for _ in range(n+1)]; back=[dict() for _ in range(n+1)]
    score[0][0]=0.0
    for i in range(n+1):
        lo=0 if i==0 else max(0,exp[min(i,n-1)]-band); hi=m if i==0 else min(m,exp[min(i,n-1)]+band)
        if i==0: lo,hi=0,min(m,band)
        for j in range(lo,hi+1):
            if i==0 and j==0: continue
            best=NEG;arg=None
            if i>0 and j>0 and (j-1) in score[i-1]:
                a=A[i-1][2]; bb=B[j-1][2]
                s=len(a&bb)/max(1,len(a|bb))
                cand=score[i-1][j-1]+s+0.15
                if cand>best: best,arg=cand,'d'
            if i>0 and j in score[i-1]:
                cand=score[i-1][j]-0.05
                if cand>best: best,arg=cand,'u'
            if j>0 and (j-1) in score[i]:
                cand=score[i][j-1]-0.05
                if cand>best: best,arg=cand,'l'
            if arg: score[i][j]=best; back[i][j]=arg
    i,j=n,max(score[n], key=lambda k: score[n][k] - 0.05*(m-k))
    out={}
    while i>0 or j>0:
        a=back[i].get(j)
        if a=='d': out[(b,A[i-1][0],A[i-1][1])]=(B[j-1][0],B[j-1][1]); i-=1;j-=1
        elif a=='u': i-=1
        elif a=='l': j-=1
        else: break
    return out
def english_map():
    K=kjv(); L=lang('en'); prior=build(K,L); out={}
    for b in OURS:
        kc=[len(c) for c in K[b]]; lc=[len(c) for c in L[b]]
        if kc==lc:
            out.update({k:v for k,v in prior.items() if k[0]==b})
        else:
            out.update(align_book(K,L,b,prior))
    return out
