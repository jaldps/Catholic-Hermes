# Plan de acción · desde la 1.8.28 (20-nov-2026)

Documento vivo del coordinador. Recoge lo pendiente y el orden de trabajo pedido por el dueño, para que sobreviva a reinicios y cortes de cuota.

## 0. Cerrar la ronda actual

1. **T-045 · Auditoría a 320 dp y 130 %.** Un agente local la tiene en curso.
   - Al terminar, revisar el detector y las capturas de antes y después.
   - Commit y publicación de la **1.8.28**: Release automática más `dist` si la APK pesa ≤ 100 MiB.
2. **Guardar este plan.** Hecho: es este documento.

## 1. T-046 · «Mi camino» en la barra principal y sin entradas repetidas

Petición del dueño, con captura del 20-nov. Hoy, en «Mi camino», **tres filas llevan al mismo sitio**: «Hábitos de hoy», «Fidelidad» y la sección «Hábitos».

**Fusionar las tres filas en una sola entrada «Hábitos»** dentro de «Hoy en mi camino».
- El subtítulo combina los dos datos: «0 hechos · 12 pendientes · Empieza hoy, a tu ritmo» o «… · 5 días seguidos».
- La sección «Hábitos» desaparece de «Secciones», que quedan así: Diario · Tentaciones y victorias · Prácticas, más Confesión.
- Plan y novena en curso siguen en «Hoy en mi camino».
- Tags:
  - `my_path_today_habits` se conserva;
  - `my_path_today_fidelity` y `my_path_section_habits` pasan a ser alias, o se eliminan ajustando sus tests.

**«Mi camino» como quinta pestaña** de la barra inferior y del rail: Hoy · Orar · Leer · **Mi camino** · Explorar.
- Icono propio, el del camino o el escudo que ya usa `AppIconSlot`.
- Tag `nav_my_path`.
- Comportamiento de pestaña: volver a tocarla devuelve al inicio del hub.
- Sale de la lista de Explorar para no duplicar.
- Los destinos antiguos (`HABITS_VIRTUES`, `SPIRITUAL_LIFE`) siguen llevando a su sección.
- Comprobar las 5 pestañas a 320 dp y 130 % (etiquetas cortas si hace falta, como hoy «Más») y en las 4 identidades.

**Tests:** `MyPathUiTest`, `AppNavigationTest`, `DesignNavigationTest`, `TabletLayoutTest` y `NarrowLargeTextAuditTest`, más capturas.

**Publicar 1.8.29.**

## 2. T-047…T-05x · Interfaz horizontal (landscape) de nivel profesional

**Objetivo del dueño:** en horizontal, la app no debe ser la vertical estirada. Hay que aprovechar el espacio, que es distinto, con una experiencia práctica, profesional y en armonía con el diseño vertical, en **todas** las pantallas, hojas y diálogos. La rama `ui/landscape-redesign` de Hermes se descarta como base.

### Principio de diseño

- Un teléfono en horizontal mide unos **800–930 × 360–412 dp**: el problema es la **altura escasa**, no el ancho.
  - El cabezal de 236 dp de arte se come más de la mitad de la pantalla.
  - Las hojas inferiores tapan casi todo.
  - Las listas largas obligan a desplazar todo el rato.
- Las clases de ventana se detectan una sola vez en `ui/design`:
  - **compacta en altura** (< 480 dp de alto con ancho ≥ 600): teléfono en horizontal;
  - **mediana** y **expandida**: tabletas, que ya cubre T-043.
- Cada identidad conserva su carácter: Lumen, Tradicional, Cristal, Sanctuary.

### Fundamentos (L0): primero, en el sistema de diseño

1. **`AppWindowClass` en `AppComponents.kt`**, con `isLandscapePhone` y similares. Ninguna pantalla mide por su cuenta.
2. **Cabezal horizontal.** En lugar de 236 dp de arte arriba, un **panel lateral fijo** (≈ 34–38 % del ancho) con el arte recortado, el título y el antetítulo. A la derecha va el contenido, con su propio desplazamiento.
   - Mismo `AppHeroHeader` / `AppDetailHeader`, con otra disposición.
   - Variante compacta para las pantallas sin arte.
3. **Navegación.** El rail ya existe desde 600 dp (T-043). Hay que verificar el alto mínimo con 5 pestañas, un rail compacto en altura y que no reste ancho útil al panel lateral.
4. **Hojas:** `AppSheet` pasa a **hoja lateral** (side sheet) a la derecha en horizontal.
5. **Diálogos:** distribución en dos columnas cuando no caben a lo alto.
6. **Rejillas:** ayudas de 2–3 columnas para tarjetas (novenas, santos, devociones) y un `AppTwoPane` adaptado al alto compacto.
7. **Capturas:** `FullAppScreenshotTest` admite `shots.width` y `shots.height`. Se añade el modo horizontal con 891×411 y 800×360 dp, y los alias `-Pshots.landscape=true`.

### Áreas, una tras otra para no chocar en los mismos archivos

| # | Área | Ideas de diseño, que el implementador y el crítico deben superar |
|---|---|---|
| L1 | **Rosario y Coronilla** | Cuentas y misterio en un panel fijo a la izquierda; oración actual y siguiente a la derecha, sin desplazar para leer. Controles de avance siempre visibles. |
| L2 | **Lectores** (capítulo bíblico, Misa, oración, día de novena, Noche, Catecismo, doctrina, lección, santo, devoción) | Columna de lectura con medida cómoda (60–75 caracteres) y un panel lateral con título, navegación, «Escuchar», tamaño de letra y referencias. La Biblia en horizontal ya tiene texto más arcos: integrarlo en el mismo sistema. |
| L3 | **Hoy** | Panel de 2–3 columnas: día litúrgico y lecturas, oración del momento y «Para ti hoy», y prácticas. Que se vea lo esencial sin desplazar. |
| L4 | **Orar y Leer** (centros) | Selector de segmentos en el panel lateral; rejillas de 3 columnas para novenas y oraciones; filtros siempre visibles. |
| L5 | **Listas con ficha**: Santos, Órdenes, Devociones, Preguntas, Catecismo (índice), Planes, Búsqueda | Dos paneles también con alto compacto (lista estrecha más ficha), resultados de búsqueda en columnas. |
| L6 | **Mi camino, Confesión, Recordatorios y Explorar** | Resumen y secciones lado a lado. En Confesión, examen y «Mi lista» en paralelo. Explorar en rejilla. |
| L7 | **Hojas, diálogos y bienvenida**: Apariencia, fuente, Tu camino, copia de seguridad, informe de errores, bienvenida, rueda de color | Hojas laterales y diálogos de dos columnas. Nada recortado ni botones fuera de pantalla. |

### Método: implementador más crítico duro, en bucle

Para cada área:
1. **Implementador** (agente Sonnet local, esfuerzo alto). Construye el diseño solo en los wrappers y en la pantalla del área. Genera capturas horizontales (891×411 y 800×360, en las 4 identidades, claro y oscuro, y además 130 %) y pasa los tests del área más la auditoría `NarrowLargeTextAuditTest`, ampliada a horizontal.
2. **Crítico** (agente independiente Opus, solo lectura de capturas y código, sin editar). Juzga como un director de arte exigente:
   - jerarquía;
   - ritmo y alineación;
   - uso del espacio;
   - legibilidad;
   - alcance de los pulgares;
   - coherencia con la vertical y entre identidades;
   - estados vacíos;
   - texto recortado.
   
   Devuelve **APROBADO** (calidad AAA, sin reservas) o una lista numerada de defectos con captura y propuesta.
3. **Repetir** pasos 1 y 2 hasta APROBADO, con un **máximo de 4 rondas por área**. Si se agota, el coordinador decide y lo deja anotado.
4. **Revisión opcional de Hermes** como segundo crítico externo. Las capturas aprobadas se suben a `docs/tasks/T-0xx-shots/` y Hermes devuelve observaciones en su rama. Sin bloquear: si le falta cuota, se sigue.
5. **El coordinador** revisa las capturas, hace commit, lleva el informe al README de encargos y publica una versión **cada una o dos áreas**, para que el dueño pruebe en su teléfono.

**Al final:** crítica global de recorrido completo en horizontal (la navegación entre áreas), la suite completa y la versión final del bloque.

### Riesgos y límites

- **Robolectric no es un teléfono real.** Antes de cerrar el bloque, el dueño debe probar en su teléfono las áreas L1–L3.
- **Cuota:** un área a la vez; los agentes reanudan desde el árbol si se cortan, con copias en `wip/*`.
- **Tamaño de la APK:** ya ronda los 100 MB. Si se supera el límite de `dist`, se publica solo en Releases.

## 3. Pendiente de rondas anteriores (no bloquea)

- **T-044 (Hermes):** imágenes de las 25 devociones marianas sin imagen. Integrar por lotes cuando lleguen.
- **T-039:** 26 órdenes siguen con monograma. Reintentar solo si aparecen emblemas válidos.
- **T-021 (Polish):** sin entregar desde hace días; queda absorbido por el bloque horizontal y por T-045.
- **Codex** vuelve el 21-nov: proponerle tareas acotadas que no choquen con el bloque horizontal, por ejemplo tests o contenido.
- **Tableta horizontal (T-043):** Hoy y Orar quedan en una columna centrada. Se resolverá con L3 y L4.
- **Idea abierta:** curso bíblico nuevo para Hermes (Marcos o María en la Escritura), si el dueño lo confirma.

---

## Actualización (20-nov, tarde): método horizontal reforzado

Esta sección **sustituye** al método anterior de la §2. El dueño pidió garantizar el nivel AAA y permite dar código a Hermes con indicaciones muy detalladas.

### Lo que faltaba y se añade

- **Diseño antes de código (fase D).**
  - Un agente diseñador escribe `docs/design/LANDSCAPE.md`. Para cada área L1–L7 incluye un boceto, la jerarquía, qué queda fijo y qué se desplaza, dónde va cada control, el comportamiento al girar, los insets y la adaptación a cada identidad.
  - El crítico lo aprueba con la rúbrica.
  - **El dueño ve el resumen** (Rosario, lectores, Hoy) antes de que se construya nada.
- **Rúbrica del crítico.** Opus independiente y duro, que exige **≥ 9/10 en cada criterio**:
  1. uso del espacio;
  2. jerarquía y ritmo;
  3. legibilidad (medida de 60–75 caracteres y contraste);
  4. ergonomía (alcance del pulgar y objetivos ≥ 44 dp);
  5. coherencia con la vista vertical y entre las 4 identidades, en claro y oscuro;
  6. estados (vacío, cargando, error y 130 %);
  7. insets, recortes de cámara y teclado;
  8. continuidad al girar;
  9. accesibilidad (TalkBack).

  El crítico compara cada captura horizontal con su versión vertical.
- **Casos propios del horizontal:**
  - el recorte de cámara en un lateral (insets `displayCutout` y `systemBars` a ambos lados);
  - la barra de gestos lateral;
  - el teclado, que tapa casi todo: campos visibles con `imePadding` y la acción principal alcanzable;
  - la continuidad al girar: posición de lectura, cuenta del Rosario, día de novena, texto escrito y selección.
- **Bucle por área:** se implementa, se capturan 2 tamaños × 4 identidades × claro/oscuro, más el 130 % y el teclado abierto, y se pasan el detector y los tests. Después actúa el crítico. Como máximo 5 rondas; si no se alcanza el ≥ 9, se escala al dueño con capturas.
- **Reparto:**
  - **L0** (fundamentos en `ui/design`): un agente local.
  - **L1, L2, L3, L4 y L7:** agentes locales, en secuencia.
  - **L5 y L6:** **Hermes**, en `ext/T-05x`. El encargo es muy detallado: el diseño aprobado, los archivos permitidos y prohibidos, los wrappers obligatorios, los tests y comandos de captura, y los criterios de la rúbrica. Hace un push por pantalla. Yo reviso el código y el crítico, las capturas.
- **Validación en el teléfono real.** Se publica una versión por cada tramo: L1+L2, L3+L4 y L5–L7. Cada una lleva una **lista de comprobación** breve para el dueño: girar, recortes, teclado, Rosario completo y lectura larga. Lo que reporte abre una ronda de corrección antes de seguir.
