# T-070 · Revisión 1 (crítico independiente, 28-nov)

**Veredicto: CAMBIOS.**

Revisado: `ext/T-070` @ `766372d`. El detalle completo, con medidas y coordenadas, está en [`T-070-CRITICA-1.md`](T-070-CRITICA-1.md).

**La dirección es la buena.** La serie de Día es coherente: medallón, el mismo arco de estrellas en las nueve, duotono relleno y filete oro. El libro, el corazón y el diario ya son de la familia del símbolo, y los SVG están limpios para VectorDrawable. **Lo que falla es mecánico** y se corrige rápido.

## Bloqueos (H)

1. **El filete oro queda partido.** La capa `navy-dark` se pinta encima y tapa la mitad interior del trazo de 12 px: en el lado de sombra quedan 5 px.
   - Afecta al corazón, la lupa, el lomo del diario, la cima del libro y la base de la nube.
   - **Arreglo:** el filete en un path aparte, solo trazo (`fill="none"`, oro, 12 px), en la capa `gold` y **encima** de `navy-dark`.
2. **De Noche, la llama de la vela flota.** La mecha es `#2C4A72` sobre `#1D2D44`, con contraste 1,54:1.
   - Mecha de Noche en `#B47C20` o en `#F8E8BC`.
   - La base de la llama solapa la mecha al menos 8 px.
   - El halo va **detrás** de la vela, o pasa a r 52 con centro en (256,160).
3. **Rosario:** el detalle queda por debajo del mínimo y de Noche se lee como una línea de puntos.
   - Cuentas pequeñas de r 15 sin contorno, las de Padre Nuestro de r 22, y al menos 6 px de aire entre cuentas.
   - De Noche, el hilo de 8 px en `#B47C20`.
   - Sube el conjunto unos 16 px para dejar al menos 16 px hasta el borde.

## Antes de la versión (M)

- **M2. Capas.** Cada grupo `id` contiene solo su color (`navy`, `navy-dark`, `ivory`, `gold`, `medallion`). Los filetes y contornos van como paths de trazo aparte. Si no, no se puede teñir por tema.
- **M3. Lupa.** Se sale del medallón: el mango llega a un radio de 204. Acórtalo unos 24 px.
- **M4. Cruz.**
  - Borra la cuña marfil suelta.
  - Nimbo en `#F8E8BC` con aro de filete oro de 12 px en los dos temas.
  - Sombra solo en la mitad derecha de los dos brazos.
- **M5. Paloma.**
  - Quita el aro marino exterior.
  - El olivo: tallo de 12 px y dos hojas de 40×20 px, sin borde, hacia delante y abajo.
- **M6. Corazón.**
  - Llama asimétrica, con 2–3 lenguas y base ancha.
  - La cruz separada 8 px por encima, con brazos de 16 px sin borde marino.

## Pulido (L)

Lo que se pueda: la vela con 6 formas como máximo; la lupa sin escalón en el aro; los contornos marinos alrededor del oro, unificados o quitados; la cinta del diario dentro del libro; la barra de «sin conexión» de 32 px que sobresalga como mucho 16 px; y el informe con el **ancho visible medido**, no el atributo `stroke-width`.

## Decisión del dueño: cómo se ve de Noche (M1)

El marino `#2C4A72` sobre el medallón `#1D2D44` da 1,54:1, así que de Noche las medallas se leen como contornos dorados con un hueco. Hay dos opciones:
- **(a)** aceptar la silueta solo con el filete, una vez corregido H1;
- **(b) recomendación del crítico:** invertir de Noche. Cuerpo marfil `#EDE6D8`, que es el `brand` de Noche; detalles en marino y filete en oro.

**El dueño eligió (b)** (28-nov). De Noche:
- **Cuerpo** en marfil `#EDE6D8`;
- **sombra y segundo plano** en `#C9BFAE`;
- **detalles interiores** en marino `#10345C`, de 8 px como mínimo;
- **filete y estrellas** en oro `#E3B65A`;
- **medallón** en `#1D2D44`.

La geometría es la misma que de Día; solo cambian los colores. Comprueba que el marino interior sobre el marfil supere 4,5:1 y que el cuerpo sobre el medallón supere 3:1.

## Entrega

- En la misma rama `ext/T-070`.
- Rehaz las cuatro láminas.
- Actualiza el REPORT con las medidas visibles.
- El crítico revisa otra vez solo las láminas.

---

# Revisión 2 (crítico, 28-nov · `ext/T-070` @ `4c91c97`)

**Veredicto: CAMBIOS, acotados.** El detalle está en [`T-070-CRITICA-2.md`](T-070-CRITICA-2.md).

Quedan resueltos H1, H2, H3, M2, M3 y M6, y la Noche invertida funciona. **Seis de las nueve están aprobadas:** libro, corazón, diario, lupa, vela y rosario de Noche.

Faltan cuatro cosas, y la ronda 3 solo mira esas:

1. **Paloma de Día (bloquea).** Es marfil sobre marfil (1,00:1), así que se lee como dibujo de línea.
   - El cuerpo pasa al grupo `navy`: marino de Día y `#EDE6D8` de Noche, como el resto.
   - El ala sigue en `warm`.
   - El ojo y la línea del ala, en un rol que sea marfil de Día y marino de Noche.
2. **Cruz de Noche.** El cuerpo `#EDE6D8` sobre el nimbo `#F8E8BC` da 1,02:1.
   - De Noche, el cuerpo en `#10345C` y la sombra en `#0C2848`.
   - Quita `details-cross`, que es un path muerto.
3. **«Sin conexión» de Noche.** La barra marfil sobre la nube marfil da 1,00:1.
   - De Noche, la barra en `#10345C` con filete oro.
   - Que sobresalga 16 px como máximo; hoy son unos 40.
4. **Rosario de Día.**
   - Baja unos 12 px las cuentas de los extremos: la superior izquierda queda a 4,5 px de una estrella.
   - El color de las cuentas lo decide Jose: oro limpio o `#B47C20`.

**Pulido opcional:**
- la vela de Día con el cuerpo en `warm`;
- quita las líneas marinas de las páginas del libro de Día, que no se ven.
