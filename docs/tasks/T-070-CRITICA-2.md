# Crítico T-070 · Ronda 2 (ext/T-070 @ 4c91c97)

**Veredicto: CAMBIOS, acotados.** Queda resuelto casi todo lo de la ronda 1 y la Noche invertida funciona. Quedan:
- **1 bloqueo nuevo:** la paloma de Día;
- **3 puntos M:** la cruz de Noche, la barra de «sin conexión» de Noche y el rosario de Día.

Para la ronda 3 basta con revisar esos cuatro.

Método:
- He leído los 18 SVG y las 4 láminas.
- He hecho rejillas a 256 px y filas a 96 y 64 px sobre `#FFFFFF` / `#162438`.
- He medido con un script el ancho visible del filete en los PNG de 384, el radio máximo frente al medallón, los colores por grupo y el contraste WCAG.
- No hay prueba en dispositivo.

## Puntos de la ronda 1

| Punto | Estado | Evidencia |
|---|---|---|
| **H1** filete partido | ✅ | Ancho visible a ambos lados (en unidades de 512): corazón y=233, 12/12 (Noche 12/12); lupa y=245, 10,7/10,7 (12/12); lomo del diario 10,7 (12); cima del libro 12; nube correcta. |
| **H2** vela de Noche | ✅ | Mecha `#F8E8BC` de 12 px (11,42:1 frente al medallón), de y195 a 231. La llama la solapa 18 px. El halo, r52 en (256,160), se pinta detrás. |
| **H3** rosario | ✅ | Cuentas r15 y r22 sin contorno; aire mínimo entre cuentas de 7,0 px. Hilo de Noche `#B47C20` de 8 px (3,87:1). Cruz hasta y431, a 25 px del borde. Una regresión nueva: ver M-c. |
| **M1** Noche invertida (b) | ✅ con 2 excepciones | Cuerpo `#EDE6D8`/medallón 11,19:1; detalle `#10345C`/cuerpo 10,14:1; detalle/sombra `#C9BFAE` 6,92:1. El libro, el corazón, la lupa, el diario, la vela y la paloma se leen igual que de Día. Excepciones: cruz y barra de «sin conexión» (M-a y M-b). |
| **M2** capas | ✅ | Script: **0 grupos con más de un color**. Cada rol tiene un único color por tema: `navy` `#10345C`/`#EDE6D8`, `navy-dark` `#0C2848`/`#C9BFAE`, `gold` `#DCA83C`/`#E3B65A`, `details` `#10345C`, `warm` `#F8E8BC`, `thread` `#10345C`/`#B47C20`, `wick` `#10345C`/`#F8E8BC`. Los `id` nombran roles, no colores, y el README lo explica. |
| **M3** lupa | ✅ | Ningún píxel del motivo pasa del radio 196: queda dentro del medallón. |
| **M4** cruz | ✅ de Día, ⚠ de Noche | La cuña está borrada; el nimbo es `#F8E8BC` con aro de filete oro; la sombra cae en la mitad derecha de los dos brazos. La Noche se trata en M-a. |
| **M5** paloma | ⚠ | El aro exterior está quitado; el olivo tiene tallo de 12 px y dos hojas rellenas que ya se leen. **Pero de Día ha empeorado:** ver H. |
| **M6** corazón | ✅ | Llama asimétrica con lenguas y base ancha fundida con la hendidura; cruz de 16 px separada. Ya no se lee como gota. Es de los mejores. |

## H · Bloquea

**H. Paloma de Día: marfil sobre marfil, y la silueta solo la da un filete a 1,97:1.**
- El cuerpo `#FCF4E0` está sobre el medallón `#FCF4E0` (1,00:1), y el ala lejana `#F8E8BC` da 1,11:1. Lo único que dibuja la paloma es el trazo oro de 12 px.
- A 96 y a 64 dp (fila de Día) es la única medalla que se lee como **dibujo de línea dorada**, justo el defecto que T-070 corrige, y no cumple la regla de ≥ 3:1 de Día.
- De Noche está bien: cuerpo `#EDE6D8` a 11,19:1.
- **Arreglo, coherente con la regla de la serie** (cuerpo marino de Día y marfil de Noche):
  - el path del cuerpo pasa al grupo `navy`, así que de Día es `#10345C` (11,49:1) y de Noche sigue en `#EDE6D8`;
  - el ala lejana se queda en `warm` (10,35:1 sobre marino);
  - el ojo y la línea del ala pasan a un rol que de Día sea marfil y de Noche marino; por ejemplo, el color del medallón: `#FCF4E0` / `#1D2D44`, 11,49 / 11,19:1.
- **Alternativa, si se quiere mantener la paloma marfil:** contorno marino exterior de 8 px visibles (un trazo `#10345C` de 28 px por debajo del filete de 12). De Noche ese contorno se pinta del color del medallón.

## M · Antes de la versión

**M-a. Cruz de Noche: la cruz no se separa de su nimbo.**
- El cuerpo `#EDE6D8` sobre el nimbo `#F8E8BC` da **1,02:1**, y el filete oro sobre el cuerpo, 1,52:1. Solo la separa la línea marina interior de 8 px (`details-cross`), así que de Noche se lee como una cruz hueca de doble contorno, distinta de la cruz marina maciza de Día.
- **Arreglo:** de Noche, el cuerpo de la cruz en `#10345C` (10,35:1 sobre el nimbo) y la sombra en `#0C2848`, con lo que se lee igual que de Día. La línea `details-cross` sobra en los dos temas: de Día es marino sobre marino, un path muerto.

**M-b. «Sin conexión» de Noche: la barra se funde con la nube.**
- La barra `#EDE6D8` va sobre la nube `#EDE6D8` (1,00:1), y el filete oro da 1,52:1 contra las dos.
- A 64 dp la señal de error queda en una raya dorada tenue.
- **Arreglo:** de Noche, la barra en `#10345C` con su filete oro, que es el equivalente invertido de la barra marfil sobre nube marina de Día.
- **Pulido:** la barra sobresale de la nube unos 40 px arriba a la derecha (pedí 16 px como máximo).

**M-c. Rosario de Día y choque con las estrellas.**
- Cuentas oro sin contorno sobre marfil: **1,97:1**. La forma la sostiene el hilo marino de 8 px. Es como lo hace el símbolo, pero no cumple la regla de ≥ 3:1 del encargo. Quitar el contorno fue recomendación mía; el dueño decide entre:
  - (a) aceptar el oro limpio del símbolo;
  - (b) cuentas de Día en `#B47C20` (3,28:1; el encargo lo admite en metal).
- **Regresión nueva:** la cuenta superior izquierda (r22 en 157,155) queda a unos **4,5 px de la estrella inferior del arco**, casi tocándola.
- **Arreglo:** bajar las dos cuentas de los extremos unos 12 px (a y≈167) o cerrar el arco 6 px hacia dentro.

## L · Pulido

1. **Vela de Día:** el cuerpo es del mismo marfil que el medallón y se lee como un vaso vacío con contorno. Propuesta: pintarlo en `warm` `#F8E8BC` para que tenga masa de cera.
2. **Libro de Día:** las líneas curvas nuevas de las páginas son marino sobre marino, con 1,0–1,18:1, invisibles. De Noche funcionan bien. Aceptable, pero son paths muertos de Día.
3. **El informe interpreta «6 formas» como elementos visuales:** la vela tiene 11 paths. Lo acepto: visualmente son 6.

## Veredicto por ilustración

| Ilustración | Veredicto |
|---|---|
| `empty-book` | **APROBADO.** L2 es menor. |
| `empty-heart` | **APROBADO.** |
| `empty-journal` | **APROBADO.** |
| `empty-search` | **APROBADO.** |
| `empty-candle` | **APROBADO.** L1 es opcional. |
| `empty-dove` | **CAMBIOS.** De Día es línea oro sobre marfil (H). |
| `empty-rosary` | **APROBADO CON CAMBIOS.** Choque con la estrella; contraste de Día a decidir por el dueño (M-c). |
| `empty-cross` | **APROBADO CON CAMBIOS.** De Noche, cuerpo marino (M-a). |
| `error-offline` | **APROBADO CON CAMBIOS.** De Noche, barra marina (M-b). |

**Global: CAMBIOS, acotados.**
- Seis de las nueve se pueden integrar ya.
- La paloma de Día bloquea.
- La cruz, la barra y el rosario son cambios de color o de posición de minutos.
- Ronda 3: verificar solo esos cuatro.
