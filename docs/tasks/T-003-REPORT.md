# T-003 · Informe de Codex

**Estado: EN CURSO · primera entrega parcial, 10 oraciones completas.** No se presenta este lote como el total de 25–35 solicitado. Continuaré con los textos restantes y sus comprobaciones.

## Entrega parcial

Solo se crean `app/src/main/assets/prayers/extra.json` y este informe. Las diez entradas tienen todos los campos del contrato, en su orden, y contenido ES/EN/PT-BR. No se modificaron oraciones existentes, Kotlin ni otros archivos del producto. Se incorporó la revisión de protocolo del coordinador que permite `--partial`; T-002 está aprobado y cerrado.

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

No se compuso ninguna oración nueva ni se añadieron frases de intención al cuerpo de los textos. Las explicaciones e instrucciones son redacción original. Las traducciones se identifican en `textNote`. V/ y R/ expresan la distribución responsorial del texto existente, no un ritual nuevo.

## Validador de esta entrega

```text
$ python3 docs/tasks/tools/validate_prayers.py extra app/src/main/assets/prayers/extra.json --partial
WARN  10 new prayers (expected ~30)
10 entries · 0 errors · 1 warnings
```

La única advertencia es el tamaño del lote parcial. No hay advertencias de extensión ni campos sin traducir. La entrega final se validará sin `--partial` y alcanzará el intervalo del encargo. No se modificó el validador ni se omitieron comprobaciones por entrada.

No se compiló Android en este lote; el protocolo deja esa compilación al coordinador. Se contrastaron los IDs y el vocabulario de etiquetas con el catálogo actual.

## Fuentes consultadas para las diez entradas

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

## Pendiente de esta entrega parcial

- Completar la selección hasta 25–35 oraciones auténticas y no duplicadas. La lista final de candidatas descartadas se documentará al terminar, con sus motivos.
- Revisar las atribuciones y permisos de las candidatas restantes antes de incorporarlas. No se publicarán como auténticas composiciones o versiones sin procedencia identificada.
- No se han atribuido promesas privadas ni números de indulgencia a partir de los devocionarios antiguos. `promise` es `null` en estas diez fichas; las concesiones verificadas de las letanías se explican en `indulgenceNote` y `approval`.

## Segundo lote · 20 entradas acumuladas

Añadidas: `canticle_of_creatures`, `ephrem_lenten_prayer`, `oremus_pro_pontifice`, `benedict_medal_prayer`, `angele_dei_latin`, `salve_regina_latin`, `anima_christi_latin`, `benedic_domine_latin`, `actiones_nostras`, `visita_quaesumus`. Las cuatro ediciones latinas son las excepciones expresamente solicitadas en T-003: el catálogo previo tenía versiones vernáculas, no estos cuerpos latinos. No se añadió otra traducción de Alma de Cristo ni se atribuyó su autoría a Ignacio.

Validación parcial: **20 entries · 0 errors · 4 warnings**. Las cuatro advertencias corresponden únicamente a `leaderText` idéntico en las tres interfaces de las cuatro ediciones latinas; es deliberado, queda indicado en `textNote` y todos sus metadatos están traducidos.

Fuentes adicionales efectivamente leídas:

- [Bibliotheca Augustana, original umbro del Cantico di Frate Sole](https://www.tha.de/~harsch/italica/Cronologia/secolo13/Francesco/fra_cant.html), poema completo y referencia al manuscrito de Asís. Traducciones propias; no se copió una traducción moderna.
- [Saint Ephrem the Syrian, The Prayer of St Ephrem](https://saintephrem.org/original-translations/the-prayer-of-st-ephrem/), original griego, variantes y referencia CPG 4078.9. Se tradujo el griego sin la variante opcional entre corchetes; no se copió la traducción inglesa moderna. La atribución se conserva como tal.
- [Confederación Benedictina, The Medal of Saint Benedict](https://osb.org/the-medal-of-saint-benedict/), inscripciones latinas, origen de las letras y diferencia entre la invocación y la bendición del objeto. Se tradujeron las inscripciones antiguas, no el rito de bendición moderno.
- [Preces Latinae, Anima Christi](https://preces-latinae.org/thesaurus/PostMissam/AnimaChristi.html), original latino y referencias al Misal.
- Enchiridion oficial ya enlazado: se leyeron las concesiones 17, 18, 25 y 26 con los formularios latinos de Salve, Angele Dei, Oremus, Benedic, Actiones y Visita. Solo se anotan concesiones leídas, sin promesa privada.
