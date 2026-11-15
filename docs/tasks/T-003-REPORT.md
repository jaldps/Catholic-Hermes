# T-003 · Informe final de Codex

**Entrega completa para revisión: 25 oraciones y ediciones latinas**, dentro del intervalo 25–35 del encargo. Solo se crean `app/src/main/assets/prayers/extra.json` y este informe. Se conserva el contrato y el orden de campos; todos los metadatos están en ES/EN/PT-BR. No se tocaron Kotlin, oraciones existentes, APK ni claves.

## Contenido

| Id | Texto y alcance |
|---|---|
| `litany_of_st_joseph` | Letanía completa, con las siete invocaciones de 2021, respuestas, versículo y oración final. Texto inglés de la USCCB expresamente declarado de dominio público; traducciones propias ES/PT. |
| `litany_of_the_holy_name` | Letanía completa de la Raccolta de 1910, n. 56, incluidas las dos oraciones finales. Traducciones propias. |
| `litany_of_the_precious_blood` | Formulario latino de la letanía: 24 invocaciones principales, apertura, Cordero de Dios, versículo y oración final. Traducciones propias, sin copiar las traducciones modernas de las webs. |
| `ave_maris_stella` | Siete estrofas completas del himno latino según Raccolta 1910, n. 213. Traducciones propias no métricas. |
| `stabat_mater` | Veinte estrofas de la forma latina de Raccolta 1910, n. 238. Traducciones propias completas. Autoría presentada como discutida. |
| `montfort_short_consecration` | Fórmula montfortiana breve documentada y citada en latín por Benedicto XVI en 2011; distinta de la consagración extensa. |
| `thomas_before_study` | Creator ineffabilis, texto latino tradicional, cotejado con Raccolta 1910, n. 52, y Preces Latinae. |
| `thomas_after_communion` | Gratias tibi ago, formulario latino de acción de gracias, traducciones propias. |
| `ambrose_before_mass` | Ad mensam dulcissimi, texto completo atribuido a Ambrosio; no se afirma autoría demostrada. |
| `nada_te_turbe` | Estrofa española tradicional reproducida en CIC 227, sin añadidos; traducciones propias EN/PT. |


Las otras quince entradas son:

| Id | Alcance y comprobación |
|---|---|
| `canticle_of_creatures` | Poema umbro completo de Francisco, traducciones propias, sin omitir perdón y muerte. |
| `ephrem_lenten_prayer` | Formulario griego cuaresmal; atribución prudente a Efrén, sin variante opcional sobre la avaricia. |
| `oremus_pro_pontifice` | Versículo y respuesta del Enchiridion; N permite insertar el nombre sin fijar un pontificado. |
| `benedict_medal_prayer` | Invocación de las inscripciones latinas; no rito de bendición ni exorcismo sobre personas. |
| `angele_dei_latin` | Edición latina explícitamente solicitada; la previa era vernácula. |
| `salve_regina_latin` | Antífona latina del Enchiridion, sin añadir otras oraciones. |
| `anima_christi_latin` | Original latino; autor anónimo, anterior a Ignacio. |
| `benedic_domine_latin` | Formulario latino de bendición de la mesa. |
| `actiones_nostras` | Colecta antigua para comenzar las tareas. |
| `visita_quaesumus` | Oración tradicional de Completas por el hogar. |
| `adsumus_full` | Formulario íntegro del Enchiridion, distinto de la abreviación sinodal reciente. |
| `o_salutaris_hostia` | Dos estrofas finales de Verbum supernum. |
| `panis_angelicus` | Estrofas 6–7 de Sacris solemniis; se identifica el extracto, no se llama himno completo. |
| `transfige_dulcissime` | Oración franciscana completa; no se asegura que el formulario entero sea de Buenaventura. |
| `concede_mihi_short` | Formulario breve de Raccolta 1910 n. 51; distinto de Creator y de la oración extensa del mismo comienzo. |

No se compusieron oraciones nuevas ni se añadieron frases de intención a sus cuerpos. Las explicaciones e instrucciones son redacción original. Las traducciones se identifican en `textNote`. Las tres letanías incluyen apertura, invocaciones, respuestas y conclusión conforme al formulario identificado. Las cuatro ediciones latinas son excepciones autorizadas por T-003; no duplican otra edición latina previa.

## Validación final

```text
$ python3 docs/tasks/tools/validate_prayers.py extra app/src/main/assets/prayers/extra.json
WARN  angele_dei_latin.leaderText: identical text in two languages (untranslated?)
WARN  salve_regina_latin.leaderText: identical text in two languages (untranslated?)
WARN  anima_christi_latin.leaderText: identical text in two languages (untranslated?)
WARN  benedic_domine_latin.leaderText: identical text in two languages (untranslated?)
25 entries · 0 errors · 4 warnings
```

Las cuatro advertencias son intencionales: el cuerpo latino se conserva en las tres interfaces, como explica `textNote`; sus títulos y demás campos sí están traducidos. No hay advertencias de extensión. No se modificó el validador. Se contrastaron IDs y etiquetas con el catálogo. No se compiló Android: el coordinador realiza la integración y compilación según el protocolo.

Se publicaron lotes de 10, 20 y 25 entradas. La primera validación parcial tuvo una advertencia adicional de tamaño; la final alcanza el intervalo solicitado.

## Fuentes consultadas del primer lote

- [The Raccolta, edición de 1910, Burns & Oates / Benziger, ejemplar digitalizado de Toronto](https://archive.org/details/theraccoltaorcol00unknuoft). Se leyó el OCR del volumen y se contrastaron los formularios: n. 56 (Nombre de Jesús), n. 213 (Ave maris stella), n. 238 (Stabat Mater), n. 489 (San José), n. 52 (Creator ineffabilis) y tabla de preparación/acción de gracias de p. 403. Los errores evidentes de OCR no se reprodujeron mecánicamente. Las antiguas concesiones expresadas en «días» no se trasladaron.
- [Culto Divino, carta sobre las siete invocaciones de San José, 1 de mayo de 2021](https://press.vatican.va/content/salastampa/it/bollettino/pubblico/2021/05/01/0264/00573.html). Acredita la aprobación de 1909 y los añadidos de 2021.
- [USCCB, Litany of Saint Joseph, junio de 2021, PDF enlazado por la Diócesis de Davenport](https://files.ecatholic.com/13543/documents/2021/6/Litany%20of%20St%20Joseph%20June%202021%20BCDW.PDF). Su encabezado dice expresamente «Public domain». Se verificó el orden de todas las invocaciones, incluidas las siete nuevas; ES/PT no se presentan como traducciones oficiales de conferencias episcopales.
- [Juan XXIII, Inde a primis, 30 de junio de 1960, texto latino oficial](https://www.vatican.va/content/john-xxiii/la/apost_letters/1960/documents/hf_j-xxiii_apl_19600630_indeaprimis.html), contexto de la devoción a la Sangre de Cristo.
- [Preces Latinae, Litaniae pretiosissimi Sanguinis](https://preces-latinae.org/thesaurus/Filius/LitPSDNIC.html), texto latino completo de contraste. Se tradujo el latín, no la traducción inglesa moderna que figura al lado.
- [The Catholic Encyclopedia, Stabat Mater](https://www.newadvent.org/cathen/14239b.htm), discusión de las atribuciones. No se atribuye con certeza a Jacopone da Todi.
- [Benedicto XVI, homilía de beatificación de Juan Pablo II, boletín de 1 de mayo de 2011](https://press.vatican.va/content/salastampa/it/bollettino/pubblico/2011/05/01/0253/00635.pdf), fórmula latina Totus tuus con referencia a Verdadera devoción 266. Se traduce la fórmula antigua citada, no la traducción moderna de la homilía.
- [Preces Latinae, Creator ineffabilis](https://preces-latinae.org/thesaurus/Varia/CreatorIneff.html), original latino; su traducción inglesa no se copió.
- [Preces Latinae, Gratias tibi ago](https://preces-latinae.org/thesaurus/PostMissam/Thomae.html), original latino; su traducción inglesa no se copió.
- [Preces Latinae, Ad mensam dulcissimi](https://preces-latinae.org/thesaurus/AnteMissam/Ambrose.html), original latino y nota de atribución; su traducción inglesa no se copió.
- [Catecismo, CIC 227, página oficial](https://www.vatican.va/archive/catechism_sp/p1s2c1p1_sp.html), estrofa Nada te turbe y atribución teresiana. No se asegura una fecha de composición no documentada.
- [Enchiridion Indulgentiarum, cuarta edición de 1999, texto latino oficial](https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html), concesión 22. Las notas sobre las letanías distinguen el formulario aprobado de una traducción propia; no se afirma una aprobación litúrgica de estas traducciones nuevas.


Fuentes adicionales efectivamente leídas:

- [Bibliotheca Augustana, original umbro del Cantico di Frate Sole](https://www.tha.de/~harsch/italica/Cronologia/secolo13/Francesco/fra_cant.html), poema completo y referencia al manuscrito de Asís. Traducciones propias; no se copió una traducción moderna.
- [Saint Ephrem the Syrian, The Prayer of St Ephrem](https://saintephrem.org/original-translations/the-prayer-of-st-ephrem/), original griego, variantes y referencia CPG 4078.9. Se tradujo el griego sin la variante opcional entre corchetes; no se copió la traducción inglesa moderna. La atribución se conserva como tal.
- [Confederación Benedictina, The Medal of Saint Benedict](https://osb.org/the-medal-of-saint-benedict/), inscripciones latinas, origen de las letras y diferencia entre la invocación y la bendición del objeto. Se tradujeron las inscripciones antiguas, no el rito de bendición moderno.
- [Preces Latinae, Anima Christi](https://preces-latinae.org/thesaurus/PostMissam/AnimaChristi.html), original latino y referencias al Misal.
- Enchiridion oficial ya enlazado: se leyeron las concesiones 17, 18, 25 y 26 con los formularios latinos de Salve, Angele Dei, Oremus, Benedic, Actiones y Visita. Solo se anotan concesiones leídas, sin promesa privada.


Fuentes del cierre:

- [OFM, original umbro completo del Cántico](https://ofm.org/uploads/Cantico_Originale.pdf), cotejo adicional de todas las estrofas; [carta de la familia franciscana de 2025](https://ofm.org/es/un-canto-a-la-reconciliacion.html), que documenta la composición gradual en 1225–1226. No se copió una traducción moderna.
- [Preces Latinae, O salutaris Hostia](https://www.preces-latinae.org/thesaurus/Euch/OSalutaris.html), original latino de ambas estrofas.
- [Preces Latinae, Sacris solemniis](https://www.preces-latinae.org/thesaurus/Hymni/SacrisSol.html), original latino: se leyeron las estrofas para comprobar el lugar de Panis angelicus y su conclusión.
- [Preces Latinae, Transfige](https://preces-latinae.org/thesaurus/PostMissam/Bonaventurae.html), original latino completo y advertencia sobre su desarrollo posterior; no se copió la traducción inglesa moderna.
- Raccolta 1910, n. 51, formulario latino breve Concede mihi (no confundir con el formulario extenso que circula bajo el mismo comienzo); n. 52 corresponde a Creator.
- Enchiridion oficial, concesión 26: formulario completo Adsumus. Se hizo traducción propia del original tradicional, no de la abreviación contemporánea.

## Candidatas no incorporadas y límites de la investigación

La selección tiene 25 entradas verificables; la lista de candidatas no obligaba a incluirlas todas. Descartar en este informe significa **no incorporada**, no declarar falsa o impropia la devoción.

| Candidata | Motivo de no incorporación / dato no verificado |
|---|---|
| Abandono de Foucauld | Localizada la historia real de su adaptación comunitaria, pero no se estableció una base de reutilización de la forma abreviada moderna. [Antoine Chatelard, estudio histórico de 1995, pp. 1–2 del PDF](https://www.charlesdefoucauld.org/docs/fr_priere_abandon.pdf) documenta la adaptación en 1940 y una impresión en 1946. No se presentó esa forma como transcripción directa del manuscrito ni se compuso una alternativa. |
| Mercier / Respira en mí | Se localizaron versiones devocionales actuales, incluida la [Oficina de Renovación de Rockford](https://charismaticrenewal.rockforddiocese.org/personal-and-spiritual-growth/prayers/prayers-to-the-holy-spirit/), pero no se verificó una edición antigua reutilizable del original ni un lugar en las obras de Agustín. No se afirmó que la atribución a Agustín estuviera demostrada. |
| Dos oraciones del Ángel de Fátima | La [narrativa oficial del Santuario](https://www.fatima.pt/pt/pages/narrativa-das-aparicoes/) y sus Memórias son fuentes localizadas, pero no se completó la comprobación de los derechos de sus ediciones y traducciones. No se copió una versión contemporánea ni se inventó otra oración. |
| Ricardo de Chichester | No se verificó una edición original reutilizable de la fórmula seleccionada en esta entrega; se evitó copiar la conocida reelaboración musical moderna. |
| Vocaciones | No se seleccionó un formulario antiguo concreto con procedencia suficiente. No se compuso una oración genérica para rellenar el lugar. |
| Mira la estrella de Bernardo | No se completó el cotejo del pasaje íntegro de la homilía II, 17 con una edición original reutilizable. No se reconstruyó de memoria. |
| Letanía de humildad | Se localizaron referencias a impresiones anteriores a Merry del Val, pero no se cotejó el facsímil de una edición antigua para incorporarla fielmente. No se declaró su autoría ni aprobación para culto público. |
| Gertrudis y las almas | No se verificó el origen textual del formulario popular en una obra concreta de la santa ni el añadido numérico que circula con él. No se atribuyó una promesa ni se tomó la repetición en webs como prueba. |
| Otra Alma de Cristo vernácula de Ignacio | Excluida por duplicación explícitamente prohibida. Solo se añadió el original latino permitido; Ignacio no es presentado como autor. |

Las traducciones propias no se presentan como textos litúrgicos aprobados. Las concesiones verificadas se distinguen de promesas privadas; `promise` es `null` en las 25 fichas. No se trasladaron las antiguas concesiones en días de Raccolta. No se añadió ninguna plenaria nueva en esta selección. Las notas no certifican las traducciones propias como formularios nacionales oficiales.

## Preguntas para el coordinador

Ninguna bloquea la revisión. Si se desea ampliar la selección con las candidatas anteriores, hará falta completar la procedencia y, donde corresponda, obtener autorización de los titulares antes de incorporar el texto. No se han solicitado permisos a terceros ni publicado contenido con derechos tomándolo de sus traducciones modernas.
