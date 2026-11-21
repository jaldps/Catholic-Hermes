# Interfaz horizontal (landscape) · Especificación de diseño

Fase D del bloque T-047…T-05x («método horizontal reforzado», `docs/tasks/PLAN-NOV-2026.md`). Este documento es **diseño, no código**: describe qué debe construirse, dónde, con qué medidas y cómo se comprueba. Ningún archivo de `app/` cambia en esta fase.

**Revisión 2** (21-nov-2026): responde a `CRITICA-1.md`. El mapa de los 24 defectos y de los 9 problemas del resumen está en **§17 · Respuesta a la crítica 1**.

- Base: versión 1.8.28 (HEAD `83b3766`), con la auditoría T-045 (`appLargeText`, `appMaxLines`, `NarrowLargeTextAuditTest`).
- Lo que ya existe para ventanas anchas (T-043) sigue valiendo para tabletas: riel desde 600 dp, columna centrada de 680 dp, dos paneles desde 840 dp.
- Objetivo del dueño: «Quiero una interfaz horizontal práctica, profesional, no simplemente que se vea la misma interfaz de vertical pero estirada. El espacio es diferente; ergo, la configuración visual debe ser diferente.»

**Calibración del dueño.** Todo se optimiza para la letra al **100 %**, que es la predeterminada. El 130 % es una opción del usuario: a ese tamaño solo rige el **listón duro** (nada recortado, oculto ni inalcanzable). Se acepta simplificar: menos columnas, más desplazamiento, paneles que desplazan.

**Índice:**
- [§1 Evidencia](#1-evidencia-lo-que-se-ve-hoy-en-horizontal)
- [§2 Principios](#2-principios)
- [§3 L0 Fundamentos](#3-l0--fundamentos-en-uidesign)
- [§4 L1 Rosario](#4-l1--rosario-y-coronilla)
- [§5 L2 Lectores](#5-l2--lectores)
- [§6 L3 Hoy](#6-l3--hoy)
- [§7 L4 Orar y Leer](#7-l4--centros-orar-y-leer)
- [§8 L5 Listas con ficha](#8-l5--listas-con-ficha)
- [§9 L6](#9-l6--mi-camino-confesión-recordatorios-y-explorar)
- [§10 L7](#10-l7--hojas-diálogos-bienvenida-y-ajustes)
- [§11 Rúbrica](#11-rúbrica-del-crítico)
- [§12 Capturas](#12-matriz-de-capturas)
- [§13 Teléfono real](#13-lista-de-comprobación-en-el-teléfono-real)
- [§14 Riesgos](#14-riesgos-y-decisiones-abiertas)
- [§15 Pantallas restantes](#15-cobertura-completa-las-pantallas-restantes)
- [§16 Estados](#16-estados-por-área)
- [§17 Respuesta a la crítica 1](#17-respuesta-a-la-crítica-1)

**Convenciones:**
- **Unidades y bocetos.** Todas las medidas en dp. Los bocetos ASCII están a **891 × 411 dp**, con 1 columna ≈ 10 dp y 1 fila ≈ 20 dp. Las etiquetas de test entre comillas invertidas existen hoy y **se conservan**; las marcadas «(nueva)» son añadidos.
- **Ventana e insets.** `W` y `H` = ancho y alto de la ventana. Todas las cuentas usan los insets de un teléfono real con navegación por gestos:
  - **`S` = 24** (barra de estado) y **`B` = 16** (barra de gestos);
  - zonas de gesto lateral de 32;
  - y, como variante, un recorte de cámara de 32 en un lateral.
  - El alto útil es `H − S − B`: **371** a 891 × 411 y **320** a 800 × 360.
- **Teclado.** El teclado de referencia mide **0,56 H**: 230 a 411 y 202 a 360. Sobre él quedan `H − 0,56 H − S` = **157** (411) y **134** (360) dp útiles.
- **Medición del texto.** Las alturas de texto de §4.3 y §3.10 se midieron con las fuentes de la app (`res/font/ebgaramond_regular.ttf`, `inter_regular.ttf`) y sus métricas, con ajuste de línea por palabras. Un test con `TextLayoutResult` las confirma en L0/L1 (§3.13).

Las capturas del «antes» citadas en el resumen están en `docs/design/antes/`.

---

## 1. Evidencia: lo que se ve hoy en horizontal

Capturas propias de esta fase. Se renderizó la app completa a **891 × 411 dp, orientación `land`, español, 4 identidades, modo claro, texto al 100 %**:
- todos los destinos;
- y las subpantallas que recorre `NarrowLargeTextAuditTest`: capítulo bíblico, Misa, lección, oración, novena y día, Rosario y sus hojas, santo, orden, oficio, devoción, doctrina, pregunta, artículo del Catecismo, Compendio, plan, Confesión, búsqueda, recordatorios, Apariencia, Fuente, Tu camino, copia de seguridad, reglas del calendario y bienvenida.

En total, 461 PNG. Se usó una copia del test de auditoría fuera del repo, con `qualifiers = "w891dp-h411dp-land-xhdpi"`; el test del repositorio no se tocó. El detector de T-045 corrió también a esa medida.

| # | Hallazgo | Dónde se ve |
|---|---|---|
| E1 | El cabezal de 236 dp (arte + título) ocupa ≈ 230 de 411 dp de alto, un **56 %**, en todas las raíces. | Hoy, Orar, Leer, Explorar, Mi camino, Planes, Preguntas, Recordatorios, Confesión |
| E2 | **Hoy**: sin desplazar solo se ven la tarjeta del tiempo litúrgico y la primera fila de «Oración del momento». La liturgia del día y las prácticas quedan fuera. | `dest_home` |
| E3 | **Rosario**: ya tiene una variante ancha (arte y etiqueta a la izquierda; progreso, cuentas y oración a la derecha). Pero **el texto de la oración queda bajo el pliegue**: hay que desplazar para leer «En el nombre del Padre…». Las cuentas son un carrete horizontal en la columna derecha. El riel sigue visible, aunque la pantalla es inmersiva. | `dest_rosary_root_0` / `_end` |
| E4 | **Capítulo bíblico**: la columna de texto mide ≈ 300 dp (≈ 38 caracteres por línea), menos que el panel de arcos. La barra flotante (‹ San Juan 1 ▶ Aa ›) tapa versículos. | `bible_chapter` |
| E5 | **Dos paneles** ya activos a 891 dp (≥ 840), pero el panel de lista empieza bajo el arte de Santos (la lista arranca hacia y = 300 dp). El detalle repite un cabezal de 236 dp. | `dest_saints`, `saint_detail` |
| E6 | **Hojas inferiores** de 891 dp de ancho y hasta el 90 % del alto. En «Opciones del Rosario» solo caben las voces y el principio del mapa. En «Apariencia», los segmentos se estiran a 860 dp. | `rosary_options`, `settings_appearance` |
| E7 | **Bienvenida**: el diálogo se ancla arriba a la izquierda, «Tiempo para hoy» queda cortado abajo y la acción principal no se ve sin desplazar. | `welcome_dialog` |
| E8 | **Catecismo**: los controles flotantes (índice, Tt) tapan el texto. | `catechism_article` |
| E9 | **Objetivos pequeños** que el detector marca a esta medida (21 por identidad): horas de recordatorio de 34 dp de alto (7 + 7 en la hoja), enlace de crédito de imagen de 18 dp (santo, devoción), fila de versículo de 38 dp. | `build/audit-land/findings_*_es.tsv` |
| E10 | **Riel**: 4 destinos de 68 dp = 280 dp. Con la quinta pestaña («Mi camino», T-046) serían 340 dp. **No cabe** en un teléfono de 360 dp de alto con barra de estado y de gestos (360 − 24 − 16 = 320). | `LumenKit.railSlot` |
| E11 | **Insets laterales** (lectura del código, Robolectric no tiene insets): el andamiaje solo respeta `statusBars` arriba y `navigationBars` abajo. Con tres botones de navegación o un recorte de cámara en un lateral, el riel y el contenido pueden quedar debajo. | `LumenKit.Scaffold`, `ScreenFrame.insets` |
| E12 | **Giro** (lectura del código): <ul><li>la lista y las notas de Confesión se restauran **vacías** a propósito (`ConfessionDraft.Saver`), así que girar las borra;</li><li>«Rezar en voz alta» se detiene al girar, porque `onDispose` envía `ScreenLeft`;</li><li>las hojas abiertas con `remember` se cierran (Rosario, Recordatorios, Avisos y Copia de seguridad no tienen `rememberSaveable`).</li></ul> | `ConfessionDraft.kt`, `RosaryScreen.kt`, `RemindersScreen.kt`, `NoticeSettings.kt`, `BackupSheet.kt` |

Las identidades se comportan igual. Sanctuary pone el título sobre el papel y el arte en marco debajo (≈ 170 dp de alto), así que el problema de altura es el mismo.


**Nota sobre estas capturas.** Robolectric dibuja sin barra de estado ni de gestos (`S` = `B` = 0). En un teléfono real el problema es mayor: hay 40 dp menos de alto. Una selección está en `docs/design/antes/`.

---

## 2. Principios

**P1 · La restricción es la altura.** Un teléfono en horizontal mide 800–932 × 360–430 dp: sobra anchura y falta altura. Todo lo que en vertical se apila (arte → título → controles → contenido) pasa a **ponerse en paralelo**: lo que orienta va a un lado; lo que se lee o se usa, al otro.

**P2 · Patrones adaptativos establecidos, en nuestro idioma visual.** Se usan los layouts canónicos de Material 3, dibujados con los materiales de cada identidad:
- **riel de navegación** en lugar de la barra inferior;
- **panel de apoyo** (supporting pane) en lectores, Rosario y Biblia;
- **lista-detalle** en los catálogos;
- **feed en carriles** en Hoy, Mi camino y los centros;
- **hojas laterales** en lugar de las inferiores.

**P3 · Nada se oculta ni se encoge.**
- El contenido, las etiquetas, los valores y las etiquetas de test del vertical siguen presentes.
- El texto nunca baja del tamaño que eligió el usuario. Si algo no cabe, se desplaza; no se trunca.
- El arte puede reducirse, recortarse a su marco o pasar a miniatura, pero nunca tapa texto ni controles.

**P4 · Una sola fuente de verdad.**
- La clase de ventana se calcula una vez, en `ui/design` (`appWindowClass()`).
- Ninguna pantalla mide `screenWidthDp`, `orientation` ni `maxWidth` por su cuenta. Hoy lo hacen `BibleScreen` (orientación) y `RosaryScreen` (`maxWidth >= 600`); ambos migran a wrappers declarados (§3.4).

**P5 · Pulgares en los bordes.** Ver §2.3.
- **Pulgar izquierdo:** navegación, retroceder y herramientas.
- **Pulgar derecho:** la acción que hace avanzar.

**P6 · El giro no cambia el estado.** Ver §2.4.

**P7 · Cada identidad sigue siendo ella misma.** Ver §2.5.

**P8 · Coherencia con el vertical.** Mismo orden de lectura, mismos textos, iconos y acciones.

### 2.1 Clases de ventana

**Fuente de las medidas.** `W` y `H` son el tamaño de la **ventana completa**, barras incluidas.
- Se leen de `LocalWindowInfo.current.containerSize` (Compose UI 1.7, BOM 2024.09 del proyecto), convertido a dp.
- Si esa API resultara experimental o inexacta en algún nivel de API, se usa `WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)` de `androidx.window` (dependencia nueva, de L0).
- **No** se usa `Configuration.screenWidthDp/screenHeightDp`: hasta la API 34 excluyen las barras del sistema.
  - Ejemplo: 891 × 411 se leería como 891 × 363. La clase sería correcta, pero todas las proporciones de este documento se calcularían mal.
- Un test fija el cálculo: ventana conocida → `W`, `H` y clase esperados (§3.13).

| Clase (`AppWindowClass`) | Condición | Navegación | Disposición |
|---|---|---|---|
| `Phone` | `W < 600` y `H ≥ 480` | barra inferior | la de siempre, sin cambios |
| `CompactPhone` (pantalla dividida pequeña) | `W < 600` y `H < 480` | barra inferior | <ul><li>como `Phone`, pero con `CompactBand` (§3.3), hojas laterales (§3.6) y diálogos con alto máximo (§3.7)</li></ul> |
| `LandscapePhone` («compacta en altura») | `W ≥ 600` y `H < 480` | riel compacto (§3.5) | **este documento** |
| `TabletPortrait` | `600 ≤ W < 840` y `H ≥ 480` | riel (T-043) | columna centrada de 680 (T-043) |
| `TabletLandscape` | `W ≥ 840` y `H ≥ 480` | riel (T-043) | <ul><li>dos paneles (T-043)</li><li>con L3/L4, Hoy, Orar, Leer, Mi camino y Explorar usan el panel lateral, con 3 carriles desde `W ≥ 1200`</li></ul> |

- **`H < 480` manda sobre la anchura.** Una tableta dividida en horizontal (1280 × 400) es `LandscapePhone` a propósito.
- **Sin histéresis.** La clase solo cambia con la configuración.

### 2.2 Insets: recorte de cámara, barra de gestos lateral y teclado

La app usa `targetSdk = 36` con `enableEdgeToEdge()`, así que dibuja bajo las barras y bajo el recorte.

| Inset | Valor de validación | Lado | Regla |
|---|---|---|---|
| `statusBars` | **24** | arriba | <ul><li>El arte del panel lateral dibuja debajo.</li><li>El texto y los controles empiezan en `S + 4` o más.</li></ul> |
| `displayCutout` | 0 o **32** (variantes) | izquierda **o** derecha, según el giro | <ul><li>Ningún texto ni control entra en él.</li><li>El arte y los fondos llegan al borde.</li></ul> |
| `navigationBars`, tres botones | 48 (variante) | lateral | igual que el recorte |
| `navigationBars`, gestos | **16** | abajo | <ul><li>Lo último de cada columna termina en `H − B` o más arriba.</li><li>Las barras fijas se apoyan en `H − B`.</li></ul> |
| `systemGestures` laterales | **32** | izquierda y derecha | Ningún control **arrastrable** empieza a menos de `gestos + 8`. |
| `ime` (teclado) | **0,56 H**: 230 (411) y 202 (360) | abajo | ver abajo |

**`appSafeInsets()`** (nuevo, en `LumenFoundation.kt`) devuelve, por lado, `max(statusBars, navigationBars, displayCutout)`.
- **Inicio:** el riel se coloca después de `safeStart`. Sin riel, el primer panel suma `safeStart` a su margen interior.
- **Fin:** la última columna usa `max(gutter, safeEnd + 12)`; los grupos de acciones del borde derecho, `max(12, safeEnd + 8)`.
- `ScreenFrame.insets` pasa a llevar `start` y `end`.

**Teclado.** Sobre el teclado quedan 157 dp (891 × 411) o 134 dp (800 × 360).
- **El campo con foco se ve siempre, con ≥ 2 líneas y el cursor.** La columna que contiene el campo lleva `imePadding()` y hace `bringIntoView` del campo.
- **La acción del campo** (Buscar, Listo, Guardar) **se ve sobre el teclado:**
  - en un campo de una línea, es la tecla de acción del IME (`ImeAction`);
  - en editores y hojas, la acción principal se **mueve a la cabecera** mientras el teclado está abierto (§3.6, §10.3).
  - Las acciones destructivas («Terminar y borrar») **no** suben junto al teclado: quedan en su sitio, bajo él, a propósito.
- **Lo que no contiene el campo no se mueve.** El panel lateral sigue igual.
- **Teclado a pantalla completa.** Compose pide `IME_FLAG_NO_FULLSCREEN`; se comprueba en el teléfono real (§13).
- **Presupuestos con teclado** (§8.4, §8.6, §9.2, §10):
  - Búsqueda: barra de 56 + ≥ 1 fila de resultado completa (64).
  - Notas de Confesión: rótulo 18 + 2 líneas (48) + margen 24 = 90.
  - Hojas con campo: cabecera 56 + campo ≥ 56.
  - Editor de diario: barra 56 + 3 líneas (72).
  - Todos caben en 134.

### 2.3 Ergonomía de los pulgares

```
 891 × 411, teléfono sujeto con las dos manos
┌───────────────────────────────────────────────────────────────────────────────────────┐
│ difícil ··························· difícil ························· difícil         │  y < 0,25 H: atrás, título
│ ┌────────────────┐                                            ┌────────────────┐     │  y acciones poco frecuentes
│ │ PULGAR         │      centro: leer y desplazar              │ PULGAR         │     │
│ │ IZQUIERDO      │      (se desliza, no se apunta)            │ DERECHO        │     │
│ │ riel, cuentas, │                                            │ SIGUIENTE      │     │  ← y ≥ 0,6 H
│ │ Anterior,      │                                            │ (≤ 120 dp del  │     │
│ │ Escuchar, Aa   │                                            │  borde seguro) │     │
│ └────────────────┘                                            └────────────────┘     │
└───────────────────────────────────────────────────────────────────────────────────────┘
```

**Umbral único** (rúbrica 4):
- **Acción que avanza.** En toda pantalla secuencial (Rosario, lectores secuenciales, lección, plan, día de novena, capítulo, primeros pasos), la acción de avanzar tiene su **centro a ≤ 120 dp del borde derecho seguro** y **y ≥ 0,6 H**. Mide ≥ 48 × 48; la del Rosario, ≥ 64 × 64.
- **Objetivos táctiles:** ≥ 44 × 44 dp.
- **Navegación y retroceso:** en el tercio izquierdo, o al inicio del pie de la columna de texto.
- **Franja superior (`y < 0,25 H`):** solo atrás, títulos y acciones poco frecuentes, en la misma posición que en vertical.

### 2.4 Continuidad al girar

La actividad **se recrea** al girar: no hay `configChanges`.

| Estado | Mecanismo actual | Acción requerida |
|---|---|---|
| Destino, pila y pestaña | `rememberSaveable` en `MainApp` | ninguna |
| Segmento de los centros (Oraciones, Novenas, Noche; Biblia, Misa, Estudio; Confesión) | `rememberSaveable` **dentro** del cabezal o del cuerpo | <ul><li>**Elevar el estado por encima de `AppPageLayout`** (ver «Estabilidad del árbol»).</li></ul> |
| Texto de búsqueda (centros, Santos, Preguntas, Compendio) | `rememberSaveable` dentro de la ranura | <ul><li>**Elevar.**</li><li>Test de giro con texto escrito.</li></ul> |
| Rosario: devoción, misterio y paso | `RosarySession` + `RosarySessionStore` | ninguna |
| Rosario: hoja abierta | `remember` | <ul><li>`rememberSaveable`</li></ul> |
| Rosario: «rezar en voz alta» | `remember` + `ScreenLeft` en `onDispose` | <ul><li>no enviar `ScreenLeft` si `activity.isChangingConfigurations`</li><li>el controlador va en un holder de ámbito de actividad (`ViewModel`)</li></ul> |
| Posición de lectura | `LazyListState` o `ScrollState` | <ul><li>**ancla**: primer bloque visible y su fracción (`rememberReadingAnchor`, §3.10)</li></ul> |
| Biblia: libro, capítulo y versículo de estudio | `rememberSaveable` | ninguna |
| Día de novena | `rememberSaveable` | ninguna |
| Confesión: lista y notas | se borran a propósito | <ul><li>holder de ámbito de actividad (`ViewModel`), nunca en el Bundle</li><li>se borra al salir, al pasar a inactiva y al morir el proceso, como hoy</li></ul> |
| Copia de seguridad e informe de error | sin `rememberSaveable` | <ul><li>`rememberSaveable` en los campos no secretos</li><li>la contraseña no se guarda</li></ul> |
| Lista-detalle | `rememberSaveable` (Santos) o pila | <ul><li>selección explícita o por defecto (§3.9)</li></ul> |
| Hojas y diálogos abiertos | variable | <ul><li>`rememberSaveable` para saber qué hoja está abierta</li></ul> |
| Filas expandidas | `rememberSaveable` | ninguna |

**Estabilidad del árbol.** `rememberSaveable` restaura por la posición en el árbol de composición. Reglas obligatorias para L0 y todas las áreas:
1. **Los wrappers llaman a cada ranura desde un único punto.** Cambian la disposición dentro de una política de `Layout`, nunca con ramas `if` que cambien de padre el contenido.
2. **Ningún `rememberSaveable` dentro de `header` ni de `supporting`** de `AppPageLayout`. Esas ranuras se componen en un sitio en vertical (dentro de la lista) y en otro en horizontal (en el panel), así que cambian de padre al girar.
   - El estado que muestran (segmento, consulta, filtros, día) se **eleva** a la pantalla, por encima de `AppPageLayout`, y se pasa como parámetro.
   - Un test de lint de L0 busca `rememberSaveable` dentro de esas lambdas.
3. **Estado crítico con claves explícitas:** `rememberSaveable(key = "…")` para la sesión del Rosario, el ancla de lectura, el segmento y la selección.

**Ficha apilada en vertical → horizontal.**
- **Lista-detalle.** Una ficha abierta como página en vertical (p. ej. un santo) pasa a ser la **selección explícita** de los dos paneles, y la pila se vacía de esa página. «Atrás» sale de la pantalla (regla de T-043). Al volver a vertical, esa selección explícita vuelve a ser página apilada (§3.9).
- **Lectores.** Un lector apilado (oración, capítulo) sigue apilado: el lector horizontal es una página, no un panel.

### 2.5 Cómo conserva cada identidad su carácter

| | Lumen (Liquid Glass) | Tradicional (`CLASSIC`) | Cristal (`GLASS`) | Sanctuary (misal) |
|---|---|---|---|---|
| Panel lateral | <ul><li>Arte con bruma: se disuelve abajo y en el borde final hacia la luz ambiental.</li><li>Título sobre la bruma.</li><li>Controles en cristal claro (`AppGlassIconButton`).</li></ul> | <ul><li>Arte opaco con fundido inferior al lienzo.</li><li>Sin fundido lateral: el panel termina en un **filete de 0,5 dp** (`separator`).</li><li>Controles opacos neutros; acento solo en detalles.</li></ul> | <ul><li>Arte a sangre visible **a través** del cristal transparente del contenido: los paneles refractan el borde del arte.</li><li>Sin filete.</li></ul> | <ul><li>**Folio izquierdo (verso):** título en EB Garamond arriba, sobre papel.</li><li>Arte en **marco filetado** (0,75 dp + 5 dp de paspartú) debajo.</li><li>Entre los dos folios, un **lomo**: doble filete de 0,5 dp con 3 dp de separación.</li></ul> |
| Contenido | Cristal de contenido denso; luz litúrgica continua bajo ambos paneles (un único lienzo). | Superficies opacas `groupedSurface`. | Cristal claro que refracta. | Folio derecho (recto); filas filetadas; capitulares donde ya existen. |
| Riel | Cápsula de cristal flotante con lente deslizante (T-043), en versión compacta. | Cápsula opaca y lente sólida `action` (como hoy). | Cápsula de cristal claro. | Tira de papel a todo el alto, con filete y marca de acento (T-043). |
| Hojas laterales | Cristal flotante separado de los bordes, radio 28. | Opaca, radio 16 y filete. | Cristal claro, borde especular. | Hoja de papel a todo el alto, pegada al borde, con filete de lomo y radio 0 en ese borde. |
| «Reducir transparencia» y alto contraste | Material opaco, como en vertical. | (ya opaco) | Material opaco. | (ya opaco) |
| Modo oscuro | <ul><li>Velo negro del 18 % sobre el arte (como hoy).</li><li>Glifos de estado según `HeroMeta.tone`.</li></ul> | igual | igual | papel oscuro; el marco se mantiene |

El tinte «Dinámico Litúrgico» sigue gobernando el acento y la luz ambiental, sin excepciones en horizontal.


---

## 3. L0 · Fundamentos en `ui/design`

Lo hace un agente local antes que ninguna área. Ninguna pantalla mide ventanas, insets ni anchos de panel por su cuenta.

### 3.1 Andamiaje base

```
 0      80                          372                                                    891
┌───────┬─────────────────────────────┬──────────────────────────────────────────────────────┐ 0
│ (S)   │ (S) ‹                  ⌕  ◔ │ (S)                                                  │
│ ╭───╮ │ ╔═════════════════════════╗ │  ÁREA DE CONTENIDO · desplaza                        │
│ │Hoy│ │ ║ ARTE recortado al panel ║ │  ┌────────────────────────────────────────────────┐ │
│ │Ora│ │ ║ foco HeroMeta, bruma    ║ │  │ carriles, rejilla, lista o texto               │ │
│ │Lee│ │ ║ Antetítulo              ║ │  └────────────────────────────────────────────────┘ │
│ │Exp│ │ ║ Título (largeTitle)     ║ │  ┌────────────────────────────────────────────────┐ │
│ ╰───╯ │ ╚═════════════════════════╝ │  │                                                │ │
│       │  meta                       │  └────────────────────────────────────────────────┘ │
│       │  ┌───────────────────────┐  │                                                      │
│       │  │ RANURA DE APOYO       │  │                                                      │
│       │  │ segmentos, búsqueda,  │  │                                                      │
│       │  │ herramientas          │  │                                                      │
│       │  └───────────────────────┘  │                                                      │
└───────┴─────────────────────────────┴──────────────────────────────────────────────────────┘ 411
  riel     PANEL DE PORTADA 292 · fijo    contenido 519 · margen interior 20 · desplaza
  80       (0,36 · Wc)
```

- El riel muestra 4 destinos mientras T-046 no esté integrado, y 5 después (§3.5).
- Ancho útil: `Wc = W − riel − safeStart − safeEnd`.
- Familias de pantalla:
  - centro o raíz: panel de portada + contenido;
  - Hoy y Mi camino: panel estrecho + 2 carriles;
  - lector: panel de lector + columna de texto (§5);
  - pantalla inmersiva con panel de apoyo: Rosario, Biblia y Confesión;
  - lista-detalle (§3.9).

### 3.2 Panel de portada: el mismo cabezal, en vertical

`AppHeroHeader`, `AppDetailHeader` y `AppLargeTitleHeader` **no cambian de firma**. Cambia dónde se dibujan, a través de `LocalHeaderPlacement` (`InList | SidePanel | DetailPane | CompactBand`), que leen los marcos de `AppHeaderFrame`. Las etiquetas de test del cabezal (`titleTag`, `screen_header_illustration`, `more_hero_photo`…) lo acompañan adonde vaya.

**Geometría:**

| | Valor | 891 × 411 | 800 × 360 |
|---|---|---|---|
| Ancho, centros y raíces (`kind = Hub`) | `clamp(0,36 · Wc, 248, 320)` | 292 | 259 |
| Ancho, Hoy y Mi camino (`kind = Today`) | `clamp(0,28 · Wc, 208, 280)` | 227 | 208 |
| Ancho, lectores (`kind = Reader`, sin riel) | `clamp(0,30 · W, 224, 280)` | 267 | 240 |
| Ancho, Confesión (`kind = Immersive`, sin riel) | `clamp(0,28 · W, 224, 280)` | 249 | 224 |
| Alto | toda la ventana; dibuja bajo la barra de estado y la de gestos | 411 | 360 |
| **Zona de arte** (`AppSidePanelArtHeight`) | `clamp(0,46 · H, 160, 196)`, con `S` incluida | 189 | 166 |
| **Línea del título** | borde inferior de la zona de arte − 14 dp | y = 175 | y = 152 |

**Una sola línea de título.** Todos los títulos de panel se apoyan en la línea `0,46 H − 14`, **sin excepciones**. Es la regla «título en la misma línea base» de AGENTS.md trasladada al panel.

**Títulos largos.** Ninguna pantalla mueve la línea:
1. `largeTitle` en raíces y `title1` en el resto.
2. Si la palabra más larga no cabe en el ancho del panel (medido con `constraints`, no con `screenWidthDp`), o el título pasa de 2 líneas en `largeTitle`, pasa a `title1`.
3. El título crece **hacia arriba**, sobre el arte, hasta `S + 52` (bajo la fila de atrás).
4. Si aun así no cabe (130 %, títulos PT larguísimos), **el panel entero desplaza**. La línea del título sigue en `0,46 H − 14` en reposo, y la parte superior del título queda arriba, alcanzable desplazando el panel.
5. Al 100 %, ningún título de la app llega al paso 4. Lo comprueba el test de paridad con los títulos ES y PT (§3.13).

**Recorte del arte:**
- `ContentScale.Crop` centrado en el foco de `HeroMeta` (`focusX`, `focusY`) y recortado al rectángulo del panel.
- **Lumen:** fundido inferior desde el 30 %; fundido de 24 dp en el borde final hacia la luz ambiental (`Modifier.fadeEnd`, nuevo).
- **Cristal:** sin fundido lateral; el contenido de cristal refracta el borde del arte.
- **Tradicional:** fundido inferior y filete de 0,5 dp en el borde final.
- **Sanctuary:** título sobre el papel en la misma línea; el arte, en marco filetado bajo la meta, a `panel − 2·gutter` × `0,46 H − 72` (mínimo 96), como en el vertical de Sanctuary.

**Variante póster** (cuando la ranura de apoyo está vacía):
- **Cuándo:** Mi camino, Recordatorios vacío, Planes, Doctrina e Historia, Doctrina, Historia y Primeros pasos (§15).
- **Arte:** se prolonga desde la zona de arte **hasta el pie del panel**, con fundido continuo. A partir de `0,46 H` va bajo un velo del lienzo del 70 % (Lumen y Cristal) o del 85 % (Tradicional).
- **Texto:** el título sigue en su línea y la meta va debajo, sobre el velo, con contraste ≥ 4,5:1 (rúbrica 3).
- **Sanctuary:** el marco crece hasta `H − B − 16`.
- Así el panel no queda como un rectángulo de fondo vacío.

**Acciones del cabezal** (buscar, avisos): círculos de 44 dp en la esquina superior final del panel, a `S + 4`.

**Atrás:** el botón único del andamiaje (`nav_back`) se dibuja en la esquina superior inicial del panel.

**Ranura de apoyo:**
- Va bajo la meta.
- Cabe sin desplazar a 891 × 411 y a 800 × 360 al 100 % en todos los centros; ver los presupuestos de cada área.
- Si no cabe (130 %), **todo el panel** desplaza.

**Lado del contenido:**
- Desplaza por su cuenta, desde `S + 12`.
- Sin barra de título al desplazar (`app_bar_title` solo se compone en vertical): el título siempre está a la vista en el panel.
- Bajo la barra de estado:
  - Lumen y Cristal: el contenido se funde con una máscara de 24 dp;
  - Tradicional y Sanctuary: pasa bajo una franja `canvasTop` al 95 % con filete.

### 3.3 Las otras colocaciones

| Colocación | Cuándo | Aspecto | Alto a 411 / 360 |
|---|---|---|---|
| `SidePanel` sin arte («panel de título») | `AppLargeTitleHeader`, o un detalle sin arte (artículo del Catecismo, Compendio) | <ul><li>mismo panel</li><li>título en la misma línea, sobre el lienzo</li></ul> | — |
| `CompactBand` («banda compacta») | <ul><li>**respaldo automático** de cualquier pantalla aún no migrada a `AppPageLayout`</li><li>`CompactPhone`</li></ul> | <ul><li>al principio del contenido, **desplaza con él**</li><li>tira de arte de `S + 84` con el título en su borde inferior − 14 (`largeTitle`: 18 + 40 + 14 = 72 ≤ 84)</li><li>sin arte: `S + 8` + bloque de título</li></ul> | **108 (26 %)** / **108 (30 %)** |
| `DetailPane` («cabecera de ficha») | detalle de una lista-detalle en `LandscapePhone` | <ul><li>fila: miniatura 112 × 112 (retratos: 104 × 130), recortada al foco, radio `thumbnail` (Sanctuary: marco)</li><li>a su lado: antetítulo, título `title1` y meta</li><li>debajo, las acciones</li></ul> | 130–150 |

**El respaldo `CompactBand` es la primera entrega de L0.** En cuanto L0 se fusiona, toda raíz baja de 236 + `S` a 108 dp de cabezal aunque su área no se haya rediseñado. El test de L0 lo mide con `S` = 24.

### 3.4 Wrappers de página: `AppPageLayout` y `AppSupportingPaneLayout`

Los dos están en `AppComponents.kt`. **Son la única forma de tener panel en horizontal.** Ni el Rosario ni la Biblia ni ninguna otra pantalla maquetan paneles a mano.

```kotlin
enum class AppPageKind { Hub, Today, Reader, Immersive }

@Composable
fun AppPageLayout(
    header: @Composable () -> Unit,                           // la llamada de hoy a AppHeroHeader/AppDetailHeader, intacta
    modifier: Modifier = Modifier,
    kind: AppPageKind = AppPageKind.Hub,
    supporting: (@Composable ColumnScope.() -> Unit)? = null, // panel en horizontal; tras el cabezal en vertical
    supportingInPortrait: Boolean = true,
    panelFooter: (@Composable RowScope.() -> Unit)? = null,   // pie fijo del panel (lectores: Escuchar · Aa)
    contentFooter: (@Composable RowScope.() -> Unit)? = null, // pie fijo del contenido (lectores: ‹ Anterior … Siguiente ›)
    content: @Composable (headerItem: @Composable () -> Unit) -> Unit
)

@Composable
fun AppSupportingPaneLayout(
    supporting: @Composable () -> Unit,          // panel de apoyo (cuentas del Rosario, estudio de la Biblia)
    modifier: Modifier = Modifier,
    supportingSide: AppPaneSide = AppPaneSide.Start,
    supportingWidth: AppPaneWidth,               // regla clamp(fracción · W, mín, máx) declarada, no un Dp suelto
    showSupportingPane: Boolean = true,          // false: el apoyo se abre como hoja lateral (Biblia < 860)
    endActions: (@Composable ColumnScope.() -> Unit)? = null,  // grupo vertical en el borde derecho (Rosario)
    supportingFooter: (@Composable RowScope.() -> Unit)? = null,
    mainFooter: (@Composable RowScope.() -> Unit)? = null,
    main: @Composable () -> Unit
)
```

Las dos se comportan así:
- **Fuera de `LandscapePhone`:** se componen como hoy. `AppPageLayout` pone el cabezal y la ranura como primeros elementos de la lista. `AppSupportingPaneLayout` dibuja `main` y deja a la pantalla su disposición vertical actual.
- **En `LandscapePhone`:**
  - cada ranura se compone **una sola vez** y una política de `Layout` la coloca (§2.4);
  - `kind = Reader | Immersive` y `AppSupportingPaneLayout` ocultan el riel (`AppHideTabBar`, §3.5);
  - declaran `AppFullWidth()`;
  - el panel y el contenido son grupos de recorrido (`isTraversalGroup`), panel primero.
- **Estado:** ninguna ranura guarda estado propio; ver §2.4 regla 2.

Para migrar, cada pantalla:
- cambia `item { AppHeroHeader(…) }` por `item { headerItem() }`;
- envuelve su cuerpo en el wrapper;
- mueve a `supporting` lo que hoy va tras el cabezal (segmentos, buscadores), con su estado elevado.

Son 34 cabezales en 19 archivos.

### 3.5 Riel compacto

`LumenKit.Rail`, `GlassRail` y `FolioRail` tienen una variante compacta para `LandscapePhone`:

| | T-043 (tabletas) | Compacto (`LandscapePhone`) |
|---|---|---|
| Reserva de ancho | 92 (Sanctuary 88) | **80** (Sanctuary 76) + `safeStart` |
| Cápsula | 72 | 64 |
| Alto por destino, etiqueta de 1 línea | 68 | **56** (4 + icono 24 + 3 + 16 + 8 + margen) |
| Alto por destino, etiqueta de 2 líneas | 68 + | **72** |
| **4 destinos** (hasta T-046): Hoy · Orar · Leer · Explorar | 280 | 4 × 56 + 8 = **232** |
| **5 destinos** (con T-046): Hoy · Orar · Leer · Mi camino · Explorar | 348 | <ul><li>1 línea: 5 × 56 + 8 = 288</li><li>con «Mi camino» o «Meu caminho» en 2 líneas: 4 × 56 + 72 + 8 = **304**</li></ul> |
| Alto útil del riel (`H − S − B`) | — | 371 (411) / **320** (360): **cabe** con 16 dp de margen en el peor caso |
| Etiquetas al 100 % | completas | <ul><li>**completas**, hasta 2 líneas, nunca truncadas</li><li>el presupuesto de arriba ya cuenta la 2.ª línea del destino más largo</li></ul> |
| Etiquetas al 130 % (listón duro) | — | <ul><li>cortas oficiales: «Hoy · Orar · Leer · Camino · Más», «Today · Pray · Read · Path · More», «Hoje · Orar · Ler · Caminho · Mais», con el mismo mecanismo que hoy «Más»</li><li>si aun así no cabe, el riel desplaza dentro de su cápsula, con el destino activo visible</li></ul> |
| Posición | centrado | centrado vertical entre `S` y `H − B` |

- Las etiquetas de test no cambian: `navigation_rail`, `nav_*`, `nav_my_path` (T-046).
- **Pantallas inmersivas.** En `LandscapePhone`, el riel **respeta `AppHideTabBar`**, como la barra inferior en vertical:
  - se oculta en el Rosario, los lectores (§5), la Biblia y Confesión;
  - en las tabletas no cambia la decisión de T-043.
  - *Decisión vetable (§14).*

### 3.6 Hojas laterales

En `LandscapePhone` y `CompactPhone`, `AppSheet` (en `LumenExtras.kt`) se dibuja como **hoja lateral modal** anclada al borde final. Las 31 llamadas cambian con un solo cambio en el wrapper.

| | Valor | 891 | 800 |
|---|---|---|---|
| Ancho normal | `clamp(0,50 · W, 360, 480)` | 446 | 400 |
| Ancho amplio (`wide = true`, nuevo) | `clamp(0,66 · W, 480, 640)` | 588 | 528 |
| Alto | <ul><li>de `S + 8` a `H − B − 8`</li><li>Sanctuary: a todo el alto, pegada al borde</li></ul> | 355 | 304 |
| Cabecera fija | <ul><li>56: título (`headline`) + **«Cerrar»** de 44 (`sheet_close`, nueva)</li><li>las hojas que hoy tienen «Listo» (Fuente) lo conservan **en lugar** de «Cerrar»</li></ul> | | |
| `footer` (nuevo, opcional) | barra fija de 56 con la acción principal | | |
| **Con el teclado abierto** | <ul><li>el `footer` se oculta y **su acción pasa a la cabecera**, en lugar de «Cerrar» (que sigue disponible con atrás y con el velo)</li><li>espacio del campo: 157 − 8 − 56 = 93 (891) y 134 − 8 − 56 = 70 (800), ≥ 56 en ambos</li></ul> | | |
| Entrada y cierre | <ul><li>entra desde el borde final</li><li>se cierra con el velo, atrás (predictivo), deslizando hacia el borde o con «Cerrar»</li></ul> | | |

- La etiqueta de cada hoja sigue en su contenedor.
- `paneTitle` = título de la hoja. El foco de TalkBack entra en la hoja al abrirla y vuelve al control que la abrió al cerrarla.

### 3.7 Diálogos de dos columnas

`AppDialog` en `LandscapePhone` y `CompactPhone`:
- ancho `min(W − 2·(safe + 24), 720)`;
- alto máximo `H − S − B − 32` (339 / 288);
- centrado.

El parámetro nuevo `aside` define dos columnas: contexto a la izquierda (0,42) y elección a la derecha, que desplaza. Las acciones van fijas al pie de la columna derecha.

```
┌──────────────────────────────── 720 ─────────────────────────────────┐
│ CONTEXTO · fijo                        │ ELECCIÓN · desplaza           │
│ Título (title2)                        │ opciones y campos             │
│ texto explicativo, ilustración         │ ───────────────────────────── │
│                                        │      [Secundaria] [PRINCIPAL] │
└────────────────────────────────────────┴───────────────────────────────┘
```

`AppConfirmDialog`: una columna de 420; el mensaje desplaza si no cabe y la fila de botones queda fija.

### 3.8 Rejillas y carriles

Helpers nuevos:
- **`appGridColumns(minCell, max)`:** `floor((interior + 12) / (minCell · k + 12))`, con `k = 1`, o `k = 1,25` si `appLargeText()`.
- **`AppFeedColumns(lanes)`:** carriles con **asignación explícita** de elementos; el orden de lectura es carril A completo y luego carril B.

| Celda | `minCell` | Centro 891 (interior 479) | Centro 800 (interior 421) | Máximo |
|---|---|---|---|---|
| Tarjeta de imagen | 128 | 3 | 3 | 4 |
| Losa de categoría | 112 | 3 | 3 | 4 |
| Fila compacta en rejilla | 220 | 2 | 1 | 3 |
| Fila de resultado (Búsqueda, a todo el ancho; interior 771 / 680) | 300 | 2 | 2 | 3 |
| Carril (Hoy y Mi camino; interior 544 / 472) | 228 | 2 (264 c/u) | 2 (228 c/u) | 3 (tableta) |

Al 130 % se pierde una columna donde la fórmula lo dé (listón duro: nada se corta).

### 3.9 `AppTwoPane` con altura compacta

| | `TabletLandscape` (T-043) | `LandscapePhone` |
|---|---|---|
| Panel de lista | 360 | `clamp(0,36 · Wc, 272, 320)`: **292 / 272** |
| Panel de detalle | resto, centrado a 680 | resto: 519 / 448 |
| Cabezal de la lista | `AppHeroHeader` con arte | <ul><li>**tira de arte de 96 dp** con antetítulo y título (`largeTitle` o `title1`, misma regla de §3.2) en su borde inferior;</li><li>**desaparece al desplazar** la lista</li></ul> |
| Barra adhesiva de la lista | — | <ul><li>**≤ 56 dp:** búsqueda (campo de 44) + **botón de filtro** de 44 (`list_filter_button`, nueva), que abre una hoja lateral con los filtros de hoy (mes, «solo con ficha», segmento…)</li><li>si hay filtros activos, aparece debajo una fila de 44 dp con chips para quitarlos</li></ul> |
| Cabezal del detalle | `AppDetailHeader` con arte de 236 | `DetailPane` (§3.3) |
| Separador | 0,5 | 0,5 (Sanctuary: lomo doble) |

- **Selección explícita o por defecto.** Sin selección, se abre la primera entrada (T-043). Al girar a vertical:
  - con selección explícita, la entrada pasa a ser una página apilada;
  - con selección por defecto, se vuelve a la **lista**.
  - Para el paso contrario (de vertical a horizontal), ver §2.4.
- **Medida:** el texto del detalle usa `appReadingMeasure()`. Las fichas de lectura larga (artículo del Catecismo, respuestas extensas) ofrecen «Leer a pantalla completa» (`detail_open_reader`, nueva), que abre el lector L2.

**Filas visibles en reposo**, con la tira y la barra, en el orden por defecto de cada lista y con los nombres ES y PT:
- a 891: 371 − 96 − 56 = 219 dp. Ejemplo de enero en Santos: 108 + 64 + 86 = 258, así que en reposo se ven **2 completas y el inicio de la 3.ª**.
- **Tras desplazar la tira** (el uso real): 315 dp → **≥ 3 filas completas**.
- a 800: 320 − 152 = 168 en reposo y 264 sin la tira → **≥ 2 filas completas** sin la tira.

Este es el criterio de L5 (§8.3).

### 3.10 Medida de lectura

**`appReadingMeasure()`** mide con `TextMeasurer` el ancho de **66 caracteres** de una frase de referencia del idioma activo, en el **estilo `reading` efectivo** del usuario (fuente elegida × escala de la app × `fontScale` del sistema). El rango aceptado es de 60 a 75 caracteres. No se usa un 0,5 em fijo. La columna de texto mide `min(ancho disponible, medida)` y va centrada en su panel.

Medida con las fuentes de la app (texto de Juan 1, ES y PT):

| Estilo | dp por carácter | 60 car. | **66 car.** | 75 car. |
|---|---|---|---|---|
| EB Garamond 19/30 (predeterminada, serif) | 6,7–7,1 | 401–426 | **441–468** | 501–532 |
| Inter 18/28 (sans) | 8,0–8,5 | 481–513 | **529–564** | 601–641 |

| Lector | Ancho disponible (891 / 800) | Columna serif | Columna sans |
|---|---|---|---|
| Lector L2 (panel 267 / 240, sin riel) | 576 / 512 | 455 / 455 → **66 car.** | 545 → 66 / 512 → 62 |
| Biblia con estudio (≥ 860) | 504 / — | 455 → 66 | 504 → 61 |
| Biblia sin estudio (< 860) | — / 752 | 455 → 66 | 545 → 66 |
| Ficha de lista-detalle | 479 / 408 | 455 → 66 / 408 → 59 | 479 → 58 / 408 → 49 |
| Rosario, texto de la oración (§4) | 462–490 / 400–428 | 65–73 / 57–62 | — |

**Umbrales** (rúbrica 3, al 100 %):
- lectores y Biblia: 60–75 caracteres con cualquier fuente;
- fichas de lista-detalle: ≥ 56 con serif y ≥ 45 con sans, más «Leer a pantalla completa»;
- Rosario: ≥ 56.

Al 130 % rige solo el listón duro.

**`rememberReadingAnchor()`** guarda el índice del primer bloque visible y su fracción, y restaura por ancla al girar.

### 3.11 Espaciado y márgenes

| Token | Valor |
|---|---|
| Margen exterior | `gutter` de la identidad (20) + `safeStart` o `safeEnd` |
| Margen interior de panel | 20 |
| Separación entre carriles | 16 |
| Separación entre celdas | 12 |
| Separación entre secciones | `sectionGap` (28) |
| Inicio del contenido | `S + 12` (Rosario: `S + 4`, §4) |
| Fin del contenido | `B + 24`, o sobre el pie fijo |
| Pies fijos (lectores, hojas) | 56 + `B` |

Todo va en múltiplos de 4. Los inicios de texto se alinean con los márgenes (±1 dp).

### 3.12 Segmentos en el panel

En `SidePanel`, `AppSegmentedTabs` se dibuja como **lista de secciones**:
- filas de 48, con el material seleccionado de cada identidad;
- `Role.Tab` y `selected`;
- se conservan la etiqueta del contenedor (`prayers_segments`, `confession_tabs`…) y los textos.

Fuera del panel sigue horizontal.

### 3.13 Archivos y tests de L0

| Archivo | Cambio |
|---|---|
| `ui/design/AppComponents.kt` | <ul><li>`AppWindowClass` (con `CompactPhone`) y `appWindowClass()` desde `LocalWindowInfo.containerSize` o `androidx.window`.</li><li>`appIsLandscapePhone()`.</li><li>`AppPageLayout` y `AppSupportingPaneLayout`.</li><li>`appGridColumns`, `AppFeedColumns`, `appReadingMeasure`, `rememberReadingAnchor`.</li><li>`AppTwoPane` con barra adhesiva y tira.</li></ul> |
| `ui/design/LumenComponents.kt` | <ul><li>`LocalHeaderPlacement` y las colocaciones.</li><li>Variante póster.</li><li>Prueba del título contra `constraints`.</li><li>`AppGrid` con `appGridColumns`.</li></ul> |
| `ui/design/LumenKit.kt` | <ul><li>Riel compacto.</li><li>`AppHideTabBar` en `LandscapePhone`.</li><li>Insets laterales.</li><li>Atrás en el panel.</li><li>Sin barra de título en las pantallas con panel.</li><li>Segmentos verticales.</li></ul> |
| `ui/design/LumenExtras.kt` | <ul><li>`AppSheet` lateral (`wide`, `footer`, acción en la cabecera con teclado).</li><li>`AppDialog` con `aside`.</li><li>`AppConfirmDialog` desplazable.</li><li>`AppNotice` (§3.14).</li></ul> |
| `ui/design/LumenFoundation.kt` | `appSafeInsets()`, `ScreenFrame.insets` con `start` y `end` |
| `ui/design/LiquidGlass.kt` | `Modifier.fadeEnd` |
| `ui/MainApp.kt` | <ul><li>Columna centrada de 680 solo para las pantallas no migradas, que llevan `CompactBand` dentro: nunca se estiran a 811.</li><li>Etiquetas cortas del riel al 130 %.</li></ul> |
| `app/build.gradle.kts` | `androidx.window:window` si se usa `WindowMetricsCalculator` |
| `FullAppScreenshotTest.kt` | <ul><li>Alto mínimo de 320.</li><li>Calificador `land`.</li><li>`-Pshots.landscape=true` (891×411 y 800×360).</li><li>**Insets de un teléfono real por defecto en horizontal: `S = 24`, `B = 16`, gestos laterales de 32.</li><li>`-Pshots.ime`: 0,56 H por defecto si se pide.</li><li>`-Pshots.cutout=left` o `=right` (32).</li><li>`-Pshots.navbar=left` o `=right` (48).</li></ul> |

Tests nuevos:
- **Arnés de insets** (helper de test): despacha `WindowInsetsCompat` con `statusBars` 24, `navigationBars` 16 abajo (o 48 en un lateral), `systemGestures` de 32 en los laterales, `displayCutout` y `ime` al `ComposeView`. **Todos los tests de horizontal lo usan.** Ninguna validación se hace con `S = 0`.
- **`LandscapeLayoutTest`:**
  - la clase de ventana a partir de `containerSize`;
  - el riel de 4 y de 5 destinos (≤ 320 a 360);
  - el ancho de los paneles y la línea del título;
  - `CompactBand` ≤ 30 % de `H`;
  - la hoja lateral y su cambio con el teclado;
  - los diálogos centrados.
- **`LandscapeAuditTest`:** `NarrowLargeTextAuditTest` a 891×411 y 800×360, al 100 % y al 130 %, en ES y PT, con estos detectores:
  - `HEADER_TOO_TALL`;
  - `ESSENTIAL_BELOW_FOLD`, con la lista por área de §4–§10 y §15;
  - `MEASURE`;
  - `UNDER_INSET`;
  - `IME_HIDDEN`;
  - `SHEET_GEOMETRY`;
  - `OBSCURED`: un control que tapa texto.
- **`RosaryFitTest`:** `TextLayoutResult` de Padrenuestro, Avemaría, Gloria y «Oh Jesús mío» en ES, EN y PT, a 891×411 y 800×360, con y sin guía. Comprueba el desplazamiento esperado de la tabla de §4.3: 0 salvo las excepciones listadas.
- **`LandscapeRotationTest`:** cada fila de §2.4, con texto escrito en la búsqueda, un segmento cambiado y una ficha apilada que pasa a selección.
- **Lint de estado:** ningún `rememberSaveable` dentro de las lambdas `header` y `supporting`.
- **Paridad de nodos:** el mismo conjunto de textos y etiquetas en vertical y en horizontal por pantalla.

### 3.14 Avisos breves

Los avisos transitorios (confirmaciones, «guardado», errores de red; hoy son textos o banners de cada pantalla) se dibujan con un wrapper **`AppNotice`**, en `LumenExtras.kt`:
- **Posición:** anclado **sobre el pie fijo de la columna activa** (la que el usuario está usando), o sobre `B + 8` si no hay pie.
- **Tamaño:** alineado al final de esa columna, con 480 dp de ancho como máximo.
- **Accesibilidad:** `liveRegion = Polite`.
- **Restricciones:** nunca tapa la acción que avanza, la cabecera de una hoja ni el campo con foco. Con el teclado abierto, se ancla sobre el teclado.

### 3.15 Criterios de aceptación de L0

1. `appWindowClass()` es la única fuente y se calcula desde la ventana completa (test con `containerSize`).
2. **Sin migrar ninguna pantalla**, todas las raíces muestran `CompactBand` de 108 dp (`S = 24`), todas las hojas son laterales y todos los diálogos están centrados con sus acciones visibles.
3. El riel de 4 destinos, y el de 5 una vez integrado T-046, caben a 800 × 360 al 100 %. Al 130 % rige el listón duro.
4. Cero `UNDER_INSET` con recorte de 32 a cada lado y barra de tres botones de 48 a cada lado.
5. Los tests de interfaz que cita AGENTS.md pasan sin cambiar ninguna etiqueta.
6. El vertical y las tabletas no cambian, salvo lo declarado (capturas de regresión).

---

## 4. L1 · Rosario y Coronilla

**Patrón:** panel de apoyo inmersivo, con `AppSupportingPaneLayout(supportingSide = Start, endActions = …)` (§3.4). No se maqueta a mano. **Pantalla:** `RosaryScreen` (destino `ROSARY`), también para la Coronilla.

**Idea:** un folio de dos caras sostenido con las dos manos.
- La cara izquierda dice **dónde estás**: las cuentas, el misterio, el progreso y «Anterior», bajo el pulgar izquierdo.
- La cara derecha dice **qué rezas**: la oración entera, con «Siguiente» bajo el pulgar derecho.

### 4.1 Boceto a 891 × 411 (sin riel; `S` = 24, `B` = 16)

```
 0                             285                                                        891
┌────────────────────────────────┬─────────────────────────────────────────────────────────┐ 0
│ (S)                            │ (S)                                                     │ 24
│ ‹  [ Rosario | Coronilla ]     │  Avemaría                       3 de 10        [Guía]   │ 28–60  título (fijo)
│ ┊  Primer misterio gozoso      │ ┌─────────────────────────────────────────────┐ [···]   │        guía (si activa)
│ ●  La Anunciación              │ │ Un Ave María en cada una de las diez cuentas│         │
│ ┊  ┌──────────────────────┐    │ │ pequeñas… · V. dice quien guía · R. …       │         │
│ ●  │ pintura (16:9,       │    │ └─────────────────────────────────────────────┘         │
│ ┊  │ recortada al foco)   │    │ V. Dios te salve, María, llena eres de gracia; (Escuchar)│ y≈0,45H
│(◉) └──────────────────────┘    │    el Señor es contigo. Bendita tú eres entre           │
│ ┊  Fruto: la humildad ···      │    todas las mujeres, y bendito es el fruto de          │
│ ○  (desplaza: chip, Ver el     │    tu vientre, Jesús.                     ┌──────────┐  │
│ ┊   misterio)                  │ R. Santa María, Madre de Dios, ruega por  │SIGUIENTE │  │ y≈0,70H
│ ○  Después · Gloria            │    nosotros, pecadores, ahora y en la     │  64×64   │  │
│    ▬▬▬▬▬▬▬▬ Paso 12 de 85      │    hora de nuestra muerte. Amén.          └──────────┘  │
│ [⏮ Anterior]                   │                                                         │ 395 = H − B
└────────────────────────────────┴─────────────────────────────────────────────────────────┘ 411
  PANEL DE LAS CUENTAS 285          ORACIÓN · texto 490 (462 con recorte a la derecha)  GRUPO 72
  hilo 44 · contexto 181            (desplaza solo en los casos de §4.3)                 fijo
```

### 4.2 Geometría cerrada

**Panel de las cuentas:**
- Ancho: `clamp(0,32 W, 256, 340)`: **285** a 891 y **256** a 800.
- **Fila 1** (y = `S` … `S + 44`):
  - atrás (`nav_back`, 44) en `x = max(16, safeStart + 8)`;
  - a su derecha, el selector `Rosario | Coronilla` (`DevotionSwitch`), de 205 / 176 de ancho: 2 segmentos de 102 / 88, en los que caben «Coronilla», «Coroinha» y «Chaplet».
  - *Es el único lugar del selector, en el boceto y en la especificación.*
- **Bloque inferior fijo**, desde `H − B` hacia arriba, con huecos de 6:
  - **«Anterior»** (`prev_bead_button` / `prev_mercy_step_button`): cápsula de 48 de alto y ≥ 120 de ancho, con icono y rótulo, en `x = 40`. Está bajo el pulgar izquierdo.
  - **Progreso** (`page_dots`, `rosary_hero_step_counter` / `mercy_hero_step_counter`): 32 de alto. Al tocarlo se abren las Opciones.
  - **«Después · …»**: 18 de alto.
  - Total: 48 + 6 + 32 + 6 + 18 + 6 = **116**.
- **Región central:** de `S + 52 = 76` a `H − B − 116`: **203** (891) / **152** (800). La ocupan, uno junto al otro:
  - **Hilo de cuentas:**
    - `x = max(safeStart, gestos laterales) + 8` = **40**, ancho **44**;
    - con gestos de 32 empieza fuera de la zona de gesto: no hace falta exclusión;
    - con gestos mayores, la fórmula lo desplaza.
  - **Columna de contexto:**
    - de `x = 92` a `W_L − 12`: **181** (891) / **152** (800);
    - desplaza sola, con fundido de 16 dp e indicador de desplazamiento.

**Columna de contexto**, de arriba abajo:
1. **Antetítulo** («Primer misterio gozoso»; en el inicio, «Inicio»): 18.
2. **Título** (`rosary_hero_title` / `mercy_hero_title`, `headline`): el misterio. En las tres Avemarías iniciales es la **intención** («Por el aumento de la fe»), que sale del panel de la oración. Medido con los títulos reales: ≤ 4 líneas = 88 (3 líneas en la mayoría: 66).
3. **Pintura** (`rosary_hero_photo`, `mystery_hero_photo`):
   - con `H ≥ 400`: 181 × 102 (16:9);
   - con `H < 400`: **miniatura** en banda de 152 × 56.
   - Va recortada al foco, con radio `thumbnail` (Sanctuary: marco). La pintura completa está en «Ver el misterio».
4. **Fruto.** En el paso «Anuncio del misterio», aquí van **la meditación y la cita** (`detail`), que salen del panel de la oración.
5. **Selector de misterios** (`rosary_mystery_picker`) y **«Ver el misterio»** (44).

Sin desplazar se ven:
- a 891: antetítulo, título y pintura (18 + 66 + 8 + 102 = 194 ≤ 203). Con títulos de 4 líneas, la pintura asoma 89 de 102.
- a 800: antetítulo, título y banda (18 + 66 + 8 + 56 = 148 ≤ 152). Con títulos de 4 líneas, la banda asoma 38 de 56.

**Antetítulo y título (lo esencial) se ven siempre:** 106 ≤ 152.

**Hilo de cuentas** (`BeadReel(orientation = Vertical)`, en `components/PrayerBeadCounter.kt`):
- **Posición y aspecto:**
  - la cuenta en mano va en el centro vertical de la región, mayor y con el anillo del acento litúrgico;
  - las rezadas, **arriba** (llenas); las que faltan, **abajo** (pálidas);
  - los puntos de subpaso van a su derecha (dentro de los 44 dp).
  - Paso entre cuentas: **24 dp**. Se ven ≈ 8 cuentas a 891 y ≈ 6 a 800.
- **Arrastre:**
  - **arrastrar hacia arriba avanza** (se tira de las cuentas hacia la mano, como entre los dedos) y **hacia abajo retrocede**;
  - **24 dp por cuenta**, con un tic háptico (`CLOCK_TICK`) en cada una y el háptico firme al cerrar una decena;
  - al soltar, encaja en la cuenta más cercana; no hay inercia.
- **Toque:** tocar una cuenta va a su primer paso. El hilo entero es un solo control de 44 de ancho por toda la región.
- **Semántica y etiquetas:** `prayer_bead_counter` / `mercy_step_counter`, con `contentDescription`, `stateDescription` y las acciones «Anterior» y «Siguiente».

**Panel de la oración:**
- Desde `W_L` hasta el grupo de acciones.
- **Texto:** de `W_L + 20` a `W − 72 − 12 − max(12, safeEnd + 8)`:
  - 891: **490**, o **462** con recorte de 32 a la derecha;
  - 800: **428** / **400**.
- **Arriba (`S + 4`):** fila del título (`current_prayer_title`, `title2`, con la cuenta «3 de 10» y `skip_optional_button`): 32. Debajo, un hueco de 8.
- **Guía** (`prayer_instructions_text`, `GuideHint`), si está activa, + 6.
- **Texto de la oración** (`prayer_content_scroll`, `prayer_leader_text`, V./R. con 10 de separación, el ×1,12 de las oraciones cortas):
  - llega hasta `H − B`;
  - desplaza **solo** en los casos de §4.3, con fundido de 24 dp e indicador.
- **«Oración opcional»** (letanías) va sobre el texto. Esas oraciones son largas y desplazan.

**Grupo de acciones del borde derecho** (`endActions`), de 72 de ancho, en `x = W − max(12, safeEnd + 8) − 72`:
- **Arriba:** «Guía» (`prayer_instructions_toggle`) y «···» (`rosary_options_button`), de 44 cada uno, en `S + 4`.
- **Escuchar** (`toggle_prayer_audio_button` / `toggle_mercy_audio_button`): círculo de 48 + rótulo, centrado en **y = 0,45 H** (185 / 162).
- **Siguiente** (`next_bead_button` / `next_mercy_step_button`): tecla de **64 × 64** + rótulo «Siguiente», centrada en **y = 0,70 H** (288 / 252):
  - su centro queda a **48 dp** del borde derecho seguro;
  - acaba en 336 ≤ 395 (891) y en 300 ≤ 344 (800).

### 4.3 Presupuesto de la oración (al 100 %, `S` = 24, `B` = 16)

Alto disponible para guía + texto = `H − B − (S + 4) − 32 − 8`:
- **327** a 891 × 411;
- **276** a 800 × 360.

Ya no van en el panel de la oración «Después», el progreso, la intención inicial ni la meditación del anuncio.

**Medición** con EB Garamond 19/30 (estilo `reading` predeterminado) y la guía en Inter 15/13.
- Cada celda: líneas, alto del texto (con el hueco V./R. de 10 y el ×1,12 si es corta) y, tras «→», el alto con la guía (+ tarjeta + 6).
- ✔ cabe sin desplazar; ✘ desplaza.

| 891 × 411 (texto 462–490) | ES | EN | PT |
|---|---|---|---|
| Padrenuestro | 6 L · 190 → 298 ✔ | 5 L · 160 → 268 ✔ | 6 L · 190 → 298 ✔ |
| Avemaría | 5 L · 160 → 268 ✔ | 4 L · 130 → 238 ✔ | 4 L · 130 → 238 ✔ |
| Gloria | 3 L · 111 → 219 ✔ | 4 L · 144 → 252 ✔ | 3 L · 111 → 219 ✔ |
| Oh Jesús mío | 3 L · 90 → 198 ✔ | 3 L · 101 → 209 ✔ | 3 L · 101 → 209 ✔ |

| 800 × 360 (texto 400–428) | ES | EN | PT |
|---|---|---|---|
| Padrenuestro | 7 L · 220 ✔ / con guía 328 ✘ | 6–7 L · 190–220 ✔ / con guía 298–328 ✘ | 7 L · 220 ✔ / con guía 328 ✘ |
| Avemaría | 5 L · 160 → 268 ✔ | 5 L · 160 → 268 ✔ | 5 L · 160 → 268 ✔ |
| Gloria | 3 L · 111 → 219 ✔ | 4 L · 144 → 252 ✔ | 3 L · 111 → 219 ✔ |
| Oh Jesús mío | 3 L · 90 → 198 ✔ | 3 L · 101 → 209 ✔ | 3 L · 101 → 209 ✔ |

**Resultado:**
- **891 × 411:** las 24 combinaciones (4 oraciones × 3 idiomas × guía sí/no) **sin desplazar**. Margen mínimo: 29 dp.
- **800 × 360 sin guía:** las 12 **sin desplazar**. Margen mínimo: 56 dp.
- **800 × 360 con guía:** Avemaría, Gloria y «Oh Jesús mío» sin desplazar (margen mínimo 8 dp).
  - **Excepción única:** el **Padrenuestro con la guía activa** (6 pasos de 85) desplaza 22–52 dp (1–2 líneas), con fundido e indicador. «Siguiente» no se mueve.
  - Alternativas medidas y descartadas en §17.1.
- **Coronilla:** «Eterno Padre» (≈ 180 caracteres) y «Por su dolorosa Pasión» (corta) se miden en el mismo test y tienen que caber sin desplazar en las 12 combinaciones de 891 × 411 y 800 × 360 sin guía.
- **Siempre desplazan** (largas por naturaleza, con pista): el Credo, la Salve, las letanías, la oración final y el «Anuncio del misterio» de la Coronilla, si lo hubiera.
- **130 % (listón duro):** todo puede desplazar con pista; el hilo, el título, «Anterior», «Escuchar» y «Siguiente» quedan fijos.

`RosaryFitTest` (§3.13) convierte esta tabla en un test con `TextLayoutResult`. Si una celda ✔ falla en Android por métricas distintas, se corrige el diseño, no el test.

### 4.4 Hojas

- **Misterios** (`rosary_mysteries_sheet`): lateral normal (446).
- **Opciones** (`rosary_options_sheet`): lateral **amplia** (588), con dos columnas:
  - izquierda: «Voces» como **lista vertical** de 3 filas de 48 («Ambas voces», «Guía», «Respuesta»), que es la misma `rosary_voice_mode_picker` en forma de lista (§3.12), y su explicación debajo;
  - derecha: `rosary_loop_map` y la lista de partes.
- **Ver el misterio** (`rosary_mystery_sheet`): amplia, con dos columnas:
  - izquierda: la pintura enmarcada completa y el crédito;
  - derecha: la cita, la meditación y el fruto.

### 4.5 Giro, recorte, 130 % e identidades

- **Giro:** se conservan la sesión, la hoja abierta (`rememberSaveable`), la voz activa (sin `ScreenLeft` al cambiar de configuración) y el ancla del texto.
- **Recorte de cámara:**
  - a la izquierda, el hilo y «Anterior» se apartan;
  - a la derecha, el grupo se aparta y el texto queda en 462 / 400 (ya medido en §4.3).
- **130 %:** listón duro (§4.3).
- **Identidades:**
  - **Lumen:** bruma tras ambos paneles y grupo de cristal.
  - **Tradicional:** grupo opaco con filete; Siguiente sólido `action`.
  - **Cristal:** el panel de la oración refracta la pintura.
  - **Sanctuary:** pintura enmarcada, lomo doble entre paneles, cuentas en granate y título en EB Garamond.

### 4.6 Criterios de aceptación de L1

1. Geometría de §4.2 a 891×411 y a 800×360, en las 4 identidades, en claro y oscuro, con el arnés de insets (S = 24, B = 16, gestos de 32), y con un recorte de 32 a cada lado.
2. `RosaryFitTest` verde según la tabla de §4.3.
3. `next_bead_button`: centro a ≤ 120 del borde derecho seguro, `y ≥ 0,6 H`, ≥ 64 × 64.
4. El hilo cumple §4.2: arrastre de 24 dp por cuenta y dirección correcta (`RosaryInteractionTest` ampliado).
5. Al girar a mitad de la 3.ª decena con la voz activa y «Opciones» abierta, todo sigue igual (`LandscapeRotationTest`, `RosaryNarrationTest`, `RosaryPersistenceTest`).
6. TalkBack recorre: atrás → selector → antetítulo y título → pintura → fruto → selector de misterios → hilo → «Después» → progreso → «Anterior» → título de la oración → guía → texto → «Guía», «···» → Escuchar → Siguiente.

**Archivos:**
- `ui/screens/RosaryScreen.kt`: la rama `wide` se sustituye por `AppSupportingPaneLayout`.
- `ui/components/PrayerBeadCounter.kt`: `BeadReel` vertical.
- `ui/screens/RosaryNarration.kt`.
- `ui/design/AppComponents.kt`: el wrapper, de L0.

---

## 5. L2 · Lectores

**Patrón:** panel de apoyo, con `AppPageLayout(kind = Reader)`. Sin riel.

### 5.1 Plantilla común, a 891 × 411

```
 0                           267                                                                 891
┌──────────────────────────────┬──────────────────────────────────────────────────────────────────┐ 0
│ (S) ‹                    ☆ ⋯ │ (S) · · · · · fundido bajo la barra de estado · · · · · · · · · · │
│ ╔══════════════════════════╗ │                                                                  │
│ ║ ARTE (si lo hay), bruma  ║ │         ┌────── columna de lectura 455 (66 car.) ──────┐         │
│ ║ Antetítulo               ║ │         │ Dios te salve, María, llena eres de gracia;  │         │
│ ║ Título (title1)          ║ │         │ el Señor es contigo. Bendita tú eres entre   │         │
│ ╚══════════════════════════╝ │         │ todas las mujeres, y bendito es el fruto de  │         │
│  meta                        │         │ tu vientre, Jesús. …                         │         │
│  [ Evangelio ▾ ]  ← índice   │         └──────────────────────────────────────────────┘         │
│  (selector propio: novena…)  │ ───────────────────────────────────────────────────────────────── │
│ [▶ Escuchar]  [Aa]           │  [‹ Anterior]                                    [ Siguiente › ] │ ← pie de texto
└──────────────────────────────┴──────────────────────────────────────────────────────────────────┘ 411
  PANEL DEL LECTOR 267 · fijo           PANEL DE TEXTO 624 · desplaza, con pie fijo (solo lectores secuenciales)
  pie: Escuchar · Aa
```

**Panel del lector** (`clamp(0,30 W, 224, 280)`: 267 / 240), de arriba abajo:
1. Atrás y acciones (marcador, más).
2. Zona de arte (§3.2) o panel de título.
3. Antetítulo, título (`title1`) y meta.
4. **Índice en una fila de 44 dp** (`reader_outline_row`, nueva):
   - muestra la parte actual («Evangelio ▾») y se actualiza con el desplazamiento;
   - al tocarla, abre la lista de partes en una hoja lateral (`reader_outline_sheet`, nueva);
   - la parte actual va marcada `selected` y al elegir otra el texto se desplaza a ella;
   - es `AppReaderOutline`.
   - En `LandscapePhone` el índice **siempre** va plegado: el espacio libre del panel es 395 − 189 − 28 − 56 = **122** (891) y 344 − 166 − 28 − 56 = **94** (800), y con < 3 filas una lista desplazable sería menos útil que el selector.
   - En tableta horizontal se despliega como lista.
5. Selector propio del lector, si lo tiene (novena: §5.3).
6. **Pie del panel, fijo** (56 + `B`): solo **Escuchar** y **Aa** (pulgar izquierdo).

Presupuesto del panel a 800: 166 + 28 + 44 (índice) + 44 (selector de novena) = 282 ≤ 344 − 56 = 288. Cabe con 6 dp de margen.

**Panel de texto:**
- Desplaza.
- Columna a la medida (§3.10), centrada.
- **Pie fijo** (56 + `B`) solo en los **lectores secuenciales**:
  - «‹ Anterior» al inicio;
  - **la acción que avanza al final**: «Siguiente ›», «Día siguiente ›», «Continuar ›», «Marcar leído ›», con su centro a **≤ 120 dp del borde derecho seguro** e `y = H − B − 28` (367 / 316 ≥ 0,6 H).
- Los lectores no secuenciales (oración suelta, santo, devoción, mandamiento) no tienen pie: no hay nada que «avance».
- **Nada flota sobre el texto:** los controles flotantes de hoy (barra de la Biblia, índice y Tt del Catecismo) pasan al pie del panel o al pie del texto.

### 5.2 Capítulo bíblico

Usa `AppSupportingPaneLayout(supportingSide = End, supportingWidth = clamp(0,38 W, 300, 360), showSupportingPane = W ≥ 860)`. Ya no se usa `LocalConfiguration.orientation`.

```
 W ≥ 860 (891)                                                                                   891
┌──────────────────────────────────────────────────────────┬──────────────────────────────────────┐
│ (S) ‹   Biblia Platense · Español ▾                      │ (S) ARCOS (CrossReferenceStudy)      │
│         San Juan · Capítulo 1                            │     San Juan 1 · toca un versículo   │
│  ¹ En el principio era el Verbo, y el Verbo estaba junto │                                      │
│  a Dios, y el Verbo era Dios. ² Él estaba en el          │  referencias del versículo elegido   │
│  principio junto a Dios. …         (columna 455, 66 car.)│                           (desplaza) │
│                                                          │ ──────────────────────────────────── │
│                                                          │ [‹] Juan 1 ▾ [▶] [Aa]   [Siguiente ›]│ ← pie del estudio
└──────────────────────────────────────────────────────────┴──────────────────────────────────────┘
  TEXTO 552                                                   ESTUDIO 339
```

- **`W ≥ 860`:**
  - el texto va a la izquierda y el estudio a la derecha;
  - el pie del estudio (`bible_reader_*`) lleva «‹» (capítulo anterior), el selector de capítulo, Escuchar, Aa y **«Siguiente ›»** al final, a ≤ 120 del borde derecho seguro.
- **`W < 860` (800):**
  - el texto ocupa la ventana (columna de 455 centrada);
  - el **pie del texto** es el de §5.1, con Escuchar, Aa y el selector de capítulo entre «‹ Anterior» y «Siguiente ›»;
  - al tocar un versículo se abre el estudio como hoja lateral amplia (528).
- **Cabecera:** la edición (`bible_active_edition`), el libro, el capítulo y la introducción desplazan con el texto.
- **Filas de versículo** (`verse_N`): área táctil ≥ 44 (E9) sin cambiar el interlineado.

### 5.3 Ajustes de cada lector

| Lector | `kind` | Arte | Índice (fila) | Selector propio | Pie de texto (avanza) | Archivo |
|---|---|---|---|---|---|---|
| Lecturas de la Misa | `Reader` (página propia, también desde el centro Leer: §7.2) | sí | 1.ª lectura · Salmo · 2.ª · Aclamación · Evangelio · Reflexión | fecha y color litúrgico en la meta | — (no secuencial) | `BibleScreen.kt` |
| Oración | `Reader` | sí | estrofas, si las hay | — | — | `PrayersAndNovenasScreen.kt` |
| **Día de novena** | `Reader` | sí | partes del día | **«Día 3 de 9 ▾»** (44; `novena_day_strip`), que abre «Todos los días» en hoja lateral | ‹ Día anterior · **Día siguiente ›** | `PrayersAndNovenasScreen.kt` |
| Oraciones de la noche (oficio) | `Reader` | sí | partes del oficio | selector de oficio (fila de 44 → hoja) | — | `NightPrayersScreen.kt` |
| Artículo del Catecismo | `Reader` | no (panel de título) | grupos de § | migas, §§, «Escritura clave» | ‹ Artículo anterior · **Siguiente ›** | `CatechismScreen.kt` |
| Doctrina (mandamiento, sacramento…) | `Reader` | sí | subapartados | fuente | ‹ · **Siguiente ›** (orden de la lista) | `DoctrineParts.kt`, `DoctrineMoral.kt`, `DoctrineFormat.kt`, `DoctrineHistory.kt` |
| Lección | `Reader` | sí | pasos | progreso del curso | ‹ · **Continuar ›** (`study_continue_button`) | `ScriptureStudyScreen.kt` |
| Santo (fuera de lista-detalle) | `Reader` | sí | — | crédito (`saint_image_credit`, ≥ 44) | — | `SaintsScreen.kt` |
| Devoción (fuera de lista-detalle) | `Reader` | sí | historia · oración · promesas | crédito (`devotion_image_credit`, ≥ 44) | — | `DevotionsExplorerScreen.kt` |
| Día de plan | `Reader` | sí | pasajes | «Día 12 de 365» | ‹ · **Marcar leído ›** | `ReadingPlansScreen.kt` |
| El sacramento (Confesión) | `Reader`, dentro de Confesión inmersiva (§9.2) | — | partes | — | — | `ConfessionScreen.kt` |

**Hoja «Todos los días»** de la novena:
- Rejilla de **7 columnas** de celdas de 44 (`novena_day_pill_N`, conserva el tag) que desplaza.
- El día actual va centrado al abrirla, con «Hoy», «Hecho» o «Pendiente» en semántica.
- Sirve para las novenas de 9, 10, 26, 30, 46, 54, 144 y 275 días.

### 5.4 Giro, teclado, recorte e identidades

- **Giro:** ancla de lectura (§3.10). El versículo de estudio de la Biblia pasa del panel a la hoja según la anchura.
- **Teclado:** búsquedas del Compendio y de Preguntas (§15). El campo va en el panel; los resultados, en el texto con `imePadding`. Con 134 dp libres se ve ≥ 1 resultado.
- **Recorte de cámara:** lo absorben los márgenes. El pie de texto aparta «Siguiente» (`safeEnd + 12`).
- **Identidades:**
  - **Sanctuary:** el panel es el folio izquierdo y la fila del índice va en versalitas pequeñas.
  - **Todas:** la lectura va sobre superficie opaca.

### 5.5 Criterios de aceptación de L2

1. Medida de §3.10: 60–75 caracteres con serif y con sans, a 891 y a 800, al 100 %.
2. Cero `OBSCURED`: nada flota sobre el texto.
3. Primer párrafo visible sin desplazar. Arte ≤ 40 % del área.
4. En los lectores secuenciales, la acción que avanza cumple §2.3.
5. Al girar, se ve el mismo versículo o párrafo (±1).
6. Los tags `bible_reader*`, `novena_day_*`, `catechism_article_*`, `study_*` y `prayer_*` se mantienen. `CrossReferencesUiTest`, `BibleReadingJourneyTest`, `CatechismInteractionTest`, `PrayerDetailsUiTest` y `NovenaIndexJourneyTest` en verde.

---

## 6. L3 · Hoy

**Patrón:** feed en carriles + panel de portada, con `AppPageLayout(kind = Today)`. **Archivos:** `HomeScreen.kt` y `ForYouToday.kt`.

### 6.1 Boceto a 891 × 411

```
 0      80                      307                                                            891
┌───────┬─────────────────────────┬──────────────────────────────────────────────────────────────┐ 0
│ (S)   │ (S)                 ⌕ ◔ │ (S)                                                          │ 24
│ ╭───╮ │ ╔═════════════════════╗ │  CARRIL A · 264                  CARRIL B · 264              │ 36
│ │Hoy│ │ ║ la cruz en la       ║ │  Oración del momento             Liturgia de hoy   Jn 1,1-18 │
│ │Ora│ │ ║ montaña (bruma)     ║ │ ┌────────────────────────────┐ ┌────────────────────────────┐│
│ │Lee│ │ ║ Sábado, 21 de nov.  ║ │ │ ☾ Oraciones de la noche  › │ │ «En el principio era el    ││
│ │Exp│ │ ║ Hoy                 ║ │ │   Ahora · 20:00            │ │  Verbo, y el Verbo estaba  ││
│ ╰───╯ │ ╚═════════════════════╝ │ │ ✝ Ángelus                › │ │  junto a Dios…»            ││
│       │ ┌─────────────────────┐ │ │   Mañana · 6:00            │ │ Primera lectura · Ap 11  › ││
│       │ │● Tiempo Ordinario  ›│ │ └────────────────────────────┘ │ Reflexión                › ││
│       │ │ Presentación de la  │ │  Mis prácticas                 │ [■ Leer las lecturas ▸]    ││ ← único primario
│       │ │ Virgen María        │ │ ┌────────────────────────────┐ └────────────────────────────┘│
│       │ └─────────────────────┘ │ │ (0) de 12 hoy              │  Para ti hoy …               │
│       │                         │ │ [ Ver mis prácticas ]      │                              │ ← secundario
└───────┴─────────────────────────┴──────────────────────────────────────────────────────────────┘ 411
  riel   PANEL 227 · fijo          CONTENIDO 584 · 2 carriles · desplaza
```

El riel muestra 4 destinos hasta T-046 y «Mi camino» después.

### 6.2 Qué va dónde

**Panel:** `clamp(0,28 · Wc, 208, 280)`, de 227 a 891 y 208 a 800. Lleva:
- el cabezal: fecha, «Hoy» y acciones (`global_search_button`, `home_reminders_button`);
- el tiempo litúrgico (`liturgical_calendar_widget`) en la ranura.

**Carriles:** 264 + 264 a 891 y 228 + 228 a 800.
- **A:** «Oración del momento» (`home_prayer_moment_heading`) y «Mis prácticas» (`home_practices_heading`, `home_fidelity_card`, `home_habit_tracker_card`, `home_fidelity_count`, `home_open_temptations`).
- **B:** «Liturgia de hoy» (`daily_mass_card`, `home_first_reading_row`, `home_continue_row`, `home_continue_reading`) y «Para ti hoy» (`home_for_you`).
- **A todo el ancho:** «Más para hoy» (`today_more_toggle`). Al expandirse vuelve a dos carriles:
  - A: versículo, invitación (`today_invitation`) y Rosario (`today_continue_rosary`, `today_continue_prayer`);
  - B: santo, pregunta (`home_daily_question`) y explorar.
- `today_next_action` va en el carril A.
- El orden de TalkBack coincide con el de vertical.

**Jerarquía:** **un solo botón primario relleno** en la pantalla: **«Leer las lecturas»**, en el carril B. «Ver mis prácticas» pasa a **secundario** (`AppSecondaryButton` o `AppSecondaryCapsule`), también en vertical para no crear dos versiones. *Esto cambia el vertical; vetable (§14).*

### 6.3 Presupuesto y lo esencial sin desplazar (al 100 %, `S` = 24, `B` = 16)

Alto visible de los carriles: `H − B − (S + 12)`, es decir 359 (891) y 308 (800).

| Bloque | Alto |
|---|---|
| Cabecera de sección | 44 |
| Fila de oración (2 líneas) | 64 |
| Tarjeta de prácticas, fila del anillo | 88 |
| Botón secundario | 44 |
| Tarjeta de liturgia: cabecera | 44 |
| Tarjeta de liturgia: vista previa del Evangelio (3 líneas de `reading`) | 90 |
| Tarjeta de liturgia: fuente | 18 |
| Tarjeta de liturgia: filas de 1.ª lectura y de reflexión | 2 × 52 |
| Tarjeta de liturgia: acción primaria | 48 |
| Tarjeta de liturgia: márgenes | 40 |

| Medida | Debe verse entero sin desplazar | Suma |
|---|---|---|
| 891 × 411 | <ul><li>panel completo;</li><li>A: cabecera + 2 filas + cabecera de prácticas + anillo (44 + 128 + 16 + 44 + 88);</li><li>B: tarjeta de liturgia hasta «Leer las lecturas» (44 + 90 + 18 + 104 + 48 + 40)</li></ul> | <ul><li>A: 320 ≤ 359</li><li>B: 344 ≤ 359</li></ul> |
| 800 × 360 | <ul><li>panel completo;</li><li>A: cabecera + 2 filas + cabecera de prácticas;</li><li>B: cabecera + referencia + vista previa del Evangelio</li></ul> | <ul><li>A: 232 ≤ 308</li><li>B: 142 ≤ 308</li></ul> |
| 130 % (listón duro) | un carril; el panel se mantiene; nada recortado | — |

### 6.4 Tableta, giro e identidades

- **Tableta horizontal:** 3 carriles desde 1200 dp (A oración y prácticas; B liturgia; C «Para ti hoy» y extras).
- **Giro:** ancla del bloque y «Más para hoy» abierto o cerrado (`rememberSaveable`, fuera de las ranuras).
- **Identidades:** tarjeta del tiempo litúrgico flotando sobre la bruma (Lumen), opaca (Tradicional), refractando (Cristal) o en folio filetado (Sanctuary).
- **Captura en PT:** cada título de sección ocupa ≤ 3 líneas en su carril.

### 6.5 Criterios de aceptación de L3

1. Tabla de §6.3 (`ESSENTIAL_BELOW_FOLD`).
2. Carriles de igual ancho (±1) y cabeceras alineadas.
3. Un solo botón primario relleno.
4. Paridad de nodos con el vertical.
5. `HomeTextTest`, `HomePrayerPrioritiesTest` y `AppNavigationTest` en verde.

---

## 7. L4 · Centros Orar y Leer

**Patrón:** panel de portada con segmentos + rejilla. **Archivos:** `PrayersAndNovenasScreen.kt` y `BibleScreen.kt`.

### 7.1 Orar

```
┌───────┬─────────────────────────────┬──────────────────────────────────────────────────────────┐
│ riel  │ (S)                       ⌕ │ (S) ┌─ adhesivo: búsqueda (a 800) y filtros (Novenas) ─┐ │
│       │ ╔═════════════════════════╗ │     └─────────────────────────────────────────────────┘ │
│       │ ║ lirios · Ora sin cesar  ║ │  ┌──────────┐ ┌──────────┐ ┌──────────┐                  │
│       │ ║ Oraciones               ║ │  │ Rosario  │ │ Coronilla│ │ Ángelus  │  guías, 3 col.   │
│       │ ╚═════════════════════════╝ │  └──────────┘ └──────────┘ └──────────┘                  │
│       │ ▌Oraciones                  │  [Fundamentales] [Marianas] [Santos]   losas, 3 col.      │
│       │  Novenas                    │  …                                            (desplaza)  │
│       │  Noche                      │                                                          │
│       │ [⌕ Buscar oraciones]  (891) │                                                          │
└───────┴─────────────────────────────┴──────────────────────────────────────────────────────────┘
```

**Panel:**
- Cabezal, **segmentos verticales** (`prayers_segments`) y la búsqueda del segmento.
- Presupuestos (alto útil del panel = `H − B`):
  - 891: 189 + 144 + 56 = **389 ≤ 395**;
  - 800: 166 + 144 = 310 ≤ 344. La búsqueda no cabe (366): pasa a ser la **primera fila adhesiva del contenido**.
- Los segmentos nunca salen del panel.

**Contenido:**
- **Oraciones:**
  - guías (`pray_primary_guides`) y categorías (`prayer_category_tile_*`), en 3 columnas;
  - filas (`prayer_row_*`) en 1 columna.
- **Novenas:**
  - filtros (`novena_filter_chips`) adhesivos;
  - en curso (`novenas_in_progress`);
  - rejilla de 3 columnas (`novena_card_*`);
  - completadas (`novenas_completed`).
- **Noche:** oficios en filas. El oficio abierto es un lector L2.

**Páginas que se abren:**
- **Ficha de novena** (`novena_detail_*`): página con panel. «Empezar» o «Continuar» (`novena_start_button`) va en el **pie del contenido, al final** (acción que avanza).
- **Lista de una categoría:** ver §15.

### 7.2 Leer

**Panel:** cabezal, segmentos Biblia · Misa · Estudio y selector de edición.

**Contenido:**
- **Biblia:**
  - «Continuar leyendo» (`bible_continue_reading`): tarjeta de imagen de 160 de alto;
  - directorio (`bible_book_directory`) en filas compactas, 2 columnas a 891 y 1 a 800.
- **Misa:** resumen de las lecturas del día.
  - Muestra la fecha, la celebración, la lista de lecturas con su referencia (1 fila cada una) y **«Leer las lecturas ›»** al final del pie del contenido.
  - Esa acción abre el **lector de la Misa como página `kind = Reader`** (§5.3): riel oculto y medida de 66 caracteres.
  - Ya no se lee dentro del centro, donde la medida sería de 479 dp con el riel (corrección 12).
- **Estudio:** cursos en rejilla de 3 columnas. La ficha del curso, en §15.

### 7.3 Giro, 130 % e identidades

- **Giro:** el segmento, los filtros y la búsqueda se **elevan** por encima de `AppPageLayout` (§2.4). El ancla del contenido se conserva.
- **130 %:** el panel desplaza y se pierde una columna.
- **Identidades:**
  - Sanctuary: segmentos como sumario.
  - Tradicional: segmento activo sólido.
  - Lumen y Cristal: lente de cristal.

### 7.4 Criterios de aceptación de L4

1. Sin desplazar se ven el panel completo y la primera fila completa de la rejilla, a 891 y a 800, con `S` = 24 y `B` = 16.
2. Los filtros de Novenas, y la búsqueda a 800, son visibles en cualquier punto del desplazamiento.
3. Rejillas según §3.8.
4. `NovenaIndexJourneyTest`, `NovenaOriginBadgeUiTest`, `PrayerDetailsUiTest`, `BibleReadingJourneyTest` y `ReadingPlansUiTest` en verde.

---

## 8. L5 · Listas con ficha

**Patrón:** lista-detalle, con `AppTwoPane` según §3.9. Lo implementa Hermes (`ext/T-05x`).

### 8.1 Boceto común (Santos), a 891 × 411

```
 0      80                         372                                                         891
┌───────┬────────────────────────────┬───────────────────────────────────────────────────────────┐ 0
│ (S)   │ (S) ‹                      │ (S)                                                       │
│ riel  │ ╔══════════════════════════╗│ ┌──────────┐  2 de enero · Memoria                        │
│       │ ║ retablo · Calendario     ║│ │ miniatura│  San Basilio Magno y San Gregorio            │ ← cabecera de ficha
│       │ ║ Santos         (tira 96) ║│ │ 104×130  │  Nacianceno, obispos y doctores              │
│       │ ╚══════════════════════════╝│ └──────────┘  Imagen: Rijksmuseum · CC0  [▶ Escuchar]   │
│       │ [⌕ Buscar un santo    ][⫶] │ ─────────────────────────────────────────────────────────  │ ← barra adhesiva 56
│       │ ENERO                      │  La Iglesia celebra el 2 de enero a dos grandes Padres…   │
│       │▌San Basilio Magno y San    │  (columna a la medida)                                    │
│       │ Gregorio Nacianceno…       │                                                           │
│       │ Santísimo Nombre de Jesús  │                                                (desplaza) │
└───────┴────────────────────────────┴───────────────────────────────────────────────────────────┘ 411
         LISTA 292 · la tira desaparece   FICHA 519
         al desplazar
```

- La tira de 96 y el título de la lista desaparecen al desplazar. La barra (búsqueda + filtro) es adhesiva.
- El botón de filtro (`list_filter_button`) abre una hoja lateral con los filtros de hoy. Con filtros activos, aparece una fila de 44 con chips para quitarlos.
- **Ficha vacía:** arte de la sección en un marco de 280 × 180 + `AppEmptyState`.

### 8.2 Pantallas

| Pantalla | Barra adhesiva | Filtros (hoja) | Ficha | Archivo |
|---|---|---|---|---|
| Santos | búsqueda | mes, «solo con ficha» | santo | `SaintsScreen.kt` |
| Órdenes y oficios | búsqueda | segmento Órdenes/Oficios | orden u oficio | `DoctrineAndHistoryScreen.kt`, `DoctrineOrders.kt`, `DoctrineParts.kt` |
| Devociones | búsqueda | advocación, lugar | devoción | `DevotionsExplorerScreen.kt` |
| 101 preguntas | búsqueda | tema | <ul><li>pregunta</li><li>`catholic_qa_prev` y `next` con el título completo en 2 líneas (≥ 44)</li></ul> | `DoctrineQuestions.kt` |
| Catecismo (índice y parte) | búsqueda | — | <ul><li>hijos del nodo</li><li>el artículo abre L2</li><li>«Compendio» (`catechism_open_compendium`) al pie de la lista</li></ul> | `CatechismScreen.kt` |
| Planes | — | — | <ul><li>detalle del plan</li><li>«Empezar hoy» en el pie de la ficha, al final</li></ul> | `ReadingPlansScreen.kt` |
| Doctrina, Historia | ver §15 | | | |

### 8.3 Lo esencial sin desplazar (100 %, `S` = 24, `B` = 16)

Medido en el orden por defecto de cada lista, con los nombres ES y PT:
- **891:** barra adhesiva, ≥ 2 filas completas en reposo y **≥ 3 tras desplazar la tira**; cabecera de ficha completa + 3 líneas de texto.
- **800:** barra adhesiva, ≥ 1 fila en reposo y **≥ 2 tras desplazar la tira**; cabecera de ficha completa.

### 8.4 Teclado

- La búsqueda está en la barra de la lista. Sobre el teclado (0,56 H) quedan 157 / 134: barra 56 + **≥ 1 fila completa** (64) a 891 y a 800.
- La ficha no se mueve.
- La tecla de acción del IME es «Buscar».

### 8.5 Giro

Se conservan la selección (explícita o por defecto, §3.9; de vertical a horizontal, §2.4), la lista por clave, la consulta **elevada** y el ancla de la ficha.

### 8.6 Búsqueda (`SearchScreen.kt`)

**Sin arte y sin panel:**
- **Barra adhesiva de 56:** atrás + campo (`search_field`) + borrar.
- **Fila de chips de secciones** (Todo · Oraciones · Biblia · Santos · …) bajo la barra. **Se oculta mientras el teclado está abierto.**
- **Resultados** (`search_group_*`) en rejilla de 2 columnas (§3.8), con la cabecera de cada grupo y su recuento.
- **Con teclado:** 157 / 134 − 56 = 101 / 78 → **≥ 1 fila completa** (64) a 891 y a 800.

### 8.7 Identidades y criterios de L5

**Identidades:** como en §2.5. La lista de Sanctuary va filetada.

**Criterios de aceptación:**
1. §8.3 y §8.4 medidos con el arnés de insets y de teclado.
2. Seleccionar no apila. «Atrás» sale.
3. Giro según §3.9 y §2.4.
4. Cero `SMALL_TARGET`.
5. La fila de la lista no elide texto: la de Historia lleva el título y las fechas; el resumen está en la ficha (§15).
6. `SaintsScreenTest`, `OrdersScreenTest`, `Devotion*`, `QuestionDeeperUiTest`, `CatechismInteractionTest`, `ReadingPlansUiTest`, `SearchUiTest` y `TabletLayoutTest` en verde.

---

## 9. L6 · Mi camino, Confesión, Recordatorios y Explorar

Lo implementa Hermes en `ext/T-05x`, después de T-046.

### 9.1 Mi camino (`MyPathScreen.kt`)

```
┌───────┬─────────────────────────┬──────────────────────────────────────────────────────────────┐
│ riel  │ ╔═════════════════════╗ │  Hoy en mi camino (carril A)     Secciones (carril B)        │
│       │ ║ bosque en la niebla ║ │ ┌────────────────────────────┐ ┌────────────────────────────┐│
│       │ ║                     ║ │ │ Hábitos                    │ │ Diario                   › ││
│       │ ║ Mi camino           ║ │ │ 0 hechos · 12 pendientes › │ │ Tentaciones y victorias  › ││
│       │ ║   (póster: el arte  ║ │ │ Plan: Biblia en un año   › │ │ Prácticas                › ││
│       │ ║    llega al pie)    ║ │ │ Novena: día 3 de 9       › │ └────────────────────────────┘│
│       │ ╚═════════════════════╝ │ └────────────────────────────┘ ┌────────────────────────────┐│
│       │                         │                                │ Confesión                › ││
└───────┴─────────────────────────┴──────────────────────────────────────────────────────────────┘
```

- **Panel:** `kind = Today` (227 / 208), en **variante póster** (§3.2).
- **Carriles:**
  - A: «Hoy en mi camino» (`my_path_today_header`, `my_path_today`, `my_path_today_habits`, plan y novena);
  - B: `my_path_sections` + `my_path_confession_entry`.
- **Presupuesto a 891:** A = 44 + 72 + 64 + 64 = 244; B = 168 + 16 + 64 = 248. Ambos ≤ 359.
- **A 800:** A completo (244 ≤ 308) y las 3 secciones de B.

**Hábitos** (`HabitsAndVirtuesScreen.kt`):
- **Panel:** cabezal + anillo del día. Cabe: 189 + 28 + 120 ≤ 395.
- **Carril A:** el **calendario de fidelidad** del mes (`fidelity_day_*`; 7 columnas en 264, con celdas de 36 × 46, con el permiso que ya existe).
- **Carril B:** los hábitos con casilla.

**Diario:**
- lista-detalle (§3.9);
- editor: §10.3.

**Tentaciones y victorias:**
- calendario en el carril A y registro en el B;
- hojas laterales.

**Prácticas:** 2 carriles.

### 9.2 Confesión (`confession/ConfessionScreen.kt`)

**`kind = Immersive`**: sin riel. Panel `clamp(0,28 W, 224, 280)`: 249 / 224. Carriles iguales de **293** (891) y **260** (800).

```
 0                          249                                                                     891
┌─────────────────────────────┬─────────────────────────────────────┬──────────────────────────────────┐
│ (S) ‹                       │ (S) EXAMEN · carril A · 293         │ (S) MI LISTA · carril B · 293    │
│ ╔═════════════════════════╗ │  [Elegir perfil]   Soltero/a        │ ● Una lista temporal: se borra   │
│ ║ Jesús y los apóstoles   ║ │  ▸ Amor a Dios               (2)    │   al salir… (aviso de privacidad)│
│ ║ Un encuentro con la     ║ │    ☐ ¿He faltado a Misa…?           │  Para conversar con el sacerdote │
│ ║ misericordia · Confesión║ │    ☑ ¿He mentido…?                  │  ☑ ¿He mentido…?                 │
│ ╚═════════════════════════╝ │  ▸ Amor al prójimo                  │  ┌────────────────────────────┐  │
│ ▌Prepararme                 │  ▸ Deberes de estado                │  │ Notas personales (opc.)    │  │
│  Mi lista                   │                          (desplaza) │  └────────────────────────────┘  │
│  El sacramento              │                                     │  [ Terminar y borrar ]           │
└─────────────────────────────┴─────────────────────────────────────┴──────────────────────────────────┘
```

**Panel:**
- Cabezal y segmentos verticales (`confession_tabs`).
- Cabe: 189 + 28 + 144 = 361 ≤ 395 (891); 166 + 28 + 144 = 338 ≤ 344 (800).
- El aviso de privacidad (`confession_privacy_notice`) **encabeza el carril B**.

**Pestañas:**
- **Tab 0 «Prepararme»:** examen (carril A: `confession_examen`, `confession_profile_button`, `confession_profile_title`, `confession_section_*`) y lista en vivo (carril B: `confession_private_list`, `confession_empty_list`, `confession_review_list`, `confession_notes`, `confession_finish_erase`).
- **Tab 1 «Mi lista»:** la lista a todo el ancho del contenido, con un campo de notas grande.
- **Tab 2 «El sacramento»:** lector a la medida (455 dentro de 602 / 536), con `confession_guide` y `confession_prayer_*`.

**Teclado** (notas): solo se encoge el carril B. Sobre el teclado quedan 157 / 134: rótulo 18 + 2 líneas (48) + margen 24 = 90.
- La tecla de acción del IME es «Listo».
- **«Terminar y borrar» es destructiva y no sube junto al teclado:** queda bajo él, a propósito.

**Privacidad:** la lista y las notas sobreviven al giro (holder de actividad) y nunca se guardan (§2.4).

**Diálogo inicial:** `AppDialog` con `aside`.

### 9.3 Recordatorios (`RemindersScreen.kt`, `NoticeSettings.kt`)

- **Panel:** cabezal + avisos de estado (notificaciones o alarmas desactivadas, con «Activar» / «Permitir»). Sin avisos y sin recordatorios: variante póster.
- **Contenido:** recordatorios (`notice_row_*`) en 2 columnas a 891 y 1 a 800. Horas (`reminder_time_*`) de **≥ 44** de alto (hoy 34, E9).
- **Hojas laterales**, con su estado guardado.

### 9.4 Explorar (`MoreScreen.kt`)

- **Panel:** cabezal (`more_hero_photo`), fila «Buscar» y «Empieza aquí» (`explore_start_here`).
- **Contenido:** secciones (`more_section_N`) en filas compactas en rejilla (2 columnas a 891, 1 a 800); tarjetas de sección (`more_section_hero_0`); versión (`more_version`).

### 9.5 Criterios de aceptación de L6

Con el arnés de insets y de teclado:
1. **Mi camino:** carriles completos a 891; carril A y 3 secciones a 800.
2. **Confesión:**
   - carriles ≥ 260;
   - con el teclado, el campo de notas tiene ≥ 2 líneas visibles a 891 y a 800 (`IME_HIDDEN` = 0);
   - la lista sobrevive al giro y se vacía al salir.
3. **Recordatorios:** cero `SMALL_TARGET`.
4. **Explorar:** paridad de nodos.
5. `MyPathUiTest`, `ConfessionInteractionTest`, `RemindersTest`, `NoticeUiTest`, `JournalTemptationInteractionTest` y `BackupUiTest` en verde.

---

## 10. L7 · Hojas, diálogos, bienvenida y ajustes

### 10.1 Inventario

| Hoja o diálogo | Archivo | Forma | Detalle |
|---|---|---|---|
| Apariencia (`gui_customizer_sheet`) | `components/GuiCustomizerModal.kt` | lateral amplia, 2 columnas | <ul><li>izquierda: Estilo, Modo, Reducir transparencia y Alto contraste</li><li>derecha: acento y vista previa en vivo</li></ul> |
| Fuente (`font_picker`) | `components/FontPickerDialog.kt` | lateral amplia, 2 columnas | <ul><li>izquierda, fija: muestra (Juan 1,1), tamaño y espaciado</li><li>derecha: tipografías</li><li>**«Listo» ocupa el lugar de «Cerrar»** en la cabecera (una sola acción de cierre)</li></ul> |
| Texto del lector (`reading_settings_sheet`) | `design/LumenExtras.kt` | lateral normal | — |
| Tu camino (`welcome_profile_sheet`) | `screens/BeginnerJourneyScreen.kt` | lateral normal, `footer` «Guardar» | — |
| Copia de seguridad (`backup_*`) | `backup/BackupSheet.kt` | lateral normal, `footer` | <ul><li>con el teclado, la acción pasa a la cabecera (§3.6)</li><li>`rememberSaveable` en los campos no secretos</li></ul> |
| Informe de errores (`error_report_sheet`) | `diagnostics/ErrorReportSheet.kt` | lateral normal, `footer` «Enviar» | igual que la copia de seguridad |
| Idioma, reglas del calendario, créditos, recuperación, «Para ti hoy» | varios | lateral normal | — |
| Calendario (`calendar_picker_sheet`) | `components/CalendarPickerSheet.kt` | lateral amplia, 2 columnas | <ul><li>**izquierda:** navegación del mes (‹ noviembre 2026 ›), el día elegido (tiempo, celebración, color) e «Ir a este día»</li><li>**derecha:** rejilla 7 × 6 de celdas de 44</li><li>en esta hoja, el título y «Cerrar» van al principio de la columna izquierda, y la rejilla usa todo el alto de la hoja</li><li>presupuesto a 360 (hoja de 304): días de la semana 20 + 6 × 44 = 284 ≤ 304</li></ul> |
| Rueda de color | `components/AccentColorWheelDialog.kt` | `AppDialog` con `aside` | <ul><li>rueda ≤ `H − 120`</li><li>muestras y acciones a la derecha</li></ul> |
| Navegador interno | `components/InAppBrowser.kt` | pantalla completa | insets laterales |
| Confirmaciones | varios | una columna, 420 | — |

### 10.2 Bienvenida (`welcome_dialog`)

```
┌───────────────────────────── AppDialog 720 × ≤ 339 (288 a 360) ───────────────────────────┐
│ Un lugar para comenzar                      │  Estoy explorando                       ✓   │
│ Ven como eres. Puedes cambiar de camino     │  Estoy regresando                           │
│ en cualquier momento.                       │  Estoy practicando                          │
│ Tiempo para hoy                             │                                             │
│ [ 2 min ] [ 5 min ] [ 10 min ]              │ ─────────────────────────────────────────── │
│                                             │                    [Ahora no]  [EMPEZAR ▸]  │
└─────────────────────────────────────────────┴─────────────────────────────────────────────┘
```

«Tiempo para hoy» va en la **columna izquierda** en todos los tamaños.

| Columna | Contenido | 360 (alto máximo 288) |
|---|---|---|
| Izquierda | título 28 + texto 40 + rótulo 18 + chips 44 + márgenes 48 | 178 ≤ 288 |
| Derecha | 3 filas × 48 + acciones 52 + márgenes 48 | 244 ≤ 288 |

Etiquetas: `welcome_dialog`, `welcome_profile_N` y `welcome_personalize`.

### 10.3 Editor de diario (`SpiritualLifeScreen.kt`)

En `LandscapePhone` es un **editor a pantalla completa**:
- **Barra de 56:** «Cancelar» · el título de la entrada como campo · **«Guardar»** al final.
- **Cuerpo:** a la medida, con `imePadding`.
- **Con el teclado** (157 / 134 libres): barra 56 + **≥ 3 líneas** (72) = 128. Cabe en ambos tamaños.
- El cuerpo pasa a `rememberSaveable`; el título ya lo usa (`draftTitle`).

### 10.4 Criterios de aceptación de L7

1. Cero hojas inferiores en `LandscapePhone` y en `CompactPhone`.
2. Nada recortado en ninguna hoja ni diálogo (4 identidades, 100 % y listón duro al 130 %).
3. `IME_HIDDEN` = 0 en todas las hojas con campo, con el teclado de 0,56 H.
4. La bienvenida y la intro de Confesión muestran su acción principal sin desplazar a 891 y a 800.
5. La fuente elegida se aplica en la muestra y bajo la hoja (`FontPickerInteractionTest`, `FontPreferencesTest`).
6. Al girar, cada hoja abierta sigue abierta y con sus valores.

---

## 11. Rúbrica del crítico

**Cómo se puntúa:**
- **Escala:** cada criterio va de 0 a 10. **Se aprueba con ≥ 9 en los 9 criterios.** Un 9 exige todas las comprobaciones duras y, como mucho, un defecto menor documentado.
- **Tamaño de referencia:** se puntúa **al 100 %**. Al 130 % solo se exige el listón duro: nada recortado, oculto ni inalcanzable. Menos columnas y más desplazamiento no restan.
- **Insets:** todas las comprobaciones se hacen con el arnés de insets de §3.13 (`S` = 24, `B` = 16, gestos de 32).
- **Comparación:** cada captura horizontal se compara con su vertical.

| # | Criterio | Comprobaciones duras (todas, para ≥ 9) |
|---|---|---|
| 1 | **Uso del espacio** | <ul><li>Arte de cabezal ≤ 40 % del área; `CompactBand` ≤ 30 % de `H`; panel ≤ 0,40 W.</li><li>Lo esencial de cada área (§4.2–4.3, §6.3, §7.4, §8.3, §9.5, §15) se ve sin desplazar.</li><li>Sin franjas vacías > 15 % del área, salvo los márgenes de la medida; la variante póster cubre los paneles sin ranura.</li><li>Ninguna columna < 220 (salvo el hilo, de 44).</li></ul> |
| 2 | **Jerarquía y ritmo** | <ul><li>Un título por pantalla, con el rango de AGENTS.md.</li><li>Todos los títulos de panel en `0,46 H − 14` (±1). **Excepción declarada:** el título de la tira de 96 de las listas de lista-detalle (§3.9), que desplaza con la lista.</li><li>Múltiplos de 4.</li><li>Inicios alineados (±1).</li><li>Carriles iguales (±1).</li><li>**Un solo botón primario relleno por pantalla.**</li></ul> |
| 3 | **Legibilidad** | <ul><li>Medida medida con `TextMeasurer` (§3.10): 60–75 caracteres en lectores y Biblia; ≥ 56 en fichas con serif y en el Rosario.</li><li>Contraste ≥ 4,5:1 en texto (≥ 3:1 en texto grande y controles), también sobre el velo de la variante póster.</li><li>Lectura sobre superficie opaca.</li><li>Ningún texto por debajo del tamaño del usuario.</li></ul> |
| 4 | **Ergonomía** | <ul><li>Objetivos ≥ 44 (cero `SMALL_TARGET`).</li><li>**Acción que avanza: centro a ≤ 120 dp del borde derecho seguro e `y ≥ 0,6 H`** (§2.3).</li><li>Retroceso y navegación en el tercio izquierdo o al inicio del pie.</li><li>Ningún control arrastrable a menos de `gestos + 8` del borde.</li></ul> |
| 5 | **Coherencia** | <ul><li>Paridad de nodos con el vertical.</li><li>4 identidades × claro/oscuro, fieles a §2.5.</li><li>Paneles solo con `AppPageLayout`, `AppSupportingPaneLayout` y `AppTwoPane`.</li></ul> |
| 6 | **Estados** | Cada estado de la tabla de §16 diseñado dentro de la disposición horizontal, con su etiqueta y su captura. |
| 7 | **Insets y teclado** | <ul><li>Cero `UNDER_INSET` con recorte de 32 y barra de tres botones de 48 a cada lado.</li><li>Cero `IME_HIDDEN` con teclado de 0,56 H.</li></ul> |
| 8 | **Giro** | `LandscapeRotationTest` verde en todas las filas de §2.4, también con texto escrito en la búsqueda y con una ficha apilada. |
| 9 | **Accesibilidad** | <ul><li>Orden de TalkBack igual al del vertical.</li><li>`heading`, `paneTitle`, `selected`.</li><li>Acciones del hilo.</li><li>El foco entra y sale de las hojas.</li><li>`liveRegion` en los avisos breves.</li></ul> |

Si falla una comprobación dura, ese criterio no pasa de 8.

---

## 12. Matriz de capturas

```sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache \
  :app:testDebugUnitTest --tests 'com.example.screenshots.FullAppScreenshotTest' \
  -Pshots.landscape=true -Pshots.lang=es -Pshots.identity=all
```

`-Pshots.landscape=true` aplica por defecto `S` = 24, `B` = 16 y gestos de 32. Además se pasa `LandscapeAuditTest` con `-Paudit.capture=all`.

| Eje | Valores | Obligatorio por área |
|---|---|---|
| Tamaño | **891 × 411** · **800 × 360** | ambos |
| Identidad | las 4 | las 4 |
| Modo | claro · oscuro | ambos |
| Material | «Reducir transparencia» | Lumen y Cristal: 1 por pantalla del área, en 891, claro |
| Texto | 100 % · 130 % | <ul><li>100 %: todo</li><li>130 %: 800, ES y PT, las 4 identidades, claro (listón duro)</li></ul> |
| Idioma | ES · PT | PT en 891 Lumen claro y en las de 130 % |
| Teclado (`-Pshots.ime`, 0,56 H) | 230 / 202 | <ul><li>Búsqueda</li><li>notas de Confesión</li><li>Copia de seguridad</li><li>Informe de errores</li><li>editor de diario</li><li>búsquedas de Santos, Preguntas, Compendio y Parroquias</li></ul> Lumen y Sanctuary, claro, ES. |
| Insets | recorte a la izquierda · recorte a la derecha · tres botones a la derecha | <ul><li>L0: todas las raíces</li><li>cada área: su pantalla principal, en Lumen claro</li></ul> |
| Estados | §16 | cada fila, en Lumen claro, 891 |
| Regresión | 411 × 891 · 1280 × 800 | <ul><li>L0, L3 y L4: todas las raíces</li><li>el resto: su pantalla principal, en Lumen y Sanctuary claro</li></ul> |

- **Mínimo por pantalla:** 16 (2 tamaños × 4 identidades × 2 modos), más 8 al 130 %, más las de teclado, insets y estados que le correspondan.
- **Nombre:** `{área}_{pantalla}_{identidad}_{claro|oscuro}_{w}x{h}[_s130][_pt][_ime][_cutL|_cutR|_navR][_estado]`.
- **Destino:** `docs/tasks/T-0xx-shots/`, con una lámina de contacto por área (vertical | horizontal).

---

## 13. Lista de comprobación en el teléfono real

Robolectric no simula el recorte real, el teclado, la rotación física ni el coste del cristal. Con cada versión de tramo (L1+L2, L3+L4, L5–L7), el dueño comprueba:

1. **Giro en los dos sentidos** a mitad de:
   - un Rosario con la voz activa;
   - un capítulo largo;
   - la lista de Confesión con notas;
   - una búsqueda con texto.
2. **Cámara** a cada lado: nada importante debajo.
3. **Tres botones** (si los usa): ni el riel ni «Siguiente» quedan debajo.
4. **Teclado** en Búsqueda, en las notas de Confesión y en el diario: se ven el campo y su acción, y el teclado no ocupa toda la pantalla.
5. **Rosario completo:**
   - las oraciones de decena se leen sin desplazar;
   - el pulgar izquierdo mueve las cuentas y «Anterior»;
   - el derecho pulsa «Siguiente».
6. **Lectura larga:** líneas cómodas y nada sobre el texto; «Siguiente» abajo a la derecha.
7. **Letra al 130 %:** nada cortado y el riel completo.
8. **Fluidez** de Hoy y Orar en Lumen y en Cristal.

Cada fallo abre una ronda de corrección del área.

---

## 14. Riesgos y decisiones abiertas

**Decisiones que el dueño puede vetar** (también en `LANDSCAPE-RESUMEN.md`):
1. El riel se oculta en el Rosario, los lectores, la Biblia y Confesión (§3.5).
2. **Rosario:**
   - «Anterior» a la izquierda y «Siguiente» a la derecha;
   - en un teléfono pequeño y con la guía activa, el Padrenuestro desplaza 1–2 líneas (§4.3);
   - el Credo, la Salve y las letanías desplazan siempre.
3. En la Biblia, los arcos son un panel fijo solo desde 860 dp; por debajo se abren al tocar un versículo (§5.2).
4. Las hojas inferiores pasan a laterales (§3.6).
5. Nombres completos en el riel al 100 %; cortos («Camino», «Más») solo al 130 % (§3.5).
6. Confesión ocupa toda la pantalla, sin riel, y conserva la lista al girar sin guardarla nunca (§9.2).
7. En Hoy, «Ver mis prácticas» pasa a botón secundario **también en vertical** (§6.2).
8. Las lecturas de la Misa se abren como página de lectura desde el centro Leer (§7.2).

**Riesgos técnicos:**
- **Estabilidad del árbol al girar** (§2.4). Lo vigilan el lint de estado y `LandscapeRotationTest`, que se crean en L0.
- **Métricas de texto en Android frente a la medición con las TTF.** Pueden variar unos dp. `RosaryFitTest` es la autoridad; los márgenes de §4.3 (≥ 8 dp) lo absorben salvo en la excepción declarada.
- **Coste del cristal vivo con dos paneles:** se mide en el teléfono (§13.8). Si hace falta, el panel usa bruma estática.
- **`LocalWindowInfo.containerSize`:** si falla en algún nivel de API, se usa `androidx.window` (§2.1).
- **Carga de migración:** 34 cabezales en 19 archivos. `CompactBand` da el respaldo inmediato.
- **Hermes (L5–L6):** recibe este documento, los archivos permitidos y la prohibición de tocar `ui/design`.

**Fuera de alcance:** postura de mesa en plegables, ventanas libres (freeform) y teclados físicos.

---

## 15. Cobertura completa: las pantallas restantes

Todos los destinos de `AppDestination` y todas las subpantallas que recorre la auditoría tienen patrón. Estas faltaban en la revisión 1:

| Pantalla (destino o subpantalla) | Archivo | Área | Patrón | Lo esencial sin desplazar (891 / 800) | Capturas |
|---|---|---|---|---|---|
| **Parroquias** (`PARISHES`) | `ParishFinderScreen.kt` | L6 | <ul><li>`AppPageLayout(Hub)`</li><li>ranura: aviso «no son horarios verificados» (meta), campo de búsqueda y **«Parroquias cerca de mí»** (primario)</li><li>contenido: resultados en 2 columnas o estado (§16)</li></ul> | <ul><li>campo + botón primario en el panel</li><li>1.ª fila de resultados</li></ul> | matriz completa + teclado + sin conexión |
| **Noticias** (`CHURCHES_NEWS`) | `DoctrineNews.kt` | L6 | <ul><li>`AppPageLayout(Hub)`, póster</li><li>contenido: noticias en tarjetas de imagen, 2 columnas (`minCell` 220)</li><li>la noticia abre el navegador interno</li></ul> | 1.ª fila de 2 tarjetas | matriz + cargando + sin conexión |
| **Calendario y Santuarios** (`PILGRIMAGE_HABITS`) | `PilgrimageAndHabitsScreen.kt` | L6 | <ul><li>`AppPageLayout(Hub)` con segmentos Calendario · Santuarios</li><li>**Calendario:** carril A con «Hoy» y el mes en rejilla; carril B con el día elegido</li><li>**Santuarios:** lista-detalle (§3.9)</li></ul> | <ul><li>tarjeta de hoy + mes completo (6 × 44 = 264 ≤ 359; a 800, 5 semanas y desplaza)</li><li>Santuarios: §8.3</li></ul> | matriz + vacío |
| **Doctrina e Historia** (`DOCTRINE`), **Doctrina** (`STUDY_DOCTRINE`) | `DoctrineAndHistoryScreen.kt`, `DoctrineMoral.kt` | L5 | <ul><li>lista-detalle: lista de mandamientos, sacramentos, etc. (`commandment_row_*`)</li><li>ficha L2 en el detalle, con «Leer a pantalla completa»</li></ul> | §8.3 | matriz |
| **Historia** (`STUDY_HISTORY`) | `DoctrineHistory.kt` | L5 | <ul><li>lista-detalle: línea del tiempo en la lista</li><li>las filas `epoch_row_*` llevan **título y fechas**, sin el resumen (que se elidía, E9)</li><li>el resumen completo va en la ficha</li></ul> | §8.3 | matriz |
| **Primeros pasos** (`BEGINNER`) | `BeginnerJourneyScreen.kt` | L6 | <ul><li>`AppPageLayout(Hub)`, póster con la barra de 7 pasos en la meta</li><li>contenido: tarjeta del paso actual (lectura a la medida)</li><li>pie del contenido: «‹ Paso anterior» · **«Siguiente paso ›»**</li></ul> | <ul><li>paso actual: título + 3 líneas</li><li>pie</li></ul> | matriz |
| **Lista de una categoría de oraciones** (`prayer_category`) | `PrayersAndNovenasScreen.kt` | L4 | <ul><li>`AppPageLayout(Hub)` con `AppDetailHeader`</li><li>ranura: recuento («93 oraciones»)</li><li>contenido: chips de intención adhesivos (fila con desplazamiento horizontal) y filas en 1 columna</li></ul> | <ul><li>chips</li><li>≥ 3 filas</li></ul> | matriz |
| **Parte del Catecismo** (`catechism_part`) | `CatechismScreen.kt` | L5 | <ul><li>lista-detalle: contenido de la parte (secciones, `ccc_node_*`) en la lista</li><li>hijos en la ficha</li><li>el artículo abre L2</li></ul> | §8.3 | matriz |
| **Compendio** (`compendium`) | `CatechismScreen.kt` | L5 | <ul><li>`AppPageLayout(Hub)` sin arte (panel de título)</li><li>ranura: aviso editorial (meta), búsqueda (`compendium_search_input`) y «Parte: Todas»</li><li>contenido: preguntas y respuestas en 1 columna a la medida</li></ul> | <ul><li>búsqueda</li><li>1.ª pregunta con su respuesta</li></ul> | matriz + teclado + sin resultados |
| **Ficha del curso** (`study_overview_page`) | `ScriptureStudyScreen.kt` | L4 | <ul><li>`AppPageLayout(Hub)` con el arte del curso y el progreso en la ranura</li><li>contenido: lecciones</li><li>pie del contenido: **«Continuar ›»** (`study_continue_button`) al final</li></ul> | <ul><li>progreso</li><li>≥ 3 lecciones</li><li>pie</li></ul> | matriz |
| **Hábitos y virtudes, Vida espiritual** (destinos absorbidos por Mi camino) | — | L6 | llevan a su sección de Mi camino (§9.1) | — | — |

Los avisos breves (`AppNotice`, §3.14) siguen la misma regla en todas estas pantallas.

---

## 16. Estados por área

Cada estado se diseña **dentro** de la disposición horizontal: el panel se mantiene y el estado ocupa la columna de contenido.

| Área | Estado | Panel | Dónde y cómo | Etiqueta | Captura |
|---|---|---|---|---|---|
| L3 Hoy | Liturgia cargando | normal | carril B: la tarjeta de liturgia con su cabecera y un marcador de 3 líneas | `daily_missal_loading` | `l3_home_*_liturgia-cargando` |
| L3 Hoy | Liturgia con error | normal | carril B: `ContentLoadNotice` con «Reintentar» dentro de la tarjeta | `daily_missal_error` (nueva) | `l3_home_*_liturgia-error` |
| L2 Biblia | Capítulo cargando | normal | columna de texto: `bible_verses_loading` centrado | `bible_verses_loading` | `l2_bible_*_cargando` |
| L2 Biblia | Libro no disponible en la edición | normal | columna de texto: título + texto + «Usar Biblia incluida» (`bible_use_bundled`) | `bible_use_bundled` | `l2_bible_*_sin-libro` |
| L2 Lectores | Contenido sin abrir | normal | columna de texto: `ContentLoadNotice` | `content_load_notice` (nueva) | `l2_*_error` |
| L4 Novenas | Filtros sin resultados | normal | contenido: filtros adhesivos visibles + `AppEmptyState` «Ninguna novena coincide» + «Quitar filtros» | `novena_filter_empty` (nueva) | `l4_novenas_*_vacío` |
| L4 Oración | Oración cargando | normal | columna de texto: «Cargando oración…» | existente (`AppEmptyState`) | `l4_prayer_*_cargando` |
| L5 Búsqueda | Sin resultados | — | bajo la barra: `AppEmptyState` (`search_empty`) con sugerencias | `search_empty` | `l5_search_*_vacío` |
| L5 Búsqueda | Buscando | — | bajo la barra: `search_loading` | `search_loading` | `l5_search_*_cargando` |
| L5 Listas | Filtro sin resultados | lista | lista: `AppEmptyState` + «Quitar filtros»; ficha: arte de la sección enmarcado | `list_empty` (nueva) | `l5_saints_*_vacío` |
| L5 Compendio | Sin resultados | panel de título | contenido: `ccc_search_empty` | `ccc_search_empty` | `l5_compendium_*_vacío` |
| L5 Planes | Ningún plan empezado | póster | ficha: el plan destacado con «Empezar hoy» en el pie | `plan_detail_screen` | `l5_plans_*_sin-plan` |
| L6 Recordatorios | Sin recordatorios activos | póster | contenido: `AppEmptyState` + «Activar el Ángelus» | `reminders_empty` (nueva) | `l6_reminders_*_vacío` |
| L6 Recordatorios | Notificaciones desactivadas | normal | panel: aviso con «Activar» | existente | `l6_reminders_*_sin-permiso` |
| L6 Diario | Vacío | póster | lista: `AppEmptyState` + «Escribir la primera entrada» | `journal_empty` (nueva) | `l6_journal_*_vacío` |
| L6 Confesión | Lista vacía | normal | carril B: `confession_empty_list` | `confession_empty_list` | `l6_confession_*_vacía` |
| L6 Confesión | Contenido sin abrir | normal | contenido: `AppEmptyState` + reintentar | existente | `l6_confession_*_error` |
| L6 Parroquias | Sin conexión o error de Maps | normal | contenido: `parish_maps_error` + «Reintentar»; campo y botón siguen en el panel | `parish_maps_error` | `l6_parishes_*_sin-conexión` |
| L6 Noticias | Cargando / sin conexión | póster | contenido: `news_loading` / `AppEmptyState` + «Reintentar» | `news_loading`, `news_offline` (nueva) | `l6_news_*_cargando`, `_sin-conexión` |
| L7 Copia de seguridad | Error | — | hoja: `backup_error` sobre el `footer` | `backup_error` | `l7_backup_*_error` |
| L7 Informe de errores | Error de envío | — | hoja: `error_report_error` | `error_report_error` | `l7_error_report_*_error` |
| Todas | 130 % | igual | listón duro | — | §12 |
| Todas | Sin arte (el recurso no existe) | panel de título | mismo panel, sin arte | — | una por área |

---

## 17. Respuesta a la crítica 1

### 17.1 Defectos

| # | Defecto | Resolución | Dónde |
|---|---|---|---|
| 1 | Presupuesto del Rosario | <ul><li>**Adoptado y medido.** Panel `clamp(0,32 W, 256, 340)`; «Después» bajo el progreso; la intención y la meditación del anuncio pasan a la columna de contexto; miniatura con `H < 400`.</li><li>Presupuesto rehecho con S = 24 y B = 16, con el hueco V./R., «Oración opcional» y ×1,12, medido con las fuentes de la app. Resultado: 24/24 combinaciones a 891 y 12/12 a 800 sin guía, sin desplazar; `RosaryFitTest`.</li><li>**Discrepancia medida:** la guía **no** va a la columna de contexto. Allí mide 180–198 dp (891) y 218–236 (800), en una región de 203 / 152 que ya ocupan el antetítulo y el título (106): solo se verían 97 / 46 dp de guía, cortada a mitad en los dos tamaños.</li><li>En el panel de la oración la guía cabe en todas las combinaciones salvo una: **Padrenuestro con guía a 800 × 360**, que desplaza 22–52 dp.</li><li>Otras alternativas medidas y descartadas:<ul><li>título dentro de la tarjeta de guía: −46 dp;</li><li>transporte horizontal abajo: −68 dp en todos los casos;</li><li>panel de cuentas de 224: columna de contexto de 120 dp, con títulos de 6 líneas.</li></ul></li><li>Excepción declarada y vetable (§14.2).</li></ul> | §4.2, §4.3 |
| 2 | Acción que avanza en los lectores | **Adoptado:** pie fijo del texto con «‹ Anterior» al inicio y la acción que avanza al final (≤ 120, `y ≥ 0,6 H`); el pie del panel solo con Escuchar y Aa; la Biblia por debajo de 860, igual. | §5.1, §5.2 |
| 3 | Panel del lector a 800 | **Adoptado y simplificado:** en `LandscapePhone`, el índice va siempre plegado en una fila de 44 con la parte actual, que abre una hoja (medido: quedan 122 / 94 dp). | §5.1 |
| 4 | Rejilla 3 × 3 de días | **Adoptado con un ajuste:** fila «Día N de M ▾» (44) + «Todos los días» en hoja con rejilla de 7 columnas de 44. Las flechas ‹ › van al pie del texto (§5.1) para no duplicar controles. Se conservan `novena_day_strip` y `novena_day_pill_N`. | §5.3 |
| 5 | `S` = 0 y teclado optimista | **Adoptado:** arnés con S = 24, B = 16, gestos de 32 y teclado de 0,56 H; todos los presupuestos rehechos. | §2.2, §3.13, §4.3, §6.3, §7.1, §8.3–8.6, §9.2, §9.5, §10.3 |
| 6 | Banda del 33 % | **Adoptado:** `S + 84` = 108 (26 % / 30 %). | §3.3 |
| 7 | Estado que cambia de padre | **Adoptado:** sin `rememberSaveable` en `header` ni `supporting` (con lint); estado elevado; giro con texto escrito; ficha apilada → selección explícita. | §2.4, §3.13 |
| 8 | Cobertura y avisos | **Adoptado:** 11 pantallas añadidas con patrón, esenciales y capturas; `AppNotice` sobre el pie de la columna activa, ≤ 480. | §15, §3.14 |
| 9 | Estados | **Adoptado:** tabla de 23 estados con panel, etiqueta y captura. | §16 |
| 10 | Panel muerto | **Adoptado:** variante póster. | §3.2 |
| 11 | Filas imposibles y arte perdido | <ul><li>**Adoptado:** barra adhesiva de ≤ 56 con botón de filtro y hoja; fila de chips solo con filtros activos; tira de 96 con título que desaparece; excepción en la rúbrica 2.</li><li>**Criterio ajustado con medida:** con la tira visible, a 891 caben 2 filas reales completas (108 + 64 = 172 ≤ 219); 3 filas (258) piden quitar la tira. Por eso: ≥ 3 filas tras desplazar la tira a 891 y ≥ 2 a 800.</li></ul> | §3.9, §8.3, §11 |
| 12 | Lectores embebidos | **Adoptado:** Misa, «El sacramento» y los oficios de Noche son `kind = Reader` o van dentro de una pantalla inmersiva, con medida de 66 caracteres. | §5.3, §7.2, §9.2 |
| 13 | Confesión | **Adoptado:** `kind = Immersive`, carriles de 293 / 260 y boceto corregido. | §9.2 |
| 14 | Hábitos desborda | **Adoptado:** el calendario pasa al carril A; el panel lleva cabezal y anillo (337 ≤ 395). | §9.1 |
| 15 | Búsqueda | **Adoptado:** sin arte; barra adhesiva de 56 (atrás, campo, borrar); chips ocultos con el teclado; ≥ 1 fila con el teclado a 800 (78 ≥ 64). | §8.6 |
| 16 | Hilo sin definir | **Adoptado:** hacia arriba avanza, 24 dp por cuenta, tic por cuenta, geometría cerrada, `x = max(safeStart, gestos) + 8`, aceptación con gestos de 32. | §4.2 |
| 17 | Jerarquía de Hoy | **Adoptado:** un primario («Leer las lecturas»); panel `clamp(0,28 Wc, 208, 280)`; carriles de 264 / 228; captura en PT. | §6 |
| 18 | Etiquetas del riel | **Adoptado (2.ª opción):** etiquetas completas con presupuesto de 2 líneas (304 ≤ 320) y riel de 4 destinos descrito. | §3.5 |
| 19 | Medida | **Adoptado:** `TextMeasurer` en el estilo real; tabla rehecha con las fuentes medidas (Garamond 19: 66 caracteres ≈ 455 dp, no 576). | §3.10 |
| 20 | Clase de ventana | **Adoptado:** `LocalWindowInfo.containerSize` o `androidx.window`; `CompactPhone` con banda y hojas laterales. | §2.1 |
| 21 | Umbral unificado | **Adoptado** en §2.3 y en la rúbrica 4. | §2.3, §11 |
| 22 | Desbordes de L7 | **Adoptado:** calendario con navegación a la izquierda y rejilla a la derecha (284 ≤ 304); «Tiempo para hoy» a la izquierda en la bienvenida; «Voces» como lista vertical; «Listo» sustituye a «Cerrar». | §10, §4.4 |
| 23 | Arte que crece | **Adoptado:** la línea nunca se mueve; el título crece hacia arriba y, si no basta, desplaza el panel. | §3.2 |
| 24 | Rosario y Biblia a mano | **Adoptado:** `AppSupportingPaneLayout`, declarado en `AppComponents.kt`. | §3.4, §4, §5.2 |

### 17.2 Resumen para el dueño

| # | Problema | Resolución |
|---|---|---|
| 1 | La decisión 2 prometía de más | Reescrita con la excepción exacta (Padrenuestro con guía en teléfono pequeño) y con Credo, Salve y letanías como «desplazan siempre». |
| 2 | «Atrás» y «Oír» | Ahora dice «Anterior» y «Escuchar», como en la app. |
| 3 | Pestañas recortadas y 5.ª inexistente | El boceto muestra 4 pestañas con su nombre completo y una nota sobre «Mi camino» (T-046). |
| 4 | «Una sola columna» | Reescrito: Hoy pasa a una columna; los centros pierden una columna de tarjetas. |
| 5 | «Las imágenes siempre se ven» | Reescrito: arte en paneles y fichas (miniatura); Búsqueda no lleva arte. |
| 6 | Controles de la Biblia | Reescrito: abajo a la derecha en el panel de estudio (≥ 860), o al pie del texto con «Siguiente» a la derecha. |
| 7 | Sin enlaces a las capturas | Enlaces a `docs/design/antes/`. |
| 8 | «Un tercio» del Rosario | Ahora es cierto: el panel mide el 32 % del ancho (285 de 891). |
| 9 | El selector Rosario/Coronilla, en sitios distintos | Un solo sitio en el boceto y en la especificación: la fila superior del panel de las cuentas, junto a «atrás». |
