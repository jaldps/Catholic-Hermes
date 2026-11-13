# Primer incremento de Estudio y correcciones editoriales

Base: `d172a7f`. Trabajo del 13 de noviembre de 2026. Este registro distingue implementación, pruebas y trabajo futuro. La entrega se empaqueta como preview 1.6; el instalador y sus metadatos están en DELIVERY-1.6.md y APK-1.6.json.

## Alcance implementado

- Correcciones M1 en las tres notas de ejecución: [editorial](M1-EDITORIAL-FIXES.md), [liturgia](M1-LITURGY-FIXES.md), [catequesis](M1-CATECHESIS-FIXES.md).
- Un curso completo nuevo, **Aprender a leer la Biblia**, con cinco lecciones en ES/EN/PT. Se conservan los tres cursos y quince lecciones anteriores: total actual **cuatro cursos y veinte lecciones**.
- Cada nueva lección desarrolla contexto, tres explicaciones, tres preguntas con respuesta razonada, fuentes del Catecismo, lectura bíblica, meditación, oración, silencio contemplativo y acción distinta. Los pasajes se resuelven desde las ediciones incluidas; no se redacta una nueva traducción bíblica.
- Objetivos y tiempo orientativo de curso; nivel de fundamentos; navegación por partes de la lección; notas guardables y continuación sin marcar automáticamente una lección como completada.
- Fuentes del nuevo curso abren el párrafo del Catecismo dentro de la app. Las referencias antiguas conservan su presentación; enlazarlas de forma estructurada sigue pendiente.
- El área desplazable termina antes de la barra inferior para que los controles al final de la lección sean alcanzables. El borrador de notas es restaurable y no depende de que la consulta de Room haya terminado antes de componer la vista.
- La contemplación y la acción se presentan separadas también en los cursos anteriores: se conserva su acción original y se incorpora una invitación breve al silencio.

## Fuentes y límites

La procedencia del nuevo lote se registra en [study-course-provenance.json](../content/study-course-provenance.json), enlazada al manifiesto del Catecismo. El coordinador contrastó los párrafos citados y revisó los tres textos. Las explicaciones son editoriales originales; las fuentes no otorgan autoría vaticana ni aprobación eclesiástica a la app.

Se comprobó en los tres corpus que el salmo del Buen Pastor usa **Salmo 22** en la numeración incluida. El curso explica su equivalencia habitual con Salmo 23; no envía al capítulo 23 de estas ediciones, que contiene otro salmo. Inglés usa «The Lord ruleth me» y portugués «O Senhor me apascenta»; no se exige artificialmente que todas las traducciones incluyan la palabra «pastor/shepherd».

La ampliación de Jesús y el misterio pascual está diseñada y **no implementada**. Tampoco se cierran H3 completo, la revisión de las 246 novenas, los derechos de textos litúrgicos, el misal, el breviario, el santoral anual ni las demás ampliaciones del [plan escalonado](CONTENT-DELIVERY-PLAN.md).

## Revisión y ejecución

Los agentes de esfuerzo medio completaron M1. El planificador y el diseñador curricular prepararon sus documentos y un modelo aditivo. El agente revisor xhigh informó inicialmente que no había encontrado un bloqueo confirmado, pero su revisión final y los agentes de los cursos nuevos se interrumpieron por el límite de uso. **No se atribuye aprobación independiente final.** El coordinador continuó el curso, la integración y la comprobación directamente.

No se modifica Room ni se cambian IDs anteriores. Se conserva corpus, progreso, notas y textos de las oraciones M1. Las correcciones no equivalen a haber revisado cada afirmación del catálogo heredado.

## Validación final del incremento

Primer ensayo enfocado: cuatro pruebas, tres fallos. Uno detectó que la prueba buscaba palabras distintas de las ediciones reales del salmo; otro requería desplazar la respuesta desplegada; el recorrido de continuación reveló controles susceptibles de quedar bajo el dock. Se ajustaron la prueba de edición y el viewport, conservando comprobación real de interacción y persistencia. No se trata ese ensayo como aprobación.

Se ejecutaron 277 casos en la primera regresión: 275 aprobados y dos expectativas antiguas fallidas (catálogo de tres cursos y nombre de producción). Se corrigieron esas expectativas. La revalidación de 18 casos en cinco clases pasó sin fallos, errores u omisiones; después se repitieron con éxito los dos recorridos de Estudio tras añadir el tag del índice.

Las capturas finales pasaron cuatro métodos normales (20 imágenes) y cuatro compactos (12 imágenes) en Lumen/Sanctuary, claro/oscuro y español. El caso compacto usa 320×760 dp al 200 % y comprueba que continuar queda por encima de la barra. El generador se corrigió para desplazar el índice hasta un control todavía no compuesto y para usar el orden/rótulos actuales de navegación. La revisión de los 32 estados finales corresponde al coordinador, sin atribuir aprobación al agente interrumpido.

[Resultados exactos](VALIDATION-1.6.json), [entrega y límites](DELIVERY-1.6.md) y muestras: [Lumen claro](study-preview/lumen-light.png), [Lumen oscuro](study-preview/lumen-dark.png), [Sanctuary claro](study-preview/sanctuary-light.png), [Sanctuary oscuro](study-preview/sanctuary-dark.png), [Lumen compacto](study-preview/lumen-compact.png) y [Sanctuary compacto](study-preview/sanctuary-compact.png). No se reutilizan los 273 éxitos de la entrega anterior.
