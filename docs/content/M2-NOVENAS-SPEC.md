# M2 · Novenas: procedencia, promesas y novenas clásicas

Lee primero `docs/content/EDITORIAL-RULES-1.8.md` (obligatorias). Este documento es el contrato de M2.

## 1. Contrato de datos (procedencia)

Archivos JSON nuevos en `app/src/main/assets/novenas/provenance/`:
`p1.json` (índices 0–61), `p2.json` (62–122), `p3.json` (123–184), `p4.json` (185–245), `new.json` (novenas nuevas, índices ≥ 246).
El índice es la posición en `assets/novenas/index.json` (ver lista en el encargo). Cada archivo es un array de objetos:

```json
{
  "id": "novena-a-cristo-rey",
  "status": "historical | traditional | modern | pastoral_review",
  "origin":  {"es": "", "en": "", "pt": ""},
  "history": {"es": "", "en": "", "pt": ""},
  "approval": {"es": "", "en": "", "pt": ""},
  "promise": {
    "kind": "SOURCED | ECCLESIAL_GRANT | TRADITIONAL | NONE",
    "text": {"es": "", "en": "", "pt": ""},
    "attribution": {"es": "", "en": "", "pt": ""},
    "source": {"es": "", "en": "", "pt": ""},
    "conditions": {"es": "", "en": "", "pt": ""}
  },
  "commercialAdditions": {"es": "", "en": "", "pt": ""},
  "pastoralNote": {"es": "", "en": "", "pt": ""},
  "sources": ["Enchiridion Indulgentiarum (1999), conc. 22", "…"]
}
```

Significado y reglas:

- **id**: idéntico al de `index.json`. Una entrada por novena, sin omitir ninguna del rango.
- **status**:
  - `historical`: texto o práctica con autor/fecha documentados (p. ej. Novena de Gracia, 1633; Novena al Espíritu Santo de León XIII, 1897).
  - `traditional`: práctica antigua extendida, sin autor documentado.
  - `modern`: composición reciente o de autor desconocido (muchas novenas de webs devocionales). No es un defecto: se dice con honestidad.
  - `pastoral_review`: título, enfoque o texto que choca con la doctrina o la prudencia pastoral (p. ej. «maldiciones ancestrales/generacionales», promesas automáticas, fines políticos o lucrativos). Requiere `pastoralNote`.
- **origin**: una línea: autor/promotor, fecha o siglo, lugar. Si se desconoce: «Autor y fecha desconocidos; composición devocional moderna» (en los 3 idiomas).
- **history**: 60–160 palabras por idioma: quién es el destinatario (santo, misterio, advocación), cómo nació la devoción/novena, fiesta litúrgica y por qué se reza esos 9 días. Solo hechos verificables; la tradición piadosa se marca como tal.
- **approval**: solo aprobaciones verificables: indulgencia del *Enchiridion* 1999 (número de concesión), reconocimiento episcopal de una aparición con año, fiesta en el Calendario Romano General, decreto con fecha. Si no hay nada verificable: cadena vacía en los 3 idiomas. Nunca inventar imprimatur.
  - Nota útil: el *Enchiridion* 1999, conc. 22 §2 concede indulgencia parcial por las novenas públicas antes de Navidad, Pentecostés y la Inmaculada (verificar la redacción antes de usarla).
- **promise**: lo que se promete o atribuye a quien la reza.
  - `SOURCED`: texto + atribución + fuente documental localizable (p. ej. Santa Margarita María, carta de mayo de 1688; *Diario* de Santa Faustina, n.º 796). `text` puede ser paráfrasis fiel si la fuente está protegida.
  - `ECCLESIAL_GRANT`: indulgencia o privilegio concedido por la Iglesia, con documento y condiciones (para plenaria: confesión, comunión, oración por las intenciones del Papa, desapego del pecado).
  - `TRADITIONAL`: lo que la tradición popular atribuye, presentado como tal («Según la tradición popular…»), con `source` = «Sin fuente documental verificable».
  - `NONE`: no hay promesa. Entonces `text`, `attribution`, `source` y `conditions` vacíos.
  - `conditions`: requisitos reales (estado de gracia, confesión, días seguidos, disposición de fe, aceptar la voluntad de Dios). Toda promesa se encuadra: Dios no está obligado por fórmulas; la oración no es magia (CIC 2111).
- **commercialAdditions**: si el texto del cuerpo (`NNN.json`), la descripción o la práctica popular asociada incluye fórmulas de cadena o comerciales («nunca falla», «publícala/compártela», «deja 9 copias en la iglesia», «se cumple en 9 días», «agradece en el periódico», amenazas si se interrumpe), descríbelas aquí brevemente y di que **no forman parte de la devoción** ni tienen aprobación. Si no hay: cadena vacía. Revisa el cuerpo real con `python3` (busca "public", "copias", "nunca falla", "never fail", "publish", "copies", "compart", "share", "garant", "guarant").
- **pastoralNote**: solo cuando haga falta (status `pastoral_review`, o una novena con fin delicado): orientación breve, pastoral y doctrinal. Vacía en los demás.
- **sources**: 1–4 líneas cortas con fuentes reales (documento, obra, sitio oficial de santuario o diócesis, Martirologio Romano 2004, Calendario Romano General). Sin URL inventadas.

Trilingüe: ES, EN y PT completos y naturales. Mismo contenido sustancial.

## 2. Novenas clásicas nuevas (`new.json` + cuerpos 246…)

Antes de crear una, comprueba en `index.json` que no exista con otro título.
Candidatas (unas 15–20, prioriza las de fuerte arraigo y fuente clara):
Aparecida, Luján, Pilar, Señor de los Milagros, Chiquinquirá, Coromoto, Altagracia, Copacabana, Cristo de Esquipulas, Divino Niño (Bogotá), San Ramón Nonato, San Pancracio, Santa Eduviges, São Benedito, Nazaré (Círio), San José Gregorio Hernández, Merced, Suyapa, Santa Bárbara.

Cada novena nueva:
- Entrada en `index.json` (mismo formato que las existentes, `bodyFile` `246.json`, `247.json`…, `imageUrl` null, `tags` de la lista existente, `promises`/`miraculousHistory` vacíos, `description` sobria, sin tono comercial).
- Cuerpo `NNN.json` con 9 días (`dayNumber`, `theme`, `scripture` referencia, `meditation`, `prayer`, `intentionGuidance`), texto de dominio público o redacción propia. Estructura de cada día: señal de la cruz, oración inicial común, consideración del día (~80–150 palabras), petición, Padre nuestro/Avemaría/Gloria, oración final común.
- Entrada de procedencia en `new.json`.

## 3. Integración (código)

- `NovenaCatalog`: cargar opcionalmente `provenance/p1..p4.json` y `provenance/new.json` (si falta un archivo, ignorarlo) y adjuntar `provenance: NovenaProvenance?` al modelo `Novena` (campo nuevo con valor por defecto `null`). Modelos Kotlin en `data/models/NovenaProvenance.kt`, con `PromiseKind` enum.
- Resumen de la novena (`PrayersAndNovenasScreen.kt`, sección actual «Promesas y milagros registrados», ~l.2333/2435) se sustituye/amplía con wrappers del kit (`AppCard`, `AppSectionHeader`, `AppBanner`):
  - «Historia» (`novena_history`): `origin` en pie, `history`, `approval` si existe.
  - «Promesa» (`novena_promise`): insignia de tipo (Con fuente / Concesión de la Iglesia / Tradición popular / Sin promesa), texto, atribución, fuente y condiciones.
  - Aviso «Añadidos comerciales» (`novena_commercial_warning`) si `commercialAdditions` no está vacío.
  - Nota pastoral (`novena_pastoral_note`) si existe.
  - Mantener la visualización de `promises`/`miraculousHistory` antiguos solo si no hay procedencia.
- Tests:
  - `NovenaProvenanceTest`: cada id del índice tiene procedencia; ids existentes; trilingüe completo en `history`/`origin`; `kind` válido; `SOURCED`/`ECCLESIAL_GRANT` con `source` no vacío; `NONE` con texto vacío; `pastoral_review` con `pastoralNote`.
  - Actualizar recuentos 246/3068 en `NovenaAssetIntegrityTest`, `NovenaCatalogLoadingTest`, `EditorialReliabilityTest`; añadir hashes de los cuerpos nuevos a `novena-body-hashes.json` sin cambiar los existentes (los cuerpos 000–245 no se tocan).
