# T-015 · Las 8 novenas pendientes — REPORT

- **Encargo:** T-015 (`docs/tasks/T-015.md`)
- **Rama:** `ext/T-015` (en el fork `joselanzd-hash/Catholic-Hermes`)
- **Cuerpos creados:** `app/src/main/assets/novenas/269.json` … `276.json` (8 novenas × 9 días)
- **Índice:** `docs/tasks/T-015-data/index-add.json` (8 entradas)
- **Procedencia:** `app/src/main/assets/novenas/provenance/new-d.json` (8 entradas, `status: pastoral_review`)
- **Validación:** `0 errores · 0 avisos` (8 cuerpos, 9 días ordenados, `prayer` no vacío en es/en/pt, ids iguales en `index-add.json` y `new-d.json`, cuerpos consecutivos 269–276)

No se tocó ningún otro archivo. `index.json` y `new-c.json` quedan intactos.

## Regla de texto aplicada

El idioma original se reproduce **fielmente** (transcripción: sin resumir, sin modernizar, conservando erratas, puntuación y mayúsculas de la fuente); los otros dos idiomas son **traducción fiel**. Así se declara en cada `origin` de la procedencia.

Comprobación hecha: para cada novena, cada bloque del idioma original se comparó automáticamente contra el texto extraído de la fuente, normalizando espacios y comillas; el resultado fue **0 fragmentos no hallados** en las 8 novenas. Las erratas se conservan (p. ej. `m ismo`, `ocasione`, `uestros`, `salvaros` corregido a `salvarnos` en 271, `aDios`, `beningna`, `osotros`, `consolidación`, `seta`, `nPor`).

## Tabla por novena

Fecha de consulta de todas las fuentes: **15 de noviembre de 2026**.

| # | URL | Qué parte reproduje | Omisiones declaradas | Aviso de derechos visto (literal) | Dudas |
|---|-----|--------------------|----------------------|-----------------------------------|-------|
| 269 | https://www.ewtn.com/es/oracion/novena-a-nuestra-senora-de-coromoto | Los 9 días completos: señal de la cruz, acto de contrición, oración inicial, «Se dice/Se responde», oración del día, «Aquí pedirá…», «Reza una Salve.», oración final y la rúbrica de la corona de doce avemarías. | Ninguna. `theme`, `scripture`, `meditation` e `intentionGuidance` van vacíos porque la fuente no los trae. | «© 2026 EWTN Inc. Todos los derechos reservados.» → **Derechos: EWTN Inc.** | — |
| 270 | https://www.conferenciaepiscopaldehonduras.com/novena-a-la-virgen | Oraciones iniciales, la cita bíblica, la reflexión y el **Mensaje del Papa Francisco para el año Santo 2025** de cada día (conservado tal cual), y las peticiones. | Las Letanías Lauretanas y el «Bajo tu amparo», que la página pone tras el día 9, se añadieron **al final de la oración del día 9**. No se incluye el pie de página ni los enlaces de contacto. | «©2026 Conferencia Episcopal de Honduras» → **Derechos: Conferencia Episcopal de Honduras.** | La errata de la fuente `nPor su docilidad` se conserva. El rótulo original es «Letanias Lauretanas» (sin tilde), también se conserva. |
| 271 | https://www.ewtn.com/es/oracion/novena-al-senor-de-los-milagros | Los 9 días completos: señal de la cruz, acto de contrición, oración preparatoria, la «Considera…» de cada día, oración a la Santísima Virgen, y la rúbrica final con la súplica «Aplaca Señor tu ira…» y el Gloria. | Ninguna. `theme`, `scripture`, `meditation` e `intentionGuidance` van vacíos. | «© 2026 EWTN Inc. Todos los derechos reservados.» → **Derechos: EWTN Inc.** | La fuente dice «para salvarnos» (la primera extracción arrojó «salvaros»; se corrigió al cotejar). |
| 272 | https://www.ewtn.com/es/oracion/novena-al-divino-nino-2022 | Palabra de Dios, reflexión, propósito, oración comunitaria, gozos (coro y estrofas) y oración final de cada día. | Ninguna. **No se mezcló** con el PDF alternativo. | «© 2026 EWTN Inc. Todos los derechos reservados.» → **Derechos: EWTN Inc.** | El PDF alternativo queda citado en `sources` como no utilizado. |
| 273 | https://www.devocionario.com/santos/pancracio_2.html | Las 9 reflexiones y, al final de cada día, las **oraciones a la Santísima Trinidad** completas (al Padre, al Hijo, al Espíritu Santo y oración final). | No se usan imágenes del sitio. El menú, los enlaces y la nota final («El autor de esta novena es Salvador Mujal, Pbro.…») no van en el cuerpo; se recogen en `origin`/`sources`. | **Sin línea de «Derechos»**: el sitio permite reproducir textos concretos sin fines comerciales citando la fuente, y la app es gratuita. Se cita «Fuente: Devocionario Católico, www.devocionario.com». | Se conservan las erratas (`Espírtu`, `como lo hacia`, `concededme a mi`). La frase «Concluir con la oraciones a la Santísima Trinidad.» se añadió como línea de cierre en cada día, tal como la pone la página. |
| 274 | https://bibliotecacatolica.com.br/blog/novenas/novena-a-santa-edwiges | **Original en portugués**: las 9 reflexiones y la «Oração para todos os dias» con su rúbrica («Reza-se: 1 Pai Nosso; 1 Ave-Maria e 1 Glória ao Pai.» y «Santa Edwiges, rogai por nós!»). ES/EN son traducción fiel. | No se incluyen la introducción general de la página ni los artículos relacionados del final. | «Todos direitos reservados à Biblioteca Católica» (más «@2026») → **Derechos: Biblioteca Católica.** | La página está publicada con fecha 07 de octubre de 2026; es una devoción popular para pedir auxilio en las deudas (declarado en la nota pastoral). |
| 275 | https://rosary.ipray.eu/es_novena_san_benito_de_palermo.htm | Acto de contrición, oración preparatoria, la **lectura de cada día** (relato hagiográfico) y las oraciones finales con la antífona. Es **San Benito de Palermo** (São Benedito), no Benito Abad. | Ninguna. Los relatos hagiográficos se conservan como afirmación del texto antiguo, no como hechos verificados. | **Sin aviso de derechos hallado** en la página → no lleva línea de «Derechos». | La fuente propone ayuno y mortificación solo como **descripción de la vida del santo**, no como práctica para quien reza; se declara en `pastoralNote`. La errata «La virgen María se lee aparece» se conserva. |
| 276 | https://www.josegregorio.org/el-hombre-de-dios/novena-y-oraciones | Oración para todos los días y la oración de cada uno de los 9 días, con la fórmula final «Aquí se pide la gracia que se desea. (Padre Nuestro y Ave María)». | **Omisión declarada:** las otras tres oraciones de la página (Oración Oficial de la Causa, Oración Diaria y Oración para la pronta Beatificación), la Entronización del Sagrado Corazón y los poemas no forman parte de la novena y no se incluyen. | Sin aviso de derechos sobre el contenido; solo avisos de librerías de Google («Copyright Google LLC», «Copyright 2024 Google, Inc»). | La página se carga con JavaScript; se transcribió desde el navegador. Se conservan los títulos «Siervo de Dios», «Venerable» y «Beato», tal como pide el encargo. |

## Notas pastorales

Todas las entradas quedan en `status: pastoral_review` con una `pastoralNote` breve y trilingüe. Las principales:
- Las novenas no garantizan favores; la intercesión depende enteramente de Cristo, único Mediador (CIC 970). Las peticiones de salud y trabajo son súplicas, no promesas.
- Las consideraciones que hablan de milagros (271, 275) se presentan como tradición de la devoción, no como promesas garantizadas; la oración acompaña, no sustituye, la atención médica.
- 274: la devoción de la patrona de los endeudados no sustituye el trabajo, la planificación ni el asesoramiento.
- 275: las prácticas ascéticas descritas son propias de la vida del santo y no se proponen a quien reza; la penitencia cristiana nunca debe dañar la salud.
- 276: la canonización (León XIV, 19-X-2025) queda anotada en `history`; las oraciones se reproducen con sus títulos históricos.

## Dudas para el coordinador

1. **270 (Suyapa):** las Letanías Lauretanas y el «Bajo tu amparo» aparecen en la página tras el día 9; las puse al final de la oración del día 9. Si prefiere que vayan en los 9 días o en otro campo, lo ajusto.
2. **276 (José Gregorio):** la página tiene tres oraciones adicionales fuera de la novena; las omití y lo declaré. Si quiere que entren en el cuerpo (p. ej. en el día 1), lo hago.
3. **273 (San Pancracio):** el texto de las oraciones trinitarias es idéntico en los 9 días; lo reproduzco en cada día siguiendo el criterio de las novenas 262–268.
4. **Repetición de oraciones comunes:** en las fuentes EWTN y ipray los bloques comunes (contrición, oración preparatoria/final) están una sola vez; los repito en cada día para ajustarme al contrato de campos, igual que en 262–268. Está declarado en cada `origin`.

## Entrega

Push a `fork/ext/T-015` en dos lotes: 269, 272, 273, 274 y después 270, 271, 275, 276 con el índice y la procedencia completos.
