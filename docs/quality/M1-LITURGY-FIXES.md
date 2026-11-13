# M1 — Correcciones de confianza: noche, Biblia e indulgencias

Fecha: 13 de noviembre de 2026. Alcance: correcciones acotadas del recorrido actual descrito en [CONTENT-LITURGY-AUDIT.md](CONTENT-LITURGY-AUDIT.md). No se implementa un breviario, un misal ni una nueva concesión de indulgencias.

## Noche

- `NightPrayerData.kt` y `NightPrayerModels.kt`: los nombres públicos identifican una oración inspirada en Completas. Se mantienen `ROMAN_COMPLINE`, `complineOffice` y las demás claves internas para compatibilidad.
- `NightPrayersScreen.kt`: tarjeta y lector declaran «selección devocional abreviada» en ES/EN/PT. Una nota discreta antes del control de escucha aclara que no es el oficio completo del día, que los extractos varían según el idioma y que no se aplican todas las rúbricas o variaciones del calendario. La escucha se denomina oración.
- El salmo se identifica como extracto en los datos y en el encabezado visible del lector, en los tres idiomas. Se elimina la afirmación falsa de contener los vv. 1–16. Se conserva la referencia al salmo 91 (90), sin inventar una extensión común a textos de distinta longitud.
- Los textos de oración, extractos, latín, antífonas, navegación, progreso y etiquetas de prueba se conservan. También permanece el Aleluya del texto fijo; la nota explica que en la celebración litúrgica se omite durante Cuaresma. Este recorrido es una selección devocional, no un oficio calculado para hoy.

Fuente de alcance litúrgico: [Ordenación General de la Liturgia de las Horas, 84–92](https://liturgyoffice.org.uk/Resources/Rites/GILH.pdf), especialmente 88 (opción legítima de repetir los salmos dominicales) y 41 (omisión del Aleluya en Cuaresma). La repetición no se presenta como un defecto por sí sola. El PDF procede de la auditoría previa; no se afirma una nueva revisión íntegra del documento ni aprobación de los textos de la app.

## Biblia

`BibleData.kt` identifica la tarjeta `douay_rheims` como **Douay-Rheims-Challoner**, idioma **English**. Se retiran «Nova Vulgata», «English / Latin» y la atribución de autoridad de Trento al texto digital distribuido. La descripción ES/EN/PT reconoce que la procedencia y las transformaciones del corpus requieren verificación documental. Se conserva el canon católico de 73 libros, con las adiciones de Ester y Daniel; no se modifica ningún archivo del corpus ni sus referencias.

No se inventan editor digital, permiso, imprimatur, cotejo íntegro o vínculo de procedencia. La identificación de edición procede del inventario existente de la auditoría; queda pendiente documentar la cadena de origen de los assets y cotejarla con la edición concreta.

## Santuarios e indulgencias

Se revisaron las quince variantes ES/EN/PT de las cinco fichas activas (`rome_vatican`, `holy_sepulchre`, `fatima_sanctuary`, `lourdes_sanctuary`, `santiago_compostela`). Todas usan ahora la misma ayuda localizada, que:

- niega que la visita por sí sola garantice una indulgencia plenaria;
- declara que la ficha no documenta una concesión concreta;
- remite a comprobar fuente oficial, vigencia, fechas y obra prescrita con el santuario;
- explica la intención de obtenerla, estado de gracia, confesión sacramental, Comunión, oración por las intenciones del Papa y desapego de todo pecado, incluso venial.

Las fuentes generales se nombran en las tres traducciones y se enlazan aquí. Se recuperaron ambas páginas oficiales con HTTP 200 el 13 de noviembre de 2026 y se leyeron sus pasajes pertinentes:

1. [Penitenciaría Apostólica, *The Gift of the Indulgence*, 29 de enero de 2000, 2–5](https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_pro_20000129_indulgence_en.html): distingue condiciones y obras; exige estado de gracia y las condiciones comunes. El n. 5 explica el margen temporal y que cada indulgencia requiere su propia Comunión y oración por el Papa. Las obras jubilares particulares de 2000 no se transfieren a cualquier fecha actual.
2. [Pablo VI, *Indulgentiarum doctrina*, 1 de enero de 1967, norma 7](https://www.vatican.va/content/paul-vi/en/apost_constitutions/documents/hf_p-vi_apc_01011967_indulgentiarum-doctrina.html): requiere obra prescrita, confesión, Comunión, oración por el Papa y ausencia de apego al pecado, incluso venial.

No se afirma que no existan concesiones para esos lugares: se declara que estas fichas no las documentan. Queda pendiente obtener y citar para cada santuario una concesión vigente con su obra, fechas y condiciones particulares. La ayuda es un resumen de condiciones comunes, no un manual exhaustivo (p. ej. límites diarios, plazos o conmutaciones por impedimento); esos detalles figuran en las fuentes. Se conservan íntegros IDs, nombres, historia, significado, reliquias, consejos y coordenadas. Las afirmaciones sustituidas se registran más abajo para trazabilidad, sin presentarlas como concesiones verificadas.

## Verificación de esta entrega

Revisión estática de los cinco archivos de producción asignados, de los tres idiomas y del diff; se comprobó que las cinco fichas usan la ayuda común y que no se modificaron los textos de oración, el corpus bíblico, los IDs o el progreso. No se ejecutaron builds ni tests en esta subtarea; la validación integrada corresponde al agente principal. No se afirma comprobación visual o en dispositivo.

## Registro de afirmaciones sustituidas (sin verificar; no usar como guía)

### rome_vatican

- ES: Indulgencia plenaria concedida a los peregrinos que visitan la basílica, rezan el Credo y el Padre Nuestro por el Papa y confiesan.

- EN: Plenary indulgence available under standard conditions (Confession, Communion, prayer for the Pope).

- PT: Indulgência plenária aos peregrinos que rezam com devoção diante do túmulo do Apóstolo.

### holy_sepulchre

- ES: Indulgencia plenaria visitando devotamente el Santo Sepulcro en peregrinación santa.

- EN: Plenary indulgence for holy pilgrims venerating the site of Christ's Passion and Resurrection.

- PT: Indulgência plenária aos peregrinos na Terra Santa.

### fatima_sanctuary

- ES: Indulgencia plenaria en la Capilla de las Apariciones en las fiestas marianas solemnes.

- EN: Plenary indulgence available for devout pilgrims at the Chapel of Apparitions.

- PT: Indulgência plenária na Capelinha das Aparições.

### lourdes_sanctuary

- ES: Indulgencia plenaria en el santuario cumpliendo las condiciones de la Iglesia.

- EN: Plenary indulgence granted to pilgrims who pray before the Grotto and receive sacraments.

- PT: Indulgência plenária aos peregrinos que rezam na Gruta.

### santiago_compostela

- ES: Indulgencia plenaria plenísima en los Años Santos Compostelanos (cuando el 25 de julio cae en domingo).

- EN: Jubilee plenary indulgence granted to pilgrims arriving via the Camino.

- PT: Indulgência plenária concedida aos peregrinos que alcançam a Catedral.
