# T-008 · Revisión intermedia del coordinador (entrega parcial 3)

**Veredicto parcial: BIEN ENCAMINADO.** Continúa con las 12 restantes. El coordinador integrará todo junto cuando termines, o antes si lo pide el usuario.

Las tres recuperaciones cumplen la regla del usuario: texto real de fuente comprobada, cotejado imagen a imagen, sin rellenos.
- **Pilar:** edición de 1855.
- **San Ramón Nonato:** edición de 1780, con la laguna del ejemplar de 1825 detectada.
- **Copacabana:** texto de 1621 tomado de los JP2 sin recortar.

## Cambio pedido (pastoral)

- **Pilar, p. 49:** omite del cuerpo el «obsequio» de **disciplina corporal o mortificación**. No lo reproduzcas, ni siquiera como rúbrica histórica.
  - En `origin` o `history` de la procedencia, indica que «se omite una práctica penitencial corporal propuesta por el impreso de 1855».
  - Añade una `pastoralNote` breve: la penitencia cristiana nunca debe dañar la salud; se acompaña con el confesor.
  - Es la única omisión permitida, y queda declarada.
- **Mismo criterio para las demás:** si alguna novena propone autolesión, ayunos extremos u otras prácticas que pongan en riesgo la salud, omítelas y decláralo de la misma forma.

## Recordatorio

Las afirmaciones hagiográficas del impreso, como los «24.000 milagros» de Nonato, quedan como afirmación del texto antiguo, igual que hiciste. No las presentes como hechos verificados.

## Revisión intermedia 2 (5/15)

**Integradas en `CatholicGPT`:** Altagracia (265) y Chiquinquirá (266), con commit `0d1f2b6`.
- **Altagracia:** Plan de Pastoral de 2021 de las diócesis dominicanas.
- **Chiquinquirá:** impreso diocesano de 2012, con oraciones de Juan Pablo II (1986) cotejadas en vatican.va.

Ambas fuentes son PDF aportados por el usuario, cotejados por SHA‑256. Las notas pastorales están presentes. Continúa con las 10 restantes con el mismo criterio.

## Revisión intermedia 3 (informe `efebabf`)

He revisado el informe. Las fuentes y los límites que documentas son correctos: no rellenaste campos ni atribuiste ediciones por conjetura. Desbloqueo dos novenas.

### Corrección del contrato

El encargo decía «si la fuente no trae un campo, déjalo vacío», pero la validación exigía `meditation` no vacía. Gana la primera regla:

- **`meditation` puede ir `""` en los tres idiomas** cuando la fuente no trae meditación separada. La app ya oculta la meditación vacía; lo comprobé en el código.
- **`prayer` sigue siendo obligatorio.**
- No conviertas oraciones en meditaciones ni rellenes nada con texto propio.

### Aparecida: aprobada para entregar

- **Fuente:** el formulario del CEPAR / Misioneros Javerianos (PDF de 2 páginas, nueve oraciones), cotejado con *The Raccolta* (1910), n.º 199.
- **Cuerpo:** las nueve oraciones fieles, una por día. `meditation` va vacía.
- **`origin`, dicho tal cual:** «formulario tradicional de la novena de la Inmaculada (Raccolta 1910, n.º 199) con invocación final a Nuestra Señora Aparecida; no es el libro del santuario de 2026». La invocación final no lleva autor ni fecha.
- **Traducción:** fiel, y declarada en `origin`.

### Luján: aprobada para entregar

- **Fuente:** la *Sagrada novena* en la edición del IVE (nuestrocarisma.org, 2021), basada en Maqueda (1812).
- **Cuerpo:** las plegarias de cada día van fieles en `prayer`.
- **Intenciones:** las del instituto van en `intentionGuidance`. Las citas de sus Constituciones y Directorios se pueden omitir; si las omites, decláralo.
- **`origin`:** «texto publicado por Maqueda en 1812; español actualizado e intenciones añadidas en la edición del Instituto del Verbo Encarnado (2021)». No atribuyas los añadidos al original.
- No encontraste reserva expresa, así que no va línea de «Derechos».

### Las otras 8

Siguen pendientes con las limitaciones de tu informe: Coromoto, Suyapa, Señor de los Milagros, Divino Niño, San Pancracio, Santa Eduviges, São Benedito y José Gregorio Hernández. No hace falta insistir ahora. Retómalas si aparece un ejemplar íntegro cotejable.

### Orden de trabajo

1. Entrega Aparecida (cuerpo `267.json`) y Luján (cuerpo `268.json`) en `ext/T-008`, en un solo push.
2. Después sigue con T-012.

## Revisión intermedia 4 (`78ad543`, 7/15)

**Integradas:** Aparecida (267) y Luján (268).
- Cada una tiene 9 días; la meditación va vacía, como permite la revisión 3.
- La procedencia declara la fuente, la traducción y las omisiones.
- El estado queda en `pastoral_review` y las notas pastorales están completas.
- Índice, hashes y recuentos actualizados: 254 novenas. Los tests de novenas y `EditorialReliabilityTest` pasan.

Las 8 restantes siguen pendientes, con las limitaciones de tu informe. Prioridad ahora: T-013 y T-014.

## Cierre (7/15)

T-008 se cierra con 7 novenas integradas. El usuario aportó fuentes concretas para las 8 restantes y decidió cómo tratar sus derechos. Ese trabajo continúa en **T-015** (Hermes). Gracias, Codex: la documentación de búsqueda de tu informe sirvió para decidir.
