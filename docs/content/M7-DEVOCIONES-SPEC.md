# M7 · Devociones a Jesús y a María

Antes de empezar, lee `docs/content/EDITORIAL-RULES-1.8.md`. Sus reglas son obligatorias. Este encargo se apoya sobre todo en estas dos:

- **Ningún texto inventado.** Toda oración procede de una fuente comprobada y se reproduce fielmente. Solo se traduce fielmente.
- **Revelaciones privadas.** Se aplican las *Normas para proceder en el discernimiento de presuntos fenómenos sobrenaturales* del DDF (17-V-2024).

## Base actual

`app/src/main/java/com/example/data/repository/DevotionsAndHistoryData.kt` tiene:

- **30 advocaciones marianas** (`MarianApparition`). Campos: `title`, `feastDay`, `year`, `location`, `primarySanctuary`, `historyAndApparition`, `promises`, `recordedMiracles`, `venerationPrayer`, `region`, `country` y `patronageOf`.
- **20 devociones de Cristo** (`ChristManifestation`). Campos: `title`, `feastDay`, `originHistory`, `promises`, `theologicalMeaning`, `officialPrayer`, `region`, `country`, `primarySanctuary` y `recordedMiracles`.

Esos datos se escribieron antes de las reglas actuales. Puede que tengan:

- oraciones sin procedencia o redactadas por agentes;
- promesas sin atribución;
- `recordedMiracles` genéricos, como «Incontables milagros…».

M7 **audita y corrige** esos datos y **añade** devociones nuevas. El archivo Kotlin no se toca: todo va en JSON dentro de `app/src/main/assets/devotions/`.

## Archivo 1 · `marian-details.json` / `christ-details.json`

Un elemento por devoción existente, en el orden del Kotlin.

```json
{
  "id": "guadalupe",
  "recognition": {"es": "", "en": "", "pt": ""},
  "promises": null,
  "miracles": null,
  "prayer": null,
  "practice": {"es": "", "en": "", "pt": ""},
  "sources": ["…", "…"]
}
```

| Campo | Contenido |
|---|---|
| `recognition` | Estatus eclesial verificado, en 1 a 3 frases, con documento y año. Por ejemplo: aprobación del obispo diocesano, culto autorizado, fiesta en el calendario romano o nacional, coronación pontificia, *nihil obstat* según las Normas de 2024, o «la Iglesia no se ha pronunciado». **Obligatorio.** |
| `promises` | `null` deja el texto actual. Para reemplazarlo: `{"text": {es,en,pt}, "attribution": {es,en,pt}, "source": "obra y lugar"}`, con la promesa atribuida a quien la recibió y su fuente. Para retirar una promesa sin fuente, usa `{"remove": true}`. |
| `miracles` | `null` deja el texto actual. Si es genérico o no tiene fuente, se sustituye por `{"text": {es,en,pt}}`. El texto cita solo milagros o prodigios reconocidos con fuente, o dice con honestidad, por ejemplo: «La tradición atribuye favores a esta advocación; la Iglesia no ha declarado milagros concretos». |
| `prayer` | `null` deja la oración actual, solo si es auténtica y tiene fuente. Si no lo es, hay dos opciones: <ul><li>`{"text": {es,en,pt}, "source": "…"}`, con una oración real y su fuente;</li><li>`{"remove": true}`, si no hay oración verificable.</li></ul>En el REPORT, di qué oraciones actuales verificaste y dónde. |
| `practice` | 1 a 3 frases sobre cómo se vive la devoción: fiesta, peregrinación, novena, escapulario, etc. |
| `sources` | Entre 2 y 5 fuentes reales, sin URL. Las URL van en el REPORT. |

## Archivo 2 · `marian-new.json` / `christ-new.json`

Son devociones nuevas, con los mismos campos del modelo Kotlin correspondiente, más `recognition`, `practice` y `sources`. La oración solo se incluye si es real y tiene fuente; si no, va `null`.

```json
{
  "id": "virgen_del_quinche",
  "title": {"es": "", "en": "", "pt": ""},
  "feastDay": {"es": "", "en": "", "pt": ""},
  "year": "1588",
  "location": {"es": "", "en": "", "pt": ""},
  "primarySanctuary": {"es": "", "en": "", "pt": ""},
  "history": {"es": "", "en": "", "pt": ""},
  "promises": null,
  "miracles": {"es": "", "en": "", "pt": ""},
  "prayer": null,
  "region": "Americas",
  "country": {"es": "", "en": "", "pt": ""},
  "patronageOf": {"es": "", "en": "", "pt": ""},
  "recognition": {"es": "", "en": "", "pt": ""},
  "practice": {"es": "", "en": "", "pt": ""},
  "sources": ["…", "…"]
}
```

| Campo | Contenido |
|---|---|
| `history` | Entre 100 y 220 palabras por idioma. Hechos verificables, y la tradición presentada como tal. |
| `year` | En `christ-new.json` puede ser `""`. |
| `promises` / `prayer` | `null`, o el mismo formato con fuente del archivo 1. |
| `region` | `Americas`, `Europe`, `Asia_Africa` o `Universal`. |

Las devociones de Cristo usan `history`, que corresponde a `originHistory`, y añaden un campo `theologicalMeaning` de 40 a 120 palabras. No llevan `location`.

## Validar

```bash
python3 docs/tasks/tools/validate_devotions.py details marian app/src/main/assets/devotions/marian-details.json
python3 docs/tasks/tools/validate_devotions.py new     marian app/src/main/assets/devotions/marian-new.json
python3 docs/tasks/tools/validate_devotions.py details christ app/src/main/assets/devotions/christ-details.json
python3 docs/tasks/tools/validate_devotions.py new     christ app/src/main/assets/devotions/christ-new.json
```
