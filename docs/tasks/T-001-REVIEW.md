# T-001 · Revisión del coordinador

**Veredicto: CAMBIOS PEDIDOS.** La estructura es correcta: `34 entries · 0 errors · 0 warnings`, y el tono y la extensión están bien. El contenido histórico tiene errores, y el informe dice que **no se consultaron fuentes externas**. El encargo exige verificar cada dato.

## Entrega

Tu push a `jaldps/Catholic-Hermes` está denegado: tu credencial no tiene permiso de escritura. **Sigue entregando en tu fork** (`joselanzd-hash/Catholic-Hermes`, rama `ext/T-001`); el coordinador lo lee desde ahí. Lee esta revisión y los encargos siempre desde `origin/CatholicGPT`.

## Cambios obligatorios

1. **Verifica cada afirmación con fuentes reales**: vatican.va, *Enchiridion Indulgentiarum* 1999 (texto oficial en vatican.va), Catholic Encyclopedia (newadvent.org), Misal Romano y sitios oficiales de santuarios u órdenes. Escribe en el REPORT la fuente de cada ficha, con la URL; en el JSON no van URL. Lo que no puedas verificar, quítalo o preséntalo como «según la tradición».
2. **`textNote`**: no pongas «traducción propia». Los textos de la app no son traducciones nuestras.
   - Usa «Texto tradicional de dominio público» solo si lo es.
   - Si es una traducción moderna de edición desconocida, escribe «Traducción tradicional; edición no identificada».
3. **Errores concretos que hay que corregir:**
   - **`memorare`**: Claude Bernard (s. XVII) **difundió** el Memorare; no es su autor. El texto se documenta desde el s. XV como parte de una oración más larga (*Ad sanctissimam Virginem*). Verifica y corrige.
   - **`st_michael`**: la fuente no es el «Ritual Romano». La oración breve formó parte de las preces leoninas tras la Misa rezada (León XIII, 1886) y se retiraron en 1964 (*Inter Oecumenici*). Verifica también «san Pío X la promovió»; si no hay fuente, quítalo. Juan Pablo II la recomendó en el *Regina Caeli* del 24‑4‑1994: verifica y cítalo si procede.
   - **`stay_with_me_lord`**: el texto de la app es el inicio de la oración «Quédate conmigo, Señor» que **san Pío de Pietrelcina** rezaba tras la comunión. No es una «composición reciente de redacción propia». Corrige `history`, `source` y `textNote` (oración atribuida a san Pío; edición no identificada).
   - **`christmas_prayer`**: es la colecta de la **Misa de la Noche** de Navidad (*Deus, qui hanc sacratissimam noctem…*), no la de la misa del día. Corrige.
   - **`salve_regina`**: el Misal Romano no es su fuente. La fuente es la Liturgia de las Horas (Completas) y la tradición monástica. La atribución a Hermann de Reichenau es discutida; también se menciona a Ademar de Le Puy. Preséntalo como discusión.
   - **`hail_mary`**: «usada en la Liturgia de las Horas» no se sostiene así; verifica o quítalo. Que Éfeso (431) «consolidó la oración» es impreciso: Éfeso definió la *Theotokos*, no fijó la oración. Reformúlalo.
   - **`easter_prayer`, `lenten_prayer`**: identifica el texto exacto (Misal, Liturgia de las Horas u otro) a partir del texto de la app. Si no lo identificas, dilo y no lo atribuyas al Misal.
   - **`approval` de las 34**: escribe solo lo verificable: uso litúrgico concreto o una concesión del *Enchiridion* 1999 que hayas leído en el texto oficial. Por ejemplo, Ángelus/Regina caeli, Credo y Acto de contrición tienen concesiones parciales. No lo rellenes «con lo que el texto muestra».
4. **Respuestas a tus preguntas:**
   - Indulgencias: sí, inclúyelas, pero **solo** desde el texto oficial del *Enchiridion* 1999, con su acto y sus condiciones.
   - `sign_of_the_cross` y `signarse`: trátalas como fichas paralelas, pero que cada una describa su texto exacto.
   - Extensión: correcta.

Cuando termines, vuelve a validar, actualiza el REPORT con las fuentes de cada ficha y haz push a `ext/T-001` en tu fork.
