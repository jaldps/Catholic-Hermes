# T-048 · Rosario y Coronilla en horizontal

Entrega del estado actual; cierre de pruebas por indicación del propietario. Base: `origin/CatholicGPT` @ `e4a5cdd`, con T-047 integrado y aprobado.

## Geometría

La rama `LandscapePhone` usa `AppSupportingPaneLayout` con panel `clamp(0,32 W, 256, 340)`, contexto a la izquierda, lectura opaca y acción de avance fija de 56 × 56. El selector se conserva en cristal en la fila de atrás. Las píldoras miden 44 dp.

**Corrección L0 aislada:** el wrapper limitaba `endActions` a 56 dp, lo que impedía el grupo de 72 dp de §4.2. El commit «L0 fix» cambia solo ese límite a `72 + max(12, safe.end + 8)`. No modifica la disposición de vertical ni tableta.

Validación: `RosaryLandscapeGeometryTest`: **5/5**, incluidos 800 × 360, 891 × 411, recortes izquierdo/derecho y navegación lateral de tres botones. Regresión nativa sin máscaras en Lumen y Sanctuary, vertical 411 × 891 y tableta 1280 × 800: **4/4 idénticas píxel a píxel**. [Medidas/pruebas](T-048-shots/validation-geometry.json), [huellas de regresión](T-048-shots/regression-geometry.json).

## Hilo vertical

`BeadReel(orientation = Vertical)` conserva intacta la rama horizontal original. Tiene paso de 24 dp, arrastre hacia arriba para avanzar, encaje sin inercia y un tic por cada cuenta cruzada. Los toques superior e inferior solo mueven una cuenta; el centro de 44 dp no mueve ninguna. Es un único control ajustable, con rango y `SetProgress`; las cuentas dibujadas no son objetivos independientes.

`RosaryReelInteractionTest`: **3/3**; geometría: **5/5**. [Resultado](T-048-shots/validation-reel.json). La primera ejecución conjunta dejó 12 fallos en la preparación de `RosaryFitTest`: la guía de prueba omitía la oración inicial de la Coronilla y sus índices no coincidían con la pantalla. Se corrigió esa preparación; no se declara aún verde el presupuesto.

## Presupuesto de lectura

`RosaryFitTest`: **12/12**, con seis oraciones por caso (**72 escenarios de texto real**): Padrenuestro, Avemaría, Gloria, Oh Jesús mío, Padre Eterno y Por su dolorosa Pasión, en ES/EN/PT, a 891 × 411 y 800 × 360, con y sin guía. Se comprueban `TextLayoutResult`, ausencia de elipsis, límites completos y rango de desplazamiento **0**.

La instrucción está en el contexto izquierdo, según §4.3; así también cabe el Padrenuestro a 800 con guía, sin necesitar la excepción de desplazamiento de la tabla anterior. Las oraciones largas siguen pudiendo desplazarse, con pista y avance fijo. El progreso conserva 32 dp visuales y 44 dp de objetivo táctil. El salto de cuentas al terminar una decena recibe el háptico firme. El botón de voz anuncia «Pausar» mientras está activo.

Perfil conjunto: **21/21** (12 presupuesto + 3 hilo + 5 geometría + 1 captura con ambas devociones). [Resultado](T-048-shots/validation-budget.json). Se revisaron las dos primeras capturas de Lumen a 800 × 360. Se detectó que el trazo del hilo necesita recorte a su región; queda pendiente esa corrección visual antes de las capturas finales.

## Hojas y accesibilidad del hilo

Misterios conserva la hoja lateral normal. Opciones usa la hoja amplia y dos columnas con desplazamiento y pista propios: las tres voces son filas verticales de 44 dp; el mapa y las partes quedan a la derecha. Ver el misterio usa imagen completa enmarcada y crédito a la izquierda, cita, meditación y fruto a la derecha. La rama vertical conserva el orden y la disposición anteriores.

También se recortó el hilo a su región (corrección detectada en la captura anterior), se añadió foco visible y ajuste con teclado, y el antetítulo del misterio identifica su número y conjunto.

Perfil de esta entrega: **12/12** (2 hojas, 4 hilo —incluido teclado—, 5 geometría y 1 captura de Opciones en ambas devociones). [Resultado](T-048-shots/validation-sheets.json). Las capturas de Opciones a 800 × 360 se revisaron visualmente.

## Giro y voz

La sesión, la hoja abierta y el ancla tienen claves guardables explícitas, por encima del wrapper. La lectura se restaura mediante bloque de texto y fracción, con medición del área visible exterior al desplazamiento. La prueba usa una oración auténtica larga que desborda ambas orientaciones; no añade espacio vacío para forzar el anclaje.

`RosaryNarrationHolder`, con ámbito de actividad, conserva el controlador y el motor. Al girar no envía `ScreenLeft` ni repite la oración; al salir sí detiene la voz. El motor retiene únicamente el contexto de aplicación. El puente de propiedad `retainForRosaryHolder` en `data/audio/LiturgicalTtsManager.kt` es necesario porque la actividad libera su motor en `onDestroy`: difiere ese cierre hasta que el holder lo suelte y evita que el motor nuevo e inactivo sustituya al de la notificación. Es el cambio de datos limitado al holder que permite el encargo; no se modificó `MainActivity`.

Perfil: **44/44**. Incluye `LandscapeRotationTest` **16/16** (giro real en Lumen y Sanctuary, tercera decena, voz y Opciones abierta; y ancla de la oración larga), holder **2/2**, narración, persistencia, geometría y hojas. [Resultado](T-048-shots/validation-rotation.json). Son pruebas con motor de voz simulado, no escucha física en un teléfono.

## Pendiente

Matriz de capturas, auditoría y autoevaluación final. Esta entrega parcial no declara esos puntos terminados ni una calificación visual final.

La suite completa corresponde al coordinador (regla 10); no se ejecuta para este encargo. No se ha probado en un teléfono físico.


## Entrega solicitada por el propietario

El propietario declara que verificó la app y pide subir el estado actual, detener pruebas/revisiones para conservar cuota y esperar instrucciones. Esa verificación es del propietario; no se atribuye al agente una comprobación que no realizó.

Antes de la pausa, el perfil final del área terminó **73/73**: presupuesto 12 (72 escenarios de texto), hilo 4, geometría 5, giro 18, holder 2 y auditoría L1 32. Las 32 auditorías no registraron hallazgos en sus muestras. [Resultado observado](T-048-shots/validation-final-area.json). Una ejecución previa falló por falta de espacio; se liberaron cachés regenerables y se repitió satisfactoriamente. La suite completa no se ejecutó.

La matriz amplia de capturas se detuvo por la petición del propietario. No se declara completada ni se inventa la aprobación visual final o la rúbrica de nueve criterios.

### Observaciones pendientes del revisor independiente

- El índice de recorrido del hilo debe trasladarse de la rama original al hilo horizontal para asegurar contexto → hilo → pie en TalkBack.
- Verificar las medidas con `MainApp` real para descartar que los insets laterales se apliquen dos veces.
- Si falta el recurso de arte, omitir el bloque vacío y su espacio asociado.

Estas observaciones quedan documentadas para la próxima indicación. No se continuaron los cambios tras la orden de entregar y esperar.


## Ronda 2 (coordinador)

Correcciones aplicadas por el agente local del coordinador sobre la integración de `ext/T-048`, a partir de `docs/tasks/T-048-REVIEW.md`. Sin commit; todo en el árbol de trabajo.

| # | Defecto | Arreglo (`RosaryScreen.kt`) |
|---|---|---|
| 1 | Matriz §12 | Regenerada con `RosaryLandscapeScreenshotTest` desde el código actual, al 60 %, más `_contact.png` (ver abajo). |
| 2 | Selector sin estado | `DevotionSwitch` compacto: segmento elegido con `p.action`, `p.onAction`, padding 3 dp, `CircleShape` (8 dp en Sanctuary); Medium en el otro. Verificado visualmente en las 4 identidades. |
| 3 | Guía | `GuideHint(compact)` sale de la columna de contexto y va en el panel de la oración entre título y texto (§14.1). `LandscapePrayerPill(switchState)`: fondo `p.action` + texto `p.onAction` y rol `Switch` con `toggleableState` (se usó `lumenPress(role = Switch)` y `toggleableState` en vez de `Modifier.toggleable` para no duplicar el manejador de clic). `RosaryFitTest` con guía comprueba que `prayer_instructions_text` queda entero dentro de la ventana y sobre el texto; excepción declarada: Padrenuestro con guía a 800 × 360 desplaza (se omiten solo sus comprobaciones de «cabe sin desplazar»). |
| 4 | Fundido | `Modifier.rosaryScrollFade` (capa `Offscreen` + `DstIn`) sobre los 3 usos (contexto, oración, columnas de hoja); la flecha pasa a `BottomEnd`. |
| 5 | «Guía» y «···» pegados | `Arrangement.spacedBy(8.dp)`. |
| 6 | Título duplicado | `PrayerTitleRow(..., !nameRepeatsText(step), ...)`. |
| 7 | TalkBack | Hilo `traversalIndex = 1f`, contexto 0 (grupo), pie 2; acciones Anterior/Siguiente del hilo mueven por paso (`onPrevious()` y nuevo `onNext = go(index + 1)`). |
| 8 | «Escuchar» fijo | El rótulo visible pasa a «Pausar»/«Pause» mientras suena. |
| 9 | Mapa recortado | En Opciones (horizontal) el mapa se dimensiona `min(ancho − 16, alto de columna − 64)` y se centra. Se usó 64 dp y no 40 dp porque el rótulo de sección y el relleno consumen ~48 dp (con 40 se veía cortado el colgante). La altura de la columna llega por un `CompositionLocal` desde `RosarySheetColumn`, porque dentro del desplazamiento `maxHeight` es infinito. |
| 10 | Marcas V./R. | `start = 24 dp` en la tarjeta y `hang = 16 dp` en `LandscapePhone` (vertical sigue en 14). |
| 11 | «Siguiente» deshabilitado | Fondo `p.action` a alfa .38 e icono atenuado. |
| 12 | Arte ausente | Bloque de miniatura solo `if (art != null)`; `MysterySheet` horizontal usa `aspectRatio` + `heightIn(max = 192.dp)`, filete/passe-partout solo en Sanctuary y `clip(12.dp)` en el resto. |
| 13 | Superficie vacía (P9) | El área de desplazamiento llega a H − B; el fondo `p.canvasTop` se dibuja con `drawBehind` solo hasta `min(contenido medido + 24 dp, H − B)`. Se corrigió en esta ronda que la posición del viewport ignoraba el relleno superior (safe.top + 4). |

### Fallo extra: `RosarySessionTest.finishingADecadeIsTheLastHailMaryToTheNextDecade`

Causa raíz: T-048 había cambiado `PrayerGuide.finishesDecade` de `to == from + 1 && sección distinta` a `to > from && sección posterior`, para que un salto del hilo también diera el háptico de «decena completada». El test (y el contrato original) dice que los saltos de más de un paso nunca son decena completada (`finishesDecade(20, 22)` falso). Arreglo: se restauró la definición original; la aserción no se tocó. Consecuencia: un salto largo del hilo que cruza una decena ya no da el háptico firme (sí el tic por cuenta).

### Capturas
`docs/tasks/T-048-shots/` (60 %): Rosario y Coronilla × 891 × 411 y 800 × 360 × Lumen, Tradicional, Cristal, Sanctuary × claro/oscuro; `_s130` (ES) y `_s130_pt` a 800 × 360 en las 4 identidades; `_pt` en 891 Lumen claro; `_cutL`, `_cutR`, `_navR` (Lumen claro, 800 × 360); hojas `_mysteries`, `_options`, `_mystery` (Lumen claro 891; Opciones también en Sanctuary); `_guia` (800 × 360, 4 identidades); regresión `l1_regresion_vertical_*` y `l1_regresion_tableta_*`; `_contact.png`. Regresión vertical 411 × 891 y tableta 1280 × 800: los 4 hashes de píxeles de `regression-geometry.json` (Lumen y Sanctuary) coinciden con la base `e4a5cdd` tras estos cambios.

### Límites
- Cristal y Tradicional: la hoja Opciones, `_cut*` y `_navR` solo en Lumen claro (§12 pide una identidad por estado).
- Sin teléfono físico ni TalkBack real; el orden de recorrido está por semántica, no escuchado.
