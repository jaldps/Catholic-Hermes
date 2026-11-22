# T-047 · L0 horizontal

Entrega escalonada en `ext/T-047`. **A implementada; B y C pendientes. Este informe no declara cerrado T-047.**

Base inicial: `2a00060`. Se incorporó `origin/CatholicGPT` hasta `2e92076`: el coordinador corrigió dos clases de tests de T-046 y publicó 1.8.29. No hay diferencias de producción en `ui/` entre esas dos bases. Las capturas iniciales siguen siendo una referencia válida.

## A · Ventana, respaldo y contenedores

- `AppComponents.kt`: cinco clases de ventana; manda la altura completa de 480 dp. Compose 1.7.2 no tiene `WindowInfo.containerSize` (comprobado en su API); se usa la alternativa autorizada `WindowMetricsCalculator`, versión fija `androidx.window:window:1.3.0`. `Configuration` únicamente invalida el recuerdo al cambiar la configuración; sus dimensiones no determinan la clase.
- `LumenFoundation.kt`: `appSafeInsets()` toma el máximo de barras y recorte por borde. `ScreenFrame.insets` expone también inicio y final. El scaffold consume los bordes laterales para proteger las pantallas antiguas; los wrappers deben medir el espacio interior real sin volver a sumar ese mismo padding.
- `LumenComponents.kt`: respaldo con arte de `S + 84` (108 dp con S=24), dentro del desplazamiento existente. Sin arte conserva el bloque natural de título. Se mantienen las firmas públicas y las etiquetas del llamante.
- `LumenKit.kt`: riel de teléfono con reserva 80/76, cápsula 64, filas de 56 (72 si el rótulo necesita dos líneas), lente deslizante, desplazamiento y selección alcanzable. `AppHideTabBar` también oculta el riel de `LandscapePhone`; las tabletas conservan su decisión anterior.
- `MainApp.kt`: solo columna de 680 para pantallas sin migrar y rótulos oficiales cortos al ampliar texto en el riel.
- `LumenExtras.kt`: hojas laterales con `wide`, cabecera, cierre, pie opcional y traslado de ese pie a la cabecera al abrir IME. Diálogo con contexto `aside`, contenido desplazable y acciones fijas; confirmación compacta de 420. `AppNotice` anuncia con `liveRegion = Polite` y se coloca sobre el pie/teclado de la columna que lo aloja.
- Las hojas y diálogos componen sus ranuras desde un único punto y usan políticas de `Layout`: cambiar orientación no mueve la llamada al contenido. Los diálogos siguen en una ventana modal nativa (foco, atrás y restauración de la ventana anterior); la hoja también admite velo, cierre y arrastre de 44 dp hacia el borde final. La comprobación de TalkBack y atrás predictivo en un teléfono sigue siendo la de §13.

Los botones antiguos que una pantalla haya escrito dentro de su cuerpo siguen formando parte de ese cuerpo. El nuevo contrato `footer` permite fijarlos y subir la acción con IME sin adivinar callbacks ni editar pantallas; su adopción pertenece a las migraciones correspondientes. No se ha modificado `ui/screens/**` ni `GuiCustomizerModal.kt`.

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
- A 320 dp de alto, una banda fija de 108 ocupa **33,75 %**, incompatible con exigir simultáneamente ≤30 %. En los dos tamaños obligatorios ocupa **26,28 % y 30 %**. Se conserva el valor de banda aprobado; C registra también la prueba de altura mínima.
- En una ventana compacta de ancho menor que 376, no cabe una hoja mínima de 360 con márgenes de 8 a ambos lados. El ancho se limita al espacio seguro disponible; C añadirá la medida de esa excepción.
- No se cambian contenido, datos, esquema Room, temas, identificadores, firma ni versiones. Los PNG históricos regenerados por la suite se restituyeron a HEAD; solo se entregan evidencias de T-047.

Documentación consultada: `AGENTS.md`, protocolo, T-047, reglas editoriales, `LANDSCAPE.md` §2–§3/§11/§12 y las fuentes AndroidX de `WindowInsets.android.kt` 1.7.2 (artefacto de fuentes de Google Maven). La rúbrica de nueve criterios y las comprobaciones de giro, paridad y auditoría se completan en C.
