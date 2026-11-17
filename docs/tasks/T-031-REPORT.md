# T-031 · «Mi camino» — informe

Estado: implementado y validado en local (sin commit ni push, según instrucciones). Rama de trabajo: `CatholicGPT` (HEAD 1999724 + cambios sin confirmar).

## Qué se hizo

- **Destino nuevo** `AppDestination.MY_PATH` («Mi camino» / "My path" / "Meu caminho", tag `nav_my_path`). En Explorar, dentro del grupo «Vida interior», sustituye a las dos filas de Hábitos y Virtudes y de Vida espiritual. Confesión conserva su fila propia en Explorar.
- **Inicio de «Mi camino»** (`MyPathHub`, `ui/screens/MyPathScreen.kt`): cabezal único (`AppHeroHeader`, arte existente `heroes/lumen/habits.jpg`), «Hoy en mi camino» y «Secciones».
  - **Hoy en mi camino**, cada fila abre su sitio:
    - Hábitos de hoy: «N hechos · M pendientes» (abre Hábitos).
    - Fidelidad: racha de días seguidos, sin lenguaje de culpa; si no hay racha, «Empieza hoy, a tu ritmo.» (abre Hábitos, donde está el calendario).
    - Plan de lectura en curso, una fila por plan empezado y no terminado: «Día X de N · P %» (abre el plan, vía `plansInitialId`).
    - Novena en curso, hasta dos, la más reciente primero: «Novena · día X de N» (abre la novena en Orar).
    - Si no hay plan ni novena en curso, esas filas no aparecen.
  - **Secciones**: Hábitos · Diario · Tentaciones y victorias · Prácticas, más el acceso a Confesión (`my_path_confession_entry`).
- **Las secciones reutilizan las pantallas de siempre**, sin duplicar lógica:
  - Hábitos y Tentaciones y victorias → `HabitsAndVirtuesScreen` (la segunda abre su página de tentaciones con `initialOpenTemptations`).
  - Diario y Prácticas → `SpiritualLifeScreen` con un parámetro nuevo `focus` (`ALL` por defecto, el comportamiento de siempre; `JOURNAL`; `PRACTICES`).
    - Diario: cabezal «Diario», botón de Confesión (`spiritual_confession_entry`) y diario.
    - Prácticas: cabezal «Prácticas» y las tres prácticas como lista (Vía Crucis, Preguntas de fe, Canto gregoriano). Como lista y no como la rejilla de 3 columnas, para que los nombres no se partan a 320 dp / 130 %.
- **Datos:** sin cambios de esquema Room ni de formato guardado. Todo lo del resumen se lee de lo que ya se guarda (completados de hábitos, `planProgress`, días de novena); abrir una fila no escribe nada. La copia de seguridad no cambia.
- Textos en ES/EN/PT. Solo componentes de `ui/design` (`AppHeroHeader`, `AppSectionHeader`, `AppListGroup`, `AppListRow`, `AppBackHandler`).

## Cómo enrutan los destinos antiguos

Los destinos `HABITS_VIRTUES` y `SPIRITUAL_LIFE` siguen existiendo como nombres (ids guardados intactos), pero ya no son una pantalla: `AppDestination.myPathSectionFor` los traduce.

| Entrada | Llega a |
|---|---|
| `HABITS_VIRTUES` (deep link, búsqueda, bienvenida, avisos, Inicio «Hábitos») | Mi camino → sección Hábitos (`tab_habits_rule`) |
| `HABITS_VIRTUES` con tentaciones (atajo de Inicio «Tentaciones») | Mi camino → Tentaciones y victorias (`tab_sin_avoider`) |
| `SPIRITUAL_LIFE` (Inicio «Vida interior», búsqueda, etc.) | Mi camino → Diario (`spiritual_confession_entry`) |
| Atajo de Inicio «Trivia» (antes `SPIRITUAL_LIFE` pestaña 1) | Mi camino → Prácticas, directo al quiz (`trivia_progress`) |
| `MY_PATH` (Explorar, búsqueda «Mi camino») | Inicio de Mi camino |

- `go()` canonicaliza: abrir un destino antiguo por nombre abre `MY_PATH` con su sección.
- También se canonicaliza el `initialDestination` y cualquier nombre guardado.
- **Atrás.** Si se llega por enlace, atrás vuelve a quien llamó (Inicio, la búsqueda, Explorar), como antes.
  - Si se baja desde el inicio de Mi camino, atrás vuelve al inicio, y de ahí a Explorar.
  - Desde el inicio, la sección de Tentaciones vuelve directa al inicio (parámetro nuevo `onExitTemptations`, solo en ese caso).
- La búsqueda global conserva las filas «Hábitos y Virtudes» y «Vida espiritual» y añade «Mi camino».
- `profiles.json` y `notices.json` no se tocan: sus nombres de destino siguen siendo válidos.

## Archivos

Nuevos:

- `app/src/main/java/com/example/ui/screens/MyPathScreen.kt`: inicio, `MyPathSection`, `MyPathSwitcher`.
- `app/src/main/java/com/example/ui/screens/MyPathSummary.kt`: reglas puras del resumen (racha, hábitos de hoy, planes y novenas en curso).
- `app/src/test/java/com/example/MyPathUiTest.kt`
- `app/src/test/java/com/example/MyPathSummaryTest.kt`
- `app/src/test/java/com/example/screenshots/MyPathScreenshotTest.kt`
- `docs/tasks/T-031-shots/*.png`

Modificados:

- `app/src/main/java/com/example/ui/MainApp.kt`: destino, enrutado, estado, filas de Explorar.
- `app/src/main/java/com/example/ui/screens/SpiritualLifeScreen.kt`: `SpiritualFocus` y lista de prácticas.
- `app/src/main/java/com/example/ui/screens/HabitsAndVirtuesScreen.kt`: `onExitTemptations`; `rememberToday` pasa a `internal`.
- `app/src/main/java/com/example/ui/screens/PrayersAndNovenasScreen.kt`: la lógica de «días rezados» se extrae a `novenaDaysPrayed`, que usan Orar y el resumen. Comportamiento idéntico.
- `app/src/main/java/com/example/ui/screens/MoreScreen.kt`: fila `nav_my_path`.
- `app/src/test/java/com/example/AppNavigationTest.kt`: solo la entrada de navegación (`nav_my_path` → sección Hábitos → atrás al inicio → atrás a Explorar).

No se tocó `assets/`, versiones, firma, `tools/hermes-preview.gradle`, `dist/` ni Room.

## Tests

Todos los tests existentes se mantienen sin relajar. Se conservan todos los test tags de ambas pantallas.

- Nuevos (`MyPathUiTest`, 10 tests):
  - Explorar tiene una sola entrada y ya no lista `nav_habits` / `nav_spiritual`.
  - Cada sección abre la pantalla de siempre y atrás vuelve al inicio.
  - Diario no muestra las prácticas, y Prácticas no muestra el diario.
  - Tentaciones vuelve directo al inicio.
  - Confesión a un toque desde el inicio.
  - Una práctica abierta desde la lista vuelve a la lista y luego al inicio.
  - Atajos de Inicio (Vida interior, Trivia).
  - Destinos antiguos como enlaces, con atrás al llamador.
  - Búsqueda: «Vida espiritual» y «Hábitos» aterrizan en su sección.
  - Resumen con datos de ejemplo (un hábito hecho en la fixture, racha de 2 días, plan empezado, novena empezada): se muestra y cada fila navega.
  - Resumen vacío.
  - ES/EN/PT.
- Nuevos (`MyPathSummaryTest`): racha (no se rompe con el día aún abierto), hábitos de hoy, planes en curso.
- Ejecutados y en verde: `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `SearchUiTest`, `ConfessionInteractionTest`, `JournalTemptationInteractionTest`, `TemptationGuideInteractionTest`, `HabitsLogicTest`, `BackupCoreTest`, `ReadingPlansUiTest`, `FontPreferencesTest`, `NovenaIndexJourneyTest`, `RetainedNavigationTest`, `GlobalSearchIndexTest`, `HomePrayerPrioritiesTest`, `MyPathScreenshotTest`.
- Suite completa y `:app:assembleDebug`: ver el resultado al final de este informe.

## Capturas

`docs/tasks/T-031-shots/`, a 60 % del tamaño real:

- `{lumen_light, sanctuary_dark, glass_light, classic_dark}_{hub, hub_bottom, practices, diary}.png`: ES, 411 dp.
- `*_320_130_{hub, hub_sections, hub_bottom, practices, diary}.png`: PT, 320 dp, 130 %.

Se revisaron las capturas. Se corrigieron dos problemas:

- A 320 dp / 130 % el «2/12» de la fila de hábitos partía «pendientes» a mitad de palabra. Se quitó el texto de la derecha; el recuento ya está en el subtítulo.
- La rejilla de 3 columnas de Prácticas partía «Via-Sacra». Dentro de «Mi camino» ahora es una lista.

## Límites y decisiones

- Las filas de «Hoy en mi camino» muestran hasta dos novenas. Los planes son solo dos, así que se muestran los que estén en curso.
- La **racha** es nueva (la app no tenía el concepto). Cuenta días consecutivos con al menos una práctica marcada. Si hoy aún no hay ninguna, cuenta desde ayer, de modo que el número no cae a cero por la mañana. No hay mensajes de pérdida.
- «Hábitos» y «Fidelidad» abren la misma sección (el calendario está en su pantalla). No se añadió un parámetro para saltar al calendario.
- Cada sección conserva su cabezal (`largeTitle`) bajo el del inicio de Mi camino. Es la reutilización «tal cual» que pedía el encargo; no se creó un segundo sistema de cabezales.
- La pila de «atrás» dentro de Mi camino es de un solo nivel (inicio ↔ sección). Las subpáginas de cada pantalla (resumen, guía de tentación, etc.) conservan su propio atrás.
- En los tests de interfaz hay una carrera de cierre conocida (una consulta Room en vuelo cuando se cierra la base). Una ejecución intermedia de `MyPathUiTest` falló 3 veces por ello y la siguiente pasó entera. Se añadió una pausa corta de 300 ms antes de cerrar la base en `MyPathUiTest`.
- No hay verificación en dispositivo real ni comparación con ningún sitio web; la validación son los tests Robolectric y las capturas.

## Resultado final

- Suite completa `:app:testDebugUnitTest`: 687 tests, 686 en verde. El único fallo es `ExampleRobolectricTest » read string from context`, el conocido por el script de vista previa que renombra `app_name`.
- `:app:assembleDebug`: BUILD SUCCESSFUL.
