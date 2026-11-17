# T-030 · Búsqueda global — informe

Integración local (no hay commit ni push; lo hace el coordinador). Rama de trabajo `CatholicGPT`, partiendo de `64d1b2c`.

## Qué hay

Un destino nuevo `AppDestination.SEARCH` («Buscar» / "Search" / «Buscar», tag `nav_search`) con un único campo (`AppSearchBar`, tag `search_field`) que busca en todo el contenido de la app sin conexión.

- **Entradas:** lupa en el cabezal de Hoy (`global_search_button`, junto a la campana, mismo `actions` de `AppHeroHeader`) y una fila «Buscar» arriba de Explorar (tag `nav_search`). No está en las pestañas inferiores.
- **Campo:** toma el foco al abrir (`AppSearchBar` ganó el parámetro opcional `autoFocus`), busca desde 2 caracteres con 250 ms de *debounce*. Sin tildes ni mayúsculas, en el idioma de la interfaz.
- **Resultados:** agrupados por tipo (`search_group_<tipo>`, `search_result_<tipo>_<id>`); cada grupo enseña 5 y «Ver todos (N)» (`search_more_<tipo>`) los despliega en el sitio (hasta 50; si hay más, lo dice). Cada fila lleva el título y, si la coincidencia está en el cuerpo, un fragmento con el texto alrededor. Los grupos se ordenan por la mejor coincidencia; las pantallas «por nombre» van detrás del contenido que coincide igual de bien.
- **Vacío:** «Sin resultados · Prueba con otra palabra.» (`search_empty`). Con menos de 2 caracteres, una pista breve (`search_hint`).
- **Cabezal:** `AppLargeTitleHeader` (sin ilustración: es una herramienta, y así el campo queda siempre a la vista a 320 dp al 130 %). Solo wrappers de `AppComponents.kt` / `AppKit`: `AppListGroup`, `AppListRow`, `AppSectionHeader`, `AppSecondaryButton`, `AppBanner`, `AppEmptyState`. Idiomas ES/EN/PT. Las cuatro identidades, claro y oscuro (capturas abajo).
- **Privacidad:** no se guarda historial. La consulta vive solo en el estado de la pantalla; cada entrada nueva (`searchEntryId`) descarta el estado de la visita anterior, y volver desde un resultado conserva la consulta. Nada se escribe en DataStore, Room ni disco.

## Qué se indexa (`com.example.data.search`)

| Tipo | Fuente | Título | Texto buscable |
|---|---|---|---|
| `prayer` | `repository.vaticanPrayers` (incluye `PrayerDetailsCatalog`) | título | título latino y categoría, texto, respuesta, cuándo rezar, etiquetas |
| `novena` | `NovenaCatalog.default.allNovenas` (**solo metadatos**; no se carga ningún cuerpo, comprobado en test con `cachedBodyCount() == 0`) | título | patrono, categoría, descripción, etiquetas |
| `saint` | `GeneralRomanSanctoral` + `SaintsCatalog` (solo celebraciones con ficha, que son las que tienen página que abrir: 215) | nombre | fecha y rango, biografía, patronazgos |
| `devotion` | `DevotionsCatalog` (mariano + Cristo, 93) | título | país, historia, significado, santuario, patronazgo |
| `order` | `OrdersCatalog` (órdenes + oficios, 157) | nombre | abreviatura, fundador, descripción, carisma, lema, santos |
| `question` | `CatholicQuestionsData` (101) | pregunta | sección, respuesta, aclaración |
| `course` | `scriptureStudyTracks` (cursos de Kotlin + JSON, 40 lecciones) | título de la lección | curso, referencia, contexto, secciones de enseñanza, objetivos |
| `catechism` | `catechismParagraphText` (2.865 párrafos, idioma activo) | «CIC n» / «CCC n» | texto; subtítulo = sección del índice |
| `plan` | `ReadingPlansCatalog` (2) | título | descripción |
| `section` | títulos de `AppDestination` (18 pantallas) | título | nada más (Confesión: **solo el nombre de la pantalla**, nada de su contenido) |
| `bible` | edición activa (`BibleTextProvider.searchFolded`), **a demanda**, no está en el índice | «Libro cap, v» | versículo |
| `reference` | lo escrito, leído como cita | — | — |

3.831 entradas en total (ES). Las coincidencias empiezan en el inicio de palabra («mar» encuentra «María»; «maria» no encuentra «perfumaría»); hacen falta todas las palabras de la consulta.

### Citas

`BibleReferenceQuery` (nueva clase en `FoundationRefParser.kt`, reutiliza los alias ES/EN/PT de `parseFoundation`; `parseFoundation` queda intacto) lee «Jn 3,16», «Luke 1:1-4», «Sal 23», «gen 1», «1 Cor 13». «CIC 1030», «ccc 1030», «§1030» y «1030» abren el párrafo del Catecismo. La cita sale en un grupo propio («Ir directamente») arriba del todo. Los Salmos se leen con la numeración habitual (hebrea) y se convierten con `ReadingPlanLinks.bibleTarget` (`PsalmNumberingMap`) a la de la edición activa: «Sal 23» abre el capítulo 22 en las ediciones de numeración Vulgata (la ES incluida), y el título lo dice («Salmos 23 (22)»).

## Adónde navega cada resultado

Todo abre una pantalla o ficha que ya existía; no hay vistas paralelas. La pantalla de origen se apila bajo el destino, de modo que **atrás vuelve a los resultados** con la consulta intacta (en las pantallas con detalle interno se añadió un `onExitContext` para que atrás no pase antes por la lista de la propia pantalla).

| Resultado | Destino | Enlace |
|---|---|---|
| Oración | Orar (apilado sobre Buscar) → ficha de la oración | `prayersInitialPrayerId` / `initialPrayerId` (ya existía) |
| Novena | Orar → pestaña Novenas → resumen de la novena | `initialNovenaId` **(nuevo, mínimo; se consume una vez con `rememberSaveable`)** |
| Santo | Santos → ficha | `initialKey` **(nuevo)** |
| Devoción | María y Jesús → ficha `devotion:<id>` | `initialEntry` de `DoctrineAndHistoryScreen` (ya existía) |
| Orden / oficio | Órdenes y oficios → `order:<i>` / `office:<i>` | ídem |
| Pregunta | 101 preguntas → `qa:<i>` | ídem |
| Lección | Leer → Cursos → lección | `initialLessonId` en `BibleScreen`/`ScriptureStudyScreen` **(nuevo)** |
| Catecismo (y cita CIC) | Catecismo en el párrafo | `onOpenCatechism` |
| Biblia (texto y cita) | Lector en el capítulo y versículo | `onOpenBible` |
| Plan | Planes de lectura → plan | `initialPlanId` **(nuevo)** |
| Sección | la pantalla (Rosario, Hábitos, Confesión…) | `go()` / `openRosary` / `openHabits` |

En `MainApp.kt`: los enlaces nuevos (`saintsInitialKey`, `studyInitialEntry`, `prayersInitialNovenaId`, `bibleInitialLesson`, `plansInitialId`) se limpian en `go()`, `onOpenBible` y al elegir una pestaña, así ningún otro camino hereda un enlace de la búsqueda; las claves de `SaveableStateProvider` de esas pantallas los incluyen para que cada llegada empiece de cero.

## Rendimiento

- **Índice en memoria, una vez, en `Dispatchers.IO`.** `SearchIndexHolder` (singleton con `Mutex`) lo construye la primera vez que se abre el buscador (mientras la persona escribe) y lo reutiliza; un cambio de idioma construye otro. Sin red, sin Room, sin disco nuevo. El hilo principal solo ve «Preparando la búsqueda…» si escribe antes de que termine.
- **Coste medido** (JVM de Robolectric, no un dispositivo; ver `buildTimeIsReported` en `GlobalSearchIndexTest`): construcción **~670 ms en frío** (primer uso del proceso: carga de las 29 clases del Catecismo, JSON de santos/devociones/órdenes/cursos/planes) y **55–80 ms en caliente**; 3.831 entradas. Cada consulta recorre el índice en **~2–4 ms**.
- **Biblia:** no se indexa (35.000 versículos × 3 idiomas × ediciones). Se busca a demanda en IO, sobre el texto ya parseado de la edición activa, comparando sin asignar memoria por versículo, con tope de 50 (`50+` en pantalla). Los resultados del índice aparecen antes; el grupo de la Biblia llega después.
- `AppSearchBar`/pantalla usan `produceState` con la consulta como clave: cada pulsación cancela la búsqueda anterior (ese es el *debounce* de 250 ms).

## Pruebas

Ejecutadas con `gw.sh` (cerrojo `flock`), Robolectric:

| Clase | Pruebas | Resultado |
|---|---|---|
| `GlobalSearchIndexTest` (nueva) | 10 | OK. Una consulta por tipo encuentra su elemento; «maria»/«MARÍA» encuentran «María»; «Sal 23» → `Bible(psa, 22)` en `bundled-es` (y 23 en una edición de numeración hebrea); «Jn 3,16» → `Bible(joh, 3, 16)`; «Luke 1:1-4», «gen 1», «1 Cor 13», «CIC 1030» y los falsos («Sal 151», «CIC 9999», «maria»); inicio de palabra («perfumaría» no es «maria», sobre toda la Biblia PT); tope de 50 y accentos en el texto bíblico; EN/PT; ningún cuerpo de novena cargado; tiempos. |
| `SearchUiTest` (nueva) | 8 | OK. Hoy → lupa → foco → escribir → grupos → tocar un resultado → ficha → atrás conserva la consulta → reabrir empieza vacío; no está en las pestañas; entrada en Explorar; vacío; 5 por grupo y «Ver todos»; oración, novena, devoción, orden y pregunta abren su ficha; lección, plan, Catecismo y Confesión abren su pantalla; cita Jn 3,16 y texto bíblico abren el lector; EN. |
| `SearchScreenshotTest` (nueva) | 12 | OK (3 pruebas × 4 identidades). |
| `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `FontPreferencesTest`, `ReadingPlansUiTest`, `ConfessionInteractionTest` | 5, 8, 1, 4, 4, 3 | OK |
| Además (tocan pantallas modificadas): `FontPickerInteractionTest`, `ReadingPlansLogicTest`, `RetainedNavigationTest`, `SaintsScreenTest`, `StudyJourneyTest`, `StudyCourseCatalogUiTest`, `DevotionsScreenTest`, `OrdersScreenTest`, `NovenaIndexJourneyTest`, `PrayerDetailsUiTest`, `FoundationRefParserTest`, `CatechismInteractionTest`, `BibleReadingJourneyTest`, `QuestionDeeperUiTest`, `HomeTextTest`, `HomePrayerPrioritiesTest`, `RosaryInteractionTest` | 143 en total con las anteriores | OK |

Suite completa (`:app:assembleDebug :app:testDebugUnitTest`): ver el último apartado.

## Capturas (`docs/tasks/T-030-shots/`, PNG al 60 %)

Cada identidad (`lumen` = Liquid Glass, `sanctuary`, `glass`, `classic`) con su modo: `lumen_light_*`, `sanctuary_dark_*`, `glass_light_*`, `classic_dark_*`.

- `*_empty.png`: pantalla al abrir (foco en el campo, pista).
- `*_maria.png`, `*_maria_scrolled.png`, `*_maria_expanded.png`: consulta «María» con varios grupos, bajando hasta el Catecismo y la Biblia, y con «Ver todos» abierto.
- `*_320_130_maria.png`, `*_320_130_maria_bible.png`: **320 dp al 130 %**, portugués («Maria»).
- `*_en_reference.png`, `*_en_empty.png`: inglés, cita «John 3:16» y sin resultados.

Se revisaron las de las cuatro identidades: sin recortes ni solapes; el campo y los títulos largos de devociones envuelven bien a 320 dp al 130 %. Lo único que se corrigió tras verlas: «1 resultados» (ahora «1 resultado»), las pantallas por nombre encabezaban la lista en «María» (ahora van detrás) y «maria» coincidía dentro de «perfumaria» (ahora solo inicio de palabra).

## Cambios fuera de `data/search` y `ui/screens/SearchScreen.kt`

`MainApp.kt` (destino, enlaces, `openSearch`/`openFromSearch`, claves de estado, entrada en Explorar), `HomeScreen.kt` (lupa), `MoreScreen.kt` (fila de búsqueda), `LumenExtras.kt` (`autoFocus` en `AppSearchBar`), `FoundationRefParser.kt` (`BibleReferenceQuery`), `BibleTextProvider.kt` + `CatholicDataRepository.kt` (`searchFolded` / `searchBibleText`), y los parámetros opcionales de enlace en `PrayersAndNovenasScreen`, `SaintsScreen`, `BibleScreen`, `ScriptureStudyScreen` y `ReadingPlansScreen`. No se tocó `assets/`, versiones, firma, `tools/hermes-preview.gradle`, `dist/` ni el esquema de Room.

## Límites

- Los fragmentos son texto plano con puntos suspensivos: `AppListRow` recibe `String`, así que la coincidencia **no va resaltada** en negrita.
- La Biblia se busca por el orden canónico y se queda con los primeros 50 versículos: no hay ranking por relevancia (como la búsqueda propia del lector). En ediciones descargadas se lee libro a libro, más lento (no medido).
- Las novenas se encuentran por título, patrono, categoría, descripción y etiquetas, **no por el texto de sus días** (cargar los 246 cuerpos iría contra el rendimiento). Los santos sin ficha no se indexan (no tienen página); se encuentran en su lista.
- Sin tolerancia a erratas, sinónimos ni raíces: coincidencia por inicio de palabra. Búsqueda solo en el idioma de la interfaz.
- Los enlaces a órdenes, oficios y preguntas van por posición en su lista (como el resto de la app); válidos mientras el contenido sea el incluido.
- Los Salmos con versículos en los capítulos hebreos 9, 10, 114–116 y 147 heredan el límite documentado de `PsalmNumberingMap`.
- El botón de borrar de `AppSearchBar` conserva su descripción fija «Clear» (existente, compartida con otras pantallas).
- No se ha comprobado en un dispositivo real ni se ha comparado con ningún sitio web; los tiempos son de la JVM de Robolectric. En Robolectric no hay teclado: el foco se comprueba con `assertIsFocused`.
