# La app en horizontal · Resumen para decidir

**La idea en una frase.** Con el teléfono en horizontal sobra ancho y falta alto. Por eso, lo que hoy se apila de arriba abajo (la imagen, el título y luego el contenido) se pone **lado a lado**: a la izquierda, lo que te sitúa; a la derecha, lo que lees o usas.

**Tus pulgares mandan.**
- Con el izquierdo: las pestañas y las cuentas del Rosario.
- Con el derecho: «Siguiente».
- Nada se esconde y la letra nunca se achica.

Antes de diseñar, miré la app tal como se ve hoy en horizontal: las 4 apariencias y unas 115 pantallas cada una, en un teléfono de 891 × 411. Lo de «antes» viene de esas capturas. La especificación completa está en `LANDSCAPE.md`.

---

## 1. Rosario

**Antes.**
- La pintura y el nombre del misterio ocupan la mitad izquierda.
- A la derecha, el progreso y las cuentas en una tira horizontal.
- **La oración no se ve: hay que deslizar para leerla.**
- La barra de pestañas sigue a la vista, aunque estés rezando.

**Después.**

```
┌──────────────────────────────┬──────────────────────────────────────────┐
│ ‹   [ Rosario | Coronilla ]  │  Avemaría                       3 de 10  │
│ ┊  ┌──────────────────────┐  │  Dios te salve, María, llena eres de     │
│ ●  │  pintura del misterio│  │  gracia; el Señor es contigo; bendita    │
│ ┊  └──────────────────────┘  │  tú eres entre todas las mujeres…        │
│(◉) Misterios Gozosos ▾       │  Santa María, Madre de Dios, ruega por   │
│ ┊  1.º La Anunciación        │  nosotros, pecadores… Amén.              │
│ ○  Fruto: la humildad        │  Después · Gloria                        │
│ ┊  ▬▬▬▬▬▬▬ Paso 12 de 85     │ [Guía] [···]   [Atrás] [Oír] [SIGUIENTE] │
└──────────────────────────────┴──────────────────────────────────────────┘
  cuentas en vertical (pulgar      oración completa, sin deslizar
  izquierdo: tocar o arrastrar)    «Siguiente» bajo el pulgar derecho
```

---

## 2. Un capítulo de la Biblia (y, del mismo modo, oraciones, novenas y Catecismo)

**Antes.**
- El texto va en una columna estrecha de unas 38 letras por línea, más estrecha que el panel de los arcos.
- Una barra flotante (‹ San Juan 1 ▶ Aa ›) **tapa versículos**.

**Después.**

```
┌──────────────────────────────────────────────┬──────────────────────────┐
│ ‹  Biblia Platense ▾                         │  ARCOS de referencias    │
│    San Juan · Capítulo 1                     │  (toca un versículo)     │
│  ¹ En el principio era el Verbo, y el Verbo  │                          │
│  estaba junto a Dios, y el Verbo era Dios.   │  Referencias del         │
│  ² Él estaba en el principio junto a Dios…   │  versículo elegido       │
│  (líneas de unas 60–70 letras, cómodas)      │ ───────────────────────  │
│                                              │ [‹] Juan 1 ▾ [▶] [Aa] [›]│
└──────────────────────────────────────────────┴──────────────────────────┘
```

- Nada flota sobre el texto. Los controles van abajo a la derecha, con «capítulo siguiente» en el borde.
- En los demás lectores (oración, día de novena, Noche, Catecismo, lección), la izquierda lleva:
  - la imagen y el título;
  - el índice de partes, que señala por dónde vas;
  - y abajo, «Escuchar» y «Aa».

---

## 3. Hoy

**Antes.**
- La imagen y el título «Hoy» ocupan más de la mitad del alto.
- Sin deslizar solo se ven el tiempo litúrgico y una oración.
- La liturgia del día y tus prácticas quedan escondidas abajo.

**Después.**

```
┌────┬─────────────────────┬──────────────────────────┬──────────────────────────┐
│Hoy │ (imagen, en bruma)  │ Oración del momento      │ Liturgia de hoy  Jn 1,1  │
│Orar│ Sábado, 21 de nov.  │  Oraciones de la noche › │ «En el principio era el  │
│Leer│ Hoy                 │  Ángelus, mañana       › │  Verbo, y el Verbo…»     │
│Cami│ ┌─────────────────┐ │ Mis prácticas            │  Primera lectura       › │
│Expl│ │ Tiempo Ordinario│ │  0 de 12 hoy             │ [ Leer las lecturas ▸ ]  │
│    │ │ Presentación de │ │ [ Ver mis prácticas ▸ ]  │ Para ti hoy              │
│    │ │ la Virgen María │ │                          │  …                       │
│    │ └─────────────────┘ │ ─────── Más para hoy ──────────────────────────── › │
└────┴─────────────────────┴──────────────────────────┴──────────────────────────┘
```

Lo esencial del día se ve de un vistazo, sin deslizar.

---

## Decisiones que quizá quieras vetar

1. **Pestañas ocultas mientras rezas o lees.** En el Rosario, la Biblia, las oraciones, las novenas y el Catecismo, la barra lateral de pestañas se oculta, como ya se oculta la de abajo en vertical. Se vuelve con «atrás». Así gana anchura el texto.
2. **Oraciones largas en el Rosario.** El Padrenuestro, el Avemaría, el Gloria y «Oh Jesús mío» se leen sin deslizar. El Credo, la Salve y las letanías, en un teléfono pequeño o con la guía activada, pueden necesitar deslizar un poco. Los botones no se mueven.
3. **Los arcos de la Biblia** son un panel fijo solo en teléfonos anchos. En los más estrechos se abren al tocar un versículo, para que el texto tenga líneas cómodas.
4. **Las ventanas que hoy suben desde abajo** (Apariencia, Fuente, Opciones del Rosario, Copia de seguridad…) entrarán por la derecha, con un botón «Cerrar».
5. **Nombres cortos en las pestañas solo con letra grande.** «Mi camino» se leerá «Camino», igual que «Explorar» ya pasa a «Más». Con letra normal, el nombre completo.
6. **Confesión** muestra el examen y «Mi lista» uno al lado del otro. Tu lista y tus notas **sobrevivirán al girar el teléfono**, aunque siguen sin guardarse nunca: se borran al salir, como ahora.
7. **Con letra al 130 %**, «Hoy» y los centros pasan a una sola columna a la derecha, sin achicar nada.
8. **Las imágenes** se recortan a un panel de un tercio de la pantalla. Siempre se ven, nunca tapan texto ni botones.

**Qué probarás en tu teléfono** en cada entrega:
- girar a mitad del Rosario y de un capítulo;
- la cámara a cada lado;
- escribir con el teclado abierto;
- un Rosario completo;
- la letra grande.
