# T-055 · Informe de entrega — estados vacíos y de error que faltaban (vertical)

- **Rama:** `ext/T-055` en el fork `joselanzd-hash`, creada desde `CatholicGPT @ a5ac494` (posterior al mínimo `2a00060` del encargo).
- **Idioma:** español.
- **Ámbito:** solo vertical; no se ha tocado nada de horizontal, `ui/design/*`, `MainApp.kt` ni `FullAppScreenshotTest.kt`.

## Cambios por estado

1. **`content_load_notice`** (`ui/components/ContentLoadNotice.kt`): último parámetro `modifier`, aplicado a la `Column` junto con `.testTag("content_load_notice")`. Las 6 llamadas existentes siguen usándose sin cambios.
2. **`daily_missal_error`** (`HomeScreen.kt`, solo tarjeta de liturgia): cuando falla Vatican News (sin red y sin caché del día) y el leccionario incluido no aporta evangelio ni primera lectura, la tarjeta conserva su cabecera y muestra `ContentLoadNotice(failed = true, retry = …)` con la etiqueta `daily_missal_error`. «Reintentar» incrementa un contador `rememberSaveable` que forma parte de la `key(...)` de la carga, así que vuelve a llamar al lector.
3. **`novena_filter_empty`** (`PrayersAndNovenasScreen.kt`, solo lista de novenas): se reutilizó el estado vacío que ya existía, con los textos del encargo y `AppEmptyState(... testTag("novena_filter_empty"))`. Debajo, `AppSecondaryCapsule` «Quitar filtros» (tag `novena_clear_filters`, conservado) restablece búsqueda, intención, tipo y origen. Los filtros siguen visibles encima.
4. **`list_empty`**:
   - Santos (`SaintsScreen.kt`): estado propio conservado dentro de un contenedor con `list_empty`; «Quitar filtros» limpia búsqueda, mes y «solo con ficha».
   - Devociones (`DevotionsExplorerScreen.kt`): mismo patrón; «Quitar filtros» limpia búsqueda y región.
   - Órdenes (`DoctrineOrders.kt`): **sin cambios**. Solo tiene el segmentado Órdenes/Oficios, que no es un filtro de búsqueda y no puede dejar la lista vacía por entrada del usuario. El encargo exime este caso y pide anotarlo.
5. **`reminders_empty`** (`RemindersScreen.kt`): `AppEmptyState` al principio de la lista cuando no hay ningún recordatorio activo, con la etiqueta `reminders_empty`, y `AppSecondaryCapsule` «Activar el Ángelus» que llama a `onToggle(...)` del Ángelus del mediodía — el mismo método del interruptor, con su flujo de permiso de notificaciones. La lista de recordatorios sigue debajo.
6. **Noticias**: nada que hacer. `news_failed` ya cubre el caso; no se creó `news_offline`.

## Tests

Java 21, Gradle 9.3.1, SDK 36.1. Corridas una clase por proceso, según el procedimiento del repo.

| Clase | Resultado |
|---|---|
| `EmptyStatesTest` (nueva, 5 casos) | **5/5 OK** (`BUILD SUCCESSFUL in 2m 31s`) |
| `SaintsScreenTest` | OK |
| `HomeTextTest` | OK |
| `NovenaOriginBadgeUiTest` | OK |
| `HomePrayerPrioritiesTest` | **4/8** — 4 `AppNotIdleException` en `HomePrayerPrioritiesTest.kt:127` |
| `:app:assembleDebug` | **BUILD SUCCESSFUL** |

Los 4 fallos de `HomePrayerPrioritiesTest` son los mismos casos y la misma excepción **en el commit base `2a00060`** (`8 tests completed, 4 failed`, `BUILD FAILED in 41m 46s`), por lo que son de entorno y no una regresión.

## Capturas

`app/src/test/java/com/example/screenshots/EmptyStatesScreenshotTest.kt`, 6 métodos × 4 identidades = **24 PNG al 60 %** en `docs/tasks/T-055-shots/`: Lumen claro, Sanctuary oscuro, Cristal claro y Tradicional oscuro, a 411 dp; más `novena_filter_empty` a 320 dp con letra al 130 %.

- Nombres: `<identidad>_<claro|oscuro>_<estado>.png` y `..._320_130_novena_filter_empty.png`.
- Lámina: `docs/tasks/T-055-shots/_contact.png`.
- **Revisadas.** Los cinco estados se ven centrados y legibles; los botones aparecen completos en Lumen, Cristal y Tradicional.

## Decisiones y observaciones que no están literalmente en el encargo

1. **Sobrecarga de `ContentLoadNotice`**: `BibleScreen.kt:843` pasaba el `retry` como lambda final; añadir `modifier` como último parámetro rompía esa llamada (`Modifier` esperado). Para no tocar `BibleScreen.kt` (fuera del ámbito) se añadió en el mismo archivo una sobrecarga de 3 parámetros que delega con `Modifier`. Las 6 llamadas siguen iguales.
2. **Ángelus del mediodía** (`angelus_midday`): hay tres recordatorios del Ángelus (mañana, mediodía y tarde). Se eligió el del mediodía por ser el que viene activo por defecto en `ReminderStore.DEFAULT_ON`.
3. **`novena_clear_filters`**: el botón ya existía con esa etiqueta y varios tests la referenciaban; se conservó el tag aunque el texto pasó de «Ver todas» a «Quitar filtros».
4. ** Sanctuary oscuro a 411 dp**: el botón «Quitar filtros» de `novena_filter_empty` está en el árbol y `assertIsDisplayed()` pasa, pero en la captura queda cubierto por el dock inferior (media 863 dp de 891 dp, frente a 807 dp en las otras tres identidades). Se añadió un `Spacer` de 64 dp debajo del botón para que en el uso real no quede bajo la barra; la captura sigue sin mostrarlo en esa identidad. Se reporta para que el coordinador evalúe el espaciado de `AppSecondaryCapsule` en Sanctuary, que no está en los archivos permitidos de este encargo.
5. **T-052**: al terminar se consultó `origin/CatholicGPT:docs/tasks/README.md` y sigue marcada **EN COLA (tras T-047; en paralelo con Codex)**, no ABIERTA. No se ha empezado.


## Petición menor de la revisión (capturas Lumen/Cristal claros)

- `EmptyStatesScreenshotTest.capture()`: `reduceMotion = true` ya estaba en los dos puntos donde se aplica `SanctaFidesTheme`; se añade `compose.mainClock.advanceTimeBy(3_000L)` entre dos `waitForIdle()` antes de dibujar, para que cualquier animación de entrada esté terminada al capturar.
- Repetida la clase completa: **24/24 tests, 0 fallos** (`BUILD SUCCESSFUL in 6m 36s`, Java 21).
- Regeneradas las 24 capturas y la lámina `_contact.png`. Cambiaron tres PNG (los de filtro de novenas en tres identidades) y la lámina; el resto quedó byte-idéntico.
- Revisadas visualmente `lumen_light_novena_filter_empty.png` y `glass_light_daily_missal_error.png`: textos y títulos con buen contraste y legibles. No se ha detectado el defecto de legibilidad que se veía en la lámina anterior.
