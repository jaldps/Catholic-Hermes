# T-002 · Informe de Codex

## Entrega

Se creó `app/src/main/assets/prayers/details-b.json` con las 34 entradas de `T-002.ids`, en el mismo orden y con las claves exactas del contrato. Historia, instrucciones, referencias y notas están en ES/EN/PT-BR. No se modificó ningún cuerpo de oración, archivo Kotlin, interfaz, firma ni APK.

Las explicaciones e instrucciones son redacción original. No se compuso ninguna oración nueva. Cuando no se pudo documentar el formulario actual, la ficha lo dice: una fuente sobre la devoción o sobre un formulario distinto no acredita la autoría, aprobación o licencia del texto de la app.

Se documentaron como `ECCLESIAL_GRANT` las concesiones plenarias del Veni Creator, Tantum ergo y En ego, y del Te Deum, con sus actos específicos y condiciones habituales. Se documentó como `SOURCED` la promesa de conversión ligada a la invocación a la Sangre y al Agua del Diario 186–187, con atribución y condiciones. Las restantes fichas tienen `promise: null`; algunas describen concesiones parciales en `approval`, distinguiendo el formulario aprobado de las versiones que requieren cotejo.

## Validador

```text
$ python3 docs/tasks/tools/validate_prayers.py details app/src/main/assets/prayers/details-b.json docs/tasks/T-002.ids
34 entries · 0 errors · 0 warnings
```

No se modificó el validador ni se redujo la lista para obtener este resultado. El primer borrador de diez fichas tenía cero advertencias de contenido/extensión, pero el validador rechazaba la lista incompleta. Como T-002 exige exactamente 34 entradas antes de cada push, la primera entrega reúne las 34: no se publicaron objetos vacíos o textos de relleno para eludir esa comprobación. En los encargos que permitan validar subconjuntos se harán entregas aproximadamente cada diez elementos.

No se ejecutó una compilación Android: no hay cambios de código y el protocolo deja esa compilación al coordinador. El resultado del validador comprueba estructura y extensión; no constituye una aprobación doctrinal ni una licencia de reproducción.

## Hallazgos que requieren otro encargo

No se corrigió `PrayerAndNovenaData.kt`, por estar fuera del alcance autorizado.

| Id | Hallazgo y límite de la documentación |
|---|---|
| `laudes` | Es una selección devocional, no Laudes completas. El himno y la oración final no tienen edición identificada; el salmo necesita aclarar su doble numeración y sus variantes. |
| `alma_missionum` | No es Alma Redemptoris Mater. No se localizó fuente del formulario o de su título latino; no se le atribuyen autor, antigüedad o aprobación. |
| `sub_tuum_praesidium` | La fecha categórica «siglo III» del texto auxiliar no refleja la discusión paleográfica de P.Ryl. III 470. La ficha documenta la incertidumbre. |
| `peace_prayer` | El título sigue atribuyéndola a Francisco; su primera publicación documentada fue anónima, en La Clochette, 1912. |
| `adoro_te_devote` | El campo español contiene latín abreviado y alterado; por ejemplo, difiere del poema en los pasajes sobre los sentidos y sobre la Cruz. La respuesta no es el final del himno. Hace falta reemplazo fiel, no atribuir automáticamente a Tomás estas variantes. |
| `veni_creator_spiritus` | Paráfrasis de varias estrofas; no se acredita como traducción litúrgica oficial. Para el acto indulgenciado se requiere el himno aprobado. |
| `laudes_divinas` | Selección con repeticiones, distinta de la serie habitual. La aprobación o historia de la serie no acredita estas variantes. |
| `sacred_heart_consecration` | La fórmula corta no es Iesu dulcissime, Redemptor humani generis. No se le atribuyen la autoría de León XIII ni la concesión específica de ese otro acto. |
| `st_joseph` | No es Ad te, beate Ioseph. No corresponde presentarla como oración compuesta por León XIII en Quamquam pluries. |
| `st_jude_thaddeus`, `st_anthony` | Fórmulas breves sin autor/edición identificados. La de Antonio no es Si quaeris miracula. |
| `suscipe_prayer` | Falta la petición final del amor y la gracia de EE 234; la respuesta añadida no se ha verificado como texto de Ignacio. |
| `immaculate_conception` | La invocación principal corresponde a la medalla; la respuesta añadida no es su inscripción. No se examinó un decreto de reconocimiento de las apariciones. |
| `bendita_sea_tu_pureza` | Uso devocional documentado; no se verificaron autor y fecha de composición. No se adjudica un siglo por conjetura. |
| `advent_antiphons` | Contiene estrofas de O Come, O Come, Emmanuel, no las siete antífonas litúrgicas del 17–23 de diciembre. |
| `chaplet_of_st_michael` | No reproduce la corona tradicional: cambia las salutaciones y reúne otros textos. Incluye un pasaje extenso relacionado con fórmulas de exorcismo. Revisar expresamente a la luz de Inde ab aliquot annis, sin confundir la oración breve a san Miguel con los formularios reservados. La noticia secundaria de 1851 no acredita una aprobación del compuesto actual ni de una aparición. |
| `confiteor` | `whenToPray` lo sitúa «antes de la comunión». En la Misa corresponde al acto penitencial inicial. No sustituye la absolución sacramental. |
| `litany_of_loreto` | Faltan las tres invocaciones de 2020. En ES se repite «Madre inmaculada» y la oración final es la del Rosario; cotejar el formulario completo y sus traducciones. |
| `litany_of_the_sacred_heart` | ES contiene 32 invocaciones al Corazón frente a las 33 del formulario de referencia. Falta «en quien el Padre se complace». Hay otras variantes de traducción que conviene cotejar. |
| `prayer_to_st_patrick` | Es una invocación al santo, no su Coraza ni su Confesión. No se identificó una edición del texto concreto. |
| `prayer_to_st_rita` | Se localizó un formulario paralelo, pero no su primera edición. Los episodios hagiográficos, las penitencias y la afirmación de cuatro años viviendo solo de la Eucaristía no se han corroborado históricamente. |
| `prayer_to_st_therese` | Es una oración dirigida a Teresa, no escrita por ella. No se verificó una promesa de recibir una rosa como señal necesaria o dentro de un plazo. |
| `armor_of_god` | Paráfrasis orante de Ef 6,10–18, no transcripción de una oración paulina. Autor/edición de la adaptación desconocidos. |

La consigna menciona «Quédate conmigo, Señor», de Padre Pío, pero no figura en `T-002.ids`. No se añadió una entrada fuera de la lista fija.

## Fuentes consultadas

Los enlaces están aquí porque el contrato prohíbe URL dentro de los textos JSON. Las referencias abreviadas de cada ficha permiten relacionarla con estas fuentes.

1. [Penitenciaría Apostólica, Enchiridion Indulgentiarum, cuarta edición, 1999, texto latino oficial](https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html). Se comprobaron los apartados sobre culto eucarístico, comunión, oraciones marianas, san José, letanías, oración de acción de gracias y las normas para las plenarias. El Tantum ergo del Jueves Santo no debe confundirse con la concesión por participar en la procesión eucarística del Corpus.
2. [IGLH, texto latino publicado por la Conferencia Episcopal Eslovaca](https://breviar.kbs.sk/la/docs/smernice_lh.htm), contexto de Laudes; [respuesta de Culto Divino sobre la Liturgia de las Horas](https://www.vatican.va/roman_curia/congregations/ccdds/documents/rc_con_ccdds_doc_20001205_liturgia_horarum_sp.html), contexto de los horarios.
3. [The Catholic Encyclopedia, Ave Regina](https://www.newadvent.org/cathen/02149b.htm), testimonios medievales y ausencia de autor seguro. Su calendario descrito es histórico; no se impone como norma nueva del oficio actual.
4. [Notre-Dame de Paris, Alma Redemptoris Mater](https://www.notredamedeparis.fr/prier/priere-et-meditation-a-notre-dame/alma-redemptoris-mater/), texto de contraste localizado en la búsqueda. La búsqueda de «Alma Missionum Redemptoris Mater» no estableció una fuente del formulario de la app.
5. [Roger Pearse, revisión de la datación de P.Ryl. III 470, 2023](https://roger-pearse.com/weblog/2023/08/24/theotokos-a-review-of-the-scholarship-on-the-paleographical-date-of-p-ryl-iii-407-sub-tuum-praesidium/). Fuente secundaria que remite al catálogo de Roberts y a la discusión paleográfica; no se presenta como examen material propio del papiro.
6. [Redentoristas de Baltimore, An act of spiritual Communion](https://redemptorists.net/news/an-act-of-spiritual-communion), fórmula difundida bajo el nombre de san Alfonso.
7. [Franciscan Media, A Look at the Peace Prayer of St. Francis](https://www.franciscanmedia.org/st-anthony-messenger/a-look-at-the-peace-prayer-of-st-francis/), investigación de Christian Renoux resumida por Jack Wintz OFM.
8. [The Catholic Encyclopedia, Veni Creator Spiritus](https://www.newadvent.org/cathen/15341a.htm), tradición manuscrita y atribución a Rabano Mauro.
9. [The Catholic Encyclopedia, Adoro Te Devote](https://www.newadvent.org/cathen/01154b.htm); [New Catholic Encyclopedia, Adoro Te Devote](https://www.encyclopedia.com/religion/encyclopedias-almanacs-transcripts-and-maps/adoro-te-devote), discusión de la autoría localizada en la búsqueda. No se asegura una fecha exacta de composición.
10. [Benedicto XVI, audiencia sobre santa Juliana de Cornillon, 17 de noviembre de 2010](https://www.vatican.va/content/benedict-xvi/en/audiences/2010/documents/hf_ben-xvi_aud_20101117.html), contexto de la institución del Corpus y encargo del oficio a Tomás.
11. [The Catholic Weekly, origen de las Alabanzas divinas](https://catholicweekly.com.au/what-is-the-origin-of-divine-praises-we-say-in-benediction/), núcleo de Felici y añadidos. El PDF de normas de exposición de la Arquidiócesis de Sídney apareció en la búsqueda, pero devolvió 403 al abrirlo; se utilizó esta alternativa legible para el dato histórico.
12. [León XIII, Annum sacrum, 25 de mayo de 1899](https://www.vatican.va/content/leo-xiii/en/encyclicals/documents/hf_l-xiii_enc_25051899_annum-sacrum.html), para distinguir la consagración histórica de la fórmula de la app.
13. [León XIII, Quamquam pluries, 15 de agosto de 1889](https://www.vatican.va/content/leo-xiii/en/encyclicals/documents/hf_l-xiii_enc_15081889_quamquam-pluries.html), para distinguir Ad te, beate Ioseph de la invocación corta.
14. [Diócesis de Saint Petersburg, Prayer to St. Jude](https://www.dosp.org/our-faith/prayers/prayer-to-st-jude/) y [Santuario Nacional de San Judas de los Claretianos](https://forms.shrineofstjude.org/site/PageServer?pagename=ssj_shrine_celebrate), testimonios de la devoción localizados en la búsqueda; no prueban la primera edición de la abreviación.
15. [Preces Latinae, Si quaeris miracula](https://www.preces-latinae.org/thesaurus/Sancti/SAntoniusDePadova/SiQuaeris.html), formulario distinto de la invocación corta de la app, localizado en la búsqueda.
16. [Xavier University, Jesuit Resource, Ignatian Prayers](https://www.xavier.edu/jesuitresource/online-resources/prayer-index/ignatian-prayers), texto Suscipe, cotejado con la conclusión que falta en la app; EE 234 es su lugar en la obra.
17. [Capilla de la Medalla Milagrosa, rue du Bac, relato e inscripción](https://www.chapellenotredamedelamedaillemiraculeuse.com/langues/english/apparitions-et-la-medaille-miraculeuse-gb/). La página es fuente del relato devocional; no sustituye un decreto de discernimiento examinado.
18. [Diócesis de Jaén, Carta del Obispo: ¡Bendita sea tu pureza!, 7 de diciembre de 2018](https://diocesisdejaen.es/carta-del-obispo-bendita-sea-tu-pureza/), uso devocional y comentario, sin adjudicar autor al poema.
19. [USCCB, The O Antiphons of Advent](https://www.usccb.org/prayers/o-antiphons-advent) y [reflexión sobre las antífonas](https://www.usccb.org/prayer-and-worship/liturgical-year-and-calendar/advent/reflection-on-the-o-antiphons), diferencia entre antífonas e himno.
20. [St. Michael Center, The Chaplet of St. Michael](https://www.catholicexorcism.org/assisting-laity-prayers/the-chaplet-of-st.-michael), reproducción atribuida a EWTN. El enlace antiguo directo de EWTN devolvió 404. No se examinó el decreto de 1851 ni se atribuye a esta fuente secundaria valor de reconocimiento canónico de una aparición.
21. [CDF, Inde ab aliquot annis, AAS 77 (1985), 1169–1170, texto latino oficial](https://www.vatican.va/roman_curia/congregations/cfaith/documents/rc_con_cfaith_doc_19850924_exorcism_lt.html), especialmente el punto 2. El encabezado del documento indica 29 de septiembre, aunque el nombre del archivo contiene 19850924; se citó por título y referencia de AAS.
22. [USCCB, Newsletter del Comité de Culto Divino, julio de 2011, pp. 26–27 del boletín](https://www.usccb.org/resources/newsletter-2011-07.pdf), distinción entre acto penitencial y absolución sacramental y comentario al gesto del Confiteor. La página HTML del GIRM devolvió 403; el boletín PDF fue legible.
23. [Padres Marianos, The Chaplet of Divine Mercy](https://thedivinemercy.org/articles/chaplet-divine-mercy), Diario 474–476, secuencia y cierre opcional del 950; [All-Occasion Prayers](https://thedivinemercy.org/articles/all-occasion-prayers), 84 y 1319; [Our Hope is in the Divine Mercy](https://thedivinemercy.org/articles/our-hope-divine-mercy), 186–187. [USCCB, Chaplet of Divine Mercy](https://www.usccb.org/prayers/chaplet-divine-mercy) fue accesible en los extractos de búsqueda; la apertura directa devolvió 403.
24. [The Catholic Encyclopedia, Litany of Loreto](https://www.newadvent.org/cathen/09287a.htm); [carta oficial de Culto Divino, 20 de junio de 2020](https://press.vatican.va/content/salastampa/es/bollettino/pubblico/2020/06/20/letan.html), las tres invocaciones y su posición.
25. [Bonaventure Hammer, General Catholic Devotions, Project Gutenberg 33676](https://www.gutenberg.org/files/33676/33676-h/33676-h.htm). Se leyó el texto: portada con copyright de Benziger de **1909**, aprobación provincial de 1908 e imprimátur de 1909. La edición se cita como 1909, no confundiendo las dos fechas. Contiene la letanía del Sagrado Corazón con su noticia de aprobación y la oración por los difuntos tras la Letanía de Todos los Santos. Sus antiguas indulgencias expresadas en días no se trasladaron al contenido nuevo.
26. [Dunleer Parish, Arquidiócesis de Armagh, St Patrick’s Breastplate](https://www.dunleerparish.ie/2018/03/st-patricks-breastplate/), texto de contraste localizado en la búsqueda; no acredita la oración distinta del archivo.
27. [St. Rita of Cascia, Novenas to St. Rita](https://www.stritaofcascia.com/prayers-and-novenas/novenas-to-st-rita/), primer formulario, cotejado como testimonio de circulación. Es un sitio devocional, no un decreto ni una edición crítica de la vida de la santa; no se lo presenta como santuario oficial.
28. [Society of the Little Flower, Prayers to St. Thérèse](https://www.littleflower.org/prayers-sharing/prayers-to-st-therese/), sección My Novena Rose Prayer.
29. [The Catholic Encyclopedia, Te Deum](https://www.newadvent.org/cathen/14468c.htm), discusión de las atribuciones a Ambrosio/Agustín y Nicetas; no se transforma una hipótesis de autoría en certeza.

## Datos no verificados y preguntas al coordinador

- No se ha establecido la edición y licencia de las traducciones modernas ya presentes en la app. En particular, no se declararon de dominio público las traducciones del Diario, del Misal o de la Liturgia de las Horas por el mero hecho de ser oraciones religiosas. Se señala en `textNote`; resolver los cuerpos requiere otro alcance.
- No se localizaron primera edición/autor de varias fórmulas abreviadas. La ausencia de una fuente localizada no demuestra que nunca haya existido; no se afirma tal inexistencia.
- La datación del papiro, la autoría del Adoro te y la del Te Deum se mantienen como discutidas. La aprobación de una devoción no se usa para certificar automáticamente una aparición o una versión alterada.
- ¿Abre el coordinador un encargo para sustituir los cuerpos con procedencia no verificada o con alteraciones, especialmente Adoro te, Suscipe, alma_missionum y la corona de San Miguel? Este encargo solo permite crear metadatos.
- ¿Puede habilitarse en futuros validadores un modo de entrega parcial sin omitir las comprobaciones de cada entrada? T-002 exige la lista completa y por ello no permite un push válido al llegar a diez fichas.
