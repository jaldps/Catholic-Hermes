# T-047 · L0 horizontal · C y Revisión 2

Entrega en `ext/T-047`, actualizada desde `origin/CatholicGPT` (`43a3c80`). A y B siguen aceptadas. Esta actualización aplica los nueve cambios de **Revisión 2 (23-nov)**; la revisión e integración corresponden al coordinador. No se publicó APK ni se cambió versión, firma, ids, Room, datos o tema.

**Decisión final: el lector no lleva imagen, tampoco como fondo.** La variante ilustrada y su dictamen anterior quedan sustituidos. Los ejemplos de L0 son composables de test; las pantallas reales se adaptan en L1–L7.

## Respuesta a los nueve puntos

| # | Cambio | Comprobación |
|---|---|---|
| 1 | `lumenPress` y las filas comparten una indicación de foco propia. Anillo de **2 dp** en `textPrimary`, con un halo de lienzo de 1 dp a cada lado; todo queda dentro del control para no recortarse. La fábrica conserva referencias a `State` y el nodo observa sus cambios: enfocar, cambiar tema/forma y transferir el foco no reemplazan el colector. Se pasa la forma real en los wrappers. Sin ripple; se conserva el resorte de presión. Un `BringIntoViewSpec` del scaffold reserva el dock más 8 dp. | `KeyboardFocusTest`: cuatro identidades × claro/oscuro × botón/fila/versículo/borrado de búsqueda (**32 casos**); píxeles del anillo y halo, contraste≥3:1, retintado con foco retenido y transferencia a otro control. `DockKeyboardFocusTest`: la última fila de Explorar recibe foco real y queda sobre el dock, sin empujón de test. Comprueba también el contraste de su anillo real (mínimos: Lumen16,95; Tradicional15,51; Cristal16,95). |
| 2 | `MainActivity` usa la preferencia de la app **o** `!ValueAnimator.areAnimatorsEnabled()` (API≥26), leído de nuevo en `ON_RESUME`. También en la recuperación de copia de seguridad. | `SystemReduceMotionTest`: duración0 llega a `LocalAppDesign.reduceMotion`; cambio del sistema en ambas direcciones al regresar; la preferencia de la app sigue mandando si está activada. |
| 3 | La pintura de `CompactBand` se ve: se eliminan los velos globales0,90/0,94, queda un degradado inferior transparente en0/0,5 → lienzo0,85 y fundido desde0,55. En oscuro se conserva el velo negro18 %. La leyenda lleva una **placa opaca localizada**, que abraza solo su texto. | `CompactBandContrastTest` mide los píxeles pintados detrás de los glifos en las cinco ilustraciones de raíz, tres identidades × dos modos. Sin la placa, algunas leyendas daban1–4:1; la placa evita volver a blanquear toda la banda. El título se mide contra una captura sin glifos; la placa contra sus píxeles adyacentes opacos. |
| 4 | En Sanctuary compacto se reutiliza la rama de título **sin arte**, sobre papel. Su margen inferior es4 dp en compacto (14 en vertical/tableta); elimina10 dp heredados y evita huecos32/29 antes de pestañas. No se fuerza un marco dentro de 84 dp. La tira con ficha reserva la fila de Volver más4 dp (**S+56** antes del título), y omite el antetítulo vacío. Se corrigió una oclusión real de la «O» por el disco de Volver, detectada por el revisor visual. | Comparación nativa con recurso y sin recurso: píxeles idénticos. `LandscapeFolioListHeaderTest`: ocho casos ES/PT,100/130 %, ambos tamaños, con título completo separado del Back real y filtros contiguos. Capturas de raíces y lista-ficha. |
| 5 | En el folio lateral, el título comienza en **S+12 sin atrás / S+52 con atrás**. La meta va después. El marco solo ocupa el espacio que queda tras medir la ranura y los huecos; se oculta si queda menos de64 dp. Los paneles desplazables de página muestran un fundido inferior de24; el ejemplo de apoyo con scroller propio usa el helper equivalente. | Geometría Sanctuary a ambos tamaños: tres segmentos y búsqueda visibles al 100 %, sin scroll del panel. Test positivo/negativo de la señal de más contenido; matriz130 %. |
| 6 | El lienzo opaco del lector empieza en `safe.top`, detrás del viewport; el texto y el título siguen en **S+52 =76 dp**. No se compone arte del lector. | Comparación con/sin recurso; alineación±4; píxeles opacos entreS yS+52 en las cuatro identidades y ambos tamaños. |
| 7 | Cabecera compacta: texto del título a12 dp del borde inferior; cuerpo sin margen superior8. En Apariencia, solo el primer `Label` baja de18 a12 en compacto. | `VERTICAL_GAP` se aplica también a la hoja real: **24 dp** en Lumen/Sanctuary, ambos tamaños. Con el margen propio18 daba30 (12+18); se conserva18 en vertical y en las demás secciones. |
| 8 | El diálogo compacto pinta su propio `scrim`, igual que la hoja, cierra al tocar fuera y fija `dimAmount=0`. Al salir de compacto se restaura el valor anterior de la ventana. | Test de toque exterior, dim y ausencia de acción semántica/foco en el velo (solo recibe el toque); capturas de ambos tamaños e identidades, con/sin IME. |
| 9 | Los cuatro pies de los wrappers tienen el material de su columna. Lienzo opaco en Reader; filete0,5 en Tradicional/Sanctuary; fundido16 en Lumen/Cristal, dibujado antes del recorte del material. | Matriz de centro, lector y apoyo; acciones fijas y alcanzables; comparación visual del borde de lectura. |

**Alcance adicional mínimo:** `GuiCustomizerModal.kt` no estaba en la lista original de archivos. Fue necesario ajustar solo su primer margen de etiqueta para resolver el punto7 sin solapar cuerpo/cabecera ni moverlos con offsets negativos. También se especifica la forma circular del foco de sus muestras de color (punto1). `MoreScreen.EntryRow` tenía un clic propio sin indicación: ahora usa el mismo anillo y su forma real; no cambia su disposición ni navegación. Esta segunda excepción mínima permite comprobar el foco **real** de Créditos, además de su posición sobre el dock. El borrado de búsqueda y los versículos clicables reciben el mismo tratamiento en los componentes compartidos. El resto del diseño y el vertical se conservan. El revisor independiente confirmó la necesidad de este ajuste localizado.

## Mi camino y estabilidad

`MyPathUiTest` comprobaba el texto al aparecer la fila, cuando Room/Flow todavía podía estar emitiendo el resumen inicial. `assertRowText` espera el mismo contenido esperado (máximo25 s) antes de ejecutar **la misma aserción**. No se modificó `MyPathScreen` ni se aceptan valores alternativos. La clase pasó **13/13** en la suite general, además del control anterior.

`AppNavigationTest` esperaba «Matthew» tras ver una tarjeta independiente de continuar leyendo, antes de que el directorio completara sus recuentos por IO. Ahora espera primero el libro inicial real y confirma el canon seleccionado; después conserva el límite de15 s y la aserción de «Matthew». Pasó **5/5**, con todas las navegaciones y comprobaciones de fuentes originales. El filtro no se simula. El arreglo de las capturas de Explorar para despejar el dock llegó también del coordinador (`109b77e`); se conserva su implementación al fusionar `43a3c80`, sin duplicarla.

Los wrappers conservan una llamada por ranura, estado elevado, selección explícita/por defecto y ancla de lectura. Se mantiene el lint de `rememberSaveable`. El test de paridad fija la fuente bíblica incluida solo dentro de la prueba, evitando comparar traducciones distintas si llega una respuesta de red entre orientaciones. La diferencia bruta de longitud del extracto de Hoy sigue declarada para T-050; no se oculta contenido.

## Validación

**El dueño autorizó continuar con la entrega sin esperar las comprobaciones restantes** («asume que todas las pruebas fueron satisfactorias. Continúa con el proximo paso»). Se detuvieron las reejecuciones pendientes y se conservan a continuación los resultados observados. [Decisión y pendientes](T-047-shots/validation-R2-delivery-decision.json).

- **Compilación:** `:app:assembleDebug` → `BUILD SUCCESSFUL`; APK de verificación sin publicar: [metadatos](T-047-shots/build-R2.json).
- **Matriz L0 de base:** **167/167**, y **14/14** suplementos de ES/PT130 %, opacidad y PT. La matriz de base precede al último ajuste de separación de la tira; ese cambio tiene su perfil específico posterior: **31/31** (8 casos nuevos de cabecera,21 layouts y2 renders de Sanctuary). [Matriz](T-047-shots/validation-R2-matrix.json), [perfil de cabecera](T-047-shots/validation-R2-folio.json).
- **Suite general final sin filtros:** **1157 aprobados, 7 omitidos opt-in, 2 fallos de inicialización, 0 errores**, 1166 casos registrados; `BUILD FAILED` en 2 h 7 min 10 s. Los dos fallos son `ExampleRobolectricTest.classMethod` y `LiquidGlassTest.classMethod`: Maven devolvió **HTTP429** al obtener sus runtimes y esas clases no arrancaron. No se observaron fallos de aserciones de la app. [Resultado bruto completo](T-047-shots/validation-R2-full-final.json). La recuperación de las clases se omitió por la instrucción final del dueño.
- **Primera suite general:**1172 casos, 1146 aprobados, 19 fallidos, 7 omitidos. Los 16 fallos de disco/runtime se resolvieron en una reejecución de esas clases; se corrigió la espera de navegación y se incorporó la corrección de Explorar del coordinador. Se conserva el [resultado inicial](T-047-shots/validation-R2-full-raw.json) y la [primera reejecución](T-047-shots/validation-R2-recheck-raw.json).
- **Regresión visual actual:**16/20 imágenes idénticas en píxeles nativos. Las cuatro de Hoy usan por error fecha/horario diferentes: una zona IANA inexistente fue sustituida silenciosamente por GMT. Se marcó ese ensayo como **inválido**, se añadió un guard al renderer y se preparó la repetición con un offset GMT válido; esa repetición se omitió por la instrucción del dueño. No se afirma 20/20 en esta entrega. [Registro](T-047-shots/regression-R2.json).

| Comprobación | Casos aprobados observados en la suite final |
|---|---|
| `LandscapeFolioListHeaderTest` | 8/8 |
| `LandscapeLayoutTest` | 21/21 |
| `LandscapeGeometryTest` | 16/16 |
| `LandscapeAuditTest` | 32/32 |
| `LandscapeAuditRulesTest` | 1/1 |
| `LandscapeRotationTest` | 12/12 |
| `LandscapeStateLintTest` | 2/2 |
| `LandscapeParityTest` | 14/14 |
| `LandscapeModalTest` | 12/12 |
| `LandscapeReadingMeasureTest` | 16/16 |
| `CompactPrayerGuidesTest` | 16/16 |
| `KeyboardFocusTest` | 32/32 |
| `DockKeyboardFocusTest` | 3/3 |
| `SystemReduceMotionTest` | 3/3 |
| `CompactBandContrastTest` | 6/6 |
| `AppNavigationTest` | 5/5 |
| `DesignNavigationTest` | 8/8 |
| `RosaryInteractionTest` | 10/10 |
| `DesignContrastTest` | 1/1 |
| `TabletLayoutTest` | 44/44 |
| `NarrowLargeTextAuditTest` | 8/8 |
| `MyPathUiTest` | 13/13 |
| Fuentes: `FontPickerInteractionTest`, `FontPreferencesTest`, `FontTypographyTest`, `ReaderFontChoiceTest` | 16/16 |

El auditor estrecho cubrió 8 casos × 50 escenarios =400: [cobertura real](T-047-shots/narrow-coverage-R2.json). No se atribuye esta cobertura a los filtros vacíos de A/B. El primer ensayo del nuevo matcher de canon apuntó al estado del hijo en lugar del contenedor; se corrigió para comprobar el texto seleccionado, manteniendo las aserciones anteriores.


Los filtros y escenarios de cada ejecución se conservan en su evidencia. La suite general actual se ejecuta sin filtros de clase ni de capturas. La cobertura del auditor estrecho se comprueba a partir de sus registros; A/B habían pasado sus métodos con un filtro que no recorría escenarios, y no se les atribuye cobertura retrospectiva. Las comprobaciones reales de C sustituyen esa limitación.

`VaticanNavigationLayoutTest`, citado en el AGENTS histórico, no existe en este checkout. No se le atribuye una ejecución. No se han cambiado aserciones para ocultar regresiones.

## Capturas y revisión

PNG al **60 %** del Canvas nativo, fuera del APK, con S24/B16/gestos32 en horizontal. La matriz incluye891×411 y800×360; cuatro identidades; claro/oscuro;130 % ES/PT; material opaco; PT; recorte32 a ambos lados y navegación48 a ambos lados; hojas/diálogo ±IME; tarjetas compactas; y regresión vertical/tableta. El teclado es un inset inyectado, no una imagen de un teclado físico.

| Evidencia | Láminas |
|---|---|
| Entrada y raíces | [Contacto](T-047-shots/_contact.png); [891 oscuro](T-047-shots/_contact_roots_891x411_oscuro.png); [800 claro](T-047-shots/_contact_roots_800x360_claro.png), [oscuro](T-047-shots/_contact_roots_800x360_oscuro.png) |
| Wrappers rehechos | [B891](T-047-shots/_B_fixtures_891x411.png), [B800](T-047-shots/_B_fixtures_800x360.png); [891 oscuro](T-047-shots/_contact_fixtures_891x411_oscuro.png), [800 oscuro](T-047-shots/_contact_fixtures_800x360_oscuro.png) |
| Tira sin arte y Volver | [ES/PT100/130 %](T-047-shots/_contact_R2_folio.png) |
| Lector sin arte | [100 %](T-047-shots/_contact_reader_review_100.png), [130 % ES](T-047-shots/_contact_reader_review_130.png), [130 % PT](T-047-shots/_contact_reader_review_130_pt.png) |
| Texto130 % | Raíces [ES](T-047-shots/_contact_roots_800_s130.png), [PT](T-047-shots/_contact_roots_800_s130_pt.png); ejemplos [ES](T-047-shots/_contact_fixtures_800_s130.png), [PT](T-047-shots/_contact_fixtures_800_s130_pt.png) |
| Opaco / portugués | Raíces [opacas](T-047-shots/_contact_roots_opaque.png), [PT](T-047-shots/_contact_roots_pt.png); ejemplos [opacos](T-047-shots/_contact_fixtures_opaque.png), [PT](T-047-shots/_contact_fixtures_pt.png) |
| Insets | [891](T-047-shots/_contact_insets_891x411.png), [800](T-047-shots/_contact_insets_800x360.png), con recorte y navegación a ambos lados |
| Hojas y diálogo | [Matriz ±IME](T-047-shots/_contact_modals.png) |
| Identidad y guías | [Tradicional](T-047-shots/_contact_classic_final.png), [Sanctuary](T-047-shots/_contact_sanctuary_final.png), [tarjetas de Orar](T-047-shots/_contact_guides.png) |
| Foco y contraste | [32 controles](T-047-shots/_contact_R2_focus.png), [Créditos sobre el dock](T-047-shots/_contact_R2_dock.png); bandas [claras](T-047-shots/_contact_R2_contrast_light.png), [oscuras](T-047-shots/_contact_R2_contrast_dark.png) |
| Regresión | [Vertical](T-047-shots/_contact_regression_411x891.png), [tableta](T-047-shots/_contact_regression_1280x800.png), con base y C lado a lado |


El revisor independiente volvió a revisar código y capturas después de corregir tres omisiones de foco (Créditos, borrar búsqueda y versículos). **Aprobó visualmente los nueve puntos**, incluida la protección frente al dock, sin defectos pendientes en ese alcance. En el último pase detectó que Volver mordía la «O» de «Oraciones» en lista-ficha; la nueva regresión de ocho casos y las capturas cerraron esa oclusión. Su dictamen no sustituye la suite ni las pruebas físicas: [dictamen actual](T-047-shots/visual-review-R2.json).

## Medidas y decisiones del coordinador

| Caso | Medida / resultado | Estado |
|---|---|---|
| Banda con H320 |108/320 = **33,75 %**. Con H360:30 %; con H411:26,28 %. | Aceptado para el mínimo absoluto320. El30 % rige en360/411. |
| Lector PT/Inter19 a800 | **56,49 caracteres**, usando todo el ancho disponible menos márgenes40. | Aceptado: mínimo56 con sans. Serif conserva60–75. El test ya aplica el umbral aprobado, sin `KNOWN_LIMIT` artificial. |
| W600 con cutL32/navR48/riel80 | Wc440; panel248; principal **192 dp**. | Aceptado como límite inferior; una columna. |
| Hoja en W320 con márgenes8 | **304 dp**, menor que el mínimo nominal360. | Aceptado en la revisión de A/B; se ajusta al ancho seguro real. |
| Tira sin arte de lista-ficha Sanctuary | **110 dp al 100 %,124 dp al 130 %**, con inicio del títuloS+56 y separación real de 6 dp frente a Volver. | Excepción a96 de la tira de arte: sin pintura, reserva la fila52+4 y conserva la línea completa del título; aprobada visualmente. |
| Folio de centro Sanctuary | Ancho293,5/260,5 dp a891/800; cabezal211/112 dp, línea del título74,5; `panel_scroll_max=0` en ambos tamaños. Los tres segmentos y la búsqueda están dentro del panel. | Resuelto: la ranura medida tiene prioridad sobre el marco. |
| Ficha PT800 | Serif **55,12**, Inter **44,33** caracteres. | La tolerancia del detector es±1 carácter; existe apertura del lector completo. |
| Diálogo con IME | Los viewports medidos con IME son **78,5/34,5 dp** a360 y **101/57 dp** a411 (apoyo/principal); las acciones permanecen visibles. La superficie mide **720×102,5** y **720×125 dp**, respectivamente. | No se declara que quepa un campo56. Los formularios reales se adaptan en L7, con hoja lateral cuando corresponda. |
| Extracto de Hoy | El mismo pasaje usa una longitud distinta según el ancho. | Diff bruto declarado, pendiente de T-050. |

La auditoría conserva **98 avisos**, con el desglose {'VERTICAL_GAP': 85, 'OBSCURED': 13}: [filas completas](T-047-shots/landscape-audit-R2.json). No se han suprimido ni cambiado los umbrales. Los huecos propios del wrapper de Sanctuary quedaron resueltos. Los avisos restantes provienen del espaciado de Explorar y, cuando aparecen, del contenedor clicable de Liturgia en Hoy (el heading heredado mide el contenedor completo).

Para L6 se propone reducir7–10 dp el primer margen horizontal de Explorar y volver a medir. En T-050 corresponde revisar el espaciado de las tarjetas de Hoy y la extensión común de su extracto. No se migran esas pantallas en L0. Esos límites explican las notas globales inferiores a9; la aprobación visual se limita a los nueve puntos solicitados.

Medidas: [paneles](T-047-shots/panel-geometry-R2.json), [modales](T-047-shots/modal-geometry-R2.json), [lectura](T-047-shots/reading-measure-R2.json), [tira sin arte y Volver](T-047-shots/folio-list-header-R2.json), [contraste real](T-047-shots/band-contrast-R2.json), [foco y dock](T-047-shots/dock-focus-R2.json), [paridad bruta](T-047-shots/node-parity-R2.json).

## Autoevaluación de §11

Evaluación limitada a **L0 y los puntos de Revisión2**, no a las pantallas pendientes. Las excepciones numéricas aceptadas se aplican explícitamente. Las comprobaciones físicas de §13 siguen pendientes; no se presenta esto como certificación universal de accesibilidad, rendimiento ni calidad de toda la app.

| # | Criterio | Nota | Evidencia / límite |
|---|---|---|---|
|1|Uso del espacio y P9|**8 global / 9 en wrappers revisados**|Reader sin arte, texto/títuloS+52, controles44/48; folio adapta el marco a sus controles; hojas24. Queda el hueco de Explorar producido por su propio espaciado de pantalla (L6), declarado abajo. Se aplican las excepciones numéricas aceptadas.|
|2|Jerarquía y ritmo|**9**|Geometría de cuatro identidades; línea0,46H−14 donde corresponde y excepciones explícitas Reader/Sanctuary; un primario por ejemplo.|
|3|Legibilidad|**9**|Lectura opaca desdeS, fuentes efectivas, medida serif60–75/sans≥56 según revisión; contraste nativo mínimo de bandas: título4,68:1 y leyenda5,17:1. Las fichas ofrecen abrir lector completo.|
|4|Ergonomía|**9**|Objetivos≥44; avance48 cerca del borde seguro y en la franja inferior; acciones fijas y traslado del pie al cabezal con IME.|
|5|Coherencia|**8 global / 9 en wrappers**|Cuatro identidades y modos; regresión y paridad de ejemplos. El extracto de Hoy cambia de longitud según el ancho; se entrega su diff bruto para T-050, sin atribuir paridad literal a esa raíz.|
|6|Estados de L0|**9**|Selección explícita/por defecto, ficha apilada, hoja abierta, consulta escrita, aviso y desplazamiento. Los estados de las áreas se migran en sus encargos.|
|7|Insets y teclado|**9**|ArnésS24/B16/gestos32; recorte32 y navegación48 a ambos lados; hojas/diálogo con IME0,56H y acciones visibles. No certifica que un formulario56 quepa en34,5.|
|8|Giro|**9**|12 casos de recreación real; segmento, consulta, selección, ficha, hoja, texto y ancla conservados; lint de estado.|
|9|Accesibilidad de L0|**9**|Foco nativo visible, forma real, retintado y transferencia; Créditos sobre el dock; movimiento del sistema al reanudar; grupos de recorrido y semántica. Prueba física de TalkBack pendiente (§13).|


## Archivos

La producción de A/B/C se concentra en `AppComponents.kt`, `LiquidGlass.kt`, `LumenComponents.kt`, `LumenExtras.kt`, `LumenFoundation.kt`, `LumenKit.kt`, `MainApp.kt` y la dependencia fijada de ventana en `app/build.gradle.kts`. El ajuste compacto de tarjetas en `PrayersAndNovenasScreen.kt` fue autorizado en B. R2 añade el respeto al movimiento del sistema en `MainActivity.kt` y las dos excepciones localizadas ya justificadas: `GuiCustomizerModal.kt` y `MoreScreen.kt`.

Se añaden cinco clases de test para los puntos HIGH: `LandscapeFolioListHeaderTest`, `KeyboardFocusTest`, `DockKeyboardFocusTest`, `SystemReduceMotionTest` y `CompactBandContrastTest`. Los tests de L0 cubren composición, geometría, capturas, auditoría, giro, paridad, medida, estado y modales. `MyPathUiTest` y `AppNavigationTest` conservan sus aserciones y esperan sus datos asíncronos reales. Las capturas frías del auditor esperan el mismo tiempo de decodificación que el renderer general. La relación exacta de fuentes y hashes está en [procedencia](T-047-shots/validation-R2-provenance.json); las imágenes de T-055 y sus cambios vienen de `CatholicGPT`, no son una reapertura de ese encargo.

## Historial, fuentes y entrega

A (`77dba52`) y B (`d28b7ea`) entregaron ventana/insets, respaldo, riel, contenedores, wrappers, medida y ancla, con116/116 y137/137 respectivamente. C anterior (`ef5c37b`) quedó en CAMBIOS. Su arte del lector y su aprobación visual corresponden a otro estado y ya no son la aceptación actual.

Se consultaron `AGENTS.md`, el README de encargos, `T-047.md`, `T-047-REVIEW.md` (incluido el añadido del dock), `docs/design/LANDSCAPE.md` (§2–§3,§5.1,§11–§12), las reglas editoriales y las revisiones/validación del repositorio. Se aplicó la [skill local de accesibilidad](../../.claude/skills/better-accessibility/SKILL.md) a foco, movimiento y obstrucción, adaptándola a Compose. Los ejemplos usan textos e ilustraciones existentes; no se compusieron oraciones ni se añadieron datos religiosos.

No hay comparación web en vivo, prueba de instalación física ni medición de GPU/TalkBack en un teléfono. No se escribe en `CatholicGPT` ni `main`. El cierre e integración son del coordinador. T-048 se inicia solo si el README actualizado lo marca ABIERTO.
