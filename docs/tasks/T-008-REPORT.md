# T-008 · Informe de Codex

## Entrega parcial 2

Dos novenas recuperadas: **Pilar y San Ramón Nonato, 18 días ES/EN/PT** (`262.json` y `263.json`), con índice de integración y procedencia. Primera entrega parcial: `e75e65e`. Las otras trece siguen en investigación; esta entrega no las certifica ni restituye los cuerpos anteriormente redactados por agentes.

## Pilar: fuente, alcance y cotejo

| Fuente exacta, con URL | Tipo de fuente | Reserva expresa | Resultado |
|---|---|---|---|
| *Devota novena di Nostra Signora del Pilar di Saragozza*, versión del español de S. A. R. D. M. A. B., con noticia histórica de P. Fr. Diego dello Spirito Santo; Nápoles, Stabilimento tipografico di G. Nobile, **1855**, pp. 19–52. [Catálogo y facsímil](https://archive.org/details/bub_gb_VN7wZpxiFH0C); [PDF](https://archive.org/download/bub_gb_VN7wZpxiFH0C/bub_gb_VN7wZpxiFH0C.pdf). | Devocionario antiguo, texto italiano y latín de dominio público. | No se encontró reserva vigente en el impreso antiguo. No se atribuye una reserva por el solo hecho de estar digitalizado. | Nueve consideraciones, oración al Señor y tres peticiones marianas por día, obsequios, apertura y conclusión auténticas. Traducciones propias y fieles de IT/LA a ES/EN/PT. Marcado `pastoral_review` por las prácticas corporales históricas; véase nota al coordinador. |

- Se leyó visualmente la portada para confirmar **1855**: el OCR la confundía con 1853. También se cotejaron **todas las páginas 19–52**, incluidas las nueve jornadas y la letanía. No se completa ninguna frase dudosa de memoria.
- El aviso de pp. 5–6 identifica a la traductora como María Amalia de Borbón y explica la división de una oración en tres. Se describe esta **edición italiana concreta**: no se pretende que la retraducción española sea transcripción del original español no localizado. Los tres idiomas son traducciones fieles de los textos efectivamente consultados.
- `meditation` reproduce únicamente el «Si considera…» de cada jornada. No se añade una meditación escrita por el agente.
- `prayer` conserva apertura V/R, rúbrica del Gloria, oración al Señor, tres peticiones con su invocación común y tres avemarías cada una, Gloria, *Sub tuum praesidium*, la letanía **tal como se imprime en 1855** y su oración propia. Se hacen explícitas las respuestas abreviadas «ora». La letanía tiene 46 títulos, incluido *Sancta Maria de Pilari*; no se introducen las adiciones de 2020 en un documento fechado en 1855.
- Se conserva incluso la rúbrica adicional del Gloria tras la primera petición del **día 7**, que consta en la p. 41, además del Gloria final. No se regulariza silenciosamente el original.
- `intentionGuidance` recoge los obsequios del impreso, sin inventar intenciones. Las menciones de avemarías y del Gloria son rúbricas para rezarlas; la conclusión latina «Qui vivis etc.» mantiene su abreviación, sin atribuir al libro una expansión no impresa.
- No se incorporan la reseña histórica previa, las antiguas indulgencias, el himno opcional ni los textos separados de bendición eucarística o de oración por enfermos que siguen a la letanía. El libro los distingue de las nueve jornadas. No se certifican los relatos de milagros o de traslado corporal de María como hechos demostrados: la historia ya revisada explica su carácter tradicional.

### Nota concreta para el coordinador

La p. 49 incluye como obsequio una **disciplina corporal** o una mortificación proporcionada. Se conserva documentariamente y se identifica como práctica del impreso histórico; **no se presenta como instrucción corporal actual para el lector**. La descripción y la procedencia visibles señalan que requiere revisión pastoral y acompañamiento. No se ha inventado una penitencia sustitutiva dentro de la novena. Antes de integrarla como guía práctica para principiantes, el coordinador debe decidir cómo presentar esta edición histórica y su contexto. No se modifica el `pastoralNote` previamente revisado porque el contrato solo autoriza cambiar `status`, `origin` y `sources`.

## San Ramón Nonato: fuente completa y laguna detectada

| Fuente exacta, con URL | Tipo de fuente | Reserva expresa | Resultado |
|---|---|---|---|
| *Novena del glorioso San Ramon No Nacido*, México, Imprenta de los Herederos del Lic. D. Joseph de Jáuregui, calle de San Bernardo, **1780**. [Ejemplar Wellcome b29296043](https://archive.org/details/b29296043); [PDF completo](https://archive.org/download/b29296043/b29296043.pdf), pp. PDF 9 (portada), 12 (rúbrica de meditación), 14–32 (nueve días y cierre). Autor no establecido. | Impreso antiguo, **Public Domain Mark** explícita en sus metadatos. | Marca de dominio público; no se asigna una reserva de la digitalización al texto antiguo. | `263.json`: nueve oraciones particulares, oración común, gozos con respuesta, antífona y colecta, ES/EN/PT. Marcado `pastoral_review` por el contexto hagiográfico y lenguaje histórico. |

- Se comprobaron visualmente la portada y todas las páginas PDF **14–32**, además de la p. 12 que contiene la rúbrica para meditar. ES: transcripción con normalización tipográfica (s larga, cortes de línea, mayúsculas). EN/PT: traducciones propias y fieles. Se conserva la sintaxis antigua, incluso alguna discordancia del impreso; no se modifica la petición para modernizarla.
- El ejemplar de [Mariano Ontiveros, 1825](https://archive.org/details/b29296456) **no sirve por sí solo como fuente completa**: después de la antífona de su página PDF 21 salta a la continuación de la rúbrica y al tercer día en la 22. Faltan el V/R, la colecta y el cuerpo del segundo día. Se detectó comparando las imágenes, no solo el OCR. Por eso se cambió la base a **1780**, que sí conserva el tramo completo; no se inventa ni se atribuye a 1825 lo que no aparece en su ejemplar digital.
- `meditation` reproduce únicamente la **cuarta advertencia del propio novenario**: leer un capítulo de la vida del santo o meditar en sus virtudes, con deseo de imitarlas. Se mantiene como rúbrica, sin escribir meditaciones nuevas ni inventar un capítulo biográfico. Esa instrucción original general se aplica a la novena; no se presenta como una reflexión diferente para cada fecha.
- `intentionGuidance` conserva la rúbrica original de comenzar con la señal de la cruz y el acto de contrición, y de la recitación comunitaria. Las oraciones básicas aparecen como rúbricas, igual que en el impreso. `scripture` permanece vacío.
- La respuesta de los gozos (*Sednos Protector, y Guía / Non-nato Ramon Glorioso*) se imprime completa al principio y luego abreviada «Sednos, &c.»: se desarrolla la **misma respuesta**, sin escribir otra. Se conservan todas sus estrofas. El V/R y la colecta final se traducen del latín antiguo; no se usa una traducción litúrgica moderna.
- La antífona de 1780 muestra la forma impresa *dilexit Christus* donde el contexto reclama el objeto *Christum*; el ejemplar de 1825 permite cotejar la lectura *Christum*. La traducción sigue el sentido inequívoco «amó a Cristo», sin inventar una oración adicional ni presentar una transcripción latina corregida como si fuera literal del impreso.
- Los gozos y la oración común incluyen afirmaciones hagiográficas (nacimiento, imágenes que sudan, curaciones y una cifra de **24.000 milagros en dos años**). Se documenta que **el impreso las afirma**; no se han verificado como hechos ni como milagros reconocidos. La procedencia visible lo aclara. Se conserva también la petición final de resignación a la voluntad de Dios si el favor tarda o no se obtiene; no se convierte el lenguaje devocional en promesa automática.
- Las expresiones históricas sobre cautividad, «infieles», sufrimiento y humildad requieren contexto pastoral. No se promueve buscar daño ni aceptar abusos, ni se sustituye atención sanitaria. Las antiguas indulgencias y las instrucciones para embarazo de los apéndices no se incorporan al cuerpo de los nueve días.

## Resto de la investigación en curso

Estas son pistas documentadas, no declaraciones de inexistencia ni de protección automática de los textos.

| Novena | Fuente concreta / avance | Reserva expresa verificada | Resultado en esta entrega |
|---|---|---|---|
| Aparecida | [Santuario/A12, libro de la novena 2026](https://www.a12.com/santuario/noticias/livreto-da-novena-e-festa-da-padroeira-2026-e-lancado-em-missa). | No se atribuye aquí una reserva específica por ser una editorial. | Pendiente de fuente textual íntegra reutilizable y cotejo. |
| Luján | [Santuario, novena 2026](https://santuariodelujan.org.ar/novena-a-la-virgen-de-lujan-2026/). | No se afirma una reserva específica sin verla. | Pendiente de revisión del texto completo. |
| Chiquinquirá | **Nueva fuente antigua descargada:** Pedro Masústegui, *Modo de rezar la novena…*, Sevilla, Juan Francisco Blas de Quesada, **1734**, [ejemplar John Carter Brown Library](https://archive.org/details/mododerezarlanov00mass). | No se observa reserva vigente del texto antiguo. | En cotejo. El formulario está escrito como plegarias; no se inventará una meditación para cumplir el validador. |
| Coromoto | [Diócesis de Guanare, PDF 2026](https://diocesisguanare.org/wp-content/uploads/2026/08/NovenaVirgendeCoromoto2026.pdf), 56 páginas. Se releen portada, créditos y prólogo: reconoce revisión, adaptación y cambios de contenido, además de citar a Borges (1758). | La búsqueda en el PDF no encontró una reserva expresa. No se le asigna un titular ficticio. | No incorporada en esta entrega; no se identificará toda la revisión de 2026 como si fuese literalmente el original de 1758. |
| Altagracia | [Archivo General de la Nación, 00112883PI](https://colecciones.agn.gob.do/opac/ficha.php?codopac=OUDIG&idpag=2211471180&informatico=00112883PI&presenta=digitaly2p). | No establecida. | Pendiente de ejemplar legible, fecha y cotejo. |
| Copacabana | **Nueva fuente antigua descargada:** Alonso Ramos Gavilán, *Historia del célebre santuario…*, Lima, Gerónimo de Contreras, **1621**, [John Carter Brown Library](https://archive.org/details/historiadelceleb00ramo). Se localiza libro III con instrucciones, puntos para oración mental y oración. | El original antiguo no presenta reserva vigente. **Sí** hay reserva expresa en la edición de 2015 del ABNB / Hans van den Berg / Andrés Eichmann, con prohibición de reproducción; no se copia su estudio ni notas. | En cotejo del original de 1621. El PDF moderno de la Academia Boliviana solo contiene preliminares (88 páginas), no el cuerpo completo: no se usa para fingir lectura de los nueve días. |
| Suyapa | [Basílica, novena](https://basilicadesuyapa.com/novena-virgen-de-suyapa). | No se atribuye una reserva sin comprobarla. | Pendiente de texto íntegro; la consulta anterior mostraba el archivo como próximo. |
| Señor de los Milagros | [Editorial Fátima, Novena rogativa e historia](https://www.fatima.pe/producto/novena-rogativa-e-historia-del-senor-de-los-milagros). | No se equipara la ficha comercial a una reserva expresa del texto. | Pendiente de texto íntegro cotejable; no es prueba de inexistencia de otro novenario. |
| Divino Niño del 20 de Julio | Eliécer Salesman, *Novena bíblica al Divino Niño Jesús*, [catálogo San Pablo](https://sanpablo.cr/libreria/producto/oracion-y-devocion/novena-biblica-al-divino-nino-jesus/). | No se afirma reserva específica solo por ser edición reciente. | Pendiente de fuente textual íntegra reutilizable; no se confunde con Praga. |
| San Ramón Nonato | **Entregada la edición de 1780**, Herederos de Joseph de Jáuregui, [Wellcome b29296043](https://archive.org/details/b29296043). El ejemplar de 1825 presenta la laguna descrita arriba. | Marca explícita **Public Domain Mark**. | Nueve días ES/EN/PT en `263.json`. También se identificó el [PDF mercedario contemporáneo](https://merced.org.ar/wp-content/uploads/2019/12/04-Novena-Nonato.pdf), de Patricia Inés Guerra: no se confunde con el impreso antiguo ni se copia. |
| San Pancracio | [Sancho el Sabio, Salvador Mujal, 1958](https://catalogo.sanchoelsabio.eus/Record/310186/Details). | No se atribuye reserva específica sin verla. | Pendiente de cuerpo cotejable. |
| Santa Eduviges | Antonio de Paredes, *Novena de la gloriosa Santa Heduvige*, México, Herederos de Joseph de Jáuregui, **1791**, [catálogo Biblioteca Pública de Jalisco](https://virtual.bpej.udg.mx/coleccion/23?page=119), exlibris 2664. | Texto antiguo, sin reserva vigente establecida. | Pendiente de facsímil completo. |
| São Benedito | Pe. Fábio Evaristo CSsR, *Novena de São Benedito*, 5.ª ed., 2021, [Editora Santuário](https://www.editorasantuario.com.br/livros/novenas/novena-de-sao-benedito), ISBN 8572008020. | Reserva editorial expresa comprobada en T-004. Derechos: Editora Santuário. | No se incorpora esa edición; se busca otra fuente. Es Benedito de Palermo, no Bento de Nursia. |
| San José Gregorio Hernández | [San Pablo Panamá, novena, ISBN 9789587689983](https://sanpablo.com.pa/product/novena-a-san-jose-gregorio-hernandez/). | No se presume reserva específica por la ficha de venta. | Pendiente de cuerpo íntegro verificable y reutilizable. |

## Validación

Se ejecutó Python conforme al encargo: JSON válido, nueve días ordenados, `meditation` y `prayer` completos ES/EN/PT, ids del índice iguales a los de procedencia, numeración desde 262 sin huecos y conservación de campos de metadatos no autorizados.

```text
2 novenas · 18 trilingual days · matching index/provenance IDs · 0 errors
scripture uses the existing String model; unmodified metadata preserved.
```

**Discrepancia de la especificación:** `NovenaDay.scripture` es `String` y los cuerpos reales utilizan una cadena. Un objeto ES/EN/PT allí haría fallar la deserialización. Como esta fuente no proporciona una lectura bíblica diaria, se conserva `scripture: ""`, sin añadir versículos ni cambiar Kotlin. Los demás campos sí son ES/EN/PT.

No se modifica `index.json`, Kotlin, hashes ni APK. La integración y compilación corresponden al coordinador.
