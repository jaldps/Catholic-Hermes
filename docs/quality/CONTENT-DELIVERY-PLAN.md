# Plan de entrega del contenido

13 de noviembre de 2026 · rama `CatholicGPT` · base auditada `d172a7f`.

Este documento convierte [la hoja de ruta](CONTENT-AUDIT-ROADMAP.md), [la auditoría litúrgica](CONTENT-LITURGY-AUDIT.md) y [la formativa](CONTENT-FORMATION-AUDIT.md) en ocho hitos consecutivos. Describe trabajo futuro: redactar este plan no implementa ni verifica ninguna mejora de la aplicación. Los hallazgos estáticos de esas auditorías son la base, no evidencia nueva de funcionamiento.

## Estados, equipo y puertas

Estados: **planificado** = definido sin ejecución acreditada; **en curso** = responsable y trabajo iniciados; **verificado** = criterios satisfechos con evidencia del commit evaluado; **bloqueado** = requisito externo pendiente, identificado con responsable. Estado de ejecución: H1 implementado y comprobado por el coordinador y pruebas; revisión independiente final pendiente. Fundamentos del primer lote de H2 y primer incremento de H3 entregados; el resto sigue en curso. H4–H8 planificados. El registro de evidencia vigente está en STUDY-DELIVERY.md. H2-D, H4-C y H5-A tienen dependencias de fuentes/derechos todavía sin acreditar; no se anuncian como entregables oficiales disponibles.

Un hito empieza cuando termina el anterior. Su cierre exige todas sus tareas verificadas o un alcance reducido explícito: una ampliación sin licencia queda **bloqueada**, fuera de la entrega, con alternativa honesta documentada; nunca se marca completada por disponer de un enlace. Registrar por ID: estado, responsable, commit, fuente, evidencia, defecto pendiente y decisión de alcance. No avanzar con errores doctrinales/editoriales críticos abiertos. **Estudio será la siguiente entrega visible tras H1 y los fundamentos mínimos de H2**; no esperará la revisión completa de 246 novenas.

**Responsables:** coordinador editorial; ejecutor Android/contenido; especialista litúrgico y canónico; teólogo/catequista; revisor pastoral; revisores lingüísticos ES/EN/PT; QA de accesibilidad; responsable de fuentes/licencias. Son competencias y encargos necesarios, no personas ya contratadas ni aprobaciones eclesiásticas obtenidas. La revisión por agentes no sustituye dictamen profesional cuando se requiera.

**Esfuerzo de agentes:** Sol **medium** para ejecución, integración y comprobaciones ordinarias; **high** solo para resolución litúrgica compleja (calendario, rúbricas, numeración y disciplina territorial); **xhigh** para revisión independiente de contenido crítico y cierre de H8, separada del ejecutor. El primer intento de revisión M1 se interrumpió por límite de uso; no se considera aprobado por ese agente. Un ejecutor y un revisor por lote bastan; especialistas consultados ante decisiones concretas, sin multiplicar agentes por pantalla.

Cada hito admite una revisión, una corrección y una segunda comprobación limitada a los defectos y regresiones afectados. Si persiste un defecto crítico, queda bloqueado con fuente y pregunta concreta; no se repite un ciclo subjetivo de «más impacto visual».

## H1 · Recuperar confianza editorial

**Estado: implementado y comprobado en alcance M1; revisión independiente pendiente.** Dependencia: base auditada. Ejecutor medium; consulta litúrgica/canónica high donde haya una decisión normativa, revisión teológica y lingüística.

- **H1-A Autoridad:** sustituir «Vatican study course», «Magisterio del Vaticano» editorial y «Oración oficial» indiscriminada; identificar comentario, cita, adaptación o tradición y documento/párrafo realmente citado.
- **H1-B Noche:** presentar Completas como selección devocional abreviada, identificar extractos y referencias acordes al texto ES/EN/PT, evitar equivalencia con oficio oficial y resolver el Aleluya cuaresmal según el alcance elegido. No tratar la repetición dominical legítima como error.
- **H1-C Indulgencias:** revisar todas las fichas activas: concesión, obra, vigencia y condiciones comunes completas. Si falta concesión verificable, retirar la promesa concreta y remitir a consulta autorizada; no inventarla ni reutilizar jubileos caducados.
- **H1-D Lourdes/historia:** eliminar garantías personales de curación, separar evaluación médica y reconocimiento eclesial; corregir la pintura del XVIII/terremoto de 1647 con fuente o reconocer incertidumbre.
- **H1-E Biblia:** separar Douay-Rheims/Nova Vulgata y retirar el latín inexistente; no atribuir identidad editorial que la procedencia digital no demuestre.
- **H1-F Rito y disciplina:** precisar primera Comunión/Confirmación latinas y excepciones orientales; armonizar ayuno con salud, edades y consulta territorial. Idioma no determina país.

**Puerta y evidencia:** inventario de todas las ocurrencias alcanzables, comparación antes/después ES/EN/PT, fuentes exactas, revisión de las seis tareas y comprobaciones de las rutas afectadas. Cero atribuciones falsas o garantías automáticas en ese alcance. Identidad bibliográfica pendiente queda explícita, no supuesta. El informe de implementación debe indicar qué se corrigió y qué sigue bloqueado.

## H2 · Procedencia, derechos y consistencia

**Estado: fundamentos del primer curso entregados; saneamiento restante planificado.** Depende del cierre de H1; saneamiento restante por lotes durante H4–H7. Medium, responsable editorial/licencias y revisores ES/EN/PT.

- **H2-A** Crear ficha con ID estable, tipo, fuente exacta, edición, idioma, alcance rito/territorio, titular/licencia, modificaciones, fecha y responsable. Aplicarla primero a todo contenido de Estudio nuevo/modificado y referencias reutilizadas. Inventariar la cola de tres corpus bíblicos, 68 oraciones, 246 novenas, devociones, santos e historia sin exigir su saneamiento completo para empezar H3.
- **H2-B** Revisar equivalencia doctrinal/pastoral y atribuciones del lote de Estudio. Programar después promesas, citas, patronazgos, revelación privada y sacramentales; distinguir fiel/ministro y hecho/tradición. Cada lote necesita aceptación propia antes de declararse revisado; el resto conserva estado pendiente hasta H8.
- **H2-C** Contrastar referencias del lote de Estudio por edición y conservar rangos múltiples/capítulos cruzados ya resueltos. Programar la revisión completa de salmos/títulos, Ester/Daniel, versículos partidos y referencias cruzadas para H4.
- **H2-D** Abrir matriz de derechos para Compendio, leccionario, misal, Horas, audio e imágenes; acreditar los usados en Estudio. Obtener/archivar redistribución y uso offline antes de cada incorporación en H3–H7. Sin permiso, bloquear el texto íntegro y mantener resúmenes/enlaces identificados.

**Puerta y evidencia:** fuentes/derechos y revisión trilingüe suficientes para el siguiente lote de Estudio; cola restante con propietario y hito, muestras bíblicas comparadas con edición identificada y paquetes bloqueados. Un texto público en internet no acredita permiso.

## H3 · Formación y Biblia conectadas

**Estado: primer incremento entregado (cuatro cursos, veinte lecciones); las ampliaciones siguientes continúan planificadas.** Depende de H2 mínimo; siguiente entrega visible; medium, catequista y lingüistas.

- **H3-A** Adaptar las tres entradas existentes (explorar/volver/profundizar) a 2/5/10 minutos; ofrecer siguiente paso, núcleo cristológico, cuatro pilares, historia de salvación y glosario. Diferenciar iniciación de adultos y regreso del bautizado.
- **H3-B** Convertir Estudio (incluida su entrada en Explorar) en currículo de iniciación, intermedio y profundización: cursos finitos con requisitos, objetivos, secuencia y cierre. Partir de los tres cursos/quince lecciones existentes; ampliar con explicaciones sustanciales, contexto bíblico/doctrinal, dificultades reales, reflexión, oración y aplicación. No rellenar para aumentar cifras. Incluir preguntas 31–40 y 101 en itinerarios; revisar las 101 con objeciones fielmente expuestas. Evaluaciones de comprensión/aplicación alineadas a objetivos, explicación del error y progreso/reanudación. Enlazar cada referencia al párrafo/pasaje completo y notas del Catecismo.
- **H3-C** Añadir introducciones a 73 libros, géneros/interpretación, planes finitos de Jesús/Evangelios/salmos/historia de salvación/lectura extensa y lectio con silencio. Integrar marcadores, notas, colecciones y reanudación. Si hay comparación de 66 libros, advertir canon y mantener entrada católica.
- **H3-D** Acceso respuesta breve → explicación → fuente; Compendio íntegro solo tras H2-D, conservando mientras tanto la etiqueta editorial.

**Puerta y evidencia:** matriz nivel → curso → objetivo → lección → evaluación; cursos publicados completos, sin lecciones repetidas de relleno; fuentes directas y revisión de profundidad por catequista. Cobertura 101/101 y guiones de las tres personas completando un paso sin perder progreso; introducciones/planes revisados. No se certifica preparación sacramental por completar un curso.

## H4 · Misa, territorio y calendario

**Estado: planificado.** Depende de H3 y H2-D para textos. ejecución medium; diseño/verificación normativa high con liturgista.

- **H4-A** Selección explícita de país, rito y diócesis solo soportados; normas locales de precepto, ayuno y traslados con fuente. Itinerarios de Adviento a Tiempo Ordinario.
- **H4-B** Guía sobria de Misa: respuestas/gestos fiel-ministro, presencia real, preparación y ayuno eucarístico, participación sin comulgar, antes/después.
- **H4-C** Integrar lecturas autorizadas territoriales: A/B/C, I/II, solemnidades/opciones, respuesta del salmo, aclamaciones/secuencias; fecha y descarga acotada. Misal/propios solo con edición y derechos. Conservar advertencia de selecciones bíblicas mientras corresponda.
- **H4-D** Santoral anual offline con fuente o indisponibilidad honesta; distinguir santo biográfico/celebración y advertir respaldo en otro idioma.

**Puerta y evidencia:** matriz fecha-territorio-edición y casos de Cuaresma/Triduo/Pascua, solemnidad trasladada, opciones y ausencia de red contrastados por liturgista. Ninguna cobertura universal ni misal completo anunciado sin acreditación.

## H5 · Horas y aprender a orar

**Estado: planificado.** Depende de H4 y derechos. medium, high para rúbricas y calendario.

- **H5-A** Primera entrega autorizada de Laudes/Vísperas/Completas: salterio, tiempo, santoral, propios/comunes, opciones y rúbricas. Oficio de lecturas/horas intermedias solo como entrega posterior delimitada. Sin fuente/derechos queda bloqueada, manteniendo selecciones devocionales honestas.
- **H5-B** Modos rezar/aprender, pausas y explicación breve; oración por momento/necesidad, adoración 10/20/30 minutos, oración mental, distracción y sequedad. Completar estructura de Corona de San Miguel/letanías con fuente. Rosario y Coronilla conservan protagonismo; «Ayúdame a orar» secundario.

**Puerta y evidencia:** cotejo textual y rúbricas de cada variante soportada, fechas representativas y paquetes offline; una persona identifica si reza oficio o devoción y completa la sesión. No prometer breviario completo.

## H6 · Sacramentos y conciencia serena

**Estado: planificado.** Depende de H5. medium, catequista y revisión pastoral.

- **H6-A** Centro de siete sacramentos: significado, destinatario, preparación y contacto; padrinos/iniciación, disciplina local, Unción sin limitarla a últimas horas, matrimonio y vocaciones con atención competente.
- **H6-B** Unificar Confesión antes/durante/después: examen razonable, ayuda sacerdotal, especie/número posible, olvido, contrición, absolución, penitencia/reparación; separar examen diario, sacramental y hábito. Añadir ayuda para escrúpulos/pensamientos intrusivos y tentación sin diagnóstico ni cálculo de culpa.
- **H6-C** Uso sin contadores ni registros, privacidad de notificaciones/copias/exportaciones; sin absolución simulada ni puntuación de santidad.

**Puerta y evidencia:** siete fichas con siguiente contacto, escenarios regreso/enfermedad/escrúpulos revisados pastoralmente y verificación del recorrido sin registro ni exposición de datos sensibles.

## H7 · Vida cotidiana y profundidad opcional

**Estado: planificado.** Depende de H6. medium y especialistas por contenido.

- **H7-A** Virtudes, siete semanas de caridad/doctrina social; familia, infancia/adolescencia, pareja/soltería/vocación, infertilidad/embarazo, cuidado/vejez, enfermedad y duelo/pérdida gestacional. Formato explicación → oración → acción → ayuda; apoyos ante adicción/abuso/violencia, sin exigir exposición al peligro.
- **H7-B** Parroquia guardada con contacto, origen/fecha de horarios y servicios; búsqueda externa explícita, sin afirmar disponibilidad actual con datos antiguos.
- **H7-C** Búsqueda cotidiana transversal, favoritos/recientes/descargas compartidos; Hoy ofrece una siguiente acción y cuatro entradas conservadas. Biblioteca avanzada contextualizada (Padres, documentos, historia, órdenes, Oriente); noticias secundarias.
- **H7-D** Audio con permisos, transcripción, descarga, pausas/reanudación/temporizador y tamaño; distinguir voz sintética de música grabada.

**Puerta y evidencia:** los cinco recorridos finitos de la auditoría formativa tienen inicio/cierre; familia/cuidador encuentran ayuda concreta; contactos fechados, paquetes con tamaño y derechos, sin rankings espirituales.

## H8 · Cierre independiente y entrega comprobable

**Estado: planificado.** Depende de H1–H7 cerrados según alcance. QA medium y revisor independiente xhigh.

- **H8-A** En cada hito conservar corpus/referencias, progreso, diario/notas/intenciones, preferencias, IDs antiguos de identidad, etiquetas accesibles y tags. Migraciones con datos existentes; sin sistemas paralelos a `AppKit.kt`/`AppComponents.kt`.
- **H8-B** Matriz de ambas identidades Lumen/Sanctuary × claro/oscuro × ES/EN/PT, sistema y fuente persistentes; ancho estrecho, texto ampliado, lector de pantalla, foco, contraste y controles sin recorte. Capturas y recorridos funcionales de pantallas modificadas; contenido esencial offline.
- **H8-C** Ejecutar build/pruebas exigidos por `AGENTS.md` con bloqueo Gradle compartido y comprobaciones enfocadas justificadas; registrar comando, commit y resultado, sin reutilizar éxitos históricos.
- **H8-D** Revisión independiente contra cada ID y fuente: enumerar faltantes y límites. Una ronda final de corrección y revalidación afectada; fallo crítico bloquea entrega.

**Puerta y evidencia:** informe con commit exacto, pruebas/capturas y matriz de cumplimiento; estados finales por tarea y bloqueos con alternativa. «Verificado» se limita a lo demostrado; no equivale a imprimatur, prueba en dispositivo ni publicación. Este plan no ejecuta pruebas, genera APK ni realiza commits.
