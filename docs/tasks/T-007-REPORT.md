# T-007 · Informe de Codex

Entrega completa: **12 de 12** entradas, en el orden del encargo. Trabajo limitado a `text-overrides.json`, las fichas correspondientes de `details-b.json` y este informe. La primera entrega parcial fue el commit `99baf45` (3 entradas).

## Fuentes y cambios por id

### `chaplet_of_st_michael`

- **Fuente textual:** *The Raccolta*, Burns & Oates / Benziger Brothers, edición inglesa de 1910, n.º 291, «Angelical Crown», pp. impresas 261–263. [Facsímil completo](https://archive.org/details/theraccoltaorcol00unknuoft); [PDF](https://archive.org/download/theraccoltaorcol00unknuoft/theraccoltaorcol00unknuoft.pdf), páginas PDF 281–283. Leídas visualmente las tres páginas, además de consultar su OCR: por ejemplo, el comienzo es **At the intercession**, no el «By» que una transcripción secundaria sugería.
- Inglés de esa edición de dominio público, con normalización tipográfica. Traducciones propias y fieles ES/PT. Las rúbricas mantienen los nueve grupos de un padrenuestro y tres avemarías, el Gloria inicial y los cuatro padrenuestros finales; no se inventa una expansión de las oraciones abreviadas en la fuente.
- Se restituyen las nueve peticiones distintas, desde serafines hasta ángeles, antífona, V/R y oración. Sin fórmula de exorcismo. Antífona y oración continúan en `responseText`.
- Actualizada la ficha histórica y práctica. La mención impresa a Pío IX, 8-08-1851, se identifica como referencia del devocionario, **no** como decreto original examinado. No se reproducen como actuales las indulgencias ni las fechas festivas de 1910. No se acredita aparición ni promesa privada.

### `adoro_te_devote`

- **Fuente textual:** la misma *Raccolta*, 1910, n.º 106, pp. 86–88, texto latino. Cotejadas visualmente las páginas PDF 106–108.
- Traducciones propias y fieles ES/EN/PT de las siete estrofas completas, dispuestas en líneas. No se atribuyen a Caswall o Hopkins. La séptima estrofa continúa en `responseText`; se elimina así el cierre ajeno que se conservaría si ese campo fuese `null`.
- Se retira el aviso de transcripción alterada y se conserva la prudencia sobre la autoría tradicional. Contexto previamente comprobado en T-002: [Catholic Encyclopedia, Adoro Te Devote](https://www.newadvent.org/cathen/01154b.htm). El [Enchiridion de 1999](https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html), concesión 7, **menciona** el himno, no imprime aquí su texto íntegro. La fuente textual es la edición de 1910.

### `litany_of_loreto`

- **Base antigua:** *The Raccolta*, 1910, n.º 189, pp. 168–170, formulario latino de dominio público.
- **Comprobación de la serie actual completa:** [Santa Sede, The Litany of Loreto](https://www.vatican.va/special/rosary/documents/litanie-lauretane_en.html), contrastada también con su [página italiana](https://www.vatican.va/special/rosary/documents/litanie-lauretane_it.html). Estas páginas están actualizadas e incluyen las tres invocaciones de 2020; no se las fecha ficticiamente en 1910. Se cuentan **54 títulos marianos**, sin duplicados.
- **Disposición y lugares exactos de las tres incorporaciones:** [carta de la Congregación para el Culto Divino, 20-06-2020](https://press.vatican.va/content/salastampa/es/bollettino/pubblico/2020/06/20/letan.html): *Mater misericordiae* después de *Mater Ecclesiae*; *Mater spei* después de *Mater divinae gratiae*; *Solacium migrantium* después de *Refugium peccatorum*.
- Traducciones propias del formulario tradicional, con los títulos añadidos y cotejados. No se copia una traducción litúrgica nacional moderna. Para la oración final se utiliza el **Concede nos famulos tuos** tradicional: latín completo comprobado en [Movimento Apostolico, Litanie Lauretane, sección «Preghiamo»](https://www.movimentoapostolico.org/formazione/parola-commentata/meditazioni-preghiere/litanie-lauretane.pdf); su contenido coincide con la conclusión publicada por la Santa Sede. Se traduce el latín antiguo, sin reproducir el comentario moderno del PDF.
- Se restauran las peticiones iniciales, respuestas en cada título, tres *Agnus Dei*, V/R y colecta. La colecta continúa en `responseText`. Se actualiza la ficha, eliminando los avisos de omisiones y duplicados.

### `litany_of_the_sacred_heart`

- **Fuente textual:** Bonaventure Hammer, *General Catholic Devotions*, 1909, «Litany of the Sacred Heart of Jesus», [Project Gutenberg 33676](https://www.gutenberg.org/files/33676/33676-h/33676-h.htm). Texto inglés de dominio público completo, no una reconstrucción de memoria; traducciones propias y fieles ES/PT.
- Se restituyen las **33 invocaciones** al Corazón de Jesús, incluida *in whom the Father was well pleased*, además de apertura, tres *Agnus Dei*, V/R y colecta. V/R explicita la alternancia de peticiones/respuestas; no añade nuevas súplicas. La colecta continúa en `responseText`.
- La fecha 2-04-1899 se atribuye al registro de aprobación impreso en el devocionario, no a un decreto original que se finja haber consultado. No se trasladan sus antiguas indulgencias de días a la actualidad. Referencia actual cotejada: *Enchiridion*, 1999, concesión 22.

### `advent_antiphons`

- **Fuente textual latina:** *Antiphonae majores*, [hoja reunida por Adam McCune](https://adam-mccune.com/classic-hymns/pdf/OComeOComeEmmanuelGreatAntiphonsParallel.pdf), primera página. El propio documento remite a J. P. Migne, *Patrologia Latina* 78, pp. 732–733, vía [Hymnology Archive](https://hymnologyarchive.squarespace.com/o-come-o-come-emmanuel). Se leyó el latín completo del PDF; no se afirma haber consultado el volumen de Migne directamente. La hoja no proporciona un año de edición verificable: se registra **sin fecha**, sin inventarlo. Se traduce el original litúrgico antiguo de dominio público; **no se reproduce la traducción moderna de la Church of England ni el himno métrico** que la hoja también incluye.
- Traducciones propias y fieles ES/EN/PT de las siete antífonas completas. Las fechas y nombres son rótulos editoriales, no palabras añadidas a la oración. *O Emmanuel* continúa en `responseText`; desaparece el anterior estribillo del himno. Se actualiza también el `latinTitle`, que antes contenía un verso de *Veni, veni Emmanuel*.
- Calendario y contexto: [USCCB, The O Antiphons of Advent](https://www.usccb.org/prayers/o-antiphons-advent), ya consultado en T-002. Se precisa `whenToPray`: 17–23 de diciembre, Magnificat de Vísperas.

### `suscipe_prayer`

- **Fuente:** san Ignacio de Loyola, *Ejercicios espirituales*, 234, dentro de la Contemplación para alcanzar amor. Español antiguo con ortografía normalizada según la cita íntegra del [material EELoyola, parroquia Santa Cruz de Madrid](https://santacruz.archimadrid.es/santacruz/EELoyola.pdf), primera página; se copia la oración de Ignacio, no el comentario contemporáneo. No se atribuye a este folleto una edición de 1548.
- Inglés de Elder Mullan, *The Spiritual Exercises of St. Ignatius of Loyola*, P. J. Kenedy & Sons, **1914**: [portada y datos de edición](https://www.ccel.org/ccel/ignatius/exercises.i.html), [Contemplation to Gain Love](https://ccel.org/ccel/ignatius/exercises.xvi.html). La antigua mención de copyright de 1914 pertenece a una edición que ya está en dominio público; no se presenta como reserva vigente.
- Portugués: traducción propia y fiel. Restituida la petición final de amor y gracia en `responseText`, sustituyendo el cierre que no era de Ignacio. La ficha explica el contexto sin atribuir una promesa privada a la oración.

### `st_joseph`

- **Fuente textual:** *Ad te, beate Ioseph*, latín completo leído en el [Enchiridion de 1999](https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html), concesión 19. Inglés de dominio público de *The Raccolta*, 1910, n.º 308, p. 281. ES/PT: traducciones propias y fieles del latín antiguo.
- **Contexto:** León XIII, [Quamquam pluries, 15-08-1889](https://www.vatican.va/content/leo-xiii/la/encyclicals/documents/hf_l-xiii_enc_15081889_quamquam-pluries.html). La encíclica contextualiza la práctica; no se dice que su página electrónica imprima la oración cuando no lo hace. El texto íntegro se comprueba en las otras dos fuentes.
- Reemplazadas las invocaciones breves no documentadas por los dos párrafos completos. El segundo continúa en `responseText`; `latinTitle` actualizado. No se copian concesiones antiguas de días ni se asignan promesas de resultado.

### `sacred_heart_consecration`

- **Fuente textual exacta:** *Iesu dulcissime, Redemptor humani generis*, forma latina completa impresa en *Enchiridion Indulgentiarum*, cuarta edición, **1999**, concesión 2, [texto de la Santa Sede](https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html). ES/EN/PT: traducciones propias y fieles del latín de esta oración histórica; no traducciones de versiones nacionales modernas.
- **Contexto histórico:** León XIII, [Annum sacrum, 25-05-1899](https://www.vatican.va/content/leo-xiii/la/encyclicals/documents/hf_l-xiii_enc_25051899_annum-sacrum.html). Se identifica claramente la **forma recogida en 1999**, sin confundirla con versiones históricas más largas ni atribuir a 1899 cualquier añadido posterior.
- Sustituida la entrega breve no identificada por el acto completo. El último párrafo continúa en `responseText`; se actualiza el título latino. `whenToPray` señala el acto público de Cristo Rey mencionado en la fuente. No se incorporan promesas de otras prácticas del Sagrado Corazón ni se añade un beneficio sin condiciones.

### `veni_creator_spiritus`

- **Fuente textual:** *The Raccolta*, **1910**, n.º 35, pp. 27–28, latín; cotejadas visualmente las páginas PDF 47–48. Traducciones propias y fieles ES/EN/PT de las siete estrofas.
- Se usa la **doxología ordinaria** impresa en esa edición (*Deo Patri sit gloria / Eiusque soli Filio / Cum Spiritu Paraclito / Nunc et per omne saeculum*), no su alternativa pascual. La doxología continúa en `responseText`; no se compone un cierre nuevo ni se mezcla el himno con la secuencia *Veni Sancte Spiritus*.
- Se conserva la prudencia histórica sobre Rabano Mauro. Se mantiene la concesión 26 de 1999 ya documentada, corrigiendo sus condiciones para retirar la alusión obsoleta a «esta adaptación» y conservar iglesia/oratorio, acto solemne, 1 de enero o Pentecostés y condiciones habituales completas. No se presenta una lectura privada como ese acto ni se afirma aprobación litúrgica de la traducción propia.

### `laudes_divinas`

- **Fuente de la serie actual:** latín completo de las 14 aclamaciones en [Preces Latinae, Laudes Divinae](https://www.preces-latinae.org/thesaurus/Euch/Laudes.html), consultado mediante su transcripción web accesible. La página no ofrece año de edición verificable; se registra **sin fecha**. Se traducen las fórmulas tradicionales latinas, no el comentario ni una traducción moderna protegida.
- **Comprobación histórica:** *The Raccolta*, **1910**, n.º 426, p. 369, trae una forma más breve. No se presenta esa serie de 1910 como si incluyera los añadidos posteriores. La historia del núcleo atribuido a Felici se conserva como atribución y se distingue de la serie actual.
- Traducciones propias y fieles ES/EN/PT; se suprimen duplicados y sustituciones. La última alabanza y el Amén continúan en `responseText`. Se corrige también la indicación española de uso que contenía la palabra inglesa «repeated».

### `alma_missionum`

- **Fuente textual:** antífona medieval *Alma Redemptoris Mater*, latín completo cotejado en el [libreto de la Santa Sede, primeras Vísperas de Santa María Madre de Dios y Te Deum, 31-12-2017](https://www.vatican.va/news_services/liturgy/libretti/2017/20171231-libretto-maria-madre-di-dio_pvespri.pdf), p. impresa **50**. Se descargó el PDF y se leyó el texto latino bajo la notación. El original medieval es de dominio público; no se copia la traducción italiana contemporánea.
- Traducciones propias y fieles ES/EN/PT, incluida la última petición en `responseText`. No se añaden una colecta, exclamaciones ni intenciones misioneras ajenas al original. Se actualizan los tres títulos, `latinTitle` y `whenToPray`, conservando el id persistido. No se certifica la procedencia del antiguo formulario *Alma missionum*: se retira.
- Contexto: san Juan Pablo II, *Redemptoris Mater* (1987), 51, cuyo comienzo latino también consta en [AAS 79 (1987), p. 430](https://www.vatican.va/archive/aas/documents/AAS-79-1987-ocr.pdf). No se asegura una autoría individual ni una fecha precisa de composición.

### `confiteor`

- Se corrige **solo la indicación de uso**: acto penitencial de los ritos iniciales de la Misa, cuando se utiliza esta fórmula. No se presenta como un texto inmediatamente anterior a la Comunión.
- Fuente normativa: Ordenación General del Misal Romano, 51; comprobación ya realizada en T-002 con [USCCB, boletín de julio de 2011, pp. 26–27](https://www.usccb.org/resources/newsletter-2011-07.pdf), que distingue el acto penitencial de la absolución sacramental.
- El contrato obliga a proporcionar `leaderText`, aunque este punto del encargo solo pide `whenToPray`. Por ello se extraen y conservan **literalmente** los tres textos ya existentes en Kotlin, sin sustituirlos por otra traducción ni certificar de nuevo su edición o licencia. `responseText: null` conserva el estado anterior. Su ficha mantiene historia, instrucciones, fuentes y aprobación; se precisa la nota textual para reflejar el alcance exacto del cambio.

## Validación de esta entrega

```text
python3 docs/tasks/tools/validate_prayers.py overrides app/src/main/assets/prayers/text-overrides.json
12 entries · 0 errors · 0 warnings

python3 docs/tasks/tools/validate_prayers.py details app/src/main/assets/prayers/details-b.json docs/tasks/T-002.ids
34 entries · 0 errors · 0 warnings
```

Las 22 fichas no correspondientes a este encargo se conservan sin cambios de contenido. No se ha compilado Android: el protocolo deja la compilación al coordinador.

## Precauciones técnicas y límites

- El contrato de la app conserva el valor anterior cuando `responseText` es `null`. Por eso se distribuye cada formulario completo entre texto y continuación auténtica: no se deja sobrevivir un añadido anterior ni se compone una respuesta nueva. La división de un himno entre campos **no** significa que tenga un estribillo litúrgico.
- No se afirma que estas traducciones propias sean versiones litúrgicas aprobadas. Para uso litúrgico se remite a los libros aprobados de la comunidad.
- No se ha inspeccionado el original del decreto de 1851 citado por la *Raccolta*, ni se revalidan concesiones antiguas.
- Los campos heredados fuera del contrato (categoría, etiquetas y `indulgenceNote`) no se modifican: el contrato no permite sobrescribirlos ni autoriza editar Kotlin. En particular, el `indulgenceNote` heredado de `laudes_divinas` dice «indulgencia parcial» sin fuente; esta entrega no certifica esa nota ni añade una concesión a su ficha. El coordinador puede resolverla al integrar. Las etiquetas misioneras antiguas de `alma_missionum` también quedan fuera del alcance técnico de este encargo.
- No se atribuyen fechas de edición inexistentes a compilaciones web sin fecha ni se afirma cotejo directo de fuentes consultadas a través de una transcripción identificada.
- Completadas las 12 entradas. La edición y licencia de las traducciones preexistentes del `confiteor` siguen sin verificarse; su texto no fue sustituido por este encargo.

## Preguntas para el coordinador

Sin bloqueos para entregar T-007. Al integrar, conviene retirar o documentar el `indulgenceNote` heredado de las Alabanzas divinas y actualizar las etiquetas misioneras heredadas de Alma; ambos campos están fuera del contrato autorizado.
