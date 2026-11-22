# «Otras coronillas» · especificación (T-056 a T-058)

Petición del dueño (22-nov): además del Rosario y la Coronilla de la Divina Misericordia, la app ofrece **otras coronillas** con la misma experiencia de rezo:
- hilo de cuentas;
- imagen propia;
- voces V y R;
- guía paso a paso.

Se eligen desde un **tercer segmento «Otras ▾»** en la pantalla del Rosario, que abre un catálogo con búsqueda y grupos, y también desde Orar. El catálogo está **curado y sin duplicados**: 55 entradas.

**Materiales del dueño** (en `docs/content/coronillas/`):
- `coronillas_spec.json`: 87 fichas con instrumento, cuentas, grupos, pasos, días, variables, meditación y notas.
- `informe_rosarios_coronas.md`: tipología, sustituciones, datos que hay que pedir, tabla maestra y fichas detalladas.

> **La estructura sale de esos archivos. Los textos no:** hay que copiarlos **literalmente** de la página de cada ficha (campo `fuente`). Nunca se generan, se parafrasean ni se completan oraciones devocionales.

## Fuentes (confirmadas por el dueño)

- Catholic Doors Ministry, `https://www.catholicdoors.com/prayers/chaplets.htm`, más la página de cada coronilla. Está **en inglés**.
- Instituto del Verbo Encarnado, `https://iveinarabic.org/es/rosarios-y-coronas/`. Está **en español**. Tiene algunas erratas de transcripción, señaladas en el informe: se corrigen solo si son evidentes y se anotan.
- Hozana, `https://hozana.org/es/oracion/santo-rosario/` + `lourdes`, `de-liberacion`, `de-las-llagas-de-cristo`, `santisimo-sacramento`, `coronilla-san-miguel-arcangel`, `coronilla-san-jose`, `santa-teresita`, `almas-purgatorio` y `san-miguel`. Está **en español**.

**Los tres idiomas son obligatorios** (ES, EN y PT):
- el idioma de la fuente se copia tal cual;
- los otros dos son **traducción fiel**, declarada como tal en `translation`;
- si existe una traducción publicada de buena procedencia (de la misma web en otro idioma, o de un editor católico), se prefiere y se cita.

## Reglas (además de `docs/content/EDITORIAL-RULES-1.8.md`)

1. **Nada inventado.** Si a la fuente le falta una parte (la oración final, por ejemplo), se deja sin ella y se anota en el REPORT. No se completa.
2. **Oraciones comunes por referencia.** Padre Nuestro, Avemaría, Gloria, Credo, Salve, Señal de la cruz, Acto de contrición, Dale Señor el descanso eterno, etc. se citan con `prayerRef` a los ids que ya existen en la app (`our_father`, `hail_mary`, `glory_be`, `creed`, `salve_regina`, `sign_of_the_cross`, `act_of_contrition`, `eternal_rest`, `memorare`, `fatima_prayer`…; el validador tiene la lista). **No se duplica su texto.**
3. **Derechos.**
   - Se verifica si cada sitio reserva expresamente los derechos de sus textos (pie de página, aviso legal).
   - Si los reserva, el texto entra igualmente, con `rights: "Derechos: <titular>"` y la fuente, según la regla del dueño, como con EWTN y la Conferencia Episcopal de Honduras. Se lista en el REPORT para que el dueño lo sepa.
   - Si no hay reserva verificada, no se pone línea de derechos.
4. **Estatus eclesial.**
   - Las coronillas ligadas a **revelaciones privadas no reconocidas**, o desaconsejadas por la autoridad competente (Normas del DDF de 2024), **no entran**. Se anotan en el REPORT con el motivo.
   - **Marcadas «verificar»:** CD-47 *Queen of Light*, CD-57 *Mother of the Most Holy Eucharist* y HZ-02 *Coronilla de liberación*.
5. **Indulgencias y promesas.**
   - Las de la fuente que sean históricas («300 días», «5 años», promesas de apariciones) se muestran **solo** como nota histórica con fuente y fecha («La fuente afirma…; normativa vigente: Enchiridion Indulgentiarum, 4.ª ed., 1999»), nunca como vigentes.
   - Las promesas sin fuente se omiten.
6. **Incoherencias de la fuente.** Se resuelven con el **texto real**, no con la cifra declarada, y se documentan:
   - CD-38: 50 cuentas declaradas, 46–48 descritas;
   - CD-42: 15 declaradas, 13 descritas;
   - CD-54: 15 grupos para 14 estaciones;
   - CD-04: la cabecera equivocada.
7. **Variables del usuario** (nombre del difunto, intención, género):
   - el texto lleva marcadores `{{nombre}}`, `{{intencion}}` y formas `{{el|ella}}` / `{{him|her}}`;
   - la app las pide al empezar y **no las guarda**.

## Lista curada (55)

**Excluidas:**
- **Duplican lo que ya tiene la app:**
  - IVE-01 a IVE-04 (los cuatro misterios del Rosario);
  - IVE-05, CD-06 y CD-07 (Divina Misericordia).
- **Versiones repetidas.** Se conserva la marcada ✓:

  | Devoción | Se conserva | Se excluye |
  |---|---|---|
  | Almas y difuntos | ✓ IVE-06 · ✓ HZ-10 · ✓ CD-01 (estructura propia de 45) | CD-02, CD-04, HZ-09 |
  | San Miguel | ✓ la Corona de San Miguel que **ya existe** (`chaplet_of_st_michael`, Raccolta 1910 n.º 291, T-007), migrada al motor | CD-27, HZ-06, HZ-11 |
  | Sagrado Corazón | ✓ IVE-07 | CD-49, CD-50, CD-51 |
  | Inmaculado Corazón | ✓ IVE-08 | CD-43 |
  | Preciosa Sangre | ✓ IVE-09 | CD-46 |
  | Espíritu Santo | ✓ IVE-10 | CD-38 |
  | Siete Dolores | ✓ IVE-11 | CD-52 |
  | San Antonio | ✓ IVE-14 | CD-18 |
  | Alabanzas a María | ✓ IVE-12 | CD-03 |
  | Llagas | ✓ HZ-03 | CD-36, CD-41 |
  | Santísimo | ✓ HZ-04 y ✓ HZ-05 (estructuras distintas) | CD-35 |
  | San José | ✓ HZ-07 | CD-23 |
  | Santa Teresita | ✓ HZ-08 | CD-33 |
  | Perpetuo Socorro | ✓ CD-13 | CD-14 |
  | Santa Faz | ✓ CD-37 | CD-48 |
  | Lágrimas | ✓ CD-34 | CD-16 |
  | Niño Jesús | ✓ CD-44 | CD-39 |
  | Confianza | ✓ IVE-17 | CD-56 |
- **Uso privado según la propia fuente:** CD-59 (*Josephian Rosary*).

**Incluidas, por grupo** (el grupo decide dónde aparece en el catálogo):

| Grupo | Ids |
|---|---|
| **Dios y Cristo** | IVE-07 Sagrado Corazón · IVE-09 Preciosa Sangre · IVE-10 Espíritu Santo · IVE-15 Pasión · HZ-03 Llagas · HZ-04 Santísimo (meditar y adorar) · HZ-05 Sagrada Eucaristía · CD-37 Santa Faz · CD-40 Santo Nombre · CD-44 Niño Jesús de Praga · CD-45 Santísima Trinidad · CD-54 Vía Crucis · HZ-02 Liberación (verificar) |
| **María** | IVE-08 Inmaculado Corazón · IVE-11 Siete Dolores · IVE-12 Alabanzas a María · IVE-16 Desagravio · HZ-01 Lourdes · CD-10 Czestochowa · CD-11 Guadalupe · CD-12 Carmen · CD-13 Perpetuo Socorro · CD-15 Estrella del Mar · CD-34 Lágrimas · CD-42 Inmaculada Concepción · CD-53 Dos Corazones · CD-47 Reina de la Luz (verificar) · CD-57 Madre de la Eucaristía (verificar) |
| **Santos** | HZ-07 San José · HZ-08 Santa Teresita · IVE-13 San Charbel · IVE-14 San Antonio · CD-17 Santa Ana · CD-19 Santa Dimpna · CD-20 Santa Francisca Cabrini · CD-22 Santa Gertrudis · CD-24 San Judas · CD-25 Santa Kateri · CD-26 San Maximiliano Kolbe · CD-28 San Pío de Pietrelcina · CD-29 San Patricio · CD-30 Santa Filomena · CD-32 Santa Rita · CD-58 Familia franciscana |
| **Ángeles** | Corona de San Miguel (existente, `sourceId: "APP-ST-MICHAEL"`) · CD-21 San Gabriel · CD-31 San Rafael |
| **Difuntos** | IVE-06 Almas del Purgatorio · HZ-10 Rosario por los difuntos · CD-01 Coronilla por los difuntos |
| **Por una intención** | IVE-17 Confianza en grandes tribulaciones · CD-05 Conversión · CD-08 Fe · CD-09 Misericordia y perdón · CD-55 Unidad |

Total: 13 + 15 + 16 + 3 + 3 + 5 = **55**.

Si una entrada marcada «verificar» no supera la regla 4, sale. No se sustituye por otra sin consultar.

## Contrato · `app/src/main/assets/chaplets/<id>.json`

Un archivo por coronilla. El `id` es estable y en minúsculas con guiones (p. ej. `siete-dolores`). El id de origen va en `sourceId` (`IVE-11`).

```json
{
  "id": "siete-dolores",
  "sourceId": "IVE-11",
  "group": "mary",
  "title":       {"es": "Corona de los Siete Dolores", "en": "Chaplet of the Seven Sorrows", "pt": "Coroa das Sete Dores"},
  "summary":     {"es": "…", "en": "…", "pt": "…"},
  "instrument":  {"es": "Coronilla de 7 grupos de 7 cuentas…", "en": "…", "pt": "…"},
  "image": "siete-dolores",
  "when":        {"es": "Uso libre; especialmente el viernes y el 15 de septiembre", "en": "…", "pt": "…"},
  "weekdays": [5],
  "variables": [],
  "beads": [
    {"kind": "cross"}, {"kind": "medal", "label": {"es": "Medalla", "en": "Medal", "pt": "Medalha"}},
    {"kind": "large"}, {"kind": "small", "count": 7, "color": "#5B2A86"}
  ],
  "steps": [
    {"bead": 0, "prayerRef": "sign_of_the_cross"},
    {"bead": 0, "text": {"es": "…literal…", "en": "…", "pt": "…"}},
    {"bead": 2, "meditation": {"es": "Primer dolor: …", "en": "…", "pt": "…"}, "prayerRef": "our_father"},
    {"bead": 3, "prayerRef": "hail_mary", "repeat": 7},
    {"bead": 0, "vr": {"v": {"es": "V. …", "en": "…", "pt": "…"}, "r": {"es": "R. …", "en": "…", "pt": "…"}}}
  ],
  "promise": null,
  "historicalNote": null,
  "sources": [{"title": "IVE · Rosarios y coronas", "url": "https://iveinarabic.org/es/rosarios-y-coronas/"}],
  "translation": {"es": "original", "en": "traducción fiel del coordinador de contenido (Codex), 2026", "pt": "traducción fiel …"},
  "rights": null,
  "notes": "Corrección de errata «…» → «…»."
}
```

**Campos:**
- **`group`:** `god_christ` | `mary` | `saints` | `angels` | `departed` | `intention`.
- **`beads`:** el **patrón del hilo**, en orden, para dibujarlo.
  - `kind`: `cross` | `medal` | `large` | `small` | `separator` | `knot`.
  - `count`: opcional, 1 por defecto.
  - `color`: hexadecimal opcional, para coronillas de colores.
  - `label`: opcional, en los tres idiomas.
  - Si la coronilla no usa instrumento (CD-17 Santa Ana), `beads: []` y los pasos cuentan oraciones.
- **`steps`:** la secuencia de rezo. Cada paso lleva:
  - `bead`: índice en `beads` del grupo al que pertenece, o `null` si va fuera del hilo;
  - **exactamente uno** de `prayerRef`, `text` o `vr`;
  - `repeat`: opcional, 1 por defecto. Cada repetición avanza una cuenta si el grupo tiene `count` > 1;
  - `meditation`: opcional, en los tres idiomas. Se muestra al entrar en el grupo.
- **`vr`:** versículo y respuesta para las dos voces (guía y respuesta), igual que en el Rosario.
- **`variables`:** `["deceased_name", "deceased_gender", "intention"]`. El texto usa `{{nombre}}`, `{{intencion}}` y `{{el|ella}}` / `{{him|her}}` / `{{ele|ela}}`.
- **`weekdays`:** días sugeridos (ISO 1–7). Además, `season` opcional: `lent` | `advent` | `easter`.
- **`promise` / `historicalNote`:** solo con fuente (regla 5).
- **`image`:** clave de imagen (T-058). La resuelve `assets/chaplets/images/<image>.jpg` con `credits.json`. Si no existe, se usa el arte de la sección.

**Coherencia comprobada por el validador.** La suma de cuentas que recorren los pasos (`repeat` sobre grupos con `count`) debe coincidir con el `count` declarado de cada grupo. Si no coincide, es un error, salvo que se declare en `notes` con el motivo (regla 6).

## Validador

```sh
python3 docs/tasks/tools/validate_chaplets.py app/src/main/assets/chaplets/*.json
```

Comprueba:
- el esquema;
- los tres idiomas presentes y no vacíos;
- que EN y PT no sean idénticos al ES salvo en latín;
- `prayerRef` contra los ids de la app;
- que cada paso tenga exactamente un contenido;
- índices de `bead` válidos y conteo de cuentas;
- `group` válido;
- `sources` con URL https;
- ids únicos y coincidentes con el nombre del archivo;
- marcadores de variables declarados en `variables`.

Con `--partial`, admite un subconjunto de la lista curada. Sin él, exige las 55, salvo las excluidas por la regla 4 y anotadas en `docs/tasks/T-056-data/excluded.json`.

## Encargos

| Encargo | Quién | Cuándo | Qué |
|---|---|---|---|
| T-056 | Codex | Tras T-054 (L7) | Contenido: las 55 coronillas en `assets/chaplets/`, por lotes de unos 10, con el validador. Informe de derechos y de estatus eclesial. |
| T-058 | Hermes | Tras T-053 (L6) | Imágenes: una por coronilla. Primero se reutilizan las de santos y devociones de la app; si no hay, de Commons con licencia libre, como en T-034 y T-044; con `credits.json`. |
| T-057 | Codex | Tras T-056 | Motor e interfaz:<ul><li>`ChapletCatalog`;</li><li>`StepMap` generado desde `steps`;</li><li>`RosarySession` con `Devotion.OTHER` + `chapletId` (`Saver` compatible);</li><li>segmento «Otras ▾» con catálogo en hoja;</li><li>entrada en Orar;</li><li>hilo según `beads`;</li><li>voces V y R;</li><li>variables sin guardar;</li><li>vertical y horizontal (L1);</li><li>tests, capturas y crítico.</li></ul> |

El encargo detallado de cada uno se escribe cuando se abra. Esta especificación es su base.
