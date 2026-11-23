# T-062 · Pasada de interfaz con las skills de diseño (vertical)

- **Rama:** `ext/T-062`, desde `origin/CatholicGPT` actualizado.
- **Chuleta:** `docs/tasks/T-062-data/skills-notes.md` (5 reglas × 8 skills, traducidas a Compose).
- **Método:** una pantalla por commit y push; capturas antes/después al 60 % en `docs/tasks/T-062-shots/<n>-<pantalla>/`, mínimo Lumen claro y Sanctuary oscuro. Solo se cambia lo que se justifica con una regla de las skills y se ve en la captura.
- **Lista de 18 puntos:** la del encargo `docs/tasks/T-062.md`.

## Pantallas

| # | Pantalla | Estado |
|---|---|---|
| 1 | Santos (lista y ficha) | En curso |
| 2 | Órdenes y oficios | Pendiente |
| 3 | Devociones | Pendiente |
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

## Para la capa compartida

_Pendiente._
