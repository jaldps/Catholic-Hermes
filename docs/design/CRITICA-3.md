# Crítica 3 · Interfaz horizontal, revisión 3 (commit `da4f5ed`)

**VEREDICTO: CAMBIOS** (un solo cambio obligatorio y dos retoques de redacción)

Comprobé uno por uno los 8 defectos de la crítica 2 contra las cifras del propio documento: los 8 están resueltos y sus sumas cuadran. Lo único que bloquea es una consecuencia nueva y declarada de la medición en Hoy. En el caso peor, el **único botón primario de la pantalla de inicio** («Leer las lecturas») queda bajo el pliegue en los dos tamaños. Se corrige reordenando un bloque dentro de la tarjeta de liturgia, sin añadir alto (defecto 1).

Mantengo la calibración del dueño: todo se juzga al 100 %, y al 130 % solo se exige que nada quede cortado, oculto ni inalcanzable.

## Puntuaciones (1–10, al 100 %)

| Área | 1 Espacio | 2 Jerarquía | 3 Legibilidad | 4 Ergonomía | 5 Coherencia | 6 Estados | 7 Insets/teclado | 8 Giro | 9 Accesibilidad |
|---|---|---|---|---|---|---|---|---|---|
| L0 Fundamentos | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 |
| L1 Rosario | 10 | 9 | 10 | 9 | 9 | 9 | 9 | 9 | 9 |
| L2 Lectores | 9 | 9 | 10 | 9 | 9 | 9 | 9 | 9 | 9 |
| L3 Hoy | 9 | **8** | 9 | 9 | 9 | 9 | 9 | 9 | 9 |
| L4 Orar y Leer | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 |
| L5 Lista-ficha | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 |
| L6 Mi camino, Confesión… | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 |
| L7 Hojas y diálogos | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 | 9 |
| **Global** | **9** | **8** | **9** | **9** | **9** | **9** | **9** | **9** | **9** |

## Comprobación de los 8 defectos de la crítica 2

1. **Pie del estudio bíblico: resuelto.**
   - El pie suma 44 + 12 + 48 + 12 + 44 + 12 + 114 = 286, dentro de 339 − 2 × 20 = 299.
   - «Siguiente ›» termina en 891 − 20 = 871, así que su centro queda en 814: a 77 dp del borde, dentro de los 120 exigidos.
   - El selector de capítulo pasa a la cabecera del texto, con 44 dp de alto.
   - A 800, el pie del texto suma 362, dentro de 760.
2. **Presupuestos con huecos: resuelto.**
   - La regla de §3.11 queda escrita y los segmentos miden 44 dp.
   - Las sumas, recalculadas con los huecos:

   | Pantalla | Tamaño | Suma | Límite |
   |---|---|---|---|
   | Novena (panel) | 800 | 166 + 28 + 8 + 44 = 246 | 288 |
   | Pie de la novena | 800 | 130 + 110 + 140 + 24 = 404 | 560 − 40 = 520 |
   | Confesión | 800 | 166 + 28 + 8 + 132 = 334 | 344 |
   | Orar | 891 | 189 + 8 + 132 + 8 + 48 = 385 | 395 |
   | Orar | 800 | 166 + 8 + 132 = 306 | 344 |
   | Hábitos | 891 / 800 | 345 / 322 | 395 / 344 |
   | Espacio libre del panel del lector | 891 / 800 | 114 / 86 | — |

   - El centro de «Día siguiente ›» queda a unos 90 dp del borde, dentro de los 120.
   - Todas cuadran.
3. **Calendario: resuelto.**
   - En el panel `kind = Today` de 227 dp, el contenido mide 584 y el interior 544.
   - El carril A mide 7 × 44 + 16 = 324 de ancho y 44 + 20 + 264 = 328 de alto, dentro de 359.
   - El carril B mide 544 − 324 − 16 = 204, una columna de contexto por encima del mínimo de 150.
   - El panel con 2 segmentos: 189 + 8 + 88 = 285 a 891 y 262 a 800, dentro del límite.
   - A 800 queda un carril de 472 dp con 5 semanas visibles: 284, dentro de 308.
   - Las celdas miden 44.
4. **Teclado: resuelto, y el ajuste del diseñador es correcto.**
   - La sección como prefijo de la línea secundaria da filas de 68 dp: 56 + 68 = 124, dentro de 134.
   - Mi antetítulo habría dado 56 + 86 = 142, que no cabe. El prefijo es mejor solución que la mía.
   - Conservar `search_group_*` como `collectionInfo` mantiene la agrupación para TalkBack.
   - El criterio de las listas con alternativa (fila completa si mide ≤ 78; si no, su primera línea y la tecla «Buscar») es honesto.
5. **Cuentas: resuelto con la opción (b).**
   - La zona actual mide 44; la superior y la inferior, (203 − 44) / 2 = 79 a 891 y (152 − 44) / 2 = 54 a 800. Las tres pasan de 44 dp.
   - Los saltos largos se hacen arrastrando o desde el mapa de Opciones.
   - TalkBack lo maneja como control ajustable (`ProgressBarRangeInfo` + `SetProgress`), y la exención está documentada.
   - Se siguen viendo 8 cuentas a 891 y 6 a 800, así que la decena se lee como un hilo. La elección es correcta.
6. **Rúbrica: resuelto.**
   - Criterio 1: columnas principales ≥ 220 y de contexto ≥ 150. Lo cumplen los carriles de Hoy (228 a 800), los de Confesión (260), la lista (272), el panel del lector (240), el contexto del Rosario (152) y el carril B del Calendario (204).
   - Criterio 9: el orden de TalkBack se define panel a panel en las pantallas con panel de apoyo.
7. **Presupuesto de Hoy: medido de forma honesta, pero el resultado no es aceptable tal cual.** Ver el defecto 1.
   - Las alturas son coherentes con el código:
     - fila de 2 líneas: 12 + 48 + 2 + 18 + 12 = 92;
     - cabecera de sección: 10 + 25 + 12 = 47;
     - carril A: 47 + 92 + 92 + 12 + 47 = 290, dentro de 308 y de 359;
     - carril B, la tarjeta de liturgia hasta la primera lectura: 274.
8. **Lista de una categoría: resuelto.** Pasa a la variante póster, con el recuento en la meta.

## ¿Es aceptable que «Leer las lecturas» quede bajo el pliegue?

**No, tal como está.** Razones:
- Es la pantalla de inicio, y el dueño pidió que en Hoy «se vea lo esencial sin desplazar».
- La revisión 2 dejó un solo botón primario relleno en la pantalla, precisamente para que destaque; tenerlo fuera de la vista en ambos tamaños contradice esa jerarquía (criterio 2).
- Que la cabecera y las filas de la tarjeta se puedan tocar mitiga, pero no sustituye a la acción principal visible.

En cambio, que «Ver mis prácticas» (secundario) quede bajo el pliegue sí es aceptable: el título de «Mis prácticas» y el borde de su tarjeta asoman y avisan de que hay más. Lo que no es aceptable es perder el primario, y se arregla sin ganar alto: basta reordenar dentro de la tarjeta.

## Defectos que quedan

1. **L3 §6.3 (obligatorio) · El primario de Hoy debe verse sin desplazar a 100 % en los dos tamaños.**
   - **Cambio:** dentro de la tarjeta de liturgia (`daily_mass_card`), «Leer las lecturas» pasa a ir **justo después de la vista previa del Evangelio y su fuente**. Las filas de primera lectura (`home_first_reading_row`) y de reflexión van debajo, como enlaces secundarios.
   - **Nuevo presupuesto del carril B:** 20 + 22 + 6 + 90 + 12 + 18 (= 168) + 14 + 52 = **234**, dentro de 308 (800) y de 359 (891).
   - **Mismo orden en vertical,** para no mantener dos versiones (P8). Es la secuencia natural: ver el Evangelio y luego «Leer las lecturas». Se declara junto a la decisión vetable 7 de §14.
   - **Lista de esenciales de §6.3 y §6.5:** añadir `home_continue_reading` (o la etiqueta real del botón) en los dos tamaños, para que `ESSENTIAL_BELOW_FOLD` lo vigile.
   - **Bocetos:** actualizar el de §6.1 y el del resumen, que hoy dibujan el botón visible pero después de «Primera lectura». Con el cambio, el botón va antes de esa fila.
2. **L5 §8.6 (retoque de redacción) · Falta la alternativa del teclado en la Búsqueda.** La fila de 68 dp supone un título de una línea, pero un título de resultado de dos líneas (≈ 92 dp) da 56 + 92 = 148, que no cabe en 134.
   - **Cambio:** añadir a la Búsqueda la misma alternativa que ya tienen las listas en §8.4: «si la fila mide más de 78 dp, su primera línea completa y la tecla "Buscar" visible».
3. **Resumen (retoque) · El texto de Hoy debe coincidir con el cambio 1.**
   - **Cambio:** decir «sin deslizar, en los dos tamaños: el día, las dos oraciones del momento, el comienzo del Evangelio y el botón "Leer las lecturas"; deslizando un poco, "Mis prácticas" con su botón y las demás lecturas».
   - **Boceto del resumen:** poner `[■ Leer las lecturas ▸]` antes de «Primera lectura ›».

## Fidelidad del resumen del dueño

Por lo demás, el resumen es fiel a la especificación:
- **Biblia:** el boceto coincide; el selector de capítulo va en la cabecera y el pie es ‹ · Escuchar · Aa · Siguiente ›.
- **Lectores:** queda claro que solo los lectores en orden tienen el pie «Anterior / Siguiente», y que la novena lleva «Día 3 de 9 ▾» en ese pie.
- **Decisiones vetables nuevas:** se añaden la miniatura del Rosario en teléfono pequeño (9), y los filtros en hoja lateral y la tira de arte que desaparece al deslizar (10).
- **Rosario:** se mantiene la excepción del Padrenuestro con la guía en teléfono pequeño.
- **Hoy:** el resumen ya no promete ver los dos botones sin deslizar. Solo falta ajustarlo al cambio 1 (retoque 3).

Con el cambio 1 y los dos retoques, aprobaría la especificación: el Rosario, los lectores y el sistema de paneles quedan a un nivel que se puede entregar con orgullo.

---

## Confirmación limitada (tras los cambios del coordinador)

**VEREDICTO: APROBADO.** L3 Hoy y la fila global quedan en 9 en los nueve criterios.

**Lo comprobado:**
- **§6.1–§6.5:** «Leer las lecturas» va tras el Evangelio. El carril B suma 234, por debajo de 308 y de 359, y `home_continue_reading` figura entre los esenciales.
- **§8.6:** la alternativa con el teclado abierto para resultados de dos líneas.
- **El resumen:** Hoy, la decisión 7 y la etiqueta corta «Ruta» / «Rota» aparecen igual en §3.5, §14 y en la decisión 5.

**Arreglo editorial aplicado:** §17.4 se movió al final del documento.
