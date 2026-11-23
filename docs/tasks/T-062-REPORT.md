# T-062 · Pasada de interfaz con las skills de diseño (vertical)

- **Rama:** `ext/T-062`, desde `origin/CatholicGPT` actualizado.
- **Chuleta:** `docs/tasks/T-062-data/skills-notes.md` (5 reglas × 8 skills, traducidas a Compose).
- **Método:** una pantalla por commit y push; capturas antes/después al 60 % en `docs/tasks/T-062-shots/<n>-<pantalla>/`, mínimo Lumen claro y Sanctuary oscuro. Solo se cambia lo que se justifica con una regla de las skills y se ve en la captura.
- **Lista de 18 puntos:** la del encargo `docs/tasks/T-062.md`.

## Pantallas

| # | Pantalla | Estado |
|---|---|---|
| 1 | Santos (lista y ficha) | Entregado (`227e5c8`) |
| 2 | Órdenes y oficios | Entregado (este lote) |
| 3 | Devociones | Entregado (este lote) |
| 4 | 101 preguntas | Pendiente |
| 5 | Catecismo | Pendiente |
| 6 | Planes | Pendiente |
| 7 | Búsqueda | Pendiente |
| 8 | Mi camino y secciones | Pendiente |
| 9 | Confesión | Pendiente |
| 10 | Recordatorios y avisos | Pendiente |
| 11 | Explorar y Ajustes | Pendiente |
| 12 | Orar (sin Rosario) | Pendiente |
| 13 | Hoy | Pendiente |

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

## Para la capa compartida

_Pendiente._
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

## Para la capa compartida

| Pantalla | Wrapper / token | Fallo | Regla |
|---|---|---|---|
| Santos, Órdenes | `AppDetailHeader` (`ui/design`) | El antetítulo y el crédito sobre arte claro se leen débiles al 130 %. No medido, solo observado; no se cambia el color del wrapper sin medir. | better-colors: medir el par real; better-accessibility 1.4.3 |
| Órdenes | `EmblemPlate` (`DoctrineParts.kt:266`) | Fondos `Color(0xFFECE9E5)` / `Color(0xFFF6F4F1)` en vez de `palette.raised` o `groupedSurface`. | better-colors: solo tokens de rol |
| Órdenes | `AppSegmentedTabs` / barra inferior | Selección de pestaña reforzada solo por fondo; la barra inferior muestra 6 destinos con textos ajustados. | better-accessibility 1.4.11 / 2.5.8 |
| Devociones | `FactsGroup` (`DoctrineParts.kt:422`) | Los rótulos «Fiesta», «País», «Lugar»… son `styles.footnote` sin `heading()`; otros encabezados de la misma área sí lo llevan. | better-accessibility: estructura de headings |

