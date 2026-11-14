# Reglas editoriales · Catholic Hermes 1.8

Obligatorias para todo contenido nuevo o reescrito (santos, órdenes, cargos, devociones, preguntas, novenas, oraciones, cursos, guías de tentaciones). Un revisor rechaza cualquier entrega que las incumpla.

## 1. Verdad y fuentes

1. **Nada inventado.** Ninguna cita textual, fecha, imprimatur, indulgencia, milagro, aprobación, promesa, cifra o anécdota sin una fuente identificable (documento, obra y capítulo/párrafo, o sitio oficial). Si no hay fuente fiable, se omite o se dice abiertamente: «atribución tradicional, sin fuente documental verificable».
2. **Citas de santos** solo con obra y lugar (p. ej. «San Agustín, *Confesiones* I,1»). Si no se puede ubicar, no se cita entre comillas: se parafrasea sin comillas y sin atribución literal.
3. **Distinguir** siempre: dogma/doctrina (CIC con número), práctica eclesial aprobada, tradición piadosa, revelación privada y opinión teológica. La revelación privada nunca obliga (CIC 67).
4. **Apariciones y fenómenos:** aplicar las *Normas para proceder en el discernimiento de presuntos fenómenos sobrenaturales* (DDF, 17‑05‑2024): *nihil obstat*, *prae oculis habeatur*, *curatur*, *sub mandato*, *prohibetur et obstruatur*, declaración de no sobrenaturalidad. Indicar el estatus conocido y quién lo dio (obispo, año); si se desconoce, decirlo.
5. **Indulgencias:** solo las del *Enchiridion Indulgentiarum* (4.ª ed., 1999; citar la concesión, p. ej. «Conc. 22») o decretos de la Penitenciaría Apostólica con fecha. Siempre con las condiciones habituales cuando sea plenaria (confesión, comunión, oración por las intenciones del Papa, desapego del pecado). No reutilizar concesiones jubilares caducadas.
6. **Promesas** (novenas, oraciones, devociones): registrar el texto, a quién se atribuye, la fuente documental, las condiciones y el tipo:
   - `SOURCED`: atribuida con fuente documental identificable (p. ej. cartas de Santa Margarita María; *Diario* de Santa Faustina con número).
   - `ECCLESIAL_GRANT`: concesión de la Iglesia (indulgencia, privilegio) con documento.
   - `TRADITIONAL`: atribución tradicional sin fuente verificable.
   - `NONE`: no hay promesa asociada.
   Las fórmulas comerciales o de cadena («nunca falla», «publícala / compártela N veces», «concedido en 9 días garantizado», amenazas si se interrumpe) **no forman parte de la devoción**: se señalan expresamente como añadidos ajenos y no se reproducen como promesa.
7. **Números del Catecismo** (CIC/CCC/CatIC) reales y pertinentes; referencias bíblicas existentes en la Biblia católica (73 libros).
8. **Fechas y datos históricos** prudentes: si hay discrepancia entre fuentes, dar el rango o decir «según la tradición».

## 2. Derechos

- Textos de dominio público (oraciones antiguas, Padres, textos anteriores a ~1930, documentos del Magisterio citados brevemente) pueden reproducirse.
- No copiar íntegros textos con derechos (traducciones litúrgicas oficiales modernas —ICEL, misales nacionales—, libros de editoriales, webs comerciales). Usar traducción propia de un original de dominio público o redacción propia, y marcar «traducción/adaptación propia» cuando corresponda.
- Explicaciones, biografías, guías: siempre redacción original.

## 3. Trilingüe ES / EN / PT

- Los tres idiomas completos y naturales, no calcos. Mismo contenido sustancial en los tres.
- PT en portugués (norma brasileña preferente, comprensible en Portugal).
- Referencias en el formato de cada idioma: ES «Jn 3,16», EN «John 3:16», PT «Jo 3,16»; Catecismo ES «CIC 1822», EN «CCC 1822», PT «CIC 1822».
- Títulos de obras en su forma usual en cada idioma cuando exista.

## 4. Tono y pastoral

- Catequista experto: claro, cálido, preciso, didáctico; sin polémica agresiva; objeciones expuestas con honestidad y respondidas con argumentos.
- Ningún diagnóstico clínico. Ante adicción, depresión, autolesión, abuso o escrúpulos graves: remitir con delicadeza a confesor/director espiritual y a profesionales de salud.
- Sin garantías de resultados (curaciones, favores) por realizar una práctica.

## 5. Técnica

- No cambiar IDs, claves persistidas ni test tags existentes. IDs nuevos: `snake_case` o `kebab-case` según el archivo, únicos y estables.
- Texto con `LocalizedText(es, en, pt)`. En Kotlin, usar `"""…"""` solo cuando haga falta; escapar `$` como `${'$'}`.
- Archivos Kotlin de datos ≤ ~800 líneas (límites de la JVM); datos grandes en JSON bajo `app/src/main/assets/`.
- Compilar siempre con el script de la sesión (usa `flock /tmp/gradle.lock`). No lanzar compilaciones en paralelo.
- No tocar firmas, `applicationId` de producción ni APK históricos de `dist/`.
