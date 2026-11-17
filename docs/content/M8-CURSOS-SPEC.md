# M8 · Cursos bíblicos nuevos

Antes de empezar lee `docs/content/EDITORIAL-RULES-1.8.md`, que es obligatorio, y `docs/quality/STUDY-CURRICULUM-PLAN.md` §3, §5, §7 y §10. Este último fija la anatomía de cada lección, el curso B ya diseñado y los criterios de calidad.

## Base actual

La app tiene cuatro cursos:
- **A** · `track_catholic_bible`: Aprender a leer la Biblia en la Iglesia, en Kotlin.
- **Sacramentos**, **Parábolas** y **Apostólico**, en `ScriptureStudyData.kt`.

La pantalla `ScriptureStudyScreen.kt` ya muestra objetivos, contexto, tres desarrollos, lectio/meditatio/oratio/contemplatio, acción, tres preguntas con respuesta y fuentes. Los cursos nuevos van en JSON, **sin tocar Kotlin**. La app los añade después de los cuatro existentes.

## Cursos de M8

| Curso | id | Encargo |
|---|---|---|
| B · Jesús y el misterio pascual | `track_jesus_paschal` | T-022 (Codex). Diseño ya fijado en STUDY-CURRICULUM-PLAN §5. |
| C · Historia de la salvación | `track_salvation_history` | T-022 (Codex). Creación → Abrahán → Éxodo y alianza → David y los profetas → Cristo, plenitud. |
| D · Los Salmos, escuela de oración | `track_psalms_prayer` | T-023 (Hermes). Alabanza, súplica, confianza, penitencia (Sal 51/50), Salmos en la liturgia y en Cristo. |
| E · San Pablo y la vida en Cristo | `track_paul_life_in_christ` | T-024 (Codex). Conversión (Hch 9), justificación por la gracia (Rm 5), Cuerpo de Cristo (1 Co 12), caridad (1 Co 13), esperanza (Rm 8). |

Cada curso tiene 5 lecciones, de unos 20 minutos cada una y 100 en total. Nivel `FOUNDATION`, salvo que se justifique `INTERMEDIATE`.

## Contrato: `app/src/main/assets/courses/<id>.json`

```json
{
  "id": "track_jesus_paschal",
  "level": "FOUNDATION",
  "estimatedMinutes": 100,
  "iconName": "AutoStories",
  "title": {"es": "", "en": "", "pt": ""},
  "description": {"es": "", "en": "", "pt": ""},
  "objectives": [{"es": "", "en": "", "pt": ""}],
  "lessons": [
    {
      "id": "jesus_1_incarnation",
      "lessonNumber": 1,
      "title": {"es": "", "en": "", "pt": ""},
      "scriptureReferences": ["luk 1:26-38"],
      "estimatedMinutes": 20,
      "objectives": [{"es": "", "en": "", "pt": ""}],
      "summary": {"es": "", "en": "", "pt": ""},
      "historicalContext": {"es": "", "en": "", "pt": ""},
      "teachingSections": [{"title": {"es": "", "en": "", "pt": ""}, "body": {"es": "", "en": "", "pt": ""}}],
      "lectio": {"es": "", "en": "", "pt": ""},
      "meditatio": {"es": "", "en": "", "pt": ""},
      "oratio": {"es": "", "en": "", "pt": ""},
      "contemplatio": {"es": "", "en": "", "pt": ""},
      "actionStep": {"es": "", "en": "", "pt": ""},
      "reviewQuestions": [{"prompt": {"es": "", "en": "", "pt": ""}, "answer": {"es": "", "en": "", "pt": ""}}],
      "sourceReferences": [{"id": "ccc_456_460", "title": {"es": "", "en": "", "pt": ""}, "catechismStart": 456, "catechismEnd": 460, "url": null}]
    }
  ]
}
```

| Campo | Regla |
|---|---|
| `objectives` del curso | 3 objetivos. |
| `objectives` de la lección | 2 o 3 objetivos observables. |
| `scriptureReferences` | Una entrada por libro, con el id de libro de la app (`gen`, `exo`, `psa`, `mat`, `mar`, `luk`, `joh`, `act`, `rom`, `1co`…; la lista completa está en `BibleData.catholicCanonBooks`). La app resuelve el texto desde la Biblia de cada idioma. **No escribas el texto bíblico.** Con los salmos, ten en cuenta que la numeración difiere entre ediciones: indica la numeración hebrea (la de la Biblia en inglés incluida) y anótalo en el REPORT. |
| `summary` | 1 o 2 frases. Se muestra como síntesis. |
| `historicalContext` | Entre 70 y 110 palabras por idioma. |
| `teachingSections` | **Exactamente 3.** Cada cuerpo, entre 80 y 130 palabras por idioma; en total, unas 250–350. |
| `lectio` / `meditatio` / `contemplatio` / `actionStep` | Según STUDY-CURRICULUM-PLAN §3. |
| `oratio` | **Regla del usuario: no se componen oraciones.** Puede ser una **guía** para la oración personal, por ejemplo: «Habla con Dios sobre…, pídele…». Si se presenta un **texto de oración**, debe ser real y tener fuente: un salmo indicado por referencia, el Padrenuestro, una oración tradicional con fuente. |
| `reviewQuestions` | **Exactamente 3**, con respuesta razonada. Dos de comprensión y una de discernimiento. |
| `sourceReferences` | Entre 2 y 6. Para el Catecismo, `catechismStart` y `catechismEnd` inclusivos, entre 1 y 2865, con id `ccc_<a>_<b>`. Para otros documentos (*Dei Verbum*, encíclicas), el título lleva el número exacto y `url` es un objeto es/en/pt con la URL oficial de vatican.va. |

**Prohibido:**
- citas inventadas;
- atribuir aprobación eclesiástica al texto editorial;
- párrafos intercambiables o repetidos;
- traducciones copiadas entre idiomas;
- culpas colectivas, por ejemplo hacia los judíos (CIC 597);
- presentar el sufrimiento o el abuso como algo que se debe soportar.

## Validar

```bash
python3 docs/tasks/tools/validate_courses.py app/src/main/assets/courses/<id>.json
```
