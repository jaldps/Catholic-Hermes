# T-063 · Auditoría y corrección de las 55 coronillas (excepto Rosario original y Divina Misericordia)

Trabaja SOLO con los archivos que se te asignen. No inventes ni completes texto devocional: usa exclusivamente el texto de la fuente guardada y las oraciones canónicas ya presentes en la app.

## Rutas

- JSON actuales: `C:/Users/lanzj/Documents/Catholic-Hermes-work/repo/app/src/main/assets/chaplets/<id>.json`
- Texto literal de la fuente ya descargado: `C:/Users/lanzj/Documents/Catholic-Hermes-work/repo/docs/content/coronillas/sources/<id>.txt`
- Esquema y ejemplos: `C:/Users/lanzj/Documents/Catholic-Hermes-work/repo/docs/content/CORONILLAS-SPEC.md`
- Motor: `app/src/main/java/com/example/ui/screens/ChapletEngine.kt` y `ChapletCatalog.kt`
- Oraciones canónicas (ids y textos ES/EN/PT): `app/src/main/java/com/example/data/repository/PrayerAndNovenaData.kt`

## Contrato de salida

Para cada `<id>.json` asignado, escribe una versión corregida en `C:/Users/lanzj/Documents/Catholic-Hermes-work/repo/docs/tasks/chaplet-audit/<TU_BATCH>/<id>.json`.

Conserva todos los campos (`id`, `sourceId`, `title`, `summary`, `instrument`, `when`, `weekdays`, `notes`, `image`, `variables`, `promise`, `historicalNote`, `season`, `rights`, `translation`, `sources`). Cambia SOLO `beads`, `steps` y, si hace falta, `summary`, `instrument` y `notes`.

### Reglas de `steps`

- Un paso = **una sola oración o jaculatoria** dicha en una cuenta. Nunca agrupes dos oraciones distintas en un mismo `text`.
- Si la fuente dice «Padre Nuestro», «Ave María», «Gloria», «Credo», «Acto de Contrición», usa `prayerRef` con el id canónico correspondiente. El motor ya expande la oración COMPLETA y separa `V/` y `R/` cuando el `VaticanPrayer` tiene `responseText`.
- Si la fuente da una jaculatoria u oración propia con dos voces, usa `{"vr":{"v":{...},"r":{...}}}` y conserva el texto literal. Quita los prefijos `V/` `R/` del contenido (el motor los dibuja).
- Si una oración se dice **en conjunto** (Credo, Acto de Contrición, jaculatorias sin réplica), usa `text` y añade `rubric` con «Se reza en conjunto / Said together / Rezado em conjunto» en los tres idiomas. No inventes un diálogo V/R donde la fuente no lo tiene.
- `repeat` solo para la MISMA oración repetida en cuentas distintas; el motor ya crea un paso por repetición. No lo uses para esconder oraciones diferentes.
- `meditation` abre una sección nueva; úsalo para cada grupo (dolores, misterios, intenciones) según la fuente.
- `bead` apunta al índice del grupo de cuentas (`0`=cruz/medalla inicial, `1`=cuenta grande, `2`=cuenta pequeña…). La secuencia de `beads` debe seguir el instrumento de la fuente: no inviertas ni reordenes grupos arbitrariamente.
- Conserva `{{nombre}}`, `{{intencion}}` y las variables existentes; no las sustituyas por texto fijo.

### Idiomas

- La fuente IVE está en español; Catholic Doors en inglés; Hozana en español.
- Copia el texto literal en el idioma de la fuente a ese idioma del JSON. Para los otros dos idiomas conserva la traducción existente solo si es fiel; si falta, marca `translation` y no inventes. Nunca cambies oraciones canónicas.

## Verificación

Antes de terminar, para cada JSON corregido:

1. `python3 -m json.tool <archivo>` debe pasar.
2. Imprime el número de `steps` y `beads` y una lista de los `prayerRef` usados.
3. Confirma que ningún `text` contiene dos frases de oraciones distintas pegadas.

## Entrega

Devuelve un resumen corto con: número de archivos corregidos, cambios estructurales principales y cualquier devoción donde la fuente sea ambigua. No escribas en los JSON originales.
