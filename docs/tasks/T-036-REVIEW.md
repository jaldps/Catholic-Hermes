# T-036 · Revisión del coordinador (1)

- **Entrega:** `ext/T-036` (fork) @ `df8797a`
- **Estado:** **CAMBIOS PEDIDOS**. No se integra todavía.

Los rangos de las partes, las secciones, los capítulos y la mayoría de los artículos son correctos. El validador da 0 errores. Pero hay errores de estructura y de títulos que el validador no detecta. Corrígelos en la misma rama.

## 1. Artículos que faltan (sí existen en ES y PT)

| Lugar | Qué falta | Párrafos |
|---|---|---|
| Parte 4, sección 1, capítulo 3 | **Artículo 3: «La oración de la Hora de Jesús»** («The Prayer of the Hour of Jesus»). El artículo 2, «El combate de la oración», termina en 2745, no en 2758. | 2746–2758, incluido «En síntesis» |
| Parte 4, sección 2 | **Artículo 4: «La doxología final»** («The Final Doxology»). El artículo 3, «Las siete peticiones», termina en 2854. | 2855–2865, incluido «En síntesis» |

El REPORT dice que ES y PT no tienen estos artículos. Vuelve a mirar los índices de vatican.va en ES y PT: los tienen. **Sigue el libro, no una sola edición.**

## 2. Título equivocado en EN

- Parte 1, sección 2: «SECTION TWO I. THE CREEDS» junta el título de la sección con el de su primer apartado.
  - El título de la sección es **«Section Two: The Profession of the Christian Faith»**.
  - **«I. The Creeds»** (185–197) es un apartado introductorio. Ponlo como nodo `HEADING` [185, 197], con sus títulos en ES («Los Símbolos de la fe») y en PT, según cada edición.
- Del mismo modo, el **«Amén»** final del Credo (1061–1065) es un apartado propio. Ponlo como `HEADING` [1061, 1065]; el artículo 12 termina en 1060.

## 3. Mayúsculas y erratas

- **Los títulos vienen en MAYÚSCULAS sostenidas** («PRIMERA PARTE LA PROFESIÓN DE LA FE», «Article 1 THE REVELATION OF GOD») y mezclados con otros en minúscula. La app los mostrará tal cual, así que pásalos todos a mayúscula inicial según la norma de cada idioma, conservando los nombres propios: Dios, Jesucristo, Espíritu Santo, Iglesia, Padre, Credo…
  - En ES y PT, las citas del Credo van entre comillas latinas, sin espacios interiores: «Creo en el Espíritu Santo».
- **Quita del título el número del nodo** («Artículo 1:», «CAPÍTULO PRIMERO:», «Article 7»). La app lo compone a partir de `kind` y de la posición. Si quieres conservarlo, añade un campo `label` aparte (por ejemplo, `{"es": "Artículo 1", …}`).
- **Erratas:** «AGAlN» (con l minúscula) pasa a «again», y «DEL LA INICIACIÓN» pasa a «de la iniciación». Si la errata está en la fuente, corrígela igualmente y anótalo en el REPORT.

## 4. Validador

Añade estas comprobaciones:
- ningún título con más de 3 palabras seguidas en mayúsculas;
- ningún título que empiece por «Artículo / Article / Capítulo / Chapter / Sección / Section / Parte / Part / Secção / Artigo / Capítulo» seguido de número u ordinal;
- un control fijo con los rangos de los artículos de la Parte 4.

---

# T-036 · Revisión del coordinador (2)

- **Entrega:** `ext/T-036` (fork) @ `e0c1932`
- **Estado:** **APROBADO · CERRADO**, integrado en `CatholicGPT`

Todos los puntos de la revisión 1 están resueltos:
- los dos artículos de la Cuarta Parte;
- el título EN de la Sección Segunda;
- los apartados «Los Símbolos de la fe» y «Amén»;
- la mayúscula inicial y los títulos sin número;
- las erratas;
- el validador ampliado.

El árbol tiene 123 nodos.

**Ajustes del coordinador:**
- Al pasar los títulos a minúscula se perdieron algunos nombres propios. Quedan restituidos:
  - María / Mary / Maria;
  - Poncio Pilato / Pontius Pilate / Pôncio Pilatos;
  - Israel;
  - Espíritu / Spirit / Espírito, en la vida en el Espíritu;
  - Evangelio / Gospel / Evangelho;
  - Holy Orders.
- Otros retoques:
  - «hell. on the third day» pasa a «hell; on the third day»;
  - «A Igreja. mãe e educadora» pasa a «A Igreja, mãe e educadora»;
  - en EN, «I. The Creeds» pasa a «The Creeds», igual que en ES y PT;
  - «The Lord's prayer» pasa a «The Lord's Prayer: "Our Father"».
