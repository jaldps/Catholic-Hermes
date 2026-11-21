# Crítica de diseño horizontal · ronda 1

**Crítico:** Opus independiente. **VEREDICTO: CAMBIOS.**

**Calibración del dueño.** Se optimiza para letra al 100 %. Al 130 % solo se exige lo mínimo: nada recortado, oculto ni inalcanzable. No se penaliza que el diseño se simplifique a ese tamaño.

## Puntuaciones (1–10)

| Área | Espacio | Jerarquía | Legibilidad | Ergonomía | Coherencia | Estados | Insets/IME | Giro | Accesib. |
|---|---|---|---|---|---|---|---|---|---|
| L0 | 7 | 8 | 7 | 8 | 8 | 6 | 6 | 6 | 8 |
| L1 Rosario | 6 | 7 | 8 | 7 | 8 | 7 | 8 | 8 | 8 |
| L2 Lectores | 6 | 7 | 7 | 5 | 6 | 6 | 7 | 8 | 8 |
| L3 Hoy | 8 | 7 | 7 | 8 | 8 | 6 | 8 | 8 | 8 |
| L4 Orar/Leer | 7 | 8 | 6 | 8 | 8 | 6 | 8 | 8 | 8 |
| L5 Lista-ficha | 6 | 6 | 8 | 8 | 6 | 6 | 6 | 7 | 8 |
| L6 | 6 | 7 | 6 | 8 | 7 | 6 | 7 | 8 | 8 |
| L7 | 7 | 8 | 8 | 8 | 8 | 7 | 6 | 8 | 8 |
| **Global** | 7 | 7 | 7 | 6 | 7 | 5 | 6 | 7 | 8 |

## Defectos que hay que corregir, por gravedad

1. **L1 §4.3 · La oración del Rosario no cabe con las cifras del propio documento.**
   - Ejemplos:
     - a 891 con la guía, el Avemaría necesita 160 dp y hay 147;
     - a 800, 180 dp frente a 160.
   - El presupuesto omite:
     - el hueco de 10 dp entre V. y R.;
     - la intención;
     - «Oración opcional»;
     - el factor ×1,12;
     - el paso «Anuncio del misterio».
   - La geometría del hilo tampoco cierra: 40 + 56 + 8 + 260 = 364, más que 356 y que 320.
   - **Cambios exigidos:**
     - La instrucción de la guía va a la columna de contexto, en lugar del fruto mientras la guía esté activa. «Después · …» va bajo el progreso.
     - Con H < 400, la pintura pasa a una miniatura de 56 × 56 junto al título y queda completa en «Ver el misterio».
     - El panel de cuentas mide `clamp(0,32 W, 256, 340)`.
     - Rehacer el presupuesto con S = 24 y B = 16.
     - Añadir un test con `TextLayoutResult` para el Padrenuestro, el Avemaría, el Gloria y «Oh Jesús mío» en ES, PT y EN, a 891×411 y 800×360, al 100 %, con la guía y sin ella. Resultado esperado: cero desplazamiento.
     - El «Anuncio del misterio» desplaza, o su meditación pasa a la columna de contexto.
     - Al 130 % puede desplazar, siempre con pista de desplazamiento y «Siguiente» fijo.
2. **L2 §5.1/§5.3 · En los lectores, la acción que avanza queda en el pulgar izquierdo, contra §2.3.**
   - Pie fijo de 56 dp + B en el panel de texto: «‹ Anterior» al inicio y la acción que avanza al final, con su centro a ≤ 120 dp del borde derecho seguro.
   - El pie izquierdo se queda solo con Escuchar y Aa.
   - La Biblia por debajo de 860 dp usa el mismo pie.
3. **L2 · El panel del lector no cabe a 800×360.** Quedan 98 dp para el índice, es decir, unas 2 filas.
   - El índice desplaza por su cuenta, con fundido, y la parte actual siempre visible.
   - Con H < 400, el índice se pliega a una fila de 44 dp («Primera lectura ▾») que lo abre en una hoja lateral.
4. **L2 · La rejilla 3×3 de días de novena no sirve.** Hay novenas de 10, 26, 30, 46, 54, 144 y 275 días.
   - Sustituir por el selector «Día N de M ‹ ›» de 44 dp más «Todos los días» en hoja lateral: rejilla de 7 columnas de 44 dp, con desplazamiento y el día actual centrado.
   - Se conservan los tags `novena_day_pill_N` y `novena_day_strip`.
5. **L0 §3.13/§12 · Las validaciones usan S = 0 y un teclado optimista.**
   - El arnés inyecta status 24 y gestos 16.
   - El teclado simulado principal mide 0,56 H (230 dp a 411 y 202 a 360).
   - Rehacer §4.3, §6.3, §7.1, §8.3, §8.4, §8.6, §9.5 y §10.3.
6. **L0 §3.3 · La banda compacta ocupa el 33 % de la altura.**
   - Debe medir `S + 84`.
   - El test se hace con S real.
7. **L0 §2.4 · Estado que cambia de padre al girar.**
   - Nada de `rememberSaveable` dentro de `header` ni de `supporting`: ese estado se eleva por encima de `AppPageLayout`.
   - Test de giro con texto escrito en la búsqueda.
   - Definir el paso de vertical a horizontal con una ficha apilada: se convierte en selección explícita en dos paneles, y «atrás» sale de la pantalla.
8. **Cobertura incompleta.**
   - Faltan estas pantallas:
     - Parroquias, Noticias, Calendario y Santuarios;
     - Doctrina e Historia, Doctrina, Historia;
     - Primeros pasos;
     - la lista de una categoría de oraciones;
     - la parte del Catecismo y el Compendio;
     - la ficha del curso.
   - Cada una necesita patrón, área, elementos esenciales y capturas.
   - Los avisos breves (toasts y banners) se anclan sobre el pie fijo de la columna activa, con un máximo de 480 dp de ancho.
9. **Estados sin diseñar.** Hace falta una tabla por área: estado → panel → tag → captura. Como mínimo:
   - liturgia de Hoy cargando y con error;
   - capítulo bíblico cargando;
   - Búsqueda sin resultados;
   - Novenas filtradas sin resultados;
   - Recordatorios vacío;
   - Planes sin plan;
   - Diario vacío;
   - Parroquias y Noticias sin conexión.
10. **§3.2 · Panel muerto cuando la ranura está vacía.**
    - Variante «póster»: el arte se prolonga hasta el pie con fundido y el título se queda en la línea 0,46 H − 14.
    - Se aplica a Mi camino, Recordatorios vacío, Planes y los centros de doctrina.
11. **L5 §8.3/§3.9 · Pedir «≥ 4 filas» es imposible, y el arte desaparece.**
    - Barra adhesiva de ≤ 56 dp: búsqueda más un botón de filtro que abre hoja lateral. La fila de 44 dp con filtros solo aparece si hay alguno activo.
    - Criterio: ≥ 3 filas a 891 y ≥ 2 a 800, medidas con los nombres ES y PT más largos.
    - Tira de arte de 96 dp con el título, que desaparece al desplazar.
    - Excepción explícita en la rúbrica 2.
12. **L4 §7.2 y L6 §9.2 · Lectores embebidos con medida insuficiente.**
    - Misa, «El sacramento» y los oficios de Noche pasan a `kind = Reader`: riel oculto y ≥ 56 caracteres a 800.
13. **L6 §9.2 · Confesión.**
    - En horizontal usa `kind = Immersive`: sin riel y carriles de ≥ 260 dp.
    - Corregir el boceto.
14. **L6 §9.1 · El panel de Hábitos desborda** (unos 485 dp frente a 411).
    - El calendario de fidelidad pasa al carril A.
    - El panel se queda con el cabezal y el anillo.
15. **L5 §8.6 · Contradicción en Búsqueda.**
    - Sin arte: barra adhesiva de 56 dp con atrás, el campo y borrar.
    - Los chips se ocultan mientras el teclado está abierto.
    - Criterio: ≥ 1 fila completa de resultados visible a 800×360 con el teclado.
16. **L1 §4.2 · El hilo de cuentas está sin definir.**
    - Arrastrar hacia arriba avanza y hacia abajo retrocede; 24 dp por cuenta, con un tic en cada una.
    - Geometría cerrada.
    - El inicio va en `max(safeStart, gestos) + 8`, y la aceptación se comprueba con gestos de 32 dp.
17. **L3 §6 · Jerarquía de Hoy.**
    - Un solo botón primario relleno, «Leer las lecturas». «Ver mis prácticas» pasa a secundario.
    - A 800, panel `clamp(0,28 Wc, 208, 280)`, con carriles de ≥ 228.
    - Captura en PT con ≤ 3 líneas por título.
18. **L0 §3.5 · Etiquetas del riel.**
    - «Mi camino» se muestra siempre en forma corta («Camino», «Path», «Caminho»), o se permiten 2 líneas con presupuesto (4 × 56 + 72 + 8 = 304 ≤ 320).
    - Describir el riel de 4 pestañas mientras T-046 no esté integrado.
19. **L0 §3.10 · Medida de lectura.**
    - `appReadingMeasure()` se mide con `TextMeasurer` en el estilo y la fuente reales del usuario, no con 0,5 em fijo.
    - Rehacer la tabla.
20. **L0 §2.1 · Clase de ventana.**
    - Se obtiene de `LocalWindowInfo.current.containerSize` o `WindowMetricsCalculator`: `Configuration` excluye las barras en API < 35.
    - Con H < 480 y W < 600 (pantalla dividida), se usa al menos la banda compacta y hojas laterales.
21. **Umbrales de ergonomía unificados.** Toda acción que avanza tiene su centro a ≤ 120 dp del borde derecho seguro y y ≥ 0,6 H.
22. **L7 · Desbordes.**
    - **Calendario a 360:** la navegación del mes va a la izquierda y la rejilla a la derecha.
    - **Bienvenida a 360:** «Tiempo para hoy» pasa a la columna izquierda.
    - **Opciones del Rosario:** «Voces» se muestra como lista vertical.
    - **Hoja de Fuente:** «Listo» sustituye a «Cerrar».
23. **§3.2 · El arte que crece «solo en esa pantalla» viola la regla de cabezal único.** El panel desplaza y la línea del título no se mueve.
24. **L1 y L2 · El Rosario y la Biblia no pueden maquetarse a mano.** Usan `AppPageLayout(kind = Immersive)` o un wrapper declarado en `AppComponents.kt`, como `AppSupportingPaneLayout`.

## El resumen para el dueño engaña o confunde

1. **Decisión 2:** promete sin desplazamiento oraciones que, según la especificación, sí desplazan. Además, el Credo y la Salve desplazan siempre.
2. **Boceto del Rosario:** debe decir «Anterior» y «Escuchar», no «Atrás» y «Oír».
3. **Boceto de Hoy:** muestra pestañas recortadas y una 5.ª pestaña que todavía no existe.
4. **Decisión 7:** dice que «pasan a una sola columna», pero en los centros solo se pierde una columna.
5. **Decisión 8:** dice que «las imágenes siempre se ven», pero no se ven en lista-ficha ni en Búsqueda.
6. **Biblia:** los controles van abajo a la derecha solo a partir de 860 dp.
7. **Capturas:** no enlaza las capturas de `docs/design/antes/`.
8. **Rosario:** el panel ocupa el 40 % del ancho, no «un tercio».
9. **Selector Rosario | Coronilla:** el boceto y la especificación lo sitúan en sitios distintos.
