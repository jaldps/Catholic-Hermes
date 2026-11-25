# Corrección del rosario de Santa Rita de Casia (1.9.6)

## Motivo

La entrada `santa-rita-coronilla` seguía una coronilla corta de Catholic Doors (medalla + 3 + 3 + 3 cuentas azules + cruz), con una oración de apertura y tres Padres Nuestros, tres Avemarías y tres Glorias. El dueño pidió que se sustituyera por el **Terço de Santa Rita de Cássia** publicado por el futuro Santuário de Londrina, adaptado a español e inglés y guiado cuenta por cuenta.[46]

## Fuente y criterio de fidelidad

La fuente nueva indica que el terço se reza todos los miércoles a las 18:30 y describe cinco misterios: hija obediente, esposa virtuosa, madre ejemplar, viuda y religiosa sierva de Dios.[46] Cada misterio lleva una oración larga a Santa Rita, diez repeticiones de la jaculatoria «Santa Rita dos impossíveis: nada para vós é impossível» y un Gloria.[46] El ofrecimiento inicial y la consagración final también están en la fuente y se reproducen íntegros.[46]

Se han aplicado las reglas del encargo: cada cuenta lleva **una** oración completa, sin abreviaturas y sin agrupar varias oraciones en una cuenta. La fuente no distingue entre guía y respuesta, por lo que las oraciones largas, la jaculatoria, la consagración y la invocación final se marcan como «Se reza en conjunto»; no se ha inventado ninguna respuesta. El texto portugués se conserva literalmente, incluida la errata «poderoso auxilia» (por «auxílio»), que se documenta en `notes`.

## Rezo implementado

| Bloque | Cuentas | Contenido | Voces |
|---|---|---|---|
| Inicio | cruz, cuenta grande, 3 pequeñas, cuenta grande | Persignación, ofrecimiento (con petición personal), Credo, Padre Nuestro, 3 Avemarías, Gloria | Conjunto / `prayerRef` |
| Misterio 1 | cuenta grande + 10 pequeñas + cuenta grande | Oración «Minha gloriosa Santa Rita dos Impossíveis…», 10 jaculatorias, Gloria | Conjunto / `prayerRef` |
| Misterios 2–5 | igual | Igual, con la meditación propia de cada misterio | Conjunto / `prayerRef` |
| Cierre | fuera del hilo | Consagración, «Santa Rita de Cássia: rogai por nós!» y Señal de la Cruz | Conjunto / `prayerRef` |

La fuente no menciona el Padre Nuestro en cada misterio, así que **no se ha añadido**; las cuentas grandes de cada decena sostienen la oración larga de Santa Rita y el Gloria. El aviso web «Faça um pedido… clicando no relíquia» es una interacción del sitio, no parte del rezo, y tampoco se incluye.

## Registro de cambios

| Campo | Antes | Ahora |
|---|---|---|
| `title` | «Coronilla de Santa Rita» | «Rosario de Santa Rita de Casia» / *Rosary of Saint Rita of Cascia* / *Terço de Santa Rita de Cássia* |
| `instrument` | Medalla + 3 + 3 + 3 cuentas azules + cruz | Rosario común de cinco decenas |
| `weekdays` | vacío | miércoles (ISO 3) |
| `beads` | 9 cuentas pequeñas | cruz + 3 iniciales + 5×10 pequeñas, con sus cuentas grandes y medalla |
| `steps` | 4 pasos (oración inicial + 3+3+3) | 24 definiciones → **71 rezos guiados**, uno por cuenta |
| `sources` | Catholic Doors chap41 | Santuário Santa Rita de Cássia, Londrina |
| `translation.pt` | «traducción fiel de Hermes» | `original` (la fuente está en portugués) |
| `rights` | Catholic Doors Ministry | Futuro Santuário Santa Rita de Cássia - Londrina |

El `id` (`santa-rita-coronilla`) y el `sourceId` (`CD-32`) se conservan para no romper el catálogo de 55 entradas ni los enlaces desde Orar. Se actualizó `docs/content/coronillas/coronillas_spec.json` y el validador de fidelidad para que CD-32 compruebe el texto contra la evidencia portuguesa.

## Validación

- `docs/tasks/tools/validate_chaplets.py --partial --images`: **0 errores**.
- `tools/chaplets/validate_chaplets_fidelity.py`: **55 fichas, 964 pasos, 0 errores**.
- `SantaRitaRosaryGuideTest`: **2/2 tests**, incluidos los 71 rezos, las 6 secciones, las diez cuentas distintas de cada jaculatoria y la ausencia de respuestas inventadas en ES/EN/PT.
- Versión incrementada a **1.9.6** (`versionCode=46`). APK firmado con el certificado Hermes Preview, SHA-256 `71370baae73dbec6dcf9a56bd212f850d2c111f18d45392945506e4b3d8845ee`, 110.800.000 bytes; metadatos en `docs/quality/release-1.9.6/APK.json`.

## Sources

[46] https://santaritalondrina.com.br/p/terco-de-santa-rita-de-cassia — Santuário Santa Rita de Cássia · Terço de Santa Rita
