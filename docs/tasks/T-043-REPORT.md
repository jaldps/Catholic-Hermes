# T-043 · Diseño para tabletas y pantallas grandes

Estado: implementado y verificado con Robolectric (no se ha probado en dispositivo real ni en un plegable físico).
Los teléfonos (< 600 dp) no cambian: misma barra inferior, mismas pantallas y mismo flujo.

## Puntos de corte

Se miden con `LocalConfiguration.screenWidthDp`, es decir, el ancho de la ventana (también en pantalla dividida y plegables).
Constantes en `AppWindow` (`ui/design/AppComponents.kt`).

| Ancho | Comportamiento |
|---|---|
| < 600 dp | Teléfono: barra inferior, sin cambios. |
| ≥ 600 dp | Barra inferior sustituida por un **riel de navegación** en el borde inicial. El contenido reserva el ancho del riel (92 dp, 88 dp en Sanctuary) en lugar del alto de la barra, y cada página es una **columna de lectura centrada** (`readingMaxWidth` + gutters = 680 dp). |
| ≥ 840 dp | **Dos paneles** (lista 360 dp + detalle) en Santos, Órdenes y Devociones. |

## Riel de navegación

- Es una variante del contenedor de navegación existente: lo dibuja `LumenKit.Scaffold` (`ui/design/LumenKit.kt`), sin cambiar la interfaz `AppKit` ni las firmas de `AppComponents.kt`. Cada identidad dibuja el suyo:
  - Lumen, Cristal y Tradicional: cápsula flotante de cristal con la lente que se desliza en vertical (el material sigue los tokens de cada identidad; opaco con «Reducir transparencia» / alto contraste).
  - Sanctuary: tira de papel con filete a todo el alto y marca de acento bajo la etiqueta.
- Mismos 4 destinos, iconos, etiquetas, etiquetas de test `nav_*`, `selected` y comportamiento de selección/reselección (`onNavSelected`). Etiqueta de test del contenedor: `navigation_rail` (la barra inferior sigue siendo `bottom_nav_bar` y no existe en pantallas anchas).
- Con tamaños de letra grandes el alto de cada destino crece para que las etiquetas no se recorten (máx. 2 líneas).
- Decisión: el riel permanece visible aunque un lector pida ocultar la barra (`AppHideTabBar`). La barra inferior se oculta en teléfono porque invade la lectura; el riel no la invade.
- Insets: en modo riel el relleno inferior vuelve a ser solo la barra de navegación del sistema (`appDockInset()`), y el contenido, la barra superior en scroll y la fila de atrás/acciones se desplazan el ancho del riel. El lienzo ambiental y el fondo siguen a pantalla completa.

## Ancho de lectura

- `MainApp` limita cada página a `appContentMaxWidth()` y la centra (en el área junto al riel) cuando la ventana es ancha. Así Biblia, oraciones, novenas, Catecismo, lectores de doctrina, santos, devociones, Home, etc. no se estiran de borde a borde, sin tocar cada pantalla (varias usaban `widthIn(max=…)` sin centrar y quedaban pegadas a la izquierda).
- Excepciones declaradas con `AppFullWidth()` (`LumenFoundation.kt`; contador de reclamaciones que se libera al salir): las pantallas de dos paneles y el lector bíblico en horizontal (su vista de estudio texto + referencias cruzadas ya existía y usa todo el ancho).
- El panel de detalle de un dos paneles se centra a su vez en una columna de lectura si es más ancho que ella (ventanas ≥ ~1130 dp).

## Cabeceras

- Siguen siendo `AppHeroHeader` / `AppDetailHeader` / `AppLargeTitleHeader`, 236 dp de arte y título en la misma línea base; sin alturas ni estilos nuevos.
- Al estar la página en una columna de 680 dp, el arte ya no se recorta como una tira (3:1 a 5:1) y queda en proporción ≈ 2,9:1. Para que no termine en bordes duros, el arte difumina sus bordes laterales (`fadeSides`) cuando la ventana es ancha. Sigue recortado a su marco; en los paneles de lista no se difumina.
- Sanctuary y los textos muy grandes siguen con su marco de papel.

## Dos paneles

Pantallas con dos paneles (≥ 840 dp): **Santos, Órdenes y Devociones** (las tres).
Los tres paneles usan el wrapper `AppTwoPane` (`AppComponents.kt`).

- **Santos** (`SaintsScreen`): lista a la izquierda (filtros, búsqueda y meses intactos, fila abierta marcada) y ficha a la derecha. Sin selección se abre la primera ficha del calendario (o la de `initialKey`). Seleccionar otro santo cambia el panel derecho; no hay página que apilar, por lo que el botón «Todos los santos» (`saint_detail_back`) no se muestra. Si se llegó desde la búsqueda global, atrás vuelve a ella.
- **Órdenes** (`DoctrineAndHistoryScreen`, pestaña 4) y **Devociones** (pestaña 1, «María y Jesús»): la lista raíz a la izquierda y la entrada elegida (`order:N`, `office:N`, `devotion:id`) a la derecha. Elegir una fila reemplaza la entrada del panel derecho; lo que se abre desde dentro del detalle (siguiente/anterior, etc.) sigue la pila habitual. Sin selección se muestra la primera orden / advocación. La fila abierta se resalta (`LeadRow(selected)`).
- Atrás: en dos paneles solo deshace lo abierto desde dentro del detalle; con la selección inicial, atrás sale de la pantalla (no «cierra» el detalle, que no se puede cerrar).
- Teléfono y ventanas < 840 dp: flujo de siempre (lista → página).

## Archivos modificados

- `app/src/main/java/com/example/ui/design/AppComponents.kt`: `AppWindow`, `appWindowWidth/appUsesNavigationRail/appIsTwoPane/appContentMaxWidth`, `AppTwoPane`.
- `app/src/main/java/com/example/ui/design/LumenKit.kt`: modo riel en `Scaffold`, `Rail`, `GlassRail`, `FolioRail`.
- `app/src/main/java/com/example/ui/design/LumenFoundation.kt`: `ScreenFrame.fullWidth` y `AppFullWidth()`.
- `app/src/main/java/com/example/ui/design/LumenComponents.kt`: difuminado lateral del arte en cabeceras (`LocalHeroSideFade`).
- `app/src/main/java/com/example/ui/design/LiquidGlass.kt`: `Modifier.fadeSides`.
- `app/src/main/java/com/example/ui/MainApp.kt`: columna de lectura centrada en ventanas anchas.
- `app/src/main/java/com/example/ui/screens/SaintsScreen.kt`, `DoctrineAndHistoryScreen.kt`, `DoctrineOrders.kt`, `DevotionsExplorerScreen.kt`, `DoctrineParts.kt` (`LocalOpenEntry`, `LeadRow(selected)`): dos paneles.
- `app/src/main/java/com/example/ui/screens/BibleScreen.kt`: el lector en horizontal usa todo el ancho.
- Tests nuevos: `app/src/test/java/com/example/TabletLayoutTest.kt`, `app/src/test/java/com/example/screenshots/TabletScreenshotTest.kt`.
- No se tocaron `assets/`, versiones, firma, `tools/hermes-preview.gradle`, `.github/`, `dist/` ni el esquema Room.

## Pruebas

`TabletLayoutTest` (4 identidades × 11 casos = 44) a `w840dp-h1200dp`, `w1280dp-h800dp`, `w411dp`, `w599dp`, `w600dp`, `w839dp`:
riel mostrado y barra inferior ausente; riel en el borde, estrecho, destinos apilados y dentro de él; cada destino `nav_*` abre su pantalla, queda `selected` y se puede reseleccionar; el contenido empieza tras el riel; en 411 dp y 599 dp sigue la barra inferior; en 600 dp aparece el riel; en 600–839 dp Santos sigue apilando (con botón atrás); Santos, Órdenes y Devociones muestran lista (360 dp) y detalle lado a lado, seleccionar otro elemento actualiza el detalle sin apilar y la fila queda `selected`; el detalle del horizontal se centra y no supera la columna; la columna de lectura está centrada junto al riel.

Suite pedida: `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `FontPreferencesTest`, `SaintsScreenTest`, `OrdersScreenTest`, `Devotion*`, `MyPathUiTest`, `SearchUiTest`, `RosaryInteractionTest`, `VaticanNavigationLayoutTest`: verdes, sin modificar ningún test existente.

Suite completa (`:app:testDebugUnitTest`): 921 pruebas, 1 fallo, el conocido `ExampleRobolectricTest` «read string from context» (lo causa el init script de la vista previa). Los PNG ajenos que regeneró la suite (`PLANS-shots`, `T-031/034/037/038-shots`) se revirtieron con `git checkout`. `:app:assembleDebug`: correcto.

## Capturas

`docs/tasks/T-043-shots/` (32 PNG, 0,4× de 840×1200 y 1280×800 dp): `{lumen_light, sanctuary_dark, glass_light, classic_dark}_{portrait,landscape}_{1_hoy,2_orar,3_biblia,4_santos}.png`.
Se revisaron una a una. Ajustes hechos a partir de ellas: cargar el arte antes de capturar, difuminar los bordes laterales del arte y dar todo el ancho al lector bíblico en horizontal (con la columna de 680 dp quedaba apretado).

## Limitaciones

- En apaisado (1280 dp) las pantallas que no son de dos paneles (Hoy, Orar…) son una sola columna de 680 dp centrada: cómoda, pero deja mucho espacio libre a los lados. No hay diseño multicolumna en Hoy/Orar.
- En 840 dp el panel de detalle mide ≈ 388 dp (840 − riel − 360 de lista): es ancho de teléfono. Con 1280 dp tiene ≈ 830 dp y se centra a 680 dp.
- Dos paneles solo en Santos, Órdenes (y oficios) y Devociones; el resto de listas (Doctrina, Historia, Catecismo, etc.) siguen apilando.
- La selección de los dos paneles se guarda con el `stack` habitual (Órdenes/Devociones) o `rememberSaveable` (Santos); al girar de ≥ 840 dp a teléfono la entrada abierta se mantiene como página.
- El riel no se oculta en lectores inmersivos (decisión de diseño, arriba).
- Verificado solo con Robolectric (render nativo); sin dispositivo ni plegable real, ni cambio de postura en vivo.
