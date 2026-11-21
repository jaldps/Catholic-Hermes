# Interfaz horizontal (landscape) · Especificación de diseño

Fase D del bloque T-047…T-05x («método horizontal reforzado», `docs/tasks/PLAN-NOV-2026.md`). Este documento es **diseño, no código**: describe qué debe construirse, dónde, con qué medidas y cómo se comprueba. Ningún archivo de `app/` cambia en esta fase.

- Base: versión 1.8.28 (HEAD `83b3766`), con la auditoría T-045 (`appLargeText`, `appMaxLines`, `NarrowLargeTextAuditTest`).
- Lo que ya existe para ventanas anchas (T-043) sigue valiendo para tabletas: riel desde 600 dp, columna centrada de 680 dp, dos paneles desde 840 dp.
- Objetivo del dueño: «Quiero una interfaz horizontal práctica, profesional, no simplemente que se vea la misma interfaz de vertical pero estirada. El espacio es diferente; ergo, la configuración visual debe ser diferente.»

Índice: [§1 Evidencia](#1-evidencia-lo-que-se-ve-hoy-en-horizontal) · [§2 Principios](#2-principios) · [§3 L0 Fundamentos](#3-l0--fundamentos-en-uidesign) · [§4 L1 Rosario](#4-l1--rosario-y-coronilla) · [§5 L2 Lectores](#5-l2--lectores) · [§6 L3 Hoy](#6-l3--hoy) · [§7 L4 Orar y Leer](#7-l4--centros-orar-y-leer) · [§8 L5 Listas con ficha](#8-l5--listas-con-ficha) · [§9 L6 Mi camino, Confesión, Recordatorios, Explorar](#9-l6--mi-camino-confesión-recordatorios-y-explorar) · [§10 L7 Hojas, diálogos, bienvenida y ajustes](#10-l7--hojas-diálogos-bienvenida-y-ajustes) · [§11 Rúbrica](#11-rúbrica-del-crítico) · [§12 Matriz de capturas](#12-matriz-de-capturas) · [§13 Teléfono real](#13-lista-de-comprobación-en-el-teléfono-real) · [§14 Riesgos y decisiones abiertas](#14-riesgos-y-decisiones-abiertas)

Convenciones:
- Todas las medidas en dp. `W` y `H` = ancho y alto de la ventana. `S` = barra de estado (24–28 dp en un teléfono real; 0 en Robolectric). `B` = inset inferior (barra de gestos, ≈ 16 dp; 0 con tres botones laterales).
- Los bocetos ASCII están a **891 × 411 dp**, con 1 columna ≈ 10 dp y 1 fila ≈ 20 dp.
- «Fijo» = no se desplaza. «Desplaza» = tiene su propio desplazamiento vertical.
- Las etiquetas de test entre comillas invertidas existen hoy y **se conservan**. Las marcadas «(nueva)» son añadidos.

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

---

## 2. Principios

**P1 · La restricción es la altura.** En horizontal, un teléfono mide 800–932 × 360–430 dp. Sobra anchura y falta altura. Todo lo que en vertical se apila (arte → título → controles → contenido) pasa a **ponerse en paralelo**: lo que orienta va a un lado y lo que se lee o se usa, al otro.

**P2 · Patrones adaptativos establecidos, en nuestro idioma visual.** Se usan los layouts canónicos de Material 3, dibujados con los materiales de cada identidad:
- **riel de navegación** en lugar de la barra inferior;
- **panel de apoyo** (supporting pane) para lectores y Rosario;
- **lista-detalle** para catálogos;
- **feed en carriles** para Hoy y los centros;
- **hojas laterales** en lugar de las inferiores.

**P3 · Nada se oculta ni se encoge.** Todo el contenido, las etiquetas, los valores y las etiquetas de test del vertical siguen presentes. El texto nunca baja del tamaño que eligió el usuario. Si algo no cabe, se desplaza; no se trunca. El arte solo puede reducirse, recortarse a su marco o pasar a miniatura; nunca tapa texto ni controles.

**P4 · Una sola fuente de verdad.**
- La clase de ventana se calcula una vez, en `ui/design` (`appWindowClass()`).
- Ninguna pantalla mide `screenWidthDp` ni `orientation` por su cuenta. Hoy lo hacen `BibleScreen` (orientación) y `RosaryScreen` (`maxWidth >= 600`); deben migrar.
- Las pantallas cambian de disposición a través de los wrappers de `AppComponents.kt`.

**P5 · Pulgares en los bordes.** El teléfono en horizontal se sujeta con las dos manos. Las zonas cómodas son los dos **tercios inferiores de los bordes laterales**:
- izquierda: navegación y herramientas secundarias;
- derecha: la acción que hace avanzar (Siguiente, Guardar, Abrir).

Ver §2.3.

**P6 · El giro no cambia el estado.** Girar el teléfono conserva todo lo que el usuario estaba haciendo; ver §2.4.

**P7 · Cada identidad sigue siendo ella misma.** El panel lateral es el mismo cabezal con otra disposición, no un componente nuevo con estética propia; ver §2.5.

**P8 · Coherencia con el vertical.** Mismo orden de lectura, mismos textos, mismos iconos y mismas acciones. Quien conoce la app en vertical la reconoce en horizontal al primer vistazo: el título está donde estaba el arte y el contenido, donde seguía después.

### 2.1 Clases de ventana

Se calculan con el **tamaño de la ventana** (sigue la pantalla dividida y los plegables), en dp:
- `W = LocalConfiguration.screenWidthDp`;
- `H = LocalConfiguration.screenHeightDp`.

Ambos incluyen las barras del sistema en las API con edge-to-edge obligatorio. Si una versión de Android los diera sin barras, se usa `LocalWindowInfo.containerSize`. El implementador de L0 lo verifica y deja un test.

| Clase (`AppWindowClass`) | Condición | Navegación | Disposición |
|---|---|---|---|
| `Phone` | `W < 600` | barra inferior | la de siempre, sin cambios |
| `LandscapePhone` («compacta en altura») | `H < 480` y `W ≥ 600` | riel compacto (§3.5) | **este documento** |
| `TabletPortrait` | `600 ≤ W < 840` y `H ≥ 480` | riel (T-043) | columna centrada de 680 dp (T-043) |
| `TabletLandscape` | `W ≥ 840` y `H ≥ 480` | riel (T-043) | <ul><li>dos paneles (T-043);</li><li>con L3/L4, Hoy, Orar, Leer, Mi camino y Explorar usan el panel lateral y 3 carriles desde `W ≥ 1200`.</li></ul> |

- Una ventana de `W < 600` y `H < 480` (pantalla dividida pequeña) sigue siendo `Phone`.
- Una tableta dividida en horizontal (p. ej. 1280 × 400) es `LandscapePhone`, a propósito: tiene el mismo problema de altura.
- **Prioridad:** `H < 480` manda sobre la anchura. A 891 × 411, hoy `appIsTwoPane()` devuelve verdadero; con L0 depende de la clase (§3.9).
- No hace falta histéresis: la clase solo cambia con la configuración.

### 2.2 Insets: recorte de cámara, barra de gestos lateral y teclado

`targetSdk = 36` con `enableEdgeToEdge()`. La app dibuja bajo todas las barras y bajo el recorte, así que el andamiaje debe apartar el contenido de ellos.

| Inset | Valor típico en horizontal | Lado | Regla |
|---|---|---|---|
| `statusBars` | 24–28 | arriba | <ul><li>El arte del panel lateral dibuja debajo (igual que el cabezal vertical).</li><li>El texto y los controles empiezan en `S + 8`.</li></ul> |
| `displayCutout` | 0–40 | izquierda **o** derecha, según el giro (90° o 270°) | <ul><li>Ningún texto ni control entra en él.</li><li>El arte y los fondos sí: quedan a sangre.</li></ul> |
| `navigationBars`, tres botones | 42–48 | lateral (el lado del borde inferior del teléfono) | Igual que el recorte: se suma al margen de ese lado. |
| `navigationBars`, gestos | 12–20 | abajo | <ul><li>El último elemento de cada columna termina en `H − B − 8`.</li><li>Las barras de acciones fijas se apoyan en `H − B`.</li></ul> |
| `systemGestures` laterales | 24–32 | izquierda y derecha | <ul><li>Ningún control **arrastrable** (hilo de cuentas, deslizadores) empieza a menos de esa distancia del borde.</li><li>Si no hay alternativa, se usa `Modifier.systemGestureExclusion()` sobre ≤ 200 dp de alto (límite de Android).</li></ul> |
| `ime` (teclado) | 170–230 en horizontal (≈ 50 % de `H`) | abajo | Ver «Teclado» abajo. |

**`appSafeInsets()`** es un helper nuevo en `LumenFoundation.kt`. Devuelve, por lado, `max(statusBars, navigationBars, displayCutout)`. El andamiaje lo aplica así:
- **Inicio:** el riel se coloca después de `safeStart`. Sin riel (pantallas inmersivas), el primer panel añade `safeStart` a su margen interior. El arte sigue a sangre.
- **Fin:** la última columna añade `safeEnd` a su margen (`max(gutter, safeEnd + 12)`).
- **Arriba y abajo:** como hoy (`S`, `B`).

`ScreenFrame.insets` pasa a llevar `start` y `end`. Hoy solo lleva `top` y `bottom`.

**Teclado** (P3 + rúbrica 7). Con el teclado abierto quedan ≈ 180–200 dp de alto.
- **El campo con foco y la acción principal se ven siempre.**
  - La columna que contiene el campo lleva `imePadding()` y hace `bringIntoView` del campo.
  - La acción principal (Guardar, Buscar, Enviar) vive en una barra fija de esa columna, por encima del teclado. No va al final del contenido desplazable.
- **Lo que no contiene el campo no se mueve.** En una disposición de dos columnas, solo la columna del campo se encoge; el panel lateral sigue igual. Así, al buscar se siguen viendo resultados.
- **Teclado a pantalla completa.** Android puede mostrar el modo «extract» (teclado a pantalla completa) en horizontal. Compose pide `IME_FLAG_NO_FULLSCREEN` en sus campos; se comprueba en el teléfono real (§13). Los campos que no sean de Compose, si los hubiera, lo piden explícitamente.

### 2.3 Ergonomía de los pulgares

```
 891 × 411, teléfono sujeto con las dos manos
┌───────────────────────────────────────────────────────────────────────────────────────┐
│ difícil ··························· difícil ························· difícil         │  y < 0,25H: solo «atrás»,
│                                                                                       │  título y acciones raras
│ ┌───────────────┐                                              ┌───────────────┐     │
│ │ PULGAR        │            centro: lectura y desplazamiento  │ PULGAR        │     │  0,35H–1,0H
│ │ IZQUIERDO     │            (se desliza, no se apunta)        │ DERECHO       │     │
│ │ riel, cuentas,│                                              │ Siguiente,    │     │
│ │ herramientas  │                                              │ Guardar,      │     │
│ │ ≈ 200 dp      │                                              │ Abrir ≈ 200 dp│     │
│ └───────────────┘                                              └───────────────┘     │
└───────────────────────────────────────────────────────────────────────────────────────┘
```

Reglas:
- **Objetivos.** Todo objetivo táctil mide **≥ 44 × 44 dp** (norma de la app y del detector). Las acciones primarias, **≥ 48 × 48**; el «Siguiente» del Rosario, ≥ 64 × 64.
- **Acción que avanza.** Cada pantalla secuencial (Rosario, día de novena, lección, día de plan, capítulo) coloca su acción de avance a menos de 200 dp del borde derecho seguro y por debajo de `0,4 H`.
- **Navegación entre secciones.** El riel, los segmentos de los centros y el índice de los lectores van en el tercio izquierdo.
- **Franja superior.** En los 56 dp de arriba solo van «atrás» (poco frecuente y en la misma posición que en vertical) y las acciones del cabezal (buscar, avisos), que también están ahí en vertical.

### 2.4 Continuidad al girar

La actividad **se recrea** al girar: no hay `configChanges` en el manifiesto. Debe sobrevivir:

| Estado | Mecanismo actual | Acción requerida |
|---|---|---|
| Destino, pila y pestaña | `rememberSaveable` en `MainApp` | ninguna |
| Segmento de los centros (Oraciones/Novenas/Noche; Biblia/Misa/Estudio; Confesión) | `rememberSaveable` | <ul><li>comprobar que la clave no cambia de disposición (ver «Estabilidad del árbol» abajo)</li></ul> |
| Rosario: devoción, misterio, paso, guía | `RosarySession` + `RosarySessionStore` | ninguna |
| Rosario: hoja abierta | `remember` | <ul><li>**`rememberSaveable`**</li></ul> |
| Rosario: «rezar en voz alta» activo | `remember` + `ScreenLeft` en `onDispose` | <ul><li>**No enviar `ScreenLeft` si la actividad está cambiando de configuración** (`activity.isChangingConfigurations`).</li><li>Conservar `NarrationController` (holder de ámbito de actividad, `ViewModel`).</li></ul> |
| Posición de lectura (capítulo, oración, día de novena, artículo, lección, santo) | `LazyListState` o `ScrollState` | <ul><li>**Guardar un ancla, no píxeles:** índice del primer párrafo o versículo visible + fracción.</li><li>Al girar cambia el ancho, así que un desplazamiento en píxeles cae en otro sitio.</li><li>Helper nuevo `rememberReadingAnchor()` (§3.10).</li></ul> |
| Biblia: libro, capítulo, versículo de estudio | `rememberSaveable` | ninguna; el versículo de estudio pasa a ser la selección del panel o de la hoja de estudio |
| Día de novena seleccionado | `rememberSaveable` | ninguna |
| Texto escrito: búsqueda, diario | `rememberSaveable` | ninguna |
| Texto escrito: Confesión (lista y notas) | **se borra a propósito** | <ul><li>**Conservar durante el giro sin tocar el Bundle:** mover `ConfessionDraft` a un holder de ámbito de actividad (`ViewModel`) que no se guarda en el estado.</li><li>Sigue borrándose al salir de la pantalla, al pasar a inactiva y al morir el proceso, como hoy.</li></ul> |
| Texto escrito: copia de seguridad (frase o fichero), informe de error | sin `rememberSaveable` | <ul><li>**`rememberSaveable`** en los campos no secretos.</li><li>La contraseña de la copia, si la hay, **no** se guarda; se pide de nuevo.</li></ul> |
| Selección de lista-detalle | `rememberSaveable` (Santos) o pila (Órdenes, Devociones) | <ul><li>distinguir selección **explícita** de selección **por defecto** (§3.9)</li></ul> |
| Hojas y diálogos abiertos, con su desplazamiento | variable | <ul><li>**`rememberSaveable`** para «qué hoja está abierta» en todas las pantallas</li></ul> |
| Filas expandidas («Más para hoy», secciones del examen) | `rememberSaveable` | ninguna |

**Estabilidad del árbol** (riesgo técnico principal). `rememberSaveable` restaura por la posición en el árbol de composición. Si un wrapper hace `if (horizontal) Row { content() } else Column { content() }`, el contenido cambia de grupo y su estado **no se restaura** al girar.

Reglas para todos los wrappers de L0:
1. Llamar a `content` desde **un único punto**.
2. Cambiar la disposición dentro de una política de `Layout` (medir y colocar), nunca con ramas `if` que reparenten el contenido.
3. El estado crítico (sesión del Rosario, ancla de lectura, segmento, selección) usa claves explícitas: `rememberSaveable(key = "…")`.

El test de giro (§3.13) lo comprueba.

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

Lo hace un agente local antes que ninguna área. Ninguna pantalla debe medir ventanas, insets ni anchos de panel por su cuenta.

### 3.1 Andamiaje base

```
 0      80                          372                                                    891
┌───────┬─────────────────────────────┬──────────────────────────────────────────────────────┐ 0
│ (S)   │ (S) ‹                  ⌕  ◔ │ (S)                                                  │
│ ╭───╮ │ ╔═════════════════════════╗ │  ÁREA DE CONTENIDO · desplaza                        │
│ │ ⌂ │ │ ║ ARTE recortado al panel ║ │  ┌────────────────────────────────────────────────┐ │
│ │Hoy│ │ ║ foco HeroMeta, bruma    ║ │  │ carriles, rejilla, lista o texto               │ │
│ │ † │ │ ║                         ║ │  │ (según el área)                                │ │
│ │Ora│ │ ║ Antetítulo              ║ │  └────────────────────────────────────────────────┘ │
│ │ ▯ │ │ ║ Título (largeTitle)     ║ │  ┌────────────────────────────────────────────────┐ │
│ │Lee│ │ ╚═════════════════════════╝ │  │                                                │ │
│ │ ◇ │ │  meta                       │  │                                                │ │
│ │Cam│ │  ┌───────────────────────┐  │  └────────────────────────────────────────────────┘ │
│ │ ▦ │ │  │ RANURA DE APOYO       │  │                                                      │
│ │Exp│ │  │ segmentos, búsqueda,  │  │                                                      │
│ ╰───╯ │  │ índice, herramientas  │  │                                                      │
│       │  └───────────────────────┘  │                                                      │
│       │  (el panel desplaza solo si │                                                      │
│       │   no cabe: 130 %, títulos)  │                                                      │
└───────┴─────────────────────────────┴──────────────────────────────────────────────────────┘ 411
  riel     PANEL DE PORTADA 292 · fijo    contenido 519 · margen interior 20 · desplaza
  80       (0,36 · Wc)
```

Ancho útil `Wc = W − riel − safeStart − safeEnd`. El riel desaparece en las pantallas inmersivas (§3.5). Tres familias de pantalla comparten este andamiaje:
- **centro / raíz:** panel de portada + contenido;
- **lector:** panel de lector + columna de lectura (§5);
- **lista-detalle:** lista + ficha (§3.9).

### 3.2 Panel de portada: el mismo cabezal, en vertical

`AppHeroHeader`, `AppDetailHeader` y `AppLargeTitleHeader` **no cambian de firma**. Cambia dónde se dibujan, a través de un `CompositionLocal` nuevo:

```kotlin
enum class AppHeaderPlacement { InList, SidePanel, DetailPane, CompactBand }
internal val LocalHeaderPlacement = staticCompositionLocalOf { AppHeaderPlacement.InList }
```

`AppHeaderFrame` (en `LumenComponents.kt`) dibuja cada colocación con los mismos datos: arte, antetítulo, título, meta, acciones y etiquetas de test (`titleTag`, el `modifier` con `screen_header_illustration`, `more_hero_photo`…). Las etiquetas acompañan al cabezal adonde vaya.

**Geometría del panel de portada (`SidePanel`):**

| | Valor | 891 × 411 | 800 × 360 |
|---|---|---|---|
| Ancho, centros y raíces | `clamp(0,36 · Wc, 248, 320)` | 292 | 259 |
| Ancho, Hoy, Mi camino y Confesión (`kind = Today`) | `clamp(0,30 · Wc, 224, 280)` (deja dos carriles ≥ 220) | 243 | 224 |
| Ancho, lectores (sin riel) | `clamp(0,30 · W, 224, 280)` | 267 | 240 |
| Alto | toda la ventana; dibuja bajo la barra de estado y la de gestos | 411 | 360 |
| **Zona de arte** (`AppSidePanelArtHeight`) | `clamp(0,46 · H, 160, 196)`, incluida `S` | 189 | 166 |
| Línea del título | borde inferior de la zona de arte − 14 dp (`HeaderTextBottom`, igual que en vertical) | y = 175 | y = 152 |
| Margen interior | `gutter` de la identidad (20) + `safeStart` si no hay riel | 20 | 20 |

**Una sola línea de título en todas las pantallas horizontales.** Es la traducción directa de la regla «236 dp de arte, título en la misma línea base»: todos los títulos de panel se apoyan en la misma línea (`0,46 H − 14`). El crítico lo comprueba superponiendo capturas.

**Recorte del arte:**
- `ContentScale.Crop` centrado en el foco de `HeroMeta` (`focusX`, `focusY`; ya existe para el Rosario y los cabezales).
- Recortado al rectángulo del panel (`clip`): nunca invade el contenido.
- **Lumen:**
  - fundido inferior desde el 30 % (`fadeStart = 0.30`, como hoy);
  - fundido lateral solo en el **borde final**: 24 dp hacia la luz ambiental (`fadeEnd`, variante de `fadeSides`).
- **Cristal:** sin fundido lateral; el arte llega al borde y el cristal del contenido lo refracta.
- **Tradicional:** fundido inferior y filete de 0,5 dp en el borde final.
- **Sanctuary:**
  - título arriba (`S + 16`), sobre el papel;
  - arte en marco debajo, a `panel − 2·gutter` de ancho y `0,46 H − 72` de alto (≈ 117 dp a 411; mínimo 96);
  - la línea del título es la del papel, igual en todas las pantallas Sanctuary.
- **Texto grande** (`appLargeText()`, ≥ 1,25): igual que en vertical, el título va sobre el lienzo y el arte enmarcado debajo, con 96 dp de alto. El panel desplaza.

**Título:**
- Rango `largeTitle` en raíces y `title1` en el resto (regla vigente).
- La prueba «¿cabe la palabra más larga?» mide contra el **ancho del panel** (`constraints.maxWidth`), no contra `screenWidthDp`. Con eso «Recordatorios» o «Constantinopolitano» bajan a `title1` en el panel igual que a 320 dp.
- Un título de más de 2 líneas en `largeTitle` también baja a `title1`.
- Si aun así pasa de 3 líneas, la zona de arte crece hacia abajo **solo en esa pantalla**. El título nunca se trunca.

**Acciones del cabezal** (buscar, avisos, traducir, marcador):
- Círculos de 44 dp en la esquina superior final del panel (`S + 2`), sobre el arte, como en vertical.
- Sanctuary las pone en línea con el título, también como en vertical.

**Atrás:** el botón único del andamiaje (`nav_back`) se dibuja en la esquina superior inicial del **panel**, no del área total. Su posición respecto al arte es la de vertical.

**Ranura de apoyo** (`supporting`), bajo la meta: segmentos verticales (§3.12), campo de búsqueda, índice, herramientas del lector y tarjeta del día. Reglas:
- Cabe sin desplazar a 891 × 411 al 100 % en todos los centros: lo comprueba el detector.
- Si no cabe (130 %, PT), **todo el panel** desplaza como una columna. El arte no queda fijo.

**Lado del contenido:**
- Desplaza por su cuenta.
- Margen superior `S + 12`. No hay fila de 52 dp para «atrás», que vive en el panel.
- **No se dibuja la barra de título que aparece al desplazar** (`app_bar_title`): el título siempre está a la vista en el panel. El nodo sigue existiendo en vertical; en horizontal no se compone.
- Bajo la barra de estado, el contenido se funde (Lumen y Cristal: máscara de 24 dp) o pasa bajo una franja opaca del lienzo (Tradicional y Sanctuary: `canvasTop` al 95 % y filete).

### 3.3 Las otras tres colocaciones

| Colocación | Cuándo | Aspecto |
|---|---|---|
| `SidePanel` **sin arte** («panel de título») | `AppLargeTitleHeader`, o un detalle sin arte (artículo del Catecismo) | <ul><li>Mismo panel y ancho.</li><li>Título en la misma línea `0,46 H − 14`.</li><li>Sobre el lienzo, sin arte.</li></ul> |
| `CompactBand` («banda compacta») | <ul><li>Pantallas que necesitan todo el ancho para su contenido: resultados de búsqueda (§8.6) y lector bíblico (§5.2).</li><li>**Respaldo automático** de cualquier pantalla que todavía no use `AppPageLayout`.</li></ul> | <ul><li>Dentro del contenido, al principio, y **desplaza con él**.</li><li>Arte en tira de `S + 96` dp de alto, recortada al foco, con el título en su borde inferior − 14.</li><li>Rango de título sin cambios.</li><li>Sin arte: `S + 8` + bloque de título.</li><li>Ocupa como máximo el 30 % de `H`. Hoy ocupa el 56 %.</li></ul> |
| `DetailPane` («cabecera de ficha») | El detalle de un lista-detalle en `LandscapePhone` (§3.9) | <ul><li>Fila: miniatura de arte de 112 × 112 (retratos de santos y devociones: 104 × 130, proporción 4:5), recortada al foco y con radio `thumbnail` (Sanctuary: marco filetado).</li><li>A su lado: antetítulo, título `title1` (crece) y meta.</li><li>Debajo, las acciones (Escuchar…).</li></ul> |

**El respaldo `CompactBand` es la primera entrega de L0.** En cuanto L0 se fusiona, todas las pantallas ganan ≈ 130 dp de alto aunque su área no se haya rediseñado todavía. `AppHeaderFrame` elige `CompactBand` cuando `appIsLandscapePhone()` y no hay un `AppPageLayout` que pida `SidePanel`.

### 3.4 Wrapper `AppPageLayout`

Es el wrapper nuevo en `AppComponents.kt`. Es la única forma de tener panel lateral:

```kotlin
@Composable
fun AppPageLayout(
    header: @Composable () -> Unit,                          // la llamada a AppHeroHeader/AppDetailHeader de hoy, intacta
    modifier: Modifier = Modifier,
    kind: AppPageKind = AppPageKind.Hub,                     // Hub | Today | Reader | Immersive
    supporting: (@Composable ColumnScope.() -> Unit)? = null, // solo se compone en el panel (horizontal)
    supportingInPortrait: Boolean = true,                     // si la ranura también se dibuja en vertical, tras el cabezal
    content: @Composable (headerItem: @Composable () -> Unit) -> Unit
)
```

- **`Phone`, `TabletPortrait` y las tabletas que no adoptan el panel** (§2.1): `content { header(); if (supportingInPortrait) supporting?.invoke() }`. Es exactamente lo de hoy: el cabezal es el primer elemento de la lista.
- **`LandscapePhone`:**
  - el panel (ancho según `kind`) compone `header()` con `LocalHeaderPlacement = SidePanel` y luego `supporting`;
  - el área de contenido llama a `content { }` con un `headerItem` vacío.
  - Las dos llamadas a `content` son **la misma**, colocada por una política de `Layout`; ver «Estabilidad del árbol» (§2.4).
- Declara `AppFullWidth()` en horizontal: la columna centrada de 680 dp no aplica.
- `kind = Reader | Immersive` llama a `AppHideTabBar(true)` en horizontal (§3.5).
- Semántica:
  - panel y contenido son grupos de recorrido (`isTraversalGroup`), panel primero;
  - el orden de TalkBack es el de vertical: cabezal, ranura, contenido.

Cada pantalla migra cambiando `item { AppHeroHeader(…) }` por `item { headerItem() }` y envolviendo su cuerpo. Son 34 llamadas en 19 archivos (§3.13). Los segmentos y buscadores que hoy van tras el cabezal se mueven a `supporting`, con `supportingInPortrait = true` para que el vertical no cambie.

### 3.5 Riel compacto con 5 pestañas

`LandscapePhone` usa una variante compacta del riel de T-043, en `LumenKit.Rail`, `GlassRail` y `FolioRail`:

| | T-043 (tabletas) | Compacto (`LandscapePhone`) |
|---|---|---|
| Reserva de ancho | 92 (Sanctuary 88) | **80** (Sanctuary 76) + `safeStart` |
| Cápsula | 72 de ancho | **64**; con texto grande, 72 |
| Alto de destino | `68 + 44·(escala − 1)` | **`max(56, 4 + 24 icono + 3 + líneas × navLabel.lineHeight + 8)`**: 56 al 100 %; ≈ 60 al 130 % con 1 línea |
| 5 destinos al 100 % | 340 + 8 | **288** (cabe en 411 − 24 − 16 = 371 y en 360 − 24 − 16 = 320) |
| 5 destinos al 130 % | 405 (no cabe) | **≈ 308** (cabe en 320) |
| Etiquetas | completas, hasta 2 líneas | <ul><li>completas en 1 línea;</li><li>con texto grande, las cortas oficiales: «Hoy · Orar · Leer · Camino · Más» (ES), «Today · Pray · Read · Path · More» (EN), «Hoje · Orar · Ler · Caminho · Mais» (PT), con el mismo mecanismo que hoy usa «Más»</li></ul> |
| Si aun así no cabe (200 % del sistema) | crece | <ul><li>el riel **desplaza** verticalmente dentro de su cápsula, con el destino activo siempre visible;</li><li>nunca se trunca</li></ul> |
| Posición | centrado vertical | centrado vertical entre `S` y `H − B` (zona del pulgar izquierdo) |

- Los 5 destinos son Hoy · Orar · Leer · **Mi camino** (`nav_my_path`, de T-046) · Explorar.
- Etiquetas de test sin cambios: `navigation_rail`, `nav_*`.
- `selected` y la reselección (volver al inicio del centro) no cambian.

**Pantallas inmersivas:** en `LandscapePhone`, el riel **respeta `AppHideTabBar`**, como la barra inferior en vertical:
- se desliza hacia el borde inicial (`mediumMillis`; nada con «reducir movimiento»);
- devuelve sus 80 dp a la lectura;
- se vuelve con «atrás».

Afecta a Rosario, capítulo bíblico, oración, día de novena, Noche, artículo del Catecismo y lección. En tabletas se mantiene la decisión de T-043 (el riel no se oculta). *Decisión vetable (§14).*

### 3.6 Hojas laterales

En `LandscapePhone`, `AppSheet` (en `LumenExtras.kt`) se dibuja como **hoja lateral modal** anclada al borde final. Ninguna llamada cambia: las 31 llamadas a `AppSheet` de la app pasan a laterales con un solo cambio.

| | Valor | 891 | 800 |
|---|---|---|---|
| Ancho normal | `clamp(0,50 · W, 360, 480)` | 446 | 400 |
| Ancho amplio (`wide = true`, parámetro nuevo) | `clamp(0,66 · W, 480, 640)`, para hojas de dos columnas (§10) | 588 | 528 |
| Alto | <ul><li>de `S + 8` a `H − B − 8`;</li><li>Sanctuary: a todo el alto, pegada al borde</li></ul> | 371 | 320 |
| Separación de los bordes | 8 arriba y abajo; `safeEnd + 8` al final (Sanctuary: 0) | | |
| Forma | <ul><li>`dialog` de la identidad en las cuatro esquinas (Lumen 28; Tradicional y Cristal según tokens);</li><li>Sanctuary: esquinas iniciales 0 y filete de lomo</li></ul> | | |
| Entrada | desde el borde final (`slideInHorizontally`, `mediumMillis`); nada con «reducir movimiento» | | |
| Cierre | <ul><li>toque en el velo;</li><li>«atrás» (con atrás predictivo);</li><li>deslizar hacia el borde final;</li><li>**botón «Cerrar» de 44 dp** en la cabecera (`sheet_close`, nueva)</li></ul> | | |
| Cabecera | <ul><li>fila fija de 56 dp: título (`headline`, alineado al inicio; Sanctuary en EB Garamond) + «Cerrar» al final;</li><li>sustituye al asa de arrastre, que es una metáfora vertical</li></ul> | | |
| Contenido | desplaza dentro de la hoja | | |
| `footer` (parámetro nuevo, opcional) | <ul><li>barra fija al pie de la hoja para la acción principal (Guardar, Restaurar, Enviar);</li><li>lleva `imePadding()`: queda sobre el teclado</li></ul> | | |

- La etiqueta de cada hoja (`rosary_options_sheet`, `gui_customizer_sheet`…) sigue en el contenedor de la hoja.
- Semántica: `paneTitle` = título de la hoja; el foco de TalkBack entra en la hoja al abrirse y vuelve al control que la abrió al cerrarse.
- En vertical y en tabletas, `AppSheet` no cambia.

### 3.7 Diálogos de dos columnas

`AppDialog` (en `LumenExtras.kt`):
- Ancho: `min(W − 2·(safe + 24), 720)`; 720 a 891 y a 800 sin recorte (688 a 800 con un recorte de 32 dp).
- Alto máximo: `H − S − B − 32`.
- Se centra en la ventana. Hoy se ancla arriba a la izquierda en horizontal (E7).

**Parámetro nuevo `aside: (@Composable ColumnScope.() -> Unit)?`.** En `LandscapePhone` se compone así:

```
┌──────────────────────────────── 720 ─────────────────────────────────┐
│ COLUMNA DE CONTEXTO · 0,42 · fija      │ COLUMNA DE ELECCIÓN · desplaza │
│ Título (title2)                        │ opciones, campos               │
│ texto explicativo                      │ …                              │
│ (ilustración o vista previa)           │                                │
│                                        │ ────────────────────────────── │
│                                        │      [Secundaria] [PRINCIPAL] ▸│ ← acciones fijas, abajo a la derecha
└────────────────────────────────────────┴────────────────────────────────┘
```

- `aside` es la columna izquierda; `content` es la derecha. Las acciones van al pie de la columna derecha y nunca salen de la pantalla.
- En vertical, `aside` se compone antes de `content`, en una columna: lo mismo que hoy.

`AppConfirmDialog` (mensaje corto + dos botones):
- sigue en una columna, de 420 dp de ancho, centrado;
- si el mensaje no cabe en el alto, desplaza; la fila de botones queda fija.

Los diálogos propios fuera del sistema, como el editor de diario (`SpiritualLifeScreen.kt`, un `Dialog` crudo), se tratan en §10.

### 3.8 Rejillas y carriles

Helpers nuevos:
- **`appGridColumns(minCell: Dp, max: Int)`:** `floor((ancho interior + gap) / (minCell · k + gap))`, entre 1 y `max`, con `k = 1` al 100 % y `k = 1,25` con `appLargeText()`.
- **`AppGrid`:** su `columns` por defecto pasa a usar esta fórmula. Hoy fija 3, o 2 con texto grande.
- **`AppFeedColumns(lanes)`:** carriles con **asignación explícita** de elementos (`lane(0) { … }`, `lane(1) { … }`), no ajuste automático. Así el orden de lectura queda decidido: carril A completo y luego carril B. Es no perezoso, para pantallas de ≤ 20 bloques (Hoy, Mi camino, Explorar).

| Tipo de celda | `minCell` | Hub 891 (interior 479) | Hub 800 (interior 421) | Al 130 % (891 / 800) | Máximo |
|---|---|---|---|---|---|
| Tarjeta de imagen (novenas, guías, cursos) | 128 | 3 | 3 | 2 / 2 | 4 |
| Losa de categoría (icono + rótulo) | 112 | 3 | 3 | 3 / 2 | 4 |
| Fila compacta en rejilla (Explorar, secciones) | 220 | 2 | 1 | 1 / 1 | 3 |
| Fila de resultado (búsqueda, a lo ancho; §8.6) | 300 | 2 (interior 771) | 2 (interior 680) | 2 / 1 | 3 |
| Carril de feed (Hoy, Mi camino) | 220 | 2 (interior 528) | 2 (interior 456) | 1 / 1 | 3 (tableta) |

Separación entre celdas: 12 dp; entre carriles, 16 dp. En tableta (`TabletLandscape`, `W ≥ 1200`) los carriles suben a 3.

### 3.9 `AppTwoPane` con altura compacta

En `LandscapePhone`, `appIsTwoPane()` es verdadero para las pantallas de L5, con estas medidas:

| | `TabletLandscape` (T-043) | `LandscapePhone` |
|---|---|---|
| Panel de lista | 360 fijo | `clamp(0,36 · Wc, 272, 320)`: 292 (891) / 272 (800) |
| Panel de detalle | resto, centrado a 680 | resto: 519 / 448; sin centrar |
| Cabezal de la lista | `AppHeroHeader` en lista, con arte | <ul><li>**«Cabecera de lista» sin arte:** antetítulo + título (`largeTitle`), que desplaza con la lista.</li><li>La búsqueda y los filtros son **adhesivos** arriba (`stickyHeader`, 48 + 44).</li><li>El arte de la sección pasa a la ficha vacía (abajo).</li></ul> |
| Cabezal del detalle | `AppDetailHeader` con arte de 236 | `DetailPane` (§3.3) |
| Separador | 0,5 dp | <ul><li>0,5 dp (Tradicional, Cristal y Lumen);</li><li>lomo doble (Sanctuary)</li></ul> |

- **Selección:** sin selección explícita se abre la primera entrada (regla de T-043). La selección se guarda con una marca `explicit`.
- **Al girar a vertical:**
  - con selección explícita, la entrada se abre como página apilada (T-043);
  - con selección por defecto, se vuelve a la **lista**, no a una página que el usuario nunca abrió.
- **Lectura larga dentro de la ficha:** el texto del detalle respeta la medida (§3.10). Si la ficha es un lector largo (artículo del Catecismo, respuesta extensa), la ficha ofrece «Leer a pantalla completa» (`detail_open_reader`, nueva), que abre el lector L2.
- **Fila activa:** `LeadRow(selected)` / `AppListRow(selected = true)`, con `selected` en semántica (ya existe).

### 3.10 Medida de lectura

Helper nuevo **`appReadingMeasure()`**:
- Calcula el ancho de la columna de texto para unos **66 caracteres**: `33 × tamaño de reading` en dp (glifo medio ≈ 0,5 em).
- Lo acota a `[30 em, 37 em]` (≈ 60–75 caracteres).
- Usa el estilo `reading` **efectivo**: tamaño de la identidad × escala de la app × `fontScale` del sistema.

El texto se coloca en `min(ancho del panel − 2·gutter, appReadingMeasure())`, centrado en su panel.

| Lector | Ancho del panel de texto (891 / 800) | Columna (891 / 800) | Caracteres por línea: serif 19 (predeterminada) | Sans 18 |
|---|---|---|---|---|
| Lector L2 (panel 267 / 240, sin riel) | 624 / 560 | 576 / 512 | ≈ 70 / 62 | ≈ 64 / 57 |
| Biblia con estudio (§5.2) | 552 / — | 504 / — | ≈ 61 | ≈ 56 |
| Biblia sin estudio | 891 / 800 | medida (≈ 627) | 66 | 66 |
| Ficha de lista-detalle | 519 / 448 | 479 / 408 | ≈ 58 / 50 | ≈ 53 / 45 |

**Umbrales** (rúbrica 3):
- Lectores L2 a 891 × 411 al 100 %: 60–75 caracteres con la fuente predeterminada.
- A 800 × 360, y con la fuente sans: ≥ 56.
- Fichas de lista-detalle: ≥ 45, más el botón «Leer a pantalla completa».
- Al 130 %: ≥ 45 en lectores. Si una columna bajara de 45, el panel del lector pasa a `CompactBand` y la lectura recupera el ancho.

Lo mide el detector: media de caracteres por línea en el `TextLayoutResult` del primer párrafo largo.

**`rememberReadingAnchor(listState | scrollState, key)`** (para el giro):
- Guarda en `rememberSaveable` el índice del primer bloque visible y su fracción.
- Al restaurar, desplaza a ese bloque. Sirve para lectores con `LazyColumn` o con `Column` + `verticalScroll`, en este caso con `onGloballyPositioned` de los párrafos.

### 3.11 Espaciado y márgenes

| Token | Valor en horizontal | Nota |
|---|---|---|
| Margen exterior (al borde seguro) | `gutter` de la identidad (20) | más `safeStart` o `safeEnd` |
| Margen interior de cada panel | 20 | idéntico al vertical |
| Separación entre panel y contenido | 0 + separador de la identidad (§2.5) | el ritmo lo dan los márgenes interiores (20 + 20) |
| Separación entre carriles | 16 | |
| Separación entre celdas de rejilla | 12 | como `AppGrid` hoy |
| Separación entre secciones | `sectionGap` (28) | sin cambios |
| Inicio del contenido | `S + 12` | sin la fila de 52 de «atrás» |
| Fin del contenido | `B + 24` | |
| Barras fijas inferiores | 56–64 de alto + `B` | Rosario, lectores y hojas con `footer` |

Todos los valores son múltiplos de 4. La rúbrica 2 exige que los inicios de texto coincidan con los márgenes (±1 dp).

### 3.12 Segmentos en el panel: `AppSegmentedTabs` vertical

En un panel de 252 dp útiles, tres segmentos horizontales («Oraciones · Novenas · Noche») caben al 100 % pero no al 130 % en PT. Por eso, en `SidePanel`, `AppSegmentedTabs` se dibuja como **lista de secciones**:
- **Filas de 48 dp** (crecen con el texto, nunca truncan).
- **Selección:** la fila activa lleva el material «seleccionado» de la identidad:
  - Lumen y Cristal: lente de cristal;
  - Tradicional: fondo `action` y texto `onAction`;
  - Sanctuary: filete de acento de 2 dp al inicio y texto en acento.
- **Semántica y etiquetas:** `Role.Tab` y `selected`. Se conservan la etiqueta del contenedor (`prayers_segments`, `confession_tabs`…) y los textos.
- **Fuera del panel:** el control sigue horizontal, en vertical y en las bandas compactas.

### 3.13 Cambios de L0 por archivo y tests

| Archivo | Cambio |
|---|---|
| `ui/design/AppComponents.kt` | <ul><li>`AppWindowClass`, `appWindowClass()`, `appWindowHeight()`, `appIsLandscapePhone()`.</li><li>`appIsTwoPane()` según la clase.</li><li>`AppPageLayout`, `AppPageKind`.</li><li>`appGridColumns`, `AppFeedColumns`, `appReadingMeasure`, `rememberReadingAnchor`.</li><li>`AppTwoPane` con las medidas de §3.9.</li></ul> |
| `ui/design/LumenComponents.kt` | <ul><li>`LocalHeaderPlacement`.</li><li>`AppHeaderFrame` dibuja `SidePanel`, `CompactBand` y `DetailPane`.</li><li>La prueba de ancho del título usa `constraints`.</li><li>`AppSidePanelArtHeight`.</li><li>`AppGrid` con `appGridColumns`.</li></ul> |
| `ui/design/LumenKit.kt` | <ul><li>Riel compacto (`railReserve`, `railSlot` y etiquetas cortas por clase).</li><li>El riel respeta `AppHideTabBar` en `LandscapePhone`.</li><li>Insets laterales.</li><li>«Atrás» dentro del panel.</li><li>Sin barra de título al desplazar en pantallas con panel.</li><li>`SegmentedTabs` vertical en el panel.</li></ul> |
| `ui/design/LumenExtras.kt` | <ul><li>`AppSheet` lateral (con `wide` y `footer`).</li><li>`AppDialog` con `aside`, centrado y con alto máximo.</li><li>`AppConfirmDialog` desplazable.</li></ul> |
| `ui/design/LumenFoundation.kt` | <ul><li>`appSafeInsets()`.</li><li>`ScreenFrame.insets` con `start` y `end`.</li></ul> |
| `ui/design/LiquidGlass.kt` | `Modifier.fadeEnd` (variante de `fadeSides`) |
| `ui/MainApp.kt` | <ul><li>La columna centrada de 680 se mantiene en todas las clases con riel para las pantallas que no reclaman el ancho completo: en `LandscapePhone` solo la usan las pantallas todavía no migradas a `AppPageLayout` (que llama a `AppFullWidth()`), con `CompactBand` dentro. Así una pantalla no migrada nunca se estira a 811 dp.</li><li>Etiquetas cortas del riel con texto grande (con «Camino»).</li></ul> |
| `app/src/test/.../screenshots/FullAppScreenshotTest.kt` | <ul><li>Alto mínimo de 320 (hoy 640).</li><li>Calificador `land` cuando `W > H`.</li><li>`-Pshots.landscape=true` = 891×411 y 800×360.</li><li>`-Pshots.ime=<dp>`, `-Pshots.cutout=left` o `=right`, `-Pshots.navbar=left` o `=right` (insets simulados).</li></ul> |
| Tests nuevos | Ver la lista que sigue. |

Tests nuevos:
- **`LandscapeLayoutTest`**, a 891×411 y 800×360, en las 4 identidades:
  - la clase de ventana;
  - el riel compacto con 5 destinos que caben al 100 % y al 130 %;
  - el panel de portada a la anchura y la línea de título de la tabla;
  - la hoja lateral anclada al final con su anchura;
  - el diálogo centrado;
  - `CompactBand` ≤ 30 % de `H`;
  - nada bajo los insets simulados.
- **`LandscapeAuditTest`:** `NarrowLargeTextAuditTest` parametrizado por ventana (891×411, 800×360) y escala (1,0 y 1,3), en ES y PT. Añade estos detectores:
  - `HEADER_TOO_TALL`: arte de cabezal > 40 % del área de la ventana o `CompactBand` > 30 % de `H`;
  - `ESSENTIAL_BELOW_FOLD`: un nodo marcado como esencial por el área (lista por pantalla en §4–§10) fuera de la ventana sin desplazar;
  - `MEASURE`: caracteres por línea fuera del umbral de §3.10;
  - `UNDER_INSET`: texto o control dentro de un inset simulado;
  - `IME_HIDDEN`: campo con foco o acción principal bajo el teclado simulado;
  - `SHEET_GEOMETRY`: hoja que no es lateral en `LandscapePhone`.
- **`LandscapeRotationTest`** (Robolectric: `RuntimeEnvironment.setQualifiers("+land")` + `ActivityScenario.recreate()`). Gira de vertical a horizontal y de vuelta, y comprueba cada fila de §2.4: el paso del Rosario y la voz activa, el ancla de lectura, el segmento, la hoja abierta, el texto de búsqueda, la lista de Confesión, la selección de lista-detalle y el día de novena.
- **Insets simulados para Robolectric:** helper de test que despacha `WindowInsetsCompat` con `displayCutout`, `navigationBars` laterales e `ime` al `ComposeView`.

**Criterios de aceptación de L0:**
1. `appWindowClass()` es la única fuente. Ningún `screenWidthDp` ni `orientation` nuevo en `ui/screens`. `BibleScreen` y `RosaryScreen` migran en L1 y L2.
2. **Sin migrar ninguna pantalla:**
   - todas las raíces muestran `CompactBand` (≤ 30 % de `H`);
   - todas las hojas son laterales;
   - todos los diálogos están centrados con acciones visibles.
   - Lo comprueba una captura de todos los destinos a 891×411.
3. El riel compacto con 5 destinos cabe a 800×360 al 100 % y al 130 % en ES, EN y PT, en las 4 identidades.
4. Con un recorte simulado de 32 dp a la izquierda y a la derecha, y con la barra de tres botones (48 dp) a cada lado, ningún texto ni control queda debajo.
5. Los tests de interfaz que cita AGENTS.md pasan sin modificar sus etiquetas: `AppNavigationTest`, `DesignNavigationTest`, `RosaryInteractionTest`, `DesignContrastTest`, `VaticanNavigationLayoutTest`, `TabletLayoutTest`, fuente y `NarrowLargeTextAuditTest`.
6. El vertical y las tabletas no cambian, salvo lo declarado: lo comprueban las capturas de regresión a 411×891 y 1280×800.

---

## 4. L1 · Rosario y Coronilla

**Patrón:** panel de apoyo inmersivo. **Pantalla:** `RosaryScreen` (destino `ROSARY`), también para la Coronilla (`initialTab = 1`).

### 4.1 Boceto a 891 × 411 (sin riel)

```
 0                                      356                                                  891
┌────────────────────────────────────────┬─────────────────────────────────────────────────────┐ 0
│ (S)                                    │ (S)                                                 │
│ ‹      ╭───────────────────────────╮  │  Avemaría                                3 de 10    │ ← título y cuenta (fijo)
│  ┊     │ [ Rosario | Coronilla ]   │  │ ┌─────────────────────────────────────────────────┐ │
│  ●     │   PINTURA del misterio    │  │ │ (instrucción, si está activa)                   │ │ ← fijo
│  ┊     │   recortada al foco,      │  │ └─────────────────────────────────────────────────┘ │
│  ●     │   se disuelve en bruma    │  │  Dios te salve, María, llena eres de gracia;        │
│  ┊     ╰───────────────────────────╯  │  el Señor es contigo; bendita tú eres entre         │
│ (◉)◦  Misterios Gozosos ▾             │  todas las mujeres, y bendito es el fruto de tu     │ ← ORACIÓN COMPLETA
│  ┊    1.º La Anunciación              │  vientre, Jesús.                                    │   sin desplazar
│  ○    Fruto: la humildad  · Ver ›     │  Santa María, Madre de Dios, ruega por nosotros,    │   (ver §4.3)
│  ┊                                     │  pecadores, ahora y en la hora de nuestra muerte.   │
│  ○    ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬     │  Amén.                                              │
│  ┊    Paso 12 de 85 · 1.er misterio    │  Después · Gloria                                   │
│  ○                                     │ ─────────────────────────────────────────────────── │
│ hilo   contexto (desplaza a 130 %)     │ [Guía] [···]        [⏮ Anterior] [▶ Escuchar] [⏭ SIGUIENTE] │ ← transporte (fijo)
└────────────────────────────────────────┴─────────────────────────────────────────────────────┘ 411
  PANEL DE LAS CUENTAS · 0,40 W (356)       PANEL DE LA ORACIÓN · 535 · margen 20
  hilo vertical 56 + contexto 260
```

### 4.2 Qué va dónde

**Panel de las cuentas**, a la izquierda:
- **Ancho:** `clamp(0,40 W, 320, 380)`: 356 a 891 y 320 a 800.
- **Fijo**, salvo la columna de contexto, que desplaza solo si no cabe (130 %).
- **Hilo vertical**: el que pidió el dueño.
  - Ocupa una columna de 56 dp desde `x = max(safeStart, gestos laterales) + 8` y desde `y = S + 52` (bajo «atrás») hasta `H − B − 8`.
  - Es el `BeadReel` actual con `orientation = Vertical` (parámetro nuevo en `components/PrayerBeadCounter.kt`) y la misma lógica:
    - la cuenta en mano va en el centro vertical, mayor y con el anillo del acento litúrgico;
    - las rezadas, arriba y «contadas» (llenas); las que faltan, abajo y pálidas;
    - los puntos de subpaso van a la derecha de la cuenta;
    - se toca una cuenta para ir a ella o se arrastra en vertical para pasar de una en una, con el mismo háptico de tic y el más firme al cerrar una decena.
  - Lo usa el pulgar izquierdo.
  - Etiquetas y semántica intactas: `prayer_bead_counter` / `mercy_step_counter`, `contentDescription`, `stateDescription` y acciones personalizadas «Anterior» y «Siguiente».
- **Columna de contexto** (≈ 260):
  - **Arriba**, la pintura del misterio (`rosary_hero_photo`, `mystery_hero_photo`):
    - ocupa el ancho de la columna y `clamp(0,40 H, 140, 170)` de alto;
    - va recortada al foco y se disuelve en bruma, como el escenario vertical;
    - Sanctuary la pone en marco filetado.
  - Sobre la pintura, el selector `Rosario | Coronilla`: el mismo `DevotionSwitch`, en cristal sobre el arte.
  - **Debajo, sobre la bruma**, la etiqueta del escenario (`StageLabel`):
    - selector de misterios (`rosary_mystery_picker`);
    - título del misterio (`rosary_hero_title` / `mercy_hero_title`);
    - fruto;
    - «Ver el misterio».
  - **Al pie**, `SectionProgress` (`page_dots`, `rosary_hero_step_counter` / `mercy_hero_step_counter`). Al tocarlo se abre «Opciones».

**Panel de la oración**, a la derecha:
- **Ancho:** el resto (535 / 480). Margen final `max(20, safeEnd + 12)`.
- **Fijos:**
  - la fila del título de la oración (`current_prayer_title`, cuenta «3 de 10», `skip_optional_button`);
  - la instrucción (`prayer_instructions_text`), si está activa;
  - la línea «Después · …»;
  - el **transporte** (`prayer_transport`).
- **El texto de la oración** (`prayer_content_scroll`, `prayer_leader_text`…):
  - ocupa el espacio entre medias;
  - desplaza **solo si no cabe**, con un fundido inferior de 24 dp y el indicador de desplazamiento como pista (§4.3).
- **Transporte** (barra de 64 dp, sobre `B`):
  - al inicio, «Guía» (`prayer_instructions_toggle`) y «···» (`rosary_options_button`): poco frecuentes;
  - al final, el racimo `Anterior · Escuchar · Siguiente oración`, con **Siguiente** (`next_bead_button` / `next_mercy_step_button`) en el extremo derecho, ≥ 64 × 64, en la zona del pulgar derecho;
  - Anterior (`prev_bead_button`) y Escuchar (`toggle_prayer_audio_button` / `toggle_mercy_audio_button`) conservan sus etiquetas y su semántica.

### 4.3 Presupuesto de alto: por qué la oración cabe

Alto útil del panel de la oración: `H − S − B` = 371 (891×411) o 320 (800×360). El texto usa el estilo `reading`: serif 19/30 por defecto, columna de 495 / 440 ≈ 60 / 53 caracteres.

| Bloque | Alto |
|---|---|
| Título de la oración | 40 |
| Instrucción, 2 líneas de `subhead` (si está activa) | 64 |
| «Después · …» | 24 |
| Transporte | 64 |
| Respiros (4 × 8) | 32 |
| **Queda para el texto**, sin instrucción / con instrucción | 211 / 147 a 891 · 160 / 96 a 800 |

| Oración (ES) | Caracteres | Líneas a 891 | Líneas a 800 | ¿Cabe sin desplazar? |
|---|---|---|---|---|
| Avemaría | ≈ 245 (2 párrafos) | 5 (150 dp) | 6 (180) | 891: sí, con y sin instrucción · 800: solo sin instrucción |
| Padrenuestro | ≈ 330 | 6 (180) | 7 (210) | 891: sin instrucción · 800: desplaza |
| Gloria | ≈ 120 | 2–3 | 3 | siempre |
| «Oh Jesús mío» | ≈ 190 | 4 | 4 | 891 siempre · 800 sin instrucción |
| Señal de la cruz | ≈ 60 | 1–2 | 2 | siempre |
| Credo, Salve, letanías y oración final | 420–1500 | 8–50 | — | desplazan, con pista |

**Criterio:** a 891 × 411 al 100 %, **toda oración de decena** (Padrenuestro, Avemaría, Gloria y «Oh Jesús mío») se lee **sin desplazar** con la guía desactivada, y Avemaría y Gloria también con la guía activa. Hoy no cabe ninguna (E3). Las largas desplazan dentro de su panel, sin mover el transporte.

### 4.4 Hojas del Rosario

Son laterales (§3.6):
- **Misterios** (`rosary_mysteries_sheet`): normal, 446. Lista con miniaturas, como hoy.
- **Opciones** (`rosary_options_sheet`): **amplia**, 588, en dos columnas:
  - a la izquierda, «Voces» (`rosary_voice_mode_picker`) y su explicación;
  - a la derecha, `rosary_loop_map` y la lista de partes. En la Coronilla, la cita de Santa Faustina va al pie de la izquierda.
- **Ver el misterio** (`rosary_mystery_sheet`): amplia, en dos columnas:
  - a la izquierda, la pintura enmarcada (recortada a su marco) y el crédito;
  - a la derecha, la cita bíblica, la meditación en `reading` y el fruto.

### 4.5 Giro, recorte, texto grande e identidades

- **Giro.** Se conservan la sesión, el paso, la devoción y el misterio (ya existe). Además:
  - la **hoja abierta** (`rememberSaveable`);
  - la **voz** sigue rezando: no se envía `ScreenLeft` en un cambio de configuración (§2.4).
  - El desplazamiento del texto vuelve arriba al cambiar de paso, como hoy, y al girar conserva el ancla.
- **Recorte de cámara.**
  - A la izquierda: el hilo se aparta (`safeStart`) y la pintura sigue a sangre.
  - A la derecha: «Siguiente» se aparta (`safeEnd + 12`).
- **130 %.** La columna de contexto desplaza; el hilo, el título de la oración y el transporte quedan fijos. El transporte sigue en una fila: a 535 dp caben las tres teclas con rótulo. La variante de dos filas (`largeControls`) queda solo para anchos < 330.
- **Identidades:**
  - **Lumen:** bruma y luz litúrgica tras ambos paneles; transporte en cápsula de cristal.
  - **Tradicional:** transporte sobre `canvasBottom` opaco con filete (como hoy); pintura sin bruma lateral.
  - **Cristal:** el panel de la oración refracta el borde de la pintura; botones de cristal claro.
  - **Sanctuary:**
    - pintura en marco y filete de lomo entre los paneles;
    - cuentas en el acento granate y título de la oración en EB Garamond;
    - transporte con filete superior (como hoy).

### 4.6 Criterios de aceptación de L1

1. A 891×411 y 800×360, en las 4 identidades, en claro y oscuro:
   - el hilo está a la izquierda;
   - el texto de la oración, a la derecha;
   - el transporte, fijo abajo a la derecha;
   - el riel, oculto.
2. Se cumple la tabla de §4.3. `ESSENTIAL_BELOW_FOLD` = `current_prayer_title`, el texto completo de la oración de decena, `next_bead_button` y `prayer_bead_counter`.
3. `next_bead_button` tiene su centro a ≤ 120 dp del borde derecho seguro y por debajo de `0,6 H`, y mide ≥ 64 × 64.
4. Tocar y arrastrar el hilo vertical lleva al paso correcto. El háptico de fin de decena se mantiene (`RosaryInteractionTest` ampliado a horizontal).
5. Al girar a mitad de la tercera decena con la voz activa y la hoja «Opciones» abierta, siguen el mismo paso, la voz y la hoja (`LandscapeRotationTest`; `RosaryNarrationTest` y `RosaryPersistenceTest` en verde).
6. Las 3 hojas son laterales y no recortan contenido.
7. TalkBack recorre: atrás → selector de devoción → misterio → progreso → hilo (con sus acciones) → título de la oración → instrucción → texto → transporte.

**Archivos:**
- `ui/screens/RosaryScreen.kt`: la rama `wide` se sustituye por la disposición de §4.1, con `appWindowClass()`; también `StageLabel`, `SectionProgress`, `TransportRow` y las tres hojas.
- `ui/components/PrayerBeadCounter.kt`: `BeadReel` vertical.
- `ui/screens/RosaryNarration.kt`: cambio de configuración.
- `ui/screens/RosarySession.kt` / `RosarySessionStore.kt`: sin cambios previstos.

---

## 5. L2 · Lectores

**Patrón:** panel de apoyo («lector apaisado»), con `AppPageLayout(kind = Reader)`. Sin riel.

### 5.1 Plantilla común, a 891 × 411

```
 0                           267                                                                 891
┌──────────────────────────────┬──────────────────────────────────────────────────────────────────┐ 0
│ (S) ‹                    ☆ ⋯ │ (S)  · · · · · · · · fundido bajo la barra de estado · · · · · · │
│ ╔══════════════════════════╗ │                                                                  │
│ ║ ARTE (si lo hay)         ║ │        ┌──────────── columna de lectura 576 (≈ 70 car.) ──────┐  │
│ ║ bruma                    ║ │        │ Dios te salve, María, llena eres de gracia; el Señor │  │
│ ║ Antetítulo               ║ │        │ es contigo; bendita tú eres entre todas las mujeres, │  │
│ ║ Título (title1)          ║ │        │ y bendito es el fruto de tu vientre, Jesús.          │  │
│ ╚══════════════════════════╝ │        │                                                      │  │
│  meta                        │        │ Santa María, Madre de Dios, ruega por nosotros,      │  │
│  ÍNDICE  (sincronizado)      │        │ pecadores, ahora y en la hora de nuestra muerte.     │  │
│  ▸ Primera lectura           │        │ Amén.                                                │  │
│    Salmo                     │        │                                                      │  │
│    Evangelio                 │        │ ‹ Anterior                         Siguiente ›       │  │ ← al final del texto
│ ──────────────────────────── │        └──────────────────────────────────────────────────────┘  │
│ [▶ Escuchar] [Aa]   [‹] [›]  │                                                                  │ ← pie de herramientas (fijo)
└──────────────────────────────┴──────────────────────────────────────────────────────────────────┘ 411
  PANEL DEL LECTOR · 267 · fijo         PANEL DE TEXTO · 624 · desplaza; columna centrada a la medida
```

**Panel del lector** (`clamp(0,30 W, 224, 280)`), de arriba abajo:
1. «Atrás» y las acciones del lector (marcador, más).
2. Zona de arte (§3.2), si el lector tiene arte, o panel de título.
3. Antetítulo, título (`title1`) y meta.
4. **Índice**, solo si el texto tiene partes. Está sincronizado:
   - la parte que ocupa el tercio superior del panel de texto se marca `selected`;
   - al tocar una parte, el texto se desplaza hasta ella, con animación salvo con «reducir movimiento».
   - Es el componente nuevo `AppReaderOutline`, en `AppComponents.kt`.
5. **Pie de herramientas, fijo** (56 dp + `B`), en la zona del pulgar izquierdo:
   - `Escuchar`, con las etiquetas de cada lector (`bible_reader_listen`…);
   - `Aa`, que abre la hoja de lectura (`reading_settings_sheet`) lateral;
   - y, en los lectores secuenciales, `‹` y `›`, que repiten la navegación del final del texto.

**Panel de texto:**
- Desplaza.
- Columna de lectura a la medida (§3.10), centrada.
- Al final, la fila «Anterior / Siguiente» de hoy, con «Siguiente» alineado a la derecha.
- Los controles flotantes que hoy tapan el texto (E4, E8) pasan al pie del panel y **no se superponen** al texto.

### 5.2 Capítulo bíblico: texto más estudio

Se integra la vista de estudio que T-043 ya hacía en horizontal (texto + arcos), dentro del mismo sistema. Ya no se usa `LocalConfiguration.orientation`.

```
 W ≥ 860: dos paneles                                                                             891
┌──────────────────────────────────────────────────────────┬──────────────────────────────────────┐
│ (S) ‹   Biblia Platense · Español ▾                      │ (S)                                  │
│         San Juan                                         │ ┌──────────────────────────────────┐ │
│         Capítulo 1                                       │ │ ARCOS (CrossReferenceStudy)      │ │
│         El Evangelio teológico: el Verbo hecho carne…    │ │ San Juan 1 · toca un versículo   │ │
│                                                          │ └──────────────────────────────────┘ │
│  ¹ En el principio era el Verbo, y el Verbo estaba junto │  Cada arco une dos pasajes…           │
│  a Dios, y el Verbo era Dios. ² Él estaba en el          │  (referencias del versículo elegido)  │
│  principio junto a Dios. …                    (desplaza) │                            (desplaza) │
│                                                          │ ──────────────────────────────────── │
│                                                          │ [‹]  San Juan 1 ▾  [▶] [Aa]      [›] │ ← barra del lector (fija)
└──────────────────────────────────────────────────────────┴──────────────────────────────────────┘
  TEXTO · 552 · columna 504 (≈ 61 car.)                        ESTUDIO · clamp(0,38 W, 300, 360) = 339
```

- **`W ≥ 860` (891):**
  - texto a la izquierda;
  - panel de estudio a la derecha (`clamp(0,38 W, 300, 360)`), con los arcos y las referencias del versículo elegido;
  - la **barra del lector** (`‹ capítulo ▾ ▶ Aa ›`, con sus etiquetas `bible_reader_*`) fija al pie del panel de estudio, con `›` (capítulo siguiente) en el borde derecho.
- **`W < 860` (800):**
  - el texto ocupa la ventana a la medida (≈ 66 caracteres);
  - la barra del lector va fija al pie del texto, **sin superponerse**;
  - al tocar un versículo se abre el estudio como **hoja lateral amplia** (528) con `CrossReferenceStudy`. Hoy, en vertical, se abre `VerseSheet`; en horizontal, el estudio.
- **Cabecera:** antetítulo del libro, «Capítulo N» e introducción desplazan con el texto, como en vertical. El selector de edición (`bible_active_edition`) va arriba.
- **Hojas** `bible_chapter_sheet` y Texto (`bible_reader_font_*`, `bible_reader_spacing`): laterales.
- **Objetivos:** la fila de versículo (`verse_N`) pasa a medir ≥ 44 dp de alto (E9). Se hace con área táctil (`minimumInteractiveComponentSize`) sin cambiar el interlineado.

### 5.3 Ajustes de cada lector

| Lector | Arte en el panel | Índice | Extra en el panel | Pie | Archivo |
|---|---|---|---|---|---|
| Lecturas de la Misa | sí (arte de Misa) | <ul><li>Primera lectura · Salmo · Segunda lectura · Aclamación · Evangelio · Reflexión</li></ul> | <ul><li>fecha, celebración y punto del color litúrgico</li></ul> | <ul><li>Escuchar · Aa</li></ul> | `BibleScreen.kt` (segmento Misa) |
| Oración | sí | — (o estrofas, si las tiene) | <ul><li>categoría · origen</li></ul> | <ul><li>Escuchar · Aa</li></ul> | `PrayersAndNovenasScreen.kt` (detalle, `AppHideTabBar` en la línea 1477) |
| Día de novena | sí | <ul><li>partes del día</li></ul> | <ul><li>**rejilla 3 × 3 de días** (`novena_day_strip`, `novena_day_pill_N`, de 48 dp), en sustitución de la tira horizontal</li></ul> | <ul><li>Escuchar · Aa · ‹ día · día ›</li></ul> | `PrayersAndNovenasScreen.kt` (`novena_day_reader`) |
| Oraciones de la noche | sí | <ul><li>partes del oficio (examen, himno, salmodia, lectura, responsorio, cántico, oración, antífona mariana)</li></ul> | <ul><li>selector de oficio vertical (§3.12)</li></ul> | <ul><li>Escuchar · Aa</li></ul> | `NightPrayersScreen.kt` |
| Artículo del Catecismo | no (panel de título) | <ul><li>grupos de §</li></ul> | <ul><li>migas (Parte › Sección › Capítulo › Artículo)</li><li>§§ y «Escritura clave»</li></ul> | <ul><li>Índice (`catechism` TOC lateral) · Aa</li><li>los controles flotantes desaparecen</li></ul> | `CatechismScreen.kt` (línea 1742) |
| Lectores de doctrina (mandamiento, sacramento, credo…) | sí, si el apartado lo tiene | <ul><li>subapartados</li></ul> | <ul><li>fuente</li></ul> | <ul><li>Aa · ‹ ›</li></ul> | `DoctrineParts.kt`, `DoctrineMoral.kt`, `DoctrineFormat.kt`, `DoctrineHistory.kt` |
| Lección de estudio | sí | <ul><li>pasos de la lección</li></ul> | <ul><li>progreso del curso</li></ul> | <ul><li>Escuchar · Aa · **Continuar** (`study_continue_button`, a la derecha del pie)</li></ul> | `ScriptureStudyScreen.kt` |
| Santo (fuera de lista-detalle, p. ej. desde Hoy) | sí | — | <ul><li>fecha y rango</li><li>crédito (`saint_image_credit`, área ≥ 44)</li></ul> | <ul><li>Escuchar</li></ul> | `SaintsScreen.kt` |
| Devoción (fuera de lista-detalle) | sí | <ul><li>historia · oración · promesas</li></ul> | <ul><li>crédito (`devotion_image_credit`, área ≥ 44)</li></ul> | <ul><li>Escuchar · Aa</li></ul> | `DevotionsExplorerScreen.kt` |
| Día de plan | sí (arte del plan) | <ul><li>pasajes del día</li></ul> | <ul><li>«Día 12 de 365»</li></ul> | <ul><li>Aa · **Marcar leído** a la derecha</li></ul> | `ReadingPlansScreen.kt` |

### 5.4 Giro, teclado, recorte e identidades

- **Giro.**
  - Ancla de lectura (§3.10). En la Biblia, el versículo de estudio pasa del panel a la hoja según la anchura, con el mismo valor.
  - En vertical, al volver, el ancla apunta al mismo párrafo.
- **Teclado.** Solo lo usan el Compendio (`compendium_search_input`) y la búsqueda de preguntas:
  - el campo vive en el panel (arriba);
  - los resultados, en el panel de texto con `imePadding`.
- **Recorte de cámara.**
  - A la izquierda: lo absorbe el margen del panel.
  - A la derecha: margen final del texto `max(24, safeEnd + 12)`.
- **Identidades:**
  - **Sanctuary:**
    - el panel es el folio izquierdo, con el título arriba, el arte enmarcado y el índice en versalitas pequeñas, como el «sumario» de un misal (`usesAllCapsLabels` donde la identidad ya lo usa);
    - el texto conserva sus capitulares (`usesDropCaps`).
  - **Tradicional:** filete y superficies opacas.
  - **Lumen y Cristal:** bruma y cristal.
  - **Todas:** la columna de texto se lee sobre una superficie **opaca** (regla de AGENTS: «superficies de lectura opacas»).

### 5.5 Criterios de aceptación de L2

1. Medida (§3.10): 60–75 caracteres en lectores a 891 al 100 % con la fuente predeterminada; ≥ 56 a 800. En la Biblia con estudio, ≥ 60 a 891.
2. Ningún control flota sobre el texto: el detector añade un solapamiento de texto con controles como `CLIPPED` / `OBSCURED` (nuevo).
3. El primer párrafo del texto se ve sin desplazar. El arte del panel nunca supera el 40 % del área de la ventana.
4. El índice marca la parte visible y lleva a ella. TalkBack anuncia «seleccionado».
5. Al girar a mitad de un capítulo, se ve el mismo versículo (±1).
6. Las etiquetas `bible_reader*`, `novena_day_*`, `catechism_article_*`, `study_*` y `prayer_*` siguen existiendo. `CrossReferencesUiTest`, `BibleReadingJourneyTest`, `CatechismInteractionTest`, `PrayerDetailsUiTest` y `NovenaIndexJourneyTest`, en verde.

---

## 6. L3 · Hoy

**Patrón:** feed en carriles + panel de portada (`AppPageLayout(kind = Today)`). **Pantalla:** `HomeScreen.kt` (+ `ForYouToday.kt`).

### 6.1 Boceto a 891 × 411

```
 0      80                         323                                                         891
┌───────┬────────────────────────────┬───────────────────────────────────────────────────────────┐ 0
│ (S)   │ (S)                    ⌕ ◔ │ (S)                                                       │
│ ╭───╮ │ ╔════════════════════════╗ │  CARRIL A · 256                 CARRIL B · 256            │
│ │Hoy│ │ ║ Friedrich: la cruz en  ║ │  Oración del momento            Liturgia de hoy  Jn 1,1-18│
│ │Ora│ │ ║ la montaña (bruma)     ║ │ ┌──────────────────────────┐ ┌──────────────────────────┐ │
│ │Lee│ │ ║ Sábado, 21 de noviembre║ │ │ (☾) Oraciones de la noche│ │ «En el principio era el  │ │
│ │Cam│ │ ║ Hoy                    ║ │ │     Ahora · 20:00      › │ │  Verbo, y el Verbo…»     │ │
│ │Exp│ │ ╚════════════════════════╝ │ │ (✝) Ángelus              │ │ Primera lectura · Ap 11  │ │
│ ╰───╯ │ ┌────────────────────────┐ │ │     Mañana · 6:00      › │ │ Reflexión              › │ │
│       │ │● Tiempo Ordinario    › │ │ └──────────────────────────┘ │ [ Leer las lecturas  ▸ ] │ │
│       │ │ Presentación de la     │ │  Mis prácticas               └──────────────────────────┘ │
│       │ │ Santísima Virgen María │ │ ┌──────────────────────────┐  Para ti hoy                │
│       │ └────────────────────────┘ │ │ (0) de 12 hoy            │ ┌──────────────────────────┐ │
│       │                            │ │ [ Ver mis prácticas  ▸ ] │ │ …                        │ │
│       │                            │ └──────────────────────────┘ └──────────────────────────┘ │
│       │                            │  ─────────── Más para hoy (a todo el ancho) ─────────── › │
└───────┴────────────────────────────┴───────────────────────────────────────────────────────────┘ 411
  riel   PANEL 243 · fijo              CONTENIDO 568 · dos carriles · desplaza
```

### 6.2 Qué va dónde

Orden de vertical (`HomeScreen`): cabezal → tiempo litúrgico → oración del momento → mis prácticas → liturgia → «Para ti hoy» → «Más para hoy» → [versículo, invitación, continuar el Rosario, santo, pregunta, explorar].

**Panel** (243, fijo):
- cabezal con fecha, «Hoy» y las acciones `global_search_button` y `home_reminders_button`;
- en la ranura de apoyo, la tarjeta del **tiempo litúrgico** (`liturgical_calendar_widget`), que es la identidad del día.
- El panel no desplaza al 100 %.

**Carril A:**
- «Oración del momento» (`home_prayer_moment_heading`, ambas filas);
- «Mis prácticas» (`home_practices_heading`, `home_fidelity_card`, `home_habit_tracker_card`, `home_fidelity_count`, `home_open_temptations`).

**Carril B:**
- «Liturgia de hoy» (`daily_mass_card`, `home_first_reading_row`, `home_continue_row`, `home_continue_reading`);
- «Para ti hoy» (`home_for_you`).

**A todo el ancho, bajo los carriles:**
- «Más para hoy» (`today_more_toggle`);
- al expandirse, vuelve a dos carriles:
  - A: versículo, invitación (`today_invitation`) y continuar el Rosario (`today_continue_rosary` / `today_continue_prayer`);
  - B: santo, pregunta (`home_daily_question`) y explorar.
- `today_next_action` queda en el carril A, en su posición relativa de vertical.

El orden de TalkBack es carril A completo y luego carril B. Coincide con el de vertical, salvo que el tiempo litúrgico va con el cabezal, igual que hoy.

### 6.3 Lo esencial sin desplazar

| Medida | Debe verse entero |
|---|---|
| 891 × 411 al 100 % | <ul><li>fecha, «Hoy» y tiempo litúrgico;</li><li>las 2 filas de oración del momento;</li><li>el recuento de prácticas y su botón;</li><li>la cabecera de la liturgia con la referencia, el inicio del Evangelio (3 líneas de vista previa, como hoy) y la acción principal</li></ul> |
| 800 × 360 al 100 % | <ul><li>fecha, «Hoy» y tiempo litúrgico;</li><li>las 2 filas de oración del momento;</li><li>la cabecera de la liturgia con la referencia</li></ul> |
| 130 % (un carril) | fecha, «Hoy», tiempo litúrgico y la 1.ª fila de oración del momento |

Hoy, a 891 × 411, solo se ven el tiempo litúrgico y una fila (E2).

### 6.4 Carriles, texto grande, giro, tableta e identidades

- **Carriles:** 2 a 891 y a 800 al 100 %; 1 al 130 %. El panel se mantiene (§3.8).
- **Giro:** se conservan el desplazamiento (por ancla del bloque) y «Más para hoy» expandido (ya es `rememberSaveable`).
- **Tableta horizontal:** la misma disposición, con 3 carriles desde 1200 dp:
  - A: oración y prácticas;
  - B: liturgia;
  - C: «Para ti hoy» y extras.
  - Esto resuelve la limitación de T-043.
- **Identidades:**
  - **Lumen:** la tarjeta del tiempo litúrgico flota sobre la bruma del panel, igual que hoy flota sobre el cabezal (`overlap`).
  - **Tradicional:** tarjetas opacas y punto de color litúrgico como único acento.
  - **Cristal:** las tarjetas refractan la luz ambiental.
  - **Sanctuary:**
    - el panel es el folio con «Hoy» en EB Garamond y el grabado enmarcado;
    - los carriles son columnas de misal con filetes de sección.

### 6.5 Criterios de aceptación de L3

1. Se cumple la tabla de §6.3 (`ESSENTIAL_BELOW_FOLD` = las etiquetas citadas).
2. Los carriles tienen el mismo ancho (±1 dp) y alinean sus cabeceras de sección en la misma línea cuando empiezan juntos.
3. Ningún bloque de Hoy desaparece: el recuento de nodos con texto es igual en vertical y en horizontal (test de paridad).
4. `HomeTextTest`, `HomePrayerPrioritiesTest` y `AppNavigationTest` en verde. Etiquetas intactas.

---

## 7. L4 · Centros Orar y Leer

**Patrón:** panel de portada con selector de segmentos + rejilla (feed). **Pantallas:** `PrayersAndNovenasScreen.kt` (Oraciones, Novenas y Noche) y `BibleScreen.kt` (Biblia, Misa y Estudio; raíz `bible_screen`).

### 7.1 Orar a 891 × 411

```
 0      80                          372                                                        891
┌───────┬─────────────────────────────┬──────────────────────────────────────────────────────────┐ 0
│ (S)   │ (S)                       ⌕ │ (S)  ┌─ filtros (solo Novenas) ─ adhesivos ──────────┐   │
│ ╭───╮ │ ╔═════════════════════════╗ │      │ [A quién: Todas ▾] [Intención ▾] [Origen ▾]  │   │
│ │Hoy│ │ ║ lirios (bruma)          ║ │      └───────────────────────────────────────────────┘   │
│ │Ora│ │ ║ Ora sin cesar           ║ │  ┌──────────┐ ┌──────────┐ ┌──────────┐                  │
│ │Lee│ │ ║ Oraciones               ║ │  │ Rosario  │ │ Coronilla│ │ Ángelus  │  ← guías, 3 col. │
│ │Cam│ │ ╚═════════════════════════╝ │  │ (imagen) │ │ (imagen) │ │ (imagen) │                  │
│ │Exp│ │ ▌Oraciones                  │  └──────────┘ └──────────┘ └──────────┘                  │
│ ╰───╯ │  Novenas                    │  Categorías                                               │
│       │  Noche                      │  [Fundamentales] [Marianas] [Santos]   ← losas, 3 col.    │
│       │ ┌─────────────────────────┐ │  [Por los difuntos] [Eucaristía] [Todas]                  │
│       │ │ ⌕ Buscar oraciones      │ │  …                                                        │
│       │ └─────────────────────────┘ │                                                (desplaza) │
└───────┴─────────────────────────────┴──────────────────────────────────────────────────────────┘ 411
         PANEL 292 · segmentos verticales     CONTENIDO 519 · rejilla
         y búsqueda siempre visibles
```

- **Panel:**
  - cabezal (arte de los lirios, «Ora sin cesar», «Oraciones»);
  - **segmentos verticales** (`prayers_segments`, §3.12);
  - **búsqueda** (`AppSearchBar` del segmento activo).
  - Cabe a 891 × 411 al 100 %: 189 de arte + 144 de segmentos + 56 de búsqueda = 389 ≤ 395 (411 − `B`).
  - A 800 × 360 no cabe: 166 + 144 + 56 = 366 > 344. Regla: **los segmentos nunca salen del panel**; si la ranura no cabe, la búsqueda pasa a ser la primera fila adhesiva del contenido (junto a los filtros de Novenas). Así el panel no desplaza al 100 % en ningún tamaño.
- **Contenido, según el segmento:**
  - **Oraciones** (`prayers_landing`): guías (`pray_primary_guides`) en 3 columnas, categorías (`prayer_category_tile_*`) en 3 columnas y filas de oraciones (`prayer_row_*`) en 2 columnas desde 600 dp de interior. Aquí, en 1.
  - **Novenas** (`novena_index`):
    - filtros (`novena_filter_chips`) **adhesivos** arriba del contenido, siempre visibles;
    - «En curso» (`novenas_in_progress`) como fila de tarjetas;
    - todas las novenas (`novena_card_*`) en rejilla de 3 columnas;
    - «Completadas» (`novenas_completed`).
  - **Noche:** oficios en filas (2 columnas si el interior ≥ 600). El oficio abierto es un lector L2.
- **Ficha de novena** (`novena_detail_*`): página con panel de portada:
  - el panel lleva «Empezar / Continuar» (`novena_start_button`) y el progreso;
  - el contenido, la historia (`novena_history`), la promesa (`novena_promise`) y las notas.

### 7.2 Leer a 891 × 411

- **Panel:**
  - cabezal (anciana leyendo, «Sagrada Biblia»; acción de traducción);
  - segmentos verticales Biblia · Misa · Estudio;
  - selector de edición.
- **Contenido, según el segmento:**
  - **Biblia:**
    - «Continuar leyendo» (`bible_continue_reading`): tarjeta de imagen a todo el ancho del contenido, de 160 de alto (hoy son 236 de arte);
    - el directorio (`bible_book_directory`): AT y NT como cabeceras, con los libros en filas compactas en rejilla de 2 columnas (`minCell 220`; 3 en tableta).
  - **Misa:** el lector L2 de las lecturas va **dentro del contenido**, y su índice (lecturas) en la ranura del panel, bajo los segmentos.
  - **Estudio:** cursos en rejilla de tarjetas de imagen, de 3 columnas; la ficha del curso (`study_overview_page`) es una página con panel.

### 7.3 Giro, texto grande, tableta e identidades

- **Giro:** se conservan el segmento, los filtros, la búsqueda y el ancla del contenido.
- **130 %:** el panel desplaza, y los segmentos siguen visibles porque son lo primero tras el cabezal. Las rejillas bajan una columna (§3.8).
- **Tableta horizontal:** la misma disposición, con 4 columnas de tarjetas de imagen.
- **Identidades:**
  - **Sanctuary:** los segmentos son un «sumario» con filete de acento; las tarjetas de imagen, láminas enmarcadas.
  - **Tradicional:** segmento activo sólido `action`.
  - **Lumen y Cristal:** lente de cristal.

### 7.4 Criterios de aceptación de L4

1. Sin desplazar, a 891 × 411, se ven el panel completo (segmentos y búsqueda) y la primera fila completa de la rejilla, sin recortes.
2. A 800 × 360 se ven los segmentos (en el panel) y la búsqueda (adhesiva en el contenido). El panel solo desplaza al 130 %.
3. Rejillas según §3.8. Ninguna tarjeta mide menos de 128 de ancho al 100 %.
4. Los filtros de Novenas son visibles en cualquier punto del desplazamiento.
5. `NovenaIndexJourneyTest`, `NovenaOriginBadgeUiTest`, `PrayerDetailsUiTest`, `BibleReadingJourneyTest` y `ReadingPlansUiTest` en verde.

---

## 8. L5 · Listas con ficha

**Patrón:** lista-detalle (Material 3), con `AppTwoPane` según §3.9. Lo implementa Hermes (`ext/T-05x`), con este documento como encargo.

### 8.1 Boceto común, a 891 × 411 (Santos)

```
 0      80                         372                                                         891
┌───────┬────────────────────────────┬───────────────────────────────────────────────────────────┐ 0
│ (S) ‹ │ (S)                        │ (S)                                                       │
│ ╭───╮ │ ┌────────────────────────┐ │ ┌──────────┐  2 de enero · Memoria                        │
│ │Hoy│ │ │⌕ Buscar un santo       │ │ │ miniatura│  San Basilio Magno y San Gregorio            │ ← cabecera de ficha
│ │Ora│ │ └────────────────────────┘ │ │ 104×130  │  Nacianceno, obispos y doctores              │
│ │Lee│ │ [Todos los meses▾][Ficha]  │ └──────────┘  Imagen: Rijksmuseum · CC0  [▶ Escuchar]   │
│ │Cam│ │ Calendario Romano General  │ ───────────────────────────────────────────────────────── │
│ │Exp│ │ Santos                     │  La Iglesia celebra el 2 de enero a dos grandes Padres y  │
│ ╰───╯ │ ENERO                      │  doctores de la Iglesia: Basilio Magno y Gregorio         │
│       │▌San Basilio y San Gregorio │  Nacianceno…                                              │
│       │ Santísimo Nombre de Jesús  │                                                           │
│       │ San Raimundo de Peñafort   │                                                           │
│       │ San Hilario de Poitiers    │                                                (desplaza) │
└───────┴────────────────────────────┴───────────────────────────────────────────────────────────┘ 411
         LISTA 292: búsqueda y filtros     FICHA 519 · texto a la medida (≈ 58 car.)
         adhesivos; el título desplaza
```

**Ficha vacía** (antes de cargar, o sin resultados de filtro): el **arte de la sección** (Santos: el retablo) va recortado a un marco centrado de 280 × 180, con `AppEmptyState` («Elige un santo», o «Ningún santo coincide»).

### 8.2 Pantallas

| Pantalla | Lista (adhesivo) | Ficha | Notas | Archivo |
|---|---|---|---|---|
| **Santos** | búsqueda + mes + «solo con ficha» | santo (`saint_row_*` → ficha) | <ul><li>T-043 ya lo hace; aquí se adapta al alto compacto</li><li>sin `saint_detail_back` en dos paneles</li></ul> | `SaintsScreen.kt` |
| **Órdenes y oficios** | segmento Órdenes/Oficios + búsqueda | orden u oficio (`order_*`, `office_*`) | <ul><li>emblema como miniatura (o monograma)</li></ul> | `DoctrineAndHistoryScreen.kt`, `DoctrineOrders.kt`, `DoctrineParts.kt` |
| **Devociones** (María y Jesús) | filtros | devoción (`devotion_card_*`) | <ul><li>la miniatura es la imagen de T-044, o el glifo si no hay</li></ul> | `DevotionsExplorerScreen.kt` |
| **101 preguntas** | búsqueda + temas (cabeceras) | pregunta y respuesta (`qa_daily`, preguntas) | <ul><li>la «pregunta del día» abre por defecto</li><li>`catholic_qa_prev` / `next` como botones de ≥ 44 dp con el título **completo** en 2 líneas: hoy está elidido (E9)</li></ul> | `DoctrineQuestions.kt`, `DoctrineAndHistoryScreen.kt` |
| **Catecismo (índice)** | partes y secciones (`catechism_part_N`, `ccc_node_*`), en árbol | hijos del nodo elegido (capítulos y artículos) | <ul><li>el artículo (`catechism_article_*`) **abre el lector L2**: no cabe a la medida en una ficha de 519</li><li>«Compendio» (`catechism_open_compendium`) como fila fija al pie de la lista</li></ul> | `CatechismScreen.kt` |
| **Planes** | planes (`plan_card_*`) | detalle del plan (`plan_detail_screen`) con los días | <ul><li>el día abre el lector L2</li><li>«Empezar hoy» en la ficha, abajo a la derecha</li></ul> | `ReadingPlansScreen.kt` |
| **Búsqueda** | — (ver §8.6) | — | <ul><li>no es lista-detalle: resultados a lo ancho</li></ul> | `SearchScreen.kt` |

### 8.3 Lo esencial sin desplazar

A 891 × 411 deben verse:
- la búsqueda y los filtros;
- ≥ 4 filas de la lista;
- la cabecera de ficha completa y las primeras 3 líneas del texto.

A 800 × 360: la búsqueda, ≥ 3 filas y la cabecera de ficha.

### 8.4 Teclado

- La búsqueda está en el panel de lista. Con el teclado abierto, la lista lleva `imePadding` y muestra los resultados que caben (≥ 2 filas a 891×411 con 190 dp de teclado). La ficha no se mueve.
- La acción del IME es «Buscar»: cierra el teclado y deja el foco en el primer resultado.

### 8.5 Giro

Se conservan:
- la selección (explícita o por defecto, §3.9);
- el desplazamiento de la lista (por clave del elemento);
- la consulta;
- el ancla de la ficha.

### 8.6 Búsqueda (`SearchScreen.kt`)

- **Banda compacta** (`CompactBand`, sin panel): título «Buscar» + campo (`search_field`) a lo ancho.
- **Bajo la banda, adhesiva**, una fila de secciones como chips: Todo · Oraciones · Biblia · Santos · …. Si caben, se ven todas; si no, la fila se desplaza en horizontal con fundido de borde. Son las mismas secciones de `searchSections`.
- **Resultados por grupo** (`search_group_*`) en **rejilla de 2 columnas** de filas de resultado (§3.8). Cabecera de grupo a todo el ancho con su recuento.
- **Teclado:**
  - el campo está arriba, siempre visible;
  - los resultados llevan `imePadding`; con 190 dp de teclado se ven ≥ 2 filas de 2 resultados;
  - la acción del IME cierra el teclado.

### 8.7 Identidades y criterios de L5

**Identidades:**
- **Sanctuary:** lista filetada (`RULED_FOLIO`), lomo doble y miniatura en marco.
- **Tradicional:** fila activa sólida y filete.
- **Lumen y Cristal:** lente de cristal en la fila activa y separador de 0,5 dp.

**Criterios de aceptación:**
1. Se cumple §8.3 en las 7 pantallas.
2. Seleccionar una fila cambia la ficha sin apilar. «Atrás» sale de la pantalla (regla de T-043).
3. Al girar a vertical con selección por defecto, se ve la lista. Con selección explícita, se ve la página.
4. Los enlaces de crédito y los botones anterior/siguiente miden ≥ 44 dp (cero `SMALL_TARGET`) y no elíden el título de la pregunta.
5. `SaintsScreenTest`, `OrdersScreenTest`, `Devotion*`, `QuestionDeeperUiTest`, `CatechismInteractionTest`, `ReadingPlansUiTest`, `SearchUiTest` y `TabletLayoutTest` en verde.

---

## 9. L6 · Mi camino, Confesión, Recordatorios y Explorar

Lo implementa Hermes en `ext/T-05x`, después de T-046 («Mi camino» como 5.ª pestaña y fila única de hábitos).

### 9.1 Mi camino (`MyPathScreen.kt`) a 891 × 411

```
┌───────┬────────────────────────────┬───────────────────────────────────────────────────────────┐
│ riel  │ PANEL 243                  │  CARRIL A · Hoy en mi camino     CARRIL B · Secciones      │
│ ▌Cam  │ ╔════════════════════════╗ │ ┌──────────────────────────┐ ┌──────────────────────────┐ │
│       │ ║ bosque en la niebla    ║ │ │ Hábitos                  │ │ Diario                 › │ │
│       │ ║ Mi camino              ║ │ │ 0 hechos · 12 pendientes │ │ Tentaciones y victorias› │ │
│       │ ╚════════════════════════╝ │ │ · Empieza hoy…         › │ │ Prácticas              › │ │
│       │                            │ │ Plan: Biblia en un año › │ └──────────────────────────┘ │
│       │                            │ │ Novena: día 3 de 9     › │ ┌──────────────────────────┐ │
│       │                            │ └──────────────────────────┘ │ Confesión              › │ │
│       │                            │                              └──────────────────────────┘ │
└───────┴────────────────────────────┴───────────────────────────────────────────────────────────┘
```

- **Carril A:** «Hoy en mi camino» (`my_path_today_header`, `my_path_today`, `my_path_today_habits`, plan y novena).
- **Carril B:** «Secciones» (`my_path_sections`, diario, tentaciones y prácticas) y Confesión (`my_path_confession_entry`).
- Las etiquetas que T-046 conserve o convierta en alias se respetan tal como queden.

**Subsecciones:**
- **Hábitos** (`HabitsAndVirtuesScreen.kt`):
  - el panel lleva el cabezal, el anillo del día y el **calendario de fidelidad** del mes (`fidelity_day_*`, 7 columnas a 252 dp ≈ 36 dp por columna; mismo permiso que en vertical);
  - el contenido, la lista de hábitos con casillas, en 2 columnas.
- **Diario** (`SpiritualLifeScreen.kt`):
  - lista de entradas y entrada (lista-detalle, §3.9);
  - el editor, en §10.3.
- **Tentaciones y victorias** (`TemptationCalendar.kt`, `TemptationGuidePage.kt`):
  - calendario en el panel, registro en el contenido;
  - las hojas `victory_sheet`, `fall_sheet` y `root_sheet` son laterales.
- **Prácticas:** lista en 2 columnas.

### 9.2 Confesión (`confession/ConfessionScreen.kt`)

```
┌──────────────────────────────┬────────────────────────────────────┬──────────────────────────────┐
│ PANEL 243 (kind = Today)     │  EXAMEN · carril A                 │  MI LISTA · carril B         │
│ ╔══════════════════════════╗ │  [Elegir perfil]                   │ ● Una lista temporal: se     │
│ ║ Jesús y los apóstoles    ║ │  Soltero/a                         │   borra al salir… (aviso)    │
│ ║ Un encuentro con la      ║ │  ▸ Amor a Dios               (2)   │  Para conversar con el sac.  │
│ ║ misericordia · Confesión ║ │    ☐ ¿He faltado a Misa…?          │  ☑ ¿He faltado a Misa…?      │
│ ╚══════════════════════════╝ │    ☑ …                             │  ┌─────────────────────────┐ │
│ ▌Prepararme                  │  ▸ Amor al prójimo                 │  │ Notas personales        │ │
│  Mi lista                    │  ▸ Deberes de estado               │  │ (opcionales)            │ │
│  El sacramento               │                       (desplaza)   │  └─────────────────────────┘ │
│                              │                                    │  [ Terminar y borrar ]     ▸ │
└──────────────────────────────┴────────────────────────────────────┴──────────────────────────────┘
```

- **Panel:**
  - cabezal;
  - segmentos verticales (`confession_tabs`: Prepararme · Mi lista · El sacramento).
  - El **aviso de privacidad** (`confession_privacy_notice`, ≈ 5 líneas a 252 dp) no cabe en el panel junto a los segmentos. Va **al principio del carril B** en los tabs 0 y 1 (donde aplica: a la lista) y al principio del contenido en el tab 2.
- **«Prepararme» (tab 0): examen y lista en paralelo** (petición del plan).
  - Dos carriles iguales (`AppFeedColumns(2)`, como Hoy): 256 + 256 a 891 y 220 + 220 a 800; el panel usa el ancho de Hoy (`kind = Today`). Al 130 %, un carril: el examen y, debajo, la lista.
  - Carril A: examen (`confession_examen`, `confession_profile_button`, `confession_profile_title`, `confession_section_*`).
  - Carril B: mi lista en vivo:
    - `confession_private_list` y `confession_empty_list` («Marca las preguntas…»);
    - `confession_review_list`;
    - notas (`confession_notes`);
    - «Terminar y borrar» (`confession_finish_erase`), fijo al pie del carril B y a la derecha.
  - Marcar una pregunta en A la añade al instante en B.
- **«Mi lista» (tab 1):** la lista a todo el ancho del contenido, con el campo de notas grande. Es la opción cómoda para escribir.
- **«El sacramento» (tab 2):** lector L2 dentro del contenido (`confession_guide`, `confession_prayer_*`, `confession_prayer_source_*`).
- **Teclado** (notas):
  - solo el carril B se encoge (`imePadding`) y lleva el campo a la vista;
  - «Terminar y borrar» queda sobre el teclado;
  - el examen no se mueve.
- **Privacidad y giro:** la lista y las notas **sobreviven al giro** (holder de ámbito de actividad, §2.4) y **siguen sin guardarse** en el Bundle, el disco ni las copias. Se borran al salir, al pasar a inactiva o al morir el proceso. El texto del aviso no cambia.
- **Diálogo inicial** (intro): `AppDialog` con `aside`:
  - a la izquierda, el título y la explicación;
  - a la derecha, el aviso y «Entendido».
- **Hoja «Elegir perfil»:** lateral (`confession_profile_*`).

### 9.3 Recordatorios (`RemindersScreen.kt`, `NoticeSettings.kt`)

- **Panel:**
  - cabezal («Recordatorios», «2 activos · Próximo: el Ángelus, 12:00»);
  - **avisos de estado** (notificaciones o alarmas exactas desactivadas, con «Activar» / «Permitir»): es lo primero que hay que resolver y queda a la vista.
- **Contenido:** recordatorios (`notice_row_*`) en filas de 2 columnas (`minCell 300`; 1 a 800 al 100 %).
  - Cada fila tiene interruptor y hora.
  - **Las horas (`reminder_time_*`) miden ≥ 44 dp de alto** (hoy 34: E9). El chip conserva su aspecto y crece su área táctil.
- **Hojas** `reminder_sheet`, `notice_sheet` y `obligation_sheet`: laterales. Su estado abierto pasa a `rememberSaveable`.
- **Selector de hora** del sistema: el diálogo nativo ya es horizontal; se comprueba que no se corte (§13).

### 9.4 Explorar (`MoreScreen.kt`)

- **Panel:**
  - cabezal (`more_hero_photo`);
  - fila «Buscar»;
  - tarjeta «Empieza aquí» (`explore_start_here`).
- **Contenido:**
  - cada sección (`more_section_N`) con su cabecera a todo el ancho y sus filas en **rejilla de filas compactas**: 2 columnas a 891 y 1 a 800 (§3.8);
  - las tarjetas de sección con imagen (`more_section_hero_0`) en 2 columnas;
  - la versión (`more_version`) al pie.
- «Mi camino» ya no aparece en Explorar (T-046).

### 9.5 Criterios de aceptación de L6

1. **Mi camino:**
   - los dos carriles se ven completos sin desplazar a 891 × 411;
   - a 800 × 360 se ven «Hábitos» y las 3 primeras secciones.
2. **Confesión:**
   - con el teclado simulado (190 dp) y el foco en las notas, se ven el campo y «Terminar y borrar» (`IME_HIDDEN` = 0);
   - al girar, la lista y las notas siguen ahí;
   - al salir y volver, están vacías (`ConfessionInteractionTest` ampliado).
3. **Recordatorios:** 0 `SMALL_TARGET`; los avisos de estado se ven sin desplazar.
4. **Explorar:** todas las filas de vertical existen; el número de nodos con texto es el mismo.
5. `MyPathUiTest`, `ConfessionInteractionTest`, `RemindersTest`, `NoticeUiTest`, `JournalTemptationInteractionTest` y `BackupUiTest` en verde.

---

## 10. L7 · Hojas, diálogos, bienvenida y ajustes

Con L0, todas las hojas son laterales. L7 ajusta las que tienen dos columnas naturales, los diálogos y las que tienen teclado.

### 10.1 Inventario

| Hoja o diálogo | Archivo | Forma en horizontal | Detalle |
|---|---|---|---|
| Apariencia (`gui_customizer_sheet`) | `components/GuiCustomizerModal.kt` | **lateral amplia (588), 2 columnas** | <ul><li>Izquierda: Estilo (las 4 identidades), Modo, Reducir transparencia y Alto contraste.</li><li>Derecha: Color de acento (paleta y rueda) y una **vista previa en vivo**: miniatura de una fila y un botón con el acento.</li><li>Los segmentos dejan de estirarse a 860 dp (E6).</li></ul> |
| Fuente (`font_picker`) | `components/FontPickerDialog.kt` | lateral amplia, 2 columnas | <ul><li>Izquierda, **fija**: muestra con Juan 1,1, tamaño y espaciado.</li><li>Derecha: lista de tipografías.</li><li>«Listo» en la cabecera, como hoy.</li><li>La fuente elegida se aplica en vivo a la muestra y a la app (AGENTS: visible, persistente, efectiva).</li></ul> |
| Texto del lector (`reading_settings_sheet`) | `design/LumenExtras.kt` | lateral normal | <ul><li>tamaños en fila de 4</li><li>tipografías en lista</li></ul> |
| Tu camino (`welcome_profile_sheet`) | `screens/BeginnerJourneyScreen.kt` | lateral normal | <ul><li>perfiles y minutos</li><li>«Guardar» en `footer`</li></ul> |
| Copia de seguridad (`backup_*`) | `backup/BackupSheet.kt` | lateral normal con `footer` | <ul><li>Campos con `imePadding` (ya existe) y `rememberSaveable`.</li><li>La acción principal (Crear o Restaurar) va en el `footer`, sobre el teclado.</li><li>`AppConfirmDialog` «¿Reemplazar tus datos?»: una columna.</li></ul> |
| Informe de errores (`error_report_sheet`) | `diagnostics/ErrorReportSheet.kt` | lateral normal con `footer` | <ul><li>texto con `rememberSaveable`</li><li>«Enviar» en el `footer`</li></ul> |
| Idioma (`language_sheet`) | `components/LanguageSelectorDialog.kt` | lateral normal | 3 filas |
| Reglas del calendario (`calendar_rules_sheet`) | `components/CalendarRulesSheet.kt` | lateral normal | — |
| Calendario (`calendar_picker_sheet`) | `components/CalendarPickerSheet.kt` | **lateral amplia, 2 columnas** | <ul><li>izquierda: mes en rejilla 7 × 6 (celdas ≥ 44)</li><li>derecha: el día elegido (tiempo, celebración, color) y «Ir a este día»</li></ul> |
| Rueda de color | `components/AccentColorWheelDialog.kt` | `AppDialog` con `aside` | <ul><li>izquierda: la rueda (≤ `H − 120`)</li><li>derecha: muestras, valor y acciones</li></ul> |
| Créditos de imagen (`image_credits_sheet`) | `components/ImageCredits.kt` | lateral normal | — |
| Recuperar preferencias o narración | `PreferencesRecoverySheet.kt`, `NarrationRecoverySheet.kt` | lateral normal | <ul><li>acción en `footer`</li></ul> |
| «Para ti hoy» (`for_you_sheet`) | `screens/ForYouToday.kt` | lateral normal | — |
| Hojas de Biblia, lección, plan y Catecismo | ver L2 y L5 | lateral | <ul><li>`lesson_xref_sheet` y `plan_xref_sheet`: amplias, con arcos</li></ul> |
| Navegador interno | `components/InAppBrowser.kt` | pantalla completa (sin cambio) | <ul><li>la barra respeta los insets laterales</li></ul> |
| Confirmaciones (`AppConfirmDialog`) | varios | una columna, 420, centrado | — |

### 10.2 Bienvenida (`BeginnerJourneyScreen.kt`, `welcome_dialog`)

```
┌─────────────────────────────────────── AppDialog 720 × ≤ 339, centrado ────────────────────────┐
│ Un lugar para comenzar                          │  Estoy explorando                          ✓  │
│ Ven como eres. Puedes cambiar de camino         │  Estoy regresando                             │
│ en cualquier momento.                           │  Estoy practicando                            │
│                                                 │  Tiempo para hoy                              │
│ (ilustración pequeña, recortada a su marco,     │  [ 2 min ]  [ 5 min ]  [ 10 min ]             │
│  solo si cabe: se omite antes que recortar      │ ───────────────────────────────────────────── │
│  texto)                                         │                     [Ahora no]  [ EMPEZAR ▸ ] │
└─────────────────────────────────────────────────┴───────────────────────────────────────────────┘
```

- Las etiquetas `welcome_dialog`, `welcome_profile_N` y `welcome_personalize` se mantienen.
- La acción principal se ve **sin desplazar**. Hoy no se ve (E7).

### 10.3 Editor de diario (`SpiritualLifeScreen.kt`, `Dialog` crudo)

En `LandscapePhone` pasa a **editor a pantalla completa** (sin velo ni hoja del 94 %):
- **Barra superior de 56 dp:** «Cancelar» al inicio; título de la entrada en línea; «Guardar» al final, a la derecha, siempre visible.
- **Cuerpo:** de borde a borde, a la medida, con `imePadding`.
- **Con teclado** (≈ 190 dp) quedan ≈ 165 dp: la barra (56) y ≥ 3 líneas del cuerpo con el cursor a la vista.

El borrador ya se guarda con `rememberSaveable` (`draftTitle`); el cuerpo también debe guardarse.

### 10.4 Criterios de aceptación de L7

1. En `LandscapePhone`, cero hojas inferiores (`SHEET_GEOMETRY`).
2. Ninguna hoja ni diálogo tiene contenido o botones fuera de la pantalla, en ninguna de las 4 identidades, al 100 % y al 130 %.
3. Cada hoja con campo de texto cumple `IME_HIDDEN` = 0 con el teclado simulado.
4. La bienvenida y la intro de Confesión muestran su acción principal sin desplazar a 891 y a 800.
5. La fuente elegida en la hoja se ve aplicada en la muestra y en la pantalla de debajo al cerrar (`FontPickerInteractionTest` y `FontPreferencesTest` ampliados).
6. Al girar, cada hoja abierta sigue abierta y con sus valores.

---

## 11. Rúbrica del crítico

Es la rúbrica del plan, con umbrales comprobables:
- Cada criterio se puntúa de 0 a 10. **Se aprueba con ≥ 9 en los 9 criterios.**
- **Un 9 exige que se cumplan todas las comprobaciones duras** del criterio y que no quede más de un defecto menor documentado.
- El crítico compara **cada captura horizontal con su vertical** (mismo destino, identidad y modo).

| # | Criterio | Comprobaciones duras (todas, para ≥ 9) | Defectos que bajan de 9 |
|---|---|---|---|
| 1 | **Uso del espacio** | <ul><li>Arte de cabezal ≤ 40 % del área de la ventana, `CompactBand` ≤ 30 % de `H` y panel lateral ≤ 0,40 W.</li><li>Lo esencial de cada área (§4.3, §6.3, §7.4, §8.3, §9.5) se ve sin desplazar.</li><li>Ninguna franja vacía (solo fondo) > 15 % del área de la ventana, salvo los márgenes de la medida de lectura.</li><li>Ninguna columna de contenido < 220 dp (salvo los 56 del hilo).</li></ul> | <ul><li>vertical estirado;</li><li>espacios muertos;</li><li>arte decorativo que obliga a desplazar</li></ul> |
| 2 | **Jerarquía y ritmo** | <ul><li>Un título por pantalla, con el rango de AGENTS.</li><li>Todos los títulos de panel en la línea `0,46 H − 14` (±1 dp).</li><li>Espaciado en múltiplos de 4.</li><li>Inicios de texto alineados a los márgenes (±1 dp).</li><li>Carriles de igual ancho (±1 dp).</li><li>Acciones primarias únicas por zona.</li></ul> | <ul><li>dos focos que compiten;</li><li>bloques desalineados;</li><li>separaciones irregulares</li></ul> |
| 3 | **Legibilidad** | <ul><li>Medida (§3.10): 60–75 caracteres en lectores a 891 al 100 %; ≥ 56 a 800 y con sans; ≥ 45 en fichas y al 130 %.</li><li>Contraste ≥ 4,5:1 en texto y ≥ 3:1 en texto grande y controles: `DesignContrastTest` ampliado a texto sobre arte del panel, con el píxel más claro bajo el texto.</li><li>Ningún texto por debajo del tamaño del usuario.</li><li>La lectura, sobre superficie opaca.</li></ul> | <ul><li>líneas de 35 o de 90 caracteres;</li><li>texto sobre arte sin velo;</li><li>tamaños reducidos</li></ul> |
| 4 | **Ergonomía** | <ul><li>Objetivos ≥ 44 dp (cero `SMALL_TARGET` fuera de la lista de permisos actual) y primarias ≥ 48.</li><li>La acción que avanza (§2.3), a ≤ 200 dp del borde derecho seguro y por debajo de `0,4 H`.</li><li>La navegación en el tercio izquierdo.</li><li>Ningún control arrastrable en las zonas de gesto lateral sin exclusión.</li></ul> | <ul><li>acción frecuente arriba en el centro;</li><li>controles pequeños;</li><li>alcance cruzado</li></ul> |
| 5 | **Coherencia** | <ul><li>Mismos textos, iconos, acciones y etiquetas de test que en vertical: test de paridad de nodos por pantalla.</li><li>Las 4 identidades × claro/oscuro capturadas y fieles a §2.5.</li><li>Sin componentes paralelos fuera de `ui/design`.</li></ul> | <ul><li>un control que solo existe en horizontal sin justificación;</li><li>estética ajena a la identidad</li></ul> |
| 6 | **Estados** | Vacío, cargando (`ContentLoadNotice`), error, 130 %, títulos PT largos y ausencia de arte: cada uno diseñado dentro de la disposición horizontal y capturado (§12). | <ul><li>estados que vuelven a la disposición vertical;</li><li>huecos sin mensaje</li></ul> |
| 7 | **Insets y teclado** | <ul><li>Recorte de 32 dp a izquierda y derecha, barra de tres botones de 48 a cada lado y gestos de 16 abajo: cero `UNDER_INSET`.</li><li>Teclado simulado de 190 (411) o 170 (360): cero `IME_HIDDEN`.</li></ul> | <ul><li>texto bajo la cámara;</li><li>acción tapada por el teclado</li></ul> |
| 8 | **Continuidad al girar** | `LandscapeRotationTest` verde en todas las filas de §2.4: vertical → horizontal → vertical, mismo estado. | <ul><li>cualquier pérdida de estado o de posición</li></ul> |
| 9 | **Accesibilidad** | <ul><li>Orden de TalkBack = orden de vertical (por pantalla).</li><li>Títulos con `heading`.</li><li>Paneles y hojas con `paneTitle`.</li><li>`selected` en riel, segmentos, índice y filas activas.</li><li>Acciones personalizadas del hilo de cuentas.</li><li>Foco que entra y sale de las hojas.</li><li>Ningún control sin nombre.</li></ul> | <ul><li>trampas de foco;</li><li>orden de lectura distinto del visual sin motivo</li></ul> |

Si en una ronda falla una comprobación dura, el criterio no pasa de 8, por bueno que sea el resto.

---

## 12. Matriz de capturas

Comando base, una vez hecho L0:

```sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache \
  :app:testDebugUnitTest --tests 'com.example.screenshots.FullAppScreenshotTest' \
  -Pshots.landscape=true -Pshots.lang=es -Pshots.identity=all
```

Y `LandscapeAuditTest` con `-Paudit.capture=all` para las subpantallas.

| Eje | Valores | Obligatorio por área |
|---|---|---|
| Tamaño | **L-A 891 × 411** (teléfono grande) · **L-B 800 × 360** (teléfono pequeño) | ambos |
| Identidad | Lumen · Tradicional · Cristal · Sanctuary | las 4 |
| Modo | claro · oscuro | ambos |
| Material | «Reducir transparencia» activado | Lumen y Cristal, 1 captura por pantalla del área, en L-A claro |
| Texto | 100 % · **130 %** | 130 % en L-B, en ES y PT, en las 4 identidades, modo claro |
| Idioma | ES (base) · PT (las cadenas más largas) | PT en L-A Lumen claro y en las de 130 % |
| Teclado (`-Pshots.ime`) | 190 dp en L-A · 170 en L-B | <ul><li>Búsqueda;</li><li>Confesión (notas);</li><li>Copia de seguridad;</li><li>Informe de errores;</li><li>editor de diario;</li><li>búsqueda de Santos, Preguntas y Compendio.</li><li>Lumen y Sanctuary, claro, ES.</li></ul> |
| Insets (`-Pshots.cutout`, `-Pshots.navbar`) | recorte a la izquierda · recorte a la derecha · tres botones a la derecha | L0 en todas las raíces; cada área en su pantalla principal, en Lumen claro |
| Estados | vacío · cargando · error · sin arte | una captura de cada uno por área donde exista, en Lumen claro, L-A |
| Regresión | vertical 411 × 891 · tableta 1280 × 800 | <ul><li>L0, L3 y L4: todas las raíces;</li><li>el resto de áreas: su pantalla principal, en Lumen y Sanctuary claro</li></ul> |

- **Mínimo por pantalla:** 2 tamaños × 4 identidades × 2 modos = **16**, más 8 de 130 % (ES y PT × 4 identidades en L-B), más las de teclado, insets y estados que le toquen.
- **Nombre:** `{área}_{pantalla}_{identidad}_{claro|oscuro}_{w}x{h}[_s130][_pt][_ime][_cutL|_cutR|_navR][_vacío|_error]`.
- **Destino:** `docs/tasks/T-0xx-shots/`, con una lámina de contacto por área (vertical | horizontal lado a lado), para que el crítico compare.

---

## 13. Lista de comprobación en el teléfono real

Robolectric no es un teléfono: no simula el recorte real, el IME, la rotación física ni el rendimiento del cristal. Con cada versión de tramo (L1+L2, L3+L4, L5–L7), el dueño comprueba en su teléfono:

1. **Girar en los dos sentidos** (cámara a la izquierda y a la derecha), en mitad de:
   - un Rosario con la voz activa;
   - un capítulo largo;
   - la lista de Confesión con notas escritas;
   - la búsqueda con texto.
   
   Todo sigue igual.
2. **Recorte de cámara.** Nada importante bajo la cámara en ningún giro.
3. **Navegación de tres botones** (si la usa). El riel y «Siguiente» no quedan debajo.
4. **Teclado.** Escribir en Búsqueda, en notas de Confesión y en el diario: se ven el campo y su botón, y el teclado **no** ocupa toda la pantalla.
5. **Rosario completo en horizontal.** Las oraciones de la decena se leen sin desplazar; las cuentas se tocan y arrastran con el pulgar izquierdo; «Siguiente» se pulsa con el derecho.
6. **Lectura larga** (un capítulo de los Hechos): líneas cómodas y nada flotando sobre el texto.
7. **Letra grande** (130 % en Ajustes): nada cortado y el riel con sus 5 pestañas.
8. **Fluidez.** Desplazar Hoy y Orar no da tirones en Lumen ni en Cristal (el cristal vivo cuesta más con dos paneles).

Cada fallo abre una ronda de corrección del área antes de seguir.

---

## 14. Riesgos y decisiones abiertas

**Decisiones que el dueño puede vetar.** Están resumidas también en `LANDSCAPE-RESUMEN.md`.
1. El riel se oculta en las pantallas inmersivas: Rosario y lectores (§3.5).
2. En la Biblia, el estudio con arcos es un panel fijo solo en teléfonos anchos (≥ 860 dp). En los más estrechos se abre al tocar un versículo (§5.2).
3. Credo, Salve y las oraciones largas pueden necesitar desplazar en el Rosario (§4.3).
4. Etiquetas cortas del riel con letra grande: «Camino», «Más» (§3.5).
5. Todas las hojas inferiores pasan a laterales en horizontal (§3.6).
6. Confesión conserva la lista al girar, sin guardarla nunca (§9.2).
7. A 130 %, Hoy pasa a una columna (§6.4).

**Riesgos técnicos:**
- **Estabilidad del árbol al girar** (§2.4). Es el defecto más probable: el estado que se pierde «a veces». Lo vigila `LandscapeRotationTest`, que debe crearse en L0, antes que las áreas.
- **Cristal vivo con dos paneles.** El fondo líquido muestrea el lienzo completo; con un panel de arte fijo y contenido que desplaza puede costar más. Se mide en el teléfono (§13.8). Si hiciera falta, el panel usa la bruma estática y no el muestreo vivo.
- **`screenHeightDp`** puede excluir barras en algunas versiones (§2.1). L0 deja un test que fija el cálculo de la clase.
- **Carga de migración:** 34 cabezales en 19 archivos. El respaldo `CompactBand` (§3.3) da una mejora inmediata y evita un estado intermedio peor que el actual.
- **Hermes (L5–L6)** debe recibir este documento, la lista de archivos permitidos de su área y la prohibición de tocar `ui/design` (que es de L0).

**Fuera de alcance:**
- tableta en postura de mesa (plegable a medio abrir);
- ventanas flotantes (freeform);
- teclados físicos (aunque el orden de foco de §11.9 los favorece).
