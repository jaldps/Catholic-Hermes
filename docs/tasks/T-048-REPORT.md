# T-048 · Rosario y Coronilla en horizontal

Entrega parcial 1/6. Base: `origin/CatholicGPT` @ `e4a5cdd`, con T-047 integrado y aprobado.

## Geometría

La rama `LandscapePhone` usa `AppSupportingPaneLayout` con panel `clamp(0,32 W, 256, 340)`, contexto a la izquierda, lectura opaca y acción de avance fija de 56 × 56. El selector se conserva en cristal en la fila de atrás. Las píldoras miden 44 dp.

**Corrección L0 aislada:** el wrapper limitaba `endActions` a 56 dp, lo que impedía el grupo de 72 dp de §4.2. El commit «L0 fix» cambia solo ese límite a `72 + max(12, safe.end + 8)`. No modifica la disposición de vertical ni tableta.

Validación: `RosaryLandscapeGeometryTest`: **5/5**, incluidos 800 × 360, 891 × 411, recortes izquierdo/derecho y navegación lateral de tres botones. Regresión nativa sin máscaras en Lumen y Sanctuary, vertical 411 × 891 y tableta 1280 × 800: **4/4 idénticas píxel a píxel**. [Medidas/pruebas](T-048-shots/validation-geometry.json), [huellas de regresión](T-048-shots/regression-geometry.json).

## Pendiente

Hilo vertical, presupuesto de las oraciones y guía, hojas, continuidad del giro y la voz, matriz de capturas, auditoría y autoevaluación final. Esta entrega parcial no declara esos puntos terminados ni una calificación visual final.

La suite completa corresponde al coordinador (regla 10); no se ejecuta para este encargo. No se ha probado en un teléfono físico.
