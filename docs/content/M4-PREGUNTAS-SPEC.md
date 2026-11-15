# M4 · 101 preguntas: respuestas de catequista experto

Lee primero `docs/content/EDITORIAL-RULES-1.8.md`; sus reglas son obligatorias.

## Objetivo

Las 101 preguntas de `app/src/main/java/com/example/data/repository/CatholicQuestionsData.kt` (`ApologeticsQuestion`: number, section, question, answer, foundation, clarification) tienen hoy unas 174 palabras por respuesta.

No se tocan ni la respuesta ni la pregunta, ni los campos `foundation` y `clarification` existentes. Se añade una sección **«Profundizar»**, que explica el tema como lo haría un catequista y teólogo experto: claro, cálido, riguroso, didáctico y honesto con las objeciones.

## Contrato

El contenido va en `app/src/main/assets/questions/deeper-a.json` (preguntas 1–51) y `deeper-b.json` (52–101). Cada archivo es un array ordenado por `number`:

```json
{
  "number": 1,
  "explanation": {"es": "", "en": "", "pt": ""},
  "scripture":   {"es": "", "en": "", "pt": ""},
  "tradition":   {"es": "", "en": "", "pt": ""},
  "magisterium": {"es": "", "en": "", "pt": ""},
  "objection":   {"es": "", "en": "", "pt": ""},
  "analogy":     {"es": "", "en": "", "pt": ""},
  "synthesis":   {"es": "", "en": "", "pt": ""},
  "sources": ["CIC 309-314", "Santo Tomás, Suma Teológica I, q. 2, a. 3 ad 1", "Agustín, Enchiridion 11"]
}
```

| Campo | Palabras por idioma | Contenido |
|---|---|---|
| `explanation` | 90–160 | La explicación de fondo: conceptos, distinciones y por qué la Iglesia lo enseña así. |
| `scripture` | 50–110 | 2–4 pasajes con lo que aportan. Referencias en el formato de cada idioma: ES «Jn 3,16», EN «John 3:16», PT «Jo 3,16». No copiar traducciones bíblicas con derechos: parafrasear o citar muy brevemente. |
| `tradition` | 40–100 | Padres y doctores con obra y lugar exactos. Las citas literales solo van entre comillas si la fuente está verificada; si no, se parafrasean. |
| `magisterium` | 40–100 | Catecismo con números reales y pertinentes (ES «CIC 1822», EN «CCC 1822», PT «CIC 1822»), concilios y encíclicas con número de párrafo. |
| `objection` | 60–120 | La objeción más fuerte y honesta, formulada con justicia, y la respuesta argumentada. Sin caricaturas ni polémica agresiva. |
| `analogy` | 25–70 | Una imagen o comparación que ilumine, con su límite («como toda analogía, cojea en…»). |
| `synthesis` | 20–50 | Dos o tres frases que el lector pueda recordar. |
| `sources` | — | 3–8 líneas cortas: CIC, documentos, obras con lugar. Sin URL. |

El total por idioma debe quedar entre 350 y 650 palabras.

## Interfaz (integrador, en esta sesión)

- Debajo de la respuesta actual va un bloque «Profundizar», con tag `qa_deeper`.
- El bloque tiene 7 subsecciones con títulos localizados por la app, no por el JSON: «Explicación», «Lo que dice la Escritura», «Padres y Tradición», «Magisterio», «La objeción más fuerte», «Para entenderlo», «En síntesis». Tags `qa_deeper_<campo>`.
- Las `sources` se añaden al pie, en estilo de nota.
- El cargador es opcional, igual que el de las oraciones: si falta un archivo, se ignora.
- Tests de contenido y de UI.
