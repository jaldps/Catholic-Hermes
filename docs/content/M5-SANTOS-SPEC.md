# M5 · Santos del Calendario Romano General

Antes de empezar, lee `docs/content/EDITORIAL-RULES-1.8.md`. Sus reglas son obligatorias.

## Base

`app/src/main/java/com/example/data/liturgy/GeneralRomanSanctoral.kt` contiene las celebraciones del Calendario Romano General. Cada una tiene `month`, `day`, `key`, el título en ES/EN/PT, el rango y la marca `isLordFeast`.

Las fichas de santos se escriben solo para las celebraciones con `isLordFeast = false`. Esto incluye las memorias marianas: en ellas, la ficha describe la celebración, no una biografía.

Cada encargo recibe su lista exacta de `key` en `docs/tasks/T-0NN.keys`.

## Contrato

El archivo es `app/src/main/assets/saints/saints-<lote>.json`: un array en el mismo orden que la lista de keys.

```json
{
  "key": "francis_de_sales_bishop",
  "month": 1,
  "day": 24,
  "bio": {"es": "", "en": "", "pt": ""},
  "patronages": {"es": [], "en": [], "pt": []},
  "quote": null,
  "sources": ["Martirologio Romano (2004), 28 de diciembre", "…"]
}
```

| Campo | Contenido |
|---|---|
| `key`, `month`, `day` | Idénticos a `GeneralRomanSanctoral.kt`. |
| `bio` | 120–200 palabras por idioma. Quién fue, en qué época vivió, qué hizo y por qué la Iglesia lo celebra (mártir, doctor, fundador…). En las memorias marianas: origen y sentido de la celebración. Sigue estas pautas: <ul><li>solo hechos verificables;</li><li>la tradición se presenta como tradición («según la tradición…»);</li><li>los milagros, solo si la Iglesia los reconoció o como «se le atribuyen».</li></ul> |
| `patronages` | Patronazgos reconocidos de forma general (oficios, lugares, causas). Si no hay ninguno, se deja una lista vacía. No se inventan patronazgos populares sin fuente. |
| `quote` | `null`, u objeto `{"text": {"es","en","pt"}, "source": "obra, libro, capítulo"}`. Solo se usa con una **cita textual verificada** del santo, con su obra y su lugar exactos. Si hay duda, va `null`. |
| `sources` | Entre 2 y 5 fuentes reales, por ejemplo: <ul><li>Martirologio Romano (2004);</li><li>Calendarium Romanum (1969);</li><li>biografías de vatican.va (audiencias de Benedicto XVI sobre Padres y doctores);</li><li>Catholic Encyclopedia;</li><li>sitios oficiales de órdenes o santuarios.</li></ul> Sin URL dentro del JSON: las URL van en el REPORT. |

**Regla del usuario:** no se compone ningún texto que se presente como oración, cita o palabras del santo. Las biografías son redacción propia, informativa y con fuentes.

Los tres idiomas deben ser completos y naturales. El portugués sigue la norma de Brasil.

## Validar

```bash
python3 docs/tasks/tools/validate_saints.py app/src/main/assets/saints/saints-<lote>.json docs/tasks/T-0NN.keys [--partial]
```
