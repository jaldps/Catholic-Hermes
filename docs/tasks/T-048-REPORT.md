# T-048 · Rosario y Coronilla en horizontal

Entrega parcial 5/6. Base: `origin/CatholicGPT` @ `e4a5cdd`, con T-047 integrado y aprobado.

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
