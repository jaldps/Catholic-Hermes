# T-029 · Contenido de los planes de lectura (M12, paso 5)

- **Asignado:** Hermes · **Rama:** `ext/T-029` (fork) · **Estado:** ABIERTO, entregado.
- **Archivos creados:** `app/src/main/assets/plans/bible-year.json`,
  `app/src/main/assets/plans/catechism-year.json`,
  `docs/tasks/tools/validate_plans.py`, `docs/tasks/T-029-REPORT.md`.
- No se tocó ningún otro archivo.

Son **solo listas de referencias** (libro y capítulos, o párrafos). No se copia texto bíblico ni del
Catecismo: la app ya lo tiene y lo resuelve.

## 1. `bible-year.json` · La Biblia en un año

365 días. Cada día lleva lectura del Antiguo Testamento, del Nuevo Testamento y un salmo o un
proverbio, repartidos de forma proporcional.

### Criterio de reparto

Se montaron tres corrientes en el orden canónico de `BibleData.catholicCanonBooks`:

| Corriente | Libros | Capítulos |
|---|---|---|
| Antiguo Testamento (sin Salmos ni Proverbios) | `gen` … `mal` | 893 |
| Nuevo Testamento | `mat` … `rev` | 260 |
| Salmos y Proverbios | `psa` 1-150, `pro` 1-31 | 182 |

Para cada corriente se calcula la meta acumulada `round(n·día/365)` y se reparte lo que queda
hasta esa meta. Así la carga queda plana a lo largo del año y no hay días vacíos ni maratones.
Los salmos y proverbios se colocan **en días que ya tienen lectura del Nuevo Testamento**, para
que el mayor número de días tenga las tres partes.

### Carga

- **Total:** 1335 lecturas (1334 capítulos; el Salmo 119 se parte en dos mitades, abajo).
- **Por día:** entre **2 y 5 lecturas**, media **3,66**.
- Días con lectura del Nuevo Testamento: 260. Días con salmo o proverbio: 182.
- **Días con las tres partes: 182.**

### Decisiones

- **Salmos en numeración hebrea** (1-150), como pide el encargo. La conversión a cada Biblia la
  hace la app; el coordinador ya confirmó en la revisión de T-023 que ajustará el resolutor.
- **Salmo 119:** se parte en dos días, `psa 119:1-88` y `psa 119:89-176`, por ser un capítulo muy
  largo. Es la única referencia con versículos del plan.
- **Libros deuterocanónicos:** se leen enteros en el orden canónico: `tob`, `jdt`, `est` (16
  capítulos, con las partes griegas), `1ma`, `2ma`, `wis`, `sir`, `bar`, y `dan` (14 capítulos,
  con las partes griegas). El validador los trata como capítulos normales porque la Biblia
  incluida los tiene así.
- **No todos los días tienen las tres partes, y es aritméticamente imposible que sea así:** el
  Nuevo Testamento tiene 260 capítulos y Salmos más Proverbios, 182, menos que 365 días. Si se
  exigiera lectura del NT o del salmo todos los días, habría que repetir libros o leer dos veces
  una parte. Se optó por repartir sin repetir, y por que 182 días sí tengan las tres partes.
- **No se copia ninguna tabla de otros planes:** el reparto se genera con el criterio de arriba.

## 2. `catechism-year.json` · El Catecismo en un año

365 días, con rangos **inclusivos** de párrafos que cubren del **1 al 2865 sin huecos ni solapes**.

- **Por día:** entre **7 y 8 párrafos**, media **7,85**.
- Corte por reparto uniforme (`round(restantes/días que quedan)`), de modo que no se acumula al
  final.
- **Límites de sección:** no se dispone en la app de un índice de secciones del Catecismo, así
  que el corte es numérico. Si el coordinador quiere cortar por artículos o secciones, hace falta
  ese índice y lo ajusto.

## Validación

```bash
$ python docs/tasks/tools/validate_plans.py \
      app/src/main/assets/plans/bible-year.json \
      app/src/main/assets/plans/catechism-year.json
bible-year.json: 365 dias · 1335 lecturas · capitulos distintos cubiertos 1334/1334 · carga 2-5 (media 3.66)
catechism-year.json: 365 dias · carga 7-8 (media 7.85) · total 2865
0 errors · 0 warnings
```

El script (dejado en `docs/tasks/tools/validate_plans.py`) comprueba: JSON válido; 365 días
numerados del 1 al 365; `title` y `description` con `es`/`en`/`pt` no vacíos; que cada referencia
bíblica tenga un id de libro válido y capítulos existentes (contados de
`app/src/main/assets/bible/bible_es.json`, con los ids de `BibleData.catholicCanonBooks`); que la
unión cubra **todos** los capítulos de los 73 libros; y que el Catecismo vaya del 1 al 2865
exacto, sin huecos ni solapes.

## Lo que no se pudo verificar

- **La resolución real en la app:** los salmos van en numeración hebrea y la conversión depende
  del ajuste del resolutor que el coordinador dijo que haría tras T-023. Sin ese ajuste, los
  salmos caerían en el capítulo siguiente en las ediciones incluidas.
- **La pantalla de planes:** no existe todavía; no hay capturas ni comprobación en dispositivo.
- No se ha compilado la app: no hay Android SDK en este equipo.

## Preguntas para el coordinador

1. ¿Te vale que 105 días no tengan lectura del Nuevo Testamento (y 183 no tengan salmo), o prefieres
   que el plan repita alguna parte para que todos los días tengan las tres?
2. ¿Quieres que el plan del Catecismo corte por secciones del CIC? Si me pasas el índice de
   secciones, lo ajusto.
3. ¿El Salmo 119 partido en dos mitades te parece bien o lo dejo entero en un día?
