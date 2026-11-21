# La app en horizontal · Resumen para decidir

**La idea.** Con el teléfono en horizontal sobra ancho y falta alto. Lo que hoy se apila (la imagen, el título y debajo el contenido) se pone **lado a lado**:
- a la izquierda, lo que te sitúa;
- a la derecha, lo que lees o rezas.

**Tus pulgares mandan:**
- con el **izquierdo**, las pestañas, las cuentas del Rosario y «Anterior»;
- con el **derecho**, «Siguiente».

Nada se esconde y la letra no se achica. Todo está pensado para la letra normal (100 %). Con la letra al 130 % solo garantizamos que nada se corte; puede haber menos columnas o más deslizamiento.

Las cuentas tienen en cuenta la barra de estado y la de gestos de un teléfono real. Los detalles están en `LANDSCAPE.md`.

**Antes:** capturas de cómo se ve hoy en horizontal en un teléfono de 891 × 411, en la carpeta `antes/`:
- [Rosario](antes/lumen_es_dest_rosary_root_0.jpg) · [Rosario en Sanctuary](antes/sanctuary_es_dest_rosary_root_0.jpg)
- [Capítulo de la Biblia](antes/lumen_es_bible_chapter_chapter_0.jpg) · [Día de novena](antes/lumen_es_novena_day_day_0.jpg)
- [Hoy](antes/lumen_es_dest_home_root_0.jpg) · [Hoy en Sanctuary](antes/sanctuary_es_dest_home_root_0.jpg) · [Orar](antes/lumen_es_dest_prayers_root_0.jpg) · [Santos](antes/lumen_es_dest_saints_root_0.jpg)
- [Ventana de bienvenida](antes/lumen_es_welcome_dialog_welcome_0.jpg) · [Opciones del Rosario](antes/sanctuary_es_rosary_options_options_sheet_0.jpg) · [Misterios](antes/sanctuary_es_rosary_mysteries_mysteries_sheet_0.jpg) · [Recordatorios](antes/lumen_es_reminders_sheet_notice_sheet_0.jpg)

---

## 1. Rosario

**Antes** ([captura](antes/lumen_es_dest_rosary_root_0.jpg)).
- La pintura y el misterio ocupan la mitad izquierda.
- **La oración no se ve: hay que deslizar para leerla.**
- La barra de pestañas sigue a la vista mientras rezas.

**Después.** Un folio de dos caras.
- A la izquierda, un tercio de la pantalla dice dónde estás.
- A la derecha está la oración entera.

```
┌─────────────────────────────┬────────────────────────────────────────┬───────────┐
│ ‹ [ Rosario | Coronilla ]   │  Avemaría                    3 de 10   │  [Guía]   │
│ ┊  Primer misterio gozoso   │                                        │  [ ··· ]  │
│ ●  La Anunciación           │  Dios te salve, María, llena eres de   │           │
│ ┊  ┌───────────────────┐    │  gracia; el Señor es contigo. Bendita  │ (Escuchar)│
│(◉) │ pintura           │    │  tú eres entre todas las mujeres, y    │           │
│ ┊  └───────────────────┘    │  bendito es el fruto de tu vientre,    │           │
│ ○  Fruto: la humildad       │  Jesús. Santa María, Madre de Dios,    │ ┌───────┐ │
│    Después · Gloria         │  ruega por nosotros, pecadores, ahora  │ │SIGUIEN│ │
│    ▬▬▬▬▬▬ Paso 12 de 85     │  y en la hora de nuestra muerte. Amén. │ │  TE   │ │
│ [⏮ Anterior]                │                                        │ └───────┘ │
└─────────────────────────────┴────────────────────────────────────────┴───────────┘
  las cuentas, en vertical:       la oración completa                   bajo el pulgar
  arrastra hacia arriba para                                            derecho
  avanzar, una cuenta cada vez
```

- **Sin deslizar se leen** el Padrenuestro, el Avemaría, el Gloria y «Oh Jesús mío», en los tres idiomas y en los dos tamaños de teléfono de referencia (medido con las letras de la app; un test lo confirmará).
- **Excepción:** en un teléfono pequeño y **con la guía de principiante activada**, el Padrenuestro necesita deslizar una o dos líneas.
- **Siempre se deslizan** (son largos): el Credo, la Salve y las letanías.
- Los botones no se mueven nunca.

---

## 2. Un capítulo de la Biblia (y, del mismo modo, oraciones, novenas y Catecismo)

**Antes** ([captura](antes/lumen_es_bible_chapter_chapter_0.jpg)).
- El texto va en una columna estrecha, de unas 38 letras por línea.
- Una barra flotante **tapa versículos**.

**Después, en un teléfono ancho:**

```
┌─────────────────────────────────────────────┬──────────────────────────────┐
│ ‹  Biblia Platense ▾                        │  ARCOS de referencias        │
│    San Juan · Capítulo 1                    │  (toca un versículo)         │
│  ¹ En el principio era el Verbo, y el Verbo │                              │
│  estaba junto a Dios, y el Verbo era Dios.  │  referencias del versículo   │
│  ² Él estaba en el principio junto a Dios…  │  elegido                     │
│  (unas 66 letras por línea)                 │ ──────────────────────────── │
│                                             │ [‹] Juan 1 ▾ [▶] [Aa] [Sig. ›]│
└─────────────────────────────────────────────┴──────────────────────────────┘
```

**En un teléfono más estrecho:**
- el texto ocupa toda la pantalla;
- los controles van en una barra fija al pie del texto, con «Siguiente ›» a la derecha;
- los arcos se abren al tocar un versículo.

**Siempre:** nada flota sobre el texto y «Siguiente» queda abajo a la derecha.

**Los demás lectores** (oración, día de novena, Noche, Catecismo, lección) tienen a la izquierda:
- la imagen y el título;
- una fila con la parte en la que estás («Evangelio ▾»), que abre el índice;
- en las novenas, «Día 3 de 9 ▾», que abre todos los días;
- abajo, «Escuchar» y «Aa».

A la derecha, el texto, con «‹ Anterior» y «Siguiente ›» en su pie.

---

## 3. Hoy

**Antes** ([captura](antes/lumen_es_dest_home_root_0.jpg)).
- La imagen y el título ocupan más de la mitad del alto.
- Sin deslizar solo se ven el tiempo litúrgico y una oración.

**Después** (con las 4 pestañas de hoy; «Mi camino» se sumará al integrarse):

```
┌────────┬────────────────────┬───────────────────────────┬───────────────────────────┐
│  Hoy   │ (imagen, en bruma) │ Oración del momento       │ Liturgia de hoy   Jn 1,1  │
│  Orar  │ Sábado, 21 de nov. │  Oraciones de la noche  › │ «En el principio era el   │
│  Leer  │ Hoy                │  Ángelus, mañana        › │  Verbo, y el Verbo…»      │
│Explorar│ ┌────────────────┐ │ Mis prácticas             │  Primera lectura        › │
│        │ │Tiempo Ordinario│ │  0 de 12 hoy              │ [■ Leer las lecturas ▸]   │
│        │ │ Presentación de│ │  [ Ver mis prácticas ]    │ Para ti hoy               │
│        │ │ la Virgen María│ │                           │  …                        │
│        │ └────────────────┘ │ ──────────── Más para hoy ─────────────────────────── › │
└────────┴────────────────────┴───────────────────────────┴───────────────────────────┘
```

- **En un teléfono grande** se ven sin deslizar el día, las dos oraciones del momento, tus prácticas y las lecturas con su botón.
- **En uno pequeño:** el día, las dos oraciones y el comienzo de las lecturas.

---

## Decisiones que quizá quieras vetar

1. **Pestañas ocultas mientras rezas o lees.** En el Rosario, la Biblia, los lectores y Confesión, la barra lateral de pestañas se oculta, como ya pasa con la de abajo en vertical. Se vuelve con «atrás».
2. **Rosario:**
   - «Anterior» a la izquierda y «Siguiente» a la derecha;
   - con la guía de principiante en un teléfono pequeño, el Padrenuestro desliza una o dos líneas;
   - el Credo, la Salve y las letanías siempre deslizan.
3. **Los arcos de la Biblia** son un panel fijo solo en teléfonos anchos. En los más estrechos se abren al tocar un versículo.
4. **Las ventanas que hoy suben desde abajo** (Apariencia, Fuente, Opciones del Rosario, Copia de seguridad…) entran por la derecha, con «Cerrar» (o «Listo» en Fuente).
5. **Pestañas con su nombre completo.** Solo con la letra al 130 % se acortan: «Mi camino» pasa a «Camino», como «Explorar» ya pasa a «Más».
6. **Confesión** ocupa toda la pantalla y muestra el examen y «Mi lista» uno al lado del otro. Tu lista sobrevive al girar el teléfono, pero sigue sin guardarse: se borra al salir, como ahora.
7. **En Hoy, «Ver mis prácticas» pasa a botón secundario**, también en vertical, para que el único botón destacado sea «Leer las lecturas».
8. **Las lecturas de la Misa** se abren como página de lectura desde «Leer», con líneas cómodas, en vez de leerse dentro de la pestaña.

**Sobre las imágenes:**
- Se ven en el panel izquierdo de cada sección y como miniatura en las fichas (santos, devociones).
- Se recortan a su marco y nunca tapan texto ni botones.
- La Búsqueda no lleva imagen.
- Con la letra al 130 %, Hoy pasa a una columna y los demás centros pierden una columna de tarjetas.

**Qué probarás en tu teléfono** en cada entrega:
- girar a mitad del Rosario y de un capítulo;
- la cámara a cada lado;
- escribir con el teclado abierto;
- un Rosario completo;
- la letra grande.
