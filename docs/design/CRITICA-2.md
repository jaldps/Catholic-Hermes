# Crítica de diseño horizontal · ronda 2

**VEREDICTO: CAMBIOS.** Faltan cambios pequeños: 22 de los 24 defectos quedaron resueltos tal como se pidió.

**Discrepancias que el crítico acepta:**
- **#1:** la guía sigue en el panel de la oración. Queda la excepción del Padrenuestro con la guía en pantallas de 800×360, ya declarada en §14.2.
- **#11:** «≥ 3 filas» se cuentan después de que se desplace la tira de 96 dp.

**Puntuación global:** espacio 8 · jerarquía 9 · legibilidad 9 · ergonomía 8 · coherencia 9 · estados 9 · insets 8 · giro 9 · accesibilidad 8. El desglose por área y criterio está en el informe del crítico.

## Defectos pendientes

1. **L2 §5.2: el pie del estudio bíblico (≥ 860 dp) se desborda.**
   - Necesita 406–463 dp y hay 299.
   - El selector de capítulo pasa a la cabecera del texto («San Juan · Capítulo 1 ▾», `bible_chapter_sheet`).
   - El pie queda en ‹ 44 · Escuchar 48 · Aa 44 · «Siguiente ›» 114, con huecos de 12 = 286 ≤ 299.
   - Añadir la suma a §5.2 y corregir el boceto del resumen.
2. **Presupuestos de panel sumados sin huecos.**
   - **Novena:** «Día N de M ▾» pasa al centro del pie del texto («‹ Día anterior · Día 3 de 9 ▾ · Día siguiente ›»). El panel queda en 166 + 28 + 8 + 44 = 246 ≤ 288.
   - **Confesión a 800:** filas de segmento de 44 en §3.12 con huecos de 8, total 334 ≤ 344.
   - **Orar a 891:** segmentos 3 × 44 y búsqueda de 48 con huecos de 8, total 385 ≤ 395.
   - **Regla en §3.11:** todo presupuesto incluye sus huecos y lo verifica `ESSENTIAL_BELOW_FOLD`.
3. **§15: el calendario de Calendario y Santuarios incumple los 44 dp.**
   - Se usa `kind = Today`.
   - Carril A: 7 × 44 + 16 = 324; navegación del mes 44 + días de la semana 20 + 6 × 44 = 328 ≤ 359.
   - Carril B: el día elegido (hoy por defecto), sin la tarjeta «Hoy» duplicada.
   - A 800, un solo carril: el mes y debajo el día.
4. **L5 §8.4 y §8.6: los criterios con el teclado abierto no se cumplen.**
   - En Búsqueda con teclado, lista plana: la sección va como antetítulo de cada fila y no hay cabeceras de grupo (56 + 64 = 120 ≤ 134).
   - En las listas: «≥ 1 fila completa si mide ≤ 78 dp; si no, su primera línea completa y la tecla Buscar visible», medido con los nombres más largos en ES y PT.
5. **L1 §4.2: cuentas de 24 dp como objetivo táctil.** Hay dos opciones:
   - (a) paso de ≥ 32 dp;
   - (b) el toque solo afecta a la cuenta actual ± 1, con zonas de ≥ 44, y el salto largo se hace arrastrando.

   En ambos casos el hilo es un control ajustable (`ProgressBarRangeInfo` + `setProgress`) con su exención documentada.
6. **§11: la rúbrica contradice el diseño.**
   - **Criterio 1:** «ninguna columna de contenido principal < 220; paneles y columnas de contexto ≥ 150».
   - **Criterio 9:** en pantallas con panel de apoyo, el orden de TalkBack de referencia es el de su sección (§4.6), leyendo por panel de izquierda a derecha.
7. **L3 §6.3: el presupuesto de Hoy está subestimado.** Hay que usar alturas medidas (ES y PT, con el momento de oración más largo, por ejemplo «Coronilla de la Divina Misericordia»).
8. **§15: la lista de una categoría de oraciones** pasa a la variante póster (§3.2).

## Resumen para el dueño

1. Actualizar el boceto de la Biblia según el defecto 1.
2. Aclarar que el pie con «Anterior / Siguiente» solo aparece en los lectores secuenciales. Mover «Día 3 de 9 ▾» al pie.
3. Añadir a las decisiones vetables la miniatura del Rosario en teléfonos pequeños.
4. Añadir a las decisiones vetables los filtros de lista-ficha en un panel lateral y la tira de arte que se desplaza.
