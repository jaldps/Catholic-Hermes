# T-040 · Referencias cruzadas de la Biblia en los cursos y en el plan de lectura

- **Rama:** `CatholicGPT` (sin commit ni push, según lo pedido).
- **No se ha tocado:** `assets/`, versiones, firma, `tools/hermes-preview.gradle`, `.github/`, `dist/`, el esquema de Room ni la pestaña «Misa» de `BibleScreen`.

## Qué hace ahora la app

1. **Cursos** (`ScriptureStudyScreen.kt`). En la sección «Leer» de una lección, bajo el botón «Escuchar», cada cita bíblica de la lección tiene una fila discreta: la cita a la izquierda y un chip «Referencias» / "Cross-references" / «Referências» (tag `lesson_xref_<lessonId>_<index>`). El texto del pasaje, la escucha y el resto de la lección no cambian.
   - Un pasaje de **un solo versículo** abre exactamente el `VerseSheet` del lector (texto, copiar/compartir, arcos, lista).
   - Un pasaje de **varios versículos** abre una hoja (`AppSheet`, tag `lesson_xref_sheet`) con el nuevo `PassageCrossReferences`: los pasajes relacionados **versículo a versículo**, los 3 más fuertes de cada uno, con su vista previa de texto; «Ver las N» despliega los de ese versículo, «Más versículos» muestra el resto del pasaje.
   - Tocar un pasaje relacionado cierra la hoja y abre el lector de la Biblia en ese versículo. **Atrás** en el lector devuelve a la misma lección (ver «Limitaciones»).
2. **Plan de lectura** (`ReadingPlansScreen.kt`). Para las lecturas bíblicas del día, cada capítulo tiene la misma fila con su chip (tag `plan_xref_<index>`), situada **debajo de «Marcar como leído»** para no empujar la acción principal. Abre la hoja `plan_xref_sheet` con el capítulo agrupado por versículo, con tope: 3 referencias por versículo y como mucho 40 versículos con referencias (8 al abrir, el resto con «Más versículos»); si el capítulo tiene más, la hoja lo avisa («Se muestran los N primeros versículos…», tag `passage_xref_truncated`). Tocar un pasaje abre el lector con la pila de navegación existente: Atrás vuelve al día del plan. Los días del plan del Catecismo no tienen estas filas.
3. **Misa:** sin cambios.

Todo funciona sin conexión (tablas `xref` ya incluidas), en ES/EN/PT y con las 4 identidades (el resto de la hoja reutiliza `AppListGroup`/`AppListRow`/`AppSecondaryCapsule`/`AppChip`/`AppSheet`; no hay componentes paralelos).

## Cómo se asignan las referencias a la edición

Las citas de cursos y planes están en numeración **hebrea** de los salmos; las tablas de referencias y los lectores usan la numeración de la edición activa.

- Edición: `repository.resolvedEditionId(activeBibleEditionId, language)` (p. ej. `bundled-es`).
- `CatholicDataRepository.citedVerses(raw, editionId, language)`: parsea la cita con `ScriptureReference.parse`, la convierte con `ScriptureResolver.convert` + `PsalmNumberingMap.numberingOf(edition)` (en las ediciones de numeración Vulgata, `psa 23` pasa al capítulo **22**; `psa 116` a 114–115; `psa 119:1-88` queda en 118) y enumera los versículos que **realmente existen** en esa edición (tolera huecos de ediciones descargadas; un capítulo entero da todos sus versículos; tope de seguridad de 400).
- `CatholicDataRepository.passageCrossReferences(raw, language, editionId, perVerse = 3, maxVerses = 40)`: para cada versículo con referencias (`crossReferenceCounts`) trae las `perVerse` más fuertes con `crossReferences(...)` (etiqueta y vista previa en la edición) y devuelve el total real para «Ver las N». Nuevos tipos de datos: `PassageCrossReferences`, `PassageVerseReferences`.
- La etiqueta de la hoja conserva el número de la cita y añade el de la edición: «Salmos 23 (22)» (`studyScriptureLabel`).
- Seguir un pasaje relacionado llama a `onOpenBible(bookId, chapter, verse)` / al lector de `BibleScreen` con el `VerseRef` ya en la numeración de la edición, igual que el resto de referencias del lector.

## Archivos

Modificados:
- `app/src/main/java/com/example/data/repository/CatholicDataRepository.kt` (`citedVerses`, `passageCrossReferences` y sus tipos).
- `app/src/main/java/com/example/ui/screens/CrossReferenceViews.kt`: `CrossReferenceList` (lista con «Ver las N» extraída de `VerseSheet`, que sigue igual), `PassageCrossReferences` y `CrossReferenceAction`.
- `app/src/main/java/com/example/ui/screens/ScriptureStudyScreen.kt` (acciones, hoja y parámetro `onOpenBible`).
- `app/src/main/java/com/example/ui/screens/ReadingPlansScreen.kt` (acciones, hoja, parámetros opcionales `repository` y `onOpenCrossReference`; la ruta los rellena).
- `app/src/main/java/com/example/ui/screens/BibleScreen.kt`: solo la rama de Estudio y el estado `studyResumeLesson` (volver a la lección desde el lector); la pestaña Misa queda intacta.

Nuevos:
- `app/src/test/java/com/example/PassageCrossReferencesTest.kt` (7 pruebas).
- `app/src/test/java/com/example/CrossReferencesUiTest.kt` (5 pruebas).
- `app/src/test/java/com/example/screenshots/CrossRefsScreenshotTest.kt` (4 pruebas × 4 identidades: lección y plan, estándar y 320 dp / 130 %).
- `docs/tasks/T-040-shots/*.png` (32 capturas) y este informe.

## Pruebas

- **Mapeo (unitaria, datos incluidos):** `joh 3:16` incluye `rom 5:8` en ES, EN y PT; `psa 23` en `bundled-es` (y en/pt) se enumera en el capítulo 22 y sus referencias incluyen `joh 10:*` (el Buen Pastor); rangos y cruces de capítulo exactos (`luk 1:26-38`, `gen 1:1-2:3`, `psa 116`, `psa 119:1-88`); tope por versículo y aviso de truncado (`gen 1`); libros deuterocanónicos y citas ilegibles dan vacío sin fallar.
- **UI (Robolectric):** lección `psalms_1_israel_jesus` -> «Referencias» -> hoja con pasajes relacionados -> tocar uno -> lector de la Biblia -> Atrás -> la lección; cada cita de una lección tiene su acción (`jesus_3_supper_service`: 2); texto en inglés ("Cross-references"); día del plan -> «Referencias» -> hoja -> lector -> Atrás -> el día del plan; los días del plan del Catecismo no tienen acciones.
- **Regresión dirigida (110 pruebas, 0 fallos):** `ReadingPlans*`, `CrossReference*`, `PassageCrossReferencesTest`, `CrossReferencesUiTest`, `Study*`, `SearchUiTest`, `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `Bible*`, `RetainedNavigationTest`, `PsalmNumberingTest`, `ScriptureRangeTest`.
- **Suite unitaria completa** (156 clases, 830 pruebas): pasó salvo `ExampleRobolectricTest` «read string from context» (conocido, por el script de init de la vista previa) y un fallo de `ReadingPlansUiTest` que mi primera disposición provocaba (la fila de referencias empujaba «Marcar como leído» fuera de pantalla). Se corrigió moviendo las filas debajo de ese botón y `ReadingPlansUiTest` pasa **sin modificar**. Tras ese cambio no repetí la suite entera (35 min), solo el conjunto dirigido de arriba.
- `:app:assembleDebug`: correcto.

## Capturas (`docs/tasks/T-040-shots/`)

`{lumen_light, sanctuary_dark, glass_light, classic_dark}_` + `lesson` (lección con las acciones), `lesson_sheet` (hoja abierta), `plan`, `plan_sheet`; y las mismas con prefijo `320_130_` (320 dp, texto al 130 %). Revisadas a ojo: sin recortes ni solapes; en 320 dp / 130 % el chip pasa a la línea de debajo de la cita en lugar de comprimirla.

## Limitaciones

- **Versículos de los salmos:** la conversión solo cambia el capítulo (limitación conocida de `PsalmNumberingMap`). Una cita con versículos de un salmo cuya numeración de versículos difiere en la edición (p. ej. los salmos con título que cuenta como versículo en ES/PT) puede quedar desplazada; las citas de capítulo entero son exactas.
- **Libros deuterocanónicos:** la fuente (OpenBible.info) no los cubre; la hoja lo dice en lugar de quedar vacía.
- **Volver a la lección:** al volver del lector, la lección se reabre desde el principio (la pestaña Estudio se recompone, no conserva el scroll ni las notas sin guardar; las notas ya se guardan solas).
- **Horizontal:** la hoja es la misma en vertical y horizontal; el estudio de arcos (`CrossReferenceStudy`) sigue siendo solo del lector.
- El `VerseSheet` de un pasaje de un versículo no ofrece «Escuchar» (solo copiar y compartir).
- Las capturas son de Robolectric, no de dispositivo real. Efecto colateral al ejecutar la suite completa: otras pruebas de capturas reescriben `docs/tasks/{PLANS,T-031,T-037,T-038}-shots`; esos cambios se revirtieron con `git checkout` y no forman parte de esta tarea (por tanto `PLANS-shots` no muestra las filas nuevas del plan; las de T-040 sí).
