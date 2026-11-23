# T-047 · Revisión intermedia del coordinador (pushes A y B)

- **Rama:** `ext/T-047` @ `d28b7ea`.
- **Estado:** A y B se aceptan como avance. Antes de cerrar con el push C hay que aplicar **un cambio de diseño nuevo, decidido por el dueño** (§2, P9 de `docs/design/LANDSCAPE.md`).

## Lo que está bien

- Alcance correcto: `ui/design`, tests y capturas. El cambio en `PrayersAndNovenasScreen` (`AppGuidedPrayerCard`) lo pidió el dueño; se acepta.
- Regresión de vertical y tableta: 20/20 capturas idénticas píxel a píxel. Tests: A 116/116 y B 137/137.
- El informe es honesto con las cifras que no cuadran:
  - banda de 33,75 % a 320 dp de alto;
  - hoja menor de 376;
  - cabezal de Sanctuary con 3 segmentos y búsqueda.

  Las tres se aceptan como medidas. El conflicto de Sanctuary en Orar se decidirá en L4.

## Cambio pedido · P9: el espacio vertical vacío es el enemigo

El dueño vio `_B_fixtures_891x411.png`. En el lector de prueba (Padre Nuestro), el arte del panel deja el título a media altura (y ≈ 345), y entre el título y el texto queda un hueco vertical grande. Dice que la foto no hace falta y que algunos botones son demasiado altos para horizontal.

El documento ya está actualizado (§2 P9, §5.1, §11 criterio 1). En L0 eso supone:

1. **`AppPageLayout(kind = Reader)` deja de dibujar el arte en `LandscapePhone`.**
   - El panel pasa a ser un panel de título.
   - El título (`title1`) va arriba, en `S + 52`, bajo la fila de atrás, alineado con la primera línea del texto (±4).
   - Debajo van, sin huecos de más de 24 dp: la meta, el índice y, fijo en el pie, Escuchar · Aa.
   - El hueco libre queda abajo, junto al pie.
   - Es una excepción declarada a la línea única del título (§3.2), solo para `kind = Reader`.
2. **Alturas de botón en `LandscapePhone`.**
   - Las píldoras («Volver a Orar», «Anterior», «Rezar», la acción del pie de las hojas) miden 44 dp de alto.
   - La acción que avanza («Siguiente›») mide 48.
   - El objetivo táctil mínimo sigue en 44.
   - En vertical no cambia nada.
3. **El resto de colocaciones no cambia:** portada de centro, póster, `DetailPane` con miniatura. Revisa de todos modos tus capturas buscando huecos de más de 24 dp entre un título y su contenido, o franjas vacías de más de 48 dp de alto, y corrígelos si salen de un wrapper de L0.
4. **Tests:**
   - `LandscapeLayoutTest` comprueba la posición del título del lector (`S + 52` ± 4) y la altura de los botones (44 y 48).
   - `LandscapeAuditTest` añade el detector `VERTICAL_GAP`: un hueco de más de 24 dp entre un nodo `heading` y el siguiente nodo de texto de su mismo panel.
5. **Capturas:** rehaz las de los composables de prueba y las láminas `_B_*`. En la autoevaluación, el criterio 1 incluye P9.

Hazlo en el push C o en un commit propio antes de él.

## Añadido (23-nov): dos fallos HIGH de la revisión de tableta, que también van en L0

`docs/design/TABLET-REVIEW.md` revisó la interfaz con la skill `better-interface` en sus 7 dominios. Encontró dos fallos de **toda la app** en componentes que L0 ya toca. Inclúyelos en el push C:

1. **Foco de teclado invisible.**
   - **Dónde:** `ui/design/LumenFoundation.kt:143`. `lumenPress` usa `.clickable(source, indication = null, …)` y lo usan 72 controles: riel, barra, segmentos, filas, botones y tarjetas. Con un teclado (tableta con funda, Chromebook) no se ve dónde está el foco (WCAG 2.4.7).
   - **Arreglo:** un `IndicationNodeFactory` propio que solo dibuja con `FocusInteraction` del mismo `source`. Pinta un anillo de 2 dp en `palette.textPrimary` (≥ 3:1 sobre cada superficie) con la forma del control. Sin ripple, conservando el resorte de presión.
   - **Test:** `requestFocus()` en un control `lumenPress` y comprobar que el anillo se dibuja. Por ejemplo con una captura, o comprobando que el nodo de indicación está activo.
2. **«Quitar animaciones» del sistema ignorado.**
   - **Dónde:** `MainActivity.kt:78` pasa solo `preferences.reduceMotion` (falso por defecto). Solo `ui/components/SacredArt.kt:125` lee `ValueAnimator.areAnimatorsEnabled()`.
   - **Arreglo:** `reduceMotion = preferences.reduceMotion || (Build.VERSION.SDK_INT >= 26 && !ValueAnimator.areAnimatorsEnabled())`, reevaluado al volver a la app (`onResume`).
   - **Test:** con `ShadowValueAnimator.setDurationScale(0f)`, `LocalAppDesign.current.reduceMotion` es `true`.

## Revisión 2 (23-nov) · push C (`ext/T-047` @ `ef5c37b`) · VEREDICTO: CAMBIOS

El trabajo es muy bueno:
- regresión de vertical y tableta idéntica;
- 162/162 casos y `LandscapeRotationTest` 12/12 con recreación real;
- informe honesto con sus cifras.

El crítico Opus (rúbrica §11 con P9, más la skill `better-interface`) da **7–9**: espacio 7, jerarquía 8, legibilidad 8, ergonomía 9, coherencia 8, estados 9, insets 9, giro 9, **accesibilidad 6**. Faltan los cambios de abajo. **No se integra todavía.** Haz los cambios en la misma rama.

### Decisión del dueño (23-nov): el lector NO lleva imagen

La «aclaración posterior» que citas en el REPORT (usar la ilustración en el apoyo izquierdo) queda **sustituida**. El dueño confirma §5.1: **sin arte en `kind = Reader`**.
- **Quita la llamada al arte del lector:** `AppComponents.kt:460` y el velo de `LumenComponents.kt:1364-1378`.
- **El panel queda así:** fila de atrás, antetítulo y título arriba en `S + 52`, meta e índice, y el pie Escuchar · Aa.

### Cambios pedidos

1. **HIGH · Foco visible con teclado** (ya pedido en «Añadido 23-nov»).
   - **Dónde:** `LumenFoundation.kt:149` (`lumenPress`) y `LumenKit.kt:920`, que repite `indication = null`.
   - **Arreglo:**
     - un `IndicationNodeFactory` que solo dibuja con `FocusInteraction.Focus` del mismo `source`;
     - anillo de 2 dp en `palette.textPrimary` con la forma del control (añade `shape` a `lumenPress`);
     - sin ripple y conservando el resorte.
   - **Test:** con `requestFocus()`, el anillo se dibuja.
2. **HIGH · «Quitar animaciones» del sistema.**
   - **Dónde:** `MainActivity.kt:78`.
   - **Arreglo:** `preferences.reduceMotion || (SDK_INT >= 26 && !ValueAnimator.areAnimatorsEnabled())`, reevaluado en `onResume`.
   - **Test:** con `ShadowValueAnimator.setDurationScale(0f)`.
3. **HIGH · `CompactBand` es una franja vacía (P9).**
   - **Dónde se ve:** en las 5 raíces y las 4 identidades (`_A_explorar.png`, `l0_mi-camino_lumen_oscuro_891x411.png`), y también en la tira de 96 de la lista-ficha (`l0_listdetail_glass_oscuro_891x411.png`).
   - **Por qué:** `LumenComponents.kt:1324-1330` apila `fadeOutBottom(0.45f)`, `fadeStart = 0.15f` y un velo `canvasTop` de 0,90 / 0,94 / 0,40. El arte queda al 5–10 %.
   - **Arreglo:**
     - quitar el velo 0,90 / 0,94;
     - un solo degradado de legibilidad bajo el título (`0f` y `0.5f` transparente, `1f` a `canvasTop.copy(alpha = 0.85f)`);
     - `fadeOutBottom` desde 0,55.
   - **Verificar:** el título ≥ 3:1 y la meta ≥ 4,5:1 sobre lo pintado (`DesignContrastTest` o la auditoría).
4. **HIGH · Sanctuary: la banda corta su marco y monta el título sobre el filete.**
   - **Dónde se ve:** `l0_explorar_sanctuary_claro_891x411.png`, y Hoy y Mi camino en `_contact_roots_800_s130_pt.png`.
   - **Arreglo:** en Sanctuary, la banda usa la rama **sin arte** (`S + 8` + bloque de título sobre papel). Un marco de 96 o más no cabe en 84.
5. **HIGH · Folio de Sanctuary: 70–90 dp de papel vacío y la ranura oculta sin señal.**
   - **Dónde se ve:** `l0_hub_sanctuary_claro_891x411.png` («Marianas» y «Buscar» bajo el borde, «Básicas» cortada) y `l0_hub_sanctuary_claro_800x360.png` (sin señal de que hay más).
   - **Arreglo:**
     - en Sanctuary, el bloque de título va **arriba**: `S + 52` con fila de atrás y `S + 12` sin ella, como pide §2.5 («título arriba»). Es una **excepción declarada a §3.2**, como la del lector;
     - el marco va bajo la meta, con alto igual al restante − la ranura medida − los huecos, y se oculta si queda por debajo de 64;
     - en todos los `SidePanel` que aún puedan desplazarse (`canScrollForward`), un fundido inferior de 24 dp como señal.
6. **MEDIUM · Lectura opaca desde arriba.**
   - **Qué falla:** `AppComponents.kt:477/484` desplaza el viewport 52 dp, pero el `canvasTop` empieza en ese viewport, así que entre `S` y `S + 52` se ve la luz ambiente (`l0_reader_glass_claro_800x360.png`).
   - **Arreglo:** para `kind = Reader`, la superficie `canvasTop` va detrás de `main` desde `safe.top`. El texto sigue en `S + 52`.
7. **MEDIUM · Hojas: 44 dp entre el título y el contenido (P9).**
   - **Dónde se ve:** `l0_appearance_lumen_claro_891x411.png`; la causa está en `LumenExtras.kt:579`.
   - **Arreglo:** en compacto, el título a 12 dp del borde inferior de su cabecera, y el cuerpo sin el `padding(top = 8.dp)`.
   - `VERTICAL_GAP` tiene que cubrir también las hojas.
8. **MEDIUM · El diálogo compacto no tiene velo propio.**
   - **Dónde se ve:** `l0_dialog_lumen_claro_800x360.png`: el fondo queda a pleno contraste.
   - **Arreglo:** el mismo velo que dibuja `AppSheet` (`p.scrim` + cierre al tocar), con `window.setDimAmount(0f)`.
9. **LOW · Pies de Hub y Supporting sin material.**
   - **Dónde se ve:** `l0_supporting_lumen_claro_800x360.png`: tarjeta cortada en recto y renglón partido.
   - **Arreglo:** el pie con el material de su columna. En Tradicional y Sanctuary, un filete `separator` de 0,5 dp; en Lumen y Cristal, un fundido de 16 dp.

### Las tres cifras que dejaste abiertas (decisión del coordinador)

- **Inter 19 en el lector** (PT a 800 da 56,5 caracteres): se acepta ≥ 56 con sans, igual que en las fichas. El rango 60–75 se mantiene para la serif predeterminada.
- **Banda al 33,75 % con H = 320:** se acepta. 320 dp es el mínimo absoluto; el criterio del 30 % rige en 360 y 411.
- **Columna principal de 192 con W = 600 y recortes:** se acepta como caso límite, pero la columna no baja de 192 y la medida baja a una columna.

### Entrega

- Push con los nueve cambios.
- Capturas rehechas de las láminas afectadas: raíces, ejemplos, lector, hojas y diálogo.
- REPORT actualizado con la autoevaluación.
- Después, el crítico confirma solo estos puntos y se integra L0.
- **Tests del coordinador sobre la fusión** (`wip/t047-merge`): `Landscape*`, `CompactPrayerGuidesTest`, los de AGENTS.md, `TabletLayoutTest`, `NarrowLargeTextAuditTest`, `Font*`, `WelcomePersonalizationTest` y `MyPathUiTest`. Resultado: **273/274**.
  - Falla `MyPathUiTest.todaySummaryShowsHabitsFidelityPlanAndNovenaAndEachRowOpensItsPlace`, que pasaba 13/13 al integrar T-046.
  - Comprueba si es intermitente o un efecto de L0, y deja la clase en verde.

### Añadido a la revisión 2 · el foco no debe quedar bajo el dock (WCAG 2.4.11)

Al arreglar 9 tests rotos desde T-046 (`181f90c`), se vio que en las identidades con dock flotante (Lumen, Cristal y Tradicional) `bringIntoView` y `performScrollTo` dejan la fila en el borde inferior, **bajo el dock**. El toque cae en el dock.

En la app, lo mismo le pasa al foco de teclado o de TalkBack: el elemento enfocado puede quedar tapado. Va con el punto 1 de la revisión (foco visible).

**Arreglo:** en el andamiaje (`LumenKit.kt`, contenido de las pestañas con dock), un `BringIntoViewSpec` propio, o un margen de desplazamiento igual al alto del dock, para que un elemento que se trae a la vista (por foco o por `bringIntoView`) quede **por encima** del dock.

**Test:** enfocar la última fila de Explorar y comprobar que su borde inferior queda por encima del borde superior del dock. Así, el helper de test `revealAboveDock` (`app/src/test/java/com/example/TestDockClearance.kt`) deja de hacer falta en las pantallas que lo usan.
