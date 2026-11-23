# T-060 · Informe de entrega — espacios antes de la puntuación en la Biblia incluida

- **Rama:** `ext/T-060`, desde `origin/CatholicGPT` actualizado.
- **Script:** `tools/bibles/fix_punctuation_spacing.py` (con `--dry-run`). Quita solo espacios/tabuladores inmediatamente antes de `. , ; : ! ? »`. No toca `¿`, `¡` ni `«`. Escribe con `separators=(",",":")`, `ensure_ascii=False` y sin salto final, igual que los originales.
- **Formato comprobado:** mismos 73 libros, mismo número de versículos, mismo orden de claves; diff de una línea por archivo.

## Recuentos

| Idioma | Versículos cambiados | Espacios eliminados | Versículos totales |
|---|---:|---:|---:|
| es | 3069 | 3080 | 35818 |
| en | 23 | 23 | 35746 |

### Español, por libro

| libro | versículos | libro | versículos |
|---|---:|---|---:|
| 1ch | 3 | heb | 128 |
| 1co | 160 | hos | 5 |
| 1jo | 69 | isa | 6 |
| 1ki | 20 | jam | 50 |
| 1ma | 1 | jde | 17 |
| 1pe | 58 | jdt | 1 |
| 1sa | 12 | jer | 1 |
| 1th | 38 | job | 9 |
| 1ti | 48 | joh | 317 |
| 2ch | 2 | jos | 3 |
| 2co | 107 | jud | 6 |
| 2jo | 11 | lev | 9 |
| 2ki | 17 | luk | 291 |
| 2pe | 36 | mal | 2 |
| 2sa | 5 | mar | 158 |
| 2th | 14 | mat | 250 |
| 2ti | 30 | mic | 3 |
| 3jo | 10 | nah | 2 |
| act | 405 | num | 11 |
| amo | 1 | oba | 2 |
| bar | 1 | phi | 38 |
| col | 39 | phm | 6 |
| dan | 8 | pro | 1 |
| deu | 7 | psa | 5 |
| ecc | 1 | rev | 256 |
| eph | 72 | rom | 143 |
| est | 1 | rut | 1 |
| exo | 18 | sir | 2 |
| eze | 35 | tit | 22 |
| gal | 62 | tob | 1 |
| gen | 20 | wis | 2 |
| hab | 2 | zec | 5 |
| hag | 1 | zep | 2 |

### Inglés, por libro

| libro | versículos |
|---|---:|
| 2ki | 1 |
| bar | 1 |
| exo | 1 |
| gen | 1 |
| jer | 5 |
| jud | 1 |
| psa | 8 |
| rut | 2 |
| sir | 1 |
| wis | 1 |
| zec | 1 |

**Nota sobre el recuento del encargo:** el brief cita 3.048 versículos en español y 16 en inglés; el script encuentra 3.069 y 23 respectivamente. La diferencia son casos del mismo patrón (espacio antes de `:` y `?`, sobre todo) que el recuento previo no incluyó. No se ha eliminado ningún espacio que no preceda a un signo de cierre; la comprobación inversa (`quitar esos espacios` → texto final) da 40/40 en la muestra aleatoria y 0 diferencias inesperadas en los dos archivos completos. El portugués no se tocó (0 casos).

## Cinco ejemplos (antes → después)

```text
[es gen 4:10]
ANTES: Y dijo (Yahvé) : “¿Qué has hecho? La voz de la sangre de tu hermano está clamando a Mí desde la tierra.
DESPUÉS: Y dijo (Yahvé): “¿Qué has hecho? La voz de la sangre de tu hermano está clamando a Mí desde la tierra.

[es joh 1:1]
ANTES: En el principio el Verbo era, y el Verbo era junto a Dios, y el Verbo era Dios .
DESPUÉS: En el principio el Verbo era, y el Verbo era junto a Dios, y el Verbo era Dios.

[es joh 1:5]
ANTES: Y la luz luce en las tinieblas, y las tinieblas no la recibieron .
DESPUÉS: Y la luz luce en las tinieblas, y las tinieblas no la recibieron.

[en gen 15:4]
ANTES: And immediately the word of the Lord came to him, saying : He shall not be thy heir: but he that shall come out of thy bowels, him shalt thou have for thy heir.
DESPUÉS: And immediately the word of the Lord came to him, saying: He shall not be thy heir: but he that shall come out of thy bowels, him shalt thou have for thy heir.

[en rut 2:5]
ANTES: And Booz said to the young man that was set over the reapers: Whose maid is this ?
DESPUÉS: And Booz said to the young man that was set over the reapers: Whose maid is this?

```

## Casos raros

- **Números:** 0 casos de `1 .` u otro dígito seguido de espacio y puntuación.
- **Puntos suspensivos:** 0 casos de `… .`.
- **Paréntesis y comillas de cierre:** abundantísimos en español, p. ej. `(Yahvé) :` → `(Yahvé):`. No se encontró ningún caso con `»` en los tres archivos; el script lo cubre de todas formas.
- **Signos de apertura:** se comprobó que no se tocó ningún espacio delante de `¿`, `¡` ni `«`.
- Se revisaron 20 casos aleatorios del español y 20 del inglés contra el texto original; los 40 coinciden exactamente con quitar solo los espacios permitidos.

## Tests

Ejecutados con Java 21 (`./gradlew :app:testDebugUnitTest --tests … --no-daemon`). Recuentos del XML de resultados:

| Clase | Tests | Fallos | Errores |
|---|---:|---:|---:|
| `BiblePunctuationTest` (nuevo) | 1 | 0 | 0 |
| `BibleIntegrityTest` | 8 | 0 | 0 |
| `CrossReferenceEditionTest` | 3 | 0 | 0 |
| `BibleEditionsTest` | 6 | 0 | 0 |
| `LectionaryEditionTest` | 3 | 0 | 0 |
| `MissalTest` | 8 | 0 | 0 |
| `PsalmNumberingTest` | 10 | 0 | 0 |
| `ReadingPlansLogicTest` | 10 | 0 | 0 |
| `BibleReadingJourneyTest` | 2 | 1 | 0 |

Total: 51 tests, 1 fallo. El único fallo es `BibleReadingJourneyTest.aSearchCanContinueBeyondItsFirstTwentyResults`, con `androidx.test.espresso.AppNotIdleException` en `BibleReadingJourneyTest.kt:61`. Es el mismo tipo de fallo de entorno (`AppNotIdleException`) documentado en la revisión de T-055 para `HomePrayerPrioritiesTest`: no depende del texto de la Biblia. No se ha modificado ningún archivo de esa prueba. **Verificado en el commit base**: en un worktree de `origin/CatholicGPT` @ `7f57e51` (sin los cambios de T-060) el mismo test falla igual, `AppNotIdleException` en `BibleReadingJourneyTest.kt:61`, `BUILD FAILED in 16m 6s`.

## Datos no verificados / preguntas

- El recuento del brief (3.048 / 16) no coincide con el observado (3.069 / 23); se documenta arriba. Pregunta al coordinador si quería un subconjunto más estrecho.
- No se han tocado las ediciones descargables (`bibles-data`).
