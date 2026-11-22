# T-056 · Informe de entrega (por lotes)

- **Rama:** `ext/T-056` en el fork `joselanzd-hash`, creada desde `origin/CatholicGPT`.
- **Estado previo:** T-055 entregada (`ext/T-055` @ `90ff84b`, sin `T-055-REVIEW.md` pendiente). Al releer `origin/CatholicGPT:docs/tasks/README.md`, **T-052 sigue EN COLA** (no abierta), así que no desplaza a T-056.
- **Ámbito:** solo contenido (`assets/chaplets/*.json`, `docs/tasks/T-056-data/`, este informe). No se toca código.

## Lote 1 — IVE-07, IVE-08, IVE-09, IVE-10, IVE-11, IVE-12, IVE-15, IVE-16

| `id` | `sourceId` | URL abierta | Idioma fuente | Traducción | Derechos |
|---|---|---|---|---|---|
| `sagrado-corazon-jesus` | IVE-07 | https://iveinarabic.org/es/rosarios-y-coronas/ | ES | EN/PT: traducción fiel de Hermes desde el original ES | Sin reserva expresa verificada en la página → `rights: null` |
| `inmaculado-corazon-maria` | IVE-08 | índice IVE, panel «Coronilla al Inmaculado Corazón de María» | ES | ídem | ídem |
| `sangre-preciosa-corona` | IVE-09 | índice IVE, panel «Corona a La Sangre Preciosa de Jesús.» | ES | ídem | ídem |
| `rosario-espiritu-santo` | IVE-10 | índice IVE, panel «El Rosariod del Espíritu Santo» | ES | ídem | ídem |
| `siete-dolores-corona` | IVE-11 | índice IVE, panel «Corona De Los Siete Dolores» | ES | ídem | ídem |
| `alabanzas-maria` | IVE-12 | índice IVE, panel «Coronilla de Alabanzas a Maria» | ES | ídem | ídem |
| `pasiones-cristo-rosario` | IVE-15 | índice IVE, panel «Rosario de la Pasión de Jesús» | ES | ídem | ídem |
| `desagravio-maria` | IVE-16 | índice IVE, panel «Coronilla simple de desagravio a María Santísima» | ES | ídem | ídem |

Las 8 fichas están en un único panel desplegable de la misma URL; el texto se extrajo del HTML y se cotejó con las transcripciones de `docs/content/coronillas/informe_rosarios_coronas.md`.

### Incoherencias y decisiones del lote

- **IVE-09:** la fuente dice «CINCO Padre nuestros con un Gloria menos en el último» y más adelante aclara «en este último misterio se rezarán solamente tres Padre nuestros, para completar el número de treinta y tres, y Gloria». Se modelan 6×5 + 3 = 33 cuentas de Padre Nuestro. Se conserva la errata «TERCERA MISTERIO» del original.
- **IVE-10:** el panel dice «Rosariod»; se corrige el título a «Rosario del Espíritu Santo» y se anota la errata. Cada misterio lleva cita bíblica literal en la meditación.
- **IVE-11:** las meditaciones largas de los siete dolores no se convierten en pasos: se conservan **literalmente** los encabezados de cada dolor como `meditation` y la estructura Padre Nuestro + 7 Avemarías + Gloria. No se parafrasea ni se completa ningún texto. Las «siete gracias» se registran como `historicalNote` atribuido a la fuente, no como promesa vigente.
- **IVE-12:** la fuente no describe un hilo de cuentas; `beads: []`. Se conservan las erratas de puntuación del original («muy hermosa eres oh María No hay…»). El Credo y las oraciones comunes van como `prayerRef`.
- **IVE-15:** la fuente empieza con Padre Nuestro, Avemaría y Gloria (sin Credo); se respeta. La cita inicial de Cristo crucificado se incluye como texto literal.
- **IVE-16:** los cinco misterios se pueden rezar separados; `when` lo refleja. No se inventa instrumento: cinco grupos de tres cuentas pequeñas.

### Partes que faltan en la fuente

- IVE-07, IVE-08, IVE-09, IVE-11, IVE-12, IVE-15: la fuente no da oración inicial con Credo; no se añade.
- IVE-09: las aspiraciones finales con indulgencia se omiten como pasos; queda recogida solo la oración principal y la nota histórica.
- IVE-11: las meditaciones extensas quedan fuera de los pasos (ver decisión anterior).
- IVE-12: la fuente no especifica cuentas; no se inventa hilo.

### Validador

```
$ python3 docs/tasks/tools/validate_chaplets.py --partial app/src/main/assets/chaplets/*.json
WARN  partial delivery: 8 of 55
8 files · 0 errors · 1 warnings
```

El WARN es esperado en una entrega parcial.

### Validador acumulado (tras el lote 2)
```
20 files · 0 errors · 1 warnings
```

## Excluidas



## Lote 2 — IVE-06, IVE-13, IVE-14, IVE-17, HZ-01, HZ-02, HZ-03, HZ-04, HZ-05, HZ-07, HZ-08, HZ-10

| `id` | `sourceId` | URL abierta | Idioma fuente | Traducción | Derechos |
|---|---|---|---|---|---|
| `almas-purgatorio-coronilla` | IVE-06 | https://iveinarabic.org/es/rosarios-y-coronas/ | ES | EN/PT: traducción fiel de Hermes | Sin reserva expresa → `null` |
| `san-charbel` | IVE-13 | índice IVE, panel «Coronillade San Charbel» | ES | ídem | ídem |
| `san-antonio-padua-coronilla` | IVE-14 | índice IVE, panel «San Antonios de Padua» | ES | ídem | ídem |
| `confianza-tribulaciones` | IVE-17 | índice IVE, panel «Corona de confianza…» | ES | ídem | ídem |
| `lourdes-rosario` | HZ-01 | https://hozana.org/es/oracion/santo-rosario/lourdes | ES | PT: **traducción publicada** por Hozana (https://hozana.org/pt/oracao/terco/lourdes); EN: Hermes | Sin reserva expresa → `null` |
| `coronilla-liberacion` | HZ-02 | https://hozana.org/es/oracion/santo-rosario/de-liberacion | ES | EN: **traducción publicada** por Hozana (https://hozana.org/en/prayer/rosary/liberation); PT: Hermes | ídem |
| `llagas-cristo-coronilla` | HZ-03 | https://hozana.org/es/oracion/santo-rosario/de-las-llagas-de-cristo | ES | EN/PT: Hermes | ídem |
| `santisimo-meditar-adorar` | HZ-04 | https://hozana.org/es/oracion/santo-rosario/santisimo-sacramento | ES | ídem | ídem |
| `sagrada-eucaristia-33` | HZ-05 | misma página que HZ-04 | ES | ídem | ídem |
| `coronilla-san-jose-hozana` | HZ-07 | https://hozana.org/es/oracion/santo-rosario/coronilla-san-jose | ES | ídem | ídem |
| `teresita-24-glorias` | HZ-08 | https://hozana.org/es/oracion/santo-rosario/santa-teresita | ES | ídem | ídem |
| `rosario-difuntos-hozana` | HZ-10 | https://hozana.org/es/oracion/santo-rosario/almas-purgatorio | ES | ídem | ídem |

### Estatus eclesial de HZ-02 (marcada «verificar»)

La página de Hozana fundamenta la coronilla únicamente en **Juan 8:36**; no menciona visionario, revelación privada, aparición ni fenómeno sobrenatural. Por tanto **no se excluye**: no hay elemento que encaje en las *Normas para proceder en el discernimiento de presuntos fenómenos sobrenaturales* (DDF, 17-05-2024). Se deja constancia en `notes`.

### Incoherencias y decisiones del lote

- **IVE-06:** la fuente dice «Jesús, María os amo, salvad las almas» en cada cuenta de cada decena y la jaculatoria larga entre decenas; se modelan ambas. La oración final se atribuye a María Valtorta (24-10-1944) como `historicalNote`, no como promesa.
- **IVE-13:** erratas corregidas «cuesta»→«cuenta» y «Las cuatro grupo»→«El cuarto grupo»; se conserva el texto de la plegaria maronita (S. Jacobo de Sarug, s. V).
- **IVE-14:** errata corregida «Si quieres ver milagros»→«Si buscas milagros, mira».
- **IVE-17:** 10 misterios × 9 repeticiones + cierre = 91, según el texto de la fuente.
- **HZ-08:** el título «24 Glorias» no coincide con el cuerpo, que da oraciones propias; se reproducen las oraciones literales.
- **HZ-07:** la fuente da 60 cuentas pequeñas pero no el número de cuentas grandes separadoras; se modelan 20 tríadas y 19+1 grandes, anotándolo.
- **HZ-01:** la versión portuguesa de Hozana añade una Salve final que falta en la española; no se añade para conservar el mismo contenido en los tres idiomas.

### Partes que faltan en la fuente

- IVE-06: la fuente no da Credo inicial (sí Padre Nuestro, Avemaría y Credo tras la introducción); se respeta el orden del texto.
- HZ-03: las dos oraciones complementarias (consagración a la Faz y a la llaga del hombro) no pertenecen a la coronilla y no se modelan como pasos.
- HZ-08: la fuente alude a una oración de François Lugan sin transcribirla; no se inventa.

### Validador

```
$ python3 docs/tasks/tools/validate_chaplets.py --partial app/src/main/assets/chaplets/*.json
WARN  partial delivery: 20 of 55
20 files · 0 errors · 1 warnings
```



## Lote 3 — APP-ST-MICHAEL, CD-21, CD-31, CD-01, CD-05, CD-08, CD-09, CD-55

| `id` | `sourceId` | URL abierta | Idioma fuente | Traducción | Derechos |
|---|---|---|---|---|---|
| `corona-san-miguel-raccolta` | APP-ST-MICHAEL | `app/src/main/assets/prayers/text-overrides.json` (Raccolta 1910, n.º 291) | EN original | ES/PT: traducciones fieles de T-007, ya publicadas en la app | Dominio público (Raccolta 1910) |
| `san-gabriel-coronilla` | CD-21 | https://www.catholicdoors.com/prayers/chaplets/chap38.htm | EN | ES/PT: Hermes | `Derechos: Catholic Doors Ministry` |
| `san-rafael-coronilla` | CD-31 | https://www.catholicdoors.com/prayers/chaplets/chap13.htm | EN | ídem | ídem |
| `coronilla-difuntos-45` | CD-01 | https://www.catholicdoors.com/prayers/chaplets/chap22.htm | EN | ídem | ídem |
| `conversion-coronilla` | CD-05 | https://www.catholicdoors.com/prayers/chaplets/chap16.htm | EN | ídem | ídem |
| `fe-coronilla` | CD-08 | https://www.catholicdoors.com/prayers/chaplets/chap03.htm | EN | ídem | ídem |
| `misericordia-perdon` | CD-09 | https://www.catholicdoors.com/prayers/chaplets/chap27.htm | EN | ídem | ídem |
| `unidad-coronilla` | CD-55 | https://www.catholicdoors.com/prayers/chaplets/chap43.htm | EN | ídem | ídem |

### Notas del lote

- **APP-ST-MICHAEL:** los pasos se generan desde los campos `leaderText` y `responseText` de `text-overrides.json`, sin reescribir ninguna frase. Es el texto auténtico de T-007, no el de Catholic Doors ni el de Hozana.
- **CD-01:** la fuente ofrece «De Profundis **o** Padre Nuestro y Avemaría»; se modela la segunda opción. Dice 5 cuentas introductorias pero solo menciona tres actos (Fe, Esperanza y Caridad): el WARN del validador está explicado en `notes`.
- **CD-21 y CD-09:** se conservan las erratas del original («though among women», «they womb», «grat mercy»).
- **CD-08:** la indulgencia de 5 años se registra solo como `historicalNote`.

### Validador acumulado (tras el lote 3)

```
$ python3 docs/tasks/tools/validate_chaplets.py --partial app/src/main/assets/chaplets/*.json
WARN  coronilla-difuntos-45.json.beads[1]: steps cover 3 prayers for a group of 5 (explained in notes)
WARN  partial delivery: 28 of 55
28 files · 0 errors · 2 warnings
```



## Lote 4 — CD-10, CD-11, CD-12, CD-13, CD-15, CD-34, CD-42, CD-53, CD-47, CD-57

Todas las fichas de este lote proceden de `https://www.catholicdoors.com/prayers/chaplets/`:
`chap45.htm` (CD-10), `chap28.htm` (CD-11), `chap44.htm` (CD-12), `chap10.htm` (CD-13), `chap47.htm` (CD-15), `chap37.htm` (CD-34), `chap36.htm` (CD-42), `chap20.htm` (CD-53), `chap19.htm` (CD-47) y `chap35.htm` (CD-57).
Idioma fuente: **EN**. Traducción ES/PT: Hermes, fiel al original. Derechos: `Derechos: Catholic Doors Ministry` en todas (pie de página «Copyright © Catholic Doors Ministry»).

### Estatus eclesial de las marcadas «verificar»

- **CD-47, Reina de la Luz:** la página no identifica visionario, aparición ni revelación privada; las tres meditaciones se presentan como dichos sin atribución personal. No se ha encontrado pronunciamiento del DDF ni de la autoridad competente que las desaconseje. **Se conserva**, con esta comprobación en `notes`.
- **CD-57, Madre de la Santísima Eucaristía:** la página tampoco menciona visionario ni revelación. Otras webs (p. ej. catholictradition.org) la encuadran en una polémica ajena a la coronilla; ese encuadre **no se reproduce**. Sin elemento que encaje en las *Normas DDF* de 2024. **Se conserva**, con la comprobación en `notes`.

### Incoherencias y decisiones

- **CD-11:** la fuente transcribe la acción de gracias de la primera aparición; para las otras tres solo da el título, el Padre Nuestro, tres Avemarías y el Gloria. No se inventa ninguna acción de gracias adicional.
- **CD-34:** se conserva la errata «most holly Mothers tears». El texto responsorial se reparte con `vr`.
- **CD-42:** 15 cuentas declaradas frente a 3×4+1=13 descritas; el hilo sigue el texto real y el desajuste queda en `notes`.
- **CD-47:** 18 cuentas en 3×6; un Padre Nuestro con meditación y cinco Avemarías por grupo.
- **CD-57:** se conservan «0 Mother» (cero por O) y «I Believe, that He is the, only begotten».

### Validador acumulado (tras el lote 4)

```
$ python3 docs/tasks/tools/validate_chaplets.py --partial app/src/main/assets/chaplets/*.json
WARN  coronilla-difuntos-45.json.beads[1]: steps cover 3 prayers for a group of 5 (explained in notes)
WARN  partial delivery: 38 of 55
38 files · 0 errors · 2 warnings
```

Ninguna todavía: las tres marcadas «verificar» (CD-47, CD-57, HZ-02) se han comprobado y se conservan con nota.: CD-47, CD-57 y HZ-02 (marcadas «verificar» en la especificación).
