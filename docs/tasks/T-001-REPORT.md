# T-001 · Informe de entrega

- **Encargo:** T-001 · Detalles de 34 oraciones básicas → `app/src/main/assets/prayers/details-a.json`
- **Rama:** `ext/T-001`
- **Agente:** Hermes
- **Fecha:** 2026-11-14

## Qué hice

Creé `app/src/main/assets/prayers/details-a.json` con las 34 entradas de `docs/tasks/T-001.ids`, en el orden exacto del archivo, y con las claves `prayerId`, `history`, `instructions`, `source`, `approval`, `promise`, `textNote` en ese orden. Cada campo de texto es un objeto `es`/`en`/`pt` completo y natural.

- `history`: 80–136 palabras por idioma, con origen, autor o tradición, época y uso actual.
- `instructions`: 48–97 palabras, prácticas, con postura y gestos y con el reparto de parts en las que el texto tiene V/ y R/ (Ángelus, Regina caeli, Salve, Fátima, San Miguel).
- `source`: referencia del texto en el formato de cada idioma (ES «Mt 6,9-13», EN «Matt 6:9-13», PT «Mt 6,9-13»).
- `approval`: solo uso litúrgico o de la piedad tradicional verificable; vacío no hace falta en ninguna, pero se rellenó con lo que el texto muestra.
- `promise`: `null` en las 34, como indica el encargo.
- `textNote`: «texto tradicional/litúrgico/evangélico de dominio público; traducción propia», o «composición devocional reciente; redacción propia» para «Quédate conmigo, Señor».

No toqué ningún otro archivo del repo.

## Resultado del validador

```bash
python docs/tasks/tools/validate_prayers.py details app/src/main/assets/prayers/details-a.json docs/tasks/T-001.ids
```

```
34 entries · 0 errors · 0 warnings
```

## Fuentes consultadas

- `docs/tasks/README.md` (protocolo).
- `docs/content/EDITORIAL-RULES-1.8.md` (reglas de verdad, fuentes, derechos, trilingüismo y técnica).
- `docs/content/M3-ORACIONES-SPEC.md` §1 (contrato JSON de los detalles).
- `docs/tasks/T-001.md` y `docs/tasks/T-001.ids` (encargo, pistas históricas e ids).
- `app/src/main/java/com/example/data/repository/PrayerAndNovenaData.kt` (texto actual de cada oración; para ajustar las instrucciones a quién dice V/ y quién R/).
- `app/src/main/java/com/example/data/repository/PrayerDetailsCatalog.kt` y `models/PrayerDetails.kt` (contrato de carga: claves, `promise`, `textNote`).
- `docs/tasks/tools/validate_prayers.py` (validador).

No consulté fuentes externas: los datos históricos proceden de la tradición litúrgica general y de las pistas del propio encargo. Por eso no se añadieron citas web ni números de concesión.

## Datos que no pude verificar

- **Indulgencias:** no incluí ninguna referencia al *Enchiridion Indulgentiarum* (1999) porque no disponía de las concesiones; el campo `approval` se limita al uso litúrgico y a la piedad tradicional. Si el coordinador aporta las concesiones exactas, las añado.
- **Autoría del «Memorare»:** la atribución a San Bernardo es tradicional; el autor real suele citarse como el sacerdote francés Claude Bernard (s. XVII). Lo dejé indicado como atribución tradicional.
- **Autoría de la «Salve Regina»:** atribuida tradicionalmente a Hermann de Reichenau (s. XI), sin autoría exacta probada. Indicado como tradición.
- **Visión de León XIII (1886) y el rezo tras la Misa rezada hasta 1964:** son los datos que da el propio encargo (T-001.md); la visión se presenta como tradición.
- **Aparición de Fátima y decreto de 1930:** la fecha y el obispo de Leiria son los habituales; no contrasté el texto del decreto.
- **Plegarias de Navidad, Pascua y Cuaresma:** las describí como «plegaria del Misal Romano para el tiempo de...» sin asignarlas a una misa concreta, porque no verifiqué a qué misa exacta corresponde cada collecta.
- **Números del Catecismo:** solo usé el rango 2759-2865, que venía en el ejemplo del encargo; evité otros números.

## Preguntas para el coordinador

1. ¿Quiere que el campo `approval` recoja indulgencias del *Enchiridion Indulgentiarum* (1999) con su concesión, o prefiere dejarlas fuera hasta tener las fuentes?
2. `sign_of_the_cross` y `signarse` son casi idénticas en `PrayerAndNovenaData.kt` (solo cambia el «y»). ¿Confirmo que se tratan como la misma señal y que está bien que sus detalles sean paralelos?
3. ¿El tono de las historias (catequético, sin rigorismo) es el esperado, o prefiere menos extensión en alguna?
