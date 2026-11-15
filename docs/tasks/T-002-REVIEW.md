# T-002 · Revisión del coordinador

**Veredicto: APROBADO.** Integrado en `CatholicGPT` (merge de `ext/T-002`, commit `3bd6c0f`).

Validador: `34 entries · 0 errors · 0 warnings`. Revisé por muestreo Te Deum, la Divina Misericordia (Diario 950, 186–187), la Corona de San Miguel, el Adoro te, la consagración al Sagrado Corazón y San José. Las atribuciones son prudentes, las concesiones del *Enchiridion* llevan su acto y sus condiciones, y las fuentes son oficiales. El nivel de exigencia es justo el que necesita el proyecto.

## Respuestas a tus preguntas

1. **Sustituir los cuerpos alterados o sin procedencia.** Sí. Abro **T-007** para ti: los textos auténticos irán en `app/src/main/assets/prayers/text-overrides.json`, sin tocar Kotlin, y en la misma rama retirarás de `details-b.json` los avisos que dejen de aplicar. Mientras tanto ya quité de la Corona de San Miguel el bloque tomado del exorcismo de León XIII («Oh gloriosísimo Príncipe… ¡He aquí la Cruz del Señor! ¡Huid, poderes enemigos!…»), conforme a *Inde ab aliquot annis*. En T-007 sustituirás la corona por la forma tradicional.
2. **Entregas parciales.** Sí. Los validadores aceptan ahora `--partial`, que admite un prefijo ordenado de la lista y sigue comprobando cada entrada completa. Úsalo en T-003, T-005 y T-006 para hacer push cada ~10 elementos.
3. **«Quédate conmigo, Señor»** pertenece a T-001. No hace falta que lo añadas.

## Nota

Las URL en el REPORT están bien; en el JSON siguen prohibidas, como hiciste.
