# Revisión de interfaz en tableta (piloto con `better-interface`)

- **Fecha:** 23-nov-2026.
- **Método:** skill `better-interface` (`.claude/skills/`, de `jakubkrehel/skills` @ `672ae44`), con `better-layout` como dominio principal.
- **Revisor:** el coordinador.

## Alcance y cobertura

- **Qué:** las disposiciones de tableta de T-043, en vertical **840 × 1200 dp** y horizontal **1280 × 800 dp**. Cuatro pantallas raíz (Hoy, Orar, Leer/Biblia y Santos), en las 4 identidades: Lumen claro, Tradicional oscuro, Cristal claro y Sanctuary oscuro.
- **Pila:** Kotlin + Jetpack Compose. Los wrappers y tokens están en `ui/design` (`AppComponents.kt`, `LumenKit.kt`, `LocalAppDesign.current.spacing`).
- **Convenciones leídas:**
  - `AGENTS.md`: cabezal único, todo pasa por `AppKit` y wrappers, ilustraciones recortadas;
  - `docs/design/LANDSCAPE.md`: §2.1 clases de ventana, §6.4 Hoy en tableta, §7 centros, P9 espacio negativo.
- **Material:** capturas **renderizadas de nuevo** desde `CatholicGPT` @ `0bd5d40`, con `TabletScreenshotTest`. Ya incluyen «Mi camino» como 5.ª pestaña. Las de `docs/tasks/T-043-shots/` son anteriores y no se usaron. Las nuevas no se suben al repositorio.
- **Límite del piloto:** estructura y espacio, que es lo que cambia la tableta. Los demás dominios no se revisaron a fondo (ver tabla).

| Dominio | Evidencia inspeccionada | Resultado |
|---|---|---|
| Accesibilidad | — | **No revisado.** No se cargó `better-accessibility`; solo se miraron a ojo los objetivos táctiles del riel. |
| Layout | 32 capturas (4 pantallas × 2 orientaciones × 4 identidades), `MainApp.kt`, `AppComponents.kt`, `CrossReferenceViews.kt`, `BibleScreen.kt` | 3 hallazgos |
| Writing | — | **No revisado.** Los textos no cambian con el tamaño de pantalla. |
| Typography | — | **No revisado.** Fuera del piloto. |
| Colors | Un contraste medido, el antetítulo sobre la imagen de la ficha del santo | **No revisado** como dominio; la medición pasa (ver Verificación). |
| UI | — | **No revisado.** Fuera del piloto. |
| Motion | — | **No revisado.** Las capturas son estáticas. |

## Hallazgos

| Severidad | Dominio | Ubicación | Antes | Después | Por qué |
|---|---|---|---|---|---|
| MEDIUM | layout | `ui/MainApp.kt:611` y `ui/design/AppComponents.kt:263` (`appContentMaxWidth()`). Afecta a Hoy, Orar, Mi camino y Explorar a 1280 × 800. | `val width = if (wide && !frame.fullWidth) minOf(constraints.maxWidth, columnMax)`: toda raíz no migrada es una columna centrada de unos 680 dp. A 1280 dp, con el riel, **queda vacío cerca del 45 % del ancho** y lo esencial de Hoy («Mis prácticas», la liturgia) cae bajo el pliegue. | **Ya está diseñado:** `LANDSCAPE.md` §2.1 (`TabletLandscape`), §6.4 y §7. Hoy, Orar, Leer, Mi camino y Explorar usan el panel lateral de `AppPageLayout`, con 2 carriles, y 3 desde 1200 dp. **Acción:** los encargos L3 (T-050), L4 (T-051) y L6 (T-053) incluyen como criterio de aceptación las capturas a 1280 × 800 y a 840 × 1200, en las 4 identidades. | Hay que mantener la estructura ampliada hasta que deje de caber, en vez de encoger el contenido a una columna de móvil. En pantalla grande, medio ancho vacío y lo esencial bajo el pliegue empeoran la eficiencia. |
| MEDIUM | layout | `ui/screens/CrossReferenceViews.kt:556` y `:570`: panel de arcos de la Biblia en horizontal | `AppGlassSurface(Modifier.fillMaxWidth().weight(0.56f))` + `Box(Modifier.weight(0.44f))`. Sin versículo elegido (`verse == null`), la caja inferior solo muestra una línea de ayuda, así que **unos 40 % del alto del panel queda vacío**. | Sin versículo elegido, los arcos ocupan el alto (`weight(1f)`) y la ayuda va como pie bajo ellos, con su alto natural. La caja de 0,44 solo aparece cuando hay versículo, con `AnimatedContent` y la misma curva de la app. | P9 (`LANDSCAPE.md` §2): ninguna franja vacía de más de 48 dp de alto sin propósito. Agrupar con espacio, no dejar huecos muertos. |
| LOW | layout | `ui/screens/BibleScreen.kt:1450–1454` (`ReaderBar` con `Alignment.BottomCenter`) | La barra flota sobre las últimas líneas visibles mientras se muestra. Hay `contentPadding(bottom = readerBarHeight + 32.dp)` (`:1384`) y la barra se oculta al desplazar, así que el final del texto es alcanzable. | En tableta, nada que hacer. En teléfono horizontal, L2 (§5.1) ya la sustituye por un pie fijo («Nada flota sobre el texto»). | El contenido que pasa bajo controles fijos es aceptable si el final se alcanza. Aquí se alcanza; se anota por coherencia con L2. |

## Verificación

**Pasaron:**
- **Riel de 5 destinos en tableta:** cabe en vertical y en horizontal, en las 4 identidades. «Mi camino» va en 2 líneas, sin cortes.
- **Contraste** del antetítulo «2 de enero · Memoria» sobre el grabado de la ficha del santo (`*_landscape_4_santos`). Medido en las capturas (luminancia relativa WCAG; el 5 % más oscuro frente a la mediana del fondo):
  - Lumen claro: **4,51:1**;
  - Cristal claro: **5,05:1**.

  Es el límite y pasa. El suavizado al 80 % de escala hace que lo medido quede por debajo del valor real.
- **Lista-detalle de Santos** a 1280 y 840: selección por defecto, lista de 360 y detalle legible.

**No verificado:** foco de teclado, TalkBack, movimiento, tipografía fina y textos.

## Veredicto

**Approve** (sin hallazgos HIGH) en **layout**. **No revisados:** accessibility, writing, typography, colors (salvo una medición), ui y motion.

**Cómo se integra:**
- El hallazgo 1 entra como criterio de aceptación en T-050, T-051 y T-053, ya diseñados.
- El hallazgo 2 entra en el encargo de L2 (T-049, Codex), porque toca el mismo panel de la Biblia.
- No hace falta un encargo aparte de pulido de tableta.
