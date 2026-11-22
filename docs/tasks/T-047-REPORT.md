# T-047 · L0 horizontal

Entrega escalonada en `ext/T-047`. **A y B implementadas; C en curso. Este informe no declara cerrado T-047.**

Base inicial: `2a00060`. Se incorporó `origin/CatholicGPT` hasta `2e92076`: el coordinador corrigió dos clases de tests de T-046 y publicó 1.8.29. No hay diferencias de producción en `ui/` entre esas dos bases. Las capturas iniciales siguen siendo una referencia válida.

## A · Ventana, respaldo y contenedores

- `AppComponents.kt`: cinco clases de ventana; manda la altura completa de 480 dp. Compose 1.7.2 no tiene `WindowInfo.containerSize` (comprobado en su API); se usa la alternativa autorizada `WindowMetricsCalculator`, versión fija `androidx.window:window:1.3.0`. `Configuration` únicamente invalida el recuerdo al cambiar la configuración; sus dimensiones no determinan la clase.
- `LumenFoundation.kt`: `appSafeInsets()` toma el máximo de barras y recorte por borde. `ScreenFrame.insets` expone también inicio y final. El scaffold consume los bordes laterales para proteger las pantallas antiguas; los wrappers deben medir el espacio interior real sin volver a sumar ese mismo padding.
- `LumenComponents.kt`: respaldo con arte de `S + 84` (108 dp con S=24), dentro del desplazamiento existente. Sin arte conserva el bloque natural de título. Se mantienen las firmas públicas y las etiquetas del llamante.
- `LumenKit.kt`: riel de teléfono con reserva 80/76, cápsula 64, filas de 56 (72 si el rótulo necesita dos líneas), lente deslizante, desplazamiento y selección alcanzable. `AppHideTabBar` también oculta el riel de `LandscapePhone`; las tabletas conservan su decisión anterior.
- `MainApp.kt`: solo columna de 680 para pantallas sin migrar y rótulos oficiales cortos al ampliar texto en el riel.
- `LumenExtras.kt`: hojas laterales con `wide`, cabecera, cierre, pie opcional y traslado de ese pie a la cabecera al abrir IME. Diálogo con contexto `aside`, contenido desplazable y acciones fijas; confirmación compacta de 420. `AppNotice` anuncia con `liveRegion = Polite` y se coloca sobre el pie/teclado de la columna que lo aloja.
- Las hojas y diálogos componen sus ranuras desde un único punto y usan políticas de `Layout`: cambiar orientación no mueve la llamada al contenido. Los diálogos siguen en una ventana modal nativa (foco, atrás y restauración de la ventana anterior); la hoja también admite velo, cierre y arrastre de 44 dp hacia el borde final. La comprobación de TalkBack y atrás predictivo en un teléfono sigue siendo la de §13.

Los botones antiguos que una pantalla haya escrito dentro de su cuerpo siguen formando parte de ese cuerpo. El nuevo contrato `footer` permite fijarlos y subir la acción con IME sin adivinar callbacks ni editar pantallas; su adopción pertenece a las migraciones correspondientes. En A no se modificó `ui/screens/**` ni `GuiCustomizerModal.kt`; B incorpora la excepción de tarjetas solicitada después por Jose.

## Validación de A

Se ejecutaron ensamblado debug y los controles de AGENTS, más `TabletLayoutTest`, `NarrowLargeTextAuditTest`, `MyPathUiTest` y los nuevos fundamentos. El último resultado por caso suma **116/116 aprobados, 0 omitidos**; [desglose](T-047-shots/validation-A-latest.json).

La primera pasada enfocada tuvo 115 casos y dos fallos en `TabletLayoutTest` (selección de orden y una reentrada del motor de layout de Robolectric). Ambos se repitieron en las cuatro identidades y pasaron: **8/8**. No se cambió ni se eliminó ninguna aserción. [Primera pasada](T-047-shots/validation-A-first-run.json).

`LandscapeFoundationsTest`: **7/7**. Comprueba límites de clase, ventana completa 891×411 con barras/recorte, insets 24/32/48, banda 108 y etiqueta antigua, título sin arte, cinco destinos a 800×360, pie de hoja con IME y acción de diálogo antiguo visible.

`FullAppScreenshotTest.appleLight -Pshots.landscape=true` pasó y produjo las cinco raíces en **891×411 y 800×360** con S24/B16 y gestos laterales 32. Se revisaron las diez imágenes; las láminas `_A_*.png` reúnen ambos tamaños por raíz. La matriz completa de identidades y modos se entrega en C.

Regresión: las cinco raíces a **411×891 y 1280×800**, Lumen y Sanctuary claros, son **20/20 idénticas píxel por píxel**. Se adjuntan base y A al 60 % del PNG nativo. [Medidas y hashes de píxeles originales](T-047-shots/regression-A.json).

Comandos (con el entorno preparado y siempre `flock /tmp/gradle.lock`):

```sh
gradle --no-daemon --max-workers=2 --no-configuration-cache --no-build-cache -I /tmp/t047-runner.gradle :app:assembleDebug :app:testDebugUnitTest \
  --tests com.example.LandscapeFoundationsTest --tests com.example.AppNavigationTest \
  --tests com.example.DesignNavigationTest --tests com.example.RosaryInteractionTest \
  --tests com.example.DesignContrastTest --tests com.example.VaticanNavigationLayoutTest \
  --tests com.example.FontPreferencesTest --tests com.example.FontTypographyTest \
  --tests com.example.FontPickerInteractionTest --tests com.example.ReaderFontChoiceTest \
  --tests com.example.TabletLayoutTest --tests com.example.NarrowLargeTextAuditTest \
  --tests com.example.MyPathUiTest --tests com.example.screenshots.FullAppScreenshotTest \
  -Pshots.only=home,prayers,bible,my_path,more -Pshots.lang=es -Pshots.mode=light
```

El init script local solo fija el hogar escribible, proxy/certificados de este entorno y el registro de resultados. No se incorpora a Git. Los renders horizontal y de regresión se ejecutaron en pasadas separadas; la repetición de los dos casos de tableta se conserva en el desglose.

## Referencia anterior a los cambios

La suite completa de `2a00060` terminó en **915 casos: 885 aprobados, 23 fallidos, 7 omitidos**, antes de compilar A. [Resultados por clase y fallos](T-047-shots/validation-baseline.json).

Cinco fallos fueron de descarga de SDK de Robolectric (9 y 16), no de una aserción de la app. Otros incluyen tests que buscaban «Mi camino» en Explorar: el coordinador corrigió `AppNavigationTest` y `WelcomePersonalizationTest` después de la captura base. El fallo inicial de `MyPathUiTest` no se reprodujo en A (13/13). La suite completa final y la comparación por caso se incorporarán en C, incluyendo los fallos que persistan, sin filtrarlos ni convertirlos en aprobados.

## Decisiones y límites

- Para que los comandos exigidos con `-Pshots.landscape`, `ime`, `cutout`, `navbar` y `opaque` lleguen a la JVM, se amplió la lista de propiedades reenviadas en `app/build.gradle.kts`, además de añadir la dependencia permitida. Es una excepción mínima al límite de edición de ese archivo; no cambia la configuración de producción.
- El arnés despacha `WindowInsetsCompat` reales a los hosts Compose de actividad y diálogo. Usa el gancho `@TestOnly setUseTestInsets` de AndroidX 1.7.2 y fija el suministro en los hosts para que Robolectric no los sustituya después por ceros. El listener de `AndroidComposeView` queda intacto. La prueba exige S24; no acepta una captura validada con S0.
- A 320 dp de alto, una banda fija de 108 ocupa **33,75 %**, incompatible con exigir simultáneamente ≤30 %. En los dos tamaños obligatorios ocupa **26,28 % y 30 %**. Se conserva el valor de banda aprobado; La prueba de C registra también esa altura mínima.
- En una ventana compacta de ancho menor que 376, no cabe una hoja mínima de 360 con márgenes de 8 a ambos lados. El ancho se limita al espacio seguro disponible; La medida de esa excepción se registra en C.
- No se cambian contenido, datos, esquema Room, temas, identificadores, firma ni versiones. Los PNG históricos regenerados por la suite se restituyeron a HEAD; solo se entregan evidencias de T-047.

Documentación consultada: `AGENTS.md`, protocolo, T-047, reglas editoriales, `LANDSCAPE.md` §2–§3/§11/§12 y las fuentes AndroidX de `WindowInsets.android.kt` 1.7.2 (artefacto de fuentes de Google Maven). La rúbrica de nueve criterios y las comprobaciones de giro, paridad y auditoría se completan en C.

## B · Páginas, lectura, listas y ajuste solicitado en Orar

- `AppPageLayout` y `AppSupportingPaneLayout` tienen las firmas de §3.4. Cada ranura mantiene un único punto de composición. La página usa un marcador medido en la lista vertical, sin cambiar el padre de su cabezal; en horizontal coloca panel y contenido mediante `SubcomposeLayout`. El apoyo conserva sus ranuras en una política de `Layout`. El estado de los ejemplos está elevado, con claves explícitas.
- Paneles por `kind`, título medido con `TextMeasurer`, línea base aprobada, título largo hacia arriba y desplazamiento del panel, acciones y atrás dentro del panel. Póster extendido, velo constante inferior 70/85 %, velo oscuro 18 %, recorte al foco de `HeroMeta`, fundido final Lumen, filete Tradicional y marco/lomo Sanctuary. El contenido tiene su protección de barra de estado y desplazamiento independiente.
- El viewport horizontal compartido conserva la medición de ventana completa, pero recorta dibujo y coordenadas accesibles a S/B incluso al desplazar, para que las filas no pasen por debajo de las barras. Las filas seleccionadas exponen `selected`. Segmentos verticales de al menos 44 dp con `Role.Tab` y `selected`; búsqueda compacta; `AppGrid` con fórmula de columnas y `AppFeedColumns` con carriles explícitos. Se corrigió, al mirar las capturas, el texto seleccionado blanco sobre papel claro de Sanctuary.
- Nueva sobrecarga de `AppTwoPane` con selección explícita o por defecto, tira de 96, búsqueda/filtro adhesivos, chips activos y acceso al lector completo. La sobrecarga antigua y `appIsTwoPane()` mantienen las pantallas de tableta de T-043: L0 no activa una migración implícita de Santos, Órdenes ni Devociones.
- `appReadingMeasure()` mide 66 caracteres del Juan 1 ya incluido en la app, con el estilo efectivo. `rememberReadingAnchor()` guarda bloque, índice y fracción. `AppNotice` descuenta el espacio que su host ya ha reservado para barras, teclado o pie, evitando aplicar el inset dos veces.
- Cuatro ejemplos exclusivamente en tests (`LandscapeShowcase.kt`): centro con segmentos/búsqueda, lector con pies, apoyo/contenido y lista-detalle. Usan arte real y las oraciones oficiales existentes, sin redactar contenido ni añadir rutas al producto. [Ejemplos 891](T-047-shots/_B_fixtures_891x411.png) · [800](T-047-shots/_B_fixtures_800x360.png).

**Excepción de alcance autorizada por Jose durante B:** pidió reducir las imágenes del Rosario y la Coronilla en Oraciones horizontal. En `PrayersAndNovenasScreen.kt` se cambia únicamente la llamada de las dos tarjetas destacadas a `AppGuidedPrayerCard`, conservando argumentos, etiquetas, callbacks y proporciones verticales. En altura compacta dibuja una miniatura de **56×72**, superficie de la identidad, título completo y señal de reproducción decorativa; la altura mínima es **96** y crece con la letra. La pareja reserva dos líneas para que los títulos portugueses no desalineen las tarjetas. En vertical/tableta delega al `AppImageCard` anterior con los mismos parámetros.

`CompactPrayerGuidesTest`: **16/16**, cuatro identidades × ES/PT × 100/130 %, a 800×360 con S24/B16. Comprueba ambos accesos completos antes de desplazar, altura igual, texto sin elipsis ni líneas recortadas, objetivos ≥44 y apertura correcta de cada devoción. [Ejemplo Lumen PT130](T-047-shots/l0_guides_lumen_pt_130.png).

Validación de B: ensamblado debug correcto; **137/137 aprobados en una sola ejecución final, 0 omitidos**, incluyendo los controles de AGENTS, las cuatro identidades de tableta, fuentes y `MyPathUiTest`, nueve pruebas de geometría, siete de fundamentos y los renders. [Desglose](T-047-shots/validation-B-latest.json). La primera ejecución tuvo 16 fallos del nuevo test por el indicador engañoso `didOverflowWidth` de Compose: el párrafo conserva el ancho máximo aunque `Text` encoja a su ancho intrínseco (Rosario: caja 124 px, párrafo 356). Se sustituyó esa comprobación por bordes reales de cada línea, altura, final íntegro y ausencia de elipsis; no se reduce la exigencia visual. También hubo un fallo asíncrono de «0 hechos» en Mi camino; su clase completa pasó después **13/13**. C comprueba además el giro y la paridad. La pasada final vuelve a ejecutar ensamblado, AGENTS, las cuatro identidades de tableta, fuentes, Mi camino, fundamentos, geometría y tarjetas, y termina en `BUILD SUCCESSFUL` (7 min 37 s).

El filtro de capturas usado en la pasada conjunta no coincide con los nombres de escenarios de `NarrowLargeTextAuditTest`: sus métodos se ejecutaron sin escenarios. **No se presentan como prueba de la auditoría estrecha completa**. C lo ejecuta sin ese filtro y conserva su resultado real. La misma limitación afecta a la pasada conjunta de A; no a sus pruebas de tableta, fuentes, navegación ni regresión píxel a píxel.

**Cifra incompatible observada:** respetar en Sanctuary la línea del título y el marco debajo de la meta da un cabezal de apoyo más alto que en Lumen; tres segmentos de 44 más búsqueda no caben todos sin desplazar a 360/411. Se conserva la geometría aprobada y el panel entero desplaza. C registra los tamaños reales; no se ocultan segmentos ni se reduce su letra para simular cumplimiento.

## C · Auditoría, giro, paridad y evidencia final

El merge `5008457` incorpora únicamente documentación del coordinador hasta `dc1e000`. T-048 sigue EN COLA. Entregas A `77dba52`, B `d28b7ea`; esta tercera entrega no migra las pantallas L1–L7.

Se corrigieron tres problemas encontrados al probar los wrappers: el último renglón del panel de apoyo sin pie podía entrar en B; las acciones laterales podían empezar bajo S; y la nueva sobrecarga lista-detalle debía centrar su ficha de tableta a 680. El botón de prueba «Volver a Orar» ahora prueba la salida de la ruta, además del cambio de oración. En altura compacta, las acciones del pie de la hoja se alinean al final, también al subir a cabecera con IME; en vertical se conserva su alineación previa. El ejemplo de «Guardar» reserva140dp (112 recortaba la última letra con el padding del botón); el test final exige una línea completa y centro a≤120 del borde final.

El arte lateral se funde de manera continua, también en Tradicional. La decoración que llega bajo la barra de estado se graba por separado del contenido: solo imágenes/velos, sin controles ni semántica. El scaffold mantiene un viewport rectangular seguro para texto, controles y coordenadas accesibles. Se usa `GraphicsLayer` en Canvas acelerado y `Picture` en Canvas de software; los renders nativos calientan la grabación con una pasada antes de capturar. La pasada adicional de `FullAppScreenshotTest` se limita a altura compacta para conservar el procedimiento de regresión de teléfono/tableta. No se ha medido el coste GPU en un teléfono.

En el póster Sanctuary a 800×360, el espacio entre meta y marco pasa de 8 a **4 dp solo cuando hace falta** para respetar el marco mínimo de 96 y el pie fijo. El título no se mueve ni se reduce. Los ocho casos de lector de `LandscapeGeometryTest` tienen desplazamiento de panel **0** al 100 %.

### Pruebas de L0

La pasada principal de C terminó en **144/144 aprobadas, 0 fallos, 0 errores y 0 omitidas**: [desglose por clase](T-047-shots/validation-C-matrix.json). Las pasadas adicionales suman **34/34** aprobadas, sin omitidas: **16/16** mediciones nuevas de geometría y los renders afectados/adicionales. [Desglose](T-047-shots/validation-C-supplementary.json). La suite completa final, que vuelve a ejecutar estas clases sin filtros de clase, se registra abajo.

- `LandscapeAuditTest`: **32 combinaciones** de cuatro identidades, ES/PT, dos tamaños, 100/130 %. Recorre las cinco raíces tanto al inicio como al final; Lumen ES100 añade recorte y navegación lateral a ambos lados. **Cero hallazgos** en los 32 TSV, incluidos `HEADER_TOO_TALL`, `UNDER_INSET` y `SHEET_GEOMETRY`. [TSV completos en JSON](T-047-shots/landscape-audit.json). Las raíces no abren hojas ni IME en esta prueba: esas geometrías se prueban en `LandscapeModalTest`; no se atribuye esa cobertura al recorrido de raíces.
- `LandscapeAuditRulesTest`: casos positivos y negativos de los siete detectores. Las listas `ESSENTIAL_BELOW_FOLD` siguen vacías en L0. `MEASURE` se comprueba sobre las columnas de lectura reales de los ejemplos mediante `TextLayoutResult`, no sobre leyendas o tarjetas del inicio.
- `LandscapeRotationTest`: **12/12**. Cambia las dimensiones de ventana y recrea una actividad de prueba, exigiendo otra instancia; conserva segmento, consulta, selección por defecto/explícita, ficha apilada, hoja abierta, bloque de lectura y fracción, también después de cambiar la letra. Lumen/Sanctuary. El atrás de una ventana modal se envía a su `ComponentDialog`.
- `LandscapeParityTest`: **14/14**, unión de nodos alcanzables a lo largo del desplazamiento en los cuatro ejemplos y Hoy/Orar/Explorar, Lumen/Sanctuary. Se excluyen únicamente las etiquetas propias de la colocación y el título duplicado de la barra. **No es paridad literal completa de Hoy:** su `ExcerptText` anterior a L0 abrevia el mismo pasaje según la anchura. Se conserva la diferencia bruta y se exige el mismo prefijo de al menos 40 caracteres y paridad exacta del resto. [Diferencias sin ocultar](T-047-shots/node-parity.json). Editar Hoy pertenece a T-050.
- `LandscapeStateLintTest`: **2/2**, analiza los slots de las pantallas, ignora comentarios y literales, y demuestra que detecta ejemplos anidados inválidos de `rememberSaveable`.
- `LandscapeLayoutTest`: **14/14** en la matriz principal: ventana completa, riel, banda, hojas, continuidad del cabezal, panel sin pie, avance/salida, ficha de tableta y casos límite de 320/600. `LandscapeGeometryTest`: **16/16**, mide los dos sistemas tipográficos, anchuras, línea base ±1, altura del cabezal y desplazamiento real, y alcanza la búsqueda mediante scroll. [Mediciones](T-047-shots/panel-geometry.json).
- `LandscapeModalTest`: **12/12**, Apariencia real y ejemplos de pie/campo y diálogo de dos columnas, con fondo Orar real, dos identidades y dos tamaños, ±IME. La acción del campo pasa a cabecera y queda visible; el campo escrito también. [Geometría](T-047-shots/modal-geometry.json). El IME es un inset inyectado: las capturas no muestran un teclado del sistema real.
- `LandscapeReadingMeasureTest`: **16/16 con límite declarado**, ancho real y estilo efectivo, EB Garamond/Inter, ES/PT, ambos tamaños. El caso PT/Inter/800 conserva un mínimo de regresión de 56 caracteres y prueba que usa toda la anchura disponible; **no se cuenta como cumplimiento de 60**. [Valores](T-047-shots/reading-measure.json).
- `LandscapeNoticeTest`: **2/2**, anuncio `Polite`, aviso encima del pie/IME y ausencia de descuento doble cuando el host ya se ha reducido.
- `CompactPrayerGuidesTest`: **16/16**, conserva el ajuste autorizado de las tarjetas, sus textos completos y las aperturas correctas.

### Capturas y revisión visual

Todas son PNG al **60 %** de la captura Canvas nativa, fuera del APK. Se entregan y se revisan las cinco raíces y los cuatro ejemplos en 891×411/800×360, las cuatro identidades, claro/oscuro; 130 % ES/PT; material opaco Lumen/Cristal; PT891 Lumen; Apariencia/diálogo ±IME y hoja con campo; recortes y navegación lateral; y las regresiones.

| Evidencia | Láminas |
|---|---|
| Primer vistazo del crítico | [_contact.png](T-047-shots/_contact.png) |
| Raíces, 891 | [claro](T-047-shots/_contact_roots_891x411_claro.png) · [oscuro](T-047-shots/_contact_roots_891x411_oscuro.png) |
| Raíces, 800 | [claro](T-047-shots/_contact_roots_800x360_claro.png) · [oscuro](T-047-shots/_contact_roots_800x360_oscuro.png) |
| Ejemplos, 891 | [claro](T-047-shots/_contact_fixtures_891x411_claro.png) · [oscuro](T-047-shots/_contact_fixtures_891x411_oscuro.png) |
| Ejemplos, 800 | [claro](T-047-shots/_contact_fixtures_800x360_claro.png) · [oscuro](T-047-shots/_contact_fixtures_800x360_oscuro.png) |
| 130 %, raíces | [ES](T-047-shots/_contact_roots_800_s130.png) · [PT](T-047-shots/_contact_roots_800_s130_pt.png) |
| 130 %, ejemplos | [ES](T-047-shots/_contact_fixtures_800_s130.png) · [PT](T-047-shots/_contact_fixtures_800_s130_pt.png) |
| Transparencia reducida | [raíces](T-047-shots/_contact_roots_opaque.png) · [ejemplos](T-047-shots/_contact_fixtures_opaque.png) |
| PT891 | [raíces](T-047-shots/_contact_roots_pt.png) · [ejemplos](T-047-shots/_contact_fixtures_pt.png) |
| Hojas/diálogos | [±IME](T-047-shots/_contact_modals.png) |
| Recorte L/R y navegación R/L | [891](T-047-shots/_contact_insets_891x411.png) · [800](T-047-shots/_contact_insets_800x360.png) |
| Tarjetas Rosario/Coronilla | [ES/PT100/130](T-047-shots/_contact_guides.png) |
| Regresión base/C | [411×891](T-047-shots/_contact_regression_411x891.png) · [1280×800](T-047-shots/_contact_regression_1280x800.png) |

La regresión C vuelve a dar **20/20 idénticas píxel por píxel** a la base. [Hashes y diferencias](T-047-shots/regression-C.json). La comparación fija la misma fecha/zona (noche del 21-nov) en ambos lados para evitar que la fecha diaria se confunda con un cambio de layout; las demás capturas muestran el 22-nov. El [manifiesto](T-047-shots/capture-manifest-C.json) enumera **359 capturas requeridas y suplementarias, sin ausencias**; también se incluyen las combinaciones adicionales capturadas por la auditoría. Se revisaron todas las láminas enlazadas, incluida la corrección final del fundido Tradicional y del marco Sanctuary.

### Cifras incompatibles y propuestas para el coordinador

Se mantienen las cifras aprobadas salvo el ajuste de 4 dp del marco ya explicado; no se reduce la tipografía del usuario ni se oculta contenido.

| Restricción | Medida real | Propuesta, pendiente de diseño |
|---|---|---|
| Banda 108 y ≤30 % a H320 | **33,75 %**. A H360:30 %; H411:26,28 %. | Declarar la excepción compacta o aprobar una banda de 96 para H320. |
| Hoja mínima360 en ventana320, márgenes8 | **304 dp** de anchura disponible. | Mantener ajuste al espacio seguro; mínimo360 solo cuando quepa. |
| Principal≥220 en W600 con cutL32/navR48/riel80 y panelHub≥248 | Wc440; panel248, principal **192 dp**. | Aprobar respaldo de una columna cuando el ancho útil sea insuficiente. |
| Apoyo de centro sin scroll100, tres segmentos44+búsqueda44 | A800, Lumen/Tradicional/Cristal necesitan **5,5 dp** de scroll. Sanctuary: cabezal269,5 y scroll **109,5**; a891: cabezal314 y scroll **103**. Búsqueda y todos los segmentos alcanzables. | Ajustar el presupuesto del centro al migrarlo o aprobar scroll100; mantener el marco Sanctuary. |
| Lector60–75 caracteres con estilo efectivo | PT/Inter19sp a800: **56,49**; usa todo el ancho principal menos márgenes40. ES/Inter800:60,32; PT/Inter891:63,39. | Aprobar una geometría distinta o declarar el límite para esa elección de letra. La tabla del diseño presupone Inter18; la app usa19. |
| Ficha serif≥56, Inter≥45 | PT800: **55,12 /44,33**; el detector admite ±1 carácter por redondeo. | Aclarar esa tolerancia o ampliar el detalle. El lector completo sigue accesible. |
| Diálogo con IME y pie fijo | Superficie **102,5 /125 dp**; viewport de cuerpo **26,5 /49 dp** a360/411. Acción visible; no cabe un campo56 en ese cuerpo. | En L7, usar hoja lateral para formularios o aprobar un presupuesto distinto con IME. El ejemplo es de lectura, no un formulario. |
| Paridad textual literal de Hoy | Un extracto del mismo pasaje cambia de longitud por `ExcerptText`. | Resolver en T-050; raw diff adjunto, resto exacto. |

`AppNotice` dispone el aviso sobre el espacio reservado por su host. Un llamante que coloque a la vez un campo alto, un pie fijo y un aviso debe reservar su espacio o usar un error inline en alturas mínimas; L0 no garantiza que tres bloques incompatibles quepan en cualquier host.

### Autoevaluación de §11

Estas notas se limitan a L0, los ejemplos y el ajuste explícito de Orar. **No constituyen aprobación del crítico ni certifican las áreas todavía sin migrar.** Las comprobaciones físicas de §13 siguen pendientes.

| # | Criterio | Nota | Evidencia y límite |
|---|---|---|---|
| 1 | Uso del espacio | **8** | Banda/paneles y póster medidos; casos H320/W600 y apoyo100 incompatibles arriba. |
| 2 | Jerarquía y ritmo | **9** | `LandscapeGeometryTest`16, línea base175/151,5 ±1, carriles, un primario en cada ejemplo. |
| 3 | Legibilidad | **8** | Lectura opaca y fuente efectiva; límite PT/Inter800, fichas PT y cuerpo mínimo del diálogo documentados. |
| 4 | Ergonomía | **9** | Pies y botones alcanzables, avance a la derecha con centro y≥0,6H, targets; tarjetas16 y layout/modal verdes. La acción de hoja sube con IME conforme al contrato. |
| 5 | Coherencia | **8** | Cuatro identidades×dos modos y regresión20; diferencia bruta del extracto de Hoy, sin editar su pantalla. |
| 6 | Estados | **9 para L0** | Selección explícita/por defecto, ficha apilada, hoja abierta, query, IME y ancla probados. Estados específicos de áreas de §16 se entregan con L1–L7/T-055. |
| 7 | Insets y teclado | **8** | Auditoría de raíces0 y hojas/campo seguros; diálogo con IME sin espacio para campo56. No se declara cumplimiento universal de formularios aún sin migrar. |
| 8 | Giro | **9** | Recreación real de actividad de prueba,12/12, ida/vuelta y letra. Rosario/audio/Confesión pertenecen a sus tramos. |
| 9 | Accesibilidad | **8** | `isTraversalGroup`, orden panel→contenido, `heading`/`paneTitle`/`selected`, diálogo modal y `Polite`; TalkBack, vuelta física del foco y atrás predictivo requieren §13 en dispositivo. |

No se fuerzan notas≥9 para tapar límites que requieren cambiar el diseño aprobado o comprobar el teléfono. Las propuestas anteriores son para la revisión del coordinador; este encargo ordena conservar el diseño y registrar las cifras incompatibles.
