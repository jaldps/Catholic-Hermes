# T-020 · Informe — Rediseño Liquid Glass

Rama `ext/T-020`, base `origin/CatholicGPT` (`3576e6e`, justo encima de la versión 1.8.12).

**Alcance real de esta entrega.** El encargo escrito en `T-020.md` es un pulido visual. El dueño me pidió algo distinto y más amplio en esta misma rama: **un rediseño completo de la identidad Lumen como Liquid Glass**, con libertad total sobre lo visual y con la uniformidad como criterio principal. El pulido de `T-020.md` queda para el agente «Polish»; si entrega en esta misma rama habrá conflictos en `ui/design/` (ver «Preguntas»).

No se ha tocado contenido (oraciones, Escritura, catálogos), navegación, IDs guardados, `dist/`, firma ni versiones. Se conservan los test tags y las etiquetas de accesibilidad.

## Qué es el rediseño

Tres capas, las mismas en todas las pantallas:

1. **Luz ambiental.** Detrás de todo hay un campo fijo de luz (cuatro focos de color sobre el lienzo), como luz de vitral sobre piedra. Sustituye al gris plano.
2. **Cristal de contenido.** Tarjetas, grupos y fichas son paneles densos y calmados: aptos para leer, pero dejan pasar la luz.
3. **Cristal de control.** Dock, barra, botones flotantes, chips y campos son cristal claro con borde especular. En Android 12+ desenfocan en vivo lo que pasa por debajo; en Android 13+ además lo refractan en el borde (shader AGSL propio).

Todo panel se construye igual (`ui/design/LiquidGlass.kt`): sombra de color, relleno, brillo superior y un borde especular que entra por arriba a la izquierda y vuelve abajo a la derecha. Sanctuary, «reducir transparencia» y «alto contraste» reciben la misma geometría en material opaco.

**Luz litúrgica.** Con el tinte «Dinámico Litúrgico» (el predeterminado), el acento y la luz ambiental siguen ahora el color litúrgico del día: verde, morado, blanco/oro, rojo o rosa. Antes el nombre prometía eso, pero el acento era siempre verde. Es un cambio de comportamiento visible; ver «Propuestas».

## Cambios

### Sistema (`ui/design/`, `ui/theme/`)

- **`LiquidGlass.kt` (nuevo).** Material, luz ambiental, máscara de disolución, fondo vivo grabado (`GraphicsLayer`), lente AGSL, y la respuesta táctil y sonora del sistema al tocar (clic del sistema y tic háptico en pestañas, segmentos, interruptores y casillas; respeta los ajustes del teléfono).
- **Paletas.** «Alba» (claro) y «Vigilia» (oscuro, azul medianoche) recalibradas. Todas las tintas pasan 4,5:1 sobre el lienzo, sobre cada cristal y sobre cada foco de luz de cada color litúrgico y de cada tinte (`LiquidGlassTest`).
- **Acción primaria** en cristal teñido con el acento, en lugar de la cápsula carbón.
- **Formas.** Tarjetas 28, grupos 24, hojas 34, controles en cápsula.
- **Tipografía.** Títulos en seminegrita con tracking más cerrado. La fuente elegida por el usuario sigue aplicándose a todo (`FontTypographyTest`).
- **Armazón.** Dock flotante en cápsula con una lente que se desliza entre pestañas con muelle; barra superior progresiva sin línea dura; el contenido se disuelve en la luz en los bordes de scroll.
- **Componentes.** Segmentados con pulgar deslizante, chips, interruptor, cápsulas, filas con «pozos» de icono teñidos, hojas y diálogos iluminados por la misma luz, diálogo de cristal nuevo (`AppDialog`), marcador de arte mientras carga una imagen.

### Cabezal único (lo que pidió el dueño a mitad de trabajo)

Había diez alturas de imagen distintas (180 a 340 dp), títulos en tres tamaños y en dos posiciones, y pantallas sin cabezal. Ahora `AppHeroHeader`, `AppDetailHeader` y `AppLargeTitleHeader` comparten un solo marco:

- **236 dp de arte** bajo la barra de estado, siempre.
- **El título en la misma línea** en todas las pantallas. Un título largo hace crecer el cabezal hacia arriba; nunca se trunca ni mueve esa línea.
- **Dos rangos, nada más:** `largeTitle` en las raíces de sección y `title1` en todo lo que hay debajo.
- **Antetítulo** encima y **línea meta** colgada debajo, sin mover el título, siempre en el mismo estilo.

Pantallas que cambian por esto:

| Pantalla | Cambio |
|---|---|
| Hoy, Orar, Explorar, Hábitos | Misma altura de arte y misma línea de título (antes 212/260/232/260 dp; Hoy además tenía solape). |
| Biblia | Ahora tiene cabezal con arte como las demás raíces. La tarjeta del Evangelio de Juan usa una pintura propia. |
| Santos, Primeros pasos | Antes sin cabezal; ahora con arte propio. |
| Catecismo, Doctrina, María y Jesús, Historia, 101 preguntas, Órdenes, Noticias, Parroquias, Calendario, Recordatorios, Vida espiritual | Pasan a raíz (`largeTitle`), misma altura. |
| Categoría de oración, oración, Cómo orar, Coronilla, Noche | Misma altura; los textos de entrada a medida pasan a la línea meta. |
| Novena y día de novena | Su cabezal propio (título más bajo) se sustituye por el común. |
| Fichas de doctrina (mandamiento, pregunta, orden, oficio, devoción, época…) y ficha de santo | Abren con el mismo cabezal, con el arte de su sección. |

### Por pantalla

- **Rosario.** La pintura se disuelve en la luz en vez de fundirse a un color; el transporte flota sin banda opaca.
- **Santos.** Lista sin doble margen ni dobles separadores; filtros en una sola fila; ficha en paneles de cristal con cita destacada. El botón atrás del armazón vuelve a la lista.
- **Primeros pasos.** Barra de progreso de siete pasos, tarjeta del paso, «El camino» como grupo de cristal. La bienvenida deja de ser un `AlertDialog` de Material con «✓» en texto.
- **Explorar.** Filas con pozos de icono; tarjetas de estudio con borde especular.
- **Catecismo, Vida espiritual.** Encabezados de sección en `title3`, como el resto.
- **Sanctuary.** Mismo marco de cabezal en su versión de papel (título arriba, arte enmarcado a la misma altura en todas las pantallas).

### Imágenes nuevas

Tres pinturas de dominio público de Wikimedia Commons, para que ninguna raíz repita cabecera. Título, autor, año, licencia y fuente están en `assets/heroes/lumen/manifest.json` y en `docs/redesign/IMAGES.md`.

| Archivo | Obra | Uso |
|---|---|---|
| `saints.jpg` | Durero, *Adoración de la Trinidad* (Retablo Landauer), 1511 | Santos |
| `begin.jpg` | William Holman Hunt, *La luz del mundo*, 1851–1852 | Primeros pasos |
| `john.jpg` | Velázquez, *San Juan Evangelista en Patmos*, 1619–1620 | Tarjeta del Evangelio de Juan |

Los años y las licencias son los que declara la página de cada archivo en Commons; no los he contrastado con otra fuente.

## Capturas

En `docs/tasks/T-020-shots/`, a mitad de tamaño y ≤ 300 KB, con nombre `<pantalla>_<identidad>_<modo>_{before,after}.png`. Las «antes» salen del commit base sin tocar.

- Claro y oscuro: `home`, `prayers`, `bible`, `more`, `rosary`, `habits_virtues`, `appearance`.
- Solo claro: `catechism`, `saints`, `study_orders`, `beginner`, `pilgrimage_habits`, `reminders`, `questions`, `study_mary_jesus`, `spiritual_life`, `parishes`, `doctrine`, `study_history`, `order_detail`, `devotion_marian`, `novena_detail`, `prayer_detail`, `novenas`, `prayer_category`, `bible_reader`.
- `home_sanctuary_light_after.png` (sin «antes»: no rendericé Sanctuary en la base).

**Límite importante de las capturas.** Se renderizan por software (Robolectric), que no ejecuta el desenfoque en vivo ni la lente. Muestran por tanto el material de respaldo: el mismo que verá un teléfono con Android 11 o anterior. En algunas capturas de detalle el arte de cabecera sale en blanco porque ese arnés captura antes de que termine de decodificarse; no es un fallo de la pantalla.

## Tests

Entorno: Java 21, Gradle 9.3.1 (wrapper), Android SDK 36.1, instalado en esta sesión.

**Compilación:** `./gradlew :app:assembleDebug` — correcta.

**Suite completa:** `./gradlew :app:testDebugUnitTest` — 469 tests, 463 pasan y 6 fallan en la última pasada completa:

| Test | Estado | Causa |
|---|---|---|
| `AppNavigationTest` [SANCTUARY] | **Corregido y vuelto a pasar** | Regresión mía: la Biblia en Sanctuary tiene ahora cabezal con arte, y el `performScrollTo` mínimo dejaba la fila de Génesis detrás del dock. El test termina ahora el scroll hasta dejarla pulsable. Tras el cambio, la clase entera pasa (ejecutada sola). |
| `RecoverySheetsScreenshotTest` (4 variantes) | Falla igual en la base | «Clipped sheet title». Falla idéntico en `3576e6e` sin tocar, en las cuatro variantes. No lo he resuelto. |
| `HomePrayerPrioritiesTest` (1 de 4 variantes, cambia en cada pasada) | Inestable en la base | En tres pasadas sobre `3576e6e` sin tocar falló las tres, cada vez en variantes distintas (línea 145, selector de capítulo no visible). |

No he vuelto a lanzar la suite completa después de corregir `AppNavigationTest`; solo esa clase.

**Tests indicados en `T-020.md`:** `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `RosaryInteractionTest`, los de fuentes (`FontPickerInteractionTest`, `FontPreferencesTest`, `FontTypographyTest`, `ReaderFontChoiceTest`), `SaintsScreenTest`, `OrdersScreenTest`, `DevotionsScreenTest`, `VoiceSettingsUiTest` y `screenshots.FullAppScreenshotTest`: todos pasan. `VaticanNavigationLayoutTest` no existe en el repositorio.

**Test nuevo:** `LiquidGlassTest` (6 tests): contraste 4,5:1 de cada tinta sobre lienzo, cristal y cada foco de luz para los cinco colores litúrgicos y los cuatro tintes; etiqueta de la acción primaria sobre el cristal teñido; resolución del color litúrgico; material opaco en Sanctuary, «reducir transparencia» y «alto contraste»; compilación del shader de la lente; respaldo en Android antiguo.

**Tests modificados (dos, por la misma razón):** `AppNavigationTest` y `screenshots.DevotionalPrioritiesScreenshotTest` tocaban un objetivo tras un scroll mínimo que, con los cabezales nuevos, queda detrás del dock flotante. Ahora completan el scroll antes de tocar. No se ha relajado ninguna comprobación.

**Robustez:** `FullAppScreenshotTest` a 320 × 760 dp con tipografía al 130 % en Hoy, Orar, Biblia, Explorar, Santos, Primeros pasos, Rosario y Recordatorios (Lumen claro): sin recortes ni solapes a la vista, títulos alineados. No lo he repetido en oscuro ni en Sanctuary.

## Lo que no he podido verificar

- **Nada se ha probado en un teléfono real ni en emulador** (este entorno no tiene aceleración para emular). En concreto no he visto funcionar el **desenfoque en vivo** (Android 12+) ni la **lente** (Android 13+). El shader compila en el test y el camino tiene respaldo si falla, pero su aspecto y su rendimiento al hacer scroll están sin comprobar. Es lo primero que conviene mirar en un dispositivo.
- La respuesta háptica y el sonido de clic tampoco se pueden comprobar aquí.
- Rendimiento: cada panel dibuja sombra difusa y la pantalla usa una capa para la máscara de bordes. No lo he medido.

## Fuera de alcance

- Contenido, datos, navegación, `dist/`, firma, versiones, `tools/hermes-preview.gradle`.
- `ScriptureStudyScreen.kt`: sin cambios propios (hereda el material del kit).
- Lectores inmersivos de Biblia y Catecismo: conservan su cabecera tipográfica de «página de libro»; no usan el cabezal con arte.
- Rosario: conserva su disposición de reproductor (selector sobre la pintura, sin título).
- `AppleKit.kt`, `MaterialKit.kt`, `VaticanKit.kt`: siguen en el repo sin que nada los use.
- No he revisado una a una los estilos de texto ajustados a mano dentro de las pantallas (por ejemplo, 36 usos de `.copy(fontWeight = …)`); corregí los visibles junto a cabezales y encabezados de sección.

## Propuestas (requieren decisión del dueño)

1. **Luz litúrgica.** El acento ya no es siempre verde con el tinte predeterminado: en Adviento y Cuaresma será morado, en Navidad y Pascua oro, etc. Si se prefiere el verde fijo, es una línea en `MainActivity`.
2. **Hoy, Santos y Primeros pasos** detienen el scroll por encima del dock (lo exige un test de comportamiento de Hoy: el objetivo debe quedar pulsable sobre el dock). En esas tres el dock de cristal solo desenfoca la luz ambiental, no el contenido. Unificarlo con el resto supone cambiar ese criterio.
3. **Borrar los tres kits muertos** (unas 5.600 líneas).
4. **Añadir a `AGENTS.md`** la regla del cabezal único para que no vuelva a divergir.
5. **Probar en dispositivo** y, si el desenfoque en vivo da problemas en algún teléfono, desactivarlo es un interruptor (`liquidBlurAvailable`).

## Preguntas

- `T-020.md` asigna esta misma rama a «Polish». Si ese agente va a trabajar a la vez, ¿le damos otra rama (por ejemplo `ext/T-021`) partiendo de esta, para que pula sobre el cabezal y el material nuevos y no sobre los antiguos?
