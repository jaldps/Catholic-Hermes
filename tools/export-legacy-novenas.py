#!/usr/bin/env python3
"""One-time legacy content migration. Original runtime equivalence was checked before
removing generated classes; evidence is in docs/quality/audit-remediation/stage-3a.
Use --revision 0fd57a1 and an empty --output directory to reproduce the input export.
Do not overwrite the app's curated metadata with this legacy export.
"""
from pathlib import Path
import re,json,hashlib,argparse,subprocess
args=argparse.ArgumentParser(description="Export legacy novena Kotlin values without executing source")
args.add_argument('--revision', required=True)
args.add_argument('--output', required=True, type=Path)
options=args.parse_args()
assert re.fullmatch(r'[a-f0-9]{7,40}',options.revision), 'Use an immutable Git revision'
repo=Path(__file__).resolve().parent.parent;dest=options.output
assert not dest.exists() or not any(dest.iterdir()), 'Choose an empty output directory'
dest.mkdir(parents=True,exist_ok=True)
def original(name):
 return subprocess.check_output(['git','show',f'{options.revision}:app/src/main/java/com/example/data/repository/{name}'],cwd=repo,text=True)

pattern=re.compile(r'\s+|//[^\n]*|/\*.*?\*/|""".*?"""|"(?:\\.|[^"\\])*"|[A-Za-z_]\w*|\d+|[(),=]',re.S)
class Parser:
 def __init__(self,s):
  self.t=[];last=0
  for m in pattern.finditer(s):
   assert not s[last:m.start()].strip(),repr(s[last:m.start()][:80]);last=m.end()
   v=m[0]
   if not v.isspace() and not v.startswith(('//','/*')):self.t.append(v)
  assert not s[last:].strip();self.i=0
 def take(self):v=self.t[self.i];self.i+=1;return v
 def expect(self,v):assert self.take()==v
 def value(self):
  v=self.take()
  if v.startswith('"""'):
   assert '${' not in v,'Kotlin interpolation needs explicit review'
   return v[3:-3]
  if v.startswith('"'):return json.loads(v)
  if v.isdecimal():return int(v)
  if v in ['true','false','null']:return {'true':True,'false':False,'null':None}[v]
  assert v in ['Novena','NovenaDay','LocalizedText','listOf','emptyList'],v
  self.expect('(');kwargs={};args=[]
  while self.t[self.i]!=')':
   if self.i+1<len(self.t) and self.t[self.i+1]=='=':
    key=self.take();self.expect('=');kwargs[key]=self.value()
   else:args.append(self.value())
   if self.t[self.i]==',':self.take()
   else:assert self.t[self.i]==')',self.t[self.i:self.i+3]
  self.expect(')')
  if v in ['listOf','emptyList']:assert not kwargs;return args
  if v=='LocalizedText':assert len(args)==3 and not kwargs;return dict(zip(['es','en','pt'],args))
  assert not args
  return kwargs
names=re.findall(r'\b(NovenaData\d+)\.novena',original('CrawledNovenasData.kt'))
assert len(names)==246
manifest=[];index=[]
for n,name in enumerate(names):
 s=original(f'{name}.kt');start=s.index('Novena(',s.index('val novena'))
 # Locate the Novena expression with the tokenizer and stop before the object's final brace.
 fragment=s[start:s.rfind('\n    )')+6].strip()
 parsed=Parser(fragment).value();parsed.setdefault('imageUrl',None);parsed.setdefault('tags',[])
 expected={'id','title','patron','category','description','miraculousHistory','promises','days','imageUrl','tags'}
 assert set(parsed)==expected,(name,set(parsed))
 numbers=[d['dayNumber'] for d in parsed['days']];assert len(numbers)==len(set(numbers)) and all(x>0 for x in numbers)
 text=json.dumps(parsed,ensure_ascii=False,sort_keys=True,separators=(',',':'))
 (dest/f'{n:03d}-full.json').write_text(text)
 (dest/f'{n:03d}.json').write_text(json.dumps({'days':parsed['days']},ensure_ascii=False,separators=(',',':')))
 meta={k:v for k,v in parsed.items() if k!='days'};meta.update({'bodyFile':f'{n:03d}.json','dayNumbers':numbers});index.append(meta)
 manifest.append({'sourceClass':name,'id':parsed['id'],'dayCount':len(numbers),'sha256':hashlib.sha256(text.encode()).hexdigest()})
(dest/'index.json').write_text(json.dumps(index,ensure_ascii=False,separators=(',',':')))
(dest/'content-hashes.json').write_text(json.dumps(manifest,ensure_ascii=False,indent=2))
print({'novenas':len(index),'days':sum(len(x['dayNumbers']) for x in index),'index_bytes':(dest/'index.json').stat().st_size,'body_bytes':sum((dest/f'{n:03d}.json').stat().st_size for n in range(len(index)))})
