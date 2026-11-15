# M6 · Órdenes, congregaciones y cargos de la Iglesia

Antes de empezar, lee `docs/content/EDITORIAL-RULES-1.8.md`. Sus reglas son obligatorias.

## Base actual

`app/src/main/java/com/example/data/repository/ReligiousOrdersData.kt` tiene:
- **37 órdenes:** `id`, `name`, `abbreviation`, `foundationYear`, `founder`, `description` y `charism`;
- **26 cargos** (`office_*`): `id`, `name` y `description`.

Ese archivo no se toca. Todo lo nuevo va en JSON dentro de `app/src/main/assets/orders/`. La app fusiona los JSON con los datos existentes.

## Archivo 1 · `orders-details.json` (T-016)

Un elemento por orden: las 37 existentes y todas las nuevas del archivo 2. Va en el mismo orden: primero las existentes, en el orden de `ReligiousOrdersData.kt`, y después las nuevas.

```json
{
  "id": "franciscanos-ofm",
  "kind": "order",
  "motto": {"es": "Pax et bonum", "en": "Pax et bonum", "pt": "Pax et bonum"},
  "approval": {"es": "", "en": "", "pt": ""},
  "notableSaints": {"es": [], "en": [], "pt": []},
  "presence": {"es": "", "en": "", "pt": ""},
  "sources": ["Annuario Pontificio 2025", "…"]
}
```

| Campo | Contenido |
|---|---|
| `kind` | Uno de estos valores:<ul><li>`order`: orden religiosa de votos solemnes;</li><li>`congregation`: instituto religioso de votos simples;</li><li>`society`: sociedad de vida apostólica;</li><li>`secular_institute`: instituto secular;</li><li>`prelature`: prelatura personal;</li><li>`monastic_federation`: federación monástica.</li></ul>Debe estar verificado. Por ejemplo, el Opus Dei es `prelature`. |
| `motto` | Lema oficial, con el original latino si lo tiene. Puede ser `null`. Nunca se inventa. |
| `approval` | Aprobación pontificia: papa, documento y año. Por ejemplo: «Paulo III, bula *Regimini militantis Ecclesiae*, 27-IX-1540». Si no se conoce con certeza, dejar los tres idiomas en `""`. |
| `notableSaints` | Hasta 5 santos o beatos canonizados de la familia, con la misma cantidad en los tres idiomas. |
| `presence` | De 1 a 3 frases sobre la presencia actual: países, miembros, obras. Cifras solo con fuente y año («según el Annuario Pontificio 2025…»). |
| `sources` | Entre 2 y 5 fuentes reales: Annuario Pontificio, sitio oficial de la orden, vatican.va, Catholic Encyclopedia. Sin URL; las URL van en el REPORT. |

## Archivo 2 · `orders-new.json` (T-016)

Las órdenes nuevas usan el mismo formato que las existentes, más el campo `gender`.

```json
{
  "id": "mercedarios-odem",
  "name": {"es": "", "en": "", "pt": ""},
  "abbreviation": "O. de M.",
  "foundationYear": "1218",
  "founder": {"es": "", "en": "", "pt": ""},
  "description": {"es": "", "en": "", "pt": ""},
  "charism": {"es": "", "en": "", "pt": ""},
  "gender": "men"
}
```

| Campo | Contenido |
|---|---|
| `id` | En kebab-case: nombre corto y sigla, como en los existentes. Debe ser único. |
| `description` | Entre 60 y 120 palabras por idioma, con 150 caracteres como mínimo. Explica quiénes son, el origen, el carisma, qué los distingue y dónde sirven hoy. Usa el mismo tono que las existentes. |
| `charism` | Una frase. |
| `gender` | `men`, `women` o `mixed`. |

## Archivo 3 · `offices-new.json` (T-017)

```json
{
  "id": "office_obispo_auxiliar",
  "name": {"es": "", "en": "", "pt": ""},
  "description": {"es": "", "en": "", "pt": ""},
  "group": "clergy",
  "after": "office_obispo",
  "sources": ["Código de Derecho Canónico, cc. 403-411", "…"]
}
```

| Campo | Contenido |
|---|---|
| `id` | Empieza por `office_`, en snake_case, y es único. |
| `description` | Entre 60 y 120 palabras por idioma, con 150 caracteres como mínimo. Explica qué es, quién lo nombra, qué funciones tiene y su base canónica (CIC, CCEO o *Praedicate Evangelium*), en un tono catequético claro. |
| `group` | Uno de estos valores: `altar`, `clergy`, `consecrated`, `mission`, `governance`. `governance` es el grupo nuevo «Gobierno y Curia». |
| `after` | Id del cargo detrás del cual se muestra, sea existente o nuevo, para mantener el orden jerárquico. Si va al final de su grupo, `null`. |
| `sources` | Entre 2 y 5 fuentes: cánones exactos, constituciones o motu proprio. |

## Reglas

- **Solo hechos verificables.** Las fechas, aprobaciones, lemas y cifras deben salir de fuentes reales. Si hay duda, el campo se deja vacío.
- **Sin superlativos sin fuente**, como «la más grande» o «la más antigua».
- **Tres idiomas completos y naturales.** El portugués sigue la norma de Brasil.
- **Instituciones con historia reciente delicada** (abusos, refundaciones, comisarios pontificios), por ejemplo los Legionarios de Cristo: se describen con sobriedad, incluido el hecho público y documentado de la intervención de la Santa Sede, sin juicios propios.

## Validar

```bash
python3 docs/tasks/tools/validate_orders.py details   app/src/main/assets/orders/orders-details.json
python3 docs/tasks/tools/validate_orders.py new       app/src/main/assets/orders/orders-new.json
python3 docs/tasks/tools/validate_orders.py offices   app/src/main/assets/orders/offices-new.json
```
