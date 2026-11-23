# T-062 · Chuleta de las 8 skills, traducida a Compose

Leídas en orden: `better-interface`, `better-accessibility`, `better-layout`, `better-writing`, `better-typography`, `better-colors`, `better-ui`, `better-motion` (todas completas en `.claude/skills/*/SKILL.md`). `docs/design/TABLET-REVIEW.md` es el ejemplo real de esta app: foco en `lumenPress`, `reduceMotion` del sistema, un solo primario, puntuación y medida de lectura.

Convenciones de traducción usadas en todo el documento: `px/rem → dp/sp`; `aria-label → contentDescription` o `Modifier.semantics { contentDescription = … }`; `role="button" → Role.Button` con `lumenPress`; `heading → Modifier.semantics { heading() }`; `prefers-reduced-motion → LocalAppDesign.current.reduceMotion`; color → `LocalAppDesign.current.palette.*`; espacio → `LocalAppDesign.current.spacing.*` y múltiplos de 4 dp; tipo → `LocalAppTextStyles.current.*`; componente → wrappers de `ui/design/AppComponents.kt`; ellipsis → `appMaxLines(n)` + `TextOverflow.Ellipsis`. **No tocar** `lumenPress` (anillo de foco ya resuelto), `ui/design`, `ui/theme` ni `MainApp.kt`.

## 1. better-interface (orquestación y evidencia)

1. Resuelve el alcance antes de juzgar: una pantalla o un flujo completo, nunca «toda la app de una vez». Di qué queda fuera.
2. Revisa en este orden para que lo estructural no se esconda detrás del pulido: accesibilidad → layout → escritura → tipografía → color → UI → movimiento.
3. Todo hallazgo lleva evidencia: `archivo:línea` y el fallo visto en la captura. Si no lo puedes verificar, escríbelo como «No verificado», nunca como aprobado ni como hallazgo.
4. Un only primario por vista y la escalera de arreglo más barata: borrar → usar el wrapper nativo existente → reutilizar token → corregir el valor → añadir algo nuevo.
5. Unifica hallazgos con la misma causa raíz en una sola fila (máx. 15), prioriza HIGH y arregla primero lo que afecta a más pantallas; la revisión es de solo lectura salvo que el encargo pida implementar.

## 2. better-accessibility (WCAG 2.2 A/AA)

1. Un botón de solo icono lleva `contentDescription` con la **acción** («Buscar», «Cerrar filtros»), nunca con el dibujo («lupa», «x»). Usa `Role.Button` si el control no es un `Button` nativo.
2. Todo objetivo táctil mide al menos 44 × 44 dp; si el glyph es más pequeño, amplía el área con `sizeIn(minWidth = 44.dp, minHeight = 44.dp)` sin que se solapen los vecinos.
3. Los títulos de sección se marcan con `Modifier.semantics { heading() }` y el orden de composición coincide con el orden visual; no reordromes con `weight` ni `align` lo que el lector debe oír en otro sitio.
4. Ningún estado se comunica solo con color: junto al color va icono, texto o `selected` en semántica (`AppListRow(selected = …)`, pestañas con `Role.Tab`).
5. Si hay movimiento, respeta `LocalAppDesign.current.reduceMotion` (ya incluye el ajuste del sistema); con reduceMotion, cross-fade de opacidad en lugar de desplazamiento. No toques el anillo de foco de `lumenPress`.

## 3. better-layout (espacio, alineación, crecimiento)

1. Agrupa con espacio: el hueco entre grupos es ≥ 2× el hueco dentro del grupo. Usa `spacing.gutter`, `spacing.sectionGap` y múltiplos de 4 dp; no añadas divisores donde el espacio ya separa.
2. Como máximo un `AppPrimaryCapsule` relleno por pantalla; el resto son `AppSecondaryCapsule` u otros secundarios. Dos primarios compiten y rompen la jerarquía (hallazgo real en Hoy).
3. Alinea los textos a bordes compartidos (±1 dp) y deja márgenes laterales con `spacing.gutter`; los elementos que empiezan en la misma columna deben empezar en el mismo `padding(horizontal = …)`.
4. Regla P9 del dueño: ningún hueco > 24 dp entre un título y su contenido, y ninguna franja vacía > 48 dp sin propósito. Si aparece, búsquese una causa (panel sin contenido, `Spacer` huérfano) y anótala.
5. Diseña para texto traducido que crece: `fillMaxWidth()`, `widthIn(max = …)`, `heightIn(min = …)` y `appMaxLines(n)` con ellipsis; nunca `width(...)` ni `height(...)` fijos sobre texto. Si algo queda fuera de pantalla, deja que asome una parte o añade una señal («Ver más»).

## 4. better-writing (textos de interfaz)

1. Inventaría primero cómo se llama cada cosa y cada acción en ES/EN/PT (`tr(...)`, `pick(...)`, `strings(...)`); un mismo objeto se llama igual en toda la pantalla y en los tres idiomas.
2. Los botones empiezan por verbo y nombran la acción: «Guardar», «Ver mis prácticas», «Reintentar». Nunca «OK», «Sí», «Aceptar» suelto ni signos de exclamación en errores.
3. Un error dice qué falló y cómo seguir, junto al control afectado: «No se pudo abrir este texto. Vuelve a intentarlo.» (hallazgo real de `ContentLoadNotice`). Un estado vacío dice qué es y ofrece una acción.
4. Escribe frases completas con parámetros (`stringResource(R.string.x, n)`), no concatenaciones; la posición del número cambia entre idiomas. Usa `plurals` si el recuento varía.
5. Una sola política de mayúsculas por tipo de elemento y tono acorde a la importancia: neutral en ajustes, cálido en estados vacíos, sobrio en errores. Cambiar solo una etiqueta «porque suena mejor» no es un hallazgo.

## 5. better-typography (render del texto)

1. Usa siempre `LocalAppTextStyles.current.*` (`largeTitle`, `title1`, `body`, `footnote`…): no hay `fontSize`, `fontWeight` ni `lineHeight` literales en una pantalla.
2. La medida de lectura larga es 60–75 caracteres; en vertical suele cumplirse, pero revisa tableta con `appReadingMeasure()` y no fuerces una columna fija de 640 dp (hallazgo real).
3. Puntuación typográfica en los textos de interfaz: «…» no «...», guion largo «—» para rangos, y nunca un espacio antes de `. , ; : ! ? »` (T-060 limpió la Biblia; no lo reintroduzcas en etiquetas).
4. Los valores que cambian (rachas, «3 de 9», temporizadores) usan la variante tabular del estilo para que las cifras no bailen; el resto de la prosa mantiene cifras proporcionales.
5. Los textos largos se cortan con `appMaxLines(n)` + `TextOverflow.Ellipsis`, pero siempre queda una vía al completo: ficha, expandir o `detail_open_reader`. Nunca `maxLines = 1` en un resumen que el usuario necesita.

## 6. better-colors (tokens y contraste)

1. Usa únicamente `LocalAppDesign.current.palette.*` (`textPrimary`, `textSecondary`, `action`, `separator`…). Nada de `Color(0xFF…)` ni hexadecimales en la pantalla; si falta un rol, va al informe «Para la capa compartida».
2. Un color, un significado: el color de acento / acción no se reutiliza para decorar; el rojo/aviso no se usa en una acción no destructiva.
3. Un solo botón relleno por vista; el color de relleno marca la acción primaria, no el texto de un botón neutro (ese parece enlace).
4. Contraste: texto ≥ 4,5:1 sobre el fondo realmente renderizado, texto grande ≥ 3:1 y bordes/controles ≥ 3:1. **Nunca informes un ratio que no hayas medido**; si dudas, mídelo en la captura.
5. El color nunca es el único portador del estado: acompáñalo de icono o texto, y cambia luminosidad (no solo tono) cuando haya que corregir un contraste. Las cuatro identidades y claro/oscuro se revisan por separado.

## 7. better-ui (pulido de superficies e iconos)

1. Si una superficie anidada comparte inset parejo, el radio exterior = radio interior + padding + borde. Usa los radios del tema (`design.shapes.*`); no inventes valores.
2. Alineación óptica: si un icono parece descentrado, ajusta por ojo unos dp en su lado; no sustituyas la token por un número nuevo global.
3. Sombras para elevación, bordes para estructura. Un borde que solo da profundidad se convierte en sombra; los divisores y selecciones conservan su borde de 1 dp con `palette.separator`.
4. Los iconos usan el `ImageVector` del proyecto y `tint = palette.*` (equivalente a `currentColor`): un mismo drawable cambia de color con el estado, no un asset por estado. El trazo acompaña al peso del texto vecino.
5. El hover no existe en táctil: evita cambios de color que se queden «pegados» tras el toque; el pressed lo gestiona `lumenPress`. El scroll dentro de hojas y diálogos no debe arrastrar la pantalla de detrás.

## 8. better-motion (movimiento)

1. Primero preguntar si debe animarse: en interacciones frecuentes (teclear, cambiar de pestaña) basta el cambio instantáneo o ≤ 150 ms de opacidad. Quitar una animación también es una solución.
2. Entradas: 150 ms para elementos pequeños, 250 ms para menús, 350–450 ms para paneles; salidas más cortas (150 ms) y con menor distancia que las entradas.
3. Usa `ease-out` para entradas y cambios de estado; `spring` solo para objetos con peso (hojas, tiradores), sin rebote. Color y opacidad nunca llevan spring.
4. Todo movimiento, escala o desenfoque se envuelve en `if (!reduceMotion)`; con reduceMotion se sustituye por un cross-fade de opacidad. La pulsación de 0,96 es feedback breve y puede permanecer.
5. Cada cambio de estado deja una huella estática (color, icono o texto): el movimiento nunca es el único indicador. Revisa la animación a una décima de velocidad antes de darla por terminada.
