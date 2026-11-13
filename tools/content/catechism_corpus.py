#!/usr/bin/env python3
"""Reproduce and verify the Vatican CCC import; never substitute another language.

Requires Python 3 and lxml only for --rebuild. --verify uses the standard library.
Source HTML is cached outside git. No authoritative prose is generated or translated.
"""
from __future__ import annotations
from pathlib import Path
import argparse
import hashlib
import sys
import urllib.request
import urllib.parse
from concurrent.futures import ThreadPoolExecutor

REPO = Path(__file__).resolve().parents[2]
DATA = REPO / "app/src/main/java/com/example/data/repository"
PROVENANCE = REPO / "docs/content/catechism-provenance.json"
BASE = {
    "en": "https://www.vatican.va/archive/ENG0015/",
    "es": "https://www.vatican.va/archive/catechism_sp/",
    "pt": "https://www.vatican.va/archive/cathechism_po/index_new/",
}
INDEX = {"en": "_INDEX.HTM", "es": "index_sp.html", "pt": "prima-pagina-cic_po.html"}
LANGS = ("es", "en", "pt")

def digest(data):
    return hashlib.sha256(data).hexdigest()

def corpus_digest(values):
    return digest(json.dumps(values, ensure_ascii=False, sort_keys=True, separators=(",", ":")).encode())

import re,json
SPACE=re.compile(r'\s+')
def text(el):return SPACE.sub(' ',el.text_content()).strip()
# Number typos in the official Portuguese HTML; neighbouring markers and the
# parallel official Spanish/English paragraphs independently establish these IDs.
REN={'pt-p3s2cap2_2196-2557_po.html':{2117:2217,1439:2439}}
def extract(path,lang):
 from lxml import html
 tree=html.fromstring(path.read_bytes());events=[]
 # Promote unformatted initial markers, including EN italic summary paragraphs.
 for p in tree.xpath('//p'):
  if lang=='pt':continue
  full=text(p)
  regex=r'^(\d{1,4})\s+' if lang=='en' else r'^(\d{1,4})(?:[.]?\s+)'
  m=re.match(regex,full)
  if not m:continue
  num=int(m[1])
  if lang=='es' and num<26 and path.name!='es-prologue_sp.html':continue
  # Never promote the numbered Decalogue lists or footnote backlink blocks.
  if p.xpath('.//a[starts-with(@name,"$")]'):continue
  if any(re.fullmatch(r'\d{1,4}\s*[.]?',text(b)) for b in p.xpath('./b|./strong')):continue
  for node in p.iter():
   if node.text and node.text.strip():
    prefix=re.match(r'^\s*'+str(num)+r'[.]?\s*',node.text)
    if prefix:
     node.text=node.text[prefix.end():]
     mark=html.Element('ccc');mark.set('number',str(num));p.insert(0,mark)
     if node is p:mark.tail=p.text;p.text=None
    break
 # Generate a stream rather than relying on <p> boundaries: Vatican HTML has
 # bare bold paragraph numbers, inline paragraph starts, and malformed blocks.
 def walk(el):
  tag=el.tag if isinstance(el.tag,str) else ''
  if not tag or tag in ('head','script','style'):return
  t=text(el)
  if tag=='ccc':events.append(('marker',int(el.get('number'))));return
  # All centered blocks are headings/navigation. Font-only bare headings are
  # captured below by all-uppercase standalone text.
  marker=re.fullmatch(r'(\d{1,4})\s*\.?',t) if tag in ('b','strong') else None
  if marker and not el.xpath('.//a[starts-with(@name,"$")]'):
   n=int(marker[1]);n=REN.get(path.name,{}).get(n,n)
   events.append(('marker',n));return
  if tag in ('b','strong') and not el.xpath('ancestor::p') and t and not marker:
   events.append(('skip',None));return
  if tag=='hr':events.append(('stop',None));return
  if tag=='p':
   events.append(('break',None))
   if re.match(r'^[IVX]+[.]\s',t) and len(t)<150:events.append(('skip',None));return
   if el.get('align')=='center' or ((t.isupper() or (lang=='pt' and t.count(' ')>1 and sum(c.isupper() for c in t)>len(t)*0.5)) and len(t)<200 and re.search(r'[A-ZÀ-Ú]',t)):events.append(('skip',None));return
   if lang=='en' and path.name not in ('en-__P78.HTM','en-__P79.HTM') and not el.xpath('./ccc') and len(t)<180 and not re.search(r'[.?!;:]\s*[\d\"\'”»)]*$',t) and not re.match(r'^\d',t) and not t.startswith(('"',"'",'«')):
    events.append(('skip',None));return
   bold=el.xpath('.//b|.//strong')
   if bold and text(bold[0])==t and not re.match(r'^\d',t):events.append(('skip',None));return
  if tag=='a' and (el.get('name') or '').startswith('$'):events.append(('stop',None));return
  if el.text:events.append(('text',el.text))
  for c in el:
   walk(c)
   if c.tail:events.append(('text',c.tail))
  if tag in ('p','blockquote','li','br'):events.append(('break',None))
 walk(tree)
 # EN has two paragraph starts embedded in preceding paragraphs, confirmed from
 # the source HTML and neighbouring paragraph numbers (not inferred wording).
 if lang=='en':
  split=[]
  for kind,value in events:
   if kind=='text':
    pieces=re.split(r'\b(2077|2436)\s+(?=[A-Z])',value)
    for i,piece in enumerate(pieces):split.append(('marker',int(piece)) if i%2 else ('text',piece))
   else:split.append((kind,value))
  events=split
 out={};current=None;last=0
 for kind,value in events:
  if kind=='marker':
   # Reject footnote/list numbers and create only increasing body paragraphs.
   if value<=last or value>2865:
    current=None;continue
   if last and value!=last+1:
    # Body headings can contain large digits; only real plausible markers pass.
    if value<last:current=None;continue
   current=value;last=value;out[value]=[]
  elif kind=='stop':current=None
  elif kind=='skip':
   if current!=1471:current=None
  elif current:
   if kind=='break':out[current].append('\n\n')
   else:
    out[current].append(value)
 result={}
 for n,parts in out.items():
  # Normalize layout whitespace while preserving actual block separations.
  lines=[SPACE.sub(' ',x).strip() for x in ''.join(parts).split('\n\n')]
  content='\n\n'.join(x for x in lines if x)
  if lang=='pt':content=re.sub(r'^[.]\s+', '', content)
  embedded_headings={
   1383: '"Take this and eat it, all of you": communion',
   209: '"A God merciful and gracious"',
   268: '"He does whatever he pleases"104',
   269: '"You are merciful to all, for you can do all thing"108',
   328: 'Who are they?',
   370: '"Each for the other" - "A unity in two"',
   406: 'A hard battle. . .',
   424: '"To preach. . . the unsearchable riches of Christ"10',
   493: '"Let it be done to me according to your word. . ."',
   540: '"The kingdom of God is at hand"',
   550: '"The keys of the kingdom"',
   598: '"Jesus handed over according to the definite plan of God"',
   600: '"He died for our sins in accordance with the Scriptures"',
   601: '"For our sake God made him to be sin"',
   607: '"The Lamb who takes away the sin of the world"',
   626: '"You will not let your Holy One see corruption"',
   627: '"Buried with Christ. . ."',
   720: '"Rejoice, you who are full of grace"',
   789: '"One Body"',
   791: '"Christ is the Head of this Body"',
   829: 'What does "catholic" mean?',
   835: 'Who belongs to the Catholic Church?',
   845: '"Outside the Church there is no salvation"',
   873: 'Why the ecclesial ministry?',
   963: 'Wholly united with her Son . . .',
   996: 'How do the dead rise?',
   1068: 'What does the word liturgy mean?',
   1085: '. . . from the time of the Church of the Apostles . . .',
   1087: '. . . is present in the earthly liturgy . . .',
   1262: 'For the forgiveness of sins . . .',
   1264: '"A new creature"',
   1271: 'An indelible spiritual mark . . .',
   1340: '"Do this in memory of me"',
   1505: '"Heal the sick . . ."',
   1515: '" . . . let him call for the presbyters of the Church"',
   1547: 'In the person of Christ the Head . . .',
   2658: '"Today"',
   2669: '"Come, Holy Spirit"',
   2734: 'Why do we complain of not being heard?',
   2737: 'How is our prayer efficacious?',
   2838: 'and forgive us our trespasses . . .',
   75: 'In the apostolic preaching. . .',
   2055: 'The Decalogue in Sacred Scripture',
   2063: "The Decalogue in the Church's Tradition",
   2068: 'The unity of the Decalogue',
   2069: 'The Decalogue ant the natural law',
   2071: 'the obligation\n\nof the Decalogue',
   670: '. . . until all things are subjected to him',
   900: "The participation of lay people in Christ's priestly office",
   1092: 'The Holy Spirit prepares for the reception of Christ',
   1152: 'Words and actions',
   2103: 'The social duty of religion and the right to religious freedom',
   2841: '. . . as we forgive those who trespass against us',
  }
  if lang=='en' and n in embedded_headings and content.endswith(embedded_headings[n]):
   content=content[:-len(embedded_headings[n])].rstrip()
  pt_headings={150:'CRER EM JESUS CRISTO, FILHO DE DEUS',862:'O APOSTOLADO',2350:'AS OFENSAS À CASTIDADE'}
  if lang=='pt' and n in pt_headings and content.endswith(pt_headings[n]):
   content=content[:-len(pt_headings[n])].rstrip()
  if lang=='en' and path.name in ('en-__P78.HTM','en-__P79.HTM'):content=SPACE.sub(' ',content).strip()
  es_headings={64:'III Cristo Jesús, «mediador y plenitud de toda la Revelación» (DV 2)',149:'II "Yo sé en quién tengo puesta mi fe"(2 Tm 1,12)'}
  if lang=='es' and n in es_headings and content.endswith(es_headings[n]):content=content[:-len(es_headings[n])].rstrip()
  if content:result[n]=content
 return result

def validate(corpus):
    expected = set(range(1, 2866))
    for lang in LANGS:
        assert set(corpus[lang]) == expected, (lang, sorted(expected - set(corpus[lang])))
        assert all(len(value.strip()) >= 30 for value in corpus[lang].values()), lang
        assert not any('Copyright ©' in value for value in corpus[lang].values()), lang
        for number, value in corpus[lang].items():
            assert not re.search(r'\b' + str(number + 1) + r'[.]?\s+[A-ZÁ-Ú«“"]', value), (lang, number, 'joined next paragraph')
    for lang in ('es', 'pt'):
        assert not any(corpus[lang][n] == corpus['en'][n] for n in expected), (lang, 'untranslated English field')
    for lang, keywords in {
        'en': ('inadmissible', 'dignity', 'abolition worldwide'),
        'es': ('inadmisible', 'dignidad', 'abolición en todo el mundo'),
        'pt': ('inadmissível', 'dignidade', 'abolição em todo o mundo'),
    }.items():
        assert all(word in corpus[lang][2267] for word in keywords), (lang, 2267)
        assert len(corpus[lang][2267]) > 900, (lang, 2267, 'truncated')
        assert len(corpus[lang][126]) > 950, (lang, 126, 'lost three-stage Gospel list')
        assert len(corpus[lang][1471]) > 600, (lang, 1471, 'lost indulgence definition')
    assert 'Espírito Santo' in corpus['pt'][263]
    assert '263.' not in corpus['pt'][262]
    assert 'PARÁGRAFO' not in corpus['pt'][266]
    assert 'Spirit' in corpus['en'][263]
    assert 'Unemployment' in corpus['en'][2436]
    assert 'The gift of the Decalogue' in corpus['en'][2077]
    assert 'The Decalogue in Sacred Scripture' not in corpus['en'][2055]
    assert len(corpus['pt'][2865]) < 200  # no appended chapter footnotes


def read_kotlin():
    corpus = {lang: {} for lang in LANGS}
    pattern = re.compile(r'(\d+) to LocalizedText\("""(.*?)""", """(.*?)""", """(.*?)"""\)', re.S)
    for path in sorted(DATA.glob('CatechismFullData[0-9]*.kt')):
        for match in pattern.finditer(path.read_text()):
            number = int(match[1])
            for lang, value in zip(LANGS, match.groups()[1:]):
                assert number not in corpus[lang], (path, number, 'duplicate')
                corpus[lang][number] = value.replace("${'$'}", '$')
    return corpus


def verify():
    corpus = read_kotlin()
    validate(corpus)
    provenance = json.loads(PROVENANCE.read_text())
    for lang in LANGS:
        assert corpus_digest(corpus[lang]) == provenance['corpus'][lang]['sha256'], (lang, 'corpus drift; review sources before updating digest')
    for path in DATA.glob('NovenaData[0-9]*.kt'):
        content = path.read_text()
        assert 'Continue esta novena no app' not in content, path
        assert 'Configure lembretes para não perder nenhum dia' not in content, path
        baseline = __import__('subprocess').check_output(['git', 'show', 'HEAD:' + str(path.relative_to(REPO))], cwd=REPO, text=True)
        assert re.findall(r'dayNumber = (\d+)', content) == re.findall(r'dayNumber = (\d+)', baseline), path
    assert len(list(DATA.glob('NovenaData[0-9]*.kt'))) == 246
    assert 'Patron saint of the Kingdom of Jesus' not in (DATA / 'NovenaData000.kt').read_text()
    prayer = (DATA / 'PrayerAndNovenaData.kt').read_text()
    assert 'Morning prayer · abridged selection' in prayer
    assert 'this is not the complete office' in prayer
    compendium = (DATA / 'CatechismCompendiumData.kt').read_text()
    assert 'this does not reproduce the 598 questions and answers' in compendium
    print('PASS: 2,865 paragraphs × 3 languages; source digests; current §2267; boundaries; 246 complete novenas; honest editorial labels.')


def fetch_sources(cache):
    from lxml import html
    cache.mkdir(parents=True, exist_ok=True)
    jobs = []
    for lang in LANGS:
        index_path = cache / f'{lang}-index.html'
        if not index_path.exists():
            index_path.write_bytes(urllib.request.urlopen(BASE[lang] + INDEX[lang], timeout=30).read())
        tree = html.fromstring(index_path.read_bytes())
        for href in dict.fromkeys(tree.xpath('//a/@href')):
            href = urllib.parse.urldefrag(href)[0]
            include = (
                (lang == 'en' and href.startswith('__P')) or
                (lang == 'es' and href.startswith('p') and href.endswith('_sp.html')) or
                (lang == 'pt' and href.startswith('p') and href.endswith('_po.html'))
            )
            if include:
                jobs.append((lang, urllib.parse.urljoin(BASE[lang], href)))
    def fetch(job):
        lang, url = job
        path = cache / (lang + '-' + url.rsplit('/', 1)[-1])
        if not path.exists():
            path.write_bytes(urllib.request.urlopen(url, timeout=30).read())
        return lang, url, path
    with ThreadPoolExecutor(max_workers=6) as pool:
        return sorted(pool.map(fetch, dict.fromkeys(jobs)), key=lambda e: (e[0], e[1]))


def rebuild(cache):
    corpus = {lang: {} for lang in LANGS}
    sources = []
    for lang, url, path in fetch_sources(cache):
        entries = extract(path, lang)
        for number, value in entries.items():
            assert number not in corpus[lang], (lang, number, url, 'duplicate source paragraph')
            corpus[lang][number] = value
        sources.append({'language': lang, 'url': url, 'sha256': digest(path.read_bytes()), 'paragraphs': sorted(entries)})
    validate(corpus)  # Fail closed before changing any application file.
    provenance = {
        'retrieved': '2026-11-12',
        'publisher': 'Libreria Editrice Vaticana / Holy See',
        'edition': 'Official Vatican online Catechism, including the 2018 revision of §2267',
        'normalization': 'HTML layout whitespace and headings omitted; paragraph prose and source note markers retained. Source footnote bodies are available at the original URLs.',
        'numberingRepairs': [
            'EN §2077 is embedded in the preceding paragraph; §2436 follows a br inside §2435. Split at the explicit source numbers.',
            'PT 2217 is printed 2117 and 2439 is printed 1439 in p3s2cap2_2196-2557_po.html. IDs follow neighbouring numbers and parallel official ES/EN text; prose is unchanged.',
        ],
        'corpus': {lang: {'count': len(corpus[lang]), 'sha256': corpus_digest(corpus[lang])} for lang in LANGS},
        'sources': sources,
    }
    # Stage generated strings in memory; validation has already checked the corpus.
    generated = {}
    for part in range(29):
        start = max(1, part * 100)
        end = min((part + 1) * 100 - 1, 2865)
        lines = ['package com.example.data.repository', '', 'import com.example.data.models.LocalizedText', '',
                 '// Generated from official Vatican HTML by tools/content/catechism_corpus.py.',
                 '// Provenance, retrieval date and source hashes: docs/content/catechism-provenance.json.',
                 f'object CatechismFullData{part:02d} {{', '    val entries: Map<Int, LocalizedText> = mapOf(']
        for number in range(start, end + 1):
            values = []
            for lang in LANGS:
                value = corpus[lang][number]
                assert '"""' not in value
                values.append('"""' + value.replace('$', "${'$'}") + '"""')
            lines.append(f'        {number} to LocalizedText(' + ', '.join(values) + '),')
        lines += ['    )', '}', '']
        generated[DATA / f'CatechismFullData{part:02d}.kt'] = '\n'.join(lines)
    for path, content in generated.items():
        path.write_text(content)
    PROVENANCE.write_text(json.dumps(provenance, ensure_ascii=False, indent=2) + '\n')
    print(f'Rebuilt {len(sources)} official source pages; generated {len(generated)} Kotlin files.')
    verify()


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--verify', action='store_true', help='Verify the bundled corpus offline (standard library only)')
    parser.add_argument('--rebuild', action='store_true', help='Retrieve official Vatican sources and rebuild all three languages (requires lxml)')
    parser.add_argument('--cache', type=Path, default=Path('/tmp/catholicgpt-ccc-sources'))
    args = parser.parse_args()
    if args.rebuild:
        rebuild(args.cache)
    else:
        verify()

if __name__ == '__main__':
    main()
