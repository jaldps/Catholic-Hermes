# T-053 · Informe de entrega — L6 en horizontal

- **Rama:** `ext/T-053`, desde `CatholicGPT` y con la base 1.9.5 integrada (corrección del force close y 55 coronillas).
- **Método:** el mismo de T-052 — un push por pantalla, tests ligeros propios, sin tocar `ui/design`, `ui/theme` ni `MainApp.kt`.
- **Estado:** pantalla **5 de 6**.





## 5. Recordatorios — `RemindersScreen.kt`

- En `AppWindowClass.LandscapePhone` se usa `AppPageLayout(kind = AppPageKind.Hub)`: los avisos de estado (permisos y fallos de escritura) van en el `supporting` y los recordatorios en rejilla de 2 columnas a 891 y 1 columna a 800.
- La rueda de horas/miutos pasa de 40 a **44 dp** por ítem (`WheelItem`), cumpliendo el mínimo de objetivo táctil.
- En vertical y tableta permanece la `LazyColumn` original. No se ha tocado `NoticeSettings.kt` ni la persistencia.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Panel y rejilla | 9/10 | `RemindersLandscapeL6Test` verifica panel, cabecera y lista. |
| 2 | Teclado | No aplica | Sin campo principal. |
| 3 | Medida de lectura | 8/10 | Wrappers L0. |
| 4 | Selección sin apilar y atrás | 8/10 | Navegación heredada. |
| 5 | Giro | 8/10 | Estado de preferencias intacto. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Rueda a 44 dp. |
| 7 | Texto no elidido | 9/10 | Textos cortos. |
| 8 | Tests de área | 9/10 | `RemindersLandscapeL6Test` 1/1. |
| 9 | Identidades y estados | 8/10 | Theme y estados heredados. |

## 4. Confesión — `ui/confession/ConfessionScreen.kt`

- En `AppWindowClass.LandscapePhone` se usa `AppPageLayout(kind = AppPageKind.Immersive)`: segmentos verticales (`confession_tabs`), examen en el carril A (`confession_examen`) y lista privada en el B (`confession_private_list`), con el aviso de privacidad encabezando el carril B.
- El borrador sigue en el holder existente: la lista y las notas no se escriben en disco y se vacían al salir. No se ha cambiado ese mecanismo.
- En vertical y tableta permanece la `LazyColumn` original.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Panel y carriles | 9/10 | `ConfessionLandscapeL6Test` verifica panel, pestañas, ambos carriles, aviso y notas. |
| 2 | Teclado | 7/10 | `imePadding` heredado; medición de 2 líneas pendiente con arnés de teclado. |
| 3 | Medida de lectura | 8/10 | Wrappers L0. |
| 4 | Selección sin apilar y atrás | 8/10 | Navegación heredada. |
| 5 | Giro | 9/10 | El holder ya conserva el borrador. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Botones y filas de 44 dp. |
| 7 | Texto no elidido | 9/10 | Textos cortos. |
| 8 | Tests de área | 9/10 | `ConfessionLandscapeL6Test` 1/1. |
| 9 | Identidades y estados | 8/10 | Theme y estados heredados. |

## 3. Diario, Tentaciones y Prácticas — `SpiritualLifeScreen.kt`

- En `AppWindowClass.LandscapePhone`, `SpiritualHub` usa `AppPageLayout(kind = AppPageKind.Hub)` con dos carriles: diario (`journal_landscape_list`) y prácticas (`practices_landscape_list`, con acceso a tentaciones).
- El editor de diario y sus hojas laterales siguen siendo los existentes; no se ha tocado Room ni el texto de las entradas.
- En vertical y tableta permanece la `LazyColumn` original.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Panel y carriles | 8/10 | `SpiritualLandscapeL6Test` verifica ambos carriles. |
| 2 | Teclado | 8/10 | Editor heredado con `imePadding`. |
| 3 | Medida de lectura | 8/10 | Wrappers L0. |
| 4 | Selección sin apilar y atrás | 8/10 | Navegación heredada; falta test específico de giro. |
| 5 | Giro | 8/10 | Rama condicionada a la clase de ventana. |
| 6 | Cero `SMALL_TARGET` | 9/10 | `AppListRow` y wrappers. |
| 7 | Texto no elidido | 9/10 | Textos cortos. |
| 8 | Tests de área | 9/10 | `SpiritualLandscapeL6Test` 1/1. |
| 9 | Identidades y estados | 8/10 | Theme y estados heredados. |

## 2. Hábitos — `HabitsAndVirtuesScreen.kt`

- En `AppWindowClass.LandscapePhone`, `HabitsRoot` usa `AppPageLayout(kind = AppPageKind.Today)`: cabezal con el arte y el anillo del día, carril A con el calendario de fidelidad (`habit_month_day_*`) y carril B con los hábitos y sus casillas (`habits_checklist_card`).
- En vertical y tableta permanece la `LazyColumn` original.
- `TodayRing` y `FidelityCalendar` se reutilizan; no se ha tocado persistencia ni texto devocional.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Panel y carriles | 9/10 | `HabitsLandscapeL6Test` verifica panel, anillo, checklist y días de fidelidad. |
| 2 | Teclado | No aplica | Sin campo de texto en la vista principal. |
| 3 | Medida de lectura | 8/10 | Wrappers L0. |
| 4 | Selección sin apilar y atrás | 8/10 | Sin cambios de navegación. |
| 5 | Giro | 8/10 | Rama condicionada a la clase de ventana. |
| 6 | Cero `SMALL_TARGET` | 9/10 | `RuleRow` y wrappers existentes. |
| 7 | Texto no elidido | 9/10 | Textos cortos. |
| 8 | Tests de área | 9/10 | `HabitsLandscapeL6Test` 1/1. |
| 9 | Identidades y estados | 8/10 | Theme y estados heredados. |

## 1. Mi camino — `MyPathScreen.kt`

- En `AppWindowClass.LandscapePhone`, `MyPathHub` usa `AppPageLayout(kind = AppPageKind.Today)` con `LocalHeaderPoster provides true`; la cabecera conserva `my_path_title`.
- El `supporting` del panel muestra dos carriles: A «Hoy en mi camino» (`my_path_today`) y B «Secciones» (`my_path_sections`) con la entrada a Confesión (`my_path_confession_entry`).
- En vertical y tableta sigue la `LazyColumn` original; el cambio está limitado a `appIsLandscapePhone()`.
- No se ha tocado contenido devocional ni datos.

### Autoevaluación (rúbrica §11, 9 criterios)

| # | Criterio | Nota | Evidencia |
|---|---|---|---|
| 1 | Panel y carriles visibles | 9/10 | `MyPathLandscapeL6Test` verifica `app_page_panel`, ambos carriles y la entrada de Confesión. |
| 2 | Teclado | No aplica | Sin campo de texto en esta pantalla. |
| 3 | Medida de lectura | 8/10 | Wrappers L0 existentes. |
| 4 | Selección sin apilar y atrás | 8/10 | Sin cambios en la navegación; giro pendiente de test. |
| 5 | Giro | 8/10 | Rama condicionada a la clase de ventana. |
| 6 | Cero `SMALL_TARGET` | 9/10 | Solo `AppListRow` y wrappers. |
| 7 | Texto no elidido | 9/10 | Textos cortos de carril. |
| 8 | Tests de área | 9/10 | `MyPathLandscapeL6Test` 1/1. |
| 9 | Identidades y estados | 8/10 | Theme y estados heredados. |

## Tests

- `RemindersLandscapeL6Test`: **1/1 test, 0 fallos** en 2m09s.
- `ConfessionLandscapeL6Test`: **1/1 test, 0 fallos** en 2m05s.
- `SpiritualLandscapeL6Test`: **1/1 test, 0 fallos** en 2m15s.
- `HabitsLandscapeL6Test`: **1/1 test, 0 fallos** en 2m09s.
- `MyPathLandscapeL6Test`: **1/1 test, 0 fallos** en 2m03s.

## Pendiente

- Pantalla 6: Hábitos, Diario/Tentaciones/Prácticas, Confesión, Recordatorios y Explorar.
- Suites pausadas de T-052: `SearchUiTest` (1 fallo), `TabletLayoutTest` y `NarrowLargeTextAuditTest` (colgados / hallazgo APPLE ES).
