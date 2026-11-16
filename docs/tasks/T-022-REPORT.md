# T-022 · Informe de Codex

Rama `ext/T-022`. Base inicial de T-022: `origin/CatholicGPT` (`be71f71`). Solo se entregan los archivos autorizados.

## Criterio editorial y procedencia

Enseñanza y contexto de redacción propia en ES/EN/PT. No se incorpora texto bíblico: las referencias se resuelven desde la edición que selecciona la app. No se compone ninguna oración: `oratio` contiene consignas para la oración personal, no palabras atribuidas a Jesús, María o un santo. El curso no se presenta como publicación del Vaticano ni como formación sacramental certificada.

Los rangos del Catecismo se cotejan en el corpus completo ES/EN/PT, cuya procedencia oficial documenta `docs/content/catechism-provenance.json`, y en las páginas oficiales españolas enumeradas por lección. Se contrastan los pasajes en las Biblias incluidas. Las explicaciones son paráfrasis editoriales, sin citas textuales importadas de memoria.

## Numeración bíblica y resolución

No se asigna una lectura principal de los Salmos en estos cursos. Si se menciona un salmo, se explicita su numeración hebrea; no se presupone equivalencia automática con la numeración griega/latina de una edición. Las lecturas de distintos libros siempre van en entradas separadas. Cuando hay dos rangos no contiguos del mismo libro se usan dos entradas: el resolutor admite ambos bloques y el validador actual no admite el punto y coma que permite el parser. No se concatenan capítulos como si fueran versículos.

## Entregas y validación

El validador publicado exige cinco lecciones y no tiene opción `--partial`. En entregas de una o dos lecciones se ejecuta intacto mediante un envoltorio temporal externo al repositorio: conserva todos sus controles y descuenta únicamente el error de cantidad total, declarado como pendiente. El envoltorio además exige 70–110 palabras de contexto y 80–130 por desarrollo en cada idioma, con ambos contadores (espacios y regex). No se anuncian cinco lecciones cuando todavía hay un prefijo. Al completar cada curso se ejecuta el comando oficial sin envoltorio.

## `track_jesus_paschal` · 1/5 lecciones

```text
1/5 lessons (partial; exact-five completeness requirement pending) · 0 errors · 0 warnings
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

## Límites y revisión pendiente

No se modifica Kotlin, navegación, progreso ni corpus bíblico. No se afirma haber probado la navegación en un dispositivo o haber compilado Android: la integración, pruebas del catálogo y compilación corresponden al coordinador. La revisión externa teológica y lingüística y la comprobación final del texto resuelto en las tres lenguas quedan a su cargo. Las duraciones son estimaciones pedagógicas; la lectura y el silencio pueden requerir más tiempo.

No quedan datos históricos controvertidos presentados como fechas o autorías seguras. Los matices y límites concretos se documentan por lección. No se deducen decretos o aprobación eclesiástica del hecho de enlazar el Catecismo.
