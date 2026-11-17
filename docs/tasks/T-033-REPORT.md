# T-033 · Textos del perfil de bienvenida

- **Asignado:** Hermes · **Rama:** `ext/T-033` (fork) · **Estado:** ABIERTO, entregado.
- **Archivos creados:** `app/src/main/assets/onboarding/profiles.json`,
  `docs/tasks/tools/validate_onboarding.py`, `docs/tasks/T-033-REPORT.md`.
- No se tocó ningún otro archivo ni el código de la pantalla.

Solo contenido: el coordinador hará la parte de código de `BeginnerJourneyScreen.kt`.

## Contenido

`profiles.json` con los tres perfiles en el orden que ya usa la app (`exploring` 0, `returning` 1,
`practicing` 2), y `welcome` (1-2 frases, ≤35 palabras), `description` (25-60) y `suggestions`
(6 por perfil) en ES, EN y PT.

### Criterio de las sugerencias

Todas se ordenan **de la más sencilla a la más exigente** y cubren las tres duraciones (2, 5 y
10 min), como pide el encargo. La progresión en los tres perfiles es la misma idea: primero algo
corto y ya conocido (el Evangelio del día, el Padrenuestro), después una práctica con un pasaje
(Rosario, santo, Catecismo) y por último algo que pide más tiempo o decisión (preguntas, cursos,
vida espiritual). Cada `why` dice en una frase por qué encaja en ese momento, sin prometer resultados.

| Perfil | Sugerencias (min) |
|---|---|
| `exploring` | Evangelio de hoy (2) · Padrenuestro (2) · un misterio del Rosario (5) · conocer a un santo (5) · una pregunta del Catecismo (5) · 101 preguntas (10) |
| `returning` | Evangelio de hoy (2) · volver al Padrenuestro (2) · preparar una confesión (5) · un misterio del Rosario (5) · empezar por Hoy (10) · santos que también volvieron (10) |
| `practicing` | lectura del Evangelio (2) · rezar un misterio (5) · profundizar en el Catecismo (5) · hábitos y virtudes (5) · curso de doctrina (10) · vida espiritual (10) · santos y oficios (10) |

### Decisiones de tono

- **`exploring`** no da por supuesta la fe: habla de «encontrarás», «puedes probar», «sin
  compromiso» y evita la jerga («sin tecnicismos», «sin palabras complicadas»).
- **`returning`** acoge sin pedir explicaciones («no hace falta explicar nada», «sin prisas y sin
  juicios») y propone la Confesión con delicadeza, como «una puerta de vuelta» y «una guía serena,
  sin culpa», sin dar por hecho nada de la vida de la persona.
- **`practicing`** no regaña ni promete perfección: habla de profundizar, ordenar y elegir un ritmo.
- **Ninguna oración compuesta**: solo se nombran prácticas («rezar un misterio del Rosario», «leer el
  Evangelio del día», «rezar el Padrenuestro»). No se cita ni compone texto oracional.
- **Sin afirmaciones doctrinales que necesiten fuente**: por eso ningún `why` lleva número del
  Catecismo. Si el coordinador quiere añadirlo en alguna, lo indico y lo incorporo.
- **Destinos usados:** `BIBLE`, `PRAYERS`, `ROSARY`, `SAINTS`, `CATECHISM`, `QUESTIONS`,
  `CONFESSION`, `HOME`, `HABITS_VIRTUES`, `STUDY_DOCTRINE`, `SPIRITUAL_LIFE`. No uso
  `PILGRIMAGE_HABITS` ni `CHURCHES_NEWS` porque no están en la lista del encargo.
- **Idiomas naturales, no traducción literal.** Un caso: el título de la sugerencia de
  `SPIRITUAL_LIFE` era igual en ES y en PT («Vida espiritual»); lo cambié a «Caminho espiritual»
  para que los tres idiomas se distingan.

## Validación

```bash
$ python docs/tasks/tools/validate_onboarding.py \
      app/src/main/assets/onboarding/profiles.json
0 errores · 0 avisos
```

El script (dejado en `docs/tasks/tools/validate_onboarding.py`) comprueba: los tres perfiles con sus
ids e índices; `welcome` ≤35 y `description` entre 25 y 60 palabras; `title` ≤8 y `why` ≤30;
es/en/pt no vacíos **y distintos entre sí**; entre 5 y 7 sugerencias por perfil, con ids únicos en
`snake_case`; `destination` dentro de la lista permitida; `minutes` en {2, 5, 10} y las tres
duraciones presentes en cada perfil; que las sugerencias vayan de la más sencilla a la más
exigente; y que no aparezcan `TODO`, `lorem` ni URLs.

Nota: el patrón de `TODO` va con delimitadores de palabra para que no dé falsos positivos con el
«todo» español («Todo está en tu idioma»).

## Lo que no se pudo verificar

- **La pantalla:** no se ha modificado `BeginnerJourneyScreen.kt` ni `WelcomeChoices`. El
  coordinador conectará el JSON y hará las capturas; no hay pantallas ni comprobación en
  dispositivo de esta parte.
- No se ha compilado la app: no hay Android SDK en este equipo.

## Preguntas para el coordinador

1. ¿Quieres que el `minutes` de cada sugerencia sea solo orientativo o que condicione lo que se
   muestra cuando la persona eligió 2, 5 o 10 min en la bienvenida?
2. ¿Añado números del CIC en algún `why`, o lo dejo sin citas como está?
3. ¿Hace falta un campo nuevo (por ejemplo, `icon` o `tag` para el test) que deba añadir al JSON?
