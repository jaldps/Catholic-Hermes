# T-001 · Informe de entrega (2.ª versión, tras revisión)

- **Encargo:** T-001 · Detalles de 34 oraciones básicas → `app/src/main/assets/prayers/details-a.json`
- **Rama:** `ext/T-001` (entregada en `fork/ext/T-001`; el push a `origin` está denegado)
- **Agente:** Hermes
- **Fecha:** 2026-11-14

## Qué hice en esta versión

Apliqué los cambios pedidos en `docs/tasks/T-001-REVIEW.md`:

1. **Verifiqué las afirmaciones con fuentes reales** y corregí las ocho errores señaladas:
   - `memorare`: ya no se atribuye a san Bernardo ni se dice que Claude Bernard sea su autor; ahora consta que él lo difundió y que el texto se documenta desde el s. XV dentro de *Ad sanctitatis tuae pedes, dulcissima Virgo Maria*.
   - `st_michael`: la fuente es la **preces leoninas** (León XIII, 1884; la invocación se añadió en 1886) y la supresión de 1964 (*Inter Oecumenici*, §48 j), no el «Ritual Romano». Se quitó «san Pío X la promovió» y se añadió la recomendación de Juan Pablo II en el *Regina Caeli* del 24-4-1994.
   - `stay_with_me_lord`: es la oración que **san Pío de Pietrelcina** rezaba después de la comunión; no una composición de la app. Se corrigieron `history`, `source` y `textNote`.
   - `christmas_prayer`: es la **colecta de la Misa de la Noche** de Navidad, no la de la misa del día.
   - `salve_regina`: la fuente es la Liturgia de las Horas (Completas) y la tradición monástica; se presenta la autoría (Hermann de Reichenau / Adhémar de Le Puy) como **discusión**.
   - `hail_mary`: se quitó «usada en la Liturgia de las Horas» y se reformuló lo de Éfeso (431): Éfeso definió la *Theotokos*, no fijó la oración.
   - `easter_prayer`: identificada como **colecta de la Misa del Domingo de Pascua**.
   - `lenten_prayer`: **no identificada**; se dice expresamente que no se atribuye al Misal Romano.
   - También se precisaron `angelus` (fechas según la Enciclopedia Católica) y `anima_christi` (origen s. XIV, Juan XXII 1330, la atribución a san Ignacio es un error).
2. **`textNote`**: eliminados «traducción propia» y «redacción propia». Se usa «Texto tradicional/evangélico/litúrgico de dominio público» cuando lo es; «Traducción tradicional; edición no identificada» para las colectas; y «edición no identificada» para los textos de origen o edición desconocidos.
3. **`approval`**: solo uso litúrgico concreto o concesiones del *Enchiridion Indulgentiarum* (1999) leídas en el texto oficial. Se indican las concesiones 8 §2, 2° (Anima Christi), 9 (acto de contrición), 17 §2, 1° (Magnificat), 17 §2, 2° (Ángelus y Regina caeli), 17 §2, 3° (Memorare y Salve Regina) y 18 (ángel custodio), más el testimonio público de fe para el Credo.

No toqué ningún otro archivo del repo.

## Resultado del validador

```bash
python docs/tasks/tools/validate_prayers.py details app/src/main/assets/prayers/details-a.json docs/tasks/T-001.ids
```

```
34 entries · 0 errors · 0 warnings
```

## Fuentes de cada ficha

Fuentes generales consultadas:

- *Enchiridion Indulgentiarum*, 4.ª ed. (1999), texto latino oficial: <https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html>
- *Manual of Indulgences* (USCCB, trad. oficial al inglés de la 4.ª ed.): <https://laikos.org/indulgences_1999.pdf>
- Texto actual de las oraciones: `app/src/main/java/com/example/data/repository/PrayerAndNovenaData.kt` (rama `CatholicGPT`).

| prayerId | Fuente concreta consultada | URL |
|---|---|---|
| `creed` | *Symbolum Apostolicum*; Catecismo de la Iglesia Católica, parte I; Enchiridion 1999 (testimonio público de fe, p. 340 de la ed. latina) | <https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html> |
| `our_father` | Mt 6,9-13 y Lc 11,2-4; Enciclopedia Católica, «Lord's Prayer» | <https://www.newadvent.org/cathen/09356a.htm> |
| `hail_mary` | Lc 1,28 y 1,42; Enciclopedia Católica, «Hail Mary» (Breviario de 1568) | <https://www.newadvent.org/cathen/07110b.htm> |
| `glory_be` | Liturgia de las Horas y Misal Romano (doxología) | — |
| `sign_of_the_cross` | Mt 28,19; Misal Romano | — |
| `signarse` | Mt 28,19; Misal Romano | — |
| `persignarse` | Nm 6,24-26; Misal Romano (bendición) | — |
| `act_of_contrition` | Enchiridion 1999, concesión 9 | <https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html> |
| `act_of_faith` | Devocionarios y catecismos tradicionales; San Francisco de Sales, *Introducción a la vida devota* | — |
| `act_of_hope` | Devocionarios y catecismos tradicionales | — |
| `act_of_charity` | Mt 22,37-39; devocionarios tradicionales | — |
| `grace_before_meals` | Devocionarios tradicionales; bendición de la mesa | — |
| `grace_after_meals` | Devocionarios tradicionales; bendición de la mesa | — |
| `eternal_rest` | Liturgia de las exequias (*Réquiem æternam*) | — |
| `guardian_angel` | *Ángele Dei*; Enchiridion 1999, concesión 18 | <https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html> |
| `morning_offering` | Devoción al Sagrado Corazón y Apostolado de la Oración | — |
| `evening_prayer` | Devocionarios tradicionales; Liturgia de las Horas (Completas) | — |
| `angelus` | Enciclopedia Católica, «Angelus» (campanas s. XIII, forma 1560/1612, Benedicto XIII 1724) | <https://www.newadvent.org/cathen/01486b.htm> |
| `regina_caeli` | Liturgia de las Horas, Completas (antífona pascual); Enchiridion 1999, concesión 17 §2, 2° | <https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html> |
| `salve_regina` | Enciclopedia Católica, «Salve Regina» (Hermann de Reichenau / Adhémar de Le Puy; uso en Completas); Enchiridion 1999, concesión 17 §2, 3° | <https://www.newadvent.org/cathen/13409a.htm> |
| `memorare` | Preces Latinae, «Memorare» (*Ad sanctitatis tuae pedes*, s. XV; Claude Bernard la difundió; forma fijada en el s. XIX); Enchiridion 1999, concesión 17 §2, 3° | <https://www.preces-latinae.org/thesaurus/BVM/Memorare.html> |
| `anima_christi` | Enciclopedia Católica, «Anima Christi» (s. XIV; Juan XXII 1330; la atribución a san Ignacio es un error); Enchiridion 1999, concesión 8 §2, 2° | <https://www.newadvent.org/cathen/01515a.htm> |
| `st_michael` | Preces leoninas (León XIII 1884-1886; *Inter Oecumenici* 1964, §48 j); Juan Pablo II, *Regina Caeli* 24-4-1994 | <https://www.vatican.va/content/john-paul-ii/es/angelus/1994/documents/hf_jp-ii_reg_19940424.html> |
| `fatima_prayer` | Fátima, 13-7-1917; oraciones de Fátima (santuario oficial) | <https://www.fatima.pt/en/pages/fatima-prayers> |
| `come_holy_spirit` | Misal Romano, secuencia de Pentecostés (*Veni Sancte Spiritus*) | — |
| `jesus_prayer` | *Filocalia*; Lc 18,13 y 18,38 | — |
| `magnificat` | Lc 1,46-55; Liturgia de las Horas, Vísperas; Enchiridion 1999, concesión 17 §2, 1° | <https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html> |
| `canticle_of_simeon` | Lc 2,29-32; Liturgia de las Horas, Completas | — |
| `canticle_of_zechariah` | Lc 1,68-79; Liturgia de las Horas, Laudes | — |
| `nicene_creed` | Concilios de Nicea (325) y Constantinopla (381); Misal Romano | — |
| `christmas_prayer` | Misal Romano, Misa de la Noche (colecta); texto de la colecta | <https://maternalheart.org/parishpropers/christmastide/christmas_midnight.pdf> |
| `easter_prayer` | Misal Romano, Misa del Domingo de Pascua (colecta); texto de la colecta | <https://newliturgicalmovement.org/2021/04/the-collect-for-easter-sunday.html> |
| `lenten_prayer` | **No identificada** entre las colectas del Misal o la Liturgia de las Horas; no se atribuye al Misal | — |
| `stay_with_me_lord` | Oración de san Pío de Pietrelcina después de la comunión; edición no identificada | <https://padrepiodevotions.org/stay-with-me-lord> |

## Datos que no pude verificar

- **`lenten_prayer`**: el texto latino de la app (*Omnipotens et misericors Deus, qui nos per observantiam Quadragesimae ad regnum tuum…*) no aparece en las colectas del Misal Romano ni en la Liturgia de las Horas consultadas; se declara de origen no identificado.
- **`stay_with_me_lord`**: la edición concreta de la oración de san Pío no está identificada (el texto de la app es el inicio).
- **`christmas_prayer` / `easter_prayer`**: la edición de la traducción española no está identificada; solo se identificó la colecta latina.
- **`st_michael`**: la «visión de León XIII» es tradición sin fuente documental segura; no se cita como hecho.
- **Concesión del Credo**: el *Enchiridion* concede indulgencia parcial por el **testimonio público de fe**; no encontré una concesión redactada específicamente «por rezar el Credo», así que el `approval` lo cita como testimonio de fe. Si el coordinador conoce la concesión concreta, la ajusto.
- **`sign_of_the_cross` / `signarse` / `persignarse`**: no hay una concesión específica en el *Enchiridion* consultado; el `approval` se limita al uso litúrgico.
- **Edición de las traducciones**: en general no se identificó la edición de las traducciones de la app; por eso los `textNote` usan «edición no identificada» cuando no son dominio público.

## Preguntas para el coordinador

1. ¿La concesión que tiene en mente para el Credo es la del «testimonio público de fe» (p. 340 de la ed. latina) o hay otra concreta?
2. ¿Quiere que el `approval` de las colectas de Navidad y Pascua indique también la concesión general de oír Misa o solo la identificación de la colecta?
3. El push a `origin` sigue denegado (credencial `joselanzd-hash`, `push: false`); entrego en `fork/ext/T-001`, como indicó.
