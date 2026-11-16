# T-022 · Informe de Codex

Rama `ext/T-022`. Base inicial de T-022: `origin/CatholicGPT` (`be71f71`). Solo se entregan los archivos autorizados.

## Criterio editorial y procedencia

Enseñanza y contexto de redacción propia en ES/EN/PT. No se incorpora texto bíblico: las referencias se resuelven desde la edición que selecciona la app. No se compone ninguna oración: `oratio` contiene consignas para la oración personal, no palabras atribuidas a Jesús, María o un santo. El curso no se presenta como publicación del Vaticano ni como formación sacramental certificada.

La doctrina citada se revisó en la edición española oficial del Catecismo y se comprobó que todos los números existen en el corpus ES/EN/PT, cuya procedencia documenta `docs/content/catechism-provenance.json`. Las páginas españolas consultadas se enumeran por lección. Las explicaciones EN/PT son traducciones editoriales propias, no copias del Catecismo ni traducciones litúrgicas. Se contrastan los pasajes en las Biblias incluidas; las explicaciones son paráfrasis editoriales, sin citas textuales importadas de memoria.

## Numeración bíblica y resolución

No se asigna una lectura principal de los Salmos en estos cursos. Si se menciona un salmo, se explicita su numeración hebrea; no se presupone equivalencia automática con la numeración griega/latina de una edición. Las lecturas de distintos libros siempre van en entradas separadas. Cuando hay dos rangos no contiguos del mismo libro se usan dos entradas: el resolutor admite ambos bloques y el validador actual no admite el punto y coma que permite el parser. No se concatenan capítulos como si fueran versículos.

## Entregas y validación

El validador publicado exige cinco lecciones y no tiene opción `--partial`. En entregas de una o dos lecciones se ejecuta intacto mediante un envoltorio temporal externo al repositorio: conserva todos sus controles y descuenta únicamente el error de cantidad total, declarado como pendiente. El envoltorio además exige 70–110 palabras de contexto y 80–130 por desarrollo en cada idioma, con ambos contadores (espacios y regex). No se anuncian cinco lecciones cuando todavía hay un prefijo. Al completar cada curso se ejecuta el comando oficial sin envoltorio.

## `track_jesus_paschal` · 5/5 lecciones

```text
5 lessons · 0 errors · 0 warnings
```

### 1. `jesus_1_incarnation`

Lecturas: `luk 1:26-38`.

- CIC **456–460**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2a3p1_sp.html
- CIC **464–469**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2a3p1_sp.html
- CIC **484–489**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2a3p2_sp.html
- CIC **494–494**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2a3p2_sp.html
- CIC **470–475**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2a3p1_sp.html

CIC 456–460 sostiene las finalidades de la Encarnación; 464–469, la única persona y las dos naturalezas, incluida la maternidad divina en 466; 484–489, Anunciación, Espíritu e Israel; 494, consentimiento libre. La presencia de inteligencia y voluntad humanas se explicita con más detalle en CIC 470–475: se incorpora ese rango para que la afirmación tenga su anclaje directo. No se ofrecen fechas de composición de Lucas ni especulaciones biológicas.

### 2. `jesus_2_kingdom`

Lecturas: `mar 1:14-20`.

- CIC **541–546**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p122a3p3_sp.html
- CIC **1427–1429**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p2s2c2a4_sp.html

CIC 541–546 sostiene Reino, centralidad de Cristo, Iglesia como germen y comienzo, acogida de pobres y pecadores; 1427–1429, conversión primera y continua, gracia y ejemplo de Pedro. No se identifica el Reino con prosperidad, poder o una organización política. La orientación sobre decisiones responsables es aplicación pastoral editorial; no se atribuyen a Marcos instrucciones laborales o familiares que el pasaje no contiene.

### 3. `jesus_3_supper_service`

Lecturas: `luk 22:14-20`, `joh 13:1-15`.

- CIC **610–611**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p122a4p2_sp.html
- CIC **1337–1340**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p2s2c1a3_sp.html
- CIC **1362–1367**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p2s2c1a3_sp.html

CIC 610–611 sostiene la entrega libre y su anticipación en la Cena; 1337–1340 relaciona lavatorio, institución y Pascua; 1362–1367 distingue memorial eficaz y repetición de la muerte. Se amplía el rango propuesto 1362–1364 hasta 1367 para sostener directamente la unicidad del sacrificio y la diferencia del modo sacramental. No se armonizan a la fuerza las cronologías de Lucas y Juan, ni se traslada a Juan una institución que no narra en este pasaje. Se respeta el significado de la Pascua de Israel.

### 4. `jesus_4_cross`

Lecturas: `luk 23:33-49`.

- CIC **595–598**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p122a4p2_sp.html
- CIC **604–609**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p122a4p2_sp.html
- CIC **610–618**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p122a4p2_sp.html
- CIC **2264–2265**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p3s2c2a5_sp.html

Anclajes separados: CIC 595–598 para complejidad histórica y prohibición de culpa colectiva, especialmente 597; 604–609 para iniciativa amorosa del Padre y entrega libre del Hijo; 610–618 para sacrificio, reparación, comunión y participación del discípulo. CIC 2264–2265 se añade como apoyo directo al derecho de proteger la vida y al deber de proteger a otros. La orientación para buscar seguridad y ayuda es aplicación pastoral editorial, no diagnóstico ni mandato de soportar abuso. No se presenta al Padre como enemigo del Hijo ni se define la redención como mera cantidad de dolor.

### 5. `jesus_5_risen_mission`

Lecturas: `luk 24:36-53`, `act 2:1-13`.

- CIC **639–655**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p122a5p2_sp.html
- CIC **659–667**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2c2a6_sp.html
- CIC **731–732**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2c3a8_sp.html
- CIC **767–768**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p123a9p1_sp.html

CIC 639–655 distingue realidad corporal, signos históricos y misterio trascendente; especialmente 645–647 evita tanto fantasma como retorno a la vida mortal y reconoce que nadie observó el instante mismo. CIC 659–667 sostiene Ascensión, intercesión y esperanza; 731–732, don del Espíritu como Persona divina; se añade 767–768 para la manifestación pública y misión universal de la Iglesia. No se reduce Pascua a metáfora ni el Espíritu a energía. El cierre remite a los cursos existentes sin imponer Bautismo o comunión como requisito.

## `track_salvation_history` · 5/5 lecciones

```text
5 lessons · 0 errors · 0 warnings
```

### 1. `salvation_1_creation_fall`

Lecturas: `gen 1:1-2:3`, `gen 3:1-24`.

- CIC **279–301**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2c1p4_sp.html
- CIC **337–349**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2c1p5_sp.html
- CIC **355–357**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2c1p6_sp.html
- CIC **385–412**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2c1p7_sp.html

CIC 279–301 sostiene creación libre, bondad, conservación y distinción de las preguntas científicas; 337–349 añade el lenguaje de los días y el descanso; 355–357, dignidad personal e imagen de Dios; 385–412, pecado original, lenguaje figurado que afirma un acontecimiento primordial (390), ausencia de falta personal del recién nacido (405) y protoevangelio (410–411). No se da una cronología científica, ni se reduce la caída a ficción o mala estructura social. Se emplean dos rangos separados de Génesis para evitar incluir silenciosamente otro capítulo como parte de una sola referencia.

### 2. `salvation_2_abraham_promise`

Lecturas: `gen 12:1-9`, `gen 15:1-21`.

- CIC **59–61**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s1c2a1_sp.html
- CIC **144–147**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s1c3a1_sp.html

CIC 59–61 sostiene elección, bendición a las naciones, pueblo depositario y veneración de los patriarcas; 144–147, obediencia libre de la fe y confianza de Abrahán. Se conserva Abram en el contexto de Génesis 12 y 15, antes del cambio de nombre del capítulo 17. No se inventan fechas históricas ni se aplica la promesa particular de hijos o tierra como garantía material al usuario. Los signos de Génesis 15 se describen desde el relato, sin reconstruir un ritual jurídico externo no documentado.

### 3. `salvation_3_exodus_covenant`

Lecturas: `exo 3:1-15`, `exo 19:1-20:17`.

- CIC **62–64**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s1c2a1_sp.html
- CIC **2056–2063**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p3s2_sp.html
- CIC **121–123**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s1c2a3_sp.html

CIC 62–64 sostiene liberación, Sinaí, vocación de Israel y esperanza; 2056–2063, Decálogo dentro de la alianza y respuesta al don previo, especialmente 2057 y 2061–2062. Se añade 121–123 para apoyar el valor permanente del Antiguo Testamento. No se asigna un faraón, año del éxodo o explicación natural de la zarza. Se rechaza la caricatura del judaísmo como mero legalismo y se distingue pueblo sacerdotal de una proyección de cargos sacramentales posteriores.

### 4. `salvation_4_david_prophets`

Lecturas: `2sa 7:1-17`, `isa 52:13-53:12`.

- CIC **64–64**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s1c2a1_sp.html
- CIC **709–716**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s2c3a8_sp.html
- CIC **601–601**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p122a4p2_sp.html
- CIC **128–130**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s1c2a3_sp.html

CIC 64 sostiene la formación profética de la esperanza; 709–716, David, Mesías, Siervo y promesa del Espíritu; 601 se añade como anclaje directo del cumplimiento del Siervo en la muerte redentora de Jesús; 128–130 protege el valor propio del Antiguo Testamento dentro de la lectura tipológica. No se atribuyen a Jesús las faltas de descendientes davídicos de 2 S 7, ni se pretende zanjar la identidad histórica original del Siervo o fechar su composición. Las referencias cruzan correctamente Is 52–53.

### 5. `salvation_5_christ_fulfilment`

Lecturas: `heb 1:1-4`, `luk 24:44-49`.

- CIC **65–67**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s1c2a1_sp.html
- CIC **128–130**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s1c2a3_sp.html
- CIC **121–123**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p1s1c2a3_sp.html
- CIC **839–839**, rango inclusivo comprobado.
  - https://www.vatican.va/archive/catechism_sp/p123a9p3_sp.html

CIC 65–67 sostiene plenitud en Cristo, comprensión gradual y función de las revelaciones privadas; 128–130, tipología y lectura recíproca de ambos Testamentos; 121–123, valor permanente y Antigua Alianza no revocada; 839, vínculo con el pueblo judío y dones irrevocables. No se atribuye Hebreos a Pablo ni se niega el valor legítimo de revelaciones privadas reconocidas. El texto no transforma cumplimiento en desprecio de Israel o abolición del Antiguo Testamento. El cierre exige explicar y releer, no alcanzar un rango espiritual.

## Límites y revisión pendiente

No se modifica Kotlin, navegación, progreso ni corpus bíblico. No se afirma haber probado la navegación en un dispositivo o haber compilado Android: la integración, pruebas del catálogo y compilación corresponden al coordinador. La revisión externa teológica y lingüística y la comprobación final del texto resuelto en las tres lenguas quedan a su cargo. Las duraciones son estimaciones pedagógicas; la lectura y el silencio pueden requerir más tiempo.

No quedan datos históricos controvertidos presentados como fechas o autorías seguras. Los matices y límites concretos se documentan por lección. No se deducen decretos o aprobación eclesiástica del hecho de enlazar el Catecismo.

## Cierre de T-022

- **Dos cursos completos, diez lecciones y tres idiomas**. Todos los ids y el orden de las cinco lecciones del curso B coinciden con el plan; el curso C sigue las cinco etapas solicitadas. Nivel FOUNDATION, 20 minutos sugeridos por lección y 100 por curso.
- **Validadores oficiales, sin envoltorio:**

```text
track_jesus_paschal.json: 5 lessons · 0 errors · 0 warnings
track_salvation_history.json: 5 lessons · 0 errors · 0 warnings
```

- Comprobación adicional: 10 ids únicos, 30 contextos de 70–110 palabras y 90 desarrollos de 80–130 palabras, con conteo por espacios y por regex. Cada lección contiene dos objetivos observables, tres desarrollos, dos preguntas de meditación y tres preguntas con respuesta razonada (dos de comprensión y una de discernimiento).
- Se comprobaron **17 referencias en cada corpus incluido ES/EN/PT**: los rangos completos contienen números de versículo consecutivos y texto no vacío. Esto cubre los cruces Gn 1–2, Ex 19–20 e Is 52–53. No demuestra por sí solo que la transcripción de cada versículo heredado esté libre de erratas. El resolutor de estudio consulta esos corpus por idioma; no se ha fingido una interacción con la pantalla ni una prueba de todas las ediciones alternativas.
- `oratio` contiene exclusivamente guías en segunda persona para la oración personal. No contiene plegarias inventadas, nuevas palabras de Jesús o María, ni citas atribuidas a santos. La contemplación mantiene silencio real y la acción se distingue de él.
- Los cierres proponen resumir lo aprendido, expresar una pregunta y elegir una acción; no califican la fe, no certifican formación sacramental y no exigen comulgar.
- Solo se han creado los dos JSON y este informe. Se completó `git diff --check`.

### Observación sobre el corpus bíblico heredado (fuera del alcance)

Durante el cotejo apareció una posible truncación en `assets/bible/bible_es.json`, Gn 15,9: la enumeración española no contiene los animales equivalentes a cabra y carnero que sí aparecen en las copias EN/PT. El rango se resuelve porque el versículo existe y no está vacío, pero esa integridad estructural no garantiza integridad textual. No se corrige la Biblia dentro de este encargo, cuyos archivos autorizados son solo los cursos y el informe; se recomienda al coordinador cotejar ese versículo con la edición Platense antes de publicar. El comentario del curso no reproduce la lista ni añade una supuesta cita para disimularla.
