# T-006 · Informe de Codex

## Entrega completa: preguntas 52–101

Se añaden **las 50 preguntas**, en orden, con siete secciones completas y fuentes en ES/EN/PT-BR. No se modifican las preguntas, respuestas, fundamentos o aclaraciones de Kotlin. La base es `origin/CatholicGPT` (`4f302d7`, incorporado por merge). Primera entrega parcial: `c8df15d`. Se completa la continuación pedida en `T-006-REVIEW.md`. Entregas parciales posteriores: `71a103e` (52–71), `f975400` (52–81) y `c4f438a` (52–91).

Son explicaciones originales apoyadas en fuentes leídas, no nuevas oraciones. Las alusiones bíblicas y patrísticas son paráfrasis, sin comillas que simulen citas literales. Las analogías incluyen su límite y no se ofrecen como demostraciones de la eficacia sacramental.

## Validación ejecutada

```text
$ python3 docs/tasks/tools/validate_questions.py app/src/main/assets/questions/deeper-b.json 52 101
50 entries · 0 errors · 0 warnings
```

No hay avisos de extensión. Se conserva el contrato JSON y los números 52–101, sin huecos. `git diff --check` también finaliza sin errores. Se revisaron las localizaciones finales y las tres versiones; no se cambian Kotlin, APK ni encargos cerrados.

## Fuentes consultadas

Los números del Catecismo se cotejaron en el [texto oficial del Vaticano](https://www.vatican.va/archive/ENG0015/_INDEX.HTM). Las referencias bíblicas se comprobaron en la [Biblia Douay-Rheims de dominio público](https://www.gutenberg.org/ebooks/8300), atendiendo a las diferencias de numeración; no se copian sus notas polémicas ni traducciones bíblicas modernas.

| Pregunta | Fuentes y comprobación |
|---|---|
| 52 · Sacrificio de la Misa | CIC 1362–1367; Hb 10,10-14; Lc 22,19-20; 1 Co 11,23-26. [Tomás de Aquino, *Suma Teológica* III, q. 83, a. 1, incluido ad 1](https://www.newadvent.org/summa/4083.htm): representación sacramental de la Pasión, participación en sus frutos e identidad de la víctima. Se utiliza esta localización comprobada en lugar de una numeración de homilía que no se pudo fijar con igual precisión. |
| 53 · Transubstanciación | CIC 1376–1377 y 1381; Mt 26,26-28; 1 Co 11,23-29; Jn 6,53-56. [Tomás, III, q. 75, a. 5, ad 2](https://www.newadvent.org/summa/4075.htm): los sentidos perciben propiedades reales y la fe no exige negar esa percepción. |
| 54 · Confesión | CIC 1441–1445 y 1451–1453; Jn 20,21-23; 2 Co 5,18-20; St 5,14-16. [Juan Crisóstomo, *Sobre el sacerdocio* III,5](https://www.newadvent.org/fathers/19223.htm): aplicación ministerial del perdonar y retener, sin convertir al sacerdote en fuente autónoma de misericordia. |
| 55 · Ministro pecador | CIC 1127–1128 y 1584; 1 Co 3,5-7; 2 Co 4,7; Jn 20,21-23. [Agustín, *Tratados sobre Juan* 6,7–8](https://www.newadvent.org/fathers/1701006.htm): Cristo conserva el poder de bautizar aunque actúen distintos ministros. Validez no se confunde con licitud, santidad ni continuidad en un cargo. |
| 56 · Bautismo infantil | CIC 1250–1252 y 1255; Jn 3,5; Col 2,11-12; Hch 16,15.33. [Cipriano, carta a Fido, §2](https://www.newadvent.org/fathers/050658.htm): carta 64 en otra numeración, **58 en ANF**. Se explica esta diferencia, sin inventar niños concretos en los relatos de bautismos de casas enteras. |
| 57 · Niños fallecidos sin bautismo | CIC 1257 y 1261; Mc 10,13-16; Mt 18,14; 1 Tm 2,4. [Gregorio Nacianceno, *Discurso* 40,23](https://www.newadvent.org/fathers/310240.htm): se distingue su opinión antigua de una definición de la Iglesia. La esperanza eclesial no se presenta como condenación ni como información individual revelada. |
| 58 · Domingo | CIC 2180–2183 y 1857–1859; Hch 2,42; Hch 20,7; Hb 10,24-25. [Justino, *Primera Apología* 67](https://www.newadvent.org/fathers/0126.htm): reunión, Palabra, Eucaristía y ayuda a necesitados. |
| 59 · Preparación para comulgar | CIC 1385 y 1395–1396; 1 Co 10,16-17; 1 Co 11,27-29; Mt 5,23-24. Justino, *Primera Apología* 66, en el enlace anterior. [Canon 844 §§3–4](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib4-cann834-878_en.html) y [canon 916](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib4-cann879-958_en.html), texto oficial completo. No se convierten sus casos precisos en intercomunión general. |
| 60 · Sacramentales | CIC 1667–1670, 2111 y 1128; Nm 6,24-26; Mc 6,13; Hch 19,11-12. [Tertuliano, *De corona* 3](https://ccel.org/ccel/tertullian/corona/anf03.iv.vi.iii.html): señal de la cruz en actividades cotidianas. Se lo identifica como autor cristiano antiguo, sin atribuirle condición de santo o doctor. No se equipara cada uso de aceite con la unción sacramental. |
| 61 · Base bíblica del purgatorio | CIC 1030–1032; 1 Co 3,11-15; Ap 21,27; 2 M 12,43-46. [Gregorio Magno, *Diálogos* IV,39](https://www.tertullian.org/fathers/gregory_04_dialogues_book4.htm): faltas leves y purificación; también admite una lectura de 1 Co 3 referida a tribulaciones presentes. No se elevan sus imágenes o ejemplos a definición dogmática. |

| 62 · Cruz y purificación | CIC 1030–1031 y 1472; Hb 10,14; Flp 1,6; 1 Co 3,11-15. Gregorio Magno, *Diálogos* IV,39, ya enlazado. Se explica purificación como fruto de la cruz y se descarta una cuota inevitable de sufrimiento. |
| 63 · Fe y obras | CIC 1989, 1993, 2001 y 2008–2010; Ef 2,8-10; Ga 5,6; St 2,14-26; Rm 3,28. [Agustín, *La gracia y el libre albedrío* XVII,33](https://www.newadvent.org/fathers/1510.htm): gracia operante y cooperante; no autosalvación ni caricatura de la posición protestante. |
| 64 · Perdón y responsabilidad | CIC 982, 1459, 1037 y 847; Lc 23,39-43; Lc 19,8-10; Lc 15,11-32. [Cirilo de Jerusalén, *Catequesis* II,14](https://www.newadvent.org/fathers/310102.htm): exhortación a no desesperar de la conversión, sin certificar detalles extrabíblicos de sus ejemplos ni destinos individuales. |
| 65 · Pecado original | CIC 390 y 404–405; Gn 3; Rm 5,12-21; 1 Co 15,21-22. [Agustín, *Enquiridión* 43 y 50](https://www.newadvent.org/fathers/1302.htm): condición original y faltas personales, redención en Cristo. **Se leyeron también 46–48**: sus hipótesis sobre la culpa de antepasados inmediatos no se presentan como doctrina católica actual ni se ocultan bajo una atribución falsa. |
| 66 · Mortal y venial | CIC 1735 y 1855–1863; 1 Jn 5,16-17; St 1,14-15; Mc 7,21-23. [Tomás, I-II, q. 88, a. 1](https://www.newadvent.org/summa/2088.htm): orientación al fin último; su término «irreparable» no limita el poder sanador de Dios. Se distingue tentación involuntaria de consentimiento, sin diagnósticos individuales. |
| 67 · Esperanza de salvación | CIC 1033–1037 y 1821; 1 Tm 2,3-6; 2 P 3,9; Mt 25,46; Rm 11,32. [Tomás, I, q. 19, a. 6, ad 1](https://www.newadvent.org/summa/1019.htm): se leyeron las tres interpretaciones y la distinción antecedente/consecuente, sin convertir sus ejemplos penales medievales en norma actual ni la distinción en una cifra de salvados. |
| 68 · Quienes no conocen a Cristo | CIC 846–848; Jn 1,9; Rm 2,14-16; Hch 10,34-35. [Justino, *Primera Apología* 46](https://www.newadvent.org/fathers/0126.htm): universalidad del Verbo, no un catálogo infalible de destinos ni una fuente salvadora independiente de Cristo. |
| 69 · Oración por los difuntos | CIC 958 y 1032; 2 M 12,43-46; 1 Co 3,11-15; 2 Tm 1,16-18. [Agustín, *Confesiones* IX,13,35–37](https://www.newadvent.org/fathers/110109.htm): oración por Mónica y Patricio. [Tomás, II-II, q. 83, a. 2](https://www.newadvent.org/summa/3083.htm): providencia, medios y oración; se aplica su argumento general, sin atribuirle una descripción individual de los difuntos. |
| 70 · Indulgencias | CIC 1471–1473 y 1478–1479; Mt 16,19; 2 Co 2,6-11; 1 Co 12,26. Agustín, *Enquiridión* 110 (enlace arriba): solidaridad espiritual, no concesión actual. [Trento, sesión XXV, decreto de indulgencias, **4-XII-1563**](https://www.papalencyclicals.net/councils/trent/twenty-fifth-session.htm): reconocimiento y mandato de corregir ganancias indebidas y abusos. [*Enchiridion Indulgentiarum*, 1999, norma **20 §1**](https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html): obra prescrita y condiciones de la plenaria, incluido desapego del pecado venial. No se inventa una nueva concesión. |
| 71 · Homosexualidad y doctrina moral | CIC 2357–2359, leídos en el texto oficial aunque tres párrafos contiguos no aparecían en el índice local; Gn 1,27; Mt 19,4-6; Rm 1,26-27; Jn 13,34-35. [Tomás, II-II, q. 151, a. 1 y ad 2](https://www.newadvent.org/summa/3151.htm): virtud y consentimiento, sin atribuirle una teoría moderna de orientación sexual. [DDF, *Fiducia supplicans*, **18-XII-2023**, 31 y 38–39](https://www.vatican.va/roman_curia/congregations/cfaith/documents/rc_ddf_doc_20231218_fiducia-supplicans_en.html): bendiciones espontáneas de parejas y límites que impiden confundirlas con matrimonio. |


### Preguntas 72–81 y revisión del coordinador

Se leyó `T-006-REVIEW.md`: el coordinador aprueba las primeras veinte y pide continuar. Se incorporó la nueva base sin modificar sus Biblias, Kotlin o versiones publicadas.

| Pregunta | Fuentes cotejadas y límites |
|---|---|
| 72 · Matrimonio | CIC 1601, 1617, 1643 y 1654; Gn 2,24; Mt 19,4-6; Ef 5,25-32. [Agustín, *El bien del matrimonio*, 3](https://www.newadvent.org/fathers/1309.htm): compañía y matrimonio de ancianos o sin hijos. [Canon 1084 §3](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib4-cann998-1165_en.html): esterilidad no equivale a nulidad. |
| 73 · Relaciones prematrimoniales | CIC 2337, 2353, 2362 y 1735; Gn 2,24; 1 Co 6,18-20; 1 Ts 4,3-5. Tomás, II-II, q. 151, a. 1, ad 2, ya enlazado: consentimiento y castidad, sin culpabilizar a víctimas de violencia. |
| 74 · Anticoncepción y regulación natural | CIC 2366 y 2368–2370. [*Humanae vitae* 10–12 y 14–16](https://www.vatican.va/content/paul-vi/en/encyclicals/documents/hf_p-vi_enc_25071968_humanae-vitae.html), leído también el n. 14 que aparece separado tipográficamente en el HTML. [Tomás, I-II, q. 18, a. 2](https://www.newadvent.org/summa/2018.htm): objeto del acto. Gn 1,28; Gn 2,24; 1 Co 7,3-5. Se distingue finalidad, objeto y terapias con efectos no buscados; no se inventa eficacia clínica. |
| 75 · Fecundación in vitro | CIC 2376–2378; Gn 1,27-28; Sal 139,13-16; Gn 2,24. [*Dignitas personae* 12–16](https://www.vatican.va/roman_curia/congregations/cfaith/documents/rc_con_cfaith_doc_20081208_dignitas-personae_en.html) y [*Donum vitae* II,5](https://www.vatican.va/roman_curia/congregations/cfaith/documents/rc_con_cfaith_doc_19870222_respect-for-human-life_en.html), incluida su afirmación final de la dignidad del hijo nacido. [*Didaché* 2,2](https://www.newadvent.org/fathers/0714.htm): testimonio antiguo, sin atribuirle técnicas actuales. No se usan cifras de 2008 como estadísticas presentes. |
| 76 · Violencia y separación | CIC 1649, 2383 y 1629; cánones 1153 §1, 1154 y 1085 §2, leídos en el enlace del código anterior. [Tomás, II-II, q. 64, a. 7](https://www.newadvent.org/summa/3064.htm): defensa proporcionada, sin instrucciones tácticas. Ef 5,25-29; Mt 2,13-14; 1 Co 7,10-11. La protección urgente no espera un proceso de nulidad ni autorización pastoral. |
| 77 · Indisolubilidad | CIC 1640, 1646, 1649, 1651, 2386 y 1629; Agustín, *El bien del matrimonio*, 3. Mt 19,3-9; Mc 10,2-12; 1 Co 7,10-11; Ef 5,25-33. Se mantiene la precisión «rato y consumado» y se distingue vínculo válido de convivencia; no se promete recuperación del afecto. |
| 78 · Identidad y cuerpo | CIC 364–365. [*Dignitas infinita* 55–60](https://www.vatican.va/roman_curia/congregations/cfaith/documents/rc_ddf_doc_20240402_dignitas-infinita_en.html), incluida la distinción expresa del n. 60 sobre anomalías genitales. [Tomás, I, q. 76, a. 1](https://www.newadvent.org/summa/1076.htm): unidad corporal y espiritual, sin atribuirle diagnósticos actuales. Gn 1,27; 1 Co 6,19-20; Ga 3,28. |
| 79 · Masturbación y consentimiento | CIC 2352, 1735 y 1857. [Agustín, *Confesiones* X,30,41](https://www.newadvent.org/fathers/110110.htm): experiencia durante el sueño y voluntad, sin convertir el pasaje en un estudio clínico. Mt 5,27-28; 1 Co 6,12-20; Ga 5,22-23. No se inventa una prohibición bíblica explícita ni se utiliza el relato de Onán como prueba directa. |
| 80 · Justicia social | CIC 2424 y 2443–2447; Lc 4,18-19; St 5,1-6; Mt 25,34-40. [Crisóstomo, *Homilías sobre Mateo* **50,4**](https://www.newadvent.org/fathers/200150.htm), cotejado en esta edición: exige ayudar antes y junto a los dones para el culto, sin prohibirlos. Se corrigió la numeración preliminar 50,3 al comprobar el comienzo exacto del apartado. |
| 81 · Aborto y autonomía | CIC 2270–2273; *Didaché* 2,2; Gn 1,27; Sal 139,13-16; Lc 1,39-44. [*Evangelium vitae* 62 y 99](https://www.vatican.va/content/john-paul-ii/en/encyclicals/documents/hf_jp-ii_enc_25031995_evangelium-vitae.html): aborto directo y llamada a la misericordia. No se inventan protocolos médicos ni penas canónicas actuales a partir de numeración antigua. |


### Preguntas 82–91

| Pregunta | Fuentes cotejadas y límites |
|---|---|
| 82 · Violación | CIC 2356, 2270–2271; Ex 20,13; Sal 139,13-16; Mt 25,40. [Agustín, *La ciudad de Dios* I,18–19](https://www.newadvent.org/fathers/120101.htm): la violencia sufrida sin consentimiento no destruye la castidad de la víctima. No se traslada su vocabulario antiguo a un diagnóstico de trauma. |
| 83 · Doble efecto | CIC 1750–1756 y 2271; Rm 3,8; Ex 20,13; Lc 10,33-35. Tomás, II-II, q. 64, a. 7, ya enlazado: caso de defensa, no protocolo obstétrico ni identificación del feto con un agresor. No se clasifican procedimientos médicos. |
| 84 · Final de la vida | CIC 2276–2279, leídos completos; Ex 20,13; Mt 25,36; Lc 10,33-35. Tomás, II-II, q. 64, a. 7: principio moral general, sin atribuirle análisis de medicamentos modernos. Se distingue intención de muerte, tratamiento desproporcionado y cuidados ordinarios debidos. |
| 85 · Policía y militares | CIC 2263–2265 y 2310–2313; Lc 3,14; Rm 13,1-4; Hch 5,29. [Agustín, *Carta* 189 a Bonifacio, 4 y 6](https://www.newadvent.org/fathers/1102189.htm): servicio, paz y trato al vencido; no reglas operativas presentes ni aprobación de cada juicio sobre pueblos de su época. |
| 86 · Guerra y paz | CIC 2307–2309 y 2312–2313; Mt 5,38-44; Rm 12,17-21; Rm 13,4. Agustín, *Carta* 189,6, mismo enlace. Condiciones simultáneas, evaluación crítica y deberes humanitarios: no una cuenta no comprobada de guerras justas o injustas. |
| 87 · Pena capital | CIC 2267, verificado en su redacción revisada. [CDF, carta a los obispos, 1-VIII-2018, 7–8](https://www.vatican.va/roman_curia/congregations/cfaith/documents/rc_con_cfaith_doc_20180801_lettera-vescovi-penadimorte_en.html), leída completa: desarrollo y contexto de formulaciones anteriores. Agustín, *La ciudad de Dios* I,21: testimonio de una posición antigua diferente; no autorización privada para matar. Gn 9,6; Rm 13,4; Mt 5,43-48. |
| 88 · Esclavitud y racismo | CIC 1934–1938; Gn 1,27; Ga 3,28; Flm 15-16; St 2,1-9. [Gregorio de Nisa, *Homilías sobre Eclesiastés* IV, comentario a Ecl 2,7](https://earlychurchtexts.com/public/gregoryofnyss_ecclesiastes_slavery.htm), texto del argumento leído, parafraseado, no copiado de su traducción moderna. [Declaración conjunta de dicasterios, 30-III-2023, 6–7](https://press.vatican.va/content/salastampa/en/bollettino/pubblico/2023/03/30/230330b.html), leída completa. No se borra responsabilidad institucional ni se afirma haber cotejado el texto completo de cada bula citada por la declaración. |
| 89 · Riqueza | CIC 2402–2406 y 2446; Mc 10,23-27; Lc 12,15-21; Lc 19,8-10; St 5,1-6. Crisóstomo, *Homilías sobre Mateo* 50,4, enlace anterior: importancia de ganancias honestas, no una cuota de donación que legitime cualquier fortuna. |
| 90 · Migrantes y pobres | CIC 2241 y 2443–2447; Lv 19,33-34; Mt 25,35; Hb 13,2; St 2,14-17. [Benito, *Regla* 53,1 y 15](https://www.gutenberg.org/ebooks/50040), texto del capítulo leído completo. Hospitalidad monástica como testimonio, no un modelo legislativo contemporáneo. |
| 91 · Celibato | CIC 1579–1580; Mt 8,14-15; 1 Tm 3,2; Mt 19,12; 1 Co 7,32-35. [Crisóstomo, *Homilía* 10 sobre la Primera a Timoteo](https://www.newadvent.org/fathers/230610.htm), comentario a 1 Tm 3,2: discusión antigua, sin confundirla con la disciplina episcopal oriental vigente. |


### Preguntas 92–101

| Pregunta | Fuentes cotejadas y límites |
|---|---|
| 92 · Vida consagrada | CIC 915–916 y 931; Mt 19,10-12; 1 Co 7,7-8.32-35; Ef 5,25-32. Benito, *Regla* 58,9-16, capítulo leído completo en Gutenberg 50040: proceso previo a profesión, sin elevar sus detalles disciplinarios antiguos a normas actuales. [Canon 601](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib2-cann573-606_en.html): superiores legítimos y constituciones; no obediencia abusiva. |
| 93 · Ordenación de mujeres | CIC 1577–1578; Mc 3,13-19; Jn 20,17-18; Rm 16,1-4. [*Ordinatio sacerdotalis* 3–4](https://www.vatican.va/content/john-paul-ii/en/apost_letters/1994/documents/hf_jp-ii_apl_19940522_ordinatio-sacerdotalis.html), leído el contexto: carácter definitivo y distinción de dignidad. [*Suplemento de la Suma*, q. 39, a. 1](https://www.newadvent.org/summa/5039.htm), artículo leído: se reconoce expresamente su argumento medieval de subordinación, sin repetirlo como prueba de inferioridad humana. Se cita el Suplemento como tal, sin fingir que es una redacción moderna del Magisterio ni una obra terminada por Tomás. |
| 94 · Familias, mujeres y escucha | CIC 907 y 911; [canon 212 §3](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib2-cann208-329_en.html), leído completo; Hch 18,24-26; Rm 16,1-4; 1 Co 12,12-20. [Crisóstomo, *Homilía* 31 sobre Romanos, comentario a Rm 16,6](https://www.newadvent.org/fathers/210231.htm): María, Priscila y enseñanza, sin trasladar su disciplina de presidencia pública a cada cargo contemporáneo. |
| 95 · Patrimonio | CIC 2403–2404; [cánones 1254 §2 y 1291](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib5-cann1254-1310_en.html); Lc 12,15; Hch 4,32-35; Mt 25,35-40. [Ambrosio, *Sobre los deberes* II,28,137–139](https://www.newadvent.org/fathers/34012.htm): empleo de oro y vasos para rescatar cautivos. No se inventan cifras patrimoniales ni se afirma que cada bien sea invendible. |
| 96 · Corrupción financiera | CIC 2409, 2454 y 827; cánones 1284 y 1287, leídos en el enlace anterior, con distinción de sus destinatarios y del derecho particular. Mt 21,12-13; 1 Tm 6,9-10; St 5,1-6. Crisóstomo, *Homilías sobre Mateo* 50,4: origen honesto de los dones. No se formulan acusaciones contemporáneas sin pruebas. |
| 97 · Título de padre | CIC 239 y 1547; Mt 23,8-12; 1 Co 4,14-15; Flm 10. [Crisóstomo, *Homilías sobre Mateo* 72,3](https://www.newadvent.org/fathers/200172.htm), leído el comentario exacto: paternidad suprema de Dios y humildad; no autoridad humana ilimitada. |
| 98 · Comunidad, templo y ministerio | CIC 1373, 1180 y 1547; Mt 18,19-20; Hch 2,42; Hch 14,23; 1 Co 11,23-26. Justino, *Primera Apología* 67, enlace anterior. **Hch 14,23 en numeración habitual corresponde aquí a 14,22 en la Douay-Rheims consultada**; se comprobó el contenido de la designación de responsables, no solo un número. Se distinguen los modos de presencia de Cristo. |
| 99 · Incoherencia de ministros | CIC 2039, 1782 y 907; canon 212 §3; Mt 23,1-4; Rm 2,21-24; 1 P 5,2-3. Crisóstomo, *Homilías sobre Mateo* 72,1, mismo enlace: responsabilidad agravada del maestro incoherente. No se deduce de ese texto una dispensa de protección o de rendición de cuentas. |
| 100 · Fidelidad de la Iglesia | CIC 823 y 827; Mc 10,42-45; Jn 13,12-17; Ap 2,1-7; Ef 5,25-27. [Agustín, *La doctrina cristiana* I,35,39 y I,36,40–41](https://www.newadvent.org/fathers/12021.htm), cotejadas también las divisiones de capítulo: caridad y corrección de lecturas erróneas. No se inventa un juicio hipotético de Jesús ni una Iglesia ideal que excuse los daños de la comunidad real. |
| 101 · Repetición al orar | CIC 2700–2703, 2708 y 2729; Mt 6,7-13; Lc 11,1-4; Mt 26,44. [Agustín, *Carta* 130 a Proba, 10,19–20](https://www.newadvent.org/fathers/1102130.htm): deseo perseverante, brevedad y atención; no se le atribuye una exposición del Rosario posterior. La distracción involuntaria no se presenta como invalidez de la oración. |

## Precisiones doctrinales y pastorales

- La Misa hace presente el único sacrificio; no añade otra muerte o una redención independiente.
- La transubstanciación no se demuestra mediante análisis de propiedades que la doctrina afirma conservadas. No se finge una prueba de laboratorio.
- La contrición perfecta requiere amor de Dios y propósito firme de confesión cuanto antes; no se presenta como permiso ordinario para prescindir del sacramento.
- La indignidad de un ministro no invalida por sí sola un sacramento ni exime al ministro de responsabilidad. Se distinguen validez, licitud y fructuosidad.
- Las normas de comunión se explican sin juzgar públicamente conciencias. El canon 916 exige conjuntamente razón grave y falta de oportunidad de confesarse, además de contrición perfecta y propósito de confesión pronta.
- El purgatorio es purificación de quienes mueren en gracia y amistad con Dios. No es segunda conversión después de la muerte ni una cronología física revelada.

## Problemas detectados en el texto existente, sin editarlo

1. **Pregunta 60:** «no producen automáticamente la gracia del mismo modo que los sacramentos» puede dar a entender que los sacramentos sí son automáticos o mágicos. Se recomienda expresar la distinción de CIC 1670 junto con CIC 1128: acción de Cristo y disposición para recibir sus frutos. El nuevo Profundizar lo aclara.
2. **Pregunta 61:** comienza «La respuesta directa es no» ante la pregunta «¿Dónde dice…?». Es un desajuste de pregunta/respuesta: debería decir directamente que no aparece la palabra ni una descripción completa, y después exponer los fundamentos.
3. **Pregunta 61:** «con la culpa ya perdonada» necesita precisión. CIC 1030 habla de morir en gracia y amistad con Dios; CIC 1031 admite purificación de faltas leves. No debe entenderse que no puede quedar ninguna falta venial pendiente. La nueva explicación mantiene esa distinción.

4. **Pregunta 86:** «la mayoría de las guerras reales no las cumplen» formula una proporción sin un universo de casos o estudio identificable. Se recomienda sustituirla por la exigencia de evaluación rigurosa de cada conflicto; el nuevo texto no repite esa cuantificación.

## Datos no establecidos y preguntas para el coordinador

No se atribuyen edades no narradas en los bautismos domésticos, una ubicación o duración física del purgatorio ni una sentencia individual sobre niños fallecidos sin bautismo. No se certifican como dogma las opiniones particulares de Gregorio Nacianceno ni cada detalle de Gregorio Magno.

Tampoco se presenta la interpretación de Rm 1 como una discusión resuelta por la sola mención de un versículo ni se identifica atracción con acto voluntario. Se mantiene la enseñanza moral y la exigencia de respeto a las personas, sin promesas de cambiar orientación ni juicios de culpabilidad individual.

No se requiere cambiar el contrato. El coordinador puede integrar el archivo completo y valorar las cuatro correcciones del texto base señaladas arriba. La revisión intermedia indicaba que esperaría a tener las cincuenta entradas para ejecutar el test de contenido 1–101; aquí no se afirma haber ejecutado esa integración ni una compilación Android.
