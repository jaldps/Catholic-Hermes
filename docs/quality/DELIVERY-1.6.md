# Catholic Hermes Preview 1.6

Versión `1.6-quality-preview`, código 7; paquete `com.aistudio.sanctafides.qvmrpx.qualitypreview`. El certificado coincide con el quality preview anterior. No se cambian tablas Room, identificadores anteriores, firma de producción ni instaladores históricos. La instalación en un teléfono físico no se ha comprobado.

[Descargar APK](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-quality-preview.apk) · [metadatos y SHA-256](APK-1.6.json) · [plan escalonado](CONTENT-DELIVERY-PLAN.md)

## Qué cambia

La primera etapa corrige atribuciones de autoridad, la presentación de la selección nocturna, indulgencias no documentadas, Lourdes, una cronología imposible, la tarjeta inglesa de la Biblia y explicaciones de disciplina latina/oriental. Los tres informes M1 y [la revisión del coordinador](M1-COORDINATOR-REVIEW.md) precisan alcance y fuentes.

Estudio incorpora el curso completo **Aprender a leer la Biblia**, en ES/EN/PT, con cinco lecciones: Escritura/Tradición/Iglesia, canon e historia de salvación, género/contexto, sentidos de la Escritura y lectura orante. Se mantienen los tres cursos y quince lecciones originales: cuatro cursos y veinte lecciones en total.

El curso nuevo ofrece objetivos, contexto, quince bloques explicativos y quince preguntas con respuesta razonada; fuentes del Catecismo accesibles dentro de la app; pasajes exactos de la edición incluida; contemplación y acción diferenciadas; notas y continuación. Las áreas desplazables dejan los controles fuera de la barra inferior. La vuelta desde el Catecismo conserva la lección y el borrador.

Accesos: **Leer → Estudio**, o **Explorar → Estudio → Cursos bíblicos**. [Registro completo de implementación](STUDY-DELIVERY.md) y [procedencia del lote](../content/study-course-provenance.json).

## Evidencia de validación

- Primer lote completo: 277 pruebas, 275 aprobadas y dos expectativas antiguas fallidas (tres cursos y nombre de aplicación de producción). Se actualizaron esas expectativas para el catálogo ampliado y el paquete preview; no se relajaron las comprobaciones doctrinales o de preservación.
- Revalidación final enfocada: **18 pruebas aprobadas, cero fallos/errores/omitidas**; incluye las expectativas corregidas, navegación original/contextual, ambos accesos a Estudio, citas, respuestas y notas.
- Tras añadir un tag estable al índice se repitieron los dos recorridos funcionales con éxito junto a las capturas. La primera captura compacta exigió adaptar el recorrido de prueba para encontrar un control todavía fuera de composición. El generador también se actualizó para usar la navegación actual, no los rótulos antiguos.
- El cierre visual usa Lumen/Sanctuary, claro/oscuro, ES: veinte estados normales y doce compactos (320×760 dp, texto al 200 %). Incluye una comprobación geométrica de que continuar queda por encima del dock. Resultados y huellas en [VALIDATION-1.6.json](VALIDATION-1.6.json).
- Verificador de contenido aprobado: 2.865 párrafos × tres idiomas, digests y 246 secuencias de novenas. IDs de seis repositorios y campos de oración heredados comparados. Los 74 archivos empaquetados de Biblia, referencias cruzadas e ilustraciones comparados con el instalador anterior permanecen idénticos.
- APK ensamblado, paquete/versión y firma comprobados con herramientas Android; mismo certificado del preview anterior.

Las capturas usan el árbol Compose nativo en un harness con el scaffold compartido; no son una instalación física. La revisión visual corresponde al coordinador. La matriz completa de idiomas/dispositivos, TalkBack, teclado real y audio en segundo plano permanece fuera de esta entrega.

## Estado de la hoja de ruta

H1 tiene implementación y comprobación del lote acotado; H2 inicia la procedencia del curso nuevo; H3 entrega su primer incremento. El curso de Jesús y el misterio pascual y las demás ampliaciones siguen planificados. No se entregan aún un misal, un leccionario territorial, un breviario ni un santoral anual completos.

La revisión independiente xhigh se interrumpió por límite de uso y no se declara aprobada. H8 y la revisión editorial de todo el catálogo continúan pendientes. Esta versión no equivale a aprobación eclesiástica ni a perfección universal.
