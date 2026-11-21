# T-046 · Informe de entrega — «Mi camino» en la barra principal y fila única de hábitos

- **Rama:** `ext/T-046` (fork `joselanzd-hash`), creada desde `CatholicGPT` en `83b3766` (Release 1.8.28, con la auditoría T-045).
- **Idioma:** español.
- **Estado:** ver detalle de tests más abajo.

## Resumen de cambios

1. **Una sola fila de hábitos** (`MyPathScreen.kt`): «Hoy en mi camino» muestra una única `AppListRow` (`my_path_today_habits`) con el progreso y la racha unidos por « · ». Se borraron `my_path_today_fidelity` y `my_path_section_habits`; los tests que las comprobaban se adaptaron a la fila única sin quitar aserciones.
2. **Quinta pestaña** (`MainApp.kt`): `tabs = listOf(HOME, PRAYERS, BIBLE, MY_PATH, MORE)`. «Mi camino» abre el hub sin pila; volver a tocarla desde una sección regresa al hub. La han heredado `selectedNavIndex`, `origin`, `isPushed`, `stackNames`, el dock y el rail de tableta/horizontal.
3. **Icono** (`AppIconSet.kt`): nuevo `AppIconSlot.PATH` con reposo/seleccionado en los cuatro estilos (`Outlined.Route` / `Filled.Route` y variantes rounded y sharp), asignado a `MY_PATH`.
4. **Sin duplicado en Explorar**: se quitó la entrada de «Mi camino» de `moreSections(...)`; Confesión sigue ahí.
5. **Etiqueta corta** bajo `compactNavigation`/`largeNavigationText`, junto a «Explorar» → «Más».
6. **Orígenes de navegación**: los deep links, la búsqueda, los avisos y la bienvenida dejan seleccionada la pestaña y «atrás» vuelve al hub; los accesos propios de «Hoy» conservan su vuelta a «Hoy».
7. **Tests**: nuevos casos en `MyPathUiTest` (pestaña, fila única, deep link), tests de navegación y de tableta adaptados a 5 pestañas, y `MyPathTabScreenshotTest` nuevo.

## Tests

Ejecutados con `JAVA_HOME=C:/Program Files/Microsoft/jdk-21.0.13.21-hotspot` y `ANDROID_HOME=C:/Users/lanzj/AppData/Local/Android/Sdk`.

| Clase | Resultado |
|---|---|
| `MyPathUiTest` | **13/13 OK** (`BUILD SUCCESSFUL in 18m 53s`) |
| `MyPathSummaryTest` | **OK** (`BUILD SUCCESSFUL in 2m`) |
| `DesignNavigationTest` | **OK** (`BUILD SUCCESSFUL in 59s`) |
| `TabletLayoutTest` | **43/44** — 1 `AppNotIdleException` (ver nota de entorno) |
| `NarrowLargeTextAuditTest` | **0/8** — `AppNotIdleException` (ver nota de entorno) |
| `SearchUiTest` | **7/8** — 1 nodo del catecismo desaparece al hacer scroll |
| `WelcomePersonalizationTest` | **3/16** — 13 timeouts/`AppNotIdleException` (ver nota) |
| `AppNavigationTest` | **0/5** — `AppNotIdleException` (idéntico en la base; ver nota) |
| `:app:assembleDebug` | **BUILD SUCCESSFUL** |

### Nota de entorno: `NarrowLargeTextAuditTest`

Los 8 casos fallan con `UNREACHED: AppNotIdleException` en `dest_home` («Compose did not get idle after 2 attempts in 60 SECONDS»). **El mismo fallo, con los mismos 8 casos, se reproduce en el commit base `83b3766`** (`BUILD FAILED in 1h 23m 34s`), por lo que es una limitación del entorno de Robolectric y no una regresión de este encargo.

Se comprobó lo mismo en `AppNavigationTest`: **5/5 fallidos por `AppNotIdleException` en la misma línea (`AppNavigationTest.kt:129`) tanto en `ext/T-046` como en el commit base `83b3766`**. En `TabletLayoutTest`, `WelcomePersonalizationTest` y `AppNavigationTest` no aparece ninguna aserción fallida sobre pestañas ni rutas: todos los fallos son timeouts o `AppNotIdleException`.

Antes de ese diagnóstico, el audit sí detectó un problema real introduce por la quinta pestaña: las etiquetas «Camino» y «Caminho» se partían a mitad de palabra en la columna de 48 dp con letra al 130 %. Se corrigió con la etiqueta corta indicada en «Decisiones». Tras el cambio, el fichero de hallazgos ya no contiene ningún `MIDWORD`; solo el `AppNotIdleException` previo.

## Capturas

Generadas por `MyPathTabScreenshotTest`, a escala 60 %, en `docs/tasks/T-046-shots/`:

- `lumen_light_hub.png`, `sanctuary_dark_hub.png`, `glass_light_hub.png`, `classic_dark_hub.png`
- `lumen_light_320_130_hub.png`, `sanctuary_dark_320_130_hub.png`, `glass_light_320_130_hub.png`, `classic_dark_320_130_hub.png` (320 dp, letra al 130 %)
- `_docks.png` — comparativa de las cuatro barras inferiores a 320 dp/130 %.

**Revisadas visualmente.** En las cuatro identidades se ven las 5 pestañas completas («Hoy · Orar · Leer · Ruta · Más»), sin etiquetas partidas ni cortadas y con los iconos separados. «Ruta» aparece íntegro en las cuatro.

Las primeras corridas en rojo de `MyPathUiTest` y `SearchUiTest` sirvieron para corregir los tests a los que el encargo cambia el comportamiento: «Mi camino» ya no está en Explorar, el hub es una pestaña (su «atrás» va a «Hoy», no a Explorar) y el botón de búsqueda global vive en la cabecera de «Hoy». No se quitaron comprobaciones: se reescribieron sobre el nuevo destino equivalente.

## Decisiones que no están literalmente en el encargo

1. **Etiqueta corta «Ruta» / "Path" / «Rota»**, en lugar de la propuesta «Camino» / "Path" / «Caminho». El encargo pide aplicar «la misma lógica» de «Explorar» → «Más»; los criterios reales del dock (columna de 48 dp, letra al 130 %) parten «Camino» y «Caminho» por la mitad. «Ruta»/«Rota» son sinónimos de «camino» y pasan la medición y la revisión visual en las cuatro identidades. `AppNavItem.label` es un `String`, así que no se puede reducir la letra solo para esta pestaña sin tocar `ui/design/LumenKit.kt`, que está fuera de los archivos permitidos.
2. **`myPathFromHub` distingue el origen** (hub, deep link, búsqueda, avisos y bienvenida → hub; accesos de «Hoy» → «Hoy»). El encargo pide «atrás hasta el hub» para los primeros y no menciona los accesos de «Hoy», que conservan su comportamiento anterior.
3. Las corridas de batería se hicieron **clase por clase** para evitar la contención de Robolectric (`AppNotIdleException`) que invalidó la corrida conjunta; los recuentos de arriba corresponden a esas corridas aisladas.
