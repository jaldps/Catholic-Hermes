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
