# T-024 · Informe de Codex

Rama `ext/T-024`. Base inicial: `origin/CatholicGPT` (`be71f71`). Solo se entregan los archivos autorizados.

## Criterio editorial y procedencia

Enseñanza y contexto de redacción propia en ES/EN/PT. No se incorpora texto bíblico: las referencias se resuelven desde la edición que selecciona la app. No se compone ninguna oración: `oratio` contiene consignas para la oración personal, no palabras atribuidas a Jesús, María o un santo. El curso no se presenta como publicación del Vaticano ni como formación sacramental certificada.

La doctrina citada se revisó en la edición española oficial del Catecismo y se comprobó que todos los números existen en el corpus ES/EN/PT, cuya procedencia documenta `docs/content/catechism-provenance.json`. Las páginas españolas consultadas se enumeran por lección. Las explicaciones EN/PT son traducciones editoriales propias, no copias del Catecismo ni traducciones litúrgicas. Se contrastan los pasajes en las Biblias incluidas; las explicaciones son paráfrasis editoriales, sin citas textuales importadas de memoria.

## Numeración bíblica y resolución

No se asigna una lectura principal de los Salmos en estos cursos. Si se menciona un salmo, se explicita su numeración hebrea; no se presupone equivalencia automática con la numeración griega/latina de una edición. Las lecturas de distintos libros siempre van en entradas separadas. Cuando hay dos rangos no contiguos del mismo libro se usan dos entradas: el resolutor admite ambos bloques y el validador actual no admite el punto y coma que permite el parser. No se concatenan capítulos como si fueran versículos.

## Entregas y validación

El validador publicado exige cinco lecciones y no tiene opción `--partial`. En entregas de una o dos lecciones se ejecuta intacto mediante un envoltorio temporal externo al repositorio: conserva todos sus controles y descuenta únicamente el error de cantidad total, declarado como pendiente. El envoltorio además exige 70–110 palabras de contexto y 80–130 por desarrollo en cada idioma, con ambos contadores (espacios y regex). No se anuncian cinco lecciones cuando todavía hay un prefijo. Al completar cada curso se ejecuta el comando oficial sin envoltorio.

## `track_paul_life_in_christ` · 4/5 lecciones

```text
4/5 lessons (partial; exact-five completeness requirement pending) · 0 errors · 0 warnings
```

### 1. `paul_1_damascus_encounter`

Lecturas: `act 9:1-19`.

- CIC **442–442**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2c2a2_sp.html
- CIC **1212–1216**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p2s2c1_sp.html
  - https://www.vatican.va/archive/catechism_sp/p2s2c1a1_sp.html
- CIC **787–789**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p123a9p2_sp.html

CIC 442 sitúa la conversión de Pablo como revelación del Hijo por gracia; 1212–1216 sostiene iniciación, Bautismo y misión; se añade 787–789 para sostener directamente la unión de Cristo con sus discípulos perseguidos y el Cuerpo eclesial. Hechos 9 no narra un cambio de nombre a Pablo ni menciona un caballo; la explicación se atiene al texto sin usar esos detalles como examen de trivia. No se identifica el judaísmo con violencia ni se impone una experiencia extraordinaria como requisito. La cautela ante personas amenazantes es aplicación pastoral, no negación del relato de Ananías.

### 2. `paul_2_justified_grace`

Lecturas: `rom 5:1-11`.

- CIC **1987–1995**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p3s1c3a2_sp.html
- CIC **1996–2005**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p3s1c3a2_sp.html
- CIC **2006–2011**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p3s1c3a2_sp.html

CIC 1987–1995 sostiene justificación, fe, Bautismo, perdón, renovación y cooperación libre. La referencia a Trento se verifica a través de las citas del Catecismo: especialmente 1989 (DS 1528), 1992 (DS 1529) y 1993 (DS 1525); no se finge consulta de una edición independiente de los decretos. Se amplían los rangos con 1996–2005 para gracia, preparación y distinción entre estado de gracia y emociones (2005), y 2006–2011 para mérito y gratuidad de la gracia primera (2010). Se leyó también el resumen 2017–2029. No se caricaturiza a protestantes, ni se equipara justificación con éxito moral previo, ficción jurídica o perfección instantánea.

### 3. `paul_3_one_body`

Lecturas: `1co 12:12-27`.

- CIC **787–795**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p123a9p2_sp.html
- CIC **799–801**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p123a9p2_sp.html
- CIC **827–827**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p123a9p3_sp.html

CIC 787–795 sostiene comunión, Bautismo, Eucaristía, diversidad, Cabeza y crecimiento; se añaden 799–801 para carismas, bien común y discernimiento con los pastores, y 827 para la necesidad de conversión de miembros y ministros. La dignidad no se hace depender de utilidad, salud o cargo. No se interpreta Cuerpo de Cristo como absorción de las personas, ni santidad de la Iglesia como impecabilidad de cada decisión institucional. Las aplicaciones prácticas respetan la autonomía de quien necesita ayuda.

### 4. `paul_4_greatest_love`

Lecturas: `1co 13:1-13`.

- CIC **1812–1813**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p3s1c1a7_sp.html
- CIC **1822–1829**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p3s1c1a7_sp.html
- CIC **2264–2265**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p3s2c2a5_sp.html

CIC 1822–1829 sostiene caridad, amor de Dios y del prójimo, forma de las virtudes, verdad y corrección; se añade 1812–1813 para explicar virtud teologal como don de Dios y 2264–2265 para evitar interpretar paciencia como renuncia a proteger la vida. 1 Co 13 se lee dentro de la discusión sobre carismas, no solo como texto nupcial. No se compone una oración ni se reproduce de memoria una traducción moderna del himno. Las situaciones prácticas son ejemplos editoriales, no citas atribuidas a una persona real.

## Límites y revisión pendiente

No se modifica Kotlin, navegación, progreso ni corpus bíblico. No se afirma haber probado la navegación en un dispositivo o haber compilado Android: la integración, pruebas del catálogo y compilación corresponden al coordinador. La revisión externa teológica y lingüística y la comprobación final del texto resuelto en las tres lenguas quedan a su cargo. Las duraciones son estimaciones pedagógicas; la lectura y el silencio pueden requerir más tiempo.

No quedan datos históricos controvertidos presentados como fechas o autorías seguras. Los matices y límites concretos se documentan por lección. No se deducen decretos o aprobación eclesiástica del hecho de enlazar el Catecismo.
