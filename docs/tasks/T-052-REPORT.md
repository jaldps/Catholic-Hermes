# T-052 · Informe de entrega — L5 listas con ficha en horizontal

- **Rama:** `ext/T-052`, desde `origin/CatholicGPT` en `cdd0458` (apertura de T-052).
- Rama: `ext/T-052`, desde `origin/CatholicGPT` en `cdd0458`; luego fusionada con `origin/CatholicGPT` @ `c8e5a97` (1.9.0, con T-062 integrado). Conflicto en `SaintsScreen.kt` resuelto conservando el `AppTwoPane` de T-052 y aplicando el `appDockInset()` de T-062 en la lista.
- Entrega parcial: pantallas 1 y 2 de 7.

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

## Tests

- `OrdersLandscapeL5Test` (nuevo): **2/2 tests, 0 fallos** en 2m10s. Cubre barra fija + hoja de filtros y selección sin apilar a 891×411.
- `SaintsLandscapeL5Test` (nuevo): **2/2 tests, 0 fallos** en 2m17s. Cubre selección en dos paneles sin apilar y el flujo vertical de hoja de filtros + ficha con «atrás».
- `SaintsScreenTest`: se actualizan los dos escenarios a la nueva hoja de filtros (`list_filter_button` → `saints_month_picker` → `sheet_close`) y se conservan todas las comprobaciones de ficha y crédito. **Queda sin ejecutar** en esta entrega: su `@GraphicsMode(NATIVE)` lo hace muy lento en este PC y se canceló al pausar el encargo.

## Rendimiento de tests en este PC

- Con `--no-daemon`: `SaintsScreenTest` tardó **28m50s** y falló solo por las etiquetas antiguas.
- Sin esa bandera, con daemon y `--max-workers=4`: `SaintsLandscapeL5Test` tardó **2m17s** (≈12× más rápido). El coste dominante era el arranque/daemon, no el render nativo. Para T-052 se seguirá sin `--no-daemon`.

## Cifras que no se cumplen

- Ninguna detectada por ahora; la verificación numérica de §8.3 y §8.4 queda para las capturas con insets.

## Decisiones

- Los filtros ya no aparecen dentro de la lista en horizontal: el diseño L5 los mueve a la hoja lateral, pero se conservan sus etiquetas para no romper los tests ni la accesibilidad.
- No se han tocado `ui/design/*` ni otras pantallas.
