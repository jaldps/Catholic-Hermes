# T-044 · Imágenes de las 25 devociones marianas — INFORME

**Rama de entrega:** `media/devotion-images-2` en el fork
**Origen:** `docs/tasks/T-044-data/devotions.tsv` (25 devociones, id + nombre + lugar)
**Estado:** EN CURSO — **no dar por terminado**

## Reglas aplicadas

1. **La imagen tiene que ser de esa advocación concreta.** No valen fachadas, procesiones lejanas,
   ni una Virgen genérica.
2. **Commons primero.** Solo dominio público, CC0, CC BY y CC BY-SA. Nada de NC, ND, *non-free* ni
   *fair use*.
3. **Verificación por descripción y categorías, no por el nombre del archivo.** Es la regla que
   marcó el coordinador y la que más errores ha evitado: el puntaje automático por solapamiento de
   tokens colaba la catedral, un hospital sevillano llamado «Virgen del Rocío» y una multitud en
   Madrid celebrando Urkupiña.
4. **Si no hay imagen libre y fiable, se deja sin imagen.** Nada de relleno ni de imagen generada.
5. **No se declara una imagen sin mirarla.** La licencia y los metadatos sí están verificados; la
   inspección visual está pendiente por el cupo (ver abajo).

## Cobertura

| | |
|---|---|
| Devociones en el TSV | 25 |
| Con imagen descargada | **17** |
| Sin imagen (a día de hoy) | 8 |

### Con imagen (17)

| id | Archivo de Commons | Licencia | Nota |
|---|---|---|---|
| `virgen_urkupina` | `Virgen de Urkupiña.jpg` | CC BY-SA 4.0 | Iglesia de San Ildefonso, Quillacollo: el santuario |
| `virgen_socavon` | `Virgen socavon candelaria.jpg` | Dominio público | Pintura del s. XVIII; la advocación del Socavón es la Candelaria |
| `virgen_zapopan` | `Catedral de la Asunción de María Santísima (Guadalajara) - statue from the altar of Our Lady of Zapopan.jpg` | CC BY-SA 4.0 | **REFERENCIAL**: está en la catedral de Guadalajara, no en la basílica de Zapopan |
| `virgen_itati` | `Virgen itati 1912.jpg` | Dominio público | Escultura de 1912, Corrientes |
| `virgen_rocio` | `Virgen del Rocío de porcelana.JPG` | CC BY-SA 4.0 | Porcelana de Lladró (1942) del tesoro de la hermandad matriz de Almonte |
| `virgen_covadonga` | `Virgen de Covadonga2.jpg` | CC BY-SA 4.0 | La Virgen en la gruta |
| `nuestra_senora_knock` | `Basilica of Our Lady, Queen of Ireland (Interior)02.jpg` | CC0 | **Mosaico** de la aparición de 1879: el santuario no tiene imagen antigua |
| `virgen_banneux` | `Banneux Maagd der armen.jpg` | CC BY-SA 3.0 | Estatua y fuente de la Virgen de los Pobres |
| `virgen_la_salette` | `Eglise de Corps-statue-82.JPG` | CC BY-SA 3.0 | Estatua en la iglesia de Corps (Isère), en la comarca de la aparición |
| `virgen_czestochowa` | `Czestochowa MB Czestochowska.jpg` | CC BY-SA 4.0 | El icono de Jasna Góra |
| `nuestra_senora_vailankanni` | `Mother Mary Vailankanni.jpg` | CC BY-SA 4.0 | Estatua de Ntra. Sra. de la Salud |
| `virgen_suyapa` | `Virgen de Suyapa.jpg` | CC BY-SA 4.0 | La imagen en su basílica de Tegucigalpa |
| `virgen_desamparados_valencia` | `Nuestra Señora de los Desamparados (gravat).jpg` | CC BY-SA 4.0 | Grabado xilográfico del s. XVIII de la Mare de Déu dels Desemparats |
| `madre_buen_consejo_genazzano` | `Horb Stiftskirche Gemälde Maria vom Guten Rat von Genazzano.jpg` | CC BY 3.0 | **REFERENCIAL**: copia del Gnadenbild (c. 1750) en Horb; no el original de Genazzano |
| `nuestra_senora_pontmain` | `Pontmain (53) Basilique Vitrail 01.jpg` | CC0 | Vitral de ábside de la basílica Ntra. Sra. de la Esperanza: representa la aparición |
| `nossa_senhora_nazare_belem` | `Imagem de Nossa Senhora de Nazaré em Paragominas-Pará.jpg` | CC BY 4.0 | **REFERENCIAL**: estatua del Cirio en Paragominas, no la de la basílica de Belém |
| `maria_desatadora_nudos` | `03178ajfStatues of Mary Untier of Knots in the Philippinesfvf.jpg` | CC0 | **REFERENCIAL**: estatua en Filipinas; el original es el cuadro de Augsburgo |

### Sin imagen (8)

| id | Motivo |
|---|---|
| `virgen_del_quinche` | El candidato era una copia en Quisapincha (Ambato); elegible como **referencial**, pendiente de decisión. No descargada. |
| `virgen_montserrat` | Los candidatos eran la Moreneta (baja resolución o con velos) o retablos; la descarga falló con 429. |
| `virgen_loreto` | `Our Lady of Loreto.jpg` no descargada (429). |
| `virgen_walsingham` | Icono contemporáneo de Olga Shalamova; no descargada (404 en la vía alternativa). |
| `virgen_beauraing` | La búsqueda devuelve multitudes y peregrinos (Agence Rol); la estatua del «Corazón de Oro» no localizada. |
| `nossa_senhora_nazare_portugal` | La búsqueda devuelve el núcleo de Nazaré y el «veado surfista»; la imagen venerada no localizada. |
| `maria_auxiliadora` | Solo fachadas, capillas y personas; no una imagen de la Virgen. |
| `virgen_paz_el_salvador` | Solo iglesias tituladas «Our Lady Queen of Peace» en otros países; la de San Miguel no localizada. |

## Bloqueos declarados

- **HTTP 429 de Wikimedia** (`retry-after: 600`) en *todas* las rutas de descarga: original,
  `Special:FilePath` y `upload.wikimedia.org`. La vía alternativa `images.weserv.nl` responde
  **404** para varios de estos títulos. Hay una redescarga programada al vencer el corte.
- **Cupo de visión agotado**, y **no es de mi clave**: el modelo de análisis responde
  *«temporarily at capacity upstream»*. Por tanto **las 13 imágenes descargadas NO están
  verificadas visualmente**. Las hojas de contactos (`hojas/h1.png`…) y el `PROMPT.md` con las
  reglas SÍ/NO/DUDOSO están listos para traspaso, pero no se han usado.
- Lo que **sí** está verificado de cada imagen elegida: licencia, autor, descripción y categorías
  de Commons, contrastados contra el nombre de la devoción.

## Próximos pasos

1. Reintentar la descarga al vencer el corte de Wikimedia.
2. Verificar las 13 descargadas y las que se añadan, con las hojas de contacto.
3. Decidir las 12 sin imagen: dejarlas vacías o admitir las referenciales.
4. Revisar la paridad ficheros ↔ `credits.json`.
