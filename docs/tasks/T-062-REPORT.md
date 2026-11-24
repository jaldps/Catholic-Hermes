# T-062 · Pasada de interfaz con las skills de diseño (vertical)

- **Rama:** `ext/T-062`, desde `origin/CatholicGPT` actualizado.
- **Chuleta:** `docs/tasks/T-062-data/skills-notes.md` (5 reglas × 8 skills, traducidas a Compose).
- **Método (ajuste por decisión del dueño):** se abandonan las capturas y suites NATIVE lentas de cada pantalla. El dueño decidió acelerar: aplicar las 8 skills con criterio perfeccionista en vertical **y** horizontal, verificar con tests ligeros (`createComposeRule`) y generar un **APK con `assembleDebug`**. La revisión completa con capturas se hará después, con el coordinador. Los lotes ya entregados (1–3) conservan sus capturas.
- **Verificación final:** `assembleDebug` + `*CatalogTest` → `BUILD SUCCESSFUL in 6m 42s`; APK `app/build/outputs/apk/debug/app-debug.apk` (110 MB, 2026-11-23 18:02).
- **Lista de 18 puntos:** la del encargo `docs/tasks/T-062.md`.

## Pantallas

| # | Pantalla | Estado |
|---|---|---|
| 1 | Santos (lista y ficha) | Entregado (`227e5c8`) |
| 2 | Órdenes y oficios | Entregado (este lote) |
| 3 | Devociones | Entregado (este lote) |
| 4 | 101 preguntas | Entregado (pasada rápida) |
| 5 | Catecismo | Entregado (pasada rápida) |
| 6 | Planes | Entregado (pasada rápida) |
| 7 | Búsqueda | Entregado (pasada rápida) |
| 8 | Mi camino y secciones | Entregado (pasada rápida) |
| 9 | Confesión | Entregado (pasada rápida) |
| 10 | Recordatorios y avisos | Entregado (pasada rápida) |
| 11 | Explorar y Ajustes | Entregado (pasada rápida) |
| 12 | Orar (sin Rosario) | Entregado (pasada rápida) |
| 13 | Hoy | Entregado (pasada rápida) |

## 1. Santos (lista y ficha)


- **Antes:** `docs/tasks/T-062-shots/1-santos/antes/` (Lumen claro y Sanctuary oscuro, ficha y 320 dp/130 %).
- **Después:** `docs/tasks/T-062-shots/1-santos/despues/` (mismas cuatro capturas).
- **Cambios:** `ui/screens/SaintsScreen.kt`: `appDockInset()` en el padding inferior de la lista y de la ficha; 4 dp superiores en el crédito de la imagen. Sin cambios en la capa compartida.

| # | Comprobación | Resultado |
|---|---|---|
| 1 | Iconos con contenido | Pasa: no hay botones de solo icono en la pantalla; la barra inferior aporta `contentDescription` desde la capa compartida (verificado en TABLET-REVIEW). |
| 2 | Objetivos ≥44 dp | Pasa: `AppKitChip` usa `heightIn(min = 48.dp)` y las cápsulas usan el wrapper. |
| 3 | `heading()` | Pasa: `AppSectionHeader` y los textos «Patronazgos»/«Fuentes» ya lo llevan. |
| 4 | Estado por color | Pasa: filas y chips usan `selected` en semántica además del color. |
| 5 | Agrupar con espacio | Pasa: `spacing.gutter`, `spacing.cardGap` y huecos de 6–10 dp dentro del grupo. |
| 6 | Un solo primario | Pasa: «Todos los santos» y «Escuchar» son `AppSecondaryCapsule`; ningún `AppPrimaryCapsule`. |
| 7 | Bordes compartidos | Pasa: capturas muestran margen izquierdo común de 24 dp. |
| 8 | Espacio negativo | **Fallo** a 130 %: crédito pegado al título → 4 dp superiores. Se ve en la captura después. |
| 9 | Contenido oculto | **Fallo** a 130 %: el final de la ficha no despejaba la barra flotante → `appDockInset()` en `contentPadding`. La captura después muestra una línea más despejada. |
| 10 | Botones verbos | Pasa: «Quitar filtros», «Escuchar», «Todos los santos» (navegación). |
| 11 | Errores/vacíos | Pasa: «Nada coincide… Prueba con otra búsqueda o quita los filtros» + acción «Quitar filtros». |
| 12 | Un término | Pasa: «Santos», «ficha», «filtros» coherentes en ES/EN/PT. |
| 13 | Medida 60–75 | No verificado en tableta (esta captura es vertical); el estilo `reading` es el de la capa compartida. |
| 14 | Puntuación | Pasa: «·» como separador, comillas latinas en los títulos, sin espacio antes de signos. |
| 15 | Contadores | Pasa: no hay contadores que cambien en esta pantalla. |
| 16 | Contraste | No verificado numéricamente en estas capturas; TABLET-REVIEW midió 30 pares sin fallos. El crédito sobre arte claro se ve débil al 130 %, pero no hay medición, así que no se cambia. |
| 17 | Radios anidados | Pasa: tarjeta con radio de tema y píldoras; no hay inset parejo con radios iguales. |
| 18 | Movimiento | Pasa: la pantalla no define animaciones; `reduceMotion = true` en el arnés de capturas. |

| Punto | Fallo | Ubicación | Arreglo |
|---|---|---|---|
| 8 | Crédito pegado al título a 130 % | `SaintsScreen.kt:318` | `padding(top = 4.dp, bottom = 6.dp)` |
| 9 | Final de la ficha bajo la barra flotante | `SaintsScreen.kt:176` y `:303` | `appDockInset()` en el padding inferior |

**Tests:** `com.example.Saints*` → `SaintsScreenTest` 2/2 y `SaintsCatalogTest` 4/4, 0 fallos (26m01s). `SaintImageScreenshotTest` 8/8 antes y después.


## 2. Órdenes y oficios


- **Antes/después:** `docs/tasks/T-062-shots/2-ordenes/{antes,despues}/` (Lumen claro y Lumen oscuro: lista, ficha de orden y ficha de oficio). El test `DoctrineAreaScreenshotTest` no parametriza identidad, así que Sanctuary no está disponible aquí; queda como «No verificado».
- **Sin cambios de código:** los fallos visibles pertenecen a la capa compartida o son decisiones deliberadas del proyecto; no se ha podido señalar un fallo de pantalla que se vea en la captura y se arregle con un token/wrapper existente. Las PNG de después son idénticas a las de antes.

| # | Comprobación | Resultado |
|---|---|---|
| 1 | Iconos con contenido | Pasa en la ficha: `EmblemPlate(..., contentDescription = name)`. En la lista el nombre aparece junto al emblema, así que la imagen es decorativa. |
| 2 | Objetivos ≥44 dp | Pasa: filas y pestañas usan wrappers con mínimo de 48 dp. La barra inferior de 6 destinos es compartida (ver capa compartida). |
| 3 | `heading()` | Pasa: `SectionHeader` de `DoctrineParts.kt:549` lo lleva; las filas de oficio lo agrupan por sección. |
| 4 | Estado por color | Pasa: pestañas y filas `selected` con semántica además del fondo. |
| 5 | Agrupar con espacio | Pasa: `spacing.gutter`, `Spacer(4/16/20 dp)` y `BlockGap(20/24/28 dp)`; sin divisores redundantes. |
| 6 | Un solo primario | Pasa: no hay `AppPrimaryCapsule` en la pantalla. |
| 7 | Bordes compartidos | Pasa: margen común de `gutter` en tarjetas, pestañas y texto. |
| 8 | Espacio negativo | Pasa en la lista y en la ficha: los huecos entre bloques son de 20–28 dp. El espacio entre el título del hero y las pestañas viene de `AppDetailHeader` (capa compartida). |
| 9 | Contenido oculto | Pasa: la última fila asoma a la mitad y todas las tarjetas llevan chevron. |
| 10 | Botones verbos | Pasa: «Escuchar», «Siguiente», «Anterior». |
| 11 | Errores/vacíos | Mejorable: `Sin órdenes por mostrar` / `Sin oficios por mostrar` (`DoctrineOrders.kt:78` y `:100`) solo tienen título; no se han cambiado porque esos estados no aparecen en las capturas y la regla exige ver el fallo. |
| 12 | Un término | Pasa: «Órdenes», «Oficios», «Fundada en…» coherentes en ES/EN/PT. |
| 13 | Medida 60–75 | No verificado en tableta; el texto usa el estilo `reading` compartido. |
| 14 | Puntuación | Pasa: «·» en `97 órdenes · 60 oficios` y en abreviatura-año. |
| 15 | Contadores | Pasa: `Orden ${i+1} de $total` es texto estático en la ficha; no hay dígitos que cambien en la misma composición. |
| 16 | Contraste | No verificado numéricamente. El antetítulo del hero sobre el claustro claro se ve débil; es `AppDetailHeader` (capa compartida). Colores literales en `EmblemPlate` (`DoctrineParts.kt:266`), también compartido. |
| 17 | Radios anidados | Pasa: radio de placa = `min(20.dp, size*0.28f)`, tarjetas y píldoras con radios del tema. |
| 18 | Movimiento | Pasa: sin animaciones propias; el arnés usa `reduceMotion = true`. |

**Tests:** `DoctrineAreaScreenshotTest` 2/2 para los tres estados pedidos; `com.example.Orders*` → `OrdersScreenTest` 4/4 y `OrdersCatalogTest` 5/5 (9/9, 0 fallos en el XML). El proceso Gradle tardó 46 min en este PC y se abortó después de escribir el XML, sin llegar a imprimir `BUILD SUCCESSFUL`; se anota como limitación del entorno.


## 3. Devociones


- **Antes/después:** `docs/tasks/T-062-shots/3-devociones/{antes,despues}/` (`mary`, `devotion_marian`, `devotion_christ`, claro y oscuro). El test `DevotionalPrioritiesScreenshotTest` que nombra el encargo captura Hoy/Orar/Diario, no la lista de Devociones; se usa `DoctrineAreaScreenshotTest` y se anota la discrepancia.
- **Sin cambios de código:** no hay ningún fallo de esta pantalla que se vea en la captura y se pueda arreglar con un token o wrapper existente sin tocar la capa compartida. PNG de después idénticos.

| # | Comprobación | Resultado |
|---|---|---|
| 1 | Iconos con contenido | Pasa: búsqueda y selector de región llevan etiqueta visible; el crédito de imagen es texto con `Role.Button`. |
| 2 | Objetivos ≥44 dp | Pasa: segmentos, búsqueda, filtro y cápsulas usan wrappers con mínimo de 48 dp. |
| 3 | `heading()` | Pasa en la pantalla: `SectionHeader` y «Promesas» lo llevan. Los rótulos de `FactsGroup` no lo llevan; son de `DoctrineParts.kt` (capa compartida). |
| 4 | Estado por color | Pasa: segmentos y filas con `selected` en semántica. La pestaña activa del riel es compartida. |
| 5 | Agrupar con espacio | Pasa: `Spacer(4/12/14 dp)` y `topGap = 24.dp` en los encabezados de región. |
| 6 | Un solo primario | Pasa: «Quitar filtros» es `AppSecondaryCapsule`; no hay primarios. |
| 7 | Bordes compartidos | Pasa: `spacing.gutter` en tarjetas, búsqueda y filtro. |
| 8 | Espacio negativo | Pasa: los huecos entre controles son de 4–24 dp. El espacio hero→controles viene de `AppDetailHeader` (compartido). |
| 9 | Contenido oculto | Pasa: última fila asomando y chevrons en las tarjetas. |
| 10 | Botones verbos | Pasa: «Quitar filtros», «Escuchar», «Ver más». |
| 11 | Errores/vacíos | Pasa: «Nada coincide… Prueba con otra búsqueda o quita los filtros» + «Quitar filtros». |
| 12 | Un término | Pasa: «Advocaciones marianas», «Devociones a Cristo», «Región» coherentes en ES/EN/PT. |
| 13 | Medida 60–75 | No verificado en tableta; el texto usa `styles.reading`. |
| 14 | Puntuación | Pasa: «·» en `55 advocaciones · 38 devociones` y `Advocación mariana · 1917`. Los datos de `facts` son contenido, no se tocan. |
| 15 | Contadores | Pasa: `${members.size}` es estático por grupo; no hay cifras que cambien en vivo. |
| 16 | Contraste | No verificado numéricamente. Se observa muy claro el año de las filas; sin medición no se cambia. |
| 17 | Radios anidados | Pasa: radios de tema en tarjetas, segmentos y píldoras; no hay inset parejo duplicado. |
| 18 | Movimiento | Pasa: sin animaciones propias; `reduceMotion = true` en el arnés. |

**Tests:** `DoctrineAreaScreenshotTest` 2/2 para los tres estados pedidos, 0 fallos. `com.example.Devotions*` se abortó tras 10 min sin llegar a escribir XML; queda **pendiente para la pasada final** de tests lentos.


## 4. 101 preguntas


- **Cambio:** `Spacer(36.dp)` → `24.dp` antes de «Rezar ahora» (`DoctrineQuestions.kt:725`): el hueco superaba el límite de 24 dp de P9. Afecta a vertical y horizontal por igual.
- **Resto de la lista de 18:** sin hallazgos nuevos; hay `heading()` en la pregunta, `appMaxLines(2)` en el resumen, botones verbos, puntuación correcta y un solo primario por vista.
- **Tests:** pasada final de tests lentos al terminar las 13 pantallas.


## 5. Catecismo (índice y lector)


- **Sin cambios:** el índice y el lector ya usan `appDockInset()`, `SectionHeader` con `heading()`, búsqueda elevada y `styles.reading`. La ficha del artículo usa el lector L2, fuera de alcance.
- **Para la capa compartida:** medida de lectura de 640 dp fija y barra del lector; ya documentados en `TABLET-REVIEW.md` (hallazgos 5 y 9).


## 6. Planes


- **Cambio:** `appScreenInsets().calculateBottomPadding()` → `appDockInset()` (`ReadingPlansScreen.kt:156`); evita que el pie «Empezar hoy» quede bajo la barra flotante en vertical y horizontal.
- **Resto:** sin primarios duplicados, estado vacío con wrapper, puntuación correcta.


## 7. Búsqueda


- **Cambio:** `appScreenInsets().calculateBottomPadding()` → `appDockInset()` (`SearchScreen.kt:117`), para que la última fila de resultados despeje la barra.
- **Resto:** rejilla de resultados y campos con `contentDescription` desde la capa compartida.


## 8. Mi camino y secciones


- **Sin cambios:** los tres archivos (`MyPathScreen`, `HabitsAndVirtuesScreen`, `SpiritualLifeScreen`) ya usan `appDockInset()`, `Role`/`contentDescription` en controles y `heading()` en secciones. Los varios `AppPrimaryCapsule` están en diálogos distintos, uno por vista.


## 9. Confesión


- **Cambio:** `appScreenInsets().calculateBottomPadding()` → `appDockInset()` (`ConfessionScreen.kt:78`), combinado con `imePadding()`, para que el teclado y la barra flotante no se solapen.
- **Resto:** botones verbos y semántica de pasos ya correctos.


## 10. Recordatorios y avisos


- **Sin cambios:** un solo primario por hoja («Guardar», «Iniciar novena»), estados vacíos con acción y dock ya resuelto en la pantalla.
- **Para la capa compartida:** etiquetas de la barra inferior de 6 destinos.


## 11. Explorar y Ajustes


- **Sin cambios:** un `AppPrimaryCapsule` por vista, sin huecos >24 dp y textos de ajustes con una sola voz en ES/EN/PT.


## 12. Orar (lista de oraciones y novenas)


- **Cambio:** `appScreenInsets().calculateBottomPadding()` → `appDockInset()` en el `contentPadding` de la lista (`PrayersAndNovenasScreen.kt:2690`), conservando los 40 dp extra de respiración.
- **Texto:** los tres puntos literales de la promesa de la Divina Misericordia son contenido religioso y **no se tocan**; el encargo lo prohíbe. Se anota para el coordinador.


## 13. Hoy


- **Cambio:** de tres `AppPrimaryCapsule` a uno. «Ver mis prácticas» (`HomeScreen.kt:1039`) y «Leer respuesta» (`:803`) pasan a `AppSecondaryCapsule`; queda como único primario «Ver la liturgia de hoy» (`:593`), decisión ya recogida en `LANDSCAPE.md` §6.2 y hallazgo 7 de `TABLET-REVIEW`. Ahora se cumple en vertical y horizontal.
- **Puntuación:** `HomeScreen` ya normaliza `...` a `…` en los extractos (`:1058`).


## Auditoría estática posterior (ampliación pedida por el dueño)

Tras la entrega rápida, el dueño pidió revisar el código completo con las skills aunque no hubiera captura. Se hizo una pasada estática de los 16 archivos permitidos buscando literales de color/tipo, huecos >24 dp, truncate sin vía de vuelta, controles sin semántica y títulos de sección sin `heading()`.

| Archivo | Antes | Ahora | Regla |
|---|---|---|---|
| `CatechismScreen.kt:1428,2179` | Títulos de sección del índice y de «siguiente» sin `heading()` | `Modifier.semantics { heading() }` | better-accessibility 3 |
| `MoreScreen.kt:194` | «Empieza aquí» sin `heading()` | `Modifier.semantics { heading() }` | better-accessibility 3 |
| `HomeScreen.kt:686` | «Un momento con Dios» sin `heading()` | `Modifier.semantics { heading() }` | better-accessibility 3 |
| `SpiritualLifeScreen.kt:663` | Título del diario vacío sin `heading()` | `Modifier.semantics { heading() }` | better-accessibility 3 |
| `PrayersAndNovenasScreen.kt:1033` | Título del estado vacío sin `heading()` | `Modifier.semantics { heading() }` | better-accessibility 3 |
| `SpiritualLifeScreen.kt:833` | El velo del diálogo era un `clickable` sin nombre para TalkBack | `clearAndSetSemantics {}`; el cierre sigue en el botón del diálogo | better-accessibility 1 |
| `DoctrineQuestions.kt:314` | `fontSize = styles.caption.fontSize * 0.85f` | `styles.caption` sin modificar | better-typography 1 |
| `PrayersAndNovenasScreen.kt:2123` | `Spacer(64.dp)` de dock sumado al `appDockInset()` ya aplicado | Se elimina: el `contentPadding` ya despeja la barra | better-layout 4 |

`EmblemPlate` sigue en «Para la capa compartida»: cambiar sus dos `Color(0xFF…)` por `palette.groupedSurface` alteraría la placa clara en modo oscuro; se requiere decisión de diseño.

**Verificación:** `:app:compileDebugKotlin` → `BUILD SUCCESSFUL in 3m 9s`; reconstrucción final `:app:assembleDebug` → `BUILD SUCCESSFUL in 31s`; APK `app/build/outputs/apk/debug/app-debug.apk` (110 MB, 2026-11-23 18:17).

## Pendiente para la revisión completa

- `com.example.Devotions*` quedó abortado por lentitud; se corre al final junto con la pasada completa de capturas.
- Sin capturas nuevas para los lotes 4–13 (decisión del dueño, ver `T-062-NOTE.md`).

## Para la capa compartida


_Pendiente._


| Pantalla | Wrapper / token | Fallo | Regla |
|---|---|---|---|
| Santos, Órdenes | `AppDetailHeader` (`ui/design`) | El antetítulo y el crédito sobre arte claro se leen débiles al 130 %. No medido, solo observado; no se cambia el color del wrapper sin medir. | better-colors: medir el par real; better-accessibility 1.4.3 |
| Órdenes | `EmblemPlate` (`DoctrineParts.kt:266`) | Fondos `Color(0xFFECE9E5)` / `Color(0xFFF6F4F1)` en vez de `palette.raised` o `groupedSurface`. | better-colors: solo tokens de rol |
| Órdenes | `AppSegmentedTabs` / barra inferior | Selección de pestaña reforzada solo por fondo; la barra inferior muestra 6 destinos con textos ajustados. | better-accessibility 1.4.11 / 2.5.8 |
| Devociones | `FactsGroup` (`DoctrineParts.kt:422`) | Los rótulos «Fiesta», «País», «Lugar»… son `styles.footnote` sin `heading()`; otros encabezados de la misma área sí lo llevan. | better-accessibility: estructura de headings |