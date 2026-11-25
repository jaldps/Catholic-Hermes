# Insets laterales del Rosario en horizontal — diagnóstico y corrección

**Rama:** `ext/fix-rosary-insets` (desde `origin/CatholicGPT`, 1.9.2). T-048 está CERRADO; no se repitió su auditoría ni su matriz de capturas.

## Diagnóstico

Sí existe duplicación, y está en la causa compartida, no en un control suelto:

- `LumenKit.Scaffold` ya aplica `Modifier.padding(start = railInset + safe.start, end = safe.end)` al contenido (`LumenKit.kt:212`) antes de que se componga la pantalla.
- `RosaryScreen` volvía a sumar `safe.start` en la posición del hilo y de la fila de atrás (`LandscapeRosaryContext`), y `safe.end` en el grupo de acciones (`LandscapeRosaryActions` y el cálculo de `AppSupportingPaneLayout`).

A 800×360, Lumen claro, con status 24, navegación inferior 16 y gestos laterales 32, el recorte izquierdo movía el hilo de 40 a 72 dp (+32 de más) y el recorte derecho estrechaba el texto en 60 dp y desplazaba «Siguiente» en 60 dp, cuando la spec prevé 28 dp (`safeEnd + 8` frente a la holgura base de 12).

## Medidas reales (coordenadas de ventana, dp)

| Caso | Fase | Panel | Hilo x | Texto x | Texto ancho | «Siguiente» derecha |
|---|---|---:|---:|---:|---:|---:|
| Sin recorte | antes | 256 | 40 | 280 | 424 | 780 |
| Recorte izq. 32 | antes | 256 | **72** | 312 | 392 | 780 |
| Recorte der. 32 | antes | 256 | 40 | 280 | **364** | **720** |
| Sin recorte | después | 256 | 40 | 280 | 424 | 780 |
| Recorte izq. 32 | después | 256 | **40** | 312 | 392 | 780 |
| Recorte der. 32 | después | 256 | 40 | 280 | **396** | **752** |

Contraste con `LANDSCAPE.md` §4.2:

-_panel de 256_ y hilo en `max(safeStart, gestos) + 8 = 40` en los tres casos: **cumplido tras el arreglo**.
- Texto: 280 = `W_L + 20 + 4` de la costilla compartida (la spec da 276 sin ese filete); con recorte derecho 396 = 424 − 28, como manda `safeEnd + 8`. El desplazamiento de 32 con recorte izquierdo es **una sola aplicación** de `safeStart`, no duplicación.
- «Siguiente» sigue siendo 56×56, centrado en `y = 252` (0,70 H), con la derecha en 752 ≤ borde seguro 768.

La prueba `RosaryInsetsMainAppTest` usa `MainApp` real mediante `LandscapeRealAppFixture`, no `RosaryScreen` aislada, y deja las medidas en `app/build/audit/rosary_insets_mainapp.json`.

## Cambios

- `RosaryScreen.kt` (solo ramas `LandscapePhone`):
  - hilo: `start = max(0, gestos - safe.start) + 8`;
  - fila de atrás: `padding(start = max(8, 16 - safe.start))`;
  - acciones: `edge = max(8, 12 - safe.end)`.
- `AppComponents.kt` (`AppSupportingPaneLayout`, solo `split` horizontal): el ancho del grupo de acciones pasa de `72 + max(12, safe.end + 8)` a `72 + max(8, 12 - safe.end)`.

No se tocaron vertical, tableta, las cuatro identidades, accesibilidad, estado al girar, versiones, firma, Room, contenido ni `dist/`. No se generó APK ni se integró en otra rama.

## Pruebas (solo las afectadas)

| Clase | Tests | Resultado |
|---|---:|---|
| `RosaryInsetsMainAppTest` (nueva, regresión) | 1 | 1/1 |
| `RosaryLandscapeGeometryTest` | 5 | 5/5 |
| `RosaryInteractionTest` | 10 | 10/10 |

Ejecución conjunta: **16 tests, 0 fallos**, `BUILD SUCCESSFUL in 3m 22s`. Las aserciones antiguas de `RosaryLandscapeGeometryTest` esperaban el doble inset en coordenadas de `RosaryScreen` aislada; se actualizaron a su sistema de coordenadas y el control del borde seguro absoluto quedó en la prueba con `MainApp`.
