# T-045 — Auditoría a 320 dp con texto al 130 %

Alcance: toda la app a 320 dp de ancho (`w320dp-h640dp-xhdpi`) con el tamaño de letra de la app al 130 %, en español y portugués, en las cuatro identidades (Lumen, Tradicional, Cristal, Sanctuary). Resultado: **0 hallazgos inesperados en las 8 combinaciones**, con una lista de permitidos explícita (más abajo). No se ha hecho commit ni push.

## Método

`app/src/test/java/com/example/NarrowLargeTextAuditTest.kt` recorre el árbol de semántica. Cubre las 23 `AppDestination` y 26 subpantallas: capítulo de la Biblia, Misa, lección, oración, categoría de oración, novena (índice, ficha y día), Rosario (misterios y opciones), santo, orden, oficio, devoción, doctrina, pregunta, artículo del Catecismo, Compendio, plan, Confesión (diálogo inicial, resumen, examen), búsqueda con resultados, hoja de recordatorios, hojas de Ajustes (Apariencia, Fuente, Tu camino, Copia de seguridad, Calendario) y el diálogo de bienvenida.

Cada contenedor desplazable se recorre de arriba abajo y se muestrea en cada paso, y también al final del desplazamiento. Detecta:

- **OVERFLOW**: texto que sobresale de su caja.
- **ELLIPSIS**: texto cortado con puntos suspensivos.
- **MIDWORD**: palabra partida por la mitad (letra a letra).
- **CLIPPED**: texto o botón recortado por un padre o por la ventana.
- **SMALL_TARGET**: clicable de menos de 44 dp.
- **BEHIND_DOCK**: contenido bajo la barra de pestañas al llegar al final.
- **UNREACHED**: el escenario no llegó a la pantalla (fallo de cobertura).

Se descartan los ítems perezosos no colocados (sin posición real). Un recorte solo se cuenta si persiste tras reposar el layout, y no cuenta el recorte normal de un ítem que cruza el borde del viewport.

Ejecución: `gw.sh :app:testDebugUnitTest --tests com.example.NarrowLargeTextAuditTest`. Se puede acotar con `-Pshots.only=<escenarios>`, `-Pshots.identity=` y `-Pshots.lang=`. Con `-Paudit.capture=true|all` guarda PNG en `app/build/screenshots/audit`. La propiedad `audit.capture` se reenvía desde `app/build.gradle.kts`.

Umbral de objetivo táctil: 44 dp, el estándar de la app (confirmado por coordinación).

## Cifras

| | Antes (código en HEAD) | Después |
|---|---|---|
| Hallazgos inesperados (8 combinaciones, sin duplicados por pantalla) | **2 393** (1 918 ELLIPSIS, 435 MIDWORD, 40 SMALL_TARGET) | **0** |
| Entradas permitidas (observadas) | n/a | 325 (ver lista) |

Notas sobre las cifras:

- «Antes» se midió con `app/src/main` apartado con `git stash`, con el mismo detector.
- De los 2 393, 108 son el título compacto de la barra superior. Ahora lleva `testTag("app_bar_title")` y está en la lista de permitidos.
- Durante las correcciones el detector encontró además tres regresiones introducidas por los propios cambios, y las tres están corregidas:
  - la ficha de imagen recortaba su título;
  - el monograma «O.F.M.Conv.» desbordaba;
  - el botón de compartir de Hoy quedaba en 43 dp.

## Hallazgos y correcciones (antes → corrección → después)

Los recuentos son sobre las 8 combinaciones. «Después» es 0 salvo que se indique.

| Pantalla / componente | Problema (antes) | Corrección |
|---|---|---|
| Novenas (fichas 2 por fila, 106 dp) | 384 ELLIPSIS + 52 MIDWORD («Misericór/dia») | `novenaCardsPerRow()`: una ficha por fila con texto grande o <340 dp. La ficha crece (la proporción es un mínimo). |
| Devociones (`devotion_card_*`) | 280 ELLIPSIS + 68 MIDWORD: el año dejaba una columna de 46–97 dp | `LeadRow`: con texto grande el valor final pasa a la segunda línea y se quitan los topes de líneas. |
| Noticias (lista y destacada) | 248 + 8 ELLIPSIS (columna de 158 dp junto a la miniatura); palabra larga partida | `NewsRow` apila: titular a todo el ancho y la imagen como franja debajo. Sin tope de líneas. |
| Órdenes y oficios | 164 ELLIPSIS; 16 con «Anterior · …» (pie de secuencia) | `LeadRow` y `SequenceFooter` sin tope. El monograma de la placa se ajusta al ancho. |
| Compendio del Catecismo | 152 ELLIPSIS + 8 MIDWORD; los rangos «101–135» se partían en 64 dp | `CatRowShell`: el rango va encima del título con texto grande; el valor final pasa a la segunda línea. |
| Libros de la Biblia (`book_item_*`) | 160 MIDWORD («Deuteronomio» en 97 dp) | `AppGroupedRow` apila el valor («5 capítulos») en la segunda línea. |
| Biblia, fila «Libros» + filtro | MIDWORD en 77 dp | Con texto grande el filtro baja bajo el encabezado. |
| Explorar, tarjetas de Estudio | 48 MIDWORD («Catecism/o») + 216 ELLIPSIS | `StudyCard`: con texto grande el texto ocupa todo el ancho y la pintura pasa a franja inferior. |
| Preguntas (`qa_*`, fuentes, continuar) | 104 ELLIPSIS | `LeadRow` / `appMaxLines`. |
| Historia (épocas), preceptos, Didaché, hábitos | 76 + 64 + 16 + 12 ELLIPSIS | `appMaxLines`. |
| Recordatorios: permisos y título | 32 MIDWORD («Notificaçõ/es»); título «Recordatorios» en 280 dp; `open_reminders` | `PermissionRow` coloca la acción bajo el texto. `AppHeaderFrame`: si una palabra del título no cabe en `largeTitle`, usa `title1` (sigue escalado). `AppListRow` apila el valor. |
| Hoy y diálogo de bienvenida | 24 + 12 + 12 + 32 ELLIPSIS (calendario litúrgico, misa, lecturas, versículo); MIDWORD en los accesos | `CardHeader` apila la referencia. `AppGrid` pasa a 2 columnas con texto grande. `AppImageCard` crece en vez de recortar. |
| Orar: categorías | 12 MIDWORD («Intenções», «Proteção») | `AppGrid` a 2 columnas (el mismo cambio sirve para Hoy y Vida interior). |
| Hojas Fuente y Calendario | 8 MIDWORD (título en 55 dp entre dos reservas laterales) | `LumenSheetHeader`: con texto grande el botón va en su propia fila y el título usa todo el ancho. |
| Selectores de voz, de lección y de misal | 14 ELLIPSIS | Sin tope de líneas. |
| `rosary_mystery_picker` | 24 SMALL_TARGET (40 dp) | Zona táctil de 48 dp (pulsación y etiqueta en el contenedor); la píldora conserva su aspecto. |
| `bible_active_edition` | 8 SMALL_TARGET (24 dp) | `clickable` y `testTag` antes del dimensionado: zona de 48 dp, sin cambio visual. |
| `home_daily_question_share` | 8 SMALL_TARGET (43 dp) | `FlowRow`: el botón baja bajo la cápsula en vez de comprimirse. |

## Cambios en los envoltorios compartidos

- `ui/theme/Theme.kt`: nuevo `LocalAppFontScale`.
- `ui/design/AppComponents.kt`: `appTextScale()`, `appLargeText()` (≥ 125 %) y `appMaxLines(n)`. En 100 % no cambian nada.
- ~80 `maxLines = N` en pantallas, componentes y diseño pasan a `appMaxLines(N)`. Se excluyen Rosario, los medidores de texto, los campos de texto y los marcadores de posición.
- `LumenComponents.kt`: `AppImageCard` (proporción mínima, redondeo idéntico a `aspectRatio`), `AppGrid` (2 columnas), `AppHeaderFrame` (rango del título).
- `LumenExtras.kt`: `AppGroupedRow`.
- `LumenKit.kt`: `AppListRow` (`Row`) y `testTag("app_bar_title")` en el título de la barra.
- `FontPickerDialog.kt`: `LumenSheetHeader`.
- Cambios por pantalla (específicos): `MoreScreen` (StudyCard), `DoctrineParts` (`LeadRow`, `SequenceFooter`, `EmblemPlate`), `DoctrineNews`, `CatechismScreen` (`CatRowShell`, `CatRow`), `PrayersAndNovenasScreen`, `HomeScreen`, `RemindersScreen`, `BibleScreen`, `RosaryScreen`.

## Lista de permitidos (en `NarrowLargeTextAuditTest.allowList`)

| Tipo | Patrón | Motivo | Entradas |
|---|---|---|---|
| ELLIPSIS | `app_bar_title` | El título compacto de la barra solo repite el título grande de la página, que se muestra completo y se lee como encabezado. | 108 |
| ELLIPSIS | Marcadores de posición de búsqueda («Buscar…», «Pesquisar…», «Cidade, bairro…», «Nome, país…») | Son una pista de una línea que desaparece al escribir; el campo conserva su nombre accesible completo. | 48 |
| SMALL_TARGET | `fidelity_day_N` | Celdas del calendario mensual de 36×46 dp: siete columnas deben caber en 320 dp y la altura cumple 44 dp. | 153 |
| MIDWORD | `prayer_row_nicene_creed` | «Constantinopolitano» (19 letras) es más ancha que la columna de 220 dp al 130 %; una palabra no se puede dividir y el título completo está en la página. | 8 |
| MIDWORD | `order_premonstratenses-opraem` | «Premonstratenses» (16 letras) no cabe en los 162 dp que dejan emblema y chevrón; el resto de palabras sí se ajusta y la página repite el nombre completo. | 8 |

Total observado en la última ejecución: 325 entradas (108 + 48 + 153 + 16).

## Verificación

- `NarrowLargeTextAuditTest`: 8 instancias (4 identidades × ES/PT), todas en verde, sin hallazgos inesperados.
- Suite unitaria completa (`:app:testDebugUnitTest`): 929 tests, 1 fallo conocido (`ExampleRobolectricTest` «read string from context», por el script de init de preview), 7 omitidos. Los tests pedidos pasan: `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `FontPreferencesTest`, `TabletLayoutTest`, `SearchUiTest`, `MyPathUiTest`, `RosaryInteractionTest`.
- `VaticanNavigationLayoutTest`: la clase **no existe** en este árbol (solo `MassVaticanSourceTest`, `VaticanGospelTest`, `VaticanNewsTest` y `VaticanReadingsCacheTest`, todos en verde). No se pudo ejecutar.
- 411 dp / 100 %: 40 capturas (10 pantallas × 4 identidades, ES, modo claro) antes y después. 36 son idénticas píxel a píxel. Las 4 del Rosario difieren en ≤ 59/255 en el sombreado de la píldora de misterios, imperceptible. Esto sucedió tras corregir el redondeo de `AppImageCard`, que movía 1 px las tarjetas de Orar.
- Se revirtieron con `git checkout` las capturas regeneradas por la suite en `docs/tasks/*-shots` ajenas a T-045.
- `:app:assembleDebug`: correcto.

## Evidencia

`docs/tasks/T-045-shots/` contiene 21 PNG, cada uno con «antes | después» lado a lado, a 320 dp / 130 % en portugués:

- 11 casos peores en Lumen:
  - novenas, compendio, Explorar (estudio), lembretes, devoções, notícias, livros da Bíblia, folha Fonte, boas-vindas, ordens, perguntas.
- 10 pantallas completas en varias identidades:
  - Hoy ×4, lembretes, orar, catecismo, órdenes, devociones.

## Lo que no se pudo arreglar o queda como limitación

- Las dos palabras indivisibles (Constantinopolitano, Premonstratenses) siguen partiéndose. Solucionarlo exigiría guionado automático (`Hyphens.Auto`), que cambiaría el aspecto a 100 % y no se puede verificar en Robolectric.
- Los marcadores de posición de búsqueda se siguen cortando (permitidos, ver arriba).
- Las pestañas segmentadas anchas desplazan horizontalmente en lugar de envolver. Son alcanzables, pero la última puede quedar parcialmente fuera hasta desplazar; el detector no lo marca.
- El audit mide solo el tamaño de letra de la app al 130 %, no se combina con la escala de fuente del sistema. Modo claro únicamente (el color no afecta al layout). No hay verificación en dispositivo real. El contenido en línea (noticias, lecturas de la Misa) depende de la caché/red del entorno de pruebas.
- Los objetivos de 44 dp se mantienen (estándar de la app); pasar a 48 dp en todo cambiaría el aspecto a 411 dp / 100 %.
- El detector descarta ítems que cruzan el borde del viewport y los no colocados; esos casos se evalúan al estar completamente visibles.
