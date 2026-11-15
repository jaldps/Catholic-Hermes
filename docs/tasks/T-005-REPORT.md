# T-005 · Informe de Codex

**EN CURSO: entrega parcial ordenada, preguntas 1–20 de 51.** No se declara completo el encargo. El siguiente lote empieza en la pregunta 21. Solo se crean `app/src/main/assets/questions/deeper-a.json` y este informe.

## Contenido entregado

Veinte bloques completos con `explanation`, `scripture`, `tradition`, `magisterium`, `objection`, `analogy`, `synthesis`, en ES/EN/PT-BR; fuentes en 3–5 entradas por pregunta. Redacción y paráfrasis propias, sin citas extensas de traducciones modernas. No se tocaron la pregunta, respuesta, fundamento ni aclaración existentes.

Se distinguen sufrimiento físico/moral, conocimiento/coacción, condenación/culpabilidad subjetiva, fe/emoción, razonamiento filosófico/testimonio histórico, gratuidad/necesidad de Dios, petición/garantía, providencia/aprobación del mal, unidad bíblica/lectura literalista y gracia/autosuficiencia moral. Las analogías declaran sus límites; las objeciones no se presentan como si fueran absurdas.

## Validación

```text
$ python3 docs/tasks/tools/validate_questions.py app/src/main/assets/questions/deeper-a.json 1 51 --partial
WARN  partial delivery: 20/51 questions
20 entries · 0 errors · 1 warnings
```

La única advertencia es la entrega parcial. Totales de palabras por idioma según el mismo criterio del validador:

| Pregunta | ES | EN | PT |
|---|---:|---:|---:|
| 1 | 460 | 425 | 436 |
| 2 | 451 | 439 | 440 |
| 3 | 448 | 424 | 419 |
| 4 | 452 | 432 | 419 |
| 5 | 457 | 446 | 438 |
| 6 | 456 | 433 | 442 |
| 7 | 471 | 444 | 439 |
| 8 | 457 | 433 | 432 |
| 9 | 478 | 437 | 446 |
| 10 | 469 | 443 | 439 |
| 11 | 469 | 439 | 438 |
| 12 | 474 | 441 | 434 |
| 13 | 462 | 437 | 435 |
| 14 | 464 | 427 | 430 |
| 15 | 470 | 435 | 429 |
| 16 | 470 | 438 | 430 |
| 17 | 438 | 426 | 418 |
| 18 | 437 | 426 | 422 |
| 19 | 424 | 415 | 410 |
| 20 | 433 | 428 | 419 |

No se modificó el validador. Incidencia: su patrón `\bTODO\b`, aplicado sin distinguir mayúsculas, también rechaza la palabra española/portuguesa «todo» dentro de una frase terminada. Se reformularon tres apariciones para poder validar; convendría que el coordinador restringiera el patrón a un marcador real. Esto no bloquea la entrega y no se corrigió fuera del alcance autorizado.

No se compiló Android; el protocolo reserva la integración y compilación al coordinador.

## Fuentes realmente consultadas

### Catecismo oficial

Se descargaron las secciones del [índice oficial en inglés](https://www.vatican.va/archive/ENG0015/_INDEX.HTM) para consultar localmente los párrafos, distinguiéndolos de las notas al pie que también llevan números. Se leyeron los lugares citados; no se consideró una coincidencia de número en una nota como verificación de un párrafo.

- [CIC 31–35](https://www.vatican.va/archive/ENG0015/__PA.HTM), [36–38](https://www.vatican.va/archive/ENG0015/__PB.HTM), [156 y 160](https://www.vatican.va/archive/ENG0015/__PX.HTM).
- [CIC 293–294 y 309–314](https://www.vatican.va/archive/ENG0015/__P19.HTM).
- [CIC 600](https://www.vatican.va/archive/ENG0015/__P1O.HTM), conocimiento eterno y respuestas libres.
- [CIC 846–848](https://www.vatican.va/archive/ENG0015/__P29.HTM), salvación y evangelización.
- [CIC 1033–1037](https://www.vatican.va/archive/ENG0015/__P2O.HTM), infierno; CIC 1857–1861, condiciones del pecado mortal y juicio de las personas.
- [CIC 1730](https://www.vatican.va/archive/ENG0015/__P5M.HTM), [1731–1735](https://www.vatican.va/archive/ENG0015/__P5N.HTM), libertad y condicionamientos.
- [CIC 2096–2097](https://www.vatican.va/archive/ENG0015/__P7D.HTM), adoración.
- [CIC 2734–2741](https://www.vatican.va/archive/ENG0015/__P9R.HTM), oración de petición.
- [CIC 121–123](https://www.vatican.va/archive/ENG0015/__PR.HTM), valor del Antiguo Testamento.

### Padres y doctores: obra y lugar cotejados

- [Agustín, Enchiridion 11](https://www.newadvent.org/fathers/1302.htm): privación y posibilidad de bien surgido del mal. No se usa para explicar por qué ocurrió una tragedia individual.
- [Agustín, La ciudad de Dios V,9](https://www.newadvent.org/fathers/120105.htm): defensa conjunta de presciencia y voluntad libre frente a la alternativa de Cicerón.
- [Agustín, La ciudad de Dios XXI,23](https://www.newadvent.org/fathers/120121.htm): argumento sobre la eternidad en Mt 25,46, sin convertir cada descripción física de Agustín en dogma.
- [Agustín, Confesiones I,1,1](https://www.newadvent.org/fathers/110101.htm): búsqueda, invocación y gracia.
- [Agustín, Carta 130 a Proba, 8,17 y 9,18](https://www.newadvent.org/fathers/1102130.htm): el deseo se ejercita en la oración. Se verificó la ubicación: el pasaje sobre ejercitar el deseo está en 8,17, no se inventó otra numeración.
- [Tomás de Aquino, Suma Teológica I, q. 2, a. 3](https://www.newadvent.org/summa/1002.htm): vías, objeciones y respuestas.
- [Tomás de Aquino, Suma Teológica I, q. 44, a. 4](https://www.newadvent.org/summa/1044.htm): comunicación de bondad, sin provecho necesario para Dios.
- [Ireneo, Contra las herejías IV,9,1–2](https://www.newadvent.org/fathers/0103409.htm): un mismo autor de ambas alianzas; no se le atribuye una exégesis particular de Amalec que no está en ese lugar.
- [Justino, Primera Apología 46](https://www.newadvent.org/fathers/0126.htm): acción del Logos antes de Cristo. Se limita el argumento a lo que Justino realmente trata, sin atribuirle las distinciones modernas completas del ateísmo.

### Documentos y Escritura

- [Lumen gentium 16](https://www.vatican.va/archive/hist_councils/ii_vatican_council/documents/vat-ii_const_19641121_lumen-gentium_en.html), texto completo del apartado: la actuación de la gracia no se limita al conocimiento explícito del Evangelio; no se enseña salvación por mérito independiente.
- [Dei Verbum 15](https://www.vatican.va/archive/hist_councils/ii_vatican_council/documents/vat-ii_const_19651118_dei-verbum_en.html), preparación veterotestamentaria.
- [Benedicto XVI, Verbum Domini 42](https://www.vatican.va/content/benedict-xvi/en/apost_exhortations/documents/hf_ben-xvi_exh_20100930_verbum-domini.html), leído íntegro: pasajes difíciles, contexto y lectura cristológica. No se cita únicamente su título en el índice.
- [Biblia Douay-Rheims, texto completo de consulta, Gutenberg 8300](https://www.gutenberg.org/ebooks/8300): se cotejaron los pasajes bíblicos de los diez bloques. La app recibe paráfrasis propias, no el texto ni las notas editoriales de esa edición. Se tuvieron presentes las diferencias de numeración: salmos 18/19, 49/50 y 138/139, nombres antiguos de los libros de Samuel/Reyes y variantes de versificación de Marcos/Eclesiástico. Las referencias del JSON siguen la numeración moderna habitual de las respuestas de la app.

## Observaciones sobre las respuestas existentes (sin modificarlas)

1. **Pregunta 1:** «Dios no quiera el sufrimiento inocente» pide precisión para que no parezca negar la entrega libre de Cristo y el designio pascual. Es correcto rechazar el mal como fin o una atribución automática de culpa al enfermo; no conviene confundir eso con que Dios nunca pueda querer un bien que incluya asumir libremente sufrimiento. La ampliación distingue los planos y no pretende conocer el motivo de cada tragedia.
2. **Pregunta 2:** el padre que predice lo que hará su hijo solo ofrece conocimiento humano falible. La respuesta señala su límite, pero la ampliación debe evitar que el ejemplo se tome por explicación de la infalibilidad divina. Se añadió la distinción lógica y la referencia real a CIC 600.
3. **Pregunta 3:** «rechazo sostenido» no debe interpretarse como requisito de muchos años o numerosas faltas. CIC 1857–1861 y 1037 hablan de pecado mortal y persistencia sin arrepentimiento hasta el final, con atención a conocimiento y consentimiento. La ampliación lo precisa; no se decide la condenación de individuos.
4. **Pregunta 7:** errata española «Recocer el misterio» → «Reconocer el misterio». Además, «sin prometer que recibiremos al pie de la letra» podría entenderse como negación de las promesas evangélicas: conviene explicar su contexto de fe, comunión y voluntad del Padre, no descartarlas. Se desarrolló esa lectura sin alterar la respuesta.
5. **Pregunta 8:** el árbitro «no puede detener el partido sin destruir la libertad» es una premisa falsa en el ejemplo humano: puede sancionar o detener un daño sin anular la capacidad de elegir. No debe usarse para insinuar que proteger a una víctima suprime la libertad del agresor. La ampliación usa otra analogía y afirma la responsabilidad de protección.
6. **Pregunta 8:** «Dios no manda cometer guerras» y la afirmación general de que la Biblia «los condena» requieren distinguir guerras injustas/agresión y legítima defensa, además de la cuestión textual que aborda la pregunta 9. No se debe insinuar que toda defensa armada es intrínsecamente inmoral; CIC 2309 contiene condiciones estrictas de legítima defensa. La ampliación habla expresamente de guerra injusta y abuso.
7. **Pregunta 10:** la etiqueta «objeción protestante más fuerte» no debe tratarse como posición uniforme de todos los protestantes. La objeción se desarrolla sin una atribución confesional general; se evita también confundir gracia con autosuficiencia moral.

## Dudas y límites

- Las preguntas 21–51 **todavía no están entregadas**. Se leerán sus respuestas actuales y se verificarán las fuentes de cada grupo antes de continuar.
- Ninguna teoría filosófica particular sobre presciencia/libertad se presenta como la única explicación dogmática.
- No se pretende resolver definitivamente el sufrimiento inocente, el ocultamiento divino o cada mandato bélico antiguo. Reconocer el límite no equivale a atribuir contradicciones al Magisterio ni a llamar buenas las atrocidades.
- No se proporcionan diagnósticos ni garantías de curación. Las indicaciones de protección y ayuda no están condicionadas a aceptar una explicación religiosa.
- No hay preguntas que bloqueen la revisión de este lote. Las observaciones de contenido existente corresponden al coordinador, dado que T-005 prohíbe modificar esos campos.

## Ampliación 11–20: comprobaciones y fuentes añadidas

Se desarrolla la relación Escritura/Tradición/Magisterio, la formación del canon, la diversidad antigua de listas, la inspiración e interpretación, creación/evolución, pasajes sobre esclavitud, historicidad evangélica, apócrifos y desarrollo doctrinal. No se emplean citas literales de los Padres: son paráfrasis propias con obra y lugar.

Fuentes efectivamente leídas:

- Catecismo oficial: [66–67](https://www.vatican.va/archive/ENG0015/__PH.HTM), [80–83](https://www.vatican.va/archive/ENG0015/__PL.HTM), [85–86 y 94](https://www.vatican.va/archive/ENG0015/__PM.HTM), [105–107](https://www.vatican.va/archive/ENG0015/__PP.HTM), [109–119](https://www.vatican.va/archive/ENG0015/__PQ.HTM), [120 y 126](https://www.vatican.va/archive/ENG0015/__PR.HTM), [283](https://www.vatican.va/archive/ENG0015/__P19.HTM), [366](https://www.vatican.va/archive/ENG0015/__P1B.HTM), [390 y 404](https://www.vatican.va/archive/ENG0015/__P1C.HTM), [2414](https://www.vatican.va/archive/ENG0015/__P8B.HTM).
- [Dei Verbum 8–12 y 19](https://www.vatican.va/archive/hist_councils/ii_vatican_council/documents/vat-ii_const_19651118_dei-verbum_en.html), texto de los apartados, no solo índices.
- [Pío XII, Humani generis 36–38](https://www.vatican.va/content/pius-xii/en/encyclicals/documents/hf_p-xii_enc_12081950_humani-generis.html): se recoge expresamente la restricción sobre poligenismo de §37; no se presenta como una autorización irrestricta ni se afirma su derogación. Se distingue investigación sobre el cuerpo y creación inmediata del alma.
- [Ireneo, Contra las herejías III,3,1–3](https://www.newadvent.org/fathers/0103303.htm), transmisión pública de la fe; [III,1,1](https://www.newadvent.org/fathers/0103301.htm), recepción de los Evangelios y vinculaciones apostólicas, sin convertir su testimonio en demostración automática de todas las fechas o autorías debatidas.
- [Atanasio, Carta festal 39, apartados 4–7](https://www.newadvent.org/fathers/2806039.htm): lista neotestamentaria de 27 libros, pero lista veterotestamentaria distinta del canon católico actual. Se indica esa diferencia expresamente.
- [Agustín, La doctrina cristiana II,8](https://logoslibrary.org/augustine/doctrine/208.html): se verificó la presencia de Tobías, Judit, Macabeos, Sabiduría y Eclesiástico. No se adopta su conjetura sobre autoría común de Sabiduría/Eclesiástico ni se equipara mecánicamente su recuento de libros al moderno.
- [Agustín, Carta 82 a Jerónimo, 1,3](https://www.newadvent.org/fathers/1102082.htm): dificultades de transmisión, traducción o comprensión; no se inventan armonizaciones de relatos.
- [Tomás de Aquino, Suma Teológica I, q. 90, a. 2](https://www.newadvent.org/summa/1090.htm), alma y creación, sin atribuirle genética moderna; [I, q. 1, a. 10, ad 3](https://www.newadvent.org/summa/1001.htm), metáfora y sentido literal.
- [Gregorio de Nisa, Homilía IV sobre Eclesiastés, comentario a Ecl 2,7](https://earlychurchtexts.com/public/gregoryofnyss_ecclesiastes_slavery.htm): crítica a poseer personas. Se consultó la traducción de Andrew Maguire para verificar el argumento, sin copiarla; no se atribuye ese rechazo con idéntico alcance a cada Padre antiguo.
- [Eusebio, Historia eclesiástica III,25,1–7](https://www.newadvent.org/fathers/250103.htm): recepción, discusión y rechazo de escritos. El pasaje menciona un evangelio de Tomás, no el de María. No se identifica sin más el Tomás referido con cada versión conservada.
- [Evangelio de Tomás, dicho 1](https://www.earlychristianwritings.com/thomas/gospelthomas1.html), solo el texto, no las opiniones de usuarios de esa página; [Evangelio de María, capítulos 5 y 9](https://www.earlychristianwritings.com/text/gospelmary.html), visión y disputa. Se describen muy brevemente, sin reproducir traducciones modernas, inventar fechas exactas ni atribuir la autoría al apóstol o a María Magdalena como hecho demostrado. La condición fragmentaria y la atribución nominal no prueban inspiración ni falsedad de cada detalle.
- [Vicente de Lérins, Commonitorium 23, §§54–59](https://www.newadvent.org/fathers/3506.htm): crecimiento conservando identidad, no simple autorización de cualquier innovación.
- Escritura: cotejo de los pasajes añadidos en la Biblia de consulta ya indicada. También se comprobó la diferencia de numeración de 2 Ts 2,15 moderno / 2 Ts 2,14 en Douay-Rheims; no se confundió la bendición del versículo siguiente con la exhortación a conservar las tradiciones.

Observaciones adicionales sobre las respuestas existentes, sin cambiarlas:

8. **Pregunta 15:** la apertura a investigar el origen corporal no equivale a que cualquier hipótesis sobre Adán, pecado original o poligenismo sea doctrinalmente indiferente. La ampliación cita CIC 390/404 y Humani generis 37 y no convierte a Adán en un mero símbolo colectivo ni en una afirmación genética mensurable por el Catecismo.
9. **Pregunta 17:** la analogía del enfermo de gripe que dice «me estoy muriendo» y no requiere urgencias es clínicamente inadecuada sin información: una gripe también puede ser grave. No corresponde deducir que no hay que buscar ayuda por una frase o diagnóstico supuesto. Se emplea un mapa como analogía, sin dar instrucciones médicas.
10. **Pregunta 19:** no conviene sugerir mediante la carta escolar en «papel moderno» que se ha demostrado ya una falsificación concreta de Tomás o María. La ampliación separa atribución, recepción, inspiración y utilidad histórica, y evita una cronología no documentada.

Límites: no se ofrece una armonización exhaustiva de cada diferencia entre Evangelios, una solución científica al pecado original ni un catálogo completo de manuscritos apócrifos. Las explicaciones no reemplazan esos estudios especializados y no presentan esos problemas como ya resueltos.
