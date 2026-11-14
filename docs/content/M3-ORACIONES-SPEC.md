# M3 · Oraciones: historia, instrucciones, fuentes y oraciones nuevas

Antes de empezar, lee `docs/content/EDITORIAL-RULES-1.8.md`. Sus reglas son obligatorias.

## 1. Contrato (JSON en `app/src/main/assets/prayers/`)

Archivos (la lista es fija; el cargador ignora los que falten):
- `details-a.json` — detalles de 34 oraciones existentes (encargo T-001).
- `details-b.json` — detalles de las otras 34 (encargo T-002).
- `extra.json` — ~30 oraciones nuevas con sus detalles (encargo T-003).

Entrada de detalles (`details-*.json`, array):

```json
{
  "prayerId": "angelus",
  "history":      {"es": "", "en": "", "pt": ""},
  "instructions": {"es": "", "en": "", "pt": ""},
  "source":       {"es": "", "en": "", "pt": ""},
  "approval":     {"es": "", "en": "", "pt": ""},
  "promise": null,
  "textNote":     {"es": "", "en": "", "pt": ""}
}
```

- `history`: 80–180 palabras por idioma: origen, autor, época y uso en la Iglesia. Las atribuciones dudosas se presentan como tales.
- `instructions`: 40–120 palabras: cuándo se reza, postura y gestos, quién dice V/ y quién R/, repeticiones.
- `source`: texto original o edición de referencia (p. ej. «Liturgia de las Horas, Completas»; «Diario de Santa Faustina, n. 476»).
- `approval`: uso litúrgico, aprobación de letanías o indulgencias del *Enchiridion Indulgentiarum* (1999). Solo lo verificable; si no hay nada, queda vacío. No se inventan números de concesión.
- `promise`: `null`, u objeto `{"kind": "SOURCED|ECCLESIAL_GRANT|TRADITIONAL|NONE", "text", "attribution", "source", "conditions"}` (cada uno LocalizedText), igual que en las novenas. Las promesas comerciales o de cadena se mencionan en `history` como añadidos ajenos a la devoción.
- `textNote`: p. ej. «Texto tradicional de dominio público» o «Traducción propia del latín». Puede quedar vacío.

Entrada de oración nueva (`extra.json`, array), con los mismos campos de detalle más:

```json
{
  "id": "litany_of_st_joseph",
  "title": {"es": "", "en": "", "pt": ""},
  "latinTitle": "Litaniae Sancti Ioseph",
  "category": "INTERCESSION",
  "leaderText": {"es": "", "en": "", "pt": ""},
  "responseText": null,
  "whenToPray": {"es": "", "en": "", "pt": ""},
  "indulgenceNote": null,
  "thematicTags": ["family"],
  "history": {}, "instructions": {}, "source": {}, "approval": {}, "promise": null, "textNote": {}
}
```

- `category` es un valor de `PrayerCategory`: BASIC, MORNING_NIGHT, MARIAN, EUCHARISTIC, HOLY_SPIRIT, PROTECTION, INTERCESSION, ANGELUS.
- `thematicTags` toma valores del vocabulario existente en `PrayerAndNovenaData.kt`.
- El texto de la oración debe ser de dominio público, o una traducción propia de un original de dominio público. Nunca traducciones litúrgicas modernas con derechos.

## 2. Interfaz (integrador)

En el detalle de la oración, que hoy muestra `whenToPray` e `indulgenceNote` (~`PrayersAndNovenasScreen.kt` l.1255), cuando exista `PrayerDetails` se añaden estas secciones con los wrappers del kit:

| Sección | Tag | Contenido |
|---|---|---|
| «Cómo rezarla» | `prayer_instructions` | `instructions` |
| «Historia» | `prayer_history` | `history`; debajo, en pie de página, `source` y `textNote` |
| «Aprobación» | `prayer_approval` | `approval`, si no está vacía |
| «Promesa» | `prayer_promise` | igual que en las novenas: insignia de tipo, atribución, fuente y condiciones |

- Reutiliza el componente de promesa de las novenas si se puede extraer sin duplicar código.
- Tests:
  - `PrayerDetailsTest`: cada id existente tiene detalles; ES/EN/PT no vacíos en `history` e `instructions`; ids únicos; las oraciones extra no colisionan con las existentes.
  - Un test de UI que abre una oración y comprueba `prayer_history`.

## 3. Criterios

- Historia exacta: autor, siglo y aprobación solo si son verificables. Las atribuciones dudosas se dicen como tales; por ejemplo, la «Oración de San Francisco» es de 1912, de *La Clochette*, anónima.
- Instrucciones prácticas, sin rigorismo.
- Los tres idiomas naturales y completos.
