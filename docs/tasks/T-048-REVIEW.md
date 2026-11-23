# T-048 · Revisión del coordinador (ronda 1)

- **Entrega revisada:** `ext/T-048` @ `78d6640`, integrada localmente sobre `CatholicGPT`.
- **Veredicto del crítico (rúbrica §11 + `better-interface`):** CAMBIOS.

| Criterio | 1 Espacio | 2 Jerarquía | 3 Legibilidad | 4 Ergonomía | 5 Coherencia | 6 Estados | 7 Insets | 8 Giro | 9 Accesibilidad |
|---|---|---|---|---|---|---|---|---|---|
| Nota | 7 | 7 | 8 | 9 | 6 | 5 | 8 | 9 | 6 |

**Lo que está bien:**
- La geometría de §4.2 se cumple: panel de 256 a 800, «Siguiente» de 56×56 al alcance del pulgar, y el hilo con zonas de 44 o más y arrastre de 24 dp.
- El giro conserva sesión, hoja, ancla y voz.
- El «L0 fix» de `endActions` es correcto y su regresión salió idéntica.

## Decisiones del coordinador

1. **Guía:** se aplica la especificación, no el punto 4 del encargo.
   - LANDSCAPE.md §14, decisión 1, midió que en la columna de contexto la guía queda cortada; el error estaba en el encargo.
   - La guía compacta va **en el panel de la oración**, entre el título y el texto.
   - Se acepta la excepción declarada: el Padrenuestro con guía a 800×360 desplaza.
2. **Superficie de lectura en oraciones cortas (P9):** se adopta la propuesta del crítico.
   - El área de desplazamiento sigue llegando hasta H − B.
   - El fondo opaco se pinta solo hasta `min(contenido + 24 dp, H − B)`.
3. **Corrector:** las correcciones las hace un agente local del coordinador sobre esta misma integración, porque a Codex le queda poca cuota. No hace falta que Codex haga nada.

## Defectos a corregir

| # | Gravedad | Defecto | Arreglo |
|---|---|---|---|
| 1 | ALTA | Matriz §12 sin entregar; las 4 PNG son anteriores al código | Regenerar la matriz de §12 con `_contact.png` |
| 2 | ALTA | El selector Rosario \| Coronilla no muestra cuál está elegido | Segmento elegido con `p.action`/`p.onAction` y peso Medium en el otro; en Sanctuary, radio 8 |
| 3 | ALTA | La guía no se ve y su botón no tiene estado visible | Decisión 1, más `LandscapePrayerPill(selected)` con `toggleable(role = Switch)`; `RosaryFitTest` comprueba que la guía se ve entera |
| 4 | MEDIA | El fundido pinta un rectángulo y la flecha parece un desplegable | Máscara `DstIn` sobre el contenido y flecha en `BottomEnd` |
| 5 | MEDIA | «Guía» y «···» se tocan | `spacedBy(8.dp)` |
| 6 | MEDIA | Título duplicado en la Coronilla | `nameRepeatsText`, como en vertical |
| 7 | MEDIA | Orden de TalkBack (§4.6) y acciones del hilo por cuenta | `traversalIndex`; las acciones avanzan por paso |
| 8 | MEDIA | El rótulo visible «Escuchar» no cambia al reproducir | «Pausar» mientras suena |
| 9 | MEDIA | El mapa se recorta en Opciones | Tamaño `min(ancho, alto)` y centrado |
| 10 | BAJA | Marcas V./R. pegadas al borde de la tarjeta | `start = 24`, `hang = 16` en `LandscapePhone` |
| 11 | BAJA | «Siguiente» deshabilitado no se distingue | Alfa .38 |
| 12 | BAJA | Hueco vacío sin arte; el marco de Sanctuary aparece en todas las identidades | `if (art != null)`; filete solo en Sanctuary |
| 13 | BAJA | Superficie vacía en oraciones cortas | Decisión 2 |

## Notas para T-057 (no bloquean)

- Extraer un `DevotionSpec` en lugar de las ramas `isRosary`.
- El selector se queda en dos segmentos y las coronillas van en un catálogo.
- Modelo de cuentas parametrizable.
- Generalizar `RosaryFitTest`.
- Test del `active` de `LiturgicalTtsManager` tras girar con la voz activa.
