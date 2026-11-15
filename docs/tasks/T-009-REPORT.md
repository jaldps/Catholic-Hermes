# T-009 · Texto completo de «Ven, Espíritu Santo» y «Quédate conmigo, Señor»

- **Asignado:** Hermes
- **Estado:** ABIERTO → entregado
- **Rama de entrega:** `ext/T-009` (en el fork)
- **Archivo creado:** `app/src/main/assets/prayers/text-overrides-b.json` (no se tocó `text-overrides.json`, que es de Codex/T-007)
- **Fecha:** 2026-11-15

## Qué hice

1. **`come_holy_spirit`** — sustituido por la secuencia completa *Veni Sancte Spiritus* (las diez estrofas, hasta «da perenne gaudium»; previamente se cortaba en «en el llanto, consuelo»).
   - ES y PT: traducción propia y fiel del latín, marcada como tal.
   - EN: traducción de Edward Caswall, *Lyra Catholica* (1849), dominio público.
   - `responseText`: dejado en `null` (el contrato del validador exige texto no vacío si el objeto está presente, y el cargador solo aplica `responseText` si no es `null`), por lo que la app conserva la respuesta añadida. Véase la pregunta 1.
2. **`stay_with_me_lord`** — sustituido por la oración **completa** atribuida a san Pío de Pietrelcina, tomada del texto italiano de los Grupos de Oración del Padre Pio (sitio oficial operapadrepio.it) y traducida fielmente a ES/EN/PT. Antes la app solo recogía el inicio.

No se tocó ningún otro archivo del repo.

## Resultado del validador

```bash
python docs/tasks/tools/validate_prayers.py overrides app/src/main/assets/prayers/text-overrides-b.json
```

```
2 entries · 0 errors · 0 warnings
```

## Fuentes (con URL)

- **Veni Sancte Spiritus (latín)**: secuencia del Misal Romano; texto latino completo en
  - <https://latinprayers.org/prayers/veni-sancte-spiritus/> y
  - <https://www.chantcd.com/lyrics/veni_sancte_spiritus.htm>.
  Dominio público. La autoría (Esteban Langton / Inocencio III / Roberto II) es discutida; no se afirma en el `textNote`.
- **EN (Caswall, 1849)**: Edward Caswall, *Lyra Catholica* (1849), texto completo en
  - <https://www.blueletterbible.org/hymns/c/come_thou_holy_spirit_come.cfm> (atribuido a Caswall, 1849) y
  - <https://en.wikisource.org/wiki/Holy_Spirit,_Lord_of_light> (nota de autoría).
  Dominio público.
- **ES y PT de Veni Sancte Spiritus**: traducción propia y fiel del latín, marcada en el `textNote`.
- **«Resta con me, Signore» (italiano)**: Grupos de Oración del Padre Pio (Opera del Padre Pio, sitio oficial):
  - <https://gruppidipreghiera.operapadrepio.it/preghiere-varie>.
  Texto completo de la oración de san Pío de Pietrelcina después de la comunión.
- **Contraste de la traducción de san Pío**:
  - <https://padrepiodevotions.org/stay-with-me-lord> (EN),
  - <https://padrepiodapietrelcina.com/es/quedate-senor-conmigo-oracion-del-padre-pio> (ES).

## Datos que no pude verificar / decisiones

- **`responseText` de `come_holy_spirit`**: la respuesta actual de la app («Oh Espíritu Santo, ven y renueva la faz de la tierra…») no pertenece a la secuencia. El contrato del validador no permite un objeto `responseText` con idiomas vacíos (exige texto si está presente) y el cargador solo sobrescribe `responseText` si no es `null`, así que dejé `null` y la app conserva la respuesta. Para retirarla haría falta tocar `PrayerAndNovenaData.kt` o ampliar el contrato.
- **`responseText` de `stay_with_me_lord`**: la respuesta corta actual («Quédate, Señor, conmigo. Amén.») tampoco es parte de la oración completa; misma limitación.
- **Final «Aleluia»**: las fuentes latinas varían (latinprayers.org termina en «Alleluia»; chantcd en «Amen, Alleluia»; Caswall no lo incluye). En ES/PT añadí «Amén. Aleluia.»; en EN dejé el texto de Caswall tal cual, sin «Alleluia».
- **Edición de la oración de san Pío**: la fuente es el texto del sitio de los Grupos de Oración; no se identifica una edición impresa concreta.

## Preguntas para el coordinador

1. ¿Retiro la `responseText` añadida de `come_holy_spirit` («renueva la faz de la tierra»)? Queda fuera de este encargo; dímelo si quieres que lo pida como tarea aparte.
2. ¿Hago lo mismo con la respuesta corta de `stay_with_me_lord`?
3. No hay campo para el texto latino completo en el contrato de overrides; si lo quieres recogido, habría que ampliarlo.
