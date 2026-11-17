# T-036 · Índice completo del Catecismo

- **Asignado:** Hermes · **Rama:** `ext/T-036` (fork) · **Estado:** ABIERTO, cambios de la revisión aplicados.
- **Archivos creados:** `app/src/main/assets/catechism/structure.json`,
  `docs/tasks/tools/validate_catechism_structure.py`, `docs/tasks/T-036-REPORT.md`.
- No se tocó ningún otro archivo.

Solo **títulos**: no se copia ni un párrafo del Catecismo. La app ya tiene los 2865 párrafos en
`CatechismFullData*`.

## Contenido

`{"nodes": [...]}` con el árbol completo. **123 nodos**:

| Nivel | Cantidad |
|---|---|
| `PROLOGUE` | 1 |
| `PART` | 4 |
| `SECTION` | 8 |
| `CHAPTER` | 20 |
| `ARTICLE` | 67 |
| `PARAGRAPH` | 21 |
| `HEADING` | 2 |

## Cambios de la revisión (1) aplicados

1. **Artículos que faltaban** (existen en el libro; el índice PT no los listaba como artículos):
   - `p4s1c3a3` **La oración de la Hora de Jesús** (2746-2758); el artículo 2 queda en 2725-2745.
   - `p4s2a4` **La doxología final** (2855-2865); el artículo 3 queda en 2803-2854.
2. **Título EN de la sección 2 de la parte 1**: «The profession of the Christian faith» (el índice EN
   lo juntaba con el apartado siguiente). El apartado **«I. The Creeds»** (185-197) queda como nodo
   `HEADING` `p1s2h1`, con «Los Símbolos de la fe» en ES y «Os Símbolos da Fé» en PT.
3. **«Amén»** (1061-1065) como `HEADING` `p1s2c3h1`, hermano del artículo 12, que termina en 1060.
4. **Mayúsculas**: todos los títulos pasan a mayúscula inicial, conservando los nombres propios
   (Dios, Jesucristo, Espíritu Santo, Iglesia, Padre, Credo…). Las comillas latinas van sin espacios
   interiores («Creo en el Espíritu Santo»).
5. **Número del nodo fuera del título**: se quitó «Artículo 1:», «CAPÍTULO PRIMERO:», «Article 7»,
   «Artigo 1», etc. La app lo compone con `kind` y la posición.
6. **Erratas corregidas**: «AGAlN» → «again» y «DEL LA INICIACIÓN» → «de la iniciación».

Cada nodo lleva `id` jerárquico (`prologue`, `p1`, `p1s2`, `p1s2c3`, `p1s2c3a9`, `p1s2c3a9p4`),
`kind`, `title` en **es/en/pt** y `range` `[a, b]` inclusivo.

Los rangos se calcularon con las páginas de párrafos ya registradas en
`docs/content/catechism-provenance.json`: cada página del índice ES tiene su lista de párrafos y
el rango de un nodo es el mínimo y el máximo de su subárbol. Las partes quedan **26-1065,
1066-1690, 1691-2557 y 2558-2865**, y el prólogo **1-25**, así que el árbol cubre los 2865 párrafos
sin huecos.

## Fuentes (vatican.va, por idioma)

- **Español:** índice `https://www.vatican.va/archive/catechism_sp/index_sp.html` y las 109 páginas
  de estructura que cuelga de él (`p1s1c1_sp.html`, `p1s2c1p1_sp.html`, …). Los títulos se tomaron
  del texto del índice; los rangos, de los párrafos de cada página.
- **Inglés:** índice `https://www.vatican.va/archive/ENG0015/_INDEX.HTM` (línea a línea; los
  artículos y párrafos llevan el número en la misma línea).
- **Portugués:** índice `https://www.vatican.va/archive/cathechism_po/index_new/indice_po.html`.
  En el índice PT el número y el título salen en líneas separadas, y faltaban tres artículos; se
  completaron con las páginas de la propia edición:
  `p1s2cap2_422-682_po.html`, `p3s2cap1_2083-2195_po.html`, `p4s1cap1_2566-2649_po.html`,
  `p4s2_2759-2865_po.html` y `p3s1cap1_1699-1876_po.html`.

No se ha traducido nada: cada título está en la edición de su idioma.

## Decisiones

- **Niveles:** `PROLOGUE`, `PART`, `SECTION`, `CHAPTER`, `ARTICLE`, `PARAGRAPH`. No hay `HEADING`:
  los títulos intermedios que introduce el propio libro («Los Símbolos de la fe», «El Credo»,
  «¿Qué es la oración?», «Los Diez Mandamientos») no son estructura de capítulos, y los párrafos
  que ocupan (185-197, 2558-2565, 2052-2082, 1691-1698, 1066-1075…) quedan como **párrafos del padre
  antes del primer hijo**, que el formato permite. Si el coordinador quiere que sean nodos, se
  añaden como `HEADING` sin cambiar los rangos.
- **Las divisiones con número romano** («I. El deseo de Dios», «II. Las etapas de la Revelación»)
  quedan fuera: están por debajo del nivel de párrafo temático, que es hasta donde llega el
  índice pedido. Hay unas 270 en ES; si se quieren, se pueden añadir como `HEADING`.
- **`IN BRIEF` / `Resumen` / `EM SÍNTESE`** no son nodos: quedan dentro del rango de su artículo.
- **Erratas de la fuente corregidas solo en la clave:** el índice ES escribe «Párrafo l» y
  «Artículo l» donde el libro tiene «1»; el índice PT escribe «PARÁGRAFO I» y «ARTIGO l». Se
  normalizó a `1` para que el título sea legible.

## Divergencias entre ediciones (detectadas)

- **Sección 2 de la parte 1:** el índice EN la titula **«SECTION TWO I. THE CREEDS»**, mientras que
  ES dice «SEGUNDA SECCIÓN: LA PROFESIÓN DE LA FE CRISTIANA» y PT «SEGUNDA SECÇÃO A PROFISSÃO DA
  FÉ CRISTÃ». Comprobado en la propia página EN (`__P14.HTM`): el título de la edición inglesa es
  ese. Se respeta lo que dice cada edición.
- **Parte 4, sección 2 («Padre nuestro»):** el índice EN divide el último tramo en dos artículos
  («Article 3 THE SEVEN PETITIONS» y «Article 4 THE FINAL DOXOLOGY»); ES y PT tienen solo tres. El
  árbol sigue la estructura de ES/PT y el nodo EN correspondiente queda con el título «Article 3».
- **Parte 3, artículo 3 del capítulo 3:** PT lo titula «A Igreja. Mãe e educadora»; ES «La Iglesia,
  madre y maestra»; EN «THE CHURCH, MOTHER AND TEACHER».
- **Parte 4, sección 1, capítulo 3:** EN añade «Article 3 THE PRAYER OF THE HOUR OF JESUS», que ES y
  PT no tienen como artículo. Queda fuera del árbol.
- **Parte 1, artículo 5:** EN lo titula «HE DESCENDED INTO HELL. ON THE THIRD DAY HE ROSE AGAIN»,
  juntando lo que ES/PT tratan en el artículo 5 y su párrafo 2.

## Validación

```bash
$ python docs/tasks/tools/validate_catechism_structure.py \
      app/src/main/assets/catechism/structure.json
0 errores · 0 avisos
```

El script (dejado en `docs/tasks/tools/validate_catechism_structure.py`) comprueba: JSON válido;
claves de cada nodo; `kind` dentro de la lista; `id` único y `snake_case`; títulos es/en/pt no
vacíos; **ningún título con más de 3 palabras seguidas en mayúsculas**; **ningún título que empiece
por el número del nodo**; que cada hijo caiga dentro del rango de su padre; que los hermanos no se
solapen; que hay 4 partes con los rangos del libro; un prólogo con rango 1-25; que prólogo + partes
cubren 1..2865 sin huecos; y **los rangos fijos de los 13 artículos de la Parte 4**
(`ARTICULOS_P4`, fijados en la revisión).

## Lo que no se pudo verificar

- **La pantalla:** no existe todavía; el coordinador la hará y hará las capturas.
- **La correspondencia exacta de los «Párrafo N» con el texto de la app:** los rangos salen de las
  páginas de la procedencia, que es la misma fuente del corpus, pero no se ha comprobado nodo a
  nodo contra el contenido.
- No se ha compilado la app: no hay Android SDK en este equipo.

## Preguntas para el coordinador

1. ¿Quieres que los títulos intermedios («Los Símbolos de la fe», etc.) sean nodos `HEADING`, o
   los dejamos como párrafos iniciales del padre?
2. ¿Añado las divisiones numeradas con romano («I. El deseo de Dios»…) como `HEADING`, o sobran?
3. La sección 2 de la parte 1 tiene títulos distintos en EN y ES: ¿la dejo como dice cada edición o
   unifico a la del ES?
