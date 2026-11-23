# T-056 · Revisión del coordinador (6 lotes, `ext/T-056` @ `4799d80`)

**VEREDICTO: CAMBIOS.** No se integra todavía.

El trabajo es mucho y en buena parte excelente. El fallo grave es mecánico y se arregla rápido.

## Lo que está bien

- **Fidelidad comprobada palabra por palabra** contra la fuente en tres muestras:
  - IVE-07, Sagrado Corazón: idéntico, incluidos V/ y R/;
  - CD-31, San Rafael: el original inglés y su traducción ES son fieles; el hilo, medalla + 3 + 9, es correcto;
  - HZ-08, Santa Teresita: las 24 cuentas llevan la invocación y no un Gloria, como dijiste. Bien visto.
- **Derechos verificados:**
  - Catholic Doors: «Copyright © Catholic Doors Ministry», sin permiso de copia, así que `Derechos: Catholic Doors Ministry` es correcto;
  - IVE y Hozana: sin reserva, `null` correcto.
- **Decisiones correctas:**
  - la Corona de San Miguel desde `text-overrides.json` (Raccolta);
  - las traducciones publicadas por Hozana (PT de Lourdes, EN de Liberación) citadas en `sources`;
  - CD-54 con las 14 estaciones reales;
  - CD-17 sin hilo;
  - las indulgencias antiguas como `historicalNote`.
- Las IVE y Hozana (lotes 1 y 2) no tienen el error grave. Solo les afectan los cambios 2 y 4.

## Cambios pedidos

### 1. GRAVE · ES y EN intercambiados en 28 de las 34 coronillas de Catholic Doors

Tu ayudante es `L(es, en, pt)` (`T-056-data/lib.py`). En los lotes 3 a 6, los **textos de oración** de Catholic Doors se pasaron como `L(<original inglés>, <traducción española>, <pt>)`. Por eso el campo **`es` contiene el inglés y `en` contiene el español**.

Ejemplos:
- `carmen-coronilla.json`: `es` = «O most beautiful flower of Mount Carmel…» y `en` = «Oh flor más hermosa…».
- `madre-eucaristia-coronilla.json`: `es` = «0 Mother help me!».

Los títulos, resúmenes y `when` están bien; el problema son los pasos y las meditaciones.

- **Arreglo:** en las llamadas `T(...)`, `V(...)` y `med=` de los lotes 3 a 6, pon el español primero y el inglés después, y regenera.
- **Revisa también el portugués:** algunos `pt` pueden seguir en inglés (p. ej. `coronilla-liberacion`, `confianza-tribulaciones`).
- **El validador ya lo detecta:** `wrong_language` da «reads as EN, not ES». Hoy marca 28 archivos, y tiene que quedarse en 0.

### 2. Instrucciones en `meditation` → campo nuevo `rubric`

La especificación tiene ahora `rubric`: la instrucción de la fuente, que la app muestra en pequeño y no narra. Hay 22 pasos con instrucciones metidas en `meditation`. Muévelas a `rubric`:
- «Cuenta grande»
- «Al final, tres veces»
- «En la medalla»
- «En la última cuenta, seguida de un Padre Nuestro»
- «Al principio de cada decena, nombrar una…»
- «Al final de cada misterio»

`meditation` queda **solo** para meditaciones reales: el título del misterio, del dolor o de la llaga, o la cita bíblica. El validador lo marca («looks like an instruction»).

### 3. Erratas evidentes: se corrigen y se anotan, no se conservan

La regla 6 de la especificación ya lo decía para IVE, y vale para todas las fuentes. El usuario no debe leer «holly» por «holy». Corrige en el texto original, anótalo en `notes` y traduce desde el texto corregido:

| Archivo | Errata | Corrección |
|---|---|---|
| `coronilla-lagrimas` | «most holly Mothers tears» | «most holy Mother's tears» |
| `madre-eucaristia-coronilla` | «0 Mother» | «O Mother» |
| `madre-eucaristia-coronilla` | «I Believe, that He is the, only begotten» | puntuación normal |
| `san-gabriel-coronilla` | «though among women», «they womb» | «thou among women», «thy womb» |
| `unidad-coronilla` | «grat mercy» | «great mercy» |
| `santisima-trinidad-coronilla` | «make hast» | «make haste» |

Revisa si hay más en los demás archivos.

### 4. IVE-06 · Quitar la oración atribuida a María Valtorta (regla 4)

`almas-purgatorio-coronilla.json` incluye la oración final «dictada a María Valtorta (24-10-1944)». La obra de Valtorta estuvo en el Índice y la Congregación para la Doctrina de la Fe reiteró en 1985 que no puede considerarse de origen sobrenatural. La regla 4 de la especificación excluye los textos que dependen de revelaciones privadas no reconocidas.

- **Quita** ese paso y su `historicalNote`.
- Anótalo en `notes` y en el REPORT («omitida por la regla 4»).
- El resto de la coronilla se mantiene.

### 5. CD-47 y CD-57 · Se conservan, con su origen dicho con honradez

El coordinador buscó por su cuenta y no encontró documentado el origen de ninguna de las dos, ni revelación ni autor. Se quedan, pero añade lo mismo en el `summary` de los tres idiomas y en `notes`:
- ES: «Origen no documentado; texto tal como lo publica Catholic Doors Ministry».
- EN y PT: el equivalente.

## Validar antes de entregar

```sh
python3 docs/tasks/tools/validate_chaplets.py app/src/main/assets/chaplets/*.json
```

Tiene que dar **0 errores**. El validador es nuevo, con dos comprobaciones añadidas, así que actualiza tu rama desde `CatholicGPT`.

Con eso se integra entero. Después sigue T-058, las imágenes.

## Revisión 2 (23-nov) · APROBADO · CERRADO

`ext/T-056` @ `05b29b5`.

- **Validador completo, sin `--partial`:** 55 archivos, **0 errores**. Queda 1 aviso explicado (CD-01, cuentas iniciales).
- **ES y EN en su sitio.** Se comprobaron Carmen y Madre de la Eucaristía: en ES va la traducción, en EN el original de Catholic Doors.
- **`rubric`:** las 22 instrucciones están fuera de `meditation`.
- **Erratas:** corregidas («holly», «0 Mother», «grat», «they womb», «make hast»).
- **Valtorta:** fuera de IVE-06. Solo queda la nota «Omitida por la regla 4».
- **CD-47 y CD-57:** llevan «Origen no documentado; texto tal como lo publica Catholic Doors Ministry».
- **Detalle menor, no bloquea:** la nota de `almas-purgatorio-coronilla.json` cita «IVE-17» donde corresponde IVE-06. Corrígelo en tu próxima entrega.

**Integrado en `CatholicGPT`.** Los JSON no cambian la app hasta que exista el motor (T-057, Codex, tras L1). Siguiente encargo: **T-058**, las imágenes, una propia por coronilla y obligatoria (`validate_chaplets.py --images`).
