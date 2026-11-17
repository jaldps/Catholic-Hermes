# T-035 · Textos de los avisos litúrgicos y recordatorios

- **Asignado:** Hermes · **Rama:** `ext/T-035` (fork) · **Estado:** ABIERTO, entregado.
- **Archivos creados:** `app/src/main/assets/notices/notices.json`,
  `docs/tasks/tools/validate_notices.py`, `docs/tasks/T-035-REPORT.md`.
- No se tocó ningún otro archivo ni el código (T-032 lo hará el coordinador).

Solo contenido. El motor litúrgico ya sabe qué día es; aquí solo está lo que se dice.

## Contenido

| Bloque | Cantidad |
|---|---|
| `seasons` | 7 tiempos (ADVENT, CHRISTMAS, ORDINARY_TIME, LENT, HOLY_WEEK, TRIDUUM, EASTER): un aviso de inicio y **32 avisos semanales** (4-6 por tiempo) |
| `celebrations` | **48** claves, cada una con `eve` y `day` (17 fiestas móviles + 31 fijas) |
| `reminders` | 7 (`sunday_mass`, `holy_day_of_obligation`, `novena_day`, `plan_day`, `rosary_evening`, `examen_night`, `confession_gentle`) |

Cada aviso lleva `title` (≤6 palabras), `body` (≤25) y un `action` opcional. Los marcadores van en
el `body` de `novena_day` (`{{novena}}`, `{{day}}`) y de `plan_day` (`{{plan}}`, `{{day}}`), en los
tres idiomas.

### Criterio

- **Señal, no sermón.** Cada aviso dice qué se celebra y propone una práctica concreta y breve:
  encender una vela, leer el Evangelio del día, rezar un misterio, visitar al Santísimo, llamar a
  alguien. Se nombra la práctica, no se compone la oración.
- **Invita, no ordena.** Se evita el «no olvides» y la culpa: «Si puedes», «Si lo deseas»,
  «Prepara tu corazón».
- **Sin dato sin respaldo.** Lo que se afirma es lo que dice el Calendario Romano General o el
  Catecismo; nada de leyendas piadosas presentadas como hechos.
- **Divina Misericordia:** sin promesas ni indulgencias en el aviso; se invita a confiar
  («La misericordia de Dios es más grande que nuestras faltas»).
- **Precepto:** `holy_day_of_obligation` no dice qué días lo son, porque varía por diócesis; la app
  solo lo usará donde la configuración local lo indique.
- **`sunday_mass`** se envía el sábado por la tarde, como pide el encargo.
- **`confession_gentle`** es una invitación mensual, delicada y sin dar por hecho nada.

## Fuentes de los datos que se mencionan

- **Calendario Romano General** y el motor de la app (`data/liturgy/LiturgicalCalendar.kt`,
  `GeneralRomanSanctoral.kt`): las claves de los tiempos y de las fiestas, y el orden del año
  litúrgico. Las 48 claves de `celebrations` son las del encargo y coinciden con las del archivo
  de la app (comprobado: ninguna falta).
- **Catecismo de la Iglesia Católica** (texto incluido en la app):
  - Inmaculada Concepción: 491.
  - «María, Madre de Dios»: 495 y 509.
  - La Cátedra de san Pedro y su sucesión: 552.
  - Corpus Christi y la presencia real: 1374-1376.
  - Bautismo del Señor: 536.
  - La Misericordia y el sacramento: 1439.
- **Liturgia de las Horas y Leccionario** (las lecturas que se nombran): la Visitación y el
  Magníficat (Lc 1,39-56); el Bautismo del Señor (Mt 3,13-17); la Transfiguración (Mt 17,1-8);
  la Pasión (los cuatro evangelios); el mandamiento nuevo (Jn 13,34).
- **Bula *Ineffabilis Deus* (Pío IX, 8 de diciembre de 1854)**, para el dato de la Inmaculada
  Concepción; y el **Misal Romano** y los **santorales** para los títulos de las fiestas.
- **Devoción tradicional** (sin promesas en el aviso): Judas Tadeo, abogado de las causas
  difíciles; Santiago, el primero de los apóstoles en dar la vida (Hch 12,2).

## Validación

```bash
$ python docs/tasks/tools/validate_notices.py \
      app/src/main/assets/notices/notices.json
0 errores · 0 avisos
```

El script (dejado en `docs/tasks/tools/validate_notices.py`) comprueba: las tres claves de nivel
superior; las 7 de `seasons` con `start` y `weekly` (4-6 avisos); las **48** de `celebrations` con
`eve` y `day`; las 7 de `reminders`; `title` ≤6 y `body` ≤25 palabras; es/en/pt no vacíos y
distintos entre sí; `action` dentro de la lista permitida; los marcadores `{{…}}` en los
recordatorios que los piden; ids únicos en `snake_case`; y la ausencia de URLs, emojis,
mayúsculas sostenidas, exclamaciones múltiples, `TODO` y `lorem`.

## Ajustes hechos durante la validación

- Varios títulos de víspera pasaban de 6 palabras («Víspera de la Cátedra de san Pedro»): se
  acortaron con el patrón «Víspera: …».
- Tres avisos salían iguales en dos idiomas y se distinguieron: «Semana Santa»/«Semana da Paixão»,
  «Domingo de Ramos»/«Domingo das Palmas» y «Corpus Christi»/«Body of Christ»/«Corpo de Deus».

## Lo que no se pudo verificar

- **La notificación real:** el envío de los avisos depende de T-032 (código) y de la configuración
  local de preceptos; no hay capturas ni comprobación en dispositivo.
- **Los días exactos de las fiestas móviles:** los calcula el motor de la app; los textos no los
  fechan, solo nombran la fiesta.
- No se ha compilado la app: no hay Android SDK en este equipo.

## Preguntas para el coordinador

1. ¿Quieres que `seasons.ORDINARY_TIME` tenga 6 avisos en vez de 5 (algunos planes distinguen dos
   tramos del tiempo ordinario)?
2. ¿Hace falta un aviso para el **inicio** de cada tiempo además del de las semanas, o el `start`
   que ya hay basta?
3. ¿El `action: PLANS` de `plan_day` es válido aunque la pantalla de planes aún no exista, o lo
   dejo sin `action` hasta que exista?
