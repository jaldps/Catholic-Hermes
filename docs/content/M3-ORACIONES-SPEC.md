# M3 · Oraciones: historia, instrucciones, fuentes y oraciones nuevas

Antes de empezar, lee `docs/content/EDITORIAL-RULES-1.8.md`. Sus reglas son obligatorias.

## 1. Contrato

Modelos nuevos en `app/src/main/java/com/example/data/models/PrayerDetails.kt`:

```kotlin
data class PrayerDetails(
    val prayerId: String,                 // = VaticanPrayer.id
    val history: LocalizedText,           // 80–180 palabras: origen, autor, época, uso en la Iglesia
    val instructions: LocalizedText,      // cómo rezarla: momento, postura, gestos, partes V/R, repeticiones; 40–120 palabras
    val source: LocalizedText,            // texto original/edición: p. ej. «Liturgia de las Horas, Completas»; «Enchiridion Indulgentiarum (1999)»; «Diario de Santa Faustina, n. 1319»
    val approval: LocalizedText,          // uso litúrgico, inclusión en el Enchiridion, aprobación de letanía (p. ej. «aprobada para el culto público por San Pío X, 1909»). Vacía si no hay nada verificable.
    val promise: NovenaPromise? = null,   // mismo tipo que en novenas (PromiseKind SOURCED/ECCLESIAL_GRANT/TRADITIONAL/NONE); null = sin promesa
    val textNote: LocalizedText? = null   // p. ej. «Traducción propia del latín» o «Texto tradicional de dominio público»
)
```

- **Detalles de las 68 oraciones existentes.** Van en `data/repository/PrayerDetailsData1.kt`, `PrayerDetailsData2.kt` y `PrayerDetailsData3.kt`, con unas 23 entradas cada uno y un máximo de unas 800 líneas por archivo. Se unen en `PrayerDetailsData.byId: Map<String, PrayerDetails>`.
  - Los ids son los de `PrayerAndNovenaData.kt`, en este orden: creed, angelus, laudes, st_michael, memorare, anima_christi, morning_offering, act_of_contrition, our_father, hail_mary, glory_be, fatima_prayer, regina_caeli, salve_regina, ave_regina_caelorum, alma_missionum, sub_tuum_praesidium, act_of_faith, act_of_hope, act_of_charity, act_of_spiritual_communion, peace_prayer, come_holy_spirit, veni_creator_spiritus, guardian_angel, grace_before_meals, grace_after_meals, eternal_rest, adoro_te_devote, o_sacrum_convivium, tantum_ergo, laudes_divinas, sacred_heart_consecration, jesus_prayer, st_joseph, st_jude_thaddeus, st_anthony, evening_prayer, suscipe_prayer, immaculate_conception, stay_with_me_lord, signarse, persignarse, bendita_sea_tu_pureza, nicene_creed, advent_antiphons, canticle_of_simeon, canticle_of_zechariah, chaplet_of_st_michael, christmas_prayer, confiteor, divine_mercy_opening_prayer, divine_mercy_closing_prayer, easter_prayer, holy_god, lenten_prayer, litany_of_loreto, litany_of_the_sacred_heart, magnificat, prayer_before_crucifix, prayer_for_the_dead, prayer_to_st_patrick, prayer_to_st_rita, prayer_to_st_therese, sign_of_the_cross, te_deum, armor_of_god, eternal_father.
  - Escritor 1 cubre los ids 1–23; escritor 2, del 24 al 46; escritor 3, del 47 al 68.
- **Oraciones nuevas: unas 30.** Van como `VaticanPrayer` en `data/repository/PrayersExtraData.kt`, como `val extraPrayers: List<VaticanPrayer>`, y sus detalles en `PrayerDetailsExtra.kt`. El integrador las añade a la lista de oraciones del repositorio.
  - **Requisitos:** solo oraciones reales con historia documentable, con texto de dominio público o con traducción propia de un original de dominio público.
  - **Antes de añadir una:** comprobar que no existe ya con otro id.
  - **Candidatas:**
    - Letanía de San José (aprobada en 1909).
    - Letanía del Santísimo Nombre de Jesús.
    - Letanía de la Preciosísima Sangre (1960).
    - Ave Maris Stella.
    - Stabat Mater, en su forma de oración.
    - Totus Tuus / consagración a María de San Luis M.ª Grignion de Montfort, versión breve.
    - Oración de abandono de Charles de Foucauld.
    - Oración antes del estudio de Santo Tomás (*Ante studium*).
    - Acción de gracias después de la comunión de Santo Tomás (*Gratias tibi ago*).
    - Oración antes de la Misa de San Ambrosio, atribuida.
    - «Nada te turbe» de Santa Teresa.
    - Oración de San Agustín al Espíritu Santo («Respira en mí…»), con la atribución tratada con prudencia.
    - Oración del cardenal Mercier al Espíritu Santo.
    - Oraciones del Ángel de Fátima («Dios mío, yo creo…»; «Santísima Trinidad…»).
    - Cántico de las criaturas de San Francisco.
    - Oración de San Ricardo de Chichester.
    - Oración por el Papa (*Oremus pro Pontifice*).
    - Oración por las vocaciones.
    - Bendición de San Benito / *Vade retro*.
    - Oración de San Bernardo «Mira la estrella».
    - Letanía de la humildad, atribuida al cardenal Merry del Val: decirlo con exactitud, porque no es una letanía aprobada para el culto público.
    - *Angele Dei* en latín, si no está.
    - Oración de Santa Gertrudis por las almas: registrar la promesa de «1000 almas» como añadido sin fuente.
    - Oración de San Juan Pablo II a la Virgen de Guadalupe (1979): solo si su texto no tiene derechos; si los tiene, omitirla.
  - **Campos de cada `VaticanPrayer` nuevo:** el `category` existente más adecuado, `leaderText` (texto completo ES/EN/PT), `responseText` si hay oración final, `whenToPray`, `indulgenceNote` solo si la indulgencia es verificable y sin números de concesión inventados, y `thematicTags` del vocabulario existente.

## 2. Interfaz (integrador)

En el detalle de la oración, que hoy muestra `whenToPray` e `indulgenceNote` (~`PrayersAndNovenasScreen.kt` l.1255), cuando exista `PrayerDetails` se añaden estas secciones con los wrappers del kit:

| Sección | Tag | Contenido |
|---|---|---|
| «Cómo rezarla» | `prayer_instructions` | `instructions` |
| «Historia» | `prayer_history` | `history`; debajo, en pie de página, `source` y `textNote` |
| «Aprobación» | `prayer_approval` | `approval`, si no está vacía |
| «Promesa» | `prayer_promise` | igual que en las novenas: insignia de tipo, atribución, fuente y condiciones |

- Reutiliza el componente de promesa de las novenas si se puede extraer sin duplicar código.
- Tests:
  - `PrayerDetailsTest`: cada id existente tiene detalles; ES/EN/PT no vacíos en `history` e `instructions`; ids únicos; las oraciones extra no colisionan con las existentes.
  - Un test de UI que abre una oración y comprueba `prayer_history`.

## 3. Criterios

- Historia exacta: autor, siglo y aprobación solo si son verificables. Las atribuciones dudosas se dicen como tales; por ejemplo, la «Oración de San Francisco» es de 1912, de *La Clochette*, anónima.
- Instrucciones prácticas, sin rigorismo.
- Los tres idiomas naturales y completos.
