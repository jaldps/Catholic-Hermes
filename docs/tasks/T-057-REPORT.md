# T-057 · «Otras coronillas»: informe

Motor e interfaz de las 55 coronillas sobre la pantalla del Rosario. No se tocó contenido (JSON, imágenes), `ui/design/*`, `ui/theme/*`, `assets/`, versiones, firma ni Room.

## Qué se hizo

- **`ChapletCatalog`** (`data/repository/`): `install` / `default` / `override`; lee los 55 JSON y `images/credits.json`; resuelve `prayerRef` contra `vaticanPrayers` (PrayerAndNovenaData + `prayers/extra.json`).
- **`ChapletPlan` (StepMap genérico)**: cada repetición es un paso con su cuenta; los pasos sin cuenta (`bead: null`) descansan en la cuenta anterior; las cuentas sin paso se atenúan y no se pueden elegir (toque, arrastre, `setProgress`).
- **`DevotionSpec`** sustituye las ramas `isRosary` de tags, títulos y mapa. Rosario y Misericordia conservan sus tags.
- **Cuentas parametrizables**: `BeadStyle` (colores, atenuadas), `BeadKind.SEPARATOR/KNOT`, mapa en anillo para hilos que no son rosario. Sin hilo (Santa Ana): sin hilo y sin columna.
- **`RosarySession`**: `Devotion.OTHER` + `chapletId` + `chapletStep`; `fromTab(2)` = OTHER; el `Saver` y `RosarySessionStore` leen sesiones antiguas.
- **UI**: tercer segmento «Otras ▾» (`other_chaplets_segment`), catálogo en hoja (`chaplet_catalog`: búsqueda, «Para hoy», 6 grupos, filas con miniatura/título/cuándo), hoja de variables (`chaplet_variables_sheet`), hoja de información (promesa y nota histórica solo aquí), créditos «Imagen: …» / «Imagen referencial» visibles.
- **Voces**: V/R por `speakDialogue`; las rúbricas se muestran pequeñas y no se narran (test).
- **Variables**: solo en memoria (`ChapletVariablesMemory`); test de que no llegan al disco.
- **Orar**: fila «Otras coronillas» (`nav_other_chaplets`); la fila «Corona de San Miguel» abre `corona-san-miguel-raccolta` (el texto de la oración sigue en la búsqueda global y en los pasos de la coronilla).
- **Seguimiento T-048**: (1) 18 dp reservados con el fundido; (2) `hang` 18 dp con `fontScale > 1,15`; (3) `Spacer` 20 dp tras el mapa; (4) «Guía» activa con acento al 14 %; (5) háptico `CONFIRM` al cruzar una cuenta grande al arrastrar.

## Cambios fuera de la pantalla del Rosario (explicados)

- `ui/MainApp.kt`: 3 líneas. `onOpenOtherChaplets = { openRosary(2) }` y `onOpenChapletById` (fijan `ChapletLaunch` y abren la pestaña 2) hacia Orar, y `onNavigateToOtherChaplet = { openRosary(2) }` hacia Home. Necesarias porque el único punto de entrada a la pantalla es `openRosary(tab)`.
- `ui/screens/HomeScreen.kt`: un parámetro y un `when` en «Continuar mi oración», para reanudar una coronilla en curso (si no, abría el Rosario).
- `ui/screens/PrayersAndNovenasScreen.kt`: fila nueva y desvío de la fila de San Miguel.
- `app/build.gradle.kts`: `src/main/assets/chaplets` como recurso de los tests JVM.

## Desviaciones

- El **segmento «Otras ▾»** es un tercer elemento junto a la pista de cristal (que solo conoce 2 posiciones y está en `ui/design`). Con otra coronilla abierta la pista descansa sin selección. A 411 dp la pista usa etiquetas cortas («Rosario», «Coronilla»); la cabecera cambia, el resto del Rosario no.
- Tests existentes tocados: `RosarySessionTest` (`opening(7)` ahora es OTHER; el rango es 0..2) y `RosaryInteractionTest` (acepta la etiqueta corta del segmento).
- Los hashes de píxeles de `regression-geometry.json` **no se regeneraron**: la cabecera cambia por diseño al añadir el segmento.
- Defecto menor abierto: a 130 % en PT y 800 × 360 la etiqueta «Outra» queda ajustada al límite. Los botones de transporte vertical a 130 % parten palabras (ya ocurría antes en el Rosario).
- Los tests `RosaryFitTest` generalizado y el de `active` de `LiturgicalTtsManager` (notas de T-048) no se hicieron. «Para hoy» solo tiene hoy una coronilla (domingo, Espíritu Santo).

## Contenido que no encaja (no se modificó)

- **es/en intercambiados** (el campo `es` trae inglés y viceversa) en: `cabrini-coronilla`, `coronilla-difuntos-45`, `coronilla-lagrimas` (vr), `dos-corazones-coronilla`, `fe-coronilla`, `jesus-praga-coronilla`, `kateri-coronilla`, `maximiliano-coronilla`, `misericordia-perdon`, `reina-luz-coronilla`, `santa-ana-coronilla`, `santa-dimpna-coronilla`, `santa-filomena-coronilla`, `santisima-trinidad-coronilla`. El validador no lo detecta. La app muestra lo que hay.
- `credits.json`, `coronilla-lagrimas`: autor «Unknown authorUnknown author». Fuentes «Imagen ya presente en la app (devotions)» son notas internas: la app no las muestra.
- Solo `rosario-espiritu-santo` trae `weekdays`; ninguna trae `season`.

## Tests

Perfil del área (Rosary*, Landscape*, TabletLayout, AppNavigation, DesignNavigation, DesignContrast, Chaplet*): **362 tests, 0 fallos**. Nuevos: `ChapletCatalogTest` (10), `RosarySessionSaverTest` (5), `ChapletFlowTest` (7), `ChapletVariablesTest` (3); `ChapletShotsTest` (capturas, solo con `-Pshots.only=chaplets`).

## Rúbrica §11 (autoevaluación, sin crítico)

| 1 Espacio | 2 Jerarquía | 3 Legibilidad | 4 Ergonomía | 5 Coherencia | 6 Estados | 7 Insets | 8 Giro | 9 Accesibilidad |
|---|---|---|---|---|---|---|---|---|
| 8 | 8 | 8 | 9 | 8 | 8 | 8 | 8 | 8 |

No es una puntuación del crítico. No hay verificación en dispositivo real. Giro con coronilla abierta: no se añadió test propio (la sesión y las hojas usan el mismo estado que el Rosario).

## Capturas

`docs/tasks/T-057-shots/` (60 %, paleta de 256 colores) y una lámina por escena (`_contact_<escena>.png`): catálogo con «Para hoy», siete dolores, Santa Ana (sin hilo), lágrimas (V/R), San José (hilo de colores) y hoja de variables; Lumen claro y Sanctuary oscuro × 411×891, 891×411 y 800×360; 130 % ES y PT (`_s130`, `_s130_pt`) en Lumen claro y Sanctuary oscuro, vertical y 800×360.

## Revisión del coordinador

- **Capturas:** la matriz completa ocupaba 35 MB, porque `_contact.png` sola pesaba 17 MB. En el repositorio quedan Lumen claro, Sanctuary oscuro y el 130 %, con una lámina pequeña por escena. La matriz completa de las 4 identidades se generó y se revisó, pero no se sube.
- **Contenido corregido:** los campos `es` y `en` estaban intercambiados en 104 casos, repartidos en 19 coronillas.
  - Se corrigieron con un detector por pares que ya forma parte de `validate_chaplets.py`. Antes del arreglo da 104 errores y después 0.
  - `credits.json`: «Unknown authorUnknown author» pasa a «Unknown author» (3 entradas).
- **Pendiente menor:** en horizontal, la línea «Imagen: autor · licencia» de la columna de contexto se corta. Conviene que pase a 2 líneas o se abrevie.
