# T-034 · Imágenes de los santos

- **Asignado:** Hermes · **Rama de entrega:** `media/saint-images` (fork) · **Estado:** ABIERTO.
- **Nota de estado:** el coordinador dijo en 17-nov que se empezara al terminar T-036. La tabla de
  `origin` seguía marcándolo `EN COLA`, así que se adelantó la primera entrega y **queda constancia de
  que se trabaja en él**, como pidió el usuario. No se ha tocado `CatholicGPT`.
- **Cobertura:** **las 215 fichas tienen imagen** (0 sin imagen).

## Recuento

| | Cantidad |
|---|---|
| Fichas totales (`saints.tsv`) | 215 |
| **Con imagen** | **215** |
| Sin imagen | 0 |
| Referenciales | 0 |
| Tamaño total de `saints/` | ~50 MB (215 JPG a ≤1200 px por lado) |

## Auditoría externa (17-nov, modelo de visión de Google)

Se generaron **32 hojas de contactos** (6 imágenes por hoja) y se subieron a la rama
`media/saint-images-hojas` para su revisión. Resultado: **127 SÍ · 35 NO · 26 DUDOSO**.

- Las **35 NO** eran claramente otra cosa: iglesias, calles, fachadas, criptas vacías, páginas o
  portadas de libro, escudos heráldicos, un busto relicario sin identificar, un guerrero nórdico, un
  escultor en su taller, un globo aerostático, estandartes procesionales… Se retiraron.
- Las **26 DUDOSO** no permitían reconocer al santo (vidrieras diminutas, frescos de techo alejados,
  negativos borrosos, bustos genéricos). También se retiraron: el encargo no admite una imagen que no
  represente al santo.

Sustitución: se verificaron **57 reemplazos** en Wikimedia Commons (título, descripción y categorías
del archivo) y se descargaron. **4 se descartaron** aunque el auditor los propusiera, porque tampoco
representan al santo:

| Descartado | Motivo |
|---|---|
| `all_saints` | el «Landauer Altar» de Durero es la *Adoración de la Trinidad*, no Todos los Santos |
| `our_lady_of_sorrows` | foto de la Iglesia de la Vera Cruz (Salamanca) |
| `matthew_apostle` | es el *ángel* de San Mateo, no Mateo |
| `simon_and_jude_apostles` | políptico de Ugolino di Nerio sin categorías que lo confirmen |

## Tercera ronda: los 31 que quedaban

Tras cerrar la segunda pasada, se buscó de nuevo para los 31 que no tenían imagen: cuatro rondas de
consultas en Commons (el nombre de la ficha, el nombre en inglés claro, «icon / painting / portrait»
y búsquedas dirigidas por nombre y obra). **Se consiguió imagen para los 31.**

| Santo | Obra | Licencia |
|---|---|---|
| Timoteo y Tito | `Saint Timothy.jpg` (icono) | Public domain |
| Pedro Damián | `Pierodamiani2.JPG` | Public domain |
| Anselmo de Canterbury | `Anselm-CanterburyVit.jpg` (vidriera) | Public domain |
| Nereo y Aquileo | `Rome, Chiesa di Santa Maria in Vallicella 010.JPG` (Rubens) | CC BY-SA 4.0 |
| Bernardino de Siena | `St. Bernardine of Siena Met DP890968.jpg` | CC0 |
| Cristóbal Magallanes | `Cristobal.jpg` | Public domain |
| Beda el Venerable | `Venerable Bede, Bramley.jpg` (vidriera) | CC BY 2.0 |
| María Magdalena de Pazzi | `Maria Maddalena de' Pazzi.jpg` | Public domain |
| Marcelino y Pedro | `Marcellinus Petrus.jpg` | Public domain |
| Primeros mártires de Roma | `Siemiradski Fackeln.jpg` (Siemiradzki) | Public domain |
| Agustín Zhao Rong | `Saint Augustine Tchao.png` | CC0 |
| Lorenzo de Brindisi | `Slorenzo5.jpg` | Public domain |
| Sarbelio Makhluf | `Charbel.jpg` | Public domain |
| Dedicación de Santa María Mayor | `Basilica di Santa Maria Maggiore abside a Roma.jpg` | CC BY-SA 3.0 |
| Sixto II y compañeros | `Pope St. Sixtus II -Mission San Miguel.png` | CC0 |
| Domingo de Guzmán | `Santo Domingo de Guzmán, Museo de Bellas Artes de Córdoba.jpg` | CC BY-SA 4.0 |
| Ponciano e Hipólito | `18-St.Pontian.jpg` | Public domain |
| Asunción de María | `Tizian 041.jpg` (la asmunsión de Tiziano) | Public domain |
| Virgen María de los Dolores | `Painting of Our Lady of Sorrows in Pleinfeld.jpg` | CC BY-SA 4.0 |
| Cornelio y Cipriano | `Paus Cornelius (tg-uact-408).jpg` | CC0 |
| San Mateo | `St. Matthew, Apostle Met DP891126.jpg` | CC0 |
| Cosme y Damián | `French - Saint Cosmas - Walters 27283.jpg` | Public domain |
| Lorenzo Ruiz | `San Lorenzo Ruiz Monument, Manila, Jun 2024.jpg` | CC BY-SA 4.0 |
| Virgen María del Rosario | `Matka Boża Pompejańska…` (Nuestra Señora del Rosario) | Public domain |
| Dionisio de París | `Enthauptung des Heiligen Dionysius auf dem Montmartre.jpg` | CC BY-SA 4.0 |
| Simón y Judas | `El Greco - St. Simon - Google Art Project.jpg` | Public domain |
| Todos los Santos | `Pala Ognissanti - Antonio Boselli.jpg` | CC BY-SA 4.0 |
| Fieles difuntos | `William-Adolphe Bouguereau… The Day of the Dead (1859)` | Public domain |
| Josafat Kuncewicz | `Simmler Martyrdom of Josaphat Kuntsevych.jpg` | Public domain |
| Columbano | `Columbanus at Bobbio.jpg` | CC BY-SA 3.0 |
| Santos Inocentes | `Daniele da Volterra - The Massacre of the Innocents…` | Public domain |

Dos salvedades que conviene que el coordinador conozca:

- `dedication_of_the_basilic_of_saint_mary_major` estaba creado con una errata en la clave
  («basilic»); se renombró a la clave del TSV (`…basilica…`) y se actualizó `credits.json`.
- Varias de estas 31 vienen de vidrieras, monumentos o retruques de cuadros; son la obra que
  representa al santo, no una iglesia ni una calle. Se han preferido a dejar la ficha vacía.

## Licencias

| Licencia | Cantidad |
|---|---|
| Public domain | 137 |
| CC0 | 33 |
| CC BY-SA 4.0 | 25 |
| CC BY-SA 3.0 | 9 |
| CC BY 4.0 | 7 |
| CC BY 2.0 / CC BY-SA 2.0 / CC BY 3.0 | 1 cada una |

Todas permitidas (dominio público, CC0, CC BY, CC BY-SA). **Ninguna NC ni ND.** Solo una entrada
tiene el autor vacío (la ficha de Commons no lo tiene legible).

## Fuentes

- **Wikimedia Commons** (`commons.wikimedia.org`), vía su API. Cada imagen se descargó por el título
  exacto del archivo y su `url` en `credits.json` apunta a la **página del archivo**, no a la imagen.
- Autor y licencia se tomaron de `extmetadata` (`Artist`, `LicenseShortName`); solo una entrada tiene
  el autor vacío (no consta en la ficha). No se ha inventado ningún autor.
- La tercera ronda (los 31 restantes) se hizo entera por la API de Commons y se descargó igual que
  las sustituciones: miniaturas de 1280 px vía `images.weserv.nl`, reducidas después a 1200 px.
- El grueso de las descargas se hizo de `upload.wikimedia.org`. Wikimedia cortó el acceso a los
  originales por exceso de peticiones (HTTP 429 con `retry-after: 120`), así que las sustituciones de
  la auditoría se bajaron como **miniaturas de 1280 px** a través de la caché `images.weserv.nl` y
  después se redujeron a un máximo de 1200 px por lado.
- `our_lady_of_lourdes` reutiliza la imagen de la devoción ya integrada en la app
  (`app/src/main/assets/devotions/images/lourdes.jpg`, Fernando Martello, CC BY-SA 4.0), anotado en
  `note`.

## Criterio de selección

1. **Obra del santo concreto**: cuadro, icono, vitral, escultura, grabado o fotografía histórica que
   lo represente; nunca una iglesia, una calle ni una procesión. Se descartaron sistemáticamente los
   PDFs, los vídeos, las fachadas y las páginas de libros.
2. **Antiguo y de dominio público** cuando existe; para santos modernos (Don Bosco, Josefa Bakhita,
   Teresa de Calcuta, Edith Stein) una fotografía histórica o un retrato.
3. **Sin imagen si no hay obra fiable**, como manda el encargo.

## Verificación

- **Primera pasada** (32 hojas, 188 imágenes): hecha por el modelo de visión de Google sobre las hojas
  publicadas en `media/saint-images-hojas`. Resultado: 127 SÍ · 35 NO · 26 DUDOSO.
- **Segunda pasada** (31 hojas, 184 imágenes): misma auditoría sobre las hojas regeneradas tras la
  sustitución. Resultado: **182 SÍ · 0 NO · 2 DUDOSO**.
  - `martha_of_bethany_mary_of_bethany_and_lazarus_of_bethany`: se dejó el Vermeer completo
    (*Cristo en casa de Marta y María*, dominio público): se ve a Cristo, a María a sus pies y a Marta
    con la cesta de pan. El fichero «(cropped2)» era un recorte de este mismo cuadro (el primer modelo
    de visión lo confundió con *La lechera*), y el primer auditor la dio por dudosa; el cuadro entero
    es la mejor opción.
  - `hilary_of_poitiers_bishop`: el auditor describió una «miniatura de consagración genérica», pero
    esa descripción era de `Hilaryofpoitiers.jpg`; el icono `Hilary of Poitiers.png` que había en su
    lugar **sí es San Hilario** (inscrito «saint hilaire de Poitiers»). Tras verlo, se recupera el
    icono (CC0, 847×1000), mejor que la miniatura de 500×285.
- Las sustituciones se contrastaron además una a una contra la ficha de Commons (título, descripción y
  categorías).

## Formatos

- `saints/<clave>.<jpg>` — la clave exacta de `docs/tasks/T-034-data/saints.tsv`, imagen original
  redimensionada a un máximo de 1200 px por lado (la app la muestra a 236 dp; el coordinador vuelve a
  redimensionar).
- `saints/credits.json` — mismo formato que
  `app/src/main/assets/devotions/images/credits.json`.

## Pendiente

- Pasada visual de las 31 imágenes de la tercera ronda (no se han revisado de visu; la selección se
  hizo por título, descripción y categorías de Commons, y se descartaron las que eran iglesias,
  calles, monumentos genéricos o páginas de libro).
- Verificar que el coordinador apruebe las dos ramas (`media/saint-images` y
  `media/saint-images-hojas`, la segunda es solo de verificación y no se integra).
