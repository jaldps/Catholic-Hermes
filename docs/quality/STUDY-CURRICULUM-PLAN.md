# Estudio bíblico: currículo y contrato de integración


**Estado del primer incremento:** el curso A está implementado (cinco lecciones); el curso B y la ampliación total a veinticinco lecciones siguen planificados. El catálogo entregado tiene cuatro cursos y veinte lecciones. Véase STUDY-DELIVERY.md para pruebas y límites.
13 de noviembre de 2026. Alcance de la siguiente entrega: **dos cursos nuevos completos, cinco lecciones cada uno**, con desarrollo ES/EN/PT y una lectura más clara. El resultado previsto es cinco cursos y veinticinco lecciones. La profundidad se revisa por lo que explica y permite comprender cada lección, no por el tamaño del catálogo.

Este documento es un plan y contrato editorial/técnico. Se inspeccionó el código; no acredita interacción en dispositivo, aprobación eclesiástica ni revisión lingüística profesional. Los campos aditivos de `ScriptureCourseModels.kt` sí se han preparado durante esta tarea; la existencia de este plan no significa que los nuevos textos o pantallas estén terminados. La integración y validación quedan a cargo de sus responsables. No se ejecutaron builds, pruebas ni commits para redactarlo.

## 1. Punto de partida que se conserva

- `ScriptureStudyData.kt` ya contiene **sacramentos (6), parábolas (6) y vida apostólica (3)**. Hay pasaje, comentario, lectura/reflexión/oración/resolución y referencias. No se presenta esta entrega como creación desde cero.
- `ScriptureStudyScreen.kt` ya ofrece selección de curso, porcentaje, siguiente lección incompleta, lectura por voz, notas y marcado manual. Se mejora esta ruta existente dentro de Biblia → Estudio; no se crea otro sistema de navegación.
- `CatholicDataRepository.scriptureStudyTracks` expone actualmente `ScriptureStudyData.tracks`. La ampliación debe componer esa lista con dos objetos de datos nuevos.
- Room guarda `lessonId`, `trackId`, `isCompleted`, `completedTimestamp` y `personalNotes`. Los quince IDs existentes, los textos conservados, notas y fechas continúan siendo válidos. Los metadatos nuevos no requieren migración.
- Actualmente `biblicalReference` es una etiqueta no localizada y `catechismRefs` mezcla rangos del Catecismo con documentos. Ambos campos siguen disponibles para compatibilidad. Los nuevos cursos usan referencias canónicas y fuentes estructuradas.
- En las lecciones existentes, `contemplatioStep` contiene principalmente acciones. El rótulo actual equipara contemplación con transformación práctica. Es necesario distinguir el silencio ante Dios de la resolución posterior.
- `DoctrineQuestions.kt` ya tiene lectores de fuentes y preguntas de itinerarios; su nivel de fundamento es una heurística, no certificación. Reutilizar el acceso a fuentes cuando sea compatible; no trasladar esa puntuación a estos cursos ni tomar la presencia de referencias como prueba de profundidad.

La instrucción vigente de `AGENTS.md` es Lumen y Sanctuary, ambas con claro/oscuro/sistema. Los informes antiguos de tres identidades son antecedentes, no el contrato actual.

## 2. Resultado editorial y experiencia del lector

Una persona que llega sin formación debe entender qué va a aprender, por qué importa el pasaje, qué enseña la Iglesia y cómo leerlo y rezarlo. Cada curso tiene destinatario, tres objetivos, cinco encuentros ordenados y cierre. El orden se recomienda sin bloquear lecciones ni convertir el avance en examen de fe.

**Aprender a leer la Biblia en la Iglesia** es la entrada recomendada: fundamento, cinco encuentros de unos veinte minutos. Enseña Escritura/Tradición, canon e historia de salvación, género y contexto, unidad y sentidos, y lectura orante. No promete una introducción exhaustiva a los 73 libros.

**Jesús y el misterio pascual** ofrece cinco encuentros de unos veinte minutos desde la Encarnación hasta Pascua, Espíritu y misión. Puede hacerse directamente; se recomienda el primer curso para quien necesite aprender a manejar los textos. No se anuncia como biografía exhaustiva ni preparación sacramental certificada.

Los sacramentos, las parábolas y la vida apostólica permanecen como cursos temáticos. La secuencia sugerida es Biblia → Jesús → cursos temáticos, conservando el acceso libre y los avances anteriores. El cierre de cada curso muestra lo recorrido, tres aprendizajes que revisar y un siguiente curso pertinente; no diplomas ni rangos espirituales.

## 3. Anatomía obligatoria de cada nueva lección

| Parte | Entrega concreta | Revisión de profundidad |
|---|---|---|
| Orientación | Título, 2–3 objetivos observables, duración sugerida | Los objetivos describen comprensión o práctica, no «conocer más» sin objeto. |
| Texto bíblico | Una o más referencias canónicas; texto resuelto desde la edición incluida del idioma actual | Coincide íntegramente con sus rangos; no se redactan citas bíblicas de memoria ni se mezclan versiones. |
| Contexto | Un párrafo de unas 70–110 palabras por idioma | Sitúa género, escena, interlocutores y lugar del pasaje en el libro; distingue datos del texto de reconstrucciones discutidas. |
| Enseñanza | **Tres secciones tituladas, unas 250–350 palabras en total por idioma** | Desarrollan una idea cada una, explican sus conexiones y una dificultad real. Ese rango orienta la edición; no justifica relleno. |
| Lectio | Una observación y una consigna para releer detalles concretos | Pregunta por lo que el pasaje realmente dice antes de aplicarlo. |
| Meditatio | Dos preguntas personales concretas, relacionadas con la enseñanza | No presupone que el lector esté bautizado ni exige compartir asuntos íntimos. |
| Oratio | Oración editorial breve, fiel a la lección | Se identifica como propuesta editorial; las comillas no la convierten en oración tradicional. |
| Contemplatio | Invitación a unos 60–90 segundos de silencio con una palabra/imagen del texto | No exige producir una emoción ni completar una técnica; admite distracción y regreso sereno. |
| Acción | Una práctica pequeña, realizable y voluntaria | Es distinta del silencio y puede realizarse en circunstancias ordinarias. |
| Comprensión | **Tres preguntas con respuesta explicada**, vinculadas a los objetivos | Dos comprueban comprensión del texto/doctrina; una pide discernir una aplicación. No trivia de nombres, fechas o números. |
| Fuentes | Rangos precisos del Catecismo y pasajes, con acceso real | Una referencia sostiene una afirmación identificable; no listas de documentos para dar apariencia de autoridad. |

Los objetivos, contexto, enseñanza, preguntas, oración, silencio y acción se redactan completos en ES, EN y PT. La longitud no debe cumplirse mediante traducciones copiadas, párrafos intercambiables o repeticiones de los quince encuentros anteriores. El contenido de enseñanza propuesto suma unas 2.500–3.500 palabras por idioma, además del contexto y la guía orante; es una ampliación sustancial y acotada.

## 4. Curso A — Aprender a leer la Biblia en la Iglesia

**Objeto:** `CatholicBibleCourseData.track`. **Archivo:** `data/repository/CatholicBibleCourseData.kt`. **ID:** `track_catholic_bible`. **Nivel:** `FOUNDATION`. **Tiempo:** 100 minutos estimados, 20 por encuentro.

Objetivos de curso: (1) explicar cómo se relacionan Escritura y Tradición y qué significa inspiración; (2) leer un pasaje atendiendo a género, contexto y unidad de la Biblia; (3) realizar una lectura orante y comprobar su interpretación con fuentes de la Iglesia.

Los anclajes siguientes son una selección editorial para el autor y deben contrastarse antes del cierre; este plan no afirma haber cotejado cada párrafo externo.

| Lección e ID | Pasaje canónico principal | Tres desarrollos de enseñanza | Fuentes precisas propuestas |
|---|---|---|---|
| 1. Dios habla: Escritura y Tradición — `bible_1_word_tradition` | `2ti 3:14-17` | Revelación como comunicación de Dios culminada en Cristo; inspiración y autores humanos sin dictado mecánico; una misma transmisión de la fe en Escritura y Tradición, servida por el Magisterio. Explicar por qué «inspirada» no significa una colección de respuestas aisladas. | CEC 80–83, 101–104, 105–108; *Dei Verbum* 9–11 si se añade documento. |
| 2. Una biblioteca y una historia de salvación — `bible_2_canon_history` | `luk 24:25-27` | Canon católico de 46 libros del AT y 27 del NT; hilo creación/alianza/éxodo/profetas/Cristo/Iglesia; relación de promesa y cumplimiento sin despreciar a Israel ni desechar el AT. Distinguir secuencia narrativa y fecha de composición. | CEC 120–123, 128–130. |
| 3. Género, contexto y autor — `bible_3_genre_context` | `psa 23:1-6` | El salmo como poesía y oración; sentido de pastor, camino, mesa y valle dentro del texto; método para preguntar quién habla, a quién y con qué imágenes. Explicar por qué confiar no garantiza ausencia de sufrimiento. | CEC 109–114; 2585–2589 para los salmos. |
| 4. Leer el conjunto: sentido literal y espiritual — `bible_4_senses_unity` | `exo 12:1-14`; `joh 1:29-34` | La Pascua de Israel en su propio contexto; lectura cristológica del Cordero y unidad de las alianzas; sentidos alegórico, moral y anagógico apoyados en el literal. Distinguir tipología de convertir cualquier detalle en código secreto. | CEC 115–119, 128–130; 608. |
| 5. Escuchar, comprender y responder — `bible_5_prayer_church` | `luk 24:13-35` | Movimiento del relato de Emaús: desconcierto, Escritura, reconocimiento y vuelta; lectura orante en la Iglesia y vínculo con su vida litúrgica; práctica de lectio/meditatio/oratio/contemplatio/acción sin saltar la comprensión. | CEC 131–133, 2653–2654; 1346–1347 para el vínculo litúrgico. |

**Preguntas que el curso debe resolver:** ¿Tradición significa cualquier costumbre? ¿Por qué una biblioteca de géneros distintos puede tener unidad? ¿Puede un salmo de confianza prometerme que nada malo ocurrirá? ¿Cómo sé que una lectura espiritual respeta el texto? ¿Cuál es la diferencia entre entender, pedir y permanecer en silencio?

**Cierre aplicado:** elegir un pasaje breve, identificar género/contexto y describir su sentido antes de formular una oración y una práctica. En la respuesta explicada se muestran criterios, sin evaluar la intimidad de la oración. El siguiente curso recomendado es Jesús y el misterio pascual.

**Cuidado con los salmos:** `psa` es el ID existente. La numeración y los títulos deben comprobarse en ES/EN/PT. Si el salmo del pastor difiere de número en una edición, ajustar la referencia por edición o elegir otro texto compartido; nunca mostrar un capítulo diferente bajo una etiqueta común ni asumir que un rango sintácticamente válido significa el mismo pasaje.

## 5. Curso B — Jesús y el misterio pascual

**Objeto:** `JesusPaschalCourseData.track`. **Archivo:** `data/repository/JesusPaschalCourseData.kt`. **ID:** `track_jesus_paschal`. **Nivel:** `FOUNDATION`. **Tiempo:** 100 minutos estimados, 20 por encuentro. Curso A recomendado, sin requisito técnico ni bloqueo.

Objetivos de curso: (1) explicar la identidad de Jesús y la unidad de su vida y misión; (2) relacionar Reino, Última Cena, cruz y resurrección; (3) reconocer la vida cristiana como respuesta a su don y misión en el Espíritu, sin reducirla a un esfuerzo individual.

| Lección e ID | Pasaje canónico principal | Tres desarrollos de enseñanza | Fuentes precisas propuestas |
|---|---|---|---|
| 1. El Verbo se hace hombre — `jesus_1_incarnation` | `luk 1:26-38` | Promesa mesiánica y escena de la Anunciación; verdadero Dios y verdadero hombre, una persona, sin disfraz humano; consentimiento de María y primacía del don de Dios. Aclarar por qué Encarnación significa salvación y cercanía real. | CEC 456–460, 464–469, 484–489, 494. |
| 2. El Reino llega: conversión y seguimiento — `jesus_2_kingdom` | `mar 1:14-20` | Buena noticia y cercanía del Reino; conversión como respuesta a la gracia; llamada concreta y comunidad de discípulos. Explicar que el Reino no equivale a prosperidad material o dominio político. | CEC 541–546; 1427–1429 para conversión. |
| 3. La Cena: don, alianza y servicio — `jesus_3_supper_service` | `luk 22:14-20`; `joh 13:1-15` | Contexto pascual y entrega libre de Cristo; palabras sobre pan y cáliz y su relación con la cruz; lavatorio como forma del amor y no sustitución de la realidad sacramental. Distinguir memorial de mero recuerdo sin repetir la antigua lección de Eucaristía. | CEC 610–611, 1337–1340; 1362–1364 para memorial. |
| 4. La cruz: amor que se entrega — `jesus_4_cross` | `luk 23:33-49` | Escena, personajes y oración de Jesús; libertad, amor y reconciliación, evitando presentar al Padre como enemigo del Hijo; seguimiento ante el sufrimiento y búsqueda de ayuda. Explicar expresamente que no hay culpa colectiva de los judíos ni deber de permanecer en abuso. | CEC 595–598, 604–618; separar lo que cada rango sostiene. |
| 5. Resucitado, Espíritu y misión — `jesus_5_risen_mission` | `luk 24:36-53`; `act 2:1-13` | Resurrección real y corporal que supera el simple regreso a la vida anterior; comprensión de las Escrituras y envío de testigos; ascensión y don del Espíritu para la misión de la Iglesia. Explicar esperanza futura y vida presente sin tratar Pascua como una metáfora de ánimo. | CEC 639–655, 659–667, 731–732. |

**Preguntas que el curso debe resolver:** ¿Por qué importa que Jesús sea plenamente humano? ¿Qué cambia entre admirarlo y seguirlo? ¿Cómo se relacionan Cena, servicio y cruz? ¿Cómo hablar de redención sin atribuir culpa colectiva o santificar el abuso? ¿Qué distingue resurrección, reanimación y recuerdo simbólico?

**Cierre aplicado:** explicar con palabras propias la unidad entre entrega, Pascua y misión; escoger una acción concreta de servicio y una oración de esperanza. Proponer Sacramentos para profundizar en la vida de la Iglesia, o Parábolas para volver a la enseñanza del Reino. No se obliga a estar bautizado o comulgar para completar el encuentro.

## 6. Contrato de datos aditivo

Los siguientes campos están añadidos al final de los constructores con valores por defecto. Las llamadas existentes siguen compilando por forma; la compilación efectiva se verifica al integrar.

| Modelo | Campos añadidos | Semántica |
|---|---|---|
| `ScriptureCourseTrack` | `level: StudyCourseLevel = UNSPECIFIED`; `objectives: List<LocalizedText> = emptyList()`; `estimatedMinutes: Int? = null` | `UNSPECIFIED` y `null` significan dato no preparado; no mostrar nivel o «0 minutos» en cursos antiguos. |
| `ScriptureLesson` | `scriptureReferences: List<String> = emptyList()` | Entradas para `ScriptureReference.parse`, una por libro: `luk 24:13-35`. No usar nombres traducidos o concatenar dos libros en una sola entrada. |
| `ScriptureLesson` | `historicalContext: LocalizedText? = null`; `teachingSections: List<StudyTeachingSection> = emptyList()` | Contexto distinto de explicación doctrinal. Tres secciones obligatorias en cada nueva lección. |
| `ScriptureLesson` | `objectives: List<LocalizedText> = emptyList()`; `reviewQuestions: List<StudyReviewQuestion> = emptyList()` | Objetivos de lección y tres preguntas con explicación. No requieren tabla de resultados ni calificación. |
| `ScriptureLesson` | `actionStep: LocalizedText? = null`; `estimatedMinutes: Int? = null`; `sourceReferences: List<StudySourceReference> = emptyList()` | Acción separada de contemplación; duración sugerida; fuentes precisas y navegables. |

Modelos auxiliares preparados:

```kotlin
enum class StudyCourseLevel { UNSPECIFIED, FOUNDATION, INTERMEDIATE }
data class StudyTeachingSection(val title: LocalizedText, val body: LocalizedText)
data class StudyReviewQuestion(val prompt: LocalizedText, val answer: LocalizedText)
data class StudySourceReference(
    val id: String,
    val title: LocalizedText,
    val catechismStart: Int? = null,
    val catechismEnd: Int? = null,
    val url: LocalizedText? = null
)
```

Para Catecismo, ambos extremos son inclusivos y `1 <= start <= end <= 2865`; un párrafo se representa con el mismo número en ambos. Para otro documento, el título contiene sección/número preciso y `url` aporta el destino oficial en cada lengua. Un ID de fuente es estable y descriptivo, por ejemplo `ccc_109_114`; es independiente del idioma. Estos son invariantes de autoría/validación; el modelo no valida URLs ni concede derechos.

Las nuevas lecciones conservan los campos requeridos antiguos: `biblicalReference` como etiqueta heredada, `biblicalPassageText` como campo de compatibilidad y `vaticanContext` como breve síntesis editorial. **Su texto visible y el audio de Escritura deben proceder del resolutor**, no del campo de compatibilidad. Nunca recurrir a un texto bíblico redactado o parafraseado para disimular un fallo de resolución. Los párrafos de enseñanza van en `teachingSections`, sin duplicarlos completos en `vaticanContext`.

`historicalContext` no debe afirmar fechas exactas de composición o autores discutidos como si fueran consenso. Los términos difíciles se explican en el propio texto; no requieren un glosario nuevo para entender la lección.

## 7. Contrato de lectura, fuentes y procedencia

1. Resolver cada `scriptureReferences` mediante `ScriptureReference` y el `ScriptureResolver` existente sobre `repository.catholicBooks`/versículos del idioma activo. Mantener las lecturas de distintos libros en bloques separados con su etiqueta. El modelo existente admite rangos múltiples y capítulos cruzados.
2. Mostrar el nombre real y la situación editorial de la edición incluida en ese idioma. No inferir una edición por similitud con una cita ni anunciar Nova Vulgata donde hay inglés. La corrección bibliográfica de M1 prevalece sobre metadatos antiguos.
3. Un fallo de resolución muestra indisponibilidad del pasaje y conserva la referencia y el comentario. No cortar silenciosamente rangos, traducir de otro idioma ni usar el extracto antiguo como cita exacta. El audio lee exactamente el texto visible resuelto.
4. Las fuentes CCC abren los párrafos completos en el lector existente; los pasajes abren Biblia en libro/capítulo/versículo. Volver conserva curso, lección, notas y progreso. Si para un destino externo se ofrece un enlace, su rótulo identifica que sale de la app.
5. `DoctrineQuestions.kt` sirve como precedente de lector/fuentes y `parseFoundation` como precedente de reconocimiento. Para contenido nuevo, usar los targets estructurados en lugar de volver a inferirlos a partir del texto traducido.
6. El Catecismo íntegro ya cuenta con `docs/content/catechism-provenance.json`: fuente por lengua, hashes y párrafos. Esta procedencia del corpus no certifica las afirmaciones nuevas ni la traducción editorial. No modificar ese registro para atribuirle revisión de los cursos.
7. La integración debe añadir `docs/content/study-provenance.json` con curso/lección, tipo editorial, referencias bíblicas, fuente/edición por idioma, rangos CCC/documentos, fecha, cambios y estado real de revisión. Si hay una cita literal, registrar URL/locator y condiciones de reproducción; preferir explicación original con enlace para evitar importar textos extensos sin resolver derechos.
8. Cada afirmación doctrinal central se coteja con su anclaje; las citas literales se diferencian de las paráfrasis. Un enlace a vatican.va no significa que el curso sea «del Vaticano», tenga imprimatur o haya sido aprobado por la Iglesia.

La procedencia/licencia de los textos bíblicos heredados debe identificarse honestamente. Resolverlos desde el bundle evita nuevas citas inventadas y divergencias, pero no resuelve por sí mismo los derechos pendientes del corpus. No incorporar nuevas traducciones completas a este lote.

## 8. Contrato de interfaz y progreso

**Resumen del curso:** título, descripción con destinatario, nivel si está definido, tiempo estimado, tres objetivos y lista ordenada de encuentros. Mantener número de completadas, porcentaje y CTA Empezar/Continuar/Repasar. Mostrar un resumen corto de objetivos antes de empezar; no añadir un mural de tarjetas que esconda las lecciones.

**Lección:** título/número/duración → objetivos → pasaje y escuchar → contexto → tres desarrollos → lectio/meditatio/oratio/contemplatio → acción → tres preguntas con respuestas desplegables → fuentes → notas y completar/continuar. Mantener superficies de lectura opacas, línea cómoda y jerarquía de títulos. Las respuestas pueden mostrarse a petición sin imponer un cuestionario de opción múltiple.

**Contemplación y acción heredadas:** si se migra una lección antigua, copiar primero su resolución existente a `actionStep` y redactar una invitación al silencio vinculada al pasaje en `contemplatioStep`. Si esa revisión no está preparada, presentar el contenido heredado como Acción; no cambiar el rótulo y afirmar que se ha añadido contemplación. Las quince resoluciones existentes se conservan aunque cambie su campo.

**Siguiente paso visible:** al completar, el lector ve «Completada · siguiente: [título]» y un botón para abrir esa lección, con alternativa Volver al curso. En la última, mostrar el cierre y Repasar/Volver. La pregunta de comprensión no debe impedir marcar avance. Guardar notas de una lección ya completada mantiene la fecha original y el estado.

**Reanudación:** el CTA usa la primera incompleta del curso, como ahora. Para conservar una lección en curso al volver de las fuentes, mantener el ID activo y restaurar la posición de lectura. No prometer reanudación exacta tras cerrar la aplicación si solo existe `rememberSaveable`; verificar el alcance real y describirlo con precisión. No es necesario cambiar Room para este lote.

**Selección estable:** usar ID de curso al recuperar selección si se cambia el orden del catálogo. Como alternativa mínima, añadir los dos cursos después de los tres existentes para conservar el significado del índice guardado. Todos los `trackId` de las lecciones deben coincidir con su curso; IDs nuevos únicos y números 1–5 consecutivos.

**Dos identidades y tres idiomas:** usar las envolturas de `AppComponents.kt` y el contrato de `AppKit.kt`; no introducir una estética paralela para «cursos». Lumen y Sanctuary deben mostrar el mismo contenido, metas, fuentes y estados. Localizar también botones, duraciones, nivel, preguntas y avisos, no solo títulos. Mantener tags estables: `scripture_study_screen`, `study_track_picker`, `track_row_*`, `lesson_row_*`, `study_course_progress`, `study_continue_button`, `lesson_listen_button`, `toggle_lesson_complete_button`, `lesson_notes_field`, `save_lesson_button`; añadir tags semánticos para fuentes, respuestas y siguiente lección.

## 9. División de trabajo sin colisiones

| Lote | Propiedad de archivos | Entrega y dependencia |
|---|---|---|
| Contrato | `ScriptureCourseModels.kt`; este documento | Campos preparados, valores por defecto y semántica compartida. No cambia Room. |
| Autor A | `CatholicBibleCourseData.kt` nuevo | Curso completo de cinco encuentros ES/EN/PT según §§3–4; fuentes y preguntas incluidas. Puede trabajar una vez fijado el contrato. |
| Autor B | `JesusPaschalCourseData.kt` nuevo | Curso completo de cinco encuentros ES/EN/PT según §§3 y 5; misma estructura, contenido propio. Puede trabajar en paralelo con A sin editar modelos/repositorio/UI. |
| Revisión editorial | Nuevos cursos y `study-provenance.json` | Lectura completa por idioma y contraste de fuentes; señala y corrige errores concretos antes de declarar completo. Mantiene estado de revisión honesto. |
| Integración | `CatholicDataRepository.kt`, `ScriptureStudyScreen.kt`, `BibleScreen.kt` solo donde sea necesario | Componer cinco cursos; resolver pasajes; navegación a fuentes; objetivos/enseñanza/preguntas; siguiente lección y cierre. Empieza tras liberar la propiedad editorial M1 de datos/pantalla. |
| Verificación | Pruebas/capturas existentes y comprobaciones enfocadas justificadas | Valida contenido real resuelto, fuentes, avances/notas y matriz visual; registra evidencia de esta entrega. |

El autor de un curso no toca `ScriptureStudyData.kt` ni `ScriptureStudyScreen.kt` mientras el propietario de M1 sigue trabajando allí. Una ampliación de los quince encuentros antiguos exige encargo propio y revisión; no se convierte en una reescritura silenciosa dentro de este lote. El padre/coordinador integra los nuevos objetos en el repositorio tras la revisión.

## 10. Criterios de aceptación de la siguiente entrega

- Cinco cursos accesibles y veinticinco IDs de lección únicos; los quince anteriores no desaparecen ni se renumeran. Ambos nuevos contienen cinco lecciones completas en ES/EN/PT, sin filas «próximamente».
- Cada nueva lección tiene contexto específico, tres desarrollos sustanciales, objetivos, guía orante con silencio real, acción y tres preguntas con respuesta razonada. Un revisor puede señalar dónde se responde cada objetivo y cada dificultad del temario.
- Cada rango bíblico se resuelve completamente en las tres lenguas y corresponde al contenido descrito; se comprueban especialmente salmos/numeración y pasajes múltiples. El lector ve y escucha la misma edición identificada.
- Fuentes doctrinales precisas y operativas: abrir el párrafo/pasaje completo y regresar no pierde notas ni estado. Documentos externos indican el destino. No hay citas inventadas ni autoridad eclesiástica atribuida al texto editorial.
- Completar, desmarcar y guardar notas siguen funcionando con datos ya existentes; guardar notas no cambia la fecha de una lección completada. El resumen refleja avances y el siguiente paso; el cierre no promete un título espiritual o sacramental.
- Revisión de las cuatro combinaciones Lumen/Sanctuary × claro/oscuro en ES/EN/PT. Revisar también sistema, fuente persistente, ancho estrecho y texto ampliado en las pantallas modificadas. Etiquetas accesibles, foco de respuestas desplegadas y fuentes legibles sin recortes.
- Ejecutar la compilación y las pruebas exigidas por `AGENTS.md` con `flock /tmp/gradle.lock`. Añadir solo comprobaciones que cubran riesgos concretos: integridad/resolución de referencias, navegación a fuente y conservación de progreso/notas; no tests que repitan cada párrafo de la implementación.
- Registrar comando, revisión exacta, resultado y capturas de esta entrega. Los resultados históricos en `docs/review/catholicgpt/VALIDATION.md` no validan estos cambios. Señalar expresamente qué revisión profesional o uso en dispositivo sigue pendiente.

Quedan fuera de este lote la reescritura íntegra de los quince encuentros anteriores, un curso completo de historia eclesial, introducciones extensas a los 73 libros, vídeo, evaluaciones con notas, un nuevo diario, gamificación y centenares de temas breves. Se amplía después de demostrar profundidad, referencias útiles y una lectura cuidada en estos diez encuentros.
