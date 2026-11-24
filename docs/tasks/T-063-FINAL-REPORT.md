# T-063 · Corrección integral de las 55 coronillas (1.9.5)

## Alcance

- Se han revisado las **55 coronillas** del catálogo «Otras coronillas».
- Quedan fuera, por estar ya bien implementadas y ser anteriores, el **Rosario original** y la **Coronilla de la Divina Misericordia**.
- Cada devoción conserva en su JSON la URL exacta de su fuente; las 45 URLs distintas están registradas en el ledger de citas y el texto descargado se guardó en `docs/content/coronillas/sources/`. Las fuentes son Catholic Doors[2], IVE[1] y Hozana[8].

## Criterio

1. **Una cuenta = una oración**. Se han separado las oraciones o jaculatorias que estaban pegadas en un mismo `text` (p. ej. San Miguel[5], Santa Faz, Unidad, Vía Crucis[45]).
2. **Oraciones canónicas completas**: allí donde una cuenta decía sólo «Avemaría» o «Padre Nuestro», se usa `prayerRef`, con lo que la app muestra el texto íntegro y separa V/R cuando la oración lo tiene. La coronilla de San Gabriel[38] usaba el saludo abreviado «Hail, full of grace…»; ahora usa el Ave María completo.[1]
3. **V/R reales**: los bloques con dos voces se modelan con `vr` y sin prefijos `V/` `R/` en el contenido. Se han eliminado los `vr` inventados donde la fuente no da diálogo (Santa Faz, Santo Nombre, Trinidad).
4. **Rezo en conjunto**: Credo, Acto de Contrición y jaculatorias sin réplica llevan `rubric` «Se reza en conjunto / Said together / Rezado em conjunto».
5. **Instrumento y orden**: se han corregido cuentas sobrantes o ausentes, medallas y separadores según el patrón descrito por cada fuente; ya no se invierte ni se reordena arbitrariamente el hilo.
6. **Texto fidedigno**: se conserva literalmente el texto de la fuente, incluidas sus erratas documentadas (p. ej. «holly» en Lágrimas[7] o el «0» de Guadalupe), y se anota en `notes` cualquier incoherencia de la fuente.

## Correcciones principales por lote

- **Lote 1**: San Miguel restaura la invocación en la medalla y separa antífona, V/R y oración final; Coronilla de Lágrimas reemplaza la meditación inventada por la de Simeón y añade la oración final; Difuntos-45 completa el final; Conversión restaura las Avemarías de las cuentas grandes; San José corrige el hilo y añade la cuenta grande que faltaba.[5][7]
- **Lote 2**: Estrella del Mar separa las dos invocaciones que estaban en una cuenta; Guadalupe restituye las cuatro oraciones de las apariciones; Cabrini retira la Señal de la Cruz y las cuentas grandes inventadas; Jesús de Praga añade el versículo antes de **cada** Padre Nuestro y Avemaría.[2]
- **Lote 3**: Misericordia y Perdón quita la elipsis del Padre Nuestro inicial y añade los pasos omitidos; Padre Pío reparte la oración larga en tres grupos; Rosario del Espíritu Santo mueve el Gloria final a su cuenta; Madre de la Eucaristía usa el literal de la fuente.[2]
- **Lote 4**: San Judas separa las cuatro oraciones finales; Sagrada Eucaristía reparte las 33 repeticiones por las 33 cuentas; Sangre Preciosa retira el Signo de la Cruz inventado y corrige «TERCERA MISTERIO»; San Gabriel usa el Ave María completo.[2]
- **Lote 5**: Santa Faz convierte el `vr` inventado en rezo en conjunto y añade la sexta cuenta grande; Santísima Trinidad mueve los Glorias después de cada decena; Santo Nombre elimina los `vr` sin diálogo; Unidad separa las cuatro invocaciones finales; Vía Crucis marca «Te adoramos» como jaculatoria en conjunto.[1][45]

## Validación

- `validate_chaplets.py`: **55 files · 0 errors · 1 warning** heredero y documentado (`coronilla-difuntos-45`, 3 oraciones en un grupo de 5, explicado en `notes`).
- `validate_chaplets_fidelity.py` (nuevo): **55 files · 944 steps · 482 prayerRefs · 44 V/R · 241 recitaciones en conjunto · 0 errors**.
- Tests: **15/15 en verde** — `ChapletGuideAuditTest` 1/1 (55 guías construidas), `OtherChapletsEntryCrashTest` 2/2, `OtherChapletsEntryMatrixTest` 8/8, `ChapletVariablesMarkerTest` 4/4.
- APK 1.9.5 firmado con el certificado Hermes Preview (`b634b6b8…`), `versionCode=45`, `application-label='Catholic Hermes 1.9.5'`, SHA-256 `06491bd54e007b4a9eccbcfbd3c336d622e54fa68f9b43d79f11031b6dbbd3af`, 110796732 bytes.

## Nota

El force close corregido en 1.9.4 (regex estático en `ChapletVariables`) no aparece en esta versión; el motor y esa corrección se conservan.

## Sources

[1] https://iveinarabic.org/es/rosarios-y-coronas — IVE · Rosarios y coronas
[2] https://www.catholicdoors.com/prayers/chaplets/chap34.htm — Catholic Doors · Chaplet of Saint Frances Xavier Cabrini
[5] https://www.catholicdoors.com/prayers/chaplets/chap08.htm — The Raccolta, 1910, n.º 291, pp. 261–263 (texto de T-007)
[7] https://www.catholicdoors.com/prayers/chaplets/chap37.htm — Catholic Doors · Chaplet of Tears
[8] https://hozana.org/es/oracion/santo-rosario/de-liberacion — Hozana · Coronilla de liberación
[38] https://www.catholicdoors.com/prayers/chaplets/chap32.htm — Catholic Doors · Chaplet of Saint Philomena
[45] https://www.catholicdoors.com/prayers/chaplets/chap06.htm — Catholic Doors · Chaplet of the Way of the Cross
