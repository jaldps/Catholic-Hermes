# Revisión de interfaz en tableta (`better-interface`, los 7 dominios)

- **Fecha:** 23-nov-2026.
- **Método:** skill `better-interface` y sus siete skills de dominio, de `jakubkrehel/skills` @ `672ae44`, instaladas en `.claude/skills/`. Se cargaron en el orden que manda la skill: accessibility, layout, writing, typography, colors, ui, motion.
- **Revisor:** el coordinador.
- **Versión anterior:** era un piloto solo de layout. **Esta la sustituye:** el dueño pidió revisarlo todo.

## Alcance y cobertura

- **Qué:** las disposiciones de tableta de T-043, en vertical **840 × 1200 dp** y horizontal **1280 × 800 dp**.
  - Pantallas: Hoy, Orar, Leer/Biblia y Santos (lista-ficha).
  - Además, el riel de navegación y los componentes compartidos que usan (`lumenPress`, `ContentLoadNotice`, tema).
  - En las 4 identidades: Lumen claro, Tradicional oscuro, Cristal claro y Sanctuary oscuro.
- **Pila:** Kotlin + Jetpack Compose. Tokens en `ui/theme/DesignSystem.kt`; wrappers en `ui/design` (`AppComponents.kt`, `LumenKit.kt`, `LumenFoundation.kt`).
- **Convenciones leídas:**
  - `AGENTS.md`;
  - `docs/design/LANDSCAPE.md`: §2.1 clases de ventana, §2.5 identidades, §3.10 medida de lectura, §6.4 y §7 tableta, P9;
  - `docs/content/EDITORIAL-RULES-1.8.md`, por los textos.
- **Material:** 32 capturas **renderizadas de nuevo** desde `CatholicGPT` @ `0bd5d40` con `TabletScreenshotTest` (con «Mi camino» como 5.ª pestaña), el código fuente y los datos bíblicos incluidos.
- **Fuera de alcance:** las pantallas que no son raíz y las hojas o diálogos en tableta. Las cubre L7 (§10).

| Dominio | Evidencia inspeccionada | Resultado |
|---|---|---|
| Accessibility | `LumenFoundation.kt` (`lumenPress`, 72 usos), los 19 `AppGlassIconButton` de `ui/screens`, semántica del riel (`LumenKit.kt:418–607`), objetivos táctiles en las capturas | 1 hallazgo, HIGH |
| Layout | Las 32 capturas, `MainApp.kt`, `AppComponents.kt`, `CrossReferenceViews.kt`, `BibleScreen.kt`, `HomeScreen.kt` | 4 hallazgos |
| Writing | Los textos visibles de las 4 pantallas (ES) y los estados vacíos o de error de T-055 | 1 hallazgo, LOW |
| Typography | Medida de lectura (`DesignSystem.kt:258`, estilo `reading`) y los datos de `assets/bible/bible_{es,en,pt}.json` | 2 hallazgos |
| Colors | `DesignContrastTest` (tokens) y **30 pares medidos en las capturas** de las 4 identidades | **Clear** |
| UI | Radios anidados (riel, segmentos, tarjetas), marcos de imagen e iconos | Clear (el único detalle repetido de dos formas es el hallazgo 7) |
| Motion | Puertas de `reduceMotion` en el código (`Theme.kt`, `MainActivity.kt`, `SacredArt.kt`, `DevotionalLiquidGlass.kt`) y `AnimatedVisibility` de la Biblia | 2 hallazgos |

## Hallazgos

Ordenados por severidad y, dentro de cada una, por alcance.

| # | Severidad | Dominio | Ubicación | Antes | Después | Por qué |
|---|---|---|---|---|---|---|
| 1 | **HIGH** | accessibility | `ui/design/LumenFoundation.kt:143` (`lumenPress`). Lo usan 72 controles: pestañas del riel y de la barra, segmentos, filas, botones y tarjetas. | `.clickable(source, indication = null, enabled = enabled, role = role)`: sin ningún indicador de foco. | Indicación propia que **solo dibuja con foco de teclado**: un `IndicationNodeFactory` que lee `FocusInteraction` del mismo `source` y pinta un anillo de 2 dp en `palette.textPrimary` (≥ 3:1 sobre cada superficie) siguiendo la forma del control. Sin ripple, conservando el resorte actual. Se aplica una vez en `lumenPress` y llega a los 72 usos. | **Disparador:** control alcanzable con teclado sin indicador de foco visible (WCAG 2.4.7). Las tabletas con teclado (funda con teclado, Chromebook) no muestran dónde está el foco. |
| 2 | **HIGH** | motion | `MainActivity.kt:78` y `ui/theme/Theme.kt:93–95` | `reduceMotion = preferences.reduceMotion`: solo el ajuste propio de la app, falso por defecto. El ajuste del sistema «Quitar animaciones» solo lo lee `ui/components/SacredArt.kt:125` (`ValueAnimator.areAnimatorsEnabled()`). | `reduceMotion = preferences.reduceMotion \|\| (Build.VERSION.SDK_INT >= 26 && !ValueAnimator.areAnimatorsEnabled())`, reevaluado en `onResume`. Así, `SacredArt` deja de ser el único que lo respeta. | **Disparador:** movimiento que ignora la preferencia de reducir movimiento. Con «Quitar animaciones» activado siguen el resorte de presión, la lente deslizante del riel y del selector de devoción, y la entrada y salida de la barra de la Biblia. Además, el mismo criterio está implementado de dos formas. |
| 3 | MEDIUM | layout | `ui/MainApp.kt:611` y `ui/design/AppComponents.kt:263` (`appContentMaxWidth()`). Afecta a Hoy, Orar, Mi camino y Explorar a 1280 × 800. | Toda raíz no migrada es una columna centrada de unos 680 dp. A 1280 dp queda vacío **cerca del 45 % del ancho** y lo esencial de Hoy cae bajo el pliegue. | **Ya diseñado:** `LANDSCAPE.md` §2.1, §6.4 y §7: panel de `AppPageLayout` y 2 carriles, 3 desde 1200 dp. **T-050, T-051 y T-053 exigen capturas a 1280×800 y 840×1200 en las 4 identidades.** | Mantener la estructura hasta que se rompa. Medio ancho vacío con lo esencial bajo el pliegue. |
| 4 | MEDIUM | typography | `app/src/main/assets/bible/bible_es.json`, **3.048 versículos (8,5 %)**. Por libro: Hechos 405, Juan 320, Lucas 291, Apocalipsis 257… En `bible_en.json`, 16. | «…y el Verbo era Dios **.**», «…no la recibieron **.**», «(Yahvé) **:**». Hay un espacio antes del signo, resto de notas retiradas de la edición. | Limpieza única de los datos con `\s+([.,;:!?])` → `$1` sobre los versos, con un script en `tools/bibles/` que deja constancia. Después se regeneran los conteos o hashes que vigilen `BibleIntegrityTest` y la referencia cruzada, y un test impide que vuelva. | Puntuación tipográfica: el texto mostrado no lleva caracteres de transcripción. Se ve en cada lectura del Nuevo Testamento. |
| 5 | MEDIUM | typography | `ui/theme/DesignSystem.kt:258` (`readingMaxWidth = 640.dp`): columna del capítulo en tableta vertical | Con EB Garamond, un versículo como Jn 1,1 ocupa **una línea de unos 83 caracteres** en 840 × 1200. | La columna de lectura mide con `appReadingMeasure()` (L0, §3.10: 66 caracteres en el estilo y la fuente reales del usuario), no con 640 dp fijos. Entra en **T-049 (L2)** para la Biblia y los demás lectores en tableta. | Medida de 60–75 caracteres para el texto largo. Las líneas largas cansan y hacen perder el renglón. |
| 6 | MEDIUM | layout | `ui/screens/CrossReferenceViews.kt:556` y `:570`: panel de arcos en horizontal | `weight(0.56f)` + `Box(weight(0.44f))`. Sin versículo elegido, la caja inferior solo muestra una línea de ayuda y queda vacío **cerca del 40 % del alto** del panel. | Sin versículo elegido, los arcos ocupan el alto (`weight(1f)`) y la ayuda va como pie bajo ellos. La caja de 0,44 aparece solo con un versículo elegido. **Entra en T-049.** | P9: ninguna franja vacía de más de 48 dp de alto sin propósito. |
| 7 | MEDIUM | layout | `ui/screens/HomeScreen.kt:1039` («Ver mis prácticas», `AppPrimaryCapsule`) y `:594` («Ver la liturgia de hoy») | Dos botones primarios rellenos en la misma vista, uno a todo el ancho y otro ajustado a su texto. Pasa en tableta y en teléfono. | **Ya decidido:** decisión vetable 7 de `LANDSCAPE.md` §6.2. «Ver mis prácticas» pasa a secundario **también en vertical** y «Leer las lecturas» queda como el único primario. **Entra en T-050 (L3).** | Un solo primario por vista. Dos rellenos compiten y diluyen la jerarquía. |
| 8 | LOW | writing | `ui/components/ContentLoadNotice.kt:27` | «No se pudo cargar el contenido.» + «Reintentar» | «No se pudo abrir este texto. Vuelve a intentarlo.» El error casi nunca es de red (el contenido viene incluido), y ahora dice qué falló y qué hacer. Lo mismo en EN y PT. | Un error dice cómo seguir. «El contenido» no orienta. |
| 9 | LOW | motion | `ui/screens/BibleScreen.kt:1451–1453` (barra del lector) | Entra con `slideIn` en `tween(260)` y sale con `slideOut` también en `tween(260)` y la misma distancia. | Salida más corta que la entrada: `tween(150)` y un desplazamiento fijo pequeño. | La salida es más corta y más pequeña que la entrada. Se ve muchas veces al leer. |
| 10 | LOW | layout | `ui/screens/BibleScreen.kt:1450–1454` (`ReaderBar` con `Alignment.BottomCenter`) | La barra flota sobre las últimas líneas visibles. Hay padding final (`:1384`) y la barra se oculta al desplazar, así que el final es alcanzable. | En tableta, nada que hacer. En teléfono horizontal, L2 (§5.1) la convierte en pie fijo. | Que el contenido pase bajo un control fijo es aceptable si su final se alcanza. Se anota por coherencia con L2. |

Hay 10 hallazgos, por debajo del tope de 15.

## Verificación

**Pasaron:**
- **`DesignContrastTest`:** 1/1, con `flock` y Gradle, sobre `CatholicGPT` @ `0bd5d40`.
- **30 pares de contraste medidos en las capturas.** Luminancia relativa WCAG, el 6 % de píxeles extremos de la letra frente a la mediana del fondo. El suavizado al 80 % de escala hace que lo medido quede por debajo del valor real.

  | Identidad | Rango | Casos |
  |---|---|---|
  | Cristal claro | 5,58–12,65:1 | segmento no elegido, subtítulo de fila, etiquetas del riel, antetítulo sobre arte, losas, texto sobre foto |
  | Lumen claro | 5,58–15,47:1 | los mismos |
  | Sanctuary oscuro | 7,48–8,99:1 | subtítulos, riel, «Ver mis prácticas» sobre rosa, Vatican News |
  | Tradicional oscuro | 5,11–15,91:1 | riel, subtítulos, botón claro, icono de fila |
  | Antetítulo de la ficha de santo sobre grabado (Lumen y Cristal claro) | 4,51–5,05:1 | justo en el límite |

  Ninguno falla.
- **Nombres accesibles:** los 19 `AppGlassIconButton` de `ui/screens` pasan `contentDescription` con la acción en ES, EN y PT.
- **Pestañas del riel y de la barra:** llevan `Role.Tab` y `selected`.
- **Objetivos táctiles visibles:** ≥ 44 dp en riel, filas, segmentos y botones de las capturas.
- **Riel de 5 destinos:** cabe en las dos orientaciones y en las 4 identidades.
- **Lista-ficha de Santos:** selección por defecto, lista de 360 y ficha legible.
- **Datos bíblicos:** conteo de espacios antes de signos con un script sobre los JSON: ES 3.048, EN 16, PT 0.

**No verificado (no se pudo ejecutar aquí):**
- recorrido real con teclado y con TalkBack en un dispositivo;
- comportamiento con «Quitar animaciones» activado en un teléfono;
- la cámara lenta de las animaciones;
- si Compose escala sus animaciones con la escala de animación del sistema.

## Veredicto

**Block** por los hallazgos **1** (foco invisible) y **2** (reducir movimiento del sistema). Ninguno de los dos es exclusivo de la tableta: afectan a toda la app.

Los siete dominios están revisados, con el alcance indicado arriba. No queda ningún dominio sin revisar.

## Cómo se integra

| Hallazgos | Dónde |
|---|---|
| **1 y 2** (HIGH, componentes compartidos) | **T-047 (L0, Codex)**, que ya está tocando `LumenFoundation.kt` y el tema. Así llegan a toda la app y a todas las áreas horizontales que vienen detrás. |
| **4** (espacios en la Biblia) | Encargo pequeño de datos para Hermes o Codex, con su script y su test. No choca con el horizontal. |
| **3, 5, 6 y 7** | Ya están en el diseño aprobado. Se añaden como criterios de aceptación a T-049, T-050, T-051 y T-053. |
| **8, 9 y 10** | Se recogen en T-049 (L2). |
