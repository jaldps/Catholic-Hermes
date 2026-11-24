# T-063 · Corrección integral de las 55 coronillas (1.9.5)

> Pendiente de completar con los resultados de las cinco auditorías. Fuente de cada devoción: el URL registrado en su JSON y guardado en `docs/content/coronillas/sources/`.

## Alcance

- Se revisan las **55 coronillas** del catálogo «Otras coronillas».
- Quedan fuera, por estar bien implementadas y ser anteriores, el **Rosario original** y la **Coronilla de la Divina Misericordia**.

## Criterio aplicado

1. Una cuenta = una sola oración o jaculatoria.
2. Oraciones canónicas (`Padre Nuestro`, `Ave María`, `Gloria`, `Credo`, `Acto de Contrición`, etc.) se referencian con `prayerRef`, para que la app muestre el texto completo y separe V/R cuando procede.
3. Jaculatorias propias con dos voces se modelan con `vr`; las que se dicen en conjunto llevan `rubric` «Se reza en conjunto / Said together / Rezado em conjunto».
4. El orden de las cuentas sigue el instrumento descrito por la fuente; no se agrupan oraciones distintas en una misma cuenta.
5. El texto devocional se reproduce literalmente de la fuente verificada. No se inventa, completa ni parafrasea.

## Cambios por lote

_(se completa)_

## Validación

_(se completa: validador de fidelidad, tests de catálogo y guías, compilación)_

## APK

_(se completa: SHA-256, rama y commit)_
