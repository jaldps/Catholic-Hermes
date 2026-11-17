# T-034 · Imágenes de los santos

- **Asignado:** Hermes · **Rama de entrega:** `media/saint-images` (fork) · **Estado:** ABIERTO.
- **Nota de estado:** el coordinador dijo en 17-nov que se empezara al terminar T-036. La tabla de
  `origin` seguía marcándolo `EN COLA`, así que se adelantó la primera entrega y **queda constancia de
  que se trabaja en él**, como pidió el usuario. No se ha tocado `CatholicGPT`.
- **Cobertura:** 184 de las 215 fichas tienen imagen. Quedan 31 sin imagen (abajo).

## Recuento

| | Cantidad |
|---|---|
| Fichas totales (`saints.tsv`) | 215 |
| **Con imagen** | **184** |
| Sin imagen | 31 |
| Referenciales | 0 |
| Tamaño total de `saints/` | ~42 MB (184 JPG a ≤1200 px por lado) |

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

## Las 31 sin imagen

Sin obra fiable **del santo concreto** en Commons, o con un candidato que era otra cosa:

`timothy_of_ephesus_and_titus_of_crete_bishops`, `peter_damian_bishop`,
`anselm_of_canterbury_bishop`, `nereus_of_terracina_and_achilleus_of_terracina_martyrs`,
`bernardine_of_siena_priest`, `christopher_magallanes_priest_and_companions_martyrs`,
`bede_the_venerable_priest`, `mary_magdalene_de_pazzi_virgin`,
`marcellinus_of_rome_and_peter_the_exorcist_martyrs`, `first_martyrs_of_the_holy_roman_church`,
`augustine_zhao_rong_priest_and_companions_martyrs`, `lawrence_of_brindisi_priest`,
`sharbel_makhluf_priest`, `dedication_of_the_basilic_of_saint_mary_major`,
`sixtus_ii_pope_and_companions_martyrs`, `dominic_de_guzman_priest`,
`pontian_i_pope_and_hippolytus_of_rome_priest`, `assumption_of_the_blessed_virgin_mary`,
`our_lady_of_sorrows`, `cornelius_i_pope_and_cyprian_of_carthage_bishop_martyrs`,
`matthew_apostle`, `cosmas_of_cilicia_and_damian_of_cilicia_martyrs`,
`lawrence_ruiz_and_companions_martyrs`, `our_lady_of_the_rosary`,
`denis_of_paris_bishop_and_companions_martyrs`, `simon_and_jude_apostles`, `all_saints`,
`commemoration_of_all_the_faithful_departed`, `josaphat_kuntsevych_bishop`,
`columban_of_luxeuil_abbot`, `holy_innocents_martyrs`.

## Licencias

| Licencia | Cantidad |
|---|---|
| Public domain | 120 |
| CC0 | 28 |
| CC BY-SA 4.0 | 19 |
| CC BY 4.0 | 7 |
| CC BY-SA 3.0 | 7 |
| CC BY 2.0 / CC BY-SA 2.0 / CC BY 3.0 | 1 cada una |

Todas permitidas (dominio público, CC0, CC BY, CC BY-SA). **Ninguna NC ni ND.**

## Fuentes

- **Wikimedia Commons** (`commons.wikimedia.org`), vía su API. Cada imagen se descargó por el título
  exacto del archivo y su `url` en `credits.json` apunta a la **página del archivo**, no a la imagen.
- Autor y licencia se tomaron de `extmetadata` (`Artist`, `LicenseShortName`); solo una entrada tiene
  el autor vacío (no consta en la ficha). No se ha inventado ningún autor.
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

- Buscar obra para los 31 que se han quedado sin imagen (de los que 27 ya se buscaron en tres rondas).
- Verificar que el coordinador apruebe las dos ramas (`media/saint-images` y
  `media/saint-images-hojas`, la segunda es solo de verificación y no se integra).
