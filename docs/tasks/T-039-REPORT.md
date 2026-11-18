# T-039 · Emblemas de las órdenes y congregaciones nuevas

- **Asignado:** Hermes · **Rama de entrega:** `media/order-emblems` (fork) · **Estado:** ABIERTO.
- **Lista:** `docs/tasks/T-039-data/orders.tsv` (60 órdenes de T-016).
- **Recuento:** **23 con emblema · 37 sin emblema** (la app sigue mostrando el monograma de reserva).

## Con emblema (23)

| id | Obra en Commons | Licencia |
|---|---|---|
| mercedarios-odem | `Emblem mercedarians.png` | Public domain |
| trinitarios-osst | `Emblem Trinitarian Order Rome.jpg` | Public domain |
| carmelitas-descalzos-ocd | `Fnv0251.jpg` | Public domain |
| tercera-orden-regular-tor | `ESCUDO TOR.png` | CC0 |
| teatinos-cr | `800px-Chiesa di san gaetanoStemma.JPG` | Public domain |
| hermanos-lasalle-fsc | `Signum Fidei.PNG` | CC BY-SA 3.0 |
| hospitalarios-san-juan-dios-oh | `Blason de l'ordre hospitalier de Saint Jean de Dieu.svg` | Public domain |
| oratorianos-co | `S. F. Nerist.JPG` | Public domain |
| vicentinos-cm | `CMPaul.png` | Public domain |
| palotinos-sac | `SulpiciensPSS.jpg` | Public domain |
| marianistas-sm | `Marist-stem.jpg` | Public domain |
| sacramentinos-sss | `SSS logo.jpg` | CC BY-SA 4.0 |
| maryknoll-mm | `Maryknoll Order Logo.gif` | Public domain |
| brigidinas-monasticas-osss | `Brigidine.gif` | Public domain |
| compania-santa-teresa-stj | `EscudoTeresianas.png` | Public domain |
| religiosas-maria-inmaculada-rmi | `RMI-escut.jpg` | Public domain |
| hermanitas-ancianos-desamparados-had | `HAD-escut.jpg` | Public domain |
| servitas-osm | `OSMariaEscut.gif` | Public domain |
| premonstratenses-opraem | `COA Premonstratensians.svg` | Public domain |
| agustinos-recoletos-oar | `Escudo-agustinos-recoletos.gif` | Public domain |
| javerianos-sx | `Xaverian Hawk.png` | CC BY-SA 4.0 |
| misioneros-sagrado-corazon-msc | `Brasão MSC.png` | Public domain |
| siervas-maria-enfermos-sdem | `Escudo color SM.jpg` | Public domain |

## Licencias

| Licencia | Cantidad |
|---|---|
| Public domain | 18 |
| CC BY-SA 4.0 | 3 |
| CC BY-SA 3.0 | 1 |
| CC0 | 1 |

**Ninguna NC ni ND**, ningún *non-free* ni *fair use*.

## Sin emblema (37) y por qué

No se encontró en Wikimedia Commons un emblema o escudo **de esa orden concreta** con licencia
libre. En la mayoría de los casos lo único que hay es el escudo de una provincia, de un colegio o
de la casa general (expresamente excluido por el encargo), o el escudo de un obispo del lugar, o una
foto de la fachada.

`minimos-om`, `franciscanos-conventuales-ofmconv`, `escolapios-schp`, `barnabitas-crsp`,
`somascos-crs`, `combonianos-mccj`, `misiones-extranjeras-pime`, `misiones-africanas-sma`,
`misioneros-africa-mafr`, `consolata-imc`, `legionarios-cristo-lc`, `montfortianos-smm`,
`dehonianos-scj`, `asuncionistas-aa`, `orionistas-fdp`, `rogacionistas-rcj`,
`sagrados-corazones-sscc`, `hermanos-cristianos-cfc`, `basilianos-san-josafat-osbm`,
`benedictinas-adoracion-perpetua-osb`, `dominicas-contemplativas-op`, `concepcionistas-oic`,
`carmelitas-caridad-vedruna-ccv`, `hermanitas-pobres-psdp`, `religiosas-sagrado-corazon-rscj`,
`hermanas-misericordia-rsm`, `notre-dame-namur-sndden`, `congregacion-jesus-cj`,
`san-jose-cluny-sjc`, `hermanas-hospitalarias-hsc`, `religiosas-asuncion-ra`, `buen-pastor-rgs`,
`siervas-espiritu-santo-ssps`, `hijas-san-pablo-fsp`, `carmelitas-misioneras-teresianas-cmt`,
`mercedarias-caridad-mc`, `hermanas-cruz-hc`.

Dos casos a revisar:

- `escolapios-schp`: sí hay escudo de la Orden de las Escuelas Pías
  (`COA Ordo Clericorum Regularium pauperum Matris Dei Scholarum Piarum1-3.jpg`, CC BY-SA 4.0), pero
  el archivo no se dejó descargar por la vía de caché usada. **Pendiente de reintentar.**
- `combonianos-mccj`: `Emblem of Combonian Missioners.jpg` (dominio público) tampoco se dejó bajar.
  **Pendiente de reintentar.**

## Fuentes

- **Wikimedia Commons** (API + `Special:FilePath`), vía la caché `wsrv.nl` / `images.weserv.nl`.
  `url` en `credits.json` apunta a la **página del archivo**.
- **Wikidata** (`P154` logo y `P94` escudo) para localizar los emblemas de las órdenes que no
  aparecen con las consultas de Commons: mercedarios, servitas, premonstratenses, agustinos
  recoletos, lasallistas, hospitalarios de San Juan de Dios y misioneros del Sagrado Corazón.
- Autor: solo se ha rellenado cuando consta en la ficha de Commons; **no se ha inventado ninguno**.
  En esta entrega la mayoría de los emblemas son obras heráldicas sin autor individual.

## Formato

- `orders/<id>.png` — PNG de **512 px** por lado máximo, con **fondo transparente** si el original lo
  tenía; si no, fondo blanco y anotado en `note`.
- Los SVG se rasterizaron a PNG mediante el renderizador de Wikimedia (`Special:FilePath…&output=png`),
  no con un conversor propio.
- `orders/credits.json` — mismo formato que
  `app/src/main/assets/devotions/images/credits.json`, con `referential: false` y `note` por
  archivo.

## Notas

- `trinitarios-osst`: la única obra libre y clara es la foto del **emblema de piedra** de la fachada
  de la casa general de Roma (dominio público). No es una foto de edificio: es el emblema mismo.
- `servitas-osm`, `maryknoll-mm`, `brigidinas-monasticas-osss`, `agustinos-recoletos-oar`: el origen
  es un GIF o un grabado sin transparencia; se entregan como PNG con fondo blanco y anotado.
- `teatinos-cr`: escudo teatino fotografiado en la iglesia de San Gaetano (Roma); es el escudo del
  instituto, no un escudo de OBispo.
- Ninguna rama femenina comparte emblema con la masculina en esta entrega: las que tienen emblema
  son propias (`compania-santa-teresa-stj`, `religiosas-maria-inmaculada-rmi`,
  `hermanitas-ancianos-desamparados-had`, `siervas-maria-enfermos-sdem`). `dominicas-contemplativas-op`
  quedó sin emblema, así que no ha hecho falta invocar la regla de la Orden de Predicadores.

## Pendiente

- Reintentar `escolapios-schp` y `combonianos-mccj`.
- Si el coordinador tiene alguna fuente propia con licencia libre para las 37 restantes, se añaden en
  la misma rama.
