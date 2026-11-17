# T-023 · Curso bíblico D · Los Salmos, escuela de oración

## Qué hice

Creé `app/src/main/assets/courses/track_psalms_prayer.json` con las **5 lecciones** del curso
`track_psalms_prayer` (nivel `FOUNDATION`, 100 min, icono `AutoStories`), en ES/EN/PT, con el
contrato exacto de `docs/content/M8-CURSOS-SPEC.md` y la anatomía de
`docs/quality/STUDY-CURRICULUM-PLAN.md` §3.

| # | id | Título | Referencias | Fuentes |
|---|---|---|---|---|
| 1 | `psalms_1_israel_jesus` | Los Salmos, oración de Israel y de Jesús | `psa 23` | CIC 2585–2589; Dei Verbum 2 |
| 2 | `psalms_2_praise` | Alabanza | `psa 103` | CIC 2639–2643; CIC 2588 |
| 3 | `psalms_3_lament` | Súplica y lamento | `psa 22`; `mar 15:34` | CIC 2585–2589; CIC 603 |
| 4 | `psalms_4_penance` | Penitencia | `psa 51` | CIC 1430–1433; CIC 2588 |
| 5 | `psalms_5_liturgy` | Los Salmos en la liturgia de la Iglesia | `psa 95` | CIC 1174–1178; CIC 2586; Sacrosanctum Concilium 90 |

Cada lección lleva 3 objetivos observables, `summary`, `historicalContext` (81–89 palabras),
exactamente 3 `teachingSections` (83–100 palabras por cuerpo), `lectio`, `meditatio`, `oratio`,
`contemplatio`, `actionStep` y 3 `reviewQuestions` con respuesta razonada (dos de comprensión y
una de discernimiento).

**Regla del usuario aplicada:** no se compone ninguna oración. El campo `oratio` es una **guía**
que remite al salmo por referencia («Reza despacio el Sal 23…», «Reza despacio el Sal 51…»); no
se reescribe el salmo. Tampoco se copia texto bíblico: las citas bíblicas se resuelven desde la
edición incluida.

## Validador

```
$ python docs/tasks/tools/validate_courses.py app/src/main/assets/courses/track_psalms_prayer.json
5 lessons · 0 errors · 0 warnings
```

Sin avisos: no hay textos idénticos entre idiomas ni patrones prohibidos.

## Fuentes comprobadas

### Catecismo (corpus de la app, `CatechismFullData*.kt`, 2865 párrafos)

Comprobé cada rango contra el texto del Catecismo incluido en la app:

- **2585–2589** · los Salmos como obra maestra de la oración del AT, recogidos en cinco libros;
  oración individual y comunitaria; recitados por Cristo y cumplidos en él; el Salterio como
  Palabra de Dios convertida en oración del hombre (2587 cita *Dei Verbum* 2).
- **2639–2643** · la alabanza: reconoce que Dios es Dios, integra las demás formas, la Iglesia
  canta el Salterio y compone himnos, los cánticos de la liturgia celestial.
- **603** · Jesús en la cruz dice «Dios mío, Dios mío, ¿por qué me has abandonado?» (Mc 15,34;
  **Sal 22,2**).
- **1430–1433** · la penitencia interior, el corazón nuevo, la gracia de la conversión.
- **2588** · las expresiones de oración de los salmos (himno, desamparo, acción de gracias,
  súplica) se hacen realidad viva en la liturgia y en el corazón.
- **2586** · los salmos, oración del pueblo, recitados por Cristo.
- **1174–1178** · la Liturgia de las Horas; 1176 cita *Sacrosanctum Concilium* 90.

Procedencia del corpus: `docs/content/catechism-provenance.json` (Santa Sede, recuperado el
12-XI-2026, con la revisión de 2018 de §2267).

### Documentos conciliares (URLs oficiales, comprobadas con HTTP 200)

- *Dei Verbum* 2 (citado en CIC 2587):
  - ES https://www.vatican.va/archive/hist_councils/ii_vatican_council/documents/vat-ii_const_19651118_dei-verbum_sp.html
  - EN https://www.vatican.va/archive/hist_councils/ii_vatican_council/documents/vat-ii_const_19651118_dei-verbum_en.html
  - PT https://www.vatican.va/archive/hist_councils/ii_vatican_council/documents/vat-ii_const_19651118_dei-verbum_po.html
- *Sacrosanctum Concilium* 90 (citado en CIC 1176):
  - ES https://www.vatican.va/archive/hist_councils/ii_vatican_council/documents/vat-ii_const_19631204_sacrosanctum-concilium_sp.html
  - EN https://www.vatican.va/archive/hist_councils/ii_vatican_council/documents/vat-ii_const_19631204_sacrosanctum-concilium_en.html
  - PT https://www.vatican.va/archive/hist_councils/ii_vatican_council/documents/vat-ii_const_19631204_sacrosanctum-concilium_po.html

## Numeración de los salmos — **desacuerdo con el encargo que hay que resolver**

El encargo pedía indicar la referencia con la **numeración hebrea**, «la de la Biblia en inglés
incluida». Lo he comprobado y **la premisa no se cumple**: las tres ediciones incluidas usan la
numeración de la **Vulgata / Septuaginta** para los salmos 10–147, no la hebrea.

Comprobado sobre `app/src/main/assets/bible/bible_<lang>.json` (ediciones incluidas:
ES *Biblia Platense*, EN *Douay-Rheims-Challoner*, PT *Matos Soares*):

| Salmo (hebreo) | ES incluida | EN incluida | PT incluida | capítulo que muestra la app si se cita «psa N» |
|---|---|---|---|---|
| 23 | 22 | 22 | 22 | 23 = «De Yahvé es la tierra…» (hebreo 24) |
| 103 | 102 | 102 | 102 | 103 = hebreo 104 |
| 22 | 21 | 21 | 21 | 22 = «El Señor es mi pastor» (hebreo 23) |
| 51 | 50 | 50 | 50 | 51 = hebreo 52 |
| 95 | 94 | 94 | 94 | 95 = hebreo 96 |

Evidencia:
- `bible_es.json` 22 = «Yahvé es mi pastor» (hebreo 23); 50 = «Ten piedad» (hebreo 51).
- `bible_en.json` 22 = «The Lord ruleth me» (hebreo 23); 50 = «When Nathan the prophet came…»
  (hebreo 51).
- `bible_pt.json` 22 = «O Senhor me apascenta» (hebreo 23); 50 = «Ao mestre do coro. Salmo. De
  Davide» (hebreo 51).
- El propio código lo sabe: `LectionaryData.kt` documenta que «psalm chapter numbers follow the
  Vulgate» y usa `responsorialPsalmByLanguage` para desplazar los versículos por edición.
- El Catecismo, en cambio, cita los salmos con numeración hebrea (CIC 603 cita «Sal 22,2» para
  Mc 15,34).

`ScriptureReference`/`ScriptureResolver` resuelven el número de capítulo **directamente** contra
la edición incluida (`BibleTextProvider.getVerses`), sin normalización de salmos. Por tanto, con
las referencias en hebreo la app mostraría **otro salmo** en las tres lenguas.

**Decisión tomada:** dejé las referencias en **numeración hebrea**, como pide literalmente el
encargo, y lo señalo aquí para que ajustes la resolución. Si prefieres que la app resuelva bien
sin tocar código, el cambio es mecánico (22→21, 23→22, 51→50, 95→94, 103→102); pero si ajustas
el resolutor, habrá que hacerlo para las tres ediciones incluidas, no solo para ES/PT.

## Lo que no pude verificar

- Que la numeración hebrea sea la de la Biblia en inglés incluida: **no lo es** (ver arriba).
- No he podido compilar la app: no hay Android SDK en este equipo. El coordinador comprueba la
  compilación y la resolución real del pasaje.
- El tono y la extensión se han medido por recuento de palabras; no hay revisión lingüística
  profesional.

## Preguntas para el coordinador

1. **Numeración:** ¿mantengo la hebrea en `scriptureReferences` y añades la resolución por
   edición, o prefieres que use la numeración de la Vulgata que ya resuelven las ediciones
   incluidas? (Ver la tabla.)
2. El curso A, en `CatholicBibleCourseData.kt`, usa `objectives = listOf(questions.first().prompt)`
   como valor provisional. Yo escribí objetivos propios en las cinco lecciones; confirma que es
   lo esperado para los cursos JSON.
3. ¿Quieres que añada `docs/content/study-provenance.json` para este curso (lo prevé el plan en
   §7.7) o lo llevas tú en la integración?
