# T-038 · Índice completo del Catecismo, conectado a la app

- **Rama:** `CatholicGPT` (sin commit ni push, según lo pedido).
- **Contenido usado:** `app/src/main/assets/catechism/structure.json` (T-036), tal cual; no se ha tocado `assets/`, ni `assets/plans`, ni versiones, firma, `tools/hermes-preview.gradle`, `dist/` ni el esquema de Room.

## Qué hace ahora la app

1. **Catálogo** `CatechismStructureCatalog` (`data/repository/`), con el patrón de `ConfessionCatalog` / `ReadingPlansCatalog`: `install(context)`, `default`, `override` (solo tests), Moshi con validación y respaldo por classpath.
   - Valida: JSON legible, `id` único en snake_case, `kind` conocido, títulos es/en/pt no vacíos, `range` `[a, b]` válido, hijos dentro del padre y sin solaparse, y que el prólogo y las 4 partes cubran 1..2865 sin huecos.
   - Ordena los hijos por posición (en el JSON, `p1s2c3` trae el «Amén» antes que los artículos).
   - Si falla (asset ausente, JSON roto, validación): registra `LogEvent.CATECHISM_STRUCTURE` en `AppLog` y cae al `CatechismOutline` de siempre (Prólogo + 4 partes con las ~30 secciones debajo). No se inventa ningún título: el respaldo solo usa títulos que el outline ya tenía. En ese modo no hay etiquetas con número («Artículo 9»), porque el outline no las tiene.
   - Ayudantes en `CatechismStructure`: `breadcrumb(n)`, `deepestNode(n)`, `nodesAtDepth(d)`, `nodesOfKind(k)`, `nodeForRange(a, b)`, `titleNodes(range)` (para el plan) y `adjacent(a, b, forward)` (lectura anterior/siguiente).
   - `CatechismLabels` compone la etiqueta del nodo a partir de `kind` y de la posición (el número final del `id`, p. ej. `p1s2c3a9` -> 9), porque los títulos no llevan números: «Primera parte / Sección segunda / Capítulo tercero / Artículo 9 / Párrafo 2» y su forma corta para la miga de pan («Parte 3 › Sección 2 › Capítulo 2 › Artículo 6»), en ES, EN y PT. El prólogo y los apartados (`HEADING`) no llevan etiqueta, solo título.
   - Instalado en `SanctaFidesApplication`; `src/main/assets/catechism` añadido como directorio de recursos de test en `app/build.gradle.kts`.

2. **Pantalla del Catecismo** (`CatechismScreen.kt`) — enfoque **mixto: baja a la parte y se despliega dentro de ella**.
   - **Raíz:** el grupo «Contenido» (`ccc_index`) lista el nivel 0 del árbol: Prólogo y las 4 partes, con título, «Primera parte · CIC 26–1065» («CCC» en inglés). Cada parte abre su propia página (la que ya existía), y el Prólogo abre el lector. Se mantienen `catechism_part_N`; cada fila lleva además `ccc_node_<id>`.
   - **Página de la parte:** reemplaza la lista plana del outline por el árbol real. Sección → Capítulo → Artículo → Párrafo se **despliegan en el sitio** (flecha arriba/abajo, `stateDescription` plegado/desplegado, sangría de 14 dp por nivel). Una hoja abre el lector con el rango del nodo. Al desplegar un nodo aparece como primera fila **«Leer»** (`ccc_read_<id>`, con su rango y número de párrafos), que abre el nodo completo; es el «Leer» de cualquier nodo y cubre las introducciones anteriores al primer hijo. La parte tiene su propio «Leer» arriba.
   - Por qué este enfoque: una sola página con los 123 nodos desplegables sería interminable y empujaría el Compendio; bajar a la parte mantiene la raíz corta y la pila de navegación existente, y desplegar dentro de la parte evita 3 pantallas más para llegar a un artículo. Lo desplegado se conserva al leer y volver (estado elevado y guardado), y el punto de lectura reciente se marca con el punto de color.
   - **Se conservan** el buscador, el salto por número, los enlaces cruzados (`onOpenCatechism(paragraph)`) y todos los tags previos salvo `catechism_section_<n>` (ver «Limitaciones»). «Párrafos clave» (`catechism_key_<n>`) sigue en la página de la parte.

3. **Lector con cualquier rango.**
   - `CccPage.Reader(section, focus, last)` guarda ahora el fin del rango y se serializa como `r:primero:foco:último`. El estado guardado antiguo (`r:primero:foco`, sin `último`) sigue valiendo: se resuelve por la sección del outline que empieza ahí.
   - `resolveSection(first, last)`: si coincide con una sección del outline se usa esa (con su «Escritura clave»); si no, se construye un `CatechismOutlineSection` a partir del nodo con ese rango exacto (o del nodo más profundo que contiene `first`), sin referencias bíblicas. Así el lector, el progreso, «Continuar leyendo», el hoja «Contenido» y «Ir al §» no cambian.
   - Los enlaces profundos (cuestiones, cursos, planes, búsqueda global) siguen abriendo la sección del outline que contiene el párrafo, enfocado.
   - **Anterior / Siguiente:** en secciones del outline, el vecino del outline; en rangos del árbol, `adjacent()`: el nodo contiguo del mismo nivel (o, si no existe, el tramo propio contiguo del nodo, p. ej. la introducción de un capítulo).
   - **Miga de pan (`ccc_breadcrumb`):** una franja fija bajo la barra («Parte 2 › Sección 2 › Capítulo 1 › Artículo 1» y debajo el título de ese nodo) que **sigue al desplazamiento**: se recalcula solo cuando cambia el nodo más profundo del párrafo que ocupa la parte superior (el primero cuya mitad es visible, para que un párrafo asomando 24 dp no cuente). Tocarla abre el índice de la parte con la ruta desplegada (`back_to_catechism_outline`, antes en la cabecera). El lector ya no se desliza bajo la franja: la lista empieza debajo de ella, con la línea de progreso entre ambas. En un rango del árbol, la cabecera muestra la etiqueta del nodo («Artículo 1») como sobretítulo.

4. **Plan «El Catecismo en un año»** (`ReadingPlansScreen.kt`). Cada día muestra, bajo «CIC 1–8», el subtítulo con el nodo común más profundo; si el día cruza una frontera, los dos nodos de cada extremo unidos con « · » (máximo 2; si uno contiene al otro, solo el exterior). Ejemplos: día 1 -> «Prólogo»; día 153 (§§ 1217–1224) -> «El sacramento del bautismo»; día 161 (§§ 1281–1288) -> «El sacramento del bautismo · El sacramento de la confirmación». Sin árbol, queda el subtítulo antiguo. No se tocan `assets/plans` ni los límites de los días.

5. `MoreScreen` no necesitaba cambios (`CatechismOutline.totalParagraphs` sigue siendo 2865).

## Archivos

Nuevos:
- `app/src/main/java/com/example/data/repository/CatechismStructureCatalog.kt`
- `app/src/test/java/com/example/CatechismStructureCatalogTest.kt`
- `app/src/test/java/com/example/CatechismIndexTest.kt`
- `app/src/test/java/com/example/screenshots/CatechismIndexScreenshotTest.kt`
- `docs/tasks/T-038-shots/*.png` y este informe

Modificados:
- `app/build.gradle.kts` (recursos de test `catechism`)
- `data/diagnostics/AppLog.kt` (`CATECHISM_STRUCTURE`)
- `SanctaFidesApplication.kt`
- `ui/screens/CatechismScreen.kt`
- `ui/screens/ReadingPlansScreen.kt`
- Tests: `CatechismInteractionTest.kt` (`backPopsOnePageAtATime`), `ReadingPlansUiTest.kt` (test nuevo), `screenshots/CatechismScreenshotTest.kt` (los escenarios que pasaban por `catechism_section_<n>`).

Solo se usan los componentes ya existentes de la pantalla (`CatRow`/`CatRowShell`, que se apoyan en `AppListGroup`, `AppChevron`, `AppSeasonDot`) y `AppListRow` en el plan; el único añadido es un parámetro `indent` en `CatRowShell`. `AppListGroup` ya aplica la guarda horizontal: no se duplica.

## Pruebas

- **Catálogo** (`CatechismStructureCatalogTest`, 9 tests): carga y 123 nodos; el árbol cubre 1..2865 sin huecos ni solapes; los 2865 párrafos tienen miga; migas de 1, 1213, 2084, 2746 (bajo «La oración de la Hora de Jesús») y 2855 (bajo «La doxología final»); etiquetas en ES/EN/PT; subtítulos del plan (los 365 días dan 1–2 nodos); anterior/siguiente; **respaldo** con asset ausente (sin títulos inventados) y rechazo de assets rotos.
- **UI** (`CatechismIndexTest`, 7 tests): abrir el Catecismo, Parte 2 -> Sección 2 -> Capítulo 1 -> «El sacramento del bautismo», el lector empieza en 1213 (no existe el 1212) con la miga; la miga sigue al desplazamiento entre artículos; Siguiente y salto «Ir al §» (2084 -> «Parte 3»); enlace profundo (2746); la miga vuelve al índice desplegado; etiquetas localizadas (EN); respaldo por outline sin asset.
- **Plan** (`ReadingPlansUiTest.catechismDaysNameTheDivisionOfTheBookTheyBelongTo`): día 1 con «Prólogo», día 153 con el título de Bautismo, día 161 con los dos títulos.
- Ejecutados además: todos los tests de Catecismo, `ReadingPlansUiTest`, `SearchUiTest`, `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `FontPreferencesTest` y la suite completa : 794 tests, 0 fallos salvo el conocido `ExampleRobolectricTest`; `:app:assembleDebug` correcto.

## Capturas

`docs/tasks/T-038-shots/` (48 PNG, 2,5 MB tras cuantizar a 128 colores; 411×891 px; 320 dp a 130 % con prefijo `320_130_`), por identidad: `lumen_light`, `sanctuary_dark`, `glass_light` (Cristal) y `classic_dark` (Tradicional): `index_top`, `part`, `index_articles` (desplegado hasta los artículos), `reader`, `reader_scrolled` y `plan_day` (día 161 con los dos títulos). He revisado una muestra de cada identidad y de cada tamaño (index_top, index_articles, reader, reader_scrolled, part, plan_day); no he visto recortes ni solapes. En 320 dp / 130 % los títulos y las etiquetas se parten en varias líneas sin cortarse, y el acrónimo «CIC» no se separa de su número (espacio duro).

## Limitaciones y decisiones

- **`catechism_section_<n>` desaparece.** Era la fila de las ~30 secciones del outline en la página de la parte; el árbol la sustituye por `ccc_node_<id>` / `ccc_read_<id>`. Se han adaptado `CatechismInteractionTest.backPopsOnePageAtATime` y los escenarios de `CatechismScreenshotTest`. El resto de tags previos se conserva. Si hace falta, se puede añadir un envoltorio con el tag antiguo.
- Las etiquetas de parte usan la forma del libro y de la app existente («Primera parte», «Part One», «Primeira parte»), no «Parte primera», para coincidir con el Compendio y «Continuar leyendo».
- Los títulos son los de la edición revisada tal cual (p. ej. «El sacramento del bautismo» en minúscula; así lo dejó T-036).
- Los enlaces profundos y la búsqueda siguen abriendo la sección del outline (más contexto que un artículo); la miga indica el artículo dentro de ella. «Continuar leyendo» guarda el párrafo, no el rango.
- Sin árbol (respaldo) no hay etiquetas con número, y las filas muestran solo los títulos del outline.
- La franja de miga ocupa 2–4 líneas con texto grande en 320 dp; es el coste de tenerla siempre visible.
- Validación solo con Robolectric/capturas; no hay verificación en dispositivo real ni comparación con la web.
- `ExampleRobolectricTest` «read string from context» falla por el script de init del preview (conocido).
