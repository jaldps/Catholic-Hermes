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

## Excluidas

Ninguna todavía. Quedan por resolver en lotes posteriores: CD-47, CD-57 y HZ-02 (marcadas «verificar» en la especificación).
