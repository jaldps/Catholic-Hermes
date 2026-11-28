# Crítico T-070 · Ilustraciones v2 «medallas de marca» (Chaty)

**Veredicto: CAMBIOS.** La dirección es la buena y la serie de Día es coherente: medallón, arco de tres estrellas idéntico en las nueve, duotono relleno y filete oro. El libro, el corazón y el diario ya se leen como de la misma familia que el símbolo. Lo que bloquea es mecánico y se arregla rápido. El filete queda partido a 5–6 px. De Noche, la vela tiene la llama despegada y el rosario se queda en una línea de puntos. Además hay una decisión de Noche que corresponde al dueño.

Método: he leído los 18 SVG, las 4 láminas y los 36 PNG, y he hecho rejillas a 256 px y filas a 64 px sobre las superficies `#FFFFFF` / `#162438`. Con un script he medido el ancho visible de los filetes, el contraste WCAG, el radio máximo frente al medallón y los elementos y atributos SVG. No hay evidencia en dispositivo.

## H · Bloquean la integración

**H1. El filete oro queda partido a la mitad en el lado de sombra.** La capa `navy-dark` se pinta encima del trazo de 12 px del cuerpo y tapa su mitad interior.
- Medido en los PNG de Día a 384, en unidades de 512:
  - corazón, y=233: izquierda 12 px, **derecha 5 px**;
  - lupa, y=245: 10 px / **5 px**;
  - lomo del diario: **5 px**;
  - cima izquierda del libro: 8 px frente a 12 px abajo;
  - base de la nube: tapada igual.
- Incumple «filete 12 px» y «ningún trazo < 8 px». De Noche la silueta depende solo de ese filete (ver M1), así que ese lado queda en **≈1 dp a 96 dp**: es el mismo fallo de línea fina de T-068.
- **Arreglo:** saca el filete a un path aparte, solo trazo (`fill="none"`, `stroke` oro, 12 px), en la capa `gold` y **pintado encima** de `navy-dark`. La alternativa es encoger `navy-dark` 6 px hacia dentro.

**H2. De Noche, la llama de la vela flota.**
- La mecha es `#2C4A72` sobre `#1D2D44` (**1,54:1**). En `empty-candle-night-384.png`, columna x=256, los píxeles de y=209 a 223 son (44,74,114): lo único que une la llama con la vela es el halo dorado que pasa por encima.
- El encargo pide expresamente una llama «unida a la mecha».
- Además, de Día y de Noche, el halo (r 60, centro 256,168) se pinta **sobre** la parte superior de la vela, que en y≈220–232 queda cruzada por un aro dorado.
- **Arreglo:**
  - mecha de Noche en `#B47C20` (3,87:1; el encargo lo admite en metal) o en `#F8E8BC`;
  - la base de la llama solapa la mecha al menos 8 px;
  - el path del halo pasa a dibujarse antes del cuerpo de la vela, para que quede detrás, o se reduce a r 52 con centro en (256,160).

**H3. Rosario: detalle por debajo del mínimo; de Noche es una línea de puntos.**
- Las cuentas pequeñas tienen r 13 con un contorno de 8 px: el oro visible mide **18 px (< 24 px)**.
- La cuenta central (r 20) se solapa con sus vecinas (distancia 22,8 frente a 33 de radios sumados) y forma un grumo en (236–276, 319–348).
- Es la medalla con menos masa: **18 % de cobertura frente al 24–38 %** de las demás.
- El crucifijo queda a unos 5 px del borde del medallón (y=451 frente a 456).
- De Noche:
  - el contorno `#2C4A72` desaparece;
  - el hilo pasa de marino a oro de 12 px (el dibujo ya no es el mismo que de Día);
  - las cuentas de 18 px apenas se distinguen del hilo: a 64 dp se lee como el «óvalo de puntos» que el encargo pide evitar.
- **Arreglo:**
  - cuentas pequeñas de r 15 sin contorno (30 px de oro), 5 por lado;
  - las de Padre Nuestro, de r 22;
  - al menos 6 px de aire entre cuentas;
  - de Noche, hilo de 8 px en `#B47C20` para que las cuentas `#E3B65A` se separen por tono;
  - el conjunto sube unos 16 px para dejar al menos 16 px hasta el borde.

## M · Antes de la versión

**M1. De Noche, el lenguaje «relleno» se pierde (decisión del dueño).**
- Con el cuerpo `#2C4A72` sobre el medallón `#1D2D44` (1,54:1), el corazón, la nube, el libro, el aro de la lupa, la tapa del diario y el candelero se leen como **contornos dorados con un hueco** (rejilla de Noche, filas a 64 dp).
- La regla de ≥ 3:1 solo se cumple gracias al filete (oro/medallón 7,35:1), y eso exige H1 resuelto.
- Un marino que llegue a 3:1 (`#5A7FB2`, 3,38:1) deja de ser marino y baja el oro sobre el cuerpo a 2,17:1.
- **Opciones para el dueño:**
  - (a) aceptar la silueta por filete, una vez arreglado H1;
  - (b) **mi recomendación:** invertir de Noche, con cuerpo marfil `#EDE6D8` (11,19:1, que es el `brand` de Noche de §2.2), detalles marino y filete oro.

**M2. Los `id` de capa no corresponden a su contenido.** Eso impide teñir por tema, como pide el encargo.
- Ejemplos:
  - paloma: el cuerpo marfil está en `navy-dark`;
  - cruz: el nimbo marfil está en `navy`;
  - rosario: el crucifijo oro está en `ivory` y, de Noche, el hilo oro está en `navy`;
  - corazón y diario: hay contornos marinos dentro de `gold`;
  - los filetes oro son trazos de los paths `navy`.
- El README lo reconoce («colores por path»).
- **Arreglo:** cada grupo contiene solo su color, y los filetes y contornos van como paths de trazo aparte. Si se decide entregar `drawable/` + `drawable-night/` sin tinte, hay que decirlo y retirar la promesa del README.

**M3. La lupa se sale del medallón.**
- El extremo del mango con su filete llega a un radio de **204 > 200** (≈5 px fuera, abajo a la derecha). AGENTS pide que la ilustración quede dentro de su marco.
- **Arreglo:** acortar el mango unos 24 px (extremo a un radio ≤ 188).

**M4. Cruz.**
- Hay una cuña marfil huérfana `#F8E8BC` en (191–228, 170–199): 1,11:1 sobre el nimbo, invisible y suelta.
- De Día, el nimbo es del mismo marfil que el medallón y solo lo delimita un aro marino de 12 px, así que se lee como cruz celta o punto de mira. De Noche es un disco marfil brillante (12,68:1), y las dos versiones se leen distinto.
- La sombra es incoherente: el brazo derecho está entero en oscuro y el vertical solo a medias.
- **Arreglo:**
  - borrar la cuña;
  - nimbo en `#F8E8BC` con aro de filete oro de 12 px en los dos temas;
  - sombra solo en la mitad derecha de los dos brazos.

**M5. Paloma.**
- A 96 dp el ramo de olivo es un nudo o «pretzel» (hojas de unos 30 px con bordes marinos de 3 px).
- El aro exterior marino de 24 px bajo el filete deja una banda de **6 px** (< 8) por fuera del oro. Es la única medalla con doble contorno, y de Noche se ve como un halo turbio.
- La anatomía está bien: el ala no corta la cabeza.
- **Arreglo:**
  - quitar el aro exterior y dejar que el filete oro defina la silueta; de Día el marfil sobre marfil se separa por el filete y el ala interior;
  - un tallo de 12 px y dos hojas de 40×20 px, sin borde marino, hacia delante y abajo desde el pico.

**M6. Corazón.**
- La llama es una gota simétrica con núcleo marfil y la cruz se monta sobre su punta (la cruz llega a y=155 y la punta de la llama está en y=137). A 96 dp se lee como un adorno o un frasco, y la lectura de «gota» que el encargo quería eliminar persiste.
- Los bordes marinos de la cruz miden **4 px**.
- **Arreglo:**
  - llama asimétrica con 2–3 lenguas, base ancha fundida con la hendidura;
  - cruz separada 8 px por encima, con brazos de 16 px de oro y sin borde marino.

## L · Pulido

1. **Vela:**
   - tiene 8 formas (el máximo es 6);
   - el halo pasa a unos 11 px de la tercera estrella;
   - la franja `#F8E8BC` aporta poco.
2. **Lupa:**
   - la media luna `navy-dark` corta en vertical a las 12 y a las 6, con un escalón visible;
   - el radio interior (85) no coincide con el cristal (79+4) y deja una lámina marina;
   - la estrella dorada sobre el cristal está a 1,97:1 y sin contorno, a diferencia del resto de formas oro.
3. **Contornos marinos alrededor del oro** (cuentas, llama, «1», pluma, crucifijo): dan un aire de pegatina que no tiene el símbolo, donde el oro va limpio. Además son irregulares: la pluma tiene 12 px, el resto 8, y la estrella y la cinta del diario no llevan ninguno. Hay que unificar, por ejemplo a 8 px en todas las formas oro sobre marfil.
4. **Diario:**
   - se parece mucho a Material `history_edu` (hoja con pluma);
   - la cinta sale por el borde superior y se lee como pestaña;
   - tiene 7 formas.
   - Propuesta: libreta cerrada con la cinta colgando por abajo.
5. **Sin conexión:**
   - la barra de 42 px se lee como un tablón;
   - los extremos que salen de la nube quedan marfil sobre marfil.
   - Propuesta: barra de 32 px que sobresalga como mucho 16 px.
6. **Libro:**
   - el canto marfil de las páginas tiene 14 px visibles (< 24);
   - es aceptable porque viene del símbolo.
7. **Medallón de Noche frente a la tarjeta `#162438` y el lienzo:** 1,1–1,26:1, casi invisible. Es aceptable como placa sutil, pero que se sepa.
8. **El informe dice «grosores mínimos validados»,** pero midió el atributo `stroke-width`, no el ancho visible. Hay anchos visibles de 3–6 px (H1, M5, M6).

## Lo que está bien

- SVG limpios para VectorDrawable:
  - solo `svg`, `g` (90) y `path` (132);
  - atributos solo `d`, `fill`, `stroke`, `stroke-width`, `linecap`, `linejoin`;
  - sin `transform`, máscaras, filtros, `clipPath` ni `dasharray`;
  - trazos de 8, 12, 14, 20 y 24.
- Geometría idéntica de Día a Noche (solo cambian los colores).
- Un único oro por tema, y `#B47C20` no se usa.
- Sin rostros ni figuras, sin espinas ni sangre.
- Paloma con alas en V y sin corte de la cabeza.
- Ningún parecido con obras religiosas concretas: la cruz con nimbo roza la cruz celta (ver M4) y el corazón con llama y cruz es iconografía genérica.
- Arco de estrellas idéntico en las nueve, con la estrella del logo.
- Láminas a 96 px y fila de marca con el símbolo a 46 px, como se pidió.
- PNG transparentes a 288 y 384 px completos (36 más 4 láminas).

## Veredicto por ilustración

| Ilustración | Veredicto |
|---|---|
| `empty-book` | **APROBADO CON CAMBIOS.** La mejor, en el lenguaje del símbolo; arreglar el filete de la cima (H1). |
| `empty-heart` | **CAMBIOS.** Filete derecho de 5 px (H1) y llama-gota con la cruz pegada (M6). |
| `empty-journal` | **APROBADO CON CAMBIOS.** Lomo de 5 px (H1) y aire Material (L4). |
| `empty-search` | **CAMBIOS.** Filete derecho de 5 px (H1), se sale del medallón (M3) y seam en el aro (L2). |
| `empty-candle` | **CAMBIOS.** Llama flotante de Noche y halo sobre la vela (H2); 8 formas (L1). |
| `empty-dove` | **APROBADO CON CAMBIOS.** Olivo ilegible y doble contorno de 6 px (M5). |
| `empty-rosary` | **CAMBIOS.** Cuentas de 18 px, grumo central, poca masa y Noche en puntos (H3). |
| `empty-cross` | **CAMBIOS.** Cuña huérfana, nimbo de punto de mira y Día ≠ Noche (M4). |
| `error-offline` | **APROBADO CON CAMBIOS.** Base de filete tapada (H1) y barra-tablón (L5). |

**Global: CAMBIOS.** Hay que corregir H1–H3, más M2–M6, y que el dueño decida M1 (recomiendo cuerpo marfil de Noche). Después, rehacer las láminas y repetir el crítico solo sobre las láminas.
