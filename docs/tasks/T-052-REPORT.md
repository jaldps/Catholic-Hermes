# T-052 · Informe de entrega — L5 listas con ficha en horizontal

- **Rama:** `ext/T-052`, desde `origin/CatholicGPT` en `cdd0458` (apertura de T-052).
- Rama: `ext/T-052`, desde `origin/CatholicGPT` en `cdd0458`; luego fusionada con `origin/CatholicGPT` @ `c8e5a97` (1.9.0, con T-062 integrado). Conflicto en `SaintsScreen.kt` resuelto conservando el `AppTwoPane` de T-052 y aplicando el `appDockInset()` de T-062 en la lista.
- **Entrega completa: 7 de 7 pantallas.**

## 1. Santos — `SaintsScreen.kt`

- Migrada al nuevo `AppTwoPane` de L0 con `rememberAppListDetailState(keys, initialKey, key = "saints_list_detail")`. Seleccionar una fila cambia la ficha del panel; no apila página.
- `listHeader`: `AppHeroHeader` de Santos (tira de 96 dp en horizontal, desaparece al desplazar).
- Barra adhesiva de 56 dp con `saints_search` y `list_filter_button`. Los filtros de hoy (mes y «solo con ficha») pasan a la hoja lateral `saints_filter_sheet`; con filtros activos aparece su fila de chips para quitarlos.
- Estado elevado con claves explícitas: `saints_query`, `saints_month`, `saints_profiles_only`, `saints_filters_open` y `saints_list_detail`. No hay `rememberSaveable` dentro de las ranuras del wrapper.
- En vertical, la ficha conserva `saint_detail_back` y vuelve a la lista con `state.showList()`. En horizontal no se muestra ese botón porque la lista sigue visible.
- `onExitContext` (búsqueda global) sigue saliendo de la pantalla cuando la ficha se abrió desde fuera.
- Se conservan las etiquetas: `saints_screen`, `saints_search`, `saints_month_picker`, `saints_month_*`, `saints_profiles_only`, `saint_row_*`, `saint_detail`, `saint_detail_back`, `saint_image_credit`, `list_empty`, `saints_clear_filters`.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Barra y filas visibles (§8.3) | 8/10 | Wrapper L0: lista 292 dp a 891; tira 96 + barra 56; la hoja de filtros sustituye los controles inline. Medición fina pendiente con el arnés. |
| 2 | Teclado (§8.4) | Pendiente | Sin captura con teclado todavía. |
| 3 | Medida de lectura (§3.10) | 8/10 | La ficha usa `AppDetailHeader` y tarjetas existentes dentro de la columna del wrapper. |
| 4 | Selección sin apilar y atrás | 9/10 | `state.select` / `state.showList`; test `SaintsLandscapeL5Test`. |
| 5 | Giro (§3.9, §2.4) | 8/10 | Estado elevado con claves; falta el test `LandscapeRotationTest` específico. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Botón de filtro de 44 dp del wrapper; sin objetivos nuevos más pequeños. |
| 7 | Texto no elidido | 9/10 | `AppListRow` existente, título y subtítulo sin cortes nuevos. |
| 8 | Tests de área | Pendiente | `SaintsScreenTest` en ejecución; test propio de horizontal añadido. |
| 9 | Identidades y estados §16 | 8/10 | Mismo theming; `list_empty` dentro de la lista del wrapper. |

## 2. Órdenes y oficios — `DoctrineAndHistoryScreen.kt`, `DoctrineOrders.kt`, `DoctrineParts.kt`

- En horizontal (teléfono y tableta) la pantalla entra en `AppTwoPane` con `rememberAppListDetailState(keys, key = "orders_list_detail")`; el estado de búsqueda, segmento y hoja se eleva con claves `orders_query`, `orders_segment` y `orders_filters_open`.
- Barra fija de 56 dp con `orders_search` y `list_filter_button`. Los segmentos Órdenes/Oficios salen de la lista y van a la hoja `orders_filter_sheet`; al elegir Oficios aparece el chip `orders_segment_chip` para volver.
- `OrdersRoot` acepta `searchQuery`, `segmentIndex`, `headerItem` y `onSelect`; en vertical sigue con su estado local y la lista sin cambios. Seleccionar una orden usa `state.select(...)` y sustituye la ficha, sin apilar.
- La búsqueda filtra órdenes por nombre/abreviatura y oficios por nombre; las etiquetas originales (`orders_list`, `orders_offices_tabs`, `order_*`, `office_*`) se conservan.
- Se añadió `appIsLandscapePhone()` a la condición de dos paneles: antes solo se activaba en tableta, aunque el wrapper soporta teléfono horizontal.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Barra y filas visibles (§8.3) | 8/10 | `list_sticky_bar` y `two_pane` en el test a 891×411; medición fina de filas pendiente en capturas. |
| 2 | Teclado (§8.4) | No verificado | Sin captura con teclado aún. |
| 3 | Medida de lectura (§3.10) | 8/10 | La ficha usa `AppTwoPane` y el lector existente. |
| 4 | Selección sin apilar y atrás | 9/10 | `selectingAnOrderDoesNotPushAnotherPane` 1/1. |
| 5 | Giro (§3.9, §2.4) | 8/10 | Estado elevado con claves; `LandscapeRotationTest` específico pendiente. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Wrappers L0 de 44/48 dp; ningún control nuevo pequeño. |
| 7 | Texto no elidido | 8/10 | `LeadRow` existente; sin cortes nuevos. |
| 8 | Tests de área | 9/10 | `OrdersLandscapeL5Test` 2/2; `OrdersScreenTest` se deja para la pasada final por su coste. |
| 9 | Identidades y estados §16 | 8/10 | Mismo theming; estados vacíos dentro de la lista. |

## 3. Devociones — `DevotionsExplorerScreen.kt`, `DoctrineAndHistoryScreen.kt`, `DoctrineParts.kt`

- Misma migración que Órdenes: `AppTwoPane` con `rememberAppListDetailState(keys, key = "devotions_list_detail")`; estado elevado en `devotions_query`, `devotions_category`, `devotions_region` y `devotions_filters_open`.
- Barra fija con `devotions_search` y `list_filter_button`; categoría (Nuestra Señora/Cristo) y región pasan a la hoja `devotions_filter_sheet`, con chips `devotions_category_chip` y `devotions_region_chip` cuando están activos.
- `DevotionsExplorerScreen` acepta `searchQuery`, `categoryIndex`, `regionFilter`, `headerItem` y `onSelect`; en vertical conserva sus controles y claves actuales.
- Seleccionar una tarjeta usa `state.select(...)`; la ficha se sustituye sin apilar. Las etiquetas `devotions_explorer_screen`, `devotions_search_input`, `devotions_category_picker`, `devotions_region_picker`, `devotion_card_*` se conservan.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Barra y filas visibles | 8/10 | `list_sticky_bar` y `two_pane` en test 891×411. |
| 2 | Teclado | No verificado | Sin captura con teclado. |
| 3 | Medida de lectura | 8/10 | Ficha existente dentro del panel del wrapper. |
| 4 | Selección sin apilar y atrás | 9/10 | `selectingADevotionDoesNotPushAnotherPane` 1/1. |
| 5 | Giro | 8/10 | Estado elevado con claves; test de rotación pendiente. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Solo wrappers L0. |
| 7 | Texto no elidido | 8/10 | `LeadRow` existente. |
| 8 | Tests de área | 9/10 | `DevotionsLandscapeL5Test` 2/2. |
| 9 | Identidades y estados | 8/10 | Theming y estados vacíos heredados. |

## 4. 101 preguntas — `DoctrineQuestions.kt`, `DoctrineAndHistoryScreen.kt`, `DoctrineParts.kt`

- `AppTwoPane` con `rememberAppListDetailState(keys, key = "questions_list_detail")`, estado elevado en `questions_query`, `questions_section` y `questions_filters_open`.
- Barra fija con `questions_search` y `list_filter_button`; el filtro de tema va en la hoja `questions_filter_sheet` (`questions_section_picker`) y aparece como chip `questions_section_chip`.
- `QuestionsRoot` acepta `searchQuery`, `sectionFilter`, `headerItem` y `onSelect`. El filtro por sección muestra solo las preguntas de ese tema, con `SectionHeader` y su recuento.
- `catholic_qa_prev` y `catholic_qa_next` siguen mostrando el título completo en dos líneas y ≥44 dp dentro de la ficha.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Barra y filas visibles | 8/10 | `list_sticky_bar` y `two_pane` en test 891×411. |
| 2 | Teclado | 8/10 | `performTextInput` sobre el campo elevado en el test. |
| 3 | Medida de lectura | 8/10 | Ficha existente con `appReadingMeasure()`. |
| 4 | Selección sin apilar y atrás | 9/10 | `selectingAQuestionDoesNotPushAnotherPane` 1/1. |
| 5 | Giro | 8/10 | Estado elevado con claves; test de rotación pendiente. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Solo wrappers L0. |
| 7 | Texto no elidido | 9/10 | `maxTitleLines = 4` y navegación con título completo. |
| 8 | Tests de área | 9/10 | `QuestionsLandscapeL5Test` 2/2. |
| 9 | Identidades y estados | 8/10 | Theming y estados vacíos heredados. |

## 5. Catecismo — `CatechismScreen.kt`

- En horizontal se usa `AppTwoPane` con `rememberAppListDetailState(cccKeys, key = "catechism_list_detail")`. El índice queda en la lista con búsqueda fija `ccc_search`; el campo inline se oculta (`showSearch = false`) y el resultado abre el lector en el panel derecho sin apilar.
- El estado de la pila (`stack`), consulta, expansiones y Compendio sigue elevado y sobrevive al giro. No hay hoja de filtros: el encargo no la pide para esta pantalla.
- «Compendio» (`catechism_open_compendium`) sigue al pie del índice y el artículo abre el lector L2.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Barra y filas visibles | 8/10 | `two_pane` y `list_sticky_bar` en test 891×411. |
| 2 | Teclado | 8/10 | `performTextInput` + `waitUntil` de resultados en el test. |
| 3 | Medida de lectura | 9/10 | `appReadingMeasure()` en el lector existente. |
| 4 | Selección sin apilar y atrás | 9/10 | `openingASearchResultKeepsTheTwoPanes` 1/1. |
| 5 | Giro | 8/10 | Pila y consulta sobreviven al giro. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Solo wrappers L0. |
| 7 | Texto no elidido | 9/10 | Títulos de sección sin cortes nuevos. |
| 8 | Tests de área | 9/10 | `CatechismLandscapeL5Test` 2/2. |
| 9 | Identidades y estados | 8/10 | Theming y `ccc_search_empty` heredados. |

## 6. Planes — `ReadingPlansScreen.kt`

- En horizontal se usa `AppTwoPane` con `rememberAppListDetailState(planKeys, key = "plans_list_detail")`. La lista mantiene la cabecera y las tarjetas; la ficha se muestra a la derecha con «Empezar hoy» al pie (`plan_start`).
- Sin barra de búsqueda ni hoja de filtros, según §8.2. El estado `selectedId` se sincroniza con la selección del wrapper.
- En vertical la pantalla conserva exactamente su columna anterior.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Barra y filas visibles | 8/10 | `two_pane` y lista en test 891×411. |
| 2 | Teclado | No aplica | Sin campo de texto en esta pantalla. |
| 3 | Medida de lectura | 8/10 | `PlanDetail` heredado. |
| 4 | Selección sin apilar y atrás | 9/10 | `selectingAPlanKeepsTheDetailBesideTheList` 1/1. |
| 5 | Giro | 8/10 | `selectedId` y estado del wrapper con claves. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Solo wrappers L0. |
| 7 | Texto no elidido | 9/10 | Tarjetas y ficha heredadas. |
| 8 | Tests de área | 9/10 | `PlansLandscapeL5Test` 2/2. |
| 9 | Identidades y estados | 8/10 | Theming y `AppEmptyState` heredados. |

## 7. Búsqueda — `SearchScreen.kt`

- En horizontal no hay segundo panel: barra adhesiva de 56 dp con `search_field`, fila de chips `search_section_chips` que se oculta con el teclado, y resultados en rejilla de 2 columnas (`LazyVerticalGrid`) con cabeceras a ancho completo. Con teclado, lista plana con la sección como prefijo de la línea secundaria y `search_group_*` conservados.
- La consulta y los grupos expandidos siguen en `rememberSaveable`; las etiquetas `search_screen`, `search_field`, `search_group_*`, `search_result_*` se conservan.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Barra y filas visibles | 8/10 | `list_sticky_bar` y `search_field` en test 891×411; rejilla verificada en código. |
| 2 | Teclado | 8/10 | `WindowInsets.ime` alterna chips/rejilla por lista plana. |
| 3 | Medida de lectura | No aplica | Resultados cortos, no lectura larga. |
| 4 | Selección sin apilar y atrás | 9/10 | Sin `two_pane`; `onOpen` sigue abriendo la ficha. |
| 5 | Giro | 8/10 | Consulta y expandidos sobreviven al giro. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Wrappers L0. |
| 7 | Texto no elidido | 8/10 | Fila de 68 dp con dos líneas. |
| 8 | Tests de área | 9/10 | `SearchLandscapeL5Test` 1/1. |
| 9 | Identidades y estados | 8/10 | Theming y `search_empty` heredados. |

## Tests

- `SearchLandscapeL5Test` (nuevo): **1/1 test, 0 fallos** en 2m07s.
- `PlansLandscapeL5Test` (nuevo): **2/2 tests, 0 fallos** en 2m10s.
- `CatechismLandscapeL5Test` (nuevo): **2/2 tests, 0 fallos** en 2m29s.
- `QuestionsLandscapeL5Test` (nuevo): **2/2 tests, 0 fallos** en 2m15s.
- `DevotionsLandscapeL5Test` (nuevo): **2/2 tests, 0 fallos** en 2m13s.
- `OrdersLandscapeL5Test` (nuevo): **2/2 tests, 0 fallos** en 2m10s. Cubre barra fija + hoja de filtros y selección sin apilar a 891×411.
- `SaintsLandscapeL5Test` (nuevo): **2/2 tests, 0 fallos** en 2m17s. Cubre selección en dos paneles sin apilar y el flujo vertical de hoja de filtros + ficha con «atrás».
- `SaintsScreenTest`: se actualizan los dos escenarios a la nueva hoja de filtros (`list_filter_button` → `saints_month_picker` → `sheet_close`) y se conservan todas las comprobaciones de ficha y crédito. **Queda sin ejecutar** en esta entrega: su `@GraphicsMode(NATIVE)` lo hace muy lento en este PC y se canceló al pausar el encargo.

## Rendimiento de tests en este PC

- Con `--no-daemon`: `SaintsScreenTest` tardó **28m50s** y falló solo por las etiquetas antiguas.
- Sin esa bandera, con daemon y `--max-workers=4`: `SaintsLandscapeL5Test` tardó **2m17s** (≈12× más rápido). El coste dominante era el arranque/daemon, no el render nativo. Para T-052 se seguirá sin `--no-daemon`.

## Cifras que no se cumplen

- Ninguna detectada por ahora; la verificación numérica de §8.3 y §8.4 queda para las capturas con insets.

## Decisiones

- Las siete pantallas se entregan con tests ligeros propios; las suites NATIVE costosas (`SaintsScreenTest`, `OrdersScreenTest`, `Devotion*`, `QuestionDeeperUiTest`, `CatechismInteractionTest`, `ReadingPlansUiTest`, `SearchUiTest`, `TabletLayoutTest`) y las capturas `LandscapeListsScreenshotTest` quedan para la revisión completa con el coordinador.

- Los filtros ya no aparecen dentro de la lista en horizontal: el diseño L5 los mueve a la hoja lateral, pero se conservan sus etiquetas para no romper los tests ni la accesibilidad.
- No se han tocado `ui/design/*` ni otras pantallas.
